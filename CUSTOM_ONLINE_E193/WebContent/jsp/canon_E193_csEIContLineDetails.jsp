<!-- $Header: canon_E193_csEIContLineDetails.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |  canon_E193_csEIContLineDetails.jsp - contact line details.
 |   
 | DESCRIPTION
 |   Details of the incorrect billing issue details.
 |
 | AUTHOR
 |  Subba 
 |
 | CREATION DATE
 |  10/04/2005
 |
 | HISTORY
 | DATE         WHO                 WHY
 | 06-Oct-09    Naveen Khandelwal   MW Project Changes
 | 15-Oct-09    Naveen Khandelwal   MW Project Changes
 | 05-Feb-13	Hema 				 ITG#434465 : To handle Non-Serialized accessories.
 |
 +=======================================================================--%>
<%@ page language="java" %> 
<%@ page import ="java.util.*" %>
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AcctInfoObj" %>
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_OKSBillingDtlsObj" %>
<%@ page import="com.canon.oracle.custapp.util.CanonCustAppUtil" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objIBList" scope="request" />
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Severity" id="objSeverityDao" scope="page"/>
<%@ include file="canon_E193_csTopInc.jsp" %>

<%
    // Menu Prompt 
    strPageName = "Enter & Inquiry";
isActivePage = false;
    // Check page from to show the path
    strPageFrom = request.getParameter("hPageFrom");
    String hPath = request.getParameter("hPath");
    if (hPath == null)
        hPath = strContLineDetailsT1;
    else
        if(hPath.indexOf(strContLineDetailsT1) < 0)
            hPath = hPath + " -> " + strContLineDetailsT1;  
%>
<%@ include file="canon_E193_csMenuInc.jsp" %>

