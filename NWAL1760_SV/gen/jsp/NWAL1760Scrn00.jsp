<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160220201441 --%>
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
			<input type="hidden" name="pageID" value="NWAL1760Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price List Popup">
<!-- **** Aplication Main Contents area starts hear **** -->

	<div>
		<table border="0" cellpadding="5" cellspacing="0">
			<tr><td>
				<!-- Header area -->
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<colgroup>
									<col width="80">
									<col width="17">
									<col width="360">
									<col width="60">
									<col width="5">
									<col width="120">
								</colgroup>
								<tr>
									<td class="stab"><label>Order Category</label></td>
									<td></td>
									<td><ezf:inputText name="dsOrdCatgDescTxt" ezfName="dsOrdCatgDescTxt" value="WWWWWWWWWXWWWWWW" otherAttr=" maxlength=\"50\" size=\"50\""/></td>
									<td class="stab"><label>CSMP#</label></td>
									<td></td>
									<td><ezf:inputText name="csmpNum" ezfName="csmpNum" value="WWWWWWWWWXWWWWWW" otherAttr=" maxlength=\"20\" size=\"20\""/></td>
								</tr>
								<tr>
									<td class="stab"><label>Order Reason</label></td>
									<td></td>
									<td><ezf:inputText name="dsOrdTpDescTxt" ezfName="dsOrdTpDescTxt" value="WWWWWWWWWXWWWWWW" otherAttr=" maxlength=\"50\" size=\"50\""/></td>
									<td class="stab"><label>Dealer Ref#</label></td>
									<td></td>
									<td><ezf:inputText name="dlrRefNum" ezfName="dlrRefNum" value="WWWWWWWWWXWWWWWW" otherAttr=" maxlength=\"20\" size=\"20\""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<colgroup>
									<col width="96">
									<col width="120">
									<col width="190">
								</colgroup>
								<tr>
									<td class="stab"><label>Customer Account</label></td>
									<td><ezf:inputText name="custAcctNum" ezfName="custAcctNum" value="WWWWWWWWWXWWWWWW" otherAttr=" maxlength=\"20\" size=\"20\""/></td>
									<td><ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="WWWWWWWWWXWWWWWW" otherAttr=" maxlength=\"60\" size=\"60\""/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td></tr>
			
			<tr><td>
				<hr/>
			</td></tr>

			<tr><td>
			<!-- Body area -->

				<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed; ">
					<col width="200"  align="center">
					<col width="560" align="center">
					<tr>
						<td class="pClothBs">Price List Type</td>
						<td class="pClothBs">Price List</td>
					</tr>
				</table>

				<div style="overflow:auto; height:435; width:780">
					<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed; ">
						<col width="200"  align="left">
						<col width="560" align="left">
						<tbody>
							<ezf:row ezfHyo="A">
								<tr id="id_row{EZF_ROW_NUMBER}">
									<td><ezf:label name="prcListTpDescTxt_A" ezfName="prcListTpDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OnClick_PriceList" ><ezf:label name="prcCatgNm_A" ezfName="prcCatgNm_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
								</tr>
							</ezf:row>
							<ezf:skip>
								
								<tr>
									<td><label ezfout>A00000000000003</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWW75</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>A00000000000004</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>
								
								<tr>
									<td><label ezfout>A00000000000005</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>B1111111111111Z</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>
								
								<tr>
									<td><label ezfout>B2222222222222Z</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr>
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr>
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr>
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr>
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr>
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr>
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr>
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr>
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr>
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr>
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr>
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr>
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>

								<tr>
									<td><label ezfout>C2222222222222J</label></td>
									<td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
								</tr>
							</ezf:skip>
						</tbody>
					</table>
				</div>
				
				
			</td></tr>
		</table>
	</div>


<%-- **** Task specific area ends here **** --%>
