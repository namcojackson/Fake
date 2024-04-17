<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160309094108 --%>
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
			<input type="hidden" name="pageID" value="NSBL0320Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Select SR Summary Criteria">
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
										<li title="Select SR Summary Criteria" class="pTab_ON"><a href="#">Slct Smry</a></li>
									</div>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>
<%-- ######################################## HEADER ######################################## --%>
				<div class="pTab_BODY">
				<table border="0" width="93%" align="center">
					<col width="" align="" valign="top">
					<col width="" align="" valign="top">
					<col width="" align="" valign="top">
					<col width="" align="" valign="bottom">
					<tr>
						<td>
							<table align ="center" border="0" cellpadding="5" cellspacing="5">
								<tr>
									<td>&nbsp;</td>
									<td>
										<ezf:select name="svcRqstCritTpCd" ezfName="svcRqstCritTpCd" ezfCodeName="svcRqstCritTpCd_01" ezfDispName="svcRqstCritTpDescTxt_01" otherEvent1=" onchange=\"sendServer('OnChangeCriteria')\"" otherAttr=" style=\"width:150;\""/>
									</td>
									<td>&nbsp;</td>
									<td >
										<ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn7" />
									</td>
									<td>
										<ezf:inputButton name="UnselectAll" value="Unselect All" htmlClass="pBtn7" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
<%-- ######################################## DETAIL ######################################## --%>

				<table border="0" cellpadding="0" cellspacing="0" width="93%"align="center">
					<col align="center" valign="top">
					<tr>
						<td>
							<div align ="center" id="Detail" style="width:321; overflow-y:scroll; height:500;">
								<table align ="center" border="0" width="320" height="24" id="A" border="0" cellpadding="1" cellspacing="0">
									<col width="100"  align="right">		<!-- (Check Box) -->
									<col width="220" align="left">		<!-- Branch -->
									<ezf:row ezfHyo="A">
										<tr>
											<td>
												<ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" ezfHyo="A" ezfArrayNo="0" />
											</td>
											<td>
												<ezf:inputText name="xxGenlFldAreaTxt" ezfName="xxGenlFldAreaTxt" value="100" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"45\""/>
											</td>
										</tr>
									</ezf:row>
								</table>
							</div>
						</td>
					</tr>
					<td align="left">
						<table align ="center" border="0" width="320" cellpadding="1" cellspacing="1">
							<tr>
								<td>&nbsp;</td>
								<td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" /></td>
							</tr>
						</table>
					</td>
				</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
