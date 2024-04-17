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
							<col width="100">
							<col width="168">
							<col width="400">
							
							<tr>
								<td/>
								<td class="stab" align="left">Report ID</td>
								<td><ezf:inputText name="xxRptNm_1" ezfName="xxRptNm_1" value="AWCF0020ENOXXXXXXXXX" otherAttr=" size=\"31\" align=\"left\""/></td>
								<td class="pOut" align="left"><ezf:label name="xxPrtrDescTxt_1" ezfName="xxPrtrDescTxt_1" /></td>
							</tr>
						</table>

						<table>
							<col width="50">
							<col width="100">
							<col width="111">
							<col width="10" align="center">
							<col width="111">
							
							<tr>
								<td/>
								<td class="stab" align="left">Creation Date</td>
								<td>
									<ezf:inputText name="xxCratDt_FR" ezfName="xxCratDt_FR" value="04/12/2013" otherAttr=" size=\"10\" maxlength=\"10\""/>
									<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxCratDt_FR', 4);" >
								</td>
								<td class="stab">-</td>
								<td>
									<ezf:inputText name="xxCratDt_TO" ezfName="xxCratDt_TO" value="05/13/2013" otherAttr=" size=\"10\" maxlength=\"10\""/>
									<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxCratDt_TO', 4);" >
								</td>
							</tr>
						</table>

						<table>
							<col width="50">
							<col width="100">
							<col>
							<col>
							<col width="5">
							<col>
							<col>
							<col>

							<tr>
								<td/>
								<td class="stab">Advanced Search(*)</td>
								<td>
									<ezf:select name="xxRptSrchCd_I1" ezfName="xxRptSrchCd_I1" ezfCodeName="xxRptSrchCd_O1" ezfDispName="xxRptSrchNm_O1" otherAttr=" style=\"width:227;\""/>
								</td>
								<td><ezf:inputText name="xxRptSrchTxt_1" ezfName="xxRptSrchTxt_1" value="9999999999" otherAttr=" size=\"25\" maxlength=\"50\""/></td>
								<td></td>
								<td>
									<ezf:select name="xxRptSrchCd_I3" ezfName="xxRptSrchCd_I3" ezfCodeName="xxRptSrchCd_O3" ezfDispName="xxRptSrchNm_O3" otherAttr=" style=\"width:227;\""/>
								</td>
								<td><ezf:inputText name="xxRptSrchTxt_3" ezfName="xxRptSrchTxt_3" value="9999999999" otherAttr=" size=\"25\" maxlength=\"50\""/></td>
								<td></td>
							</tr>
							<tr>
								<td/>
								<td></td>
								<td>
									<ezf:select name="xxRptSrchCd_I2" ezfName="xxRptSrchCd_I2" ezfCodeName="xxRptSrchCd_O2" ezfDispName="xxRptSrchNm_O2" otherAttr=" style=\"width:227;\""/>
								</td>
								<td><ezf:inputText name="xxRptSrchTxt_2" ezfName="xxRptSrchTxt_2" value="9999999999" otherAttr=" size=\"25\" maxlength=\"50\""/></td>
								<td></td>
								<td>
									<ezf:select name="xxRptSrchCd_I4" ezfName="xxRptSrchCd_I4" ezfCodeName="xxRptSrchCd_O4" ezfDispName="xxRptSrchNm_O4" otherAttr=" style=\"width:227;\""/>
								</td>
								<td><ezf:inputText name="xxRptSrchTxt_4" ezfName="xxRptSrchTxt_4" value="9999999999" otherAttr=" size=\"25\" maxlength=\"50\""/></td>
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
										<jsp:param name="TableNm"     value="B" />
										<jsp:param name="ShowingFrom" value="xxPageShowFromNum_1" />
										<jsp:param name="ShowingTo"   value="xxPageShowToNum_1" />
										<jsp:param name="ShowingOf"   value="xxPageShowOfNum_1" />
									</jsp:include>
								</td>
							</tr>
						</table>

						<%-- Detail --%>
						<table cellspacing="0" cellpadding="1" border="1" width="1033" align="center">
							<col width="156">
							<col width="865">
							
							<tr align="center">
								<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxRptCratTm')">Creation Time<img id="sortIMG.xxRptCratTm" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
								<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', 'xxRptTtlTxt')">Title<img id="sortIMG.xxRptTtlTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							</tr>
						</table>

						<div style="OVERFLOW-Y:scroll; OVERFLOW-X:none; " >
							<table cellspacing="0" cellpadding="1" border="1" width="1033" id="B" align="center">
								<col width="156">
								<col width="865">

								<tbody>
									<ezf:row ezfHyo="B">
										<tr>
											<td><ezf:label name="xxRptCratTm" ezfName="xxRptCratTm" ezfHyo="B" ezfArrayNo="0" /></td>
											<td><ezf:anchor name="" ezfName="" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenRptTtl_Row" ><ezf:label name="xxRptTtlNm" ezfName="xxRptTtlNm" ezfHyo="B" ezfArrayNo="0" /></ezf:anchor></td>
										</tr>
									</ezf:row>
									<ezf:skip>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRptCratTm" ezfname="xxRptCratTm" ezfhyo="B">2013/05/23 01:23</label></td>
											<td><a href="#" onclick="sendServer('OpenRptTtl_Row')" ezfhyo="B"><label ezfout name="xxRptTtlNm" ezfname="xxRptTtlNm" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></a></td>
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
			<input type="hidden" name="pageID" value="ZZPL0030Scrn01">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Archive Report History">

<%-- **** Task specific area ends here **** --%>
