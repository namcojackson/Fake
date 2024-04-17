<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160222113709 --%>
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
			<input type="hidden" name="pageID" value="NMAL7110Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price Contract Search">
			
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPConstant" %>
<%@ page import="business.servlet.NMAL7110.NMAL7110BMsg" %>
<% 
	NMAL7110BMsg bMsg = (NMAL7110BMsg)databean.getEZDBMsg(); 
%>

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	<td>
		<%-- ###TAB - HEAD --%>
		<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
		<ezf:skip>
		<div class="pTab_HEAD">
			<ul>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="96%">
							<div>
								<li title="Price Contract" class="pTab_ON"><a href="#">PrcContrSrch</a></li>
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
		<div class="pTab_BODY">
<%------------------------------------%>
<%-- Save Option					--%>
<%------------------------------------%>
		<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
			<col width="152">
			<col width="345">
			<col width="110">
			<col width="300">
			<col width="">
			<tr>
				<td class="stab">Saved Search Options</td>
				<td>
					<ezf:select name="srchOptPk" ezfName="srchOptPk" ezfBlank="1" ezfCodeName="srchOptPk_L1" ezfDispName="srchOptNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_SavedSearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
				</td>
				<td class="stab">Search Option Name</td>
				<td class="stab"><ezf:inputText name="srchOptNm" ezfName="srchOptNm" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
				<td>
					<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
					<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
				</td>
			</tr>
		</table>
		<hr style="height: 0px;" cellpadding="0">
		<table>
<!-- <%-- ######################################## FROM HEADER ######################################## --%> -->
		<tr>
			<td>
				<table width="1100" border="0" cellspacing="0" cellpadding="0" align="center">
					<tr>
						<td style="border:1pt solid black;">
							<table border="0" style="table-layout:fixed" width="100%">
								<col style="text-align:right;" width="60px">
								<col style="text-align:left;" width="120px">
								<col style="text-align:left;" width="30px">
								<col style="text-align:right;" width="70px">
								<col style="text-align:left;" width="70px">
								<tr>
									<td style="text-align:left;"><span style="font-weight: bold; border-bottom:solid 1px #000000;">Search Filters</span></td>
								</tr>
								<tr>
									<td>Price Contract Name(*)</td>
									<td><ezf:inputText name="prcContrNm" ezfName="prcContrNm" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
									<td></td>
									<td>Effective Date From:</td>
									<td>
										<ezf:inputText name="effFromDt" ezfName="effFromDt" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effFromDt\""/>
										<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);">
									</td>
								</tr>
								<tr>
									<td>Price Contract#(*)</td>
									<td><ezf:inputText name="prcContrNum" ezfName="prcContrNum" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
									<td></td>
									<td>Effective Date To:</td>
									<td>
										<ezf:inputText name="effThruDt" ezfName="effThruDt" otherAttr=" size=\"10\" maxlength=\"10\" id=\"\""/>
										<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);">
									</td>
								</tr>
								<tr>
									<td>CSAP Contract:</td>
									<td><ezf:inputCheckBox name="assnPgmContrFlg" ezfName="assnPgmContrFlg" value="Y" /></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td>Bid#(*)</td>
									<td><ezf:inputText name="prcContrCustBidNum" ezfName="prcContrCustBidNum" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
									<td></td>
									<td>Active:</td>
									<td><ezf:inputCheckBox name="actvFlg" ezfName="actvFlg" value="Y" /></td>
								</tr>
								<tr>
									<td>Line Of Business:</td>
									<td>
										<ezf:select name="lineBizTpCd" ezfName="lineBizTpCd" ezfBlank="1" ezfCodeName="lineBizTpCd_L1" ezfDispName="lineBizTpDescTxt_L1" otherAttr=" style=\"width:146px;\""/>
									</td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border="0" style="table-layout:fixed" width="100%">
					<col style="text-align:right;" width="700">
					<col style="text-align:center;" width="100">
					<col style="text-align:center;" width="100">
					<col style="text-align:center;" width="200">
					
					<tr>
						<td>&nbsp;</td>
						<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
						<td><ezf:inputButton name="ADD" value="Add" htmlClass="pBtn6" /></td>
					</tr>
				</table>
			</td>
		</tr>
