<%@page import="com.canon.oracle.custapp.util.CanonE193CreditRebillAPIUtil"%>
<%@page import="com.canon.oracle.custapp.custinq.beans.*"%>
<%@page import="canon.apps.common.CanonConstants"%>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@page import="com.canon.oracle.custapp.custinq.dao.*"%>
<%@ page import="com.canon.oracle.*" %>  
<%@ page import="java.text.*"%> 
<%@ page import="java.util.*"%>
<%@page import="java.util.Date"%>
<%@page import="parts.dbcommon.EZDDBCICarrier"%> 
<%@page import="java.util.ArrayList"%>

    
 
<%   
String pageTitle="Customer Care Credit Rebill: Create";
String pageHdr="Customer Care";
  

 ArrayList<String> menuList = new ArrayList<String>();
 menuList.add("MENU2:N:View Escalations:N");
 menuList.add("MENU3:N:Escalate:escalate();"); 
 menuList.add("MENU4:N:View Entitlement:viewEntitlement();");
 menuList.add("sp:btn:Scratch Pad:opnSCPad();");
    
    
%>  
 
<%
 
 
 // for copy FSR 

  String errorMsg="";
  CanonE193CreditRebillAPIUtil apiUti= new CanonE193CreditRebillAPIUtil();
  
    
   String[] resArr =  apiUti.createCreditRebill(request); //UC
   String errCd =resArr[0];
   String retMsg =resArr[1];
   
  
    //String[] resArr = {"Y","11111"};
	  /*String rFlg= util.checkNull(resArr[0]);
	  String rFlg=util.
	  if(rFlg.equalsIgnoreCase("Y")){
		  String fsrNum =resArr[1]; 
      }else{
		  errorMsg=resArr[1];
	  }*/
	    		
%>
 Error Code #  : <%=errCd%> <br>
 API Return Code #  : <%=retMsg%> 

  
</script>
</form>
</div>

