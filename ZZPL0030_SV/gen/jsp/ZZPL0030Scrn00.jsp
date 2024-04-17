<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20130815055030 --%>
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

		<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<%-- include S21BusinessProcessTAB --%>
					<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

					<div class="pTab_BODY">

						<%-- Header --%>
						<table>
							<col width="50">
							<col>
							<col width="168">
							<col>
							
							<tr>
								<td/>
								<td class="stab">Report ID(*)</td>
								<td><ezf:inputText name="xxRptNm_0" ezfName="xxRptNm_0" value="AWCF0020ENOXXXXXXXXX" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
								<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
							</tr>
						</table>

						<hr noshade>

						<%-- Pagenation --%>
						<table width="1083">
							<tr align="right">
								<td>
									<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
										<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
										<jsp:param name="TableNm"     value="A" />
										<jsp:param name="ShowingFrom" value="xxPageShowFromNum_0" />
										<jsp:param name="ShowingTo"   value="xxPageShowToNum_0" />
										<jsp:param name="ShowingOf"   value="xxPageShowOfNum_0" />
									</jsp:include>
								</td>
							</tr>
						</table>

						<%-- Detail --%>
						<table cellspacing="0" cellpadding="1" border="1" width="1033" align="center">
							<col width="250">
							<col width="771">
							
							<tr align="center">
								<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxRptNm_A')">Report ID<img id="sortIMG.xxRptNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
								<td class="pClothBs">Description</td>
							</tr>
						</table>

						<div style="OVERFLOW-Y:scroll; OVERFLOW-X:none; " >
							<table cellspacing="0" cellpadding="1" border="1" width="1033" id="A" align="center">
								<col width="250">
								<col width="771">

								<tbody>
									<ezf:row ezfHyo="A">
										<tr>
											<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenRptNm_Row" ><ezf:label name="xxRptNm_A" ezfName="xxRptNm_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
											<td><ezf:label name="xxPrtrDescTxt" ezfName="xxPrtrDescTxt" ezfHyo="A" ezfArrayNo="0" /></td>
										</tr>
									</ezf:row>
									<ezf:skip>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
										<tr>
											<td><a href="#" onclick="sendServer('OpenRptNm_Row')" ezfhyo="A"><label ezfout name="xxRptNm_A" ezfname="xxRptNm_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXX</label></a></td>
											<td><label ezfout name="xxPrtrDescTxt" ezfname="xxPrtrDescTxt" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
										</tr>
									</ezf:skip>
								</tbody>
							</table>
						</div>

					</div>
				</td>
			</tr>
		</table>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="ZZPL0030Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Archive Report List">

<%-- **** Task specific area ends here **** --%>
