<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100204014247 --%>
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
			<input type="hidden" name="pageID" value="ZZOL0050Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="CSV Upload Config Search">
			
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
			<table width="990" align="center" border="0" cellpadding="1" cellspacing="0">
				<tr style="text-align: center">
					<td>
						<table border="0" cellpadding="1" cellspacing="0">
							<!-- Global Company Code -->
							<tr align="left">
								<td class="stab" width="170"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company CD</ezf:anchor></td>
								<td width="650">
									<ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXXX" otherAttr=" size=\"4\" ezftoupper=\"\""/>
								</td>
								<td width="120"></td>
							</tr>
							<!-- Upload CSV ID -->
							<tr align="left">
								<td class="stab" width="170">Upload CSV ID (*)</td>
								<td width="650">
									<ezf:inputText name="upldCsvId" ezfName="upldCsvId" value="XXXXXXXXX1" otherAttr=" size=\"11\" maxlength=\"10\" ezftoupper=\"\""/>
								</td>
								<td width="120"></td>
							</tr>
							<!-- Upload CSV  Name -->
							<tr align="left">
								<td class="stab" width="170">Upload CSV Name (*)</td>
								<td width="650">
									<ezf:inputText name="upldCsvNm" ezfName="upldCsvNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXX" otherAttr=" size=\"65\" maxlength=\"64\""/>
								</td>
								<td width="120" align="right">
									<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
