package com.ceam.common.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ceam.common.constants.UrlConstants;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author CeaM
 * 2023/01/19 19:30
 **/
public class StringUtils extends org.apache.commons.lang3.StringUtils {


    private static final char SEPARATOR = '_';

    private static final String UNKNOWN = "unknown";

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 获取ip地址
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String comma = ",";
        String localhost = "127.0.0.1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return ip;
    }

    /**
     * 根据ip获取详细地址
     */
    public static String getCityInfo(String ip) {
        String api = String.format(UrlConstants.Url.IP_URL, ip);
        JSONObject object = JSONUtil.parseObj(HttpUtil.get(api));
        return object.get("addr", String.class);
    }

    public static String getBrowser(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }

    /**
     * 获得当天是周几
     */
    public static String getWeekDay() {
        String[] weekDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * Description: 格式化字符串(用户组建表使用) <br>
     *
     * @param name
     * @return String
     */
    public static String formatString(String name) {
        String s = "0000000000" + name;
        return s.substring(s.length() - 10, s.length());
    }

    /**
     * Description:把GBK转码成ISO8859_1 <br>
     *
     * @param name
     * @return String
     */
    public static String getStringByISO(String name) {
        try {
            // 防止中文字符出现乱码
            name = new String(name.getBytes("GBK"), "ISO8859_1");
        } catch (UnsupportedEncodingException e2) {
        }

        return name;
    }

    /**
     * Description:获得格式化的url <br>
     *
     * @param url
     * @return String
     */
    public static String getFormatUrl(String url) {
        if (isNullOrNone(url)) {
            return "";
        }

        if (url.indexOf('?') == -1) {
            return url + '?';
        } else {
            return url + '&';
        }
    }

    /**
     * Description: 获得字符(byte)的实际长度<br>
     * 1、…<br>
     * 2、…<br>
     * 3、…<br>
     * 4、…<br>
     *
     * @param s
     * @return int
     * @exception/throws
     */
    public static int lengthByte(String s) {
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) <= 127) {
                length++;
            } else {
                length = length + 2;
            }
        }
        return length;
    }

    /**
     * Description: 在jdbc用thin客户端连接oracle里面中文算3个字符<br>
     *
     * @param s
     * @return int
     * @exception/throws
     */
    public static int lengthByteInPrepared(String s) {
        int length = 0;

        if (s == null) {
            return length;
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) <= 127) {
                length++;
            } else {
                length = length + 3;
            }
        }
        return length;
    }

    /**
     * Description:判断字段空null <br>
     *
     * @param s
     * @return boolean
     */
    public static boolean isNullOrNone(String s) {
        if (s == null || "".equals(s.trim())) {
            return true;
        }

        return false;
    }

    /**
     * Description: 判断对象是否为空<br>
     *
     * @param obj
     * @return boolean
     * @exception/throws [违例类型] [违例说明]
     */
    public static boolean isNullOrNone(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            return isNullOrNone((String) obj);
        }
        return false;
    }

    /**
     * Description:判断字段空null <br>
     *
     * @param ss
     * @return boolean
     */
    public static boolean isNullOrNone(String[] ss) {
        if (ss == null || ss.length == 0) {
            return true;
        }

        for (int i = 0; i < ss.length; i++) {
            if (ss[i] == null || "".equals(ss[i].trim())) {
                return true;
            }
        }

        return false;
    }

    public static int countChar(String src, char c) {
        if (isNullOrNone(src)) {
            return 0;
        }

        int k = 0;
        for (int i = 0; i < src.length(); i++) {
            if (src.charAt(i) == c) {
                k++;
            }
        }

        return k;
    }

    /**
     * 把'变成 ''
     *
     * @param src
     * @return
     */
    public static String oracleString(String src) {
        if (isNullOrNone(src)) {
            return "";
        }

        return src.replaceAll("'", "''");
    }

    /**
     * 替换函数
     *
     * @param source
     * @param find
     * @param rep
     * @return String
     */
    public static String replace(String source, String find, String rep) {
        String result = source;
        String begin = "";
        String end = "";
        int flag;

        flag = result.indexOf(find, 0);

        while (flag != -1) {
            begin = result.substring(0, flag);
            end = result.substring(flag + (find.length()), result.length());
            result = begin + rep + end;
            flag = result.indexOf(find, flag + rep.length());
        }
        return result;
    }

    /**
     * 保护截断
     *
     * @param s
     * @param begin
     * @param end
     * @return
     */
    public static String truncate(String s, int begin, int end) {
        if (isNullOrNone(s)) {
            return "";
        }

        if (begin < 0) {
            return "";
        }

        if (begin >= end) {
            return "";
        }

        s = s.trim();
        if (begin >= s.length()) {
            return "";
        }

        if (end >= s.length()) {
            return s;
        } else {
            return s.substring(begin, end);
        }
    }

    /**
     * Description: 判断是否为数字<br>
     * 1、…<br>
     * 2、…<br>
     * 3、…<br>
     * 4、…<br>
     *
     * @param str
     * @return boolean
     * @exception/throws [违例类型] [违例说明]
     */
    public static boolean isNumber(String str) {
        if (null == str || "".equals(str)) {
            return false;
        } else {
            return Pattern.matches("^[0-9]+$", str);
        }
    }


    /**
     * Description: 获得字符(byte)的实际长度<br>
     * 1、…<br>
     * 2、…<br>
     * 3、…<br>
     * 4、…<br>
     *
     * @param s
     * @return int
     * @exception/throws
     */
    public static int realLength(String s) {
        if (null == s || "".equals(s)) {
            return 0;
        } else {
            return s.getBytes().length;
        }
    }

    /**
     * Description: 检查非法字符<br>
     */
    public static boolean isUnlawfulChar(String s) {
        if (null == s || "".equals(s)) {
            return false;
        } else {
            return Pattern.matches("^[^`~@#\\$%\\^&\\*\\(\\)=\\!\\+\\\\/\\|<>\\?;\\:\\.'\"\\{\\}\\[\\]??, ]*$", s);
        }
    }

    /**
     * Description: 判断是否为字母<br>
     * 1、…<br>
     * 2、…<br>
     * 3、…<br>
     * 4、…<br>
     *
     * @param str
     * @return boolean
     * @exception/throws [违例类型] [违例说明]
     */
    public static boolean isLetter(String str) {
        if (null == str || "".equals(str)) {
            return false;
        } else {
            return Pattern.matches("^[A-Za-z]+$", str);
        }
    }

    /**
     * (source != null) ? source : "";相当于oracle的nvl函数
     *
     * @param source
     * @return String
     */
    public static String nvl(String source) {
        return (source != null) ? source.trim() : "";
    }

    /**
     * Method nvl.
     *
     * @param source        String
     * @param defaultString String
     * @return String
     */
    public static String nvl(String source, String defaultString) {
        return (source != null) ? source.trim() : defaultString;
    }

    /**
     * 实现对符合条件的字符串的反转
     *
     * @param str 原始字符串
     * @return 经过反转处理后的字符串
     */
    public static String reverseString(String str) {
        StringBuffer resultStringBuffer = new StringBuffer();
        String[] allStr = str.split(" ");
        StringBuffer sb = new StringBuffer(); // 保存需要进行反转的字符串
        for (int i = 0; i < allStr.length; i++) {
            if (meetReverseCondition(allStr[i])) {
                sb.delete(0, sb.length());

                // 将需要反转的字符串反转后添加到结果字符串
                resultStringBuffer.append(sb.append(allStr[i]).reverse()).append(" ");
            } else {
                resultStringBuffer.append(allStr[i]).append(" ");
            }
        }

        return resultStringBuffer.deleteCharAt(resultStringBuffer.length() - 1).toString();
    }

    /**
     * 判断字符串是否符合反转条件 本函数被reverseString(String str)调用
     *
     * @param str 需要进行反转处理的字符串
     * @return true 符合反转条件 false 不符合反转条件
     */
    private static boolean meetReverseCondition(String str) {
        // 如果字符串是以'*'开头并且长度大于1,并且第二个字符是英文,汉字或数字
        return (str.charAt(0) == '*') && (str.length() > 1) && Character.isLetterOrDigit(str.charAt(1));
    }

    /**
     * 取字符串的前maxLength长度的字串（一个中文字符为2个长度）
     *
     * @param str
     * @param maxLength
     * @return String throws
     */
    public static String splitStr(String str, int maxLength) {
        if (maxLength <= 0) {
            return "";
        }
        String result = str;
        byte[] bytes = str.getBytes();
        if (bytes.length > maxLength) {

            result = new String(bytes, 0, maxLength);
            if (!result.substring(result.length() - 1).equals(str.substring(result.length() - 1, result.length()))) {
                result = new String(bytes, 0, maxLength - 1);
            }
        }

        return result;
    }

    /**
     * 判断是否存在
     *
     * @param filterStr
     * @param id
     * @return boolean
     */
    public static boolean isExists(String filterStr, String id) {
        String[] filterTag = filterStr.split(",");
        for (int i = 0; i < filterTag.length; i++) {
            if (id.equals(filterTag[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 格式化时间
     *
     * @param dt
     * @param format
     * @return String
     */
    public static String getTime(Date dt, String format) {
        String time = null;
        java.text.DateFormat df = new java.text.SimpleDateFormat(format);
        time = df.format(dt);

        return time;
    }

    public static String[] stringToArray(String str, String start, String end, String split) {
        if (null == str || "".equals(str))
            return null;
        if (str.indexOf(start) == -1 || str.indexOf(end) == -1 || str.indexOf(split) == -1)
            return new String[]{str};
        return str.substring(str.indexOf(start) + 1, str.indexOf(end)).split(split);
    }

    /**
     * 判断字符串是否为null或者空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 校验是否为JSON
     *
     * @param msg
     * @return
     */
    public static Boolean isJsonObject(String msg) {
        try {
            com.alibaba.fastjson.JSONObject.parseObject(msg);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 获取指定长度(按字节长度获取)的字符串，中文算2个字节长度，兼容oracle的 varchar2长度计算方式
     *
     * @param src    源字符串
     * @param length 长度
     * @return
     */
    public static String getSubStr(String src, int length) {
        if (isEmpty(src)) {
            return null;
        }
        byte[] b = src.getBytes();
        if (b.length > length) {
            byte[] s = new byte[length];
            for (int i = 0; i < length; i++) {
                s[i] = b[i];
            }
            return new String(s);
        } else {
            return src;
        }
    }

    public static String truncateMessage(String description, int length) {
        org.springframework.util.Assert.state(length > 0);
        if ((description != null) && (description.length() > length)) {
            return description.substring(0, length);
        }

        return description;
    }

    /**
     * json是否包含key 并且存在值
     *
     * @param param
     * @param key
     * @return
     */
    public static boolean jsonHasKayAndValue(com.alibaba.fastjson.JSONObject param, String key) {
        if (param == null) {
            return false;
        }

        if (!param.containsKey(key)) {
            return false;
        }

        if (isEmpty(param.getString(key))) {
            return false;
        }

        return true;
    }

    /**
     * json是否包含key 并且存在值
     *
     * @param param
     * @param key
     * @return
     */
    public static boolean jsonHasKayAndValue(String param, String key) {
        com.alibaba.fastjson.JSONObject paramObj = null;
        try {
            paramObj = com.alibaba.fastjson.JSONObject.parseObject(param);
            return jsonHasKayAndValue(paramObj, key);
        } catch (Exception e) {
            return false;
        }
    }
}
