<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170607161820 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- I18N START --%>
<%@ page import="parts.i18n.*" %>

<%	pageContext.setAttribute("ezdi18nlocale", EZDI18NContext.getInstance().getI18NAccessor().getLocale()); %>
<fmt:setLocale value="${ezdi18nlocale}" scope="request" />
<fmt:setBundle basename="I18N_NMAL6800Scrn00" var="I18N_SCREEN_ID" scope="request" />
<fmt:setBundle basename="I18N" var="I18N_DEFAULT" scope="request" />
<%-- I18N END --%>

<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NMAL6800Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NMAL6800Scrn00.title" bundle="${I18N_SCREEN_ID}">Item Master Search Popup</fmt:message>">

			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>


								<table border="0" cellpadding="0" cellspacing="0" height="">
									<tr>
										<td valign="top">
                                        </td>
										<td valign="top" width="342">
											<table cellpadding="0">
												<col align="left" width="121">
												<col align="left" width="221">
												<tr>
													<td class="stab"><fmt:message key="i18n.NMAL6800Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Item Number(*)</fmt:message></td>
													<td><ezf:inputText name="mdseCd_H1" ezfName="mdseCd_H1" value="XX" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab"><fmt:message key="i18n.NMAL6800Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Item Description(*)</fmt:message></td>
													<td><ezf:inputText name="mdseDescShortTxt_H1" ezfName="mdseDescShortTxt_H1" value="XX" otherAttr=" size=\"27\" maxlength=\"250\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_33" ezfEmulateBtn="1" ezfGuard="Manufacturer_Link" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NMAL6800Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Manufacturer(*)</fmt:message></ezf:anchor></td>
													<td><ezf:inputHidden name="mdseItemMnfCd_H1" ezfName="mdseItemMnfCd_H1" value="XX" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/>
													<ezf:inputText name="mdseItemMnfNm_H1" ezfName="mdseItemMnfNm_H1" value="XX" otherAttr=" size=\"27\" maxlength=\"40\" tabindex=\"-1\""/></td>
												</tr>
												<tr>
													<td class="stab"><fmt:message key="i18n.NMAL6800Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Manufacturer Item #(*)</fmt:message></td>
													<td><ezf:inputText name="mnfItemCd_H1" ezfName="mnfItemCd_H1" value="XX" otherAttr=" size=\"27\" maxlength=\"30\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab"><fmt:message key="i18n.NMAL6800Scrn00.label.5" bundle="${I18N_SCREEN_ID}">UPC Code(*)</fmt:message></td>
													<td><ezf:inputText name="upcCd_H1" ezfName="upcCd_H1" value="XX" otherAttr=" size=\"12\" maxlength=\"12\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab"><fmt:message key="i18n.NMAL6800Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Status</fmt:message></td>
													<td><ezf:select name="mdseItemStsCd_H1" ezfName="mdseItemStsCd_H1" ezfBlank="1" ezfCodeName="mdseItemStsCd_L1" ezfDispName="mdseItemStsNm_L1" otherAttr=" style=\"width:197px\""/></td>
												</tr>
												<tr>
													<td class="stab" vAlign="top"><fmt:message key="i18n.NMAL6800Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Long Description(*)</fmt:message></td>
													<td><ezf:textArea name="mdseDescLongTxt_H1" ezfName="mdseDescLongTxt_H1" otherAttr=" size=\"27\" maxlength=\"90\" rows=\"5\" cols=\"25\" ezftoupper=\"\""/></td>
												</tr>
											</table>
										</td>
										<td valign="top" width="350">
											<table width="350" cellpadding="0" border="0">
												<tr>
													<td class="stab"><fmt:message key="i18n.NMAL6800Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Item Type</fmt:message></td>
													<td colspan="3">
														<ezf:select name="mdseItemTpCd_H1" ezfName="mdseItemTpCd_H1" ezfBlank="1" ezfCodeName="mdseItemTpCd_L1" ezfDispName="mdseItemTpNm_L1" otherAttr=" style=\"width:195px\""/>
													</td>
												</tr>
												<tr>
													<td class="stab"><fmt:message key="i18n.NMAL6800Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Item Classification</fmt:message></td>
													<td colspan="3">
														<ezf:select name="mdseItemClsTpCd_H1" ezfName="mdseItemClsTpCd_H1" ezfBlank="1" ezfCodeName="mdseItemClsTpCd_L1" ezfDispName="mdseItemClsTpNm_L1" otherAttr=" style=\"width:195px\""/>
													</td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_28" ezfEmulateBtn="1" ezfGuard="Merchandise_Type_Link" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NMAL6800Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Merchandise Type(*)</fmt:message></ezf:anchor></td>
													<td colspan="3">
														<ezf:inputText name="coaMdseTpCd_H1" ezfName="coaMdseTpCd_H1" value="XX" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/>
														<ezf:inputText name="coaProjDescTxt_H1" ezfName="coaProjDescTxt_H1" value="XX" otherAttr=" size=\"23\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_07" ezfEmulateBtn="1" ezfGuard="COA_Product_Link" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NMAL6800Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Product Code(*)</fmt:message></ezf:anchor></td>
													<td colspan="3">
														<ezf:inputText name="coaProdCd_H1" ezfName="coaProdCd_H1" value="XX" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/>
														<ezf:inputText name="coaProdNm_H1" ezfName="coaProdNm_H1" value="XX" otherAttr=" size=\"23\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr>
													<td class="stab"><fmt:message key="i18n.NMAL6800Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Planning Group</fmt:message></td>
													<td colspan="3">
														<ezf:select name="prchGrpCd_H1" ezfName="prchGrpCd_H1" ezfBlank="1" ezfCodeName="prchGrpCd_L1" ezfDispName="prchGrpNm_L1" otherAttr=" style=\"width:70px\""/>
													</td>
												</tr>
												<tr>
													<td class="stab"><fmt:message key="i18n.NMAL6800Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Third Party</fmt:message></td>
													<td colspan="3">
														<ezf:inputCheckBox name="thirdPtyItemFlg_HY" ezfName="thirdPtyItemFlg_HY" value="Y" otherAttr=" id=\"thirdPtyItemFlg_HY\""/><fmt:message key="i18n.NMAL6800Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Yes</fmt:message>
														<ezf:inputCheckBox name="thirdPtyItemFlg_HN" ezfName="thirdPtyItemFlg_HN" value="N" otherAttr=" id=\"thirdPtyItemFlg_HN\""/><fmt:message key="i18n.NMAL6800Scrn00.label.15" bundle="${I18N_SCREEN_ID}">No</fmt:message>
													</td>
												</tr>
