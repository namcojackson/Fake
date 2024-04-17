<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20150827152445 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- I18N START --%>
<%@ page import="parts.i18n.*" %>

<%	pageContext.setAttribute("ezdi18nlocale", EZDI18NContext.getInstance().getI18NAccessor().getLocale()); %>
<fmt:setLocale value="${ezdi18nlocale}" scope="request" />
<fmt:setBundle basename="I18N_NPAL1240Scrn00" var="I18N_SCREEN_ID" scope="request" />
<fmt:setBundle basename="I18N" var="I18N_DEFAULT" scope="request" />
<%-- I18N END --%>

<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NPAL1240Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NPAL1240Scrn00.title" bundle="${I18N_SCREEN_ID}">Qualifier Setup</fmt:message>">

			<center>
				<table border="0" cellpadding="0" cellspacing="0" style="margin-top:40px;">
					<%-- ========== DETAIL TABLE ========== --%>
					<tr>
						<td>
							<%-- TABLE HEADER --%>
							<div style="overflow-x:hidden; overflow-y:none; width:671px;">
								<table border="1" cellpadding="1" cellspacing="0" width="654px">
									<col width="40px"  align="center"><%-- check box --%>
									<col width="320px" align="center"><%-- Qualifier Type --%>
									<col width="50px"  align="center"><%-- Ref# Button --%>
									<col width="220px" align="center"><%-- Reference# --%>
									<tr height="28px">
										<td class="pClothBs">&nbsp;</td>
										<td class="pClothBs"><fmt:message key="i18n.NPAL1240Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Qualifier Type</fmt:message></td>
										<td class="pClothBs">&nbsp;</td>
										<td class="pClothBs"><fmt:message key="i18n.NPAL1240Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Reference#</fmt:message></td>
									</tr>
								</table>
							</div>
							<%-- TABLE BODY --%>
							<div style="overflow-y:auto; height:440px;">
								<table border="1" cellpadding="1" cellspacing="0" width="654px">
									<col width="40px"  align="center"><%-- check box --%>
									<col width="320px" align="center"><%-- Qualifier Type --%>
									<col width="50px"  align="center"><%-- Ref# Button --%>
									<col width="220px" align="center"><%-- Reference# --%>
									<%-- 1 --%>
									<ezf:row ezfHyo="A">
									<tr height="28px">
										<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
                                        <td>
                                            <ezf:select name="aslQlfyTpCd_A" ezfName="aslQlfyTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="aslQlfyTpCd_L" ezfDispName="aslQlfyTpDescTxt_L" otherAttr=" style=\"width:320px;\" ezftoupper=\"\""/>
                                        </td>
										<td><ezf:inputButton name="OnClick_RefNum" value="Ref#" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn3" /></td>
										<td><ezf:inputText name="aslQlfyRefCd_A" ezfName="aslQlfyRefCd_A" value="1330700" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" ezftoupper=\"\""/></td>
									</tr>
									</ezf:row>
									<%-- 2 --%>
									<ezf:skip>
									<tr height="28px">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
                                        <td>
                                            <select style="width:320px;" name="aslQlfyTpCd_A" ezfname="aslQlfyTpCd_A" ezflist="aslQlfyTpCd_L,aslQlfyTpDescTxt_L,99, ,noblank" ezfhyo="A"  ezftoupper class="pHsu">
                                                <option></option>
                                                <option selected>CUSTOMER SPECIFIC</option>
                                                <option>BIG DEAL SPECIFIC</option>
                                            </select>
                                        </td>
										<td><input type="button" class="pBtn3" value="Ref#" onclick="sendServer(this)" name="OnClick_RefNum" ezfhyo="A"></td>
										<td><input type="text" size="30" value="1321281"  ezftoupper class="pHsu" name="aslQlfyRefCd_A" ezfname="aslQlfyRefCd_A" ezfhyo="A"></td>
									</tr>
									<%-- 3 --%>
									<tr height="28px">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
                                        <td>
                                            <select style="width:320px;" name="aslQlfyTpCd_A" ezfname="aslQlfyTpCd_A" ezflist="aslQlfyTpCd_L,aslQlfyTpDescTxt_L,99, ,noblank" ezfhyo="A"  ezftoupper class="pHsu">
                                                <option></option>
                                                <option selected>CUSTOMER SPECIFIC</option>
                                                <option>BIG DEAL SPECIFIC</option>
                                            </select>
                                        </td>
										<td><input type="button" class="pBtn3" value="Ref#" onclick="sendServer(this)" name="OnClick_RefNum" ezfhyo="A"></td>
										<td><input type="text" size="30" value="1104360" ezftoupper class="pHsu" name="aslQlfyRefCd_A" ezfname="aslQlfyRefCd_A" ezfhyo="A"></td>
									</tr>
									<%-- 4 --%>
									<tr height="28px">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
                                        <td>
                                            <select style="width:320px;" name="aslQlfyTpCd_A" ezfname="aslQlfyTpCd_A" ezflist="aslQlfyTpCd_L,aslQlfyTpDescTxt_L,99, ,noblank" ezfhyo="A"  ezftoupper class="pHsu">
                                                <option></option>
                                                <option>CUSTOMER SPECIFIC</option>
                                                <option selected>BIG DEAL SPECIFIC</option>
                                            </select>
                                        </td>
										<td><input type="button" class="pBtn3" value="Ref#" onclick="sendServer(this)" name="OnClick_RefNum" ezfhyo="A"></td>
										<td><input type="text" size="30" value="857937" ezftoupper class="pHsu" name="aslQlfyRefCd_A" ezfname="aslQlfyRefCd_A" ezfhyo="A"></td>
									</tr>
									<%-- 5 --%>
									<tr height="28px">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
                                        <td>
                                            <select style="width:320px;" name="aslQlfyTpCd_A" ezfname="aslQlfyTpCd_A" ezflist="aslQlfyTpCd_L,aslQlfyTpDescTxt_L,99, ,noblank" ezfhyo="A"  ezftoupper class="pHsu">
                                                <option></option>
                                                <option>CUSTOMER SPECIFIC</option>
                                                <option selected>BIG DEAL SPECIFIC</option>
                                            </select>
                                        </td>
										<td><input type="button" class="pBtn3" value="Ref#" onclick="sendServer(this)" name="OnClick_RefNum" ezfhyo="A"></td>
										<td><input type="text" size="30" value="859119" ezftoupper class="pHsu" name="aslQlfyRefCd_A" ezfname="aslQlfyRefCd_A" ezfhyo="A"></td>
									</tr>
									<%-- 6 --%>
									<tr height="28px">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
                                        <td>
                                            <select style="width:320px;" name="aslQlfyTpCd_A" ezfname="aslQlfyTpCd_A" ezflist="aslQlfyTpCd_L,aslQlfyTpDescTxt_L,99, ,noblank" ezfhyo="A"  ezftoupper class="pHsu">
                                                <option></option>
                                                <option>CUSTOMER SPECIFIC</option>
                                                <option selected>BIG DEAL SPECIFIC</option>
                                            </select>
                                        </td>
										<td><input type="button" class="pBtn3" value="Ref#" onclick="sendServer(this)" name="OnClick_RefNum" ezfhyo="A"></td>
										<td><input type="text" size="30" value="861230" ezftoupper class="pHsu" name="aslQlfyRefCd_A" ezfname="aslQlfyRefCd_A" ezfhyo="A"></td>
									</tr>
									<%-- 7 --%>
									<tr height="28px">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
                                        <td>
                                            <select style="width:320px;" name="aslQlfyTpCd_A" ezfname="aslQlfyTpCd_A" ezflist="aslQlfyTpCd_L,aslQlfyTpDescTxt_L,99, ,noblank" ezfhyo="A"  ezftoupper class="pHsu">
                                                <option></option>
                                                <option>CUSTOMER SPECIFIC</option>
                                                <option selected>BIG DEAL SPECIFIC</option>
                                            </select>
                                        </td>
										<td><input type="button" class="pBtn3" value="Ref#" name="OnClick_RefNum" onclick="sendServer(this)" ezfhyo="A"></td>
										<td><input type="text" size="30" value="862341" ezftoupper class="pHsu" name="aslQlfyRefCd_A" ezfname="aslQlfyRefCd_A" ezfhyo="A"></td>
									</tr>
									<%-- 8 --%>
									<tr height="28px">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
                                        <td>
                                            <select style="width:320px;" name="aslQlfyTpCd_A" ezfname="aslQlfyTpCd_A" ezflist="aslQlfyTpCd_L,aslQlfyTpDescTxt_L,99, ,noblank" ezfhyo="A" ezftoupper class="pHsu">
                                                <option></option>
                                                <option>CUSTOMER SPECIFIC</option>
                                                <option selected>BIG DEAL SPECIFIC</option>
                                            </select>
                                        </td>
										<td><input type="button" class="pBtn3" value="Ref#" name="OnClick_RefNum" onclick="sendServer(this)" ezfhyo="A"></td>
										<td><input type="text" size="30" value="862391" ezftoupper class="pHsu" name="aslQlfyRefCd_A" ezfname="aslQlfyRefCd_A" ezfhyo="A"></td>
									</tr>
									<%-- 9 --%>
									<tr height="28px">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
                                        <td>
                                            <select style="width:320px;" name="aslQlfyTpCd_A" ezfname="aslQlfyTpCd_A" ezflist="aslQlfyTpCd_L,aslQlfyTpDescTxt_L,99, ,noblank" ezfhyo="A" ezftoupper class="pHsu">
                                                <option></option>
                                                <option>CUSTOMER SPECIFIC</option>
                                                <option selected>BIG DEAL SPECIFIC</option>
                                            </select>
                                        </td>
										<td><input type="button" class="pBtn3" value="Ref#" name="OnClick_RefNum" onclick="sendServer(this)" ezfhyo="A"></td>
										<td><input type="text" size="30" value="862440" ezftoupper class="pHsu" name="aslQlfyRefCd_A" ezfname="aslQlfyRefCd_A" ezfhyo="A"></td>
									</tr>
									<%-- 10 --%>
									<tr height="28px">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
                                        <td>
                                            <select style="width:320px;" name="aslQlfyTpCd_A" ezfname="aslQlfyTpCd_A" ezflist="aslQlfyTpCd_L,aslQlfyTpDescTxt_L,99, ,noblank" ezfhyo="A" ezftoupper class="pHsu">
                                                <option></option>
                                                <option>CUSTOMER SPECIFIC</option>
                                                <option selected>BIG DEAL SPECIFIC</option>
                                            </select>
                                        </td>
										<td><input type="button" class="pBtn3" value="Ref#" name="OnClick_RefNum" onclick="sendServer(this)" ezfhyo="A"></td>
										<td><input type="text" size="30" value="862883"  ezftoupper class="pHsu" name="aslQlfyRefCd_A" ezfname="aslQlfyRefCd_A" ezfhyo="A"></td>
									</tr>
									</ezf:skip>
								</table>
							</div>
						</td>
					</tr>
					<%-- ========== BUTTON AREA ========== --%>
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0" width="654px">
								<col width="" align="right">
								<tr>
									<td>
										<ezf:inputButton name="OnClick_InsertRow" value="Insert Row" htmlClass="pBtn6" />
										<ezf:inputButton name="OnClick_DeleteRow" value="Delete Row" htmlClass="pBtn6" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</center>


<%-- **** Task specific area ends here **** --%>
