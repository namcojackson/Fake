<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20221201173407 --%>
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
			<input type="hidden" name="pageID" value="NFCL5140Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="On Account/Adjustment/Deduction Entry">
<center>
	<table>
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<table border="0">
					<col width="24" align="center">
					<col width="100">
					<col width="5">
					<col width="24" align="center">
					<col width="100">
					<col width="">
					<tr>
						<td>
							<ezf:inputRadio name="xxModeInd" ezfName="xxModeInd" value="1" otherAttr="onClick=\"sendServer('Onchange_RB_OnAccountEntry');\""/>
						</td>
						<td class="stab">On Account</td>
						<td></td>
						<td>
							<ezf:inputRadio name="xxModeInd" ezfName="xxModeInd" value="3" otherAttr="onClick=\"sendServer('Onchange_RB_AdjustmentEntry');\""/>
						</td>
						<td class="stab">Adjustment</td>
					</tr>
				</table>
				<hr/>
				<table>
					<tr>
						<td>
							<div style="word-break:break-all; overflow-x:auto; overflow-y:none; height:20;">
								<table border="0" cellpadding="0" cellspacing="0" width="" height="20">
									<col width="48" align="center">
									<tr>
										<td></td>
									</tr>
								</table>
							</div>
						</td>
						<td>
							<div style="word-break:break-all; overflow-x:auto; overflow-y:none; height:20;">
								<table border="1" cellpadding="1" cellspacing="0" width="" height="20">
									<col width="300" align="center">
									<col width="200" align="center">
									<col width="300" align="center">
									<tr height="20">
										<td>Adj Type</td>
										<td>Amount</td>
										<td>Charge Account</td>
									</tr>
									<tr height="20">
										<td align="left">
											<ezf:select name="arAdjTpCd_P1" ezfName="arAdjTpCd_P1" ezfBlank="1" ezfCodeName="arAdjTpCd" ezfDispName="xxArAdjTpNm" otherEvent1=" onchange=\"sendServer('Onchange_PD_AdjTpCd')\"" otherAttr=" style=\"width:300\""/>
										</td>
										<td align="right"><ezf:inputText name="xxInpAmtNum" ezfName="xxInpAmtNum" value="-999,999,999,999.99" otherAttr=" size=\"30\" style=\"text-align:right\""/></td>
										<td align="center"><ezf:inputText name="xxCmntTxt" ezfName="xxCmntTxt" value="800.000.000.00000.31001.00.00.000.00000.00000" otherAttr=" size=\"35\" maxlength=\"60\" ezftoupper=\"\" tabindex=\"-1\""/>		<ezf:inputButton name="OpenWin_ChargeAccount" value="A" htmlClass="pBtn0" otherAttr=" tabindex=\"-1\""/></td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td></td>
						<td align="right"><ezf:inputButton name="Insert_Record" value="Insert" htmlClass="pBtn12" /></td>
					</tr>
				</table>
				<hr>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
