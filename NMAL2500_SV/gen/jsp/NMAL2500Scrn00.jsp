<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20181102131107 --%>
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
			<input type="hidden" name="pageID" value="NMAL2500Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Organization Resource Search">
      <!-- include S21BusinessProcessTAB -->
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

<div class="pTab_BODY">
	<center>
		<table width="100%">
			<col valign="top">
			<tr>
				<td>
					<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
						<col width="147">
						<col width="345">
						<col width="110">
						<col width="300">
						<col width="">
						<tr>
							<td class="stab">Saved Search Options</td>
							<td>
								<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_L" ezfDispName="srchOptNm_L" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
							</td>
							<td class="stab">Search Option Name</td>
							<td class="stab"><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
							<td>
								<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
								<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
							</td>
						</tr>
					</table>
					<table style="table-layout:fixed;">
						<col valign="top" width="605">
						<col valign="top" width="435">
						<tr>
							<td class="stab">
								<table style="table-layout:fixed;">
									<col valign="top" width="600">
									<tr>
										<td class="stab">
											Business Area &nbsp;
											<ezf:select name="bizAreaOrgCd_P1" ezfName="bizAreaOrgCd_P1" ezfCodeName="bizAreaOrgCd_H1" ezfDispName="bizAreaOrgNm_H1" otherEvent1=" onchange=\"sendServer('OnChange_BusinessArea')\"" otherAttr=" tabindex=\"1\""/>
										</td>
									</tr>
									<tr>
										<td>
											<fieldset>
											<legend style="font-size:12px;">Organization Search Filters</legend>
												<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
												<col width="110">
												<col width="370">
												<col width="80">
												<col width="30">
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_OrganizationQuickLookup" otherAttr=" tabindex=\"1\"">Organization Name (*)</ezf:anchor></td>
													<td>
														<ezf:inputText name="orgNm_H2" ezfName="orgNm_H2" otherAttr=" size=\"50\" maxlength=\"50\" tabindex=\"1\" ezftoupper=\"\""/>
													</td>
													</tr>
												<tr>
													<td class="stab">Organization Tier</td>
													<td>
														<ezf:select name="orgTierCd_P2" ezfName="orgTierCd_P2" ezfBlank="1" ezfCodeName="orgTierCd_H2" ezfDispName="orgTierNm_H2" otherAttr=" tabindex=\"1\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">CSR Region</td>
													<td>
														<ezf:select name="csrRgTpCd_P2" ezfName="csrRgTpCd_P2" ezfBlank="1" ezfCodeName="csrRgTpCd_H2" ezfDispName="csrRgTpDescTxt_H2" otherAttr=" tabindex=\"1\""/>
													</td>
													<td class="stab">Include inactive</td>
													<td>
														<ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" otherAttr=" id=\"xxChkBox_H2#{EZF_ROW_NUMBER}\" tabindex=\"1\""/>
													</td>
												</tr>
												</table>
											</fieldset>
										</td>
									</tr>
									<tr>
										<td>
											<fieldset>
											<legend style="font-size:12px;">Resource Search Filters</legend>
												<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
													<col width="100">
													<col width="400">
													<tr>
														<td class="stab">Resource Name (*)</td>
														<td><ezf:inputText name="xxPsnNm_H3" ezfName="xxPsnNm_H3" otherAttr=" size=\"50\" maxlength=\"30\" tabindex=\"1\""/></td>
													</tr>
													<tr>
														<td class="stab">Employee ID (*)</td>
														<td><ezf:inputText name="psnCd_H3" ezfName="psnCd_H3" otherAttr=" size=\"50\" maxlength=\"30\" tabindex=\"1\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<td class="stab">Job Code (*)</td>
														<td><ezf:inputText name="jobTtlCd_H3" ezfName="jobTtlCd_H3" otherAttr=" size=\"50\" maxlength=\"30\" tabindex=\"1\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<td class="stab">Job Name (*)</td>
														<td><ezf:inputText name="hrTtlNm_H3" ezfName="hrTtlNm_H3" otherAttr=" size=\"50\" maxlength=\"30\" tabindex=\"1\""/></td>
													</tr>
													<tr>
														<td class="stab">Resource# (*)</td>
														<td><ezf:inputText name="psnNum_H3" ezfName="psnNum_H3" otherAttr=" size=\"50\" maxlength=\"50\" tabindex=\"1\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<td class="stab">Role Name</td>
														<td>
															<ezf:select name="orgFuncRoleTpCd_P3" ezfName="orgFuncRoleTpCd_P3" ezfBlank="1" ezfCodeName="orgFuncRoleTpCd_H3" ezfDispName="orgFuncRoleTpNm_H3" otherAttr=" tabindex=\"1\""/>
														</td>
													</tr>
													<tr>
														<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_TerritoryPopup" otherAttr=" tabindex=\"1\"">Territory Name (*)</ezf:anchor></td>
														<td>
															<ezf:inputText name="orgNm_H3" ezfName="orgNm_H3" otherAttr=" size=\"50\" maxlength=\"50\" tabindex=\"1\" ezftoupper=\"\""/>
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<table style="table-layout:fixed;">
																<col width="95"  valign="top">
																<col width="100" valign="top">
																<col width="35" align="center">
																<col width="100" valign="top">
																<tr>
																	<td class="stab">Start Date</td>
																	<td><ezf:inputText name="effFromDt_FR" ezfName="effFromDt_FR" value="02/26/2015" otherAttr=" size=\"10\" tabindex=\"1\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_FR', 4);" ></td>
																	<td>&ndash;</td>
																	<td><ezf:inputText name="effFromDt_TO" ezfName="effFromDt_TO" value="02/26/2015" otherAttr=" size=\"10\" tabindex=\"1\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_TO', 4);" ></td>
																</tr>
																<tr>
																	<!--<td>&nbsp;</td>-->
																	<td class="stab">End Date</td>
																	<td><ezf:inputText name="effThruDt_FR" ezfName="effThruDt_FR" value="02/26/2015" otherAttr=" size=\"10\" tabindex=\"1\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_FR', 4);" ></td>
																	<td>&ndash;</td>
																	<td><ezf:inputText name="effThruDt_TO" ezfName="effThruDt_TO" value="02/26/2015" otherAttr=" size=\"10\" tabindex=\"1\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_TO', 4);" ></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</fieldset>
										</td>
									</tr>
								</table>
							</td>
							<td class="stab">
								<table style="table-layout:fixed;">
									<col valign="top" width="430">
									<tr>
										<td class="stab">
											Search Mode &nbsp; 
											<ezf:select name="xxModeCd_P1" ezfName="xxModeCd_P1" ezfCodeName="xxModeCd_H1" ezfDispName="xxModeNm23Txt_H1" otherEvent1=" onchange=\"sendServer('OnChange_ModeCode')\"" otherAttr=" tabindex=\"1\""/>
										</td>
									</tr>
									<tr>
										<td>
											<fieldset>
											<legend style="font-size:12px;">Advanced Search</legend>
												<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
												<col width="400">
													<tr>
														<td class="stab"><label><ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" value="1" otherAttr=" tabindex=\"1\""/>Display Top Tiers Only</label></td>
													</tr>
													<tr>
														<td class="stab"><label><ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" value="2" otherAttr=" tabindex=\"1\""/>Display Org Level & Children Only (no resources)</label></td>
													</tr>
													<tr>
														<td class="stab"><label><ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" value="3" otherAttr=" tabindex=\"1\""/>Display Org Level, Children & Resource Assignment(s)</label></td>
													</tr>
													<tr>
														<td class="stab"><label><ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" value="4" otherAttr=" tabindex=\"1\""/>Display All Levels Expanded w/Resource Assignment(s)</label></td>
													</tr>
												</table>
											</fieldset>
										</td>
									</tr>
									<tr height="158">
										<td valign="bottom" align="right">
											<ezf:inputButton name="Create_Organization" value="Create Organization" htmlClass="pBtn12" otherAttr=" tabindex=\"1\""/>
											<ezf:inputButton name="Create_Resource" value="Create Resource" htmlClass="pBtn10" otherAttr=" tabindex=\"1\""/>
											<ezf:inputButton name="Search" value="Search" htmlClass="pBtn9" otherAttr=" tabindex=\"1\""/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr height="192">
				<td>
