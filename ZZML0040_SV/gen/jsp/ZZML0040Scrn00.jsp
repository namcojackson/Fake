<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20130816061910 --%>
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
			<input type="hidden" name="pageID" value="ZZML0040Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Mail Template Configuration">
			
<table align="center" border="0" cellpadding="0" cellspacing="0">
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
			<tr align="left">
				<td>
					<table border="0" cellpadding="1" cellspacing="0">
						<!-- Global Company Code -->
						<tr align="left">
							<td width="170" class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company CD</ezf:anchor></td>
							<td width="700">
								<ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXXX" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/>
							</td>
							<td width="120"></td>
						</tr>
						<!-- Mail Template -->
						<tr align="left">
							<td width="170" class="stab">Mail Template ID(*)</td>
							<td width="700">
								<ezf:inputText name="mlTmplId" ezfName="mlTmplId" value="XXXXXXXXX1XX" otherAttr=" size=\"12\" maxlength=\"12\" ezftoupper=\"\""/>
							</td>
							<td width="120"></td>
						</tr>
						<tr align="left">
							<td width="170" class="stab">Subject(*)</td>
							<td width="700">
								<ezf:inputText name="mlSubjTmplTxt" ezfName="mlSubjTmplTxt" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" otherAttr=" size=\"80\" maxlength=\"100\""/>
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

		<hr size="1" noshade>

		<%-- item --%>

		<%-- Pagenation --%>
		<table width="990" align="center">
			<tr>
				<td align="left">
					<table border="0" cellpadding="1" cellspacing="0">
						<col>
						<tr>
							<td><ezf:inputButton name="Add" value="Add" htmlClass="pBtn6" /></td>
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
			<col width="180">
			<col width="820">
			<tr>
				<td valign="top">
					<%-- LEFT-TABLE(TOP) --%>
					<div id="leftTopTBL" style="overflow-y:hidden; height:28; overflow-x:hidden; width:;">
						<table cellpadding="1" cellspacing="0" border="1">
							<col width="32">
							<col width="39">
							<col width="50">
							<col width="50">
							<tr style="text-align: center" height="28">
								<td class="pClothBs" nowrap></td>
								<td class="pClothBs" nowrap>No.</td>
								<td class="pClothBs" nowrap></td>
								<td class="pClothBs" nowrap></td>
							</tr>
						</table>
					</div>
					
					<%-- LEFT-TABLE(MID) --%>
					<div id="leftTBL" style="overflow-y:hidden; height:418; overflow-x:hidden; width:;" onScroll="synchroScrollTop( this.id, new Array( 'rightTBL' ) );">
						<table cellpadding="1" cellspacing="0" border="1" id="A_LeftTBL">
							<col width="30">
							<col width="39">
							<col width="50">
							<col width="50">
							<tbody>
								<ezf:row ezfHyo="A">
								<tr style="text-align: left" height="52">
									<!-- # -->
									<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
									<!-- No -->
									<td align="center"><ezf:label name="xxNum_A" ezfName="xxNum_A" ezfHyo="A" ezfArrayNo="0" /></td>

									<!-- View -->
									<td align="center"><ezf:inputButton name="View" value="View" ezfHyo="A" ezfArrayNo="0" htmlClass="cBtn1" /></td>
									<!-- Edit -->
									<td align="center"><ezf:inputButton name="Edit" value="Edit" ezfHyo="A" ezfArrayNo="0" htmlClass="cBtn1" /></td>
								</tr>
								</ezf:row>

								<ezf:skip>
								<tr style="text-align: left" height="52">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_A" ezfname="xxNum_A" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>

								<tr style="text-align: left" height="52">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_A" ezfname="xxNum_A" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
