<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160810202435 --%>
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
			<input type="hidden" name="pageID" value="NPAL1430Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Reman Model Standard Parts Setup">
			
<%-- ######################################## UPPER TAB AREA ######################################## --%>

			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

<%-- ######################################## HEADER AREA ######################################## --%>
			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<li class="pTab_ON">
											<a href="./NPAL1430Scrn00.html">Reman Parts</a>
									</li>
								</ul>
							</div>
							</ezf:skip>
							
							<div class="pTab_BODY">
								<table  style="margin-left:30px; margin-top:0px;">
									<col align="left">
									<tr>
										<td>
											<table border="0">
												<col width="100px"  align="left">
												<col width=""  align="left">
												<%-- =============== Warehouse Category =============== --%>
												<tr height="20px">
													<td class="stab"><ezf:anchor name="rmnfMdlId_AC" ezfName="rmnfMdlId_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_Model" >Model</ezf:anchor></td>
													<td style="text-align: left;">
														<ezf:inputText name="t_MdlNm" ezfName="t_MdlNm" value="IPC7000" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>&nbsp;
														<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
													</td>
												</tr>
												<tr height="20px">
													<td class="stab">Description</td>
													<td style="text-align: left;">
														<ezf:inputText name="rmnfMdlStdPrtDescTxt" ezfName="rmnfMdlStdPrtDescTxt" value="IPC7000" otherAttr=" size=\"44\" maxlength=\"50\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr height="20px">
													<td class="stab">Update Date</td>
													<td style="text-align: left;">
														<ezf:inputText name="lastUpdDt" ezfName="lastUpdDt" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>

							<hr>

							<%-- ######################################## DETAIL AREA ######################################## --%>
	                        <table style="MARGIN-LEFT: 30px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
	                            <tr height="20">
									<td align="left" style="font-size:15px"><b>Standard Parts List</b>
									</td>
	                            </tr>
	                            <tr height="40">
									<td align="left">
										<ezf:inputButton name="DeleteLine" value="Delete Line" htmlClass="pBtn8" />
										<ezf:inputButton name="AddLine" value="Add New Line" htmlClass="pBtn8" />
									</td>
	                            </tr>
	                            <tr>
									<%-- =============== TABLE HEADER =============== --%>
	                                <td valign="top">
	                                    <div id="topRightTBL" style="overflow-y:none; overflow-x:hidden; width:495;">
	                                        <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" height="30" width="">
												<col width="30px" align="center">
												<col width="160px" align="center">
												<col width="220px" align="center">
												<col width="80px" align="center">
												<tr>
													<td class="pClothBs">&nbsp;</td>
													<td class="pClothBs">Item Number</td>
													<td class="pClothBs">Description</td>
													<td class="pClothBs">Qty</td>
												</tr>
											</table>
										</div>
										<%-- =============== TABLE BODY =============== --%>
		                                <div id="RightTBL" style="overflow-y:scroll; height:380; overflow-x:none; width:505;" 
	                                        onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'topRightTBL' ) );">
	                                        <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="">
												<col width="30px" align="center">
												<col width="160px" align="left">
												<col width="220px" align="left">
												<col width="80px" align="left">
												<ezf:row ezfHyo="A">
													<tr id="id_row${EZF_ROW_NUMBER}" height="28px">
														<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\""/></td>
														<td><ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="FM1-234-456" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/>
	                                                        <ezf:inputButton name="OpenWin_Item" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" ezftoupper=\"\""/>
														</td>
														<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="ROLLER" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
														<td><ezf:inputText name="rmnfReqQty_A1" ezfName="rmnfReqQty_A1" value="4" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right; \" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/></td>
													</tr>
												</ezf:row>
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
