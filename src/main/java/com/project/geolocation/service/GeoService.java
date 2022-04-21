package com.project.geolocation.service;

import com.project.geolocation.exception.ClientDataException;
import com.project.geolocation.domain.Geolocation;
import com.project.geolocation.domain.dto.GeolocationDTO;
import com.project.geolocation.repository.GeoRepository;
import com.project.geolocation.service.Adapter.GeolocationAdapter;
import com.project.geolocation.service.geoServiceInterface.IgeoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.util.Optional;
@Log4j2
@Service
public class GeoService implements IgeoService {
    private final String getURL = "http://ip-api.com/json/";
    private final GeoRepository geoRepository;
    private RestTemplate restTemplate;

    @Autowired
    public GeoService(GeoRepository geoRepository,RestTemplate restTemplate){
       this.geoRepository = geoRepository;
       this.restTemplate = restTemplate;
    }

   @Cacheable("geolocationbyip")
    public GeolocationDTO getGeoDataFromAPI(String ipAddress){
        GeolocationDTO geolocationDTO;
        Geolocation geoData;
       try{
           log.info("Ip address Searching in Database: "+ipAddress);
          geoData = getGeoDataFromDB(ipAddress);
           if(LocalDateTime.now().minusMinutes(5l).isAfter(geoData.getUpdateTime())){
               log.info("Refreshing data in 5 minutes by calling external API");
               geoData = restTemplate.getForObject(getURL+ipAddress,Geolocation.class);
           }
          geolocationDTO = GeolocationAdapter.get(geoData);
        }
        catch (ClientDataException c){
           log.info("External API is called to search IP address: "+ipAddress);
               geoData = restTemplate.getForObject(getURL+ipAddress,Geolocation.class);
                if(geoData.getStatus().equalsIgnoreCase("success")){
                    saveGeoData(ipAddress,geoData);
                }
                geolocationDTO = GeolocationAdapter.get(geoData);
        }
        return geolocationDTO;
    }

    public void saveGeoData(String ipAddress, Geolocation geolocation){
        log.info("save geolocation data in database with ip address: "+ipAddress);
        geolocation.setIpAddress(ipAddress);
        geoRepository.save(geolocation);
    }

    public Geolocation getGeoDataFromDB (String ipAddress){
       log.info("Database is called to search Ip address: "+ipAddress);
        Optional<Geolocation> geolocationOpt = geoRepository.findByIpAddress(ipAddress);
        Geolocation geolocation = null;
        if(geolocationOpt.isPresent()){
            geolocation = geolocationOpt.get();
            log.info("Record found in database");
        }else{
            log.info("Record not found in database: need to call external API");
            throw new ClientDataException("Ip address not present in database");
        }

        return geolocation;
    }
}
