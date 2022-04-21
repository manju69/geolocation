package com.project.geolocation.repository;

import com.project.geolocation.domain.Geolocation;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeoRepository extends JpaRepository<Geolocation,String> {
    Optional<Geolocation> findByIpAddress(String ipAddress);
}
