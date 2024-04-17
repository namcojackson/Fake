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
			<input type="hidden" name="pageID" value="ZZML0030Scrn01">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Mail Archives Monitor Detail">
			
<!--<table width="990" align="center" border="0" cellpadding="0" cellspacing="0">-->
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
			</div> -->

			<div class="pTab_BODY">
			<table width="1133" align="center" border="0" cellpadding="1" cellspacing="0">
			<tr><td>
				<table width="990" align="center" border="0" cellpadding="1" cellspacing="0">
					<tr align="left">
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="170">
								<col width="818">
								<!-- Global Company Code -->
								<tr align="left">
									<td class="stab">Global Company Code</td>
									<td>
										<ezf:inputText name="glblCmpyCd_01" ezfName="glblCmpyCd_01" value="XXXX" otherAttr=" size=\"4\""/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br>
				<%-- item --%>
				<table width="990" align="center" border="0" cellpadding="1" cellspacing="0">
					<tr align="left">
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="100">
								<col width="860">
								<!-- Address -->
								<tr align="left">
									<td valign="top" class="stab">Address</td>
									<td>
										
										<!-- view -->
										<table cellpadding="0" cellspacing="0" border="0" width="100%">
											<col width="140">
											<col width="690">
											<tr>
												<td valign="top">
													<%-- LEFT-TABLE(TOP) --%>
													<div id="leftTopTBL" style="overflow-y:hidden; height:26; overflow-x:hidden; width:;">
														<table cellpadding="1" cellspacing="0" border="1">
															<col width="38">
															<col width="98">
																<tr style="text-align: center" height="24">
																	<td class="pClothBs" nowrap>No.</td>
																	<td class="pClothBs" nowrap>Address Type</td>
																</tr>
														</table>
													</div>
													
													<%-- LEFT-TABLE(MID) --%>
													<div id="leftTBL" style="overflow-y:hidden; height:80; overflow-x:hidden; width:;" onScroll="synchroScroll_fromLeftTblAction()">
														<table cellpadding="1" cellspacing="0" border="1" id="B">
															<col width="38">
															<col width="98">
															
															<ezf:row ezfHyo="B">
															<tr style="text-align: center" height="26">
																<!-- No -->
																<td><ezf:label name="xxNum_B" ezfName="xxNum_B" ezfHyo="B" ezfArrayNo="0" /></td>
																<!-- Address Type -->
																<td><ezf:label name="mlAddrTpCd_B" ezfName="mlAddrTpCd_B" ezfHyo="B" ezfArrayNo="0" /></td>
															</tr>
															</ezf:row>

															<ezf:skip>
															<tr style="text-align: center" height="26">
																<!-- No -->
																<td><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
																<!-- Address Type -->
																<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">TO</label></td>
															</tr>
															<tr style="text-align: center" height="26">
																<!-- No -->
																<td><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
																<!-- Address Type -->
																<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">CC</label></td>
															</tr>
															<tr style="text-align: center" height="26">
																<!-- No -->
																<td><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
																<!-- Address Type -->
																<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">CC</label></td>
															</tr>
															</ezf:skip>
														</table>
													</div>
													
												</td>
												<td>
													<%-- RIGHT-TABLE(TOP) --%>
													<div id="rightTopTBL" style="overflow-y:none; height:26; overflow-x:hidden; width:690;">
														<table cellpadding="1" cellspacing="0" border="1" width="690">
															<col width="342">
															<col width="247">
															<col width="88">
															<tr style="text-align: center" height="24">
																<td class="pClothBs" nowrap>Mail Address</td>
																<td class="pClothBs" nowrap>User Name</td>
																<td class="pClothBs" nowrap>User ID</td>
															</tr>
														</table>
													</div>
													
													<%-- RIGHT-TABLE(MID) --%>
													<div id="rightTBL" style="overflow-y:scroll; height:80; overflow-x:hidden; width:707;" onscroll="synchroScroll_fromRightTblAction();">
														<table  align="left" cellpadding="1" cellspacing="0" border="1" width="690" id="B">
															<col width="340">
															<col width="248">
															<col width="88">
															<ezf:row ezfHyo="B">
															<tr height="26">
																<!-- Mail Address -->
																<td style="padding: 0px 0px 0px 0px;"><ezf:inputText name="mlUsrAddr_B" ezfName="mlUsrAddr_B" value="user1@test.co.jpXXX2XXXXXXXXX3XXXXXXXXX4" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"background-color=FBFBFB;\" size=\"42\" maxlength=\"240\""/></td>
																<!-- User Name -->
																<td style="padding: 0px 0px 0px 0px;"><ezf:inputText name="mlUsrNm_B" ezfName="mlUsrNm_B" value="user1@test.co.jpXXX2XXXXXXXXX3XXXXXXXXX4" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"background-color=FBFBFB;\" size=\"30\" maxlength=\"100\""/></td>
																<!-- User ID -->
																<td><ezf:label name="mlUsrId_B" ezfName="mlUsrId_B" ezfHyo="B" ezfArrayNo="0" /></td>
															</tr>
															</ezf:row>

															<ezf:skip>
															<tr height="26">
																<!-- Mail Address -->
																<td style="padding: 0px 0px 0px 0px;"><input type="text" readonly="readonly" name="mlUsrAddr_B" ezfname="mlUsrAddr_B"  ezfhyo="B" size="42" maxlength="240" value="user1@test.co.jpXXX2XXXXXXXXX3XXXXXXXXX4" /></td>
																<!-- User Name -->
																<td style="padding: 0px 0px 0px 0px;"><input type="text" readonly="readonly" name="mlUsrNm_B" ezfname="mlUsrNm_B"  ezfhyo="B" size="30" maxlength="100" value="user1@test.co.jpXXX2XXXXXXXXX3XXXXXXXXX4" /></td>
																<!-- User ID -->
																<td><label ezfout name="mlUsrId_B" ezfname="mlUsrId_B" ezfhyo="B">QXXXXXXXXX</label></td>
															</tr>
															<tr height="26">
																<!-- Mail Address -->
																<td style="padding: 0px 0px 0px 0px;"><input type="text" readonly="readonly" name="mlUsrAddr_B" ezfname="mlUsrAddr_B"  ezfhyo="B" size="42" maxlength="240" value="user1@test.co.jpXXX2XXXXXXXXX3XXXXXXXXX4" /></td>
																<!-- User Name -->
																<td style="padding: 0px 0px 0px 0px;"><input type="text" readonly="readonly" name="mlUsrNm_B" ezfname="mlUsrNm_B"  ezfhyo="B" size="30" maxlength="100" value="user1@test.co.jpXXX2XXXXXXXXX3XXXXXXXXX4" /></td>
																<!-- User ID -->
																<td><label ezfout name="mlUsrId_B" ezfname="mlUsrId_B" ezfhyo="B">QXXXXXXXXX</label></td>
															</tr>
															<tr height="26">
																<!-- Mail Address -->
																<td style="padding: 0px 0px 0px 0px;"><input type="text" readonly="readonly" name="mlUsrAddr_B" ezfname="mlUsrAddr_B"  ezfhyo="B" size="42" maxlength="240" value="user1@test.co.jpXXX2XXXXXXXXX3XXXXXXXXX4" /></td>
																<!-- User Name -->
																<td style="padding: 0px 0px 0px 0px;"><input type="text" readonly="readonly" name="mlUsrNm_B" ezfname="mlUsrNm_B"  ezfhyo="B" size="30" maxlength="100" value="user1@test.co.jpXXX2XXXXXXXXX3XXXXXXXXX4" /></td>
																<!-- User ID -->
																<td><label ezfout name="mlUsrId_B" ezfname="mlUsrId_B" ezfhyo="B">QXXXXXXXXX</label></td>
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
						</td>
					</tr>
				</table>
				
				
				
				<table width="990" align="center" border="0" cellpadding="1" cellspacing="0">
					<tr align="left">
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="100">
								<col width="830">
								<!-- Subject -->
								<tr align="left">
									<td valign="top" class="stab">Subject</td>
									<td>
										<ezf:textArea name="mlSubjTxt_01" ezfName="mlSubjTxt_01" otherAttr=" cols=\"100\" rows=\"2\" style=\"overflow:hidden;width:812;height:38px;\""/>
									</td>
								</tr>
								<!-- Body -->
								<tr align="left">
									<td valign="top" class="stab">Body</td>
									<td>
										<ezf:textArea name="xxMlBodyTxt_01" ezfName="xxMlBodyTxt_01" otherAttr=" style=\"width:830;height:270px;\""/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				
				
				<table width="990" align="center" border="0" cellpadding="1" cellspacing="0">
					<tr align="left">
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="100">
								<col width="830">
								<!-- Attachment -->
								<tr align="left">
									<td valign="top" class="stab">Attachment</td>
									<td>
										
										<!-- view -->
										<table cellpadding="0" cellspacing="0" border="0" width="100%">
											<col width="40">
											<col width="790">
											<tr>
												<td valign="top">
													<%-- LEFT-TABLE(TOP) --%>
													<div id="leftTopTBL2" style="overflow-y:hidden; height:26; overflow-x:hidden; width:;">
														<table cellpadding="1" cellspacing="0" border="1">
																<tr style="text-align: center" height="24">
																	<td class="pClothBs" width="40" nowrap>No.</td>
																</tr>
														</table>
													</div>
													
													<%-- LEFT-TABLE(MID) --%>
													<div id="leftTBL2" style="overflow-y:hidden; height:50; overflow-x:hidden; width:;" onScroll="synchroScroll_fromLeftTblAction2()">
														<table cellpadding="1" cellspacing="0" border="1" id="C">
															<col width="40">
															
															<ezf:row ezfHyo="C">
															<tr style="text-align: center" height="24">
																<!-- No -->
																<td><ezf:label name="xxNum_C" ezfName="xxNum_C" ezfHyo="C" ezfArrayNo="0" /></td>
															</tr>
															</ezf:row>

															<ezf:skip>
															<tr style="text-align: center" height="24">
																<!-- No -->
																<td><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
															</tr>
															<tr style="text-align: center" height="24">
																<!-- No -->
																<td><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
															</tr>
															<tr style="text-align: center" height="24">
																<!-- No -->
																<td><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
															</tr>
															</ezf:skip>
														</table>
													</div>
													
												</td>
												<td>
													<%-- RIGHT-TABLE(TOP) --%>
													<div id="rightTopTBL2" style="overflow-y:none; height:24; overflow-x:hidden; width:790;">
														<table cellpadding="0" cellspacing="0" border="1" width="790">
															<col width="790">
															<tr style="text-align: center" height="24">
																<td class="pClothBs" nowrap>Attachment Name</td>
															</tr>
														</table>
													</div>
													
													<%-- RIGHT-TABLE(MID) --%>
													<div id="rightTBL2" style="overflow-y:scroll; height:50; overflow-x:hidden; width:807;" onscroll="synchroScroll_fromRightTblAction2();">
														<table  align="left" cellpadding="1" cellspacing="0" border="1" width="790" id="C">
															<col width="790">
															<ezf:row ezfHyo="C">
															<tr height="24">
																<!-- Attachment Name -->
																<td><ezf:label name="mlAttNm_C" ezfName="mlAttNm_C" ezfHyo="C" ezfArrayNo="0" /></td>
															</tr>
															</ezf:row>

															<ezf:skip>
															<tr height="24">
																<!-- Attachment Name -->
																<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4</label></td>
															</tr>
															<tr height="24">
																<!-- Attachment Name -->
																<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4</label></td>
															</tr>
															<tr height="24">
																<!-- Attachment Name -->
																<td><label ezfout name="xxUpldCsvDt" ezfname="xxUpldCsvDt" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4</label></td>
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
						</td>
					</tr>
				</table>

				
				<%-- foot --%>
										</td>
					</tr>
				</table>
			</div>
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
