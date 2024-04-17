<!-- $Header: canon_E193_csEIContLineUpdate.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |  canon_E193_csEIContLineUpdate.jsp - contact line details
 |   
 | DESCRIPTION
 |   Details of the incorrect billing issue details.
 |
 | AUTHOR
 |  Subba 
 |
 | CREATION DATE
 |  10/06/2005
 |
 | HISTORY
 | DATE         WHO                 WHY
 | 04/21/2008   Venkat V            To avoid creating duplicate tickets
 | 04/14/2009   Chandra Sekhar      Changed For ITG # 179283
 | 10/07/2009   Naveen Khandelwal   MW Project Changes
 | 10/14/2009   Naveen Khandelwal   MW Project Changes
 | 01/31/2013   Hema 				Modified to handle Non-Serialized Accessories 
 +=======================================================================--%>
<%@ page import="com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array"%>
<%@page language="java" %> 
<%@ page import ="java.util.*" %>
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AcctInfoObj" %>
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_OKSBillingDtlsObj" %>
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_BillOutListObj" %>
<%@ page import="com.canon.oracle.custapp.util.CanonCustAppUtil" %>
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_IssueListObj" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objIBList" scope="request" />
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objIssueList" scope="request" />
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Severity" id="objSeverityDao" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Invoice" id="counterReadDao" scope="page"/>
<%@ include file="canon_E193_csTopInc.jsp" %>

<%
	System.out.println("in ContLine Update jsp");
	// Menu Prompt
    strPageName = "Enter & Inquiry";
    isActivePage = false;
    
    // Check page from to show the path
    strPageFrom = request.getParameter("hPageFrom");
    String hPath = request.getParameter("hPath");
    if (hPath == null)
        hPath = strContLineUpdateT1;
    else
        if(hPath.indexOf(strContLineUpdateT1) < 0)
            hPath = hPath + " -> " + strContLineUpdateT1;   
%>
<%@ include file="canon_E193_csMenuInc.jsp" %>

<!-- 21-APR-2008, Venkat Velagala, Start -->
<script language="javascript">
window.history.forward(1);
</script>
<!-- 21-APR-2008, Venkat Velagala, End -->

<script language="JavaScript">

    var tFieldNames = new Array(
                        "newStartTHRead", "newEndTHRead", "newStartBWHardRead", "newEndBWHardRead", 
                        "newStartColorRead", "newEndColorRead", "newTestCopies", 
                        "newTier1CopyVol", "newTier2CopyVol", "newTier3CopyVol", "newTier4CopyVol");

    function action_func1() {
    	resetErroMsg();
        var objForm = document.contLineUpdateForm;
        var reqSelector=".required, .requireds"; 
        if(!validateParams(reqSelector)){
        if(objForm.iLineId.value != '' && objForm.iLineId.value != '0') {
            if(fnValidateForm(objForm)) {
            	var notes = objForm.notes.value;
    			var existingNotes = objForm.notesDisplay.value;
                if(notes == '') {
                	displayErrorMsg("Please provide full details in comments");
                    objForm.notes.focus();
                }else if((notes.length + existingNotes.length) > 32000){
    				displayErrorMsg("Total Size of Notes for an Issue Line is restricted to 32000 characters. Please use the attachment facility to add additional details.");
    				objForm.notes.focus();
    			}else{
                    objForm.flag.value = "update";
                    objForm.readData.value = "yes";
                    objForm.submit();
                }
            }
        }else{
        	var notes = objForm.notes.value;
            if(objForm.reasonCdDesc.value == '') {
            	displayErrorMsg("Please choose a reason code");
            }else if(notes == '') {
            	displayErrorMsg("Please provide full details in comments");
                objForm.notes.focus();
            }else if(notes.length > 32000){
				displayErrorMsg("Total Size of Notes for an Issue Line is restricted to 32000 characters. Please use the attachment facility to add additional details.");
				objForm.notes.focus();
			}else if(fnValidateForm(objForm)) {
                objForm.submit();
            }
        }
     }
    }
    var isTHRFlag = false, isBWFlag = false, isCLRFlag = false;
    function fnValidateForm(objForm) {
    	resetErroMsg();
        var isTrue = true, isFormField = true, isDotValidate = false;
        var errMsg = "End read should be grater than start read";
        var focusField = '';
        var corrSerialNo = 0, dtlSerialNo = 0, baseSerialNo = 0;
        if(objForm.corrSerialNo != null) corrSerialNo = objForm.corrSerialNo.length;
        if(objForm.dtlSerialNo != null) dtlSerialNo = objForm.dtlSerialNo.length;
        if(objForm.baseSerialNo != null) baseSerialNo = objForm.baseSerialNo.length;
        
        var index = objForm.elements.length;
        for(i=0; i<index; i++) {
            if(objForm.elements[i].type == "text" && objForm.elements[i].name != "reasonCdDesc" && objForm.elements[i].value != '') {
                isFormField = false;
                for(j=0; j<tFieldNames.length; j++) {
                    if(objForm.elements[i].name == tFieldNames[j] && objForm.elements[i].value.indexOf(".") != -1) {
                    	displayErrorMsg("Decimal values not allowed for this field");
                        objForm.elements[i].select();
                        objForm.elements[i].focus();
                        isDotValidate = true;
                        break;
                    }
                }
                if(isDotValidate) break;
            }
        }

        if(isFormField) {
        	displayErrorMsg("Please enter any value");
        }
        
        if(isFormField || isDotValidate) 
            return false;
        
		if(corrSerialNo > 0) {
            for(i=0; i<corrSerialNo; i++) {
                isTHRFlag = false; isBWFlag = false; isCLRFlag = false;
                if(fnCheckFieldValue(objForm.newStartTHRead[i], objForm.newEndTHRead[i], "THR")) {
                    focusField = objForm.newEndTHRead[i];
                    isTrue = false;
                    break;
                }
            }
        }else if(objForm.corrSerialNo != null){
            isTHRFlag = false; isBWFlag = false; isCLRFlag = false;
            if(fnCheckFieldValue(objForm.newStartTHRead, objForm.newEndTHRead, "THR")) {
                focusField = objForm.newEndTHRead;
                isTrue = false;
            }
        }
        
        if(isTrue && baseSerialNo > 0 ) 
		{
            for(i=0; i<baseSerialNo; i++) {
                if((objForm.baseNewRate[i].value == '' || isNaN(objForm.baseNewRate[i].value)) &&  !objForm.baseNewRate[i].readOnly ) {
               /* Changed For ITG # 179283 */
               if (objForm.baseOldRate[i].value != 0.0) {
               /* Changed For ITG # 179283 */
                      errMsg = "Please enter the new rate";
                      objForm.baseNewRate[i].select();
                      objForm.baseNewRate[i].focus();
                      isTrue = false;
                      break;
               /* Changed For ITG # 179283 */
               }
               /* Changed For ITG # 179283 */
                }
            }
        }else if(isTrue && objForm.baseNewRate != null && (objForm.baseNewRate.value == '' || isNaN(objForm.baseNewRate.value)) && !objForm.baseNewRate.readOnly ) 
		{
            errMsg = "Please enter the new rate";
            objForm.baseNewRate.select();
            objForm.baseNewRate.focus();
            isTrue = false;
        }
        
        if(!isTrue) {
        	displayErrorMsg(errMsg);
            focusField.select();
            focusField.focus();
        }
        return isTrue;
    }

    function fnCheckFieldValue(obj1, obj2, msg) {
        if(obj1.value != '' && obj2.value != '') {
            if(parseInt(obj1.value) > parseInt(obj2.value)) {
                return true;
            }
        }
        if(obj1.value != '' || obj2.value != ''){
            if(msg == "THR") isTHRFlag = true;
            else if(msg == "BW") isBWFlag = true;
            else if(msg == "CLR") isCLRFlag = true;
        }
        return false;
    }
    
   /* function InvoiceDetails(val1) {
        var vLink = "canon_E193_csActualInvoiceController.jsp?InvNo=" + val1;
        var vWin = window.open(vLink, "newwin","height=300,width=900,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,resizable=yes" );
        vWin.focus();
    }
    
    function fnGetReasonCode() {
        var val = document.contLineUpdateForm.hParentCatCode.value;
        var vWin = window.open("canon_E193_csTicketReasonCodeP.jsp?reasonCode="+val, "newwin","height=300,width=940,toolbar=no,menubar=no,scrollbars=yes,resizable=yes" );
        vWin.focus();
    }*/
    
    function action_func1ReasonCode() {
		resetErroMsg();
		var objForm = document.ReasonCodeForm;
		var isSelected = false;
		var val = '';
		var v = 0;
		if(objForm.thdetails != null) {
			v = objForm.thdetails.length;
			if(v > 0) {
				for(i=0; i<v; i++) {
					if(objForm.thdetails[i].checked) {
						isSelected = true;
						val = objForm.thdetails[i].value;
						break;
					}
				}
			}else if(objForm.thdetails.checked) {
				val = objForm.thdetails.value;
				isSelected = true;
			}
		}
		if(!isSelected) {
			displayErrorMsg("Please select the reasonCode");
		}else {		
			getReasonCode(objForm.reasonCode.value, val);
			closeDlg();
		}
	}
    
    
    function getReasonCode(code, reason) {
        var objForm = document.contLineUpdateForm;
        objForm.reasonCdDesc.value = reason;
        objForm.hReasonCd.value = code;
    }
    
    function extractNumbersAndFirstDot(str){
    	var decimalNumber = 0;
    	if(str != null){
    		if(str.indexOf('.') > 0)
    		  {
    		  	var firstStr = str.substring(0, str.indexOf('.'));
    		    var secStr = str.substring(str.indexOf('.')+1, str.length);
    		    decimalNumber = firstStr.replace(/\D/g, '')+ '.' + secStr.replace(/\D/g, '') ;
    		  	
    		  }else
    		  {
    			  decimalNumber = str.replace(/\D/g, '');
    		  }
    	}
    	return decimalNumber;
    }
    
    function fnValidateField(obj, oldObj) {
    	resetErroMsg();
        var objVal = obj.value;
        if(objVal != '' && isNaN(objVal)) {
        	displayErrorMsg("Enter numbers only");
        	obj.value = extractNumbersAndFirstDot(objVal);
            //obj.select();
            //obj.focus();
        }else if(objVal != '' && (objVal == oldObj)) {
        	displayErrorMsg("New value should not be equal to the old value");
            obj.value = '';
            //obj.select();
            //obj.focus();
        }
    }
    
    function fnValidateEqualValue(obj, oldObj) {
    	resetErroMsg();
        var objVal = obj.value;
        if(objVal != '' && (objVal == oldObj)) {
        	displayErrorMsg("New value should not be equal to the old value");
            obj.value = '';
            //obj.select();
            //obj.focus();
        }
    }
    
    $(function (){
   		$(".inTxtRdly").attr("readonly","readonly").css({"backgroundColor":"CCCCCC"});
    	$(".inTxtRdly").click(function (){
    	  $(this).blur();
    	});
    	
    	$("input.autoInteger").autoNumeric({aSep:'', mDec:'', mNum: 15} );
    	//$("input.autoDecimal4").autoNumeric({aSep:'', mDec: 4} );
    	//$("input.autoDecimal2").autoNumeric({aSep:'', mDec: 2} );
    }); 
    
    
    window.onload = function() {
    	makeStaticHeader('baseChargeTable', 300, 100, 'divBaseHeaderRow', 'divBaseMainContent', 'divBaseRoot', 1.5);
    	makeStaticHeader('priceChargeTable', 400, 100, 'divPriceHeaderRow', 'divPriceMainContent', 'divPriceRoot', 1.5);
    	makeStaticHeader('readChargeTable', 300, 100, 'divReadHeaderRow', 'divReadMainContent', 'divReadRoot', 1.5);
   	} 
