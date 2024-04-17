<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20101215074152 --%>
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
			<input type="hidden" name="pageID" value="ZZZL0030Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Online Process Monitor">
			
<center>
	<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER START ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<!-- include S21BusinessProcessTAB -->
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				 <!--<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
					<tr>
						<td width="1100px" height="28px" valign="bottom">
								<div>
									<table border="0" cellpadding="0" cellspacing="0">
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
												<tr title='Order Entry'>
													<td width="107px" align="center" class="same">
														Onl Proc View
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='Hold Release'>
													<td width="90px" align="center" class="disabled">
														Onl Proc Rank
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='Soft Allocation'>
													<td width="90px" align="center" class="disabled">
														Onl Proc Config
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
									</table>
								</div>
						</td>
						<td valign="bottom" align="center">
								<a href="#" id="PrevPageTabIndex"><img id="PrevPageBtn" src="./img/tab/tabbackbutton.png" alt="Prev" border="0"></a>
						</td>
						<td valign="bottom" align="center">
							<a href="#" id="NextPageTabIndex"><img id="NextPageBtn" src="./img/tab/tabnextbutton.png" alt="Next" border="0"></a>
						</td>
					</tr>
				</table>-->
				
				<div class="pTab_BODY">
				<table width="1100px" align="center" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<table width="400px" align="left" border="0" cellspacing="0" cellpadding="0">
								<tbody>
									<tr>
										<col width="120">
										<col width="280">
										<td class="stab" align="left">
											<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company CD</ezf:anchor>
										</td>
										<td align="left">
											<ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXX" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab" align="left">User ID
										</td>
										<td>
											<ezf:inputText name="opsUsrId" ezfName="opsUsrId" otherAttr=" size=\"7\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab" align="left">JVM
										</td>
										<td align="left">
											<ezf:select name="jvmNm_S" ezfName="jvmNm_S" ezfCodeName="jvmNm_C" ezfDispName="jvmNm_D" />
										</td>
									</tr>
									<tr>
										<td class="stab" align="left">Operation Date(From)
										</td>
										<td>
											<ezf:inputText name="xxFromDt" ezfName="xxFromDt" otherAttr=" size=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt', 4);" >
											<ezf:select name="xxHrs_FS" ezfName="xxHrs_FS" ezfCodeName="xxHrs_FC" ezfDispName="xxHrs_FD" />
											:
											<ezf:select name="xxMn_FS" ezfName="xxMn_FS" ezfCodeName="xxMn_FC" ezfDispName="xxMn_FD" />
										</td>
									</tr>
									<tr>
										<td class="stab" align="left">Operation Date(To)
										</td>
										<td>
											<ezf:inputText name="xxToDt" ezfName="xxToDt" otherAttr=" size=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxToDt', 4);" >
											<ezf:select name="xxHrs_TS" ezfName="xxHrs_TS" ezfCodeName="xxHrs_TC" ezfDispName="xxHrs_TD" />
											:
											<ezf:select name="xxMn_TS" ezfName="xxMn_TS" ezfCodeName="xxMn_TC" ezfDispName="xxMn_TD" />
										</td>
										<td>
									</tr>
									<tr>
										<td class="stab" align="left">Y-axle
										</td>
										<td align="left">
											<ezf:select name="xxYAxle" ezfName="xxYAxle" >
												<ezf:option value="ProcessingTime" >ProcessingTime</ezf:option>
												<ezf:option value="GlobalAreaSize" >GlobalAreaSize</ezf:option>
												<ezf:option value="Throughput" >Throughput</ezf:option>
											</ezf:select>
											<ezf:inputButton name="Refresh" value="Refresh" htmlClass="cBtn" />
										</td>
									</tr>
								</tbody>
							</table>
						</td>
						<td>
							<table width="495px" align="left" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
										<table align="left" border="1" cellspacing="0" cellpadding="1" width="470" height="25">
											<tr align="center">
											<col width="20">
											<col width="80">
											<col width="300">
												<td align="center" class="pClothBs"></td>
												<td align="center" class="pClothBs">BusinessID</td>
												<td align="center" class="pClothBs">Screen EventID</td>
											</tr>	
										</table>
										</td>
									</tr>
									<tr>
										<td>
										<div style="OVERFLOW-Y:scroll; OVERFLOW-X:none; WIDTH:482; HEIGHT:122; ">
										<table align="left" width="470" border="1" cellpadding="1" cellspacing="0" id="A">
											<ezf:row ezfHyo="A">
											<tr id="id_row_{EZF_ROW_NUMBER}">
											<col width="20">
											<col width="80">
											<col width="300">
												<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
												<td align="center"><ezf:label name="bizId_A" ezfName="bizId_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td align="left"><ezf:label name="scrAppId_A" ezfName="scrAppId_A" ezfHyo="A" ezfArrayNo="0" /></td>
											</tr>
											</ezf:row>
										</table>
										</div>
										</td>
									</tr>
							</table>
						</td>
						<td>
							<table width="180px" align="left" border="0" cellspacing="0" cellpadding="0">
								<tr align="left">
									<td class="stab" align="left">
										<ezf:inputButton name="EventLookup" value="Add Biz" htmlClass="pBtn4" />
									</td>
								</tr>
								<tr align="left">
									<td class="stab" align="left">
										<ezf:inputButton name="DelBiz" value="Del Biz" htmlClass="pBtn4" />
									</td>
								</tr>
								<tr>
									<td class="stab" align="left">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td class="stab" align="left">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td class="stab" align="left">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td class="stab" align="left">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td class="stab" align="left">
										&nbsp;
									</td>
								</tr>	
								<tr>
									<td class="stab" align="left">
										&nbsp;
									</td>
								</tr>																																						
								<tr align="left">
									<td align="left">
										<ezf:inputButton name="View" value="View" htmlClass="cBtn" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

				<hr size="1" width="97%" noshade>
				
				<%-- ######################################## HEADER END ######################################## --%>
  				<table align="center" border="1" cellspacing="1" cellpadding="1" width="1100" height="400">
  					<tr>
  						<td>
  							<div id="my_chart" align="center">
  							</div>
							<%@ page import="business.servlet.ZZZL0030.*"%>
							<script type="text/javascript" src="./chart/js/swfobject.js" charset="UTF-8"></script>
							<% ZZZL0030BMsg scrnMsg = (ZZZL0030BMsg)databean.getEZDBMsg();%>
							<% String deleteFlag = scrnMsg.delFlg.getValue();%>
							<% if(deleteFlag.equals("Y")) { %>
								<script type="text/javascript">
									swfobject.removeSWF("my_chart");
								</script>
							<% } else if (deleteFlag.equals("N")) { %>
								<script type="text/javascript">
									swfobject.embedSWF(
										"./chart/swf/open-flash-chart-full-embedded-font.swf", "my_chart", "100%", "100%",
										"10.0.0", "./chart/swf/expressInstall.swf",
										{"data-file":"./jsp/business/ChartData.jsp"},
										{"wmode":"transparent"});
								</script>
							<% } %>
  						</td>
  					</tr>
  				</table>
				</div>
			</td>
		</tr>
	</table>
</center>		

<%-- **** Task specific area ends here **** --%>
