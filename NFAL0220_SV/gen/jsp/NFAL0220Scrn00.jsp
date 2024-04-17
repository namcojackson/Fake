<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160928163217 --%>
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
			<input type="hidden" name="pageID" value="NFAL0220Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Manual Journal Entry">
			<!-- Upper Tab Start -->
			
            <!-- include S21BusinessProcessTAB -->
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
            <ezf:skip>
            <div class="pTab_HEAD">
                <ul>
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="96%">
                                <div>
                                    <li title="Manual Journal Entry" class="pTab_ON"><a href="#">Man Jrnl Entry</a></li>
                                </div>
                            </td>
                        </tr>
                    </table>
                </ul>
            </div>
            </ezf:skip>
<!-- Application Parts Start -->
			<div class="pTab_BODY">
			<table width="96%" height="94%" align="center" border="0" cellspacing="0">
				<tr valign="top">
					<td>
						<table width="1090" align="center" border="0" cellspacing="0">
								<col width="220">
								<col width="350">
								<col width="80">
								<col width="150">
								<col width="30">
								<col width="200">
							<tr height="28px">
								<td align="left" class="stab">Journal Name</td>
								<td align="left" class="stab">
									<ezf:inputText name="manJrnlNm" ezfName="manJrnlNm" value="S21 Accounting AR" otherAttr=" style=\"height:20px;width:254px;font-size:9pt;\""/>
								</td>
								<td align="left" class="stab">GL Send</td>
								<td align="left" ><ezf:inputCheckBox name="glSendCpltFlg" ezfName="glSendCpltFlg" value="Y" /></td>
								<td align="center"><ezf:inputCheckBox name="manJrnlCpltFlg" ezfName="manJrnlCpltFlg" value="Y" /></td>
								<td align="left" class="stab">Complete</td>
							</tr>
							<tr height="28px">
								<td align="left" class="stab">Description</td>
								<td align="left" class="stab" rowspan="2">
									<ezf:textArea name="manJrnlDescTxt" ezfName="manJrnlDescTxt" otherAttr=" rows=\"4\" cols=\"33\" maxlength=\"150\" ezftoupper=\"\""/>
								</td>
								<td align="left" class="stab">Reversal Date</td>
								<td align="left" class="stab">
									<ezf:inputText name="rsvdRvslDt" ezfName="rsvdRvslDt" otherAttr=" size=\"10\" maxlength=\"10\" id=\"rsvdRvslDt\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rsvdRvslDt', 4);">
								</td>
								<td align="center"><ezf:inputCheckBox name="autoRvslFlg" ezfName="autoRvslFlg" value="Y" /></td>
								<td align="left" class="stab">Auto Reversal</td>
							</tr>
							<tr height="28px">
								<td></td>
								<td align="left" class="stab">Reversed</td>
								<td><ezf:inputCheckBox name="rvslCpltFlg" ezfName="rvslCpltFlg" value="Y" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_C" ezfName="xxChkBox_C" value="Y" /></td>
								<td align="left" class="stab">Cancel</td>
							</tr>
							<tr height="28px">
								<td align="left" class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_JrnlCatgSearch" >Category</ezf:anchor></td>
								<td align="left" class="stab">
								 	<ezf:inputText name="jrnlCatgCd" ezfName="jrnlCatgCd" value="999" otherAttr=" style=\"height:20px;width:30px;font-size:9pt;\""/>
								 	<ezf:inputButton name="CatgSearchBtn" value=">>" htmlClass="pBtn0" />
								 	<ezf:inputText name="jrnlCatgDescTxt" ezfName="jrnlCatgDescTxt" value="XXXXXXXXX1XXXXXXXXX2123456" otherAttr=" style=\"height:20px;width:189px;font-size:9pt;\""/>
								</td>
								<td align="left"><ezf:inputButton name="CopyJournal" value="Copy Journal" htmlClass="pBtn6" /></td>
								<td align="left"><ezf:inputButton name="Reverse" value="Reverse" htmlClass="pBtn6" /></td>
								<td></td>
								<td></td>
							</tr>
							<tr height="28px">
								<td align="left" class="stab">GL Period</td>
								<td align="left" class="stab">
									<ezf:inputText name="glPerNm" ezfName="glPerNm" value="MAY-16" otherAttr=" style=\"width:100px;font-size:9pt;\" ezftoupper=\"\""/>
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr height="28px">
								<td align="left" class="stab">Accounting Date</td>
								<td align="left" class="stab">
									<ezf:inputText name="glDt" ezfName="glDt" otherAttr=" size=\"10\" maxlength=\"10\" id=\"glDt\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('glDt', 4);">
								</td>
								<td colspan="4">
									<table width="700" align="center" border="0" cellspacing="0">
										<col width="75">
										<col width="150">
										<col width="80">
										<col width="150">
										<td align="left" class="stab">Upload File</td>
										<td align="left" class="stab">
											<ezf:inputFile name="xxFileData" ezfName="xxFileData" otherAttr=" size=\"40\" maxlength=\"9999\""/>
											<td align="left"><ezf:inputButton name="UploadFile" value="Upload File" htmlClass="pBtn6" /></td>
										</td>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TemplateDownload" >Template Download</ezf:anchor></td>
									</table>
								</td>
							</tr>
						</table>
						<hr>
						<table width="1090" border="0" cellspacing="0">
							<col width="90">
							<col width="90">
							<col width="20">
							<col width="180">
							<col width="610">
							<tr height="30px">
								<td align="left" class="stab">
									<ezf:inputButton name="AddLine" value="Add Line" htmlClass="pBtn5" otherAttr=" style=\"width:90px;height:20px\""/>
								</td>
								<td align="left" class="stab">
									<ezf:inputButton name="Delete" value="Delete" htmlClass="pBtn5" otherAttr=" style=\"width:90px;height:20px\""/>
								</td>
								<td align="left"></td>
								<td align="left" class="stab"></td>
								<td align="right">
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
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
										<table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right">
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

											</tr>
											</tbody>
										</table>
									  </ezf:skip>
