<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180912084637 --%>
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
			<input type="hidden" name="pageID" value="NPAL1540Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Manual ASN Entry Screen">


<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
					<table border="0" cellpadding="1" cellspacing="0">
						<col width="5">
						<col width="90">
						<col width="150">
						<col width="60">
						<col width="50">
						<col width="80">
						<col width="120">
						<col width="5">
						<col width="100">
						<col width="60">
						<col width="80">
						<col width="80">
						<tr>
							<td>&nbsp;</td>
							<td class="stab">ASN SO#</td>
							<td><ezf:inputText name="asnSoNum" ezfName="asnSoNum" value="XXXXXXXXX1XXX" otherAttr=" size=\"20\" maxlength=\"13\" ezftoupper=\"\""/></td>
							<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
							<td>&nbsp;</td>
							<td class="stab">Process Status</td>
							<td><ezf:inputText name="xxScrItem54Txt" ezfName="xxScrItem54Txt" value="X:XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"20\" maxlength=\"54\""/></td>
							<td>&nbsp;</td>
							<td><ezf:inputButton name="MoveTo_AsnErrorCorrection" value="Error Correction" htmlClass="pBtn10" /></td>
							<td>&nbsp;</td>
							<td class="stab">Source Type</td>
							<td><ezf:inputText name="xxScrItem7Txt" ezfName="xxScrItem7Txt" value="XXXXXX" otherAttr=" size=\"6\" maxlength=\"7\""/></td>
						</tr>
					</table>
					<hr>
					<table border="0" cellpadding="1" cellspacing="0">
						<col width="5">
						<col width="90">
						<col width="60">
						<col width="50">
						<col width="50">
						<col width="33">
						<col width="73">
						<col width="455">
						<col width="15">
						<col width="80">
						<col width="150">
						<tr>
							<td>&nbsp;</td>
							<td class="stab">PO#/Cmbn SO#</td>
							<td><ezf:inputText name="poOrdNum" ezfName="poOrdNum" value="XXXXXXXX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
							<td><ezf:inputButton name="ApplyPO" value="Apply" htmlClass="pBtn3" /></td>
							<td><ezf:inputButton name="MoveTo_PoEntry" value="PO Entry" htmlClass="pBtn3" /></td>
							<td>&nbsp;</td>
							<td class="stab">Supplier Site</td>
							<td>
								<ezf:inputText name="vndCd" ezfName="vndCd" value="XXXXXXXXX1XXXXXXXXX2" otherAttr=" size=\"15\" maxlength=\"20\""/>
								<ezf:inputText name="dplyVndNm" ezfName="dplyVndNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"46\" maxlength=\"320\""/>
							</td>
							<td>&nbsp;</td>
							<td class="stab">Delivery Order#</td>
							<td><ezf:inputText name="shipFromSoNum" ezfName="shipFromSoNum" value="XXXXXXXXX1XXX" otherAttr=" size=\"20\" maxlength=\"13\" ezftoupper=\"\""/></td>
						</tr>
					</table>

					<table border="0" cellpadding="1" cellspacing="0">
						 <col width="5">
						<col width="90">
						<col width="150">
						 <col width="50">
						<col width="70">
						<col width="150">
						<col width="10">
						<col width="70">
						<col width="150">
						<col width="23">
						<col width="79">
						<col width="110">
						<tr>
							<td>&nbsp;</td>
							<td class="stab"><ezf:anchor name="xxLinkAncr" ezfName="xxLinkAncr" ezfEmulateBtn="1" ezfGuard="OpenWin_Carrier" >Carrier</ezf:anchor></td>
							<td><ezf:inputText name="carrCd" ezfName="carrCd" value="XXXXXXXXX1XXXXXXXXX2" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
							<td>&nbsp;</td>
							<td class="stab">BOL#</td>
							<td><ezf:inputText name="asnBolNum" ezfName="asnBolNum" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td>&nbsp;</td>
							<td class="stab">Total Weight</td>
							<td><ezf:inputText name="asnTotShipWt" ezfName="asnTotShipWt" value="999999999.99" otherAttr=" size=\"20\" maxlength=\"14\" style=\"text-align:right;\""/></td>
							<td>&nbsp;</td>
							<td class="stab">Ship Date</td>
							<td >
								<ezf:inputText name="shipDt" ezfName="shipDt" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'shipDt', 4 );">
							</td>
						</tr>
					</table>

					<table border="0" cellpadding="1" cellspacing="0">
						<col width="5">
						<col width="90">
						<col width="150">
						<col width="50">
						<col width="70">
						<col width="150">
						<col width="10">
						<col width="70">
						<col width="150">
						<col width="23">
						<col width="78">
						<col width="110">
						<tr>
							<td>&nbsp;</td>
							<td class="stab">Supplier WH</td>
							<td><ezf:inputText name="vndInvtyLocCd" ezfName="vndInvtyLocCd" value="XXXXXXXXX1XXXXXXXXX2" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
							<td>&nbsp;</td>
							<td class="stab">PRO#</td>
							<td><ezf:inputText name="asnProNum" ezfName="asnProNum" value="XXXXXXXXX1XXXXXXXXX2XXXXX" otherAttr=" size=\"30\" maxlength=\"25\" ezftoupper=\"\""/></td>
							<td>&nbsp;</td>
							<td class="stab">Total Freight</td>
							<td><ezf:inputText name="asnTotFrtAmt" ezfName="asnTotFrtAmt" value="99,999,999,999,999,999.99" otherAttr=" size=\"20\" maxlength=\"25\" style=\"text-align:right;\""/></td>
							<td>&nbsp;</td>
							<td class="stab">PDD Date</td>
							<td >
								<ezf:inputText name="asnPlnDelyDt" ezfName="asnPlnDelyDt" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'asnPlnDelyDt', 4 );">
							</td>
						</tr>
					</table>

