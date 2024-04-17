<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180803145922 --%>
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
			<input type="hidden" name="pageID" value="NFDL0160Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Collector Portfolio Maintenance">
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
										<li title="Actual Counters for Interface" class="pTab_ON"><a href="#">Collector Prtfl</a></li>
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
					<table border="0" bordercolor="red" cellpadding="0" cellspacing="1" style="table-layout:fixed;" width="99%" align="center" >
						<tr><td></td></tr>
						<tr><td></td></tr>
						<tr>
							<td>
								<table border="0" width="100%">
									<col width="90">	<!-- Portfolio Code: -->
									<col width="130">	<!-- (Text output) -->
									<col width="90">	<!-- Portfolio Name: -->
									<col width="250">	<!-- (Text output) -->
									<col width="90">	<!-- Collector Name: -->
									<col width="430">	<!-- (Text output) -->
									<tr>
										<td class="stab">Portfolio Code(*)</td>
										<td>
											<ezf:inputText name="cltPtfoCd" ezfName="cltPtfoCd" value="99999999" otherAttr=" size=\"12\" maxlength=\"8\" ezftoupper=\"\""/>
										</td>
										<td class="stab">Portfolio Name(*)</td>
										<td>
											<ezf:inputText name="cltPtfoNm" ezfName="cltPtfoNm" value="010" otherAttr=" id=\"cltStrgyCd\" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/>
										</td>
										<td class="stab">Collector Name(*)</td>
										<td>
											<ezf:inputText name="cltPsnNm" ezfName="cltPsnNm" value="STRATEGY 10" otherAttr=" id=\"cltStrgyNm\" size=\"40\" maxlength=\"150\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab" colspan="2">
											<table border="1" cellpadding="1" cellspacing="0" width="95%" rules="none">
												<tr>
													<td class="stab"><ezf:inputRadio name="cltPtfoStsFlg" ezfName="cltPtfoStsFlg" value="A" />Active</td>
													<td class="stab"><ezf:inputRadio name="cltPtfoStsFlg" ezfName="cltPtfoStsFlg" value="I" />Inactive</td>
													<td class="stab"><ezf:inputRadio name="cltPtfoStsFlg" ezfName="cltPtfoStsFlg" value="B" />All</td>
												</tr>
											</table>
										</td>
										<td align="right" colspan="4">
											<ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" />
										<td/>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><hr></td>
						</tr>
						<tr>
						<!-- ######################################## DETAIL ######################################## -->
							<td>
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="100%" align="center" >
									<tr>
										<td>
											<div id="Top" style="overflow-x:hidden; overflow-y:hidden; width:1100;" 
												onscroll="synchroScrollLeft( this.id, new Array( 'Top' ));" >
												<table width="1100" border="1" cellpadding="0" cellspacing="0" style="width:2400px; table-layout:fixed">
													<col width="15"  align="center">
													<col width="50" align="center">
													<col width="103" align="center">
													<col width="103" align="center">
													<col width="103" align="center">
													<col width="101" align="center">
													<col width="68"  align="center">
													<col width="68"  align="center">
													<col width="101" align="center">
													<col width="101" align="center">
													<col width="101" align="center">
													<col width="101" align="center">
													<col width="101" align="center">
													<col width="20"  align="center">
													<tr height ="37">
														<td class="pClothBs">&nbsp;</td>
														<td class="pClothBs">Portfolio Code</td>
														<td class="pClothBs">Portfolio Name</td>
														<td class="pClothBs">Description</td>
														<td class="pClothBs">Correspondence Name</td>
														<td class="pClothBs">Collector</td>
														<td class="pClothBs">Telephone Number</td>
														<td class="pClothBs">Fax Number</td>
														<td class="pClothBs">AR WriteOff Activity</td>
														<td class="pClothBs">Credit Analyst Name<br>(Equipment)</td>
														<td class="pClothBs">Credit Analyst Name<br>(Service)</td>
														<td class="pClothBs">Credit Analyst Name<br>(Supplies)</td>
														<td class="pClothBs">Portfolio Next Level</td>
														<td class="pClothBs">Status</td>
													</tr>
												</table>
											</div>
											<div id="Detail" style="overflow-x:scroll; overflow-y:scroll; width:1117; height:428;"
								 				onscroll="synchroScrollLeft( this.id, new Array( 'Top' ));" >
												<table width="1100" id="A" border="1" cellpadding="0" cellspacing="0" style="width:2400px; word-break:break-all; table-layout:fixed">
													<col width="15"  align="center">
													<col width="50" align="center">
													<col width="103" align="center">
													<col width="103" align="center">
													<col width="103" align="center">
													<col width="101" align="center">
													<col width="68"  align="center">
													<col width="68"  align="center">
													<col width="101" align="center">
													<col width="101" align="center">
													<col width="101" align="center">
													<col width="101" align="center">
													<col width="101" align="center">
													<col width="20"  align="center">
													<tbody>
													<ezf:row ezfHyo="A">
													<tr id="id_row{EZF_ROW_NUMBER}" height="37">
														<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\" tabindex=\"\""/></td>
														<td><ezf:inputText name="cltPtfoCd_A" ezfName="cltPtfoCd_A" value="99999999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"8\" tabindex=\"\""/></td>
														
														<td><ezf:inputText name="cltPtfoNm_A" ezfName="cltPtfoNm_A" value="Cash" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"28\" maxlength=\"30\" tabindex=\"\""/></td>
														
														<td><ezf:inputText name="cltPtfoDescTxt_A" ezfName="cltPtfoDescTxt_A" value="Cash" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"28\" maxlength=\"240\" tabindex=\"\""/></td>
														
														<td><ezf:inputText name="cltPtfoCorNm_A" ezfName="cltPtfoCorNm_A" value="Cash" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"28\" maxlength=\"150\" tabindex=\"\""/></td>
														
														<td><ezf:inputText name="cltPsnNm_A" ezfName="cltPsnNm_A" value="Cash" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"margin-right:-8px\" size=\"25\" maxlength=\"150\" tabindex=\"\""/>
															<ezf:inputButton name="OpenWin_Clt" value="" ezfHyo="A" ezfArrayNo="0" htmlClass="myBtn" /></td>
														
														<td><ezf:inputText name="cltStmtPhoNum_A" ezfName="cltStmtPhoNum_A" value="0362700520" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"25\" tabindex=\"\" ezftoupper=\"\""/></td>
														<td><ezf:inputText name="cltStmtFaxNum_A" ezfName="cltStmtFaxNum_A" value="0334961010" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"25\" tabindex=\"\" ezftoupper=\"\""/></td>
														
														<td><ezf:inputText name="arAdjTpDescTxt_A" ezfName="arAdjTpDescTxt_A" value="NJ DEPT OF LAW & PUBLIC" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"margin-right:-8px\" size=\"25\" maxlength=\"50\" tabindex=\"\" ezftoupper=\"\""/>
															<ezf:inputButton name="OpenWin_AR" value="" ezfHyo="A" ezfArrayNo="0" htmlClass="myBtn" /></td>														
														<td><ezf:inputText name="cltCrAnlstEquipPsnNm_A" ezfName="cltCrAnlstEquipPsnNm_A" value="Audrey Morre" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"margin-right:-2px\" size=\"25\" maxlength=\"150\" tabindex=\"\""/>
															<button value="" name="OpenWin_CR" onclick="submitFromPluralFields('OpenWin_CR', 'CR_Equip', {EZF_ROW_NUMBER})" ezfhyo="A" class="myBtn"></button></td>
														
														<td><ezf:inputText name="cltCrAnlstSvcPsnNm_A" ezfName="cltCrAnlstSvcPsnNm_A" value="Grace Hepburn" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"margin-right:-2px\" size=\"25\" maxlength=\"150\" tabindex=\"\""/>
															<button value="" name="OpenWin_CR" onclick="submitFromPluralFields('OpenWin_CR', 'CR_Svc', {EZF_ROW_NUMBER})" ezfhyo="A" class="myBtn"></button></td>
														
														<td><ezf:inputText name="cltCrAnlstSplyPsnNm_A" ezfName="cltCrAnlstSplyPsnNm_A" value="Demi Kelly" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"margin-right:-2px\" size=\"25\" maxlength=\"150\" tabindex=\"\""/>
															<button value="" name="OpenWin_CR" onclick="submitFromPluralFields('OpenWin_CR', 'CR_Sply', {EZF_ROW_NUMBER})" ezfhyo="A" class="myBtn"></button></td>
															
														<td><ezf:inputText name="cltPtfoNm_AR" ezfName="cltPtfoNm_AR" value="Brad Pitt" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"margin-right:-8px\" size=\"25\" maxlength=\"30\" tabindex=\"\""/>
															<ezf:inputButton name="OpenWin_NextLvl" value="" ezfHyo="A" ezfArrayNo="0" htmlClass="myBtn" /></td>
														
														<td><ezf:inputCheckBox name="cltPtfoStsFlg_A" ezfName="cltPtfoStsFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_B#{EZF_ROW_NUMBER}\" tabindex=\"\""/></td>
													</tr>
													</ezf:row>
													<ezf:skip>
													</ezf:skip>
													</tbody>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table border="0" bordercolor="blue"  width="100%" align="center">
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
				</div>
			</td>
		</tr>
	</table>
</center>

<style type="text/css">
	.myBtn{margin-left:3px;height:19px;width:16px;}
</style>


<%-- **** Task specific area ends here **** --%>