<!--												<tr>-->
<!--													<td class="stab">Pricing Group</td>-->
<!--													<td colspan="3">-->
<!--														<select style="width:195px" name="mdsePrcGrpCd_H1" ezfname="mdsePrcGrpCd_H1" ezflist="mdsePrcGrpCd_L1,mdsePrcGrpNm_L1,99, ,blank">-->
<!--															<option>XXXXXXXXX1XXXXXXXXX2</option>-->
<!--														</select>-->
<!--													</td>-->
<!--												</tr>-->
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_09" ezfEmulateBtn="1" ezfGuard="Mkt_Mdl_Link" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NMAL6800Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Marketing Model(*)</fmt:message></ezf:anchor></td>
													<td colspan="3">
														<ezf:inputText name="mktMdlCd_H1" ezfName="mktMdlCd_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
														<ezf:inputText name="mktMdlNm_H1" ezfName="mktMdlNm_H1" value="XX" otherAttr=" size=\"15\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_10" ezfEmulateBtn="1" ezfGuard="Mkt_Mdl_Seg_Link" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NMAL6800Scrn00.label.17" bundle="${I18N_SCREEN_ID}">Marketing Segment(*)</fmt:message></ezf:anchor></td>
													<td colspan="3">
														<ezf:inputText name="mktMdlSegCd_H1" ezfName="mktMdlSegCd_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
														<ezf:inputText name="mktMdlSegNm_H1" ezfName="mktMdlSegNm_H1" value="XX" otherAttr=" size=\"15\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_08" ezfEmulateBtn="1" ezfGuard="Item_Tmpl_Link" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NMAL6800Scrn00.label.18" bundle="${I18N_SCREEN_ID}">Created From Template(*)</fmt:message></ezf:anchor></td>
													<td colspan="3"><ezf:inputText name="mdseCratTmplNm_H1" ezfName="mdseCratTmplNm_H1" value="XX" otherAttr=" size=\"27\" maxlength=\"50\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab"><fmt:message key="i18n.NMAL6800Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Implementation Date</fmt:message></td>
													<td colspan="3"><ezf:inputText name="mdseItemActvDt_H1" ezfName="mdseItemActvDt_H1" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                                    <img src="img/calendar.gif" class="pCalendar" onclick="calendar('mdseItemActvDt_H1', 4);" > - 
													<ezf:inputText name="mdseItemActvDt_H2" ezfName="mdseItemActvDt_H2" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                                    <img src="img/calendar.gif" class="pCalendar" onclick="calendar('mdseItemActvDt_H2', 4);" ></td>
												</tr>
											</table>
										</td>

										<td valign="top" width="299">
											<table cellpadding="0" width="299" border="0">
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_02" ezfEmulateBtn="1" ezfGuard="Product_Line_Group_Link" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NMAL6800Scrn00.label.20" bundle="${I18N_SCREEN_ID}">Product Level 1(*)</fmt:message></ezf:anchor></td>
													<td><ezf:inputHidden name="zerothProdCtrlCd_H1" ezfName="zerothProdCtrlCd_H1" value="XX" otherAttr=" size=\"7\" maxlength=\"8\" ezftoupper=\"\""/><ezf:inputText name="zerothProdCtrlNm_H1" ezfName="zerothProdCtrlNm_H1" value="XX" otherAttr=" size=\"12\" maxlength=\"50\""/>&nbsp;<ezf:label name="mdseStruElmntTpNm_L1" ezfName="mdseStruElmntTpNm_L1" />
													</td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_03" ezfEmulateBtn="1" ezfGuard="Product_Line_Link" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NMAL6800Scrn00.label.22" bundle="${I18N_SCREEN_ID}">Product Level 2(*)</fmt:message></ezf:anchor></td>
													<td><ezf:inputHidden name="firstProdCtrlCd_H1" ezfName="firstProdCtrlCd_H1" value="XX" otherAttr=" size=\"7\" maxlength=\"8\" ezftoupper=\"\""/><ezf:inputText name="firstProdCtrlNm_H1" ezfName="firstProdCtrlNm_H1" value="XX" otherAttr=" size=\"12\" maxlength=\"50\""/>&nbsp;<ezf:label name="mdseStruElmntTpNm_L2" ezfName="mdseStruElmntTpNm_L2" />
													</td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_04" ezfEmulateBtn="1" ezfGuard="Product_Line_Level_2_Link" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NMAL6800Scrn00.label.23" bundle="${I18N_SCREEN_ID}">Product Level 3(*)</fmt:message></ezf:anchor></td>
													<td><ezf:inputHidden name="scdProdCtrlCd_H1" ezfName="scdProdCtrlCd_H1" value="XX" otherAttr=" size=\"7\" maxlength=\"8\" ezftoupper=\"\""/><ezf:inputText name="scdProdCtrlNm_H1" ezfName="scdProdCtrlNm_H1" value="XX" otherAttr=" size=\"12\" maxlength=\"50\""/>&nbsp;<ezf:label name="mdseStruElmntTpNm_L3" ezfName="mdseStruElmntTpNm_L3" />
													</td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_05" ezfEmulateBtn="1" ezfGuard="Product_Line_Level_3_Link" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NMAL6800Scrn00.label.24" bundle="${I18N_SCREEN_ID}">Product Level 4(*)</fmt:message></ezf:anchor></td>
													<td><ezf:inputHidden name="thirdProdCtrlCd_H1" ezfName="thirdProdCtrlCd_H1" value="XX" otherAttr=" size=\"7\" maxlength=\"8\" ezftoupper=\"\""/><ezf:inputText name="thirdProdCtrlNm_H1" ezfName="thirdProdCtrlNm_H1" value="XX" otherAttr=" size=\"12\" maxlength=\"50\""/>&nbsp;<ezf:label name="mdseStruElmntTpNm_L4" ezfName="mdseStruElmntTpNm_L4" />
													</td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_06" ezfEmulateBtn="1" ezfGuard="Product_Line_Level_4_Link" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NMAL6800Scrn00.label.25" bundle="${I18N_SCREEN_ID}">Product Level 5(*)</fmt:message></ezf:anchor></td>
													<td><ezf:inputHidden name="frthProdCtrlCd_H1" ezfName="frthProdCtrlCd_H1" value="XX" otherAttr=" size=\"7\" maxlength=\"8\" ezftoupper=\"\""/><ezf:inputText name="frthProdCtrlNm_H1" ezfName="frthProdCtrlNm_H1" value="XX" otherAttr=" size=\"12\" maxlength=\"50\""/>&nbsp;<ezf:label name="mdseStruElmntTpNm_L5" ezfName="mdseStruElmntTpNm_L5" />
													</td>
												</tr>
												<tr><!--DS_MDSE_INFO.SLS_MAT_GRP_CD_01-->
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_22" ezfEmulateBtn="1" ezfGuard="Sls_Mat_Group_Link_01" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NMAL6800Scrn00.label.26" bundle="${I18N_SCREEN_ID}">Material Group 1(*)</fmt:message></ezf:anchor></td>
													<td><ezf:inputHidden name="slsMatGrpCd_01" ezfName="slsMatGrpCd_01" value="XX" otherAttr=" size=\"7\" maxlength=\"8\" ezftoupper=\"\""/><ezf:inputText name="slsMatGrpDescTxt_01" ezfName="slsMatGrpDescTxt_01" value="XX" otherAttr=" size=\"12\" maxlength=\"50\""/><fmt:message key="i18n.NMAL6800Scrn00.label.27" bundle="${I18N_SCREEN_ID}">&nbsp;MG1 Desc</fmt:message></label>
													</td>
												</tr>
												<tr><!--DS_MDSE_INFO.SLS_MAT_GRP_CD_02-->
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_23" ezfEmulateBtn="1" ezfGuard="Sls_Mat_Group_Link_02" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NMAL6800Scrn00.label.28" bundle="${I18N_SCREEN_ID}">Material Group 2(*)</fmt:message></ezf:anchor></td>
													<td><ezf:inputHidden name="slsMatGrpCd_02" ezfName="slsMatGrpCd_02" value="XX" otherAttr=" size=\"7\" maxlength=\"8\" ezftoupper=\"\""/><ezf:inputText name="slsMatGrpDescTxt_02" ezfName="slsMatGrpDescTxt_02" value="XX" otherAttr=" size=\"12\" maxlength=\"50\""/><fmt:message key="i18n.NMAL6800Scrn00.label.29" bundle="${I18N_SCREEN_ID}">&nbsp;MG2 Desc</fmt:message></label>
													</td>
												</tr>
												<tr><!--DS_MDSE_INFO.SLS_MAT_GRP_CD_03-->
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_24" ezfEmulateBtn="1" ezfGuard="Sls_Mat_Group_Link_03" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NMAL6800Scrn00.label.30" bundle="${I18N_SCREEN_ID}">Material Group 3(*)</fmt:message></ezf:anchor></td>
													<td><ezf:inputHidden name="slsMatGrpCd_03" ezfName="slsMatGrpCd_03" value="XX" otherAttr=" size=\"7\" maxlength=\"8\" ezftoupper=\"\""/><ezf:inputText name="slsMatGrpDescTxt_03" ezfName="slsMatGrpDescTxt_03" value="XX" otherAttr=" size=\"12\" maxlength=\"50\""/><fmt:message key="i18n.NMAL6800Scrn00.label.31" bundle="${I18N_SCREEN_ID}">&nbsp;MG3 Desc</fmt:message></label>
													</td>
												</tr>
												<tr><!--DS_MDSE_INFO.DS_CMSN_GRP_CD-->
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_25" ezfEmulateBtn="1" ezfGuard="Comsn_Group_Link" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NMAL6800Scrn00.label.32" bundle="${I18N_SCREEN_ID}">Commision Group(*)</fmt:message></ezf:anchor></td>
													<td><ezf:inputHidden name="dsCmsnGrpCd_H1" ezfName="dsCmsnGrpCd_H1" value="XX" otherAttr=" size=\"7\" maxlength=\"8\" ezftoupper=\"\""/><ezf:inputText name="dsCmsnGrpDescTxt_H1" ezfName="dsCmsnGrpDescTxt_H1" value="XX" otherAttr=" size=\"12\" maxlength=\"50\""/><fmt:message key="i18n.NMAL6800Scrn00.label.33" bundle="${I18N_SCREEN_ID}">&nbsp;CG Desc</fmt:message></label>
													</td>
												</tr>
												<tr align="right">
												<td colspan="2" valign="bottom" align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<hr style="height: 0px;" cellpadding="0">
								<table cellpadding="0" cellspacing="0">
									<col width="003">
									<col width="200">
									<col width="780">
									<tr>
										<td></td>
										<td>
											<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
										</td>
										<td align="right">
											<table cellpadding="0" cellspacing="0">
												<tr>
													<td>
														<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
															<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"     value="A" />
															<jsp:param name="ShowingFrom" value="xxPageShowFromNum_10" />
															<jsp:param name="ShowingTo"   value="xxPageShowToNum_10" />
															<jsp:param name="ShowingOf"   value="xxPageShowOfNum_10" />
														</jsp:include>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>

									<div id="parentBoxA">

									<table cellpadding="0" border="0">
										<tr>
											<td valign="Top" width="980">
										<div style="float:left; display:block"> <!-- left table -->
									          <div id='leftTblHead' style="display:block;">
									          </div>
									          <div id='leftTbl' style="float:left; display:block; height:233px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
									          </div>
									    </div>  <!-- left table -->
									    <div style="float:left"> <!-- right table -->
											<div id='rightTblHead' style="width:960px; display:block; overflow:hidden; margin:0px;padding:0px;">
												<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="AHEAD" width="3760px" style="margin-right:20px" >
													<col align="center" width="120">
													<col align="center" width="160">
													<col align="center" width="160">
													<col align="center" width="158">
													<col align="center" width="158">
													<col align="center" width="158">
													<col align="center" width="158">
													<col align="center" width="158">
													<col align="center" width="158">
													<col align="center" width="158">
													<col align="center" width="158">
													<col align="center" width="158">
													<col align="center" width="158">
													<col align="center" width="158">
													<col align="center" width="158">
													<col align="center" width="158">
													<col align="center" width="158">
													<col align="center" width="158">
													<col align="center" width="158">
													<col align="center" width="158">
													<col align="center" width="160">
													<col align="center" width="158">
													<col align="center" width="158">
													<col align="center" width="158">
													<tr height="24">
														<td id="AH1" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCd_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.34" bundle="${I18N_SCREEN_ID}">Item Number</fmt:message><img id="sortIMG.mdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH2" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseDescShortTxt_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.35" bundle="${I18N_SCREEN_ID}">Item Description</fmt:message><img id="sortIMG.mdseDescShortTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mnfItemCd_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.36" bundle="${I18N_SCREEN_ID}">Manufacturer Item #</fmt:message><img id="sortIMG.mnfItemCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseItemTpNm_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Item Type</fmt:message><img id="sortIMG.mdseItemTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseItemClsTpNm_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.37" bundle="${I18N_SCREEN_ID}">Item Class</fmt:message><img id="sortIMG.mdseItemClsTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'coaMdseTpCd_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.38" bundle="${I18N_SCREEN_ID}">Merchandise Type</fmt:message><img id="sortIMG.coaMdseTpCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'coaProdCd_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.39" bundle="${I18N_SCREEN_ID}">Product Code</fmt:message><img id="sortIMG.coaProdCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseItemStsNm_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Status</fmt:message><img id="sortIMG.mdseItemStsNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mktMdlNm_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.40" bundle="${I18N_SCREEN_ID}">Marketing Model</fmt:message><img id="sortIMG.mktMdlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mktMdlSegCd_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.41" bundle="${I18N_SCREEN_ID}">Marketing Segment</fmt:message><img id="sortIMG.mktMdlSegCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'zerothProdCtrlNm_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.42" bundle="${I18N_SCREEN_ID}">Product Level1</fmt:message><img id="sortIMG.zerothProdCtrlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'firstProdCtrlNm_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.43" bundle="${I18N_SCREEN_ID}">Product Level2</fmt:message><img id="sortIMG.firstProdCtrlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'scdProdCtrlNm_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.44" bundle="${I18N_SCREEN_ID}">Product Level3</fmt:message><img id="sortIMG.scdProdCtrlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'thirdProdCtrlNm_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.45" bundle="${I18N_SCREEN_ID}">Product Level4</fmt:message><img id="sortIMG.thirdProdCtrlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'frthProdCtrlNm_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.46" bundle="${I18N_SCREEN_ID}">Product Level5</fmt:message><img id="sortIMG.frthProdCtrlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'slsMatGrpDescTxt_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.47" bundle="${I18N_SCREEN_ID}">Material Group 1</fmt:message><img id="sortIMG.slsMatGrpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'slsMatGrpDescTxt_A2' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.48" bundle="${I18N_SCREEN_ID}">Material Group 2</fmt:message><img id="sortIMG.slsMatGrpDescTxt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'slsMatGrpDescTxt_A3' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.49" bundle="${I18N_SCREEN_ID}">Material Group 3</fmt:message><img id="sortIMG.slsMatGrpDescTxt_A3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsCmsnGrpDescTxt_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.50" bundle="${I18N_SCREEN_ID}">Commissions Group</fmt:message><img id="sortIMG.dsCmsnGrpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseItemMnfNm_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.53" bundle="${I18N_SCREEN_ID}">Manufacturer</fmt:message><img id="sortIMG.mdseItemMnfNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'upcCd_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.51" bundle="${I18N_SCREEN_ID}">UPC Code</fmt:message><img id="sortIMG.upcCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseItemActvDt_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Implementation Date</fmt:message><img id="sortIMG.mdseItemActvDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prchGrpNm_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Planning Group</fmt:message><img id="sortIMG.prchGrpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseDescLongTxt_A1' )" tabindex="-1"><fmt:message key="i18n.NMAL6800Scrn00.label.52" bundle="${I18N_SCREEN_ID}">Long Description</fmt:message><img id="sortIMG.mdseDescLongTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													</tr>
												</table>
											</div><!-- rightTblHead -->
											<div  id="rightTbl" style="width:980px; height:250px; display:block; overflow:scroll; margin:0px; padding:0px;">
												<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="A" width="3777px">
													<col align="left"   width="120">
													<col align="left"   width="160">
													<col align="left"   width="160">
													<col align="left"   width="158">
													<col align="left"   width="158">
													<col align="left"   width="158">
													<col align="left"   width="158">
													<col align="left"   width="158">
													<col align="left"   width="158">
													<col align="left"   width="158">
													<col align="left"   width="158">
													<col align="left"   width="158">
													<col align="left"   width="158">
													<col align="left"   width="158">
													<col align="left"   width="158">
													<col align="left"   width="158">
													<col align="left"   width="158">
													<col align="left"   width="158">
													<col align="left"   width="158">
													<col align="left"   width="158">
													<col align="left"   width="160">
													<col align="left"   width="158">
													<col align="left"   width="158">
													<col align="left"   width="158">
													<ezf:row ezfHyo="A">
													<tr height="24">
														<td align="left" id="mdseCd_A1#{EZF_ROW_NUMBER}">
															<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SelectItem" otherAttr=" tabindex=\"-1\"">
																<ezf:label name="mdseCd_A1" ezfName="mdseCd_A1" ezfHyo="A" ezfArrayNo="0" />
															</ezf:anchor>
														</td>
														<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mnfItemCd_A1" ezfName="mnfItemCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseItemTpNm_A1" ezfName="mdseItemTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseItemClsTpNm_A1" ezfName="mdseItemClsTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxScrItem54Txt_A1" ezfName="xxScrItem54Txt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxScrItem61Txt_A1" ezfName="xxScrItem61Txt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseItemStsNm_A1" ezfName="mdseItemStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mktMdlNm_A1" ezfName="mktMdlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mktMdlSegCd_A1" ezfName="mktMdlSegCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="zerothProdCtrlNm_A1" ezfName="zerothProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="firstProdCtrlNm_A1" ezfName="firstProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="scdProdCtrlNm_A1" ezfName="scdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="thirdProdCtrlNm_A1" ezfName="thirdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="frthProdCtrlNm_A1" ezfName="frthProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="slsMatGrpDescTxt_A1" ezfName="slsMatGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="slsMatGrpDescTxt_A2" ezfName="slsMatGrpDescTxt_A2" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="slsMatGrpDescTxt_A3" ezfName="slsMatGrpDescTxt_A3" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsCmsnGrpDescTxt_A1" ezfName="dsCmsnGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseItemMnfNm_A1" ezfName="mdseItemMnfNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="upcCd_A1" ezfName="upcCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseItemActvDt_A1" ezfName="mdseItemActvDt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="prchGrpNm_A1" ezfName="prchGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseDescLongTxt_A1" ezfName="mdseDescLongTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<!--
												-->
													</tr>
													</ezf:row>
													<ezf:skip>
													<tr height="24">
														<td align="left" id="mdseCd_A1#{EZF_ROW_NUMBER}">
															<a href="#" onclick="sendServer('SelectItem')" ezfHyo="A" tabindex="-1">
																<label ezfout name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW1</label>
															</a>
														</td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WW" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mnfItemCd_A1" ezfName="mnfItemCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemTpNm_A1" ezfName="mdseItemTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemClsTpNm_A1" ezfName="mdseItemClsTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemStsNm_A1" ezfName="mdseItemStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlNm_A1" ezfName="mktMdlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlSegCd_A1" ezfName="mktMdlSegCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="zerothProdCtrlNm_A1" ezfName="zerothProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="firstProdCtrlNm_A1" ezfName="firstProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="scdProdCtrlNm_A1" ezfName="scdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thirdProdCtrlNm_A1" ezfName="thirdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="frthProdCtrlNm_A1" ezfName="frthProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A1" ezfName="slsMatGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A2" ezfName="slsMatGrpDescTxt_A2" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A3" ezfName="slsMatGrpDescTxt_A3" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="dsCmsnGrpDescTxt_A1" ezfName="dsCmsnGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemMnfNm_A1" ezfName="mdseItemMnfNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="upcCd_A1" ezfName="upcCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemActvDt_A1" ezfName="mdseItemActvDt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="prchGrpNm_A1" ezfName="prchGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdsePrcGrpNm_A1" ezfName="mdsePrcGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescLongTxt_A1" ezfName="mdseDescLongTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td align="left" id="mdseCd_A1#{EZF_ROW_NUMBER}">
															<a href="#" onclick="sendServer('SelectItem')" ezfHyo="A" tabindex="-1">
																<label ezfout name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW1</label>
															</a>
														</td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WW" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mnfItemCd_A1" ezfName="mnfItemCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemTpNm_A1" ezfName="mdseItemTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemClsTpNm_A1" ezfName="mdseItemClsTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemStsNm_A1" ezfName="mdseItemStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlNm_A1" ezfName="mktMdlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlSegCd_A1" ezfName="mktMdlSegCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="zerothProdCtrlNm_A1" ezfName="zerothProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="firstProdCtrlNm_A1" ezfName="firstProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="scdProdCtrlNm_A1" ezfName="scdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thirdProdCtrlNm_A1" ezfName="thirdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="frthProdCtrlNm_A1" ezfName="frthProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A1" ezfName="slsMatGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A2" ezfName="slsMatGrpDescTxt_A2" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A3" ezfName="slsMatGrpDescTxt_A3" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="dsCmsnGrpDescTxt_A1" ezfName="dsCmsnGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemMnfNm_A1" ezfName="mdseItemMnfNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="upcCd_A1" ezfName="upcCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemActvDt_A1" ezfName="mdseItemActvDt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="prchGrpNm_A1" ezfName="prchGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdsePrcGrpNm_A1" ezfName="mdsePrcGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescLongTxt_A1" ezfName="mdseDescLongTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td align="left" id="mdseCd_A1#{EZF_ROW_NUMBER}">
															<a href="#" onclick="sendServer('SelectItem')" ezfHyo="A" tabindex="-1">
																<label ezfout name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW1</label>
															</a>
														</td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WW" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mnfItemCd_A1" ezfName="mnfItemCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemTpNm_A1" ezfName="mdseItemTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemClsTpNm_A1" ezfName="mdseItemClsTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemStsNm_A1" ezfName="mdseItemStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlNm_A1" ezfName="mktMdlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlSegCd_A1" ezfName="mktMdlSegCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="zerothProdCtrlNm_A1" ezfName="zerothProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="firstProdCtrlNm_A1" ezfName="firstProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="scdProdCtrlNm_A1" ezfName="scdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thirdProdCtrlNm_A1" ezfName="thirdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="frthProdCtrlNm_A1" ezfName="frthProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A1" ezfName="slsMatGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A2" ezfName="slsMatGrpDescTxt_A2" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A3" ezfName="slsMatGrpDescTxt_A3" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="dsCmsnGrpDescTxt_A1" ezfName="dsCmsnGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemMnfNm_A1" ezfName="mdseItemMnfNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="upcCd_A1" ezfName="upcCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemActvDt_A1" ezfName="mdseItemActvDt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="prchGrpNm_A1" ezfName="prchGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdsePrcGrpNm_A1" ezfName="mdsePrcGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescLongTxt_A1" ezfName="mdseDescLongTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td align="left" id="mdseCd_A1#{EZF_ROW_NUMBER}">
															<a href="#" onclick="sendServer('SelectItem')" ezfHyo="A" tabindex="-1">
																<label ezfout name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW1</label>
															</a>
														</td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WW" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mnfItemCd_A1" ezfName="mnfItemCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemTpNm_A1" ezfName="mdseItemTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemClsTpNm_A1" ezfName="mdseItemClsTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemStsNm_A1" ezfName="mdseItemStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlNm_A1" ezfName="mktMdlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlSegCd_A1" ezfName="mktMdlSegCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="zerothProdCtrlNm_A1" ezfName="zerothProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="firstProdCtrlNm_A1" ezfName="firstProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="scdProdCtrlNm_A1" ezfName="scdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thirdProdCtrlNm_A1" ezfName="thirdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="frthProdCtrlNm_A1" ezfName="frthProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A1" ezfName="slsMatGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A2" ezfName="slsMatGrpDescTxt_A2" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A3" ezfName="slsMatGrpDescTxt_A3" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="dsCmsnGrpDescTxt_A1" ezfName="dsCmsnGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemMnfNm_A1" ezfName="mdseItemMnfNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="upcCd_A1" ezfName="upcCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemActvDt_A1" ezfName="mdseItemActvDt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="prchGrpNm_A1" ezfName="prchGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdsePrcGrpNm_A1" ezfName="mdsePrcGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescLongTxt_A1" ezfName="mdseDescLongTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td align="left" id="mdseCd_A1#{EZF_ROW_NUMBER}">
															<a href="#" onclick="sendServer('SelectItem')" ezfHyo="A" tabindex="-1">
																<label ezfout name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW1</label>
															</a>
														</td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WW" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mnfItemCd_A1" ezfName="mnfItemCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemTpNm_A1" ezfName="mdseItemTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemClsTpNm_A1" ezfName="mdseItemClsTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemStsNm_A1" ezfName="mdseItemStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlNm_A1" ezfName="mktMdlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlSegCd_A1" ezfName="mktMdlSegCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="zerothProdCtrlNm_A1" ezfName="zerothProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="firstProdCtrlNm_A1" ezfName="firstProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="scdProdCtrlNm_A1" ezfName="scdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thirdProdCtrlNm_A1" ezfName="thirdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="frthProdCtrlNm_A1" ezfName="frthProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A1" ezfName="slsMatGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A2" ezfName="slsMatGrpDescTxt_A2" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A3" ezfName="slsMatGrpDescTxt_A3" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="dsCmsnGrpDescTxt_A1" ezfName="dsCmsnGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemMnfNm_A1" ezfName="mdseItemMnfNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="upcCd_A1" ezfName="upcCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemActvDt_A1" ezfName="mdseItemActvDt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="prchGrpNm_A1" ezfName="prchGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdsePrcGrpNm_A1" ezfName="mdsePrcGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescLongTxt_A1" ezfName="mdseDescLongTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td align="left" id="mdseCd_A1#{EZF_ROW_NUMBER}">
															<a href="#" onclick="sendServer('SelectItem')" ezfHyo="A" tabindex="-1">
																<label ezfout name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW1</label>
															</a>
														</td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WW" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mnfItemCd_A1" ezfName="mnfItemCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemTpNm_A1" ezfName="mdseItemTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemClsTpNm_A1" ezfName="mdseItemClsTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemStsNm_A1" ezfName="mdseItemStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlNm_A1" ezfName="mktMdlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlSegCd_A1" ezfName="mktMdlSegCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="zerothProdCtrlNm_A1" ezfName="zerothProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="firstProdCtrlNm_A1" ezfName="firstProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="scdProdCtrlNm_A1" ezfName="scdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thirdProdCtrlNm_A1" ezfName="thirdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="frthProdCtrlNm_A1" ezfName="frthProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A1" ezfName="slsMatGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A2" ezfName="slsMatGrpDescTxt_A2" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A3" ezfName="slsMatGrpDescTxt_A3" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="dsCmsnGrpDescTxt_A1" ezfName="dsCmsnGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemMnfNm_A1" ezfName="mdseItemMnfNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="upcCd_A1" ezfName="upcCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemActvDt_A1" ezfName="mdseItemActvDt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="prchGrpNm_A1" ezfName="prchGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdsePrcGrpNm_A1" ezfName="mdsePrcGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescLongTxt_A1" ezfName="mdseDescLongTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td align="left" id="mdseCd_A1#{EZF_ROW_NUMBER}">
															<a href="#" onclick="sendServer('SelectItem')" ezfHyo="A" tabindex="-1">
																<label ezfout name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW1</label>
															</a>
														</td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WW" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mnfItemCd_A1" ezfName="mnfItemCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemTpNm_A1" ezfName="mdseItemTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemClsTpNm_A1" ezfName="mdseItemClsTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemStsNm_A1" ezfName="mdseItemStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlNm_A1" ezfName="mktMdlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlSegCd_A1" ezfName="mktMdlSegCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="zerothProdCtrlNm_A1" ezfName="zerothProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="firstProdCtrlNm_A1" ezfName="firstProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="scdProdCtrlNm_A1" ezfName="scdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thirdProdCtrlNm_A1" ezfName="thirdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="frthProdCtrlNm_A1" ezfName="frthProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A1" ezfName="slsMatGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A2" ezfName="slsMatGrpDescTxt_A2" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A3" ezfName="slsMatGrpDescTxt_A3" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="dsCmsnGrpDescTxt_A1" ezfName="dsCmsnGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemMnfNm_A1" ezfName="mdseItemMnfNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="upcCd_A1" ezfName="upcCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemActvDt_A1" ezfName="mdseItemActvDt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="prchGrpNm_A1" ezfName="prchGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdsePrcGrpNm_A1" ezfName="mdsePrcGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescLongTxt_A1" ezfName="mdseDescLongTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td align="left" id="mdseCd_A1#{EZF_ROW_NUMBER}">
															<a href="#" onclick="sendServer('SelectItem')" ezfHyo="A" tabindex="-1">
																<label ezfout name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW1</label>
															</a>
														</td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WW" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mnfItemCd_A1" ezfName="mnfItemCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemTpNm_A1" ezfName="mdseItemTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemClsTpNm_A1" ezfName="mdseItemClsTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemStsNm_A1" ezfName="mdseItemStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlNm_A1" ezfName="mktMdlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlSegCd_A1" ezfName="mktMdlSegCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="zerothProdCtrlNm_A1" ezfName="zerothProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="firstProdCtrlNm_A1" ezfName="firstProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="scdProdCtrlNm_A1" ezfName="scdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thirdProdCtrlNm_A1" ezfName="thirdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="frthProdCtrlNm_A1" ezfName="frthProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A1" ezfName="slsMatGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A2" ezfName="slsMatGrpDescTxt_A2" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A3" ezfName="slsMatGrpDescTxt_A3" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="dsCmsnGrpDescTxt_A1" ezfName="dsCmsnGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemMnfNm_A1" ezfName="mdseItemMnfNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="upcCd_A1" ezfName="upcCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemActvDt_A1" ezfName="mdseItemActvDt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="prchGrpNm_A1" ezfName="prchGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdsePrcGrpNm_A1" ezfName="mdsePrcGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescLongTxt_A1" ezfName="mdseDescLongTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td align="left" id="mdseCd_A1#{EZF_ROW_NUMBER}">
															<a href="#" onclick="sendServer('SelectItem')" ezfHyo="A" tabindex="-1">
																<label ezfout name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW1</label>
															</a>
														</td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WW" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mnfItemCd_A1" ezfName="mnfItemCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemTpNm_A1" ezfName="mdseItemTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemClsTpNm_A1" ezfName="mdseItemClsTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemStsNm_A1" ezfName="mdseItemStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlNm_A1" ezfName="mktMdlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlSegCd_A1" ezfName="mktMdlSegCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="zerothProdCtrlNm_A1" ezfName="zerothProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="firstProdCtrlNm_A1" ezfName="firstProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="scdProdCtrlNm_A1" ezfName="scdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thirdProdCtrlNm_A1" ezfName="thirdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="frthProdCtrlNm_A1" ezfName="frthProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A1" ezfName="slsMatGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A2" ezfName="slsMatGrpDescTxt_A2" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A3" ezfName="slsMatGrpDescTxt_A3" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="dsCmsnGrpDescTxt_A1" ezfName="dsCmsnGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemMnfNm_A1" ezfName="mdseItemMnfNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="upcCd_A1" ezfName="upcCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemActvDt_A1" ezfName="mdseItemActvDt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="prchGrpNm_A1" ezfName="prchGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdsePrcGrpNm_A1" ezfName="mdsePrcGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescLongTxt_A1" ezfName="mdseDescLongTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td align="left" id="mdseCd_A1#{EZF_ROW_NUMBER}">
															<a href="#" onclick="sendServer('SelectItem')" ezfHyo="A" tabindex="-1">
																<label ezfout name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW1</label>
															</a>
														</td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WW" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mnfItemCd_A1" ezfName="mnfItemCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemTpNm_A1" ezfName="mdseItemTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemClsTpNm_A1" ezfName="mdseItemClsTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemStsNm_A1" ezfName="mdseItemStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlNm_A1" ezfName="mktMdlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mktMdlSegCd_A1" ezfName="mktMdlSegCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="zerothProdCtrlNm_A1" ezfName="zerothProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="firstProdCtrlNm_A1" ezfName="firstProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="scdProdCtrlNm_A1" ezfName="scdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thirdProdCtrlNm_A1" ezfName="thirdProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="frthProdCtrlNm_A1" ezfName="frthProdCtrlNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A1" ezfName="slsMatGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A2" ezfName="slsMatGrpDescTxt_A2" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="slsMatGrpDescTxt_A3" ezfName="slsMatGrpDescTxt_A3" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="dsCmsnGrpDescTxt_A1" ezfName="dsCmsnGrpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemMnfNm_A1" ezfName="mdseItemMnfNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="upcCd_A1" ezfName="upcCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemActvDt_A1" ezfName="mdseItemActvDt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="prchGrpNm_A1" ezfName="prchGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdsePrcGrpNm_A1" ezfName="mdsePrcGrpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescLongTxt_A1" ezfName="mdseDescLongTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													</ezf:skip>
												<!--
												-->
												</table>
											</div><!-- rightTbl -->
										</td>
									</tr>
								</table>
								
						</td>
					</tr>
					<tr>
				</table>
			</center>
<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxA", "AHEAD", "A", 2, false, true);
</script>


<%-- **** Task specific area ends here **** --%>
