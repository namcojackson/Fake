<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161107104559 --%>
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
			<input type="hidden" name="pageID" value="NFBL2120Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Working Folder Screen">

			<center>
				<table width="100%">
					<col valign="top">
					<tr>
						<td>
							<table border="0" cellpadding="2" cellspacing="0" width="100%" style="table-layout:fixed;" >
								<col align="left" width="90">
								<col width="160">
								<col align="left" width="80">
								<col width="160">
								<tr>
									<td class="stab">Organization</td>
									<td><ezf:select name="docMgtOrgCd_H" ezfName="docMgtOrgCd_H" ezfBlank="1" ezfCodeName="docMgtOrgCd_L" ezfDispName="docMgtOrgDescTxt_L" otherAttr=" style=\"width:180;\""/></td>
									<td class="stab">Priority</td>
									<td><ezf:select name="docMgtPrtyCd_H" ezfName="docMgtPrtyCd_H" ezfBlank="1" ezfCodeName="docMgtPrtyCd_L" ezfDispName="docMgtPrtyDescTxt_L" otherAttr=" style=\"width:180;\""/></td>
								</tr>
								<tr>
									<td class="stab">Category</td>
									<td><ezf:select name="docMgtCatgCd_H" ezfName="docMgtCatgCd_H" ezfBlank="1" ezfCodeName="docMgtCatgCd_L" ezfDispName="docMgtCatgDescTxt_L" otherAttr=" style=\"width:180;\""/></td>
									<td colspan="2" align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</td>
								</tr>
							</table>
							<hr>
							<ezf:skip>
							<table>
								<col width="220">
								<tr>
									<td></td>
									<td>
										<ezf:skip>
										<table border="0" cellpadding="0" width="100%">
											<tr>
												<td align="left">
													<table border="0" cellpadding="0" align="left" cellspacing="0">
														<col>
															<tr>
																<td>Results 990 - 1000 of 1000</td>
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
															<td><input type="text" name="xxPageShowCurNum" value="1" size="4" maxlength="4" style="text-align:right" ezfname="xxPageShowCurNum"></td>
															<td class="stab">/</td>
															<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="1" class="pPro" style="text-align:right" readOnly></td>
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
									</td>
								</tr>
							</table>
							</ezf:skip>
							<table border="0" cellpadding="1" cellspacing="0" width="99%">
								<tr align="right">
									<td>
										<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
											<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"     value="A" />
											<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
											<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
											<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
											<jsp:param name="ShowingCurrent"	value="xxPageShowCurNum" />
											<jsp:param name="ShowingTotal"	value="xxPageShowTotNum" />
											<jsp:param name="ViewMode"	value="FULL" />
										</jsp:include>
									</td>
								</tr>
							</table>
				
