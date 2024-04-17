<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161205175925 --%>
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
			<input type="hidden" name="pageID" value="NSBL0420Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Mods Parts Entry">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Mods Parts Entry" class="pTab_ON"><a href="#">Mod Parts</a></li>
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
				</ezf:skip>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
<%-- ######################################## HEADER ######################################## --%>
				<div class="pTab_BODY">
				<table border="0" width="93%" align="center">
					<col width="" align="" valign="top">
					<col width="" align="" valign="top">
					<col width="" align="" valign="top">
					<col width="" align="" valign="bottom">
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<tr>
									<td class="stab"><ezf:anchor name="mdseCd_AC" ezfName="mdseCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_ItemMstrPop" >Item Code</ezf:anchor></td>
									<td>&nbsp;</td>
									<td>
										<ezf:inputText name="mdseCd_TF" ezfName="mdseCd_TF" value="0000006012345678" otherAttr=" id=\"mdseCd_TF\" size=\"20\" ezftoupper=\"\""/>
									</td>
									<td >
										<ezf:inputButton name="Add" value="Add" htmlClass="pBtn3" otherAttr=" id=\"btnAdd\""/>
									</td>
									<td>&nbsp;</td>
							<!-- Pagination & Navigation--START-->
									<td>
										<ezf:skip>
												<table border="0" cellpadding="1" cellspacing="0">
													<col >
													<col width="40"  align="right">
													<col width="16"  align="center">
													<col width="40"  align="right">
													<col width="16"  align="center">
													<col width="40"  align="right">
													<col width="10">
													<col width="0">
													<col width="1">
													<col width="0">
													<tr>
														<td class="stab">Showing</td>
														<td class="pOut">1</td>
														<td class="stab">to</td>
														<td class="pOut">3</td>
														<td class="stab">of</td>
														<td class="pOut">1000</td>
														<td>&nbsp;</td>
														<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
														<td></td>
														<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
													</tr>
												</table>
										</ezf:skip>
										<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
											<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"     value="A" />
											<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
											<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
											<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
										</jsp:include>
									</td>
							<!-- Pagination & Navigation--END-->
									<td>&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
<%-- ######################################## DETAIL ######################################## --%>
				<table border="0" cellpadding="1" cellspacing="0" width="93%"align="center">
					<col align="left" valign="top">
					<tr>
						<td>
							<div id="Top" style="overflow-x:hidden; width:680;">
								<table border="1" height="24" align="left" border="1" cellpadding="1" cellspacing="0">
									<col width="30"  align="center">		<!-- (Check Box) -->
									<col width="110" align="center">		<!-- Qty -->
									<col width="130" align="center">		<!-- Mdse Code -->
									<col width="390" align="center">		<!-- Mdse Name -->
									<tr height="27">
										<td class="pClothBs"></td>
										<td class="pClothBs">Qty</td>
										<td class="pClothBs">Item Code</td>
										<td class="pClothBs">Item Name</td>
									</tr>
								</table>
							</div>
							<div id="Detail" style="width:697; overflow-y:scroll; height:460;">
								<table border="1" height="24" align="left" id="A" border="1" cellpadding="1" cellspacing="0">
									<col width="30"  align="center">		<!-- (Check Box) -->
									<col width="110" align="center">		<!-- Qty -->
									<col width="130" align="left">		<!-- Mdse Code -->
									<col width="390"  align="left">		<!-- Mdse Name -->
									<ezf:row ezfHyo="A">
										<tr>
											<td>
												<ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
											</td>
											<td>
												<ezf:inputText name="svcModQty_A" ezfName="svcModQty_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right;\" id=\"svcModQty_A\" size=\"12\""/>
											</td>
											<td class="stab">
												<ezf:label name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" />
											</td>
											<td>
												<ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="ABCDEFGHIJABCDEFGHIJABCDEFLASTABCDEFGHIJABCDEFLAST" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"mdseDescShortTxt_A\" size=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/>
											</td>
											<td class="pAuditInfo">
												<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
												<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
												<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
												<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
												<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
											</td>
										</tr>
										<ezf:skip>
										</ezf:skip>
									</ezf:row>
								</table>
							</div>
						</td>
					</tr>
				</table>
				
				<table border="0" width="93%" align="center">
					<col width="" align="" valign="top">
					<col width="" align="" valign="top">
					<col width="" align="" valign="top">
					<col width="" align="" valign="bottom">
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<tr>
									<td >
										<ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn7" otherAttr=" id=\"btnSelectAll\""/>
									</td>
									<td>&nbsp;</td>
									<td>
										<ezf:inputButton name="UnselectAll" value="Unselect All" htmlClass="pBtn7" otherAttr=" id=\"btnUnselectAll\""/>
									</td>
									<td>&nbsp;</td>
									<td>
										<ezf:inputButton name="Delete" value="Delete" htmlClass="pBtn7" otherAttr=" id=\"btnDelete\""/>
									</td>
									<td>&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<ezf:skip>
<script type="text/javascript" src="js/html_preview.js" charset="UTF-8"></script>
</ezf:skip>
<!-- ###SCRIPT -->
<script type="text/javascript">
	function synchroHeaderRightScroll() {
		var topTBL    = document.getElementById( 'topHeaderTBL'    );
		var rightTBL  = document.getElementById( 'rightHeaderTBL'  );
		var leftTBL  = this.document.getElementById( 'leftHeaderTBL'  );
		topTBL.scrollLeft = rightTBL.scrollLeft;
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
	
	function synchroScroll_fromHeaderLeftTblAction() {
		var leftTBL  = this.document.getElementById( 'leftHeaderTBL'  );
		var rightTBL = this.document.getElementById( 'rightHeaderTBL' );
		
		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
	
	function synchroLineRightScroll() {
		var topTBL    = document.getElementById( 'topLineTBL'    );
		var rightTBL  = document.getElementById( 'rightLineTBL'  );
		var leftTBL  = this.document.getElementById( 'leftLineTBL'  );
		topTBL.scrollLeft = rightTBL.scrollLeft;
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
	
	function synchroScroll_fromLineLeftTblAction() {
		var leftTBL  = this.document.getElementById( 'leftLineTBL'  );
		var rightTBL = this.document.getElementById( 'rightLineTBL' );
		
		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
</script>

<%-- **** Task specific area ends here **** --%>
