package com.mobile.signal.beans;

import java.io.Serializable;

/**
 * 蓝牙信息
 * Created by gaoxuefeng on 2018/3/16.
 */

public class BeaconInfo implements Serializable {
    private String uuid;
    private String major;
    private String minor;
    private String power;
    private String rssi;
    private String address;

    public String getUuid() {
        return uuid == null ? "": uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMajor() {
        return major == null ? "": major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor == null ? "": minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public String getPower() {
        return power == null ? "": power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getRssi() {
        return rssi == null ? "": rssi;
    }

    public void setRssi(String rssi) {
        this.rssi = rssi;
    }

    public String getAddress() {
        return address == null ? "": address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "BeaconInfo{" +
                "uuid='" + uuid + '\'' +
                ", major='" + major + '\'' +
                ", minor='" + minor + '\'' +
                ", power='" + power + '\'' +
                ", rssi='" + rssi + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
