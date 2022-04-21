package com.project.geolocation.controller;

import com.project.geolocation.domain.dto.ApiResponse;
import com.project.geolocation.domain.dto.GeolocationDTO;
import com.project.geolocation.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.Pattern;



@Validated
@RestController
@RequestMapping("/geolocation")
public class GeoController {
    private final GeoService geoService;

    @Autowired
    public GeoController(GeoService geoService){
        this.geoService = geoService;
    }

    @GetMapping
    public ResponseEntity<?> getGeolocation(@Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}" +
            "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])$",
            message = "IP address must be in the format: 0.0.0.0 to 255.255.255.255") @RequestParam String ipAddress){
        GeolocationDTO geolocationDTO = geoService.getGeoDataFromAPI(ipAddress);
        ApiResponse<Object, Object> response;
        if(geolocationDTO.getStatus().equalsIgnoreCase("success")){
            response = ApiResponse.success(geolocationDTO, "Geolocation found");
        }else
            response = ApiResponse.failed(HttpStatus.NOT_FOUND,"Geolocation not found in API");

        return ResponseEntity.ok(response);
    }

}

