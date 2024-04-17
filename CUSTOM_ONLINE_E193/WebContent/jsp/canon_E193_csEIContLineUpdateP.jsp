<!-- $Header: canon_E193_csEIContLineUpdateP.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |  canon_E193_csEIContLineUpdateP.jsp - contact line details.
 |   
 | DESCRIPTION
 |   Details of the incorrect billing issue details.
 |
 | AUTHOR
 |  Subba 
 |
 | CREATION DATE
 |  10/26/2005
 |
 | HISTORY
 | DATE         WHO                 WHY
 | 10/07/2009   Naveen Khandelwal   MW Project Changes
 |
 |
 +=======================================================================--%>
<html>
<head>
<title>Billing Contract Line Details</title>
</head> 
<%@page language="java" %> 
<%@page import ="java.util.*" %>
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_OKSBillingDtlsObj" %>
<%@ page import="com.canon.oracle.custapp.util.CanonCustAppUtil" %>
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_IssueListObj" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objIBList" scope="request" />
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objIssueList" scope="request" />
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Invoice" id="counterReadDao" scope="page"/>
<% String ctxPath = request.getContextPath(); %>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=ctxPath%>/e193/js/canon_E193_csCButton.js"></script>
<link rel="stylesheet" href="<%=ctxPath%>/e193/css/styles.css" type="text/css">
<link rel="stylesheet" href="<%=ctxPath%>/e193/css/canon_E193_csStyleSheet.css" type="text/css">


