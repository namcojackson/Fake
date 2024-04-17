<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160602180616 --%>
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
			<input type="hidden" name="pageID" value="NMAL2810Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Merge Fields Pop Up">


			<center>
				<table width="1133" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<div class="pTab_BODY_In">
								<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:50px;margin-left:3px;margin-bottom:-5px;">
									<tr>
										<td>&nbsp;</td>
										<td align="reft">
											Select the values that you want to retain in the merged record, Highlighted rows indicates fields that contain conflicting data
										</td>
									</tr>
								</table>
								<table>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td align="top" width="1130">
										
											<%-- ### Header ### --%>
											<div id="TBL_Top">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col width="180">
													<col width="20"><!-- PROSPECT(radio) -->
													<col width="370"><!-- PROSPECT(label) -->
													<col width="20"><!-- MERGE TO(radio) -->
													<col width="370"><!-- MERGE TO(label) -->
													
													<tr height="36">
														<td class="pClothBs">&nbsp;</td>
														<td class="pClothBs" colspan="2">
															PROSPECT<br>
															<ezf:inputText name="rvwProsNum_L" ezfName="rvwProsNum_L" value="123456789012345678901234567890" otherAttr=" size=\"30\" style=\"border:none;background-color:transparent;padding:0px;color:white\" tabindex=\"-1\""/>
														</td>
														<td class="pClothBs" colspan="2">
															MERGE TO<br>
															<ezf:inputText name="locNum_R" ezfName="locNum_R" value="123456789012345678901234567890" otherAttr=" size=\"30\" style=\"border:none;background-color:transparent;padding:0px;color:white\" tabindex=\"-1\""/>
														</td>
													</tr>
												</table>
											</div>

											<%-- ### Detail ### --%>
											<div id="TBL">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;" id="table_A">
													<col width="180">
													<col width="20"><!-- PROSPECT(radio) -->
													<col width="370"><!-- PROSPECT(label) -->
													<col width="20"><!-- MERGE TO(radio) -->
													<col width="370"><!-- MERGE TO(label) -->
														<tr height="20">
															<td>Address 1</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_1" ezfName="xxRadioBtn_1" value="0" />
															</td>
															<td>
																<ezf:inputText name="firstLineAddr_L" ezfName="firstLineAddr_L" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_1" ezfName="xxRadioBtn_1" value="1" />
															</td>
															<td>
																<ezf:inputText name="firstLineAddr_R" ezfName="firstLineAddr_R" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td>Address 2</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_2" ezfName="xxRadioBtn_2" value="0" />
															</td>
															<td>
																<ezf:inputText name="scdLineAddr_L" ezfName="scdLineAddr_L" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_2" ezfName="xxRadioBtn_2" value="1" />
															</td>
															<td>
																<ezf:inputText name="scdLineAddr_R" ezfName="scdLineAddr_R" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td>Address 3</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_3" ezfName="xxRadioBtn_3" value="0" />
															</td>
															<td>
																<ezf:inputText name="thirdLineAddr_L" ezfName="thirdLineAddr_L" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_3" ezfName="xxRadioBtn_3" value="1" />
															</td>
															<td>
																<ezf:inputText name="thirdLineAddr_R" ezfName="thirdLineAddr_R" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td>Address 4</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_4" ezfName="xxRadioBtn_4" value="0" />
															</td>
															<td>
																<ezf:inputText name="frthLineAddr_L" ezfName="frthLineAddr_L" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_4" ezfName="xxRadioBtn_4" value="1" />
															</td>
															<td>
																<ezf:inputText name="frthLineAddr_R" ezfName="frthLineAddr_R" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td>City</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_5" ezfName="xxRadioBtn_5" value="0" />
															</td>
															<td>
																<ezf:inputText name="ctyAddr_L" ezfName="ctyAddr_L" value="1234567890123456789012345" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_5" ezfName="xxRadioBtn_5" value="1" />
															</td>
															<td>
																<ezf:inputText name="ctyAddr_R" ezfName="ctyAddr_R" value="1234567890123456789012345" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td>State</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_6" ezfName="xxRadioBtn_6" value="0" />
															</td>
															<td>
																<ezf:inputText name="stCd_L" ezfName="stCd_L" value="12" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_6" ezfName="xxRadioBtn_6" value="1" />
															</td>
															<td>
																<ezf:inputText name="stCd_R" ezfName="stCd_R" value="12" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td>Postal Code</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_7" ezfName="xxRadioBtn_7" value="0" />
															</td>
															<td>
																<ezf:inputText name="postCd_L" ezfName="postCd_L" value="123456789012345" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_7" ezfName="xxRadioBtn_7" value="1" />
															</td>
															<td>
																<ezf:inputText name="postCd_R" ezfName="postCd_R" value="123456789012345" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td>Phone</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_8" ezfName="xxRadioBtn_8" value="0" />
															</td>
															<td>
																<ezf:inputText name="telNum_L" ezfName="telNum_L" value="12345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_8" ezfName="xxRadioBtn_8" value="1" />
															</td>
															<td>
																<ezf:inputText name="telNum_R" ezfName="telNum_R" value="12345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td>DNB Name</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_9" ezfName="xxRadioBtn_9" value="0" />
															</td>
															<td>
																<ezf:inputText name="dsAcctDunsNm_L" ezfName="dsAcctDunsNm_L" value="12345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_9" ezfName="xxRadioBtn_9" value="1" />
															</td>
															<td>
																<ezf:inputText name="dsAcctDunsNm_R" ezfName="dsAcctDunsNm_R" value="12345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td>DUNS Number</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_10" ezfName="xxRadioBtn_10" value="0" />
															</td>
															<td>
																<ezf:inputText name="dunsNum_L" ezfName="dunsNum_L" value="12345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_10" ezfName="xxRadioBtn_10" value="1" />
															</td>
															<td>
																<ezf:inputText name="dunsNum_R" ezfName="dunsNum_R" value="12345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td>Ultimate DUNS Number</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_11" ezfName="xxRadioBtn_11" value="0" />
															</td>
															<td>
																<ezf:inputText name="dsUltDunsNum_L" ezfName="dsUltDunsNum_L" value="12345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_11" ezfName="xxRadioBtn_11" value="1" />
															</td>
															<td>
																<ezf:inputText name="dsUltDunsNum_R" ezfName="dsUltDunsNum_R" value="12345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td>SIC Code</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_12" ezfName="xxRadioBtn_12" value="0" />
															</td>
															<td>
																<ezf:inputText name="dsCustSicCd_L" ezfName="dsCustSicCd_L" value="123456789012345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_12" ezfName="xxRadioBtn_12" value="1" />
															</td>
															<td>
																<ezf:inputText name="dsCustSicCd_R" ezfName="dsCustSicCd_R" value="123456789012345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td>Line Of Business</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_13" ezfName="xxRadioBtn_13" value="0" />
															</td>
															<td>
																<ezf:inputText name="dsCustSicDescTxt_L" ezfName="dsCustSicDescTxt_L" value="1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_13" ezfName="xxRadioBtn_13" value="1" />
															</td>
															<td>
																<ezf:inputText name="dsCustSicDescTxt_R" ezfName="dsCustSicDescTxt_R" value="1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td>Annual Revenue</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_14" ezfName="xxRadioBtn_14" value="0" />
															</td>
															<td>
																<ezf:inputText name="dsLocRevAmt_L" ezfName="dsLocRevAmt_L" value="12345678901234.1234" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_14" ezfName="xxRadioBtn_14" value="1" />
															</td>
															<td>
																<ezf:inputText name="dsLocRevAmt_R" ezfName="dsLocRevAmt_R" value="12345678901234.1234" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td># of Employees</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_15" ezfName="xxRadioBtn_15" value="0" />
															</td>
															<td>
																<ezf:inputText name="dsLocEmpNum_L" ezfName="dsLocEmpNum_L" value="12345678" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_15" ezfName="xxRadioBtn_15" value="1" />
															</td>
															<td>
																<ezf:inputText name="dsLocEmpNum_R" ezfName="dsLocEmpNum_R" value="12345678" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td>GLN</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_16" ezfName="xxRadioBtn_16" value="0" />
															</td>
															<td>
																<ezf:inputText name="glnNum_L" ezfName="glnNum_L" value="1234567890123" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_16" ezfName="xxRadioBtn_16" value="1" />
															</td>
															<td>
																<ezf:inputText name="glnNum_R" ezfName="glnNum_R" value="1234567890123" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td>Parent DUNS Number</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_17" ezfName="xxRadioBtn_17" value="0" />
															</td>
															<td>
																<ezf:inputText name="dsPrntDunsNum_L" ezfName="dsPrntDunsNum_L" value="12345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_17" ezfName="xxRadioBtn_17" value="1" />
															</td>
															<td>
																<ezf:inputText name="dsPrntDunsNum_R" ezfName="dsPrntDunsNum_R" value="12345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td>HQ DUNS Number</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_18" ezfName="xxRadioBtn_18" value="0" />
															</td>
															<td>
																<ezf:inputText name="hqDunsNum_L" ezfName="hqDunsNum_L" value="12345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_18" ezfName="xxRadioBtn_18" value="1" />
															</td>
															<td>
																<ezf:inputText name="hqDunsNum_R" ezfName="hqDunsNum_R" value="12345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td>Company SIC</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_19" ezfName="xxRadioBtn_19" value="0" />
															</td>
															<td>
																<ezf:inputText name="dsCustSicCd_LC" ezfName="dsCustSicCd_LC" value="123456789012345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_19" ezfName="xxRadioBtn_19" value="1" />
															</td>
															<td>
																<ezf:inputText name="dsCustSicCd_RC" ezfName="dsCustSicCd_RC" value="123456789012345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
														<tr height="20">
															<td>Company SIC Description</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_20" ezfName="xxRadioBtn_20" value="0" />
															</td>
															<td>
																<ezf:inputText name="dsCustSicDescTxt_LC" ezfName="dsCustSicDescTxt_LC" value="1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputRadio name="xxRadioBtn_20" ezfName="xxRadioBtn_20" value="1" />
															</td>
															<td>
																<ezf:inputText name="dsCustSicDescTxt_RC" ezfName="dsCustSicDescTxt_RC" value="1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"53\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
												</table>
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col width="180">
													<col width="20">
													<col width="370">
													<col width="20">
													<col width="370">
													<tr height="5">
													</tr>
													<tr>
														<td>&nbsp;</td>
														<td colspan="2" style="border-style:none;">
															<ezf:inputButton name="ProspectSelectAll" value="SELECT ALL" htmlClass="pBtn6" />
														</td>
														<td colspan="2" style="border-style:none;">
															<ezf:inputButton name="MergeToSelectAll" value="SELECT ALL" htmlClass="pBtn6" />
														</td>
													</tr>
													<tr height="10">
													</tr>
													<tr>
														<td colspan="3" style="border-style:none;">&nbsp;</td>
														<td colspan="2" style="border-style:none;">
															<ezf:inputButton name="AddressValidation" value="Address Validataion " htmlClass="pBtn10" />
														</td>
													</tr>
													<tr height="10">
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

<STYLE type="text/css"> 
<!-- 
tr.pHightlightBGcolor	{background-color:#FFFF00;}
tr.pErrorBGcolor		{background-color:#FF0000;}
--> 
</STYLE> 


<%-- **** Task specific area ends here **** --%>
