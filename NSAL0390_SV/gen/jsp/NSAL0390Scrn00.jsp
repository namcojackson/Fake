<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180320172217 --%>
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
			<input type="hidden" name="pageID" value="NSAL0390Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Update Credit Card Info contract / machine level">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
							<tr>
								<td width="96%">
									<li title = "Update Credit Card Info contract / machine level" class="pTab_ON" ><a href="#">Upd CC</a></li>
								</td>

								<td valign="bottom" align="center">
									<a href="#"><img id ="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id ="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
								</td>

							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

				<div class="pTab_BODY">
				<br>
					<table width="100%" border="0" cellpadding="1" cellspacing="0" style="margin-left:4px">
						<col width="75">
						<col width="95">
						<col width="15" align="left">
						<col width="75">
						<col width="200">
						<col width="65">
						<col width="95">
						<col width="15" align="left">
						<col width="65">
						<col width="300">
						<col width="">
						<tr>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SrchSerFrom" >Serial # From</ezf:anchor></td>
							<td><ezf:inputText name="serNum_HF" ezfName="serNum_HF" value="HHT0254" otherAttr=" size=\"12\""/></td>
							<td class="stab">-</td>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SrchSerThru" >Serial # Thru</ezf:anchor></td>
							<td><ezf:inputText name="serNum_HT" ezfName="serNum_HT" value="HHT0254" otherAttr=" size=\"12\""/></td>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SrchIBFrom" >IB ID From</ezf:anchor></td>
							<td><ezf:inputText name="svcMachMstrPk_HF" ezfName="svcMachMstrPk_HF" value="HHT0254" otherAttr=" size=\"12\""/></td>
							<td class="stab">-</td>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SrchIBThru" >IB ID Thru</ezf:anchor></td>
							<td><ezf:inputText name="svcMachMstrPk_HT" ezfName="svcMachMstrPk_HT" value="HHT0254" otherAttr=" size=\"12\""/></td>
							<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
						</tr>
					</table>
					<hr width="100%" align="left">
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
												<col width="100">
												<tr>
													<td class="stab"><ezf:anchor name="crCardCustRefNum_H" ezfName="crCardCustRefNum_H" ezfEmulateBtn="1" ezfGuard="OpenWin_CreditCard" >New Reference#</ezf:anchor></td>
													<td><ezf:inputText name="crCardCustRefNum_H" ezfName="crCardCustRefNum_H" value="WWWWWWWWW1WWWWWWWWW2WWWWW" otherAttr=" size=\"20\""/></td>
													<td></td>
													<td class="stab">Expire Date</td>
													<td><ezf:inputText name="crCardExprYrMth_H" ezfName="crCardExprYrMth_H" value="01/2015" otherAttr=" size=\"10\""/></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td class="stab">Reason Code</td>
										<td colspan="3"><ezf:select name="svcMemoRsnCd_H" ezfName="svcMemoRsnCd_H" ezfBlank="1" ezfCodeName="svcMemoRsnCd_L" ezfDispName="svcMemoRsnNm_L" /></td>
									</tr>
									<tr>
										<td class="stab">Notes</td>
										<td colspan="2"><ezf:textArea name="svcCmntTxt_H" ezfName="svcCmntTxt_H" otherAttr=" cols=\"80\" rows=\"4\""/></td>
										<td><ezf:inputButton name="ApplyToAll" value="APPLY TO ALL" htmlClass="pBtn8" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
					<table border="0" cellpadding="1" cellspacing="0" width="1111">
						<tr>
							<td align="left">&nbsp;</td>
							<td width="550" align="right">
								<table width="100%">
									<tr align="right">
										<td>
											<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
												<jsp:param name="beanId"			value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"			value="A" />
												<jsp:param name="ShowingFrom"		value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"			value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"			value="xxPageShowOfNum" />
												<jsp:param name="ShowingCurrent"	value="xxPageShowCurNum" />
												<jsp:param name="ShowingTotal"		value="xxPageShowTotNum" />
												<jsp:param name="ViewMode"			value="FULL" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

