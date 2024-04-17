<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161206142409 --%>
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
			<input type="hidden" name="pageID" value="NSAL1030Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Contract Invoice Detail Search">
<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Actual Counters for Interface" class="pTab_ON"><a href="#">Inv Srch</a></li>
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
				<table border="0" width="99%" align="center">
					<tr height="35" valign="bottom">
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="20">
								<col width="110" align="left">
								<col width="100">
								<col width="20">
								<col width="60" align="left">
								<col width="90">
								<col width="20">
								<col width="70" align="left">
								<col width="150">
								<tr>
									<td>&nbsp;</td>
									<td class="stab">Customer Incident#</td>
									<td><ezf:inputText name="custIncdtId_H" ezfName="custIncdtId_H" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"20\""/></td>
									<td>&nbsp;</td>
									<td class="stab">CR Status</td>
									<td><ezf:inputText name="svcCrRebilStsDescTxt_H" ezfName="svcCrRebilStsDescTxt_H" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"20\""/></td>
									<td>&nbsp;</td>
									<td class="stab">Description</td>
									<td><ezf:inputText name="svcCrRebilDescTxt_H" ezfName="svcCrRebilDescTxt_H" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"50\""/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<hr>

<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
				<table width="99%" border="0">
					<tr>
						<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
						<td align="right">
							<ezf:skip>
									<table border="0" cellpadding="1" cellspacing="0">
										<col >
										<col width="40"  align="right">
										<col width="16"  align="center">
										<col width="40"  align="right">
										<col width="16"  align="center">
										<col width="40"  align="right">
										<col width="10">
										<col width="0">
										<col width="1">
										<col width="0">
										<tr>
											<td class="stab">Showing</td>
											<td class="pOut">1</td>
											<td class="stab">to</td>
											<td class="pOut">3</td>
											<td class="stab">of</td>
											<td class="pOut">200</td>
											<td>&nbsp;</td>
											<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
											<td></td>
											<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
										</tr>
									</table>
							</ezf:skip>

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
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

<%-- ######################################## DETAIL ######################################## --%>
				<table>
					<tr>
						<td>
							<div id="parentBoxA">
								<div style="float:left; display:block"> <!-- left table -->
									<div id='leftTblHead' style="display:block;">
									</div>
									<div id='leftTbl' style="float:left; display:block; height:427px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
									</div>
								</div>  <!-- left table -->
								<div style="float:left"> <!-- right table -->
									<div id='rightTblHead' style="width:1097px; display:block; overflow:hidden; margin:0px;padding:0px;">
										<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="100%" style="margin-right:20px" >
											<col width="40"  align="center">	<!-- Seq -->
											<col width="80"  align="center">	<!-- Billed From -->
											<col width="80"  align="center">	<!-- Billed To -->
											<col width="90"  align="center">	<!-- Invoice Num -->
											<col width="90"  align="center">	<!-- Contract Num -->
											<col width="80"  align="center">	<!-- Consolidated Bill# -->
											<col width="60" align="center">		<!-- Invoice Type -->
											<col width="90"  align="center">	<!-- Sub Total -->
											<col width="90"  align="center">	<!-- Tax -->
											<col width="90"  align="center">	<!-- Total Invoice Amount -->
											<col width="90"  align="center">	<!-- Serial Number -->
											<col width="90"  align="center">	<!-- Mdse Code -->
											<col width="120" align="center">	<!-- Customer Incident Status -->
											<tr height="40px">
												<td id="AH0" class="pClothBs">Seq</td>
												<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bllgPerFromDt_A')">Billed From</a><img id="sortIMG.bllgPerFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bllgPerThruDt_A')">Billed To</a><img id="sortIMG.bllgPerThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcInvNum_A')">Invoice<br>Num</a><img id="sortIMG.svcInvNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrNum_A')">Contract<br>Num</a><img id="sortIMG.dsContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'conslBillPk_A')">Consolidated<br>Bill Num</a><img id="sortIMG.conslBillPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcInvChrgTpDescTxt_A')">Invoice<br>Type</a><img id="sortIMG.svcInvChrgTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invLineDealSlsAmt_A')">Sub Total</a><img id="sortIMG.invLineDealSlsAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invLineDealTaxAmt_A')">Tax</a><img id="sortIMG.invLineDealTaxAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invLineDealNetAmt_A')">Total Invoice<br>Amount</a><img id="sortIMG.invLineDealNetAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serNum_A')">Serial<br>Number</a><img id="sortIMG.serNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseCd_A')">Item Cd</a><img id="sortIMG.mdseCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcCrRebilStsDescTxt_A')">CI Status</a><img id="sortIMG.svcCrRebilStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											</tr>
										</table>
									</div><!-- 'rightTblHead' -->
									<div id="rightTbl" style="width:1110px; height:427px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
										<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="100%" >
											<col width="40"  align="center">	<!-- Seq -->
											<col width="80"  align="center">	<!-- Billed From -->
											<col width="80"  align="center">	<!-- Billed To -->
											<col width="90"  align="center">	<!-- Invoice Num -->
											<col width="90"  align="center">	<!-- Contract Num -->
											<col width="80"  align="center">	<!-- Consolidated Bill# -->
											<col width="60" align="center">		<!-- Invoice Type -->
											<col width="90"  align="center">	<!-- Sub Total -->
											<col width="90"  align="center">	<!-- Tax -->
											<col width="90"  align="center">	<!-- Total Invoice Amount -->
											<col width="90"  align="center">	<!-- Serial Number -->
											<col width="90"  align="center">	<!-- Mdse Code -->
											<col width="120" align="center">	<!-- Customer Incident Status -->
											<ezf:row ezfHyo="A">
											<tr height="25px" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
												<td><ezf:anchor name="xxRowNum_AL" ezfName="xxRowNum_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SeqLink','{EZF_ROW_NUMBER}" otherAttr=" id=\"xxRowNum_AL\""><ezf:label name="xxRowNum_A" ezfName="xxRowNum_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
												<td><ezf:label name="bllgPerFromDt_A" ezfName="bllgPerFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="bllgPerThruDt_A" ezfName="bllgPerThruDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="svcInvNum_A" ezfName="svcInvNum_A" value="XXXXXXXXX1XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="dsContrNum_A" ezfName="dsContrNum_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="conslBillPk_A" ezfName="conslBillPk_A" value="1234567890123456789012345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="svcInvChrgTpDescTxt_A" ezfName="svcInvChrgTpDescTxt_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="invLineDealSlsAmt_A" ezfName="invLineDealSlsAmt_A" value="123,456,789,012,345.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="invLineDealTaxAmt_A" ezfName="invLineDealTaxAmt_A" value="123,456,789,012,345.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="invLineDealNetAmt_A" ezfName="invLineDealNetAmt_A" value="123,456,789,012,345.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="serNum_A" ezfName="serNum_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="XXXXXXXXX1XXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="svcCrRebilStsDescTxt_A" ezfName="svcCrRebilStsDescTxt_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td class="pAuditInfo">
													<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A\""/>
													<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A\""/>
													<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A\""/>
													<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A\""/>
													<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A\""/>
												</td>
											</tr>
											<ezf:skip>
											</ezf:skip>
											</ezf:row>
										</table>
									</div><!-- rightTbl -->
								</div><!-- right table -->
							</div><!-- parent box -->
						</td>
					</tr>
				</table>
				</div><!-- pTab_BODY -->
			</td>
		</tr>
	</table>
</center>

<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, false);
</script>


<%-- **** Task specific area ends here **** --%>
