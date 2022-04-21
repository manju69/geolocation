package com.project.geolocation.service.geoServiceInterface;

import com.project.geolocation.domain.Geolocation;
import com.project.geolocation.domain.dto.GeolocationDTO;

public interface IgeoService {
    public GeolocationDTO getGeoDataFromAPI(String ipAddress);
    public void saveGeoData(String ipAddress, Geolocation geolocation);
    public Geolocation getGeoDataFromDB(String ipAddress);
}
