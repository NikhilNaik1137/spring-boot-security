package com.nik.security.demo.controller;

import com.nik.security.demo.dto.DeviceDTO;
import com.nik.security.demo.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/devices")
public class DeviceController {

    private final DeviceService deviceService;

    DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    @Secured("ADMIN")
    public ResponseEntity getAllDevices() {
        try {
            return ResponseEntity.ok(deviceService.getDevices());
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unable to retrieve all devices");
        }
    }

    @PostMapping
    public ResponseEntity createDevice(@RequestBody DeviceDTO deviceDTO) {
        try {
            deviceService.createDevice(deviceDTO);
            return ResponseEntity.ok().body("Created Device Successfully");
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unable to create device");
        }
    }

}
