<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20091030085948 --%>
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

<%@ page import="business.servlet.ZZOL0110.ZZOL0110BMsg" %>
<%@ page import="business.servlet.ZZOL0110.ZZOL0110Bean" %>
<% ZZOL0110BMsg bMsg = (ZZOL0110BMsg)databean.getEZDBMsg(); %>
			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="ZZOL0110Scrn01">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Admin menu">
			<!-- Parts or S21 -->
			<table width="100%" height="2" border="0">
				<tr valign="middle">
					<td>&nbsp;</td>
				</tr>
			</table>
			<table width="100%" height="35" border="0" id="sysTable">
				<col width="50">
				<col width="150">
				<col width="40">
				<col width="100">
				<col width="560">
				<tr valign="middle">
					<td>&nbsp;</td>
					<td>Global Company CD</td>
					<td class="pout" ><ezf:label name="glblCmpyCd_X2" ezfName="glblCmpyCd_BK" /></td>
					<td class="pout" ><ezf:label name="glblCmpyNm_X2" ezfName="glblCmpyNm_X1" /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="5"><hr></td>
				</tr>
			</table>

			<table width="100%" height="30" align="center">
				<col width="100">
				<tr valign="middle">
					<td>&nbsp;</td>
					<td class="stab" align="left"><ezf:label name="sysMenuNm_X2" ezfName="sysMenuNm_X2" /></td>
				</tr>
			</table>
			<table cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td align="center">
					<div id="tableRight" style="OVERFLOW-Y: hidden; HEIGHT: 400px">
					<table cellpadding="0" cellspacing="0" border="0">
					<col width="70">
					<col width="250">
					<col width="50">
					<col width="250">
					<col width="50">
					<col width="250">
					<tbody>
					<%
						String bizAppId    = "";
						String sysMenuNm   = "";
						String wfAppNm     = "";
						String sysUsbleFlg = "";
						String strHtml     = "";
						for (int i = 0; i < 10; i++) {
							bizAppId    = bMsg.D.no(i).bizAppId_D1.getValue();
							sysMenuNm   = bMsg.D.no(i).sysMenuNm_D1.getValue();
							wfAppNm     = bMsg.D.no(i).wfAppNm_D1.getValue();
							sysUsbleFlg = bMsg.D.no(i).sysUsbleFlg_D1.getValue();
							strHtml     = "";
							if (wfAppNm.equals("")) {
								strHtml = "<" + "input type=\"Button\" name=\"SelectAppL\" class=\"pBtn24\" value=\"" + sysMenuNm + "\" onclick=\"sendServer(this, " + i + ")\"";
								if (!sysUsbleFlg.equals("Y") || sysMenuNm.equals("")) {
									strHtml = strHtml + " disabled=\"true\"";
								}
								strHtml = strHtml + ">";
							} else {
								strHtml = "<" + "input type=\"Button\" name=\"SelectAppL\" class=\"pBtn24\" value=\"" + sysMenuNm + "\" onClick=\"disp('" + wfAppNm + "')\"";
								if (!sysUsbleFlg.equals("Y") || sysMenuNm.equals("")) {
									strHtml = strHtml + " disabled=\"true\"";
								}
								strHtml = strHtml + ">";
							}
					%>
							<tr height="40">
								<td>&nbsp;</td>
								<td><%= strHtml %></td>
							</tr>
					<%
						}
					%>
						<ezf:skip>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppL" value="---------1---------2---------3---------4---------5" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppL" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppL" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppL" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppL" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppL" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppL" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppL" value="" class="pBtn24" disabled="true"></td>
						</tr>
						</ezf:skip>
					</tbody>
					</table>
					</div>
				</td>
				<td align="center">
					<div id="tableRight" style="OVERFLOW-Y: hidden; HEIGHT: 400px">
					<table cellpadding="0" cellspacing="0" border="0">
					<col width="70">
					<col width="250">
					<col width="50">
					<col width="250">
					<col width="50">
					<col width="250">
					<tbody>
					<%
						for (int i = 0; i < 10; i++) {
							bizAppId    = bMsg.E.no(i).bizAppId_E1.getValue();
							sysMenuNm   = bMsg.E.no(i).sysMenuNm_E1.getValue();
							wfAppNm     = bMsg.E.no(i).wfAppNm_E1.getValue();
							sysUsbleFlg = bMsg.E.no(i).sysUsbleFlg_E1.getValue();
							strHtml     = "";
							if (wfAppNm.equals("")) {
								strHtml = "<" + "input type=\"Button\" name=\"SelectAppC\" class=\"pBtn24\" value=\"" + sysMenuNm + "\" onclick=\"sendServer(this, " + i + ")\"";
								if (!sysUsbleFlg.equals("Y") || sysMenuNm.equals("")) {
									strHtml = strHtml + " disabled=\"true\"";
								}
								strHtml = strHtml + ">";
							} else {
								strHtml = "<" + "input type=\"Button\" name=\"SelectAppC\" class=\"pBtn24\" value=\"" + sysMenuNm + "\" onClick=\"disp('" + wfAppNm + "')\"";
								if (!sysUsbleFlg.equals("Y") || sysMenuNm.equals("")) {
									strHtml = strHtml + " disabled=\"true\"";
								}
								strHtml = strHtml + ">";
							}
					%>
							<tr height="40">
								<td>&nbsp;</td>
								<td><%= strHtml %></td>
							</tr>
					<%
						}
					%>
						<ezf:skip>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppC" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppC" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppC" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppC" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppC" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppC" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppC" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppC" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppC" value="" class="pBtn24" disabled="true"></td>
						</tr>
						</ezf:skip>
					</tbody>
					</table>
					</div>
				</td>
				<td align="center">
					<div id="tableRight" style="OVERFLOW-Y: hidden; HEIGHT: 400px">
					<table cellpadding="0" cellspacing="0" border="0">
					<col width="70">
					<col width="250">
					<col width="50">
					<col width="250">
					<col width="50">
					<col width="250">
					<tbody>
					<%
						for (int i = 0; i < 10; i++) {
							bizAppId    = bMsg.F.no(i).bizAppId_F1.getValue();
							sysMenuNm   = bMsg.F.no(i).sysMenuNm_F1.getValue();
							wfAppNm     = bMsg.F.no(i).wfAppNm_F1.getValue();
							sysUsbleFlg = bMsg.F.no(i).sysUsbleFlg_F1.getValue();
							strHtml     = "";
							if (wfAppNm.equals("")) {
								strHtml = "<" + "input type=\"Button\" name=\"SelectAppR\" class=\"pBtn24\" value=\"" + sysMenuNm + "\" onclick=\"sendServer(this, " + i + ")\"";
								if (!sysUsbleFlg.equals("Y") || sysMenuNm.equals("")) {
									strHtml = strHtml + " disabled=\"true\"";
								}
								strHtml = strHtml + ">";
							} else {
								strHtml = "<" + "input type=\"Button\" name=\"SelectAppR\" class=\"pBtn24\" value=\"" + sysMenuNm + "\" onClick=\"disp('" + wfAppNm + "')\"";
								if (!sysUsbleFlg.equals("Y") || sysMenuNm.equals("")) {
									strHtml = strHtml + " disabled=\"true\"";
								}
								strHtml = strHtml + ">";
							}
					%>
							<tr height="40">
								<td>&nbsp;</td>
								<td><%= strHtml %></td>
							</tr>
					<%
						}
					%>
						<ezf:skip>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppR" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppR" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppR" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppR" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppR" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppR" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppR" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppR" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectAppR" value="" class="pBtn24" disabled="true"></td>
						</tr>
						</ezf:skip>
					</tbody>
					</table>
					</div>
				</td>
			</table>
<script type="text/javascript">
<!--

function disp(url){
	if (url.length >= 4) {
	    if (url.substring(0,4) == "http") {
	        window.open(url, "_blank", "");
        } else {
            window.open(url, "_self", "");
        }
    }
}

// -->
</script>


<%-- **** Task specific area ends here **** --%>
