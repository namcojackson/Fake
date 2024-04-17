<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170223094419 --%>
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
			<input type="hidden" name="pageID" value="NMAL2530Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Organization Lookup">


<center>
	<table width="100%" >
		<col valign="top">
		<tr>
			<td>
				<div class="pTab_BODY">
					<table style="table-layout:fixed;margin:0px 0px 0px 170px;">
						<col valign="top" width="150">
						<col valign="top" width="450">
						<tr>
							<td class="stab">Business Area</td>
							<td>
								<ezf:select name="orgStruTpCd_H1" ezfName="orgStruTpCd_H1" ezfBlank="1" ezfCodeName="orgStruTpCd_H2" ezfDispName="orgStruTpNm_H1" />
							</td>
						</tr>
						<tr>
							<td class="stab">Organization Name(*)</td>
							<td>
								<ezf:inputText name="orgNm_H1" ezfName="orgNm_H1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
							</td>
						</tr>
						<tr>
							<td class="stab">Parent Organization Name(*)</td>
							<td>
								<ezf:inputText name="orgNm_H2" ezfName="orgNm_H2" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
							</td>
						</tr>
						<tr>
							<td class="stab">Resource Name(*)</td>
							<td>
								<ezf:inputText name="tocNm_H1" ezfName="tocNm_H1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
							</td>
						</tr>
						<tr>
							<td class="stab">Employee ID(*)</td>
							<td>
								<ezf:inputText name="psnCd_H1" ezfName="psnCd_H1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
							</td>
						</tr>
						<tr>
							<td class="stab">Tier</td>
							<td>
								<ezf:select name="orgTierCd_H1" ezfName="orgTierCd_H1" ezfBlank="1" ezfCodeName="orgTierCd_H2" ezfDispName="orgTierNm_H1" />
							</td>
						</tr>
						<tr>
							<td class="stab">SCR Region</td>
							<td>
								<ezf:select name="csrRgTpCd_H1" ezfName="csrRgTpCd_H1" ezfBlank="1" ezfCodeName="csrRgTpCd_H2" ezfDispName="csrRgTpDescTxt_H1" />
							</td>
						</tr>
						<tr>
							<td class="stab">Start Date</td>
							<td>
								<ezf:inputText name="effFromDt_FR" ezfName="effFromDt_FR" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_FR', 4);" >
								&ndash;
								<ezf:inputText name="effFromDt_TO" ezfName="effFromDt_TO" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_TO', 4);" >
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td align="right">
				 <ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" otherAttr=" tabindex=\"\""/>
				 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		<tr>
		<td class="stab" >
		    <table>
		    <tr>
			    <td class="stab">
			    	<span style="margin-left:70px">Search Result<span>
		    	</td>
		    	<td width="740px" align="right">
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
		<tr align="center">
			<td>
					<table border="0" cellpadding="0" cellspacing="0" >
						<col align="left" valign="top">
						<tr>
							<td>
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
									<col align="center" width="300">
									<col align="center" width="300">
									<col align="center" width="80">
									<col align="center" width="80">
									<col align="center" width="80">
									<tr height="22">
										<td class="pClothBs">Organization Name</td>
										<td class="pClothBs">Parent Organization</td>
										<td class="pClothBs">Tier</td>
										<td class="pClothBs">Start Date</td>
										<td class="pClothBs">End Date</td>
									</tr>
								</table>
								<div style="overflow-x:hidden; width:860; height:310px; overflow-y:scroll;">
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A">
										<col width="300">
										<col width="300">
										<col width="80">
										<col width="80">
										<col width="80">
										<ezf:row ezfHyo="A">
										<tr height="22">
											<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_OrganizationName" ><ezf:label name="orgNm_A1" ezfName="orgNm_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
											<td><ezf:label name="orgNm_A2" ezfName="orgNm_A2" ezfHyo="A" ezfArrayNo="0" /></a></td>
											<td><ezf:label name="orgTierNm_A1" ezfName="orgTierNm_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
											<td><ezf:label name="effFromDt_A1" ezfName="effFromDt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
											<td><ezf:label name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
											</tr>
										</ezf:row>
										<ezf:skip>
										<tr height="22">
											<td><a href="#"  ezfhyo="A" onclick="sendServer('Select_OrganizationName')"><label ezfout name="orgNm_A1" ezfname="orgNm_A1" ezfhyo="A">ESS SALES OPS</label></a></td>
											<td><label ezfout name="orgNm_A2" ezfname="orgNm_A2" ezfhyo="A">CSA SALES OPS</label></a></td>
											<td><label ezfout name="orgTierNm_A1" ezfname="orgTierNm_A1" ezfhyo="A">20</label></a></td>
											<td><label ezfout name="effFromDt_A1" ezfname="effFromDt_A1" ezfhyo="A">06/01/2015</label></a></td>
											<td><label ezfout name="effThruDt_A1" ezfname="effThruDt_A1" ezfhyo="A">07/01/2015</label></a></td>
										</tr>
										</ezf:skip>
										
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