<!----------------------------------- DUMMY --------------------------- -->
								<tr style="text-align: left" height="52">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_A" ezfname="xxNum_A" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="52">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_A" ezfname="xxNum_A" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="52">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_A" ezfname="xxNum_A" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="52">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_A" ezfname="xxNum_A" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="52">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_A" ezfname="xxNum_A" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="52">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_A" ezfname="xxNum_A" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
								<tr style="text-align: left" height="52">
									<!-- # -->
									<td align="center"><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
									<!-- No -->
									<td align="center"><label ezfout name="xxNum_A" ezfname="xxNum_A" ezfhyo="A">XXX</label></td>

									<!-- View -->
									<td align="center"><input type="button" class="pBtn1" value="View" name="View" onclick="sendServer(this)"></td>
									<!-- Edit -->
									<td align="center"><input type="button" class="pBtn1" value="Edit" name="Edit" onclick="sendServer(this)"></td>
								</tr>
<!----------------------------------- DUMMY --------------------------- -->
								</ezf:skip>
							</tbody>
						</table>
					</div>
					
				</td>
				<td>
					<%-- RIGHT-TABLE(TOP) --%>
					<div id="rightTopTBL" style="overflow-y:hidden; height:28; overflow-x:hidden; width:804;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTBL' ) );">
						<table cellpadding="1" cellspacing="0" border="1" width="843">
							<col width="120">
							<col width="120"> 
							<col width="580">
							<tr style="text-align: left" height="28">
								<td class="pClothBs" nowrap>Mail Template ID</td>	
								<td class="pClothBs" nowrap>Locale</td>
								<td class="pClothBs" nowrap>Subject</td>
							</tr>
						</table>
					</div>
					
					<%-- RIGHT-TABLE(MID) --%>
					<div id="rightTBL" style="overflow-y:scroll; height:418; overflow-x:hidden; width:820;" onscroll="synchroScrollTop( this.id, new Array( 'leftTBL' ) );synchroScrollLeft( this.id, new Array( 'rightTopTBL' ) );">
						<table cellpadding="1" cellspacing="0" border="1" width="843" id="A_RightTBL">
							<col width="120">
							<col width="120">
							<col width="580">
							<tbody>
								<ezf:row ezfHyo="A">
								<tr height="52">
									<!-- Mail Template ID -->
									<td><ezf:label name="mlTmplId_A" ezfName="mlTmplId_A" ezfHyo="A" ezfArrayNo="0" /></td>

									<!-- Language -->
									<td><ezf:label name="langNm_A" ezfName="langNm_A" ezfHyo="A" ezfArrayNo="0" /></td>

									<!-- Subject -->
									<td style="word-break:break-all;vertical-align:top;"><ezf:label name="mlSubjTmplTxt_A" ezfName="mlSubjTmplTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
								</tr>
								</ezf:row>

								<ezf:skip>

								<tr height="52">
									<!-- Mail Template ID -->
									<td><label ezfout name="mlTmplId_A" ezfname="mlTmplId_A" ezfhyo="A">NWAB0470M001</label></td>

									<!-- Language -->
									<td><label ezfout name="mlLocId_A" ezfname="mlLocId_A" ezfhyo="A">English</label></td>

									<!-- Subject -->
									<td style="word-break:break-all;vertical-align:top;"><label ezfout name="mlSubjTmplTxt_A" ezfname="mlSubjTmplTxt_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0</label></td>
								</tr>

								<tr height="52">
									<!-- Mail Template ID -->
									<td><label ezfout name="mlTmplId_A" ezfname="mlTmplId_A" ezfhyo="A">NWAB0470M001</label></td>

									<!-- Language -->
									<td><label ezfout name="mlLocId_A" ezfname="mlLocId_A" ezfhyo="A">English</label></td>

									<!-- Subject -->
									<td><label ezfout name="mlSubjTmplTxt_A" ezfname="mlSubjTmplTxt_A" ezfhyo="A">This is test mail.</label></td>
								</tr>
