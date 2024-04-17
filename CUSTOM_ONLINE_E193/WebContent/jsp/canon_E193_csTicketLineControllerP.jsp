<!-- $Header: canon_E193_csTicketLineControllerP.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |  canon_E193_csTicketLineControllerP.jsp - line Controller.
 |   
 | DESCRIPTION
 |   For a given criteria get records and forwards it to respective jsp page
 |
 | AUTHOR
 |  Subba 
 |
 | CREATION DATE
 |  10/26/2005
 |
 | HISTORY
 | DATE         WHO                 WHY
 | 18-Dec-2006    Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes
 | 13-Apr-2009    Chandra Sekhar     ITG # 176718
 | 05-Oct-2009    Naveen Khandelwal  MW Project Changes
 | 05-Feb-2013	  Hema				 ITG#434465 : To handle Non-Serialized accessories.
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
        //Get Org ID
        int iOrgId = objCiSession.getOrgId();
        
        // get userId
        //Start changes for S21 by Mangala
        //int iUserId = objCiSession.getUserId();
        String iUserId = objCiSession.getUserId();		
       // get resource id
        //int iResourceId = objCiSession.getResourceId();
        String iResourceId = objCiSession.getResourceId();		
      //End changes for S21 by Mangala
        // forward to next page
        String strNextPage = "canon_E193_csNBIssueCaptureP.jsp";
        
        String strControlForm = request.getParameter("controlForm");
        System.out.println("hello in canon_E193_csTicketLineControllerP iOrgId =" + iOrgId + "iUserId =" + iUserId + "iResourceId = " + iResourceId + "strControlForm =" + strControlForm);
        // get ticket no
        int iTicketId = 0;
        int iLineId = 0;
        String strTicketId = request.getParameter("iTicketId")==null?"":request.getParameter("iTicketId");
        if(!"".equals(strTicketId)) iTicketId = Integer.parseInt(strTicketId);

        String strLineId = request.getParameter("iLineId")==null?"":request.getParameter("iLineId");
        System.out.println("hello in canon_E193_csTicketLineControllerP strLineId =" + strLineId);
        if(!"".equals(strLineId)) iLineId = Integer.parseInt(strLineId);
        Canon_E193_TicketLinesObj objLine = null;
        String strPurchageOrd = "", strTaxNewValue = "", strTaxExamption = "", strPONewValue = "", strFreightNewVal = "", strTaxOldValue = "";
        ArrayList ibList = new ArrayList();
        ArrayList alIssueList = new ArrayList();
        if(strControlForm == null || "".equals(strControlForm) || "null".equalsIgnoreCase(strControlForm)) {
        	 System.out.println("in outer if");
            if(!"".equals(strLineId)) {
            	 System.out.println("in if");
                objLine = objNonBillDao.getNonBillLine(iOrgId, iTicketId, iLineId);
            }else{
            	 System.out.println("in else");
                objLine = new Canon_E193_TicketLinesObj();
            }
        }else{ // for billing
        	System.out.println("in outer else");
            if(!"".equals(strLineId)) {
                objLine = objBillDao.getBillLine(iOrgId, iTicketId, iLineId);
                ArrayList subList = objBillDao.getBillSubLine(iOrgId, iTicketId, iLineId);
                System.out.println("canon_E193_csTicketLineControllerP subList = "+ subList.size());
                Canon_E193_TicketSubLinesObj objSL = new Canon_E193_TicketSubLinesObj();
                if(subList != null && subList.size() > 0) {
                    objSL = (Canon_E193_TicketSubLinesObj)subList.get(0);
                }
               System.out.println("objLine.getCatCode() is " + objLine.getCatCode());
                if(objLine != null) {
                    if("TAX".equalsIgnoreCase(objLine.getCatCode())) {
                        strTaxNewValue = objSL.getNewValue()==null?"":objSL.getNewValue();
                        strTaxExamption = objSL.getTaxExamption()==null?"":objSL.getTaxExamption();
                        strTaxOldValue = objSL.getCurrentValue()==null?"":objSL.getCurrentValue();
                        strNextPage = "canon_E193_csBTaxIssueP.jsp";
                    }else if("PO".equalsIgnoreCase(objLine.getCatCode())) {
                        strPurchageOrd = objSL.getCurrentValue()==null?"":objSL.getCurrentValue();
                        strPONewValue = objSL.getNewValue()==null?"":objSL.getNewValue();
                        strNextPage = "canon_E193_csBPOIssueP.jsp";
                    }else if("OTHERS".equalsIgnoreCase(objLine.getCatCode())) {
                        strNextPage = "canon_E193_csBOthersIssueP.jsp";
                    }else if("FREIGHT".equalsIgnoreCase(objLine.getCatCode())) {
                        strFreightNewVal = objSL.getNewValue()==null?"":objSL.getNewValue();
                        strNextPage = "canon_E193_csBFreightIssueP.jsp";
                    }else if("BILLING".equalsIgnoreCase(objLine.getCatCode())) {
                        Canon_E193_IssueListObj issueListObj = null;
                        String strInv = request.getParameter("invoiceNumber");
                        String strInvSource = request.getParameter("InvSource")==null?"":request.getParameter("InvSource");
                        String strInvoiceType = request.getParameter("strInvoiceType")==null?"":request.getParameter("strInvoiceType");

                        /* ITG# 73987 : Begin */    
                        /* ITG # 176718 Changes */
                  //String strRegionCode = (String)objCiSession.getRegionCode();    
                  String strRegionCode = Canon_E193_RegionCodeDAO.getRegionCode(strInv);
                  /* ITG # 176718 Changes */
                        /* ITG# 73987 : End */

                        alIssueList = objIssueDao.getIssueList(iOrgId, strRegionCode, null, objLine.getCatId());
                        System.out.println(" ######### strInvSource =  "+strInvSource + " alIssueList = " + alIssueList.size());
                        if("CONTRACTS".equalsIgnoreCase(strInvSource)) {
                            //MW Project Changes - Starts
                            ArrayList ibListRead = null, ibListPrice = null, ibListBRead = null, aggregateList = null;
                            String str = "", strRFleetCont = "N", strPFleetCont = "N", strBFleetCont = "N";

                            ArrayList alR = new ArrayList();
                            ArrayList alP = new ArrayList();
                            ArrayList alB = new ArrayList();
							boolean isReadChange = false;
							boolean isPriceChange = false;
							boolean isBaseChange = false;
                            for(int i=0; i<subList.size(); i++) {
                                objSL = (Canon_E193_TicketSubLinesObj)subList.get(i);
                                str = objSL.getObjectType()==null?"":objSL.getObjectType();
                                System.out.println(" ######### ObjectType =  "+str);
                                if(str.startsWith("USAGE")){
                                    if(objSL.getSerialNo().substring(0,3).equalsIgnoreCase("FLT")) strRFleetCont = "Y";
                                    alR.add(objSL.getSerialNo()+"~"+objSL.getInvoiceNumbers()+"~"+objSL.getProductNumber()+"~"+objSL.getAttribute5());
									isReadChange = true;
									//System.out.println(" USAGE Serial Number = "+objSL.getSerialNo());
									//System.out.println(" USAGE Invoice Number = "+objSL.getInvoiceNumbers());
                                }else if(str.startsWith("PRICING")) {
                                	if(objSL.getSerialNo()==null)
                                	{
                                		objSL.setSerialNo("");
                                		strPFleetCont = "Y";	
                                	}                                	
                                	else if(objSL.getSerialNo().substring(0,3).equalsIgnoreCase("FLT")) 
                                    	strPFleetCont = "Y";
                                    alP.add(objSL.getSerialNo()+"~"+objSL.getInvoiceNumbers()+"~"+objSL.getProductNumber()+"~"+objSL.getAttribute5());
									isPriceChange = true;
									//System.out.println(" PRICING Serial Number = "+objSL.getSerialNo());
									//System.out.println(" PRICING Invoice Number = "+objSL.getInvoiceNumbers());
                                }else if(str.startsWith("BASE")) {
                                  //  if(objSL.getSerialNo().substring(0,3).equalsIgnoreCase("FLT")) strBFleetCont = "Y";
									strBFleetCont = objSL.getFleetFlag()==null?"N":objSL.getFleetFlag();
									alB.add(objSL.getSerialNo()+"~"+objSL.getInvoiceNumbers()+"~"+objSL.getProductNumber()+"~"+objSL.getAttribute5());
									isBaseChange = true;
									//System.out.println(" BASE Serial Number = "+objSL.getSerialNo());
									//System.out.println(" BASE Invoice Number = "+objSL.getInvoiceNumbers());
                                }
                            }
                            //System.out.println("Done");
                            String[] strRSerialNo = null;
                            String[] strPSerialNo = null;
                            String[] strBSerialNo = null;
                            if(alR != null && alR.size() > 0) {
                                strRSerialNo = new String[alR.size()];
                                for(int i=0; i<alR.size(); i++) {
                                    strRSerialNo[i] = alR.get(i).toString();
                                }
                            }
                            if(alP != null && alP.size() > 0) {
                                strPSerialNo = new String[alP.size()];
                                for(int i=0; i<alP.size(); i++) {
                                    strPSerialNo[i] = alP.get(i).toString();
                                }
                            }
                            if(alB != null && alB.size() > 0) {
                                strBSerialNo = new String[alB.size()];
                                for(int i=0; i<alB.size(); i++) {
                                    strBSerialNo[i] = alB.get(i).toString();
                                }
                            }
							//System.out.println(" iOrgId = "+iOrgId);
							//System.out.println(" strRFleetCont = "+strRFleetCont);
							//System.out.println(" strInv = "+strInv);
							//System.out.println(" strRSerialNo = "+strRSerialNo); 
							
                            if(isBaseChange  && (isReadChange || isPriceChange))
								strInvoiceType = "CONSOLIDATED";
							else if(isBaseChange)	
								strInvoiceType = "BASE";
							else
								strInvoiceType = "USAGE";
                            System.out.println(" #####strInvoiceType = "+strInvoiceType);
							if("CONSOLIDATED".equalsIgnoreCase(strInvoiceType)) {
                                ibListRead = objInvDao.getOksBillingSerialNoDtlsDisplay(iOrgId, strRFleetCont, "USAGE", "READ", strInv, strRSerialNo, null, null);
                                //System.out.println(" CONSOLIDATED ibListRead= "+ ibListRead );
                                ibList.add(ibListRead);
                                
                                aggregateList = objInvDao.getOksAggregatePricingDtls(strInv, "USAGE");
								//System.out.println(" CONSOLIDATED aggregateList= "+ aggregateList );
                                ibListPrice = objInvDao.getOksBillingSerialNoDtlsDisplay(iOrgId, strPFleetCont, "USAGE", "PRICE", strInv, strPSerialNo, null, null);
                                //System.out.println(" CONSOLIDATED ibListPrice= "+ ibListPrice );
                                aggregateList.addAll(ibListPrice);
                                ibList.add(aggregateList);
                                
                                ibListBRead = objInvDao.getOksBillingSerialNoDtlsDisplay(iOrgId, strBFleetCont, "BASE", "READ", strInv, strBSerialNo, null, null);
                                ibList.add(ibListBRead);
                                //System.out.println(" CONSOLIDATED ");
                                
                            }
                            else if("USAGE".equalsIgnoreCase(strInvoiceType)) {
                                ibListRead = objInvDao.getOksBillingSerialNoDtlsDisplay(iOrgId, strRFleetCont, "USAGE", "READ", strInv, strRSerialNo, null, null);
                                ibList.add(ibListRead);
                                //System.out.println(" USAGE ibListRead= "+ ibListRead );
                                
                                aggregateList = objInvDao.getOksAggregatePricingDtls(strInv, "USAGE");
								//System.out.println(" USAGE aggregateList= "+ aggregateList );
                                ibListPrice = objInvDao.getOksBillingSerialNoDtlsDisplay(iOrgId, strPFleetCont, "USAGE", "PRICE", strInv, strPSerialNo, null, null);
                                //System.out.println(" USAGE ibListRead= "+ ibListPrice );
                                aggregateList.addAll(ibListPrice);
                                ibList.add(aggregateList);
                                
                            }
                            else if("BASE".equalsIgnoreCase(strInvoiceType)) {
                                ibListBRead = objInvDao.getOksBillingSerialNoDtlsDisplay(iOrgId, strBFleetCont, "BASE", "READ", strInv, strBSerialNo, null, null);
                                ibList.add(ibListBRead);
                                //System.out.println(" BASE ibListRead= "+ ibListBRead );
                            }
                           
                            //MW Project Changes - Ends
                            Canon_E193_OKSBillingDtlsObj objOKS = null;
                            String strOKSerialNo = "", strOKSLineId = "", strSLSerialNo = "", strSLObjectType = "", strModel = "", strOKSFleetSerialNo = "", strOKSInvoiceType = "", strFleetContract = "";
                            for(int i=0; i<ibList.size(); i++) {
                                ArrayList list = (ArrayList)ibList.get(i);
                                System.out.println(" list.size()= " + list.size());
                                for(int k=0; k<list.size(); k++) {
                                    objOKS = (Canon_E193_OKSBillingDtlsObj)list.get(k);
                                    strModel = objOKS.getModel()==null?"":objOKS.getModel();
                                    strOKSerialNo = objOKS.getSerialNumber()==null?"":objOKS.getSerialNumber();
                                    strOKSFleetSerialNo = objOKS.getFleetSeriaNumber()==null?"":objOKS.getFleetSeriaNumber();
                                    strOKSInvoiceType = objOKS.getInvoiceType()==null?"":objOKS.getInvoiceType();
                                    strFleetContract = objOKS.getFleetContract()==null?"":objOKS.getFleetContract();
                                    strOKSLineId = objOKS.getContractLineId();
                                    //System.out.println(" strOKSerialNo= " + strOKSerialNo + " strOKSInvoiceType = " + strOKSInvoiceType + "strOKSLineId= "+ strOKSLineId);
                                    Hashtable htValues = new Hashtable();
                                    for(int j = 0; j<subList.size(); j++) {
                                        objSL = (Canon_E193_TicketSubLinesObj)subList.get(j);
                                        strSLSerialNo = objSL.getSerialNo()==null?"":objSL.getSerialNo();
                                        strSLObjectType = objSL.getObjectValue()==null?"":objSL.getObjectValue();
										String counterName = objSL.getAttribute3(); // spothuri -- added counter name to hashtable mapping
										if(counterName == null )
											counterName = "";
										String physMtrLbCd = objSL.getAttribute4(); // spothuri -- added counter name to hashtable mapping -- Q11607 changed to physical meter label code
										if(physMtrLbCd == null )
											physMtrLbCd = "";
                                        //MW Project Changes
                                        if(("Y".equalsIgnoreCase(strFleetContract) && "BASE".equalsIgnoreCase(strOKSInvoiceType))
                                        || ("Y".equalsIgnoreCase(strFleetContract) && "USAGE".equalsIgnoreCase(strOKSInvoiceType) && objOKS.getLseId() == 12))
                                        {

                                            String tempSerialNo = null;
                    
                                            if(strOKSFleetSerialNo == null || strOKSFleetSerialNo.equals("") || strOKSFleetSerialNo.equalsIgnoreCase("null"))
                                                tempSerialNo = strSLSerialNo;
                                            else
                                                tempSerialNo = strOKSFleetSerialNo;   
                                                
                                            if(tempSerialNo.equalsIgnoreCase(strSLSerialNo) && 
                                                strOKSLineId.equalsIgnoreCase(strSLObjectType))
                                                htValues.put((tempSerialNo+strOKSLineId+counterName+physMtrLbCd+objSL.getCatId()), (objSL.getNewValue()==null?"":objSL.getNewValue()));
                                        }
                                        else
                                        {
                                            if(strOKSerialNo.equalsIgnoreCase(strSLSerialNo) && 
                                                strOKSLineId.equalsIgnoreCase(strSLObjectType))
                                                htValues.put((strOKSerialNo+strOKSLineId+counterName+physMtrLbCd+objSL.getCatId()), (objSL.getNewValue()==null?"":objSL.getNewValue()));
                                        }
                                    }
                                    objOKS.setHtAttribute(htValues);
                                }
                            }
                            System.out.println(" ######### Before calling  canon_E193_csEIContLineUpdateP.jsp ");
                            strNextPage = "canon_E193_csEIContLineUpdateP.jsp";
                        }else{
                        	System.out.println(" ######### Inside Else ");
                            ibList = objInvDao.getInvoiceLines(iOrgId, strInv);
                            System.out.println(" csTicketLineControllerP getInvoiceLines");
                            if(ibList!=null && ibList.size() >0)
                            ibList.remove(0);
                            boolean isNotMatch = true;
                            Canon_E193_InvoiceLinesObj objInvLines = null;
                            String objCustLineIdVal = "";
                            if(ibList != null && ibList.size() > 0) {
                                for(int i=0; i<ibList.size(); i++) {
                                    isNotMatch = true;
                                    objInvLines = (Canon_E193_InvoiceLinesObj)ibList.get(i);
                                    //System.out.println( "canon_E193_csEIContLineUpdateP objInvLines = ");
                                    for(int j = 0; j<subList.size(); j++) {
                                        objSL = (Canon_E193_TicketSubLinesObj)subList.get(j);
                                        objCustLineIdVal = objSL.getObjectValue();
                                        //System.out.println(" ######### objCustLineIdVal= " + objCustLineIdVal);
                                        if(objCustLineIdVal.equals(objInvLines.getCustTrxLineId())) { // Start Comment For Defect # 18796
                                            objInvLines.setChekcboxCheck(true);
                                            isNotMatch = false;
                                            objInvLines.setStrReasonCd(objSL.getCreditReason());
                                            for(int k=0; k<alIssueList.size(); k++) {
                                                issueListObj = (Canon_E193_IssueListObj)alIssueList.get(k);
                                                if(objSL.getCatId() == issueListObj.getCatId() && issueListObj.getCatCode().endsWith("QTY"))
                                                    objInvLines.setStrCreditQty(objSL.getNewValue());
                                                else if(objSL.getCatId() == issueListObj.getCatId() && issueListObj.getCatCode().endsWith("AMT"))
                                                    objInvLines.setStrCreditAmt(objSL.getNewValue());
                                            }
                                            break;
                                       } // End Comment For Defect # 18796
                                    }
                                    if(isNotMatch) {
                                        ibList.remove(i);
                                        --i;
                                    }
                                }
                            }
                            if("SUPPLY".equalsIgnoreCase(strInvSource)) strNextPage = "canon_E193_csEISupplyLineDetailsP.jsp";
                            else if("SERVICE".equalsIgnoreCase(strInvSource)) strNextPage = "canon_E193_csEIServiceLineDetailsP.jsp";
                            else strNextPage = "canon_E193_csEIOtherLineDetailsP.jsp";
                        }
                    }else strNextPage = "canon_E193_csErrorPage.jsp";
                }
            }
            if(objLine == null) objLine = new Canon_E193_TicketLinesObj();
        }
        System.out.println(" IbList - For Service Line Details= ");
        objIBList.setArrayList(ibList);
        objIssueList.setArrayList(alIssueList);
        
        String strReasonCd = objLine.getReasonCd()==null?"":objLine.getReasonCd();
        String strReason = objLine.getReason()==null?"":objLine.getReason();
        String strCatId = String.valueOf(objLine.getCatId());
        String strParentCatId = String.valueOf(objLine.getParentCatId());
        String strCatDesc = objLine.getCatIdDesc()==null?"":objLine.getCatIdDesc();
        String strNoteId = String.valueOf(objLine.getNoteId());
        String strNote = objLine.getNotes()==null?"":objLine.getNotes();
        String strSeverity = objLine.getSeverity()==null?"":objLine.getSeverity();        
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
            </jsp:forward>
        </form>
<%
} 
catch(com.canon.oracle.custapp.util.CanonCustAppExceptionUtil eCustExp) {
       String strErr = "-- Exception : " + eCustExp.getStrErrorDesc() + " -- Exception Location :" + eCustExp.getStrErrorLocation();
       System.out.println("strErr in exception is " + strErr);
    //   response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + strErr);
}
catch (Exception eExp) {
	System.out.println("eExp in exception is " + eExp.toString());
  //  response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}
%>