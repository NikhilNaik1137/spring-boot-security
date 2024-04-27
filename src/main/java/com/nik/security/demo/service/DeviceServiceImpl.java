package com.nik.security.demo.service;


import com.nik.security.demo.dto.DeviceDTO;
import com.nik.security.demo.entity.DeviceSQLEntity;
import com.nik.security.demo.repo.DeviceSQLRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceSQLRepository deviceSQLRepository;

    public DeviceServiceImpl(DeviceSQLRepository deviceSQLRepository) {
        this.deviceSQLRepository = deviceSQLRepository;
    }

    @Override
    public List<DeviceDTO> getDevices() {
        List<DeviceSQLEntity> deviceEntities = deviceSQLRepository.findAll();
        List<DeviceDTO> deviceDTOs = deviceEntities.stream().map((deviceEntity) -> {
            DeviceDTO deviceDTO = new DeviceDTO();
            BeanUtils.copyProperties(deviceEntity, deviceDTO);
            return deviceDTO;
        }).toList();
        return deviceDTOs;
    }

    @Override
    public void createDevice(DeviceDTO deviceDTO) {
        DeviceSQLEntity deviceEntity = new DeviceSQLEntity();
        BeanUtils.copyProperties(deviceDTO, deviceEntity);
        deviceSQLRepository.save(deviceEntity);
    }

    @Override
    public void deleteDevice() {

    }
}