<!----------------------------------- DUMMY --------------------------- -->
								<tr height="52">
									<!-- Mail Template ID -->
									<td><label ezfout name="mlTmplId_A" ezfname="mlTmplId_A" ezfhyo="A">NWAB0470M001</label></td>

									<!-- Language -->
									<td><label ezfout name="mlLocId_A" ezfname="mlLocId_A" ezfhyo="A">English</label></td>

									<!-- Subject -->
									<td><label ezfout name="mlSubjTmplTxt_A" ezfname="mlSubjTmplTxt_A" ezfhyo="A">This is test mail.</label></td>
								</tr>
								<tr height="52">
									<!-- Mail Template ID -->
									<td><label ezfout name="mlTmplId_A" ezfname="mlTmplId_A" ezfhyo="A">NWAB0470M001</label></td>

									<!-- Language -->
									<td><label ezfout name="mlLocId_A" ezfname="mlLocId_A" ezfhyo="A">English</label></td>

									<!-- Subject -->
									<td><label ezfout name="mlSubjTmplTxt_A" ezfname="mlSubjTmplTxt_A" ezfhyo="A">This is test mail.</label></td>
								</tr>
								<tr height="52">
									<!-- Mail Template ID -->
									<td><label ezfout name="mlTmplId_A" ezfname="mlTmplId_A" ezfhyo="A">NWAB0470M001</label></td>

									<!-- Language -->
									<td><label ezfout name="mlLocId_A" ezfname="mlLocId_A" ezfhyo="A">English</label></td>

									<!-- Subject -->
									<td><label ezfout name="mlSubjTmplTxt_A" ezfname="mlSubjTmplTxt_A" ezfhyo="A">This is test mail.</label></td>
								</tr>
								<tr height="52">
									<!-- Mail Template ID -->
									<td><label ezfout name="mlTmplId_A" ezfname="mlTmplId_A" ezfhyo="A">NWAB0470M001</label></td>

									<!-- Language -->
									<td><label ezfout name="mlLocId_A" ezfname="mlLocId_A" ezfhyo="A">English</label></td>

									<!-- Subject -->
									<td><label ezfout name="mlSubjTmplTxt_A" ezfname="mlSubjTmplTxt_A" ezfhyo="A">This is test mail.</label></td>
								</tr>
								<tr height="52">
									<!-- Mail Template ID -->
									<td><label ezfout name="mlTmplId_A" ezfname="mlTmplId_A" ezfhyo="A">NWAB0470M001</label></td>

									<!-- Language -->
									<td><label ezfout name="mlLocId_A" ezfname="mlLocId_A" ezfhyo="A">English</label></td>

									<!-- Subject -->
									<td><label ezfout name="mlSubjTmplTxt_A" ezfname="mlSubjTmplTxt_A" ezfhyo="A">This is test mail.</label></td>
								</tr>
								<tr height="52">
									<!-- Mail Template ID -->
									<td><label ezfout name="mlTmplId_A" ezfname="mlTmplId_A" ezfhyo="A">NWAB0470M001</label></td>

									<!-- Language -->
									<td><label ezfout name="mlLocId_A" ezfname="mlLocId_A" ezfhyo="A">English</label></td>

									<!-- Subject -->
									<td><label ezfout name="mlSubjTmplTxt_A" ezfname="mlSubjTmplTxt_A" ezfhyo="A">This is test mail.</label></td>
								</tr>
								<tr height="52">
									<!-- Mail Template ID -->
									<td><label ezfout name="mlTmplId_A" ezfname="mlTmplId_A" ezfhyo="A">NWAB0470M001</label></td>

									<!-- Language -->
									<td><label ezfout name="mlLocId_A" ezfname="mlLocId_A" ezfhyo="A">English</label></td>

									<!-- Subject -->
									<td><label ezfout name="mlSubjTmplTxt_A" ezfname="mlSubjTmplTxt_A" ezfhyo="A">This is test mail.</label></td>
								</tr>
<!----------------------------------- DUMMY --------------------------- -->

								</ezf:skip>
							</tbody>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<%-- foot --%>
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
