<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170530150709 --%>
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
			<input type="hidden" name="pageID" value="NSAL1300Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Usage Meter Detail">
<style type="text/css">
</style>
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Chg Read" class="pTab_ON"><a href="#">Chg Read</a></li>
									</div>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>
				<div class="pTab_BODY">
					<table border="0" width="100%">
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0"  width="766px">
									<col width="5">
									<col width="100%" align="left">
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Usage Meter Detail</td>
										<td><ezf:inputHidden name="svcContrBllgPk" ezfName="svcContrBllgPk" value="1" /></td>
									</tr>
<!-- ######################################## DETAIL A #################################### -->
									<tr>
										<td>&nbsp;</td>
										<td>
											<div id='detailATblHead' style="width:742px; display:block; overflow:hidden; margin:0px; padding:0px;">
												<table style="table-layout:fixed; margin-right:20px;" border="1" cellpadding="1" cellspacing="0" width="740px" id="AHEAD">
													<col width="120px" align="center"><!-- Counter Type -->
													<col width="110px" align="center"><!-- Net Allowance -->
													<col width="110px" align="center"><!-- Net Usage -->
													<col width="110px" align="center"><!-- Billable Usage -->
													<col width="70px" align="center"><!-- Rate($) -->
													<col width="110px" align="center"><!-- Amount($) -->
													<col width="110px" align="center"><!--Total Amount($) -->
													<tr height="25">
														<td class="pClothBs">Counter Type</td>
														<td class="pClothBs">Net Allowance</td>
														<td class="pClothBs">Net Usage</td>
														<td class="pClothBs">Billable Usage</td>
														<td class="pClothBs">Rate($)</td>
														<td class="pClothBs">Amount($)</td>
														<td class="pClothBs">Total Amount($)</td>
													</tr>
												</table>
											</div>
											<div id="detailATbl" style="width:759px; height:102px; display:block; overflow-x:hidden; overflow-y:scroll; margin:0px; padding: 0px;">
												<table style="table-layout:fixed;" width="742px" border="1" cellpadding="1" cellspacing="0" id="A">
													<col width="120px" align="center"><!-- Counter Type -->
													<col width="110px" align="right"><!-- Net Allowance -->
													<col width="110px" align="right"><!-- Net Usage -->
													<col width="110px" align="right"><!-- Billable Usage -->
													<col width="70px" align="right"><!-- Rate($) -->
													<col width="110px" align="right"><!-- Amount($) -->
													<col width="110px" align="right"><!--Total Amount($) -->
													<ezf:row ezfHyo="A">
														<tr height="25">
															<td><ezf:inputText name="mtrLbDescTxt_A" ezfName="mtrLbDescTxt_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\""/></td>
															<td><ezf:label name="copyInclQty_A" ezfName="copyInclQty_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="mtrCopyQty_A" ezfName="mtrCopyQty_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="bllgCopyQty_A" ezfName="bllgCopyQty_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="xsMtrAmtRate_A" ezfName="xsMtrAmtRate_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="mtrChrgDealAmt_A" ezfName="mtrChrgDealAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="xxTotAmt_A" ezfName="xxTotAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
															</td>
														</tr>
													</ezf:row>
													<ezf:skip>
														<tr height="25">
															<td><input type="text" class="pPro" size="15" maxlength="50" value="Fleet Black and White (Hard)" name="mtrLbCd_A" ezfname="mtrLbCd_A" ezfHyo="A"></td>
															<td><label ezfout ezfHyo="A" name="copyInclQty_A" ezfname="copyInclQty_A">1,850</label></td>
															<td><label ezfout ezfHyo="A" name="mtrCopyQty_A" ezfname="mtrCopyQty_A">18,501,850</label></td>
															<td><label ezfout ezfHyo="A" name="bllgCopyQty_A" ezfname="bllgCopyQty_A">18,500,000</label></td>
															<td><label ezfout ezfHyo="A" name="xsMtrAmtRate_A" ezfname="xsMtrAmtRate_A">0.1234</label></td>
															<td><label ezfout ezfHyo="A" name="mtrChrgDealAmt_A" ezfname="mtrChrgDealAmt_A">18,500,000</label></td>
															<td><label ezfout ezfHyo="A" name="xxTotAmt_A" ezfname="xxTotAmt_A">18,500,000</label></td>
														</tr>
													</ezf:skip>
												</table>
											</div><!-- rightTbl -->
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr></tr>
						<tr></tr>
						<tr>
