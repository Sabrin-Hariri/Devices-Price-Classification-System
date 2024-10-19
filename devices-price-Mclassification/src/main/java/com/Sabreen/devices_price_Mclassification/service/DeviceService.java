package com.Sabreen.devices_price_Mclassification.service;

import com.Sabreen.devices_price_Mclassification.entity.Device;
import com.Sabreen.devices_price_Mclassification.repository.DeviceRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    
    @Autowired
    private DeviceRepository deviceRepository;

    // Read All
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public Optional<Device> getDeviceById(Long id) {
        Optional<Device> device = deviceRepository.findById(id);
        if (!device.isPresent()) {
            throw new RuntimeException("Device not found with id: " + id);
        }
        return device;
    }

    // Save device
    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
}

