package com.iqurong.qjq.cms.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author TJP
 * @date 2018/2/8 13:51
 * http请求工具类
 **/
@Slf4j
public class HttpUtils {
	
	/**
     * 设置常用请求头
     * @param connection 连接实体
     * @return 连接实体
     */
    private static URLConnection setDefaultHeaders(URLConnection connection) {
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        return connection;
    }

    /**
     * 发送POST请求
     *
     * @param url   目标URL
     * @param param 参数，格式：name1=value1&name2=value2
     * @return 响应结果
     */
    public static String sendPost(String url, String param) throws IOException {
        StringBuilder result = new StringBuilder();
        URL realUrl = new URL(url);
        // 打开URL连接
        URLConnection conn = realUrl.openConnection();
        // 设置请求头
        conn = setDefaultHeaders(conn);
        // 判断是否为JSON数据格式
        if (JSONObject.parse(param) != null) {
            conn.setRequestProperty("Content-Type", "application/json");
        }

        // POST请求必须设置
        conn.setDoOutput(true);
        conn.setDoInput(true);
        try(PrintWriter out = new PrintWriter(conn.getOutputStream())) {
            // 设置请求体
            out.print(param);
            // 发送请求体
            out.flush();
            // 设置输入流读取响应
            try(BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
                String line;
                while ((line = in.readLine()) != null){
                    result.append(line);
                }
            }
        }
        return result.toString();
    }
    /**
     * 发送post请求
     * @param url 请求地址
     * @param parameters 请求参数
     * @return 返回结果
     */
    public static String sendPost(String url, Map<String, String> parameters) {
        // 返回的结果
        StringBuilder result = new StringBuilder();
        // 读取响应输入流
        BufferedReader in = null;
        PrintWriter out = null;
        // 处理请求参数
        StringBuilder sb = new StringBuilder();
        // 编码之后的参数
        String params;
        try {
            // 编码请求参数
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name),"UTF-8")).append("&");
                }
                String tempParams = sb.toString();
                params = tempParams.substring(0, tempParams.length() - 1);
            }
            // 创建URL对象
            java.net.URL connURL = new java.net.URL(url);
            // 打开URL连接
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 设置POST方式
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
}
