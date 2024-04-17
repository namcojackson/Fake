<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160310044019 --%>
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
			<input type="hidden" name="pageID" value="NMAL2680Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Rules Pop-up">


<center>
	<table width="100%">
		<col valign="top">
		<tr>
			<td>
				<table border="0" cellpadding="2" cellspacing="0" style="margin-left:5; table-layout:fixed;" >
					<col width="50">
					<col width="70">
					<col width="230">
					<col width="80">
					<col width="80">
					<tr>
						<td class="stab">Territory</td>
						<td><ezf:inputText name="orgCd_H1" ezfName="orgCd_H1" otherAttr=" size=\"8\""/></td>
						<td><ezf:inputText name="orgNm_H1" ezfName="orgNm_H1" otherAttr=" size=\"30\""/></td>
						<td><ezf:inputButton name="Search_Cust" value="Custmer" htmlClass="pBtn6" /></td>
						<td><ezf:inputButton name="Search_Pros" value="Prospect" htmlClass="pBtn6" /></td>
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
                <table border="0" cellpadding="1" cellspacing="0"  width="99%" align="center" style="table-layout:fixed;">
                    <tr>
                        <td colspan="2">
                             <ezf:label name="xxDsMsgEntryTxt_H1" ezfName="xxDsMsgEntryTxt_H1" />
                        </td>
                    </tr>
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
                            <table border="0" cellpadding="1" cellspacing="0" width="100%" align="right">
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
                <table align="center" style="table-layout:fixed;">
                    <tr>
                        <td>
                            <table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
                                <col width="180" align="center">
                                <col width="70"  align="center">
                                <col width="70"  align="center">
                                <col width="70"  align="center">
                                <col width="374" align="center">
                                <tr>
                                    <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNm_A1' )" >Account Name<img id="sortIMG.dsAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                    <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNum_A1' )" >Account#<img id="sortIMG.dsAcctNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                    <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctTpNm_A1' )" >Account<img id="sortIMG.dsAcctTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                    <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'locNum_A1' )" >Location#<img id="sortIMG.locNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                    <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxAllLineAddr_A1' )" >Address<img id="sortIMG.xxAllLineAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                </tr>
                            </table>
                            <div style="overflow-y:scroll; height:430; width:783;">
                                <table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;">
	                                <col width="180">
	                                <col width="70">
	                                <col width="70">
	                                <col width="70">
	                                <col width="374">
                                    <tbody>
                                        <ezf:row ezfHyo="A">
                                            <tr id="id_row{EZF_ROW_NUMBER}">
                                                <td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
                                                <td><ezf:label name="dsAcctNum_A1" ezfName="dsAcctNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                <td><ezf:label name="dsAcctTpNm_A1" ezfName="dsAcctTpNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                <td><ezf:label name="locNum_A1" ezfName="locNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                <td><ezf:inputText name="xxAllLineAddr_A1" ezfName="xxAllLineAddr_A1" value="6730 SUMATRA ST LAS VEGAS, US, 89166-7017" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"52\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
                                            </tr>
                                        </ezf:row>
                                        <ezf:skip>
                                            <tr id="id_row{EZF_ROW_NUMBER}">
                                                <td><input type="text" name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" size="20" style="border:none;background-color:transparent;padding:0px;" /></td>
                                                <td><label ezfout name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfhyo="A">1850781</label></td>
                                                <td><label ezfout name="dsAcctTpNm_A1" ezfname="dsAcctTpNm_A1" ezfhyo="A">CUSTOMER</label></td>
                                                <td><label ezfout name="locNum_A1" ezfname="locNum_A1" ezfhyo="A">12345678</label></td>
                                                <td><input type="text" name="xxAllLineAddr_A1" ezfName="xxAllLineAddr_A1" value="6730 SUMATRA ST LAS VEGAS, US, 89166-7017" ezfHyo="A" size="20" style="border:none;background-color:transparent;padding:0px;" /></td>
                                            </tr>
                                            <tr id="id_row{EZF_ROW_NUMBER}">
                                                <td><input type="text" name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" size="20" style="border:none;background-color:transparent;padding:0px;" /></td>
                                                <td><label ezfout name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfhyo="A">1850781</label></td>
                                                <td><label ezfout name="dsAcctTpNm_A1" ezfname="dsAcctTpNm_A1" ezfhyo="A">CUSTOMER</label></td>
                                                <td><label ezfout name="locNum_A1" ezfname="locNum_A1" ezfhyo="A">12345678</label></td>
                                                <td><input type="text" name="xxAllLineAddr_A1" ezfName="xxAllLineAddr_A1" value="6730 SUMATRA ST LAS VEGAS, US, 89166-7017" ezfHyo="A" size="20" style="border:none;background-color:transparent;padding:0px;" /></td>
                                            </tr>
                                            <tr id="id_row{EZF_ROW_NUMBER}">
                                                <td><input type="text" name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" size="20" style="border:none;background-color:transparent;padding:0px;" /></td>
                                                <td><label ezfout name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfhyo="A">1850781</label></td>
                                                <td><label ezfout name="dsAcctTpNm_A1" ezfname="dsAcctTpNm_A1" ezfhyo="A">CUSTOMER</label></td>
                                                <td><label ezfout name="locNum_A1" ezfname="locNum_A1" ezfhyo="A">12345678</label></td>
                                                <td><input type="text" name="xxAllLineAddr_A1" ezfName="xxAllLineAddr_A1" value="6730 SUMATRA ST LAS VEGAS, US, 89166-7017" ezfHyo="A" size="20" style="border:none;background-color:transparent;padding:0px;" /></td>
                                            </tr>
                                        </ezf:skip>
                                    </tbody>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
				<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
					<tr>
						<td align="right"><ezf:inputButton name="Download" value="Download" htmlClass="pBtn6" /></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>





<%-- **** Task specific area ends here **** --%>
