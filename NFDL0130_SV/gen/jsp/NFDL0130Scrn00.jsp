<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160912183653 --%>
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
			<input type="hidden" name="pageID" value="NFDL0130Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Stategy Master - Work Item">
<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
<!-- ######################################## HEADER ######################################## -->
				<%-- ###TAB - HEAD --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Group Level Report" class="pTab_ON"><a href="#">Work Item</a></li>
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
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table height="10">
						<tr>
							<td><br></td>
						</tr>
					</table>
					<!-- ######################################## DETAIL ######################################## -->
					<table border="0" style="margin-left:15;">
						<tr>
							<td>
								<!-- total table -->
								<table border="0" cellpadding="1" cellspacing="0" width="1080" height="450" rules="none">
									<tr>
										<!-- Left -->
										<td align="center" valign="top">
											<!-- Left TBL Top -->
											<div id="LeftTBL_Top" style="overflow-x:none; overflow-y:none; float:none;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col width="30">
													<col width="60">
													<col width="400">
													<tr height="50" align="center" valign="middle">
														<td class="pClothBs">&nbsp;</td>
														<td class="pClothBs">Code</td>
														<td class="pClothBs">Name</td>
													</tr>
												</table>
											</div>
											<!-- Left TBL -->
											<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:450; float:none;" onScroll="synchroScrollTop(this.id, new Array('RightTBL'));">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed" id="A1">
													<col width="30" align="center">
													<col width="60" align="center">
													<col width="400">
													<ezf:row ezfHyo="A">
													<tr height="24">
														<td><ezf:inputRadio name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"xxRadioBtn_A#{EZF_ROW_NUMBER}\""/></td>
														<td><ezf:inputText name="cltWrkItemCd_A" ezfName="cltWrkItemCd_A" value="01" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"28\" id=\"cltWrkItemCd_A#{EZF_ROW_NUMBER}\" style=\"text-align:center;\" ezftoupper=\"\""/></td>
														<td><ezf:inputText name="cltWrkItemNm_A" ezfName="cltWrkItemNm_A" value="1st Dunning Letter" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"55\" maxlength=\"100\" id=\"cltWrkItemNm_A#{EZF_ROW_NUMBER}\" style=\"text-align:left;\""/></td>
													</tr>
													<ezf:skip>
													<tr height="24">
														<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" ezfhyo="A" value="{EZF_ROW_NUMBER}" id="xxRadioBtn_A#{EZF_ROW_NUMBER}"></td>
														<td><input type="text" size="7"  maxlength="28"  name="cltWrkItemCd_A" ezfName="cltWrkItemCd_A" ezfhyo="A" id="cltWrkItemCd_A#{EZF_ROW_NUMBER}" style="text-align:center;" value="01" ezftoupper></td>
														<td><input type="text" size="55" maxlength="100" name="cltWrkItemNm_A" ezfName="cltWrkItemNm_A" ezfhyo="A" id="cltWrkItemNm_A#{EZF_ROW_NUMBER}" style="text-align:left;"   value="1st Dunning Letter" ezfhyo="A"></td>
													</tr>
													<tr height="24">
														<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" ezfhyo="A" value="{EZF_ROW_NUMBER}" id="xxRadioBtn_A#{EZF_ROW_NUMBER}"></td>
														<td><input type="text" size="7"  maxlength="28"  name="cltWrkItemCd_A" ezfName="cltWrkItemCd_A" ezfhyo="A" id="cltWrkItemCd_A#{EZF_ROW_NUMBER}" style="text-align:center;" value="01" ezftoupper></td>
														<td><input type="text" size="55" maxlength="100" name="cltWrkItemNm_A" ezfName="cltWrkItemNm_A" ezfhyo="A" id="cltWrkItemNm_A#{EZF_ROW_NUMBER}" style="text-align:left;"   value="1st Dunning Letter" ezfhyo="A"></td>
													</tr>
													<tr height="24">
														<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" ezfhyo="A" value="{EZF_ROW_NUMBER}" id="xxRadioBtn_A#{EZF_ROW_NUMBER}"></td>
														<td><input type="text" size="7"  maxlength="28"  name="cltWrkItemCd_A" ezfName="cltWrkItemCd_A" ezfhyo="A" id="cltWrkItemCd_A#{EZF_ROW_NUMBER}" style="text-align:center;" value="01" ezftoupper></td>
														<td><input type="text" size="55" maxlength="100" name="cltWrkItemNm_A" ezfName="cltWrkItemNm_A" ezfhyo="A" id="cltWrkItemNm_A#{EZF_ROW_NUMBER}" style="text-align:left;"   value="1st Dunning Letter" ezfhyo="A"></td>
													</tr>
													</ezf:skip>
													</ezf:row>
												</table>
											</div>
										</td>
										<!-- Right -->
										<td align="left" valign="top">
											<!-- Right TBL Top -->
											<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:570; float:left;" onScroll="synchroScrollLeft(this.id, new Array('RightTBL'));">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;" width="600">
													<col width="110" align="center">	<!-- work item type -->
													<col align="center" width="60">	<!-- work category code -->
													<col align="center" width="130">	<!-- work category name -->
													<col align="center" width="80">	<!-- wait time(days) -->
													<col align="center" width="80">	<!-- escalation time(days) -->
													<col align="center" width="60">	<!-- optional item -->
													<col align="center" width="60">	<!-- escalation -->
													<col align="center" width="60">	<!-- notify -->
													<col align="center" width="500">	<!-- work item description -->
													<tr height="50" valign="middle">
														<td class="pClothBs">Work Item Type</td>
														<td class="pClothBs">Work Category Code</td>
														<td class="pClothBs">Work Category Name</td>
														<td class="pClothBs">Wait time(days)</td>
														<td class="pClothBs">Escalation Time(days)</td>
														<td class="pClothBs">Optional Item</td>
														<td class="pClothBs">Escalation</td>
														<td class="pClothBs">Notify</td>
														<td class="pClothBs">Work Item Description</td>
													</tr>
												</table>
											</div>
											<!-- Right TBL -->
											<div id="RightTBL" style="overflow-x:scroll; overflow-y:scroll; width:590; height:470; float:left;" onScroll="synchroScrollTop(this.id, new Array('LeftTBL')); synchroScrollLeft(this.id, new Array('RightTBL_Top'));">
												<table border="1" cellpadding="1" cellspacing="0" id="A2" style="table-layout: fixed" width="600">
													<col align="center" width="110">	<!-- work item type -->
													<col align="center" width="60">	<!-- work category code -->
													<col align="center" width="130">	<!-- work category name -->
													<col align="right"  width="80">	<!-- wait time(days) -->
													<col align="right"  width="80">	<!-- escalation time(days) -->
													<col align="center" width="60">	<!-- optional item -->
													<col align="center" width="60">	<!-- escalation -->
													<col align="center" width="60">	<!-- notify -->
													<col align="center" width="500">	<!-- work item description -->
													<ezf:row ezfHyo="A">
														<tr id="id_row${EZF_ROW_NUMBER}" height="24">
															<td align="center"><ezf:select name="cltWrkTpCd_SV" ezfName="cltWrkTpCd_SV" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="cltWrkTpCd_CD" ezfDispName="cltWrkTpNm_SC" ezfCodeDispHyo="A" otherAttr=" style=\"width:100px;\" id=\"cltWrkTpCd_SV#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputText name="cltWrkCatgCd_A" ezfName="cltWrkCatgCd_A" value="2111" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"28\" id=\"cltWrkCatgCd_A#{EZF_ROW_NUMBER}\" style=\"text-align:center;\" ezftoupper=\"\""/></td>
															<td><ezf:inputText name="cltWrkCatgNm_A" ezfName="cltWrkCatgNm_A" value="3111" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"100\" id=\"cltWrkCatgNm_A#{EZF_ROW_NUMBER}\" style=\"text-align:left;\""/></td>
															<td><ezf:inputText name="cltWrkWaitDaysAot_A" ezfName="cltWrkWaitDaysAot_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"cltWrkWaitDaysAot_A#{EZF_ROW_NUMBER}\" style=\"text-align:right;\""/></td>
															<td><ezf:inputText name="cltWrkEsclWaitDaysAot_A" ezfName="cltWrkEsclWaitDaysAot_A" value="10" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"cltWrkEsclWaitDaysAot_A#{EZF_ROW_NUMBER}\" style=\"text-align:right;\""/></td>
															<td><ezf:inputCheckBox name="cltWrkOptItemFlg_A" ezfName="cltWrkOptItemFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"cltWrkOptItemFlg_A#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputCheckBox name="cltWrkEsclFlg_A" ezfName="cltWrkEsclFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"cltWrkEsclFlg_A#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputCheckBox name="cltWrkNtfyFlg_A" ezfName="cltWrkNtfyFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"cltWrkNtfyFlg_A#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputText name="cltWrkDescTxt_A" ezfName="cltWrkDescTxt_A" value="XxxxxxxxxxXxxxxxxxxxXxxxxxxxxx" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"70\" maxlength=\"1000\" id=\"cltWrkDescTxt_A#{EZF_ROW_NUMBER}\" style=\"text-align:left;\""/></td>
														</tr>
														<ezf:skip>
														<tr id="id_row${EZF_ROW_NUMBER}" height="24">
															<td align="center"><select name="cltWrkTpCd_SV" ezfname="cltWrkTpCd_SV" ezfhyo="A" ezflist="cltWrkTpCd_CD,cltWrkTpNm_SC,99, ,blank" ezfCodeDispHyo="A" style="width:100px;" id="cltWrkTpCd_SV#{EZF_ROW_NUMBER}"><option>Workflow</option><option>Automatic</option><option>Manual</option></select></td>
															<td><input type="text" size="7"  maxlength="28"   name="cltWrkCatgCd_A"          ezfName="cltWrkCatgCd_A"          ezfhyo="A" id="cltWrkCatgCd_A#{EZF_ROW_NUMBER}"          style="text-align:center;" value="2111" ezftoupper></td>
															<td><input type="text" size="20" maxlength="100"  name="cltWrkCatgNm_A"          ezfName="cltWrkCatgNm_A"          ezfhyo="A" id="cltWrkCatgNm_A#{EZF_ROW_NUMBER}"          style="text-align:left;"   value="3111"></td>
															<td><input type="text" size="10"  maxlength="10"   name="cltWrkWaitDaysAot_A"     ezfName="cltWrkWaitDaysAot_A"     ezfhyo="A" id="cltWrkWaitDaysAot_A#{EZF_ROW_NUMBER}"     style="text-align:right;"  value="1"></td>
															<td><input type="text" size="10"  maxlength="10"   name="cltWrkEsclWaitDaysAot_A" ezfName="cltWrkEsclWaitDaysAot_A" ezfhyo="A" id="cltWrkEsclWaitDaysAot_A#{EZF_ROW_NUMBER}" style="text-align:right;"  value="10"></td>
															<td><input type="checkbox" class="pPro"           name="cltWrkOptItemFlg_A"      ezfName="cltWrkOptItemFlg_A"      ezfhyo="A" id="cltWrkOptItemFlg_A#{EZF_ROW_NUMBER}"                                 value="Y"></td>
															<td><input type="checkbox" class="pPro"           name="cltWrkEsclFlg_A"         ezfName="cltWrkEsclFlg_A"         ezfhyo="A" id="cltWrkEsclFlg_A#{EZF_ROW_NUMBER}"                                    value="Y"></td>
															<td><input type="checkbox" class="pPro"           name="cltWrkNtfyFlg_A"         ezfName="cltWrkNtfyFlg_A"         ezfhyo="A" id="cltWrkNtfyFlg_A#{EZF_ROW_NUMBER}"                                    value="Y"></td>
															<td><input type="text" size="70" maxlength="1000" name="cltWrkDescTxt_A"         ezfName="cltWrkDescTxt_A"         ezfhyo="A" id="cltWrkDescTxt_A#{EZF_ROW_NUMBER}"         style="text-align:left;"   value="XxxxxxxxxxXxxxxxxxxxXxxxxxxxxx"></td>
														</tr>
														<tr id="id_row${EZF_ROW_NUMBER}" height="24">
															<td align="center"><select name="cltWrkTpCd_SV" ezfname="cltWrkTpCd_SV" ezfhyo="A" ezflist="cltWrkTpCd_CD,cltWrkTpNm_SC,99, ,blank" ezfCodeDispHyo="A" style="width:100px;" id="cltWrkTpCd_SV#{EZF_ROW_NUMBER}"><option>Workflow</option><option>Automatic</option><option>Manual</option></select></td>
															<td><input type="text" size="7"  maxlength="28"   name="cltWrkCatgCd_A"          ezfName="cltWrkCatgCd_A"          ezfhyo="A" id="cltWrkCatgCd_A#{EZF_ROW_NUMBER}"          style="text-align:center;" value="2111" ezftoupper></td>
															<td><input type="text" size="20" maxlength="100"  name="cltWrkCatgNm_A"          ezfName="cltWrkCatgNm_A"          ezfhyo="A" id="cltWrkCatgNm_A#{EZF_ROW_NUMBER}"          style="text-align:left;"   value="3111"></td>
															<td><input type="text" size="10"  maxlength="10"   name="cltWrkWaitDaysAot_A"     ezfName="cltWrkWaitDaysAot_A"     ezfhyo="A" id="cltWrkWaitDaysAot_A#{EZF_ROW_NUMBER}"     style="text-align:right;"  value="1"></td>
															<td><input type="text" size="10"  maxlength="10"   name="cltWrkEsclWaitDaysAot_A" ezfName="cltWrkEsclWaitDaysAot_A" ezfhyo="A" id="cltWrkEsclWaitDaysAot_A#{EZF_ROW_NUMBER}" style="text-align:right;"  value="10"></td>
															<td><input type="checkbox" class="pPro"           name="cltWrkOptItemFlg_A"      ezfName="cltWrkOptItemFlg_A"      ezfhyo="A" id="cltWrkOptItemFlg_A#{EZF_ROW_NUMBER}"                                 value="Y"></td>
															<td><input type="checkbox" class="pPro"           name="cltWrkEsclFlg_A"         ezfName="cltWrkEsclFlg_A"         ezfhyo="A" id="cltWrkEsclFlg_A#{EZF_ROW_NUMBER}"                                    value="Y"></td>
															<td><input type="checkbox" class="pPro"           name="cltWrkNtfyFlg_A"         ezfName="cltWrkNtfyFlg_A"         ezfhyo="A" id="cltWrkNtfyFlg_A#{EZF_ROW_NUMBER}"                                    value="Y"></td>
															<td><input type="text" size="70" maxlength="1000" name="cltWrkDescTxt_A"         ezfName="cltWrkDescTxt_A"         ezfhyo="A" id="cltWrkDescTxt_A#{EZF_ROW_NUMBER}"         style="text-align:left;"   value="XxxxxxxxxxXxxxxxxxxxXxxxxxxxxx"></td>
														</tr>
														<tr id="id_row${EZF_ROW_NUMBER}" height="24">
															<td align="center"><select name="cltWrkTpCd_SV" ezfname="cltWrkTpCd_SV" ezfhyo="A" ezflist="cltWrkTpCd_CD,cltWrkTpNm_SC,99, ,blank" ezfCodeDispHyo="A" style="width:100px;" id="cltWrkTpCd_SV#{EZF_ROW_NUMBER}"><option>Workflow</option><option>Automatic</option><option>Manual</option></select></td>
															<td><input type="text" size="7"  maxlength="28"   name="cltWrkCatgCd_A"          ezfName="cltWrkCatgCd_A"          ezfhyo="A" id="cltWrkCatgCd_A#{EZF_ROW_NUMBER}"          style="text-align:center;" value="2111" ezftoupper></td>
															<td><input type="text" size="20" maxlength="100"  name="cltWrkCatgNm_A"          ezfName="cltWrkCatgNm_A"          ezfhyo="A" id="cltWrkCatgNm_A#{EZF_ROW_NUMBER}"          style="text-align:left;"   value="3111"></td>
															<td><input type="text" size="10"  maxlength="10"   name="cltWrkWaitDaysAot_A"     ezfName="cltWrkWaitDaysAot_A"     ezfhyo="A" id="cltWrkWaitDaysAot_A#{EZF_ROW_NUMBER}"     style="text-align:right;"  value="1"></td>
															<td><input type="text" size="10"  maxlength="10"   name="cltWrkEsclWaitDaysAot_A" ezfName="cltWrkEsclWaitDaysAot_A" ezfhyo="A" id="cltWrkEsclWaitDaysAot_A#{EZF_ROW_NUMBER}" style="text-align:right;"  value="10"></td>
															<td><input type="checkbox" class="pPro"           name="cltWrkOptItemFlg_A"      ezfName="cltWrkOptItemFlg_A"      ezfhyo="A" id="cltWrkOptItemFlg_A#{EZF_ROW_NUMBER}"                                 value="Y"></td>
															<td><input type="checkbox" class="pPro"           name="cltWrkEsclFlg_A"         ezfName="cltWrkEsclFlg_A"         ezfhyo="A" id="cltWrkEsclFlg_A#{EZF_ROW_NUMBER}"                                    value="Y"></td>
															<td><input type="checkbox" class="pPro"           name="cltWrkNtfyFlg_A"         ezfName="cltWrkNtfyFlg_A"         ezfhyo="A" id="cltWrkNtfyFlg_A#{EZF_ROW_NUMBER}"                                    value="Y"></td>
															<td><input type="text" size="70" maxlength="1000" name="cltWrkDescTxt_A"         ezfName="cltWrkDescTxt_A"         ezfhyo="A" id="cltWrkDescTxt_A#{EZF_ROW_NUMBER}"         style="text-align:left;"   value="XxxxxxxxxxXxxxxxxxxxXxxxxxxxxx"></td>
														</tr>
														</ezf:skip>
													</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<table border="0" width="93%" align="center">
								<col width="" align="right">
								<col width="" align="">
								<col width="" align="right">
								<col width="" align="">
								<tr>
									<td>
										<table border="0" cellpadding="1" cellspacing="0">
											<tr>
												<td >
													<ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn7" otherAttr=" id=\"btnInsertRow\""/>
												</td>
												<td>&nbsp;</td>
												<td>
													<ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn7" otherAttr=" id=\"btnDeleteRow\""/>
												</td>
												<td>&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					<%-- ## TABLE_DEFINITION ## --%>
				</div><!-- pTab_BODY -->
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
