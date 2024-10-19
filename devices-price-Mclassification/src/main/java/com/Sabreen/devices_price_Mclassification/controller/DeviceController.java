package com.Sabreen.devices_price_Mclassification.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.Sabreen.devices_price_Mclassification.service.DeviceService;
import com.Sabreen.devices_price_Mclassification.entity.Device;

@RestController
@RequestMapping("/api/devices")

public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    
// Path to predict the device price using the device ID
    @PostMapping("/predict/{id}")
    public ResponseEntity<String> predictDevicePrice(@PathVariable("id") Long id) {

        // Retrieve the device data using the device ID
        Device device = deviceService.getDeviceById(id)
                .orElseThrow(() -> new RuntimeException("Device not found"));

                
        // Convert the device specifications to JSON
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:5000/prediction"; //  Flask API

        // send to  Flask
        Map<String, Object> request = new HashMap<>();
        request.put("battery_power", device.getBattery_power());
        request.put("blue", device.getBlue());
        request.put("clock_speed", device.getClock_speed());
        request.put("dual_sim", device.getDual_sim());
        request.put("fc", device.getFc());
        request.put("four_g", device.getFour_g());
        request.put("int_memory", device.getInt_memory());
        request.put("m_dep", device.getM_dep());
        request.put("mobile_wt", device.getMobile_wt());
        request.put("n_cores", device.getN_cores());
        request.put("pc", device.getPc());
        request.put("px_height", device.getPx_height());
        request.put("px_width", device.getPx_width());
        request.put("ram", device.getRam());
        request.put("sc_h", device.getSc_h());
        request.put("sc_w", device.getSc_w());
        request.put("talk_time", device.getTalk_time());
        request.put("three_g", device.getThree_g());
        request.put("touch_screen", device.getTouch_screen());
        request.put("wifi", device.getWifi());

        // Call the Flask API and get the result
    //     ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);


    //     // Set the prediction on the device's field
    //     device.setPredictedPrice(response.getBody());  

    //     // Save the device with all fields in the database
    //     deviceService.saveDevice(device);

    //     return ResponseEntity.ok("Price prediction: " + response.getBody());
    // }
    try {
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);
        String predictedPrice = response.getBody();

        device.setPredictedPrice(predictedPrice);  
        deviceService.saveDevice(device);

        return ResponseEntity.ok("Price prediction: " + predictedPrice);

    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Error occurred while predicting device price: " + e.getMessage());
    }}
    // Path to create a new device and save it in the database
    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        Device savedDevice = deviceService.saveDevice(device);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDevice);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable("id") Long id) {
        Device device = deviceService.getDeviceById(id)
            .orElseThrow(() -> new RuntimeException("Device not found with id: " + id));
        return ResponseEntity.ok(device);
    }
     //get all devices
    @GetMapping("")
    public ResponseEntity<List<Device>> getAllDevices() {
    List<Device> devices = deviceService.getAllDevices();
    return ResponseEntity.ok(devices);
}


}
