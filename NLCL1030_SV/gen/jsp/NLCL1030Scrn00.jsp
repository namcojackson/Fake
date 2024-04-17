<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161020160857 --%>
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
			<input type="hidden" name="pageID" value="NLCL1030Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Inventory ABC Analysis Search">

<center>
	<table height="95%" cellSpacing="0" cellPadding="0" width="100%" border="0">
		<tr>
			<td>
				<%-- ********************** --%>
				<%-- *** Upper Tab Area *** --%>
				<%-- ********************** --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
						<tr>
							<td width="1070px" height="28px" valign="bottom">
								<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
									<tr title="ABC Search">
										<td width="107px" align="center" class="same">ABC Search</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</ezf:skip>

				<div class="pTab_BODY">
                        <!-- ################################################## Search Criteria - [START] ################################################## -->
                        <table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
                            <col width="152">
                            <col width="345">
                            <col width="110">
                            <col width="300">
                            <col width="">
                            <tr>
                                <td class="stab">Saved Search Options</td>
                                <td>
                                    <ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_L" ezfDispName="srchOptNm_L" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
                                </td>
                                <td class="stab">Search Option Name</td>
                                <td class="stab"><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\""/></td>
                                <td>
                                    <ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
                                    <ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
                                </td>
                            </tr>
                        </table>

					<hr style="height: 0px;" cellpadding="0">

                    <!-- ################################################## Header - [START] ################################################## -->
					<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
						<col width=""  align="left">
						<tr valign="top">
							<td>
								<table border="0" cellpadding="0" cellspacing="0" >
									<col width="140">
									<col width="300">
									<col width="">
									<!-- 1 -->
									<tr height="25">
										<td class="stab">ABC Name (*)</td>
										<td class="stab"><ezf:inputText name="abcAsgNm" ezfName="abcAsgNm" value="MONROE EQUIPMENT" otherAttr=" size=\"39\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="25">
										<td class="stab">Warehouse Type</td>
										<td class="stab">
											<ezf:select name="rtlWhCatgCd_H1" ezfName="rtlWhCatgCd_H1" ezfBlank="1" ezfCodeName="rtlWhCatgCd_L1" ezfDispName="rtlWhCatgDescTxt_L1" otherAttr=" style=\"width:278\""/>
										</td>
									</tr>
									<tr height="25">
										<td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" >Warehouse (*)</ezf:anchor></td>
										<td class="stab">
											<ezf:inputText name="rtlWhCdSrchTxt_RW" ezfName="rtlWhCdSrchTxt_RW" value="3Y1" otherAttr=" size=\"12\" maxlength=\"1000\" ezftoupper=\"\""/>
											<ezf:inputText name="rtlWhNmSrchTxt_RW" ezfName="rtlWhNmSrchTxt_RW" value="WOODRIDGE WH- CSA" otherAttr=" size=\"26\" maxlength=\"1000\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr height="25">
										<td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_SubWarehouse" >Sub Warehouse (*)</ezf:anchor></td>
										<td class="stab"><ezf:inputText name="rtlSwhCdSrchTxt_SW" ezfName="rtlSwhCdSrchTxt_SW" value="NEW" otherAttr=" size=\"39\" maxlength=\"1000\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<!-- ################################################## Header - [E N D] ################################################## -->

					<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->

					<hr style="height: 0px;" cellpadding="0">
					<table cellpadding="0" cellspacing="0">
						<col width="003">
						<col width="200">
						<col width="345">
						<col width="555">
						<tr>
							<td></td>
							<td>
								<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
							</td>
							<td></td>
							<td align="right">
								<ezf:skip>
								<table border="0" cellpadding="0" width="100%">
									<tr>
										<td align="left">
											<table border="0" cellpadding="0" align="left" cellspacing="0">
												<col>
													<tr>
													<td>Results 1000 - 1000 of 1000</td>
													</tr>
											</table>
										</td>
										<td align="right">
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="54"  align="center">
												<col width="40"  align="center">
												<col width="16"  align="center">
												<col width="40"  align="center">
												<col width="26"  align="center">
												<col width="10">
												<col>
												<col width="20">
												<col>
												<col width="1">
												<col>
												<tr>
													<td class="stab">Showing</td>
													<td><input type="text" name="xxPageShowCurNum_A" value="9999" size="4" maxlength="4" style="text-align:right" ezfname="xxPageShowCurNum_A"></td>
													<td class="stab">/</td>
													<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
													<td class="stab">page</td>
													<td>&nbsp;</td>
													<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								</ezf:skip>
								<table width="100%">
									<tr align="right">
										<td>
											<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
												<jsp:param name="beanId"         value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"        value="A" />
												<jsp:param name="ShowingFrom"    value="xxPageShowFromNum_A" />
												<jsp:param name="ShowingTo"      value="xxPageShowToNum_A" />
												<jsp:param name="ShowingOf"      value="xxPageShowOfNum_A" />
												<jsp:param name="ShowingCurrent" value="xxPageShowCurNum_A" />
												<jsp:param name="ShowingTotal"   value="xxPageShowTotNum_A" />
												<jsp:param name="ViewMode"       value="FULL" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<!-- ######################################## TO (COMMON)PAGENATION ###################################### --!>

					<!-- ################################################## Detail   - [START] ################################################## -->
					
					<div id="parentBoxA">
						<table border="1">
							<tr>
								<td valign="top" width="1132">
									<div style="float:left; display:block"> <!-- left table -->
										<div id='leftTblHead' style="display:block;">
										</div>
										<div id='leftTbl' style="float:left; display:block; height:320; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
										</div>
									</div><!-- left table -->
									<%-- LEFT-TABLE(TOP) --%>
									
									<!-- right table -->
									<div style="float:left">
										<!-- rightTblHead -->
										<div id='rightTblHead' style="width:1100px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed" id="AHEAD" width="3815px" style="margin-right:20px">
												<col width="200"  align="center">	<!-- Requisition# -->
												<col width="250"  align="center">	<!-- ABC Description -->
												<col width="120"  align="center">	<!-- Warehouse Type -->
												<col width="70"   align="center">	<!-- Warehouse Code -->
												<col width="230"  align="center">	<!-- Warehouse Name-->
												<col width="100"  align="center">	<!-- Sub Warehouse Code -->
												<col width="230"  align="center">	<!-- Analysis Criteria -->
												<col width="80"   align="center">	<!-- From Date -->
												<col width="80"   align="center">	<!-- To Date -->
												<col width="150"  align="center">	<!-- ABC Class Name -->
												<tr height="25">
													<td id="AH0"   class="pClothBs"><a href="#" class="pSortCol" id="abcAsgNm_A"          onclick="columnSort( 'A','abcAsgNm_A')">ABC Name<img id="sortIMG.abcAsgNm_A"                         border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH1"   class="pClothBs"><a href="#" class="pSortCol" id="abcAsgDescTxt_A"     onclick="columnSort( 'A','abcAsgDescTxt_A')">ABC Description<img id="sortIMG.abcAsgDescTxt_A"        border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH2"   class="pClothBs"><a href="#" class="pSortCol" id="rtlWhCatgDescTxt_A"  onclick="columnSort( 'A','rtlWhCatgDescTxt_A')">Warehouse Type<img id="sortIMG.rtlWhCatgDescTxt_A"   border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH3"   class="pClothBs"><a href="#" class="pSortCol" id="rtlWhCd_A"           onclick="columnSort( 'A','rtlWhCd_A')">Warehouse<img id="sortIMG.rtlWhCd_A"                          border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH4"   class="pClothBs"><a href="#" class="pSortCol" id="rtlWhNm_A"           onclick="columnSort( 'A','rtlWhNm_A')">Warehouse Name<img id="sortIMG.rtlWhNm_A"                     border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH5"   class="pClothBs"><a href="#" class="pSortCol" id="rtlSwhDescTxt_A"     onclick="columnSort( 'A','rtlSwhDescTxt_A')">Sub Warehouse<img id="sortIMG.rtlSwhDescTxt_A"          border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH6"   class="pClothBs"><a href="#" class="pSortCol" id="abcAnlsCritNm_A"     onclick="columnSort( 'A','abcAnlsCritNm_A')">Analysis Criteria<img id="sortIMG.abcAnlsCritNm_A"      border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH7"   class="pClothBs"><a href="#" class="pSortCol" id="effFromDt_A"         onclick="columnSort( 'A','effFromDt_A')">From Date<img id="sortIMG.effFromDt_A"                      border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH8"   class="pClothBs"><a href="#" class="pSortCol" id="effThruDt_A"         onclick="columnSort( 'A','effThruDt_A')">To Date<img id="sortIMG.effThruDt_A"                        border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH9"   class="pClothBs"><a href="#" class="pSortCol" id="abcAnlsClsNm_A"      onclick="columnSort( 'A','abcAnlsClsNm_A')">ABC Class Name<img id="sortIMG.abcAnlsClsNm_A"           border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													</tr>
											</table>
										</div>
										<!-- rightTblHead -->
										
										<!-- rightTbl -->
										<div id="rightTbl" style="width:1117px; height:337; display:block; overflow:scroll; margin:0px; padding:0px;">
											<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed"  width="3815px" >
												<col width="200"  align="left">		<!-- Requisition# -->
												<col width="250"  align="center">		<!-- ABC Description -->
												<col width="120"  align="center">		<!-- Warehouse Type -->
												<col width="70"   align="center">		<!-- Warehouse Code -->
												<col width="230"  align="center">		<!-- Warehouse Name-->
												<col width="100"  align="center">		<!-- Sub Warehouse Code -->
												<col width="230"  align="center">		<!-- Analysis Criteria -->
												<col width="80"   align="center">		<!-- From Date -->
												<col width="80"   align="center">		<!-- To Date -->
												<col width="150"  align="center">		<!-- ABC Class Name -->
												<% int indexA = 0; %>
												<ezf:row ezfHyo="A">
												<tr height="25" id="id_leftA_row{EZF_ROW_NUMBER}">
													<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_ABCSetup" otherAttr=" ezfanchortext=\"\" id=\"abcAsgNm_A{EZF_ROW_NUMBER}\""><ezf:label name="abcAsgNm_A" ezfName="abcAsgNm_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
													<td><ezf:inputText name="abcAsgDescTxt_A" ezfName="abcAsgDescTxt_A" value="ABC Classification for Items in Woodridge Warehouse" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"34\" maxlength=\"150\" id=\"abcAsgDescTxt_A\""/></td>
													<td><ezf:inputText name="rtlWhCatgDescTxt_A" ezfName="rtlWhCatgDescTxt_A" value="Equipment" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"50\""/></td>
													<td><ezf:inputText name="rtlWhCdSrchTxt_AW" ezfName="rtlWhCdSrchTxt_AW" value="3Y1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"1000\""/></td>
													<td><ezf:inputText name="rtlWhNmSrchTxt_AW" ezfName="rtlWhNmSrchTxt_AW" value="Woodridge Warehouse - CSA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"1000\""/></td>
													<td><ezf:inputText name="rtlWhCdSrchTxt_AS" ezfName="rtlWhCdSrchTxt_AS" value="NEW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"1000\""/></td>
													<td><ezf:inputText name="abcAnlsCritNm_A" ezfName="abcAnlsCritNm_A" value="Historical Usage - Transactions" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"50\""/></td>
													<td><ezf:label name="effFromDt" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="effThruDt" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="abcAnlsClsNm_A" ezfName="abcAnlsClsNm_A" value="MONROE ABC" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"40\" id=\"abcAnlsClsNm_A\""/></td>
												</tr>
												<% indexA++; %>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
										</div>
										<!-- rightTbl -->
										
									</div> 
									<!-- right table -->
								</td>
							</tr>
						</table>
						<!-- ################################################## Search Result   - [E N D] ################################################## -->
					</div><!-- parentBoxA -->
				</td>
			</tr>
		</table>
	</center>
<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxA", "AHEAD", "A", 1, true);
</script>



<%-- **** Task specific area ends here **** --%>
