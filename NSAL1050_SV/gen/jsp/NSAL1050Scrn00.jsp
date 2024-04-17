<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180625092053 --%>
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
			<input type="hidden" name="pageID" value="NSAL1050Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="T&C Attributes Setup">
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="T&C Attributes Setup" class="pTab_ON"><a href="#">T&C Attrb</a></li>
									</div>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

				<div class="pTab_BODY">
					<table width="99%"  style="margin-top: 5px; margin-left: 5px" border="0">
						<tr>
							<td>
								<table cellSpacing="0" cellPadding="1" border="0">
									<colgroup>
										<col width="50px">
										<col width="90px">
										<col width="300px">
										<col width="40px">
										<col width="60px">
										<col width="10px">
										<col width="300px">
										<col width="50px">
									</colgroup>
									<tbody>
										<tr>
											<td></td>
											<td class="stab"><Div Align="left">T&C Attribute(*)</Div></td>
											<td>
												<ezf:inputText name="svcTermAttrbDescTxt" ezfName="svcTermAttrbDescTxt" value="9999999" otherAttr=" size=\"40\" maxlength=\"30\""/>
											</td>
											<td></td>
											<td class="stab"><Div Align="left">Active Only:</Div></td>
											<td><ezf:inputCheckBox name="xxChkBox_H" ezfName="xxChkBox_H" value="Y" /></td>
											<td></td>
											<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
										</tr><br>
									</tbody>
								</table>
							</td>
						</tr>
					</table>

					<table width="99%">
						<tr align="right">
							<td>
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; ">
									<col width="80">
									<col width="100">
									<col width="120">
									<col width="218">
									<col width="580">
									<tr height='25px'>
										<td><ezf:inputButton name="Addline" value="Add line" htmlClass="pBtn6" /></td>
										<td><ezf:inputButton name="Deleteline" value="Delete line" htmlClass="pBtn6" /></td>
										<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

<%-- ######################################## DETAIL ######################################## --%>
					<table>
						<tr>
							<td>
								<div id="parentBoxA">
									<div style="float:left; display:block"> <!-- left table -->
										<div id='leftTblHead' style="display:block;">
										</div>
										<div id='leftTbl' style="float:left; display:block; height:443px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
										</div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id='rightTblHead' style="width:1098px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1552px" style="margin-right:20px">
												<col align="center" width="30px"><!-- Check Box -->
												<col align="center" width="280px"><!-- T&C Attribute -->
												<col align="center" width="180px"><!-- Short Name -->
												<col align="center" width="182px"><!-- LOV DDF Mapping -->
												<col align="center" width="130px"><!-- Data Type -->
												<col align="center" width="50px"><!-- Seq -->
												<col align="center" width="130px"><!-- Category -->
												<col align="center" width="50px"><!-- Contract -->
												<col align="center" width="50px"><!-- Machine -->
												<col align="center" width="50px"><!-- Active -->
												<col align="center" width="110px"><!-- Start -->
												<col align="center" width="110px"><!-- End -->
												<tr align="center" height='25px'>
													<td id="AH0" class="pClothBs">&nbsp;</td>
													<td id="AH1" class="pClothBs">T&C Attribute</td>
													<td id="AH2" class="pClothBs">Short Name</td>
													<td id="AH3" class="pClothBs">LOV DDF Mapping</td>
													<td id="AH4" class="pClothBs">Data Type</td>
													<td id="AH5" class="pClothBs">Seq</td>
													<td id="AH6" class="pClothBs">Category</td>
													<td id="AH7" class="pClothBs">Contract</td>
													<td id="AH8" class="pClothBs">Machine</td>
													<td id="AH9" class="pClothBs">Active</td>
													<td id="AH10" class="pClothBs">Start</td>
													<td id="AH11" class="pClothBs">End</td>
												</tr>
											</table>
										</div>
										<div id="rightTbl" style="width:1115px; height:460px; display:block; overflow:scroll; margin:0px; padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1552px">
												<col align="center" width="30px"><!-- Check Box -->
												<col align="left"  width="280px"><!-- T&C Attribute -->
												<col align="left"  width="180px"><!-- Short Name -->
												<col align="left"  width="182px"><!-- LOV DDF Mapping -->
												<col align="center" width="130px"><!-- Data Type -->
												<col align="center" width="50px"><!-- Seq -->
												<col align="center" width="130px"><!-- Category -->
												<col align="center" width="50px"><!-- Contract -->
												<col align="center" width="50px"><!-- Machine -->
												<col align="center" width="50px"><!-- Active -->
												<col align="center" width="110px"><!-- Start -->
												<col align="center" width="110px"><!-- End -->
												<% int i = 0; %>
												<ezf:row ezfHyo="A">
												<% i++; %>
													<tr align="center" height='25px' id="A_rightTBLRow#{EZF_ROW_NUMBER}">
														<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="svcTermAttrbDescTxt_A" ezfName="svcTermAttrbDescTxt_A" value="---------1---------2---------3---------4---------5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"38\" maxlength=\"50\""/></td>
														<td><ezf:inputText name="svcTermCondAttrbNm_A" ezfName="svcTermCondAttrbNm_A" value="---------1---------2---------3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"30\""/></td>
														<td><ezf:inputText name="svcTermCondSrcDescTxt_A" ezfName="svcTermCondSrcDescTxt_A" value="---------1---------2---------3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"50\""/>
														<ezf:inputButton name="OpenWin_TandC_Source" value="S" ezfHyo="F" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" tabindex=\"-1\""/></td>
														<td><ezf:select name="svcTermDataTpCd_1V" ezfName="svcTermDataTpCd_1V" ezfHyo="A" ezfArrayNo="0" ezfCodeName="svcTermDataTpCd_1C" ezfDispName="svcTermDataTpDescTxt_1D" otherEvent1=" onchange=\"sendServer('SelectDataType', '{EZF_ROW_NUMBER}')\"" /></td>
														<td><ezf:inputText name="svcTermAttrbSortNum_A" ezfName="svcTermAttrbSortNum_A" value="-" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right;\""/></td>
														<td><ezf:select name="svcTermCondCatgPk_1V" ezfName="svcTermCondCatgPk_1V" ezfHyo="A" ezfArrayNo="0" ezfCodeName="svcTermCondCatgPk_1C" ezfDispName="svcTermCondCatgDescTxt_1D" /></td>
														<td><ezf:inputCheckBox name="xxChkBox_AC" ezfName="xxChkBox_AC" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputCheckBox name="xxChkBox_AM" ezfName="xxChkBox_AM" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputCheckBox name="xxChkBox_AA" ezfName="xxChkBox_AA" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, '{EZF_ROW_NUMBER}');" ezfname="effFromDt_A" ezfhyo="A" ></td>
														<td><ezf:inputText name="effToDt_A" ezfName="effToDt_A" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effToDt_A', 4, '{EZF_ROW_NUMBER}');"  ezfname="effToDt_A" ezfhyo="A" ></td>
														<td class="pAuditInfo">
															<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
															<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
															<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
															<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
															<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
														</td>
													</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
												
											</table>
										</div>
									</div> <!-- right table -->
								</div> <!--parent box -->
							</td>
						</tr>
					</table>
<!-- ################################################## Search Result   - [E N D] ################################################## -->







				</div>
			</td>
		</tr>
	</table>
</center>

<style TYPE="text/css">
<!--
	tr.checkLineBGcolor{background-color:yellow;}
	a img{border-style:none;}
-->
</style>

<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, true,true);
</script>



<%-- **** Task specific area ends here **** --%>
