<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20140619022810 --%>
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

<%
	business.servlet.ZZOL0051.ZZOL0051BMsg bmsg = (business.servlet.ZZOL0051.ZZOL0051BMsg) databean.getEZDBMsg();
	pageContext.setAttribute("glblCmpyCd_InputProtect",    bmsg.glblCmpyCd.isInputProtected());
%>
			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="ZZOL0051Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="CSV Upload Config Detail">
			
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
	<tr>
		<td>
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
														Archive View
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='Hold Release'>
													<td width="90px" align="center" class="disabled">
														Hld Rlse
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
														Soft Alloc
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='Hard Allocation'>
													<td width="90px" align="center" class="disabled">
														Hard Alloc
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='Order Inquiry by Status'>
													<td width="90px" align="center" class="disabled">
														Ordr Inq
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='Sales Summary'>
													<td width="90px" align="center" class="disabled">
														Sales Sum
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='ATP Inquiry by Order'>
													<td width="90px" align="center" class="disabled">
														ATP Inq
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='iW Remote Upload'>
													<td width="90px" align="center" class="disabled">
														iW Rmt Upld
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='iW Remote Inquiry'>
													<td width="90px" align="center" class="disabled">
														iW Rmt Inq
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='Upload Screen for LAN Data from Canon Inc.'>
													<td width="90px" align="center" class="disabled">
														LAN Upld
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
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
				</table> -->
			<div class="pTab_BODY">
<!-- **************************************** HEADER **************************************** -->
			<table width="1100" align="center" border="0" cellpadding="1" cellspacing="0">
				<tr style="text-align: center">
					<td>
						<table align="left" border="0" cellpadding="1" cellspacing="0">
							<!-- Global Company Code -->
							<tr align="left">
								<td class="stab" width="170">Global Company CD</td>
								<td width="650">
									<ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXXX" otherAttr=" size=\"4\" ezftoupper=\"\""/>
								</td>
								<td width="120"></td>
							</tr>
							<!-- Upload CSV ID -->
							<tr align="left">
								<td class="stab" width="170">Upload CSV ID</td>
								<td width="650">
									<ezf:inputText name="upldCsvId" ezfName="upldCsvId" value="XXXXXXXXX1" otherAttr=" size=\"11\" maxlength=\"10\" ezftoupper=\"\""/>
								</td>
								<td width="120"></td>
							</tr>
							<!-- Upload CSV Name -->
							<tr align="left">
								<td class="stab" width="170">Upload CSV Name</td>
								<td width="650">
									<ezf:inputText name="upldCsvNm" ezfName="upldCsvNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXX" otherAttr=" size=\"65\" maxlength=\"64\""/>
								</td>
								<td width="120"></td>
							</tr>
							<!-- File ID -->
							<tr align="left">
								<td class="stab" width="170">File ID</td>
								<td width="650">
									<table border="0" cellpadding="0" cellspacing="0">
										<tr align="left">
											<td>
												<ezf:inputText name="upldCsvFileId" ezfName="upldCsvFileId" value="XXXXXXXXX1X" otherAttr=" size=\"31\" maxlength=\"11\" ezftoupper=\"\""/>
											</td>
											<td width="10"></td>
											<td>
												<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
											</td>
										</tr>
									</table>
								</td>
								<td width="120"></td>
							</tr>
							<!-- Table ID -->
							<tr align="left">
								<td class="stab" width="170">Table ID</td>
								<td width="650">
									<ezf:inputText name="upldCsvTempTblId" ezfName="upldCsvTempTblId" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"31\" maxlength=\"30\" ezftoupper=\"\""/>
								</td>
								<td width="120"></td>
							</tr>
							<!-- Request Job Net ID -->
							<tr align="left">
								<td class="stab" width="170">Request Job Net ID</td>
								<td width="650">
									<ezf:inputText name="ezReqBusinessID" ezfName="ezReqBusinessID" value="XXXXXXXXX" otherAttr=" size=\"31\" maxlength=\"16\" ezftoupper=\"\""/>
								</td>
								<td width="120"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
