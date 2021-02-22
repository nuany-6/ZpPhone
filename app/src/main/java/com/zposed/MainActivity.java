package com.zposed;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import zpp.wjy.zposed.callbacks.XC_LoadPackage;

public class MainActivity extends AppCompatActivity {
     XC_LoadPackage.LoadPackageParam lp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView lblTitle=(TextView)findViewById(R.id.text);
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        TelephonyManager tm = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        lblTitle.setMovementMethod(ScrollingMovementMethod.getInstance());
        lblTitle.setText("ANDROID_ID="+Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID)+"\n Build.BOARD="+Build.BOARD+"     获取设备基板名称\n"+"Build.BOOTLOADER="+Build.BOOTLOADER+"     获取设备引导程序版本号\n"+"Build.BRAND="+Build.BRAND+"     获取设备品牌\n"+"Build.CPU_ABI="+Build.CPU_ABI+"     获取设备指令集名称（CPU的类型）\n"+"Build.CPU_ABI2="+Build.CPU_ABI2+"     获取第二个指令集名称\n"+"Build.DEVICE="+Build.DEVICE+"     获取设备驱动名称\n"+"Build.DISPLAY="+Build.DISPLAY+"     获取设备显示的版本包（在系统设置中显示为版本号）和ID一样\n"+"Build.FINGERPRINT="+Build.FINGERPRINT+"     设备的唯一标识。由设备的多个信息拼接合成。\n"+"Build.HARDWARE="+Build.HARDWARE+"     设备硬件名称,一般和基板名称一样（BOARD）\n"+"Build.HOST="+Build.HOST+"     设备主机地址\n"+"Build.ID="+Build.ID+"     设备版本号。\n"+"Build.MODEL="+Build.MODEL+"     获取手机的型号 设备名称。\n"+"Build.MANUFACTURER="+Build.MANUFACTURER+"     获取设备制造商\n"+"Build.PRODUCT="+Build.PRODUCT+"     整个产品的名称\n"+"Build.RADIO="+Build.RADIO+"     无线电固件版本号，通常是不可用的 显示unknown\n"+"Build.TAGS="+Build.TAGS+"     设备标签。如release-keys 或测试的 test-keys \n"+"Build.TIME="+Build.TIME+"     时间\n"+"Build.TYPE="+Build.TYPE+"     设备版本类型  主要为user或eng\n"+"Build.USER="+Build.USER+"     设备用户名 基本上都为android-build\n"+"Build.VERSION.CODENAME="+Build.VERSION.CODENAME+"     设备当前的系统开发代号，一般使用REL代替\n"+"Build.VERSION.RELEASE="+Build.VERSION.RELEASE+"     获取系统版本字符串。如4.1.2 或2.2 或2.3等\n"+"Build.VERSION.INCREMENTAL="+Build.VERSION.INCREMENTAL+"     系统源代码控制值，一个数字或者git hash值\n"+"Build.VERSION.SDK="+Build.VERSION.SDK+"       系统的API级别 一般使用下面大的SDK_INT 来查看\n"+"Build.VERSION.SDK_INT="+Build.VERSION.SDK_INT+"    系统的API级别 数字表示\n"+"Build.SERIAL="+Build.SERIAL+"     硬件序列号（如果可用）。\n"+"Build.SUPPORTED_32_BIT_ABIS="+Build.SUPPORTED_32_BIT_ABIS+"     此设备支持的 32位 ABI的有序列表。\n"+"Build.SUPPORTED_64_BIT_ABIS="+Build.SUPPORTED_64_BIT_ABIS+"     此设备支持的 64位 ABI的有序列表。\n"+""+Build.SUPPORTED_ABIS+"     SUPPORTED_ABIS\n"+"Build.TAGS="+Build.TAGS+"     \n"+"wifi="+wifiInfo.toString()+"\n"+"wifiBSSID="+wifiInfo.getBSSID()+"\n"+"wifiMAC="+wifiInfo.getMacAddress()+"\n"+"wifiSSID="+wifiInfo.getSSID()+"\n"+"wifi链接速度="+wifiInfo.getLinkSpeed()+"\n"+"wifiNetworkId="+wifiInfo.getNetworkId()+"\n"+"wifiIpAddress="+wifiInfo.getIpAddress()+"\n"+"网络类型="+ ConnectivityManager.TYPE_WIFI+"\n"+"网络运营 MCC + MNC="+tm.getNetworkOperator()+"\n"+"返回SIM卡 MCC+MNC="+tm.getSimOperator()+"\n"+"ICCID="+tm.getSimSerialNumber()+"\n"+"手机卡状态="+tm.getSimState()+"\n"+"用户id="+tm.getSubscriberId());

    }
    public void aa(View view){
       // Toast.makeText(getApplicationContext(), "点击按钮了", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent("mayun");
        sendBroadcast(intent);
    }




}
