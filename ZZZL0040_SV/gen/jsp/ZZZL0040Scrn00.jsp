<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20101221230802 --%>
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
			<input type="hidden" name="pageID" value="ZZZL0040Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Online Process Rank">
			
<center>
	<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER START ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<!-- include S21BusinessProcessTAB -->
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				 <!--<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
					<tr>
						<td width="1100px" height="28px" valign="bottom">
								<div>
									<table border="0" cellpadding="0" cellspacing="0">
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
												<tr title='Order Entry'>
													<td width="107px" align="center" class="same">
														Onl Proc View
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='Hold Release'>
													<td width="90px" align="center" class="disabled">
														Onl Proc Rank
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='Soft Allocation'>
													<td width="90px" align="center" class="disabled">
														Onl Proc Config
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
									</table>
								</div>
						</td>
						<td valign="bottom" align="center">
								<a href="#" id="PrevPageTabIndex"><img id="PrevPageBtn" src="./img/tab/tabbackbutton.png" alt="Prev" border="0"></a>
						</td>
						<td valign="bottom" align="center">
							<a href="#" id="NextPageTabIndex"><img id="NextPageBtn" src="./img/tab/tabnextbutton.png" alt="Next" border="0"></a>
						</td>
					</tr>
				</table>-->
				
				<div class="pTab_BODY">
				<table width="1050px" align="center" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<col width="60">
							<col width="110">
							<col width="20">
							<col width="150">
							<col width="100">
							<td class="stab" align="left">
								<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company CD</ezf:anchor>
							</td>
							<td align="left">
								<ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXX" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/>
							</td>
							<td align="right">
								<ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="1" />
							</td>
							<td class="stab" align="left">- order by Business processing time
							</td>
							<td class="stab" align="right">
								Average view<ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" otherAttr=" onclick=\"sendServer('Uncheck')\""/>
							</td>
						</tr>
						<tr>
							<td class="stab" align="left">JVM
							</td>
							<td align="left">
								<ezf:select name="jvmNm_S" ezfName="jvmNm_S" ezfCodeName="jvmNm_C" ezfDispName="jvmNm_D" />
							</td>
							<td align="right">
								<ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="2" />
							</td>
							<td class="stab" align="left">- order by Business throughput
							</td>
							<td class="stab" align="right">
								Max number of display per EventID
								<ezf:select name="xxTotCnt" ezfName="xxTotCnt" >
									<ezf:option value="0" >N/A</ezf:option>
									<ezf:option value="1" >1</ezf:option>
									<ezf:option value="5" >5</ezf:option>
									<ezf:option value="10" >10</ezf:option>
								</ezf:select>
							</td>
						</tr>
						<tr>
							<td class="stab" align="left">Operation Date(From)
							</td>
							<td>
								<ezf:inputText name="xxFromDt" ezfName="xxFromDt" otherAttr=" size=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt', 4);" >
								<ezf:select name="xxHrs_FS" ezfName="xxHrs_FS" ezfCodeName="xxHrs_FC" ezfDispName="xxHrs_FD" />
								:
								<ezf:select name="xxMn_FS" ezfName="xxMn_FS" ezfCodeName="xxMn_FC" ezfDispName="xxMn_FD" />
							</td>
							<td align="right" class="stab">
								<ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="3" />
							</td>
							<td class="stab" align="left">- order by Global area size
							</td>
							<td class="stab" align="right">
							</td>
						</tr>
						<tr>
							<td class="stab" align="left">Operation Date(To)
							</td>
							<td>
								<ezf:inputText name="xxToDt" ezfName="xxToDt" otherAttr=" size=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxToDt', 4);" >
								<ezf:select name="xxHrs_TS" ezfName="xxHrs_TS" ezfCodeName="xxHrs_TC" ezfDispName="xxHrs_TD" />
								:
								<ezf:select name="xxMn_TS" ezfName="xxMn_TS" ezfCodeName="xxMn_TC" ezfDispName="xxMn_TD" />
							</td>
							<td align="right" class="stab">
							</td>
							<td class="stab" align="left">
							</td>
							<td align="right">
								<ezf:inputButton name="View" value="View" htmlClass="cBtn" />
							</td>
						</tr>
						<tr>
							<td colspan="5">
								<hr>
							</td>
						</tr>
					</tbody>
				</table>
				</div>
				<%-- ######################################## HEADER END ######################################## --%>
				<table align="center" border="0" cellspacing="0" cellpadding="0" width="1060">
					<tr>
						<td valign="top">
						<%-- LEFT-TABLE(TOP) --%>
							<div id="leftTopTBL" style="overflow-y:hidden; overflow-x:hidden; width:;">
							<table border="1" cellspacing="0" cellpadding="1">
								<col width="24">
								<col width="55">
								<col width="138">
								<col width="65">
								<col width="65">
								<col width="250">
								<tr align="center">
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxRowNum_A' )">No.<img id="sortIMG.xxRowNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'glblCmpyCd_A' )">Global Company Code<img id="sortIMG.glblCmpyCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'jvmNm_A' )">JVM<img id="sortIMG.jvmNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>							
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'opsUsrId_A' )">Operation<BR>User<img id="sortIMG.opsUsrId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'bizId_A' )">Business<BR>ID<img id="sortIMG.bizId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'scrAppId_A' )">Screen Event ID<img id="sortIMG.scrAppId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
								</tr>
							</table>
							</div>
						<%-- LEFT-TABLE(MID) --%>
							<div id="leftTBL" style="overflow-y:hidden; height:393; overflow-x:hidden; width:;" onScroll="synchroScrollTop( this.id, new Array( 'rightTBL' ) );">
							<table cellpadding="1" cellspacing="0" border="1" id="A_LeftTBL">
							<tbody>
								<col width="24">
								<col width="55">
								<col width="138">
								<col width="65">
								<col width="65">
								<col width="250">
								<ezf:row ezfHyo="A">
								<tr id="id_row_{EZF_ROW_NUMBER}">
									<td align="center">
										<ezf:label name="xxRowNum_A" ezfName="xxRowNum_A" ezfHyo="A" ezfArrayNo="0" />
									</td>
									<td align="center">
										<ezf:label name="glblCmpyCd_A" ezfName="glblCmpyCd_A" ezfHyo="A" ezfArrayNo="0" />
									</td>
									<td align="center">
										<ezf:label name="jvmNm_A" ezfName="jvmNm_A" ezfHyo="A" ezfArrayNo="0" />
									</td>
									<td align="center">
										<ezf:label name="opsUsrId_A" ezfName="opsUsrId_A" ezfHyo="A" ezfArrayNo="0" />
									</td>
									<td align="center">
										<ezf:label name="bizId_A" ezfName="bizId_A" ezfHyo="A" ezfArrayNo="0" />
									</td>
									<td align="left">
										<ezf:label name="scrAppId_A" ezfName="scrAppId_A" ezfHyo="A" ezfArrayNo="0" />
									</td>
								</tr>
								</ezf:row>
							</tbody>
							</table>
							</div>
						</td>
						<td valign="top">
							<%-- RIGHT-TABLE(TOP) --%>
							<div id="rightTopTBL" style="overflow-y:hidden; overflow-x:hidden; width:419;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTBL' ) );">
							<table cellpadding="1" cellspacing="0" border="1" width="755" height="25">
								<col width="125">
								<col width="125">
								<col width="125">
								<col width="60">
								<col width="125">
								<col width="125">
								<tr align="center">
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxBizProcAvgTmTxt_MS' )">Business<BR>Processing Time<BR>(ms)<img id="sortIMG.xxBizProcAvgTmTxt_MS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxBizThrupTxt_A' )">Business<BR>Throughput<BR>(byte/sec)<img id="sortIMG.xxBizThrupTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxGlblAreaAvgSizeTxt_A' )">Global Area<BR>Size (bytes)<img id="sortIMG.xxGlblAreaAvgSizeTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxTotCnt_A' )">Sampling<BR>Count<img id="sortIMG.xxTotCnt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxBizProcAvgTmTxt_SD' )">Business<BR>Processing Time<BR>(STDEV)<img id="sortIMG.xxBizProcAvgTmTxt_SD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxGlblAreaAvgSizeStdevTxt_SD' )">Global Area<BR>Size (STDEV)<img id="sortIMG.xxGlblAreaAvgSizeStdevTxt_SD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
								</tr>
							</table>
							</div>
							<%-- RIGHT-TABLE(MID) --%>
							<div id="rightTBL" style="overflow-y:scroll; height:410; overflow-x:scroll; width:437;" onScroll="synchroScrollTop( this.id, new Array( 'leftTBL' ) );synchroScrollLeft( this.id, new Array( 'rightTopTBL' ) );">
							<table cellpadding="1" cellspacing="0" border="1" width="755" id="A_RightTBL">
							<tbody>
								<col width="125">
								<col width="125">
								<col width="125">
								<col width="60">
								<col width="125">
								<col width="125">
								<ezf:row ezfHyo="A">
								<tr id="id_row_{EZF_ROW_NUMBER}">
									<td align="right">
										<ezf:label name="xxBizProcAvgTmTxt_MS" ezfName="xxBizProcAvgTmTxt_MS" ezfHyo="A" ezfArrayNo="0" />
									</td>
									<td align="right">
										<ezf:label name="xxBizThrupTxt_A" ezfName="xxBizThrupTxt_A" ezfHyo="A" ezfArrayNo="0" />
									</td>
									<td align="right">
										<ezf:label name="xxGlblAreaAvgSizeTxt_A" ezfName="xxGlblAreaAvgSizeTxt_A" ezfHyo="A" ezfArrayNo="0" />
									</td>
									<td align="right">
										<ezf:label name="xxTotCnt_A" ezfName="xxTotCnt_A" ezfHyo="A" ezfArrayNo="0" />
									</td>
									<td align="right">
										<ezf:label name="xxBizProcAvgTmTxt_SD" ezfName="xxBizProcAvgTmTxt_SD" ezfHyo="A" ezfArrayNo="0" />
									</td>									
									<td align="right">
										<ezf:label name="xxGlblAreaAvgSizeStdevTxt_SD" ezfName="xxGlblAreaAvgSizeStdevTxt_SD" ezfHyo="A" ezfArrayNo="0" />
									</td>
								</tr>
								</ezf:row>
							</tbody>
							</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>	
</div>		

<%-- **** Task specific area ends here **** --%>
