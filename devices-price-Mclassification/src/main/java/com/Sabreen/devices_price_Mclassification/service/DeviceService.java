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

    // Create or Update
    // public Device save(Device device) {
    //     return deviceRepository.save(device);
    // }

    // Read All
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    // Read by ID
    // public Optional<Device> getDeviceById(Long id) {
    //     return deviceRepository.findById(id);
    // }
    public Optional<Device> getDeviceById(Long id) {
        Optional<Device> device = deviceRepository.findById(id);
        if (!device.isPresent()) {
            throw new RuntimeException("Device not found with id: " + id);
        }
        return device;
    }

    // // Update
    // public Device update(Long id, Device deviceDetails) {
    //     Device device = deviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Device not found with id " + id));
    //     // تحديث الحقول حسب الحاجة
    //     extracted(deviceDetails, device);
    //     device.setBlue((Boolean) deviceDetails.getBlue());
    //     device.setClock_speed(deviceDetails.getClock_speed());
    //     device.setDual_sim(deviceDetails.getDual_sim());
    //     device.setFc(deviceDetails.getFc());
    //     device.setFour_g(deviceDetails.getFour_g());
    //     device.setInt_memory(deviceDetails.getInt_memory());
    //     device.setM_dep(deviceDetails.getM_dep());
    //     device.setMobile_wt(deviceDetails.getMobile_wt());
    //     device.setN_cores(deviceDetails.getN_cores());
    //     device.setPc(deviceDetails.getPc());
    //     device.setPx_height(deviceDetails.getPx_height());
    //     device.setPx_width(deviceDetails.getPx_width());
    //     device.setRam(deviceDetails.getRam());
    //     device.setSc_h(deviceDetails.getSc_h());
    //     device.setSc_w(deviceDetails.getSc_w());
    //     device.setTalk_time(deviceDetails.getTalk_time());
    //     device.setThree_g(deviceDetails.getThree_g());
    //     device.setTouch_screen(deviceDetails.getTouch_screen());
    //     device.setWifi(deviceDetails.getWifi());
        
    //     return deviceRepository.save(device);
    // }

    // private void extracted(Device deviceDetails, Device device) {
    //     device.setBattery_power(deviceDetails.getBattery_power());
    // }

    // // Delete
    // public void delete(Long id) {
    //     Device device = deviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Device not found with id " + id));
    //     deviceRepository.delete(device);
    // }
    // Save device
    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
}