<script language="JavaScript">

    function action_func1() {
        var objForm = document.contLineDetailForm;
        var lenR = 0, lenP = 0, lenB=0;     //MW Project Changes
        if(objForm.rSerialNo != null) lenR = objForm.rSerialNo.length
        if(objForm.pSerialNo != null) lenP = objForm.pSerialNo.length
        if(objForm.bSerialNo != null) lenB = objForm.bSerialNo.length   //MW Project Changes
        var isChecked = false;
        if(lenR > 0) {
            for(i=0; i<lenR; i++) {
                if(objForm.rSerialNo[i].checked) {
                    isChecked = true;
                    break;
                }
            }
        }else if(objForm.rSerialNo != null && objForm.rSerialNo.checked) {
            isChecked = true;
        }
        if(!isChecked && lenP > 0) {
            for(i=0; i<lenP; i++) {
                if(objForm.pSerialNo[i].checked) {
                    isChecked = true;
                    break;
                }
            }
        }else if(!isChecked && objForm.pSerialNo != null && objForm.pSerialNo.checked) {
            isChecked = true;
        }
        //MW Project Changes - Start
        if(!isChecked && lenB > 0) {
            for(i=0; i<lenB; i++) {
                if(objForm.bSerialNo[i].checked) {
                    isChecked = true;
                    break;
                }
            }
        }else if(!isChecked && objForm.bSerialNo != null && objForm.bSerialNo.checked) {
            isChecked = true;
        }
        
        var boolFlag = <%=objIBList.getContactFlag()%>;
        //MW Project Changes - End
       /*  if(!isChecked && !boolFlag) { */
    	 if(!isChecked) {
            alert("Please select a serial number");
        }else{
            objForm.submit();
        }
    }
    
    function fnSelRAll(obj) {
        var objForm = document.contLineDetailForm;
        var len = 0;
        if(objForm.rSerialNo != null) len = objForm.rSerialNo.length;
       
        if(obj.checked) {        	
            retCheckedR(objForm, len, true);
        }else{        	
            retCheckedR(objForm, len, false);
        }
    }
    
    function fnSelPAll(obj) {
        var objForm = document.contLineDetailForm;
        var len = 0;
        if(objForm.pSerialNo != null) len = objForm.pSerialNo.length;
        if(obj.checked) {
            retCheckedP(objForm, len, true);
            
        }else{
            retCheckedP(objForm, len, false);
        }
    }
    
    //MW Project Changes - Start
    function fnSelBAll(obj) {
        var objForm = document.contLineDetailForm;
        var len = 0;
        if(objForm.bSerialNo != null) len = objForm.bSerialNo.length;
        if(obj.checked) {
            retCheckedB(objForm, len, true);
        }else{
            retCheckedB(objForm, len, false);
        }
    }
    
    //MW Project Changes - End
    var array =[];
    function fnSelR(obj) {   	
    	
	
        var objForm = document.contLineDetailForm;
        var len = objForm.rSerialNo.length;
             
       if(obj.checked){
    		array.push(obj.value);
    		
    		var stringOfArray = array.toString();
        	
        	localStorage.setItem("Serial_Num", stringOfArray);
        } 
        
        
        if(!obj.checked && objForm.selRAll.checked) {
        	//alert("current object is not checked and all are selected");
        	objForm.selRAll.checked = false;
        	//alert("All selRall values are checked as false");
        }
        else if(obj.checked && checkRemR(objForm, len)) {
        	//alert("Current object is checked and checkRemR function is called");
        	//alert("len parameter===="+len);
        	objForm.selRAll.checked = true;
        	//alert("All elements are checked =true");
        }
    }
    function fnSelP(obj) {
        var objForm = document.contLineDetailForm;
        var len = objForm.pSerialNo.length;
        if(!obj.checked && objForm.selPAll.checked) 
        	objForm.selPAll.checked = false;
        else if(obj.checked && checkRemP(objForm, len))
        	objForm.selPAll.checked = true;
    }
    
    
    var selectArray = [];
    function fnSelFrequency(val){
    	
    	//alert("coming to select function");
    	//alert("Selected object===="+obj);
    	if(val != ""){
    		
    		selectArray.push(val);
    		
    		var selectArrayString = selectArray.toString();
    		
    		localStorage.setItem("selectedValue", selectArrayString);
    			/*	var input = document.createElement("input");

    	    	input.setAttribute("type", "hidden");
				
    	    	//var x= 1
    	    	//alert("x==="+x);
    	    	input.setAttribute("name", "frequency_value_");
    	    	
    	    	input.setAttribute("id", "frequency_value_");
    	    	//alert("x++===="+x++);

    	    	input.setAttribute("value",val);

    	    	//append to form element that you want .
    	    	document.getElementById("contLineDetailForm").appendChild(input); 
    	    	
    	    	
    	    	var selected_value = document.getElementById("frequency_value_").value;
    	    	alert("selected_value===="+selected_value); */
    	    	
    	    	
    	    	
    	    	
    	    	
    	}
    	
    	
    	
    }
    
    
    
    //MW Project Changes - Start
    function fnSelB(obj) {
        var objForm = document.contLineDetailForm;
        var len = objForm.bSerialNo.length;
        if(!obj.checked && objForm.selBAll.checked) objForm.selBAll.checked = false;
        else if(obj.checked && checkRemB(objForm, len)) objForm.selBAll.checked = true;
    }
    //MW Project Changes - End
    function checkRemR(objForm, len) {
        var isChecked = true;
        if(len > 0) {
            for(i=0; i<len; i++) {
                if(!objForm.rSerialNo[i].checked) {
                    isChecked = false;
                    break;
                }
            }
        }else if(!objForm.rSerialNo.checked) {
            isChecked = false;
        }
        return isChecked;
    }
    function checkRemP(objForm, len) {
        var isChecked = true;
        if(len > 0) {
            for(i=0; i<len; i++) {
                if(!objForm.pSerialNo[i].checked) {
                    isChecked = false;
                    break;
                }
            }
        }else if(!objForm.pSerialNo.checked) {
            isChecked = false;
        }
        return isChecked;
    }
    //MW Project Changes - Start
    function checkRemB(objForm, len) {
        var isChecked = true;
        if(len > 0) {
            for(i=0; i<len; i++) {
                if(!objForm.bSerialNo[i].checked) {
                    isChecked = false;
                    break;
                }
            }
        }else if(!objForm.bSerialNo.checked) {
            isChecked = false;
        }
        return isChecked;
    }
    //MW Project Changes - End
    function retCheckedR(objForm, len, bool) {
        if(len > 0) {
            for(i=0; i<len; i++) {
                objForm.rSerialNo[i].checked = bool;
            }
        }else if(objForm.rSerialNo != null) {
            objForm.rSerialNo.checked = bool;
        }
    }
    function retCheckedP(objForm, len, bool) {
        if(len > 0) {
            for(i=0; i<len; i++) {
                objForm.pSerialNo[i].checked = bool;
            }
        }else if(objForm.pSerialNo != null) {
            objForm.pSerialNo.checked = bool;
        }
    }
    //MW Project Changes - Start
    function retCheckedB(objForm, len, bool) {
        if(len > 0) {
            for(i=0; i<len; i++) {
                objForm.bSerialNo[i].checked = bool;
            }
        }else if(objForm.bSerialNo != null) {
            objForm.bSerialNo.checked = bool;
        }
    }
    //MW Project Changes - End
    /*function InvoiceDetails(val1) {
        var vLink = "canon_E193_csActualInvoiceController.jsp?InvNo=" + val1;
        var vWin = window.open(vLink, "newwin","height=300,width=900,toolbar=no,menubar=no,scrollbars=yes,resizable=yes" );
        vWin.focus();
    }*/
    
    //Added the functinality for edit the ticket
    $(function (){
        var objForm = document.contLineDetailForm;
        var len = 0;
        if(objForm.pSerialNo != null)
        {
		    len = objForm.pSerialNo.length;
		    if(checkRemP(objForm, len))
		    {
		    	objForm.selPAll.checked = true;
		    }
        }
        if(objForm.rSerialNo != null)
        {
		    len = objForm.rSerialNo.length;
		    if(checkRemR(objForm, len))
		    {
		    	objForm.selRAll.checked = true;
		    }
        }
        if(objForm.bSerialNo != null)
        {
		    len = objForm.bSerialNo.length;
		    if(checkRemB(objForm, len))
		    {
		    	objForm.selBAll.checked = true;
		    }
        }
    });
    
//-->
</script>


