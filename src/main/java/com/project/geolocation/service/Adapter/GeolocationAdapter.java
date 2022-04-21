package com.project.geolocation.service.Adapter;

import com.project.geolocation.domain.Geolocation;
import com.project.geolocation.domain.dto.GeolocationDTO;

public class GeolocationAdapter {
    public static GeolocationDTO get(Geolocation dao){
        GeolocationDTO dto = new GeolocationDTO();
        dto.setQuery(dao.getIpAddress());
        dto.setStatus(dao.getStatus());
        dto.setCountryCode(dao.getCountryCode());
        dto.setCountry(dao.getCountry());
        dto.setRegion(dao.getRegion());
        dto.setRegionName(dao.getRegionName());
        dto.setCity(dao.getCity());
        dto.setTimeZone(dao.getTimeZone());
        dto.setZip(dao.getZip());
        dto.setLat(dao.getLat());
        dto.setLon(dao.getLon());
        dto.setAs(dao.getAs());
        dto.setIsp(dao.getIsp());
        dto.setOrg(dao.getOrg());
        return dto;
    }
    public static Geolocation get(GeolocationDTO dto) {
        if (dto == null) return null;
        Geolocation dao = new Geolocation();
        dao = convert(dto, dao);
        return dao;
    }

    public static Geolocation get(GeolocationDTO dto, Geolocation dao) {
        if (dto == null) return null;
        return convert(dto,dao);
    }

    public static Geolocation convert(GeolocationDTO dto, Geolocation dao) {
        dao.setQuery(dto.getQuery());
        dao.setStatus(dto.getStatus());
        dao.setCountry(dto.getCountry());
        dao.setCountryCode(dto.getCountryCode());
        dao.setRegion(dto.getRegion());
        dao.setRegionName(dto.getRegionName());
        dao.setTimeZone(dto.getTimeZone());
        dao.setZip(dto.getZip());
        dao.setLat(dto.getLat());
        dao.setLon(dto.getLon());
        dao.setAs(dto.getAs());
        dao.setOrg(dto.getOrg());
        dao.setIsp(dto.getIsp());
        return dao;
    }
}