//-->
</script>
<%
    //Get Org ID
    ArrayList finalBaseArrayList = new ArrayList();

	ArrayList finalPriceArrayList = new ArrayList();
	
	ArrayList finalReadArrayList = new ArrayList();
	
	Canon_E193_OKSBillingDtlsObj objBill2 = null;

	Canon_E193_OKSBillingDtlsObj tempholder = null;
    int iOrgId = objCiSession.getOrgId();
   String strUsageInvType = "";
   String strBaseInvType = "";
    Object objAcct = session.getAttribute("objSessionAcctInfo");
    Canon_E193_AcctInfoObj objSessionAcctInfo = null;
    if(objAcct != null) objSessionAcctInfo = (Canon_E193_AcctInfoObj)objAcct;
    ArrayList newAlReadList = new ArrayList();
    ArrayList ibList = (ArrayList)objIBList.getArrayList();
    System.out.println("!!!!!Line Update ibList"+ ibList.size());
    
    //System.out.println("!!!ibList!!! "+ibList.size());
    //MW Project Changes - Starts
    ArrayList newArrayList = null;
    
    ArrayList alPriceList = new ArrayList();
    ArrayList alReadList = null, alBReadList = null;
   /*  if(ibList != null && ibList.size() > 2) { // Consolidated
        alReadList = (ArrayList)ibList.get(0); // MeterList
        alPriceList = (ArrayList)ibList.get(1); // PriceList
        alBReadList = (ArrayList)ibList.get(2); // BaseList
    } */
   if(ibList != null && ibList.size() > 1 && objIBList.getIsUsageFlag()) { // Usage
       /* alReadList = (ArrayList)ibList.get(0);
        alPriceList = (ArrayList)ibList.get(1);
        
        System.out.println(":::IbListList:::  price list"+ alPriceList); */
        
        strUsageInvType = "USAGE";
        
    }
    ArrayList finalAggregateArrayList = (ArrayList)objIBList.getAggregateList();
    //System.out.println("finalAggregateArrayList "+ finalAggregateArrayList);
    
    
    if(ibList != null && ibList.size() > 0 )
    {
    	int listSize = ibList.size();
    	int pricePos = 0;
    	int aggrePos = 0;
    	int readpos = 0;
    	//System.out.println("finalPriceArrayList!!!!" + listSize);
   	 	if(listSize>1)
   	 	{
   		 	for(int j=0;j<listSize;j++)
   		 	{
   			 	newArrayList=  (ArrayList)ibList.get(j);
   			 	//System.out.println("Printing price list!!! " + newArrayList + "j : " + j);
   			 	if(newArrayList!=null && !newArrayList.isEmpty())
   			 	{
   					objBill2 = (Canon_E193_OKSBillingDtlsObj)newArrayList.get(0);
   			
   			 	 	if( "USAGE".equalsIgnoreCase(objBill2.getInvoiceType()) && ((j % 2) != 0))
	   			 	{
	   				 	finalPriceArrayList.add(pricePos, newArrayList.get(0));
	   				 	pricePos++;
	   			 	}
   			 	 	else if ("USAGE".equalsIgnoreCase(objBill2.getInvoiceType()))
   			 	 	{
   			 	 		finalReadArrayList.add(readpos, newArrayList.get(0));
   			 	 		readpos++;
   			 	 	}
   			 		
   			 	}
   			 
   		 	}
   		 	if(finalPriceArrayList != null && finalPriceArrayList.size() > 0 && finalAggregateArrayList != null && finalAggregateArrayList.size() > 0)
   		 	{
   		 		finalAggregateArrayList.addAll(finalPriceArrayList);
   		 		finalPriceArrayList = finalAggregateArrayList;
   		 	}
   			alPriceList = finalPriceArrayList;
   			alReadList = finalReadArrayList;
   		 	//System.out.println("!!!!!Line Update finalPriceArrayList!!!! "+ finalPriceArrayList.toString() + "finalPriceArrayList!!!! Length: "+ finalPriceArrayList.size());
   		 	//System.out.println("!!!!!Line Update finalReadArrayList!!!! "+ finalReadArrayList.toString() + "finalReadArrayList!!!! Length: "+ finalReadArrayList.size());
   	 	}
    }
    
    
    
    //Newly Added.
     if(ibList != null && ibList.size() > 0 && objIBList.getIsBaseFalge() ) { // Base
    	// ArrayList newArrayList = null;
    	 /* for(int j=0;j<ibList.size();j+=2){
    		 newArrayList=  (ArrayList)ibList.get(j);
    	 } */
    	 int listSize = ibList.size();
    	 int z=0;
    	
    	 if(listSize>1){
    		 for(int j=0;j<listSize;j++){
    			 newArrayList=  (ArrayList)ibList.get(j);
    			 if(newArrayList!=null && !newArrayList.isEmpty())
    			 {
	    			 objBill2 = (Canon_E193_OKSBillingDtlsObj)newArrayList.get(0);
	    			// System.out.println("  Now InvoiceType=  " + objBill2.getInvoiceType());
	    			 if("BASE".equalsIgnoreCase(objBill2.getInvoiceType())){
	    				//System.out.println("  Now BASE "  );
	    				 finalBaseArrayList.add(z++, newArrayList.get(0));
	    			 }
    			 }
    		 }
    		 //System.out.println("FinalBaseArrayList!!!! "+ finalBaseArrayList.toString() + "FinalBaseArrayList!!!! Length: "+ finalBaseArrayList.size());
    	 }else{
    		 newArrayList=  (ArrayList)ibList.get(0);
    		 finalBaseArrayList.add(0, newArrayList.get(0));
    		 //System.out.println("FinalBaseArrayList_else!!!! "+ finalBaseArrayList.toString());
    	 }
    	 alBReadList = finalBaseArrayList;
    	 strBaseInvType= "BASE";
        //alBReadList = (ArrayList)ibList.get(0);
        //System.out.println("!!!!!Line Update FinalBaseArrayList_else!!!! "+ finalBaseArrayList.toString());
    }
     
    ArrayList alIssueList = (ArrayList)objIssueList.getArrayList();
    Canon_E193_OKSBillingDtlsObj objOks = null;
    Canon_E193_IssueListObj objIL = null;
     
    // getting severity codes for drop down list.
    ArrayList alSeverityList = objSeverityDao.getSeverity();
    
    String strInvNo = request.getParameter("invoiceNumber")==null?"":request.getParameter("invoiceNumber");
    //System.out.println("hello in contLine Update jsp strInvNo" + strInvNo );
