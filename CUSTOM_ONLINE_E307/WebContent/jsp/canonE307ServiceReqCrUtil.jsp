<%@page import="com.canon.apps.servreq.beans.CanonE307HardHoldInfoRec"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestSearchDao"%>
<%@page import="com.canon.apps.servreq.beans.CanonE307DebriefItemLov"%>
<%@page import="java.util.List"%>
<%@page import="com.canon.apps.servreq.util.CanonE307DebriefAPIUtil"%>
<%@page import="java.io.File"%>
<%@page import="com.canon.apps.servreq.beans.CanonE307ServiceReqRmdDtlsRec"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceReqCreateDao"%>
<%@ page import="com.canon.apps.servreq.dao.CanonE307DebriefDetailsDAO" %>  
<%@page import="java.util.ArrayList"%>


<%@page import="java.io.OutputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStream"%>

<%@page import="javax.mail.Folder"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.Store"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="canon.apps.common.CanonEmailUtil"%>
<%@page import="javax.activation.DataSource"%>
<%@page import="canon.apps.common.CanonCustomProfile"%>

<%
CanonCommonUtil util = new CanonCommonUtil();
CanonE307ServiceReqCreateDao  crDao = new CanonE307ServiceReqCreateDao();

String reqType=util.checkNull(request.getParameter("reqType"));


