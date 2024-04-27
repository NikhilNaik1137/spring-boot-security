package com.nik.security.demo.dto;

import java.time.LocalDateTime;

public class DeviceDTO {

    private String deviceName;

    private DeviceType deviceType;

    private LocalDateTime registeredAt;

    public DeviceDTO() {
    }

    public DeviceDTO(String deviceName, DeviceType deviceType, LocalDateTime registeredAt) {
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.registeredAt = registeredAt;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }
}
