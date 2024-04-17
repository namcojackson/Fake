<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100210042207 --%>
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
			<input type="hidden" name="pageID" value="ZZOL0110Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Admin Menu">
			<!-- Parts or S21 -->
			<table width="100%" height="2" border="0">
				<tr valign="middle">
					<td>&nbsp;</td>
				</tr>
			</table>
			<table width="100%" height="35" border="0" id="sysTable">
				<col width="50">
				<col width="130">
				<col width="40">
				<col width="100">
				<col width="580">
				<tr valign="middle">
					<td>&nbsp;</td>
					<td class="stab" align="left"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company CD</ezf:anchor></td>
					<td><ezf:inputText name="glblCmpyCd_X1" ezfName="glblCmpyCd_X1" value="ABR" otherAttr=" size=\"4\" ezftoupper=\"\""/></td>
					<td class="pout" id="fcNmField"><ezf:label name="glblCmpyNm_X1" ezfName="glblCmpyNm_X1" /></td>
					<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn5" /></td>
				</tr>
				<tr>
					<td colspan="5"><hr></td>
				</tr>
			</table>

			<table width="100%" height="30" border="0">
				<col width="120">
				<col width="250">
				<col width="600">
				<tr valign="middle">
					<td>&nbsp;</td>
					<td class="stab" align="left"><ezf:label name="sysMenuNm_X1" ezfName="sysMenuNm_X1" /></td>
					<td>&nbsp;</td>
				</tr>
			</table>			
			<table cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td align="center">
					<div id="tableRight" style="OVERFLOW-Y: hidden; height: 400px">
					<table cellpadding="0" cellspacing="0" border="0">
					<col width="120">
					<col width="250" align="left">
					<col width="50">
					<col width="250">
					<col width="50">
					<col width="250">
					<tbody>
					<%
						String sysMenuNm   = "";
						String wfAppNm     = "";
						String sysUsbleFlg = "";
						String rqstUrl     = "";
						String strHtml     = "";
						for (int i = 0; i < 10; i++) {
							sysMenuNm   = bMsg.A.no(i).sysMenuNm_A1.getValue();
							wfAppNm     = bMsg.A.no(i).wfAppNm_A1.getValue();
							rqstUrl     = bMsg.A.no(i).rqstUrl_A1.getValue();
							sysUsbleFlg = bMsg.A.no(i).sysUsbleFlg_A1.getValue();
							strHtml     = "";
							if (rqstUrl.equals("")) {
								strHtml = "<" + "input type=\"Button\" name=\"SelectAppL\" class=\"pBtn24\" value=\"" + sysMenuNm + "\" onclick=\"sendServer(this, " + i + ")\"";
								if (!sysUsbleFlg.equals("Y")) {
									strHtml = strHtml + " disabled=\"true\"";
								}
								strHtml = strHtml + ">";
							} else {
								if (rqstUrl.indexOf("s21extn") != -1) {
									
									String propValue = EZDSystemEnv.getString("S21.extn.contextroot.name");
									
									if (propValue != null && propValue.trim().length() != 0) {

										java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("(?i)(^s21extn|s21extnap[0-9])" + "(/.*jsp)");
										java.util.regex.Matcher matcher = pattern.matcher(rqstUrl);
								        
										StringBuffer sb = new StringBuffer();
								        if (matcher.matches()) {
								        	rqstUrl = rqstUrl.replaceFirst(matcher.group(1), propValue);
								        }
								        System.out.println("[getCustomAppURL]Converted CustomApp's URL: " + rqstUrl);
									}
									String winName = sysMenuNm.replaceAll("\\p{Z}","");
									strHtml = "<" + "input type=\"Button\" name=\"SelectAppL\" class=\"pBtn24\" value=\"" + sysMenuNm + "\" onClick=\"f_open_window_max('" + "../" + rqstUrl + "','" + winName + "')\"";
								} else {
									strHtml = "<" + "input type=\"Button\" name=\"SelectAppL\" class=\"pBtn24\" value=\"" + sysMenuNm + "\" onClick=\"disp('" + rqstUrl + "')\"";
								}
								if (!sysUsbleFlg.equals("Y")) {
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
							<td><input type="Button" name="SelectSubL" value="printing" class="pBtn24"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubL" value="User Profile" class="pBtn24"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubL" value="Code Table" class="pBtn24"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubL" value="Security" class="pBtn24"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubL" value="Mail" class="pBtn24"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubL" value="CSV Upload" class="pBtn24"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubL" value="Calender" class="pBtn24"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubL" value="Integration" class="pBtn24"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubL" value="WorkFlow" class="pBtn24"></td>
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
							sysMenuNm   = bMsg.B.no(i).sysMenuNm_B1.getValue();
							wfAppNm     = bMsg.B.no(i).wfAppNm_B1.getValue();
							rqstUrl     = bMsg.B.no(i).rqstUrl_B1.getValue();
							sysUsbleFlg = bMsg.B.no(i).sysUsbleFlg_B1.getValue();
							strHtml     = "";
							if (rqstUrl.equals("")) {
								strHtml = "<" + "input type=\"Button\" name=\"SelectAppC\" class=\"pBtn24\" value=\"" + sysMenuNm + "\" onclick=\"sendServer(this, " + i + ")\"";
								if (!sysUsbleFlg.equals("Y")) {
									strHtml = strHtml + " disabled=\"true\"";
								}
								strHtml = strHtml + ">";
							} else {
								if (rqstUrl.indexOf("s21extn") != -1) {
									
									String propValue = EZDSystemEnv.getString("S21.extn.contextroot.name");
									
									if (propValue != null && propValue.trim().length() != 0) {

										java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("(?i)(^s21extn|s21extnap[0-9])" + "(/.*jsp)");
										java.util.regex.Matcher matcher = pattern.matcher(rqstUrl);
								        
										StringBuffer sb = new StringBuffer();
								        if (matcher.matches()) {
								        	rqstUrl = rqstUrl.replaceFirst(matcher.group(1), propValue);
								        }
								        System.out.println("[getCustomAppURL]Converted CustomApp's URL: " + rqstUrl);
									}
									String winName = sysMenuNm.replaceAll("\\p{Z}","");
									strHtml = "<" + "input type=\"Button\" name=\"SelectAppC\" class=\"pBtn24\" value=\"" + sysMenuNm + "\" onClick=\"f_open_window_max('" + "../" + rqstUrl + "','" + winName + "')\"";
								} else {
									strHtml = "<" + "input type=\"Button\" name=\"SelectAppC\" class=\"pBtn24\" value=\"" + sysMenuNm + "\" onClick=\"disp('" + rqstUrl + "')\"";
								}
								if (!sysUsbleFlg.equals("Y")) {
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
							<td><input type="Button" name="SelectSubC" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubC" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubC" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubC" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubC" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubC" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubC" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubC" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubC" value="" class="pBtn24" disabled="true"></td>
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
							sysMenuNm   = bMsg.C.no(i).sysMenuNm_C1.getValue();
							wfAppNm     = bMsg.C.no(i).wfAppNm_C1.getValue();
							rqstUrl     = bMsg.C.no(i).rqstUrl_C1.getValue();
							sysUsbleFlg = bMsg.C.no(i).sysUsbleFlg_C1.getValue();
							strHtml     = "";
							if (rqstUrl.equals("")) {
								strHtml = "<" + "input type=\"Button\" name=\"SelectAppR\" class=\"pBtn24\" value=\"" + sysMenuNm + "\" onclick=\"sendServer(this, " + i + ")\"";
								if (!sysUsbleFlg.equals("Y")) {
									strHtml = strHtml + " disabled=\"true\"";
								}
								strHtml = strHtml + ">";
								
							} else {
								if (rqstUrl.indexOf("s21extn") != -1) {
									
									String propValue = EZDSystemEnv.getString("S21.extn.contextroot.name");
									
									if (propValue != null && propValue.trim().length() != 0) {

										java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("(?i)(^s21extn|s21extnap[0-9])" + "(/.*jsp)");
										java.util.regex.Matcher matcher = pattern.matcher(rqstUrl);
								        
										StringBuffer sb = new StringBuffer();
								        if (matcher.matches()) {
								        	rqstUrl = rqstUrl.replaceFirst(matcher.group(1), propValue);
								        }
								        System.out.println("[getCustomAppURL]Converted CustomApp's URL: " + rqstUrl);
									}
									String winName = sysMenuNm.replaceAll("\\p{Z}","");
									strHtml = "<" + "input type=\"Button\" name=\"SelectAppR\" class=\"pBtn24\" value=\"" + sysMenuNm + "\" onClick=\"f_open_window_max('" + "../" + rqstUrl + "','" + winName + "')\"";
								} else {
									strHtml = "<" + "input type=\"Button\" name=\"SelectAppR\" class=\"pBtn24\" value=\"" + sysMenuNm + "\" onClick=\"disp('" + rqstUrl + "')\"";
								}
								if (!sysUsbleFlg.equals("Y")) {
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
							<td><input type="Button" name="SelectSubR" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubR" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubR" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubR" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubR" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubR" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubR" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubR" value="" class="pBtn24" disabled="true"></td>
						</tr>
						<tr height="40">
							<td>&nbsp;</td>
							<td><input type="Button" name="SelectSubR" value="" class="pBtn24" disabled="true"></td>
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
	    	var pos = url.indexOf("wfadmin");
	    	if (pos == -1) {
	    		window.open(url, "_blank", "");
	    	} else {
	    		window.open(url, "_blank", "status=1,scrollbars=1,resizable=1,width=900px,height=450px");
	    	}
        } else {
		    urlpageid = document.getElementsByName("uji.pageid")[0].value;
		    if (urlpageid != "") {
			    url = url + "&uji.pageid=" + urlpageid;
		    }
            window.open(url, "_self", "");
        }
    }
}

// -->
</script>
<script>
function f_open_window_max( aURL, aWinName )
{
   var wOpen;
   var sOptions;

   sOptions = 'status=yes,menubar=no,scrollbars=yes,resizable=yes,toolbar=no';
   sOptions = sOptions + ',width=' + (screen.availWidth - 17).toString();
   sOptions = sOptions + ',height=' + (screen.availHeight - 65).toString();
   sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';

   wOpen = window.open( '', aWinName, sOptions );
   wOpen.location = aURL;
   wOpen.focus();
   wOpen.moveTo( 0, 0 );
   //wOpen.resizeTo( screen.availWidth, screen.availHeight  );
   return wOpen;
}
</script>

<%-- **** Task specific area ends here **** --%>
