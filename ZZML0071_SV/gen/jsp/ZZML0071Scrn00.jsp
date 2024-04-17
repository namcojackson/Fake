<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100205071837 --%>
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
			<input type="hidden" name="pageID" value="ZZML0071Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Mail User Configuration">
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
		<%-- item --%>
		<table width="990" align="center" border="0" cellpadding="1" cellspacing="0">
			<tr align="left">
				<td>
					<table border="0" cellpadding="0" cellspacing="0">
						<col width="170">
						<col width="*">
						<tr align="left">
							<td class="stab">
                                <c:choose>
                                    <c:when test="${glblCmpyCd_InputProtect}">Global Company CD</c:when>
                                    <c:otherwise>Global Company CD</c:otherwise>
                                </c:choose>
							</td>
							<td>
								<ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXXX" otherAttr=" size=\"4\" ezftoupper=\"\""/>
							</td>
						</tr>
						<tr align="left">
							<td class="stab">User ID</td>
							<td class="stab">
								<ezf:inputText name="mlUsrId" ezfName="mlUsrId" value="XXX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
								<span style="width:97px"></span>User Address &nbsp;&nbsp;
								<ezf:inputText name="mlUsrAddr" ezfName="mlUsrAddr" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" otherAttr=" size=\"60\" maxlength=\"240\""/>
							</td>
						</tr>
						<tr align="left">
							<td class="stab">User Name</td>
							<td class="stab">
								<ezf:inputText name="mlUsrNm" ezfName="mlUsrNm" value="XXX" otherAttr=" size=\"30\" maxlength=\"100\""/>
								<span style="width:65px"></span>Locale &nbsp;&nbsp;
							    <ezf:select name="mlUsrLocId" ezfName="mlUsrLocId" ezfBlank="1" ezfCodeName="mlUsrLocId_L1" ezfDispName="langNm_L1" />
							</td>
						</tr>
						<tr align="left">
							<td class="stab">User Description</td>
							<td>
								<ezf:inputText name="mlUsrDescTxt" ezfName="mlUsrDescTxt" value="XXX" otherAttr=" size=\"100\" maxlength=\"300\""/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<hr size="1" noshade>
		<table width="990" align="center" border="0" cellpadding="1" cellspacing="0">
			<tr align="left">
				<td>
					<table border="0" cellpadding="1" cellspacing="0">
						<col width="120">
						<col width="180">
						<col width="200">
						<tr align="left">
							<td><ezf:inputButton name="AddGroup" value="Add Group" htmlClass="pBtn9" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- view -->
		<table width="990" align="center" border="0" cellpadding="0" cellspacing="0">
			<col width="150">
			<col width="*">
			<tr>
				<td valign="top">
					<%-- LEFT-TABLE(TOP) --%>
					<div id="leftTopTBL" style="overflow-y:hidden; height:42; overflow-x:hidden; width:;">
						<table cellpadding="1" cellspacing="0" border="1" width="205">
							<col width="*">
							<tr style="text-align: center" height="40">
								<td class="pClothBs" nowrap>Mail Group ID</td>
							</tr>
						</table>
					</div>
					
					<%-- LEFT-TABLE(MID) --%>
					<div id="leftTBL" style="overflow-y:hidden; height:362; overflow-x:hidden; width:;" onScroll="synchroScroll_fromLeftTblAction()">
						<table cellpadding="1" cellspacing="0" border="1" width="205" style="word-break:break-all;white-space:pre-wrap;">
							<col width="*">
							<tbody>
								<ezf:row ezfHyo="A">
								<tr style="text-align: left" height="40">
									<!-- Mail Group ID -->
									<td align="center">
										<ezf:inputText name="mlGrpId_A" ezfName="mlGrpId_A" value="ZZZL0010M001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"20\" id=\"mlGrpId_A#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
										<ezf:inputButton name="MailGroupLookup" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"MailGroupLookup#{EZF_ROW_NUMBER}\""/></td>
								</tr>
								</ezf:row>
								<ezf:skip>
								<tr style="text-align: left" height="40">
									<!-- Mail Group ID -->
									<td align="center"><input type="text" size="20" maxlength="20" value="2">
									<input type="button" class="pBtn1" value="..." name="get" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- Mail Group ID -->
									<td align="center"><input type="text" size="20" maxlength="20" value="3">
									<input type="button" class="pBtn1" value="..." name="get" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- Mail Group ID -->
									<td align="center"><input type="text" size="20" maxlength="20" value="4">
									<input type="button" class="pBtn1" value="..." name="get" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- Mail Group ID -->
									<td align="center"><input type="text" size="20" maxlength="20" value="5">
									<input type="button" class="pBtn1" value="..." name="get" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- Mail Group ID -->
									<td align="center"><input type="text" size="20" maxlength="20" value="6">
									<input type="button" class="pBtn1" value="..." name="get" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- Mail Group ID -->
									<td align="center"><input type="text" size="20" maxlength="20" value="7">
									<input type="button" class="pBtn1" value="..." name="get" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- Mail Group ID -->
									<td align="center"><input type="text" size="20" maxlength="20" value="8">
									<input type="button" class="pBtn1" value="..." name="get" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- Mail Group ID -->
									<td align="center"><input type="text" size="20" maxlength="20" value="9">
									<input type="button" class="pBtn1" value="..." name="get" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="40">
									<!-- Mail Group ID -->
									<td align="center"><input type="text" size="20s" maxlength="20" value="10">
									<input type="button" class="pBtn1" value="..." name="get" onclick="sendServer(this)"></td>
								</tr>
								</ezf:skip>
							</tbody>
						</table>
					</div>
					
				</td>
				<td valign="top">
					<%-- RIGHT-TABLE(TOP) --%>
					<div id="rightTopTBL" style="overflow-y:none; height:42; overflow-x:hidden; width:816;">
						<table cellpadding="1" cellspacing="0" border="1" width="1520">
							<col width="400">
							<col width="90">
							<col width="90">
							<col width="90">
							<col width="*">
							<tr style="text-align: center" height="40">
								<td class="pClothBs" nowrap>Mail Group Name</td>
								<td class="pClothBs" nowrap>Mail Key<br>First</td>
								<td class="pClothBs" nowrap>Mail Key<br>Second</td>
								<td class="pClothBs" nowrap>Mail Key<br>Third</td>
								<td class="pClothBs" nowrap>Mail Group Discription</td>
							</tr>
						</table>
					</div>
					
					<%-- RIGHT-TABLE(MID) --%>
					<div id="rightTBL" style="overflow-y:scroll; height:379; overflow-x:scroll; width:833;" onscroll="synchroScroll_fromRightTblAction();">
						<table  cellpadding="1" cellspacing="0" border="1" width="1520" style="word-break:break-all;white-space:pre-wrap;">
							<col width="400">
							<col width="90">
							<col width="90">
							<col width="90">
							<col width="*">
							
							<tbody>
								<ezf:row ezfHyo="A">
								<tr height="40">
									<!-- Mail Group Name -->
									<td><ezf:label name="mlGrpNm_A" ezfName="mlGrpNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
									<!-- Mail Key First -->
									<td><ezf:inputText name="mlKeyFirstCd_A" ezfName="mlKeyFirstCd_A" value="XXXXXXXXX1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" id=\"mlKeyFirstCd_A#{EZF_ROW_NUMBER}\""/></td>
									<!-- Mail Key Second -->
									<td><ezf:inputText name="mlKeyScdCd_A" ezfName="mlKeyScdCd_A" value="XXXXXXXXX1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" id=\"mlKeyScdCd_A#{EZF_ROW_NUMBER}\""/></td>
									<!-- Mail Key Third -->
									<td><ezf:inputText name="mlKeyThirdCd_A" ezfName="mlKeyThirdCd_A" value="XXXXXXXXX1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" id=\"mlKeyThirdCd_A#{EZF_ROW_NUMBER}\""/></td>
									<!-- User Discription -->
									<td><ezf:textArea name="mlGrpDescTxt_A" ezfName="mlGrpDescTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"width:825px;height:34px;\""/></td>
									<%--<td><ezf:label name="mlGrpDescTxt_A" ezfName="mlGrpDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>--%>
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
	<%-- foot --%>
	</div>
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
			

<%-- **** Task specific area ends here **** --%>
