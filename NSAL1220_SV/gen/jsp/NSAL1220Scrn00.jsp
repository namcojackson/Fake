<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161202083430 --%>
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
			<input type="hidden" name="pageID" value="NSAL1220Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Contract Branch Revenue Distribution">
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
										<li title="Contract Branch Revenue Distribution" class="pTab_ON"><a href="#">Br Rev Dist</a></li>
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
									<td >
										<ezf:inputButton name="btn11" value="Select All" htmlClass="pBtn7" otherAttr=" id=\"btn11\""/>
									</td>
									<td>
										<ezf:inputButton name="btn12" value="Unselect All" htmlClass="pBtn7" otherAttr=" id=\"btn12\""/>
									</td>
									<td>&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
<%-- ######################################## DETAIL ######################################## --%>
				<table border="0" cellpadding="0" cellspacing="0" width="93%"align="center">
					<col align="left" valign="top">
					<tr>
						<td>
							<div id="Top" style="overflow-x:hidden; width:561;">
								<table border="1"width="560" height="24" align="left" border="1" cellpadding="1" cellspacing="0">
									<col width="20"  align="center">		<!-- (Check Box) -->
									<col width="300" align="center">		<!-- Branch -->
									<col width="240" align="center">		<!-- Percent -->
									<tr height="27">
										<td class="pClothBs"></td>
										<td class="pClothBs">Branch</td>
										<td class="pClothBs">Percent</td>
									</tr>
								</table>
							</div>
							<div id="Detail" style="width:561; overflow-y:scroll; height:460;">
								<table border="1"width="560" height="24" align="left" id="A" border="1" cellpadding="1" cellspacing="0">
									<col width="20"  align="center">		<!-- (Check Box) -->
									<col width="300" align="center">		<!-- Branch -->
									<col width="240"  align="center">		<!-- Percent -->
									<ezf:row ezfHyo="A">
										<tr>
											<td>
												<ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
											</td>
											<td>
												<ezf:inputText name="coaBrCd_A" ezfName="coaBrCd_A" value="690" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:center;\" id=\"coaBrCd_A\" size=\"27\""/>
												<ezf:inputButton name="btn13" value="S" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" />
											</td>
											<td>
												<ezf:inputText name="prcAllocRate_A" ezfName="prcAllocRate_A" value="10" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right;\" id=\"prcAllocRate_A\" size=\"25\""/>
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
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="690" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="10" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="691" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="10" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="691" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="10" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="692" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="10" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="692" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="20" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="692" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="20" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="693" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="20" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="693" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="20" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="693" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="20" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="693" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="40" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="694" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="40" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="694" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="40" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="694" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="40" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="695" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="99.99" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="695" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="99.99" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="695" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="99.99" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="999" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="99.99" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="999" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="99.99" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="999" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="99.99" name="prcAllocRate_A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" class="pPro" value="Y" name="xxChkBox_A">
											</td>
											<td>
												<input style="text-align:center;" id="coaBrCd_A" type="text" size="27" class="pPro" value="999" name="coaBrCd_A">
												<input type="button" class="pBtn1" value="S"  onClick="sendServer('Open_Win_GlComPop')">
											</td>
											<td>
												<input style="text-align:right;" id="prcAllocRate_A" type="text" size="25" class="pPro" value="99.99" name="prcAllocRate_A">
											</td>
										</tr>
										</ezf:skip>
										
									</ezf:row>
								</table>
							</div>
							<div id="Bottom" style="overflow-x:hidden; width:561;">
								<table border="1"width="560" height="24" align="left" border="1" cellpadding="1" cellspacing="0">
									<col width="20"  align="center">		<!-- (Check Box) -->
									<col width="300" align="center">		<!-- Branch -->
									<col width="240" align="center">		<!-- Percent -->
									<tr height="27">
										<td>&nbsp</td>
										<td class="stab" align="right">Total</td>
										<td class="stab" align="center">
											<ezf:label name="prcAllocRate_T"ezfName="prcAllocRate_T"/>%
										</td>
									</tr>
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
										<ezf:inputButton name="btn14" value="Add" htmlClass="pBtn3" otherAttr=" id=\"btn14\""/>
									</td>
									<td>
										<ezf:inputButton name="btn15" value="Delete" htmlClass="pBtn3" otherAttr=" id=\"btn15\""/>
									</td>
									<td>&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				
			<!-- -->
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
