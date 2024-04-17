<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20110114035902 --%>
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
			<input type="hidden" name="pageID" value="ZZZL0070Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Batch Process Monitor">
			
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
						<td valign="top" align="left">
							<table width="300" align="left" border="0" cellspacing="0" cellpadding="0">
								<tbody>
									<tr>
										<col width="140">
										<col width="140">
										<col width="85">
										<td class="stab" align="left">
											<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company CD</ezf:anchor>
										</td>
										<td colspan="2" align="left">
											<ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXX" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab" align="left">Operation Date(From)
										</td>
										<td colspan="2" >
											<ezf:inputText name="xxFromDt" ezfName="xxFromDt" otherAttr=" size=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt', 4);" >
										</td>
									</tr>
									<tr>
										<td class="stab" align="left">Operation Date(To)
										</td>
										<td colspan="2" >
											<ezf:inputText name="xxToDt" ezfName="xxToDt" otherAttr=" size=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxToDt', 4);" >
										</td>
									</tr>
									<tr>
										<td class="stab" align="left">
											Job ID
										</td>
										<td align="left">
											<ezf:inputText name="batProcJobId" ezfName="batProcJobId" value="XXXXXXXXXXX" otherAttr=" size=\"11\" ezftoupper=\"\""/>
										</td>
										<td align="left">
											<ezf:inputButton name="AddJobId" value=">>" htmlClass="pBtn4" />
										</td>
									</tr>
								</tbody>
							</table>
						</td>
						<td align="left">
							<table width="120" align="left" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
										<table align="left" border="1" cellspacing="0" cellpadding="1" width="280" height="25">
											<tr align="center">
											<col width="22">
											<col width="130">
											<col width="130">
												<td align="center" class="pClothBs"></td>
												<td align="center" class="pClothBs">JOB ID</td>
												<td align="center" class="pClothBs">Program ID</td>
											</tr>	
										</table>
										</td>
									</tr>
									<tr>
										<td>
										<div style="OVERFLOW-Y:scroll; OVERFLOW-X:none; WIDTH:297; HEIGHT:80; ">
										<table align="left" width="280" border="1" cellpadding="1" cellspacing="0" id="A">
											<ezf:row ezfHyo="A">
											<tr id="id_row_{EZF_ROW_NUMBER}">
											<col width="20">
											<col width="129">
											<col width="132">
												<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
												<td align="center"><ezf:label name="batProcJobId_A" ezfName="batProcJobId_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td align="center"><ezf:label name="batProcPgmId_A" ezfName="batProcPgmId_A" ezfHyo="A" ezfArrayNo="0" /></td>
											</tr>
											</ezf:row>
										</table>
										</div>
										</td>
									</tr>
							</table>
						</td>
						<td align="left">
							<table width="500" align="left" border="0" cellspacing="0" cellpadding="0">
								<tr align="left">
									<td class="stab" align="left">
										<ezf:inputButton name="JobIdLookup" value="Add Job" htmlClass="pBtn4" />
									</td>
								</tr>
								<tr align="left">
									<td class="stab" align="left">
										<ezf:inputButton name="DelJob" value="Del Job" htmlClass="pBtn4" />
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
  				<table align="center" border="1" cellspacing="0" cellpadding="0" width="1100" height="435">
  							<%@ page import="business.servlet.ZZZL0070.*"%>
							<script type="text/javascript" src="./chart/js/swfobject.js" charset="UTF-8"></script>
							<% ZZZL0070BMsg scrnMsg = (ZZZL0070BMsg)databean.getEZDBMsg();%>
							<% String deleteFlag = scrnMsg.delFlg.getValue();%>
  					<col width="250">
  					<col width="250">
  					<tr>
  						<td>
  							<div id="job_tm_chart" style="WIDTH:240; HEIGHT:110; ">
  							</div>
							<% if(deleteFlag.equals("Y")) { %>
								<script type="text/javascript">
									swfobject.removeSWF("job_tm_chart");
								</script>
							<% } else if (deleteFlag.equals("N")) { %>
								<script type="text/javascript">
									swfobject.embedSWF(
										"./chart/swf/open-flash-chart-full-embedded-font.swf", "job_tm_chart", "100%", "100%",
										"10.0.0", "./chart/swf/expressInstall.swf",
										{"data-file":"./jsp/business/JobTmData.jsp"},
										{"wmode":"transparent"});
								</script>
							<% } %>
  						</td>
  						<td>
  							<div id="table_size_chart" style="WIDTH:240; HEIGHT:110; ">
  							</div>
							<% if(deleteFlag.equals("Y")) { %>
								<script type="text/javascript">
									swfobject.removeSWF("table_size_chart");
								</script>
							<% } else if (deleteFlag.equals("N")) { %>
								<script type="text/javascript">
									swfobject.embedSWF(
										"./chart/swf/open-flash-chart-full-embedded-font.swf", "table_size_chart", "100%", "100%",
										"10.0.0", "./chart/swf/expressInstall.swf",
										{"data-file":"./jsp/business/TableSizeData.jsp"},
										{"wmode":"transparent"});
								</script>
							<% } %>
  						</td>
  					</tr>
  					<tr>
  						<td>
  							<div id="job_cnt_chart" style="WIDTH:240; HEIGHT:110; ">
  							</div>
							<% if(deleteFlag.equals("Y")) { %>
								<script type="text/javascript">
									swfobject.removeSWF("job_cnt_chart");
								</script>
							<% } else if (deleteFlag.equals("N")) { %>
								<script type="text/javascript">
									swfobject.embedSWF(
										"./chart/swf/open-flash-chart-full-embedded-font.swf", "job_cnt_chart", "100%", "100%",
										"10.0.0", "./chart/swf/expressInstall.swf",
										{"data-file":"./jsp/business/JobCntData.jsp"},
										{"wmode":"transparent"});
								</script>
							<% } %>
  						</td>
  						<td>
  							<div id="table_cnt_chart" style="WIDTH:240; HEIGHT:110; ">
  							</div>
							<% if(deleteFlag.equals("Y")) { %>
								<script type="text/javascript">
									swfobject.removeSWF("table_cnt_chart");
								</script>
							<% } else if (deleteFlag.equals("N")) { %>
								<script type="text/javascript">
									swfobject.embedSWF(
										"./chart/swf/open-flash-chart-full-embedded-font.swf", "table_cnt_chart", "100%", "100%",
										"10.0.0", "./chart/swf/expressInstall.swf",
										{"data-file":"./jsp/business/TableCntData.jsp"},
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
