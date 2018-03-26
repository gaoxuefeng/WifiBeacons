package signal.mobile.com.wifibeacons;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mobile.signal.IBeaconRequest;
import com.mobile.signal.SignalInit;
import com.mobile.signal.beans.BeaconInfo;
import com.mobile.signal.beans.MyPhoneInfo;
import com.mobile.signal.beans.WifiSignalBean;
import com.mobile.signal.utils.NetUtil;

import java.util.List;

/**
 * @author gaoxuefeng
 */
public class MainActivity extends AppCompatActivity {
    String[] permissions = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.READ_PHONE_STATE
    };
    private TextView tvInfo;
    private Button btRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permissions, 500);
            }
        }
        tvInfo = (TextView) findViewById(R.id.tv_info);
        btRequest = (Button) findViewById(R.id.bt_request);
        tvInfo.setText("");
        SignalInit.init(getApplicationContext());
        btRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });

        requestData();
    }

    private void requestData() {
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
        List<WifiSignalBean> wifiSignalBeanList = NetUtil.getWifiSignals();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == 500) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限已放行;
            } else {
                //拒绝
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