<%-- #################################################### DETAIL ################################################### --%>
<%@ page import="business.servlet.NSAL0390.NSAL0390BMsg" %>
<%@ page import="business.servlet.NSAL0390.NSAL0390_ABMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPConstant" %>
<%  NSAL0390BMsg bMsg = (NSAL0390BMsg)databean.getEZDBMsg(); %>
					<table border="0" width="100%">
						<tr>
							<td>
								<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
									<col width=" 18" align="center"><!-- icon -->
									<col width="150" align="center"><!-- Contract# -->
									<col width=" 24" align="center"><!-- CheckBox -->
									<col width="120" align="center"><!-- Serial# -->
									<col width=" 24" align="center"><!-- CheckBox -->
									<col width="100" align="center"><!-- Base/Overage -->
									<col width="120" align="center"><!-- Reference# -->
									<col width=" 72" align="center"><!-- Exp Date -->
									<col width="188" align="center"><!-- New Reference# -->
									<col width=" 80" align="center"><!-- New Exp Date -->
									<col width=" 50" align="center"><!-- Leasing(CheckBox) -->
									<col width=" 36" align="center"><!-- NoteFlag -->
									<col width="116" align="center"><!-- Return Message -->
									<tr>
										<td class="pClothBs" rowspan="2">&nbsp;</td>
										<td class="pClothBs" rowspan="2">Contract#</td>
										<td class="pClothBs" rowspan="2"><ezf:inputCheckBox name="xxChkBox_H0" ezfName="xxChkBox_H0" value="Y" onClick="sendServer('SelectAllContract')" /></td>
										<td class="pClothBs" rowspan="2">Serial#</td>
										<td class="pClothBs" rowspan="2"><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" onClick="sendServer('SelectAllSerial')" /></td>
										<td class="pClothBs" rowspan="2">Base/Overage</td>
										<td class="pClothBs" colspan="4">Credit Card</td>
										<td class="pClothBs" rowspan="2">Leasing</td>
										<td class="pClothBs" rowspan="2">Notes</td>
										<td class="pClothBs" rowspan="2">Return Message</td>
									</tr>
									<tr>
										<td class="pClothBs">Reference#</td>
										<td class="pClothBs">Exp Date</td>
										<td class="pClothBs">New Reference#</td>
										<td class="pClothBs">New Exp Date</td>
									</tr>
								</table>
								<div style="width:1089; height:330; display:block; overflow-x:none; overflow-y:scroll;">
									<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width=" 18" align="center"><!-- icon -->
										<col width="150" align="center"><!-- Contract# -->
										<col width=" 24" align="center"><!-- CheckBox -->
										<col width="120" align="center"><!-- Serial# -->
										<col width=" 24" align="center"><!-- CheckBox -->
										<col width="100" align="center"><!-- Base/Overage -->
										<col width="120" align="center"><!-- Reference# -->
										<col width=" 72" align="center"><!-- EXP Date -->
										<col width="188" align="center"><!-- New Refernce# -->
										<col width=" 80" align="center"><!-- New Exp Date -->
										<col width=" 50" align="center"><!-- Leasing(CheckBox) -->
										<col width=" 36" align="center"><!-- NoteFlag -->
										<col width="116" align="center"><!-- Return Message -->
										<% int i = 0; %>
										<ezf:row ezfHyo="A">
										<% NSAL0390_ABMsg abMsg = bMsg.A.no(i++); %>
											<tr height="25">
												<td>
													<% boolean isDplyCtrlFlg = "Y".equals(abMsg.xxDplyCtrlFlg_A0.getValue()); %>
													<% if(!isDplyCtrlFlg) out.println("<span style='visibility:hidden'>"); %>
														<% boolean isSmryLineFlg = "Y".equals(abMsg.xxSmryLineFlg_A0.getValue()); %>
														<a id="xxSmryLineFlg#{EZF_ROW_NUMBER}" href="#" onclick="clickImg(this, '{EZF_ROW_NUMBER}'); return false;">
															<% if(isSmryLineFlg)  out.println("<img src=\"./img/biz/rightarrow2.png\"  value=\"Expansion\">"); %>
															<% if(!isSmryLineFlg) out.println("<img src=\"./img/biz/downarrow2.png\" value=\"Contraction\">"); %>
														</a>
													<% if(!isDplyCtrlFlg) out.println("</span>"); %>
												</td>
												<td><ezf:inputText name="dsContrNum_A0" ezfName="dsContrNum_A0" value="WW3-WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"dsContrNum_A0#{EZF_ROW_NUMBER}\""/></td>
												<td>
													<% boolean isLvl = "3".equals(abMsg.dsContrMachLvlNum_A0.getValue()); %>
													<% if(isLvl) out.println("<span style='visibility:hidden'>"); %>
													<ezf:inputCheckBox name="xxChkBox_A0" ezfName="xxChkBox_A0" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A0{EZF_ROW_NUMBER}\""/>
													<% if(isLvl) out.println("</span>"); %>
												</td>
												<td><ezf:inputText name="serNum_A0" ezfName="serNum_A0" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\""/></td>
												<td>
													<% boolean isLvlD2 = "3".equals(abMsg.dsContrMachLvlNum_A0.getValue()); %>
													<% if(!isLvlD2) out.println("<span style='visibility:hidden'>"); %>
													<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/>
													<% if(!isLvlD2) out.println("</span>"); %>
												</td>
												<td><ezf:inputText name="mtrLbDescTxt_A0" ezfName="mtrLbDescTxt_A0" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
												<td><ezf:inputText name="crCardCustRefNum_A0" ezfName="crCardCustRefNum_A0" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\""/></td>
												<td><ezf:inputText name="crCardExprYrMth_A0" ezfName="crCardExprYrMth_A0" value="01/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\""/></td>
												<td><ezf:inputButton name="Open_CC" value="CC" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"Open_CC#{EZF_ROW_NUMBER}\""/><ezf:inputText name="crCardCustRefNum_A1" ezfName="crCardCustRefNum_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\""/></td>
												<td><ezf:inputText name="crCardExprYrMth_A1" ezfName="crCardExprYrMth_A1" value="01/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\""/></td>
												<td><ezf:inputCheckBox name="leaseCmpyFlg_A0" ezfName="leaseCmpyFlg_A0" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServer('LeasingCompany', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"leaseCmpyFlg_A0#{EZF_ROW_NUMBER}\""/></td>
												<td>
													<% if (ZYPConstant.FLG_ON_Y.equals(abMsg.xxCntDplyFlg_A0.getValue())) { %>
														<ezf:anchor name="xxCntDplyFlg_A0" ezfName="xxCntDplyFlg_A0" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_MemoEntry', '{EZF_ROW_NUMBER}" otherAttr=" id=\"OpenWin_MemoEntry#{EZF_ROW_NUMBER}\"">
														<ezf:label name="xxCntDplyFlg_A0" ezfName="xxCntDplyFlg_A0" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
													<% } else { %>
														<ezf:label name="xxCntDplyFlg_A0" ezfName="xxCntDplyFlg_A0" ezfHyo="A" ezfArrayNo="0" />
													<% } %>
												</td>
												<td><ezf:inputText name="xxDsMsgEntryTxt_A0" ezfName="xxDsMsgEntryTxt_A0" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\""/></td>
												<td class="pAuditInfo">
													<ezf:inputHidden name="xxRecHistCratTs_A0" ezfName="xxRecHistCratTs_A0" ezfHyo="A" ezfArrayNo="0" />
													<ezf:inputHidden name="xxRecHistCratByNm_A0" ezfName="xxRecHistCratByNm_A0" ezfHyo="A" ezfArrayNo="0" />
													<ezf:inputHidden name="xxRecHistUpdTs_A0" ezfName="xxRecHistUpdTs_A0" ezfHyo="A" ezfArrayNo="0" />
													<ezf:inputHidden name="xxRecHistUpdByNm_A0" ezfName="xxRecHistUpdByNm_A0" ezfHyo="A" ezfArrayNo="0" />
													<ezf:inputHidden name="xxRecHistTblNm_A0" ezfName="xxRecHistTblNm_A0" ezfHyo="A" ezfArrayNo="0" />
												</td>
											</tr>
										</ezf:row>
										<ezf:skip>
											<tr height="25">
												<td><img src="./img/biz/rightarrow2.png"></td>
												<td><input type="text" class="pPro" size="34" value="WW3-WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="dsContrNum_A0" ezfname="dsContrNum_A0" id="dsContrNum_A0#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
												<td><input type="checkbox" value="Y" name="xxChkBox_A0" ezfname="xxChkBox_A0" id="xxChkBox_A0#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="30" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A0" ezfname="serNum_A0" ezfhyo="A"></td>
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" id="xxChkBox_A1#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="30" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="mtrLbDescTxt_A0" ezfname="mtrLbDescTxt_A0" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="25" value="WWWWWWWWW1WWWWWWWWW2WWWWW" name="crCardCustRefNum_A0" ezfname="crCardCustRefNum_A0" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="8" value="01/2015" name="crCardExprYrMth_A0" ezfname="crCardExprYrMth_A0" ezfhyo="A"></td>
												<td><input type="button" class="pBtn0" value="CC" name="Open_CC" id="Open_CC#{EZF_ROW_NUMBER}" onclick="sendServer('Open_CC', '{EZF_ROW_NUMBER}')" ezfHyo="A"><input type="text" class="pPro" size="21" value="WWWWWWWWW1WWWWWWWWW2WWWWW" name="crCardCustRefNum_A1" ezfname="crCardCustRefNum_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="8" value="01/2015" name="crCardExprYrMth_A1" ezfname="crCardExprYrMth_A1" ezfhyo="A"></td>
												<td><input type="checkbox" value="Y" name="leaseCmpyFlg_A0" ezfname="leaseCmpyFlg_A0" ezfhyo="A" id="leaseCmpyFlg_A0#{EZF_ROW_NUMBER}" onclick="sendServer('LeasingCompany', '{EZF_ROW_NUMBER}')"></td>
												<td><a href="#" onclick="sendServer('OpenWin_MemoEntry', '{EZF_ROW_NUMBER}')" ezfhyo="OpenWin_MemoEntry">Y</a></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWWWWWWW2WWWWW" name="xxDsMsgEntryTxt_A0" ezfname="xxDsMsgEntryTxt_A0" ezfhyo="A"></td>
											</tr>
											<tr height="25">
												<td><img src="./img/biz/rightarrow2.png"></td>
												<td><input type="text" class="pPro" size="34" value="WW3-WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="dsContrNum_A0" ezfname="dsContrNum_A0" id="dsContrNum_A0#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
												<td><input type="checkbox" value="Y" name="xxChkBox_A0" ezfname="xxChkBox_A0" id="xxChkBox_A0#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="30" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A0" ezfname="serNum_A0" ezfhyo="A"></td>
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" id="xxChkBox_A1#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="30" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="mtrLbDescTxt_A0" ezfname="mtrLbDescTxt_A0" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="25" value="WWWWWWWWW1WWWWWWWWW2WWWWW" name="crCardCustRefNum_A0" ezfname="crCardCustRefNum_A0" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="8" value="01/2015" name="crCardExprYrMth_A0" ezfname="crCardExprYrMth_A0" ezfhyo="A"></td>
												<td><input type="button" class="pBtn0" value="CC" name="Open_CC" id="Open_CC#{EZF_ROW_NUMBER}" onclick="sendServer('Open_CC', '{EZF_ROW_NUMBER}')" ezfHyo="A"><input type="text" class="pPro" size="21" value="WWWWWWWWW1WWWWWWWWW2WWWWW" name="crCardCustRefNum_A1" ezfname="crCardCustRefNum_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="8" value="01/2015" name="crCardExprYrMth_A1" ezfname="crCardExprYrMth_A1" ezfhyo="A"></td>
												<td><input type="checkbox" value="Y" name="leaseCmpyFlg_A0" ezfname="leaseCmpyFlg_A0" ezfhyo="A" id="leaseCmpyFlg_A0#{EZF_ROW_NUMBER}" onclick="sendServer('LeasingCompany', '{EZF_ROW_NUMBER}')"></td>
												<td><a href="#" onclick="sendServer('OpenWin_MemoEntry', '{EZF_ROW_NUMBER}')" ezfhyo="OpenWin_MemoEntry">Y</a></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWWWWWWW2WWWWW" name="xxDsMsgEntryTxt_A0" ezfname="xxDsMsgEntryTxt_A0" ezfhyo="A"></td>
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
