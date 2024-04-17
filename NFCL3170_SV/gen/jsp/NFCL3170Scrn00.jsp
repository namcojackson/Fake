<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161213142008 --%>
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
			<input type="hidden" name="pageID" value="NFCL3170Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Bank Account Maintenance">


<center>
	<table width="1128" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>

<%-- ######################################## HEADER ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">

					<table width="100%" height="100%" border="0" cellpadding="1" cellspacing="0" align="center">
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0" height="210" width="1120" rules="none"  style="float:left;padding: 5px; margin-bottom: 5px; border: 1px solid #333333;">
									<tr valign="top">
										<td>
											<table border="0" cellpadding="2" cellspacing="1">
												<col width="130" align="right">
												<col width="005">
												<col width="380">
												<tr>
													<td  class="pClothBs" align="left"><b>Bank Accoun</b>t</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												<tr>
											</table>
											<table border="0" cellpadding="0" cellspacing="0">
												<tr valign="top">
													<td>
														<table border="0" cellpadding="2" cellspacing="1" rules="none" width="520" height="160" style="float:left;padding: 5px; margin-bottom: 5px; border: 1px solid #333333;">
															<col width="130" align="left">
															<col width="360">
															<tr>
																<td class="stab" >Bank Name:</td>
																<td><ezf:inputText name="dsBankAcctNm_H1" ezfName="dsBankAcctNm_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"50\" maxlength=\"70\" ezftoupper=\"\""/></td>
															</tr>
															<tr>
																<td class="stab" >Branch Name:</td>
																<td><ezf:inputText name="dsBankBrNm_H1" ezfName="dsBankBrNm_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"50\" maxlength=\"80\" ezftoupper=\"\""/></td>
															</tr>
															<tr>
																<td class="stab" >Routing Number:</td>
																<td><ezf:inputText name="bankRteNum_H1" ezfName="bankRteNum_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
															</tr>
															<tr>
																<td class="stab" >Account Number:</td>
																<td><ezf:inputText name="dsBankAcctNum_H1" ezfName="dsBankAcctNum_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"16\" maxlength=\"14\""/></td>
															</tr>
															<tr>
																<td class="stab">Account Type:</td>
																<td class="stab">
																	External
																<%-- Direct Edit JSP --%>
																<%--<ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="1" otherAttr="onclick=\"sendServer('OnChangeRadio');\""/></input> --%>
																	<ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="1" otherAttr="onclick=\"sendServer('OnChangeRadio');\""/></input>
																	Internal
																<%--<ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="2" otherAttr="onclick=\"sendServer('OnChangeRadio');\""/></input> --%>
																	<ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="2" otherAttr="onclick=\"sendServer('OnChangeRadio');\""/></input>
																</td>
															</tr>
															<tr>
																<td class="stab">Effective Start Date:</td>
																<td>
																	<ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" value="04/25/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
																	<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);" >
																</td>
															</tr>
															<tr>
																<td class="stab">Effective End Date:</td>
																<td>
																	<ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" value="04/25/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
																	<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H1', 4);" >
																</td>
															</tr>
														</table>
													</td>
													<td>&nbsp;</td>
													<td>
														<table border="0" cellpadding="2" cellspacing="1" rules="none" width="540" height="175"  style="float:left;padding: 5px; margin-bottom: 5px; border: 1px solid #333333;">
															<col width="080" align="left">
															<col width="430">
															<tr>
																<td class="stab" rowspan="2">Address:</td>
																<td><ezf:inputText name="firstLineAddr_H1" ezfName="firstLineAddr_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"60\" maxlength=\"60\""/></td>
															</tr>
															<tr>
<!--
																<td class="stab" ></td>
