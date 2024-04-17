<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160805125211 --%>
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
			<input type="hidden" name="pageID" value="NPAL0100Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Serial# Entry For Dropship">
			


<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
<%-- ######################################## HEADER TAB ######################################## --%>



<%-- ######################################## HEADER ######################################## --%>
				<div class="pTab_BODY">
					<table border="0" cellpadding="1" cellspacing="0" align="center">
						<tr>
							<td>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="120">
									<col width="160">
									<col width="480">
									<tr>
										<td class="stab">Supplier:</td>
										<td class="pOut" colspan="2"><ezf:label name="prntVndNm" ezfName="locNm_H1" />
									</tr>

									<tr>
										<td class="stab">Supplier Site:</td>
										<td class="pOut"><ezf:label name="vndCd" ezfName="vndCd" />
										<td class="pOut"><ezf:label name="locNm_H1" ezfName="locNm_H1" />
									</tr>
								</table>

								<table border="0" cellpadding="1" cellspacing="0">
									<col width="120">
									<col width="280">
									<col width="30">
									<col width="60">
									<col width="80">
									<tr>
										<td class="stab">PO Number :</td>
										<td class="pOut"><ezf:label name="custIssPoNum" ezfName="custIssPoNum" />
										<td></td>
										<td class="stab">PO Date :</td>
										<td class="pOut"><ezf:label name="custIssPoDt" ezfName="custIssPoDt" />
									</tr>
								</table>

								<table border="0" cellpadding="1" cellspacing="0">


									<tr>

									</tr>
								</table>

								<table border="0" cellpadding="1" cellspacing="0">
									<col width="120">
									<col width="280">
									<col width="30">
									<col width="120">
									<col width="64">
									<col width="30">
									<tr>
										<td class="stab">Document Source Type :</td>
										<td class="pOut"><ezf:label name="poOrdSrcNm" ezfName="poOrdSrcNm" />
										<td></td>
										<td class="stab">Source Doc# / Qaulifier :</td>
										<td class="pOut"><ezf:label name="cpoOrdNum" ezfName="cpoOrdNum" />
										<td class="pOut"><ezf:label name="poQlfyCd" ezfName="poQlfyCd" />
									</tr>
								</table>

								<table border="0" cellpadding="1" cellspacing="0">
									<col width="120">
									<col width="160">
									<col width="480">

									<tr>
										<td class="stab">Dealer Code :</td>
										<td class="pOut"><ezf:label name="billToCustCd" ezfName="billToCustCd" />
										<td class="pOut"><ezf:label name="locNm_H2" ezfName="locNm_H2" />
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<hr>

					<table border="1" cellpadding="1" cellspacing="0" align="center">
						<col width="128">
						<col width="240">
						<col width="80">
						<col width="80">

						<tr>
							<td class="pClothBs" height="28" align="center">Item Code</td>
							<td class="pClothBs" height="28" align="center">Item Name</td>
							<td class="pClothBs" height="28" align="center">Quantity</td>
<!--
							<td class="pClothBs" height="28" align="center">Kit</td>
-->
							<td class="pClothBs" height="28" align="center">Partial Quantity</td>
						</tr>
						<tr>
							<td class="pOut"><ezf:label name="mdseCd" ezfName="mdseCd" />
							<td class="pOut"align="left"><ezf:label name="mdseDescShortTxt" ezfName="mdseDescShortTxt" />
							<td class="pOut"align="right"><ezf:label name="poQty" ezfName="poQty" />
<!--
							<td class="pOut"><label ezfout name="coa1L3If" ezfname="coa1L3If" >Yes</label>
-->
							<td class="pOut"align="right"><ezf:label name="invQty" ezfName="invQty" />
						</tr>
					</table>

					<hr>

	<%-- ######################################## DETAIL ######################################## --%>
					<table border="0" cellpadding="1" cellspacing="0" align="center">
						<tr>
							<td>
