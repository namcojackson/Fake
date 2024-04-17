<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160309071151 --%>
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
			<input type="hidden" name="pageID" value="NMAL2670Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Territory Rules Pop-up">


<center>
	<table width="100%">
		<col valign="top">
		<tr>
			<td>
				<table border="0" cellpadding="2" cellspacing="0" width="95%" style="margin-left:40; table-layout:fixed;" >
					<col width="30">
					<col width="35">
					<col width="300">
					<tr>
						<td class="stab">Territory</td>
						<td><ezf:inputText name="orgCd_H1" ezfName="orgCd_H1" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
						<td><ezf:inputText name="orgNm_H1" ezfName="orgNm_H1" otherAttr=" size=\"30\" ezftoupper=\"\""/></td>
					</tr>
				</table>
				<hr>
        <%--
                <table border="0" cellpadding="1" cellspacing="1">
                    <tr height="25">
                        <td width="680" align="right">
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
                                    <td class="pOut">2</td>
                                    <td class="stab">of</td>
                                    <td class="pOut">2</td>
                                    <td>&nbsp;</td>
                                    <td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn3" /></td>
                                    <td></td>
                                    <td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn3" /></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
        --%>
                <table border="0" cellpadding="1" cellspacing="0" width="90%" align="center">
                    <tr>
                        <td>
                            <table border="0" cellpadding="1" cellspacing="0">
                                <col width="190">
                                <col width="60">
                                <tr>
                                    <td><ezf:inputText name="xxQueryFltrTxt_H1" ezfName="xxQueryFltrTxt_H1" otherAttr=" size=\"25\" maxlength=\"200\""/></td>
                                    <td><ezf:inputButton name="Filter" value="Filter" htmlClass="pBtn3" /></td>
                                </tr>
                            </table>
                        </td>
                        <td>
                            <table border="0" cellpadding="1" cellspacing="0" width="94%" align="right">
                                <tr>
                                    <td width="100%" align="right">
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
                <table align="center">
                    <tr>
                        <td>
                            <table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
                                <col width="680" align="center">
                                <tr>
                                    <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDsMsgEntryTxt_A1' )" >Territory Rule<img id="sortIMG.xxDsMsgEntryTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                </tr>
                            </table>
                            <div style="overflow-y:scroll; height:445; width:699;">
                                <table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;">
                                    <col width="680" align="left">
                                    <tbody>
                                        <ezf:row ezfHyo="A">
                                            <tr id="id_row{EZF_ROW_NUMBER}">
                                                <td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_Rules" ><ezf:label name="xxDsMsgEntryTxt_A1" ezfName="xxDsMsgEntryTxt_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
                                            </tr>
                                        </ezf:row>
                                        <ezf:skip>
                                            <tr>
                                                <td><a href="#" onclick="sendServer('OpenWin_Rules')" ezfhyo="A"><label ezfout name="xxDsMsgEntryTxt_A1" ezfname="xxDsMsgEntryTxt_A1" ezfhyo="A">Postal Code BETWEEN 89002 AND 89002-9999</label></a></td>
                                            </tr>
                                            <tr>
                                                <td><a href="#" onclick="sendServer('OpenWin_Rules')" ezfhyo="A"><label ezfout name="xxDsMsgEntryTxt_A1" ezfname="xxDsMsgEntryTxt_A1" ezfhyo="A">Postal Code BETWEEN 89002 AND 89002-9999</label></a></td>
                                            </tr>
                                            <tr>
                                                <td><a href="#" onclick="sendServer('OpenWin_Rules')" ezfhyo="A"><label ezfout name="xxDsMsgEntryTxt_A1" ezfname="xxDsMsgEntryTxt_A1" ezfhyo="A">Postal Code BETWEEN 89002 AND 89002-9999</label></a></td>
                                            </tr>
                                        </ezf:skip>
                                    </tbody>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
				<table border="0" cellpadding="1" cellspacing="0" width="90%" align="center">
					<tr>
						<td align="right"><ezf:inputButton name="Download" value="Download" htmlClass="pBtn6" /></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>





<%-- **** Task specific area ends here **** --%>
