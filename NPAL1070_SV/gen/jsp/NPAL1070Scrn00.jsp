<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230510163937 --%>
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
			<input type="hidden" name="pageID" value="NPAL1070Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Min-Max Planning Entry">

			<%-- ######################################## Header   - [START] #################################### --%>
			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<li class="pTab_ON"><a href="./NPAL1070Scrn00.html">Plan Entry</a></li>
								</ul>
							</div>
							<%-- include S21BusinessProcessTAB --%>
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
							</ezf:skip>

							<div class="pTab_BODY">
								<table border="0" >
									<col width="800" align="left">
									<col width="300">
									<tr valign="top">
										<%-- ================ Search Area ================ --%>
										<td>
                                            <table border="0" cellpadding="0" cellspacing="0" style="margin-left: 5px;">
												<tr>
													<col width="90px"  align="left">
													<col width="80px"  align="left">
													<col width="30px"  align="center">
													<col width="120px" align="left">
													<col width="50px"  align="left">
													<col width="100px"  align="left">
													<col width="95px"  align="left">
												</tr>
												<tr height="20">
													<%-- ---------- Plan Name ---------- --%>
													<td class="stab">Plan Name</td>
													<td colspan="3">
														<ezf:inputText name="mrpPlnNm" ezfName="mrpPlnNm" value="XXXXXXXXXXXXXXXXXXXXXXXXX" otherAttr=" maxlength=\"25\" style=\"width:295px;\" tabindex=\"1\" ezftoupper=\"\""/>
													</td>
													<td></td>
													<%-- ---------- Enabled Item Only ---------- --%>
													<td class="stab">Enabled Item Only</td>
													<td>
														<ezf:inputCheckBox name="mrpEnblFlg" ezfName="mrpEnblFlg" value="Y" otherAttr=" tabindex=\"2\""/>
													</td>
													<%-- ---------- Search ---------- --%>
													<td valign="bottom">
														<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" tabindex=\"3\""/>
													</td>
												</tr>
												<tr height="20">
													<%-- ----- Include Entered Sales Order ----- --%>
													<td class="stab">Include Entered Sales Order</td>
													<td>
														<ezf:inputCheckBox name="calcOrdProcCd_C1" ezfName="calcOrdProcCd_C1" value="1" otherAttr=" tabindex=\"4\""/>
													</td>
												</tr>
											</table>
										</td>
										<td>
                                            <table border="0" cellpadding="0" cellspacing="0" style="margin-left: 5px;">
												<col align="center" width="30">
												<col align="center" width="20">
												<col align="center" width="80">
												<col align="center" width="80">
												<col align="center" width="80">
												<col align="center" width="80">
												<col align="center" width="80">
												<tr>
													<td class="stab">
														<div> Daily </div>
														<ezf:inputCheckBox name="rplshDlyFlg" ezfName="rplshDlyFlg" value="Y" otherAttr=" tabindex=\"5\""/>
													</td>
													<td class="stab" style="border-right:1 solid gray;" >&nbsp;</td>
													<td class="stab">
														<div> Mon </div>
														<ezf:inputCheckBox name="rplshMonFlg" ezfName="rplshMonFlg" value="Y" otherAttr=" tabindex=\"6\""/>
													</td>
													<td class="stab">
														<div> Tue </div>
														<ezf:inputCheckBox name="rplshTueFlg" ezfName="rplshTueFlg" value="Y" otherAttr=" tabindex=\"7\""/>
													</td>
													<td class="stab">
														<div> Wed </div>
														<ezf:inputCheckBox name="rplshWedFlg" ezfName="rplshWedFlg" value="Y" otherAttr=" tabindex=\"8\""/>
													</td>
													<td class="stab">
														<div> Thu </div>
														<ezf:inputCheckBox name="rplshThuFlg" ezfName="rplshThuFlg" value="Y" otherAttr=" tabindex=\"9\""/>
													</td>
													<td class="stab">
														<div> Fri </div>
														<ezf:inputCheckBox name="rplshFriFlg" ezfName="rplshFriFlg" value="Y" otherAttr=" tabindex=\"10\""/>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							<td class="pAuditInfo">
								<ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" />
								<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" />
								<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" />
								<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" />
								<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" />
							</td>
							
						</td>
					</tr>
				</table>

				<hr>

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<div style="width: 98%;">
								<table border="0" >
									<col width="890"   align="left">
									<col width="100"   align="right">
									<tr valign="top">
										<%-- ================ Search Area ================ --%>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" style="margin-left: 5px;">
												<tr>
													<col width="90px"  align="left">
													<col width="80px"  align="left">
													<col width="30px"  align="center">
													<col width="120px" align="left">
													<col width="30px"  align="left">
													<col width="126px" align="left">
													<col width="105px"  align="left">
													<col width="30px"  align="center">
													<col width="10px"  align="left">
													<col width="10px"  align="left">
													<col width="130px" align=left>
													<col width="20px" align="left">
												</tr>
												<tr height="20">
													<%-- ---------- Warehouse ---------- --%>
													<td class="stab">
														<ezf:anchor name="xxLinkAncr" ezfName="xxLinkAncr" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" otherAttr=" tabindex=\"11\" id=\"xxLinkAncr\"">Warehouse</ezf:anchor>
													</td>
													<td class="stab">
														<ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="WWWWWW" otherAttr=" size=\"16\" maxlength=\"20\" tabindex=\"12\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputButton name="SetWarehouseName" ezfName="WhNm" value=">>" otherAttr=" tabindex=\"13\""/>
													 </td>
													<td class="stab">
														<ezf:inputText name="rtlWhNm_W1" ezfName="rtlWhNm_W1" value="XXXX1XXXX2XXXX3XXXX4" otherAttr=" size=\"20\" maxlength=\"25\" tabindex=\"14\""/>
													</td>
													<td>&nbsp;</td>
													<%-- ---------- Source Type ---------- --%>
													<td class="stab">Source Type</td>
													<td colspan="2">
														<ezf:select name="procrTpCd_SL" ezfName="procrTpCd_SL" ezfBlank="1" ezfCodeName="procrTpCd_PD" ezfDispName="procrTpDescTxt_PD" otherAttr=" style=\"width:150px;\" tabindex=\"23\""/>
													</td>
													<td>&nbsp;</td>
													<%-- ----- Include Entered Sales Order ----- --%>
													<td class="stab" colspan="2" style="width:140px;">Include Entered Sales Order</td>
													<td>
														<ezf:inputCheckBox name="calcOrdProcCd_C2" ezfName="calcOrdProcCd_C2" value="1" otherAttr=" tabindex=\"28\""/>
													</td>
												</tr>
												<tr height="20">
													<%-- ---------- Sub Warehouse ---------- --%>
													<td class="stab">
														<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SubWarehouse" otherAttr=" tabindex=\"15\"">Sub Warehouse</ezf:anchor>
													</td>
													<td class="stab">
														<ezf:inputText name="rtlSwhCd" ezfName="rtlSwhCd" value="WWW" otherAttr=" size=\"16\" maxlength=\"16\" tabindex=\"16\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputButton name="SetSubWarehouseName" ezfName="SwhNm" value=">>" otherAttr=" tabindex=\"17\""/>
													</td>
													<td class="stab">
														<ezf:inputText name="rtlSwhNm_S1" ezfName="rtlSwhNm_S1" value="XXXX1XXXX2XXXX3XXXX4" otherAttr=" size=\"20\" maxlength=\"25\" tabindex=\"18\""/>
													</td>
													<td>&nbsp;</td>
													<%-- ---------- Source Warehouse ---------- --%>
													<td class="stab">
														<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SourceWarehouse" otherAttr=" tabindex=\"24\"">Source Warehouse</ezf:anchor>
													</td>
													<td class="stab">
														<ezf:inputText name="srcRtlWhCd" ezfName="srcRtlWhCd" value="WWWWWW" otherAttr=" size=\"16\" maxlength=\"16\" tabindex=\"25\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputButton name="SetSourceWarehouseName" ezfName="SrcWhNm" value=">>" otherAttr=" tabindex=\"26\""/>
													</td>
													<td class="stab" colspan="3">
														<ezf:inputText name="rtlWhNm_W2" ezfName="rtlWhNm_W2" value="XXXX1XXXX2XXXX3XXXX4" otherAttr=" size=\"20\" maxlength=\"25\" tabindex=\"27\""/>
													</td>
												</tr>
												<tr height="20">
													<%-- ---------- Item Number ---------- --%>
													<td class="stab">
														<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Mdse" otherAttr=" tabindex=\"19\"">Item Number (*)</ezf:anchor>
													</td>
													<td class="stab">
														<ezf:inputText name="mdseCd" ezfName="mdseCd" value="0000A000AA" otherAttr=" size=\"16\" maxlength=\"15\" tabindex=\"20\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputButton name="SetItemDescription" ezfName="ItemNm" value=">>" otherAttr=" tabindex=\"21\""/>
													</td>
													<td class="stab">
														<ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="XXXXXXXX" otherAttr=" size=\"20\" maxlength=\"250\" tabindex=\"22\""/>
													</td>
													<td>&nbsp;</td>
													<%-- ---------- Source Sub Warehouse ---------- --%>
													<td class="stab">
														<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SourceSubWarehouse" otherAttr=" tabindex=\"29\"">Source Sub Warehouse</ezf:anchor>
													</td>
													<td class="stab">
														<ezf:inputText name="srcRtlSwhCd" ezfName="srcRtlSwhCd" value="WWW" otherAttr=" size=\"16\" maxlength=\"16\" tabindex=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputButton name="SetSourceSubWarehouseName" ezfName="SrcSwhNm" value=">>" otherAttr=" tabindex=\"31\""/>
													</td>
													<td class="stab" colspan="3">
														<ezf:inputText name="rtlSwhNm_S2" ezfName="rtlSwhNm_S2" value="XXXX1XXXX2XXXX3XXXX4" otherAttr=" size=\"20\" maxlength=\"25\" tabindex=\"32\""/>
													</td>
												</tr>
											</table>
										</td>
										<td>
                                            <table border="0" cellpadding="0" cellspacing="0" >
												<tr height="30">
													<%-- ---------- Apply to All ---------- --%>
													<td valign="bottom">
														<ezf:inputButton name="ApplyToAll" value="Apply to All" htmlClass="pBtn6" otherAttr=" tabindex=\"33\""/>
													</td>
												</tr>
												<tr height="30">
													<%-- ---------- Add ---------- --%>
													<td valign="bottom">
														<ezf:inputButton name="Add" value="Add" htmlClass="pBtn6" otherAttr=" tabindex=\"34\""/>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>

				<%-- ######################################## Header   - [END] #################################### --%>

				<hr>

				<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
				<table style="margin-left: 11px; width: 98%;" >
					<colgroup>
						<col align="left" width="200">
						<col align="right" width="900">
					</colgroup>
					<tbody>
						<tr>
							<td>
								<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
							</td>
							<td>
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
											<td class="pOut"><label ezfout name="xxPageShowFromNum" ezfname="xxPageShowFromNum">1</label></td>
											<td class="stab"><label>to</label></td>
											<td class="pOut"><label ezfout name="xxPageShowToNum" ezfname="xxPageShowToNum">20</label></td>
											<td class="stab"><label>of</label></td>
											<td class="pOut"><label ezfout name="xxPageShowOfNum" ezfname="xxPageShowOfNum">200</label></td>
											<td>&nbsp;</td>
											<td><input onclick="tablePagenation(this.name, 'A')" disabled id="btnPrev" class="pBtn3" type="button" value="Prev" name="PagePrev"></td>
											<td></td>
											<td><input onclick="tablePagenation(this.name, 'A')" id="btnNext" class="pBtn3" type="button" value="Next" name="PageNext"></td>
										</tr>
									</tbody>
								</table>
							</ezf:skip>
							<table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 38px;">
								<tbody>
									<tr align="right">
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
								</tbody>
							</table>
							</td>
						</tr>
					</tbody>
				</table>
				<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>
				
				<%-- ################################################## Detail   - [START] ################################################## --%>

				<div id="parentBoxA" style="width: 98%;" >

					<table border="0" cellpadding="1" cellspacing="0" width="100%">
						<tr>
							<td align="left" valign="top">
								<div style="float:left; display:block"><!-- left table -->
								<%-- ### A:Left Table HEAD--%>
									<div id='leftTblHead' style="display:block;">
									</div>
								<%-- ### A:Left Table BODY--%>
									<div id='leftTbl' style="float:left; display:block; height:283px; overflow:hidden; margin-left: 0px; padding:0px;">
									</div>
								</div><!-- left table -->
							</td>

							<td valign="top">
								<%-- ******************************** --%>
								<%-- *** Right Table Area(Header) *** --%>
								<%-- ******************************** --%>
								<div style="float:left"><!-- right table -->
								
									<div id="rightTblHead" style="width:1109px; display:block; overflow:hidden; margin:0px;padding:0px;">
								
										<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="1199px" style="margin-right:20px">
									
											<col align="center" width="24"><%-- Check box --%>
											<col align="center" width="100"><%-- WH Category --%>
											<col align="center" width="76"><%-- WH Code --%>
											<col align="center" width="116"><%-- WH Name --%>
											<col align="center" width="80"><%-- SWH Code --%>
											<col align="center" width="96"><%-- Item Number --%>
											<col align="center" width="160"><%-- Item Description  --%>
											<col align="center" width="40"><%-- Note  --%>
											<col align="center" width="40"><%-- MT --%>
											<col align="center" width="40"><%-- PC --%>
											<col align="center" width="120"><%-- Include Entered Sales Order --%>
											<col align="center" width="100"><%-- Min Qty --%>
											<col align="center" width="100"><%-- Max Qty --%>
											<col align="center" width="64"><%-- Enabled --%>
											<col align="center" width="160"><%-- Source Type --%>
											<col align="center" width="148"><%-- Source WH --%>
											<col align="center" width="120"><%-- Source WH Name --%>
											<col align="center" width="80"><%-- Source SWH --%>
											<col align="center" width="88"><%-- Supersession --%>
											<col align="center" width="200"><%-- Comment --%>
											<tr height="40">
												<td id="AH0" class="pClothBs">&nbsp;</td>
												<td id="AH1" class="pClothBs">WH Category</td>
												<td id="AH2" class="pClothBs">WH Code</td>
												<td id="AH3" class="pClothBs">WH Name</td>
												<td id="AH4" class="pClothBs">SWH Code</td>
												<td id="AH5" class="pClothBs">Item Number</td>
												<td id="AH6" class="pClothBs">Item Description</td>
												<td id="AH7" class="pClothBs">Note</td>
												<td id="AH8" class="pClothBs">MT</td>
												<td id="AH9" class="pClothBs">PC</td>
												<td id="AH10" class="pClothBs">Include Entered Sales Order</td>
												<td id="AH11" class="pClothBs">Min Qty</td>
												<td id="AH12" class="pClothBs">Max Qty</td>
												<td id="AH13" class="pClothBs">Enabled</td>
												<td id="AH14" class="pClothBs">Source Type</td>
												<td id="AH15" class="pClothBs">Source WH</td>
												<td id="AH16" class="pClothBs">Source WH Name</td>
												<td id="AH17" class="pClothBs">Source SWH</td>
												<td id="AH18" class="pClothBs">Supersession</td>
												<td id="AH19" class="pClothBs">Comment</td>
											</tr>
										</table>
									</div><!--rightTblHea -->
								</div><!-- right table -->
								<%-- ******************************** --%>
								<%-- *** Right Table Area(Detail) *** --%>
								<%-- ******************************** --%>
								
								<div id="rightTbl" style="width:1126; height:280px; display:block; overflow:scroll; margin:0px; padding:0px;" >	
									
									<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" style="width:700px;">
									
										<col align="center" width="24"><%-- Check box --%>
										<col align="center" width="100"><%-- WH Category --%>
										<col align="left" width="76"><%-- WH Code --%>
										<col align="center" width="116"><%-- WH Name --%>
										<col align="left" width="80"><%-- SWH Code --%>
										<col align="center" width="96"><%-- Item Number --%>
										<col align="center" width="160"><%-- Item Description --%>
										<col align="center" width="40"><%-- NoteT --%>
										<col align="center" width="40"><%-- MT --%>
										<col align="center" width="40"><%-- PC --%>
										<col align="center" width="120"><%-- Include Entered Sales Order --%>
										<col align="center" width="100"><%-- Min Qty --%>
										<col align="center" width="100"><%-- Max Qty --%>
										<col align="center" width="64"><%-- Enabled --%>
										<col align="center" width="160"><%-- Source Type --%>
										<col align="center" width="148"><%-- Source WH --%>
										<col align="center" width="120"><%-- Source WH Name --%>
										<col align="center" width="80"><%-- Source SWH --%>
										<col align="center" width="88"><%-- Supersession --%>
										<col align="center" width="200"><%-- Comment --%>
										<ezf:row ezfHyo="A">
										<tr height="30" id="id_row{EZF_ROW_NUMBER}">
											<%-- Check box --%>
											<td>
												<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1{EZF_ROW_NUMBER}\""/>
											</td>
											<%-- WH Category --%>
											<td>
												<ezf:inputText name="rtlWhCatgDescTxt_A1" ezfName="rtlWhCatgDescTxt_A1" value="XXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"50\" ezftoupper=\"\""/>
											</td>
											<%-- WH Code --%>
											<td>
												<ezf:label name="rtlWhCd_A1" ezfName="rtlWhCd_A1" ezfHyo="A" ezfArrayNo="0" />
											</td>
											<%-- WH Name --%>
											<td>
												<ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="XXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/>
											</td>
											<%-- SWH Code --%>
											<td>
												<ezf:label name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="0" />
											</td>
											<%-- Item Number --%>
											<td>
												<ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="XXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"16\" ezftoupper=\"\""/>
											</td>
											<%-- Item Description --%>
											<td>
												<ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="XXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"250\" ezftoupper=\"\""/>
											</td>
											
											<td>
											    <a href=""  tabindex="-1" id="ItemMasterAttachment{EZF_ROW_NUMBER}" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ItemMasterAttachment"><label name="xxRsltFlg_A1" ezfName="xxRsltFlg_A1" ezfHyo="A" ezfArrayNo="0"></label></a>
                                            </td>
											<%-- MT --%>
											<td>
												<ezf:label name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\""/>
											</td>
											<%-- PC --%>
											<td>
												<ezf:label name="coaProdCd_A1" ezfName="coaProdCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\""/>
											</td>
											<%-- Include Entered Sales Order --%>
											<td>
												<ezf:inputCheckBox name="calcOrdProcCd_A1" ezfName="calcOrdProcCd_A1" value="Y" ezfHyo="A" ezfArrayNo="0" />
											</td>
											<%-- Min Qty --%>
											<td>
												<ezf:inputText name="ropQty_A1" ezfName="ropQty_A1" value="9999999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\" style=\"text-align:right;\""/>
											</td>
											<%-- Max Qty --%>
											<td>
												<ezf:inputText name="maxInvtyQty_A1" ezfName="maxInvtyQty_A1" value="9999999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\" style=\"text-align:right;\""/>
											</td>
											<%-- Enabled --%>
											<td>
												<ezf:inputCheckBox name="mrpEnblFlg_A1" ezfName="mrpEnblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" />
											</td>
											<%-- Source Type --%>
											<td>
												<ezf:select name="procrTpCd_AS" ezfName="procrTpCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="procrTpCd_AP" ezfDispName="procrTpDescTxt_AP" />
											</td>
											<%-- Source WH --%>
											<td>
												<ezf:inputButton name="OpenWin_SourceWarehouseDetail" value="WH" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" otherAttr=" id=\"OpenWin_SourceWarehouseDetail{EZF_ROW_NUMBER}\""/>
												<ezf:inputText name="srcRtlWhCd_A1" ezfName="srcRtlWhCd_A1" value="XXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"20\" ezftoupper=\"\" ezftoupper=\"\""/>
												<ezf:inputButton name="SetSourceWarehouseNameDetail" value=">>" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"SetSourceWarehouseNameDetail{EZF_ROW_NUMBER}\""/>
												
											</td>
											<%-- Source WH Name --%>
											<td>
												<ezf:inputText name="rtlWhNm_A2" ezfName="rtlWhNm_A2" value="XXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/>
											</td>
											<%-- Source SWH --%>
											<td>
												<ezf:inputText name="srcRtlSwhCd_A1" ezfName="srcRtlSwhCd_A1" value="XXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"20\" ezftoupper=\"\" ezftoupper=\"\""/>
											</td>
											<%-- Supersession --%>
											<td>
													<ezf:label name="supdFlg_A1" ezfName="supdFlg_A1" ezfHyo="A" ezfArrayNo="0" />
											</td>
											<%-- Comment --%>
											<td>
												<ezf:inputText name="mrpInfoCmntTxt_A1" ezfName="mrpInfoCmntTxt_A1" value="supersession to 1111A123AB" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"120\""/>
											</td>
	                                        <td class="pAuditInfo">
	                                            <ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
	                                            <ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
	                                            <ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
	                                            <ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
	                                            <ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
	                                        </td>
										</tr>
										</ezf:row>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div><!-- parentBoxA -->
				<script type="text/javascript" defer>
					S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, false, true);
				</script>
				
				<%-- ################################################## Detail   - [END] ################################################## --%>

				
				<%-- ######################################## FOOTER    - [START] ######################################## --%>
				<div align="left">
					<table border="0">
						<tr height="20">
							<td valign="bottom">
								<table border="0" cellpadding="1" cellspacing="0" width="100%">
									<col width="82">
									<col width="82">
									<col width="">
									<tr>
										<td>
											<ezf:inputButton name="CheckAll" value="Check All" htmlClass="pBtn7" />
										</td>
										<td>
											<ezf:inputButton name="UncheckAll" value="UnCheck All" htmlClass="pBtn7" />
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr height="20">
							<td valign="bottom">
								<table border="0" cellpadding="1" cellspacing="0" width="100%">
									<col width="82">
									<col width="82">
									<col width="250">
									<col width="60">
									<col width="200">
									<col width="10">
									<col width="40">
									<col width="40">
									<col width="40">
									<tr>
										<td>
											<ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn7" />
										</td>
										<td>
											<ezf:inputButton name="Disable" value="Disable" htmlClass="pBtn7" />
										</td>
										<td></td>
										<td class="stab">
											<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TempleteFileForUpload" >Import File</ezf:anchor>
										</td>
										<td>
											<ezf:inputFile name="xxFileData_UP" ezfName="xxFileData_UP" otherAttr=" size=\"50\" maxlength=\"9999\" ezftoupper=\"\""/>
										</td>
										<td></td>
										<td><ezf:inputButton name="Import" value="Import" htmlClass="pBtn6" /></td>
										<td><ezf:inputButton name="OpneWin_Min_Max_Plan_Copy" value="Copy" htmlClass="pBtn6" /></td>
										<td><ezf:inputButton name="Attachments" value="Attachments" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</center>

			<%-- ######################################## FOOTER    - [END] ######################################## --%>

			

<%-- **** Task specific area ends here **** --%>
