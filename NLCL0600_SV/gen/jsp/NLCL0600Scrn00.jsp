<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160406094609 --%>
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
			<input type="hidden" name="pageID" value="NLCL0600Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="PI Entry">

            <%-- ********************** --%>
            <%-- *** Upper Tab Area *** --%>
            <%-- ********************** --%>
            <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

			<%-- ######################################## HEADER ######################################## --%>
			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
                            <ezf:skip>
                            <div class="pTab_HEAD">
                                <ul>
                                    <li class="pTab_ON"><a href="./NLCL0600Scrn00.html">PI Entry</a></li>
                                </ul>
                            </div>

							<!-- include S21BusinessProcessTAB -->
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                            </ezf:skip>

							<div class="pTab_BODY">
								<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px"border="0">
									<tr>
										<col width="800"  align="left">
									</tr>
									<tr>
										<td valign="top">
                                            <table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<col width="150" align="left">
													<col width="400"  align="left">
												</tr>
												<!-- 1 -->
												<tr height="20">
													<td class="stab"><ezf:anchor name="xxLinkAncr_01" ezfName="xxLinkAncr_01" ezfEmulateBtn="1" ezfGuard="OpenWin_WH" >Warehouse</ezf:anchor></td>
													<td>
													    <ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="----+----1----+----2" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/>
													    <ezf:inputButton name="SearchWHInfo" value=">>" htmlClass="pBtn0" />
													    <ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="----+----1----+----2----+----3" otherAttr=" size=\"20\" maxlength=\"30\""/>
													</td>
												</tr>
												<!-- 2 -->
												<tr height="20">
													<td class="stab">Schedule Date</td>
													<td>
													    <ezf:inputText name="physInvtyDt" ezfName="physInvtyDt" value="01/01/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
													    <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('physInvtyDt', 4);"/>
													</td>
												</tr>
												<!-- 3 -->
												<tr height="20">
													<td class="stab">Physical Inventory</td>
													<td><ezf:inputText name="physInvtyCtrlNm" ezfName="physInvtyCtrlNm" value="----+----1----+----2----+----3----+----4----+----5----+----6----+----7----+----8----+----9----+----0" otherAttr=" size=\"35\" maxlength=\"100\" ezftoupper=\"\""/></td>
												</tr>
												<!-- 4 -->
												<tr height="20">
													<td class="stab">Description</td>
													<td><ezf:inputText name="physInvtyCtrlDescNm" ezfName="physInvtyCtrlDescNm" value="----+----1----+----2----+----3----+----4----+----5----+----6----+----7----+----8----+----9----+----0----+----1----+----2----+----3----+----4----+----5----+----6----+----7----+----8----+----9----+----0----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"35\" maxlength=\"250\" ezftoupper=\"\""/></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>

							<hr>

                            <%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
                            <table style="margin-left: 5px; width: 1108;" >
                                <colgroup>
                                    <!-- Add Specific Sub WH Area -->
                                    <col align="left" width="480">
                                    <!-- Add All Sub WH Area -->
                                    <col align="center" width="150">
                                    <!-- Delete Button Area -->
                                    <col align="right">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <td class="stab">
                                            <fieldset>
                                                <legend><font color="black">Add Specific SWH</font></legend>
                                                <ezf:anchor name="WarehouseMgrPsnCd_H3" ezfName="WarehouseMgrPsnCd_H3" ezfEmulateBtn="1" ezfGuard="OpenWin_SubWH" >SWH</ezf:anchor>
                                                <ezf:inputText name="rtlSwhCd" ezfName="rtlSwhCd" value="----+----1----+----2" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/>
                                                <ezf:inputButton name="SearchRetailSubWHInfo" value=">>" htmlClass="pBtn0" />
                                                <ezf:inputText name="rtlSwhNm" ezfName="rtlSwhNm" value="----+----1----+----2----+----3" otherAttr=" size=\"20\" maxlength=\"30\""/>
                                                <ezf:inputButton name="Add_SpecificSubWH" value="Add" htmlClass="pBtn6" />
                                            </fieldset>
                                        </td>
                                        <td>
                                            <fieldset>
                                                <legend><font color="black">Add All SWH</font></legend>
                                                <ezf:inputButton name="Add_AllSubWH" value="Add All" htmlClass="pBtn6" />
                                            </fieldset>
                                        </td>
                                        <td>
                                            <ezf:inputButton name="Delete_SubWHs" value="Delete" htmlClass="pBtn6" />
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

							<%-- ######################################## DETAIL ######################################## --%>
                            <div>
                                <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td>
                                            <div id="topTBL" style="overflow-y:none; height:; overflow-x:hidden; width:1106;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('btmTBL'));">
                                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
                                                    <col width="70"   align="center">
                                                    <col width="230"  align="center">
                                                    <col width=""  align="center">
                                                    <tr height="35">
                                                        <td class="pClothBs"></td>
                                                        <td class="pClothBs">SWH Code</td>
                                                        <td class="pClothBs">SWH</td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="vertical-align:top;">
                                            <div id="btmTBL" style="overflow-y:scroll; height:350; overflow-x:scroll; width:1125;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('topTBL'));">
                                                <table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" id="A">
                                                <col width="70"   align="center">
                                                <col width="230"  align="left">
                                                <col width=""  align="left">
                                                    <ezf:row ezfHyo="A">
                                                        <tr height="23">
                                                            <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <td><ezf:label name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <td><ezf:label name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                        </tr>
                                                    </ezf:row>
                                                </table>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <!-- footer buttons -->
                            <table style="margin-left: 5px; margin-top: 6px; z-index: 2; position: relative" border="0" cellpadding="1" cellspacing="1" align="left">
                                <colgroup>
                                    <col width="">
                                    <col width="">
                                </colgroup>
                                <tr>
                                    <td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn6" /></td>
                                    <td><ezf:inputButton name="UnSelectAll" value="Un Select All" htmlClass="pBtn6" /></td>
                                <tr>
                            </table>
						</td>
					</tr>
				</table>
			</center>



<%-- **** Task specific area ends here **** --%>
