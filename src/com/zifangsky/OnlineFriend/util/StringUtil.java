package com.zifangsky.OnlineFriend.util;

import java.io.UnsupportedEncodingException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringEscapeUtils;

import com.sun.rowset.CachedRowSetImpl;

public class StringUtil {
	/**
	 * 字符串格式转换
	 * 
	 * */
	public static String handleString(String str){
		try {
			byte[] bb = str.getBytes("UTF-8");
			str = new String(bb);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		return str;
	}
	
	/**
	 * 判断字符串是否为空
	 * @param str String类型的字符串
	 * 
	 * @return 不为空返回 true
	 * */
	public static boolean isNotEmpty(String str){
		if(!"".equals(str) && str != null)
			return true;
		else 
			return false;
	}
	
	/**
	 * 将一个字符串中的小写字母转换为大写字母
	 * 
	 * */
	public static String convertToCapitalString(String src)
    {
         char[] array = src.toCharArray();
          int temp = 0;
          for (int i = 0; i < array.length; i++)
          {
              temp = (int) array[i];
              if (temp <= 122 && temp >= 97){ // array[i]为小写字母
                   array[i] = (char) (temp - 32);
              }
           }
           return String.valueOf(array);
       }  
	
	/**
	 * 字符串XSS过滤，JavaScript过滤，Sql过滤
	 * 
	 * @param str 传入的字符串
	 * 
	 * @return 转义后的字符串
	 * */
	public static String xssEncode(String str){
		String s = StringEscapeUtils.escapeHtml(str);
//		s = StringEscapeUtils.escapeJavaScript(s);
//		s = StringEscapeUtils.escapeSql(s);		
		return s;	
	}
	
	/**
	 * 获取当前时间以及随机的length个字符组成的字符串，用来重命名文件
	 * @param length 日期后面的随机字符串的长度
	 * 
	 * @return 当前精确到毫秒的时间+length长度的随机字符串组成的字符串
	 * */
	public static String getNewFileNameString(int length){
		String base = "qwertyuioplkjhgfdsazxcvbnm0123456789";
		Date date = new Date();
		Format format = new SimpleDateFormat("yyyyMMddHHmmssSSS");  //格式化当前时间，精确到毫秒
		String dateString = format.format(date);	
		
		Random random = new Random();
		StringBuffer stringBuffer = new StringBuffer();
		for(int i=0;i<length;i++){
			int dot = random.nextInt(base.length());
			stringBuffer.append(base.charAt(dot));
		}
		String randomString = stringBuffer.toString();
		
		return dateString+randomString;		
	}
	
	/**
	 * 将从数据库中取出的数据以表格形式显示
	 * @param page 当前显示页
	 * @param pageSize 每页显示记录数
	 * @param rowSet 存储表中全部记录的行集对象
	 * 
	 * @return 表格格式化后的包含所有记录的StringBuffer
	 * */
	public static StringBuffer showMember(int page,int pageSize,CachedRowSetImpl rowSet){
		StringBuffer str = new StringBuffer();
		try {
			rowSet.absolute((page-1)*pageSize + 1);  //指向当前页的第一个元素
			for(int i=1;i<=pageSize;i++){
				str.append("<tr>");
				str.append("<td align=left>" + rowSet.getString(1) + "</td>");
				str.append("<td align=left>" + rowSet.getString(2) + "</td>");
				str.append("<td align=left>" + rowSet.getString(3) + "</td>");
				str.append("<td align=left>" + rowSet.getString(4) + "</td>");
				String imgString = "<img src=data/userfile/image/" + rowSet.getString(5) + " width=100 height=100/>";
				str.append("<td align=left>" + imgString + "</td>");
				str.append("</tr>");
				rowSet.next();
			}			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return str;
	}
	
	/**
	 * 显示文章列表
	 * @param page 当前显示页
	 * @param pageSize 每页显示记录数
	 * @param rowSet 存储表中全部记录的行集对象
	 * 
	 * @return 表格格式化后的包含所有记录的StringBuffer
	 * */
	public static StringBuffer showArticleTitle(int page,int pageSize,CachedRowSetImpl rowSet){
		StringBuffer str = new StringBuffer();
		try {
			rowSet.absolute((page-1)*pageSize + 1);  //指向当前页的第一个元素
			str.append("<br>");
			for(int i=1;i<=pageSize;i++){
				str.append("<tr>");
				str.append("<td align=left><a href=ArticleShowContent?id=" + rowSet.getInt(1) + ">" + rowSet.getString(2) + "</a></td>");				
				str.append("</tr>");
				rowSet.next();
			}			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return str;
	}
	
}
