<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20130531105537 --%>
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
			<input type="hidden" name="pageID" value="NLBL0060Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="WH Lead Time">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
			
<%-- ######################################## HEADER ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table>
						<col width="16">
                        <%-- 2013/05/20 R-OM025 Inventory Transaction Add Start --%>
                        <col width="50">
                        <%-- 2013/05/20 R-OM025 Inventory Transaction Add End --%>
						<col width="131">
						<col width="5">
                        <%-- 2013/05/20 R-OM025 Inventory Transaction Modify Start --%>
                        <col width="90">
                        <%-- 2013/05/20 R-OM025 Inventory Transaction Modify End --%>
						<col width="111">
						<col width="10" align="center">
						<col width="111">
                        <%-- 2013/05/20 R-OM025 Inventory Transaction Modify Start --%>
                        <col width="250">
                        <%-- 2013/05/20 R-OM025 Inventory Transaction Modify End --%>
						<col width="0">

						<tr height="30">
                            <%-- 2013/05/20 R-OM025 Inventory Transaction Delete Start
                            <td class="stab">WH</td>
                                 2013/05/20 R-OM025 Inventory Transaction Delte End --%>
                            <%-- 2013/05/20 R-OM025 Inventory Transaction Add Start --%>
                            <td class="stab"><ezf:anchor name="xxLinkProt_H2" ezfName="xxLinkProt_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Location_Info" >WH</ezf:anchor></td>
                            <%-- 2013/05/20 R-OM025 Inventory Transaction Add End --%>
							<td>
                                <%-- 2013/05/20 R-OM025 Inventory Transaction Delete Start
                                <ezf:select name="whCd_H2" ezfName="whCd_H2" ezfCodeName="whCd_H1" ezfDispName="xxEdtCdNm_H1" />
                                     2013/05/20 R-OM025 Inventory Transaction Delte End --%>
                                <%-- 2013/05/20 R-OM025 Inventory Transaction Add Start --%>
                                <ezf:inputText name="whCd_H2" ezfName="whCd_H2" value="WWWWWWWWWWWWWWWWWWWW" otherAttr=" size=\"6\" maxlength=\"20\" ezftoupper=\"\""/>
                            </td>
                            <td>
                                <ezf:inputText name="locNm_H2" ezfName="locNm_H2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" otherAttr=" size=\"40\" maxlength=\"60\" tabindex=\"-1\""/>
                                <%-- 2013/05/20 R-OM025 Inventory Transaction Add End --%>
							</td>
							<td></td>
							<td class="stab">Effective Period</td>
							<td>
								<ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" value="06/15/2008" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'effFromDt_H1', 4 );">
							</td>
							<td class="stab">-</td>
							<td>
								<ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" value="12/15/2008" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'effThruDt_H1', 4 );">
							</td>
							<td></td>
							<td align="left"><ezf:inputButton name="OnClick_Search" value="Search" htmlClass="pBtn6" /></td>
						</tr>
					</table>
					
					<hr>
					