<!-- **************************************** HEADER **************************************** -->
			<table height="398" >
				<col valign="top">
				<tr>
					<td>
						<!-- **************************************** HTML inner TAB Frame **************************************** -->
						<table>
							<col valign="top">
							<tr>
								<td>
									<div class="pTab_HEAD">
										<ul>
											<li class="pTab_ON"  id="Header"    title="CSV Header"><ezf:anchor name="" ezfName="xxTabProt_H" ezfEmulateBtn="1" ezfGuard="TAB_Header" >CSV Header</ezf:anchor></li>
											<li class="pTab_OFF" id="BizAppId"  title="Application ID Relation"><ezf:anchor name="" ezfName="xxTabProt_B" ezfEmulateBtn="1" ezfGuard="TAB_BizAppId" >BizAppId</ezf:anchor></li>
											<li class="pTab_OFF" id="ProcessId" title="Process ID Relation"><ezf:anchor name="" ezfName="xxTabProt_P" ezfEmulateBtn="1" ezfGuard="TAB_ProcessId" >ProcessId</ezf:anchor></li>
										</ul>
									</div>

									<%-- ************************ TAB ************************ --%>
									<c:choose>
										<%-- ************************ CSV Header ************************ --%>
										<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Header'}">
											<script type="text/javascript">
												document.getElementById( "Header"   ).className = "pTab_ON";
												document.getElementById( "BizAppId" ).className = "pTab_OFF";
												document.getElementById( "ProcessId").className = "pTab_OFF";
											</script>
											<div class="pTab_BODY_In">
												<table height="360" width="1118">
													<col valign="top">
													<tr>
														<td>

															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td valign="top">
																		<%-- LEFT-TABLE(TOP) --%>
																		<div id="leftTopTBL" style="overflow-y:hidden; height:; overflow-x:hidden; width:454;">
																			<table border="1" cellpadding="1" cellspacing="0">
																				<col width="38">
																				<col width="406">
																				<tr>
																					<td style="text-align: center" class="pClothBs" nowrap>No</td>
																					<td style="text-align: left" class="pClothBs" nowrap>Data Item Name</td>
																				</tr>
																			</table>
																		</div>
																		<%-- LEFT-TABLE(MID) --%>
																		<div id="leftTBL" style="overflow-y:hidden; height:318; overflow-x:hidden; width:454;" onScroll="synchroScrollTop( this.id, new Array( 'rightTBL' ) );">
																			<table cellpadding="1" cellspacing="0" border="1" id="A_LeftTBL">
																				<col width="38">
																				<col width="405">
																				<tbody>
																					<ezf:row ezfHyo="H">
																					<tr height="28">
																						<%-- No --%>
																						<td style="text-align: center"><ezf:label name="upldCsvHdrNum_H" ezfName="upldCsvHdrNum_H" ezfHyo="H" ezfArrayNo="0" /></td>
																						<%-- Data Item Name --%>
																						<td>
																							<ezf:inputText name="upldCsvHdrNm_H" ezfName="upldCsvHdrNm_H" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"100\""/>
																						</td>
																					</tr>
																					</ezf:row>

																					<ezf:skip>
																					</ezf:skip>

																				</tbody>
																			</table>
																		</div>
																	</td>
																	<td valign="top">
																		<%-- RIGHT-TABLE(TOP) --%>
																		<div id="rightTopTBL" style="overflow-y:none; height:; overflow-x:hidden; width:641;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTBL' ) );">
																			<table cellpadding="1" cellspacing="0" border="1" width="745">
																				<col width="241">
																				<col style="text-align: center" width="82">
																				<col width="405">
																				<tr style="text-align: left">
																					<td class="pClothBs" nowrap>Data Type</td>
																					<td class="pClothBs" nowrap>Length</td>
																					<td class="pClothBs" nowrap>CSV Column Header </td>
																				</tr>
																			</table>
																		</div>
																		<%-- RIGHT-TABLE(MID) --%>
																		<div id="rightTBL" style="overflow-y:scroll; height:335; overflow-x:scroll; width:658;" onscroll="synchroScrollTop( this.id, new Array( 'leftTBL' ) );synchroScrollLeft( this.id, new Array( 'rightTopTBL' ) );">
																			<table cellpadding="1" cellspacing="0" border="1" width="745" id="A_RightTBL">
																				<col width="242">
																				<col style="text-align: right" width="82">
																				<col width="405">
																				<tbody>
																					<ezf:row ezfHyo="H">
																					<tr height="28">
																						<%-- Data Type --%>
																						<td><ezf:label name="upldCsvHdrDataTpNm_H" ezfName="upldCsvHdrDataTpNm_H" ezfHyo="H" ezfArrayNo="0" /></td>
																						<%-- Length --%>
																						<td><ezf:label name="upldCsvHdrDataLg_H" ezfName="upldCsvHdrDataLg_H" ezfHyo="H" ezfArrayNo="0" /></td>
																						<%-- CSV Column Header --%>
																						<td>
																							<ezf:inputText name="upldCsvHdrDefNm_H" ezfName="upldCsvHdrDefNm_H" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"100\""/>
																						</td>
																					</tr>
																					</ezf:row>

																					<ezf:skip>
																					</ezf:skip>

																				</tbody>
																			</table>
																		</div>
																	</td>
																</tr>
															</table>

														</td>
													</tr>
												</table>

											</div>
										</c:when>
										<%-- ************************ Application ID Relation ************************ --%>
										 <c:when test="${pageScope._ezddatabean.xxDplyTab == 'BizAppId'}">
											<script type="text/javascript">
												document.getElementById( "Header"   ).className = "pTab_OFF";
												document.getElementById( "BizAppId" ).className = "pTab_ON";
												document.getElementById( "ProcessId").className = "pTab_OFF";
											</script>
											<div class="pTab_BODY_In">
												<table height="360" width="1118" border="0">
													<col valign="top">
													<tr>
														<td>

															<%-- Add button --%>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="423"/>
																	<td>
																		<ezf:inputButton name="AddRow" value="Add" htmlClass="pBtn7" />
																	</td>
																</tr>
															</table>
															
															<hr size="1" width="24%" noshade>

														</td>
													</tr>
													<tr>
														<td style="text-align: center">

															<table border="1" cellpadding="0" cellspacing="0">
																<tr>
																	<td valign="top">
																		<%-- LEFT-TABLE(TOP) --%>
																		<div id="leftTopTBL" style="overflow-y:hidden; height:; overflow-x:hidden; width:;">
																			<table border="1" cellpadding="1" cellspacing="0">
																				<col width="36">
																				<col width="32">
																				<tr style="text-align: center">
																					<td class="pClothBs" nowrap>No</td>
																					<td class="pClothBs" nowrap></td>
																				</tr>
																			</table>
																		</div>
																		<%-- LEFT-TABLE(MID) --%>
																		<div id="leftTBL" style="overflow-y:hidden; height:286; overflow-x:hidden; width:;" onScroll="synchroScrollTop( this.id, new Array( 'rightTBL' ) );">
																			<table cellpadding="1" cellspacing="0" border="1" id="A_LeftTBL">
																				<col width="36">
																				<col width="32">
																				<tbody>
																					<ezf:row ezfHyo="B">
																					<tr height="28">
																						<%-- No --%>
																						<td style="text-align: center"><ezf:label name="xxNum_B" ezfName="xxNum_B" ezfHyo="B" ezfArrayNo="0" /></td>
																						<%-- # --%>
																						<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
																					</tr>
																					</ezf:row>

																					<ezf:skip>
																					</ezf:skip>

																				</tbody>
																			</table>
																		</div>
																	</td>
																	<td valign="top">
																		<%-- RIGHT-TABLE(TOP) --%>
																		<div id="rightTopTBL" style="overflow-y:none; height:; overflow-x:hidden; width:164;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTBL' ) );">
																			<table cellpadding="1" cellspacing="0" border="1">
																				<col width="164">
																				<tr style="text-align: center">
																					<td class="pClothBs" nowrap>Business Application ID</td>
																				</tr>
																			</table>
																		</div>
																		<%-- RIGHT-TABLE(MID) --%>
																		<div id="rightTBL" style="overflow-y:scroll; height:286; overflow-x:hidden; width:181;" onscroll="synchroScrollTop( this.id, new Array( 'leftTBL' ) );synchroScrollLeft( this.id, new Array( 'rightTopTBL' ) );">
																			<table cellpadding="1" cellspacing="0" border="1" id="A_RightTBL">
																				<col width="164">
																				<tbody>
																					<ezf:row ezfHyo="B">
																					<tr height="28">
																						<%-- Business Application ID --%>
																						<td>
																							<ezf:inputText name="upldCsvRstBizAppId_B" ezfName="upldCsvRstBizAppId_B" value="XXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"8\" ezftoupper=\"\""/>
																						</td>
																					</tr>
																					</ezf:row>

																					<ezf:skip>
																					</ezf:skip>

																				</tbody>
																			</table>
																		</div>
																	</td>
																</tr>
															</table>

														</td>
													</tr>
												</table>

											</div>
										</c:when>
										<%-- ************************ Process ID Relation ************************ --%>
										<c:when test="${pageScope._ezddatabean.xxDplyTab == 'ProcessId'}">
											<script type="text/javascript">
												document.getElementById( "Header"   ).className = "pTab_OFF";
												document.getElementById( "BizAppId" ).className = "pTab_OFF";
												document.getElementById( "ProcessId").className = "pTab_ON";
											</script>
											<div class="pTab_BODY_In">
												<table height="360" width="1118" border="0">
													<col valign="top">
													<tr>
														<td>

															<%-- Add button --%>
															<table width="155" border="0">
																<tr>
																	<td align="right" >
																		<ezf:inputButton name="AddRow" value="Add" htmlClass="pBtn7" />
																	</td>
																</tr>
															</table>
															
															<hr width="88%" noshade>

															<table align="center" border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td valign="top">
																		<%-- LEFT-TABLE(TOP) --%>
																		<div id="leftTopTBL" style="overflow-y:hidden; height:; overflow-x:hidden; width:;">
																			<table border="1" cellpadding="1" cellspacing="0">
																				<col width="38">
																				<col width="32">
																				<col width="78">
																				<tr style="text-align: center">
																					<td class="pClothBs" nowrap>No</td>
																					<td class="pClothBs" nowrap></td>
																					<td class="pClothBs" nowrap></td>
																				</tr>
																			</table>
																		</div>
																		<%-- LEFT-TABLE(MID) --%>
																		<div id="leftTBL" style="overflow-y:hidden; height:286; overflow-x:hidden; width:;" onScroll="synchroScrollTop( this.id, new Array( 'rightTBL' ) );">
																			<table cellpadding="1" cellspacing="0" border="1" id="A_LeftTBL">
																				<col width="38">
																				<col width="32">
																				<col width="78">
																				<tbody>
																					<ezf:row ezfHyo="P">
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><ezf:label name="xxNum_P" ezfName="xxNum_P" ezfHyo="P" ezfArrayNo="0" /></td>
																						<%-- # --%>
																						<td align="center"><ezf:inputCheckBox name="xxChkBox_P" ezfName="xxChkBox_P" value="Y" ezfHyo="P" ezfArrayNo="0" /></td>
																						<%-- Select --%>
																						<td style="text-align: center"><ezf:inputButton name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" htmlClass="pBtn6" /></td>
																					</tr>
																					</ezf:row>

																					<ezf:skip>
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>	
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>	
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>	
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>	
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>
																					<tr height="26">
																						<%-- No --%>
																						<td style="text-align: center"><label ezfout name="xxNum_P" ezfname="xxNum_P" ezfhyo="P">1</label></td>
																						<%-- # --%>
																						<td align="center"><input type="checkbox" name="xxChkBox_P" value="Y" ezfname="xxChkBox_P" ezfhyo="P"></td>
																						<%-- Select --%>
																						<td style="text-align: center"><input type="button" class="pBtn6" name="ProcessIdLookup" value="Select" ezfHyo="P" ezfArrayNo="0" /></td>
																					</tr>																																																																																																																												
																					</ezf:skip>

																				</tbody>
																			</table>
																		</div>
																	</td>
																	<td valign="top">
																		<%-- RIGHT-TABLE(TOP) --%>
																		<div id="rightTopTBL" style="overflow-y:none; height:; overflow-x:hidden; width:797;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTBL' ) );">
																			<table cellpadding="1" cellspacing="0" border="1">
																				<col style="text-align: center" width="158">
																				<col width="634">
																				<tr>
																					<td class="pClothBs" nowrap>Business Process ID</td>
																					<td class="pClothBs" nowrap>Business Process Name</td>
																				</tr>
																			</table>
																		</div>
																		<%-- RIGHT-TABLE(MID) --%>
																		<div id="rightTBL" style="overflow-y:scroll; height:286; overflow-x:hidden; width:814;" onscroll="synchroScrollTop( this.id, new Array( 'leftTBL' ) );synchroScrollLeft( this.id, new Array( 'rightTopTBL' ) );">
																			<table cellpadding="1" cellspacing="0" border="1" id="A_RightTBL">
																				<col style="text-align: center" width="158">
																				<col width="631">
																				<tbody>
																					<ezf:row ezfHyo="P">
																					<tr>
																						<%-- Business Process ID --%>
																						<td><ezf:label name="menuProcId_P" ezfName="menuProcId_P" ezfHyo="P" ezfArrayNo="0" /></td>
																						<%-- Data Type --%>
																						<td nowrap>
																							<ezf:inputText name="upldCsvRstProcNm_P" ezfName="upldCsvRstProcNm_P" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9" ezfHyo="P" ezfArrayNo="0" otherAttr=" size=\"78\" maxlength=\"100\""/>
																						</td>
																					</tr>
																					</ezf:row>

																					<ezf:skip>
																					<tr>
																						<%-- Business Process ID --%>
																						<td><label ezfout name="menuProcId_P" ezfname="menuProcId_P" ezfhyo="P">XXXXXXXXX1XXXXXXXX</label></td>
																						<%-- Data Type --%>
																						<td nowrap>
																							<input type="text" readonly="readonly" size="78" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9" name="upldCsvRstProcNm_P" ezfname="upldCsvRstProcNm_P" ezfhyo="P" />
																						</td>
																					</tr>
																					<tr>
																						<%-- Business Process ID --%>
																						<td><label ezfout name="menuProcId_P" ezfname="menuProcId_P" ezfhyo="P">XXXXXXXXX1XXXXXXXX</label></td>
																						<%-- Data Type --%>
																						<td nowrap>
																							<input type="text" readonly="readonly" size="78" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9" name="upldCsvRstProcNm_P" ezfname="upldCsvRstProcNm_P" ezfhyo="P" />
																						</td>
																					</tr>
																					<tr>
																						<%-- Business Process ID --%>
																						<td><label ezfout name="menuProcId_P" ezfname="menuProcId_P" ezfhyo="P">XXXXXXXXX1XXXXXXXX</label></td>
																						<%-- Data Type --%>
																						<td nowrap>
																							<input type="text" readonly="readonly" size="78" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9" name="upldCsvRstProcNm_P" ezfname="upldCsvRstProcNm_P" ezfhyo="P" />
																						</td>
																					</tr>
																					<tr>
																						<%-- Business Process ID --%>
																						<td><label ezfout name="menuProcId_P" ezfname="menuProcId_P" ezfhyo="P">XXXXXXXXX1XXXXXXXX</label></td>
																						<%-- Data Type --%>
																						<td nowrap>
																							<input type="text" readonly="readonly" size="78" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9" name="upldCsvRstProcNm_P" ezfname="upldCsvRstProcNm_P" ezfhyo="P" />
																						</td>
																					</tr>
																					<tr>
																						<%-- Business Process ID --%>
																						<td><label ezfout name="menuProcId_P" ezfname="menuProcId_P" ezfhyo="P">XXXXXXXXX1XXXXXXXX</label></td>
																						<%-- Data Type --%>
																						<td nowrap>
																							<input type="text" readonly="readonly" size="78" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9" name="upldCsvRstProcNm_P" ezfname="upldCsvRstProcNm_P" ezfhyo="P" />
																						</td>
																					</tr>
																					<tr>
																						<%-- Business Process ID --%>
																						<td><label ezfout name="menuProcId_P" ezfname="menuProcId_P" ezfhyo="P">XXXXXXXXX1XXXXXXXX</label></td>
																						<%-- Data Type --%>
																						<td nowrap>
																							<input type="text" readonly="readonly" size="78" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9" name="upldCsvRstProcNm_P" ezfname="upldCsvRstProcNm_P" ezfhyo="P" />
																						</td>
																					</tr>
																					<tr>
																						<%-- Business Process ID --%>
																						<td><label ezfout name="menuProcId_P" ezfname="menuProcId_P" ezfhyo="P">XXXXXXXXX1XXXXXXXX</label></td>
																						<%-- Data Type --%>
																						<td nowrap>
																							<input type="text" readonly="readonly" size="78" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9" name="upldCsvRstProcNm_P" ezfname="upldCsvRstProcNm_P" ezfhyo="P" />
																						</td>
																					</tr>
																					<tr>
																						<%-- Business Process ID --%>
																						<td><label ezfout name="menuProcId_P" ezfname="menuProcId_P" ezfhyo="P">XXXXXXXXX1XXXXXXXX</label></td>
																						<%-- Data Type --%>
																						<td nowrap>
																							<input type="text" readonly="readonly" size="78" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9" name="upldCsvRstProcNm_P" ezfname="upldCsvRstProcNm_P" ezfhyo="P" />
																						</td>
																					</tr>
																					<tr>
																						<%-- Business Process ID --%>
																						<td><label ezfout name="menuProcId_P" ezfname="menuProcId_P" ezfhyo="P">XXXXXXXXX1XXXXXXXX</label></td>
																						<%-- Data Type --%>
																						<td nowrap>
																							<input type="text" readonly="readonly" size="78" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9" name="upldCsvRstProcNm_P" ezfname="upldCsvRstProcNm_P" ezfhyo="P" />
																						</td>
																					</tr>
																					<tr>
																						<%-- Business Process ID --%>
																						<td><label ezfout name="menuProcId_P" ezfname="menuProcId_P" ezfhyo="P">XXXXXXXXX1XXXXXXXX</label></td>
																						<%-- Data Type --%>
																						<td nowrap>
																							<input type="text" readonly="readonly" size="78" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9" name="upldCsvRstProcNm_P" ezfname="upldCsvRstProcNm_P" ezfhyo="P" />
																						</td>
																					</tr>
																					<tr>
																						<%-- Business Process ID --%>
																						<td><label ezfout name="menuProcId_P" ezfname="menuProcId_P" ezfhyo="P">XXXXXXXXX1XXXXXXXX</label></td>
																						<%-- Data Type --%>
																						<td nowrap>
																							<input type="text" readonly="readonly" size="78" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9" name="upldCsvRstProcNm_P" ezfname="upldCsvRstProcNm_P" ezfhyo="P" />
																						</td>
																					</tr>
																					<tr>
																						<%-- Business Process ID --%>
																						<td><label ezfout name="menuProcId_P" ezfname="menuProcId_P" ezfhyo="P">XXXXXXXXX1XXXXXXXX</label></td>
																						<%-- Data Type --%>
																						<td nowrap>
																							<input type="text" readonly="readonly" size="78" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9" name="upldCsvRstProcNm_P" ezfname="upldCsvRstProcNm_P" ezfhyo="P" />
																						</td>
																					</tr>
																					<tr>
																						<%-- Business Process ID --%>
																						<td><label ezfout name="menuProcId_P" ezfname="menuProcId_P" ezfhyo="P">XXXXXXXXX1XXXXXXXX</label></td>
																						<%-- Data Type --%>
																						<td nowrap>
																							<input type="text" readonly="readonly" size="78" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9" name="upldCsvRstProcNm_P" ezfname="upldCsvRstProcNm_P" ezfhyo="P" />
																						</td>
																					</tr>
																					<tr>
																						<%-- Business Process ID --%>
																						<td><label ezfout name="menuProcId_P" ezfname="menuProcId_P" ezfhyo="P">XXXXXXXXX1XXXXXXXX</label></td>
																						<%-- Data Type --%>
																						<td nowrap>
																							<input type="text" readonly="readonly" size="78" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9" name="upldCsvRstProcNm_P" ezfname="upldCsvRstProcNm_P" ezfhyo="P" />
																						</td>
																					</tr>
																					<tr>
																						<%-- Business Process ID --%>
																						<td><label ezfout name="menuProcId_P" ezfname="menuProcId_P" ezfhyo="P">XXXXXXXXX1XXXXXXXX</label></td>
																						<%-- Data Type --%>
																						<td nowrap>
																							<input type="text" readonly="readonly" size="78" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9" name="upldCsvRstProcNm_P" ezfname="upldCsvRstProcNm_P" ezfhyo="P" />
																						</td>
																					</tr>
																					<tr>
																						<%-- Business Process ID --%>
																						<td><label ezfout name="menuProcId_P" ezfname="menuProcId_P" ezfhyo="P">XXXXXXXXX1XXXXXXXX</label></td>
																						<%-- Data Type --%>
																						<td nowrap>
																							<input type="text" readonly="readonly" size="78" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9" name="upldCsvRstProcNm_P" ezfname="upldCsvRstProcNm_P" ezfhyo="P" />
																						</td>
																					</tr>																																																																																																																																																			
																					</ezf:skip>

																				</tbody>
																			</table>
																		</div>
																	</td>
																</tr>
															</table>

														</td>
													</tr>
												</table>
											</div>
										</c:when>
									</c:choose>
									<%-- ************************ TAB ************************ --%>
								</td>
							</tr>
						</table>
						<!-- **************************************** HTML inner TAB Frame **************************************** -->
					</td>
				<tr>
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
	
	function synchroScroll_fromRightTblAction2() {
		var rightTopTBL = this.document.getElementById( 'rightTopTBL2' );
		var rightTBL    = this.document.getElementById( 'rightTBL2'     );
		var leftTBL     = this.document.getElementById( 'leftTBL2' );
		
		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
		// synchronize scroll - X
		rightTopTBL.scrollLeft = rightTBL.scrollLeft;
	}
	
	function synchroScroll_fromLeftTblAction2() {
		var leftTBL  = this.document.getElementById( 'leftTBL2'  );
		var rightTBL = this.document.getElementById( 'rightTBL2' );
		
		// synchronize scroll - Y
		rightTBL.scrollTop = leftTBL.scrollTop;
	}
</script>


<%-- **** Task specific area ends here **** --%>
