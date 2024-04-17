<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100816231312 --%>
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
			<input type="hidden" name="pageID" value="ZZML0070Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Mail User Configuration">
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
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
						<col width="170">
						<col width="110">
						<col width="140">
						<col width="175">
						<col width="140">
						<col width="165">
						<col width="*">
						<tr align="left">
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company CD</ezf:anchor></td>
							<td>
								<ezf:inputText name="glblCmpyCd_S" ezfName="glblCmpyCd_S" value="XXXX" otherAttr=" size=\"4\" ezftoupper=\"\""/>
							</td>
							<td class="stab">Mail Group ID(*)</td>
							<td>
								<ezf:inputText name="mlGrpId_S" ezfName="mlGrpId_S" value="XXXXXXXXX1XXXXXXXXX2" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/>
							</td>
							<td colspan="3">
							</td>
						</tr>
						<tr align="left" class="stab">
							<td class="stab">Mail Key First</td>
							<td>
								<ezf:inputText name="mlKeyFirstCd_S" ezfName="mlKeyFirstCd_S" value="XXX" otherAttr=" size=\"10\" maxlength=\"50\""/>
							</td>
							<td class="stab">Mail Key Second</td>
							<td>
								<ezf:inputText name="mlKeyScdCd_S" ezfName="mlKeyScdCd_S" value="XXX" otherAttr=" size=\"10\" maxlength=\"50\""/>
							</td>
							<td class="stab">Mail Key Third</td>
							<td>
								<ezf:inputText name="mlKeyThirdCd_S" ezfName="mlKeyThirdCd_S" value="XXX" otherAttr=" size=\"10\" maxlength=\"50\""/>
							</td>
							<td></td>
						</tr>
						<tr align="left">
							<td class="stab">User ID</td>
							<td>
								<ezf:inputText name="mlUsrId_S" ezfName="mlUsrId_S" value="XXX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
							</td>
							<td class="stab">User Address(*)</td>
							<td colspan="3">
								<ezf:inputText name="mlUsrAddr_S" ezfName="mlUsrAddr_S" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" otherAttr=" size=\"50\" maxlength=\"100\""/>
							</td>
							<td align="right">
								<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

		<hr size="1" noshade>

		<%-- item --%>
<%--
		<table width="990" align="center" border="0" cellpadding="1" cellspacing="0">
			<tr align="left">
				<td>
					<table border="0" cellpadding="1" cellspacing="0">
						<col width="990">
						<tr>
							<td><ezf:inputButton name="Add" value="Add User" htmlClass="pBtn8" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
					<td align="left"><ezf:inputButton name="Add" value="Add User" htmlClass="pBtn8" /></td>