<%
    //Get Org ID
    int iOrgId = objCiSession.getOrgId();
	int isvcInvLinePkUsage=0;
	int isvcInvLinePkBase=0;
    String invoiceType = "";
    String invoiceTypeBase = "";
    String invoiceTypeUsage = "";
    Object objAcct = session.getAttribute("objSessionAcctInfo");
    
    ArrayList listOfInvLinePk = new ArrayList();  
    Canon_E193_AcctInfoObj objSessionAcctInfo = null;
    if(objAcct != null) objSessionAcctInfo = (Canon_E193_AcctInfoObj)objAcct;
    
    ArrayList alInCorrList = (ArrayList)objIBList.getArrayList();
     ArrayList alFrequencyCd = objSeverityDao.getFrequencyCodes();
    Canon_E193_OKSBillingDtlsObj objOks = null;
    System.out.println("Line Details!!!!!! AlInCorrList.size() "+alInCorrList.size());
    if(alInCorrList!=null && alInCorrList.size()>0)
	{
     	for(int i=0;i<alInCorrList.size();i++)
    	{
		Canon_E193_OKSBillingDtlsObj objBill1 = (Canon_E193_OKSBillingDtlsObj)alInCorrList.get(i);
		//System.out.println("isvcInvLinePk_Details::: "+objBill1.getSvcinvlinePk());
		listOfInvLinePk.add(objBill1.getSvcinvlinePk());
		if("USAGE".equalsIgnoreCase(objBill1.getInvoiceType())){
				isvcInvLinePkUsage=objBill1.getSvcinvlinePk();
				//invoiceType = objBill1.getInvoiceType();
				invoiceTypeUsage = objBill1.getInvoiceType();
				//System.out.println("Line Details!!!!!! isvcInvLinePkUsage= " + isvcInvLinePkUsage + " invoiceType = "+ invoiceType + "  SerialNumber For Usage= " + objBill1.getSerialNumber());
          }
		else if("BASE".equalsIgnoreCase(objBill1.getInvoiceType())){
			//System.out.println("isvcInvLinePkBase_Details::: "+objBill1.getSvcinvlinePk());
			isvcInvLinePkBase=objBill1.getSvcinvlinePk();
			//invoiceType = objBill1.getInvoiceType();
			//System.out.println(" isvcInvLinePkBase= " + isvcInvLinePkBase + " invoiceType = "+ invoiceType + "  SerialNumber For Base= " + objBill1.getSerialNumber());
			invoiceTypeBase = objBill1.getInvoiceType();
		}
		else{
			invoiceType = objBill1.getInvoiceType();
			//System.out.println("Line Details!!!!!! invoiceType = "+ invoiceType);
		}
		
   	}
    	/* objIBList.setSvcinvlinePkMap(hm);
    	System.out.println("Map:: " + hm); */
	}
    String strInvNo = request.getParameter("invoiceNumber")==null?"":request.getParameter("invoiceNumber");
    ///System.out.println("iOrgId in contline details is" + iOrgId);
%> 
   <div id="main_content">
	 <div id="page_top">
	 	<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strContLineDetailsT1 %></h1>
		<div class="breadcrumb"><%=hPath%></div>		
	</div>	
    
    <form name="contLineDetailForm" id="contLineDetailForm" action="canon_E193_csEIIBController.jsp" method="post">

    <input type="hidden" name="hPageFrom" value="EIContLineDetails">
    <input type="hidden" name="nextPage" value="">
    <input type="hidden" name="hReasonCd" value="<%=request.getParameter("hReasonCd")==null?"":request.getParameter("hReasonCd")%>">
    <input type="hidden" name="hCatId" value="<%=request.getParameter("hCatId")%>">
    <input type="hidden" name="hParentCatId" value="<%=request.getParameter("hParentCatId")%>">
    <input type="hidden" name="hParentCatCode" value="<%=request.getParameter("hParentCatCode")%>">
    <input type="hidden" name="hCatCode" value="<%=request.getParameter("hCatCode")%>">
    <input type="hidden" name="hCatDesc" value="<%=request.getParameter("hCatDesc")%>">
    <input type="hidden" name="InvSource" value="<%=request.getParameter("InvSource")%>">
    
    <input type="hidden" name="listOfInvLinePk" value="<%=listOfInvLinePk%>">
    
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
    
    <input type="hidden" name="svcInvLinePkUsage" id="svcInvLinePkUsage" value="<%=isvcInvLinePkUsage%>">
    <input type="hidden" name="svcInvLinePkBase" id="svcInvLinePkBase" value="<%=isvcInvLinePkBase%>">
    
<%
//Nely Added for Contract/Invoice Billing Ticket
    String strInvoiceType ="";
   if(!objIBList.getContactFlag()){
	   strInvoiceType = request.getParameter("strInvoiceType")==null?"":request.getParameter("strInvoiceType");
   }
   else{
	   strInvoiceType = invoiceType;
   }
    String strExpirationDate = request.getParameter("strExpirationDate")==null?"":request.getParameter("strExpirationDate");
    String strContractStatus = request.getParameter("strContractStatus")==null?"":request.getParameter("strContractStatus");
    String strServiceBranch = request.getParameter("strServiceBranch")==null?"":request.getParameter("strServiceBranch");
    String strBasePeriod = request.getParameter("strBasePeriod")==null?"":request.getParameter("strBasePeriod");
    String strOveragePeriod = request.getParameter("strOveragePeriod")==null?"":request.getParameter("strOveragePeriod");
    String strCount = request.getParameter("strCount")==null?"":request.getParameter("strCount");
    
    System.out.println("!!StrInvoiceType!! "+strInvoiceType+" !!!StrServiceBranch!! "+strServiceBranch);
