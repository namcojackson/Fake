<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160921123526 --%>
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
			<input type="hidden" name="pageID" value="NLCL1050Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="ABC Class Setup">
<!-- Application Parts Start -->

<center>
	<table height="95%" cellSpacing="0" cellPadding="0" width="100%" border="0">
		<tr>
			<td valign="top">
				<%-- ********************** --%>
				<%-- *** Upper Tab Area *** --%>
				<%-- ********************** --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
						<tr>
							<td width="1070px" height="28px" valign="bottom">
								<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
									<tr title="ABC Class Setup">
										<td width="107px" align="center" class="same">ABC Class Setup</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</ezf:skip>
                <%-- ######################################## HEADER ######################################## --%>
				<div class="pTab_BODY">
				<table style="margin-left: 10px;" width="100%" cellpadding="1" cellspacing="1" align="center" border="0">
					<tr>
						<td>
							<table border="0">
                                <col width="120" align="left">
                                <col width=""    align="left">
                                <col width="80"    align="left">
                                <col width="80"    align="left">
                                <col width="80"    align="left">
								<tr>
									<td><ezf:anchor name="abcAnlsClsNm_L1" ezfName="abcAnlsClsNm_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_ABCClass" >ABC Class Name</ezf:anchor></td>
									<td><ezf:inputText name="abcAnlsClsNm" ezfName="abcAnlsClsNm" value="WOODRIDGE EQUIPMENTS VALUE" otherAttr=" size=\"40\" maxlength=\"40\" ezftoupper=\"\""/></td>
									<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									<td><ezf:inputButton name="Save" value="Save" htmlClass="pBtn6" /></td>
									<td><ezf:inputButton name="Delete" value="Delete" htmlClass="pBtn6" /></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

                <hr>

                <!-- ################################################## Detail ################################################## -->
                <table style="MARGIN-LEFT: 10px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td><ezf:inputButton name="AddLine" value="Add Line" htmlClass="pBtn6" />&nbsp;</td>
						<td><ezf:inputButton name="DeleteLine" value="Delete Line" htmlClass="pBtn6" /></td>
					</tr>
				</table>
				<br>
                <table style="MARGIN-LEFT: 10px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td align="left" valign="top">
                            <div id="topTBL" style="overflow-y:none; overflow-x:hidden; width:760;" onscroll="synchroScrollLeft(this.id, new Array('btmTBL'));">
                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" height="38">
                                    <col width="25"   align="center"><!-- checkbox -->
                                    <col width="60"   align="center"><!-- Class Priority# -->
                                    <col width="90"  align="center"><!-- Class Tag Name -->
                                    <col width="370"  align="center"><!-- Tag Description -->
                                    <col width="90"  align="center"><!-- Cycle Count Days -->
                                    <col width="100"  align="center"><!-- Value Assignment -->
                                    <tr>
                                        <td class="pClothBs">&nbsp;</td>
                                        <td class="pClothBs">Class Priority#</td>
                                        <td class="pClothBs">Class Tag Name</td>
                                        <td class="pClothBs">Tag Description</td>
                                        <td class="pClothBs">Cycle Count Days</td>
                                        <td class="pClothBs">Value Assignment</td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                    <tr valign="top">
                        <td>
                            <div id="btmTBL" style="overflow-y:scroll; height:440; overflow-x:none; width:750"
                                    onScroll="synchroScrollLeft(this.id, new Array('topTBL'));">
                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A">
                                    <col width="25" align="center"><!-- checkbox -->
                                    <col width="60" align="center"><!-- Class Priority# -->
                                    <col width="90" align="center"><!-- Class Tag Name -->
                                    <col width="370" align="center"><!-- Tag Description -->
                                    <col width="90" align="center"><!-- Cycle Count Days -->
                                    <col width="100" align="center"><!-- Value Assignment -->
                                    <ezf:row ezfHyo="A">
                                        <!-- 1 -->
                                        <tr height="25">
                                            <td><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"chkBox{EZF_ROW_NUMBER}\""/></td>
                                            <td><ezf:label name="abcAnlsClsPrtyNum" ezfName="abcAnlsClsPrtyNum" ezfHyo="A" ezfArrayNo="0" /></td>
                                            <td><ezf:inputText name="abcAnlsClsTagCd" ezfName="abcAnlsClsTagCd" value="A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"1\" ezftoupper=\"\""/></td>
                                            <td><ezf:inputText name="abcAnlsClsTagDescTxt" ezfName="abcAnlsClsTagDescTxt" value="High Value" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"50\""/></td>
                                            <td><ezf:inputText name="cycleCntFreqDaysAot" ezfName="cycleCntFreqDaysAot" value="5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"3\" style=\"text-align:right\""/></td>
                                            <td><ezf:inputText name="valAsgPct" ezfName="valAsgPct" value="70.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"6\" style=\"text-align:right\""/>&nbsp;%</td>
                                        </tr>
                                    </ezf:row>
                                    <ezf:skip>
                                    </ezf:skip>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
                </div> <!-- pTab_BODY -->
                <!-- ######################################## Footer #################################### -->
			</td>
		</tr>
	</table>
</center>
<!-- Application Parts End -->


<%-- **** Task specific area ends here **** --%>
