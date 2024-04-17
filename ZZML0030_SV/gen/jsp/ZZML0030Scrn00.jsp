<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20200227145658 --%>
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
			<input type="hidden" name="pageID" value="ZZML0030Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Mail Archives Monitor">
<table align="center" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left">
			<!-- include S21BusinessProcessTAB -->
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
			<!--
			<div class="pTab_HEAD">
				<ul>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="96%">
								<div>
									<li title="Order Entry"				 class="pTab_ON" ><a href="#">Order Entry</a></li>
									<li title="Order Upload"			 class="pTab_OFF"><a href="#">Order Upload</a></li>
									<li title="Trial/Loan Request Entry" class="pTab_OFF"><a href="#">Trial/Loan Request Entry</a></li>
									<li title="Asset Management"		 class="pTab_OFF"><a href="#">Asset Management</a></li>
									<li title="Hard Allocation"			 class="pTab_OFF"><a href="#">Hard Allocation</a></li>
									<li title="Hold Release"			 class="pTab_OFF"><a href="#">Hold Release</a></li>
									<li title="Credit Order Release"	 class="pTab_OFF"><a href="#">Credit Order Release</a></li>
									<li title="TOP STOP Release" 		 class="pTab_OFF"><a href="#">TOP STOP Release</a></li>
									<li title="Export Lisence Entry"	 class="pTab_OFF"><a href="#">Export Lisence Entry</a></li>
									<li title="Disposition Order"		 class="pTab_OFF"><a href="#">Disposition Order</a></li>
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
			-->
			<div class="pTab_BODY">
