package com.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String formatDate(Date date,String format){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			result=sdf.format(date);
		}
		return result;
	}
	
	
	public static Date formatString(String str,String format) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}
	
	
	public static double miaochalong(Date jieshuDate,Date kaishiDate) throws Exception{
		double miaochalong = (jieshuDate.getTime()-kaishiDate.getTime())/1000;
		return miaochalong;
	}
	
	
	public static int fenzhongcha(Date jieshuDate,Date kaishiDate) throws Exception{
		double miaochalong = miaochalong(jieshuDate,kaishiDate);
		int fenzhongcha = (int) Math.ceil(miaochalong/60);
		return fenzhongcha;
	}
	
	
	public static int xiaoshicha(Date jieshuDate,Date kaishiDate) throws Exception{
		double miaochalong = miaochalong(jieshuDate,kaishiDate);
		int xiaoshicha = (int) Math.ceil((miaochalong/(60*60)));
		return xiaoshicha;
	}
	
	
	public static int tiancha(Date jieshuDate,Date kaishiDate) throws Exception{
		double miaochalong = miaochalong(jieshuDate,kaishiDate);
		int tiancha = (int) Math.ceil((miaochalong/(60*60*24)));
		return tiancha;
	}
	
	
	public static Date jiafenzhong(Date kaishiDate,int shijiancha) throws Exception{
		Date jieshuDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(kaishiDate);
		calendar.add(Calendar.MINUTE,shijiancha);
		jieshuDate = calendar.getTime();
		return jieshuDate;
	}
	
	
	public static Date jiaxiaoshi(Date kaishiDate,int shijiancha) throws Exception{
		Date jieshuDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(kaishiDate);
		calendar.add(Calendar.HOUR,shijiancha);
		jieshuDate = calendar.getTime();
		return jieshuDate;
	}
	
	
	public static Date jiatian(Date kaishiDate,int shijiancha) throws Exception{
		Date jieshuDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(kaishiDate);
		calendar.add(Calendar.DATE,shijiancha);
		jieshuDate = calendar.getTime();
		return jieshuDate;
	}
	
	
	public static Date jiaxingqi(Date kaishiDate,int shijiancha) throws Exception{
		Date jieshuDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(kaishiDate);
		calendar.add(Calendar.DATE,shijiancha*7);
		jieshuDate = calendar.getTime();
		return jieshuDate;
	}
	
	
	public static Date jiayue(Date kaishiDate,int shijiancha) throws Exception{
		Date jieshuDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(kaishiDate);
		calendar.add(Calendar.MONTH,shijiancha);
		jieshuDate = calendar.getTime();
		return jieshuDate;
	}
	
	
	public static Date jianian(Date kaishiDate,int shijiancha) throws Exception{
		Date jieshuDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(kaishiDate);
		calendar.add(Calendar.YEAR,shijiancha);
		jieshuDate = calendar.getTime();
		return jieshuDate;
	}
}
