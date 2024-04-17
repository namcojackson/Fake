<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180704132028 --%>
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
			<input type="hidden" name="pageID" value="NSBL0390Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Mods Entry">
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Mods Entry" class="pTab_ON"><a href="#">Mods Entry</a></li>
									</div>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

				<div class="pTab_BODY">
				<table border="0" width="100%" cellpadding="1" cellspacing="5">
					<tr>
						<td class="stab">Steps to create new Mod Plan ID:</td>
					</tr>
					<tr>
						<td class="stab">Step1:Enter Year "YYYY" -(example 2016).</td>
					</tr>
					<tr>
						<td class="stab">Step2:Enter Month "MM" -(example 06).</td>
					</tr>
					<tr>
						<td class="stab">Step3:Enter Day "DD" -(example 09.)</td>
					</tr>
					<tr>
						<td class="stab">Step4:Enter Manufacture Unit "MU" -(example Canon or Poing or Venlo).</td>
					</tr>
					<tr>
						<td class="stab">Step5:Push "Create" button in the Seq# field to auto-fill the next available sequence number.</td>
					</tr>
					<tr>
						<td>
							<table border="0" width="100%" cellpadding="1" cellspacing="1">
								<col width="16%" align="left">
								<col width="2%" align="center">
								<col width="10%" align="left">
								<col width="2%" align="center">
								<col width="5%" align="center">
								<col width="8%" align="right">
								<col width="10%" align="right">
								<col width="47%" align="left">
								<tr>
									<td>
										<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" width="100%">
											<col width="40%" align="center">
											<col width="30%" align="center">
											<col width="30%" align="center">
											<tr>
												<td class="stab pPro">YYYY</td>
												<td class="stab pPro">MM</td>
												<td class="stab pPro">DD</td>
											</tr>
											<tr>
<!-- START 2018/07/04 T.Ogura [QC#27065,MOD] -->
<!-- 											<td><input type="text" class="pPro" size="5" value="" name="svcModYr" ezfname="svcModYr"></td> -->
												<td>
													<ezf:select name="svcModYr" ezfName="svcModYr" ezfBlank="1" ezfCodeName="xxDplyByCtrlItemCd_YY" ezfDispName="xxDplyByCtrlItemCdNm_YY" otherAttr=" style=\"width:60;\""/>
												</td>
<!-- END   2018/07/04 T.Ogura [QC#27065,MOD] -->
												<td>
													<ezf:select name="svcModMth" ezfName="svcModMth" ezfBlank="1" ezfCodeName="xxDplyByCtrlItemCd_MM" ezfDispName="xxDplyByCtrlItemCdNm_MM" otherAttr=" style=\"width:45;\""/>
												</td>
												<td>
													<ezf:select name="svcModDay" ezfName="svcModDay" ezfBlank="1" ezfCodeName="xxDplyByCtrlItemCd_DD" ezfDispName="xxDplyByCtrlItemCdNm_DD" otherAttr=" style=\"width:45;\""/>
												</td>
											</tr>
										</table>
									</td>
									<td>-</td>
									<td>
										<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="100%">
											<col width="100%" align="center">
											<tr>
												<td class="stab pPro">MU</td>
											</tr>
											<tr>
												<td>
													<ezf:select name="svcMnfCd" ezfName="svcMnfCd" ezfBlank="1" ezfCodeName="svcMnfCd_01" ezfDispName="xxDplyByCdNmCnctTxt_01" otherAttr=" style=\"width:100;\""/>
												</td>
											</tr>
										</table>
									</td>
									<td>-</td>
									<td>
										<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="100%">
											<col width="100%" align="center">
											<tr>
												<td class="stab pPro">seq#</td>
											</tr>
											<tr>
												<td><ezf:inputText name="svcModSqNum" ezfName="svcModSqNum" otherAttr=" size=\"5\""/></td>
											</tr>
										</table>
									</td>
									<td><ezf:inputButton name="Create" value="Create" htmlClass="pBtn6" /></td>
									<td class="stab">Mod Plan ID</td>
									<td><ezf:inputText name="svcModPlnId" ezfName="svcModPlnId" otherAttr=" size=\"20\""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="stab">Step6:Enter Mod Plan Description:<ezf:inputText name="svcModNm" ezfName="svcModNm" otherAttr=" size=\"45\""/></td>
					</tr>
					<tr>
						<td class="stab">Step7:Click on submit button.</td>
					</tr>
					<tr>
						<td><ezf:inputButton name="MoveWin_MdsDtl" value="Modification Detail" htmlClass="pBtn10" /></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
