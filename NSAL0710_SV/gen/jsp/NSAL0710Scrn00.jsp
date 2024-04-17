<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170310140608 --%>
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
			<input type="hidden" name="pageID" value="NSAL0710Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Update Read Methods">
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
										<li title="Update Read Methods" class="pTab_ON"><a href="#">Upd Rd Meth</a></li>
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
									<col width="600">
									<tr>
										<td class="stab">New Read Method</td>
										<td><ezf:select name="mtrReadMethCd_H3" ezfName="mtrReadMethCd_H3" ezfBlank="1" ezfCodeName="mtrReadMethCd_H1" ezfDispName="mtrReadMethNm_H2" /></td>
									</tr>
									<tr>
										<td class="stab">Reason Code</td>
										<td><ezf:select name="svcMemoRsnCd_H3" ezfName="svcMemoRsnCd_H3" ezfBlank="1" ezfCodeName="svcMemoRsnCd_H1" ezfDispName="svcMemoRsnNm_H2" /></td>
									</tr>
									<tr>
										<td class="stab">Notes</td>
										<td><ezf:textArea name="svcCmntTxt_H1" ezfName="svcCmntTxt_H1" otherAttr=" cols=\"80\" rows=\"4\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						
						<tr>
							<td>
								<hr/>
							</td>
						</tr>
					</table>