<%
    try {   
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>

<%
System.out.println("Inside canon_E193_csEIContLineUpdateP.jsp ");
    ArrayList ibList = (ArrayList)objIBList.getArrayList();
    //MW Project Changes - Starts
    ArrayList alPriceList = null, alReadList = null, alBReadList = null;
    if(ibList != null && ibList.size() > 2) { // Consolidated
        alReadList = (ArrayList)ibList.get(0);
        alPriceList = (ArrayList)ibList.get(1);
        alBReadList = (ArrayList)ibList.get(2);
    }else if(ibList != null && ibList.size() > 1) { // Usage
        alReadList = (ArrayList)ibList.get(0);
        alPriceList = (ArrayList)ibList.get(1);
    }else if(ibList != null && ibList.size() > 0) { // Base
        alBReadList = (ArrayList)ibList.get(0);
    }
    //MW Project Changes - Ends
    ArrayList alIssueList = (ArrayList)objIssueList.getArrayList();
    Canon_E193_OKSBillingDtlsObj objOks = null;
    Canon_E193_IssueListObj objIL = null;
    
    String strInvNo = request.getParameter("invoiceNumber")==null?"":request.getParameter("invoiceNumber");
    String strInvoiceType = request.getParameter("strInvoiceType")==null?"":request.getParameter("strInvoiceType");
%> 
<%!
    private String getDisValue(String strVal, Hashtable ht, ArrayList al, int iIdx) {
        int iCatIdVal = 0;
        if(ht == null) return "";
        else {
            if(al != null && al.size() >= iIdx && !al.isEmpty()) iCatIdVal = ((Canon_E193_IssueListObj)al.get(iIdx)).getCatId();
            Object obj = ht.get((strVal+iCatIdVal));
            return (obj==null?"":obj.toString());
        }
    }
%>

<form name="contLineUpdateForm" action="canon_E193_csTicketSummaryB1Controller.jsp" method="post">
	<table class="request-service" style="align:center;" cellpadding="1" cellspacing="0">            
        <tr>
            <td colspan="3" height="10"></td>
        </tr>
        <tr>
            <td class="tableQuestionCell" colspan="3">
                <font class="promptReadOnly" color="#FF0000">
                    <b><%=strReviewPopupM1%></b>
                </font>
            </td>
        </tr>
        <tr><td colspan="3" height="10"></td></tr>
        <tr>
            <td  colspan="3"><font class="promptReadOnly">Selected Invoice: <%=strInvNo%></font></td>
        </tr>
        <tr>
            <td colspan="3" height="10"></td>
        </tr>           
        <%
            ////MW Project Changes          
            if("CONSOLIDATED".equalsIgnoreCase(strInvoiceType) || "USAGE".equalsIgnoreCase(strInvoiceType)) {   // if the invoice type is CONSOLIDATED/USAGE only
                if(alReadList != null && alReadList.size() > 0) {
        %>
        <tr>
            <td  colspan="3">
                <font class="promptReadOnly">Read Details:</font>
            </td>
        </tr>
        <tr>
            <td colspan="3" height="10"></td>
        </tr>
        <tr>
            <td width="10"></td>
            <td colspan="2">
            <% if(alReadList.size() > 5) { %>
            	<div id="divReadRoot" align="left">
    			<div style="overflow: hidden;" id="divReadHeaderRow">
    			</div>

    			<div style="overflow-y:scroll;" onscroll="OnScrollDiv(this, 'divReadHeaderRow')" id="divReadMainContent">
            <% } %>
                <table width="95%" class="request-service" id="readChargeTable">
                	<thead id="readChargeTableHdr">
                    <tr>
                        <th rowspan="2" nowrap>Serial Number</th>
                        <th rowspan="2" nowrap>Start Meter<br>Read Date</th>
                        <th rowspan="2" nowrap>End Meter<br>Read Date</th>
						<th  rowspan="2" nowrap>Counter</th>
						<th colspan="3" nowrap>OLD</th>
						<th colspan="3" nowrap>NEW</th>
                    <%
                        if(alIssueList != null && alIssueList.size() > 0) {
                            String strCatCode = "";
							String strCatCodeDescription = "";
                            for(int i=0; i<alIssueList.size(); i++) {
                                objIL = (Canon_E193_IssueListObj)alIssueList.get(i);
                                strCatCode = objIL.getCatCode()==null?"":objIL.getCatCode();
                                if(!strCatCode.startsWith("BASE") && !strCatCode.startsWith("TIER")) 
								{
									// issue list meter read categories 
									strCatCodeDescription = objIL.getCatDesc();
                                }
                            }
                        }
                    %>
                    </tr>
					<tr>
								<th>Start Read</th>
								<th>End Read</th>
								<th>Test Copies</th>
								<th>Start Read</th>
								<th>End Read</th>
								<th>Test Copies</th>
					</tr>
					</thead>
					<tbody>				
                    <%
                        if(alReadList != null && alReadList.size() > 0) {
                            String strSerialNo = "", strStartMeterDate = "", strEndMeterDate = "", strObj = "" , strInvNumer = "", 
                            		tempSerialNo = ""; 
                            int intIsvcInvLinePk = 0;
                            
                            String physMtrLbCd ="";
                            
                            HashSet set = new HashSet();
                            for(int i = 0; i < alReadList.size(); i++) {
                                objOks = (Canon_E193_OKSBillingDtlsObj)alReadList.get(i);
                                strSerialNo = objOks.getSerialNumber()==null?"":objOks.getSerialNumber();
								strInvNumer   = objOks.getTrxNumber()==null?"":objOks.getTrxNumber();
								//Newly Added
								 intIsvcInvLinePk = objOks.getSvcinvlinePk()==0?0:objOks.getSvcinvlinePk();
	                             System.out.println("canon_E193_csEIContLineUpdateP isvcInvLinePk:::::"+intIsvcInvLinePk + " strSerialNo::::" + strSerialNo);
	                             
                                strStartMeterDate = objOks.getStartMeterReadDate()==null?"":objOks.getStartMeterReadDate();
                                strEndMeterDate = objOks.getEndMeterReadDate()==null?"":objOks.getEndMeterReadDate();
                                /* if(i == 0 || !set.contains((strSerialNo+strStartMeterDate+strEndMeterDate))) {
                                    set.add((strSerialNo+strStartMeterDate+strEndMeterDate)); */
                                
                                strObj = strSerialNo+objOks.getContractLineId();
								ArrayList counterMeter = new ArrayList();
										counterMeter = counterReadDao.getCounterReadList(strInvNumer,strSerialNo,intIsvcInvLinePk); // Passed 3rd Parameter For Invoice#600061.
										if(counterMeter!=null && counterMeter.size()>0) 
										{
											for(int k =0; k<counterMeter.size(); k++)
											{
												Canon_E193_OKSBillingDtlsObj meterRead = (Canon_E193_OKSBillingDtlsObj)counterMeter.get(k);
												String testCopyInd = meterRead.getTestCopyInd();
												String counterNameVal = meterRead.getBillingCounterName();
												
												physMtrLbCd = meterRead.getPhysMtrLbCd() == null ? "" : meterRead.getPhysMtrLbCd();
												
												if(counterNameVal == null)
													counterNameVal = "";
												strObj = strSerialNo + objOks.getContractLineId() + counterNameVal + physMtrLbCd;
                    %>
                                <tr >
                                	<%if(tempSerialNo.equals(strSerialNo)) {%>
                                	 <td></td>
                                    <%} else {%>
                                     <td><%=strSerialNo%></td>
                                     <%} 
                                	tempSerialNo = strSerialNo;
                                     %>
                                    <td nowrap><%=strStartMeterDate%></td>
                                    <td nowrap><%=strEndMeterDate%></td>
                                    <td nowrap><%=meterRead.getCounterDesc()%></td>
                                    <td><%=CanonCustAppUtil.getNumberWithFormat(meterRead.getStartReading())%></td>
                                    <td><%=CanonCustAppUtil.getNumberWithFormat(meterRead.getEndReading())%></td>
									<%	if(testCopyInd.equalsIgnoreCase("Y"))
										{
									%>
											<td>
												<%=CanonCustAppUtil.getNumberWithFormat(meterRead.getTestCopies())%>
											</td>
									<%	}
										else
										{
									%>
											<td> &nbsp; </td>
									<%	}	
									
									/* System.out.println("canon_E193_csEIContLineUpdateP.jsp strObj : " + strObj
											+ " objOks.getHtAttribute() " + objOks.getHtAttribute() + " alIssueList "
											+ alIssueList); */
									%>
                                    
                                
                                    <td><%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 0)%></td>
                                    <td><%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 1)%></td>
									<%
										if(testCopyInd.equalsIgnoreCase("Y")){
									%>
                                    <td> <%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 2)%></td>	
									<%
										}else{
									%>
											<td> &nbsp; </td>	
									<%
									}
									%>
                                    
                                </tr>
                <%
								}
								}
                               // }
                            }
                        }else{
                %>
                            <tr >
                                <td colspan="11">
                                    <font class="promptReadOnly"><b><%=strEIContLineUpdatePM1%></b></font>
                                </td>
                            </tr>
                <%      } %>
                	</tbody>	
                </table>
            <% if(alReadList.size() > 5) { %>
                </div>
                </div>
            <% } %>
            </td>
        </tr>
        <tr><td colspan="3"></td></tr>
    <%    } // end read
        if(alPriceList != null && alPriceList.size() > 0) {
    %>
        <tr>
            <td  colspan="3">
                <font class="promptReadOnly">Pricing Details:</font>
            </td>
        </tr>
        <tr>
            <td colspan="3" height="10"></td>
        </tr>
        <tr>
            <td width="10"></td>
            <td colspan="2">
            <% if(alPriceList != null && alPriceList.size() > 5) { %>
                <div id="divPriceRoot" align="left">
    			<div style="overflow: hidden;" id="divPriceHeaderRow">
    			</div>

    			<div style="overflow-y:scroll;" onscroll="OnScrollDiv(this, 'divPriceHeaderRow')" id="divPriceMainContent">
            <% } %>
                <table width="95%" class="request-service" id="priceChargeTable">
                	<thead id="priceChargeTableHdr">
                    <tr>
                        <th rowspan="2">Serial Number</th>
                        <th colspan="2" rowspan="2">Usage</th>
                        <th colspan="2">Tier 1</th>
                        <th colspan="2">Tier 2</th>
                        <th colspan="2">Tier 3</th>
                        <th colspan="2">Tier 4</th>
                    </tr>
                    <tr>
                    <%
                        if(alIssueList != null && alIssueList.size() > 0) {
                            String strCatCode = "";
                            for(int i=0; i<alIssueList.size(); i++) {
                                objIL = (Canon_E193_IssueListObj)alIssueList.get(i);
                                strCatCode = objIL.getCatCode()==null?"":objIL.getCatCode();
                                if(strCatCode.startsWith("TIER")) {
                    %>
                                    <td><%=objIL.getCatDesc()%></td>
                    <%
                                }
                            }
                        }
                    %>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        if(alPriceList != null && alPriceList.size() > 0) {
                            String strSerialNo = "", strObj = "", strFleetSerialNo = "";
                            for(int i = 0; i < alPriceList.size(); i++) {
                                objOks = (Canon_E193_OKSBillingDtlsObj)alPriceList.get(i);
                                strSerialNo = objOks.getSerialNumber()==null?"":objOks.getSerialNumber();
                                strFleetSerialNo = objOks.getFleetSeriaNumber()==null?"":objOks.getFleetSeriaNumber();

                                String tempSerialNo = null;

                                if(strFleetSerialNo == null || strFleetSerialNo.equals("") || strFleetSerialNo.equalsIgnoreCase("null"))
                                    tempSerialNo = strSerialNo;
                                else
                                    tempSerialNo = strFleetSerialNo;  
                                    
                                //MW Project Changes
                                if("Y".equalsIgnoreCase(objOks.getFleetContract()))
                                    strObj = tempSerialNo+objOks.getContractLineId()+objOks.getBillingCounterName();
                                else
                                    strObj = strSerialNo+objOks.getContractLineId()+objOks.getBillingCounterName();
                    %>
                                <tr >
                                    <td><%=strSerialNo%></td>
                                    <td><%=objOks.getLineDescription()==null?"":objOks.getLineDescription()%></td>
                                    <td align="right">OLD</td>
                                    <td><%=CanonCustAppUtil.getNumberWithFormat(objOks.getTier1CopyVolume())%></td>
                                    <td><%=objOks.getTier1Rate()==0.0?"":String.valueOf(objOks.getTier1Rate())%></td>
                                    <td><%=CanonCustAppUtil.getNumberWithFormat(objOks.getTier2CopyVolume())%></td>
                                    <td><%=objOks.getTier2Rate()==0.0?"":String.valueOf(objOks.getTier2Rate())%></td>
                                    <td><%=CanonCustAppUtil.getNumberWithFormat(objOks.getTier3CopyVolume())%></td>
                                    <td><%=objOks.getTier3Rate()==0.0?"":String.valueOf(objOks.getTier3Rate())%></td>
                                    <td><%=CanonCustAppUtil.getNumberWithFormat(objOks.getTier4CopyVolume())%></td>
                                    <td><%=objOks.getTier4Rate()==0.0?"":String.valueOf(objOks.getTier4Rate())%></td>
                                </tr>
                                <tr >
                                    <td align="right" colspan="3">NEW</td>
                                    <td><%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 3)%></td>
                                    <td><%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 4)%></td>
                                    <td><%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 5)%></td>
                                    <td><%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 6)%></td>
                                    <td><%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 7)%></td>
                                    <td><%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 8)%></td>
                                    <td><%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 9)%></td>
                                    <td><%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 10)%></td>
                                </tr>
                <%
                            }
                        } else {
                %>
                            <tr >
                                <td colspan="11">
                                    <font class="promptReadOnly"><b><%=strEIContLineUpdatePM2%></b></font>
                                </td>
                            </tr>
                <%      } %>
                	</tbody>
                </table>
            <% if(alPriceList != null && alPriceList.size() > 5) { %>
                </div>
                </div>
            <% } %>
            </td>
        </tr>
        <%      } // end price 
            }// end usage
             //MW Project Changes
             if(("CONSOLIDATED".equalsIgnoreCase(strInvoiceType) || "BASE".equalsIgnoreCase(strInvoiceType)) && alBReadList != null && alBReadList.size() > 0) {  
        %>      
        <tr>
            <td  colspan="3">
                <font class="promptReadOnly">Rate Details:</font>
            </td>
        </tr>
        <tr>
            <td colspan="3" height="10"></td>
        </tr>
        <tr>
            <td width="10"></td>
            <td colspan="2">
            <% 
            //MW Project Changes 
            if(alBReadList != null && alBReadList.size() > 5) { %>
            	<div id="divBaseRoot" align="left">
	    			<div style="overflow: hidden;" id="divBaseHeaderRow">
	    			</div>
	
	    			<div style="overflow-y:scroll;" onscroll="OnScrollDiv(this, 'divBaseHeaderRow')" id="divBaseMainContent">
            <% } %>
                <table width="98%" class="request-service" id="baseChargeTable">
                	<thead id="baseChargeTableHdr">
                    <tr>
                        <th>Serial Number</th>
						<th>Product Number</th>
                        <th>Item Code</th>
                        <th>Model Description</th>
                        <th>Rate</th>
                    <%
                        int iIndex = 0;
                        if(alIssueList != null && alIssueList.size() > 0) {
                            String strCatCode = "";
                            for(int i=0; i<alIssueList.size(); i++) {
                                objIL = (Canon_E193_IssueListObj)alIssueList.get(i);
                                strCatCode = objIL.getCatCode()==null?"":objIL.getCatCode();
                                if(strCatCode.startsWith("BASE")) {
                                    iIndex = i;
                    %>
                                    <th><%=objIL.getCatDesc()%></th>
                    <%
                                }
                            }
                        }
                    %>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        //MW Project Changes
                        if(alBReadList != null && alBReadList.size() > 0) {
                            String strObj = "", strSerialNo = "", strFleetSerialNo = "";
                            boolean strTextReadOnly = false;
                            //MW Project Changes
                            for(int i = 0; i < alBReadList.size(); i++) {
                                objOks = (Canon_E193_OKSBillingDtlsObj)alBReadList.get(i);
                                strTextReadOnly = false;
                                strSerialNo = objOks.getSerialNumber()==null?"":objOks.getSerialNumber();
                                strFleetSerialNo = objOks.getFleetSeriaNumber()==null?"":objOks.getFleetSeriaNumber();

                                String tempSerialNo = null;

                                if(strFleetSerialNo == null || strFleetSerialNo.equals("") || strFleetSerialNo.equalsIgnoreCase("null"))
                                    tempSerialNo = strSerialNo;
                                else
                                    tempSerialNo = strFleetSerialNo;  

                                if("Y".equalsIgnoreCase(objOks.getFleetContract()))
                                    strObj = strFleetSerialNo+objOks.getContractLineId();
                                else
                                    strObj = strSerialNo+objOks.getContractLineId();
                                /* System.out.println("canon_E193_csEIContLineUpdateP Base strObj : " + strObj
										+ " objOks.getHtAttribute() " + objOks.getHtAttribute() + " alIssueList "
										+ alIssueList); */
                                
                    %>
                                <tr >
                                    <td><%=strSerialNo%></td>
									<td><%=objOks.getProductNumber()==null?"":objOks.getProductNumber()%></td>
                                    <td><%=objOks.getItemCode()==null?"":objOks.getItemCode()%></td>
                                    <td><%=objOks.getModel()==null?"":objOks.getModel() +"<br>"+objOks.getLineDescription()==null?"":objOks.getLineDescription()%></td>
                                    <td><%=CanonCustAppUtil.getDoubleWithFormat(objOks.getBaseAmount())%></td>
                                    <td><%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, iIndex)%></td>
                                </tr>                               
                <%
                            }
                        }else{
                %>
                            <tr >
                                <td colspan="8">
                                    <font class="promptReadOnly"><b><%=strEIContLineUpdatePM3%></b></font>
                                </td>
                            </tr>
                <%      } %>
                	</tbody>
                </table>
            <%  //MW Project Changes
                if(alBReadList != null && alBReadList.size() > 5) { %>
                </div>
                </div>
            <% } %>
            </td>
        </tr>       
        <%
             } // close BASE
        %>
        <tr>
            <td colspan="3" height="10"></td>
        </tr>
        <tr>
            <td colspan="3">
                <font class="promptReadOnly">Captured Details:</font>
            </td>
        </tr>
        <tr>
            <td colspan="3" height="10"></td>
        </tr>
        <tr>
            <td width="10"></td>
            <td colspan="2">
                <table cellspacing="1" cellpadding="2" border="1" class="request-service">
                    <tr>
                        <th>Reason Code</th>
                        <th>Urgency</th>
                    </tr>
                    <tr>
                        <td><font class="promptReadOnly"><%=request.getParameter("reasonCdDesc")==null?"":request.getParameter("reasonCdDesc")%></font></td>
                        <td><font class="promptReadOnly"><%=request.getParameter("severity")==null?"":request.getParameter("severity")%></font></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td colspan="3" height="10"></td>
        </tr>
        <tr>
            <td colspan="3">
                <font class="promptReadOnly">Captured Notes:</font>
            </td>
        </tr>
        <tr>
            <td colspan="3" height="10"></td>
        </tr>
        <tr>
            <td width="10"></td>
            <td colspan="2"><font class="promptReadOnly"><%=request.getParameter("notes")==null?"":request.getParameter("notes")%></font></td>      
        </tr>
        <tr>
            <td colspan="3" height="10"></td>
        </tr>       
        
    </table>    
</form>
</html>
<%      
}catch (Exception eExp) {
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}       
%>