<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20181219143329 --%>
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
<input type="hidden" name="pageID" value="NYEL8810Scrn00">
<!-- Set Page Name -->
<input type="hidden" name="pageName" value="Worklist">




<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<%-- ###TAB - HEAD --%>
				<%-- After Convert to JSP, this area suppose to be deleted [FROM] --%>
				<ezf:skip>
					<div class="pTab_HEAD">
						<ul>
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="96%">
										<div>
											<li title="Model Search" class="pTab_ON"><a href="#">Work List</a></li>
											<li title="Model Search" class="pTab_OFF"><a href="#">Delegation</a></li>
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
				<%-- After Convert to JSP, this area suppose to be deleted [TO] --%>
				<!-- include S21BusinessProcessTAB -->
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
					<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
						<col width="147">
						<col width="345">
						<col width="110">
						<col width="300">
						<col width="">
						<tr>
							<td class="stab">Saved Search Options</td>
							<td>
								<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_L" ezfDispName="srchOptNm_L" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
							</td>
							<td class="stab">Search Option Name</td>
							<td class="stab"><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
							<td>
								<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
								<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
							</td>
						</tr>
					</table>
					<hr style="height: 0px;" cellpadding="0"  cellspacing="0">
					<table border="0" cellpadding="0" cellspacing="0" style="margin-left: 3;">
						<col width="75">
						<col width="235">
						<col width="80">
						<col width="305">
						<col width="80">
						<col width="305">
						<tr>
							<td class="stab"  height="10"><ezf:anchor name="usrId_LK" ezfName="usrId_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_UserName" >User Name</ezf:anchor></td>
							<td colspan="3" height="10">
								<ezf:inputText name="usrId" ezfName="usrId" otherAttr=" size=\"7\" maxlength=\"7\" ezftoupper=\"\""/>
								<ezf:inputButton name="Get_UserName" value=">>" htmlClass="pBtn1" otherAttr=" id=\"Get_UserName\""/>
								<ezf:inputText name="usrNm" ezfName="usrNm" value="xxxxxxxxx0xxxxxxxxx0xxxxxxxxx0xxxxxxxxx0xxxx5xx" otherAttr=" size=\"60\" maxlength=\"60\""/>
							</td>
						</tr>
						<tr>
							<td class="stab" height="10">Workflow ID</td>
							<td  height="10">
								<ezf:inputText name="wfProcPk" ezfName="wfProcPk" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/>
							</td>
							<td class="stab">Process Status</td>
							<td>
								<ezf:select name="procStsCd" ezfName="procStsCd" ezfCodeName="procStsCd_L" ezfDispName="procStsNm_L" otherAttr=" style=\"width: 200px\" id=\"procStsCd\""/>
							</td>
						</tr>
						<tr>
							<td class="stab">Source#</td>
							<td>
								<ezf:inputText name="wfProcDocId" ezfName="wfProcDocId" otherAttr=" size=\"30\" maxlength=\"40\""/>
							</td>
							<td class="stab">Process Name</td>
							<td>
								<ezf:select name="wfProcNm" ezfName="wfProcNm" ezfCodeName="wfProcNm_L" ezfDispName="wfProcNm_LD" otherAttr=" style=\"width: 200px\" id=\"\""/>
							</td>
							<td class="stab">Task Name</td>
							<td>
								<ezf:select name="wfWrkItemNm" ezfName="wfWrkItemNm" ezfCodeName="wfWrkItemNm_L" ezfDispName="wfWrkItemNm_LD" otherAttr=" style=\"width: 200px\" id=\"\""/>
							</td>
						</tr>
						<tr>
							<td class="stab">Subject</td>
							<td>
								<ezf:inputText name="wfBizAttrbTxt" ezfName="wfBizAttrbTxt" otherAttr=" size=\"30\" maxlength=\"40\""/>
							</td>
							<td class="stab">Sent Date</td>
							<td>
								<ezf:inputText name="xxFromDt_SS" ezfName="xxFromDt_SS" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt_SS', 4);" > -
								<ezf:inputText name="xxThruDt_SE" ezfName="xxThruDt_SE" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxThruDt_SE', 4);" >
							</td>
							<td class="stab">Due Date</td>
							<td>
								<ezf:inputText name="xxFromDt_DS" ezfName="xxFromDt_DS" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt_DS', 4);" > -
								<ezf:inputText name="xxThruDt_DE" ezfName="xxThruDt_DE" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxThruDt_DE', 4);" >
							</td>
						</tr>
						<tr>
							<td class="stab"><ezf:anchor name="grpId_FK" ezfName="grpId_FK" ezfEmulateBtn="1" ezfGuard="OpenWin_FromUsrGrp" >From</ezf:anchor></td>
							<td  colspan="5">
								<ezf:select name="xxGrpFlg_F" ezfName="xxGrpFlg_F" ezfCodeName="xxGrpFlg_FL" ezfDispName="xxCondNm_FD" otherAttr=" style=\"width: 70px\" id=\"\""/>
								<ezf:inputText name="wfUsrGrpNm_F" ezfName="wfUsrGrpNm_F" value="01234567890123456789" otherAttr=" size=\"20\" maxlength=\"16\" ezftoupper=\"\""/>
								<ezf:inputButton name="Get_FromGroupName" value=">>" htmlClass="pBtn1" otherAttr=" id=\"Get_FromGroupName\""/>
								<ezf:inputText name="xxDtlNm_F" ezfName="xxDtlNm_F" value="xxxxxxxxx0xxxxxxxxx0xxxxxxxxx0xxxxxxxxx0xxxx5xx" otherAttr=" size=\"60\" maxlength=\"60\""/>
							</td>
						</tr>
						<tr>
							<td class="stab"><ezf:anchor name="grpId_TK" ezfName="grpId_TK" ezfEmulateBtn="1" ezfGuard="OpenWin_ToUsrGrp" >To</ezf:anchor></td>
							<td  colspan="5">
								<ezf:select name="xxGrpFlg_T" ezfName="xxGrpFlg_T" ezfCodeName="xxGrpFlg_TL" ezfDispName="xxCondNm_TD" otherAttr=" style=\"width: 70px\" id=\"\""/>
								<ezf:inputText name="wfUsrGrpNm_T" ezfName="wfUsrGrpNm_T" value="01234567890123456789" otherAttr=" size=\"20\" maxlength=\"16\" ezftoupper=\"\""/>
								<ezf:inputButton name="Get_ToGroupName" value=">>" htmlClass="pBtn1" otherAttr=" id=\"Get_ToGroupName\""/>
								<ezf:inputText name="xxDtlNm_T" ezfName="xxDtlNm_T" value="xxxxxxxxx0xxxxxxxxx0xxxxxxxxx0xxxxxxxxx0xxxx5xx" otherAttr=" size=\"60\" maxlength=\"60\""/>
							</td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" style="margin-left: 3;margin-bottom:-5px">
						<col width="75">
						<col width="835">
						<col width="160">
						<tr>
							<td class="stab">Type</td>
							<td>
								<ezf:inputCheckBox name="xxWfAprChkFlg" ezfName="xxWfAprChkFlg" value="Y" />For Action&nbsp;
								<ezf:inputCheckBox name="xxWfSubChkFlg" ezfName="xxWfSubChkFlg" value="Y" />My Group&nbsp;
								<ezf:inputCheckBox name="xxWfVisChkFlg" ezfName="xxWfVisChkFlg" value="Y" />Rejected&nbsp;
								<ezf:inputCheckBox name="xxWfFyiChkFlg" ezfName="xxWfFyiChkFlg" value="Y" />FYI&nbsp;&nbsp;&nbsp;&nbsp;
								<ezf:inputCheckBox name="xxWfConfFlg" ezfName="xxWfConfFlg" value="Y" />FYI(Confirmed)&nbsp;
							</td>
							<td align="right">
								<!--
								<input type="button" class="pBtn6" value="PSts" name="OpenWin_ProcessStatus" onclick="sendServer(this)">
								-->
								<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
							</td>
						</tr>
					</table>
					<hr style="height: 0px;margin-bottom:-5px" cellpadding="0"  cellspacing="0">
					<div>
					<!--
						<table border="0" style="table-layout:fixed;width=100%">
						<col width="185">
						<col width="780">
						<col width="">
					-->
						<%-- Pagenation --%>
						<table border="0" cellpadding="1" cellspacing="0" width="95%">
							<tr>
								<td>
									<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
								</td>
								<td align="left">
									<label>(*) = Administrator rights</label>
								</td>
								<td align="right">
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
						<div style="border:0px solid; width:100%;"  id="parentBoxA">
							<table border="0" cellpadding="1" cellspacing="0" >
								<tr>
									<td valign="Top" width="">
										<div style="float:left; display:block"> <!-- left table -->
											<div id='leftTblHead' style="display:block;">
											</div>
											<div id='leftTbl' style="float:left; display:block; height:253; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20">
											</div>
										</div>  <!-- left table -->
										<div style="float:left"> <!-- right table -->
											<div id='rightTblHead' style="width:1080; display:block; overflow:hidden; margin:0px;padding:0px;">
												<table border="1" cellpadding="0" cellspacing="0" id="AHEAD" width="3947" style="table-layout:fixed;margin-right:20px">
													<col align="center" width="026"><!-- checkbox1 -->
													<col align="center" width="070"><!-- Type -->
													<col align="center" width="078"><!-- WorkflowID -->
													<col align="center" width="145"><!-- Source# -->
													<col align="center" width="200"><!-- process Name-->
													<col align="center" width="090"><!-- Process status -->
													<col align="center" width="165"><!-- task Name-->
													<col align="center" width="150"><!-- Task status -->
													<col align="center" width="200"><!-- attribute1 -->
													<col align="center" width="200"><!-- attribute2 -->
													<col align="center" width="200"><!-- attribute3 -->
													<col align="center" width="200"><!-- attribute4 -->
													<col align="center" width="200"><!-- attribute5 -->
													<col align="center" width="190"><!-- Sent Date -->
													<col align="center" width="190"><!-- Due Date -->
													<col align="center" width="190"><!-- Closed Date -->
													<col align="center" width="200"><!-- From -->
													<col align="center" width="400"><!-- To -->
													<col align="center" width="180"><!-- Age -->
													<col align="center" width="180"><!-- Last Action -->
													<col align="center" width="200"><!-- Last Action By -->
													<col align="center" width="400"><!-- Comment -->
													<tr>
														<td id="AH0"  class="pClothBs colFix"><ezf:inputCheckBox name="xxWfAprAllChkFlg" ezfName="xxWfAprAllChkFlg" value="Y" onClick="sendServerForPreferredView('SelectAllApr','0')" otherAttr=" id=\"AH0_ALL_CHECK_BOX\" style=\"vertical-align: 0; height: 16px; width: 16px;\""/></td>
														<td id="AH1"  class="pClothBs"><a href="#" class="pSortCol" id="xxWfPsblActNm_A" onclick="columnSort( 'A', 'xxWfPsblActNm_A' )">Type<img id="sortIMG.xxWfPsblActNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH2"  class="pClothBs"><a href="#" class="pSortCol" id="wfProcPk_A" onclick="columnSort( 'A', 'wfProcPk_A' )">Workflow ID<img id="sortIMG.wfProcPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH3"  class="pClothBs"><a href="#" class="pSortCol" id="wfProcDocId_A" onclick="columnSort( 'A', 'wfProcDocId_A' )">Source#<img id="sortIMG.wfProcDocId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH4"  class="pClothBs"><a href="#" class="pSortCol" id="wfProcNm_A" onclick="columnSort( 'A', 'wfProcNm_A' )">Process Name<img id="sortIMG.wfProcNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH5"  class="pClothBs"><a href="#" class="pSortCol" id="procStsNm_A" onclick="columnSort( 'A', 'procStsNm_A' )">Process Status<img id="sortIMG.procStsNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH6"  class="pClothBs"><a href="#" class="pSortCol" id="wfWrkItemNm_A" onclick="columnSort( 'A', 'wfWrkItemNm_A' )">Task Name<img id="sortIMG.wfWrkItemNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH7"  class="pClothBs"><a href="#" class="pSortCol" id="xxWfWrkItemStsNm_A" onclick="columnSort( 'A', 'xxWfWrkItemStsNm_A' )">Task Status<img id="sortIMG.xxWfWrkItemStsNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH8"  class="pClothBs"><a href="#" class="pSortCol" id="xxScrItem130Txt_V1" onclick="columnSort( 'A', 'xxScrItem130Txt_V1' )">Subject1<img id="sortIMG.xxScrItem130Txt_V1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH9"  class="pClothBs"><a href="#" class="pSortCol" id="xxScrItem130Txt_V2" onclick="columnSort( 'A', 'xxScrItem130Txt_V2' )">Subject2<img id="sortIMG.xxScrItem130Txt_V2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" id="xxScrItem130Txt_V3" onclick="columnSort( 'A', 'xxScrItem130Txt_V3' )">Subject3<img id="sortIMG.xxScrItem130Txt_V3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" id="xxScrItem130Txt_V4" onclick="columnSort( 'A', 'xxScrItem130Txt_V4' )">Subject4<img id="sortIMG.xxScrItem130Txt_V4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" id="xxScrItem130Txt_V5" onclick="columnSort( 'A', 'xxScrItem130Txt_V5' )">Subject5<img id="sortIMG.xxScrItem130Txt_V5" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" id="xxDtTm_AS" onclick="columnSort( 'A', 'xxDtTm_AS' )">Sent Date<img id="sortIMG.xxDtTm_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" id="xxDtTm_AD" onclick="columnSort( 'A', 'xxDtTm_AD' )">Due Date<img id="sortIMG.xxDtTm_AD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" id="xxDtTm_AE" onclick="columnSort( 'A', 'xxDtTm_AE' )">Closed Date<img id="sortIMG.xxDtTm_AE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" id="xxWfAsgFromNm_A" onclick="columnSort( 'A', 'xxWfAsgFromNm_A' )">From<img id="sortIMG.xxWfAsgFromNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" id="xxWfAsgToNm_A" onclick="columnSort( 'A', 'xxWfAsgToNm_A' )">To<img id="sortIMG.xxWfAsgToNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH18" class="pClothBs"><a href="#" class="pSortCol" id="xxBizCalTxt_A" onclick="columnSort( 'A', 'xxBizCalTxt_A' )">Age<img id="sortIMG.xxBizCalTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH19" class="pClothBs"><a href="#" class="pSortCol" id="actWfCondNm_A" onclick="columnSort( 'A', 'actWfCondNm_A' )">Last Action<img id="sortIMG.actWfCondNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH20" class="pClothBs"><a href="#" class="pSortCol" id="xxWfActOpNm_A" onclick="columnSort( 'A', 'xxWfActOpNm_A' )">Last Action By<img id="sortIMG.xxWfActOpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH21" class="pClothBs"><a href="#" class="pSortCol" id="wfCmntTxt_A" onclick="columnSort( 'A', 'wfCmntTxt_A' )">Comment<img id="sortIMG.wfCmntTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													</tr>
												</table>
											</div>
											<div id="rightTbl" style="width:1097; height:270; display:block; overflow:scroll; margin:0px; padding:0px;" >
												<table border="1" cellpadding="0" cellspacing="0" id="A" width="3947" style="table-layout:fixed;" >
													<col align="center" width="026"><!-- checkbox1 -->
													<col align="center" width="070"><!-- Type -->
													<col align="center" width="078"><!-- WorkflowID -->
													<col align="center" width="145"><!-- Source# -->
													<col align="center" width="200"><!-- process Name-->
													<col align="center" width="090"><!-- process status -->
													<col align="center" width="165"><!-- task Name-->
													<col align="center" width="150"><!-- Task status -->
													<col align="center" width="200"><!-- attribute1 -->
													<col align="center" width="200"><!-- attribute2 -->
													<col align="center" width="200"><!-- attribute3 -->
													<col align="center" width="200"><!-- attribute4 -->
													<col align="center" width="200"><!-- attribute5 -->
													<col align="center" width="190"><!-- Sent Date -->
													<col align="center" width="190"><!-- Due Date -->
													<col align="center" width="190"><!-- Closed Date -->
													<col align="center" width="200"><!-- From -->
													<col align="center" width="400"><!-- To -->
													<col align="center" width="180"><!-- Age -->
													<col align="center" width="180"><!-- Last Action -->
													<col align="center" width="200"><!-- Last Action By -->
													<col align="center" width="400"><!-- Comment -->
													<tbody>
														<%@ page import="business.servlet.NYEL8810.NYEL8810BMsg" %>
														<%@ page import="business.servlet.NYEL8810.NYEL8810_ABMsg" %>
														<%@ include file="../common/common.jsp" %>
														<% NYEL8810BMsg bMsg = (NYEL8810BMsg)databean.getEZDBMsg(); %>

														<% int i = 0; %>
														<ezf:row ezfHyo="A">
															<%NYEL8810_ABMsg abMsg = bMsg.A.no(i);%>
															<% if (i < 200) { i++; } %>
															<tr id="id_row{EZF_ROW_NUMBER}" height="32">
																<!-- CheckBox1 -->
																<td class=""><ezf:inputCheckBox name="xxWfAprChkFlg_A" ezfName="xxWfAprChkFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxWfAprChkFlg_A#{EZF_ROW_NUMBER}\" style=\"vertical-align: 0; height: 16px; width: 16px;\""/></td>
																<!-- Type -->
																<td class="">
																	<ezf:inputText name="xxWfPsblActNm_A" ezfName="xxWfPsblActNm_A" value="WWWWWWWWWXWWWWWWWWWXWWWWWWWWWX" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"border:none;background:none;\" size=\"8\" maxlength=\"30\""/>
																</td>
																<!-- Workflow ID -->
																<td class="">
																	<ezf:inputText name="wfProcPk_A" ezfName="wfProcPk_A" value="012345678901234567890123456" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"border:none;background:none;\" size=\"10\" maxlength=\"30\""/>
																</td>
																<!-- Source# -->
																<td class="">
																	<ezf:inputText name="wfProcDocId_A" ezfName="wfProcDocId_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"border:none;background:none;\" size=\"20\" maxlength=\"255\""/>
																</td>
																<!-- Process Name -->
																<td class="">
																	<ezf:textArea name="wfProcNm_A" ezfName="wfProcNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" rows=\"2\" cols=\"25\" style=\"border:none;background:none;overflow:auto\""/>
																</td>
																<!-- Process status -->
																<td class="">
																	<ezf:label name="procStsNm_A" ezfName="procStsNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"word-wrap: break-word;\""/>
																</td>
																<!-- Task Name -->
																<td class="">
	                                                                    <%if (abMsg.wfBizScrId_A.getValue() == null) {%>
	                                                                    <label>
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </label>
	                                                                    <%} else if ("NWAL1500".equals(abMsg.wfBizScrId_A.getValue())) {%>
	                                                                    <a href="#" onclick="sendServer('MoveWin_NWAL1500',{EZF_ROW_NUMBER}); return false;"  id="NWAL1500_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </a>
	                                                                    <%} else if ("NPAL0110".equals(abMsg.wfBizScrId_A.getValue())) {%>
	                                                                    <a href="#" onclick="sendServer('MoveWin_NPAL0110',{EZF_ROW_NUMBER}); return false;"  id="NPAL0110_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </a>
	                                                                    <%} else if ("NPAL1080".equals(abMsg.wfBizScrId_A.getValue())) {%>
	                                                                    <a href="#" onclick="sendServer('MoveWin_NPAL1080',{EZF_ROW_NUMBER}); return false;"  id="NPAL1080_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </a>
	                                                                    <%} else if ("NPAL1280".equals(abMsg.wfBizScrId_A.getValue())) {%>
	                                                                    <a href="#" onclick="sendServer('MoveWin_NPAL1280',{EZF_ROW_NUMBER}); return false;"  id="NPAL1280_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </a>
	                                                                    <%} else if ("NPBL0020".equals(abMsg.wfBizScrId_A.getValue())) {%>
	                                                                    <a href="#" onclick="sendServer('MoveWin_NPBL0020',{EZF_ROW_NUMBER}); return false;"  id="NPBL0020_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </a>
	                                                                    <%} else if ("NFCL2610".equals(abMsg.wfBizScrId_A.getValue())) {%>
	                                                                    <a href="#" onclick="sendServer('MoveWin_NFCL2610',{EZF_ROW_NUMBER}); return false;"  id="NFCL2610_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </a>
	                                                                    <%} else if ("NFBL1110".equals(abMsg.wfBizScrId_A.getValue())) {%>
	                                                                    <a href="#" onclick="sendServer('MoveWin_NFBL1110',{EZF_ROW_NUMBER}); return false;"  id="NFBL1110_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </a>
	                                                                    <%} else if ("NFBL2100".equals(abMsg.wfBizScrId_A.getValue())) {%>
	                                                                    <a href="#" onclick="sendServer('MoveWin_NFBL2100',{EZF_ROW_NUMBER}); return false;"  id="NFBL2100_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </a>
	                                                                    <%} else if ("NFDL0090".equals(abMsg.wfBizScrId_A.getValue())) {%>
	                                                                    <a href="#" onclick="sendServer('MoveWin_NFDL0090',{EZF_ROW_NUMBER}); return false;"  id="NFDL0090_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </a>
	                                                                    <%} else if ("NYEL8899".equals(abMsg.wfBizScrId_A.getValue())) {%>
	                                                                    <a href="#" onclick="sendServer('MoveWin_NYEL8899',{EZF_ROW_NUMBER}); return false;"  id="NYEL8899_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </a>
	                                                                    <%} else if ("NPAL1500".equals(abMsg.wfBizScrId_A.getValue())) {%>
	                                                                    <a href="#" onclick="sendServer('MoveWin_NPAL1500',{EZF_ROW_NUMBER}); return false;"  id="NPAL1500_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </a>
	                                                                    <%} else if ("NSAL1090".equals(abMsg.wfBizScrId_A.getValue())) {%>
	                                                                    <a href="#" onclick="sendServer('MoveWin_NSAL1090',{EZF_ROW_NUMBER}); return false;"  id="NSAL1090_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </a>
	                                                                    <%} else if ("NLCL0290".equals(abMsg.wfBizScrId_A.getValue())) {%>
	                                                                    <a href="#" onclick="sendServer('MoveWin_NLCL0290',{EZF_ROW_NUMBER}); return false;"  id="NLCL0290_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </a>
	                                                                    <%} else if ("NFCL0760".equals(abMsg.wfBizScrId_A.getValue())) {%>
	                                                                    <a href="#" onclick="sendServer('MoveWin_NFCL0760',{EZF_ROW_NUMBER}); return false;"  id="NFCL0760_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </a>
	                                                                    <%} else if ("NSAL0300".equals(abMsg.wfBizScrId_A.getValue())) {%>
	                                                                    <a href="#" onclick="sendServer('MoveWin_NSAL0300',{EZF_ROW_NUMBER}); return false;"  id="NSAL0300_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </a>
	                                                                    <%} else if ("NSAL0920".equals(abMsg.wfBizScrId_A.getValue())) {%>
	                                                                    <a href="#" onclick="sendServer('MoveWin_NSAL0920',{EZF_ROW_NUMBER}); return false;"  id="NSAL0920_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </a>
	                                                                    <%} else if ("EXTNE307T020".equals(abMsg.wfBizScrId_A.getValue())) {%>
	                                                                    <a href="<%= getCustomAppURL("EXTNE307T020") %>?fsr=<%= abMsg.wfBizAttrbTxt_A1.getValue() %>" target="_blank" onclick="f_open_window_max(this.href,'_blank');return false;">
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </a>
	                                                                    <%} else {%>
	                                                                    <label>
	                                                                        <%= abMsg.wfWrkItemNm_A.getValue() %>
	                                                                    </label>
	                                                                    <%}%>
																</td>
																<!-- Task status -->
																<td class="">
																	<ezf:label name="xxWfWrkItemStsNm_A" ezfName="xxWfWrkItemStsNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"word-wrap: break-word;\""/>
																</td>
																<!-- attribute1 -->
																<td class="">
                                                                        <%if (abMsg.wfBizScrId_A1.getValue() == null) {%>
                                                                        <textarea class="pPro " style="border:none;background:none;overflow:auto;height:2.5em;vertical-align:middle" cols="25" name="xxScrItem130Txt_V1" readonly><%= abMsg.xxScrItem130Txt_V1.getValue() %></textarea>
                                                                        <%} else {%>
                                                                        <textarea class="pPro " style="border:none;background:none;overflow:auto;height:2.5em;vertical-align:middle" cols="25" name="xxScrItem130Txt_V1" readonly><%= abMsg.xxScrItem130Txt_V1.getValue() %> </textarea>
                                                                        <%}%>
																</td>
																<!-- attribute2 -->
																<td class="">
                                                                        <%if (abMsg.wfBizScrId_A2.getValue() == null) {%>
                                                                        <textarea class="pPro " style="border:none;background:none;overflow:auto;height:2.5em;vertical-align:middle" cols="25" name="xxScrItem130Txt_V2" readonly><%= abMsg.xxScrItem130Txt_V2.getValue() %></textarea>
                                                                        <%} else {%>
                                                                        <textarea class="pPro " style="border:none;background:none;overflow:auto;height:2.5em;vertical-align:middle" cols="25" name="xxScrItem130Txt_V2" readonly><%= abMsg.xxScrItem130Txt_V2.getValue() %></textarea>
                                                                        <%}%>
																</td>
																<!-- attribute3 -->
																<td class="">
                                                                        <%if (abMsg.wfBizScrId_A3.getValue() == null) {%>
                                                                        <textarea class="pPro " style="border:none;background:none;overflow:auto;height:2.5em;vertical-align:middle" cols="25" name="xxScrItem130Txt_V3" readonly><%= abMsg.xxScrItem130Txt_V3.getValue() %></textarea>
                                                                        <%} else {%>
                                                                        <textarea class="pPro " style="border:none;background:none;overflow:auto;height:2.5em;vertical-align:middle" cols="25" name="xxScrItem130Txt_V3" readonly><%= abMsg.xxScrItem130Txt_V3.getValue() %></textarea>
                                                                        <%}%>
																</td>
																<!-- attribute4 -->
																<td class="">
                                                                        <%if (abMsg.wfBizScrId_A4.getValue() == null) {%>
                                                                        <textarea class="pPro " style="border:none;background:none;overflow:auto;height:2.5em;vertical-align:middle" cols="25" name="xxScrItem130Txt_V4" readonly><%= abMsg.xxScrItem130Txt_V4.getValue() %></textarea>
                                                                        <%} else if ("NFDL0020".equals(abMsg.wfBizScrId_A4.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWinSbj4_NFDL0020',{EZF_ROW_NUMBER}); return false;"  id="NFDL0020_LK_S4#{EZF_ROW_NUMBER}" ezfanchortext="">
                                                                            <textarea class="pPro " style="color:00f;text-decoration:underline;cursor:pointer;border:none;background:none;overflow:auto;height:2.5em;vertical-align:middle" cols="25" name="xxScrItem130Txt_V4" readonly onmouseover="this.style.color='#f39';" onmouseout="this.style.color='#00f';" ><%= abMsg.xxScrItem130Txt_V4.getValue() %></textarea>
                                                                        </a>
                                                                        <%} else {%>
                                                                        <textarea class="pPro " style="border:none;background:none;overflow:auto;height:2.5em;vertical-align:middle" cols="25" name="xxScrItem130Txt_V4" readonly><%= abMsg.xxScrItem130Txt_V4.getValue() %></textarea>
                                                                        <%}%>
																</td>
																<!-- attribute5 -->
																<td class="">
                                                                        <%if (abMsg.wfBizScrId_A5.getValue() == null) {%>
                                                                        <textarea class="pPro " style="border:none;background:none;overflow:auto;height:2.5em;vertical-align:middle" cols="25" name="xxScrItem130Txt_V5" readonly><%= abMsg.xxScrItem130Txt_V5.getValue() %></textarea>
                                                                        <%} else {%>
                                                                        <textarea class="pPro " style="border:none;background:none;overflow:auto;height:2.5em;vertical-align:middle" cols="25" name="xxScrItem130Txt_V5" readonly><%= abMsg.xxScrItem130Txt_V5.getValue() %></textarea>
                                                                        <%}%>
																</td>
																<!-- Sent Date -->
																<td class="">
																	<ezf:label name="xxDtTm_AS" ezfName="xxDtTm_AS" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<!-- Due Date -->
																<td class="">
																	<ezf:label name="xxDtTm_AD" ezfName="xxDtTm_AD" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<!-- Closed Date -->
																<td class="">
																	<ezf:label name="xxDtTm_AE" ezfName="xxDtTm_AE" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<!-- From -->
																<td class="">
																	<ezf:textArea name="xxWfAsgFromNm_A" ezfName="xxWfAsgFromNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" rows=\"2\" cols=\"25\" style=\"border:none;background:none;overflow:auto;vertical-align:middle\""/>
																</td>
																<!-- To -->
																<td class="">
																	<ezf:textArea name="xxWfAsgToNm_A" ezfName="xxWfAsgToNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" rows=\"2\" cols=\"50\" style=\"border:none;background:none;overflow:auto;vertical-align:middle\""/>
																</td>
																<!-- Age -->
																<td class="">
																	<ezf:label name="xxBizCalTxt_A" ezfName="xxBizCalTxt_A" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<!-- Last Action -->
																<td class="">
																	<ezf:label name="actWfCondNm_A" ezfName="actWfCondNm_A" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<!-- Last Action By -->
																<td class="">
																	<ezf:textArea name="xxWfActOpNm_A" ezfName="xxWfActOpNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" rows=\"2\" cols=\"25\" style=\"border:none;background:none;overflow:auto;vertical-align:middle\""/>
																</td>
																<!-- Comment -->
																<td class="">
																	<ezf:textArea name="wfCmntTxt_A" ezfName="wfCmntTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" rows=\"2\" cols=\"50\" style=\"border:none;background:none;overflow:auto;vertical-align:middle\""/>
																</td>
															</tr>
														</ezf:row>
													</tbody>
												</table>
											</div>
										</div> <!-- right table -->
									</td>
								</tr>
							</table>
						</div>
						<script type="text/javascript" defer>
							 S21TableUI.initialize("parentBoxA", "AHEAD", "A", 0, true);
						</script>
						<div>
							<table border="0" cellpadding="0" cellspacing="0" style="margin-left: 3;margin-bottom:-5px">
								<col width="80">
								<col width="830">
								<col width="160">
								<tr>
									<td class="stab">Comment</td>
									<td>
										<ezf:textArea name="wfCmntTxt" ezfName="wfCmntTxt" otherAttr=" rows=\"2\" cols=\"100\""/>
									</td>
									<td  align="right">
										<ezf:inputButton name="MoveWin_ProcessStatus" value="Status" htmlClass="pBtn6" otherAttr=" id=\"Status\""/>
										<ezf:inputButton name="MoveWin_Detail" value="Detail" htmlClass="pBtn6" otherAttr=" id=\"Detail\""/>
									</td>
								</tr>
							</table>
							<hr style="height: 0px;" cellpadding="0"  cellspacing="0"  style="margin-bottom:-5px">
							<table border="0" cellpadding="0" cellspacing="0" style="margin-left: 3;">
								<tr>
									<td class="stab" width="80"><ezf:anchor name="asgCd_LK" ezfName="asgCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CmnBigAssignee" >
										Assignee</ezf:anchor></td>
									<td class="stab" width="525">
										<ezf:inputText name="xxWfAsgCd" ezfName="xxWfAsgCd" otherAttr=" size=\"7\" maxlength=\"7\" ezftoupper=\"\""/>
										<ezf:inputButton name="Get_AssigneeName" value=">>" htmlClass="pBtn1" otherAttr=" id=\"Get_AssigneeName\""/>
										<ezf:inputText name="xxWfAsgNm" ezfName="xxWfAsgNm" value="xxxxxxxxx0xxxxxxxxx0xxxxxxxxx0xxxxxxxxx0xxxx5xx" otherAttr=" size=\"60\" maxlength=\"60\""/>
									</td>
									<td>
										<ezf:inputButton name="ReAssign" value="Reassign" htmlClass="pBtn6" otherAttr=" id=\"ReAssign\""/>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</td>
		</tr>
	</table>
</center>
<script>
function f_open_window_max( aURL, aWinName )
{
   var wOpen;
   var sOptions;

   sOptions = 'status=yes,menubar=no,scrollbars=yes,resizable=yes,toolbar=no';
   sOptions = sOptions + ',width=' + (screen.availWidth - 17).toString();
   sOptions = sOptions + ',height=' + (screen.availHeight - 65).toString();
   sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';

   wOpen = window.open( '', aWinName, sOptions );
   wOpen.location = aURL;
   wOpen.focus();
   wOpen.moveTo( 0, 0 );
   //wOpen.resizeTo( screen.availWidth, screen.availHeight  );
   return wOpen;
}
</script>

<%-- **** Task specific area ends here **** --%>
