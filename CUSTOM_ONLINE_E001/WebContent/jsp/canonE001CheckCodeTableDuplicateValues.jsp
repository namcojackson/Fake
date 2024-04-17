<%@page import="com.canon.apps.e001.CanonE001CodeTableDAO.CodeTableInfo"%>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.canon.apps.e001.CanonE001CodeTableDAO"%>
<%@page import="com.canon.apps.e001.CanonE001CommonUtil"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%@page import="java.util.Date"%>
<%@page import="static com.canon.apps.e001.CanonE001CommonUtil.checkResult"%>
<%@page import="static com.canon.apps.e001.CanonE001CommonUtil.checkErrors"%>
<%@page import="static com.canon.apps.e001.CanonE001CommonUtil.E001Exception"%>


<%
System.out.println("checkcode table duplicate values ");
        CanonE001CodeTableDAO.CodeTableInfo codeTableInfo=null;
	    List<CanonE001CodeTableDAO.CodeTableColumn> columnList=null;
	    CanonE001CodeTableDAO cTDao=null;
        BigDecimal cdID=CanonE001CommonUtil.toBigDecimal(request.getParameter("cd_id"), false);
        System.out.println("E001-1 ");
		Object[] result=CanonE001CodeTableDAO.getCodeTabAndCols(CanonS21SessionValidate.getUserName(), cdID);;
		System.out.println("E001-2 ");
        codeTableInfo=(CanonE001CodeTableDAO.CodeTableInfo)CanonE001CommonUtil.first((List)CanonE001CommonUtil.first(result));
        System.out.println("E001-3 ");
        columnList=(List<CanonE001CodeTableDAO.CodeTableColumn>)CanonE001CommonUtil.second(result);
        System.out.println("E001-4 ");

		 List<CanonE001CodeTableDAO.CanonE001CodeTabColRec> cdColumns=new ArrayList<CanonE001CodeTableDAO.CanonE001CodeTabColRec>();
		 System.out.println("E001-5 ");
		  for(int j=0;j<columnList.size();j++){
			  CanonE001CodeTableDAO.CanonE001CodeTabColRec cdcol=new CanonE001CodeTableDAO.CanonE001CodeTabColRec();
			  cdcol.setResultCol(columnList.get(j).getResultCol());
			  cdcol.setColPrompt(columnList.get(j).getColPrompt());
			  cdColumns.add(cdcol);
		  }
		  
		  String values[][]=new String[columnList.size()][];
		  for(int i=0;i<columnList.size();i++){
			  CanonE001CodeTableDAO.CodeTableColumn col=columnList.get(i); 
			  String resultCol=col.getResultCol();
			  values[i]=request.getParameterValues(resultCol);
		  }
		  String [][] cdValValues=CanonE001CommonUtil.getCDValValues(columnList,request);
  
		  int paramLength=cdValValues.length>0? cdValValues[0].length: 0;
		  List<CanonE001CodeTableDAO.CanonE001CodeTabValRec> cdValues=new ArrayList<CanonE001CodeTableDAO.CanonE001CodeTabValRec>();
		  String cdValIds[]=request.getParameterValues("cd_val_id");
		  for(int rowIndex=0;rowIndex<paramLength;rowIndex++){
			  if(CanonE001CommonUtil.isBlankRow(columnList,cdValValues,rowIndex)){
				  System.out.println("blank row " + rowIndex);
			  }else{  
				  System.out.println("row " + rowIndex);
				  BigDecimal cdValId=CanonE001CommonUtil.toBigDecimal(cdValIds[rowIndex],false);
				  CanonE001CodeTableDAO.CanonE001CodeTabValRec valRec=new CanonE001CodeTableDAO.CanonE001CodeTabValRec();
				  for(int j=0;j<columnList.size();j++){
					  CanonE001CodeTableDAO.CodeTableColumn col=columnList.get(j); 
					  int colIndex=CanonE001CommonUtil.toInt(col.getResultCol().substring(3));
					  String val=cdValValues[j][rowIndex];
					  System.out.println("VAL"+colIndex+"=>" +val);
					  CanonE001CommonUtil.setCdValValue(colIndex,val,valRec);
					  valRec.setCdId(cdID);
					  valRec.setValId(cdValId);
					  
				  }
				  cdValues.add(valRec);
			  }
		  }
	  
		  System.out.println("cdValues.size() " + cdValues.size());
	  if(cdValues.size()>0){
		result =CanonE001CodeTableDAO.checkDuplicateValues(CanonS21SessionValidate.getUserName(), cdID, cdColumns, cdValues);
	  }
	  

      if (result == null )	  {
		  result = new Object[]{"S",""};
	  }
	  System.out.println("e001 check duplicate result "+result);
	  String error_flag=(String)result[0];
	  String error_message=(String)result[1];
	  System.out.println("e001 check duplicate string results- flag "+error_flag);
	  System.out.println("e001 check duplicate string results- flag "+error_message);
	    
	  response.getWriter().write(error_flag+"~"+error_message+"");
       
%>