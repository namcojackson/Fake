<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20140624000324 --%>
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
			<input type="hidden" name="pageID" value="ZZZL0060Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Batch Process Registration">
<center>
	<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER START ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<!-- include S21BusinessProcessTAB -->
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
<!--
				 <table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
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
				</table>
-->
				<div class="pTab_BODY">
				<table width="1100px" align="center" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<table width="423px" align="center" border="0" cellspacing="0" cellpadding="0">
								<tbody>
									<tr>
										<col width="115">
										<col width="200">
										<col width="100">
									<tr><td>&nbsp;</td>
									</tr>
										<td class="stab" align="left">
											<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company CD</ezf:anchor>
										</td>
										<td align="left" colspan="2">
											<ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXX" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab" align="left">
											<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="JobIdLookup" ><label>Job ID</label></ezf:anchor>
										</td>
										<td>
											<ezf:inputText name="batProcJobId" ezfName="batProcJobId" value="XXXXXXXXXXX" otherAttr=" size=\"11\""/>
										</td>
										<td align="right">
											<ezf:inputButton name="Search" value="Search" htmlClass="cBtn" />
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<hr size="1" width="39%">
						</td>
					</tr>
					<tr>
						<td>
							<table width="426px" align="center" border="0" cellspacing="0" cellpadding="0">
								<tbody>
									<tr>
										<td class="stab" align="left">
											<ezf:inputButton name="Add" value="Add" htmlClass="pBtn4" />
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table width="426px" align="center" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
										<table align="left" border="1" cellspacing="0" cellpadding="1" width="460" height="25">
											<tr align="center">
											<col width="20">
											<col width="140">
											<col width="290">
												<td align="center" class="pClothBs">&nbsp;</td>
												<td align="center" class="pClothBs">Job ID</td>
												<td align="center" class="pClothBs">Table Name</td>
											</tr>	
										</table>
										</td>
									</tr>
									
									<tr>
										<td>
										<div style="OVERFLOW-Y:scroll; OVERFLOW-X:none; WIDTH:475; HEIGHT:425; ">
										<table align="left" width="460" border="1" cellpadding="1" cellspacing="0" id="A">
											<ezf:row ezfHyo="A">
											<tr id="id_row_{EZF_ROW_NUMBER}">
											<col width="20">
											<col width="140">
											<col width="290">
												<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
												<td align="center"><ezf:inputText name="batProcJobId_A" ezfName="batProcJobId_A" value="XXXXXX_XXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\""/></td>
												<td align="left"><ezf:inputText name="batTblNm_A" ezfName="batTblNm_A" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"39\""/></td>
											</tr>
											</ezf:row>
										</table>
										</div>
										</td>
									</tr>
							</table>
						</td>
					</tr>
				</table>
				</div>
			</td>
		</tr>
	</table>
</center>		

<%-- **** Task specific area ends here **** --%>
