<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20151210104855 --%>
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
			<input type="hidden" name="pageID" value="NWAL1650Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Control Fields PopUp">

<center>
	<table>
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<table border="0" cellpadding="1" cellspacing="0">
					<tr height="30">
						&nbsp;
					</tr>
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0" style="margin-left:1px">
								<col width="50">
								<col width="100">
								<col width="10">
								<col width="50">
								<col width="100">
								<tr>
									<td>Order#</td>
									<td class="pOut"><ezf:label name="cpoOrdNum" ezfName="cpoOrdNum" /></td>
									<td>&nbsp;</td>
									<td>Line#</td>
									<td class="pOut"><ezf:label name="xxDtlLineNum" ezfName="xxDtlLineNum" /></td>
								</tr>
							</table>
							<br>
							<br>
							<tr>
								<td style="font-size:12px;">Field Names</td>
							</tr>
							<table border="1" cellpadding="1" cellspacing="0" style="margin-left:1px">
								<col id="bllgAttrb_1" align="center" width="120">
								<col id="bllgAttrb_2" align="center" width="120">
								<col id="bllgAttrb_3" align="center" width="120">
								<col id="bllgAttrb_4" align="center" width="120">
								<col id="bllgAttrb_5" align="center" width="120">
								<col id="bllgAttrb_6" align="center" width="120">
								<tr>
									<td class="pClothBs" style="word-break:break-all;"><ezf:label name="bllgAttrbNm_1" ezfName="bllgAttrbNm_1" /></td>
									<td class="pClothBs" style="word-break:break-all;"><ezf:label name="bllgAttrbNm_2" ezfName="bllgAttrbNm_2" /></td>
									<td class="pClothBs" style="word-break:break-all;"><ezf:label name="bllgAttrbNm_3" ezfName="bllgAttrbNm_3" /></td>
									<td class="pClothBs" style="word-break:break-all;"><ezf:label name="bllgAttrbNm_4" ezfName="bllgAttrbNm_4" /></td>
									<td class="pClothBs" style="word-break:break-all;"><ezf:label name="bllgAttrbNm_5" ezfName="bllgAttrbNm_5" /></td>
									<td class="pClothBs" style="word-break:break-all;"><ezf:label name="bllgAttrbNm_6" ezfName="bllgAttrbNm_6" /></td>
								</tr>
								<tr>
									<td><ezf:inputText name="bllgAttrbValTxt_1" ezfName="bllgAttrbValTxt_1" value="ABC10001       " otherAttr=" size=\"15\" maxlength=\"50\""/></td>
									<td><ezf:inputText name="bllgAttrbValTxt_2" ezfName="bllgAttrbValTxt_2" value="BULLS          " otherAttr=" size=\"15\" maxlength=\"50\""/></td>
									<td><ezf:inputText name="bllgAttrbValTxt_3" ezfName="bllgAttrbValTxt_3" value="RM 200         " otherAttr=" size=\"15\" maxlength=\"50\""/></td>
									<td><ezf:inputText name="bllgAttrbValTxt_4" ezfName="bllgAttrbValTxt_4" value="DEFENSIVE COACH" otherAttr=" size=\"15\" maxlength=\"50\""/></td>
									<td><ezf:inputText name="bllgAttrbValTxt_5" ezfName="bllgAttrbValTxt_5" value="DEFENSIVE COACH" otherAttr=" size=\"15\" maxlength=\"50\""/></td>
									<td><ezf:inputText name="bllgAttrbValTxt_6" ezfName="bllgAttrbValTxt_6" value="DEFENSIVE COACH" otherAttr=" size=\"15\" maxlength=\"50\""/></td>
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
