<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161222133511 --%>
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
			<input type="hidden" name="pageID" value="ZZVL0020Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Set Up Shared My Process">
<center>
	<table height="528" width="80%">
		<tr>
			<td>
				<table width="100%">
					<tr>
						<td>
							<table>
								<col width="150">
								<col width="50">
								<col width="100">
								<tr>
									<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="SelectCompanyCD" >Global Company Code</ezf:anchor></td>
									<td><ezf:inputText name="glblCmpyCd_1" ezfName="glblCmpyCd_1" value="ADB" otherAttr=" size=\"10\" maxlength=\"6\" ezftoupper=\"\""/></td>
									<td></td>
								</tr>
								<tr>
									<td class="stab">Role ID (*)</td>
									<td><ezf:inputText name="roleNm_1" ezfName="roleNm_1" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn3" /></td>
								</tr>
							</table>
							<hr>
							<%-- Pagenation --%>
							<table width="100%">
								<tr align="right">
									<td>
										<!--
										<table border="0" cellpadding="1" cellspacing="0">
											<col width="54"  align="center">
											<col width="40"  align="right">
											<col width="16"  align="center">
											<col width="40"  align="right">
											<col width="16"  align="center">
											<col width="40"  align="right">
											<col width="10">
											<col>
											<col width="1">
											<col>
											<tr>
												<td class="stab">Showing</td>
												<td class="pOut">1</td>
												<td class="stab">to</td>
												<td class="pOut">10</td>
												<td class="stab">of</td>
												<td class="pOut">200</td>
												<td>&nbsp;</td>
												<td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn3" /></td>
												<td></td>
												<td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn3" /></td>
											</tr>
										</table>
										-->
										<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
											<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"     value="C" />
											<jsp:param name="ShowingFrom" value="xxPageShowFromNum_1" />
											<jsp:param name="ShowingTo"   value="xxPageShowToNum_1" />
											<jsp:param name="ShowingOf"   value="xxPageShowOfNum_1" />
										</jsp:include>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table border="1" cellpadding="1" cellspacing="0" width="870px">
							<col width="72px" align="center">
							<col width="72px" align="center">
							<col width="192px" align="center">
							<col width="520px" align="center">
							<tr>
								<td class="pClothBs">&nbsp;</td>
								<td class="pClothBs">&nbsp;</td>
								<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'roleNm_C' )">
									Role ID<img id="sortIMG.roleNm_C" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
								</td>
								<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'roleDescTxt_C' )">
									Description<img id="sortIMG.roleDescTxt_C" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
								</td>
							</tr>
							</table>
							<div id="bottomTBL" style="word-break:break-all; overflow:auto; height:458; width:887">
								<table border="1" cellpadding="1" cellspacing="0" id="C" width="870px">
									<col width="72px" align="center">
									<col width="72px" align="center">
									<col width="192px">
									<col width="520px">
									<tbody>
									<ezf:row ezfHyo="C">
										<tr id="id_row{EZF_ROW_NUMBER}">
											<td><ezf:inputButton name="Select" value="Select" ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn6" /></td>
											<td><ezf:inputButton name="Delete" value="Delete" ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn6" /></td>
											<td><ezf:label name="roleNm_C" ezfName="roleNm_C" ezfHyo="C" ezfArrayNo="0" /></td>
											<td><ezf:label name="roleDescTxt_C" ezfName="roleDescTxt_C" ezfHyo="C" ezfArrayNo="0" /></td>
										</tr>
									</ezf:row>
									<ezf:skip>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
										<tr>
											<td><input type="button" class="pBtn6" value="Select" name="Select" onclick="sendServer(this)"></td>
											<td><input type="button" class="pBtn6" value="Delete" name="Delete" onclick="sendServer(this)"></td>
											<td><label ezfout name="roleNm_C" ezfname="roleNm_C">X9999</label></td>
											<td><label ezfout name="roleDescTxt_C" ezfname="roleDescTxt_C">XXX XXXXXXXX XXXXXXX</label></td>
										</tr>
									</ezf:skip>
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
