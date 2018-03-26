# WifiBeacons
这个demo是为了简单的获取周围蓝牙定位和wifi定位和基站定位所需要的基础数据
可以获取基站信息,蓝牙信息,WIFI信息
具体的调用方法如下:

//为了避免每次都要传递context,在初始化是设置context

        SignalInit.init(getApplicationContext());

 //具体的使用方法如以下调用

        try {
            MyPhoneInfo myPhoneInfo = NetUtil.getPhoneInfo();
            tvInfo.setText(myPhoneInfo.toString());
            NetUtil.getBeacons(new IBeaconRequest() {
                @Override
                public void onBeaconLoad(List<BeaconInfo> beaconInfoList) {
                    tvInfo.append("\n\n" + beaconInfoList.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            tvInfo.setText(e.getMessage());
        }
  //如果单独需要WiFi信息也可以这么调
  List<WifiSignalBean> wifiSignalBeanList = NetUtil.getWifiSignals();
