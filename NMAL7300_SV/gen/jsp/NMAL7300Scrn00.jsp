<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171215172427 --%>
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
			<input type="hidden" name="pageID" value="NMAL7300Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Qty Discount for Price Adjustment Popup">
			
			
<!-- HTML Start -->
<%@ page import="business.servlet.NMAL7300.NMAL7300BMsg" %>
<%@ page import="business.servlet.NMAL7300.NMAL7300_BBMsg" %>
<% NMAL7300BMsg bMsg = (NMAL7300BMsg) databean.getEZDBMsg(); %>


			<center>
				<br/>
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
						<table border="1" cellpadding="1" cellspacing="1">
							<col align="center" width="245" >
							<col align="center" width="155" >
							<col align="center" width="190" >
							<tr>
								<td class="pClothBs">Table Condition</td>
								<td class="pClothBs">Code</td>
								<td class="pClothBs">Description</td>
							</tr>
						</table>
						<div style="overflow-y:scroll; height:122; overflow-x:hidden; width:630;">
						<table id="A" border="1" cellpadding="1" cellspacing="1">
							<col align="center" width="245" >
							<col align="center" width="155" >
							<col align="center" width="190" >
							<ezf:row ezfHyo="A">
								<tr id="id_A_row{EZF_ROW_NUMBER}">
									<td>
										<ezf:inputText name="prcRuleAttrbDescTxt_A" ezfName="prcRuleAttrbDescTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"32\" maxlength=\"50\""/>
									</td>
									<td>
										<ezf:inputText name="prcRuleCondFromTxt_A" ezfName="prcRuleCondFromTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
									</td>
									<td>
										<ezf:inputText name="xxRecNmTxt_A" ezfName="xxRecNmTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"360\""/>
									</td>
								</tr>
							</ezf:row>
						</table>
						</div>
						</td>
					</tr>
				</table>
				<br/>
<!-- Detail Start --->
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td height="25" align="right" style="padding-right: 20px;">
							<ezf:inputButton name="Add_QtyDisc" value="Add" htmlClass="pBtn3" />
							<ezf:inputButton name="Del_QtyDisc" value="Del" htmlClass="pBtn3" />
						</td>
					</tr>
					<tr>
						<td valign="top" colspan="2">
							<table style="table-layout:fixed; width:265" border="1" cellpadding="1" cellspacing="0">
								<col align="center" width="30" >	<!-- Delete/Update			-->
								<col align="center" width="100" >	<!-- Qty					-->
								<col align="center">				<!-- Price Break Amt / Percent		-->
								<tr>
									<td class="pClothBs">Del<br>Upd</td>
									<td class="pClothBs">Qty</td>
<% if ("Y".equals(bMsg.xxYesNoCd.getValue())) { %>
									<td class="pClothBs">Price Break Percent</td>
<% } else { %>
									<td class="pClothBs">Price Break Amt</td>
<% } %>
								</tr>
							</table>
							<div style="overflow-y:scroll; height:290; overflow-x:hidden; width:282; float:left;">
							<table id="B" style="table-layout:fixed; overflow-x:hidden; overflow-y:scroll;" border="1" cellpadding="1" cellspacing="0" width="299px" >
								<col align="center" width="30" >	<!-- Delete/Update			-->
								<col align="center" width="100" >	<!-- Qty					-->
								<col align="center">				<!-- Price Break Amt / Percent		-->
<% int i = 0; %>
								<ezf:row ezfHyo="B">
<% NMAL7300_BBMsg lineMsg = bMsg.B.no(i++); %>
<% if (!"Y".equals(lineMsg.delFlg_B.getValue())) { %>
								<tr id="id_row{EZF_ROW_NUMBER}">
<% } else { %>
								<tr id="id_row{EZF_ROW_NUMBER}" style="display:none;">
<% } %>
									<td>
										<ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="0" />
									</td>
									<td><ezf:inputText name="qtyDiscDtlQty_B" ezfName="qtyDiscDtlQty_B" value="1,234,567,890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"13\" style=\"text-align:right\""/></td>
