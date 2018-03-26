package com.mobile.signal.beans;

import java.io.Serializable;

/**
 * wifi信号Bean
 *
 * @author gaoxuefeng
 * @date 2018/3/15
 */

public class WifiSignalBean implements Serializable {
    private String bssid;//信号 bssid
    private int rssi;//信号强度(100最大,0最小)
    private int level;//信号强度(默认表示)
    private String ssid;//信号名称
    private String mac;//mac地址

    public String getBssid() {
        return bssid == null ? "": bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public String getSsid() {
        return ssid == null ? "": ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getMac() {
        return bssid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public String toString() {
        return "WifiSignalBean{" +
                "bssid='" + bssid + '\'' +
                ", rssi=" + rssi +
                ", level=" + level +
                ", ssid='" + ssid + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }
}
