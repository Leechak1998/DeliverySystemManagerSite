package com.example.deliverysystemmanagersite.util;

import android.os.Handler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class SendDataToServer {
    private static final int TIME_OUT = 10000; // 超时时间.
    // 连接服务器的url.
    private static final String URL = "http://192.168.1.101:8080/ReceiveAndroid/ServletForGETMethod";
    // 标识是否连接到服务器成功.
    public static final int SEND_SUCCESS = 1;
    public static final int SEND_FAIL = 0;

    private Handler handler = null;

    public SendDataToServer(Handler handler) {
        this.handler = handler;
    }

    /**
     * 往服务器发送数据.
     *
     * @param name
     * @param pwd
     */
    public void send(String name, String pwd) {
        // 这里params要传递到另外一个方法,加final为了防止被修改.
        final Map<String, String> params = new HashMap<String, String>();
        params.put("name", name);
        params.put("pwd", pwd);

        // 启动新的线程连接服务器.
        new Thread(new Runnable() {

            @Override
            public void run() {
                // 使用get请求连接.
                try {
                    if (getSend(params, URL, "utf-8"))
                        handler.sendEmptyMessage(SEND_SUCCESS);
                    else
                        handler.sendEmptyMessage(SEND_FAIL);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**发送get请求的方法.
     * @param params 请求参数的键-值对.
     * @param url
     * @param encoding 使用指定编码对参数值进行编码.
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    private boolean getSend(Map<String, String> params, String url,
                            String encoding) throws MalformedURLException, IOException {
        StringBuilder sb = new StringBuilder();
        // 向url中添加参数.
        sb.append(url).append("?");
        for (Map.Entry<String, String> param : params.entrySet()) {
            sb.append(param.getKey()).append("=")
                    .append(URLEncoder.encode(param.getValue(), encoding))
                    .append("&");
        }
        if (params.size() > 0)
            sb.deleteCharAt(sb.length() - 1); // 去掉末尾的'&'.
        HttpURLConnection conn = (HttpURLConnection) new URL(sb.toString())
                .openConnection();
        conn.setConnectTimeout(TIME_OUT);
        conn.setRequestMethod("GET"); // GET是大小写敏感的.

        return conn.getResponseCode() == 200; // 等于200表示发送成功.
    }
}