<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
							<td>
							<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; ">
								<col width="5">
								<col width="553" align="left">
								<col width="550" align="right">
								<tr>
									<td>&nbsp;</td>
									<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
									<td align="right" class="stab">
										<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
											<jsp:param name="beanId"           value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"          value="B" />
											<jsp:param name="ShowingFrom"      value="xxPageShowFromNum" />
											<jsp:param name="ShowingTo"        value="xxPageShowToNum" />
											<jsp:param name="ShowingOf"        value="xxPageShowOfNum" />
											<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum" />
											<jsp:param name="ShowingTotal"     value="xxPageShowTotNum" />
											<jsp:param name="ViewMode"         value="FULL" />
										</jsp:include>
										<ezf:skip>
											<table border="0" cellpadding="0" width="100%">
												<tr>
													<td align="left">	
														<table border="0" cellpadding="0" align="left" cellspacing="0">
															<col>
															<tr>
																<td>Results 950 - 1000 of 1000</td>
															</tr>
														</table>
													</td>
													<td align="right">
														<table border="0" cellpadding="0" cellspacing="0">
															<col width="54"  align="center">
															<col width="40"  align="center">
															<col width="16"  align="center">
															<col width="40"  align="center">
															<col width="26"  align="center">
															<col width="10">
															<col>
															<col width="20">
															<col>
															<col width="1">
															<col>
															<tr>
																<td class="stab">Showing</td>
																<td>20</td>
																<td class="stab">/</td>
																<td>20</td>
																<td class="stab">page</td>
																<td>&nbsp;</td>
																<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'B')" disabled></td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'B')" disabled></td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'B')" disabled></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</ezf:skip>
									</td>
								</tr>
							</table>
							</td>
<!-- ######################################## TO (COMMON)PAGENATION #################################### -->
						</tr>
