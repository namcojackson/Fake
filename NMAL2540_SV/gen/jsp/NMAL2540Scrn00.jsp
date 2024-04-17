<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160521053318 --%>
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
			<input type="hidden" name="pageID" value="NMAL2540Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Address input Popup">

			<center>
				<table style="table-layout:fixed;">
					<col valign="top" width="60">
					<col valign="top" width="450">
					<tr>
						<td class="stab">Address1</td>
						<td>
							<ezf:inputText name="firstLineAddr_H1" ezfName="firstLineAddr_H1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
						</td>
					</tr>
					<tr>
						<td class="stab">Address2</td>
						<td>
							<ezf:inputText name="scdLineAddr_H1" ezfName="scdLineAddr_H1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
						</td>
					</tr>
					<tr>
						<td class="stab">Address3</td>
						<td>
							<ezf:inputText name="thirdLineAddr_H1" ezfName="thirdLineAddr_H1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
						</td>
					</tr>
					<tr>
						<td class="stab">Address4</td>
						<td>
							<ezf:inputText name="frthLineAddr_H1" ezfName="frthLineAddr_H1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
						</td>
					</tr>
					<tr>
						<td class="stab"><ezf:anchor name="ctyAddr_H1" ezfName="ctyAddr_H1" ezfEmulateBtn="1" ezfGuard="OpenWin_City" >City</ezf:anchor></td>
						<td>
							<ezf:inputText name="ctyAddr_H1" ezfName="ctyAddr_H1" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/>
						</td>
					</tr>
					<tr>
						<td class="stab"><ezf:anchor name="stCd_H1" ezfName="stCd_H1" ezfEmulateBtn="1" ezfGuard="OpenWin_St" >State</ezf:anchor></td>
						<td>
							<ezf:inputText name="stCd_H1" ezfName="stCd_H1" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/>
						</td>
					</tr>
					<tr>
						<td class="stab">Province</td>
						<td>
							<ezf:inputText name="provNm_H1" ezfName="provNm_H1" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/>
						</td>
					</tr>
					<tr>
						<td class="stab"><ezf:anchor name="cntyNm_H1" ezfName="cntyNm_H1" ezfEmulateBtn="1" ezfGuard="OpenWin_Cnty" >County</ezf:anchor></td>
						<td>
							<ezf:inputText name="cntyNm_H1" ezfName="cntyNm_H1" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/>
						</td>
					</tr>
					<tr>
						<td class="stab"><ezf:anchor name="postCd_H1" ezfName="postCd_H1" ezfEmulateBtn="1" ezfGuard="OpenWin_Post" >Postal Code</ezf:anchor></td>
						<td>
							<ezf:inputText name="postCd_H1" ezfName="postCd_H1" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/><ezf:inputButton name="GetAddress" value="Get" htmlClass="pBtn4" otherAttr=" style=\"margin-left:5px;\""/>
						</td>
					</tr>
				</table>
				<table style="table-layout:fixed;">
					<col valign="top" width="60">
					<col valign="top" width="40">
					<col valign="top" width="410">
					<tr>
						<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Ctry" >Country</ezf:anchor></td>
						<td>
							<ezf:inputText name="ctryCd_H1" ezfName="ctryCd_H1" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>
						</td>
						<td>
							<ezf:inputText name="ctryNm_H1" ezfName="ctryNm_H1" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/>
						</td>
					</tr>
				</table>
			</center>


<%-- **** Task specific area ends here **** --%>
