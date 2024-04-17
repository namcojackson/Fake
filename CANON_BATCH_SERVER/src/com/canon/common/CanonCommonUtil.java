package com.canon.common;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class CanonCommonUtil {

	public final String DT_FORMAT1 = "yyyyMMddhhmm";
	public final String DT_FORMAT2 = "MM/dd/yyyy HH:mm";
	public final String DT_FORMAT3 = "yyyyMMdd";
	public final String DT_FORMAT4 = "dd-MMM-yyyy";
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
	
  public String checkNull(String str){
			 if (str != null) {
			    return str;
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
}