<!-- <%-- ######################################## TO HEADER ########################################## --%> -->
<!-- <%-- ######################################## FROM (COMMON)DETAIL ######################################## --%> -->
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" align="center">
					<col width="520">
					<col width="580">
					<tr>
						<td>
							<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
						</td>
						<td align="right">
							<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
								<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"     value="A" />
								<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
								<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
								<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
								<jsp:param name="ShowingCurrent" value="xxPageShowCurNum" />
								<jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
								<jsp:param name="ViewMode"       value="FULL" />
							</jsp:include>
						</td>
					</tr>
				</table>
				<% int idx = 0; %>
				<div id="parentBoxA">
					<table>
						<tr>
							<td width="10"></td>
							<td>
								<div style="float:left; display:block"> <!-- left table -->
									<div id="leftTblHead" style="display:block;"></div>
									<div id="leftTbl" style="float:left; display:block; height:273px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
								</div>  <!-- left table -->
								<div style="float:left"> <!-- right table -->
									<div id="rightTblHead" style="width:1097px; display:block; overflow:hidden; margin:0px;padding:0px;">
										<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0"  id="AHEAD" width="1299px" style="margin-right:20px">
											<col width="100" align="center">
											<col width="410" align="center">
											<col width="265" align="center">
											<col width="60" align="center">
											<col width="70" align="center">
											<col width="85" align="center">
											<col width="85" align="center">
											<tr height="34">
												<td id="AH0" class="pClothBs colFix" align="center">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcContrPk_A' )">
														Contract ID<img id="sortIMG.prcContrPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td id="AH1" class="pClothBs" align="center">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcContrNm_A' )">
														Price Contract Name<img id="sortIMG.prcContrNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td id="AH2" class="pClothBs" align="center">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcContrNum_A' )">
														Price Contract#<img id="sortIMG.prcContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td id="AH3" class="pClothBs" align="center">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'assnPgmContrFlg_A' )">
														CSAP Contract<img id="sortIMG.assnPgmContrFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td id="AH4" class="pClothBs" align="center">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'actvFlg_A' )">
														Status<img id="sortIMG.actvFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td id="AH5" class="pClothBs" align="center">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'effFromDt_A' )">
														Effective Date From<img id="sortIMG.effFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td id="AH6" class="pClothBs" align="center">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'effThruDt_A' )">
														Effective Date To<img id="sortIMG.effThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
											</tr>
										</table>
									</div><!-- rightTblHead-->
									<div id="rightTbl" style="width:1100px; height:290px; display:block; overflow:scroll; margin:0px; padding:0px;" >
										<table style="table-layout:fixed; white-space:nowrap;" border="1" cellpadding="0" cellspacing="0" id="A" width="1300px" >
											<col width="100" align="right">
											<col width="410" align="left">
											<col width="265" align="left">
											<col width="60" align="center">
											<col width="70" align="center">
											<col width="85" align="center">
											<col width="85" align="center">
											<ezf:row ezfHyo="A">
												<tr id="id_row{EZF_ROW_NUMBER}"  height="24">
													<td><ezf:label name="prcContrPk_A" ezfName="prcContrPk_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="GoToPrcContr" ><ezf:label name="prcContrNm_A" ezfName="prcContrNm_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
													<td><ezf:label name="prcContrNum_A" ezfName="prcContrNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:inputCheckBox name="assnPgmContrFlg_A" ezfName="assnPgmContrFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<td>
														<% if (ZYPConstant.FLG_ON_Y.equals(bMsg.A.no(idx).actvFlg_A.getValue())) { %>
															<!--label ezfout name="actvFlg_A" ezfname="actvFlg_A" ezfHyo="A">ACTIVE</label-->
															<label>ACTIVE</label>
														<% }else { %> <label>INACTIVE</label> <% } %>
													</td>
													<td><ezf:label name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<% idx++; %>
												</tr>
											</ezf:row>
										</table>
									</div><!-- rightTbl-->
								</div><!-- right table-->
							</td>
						</tr>
					</table>
				</div><!-- parentBoxA -->
			</td>
		</tr>
<!-- <%-- ######################################## TO (COMMON)DETAIL ########################################## --%> -->
		</table>
		</div>
	</td>
	</tr>
	</table>
</center>

<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", 2, false);
</script>


<%-- **** Task specific area ends here **** --%>