%>
    <input type="hidden" name="strInvoiceType" value="<%=strInvoiceType%>">
    <input type="hidden" name="strExpirationDate" value="<%=strExpirationDate%>">
    <input type="hidden" name="strContractStatus" value="<%=strContractStatus%>">
    <input type="hidden" name="strServiceBranch" value="<%=strServiceBranch%>">
    <input type="hidden" name="strBasePeriod" value="<%=strBasePeriod%>">
    <input type="hidden" name="strOveragePeriod" value="<%=strOveragePeriod%>">
    <input type="hidden" name="strCount" value="<%=strCount%>">
    
    <input type="hidden" name="reasonCdDesc" value="<%=request.getParameter("reasonCdDesc")==null?"":request.getParameter("reasonCdDesc")%>">
    <input type="hidden" name="strNoteId" value="<%=request.getParameter("strNoteId")==null?"":request.getParameter("strNoteId")%>">
    <input type="hidden" name="notes" value="<%=request.getParameter("notes")==null?"":request.getParameter("notes")%>">
    <input type="hidden" name="severity" value="<%=request.getParameter("severity")==null?"":request.getParameter("severity")%>">
    <input type="hidden" name="hPath" value="<%=hPath%>">
	<input type="hidden" name="vBillType" value="<%=request.getParameter("vBillType")%>">
	<input type="hidden" name="consolidateBillNum" value="<%=request.getParameter("consolidateBillNum")==null?"":request.getParameter("consolidateBillNum")%>">
    <table class="request-service" style="align:center;" cellspacing="0" border="0">
    <% if(objSessionAcctInfo != null) { %>
        <tr><td height="10" colspan="3"></td></tr>
        <tr>
            <td class="tdTableCellStyle" colspan="3"><font class="promptReadOnly">Customer Details:</font></td>
            <!-- <td class="tdTableCellStyle" colspan="3"><a href="#" onclick="javascript:print();"><font class="promptReadOnly">print</font></a></td> -->
        </tr>
        <tr>
            <td width="10">&nbsp;</td>      
            <td colspan="2">
                <table cellspacing="1" class="request-service">
                    <tr>
                        <th>Account Name</th>
                        <th>Account Number</th>
                        <th>Contact Name</th>
                        <th>Contact Number</th>     
                    </tr>
                    <tr>
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
        <!-- <tr><td height="5" colspan="3"></td></tr> -->
        <tr>
            <td class="tdTableCellStyle" colspan="3"><font class="promptReadOnly">Selected Invoice: <a href="#" onClick="javascript:InvoiceDetailsPDF('<%=strInvNo%>');"><%=strInvNo%></a><%=strInvoiceNoN1%></font></td>
        </tr>
       <!--  <tr><td height="5" colspan="3"></td></tr> -->
        <tr>
            <td width="10">&nbsp;</td>
            <td colspan="2">
                <table cellspacing="1" class="request-service">
                    <tr>
						<th>Contract Type</th>
                        <th>Contract Number</th>
                        <th>Contract Status</th>
                        <th>Expiration Date</th>
                        <!--MW Project Changes - Start-->
                        <!--<td>Base Period</td>
                        <td>Overage Period</td>-->
                        <!--MW Project Changes - End-->
                        <th>Service Branch</th>
                    </tr>
                    <%
                        if(alInCorrList != null && alInCorrList.size() > 0) {
                            objOks = (Canon_E193_OKSBillingDtlsObj)alInCorrList.get(0);
                            String strContractNum = objOks.getContractNumber()==null?"":objOks.getContractNumber();
							String strAggregateContractNum = objOks.getAggregateContractNumber()==null?"":objOks.getAggregateContractNumber();
							//System.out.println("InstanceId in contLineDetails : " + objOks.getInstanceId());
							//System.out.println("Line Details!!!!!! Non Fleet:: : " + strContractNum);
                    %>
                            <input type="hidden" name="contractId" value="<%=objOks.getContractId()%>">
                            <input type="hidden" name="contractNo" value="<%=strContractNum%>">
                            <input type="hidden" name="fleetContract" value="<%=objOks.getFleetContract()%>">
							<input type="hidden" name="aggregateContractNum" value="<%=strAggregateContractNum%>">
                            <tr class="tdTableCellStyle">
								<td style="text-align: center;"><%=strAggregateContractNum%></td>
                                <td style="text-align: center;"><%=strContractNum%></td>
                                <td style="text-align: center;"><%=strContractStatus%></td>
                                <td style="text-align: center;"><%=strExpirationDate%></td>
                                <!--MW Project Changes - Start-->
                                <!--<td><%=strBasePeriod%></td>
                                <td style="text-align: center;"><%=strOveragePeriod%></td>-->
                                <!--MW Project Changes - End-->
                                <td style="text-align: center;"><%=strServiceBranch%></td>
                            </tr>
                <%
                        }else{
                %>
                            <tr>
                                <td colspan="6" style="text-align: center;">
                                    <font class="promptReadOnly"><b>Invoice lines not found.</b></font>
                                </td>
                            </tr>
                <%      } %>
                </table>
            </td>
        </tr>
        <%
        boolean isDisabledRAll = true, isDisabledPAll = true;
        //MW Project Changes - Start
       if(invoiceTypeUsage!="")
        if("CONSOLIDATED".equalsIgnoreCase(invoiceTypeUsage) || "USAGE".equalsIgnoreCase(invoiceTypeUsage) /* ||"USAGE".equalsIgnoreCase(strInvoiceType) */)
        {
        %>
           <!--  <tr><td height="5" colspan="3"></td></tr> -->
            <tr>
                <td class="tableQuestionCell" colspan="3">
                    <font class="promptReadOnly" color="#FF0000">
                        <b><%=strContLineDetailsUN1%></b>
                    </font>
                </td>
            </tr>
        
            <!-- <tr><td height="5" colspan="3"></td></tr> -->
            <tr>
                <td width="10">&nbsp;</td>
                <td colspan="2">
                    <table cellspacing="1" class="request-service">
                        <tr >
                            <th >Service Program Name</th>
                            <th width='90'>Overage Period</th>
							<th width='50'>Invoice Number </th>
                            <th width='85'>Serial Number</th>
                            <th width='75'>Item Code</th>
                            <th>Model Description</th>
                            <th align="center" width='50'>Meter Read<br><input type="checkbox" id="selRAll" name="selRAll" value="allR" onClick="javascript:fnSelRAll(this)"></th>
                            <th align="center" width='50'>Base Price/CPC<br><input type="checkbox" id="selPAll" name="selPAll" value="allP" onClick="javascript:fnSelPAll(this)"></th>
                            <th align="center" width='50'>Frequency</th>
                        </tr>
                        <% int ival = alInCorrList.size();
                        //System.out.println("Line Details!!!!!! USAGE ival = "+ival  + " alInCorrList " + alInCorrList);
                            if(alInCorrList != null && alInCorrList.size() > 0) {
                                %>
                                <%=displayLineDetails(alInCorrList,"USAGE",alFrequencyCd)%>
                               
                                <%
                            }else{
                        %>
                                <tr class="tdTableCellStyle">
                                    <td colspan="5" style="text-align: center;">
                                        <font class="promptReadOnly"><b>Invoice lines not found.</b></font>
                                    </td>
                                </tr>
                        <%  } %>
                    </table>
                </td>
            </tr>
        <%
        }  
        if(invoiceTypeBase!="")
        if( "CONSOLIDATED".equalsIgnoreCase(invoiceTypeBase) ||  "BASE".equalsIgnoreCase(invoiceTypeBase)/* ||"BASE".equalsIgnoreCase(strInvoiceType) */)
        {
        %>
            <!-- <tr><td height="5" colspan="3"></td></tr> -->
            <tr>
                <td class="tableQuestionCell" colspan="3">
                    <font class="promptReadOnly" color="#FF0000">
                        <b><%=strContLineDetailsBN1%></b>
                    </font>
                </td>
            </tr>
            
            <!-- <tr><td height="5" colspan="3"></td></tr> -->
            <tr>
                <td width="10">&nbsp;</td>
                <td colspan="2">
                    <table cellspacing="1" class="request-service">
                        <tr>
                            
                            <th >Service Program Name</th>
                            <th width='90'>Base Period</th>
							<th width='50'>Invoice Number </th>
                            <th width='85'>Serial Number</th>
							<th width='85'>Product Number</th>
                            <th width='75'>Item Code</th>
                            <th>Model Description</th>
                            <th align="center"  width='50'><input type="checkbox" id="selBAll" name="selBAll" value="allB" onClick="javascript:fnSelBAll(this)"></th>
                            <th align="center" width='50'>Frequency</th>
                         </tr>
                        <% 
                        int ival = alInCorrList.size();
                        //System.out.println("Line Details!!!!!! CONSOLIDATED ival1 = "+ival + " alInCorrList" + alInCorrList);
                            if(alInCorrList != null && alInCorrList.size() > 0) {
                                %>
                                <%=displayLineDetails(alInCorrList,"BASE",alFrequencyCd)%>
                                <%
                            }else{
                        %>
                                <tr class="tdTableCellStyle">
                                    <td colspan="5">
                                        <font class="promptReadOnly"><b>Invoice lines not found.</b></font>
                                    </td>
                                </tr>
                        <%  } %>
                    </table>
                </td>
            </tr>
        <%
        }
        //MW Project Changes - End
      //  }
        %>
        
        <!-- <tr><td height="10" colspan="3"></td></tr> -->
       
    </table>
		<div style="text-align: center; margin-bottom: 5px;">

			<a class="btn_red" href="javascript:history.back();">Prev</a>
			<%
				if (alInCorrList != null && alInCorrList.size() > 0) {
			%>

			<a class="btn_red" style="margin-left: 10px; margin-right: 10px; " href="javascript:action_func1();">Next</a>
			<%
			
				} else {
			%>
			<a class="btn_red" href="#">Next</a>
			<%
				}
			%>
		</div>
	</form>
   
<%//MW Project Changes - Start %>

<%!
	String displayLineDetails(ArrayList alInCorrList, String strInvoiceType,ArrayList alFrequencyCd) {
		Canon_E193_OKSBillingDtlsObj objOks = null;
		String strSerialNo = "", strAmt = "", strModel = "", strDisModel = "", strFleetContract = "", strFleetSerialNo = "", strPeriod = "", strServiceProgram = "", strInvNumber = "", strProductNum = "";

		boolean isDisabledRAll = false, isDisabledPAll = false, isDisabledBAll = false;
		//ArrayList alSeverityList = objSeverityDao.getSeverity(); 
        //System.out.println( "Line Details!!!!!! strInvoiceType= " + strInvoiceType);
		if ("USAGE".equalsIgnoreCase(strInvoiceType)) {
			isDisabledRAll = true;
			isDisabledPAll = true;
		} else {
			isDisabledBAll = true;
		}

		StringBuffer table = new StringBuffer();
		String tempProgramName = "";
		String tempPeriod = "";
		String tempFleetSerialNo = "";
		String tempProgramNameForpricing = "";
		//String tempInvNum = "";
		int isvcInvLinePkValue = 0;
		
		int freqCount = 0;
		int freqBaseCount = 0;
		
 		System.out.println("Line Details!!!!!! ::::Size: "+ alInCorrList.size());
		for (int i = 0; i < alInCorrList.size(); i++) {
			objOks = (Canon_E193_OKSBillingDtlsObj) alInCorrList.get(i);
				if (strInvoiceType.equalsIgnoreCase(objOks.getInvoiceType()) ) {
				strSerialNo = objOks.getSerialNumber() == null ? "" : objOks
						.getSerialNumber();
				strFleetContract = objOks.getFleetContract() == null ? ""
						: objOks.getFleetContract();
				strFleetSerialNo = objOks.getFleetSeriaNumber() == null ? ""
						: objOks.getFleetSeriaNumber();
				strPeriod = objOks.getDateBilledFrom() + " - <br>"
						+ objOks.getDateBilledTo();
				strServiceProgram = objOks.getServiceProgram() == null ? ""
						: objOks.getServiceProgram();
				strInvNumber = objOks.getTrxNumber() == null ? "" : objOks
						.getTrxNumber();
				strProductNum = objOks.getProductNumber() == null ? "" : objOks
						.getProductNumber();
				/*
				if(strInvoiceType.equalsIgnoreCase(objOks.getInvoiceType())) 
				    strAmt = CanonCustAppUtil.getDoubleWithFormat(objOks.getOverageAmount());
				else strAmt = CanonCustAppUtil.getDoubleWithFormat(objOks.getBaseAmount());
				 */
				isvcInvLinePkValue=objOks.getSvcinvlinePk();
				String invoiceSerialum = strSerialNo + "~" + strInvNumber + "~"
						+ strProductNum  + "~"+isvcInvLinePkValue ;
				 //System.out.println("Line Details!!!!!! ::::InvoiceSerialum: "+ invoiceSerialum); 
				if (!("Y".equalsIgnoreCase(strFleetContract)&& "".equals(strFleetSerialNo) && "".equals(strSerialNo))
						|| "Y".equalsIgnoreCase(strFleetContract))
				{
					if ("N".equalsIgnoreCase(objOks.getFleetContract())
							|| objOks.getLseId() == 12) {

						strModel = objOks.getModel() == null ? "" : objOks
								.getModel();

						// Start: MW changes
						if ("Y".equalsIgnoreCase(strFleetContract)) {
							strDisModel = strFleetSerialNo;
						} else
							strDisModel = strModel;
						// End: MW changes
						//Commented for MW changes
						//strDisModel = strModel;
						strModel = strModel
								+ "<br>"
								+ (objOks.getLineDescription() == null ? ""
										: objOks.getLineDescription());
					} else {
						strModel = objOks.getModel() == null ? "" : objOks
								.getModel();
                        //System.out.println("Line Details!!!!!! strModel= " + strModel);
						// Start: MW changes
						if ("Y".equalsIgnoreCase(strFleetContract)) {
							strDisModel = strFleetSerialNo;
						} else
							strDisModel = strModel;
						// End: MW changes
						//Commented for MW changes
						//strDisModel = strModel;
					}
     				table.append("<tr class='tdTableCellStyle'>");
					//System.out.println( "Line Details!!!!!! tempProgramName:= "+ tempProgramName +" strServiceProgram := " + strServiceProgram + " tempFleetSerialNo := " + tempFleetSerialNo + " strFleetSerialNo := " + strFleetSerialNo);
					if (tempProgramName.equalsIgnoreCase(strServiceProgram))
						table.append("<td align='center'>&nbsp;</td>");
					else
						table.append("<td>" + strServiceProgram + "</td>");

					//if(tempPeriod.equalsIgnoreCase(strPeriod) && tempProgramName.equalsIgnoreCase(strServiceProgram))
					if (tempProgramName.equalsIgnoreCase(strServiceProgram)	&& tempPeriod.equalsIgnoreCase(strPeriod))
						table.append("<td width='75'>&nbsp;</td>");
					else
						table.append("<td nowrap width='90'>" + strPeriod+ "</td>"); // Base Period
					if ("".equals(strInvNumber) || strInvNumber == null) { // Invoice Number
						table.append("<td width='85'>" + strInvNumber + "</td>");
						//System.out.println("Here 2");
					} else
						table.append("<td width='85'>" + strInvNumber + "</td>");
					/*if(strInvNumber != null && tempInvNum != null && tempProgramName.equalsIgnoreCase(strServiceProgram) 
						&& tempPeriod.equalsIgnoreCase(strPeriod) && tempInvNum.equalsIgnoreCase(strInvNumber))
					{
						table.append("<td width='85'>&nbsp;</td>");
						//System.out.println("Here 2");
					} else
						table.append("<td width='85'>" + strInvNumber + "</td>"); */

					if ("".equals(strSerialNo) || strSerialNo == null) {
						table.append("<td width='85'>" + strDisModel + "</td>");
						//System.out.println("Here 2");
					} else
						table.append("<td width='85'>" + strSerialNo + "</td>");
					if ("BASE".equalsIgnoreCase(strInvoiceType)) {						
						if(objOks.getProductNumber()==null)
						{
							table.append("<td width='75'>&nbsp;</td>");
							//System.out.println("Line Details!!!!!! BASE PRODUCT NO: ");
						}
						else
						{
							table.append("<td width='85'>"+objOks.getProductNumber() + "</td>");
							//System.out.println("Line Details!!!!!! BASE PRODUCT NO1: ");
						}
					}

					if (objOks.getItemCode() == null)
						table.append("<td width='75'>&nbsp;</td>");
					else
						table.append("<td width='75'>" + objOks.getItemCode()
								+ "</td>");

					if (strModel == null)
						table.append("<td>&nbsp;</td>");
					else
						table.append("<td>" + strModel + "</td>");

					
					
					if ("USAGE".equalsIgnoreCase(objOks.getInvoiceType())
							&& "Y".equalsIgnoreCase(strFleetContract))
					{

						String tempSerialNo = null;

						if (strFleetSerialNo == null
								|| strFleetSerialNo.equals("")
								|| strFleetSerialNo.equalsIgnoreCase("null"))
							tempSerialNo = strSerialNo;
						else
							tempSerialNo = strFleetSerialNo;

						String tempInvSerialNo = tempSerialNo + "~"
								+ strInvNumber + "~" + strProductNum + "~"+isvcInvLinePkValue ;
						

						if (objOks.getStartTotalHardRead() == -99999) {
							table.append("<td align='center'>&nbsp;</td>");
						} else {
							isDisabledRAll = false;
							if (objOks.isChekcboxRCheck() && (strSerialNo != null && strSerialNo.trim().length() > 0 ))
							{
								table.append("<td align='center'><input type='checkbox' name='rSerialNo' value='"
										+ invoiceSerialum
										+ "'  checked onClick='javascript:fnSelR(this)'></td>");
								//System.out.println("Line Details!!!!!! invoiceSerialum For Usage Read_0: " + invoiceSerialum);
							 }else if (!objOks.isChekcboxRCheck() && (strSerialNo != null && strSerialNo.trim().length() > 0 ))
							 {
								table.append("<td align='center'><input type='checkbox' name='rSerialNo' value='"
										+ invoiceSerialum
										+ "'  onClick='javascript:fnSelR(this)'></td>");
								//System.out.println("Line Details!!!!!! invoiceSerialum For Usage Read_1: " + invoiceSerialum);
							 }else
							 {
								table.append("<td align='center'>&nbsp;</td>");
								//System.out.println("Line Details!!!!!! invoiceSerialum For Usage Read_2: " + invoiceSerialum);
							 }
						}

						if (objOks.getTier1Rate() == -99999.0) {
							table.append("<td align='center'>&nbsp;</td>");
						} else {
							isDisabledPAll = false;
							//System.out.println("Line Details!!!!!! tempInvSerialNo For Base Price: " + tempInvSerialNo);
								 /*if(tempProgramName.equalsIgnoreCase(strServiceProgram)	&& tempFleetSerialNo.equalsIgnoreCase(strFleetSerialNo)	&& tempPeriod.equalsIgnoreCase(strPeriod))
								{
									table.append("<td align='center'>&nbsp;</td>");																		
								}
								else
								{ */
									if (objOks.isChekcboxPCheck() && (strSerialNo == null || "".equals(strSerialNo.trim())))
									{
										table.append("<td align='center'><input type='checkbox' name='pSerialNo' value='"+ tempInvSerialNo + "' checked onClick='javascript:fnSelP(this)'></td>");
										//System.out.println("Line Details!!!!!! invoiceSerialum For Usage Price_0: " + invoiceSerialum);
									}
									else if (!objOks.isChekcboxPCheck() && (strSerialNo == null || "".equals(strSerialNo.trim())))
									{
										table.append("<td align='center'><input type='checkbox' name='pSerialNo' value='"+ tempInvSerialNo+ "' onClick='javascript:fnSelP(this)'></td>");
										//System.out.println("Line Details!!!!!! invoiceSerialum For Usage Price_1: " + invoiceSerialum);
									}
									else 
									{
										table.append("<td align='center'>&nbsp;</td>");
										//System.out.println("Line Details!!!!!! invoiceSerialum For Usage Price_2: " + invoiceSerialum);
									}
								/*}*/
							
						}
						
						//System.out.println( "Line Details!!!!!! tempProgramName_1:= "+ tempProgramName +" tempPeriod := " + tempPeriod + " tempFleetSerialNo := " + tempFleetSerialNo );
						// table.append("<td align='center'><select  name='frequency'  <option value=''>Please Select</option>     <option value='mth'>Month</option> 	  <option value='qtr'>Quarter</option>  <option value='yr'>Year</option>   <select>  </td>  ");

						//ArrayList alFrequencyCd = objSeverityDao.getFrequencyCodes();
						// ArrayList alFrequencyCd = (ArrayList)objSeverityDao.getFrequencyCodes();

						table.append("<td align='center'>");
						table.append("  <select  name='frequency"
									+ freqCount
									+ "' id='frequency"+freqCount+"' onchange='fnSelFrequency(this.value)'  >");
						table.append("<option value=''>Please Select</option>");
	
						if (alFrequencyCd != null && alFrequencyCd.size() > 0) {
							for (int k = 0; k < alFrequencyCd.size(); k++) {
								table.append("<option value='"
										+ alFrequencyCd.get(k) + "'> "
										+ alFrequencyCd.get(k) + "</option>");
							}
						}
						table.append("  </select>");
						table.append("  </td>");
						freqCount++;
						
					} 
					else if ("USAGE".equalsIgnoreCase(objOks.getInvoiceType())) 
					{
						//End Changes Mangala for S21
						isDisabledRAll = false;
						isDisabledPAll = false;
						//System.out.println("Line Details!!!!!! !!!InvoiceSerialum!!!"+ invoiceSerialum + " ");
						if (objOks.isChekcboxRCheck())
							table.append("<td align='center'><input type='checkbox' name='rSerialNo' value='"
									+ invoiceSerialum
									+ "'  checked onClick='javascript:fnSelR(this)'></td>");
						else
							table.append("<td align='center'><input type='checkbox' name='rSerialNo' value='"
									+ invoiceSerialum
									+ "' onClick='javascript:fnSelR(this)'></td>");

						if (objOks.isChekcboxPCheck())
							table.append("<td align='center'><input type='checkbox' name='pSerialNo' value='"
									+ invoiceSerialum
									+ "' checked onClick='javascript:fnSelP(this)'></td>");
						else
							table.append("<td align='center'><input type='checkbox' name='pSerialNo' value='"
									+ invoiceSerialum
									+ "' onClick='javascript:fnSelP(this)'></td>");
						//  table.append("<td align='center'><select  name='frequency'  <option value=''>Please Select</option>     <option value='mth'>Month</option> 	  <option value='qtr'>Quarter</option>  <option value='yr'>Year</option>   <select>  </td>  ");

						table.append("<td align='center'>");
						
						table.append("  <select  name='frequency"
									+ freqCount
									+ "' id='frequency"+freqCount+"' onchange='fnSelFrequency(this.value)' >");
						table.append("<option value=''>Please Select</option>");
						if (alFrequencyCd != null && alFrequencyCd.size() > 0) {
							for (int k = 0; k < alFrequencyCd.size(); k++) {
								table.append("<option value='"
									+ alFrequencyCd.get(k) + "'> "
									+ alFrequencyCd.get(k) + "</option>");
							}
						}
						table.append("  </select>");
						table.append("  </td>");
						freqCount++;
					} else if ("BASE".equalsIgnoreCase(objOks.getInvoiceType())
							&& "Y".equalsIgnoreCase(strFleetContract)) 
					{
						//System.out.println("Line Details!!!!!! BASE with isDisabledBAll - false= ");
						String tempSerialNo = null;
						if (strFleetSerialNo == null
								|| strFleetSerialNo.equals("")
								|| strFleetSerialNo.equalsIgnoreCase("null"))
							tempSerialNo = strSerialNo;
						else
							tempSerialNo = strFleetSerialNo;
						String tempInvSerialNo = tempSerialNo + "~"
								+ strInvNumber + "~" + strProductNum + "~"+isvcInvLinePkValue;
						
						//System.out.println("Line Details!!!!!! !!!tempInvSerialNo!!!"+ tempInvSerialNo);

						if (objOks.getBaseAmount() == -99999.0) {
							table.append("<td align='center' >&nbsp;</td>");
						} else {

							
							if(strSerialNo == null || (strSerialNo != null && "".equals(strSerialNo.trim()))) 
							{
								isDisabledBAll = false;//isDisabledPAll = false;
								if (objOks.isChekcboxBCheck())
									table.append("<td align='center' ><input type='checkbox' name='bSerialNo' value='"
											+ tempInvSerialNo
											+ "'  checked onClick='javascript:fnSelB(this)'></td>");
								else
									table.append("<td align='center' ><input type='checkbox' name='bSerialNo' value='"
											+ tempInvSerialNo
											+ "' onClick='javascript:fnSelB(this)'></td>");
	
							}
							else
							{
							    table.append("<td align='center' >&nbsp;</td>");
							}
						}
						/*
						 //isDisabledPAll = false;
						 if(strFleetSerialNo.startsWith("FLT") && "".equals(strSerialNo)) {
						 isDisabledBAll = false;
						
						 if(objOks.isChekcboxBCheck())
						 table.append("<td align='center' colspan='2'><input type='checkbox' name='bSerialNo' value='" + strFleetSerialNo + "'  checked onClick='javascript:fnSelB(this)'></td>");
						 else
						 table.append("<td align='center' colspan='2'><input type='checkbox' name='bSerialNo' value='" + strFleetSerialNo + "' onClick='javascript:fnSelB(this)'></td>");
						
						 }else{
						 table.append("<td align='center' colspan='2'>&nbsp;</td>");
						 }
						 */
						 
						table.append("<td align='center'>");
						table.append("  <select  name='frequencyBase"
									+ freqBaseCount
									+ "' id='frequencyBase"+freqBaseCount+"' onchange='fnSelFrequency(this.value)' >");
						table.append("<option value=''>Please Select</option>");
							
						if (alFrequencyCd != null && alFrequencyCd.size() > 0) {
							for (int k = 0; k < alFrequencyCd.size(); k++) {
									table.append("<option value='"
											+ alFrequencyCd.get(k) + "'> "
											+ alFrequencyCd.get(k) + "</option>");
							}
						}
						table.append("  </select>");
						table.append("  </td>");
						freqBaseCount++;
					}  else if ("BASE".equalsIgnoreCase(objOks.getInvoiceType())) {
						isDisabledBAll = false;//isDisabledPAll = false;
                        //System.out.println("Line Details!!!!!! BASE with isDisabledBAll - false= "+ invoiceSerialum);
                        
						if (objOks.isChekcboxBCheck())
							table.append("<td align='center' ><input type='checkbox' name='bSerialNo' value='"
									+ invoiceSerialum
									+ "'  checked onClick='javascript:fnSelB(this)'></td>");
						else
							table.append("<td align='center' ><input type='checkbox' name='bSerialNo' value='"
									+ invoiceSerialum
									+ "' onClick='javascript:fnSelB(this)'></td>");
						
						table.append("<td align='center'>");
						
						table.append("  <select  name='frequencyBase"
									+ freqBaseCount
									+ "' id='frequencyBase"+freqBaseCount+"' onchange='fnSelFrequency(this.value)' >");
						table.append("<option value=''>Please Select</option>");
						if (alFrequencyCd != null && alFrequencyCd.size() > 0) {
							for (int k = 0; k < alFrequencyCd.size(); k++) {
								table.append("<option value='"
									+ alFrequencyCd.get(k) + "'> "
									+ alFrequencyCd.get(k) + "</option>");
							}
						}
						table.append("  </select>");
						table.append("  </td>");
						freqBaseCount++;
					} 
					
					tempProgramName = strServiceProgram;
					tempPeriod = strPeriod;
					//tempInvNum = strInvNumber;
					//tempFleetSerialNo = strFleetSerialNo;
                   
					//table.append("<td> <select> <option value="">Please Select</option><option value=mth>Month</option><option value=qtr>Quarter</option><option value=yr>Year</option> </select></td>");
                  //System.out.println("Line Details!!!!!! Here I reached");
					table.append("</tr>");
				}
			}
		} // End Of Upper If Loop.
		//System.out.println("Line Details!!!!!! Here I reached");
		if ("USAGE".equalsIgnoreCase(strInvoiceType)) {
			table.append("<input type='hidden' name='freqCount' value='"+freqCount+"'>");
		}else {
			table.append("<input type='hidden' name='freqBaseCount' value='"+freqBaseCount+"'>");
		}

		if (isDisabledRAll) {
			table.append("<script type='text/javascript'>document.getElementById('selRAll').disabled = true;</script>");
		}
		if (isDisabledPAll) {
			table.append("<script type='text/javascript'>document.getElementById('selPAll').disabled = true;</script>");
		}
		if (isDisabledBAll) {
			table.append("<script type='text/javascript'>document.getElementById('selBAll').disabled = true;</script>");
		}
		System.out.println("End Line Details");
		return table.toString();
	} // End Of For Loop.

	//MW Project Changes - End%>

<%@ include file="canon_E193_csBottomInc.jsp" %>
</div>