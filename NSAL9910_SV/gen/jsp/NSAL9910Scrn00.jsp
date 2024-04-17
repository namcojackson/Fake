<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160301095108 --%>
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
			<input type="hidden" name="pageID" value="NSAL9910Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="General Business Master Maintenance List">

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
										<li title="General Business Master Maintenance List" class="pTab_ON"><a href="#">Mst Maint</a></li>
									</div>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

				<div class="pTab_BODY">
				<table style="margin-top: 0px; margin-left: 100px" border="0">
					<tr>&nbsp;</tr>
					<tr>
						<td>
							<div id="Top" style="width:580px; height:25px; overflow-x:hidden;">
								<table border="1" cellpadding="1" cellspacing="0">
									<col width="580"  align="center">
									<tr height="25px">
										<td class="pClothBs">Table Name</td>
									</tr>
								</table>
							</div>
							<div id="Detail" style="width:597px; height:502px; overflow-y:scroll;" >
								<table id="A" border="1" cellpadding="1" cellspacing="0">
									<col width="580" align="left">
									<ezf:row ezfHyo="A">
										<tr height="25px">
											<td><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="SelectTblNm','{EZF_ROW_NUMBER}" >
													<ezf:label name="logicMaintTrgtTblNm_A" ezfName="logicMaintTrgtTblNm_A" ezfHyo="A" ezfArrayNo="0" />
												</ezf:anchor>
											</td>
										</tr>
										<ezf:skip>
										<tr height="25px">
											<td><a href="#" onclick="sendServer('SelectTblNm','{EZF_ROW_NUMBER}')">
													<label name="logicMaintTrgtTblNm_A" ezfname="logicMaintTrgtTblNm_A" ezfhyo="A" ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8</label>
												</a>
											</td>
										</tr>
										<tr height="25px">
											<td><a href="#" onclick="sendServer('SelectTblNm','{EZF_ROW_NUMBER}')">
													<label name="logicMaintTrgtTblNm_A" ezfname="logicMaintTrgtTblNm_A" ezfhyo="A" ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8</label>
												</a>
											</td>
										</tr>
										<tr height="25px">
											<td><a href="#" onclick="sendServer('SelectTblNm','{EZF_ROW_NUMBER}')">
													<label name="logicMaintTrgtTblNm_A" ezfname="logicMaintTrgtTblNm_A" ezfhyo="A" ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8</label>
												</a>
											</td>
										</tr>
										<tr height="25px">
											<td><a href="#" onclick="sendServer('SelectTblNm','{EZF_ROW_NUMBER}')">
													<label name="logicMaintTrgtTblNm_A" ezfname="logicMaintTrgtTblNm_A" ezfhyo="A" ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8</label>
												</a>
											</td>
										</tr>
										<tr height="25px">
											<td><a href="#" onclick="sendServer('SelectTblNm','{EZF_ROW_NUMBER}')">
													<label name="logicMaintTrgtTblNm_A" ezfname="logicMaintTrgtTblNm_A" ezfhyo="A" ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8</label>
												</a>
											</td>
										</tr>
										</ezf:skip>
									</ezf:row>
								</table>
							</div>
						</td>
					</tr>

				</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<style TYPE="text/css">
<!--
	tr.checkLineBGcolor{background-color:yellow;}
	a img{border-style:none;}
-->
</style>

<%-- **** Task specific area ends here **** --%>
