<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20221129170436 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NFCL2760Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Manual Cash Application Entry">
<center>
	<table width="1128"  table border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>

<%-- ######################################## HEADER ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">

				<table border="0" cellpadding="1" cellspacing="0">
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="10">
								<col width="100">
								<col width="167">
								<col width="250">
								<col width="100">
								<col width="250">
								<tr>
									<td></td>
									<td class="stab">Mode</td>
									<td class="pOut"><ezf:label name="xxModeNm23Txt" ezfName="xxModeNm23Txt" /></td>
									<td></td>
									<td class="stab">Batch Name</td>
									<td class="pOut" align="left"><ezf:label name="arBatRcptNm_H1" ezfName="arBatRcptNm_H1" /></td>
								</tr>
							</table>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="10">
								<col width="100">
								<col width="167">
								<col width="250">
								<col width="100">
								<col width="250">
								<tr>
									<td></td>
									<td class="stab">Receipt Doc Num</td>
									<td class="pOut"><ezf:label name="rcptNum" ezfName="rcptNum" /></td>
									<td></td>
									<td class="stab">Receipt Nnmber</td>
									<td class="pOut" align="left"><ezf:label name="rcptChkNum_H1" ezfName="rcptChkNum_H1" /></td>
								</tr>
							</table>

							<table border="0" cellpadding="1" cellspacing="0">
								<col width="10">
								<col width="100">
								<col width="167">
								<col width="250">
								<col width="100">
								<col width="250">
								<tr>
									<td></td>
									<td class="stab">Receipt Date</td>
									<td class="pOut"><ezf:label name="rcptDt" ezfName="rcptDt" /></td>
									<td></td>
									<td class="stab">Receipt Amount</td>
									<td class="pOut" align="right"><ezf:label name="dealRcptAmt" ezfName="dealRcptAmt" /></td>
								</tr>
							</table>

							<table border="0" cellpadding="1" cellspacing="0">
								<col width="10">
								<col width="100">
								<col width="30">
								<col width="131">
								<col width="250">
								<col width="100">
								<col width="250">
								<tr>
									<td></td>
									<td class="stab">Receipt Type</td>
									<td class="pOut"><ezf:label name="arRcptTrxTpCd" ezfName="arRcptTrxTpCd" /></td>
									<td class="pOut"><ezf:label name="arRcptTrxTpNm" ezfName="arRcptTrxTpNm" /></td>
									<td></td>
									<td class="stab">Applied Amount</td>
									<td class="pOut" align="right"><ezf:label name="xxTotAmt_H0" ezfName="xxTotAmt_H0" /></td>
								</tr>
							</table>

							<table border="0" cellpadding="1" cellspacing="0">
								<col width="10">
								<col width="100">
								<col width="30">
								<col width="300">
								<col width="81">
								<col width="100">
								<col width="250">
								<tr>
									<td></td>
									<td class="stab">Receipt Status</td>
									<td class="pOut"><ezf:label name="arRcptStsCd_H1" ezfName="arRcptStsCd_H1" /></td>
									<td class="pOut"><ezf:label name="arRcptStsNm_H1" ezfName="arRcptStsNm_H1" /></td>
									<td></td>
									<td class="stab">On Account</td>
									<td class="pOut" align="right"><ezf:label name="xxOnAcctAmt" ezfName="xxOnAcctAmt" /></td>
								</tr>
							</table>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="10">
								<col width="100">
								<col width="30">
								<col width="300">
								<col width="81">
								<col width="100">
								<col width="250">
								<tr>
									<td></td>
									<td class="stab">Receipt Source</td>
									<td class="pOut"><ezf:label name="arRcptSrcCd_H1" ezfName="arRcptSrcCd_H1" /></td>
									<td class="pOut"><ezf:label name="arRcptSrcNm_H1" ezfName="arRcptSrcNm_H1" /></td>
									<td></td>
									<td class="stab">Unapplied</td>
									<td class="pOut" align="right"><ezf:label name="xxTotAmt_H1" ezfName="xxTotAmt_H1" /></td>

								</tr>
							</table>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="10">
								<col width="100">
								<col width="72">
								<col width="345">
								<col width="100">
								<col width="250">
								<tr>
									<td></td>
									<td class="stab">Accounting Date</td>
									<td class="pOut"><ezf:label name="glDt_H2" ezfName="glDt_H2" /></td>
									<td></td>
									<td class="stab">Refund</td>
									<td class="pOut" align="right"><ezf:label name="dealRfAmt" ezfName="dealRfAmt" /></td>
								</tr>
							</table>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="10">
								<col width="100">
								<col width="72">
								<col width="390">
								<col width="500">
								<tr>
									<td></td>
									<td class="stab">Customer</td>
									<td class="pOut"><ezf:label name="payerCustCd" ezfName="payerCustCd" /></td>
									<td align="left"><ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW60" otherAttr=" size=\"37\""/></td>
            								<td align="right"><ezf:inputButton name="CalcGrossAmount" value="Calculation" htmlClass="pBtn10" otherAttr=" id=\"CalcGrossAmountId\""/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

				<hr>