<c:choose>
	<c:when test="${pageScope._ezddatabean.xxDplyTab=='Hierarchy'}">
					<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
						<col width="438">
						<col width="288">
						<col width="288">
						<col width="30">
						<col width="30">
						<tr height="24">
							<td align="center" class="pClothBs">Organization Name</td>
							<td align="center" class="pClothBs">Manager Name</td>
							<td align="center" class="pClothBs">Role</td>
							<td align="center" class="pClothBs">&nbsp</td>
							<td align="center" class="pClothBs">&nbsp</td>
						</tr>
					</table>
<%-- ########## Hierarchy ########## --%>
					<div style="overflow-x:hidden; overflow-y:scroll; width:1093; height:172;" >
						<%@ page import="business.servlet.NMAL2500.NMAL2500Bean" %>
						<% if( ((NMAL2500Bean)databean).getTreeView() != null ) { %>
						<uji:treeView
							bean		= "${__screenName__}"
							indentIcon	= "./img/treeView/child.gif;./img/treeView/lastchild.gif;./img/treeView/hasmorechild.gif;./img/treeView/nomorechild.gif;"
							insets		= "0"
							cellSpacing	= "0"
							background	= "#FFFFFF"
							stripeBackground	= "#D7E2E2"
							css		= "pTreeView"
							noWrap		= "true"
							borderWidth	= "1"
							ruleWidth	= "1"
							rules		= "all"
							dataEscape	= "false;"
							property			= "treeView"
							dataCellType		= "data;data;data;data;radio;data;"
							columnWidth		= "438;288;288;30;30;0;"
							dataAlignmentHorizontal	= ";left;left;center;center;center;"
						/>
						<% } %>
					</div>
					<table style="table-layout:fixed;">
						<col width="720" valign="top">
						<col width="500" valign="top">
						<tr>
							<td>&nbsp</td>
							<td colspan="3">
								<ezf:inputButton name="Link_Organization" value="Edit Organization" htmlClass="pBtn8" otherAttr=" tabindex=\"1\""/>
								<ezf:inputButton name="Add_Parent" value="Add Child Organization" htmlClass="pBtn12" otherAttr=" tabindex=\"1\""/>
								<ezf:inputButton name="Link_Resource" value="Edit Resource" htmlClass="pBtn8" otherAttr=" tabindex=\"1\""/>
							</td>
						</tr>
					</table>
	</c:when>
	<c:when test="${pageScope._ezddatabean.xxDplyTab=='QuickLookup'}">
