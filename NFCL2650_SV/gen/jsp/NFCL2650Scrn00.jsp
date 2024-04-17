<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161125110926 --%>
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
			<input type="hidden" name="pageID" value="NFCL2650Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Statement Draft Creation">

<center>
	<table border="0" cellpadding="1" cellspacing="0" width="100%">
		<tr>
			<td>
			<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Location Info" class="pTab_ON"><a href="#">Stmt Draft</a></li>
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
					<table border="0" cellpadding="10" cellspacing="0">
						<col width="150" align="left">
						<col width="30" align="left">
						<col width="50" align="left">
						<col width="20" align="center">
						<col width="80" align="left">
						<tr>
							<td class="stab">As of Date</td>
							<td colspan="2"><ezf:inputText name="acctDt" ezfName="acctDt" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td class="stab">Customer Number</td>
						<td class="stab"><a href="#" onclick="submitFromPluralFields('OpenWin_Customer', 'FromCd')" ezfGuard="OpenWin_Customer">From</a></td>
							<td><ezf:inputText name="dsAcctNum_FR" ezfName="dsAcctNum_FR" value="1234567" otherAttr=" size=\"7\" maxlength=\"20\" ezftoupper=\"\""/></td>
							<td><ezf:inputButton name="GetCustNmFrom" value=">>" htmlClass="pBtn0" otherAttr=" id=\"GetCustNmFrom\""/></td>
							<td colspan="2"><ezf:inputText name="dsAcctNm_F1" ezfName="dsAcctNm_F1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWW" otherAttr=" size=\"35\" maxlength=\"360\" ezftoupper=\"\""/></td>
						</tr>
						<tr>
							<td></td>
							<td class="stab"><a href="#" onclick="submitFromPluralFields('OpenWin_Customer', 'ToCd')" ezfGuard="OpenWin_Customer">To</a></td>
							<td ><ezf:inputText name="dsAcctNum_TO" ezfName="dsAcctNum_TO" value="1234567" otherAttr=" size=\"7\" maxlength=\"20\" ezftoupper=\"\""/></td>
							<td><ezf:inputButton name="GetCustNmTo" value=">>" htmlClass="pBtn0" otherAttr=" id=\"GetCustNmTo\""/></td>
							<td colspan="2"><ezf:inputText name="dsAcctNm_T1" ezfName="dsAcctNm_T1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWW" otherAttr=" size=\"35\" maxlength=\"360\" ezftoupper=\"\""/></td>
						</tr>
						
						<tr>
							<td class="stab">Customer Name</td>
							<td class="stab"><a href="#" onclick="submitFromPluralFields('OpenWin_Customer', 'FromNm')" ezfGuard="OpenWin_Customer">From</a></td>
							<td colspan="3"><ezf:inputText name="dsAcctNm_F2" ezfName="dsAcctNm_F2" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWW" otherAttr=" size=\"53\" maxlength=\"360\" ezftoupper=\"\""/></td>
						</tr>
						<tr>
							<td></td>
							<td class="stab"><a href="#" onclick="submitFromPluralFields('OpenWin_Customer', 'ToNm')" ezfGuard="OpenWin_Customer">To</td>
							<td colspan="3"><ezf:inputText name="dsAcctNm_T2" ezfName="dsAcctNm_T2" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWW" otherAttr=" size=\"53\" maxlength=\"360\" ezftoupper=\"\""/></td>
						</tr>
						
						<tr>
							<td class="stab"><a href="#" onclick="submitFromPluralFields('OpenWin_Customer', 'Location')" ezfGuard="OpenWin_Customer">Location</a></td>
							<td></td>
							<td><ezf:inputText name="locNum" ezfName="locNum" value="WWWWWWW" otherAttr=" size=\"7\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td><ezf:inputButton name="GetBillToCust" value=">>" htmlClass="pBtn0" otherAttr=" id=\"GetBillToCust\""/></td>
							<td><ezf:inputText name="locNm" ezfName="locNm" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWW" otherAttr=" size=\"35\" maxlength=\"60\" ezftoupper=\"\""/></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>



<%-- **** Task specific area ends here **** --%>
