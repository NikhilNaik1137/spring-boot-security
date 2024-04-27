package com.nik.security.demo.service;

import com.nik.security.demo.dto.DeviceDTO;

import java.util.List;

public interface DeviceService {

    List<DeviceDTO> getDevices();

    void createDevice(DeviceDTO deviceDTO);

    void deleteDevice();
}
