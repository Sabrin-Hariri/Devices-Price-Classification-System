package com.Sabreen.devices_price_Mclassification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Sabreen.devices_price_Mclassification.entity.Device;



@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    
}
