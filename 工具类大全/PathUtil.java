package com.iqurong.new51.app.util;

import org.apache.log4j.Logger;

import java.io.File;

/**
 * 文件目录 工具类
 * @author liangz
 * @date 2018/1/2 15:16
 **/
public class PathUtil {

    static Logger logger = Logger.getLogger(PathUtil.class);

    /**
     * 创建文件目录
     * @param path
     * @return
     */
    public static boolean createDir(String path){
        if(path!=null&&!"".equals(path)){
            File file = new File(path);
            if(!file.exists()){
                //目录不存在创建目录
                if(file.mkdirs()){
                    logger.info("目录路径："+path+"创建成功");
                    return true;
                }else{
                    logger.error("创建路径出错");
                    return  false;
                }
            }else{
                logger.error("目录已存在");

            }
        }else{
            logger.error("无效的路径");
            return  false;
        }

        return false;
    }

    public static void main(String[] args) {
       System.out.println( createDir("D:\\daaada\\jiji\\sasa\\sa"));
    }
}