-->
																<td><ezf:inputText name="scdLineAddr_H1" ezfName="scdLineAddr_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"60\" maxlength=\"60\""/></td>
															</tr>
															<tr>
																<td class="stab" ><ezf:anchor name="ctyAddr_H1" ezfName="ctyAddr_H1" ezfEmulateBtn="1" ezfGuard="Click_LinkCity" >City:</ezf:anchor></td>
																<td><ezf:inputText name="ctyAddr_H1" ezfName="ctyAddr_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"25\" maxlength=\"25\""/></td>
															</tr>
															<tr>
																<td class="stab"><ezf:anchor name="cntyNm_H1" ezfName="cntyNm_H1" ezfEmulateBtn="1" ezfGuard="Click_LinkCounty" >County:</ezf:anchor></td>
																<td><ezf:inputText name="cntyNm_H1" ezfName="cntyNm_H1" value="1111111111" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
															</tr>
															<tr>
																<td class="stab"><ezf:anchor name="stCd_H1" ezfName="stCd_H1" ezfEmulateBtn="1" ezfGuard="Click_LinkState" >State:</ezf:anchor></td>
																<td>
																	<ezf:select name="stCd_H1" ezfName="stCd_H1" ezfBlank="1" ezfCodeName="stCd_LC" ezfDispName="stNm_LD" otherAttr=" style=\"width:130px;\""/>
																</td>
															</tr>
															<tr>
																<td class="stab"><ezf:anchor name="postCd_H1" ezfName="postCd_H1" ezfEmulateBtn="1" ezfGuard="Click_LinkPostal" >Postal Code:</ezf:anchor></td>
																<td><ezf:inputText name="postCd_H1" ezfName="postCd_H1" value="1111111111" otherAttr=" size=\"15\" maxlength=\"15\""/></td>
															</tr>
															<tr>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
											<table border="0" cellpadding="2" cellspacing="1">
												<col width="130" align="right">
												<col width="005">
												<col width="380">
												<tr>
													<td  class="pClothBs" align="left"><b>Account Details</b></td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												<tr>
											</table>
											<table border="0" cellpadding="0" cellspacing="0" width="520" >
												<tr valign="top" align="center">
													<td>
														<table border="0" cellpadding="2" cellspacing="1" rules="none" width="520" height="90" style="float:left;padding: 5px; margin-bottom: 5px; border: 1px solid #333333;">
															<col width="130" align="left">
															<col width="180">
															<col width="180">
															<tr>
																<td class="stab" ><ezf:anchor name="dsAcctNum_L1" ezfName="dsAcctNum_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkCustomer" >Customer Number:</ezf:anchor></td>
																<td colspan="2"><ezf:inputText name="dsAcctNum_H1" ezfName="dsAcctNum_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"14\" maxlength=\"14\""/></td>
															</tr>
															<tr>
																<td class="stab" ><ezf:anchor name="dsAcctNm_L1" ezfName="dsAcctNm_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkCustomer" >Customer Name:</ezf:anchor></td>
																<td colspan="2"><ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"50\" maxlength=\"80\""/></td>
															</tr>
															<tr>
																<td class="stab" ><ezf:anchor name="locNum_L1" ezfName="locNum_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkCustomer" >Site Number:</ezf:anchor></td>
																<td><ezf:inputText name="locNum_H1" ezfName="locNum_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"14\" maxlength=\"80\""/></td>
																<td align="right">
																	<ezf:inputButton name="Add" value="Add" htmlClass="pBtn6" />
																	<ezf:inputButton name="Delete" value="Delete" htmlClass="pBtn6" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
											<table border="1" cellpadding="1" cellspacing="0" width="520" style="float:left;padding: 5px; margin-bottom: 5px; border: 1px solid #333333;">
												<tr>
													<td>
														<div id="LeftTable_A_Top" style="overflow-x:none; overflow-y:none; width:495; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" style="word-break:break-all;table-layout: fixed" id="A">
																<col width="025" align="center">
																<col width="120" align="center">
																<col width="230" align="center">
																<col width="120" align="center">
																<tr>
																	<td class="pClothBs">&nbsp;</td>
																	<td class="pClothBs">Customer Number</td>
																	<td class="pClothBs">Customer Name</td>
																	<td class="pClothBs">Site Number</td>
																</tr>
															</table>
														</div>
														<div id="LeftTable_A" style="overflow-y:scroll; height:194; overflow-x:hidden; width:515; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" style="word-break:break-all;table-layout: fixed" id="A">
																<col width="025" align="center">
																<col width="120" align="center">
																<col width="230" align="center">
																<col width="120" align="center">
																<ezf:row ezfHyo="A">
																	<tr id="id_row{EZF_ROW_NUMBER}">
																		<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"4001\" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/></td>
																		<td><ezf:inputText name="dsAcctNum_A1" ezfName="dsAcctNum_A1" value="11111111" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"14\" tabindex=\"-1\" id=\"dsAcctNum_A1#{EZF_ROW_NUMBER}\""/></td>
																		<td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="11111111" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"31\" maxlength=\"80\" tabindex=\"-1\" id=\"dsAcctNm_A1#{EZF_ROW_NUMBER}\""/></td>
																		<td><ezf:inputText name="locNum_A1" ezfName="locNum_A1" value="11111111" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"80\" tabindex=\"-1\" id=\"locNum_A1#{EZF_ROW_NUMBER}\""/></td>
																	</tr>
																</ezf:row>
																<ezf:skip>
																	<tr id="id_row{EZF_ROW_NUMBER}">
																		<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfHyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"/></td>
																		<td><input type="text" size="15" maxlength="14" value="11111111" name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfHyo="A" id="dsAcctNum_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="31" maxlength="80" value="11111111" name="dsAcctNm_A1"  ezfname="dsAcctNm_A1"  ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="15" maxlength="80" value="11111111" name="locNum_A1"    ezfname="locNum_A1"    ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																	</tr>
																	<tr id="id_row{EZF_ROW_NUMBER}">
																		<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfHyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"/></td>
																		<td><input type="text" size="15" maxlength="14" value="11111111" name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfHyo="A" id="dsAcctNum_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="31" maxlength="80" value="11111111" name="dsAcctNm_A1"  ezfname="dsAcctNm_A1"  ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="15" maxlength="80" value="11111111" name="locNum_A1"    ezfname="locNum_A1"    ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																	</tr>
																	<tr id="id_row{EZF_ROW_NUMBER}">
																		<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfHyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"/></td>
																		<td><input type="text" size="15" maxlength="14" value="11111111" name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfHyo="A" id="dsAcctNum_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="31" maxlength="80" value="11111111" name="dsAcctNm_A1"  ezfname="dsAcctNm_A1"  ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="15" maxlength="80" value="11111111" name="locNum_A1"    ezfname="locNum_A1"    ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																	</tr>
																	<tr id="id_row{EZF_ROW_NUMBER}">
																		<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfHyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"/></td>
																		<td><input type="text" size="15" maxlength="14" value="11111111" name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfHyo="A" id="dsAcctNum_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="31" maxlength="80" value="11111111" name="dsAcctNm_A1"  ezfname="dsAcctNm_A1"  ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="15" maxlength="80" value="11111111" name="locNum_A1"    ezfname="locNum_A1"    ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																	</tr>
																	<tr id="id_row{EZF_ROW_NUMBER}">
																		<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfHyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"/></td>
																		<td><input type="text" size="15" maxlength="14" value="11111111" name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfHyo="A" id="dsAcctNum_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="31" maxlength="80" value="11111111" name="dsAcctNm_A1"  ezfname="dsAcctNm_A1"  ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="15" maxlength="80" value="11111111" name="locNum_A1"    ezfname="locNum_A1"    ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																	</tr>
																	<tr id="id_row{EZF_ROW_NUMBER}">
																		<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfHyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"/></td>
																		<td><input type="text" size="15" maxlength="14" value="11111111" name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfHyo="A" id="dsAcctNum_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="31" maxlength="80" value="11111111" name="dsAcctNm_A1"  ezfname="dsAcctNm_A1"  ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="15" maxlength="80" value="11111111" name="locNum_A1"    ezfname="locNum_A1"    ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																	</tr>
																	<tr id="id_row{EZF_ROW_NUMBER}">
																		<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfHyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"/></td>
																		<td><input type="text" size="15" maxlength="14" value="11111111" name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfHyo="A" id="dsAcctNum_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="31" maxlength="80" value="11111111" name="dsAcctNm_A1"  ezfname="dsAcctNm_A1"  ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="15" maxlength="80" value="11111111" name="locNum_A1"    ezfname="locNum_A1"    ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																	</tr>
																	<tr id="id_row{EZF_ROW_NUMBER}">
																		<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfHyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"/></td>
																		<td><input type="text" size="15" maxlength="14" value="11111111" name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfHyo="A" id="dsAcctNum_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="31" maxlength="80" value="11111111" name="dsAcctNm_A1"  ezfname="dsAcctNm_A1"  ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="15" maxlength="80" value="11111111" name="locNum_A1"    ezfname="locNum_A1"    ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																	</tr>
																	<tr id="id_row{EZF_ROW_NUMBER}">
																		<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfHyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"/></td>
																		<td><input type="text" size="15" maxlength="14" value="11111111" name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfHyo="A" id="dsAcctNum_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="31" maxlength="80" value="11111111" name="dsAcctNm_A1"  ezfname="dsAcctNm_A1"  ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="15" maxlength="80" value="11111111" name="locNum_A1"    ezfname="locNum_A1"    ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																	</tr>
																	<tr id="id_row{EZF_ROW_NUMBER}">
																		<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfHyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"/></td>
																		<td><input type="text" size="15" maxlength="14" value="11111111" name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfHyo="A" id="dsAcctNum_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="31" maxlength="80" value="11111111" name="dsAcctNm_A1"  ezfname="dsAcctNm_A1"  ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																		<td><input type="text" size="15" maxlength="80" value="11111111" name="locNum_A1"    ezfname="locNum_A1"    ezfHyo="A" id="dsAcctNm_A1#{EZF_ROW_NUMBER}"></td>
																	</tr>
																</ezf:skip>
															</table>
														</div>
													</td>
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


<%-- **** Task specific area ends here **** --%>
