<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160615155326 --%>
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
			<input type="hidden" name="pageID" value="NFAL0210Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Manual Journal Entry Inquiry">
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
										<li title="Actual Counters for Interface" class="pTab_ON"><a href="#">Man Jrnl Inq</a></li>
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
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width= "5">
									<col width= "90">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "30">
									<col width= "20">
									<col width= "70">
									<tr height="24">
										<td></td>
										<td class="stab">Journal Name(*)</td>
										<td colspan="7"><ezf:inputText name="manJrnlNm" ezfName="manJrnlNm" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
										<td></td>
										<td colspan="2"class="stab">GL Period</td>
										<td colspan="3"><ezf:inputText name="glPerNm" ezfName="glPerNm" value="XXXXXXXXXX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
										<td></td>
										<td colspan="3"class="stab">Accounting Date</td>
										<td colspan="4">
											<ezf:inputText name="xxFromDt" ezfName="xxFromDt" value="01/01/2016" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('xxFromDt', 4);" id="xxFromDt">
										</td>
										<td class="stab">-</td>
										<td colspan="4">
											<ezf:inputText name="xxToDt" ezfName="xxToDt" value="01/01/2016" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('xxToDt', 4);" id="xxToDt">
										</td>
									</tr>
									<tr height="24">
										<td></td>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_JrnlCatgSearch" >Category</ezf:anchor></td>
										<td colspan="9" class="stab">
											<ezf:inputText name="jrnlCatgCd" ezfName="jrnlCatgCd" value="999" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>
											<ezf:inputButton name="CatgSearch" value=">>" htmlClass="pBtn0" />
											<ezf:inputText name="jrnlCatgDescTxt" ezfName="jrnlCatgDescTxt" value="12345678901234567890123456" otherAttr=" size=\"26\" maxlength=\"26\""/>
										</td>
									</tr>
									<tr height="24">
										<td></td>
										<td class="stab">GL Send</td>
										<td colspan="4">
											<ezf:select name="glSendCpltFlg_SV" ezfName="glSendCpltFlg_SV" ezfBlank="1" ezfCodeName="glSendCpltFlg_CD" ezfDispName="xxScrItem20Txt_D1" otherAttr=" style=\"width:110px;\""/>
										</td>
										<td></td>
										<td colspan="2" class="stab">Reversed</td>
										<td colspan="4">
											<ezf:select name="rvslCpltFlg_SV" ezfName="rvslCpltFlg_SV" ezfBlank="1" ezfCodeName="rvslCpltFlg_CD" ezfDispName="xxScrItem20Txt_D2" otherAttr=" style=\"width:110px;\""/>
										</td>
										<td></td>
										<td colspan="2" class="stab">Complete</td>
										<td colspan="5">
											<ezf:select name="manJrnlCpltFlg_SV" ezfName="manJrnlCpltFlg_SV" ezfBlank="1" ezfCodeName="manJrnlCpltFlg_CD" ezfDispName="xxScrItem20Txt_D3" otherAttr=" style=\"width:140px;\""/>
										</td>
										<td></td>
										<td colspan="3" class="stab">Auto Reversal</td>
										<td colspan="5">
											<ezf:select name="autoRvslFlg_SV" ezfName="autoRvslFlg_SV" ezfBlank="1" ezfCodeName="autoRvslFlg_CD" ezfDispName="xxScrItem20Txt_D4" otherAttr=" style=\"width:150px;\""/>
										</td>
										<td></td>
										<td colspan="3"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<hr>
					<!-- ######################################## DETAIL HEADER ######################################## -->
 					<table border="0" style="table-layout:fixed;width=96%;margin-left:20;">
						<col width="280">	<!-- PreferredView -->
						<col width="350">	<!-- Paging -->
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
									<table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 27px;">
										<colgroup>
											<col align="center" width="54">
											<col align="right" width="40">
											<col align="center" width="16">
											<col align="right" width="40">
											<col align="center" width="16">
											<col align="center" width="20">
											<col width="40">
											<col width="10">
											<col>
											<col width="1">
											<col>
											<col width="20">
										</colgroup>
										<tbody>
										<tr>
											<td class="stab"><label>Showing</label></td>
											<td class="pOut">1</td>
											<td class="stab"><label>/</label></td>
											<td class="pOut">15</td>
											<td class="stab"><label>Page</label></td>
											<td>&nbsp;</td>
											<td><input onclick="tablePagenation(this.name, 'A')" disabled id="btnPrev" class="pBtn3" type="button" value="Jump" name="PagePrev"></td>
											<td>&nbsp;</td>
											<td><input onclick="tablePagenation(this.name, 'A')" disabled id="btnPrev" class="pBtn3" type="button" value="Prev" name="PagePrev"></td>
											<td></td>
											<td><INPUT onclick="tablePagenation(this.name, 'A')" disabled id="btnNext" class="pBtn3" type="button" value="Next" name="PageNext"></td>
											<td></td>
										</tr>
										</tbody>
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
										<div id='leftTbl' style="float:left; display:block; height:370px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
										</div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id='rightTblHead' style="width:1080px; display:block; overflow:hidden; margin:0px;padding:0p x;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1080px" style="margin-right:20px">
												<col width="30" align="center">
												<col width="240" align="center">
												<col width="210" align="center">
												<col width="100" align="center">
												<col width="87" align="center">
												<col width="100" align="center">
												<col width="80" align="center">
												<col width="87" align="center">
												<col width="60" align="center">
												<col width="80" align="center">
												<tr height="24px" valign="middle">
													<td id="AH0"  class="pClothBs colFix"></td>
													<td id="AH1"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'manJrnlNm_A')">Journal Name</a><img id="sortIMG.manJrnlNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'jrnlCatgDescTxt_A')">Category</a><img id="sortIMG.jrnlCatgDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'glPerNm_A')">GL Period</a><img id="sortIMG.glPerNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'glDt_A')">Acct Date</a><img id="sortIMG.glDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'glSendCpltFlg_A')">GL Send Status</a><img id="sortIMG.glSendCpltFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rvslCpltFlg_A')">Rvsl Status</a><img id="sortIMG.rvslCpltFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'actlRvslDt_A')">Rvsl Date</a><img id="sortIMG.actlRvslDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'manJrnlCpltFlg_A')">Comp</a><img id="sortIMG.manJrnlCpltFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'autoRvslFlg_A')">Auto Rvsl</a><img id="sortIMG.autoRvslFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												</tr>
											</table>
										</div><!-- 'rightTblHead' -->
										<div id='rightTbl' style="width:1080; height:370px; display:block; overflow-x:hidden; overflow-y:hidden; margin:0px; padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1080px" >
												<col width="30" align="center">
												<col width="240" align="center">
												<col width="210" align="center">
												<col width="100" align="center">
												<col width="87" align="center">
												<col width="100" align="center">
												<col width="80" align="center">
												<col width="87" align="center">
												<col width="60" align="center">
												<col width="80" align="center">
												<ezf:row ezfHyo="A">
												<tr height="24px">
													<td><ezf:inputRadio name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"xxRadioBtn{EZF_ROW_NUMBER}\""/></td>
													<td align="left"><ezf:inputText name="manJrnlNm_A" ezfName="manJrnlNm_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left"><ezf:inputText name="jrnlCatgDescTxt_A" ezfName="jrnlCatgDescTxt_A" value="DISPATCH CREDIT & REBILL12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"26\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left"><ezf:inputText name="glPerNm_A" ezfName="glPerNm_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left"><ezf:inputText name="glDt_A" ezfName="glDt_A" value="01/01/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left"><ezf:inputText name="glSendCpltFlg_A" ezfName="glSendCpltFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"1\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left"><ezf:inputText name="rvslCpltFlg_A" ezfName="rvslCpltFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"1\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left"><ezf:inputText name="actlRvslDt_A" ezfName="actlRvslDt_A" value="01/01/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left"><ezf:inputText name="manJrnlCpltFlg_A" ezfName="manJrnlCpltFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"1\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left"><ezf:inputText name="autoRvslFlg_A" ezfName="autoRvslFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"1\" style=\"border:none; background-color:transparent;\""/></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												<tr height="24px">
													<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" id="xxRadioBtn{EZF_ROW_NUMBER}" /></td>
													<td align="left"><input type="text" class="pPro" size="30" name="manJrnlNm_A"       ezfName="manJrnlNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="123456789012345678901234567890"></td>
													<td align="left"><input type="text" class="pPro" size="26" name="jrnlCatgDescTxt_A" ezfName="jrnlCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="DISPATCH CREDIT & REBILL12"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glPerNm_A"         ezfName="glPerNm_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="1234567890"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glDt_A"            ezfName="glDt_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="glSendCpltFlg_A"   ezfName="glSendCpltFlg_A"   ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="rvslCpltFlg_A"     ezfName="rvslCpltFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="actlRvslDt_A"      ezfName="actlRvslDt_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="manJrnlCpltFlg_A"  ezfName="manJrnlCpltFlg_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="autoRvslFlg_A"     ezfName="autoRvslFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
												</tr>
												<tr height="24px">
													<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" id="xxRadioBtn{EZF_ROW_NUMBER}" /></td>
													<td align="left"><input type="text" class="pPro" size="30" name="manJrnlNm_A"       ezfName="manJrnlNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="123456789012345678901234567890"></td>
													<td align="left"><input type="text" class="pPro" size="26" name="jrnlCatgDescTxt_A" ezfName="jrnlCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="DISPATCH CREDIT & REBILL12"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glPerNm_A"         ezfName="glPerNm_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="1234567890"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glDt_A"            ezfName="glDt_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="glSendCpltFlg_A"   ezfName="glSendCpltFlg_A"   ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="rvslCpltFlg_A"     ezfName="rvslCpltFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="actlRvslDt_A"      ezfName="actlRvslDt_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="manJrnlCpltFlg_A"  ezfName="manJrnlCpltFlg_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="autoRvslFlg_A"     ezfName="autoRvslFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
												</tr>
												<tr height="24px">
													<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" id="xxRadioBtn{EZF_ROW_NUMBER}" /></td>
													<td align="left"><input type="text" class="pPro" size="30" name="manJrnlNm_A"       ezfName="manJrnlNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="123456789012345678901234567890"></td>
													<td align="left"><input type="text" class="pPro" size="26" name="jrnlCatgDescTxt_A" ezfName="jrnlCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="DISPATCH CREDIT & REBILL12"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glPerNm_A"         ezfName="glPerNm_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="1234567890"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glDt_A"            ezfName="glDt_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="glSendCpltFlg_A"   ezfName="glSendCpltFlg_A"   ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="rvslCpltFlg_A"     ezfName="rvslCpltFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="actlRvslDt_A"      ezfName="actlRvslDt_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="manJrnlCpltFlg_A"  ezfName="manJrnlCpltFlg_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="autoRvslFlg_A"     ezfName="autoRvslFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
												</tr>
												<tr height="24px">
													<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" id="xxRadioBtn{EZF_ROW_NUMBER}" /></td>
													<td align="left"><input type="text" class="pPro" size="30" name="manJrnlNm_A"       ezfName="manJrnlNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="123456789012345678901234567890"></td>
													<td align="left"><input type="text" class="pPro" size="26" name="jrnlCatgDescTxt_A" ezfName="jrnlCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="DISPATCH CREDIT & REBILL12"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glPerNm_A"         ezfName="glPerNm_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="1234567890"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glDt_A"            ezfName="glDt_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="glSendCpltFlg_A"   ezfName="glSendCpltFlg_A"   ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="rvslCpltFlg_A"     ezfName="rvslCpltFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="actlRvslDt_A"      ezfName="actlRvslDt_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="manJrnlCpltFlg_A"  ezfName="manJrnlCpltFlg_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="autoRvslFlg_A"     ezfName="autoRvslFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
												</tr>
												<tr height="24px">
													<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" id="xxRadioBtn{EZF_ROW_NUMBER}" /></td>
													<td align="left"><input type="text" class="pPro" size="30" name="manJrnlNm_A"       ezfName="manJrnlNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="123456789012345678901234567890"></td>
													<td align="left"><input type="text" class="pPro" size="26" name="jrnlCatgDescTxt_A" ezfName="jrnlCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="DISPATCH CREDIT & REBILL12"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glPerNm_A"         ezfName="glPerNm_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="1234567890"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glDt_A"            ezfName="glDt_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="glSendCpltFlg_A"   ezfName="glSendCpltFlg_A"   ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="rvslCpltFlg_A"     ezfName="rvslCpltFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="actlRvslDt_A"      ezfName="actlRvslDt_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="manJrnlCpltFlg_A"  ezfName="manJrnlCpltFlg_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="autoRvslFlg_A"     ezfName="autoRvslFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
												</tr>
												<tr height="24px">
													<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" id="xxRadioBtn{EZF_ROW_NUMBER}" /></td>
													<td align="left"><input type="text" class="pPro" size="30" name="manJrnlNm_A"       ezfName="manJrnlNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="123456789012345678901234567890"></td>
													<td align="left"><input type="text" class="pPro" size="26" name="jrnlCatgDescTxt_A" ezfName="jrnlCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="DISPATCH CREDIT & REBILL12"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glPerNm_A"         ezfName="glPerNm_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="1234567890"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glDt_A"            ezfName="glDt_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="glSendCpltFlg_A"   ezfName="glSendCpltFlg_A"   ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="rvslCpltFlg_A"     ezfName="rvslCpltFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="actlRvslDt_A"      ezfName="actlRvslDt_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="manJrnlCpltFlg_A"  ezfName="manJrnlCpltFlg_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="autoRvslFlg_A"     ezfName="autoRvslFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
												</tr>
												<tr height="24px">
													<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" id="xxRadioBtn{EZF_ROW_NUMBER}" /></td>
													<td align="left"><input type="text" class="pPro" size="30" name="manJrnlNm_A"       ezfName="manJrnlNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="123456789012345678901234567890"></td>
													<td align="left"><input type="text" class="pPro" size="26" name="jrnlCatgDescTxt_A" ezfName="jrnlCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="DISPATCH CREDIT & REBILL12"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glPerNm_A"         ezfName="glPerNm_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="1234567890"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glDt_A"            ezfName="glDt_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="glSendCpltFlg_A"   ezfName="glSendCpltFlg_A"   ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="rvslCpltFlg_A"     ezfName="rvslCpltFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="actlRvslDt_A"      ezfName="actlRvslDt_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="manJrnlCpltFlg_A"  ezfName="manJrnlCpltFlg_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="autoRvslFlg_A"     ezfName="autoRvslFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
												</tr>
												<tr height="24px">
													<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" id="xxRadioBtn{EZF_ROW_NUMBER}" /></td>
													<td align="left"><input type="text" class="pPro" size="30" name="manJrnlNm_A"       ezfName="manJrnlNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="123456789012345678901234567890"></td>
													<td align="left"><input type="text" class="pPro" size="26" name="jrnlCatgDescTxt_A" ezfName="jrnlCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="DISPATCH CREDIT & REBILL12"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glPerNm_A"         ezfName="glPerNm_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="1234567890"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glDt_A"            ezfName="glDt_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="glSendCpltFlg_A"   ezfName="glSendCpltFlg_A"   ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="rvslCpltFlg_A"     ezfName="rvslCpltFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="actlRvslDt_A"      ezfName="actlRvslDt_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="manJrnlCpltFlg_A"  ezfName="manJrnlCpltFlg_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="autoRvslFlg_A"     ezfName="autoRvslFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
												</tr>
												<tr height="24px">
													<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" id="xxRadioBtn{EZF_ROW_NUMBER}" /></td>
													<td align="left"><input type="text" class="pPro" size="30" name="manJrnlNm_A"       ezfName="manJrnlNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="123456789012345678901234567890"></td>
													<td align="left"><input type="text" class="pPro" size="26" name="jrnlCatgDescTxt_A" ezfName="jrnlCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="DISPATCH CREDIT & REBILL12"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glPerNm_A"         ezfName="glPerNm_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="1234567890"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glDt_A"            ezfName="glDt_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="glSendCpltFlg_A"   ezfName="glSendCpltFlg_A"   ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="rvslCpltFlg_A"     ezfName="rvslCpltFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="actlRvslDt_A"      ezfName="actlRvslDt_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="manJrnlCpltFlg_A"  ezfName="manJrnlCpltFlg_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="autoRvslFlg_A"     ezfName="autoRvslFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
												</tr>
												<tr height="24px">
													<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" id="xxRadioBtn{EZF_ROW_NUMBER}" /></td>
													<td align="left"><input type="text" class="pPro" size="30" name="manJrnlNm_A"       ezfName="manJrnlNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="123456789012345678901234567890"></td>
													<td align="left"><input type="text" class="pPro" size="26" name="jrnlCatgDescTxt_A" ezfName="jrnlCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="DISPATCH CREDIT & REBILL12"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glPerNm_A"         ezfName="glPerNm_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="1234567890"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glDt_A"            ezfName="glDt_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="glSendCpltFlg_A"   ezfName="glSendCpltFlg_A"   ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="rvslCpltFlg_A"     ezfName="rvslCpltFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="actlRvslDt_A"      ezfName="actlRvslDt_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="manJrnlCpltFlg_A"  ezfName="manJrnlCpltFlg_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="autoRvslFlg_A"     ezfName="autoRvslFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
												</tr>
												<tr height="24px">
													<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" id="xxRadioBtn{EZF_ROW_NUMBER}" /></td>
													<td align="left"><input type="text" class="pPro" size="30" name="manJrnlNm_A"       ezfName="manJrnlNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="123456789012345678901234567890"></td>
													<td align="left"><input type="text" class="pPro" size="26" name="jrnlCatgDescTxt_A" ezfName="jrnlCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="DISPATCH CREDIT & REBILL12"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glPerNm_A"         ezfName="glPerNm_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="1234567890"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glDt_A"            ezfName="glDt_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="glSendCpltFlg_A"   ezfName="glSendCpltFlg_A"   ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="rvslCpltFlg_A"     ezfName="rvslCpltFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="actlRvslDt_A"      ezfName="actlRvslDt_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="manJrnlCpltFlg_A"  ezfName="manJrnlCpltFlg_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="autoRvslFlg_A"     ezfName="autoRvslFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
												</tr>
												<tr height="24px">
													<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" id="xxRadioBtn{EZF_ROW_NUMBER}" /></td>
													<td align="left"><input type="text" class="pPro" size="30" name="manJrnlNm_A"       ezfName="manJrnlNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="123456789012345678901234567890"></td>
													<td align="left"><input type="text" class="pPro" size="26" name="jrnlCatgDescTxt_A" ezfName="jrnlCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="DISPATCH CREDIT & REBILL12"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glPerNm_A"         ezfName="glPerNm_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="1234567890"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glDt_A"            ezfName="glDt_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="glSendCpltFlg_A"   ezfName="glSendCpltFlg_A"   ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="rvslCpltFlg_A"     ezfName="rvslCpltFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="actlRvslDt_A"      ezfName="actlRvslDt_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="manJrnlCpltFlg_A"  ezfName="manJrnlCpltFlg_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="autoRvslFlg_A"     ezfName="autoRvslFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
												</tr>
												<tr height="24px">
													<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" id="xxRadioBtn{EZF_ROW_NUMBER}" /></td>
													<td align="left"><input type="text" class="pPro" size="30" name="manJrnlNm_A"       ezfName="manJrnlNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="123456789012345678901234567890"></td>
													<td align="left"><input type="text" class="pPro" size="26" name="jrnlCatgDescTxt_A" ezfName="jrnlCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="DISPATCH CREDIT & REBILL12"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glPerNm_A"         ezfName="glPerNm_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="1234567890"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glDt_A"            ezfName="glDt_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="glSendCpltFlg_A"   ezfName="glSendCpltFlg_A"   ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="rvslCpltFlg_A"     ezfName="rvslCpltFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="actlRvslDt_A"      ezfName="actlRvslDt_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="manJrnlCpltFlg_A"  ezfName="manJrnlCpltFlg_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="autoRvslFlg_A"     ezfName="autoRvslFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
												</tr>
												<tr height="24px">
													<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" id="xxRadioBtn{EZF_ROW_NUMBER}" /></td>
													<td align="left"><input type="text" class="pPro" size="30" name="manJrnlNm_A"       ezfName="manJrnlNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="123456789012345678901234567890"></td>
													<td align="left"><input type="text" class="pPro" size="26" name="jrnlCatgDescTxt_A" ezfName="jrnlCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="DISPATCH CREDIT & REBILL12"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glPerNm_A"         ezfName="glPerNm_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="1234567890"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="glDt_A"            ezfName="glDt_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="glSendCpltFlg_A"   ezfName="glSendCpltFlg_A"   ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="rvslCpltFlg_A"     ezfName="rvslCpltFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="10" name="actlRvslDt_A"      ezfName="actlRvslDt_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="01/01/2016"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="manJrnlCpltFlg_A"  ezfName="manJrnlCpltFlg_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
													<td align="left"><input type="text" class="pPro" size="1"  name="autoRvslFlg_A"     ezfName="autoRvslFlg_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;" value="Y"></td>
												</tr>
												</ezf:skip>
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
	<table width ="95%">
		<tr>
			<td align="right">
				<ezf:inputButton name="MoveWin_Detail" value="Detail" htmlClass="pBtn8" />
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript" defer>
   S21TableUI.initialize("parentBoxA", "AHEAD", "A", 0, true);
</script>

<%-- **** Task specific area ends here **** --%>
