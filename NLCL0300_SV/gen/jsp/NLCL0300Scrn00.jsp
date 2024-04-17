<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160826063754 --%>
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
			<input type="hidden" name="pageID" value="NLCL0300Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Configuration Change Entry">
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
			
<%-- ######################################## HEADER ######################################## --%>
				<%-- ###TAB - HEAD --%>
                <!-- include S21BusinessProcessTAB -->
                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                    
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
							<tr>
								<td width="96%">
									<li title = "Configuration Change(Entry)" class="pTab_ON" ><a href="#">Conf Chng</a></li>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
						<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
							<col width=""  align="left">
							<tr valign="top">
								<td>
									<table border="0" cellpadding="1" cellspacing="1" >
										<col width="80">
										<col width="280">
										<!-- 1 -->
										<tr height="23">
											<td class="stab"><ezf:anchor name="rtlWhCd_HL" ezfName="rtlWhCd_HL" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" >Warehouse</ezf:anchor></td>
											<td class="stab">
                        <ezf:inputText name="rtlWhCd_H" ezfName="rtlWhCd_H" value="A01" otherAttr=" size=\"6\" maxlength=\"20\" ezftoupper=\"\""/>
 										    <ezf:inputText name="rtlWhNm_H" ezfName="rtlWhNm_H" value="MONROE" otherAttr=" size=\"30\" maxlength=\"60\" tabindex=\"-1\""/>
											</td>
										</tr>
										<tr height="23">
											<td>&nbsp;</td>
										</tr>
									</table>
								</td>
								<td width='10'></td>
								<td>
									<table border="0" cellpadding="1" cellspacing="1" style="border-left:black 1px solid">
										<col width="5">
										<col width="70">
										<col width="140">
										<col width="65">
										<tr height="23">
											<td>&nbsp;</td>
											<td class="stab"><ezf:anchor name="svcConfigMstrPk_HL" ezfName="svcConfigMstrPk_HL" ezfEmulateBtn="1" ezfGuard="OpenWin_Config" >Config ID</ezf:anchor></td>
	     								<td><ezf:inputText name="svcConfigMstrPk_H" ezfName="svcConfigMstrPk_H" value="0000000001" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
	                    <td><ezf:inputButton name="Add_ExistingConfig" value="Add Existing Config" htmlClass="pBtn13" /></td>
	                    <td></td>
										</tr>
										<tr height="23">
											<td>&nbsp;</td>
											<td class="stab"><ezf:anchor name="mdlDescTxt_HL" ezfName="mdlDescTxt_HL" ezfEmulateBtn="1" ezfGuard="OpenWin_Model" >Model ID</ezf:anchor></td>
	     								<td><ezf:inputText name="mdlDescTxt_H" ezfName="mdlDescTxt_H" otherAttr=" size=\"16\" maxlength=\"90\" ezftoupper=\"\""/></td>
	     								<td><ezf:inputButton name="Add_NewConfigFromModel" value="Add New Config From Mdl" htmlClass="pBtn13" /></td>
										</tr>
										<tr height="23">
											<td>&nbsp;</td>
										</tr>
									</table>
								</td>
								<td width='10'></td>
								<td>
									<table border="0" cellpadding="1" cellspacing="1" style="border-left:black 1px solid">
										<col width="5">
										<col width="120">
										<col width="240" align="left">
										<tr height="23">
											<td>&nbsp;</td>
											<td class="stab">Document Number</td>
											<td align="left">
												<ezf:inputText name="invtyOrdNum_H" ezfName="invtyOrdNum_H" value="IM037610" otherAttr=" size=\"20\" maxlength=\"45\" ezftoupper=\"\""/>
												<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
											</td>
										</tr>
										<tr height="23">
											<td>&nbsp;</td>
											<td class="stab">Order Status</td>
											<td align="left">
												<ezf:inputText name="invtyOrdStsDescTxt_H" ezfName="invtyOrdStsDescTxt_H" otherAttr=" size=\"20\" maxlength=\"45\" ezftoupper=\"\""/>
											</td>
										</tr>
										<tr height="23">
											<td>&nbsp;</td>
											<td class="stab">SO Number</td>
											<td align="left">
												<ezf:inputText name="soNum_H" ezfName="soNum_H" value="SH000010" otherAttr=" size=\"20\" maxlength=\"45\" ezftoupper=\"\""/>
											<ezf:inputButton name="OpenWin_ManageShippingOrder" value="Manage SO" htmlClass="pBtn6" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
								<hr>
					<table width="100%">
						<col valign="top">
						<tr>
							<td>
								<table border="0">
									<col width="80">
									<col width="56">
									<col width="248">
									<col width="5">
									<col width="56">
									<col width="250">
									<col width="200">
									<col width="120">
									<col width="108">
									<col width="65">
									<tr>
										<td class="stab">Comment</td>
										<td colspan="6">
											<ezf:inputText name="firstInvtyOrdCmntTxt_H" ezfName="firstInvtyOrdCmntTxt_H" value="TEST DATA ONE" otherAttr=" size=\"64\" maxlength=\"64\""/>
										</td>
										<td class="stab"></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>

									<tr>
										<td class="stab"></td>
										<td colspan="6">
											<ezf:inputText name="scdInvtyOrdCmntTxt_H" ezfName="scdInvtyOrdCmntTxt_H" value="TEST DATA TWO" otherAttr=" size=\"64\" maxlength=\"64\""/>
										</td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td class="stab"></td>
										<td colspan="6">
											<ezf:inputText name="thirdInvtyOrdCmntTxt_H" ezfName="thirdInvtyOrdCmntTxt_H" value="TEST DATA THREE" otherAttr=" size=\"64\" maxlength=\"64\""/>
										</td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</table>
								<hr>