if(reqType.equalsIgnoreCase("pb")) {  // Problem  
	        String attr=util.checkNull(request.getParameter("returnAttr"));
			String mdl=util.checkNull(request.getParameter("mdl"));
			String type=util.checkNull(request.getParameter("type"));
			String desc=util.checkNull(request.getParameter("desc"));
			System.out.println("attr : "+ attr);
			
			StringBuffer sb = new StringBuffer("");
			sb.append("<option value=");
			sb.append("\" \">");
			sb.append("Select</option>");

			 ArrayList<String> alProb = crDao.getProbDetails(attr, mdl, type, desc);
			 
			 for(String str: alProb){ 
			      sb.append("<option value=");
			      sb.append("\""+str+"\">");
			      sb.append(str);
			      sb.append("</option>");
			   }
		/*	 if ("problemCode".equals(attr)){
				  for(String str: alProb){ 
				      sb.append("<option value=");
				      sb.append("\""+str.split("-")[0]+"\">");
				      sb.append(str);
				      sb.append("</option>");
				   }
			 }else{
				 for(String str: alProb){ 
				      sb.append("<option value=");
				      sb.append("\""+str+"\">");
				      sb.append(str);
				      sb.append("</option>");
				   }
			 } */

			 //util.logMsg(false, "canonE307ServiceReqCrUtil.jsp", sb.toString());
			 response.getWriter().write(sb.toString());

} else if (reqType.equalsIgnoreCase("pbCd")){  // Change Reason & remedy for Bill Code
	  String pbCode=util.checkNull(request.getParameter("pbCode"));
	  String mdl=util.checkNull(request.getParameter("mdl"));
		
	  ArrayList<CanonE307ServiceReqRmdDtlsRec>  arList  = crDao.getRmdyDetails(mdl, pbCode);
	  String remedy ="";
	  String narrative="";
	  String docNm = "";
	  if(arList.size()>0){
		  CanonE307ServiceReqRmdDtlsRec rec = arList.get(0);
		  remedy= util.checkNull(rec.getSvcRmdTxt());
		  narrative= util.checkNull(rec.getSvcPblmNarrTxt());
		  docNm= util.checkNull(rec.getDocName());
	  }
		System.out.println("remedy : "+ remedy+"narrative : "+narrative);
		
	//  remedy ="htpps://emanage.csa.canon.com/OA_HTML/11123_002JAM.pdf";	
	//  narrative="Our experience shows that we may immediately reduce your downtime by providing you few quick and simple instruction.May I proceed? If no:Will you review the suggested steps first before making a final decision? I am confident you will find them very easy and user friendly.";	
	  String res = "{\"remedy\":\""+remedy +"\" , \"narrative\" : \""+narrative+"\", \"docNm\" : \""+docNm+"\"}";
	  System.out.println("res: "+ res);
	  response.getWriter().write(res);
} else if (reqType.equalsIgnoreCase("xml")){  // Creation Channel

     HashMap<String, String>  hMap = new HashMap<String, String>();
     String eo=util.checkNull(request.getParameter("eo"));
     String sc=util.checkNull(request.getParameter("sc"));
     String wc=util.checkNull(request.getParameter("wc"));
     String ord = util.checkNull(request.getParameter("ord"));
 	 
   //  System.out.println("eo : "+ eo + " sc : "+sc+" wc : "+wc+" ord : "+ord);
     if(eo.trim().length()>0)
     hMap.put("entityObject",eo);
 	 
 	 if(sc.trim().length()>0)
 	 hMap.put("SelectClause",sc);
 	
 	 if(wc.trim().length()>0)
 	 hMap.put("WhereClause",wc);
 	 
 	 if(ord.trim().length()>0)
 	 hMap.put("OrderByClause",ord); 	 
 	 
 	 
 
 	 String strXml = util.genXmlInput(hMap);
 	 
 	// util.logMsg(false, "canonE307ServiceReqCrUtil.jsp", strXml);
 	 
	 String resXML= crDao.getXml(strXml);
	// System.out.println("resXML : "+ resXML);
	 if(resXML!=null){
	    response.getWriter().write(resXML.trim());
	 }else{
		 response.getWriter().write("");
	 }
}else if (reqType.equalsIgnoreCase("email")){  // Change Reason & remedy for Bill Code
	  String res="Y";
	  String emailId=util.checkNull(request.getParameter("emailId"));
	  final String rmd=util.checkNull(request.getParameter("rmd"));
	  emailId = emailId.trim();
	  System.out.println("rmd: "+ rmd+" emailId: "+emailId);
/* 	  rmd = "/e307/pdf/ADVC5000_0205JAM.pdf";
	  System.out.println("rmd: "+ rmd+" emailId: "+emailId); */
	  try{ 
		 	final ServletContext  sc = request.getServletContext();
			
			DataSource	ds=	new DataSource(){
				@Override
				public String getContentType() {
					 return "application/pdf";
				}
				@Override
				public InputStream getInputStream() throws IOException {
					// http://bugs.java.com/bugdatabase/view_bug.do?bug_id=4267294
					// JDK-4267294 : InputStream must be created in getInputStream() of the DataSource, not passed in
					//return sc.getResourceAsStream("/e307/jsp/"+rmd);
					return sc.getResourceAsStream(rmd);
				}
				@Override
				public String getName() {
					return rmd;
				}
				@Override
				public OutputStream getOutputStream() throws IOException {
					return null;
				}
			};
			String instanceAppnd = "";
			try {
				instanceAppnd = CanonCommonUtil.checkNull(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_EMAIL_SUBJECT_INIT"));
			} catch (Exception exception) {
				System.err
						.println("Error occured from getting CANON_CUSTOM_EMAIL_SUBJECT_INIT Val "
								+ exception.getMessage());
			} 
			String sbjct = rmd.substring(10);
			System.out.println("Before createMultiPartEmail.."+sbjct);
			CanonEmailUtil.createMultiPartEmail()
			.attach(ds,sbjct,rmd)
			.setSubject(instanceAppnd + sbjct)
			.setMsg("Attached PDF")
			.addTo(emailId)
		//	.setFrom(fromEmail, fromName)
			.send();

	  }catch(Exception e){
		util.logMsg(false, "canonE307ServiceReqCrUtil.jsp", "[Email ERROR] "+e.getMessage().toString());
		res="N";
	  }
	  response.getWriter().write(res);
	  
} else if (reqType.equalsIgnoreCase("partsSrch")){ 
	 String strTaskNumber=util.checkNull(request.getParameter("taskNumber"));
	 String strPartItem = util.checkNull(request.getParameter("partItem"));
		util.logMsg(false, "canonE307ServiceReqCrUtil.jsp inside parts: ", strTaskNumber);
	 CanonE307DebriefDetailsDAO objDeb = new CanonE307DebriefDetailsDAO();
	 StringBuffer sb = new StringBuffer("");
	 sb.append("<option value=");
	 sb.append("\" \">");
	 sb.append("Select</option>");
	 
	 Object[] resultParts = objDeb.getItemDetailsLov("PARTS","", -1, -1, strTaskNumber);
		List lstPartItems=new ArrayList();
		String strSelect="";
		if(resultParts != null && resultParts.length>1){
			lstPartItems=(ArrayList)resultParts[0];
			if(lstPartItems!=null && lstPartItems.size()>0){
				System.out.println("parts lov: "+ lstPartItems.size());
				for(int i=0;i<lstPartItems.size();i++){
					CanonE307DebriefItemLov partBeanObj = (CanonE307DebriefItemLov)lstPartItems.get(i);	
					if(partBeanObj.getStrItmNm().equals(strPartItem)){
						strSelect = "selected";
					}else{
						strSelect="";
					}
				 sb.append("<option value=");
			      sb.append("\""+partBeanObj.getStrItmNm()+" - "+partBeanObj.getStrItmDesc()+ "\">");
			      sb.append(partBeanObj.getStrItmNm()+" - "+partBeanObj.getStrItmDesc());
			      sb.append("</option>");
				}
			}
		}
		// util.logMsg(false, "canonE307ServiceReqCrUtil.jsp parts: ", sb.toString());
		 response.getWriter().write(sb.toString());
}else if (reqType.equalsIgnoreCase("expense")){ 

		util.logMsg(false, "canonE307ServiceReqCrUtil.jsp inside expense: ", "expense ");
	 CanonE307DebriefDetailsDAO objDeb = new CanonE307DebriefDetailsDAO();
	 StringBuffer sb = new StringBuffer("");
	 sb.append("<option value=");
	 sb.append("\" \">");
	 sb.append("Select</option>");
	 
	 Object[] resultExpns = objDeb.getItemDetailsLov("EXPENSE","", -1, -1, "");
		List lstExpenseItems=new ArrayList();
		if(resultExpns != null && resultExpns.length>1){
			lstExpenseItems=(ArrayList)resultExpns[0];
			if(lstExpenseItems!=null && lstExpenseItems.size()>0){
				System.out.println("parts lov: "+ lstExpenseItems.size());
				for(int i=0;i<lstExpenseItems.size();i++){
					CanonE307DebriefItemLov expBeanObj = (CanonE307DebriefItemLov)lstExpenseItems.get(i);	
				 sb.append("<option value=");
			      sb.append("\""+expBeanObj.getStrItmNm()+" - "+expBeanObj.getStrItmDesc()+"\">");
			      sb.append(expBeanObj.getStrItmNm()+" - "+expBeanObj.getStrItmDesc());
			      sb.append("</option>");
				}
			}
		}
	//	 util.logMsg(false, "canonE307ServiceReqCrUtil.jsp expense: ", sb.toString());
		 response.getWriter().write(sb.toString());
}else if (reqType.equalsIgnoreCase("dbUom")){ 
	
	String strSource=util.checkNull(request.getParameter("source"));
	String strTaskNumber=util.checkNull(request.getParameter("taskNumber"));
	String strMdseCd = util.checkNull(request.getParameter("mdseCd"));
	
//	util.logMsg(false, "canonE307Servi//ceReqCrUtil.jsp dbUom", " strSource: "+ strSource+" strFsr: "+strFsr+" strMdseCd: "+strMdseCd);
 	CanonE307DebriefDetailsDAO objDeb = new CanonE307DebriefDetailsDAO();

 
 Object[] debriefDtls = objDeb.getItemDetailsLov(strSource,strMdseCd, -1, -1, strTaskNumber);
	List lstUomCnt=new ArrayList();
	String itmCnt = "";
	String strUom = "";
	if(debriefDtls != null && debriefDtls.length>1){
		lstUomCnt=(ArrayList)debriefDtls[0];
		if(lstUomCnt!=null && lstUomCnt.size()>0){
			System.out.println("parts lov: "+ lstUomCnt.size());
			for(int i=0;i<lstUomCnt.size();i++){
				CanonE307DebriefItemLov expBeanObj = (CanonE307DebriefItemLov)lstUomCnt.get(i);	
				itmCnt = expBeanObj.getStrItmCnt();
				strUom = expBeanObj.getStrUom();
			}
		}
	}
	 String res = "{\"itmCnt\":\""+itmCnt +"\" , \"strUom\" : \""+strUom+"\"}";
	 // System.out.println("res: "+ res);
	  response.getWriter().write(res);

}else if (reqType.equalsIgnoreCase("partsReg")){ 
	 String strTaskNumber=util.checkNull(request.getParameter("taskNumber"));
		util.logMsg(false, "canonE307ServiceReqCrUtil.jsp inside parts: ", strTaskNumber);
	 CanonE307DebriefDetailsDAO objDeb = new CanonE307DebriefDetailsDAO();
	 StringBuffer sb = new StringBuffer("");
	 sb.append("<option value=");
	 sb.append("\" \">");
	 sb.append("Select</option>");
	 
	 Object[] resultParts = objDeb.getItemDetailsLov("PARTS","", -1, -1, strTaskNumber);
		List lstPartItems=new ArrayList();
		if(resultParts != null && resultParts.length>1){
			lstPartItems=(ArrayList)resultParts[0];
			if(lstPartItems!=null && lstPartItems.size()>0){
				System.out.println("parts lov: "+ lstPartItems.size());
				for(int i=0;i<lstPartItems.size();i++){
					CanonE307DebriefItemLov partBeanObj = (CanonE307DebriefItemLov)lstPartItems.get(i);	
				 sb.append("<option value=");
			      sb.append("\""+partBeanObj.getStrItmNm()+" - "+partBeanObj.getStrUom()+" - "+partBeanObj.getStrItmCnt()+"\">");
			      sb.append(partBeanObj.getStrItmNm()+" - "+partBeanObj.getStrItmDesc());
			      sb.append("</option>");
				}
			}
		}
		// util.logMsg(false, "canonE307ServiceReqCrUtil.jsp parts: ", sb.toString());
		 response.getWriter().write(sb.toString());
}else if (reqType.equalsIgnoreCase("notif")){  // Change Reason & remedy for Bill Code
	  String res="Y";
	  String retVal = "";
	  String serialNumber=util.checkNull(request.getParameter("serialNumber"));
	  String svcMachMstrPk = util.checkNull(request.getParameter("machPk"));
	  serialNumber = serialNumber.trim();

	  CanonE307ServiceRequestSearchDao dao = new CanonE307ServiceRequestSearchDao();
	  CanonE307HardHoldInfoRec hhBean = dao.getNotigMngrDtls(serialNumber, svcMachMstrPk);
	  String collectnEmailAddr = util.checkNull(hhBean.getStrCEmailAddress());
	  System.out.println("collectnEmailAddr: "+collectnEmailAddr+" serialNumber: "+serialNumber+" svcMachMstrPk: "+svcMachMstrPk);
	  
      if(collectnEmailAddr!=null && collectnEmailAddr.length()>0){
			try {
				
				StringBuffer  sb= new StringBuffer();
				sb.append("The following customer called and attempted to create Service Call but is on a Hard Credit Hold. \n");
				sb.append("The Call was not created. Please call the customer. \n\n\n");
				sb.append("Customer Name  				: " + util.checkNull(hhBean.getStrCustomerName()) +"\n");
				sb.append("Address	      			  : " + util.checkNull(hhBean.getStrCustAddress()) +"\n");
				sb.append("City, State, Postal Code	   : " + util.checkNull(hhBean.getStrAttribute4()) +"\n");
				sb.append("Contact Name   				: " + util.checkNull(hhBean.getStrContactName()) +"\n");
				sb.append("Contact#       				: " + util.checkNull(hhBean.getStrContTelNum()) +"\n");
				sb.append("Model	      				: "+ util.checkNull(hhBean.getStrModel()) +"\n");
				sb.append("Serial#         			   : "+ serialNumber+"\n"); 
				
				//System.out.println("eMail Body: "+sb.toString());
				
				  try{ 
					 	final ServletContext  sc = request.getServletContext();
						
						String instanceAppnd = "";
						try {
							instanceAppnd = CanonCommonUtil.checkNull(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_EMAIL_SUBJECT_INIT"));
						} catch (Exception exception) {
							System.err
									.println("Error occured from getting CANON_CUSTOM_EMAIL_SUBJECT_INIT Val "
											+ exception.getMessage());
						} 
						String sbjct = "Service Call is not created - " + hhBean.getStrCustomerName();
						System.out.println("Before createMultiPartEmail.."+sbjct);
						CanonEmailUtil.createMultiPartEmail()
						//.attach(ds,sbjct,rmd)
						.setSubject(instanceAppnd + sbjct)
						.setMsg(sb.toString())
						.addTo(collectnEmailAddr)
					//	.setFrom(fromEmail, fromName)
						.send();
						 System.out.println("After Collection Manager email sent");
							if("Y".equals(res)){
								retVal = dao.updateNotifFlg(serialNumber, collectnEmailAddr, "","");
								System.out.println("Update Flag retVal: " + retVal);
							}
				  }catch(Exception e){
					util.logMsg(false, "canonE307ServiceReqCrUtil.jsp", "[Email ERROR] "+e.getMessage().toString());
					res="N";
				  }
				
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
	  	}
	  	String managerEmail = util.checkNull(hhBean.getStrTextEmailAddrs());
	  	System.out.println("managerEmail: "+managerEmail);
	  	if(managerEmail!=null && managerEmail.length()>0){
	  		StringBuffer  sb= new StringBuffer();
	  		
	  		sb.append(util.checkNull(hhBean.getStrCustomerName()) + " attempted to place a service call but the account is on Credit Hard Hold. No service call was created for this account.\n");
	  				
	  		//sb.append("As the Service Manager for this account you are expected to reach out to this customer immediately. Please inform the customer that their account is on HOLD and we are unable to complete their service request. A credit representative / collector will be reaching out the next business day to discuss options. Please apology for the inconvenience. \n");
	  		sb.append("Please contact and apologize for the inconvenience. \n");
	  		sb.append("Customer Name: "+ util.checkNull(hhBean.getStrCustomerName()) +"\n");
			sb.append("Address: "+ util.checkNull(hhBean.getStrCustAddress()) +"\n");
			sb.append("City, State, Postal Code: "+ util.checkNull(hhBean.getStrAttribute4()) +"\n");
			sb.append("Contact Name: "+ util.checkNull(hhBean.getStrContactName()) +"\n");
			sb.append("Contact#: "+ util.checkNull(hhBean.getStrContTelNum()) +"\n");
			sb.append("Serial#: "+ serialNumber+"\n"); 
			//sb.append("Model	     			: "+ util.checkNull(hhBean.getStrModel()) +"\n");
			
			  try{ 
				 	final ServletContext  sc = request.getServletContext();
					
					String instanceAppnd = "";
					try {
						instanceAppnd = CanonCommonUtil.checkNull(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_EMAIL_SUBJECT_INIT"));
					} catch (Exception exception) {
						System.err
								.println("Error occured from getting CANON_CUSTOM_EMAIL_SUBJECT_INIT Val "
										+ exception.getMessage());
					} 
					String sbjct = ""; // Duty Manager, machine Manager
					System.out.println("Before createMultiPartEmail.."+sbjct);
					CanonEmailUtil.createMultiPartEmail()
					//.attach(ds,sbjct,rmd)
				//	.setSubject(instanceAppnd + sbjct)
					.setMsg(sb.toString())
					.addTo(hhBean.getStrTextEmailAddrs())
				//	.setFrom(fromEmail, fromName)
					.send();
					System.out.println("After Duty Manager email sent");
			  }catch(Exception e){
				util.logMsg(false, "canonE307ServiceReqCrUtil.jsp", "[Email ERROR] "+e.getMessage().toString());
				res="N";
			  }
			
			//System.out.println("Duty Manager retVal before updateNotifFlg: "+ retVal);
			if("Y".equals(res)){
				retVal = dao.updateNotifFlg(serialNumber, "", hhBean.getStrTextEmailAddrs(), "");
				System.out.println("Duty ManagerUpdate Flag retVal: " + retVal);
			}
	  
	  response.getWriter().write(res);
	  }
	  	String machMngrEmail = util.checkNull(hhBean.getStrAttribute3());
	  	System.out.println("machine manager Email: "+machMngrEmail);
	  	if(machMngrEmail!=null && machMngrEmail.length()>0){
	  		
	  		StringBuffer  sb= new StringBuffer();
	  		
	  		sb.append(util.checkNull(hhBean.getStrCustomerName()) + " attempted to place a service call but the account is on Credit Hard Hold. No service call was created for this account.\n");
	  				
	  		//sb.append("As the Service Manager for this account you are expected to reach out to this customer immediately. Please inform the customer that their account is on HOLD and we are unable to complete their service request. A credit representative / collector will be reaching out the next business day to discuss options. Please apology for the inconvenience. \n");
	  		sb.append("Please contact and apologize for the inconvenience. \n");
	  		sb.append("Customer Name: "+ util.checkNull(hhBean.getStrCustomerName()) +"\n");
			sb.append("Address: "+ util.checkNull(hhBean.getStrCustAddress()) +"\n");
			sb.append("City, State, Postal Code: "+ util.checkNull(hhBean.getStrAttribute4()) +"\n");
			sb.append("Contact Name: "+ util.checkNull(hhBean.getStrContactName()) +"\n");
			sb.append("Contact#: "+ util.checkNull(hhBean.getStrContTelNum()) +"\n");
			sb.append("Serial#: "+ serialNumber+"\n"); 
			//sb.append("Model	     			: "+ util.checkNull(hhBean.getStrModel()) +"\n");
			
			  try{ 
				 	final ServletContext  sc = request.getServletContext();
					
					String instanceAppnd = "";
					try {
						instanceAppnd = CanonCommonUtil.checkNull(CanonCustomProfile.getUserProfileValue("CANON_CUSTOM_EMAIL_SUBJECT_INIT"));
					} catch (Exception exception) {
						System.err
								.println("Error occured from getting CANON_CUSTOM_EMAIL_SUBJECT_INIT Val "
										+ exception.getMessage());
					} 
					String sbjct = ""; // Duty Manager, machine Manager
					System.out.println("Before sending machine manager email");
					CanonEmailUtil.createMultiPartEmail()
					//.attach(ds,sbjct,rmd)
					//.setSubject(instanceAppnd + sbjct)
					.setMsg(sb.toString())
					.addTo(hhBean.getStrAttribute3())
				//	.setFrom(fromEmail, fromName)
					.send();
					System.out.println("After machine Manager email sent");
			  }catch(Exception e){
				util.logMsg(false, "canonE307ServiceReqCrUtil.jsp", "[Email ERROR] "+e.getMessage().toString());
				res="N";
			  }
			
			if("Y".equals(res)){
				retVal = dao.updateNotifFlg(serialNumber, "", "", hhBean.getStrAttribute3());
				System.out.println("Duty ManagerUpdate Flag retVal: " + retVal);
			}
	  
	  response.getWriter().write(res);
	  }	  	
	  
}
 



%>