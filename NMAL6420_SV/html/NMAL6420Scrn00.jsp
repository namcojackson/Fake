<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100710092319 --%>
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
			<input type="hidden" name="pageID" value="NMAL6420Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Location Role Type Maintenance">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>

            
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
					<table height="525">
						<col valign="top">
						<tr>
							<td>

<%-- ######################################## DETAIL ######################################## --%>
<%@ page import="business.servlet.NMAL6420.NMAL6420BMsg" %>
<%@ page import="business.servlet.NMAL6420.NMAL6420_ABMsg" %>
<%@ page import="business.servlet.NMAL6420.common.NMAL6420CommonLogic" %>
<%  NMAL6420BMsg bMsg = (NMAL6420BMsg)databean.getEZDBMsg(); %>
								<table width="1120">
									<col valign="top">
									<tr>
										<td height="20"></td>
										<td width="950" align="right">
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="A" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
											</jsp:include>
										</td>
 									</tr>
									<tr>
										<td width="62"></td>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width='100%' align="center">
												<tr>
													<td>&nbsp;</td>
													<td valign="top">
														<div id="topTBL" style="overflow-x:hidden; width:983; overflow-y:none; height:19;">
															<table border="1" cellpadding="1" cellspacing="0" align="center">
																<col align="center" width="30">
																<col align="left" width="35">
																<col align="center" width="156">
																<col align="center" width="220">
																<col align="center" width="220">
																<col align="center" width="52">
																<col align="center" width="156">
																
																<tr>
																	<td class="pClothBs">&nbsp;</td>
																	<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'locRoleTpSortNum_A1' )">Sort<br>Num<img id="sortIMG.locRoleTpSortNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'locRoleTpCd_A1' )">Code<img id="sortIMG.locRoleTpCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs">Name</td>
																	<td class="pClothBs">Description Text</td>
																	<td class="pClothBs">Account Code Flag</td>
																	<td class="pClothBs">Location Group Flag</td>
																</tr>
															</table>
														</div>
														<%-- ### TOP TABLE E ### --%>
						
														<%-- ### BOTTOM TABLE S ### --%>
														<div id="bottomTBL" style="overflow-x:none; overflow-y:scroll; width:958; height:450;">
															<table border="1" cellpadding="1" cellspacing="0" id="A" align="right">
																<col align="center" width="30">
																<col align="left" width="35">
																<col align="center" width="156">
																<col align="center" width="220">
																<col align="center" width="220">
																<col align="center" width="52">
																<col align="center" width="156">
																<% int i = 0; %>
																<ezf:row ezfHyo="A">
																<% NMAL6420_ABMsg detailMsg = bMsg.A.no(i++); %>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td>
																		<% boolean disabledxxChkBox = detailMsg.xxChkBox_A1.isInputProtected(); %>
																		<% if(disabledxxChkBox) out.println("<span style='visibility:hidden'>"); %>
																		<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" />
																		<% if(disabledxxChkBox) out.println("</span>"); %>
																	</td>
																	<td><ezf:inputText name="locRoleTpSortNum_A1" ezfName="locRoleTpSortNum_A1" value="XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right\" ezftoupper=\"\""/></td>
																	<td><ezf:inputText name="locRoleTpCd_A1" ezfName="locRoleTpCd_A1" value="---------1---------2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
																	<td><ezf:inputText name="locRoleTpNm_A1" ezfName="locRoleTpNm_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"35\" ezftoupper=\"\""/></td>
																	<td><ezf:inputText name="locRoleTpDescTxt_A1" ezfName="locRoleTpDescTxt_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
																	<td>
																		<ezf:select name="acctCdFlg_A1" ezfName="acctCdFlg_A1" ezfHyo="A" ezfArrayNo="0" >
																			<ezf:option value="Y" >Y</ezf:option>
																			<ezf:option value="N" >N</ezf:option>
																		</ezf:select>
																	</td>
																	<td>
																		<ezf:select name="locGrpCd_A1" ezfName="locGrpCd_A1" ezfHyo="A" ezfArrayNo="0" ezfCodeName="locGrpCd_PL" ezfDispName="locGrpNm_PL" otherAttr=" style=\"width:142\""/>
																	</td>
																</tr>
																</ezf:row>
																<ezf:skip>
																<tr height="28">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A1" ezfname="prcCatgSortNum_A1" class="pHsu" size="3" maxlength="3" style="text-align:right" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1"  size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y">Y</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																	<td>
																		<select style="width:142" class="pHsu" name="actvFlg_A1" ezfname="actvFlg_A1" ezfhyo="A">
																			<option value="Y">---------1---------2</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A" ezfname="prcCatgSortNum_A" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1" size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y">Y</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																	<td>
																		<select style="width:142" class="pHsu" name="actvFlg_A1" ezfname="actvFlg_A1" ezfhyo="A">
																			<option value="Y">---------1---------2</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																</tr>
																<tr height="28">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A1" ezfname="prcCatgSortNum_A1" class="pHsu" size="3" maxlength="3" style="text-align:right" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1"  size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y">Y</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																	<td>
																		<select style="width:142" class="pHsu" name="actvFlg_A1" ezfname="actvFlg_A1" ezfhyo="A">
																			<option value="Y">---------1---------2</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A" ezfname="prcCatgSortNum_A" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1" size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y">Y</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																	<td>
																		<select style="width:142" class="pHsu" name="actvFlg_A1" ezfname="actvFlg_A1" ezfhyo="A">
																			<option value="Y">---------1---------2</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																</tr>
																<tr height="28">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A1" ezfname="prcCatgSortNum_A1" class="pHsu" size="3" maxlength="3" style="text-align:right" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1"  size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y">Y</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																	<td>
																		<select style="width:142" class="pHsu" name="actvFlg_A1" ezfname="actvFlg_A1" ezfhyo="A">
																			<option value="Y">---------1---------2</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A" ezfname="prcCatgSortNum_A" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1" size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y">Y</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																	<td>
																		<select style="width:142" class="pHsu" name="actvFlg_A1" ezfname="actvFlg_A1" ezfhyo="A">
																			<option value="Y">---------1---------2</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																</tr>
																<tr height="28">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A1" ezfname="prcCatgSortNum_A1" class="pHsu" size="3" maxlength="3" style="text-align:right" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1"  size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y">Y</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																	<td>
																		<select style="width:142" class="pHsu" name="actvFlg_A1" ezfname="actvFlg_A1" ezfhyo="A">
																			<option value="Y">---------1---------2</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A" ezfname="prcCatgSortNum_A" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1" size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y">Y</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																	<td>
																		<select style="width:142" class="pHsu" name="actvFlg_A1" ezfname="actvFlg_A1" ezfhyo="A">
																			<option value="Y">---------1---------2</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																</tr>
																<tr height="28">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A1" ezfname="prcCatgSortNum_A1" class="pHsu" size="3" maxlength="3" style="text-align:right" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1"  size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y">Y</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																	<td>
																		<select style="width:142" class="pHsu" name="actvFlg_A1" ezfname="actvFlg_A1" ezfhyo="A">
																			<option value="Y">---------1---------2</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A" ezfname="prcCatgSortNum_A" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1" size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y">Y</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																	<td>
																		<select style="width:142" class="pHsu" name="actvFlg_A1" ezfname="actvFlg_A1" ezfhyo="A">
																			<option value="Y">---------1---------2</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																</tr>
																<tr height="28">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A1" ezfname="prcCatgSortNum_A1" class="pHsu" size="3" maxlength="3" style="text-align:right" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1"  size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y">Y</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																	<td>
																		<select style="width:142" class="pHsu" name="actvFlg_A1" ezfname="actvFlg_A1" ezfhyo="A">
																			<option value="Y">---------1---------2</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A" ezfname="prcCatgSortNum_A" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1" size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y">Y</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																	<td>
																		<select style="width:142" class="pHsu" name="actvFlg_A1" ezfname="actvFlg_A1" ezfhyo="A">
																			<option value="Y">---------1---------2</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																</tr>
																<tr height="28">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A1" ezfname="prcCatgSortNum_A1" class="pHsu" size="3" maxlength="3" style="text-align:right" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1"  size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y">Y</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																	<td>
																		<select style="width:142" class="pHsu" name="actvFlg_A1" ezfname="actvFlg_A1" ezfhyo="A">
																			<option value="Y">---------1---------2</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A" ezfname="prcCatgSortNum_A" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1" size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y">Y</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																	<td>
																		<select style="width:142" class="pHsu" name="actvFlg_A1" ezfname="actvFlg_A1" ezfhyo="A">
																			<option value="Y">---------1---------2</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																</tr>
																<tr height="28">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A1" ezfname="prcCatgSortNum_A1" class="pHsu" size="3" maxlength="3" style="text-align:right" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1"  size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y">Y</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																	<td>
																		<select style="width:142" class="pHsu" name="actvFlg_A1" ezfname="actvFlg_A1" ezfhyo="A">
																			<option value="Y">---------1---------2</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A" ezfname="prcCatgSortNum_A" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1" size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y">Y</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																	<td>
																		<select style="width:142" class="pHsu" name="actvFlg_A1" ezfname="actvFlg_A1" ezfhyo="A">
																			<option value="Y">---------1---------2</option>
																			<option value="N">N</option>
																		</select>
																	</td>
																</tr>
																</ezf:skip>
															</table>
														</div>
													</td>
												</tr>
											</table>
										</td>
										<td width="62"></td>
									</tr>
									<tr>
										<td width="62"></td>
										<td width="950" align="right">
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="81">
												<col width="10">
												<col width="81">
												<tr>
													<td align="left"><ezf:inputButton name="Insert_Row" value="Insert Row" htmlClass="pBtn7" /></td>
													<td align="left"><ezf:inputButton name="Delete_Row" value="Delete Row" htmlClass="pBtn7" /></td>
												</tr>
											</table>
										</td>
										<td width="62"></td>
									</tr>
								</table>
							</td>
						</tr>

<%-- ######################################## FOOTER ######################################## --%>

					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<%-- ###SCRIPT --%>
<script type="text/javascript">
	function synchroScroll_fromRightTblAction() {
		var rightTopTBL = this.document.getElementById( 'rightTopTBL' );
		var rightTBL    = this.document.getElementById( 'rightTBL'     );
		var leftTBL     = this.document.getElementById( 'leftTBL' );

		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
		// synchronize scroll - X
		rightTopTBL.scrollLeft = rightTBL.scrollLeft;
	}

	function synchroScroll_fromLeftTblAction() {
		var leftTBL  = this.document.getElementById( 'leftTBL'  );
		var rightTBL = this.document.getElementById( 'rightTBL' );

		// synchronize scroll - Y
		rightTBL.scrollTop = leftTBL.scrollTop;
	}
</script>
<%-- **** Task specific area ends here **** --%>
