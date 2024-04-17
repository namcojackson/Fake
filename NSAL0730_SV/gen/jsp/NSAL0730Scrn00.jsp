<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190313151311 --%>
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
			<input type="hidden" name="pageID" value="NSAL0730Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Update PO Information">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Update PO Information" class="pTab_ON"><a href="#">Upd PO Info</a></li>
									</div>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
					<table border="0" width="100%">
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
									<col width="100">
									<col width="180">
									<col width="420">
									<col width="100">
									<tr>
										<td colspan="4">
											<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
												<col width="100">
												<col width="180">
												<col width="10">
												<col width=" 70">
												<col width="130">
												<col width=" 70">
												<col width="130">
												<tr>
													<td class="stab"><ezf:anchor name="custPoNum" ezfName="custPoNum" ezfEmulateBtn="1" ezfGuard="OpenWin_PO" >New PO#</ezf:anchor></td>
													<td><ezf:inputText name="custPoNum" ezfName="custPoNum" value="WWWWWWWWW1WWWWWWWWW2WWWWW" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
													<td></td>
													<td class="stab">From Date</td>
													<td><ezf:inputText name="poFromDt" ezfName="poFromDt" value="01/01/2015" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('poFromDt', 4);"></td>
													<td class="stab">Thru Date</td>
													<td><ezf:inputText name="poDt" ezfName="poDt" value="01/31/2015" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('poDt', 4);"></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td class="stab">Reason Code</td>
										<td colspan="3"><ezf:select name="svcMemoRsnCd_H3" ezfName="svcMemoRsnCd_H3" ezfBlank="1" ezfCodeName="svcMemoRsnCd_H1" ezfDispName="svcMemoRsnNm_H2" /></td>
									</tr>
									<tr>
										<td class="stab">Notes</td>
										<td colspan="2"><ezf:textArea name="svcCmntTxt" ezfName="svcCmntTxt" otherAttr=" cols=\"80\" rows=\"4\""/></td>
										<td><ezf:inputButton name="ApplyToAll" value="APPLY TO ALL" htmlClass="pBtn8" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
