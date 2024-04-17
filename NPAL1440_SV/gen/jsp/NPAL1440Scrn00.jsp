<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20240325104749 --%>
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
			<input type="hidden" name="pageID" value="NPAL1440Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Request History">

<center>
	<table width="100%">
		<tr>
			<td valign="top">
                <%-- ######################################## HEADER ######################################## --%>
				<table style="margin-left: 10px;" width="100%" cellpadding="1" cellspacing="1" align="center" border="0">
					<tr>
						<td>
							<table border="0">
                                <col width="85" align="left">
                                <col width="150"    align="left">
                                <col width="85"    align="left">
                                <col width="200"    align="left">
                                <col width="85"    align="left">
                                <col width="150"    align="left">
								<tr height="20">
									<td class ="stab">Request #</td>
									<td><ezf:inputText name="prchReqNum_H" ezfName="prchReqNum_H" value="12345678" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/></td>
									<td class ="stab">Request Type</td>
									<td><ezf:inputText name="prchReqTpDescTxt_H" ezfName="prchReqTpDescTxt_H" value="Tech Request" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
									<td class ="stab">Create Date</td>
									<td><ezf:inputText name="prchReqCratDt_H" ezfName="prchReqCratDt_H" value="MM/DD/YYYY" otherAttr=" size=\"11\" maxlength=\"20\" ezftoupper=\"\""/></td>
								</tr>
								<tr height="20">
								</tr>
								<tr height="20">
									<td class ="stab">Header Status</td>
									<td><ezf:inputText name="prchReqStsDescTxt_H" ezfName="prchReqStsDescTxt_H" value="Open" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/></td>
									<td class ="stab">Requester</td>
									<td><ezf:inputText name="fullPsnNm_H" ezfName="fullPsnNm_H" value="Yasuto Nishiwaki" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

                <hr>
				  <%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
					<TABLE cellSpacing="0" cellPadding="1" width="99%" border="0">
						<TBODY>
							<tr align="right">
								  <td style="padding-right: 16px;">
								  <jsp:include page="../tablePagenation/S21TablePagenation.jsp">
									  <jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
									  <jsp:param name="TableNm"     value="A" />
									  <jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
									  <jsp:param name="ShowingTo"   value="xxPageShowToNum" />
									  <jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
								  </jsp:include>
								  </td>
							 </tr>
						</TBODY>
					</TABLE>
				  <%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

     <!-- ################################################## Search Result   - [START] ################################################## -->
                <table style="MARGIN-LEFT: 10px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td align="left" valign="top">
                            <div id="topTBL" style="overflow-y:none; overflow-x:hidden; width:750;" onscroll="synchroScrollLeft(this.id, new Array('btmTBL'));">
                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" height="38">
                                    <col width="25"   align="right"><!-- # -->
                                    <col width="45"   align="left"><!-- Line# -->
                                    <col width="125"  align="left"><!-- Action Date/TIme -->
                                    <col width="150"  align="left"><!-- User Name -->
                                    <col width="150"  align="left"><!-- Program -->
                                    <col width="150"  align="left"><!-- Mode -->
                                    <col width="150"  align="left"><!-- Event -->
                                    <col width="150"  align="left"><!-- Approval Status -->
                                    <col width="150"  align="left"><!-- Line Status -->
                                    <col width="120"  align="left"><!-- Item Number -->
                                    <col width="185"  align="left"><!-- Item Description -->
                                    <col width="100"  align="left"><!-- Request Qty -->
                                    
                                    <col width="95"  align="left"><!-- Source Type -->
                                    <col width="100"  align="left"><!-- Source WH -->
                                    <col width="100"  align="left"><!-- Source SWH -->
                                    <col width="50"  align="center"><!-- Freeze Flag -->
                                    <col width="100"  align="left"><!-- Shipped Qty -->
                                    <col width="100"  align="left"><!-- Received Qty -->
                                    <col width="150"  align="left"><!-- Final Receiver -->
                                    
                                    <tr>
                                        <td class="pClothBs">#</td>
                                        <td class="pClothBs">Line#</td>
                                        <td class="pClothBs">Action Date/Time</td>
                                        <td class="pClothBs">User Name</td>
                                        <td class="pClothBs">Program</td>
                                        <td class="pClothBs">Mode</td>
                                        <td class="pClothBs">Event</td>
                                        
                                        <td class="pClothBs">Approval Status</td>
                                        <td class="pClothBs">Line Status</td>
                                        <td class="pClothBs">Item#</td>
                                        <td class="pClothBs">Item Description</td>
                                        <td class="pClothBs">Request Qty</td>
                                        
                                        <td class="pClothBs">Source Type</td>
                                        <td class="pClothBs">Source WH</td>
                                        <td class="pClothBs">Source SWH</td>
                                        <td class="pClothBs">Freeze</td>
                                        <td class="pClothBs">Shipped Qty</td>
                                        <td class="pClothBs">Received Qty</td>
                                        <td class="pClothBs">Final Receiver</td>
                                       
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                    <tr valign="top">
                        <td>
                            <div id="btmTBL" style="overflow-y:scroll; height:430; overflow-x:scroll; width:767"
                                    onScroll="synchroScrollLeft(this.id, new Array('topTBL'));">
                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Left">
                                    <col width="25"   align="right"><!-- # -->
                                    <col width="45"   align="left"><!-- Line# -->
                                    <col width="125"  align="left"><!-- Action Date/TIme -->
                                    <col width="150"  align="left"><!-- User Name -->
                                    <col width="150"  align="left"><!-- Program -->
                                    <col width="150"  align="left"><!-- Mode -->
                                    <col width="150"  align="left"><!-- Event -->
                                    <col width="150"  align="left"><!-- Approval Status -->
                                    <col width="150"  align="left"><!-- Line Status -->
                                    <col width="120"  align="left"><!-- Item Number -->
                                    <col width="185"  align="left"><!-- Item Description -->
                                    <col width="100"  align="left"><!-- Request Qty -->
                                    
                                    <col width="95"  align="left"><!-- Source Type -->
                                    <col width="100"  align="left"><!-- Source WH -->
                                    <col width="100"  align="left"><!-- Source SWH -->
                                    <col width="50"  align="center"><!-- Freeze Flag -->
                                    <col width="100"  align="left"><!-- Shipped Qty -->
                                    <col width="100"  align="left"><!-- Received Qty -->
                                    <col width="150"  align="left"><!-- Final Receiver -->
                                    
                                    <ezf:row ezfHyo="A">
                                        <!-- 1 -->
                                        <tr height="25">
                                            <td><ezf:label name="xxNum_D1" ezfName="xxNum_D1" ezfHyo="A" ezfArrayNo="0" /></td>
                                            <td><ezf:label name="xxPopPrm_D1" ezfName="xxPopPrm_D1" ezfHyo="A" ezfArrayNo="0" /></td>
                                            <td><ezf:label name="xxPopPrm_D2" ezfName="xxPopPrm_D2" ezfHyo="A" ezfArrayNo="0" /></td>
                                            <td><ezf:inputText name="fullPsnNm_D1" ezfName="fullPsnNm_D1" value="Yasuto Nishiwaki" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"62\""/></td>
                                            <td><ezf:inputText name="ezInAplID_D1" ezfName="ezInAplID_D1" value="NPAL0110BL06" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                            <td><ezf:inputText name="prchReqLogModeTxt_D1" ezfName="prchReqLogModeTxt_D1" value="Create" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                            <td><ezf:inputText name="prchReqLogEventDescTxt_D1" ezfName="prchReqLogEventDescTxt_D1" value="Request" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                            <td><ezf:inputText name="prchReqApvlStsDescTxt_D1" ezfName="prchReqApvlStsDescTxt_D1" value="Entered" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                            <td><ezf:inputText name="prchReqLineStsDescTxt_D1" ezfName="prchReqLineStsDescTxt_D1" value="Open" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                            <td><ezf:label name="mdseCd_D1" ezfName="mdseCd_D1" ezfHyo="A" ezfArrayNo="0" /></td>
                                            <td><ezf:inputText name="mdseDescShortTxt_D1" ezfName="mdseDescShortTxt_D1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"30\""/></td>
                                            <td><ezf:inputText name="prchReqQty_D1" ezfName="prchReqQty_D1" value="9,999,999,999" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right; \" size=\"13\" maxlength=\"30\""/></td>
                                            <td><ezf:inputText name="procrTpDescTxt_D1" ezfName="procrTpDescTxt_D1" value="Warehouse" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\""/></td>
                                            <td><ezf:inputText name="prntVndNm_D1" ezfName="prntVndNm_D1" value="1Z1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"240\""/></td>
                                            <td><ezf:inputText name="locNm_D1" ezfName="locNm_D1" value="NEW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"60\""/></td>
                                            <td><ezf:label name="prchReqFrzFlg_D1" ezfName="prchReqFrzFlg_D1" ezfHyo="A" ezfArrayNo="0" /></td>
                                            <td><ezf:inputText name="shipQty_D1" ezfName="shipQty_D1" value="0" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right; \" size=\"13\" maxlength=\"30\""/></td>
                                            <td><ezf:inputText name="rwsPutAwayQty_D1" ezfName="rwsPutAwayQty_D1" value="0" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right; \" size=\"13\" maxlength=\"30\""/></td>
                                            <td><ezf:inputText name="techCd_D1" ezfName="techCd_D1" value="Yasuto Nishiwaki" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"62\""/></td>
                                        </tr>
                                    </ezf:row>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
        <!-- ################################################## Search Result   - [E N D] ################################################## -->
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