<%-- ######################################## DETAIL ######################################## --%>
								<table cellpadding="0" cellspacing="0">
									<col width="">
									<tr>
										<td>
											<table cellpadding="0" cellspacing="0" style="margin-bottom: 5px;" width="100%">
												<col width="30">
												<col width="220">
												<col width="130">
												<col width="5">
												<col width="60">
												<col width="144">
												<col width="5">
												<col width="100">
												<col width="150">
												<col width="44">
												<col width="17">
												<col width="">
												<tr>
                          <td>&nbsp;</td>
													<td>
														<table cellpadding="0" cellspacing="0">
															<col width="80">
															<col width="118">
															<col width="40">
															<tr align="left">
																<td class="stab"><ezf:anchor name="mdseCd_HL" ezfName="mdseCd_HL" ezfEmulateBtn="1" ezfGuard="OpenWin_Item" >Item Number</ezf:anchor></td>
																<td><ezf:inputText name="mdseCd_H" ezfName="mdseCd_H" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
																<td align="center"><ezf:inputButton name="Search_Item" value=">>" htmlClass="pBtn0" /></td>
															</tr>
														</table>
													</td>
													<td><ezf:inputText name="mdseDescShortTxt_H" ezfName="mdseDescShortTxt_H" otherAttr=" size=\"21\" maxlength=\"21\" tabindex=\"-1\""/></td>
													<td></td>
													<td align="center" class="stab">Serial#</td>
													<td><ezf:inputText name="serNum_H" ezfName="serNum_H" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
													<td align="center" class="stab"><ezf:inputButton name="Add_Line" value="Add" htmlClass="pBtn6" /></td>
													<td align="center" class="stab"></td>
													<td>
													</td>
													<td><ezf:inputButton name="RemoveAll" value="Remove All" htmlClass="pBtn9" /></td>
													<td></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<table cellpadding="1" cellspacing="0">
												<col width="">
												<tr>
													<td valign="top">
														<div style="overflow-x:hidden;overflow-y:none; table-layout:fixed;">
															<table border="1" cellpadding="1" cellspacing="0">
																<col width="28"  align="center">	<!-- check box -->
																<col width="48"  align="center">	<!-- Line -->
																<col width="108" align="center">	<!-- Config ID -->
																<col width="108" align="center">	<!-- Item Mumber -->
																<col width="196" align="center">	<!-- Item Description -->
																<col width="140" align="center">	<!-- Stock Status From -->
																<col width="70" align="center">	<!-- Stock Status To -->
																<col width="76"  align="center">	<!-- Quantity -->
																<col width="105" align="center">    <!-- Current Available -->
                                <col width="116" align="center">	<!-- Serial Number -->
																<col width="50"  align="center">	<!-- check box -->
																<tr height="28">
  																<td class="pClothBs"></td>
																	<td class="pClothBs">Line</td>
																	<td class="pClothBs">Config ID</td>
																	<td class="pClothBs">Item Number</td>
																	<td class="pClothBs">Item Description</td>
																	<td class="pClothBs">Stock Status</td>
																	<td class="pClothBs">Sub WH</td>
																	<td class="pClothBs">Quantity</td>
																	<td class="pClothBs">Current Available</td>
                                  <td class="pClothBs">Serial#</td>
																	<td class="pClothBs">Remove</td>
																</tr>
															</table>
														</div>
														<div style="overflow-y:scroll; height:300; table-layout:fixed;">
															<table border="1" cellpadding="1" cellspacing="0" id="A" border="1">
																<col width="28"  align="center">	<!-- check box -->
																<col width="48"  align="right">	    <!-- Line -->
																<col width="108" align="left">	<!-- Config ID -->
																<col width="108" align="left">	<!-- Item Mumber -->
																<col width="196" align="center">	<!-- Item Description -->
																<col width="140" align="center">	<!-- Stock Status From -->
																<col width="70" align="center">	<!-- Stock Status To -->
																<col width="76"  align="center">	<!-- Quantity -->
																<col width="105" align="right">	    <!-- Current Available -->
                                <col width="116" align="center">	<!-- Serial Number -->
																<col width="50"  align="center">	<!-- check box -->

                                                                <col width="116"  align="center">	<!-- Serial Number -->
																																	<ezf:row ezfHyo="A">
                                                                    <tr height="28">
                                                                        <td>
                                                                            <ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
                                                                        </td>
                                                                        <td><ezf:label name="invtyOrdLineNum_A" ezfName="invtyOrdLineNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                        <td><ezf:label name="svcConfigMstrPk_A" ezfName="svcConfigMstrPk_A" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                        <td><ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"30\" tabindex=\"-1\""/></td>
                                                                        <td><ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"21\" tabindex=\"-1\""/></td>
                                                                        <td>
                                                                            <ezf:select name="stkStsCd_A" ezfName="stkStsCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="stkStsCd_LC" ezfDispName="stkStsDescTxt_LD" otherAttr=" style=\"width:130;\""/>
                                                                        </td>
                                                                        <td>
                                                                            <ezf:select name="rtlSwhCd_A" ezfName="rtlSwhCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="rtlSwhCd_LC" ezfDispName="rtlSwhCd_LD" otherAttr=" style=\"width:65;\""/>
                                                                        </td>
                                                                        <td><ezf:inputText name="ordQty_A" ezfName="ordQty_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"8\" maxlength=\"13\""/></td>
                                                                        <td><ezf:label name="invtyAvalQty_A" ezfName="invtyAvalQty_A" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                        <td><ezf:inputText name="serNum_A" ezfName="serNum_A" value="SEREXAMPLE001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
                                                                        <td>
                                                                            <ezf:inputCheckBox name="rmvConfigFlg_A" ezfName="rmvConfigFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
                                                                        </td>

                                                                    </tr>
																</ezf:row>
															</table>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
<%-- ######################################## FOOTER ######################################## --%>
								<div align="left">
									<table border="0">
										<tr height="20">
											<td valign="bottom">
												<table border="0" cellpadding="1" cellspacing="0" width="100%">
													<col width="100">
													<tr>
														<td>
															<ezf:inputButton name="Delete_Line" value="Delete Row" htmlClass="pBtn7" />
														</td>
														<td>
															<ezf:inputButton name="Attachments" value="Attachments" htmlClass="pBtn7" />
														</td>
													</tr>
												</table>
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
