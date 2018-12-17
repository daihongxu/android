/*

从服务端获取数据  调用sendOkHttpRequest（）传入请求地址  返回JSON数组数据  JSON为一个数组长这样{"id":1,"name":"北京"}

 */
package com.coolweather.android.util;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {//从服务端获取数据，  调用sendOkHttpRequest（），传入请求地址  返回JSON数组数据  JSON为一个数组长这样{"id":1,"name":"北京"}

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

}