<%-- ########## QuickLookup ########## --%>
					<table border="0" cellpadding="0" cellspacing="0" style="margin-left:1px">
						<col align="left" valign="top">
						<tr>
							<td>
								<div id="RightTop" style="overflow-x:hidden; width:1100;">
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
										<col align="center" width="70">
										<col align="center" width="90">
										<col align="center" width="110">
										<col align="center" width="110">
										<col align="center" width="330">
										<col align="center" width="80">
										<col align="center" width="140">
										<col align="center" width="80">
										<col align="center" width="80">
										<tr height="36">
											<td class="pClothBs">Resource#</td>
											<td class="pClothBs">Employee</td>
											<td class="pClothBs">Last Name</td>
											<td class="pClothBs">First Name</td>
											<td class="pClothBs">Job Code</td>
											<td class="pClothBs">Employee ID</td>
											<td class="pClothBs">Supervisor</td>
											<td class="pClothBs">Employment<br/>Start Date</td>
											<td class="pClothBs">Employment<br/>End Date</td>
										</tr>
									</table>
								</div>
								<div style="overflow-x:hidden; width:1110; height:185px; overflow-y:scroll;">
									<table id="A_Right" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
										<col width="70">
										<col width="90">
										<col width="110">
										<col width="110">
										<col width="330">
										<col width="80">
										<col width="140">
										<col width="80">
										<col width="80">
										<ezf:row ezfHyo="A">
										<tr>
											<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Link_Resource" otherAttr=" tabindex=\"1\""><ezf:label name="psnNum_A1" ezfName="psnNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
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
</c:when>
</c:choose>
				</td>
			</tr>
		</table>
	</center>
</div>
<% if( ((NMAL2500Bean)databean).getTreeView() != null ) { %>
<%-- include S21TreeView.jsp --%>
<%@ page import="business.servlet.NMAL2500.NMAL2500Bean" %>
<jsp:include page="../treeView/S21TreeView.jsp">
<jsp:param name="treeView" value="<%= ((NMAL2500Bean)databean).getTreeView().isDisableAllFields() %>" />
<jsp:param name="TreeViewPropertyNameList" value="treeView" />
<jsp:param name="isFocusTreeView" value="<%= ((NMAL2500Bean)databean).getTreeView().isSetFocusTreeView() %>" />
<jsp:param name="setFocusTreeViewName" value="treeView" />
</jsp:include>
<% } %>


<%-- **** Task specific area ends here **** --%>