<%-- ######################################## TO (COMMON)PAGENATION #################################### --%>
								</td>
							</tr>
						</table>

						<table width="1090" border="1" cellspacing="0" style="word-break:break-all;table-layout: fixed">
							<col width="35">
							<col width="45">
							<col width="315">
							<col width="90">
							<col width="90">
							<col width="150">
							<col width="100">
							<col width="100">
							<col width="80">
							<col width="80">
							<tr>
								<td align="center" class="pClothBs">&nbsp;</td>
								<td align="center" class="pClothBs">Line#</td>
								<td align="center" class="pClothBs">Accounting String</td>
								<td align="center" class="pClothBs">Debit</td>
								<td align="center" class="pClothBs">Credit</td>
								<td align="center" class="pClothBs">Line Description</td>
								<td align="center" class="pClothBs">Customer#</td>
								<td align="center" class="pClothBs">Serial#</td>
								<td align="center" class="pClothBs">Source Type</td>
								<td align="center" class="pClothBs">Source Value</td>
							</tr>
						</table>
						<div id="rightTBL" style="overflow-y:scroll; height:300; overflow-x:hidden; width:1107;" onscroll="synchroRightScroll();">
						<table width="1090" border="1" cellspacing="0" style="word-break:break-all;table-layout: fixed" id="A">
							<col width="35">
							<col width="45">
							<col width="315">
							<col width="90" align="right">
							<col width="90" align="right">
							<col width="150">
							<col width="100">
							<col width="100">
							<col width="80">
							<col width="80">
							<tbody>
								<ezf:row ezfHyo="A">
									<tr height="23px">
										<td class="stab" align="center">
											<ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
										</td>
										<td align="right" class="stab"><ezf:label name="xxNum_A" ezfName="xxNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\""/></td>
										<td align="left" class="stab">
											<ezf:inputText name="xxScrItem61Txt_A" ezfName="xxScrItem61Txt_A" value="ADB.000.000000.11411001.00.000.000.00.000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"41\" maxlength=\"50\" ezftoupper=\"\""/>
											<ezf:inputButton name="OpenWin_GlCombnSearch" value="A" ezfHyo="A" ezfArrayNo="0" />
										</td>
										<td align="right" class="stab">
											<ezf:inputText name="jrnlDealAmt_A1" ezfName="jrnlDealAmt_A1" value="100.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"20\" style=\"text-align:right\""/>
										</td>
										<td align="right" class="stab">
											<ezf:inputText name="jrnlDealAmt_A2" ezfName="jrnlDealAmt_A2" value="100.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"20\" style=\"text-align:right\""/>
										</td>
										<td align="left" class="stab">
											<ezf:inputText name="manJrnlLineTxt_A" ezfName="manJrnlLineTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"150\""/>
										</td>
										<td align="left" class="stab">
											<ezf:inputText name="dsAcctNum_A" ezfName="dsAcctNum_A" value="2633533" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
											<ezf:inputButton name="OpenWin_DsAcctSearch" value="C" ezfHyo="A" ezfArrayNo="0" />
										</td>
										<td align="left" class="stab">
											<ezf:inputText name="serNum_A" ezfName="serNum_A" value="1212312312" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/>
										</td>
										<td>
											<ezf:select name="jrnlEntrySrcTpCd_LS" ezfName="jrnlEntrySrcTpCd_LS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="jrnlEntrySrcTpCd_LC" ezfDispName="jrnlEntrySrcTpDescTxt_LD" otherAttr=" style=\"width:77px;\""/>
										</td>
										<td align="left" class="stab">
											<ezf:inputText name="jrnlEntrySrcValTxt_A" ezfName="jrnlEntrySrcValTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
										</td>
									</tr>
								</ezf:row>
								<ezf:skip>
								</ezf:skip>
							</tbody>
						</table>
						</div>
						<table width="1090" border="1" cellspacing="0">
							<col width="365">
							<col width="450">
							<tr height="20px">
								<td align="right" class="stab">
									<ezf:inputText name="jrnlDealAmt_T1" ezfName="jrnlDealAmt_T1" value="100.00" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/>
								</td>
								<td align="left" class="stab">
									<ezf:inputText name="jrnlDealAmt_T2" ezfName="jrnlDealAmt_T2" value="100.00" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</div>

<%-- **** Task specific area ends here **** --%>
