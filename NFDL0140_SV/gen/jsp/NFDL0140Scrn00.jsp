<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160222203305 --%>
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
			<input type="hidden" name="pageID" value="NFDL0140Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Stategy Master - Strategy-Customer Type Relation">
<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
<!-- ######################################## HEADER ######################################## -->
				<%-- ###TAB - HEAD --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Actual Counters for Interface" class="pTab_ON"><a href="#">Cust Type</a></li>
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
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center" >
						<tr>
							<td>
								<table border="0" style="table-layout:fixed;" width="95%">
									<col width="5">	<!-- () -->
									<col width="80">	<!-- Strategy Code: -->
									<col width="210">	<!-- Strategy Code(Text output) -->
									<col width="5">	<!-- () -->
									<col width="40">	<!-- Name: -->
									<col width="720">	<!-- Name(Text output) -->
									<col width="100">	<!-- () -->
										<td></td>
										<td class="stab">Strategy Code</td>
										<td>
											<ezf:inputText name="cltStrgyCd" ezfName="cltStrgyCd" value="010" otherAttr=" id=\"cltStrgyCd\" size=\"28\""/>
										</td>
										<td></td>
										<td class="stab">Name</td>
										<td>
											<ezf:inputText name="cltStrgyNm" ezfName="cltStrgyNm" value="STRATEGY 10" otherAttr=" id=\"cltStrgyNm\" size=\"100\""/>
										</td>
										<td></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><hr></td>
						</tr>
					</table>
					<!-- ######################################## DETAIL ######################################## -->
					<table border="0" cellpadding="1" cellspacing="0" style="margin-left:15;">
						<col valign="top">
						<tr>
							<td>
								<div id="Top" style="overflow-y:hidden; width:1050;">
									<table border="1"width="1050" height="24" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed">
										<col width="150" align="center">		<!-- (radio button) -->
										<col width="200" align="center">		<!-- Balance Low -->
										<col width="200" align="center">		<!-- Balance High -->
										<col width="500"  align="center">		<!-- Customer Type -->
										<tr height="27">
											<td class="pClothBs"></td>
											<td class="pClothBs">Balance Low</td>
											<td class="pClothBs">Balance High</td>
											<td class="pClothBs">Customer Type</td>
										</tr>
									</table>
								</div>
								<div id="Detail" style="overflow-y:scroll; width:1070; height:460;">
									<table border="1"width="1050" height="24" id="A" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed">
										<col width="150" align="center">		<!-- (radio button) -->
										<col width="200" align="center">		<!-- Balance Low -->
										<col width="200" align="center">		<!-- Balance High -->
										<col width="500"  align="center">		<!-- Customer Type -->
										<ezf:row ezfHyo="A">
											<tr>
												<td><ezf:inputRadio name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" /></td>
												<td><ezf:inputText name="cltOverDueRangeLowAmt_A" ezfName="cltOverDueRangeLowAmt_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"22\" style=\"text-align:right;\""/></td>
												<td><ezf:inputText name="cltOverDueRangeHighAmt_A" ezfName="cltOverDueRangeHighAmt_A" value="2,000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"22\" style=\"text-align:right;\""/></td>
												<td align="center"><ezf:select name="cltCustTpCd_SV" ezfName="cltCustTpCd_SV" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="cltCustTpCd_CD" ezfDispName="cltCustTpNm_SC" ezfCodeDispHyo="A" otherAttr=" style=\"width:470px;\""/></td>
											</tr>
											<ezf:skip>
											<tr>
												<td><input type="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" /></td>
												<td><input type="text" size="22" maxlength="22" name="cltOverDueRangeLowAmt_A"  ezfName="cltOverDueRangeLowAmt_A"  ezfhyo="A" style="text-align:right;" value="1"></td>
												<td><input type="text" size="22" maxlength="22" name="cltOverDueRangeHighAmt_A" ezfName="cltOverDueRangeHighAmt_A" ezfhyo="A" style="text-align:right;" value="2,000"></td>
												<td align="center"><select name="cltCustTpCd_SV" ezfName="cltCustTpCd_SV" ezfhyo="A" ezflist="cltCustTpCd_CD,cltCustTpNm_SC,99, ,blank" ezfCodeDispHyo="A" style="width:470px;"><option>10-Customer - Canon</option><option>20-Customer - OCE</option><option>30-Customer - SAP</option><option>40-Customer - BPC</option></select></td>
											</tr>
											</ezf:skip>
										</ezf:row>
									</table>
								</div>
							</td>
						</tr>
					</table>
					<table border="0" width="93%" align="center">
					<col width="" align="right">
					<col width="" align="">
					<col width="" align="right">
					<col width="" align="">
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<tr>
									<td >
										<ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn7" otherAttr=" id=\"btnInsertRow\""/>
									</td>
									<td>&nbsp;</td>
									<td>
										<ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn7" otherAttr=" id=\"btnDeleteRow\""/>
									</td>
									<td>&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				</div><!-- pTab_BODY -->
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
