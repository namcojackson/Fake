<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180604130832 --%>
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
			<input type="hidden" name="pageID" value="NSAL1090Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Credit Rebill Detail Screen">

<%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPConstant"%>
<%@ page import="business.servlet.NSAL1090.NSAL1090BMsg" %>
<%@ page import="business.servlet.NSAL1090.NSAL1090_ABMsg" %>
<% 
	String svcCrRebilStsCd = ((business.servlet.NSAL1090.NSAL1090Bean)databean).getSvcCrRebilStsCd_H(); 
	NSAL1090BMsg bMsg = (NSAL1090BMsg) databean.getEZDBMsg();
	String xxDplyCtrlFlg_S = bMsg.xxDplyCtrlFlg_S.getValue(); 
%>

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
										<li title="Credit Rebill Detail Screen" class="pTab_ON"><a href="#">Cr Rbl Dtl</a></li>
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
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="200" align="left">
								<tr height="24">
									<td class="stab"><b>Customer Incident Details</b></td>
								</tr>
							</table>
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
								<tr height="24">
									<td>&nbsp;</td>
									<td class="stab">Customer Incident#</td>
									<td><ezf:inputText name="custIncdtId_H" ezfName="custIncdtId_H" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
									<td>&nbsp;</td>
									<td class="stab">CR Status</td>
									<td><ezf:inputText name="svcCrRebilStsDescTxt_H" ezfName="svcCrRebilStsDescTxt_H" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
									<td>&nbsp;</td>
									<td class="stab">Description</td>
									<td><ezf:inputText name="svcCrRebilDescTxt_H" ezfName="svcCrRebilDescTxt_H" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"50\" maxlength=\"4000\""/></td>
								</tr>
							</table>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="20">
								<col width="110" align="left">
								<col width="150">
								<col width="500">
								<col width="">
								<col width="2">
								<col width="">
								<col width="2">
								<col width="">
								<tr height="24">
									<td>&nbsp;</td>
									<td class="stab">Process</td>
									<td align="left">
										<ezf:select name="svcCrRebilProcCd_H" ezfName="svcCrRebilProcCd_H" ezfCodeName="svcCrRebilProcCd_L" ezfDispName="svcCrRebilDescTxt_L" otherAttr=" style=\"width:145px\""/>
									</td>
									<td>&nbsp;</td>
								<% if (SVC_CR_REBIL_STS.ENTERED.equals(svcCrRebilStsCd)) { %>
									<td align="right"><ezf:inputButton name="SubmitForApproval" value="Submit for Approval" htmlClass="pBtn10" otherAttr=" id=\"SubmitForApproval\""/></td>
								<% } else  { %>
									<td align="right"><ezf:inputButton name="ViewApprovers" value="View Approvers" htmlClass="pBtn9" otherAttr=" id=\"ViewApprovers\""/></td>
								<% } %>
									<td></td>
									<td align="right"><ezf:inputButton name="Research" value="Research" htmlClass="pBtn9" otherAttr=" id=\"Research\""/></td>
									<td></td>
									<td align="right"><ezf:inputButton name="CancelCI" value="Cancel CI" htmlClass="pBtn9" otherAttr=" id=\"CancelCI\""/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<hr>

<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
				<table width="99%" border="0">
					<tr align="right">
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="0">
								<col width="1000" align="right">
								<tr>
									<td><ezf:inputButton name="OpenWin_Attach" value="Attachment" htmlClass="pBtn9" /></td>
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
							<ezf:skip>
									<table border="0" cellpadding="1" cellspacing="0">
										<col width="0">
										<col width="700"align="right">
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
											<td><input type="button" class="pBtn9" value="Attachment" name="OpenWin_Attach" onclick="sendServer(this)"></td>
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

						</td>
					</tr>
				</table>
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

