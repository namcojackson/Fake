<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160720084950 --%>
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
			<input type="hidden" name="pageID" value="NYEL8860Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Group Name Search">

			<center>
				<table width="100%" >
					<!-- ######################################## HEADER ######################################## -->
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="2">
								<col width="160">
								<col width="310">
								<col width="310">
								<tr>
									<td class="stab">
										<label>Type</label>
									</td>
									<td>
										<ezf:select name="xxGrpFlg" ezfName="xxGrpFlg" ezfCodeName="xxGrpFlg_L" ezfDispName="xxCondNm_D" otherAttr=" style=\"width: 70px\" id=\"\""/>
									</td>
									<td>
									</td>
								</tr>
								<tr>
									<td class="stab">
										<label>Id(*)</label>
									</td>
									<td>
										<ezf:inputText name="wfUsrGrpNm" ezfName="wfUsrGrpNm" value="01234567890123456789" otherAttr=" size=\"20\" maxlength=\"20\""/>
									</td>
									<td>
										
									</td>
								</tr>
								<tr>
									<td class="stab">
										<label>Name(*)</label>
									</td>
									<td>
										<ezf:inputText name="xxDtlNm" ezfName="xxDtlNm" value="012345678901234567890123456789012345678901234567890123456789" otherAttr=" size=\"50\" maxlength=\"20\""/>
									</td>
									<td>
										
									</td>
								</tr>
								<tr>
									<td class="stab">
										
									</td>
									<td>
										
									</td>
									<td align="right">
										<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
									</td>
								</tr>
							</table>
							<hr />
							<table border="0" cellpadding="3" cellspacing="0" width="100%">
								<tr align="right">
									<td>
										<%-- Pagenation --%>
										<table border="0" cellpadding="1" cellspacing="0" width="95%">
											<tr>
												<td align="right">
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
			                <table border="1" cellpadding="1" cellspacing="0" width="777">
			                	<col width="40"  align="center">
			                    <col width="190"  align="center">
			                    <col width="547" align="center">
			                    <tr>
			                    	<td class="pClothBs">Type</td>
			                        <td class="pClothBs">Id</td>
			                        <td class="pClothBs">Name</td>
			                    </tr>
			                </table>
			                
			                <div style="overflow:auto; height:402;"  width="100%">
			                    <table border="1" cellpadding="1" cellspacing="0" id="A"  width="777">
			                    <col width="40"  align="center">
			                    <col width="190"  align="center">
			                    <col width="547" align="center">
			                        <tbody>
			                            <ezf:row ezfHyo="A">
			                                <tr id="id_row{EZF_ROW_NUMBER}">
			                                	<td><ezf:label name="xxCondNm_A" ezfName="xxCondNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
			                                    <td align="left"><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="CloseWin" otherAttr=" ezfanchortext=\"\""> <ezf:label name="wfUsrGrpNm_A" ezfName="wfUsrGrpNm_A" ezfHyo="A" ezfArrayNo="0" /> </ezf:anchor></td>
			                                    <td align="left"><ezf:label name="xxDtlNm_A" ezfName="xxDtlNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
			                                </tr>
			                            </ezf:row>
			                        </tbody>
			                    </table>
			                </div>
						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