<%-- ######################################## DETAIL ######################################## --%>
					<table border="0" cellpadding="1" cellspacing="1">
						<tr height="25">
							<td width="1082" align="right">
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="400" align="right">
									<tr>
										<td>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="A" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A1" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum_A1" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A1" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

                    <%-- 2013/05/20 R-OM025 Inventory Transaction Modify Start --%>
                    <table border="1" cellpadding="1" cellspacing="0" width="1085">
                        <col width="25" align="center">
                        <col width="356" align="center">
                        <col width="114" align="center">
                        <col width="114" align="center">
                        <col width="104" align="center">
                        <col width="134" align="center">
                        <col width="110" align="center">
                        <col width="65" align="center">
                    <%-- 2013/05/20 R-OM025 Inventory Transaction Modify End --%>
						<tr>
							<!-- title header -->
							<td class="pClothBs" rowspan="2">&nbsp;</td>
                            <%-- 2013/05/20 R-OM025 Inventory Transaction Modify Start --%>
                            <td class="pClothBs" rowspan="2">Warehouse</td>
                            <%-- 2013/05/20 R-OM025 Inventory Transaction Modify End --%>
							<td class="pClothBs" colspan="2">Effective Period</td>
							<td class="pClothBs" rowspan="2">Shipping Mode</td>
							<td class="pClothBs" rowspan="2">Service Level</td>
							<td class="pClothBs" rowspan="2">Shipping Closing<br>Time(EST, 24H)</td>
							<td class="pClothBs" rowspan="2">Lead Time<br>(day(s))</td>
						</tr>
						<tr>
							<!-- Event -->
							<td class="pClothBs">Start</td>
							<td class="pClothBs">End</td>
						</tr>
					</table>

					<!-- id:TBL -->
                    <%-- 2013/05/20 R-OM025 Inventory Transaction Modify Start --%>
                    <div id="TBL" style="overflow-y:scroll; height:394; overflow-x:hidden; width:1102;">
                        <table border="1" cellpadding="1" cellspacing="0" width="1085" id="A">
                            <col width="25" align="center">
                            <col width="356" align="left">
                            <col width="232" align="left">
                            <col width="104" align="left">
                            <col width="134" align="left">
                            <col width="110" align="right">
                            <col width="65" align="right">
                    <%-- 2013/05/20 R-OM025 Inventory Transaction Modify Start --%>

							<ezf:row ezfHyo="A">
								<tr height="28">
									<td><ezf:inputRadio name="xxRadioBtn_A1" ezfName="xxRadioBtn_A1" value="{EZF_ROW_NUMBER}" otherAttr=" id=\"radio#{EZF_ROW_NUMBER}\""/></td>
									<td>
                                        <%-- 2013/05/20 R-OM025 Inventory Transaction Delete Start
                                        <ezf:select name="whCd_A2" ezfName="whCd_A2" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="whCd_A1" ezfDispName="xxEdtCdNm_A1" ezfCodeDispHyo="A" otherAttr=" id=\"wh#{EZF_ROW_NUMBER}\""/>
                                            2013/05/20 R-OM025 Inventory Transaction Delete End --%>
                                        <%-- 2013/05/20 R-OM025 Inventory Transaction Add Start --%>
                                        <table border="0" cellpadding="0" cellspacing="1">
                                        	<col width="20" align="center">
                                        	<col width="50" align="left">
                                        	<col width="286" align="left">
                                            <tr>
                                                <td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_Location_Info_For_List" otherAttr=" id=\"whLink#{EZF_ROW_NUMBER}\"">WH</ezf:anchor></td>
                                                <td><ezf:inputText name="whCd_A1" ezfName="whCd_A1" value="WWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"whCd#{EZF_ROW_NUMBER}\" size=\"6\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                <td><ezf:inputText name="locNm_A1" ezfName="locNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"whNm#{EZF_ROW_NUMBER}\" size=\"40\" maxlength=\"60\" tabindex=\"-1\""/></td>
                                            </tr>
                                        </table>
                                        <%-- 2013/05/20 R-OM025 Inventory Transaction Add End --%>
									</td>
									<td>
										<div id="effPer#{EZF_ROW_NUMBER}">
											<ezf:inputText name="effFromDt_A1" ezfName="effFromDt_A1" value="01/01/2000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'effFromDt_A1', 4, '{EZF_ROW_NUMBER}');"> -
											<ezf:inputText name="effThruDt_A1" ezfName="effThruDt_A1" value="07/15/2008" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'effThruDt_A1', 4, '{EZF_ROW_NUMBER}');">
										</div>
									</td>
									<td>
										<div id="shpgModeNm#{EZF_ROW_NUMBER}">
											<ezf:label name="shpgModeNm_A1" ezfName="shpgModeNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"shpgModeNm#{EZF_ROW_NUMBER}\""/>
										</div>
									</td>
									<td><ezf:label name="shpgSvcLvlNm_A1" ezfName="shpgSvcLvlNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:inputText name="shpgCloTmTs_A1" ezfName="shpgCloTmTs_A1" value="1234" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right\" size=\"4\" maxlength=\"4\""/></td>
									<td><ezf:inputText name="xxDplyLeadTmDaysAot_A1" ezfName="xxDplyLeadTmDaysAot_A1" value="12" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right\" size=\"2\" maxlength=\"2\""/></td>
								</tr>
							</ezf:row>
							<ezf:skip>
								<tr height="28">
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>Parcel</label></td>
									<td><label ezfout>1st Day Service</label></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="4" maxlength="4" value="1500"></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
								</tr>
								<tr height="28">
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>2nd Day Service</label></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="4" maxlength="4" value="1500"></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
								</tr>
								<tr height="28">
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>3rd Day Service</label></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="4" maxlength="4" value="1500"></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
								</tr>
								<tr height="28">
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>Ground Service</label></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="4" maxlength="4" value="1500"></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
								</tr>
				
								<tr height="28">
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>LTL</label></td>
									<td><label ezfout>Ground Service</label></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="4" maxlength="4" value="1500"></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
								</tr>
								<tr height="28">
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>Customer Pick up</label></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="4" maxlength="4" value="1500"></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
								</tr>
				
								<tr height="28">
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>TL</label></td>
									<td><label ezfout>Ground Service</label></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="4" maxlength="4" value="1500"></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
								</tr>
								<tr height="28">
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>Customer Pick up</label></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="4" maxlength="4" value="1500"></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
								</tr>
				
								<tr height="28">
									<td><input type="radio" name="selectRadio" value="1"></td>
									<td><label ezfout>&nbsp;</label></td>
									<td>
										<input type="text" class="pHsu" name="perioddateFrom2"  size="10" maxlength="10" value="07/16/2008">
										<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'perioddateFrom2', 4 );"> -
										<input type="text" class="pHsu" name="perioddateTo2"  size="10" maxlength="10" value="12/31/2999">
										<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'perioddateTo2', 4 );">
									</td>
									<td><label ezfout>Parcel</label></td>
									<td><label ezfout>Ground Service</label></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="4" maxlength="4" value="1500"></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
								</tr>
								<tr height="28">
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>1st Day Service</label></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="4" maxlength="4" value="1500"></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
								</tr>
								<tr height="28">
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>2nd Day Service</label></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="4" maxlength="4" value="1500"></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
								</tr>
								<tr height="28">
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>3rd Day Service</label></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="4" maxlength="4" value="1500"></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
								</tr>
								<tr height="28">
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>Ground Service</label></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="4" maxlength="4" value="1500"></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
								</tr>
				
								<tr height="28">
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>LTL</label></td>
									<td><label ezfout>Ground Service</label></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="4" maxlength="4" value="1500"></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
								</tr>
								<tr height="28">
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>Customer Pick up</label></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="4" maxlength="4" value="1500"></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
								</tr>
				
								<tr height="28">
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>TL</label></td>
									<td><label ezfout>Ground Service</label></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="4" maxlength="4" value="1500"></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
								</tr>
								<tr height="28">
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>&nbsp;</label></td>
									<td><label ezfout>Customer Pick up</label></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="4" maxlength="4" value="1500"></td>
									<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
								</tr>
							</ezf:skip>
						</table>
					</div>

					<table border="0" cellpadding="0" cellspacing="0">
						<col width="72">
						<col width="1014">
						<tr>
							<td align="left"><ezf:inputButton name="OnClick_DeleteRow" value="Delete Row" htmlClass="pBtn6" otherAttr=" id=\"btn_deleteRow\""/></td>
							<td align="right"><ezf:inputButton name="OnClick_InsertRow" value="Insert Row" htmlClass="pBtn6" otherAttr=" id=\"btn_InsertRow\""/></td>
						</tr>
					</table>

				</div>

<%-- ######################################## FOOTER ######################################## --%>
				<table border="0" cellpadding="5" cellspacing="1">
					<col width="">
					<col width="">
					<col width="">
					<col width="">
					<col width="444">

					<tr>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