<%-- ######################################## DETAIL ######################################## --%>
                    <hr>
								<table width="100%" border = "0">
									<col width="150">
									<col width="10">
									<col width="70">
									<col width="30">
									<col width="100">
									<col width="70">
									<col width="70">
									<col width="">
									<tr>
  									<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="AddAllLine" value="Add All Line" htmlClass="pBtn6" /></td>
										<td>&nbsp;</td>
										<td class="stab">PO/Cmbn SO Line#</td>
										<td><ezf:inputText name="dispPoDtlLineNum" ezfName="dispPoDtlLineNum" value="XXXXXXXX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="AddLine" value="Add(F11)" htmlClass="pBtn6" /></td>
										<td align="right">
                                        <ezf:skip>
                                            <table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 30px;">
                                                <colgroup>
                                                    <col align="center" width="54">
                                                    <col align="right" width="40">
                                                    <col align="center" width="16">
                                                    <col align="right" width="40">
                                                    <col align="center" width="16">
                                                    <col align="right" width="40">
                                                    <col width="10">
                                                    <col>
                                                    <col width="1">
                                                    <col>
                                                </colgroup>
                                                <tbody>
                                                    <tr>
                                                        <td class="stab"><label>Showing</label></td>
                                                        <td class="pOut"><label ezfout name="xxPageShowFromNum_A" ezfname="xxPageShowFromNum_A">1</label></td>
                                                        <td class="stab"><label>to</label></td>
                                                        <td class="pOut"><label ezfout name="xxPageShowToNum_A" ezfname="xxPageShowToNum_A">40</label></td>
                                                        <td class="stab"><label>of</label></td>
                                                        <td class="pOut"><label ezfout name="xxPageShowOfNum_A" ezfname="xxPageShowOfNum_A">999</label></td>
                                                        <td>&nbsp;</td>
                                                        <td><input onclick="tablePagenation(this.name, 'A')" disabled id="btnPrev" class="pBtn3" type="button" value="Prev" name="PagePrev"></td>
                                                        <td></td>
                                                        <td><input onclick="tablePagenation(this.name, 'A')" id="btnNext" class="pBtn3" type="button" value="Next" name="PageNext"></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </ezf:skip>
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td>
														<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
														<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
														<jsp:param name="TableNm"     value="A" />
														<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
														<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
														<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
														</jsp:include>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
					<table border="0" cellpadding="1" cellspacing="0">
						<col width="">
						<tr>
								<td>
									<div id="parentBoxA">
										<div style="float:left; display:block"><!-- left table -->
											<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
											<div id="leftTbl" style="float:left; display:block; height:360px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
										</div><!-- left table -->
										<div style="float:left"><!-- right table -->
											<div id="rightTblHead" style="width:1101px; display:block; overflow:hidden; margin:0px;padding:0px;" >
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="1880px" style="margin-right:20px">
																<col width="40"  align="center" >
																<col width="90"  align="center" >
																<col width="90"  align="center" >
																<col width="70"  align="center" >
																<col width="90"  align="center" >
																<col width="160" align="center" >
																<col width="140" align="center" >
																<col width="160" align="center" >
																<col width="120" align="center" >
																<col width="120" align="center" >
																<col width="120" align="center" >
																<col width="90" align="center" >
																<col width="100" align="center" >
																<col width="160" align="center" >
																<col width="160" align="center" >
																<col width="200" align="center" >
													<tr height="35">
																	<td class="pClothBs" id="A3H0">&nbsp;</td>
																	<td class="pClothBs" id="A3H1">EDI PO#</td>
																	<td class="pClothBs" id="A3H2">EDI PO Line#</td>
																	<td class="pClothBs" id="A3H3">PO#<br>Cmbn SO#</td>
																	<td class="pClothBs" id="A3H4">PO/Cmbn SO Line#</td>
																	<td class="pClothBs" id="A3H5">Supplier Item Code</td>
																	<td class="pClothBs" id="A3H6">Item Code</td>
																	<td class="pClothBs" id="A3H7">Item Description</td>
																	<td class="pClothBs" id="A3H8">Request Qty</td>
																	<td class="pClothBs" id="A3H9">PO In-trnst Qty</td>
																	<td class="pClothBs" id="A3H10">PO Rcvd Qty<br>SO Ship Qty</td>
																	<td class="pClothBs" id="A3H11">Ship Qty</td>
																	<td class="pClothBs" id="A3H12">Weight</td>
																	<td class="pClothBs" id="A3H13">Freight Amt</td>
																	<td class="pClothBs" id="A3H14">Serial Number</td>
																	<td class="pClothBs" id="A3H15">Supplier Issue Order Number</td>
													</tr>
												</table>
											</div><!-- rightTblHead -->
   											<div id="rightTbl" style="width:1118px; height:360px; display:block; overflow:scroll; margin:0px; padding:0px;" >
      											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1880px">
																<col width="40" align="center">
																<col width="90">
																<col width="90">
																<col width="70">
																<col width="90">
																<col width="160">
																<col width="140">
																<col width="160">
																<col width="120" align="right">
																<col width="120" align="right">
																<col width="120" align="right">
																<col width="90" align="right">
																<col width="100" align="right">
																<col width="160" align="right">
																<col width="160">
																<col width="200">
															<ezf:row ezfHyo="A">
															<tr height="25px">
																		<td id="AH0"><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td id="AH1"><ezf:inputText name="ediPoOrdNum_A1" ezfName="ediPoOrdNum_A1" value="----+----1----+----2----+----3----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"35\""/></td>
																		<td id="AH2"><ezf:label name="ediPoOrdDtlLineNum_A1" ezfName="ediPoOrdDtlLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td id="AH3"><ezf:label name="poOrdNum_A1" ezfName="poOrdNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td id="AH4"><ezf:label name="dispPoDtlLineNum_A1" ezfName="dispPoDtlLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td id="AH5"><ezf:inputText name="asnMdseCd_A1" ezfName="asnMdseCd_A1" value="----+----1----+----2----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"25\" ezftoupper=\"\""/></td>
																		<td id="AH6"><ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="----+----1----+-" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\""/></td>
																		<td id="AH7"><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="----+----1----+----2----+----3----+----4----+----5----+----6----+----7----+----8----+----9----+----0----+----1----+----2----+----3----+----4----+----5----+----6----+----7----+----8----+----9----+----0----+----1----+----2----+----3----+----4----+----5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"250\""/></td>
																		<td id="AH8"><ezf:label name="poQty_A1" ezfName="poQty_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td id="AH9"><ezf:label name="xxQty10Num_A1" ezfName="xxQty10Num_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td id="AH10"><ezf:label name="poRcvQty_A1" ezfName="poRcvQty_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td id="AH11"><ezf:inputText name="asnQty_A1" ezfName="asnQty_A1" value="9999999999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"text-align:right;\""/></td>
																		<td id="AH12"><ezf:inputText name="asnTotShipWt_A1" ezfName="asnTotShipWt_A1" value="9999999999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"12\" style=\"text-align:right;\""/></td>
																		<td id="AH13"><ezf:inputText name="asnTotFrtAmt_A1" ezfName="asnTotFrtAmt_A1" value="99,999,999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"25\" style=\"text-align:right;\""/></td>
																		<td id="AH14">
																			<ezf:inputText name="xxScrItem8Txt_A1" ezfName="xxScrItem8Txt_A1" value="No Entry" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"8\""/>
																			<ezf:inputButton name="OpenWin_SerEntry" value="Serial" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" />
																		</td>
																		<td id="AH15"><ezf:inputText name="xxScrItem19Txt_A1" ezfName="xxScrItem19Txt_A1" value="----+----1----+----" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"19\""/></td>
															</tr>
															</ezf:row>
												</table>
											</div><!-- rightTbl -->
										</div> <!-- right table -->
									</div> <!-- parentBoxA -->
								</td>
						</tr>
					</table>

								<table width="100%">
									<col width="60">
									<col width="5">
									<col width="60">
									<col width="5">
									<col width="60">
									<col width="5">
									<col width="60">
									<col width="">
									<tr>
										<td><ezf:inputButton name="CheckAll" value="Check All" htmlClass="pBtn8" /></td>
										<td></td>
										<td><ezf:inputButton name="UnCheckAll" value="Un Check All" htmlClass="pBtn8" /></td>
										<td></td>
										<td><ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn8" /></td>
										<td></td>
										<td><ezf:inputButton name="Attachments" value="Attachments" htmlClass="pBtn8" /></td>
										<td></td>
									</tr>
								</table>
<%-- ######################################## FOOTER ######################################## --%>

				</div> <!-- pTab_BODY -->
			</td>
		</tr>
	</table>
</center>
<div style="display:none;">
	<ezf:inputButton name="btn11" value="btn11" otherAttr=" id=\"btn11\""/>
</div>
<%-- ###SCRIPT --%>
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

		if(event.keyCode == 122 ) {
			event.keyCode = null;
			event.returnValue = false;
		}

		switch(code) {
		// F11
		case 122:
			//sendServer("Line_Add");
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
<script type="text/javascript" defer>
  S21TableUI.initialize("parentBoxA", "AHEAD", "A", 0,  false, true);
</script>


<%-- **** Task specific area ends here **** --%>