%> 
<%!
    private String getDisValue(String strVal, Hashtable ht, ArrayList al, int iIdx) {
        int iCatIdVal = 0;
        try{      
        	//System.out.println("!!!! al Length=  " + al.size());
        	//System.out.println("!!!!!strVal = " + strVal);
        	//System.out.println("!!!!!iIdx = " + iIdx);
	   		if(ht == null)
	   			return "";
        	else {
	            if(al != null && al.size() >= iIdx) iCatIdVal = ((Canon_E193_IssueListObj)al.get(iIdx)).getCatId();
	            //System.out.println("!!!!!iCatIdVal= " + iCatIdVal);
	            Object obj = ht.get((strVal+iCatIdVal));
	            //System.out.println("!!!!!obj= " + obj);
	            return (obj==null?"":obj.toString());
        	}
		}catch(Exception e){
			 System.err.println("Error in getDisValue " + e.getMessage() +" al Length  " + al.size());
			  return "";
		}	
    }

%>
	<div id="main_content">
	<div id="page_top">
		<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strContLineUpdateT1 %></h1>
	   	<div class="breadcrumb"><%=hPath%></div>		
	</div>    
    <form name="contLineUpdateForm" action="canon_E193_csTicketSummaryB1Controller.jsp" method="post">

    <input type="hidden" name="hPageFrom" value="EIContLineUpdate">
    <input type="hidden" name="nextPage" value="">
    <input type="hidden" name="hReasonCd" value="<%=request.getParameter("hReasonCd")==null?"":request.getParameter("hReasonCd")%>">
    <input type="hidden" name="hCatId" value="<%=request.getParameter("hCatId")%>">
    <input type="hidden" name="hParentCatId" value="<%=request.getParameter("hParentCatId")%>">
    <input type="hidden" name="hParentCatCode" value="<%=request.getParameter("hParentCatCode")%>">
    <input type="hidden" name="hCatCode" value="<%=request.getParameter("hCatCode")%>">
    <input type="hidden" name="hCatDesc" value="<%=request.getParameter("hCatDesc")%>">
    <input type="hidden" name="InvSource" value="<%=request.getParameter("InvSource")%>">

    <input type="hidden" name="iTicketId" value="<%=request.getParameter("iTicketId")==null?"":request.getParameter("iTicketId")%>">
    <input type="hidden" name="iLineId"  id="iLineId" value="<%=request.getParameter("iLineId")==null?"":request.getParameter("iLineId")%>">
    <input type="hidden" name="flag" value="">
    <input type="hidden" name="readData" value="">

    <input type="hidden" name="origName" value="<%=request.getParameter("origName")%>" >
    <input type="hidden" name="origPhNo" value="<%=request.getParameter("origPhNo")%>">
    <input type="hidden" name="origEmailAddress" value="<%=request.getParameter("origEmailAddress")%>">
    <input type="hidden" name="origCheckbox" value="<%=request.getParameter("origCheckbox")%>">
    <input type="hidden" name="origType" value="<%=request.getParameter("origType")%>" >
    <input type="hidden" name="sourceType" value="<%=request.getParameter("sourceType")%>" >        
    <input type="hidden" name="custName" value="<%=request.getParameter("custName")%>" >
    <input type="hidden" name="custPhNo" value="<%=request.getParameter("custPhNo")%>">
    <input type="hidden" name="custEmailAddress" value="<%=request.getParameter("custEmailAddress")%>">
    
    <input type="hidden" name="recurProb" value="<%=request.getParameter("recurProb")%>">
    <input type="hidden" name="probType" value="<%=request.getParameter("probType")%>">
    <input type="hidden" name="val1" value="<%=request.getParameter("val1")%>">
    
    <input type="hidden" name="selAcctId" value="<%=request.getParameter("selAcctId")%>" >
    <input type="hidden" name="selLocId" value="<%=request.getParameter("selLocId")%>" >
    <input type="hidden" name="selAcctName" value="<%=request.getParameter("selAcctName")%>" >
    <input type="hidden" name="selAcctNum" value="<%=request.getParameter("selAcctNum")%>" >

    <input type="hidden" name="strPurchageOrd" value="<%=request.getParameter("strPurchageOrd")%>">
    <input type="hidden" name="strStatus" value="<%=request.getParameter("strStatus")%>">
    <input type="hidden" name="iTrxId" value="<%=request.getParameter("iTrxId")%>">
    <input type="hidden" name="iTaxOrig" value="<%=request.getParameter("iTaxOrig")%>">
    <input type="hidden" name="iFreightOrig" value="<%=request.getParameter("iFreightOrig")%>">
    <input type="hidden" name="iBillToSiteUseId" value="<%=request.getParameter("iBillToSiteUseId")%>">
    <input type="hidden" name="iShipToSiteUseId" value="<%=request.getParameter("iShipToSiteUseId")%>">
    <input type="hidden" name="invoiceNumber" value="<%=strInvNo%>">
    
    <input type="hidden" name="strContractNo" value="<%=request.getParameter("strContractNo")%>">
    <input type="hidden" name="strContactNoMod" value="<%=request.getParameter("strContactNoMod")%>">
    <input type="hidden" name="strOrderNo" value="<%=request.getParameter("strOrderNo")%>">
    <input type="hidden" name="strOrderType" value="<%=request.getParameter("strOrderType")%>">
	<input type="hidden" name="vBillType" value="<%=request.getParameter("vBillType")%>">
	<input type="hidden" name="aggregateContractNum" value="<%=request.getParameter("aggregateContractNum")%>">
	<input type="hidden" name="consolidateBillNum" value="<%=request.getParameter("consolidateBillNum")==null?"":request.getParameter("consolidateBillNum")%>">
    <input type="hidden" name="freqCount" value="<%=request.getParameter("freqCount")%>">
    <input type="hidden" name="freqBaseCount" value="<%=request.getParameter("freqBaseCount")%>">
<%
    String strInvoiceType = request.getParameter("strInvoiceType")==null?"":request.getParameter("strInvoiceType");
    String strExpirationDate = request.getParameter("strExpirationDate")==null?"":request.getParameter("strExpirationDate");
    String strContractStatus = request.getParameter("strContractStatus")==null?"":request.getParameter("strContractStatus");
    String strServiceBranch = request.getParameter("strServiceBranch")==null?"":request.getParameter("strServiceBranch");
    String strBasePeriod = request.getParameter("strBasePeriod")==null?"":request.getParameter("strBasePeriod");
    String strOveragePeriod = request.getParameter("strOveragePeriod")==null?"":request.getParameter("strOveragePeriod");
    //String strFrequency = request.getParameter("frequency")==null?"":request.getParameter("frequency");
    String strCount = request.getParameter("strCount")==null?"":request.getParameter("strCount");
    //MW Project Changes - Starts
    String strFleetContract = request.getParameter("fleetContract")==null?"":request.getParameter("fleetContract");
    int freqCount = request.getParameter("freqCount")==null?0:Integer.parseInt(request.getParameter("freqCount"));
    int freqBaseCount = request.getParameter("freqBaseCount")==null?0:Integer.parseInt(request.getParameter("freqBaseCount"));
    
    String isAggregateSerialDisable = "";
    String isAggContractAndNotAggSerDisable = "";
   
   // ArrayList linePKList = request.getParameter("listOfInvLinePk")==null?"":request.getParameter("listOfInvLinePk");
   // System.out.println("ArrayList : " + linePKList);
     //MW Project Changes - Ends
	//System.out.println("freqCount : " + freqCount + " strInvoiceType= "+strInvoiceType);
