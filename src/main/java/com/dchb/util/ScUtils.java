package com.dchb.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScUtils {
	public final static String DEFAULT_FORMAT="yyyy-MM-dd HH:mm:ss";
	/**
	 * 
	 * @return
	 */
	public static String getStrFdate(){
		return getStrFdate(new Date());
	}
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String getStrFdate(Date date){
		return getStrFdate(date,DEFAULT_FORMAT);
	}
	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getStrFdate(Date date,String format){
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	/**
	 * 
	 * @param datestr
	 * @param format
	 * @return
	 */
	public static Date getDateFstr(String datestr,String format ){
		DateFormat dateFormat = new SimpleDateFormat(format);
		try {
			return dateFormat.parse(datestr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @param datestr
	 * @return
	 */
	public static Date getDateFstr(String datestr){
		return getDateFstr(datestr,DEFAULT_FORMAT);
	}
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLingchenTime(Date date){
		String dateStr= getStrFdate(date,"yyyy-MM-dd 00:00:00");
		return getDateFstr(dateStr);
	}
	/**
	 * 
	 * @param date
	 * @param day
	 * @param islingchen
	 * @return
	 */
	public static Date getDiffDay(Date date,int day,boolean islingchen){
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, day);
		Date now=c.getTime();
		if(islingchen){
			return getLingchenTime(now);
		}else
		{
			return now;
		}
	}
	
	/**
	 * 获取退后或者前几天的时间，按照凌晨时间计算
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date getDiffDay(Date date,int day){
		return getDiffDay(date,day,true);
	}
	/**
	 * 获取时间差
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Long getBetweenHour(Date date1,Date date2){
		Long diff=date2.getTime()-date1.getTime();
		Long day = diff / (24 * 60 * 60 * 1000);  
		Long  hour = (diff / (60 * 60 * 1000) - day * 24);
		if(diff % (60 * 60 * 1000)==0){
			return hour;
		}else
		{
			return hour+1;
		}
	}
	
	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Long getBetweenHour(String date1,String date2){
		Long diff=getDateFstr(date2).getTime()-getDateFstr(date1).getTime();
		Long day = diff / (24 * 60 * 60 * 1000);  
		Long  hour = (diff / (60 * 60 * 1000) - day * 24);
		if(diff % (60 * 60 * 1000)==0){
			return hour;
		}else
		{
			return hour+1;
		}
	}
	/**
	 * 解析参数 参数名=值1,值2;参数名=值1,值2
	 * @param info
	 * @return
	 */
	public static Map<String,String> parseInfo(String info){
		Map<String,String> params=new HashMap<String,String>();
		String[] infos=info.split(";");
		for(String child:infos){
			String[] infoTmp=child.split("=");
			params.put(infoTmp[0], infoTmp[1]);
		}
		return params;
	}
	/**
	 * 
	* @Title: exist    
	* @Description: TODO(判断字符串是否在集合里面)    
	* @param @param str
	* @param @param collection
	* @param @param regex
	* @param @return    设定文件    
	* @return boolean    返回类型    
	* @throws
	 */
	public static boolean existStr(String str,String collection,String regex){
		String[] results=collection.split(regex);
		for(String tmp:results){
			if(str.equals(tmp)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	* @Title: existInt    
	* @Description: TODO(判断数字是否在集合之内)    
	* @param @param str
	* @param @param collection
	* @param @param regex
	* @param @return    设定文件    
	* @return boolean    返回类型    
	* @throws
	 */
	public static boolean existInt(String str,String collection,String regex){
		String[] results=collection.split(regex);
		for(String tmp:results){
			if(Integer.parseInt(str)==Integer.parseInt(tmp)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	* @Title: convertFunicode    
	* @Description: TODO(unicode 转化为中文)    
	* @param @param utfString
	* @param @return    设定文件    
	* @return String    返回类型    
	* @throws
	 */
	public static String convertUnicode(String ori){
        char aChar;
        int len = ori.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = ori.charAt(x++);
            if (aChar == '\\') {
                aChar = ori.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = ori.charAt(x++);
                        switch (aChar) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException(
                                    "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
 
        }
        return outBuffer.toString(); 
    }
	/**
	 * 
	  * @Title: checkFile  
	  * @Description: TODO(判断文件格式)  
	  * @param @param fileName
	  * @param @return    参数  
	  * @return boolean    返回类型  
	  * @throws  
	  * @user hukai
	  * @Date
	 */
	 public static  boolean checkFile(String fileName){  
	        boolean flag=false;  
	        String suffixList="xls,xlsx,jpg,gif,png,ico,bmp,jpeg,csv,txt";  
	        //获取文件后缀  
	        String suffix=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());  
	        if(suffixList.contains(suffix.trim().toLowerCase())){  
	            flag=true;  
	        }  
	        return flag;  
	} 
	 
	public static void main(String args[]){
		System.out.println(ScUtils.convertUnicode("\u9488\u7ec7\u886b"));
	}
	/**
		 * 
		 * @Title: validateSpeStr  
		 * @Description: TODO(判断是否有特殊字符)    
		 * @param @param 
		 * @return ReturnMsg    返回类型 
		 * @author hukai
		 * @date 2018年9月17日上午10:13:05
		 */
	public static boolean validateSpeStr(String str,String isValid){
		if("N".equals(isValid))return false;
		String regEx = "[`~ !@#$%^&*()=|{}':;'\\[\\].<>?~！@#￥%……&*（）——|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}
	/**
	 * 递归组织IN查询条件 
	 * a,b,c 返回 a b c ab ac bc abc
	 */
	private static List<String> allCombine(String[] in, StringBuffer out, int start,List<String>result) {
        for (int i = start; i < in.length; i++) {
            out.append(";"+in[i]);
            result.add(out.toString());
            if (i < in.length - 1) {
                allCombine(in, out, i + 1,result);
            }
            out.setLength(out.length() - in[i].length()-1);
        }
        return result;
    }
	
	/**
	 * 将list转为map
	 * @param key mapkey的字段值，多个以_隔开 如whcode_skuoid
	 * @param num 需要累加数量的字段
	 * @param list 需要转换的集合
	 */
	public static Map<String, Object>convertList2Map(List list,String key,String num){
		Map<String, Object>map=new LinkedHashMap<String,Object>();
		for(Object obj:list){
			String k="";
			if(key!=null){
				String[]ka=key.split("_");
				String []v=new String[ka.length];
				Field[] field = obj.getClass().getDeclaredFields();
				try {
		            for (int j = 0; j < field.length; j++) { // 遍历所有属性
		                String name = field[j].getName(); // 获取属性的名字
		                for(int i=0;i<ka.length;i++){
		                	String a=ka[i];
							if(a.equalsIgnoreCase(name)){
								 name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
					            // 如果type是类类型，则前面包含"class "，后面跟类名
				                Method m = obj.getClass().getMethod("get" + name);
				                String value = (String) m.invoke(obj); // 调用getter方法获取属性值
				                v[i]=value;
							}
						}
		            }
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				for(String s:v){
					k+="_"+s;
				}
				String kk=k.substring(1);
				Object c=obj;
				if(map.containsKey(kk)){
					c=map.get(kk);
					try {
			            for (int j = 0; j < field.length; j++) { // 遍历所有属性
			                String name = field[j].getName(); // 获取属性的名字
			                if(name.equalsIgnoreCase(num)){
								 name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
					            // 如果type是类类型，则前面包含"class "，后面跟类名
				                Method m = obj.getClass().getMethod("get" + name);
				                Method cm = c.getClass().getMethod("get" + name);
				                Long value = (Long) m.invoke(obj); // 调用getter方法获取属性值
				                Long cv=(Long)cm.invoke(c);
				                m = c.getClass().getMethod("set"+name,Long.class);
		                        m.invoke(c, value+cv);
							}
			            }
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				map.put(kk, c);
			}
		}
		return map;
	}
	
	/**
	 * 获取时间分钟差 time1-time2
	 */
	public static long getDiffMin(Date time1,Date time2){
		return (time1.getTime()-time2.getTime())/1000/60;
	}
	/**
	 * 获取时间天差 time1-time2
	 */
	public static long getDiffDay(Date time1,Date time2){
		return (time1.getTime()-time2.getTime())/1000/60/60/24;
	}
	/**
	 * 字符串排序 a1 a2 a10
	 */
	private static int compare(String o1, String o2) {
        // 前面3个IF主要是判空的
        if (o1 == o2) {
            return 0;
        }
        if (o1 == null) {
            return 1;
        }
        if (o2 == null) {
            return -1;
        }
        // 这里没有做太多的判断, index 代表第几个开始是数字, 直接从后面遍历
        // 比如 aa11, 我们就会判断从下标[2]开始为不是数字, 就直接截取 [2] 后面, 即11
        int index = 0;
        for (index = o1.length() - 1; index >= 0
                && (o1.charAt(index) >= '0' && o1.charAt(index) <= '9'); index--)
            ;
        int num1 = Integer.parseInt(o1.substring(index + 1));
        for (index = o2.length() - 1; index >= 0
                && (o2.charAt(index) >= '0' && o2.charAt(index) <= '9'); index--)
            ;
        int num2 = Integer.parseInt(o2.substring(index + 1));
        return num1 - num2;
    }
}