<%-- #################################################### DETAIL ################################################### --%>
<%@ page import="business.servlet.NSAL0730.NSAL0730BMsg" %>
<%@ page import="business.servlet.NSAL0730.NSAL0730_ABMsg" %>
<%  NSAL0730BMsg bMsg = (NSAL0730BMsg)databean.getEZDBMsg(); %>
					<table border="0" width="100%">
					<tr>
						<td>
							<table  border="0" cellpadding="0" cellspacing="0" width="1090">
								<col width="" align="right" valign="top">
								<tr>
									<td>
										<ezf:skip>
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="54"  align="center">
												<col width="32"  align="right">
												<col width="16"  align="center">
												<col width="32"  align="right">
												<col width="16"  align="center">
												<col width="32"  align="right">
												<col width="10">
												<col>
												<col width="1">
												<col>
												<tr>
													<td class="stab">Showing</td>
													<td class="pOut">0</td>
													<td class="stab">to</td>
													<td class="pOut">0</td>
													<td class="stab">of</td>
													<td class="pOut">0</td>
													<td>&nbsp;</td>
													<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Next" name="PageNext" disabled></td>
												</tr>
											</table>
										</ezf:skip>
										<table width="500">
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
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
								<col width=" 20" align="center"><!-- icon -->
								<col width="150" align="center"><!-- Contract# -->
								<col width=" 24" align="center"><!-- CheckBox -->
								<col width="120" align="center"><!-- Serial# -->
								<col width=" 24" align="center"><!-- CheckBox -->
								<col width="100" align="center"><!-- Base/Overage -->
								<col width="193" align="center"><!-- PO# -->
								<col width=" 97" align="center"><!-- From Date -->
								<col width=" 97" align="center"><!-- Thru Date -->
								<col width=" 24" align="center"><!-- - -->
								<col width=" 24" align="center"><!-- + -->
								<col width=" 50" align="center"><!-- Leasing(CheckBox) -->
								<col width="120" align="center"><!-- Return Message -->
								<tr>
									<td class="pClothBs" rowspan="2">&nbsp;</td>
									<td class="pClothBs" rowspan="2">Contract#</td>
									<td class="pClothBs" rowspan="2"><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" onClick="sendServer('SelectAllContract')" /></td>
									<td class="pClothBs" rowspan="2">Serial#</td>
									<td class="pClothBs" rowspan="2"><ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" onClick="sendServer('SelectAllSerial')" /></td>
									<td class="pClothBs" rowspan="2">Base/Overage</td>
									<td class="pClothBs" colspan="5">Purchase Order</td>
									<td class="pClothBs" rowspan="2">Leasing</td>
									<td class="pClothBs" rowspan="2">Return Message</td>
								</tr>
								<tr>
									<td class="pClothBs">PO#</td>
									<td class="pClothBs">From Date</td>
									<td class="pClothBs">Thru Date</td>
									<td class="pClothBs"></td>
									<td class="pClothBs"></td>
								</tr>
							</table>
							<div style="width:1043; height:380; display:block; overflow-x:none; overflow-y:scroll;">
								<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
									<col width=" 20" align="center"><!-- icon -->
									<col width="150" align="center"><!-- Contract# -->
									<col width=" 24" align="center"><!-- CheckBox -->
									<col width="120" align="center"><!-- Serial# -->
									<col width=" 24" align="center"><!-- CheckBox -->
									<col width="100" align="center"><!-- Base/Overage -->
									<col width="193" align="center"><!-- PO# -->
									<col width=" 97" align="center"><!-- From Date -->
									<col width=" 97" align="center"><!-- Thru Date -->
									<col width=" 24" align="center"><!-- - -->
									<col width=" 24" align="center"><!-- + -->
									<col width=" 50" align="center"><!-- Leasing(CheckBox) -->
									<col width="120" align="center"><!-- Return Message -->
									<% int i = 0; %>
									<% int idx=-1; %>
									<ezf:row ezfHyo="A">
									<% NSAL0730_ABMsg abMsg = bMsg.A.no(i++); %>
									<% boolean isLvl1 = "1".equals(abMsg.dsContrMachLvlNum.getValue()); %>
									<% boolean isLvl2 = "2".equals(abMsg.dsContrMachLvlNum.getValue()); %>
									<% boolean isLvl3 = "3".equals(abMsg.dsContrMachLvlNum.getValue()); %>
									<% boolean isDplyCtrlFlg = "Y".equals(abMsg.xxDplyCtrlFlg.getValue()); %>
										<tr height="25">
											<td>
												<% if(isLvl3||!isDplyCtrlFlg) out.println("<span style='visibility:hidden'>"); %>
													<% boolean isSmryLineFlg = "Y".equals(abMsg.xxSmryLineFlg.getValue()); %>
													<a id="xxSmryLineFlg#{EZF_ROW_NUMBER}" href="#" onclick="clickImg(this, '{EZF_ROW_NUMBER}'); return false;">
														<% if(isSmryLineFlg)  out.println("<img src=\"./img/biz/rightarrow2.png\"  value=\"Expansion\">"); %>
														<% if(!isSmryLineFlg) out.println("<img src=\"./img/biz/downarrow2.png\" value=\"Contraction\">"); %>
													</a>
												<% if(isLvl3||!isDplyCtrlFlg) out.println("</span>"); %>
											</td>
											<td><ezf:inputText name="xxScrItem34Txt" ezfName="xxScrItem34Txt" value="WW3-WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem34Txt#{EZF_ROW_NUMBER}\""/></td>
											<td>
												<% if(isLvl3||!isDplyCtrlFlg) out.println("<span style='visibility:hidden'>"); %>
												<ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_D1{EZF_ROW_NUMBER}\""/>
												<% if(isLvl3||!isDplyCtrlFlg) out.println("</span>"); %>
											</td>
											<td><ezf:inputText name="serNum" ezfName="serNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\""/></td>
											<td>
												<% if(!isLvl3||(isLvl3&&!isDplyCtrlFlg)) out.println("<span style='visibility:hidden'>"); %>
												<ezf:inputCheckBox name="xxChkBox_D2" ezfName="xxChkBox_D2" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_D2#{EZF_ROW_NUMBER}\""/>
												<% if(!isLvl3||(isLvl3&&!isDplyCtrlFlg)) out.println("</span>"); %>
											</td>
											<td><ezf:inputText name="mtrLbDescTxt" ezfName="mtrLbDescTxt" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
											<td><ezf:inputText name="custPoNum_A" ezfName="custPoNum_A" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_PO" value="PO#" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PO#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="poFromDt_A" ezfName="poFromDt_A" value="01/01/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('poFromDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
											<td><ezf:inputText name="poDt_A" ezfName="poDt_A" value="01/31/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('poDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
											<td><ezf:anchor name="xxLinkProt_DL" ezfName="xxLinkProt_DL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="DeleteLine" otherAttr=" id=\"xxLinkProt_BD#{EZF_ROW_NUMBER}\""><img src="./img/biz/delete.png" border="0" value="Delete Line"></ezf:anchor></td>
											<td><ezf:anchor name="xxLinkProt_AL" ezfName="xxLinkProt_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="AddLine" otherAttr=" id=\"xxLinkProt_BD#{EZF_ROW_NUMBER}\""><img src="./img/biz/add.png" border="0" value="Add Line"></ezf:anchor></td>
											<td><ezf:inputCheckBox name="xxChkBox_D3" ezfName="xxChkBox_D3" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_D3#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="xxDsMsgEntryTxt" ezfName="xxDsMsgEntryTxt" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\""/></td>
                                            <td class="pAuditInfo">
                                                <ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A\""/>
                                                <ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A\""/>
                                                <ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A\""/>
                                                <ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A\""/>
                                                <ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A\""/>
                                            </td>
										</tr>
									</ezf:row>
									<ezf:skip>
										<tr height="25">
											<td><img src="./img/biz/rightarrow2.png"></td>
											<td><input type="text" class="pPro" size="34" value="WW3-WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="xxScrItem34Txt" ezfname="xxScrItem34Txt" id="xxScrItem34Txt#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
											<td><input type="checkbox" value="Y" name="xxChkBox_D1" ezfname="xxChkBox_D1" id="xxChkBox_D1#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
											<td><input type="text" class="pPro" size="30" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum" ezfname="serNum" ezfhyo="A"></td>
											<td><input type="checkbox" value="Y" name="xxChkBox_D2" ezfname="xxChkBox_D2" id="xxChkBox_D2#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
											<td><input type="text" class="pPro" size="30" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A"></td>
											<td><input type="text" class="pPro" size="21" value="WWWWWWWWW1WWWWWWWWW2WWWWW" name="custPoNum_A" ezfname="custPoNum_A" ezfhyo="A" ezftoupper=""><input type="button" class="pBtn0" value="PO#" name="UpdatePoInfo"></td>
											<td><input type="text" class="pPro" size="10" maxlength="10" value="01/01/2015" name="poFromDt_A" ezfname="poFromDt_A" ezfhyo="A"><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('poFromDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
											<td><input type="text" class="pPro" size="10" maxlength="10" value="01/31/2015" name="poDt_A" ezfname="poDt_A" ezfhyo="A"><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('poDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
											<td><a href="#" name="xxLinkProt_DL" id="xxLinkProt_BD#{EZF_ROW_NUMBER}" ezfname="xxLinkProt_DL" ezfhyo="A" ezfArrayNo="${idx}" onclick="sendServer('Delete_Line')"><img src="./img/biz/delete.png" border="0" value="Delete Line"></a></td>
											<td><a href="#" name="xxLinkProt_AL" id="xxLinkProt_BD#{EZF_ROW_NUMBER}" ezfname="xxLinkProt_AL" ezfhyo="A" ezfArrayNo="${idx}" onclick="sendServer('Add_Line')"><img src="./img/biz/add.png" border="0" value="Add Line"></a></td>
											<td><input type="checkbox" value="Y" name="xxChkBox_D3" ezfname="xxChkBox_D3" id="xxChkBox_D3#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
											<td><input type="text" class="pPro" size="25" value="WWWWWWWWW1WWWWWWWWW2WWWWW" name="xxDsMsgEntryTxt" ezfname="xxDsMsgEntryTxt" ezfhyo="A"></td>
										</tr>
										<tr height="25">
											<td><img src="./img/biz/rightarrow2.png"></td>
											<td><input type="text" class="pPro" size="34" value="WW3-WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="xxScrItem34Txt" ezfname="xxScrItem34Txt" id="xxScrItem34Txt#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
											<td><input type="checkbox" value="Y" name="xxChkBox_D1" ezfname="xxChkBox_D1" id="xxChkBox_D1#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
											<td><input type="text" class="pPro" size="30" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum" ezfname="serNum" ezfhyo="A"></td>
											<td><input type="checkbox" value="Y" name="xxChkBox_D2" ezfname="xxChkBox_D2" id="xxChkBox_D2#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
											<td><input type="text" class="pPro" size="30" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A"></td>
											<td><input type="text" class="pPro" size="21" value="WWWWWWWWW1WWWWWWWWW2WWWWW" name="custPoNum_A" ezfname="custPoNum_A" ezfhyo="A" ezftoupper=""><input type="button" class="pBtn0" value="PO#" name="UpdatePoInfo"></td>
											<td><input type="text" class="pPro" size="10" maxlength="10" value="01/01/2015" name="poFromDt_A" ezfname="poFromDt_A" ezfhyo="A"><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('poFromDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
											<td><input type="text" class="pPro" size="10" maxlength="10" value="01/31/2015" name="poDt_A" ezfname="poDt_A" ezfhyo="A"><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('poDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
											<td><a href="#" name="xxLinkProt_DL" id="xxLinkProt_BD#{EZF_ROW_NUMBER}" ezfname="xxLinkProt_DL" ezfhyo="A" ezfArrayNo="${idx}" onclick="sendServer('Delete_Line')"><img src="./img/biz/delete.png" border="0" value="Delete Line"></a></td>
											<td><a href="#" name="xxLinkProt_AL" id="xxLinkProt_BD#{EZF_ROW_NUMBER}" ezfname="xxLinkProt_AL" ezfhyo="A" ezfArrayNo="${idx}" onclick="sendServer('Add_Line')"><img src="./img/biz/add.png" border="0" value="Add Line"></a></td>
											<td><input type="checkbox" value="Y" name="xxChkBox_D3" ezfname="xxChkBox_D3" id="xxChkBox_D3#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
											<td><input type="text" class="pPro" size="25" value="WWWWWWWWW1WWWWWWWWW2WWWWW" name="xxDsMsgEntryTxt" ezfname="xxDsMsgEntryTxt" ezfhyo="A"></td>
										</tr>
									</ezf:skip>
								</table>
							</div>
						</td>
					</tr>
				</table>

			</div>
			</td>
		</tr>
		
	</table>
</center>

<style TYPE="text/css">
<!--
	a img{border-style:none;}
-->
</style>

<script type="text/javascript">
	function clickImg(prntObj, idx) {
		var eventNm = prntObj.all(0).value;
		sendServer(eventNm, idx);
	}
</script>

<%-- **** Task specific area ends here **** --%>