%>
    <input type="hidden" name="strInvoiceType" value="<%=strInvoiceType%>">
    <input type="hidden" name="strExpirationDate" value="<%=strExpirationDate%>">
    <input type="hidden" name="strContractStatus" value="<%=strContractStatus%>">
    <input type="hidden" name="strServiceBranch" value="<%=strServiceBranch%>">
    <input type="hidden" name="strBasePeriod" value="<%=strBasePeriod%>">
    <input type="hidden" name="strOveragePeriod" value="<%=strOveragePeriod%>">
    <%// input type="hidden" name="strFrequency" value="<%=strFrequency%>
    <%//input type="hidden" name="ival" value="<%=request.getParameter("ival")==null?"":request.getParameter("ival")%>
   <% // int ival = alInCorrList.size();//Integer.parseInt(request.getParameter("ival")==null?"":request.getParameter("ival"));
   String freqen="";
   String frequency="";
   int ival =0;
   //System.out.println("!!!!!!!! alReadList.size() = "+alReadList.size());
   //System.out.println("!!!!!!!! alPriceList.size = "+alPriceList.size());
  /* if (alReadList.size() != 0 ){
	   ival = alReadList.size();
	   }
	   else if (alPriceList.size() != 0 ){
		   ival = alPriceList.size();
   }*/
   //System.out.println("!!!!!!!!ival = "+ival);
   boolean isValueExist = false;
   //String value = "";
     
   		for(int m=0; m<freqCount; m++){
   			freqen = request.getParameter("frequency"+m);
   			//System.out.println("Frequency :---------- : "+ freqen + " M " + m);
   			
   			if(freqen != null && !freqen.trim().isEmpty()){
   				frequency = frequency.concat(freqen.concat(","));
   				isValueExist = true;
   			}
   		}
   		if(isValueExist){
   			frequency = " ***Bill Usage Frequency: ".concat(frequency.substring(0, frequency.length()-1));
   		}
   		
   		
   		if(!frequency.equals ("")){
			%>
			<input type="hidden" name="frequency" value="<%=frequency%>">
		<%}
   		frequency = "";
   		isValueExist = false;
   		for(int m=0; m<freqBaseCount; m++){
   			freqen = request.getParameter("frequencyBase"+m);
   			//System.out.println("Frequency :---------- Base : "+ request.getParameter("frequencyBase"+m) + " M " + m);
   			
   			if(freqen != null && !freqen.trim().isEmpty()){
   				frequency = frequency.concat(freqen.concat(","));
   				isValueExist = true;
   			}
   		}
   		
   		if(isValueExist){
   			frequency = " ***Bill Base Frequency: ".concat(frequency.substring(0, frequency.length()-1));
   		}
   		
   		if(!frequency.equals ("") ){
			%>
			<input type="hidden" name="frequencyBase" value="<%=frequency%>">
		<%}
		%>
		
    <input type="hidden" name="strCount" value="<%=strCount%>">
    <input type="hidden" name="hPath" value="<%=hPath%>">
    <table class="request-service" style="align:center;" cellspacing="0" border="0" width="95%">	       
    <% if(objSessionAcctInfo != null) { %>
       <!--  <tr><td height="10" colspan="2"></td></tr> -->
        <tr>
            <td colspan="2" style="margin-top: 5px;"><font class="promptReadOnly">Customer Details:</font></td>
        </tr>
        <tr>
			    <td colspan="2">
			       <div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
						<p id="eMsg"></p>
				   </div>
			   </td>
			</tr>
        <tr>
            <td width="10">&nbsp;</td>      
            <td>
                <table cellspacing="1" class="request-service"  width="98%">
                    <tr >
                        <th>Account Name</th>
                        <th>Account Number</th>
                        <th>Contact Name</th>
                        <th>Contact Number</th>     
                    </tr>
                    <tr >
                        <td style="text-align: center;">
                            <% String strDisplayName = "";
                                if(objSessionAcctInfo.getAcctName() != null && !objSessionAcctInfo.getAcctName().equalsIgnoreCase("null")) 
                                    strDisplayName = objSessionAcctInfo.getAcctName();
                            %><%=strDisplayName%>
                        </td>
                        <td style="text-align: center;">
                            <% String strDisplayNo = "";
                                if(objSessionAcctInfo.getAcctNum() != null && !objSessionAcctInfo.getAcctNum().equalsIgnoreCase("null")) 
                                    strDisplayNo = objSessionAcctInfo.getAcctNum();
                            %><%=strDisplayNo%>
                        </td>
                        <td style="text-align: center;">
                            <% String strDisplayContName = "";
                                if(objSessionAcctInfo.getContactName() != null && !objSessionAcctInfo.getContactName().equalsIgnoreCase("null")) 
                                    strDisplayContName = objSessionAcctInfo.getContactName();
                            %><%=strDisplayContName%>
                        </td>
                        <td style="text-align: center;">
                            <% String strDisplayContNo = "";
                                if(objSessionAcctInfo.getContactNum() != null && !objSessionAcctInfo.getContactNum().equalsIgnoreCase("null")) 
                                    strDisplayContNo = objSessionAcctInfo.getContactNum();
                            %><%=strDisplayContNo%>
                        </td>
                    </tr>
                </table>   
            </td>
        </tr>
        <% } %>
       <!--  <tr><td height="10" colspan="2"></td></tr> -->
        <tr>
            <td  colspan="2"><font class="promptReadOnly">Selected Invoice: <a href="#" onClick="javascript:InvoiceDetailsPDF('<%=strInvNo%>');"><%=strInvNo%></a><%=strInvoiceNoN1%></font></td>
        </tr>
        <!-- <tr><td height="10" colspan="2"></td></tr> -->
        <tr>
            <td width="10">&nbsp;</td>
            <td>
                <table cellspacing="1" class="request-service"  width="98%">
                    <tr>
						<th>Contract Type</th>
                        <th>Contract Number</th>
                        <th>Contract Status</th>
                        <th>Expiration Date</th>
                        <!--MW Project Changes - Starts-->
                        <!--<td>Base Period</td>
                        <td>Overage Period</td>-->
                        <!--MW Project Changes - Ends-->
                        <th>Service Branch</th>
                    </tr>
                    <%
                        //MW Project Changes - Starts
						
                        if((alReadList != null && alReadList.size() > 0) || (alPriceList != null && alPriceList.size() > 0) || (alBReadList != null && alBReadList.size() > 0)) {
                      
						   if(alReadList != null && alReadList.size() > 0) 
                                objOks = (Canon_E193_OKSBillingDtlsObj)alReadList.get(0);
                            else if(alPriceList != null && alPriceList.size() > 0)
                                objOks = (Canon_E193_OKSBillingDtlsObj)alPriceList.get(0);
                            else 
                                objOks = (Canon_E193_OKSBillingDtlsObj)alBReadList.get(0);
                            //<!--MW Project Changes - Ends
                            //System.out.println("!!!!!!!! objOks = "+objOks);
   			                System.out.println("!!!!!Line Update !!!!!!!! alPriceList.size = "+alPriceList.size());
  
                            String strContractNum = objOks.getContractNumber()==null?"":objOks.getContractNumber();
							String strAggregateContractNum = objOks.getAggregateContractNumber()==null?"":objOks.getAggregateContractNumber();
                    %>
                            <input type="hidden" name="contractId" value="<%=objOks.getContractId()%>">
                            <input type="hidden" name="contractNo" value="<%=strContractNum%>">
							<input type="hidden" name="aggregateContractNum" value="<%=strAggregateContractNum%>">
                            <tr >
								<td style="text-align: center;"><%=strAggregateContractNum%></td>
                                <td style="text-align: center;"><%=strContractNum%></td>
                                <td style="text-align: center;"><%=strContractStatus%></td>
                                <td style="text-align: center;"><%=strExpirationDate%></td>
                                <!--MW Project Changes - Starts-->
                                <!--<td><%=strBasePeriod%></td>
                                <td style="text-align: center;"><%=strOveragePeriod%></td>-->
                                <!--MW Project Changes - Ends-->
                                <td style="text-align: center;"><%=strServiceBranch%></td>
                            </tr>
                <%
                        }else{
                %>
                            <tr >
                                <td colspan="6">
                                    <font class="promptReadOnly"><b>Invoice lines not found.</b></font>
                                </td>
                            </tr>
                <%      } %>
                </table>
            </td>
        </tr>
        <!-- <tr><td height="10" colspan="2"></td></tr> -->
        <%
			//MW Project Changes    
           /*  if(strInvoiceType.toUpperCase().contains("CONSOLIDATED")|| strInvoiceType.toUpperCase().contains("USAGE")) {  */  // if the invoice type is CONSOLIDATED/USAGE only
            	if(strInvoiceType.toUpperCase().contains("CONSOLIDATED")|| strUsageInvType.toUpperCase().contains("USAGE")) {  
            	if(alReadList != null && alReadList.size() > 0) {
        %>
        <tr>
            <td class="tableQuestionCell" colspan="2">
                <font class="promptReadOnly" color="#FF0000">
                    <b><%=strContLineUpdateN1%></b>
                </font>
            </td>
        </tr> 
        <tr><td height="10" colspan="2"></td></tr>
        <tr> 
            <td width="10">&nbsp;</td>
            <td>
            <% if(alReadList.size() > 5) { %>
            
            	<div id="divReadRoot" align="left">
    			<div style="overflow: hidden;" id="divReadHeaderRow">
    			</div>

    			<div style="overflow-y:scroll;" onscroll="OnScrollDiv(this, 'divReadHeaderRow')" id="divReadMainContent">
            
            <% } %>
                <table cellspacing="1" width="95%" class="request-service" id="readChargeTable">
                	<thead id="readChargeTableHdr">
                    <tr>
						<th rowspan="2">Invoice Number</th>
                        <th rowspan="2">Serial Number</th>
                        <th rowspan="2" nowrap>Start Meter<br>Read Date</th>
                        <th rowspan="2" nowrap>End Meter<br>Read Date</th>
						<th rowspan="2" nowrap>Counter</th>
						<th colspan="3">OLD</th>
						<th colspan="3">NEW</th>
                    </tr>
					 <tr >
								<td style="text-align: center;">Start Read</td>
								<td style="text-align: center;">End Read</td>
								<td style="text-align: center;">Test Copies</td>
								<td style="text-align: center;">Start Read</td>
								<td style="text-align: center;">End Read</td>
								<td style="text-align: center;">Test Copies</td>
				<%
                        if(alIssueList != null && alIssueList.size() > 0) {
                            String strCatCode = "";
                            for(int i=0; i<alIssueList.size(); i++) {
                                objIL = (Canon_E193_IssueListObj)alIssueList.get(i);
                                //System.out.println("objIL"+objIL);
                                strCatCode = objIL.getCatCode()==null?"":objIL.getCatCode();
                                if(!strCatCode.startsWith("BASE") && !strCatCode.startsWith("TIER")) {
                                	//System.out.println("In ContLineUpdate_: ");
                    %>
                                    <input type="hidden" name="corrCatId" value="<%=objIL.getCatId()%>">
                                   
                    <%
                                }
                            }
                        }
                    %>								
                    </tr>
                    </thead>
                    <tbody>
					
					
                    <%
                    
                    	System.out.println("!!!!!Line Update Object_Okays alReadList::: "+alReadList.size());
                        if(alReadList != null && alReadList.size() > 0) {
							//MW Project Changes
							String strSerialNo = "", strStartMeterDate = "", strEndMeterDate = "", strObj = "", strFleetSerialNo = "",strInvNumer="";
                            HashSet set = new HashSet();
							String tempInvoiceNo = "";
							String tempSerialNo = "";
							int intIsvcInvLinePk= 0;
							String strRSerialNo_ = "";
							String physMtrLbCd ="";
							
							
							 ArrayList arrayList = null;
                            	
                             for(int i = 0 ; i < alReadList.size() ;  i++)
                            { 
                            	 if(alReadList.get(i) != null)
                            	 {
                                objOks = (Canon_E193_OKSBillingDtlsObj)alReadList.get(i); 
                                //System.out.println("Object_Okays Line Update::: "+objOks.toString());
                                strSerialNo = objOks.getSerialNumber()==null?"":objOks.getSerialNumber();
                                strStartMeterDate = objOks.getStartMeterReadDate()==null?"":objOks.getStartMeterReadDate();
                                strEndMeterDate = objOks.getEndMeterReadDate()==null?"":objOks.getEndMeterReadDate();
                               //Newly Added
                                intIsvcInvLinePk = objOks.getSvcinvlinePk()==0?0:objOks.getSvcinvlinePk();
                              
								strInvNumer   = objOks.getTrxNumber()==null?"":objOks.getTrxNumber();
                                //MW Project Changes - Starts
                                strFleetSerialNo = objOks.getFleetSeriaNumber()==null?"":objOks.getFleetSeriaNumber();
                                //System.out.println("StrSerialNo " + strSerialNo + " StrStartMeterDate "+strStartMeterDate );
                                //MW Project Changes - Ends
                                
                                 String[] listOfRSerialNumbers = objIBList.getRSerialNumbers();
                                 //System.out.println("listOfRSerialNumbers.toString()= "+Arrays.toString(listOfRSerialNumbers));
                                
                               //System.out.println("  value of contract line id =  "+objOks.getContractLineId() +"   i = "+i);
                                //System.out.println("strEndMeterDate::: "+ strSerialNo+strStartMeterDate+strEndMeterDate);
                                   
                                strObj = strSerialNo+objOks.getContractLineId();
				                 //System.out.println("  value of contract line id after if =  "+objOks.getContractLineId() +"   i = "+i);
										 ArrayList counterMeter = new ArrayList();
										 
										 //System.out.println("strInvNumer ::::- " + strInvNumer + " strSerialNo "+ strSerialNo + " intIsvcInvLinePk " +intIsvcInvLinePk);
										counterMeter = counterReadDao.getCounterReadList(strInvNumer,strSerialNo,intIsvcInvLinePk); // Passed 3rd Parameter For Invoice#6000061
										
										//System.out.println("counterMeter ::::- " + counterMeter  + " counterMeter.size() " + counterMeter.size());
										
										if(counterMeter!=null && counterMeter.size()>0) {
											
											for(int k =0; k<counterMeter.size(); k++){
											Canon_E193_OKSBillingDtlsObj meterRead = (Canon_E193_OKSBillingDtlsObj)counterMeter.get(k);
											String testCopyInd = meterRead.getTestCopyInd();
											String counterNameVal = meterRead.getBillingCounterName();
											
											physMtrLbCd = meterRead.getPhysMtrLbCd() == null ? "" : meterRead.getPhysMtrLbCd();
											
											 //System.out.println("physMtrLbCd ---> " + physMtrLbCd);
											
											if(counterNameVal == null)
												counterNameVal = "";
											strObj = strSerialNo+objOks.getContractLineId() + counterNameVal + physMtrLbCd;
											if(!strSerialNo.equalsIgnoreCase("Fleet"))
											{
											
								%>  
								<input type="hidden" name="corrSerialNo" value="<%=strSerialNo%>">
								<input type="hidden" name="readInstanceNum" id="readInstanceNum" value="<%=objOks.getProductNumber()%>">
                                <input type="hidden" name="oldStartTHRead" value="<%=meterRead.getStartReading()%>">
                                <input type="hidden" name="oldEndTHRead" value="<%=meterRead.getEndReading()%>">
                                <input type="hidden" name="oldTestCopies" value="<%=meterRead.getTestCopies()%>">
                                <input type="hidden" name="corrContractLineId" value="<%=objOks.getContractLineId()%>">
                                <input type="hidden" name="oldStartMeterDate" value="<%=strStartMeterDate%>">
                                <input type="hidden" name="oldEndMeterDate" value="<%=strEndMeterDate%>">
								<input type="hidden" name="billingCounterName" value="<%=meterRead.getBillingCounterName()%>">	
								<input type="hidden" name="readinvNumbers" value="<%=strInvNumer%>">	
								<input type="hidden" name="isvcInvLinePk" value="<%=intIsvcInvLinePk%>"> <!-- Newly Added -->
								<input type="hidden" name="physMtrLbCd" value="<%=physMtrLbCd%>">
							   <tr>
									<%
									if(tempInvoiceNo.equalsIgnoreCase(strInvNumer)){
									%>
									<td>&nbsp;</td>
									<%
										}else{
									%>
									<td><%=strInvNumer%></td>
									<%
									}
									%>
									<%
									if(tempSerialNo!=null && tempSerialNo.equalsIgnoreCase(strSerialNo))
									{
									%>
                                    <td></td>
									<%
									}else{
									%>
									<td><%=strSerialNo%></td>
									<%
									}
									%>
                                    <td nowrap><%=strStartMeterDate%></td>
                                    <td nowrap><%=strEndMeterDate%></td>
									
									<td nowrap><%=meterRead.getCounterDesc()%></td>
                                    <td><%=CanonCustAppUtil.getNumberWithFormat(meterRead.getStartReading())%></td>
                                    <td><%=CanonCustAppUtil.getNumberWithFormat(meterRead.getEndReading())%></td>
									<%
										if(testCopyInd.equalsIgnoreCase("Y"))
										{
									%>
                                    <td><%=CanonCustAppUtil.getNumberWithFormat(meterRead.getTestCopies())%></td>
									<%
										}else{
									%>
									<td> &nbsp; </td>
									<%
									}
									%>
									
									<td><input type="text" id="newStartTHRead<%=i%><%=k%>" name="newStartTHRead" class="inTxt autoInteger" 
										size="8" style="width:100px;" value="<%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 0)%>" 
										onblur="fnValidateEqualValue(this, '<%=meterRead.getStartReading()%>')"></td>
                                    <td><input type="text" id="newEndTHRead<%=i%><%=k%>" name="newEndTHRead" class="inTxt autoInteger" 
                                    	size="8" style="width:100px;" value="<%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 1)%>" 
                                    	onblur="fnValidateEqualValue(this, '<%=meterRead.getEndReading()%>')"></td>
									
									<%
										if(testCopyInd.equalsIgnoreCase("Y")){
									%>
                                    <td><input type="text" id="newTestCopies<%=i%><%=k%>" name="newTestCopies" class="inTxt autoInteger" 
                                    	size="5" value="<%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 2)%>" 
                                    	onblur="fnValidateEqualValue(this, '<%=meterRead.getTestCopies()%>')"></td>	
									<%
										}else{
									%>
									<td><input type="text" name="newTestCopies" size="5" readonly ></td>	
									<%
									}
									%>
									</tr>    
									<%
											tempInvoiceNo = strInvNumer;
											tempSerialNo  = strSerialNo;
									}
								}
                             }
                            	 } 
                            }
                        
                        }else{
                %>
                            <tr >
                                <td colspan="11">
                                    <font class="promptReadOnly"><b>Invoice lines not found.</b></font>
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
       <!--  <tr><td height="10" colspan="2"></td></tr> -->
    <%  } // end Meter read  
        if(alPriceList != null && alPriceList.size() > 0) { // Start of Price List
    %>
        <tr>
            <td class="tableQuestionCell" colspan="2">
                <font class="promptReadOnly" color="#FF0000">
                    <b><%=strContLineUpdateN2%></b>
                </font>
            </td>
        </tr>
        <!-- <tr><td height="10" colspan="2"></td></tr> -->
        <tr>
            <td class="tableQuestionCell" colspan="2">
                <font class="promptReadOnly" color="#FF0000">
                    <b><%=strContLineUpdateN21%></b>
                </font>
            </td>
        </tr>
       <!--  <tr><td height="10" colspan="2"></td></tr> -->
        <tr>
            <td width="10">&nbsp;</td>
            <td>
            <% if(alPriceList != null && alPriceList.size() > 5) { %>
                <div id="divPriceRoot" align="left">
    			<div style="overflow: hidden;" id="divPriceHeaderRow">
    			</div>

    			<div style="overflow-y:scroll;" onscroll="OnScrollDiv(this, 'divPriceHeaderRow')" id="divPriceMainContent">
                
                
            <% } %>
                <table cellspacing="1" cellpadding="2" width="98%" class="request-service" id="priceChargeTable">
                	<thead id="priceChargeTableHdr">
	                    <tr>
	                        <!--MW Project Changes - Starts-->
	                        <th rowspan="2" nowrap>Service Program <br> Name</th>
	                        <!--MW Project Changes - Ends-->
	                        <th rowspan="2">Invoice Number / Serial Number</th>
	                        <th colspan="2" rowspan="2">Usage</th>
	                        <th colspan="2">Tier 1</th>
	                        <th colspan="2">Tier 2</th>
	                        <th colspan="2">Tier 3</th>
	                        <th colspan="2">Tier 4</th>
	                    </tr>
	                    <tr>
	                    <%
	                        boolean firstTier = true;
							if(alIssueList != null && alIssueList.size() > 0) {
	                            String strCatCode = "";
	                            for(int i=0; i < alIssueList.size(); i++) {
	                                objIL = (Canon_E193_IssueListObj)alIssueList.get(i);
	                                strCatCode = objIL.getCatCode()==null?"":objIL.getCatCode();
									
	                                if(strCatCode.startsWith("TIER")) {
	                    %>
	                                    <input type="hidden" name="dtlCatId" value="<%=objIL.getCatId()%>">
										
	                                   <%
										if(firstTier)
										{
											firstTier = false;
										%>
										<th nowrap><%=objIL.getCatDesc()%></th>
										<%
										}
										else
										{
										%>
											<th ><%=objIL.getCatDesc()%></th>
										<%
										}
										%>
	                    <%
	                                }
	                            }
	                        }
	                    %>
	                    </tr>
                    </thead>
                    </tbody>
                    <%
                  		System.out.println("!!!!!Line Update alPriceList size = "+ alPriceList.size());
                    	String physMtrLbCdPri ="";
                    	int priSvcInvLinePk= 0;
						if(alPriceList != null && alPriceList.size() > 0) {
                            String strSerialNo = "", strObj = "", strFleetSerialNo = "", strServiceProgram = "",strInvNumer="";
                            for(int i = 0; i < alPriceList.size(); i++) {
                            	//System.out.println("!!!!!Line Update Loop "+ i + " Beginning" );
                            	objOks = (Canon_E193_OKSBillingDtlsObj)alPriceList.get(i);
                                //System.out.println("!!!!!Line Update alPriceList objOks= "+ objOks.toString());
                                strSerialNo = objOks.getSerialNumber()==null?"":objOks.getSerialNumber();
								String instanceNum = objOks.getProductNumber();
							//	System.out.println("Inside price instanceNum : " + instanceNum);
                                //MW Project Changes - Starts
                                strServiceProgram = objOks.getServiceProgram()==null?"":objOks.getServiceProgram();
                                strFleetSerialNo = objOks.getFleetSeriaNumber()==null?"":objOks.getFleetSeriaNumber();
								strInvNumer   = objOks.getTrxNumber()==null?"":objOks.getTrxNumber();
                                String tempSerialNo = null;
                                
                              	//Newly Added
                                priSvcInvLinePk = objOks.getSvcinvlinePk()==0?0:objOks.getSvcinvlinePk();
                                
                                if(strFleetSerialNo == null || strFleetSerialNo.equals("") || strFleetSerialNo.equalsIgnoreCase("null")){
                                    tempSerialNo = strSerialNo;
                                    //System.out.println(" tempSerialNo= " + tempSerialNo);
                                }
                                else {
                                    tempSerialNo = strFleetSerialNo;
                                    //System.out.println(" Fleet tempSerialNo= " + tempSerialNo);
                                }
                                physMtrLbCdPri = objOks.getPhysMtrLbCd() == null ? "" : objOks.getPhysMtrLbCd();
                                
                                if("Y".equalsIgnoreCase(objOks.getFleetContract())){
                                    strObj = tempSerialNo+objOks.getContractLineId()+objOks.getBillingCounterName() + physMtrLbCdPri ;
                                    //System.out.println(" Fleet Contract= " + " Yes ");
                                }
                                else {
                                    strObj = strSerialNo+objOks.getContractLineId()+objOks.getBillingCounterName() + physMtrLbCdPri;
                                    //System.out.println(" Fleet Contract= " + " N0 ");
                                }
								//System.out.println(" Value of pricing billing counter name "+objOks.getBillingCounterName());	
								
								isAggregateSerialDisable = "";
								isAggContractAndNotAggSerDisable = "";
								
							    if(objOks.getSerialNumber() != null && "Aggregate".equalsIgnoreCase(objOks.getSerialNumber())) {
							    	isAggregateSerialDisable = " inTxtRdly";
							    }
							    
							    if( (objOks.getAggregateContractNumber() != null && "Aggregate".equalsIgnoreCase(objOks.getAggregateContractNumber()) &&
                                    	objOks.getSerialNumber() != null && !"Aggregate".equalsIgnoreCase(objOks.getSerialNumber())) ) {
							    	isAggContractAndNotAggSerDisable = " inTxtRdly";
							    }
                                //MW Project Changes - Ends
                    %>
                                <input type="hidden" name="dtlSerialNo" value="<%="Y".equalsIgnoreCase(objOks.getFleetContract())?strFleetSerialNo:strSerialNo%>"> 
								<%--<input type="hidden" name="dtlSerialNo" value="<%=strSerialNo%>">--%>
								<input type="hidden" name="priceInstanceNum" id="priceInstanceNum" value="<%=objOks.getProductNumber()%>">
                                <input type="hidden" name=oldTier1CopyVol value="<%=objOks.getTier1CopyVolume()%>">
                                <input type="hidden" name="oldTier1Rate" value="<%=objOks.getTier1Rate()%>">
                                <input type="hidden" name=oldTier2CopyVol value="<%=objOks.getTier2CopyVolume()%>">
                                <input type="hidden" name="oldTier2Rate" value="<%=objOks.getTier2Rate()%>">
                                <input type="hidden" name=oldTier3CopyVol value="<%=objOks.getTier3CopyVolume()%>">
                                <input type="hidden" name="oldTier3Rate" value="<%=objOks.getTier3Rate()%>">
                                <input type="hidden" name=oldTier4CopyVol value="<%=objOks.getTier4CopyVolume()%>">
                                <input type="hidden" name="oldTier4Rate" value="<%=objOks.getTier4Rate()%>">
                                <input type="hidden" name="dtlContractLineId" value="<%=objOks.getContractLineId()%>">
								<input type="hidden" name="priceinvNumbers" value="<%=strInvNumer%>">
								<input type="hidden" name="pricebillingCounterName" value="<%=objOks.getBillingCounterName()%>">
								<input type="hidden" name="priSvcInvLinePk" value="<%=priSvcInvLinePk%>"> <!-- Newly Added -->
         
                                <tr>
                                    <!--MW Project Changes - Starts-->
                                    <td style="text-align: center;"><%=strServiceProgram%></td>
                                    <!--MW Project Changes - Ends-->
                                    <td style="text-align: center;"><%=strInvNumer%> / <%=strSerialNo%></td>
                                    <td style="text-align: center;"><%=objOks.getLineDescription()==null?"":objOks.getLineDescription()%></td>
                                    <td align="right">OLD</td>
                                    <td style="test-align:center;"><%=CanonCustAppUtil.getNumberWithFormat(objOks.getTier1CopyVolume())%></td>			
									<td style="text-align: center;"><%=objOks.getTier1Rate()==0.0?"":String.valueOf(objOks.getTier1Rate())%></td>
                                    <td style="text-align: center;"><%=CanonCustAppUtil.getNumberWithFormat(objOks.getTier2CopyVolume())%></td>
                                    <td style="text-align: center;"><%=objOks.getTier2Rate()==0.0?"":String.valueOf(objOks.getTier2Rate())%></td>
                                    <td style="text-align: center;"><%=CanonCustAppUtil.getNumberWithFormat(objOks.getTier3CopyVolume())%></td>
                                    <td style="text-align: center;"><%=objOks.getTier3Rate()==0.0?"":String.valueOf(objOks.getTier3Rate())%></td>
                                    <td style="text-align: center;"><%=CanonCustAppUtil.getNumberWithFormat(objOks.getTier4CopyVolume())%></td>
                                    <td style="text-align: center;"><%=objOks.getTier4Rate()==0.0?"":String.valueOf(objOks.getTier4Rate())%></td>
                                </tr>
                                <tr >
                                    <!--MW Project Changes - Starts-->
                                    <td align="right" colspan="4" style="text-align:right" align="right">NEW</td>
                                    <!--MW Project Changes - Ends-->
                                    <td><input type="text" id="newTier1CopyVol<%=i%>" name="newTier1CopyVol"
                                    	value="<%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 3)%>" 
                                    	class="inTxt<%= isAggregateSerialDisable%> autoInteger" 
                                    	onblur="fnValidateEqualValue(this, '<%=objOks.getTier1CopyVolume()%>')"
                                    	style="width:60px" size="6" ></td>
                                    <td><input type="text" id="newTier1Rate<%=i%>" name="newTier1Rate" 
                                    	value="<%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 4)%>" 
                                    	class="inTxt<%=isAggContractAndNotAggSerDisable%>" onblur="fnValidateField(this, '<%=objOks.getTier1Rate()%>')"
                                    	style="width:60px" size="6" ></td>
                                    <td><input type="text" id="newTier2CopyVol<%=i%>" name="newTier2CopyVol" 
                                    	value="<%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 5)%>" 
                                    	class="inTxt<%= isAggregateSerialDisable%> autoInteger"
                                    	onblur="fnValidateEqualValue(this, '<%=objOks.getTier2CopyVolume()%>')"
                                    	style="width:60px" size="6" ></td>
                                    <td><input type="text" id="newTier2Rate<%=i%>" name="newTier2Rate" 
                                    	value="<%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 6)%>" 
                                    	class="inTxt<%=isAggContractAndNotAggSerDisable%>"  onblur="fnValidateField(this, '<%=objOks.getTier2Rate()%>')"
                                    	style="width:60px" size="6" ></td>
                                	<td><input type="text" id="newTier3CopyVol<%=i%>" name="newTier3CopyVol" 
                                		value="<%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 7)%>" 
                                		class="inTxt<%= isAggregateSerialDisable%> autoInteger" 
                                		onblur="fnValidateEqualValue(this, '<%=objOks.getTier3CopyVolume()%>')" 
                                		style="width:60px" size="6" ></td>
                                	<td><input type="text" id="newTier3Rate<%=i%>" name="newTier3Rate" 
	                                	value="<%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 8)%>" 
	                                	class="inTxt<%=isAggContractAndNotAggSerDisable%>" onblur="fnValidateField(this, '<%=objOks.getTier3Rate()%>')"
	                                	style="width:60px" size="6" ></td>
                                	<td><input type="text" id="newTier4CopyVol<%=i%>" name="newTier4CopyVol" 
	                                	value="<%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 9)%>" 
	                                	class="inTxt<%= isAggregateSerialDisable%> autoInteger" 
	                                	onblur="fnValidateEqualValue(this, '<%=objOks.getTier4CopyVolume()%>')"
	                                	style="width:60px" size="6" ></td>
                                	<td><input type="text" id="newTier4Rate<%=i%>" name="newTier4Rate" 
	                                	value="<%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, 10)%>" 
	                                	class="inTxt<%=isAggContractAndNotAggSerDisable%>" onblur="fnValidateField(this, '<%=objOks.getTier4Rate()%>')"
	                                	style="width:60px" size="6" ></td>
                                </tr>
                <%
                            }
                        } else {
                %>
                            <tr >
                                <td colspan="11">
                                    <font class="promptReadOnly"><b>Invoice lines not found.</b></font>
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
             /* if((strInvoiceType.toUpperCase().contains("CONSOLIDATED")|| strInvoiceType.toUpperCase().contains("BASE")) /* && alBReadList != null && alBReadList.size() > 0 ) {  */ 
            	 if((/* strInvoiceType.toUpperCase().contains("CONSOLIDATED")||  */strBaseInvType.toUpperCase().contains("BASE")) /* && alBReadList != null && alBReadList.size() > 0 */) {
            
        %>      
        <tr>
            <td class="tableQuestionCell" colspan="2">
                <font class="promptReadOnly" color="#FF0000">
                    <b><%=strContLineUpdateN3%></b>
                </font>
            </td>
        </tr>
        <!-- <tr><td height="10" colspan="2"></td></tr> -->
        <tr>
            <td width="10">&nbsp;</td>
            <td>
            <% 
            //MW Project Changes
            
            if(alBReadList != null && alBReadList.size() > 5) { %>
            
            	<div id="divBaseRoot" align="left">
    			<div style="overflow: hidden;" id="divBaseHeaderRow">
    			</div>

    			<div style="overflow-y:scroll;" onscroll="OnScrollDiv(this, 'divBaseHeaderRow')" id="divBaseMainContent">
            <% } %>
                <table cellspacing="1" width="98%" class="request-service" id="baseChargeTable">
                	<thead id="baseChargeTableHdr">
                    <tr>
						<th>Invoice Number</th>
                        <th>Serial Number</th>
						<th>Product Number</th>
                        <th>Base Period</th>
                        <th>Item Code</th>
                        <th>Model Description</th>
                        <th>Invoice Rate</th>
						<th>Contract Rate</th>
                    <%
                        int iIndex = 0;
                        System.out.println("!!!!!Line Update !!!size()!!!!"+alIssueList.size());
                        if(alIssueList != null && alIssueList.size() > 0) {
                            String strCatCode = "";
                            
                            for(int i=0; i<alIssueList.size(); i++) {
                                objIL = (Canon_E193_IssueListObj)alIssueList.get(i);
                                strCatCode = objIL.getCatCode()==null?"":objIL.getCatCode();
                                //System.out.println(" ::::StrCatCode: "+ strCatCode);
                                if(strCatCode.startsWith("BASE")) {
                                	iIndex = i;
                    %>
                                    <input type="hidden" name="baseCatId" value="<%=objIL.getCatId()%>">
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
                        //Newly Added
                        HashSet mHashSet = new HashSet();
                        String strSerialNo_ = "";
                        int baseSvcInvLinePk= 0;
                        if(alBReadList != null && alBReadList.size() > 0) {
                            String strObj = "", strSerialNo = "", strFleetSerialNo = "", strInvNumer="", strInstanceId="";
                            boolean strTextReadOnly = false;
                           
                              String[] listSerialNumber = objIBList.getBSerialNumbers();
                           int count =0;
                              //System.out.println(" finalBaseArrayList.size() = "+ finalBaseArrayList.size());
                            for(int i = 0; i < finalBaseArrayList.size(); i++) {
                                objOks = (Canon_E193_OKSBillingDtlsObj)finalBaseArrayList.get(i);
                                //System.out.println("Number of times loop rotated:~ "+ count++ + " ~~ "+objOks.toString());
                                strTextReadOnly = false;
                                strSerialNo = objOks.getSerialNumber()==null?"":objOks.getSerialNumber();
                                // Added in HashSet
                                //System.out.println(" StrSerialNo!!!_Value!! "+ strSerialNo);
                                strFleetSerialNo = objOks.getFleetSeriaNumber()==null?"":objOks.getFleetSeriaNumber();
                                strInvNumer   = objOks.getTrxNumber()==null?"":objOks.getTrxNumber();
                                
                              //Newly Added
                                baseSvcInvLinePk = objOks.getSvcinvlinePk()==0?0:objOks.getSvcinvlinePk();
                                
                                //System.out.println("::::::StrInvNumer: "+strInvNumer);
		                        // MW Project Changes
                                //if("Y".equalsIgnoreCase(objOks.getFleetContract()) && objOks.getBaseAmount() == 0.0)
                                //  strTextReadOnly = true;

                                String tempSerialNo = null;

                                if(strFleetSerialNo == null || strFleetSerialNo.equals("") || strFleetSerialNo.equalsIgnoreCase("null"))
                                    tempSerialNo = strSerialNo;
                                else
                                    tempSerialNo = strFleetSerialNo;       
                                //System.out.println("TempSerialNo!!!_Value!! "+ tempSerialNo);   
                                if("Y".equalsIgnoreCase(objOks.getFleetContract()))
                                    strObj = tempSerialNo+objOks.getContractLineId();
                                else
                                    strObj = strSerialNo+objOks.getContractLineId();
								
                                //System.out.println(" =strObj= " + strObj);
								//System.out.println("Value of total count "+alBReadList.size() + "  current index =  "+i);		
								List basePriceList = counterReadDao.getContractBasePrices(objOks.getContractId(),tempSerialNo, objOks.getContractLineId(), objOks.getProductNumber()); 
								
								String[] reading = (String[])basePriceList.get(0);
								String[] uom_period = (String[])basePriceList.get(1);
								String[] base_amount = (String[])basePriceList.get(2);
								//System.out.println("Value of objOks.getContractId() "+objOks.getContractId());
								//System.out.println("Value of tempSerialNo "+tempSerialNo);
								
								String displayBaseRateCol = "N"; 
								displayBaseRateCol = counterReadDao.displayBaseRateColumn(objOks.getContractLineId(),strInvNumer,objOks.getDateBilledFrom());
								String disableChoiceList = "";
								String disabledColorRow = "";
								String lineDescription = objOks.getLineDescription();
								String strModel = objOks.getModel()==null?"":objOks.getModel();
								if(objOks.getLineDescription() == null || "null".equals(lineDescription))
								{
									lineDescription = "";
								}
								//System.out.println("!!!!!!! reading.length= "+reading.length);
								if(reading.length < 1 ) // make the new base rate colum readonly if contract base rate is not obtained. 
								{
									displayBaseRateCol = "N";
								}
								if ("N".equals(displayBaseRateCol))
								{
									disableChoiceList  = "disabled";
									disabledColorRow = "background:#dddddd";
								}
								//Newly Added For Invoice#600023
								/*int lenListSerialNumber = listSerialNumber.length;
								System.out.println(" lenListSerialNumber= "+ lenListSerialNumber);
								 for(int z=0;z<lenListSerialNumber;z++){
									String[] temp = new String[3];
	                            	temp = listSerialNumber[z].split("~");
	                            	if(temp.length>0)
	                            		strSerialNo_ = temp[0];
	                            	
	        						if(strSerialNo_==null || strSerialNo_.equalsIgnoreCase(strSerialNo)) { */
                                
                    %>
                                <%-- <input type="hidden" name="baseSerialNo" value="<%="Y".equalsIgnoreCase(objOks.getFleetContract())?strFleetSerialNo:strSerialNo%>"> --%>
                                <input type="hidden" name="baseSerialNo" value="<%=strSerialNo%>">
								<input type="hidden" name="baseInstanceNum" id="baseInstanceNum" value="<%=objOks.getProductNumber()%>">
                                <!--MW Project Changes-->
                                <input type="hidden" name="baseFleetSerialNo" value="<%=strFleetSerialNo%>">
                                <input type="hidden" name="baseContractLineId" value="<%=objOks.getContractLineId()%>">
                                <input type="hidden" name="baseOldRate" value="<%=objOks.getBaseAmount()%>">
                                <!--Start:MW Project Changes-->
                                <input type="hidden" name="baseBilledFrom" value="<%=objOks.getDateBilledFrom()%>">
                                <input type="hidden" name="baseBilledTo" value="<%=objOks.getDateBilledTo()%>">
								<input type="hidden" name="baseinvNumbers" value="<%=strInvNumer%>">
								
								<input type="hidden" name="baseSvcInvLinePk" value="<%=baseSvcInvLinePk%>"> <!-- Newly Added -->

                                <!--End:MW Project Changes-->
                                <tr>
									<td><%=strInvNumer%></td>
                                    <td><%=strSerialNo%></td>
									<td><%=objOks.getProductNumber()==null?"&nbsp;":objOks.getProductNumber()%></td>
                                    <td width="11%"><%=objOks.getDateBilledFrom()%>  - <%=objOks.getDateBilledTo()%></td>
                                    <td><%=objOks.getItemCode()==null?"":objOks.getItemCode()%></td>
                                    <td><%=strModel +"<br>" + lineDescription %></td>  <!-- +objOks.getLineDescription() -->
                                    <td><%=CanonCustAppUtil.getDoubleWithFormat(objOks.getBaseAmount())%></td>
									<td>   <select <%=disableChoiceList%>  > 
									 <%
										for(int pq = 0; pq < reading.length; pq ++) 
										{
											%>
												<option> <%= uom_period[pq]+"  " +reading[pq] +"  $"+ base_amount[pq]%>  </option>
											<%
										}
									 %>
									</select>
									</td>
									<%
										if ("Y".equals(displayBaseRateCol))
										{
									%>
											<td>
												<input type="text"  name="baseNewRate" id="baseNewRate<%=i%>" class="inTxt required" 
													size="10" value="<%=getDisValue(strObj, objOks.getHtAttribute(), alIssueList, iIndex)%>" 
													<%=strTextReadOnly?"readOnly":""%> onblur="fnValidateField(this, '<%=objOks.getBaseAmount()%>')">
											</td>
									<%
										}
										else
										{
									%>									
											<td>
												<input type="text"  name="baseNewRate" id="baseNewRate" class="inTxt required" size="10" value="" >
											</td>		
									<%
										}
									%>	
                                </tr>                               
                <%
                         //   }
                     
								// }
                            }
                        }else{
                %>
                            <tr >
                                <td colspan="8">
                                    <font class="promptReadOnly"><b>Invoice lines not found.</b></font>
                                </td>
                            </tr>
                <%      } %>
                	</tbody>
                </table>
                
            <% if(alBReadList != null && alBReadList.size() > 5) { %>
                </div>
                
				</div>
            <% } %>
            </td>
        </tr>       
        <%
             } // close BASE
        %>
        <!-- <tr><td height="10" colspan="2"></td></tr> -->
        <tr>
            <td class="tableQuestionCell" colspan="3">
                <font class="promptReadOnly" color="#FF0000">
                    <b><%=strContLineUpdateN4%></b>
                </font>
            </td>
        </tr>
        <!-- <tr><td height="10" colspan="2"></td></tr> -->
        <tr>
            <td width="10">&nbsp;</td>
            <td>
                <table cellspacing="1" style="width:60%" class="supplies-tablehome">
                    <tr>
                        <th><a href="javascript:void(0)" onClick="getLovWithValue('canon_E193_csTicketReasonCodeP.jsp','reasonCode','CONTRACTS','Reason Code');" >Reason Code</a><%=strReasonCodeN1%></th>
                        <th>Urgency</th>
                        <th>Prior Ticket Number</th>
                    </tr>
                    <tr>
                        <td><input type="text" name="reasonCdDesc" id="reasonCdDesc" onClick="getLovWithValue('canon_E193_csTicketReasonCodeP.jsp','reasonCode','CONTRACTS','Reason Code');" value="<%=request.getParameter("reasonCdDesc")==null?"":request.getParameter("reasonCdDesc")%>" class="inTxt required" style="background-color:#ffff00" size="80" readonly=true></td>
                        <td>
                            <select name="severity" size="1" class="searchBarLink">
                            <%
                                if(alSeverityList != null && alSeverityList.size() > 0) {
                                    String reqSeverity = request.getParameter("severity")==null?"":request.getParameter("severity");
                                    String code = "";
                                    for(int i=0; i<alSeverityList.size(); i++) {
                                        code = alSeverityList.get(i).toString();
                            %>
                                        <option value="<%=code%>" <%=code.equals(reqSeverity)?"selected":""%>><%=code%></option>
                            <%      }
                                }
                            %>
                            </select>
                        </td>
                        <td>
							<input type="text" name="priorTktNumber" class="txtbox" size="30" value="">
						</td>
                    </tr>
                </table>
            </td>
        </tr>
        <!-- <tr><td height="10" colspan="2"></td></tr> -->
        <tr>
            <td class="tableQuestionCell" colspan="2">
                <font class="promptReadOnly" color="#FF0000">
                    <b><%=strContLineUpdateN5%></b>
                </font>
            </td>
        </tr>
        <!-- <tr><td height="10" colspan="2"></td></tr> -->
        <%
            String strNotesDisplay = request.getParameter("notes")==null?"":request.getParameter("notes");
            if(!"".equalsIgnoreCase(strNotesDisplay)) {
        %>
            <tr>
                <td width="10">&nbsp;</td>
                <td ><textarea name="notesDisplay" id="notesDisplay" rows="10" wrap="OFF" class="inTxt required" style="background-color:#ffff00;height:auto; width:800px;"  readonly="readonly"><%=strNotesDisplay%></textarea></td>        
            </tr>
        <%  }  %>
        <tr>
            <td width="10">&nbsp;</td>
            <td><textarea name="notes" id="notes" rows="10" wrap="OFF" class="inTxt required" style="background-color:#ffff00;height:auto; width:800px;"></textarea></td>       
        </tr>           
    </table> 
    </form>
    <div style="text-align: center; margin-bottom: 5px;">
	 <a class="btn_red" href="javascript:history.back();">Prev</a>
	 <% 
	 if((alReadList != null && alReadList.size() > 0) || (alPriceList != null && alPriceList.size() > 0) || (alBReadList != null && alBReadList.size() > 0)) {
		 System.out.println("!!!!!Line Update At the End"); 
		 %>
             <a class="btn_red" style="margin-left: 10px" href="javascript:action_func1();">Next</a>
          <% }else{ %>
          <a class="btn_red" href="#">Next</a>                                    
          <% } %>
	</div>  
	<div id="dlg"></div>     
<%@ include file="canon_E193_csBottomInc.jsp" %>
</div>
