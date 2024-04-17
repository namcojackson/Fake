<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160301024452 --%>
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
			<input type="hidden" name="pageID" value="NSAL0580Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Status Change">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Status Change" class="pTab_ON"><a href="#">Sts Chng</a></li>
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
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
					<table border="0" width="100%">
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
									<col width=" 75">
									<col width="200">
									<col width=" 40">
									<col width="400">
									<tr height="23">
										<td class="stab">Contract#</td>
										<td><ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="12038992" otherAttr=" size=\"20\""/></td>
										<td class="stab">Status</td>
										<td><ezf:inputText name="dsContrCtrlStsNm" ezfName="dsContrCtrlStsNm" value="ACTIVE" otherAttr=" size=\"10\""/></td>
									</tr>
									<tr height="23">
										<td class="stab">New Status</td>
										<td><ezf:select name="dsContrCtrlStsCd_H" ezfName="dsContrCtrlStsCd_H" ezfBlank="1" ezfCodeName="dsContrCtrlStsCd_L" ezfDispName="dsContrCtrlStsNm_L" /></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr height="23">
										<td class="stab">Reason Code</td>
										<td><ezf:select name="svcMemoRsnCd_H" ezfName="svcMemoRsnCd_H" ezfBlank="1" ezfCodeName="svcMemoRsnCd_L" ezfDispName="svcMemoRsnDescTxt_L" /></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td class="stab">Notes</td>
										<td colspan="3"><ezf:textArea name="svcCmntTxt" ezfName="svcCmntTxt" otherAttr=" cols=\"80\" rows=\"4\""/></td>
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
