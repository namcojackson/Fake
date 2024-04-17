<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20181114101644 --%>
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
			<input type="hidden" name="pageID" value="NMAL3300Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Customer Special Instruction Popup">
			
<center>
	<table>
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<c:choose>
					<c:when test="${pageScope._ezddatabean.custHeadCnt_S1 >= 2}">
						<table>
								<col width="800">
								<tr>
									<td class="stab">
										<font color="red">There is one or more messages at Bill To and/or Ship To Location. Please switch the account/location to review it.</font>
									</td>
								</tr>
						</table>
					</c:when>
				</c:choose>
				<table>
						<col width="350">
						<col width="350">
						<tr>
							<td>
								<ezf:select name="locNum_D" ezfName="locNum_D" ezfCodeName="locNum_01" ezfDispName="xxScrItem130Txt_01" otherEvent1=" onchange=\"sendServer('OnChange_locNum');\"" otherAttr=" style=\"width: 340\""/>
							</td>
							<td>
								<ezf:inputText name="dsAcctNm_D" ezfName="dsAcctNm_D" otherAttr=" size=\"80\""/>
							</td>
						</tr>
				</table>
				<hr>
				
<%-- #################################################### DETAIL ################################################### --%>
<%-- #################################################### Special Message ################################################### --%>
				<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" width="953">
					<tr height="22">
						<td align="center" width="887" class="pClothBs">Special Message</td>
					</tr>
				</table>
		
				<table border="0" cellpadding="0" cellspacing="0" width="953">
					<tr>
						<td align="right">Cursor in and [Alt + E] to view whole text</td>
						<td align="right" width="342">
						<ezf:skip>
							<table border="0" cellpadding="0" cellspacing="0">
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
									<td class="pOut">6</td>
									<td class="stab">of</td>
									<td class="pOut">6</td>
									<td>&nbsp;</td>
									<td><input type="button" class="pBtn3" value="Prev"  id="btnPrev" name="PagePrev" onclick="sendServer(this)" ></td>
									<td></td>
									<td><input type="button" class="pBtn3" value="Next"  id="btnNext" name="PageNext" onclick="sendServer(this)" ></td>
								</tr>
							</table>
						</ezf:skip>
						<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
						<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
						<jsp:param name="TableNm"     value="D" />
						<jsp:param name="ShowingFrom" value="xxPageShowFromNum_D" />
						<jsp:param name="ShowingTo"   value="xxPageShowToNum_D" />
						<jsp:param name="ShowingOf"   value="xxPageShowOfNum_D" />
						</jsp:include>
						</td>
						<td width="3"></td>
					</tr>
				</table>
				<table border="0" cellpadding="1" cellspacing="0" width="977">
					<tr>
						<td width="977">
							<div id="LeftTop_D" style="overflow-x:none; overflow-y:none; width:175;float:left;">
								<table border="1" cellpadding="1" cellspacing="0" width="175" style="table-layout:fixed;">
									<col width="55" align="center">
									<col width="120" align="center">
									
									<tr height="22">
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'hrchEffLvlTpNm_D1' )">Level<img id="sortIMG.hrchEffLvlTpNm_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'locNum_D1' )">Account/Location#<img id="sortIMG.locNum_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									</tr>
								</table>
							</div>
							<div id="RightTop_D" style="overflow-x:hidden;overflow-y:hidden; width:775;float:left;" onScroll="synchroScrollLeft( this.id, new Array( 'Right_D' ) );">
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed">
									<col width="40" align="center">
									<col width="92" align="left">
									<col width="641" align="center">
									<col width="40" align="left">
									<col width="100" align="center">
									<col width="120" align="center">
									<col width="90" align="center">
									<col width="90" align="center">
									<col width="90" align="center">
									<col width="100" align="center">
									<col width="92" align="center">
									<tr height="22">
										<td class="pClothBs" style="word-wrap: break-word;"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'lineBizTpCd_D1' )">LOB<img id="sortIMG.lineBizTpCd_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" style="word-wrap: break-word;"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'dsBizAreaNm_D1' )">Business Area<img id="sortIMG.dsBizAreaNm_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" style="word-wrap: break-word;"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'dsCustMsgTxt_D1' )">Message<img id="sortIMG.dsCustMsgTxt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" style="word-wrap: break-word;"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'dsCustMsgTpNm_D1' )">Type<img id="sortIMG.dsCustMsgTpNm_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" style="word-wrap: break-word;"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'xxChkBox_D1' )">Print On Invoice<img id="sortIMG.xxChkBox_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" style="word-wrap: break-word;"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'custEffLvlNm_D1' )">Effective<img id="sortIMG.custEffLvlNm_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" style="word-wrap: break-word;"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'effFromDt_D1' )">Effective From<img id="sortIMG.effFromDt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" style="word-wrap: break-word;"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'effThruDt_D1' )">Effective Thru<img id="sortIMG.effThruDt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" style="word-wrap: break-word;"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'ezInTime_D1' )">Creation Date<img id="sortIMG.ezInTime_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" style="word-wrap: break-word;"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'ezUpTime_D1' )">Last Updte Date<img id="sortIMG.ezUpTime_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" style="word-wrap: break-word;"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'ezBusinessID_D1' )">Attachment<img id="sortIMG.ezBusinessID_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									</tr>
								</table>
							</div>
							<div id="Left_D" style="overflow-x:hidden; overflow-y:hidden; height:403; float:left;" onScroll="synchroScrollTop( this.id, new Array( 'Right_D' ) );">
								<table id="D_Left" border="1" cellpadding="1" cellspacing="0" width="175" style="table-layout:fixed;">
									<col width="55" align="center">
									<col width="120" align="center">
									
									<ezf:row ezfHyo="D">
										<tr height="55" id="id_row{EZF_ROW_NUMBER}">
											<td><ezf:label name="hrchEffLvlTpNm_D1" ezfName="hrchEffLvlTpNm_D1" ezfHyo="D" ezfArrayNo="0" /></td>
											<td><ezf:label name="locNum_D1" ezfName="locNum_D1" ezfHyo="D" ezfArrayNo="0" /></td>
										</tr>
									</ezf:row>
								</table>
							</div>
							<div id="Right_D" style="overflow-x:scroll; overflow-y:scroll; width:792; height:420; float:left;" onScroll="synchroScrollTop( this.id, new Array( 'Left_D' ) );synchroScrollLeft( this.id, new Array( 'RightTop_D' ) );">
								<table id="D_Right" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed">
									<col width="40" align="center">
									<col width="92" align="left">
									<col width="641" align="left">
									<col width="40" align="left">
									<col width="100" align="center">
									<col width="120" align="center">
									<col width="90" align="center">
									<col width="90" align="center">
									<col width="90" align="center">
									<col width="100" align="center">
									<col width="92" align="center">
									<ezf:row ezfHyo="D">
										<tr height="55">
											<td><ezf:label name="lineBizTpCd_D1" ezfName="lineBizTpCd_D1" ezfHyo="D" ezfArrayNo="0" /></td>
											<td><ezf:label name="dsBizAreaNm_D1" ezfName="dsBizAreaNm_D1" ezfHyo="D" ezfArrayNo="0" /></td>
											<td><ezf:textArea name="dsCustMsgTxt_D1" ezfName="dsCustMsgTxt_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" cols=\"87\" rows=\"4\" maxlength=\"4000\""/></td>
											<td><ezf:label name="dsCustMsgTpNm_D1" ezfName="dsCustMsgTpNm_D1" ezfHyo="D" ezfArrayNo="0" /></td>
											<td class="stab"><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="Y" ezfHyo="D" ezfArrayNo="0" />
											<td><ezf:label name="custEffLvlNm_D1" ezfName="custEffLvlNm_D1" ezfHyo="D" ezfArrayNo="0" /></td>
											<td><ezf:label name="effFromDt_D1" ezfName="effFromDt_D1" ezfHyo="D" ezfArrayNo="0" /></td>
											<td><ezf:label name="effThruDt_D1" ezfName="effThruDt_D1" ezfHyo="D" ezfArrayNo="0" /></td>
											<td><ezf:label name="ezInTime_D1" ezfName="ezInTime_D1" ezfHyo="D" ezfArrayNo="0" /></td>
											<td><ezf:label name="ezUpTime_D1" ezfName="ezUpTime_D1" ezfHyo="D" ezfArrayNo="0" /></td>
											<td><ezf:inputButton name="OpenWin_Attachments" value="Attach" ezfHyo="D" ezfArrayNo="0" htmlClass="pBtn3" otherAttr=" id=\"btnAttach\""/></td>
										</tr>
									</ezf:row>
								</table>
							</div>
						</td>
					</tr>
				</table>
<%-- #################################################### Special Message ################################################### --%>
			</td>
		</tr>
	</table>
</center>
<STYLE type="text/css"> 
<!-- 
tr.pHightlightBGcolor	{background-color:#FFFF00;}
--> 
</STYLE> 


<%-- **** Task specific area ends here **** --%>
