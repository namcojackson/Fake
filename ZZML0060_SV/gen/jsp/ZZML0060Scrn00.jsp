<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100205065328 --%>
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
			<input type="hidden" name="pageID" value="ZZML0060Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Mail Group Configuration">
<table width="990" align="center" border="0" cellpadding="0" cellspacing="0">
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
		<table width="990" align="center" border="0" cellpadding="1" cellspacing="0">
			<tr align="left">
				<td>
					<table border="0" cellpadding="1" cellspacing="0">
						<!-- Global Company Code -->
						<tr align="left">
							<td width="170" class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company CD</ezf:anchor></td>
							<td width="700">
								<ezf:inputText name="glblCmpyCd_S" ezfName="glblCmpyCd_S" value="XXXX" otherAttr=" size=\"4\" ezftoupper=\"\""/>
							</td>
							<td width="120"></td>
						</tr>
						<!-- Mail Group -->
						<tr align="left">
							<td width="170" class="stab">Mail Group ID(*)</td>
							<td width="700">
								<ezf:inputText name="mlGrpId_S" ezfName="mlGrpId_S" value="XXXXXXXXX1XXXXXXXXX2" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/>
							</td>
							<td width="120"></td>
						</tr>
						<tr align="left">
							<td width="170" class="stab">Mail Group Name(*)</td>
							<td width="700">
								<ezf:inputText name="mlGrpNm_S" ezfName="mlGrpNm_S" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" otherAttr=" size=\"80\" maxlength=\"100\""/>
							</td>
							<td width="120" align="right">
								<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

		<hr size="1" noshade>

		<%-- item --%>

		<%-- Pagenation --%>
		<table width="990" align="center">
			<tr>
				<td align="left">
					<table border="0" cellpadding="1" cellspacing="0">
						<col>
						<tr>
							<td><ezf:inputButton name="Add" value="Add Group" htmlClass="pBtn8" /></td>
						</tr>
					</table>
				</td>
				<td align="right">
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
                                    <td class="pOut">40</td>
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
		</table>

		<!-- view -->
		<table cellpadding="0" cellspacing="0" border="0" width="990" align="center">
			<col width="280">
			<col width="*">
			<tr>
				<td valign="top">
					<%-- LEFT-TABLE(TOP) --%>
					<div id="leftTopTBL" style="table-layout:fixed; overflow-y:hidden; height:42; overflow-x:hidden; width:;">
	 					<table cellpadding="1" cellspacing="0" border="1" width="335">
							<col width="40">
							<col width="40">
							<col width="145">
							<col width="45">
							<col width="*">
							<tr style="text-align: center" height="40">
								<td rowspan="2" class="pClothBs" nowrap></td>
								<td rowspan="2" class="pClothBs" nowrap>No.</td>
								<td rowspan="2" class="pClothBs" nowrap>Mail Group ID</td>
								<td rowspan="2" class="pClothBs" nowrap></td>
								<td rowspan="2" class="pClothBs" nowrap></td>
							</tr>
						</table>
					</div>

					<%-- LEFT-TABLE(MID) --%>
					<div id="leftTBL" style="table-layout:fixed; overflow-y:hidden; height:394; overflow-x:hidden; width:;" onScroll="synchroScroll_fromLeftTblAction()">
						<table cellpadding="1" cellspacing="0" border="1" width="335" style="word-break:break-all;white-space:pre-wrap;">
							<col width="40">
							<col width="40">
							<col width="145">
							<col width="45">
							<col width="*">
							<tbody>
							<ezf:row ezfHyo="A">
								<tr style="text-align: left" height="56">
									<!-- # -->
									<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
									<!-- No -->
									<td align="center"><ezf:label name="xxNum_A" ezfName="xxNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
									<!-- Mail Group Id -->
									<td><ezf:label name="mlGrpId_A" ezfName="mlGrpId_A" ezfHyo="A" ezfArrayNo="0" /></td>
									<!-- View User -->
									<td align="center"><ezf:inputButton name="User" value="User" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" /></td>
									<!-- Edit -->
									<td align="center"><ezf:inputButton name="Edit" value="Edit" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" /></td>
								</tr>
								</ezf:row>
								<ezf:skip>
                                    <tr style="text-align: left" height="56">
                                        <!-- # -->
                                        <td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
                                        <!-- No -->
                                        <td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
                                        <!-- Mail Group Id -->
                                        <td><label ezfout name="mlGrpId_DT" ezfname="mlGrpId_DT" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>

                                        <!-- View User -->
                                        <td align="center"><input type="button" class="pBtn1" value="User" name="User" onclick="sendServer(this)"></td>
                                        <!-- Edit -->
                                        <td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
                                    </tr>

                                    <tr style="text-align: left" height="56">
                                        <!-- # -->
                                        <td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
                                        <!-- No -->
                                        <td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
                                        <!-- Mail Group Id -->
                                        <td><label ezfout name="mlGrpId_DT" ezfname="mlGrpId_DT" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>

                                        <!-- View User -->
                                        <td align="center"><input type="button" class="pBtn1" value="User" name="User" onclick="sendServer(this)"></td>
                                        <!-- Edit -->
                                        <td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
                                    </tr>

                                    <tr style="text-align: left" height="56">
                                        <!-- # -->
                                        <td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
                                        <!-- No -->
                                        <td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
                                        <!-- Mail Group Id -->
                                        <td><label ezfout name="mlGrpId_DT" ezfname="mlGrpId_DT" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>

                                        <!-- View User -->
                                        <td align="center"><input type="button" class="pBtn1" value="User" name="User" onclick="sendServer(this)"></td>
                                        <!-- Edit -->
                                        <td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
                                    </tr>

                                    <tr style="text-align: left" height="56">
                                        <!-- # -->
                                        <td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
                                        <!-- No -->
                                        <td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
                                        <!-- Mail Group Id -->
                                        <td><label ezfout name="mlGrpId_DT" ezfname="mlGrpId_DT" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>

                                        <!-- View User -->
                                        <td align="center"><input type="button" class="pBtn1" value="User" name="User" onclick="sendServer(this)"></td>
                                        <!-- Edit -->
                                        <td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
                                    </tr>

                                    <tr style="text-align: left" height="56">
                                        <!-- # -->
                                        <td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
                                        <!-- No -->
                                        <td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
                                        <!-- Mail Group Id -->
                                        <td><label ezfout name="mlGrpId_DT" ezfname="mlGrpId_DT" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>

                                        <!-- View User -->
                                        <td align="center"><input type="button" class="pBtn1" value="User" name="User" onclick="sendServer(this)"></td>
                                        <!-- Edit -->
                                        <td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
                                    </tr>

                                    <tr style="text-align: left" height="56">
                                        <!-- # -->
                                        <td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
                                        <!-- No -->
                                        <td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>
                                        <!-- Mail Group Id -->
                                        <td><label ezfout name="mlGrpId_DT" ezfname="mlGrpId_DT" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>

                                        <!-- View User -->
                                        <td align="center"><input type="button" class="pBtn1" value="User" name="User" onclick="sendServer(this)"></td>
                                        <!-- Edit -->
                                        <td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
                                    </tr>
								</ezf:skip>
							</tbody>
						</table>
					</div>

				</td>
				<td>
					<%-- RIGHT-TABLE(TOP) --%>
					<div id="rightTopTBL" style="overflow-y:none; height:42; overflow-x:hidden; width:688;">
						<table cellpadding="1" cellspacing="0" border="1" width="925">
							<col width="205">
							<col width="140">
							<col width="140">
							<col width="440">
							<tr style="text-align: center" height="20">
								<td colspan="3" class="pClothBs" nowrap>Mail Group Name</td>
								<td rowspan="2" class="pClothBs" nowrap>Mail Group Description</td>
							</tr>
							<tr style="text-align: center" height="20">
								<td class="pClothBs" nowrap>Mail Key Name First</td>
								<td class="pClothBs" nowrap>Mail Key Name Second</td>
								<td class="pClothBs" nowrap>Mail Key Name Third</td>
							</tr>
						</table>
					</div>

					<%-- RIGHT-TABLE(MID) --%>
					<div id="rightTBL" style="overflow-y:scroll; height:413; overflow-x:scroll; width:705;" onscroll="synchroScroll_fromRightTblAction();">
						<table cellpadding="1" cellspacing="0" border="1" width="925">
							<col width="205">
							<col width="140">
							<col width="140">
							<col width="440">
							<tbody>
								<ezf:row ezfHyo="A">
								<tr>
									<!-- Mail Group Name -->
									<td colspan="3"><ezf:inputText name="mlGrpNm_A" ezfName="mlGrpNm_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"58\" maxlength=\"100\""/></td>
									<!-- Mail Group Description -->
									<td rowspan="2"><ezf:textArea name="mlGrpDescTxt_A" ezfName="mlGrpDescTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"width:430;height:50px;\""/></td>
								</tr>
								<tr>
									<!-- Mail Group Key Name 1 -->
									<td><ezf:inputText name="mlKeyFirstNm_A" ezfName="mlKeyFirstNm_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"23\" maxlength=\"100\""/></td>

									<!-- Mail Group Key Name 2 -->
									<td><ezf:inputText name="mlKeyScdNm_A" ezfName="mlKeyScdNm_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"100\""/></td>

									<!-- Mail Group Key Name 3 -->
									<td><ezf:inputText name="mlKeyThirdNm_A" ezfName="mlKeyThirdNm_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"100\""/></td>
								</tr>
								</ezf:row>
								<ezf:skip>
                                <tr>
                                    <!-- Mail Group Name -->
                                    <td colspan="3"><input type="text" size="58" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlGrpNm_DT" ezfname="mlGrpNm_DT" ezfhyo="A"></td>
                                    <!-- Mail Group Description -->
                                    <td rowspan="2"><textarea name="mlGrpNm_00" style="width:430;height:50px;" ezfname="mlGrpDescTxt_DT" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
                                </tr>
                                <tr>
                                    <!-- Mail Group Key Name 1 -->
                                    <td><input type="text" size="23" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlKeyFirstNm_DT" ezfname="mlKeyFirstNm_DT" ezfhyo="A"></td>

                                    <!-- Mail Group Key Name 2 -->
                                    <td><input type="text" size="15" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlKeyScdNm_DT" ezfname="mlKeyScdNm_DT" ezfhyo="A"></td>

                                    <!-- Mail Group Key Name 3 -->
                                    <td><input type="text" size="15" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlKeyThirdNm_DT" ezfname="mlKeyThirdNm_DT" ezfhyo="A"></td>
                                </tr>

                                <tr>
                                    <!-- Mail Group Name -->
                                    <td colspan="3"><input type="text" size="58" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlGrpNm_DT" ezfname="mlGrpNm_DT" ezfhyo="A"></td>
                                    <!-- Mail Group Description -->
                                    <td rowspan="2"><textarea name="mlGrpNm_00" style="width:430;height:50px;" ezfname="mlGrpDescTxt_DT" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
                                </tr>
                                <tr>
                                    <!-- Mail Group Key Name 1 -->
                                    <td><input type="text" size="23" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlKeyFirstNm_DT" ezfname="mlKeyFirstNm_DT" ezfhyo="A"></td>

                                    <!-- Mail Group Key Name 2 -->
                                    <td><input type="text" size="15" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlKeyScdNm_DT" ezfname="mlKeyScdNm_DT" ezfhyo="A"></td>

                                    <!-- Mail Group Key Name 3 -->
                                    <td><input type="text" size="15" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlKeyThirdNm_DT" ezfname="mlKeyThirdNm_DT" ezfhyo="A"></td>
                                </tr>

                                <tr>
                                    <!-- Mail Group Name -->
                                    <td colspan="3"><input type="text" size="58" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlGrpNm_DT" ezfname="mlGrpNm_DT" ezfhyo="A"></td>
                                    <!-- Mail Group Description -->
                                    <td rowspan="2"><textarea name="mlGrpNm_00" style="width:430;height:50px;" ezfname="mlGrpDescTxt_DT" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
                                </tr>
                                <tr>
                                    <!-- Mail Group Key Name 1 -->
                                    <td><input type="text" size="23" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlKeyFirstNm_DT" ezfname="mlKeyFirstNm_DT" ezfhyo="A"></td>

                                    <!-- Mail Group Key Name 2 -->
                                    <td><input type="text" size="15" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlKeyScdNm_DT" ezfname="mlKeyScdNm_DT" ezfhyo="A"></td>

                                    <!-- Mail Group Key Name 3 -->
                                    <td><input type="text" size="15" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlKeyThirdNm_DT" ezfname="mlKeyThirdNm_DT" ezfhyo="A"></td>
                                </tr>

                                <tr>
                                    <!-- Mail Group Name -->
                                    <td colspan="3"><input type="text" size="58" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlGrpNm_DT" ezfname="mlGrpNm_DT" ezfhyo="A"></td>
                                    <!-- Mail Group Description -->
                                    <td rowspan="2"><textarea name="mlGrpNm_00" style="width:430;height:50px;" ezfname="mlGrpDescTxt_DT" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
                                </tr>
                                <tr>
                                    <!-- Mail Group Key Name 1 -->
                                    <td><input type="text" size="23" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlKeyFirstNm_DT" ezfname="mlKeyFirstNm_DT" ezfhyo="A"></td>

                                    <!-- Mail Group Key Name 2 -->
                                    <td><input type="text" size="15" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlKeyScdNm_DT" ezfname="mlKeyScdNm_DT" ezfhyo="A"></td>

                                    <!-- Mail Group Key Name 3 -->
                                    <td><input type="text" size="15" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlKeyThirdNm_DT" ezfname="mlKeyThirdNm_DT" ezfhyo="A"></td>
                                </tr>

                                <tr>
                                    <!-- Mail Group Name -->
                                    <td colspan="3"><input type="text" size="58" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlGrpNm_DT" ezfname="mlGrpNm_DT" ezfhyo="A"></td>
                                    <!-- Mail Group Description -->
                                    <td rowspan="2"><textarea name="mlGrpNm_00" style="width:430;height:50px;" ezfname="mlGrpDescTxt_DT" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
                                </tr>
                                <tr>
                                    <!-- Mail Group Key Name 1 -->
                                    <td><input type="text" size="23" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlKeyFirstNm_DT" ezfname="mlKeyFirstNm_DT" ezfhyo="A"></td>

                                    <!-- Mail Group Key Name 2 -->
                                    <td><input type="text" size="15" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlKeyScdNm_DT" ezfname="mlKeyScdNm_DT" ezfhyo="A"></td>

                                    <!-- Mail Group Key Name 3 -->
                                    <td><input type="text" size="15" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlKeyThirdNm_DT" ezfname="mlKeyThirdNm_DT" ezfhyo="A"></td>
                                </tr>
                                <tr>
                                    <!-- Mail Group Name -->
                                    <td colspan="3"><input type="text" size="58" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlGrpNm_DT" ezfname="mlGrpNm_DT" ezfhyo="A"></td>
                                    <!-- Mail Group Description -->
                                    <td rowspan="2"><textarea name="mlGrpNm_00" style="width:430;height:50px;" ezfname="mlGrpDescTxt_DT" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
                                </tr>
                                <tr>
                                    <!-- Mail Group Key Name 1 -->
                                    <td><input type="text" size="23" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlKeyFirstNm_DT" ezfname="mlKeyFirstNm_DT" ezfhyo="A"></td>

                                    <!-- Mail Group Key Name 2 -->
                                    <td><input type="text" size="15" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlKeyScdNm_DT" ezfname="mlKeyScdNm_DT" ezfhyo="A"></td>

                                    <!-- Mail Group Key Name 3 -->
                                    <td><input type="text" size="15" maxlength="100" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" name="mlKeyThirdNm_DT" ezfname="mlKeyThirdNm_DT" ezfhyo="A"></td>
                                </tr>
								</ezf:skip>
							</tbody>
						</table>
					</div>
				</td>
			</tr>
		</table>
		<%-- foot --%>
	</div>
</table>

<ezf:inputHidden name="glblCmpyCd" ezfName="glblCmpyCd" />

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

<%-- **** Task specific area ends here **** --%>
