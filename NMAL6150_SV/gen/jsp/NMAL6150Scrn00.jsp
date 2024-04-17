<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160512052800 --%>
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
			<input type="hidden" name="pageID" value="NMAL6150Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Suggested Address Popup">
			
			
			<div style="margin-left:110px; margin-top:20px;">
				<fieldset style="width:550px">
					<table border="0" style="table-layout:fixed; margin-left:10px;" width="1">
						<col width="80">
						<col width="450">
						
						<tr height="30">
							<td class="stab" colspan="2"><span style="width:130px;"><b>Original Address</b></span><ezf:inputButton name="ApplyOrigAddr" value="Apply" htmlClass="pBtn4" /></td>
						</tr>
						<tr height="20">
							<td class="stab">Address1</td>
							<td><ezf:inputText name="firstLineAddr_O1" ezfName="firstLineAddr_O1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/></td>
						</tr>
						<tr height="20">
							<td class="stab">Address2</td>
							<td><ezf:inputText name="scdLineAddr_O1" ezfName="scdLineAddr_O1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/></td>
						</tr>
						<tr height="20">
							<td class="stab">Address3</td>
							<td><ezf:inputText name="thirdLineAddr_O1" ezfName="thirdLineAddr_O1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/></td>
						</tr>
						<tr height="20">
							<td class="stab">Address4</td>
							<td><ezf:inputText name="frthLineAddr_O1" ezfName="frthLineAddr_O1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/></td>
						</tr>
						<tr height="20">
							<td class="stab">City</td>
							<td><ezf:inputText name="ctyAddr_O1" ezfName="ctyAddr_O1" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/></td>
						</tr>
						<tr height="20">
							<td class="stab">State</td>
							<td><ezf:select name="stCd_O1" ezfName="stCd_O1" ezfBlank="1" ezfCodeName="stCd_O2" ezfDispName="stNm_O2" /></td>
						</tr>
						<tr height="20">
							<td class="stab">Postal Code</td>
							<td>
								<ezf:inputText name="postCd_O1" ezfName="postCd_O1" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/>
							</td>
						</tr>
						<tr height="20">
							<td class="stab">County</td>
							<td><ezf:inputText name="cntyNm_O1" ezfName="cntyNm_O1" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
						</tr>
						<tr height="20">
							<td class="stab">Province</td>
							<td><ezf:inputText name="provNm_O1" ezfName="provNm_O1" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/></td>
						</tr>
					</table>
				</fieldset>
				
				<div style="height:30px;"></div>
				
				<fieldset style="width:550px">
					<table border="0" style="table-layout:fixed; margin-left:10px;" width="1">
						<col width="80">
						<col width="450">
						
						<tr height="30">
							<td class="stab" colspan="2"><span style="width:130px;"><b>Suggested Address</b></span><ezf:inputButton name="ApplySugAddr" value="Apply" htmlClass="pBtn4" /></td>
						</tr>
						<tr height="20">
							<td class="stab">Address1</td>
							<td><ezf:inputText name="firstLineAddr_S1" ezfName="firstLineAddr_S1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/></td>
						</tr>
						<tr height="20">
							<td class="stab">Address2</td>
							<td><ezf:inputText name="scdLineAddr_S1" ezfName="scdLineAddr_S1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/></td>
						</tr>
						<tr height="20">
							<td class="stab">Address3</td>
							<td><ezf:inputText name="thirdLineAddr_S1" ezfName="thirdLineAddr_S1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/></td>
						</tr>
						<tr height="20">
							<td class="stab">Address4</td>
							<td><ezf:inputText name="frthLineAddr_S1" ezfName="frthLineAddr_S1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/></td>
						</tr>
						<tr height="20">
							<td class="stab">City</td>
							<td><ezf:inputText name="ctyAddr_S1" ezfName="ctyAddr_S1" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/></td>
						</tr>
						<tr height="20">
							<td class="stab">State</td>
							<td><ezf:select name="stCd_S1" ezfName="stCd_S1" ezfBlank="1" ezfCodeName="stCd_S2" ezfDispName="stNm_S2" /></td>
						</tr>
						<tr height="20">
							<td class="stab">Postal Code</td>
							<td>
								<ezf:inputText name="postCd_S1" ezfName="postCd_S1" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/>
							</td>
						</tr>
						<tr height="20">
							<td class="stab">County</td>
							<td><ezf:inputText name="cntyNm_S1" ezfName="cntyNm_S1" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
						</tr>
						<tr height="20">
							<td class="stab">Province</td>
							<td><ezf:inputText name="provNm_S1" ezfName="provNm_S1" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/></td>
						</tr>
					</table>
				</fieldset>
			</div>
			

<%-- **** Task specific area ends here **** --%>