--%>
			<%-- Pagenation --%>
			<table width="990" align="center">
				<tr>
					<td align="left">
						<table border="0" cellpadding="1" cellspacing="0">
							<col>
							<col>
							<tr>
								<td align="right"><ezf:inputButton name="AddUser" value="Add User" htmlClass="pBtn8" /></td>
							</tr>
						</table>
					</td>
					<td align="right">
          <%--
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
								<td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn3" /></td>
								<td></td>
								<td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn3" /></td>
							</tr>
						</table>
          --%>
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
			<col width="200">
			<col width="*">
			<tr>
				<td valign="top">
					<%-- LEFT-TABLE(TOP) --%>
					<div id="leftTopTBL" style="table-layout:fixed; overflow-y:hidden; height:36; overflow-x:hidden;">
						<table cellpadding="1" cellspacing="0" border="1" width="200">
							<col width="40">
							<col width="40">
							<col width="60">
							<col width="*">
							<tr style="text-align: center" height="34">
								<td class="pClothBs" nowrap></td>
								<td class="pClothBs" nowrap>No.</td>
								<td class="pClothBs" nowrap></td>
								<td class="pClothBs" nowrap></td>
							</tr>
						</table>
					</div>
					
					<%-- LEFT-TABLE(MID) --%>
					<div id="leftTBL" style="table-layout:fixed; overflow-y:hidden; height:402; overflow-x:hidden; width:;" onScroll="synchroScroll_fromLeftTblAction()">
						<table cellpadding="1" cellspacing="0" border="1" width="200" >
							<col width="40">
							<col width="40">
							<col width="60">
							<col width="*">
							<tbody>
								<ezf:row ezfHyo="A">
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
									<!-- No -->
									<td align="center"><ezf:label name="xxNum_A" ezfName="xxNum_A" ezfHyo="A" ezfArrayNo="0" /></td>

									<!-- View -->
									<td align="center"><ezf:inputButton name="Group" value="Group" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn3" /></td>
									<!-- Edit -->
									<td align="center"><ezf:inputButton name="Edit" value="Edit" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" /></td>
								</tr>
								</ezf:row>
								<ezf:skip>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_DT" value="Y" ezfname="xxChkBox_DT" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_DT" ezfname="xxNum_DT" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn3" value="Group" name="Group" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								</ezf:skip>
							</tbody>
						</table>
					</div>
					
				</td>
				<td valign="top">
					<%-- RIGHT-TABLE(TOP) --%>
					<div id="rightTopTBL" style="overflow-y:none; height:36; overflow-x:hidden; width:768;">
						<table cellpadding="1" cellspacing="0" border="1" width="1655">
							<col width="145">
							<col width="90">
							<col width="75">
							<col width="75">
							<col width="75">
							<col width="300"> <%--User Address--%>
							<col width="250"> <%--User Name--%>
							<col width="90">
							<col width="400">
							<tr style="text-align: center" height="34">
								<td class="pClothBs" nowrap>Mail Group ID</td>
								<td class="pClothBs" nowrap>User ID</td>
								<td class="pClothBs" nowrap>Mail Key<br>First</td>
								<td class="pClothBs" nowrap>Mail Key<br>Second</td>
								<td class="pClothBs" nowrap>Mail Key<br>Third</td>
								<td class="pClothBs" nowrap>User Address</td>
								<td class="pClothBs" nowrap>User Name</td>
								<td class="pClothBs" nowrap>Language</td>
								<td class="pClothBs" nowrap>User Discription</td>
							</tr>
						</table>
					</div>
					
					<%-- RIGHT-TABLE(MID) --%>
					<div id="rightTBL" style="overflow-y:scroll; height:420; overflow-x:scroll; width:785;" onscroll="synchroScroll_fromRightTblAction();">
						<table  cellpadding="1" cellspacing="0" border="1" width="1655" style="word-break:break-all;white-space:pre-wrap;">
							<col width="145">
							<col width="90">
							<col width="75">
							<col width="75">
							<col width="75">
							<col width="300"> <%--User Address--%>
							<col width="250"> <%--User Name--%>
							<col width="90">
							<col width="400">
							<tbody>
								<ezf:row ezfHyo="A">
								<tr height="40">
									<!-- Mail Group ID -->
									<td><ezf:label name="mlGrpId_A" ezfName="mlGrpId_A" ezfHyo="A" ezfArrayNo="0" /></td>
									<!-- User ID -->
									<td><ezf:label name="mlUsrId_A" ezfName="mlUsrId_A" ezfHyo="A" ezfArrayNo="0" /></td>
									<!-- Mail Key First -->
									<td><ezf:label name="mlKeyFirstCd_A" ezfName="mlKeyFirstCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
									<!-- Mail Key Second -->
									<td><ezf:label name="mlKeyScdCd_A" ezfName="mlKeyScdCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
									<!-- Mail Key Third -->
									<td><ezf:label name="mlKeyThirdCd_A" ezfName="mlKeyThirdCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
									<!-- User Address -->
									<td><ezf:textArea name="mlUsrAddr_A" ezfName="mlUsrAddr_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" cols=\"42\" rows=\"2\" style=\"height:34px;width:300px;\""/></td>
									<!-- User Name -->
									<td><ezf:textArea name="mlUsrNm_A" ezfName="mlUsrNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" cols=\"33\" rows=\"2\" style=\"height:34px;width:250px\""/></td>
									<!-- Language -->
									<td><ezf:label name="langNm_A" ezfName="langNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
									<!-- User Discription -->
									<td><ezf:textArea name="mlUsrDescTxt_A" ezfName="mlUsrDescTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" cols=\"58\" rows=\"2\" style=\"height:34px;width:400px\""/></td>
								</tr>
								</ezf:row>
								<ezf:skip>
								<tr height="40">
									<!-- Mail Group ID -->
									<td><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<!-- User ID -->
									<td><label ezfout name="mlUsrId_A" ezfname="mlUsrId_A" ezfhyo="A">QXXXXXXXX1</label></td>
									<!-- Mail Key First -->
									<td><label ezfout name="mlKeyFirstCd_A" ezfname="mlKeyFirstCd_A" ezfhyo="A">AA</label></td>
									<!-- Mail Key Second -->
									<td><label ezfout name="mlKeyScdCd_A" ezfname="mlKeyScdCd_A" ezfhyo="A">BB</label></td>
									<!-- Mail Key Third -->
									<td><label ezfout name="mlKeyThirdCd_A" ezfname="mlKeyThirdCd_A" ezfhyo="A">CC</label></td>
									<!-- User Address -->
									<td><textarea name="mlUsrAddr_A" cols="42" rows="2" style="height:34px;width:300px;" ezfname="mlUsrAddr_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
									<!-- User Name -->
									<td><textarea name="mlUsrNm_A" cols="33" rows="2" style="height:34px;width:250px" ezfname="mlUsrNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
									<!-- Language -->
									<td><label ezfout name="langNm_A" ezfname="langNm_A" ezfhyo="A">English</label></td>
									<!-- User Discription -->
									<td><textarea name="mlUsrDescTxt_A" cols="58" rows="2" style="height:34px;width:400px" ezfname="mlUsrDescTxt_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
								</tr>
								<tr height="40">
									<!-- Mail Group ID -->
									<td><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<!-- User ID -->
									<td><label ezfout name="mlUsrId_A" ezfname="mlUsrId_A" ezfhyo="A">QXXXXXXXX1</label></td>
									<!-- Mail Key First -->
									<td><label ezfout name="mlKeyFirstCd_A" ezfname="mlKeyFirstCd_A" ezfhyo="A">AA</label></td>
									<!-- Mail Key Second -->
									<td><label ezfout name="mlKeyScdCd_A" ezfname="mlKeyScdCd_A" ezfhyo="A">BB</label></td>
									<!-- Mail Key Third -->
									<td><label ezfout name="mlKeyThirdCd_A" ezfname="mlKeyThirdCd_A" ezfhyo="A">CC</label></td>
									<!-- User Address -->
									<td><textarea name="mlUsrAddr_A" cols="42" rows="2" style="height:34px;width:300px;" ezfname="mlUsrAddr_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
									<!-- User Name -->
									<td><textarea name="mlUsrNm_A" cols="33" rows="2" style="height:34px;width:250px" ezfname="mlUsrNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
									<!-- Language -->
									<td><label ezfout name="langNm_A" ezfname="langNm_A" ezfhyo="A">English</label></td>
									<!-- User Discription -->
									<td><textarea name="mlUsrDescTxt_A" cols="58" rows="2" style="height:34px;width:400px" ezfname="mlUsrDescTxt_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
								</tr>
								<tr height="40">
									<!-- Mail Group ID -->
									<td><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<!-- User ID -->
									<td><label ezfout name="mlUsrId_A" ezfname="mlUsrId_A" ezfhyo="A">QXXXXXXXX1</label></td>
									<!-- Mail Key First -->
									<td><label ezfout name="mlKeyFirstCd_A" ezfname="mlKeyFirstCd_A" ezfhyo="A">AA</label></td>
									<!-- Mail Key Second -->
									<td><label ezfout name="mlKeyScdCd_A" ezfname="mlKeyScdCd_A" ezfhyo="A">BB</label></td>
									<!-- Mail Key Third -->
									<td><label ezfout name="mlKeyThirdCd_A" ezfname="mlKeyThirdCd_A" ezfhyo="A">CC</label></td>
									<!-- User Address -->
									<td><textarea name="mlUsrAddr_A" cols="42" rows="2" style="height:34px;width:300px;" ezfname="mlUsrAddr_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
									<!-- User Name -->
									<td><textarea name="mlUsrNm_A" cols="33" rows="2" style="height:34px;width:250px" ezfname="mlUsrNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
									<!-- Language -->
									<td><label ezfout name="langNm_A" ezfname="langNm_A" ezfhyo="A">English</label></td>
									<!-- User Discription -->
									<td><textarea name="mlUsrDescTxt_A" cols="58" rows="2" style="height:34px;width:400px" ezfname="mlUsrDescTxt_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
								</tr>
								<tr height="40">
									<!-- Mail Group ID -->
									<td><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<!-- User ID -->
									<td><label ezfout name="mlUsrId_A" ezfname="mlUsrId_A" ezfhyo="A">QXXXXXXXX1</label></td>
									<!-- Mail Key First -->
									<td><label ezfout name="mlKeyFirstCd_A" ezfname="mlKeyFirstCd_A" ezfhyo="A">AA</label></td>
									<!-- Mail Key Second -->
									<td><label ezfout name="mlKeyScdCd_A" ezfname="mlKeyScdCd_A" ezfhyo="A">BB</label></td>
									<!-- Mail Key Third -->
									<td><label ezfout name="mlKeyThirdCd_A" ezfname="mlKeyThirdCd_A" ezfhyo="A">CC</label></td>
									<!-- User Address -->
									<td><textarea name="mlUsrAddr_A" cols="42" rows="2" style="height:34px;width:300px;" ezfname="mlUsrAddr_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
									<!-- User Name -->
									<td><textarea name="mlUsrNm_A" cols="33" rows="2" style="height:34px;width:250px" ezfname="mlUsrNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
									<!-- Language -->
									<td><label ezfout name="langNm_A" ezfname="langNm_A" ezfhyo="A">English</label></td>
									<!-- User Discription -->
									<td><textarea name="mlUsrDescTxt_A" cols="58" rows="2" style="height:34px;width:400px" ezfname="mlUsrDescTxt_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
								</tr>
								<tr height="40">
									<!-- Mail Group ID -->
									<td><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<!-- User ID -->
									<td><label ezfout name="mlUsrId_A" ezfname="mlUsrId_A" ezfhyo="A">QXXXXXXXX1</label></td>
									<!-- Mail Key First -->
									<td><label ezfout name="mlKeyFirstCd_A" ezfname="mlKeyFirstCd_A" ezfhyo="A">AA</label></td>
									<!-- Mail Key Second -->
									<td><label ezfout name="mlKeyScdCd_A" ezfname="mlKeyScdCd_A" ezfhyo="A">BB</label></td>
									<!-- Mail Key Third -->
									<td><label ezfout name="mlKeyThirdCd_A" ezfname="mlKeyThirdCd_A" ezfhyo="A">CC</label></td>
									<!-- User Address -->
									<td><textarea name="mlUsrAddr_A" cols="42" rows="2" style="height:34px;width:300px;" ezfname="mlUsrAddr_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
									<!-- User Name -->
									<td><textarea name="mlUsrNm_A" cols="33" rows="2" style="height:34px;width:250px" ezfname="mlUsrNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
									<!-- Language -->
									<td><label ezfout name="langNm_A" ezfname="langNm_A" ezfhyo="A">English</label></td>
									<!-- User Discription -->
									<td><textarea name="mlUsrDescTxt_A" cols="58" rows="2" style="height:34px;width:400px" ezfname="mlUsrDescTxt_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
								</tr>
								<tr height="40">
									<!-- Mail Group ID -->
									<td><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<!-- User ID -->
									<td><label ezfout name="mlUsrId_A" ezfname="mlUsrId_A" ezfhyo="A">QXXXXXXXX1</label></td>
									<!-- Mail Key First -->
									<td><label ezfout name="mlKeyFirstCd_A" ezfname="mlKeyFirstCd_A" ezfhyo="A">AA</label></td>
									<!-- Mail Key Second -->
									<td><label ezfout name="mlKeyScdCd_A" ezfname="mlKeyScdCd_A" ezfhyo="A">BB</label></td>
									<!-- Mail Key Third -->
									<td><label ezfout name="mlKeyThirdCd_A" ezfname="mlKeyThirdCd_A" ezfhyo="A">CC</label></td>
									<!-- User Address -->
									<td><textarea name="mlUsrAddr_A" cols="42" rows="2" style="height:34px;width:300px;" ezfname="mlUsrAddr_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
									<!-- User Name -->
									<td><textarea name="mlUsrNm_A" cols="33" rows="2" style="height:34px;width:250px" ezfname="mlUsrNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
									<!-- Language -->
									<td><label ezfout name="langNm_A" ezfname="langNm_A" ezfhyo="A">English</label></td>
									<!-- User Discription -->
									<td><textarea name="mlUsrDescTxt_A" cols="58" rows="2" style="height:34px;width:400px" ezfname="mlUsrDescTxt_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
								</tr>
								<tr height="40">
									<!-- Mail Group ID -->
									<td><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<!-- User ID -->
									<td><label ezfout name="mlUsrId_A" ezfname="mlUsrId_A" ezfhyo="A">QXXXXXXXX1</label></td>
									<!-- Mail Key First -->
									<td><label ezfout name="mlKeyFirstCd_A" ezfname="mlKeyFirstCd_A" ezfhyo="A">AA</label></td>
									<!-- Mail Key Second -->
									<td><label ezfout name="mlKeyScdCd_A" ezfname="mlKeyScdCd_A" ezfhyo="A">BB</label></td>
									<!-- Mail Key Third -->
									<td><label ezfout name="mlKeyThirdCd_A" ezfname="mlKeyThirdCd_A" ezfhyo="A">CC</label></td>
									<!-- User Address -->
									<td><textarea name="mlUsrAddr_A" cols="42" rows="2" style="height:34px;width:300px;" ezfname="mlUsrAddr_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
									<!-- User Name -->
									<td><textarea name="mlUsrNm_A" cols="33" rows="2" style="height:34px;width:250px" ezfname="mlUsrNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
									<!-- Language -->
									<td><label ezfout name="langNm_A" ezfname="langNm_A" ezfhyo="A">English</label></td>
									<!-- User Discription -->
									<td><textarea name="mlUsrDescTxt_A" cols="58" rows="2" style="height:34px;width:400px" ezfname="mlUsrDescTxt_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
								</tr>
								<tr height="40">
									<!-- Mail Group ID -->
									<td><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<!-- User ID -->
									<td><label ezfout name="mlUsrId_A" ezfname="mlUsrId_A" ezfhyo="A">QXXXXXXXX1</label></td>
									<!-- Mail Key First -->
									<td><label ezfout name="mlKeyFirstCd_A" ezfname="mlKeyFirstCd_A" ezfhyo="A">AA</label></td>
									<!-- Mail Key Second -->
									<td><label ezfout name="mlKeyScdCd_A" ezfname="mlKeyScdCd_A" ezfhyo="A">BB</label></td>
									<!-- Mail Key Third -->
									<td><label ezfout name="mlKeyThirdCd_A" ezfname="mlKeyThirdCd_A" ezfhyo="A">CC</label></td>
									<!-- User Address -->
									<td><textarea name="mlUsrAddr_A" cols="42" rows="2" style="height:34px;width:300px;" ezfname="mlUsrAddr_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
									<!-- User Name -->
									<td><textarea name="mlUsrNm_A" cols="33" rows="2" style="height:34px;width:250px" ezfname="mlUsrNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
									<!-- Language -->
									<td><label ezfout name="langNm_A" ezfname="langNm_A" ezfhyo="A">English</label></td>
									<!-- User Discription -->
									<td><textarea name="mlUsrDescTxt_A" cols="58" rows="2" style="height:34px;width:400px" ezfname="mlUsrDescTxt_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
								</tr>
								<tr height="40">
									<!-- Mail Group ID -->
									<td><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<!-- User ID -->
									<td><label ezfout name="mlUsrId_A" ezfname="mlUsrId_A" ezfhyo="A">QXXXXXXXX1</label></td>
									<!-- Mail Key First -->
									<td><label ezfout name="mlKeyFirstCd_A" ezfname="mlKeyFirstCd_A" ezfhyo="A">AA</label></td>
									<!-- Mail Key Second -->
									<td><label ezfout name="mlKeyScdCd_A" ezfname="mlKeyScdCd_A" ezfhyo="A">BB</label></td>
									<!-- Mail Key Third -->
									<td><label ezfout name="mlKeyThirdCd_A" ezfname="mlKeyThirdCd_A" ezfhyo="A">CC</label></td>
									<!-- User Address -->
									<td><textarea name="mlUsrAddr_A" cols="42" rows="2" style="height:34px;width:300px;" ezfname="mlUsrAddr_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
									<!-- User Name -->
									<td><textarea name="mlUsrNm_A" cols="33" rows="2" style="height:34px;width:250px" ezfname="mlUsrNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
									<!-- Language -->
									<td><label ezfout name="langNm_A" ezfname="langNm_A" ezfhyo="A">English</label></td>
									<!-- User Discription -->
									<td><textarea name="mlUsrDescTxt_A" cols="58" rows="2" style="height:34px;width:400px" ezfname="mlUsrDescTxt_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</textarea></td>
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
