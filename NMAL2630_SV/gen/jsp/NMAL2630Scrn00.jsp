<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180327104627 --%>
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
			<input type="hidden" name="pageID" value="NMAL2630Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Territory Search">

<center>
	<table width="100%">
		<col valign="top">
		<tr>
			<td>
				<div class="pTab_BODY">
					<table style="table-layout:fixed;">
						<col valign="top" width="120">
						<col valign="top" width="270">
						<col valign="top" width="110">
						<col valign="top" width="250">
						<tr>
							<td class="stab">Business Area</td>
							<td>
								<ezf:select name="orgStruTpCd_H1" ezfName="orgStruTpCd_H1" ezfCodeName="orgStruTpCd_H2" ezfDispName="orgStruTpNm_H1" />
							</td>
						</tr>
						<tr>
							<td class="stab">Territory Name(*)</td>
							<td>
								<ezf:inputText name="orgNm_H1" ezfName="orgNm_H1" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
							</td>
							<td class="stab">Territory ID</td>
							<td>
								<ezf:inputText name="orgCd_H1" ezfName="orgCd_H1" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
							</td>
						</tr>
						<tr>
							<td class="stab">Parent Territory Name(*)</td>
							<td>
								<ezf:inputText name="orgNm_H2" ezfName="orgNm_H2" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
							</td>
							<td class="stab">Tier</td>
							<td>
								<ezf:select name="orgTierCd_H1" ezfName="orgTierCd_H1" ezfBlank="1" ezfCodeName="orgTierCd_H2" ezfDispName="orgTierNm_H1" />
							</td>
						</tr>
						<tr>
							<td class="stab">Resource Name(*)</td>
							<td>
								<ezf:inputText name="xxPsnNm_H1" ezfName="xxPsnNm_H1" otherAttr=" size=\"30\" maxlength=\"62\""/>
							</td>
							<td class="stab">Territory Type</td>
							<td>
								<ezf:select name="trtyTpCd_H1" ezfName="trtyTpCd_H1" ezfBlank="1" ezfCodeName="trtyTpCd_H2" ezfDispName="trtyTpDescTxt_H1" />
							</td>
						</tr>
						<tr>
							<td class="stab">Employee ID(*)</td>
							<td>
								<ezf:inputText name="psnCd_H1" ezfName="psnCd_H1" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
								<!-- Add Start 2017/11/28 Hd.Sugawara QC#21044 -->
								<ezf:inputHidden name="nonSlsRepFlg_H1" ezfName="nonSlsRepFlg_H1" otherAttr=" size=\"0\" id=\"nonSlsRepFlg_H1\""/>
								<!-- Add End   2017/11/28 Hd.Sugawara QC#21044 -->
							</td>
							<td class="stab">Territory Group</td>
							<td>
								<ezf:select name="trtyGrpTpCd_H1" ezfName="trtyGrpTpCd_H1" ezfBlank="1" ezfCodeName="trtyGrpTpCd_H2" ezfDispName="trtyGrpTpDescTxt_H1" />
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
							<td class="stab">Organization Name(*)</td>
							<td>
								<ezf:inputText name="orgNm_H3" ezfName="orgNm_H3" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/>
							</td>
						</tr>
					</table>
					<fieldset>
						<c:choose>
							<c:when test="${pageScope._ezddatabean.xxDplyTab=='AdvancedSearch'}">
								<legend style="font-size:12px;"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Link_AdvancedSearch" >Advanced Search</ezf:anchor><img src="./img/biz/downarrow1.png" style="height:14px;" align="middle"></legend>
								<table style="table-layout:fixed;">
									<col width="460" valign="top">
									<col width="160" valign="top">
									<tr>
										<td>&nbsp</td>
										<td>
											<ezf:inputButton name="InsertRow" value="Add" htmlClass="pBtn5" otherAttr=" tabindex=\"\""/>
											<ezf:inputButton name="DeleteRow" value="Delete" htmlClass="pBtn5" otherAttr=" tabindex=\"\""/>
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="0" cellspacing="0">
									<col align="left" valign="top">
									<tr>
										<td>
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
												<col align="center" width="30">
												<col align="center" width="200">
												<col align="center" width="200">
												<col align="center" width="80">
												<col align="center" width="80">
												<tr height="18">
													<td class="pClothBs">&nbsp</td>
													<td class="pClothBs">Territory Rule Type</td>
													<td class="pClothBs">Operand</td>
													<td class="pClothBs">Value From</td>
													<td class="pClothBs">Value To</td>
												</tr>
											</table>
											<div style="overflow-x:hidden; width:610; height:74px; overflow-y:scroll;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
													<col width="30" align="center">
													<col width="200">
													<col width="200">
													<col width="80">
													<col width="80">
													<ezf:row ezfHyo="B">
													<tr>
														<td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_B1#{EZF_ROW_NUMBER}\""/></td>
														<td><ezf:select name="trtyRuleTpCd_B1" ezfName="trtyRuleTpCd_B1" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="trtyRuleTpCd_B2" ezfDispName="trtyRuleTpNm_B1" otherAttr=" style=\"width:196px\""/>
														</td>
														<td><ezf:select name="trtyRuleOprdTpCd_B1" ezfName="trtyRuleOprdTpCd_B1" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="trtyRuleOprdTpCd_B2" ezfDispName="trtyRuleOprdTpNm_B1" otherAttr=" style=\"width:196px\""/>
														</td>
														<td><ezf:inputText name="trtyRuleFromValTxt_B1" ezfName="trtyRuleFromValTxt_B1" value="0000000001" ezfHyo="B" ezfArrayNo="0" htmlClass="stab" otherAttr=" size=\"10\""/></td>
														<td><ezf:inputText name="trtyRuleThruValTxt_B1" ezfName="trtyRuleThruValTxt_B1" value="1000000001" ezfHyo="B" ezfArrayNo="0" htmlClass="stab" otherAttr=" size=\"10\""/></td>
													</tr>
													</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</c:when>
							<c:when test="${pageScope._ezddatabean.xxDplyTab==''}">
								<legend style="font-size:12px;"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Link_AdvancedSearch" >Advanced Search</ezf:anchor><img src="./img/biz/rightarrow1.png" style="height:14px;" align="middle"></legend>
							</c:when>
						</c:choose>
					</fieldset>
				</div>
			</td>
		</tr>
	</table>
  <table style="table-layout:fixed;"  width="900">
  	<tr>
  		<td>&nbsp
  		</td>
		<td align="right">
		  <ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" />
		</td>
	  </tr>
	  <tr>
	  	<td colspan="2" align="right">
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
	<table style="table-layout:fixed;">
		<col valign="top" align="left">
		<tr>
			<td>
				<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
					<col align="center" width="140">
					<col align="center" width="80">
					<col align="center" width="100">
					<col align="center" width="100">
					<col align="center" width="70">
					<col align="center" width="190">
					<col align="center" width="140">
					<col align="center" width="80">
					<col align="center" width="80">
					<tr height="18">

						<td class="pClothBs">Territory Name</td>
						<td class="pClothBs">Role</td>
						<td class="pClothBs">Territory Group</td>
						<td class="pClothBs">Territory Type</td>
						<td class="pClothBs">Territory ID</td>
						<td class="pClothBs">Parent Territory Name</td>
						<td class="pClothBs">Resource Name</td>
						<td class="pClothBs">Start Date</td>
						<td class="pClothBs">End Date</td>
					</tr>
				</table>
						<c:choose>
							<c:when test="${pageScope._ezddatabean.xxDplyTab=='AdvancedSearch'}">
				<div style="overflow-x:hidden; overflow-y:scroll; width:1000; height:200;" >
							</c:when>
							<c:when test="${pageScope._ezddatabean.xxDplyTab==''}">
				<div style="overflow-x:hidden; overflow-y:scroll; width:1000; height:320;" >
							</c:when>
						</c:choose>



					<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A">
						<col width="140">
						<col width="80">
						<col width="100">
						<col width="100">
						<col width="70">
						<col width="190">
						<col width="140">
						<col width="80">
						<col width="80">
						<ezf:row ezfHyo="A">
						<tr>
							<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_TerritoryName" ><ezf:label name="orgNm_A1" ezfName="orgNm_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
							<td><ezf:label name="acctTeamRoleTpDescTxt_A1" ezfName="acctTeamRoleTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="trtyGrpTpDescTxt_A1" ezfName="trtyGrpTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="trtyTpDescTxt_A1" ezfName="trtyTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="orgCd_A1" ezfName="orgCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="orgNm_A2" ezfName="orgNm_A2" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="xxPsnNm_A1" ezfName="xxPsnNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="effFromDt_A1" ezfName="effFromDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
						</tr>
						</ezf:row>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>







<%-- **** Task specific area ends here **** --%>
