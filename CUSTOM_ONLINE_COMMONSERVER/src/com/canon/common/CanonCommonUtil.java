package com.canon.common;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.sql.Timestamp;

public class CanonCommonUtil {

	public static final String DT_FORMAT1 = "yyyyMMddhhmm";
	public static final String DT_FORMAT2 = "MM/dd/yyyy HH:mm";
	public static final String DT_FORMAT3 = "yyyyMMdd";
	public static final String DT_FORMAT4 = "dd-MMM-yyyy";
	public static final String DT_FORMAT5 = "MMM dd yyyy";
	public final boolean LOG_EABLED = true;

	
	
	public  String genXmlInput(HashMap<String, String> hMap) {

		StringBuffer sbXml = new StringBuffer();

		String eo = hMap.get("entityObject");
		String sc = hMap.get("SelectClause");
		String wc = hMap.get("WhereClause");
		String ord = hMap.get("OrderByClause");

		String cs = "<Column>";
		String ce = "</Column>";
		String cvs = "<ColumnValue>";
		String cve = "</ColumnValue>";
		String ss = "<SelectClause>";
		String se = "</SelectClause>";
		String ws = "<WhereClause>";
		String we = "</WhereClause>";

		String wcs = "<WhereClauses>"; 
		String wce = "</WhereClauses>";
		
		String ords = "<OrderByClause>";
		String orde = "</OrderByClause>";
		
		String ordcs = "<OrderByClauses>"; 
		String ordce = "</OrderByClauses>";
		
		
		sbXml.append("<AllClauses>");

		sbXml.append("<entityObject>");
		sbXml.append(cs + eo + ce);
		sbXml.append("</entityObject>");

		String[] arr_sc = sc.split(";");
		for (int i = 0; i < arr_sc.length; i++) {
			sbXml.append(ss + cs + arr_sc[i] + ce + se);
		}

		if(wc!=null) {
			String[] arr_wr = wc.split(";");
			sbXml.append(wcs);
			for (int i = 0; i < arr_wr.length; i++) {
				String wcTmp = arr_wr[i];
				sbXml.append(ws).append(cs + wcTmp.split(":")[0] + ce)
						.append(cvs + wcTmp.split(":")[1] + cve).append(we);
			}
			sbXml.append(wce);
		}	
		
		if(ord!=null) {
			String[] arr_or = ord.split(";");
			for (int i = 0; i < arr_or.length; i++) {
				sbXml.append(ords + cs + arr_or[i] + ce + orde);
			}
		}			
		
		sbXml.append("</AllClauses>");

		return sbXml.toString();
	}
	
	
	public  void printXml(String str){
		
	   String	strTmp = str.replaceAll(">", ">\n");
		
		System.out.println(strTmp);
	}



	public static String formatDateString(String frmFrmt, String toFrmt,String str) {
		if (str != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(frmFrmt);
			try {
				java.util.Date d = sdf.parse(str);
				str = (new SimpleDateFormat(toFrmt)).format(d);
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
		}else{
			str=""; 
		} 
		return str; 
 	}

  public static String checkNull(String str){
			 if (str != null) {
			    return str;
			}else {
				return "";
			}
   }
  
  public static String checkNull(BigDecimal bd){
		 if (bd != null) {
		    return bd.toString();
		}else {
			return "";
		}
  }
  
  public void logMsg(boolean error,String cls,  String str ){
 	   String e="[ERROR]";
 	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
 	   PrintStream out = System.err;
 	   
 	  if(!error  ){
		 e ="[INFO]";
		 out = System.out;
 	  }	 
 	 out = System.out; // setting System.out to all Log msgs
	  if(LOG_EABLED)
 	  out.println("["+sdf.format(Calendar.getInstance().getTime())+"] : "+e+"["+cls +"]"+ str  );
 }
  public  String checkStrNull(String str){
		if ("null".equals(str)) {
			return "";
		}else {
			return str;
		} 
  } 
  public static String formatDateTime(String str){
	  
	  if (str != null) {
		  SimpleDateFormat toFormat = new SimpleDateFormat("MMM dd yyyy hh:mm a", Locale.US);// new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.US);
		  SimpleDateFormat fromFormat = new SimpleDateFormat("yyyyMMddHHmm");
		  try{
			  java.util.Date d = fromFormat.parse(str);
			  str = toFormat.format(d);
			  //System.out.println("ret Date1 : "+ str);
		  }catch (ParseException ex) {
			  ex.printStackTrace();
		  }
	  }else{
		  return ""; 
	  }
	  return str; 
  } 
  public static String format24HrTm(String str) throws ParseException{
	  if (str != null) {
		  SimpleDateFormat displayFormat = new SimpleDateFormat("HHmm");
	      SimpleDateFormat parseFormat = new SimpleDateFormat("hhmm a");
	      try{    
		      java.util.Date date = parseFormat.parse(str);
		      str = displayFormat.format(date);
		      System.out.println("str : "+ str+ " date : " + date);
	      	}catch (ParseException ex){
	      		 ex.printStackTrace();
	      	}
	  }else{
		  str = ""; 
	  }
	  return str;
  }
  
  public static Object nth(Object obj,int n) {
      if (obj instanceof Object[]) {
          Object[] objs = (Object[]) obj;
          return objs.length < n ? null : objs[n-1];
      } else if (obj instanceof List) {
          List l = (List) obj;
          return l.size() < n ? null : l.get(n-1);
      }
      return null;
  }

  public static Object first(Object obj) {
      return nth(obj,1);
  }

  public static Object second(Object obj) {
      return nth(obj,2);
  }

  public static Object third(Object obj) {
      return nth(obj,3);
  }
  
  public static BigDecimal BigDecimal_ZERO = new BigDecimal("0");
  public static BigDecimal toBigDecimal(String s, boolean nullToZero) {
      if (s != null && s.trim().length()>0) {
          try {
              return new BigDecimal(s.trim().replaceAll(",",""));
          } catch (NumberFormatException e) {
              return null;
          }
      }
      return nullToZero ? BigDecimal_ZERO : null;
  }
  
  public static String formatDateTime(DateFormat df, java.util.Date d) {
      String strDate = "";
      if (d != null) {
          try {
              strDate = df.format(d);
          } catch (Exception ex) {
              strDate = "";
          }
      }
      return strDate;
  }
  
  private static SimpleDateFormat sdf5 = new SimpleDateFormat(DT_FORMAT5);
  
  public static String formatDateTime5(java.util.Date d) {
      return formatDateTime(sdf5,d);
  }
  
  public static Timestamp toTimestamp5(String tsStr) {
      try {
          return tsStr == null || tsStr.trim().length() == 0 ? null : new Timestamp(sdf5.parse(tsStr).getTime());
      } catch (ParseException e) {
          System.out.println(e);
          return null;
      }
  }
  
  
}