<%-- #################################################### DETAIL ################################################### --%>
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
									<col width=" 24" align="center"><!-- CheckBox -->
									<col width=" 95" align="center"><!-- Contract# -->
									<col width="100" align="center"><!-- Customer Name -->
									<col width=" 80" align="center"><!-- Start Date -->
									<col width=" 80" align="center"><!-- End Date -->
									<col width=" 80" align="center"><!-- Status -->
									<col width=" 24" align="center"><!-- CheckBox -->
									<col width=" 80" align="center"><!-- Serial# -->
									<col width=" 80" align="center"><!-- Model -->
									<col width=" 38" align="center"><!-- Phone -->
									<col width=" 35" align="center"><!-- Emanage -->
									<col width=" 35" align="center"><!-- Email -->
									<col width=" 30" align="center"><!-- Fax -->
									<col width=" 30" align="center"><!-- Imageware -->
									<col width=" 80" align="center"><!-- Read Method -->
									<col width=" 80" align="center"><!-- New Read Method -->
									<col width="130" align="center"><!-- Return Message -->
									<tr height=" 35">
										<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_AL" ezfName="xxChkBox_AL" value="Y" onClick="sendServer('SelectAllHdr')" /></td>
										<td class="pClothBs">Contract#</td>
										<td class="pClothBs">Customer Name</td>
										<td class="pClothBs">Start Date</td>
										<td class="pClothBs">End Date</td>
										<td class="pClothBs">Status</td>
										<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_BL" ezfName="xxChkBox_BL" value="Y" onClick="sendServer('SelectAllDtl')" /></td>
										<td class="pClothBs">Serial#</td>
										<td class="pClothBs">Model</td>
										<td class="pClothBs">Phone</td>
										<td class="pClothBs">Emng</td>
										<td class="pClothBs">Email</td>
										<td class="pClothBs">Fax</td>
										<td class="pClothBs">IW</td>
										<td class="pClothBs">Read Method</td>
										<td class="pClothBs">New Read Method</td>
										<td class="pClothBs">Return Message</td>
									</tr>
								</table>
								<div style="width:1120; height:365; display:block; overflow-x:none; overflow-y:scroll;">
									<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width=" 24" align="left"><!-- CheckBox -->
										<col width=" 95" align="center"><!-- Contract# -->
										<col width="100" align="center"><!-- Customer Name -->
										<col width=" 80" align="center"><!-- Start Date -->
										<col width=" 80" align="center"><!-- End Date -->
										<col width=" 80" align="center"><!-- Status -->
										<col width=" 24" align="left"><!-- CheckBox -->
										<col width=" 80" align="center"><!-- Serial# -->
										<col width=" 80" align="center"><!-- Model -->
										<col width=" 38" align="center"><!-- Phone -->
										<col width=" 35" align="center"><!-- Emanage -->
										<col width=" 35" align="center"><!-- Email -->
										<col width=" 30" align="center"><!-- Fax -->
										<col width=" 30" align="center"><!-- Imageware -->
										<col width=" 80" align="center"><!-- Read Method -->
										<col width=" 80" align="center"><!-- New Read Method -->
										<col width="130" align="center"><!-- Return Message -->
										<%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%>
										<%@ page import="business.servlet.NSAL0710.NSAL0710BMsg" %>
										<%@ page import="java.math.BigDecimal" %>
										<% NSAL0710BMsg bMsg = (NSAL0710BMsg)databean.getEZDBMsg(); %>
										<% BigDecimal dsContrPk_Pre = bMsg.dsContrPk_PR.getValue(); %>
										<% BigDecimal dsContrPk     = BigDecimal.ZERO; %>
										
										<% int index = -1; %>
										<ezf:row ezfHyo="A">
										<% index++; %>
											<tr height="25">
												<% dsContrPk = bMsg.A.no( index ).dsContrPk_A1.getValue(); %>
												<% if( !dsContrPk_Pre.equals( dsContrPk ) ) { %>
													<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:inputText name="xxScrItem34Txt_A1" ezfName="xxScrItem34Txt_A1" value="NFL-12038992" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
													<td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="CHICAGO BULLS" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
													<td><ezf:inputText name="contrVrsnEffFromDt_A1" ezfName="contrVrsnEffFromDt_A1" value="01/01/2014" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
													<td><ezf:inputText name="contrVrsnEffThruDt_A1" ezfName="contrVrsnEffThruDt_A1" value="12/31/2014" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
												<% } else { %>
													<td>&nbsp;<ezf:inputHidden name="xxChkBox_A1" ezfName="xxChkBox_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/></td>
													<td>&nbsp;<ezf:inputHidden name="xxScrItem34Txt_A1" ezfName="xxScrItem34Txt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td>&nbsp;<ezf:inputHidden name="dsAcctNm_A1" ezfName="dsAcctNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td>&nbsp;<ezf:inputHidden name="contrVrsnEffFromDt_A1" ezfName="contrVrsnEffFromDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td>&nbsp;<ezf:inputHidden name="contrVrsnEffThruDt_A1" ezfName="contrVrsnEffThruDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
												<% } %>
												<td><ezf:inputText name="dsContrCtrlStsNm_A1" ezfName="dsContrCtrlStsNm_A1" value="ACTIVE" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
												<% if( !dsContrPk_Pre.equals( dsContrPk ) ) { %>
													<td>&nbsp;<ezf:inputHidden name="xxChkBox_A2" ezfName="xxChkBox_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/></td>
												<% } else { %>
													<td><ezf:inputCheckBox name="xxChkBox_A2" ezfName="xxChkBox_A2" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/></td>
												<% } %>
												<td><ezf:inputText name="serNum_A1" ezfName="serNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
												<td><ezf:inputText name="t_MdlNm_A1" ezfName="t_MdlNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
												<td><ezf:inputText name="xxDplyCtrlFlg_A1" ezfName="xxDplyCtrlFlg_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"1\""/></td>
												<td><ezf:inputText name="xxDplyCtrlFlg_A2" ezfName="xxDplyCtrlFlg_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"1\""/></td>
												<td><ezf:inputText name="xxDplyCtrlFlg_A3" ezfName="xxDplyCtrlFlg_A3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"1\""/></td>
												<td><ezf:inputText name="xxDplyCtrlFlg_A4" ezfName="xxDplyCtrlFlg_A4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"1\""/></td>
												<td><ezf:inputText name="xxDplyCtrlFlg_A5" ezfName="xxDplyCtrlFlg_A5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"1\""/></td>
												<td><ezf:inputText name="mtrReadMethNm_A1" ezfName="mtrReadMethNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
												<td><ezf:inputText name="mtrReadMethNm_A2" ezfName="mtrReadMethNm_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
												<td><ezf:inputText name="xxGenlFldAreaTxt_A1" ezfName="xxGenlFldAreaTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\""/></td>
                                                <td class="pAuditInfo">
                                                    <ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A\""/>
                                                    <ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A\""/>
                                                    <ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A\""/>
                                                    <ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A\""/>
                                                    <ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A\""/>
                                                </td>
											</tr>
											<% dsContrPk_Pre = dsContrPk; %>
										</ezf:row>
										<%-- +++++ [END] Programming JSP for Nesting Records Table +++++ --%>
										<ezf:skip>

											<tr height="25">
												<td>&nbsp;</td>
												<td><input type="text" class="pPro" size="12" value="" name="xxScrItem34Txt_A1" ezfname="xxScrItem34Txt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="13" value="" name="dsAcctNm_A1" ezfname="dsAcctNm_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="" name="contrVrsnEffFromDt_A1" ezfname="contrVrsnEffFromDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="" name="contrVrsnEffThruDt_A1" ezfname="contrVrsnEffThruDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="ACTIVE" name="dsContrCtrlStsNm_A1" ezfname="dsContrCtrlStsNm_A1" ezfhyo="A"></td>
												<td><input type="checkbox" value="Y" name="xxChkBox_A2" ezfname="xxChkBox_A2"  ezfArrayNo="${index}" ezfhyo="A" id="xxChkBox_A2#{EZF_ROW_NUMBER}" checked></td>
												<td><input type="text" class="pPro" size="10" value="MSK101010" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="ADVC5055" name="tMdlNm_A1" ezfname="tMdlNm_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="1" value="Y" name="phone_A1" ezfname="phone_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="1" value="Y" name="eManage_A1" ezfname="eManage_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="1" value="Y" name="email_A1" ezfname="email_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="1" value="Y" name="fax_A1" ezfname="fax_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="1" value="Y" name="imageware_A1" ezfname="imageware_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="Phone" name="mtrReadMethNm_A1" ezfname="mtrReadMethNm_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="" name="mtrReadMethNm_A2" ezfname="mtrReadMethNm_A2" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="17" value="" name="msgTxt_A1" ezfname="msgTxt_A1" ezfhyo="A"></td>
											</tr>
											<tr height="25">
												<td>&nbsp;</td>
												<td><input type="text" class="pPro" size="12" value="" name="xxScrItem34Txt_A1" ezfname="xxScrItem34Txt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="13" value="" name="dsAcctNm_A1" ezfname="dsAcctNm_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="" name="contrVrsnEffFromDt_A1" ezfname="contrVrsnEffFromDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="" name="contrVrsnEffThruDt_A1" ezfname="contrVrsnEffThruDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="ACTIVE" name="dsContrCtrlStsNm_A1" ezfname="dsContrCtrlStsNm_A1" ezfhyo="A"></td>
												<td><input type="checkbox" value="Y" name="xxChkBox_A2" ezfname="xxChkBox_A2"  ezfArrayNo="${index}" ezfhyo="A" id="xxChkBox_A2#{EZF_ROW_NUMBER}" checked></td>
												<td><input type="text" class="pPro" size="10" value="MNB05459" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="ADVC5055" name="tMdlNm_A1" ezfname="tMdlNm_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="1" value="Y" name="phone_A1" ezfname="phone_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="1" value="Y" name="eManage_A1" ezfname="eManage_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="1" value="Y" name="email_A1" ezfname="email_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="1" value="Y" name="fax_A1" ezfname="fax_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="1" value="Y" name="imageware_A1" ezfname="imageware_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="Phone" name="mtrReadMethNm_A1" ezfname="mtrReadMethNm_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="" name="mtrReadMethNm_A2" ezfname="mtrReadMethNm_A2" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="17" value="" name="msgTxt_A1" ezfname="msgTxt_A1" ezfhyo="A"></td>
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


<%-- **** Task specific area ends here **** --%>
