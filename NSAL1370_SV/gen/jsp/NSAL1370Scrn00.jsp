<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170508101923 --%>
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
			<input type="hidden" name="pageID" value="NSAL1370Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Service Tier Pricing Popup">

<center>
	<br><br><br>
	<table width="500px">
		<col width="030px">
		<col width="220px">
		<col width="080px">
		<col width="170px">
		<tr>
			<td class="stab">
				Model
			</td>
			<td>
				<ezf:inputText name="t_MdlNm" ezfName="t_MdlNm" value="IRADV4245" otherAttr=" tabindex=\"-1\" size=\"20\" maxlength=\"90\""/>
			</td>
			<td class="stab">
				Billing Counter
			</td>
			<td>
				<ezf:inputText name="mtrLbDescTxt_BL" ezfName="mtrLbDescTxt_BL" value="BIL TOTAL" otherAttr=" tabindex=\"-1\" size=\"20\" maxlength=\"50\""/>
			</td>
		</tr>
	</table>

	<hr>
	<table width="450px">
		<col width="100px">
		<col width="350px">
		<tr>
			<td>
				<ezf:inputButton name="Add_Tier" value="+" htmlClass="pBtn1" />
				<ezf:inputButton name="Delete_Tier" value="-" htmlClass="pBtn1" />
			</td>
			<td></td>
		</tr>
	</table>

	<div id="detail" style="overflow-x:none; overflow-y:none; width:467px; float:center;">
	<table border="1" cellpadding="1" cellspacing="0" width="450px" style="table-layout:fixed;">
		<col width="080px">
		<col width="100px">
		<col width="100px">
		<col width="170px">
		<tr>
			<td class="pClothBs" align="center" rowspan="2">
				Tier Type
			</td>
			<td class="pClothBs" align="center" colspan="2">
				Covered Images Per Unit
			</td>
			<td class="pClothBs" align="center" rowspan="2">
				Excess Per Image Charge
			</td>
		</tr>
		<tr>
			<td class="pClothBs" align="center">
				Range From
			</td>
			<td class="pClothBs" align="center">
				Range To
			</td>
		</tr>
	</table>
	</div>

	<div id="detail" style="overflow-x:none; overflow-y:scroll; width:467px; height:400px; float:center; margin-left:17px;">
	<table id="A" border="1" cellpadding="1" cellspacing="0" width="450px" style="table-layout:fixed;">
		<col width="080px">
		<col width="100px" align="center">
		<col width="100px" align="center">
		<col width="170px" align="center">

		<ezf:row ezfHyo="A">
		<tr id="id_row{EZF_ROW_NUMBER}">
			<td>
				<ezf:inputText name="prcSvcTierTpDescTxt_A" ezfName="prcSvcTierTpDescTxt_A" value="Tier1" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\" size=\"10\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/>
			</td>
			<td>
				<ezf:inputText name="minCopyVolCnt_A" ezfName="minCopyVolCnt_A" value="0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"11\""/>
			</td>
			<td>
				<ezf:inputText name="maxCopyVolCnt_A" ezfName="maxCopyVolCnt_A" value="24,000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"11\""/>
			</td>
			<td>
				<ezf:inputText name="xsMtrAmtRate_A" ezfName="xsMtrAmtRate_A" value="0.10000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"7\""/>
			</td>
		</tr>
		</ezf:row>

		<ezf:skip>
		<tr class="pEvenNumberBGcolor">
			<td>
				<input type="text" readOnly tabIndex="-1" name="" ezfname="" value="Tier2" size="10" maxlength="50" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A">
			</td>
			<td>
				<input type="text" class="pHsu pTxtR" name="" ezfname="" value="24,001" size="11" maxlength="11" ezfhyo="A">
			</td>
			<td>
				<input type="text" class="pHsu pTxtR" name="" ezfname="" value="50,000" size="11" maxlength="11" ezfhyo="A">
			</td>
			<td>
				<input type="text" class="pHsu pTxtR" name="" ezfname="" value="0.05000" size="22" maxlength="7" ezfhyo="A">
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" readOnly tabIndex="-1" name="" ezfname="" value="Tier3" size="10" maxlength="50" style="border:none;background-color:transparent;padding:0px;" ezfhyo="A">
			</td>
			<td>
				<input type="text" class="pHsu pTxtR" name="" ezfname="" value="50,001" size="11" maxlength="11" ezfhyo="A">
			</td>
			<td>
				<input type="text" class="pHsu pTxtR" name="" ezfname="" value="999,999,999" size="11" maxlength="11" ezfhyo="A">
			</td>
			<td>
				<input type="text" class="pHsu pTxtR" name="" ezfname="" value="0.01000" size="22" maxlength="7" ezfhyo="A">
			</td>
		</tr>
		</ezf:skip>

	</table>
	</div>

</center>


<%-- **** Task specific area ends here **** --%>
