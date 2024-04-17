<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180420114105 --%>
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
			<input type="hidden" name="pageID" value="NFCL0750Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Auto Write-Off Result Inquiry Screen">
<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
<!-- ######################################## HEADER ######################################## -->
				<%-- ###TAB - HEAD --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Auto Write-Off Result Inquiry Screen" class="pTab_ON"><a href="#">Wrt Off Inq</a></li>
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
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center" >
						<col width="110">
						<col width="400">
						<col width="110">
						<col width="400">
						<col>
						<tr height="3"></tr>
						<tr>
							<td class="stab">Display</td>
							<td><ezf:select name="cltDispTpCd_H" ezfName="cltDispTpCd_H" ezfBlank="1" ezfCodeName="cltDispTpCd_LC" ezfDispName="cltDispTpDescTxt_LD" otherAttr=" style=\"width:180px;\""/></td>
							<td></td>
							<td></td>
						</tr>
						<tr height="23">
							<td class="stab">User ID</td>
							<td><ezf:inputText name="wrtOffRqstUsrId_H" ezfName="wrtOffRqstUsrId_H" value="0---------1-----E" otherAttr=" size=\"12\" ezftoupper=\"\""/></td>
							<td class="stab">Write Off Request#</td>
							<td><ezf:inputText name="wrtOffRqstGrpCd_H" ezfName="wrtOffRqstGrpCd_H" value="0--------1---------2---------3---------4" otherAttr=" size=\"25\" ezftoupper=\"\""/></td>
						</tr>
						<tr height="23">
							<td class="stab">Reason</td>
							<td>
								<ezf:select name="arAdjRsnDescTxt_SV" ezfName="arAdjRsnDescTxt_SV" ezfBlank="1" ezfCodeName="arAdjRsnCd_L" ezfDispName="arAdjRsnDescTxt_L" otherAttr=" style=\"width:330px;\""/>
							</td>
							<td class="stab">Activity</td>
							<td>
								<ezf:select name="arAdjTpDescTxt_SV" ezfName="arAdjTpDescTxt_SV" ezfBlank="1" ezfCodeName="arAdjTpCd_L" ezfDispName="arAdjTpDescTxt_L" otherAttr=" style=\"width:370px;\""/>
							</td>
						</tr>
						<tr height="23">
							<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_SrchCust" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SrchCust" >Customer Number</ezf:anchor></td>
							<td>
								<ezf:inputText name="dsAcctNum_H" ezfName="dsAcctNum_H" value="0---------1-----E" otherAttr=" size=\"15\" ezftoupper=\"\""/>
								<ezf:inputButton name="SrchCustNm" value=">>" ezfHyo="SrchCustNm" ezfArrayNo="0" htmlClass="pBtn0" />
								<ezf:inputText name="dsAcctNm_H" ezfName="dsAcctNm_H" value="CANON USA INC  (MERCHANDISE)" otherAttr=" id=\"locNm_H\" size=\"25\""/>
							</td>
							<td class="stab">Request Type</td>
							<td>
								<ezf:select name="arWrtOffRqstTpDescTxt_SV" ezfName="arWrtOffRqstTpDescTxt_SV" ezfBlank="1" ezfCodeName="arWrtOffRqstTpCd_L" ezfDispName="arWrtOffRqstTpDescTxt_L" otherEvent1=" onchange=\"sendServer('OnChange_WrtOffRqstTp')\"" otherAttr=" style=\"width:150px;\""/>
							</td>
						</tr>
						<tr height="23">
							<td class="stab">Adj / Req Date</td>
							<td class="stab">
								<ezf:inputText name="xxFromDt_H" ezfName="xxFromDt_H" value="01/01/2016" otherAttr=" size=\"12\" maxlength=\"10\""/>
								<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('xxFromDt_H', 4);" id="xxFromDt_H">
								&nbsp;-&nbsp;
								<ezf:inputText name="xxThruDt_H" ezfName="xxThruDt_H" value="12/31/2016" otherAttr=" size=\"12\" maxlength=\"10\""/>
								<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('xxThruDt_H', 4);" id="xxThruDt_H">
							</td>
							<td class="stab">Option</td>
							<td class="stab">
								<ezf:inputCheckBox name="xxChkBox_R" ezfName="xxChkBox_R" value="Y" />Generate Report Only
								<ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" />Create Adjustment
						</tr>
						<tr height="23">
							<td class="stab">Workflow Status</td>
							<td>
								<ezf:select name="arDsWfStsDescTxt_SV" ezfName="arDsWfStsDescTxt_SV" ezfBlank="1" ezfCodeName="arDsWfStsCd_L" ezfDispName="arDsWfStsDescTxt_L" otherAttr=" style=\"width:150px;\""/>
							</td>
							<td>&nbsp;</td>
							<td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
						</tr>
					</table>
					<hr>
					<!-- ######################################## DETAIL HEADER ######################################## -->
 					<table border="0" style="table-layout:fixed;width=96%;margin-left:20;">
						<col width="500">	<!-- PreferredView -->
						<col width="500">	<!-- Paging -->
						<tr>
							<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
							<!-- Pagination & Navigation--START-->
							<td  align="right">
								<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
									<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"           value="A" />
									<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
									<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum" />
									<jsp:param name="ShowingTotal"      value="xxPageShowTotNum" />
									<jsp:param name="ViewMode"          value="FULL" />
								</jsp:include>
								<ezf:skip>
								<table border="0" cellpadding="0" width="100%">
									<tr>
										<td align="left">
											<table border="0" cellpadding="0" align="left" cellspacing="0">
												<col>
													<tr>
													<td>Results 999 - 999 of 999</td>
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
													<td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
													<td class="stab">/</td>
													<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
													<td class="stab">page</td>
													<td>&nbsp;</td>
													<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								</ezf:skip>
							</td>
							<!-- Pagination & Navigation--END-->
						</tr>
					</table>
					<!-- ######################################## DETAIL ######################################## -->
					<table style="margin-left:20;">
						<tr>
							<td>
								<div id="parentBoxA">
									<div style="float:left; display:block"> <!-- left table -->
										<div id='leftTblHead' style="display:block;">
										</div>
										<div id='leftTbl' style="float:left; display:block; height:331px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
										</div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id='rightTblHead' style="width:1080px; display:block; overflow:hidden; margin:0px; padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1080px" style="margin-right:20px">
												<col width="30"  align="center">	<!-- Radio -->
												<col width="70"  align="center">	<!-- User ID -->
												<col width="180" align="center">	<!-- Write Off Request# -->
												<col width="150" align="center">	<!-- Request Type -->
												<col width="150" align="center">	<!-- Generate Report Only -->
												<col width="200" align="center">	<!-- Reason -->
												<col width="170" align="center">	<!-- Activity -->
												<col width="300" align="center">	<!-- Note -->
												<col width="150" align="center">	<!-- Remaining Balance -->
												<col width="150" align="center">	<!-- Invoice# -->
												<col width="200" align="center">	<!-- Due Date -->
												<col width="150" align="center">	<!-- Customer Number -->
												<col width="100" align="center">	<!-- Incl Cnsl Inv -->
												<col width="130" align="center">	<!-- Wf Sts -->
												<col width="220" align="center">	<!-- Approver Name -->
												<col width="400" align="center">	<!-- Error Message -->
												<tr height="24px">"arWrtOffNoteTxt
													<td id="AH0"  class="pClothBs colFix"></td>
													<td id="AH1"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'wrtOffRqstUsrId_A')">User ID</a><img id="sortIMG.wrtOffRqstUsrId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'wrtOffRqstGrpCd_A')">Write Off Request#</a><img id="sortIMG.wrtOffRqstGrpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'arWrtOffRqstTpDescTxt_A')">Request Type</a><img id="sortIMG.arWrtOffRqstTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxScrItem30Txt_A')">Option</a><img id="sortIMG.xxScrItem30Txt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'arAdjRsnDescTxt_A')">Reason</a><img id="sortIMG.arAdjRsnDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'arAdjTpDescTxt_A')">Activity</a><img id="sortIMG.arAdjTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'arWrtOffNoteTxt_A')">Note</a><img id="sortIMG.arWrtOffNoteTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxGenlFldAreaTxt_FB')">Remaining Balance</a><img id="sortIMG.xxGenlFldAreaTxt_FB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxGenlFldAreaTxt_IN')">Invoice#</a><img id="sortIMG.xxGenlFldAreaTxt_IN" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxGenlFldAreaTxt_DT')">Due Date</a><img id="sortIMG.xxGenlFldAreaTxt_DT" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxGenlFldAreaTxt_CN')">Customer Number</a><img id="sortIMG.xxGenlFldAreaTxt_CN" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'inclConslInvFlg_A')">Incl Cnsl Inv</a><img id="sortIMG.inclConslInvFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'arDsWfStsDescTxt_A')">Wf Sts</a><img id="sortIMG.arDsWfStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'wrtOffApvlUsrNm_A')">Approver Name</a><img id="sortIMG.wrtOffApvlUsrNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'wrtOffErrMsgTxt_A')">Error Message</a><img id="sortIMG.wrtOffErrMsgTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												</tr>
											</table>
										</div><!-- 'rightTblHead' -->
										<div id="rightTbl" style="width:1080; height:331px; display:block; overflow:scroll; overflow-y:hidden; margin:0px; padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1080px" >
												<col width="30"  align="left">	<!-- Radio -->
												<col width="70"  align="left">	<!-- User ID -->
												<col width="180" align="left">	<!-- Write Off Request# -->
												<col width="150" align="left">	<!-- Request Type -->
												<col width="150" align="left">	<!-- Generate Report Only -->
												<col width="200" align="left">	<!-- Reason -->
												<col width="170" align="left">	<!-- Activity -->
												<col width="300" align="left">	<!-- Note -->
												<col width="150" align="left">	<!-- Remaining Balance -->
												<col width="150" align="left">	<!-- Invoice# -->
												<col width="200" align="left">	<!-- Due Date -->
												<col width="150" align="left">	<!-- Customer Number -->
												<col width="100" align="left">	<!-- Incl Cnsl Inv -->
												<col width="130" align="left">	<!-- Wf Sts -->
												<col width="220" align="center"><!-- Approver Name -->
												<col width="400" align="left">	<!-- Error Message -->
												<ezf:row ezfHyo="A">
												<tr height="24px">
													<td><ezf:inputRadio name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"xxRadioBtn_A{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:inputText name="wrtOffRqstUsrId_A" ezfName="wrtOffRqstUsrId_A" value="Q08266" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="wrtOffRqstGrpCd_A" ezfName="wrtOffRqstGrpCd_A" value="Q0826620170727161413895" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="arWrtOffRqstTpDescTxt_A" ezfName="arWrtOffRqstTpDescTxt_A" value="Write-Off Screen" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:label name="xxScrItem30Txt_A" ezfName="xxScrItem30Txt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="arAdjRsnDescTxt_A" ezfName="arAdjRsnDescTxt_A" value="AC-SMALL AMT" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="arAdjTpDescTxt_A" ezfName="arAdjTpDescTxt_A" value="NORTH EAST WRITE OFFS" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="arWrtOffNoteTxt_A" ezfName="arWrtOffNoteTxt_A" value="0--------1---------2---------3---------4---------5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="xxGenlFldAreaTxt_FB" ezfName="xxGenlFldAreaTxt_FB" value="-1,000.00-1,000.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none; background-color:transparent;text-align:right;\""/></td>
													<td><ezf:inputText name="xxGenlFldAreaTxt_IN" ezfName="xxGenlFldAreaTxt_IN" value="6000040-6000040" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="center"><ezf:label name="xxGenlFldAreaTxt_DT" ezfName="xxGenlFldAreaTxt_DT" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="xxGenlFldAreaTxt_CN" ezfName="xxGenlFldAreaTxt_CN" value="7000267-7000267" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="center"><ezf:label name="inclConslInvFlg_A" ezfName="inclConslInvFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="arDsWfStsDescTxt_A" ezfName="arDsWfStsDescTxt_A" value="Incompleted" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="wrtOffApvlUsrNm_A" ezfName="wrtOffApvlUsrNm_A" value="0--------1---------2---------3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"29\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="wrtOffErrMsgTxt_A" ezfName="wrtOffErrMsgTxt_A" value="0--------1---------2---------3---------4---------5---------6---------7---------8--------9---------0---------1---------2---------3---------4--------5---------6---------7---------8--------9---------0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"55\" style=\"border:none; background-color:transparent;\""/></td>
												</tr>
												<ezf:skip>
												<tr height="24px">
													<td><input type="radio" value="{EZF_ROW_NUMBER}" id="xxRadioBtn_A{EZF_ROW_NUMBER}" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" ezfhyo="A" ></td>
													<td><input type="text" class="pPro" size="8"  name="wrtOffRqstUsrId_A"       ezfhyo="A" ezfname="wrtOffRqstUsrId_A"       readOnly style="border:none; background-color:transparent;"                  value="Q09708"></td>
													<td><input type="text" class="pPro" size="24" name="wrtOffRqstGrpCd_A"       ezfhyo="A" ezfname="wrtOffRqstGrpCd_A"       readOnly style="border:none; background-color:transparent;"                  value="Q0826620170727161413895"></td>
													<td><input type="text" class="pPro" size="20" name="arWrtOffRqstTpDescTxt_A" ezfhyo="A" ezfname="arWrtOffRqstTpDescTxt_A" readOnly style="border:none; background-color:transparent;"                  value="Write-Off Screen"></td>
													<td><label ezfout                             name="xxScrItem30Txt_A"        ezfhyo="A" ezfname="xxScrItem30Txt_A">Generate Report Only</label></td>
													<td><input type="text" class="pPro" size="27" name="arAdjRsnDescTxt_A"       ezfhyo="A" ezfname="arAdjRsnDescTxt_A"       readOnly style="border:none; background-color:transparent;"                  value="AC-SMALL AMT"></td>
													<td><input type="text" class="pPro" size="22" name="arAdjTpDescTxt_A"        ezfhyo="A" ezfname="arAdjTpDescTxt_A"        readOnly style="border:none; background-color:transparent;"                  value="NORTH EAST WRITE OFFS"></td>
													<td><input type="text" class="pPro" size="40" name="arWrtOffNoteTxt_A"       ezfhyo="A" ezfname="arWrtOffNoteTxt_A"       readOnly style="border:none; background-color:transparent;"                  value="0--------1---------2---------3---------4---------5"></td>
													<td><input type="text" class="pPro" size="20" name="xxGenlFldAreaTxt_FB"     ezfhyo="A" ezfname="xxGenlFldAreaTxt_FB"     readOnly style="border:none; background-color:transparent;text-align:right;" value="-1,000.00-1,000.00"></td>
													<td><input type="text" class="pPro" size="20" name="xxGenlFldAreaTxt_IN"     ezfhyo="A" ezfname="xxGenlFldAreaTxt_IN"     readOnly style="border:none; background-color:transparent;"                  value="6000040-6000040"></td>
													<td align="center"><label ezfout              name="xxGenlFldAreaTxt_DT"     ezfhyo="A" ezfname="xxGenlFldAreaTxt_DT">01/01/2016-01/01/2016</label></td>
													<td><input type="text" class="pPro" size="20" name="xxGenlFldAreaTxt_CN"     ezfhyo="A" ezfname="xxGenlFldAreaTxt_CN"     readOnly style="border:none; background-color:transparent;"                  value="7000267-7000267"></td>
													<td align="center"><label ezfout              name="inclConslInvFlg_A"       ezfhyo="A" ezfname="inclConslInvFlg_A">Y</label></td>
													<td><input type="text" class="pPro" size="16" name="arDsWfStsDescTxt_A"      ezfhyo="A" ezfname="arDsWfStsDescTxt_A"      readOnly style="border:none; background-color:transparent;"                  value="Incompleted"></td>
													<td><input type="text" class="pPro" size="29" name="wrtOffApvlUsrNm_A"       ezfhyo="A" ezfname="wrtOffApvlUsrNm_A"       readOnly style="border:none; background-color:transparent;"                  value="0--------1---------2---------3"></td>
													<td><input type="text" class="pPro" size="55" name="wrtOffErrMsgTxt_A"       ezfhyo="A" ezfname="wrtOffErrMsgTxt_A"       readOnly style="border:none; background-color:transparent;"                  value="0--------1---------2---------3---------4---------5---------6---------7---------8--------9---------0---------1---------2---------3---------4--------5---------6---------7---------8--------9---------0"></td>
												</tr>
												</ezf:skip>
												</ezf:row>
											</table>
										</div><!-- rightTbl -->
									</div><!-- right table -->
								</div><!-- parent box -->
							</td>
						</tr>
						<tr>
							<td>
								<table border="0" style="table-layout:fixed;">
									<col width="1000">
									<col width="80">
									<tr height="20px">
										<td>&nbsp;</td>
										<td><ezf:inputButton name="OpenWin_Detail" value="Detail" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div><!-- pTab_BODY -->
			</td>
		</tr>
	</table>
</center>
	<script type="text/javascript" defer>
	    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, true);
	</script>

<%-- **** Task specific area ends here **** --%>
