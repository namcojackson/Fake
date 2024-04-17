package com.canon.apps.e001;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import canon.apps.common.CanonCustomProfile;
import canon.apps.common.CanonS21SessionValidate;
import canon.apps.pci.util.CanonPCISecurityUtil;

public class CanonE001CommonUtil {

	public static final String DT_FORMAT1 = "yyyyMMddhhmm";
	public static final String DT_FORMAT2 = "MM/dd/yyyy HH:mm";
	public static final String DT_FORMAT3 = "yyyyMMdd";
	public static final String DT_FORMAT4 = "dd-MMM-yyyy";
	public static final String DT_FORMAT5 = "MMM dd yyyy";
	public final boolean LOG_EABLED = true;

    public static final int LINKS_FOR_PAGINATION = 15;
	
	
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
		  SimpleDateFormat fromFormat = new SimpleDateFormat("yyyyMMddhhmm");
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
  
  static Pattern NON_INT_PATTERN = Pattern.compile("[^0-9]");

  public static int toInt(String strInt) {
	  return toInt(strInt,-1);
  }
  
  public static int toInt(String strInt,int defaultVal) {
      if (strInt != null) {
          try {
              Matcher matcher = NON_INT_PATTERN.matcher(strInt);
              if (matcher.find()) {
                  String cleanStr = matcher.replaceAll("");
                  return Integer.parseInt(cleanStr);
              } else {
                  return Integer.parseInt(strInt);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
      return defaultVal;
  }

  static Pattern PATTERN_WHITESPACES = Pattern.compile("\\s+");

  public static String trimAll(String str) {
      return PATTERN_WHITESPACES.matcher(str).replaceAll("");
  }

  public static boolean isEmpty(final CharSequence cs) {
	        return cs == null || cs.length() == 0;	
  }  
  
  public static String trim(final String str) {
	  return str == null ? null : str.trim();
  }
	    
  public static String trimToNull(final String str) {
	  final String ts = trim(str);
	  return isEmpty(ts) ? null : ts;
  }
  
  // [+-]?
  // (?:
  //  (?:0)|                              Zero
  //  (?:[123456789][\\d,]*\\d*)|         Integer
  //  (?:[\\d,]*\\.\\d*)                  Number with decimal places
  // )
  static Pattern PARRETN_DEIMAL_NUM = Pattern.compile("[+-]?(?:(?:0)|(?:[123456789][\\d,]*\\d*)|(?:[\\d,]*\\.\\d*))");

  public static boolean isNumber(String str) {
      if(isEmpty(str))return false;
      Matcher matcher = PARRETN_DEIMAL_NUM.matcher(str);
      return matcher.matches();
  }
    
  public static String genPaginationSummary(int totalRecords, int recordsPerPage, int currentPage) {
      if (totalRecords == 0) {
          return "";
      }
      int start = (currentPage - 1) * recordsPerPage + 1;
      if (start > totalRecords || start <= 0) {
          return "<span class='pagination-summary'>" + NumberFormat.getInstance().format(totalRecords) + " </span>";
      }
      int end = currentPage * recordsPerPage > totalRecords ? totalRecords : currentPage * recordsPerPage;
      return "<span class='pagination-summary'>" + start + " to " + end + " of " + NumberFormat.getInstance().format(totalRecords) + " </span>";
  }

  public static String genPageLinks(String jsFuncName,
          String modalName,
          String formName,
          int totalPages, int recordsPerPage, int currentPage, int totalRecords) {
      String fontClass = "pageNum";
      String fontClassInactive = "linkPageInactive";

      int showCount = LINKS_FOR_PAGINATION;
      return genPageLinks(jsFuncName,
              modalName,
              formName, totalPages, recordsPerPage, currentPage, totalRecords,
              fontClass, fontClassInactive, showCount);
  }

  public static String genPageLinks(String jsFuncName, String modalName,
          String formName, int totalPages, int recordsPerPage, int currentPage, int totalRecords,
          String fontClass, String fontClassInactive,
          int showCount) {

      String firstPage = "First";
      String prevPage = "Previous ";
      String nextPage = "Next ";
      String lastPage = "Last";
      String separator = "&nbsp;\n\t\t\t";
      String tabs = "\t\t\t";
      int beg = 0;
      int end = 0;
      int count = showCount / 2;

      StringBuffer strBuf = new StringBuffer(tabs);

      if (totalPages > 1) {
          if (currentPage != 1) {
              strBuf.append(genPageNumberLink(jsFuncName, modalName, formName,
                      1, firstPage,
                      fontClass));
              strBuf.append(separator);
              strBuf.append(genPageNumberLink(jsFuncName, modalName, formName,
                      currentPage - 1, prevPage,
                      fontClass));
          } else {
              strBuf.append("<FONT CLASS=");
              strBuf.append(fontClassInactive);
              strBuf.append(">");
              strBuf.append(firstPage);
              strBuf.append("</FONT>");
              strBuf.append(separator);
              strBuf.append("<FONT CLASS=");
              strBuf.append(fontClassInactive);
              strBuf.append(">");
              strBuf.append(prevPage);
              strBuf.append("</FONT>");
          }
          strBuf.append(separator);
          if (showCount > 0) {
              beg = currentPage - count - 1;
              end = currentPage + count;
              if (beg <= 0) {
                  beg = 0;
                  end = showCount;
              }
              if (end > totalPages) {
                  beg = totalPages - showCount;
                  if (beg < 0) {
                      beg = 0;
                  }
                  end = totalPages;
              }
              for (int i = beg; i < end; i++) {
                  int pageNo = i + 1;
                  String pageText = String.valueOf(pageNo);
                  if (currentPage == pageNo) {
                      strBuf.append("<FONT CLASS=");
                      strBuf.append(fontClassInactive);
                      strBuf.append(">");
                      strBuf.append(pageText);
                      strBuf.append("</FONT>");
                      strBuf.append(separator);
                  } else {
                      strBuf.append(genPageNumberLink(jsFuncName, modalName,
                              formName, pageNo, pageText,
                              fontClass));
                      strBuf.append(separator);
                  }
              }
          }
          if (currentPage == totalPages) {
              strBuf.append("<FONT CLASS=");
              strBuf.append(fontClassInactive);
              strBuf.append(">");
              if (showCount == 0) {
                  strBuf.append(genPaginationSummary(totalRecords, recordsPerPage, currentPage));
              }
              strBuf.append(separator);
              strBuf.append(nextPage);
              strBuf.append("</FONT>");
              strBuf.append(separator);
              strBuf.append("<FONT CLASS=");
              strBuf.append(fontClassInactive);
              strBuf.append(">");
              strBuf.append(lastPage);
              strBuf.append("</FONT>");
          } else {
              if (showCount == 0) {
                  strBuf.append(genPaginationSummary(totalRecords, recordsPerPage, currentPage));
              }
              strBuf.append(separator);
              strBuf.append(genPageNumberLink(jsFuncName, modalName, formName,
                      currentPage + 1, nextPage,
                      fontClass));
              strBuf.append(separator);
              strBuf.append(genPageNumberLink(jsFuncName, modalName, formName,
                      totalPages, lastPage,
                      fontClass));
          }
      } else {
          strBuf.append("&nbsp;\n");
      }
      return strBuf.toString();
  }

  public static String genPageNumberLink(String jsFuncName, String modalName,
          String formName, int pageNo,
          String pageText, String fontClass) {

      StringBuffer strBuf = new StringBuffer("<A HREF=\"#\" data-page=").append(pageNo)
    		  .append(" id=page").append(pageNo);
      if (jsFuncName != null) {
          strBuf.append(" ONCLICK=\"");
          String jsString = genPageJsString(jsFuncName, modalName, formName, pageNo);
          strBuf.append(jsString);
          strBuf.append("\"");
      }
      strBuf.append(" CLASS=");
      strBuf.append(fontClass);
      strBuf.append(">");
      strBuf.append(pageText);
      strBuf.append("</A>");
      return strBuf.toString();
  }

  public static String genPageJsString(String jsFuncName, String modalName,
          String formName, int pageNo) {
      //searchEmployeeInfo('resource-modal',1,'resourceLOV')
      StringBuffer jsStrBuf = new StringBuffer("javascript:");
      jsStrBuf.append(jsFuncName);
      jsStrBuf.append("(");
      if (modalName != null && modalName.length() > 0) {
          jsStrBuf.append("'" + modalName);
          jsStrBuf.append("',");
      }
      jsStrBuf.append(pageNo);
      jsStrBuf.append(",'");
      jsStrBuf.append(formName);
      jsStrBuf.append("');return false;");

      return jsStrBuf.toString();
  }

  public static boolean isCharCol(int colIndex){
	 return colIndex>=1 && colIndex<=50; 
  }

  public static boolean isNumberCol(int colIndex){
	 return colIndex>=51&&colIndex<=75; 
  }
  public static boolean isDateCol(int colIndex){
	 return colIndex>=76&&colIndex<=100; 
  }

  public static void setCdValValue(int colIndex,String charVal, CanonE001CodeTableDAO.CanonE001CodeTabValRec v){
	  BigDecimal numberVal=isNumberCol(colIndex) ? toBigDecimal(charVal,false) : null;
	  Timestamp dateVal=isDateCol(colIndex) ? toTimestamp5(charVal) :null;
	  if(charVal!=null && charVal.indexOf('\'')>=0){
		  charVal=CanonPCISecurityUtil.sqlEncode(charVal);
	  }
	  switch(colIndex){
	  case 1:
		    v.setVal1(charVal);
		    break;
		case 2:
		    v.setVal2(charVal);
		    break;
		case 3:
		    v.setVal3(charVal);
		    break;
		case 4:
		    v.setVal4(charVal);
		    break;
		case 5:
		    v.setVal5(charVal);
		    break;
		case 6:
		    v.setVal6(charVal);
		    break;
		case 7:
		    v.setVal7(charVal);
		    break;
		case 8:
		    v.setVal8(charVal);
		    break;
		case 9:
		    v.setVal9(charVal);
		    break;
		case 10:
		    v.setVal10(charVal);
		    break;
		case 11:
		    v.setVal11(charVal);
		    break;
		case 12:
		    v.setVal12(charVal);
		    break;
		case 13:
		    v.setVal13(charVal);
		    break;
		case 14:
		    v.setVal14(charVal);
		    break;
		case 15:
		    v.setVal15(charVal);
		    break;
		case 16:
		    v.setVal16(charVal);
		    break;
		case 17:
		    v.setVal17(charVal);
		    break;
		case 18:
		    v.setVal18(charVal);
		    break;
		case 19:
		    v.setVal19(charVal);
		    break;
		case 20:
		    v.setVal20(charVal);
		    break;
		case 21:
		    v.setVal21(charVal);
		    break;
		case 22:
		    v.setVal22(charVal);
		    break;
		case 23:
		    v.setVal23(charVal);
		    break;
		case 24:
		    v.setVal24(charVal);
		    break;
		case 25:
		    v.setVal25(charVal);
		    break;
		case 26:
		    v.setVal26(charVal);
		    break;
		case 27:
		    v.setVal27(charVal);
		    break;
		case 28:
		    v.setVal28(charVal);
		    break;
		case 29:
		    v.setVal29(charVal);
		    break;
		case 30:
		    v.setVal30(charVal);
		    break;
		case 31:
		    v.setVal31(charVal);
		    break;
		case 32:
		    v.setVal32(charVal);
		    break;
		case 33:
		    v.setVal33(charVal);
		    break;
		case 34:
		    v.setVal34(charVal);
		    break;
		case 35:
		    v.setVal35(charVal);
		    break;
		case 36:
		    v.setVal36(charVal);
		    break;
		case 37:
		    v.setVal37(charVal);
		    break;
		case 38:
		    v.setVal38(charVal);
		    break;
		case 39:
		    v.setVal39(charVal);
		    break;
		case 40:
		    v.setVal40(charVal);
		    break;
		case 41:
		    v.setVal41(charVal);
		    break;
		case 42:
		    v.setVal42(charVal);
		    break;
		case 43:
		    v.setVal43(charVal);
		    break;
		case 44:
		    v.setVal44(charVal);
		    break;
		case 45:
		    v.setVal45(charVal);
		    break;
		case 46:
		    v.setVal46(charVal);
		    break;
		case 47:
		    v.setVal47(charVal);
		    break;
		case 48:
		    v.setVal48(charVal);
		    break;
		case 49:
		    v.setVal49(charVal);
		    break;
		case 50:
		    v.setVal50(charVal);
		    break;
		case 51:
		    v.setVal51(numberVal);
		    break;
		case 52:
		    v.setVal52(numberVal);
		    break;
		case 53:
		    v.setVal53(numberVal);
		    break;
		case 54:
		    v.setVal54(numberVal);
		    break;
		case 55:
		    v.setVal55(numberVal);
		    break;
		case 56:
		    v.setVal56(numberVal);
		    break;
		case 57:
		    v.setVal57(numberVal);
		    break;
		case 58:
		    v.setVal58(numberVal);
		    break;
		case 59:
		    v.setVal59(numberVal);
		    break;
		case 60:
		    v.setVal60(numberVal);
		    break;
		case 61:
		    v.setVal61(numberVal);
		    break;
		case 62:
		    v.setVal62(numberVal);
		    break;
		case 63:
		    v.setVal63(numberVal);
		    break;
		case 64:
		    v.setVal64(numberVal);
		    break;
		case 65:
		    v.setVal65(numberVal);
		    break;
		case 66:
		    v.setVal66(numberVal);
		    break;
		case 67:
		    v.setVal67(numberVal);
		    break;
		case 68:
		    v.setVal68(numberVal);
		    break;
		case 69:
		    v.setVal69(numberVal);
		    break;
		case 70:
		    v.setVal70(numberVal);
		    break;
		case 71:
		    v.setVal71(numberVal);
		    break;
		case 72:
		    v.setVal72(numberVal);
		    break;
		case 73:
		    v.setVal73(numberVal);
		    break;
		case 74:
		    v.setVal74(numberVal);
		    break;
		case 75:
		    v.setVal75(numberVal);
		    break;
		case 76:
		    v.setVal76(dateVal);
		    break;
		case 77:
		    v.setVal77(dateVal);
		    break;
		case 78:
		    v.setVal78(dateVal);
		    break;
		case 79:
		    v.setVal79(dateVal);
		    break;
		case 80:
		    v.setVal80(dateVal);
		    break;
		case 81:
		    v.setVal81(dateVal);
		    break;
		case 82:
		    v.setVal82(dateVal);
		    break;
		case 83:
		    v.setVal83(dateVal);
		    break;
		case 84:
		    v.setVal84(dateVal);
		    break;
		case 85:
		    v.setVal85(dateVal);
		    break;
		case 86:
		    v.setVal86(dateVal);
		    break;
		case 87:
		    v.setVal87(dateVal);
		    break;
		case 88:
		    v.setVal88(dateVal);
		    break;
		case 89:
		    v.setVal89(dateVal);
		    break;
		case 90:
		    v.setVal90(dateVal);
		    break;
		case 91:
		    v.setVal91(dateVal);
		    break;
		case 92:
		    v.setVal92(dateVal);
		    break;
		case 93:
		    v.setVal93(dateVal);
		    break;
		case 94:
		    v.setVal94(dateVal);
		    break;
		case 95:
		    v.setVal95(dateVal);
		    break;
		case 96:
		    v.setVal96(dateVal);
		    break;
		case 97:
		    v.setVal97(dateVal);
		    break;
		case 98:
		    v.setVal98(dateVal);
		    break;
		case 99:
		    v.setVal99(dateVal);
		    break;
		case 100:
		    v.setVal100(dateVal);
		    break;
	  
	  }

  }
  
  public static boolean isBlankRow(List<CanonE001CodeTableDAO.CodeTableColumn> columnList, String [][] cdValValues,int rowIndex){
	  for(int j=0;j<columnList.size();j++){
		  String val=cdValValues[j][rowIndex];
		  if(val!=null && val.trim().length()!=0){
			  return false;
		  }
	  }
	  return true;
  }

  public static String [][] getCDValValues(List<CanonE001CodeTableDAO.CodeTableColumn> columnList, final javax.servlet.http.HttpServletRequest request){
	  String values[][]=new String[columnList.size()][];
	  for(int i=0;i<columnList.size();i++){
		  CanonE001CodeTableDAO.CodeTableColumn col=columnList.get(i); 
		  String resultCol=col.getResultCol();
		  values[i]=request.getParameterValues(resultCol); 
	  }
	  return values;
  }
  
  /*
   * cdValValues= [val,row]
   * 
   * 		----> row
   * 	    |
   * 		|
   * 		V val
   * 
   * 	[
   * 		 [ row1 val1, row2 val1, row3 val1, ...rown val1],
   * 		 [ row1 val2, row2 val2, row3 val2, ...rown val2],
   * 		 [ row1 val3, row2 val3, row3 val3, ...rown val3],
   * 		 [ row1 val4, row2 val4, row3 val4, ...rown val4]
   * 	]
   */
  public static void saveCdValues(String useName, BigDecimal cdID, List<CanonE001CodeTableDAO.CodeTableColumn> columnList, final javax.servlet.http.HttpServletRequest request) throws E001Exception{
	  
	  List<CanonE001CodeTableDAO.CanonE001CodeTabColRec> cdColumns=new ArrayList<CanonE001CodeTableDAO.CanonE001CodeTabColRec>();
	  for(int j=0;j<columnList.size();j++){
		  CanonE001CodeTableDAO.CanonE001CodeTabColRec cdcol=new CanonE001CodeTableDAO.CanonE001CodeTabColRec();
		  cdcol.setResultCol(columnList.get(j).getResultCol());
		  cdcol.setColPrompt(columnList.get(j).getColPrompt());
		  cdColumns.add(cdcol);
	  }
	  
	  String [][] cdValValues=getCDValValues(columnList,request);
  
	  int paramLength=cdValValues.length>0? cdValValues[0].length: 0;
	  List<CanonE001CodeTableDAO.CanonE001CodeTabValRec> cdValues=new ArrayList<CanonE001CodeTableDAO.CanonE001CodeTabValRec>();
	  String cdValIds[]=request.getParameterValues("cd_val_id");
	  for(int rowIndex=0;rowIndex<paramLength;rowIndex++){
		  BigDecimal cdValId=toBigDecimal(cdValIds[rowIndex],false);
		  if(isBlankRow(columnList,cdValValues,rowIndex)){
			  if(cdValId!=null){
				  CanonE001CodeTableDAO.deleteCodeTabVal(useName, cdID, cdValId);
			  }
		  }else{  
			  CanonE001CodeTableDAO.CanonE001CodeTabValRec valRec=new CanonE001CodeTableDAO.CanonE001CodeTabValRec();
			  for(int j=0;j<columnList.size();j++){
				  CanonE001CodeTableDAO.CodeTableColumn col=columnList.get(j); 
				  int colIndex=toInt(col.getResultCol().substring(3));
				  String val=cdValValues[j][rowIndex];
				  setCdValValue(colIndex,val,valRec);
				  valRec.setCdId(cdID);
				  valRec.setValId(cdValId);
				  
			  }
			  cdValues.add(valRec);
		  }
	  }
	  
	  if(cdValues.size()>0){
		  Object result[]=CanonE001CodeTableDAO.createUpdateCodeTabVal(CanonS21SessionValidate.getUserName(), cdID, cdColumns, cdValues);
		  checkErrors(result, 0, 1);
	  }
  }
  
  public static class E001Exception extends Exception{
	    public E001Exception(String message, Throwable cause){
	        super(message,cause);
	    }
	}

	public static void checkResult(Object [] result) throws E001Exception{
	    System.out.println("result is " +result);
	    if(result==null) {
	        Exception cause=CanonE001CodeTableDAO.getException();
	        throw new E001Exception("Database exception.",cause);
	    }
	}

	public static void checkErrors(Object [] result,int error_flag_index,int error_message_index) throws E001Exception{
	    checkResult(result);
	    String error_flag=(String)result[error_flag_index];
	    String error_message=(String)result[error_message_index];
	    System.out.println("error_flag is " +error_flag);
	    System.out.println("error_message is " +error_message);
	    if( "E".equals(error_flag)){
	        throw new E001Exception("Database error.", new Exception(error_message));
	    }
	    if( "W".equalsIgnoreCase(error_flag) && error_message!=null){
	        throw new E001Exception("Data Validation error.", new Exception(error_message));
	    }
	}

	public static boolean getDevFlag(){
		return "Y".equals(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_APP_DEV_FLAG"));
	}
	
	final static Pattern pattern = Pattern.compile("\\r|\\n");	
	/**
	 * return number or lines
	 */
	public static int lineCount(String str){
		if(isEmpty(str))return 0;
		Matcher m= pattern.matcher(str);
		int count = 1;
		while (m.find()){
		    count++;
		}
		return count;
	}

}