<% if ("Y".equals(bMsg.xxYesNoCd.getValue())) { %>
									<td><ezf:inputText name="prcRuleDtlRate_B" ezfName="prcRuleDtlRate_B" value="-123,456,789.12" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"19\" style=\"text-align:right\""/></td>
<% } else { %>
									<td><ezf:inputText name="prcRuleDtlTxt_B" ezfName="prcRuleDtlTxt_B" value="-123,456,789.12" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"19\" style=\"text-align:right\""/></td>
<% } %>
								</tr>
								</ezf:row>
								<ezf:skip>
								<tr id="id_row{EZF_ROW_NUMBER}">
									<td>
										<input type="checkbox" value="Y" name="xxChkBox_B" ezfname="xxChkBox_B" ezfHyo="B">
									</td>
									<td><input type="text" size="12" maxlength="13" style="text-align:right" value="1,234,567,890"   name="qtyDiscDtlQty_B" ezfname="qtyDiscDtlQty_B" ezfHyo="B"></td>
									<td><input type="text" size="15" maxlength="19" style="text-align:right" value="-123,456,789.12" name="prcRuleDtlTxt_B" ezfname="prcRuleDtlTxt_B" ezfHyo="B"></td>
								</tr>
								<tr id="id_row{EZF_ROW_NUMBER}">
									<td>
										<input type="checkbox" value="Y" name="xxChkBox_B" ezfname="xxChkBox_B" ezfHyo="B">
									</td>
									<td><input type="text" size="12" maxlength="13" style="text-align:right" value="1,234,567,890"   name="qtyDiscDtlQty_B" ezfname="qtyDiscDtlQty_B" ezfHyo="B"></td>
									<td><input type="text" size="15" maxlength="19" style="text-align:right" value="-123,456,789.12" name="prcRuleDtlTxt_B" ezfname="prcRuleDtlTxt_B" ezfHyo="B"></td>
								</tr>
								<tr id="id_row{EZF_ROW_NUMBER}">
									<td>
										<input type="checkbox" value="Y" name="xxChkBox_B" ezfname="xxChkBox_B" ezfHyo="B">
									</td>
									<td><input type="text" size="12" maxlength="13" style="text-align:right" value="1,234,567,890"   name="qtyDiscDtlQty_B" ezfname="qtyDiscDtlQty_B" ezfHyo="B"></td>
									<td><input type="text" size="15" maxlength="19" style="text-align:right" value="-123,456,789.12" name="prcRuleDtlTxt_B" ezfname="prcRuleDtlTxt_B" ezfHyo="B"></td>
								</tr>
								<tr id="id_row{EZF_ROW_NUMBER}">
									<td>
										<input type="checkbox" value="Y" name="xxChkBox_B" ezfname="xxChkBox_B" ezfHyo="B">
									</td>
									<td><input type="text" size="12" maxlength="13" style="text-align:right" value="1,234,567,890"   name="qtyDiscDtlQty_B" ezfname="qtyDiscDtlQty_B" ezfHyo="B"></td>
									<td><input type="text" size="15" maxlength="19" style="text-align:right" value="-123,456,789.12" name="prcRuleDtlTxt_B" ezfname="prcRuleDtlTxt_B" ezfHyo="B"></td>
								</tr>
								<tr id="id_row{EZF_ROW_NUMBER}">
									<td>
										<input type="checkbox" value="Y" name="xxChkBox_B" ezfname="xxChkBox_B" ezfHyo="B">
									</td>
									<td><input type="text" size="12" maxlength="13" style="text-align:right" value="1,234,567,890"   name="qtyDiscDtlQty_B" ezfname="qtyDiscDtlQty_B" ezfHyo="B"></td>
									<td><input type="text" size="15" maxlength="19" style="text-align:right" value="-123,456,789.12" name="prcRuleDtlTxt_B" ezfname="prcRuleDtlTxt_B" ezfHyo="B"></td>
								</tr>
								<tr id="id_row{EZF_ROW_NUMBER}">
									<td>
										<input type="checkbox" value="Y" name="xxChkBox_B" ezfname="xxChkBox_B" ezfHyo="B">
									</td>
									<td><input type="text" size="12" maxlength="13" style="text-align:right" value="1,234,567,890"   name="qtyDiscDtlQty_B" ezfname="qtyDiscDtlQty_B" ezfHyo="B"></td>
									<td><input type="text" size="15" maxlength="19" style="text-align:right" value="-123,456,789.12" name="prcRuleDtlTxt_B" ezfname="prcRuleDtlTxt_B" ezfHyo="B"></td>
								</tr>
								<tr id="id_row{EZF_ROW_NUMBER}">
									<td>
										<input type="checkbox" value="Y" name="xxChkBox_B" ezfname="xxChkBox_B" ezfHyo="B">
									</td>
									<td><input type="text" size="12" maxlength="13" style="text-align:right" value="1,234,567,890"   name="qtyDiscDtlQty_B" ezfname="qtyDiscDtlQty_B" ezfHyo="B"></td>
									<td><input type="text" size="15" maxlength="19" style="text-align:right" value="-123,456,789.12" name="prcRuleDtlTxt_B" ezfname="prcRuleDtlTxt_B" ezfHyo="B"></td>
								</tr>
								<tr id="id_row{EZF_ROW_NUMBER}">
									<td>
										<input type="checkbox" value="Y" name="xxChkBox_B" ezfname="xxChkBox_B" ezfHyo="B">
									</td>
									<td><input type="text" size="12" maxlength="13" style="text-align:right" value="1,234,567,890"   name="qtyDiscDtlQty_B" ezfname="qtyDiscDtlQty_B" ezfHyo="B"></td>
									<td><input type="text" size="15" maxlength="19" style="text-align:right" value="-123,456,789.12" name="prcRuleDtlTxt_B" ezfname="prcRuleDtlTxt_B" ezfHyo="B"></td>
								</tr>
								<tr id="id_row{EZF_ROW_NUMBER}">
									<td>
										<input type="checkbox" value="Y" name="xxChkBox_B" ezfname="xxChkBox_B" ezfHyo="B">
									</td>
									<td><input type="text" size="12" maxlength="13" style="text-align:right" value="1,234,567,890"   name="qtyDiscDtlQty_B" ezfname="qtyDiscDtlQty_B" ezfHyo="B"></td>
									<td><input type="text" size="15" maxlength="19" style="text-align:right" value="-123,456,789.12" name="prcRuleDtlTxt_B" ezfname="prcRuleDtlTxt_B" ezfHyo="B"></td>
								</tr>
								<tr id="id_row{EZF_ROW_NUMBER}">
									<td>
										<input type="checkbox" value="Y" name="xxChkBox_B" ezfname="xxChkBox_B" ezfHyo="B">
									</td>
									<td><input type="text" size="12" maxlength="13" style="text-align:right" value="1,234,567,890"   name="qtyDiscDtlQty_B" ezfname="qtyDiscDtlQty_B" ezfHyo="B"></td>
									<td><input type="text" size="15" maxlength="19" style="text-align:right" value="-123,456,789.12" name="prcRuleDtlTxt_B" ezfname="prcRuleDtlTxt_B" ezfHyo="B"></td>
								</tr>
								<tr id="id_row{EZF_ROW_NUMBER}">
									<td>
										<input type="checkbox" value="Y" name="xxChkBox_B" ezfname="xxChkBox_B" ezfHyo="B">
									</td>
									<td><input type="text" size="12" maxlength="13" style="text-align:right" value="1,234,567,890"   name="qtyDiscDtlQty_B" ezfname="qtyDiscDtlQty_B" ezfHyo="B"></td>
									<td><input type="text" size="15" maxlength="19" style="text-align:right" value="-123,456,789.12" name="prcRuleDtlTxt_B" ezfname="prcRuleDtlTxt_B" ezfHyo="B"></td>
								</tr>
								<tr id="id_row{EZF_ROW_NUMBER}">
									<td>
										<input type="checkbox" value="Y" name="xxChkBox_B" ezfname="xxChkBox_B" ezfHyo="B">
									</td>
									<td><input type="text" size="12" maxlength="13" style="text-align:right" value="1,234,567,890"   name="qtyDiscDtlQty_B" ezfname="qtyDiscDtlQty_B" ezfHyo="B"></td>
									<td><input type="text" size="15" maxlength="19" style="text-align:right" value="-123,456,789.12" name="prcRuleDtlTxt_B" ezfname="prcRuleDtlTxt_B" ezfHyo="B"></td>
								</tr>
								<tr id="id_row{EZF_ROW_NUMBER}">
									<td>
										<input type="checkbox" value="Y" name="xxChkBox_B" ezfname="xxChkBox_B" ezfHyo="B">
									</td>
									<td><input type="text" size="12" maxlength="13" style="text-align:right" value="1,234,567,890"   name="qtyDiscDtlQty_B" ezfname="qtyDiscDtlQty_B" ezfHyo="B"></td>
									<td><input type="text" size="15" maxlength="19" style="text-align:right" value="-123,456,789.12" name="prcRuleDtlTxt_B" ezfname="prcRuleDtlTxt_B" ezfHyo="B"></td>
								</tr>
								<tr id="id_row{EZF_ROW_NUMBER}">
									<td>
										<input type="checkbox" value="Y" name="xxChkBox_B" ezfname="xxChkBox_B" ezfHyo="B">
									</td>
									<td><input type="text" size="12" maxlength="13" style="text-align:right" value="1,234,567,890"   name="qtyDiscDtlQty_B" ezfname="qtyDiscDtlQty_B" ezfHyo="B"></td>
									<td><input type="text" size="15" maxlength="19" style="text-align:right" value="-123,456,789.12" name="prcRuleDtlTxt_B" ezfname="prcRuleDtlTxt_B" ezfHyo="B"></td>
								</tr>
								</ezf:skip>
							</table>
							</div>
						</td>
					</tr>
				</table>
<!-- Detail End -->
			</center>
<script type="text/javascript" defer>

function displayCal(){

	var dynamicAttrb = document.getElementById("xxComnColOrdTxt").value;
	if(!dynamicAttrb){
		return;
		//dynamicAttrb = "BH0:BH1:BH5:BH6:BH94:BH95:BH96:BH97:BH98:BH99";
	}

	var mainTbl = document.getElementById("mainTbl");
	var attrb = mainTbl.getElementsByTagName("tr");
	var attrbLen = attrb.length;
	for (var i = 0; i < attrbLen; i++) {
    	attrb[i].style.display = 'none';
	}
    
    var calId =  dynamicAttrb.split(":");
    var len = calId.length;
	for (var i = 0; i < len; i++) {
    	var selectedCol = document.getElementById(calId[i]);
    	selectedCol.style.display = 'block';
	}
   }
	


</script>

<script type="text/javascript" defer>
	displayCal();
</script>


<%-- **** Task specific area ends here **** --%>
