package com.zposed;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.os.BatteryManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.zposed.util.AndroidUtil;
import java.util.List;

import zpp.wjy.zposed.IZposedHookLoadPackage;
import zpp.wjy.zposed.XC_MethodHook;
import zpp.wjy.zposed.ZposedBridge;
import zpp.wjy.zposed.ZposedHelpers;
import zpp.wjy.zposed.callbacks.XC_LoadPackage;

public class ZpHook  implements IZposedHookLoadPackage {
   public static XC_LoadPackage.LoadPackageParam  lp;
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        //this.lp=loadPackageParam;
        //this.hookTest(loadPackageParam);
        ZposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", loadPackageParam.classLoader, "getInstalledPackages", int.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                //自定义一个新的List用来接收原来的返回信息
                List<PackageInfo> packageInfos=(List) param.getResult();

                //开始遍历查找
                for (PackageInfo packageInfo:packageInfos){
                    //匹配信息为你想要修改的某个App标签识别码
//                    if (packageInfo.applicationInfo.labelRes==2131230747){
//                        //找到了，修改该App的应用包名
//                        packageInfo.packageName="com.test.testing";
//                    }
                    ZposedBridge.log(packageInfo.packageName);

                }

                //把修改后的List当作结果返回去
                //param.setResult(packageInfos);
            }
        });
        ZposedBridge.log("loadpackage name : "+ loadPackageParam.packageName);
        ZposedHelpers.setStaticObjectField(android.os.Build.class,"MANUFACTURER","leon");//厂商
        ZposedHelpers.setStaticObjectField(android.os.Build.class,"MODEL","made in leon");//机型
        String s = new AndroidUtil().DidRandom();
        ZposedBridge.log(s);
        ZposedHelpers.setStaticObjectField(android.os.Build.class,"SERIAL",s);//序列号
        ZposedHelpers.setStaticObjectField(android.os.Build.class,"BOARD","HUAWEI");//设备主板名称
        ZposedHelpers.setStaticObjectField(android.os.Build.class,"DEVICE","achen");//基板名称
        ZposedHelpers.setStaticObjectField(android.os.Build.class,"HARDWARE","achen");//设备硬件名称
        ZposedHelpers.setStaticObjectField(android.os.Build.class,"PRODUCT","HUAWEI");//设备产品名称
        ZposedHelpers.setStaticObjectField(android.os.Build.class,"USER","HUAWEI");//设备用户名
        ZposedHelpers.setStaticObjectField(android.os.Build.class,"FINGERPRINT","");//指纹识别
        ZposedHelpers.setStaticObjectField(android.os.Build.VERSION.class,"RELEASE","6.0");//系统版本号
        //ZposedHelpers.setStaticObjectField(android.os.Build.VERSION.class,"SDK_INT","23");//系统SDK
        //ZposedHelpers.setStaticObjectField(android.os.Build.VERSION.class,"SDK","23");//系统SDK

        Class<?> aClass = ZposedHelpers.findClass("android.provider.Settings.System",  loadPackageParam.classLoader);
        //imei
        ZposedHelpers.findAndHookMethod(TelephonyManager.class.getName(), loadPackageParam.classLoader, "getDeviceId", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                //IMEI
                String s = new AndroidUtil().DidRandom();
                Log.d("===",s);
                param.setResult(s );
            }
        });
        //Android ID

        ZposedHelpers.findAndHookMethod("android.provider.Settings.Secure", loadPackageParam.classLoader, "getString", ContentResolver.class, String.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                String s = new AndroidUtil().DidRandom();
                Log.d("===",s);
                param.setResult(s );
            }
        });
        /*
         * wifi相关
         *
         * */
        // SSID
        ZposedHelpers.findAndHookMethod("android.net.wifi.WifiInfo", loadPackageParam.classLoader, "getSSID", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                //param.setResult("HUAWEI25b36ae4fa628771");  // wifi 名字
            }
        });
        //MAC
        ZposedHelpers.findAndHookMethod("android.net.wifi.WifiInfo", loadPackageParam.classLoader, "getMacAddress", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                super.afterHookedMethod(param);
                String mac = new AndroidUtil().getMac();
                param.setResult(mac);
            }
        });
        //BSSID
        ZposedHelpers.findAndHookMethod("android.net.wifi.WifiInfo", loadPackageParam.classLoader, "getBSSID", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                String mac = new AndroidUtil().getMac();
                param.setResult(mac);

            }
        });
        /*
         * 电池相关
         *
         * */
        ZposedHelpers.findAndHookMethod("android.content.Intent", loadPackageParam.classLoader, "getIntExtra", String.class, int.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Intent intent = (Intent) param.thisObject;
                        final String action = intent.getAction();

                        if (BatteryManager.EXTRA_LEVEL.equals(param.args[0] + "")) {
                            //电池电量
                            param.setResult(100);
                        } else if ("status".equals(param.args[0] + "")) {
                            //BATTERY_STATUS_NOT_CHARGING   电池状态不冲电

                            param.setResult(BatteryManager.BATTERY_STATUS_CHARGING);
                        }

                    }

                }
        );
        /*
         * 经纬度
         * */
        ZposedHelpers.findAndHookMethod(Location.class, "getLongitude", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {


                String s = new AndroidUtil().randomLon(73.33, 135.05);
                Log.d("===",s);
                param.setResult(s);
            }
        });
        ZposedHelpers.findAndHookMethod(Location.class,  "getLatitude", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                String s = new AndroidUtil().randomLat(3.51, 53.33);
                Log.d("===",s);
                param.setResult(s);
            }
        });
        /*
         * 手机sim卡相关
         *
         * */
        //修改手机号
        ZposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", loadPackageParam.classLoader, "getLine1Number", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult("15888888888");
            }
        });
        //IMSI
        ZposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", loadPackageParam.classLoader, "getSubscriberId", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                //param.setResult("6666666666666666666");
            }
        });
        //iccid
        ZposedHelpers.findAndHookMethod("android.telephony.Tele" +
                "phonyManager", loadPackageParam.classLoader, "getSimSerialNumber", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);

                param.setResult("666666666");  //手机卡iccid   唯一标识

            }
        });
        //sim 卡设备状态
        ZposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", loadPackageParam.classLoader, "getSimState", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);

                //param.setResult("666666666");  //sim 卡设备状态


            }
        });
        ZposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", loadPackageParam.classLoader, "getSimOperator", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);

                param.setResult("4G");//MCC   MNC
            }
        });
        /*
        * 网络类型
        *
        * */
//        ZposedHelpers.findAndHookMethod("android.net.NetworkInfo", loadPackageParam.classLoader, "getType", new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                super.afterHookedMethod(param);
//
//                param.setResult("4G");
//            }
//        });
        /*
        * 屏幕
        *

        ZposedHelpers.findAndHookMethod("android.view.Display", loadPackageParam.classLoader, "getHeight", new XC_MethodHook() {
            @Override
            *
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);

                param.setResult("1920");//分辨率高
            }
        });
        ZposedHelpers.findAndHookMethod("android.view.Display", loadPackageParam.classLoader, "getWidth", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);

                param.setResult("1080");//分辨率宽
            }
        });*/
    }


}
