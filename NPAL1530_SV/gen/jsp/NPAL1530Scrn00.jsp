<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180116114159 --%>
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
			<input type="hidden" name="pageID" value="NPAL1530Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Plan Report Screen">

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
                                        <li class="pTab_ON"><a href="./NPAL1530Scrn00.html">Plan Report</a></li>
                                    </ul>
                                </div>

                                <!-- include S21BusinessProcessTAB -->
                                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                            </ezf:skip>

							<div class="pTab_BODY">
								<%-- ###TAB - HEAD --%>
                                <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
                                    <col width="100%"  align="left">
                                    <tr valign="top">
                                        <td>
                                            <table border="0" cellpadding="0" cellspacing="0" >
                                                <tr>
                                                    <col width="250"  align="left">
                                                    <col width="60"  align="left">
                                                    <col width="30"  align="center">
                                                    <col width="" align="left">
                                                </tr>
                                                <!-- 1 -->
												<tr height="28">
                                                    <td>Planning Level</td>
													<td colspan="3">
														<ezf:select name="xxCmntTxt_SL" ezfName="xxCmntTxt_SL" ezfCodeName="xxCmntTxt_CD" ezfDispName="xxCmntTxt_NM" otherAttr=" style=\"width:136\""/>
													</td>
                                                </tr>
												<tr height="28">
													<td><ezf:anchor name="xxLinkAncr_PN" ezfName="xxLinkAncr_PN" ezfEmulateBtn="1" ezfGuard="OpenWin_SearchPlanName" >Plan Name</ezf:anchor></td>
													<td colspan="3">
														<ezf:inputText name="mrpPlnNm" ezfName="mrpPlnNm" value="MONROE PLAN" otherAttr=" size=\"72\" maxlength=\"70\" ezftoupper=\"\""/>
													</td>
                                                </tr>
												<tr height="28">
                                                    <td><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_LocationInfo" >Warehouse</ezf:anchor></td>
                                                    <td><ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="A01" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                    <td><ezf:inputButton name="SetWarehouseName" ezfName="ItemNm" value=">>" /></td>
                                                    <td><ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="A01 MONROE" otherAttr=" size=\"47\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                </tr>
                                                <!-- 2 -->
												<tr height="28">
                                                    <td><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_LocationInfo" >Sub Warehouse</ezf:anchor></td>
                                                    <td><ezf:inputText name="rtlSwhCd" ezfName="rtlSwhCd" value="NEW" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                    <td><ezf:inputButton name="SetSubWarehouseName" ezfName="ItemNm" value=">>" /></td>
                                                    <td><ezf:inputText name="rtlSwhNm" ezfName="rtlSwhNm" value="NEW" otherAttr=" size=\"47\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                    <td></td>
                                                </tr>
												<tr height="28">
													<td>Demand Cut Off Date</td>
													<td colspan="3">
														<ezf:inputText name="dmndCtoffDt" ezfName="dmndCtoffDt" value="05/20/2015" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dmndCtoffDt',4);" >
													</td>
                                                </tr>
												<tr height="28">
													<td>Demand Cut Off Date OFFSET (Days)</td>
													<td colspan="3">
														<ezf:inputText name="dmndCtoffDaysAot" ezfName="dmndCtoffDaysAot" value="999" otherAttr=" size=\"5\" maxlength=\"3\" ezftoupper=\"\" style=\"text-align:right;\""/>
													</td>
                                                </tr>
												<tr height="28">
													<td>Supply Cut Off Date</td>
													<td colspan="3">
														<ezf:inputText name="splyCtoffDt" ezfName="splyCtoffDt" value="05/20/2015" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('splyCtoffDt',4);" >
													</td>
                                                </tr>
												<tr height="28">
													<td>Supply Cut Off Date OFFSET (Days)</td>
													<td colspan="3">
														<ezf:inputText name="splyCtoffDaysAot" ezfName="splyCtoffDaysAot" value="999" otherAttr=" size=\"5\" maxlength=\"3\" ezftoupper=\"\" style=\"text-align:right;\""/>&nbsp;
													</td>
                                                </tr>
												<tr height="28">
													<td>Create Requisitions</td>
													<td colspan="3">
														<ezf:inputCheckBox name="cratPrchReqFlg" ezfName="cratPrchReqFlg" value="Y" />
													</td>
                                                </tr>
												<tr height="28">
													<td>Print Custom Item Statuses</td>
													<td colspan="3">
														<ezf:inputCheckBox name="printItemStsFlg" ezfName="printItemStsFlg" value="Y" />
													</td>
                                                </tr>
												<tr height="28">
													<td>Display Item Description</td>
													<td><ezf:inputCheckBox name="printItemDescFlg" ezfName="printItemDescFlg" value="Y" /></td>
                                                </tr>
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