<!-- **************************************** HEADER **************************************** -->
			<hr size="1" width="88%" noshade>
			<%-- add --%>
			<table align="center" width="1000" border="0" cellpadding="1" cellspacing="0">
				<tr>
					<td width="10%" align="left">
						<table border="0" cellpadding="0" cellspacing="0">
							<col>
							<tr>
								<td><ezf:inputButton name="Add" value="Add" htmlClass="pBtn6" /></td>
							</tr>
						</table>
					</td>
					<td width="90%" align="right">
						
						<!--<table border="0" cellpadding="1" cellspacing="0">
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
								<td class="pOut">40</td>
								<td class="stab">of</td>
								<td class="pOut">200</td>
								<td>&nbsp;</td>
								<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
								<td></td>
								<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
							</tr>
						</table>-->
						
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
			<%-- item --%>
			<table align="center" border="0" cellpadding="0" cellspacing="0">
				<col width="80">
				<col width="920">
				<tr>
					<td valign="top">
						<%-- LEFT-TABLE(TOP) --%>
						<div id="leftTopTBL" style="overflow-y:hidden; height:28; overflow-x:hidden; width:;">
							<table border="1" cellpadding="1" cellspacing="0">
								<col width="32">
								<col width="48">
								<tr style="text-align: center" height="28">
									<td class="pClothBs" nowrap></td>
									<td class="pClothBs" nowrap></td>
								</tr>
							</table>
						</div>
						
						<%-- LEFT-TABLE(MID) --%>
						<div id="leftTBL" style="overflow-y:hidden; height:420; overflow-x:hidden; width:;" onScroll="synchroScrollTop( this.id, new Array( 'rightTBL' ) );">
							<table cellpadding="1" cellspacing="0" border="1" id="A_LeftTBL">
								<col width="32">
								<col width="48">
								<tbody>
									<ezf:row ezfHyo="A">
									<tr style="text-align: left" height="40">
										<!-- # -->
										<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
										<!-- Edit -->
										<td align="center"><ezf:inputButton name="Edit" value="Edit" ezfHyo="A" ezfArrayNo="0" htmlClass="cBtn1" /></td>
									</tr>
									</ezf:row>

									<ezf:skip>
									<tr style="text-align: left" height="40">
										<!-- # -->
										<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
										<!-- Edit -->
										<td align="center"><input type="button" class="cBtn1" value="Edit" name="Edit" ezfhyo="A" ezfArrayNo="0" onclick="sendServer(this)"></td>
									</tr>
									<tr style="text-align: left" height="40">
										<!-- # -->
										<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
										<!-- Edit -->
										<td align="center"><input type="button" class="cBtn1" value="Edit" name="Edit" ezfhyo="A" ezfArrayNo="0" onclick="sendServer(this)"></td>
									</tr>
									<tr style="text-align: left" height="40">
										<!-- # -->
										<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
										<!-- Edit -->
										<td align="center"><input type="button" class="cBtn1" value="Edit" name="Edit" ezfhyo="A" ezfArrayNo="0" onclick="sendServer(this)"></td>
									</tr>
									<tr style="text-align: left" height="40">
										<!-- # -->
										<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
										<!-- Edit -->
										<td align="center"><input type="button" class="cBtn1" value="Edit" name="Edit" ezfhyo="A" ezfArrayNo="0" onclick="sendServer(this)"></td>
									</tr>
									<tr style="text-align: left" height="40">
										<!-- # -->
										<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
										<!-- Edit -->
										<td align="center"><input type="button" class="cBtn1" value="Edit" name="Edit" ezfhyo="A" ezfArrayNo="0" onclick="sendServer(this)"></td>
									</tr>
									<tr style="text-align: left" height="40">
										<!-- # -->
										<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
										<!-- Edit -->
										<td align="center"><input type="button" class="cBtn1" value="Edit" name="Edit" ezfhyo="A" ezfArrayNo="0" onclick="sendServer(this)"></td>
									</tr>
									<tr style="text-align: left" height="40">
										<!-- # -->
										<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
										<!-- Edit -->
										<td align="center"><input type="button" class="cBtn1" value="Edit" name="Edit" ezfhyo="A" ezfArrayNo="0" onclick="sendServer(this)"></td>
									</tr>
									<tr style="text-align: left" height="40">
										<!-- # -->
										<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
										<!-- Edit -->
										<td align="center"><input type="button" class="cBtn1" value="Edit" name="Edit" ezfhyo="A" ezfArrayNo="0" onclick="sendServer(this)"></td>
									</tr>
									<tr style="text-align: left" height="40">
										<!-- # -->
										<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
										<!-- Edit -->
										<td align="center"><input type="button" class="cBtn1" value="Edit" name="Edit" ezfhyo="A" ezfArrayNo="0" onclick="sendServer(this)"></td>
									</tr>
									<tr style="text-align: left" height="40">
										<!-- # -->
										<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
										<!-- Edit -->
										<td align="center"><input type="button" class="cBtn1" value="Edit" name="Edit" ezfhyo="A" ezfArrayNo="0" onclick="sendServer(this)"></td>
									</tr>
									<tr style="text-align: left" height="40">
										<!-- # -->
										<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
										<!-- Edit -->
										<td align="center"><input type="button" class="cBtn1" value="Edit" name="Edit" ezfhyo="A" ezfArrayNo="0" onclick="sendServer(this)"></td>
									</tr>
									<tr style="text-align: left" height="40">
										<!-- # -->
										<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
										<!-- Edit -->
										<td align="center"><input type="button" class="cBtn1" value="Edit" name="Edit" ezfhyo="A" ezfArrayNo="0" onclick="sendServer(this)"></td>
									</tr>
									<tr style="text-align: left" height="40">
										<!-- # -->
										<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
										<!-- Edit -->
										<td align="center"><input type="button" class="cBtn1" value="Edit" name="Edit" ezfhyo="A" ezfArrayNo="0" onclick="sendServer(this)"></td>
									</tr>
									<tr style="text-align: left" height="40">
										<!-- # -->
										<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
										<!-- Edit -->
										<td align="center"><input type="button" class="cBtn1" value="Edit" name="Edit" ezfhyo="A" ezfArrayNo="0" onclick="sendServer(this)"></td>
									</tr>
									<tr style="text-align: left" height="40">
										<!-- # -->
										<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
										<!-- Edit -->
										<td align="center"><input type="button" class="cBtn1" value="Edit" name="Edit" ezfhyo="A" ezfArrayNo="0" onclick="sendServer(this)"></td>
									</tr>
									<tr style="text-align: left" height="40">
										<!-- # -->
										<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
										<!-- Edit -->
										<td align="center"><input type="button" class="cBtn1" value="Edit" name="Edit" ezfhyo="A" ezfArrayNo="0" onclick="sendServer(this)"></td>
									</tr>
																																																																																											
									</ezf:skip>

								</tbody>
							</table>
						</div>
						
					</td>
					<td>
						<%-- RIGHT-TABLE(TOP) --%>
						<div id="rightTopTBL" style="overflow-y:hidden; height:28; overflow-x:hidden; width:904;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTBL' ) );">
							<table cellpadding="1" cellspacing="0" border="1" width="903">
								<col width="98">
								<col width="335">
								<col width="100">
								<col width="250">
								<col width="120">
								<tr style="text-align: left" height="28">
									<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'upldCsvId_A'        )">Upload CSV ID<img id="sortIMG.upldCsvId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'upldCsvNm_A'        )">Upload CSV Name<img id="sortIMG.upldCsvNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'upldCsvFileId_A'    )">File ID<img id="sortIMG.upldCsvFileId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'upldCsvTempTblId_A' )">Table ID<img id="sortIMG.upldCsvTempTblId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezReqBusinessID_A'  )">Request Job Net ID<img id="sortIMG.ezReqBusinessID_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
								</tr>
							</table>
						</div>
						
						<%-- RIGHT-TABLE(MID) --%>
						<div id="rightTBL" style="overflow-y:scroll; height:420; overflow-x:hidden; width:920;" onscroll="synchroScrollTop( this.id, new Array( 'leftTBL' ) );synchroScrollLeft( this.id, new Array( 'rightTopTBL' ) );">
							<table cellpadding="1" cellspacing="0" border="1" width="903" id="A_RightTBL">
								<col width="97">
								<col width="335">
								<col width="99">
								<col width="251">
								<col width="119">
								<tbody>
									<ezf:row ezfHyo="A">
									<tr height="40">
										<!-- Upload CSV ID -->
										<td><ezf:label name="upldCsvId_A" ezfName="upldCsvId_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<!-- Upload CSV Name -->
										<td style="word-break:break-all;"><ezf:label name="upldCsvNm_A" ezfName="upldCsvNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<!-- File ID -->
										<td><ezf:label name="upldCsvFileId_A" ezfName="upldCsvFileId_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<!-- Table ID -->
										<td style="word-break:break-all;"><ezf:label name="upldCsvTempTblId_A" ezfName="upldCsvTempTblId_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<!-- Request Job Net ID -->
										<td><ezf:label name="ezReqBusinessID_A" ezfName="ezReqBusinessID_A" ezfHyo="A" ezfArrayNo="0" /></td>
									</tr>
									</ezf:row>

									<ezf:skip>
									<tr height="40">
										<!-- Upload CSV ID -->
										<td><label ezfout name="upldCsvId_A" ezfname="upldCsvId_A" ezfhyo="A">XXXXXXXXX1</label></td>
										<!-- Upload CSV Name -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvNm_A" ezfname="upldCsvNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXX</label></td>
										<!-- File ID -->
										<td><label ezfout name="upldCsvFileId_A" ezfname="upldCsvFileId_A" ezfhyo="A">XXXXXXXXX1X</label></td>
										<!-- Table ID -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvTempTblId_A" ezfname="upldCsvTempTblId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
										<!-- Request Job Net ID -->
										<td><label ezfout name="ezReqBusinessID_A" ezfname="ezReqBusinessID_A" ezfhyo="A">XXXXXXXXX</label></td>
									</tr>
									<tr height="40">
										<!-- Upload CSV ID -->
										<td><label ezfout name="upldCsvId_A" ezfname="upldCsvId_A" ezfhyo="A">XXXXXXXXX1</label></td>
										<!-- Upload CSV Name -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvNm_A" ezfname="upldCsvNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXX</label></td>
										<!-- File ID -->
										<td><label ezfout name="upldCsvFileId_A" ezfname="upldCsvFileId_A" ezfhyo="A">XXXXXXXXX1X</label></td>
										<!-- Table ID -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvTempTblId_A" ezfname="upldCsvTempTblId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
										<!-- Request Job Net ID -->
										<td><label ezfout name="ezReqBusinessID_A" ezfname="ezReqBusinessID_A" ezfhyo="A">XXXXXXXXX</label></td>
									</tr>
									<tr height="40">
										<!-- Upload CSV ID -->
										<td><label ezfout name="upldCsvId_A" ezfname="upldCsvId_A" ezfhyo="A">XXXXXXXXX1</label></td>
										<!-- Upload CSV Name -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvNm_A" ezfname="upldCsvNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXX</label></td>
										<!-- File ID -->
										<td><label ezfout name="upldCsvFileId_A" ezfname="upldCsvFileId_A" ezfhyo="A">XXXXXXXXX1X</label></td>
										<!-- Table ID -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvTempTblId_A" ezfname="upldCsvTempTblId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
										<!-- Request Job Net ID -->
										<td><label ezfout name="ezReqBusinessID_A" ezfname="ezReqBusinessID_A" ezfhyo="A">XXXXXXXXX</label></td>
									</tr>
									<tr height="40">
										<!-- Upload CSV ID -->
										<td><label ezfout name="upldCsvId_A" ezfname="upldCsvId_A" ezfhyo="A">XXXXXXXXX1</label></td>
										<!-- Upload CSV Name -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvNm_A" ezfname="upldCsvNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXX</label></td>
										<!-- File ID -->
										<td><label ezfout name="upldCsvFileId_A" ezfname="upldCsvFileId_A" ezfhyo="A">XXXXXXXXX1X</label></td>
										<!-- Table ID -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvTempTblId_A" ezfname="upldCsvTempTblId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
										<!-- Request Job Net ID -->
										<td><label ezfout name="ezReqBusinessID_A" ezfname="ezReqBusinessID_A" ezfhyo="A">XXXXXXXXX</label></td>
									</tr>
									<tr height="40">
										<!-- Upload CSV ID -->
										<td><label ezfout name="upldCsvId_A" ezfname="upldCsvId_A" ezfhyo="A">XXXXXXXXX1</label></td>
										<!-- Upload CSV Name -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvNm_A" ezfname="upldCsvNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXX</label></td>
										<!-- File ID -->
										<td><label ezfout name="upldCsvFileId_A" ezfname="upldCsvFileId_A" ezfhyo="A">XXXXXXXXX1X</label></td>
										<!-- Table ID -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvTempTblId_A" ezfname="upldCsvTempTblId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
										<!-- Request Job Net ID -->
										<td><label ezfout name="ezReqBusinessID_A" ezfname="ezReqBusinessID_A" ezfhyo="A">XXXXXXXXX</label></td>
									</tr>
									<tr height="40">
										<!-- Upload CSV ID -->
										<td><label ezfout name="upldCsvId_A" ezfname="upldCsvId_A" ezfhyo="A">XXXXXXXXX1</label></td>
										<!-- Upload CSV Name -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvNm_A" ezfname="upldCsvNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXX</label></td>
										<!-- File ID -->
										<td><label ezfout name="upldCsvFileId_A" ezfname="upldCsvFileId_A" ezfhyo="A">XXXXXXXXX1X</label></td>
										<!-- Table ID -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvTempTblId_A" ezfname="upldCsvTempTblId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
										<!-- Request Job Net ID -->
										<td><label ezfout name="ezReqBusinessID_A" ezfname="ezReqBusinessID_A" ezfhyo="A">XXXXXXXXX</label></td>
									</tr>
									<tr height="40">
										<!-- Upload CSV ID -->
										<td><label ezfout name="upldCsvId_A" ezfname="upldCsvId_A" ezfhyo="A">XXXXXXXXX1</label></td>
										<!-- Upload CSV Name -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvNm_A" ezfname="upldCsvNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXX</label></td>
										<!-- File ID -->
										<td><label ezfout name="upldCsvFileId_A" ezfname="upldCsvFileId_A" ezfhyo="A">XXXXXXXXX1X</label></td>
										<!-- Table ID -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvTempTblId_A" ezfname="upldCsvTempTblId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
										<!-- Request Job Net ID -->
										<td><label ezfout name="ezReqBusinessID_A" ezfname="ezReqBusinessID_A" ezfhyo="A">XXXXXXXXX</label></td>
									</tr>
									<tr height="40">
										<!-- Upload CSV ID -->
										<td><label ezfout name="upldCsvId_A" ezfname="upldCsvId_A" ezfhyo="A">XXXXXXXXX1</label></td>
										<!-- Upload CSV Name -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvNm_A" ezfname="upldCsvNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXX</label></td>
										<!-- File ID -->
										<td><label ezfout name="upldCsvFileId_A" ezfname="upldCsvFileId_A" ezfhyo="A">XXXXXXXXX1X</label></td>
										<!-- Table ID -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvTempTblId_A" ezfname="upldCsvTempTblId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
										<!-- Request Job Net ID -->
										<td><label ezfout name="ezReqBusinessID_A" ezfname="ezReqBusinessID_A" ezfhyo="A">XXXXXXXXX</label></td>
									</tr>
									<tr height="40">
										<!-- Upload CSV ID -->
										<td><label ezfout name="upldCsvId_A" ezfname="upldCsvId_A" ezfhyo="A">XXXXXXXXX1</label></td>
										<!-- Upload CSV Name -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvNm_A" ezfname="upldCsvNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXX</label></td>
										<!-- File ID -->
										<td><label ezfout name="upldCsvFileId_A" ezfname="upldCsvFileId_A" ezfhyo="A">XXXXXXXXX1X</label></td>
										<!-- Table ID -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvTempTblId_A" ezfname="upldCsvTempTblId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
										<!-- Request Job Net ID -->
										<td><label ezfout name="ezReqBusinessID_A" ezfname="ezReqBusinessID_A" ezfhyo="A">XXXXXXXXX</label></td>
									</tr>
									<tr height="40">
										<!-- Upload CSV ID -->
										<td><label ezfout name="upldCsvId_A" ezfname="upldCsvId_A" ezfhyo="A">XXXXXXXXX1</label></td>
										<!-- Upload CSV Name -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvNm_A" ezfname="upldCsvNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXX</label></td>
										<!-- File ID -->
										<td><label ezfout name="upldCsvFileId_A" ezfname="upldCsvFileId_A" ezfhyo="A">XXXXXXXXX1X</label></td>
										<!-- Table ID -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvTempTblId_A" ezfname="upldCsvTempTblId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
										<!-- Request Job Net ID -->
										<td><label ezfout name="ezReqBusinessID_A" ezfname="ezReqBusinessID_A" ezfhyo="A">XXXXXXXXX</label></td>
									</tr>
									<tr height="40">
										<!-- Upload CSV ID -->
										<td><label ezfout name="upldCsvId_A" ezfname="upldCsvId_A" ezfhyo="A">XXXXXXXXX1</label></td>
										<!-- Upload CSV Name -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvNm_A" ezfname="upldCsvNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXX</label></td>
										<!-- File ID -->
										<td><label ezfout name="upldCsvFileId_A" ezfname="upldCsvFileId_A" ezfhyo="A">XXXXXXXXX1X</label></td>
										<!-- Table ID -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvTempTblId_A" ezfname="upldCsvTempTblId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
										<!-- Request Job Net ID -->
										<td><label ezfout name="ezReqBusinessID_A" ezfname="ezReqBusinessID_A" ezfhyo="A">XXXXXXXXX</label></td>
									</tr>
									<tr height="40">
										<!-- Upload CSV ID -->
										<td><label ezfout name="upldCsvId_A" ezfname="upldCsvId_A" ezfhyo="A">XXXXXXXXX1</label></td>
										<!-- Upload CSV Name -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvNm_A" ezfname="upldCsvNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXX</label></td>
										<!-- File ID -->
										<td><label ezfout name="upldCsvFileId_A" ezfname="upldCsvFileId_A" ezfhyo="A">XXXXXXXXX1X</label></td>
										<!-- Table ID -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvTempTblId_A" ezfname="upldCsvTempTblId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
										<!-- Request Job Net ID -->
										<td><label ezfout name="ezReqBusinessID_A" ezfname="ezReqBusinessID_A" ezfhyo="A">XXXXXXXXX</label></td>
									</tr>
									<tr height="40">
										<!-- Upload CSV ID -->
										<td><label ezfout name="upldCsvId_A" ezfname="upldCsvId_A" ezfhyo="A">XXXXXXXXX1</label></td>
										<!-- Upload CSV Name -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvNm_A" ezfname="upldCsvNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXX</label></td>
										<!-- File ID -->
										<td><label ezfout name="upldCsvFileId_A" ezfname="upldCsvFileId_A" ezfhyo="A">XXXXXXXXX1X</label></td>
										<!-- Table ID -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvTempTblId_A" ezfname="upldCsvTempTblId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
										<!-- Request Job Net ID -->
										<td><label ezfout name="ezReqBusinessID_A" ezfname="ezReqBusinessID_A" ezfhyo="A">XXXXXXXXX</label></td>
									</tr>
									<tr height="40">
										<!-- Upload CSV ID -->
										<td><label ezfout name="upldCsvId_A" ezfname="upldCsvId_A" ezfhyo="A">XXXXXXXXX1</label></td>
										<!-- Upload CSV Name -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvNm_A" ezfname="upldCsvNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXX</label></td>
										<!-- File ID -->
										<td><label ezfout name="upldCsvFileId_A" ezfname="upldCsvFileId_A" ezfhyo="A">XXXXXXXXX1X</label></td>
										<!-- Table ID -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvTempTblId_A" ezfname="upldCsvTempTblId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
										<!-- Request Job Net ID -->
										<td><label ezfout name="ezReqBusinessID_A" ezfname="ezReqBusinessID_A" ezfhyo="A">XXXXXXXXX</label></td>
									</tr>
									<tr height="40">
										<!-- Upload CSV ID -->
										<td><label ezfout name="upldCsvId_A" ezfname="upldCsvId_A" ezfhyo="A">XXXXXXXXX1</label></td>
										<!-- Upload CSV Name -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvNm_A" ezfname="upldCsvNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXX</label></td>
										<!-- File ID -->
										<td><label ezfout name="upldCsvFileId_A" ezfname="upldCsvFileId_A" ezfhyo="A">XXXXXXXXX1X</label></td>
										<!-- Table ID -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvTempTblId_A" ezfname="upldCsvTempTblId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
										<!-- Request Job Net ID -->
										<td><label ezfout name="ezReqBusinessID_A" ezfname="ezReqBusinessID_A" ezfhyo="A">XXXXXXXXX</label></td>
									</tr>
									<tr height="40">
										<!-- Upload CSV ID -->
										<td><label ezfout name="upldCsvId_A" ezfname="upldCsvId_A" ezfhyo="A">XXXXXXXXX1</label></td>
										<!-- Upload CSV Name -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvNm_A" ezfname="upldCsvNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXX</label></td>
										<!-- File ID -->
										<td><label ezfout name="upldCsvFileId_A" ezfname="upldCsvFileId_A" ezfhyo="A">XXXXXXXXX1X</label></td>
										<!-- Table ID -->
										<td style="word-break:break-all;"><label ezfout name="upldCsvTempTblId_A" ezfname="upldCsvTempTblId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
										<!-- Request Job Net ID -->
										<td><label ezfout name="ezReqBusinessID_A" ezfname="ezReqBusinessID_A" ezfhyo="A">XXXXXXXXX</label></td>
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
