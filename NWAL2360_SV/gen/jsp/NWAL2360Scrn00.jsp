<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171207140124 --%>
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
			<input type="hidden" name="pageID" value="NWAL2360Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Approval Detail">
			
<div style="margin-left:5px; padding:5;">
<fieldset>
<legend>ACTIVE APPROVALS</legend>
	<table border="0" cellpadding="1" cellspacing="0" height="110"  rules="none"  style="padding: 1px; margin-bottom: 2px;">
	<tr>
		<td align="top">
			<table border="0">
				<col align="left">
				<tr>
					<td>
							<!-- Right TBL Header -->
							<div id="rightTopTBL_Item" style=" width:753px; display:block; overflow:hidden;" onscroll="synchroScrollLeft( this.id, new Array( 'rightTBL_Item' ));">
							<!--<div id="rightTopTBL_Item" style="width:800; display:block; overflow:hidden;" >-->
								<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="AHEAD" style="margin-right:0px">
									<col width="116"align="center">
									<col width="224" align="center">
									<col width="224" align="center">
									<col width="40"  align="center">
									<col width="224" align="center">
									<col width="175" align="center">
									<col width="175" align="center">
									<col width="360" align="center">
									<tr class="pEvenNumberBGcolor" height="34">
										<td class="pClothBs">Status</td>
										<td class="pClothBs">Category</td>
										<td class="pClothBs">Description</td>
										<td class="pClothBs">Ver.</td>
										<td class="pClothBs">Assigned To</td>
										<td class="pClothBs">Requested Date</td>
										<td class="pClothBs">Approved Date</td>
										<td class="pClothBs">Approval Notes</td>
									</tr>
								</table>
							</div>
							<!-- Right TBL Main -->
							<div id="rightTBL_Item" style="height:480; width:770px; display:block; overflow-y:scroll; overflow-x:scroll;" onscroll="synchroScrollLeft( this.id, new Array( 'rightTopTBL_Item' ));">
								<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="A" style="margin-right:0px">
									<col width="116" align="center">
									<col width="224" align="center">
									<col width="224" align="center">
									<col width="40" align="center">
									<col width="224" align="center">
									<col width="175" align="center">
									<col width="175" align="center">
									<col width="360" align="center">
									<tbody>
										<ezf:row ezfHyo="A">
										<tr height="22">
											<td>
												<ezf:inputText name="somStsTxt" ezfName="somStsTxt" value="Approved" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"border:none; background-color:transparent;\""/>
											</td>
											<td>
												<ezf:inputText name="somApvlTpTxt" ezfName="somApvlTpTxt" value="Profitability - Branch/Area/Mad" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\""/>
											</td>
											<td>
												<ezf:inputText name="somApvlDescTxt" ezfName="somApvlDescTxt" value="Profitability: 5.57%" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\""/>
											</td>
											<td>
												<ezf:inputText name="somVrsnId" ezfName="somVrsnId" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"4\" style=\"border:none; background-color:transparent;\""/>
											</td>
											<td>
												<ezf:inputText name="somApvrNm" ezfName="somApvrNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\""/>
											</td>
											<td>
												<ezf:inputText name="xxDtTm_FR" ezfName="xxDtTm_FR" value="mm/dd/yyyy" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"23\" style=\"border:none; background-color:transparent;\""/>
											</td>
											<td>
												<ezf:inputText name="xxDtTm_TO" ezfName="xxDtTm_TO" value="mm/dd/yyyy" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"23\" style=\"border:none; background-color:transparent;\""/>
											</td>
											<td>
												<ezf:inputText name="apvlNoteTxt" ezfName="apvlNoteTxt" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"4000\" style=\"border:none; background-color:transparent;\""/>
											</td>
										</tr>
										</ezf:row>
									</tbody>
								</table>
							</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</fieldset>
</div>

<%-- **** Task specific area ends here **** --%>
