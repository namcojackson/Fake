<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160610222448 --%>
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
			<input type="hidden" name="pageID" value="NMAL2570Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Resource Lookup">

<%@ page import="business.servlet.NMAL2570.NMAL2570Bean" %>

<center>
	<table width="100%">
		<col>
		<tr>
			<td>
				<fieldset>
					<legend style="font-size:12px;">Resource Search Filters</legend>
					<table style="table-layout:fixed;margin:0px 0px 0px 170px;" cellspacing="0" cellpadding="0">
						<col valign="top" width="200">
						<col valign="top" width="105">
						<col valign="top" width="10">
						<col valign="top" width="105">
						<col valign="top" width="250">
							<td class="stab">Resource Name(*)</td>
							<td colspan="4">
								<ezf:inputText name="xxPsnNm_H1" ezfName="xxPsnNm_H1" otherAttr=" size=\"60\" maxlength=\"60\""/>
							</td>
						</tr>
						<tr>
							<td class="stab">Employee ID(*)</td>
							<td colspan="4">
								<ezf:inputText name="psnCd_H1" ezfName="psnCd_H1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
							</td>
						</tr>
						<tr>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_CommonPopUp" >Job Code</ezf:anchor></td>
							<td colspan="4">
								<ezf:inputText name="jobTtlCd_H1" ezfName="jobTtlCd_H1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
							</td>
						</tr>
						<tr>
							<td class="stab">Resource#</td>
							<td colspan="4">
								<ezf:inputText name="psnNum_H1" ezfName="psnNum_H1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
							</td>
						</tr>
						<tr>
							<td class="stab">Role Name</td>
							<td colspan="4">
								<ezf:select name="orgFuncRoleTpCd_H1" ezfName="orgFuncRoleTpCd_H1" ezfBlank="1" ezfCodeName="orgFuncRoleTpCd_H2" ezfDispName="orgFuncRoleTpNm_H1" />
							</td>
						</tr>
						<tr>
							<td class="stab">Territory Name(*)</td>
							<td colspan="4">
								<ezf:inputText name="orgNm_H1" ezfName="orgNm_H1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
							</td>
						</tr>
						<tr>
							<td class="stab">Start Date</td>
							<td>
								<ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);" >
							</td>
							<td> - </td>
							<td>
								<ezf:inputText name="effFromDt_H2" ezfName="effFromDt_H2" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H2', 4);" >
							</td>
						</tr>
						<tr>
							<td class="stab">End Date</td>
							<td>
								<ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H1', 4);" >
							</td>
							<td> - </td>
							<td>
								<ezf:inputText name="effThruDt_H2" ezfName="effThruDt_H2" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H2', 4);" >
							</td>
						</tr>
						<tr>
							<td class="stab">Show Sales Role assginment Resource</td>
							<td colspan="2">
								<ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" />
							</td>
							<td class="stab" colspan="2"><span>Has Email Address<span><ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" /></td>
							<td align="right">
								 <ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" otherAttr=" tabindex=\"\""/>
							</td>
						</tr>
					</table>
				</fieldset>
			</td>
		</tr>
		<tr>
		</tr>
		<tr>
			<td align="right">
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
				<!-- Prev/Next Page-->
					<table width="100%">
						<tr>
							<td align="right" style="padding-right:8px;">
								<ezf:skip>
								<table border="0" cellpadding="0" cellspacing="0">
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
										<td class="pOut">6</td>
										<td class="stab">of</td>
										<td class="pOut">6</td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Prev"  id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Next"  id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')" disabled></td>
									</tr>
								</table>
								</ezf:skip>
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
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>
			</td>
		</tr>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0">
					<col align="left" valign="top">
					<tr>
						<td>
							<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
								<col align="center" width="23">
								<col align="center" width="90">
								<col align="center" width="65">
								<col align="center" width="100">
								<col align="center" width="100">
								<col align="center" width="220">
								<col align="center" width="75">
								<col align="center" width="150">
								<col align="center" width="78">
								<col align="center" width="78">
								<tr height="32">
									<td class="pClothBs">&nbsp;</td>
									<td class="pClothBs">Resource#</td>
									<td class="pClothBs">Employee</td>
									<td class="pClothBs">Last Name</td>
									<td class="pClothBs">First Name</td>
									<td class="pClothBs">Job Code</td>
									<td class="pClothBs">Employee ID</td>
									<td class="pClothBs">Supervisor</td>
									<td class="pClothBs">Employment<br>Start Date</td>
									<td class="pClothBs">Employment<br>End Date</td>
								</tr>
							</table>
						<% if ("M".equals(((NMAL2570Bean)databean).getXxModeCd_H1())) { %>
							<div style="overflow-x:hidden; width:1000; height:162; overflow-y:scroll;">
						<% } else { %>
							<div style="overflow-x:hidden; width:1000; height:297; overflow-y:scroll;">
						<% } %>
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A">
									<col width="23">
									<col width="90">
									<col width="65">
									<col width="100">
									<col width="100">
									<col width="220">
									<col width="75">
									<col width="150">
									<col width="78">
									<col width="78">
									<ezf:row ezfHyo="A">
									<tr>
										<td>
											<% if ("M".equals(((NMAL2570Bean)databean).getXxModeCd_H1())) { %>
											<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" />
											<% } else { %>
											&nbsp;
											<% } %>
										</td>
										<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_ResourceNumber" ><ezf:label name="psnNum_A1" ezfName="psnNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
										<td><ezf:label name="psnTpNm_A1" ezfName="psnTpNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="psnLastNm_A1" ezfName="psnLastNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="psnFirstNm_A1" ezfName="psnFirstNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="jobTtlNm_A1" ezfName="jobTtlNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="psnCd_A1" ezfName="psnCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="hrSupvNm_A1" ezfName="hrSupvNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="effFromDt_A1" ezfName="effFromDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
									</tr>
									</ezf:row>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	<% if ("M".equals(((NMAL2570Bean)databean).getXxModeCd_H1())) { %>
<%-- ######################################## Selected Rows ###################################### --%>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0">
					<col width="885">
					<col width="60">
					<col width="60">
					<tr>
						<td class="stab">Selected Resource#</td>
						<td><ezf:inputButton name="AddSelected" value="Add" htmlClass="pBtn3" /></td>
						<td><ezf:inputButton name="DeleteSelected" value="Delete" htmlClass="pBtn3" /></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0">
					<col align="left" valign="top">
					<tr>
						<td>
							<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
								<col align="center" width="23">
								<col align="center" width="90">
								<col align="center" width="65">
								<col align="center" width="100">
								<col align="center" width="100">
								<col align="center" width="220">
								<col align="center" width="75">
								<col align="center" width="150">
								<col align="center" width="78">
								<col align="center" width="78">
								<tr height="32">
									<td class="pClothBs">&nbsp;</td>
									<td class="pClothBs">Resource#</td>
									<td class="pClothBs">Employee</td>
									<td class="pClothBs">Last Name</td>
									<td class="pClothBs">First Name</td>
									<td class="pClothBs">Job Code</td>
									<td class="pClothBs">Employee ID</td>
									<td class="pClothBs">Supervisor</td>
									<td class="pClothBs">Employment<br>Start Date</td>
									<td class="pClothBs">Employment<br>End Date</td>
								</tr>
							</table>
							<div style="overflow-x:hidden; width:1000; height:75; overflow-y:scroll;">
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="B">
									<col width="23">
									<col width="90">
									<col width="65">
									<col width="100">
									<col width="100">
									<col width="220">
									<col width="75">
									<col width="150">
									<col width="78">
									<col width="78">
									<ezf:row ezfHyo="B">
									<tr>
										<td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
										<td><ezf:label name="psnNum_B1" ezfName="psnNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
										<td><ezf:label name="psnTpNm_B1" ezfName="psnTpNm_B1" ezfHyo="B" ezfArrayNo="0" /></td>
										<td><ezf:label name="psnLastNm_B1" ezfName="psnLastNm_B1" ezfHyo="B" ezfArrayNo="0" /></td>
										<td><ezf:label name="psnFirstNm_B1" ezfName="psnFirstNm_B1" ezfHyo="B" ezfArrayNo="0" /></td>
										<td><ezf:label name="jobTtlNm_B1" ezfName="jobTtlNm_B1" ezfHyo="B" ezfArrayNo="0" /></td>
										<td><ezf:label name="psnCd_B1" ezfName="psnCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
										<td><ezf:label name="hrSupvNm_B1" ezfName="hrSupvNm_B1" ezfHyo="B" ezfArrayNo="0" /></td>
										<td><ezf:label name="effFromDt_B1" ezfName="effFromDt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
										<td><ezf:label name="effThruDt_B1" ezfName="effThruDt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
									</tr>
									</ezf:row>
									<ezf:skip>
									<tr>
										<td><input type="checkbox" class="" value="Y" name="xxChkBox_B1" ezfname="xxChkBox_B1" ezfhyo="B" ></td>
										<td><label ezfout name="psnNum_B1" ezfname="psnNum_B1" ezfhyo="B">M08209</label></td>
										<td><label ezfout name="psnTpNm_B1" ezfname="psnTpNm_B1" ezfhyo="B">Employee</label></td>
										<td><label ezfout name="psnLastNm_B1" ezfname="psnLastNm_B1" ezfhyo="B">Berger</label></td>
										<td><label ezfout name="psnFirstNm_B1" ezfname="psnFirstNm_B1" ezfhyo="B">Gary</label></td>
										<td><label ezfout name="jobTtlNm_B1" ezfname="jobTtlNm_B1" ezfhyo="B">CB1056 ACCOUNT EXECUTIVE</label></td>
										<td><label ezfout name="psnCd_B1" ezfname="psnCd_B1" ezfhyo="B">M05669</label></td>
										<td><label ezfout name="hrSupvNm_B1" ezfname="hrSupvNm_B1" ezfhyo="B">Bollinger, Frederic</label></td>
										<td><label ezfout name="effFromDt_B1" ezfname="effFromDt_B1" ezfhyo="B">07/01/2015</label></td>
										<td><label ezfout name="effThruDt_B1" ezfname="effThruDt_B1" ezfhyo="B">07/01/2015</label></td>
									</tr>
									<tr>
										<td><input type="checkbox" class="" value="Y" name="xxChkBox_B1" ezfname="xxChkBox_B1" ezfhyo="B" ></td>
										<td><label ezfout name="psnNum_B1" ezfname="psnNum_B1" ezfhyo="B">M08209</label></td>
										<td><label ezfout name="psnTpNm_B1" ezfname="psnTpNm_B1" ezfhyo="B">Employee</label></td>
										<td><label ezfout name="psnLastNm_B1" ezfname="psnLastNm_B1" ezfhyo="B">Berger</label></td>
										<td><label ezfout name="psnFirstNm_B1" ezfname="psnFirstNm_B1" ezfhyo="B">Gary</label></td>
										<td><label ezfout name="jobTtlNm_B1" ezfname="jobTtlNm_B1" ezfhyo="B">CB1056 ACCOUNT EXECUTIVE</label></td>
										<td><label ezfout name="psnCd_B1" ezfname="psnCd_B1" ezfhyo="B">M05669</label></td>
										<td><label ezfout name="hrSupvNm_B1" ezfname="hrSupvNm_B1" ezfhyo="B">Bollinger, Frederic</label></td>
										<td><label ezfout name="effFromDt_B1" ezfname="effFromDt_B1" ezfhyo="B">07/01/2015</label></td>
										<td><label ezfout name="effThruDt_B1" ezfname="effThruDt_B1" ezfhyo="B">07/01/2015</label></td>
									</tr>
									</ezf:skip>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	<% } %>

	</table>
</center>




<%-- **** Task specific area ends here **** --%>
