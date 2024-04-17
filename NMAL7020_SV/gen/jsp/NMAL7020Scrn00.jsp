<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180824144816 --%>
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
			<input type="hidden" name="pageID" value="NMAL7020Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Copy Price List">

			<center>
				<table width="1133" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<%-- ###TAB - HEAD --%>
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="Copy Price List" class="pTab_ON"><a href="#">Copy Price List</a></li>
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
							<div class="pTab_BODY_In">
								<table border="0" cellpadding="0" cellspacing="0" height="575">
									<tr>
										<td valign="top">
										</td>
										<td valign="top" width="780">
											<table cellpadding="0" border="0">
												<col align="left" width="180">
												<col align="left" width="360">
												<col align="left" width="240">
												<tr>
													<td class="stab">
														<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_PriceList" >
															From Price List
														</ezf:anchor>
													</td>
													<td><ezf:inputText name="prcCatgNm_O" ezfName="prcCatgNm_O" value="wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" otherAttr=" size=\"75\" maxlength=\"75\" ezftoupper=\"\""/></td>
													<td class="stab">Active <ezf:inputCheckBox name="actvFlg" ezfName="actvFlg" value="Y" /></td>
												</tr>
												<tr>
													<td class="stab">New Price List Name</td>
													<td><ezf:inputText name="prcCatgNm_N" ezfName="prcCatgNm_N" value="wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" otherAttr=" size=\"75\" maxlength=\"75\" ezftoupper=\"\""/></td>
													<td><br></td>
												</tr>
												<tr>
													<td class="stab">Comments/Notes</td>
													<td><ezf:inputText name="newPrcCatgDescTxt" ezfName="newPrcCatgDescTxt" value="wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
													<td><br></td>
												</tr>
												<tr>
													<td><br></td>
													<td><br></td>
													<td><br></td>
												</tr>
												<tr>
													<td class="stab">Copy main Price List Information</td>
													<td><ezf:inputCheckBox name="mainPrcListInfoFlg" ezfName="mainPrcListInfoFlg" value="Y" /></td>
													<td><br></td>
												</tr>
												<tr>
													<td class="stab">Copy Price List Access Types</td>
													<td><ezf:inputCheckBox name="prcListAccsTpFlg" ezfName="prcListAccsTpFlg" value="Y" /></td>
													<td><br></td>
												</tr>
												<tr>
													<td class="stab">Copy Contract Attributes/Transaction Drivers</td>
													<td><ezf:inputCheckBox name="copyAttrbTrxFlg" ezfName="copyAttrbTrxFlg" value="Y" /></td>
													<td><br></td>
												</tr>
												<tr>
													<td class="stab">Copy Pricing</td>
													<td><ezf:inputCheckBox name="copyPrcFlg" ezfName="copyPrcFlg" value="Y" /></td>
													<td><br></td>
												</tr>
												<tr>
													<td><br></td>
													<td><br></td>
													<td><br></td>
												</tr>
												<tr>
													<td class="stab">Apply Price Adjustment</td>
													<td colspan="2">
														<ezf:select name="prcCalcTpCd" ezfName="prcCalcTpCd" ezfBlank="1" ezfCodeName="prcCalcTpCd_P" ezfDispName="prcCalcTpDescTxt_P" otherAttr=" style=\"width:105;\""/>
														<ezf:select name="prcPctAmtTpCd" ezfName="prcPctAmtTpCd" ezfBlank="1" ezfCodeName="prcPctAmtTpCd_P" ezfDispName="prcPctAmtTpDescTxt_P" otherAttr=" style=\"width:80;\""/>
														<ezf:inputText name="copyPrcAmtRate" ezfName="copyPrcAmtRate" value="1,234,567,890,123.123456" otherAttr=" size=\"24\" maxlength=\"24\" ezftoupper=\"\""/>
														(round)
													</td>
												</tr>
												
												<tr>
													<td><br></td>
													<td><br></td>
													<td><br></td>
												</tr>
												<tr>
													<td class="stab">New Effective Dates</td>
													<td><br></td>
													<td><br></td>
												</tr>
												<tr>
													<td class="stab">Effective From</td>
													<td>
														<ezf:inputText name="effFromDt" ezfName="effFromDt" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);" >
													</td>
													<td><br></td>
												</tr>
												<tr>
													<td class="stab">Effective To</td>
													<td>
														<ezf:inputText name="effThruDt" ezfName="effThruDt" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);" >
													</td>
													<td><br></td>
												</tr>
												<tr>
													<td class="stab">Apply To</td>
													<td colspan="1">
														<ezf:select name="effApplyLvlTpCd" ezfName="effApplyLvlTpCd" ezfBlank="1" ezfCodeName="effApplyLvlTpCd_P" ezfDispName="effApplyLvlTpDescTxt_P" otherAttr=" style=\"width:155;\""/>
													</td>
													<td><br></td>
												</tr>
											</table>
											<table cellpadding="0" border="0">
												<tr>
													<td style="word-break: break-all;width:600;">
													<!--
														<input type="text" value="* Note: If new effective dates are not provided, the original dates in the From Price List Will default as the effective date." name="varCharConstVal" ezfName="varCharConstVal" size="150" maxlength="1000" style="border:none;background-color:transparent;padding:0px;"/>
														-->
														<ezf:label name="varCharConstVal_A" ezfName="varCharConstVal_A"   />
														<br>
														<ezf:label name="varCharConstVal_B" ezfName="varCharConstVal_B"   />
													</td>
												</tr>
												<tr>
													<td colspan="3">
														<ezf:inputButton name="OpenWin_RequestHistory" value="Request History" htmlClass="pBtn9" />
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