<%--
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="130" align="center">
									<col width="50" align="center">
									<col width="32" align="right">
									<col width="15" align="center">
									<col width="32" align="right">
									<col width="15" align="center">
									<col width="32" align="right">
									<col width="5"  align="center">
									<col width="71" align="center">
									<col width="5"  align="center">
									<col width="71" align="center">

									<tr>
										<td>&nbsp;</td>
										<td class="stab">Showing</td>
										<td class="pOut">1</td>
										<td class="stab">to</td>
										<td class="pOut">20</td>
										<td class="stab">of</td>
										<td class="pOut">200</td>
										<td>&nbsp;</td>
										<td align="center"><ezf:inputButton name="PagePrev" value="Prev" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" /></td>
										<td></td>
										<td align="center"><ezf:inputButton name="PageNext" value="Next" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" /></td>
									</tr>
								</table>
--%>								
<%-- Pagenation area start ================================================================ --%>
								<table border="0" cellpadding="1" cellspacing="0" width="480">
									<tr align="right">
										<td>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="A" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
											</jsp:include>
										</td>
									</tr>
								</table>
<%-- Pagenation area end ================================================================ --%>

								<div style="width:494;">
									<table border="1" cellpadding="1" cellspacing="0">
										<col width="50"  align="center">
										<col width="264" align="center">
										<col width="72"  align="center">
										<col width="72"  align="center">

										<tr>
											<td class="pClothBs" height="28">Qty Num</td>
											<td class="pClothBs" height="28">Selial Num</td>
											<td class="pClothBs" height="28">&nbsp;</td>
											<td class="pClothBs" height="28">&nbsp;</td>
										</tr>
									</table>
								</div>

								<div style="overflow-y:scroll; height:315; width:494;">
									<table border="1" cellpadding="1" cellspacing="0" id="A">
										<col width="50"  align="center">
										<col width="264" align="center">
										<col width="72"  align="center">
										<col width="72"  align="center">

										<ezf:row ezfHyo="A">
										<tr>
											<td><ezf:label name="xxRowNum_A1" ezfName="xxRowNum_A1" ezfHyo="A" ezfArrayNo="0" />
											<td><ezf:inputText name="serNum_A1" ezfName="serNum_A1" value="123456789-123456789-123456789-" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"32\" maxlength=\"30\""/></td>

											<td align="center"><ezf:inputButton name="Edit" value="Edit" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" /></td>
											<td align="center"><ezf:inputButton name="Cancel" value="Cancel" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" /></td>
										</tr>
										</ezf:row>
										
										<ezf:skip>
										<tr class="pEvenNumberBGcolor">
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">2</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">3</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										<tr class="pEvenNumberBGcolor">
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">4</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">5</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										<tr class="pEvenNumberBGcolor">
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">6</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">7</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										<tr class="pEvenNumberBGcolor">
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">8</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">9</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										<tr class="pEvenNumberBGcolor">
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">10</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">11</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										<tr class="pEvenNumberBGcolor">
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">12</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">13</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										<tr class="pEvenNumberBGcolor">
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">14</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">15</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										<tr class="pEvenNumberBGcolor">
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">16</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">17</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										<tr class="pEvenNumberBGcolor">
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">18</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										<tr>
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">19</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										<tr class="pEvenNumberBGcolor">
											<td><label ezfout name="xxRowNum_A1" ezfname="xxRowNum_A1" ezfhyo="A">20</label>
											<td><input type="text" size="32" maxlength="30" value="123456789-123456789-123456789-" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>

											<td align="center"><input type="button" class="pBtn6" value="Edit"></td>
											<td align="center"><input type="button" class="pBtn6" value="Cancel"></td>
										</tr>
										</ezf:skip>
									</table>
								</div>
								<div style="width:494;">
									<table border="0" cellpadding="1" cellspacing="1" width="100%">
										<tr>
											<td align="center">
												<ezf:inputButton name="Apply" value="Apply" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" />
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
