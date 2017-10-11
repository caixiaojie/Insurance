package com.clkj.insurance;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.clkj.insurance.activities.comForgetPassword.ActForgetPassword;
import com.clkj.insurance.activities.comRegister.ActRegister;
import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.base.BaseActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempModule.tempUtils.TempRegexUtil;
import com.lf.tempcore.tempResponse.ResponseLoginInfo;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 登录
 */
public class ActLogin extends BaseActivity {

    @Bind(R.id.act_login_password)
    EditText act_login_password;//密码
    @Bind(R.id.act_login_tel)
    EditText act_login_tel;//手机号
    private TempRegexUtil tempRegexUtil;

    private static final String TAG = ActLogin.class.getSimpleName();
    private double latitude = 0.0;
    private double longitude = 0.0;
    private LocationManager locationManager;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_login);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
            //gps已打开
        } else {
            toggleGPS();
            new Handler() {
            }.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getLocation();
                }
            }, 2000);

        }
    }

    @Override
    protected void findViews() {
        tempRegexUtil = new TempRegexUtil();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {

    }

    @Override
    @OnClick({R.id.toolbar_back, R.id.act_login_text, R.id.act_login_forget_password, R.id.act_login_register})
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back://返回键
                finish();
                break;
            case R.id.act_login_text://登录
                String tel = act_login_tel.getText().toString().trim();
                String password = act_login_password.getText().toString().trim();

                if (tel != null) {
                    if (!tempRegexUtil.checkMobile(tel)) {
                        showToast("请输入正确的电话号码");
                        return;
                    }
                } else {
                    showToast("请输入电话号码");
                    return;
                }
                login(tel, password);
                break;
            case R.id.act_login_forget_password://忘记密码
                startActivity(new Intent(this, ActForgetPassword.class));
                break;
            case R.id.act_login_register://立即注册
                startActivity(new Intent(this, ActRegister.class));
                break;
        }
    }

    /**
     * 登录
     */
    private void login(final String tel, final String password) {
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).appUserLogin(password, tel), new TempRemoteApiFactory.OnCallBack<ResponseLoginInfo>() {
            @Override
            public void onSucceed(ResponseLoginInfo data) {
                if (data.getType() == 1) {
                    TempLoginConfig.sf_saveLoginInfo(data);
                    TempLoginConfig.sf_savePassword(password);
                    TempLoginConfig.sf_saveLoginState(true);
                    TempLoginConfig.sf_savePhone(tel);
                    data.getResult().setLat(latitude+"");
                    data.getResult().setLng(longitude+"");
                    showToast(data.getMsg());
                    startActivity(new Intent(ActLogin.this, ActIndex.class));
                } else {
                    showToast(data.getMsg());
                }
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }
        });

        Log.e("==lat==",latitude+"");
        Log.e("==lng==",longitude+"");
    }

    @Override
    public void setToolbar(Toolbar toolbarTop) {
        ImageView toolbar_back = (ImageView) toolbarTop.findViewById(R.id.toolbar_back);
        toolbar_back.setImageResource(R.mipmap.dl_1);
        toolbarTop.findViewById(R.id.toolbar_title).setVisibility(View.GONE);
        toolbarTop.setBackgroundColor(Color.parseColor("#00000000"));
    }

    private void toggleGPS() {
        Intent gpsIntent = new Intent();
        gpsIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        gpsIntent.addCategory("android.intent.category.ALTERNATIVE");
        gpsIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(this, 0, gpsIntent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
            Location location1 = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location1 != null) {
                latitude = location1.getLatitude(); // 经度
                longitude = location1.getLongitude(); // 纬度
            }
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        } else {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
        }
//        info.setText("纬度：" + latitude + "\n" + "经度：" + longitude);
    }

    LocationListener locationListener = new LocationListener() {
        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {
            Log.e(TAG, provider);
        }

        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {
            Log.e(TAG, provider);
        }

        // 当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                Log.e("Map", "Location changed : Lat: " + location.getLatitude() + " Lng: " + location.getLongitude());
                latitude = location.getLatitude(); // 经度
                longitude = location.getLongitude(); // 纬度
            }
        }
    };



    // 打开和关闭gps第二种方法
    private void openGPSSettings() {
        //获取GPS现在的状态（打开或是关闭状态）
        boolean gpsEnabled = Settings.Secure.isLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER);
        if (gpsEnabled) {
            //关闭GPS
            Settings.Secure.setLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER, false);
        } else {
            //打开GPS
            Settings.Secure.setLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER, true);
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
