package com.mobile.signal.beans;

import android.telephony.TelephonyManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.telephony.TelephonyManager.NETWORK_TYPE_CDMA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_GSM;
import static android.telephony.TelephonyManager.NETWORK_TYPE_LTE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_UMTS;

/**
 * 基站,wifi,和手机基本详情
 * Created by gaoxuefeng on 2018/3/16.
 */

public class MyPhoneInfo implements Serializable {
    private int type = -1;
    private int cid = -1;
    private int rsrp = -1;
    private int sinr = -1;
    private int rssi = -1;
    private int asulevel = -1;
    private int lac = -1;
    private int ratType = TelephonyManager.NETWORK_TYPE_UNKNOWN;
    private int psc = -1;
    private int pci = -1;
    private int tac = -1;
    private int cqi = -1;
    public int getInfoType = -1;
    public String time;
    //基本信息
    public String serialNumber;
    public String operaterName;
    public String operaterId;
    public String deviceId;
    public String deviceSoftwareVersion;
    public String imsi;
    public String imei;
    public int mnc = -1;
    public int mcc = -1;
    public int phoneDatastate = -1;
    public String phoneModel;
    private int sid = -1;
    private int nid = -1;
    private int bid = -1;
    private int level = -1;
    private String mac;
    private List<WifiSignalBean> wifiSignalList;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getDbm() {
        return getRsrp();
    }


    public int getRsrp() {
        return rsrp;
    }

    public void setRsrp(int rsrp) {
        this.rsrp = rsrp;
    }


    public int getSinr() {
        return sinr;
    }

    public void setSinr(int sinr) {
        this.sinr = sinr;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public int getAsulevel() {
        return asulevel;
    }

    public void setAsulevel(int asulevel) {
        this.asulevel = asulevel;
    }

    public int getLac() {
        return lac;
    }

    public void setLac(int lac) {
        this.lac = lac;
    }

    public int getRatType() {
        return ratType;
    }

    public void setRatType(int ratType) {
        this.ratType = ratType;
    }

    public int getPsc() {
        return psc;
    }

    public void setPsc(int psc) {
        this.psc = psc;
    }

    public int getPci() {
        return pci;
    }

    public void setPci(int pci) {
        this.pci = pci;
    }

    public int getTac() {
        return tac;
    }

    public void setTac(int tac) {
        this.tac = tac;
    }

    public int getCqi() {
        return cqi;
    }

    public void setCqi(int cqi) {
        this.cqi = cqi;
    }

    public int getGetInfoType() {
        return getInfoType;
    }

    public void setGetInfoType(int getInfoType) {
        this.getInfoType = getInfoType;
    }

    public String getTime() {
        return time == null ? "": time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSerialNumber() {
        return serialNumber == null ? "": serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOperaterName() {
        return operaterName == null ? "": operaterName;
    }

    public void setOperaterName(String operaterName) {
        this.operaterName = operaterName;
    }

    public String getOperaterId() {
        return operaterId == null ? "": operaterId;
    }

    public void setOperaterId(String operaterId) {
        this.operaterId = operaterId;
    }

    public String getDeviceId() {
        return deviceId == null ? "": deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceSoftwareVersion() {
        return deviceSoftwareVersion == null ? "": deviceSoftwareVersion;
    }

    public void setDeviceSoftwareVersion(String deviceSoftwareVersion) {
        this.deviceSoftwareVersion = deviceSoftwareVersion;
    }

    public String getImsi() {
        return imsi == null ? "": imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getImei() {
        return imei == null ? "": imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getMnc() {
        return mnc;
    }

    public void setMnc(int mnc) {
        this.mnc = mnc;
    }

    public int getMcc() {
        return mcc;
    }

    public void setMcc(int mcc) {
        this.mcc = mcc;
    }

    public int getPhoneDatastate() {
        return phoneDatastate;
    }

    public void setPhoneDatastate(int phoneDatastate) {
        this.phoneDatastate = phoneDatastate;
    }

    public String getPhoneModel() {
        return phoneModel == null ? "": phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public int getNetType() {
        switch (getRatType()) {
            case NETWORK_TYPE_LTE:
                return 4;
            case NETWORK_TYPE_GSM:
                return 1;
            case NETWORK_TYPE_CDMA:
                return 2;
            case NETWORK_TYPE_UMTS:
                return 3;
        }
        return 0;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getSid() {
        return sid;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getBid() {
        return bid;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public String getMac() {
        return mac == null ? "": mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    //运营商
    //02是中国移动，01是中国联通，03是中国电信
    public int getCarrier() {
        switch (mnc) {
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 3;
        }
        return 0;
    }

    public void setWifiSignalList(List<WifiSignalBean> wifiSignalList) {
        this.wifiSignalList = wifiSignalList;
    }

    public List<WifiSignalBean> getWifiSignalList() {
        return wifiSignalList == null ? new ArrayList(): wifiSignalList;
    }


    public List<BeaconInfo> getBeaconList(Map<String, List<BeaconInfo>> mapBltScanResult) {
        List<BeaconInfo> beacomInfoList = new ArrayList<>();
        for (List<BeaconInfo> beaconInfos : mapBltScanResult.values()) {
            beacomInfoList.addAll(beaconInfos);
        }
        return beacomInfoList;
    }

    @Override
    public String toString() {
        return "MyPhoneInfo{" +
                "type=" + type +
                ", cid=" + cid +
                ", rsrp=" + rsrp +
                ", sinr=" + sinr +
                ", rssi=" + rssi +
                ", asulevel=" + asulevel +
                ", lac=" + lac +
                ", ratType=" + ratType +
                ", psc=" + psc +
                ", pci=" + pci +
                ", tac=" + tac +
                ", cqi=" + cqi +
                ", getInfoType=" + getInfoType +
                ", time='" + time + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", operaterName='" + operaterName + '\'' +
                ", operaterId='" + operaterId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", deviceSoftwareVersion='" + deviceSoftwareVersion + '\'' +
                ", imsi='" + imsi + '\'' +
                ", imei='" + imei + '\'' +
                ", mnc=" + mnc +
                ", mcc=" + mcc +
                ", phoneDatastate=" + phoneDatastate +
                ", phoneModel='" + phoneModel + '\'' +
                ", sid=" + sid +
                ", nid=" + nid +
                ", bid=" + bid +
                ", level=" + level +
                ", mac='" + mac + '\'' +
                ", wifiSignalList=" + wifiSignalList +
                '}';
    }
}
