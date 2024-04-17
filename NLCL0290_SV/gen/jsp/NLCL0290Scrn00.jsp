<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230614093206 --%>
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
			<input type="hidden" name="pageID" value="NLCL0290Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Inventory Adjustment Form">

<center>
    <table cellSpacing="0" cellPadding="0" width="100%" border="0">
        <tbody>
            <tr>
                <td>
                    <%-- ********************** --%>
                    <%-- *** Upper Tab Area *** --%>
                    <%-- ********************** --%>
                    <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                    <ezf:skip>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
                            <tr>
                                <td width="1070px" height="28px" valign="bottom">
                                    <table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
                                        <tr title="Inventory Adjustment Form">
                                            <td width="107px" align="center" class="same">Invty Adjust</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </ezf:skip>

                    <div class="pTab_BODY">
                        <!-- ################################################## Header - [START] ################################################## -->
						<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
							<col width=""  align="left">
							<tr valign="top">
								<td>
									<table border="0" cellpadding="1" cellspacing="1" >
										<col width="150">
										<col width="300">
										<!-- 1 -->
										<tr height="23">
											<td class="stab">Adjustment Transaction Type</td>
											<td>
         								<ezf:select name="adjTrxTpCd_H" ezfName="adjTrxTpCd_H" ezfCodeName="adjTrxTpCd_LC" ezfDispName="adjTrxTpDescTxt_LD" otherAttr=" style=\"width:300px;\""/>
											</td>
										</tr>
										<tr height="23">
											<td class="stab"><ezf:anchor name="rtlWhCd_HL" ezfName="rtlWhCd_HL" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" >Warehouse</ezf:anchor></td>
											<td class="stab">
											    <ezf:inputText name="rtlWhCd_H" ezfName="rtlWhCd_H" value="A01" otherAttr=" size=\"6\" maxlength=\"20\" ezftoupper=\"\""/>
												<ezf:inputButton name="Search_RtlWHInfo" value=">>" htmlClass="pBtn0" />
												<ezf:inputText name="rtlWhNm_H" ezfName="rtlWhNm_H" value="A01 MONROE" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/>
											</td>
										</tr>
										<tr height="23">
											<td>&nbsp;</td>
											<td colspan="2" class="stab" align="right">
												<ezf:inputButton name="New" value="New" htmlClass="pBtn6" />&nbsp;&nbsp;
											</td>
										</tr>
									</table>
								</td>
								<td width='10'></td>
								<td>
									<table border="0" cellpadding="1" cellspacing="1" style="border-left:black 1px solid">
										<col width="5">
										<col width="100">
										<col width="145">
										<tr height="23">
											<td>&nbsp;</td>
											<td class="stab">Document Number</td>
											<td align="right">
												<ezf:inputText name="invtyOrdNum_H" ezfName="invtyOrdNum_H" value="IM037610" otherAttr=" size=\"20\" maxlength=\"45\" ezftoupper=\"\""/>
											</td>
										</tr>
										<tr height="23">
											<td colspan="3">&nbsp;</td>
										</tr>
										<tr height="23">
											<td>&nbsp;</td>
											<td colspan="3" class="stab" align="right">
												<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
                        <!-- ################################################## Header - [E N D] ################################################## -->

                        <hr>

                        <!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
                        <table style="MARGIN-LEFT: 5px; width: 1113px;">
                            <colgroup>
                                <col align="left" width="">
                                <col align="right" width="">
                            </colgroup>
                            <tbody>
                                <tr>
                                    <td>
                                        <table cellSpacing="1" cellPadding="1" border="0">
                                                <tr>
                                                    <td>
														<ezf:inputButton name="Add_Line" value="Add (F11)" htmlClass="pBtn6" />
														<ezf:inputButton name="Delete_Line" value="Delete" htmlClass="pBtn6" />
													<td></td>
													<td></td>
													<td></td>
													<td></td>
                                                    </td>
													<td class="stab"><ezf:anchor name="svcConfigMstrPk_HL" ezfName="svcConfigMstrPk_HL" ezfEmulateBtn="1" ezfGuard="OpenWin_ConfigID" >Select From Config</ezf:anchor></td>
													<td></td>
													<td></td>
													<td><ezf:inputText name="svcConfigMstrPk" ezfName="svcConfigMstrPk" value="----+----1----+" otherAttr=" size=\"15\" maxlength=\"28\""/></td>
													<td></td>
													<td></td>
													<td><ezf:inputButton name="Add_Config" value="Add Config" htmlClass="pBtn6" />
                                                </tr>
                                        </table>
                                    </td>
                                    <td>
										<div align="right">
											<ezf:skip>
												<table border="0" cellpadding="1" cellspacing="0">
													<col >
													<col width="40" align="right">
													<col width="16" align="center">
													<col width="40" align="right">
													<col width="16" align="center">
													<col width="40" align="right">
													<col width="10">
													<col width="0">
													<col width="1">
													<col width="0">
													<tr>
														<td class="stab">Showing</td>
														<td class="pOut">1</td>
														<td class="stab">to</td>
														<td class="pOut">20</td>
														<td class="stab">of</td>
														<td class="pOut">200</td>
														<td>&nbsp;</td>
														<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
														<td></td>
														<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
														<td></td>
													</tr>
												</table>
											</ezf:skip>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="A" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
											</jsp:include>
										</div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <!-- ######################################## TO (COMMON)PAGENATION ###################################### --!>

                        <!-- ################################################## Detail   - [START] ################################################## -->
                        <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td align="left" valign="top">
                                    <%-- ******************************* --%>
                                    <%-- *** Left Table Area(Header) *** --%>
                                    <%-- ******************************* --%>
                                    <table style="table-layout:fixed; margin-left: 5px;" border="1" cellpadding="1" cellspacing="0" height="35">
                                        <col width="30" align="center"><!-- Check Box -->
                                        <col width="188" align="center"><!-- Item Number -->
                                        <col width="140" align="center"><!-- Item Description -->
                                        <col width="80" align="center"><!-- Source Sub WH -->
                                        <col width="80" align="center"><!-- Destination Sub WH -->
                                        <tr>
                                            <td class="pClothBs">&nbsp;</td>
                                            <td class="pClothBs">Item Number</td>
                                            <td class="pClothBs">Item Description</td>
                                            <td class="pClothBs">Source Sub WH</td>
                                            <td class="pClothBs">Destination Sub WH</td>
                                        </tr>
                                    </table>
                                    <%-- ******************************* --%>
                                    <%-- *** Left Table Area(Detail) *** --%>
                                    <%-- ******************************* --%>
                                    <div id="LeftTBL" style="overflow-y:hidden; height:343; overflow-x:hidden; width:; margin-left: 5px;"
                                        onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
                                        <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Left">
	                                        <col width="30" align="center"><!-- Check Box -->
	                                        <col width="188" align="center"><!-- Item Number -->
	                                        <col width="140" align="center"><!-- Item Description -->
	                                        <col width="80" align="center"><!-- Source Sub WH -->
	                                        <col width="80" align="center"><!-- Destination Sub WH -->
                                            <ezf:row ezfHyo="A">
                                                <tr height="25" id="A_leftTBLRow#{EZF_ROW_NUMBER}">
													<!-- Check Box -->
													<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- Item Number -->
													<td class="stab">
														<ezf:inputButton name="OpenWin_Item" value="Item" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
														<ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="4234A003AA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"16\" ezftoupper=\"\""/>
														<ezf:inputButton name="Search_Item" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
													</td>
													<!-- Item Description -->
													<td class="stab">
														<ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="GPR-4 TONER BLACK/IR5000/6000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"250\""/>
													</td>
													<!-- Source Sub WH -->
													<td>
														<ezf:select name="fromRtlSwhCd_A" ezfName="fromRtlSwhCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="rtlSwhCd_LC" ezfDispName="rtlSwhCd_LD" otherAttr=" style=\"width:75px;\""/>
													</td>
													<!-- Destination Sub WH -->
													<td>
														<ezf:select name="toRtlSwhCd_A" ezfName="toRtlSwhCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="rtlSwhCd_LC" ezfDispName="rtlSwhCd_LD" otherAttr=" style=\"width:75px;\""/>
													</td>
												</tr>
											</ezf:row>
                                        </table>
                                    </div>
                                </td>
                                <td valign="top">
                                    <%-- ******************************** --%>
                                    <%-- *** Right Table Area(Header) *** --%>
                                    <%-- ******************************** --%>
                                    <div id="topRightTBL" style="overflow-y:none; overflow-x:hidden; width:563;">
                                        <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" height="35" width="">
                                            <col width="80" align="center"><!-- Item Quantity -->
                                            <col width="120" align="center"><!-- Stock Status -->
                                            <col width="120" align="center"><!-- To Stock Status -->
                                            <col width="200" align="center"><!-- Reason Code -->
                                            <col width="120" align="center"><!-- Account -->
                                            <col width="230"  align="center"><!-- Comments -->
                                            <col width="135" align="center"><!-- Serial -->
                                            <col width="100" align="center"><!-- Config PK -->
                                            <col width="100"  align="center"><!-- Machine Type -->
                                            <col width="100" align="center"><!-- Line Status -->
                                            <col width="100" align="center"><!-- Transaction Cost -->
                                            <col width="115" align="center"><!-- Document Number -->
                                            <col width="140"  align="center"><!-- Date&Time -->
                                            <col width="90"  align="center"><!-- Available Invty Qty -->
                                            <tr>
                                                <td class="pClothBs">Item Quantity</td>
                                                <td class="pClothBs">Stock Status</td>
                                                <td class="pClothBs">To Stock Status</td>
                                                <td class="pClothBs">Reason Code</td>
                                                <td class="pClothBs">Account</td>
                                                <td class="pClothBs">Comments</td>
                                                <td class="pClothBs">Serial</td>
                                                <td class="pClothBs">Config ID</td>
                                                <td class="pClothBs">Machine Type</td>
                                                <td class="pClothBs">Line Status</td>
                                                <td class="pClothBs">Transaction Cost</td>
                                                <td class="pClothBs">Document Number</td>
                                                <td class="pClothBs">Date & Time</td>
                                                <td class="pClothBs">Available<br>Inventory Qty</td>
                                            </tr>
                                        </table>
                                    </div>
                                    <%-- ******************************** --%>
                                    <%-- *** Right Table Area(Detail) *** --%>
                                    <%-- ******************************** --%>
                                    <div id="RightTBL" style="overflow-y:scroll; height:360; overflow-x:scroll; width:580;" 
                                        onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'topRightTBL' ) );">
                                        <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Right" width="700">
                                            <col width="80" align="left"><!-- Item Quantity -->
                                            <col width="120" align="left"><!-- Stock Status -->
                                            <col width="120" align="left"><!-- To Stock Status -->
                                            <col width="200" align="left"><!-- Reason Code -->
                                            <col width="120" align="left"><!-- Account -->
                                            <col width="230"  align="left"><!-- Comments -->
                                            <col width="135" align="left"><!-- Serial -->
                                            <col width="100" align="left"><!-- Config PK -->
                                            <col width="100"  align="center"><!-- Machine Type -->
                                            <col width="100" align="left"><!-- Line Status -->
                                            <col width="100" align="right"><!-- Transaction Cost -->
                                            <col width="115" align="center"><!-- Document Number -->
                                            <col width="140"  align="left"><!-- Date&Time -->
                                            <col width="90"  align="left"><!-- Available Invty Qty -->
                                            <ezf:row ezfHyo="A">
                                                <tr height="25" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
													<!-- Item Quantity -->
													<td><ezf:inputText name="ordQty_A" ezfName="ordQty_A" value="9,999,999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"text-align:right\""/></td>
													<!-- Stock Status -->
													<td>
														<ezf:select name="stkStsCd_A" ezfName="stkStsCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="stkStsCd_LC" ezfDispName="stkStsDescTxt_LD" otherAttr=" style=\"width:110px;\""/>
													</td>
													<!-- To Stock Status -->
													<td>
														<ezf:select name="toStkStsCd_A" ezfName="toStkStsCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="stkStsCd_LC" ezfDispName="stkStsDescTxt_LD" otherAttr=" style=\"width:110px;\""/>
													</td>
													<!-- Reason Code -->
													<td>
														<ezf:select name="adjCatgCd_A" ezfName="adjCatgCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="adjCatgCd_LC" ezfDispName="adjCatgDescTxt_LD" otherEvent1=" onchange=\"sendServer('On_Change_Reason', {EZF_ROW_NUMBER})\"" otherAttr=" style=\"width:190;\""/>
													</td>
													<!-- Account -->
													<td class="stab">
														<ezf:inputText name="xxScrItem130Txt_A" ezfName="xxScrItem130Txt_A" value="51101002" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/>
                            				     		<ezf:inputButton name="OpenWin_Account_Detail" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" />
													</td>
													<!-- Comments -->
													<td class="stab">
														<ezf:inputText name="invtyOrdLineCmntTxt_A" ezfName="invtyOrdLineCmntTxt_A" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"31\" maxlength=\"1000\""/>
													</td>
													<!-- Serial -->
													<td class="stab">
														<ezf:inputText name="serNum_A" ezfName="serNum_A" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
													<!--
													<input type="button" class="pBtn1" value="…" ezfhyo="A" name="OpenWin_Serial" onclick="sendServer(this)">
													-->
													</td>
													<!-- Config PK -->
													<td><ezf:inputText name="svcConfigMstrPk_A" ezfName="svcConfigMstrPk_A" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"28\""/></td>
													<!-- Machine Type -->
													<td><ezf:label name="svcMachTpDescTxt_A" ezfName="svcMachTpDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- Line Status -->
													<td><ezf:inputText name="invtyOrdStsDescTxt_A" ezfName="invtyOrdStsDescTxt_A" value="Open" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\""/></td>
													<!-- Transaction Cost -->
													<td><ezf:inputText name="invtyOrdLineCostAmt_A" ezfName="invtyOrdLineCostAmt_A" value="9,999,999.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"21\" style=\"text-align:right\""/></td>
													<!-- Document Number -->
													<td><ezf:label name="invtyOrdNum_A" ezfName="invtyOrdNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- Date&Time -->
													<td><ezf:label name="xxTsDsp19Txt_A" ezfName="xxTsDsp19Txt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- Available Invty Qty -->
													<td><ezf:inputText name="invtyAvalQty_A" ezfName="invtyAvalQty_A" value="9,999,999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td class="pAuditInfo">
														<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" />
														<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" />
														<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" />
														<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" />
														<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" />
													</td>
                                                </tr>
                                            </ezf:row>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        </table>
                        <!-- ################################################## Search Result   - [E N D] ################################################## -->
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
				<%-- ######################################## FOOTER ######################################## --%>
	<div align="right">
		<table border="0" width="100%">
			<tr height="20">
				<td align="right">
					<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TempleteFileForUpload" >Import File</ezf:anchor>
					<ezf:inputFile name="xxFileData_UP" ezfName="xxFileData_UP" otherAttr=" size=\"50\" maxlength=\"9999\" ezftoupper=\"\""/>
					<ezf:inputButton name="Import" value="Import" htmlClass="pBtn6" />
					<ezf:inputButton name="Attachments" value="Attachments" htmlClass="pBtn6" />
				</td>
				<td width="5">&nbsp;</td>
			</tr>
		</table>
	</div>
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


<%-- **** Task specific area ends here **** --%>