<%-- ######################################## DETAIL ######################################## --%>
				<table border="0">
					<tr>
							<col width="72" align="right">
							<col width="72" align="right">
							<col width="62" align="right">
							<col width="90">
							<col width="62" align="right">
							<col width="20">
							<col width="686">
							<col width="13">
							<td><ezf:inputButton name="Check_All" value="CheckAll" htmlClass="pBtn6" otherAttr=" id=\"Check_All\""/></td>
							<td><ezf:inputButton name="Un_Check_All" value="UncheckAll" htmlClass="pBtn6" otherAttr=" id=\"Un_Check_All\""/></td>
							<td>Apply To</td>
							<td>
								<ezf:inputText name="arCustRefNum_H1" ezfName="arCustRefNum_H1" value="123" otherAttr=" size=\"15\" tabindex=\"-1\""/>
							</td>
							<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" id=\"Search\""/></td>
							<td></td>
							<td align="right">
								<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
									<jsp:param name="beanId"          value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"         value="A" />
									<jsp:param name="ShowingFrom"     value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"       value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"       value="xxPageShowOfNum" />
									<jsp:param name="ShowingCurrent"  value="xxPageShowCurNum" />
									<jsp:param name="ShowingTotal"    value="xxPageShowTotNum" />
									<jsp:param name="ViewMode"        value="FULL" />
								</jsp:include>
							</td>
							<td></td>
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<!-- id:leftTopTBL -->
							<div id="leftTopTBL" style="overflow-x:none; width:30; overflow-y:none;">
								<table border="1" cellpadding="0" cellspacing="0" width="30" height="16">
									<col width="30" align="center">
									<tr height="16">
										<td class="pClothBs fontMin"></td>
									</tr>
								</table>
							</div>

							<%-- id:leftTBL(MID) --%>
							<div id="leftTBL" style="overflow-x:hidden; height:323;  width:30; overflow-y:hidden;" onScroll="synchroScrollTop( this.id, new Array( 'detailTBL' ) );">
								<table border="1" cellpadding="0" cellspacing="0" width="30" height="28" id="A1">
									<col width="30">
									<ezf:row ezfHyo="A">
										<tr id="id_b_row{EZF_ROW_NUMBER}" height="28">
											<td class="fontMin" align="center"><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServer('OnClick_Chk', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"xxChkBox#{EZF_ROW_NUMBER}\""/></td>
										</tr>
									</ezf:row>
								</table>
							</div>
						</td>
						<td valign="top">
							<!-- id:topTBL -->
							<div id="topTBL" style="overflow-y:hidden; overflow-x:hidden; width:1069;" onScroll="synchroScrollLeft( this.id, new Array( 'detailTBL' ) );">
								<!-- #109 2014/03/12 Start -->
								<!-- <table border="1" cellpadding="0" cellspacing="0" width="2796" height="16"> -->
								<table border="1" style="table-layout:fixed;"cellpadding="0" cellspacing="0" width="1390" height="16">
								<!-- #109 2014/03/12 End -->

                                    <col width="60" align="center">
                                    <col width="120" align="center">
									<col width="40" align="center">
									<col width="80" align="center">
									<col width="50" align="center">
									<col width="150" align="center">
									<col width="80" align="center">
									<col width="120" align="center">
									<col width="120" align="center">
									<col width="130" align="center">
									<col width="120" align="center">
									<col width="200" align="center">
									<col width="80" align="center">
									<col width="300" align="center">
									<tr height="16" style="font-size:8pt;">
										<td class="pClothBs fontMin">Status</td>
										<td class="pClothBs fontMin">Apply To</td>
										<td class="pClothBs fontMin">>></td>
										<td class="pClothBs fontMin">Bill#</td>
										<td class="pClothBs fontMin">Class</td>
										<td class="pClothBs fontMin">Adj Type</td>
										<td class="pClothBs fontMin">Trx Date</td>
										<td class="pClothBs fontMin">Invoice Amount</td>
										<td class="pClothBs fontMin">Current Balance</td>
										<td class="pClothBs fontMin">Applied Amount</td>
										<td class="pClothBs fontMin">Customer Number</td>
										<td class="pClothBs fontMin">Customer Name</td>
										<td class="pClothBs fontMin">Apply Date</td>
										<td class="pClothBs fontMin">Charge Account</td>
									</tr>
								</table>
							</div>
							<%-- id:detailTBL(MID) --%>
                            <!-- START 2022/01/06 G.Delgado [QC#59329, MOD]-->
                            <!-- div id="detailTBL" style="margin:0px;padding:0px;word-break:break-all; overflow-x:scroll; overflow-y:scroll; height:340; width:1086;" onScroll="synchroScrollTop( this.id, new Array( 'leftTBL' ) );synchroScrollLeft( this.id, new Array( 'topTBL' ) );" -->
                            <div id="detailTBL" style="margin:0px;padding:0px;word-break:break-all; overflow-x:scroll; overflow-y:scroll; height:340; width:1086;" onScroll="synchroScroll(this.id, new Array('leftTBL'));synchroScrollLeft( this.id, new Array( 'topTBL' ) );">
                            <!-- END 2022/01/06 G.Delgado [QC#59329, MOD]-->
								<table border="1" cellpadding="0" cellspacing="0" width="1390" id="A2" style="table-layout:fixed;">
									<col width="60" align="center">
									<col width="120">
                                    <col width="40" align="center">
									<col width="80" align="center">
									<col width="50" align="center">
									<col width="150" align="center">
									<col width="80" align="center">
									<col width="120" align="right">
									<col width="120" align="right">
									<col width="130" align="right">
									<col width="120" align="left">
									<col width="200" align="center">
									<col width="80" align="center">
									<col width="300" align="right">
									<ezf:row ezfHyo="A">
										<tr height="28" id="id_row{EZF_ROW_NUMBER}">
                                            <td class="fontMin" align="center"><ezf:label name="xxArCashApplyStsTxt" ezfName="xxArCashApplyStsTxt" ezfHyo="A" ezfArrayNo="0" /></td>
											<td class="fontMin">
												<ezf:inputText name="arCustRefNum" ezfName="arCustRefNum" value="01" ezfHyo="A" ezfArrayNo="0" otherEvent1="OnBlur_Inv" otherAttr=" size=\"15\" ezffocusout=\"OnBlur_Inv\""/>
											</td>
											<td align="center"><ezf:inputButton name="SearchTrxLine" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchTrxLine#{EZF_ROW_NUMBER}\" tabindex=\"-1\""/></td>
											<td class="fontMin" align="center"><ezf:label name="grpInvNum" ezfName="grpInvNum" ezfHyo="A" ezfArrayNo="0" /></td>
											<td class="fontMin" align="center"><ezf:label name="arTrxTpCd" ezfName="arTrxTpCd" ezfHyo="A" ezfArrayNo="0" /></td>
											<td class="fontMin" align="center"><ezf:inputText name="arAdjTpNm_A3" ezfName="arAdjTpNm_A3" ezfHyo="A" ezfArrayNo="0" htmlClass="fontMin" otherAttr=" size=\"27\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/></td>
											<td class="fontMin" align="center"><ezf:label name="arTrxDt" ezfName="arTrxDt" ezfHyo="A" ezfArrayNo="0" /></td>
											<td class="fontMin" align="right"><ezf:label name="dealOrigGrsAmt" ezfName="dealOrigGrsAmt" ezfHyo="A" ezfArrayNo="0" /></td>
											<td class="fontMin" align="right"><ezf:label name="dealRmngBalGrsAmt" ezfName="dealRmngBalGrsAmt" ezfHyo="A" ezfArrayNo="0" /></td>
											<td class="fontMin" align="center"><ezf:inputText name="xxDealApplyAmtNum_A1" ezfName="xxDealApplyAmtNum_A1" value="-123,456,789,123.12" ezfHyo="A" ezfArrayNo="0" htmlClass="fontMin" otherAttr=" style=\"text-align:right\" size=\"15\" maxlength=\"23\" id=\"xxDealApplyAmtNum_A1#{EZF_ROW_NUMBER}\" tabindex=\"-1\""/></td>
											<td class="fontMin"><ezf:label name="billToCustAcctCd_A1" ezfName="billToCustAcctCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                            <td class="fontMin"><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" ezfHyo="A" ezfArrayNo="0" htmlClass="fontMin" otherAttr=" size=\"25\" tabindex=\"-1\""/></td>
											<td class="fontMin" align="center"><ezf:label name="cashAppDt_A1" ezfName="cashAppDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											<td class="fontMin" align="center"><ezf:label name="xxCmntTxt_A1" ezfName="xxCmntTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
									 	</tr>
									</ezf:row>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<table border="0" cellpadding="1" cellspacing="0">
					<col width="72">
					<col width="72">
					<col width="410">
					<col width="72">
					<col width="72">
					<col width="72">
					<col width="20">
					<col width="72">
					<col width="72">
					<col width="72">
					<col width="12">
					<tr>
						<td><ezf:inputButton name="btn11" value="Add (F11)" otherAttr=" id=\"btn11\""/></td>
						<td><!--<input type="button" class="pBtn6" value="Add 5" onclick="sendServer(this)" name="AddFiveLine" id="AddFiveLineId">--></td>
						<td><!--<input type="button" class="pBtn6" value="Add 10" onclick="sendServer(this)" name="AddTenLine" id="AddTenLineId">--></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><!--<input type="button" class="pBtn10" value="Search TRX" name="OpenWin_Search" onclick="sendServer(this)" id="OpenWinSearchId">--></td>
						<td><!--<input type="button" class="pBtn10" value="Delete" name="DeleteLines" onclick="sendServer(this)" id="DeleteLinesId">--></td>
						<td><ezf:inputButton name="OpenWin_New" value="On Acc & Adj" htmlClass="pBtn10" otherAttr=" id=\"OpenWinNewId\""/></td>
					</tr>
				</table>

<%-- ######################################## FOOTER ######################################## --%>
				<table border="0" cellpadding="1" cellspacing="1" align="center">
					<col width="">
					<tr><td></td></tr>
				</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<%-- ###SCRIPT --%>
<!-- START 2022/01/06 G.Delgado [QC#59329, ADD]-->
<!-- **** Scroll **** -->
<ezf:inputHidden name="xxListNum_LY" ezfName="xxListNum_LY" value="0" otherAttr=" id=\"xxListNum_LY\""/>

<script type="text/javascript">
    setScroll();

    /**
     * Save scroll position.
     */
    function synchroScroll(id, ary) {
        if (id == 'detailTBL') {
            document.getElementById('xxListNum_LY').value = document.getElementById(id).scrollTop;
        }
        synchroScrollTop(id, ary);
    }

    /**
     * Set before event scroll position.
     */
    function setScroll() {
        var yVal = document.getElementById('xxListNum_LY').value;

        var leftTbl = document.getElementById('leftTBL');
        var detailTbl = document.getElementById('detailTBL');

        if (yVal < detailTbl.scrollHeight) {
            detailTbl.scrollTop = yVal;
            leftTbl.scrollTop = yVal;
        }
    }
</script>
<!-- **** Scroll **** -->
<!-- END 2022/01/06 G.Delgado [QC#59329, ADD]-->

<script type="text/javascript">

	setKeyDownHandler();

	function setKeyDownHandler() {

		if( window.addEventListener ) {
		    window.addEventListener("keyup", emulateFuncKeyDown, false);
		} else if( document.attachEvent ) {
		    document.attachEvent("onkeyup", emulateFuncKeyDown);
		} else {
		    document.onkeyup = emulateFuncKeyDown;
		}
	}

	function emulateFuncKeyDown() {

		var code = event.keyCode;
		//alert("keyCode:["+event.keyCode+"]");

		if(event.keyCode >= 122) {
			event.keyCode = null;
			event.returnValue = false;
		}

		switch(code) {
		// F11
		case 122:
			//sendServer("Line_Config_Add");
			emulateOnClickEvent("btn11");
			break;
		default:
			break;
		}
		return;
	}

	function emulateOnClickEvent(elementId) {

		var elem = document.getElementById(elementId);
		if( /*@cc_on ! @*/ false ) {
			elem.fireEvent("onclick");
		} else {
			var evt = document.createEvent("MouseEvents");
			evt.initEvent("click", false, true);
			elem.dispatchEvent(evt);
		}
	}


</script>


<%-- **** Task specific area ends here **** --%>
