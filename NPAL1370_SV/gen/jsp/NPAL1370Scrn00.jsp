<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160401102104 --%>
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
			<input type="hidden" name="pageID" value="NPAL1370Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Min-Max Planning Copy">

<center>
	<table width="100%">
		<tr>
			<td valign="top">
<%-- ######################################## HEADER ######################################## --%>
				<table width="100%" cellpadding="1" cellspacing="1" align="center" border="0" bordercolor="red">
					<tr>
						<td>
							<table border="0" bordercolor="green" >
                                <col width="130" align="left">
                                <col width="60"  align="left">
                                <col width="10"  align="left">
                                <col width="190" align="left">
                                <tr height="25">
                                    <td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_CopyfromWH" >Copy from WH</ezf:anchor></td>
                                    <td><ezf:inputText name="rtlWhCd_FR" ezfName="rtlWhCd_FR" value="WWWWWWWW" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                    <td><ezf:inputButton name="SetCopyfromWHName" ezfName="SetCopyfromWHName" value=">>" /></td>
                                    <td><ezf:inputText name="rtlWhNm_FR" ezfName="rtlWhNm_FR" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"30\" maxlength=\"45\" ezftoupper=\"\""/></td>
                                </tr>
                                <tr height="25">
                                    <td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_CopyfromSWH" >Copy from Sub WH</ezf:anchor></td>
                                    <td><ezf:inputText name="rtlSwhCd_FR" ezfName="rtlSwhCd_FR" value="WWWWWWWW" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                    <td><ezf:inputButton name="SetCopyfromSWHName" ezfName="SetCopyfromSWHName" value=">>" /></td>
                                    <td><ezf:inputText name="rtlSwhNm_FR" ezfName="rtlSwhNm_FR" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"30\" maxlength=\"45\" ezftoupper=\"\""/></td>
                                </tr>
                                <tr height="25">
                                    <td>&nbsp;</td>
                                </tr>
							</table>
						</td>
						<td>
							<table border="0" bordercolor="green" >
                                <col width="130" align="left">
                                <col width="60"  align="left">
                                <col width="10"  align="left">
                                <col width="190" align="left">
                                <tr height="25">
                                    <td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_CopytoWH" >Copy to WH</ezf:anchor></td>
                                    <td><ezf:inputText name="rtlWhCd_TO" ezfName="rtlWhCd_TO" value="WWWWWWWW" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                    <td><ezf:inputButton name="SetCopytoWHName" ezfName="SetCopytoWHName" value=">>" /></td>
                                    <td><ezf:inputText name="rtlWhNm_TO" ezfName="rtlWhNm_TO" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"30\" maxlength=\"45\" ezftoupper=\"\""/></td>
                                </tr>
                                <tr height="25">
                                    <td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_CopytoSWH" >Copy to Sub WH</ezf:anchor></td>
                                    <td><ezf:inputText name="rtlSwhCd_TO" ezfName="rtlSwhCd_TO" value="WWWWWWWW" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                    <td><ezf:inputButton name="SetCopytoSWHName" ezfName="SetCopytoSWHName" value=">>" /></td>
                                    <td><ezf:inputText name="rtlSwhNm_TO" ezfName="rtlSwhNm_TO" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"30\" maxlength=\"45\" ezftoupper=\"\""/></td>
                                </tr>
								<tr>
                                    <td class="stab">Copy to Enabled Item Only</td>
                                    <td class="stab">
                                        <ezf:inputCheckBox name="mrpEnblFlg" ezfName="mrpEnblFlg" value="Y" />
                                    </td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>



<%-- **** Task specific area ends here **** --%>
