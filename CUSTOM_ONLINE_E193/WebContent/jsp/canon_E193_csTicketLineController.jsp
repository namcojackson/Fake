<!-- $Header: canon_E193_csTicketLineController.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |  canon_E193_csTicketLineController.jsp - line Controller
 |   
 | DESCRIPTION
 |   For a given criteria get records and forwards it to respective jsp page.
 |
 | AUTHOR
 |  Subba 
 |
 | CREATION DATE
 |  09/21/2005
 |
 | HISTORY
 | DATE         WHO                 WHY
 | 18-Dec-2006    Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes
 | 13-Apr-2009    Chandra Sekhar     ITG # 176718
 | 02-Oct-2009    Naveen Khandelwal  MW Project Changes
 | 05-Feb-2013	  Hema 				 ITG#434465 : To handle Non-Serialized accessories.
 | 29-Jan-2016    Mangala Shenoy	 Modified for S21 changes
 +=======================================================================--%>
<%@page language="java" %> 

<%@page import ="java.util.*" %>
<!-- ITG # 176718 Changes -->
<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>
<!-- ITG # 176718 Changes -->
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_OKSBillingDtlsObj" %>
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_IssueListObj" %>
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_InvoiceLinesObj" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Invoice" id="objInvDao" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_NonBillingIssue" id="objNonBillDao" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_IssueList" id="objIssueDao" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_BillingIssue" id="objBillDao" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objIssueList" scope="request" />
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objIBList" scope="request" />
<jsp:setProperty name="objIBList" property="*" />
<jsp:setProperty name="objIssueList" property="*" />
<%
    try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%
        String strPageFrom = request.getParameter("hPageFrom");
        
        //Get Org ID
        int iOrgId = objCiSession.getOrgId();
        
        // get userId
        String iUserId = objCiSession.getUserId();
        
        // get resource id
        //Start changes for S21 by Mangala
       // int iResourceId = objCiSession.getResourceId();
        String iResourceId = objCiSession.getResourceId();		
      //End changes for S21 by Mangala
        // forward to next page
        String strNextPage = request.getParameter("nextPage");
        
        String strControlForm = request.getParameter("controlForm");
        // get ticket no
        int iTicketId = 0;
        int iLineId = 0;
        String strTicketId = request.getParameter("iTicketId")==null?"":request.getParameter("iTicketId");
        if(!"".equals(strTicketId)) iTicketId = Integer.parseInt(strTicketId);

        String strLineId = request.getParameter("iLineId")==null?"":request.getParameter("iLineId");
        if(!"".equals(strLineId)) iLineId = Integer.parseInt(strLineId);
        Canon_E193_TicketLinesObj objLine = null;
        String strPurchageOrd = "", strTaxNewValue = "", strTaxExamption = "", strPONewValue = "", strFreightNewVal = "", strTaxOldValue = "";
        ArrayList ibList = new ArrayList();
        if(strControlForm == null || "".equals(strControlForm) || "null".equalsIgnoreCase(strControlForm)) {
            if(!"".equals(strLineId)) {
                objLine = objNonBillDao.getNonBillLine(iOrgId, iTicketId, iLineId);
            }else{
                objLine = new Canon_E193_TicketLinesObj();
            }
        }else{ // for billing
            if(!"".equals(strLineId)) {
                objLine = objBillDao.getBillLine(iOrgId, iTicketId, iLineId);
                ArrayList subList = objBillDao.getBillSubLine(iOrgId, iTicketId, iLineId);
                Canon_E193_TicketSubLinesObj objSL = new Canon_E193_TicketSubLinesObj();
                if(subList != null && subList.size() > 0) {
                    objSL = (Canon_E193_TicketSubLinesObj)subList.get(0);
                }

                if(objLine != null) {
                    if("TAX".equalsIgnoreCase(objLine.getCatCode())) {
                        strTaxNewValue = objSL.getNewValue()==null?"":objSL.getNewValue();
                        strTaxExamption = objSL.getTaxExamption()==null?"":objSL.getTaxExamption();
                        strTaxOldValue = objSL.getCurrentValue()==null?"":objSL.getCurrentValue();
                        strNextPage = "canon_E193_csBTaxIssue.jsp";
                    }else if("PO".equalsIgnoreCase(objLine.getCatCode())) {
                        strPurchageOrd = objSL.getCurrentValue()==null?"":objSL.getCurrentValue();
                        strPONewValue = objSL.getNewValue()==null?"":objSL.getNewValue();
                        strNextPage = "canon_E193_csBPOIssue.jsp";
                    }else if("OTHERS".equalsIgnoreCase(objLine.getCatCode())) {
                        strNextPage = "canon_E193_csBOthersIssue.jsp";
                    }else if("FREIGHT".equalsIgnoreCase(objLine.getCatCode())) {
                        strFreightNewVal = objSL.getNewValue()==null?"":objSL.getNewValue();
                        strNextPage = "canon_E193_csBFreightIssue.jsp";
                    }else if("BILLING".equalsIgnoreCase(objLine.getCatCode())) {
                        Canon_E193_IssueListObj issueListObj = null;
                        String strInv = request.getParameter("invoiceNumber");
                        String strInvSource = request.getParameter("InvSource")==null?"":request.getParameter("InvSource");


      
                        /* ITG# 73987 : Begin */    
                        /* ITG # 176718 Changes */
                        //String strRegionCode = (String)objCiSession.getRegionCode();    
                        String strRegionCode = Canon_E193_RegionCodeDAO.getRegionCode(strInv);
                        /* ITG # 176718 Changes */
                        /* ITG# 73987 : End */

                        ArrayList alIssueList = objIssueDao.getIssueList(iOrgId,strRegionCode, null, objLine.getCatId());
                        if("CONTRACTS".equalsIgnoreCase(strInvSource)) {
                            ibList = objInvDao.getOksBillingDtls(iOrgId, strInv);
                            Canon_E193_OKSBillingDtlsObj objOKS = null;
                            // MW Changes Start: Abhay N
							String strSerialNo = "", strProductNo="", strOKSerialNo = "", strModel = "", strIssueCateCode = "", strOKSFleetSerialNo = "", strOKSInvoiceType = "", strFleetContract = "", strObjectType = "", strIssueInvoiceType = "", strProductNumber="";
                            String strCounterName = "", strOKSCounterName = "";
                            // MW Changes End: Abhay N
							int iSubCatId = 0, iIssueCatId = 0; 
                            boolean isBreak = false;
                            
							//System.out.println("!!!!!!!Ticket line Controller ibList " + ibList);
							//System.out.println("!!!!!!!Ticket line Controller subList " + subList);
							//System.out.println("!!!!!!!Ticket line Controller alIssueList " + alIssueList);
							
							for(int i = 0; i<subList.size(); i++) {
                                objSL = (Canon_E193_TicketSubLinesObj)subList.get(i);
                                strSerialNo = objSL.getSerialNo()==null?"":objSL.getSerialNo();
								strProductNo = objSL.getProductNumber()==null?"":objSL.getProductNumber();
                                iSubCatId = objSL.getCatId();
								// MW Changes Start: Abhay N
								strObjectType = objSL.getObjectType();
								// MW Changes End: Abhay N
                                isBreak = false;
                                
                                strCounterName = objSL.getAttribute3();
                                // MW Changes Start: Abhay N
								if(strObjectType.equalsIgnoreCase("USAGE_LINE_ID")||strObjectType.equalsIgnoreCase("PRICING_LINE_ID"))
								    strIssueInvoiceType = "USAGE";
							    else if(strObjectType.equalsIgnoreCase("BASE_LINE_ID")) 
								    strIssueInvoiceType = "BASE";
								// MW Changes End: Abhay N
									
								for(int j=0; j<ibList.size(); j++) {
                                    objOKS = (Canon_E193_OKSBillingDtlsObj)ibList.get(j);
                                    strModel = objOKS.getModel()==null?"":objOKS.getModel();
                                    strOKSerialNo = objOKS.getSerialNumber()==null?strModel:objOKS.getSerialNumber();
                                    if(objOKS.getSerialNumber() == null && "Y".equalsIgnoreCase(objOKS.getFleetContract()) && "USAGE".equalsIgnoreCase(objOKS.getInvoiceType()))
                                    {
                                    	strOKSerialNo = "Fleet";
                                    }
                                    strOKSFleetSerialNo = objOKS.getFleetSeriaNumber()==null?"":objOKS.getFleetSeriaNumber();
                                    strOKSInvoiceType = objOKS.getInvoiceType()==null?"":objOKS.getInvoiceType();
                                    strFleetContract = objOKS.getFleetContract()==null?"":objOKS.getFleetContract();
									strProductNumber = objOKS.getProductNumber()==null?"":objOKS.getProductNumber();
									strOKSCounterName = objOKS.getBillingCounterName();
									//System.out.println("*** ibList:j ****:"+j);
									//System.out.println("*** alIssueList Start ****");
								/*	System.out.println("strProductNumber : " + strProductNumber);
									System.out.println("*** ibList:strModel **** "+strModel);
									System.out.println("*** ibList:strOKSerialNo **** "+strOKSerialNo);
									System.out.println("*** ibList:strOKSFleetSerialNo **** "+strOKSFleetSerialNo);
									System.out.println("*** ibList:strOKSInvoiceType **** "+strOKSInvoiceType);
									System.out.println("*** ibList:strIssueInvoiceType **** "+strIssueInvoiceType);
									System.out.println("*** ibList:strFleetContract **** "+strFleetContract); */
									//System.out.println("");
									
                                    for(int k=0; k<alIssueList.size(); k++) {

									    //System.out.println("*** alIssueList:k ****:"+k);
									
									    issueListObj = (Canon_E193_IssueListObj)alIssueList.get(k);
                                        iIssueCatId = issueListObj.getCatId();
                                        strIssueCateCode = issueListObj.getCatCode()==null?"":issueListObj.getCatCode();

									    //System.out.println("");
									    //System.out.println("*** alIssueList:iIssueCatId **** "+iIssueCatId);
									    //System.out.println("*** alIssueList:strIssueCateCode **** "+strIssueCateCode);
										//System.out.println("*** objOKS.getLseId() **** "+objOKS.getLseId());
										//System.out.println("");

										// MW Changes Start: Abhay N
                                        if("Y".equalsIgnoreCase(strFleetContract))
                                        {
                                            if(strIssueInvoiceType.equalsIgnoreCase(strOKSInvoiceType))
                                            { 
                    
                                                String tempSerialNo = null;
                    
					                            if((strIssueCateCode.startsWith("START") || strIssueCateCode.startsWith("END") || strIssueCateCode.startsWith("TEST"))&& "USAGE".equalsIgnoreCase(strOKSInvoiceType))
											    {
                                                    if(!(strOKSerialNo == null || strOKSerialNo.equals("") || strOKSerialNo.equalsIgnoreCase("null")))
                                                        tempSerialNo = strOKSerialNo;
                                                    else if(!(strOKSFleetSerialNo == null || strOKSFleetSerialNo.equals("") || strOKSFleetSerialNo.equalsIgnoreCase("null")))
                                                        tempSerialNo = strOKSFleetSerialNo; 
                                                }
											    else
											    { 
                                                    if(strOKSFleetSerialNo == null || strOKSFleetSerialNo.equals("") || strOKSFleetSerialNo.equalsIgnoreCase("null"))
                                                        tempSerialNo = strOKSerialNo;
                                                    else
                                                        tempSerialNo = strOKSFleetSerialNo; 											
											    }
											
											    //System.out.println("");
									            //System.out.println("*** alIssueList:tempSerialNo **** "+tempSerialNo);
									            //System.out.println("");
									             //System.out.println("!!!!!!!Ticket line Controllerstr SerialNo " + strSerialNo + " tempSerialNo " + tempSerialNo
												    	//	+ " strCounterName " + strCounterName + " strOKSCounterName " + strOKSCounterName);
										
                                                if(strSerialNo.equalsIgnoreCase(tempSerialNo) && iSubCatId == iIssueCatId
                                                		&& ((strCounterName == null && strOKSCounterName == null) 
                                                				|| (strCounterName != null && strOKSCounterName != null && strCounterName.equals(strOKSCounterName)))) {

												   //System.out.println(" **** HURRAY **** ");
									   
											
  											        if(strIssueCateCode.startsWith("TIER")) 
												        objOKS.setChekcboxPCheck(true);
                                                    else if(strIssueCateCode.startsWith("BASE"))   // spothuri -- added base check 
												        objOKS.setChekcboxBCheck(true);
													else
														objOKS.setChekcboxRCheck(true);
                                                    //MW Project Changes - Starts
													//isBreak = true;   
                                                    //MW Project Changes - Ends
													break;
                                                } 
                                            }
										}
										// MW Changes End: Abhay N
                                        else
                                        {
											String serialFlag ="N";
											if("".equals(strSerialNo) || "null".equals(strSerialNo)){
												if(strProductNo.equalsIgnoreCase(strProductNumber) && iSubCatId == iIssueCatId
														&& ((strCounterName == null && strOKSCounterName == null) 
                                                				|| (strCounterName != null && strOKSCounterName != null && strCounterName.equals(strOKSCounterName)))){
													if(strIssueCateCode.startsWith("TIER")) 
														objOKS.setChekcboxPCheck(true);
													else if(strIssueCateCode.startsWith("BASE"))  // spothuri --added for base check
														objOKS.setChekcboxBCheck(true);
													else
														objOKS.setChekcboxRCheck(true);
													//MW Project Changes - Starts
													//isBreak = true;   
													//MW Project Changes - Ends
													break;
												  }
											}else{
												//System.out.println("!!!!!!!Ticket line Controllerstr strSerialNo " + strSerialNo + " strOKSerialNo " + strOKSerialNo
											    		//+ " strCounterName " + strCounterName + " strOKSCounterName " + strOKSCounterName);
												 if(strSerialNo.equalsIgnoreCase(strOKSerialNo) && iSubCatId == iIssueCatId 
														 && ((strCounterName == null && strOKSCounterName == null) 
	                                                				|| (strCounterName != null && strOKSCounterName != null && strCounterName.equals(strOKSCounterName))) ){
													   if(strIssueCateCode.startsWith("TIER")) 
														objOKS.setChekcboxPCheck(true);
													else if(strIssueCateCode.startsWith("BASE"))  // spothuri --added for base check
														objOKS.setChekcboxBCheck(true);
													else
														objOKS.setChekcboxRCheck(true);
													//MW Project Changes - Starts
													//isBreak = true;   
													//MW Project Changes - Ends
													break;								  
												  
												  }
                                            }
                                        }
                                    }
									//System.out.println("");
									//System.out.println("*** alIssueList End ****");
									//System.out.println("");							
                                    if(isBreak) break;
                                }
								//System.out.println("");
								//System.out.println("*** ibList End ****");
								//System.out.println("");
                            }
                            //System.out.println("");
							//System.out.println("*** subList End ****");
							//System.out.println("");
							
                            strNextPage = "canon_E193_csEIContLineDetails.jsp";
                        }else{
                            ibList = objInvDao.getInvoiceLines(iOrgId, strInv);
                            ibList.remove(0);
                            Canon_E193_InvoiceLinesObj objInvLines = null;
                            String objCustLineIdVal = "";
                            if(ibList != null && ibList.size() > 0) {
                                for(int i=0; i<ibList.size(); i++) {
                                    objInvLines = (Canon_E193_InvoiceLinesObj)ibList.get(i);
                                    for(int j = 0; j<subList.size(); j++) {
                                        objSL = (Canon_E193_TicketSubLinesObj)subList.get(j);
                                        objCustLineIdVal = objSL.getObjectValue();
                                        if(objCustLineIdVal.equals(objInvLines.getCustTrxLineId())) {
                                            objInvLines.setChekcboxCheck(true);
                                            objInvLines.setStrReasonCd(objSL.getCreditReason());
                                            for(int k=0; k<alIssueList.size(); k++) {
                                                issueListObj = (Canon_E193_IssueListObj)alIssueList.get(k);
                                                if(objSL.getCatId() == issueListObj.getCatId() && issueListObj.getCatCode().endsWith("QTY"))
                                                    objInvLines.setStrCreditQty(objSL.getNewValue());
                                                else if(objSL.getCatId() == issueListObj.getCatId() && issueListObj.getCatCode().endsWith("AMT"))
                                                    objInvLines.setStrCreditAmt(objSL.getNewValue());
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                            if("SUPPLY".equalsIgnoreCase(strInvSource)) strNextPage = "canon_E193_csEISupplyLineDetails.jsp";
                            else if("SERVICE".equalsIgnoreCase(strInvSource)) strNextPage = "canon_E193_csEIServiceLineDetails.jsp";
                            else strNextPage = "canon_E193_csEIOtherLineDetails.jsp";
                            objIssueList.setArrayList(alIssueList);
                        }
                    }else strNextPage = "canon_E193_csErrorPage.jsp";
                }
            }
            if(objLine == null) objLine = new Canon_E193_TicketLinesObj();
        }
        objIBList.setArrayList(ibList);
        
        String strReasonCd = objLine.getReasonCd()==null?"":objLine.getReasonCd();
        String strReason = objLine.getReason()==null?"":objLine.getReason();
        String strCatId = String.valueOf(objLine.getCatId());
        String strParentCatId = String.valueOf(objLine.getParentCatId());
        String strCatDesc = objLine.getCatIdDesc()==null?"":objLine.getCatIdDesc();
        String strNoteId = String.valueOf(objLine.getNoteId());
        String strNote = objLine.getNotes()==null?"":objLine.getNotes();
        String strSeverity = objLine.getSeverity()==null?"":objLine.getSeverity();
        String strCatCode = objLine.getCatCode()==null?"":objLine.getCatCode();
%>
        <form name="nonBillingIssueControlForm" method="post">
            <jsp:forward page="<%=strNextPage%>">
                <jsp:param name="hPageFrom" value="TicketLineController" />
                <jsp:param name="nextPage" value="" />
                <jsp:param name="iTicketId" value="<%=String.valueOf(iTicketId)%>" />
                <jsp:param name="iLineId" value="<%=String.valueOf(iLineId)%>" />
                <jsp:param name="hReasonCd" value="<%=strReasonCd%>" />
                <jsp:param name="reasonCdDesc" value="<%=strReason%>" />
                <jsp:param name="hCatId" value="<%=strCatId%>" />
                <jsp:param name="hParentCatId" value="<%=strParentCatId%>" />
                <jsp:param name="hCatDesc" value="<%=strCatDesc%>" />
                <jsp:param name="strNoteId" value="<%=strNoteId%>" />
                <jsp:param name="notes" value="<%=strNote%>" />
                <jsp:param name="severity" value="<%=strSeverity%>" />
                <jsp:param name="expTaxAmt" value="<%=strTaxNewValue%>" />
                <jsp:param name="taxExemption" value="<%=strTaxExamption%>" />
                <jsp:param name="iTaxOrig" value="<%=strTaxOldValue%>" />
                <jsp:param name="strPurchageOrd" value="<%=strPurchageOrd%>" />
                <jsp:param name="newPurchageOrd" value="<%=strPONewValue%>" />
                <jsp:param name="expFreightAmt" value="<%=strFreightNewVal%>" />
                <jsp:param name="hCatCode" value="<%=strCatCode%>" />
            </jsp:forward>
        </form>
<%
} 
catch(com.canon.oracle.custapp.util.CanonCustAppExceptionUtil eCustExp) {
       
       String strErr = "-- Exception : " + eCustExp.getStrErrorDesc() + " -- Exception Location :" + eCustExp.getStrErrorLocation();

       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + strErr);
}
catch (Exception eExp) {

       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}
%>
