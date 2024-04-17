<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20191128121915 --%>
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
			<input type="hidden" name="pageID" value="NSAL0720Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Update Bill To">
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
										<li title="Update Bill To" class="pTab_ON"><a href="#">Upd BillTo</a></li>
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
									<col width=" 75">
									<col width="300">
									<col width="300">
									<col width=" 70">
									<col width=" 70">
									<tr>
										<td class="stab"><ezf:anchor name="billToCustCd_AC" ezfName="billToCustCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" >New Bill To</ezf:anchor></td>
										<td colspan="4">
											<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
												<col width=" 65">
												<col width=" 32">
												<col width="250">
												<col width=" 70">
												<tr>
													<td><ezf:inputText name="billToCustCd_H1" ezfName="billToCustCd_H1" otherAttr=" size=\"8\" maxlength=\"20\""/></td>
													<td><ezf:inputButton name="Search_BillTo" value=">>" htmlClass="pBtn0" /></td>
													<td><ezf:inputText name="locNm_H1" ezfName="locNm_H1" otherAttr=" size=\"20\""/></td>
													<td><ezf:inputCheckBox name="xxChkBox_LS" ezfName="xxChkBox_LS" value="Y" />Lease</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td class="stab">&nbsp;</td>
										<td colspan="4"><ezf:inputText name="billToCustLocAddr_H1" ezfName="billToCustLocAddr_H1" otherAttr=" size=\"50\""/></td>
									</tr>
									<tr>
										<td class="stab">Reason Code</td>
										<td><ezf:select name="svcMemoRsnCd_H3" ezfName="svcMemoRsnCd_H3" ezfBlank="1" ezfCodeName="svcMemoRsnCd_H1" ezfDispName="svcMemoRsnNm_H2" /></td>
										<td>&nbsp;</td>
										<td><ezf:inputCheckBox name="xxChkBox_BS" ezfName="xxChkBox_BS" value="Y" onClick="sendServer('CheckAllBase')" />Base</td>
										<td><ezf:inputCheckBox name="xxChkBox_OR" ezfName="xxChkBox_OR" value="Y" onClick="sendServer('CheckAllOverage')" />Overage</td>
									</tr>
									<tr>
										<td class="stab">Notes</td>
										<td colspan="2"><ezf:textArea name="svcCmntTxt_H1" ezfName="svcCmntTxt_H1" otherAttr=" cols=\"80\" rows=\"4\""/></td>
										<td colspan="2"><ezf:inputButton name="ApplyToAll" value="Apply To All" htmlClass="pBtn8" /></td>
									</tr>
								</table>
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
									<col width=" 25" align="center"><!-- Expansion/Contraction -->
									<col width="100" align="center"><!-- Contract# -->
									<col width=" 24" align="center"><!-- CheckBox -->
									<col width=" 80" align="center"><!-- Serial# -->
									<col width=" 24" align="center"><!-- CheckBox -->
									<col width="145" align="center"><!-- Base/Overage -->
									<col width="225" align="center"><!-- Bill To -->
									<col width="285" align="center"><!-- New Bill To -->
									<col width="200" align="center"><!-- Return Message -->
									<tr>
										<td class="pClothBs">&nbsp;</td>
										<td class="pClothBs">Contract#</td>
										<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_AL" ezfName="xxChkBox_AL" value="Y" onClick="sendServer('SelectDtlAll')" /></td>
										<td class="pClothBs">Serial#</td>
										<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_BL" ezfName="xxChkBox_BL" value="Y" onClick="sendServer('SelectBllgMtrAll')" /></td>
										<td class="pClothBs">Base/Overage</td>
										<td class="pClothBs">Bill To</td>
										<td class="pClothBs">New Bill To</td>
										<td class="pClothBs">Return Message</td>
									</tr>
								</table>
								<div style="width:1089; height:370; display:block; overflow-x:none; overflow-y:scroll;">
									<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width=" 25" align="center"><!-- Expansion/Contraction -->
										<col width="100" align="center"><!-- Contract# -->
										<col width=" 24" align="center"><!-- CheckBox -->
										<col width=" 80" align="center"><!-- Serial# -->
										<col width=" 24" align="center"><!-- CheckBox -->
										<col width="145" align="center"><!-- Base/Overage -->
										<col width="225" align="center"><!-- Bill To -->
										<col width="285" align="center"><!-- New Bill To -->
										<col width="200" align="center"><!-- Return Message -->
										<%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%>
										<%@ page import="java.math.BigDecimal" %>
										<%@ page import="business.servlet.NSAL0720.NSAL0720BMsg" %>
										<%@ page import="business.servlet.NSAL0720.NSAL0720_ABMsg" %>
										<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
										<% NSAL0720BMsg bMsg = (NSAL0720BMsg)databean.getEZDBMsg(); %>
										<% BigDecimal preDsContrPk = BigDecimal.ZERO; %>
										<% int index = -1; %>
										<ezf:row ezfHyo="A">
											<tr height="25">
												<% index++; %>
												<% NSAL0720_ABMsg abMsg = bMsg.A.no(index); %>
												<td>
													<% boolean isDplyCtrlFlg = "Y".equals(abMsg.xxDplyCtrlFlg.getValue()); %>
													<% if(!isDplyCtrlFlg) out.println("<span style='visibility:hidden'>"); %>
													<% boolean isSmryLineFlg = "Y".equals(abMsg.xxSmryLineFlg.getValue()); %>
													<a id="xxSmryLineFlg#{EZF_ROW_NUMBER}" href="#" onclick="clickImg(this, '{EZF_ROW_NUMBER}'); return false;">
														<% if(isSmryLineFlg)  out.println("<img src=\"./img/biz/rightarrow2.png\"  value=\"Expansion\">"); %>
														<% if(!isSmryLineFlg) out.println("<img src=\"./img/biz/downarrow2.png\" value=\"Contraction\">"); %>
													</a>
													<% if(!isDplyCtrlFlg) out.println("</span>"); %>
												</td>
												<td><ezf:inputText name="xxScrItem34Txt_A1" ezfName="xxScrItem34Txt_A1" value="NFL-12038992" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"12\""/></td>
												<% if( ZYPCommonFunc.hasValue(bMsg.A.no( index ).xxScrItem34Txt_A1.getValue()) 
													||  ZYPCommonFunc.hasValue(bMsg.A.no( index ).serNum_A1.getValue())
													||  !ZYPCommonFunc.hasValue(bMsg.A.no( index ).mtrLbDescTxt_A1.getValue())) { %>
													<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="${idx}" /></td>
												<% } else { %>
													<td>&nbsp;<ezf:inputHidden name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="${idx}" /></td>
												<% } %>
												<td><ezf:inputText name="serNum_A1" ezfName="serNum_A1" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"10\""/></td>
												<% if( ZYPCommonFunc.hasValue(bMsg.A.no( index ).mtrLbDescTxt_A1.getValue()) ) { %>
													<td><ezf:inputCheckBox name="xxChkBox_A2" ezfName="xxChkBox_A2" value="Y" ezfHyo="A" ezfArrayNo="${idx}" /></td>
												<% } else { %>
													<td>&nbsp;<ezf:inputHidden name="xxChkBox_A2" ezfName="xxChkBox_A2" value="Y" ezfHyo="A" ezfArrayNo="${idx}" /></td>
												<% } %>
												<td><ezf:inputText name="mtrLbDescTxt_A1" ezfName="mtrLbDescTxt_A1" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"19\""/></td>
												<td><ezf:inputText name="billToCustLocAddr_A1" ezfName="billToCustLocAddr_A1" value="1901 MADISON ST, CHICAGO, IL 60612" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"30\""/></td>
												<td><ezf:inputText name="billToCustLocAddr_A2" ezfName="billToCustLocAddr_A2" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"30\""/>
												    <ezf:inputButton name="OpenWin_BillTo" value="Bill To" ezfHyo="A" ezfArrayNo="${idx}" htmlClass="pBtn2" /></td>
												<td><ezf:inputText name="xxGenlFldAreaTxt_A1" ezfName="xxGenlFldAreaTxt_A1" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"27\""/></td>
                                                <td class="pAuditInfo">
                                                    <ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A\""/>
                                                    <ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A\""/>
                                                    <ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A\""/>
                                                    <ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A\""/>
                                                    <ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A\""/>
                                                </td>
											</tr>
											<% preDsContrPk = abMsg.dsContrPk_A1.getValue(); %>
										</ezf:row>
										<%-- +++++ [END] Programming JSP for Nesting Records Table +++++ --%>
										<ezf:skip>
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