<%-- ######################################## DETAIL ######################################## --%>
				<table>
					<tr>
						<td>
							<div id="Top" style="width:1090px; height:40px; overflow-x:hidden;">
								<table border="1" cellpadding="1" cellspacing="0">
									<col width="40"  align="center">	<!-- Seq -->
									<col width="85"  align="center">	<!-- Invoice# -->
									<col width="85" align="center">	<!-- Contract#-->
									<col width="85"  align="center">	<!-- Consolidated Bill# -->
									<col width="75"  align="center">	<!-- Inv Type -->
									<col width="80"  align="center">	<!-- Billed From -->
									<col width="80"  align="center">	<!-- Billed To -->
									<col width="100" align="center">	<!-- Old Inv Amt -->
									<col width="100" align="center">	<!-- New Inv Amt -->
									<col width="100" align="center">	<!-- Inv Amt Net Impact -->
									<col width="35"  align="center">	<!-- Print Flag -->
									<col width="85"  align="center">	<!-- Rebill Invoice# -->
									<col width="85"  align="center">	<!-- Credit Invoice# -->
									<tr height="40px">
										<td class="pClothBs">Seq</td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'origSvcInvNum_A')">Invoice#</a><img id="sortIMG.origSvcInvNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrNum_A')">Contract#</a><img id="sortIMG.dsContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'conslBillPk_A')">Consolidated<br>Bill#</a><img id="sortIMG.conslBillPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invTpNm_A')">Inv<br>Type</a><img id="sortIMG.invTpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxFromDt_A')">Billed From</a><img id="sortIMG.xxFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxThruDt_A')">Billed To</a><img id="sortIMG.xxThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxInvAmt_A1')">Old Inv<br/>Amt</a><img id="sortIMG.xxInvAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxInvAmt_A2')">New Inv<br>Amt</a><img id="sortIMG.xxInvAmt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxInvAmt_A3')">Inv Amt<br>Net Impact</a><img id="sortIMG.xxInvAmt_A3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_AL" ezfName="xxChkBox_AL" value="Y" onClick="sendServer('AllCheck')" /></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rebilSvcInvNum_A')">Rebill<br>Invoice#</a><img id="sortIMG.rebilSvcInvNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'crSvcInvNum_A')">Credit<br>Invoice#</a><img id="sortIMG.crSvcInvNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
									</tr>
								</table>
							</div>
							<div id="Detail" style="width:1107px; height:377px; overflow-y:scroll;" >
								<table id="A" border="1" cellpadding="1" cellspacing="0">
									<col width="40"  align="center">	<!-- Seq -->
									<col width="85"  align="center">	<!-- Invoice# -->
									<col width="85" align="center">	<!-- Contract#-->
									<col width="85"  align="center">	<!-- Consolidated Bill# -->
									<col width="75"  align="center">	<!-- Inv Type -->
									<col width="80"  align="center">	<!-- Billed From -->
									<col width="80"  align="center">	<!-- Billed To -->
									<col width="100" align="center">	<!-- Old Inv Amt -->
									<col width="100" align="center">	<!-- New Inv Amt -->
									<col width="100" align="center">	<!-- Inv Amt Net Impact -->
									<col width="35"  align="center">	<!-- Print Flag -->
									<col width="85"  align="center">	<!-- Rebill Invoice# -->
									<col width="85"  align="center">	<!-- Credit Invoice# -->
									<% int i = 0; %>
									<ezf:row ezfHyo="A">
									<% NSAL1090_ABMsg abMsg = bMsg.A.no(i++); %>
										<tr height="25px">
											<% if (ZYPCommonFunc.hasValue(abMsg.invTpNm_A) && !"ADDITIONAL".equals(abMsg.invTpNm_A.getValue())) { %>
												<td><ezf:anchor name="xxRowNum_AL" ezfName="xxRowNum_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SeqLink','{EZF_ROW_NUMBER}" otherAttr=" id=\"xxRowNum_AL\""><ezf:label name="xxRowNum_A" ezfName="xxRowNum_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
											<% } else { %>
												<td><ezf:label name="xxRowNum_A" ezfName="xxRowNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<% } %>
											<td><ezf:inputText name="origSvcInvNum_A" ezfName="origSvcInvNum_A" value="1234567890123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:inputText name="dsContrNum_A" ezfName="dsContrNum_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:inputText name="conslBillPk_A" ezfName="conslBillPk_A" value="1234567890123456789012345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:label name="invTpNm_A" ezfName="invTpNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="xxFromDt_A" ezfName="xxFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="xxThruDt_A" ezfName="xxThruDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:inputText name="xxInvAmt_A1" ezfName="xxInvAmt_A1" value="$123,456,789,012,345.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:inputText name="xxInvAmt_A2" ezfName="xxInvAmt_A2" value="$123,456,789,012,345.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:inputText name="xxInvAmt_A3" ezfName="xxInvAmt_A3" value="$123,456,789,012,345.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServer('DetailCheck','{EZF_ROW_NUMBER}')" otherAttr=" id=\"xxChkBox_A\""/></td>
											<td><ezf:inputText name="rebilSvcInvNum_A" ezfName="rebilSvcInvNum_A" value="1234567890123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:inputText name="crSvcInvNum_A" ezfName="crSvcInvNum_A" value="1234567890123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
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
							</div>
							<div id="Sum" style="width:380px; height:25px; padding-left: 474px" >
								<table border="1" cellpadding="1" cellspacing="0">
									<col width="80"  align="center">	<!-- Billed To -->
									<col width="100" align="center">	<!-- Old Inv Amt -->
									<col width="100" align="center">	<!-- New Inv Amt -->
									<col width="100" align="center">	<!-- Inv Amt Net Impact -->
									<tr height="25px">
										<td>Sum</td>
										<td><ezf:inputText name="xxInvAmt_S1" ezfName="xxInvAmt_S1" value="$123,456,789,012,345.00" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td><ezf:inputText name="xxInvAmt_S2" ezfName="xxInvAmt_S2" value="$123,456,789,012,345.00" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td align="right">
											<% if (ZYPCommonFunc.hasValue(xxDplyCtrlFlg_S) && xxDplyCtrlFlg_S.equals(ZYPConstant.FLG_ON_Y)) { %>
											<font color="red" style="padding-right: 7px">(<ezf:label name="xxInvAmt_S3" ezfName="xxInvAmt_S3" />)</font>
											<% } else { %>
											<font color="black" style="padding-right: 7px"><ezf:label name="xxInvAmt_S3" ezfName="xxInvAmt_S3" /></font>
											<% } %>
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

<style TYPE="text/css">
<!--
	tr.checkLineBGcolor{background-color:yellow;}
	a img{border-style:none;}
-->
</style>


<%-- **** Task specific area ends here **** --%>
