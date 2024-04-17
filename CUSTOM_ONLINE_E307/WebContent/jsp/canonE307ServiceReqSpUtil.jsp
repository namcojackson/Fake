
<%@page import="com.canon.common.CanonCommonUtil"%>

<%

CanonCommonUtil util = new CanonCommonUtil();

String srtchMsg =  util.checkNull(request.getParameter("scratchPad"));

String spAction =  util.checkNull(request.getParameter("spAction"));


if(spAction.equalsIgnoreCase("clear")){
	  request.getSession().setAttribute("scratchPad", "");
}else{
	
	if(srtchMsg.trim().length()>0){
		  request.getSession().setAttribute("scratchPad", srtchMsg);
	 }else{
		   Object srtchMsgObj= request.getSession().getAttribute("scratchPad");
		   if(srtchMsgObj!=null)
			   srtchMsg =(String)srtchMsgObj;
	 } 

}
 
 
%>