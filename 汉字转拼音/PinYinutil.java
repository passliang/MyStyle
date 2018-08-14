package com.zgqb.loan.app.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author liangz
 * 拼音工具类
 */
public class PinYinutil {

    /**
     * 获取字符串拼音的第一个字母
     * @param chinese
     * @return
     */
     public static String ToFirstChar(String chinese){

         String pinyinStr = "";
         //转为单个字符
         char[] newChar = chinese.toCharArray();
         HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
         defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
         defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
         for (int i = 0; i < newChar.length; i++) {
                 if (newChar[i] > 128) {
                         try {
                                 pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0].charAt(0);
                             } catch (BadHanyuPinyinOutputFormatCombination e) {
                             }
                     }else{
                         pinyinStr += newChar[i];
                     }
             }
         return pinyinStr;
     }
     /**
       * 汉字转为拼音
       * @param chinese
       * @return
       */
     public static String ToPinyin(String chinese){
         String pinyinStr = "";
         char[] newChar = chinese.toCharArray();
         HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
         defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
         defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
         for (int i = 0; i < newChar.length; i++) {
                 if (newChar[i] > 128) {
                         try {
                                 pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0];
                             } catch (BadHanyuPinyinOutputFormatCombination e) {
                                 e.printStackTrace();
                             }
                     }else{
                         pinyinStr += newChar[i];
                     }
             }
         return pinyinStr;
     }
}