<!-- ######################################## DETAIL B #################################### -->
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td>
										<div id="parentBoxB">
											<div style="float:left; display:block"><!-- left table -->
												<div id='leftTblHead' style="display:block;"></div>
												<div id='leftTbl' style="float:left; display:block; height:312px; overflow:hidden; margin:0px; padding:0px; padding-bottom:20px"></div>
											</div><!-- left table -->
											<div style="float:left;"><!-- right table -->
												<div id='rightTblHead' style="width:1105px; display:block; overflow:hidden; margin:0px; padding:0px;">
													<table style="table-layout:fixed; margin-right:20px;" border="1" cellpadding="1" cellspacing="0" id="BHEAD" width="1402px">
														<col width="120px" align="center"><!-- Contract# -->
														<col width="120px" align="center"><!-- Seail# -->
														<col width="120px" align="center"><!-- Meter Type -->
														<col width="80px" align="center"><!-- Start Meter Date -->
														<col width="80px" align="center"><!-- End Meter Date -->
														<col width="120px" align="center"><!-- Counter Name -->
														<col width="100px" align="center"><!-- Start Read -->
														<col width="100px" align="center"><!-- End Read -->
														<col width="120px" align="center"><!-- Created By -->
														<col width="80px" align="center"><!-- Total ADCV Ratio(%) -->
														<col width="120px" align="center"><!-- Read Comment -->
														<col width="120px" align="center"><!-- New End Read -->
														<col width="120px" align="center"><!-- Comment -->
														<tr height="35">
															<td id="AH1" class="pClothBs">Contract#</td>
															<td id="AH2" class="pClothBs">Seail#</td>
															<td id="AH3" class="pClothBs">Meter Type</td>
															<td id="AH4" class="pClothBs">Start Meter Date</td>
															<td id="AH5" class="pClothBs">End Meter Date</td>
															<td id="AH6" class="pClothBs">Counter Name</td>
															<td id="AH7" class="pClothBs">Start Read</td>
															<td id="AH8" class="pClothBs">End Read</td>
															<td id="AH9" class="pClothBs">Created By</td>
															<td id="AH10" class="pClothBs">Total ADCV Ratio(%)</td>
															<td id="AH11" class="pClothBs">Read Comment</td>
															<td id="AH12" class="pClothBs">New End Read</td>
															<td id="AH13" class="pClothBs">Comment</td>
														</tr>
													</table>
												</div>
												<div id="rightTbl" style="width:1122px; height:329px; display:block; overflow:scroll; margin:0px; padding: 0px;">
													<table style="table-layout:fixed;" width="1322px" border="1" cellpadding="1" cellspacing="0" id="B">
														<col width="120px" align="center"><!-- Contract# -->
														<col width="120px" align="center"><!-- Seail# -->
														<col width="120px" align="center"><!-- Meter Type -->
														<col width="80px" align="center"><!-- Start Meter Date -->
														<col width="80px" align="center"><!-- End Meter Date -->
														<col width="120px" align="center"><!-- Counter Name -->
														<col width="100px" align="right"><!-- Start Read -->
														<col width="100px" align="right"><!-- End Read -->
														<col width="120px" align="center"><!-- Created By -->
														<col width="80px" align="right"><!-- Total ADCV Ratio(%) -->
														<col width="120px" align="center"><!-- Read Comment -->
														<col width="120px" align="center"><!-- New End Read -->
														<col width="120px" align="center"><!-- Comment -->
														<ezf:row ezfHyo="B">
															<tr height="25" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
																<td><ezf:inputText name="dsContrNum_B" ezfName="dsContrNum_B" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
																<td><ezf:inputText name="serNum_B" ezfName="serNum_B" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
																<td><ezf:inputText name="dsMtrReadTpDescTxt_B" ezfName="dsMtrReadTpDescTxt_B" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\""/></td>
																<td><ezf:label name="mtrReadDt_BL" ezfName="mtrReadDt_BL" ezfHyo="B" ezfArrayNo="0" /></td>
																<td><ezf:label name="mtrReadDt_B" ezfName="mtrReadDt_B" ezfHyo="B" ezfArrayNo="0" /></td>
																<td><ezf:inputText name="mtrLbDescTxt_B" ezfName="mtrLbDescTxt_B" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\""/></td>
																<td><ezf:label name="readMtrCnt_BL" ezfName="readMtrCnt_BL" ezfHyo="B" ezfArrayNo="0" /></td>
																<td><ezf:label name="readMtrCnt_B" ezfName="readMtrCnt_B" ezfHyo="B" ezfArrayNo="0" /></td>
																<td><ezf:inputText name="mtrReadSrcTpDescTxt_B" ezfName="mtrReadSrcTpDescTxt_B" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
																<td><ezf:label name="avgMtrReadCnt_B" ezfName="avgMtrReadCnt_B" ezfHyo="B" ezfArrayNo="0" /></td>
																<td><ezf:inputText name="mtrEntryCmntTxt_B" ezfName="mtrEntryCmntTxt_B" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXX4XXXXXXXXX5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"400\""/></td>
																<td><ezf:inputText name="readMtrCnt_BN" ezfName="readMtrCnt_BN" value="9,999,999,999" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"10\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="mtrEntryCmntTxt_BN" ezfName="mtrEntryCmntTxt_BN" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"400\""/></td>
																<td class="pAuditInfo">
																	<ezf:inputHidden name="xxRecHistCratTs_B" ezfName="xxRecHistCratTs_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																	<ezf:inputHidden name="xxRecHistCratByNm_B" ezfName="xxRecHistCratByNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																	<ezf:inputHidden name="xxRecHistUpdTs_B" ezfName="xxRecHistUpdTs_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																	<ezf:inputHidden name="xxRecHistUpdByNm_B" ezfName="xxRecHistUpdByNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																	<ezf:inputHidden name="xxRecHistTblNm_B" ezfName="xxRecHistTblNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																</td>
															</tr>
														</ezf:row>
														<ezf:skip>
															<tr height="25">
																<td><input type="text" class="pPro" size="15" maxlength="50" value="12345" name="dsContrNum_B" ezfname="dsContrNum_B" ezfHyo="B"></td>
																<td><input type="text" class="pPro" size="15" maxlength="50" value="CZ123456" name="serNum_B" ezfname="serNum_B" ezfHyo="B"></td>
																<td><input type="text" class="pPro" size="15" maxlength="50" value="FLT BW Cop" name="dsMtrReadTpDescTxt_B" ezfname="dsMtrReadTpDescTxt_B" ezfHyo="B"></td>
																<td><label ezfout ezfHyo="B" name="mtrReadDt_BL" ezfname="mtrReadDt_BL">02/01/2016</label></td>
																<td><label ezfout ezfHyo="B" name="mtrReadDt_B" ezfname="mtrReadDt_B">02/28/2016</label></td>
																<td><input type="text" class="pPro" size="15" maxlength="50" value="0000308" name="mtrLbDescTxt_B" ezfname="mtrLbDescTxt_B" ezfHyo="B"></td>
																<td><label ezfout ezfHyo="B" name="readMtrCnt_BL" ezfname="readMtrCnt_BL">10,000</label></td>
																<td><label ezfout ezfHyo="B" name="readMtrCnt_B" ezfname="readMtrCnt_B">12,000</label></td>
																<td><input type="text" class="pPro" size="15" maxlength="50" value="XXXXXXXXX1" name="mtrReadSrcTpDescTxt_B" ezfname="mtrReadSrcTpDescTxt_B" ezfHyo="B"></td>
																<td><label ezfout ezfHyo="B" name="avgMtrReadCnt_B" ezfname="avgMtrReadCnt_B">90.00</label></td>
																<td><input type="text" class="pPro" size="15" maxlength="50" value="Comment" name="mtrEntryCmntTxt_B" ezfname="mtrEntryCmntTxt_B" ezfHyo="B"></td>
																<td><input type="text" size="15" maxlength="24" value="15,000" name="readMtrCnt_BN" ezftoupper ezfname="readMtrCnt_BN" ezfHyo="B"></td>
																<td><input type="text" size="15" maxlength="1000" value="new commnent" name="mtrEntryCmntTxt_BN" ezfname="mtrEntryCmntTxt_BN" ezfHyo="B"></td>
															</tr>
														</ezf:skip>
													</table>
												</div><!-- rightTbl -->
											</div><!-- right table -->
										</div><!-- parent box -->
									</tr>
								</table>
							</td>
						</tr>
<!-- ######################################## Filter #################################### -->
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
									<col width="5px">
									<col width="85px">
									<col width="115px">
									<col width="5px">
									<col width="85px">
									<col width="115px">
									<col width="5px">
									<col width="55px">
									<col width="115px">
									<col width="5px">
									<col width="80px">
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Counter Type(*)</td>
										<td><ezf:inputText name="mtrLbDescTxt_FT" ezfName="mtrLbDescTxt_FT" otherAttr=" size=\"15\" maxlength=\"50\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Counter Name(*)</td>
										<td><ezf:inputText name="mtrLbDescTxt_FN" ezfName="mtrLbDescTxt_FN" otherAttr=" size=\"15\" maxlength=\"50\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Serial#(*)</td>
										<td><ezf:inputText name="serNum_F" ezfName="serNum_F" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="Filter" value="Filter" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
	<script type="text/javascript" defer>
		S21TableUI.initialize("parentBoxB", "BHEAD", "B");
	</script>

<%-- **** Task specific area ends here **** --%>
