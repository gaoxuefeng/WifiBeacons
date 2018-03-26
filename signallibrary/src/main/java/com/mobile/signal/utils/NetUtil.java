package com.mobile.signal.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

import com.mobile.signal.IBeaconRequest;
import com.mobile.signal.beans.BeaconInfo;
import com.mobile.signal.beans.MyPhoneInfo;
import com.mobile.signal.beans.WifiSignalBean;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.BLUETOOTH_SERVICE;
import static com.mobile.signal.SignalInit.getContext;


/**
 * Created by gaoxuefeng on 2018/3/15.
 */

public class NetUtil {
    String strNetworkType = "";


    public static int getMCC() {
        if (getIMSI().length() == 15) {
            return MyInteger.parseInt(getIMSI().substring(0, 3));
        }
        return 0;
    }

    public static int getMNC() {
        if (getIMSI().length() == 15) {
            return MyInteger.parseInt(getIMSI().substring(3, 5));
        }
        return 0;
    }

    public static int getLac() {
        CellLocation cel = getTelephonyManager().getCellLocation();
        if (cel == null) {
            return -1;
        }
        //移动联通 GsmCellLocation
        if (cel instanceof GsmCellLocation) {
            return ((GsmCellLocation) cel).getLac();
        } else if (cel instanceof CdmaCellLocation) {
            return ((CdmaCellLocation) cel).getNetworkId();
        }
        return -1;
    }

    public static int getCID() {
        CellLocation cel = getTelephonyManager().getCellLocation();
        if (cel == null) {
            return -1;
        }
        //移动联通 GsmCellLocation
        if (cel instanceof GsmCellLocation) {
            return ((GsmCellLocation) cel).getCid();
        } else if (cel instanceof CdmaCellLocation) {
            return ((CdmaCellLocation) cel).getBaseStationId();
        }
        return 0;
    }

    //TED
    public static CellIdentityLte getTEDCellIdentityLte() {
        CellInfo cellInfo = getCellInfo();
        if (cellInfo != null) {
            if (cellInfo instanceof CellInfoLte) {
                return ((CellInfoLte) cellInfo).getCellIdentity();
            } else if (cellInfo instanceof CellInfoGsm) {

            }
        }
        return null;
    }

    public static CellIdentityLte getGSMCellIdentityLte() {
        CellInfo cellInfo = getCellInfo();
        if (cellInfo != null) {
            if (cellInfo instanceof CellInfoGsm) {
                return ((CellInfoLte) cellInfo).getCellIdentity();
            } else if (cellInfo instanceof CellInfoGsm) {

            }
        }
        return null;
    }

    /**
     * 获取手机IMSI号
     */
    public static String getIMSI() {
        String imsi = getTelephonyManager().getSubscriberId();
        if (imsi == null) {
            return "";
        }
        return imsi;
    }

