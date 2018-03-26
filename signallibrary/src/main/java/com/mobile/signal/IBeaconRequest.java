package com.mobile.signal;


import com.mobile.signal.beans.BeaconInfo;

import java.util.List;

/**
 * Created by gaoxuefeng on 2018/3/26.
 */

public interface IBeaconRequest {
    void onBeaconLoad(List<BeaconInfo> beaconInfoList);
}
