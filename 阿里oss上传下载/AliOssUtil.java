package com.zgqb.loan.app.util;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.zgqb.loan.app.AppServiceApplication;
import com.zgqb.loan.app.properties.AppProperties;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * oss上传工具类 文件上传和下载
 * @author liangz
 */
@Slf4j
@Component
public class AliOssUtil  implements ApplicationContextAware{

    /**
     * pdf 后缀
     */
    private static final String PDF_SUFFIX =".pdf";

	private static ApplicationContext applicationContext;

	private  static  AppProperties appProperties;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
        appProperties = applicationContext.getBean(AppProperties.class);
	}

	/**
     * oss 文件下载
     * @param fileName 下载文件名
     * @param downloadSaveFilePath
     * @return
     */
	public   boolean downloadOssFile( String fileName,String downloadSaveFilePath) {

	    //oss 配置信息
        String endpoint = appProperties.getAliOssConfig().getEndpoint();
		String accessKeyId = appProperties.getAliOssConfig().getAccessKeyId();
		String accessKeySecret = appProperties.getAliOssConfig().getAccessKeySecret();
		String bucketName = appProperties.getAliOssConfig().getBucketName();

        // 创建OSSClient实例
		OSSClient ossClient = null;
		InputStream in = null;
		OutputStream out = null;
		try {
			ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			OSSObject ossObject = ossClient.getObject(bucketName, fileName);
			// 读Object内容
			in = ossObject.getObjectContent();
		    int len = 0;
            //缓存字节数据
		    byte [] buf = new byte[1024];
		    //将文件写入到传入位置
			out = new FileOutputStream(new File(downloadSaveFilePath));
			//切忌这后面不能加 分号 ”;“
			while( (len = in.read(buf)) > 0 ) {
		    	out.write(buf, 0, len);
		    }
		} catch (IOException e) {
            log.error("==========异常信息==========="+e.getMessage());
			e.printStackTrace();
			return false;
		}finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
			    log.error("==========io关闭异常===========");
				e.printStackTrace();
			}
			if (ossClient != null) {
			   ossClient.shutdown();
			}
		}
		return true;
	}

	/**
	 * 获取oss 输入流
	 * @param fileName
	 * @return
	 */
	public static InputStream  getOssInputStream (String fileName){
		String endpoint = "oss-cn-hangzhou.aliyuncs.com";

		String accessKeyId = "LTAIt3oUmDrHCliy";

		String accessKeySecret = "YVtrFTYHM6QMTPZ04jjVpnopXRnFWY";

		String bucketName = "qujieqian";

		// 创建OSSClient实例
		OSSClient  ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		OSSObject ossObject = ossClient.getObject(bucketName, fileName);
		// 读Object内容
		InputStream in = ossObject.getObjectContent();
		return in;
	}
    /**
     * oss 文件上传
     * @param fileName 上传文件 如果需要路径则需加上  例如 文件名为EsignCompact/test.pdf  test.pdf文件会存在于EsignCompact目录下
     * @param srcFilePath  "pdf" + File.separator + "test.pdf"
     *
     */
	public  static  String  upFileToOss(String fileName,String srcFilePath) {
		try {

            /*String endpoint = "oss-cn-hangzhou.aliyuncs.com";

            String accessKeyId = "LTAIt3oUmDrHCliy";

            String accessKeySecret = "YVtrFTYHM6QMTPZ04jjVpnopXRnFWY";

            String bucketName = "qujieqian";
            String key = "EsignCompact/";*/

            String endpoint = appProperties.getAliOssConfig().getEndpoint();
			String accessKeyId = appProperties.getAliOssConfig().getAccessKeyId();
			String accessKeySecret = appProperties.getAliOssConfig().getAccessKeySecret();
			String bucketName = appProperties.getAliOssConfig().getBucketName();
            //根目录
			String key = appProperties.getAliOssConfig().getKey();

			// 创建OSSClient实例
			OSSClient ossClient = new OSSClient(endpoint,accessKeyId, accessKeySecret);

			if(!fileName.contains(PDF_SUFFIX)){
			    //文件名不包含后缀名
                fileName+=".pdf";
            }
			//文件上传
			ossClient.putObject(bucketName, key + fileName, new File(srcFilePath));
			// 关闭client
			ossClient.shutdown();
			return  key+fileName;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("================上传出错================-");
		}
		return "";
	}

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        //System.out.print("data:"+date);
        //upFileToOss(date+"/test.pdf","pdf" + File.separator + "test.pdf");
        //downloadOssFile("EsignCompact/test.pdf");

        String fileName = "EsignCompact/test.pdf";
        int index = fileName.lastIndexOf("/");
        System.out.print("index:"+index);
        String finalFileName = fileName.substring(index).replaceAll("/","");
        System.out.print("fin:"+finalFileName);

        //测试信息
        //  String endpoint ="oss-cn-hangzhou.aliyuncs.com";
//			String accessKeyId = "LTAIt3oUmDrHCliy";
//			String accessKeySecret = "YVtrFTYHM6QMTPZ04jjVpnopXRnFWY";
//			String bucketName = "qujieqian";
//			String key = "EsignCompact/";
    }


}