    public static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
    }

    public static CellInfo getCellInfo() {
        List<CellInfo> cellInfoList = getTelephonyManager().getAllCellInfo();
        if (cellInfoList != null && cellInfoList.size() > 0) {
            return cellInfoList.get(0);
        }
        return null;
    }

    public static MyPhoneInfo getPhoneInfo() {
        MyPhoneInfo phoneInfo = new MyPhoneInfo();
        CellInfo cellInfo = getCellInfo();
        if (cellInfo != null) {
            if (cellInfo instanceof CellInfoLte) {
                CellIdentityLte cellIdentity = ((CellInfoLte) cellInfo).getCellIdentity();
                phoneInfo.setPci(cellIdentity.getCi());
                phoneInfo.setCqi(cellIdentity.getPci());
                phoneInfo.setTac(cellIdentity.getTac());
                phoneInfo.setRsrp(((CellInfoLte) cellInfo).getCellSignalStrength().getDbm());
                phoneInfo.setAsulevel(((CellInfoLte) cellInfo).getCellSignalStrength().getAsuLevel());
                phoneInfo.setRatType(TelephonyManager.NETWORK_TYPE_LTE);
                phoneInfo.setLevel(((CellInfoLte) cellInfo).getCellSignalStrength().getLevel());
            } else if (cellInfo instanceof CellInfoGsm) {
                CellIdentityGsm cellIdentityGsm = ((CellInfoGsm) cellInfo).getCellIdentity();
                phoneInfo.setCid(cellIdentityGsm.getCid());
                phoneInfo.setLac(cellIdentityGsm.getLac());
                phoneInfo.setRatType(TelephonyManager.NETWORK_TYPE_GSM);
                phoneInfo.setRsrp(((CellInfoGsm) cellInfo).getCellSignalStrength().getDbm());
                phoneInfo.setAsulevel(((CellInfoGsm) cellInfo).getCellSignalStrength().getAsuLevel());
                phoneInfo.setLevel(((CellInfoGsm) cellInfo).getCellSignalStrength().getLevel());

            } else if (cellInfo instanceof CellInfoWcdma) {
                CellIdentityWcdma cellIdentity = ((CellInfoWcdma) cellInfo).getCellIdentity();
                phoneInfo.setCid(cellIdentity.getCid());
                phoneInfo.setPsc(cellIdentity.getPsc());
                phoneInfo.setLac(cellIdentity.getLac());
                phoneInfo.setRsrp(((CellInfoWcdma) cellInfo).getCellSignalStrength().getDbm());
                phoneInfo.setAsulevel(((CellInfoWcdma) cellInfo).getCellSignalStrength().getAsuLevel());
                phoneInfo.setRatType(TelephonyManager.NETWORK_TYPE_UMTS);
                phoneInfo.setLevel(((CellInfoWcdma) cellInfo).getCellSignalStrength().getLevel());

            } else {
                if (cellInfo instanceof CellInfoCdma) {
                    phoneInfo.setRatType(TelephonyManager.NETWORK_TYPE_CDMA);
                    CellIdentityCdma cellIdentity = ((CellInfoCdma) cellInfo).getCellIdentity();
                    phoneInfo.setSid(cellIdentity.getSystemId());
                    phoneInfo.setNid(cellIdentity.getNetworkId());
                    phoneInfo.setBid(cellIdentity.getBasestationId());
                    GsmCellLocation cellLocation = (GsmCellLocation) getTelephonyManager().getCellLocation();
                    if (cellLocation != null) {
                        phoneInfo.setCid(cellLocation.getCid());
                        phoneInfo.setLac(cellLocation.getLac());
                        phoneInfo.setPsc(cellLocation.getPsc());

                    }
                    phoneInfo.setLevel(((CellInfoCdma) cellInfo).getCellSignalStrength().getLevel());


                }
            }
        }
        addGeneralInfo(phoneInfo);
        phoneInfo.setWifiSignalList(getWifiSignals());
        return phoneInfo;
    }


    private static void addGeneralInfo(MyPhoneInfo phoneInfo) {
        TelephonyManager phoneManager = getTelephonyManager();
        phoneInfo.setOperaterName(phoneManager.getNetworkOperatorName());
        phoneInfo.setOperaterId(phoneManager.getNetworkOperator());
        phoneInfo.setMnc(getMNC());
        phoneInfo.setMcc(getMCC());
        phoneInfo.setPhoneDatastate(phoneManager.getDataState());
        phoneInfo.setDeviceId(phoneManager.getDeviceId());
        phoneInfo.setImei(phoneManager.getDeviceId());
        phoneInfo.setImsi(getIMSI());
        phoneInfo.setSerialNumber(phoneManager.getSimSerialNumber());
        phoneInfo.setDeviceSoftwareVersion(android.os.Build.VERSION.RELEASE);
        if (phoneInfo.getLac() == 0) {
            phoneInfo.setLac(((GsmCellLocation) phoneManager.getCellLocation()).getLac());
        }
    }

    public static List<WifiSignalBean> getWifiSignals() {
        List<WifiSignalBean> wifiSignalBeanList = new ArrayList<>();
        WifiManager wifiManager = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> scanResultList = wifiManager.getScanResults();
        for (ScanResult scanResult : scanResultList) {
            WifiSignalBean wifiSignalBean = new WifiSignalBean();
            wifiSignalBean.setSsid(scanResult.SSID);
            wifiSignalBean.setBssid(scanResult.BSSID);
            wifiSignalBean.setRssi(WifiManager.calculateSignalLevel(scanResult.level, 100));
            wifiSignalBean.setLevel(scanResult.level);
            wifiSignalBeanList.add(wifiSignalBean);
        }
        return wifiSignalBeanList;
    }

    public static void getBeacons(final IBeaconRequest beaconRequest) {
        final List<BeaconInfo> beaconInfoList = new ArrayList<>();
        BluetoothManager bluetoothManager = (BluetoothManager) getContext().getSystemService(BLUETOOTH_SERVICE);//这里与标准蓝牙略有不同
        final BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        /*隐式打开蓝牙*/
        if (bluetoothAdapter != null && !bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.enable();
        }
        final BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {

            }
        };
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            // 5秒后停止扫描，毕竟扫描蓝牙设备比较费电，根据定位及时性自行调整该值
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (bluetoothAdapter != null) {
                        bluetoothAdapter.stopLeScan(leScanCallback);
                        if (beaconInfoList != null && beaconRequest != null) {
                            beaconRequest.onBeaconLoad(beaconInfoList);
                        }
                    }
                }
            }, 5 * 1000);
            bluetoothAdapter.startLeScan(leScanCallback);
        }
    }
}
