<!-- $Header: canon_E193_csTicketSummaryB1Controller.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |  canon_E193_csTicketSummaryB1Controller.jsp - billing issue Controller
 |   
 | DESCRIPTION
 |   For a given criteria insert records and forwards it to respective jsp page
 |
 | AUTHOR
 |  Subba 
 |
 | CREATION DATE
 |  10/08/2005
 |
 | HISTORY
 | DATE         WHO                 WHY
 | 18-Dec-2006    Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes
 | 05-Feb-2013	  Hema 				 ITG#434465 : To handle Non-Serialized accessories.
 | 29-Jan-2016    Mangala Shenoy	 Modified for S21 changes
 +=======================================================================--%>
<%@page language="java" %> 
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AcctInfoObj" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketHeaderObj" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketLinesObj" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketSubLinesObj" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_BillingIssue" id="objBillDao" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_NonBillingIssue" id="objNonBillDao" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objHeaderLinesList" scope="request" />
<jsp:setProperty name="objHeaderLinesList" property="*" />
<%
    try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%  System.out.println("in canon_E193_csTicketSummaryB1Controller.jsp");
        String strPageFrom = request.getParameter("hPageFrom");
	//	String strBaseUsageFlag = request.getParameter("usageFlag");
	//	System.out.println("strBaseUsageFlag : " + strBaseUsageFlag);
        Canon_E193_AcctInfoObj objSessionAcctInfo = (Canon_E193_AcctInfoObj)session.getAttribute("objSessionAcctInfo");
        //System.out.println("in canon_E193_csTicketSummaryB1Controller.jsp 2");
        
        String strAcctName = objSessionAcctInfo.getAcctName();
        String strAcctNo = objSessionAcctInfo.getAcctNum();
        String strContactName = objSessionAcctInfo.getContactName();
        String strContactNo = objSessionAcctInfo.getContactNum();
        int iCustAcctId = objSessionAcctInfo.getAcctId();
        String strPOReqFlag = objSessionAcctInfo.getPORequiredFlag();
        //System.out.println("in canon_E193_csTicketSummaryB1Controller.jsp iCustAcctId is ");
        System.out.println("in canon_E193_csTicketSummaryB1Controller.jsp iCustAcctId is " + iCustAcctId);
        //Get Org ID
        int iOrgId = objCiSession.getOrgId();
        /* ITG# 73987 : Begin */    
        String strRegionCode = (String)objCiSession.getRegionCode();    
        /* ITG# 73987 : End */
        System.out.println("in canon_E193_csTicketSummaryB1Controller.jsp " + strRegionCode);
        // get userId
        String iUserId = objCiSession.getUserId();
        
        // get resource id
        //Start changes for S21 by Mangala
        //int iResourceId = objCiSession.getResourceId();
        String iResourceId = objCiSession.getResourceId();		
        //End changes for S21 by Mangala
        // get ticket no
        int iTicketId = 0;
        int iLineId = 0;
        String strTicketId = request.getParameter("iTicketId")==null?"":request.getParameter("iTicketId");
        if(!"".equals(strTicketId)) iTicketId = Integer.parseInt(strTicketId);
        String strLineId = request.getParameter("iLineId")==null?"":request.getParameter("iLineId");
        if(!"".equals(strLineId)) iLineId = Integer.parseInt(strLineId);
        
        String strReadData = request.getParameter("readData")==null?"":request.getParameter("readData");
        String strFlag = request.getParameter("flag")==null?"":request.getParameter("flag");

        String strReasonCd = request.getParameter("hReasonCd")==null?"":request.getParameter("hReasonCd");
        String strReasonCdDesc = request.getParameter("reasonCdDesc")==null?"":request.getParameter("reasonCdDesc");
        //Start changes for S21 by Mangala
        String strpriorTktNumber = request.getParameter("priorTktNumber")==null?"":request.getParameter("priorTktNumber");
        String strfrequency = request.getParameter("frequency")==null?"":request.getParameter("frequency");
        String strfrequencyBase = request.getParameter("frequencyBase")==null?"":request.getParameter("frequencyBase");
        String strNotes = "";
        //int icnt =0;
        if (!"".equals(strfrequency) &&  !" ***Bill Usage Frequency: ".equals(strfrequency)){
        
        	strNotes = strNotes.concat(strfrequency.concat("***"));
        //icnt =1;
        }
        if(!"".equals(strfrequencyBase) &&  !" ***Bill Base Frequency: ".equals(strfrequencyBase)){
        	strNotes = strNotes.concat(strfrequencyBase.concat("***"));
        }
        
        strNotes = request.getParameter("notes")==null?strNotes:request.getParameter("notes").concat(strNotes);
        
        //Uncomment below code if Prior Tkt No needs to be populated in the notes
     /*   if (!"".equals(strpriorTktNumber) && icnt ==1){
            strNotes = strNotes.concat(" Prior Ticket Number: ").concat(strpriorTktNumber).concat(" ***");
            }
        else {
        	strNotes = strNotes.concat(" ***Prior Ticket Number: ").concat(strpriorTktNumber).concat(" ***");
        }*/
        
        //End Changes for S21 by Mangala
        String strCatId = request.getParameter("hCatId");
        String strParentCatId = request.getParameter("hParentCatId");
        String strCatCode = request.getParameter("hCatCode");
        String strCatDesc = request.getParameter("hCatDesc");
        String strSeverity = request.getParameter("severity")==null?"":request.getParameter("severity");
        String strInvNo = request.getParameter("invoiceNumber");
		String strAggregateContractNum = request.getParameter("aggregateContractNum")==null?"":request.getParameter("aggregateContractNum");
		String vBillType = request.getParameter("vBillType")==null?"":request.getParameter("vBillType");
		String strConsolidateBillNum = request.getParameter("consolidateBillNum")==null?"":request.getParameter("consolidateBillNum");
        // for contracts
		String[] strInvNumbers = request.getParameterValues("invNumbers");
		System.out.println("in canon_E193_csTicketSummaryB1Controller.jsp  strParentCatId is " + strParentCatId  + "lines strCatId :" + strCatId);
		// spothuri   strInvNumbers is null when coming from supply line details. 
		if(strInvNumbers != null && strInvNumbers[0] != null )
		{
			int test1 = 1	;
		}
		else
		{
			strInvNumbers = new String[1]; 
			strInvNumbers[0] = strInvNo;
		}
		String[] strCorrSerialNo = request.getParameterValues("corrSerialNo");
        String[] strCorrCatId = request.getParameterValues("corrCatId");
        String[] strDtlSerialNo = request.getParameterValues("dtlSerialNo");
        String[] strDtlCatId = request.getParameterValues("dtlCatId");
        String[] strCorrContactLineId = request.getParameterValues("corrContractLineId");
        String[] strDtlContactLineId = request.getParameterValues("dtlContractLineId");
        String[] strBaseSerialNo = request.getParameterValues("baseSerialNo");
		String[] baseinvNumbers = request.getParameterValues("baseinvNumbers");
		String[] priceinvNumbers = request.getParameterValues("priceinvNumbers");
		String[] readinvNumbers = request.getParameterValues("readinvNumbers");
		
		String[] physMtrLbCd = request.getParameterValues("physMtrLbCd");
		
        String[] strBaseContractLineId = request.getParameterValues("baseContractLineId");
        String[] strOldStartTHRead = request.getParameterValues("oldStartTHRead");
        String[] strOldEndTHRead = request.getParameterValues("oldEndTHRead");
        //String[] strOldStartBWHardRead = request.getParameterValues("oldStartBWHardRead");
        //String[] strOldEndBWHardRead = request.getParameterValues("oldEndBWHardRead");
        //String[] strOldStartColorRead = request.getParameterValues("oldStartColorRead");
        //String[] strOldEndColorRead = request.getParameterValues("oldEndColorRead");
        String[] strOldTestCopies = request.getParameterValues("oldTestCopies");
        String[] strOldStartMeterDate = request.getParameterValues("oldStartMeterDate");
        String[] strOldEndMeterDate = request.getParameterValues("oldEndMeterDate");
		String[] strBillCounterName	= request.getParameterValues("billingCounterName");
		String[] strPricebillingCounterName	= request.getParameterValues("pricebillingCounterName");
        String[] strNewStartTHRead = request.getParameterValues("newStartTHRead");
        String[] strNewEndTHRead = request.getParameterValues("newEndTHRead");
        //String[] strNewStartBWHardRead = request.getParameterValues("newStartBWHardRead");
        //String[] strNewEndBWHardRead = request.getParameterValues("newEndBWHardRead");
        //String[] strNewStartColorRead = request.getParameterValues("newStartColorRead");
        //String[] strNewEndColorRead = request.getParameterValues("newEndColorRead");
        String[] strNewTestCopies = request.getParameterValues("newTestCopies");
        String[] strOldTier1CopyVol = request.getParameterValues("oldTier1CopyVol");
        String[] strOldTier1Rate = request.getParameterValues("oldTier1Rate");
        String[] strOldTier2CopyVol = request.getParameterValues("oldTier2CopyVol");
        String[] strOldTier2Rate = request.getParameterValues("oldTier2Rate");
        String[] strOldTier3CopyVol = request.getParameterValues("oldTier3CopyVol");
        String[] strOldTier3Rate = request.getParameterValues("oldTier3Rate");
        String[] strOldTier4CopyVol = request.getParameterValues("oldTier4CopyVol");
        String[] strOldTier4Rate = request.getParameterValues("oldTier4Rate");
        String[] strNewTier1CopyVol = request.getParameterValues("newTier1CopyVol");
        String[] strNewTier1Rate = request.getParameterValues("newTier1Rate");
        String[] strNewTier2CopyVol = request.getParameterValues("newTier2CopyVol");
        String[] strNewTier2Rate = request.getParameterValues("newTier2Rate");
        String[] strNewTier3CopyVol = request.getParameterValues("newTier3CopyVol");
        String[] strNewTier3Rate = request.getParameterValues("newTier3Rate");
        String[] strNewTier4CopyVol = request.getParameterValues("newTier4CopyVol");
        String[] strNewTier4Rate = request.getParameterValues("newTier4Rate");
        String[] strBaseOldRate = request.getParameterValues("baseOldRate");
        String[] strBaseNewRate = request.getParameterValues("baseNewRate");
        String strBaseCatId = request.getParameter("baseCatId")==null?"":request.getParameter("baseCatId");
        String[] strBaseBilledFrom = request.getParameterValues("baseBilledFrom");
        String[] strBaseBilledTo = request.getParameterValues("baseBilledTo");
        // for others(supply,  service, AR Manual, Misc, Merchandise)
        String[] strSCatId = request.getParameterValues("supplyCatId");
        String[] strSCustTrxLineId = request.getParameterValues("supplyCustTrxLineId");
        String[] strSQuantity = request.getParameterValues("supplyQuantity");
        String[] strSUnitPrice = request.getParameterValues("supplyUnitPrice");
        String[] strSChkCredit = request.getParameterValues("supplyChkCredit");
        String[] strSCreditQty = request.getParameterValues("supplyCreditQty");
        String[] strSCreditAmt = request.getParameterValues("supplyCreditAmt");
        String[] strSSelCredit = request.getParameterValues("supplySelCredit");
        String[] readInstanceNum = request.getParameterValues("readInstanceNum");
        String[] priceInstanceNum = request.getParameterValues("priceInstanceNum");        
        String[] baseInstanceNum = request.getParameterValues("baseInstanceNum");
        String[] svcInvLinePk = request.getParameterValues("isvcInvLinePk");
        
        String[] priSvcInvLinePk = request.getParameterValues("priSvcInvLinePk");
        String[] baseSvcInvLinePk = request.getParameterValues("baseSvcInvLinePk");
        
        
        //System.out.println("Invoice line pk !!! "+ svcInvLinePk);
        //System.out.println("instance num !!!!!!!!! Meter " + Arrays.toString(readInstanceNum) + " Price "+Arrays.toString(priceInstanceNum) + " Base " + Arrays.toString(baseInstanceNum));
        int iTotalLineCounts = 0;
        int iCorrLength = 0, iDtlLength = 0, iBaseLength = 0, iSupplyLength = 0;
        if(strCorrSerialNo != null && strCorrSerialNo.length > 0) {
            iCorrLength = strCorrSerialNo.length;
            for(int i=0; i<iCorrLength; i++) {
                if(!"".equals(strNewStartTHRead[i].trim())) ++iTotalLineCounts;
                if(!"".equals(strNewEndTHRead[i].trim())) ++iTotalLineCounts;
                if(!"".equals(strNewTestCopies[i].trim())) ++iTotalLineCounts;

            }
        }
		
        if(strDtlSerialNo != null && strDtlSerialNo.length > 0) {
            iDtlLength = strDtlSerialNo.length;
            for(int i=0; i<iDtlLength; i++) {
                if(!"".equals(strNewTier1CopyVol[i].trim())) ++iTotalLineCounts;
                if(!"".equals(strNewTier1Rate[i].trim())) ++iTotalLineCounts;
                if(!"".equals(strNewTier2CopyVol[i].trim())) ++iTotalLineCounts;
                if(!"".equals(strNewTier2Rate[i].trim())) ++iTotalLineCounts;
                if(!"".equals(strNewTier3CopyVol[i].trim())) ++iTotalLineCounts;
                if(!"".equals(strNewTier3Rate[i].trim())) ++iTotalLineCounts;
                if(!"".equals(strNewTier4CopyVol[i].trim())) ++iTotalLineCounts;
                if(!"".equals(strNewTier4Rate[i].trim())) ++iTotalLineCounts;
            }
        }
		if(strBaseSerialNo != null && strBaseSerialNo.length > 0) {
            iBaseLength = strBaseSerialNo.length;
            for(int i=0; i<iBaseLength; i++) {
                if(!"".equals(strBaseNewRate[i].trim())) ++iTotalLineCounts;
            }
        }		
	System.out.println("iBaseLength1 : " + iBaseLength + " iTotalLineCounts: " + iTotalLineCounts);
	System.out.println("strSCatId : " + strSCatId + "strBaseCatId : " + strBaseCatId );
        // for others(supply, service, AR Manual, Misc, Merchandise)        
        if(strSChkCredit != null && strSChkCredit.length > 0) {
            iSupplyLength = strSChkCredit.length;
            iTotalLineCounts = iSupplyLength;
        }
        System.out.println("iBaseLength2 : " + iBaseLength + " iTotalLineCounts: " + iTotalLineCounts);     
        Canon_E193_TicketLinesObj[] objLines = new Canon_E193_TicketLinesObj[1];
        Canon_E193_TicketSubLinesObj[] objSubLines = new Canon_E193_TicketSubLinesObj[iTotalLineCounts];
        
        // setting lines values to object
        objLines[0] = new Canon_E193_TicketLinesObj();
        objLines[0].setOrgId(iOrgId);
        objLines[0].setCatId(Integer.parseInt(strCatId));
        objLines[0].setLineStatus("UNASSIGNED");
        objLines[0].setSeverity(strSeverity);
        objLines[0].setReasonCd(strReasonCd);
        objLines[0].setReason(strReasonCdDesc);
        objLines[0].setJtfNotesFlag("Y");
        objLines[0].setCreatedBy(iUserId);
        System.out.println("iBaseLength3 : " + iBaseLength + " iTotalLineCounts: " + iTotalLineCounts);	
        //setting sublines
        int m = 0;
        for(int i=0; i<iCorrLength; i++) {
            for(int j=0; j<3; j++) {
                if(j == 0) {
                    if("".equals(strNewStartTHRead[i].trim())) continue;
                    else {
                        objSubLines[m] = new Canon_E193_TicketSubLinesObj();
                        objSubLines[m].setCurrentValue(strOldStartTHRead[i]);
                        objSubLines[m].setNewValue(strNewStartTHRead[i]);
                    }
                }else if(j == 1) {
                    if("".equals(strNewEndTHRead[i].trim())) continue;
                    else {
                        objSubLines[m] = new Canon_E193_TicketSubLinesObj();
                        objSubLines[m].setCurrentValue(strOldEndTHRead[i]);
                        objSubLines[m].setNewValue(strNewEndTHRead[i]);
                    }
                }else if(j == 2) {
                    if("".equals(strNewTestCopies[i].trim())) continue;
                    else {
                        objSubLines[m] = new Canon_E193_TicketSubLinesObj();
                        objSubLines[m].setCurrentValue(strOldTestCopies[i]);
                        objSubLines[m].setNewValue(strNewTestCopies[i]);
                    }
                }
                objSubLines[m].setNewFlag("N");
                objSubLines[m].setCrFlag("N");
                objSubLines[m].setCompanyMoveFlag("N");
                objSubLines[m].setCancelEquipFlag("N");
                objSubLines[m].setObjectType("USAGE_LINE_ID");
				objSubLines[m].setInvoiceNumbers(readinvNumbers[i]);
				objSubLines[m].setAttribute3(strBillCounterName[i]);
				
				objSubLines[m].setAttribute4(physMtrLbCd[i]);
				
                objSubLines[m].setObjectValue(strCorrContactLineId[i]);
                objSubLines[m].setSerialNo(strCorrSerialNo[i]);
                objSubLines[m].setCatId(Integer.parseInt(strCorrCatId[j]));
                objSubLines[m].setAttribute1(strOldStartMeterDate[i]);
                objSubLines[m].setAttribute2(strOldEndMeterDate[i]);
                
                objSubLines[m].setAttribute5(svcInvLinePk[i]);
                
                if ( readInstanceNum[i] != null && !"".equals(readInstanceNum[i]) && !"null".equals(readInstanceNum[i]))
                {
					objSubLines[m].setProductNumber(readInstanceNum[i]);
                }
                ++m;
                //System.out.println("strCorrCatId[j] : " + strCorrCatId[j]);
            }
        }
        System.out.println("iBaseLength4 : " + iBaseLength + " iTotalLineCounts: " + iTotalLineCounts + "iDtlLength " + iDtlLength );
        for(int i=0; i<iDtlLength; i++) {
            for(int j=0; j<8; j++) {
            	//System.out.println("j is : " + j + strNewTier1CopyVol[i]);   
                if(j == 0) {
                	//System.out.println("j 0 is : " + j + strNewTier1CopyVol[i]);   
                    if("".equals(strNewTier1CopyVol[i].trim())) continue;
                    else {
                    	//System.out.println("in else old is : " + strOldTier1CopyVol[i] + "new is " + strNewTier1CopyVol[i]);  
                        objSubLines[m] = new Canon_E193_TicketSubLinesObj();
                        objSubLines[m].setCurrentValue(strOldTier1CopyVol[i]);
                        objSubLines[m].setNewValue(strNewTier1CopyVol[i]); 
                    }
                }else if(j == 1) {
                	//System.out.println("j 1 is : " + j + strNewTier1CopyVol[i]);   
                    if("".equals(strNewTier1Rate[i].trim())) continue;
                    else {
                        objSubLines[m] = new Canon_E193_TicketSubLinesObj();
                        objSubLines[m].setCurrentValue(strOldTier1Rate[i]);
                        objSubLines[m].setNewValue(strNewTier1Rate[i]);
                    }
                }else if(j == 2) {
                	//System.out.println("j 2 is : " + j + strNewTier1CopyVol[i]);   
                    if("".equals(strNewTier2CopyVol[i].trim())) continue;
                    else {
                        objSubLines[m] = new Canon_E193_TicketSubLinesObj();
                        objSubLines[m].setCurrentValue(strOldTier2CopyVol[i]);
                        objSubLines[m].setNewValue(strNewTier2CopyVol[i]);
                    }
                }else if(j == 3) {
                	//System.out.println("j 3 is : " + j + strNewTier1CopyVol[i]);   
                    if("".equals(strNewTier2Rate[i].trim())) continue;
                    else {
                        objSubLines[m] = new Canon_E193_TicketSubLinesObj();
                        objSubLines[m].setCurrentValue(strOldTier2Rate[i]);
                        objSubLines[m].setNewValue(strNewTier2Rate[i]);
                    }
                }else if(j == 4) {
                	//System.out.println("j 4 is : " + j + strNewTier1CopyVol[i]);   
                    if("".equals(strNewTier3CopyVol[i].trim())) continue;
                    else {
                        objSubLines[m] = new Canon_E193_TicketSubLinesObj();
                        objSubLines[m].setCurrentValue(strOldTier3CopyVol[i]);
                        objSubLines[m].setNewValue(strNewTier3CopyVol[i]);
                    }
                }else if(j == 5) {
                	//System.out.println("j 5 is : " + j + strNewTier1CopyVol[i]);  
                    if("".equals(strNewTier3Rate[i].trim())) continue;
                    else {
                        objSubLines[m] = new Canon_E193_TicketSubLinesObj();
                        objSubLines[m].setCurrentValue(strOldTier3Rate[i]);
                        objSubLines[m].setNewValue(strNewTier3Rate[i]);
                    }
                }else if(j == 6) {
                	//System.out.println("j 6 is : " + j + strNewTier1CopyVol[i]);  
                    if("".equals(strNewTier4CopyVol[i].trim())) continue;
                    else {
                        objSubLines[m] = new Canon_E193_TicketSubLinesObj();
                        objSubLines[m].setCurrentValue(strOldTier4CopyVol[i]);
                        objSubLines[m].setNewValue(strNewTier4CopyVol[i]);
                    }
                }else if(j == 7) {
                	//System.out.println("j 7 is : " + j + strNewTier1CopyVol[i]);  
                    if("".equals(strNewTier4Rate[i].trim())) continue;
                    else {
                        objSubLines[m] = new Canon_E193_TicketSubLinesObj();
                        objSubLines[m].setCurrentValue(strOldTier4Rate[i]);
                        objSubLines[m].setNewValue(strNewTier4Rate[i]);
                    }
                }
                //System.out.println("m  1 is : " + m);   
                objSubLines[m].setNewFlag("N");
                objSubLines[m].setCrFlag("N");
                objSubLines[m].setCompanyMoveFlag("N");
                objSubLines[m].setCancelEquipFlag("N");
                objSubLines[m].setObjectType("PRICING_LINE_ID");
				objSubLines[m].setAttribute3(strPricebillingCounterName[i]);
				objSubLines[m].setInvoiceNumbers(priceinvNumbers[i]);
                objSubLines[m].setObjectValue(strDtlContactLineId[i]);
                objSubLines[m].setSerialNo(strDtlSerialNo[i]);
                objSubLines[m].setAttribute5(priSvcInvLinePk[i]);
                //System.out.println("m 2 is : " + m );   
                //Start Changes for S21 by Mangala
                objSubLines[m].setCatId(Integer.parseInt(strDtlCatId[j]));
               //objSubLines[m].setCatId(0);
                //End Changes for S21 by Mangala 		
                //System.out.println("m  3 is : " + m);
                if ( priceInstanceNum[i] != null && !"".equals(priceInstanceNum[i]) && !"null".equals(priceInstanceNum[i]))
                {
					objSubLines[m].setProductNumber(priceInstanceNum[i]);
                }
                ++m;
                //System.out.println("m aFTER  is : " + m);   
            }
        }
        System.out.println("iBaseLength5 : " + iBaseLength + " iTotalLineCounts : " + iTotalLineCounts + "objSubLines.length : " + objSubLines.length);
        for(int i=0; i<iBaseLength; i++) {
        	//System.out.println("iBaseLength6 in for : " + iBaseLength);
            if("".equals(strBaseNewRate[i].trim())) continue;
            else {
                objSubLines[m] = new Canon_E193_TicketSubLinesObj();
                objSubLines[m].setCurrentValue(strBaseOldRate[i]);
                objSubLines[m].setNewValue(strBaseNewRate[i]); 
            }
            //System.out.println("iBaseLength7 in for later : " + iBaseLength);
            objSubLines[m].setNewFlag("N");
            objSubLines[m].setCrFlag("N");
            objSubLines[m].setCompanyMoveFlag("N");
            objSubLines[m].setCancelEquipFlag("N");
            objSubLines[m].setObjectType("BASE_LINE_ID");
            objSubLines[m].setObjectValue(strBaseContractLineId[i]);
			objSubLines[m].setInvoiceNumbers(baseinvNumbers[i]);
            objSubLines[m].setSerialNo(strBaseSerialNo[i]);
            //Start changes for S21 by Mangala
           objSubLines[m].setCatId(Integer.parseInt(strBaseCatId));
           // objSubLines[m].setCatId(0);
            //End Changes for S21 by Mangala
            // MW Project Changes
            objSubLines[m].setAttribute1(strBaseBilledFrom[i]);
            objSubLines[m].setAttribute2(strBaseBilledTo[i]); 
            objSubLines[m].setAttribute5(baseSvcInvLinePk[i]);
            if ( baseInstanceNum[i] != null && !"".equals(baseInstanceNum[i]) && !"null".equals(baseInstanceNum[i]))
            {
				objSubLines[m].setProductNumber(baseInstanceNum[i]);
            }
            ++m;
        }
		System.out.println(" iSupplyLength = "+iSupplyLength);
        // for others(supply, service, AR Manual, Misc, Merchandise)
        for(int i=0; i<iSupplyLength; i++) {
			int idx = Integer.parseInt(strSChkCredit[i]);
		//System.out.println(" idx = "+idx);
		//System.out.println(" i = "+i);
		//System.out.println(" strInvNumbers[0] = "+strInvNumbers[0]);
		
            objSubLines[m] = new Canon_E193_TicketSubLinesObj();
			
            if("".equalsIgnoreCase(strSCreditQty[idx])) {
                objSubLines[m].setCatId(Integer.parseInt(strSCatId[1]));
                objSubLines[m].setCurrentValue(strSUnitPrice[idx]);
                objSubLines[m].setNewValue(strSCreditAmt[idx]); 
            }else{
                objSubLines[m].setCatId(Integer.parseInt(strSCatId[0]));
                objSubLines[m].setCurrentValue(strSQuantity[idx]);
                objSubLines[m].setNewValue(strSCreditQty[idx]); 
            }
			
            objSubLines[m].setNewFlag("N");
            objSubLines[m].setCrFlag("Y");
            objSubLines[m].setCompanyMoveFlag("N");
            objSubLines[m].setCancelEquipFlag("N");
			objSubLines[m].setInvoiceNumbers(strInvNumbers[0]);
            objSubLines[m].setObjectType("INV_LINE_ID");
            objSubLines[m].setObjectValue(strSCustTrxLineId[idx]);
			
            if(strSSelCredit != null) objSubLines[m].setCreditReason(strSSelCredit[idx]);
            ++m;
        }
		
        //System.out.println(" 1 " );
        if("".equals(strReadData) || "null".equals(strReadData)) {
            String strOrigName = request.getParameter("origName");
            String strOrigPhNo = request.getParameter("origPhNo");
            String strOrigEmailAddress = request.getParameter("origEmailAddress");
            String strorigCheckbox = request.getParameter("origCheckbox");
            String strOrigType = request.getParameter("origType");
            String strSourceType = request.getParameter("sourceType");
            String strCustName = request.getParameter("custName");
            String strCustPhNo = request.getParameter("custPhNo");
            String strCustEmailAddress = request.getParameter("custEmailAddress");
            String strRecurProb = request.getParameter("recurProb");
            String strProbType = request.getParameter("probType");
            String strVal1 = request.getParameter("val1");
            String strSelAcctId = request.getParameter("selAcctId");
            String strSelLocId = request.getParameter("selLocId");
            String strSelAcctName = request.getParameter("selAcctName");
            String strSelAcctNum = request.getParameter("selAcctNum");
            String strContractNo = request.getParameter("strContractNo")==null?"":request.getParameter("strContractNo");
            String strContactNoMod = request.getParameter("strContactNoMod")==null?"":request.getParameter("strContactNoMod");
            String strOrderNo = request.getParameter("strOrderNo")==null?"":request.getParameter("strOrderNo");
            String strOrderType = request.getParameter("strOrderType")==null?"":request.getParameter("strOrderType");
            String strServiceBranch = request.getParameter("strServiceBranch")==null?"":request.getParameter("strServiceBranch");
            //System.out.println(" 2 ");
            Canon_E193_TicketHeaderObj[] objHeaders = new Canon_E193_TicketHeaderObj[1];
            System.out.println(" strTicketId = "+ strTicketId);
            if("".equals(strTicketId)) {
                objHeaders[0] = new Canon_E193_TicketHeaderObj();
                // setting header values to object
                objHeaders[0].setCatId(Integer.parseInt(strParentCatId));
                objHeaders[0].setStatus("UNASSIGNED");
                if("no".equalsIgnoreCase(strRecurProb))
                    objHeaders[0].setRecurring("N");
                else objHeaders[0].setRecurring("Y");
                objHeaders[0].setBillingIssue("Y");
                objHeaders[0].setOrgId(iOrgId);
                
                /* ITG# 73987 : Begin */
                objHeaders[0].setAttribute6(strRegionCode);
                /* ITG# 73987 : End */
                
                objHeaders[0].setCustAcctId(iCustAcctId);
                objHeaders[0].setCustomerName(strAcctName);
                objHeaders[0].setCustomerNo(strAcctNo);
                objHeaders[0].setOrigName(strOrigName);
                objHeaders[0].setOrigPhNo(strOrigPhNo);
                objHeaders[0].setOrigEmail(strOrigEmailAddress);
                objHeaders[0].setsendEmailFlag(strorigCheckbox);
                objHeaders[0].setOrigType(strOrigType);
             // Set Customer Source Type : Start
                objHeaders[0].setAttribute9(strSourceType);
             // Set Customer Source Type : End  
                if("customer".equalsIgnoreCase(strOrigType)) {
                    objHeaders[0].setCustContact(strOrigName);
                    objHeaders[0].setCustPhNo(strOrigPhNo);
                    objHeaders[0].setCustEmail(strOrigEmailAddress);
                }else{
                    objHeaders[0].setCustContact(strCustName);
                    objHeaders[0].setCustPhNo(strCustPhNo);
                    objHeaders[0].setCustEmail(strCustEmailAddress);
                }
                objHeaders[0].setJtfNotesFlag("N");
                objHeaders[0].setCreatedBy(iUserId);
                objHeaders[0].setCreatedByResId(iResourceId);
                objHeaders[0].setInvoiceNo(strInvNo);
				objHeaders[0].setAggregateContractNum(strAggregateContractNum);
				objHeaders[0].setConsolidateBillNum(strConsolidateBillNum);
                objHeaders[0].setContractNo(strContractNo);
                objHeaders[0].setContractModifier(strContactNoMod);
                if(strOrderNo != null && !"".equals(strOrderNo) && !"null".equals(strOrderNo)) 
                    objHeaders[0].setOrderNo(Integer.parseInt(strOrderNo));
                objHeaders[0].setOrderType(strOrderType);
                if(!"".equals(strServiceBranch) && !"null".equals(strServiceBranch)) 
                    objHeaders[0].setAttribute1(strServiceBranch);
                if(!"".equals(strPOReqFlag) && !"null".equals(strPOReqFlag)) 
                    objHeaders[0].setAttribute2(strPOReqFlag);
            }
            
            //System.out.println(" 3 ");
            
            if("".equals(strTicketId)) {
                //System.out.println(" in if to call  addBillHeaderLineSubLines " + objHeaders.length +  objLines.length +  objSubLines.length );
               /* System.out.println(" in if to call  addBillHeaderLineSubLines " + Arrays.toString(objHeaders) + " ---------------- "+ Arrays.toString(objLines) 
                		+ " ---------------- " + Arrays.toString(objSubLines) + " ---------------- " +strNotes); */
                iTicketId = objBillDao.addBillHeaderLineSubLines(objHeaders, objLines, objSubLines, strNotes);
                
            }else{
            	System.out.println(" in else to call  addBillSubLines ");
                objLines[0].setTicketId(iTicketId);
                objBillDao.addBillSubLines(iTicketId, iOrgId, objLines, objSubLines, strNotes);
            }
        }
        System.out.println(" in if after call to addBillHeaderLineSubLines " + iTicketId);
        if("update".equals(strFlag)) {
        	System.out.println(" in if to update line sub line ");
            // setting lines values to object
            objLines[0].setLineId(iLineId);
            objLines[0].setTicketId(iTicketId);
            objBillDao.updateLineSubLine(iTicketId, iOrgId, objLines, objSubLines, strNotes);
        }
		
        ArrayList alHLList = new ArrayList();
        if(iTicketId > 0) {
            alHLList = objNonBillDao.getNonBillHeaderLines(iOrgId, iTicketId);
            //System.out.println("~TicketSummaryB1Controller alHLList ~"+alHLList + " ~~ "+iOrgId + " ~~ "+ iTicketId);
        }
        objHeaderLinesList.setArrayList(alHLList);
%>
        <form name="nonBillingIssueControlForm" method="post">
            <jsp:forward page="canon_E193_csTicketSummary.jsp">
                <jsp:param name="hPageFrom" value="TicketSummaryB1Controller" />
                <jsp:param name="nextPage" value="" />
                <jsp:param name="iTicketId" value="<%=String.valueOf(iTicketId)%>" />
                <jsp:param name="controlForm" value="B"/>
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