<%------------------------------------%>
<%-- Detail							--%>
<%------------------------------------%>
							<table align="center">
								<tr>
									<td>
										<table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
											<col width="30" align="center" valign="center">
											<col width="240" align="center" valign="center">
											<col width="160" align="center" valign="center">
											<col width="100" align="center" valign="center">
											<col width="150" align="center" valign="center">
											<col width="80" align="center" valign="center">
											<tr style="height:30">
												<td class="pClothBs"></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrDply_A1' )">Description<img id="sortIMG.xxScrDply_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'docMgtCatgDescTxt_A1' )">Category<img id="sortIMG.docMgtCatgDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'docMgtOrgDescTxt_A1' )">Organization<img id="sortIMG.docMgtOrgDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxUsrSysDtTxt_A1' )">Received at<img id="sortIMG.xxUsrSysDtTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'docMgtPrtyDescTxt_A1' )">Priority<img id="sortIMG.docMgtPrtyDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											</tr>
										</table>
										<div style="overflow-x:hidden; overflow-y:scroll; height:410; width774;">
											<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed" width="600">
												<col width="30" align="center"><!-- Radio Button -->
												<col width="240" align="left"><!-- Description -->
												<col width="160"  align="left"><!-- Category -->
												<col width="100" align="center"><!-- Organization -->
												<col width="150" align="center"><!-- Received at -->
												<col width="80" align="center"><!-- Priority -->

												<ezf:row ezfHyo="A">
													<tr id="id_row{EZF_ROW_NUMBER}" height="26">
														<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="${EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" /></td>
														<td><ezf:inputText name="xxScrDply_A1" ezfName="xxScrDply_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"35\" maxlength=\"50\" ezftoupper=\"\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:label name="docMgtCatgDescTxt_A1" ezfName="docMgtCatgDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="docMgtOrgDescTxt_A1" ezfName="docMgtOrgDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="xxUsrSysDtTxt_A1" ezfName="xxUsrSysDtTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="docMgtPrtyDescTxt_A1" ezfName="docMgtPrtyDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													</tr>
												</ezf:row>
												<ezf:skip>
													<tr class="pEvenNumberBGcolor" height="26">
														<td><input name="xxRadioBtn" type="radio" value="${EZF_ROW_NUMBER}" ezfname="xxRadioBtn"></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Azerty</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Pre-Approved Invoice</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">LFS</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">High</label></td>
													</tr>
													<tr class="pEvenNumberBGcolor" height="26">
														<td><input name="xxRadioBtn" type="radio" value="${EZF_ROW_NUMBER}" ezfname="xxRadioBtn"></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Document Bar Code Review</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Non-PO Invoice</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">PPS</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Normal</label></td>
													</tr>
													<tr class="pEvenNumberBGcolor" height="26">
														<td><input name="xxRadioBtn" type="radio" value="${EZF_ROW_NUMBER}" ezfname="xxRadioBtn"></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">Invoice Adjustment</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">PO Invoice</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">CSA</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
														<td><label ezfout name="xxDtTm_4" ezfname="xxDtTm_4" readonly ezfhyo="A">High</label></td>
													</tr>
												</ezf:skip>
											</table>
										</div>
									</td>
								</tr>
								<tr>
									<td align="center"><ezf:inputButton name="OpenWin_ViewTherefore" value="Show Document" htmlClass="pBtn10" /><td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</center>
<ezf:inputHidden name="xxScrEventNm" ezfName="xxScrEventNm" otherAttr=" size=\"0\" id=\"xxScrEventNm\""/>
<ezf:inputHidden name="xxSrvUrlTxt" ezfName="xxSrvUrlTxt" otherAttr=" size=\"0\" id=\"xxSrvUrlTxt\""/>
<ezf:inputHidden name="docMgtDocId" ezfName="docMgtDocId" otherAttr=" size=\"0\" id=\"docMgtDocId\""/>
<ezf:inputHidden name="docMgtCatgCd" ezfName="docMgtCatgCd" otherAttr=" size=\"0\" id=\"docMgtCatgCd\""/>


<script type="text/javascript" defer>

function firstscript(url, eventNm, docId, DocCatgCd) {
    var thereforeUrl  = document.getElementById(url);
    var screenEventNm = document.getElementById(eventNm);
    var selectDocId = document.getElementById(docId);
    var selectDocCatgCd = document.getElementById(DocCatgCd);

    if (screenEventNm.value == 'OpenWin_ViewTherefore') {
        // therefore Viewer Open
        window.open(thereforeUrl.value, 'therefore', 'width=1000,height=700,scrollbars=yes,status=yes');

        if (window.opener.document.getElementById("docMgtDocId") == null
            || window.opener.document.getElementById("docMgtCatgCd") == null) {
            alert("This pop-up call is an illegal call . This is because there is no setting item of the return value .");
        } else {
            window.opener.document.getElementById("docMgtDocId").value = selectDocId.value;
            window.opener.document.getElementById("docMgtCatgCd").value = selectDocCatgCd.value;
            window.close();
        }
    }
 }

	firstscript('xxSrvUrlTxt', 'xxScrEventNm', 'docMgtDocId', 'docMgtCatgCd');
</script>

<%-- **** Task specific area ends here **** --%>
