/********************** 版权声明 *************************
 * 文件名: F99_ConvertToUnicodeNotation.java
 * 包名: font
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月6日 下午5:35:58
 * 文件版本：V1.0 
 *
 *******************************************************/
package font;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class F99_ConvertToUnicodeNotation {
    public static void main(String[] args) {
        String s = "Vous êtes d'où?";
        System.out.print("\"");
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > 31 && c < 127)
                System.out.print(String.valueOf(c));
            else
                System.out.print(String.format("\\u%04x", (int)c));
        }
        System.out.println("\"");
    }
 
}