<!-- **************************************** HEADER **************************************** -->
				<table width="990" align="center" border="0" cellpadding="1" cellspacing="0">
					<tr>
						<td align="left">
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="170">
								<col width="32">
								<col width="686">
								<col width="102">
								<!-- Global Company Code -->
								<tr align="left">
									<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company CD</ezf:anchor></td>
									<td>
										<ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXX" otherAttr=" size=\"4\" ezftoupper=\"\""/>
									</td>
									<td></td>
									<td></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr align="left">
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="60">
								<col width="90">
								<col width="5">
								<col width="80">
								<col width="248">
								<col width="5">
								<col width="160">
								<col width="248">
								<!-- Mail Archives Condition -->
								<tr align="left">
									<td class="stab">Status</td>
									<td>
										<ezf:select name="mlSendStsCd_H" ezfName="mlSendStsCd_H" ezfBlank="1" ezfCodeName="mlSendStsCd_HC" ezfDispName="xxScrItem7Txt_H" />
									</td>
									<td></td>
									<td class="stab">Subject(*)</td>
									<td>
										<ezf:inputText name="mlSubjTxt_H" ezfName="mlSubjTxt_H" value="XXX" otherAttr=" size=\"30\" maxlength=\"200\""/>
									</td>
									<td></td>
									<td class="stab">Mail Address From(*)</td>
									<td>
										<ezf:inputText name="mlUsrAddr_H" ezfName="mlUsrAddr_H" value="XXX" otherAttr=" size=\"30\" maxlength=\"240\""/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr align="left">
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="150">
								<col width="112">
								<col width="10">
								<col width="112">
								<col width="106">
                                <col width="110">
                            	<col width="48">
                            	<col width="248">
								<!-- Mail Archives Condition -->
								<tr align="left">
									<td class="stab">Status Update Date</td>
									<td>
										<ezf:inputText name="xxFromDt_H" ezfName="xxFromDt_H" value="XXXX-XX-XX" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt_H', 4);" >
									</td>
									<td>-</td>
									<td>
										<ezf:inputText name="xxToDt_H" ezfName="xxToDt_H" value="XXXX-XX-XX" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxToDt_H', 4);" >
									</td>
									<td></td>
                                	<td class="stab">Mail Address To(*)</td>
                                	<td>
										<ezf:select name="mlAddrTpCd_T" ezfName="mlAddrTpCd_T" ezfBlank="1" ezfCodeName="mlAddrTpCd_TC" ezfDispName="xxScrItem8Txt_T" />
									</td>
									<td>
										<ezf:inputText name="mlUsrAddr_T" ezfName="mlUsrAddr_T" value="XXX" otherAttr=" size=\"30\" maxlength=\"240\""/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
                    <tr align="left">
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="150">
								<col width="112">
								<col width="10">
								<col width="112">
								<col width="496">
								<col width="100">
								<!-- Mail Archives Condition -->
								<tr align="left">
									<td class="stab">Status Update Time</td>
									<td>
										<ezf:select name="xxHrs_F" ezfName="xxHrs_F" ezfBlank="1" ezfCodeName="xxHrs_FC" ezfDispName="xxHrsMn_F" />
									</td>
									<td>-</td>
									<td>
										<ezf:select name="xxHrs_T" ezfName="xxHrs_T" ezfBlank="1" ezfCodeName="xxHrs_TC" ezfDispName="xxHrsMn_T" />
									</td>
									<td></td>
									<td align="right">
										<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
	<!-- **************************************** HEADER **************************************** -->

				<hr size="1" noshade>

				<!-- view -->
				<table cellpadding="0" cellspacing="0" border="0" width="80%" align="center">
					<col width="120">
					<col width="1310">
					<%-- Pagenation --%>
					<tr>
						<td colspan="2" align="right">
							<!--
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="54"  align="center">
									<col width="40"  align="right">
									<col width="16"  align="center">
									<col width="40"  align="right">
									<col width="16"  align="center">
									<col width="40"  align="right">
									<col width="10">
									<col>
									<col width="1">
									<col>
									<tr>
										<td class="stab">Showing</td>
										<td class="pOut">1</td>
										<td class="stab">to</td>
										<td class="pOut">10</td>
										<td class="stab">of</td>
										<td class="pOut">200</td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
									</tr>
								</table>
							-->
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
						<td valign="top">
							<%-- LEFT-TABLE(TOP) --%>
							<div id="leftTopTBL" style="overflow-y:hidden; height:25; overflow-x:hidden; width:;">
								<table cellpadding="1" cellspacing="0" border="1" >
									<col width="36">
									<col width="45">
									<col width="45">
										<tr style="text-align: center" height="25">
											<td class="pClothBs" nowrap>No.</td>
											<td class="pClothBs" nowrap></td>
											<td class="pClothBs" nowrap></td>
										</tr>
								</table>
							</div>
							
							<%-- LEFT-TABLE(MID) --%>
							<div id="leftTBL" style="overflow-y:hidden; height:392; overflow-x:hidden; width:;" onScroll="synchroScrollTop( this.id, new Array( 'rightTBL' ) );">
								<table cellpadding="1" cellspacing="0" border="1" id="A_LeftTBL">
									<col width="38">
									<col width="45">
									<col width="45">
									<tbody>
										<ezf:row ezfHyo="A">
										<tr style="text-align: left" height="26">
											<!-- No -->
											<td align="center"><ezf:label name="xxNum_A" ezfName="xxNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<!-- View -->
											<td align="center">
												<ezf:inputButton name="View" value="View" ezfHyo="A" ezfArrayNo="0" htmlClass="cBtn1" />
											</td>
											<!-- Send -->
											<td align="center">
												<ezf:inputButton name="Send" value="Send" ezfHyo="A" ezfArrayNo="0" htmlClass="cBtn1" />
											</td>
										</tr>
										</ezf:row>

										<ezf:skip>

										<tr style="text-align: left" height="26">
											<!-- No -->
											<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
											<!-- View -->
											<td align="center"><input type="button" class="cBtn1" value="View" name="View" onclick="sendServer(this)"></td>
											<!-- Send -->
											<td align="center"><input type="button" class="cBtn1" value="Send" name="Send" onclick="sendServer(this)"></td>
										</tr>

										<tr style="text-align: left" height="26">
											<!-- No -->
											<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
											<!-- View -->
											<td align="center"><input type="button" class="cBtn1" value="View" name="View" onclick="sendServer(this)"></td>
											<!-- Send -->
											<td align="center"><input type="button" class="cBtn1" value="Send" name="Send" onclick="sendServer(this)"></td>
										</tr>

										<tr style="text-align: left" height="26">
											<!-- No -->
											<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
											<!-- View -->
											<td align="center"><input type="button" class="cBtn1" value="View" name="View" onclick="sendServer(this)"></td>
											<!-- Send -->
											<td align="center"><input type="button" class="cBtn1" value="Send" name="Send" onclick="sendServer(this)"></td>
										</tr>

										<tr style="text-align: left" height="26">
											<!-- No -->
											<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
											<!-- View -->
											<td align="center"><input type="button" class="cBtn1" value="View" name="View" onclick="sendServer(this)"></td>
											<!-- Send -->
											<td align="center"><input type="button" class="cBtn1" value="Send" name="Send" onclick="sendServer(this)"></td>
										</tr>

										<tr style="text-align: left" height="26">
											<!-- No -->
											<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
											<!-- View -->
											<td align="center"><input type="button" class="cBtn1" value="View" name="View" onclick="sendServer(this)"></td>
											<!-- Send -->
											<td align="center"><input type="button" class="cBtn1" value="Send" name="Send" onclick="sendServer(this)"></td>
										</tr>

										<tr style="text-align: left" height="26">
											<!-- No -->
											<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
											<!-- View -->
											<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
											<!-- Send -->
											<td align="center"><input type="button" class="pBtn1" value="Send" name="Send" onclick="sendServer(this)"></td>
										</tr>

										<tr style="text-align: left" height="26">
											<!-- No -->
											<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
											<!-- View -->
											<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
											<!-- Send -->
											<td align="center"><input type="button" class="pBtn1" value="Send" name="Send" onclick="sendServer(this)"></td>
										</tr>

										<tr style="text-align: left" height="26">
											<!-- No -->
											<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
											<!-- View -->
											<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
											<!-- Send -->
											<td align="center"><input type="button" class="pBtn1" value="Send" name="Send" onclick="sendServer(this)"></td>
										</tr>

										<tr style="text-align: left" height="26">
											<!-- No -->
											<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
											<!-- View -->
											<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
											<!-- Send -->
											<td align="center"><input type="button" class="pBtn1" value="Send" name="Send" onclick="sendServer(this)"></td>
										</tr>

										<tr style="text-align: left" height="26">
											<!-- No -->
											<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
											<!-- View -->
											<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
											<!-- Send -->
											<td align="center"><input type="button" class="pBtn1" value="Send" name="Send" onclick="sendServer(this)"></td>
										</tr>

										<tr style="text-align: left" height="26">
											<!-- No -->
											<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
											<!-- View -->
											<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
											<!-- Send -->
											<td align="center"><input type="button" class="pBtn1" value="Send" name="Send" onclick="sendServer(this)"></td>
										</tr>

										<tr style="text-align: left" height="26">
											<!-- No -->
											<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
											<!-- View -->
											<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
											<!-- Send -->
											<td align="center"><input type="button" class="pBtn1" value="Send" name="Send" onclick="sendServer(this)"></td>
										</tr>

										<tr style="text-align: left" height="26">
											<!-- No -->
											<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
											<!-- View -->
											<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
											<!-- Send -->
											<td align="center"><input type="button" class="pBtn1" value="Send" name="Send" onclick="sendServer(this)"></td>
										</tr>

										<tr style="text-align: left" height="26">
											<!-- No -->
											<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
											<!-- View -->
											<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
											<!-- Send -->
											<td align="center"><input type="button" class="pBtn1" value="Send" name="Send" onclick="sendServer(this)"></td>
										</tr>

										<tr style="text-align: left" height="26">
											<!-- No -->
											<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
											<!-- View -->
											<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
											<!-- Send -->
											<td align="center"><input type="button" class="pBtn1" value="Send" name="Send" onclick="sendServer(this)"></td>
										</tr>

										<tr style="text-align: left" height="26">
											<!-- No -->
											<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
											<!-- View -->
											<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
											<!-- Send -->
											<td align="center"><input type="button" class="pBtn1" value="Send" name="Send" onclick="sendServer(this)"></td>
										</tr>

										</ezf:skip>
									</tbody>
								</table>
							</div>
							
						</td>
						<td>
							<%-- RIGHT-TABLE(TOP) --%>
							<div id="rightTopTBL" style="overflow-y:hidden; height:25; overflow-x:hidden; width:867;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTBL' ) );">
								<table cellpadding="1" cellspacing="0" border="1" width="1520">
									<col width="547">
									<col width= "90">
									<col width="204">
									<col width="641">
									<tr style="text-align: center" height="25">
										<td class="pClothBs" nowrap><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mlSubjTxt_A' )">Subject<img id="sortIMG.mlSubjTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" nowrap><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem7Txt_A' )">Status<img id="sortIMG.xxScrItem7Txt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" nowrap><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxNum_A' )">Status Update Date Time<img id="sortIMG.xxNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" nowrap><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mlUsrAddr_A' )">Mail Address (From)<img id="sortIMG.mlUsrAddr_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									</tr>
								</table>
							</div>
							
							<%-- RIGHT-TABLE(MID) --%>
							<div id="rightTBL" style="overflow-y:scroll; height:409; overflow-x:scroll; width:883;" onScroll="synchroScrollTop( this.id, new Array( 'leftTBL' ) );synchroScrollLeft( this.id, new Array( 'rightTopTBL' ) );">
								<table cellpadding="1" cellspacing="0" border="1" width="1520" id="A_RightTBL">
									<col width="547">
									<col width= "90">
									<col width="204">
									<col width="641">
									<tbody>
										<ezf:row ezfHyo="A">
										<tr height="26">
											<!-- Subject -->
											<td style="word-break:break-all;"><ezf:label name="mlSubjTxt_A" ezfName="mlSubjTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<!-- Status -->
											<td style="text-align:center;"><ezf:label name="xxScrItem7Txt_A" ezfName="xxScrItem7Txt_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<!-- Status Update Date Time -->
											<td style="text-align:center;"><ezf:label name="xxDtTm_A" ezfName="xxDtTm_A" ezfHyo="A" ezfArrayNo="0" />(<ezf:label name="ezUpTimeZone_A" ezfName="ezUpTimeZone_A" ezfHyo="A" ezfArrayNo="0" />)</td>
											<!-- Mail Address (From) -->
											<td style="word-break:break-all;"><ezf:label name="mlUsrAddr_A" ezfName="mlUsrAddr_A" ezfHyo="A" ezfArrayNo="0" /></td>
										</tr>
										</ezf:row>

										<ezf:skip>

										<tr height="26">
											<!-- Subject -->
											<td style="word-break:break-all;"><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">*********1*********2*********3*********4*********5*********6*********</label></td>
											<!-- Status -->
											<td style="text-align:center;"><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">PENDING</label></td>
											<!-- Mail Send Date Time -->
											<td style="text-align:center;"><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">2009/06/04 10:10:01(EST)</label></td>
											<!-- Mail Address (From) -->
											<td style="word-break:break-all;text-align:left;"><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">user1@test.co.jp</label></td>
										</tr>
										<tr height="26">
											<!-- Subject -->
											<td style="word-break:break-all;"><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">*********1*********2*********3*********4*********5*********6*********</label></td>
											<!-- Status -->
											<td style="text-align:center;"><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">SENT</label></td>
											<!-- Mail Send Date Time -->
											<td style="text-align:center;"><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">2009/06/04 10:10:01(EST)</label></td>
											<!-- Mail Address (From) -->
											<td style="word-break:break-all;text-align:left;"><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">*********1*********2*********3*********4*********5*********6*********7*********8*</label></td>
										</tr>
										<tr height="26">
											<!-- Subject -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">This is test mail.</label></td>
											<!-- Status -->
											<td style="text-align:center;"><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">SENT</label></td>
											<!-- Mail Send Date Time -->
											<td style="text-align:center;"><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">2009/06/04 10:10:01(EST)</label></td>
											<!-- Mail Address (From) -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">user1@test.co.jp</label></td>
										</tr>
										
										<tr height="26">
											<!-- Subject -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">This is test mail.</label></td>
											<!-- Status -->
											<td style="text-align:center;"><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">SENT</label></td>
											<!-- Mail Send Date Time -->
											<td style="text-align:center;"><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">2009/06/04 10:10:01(EST)</label></td>
											<!-- Mail Address (From) -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">user1@test.co.jp</label></td>
										</tr>
										
										<tr height="26">
											<!-- Subject -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">This is test mail.</label></td>
											<!-- Status -->
											<td style="text-align:center;"><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">SENT</label></td>
											<!-- Mail Send Date Time -->
											<td style="text-align:center;"><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">2009/06/04 10:10:01(EST)</label></td>
											<!-- Mail Address (From) -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">user1@test.co.jp</label></td>
										</tr>
										
										<tr height="26">
											<!-- Subject -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">This is test mail.</label></td>
											<!-- Status -->
											<td style="text-align:center;"><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">SENT</label></td>
											<!-- Mail Send Date Time -->
											<td style="text-align:center;"><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">2009/06/04 10:10:01(EST)</label></td>
											<!-- Mail Address (From) -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">user1@test.co.jp</label></td>
										</tr>
										
										<tr height="26">
											<!-- Subject -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">This is test mail.</label></td>
											<!-- Status -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">SENT</label></td>
											<!-- Mail Send Date Time -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">2009/06/04 10:10:01(EST)</label></td>
											<!-- Mail Address (From) -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">user1@test.co.jp</label></td>
										</tr>
										
										<tr height="26">
											<!-- Subject -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">This is test mail.</label></td>
											<!-- Status -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">SENT</label></td>
											<!-- Mail Send Date Time -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">2009/06/04 10:10:01(EST)</label></td>
											<!-- Mail Address (From) -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">user1@test.co.jp</label></td>
										</tr>
										
										<tr height="26">
											<!-- Subject -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">This is test mail.</label></td>
											<!-- Status -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">SENT</label></td>
											<!-- Mail Send Date Time -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">2009/06/04 10:10:01(EST)</label></td>
											<!-- Mail Address (From) -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">user1@test.co.jp</label></td>
										</tr>
										
										<tr height="26">
											<!-- Subject -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">This is test mail.</label></td>
											<!-- Status -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">SENT</label></td>
											<!-- Mail Send Date Time -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">2009/06/04 10:10:01(EST)</label></td>
											<!-- Mail Address (From) -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">user1@test.co.jp</label></td>
										</tr>
										
										<tr height="26">
											<!-- Subject -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">This is test mail.</label></td>
											<!-- Status -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">SENT</label></td>
											<!-- Mail Send Date Time -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">2009/06/04 10:10:01(EST)</label></td>
											<!-- Mail Address (From) -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">user1@test.co.jp</label></td>
										</tr>
										
										<tr height="26">
											<!-- Subject -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">This is test mail.</label></td>
											<!-- Status -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">SENT</label></td>
											<!-- Mail Send Date Time -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">2009/06/04 10:10:01(EST)</label></td>
											<!-- Mail Address (From) -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">user1@test.co.jp</label></td>
										</tr>
										
										<tr height="26">
											<!-- Subject -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">This is test mail.</label></td>
											<!-- Status -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">SENT</label></td>
											<!-- Mail Send Date Time -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">2009/06/04 10:10:01(EST)</label></td>
											<!-- Mail Address (From) -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">user1@test.co.jp</label></td>
										</tr>
										
										<tr height="26">
											<!-- Subject -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">This is test mail.</label></td>
											<!-- Status -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">SENT</label></td>
											<!-- Mail Send Date Time -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">2009/06/04 10:10:01(EST)</label></td>
											<!-- Mail Address (From) -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">user1@test.co.jp</label></td>
										</tr>
										
										<tr height="26">
											<!-- Subject -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">This is test mail.</label></td>
											<!-- Status -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">SENT</label></td>
											<!-- Mail Send Date Time -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">2009/06/04 10:10:01(EST)</label></td>
											<!-- Mail Address (From) -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">user1@test.co.jp</label></td>
										</tr>
										
										<tr height="26">
											<!-- Subject -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">This is test mail.</label></td>
											<!-- Status -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">SENT</label></td>
											<!-- Mail Send Date Time -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">2009/06/04 10:10:01(EST)</label></td>
											<!-- Mail Address (From) -->
											<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">user1@test.co.jp</label></td>
										</tr>
										
										</ezf:skip>
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<%-- foot --%>
			
		</td>
	</tr>
</table>

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
<style TYPE="text/css">
	<!--
	input.cBtn1{font-family:'Arial',sans-serif;font-size:9pt;height:20;width:34;margin:0;color:#000000;}
	-->
</style>

<%-- **** Task specific area ends here **** --%>
