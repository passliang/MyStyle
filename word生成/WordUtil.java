package com.zgqb.loan.app.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 根据模板生成word 文件
 * @author liangz
 *
 */
public class WordUtil {

    /**
     * 模板文件配置对象
     *
     */
    private Configuration configuration = null;

    /**
     * 模板配置文件夹
     *
     */
    private String configPath ="templates"+File.separator;

    /**
     * 文件分割符
     */
    static String  fileSep =File.separator;



    public WordUtil() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
    }

    /**
     * 生成word
     * @param dir
     * @param fileName
     * @param savePath
     * @param sDate
     */
    public  void createDoc(String dir, String fileName, String savePath, Map<String, Object> sDate) {

        Template t = null;
        try {
            // 取模板的路径
            configuration.setDirectoryForTemplateLoading(new File(dir));
            configuration.setEncoding(Locale.getDefault(), "utf-8");
            // 设置异常处理
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
            // 设置木板的路径文件
            t = configuration.getTemplate(fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
        // 输出文档路径及名称
        File outFile = new File(savePath);
        Writer out = null;
        try {
            //设置编码格式
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "utf-8"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            //套用模板，替换变量
            t.process(sDate, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param savePath
     * @param sDate
     */
    public  void createDoc( String savePath, Map<String, Object> sDate) {

        Template t = null;
        try {
            // 取模板的路径
            configuration.setDirectoryForTemplateLoading(new File(configPath));
            configuration.setEncoding(Locale.getDefault(), "utf-8");
            // 设置异常处理
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
            // 设置木板的路径文件
            t = configuration.getTemplate("finalAgreement.ftl");

        } catch (IOException e) {
            e.printStackTrace();
        }
        // 输出文档路径及名称
        File outFile = new File(savePath);
        Writer out = null;
        try {
            //设置编码格式
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "utf-8"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            //套用模板，替换变量
            t.process(sDate, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


   public static void main(String[] args) {
        WordUtil dh = new WordUtil();
        Map<String, Object> data = new HashMap<>();
        //编号
        data.put("serialNumber","2017122100000001");
        data.put("year","2017");
        data.put("month","12");
        data.put("day","28");
        //出借人
        data.put("lender","趣融信息科技有限公司");
        //出借人身份证
        data.put("lenderIdNo","112432198009083214");
        //借款人
        data.put("borrower","小里");
        //借款人身份证
        data.put("borrowerCardId","112432198009083214");
        //借款人常驻地址
        data.put("borrowerAddress","杭州市小黄村");
        //借款人手机号
        data.put("borrowerPhone","15612426785");
        //借款人邮箱
        data.put("borrowerEmail","1726161@163.com");
        //借款金额
        data.put("borrowMoney","1000");
        data.put("startDate","20171228");
        data.put("endDate","20180102");
        //共计多长时间
        data.put("allDay","5");
        //年利率
        data.put("yearRate","31.85%");
        //借款用途
        data.put("purpose","买买买");
        //期数
        data.put("periods","3");
        //每期还款
        data.put("periodMoney","366");
        //还款日期 每期
        data.put("repaymentDate","20171228,20171229,20171230");
        //借款人
        data.put("borrowerName","小里");
        //借款人银行
        data.put("borrowerBankName","杭州银行");
        //借款人银行
        data.put("borrowerBankCard","6222031202001400208");
        //签署日期
        data.put("signeDate","20171228");
        data.put("test","呵呵哒");

       List<String>lists = new ArrayList<>();
       lists.add("20171201,");
       lists.add("20180101,");
       lists.add("20180201,");
       lists.add("20180301,");
       lists.add("20180401,");
       lists.add("20180501,");
       lists.add("20180301,");
       lists.add("20180401,");
       lists.add("20180501,");
       lists.add("20180301,");
       lists.add("20180401,");
       lists.add("20180501");

       data.put("first","20171220,");
       data.put("second","20180118,20180218,");
       data.put("third","20180318,");
       data.put("four","20180418");
       data.put("five","20180518");
       data.put("six","20171220,20180118,20180218,20180318,");
       data.put("seven","20180418");
       data.put("eight",",20180518");
       data.put("lists",lists);
        dh.createDoc("templates/", "finalAgreement.ftl", "D://协议12期.doc", data);

    }



    /**
     * 获取doc 绝对路径  目录不存在创建
     *
     * @param signFolderPrefix
     * @param name
     * @return
     *
     */
    public static  String getDocPath(String signFolderPrefix,String name ){
        //当前日期创建文件夹
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        //名字转化位拼音
        String cnName = PinYinutil.ToPinyin(name);
        //doc生成目录
        String wordSignedFolder =signFolderPrefix+fileSep+"EsignWord"+fileSep +date+fileSep+cnName;
        //创建目录
        PathUtil.createDir(wordSignedFolder);
        //doc 生成的文件名
        String srcDocFileName = File.separator+cnName+"签署协议.doc";
        return wordSignedFolder+srcDocFileName;
    }

    public static Map<String,Object> getSerialDate(){
        Map<String,Object>data = new HashMap<>();
        data.put("test","测试一");
        ////${first}20171220,  ${second}20180118,20180218,  ${third}20180318, ${four}20180418  ${five}20180518
        /*List<String>lists = new ArrayList<>();
        lists.add("20171201");
        lists.add("20180101");
        lists.add("20180201");
        lists.add("20180301");
        lists.add("20180401");
        lists.add("20180501");
        data.put("first","20171220,".trim());
        data.put("second","20180118,20180218,".trim());
        data.put("third","20180318,".trim());
        data.put("four","20180418");
        data.put("five","20180518");
        data.put("lists",lists);*/


        ////9期处理格式${first}20171220,  ${second}20180118,20180218, ${third}20180318,  ${four}20180418  ${five}20180518
        //${six},20180618,20171220,20180118
      /*  List<String>lists = new ArrayList<>();
        lists.add("20171201");
        lists.add("20180101");
        lists.add("20180201");
        lists.add("20180301");
        lists.add("20180401");
        lists.add("20180501");
        lists.add("20180301");
        lists.add("20180401");
        lists.add("20180501");

        data.put("first","20171220,");
        data.put("second","20180118,20180218,");
        data.put("third","20180318,");
        data.put("four","20180418");
        data.put("five","20180518");
        data.put("six",",20180618,20171220,20180118");
        data.put("lists",lists);
*/
        //12期处理格式  ${first}20171220,  ${second}20180118,20180218,  ${third}20180318, ${four}20180418  ${five}20180518
        //${six}20171220,20180118,20180218,20180318,  ${seven}20180418  ${eight},20180518

        List<String>lists = new ArrayList<>();
        lists.add("20171201,");
        lists.add("20180101,");
        lists.add("20180201,");
        lists.add("20180301,");
        lists.add("20180401,");
        lists.add("20180501,");
        lists.add("20180301,");
        lists.add("20180401,");
        lists.add("20180501,");
        lists.add("20180301,");
        lists.add("20180401,");
        lists.add("20180501");

        data.put("first","20171220,");
        data.put("second","20180118,20180218,");
        data.put("third","20180318,");
        data.put("four","20180418");
        data.put("five","20180518");
        data.put("six","20171220,20180118,20180218,20180318,");
        data.put("seven","20180418");
        data.put("eight",",20180518");
        data.put("lists",lists);
        return  data;
    }

    /**
     * long 转 字符串日期
     * @param time
     * @return
     */
    public static  String getDate(Long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date(time));
    }
}
