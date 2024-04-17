<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161107120103 --%>
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
			<input type="hidden" name="pageID" value="NMAL7240Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Freight Rate Setup">

			<center>
				<table width="1133" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center">
							<%-- ###TAB - HEAD --%>
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="Price Group Set Up" class="pTab_ON"><a href="#">Frt Zone Set</a></li>
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
<%------------------------------------%>
<%-- Header							--%>
<%------------------------------------%>
							<div class="pTab_BODY">
								<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:3px;">
									<col width="117">
									<col width="345">
									<col width="110">
									<col width="330">
									<col width="">
									<tr>
										<td class="stab">Saved Search Options</td>
										<td>
											<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_L" ezfDispName="srchOptNm_L" otherEvent1=" onchange=\"sendServer('OnChange_SavedSearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
										</td>
										<td class="stab">Search Option Name</td>
										<td class="stab"><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
										<td>
											<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
											<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
										</td>
									</tr>
								</table>
								<fieldset>
									<table align="left" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<table border="0">
													<col width="120"  align="left">
													<col width="200"  align="left">
													<col width="30"   align="left">
													<col width="120"  align="left">
													<col width="100"  align="left">
													<col width="50"   align="left">

													<tr height="20">
														<td class="stab">Line of Business</td>
														<td>
															<ezf:select name="lineBizTpCd" ezfName="lineBizTpCd" ezfBlank="1" ezfCodeName="lineBizTpCd_P" ezfDispName="lineBizTpDescTxt_P" otherAttr=" style=\"width:200px\""/>
														</td>
														<td>&nbsp;</td>
														<td class="stab">Effective Date From</td>
														<td>
															<ezf:inputText name="effFromDt" ezfName="effFromDt" value="01/01/1999" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
	                                                    	<img src="img/calendar.gif" id="effFromDtCalendar" class="pCalendar" onclick="calendar('effFromDt', 4);" >
	                                                    </td>
														<td>&nbsp;</td>
													</tr>
													<tr height="20">
														<td class="stab">Zone</td>
														<td><ezf:inputText name="frtZoneNum" ezfName="frtZoneNum" value="99" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/></td>
														<td>&nbsp;</td>
														<td class="stab">Effective Date To</td>
														<td>
															<ezf:inputText name="effThruDt" ezfName="effThruDt" value="12/31/9999" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
	                                                    	<img src="img/calendar.gif" id="effThruDtCalendar" class="pCalendar" onclick="calendar('effThruDt', 4);" >
	                                                    </td>
														<td>&nbsp;</td>
													</tr>
													<tr height="20">
														<td class="stab"><ezf:anchor name="xxLinkAncr_HS" ezfName="xxLinkAncr_HS" ezfEmulateBtn="1" ezfGuard="OpenWin_MultiShpgSvcLvl" otherAttr=" id=\"xxLinkAncr_HS\"">Shipping Service Level</ezf:anchor></td>
														<td><ezf:inputText name="xxDsMultMsgDplyTxt_HL" ezfName="xxDsMultMsgDplyTxt_HL" value="FREIGHT TRUCK LIGHT,SATURDAY,ALRDY SHIPPD" otherAttr=" size=\"50\" maxlength=\"1000\""/></td>
														<td>&nbsp;</td>
														<td class="stab">Active</td>
														<td><ezf:inputCheckBox name="actvFlg" ezfName="actvFlg" value="Y" /></td>
														<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" /></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table border="0">
													<col width="515" align="left">
													<col width="100" align="left">
													<col width="120" align="left">
													<col width="80" align="left">
													<col width="120" align="left">
													<col width="20" align="left">
													<col align="left">
													<tr height="20">
														<td>&nbsp;</td>
														<td class="stab">Upload Request</td>
														<td><ezf:inputFile name="xxFileData_UP" ezfName="xxFileData_UP" otherAttr=" size=\"20\" maxlength=\"9999\" ezftoupper=\"\""/></td>
														<td><ezf:inputButton name="Upload" value="Upload" htmlClass="pBtn8" /></td>
														<td class="stab"><ezf:anchor name="xxLinkAncr_DW" ezfName="xxLinkAncr_DW" ezfEmulateBtn="1" ezfGuard="TemplateDownload" >Template Download</ezf:anchor></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</fieldset>
<%------------------------------------%>
<%-- Detail							--%>
<%------------------------------------%>
								<table align="left" border="0" cellpadding="0" cellspacing="0" style="margin:5px;">
									<tr>
										<td>
											<table border="0">
												<col width="880" align="left">
												<col width="100" align="left">
												<col width="100" align="left">
												<tr height="20">
													<td>&nbsp;</td>
													<td><ezf:inputButton name="InsertRow" value="Add" htmlClass="pBtn8" /></td>
													<td><ezf:inputButton name="DeleteRow" value="Delete" htmlClass="pBtn8" /></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td>
														<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
															<col width="30" align="center">		<!-- checkBox -->
															<col width="160" align="center">	<!-- Line Of Business -->
															<col width="40" align="center">		<!-- Zone -->
															<col width="285" align="center">	<!-- Shipping Service Level -->
															<col width="105" align="center">	<!-- From Scale Quantity -->
															<col width="40" align="center">		<!-- UOM -->
															<col width="70" align="center">		<!-- Rate -->
															<col width="40" align="center">		<!-- Unit -->
															<col width="35" align="center">		<!-- Per -->
															<col width="40" align="center">		<!-- UOM -->
															<col width="110" align="center">	<!-- Effective Date From -->
															<col width="110" align="center">	<!-- Effective Date To -->
															<tr>
																<td class="pClothBs">&nbsp;</td>
																<td class="pClothBs">Line of Business</td>
																<td class="pClothBs">Zone</td>
																<td class="pClothBs">Shipping Service Level</td>
																<td class="pClothBs">From Scale<BR>Quantity</td>
																<td class="pClothBs">UOM</td>
																<td class="pClothBs">Rate</td>
																<td class="pClothBs">Unit</td>
																<td class="pClothBs">Per</td>
																<td class="pClothBs">UOM</td>
																<td class="pClothBs">Effective Date From</td>
																<td class="pClothBs">Effective Date To</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td>
														<div style="height:360px; display:block; overflow-y:scroll; overflow-x:none; margin:0px; padding:0px;" >
															<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
																<col width="30" align="center">		<!-- checkBox -->
																<col width="160" align="left">	<!-- Line Of Business -->
																<col width="40" align="center">		<!-- Zone -->
																<col width="285" align="left">	<!-- Shipping Service Level -->
																<col width="105" align="right">	<!-- From Scale Quantity -->
																<col width="40" align="center">		<!-- UOM -->
																<col width="70" align="right">		<!-- Rate -->
																<col width="40" align="center">		<!-- Unit -->
																<col width="35" align="right">		<!-- Per -->
																<col width="40" align="center">		<!-- UOM -->
																<col width="110" align="left">	<!-- Effective Date From -->
																<col width="110" align="left">	<!-- Effective Date To -->
																<ezf:row ezfHyo="A">
																	<tr id="id_row${EZF_ROW_NUMBER}">
																		<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td>
																			<ezf:inputButton name="OpenWin_LOB" value="LOB" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" />
																			<ezf:inputText name="lineBizTpDescTxt_A1" ezfName="lineBizTpDescTxt_A1" value="XXXXXXXXX1XXXXX" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onpaste=\"return fPaste(this);\"" otherAttr=" size=\"15\" maxlength=\"15\" x=\"\" ezftoupper=\"\""/>
																		</td>
																		<td><ezf:inputText name="frtZoneNum_A1" ezfName="frtZoneNum_A1" value="99" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onpaste=\"return fPaste(this);\"" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/></td>
																		<td>
																			<ezf:inputButton name="OpenWin_ShpgSvcLvl" value="SVC_LVL" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" />
																			<ezf:inputText name="shpgSvcLvlDescTxt_A1" ezfName="shpgSvcLvlDescTxt_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onpaste=\"return fPaste(this);\"" otherAttr=" size=\"28\" maxlength=\"50\""/>
																		</td>
																		<td>
																			<ezf:inputText name="fromSclQty_A1" ezfName="fromSclQty_A1" value="9,999,999,999" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onpaste=\"return fPaste(this);\"" otherAttr=" size=\"13\" maxlength=\"13\" ezftoupper=\"\""/>
																		</td>
																		<td>
																			<Div Align="center">
																				<ezf:label name="qtyUnitTpCd_A1" ezfName="qtyUnitTpCd_A1" ezfHyo="A" ezfArrayNo="0" />
																			</Div>
																		</td>
																		<td>
																			<ezf:inputText name="shpgFrtRate_A1" ezfName="shpgFrtRate_A1" value="9,999.99" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onpaste=\"return fPaste(this);\"" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
																		</td>
																		<td>
																			<Div Align="center">
																				<ezf:label name="frtRateCcyCd_A1" ezfName="frtRateCcyCd_A1" ezfHyo="A" ezfArrayNo="0" />
																			</Div>
																		</td>
																		<td>
																			<ezf:inputText name="frtRatePerNum_A1" ezfName="frtRatePerNum_A1" value="999" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onpaste=\"return fPaste(this);\"" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>
																		</td>
																		<td>
																			<Div Align="center">
																				<ezf:label name="perUnitTpCd_A1" ezfName="perUnitTpCd_A1" ezfHyo="A" ezfArrayNo="0" />
																			</Div>
																		</td>
																		<td>
																			<ezf:inputText name="effFromDt_A1" ezfName="effFromDt_A1" value="01/01/1999" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onpaste=\"return fPaste(this);\"" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
						                                                	<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A1', 4, '{EZF_ROW_NUMBER}');" >
																		</td>
																		<td>
																			<ezf:inputText name="effThruDt_A1" ezfName="effThruDt_A1" value="12/31/9999" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onpaste=\"return fPaste(this);\"" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																			<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A1', 4, '{EZF_ROW_NUMBER}');" >
																		</td>
																		<td class="pAuditInfo">
																			<ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
																		</td>
																	</tr>
																</ezf:row>
															</table>
														</div>
													</td>
												<tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</center>

<script type="text/javascript" defer>

function fPaste(obj) {
    var LF = String.fromCharCode(10);
    var TAB = String.fromCharCode(9);
    var clipTextStr = window.clipboardData.getData('Text');
    var arrayClip = clipTextStr.split(LF);
    var offset = 0;

    for (i = 0; i < document.forms[0].elements.length; i ++) {
        if (obj == document.forms[0].elements[i]) {
            offset = i;
            break;
        }
    }

    var targetCt = 0;
    for (cnt = offset; cnt < document.forms[0].elements.length && targetCt < arrayClip.length; cnt ++) {
        if(arrayClip[targetCt].length != 0 && document.forms[0].elements[cnt].name == obj.name){
            var strTmp  = arrayClip[targetCt];
            var tabClip = strTmp.split(TAB);

            tabCt = 0;
            document.forms[0].elements[cnt].value = tabClip[tabCt++].toUpperCase();
            switch (obj.name) {
                case 'lineBizTpDescTxt_A1':
                    cnt = cnt + 1;
                    if (tabClip.length > tabCt) {
                        document.forms[0].elements[cnt].value = tabClip[tabCt++].toUpperCase();
                    }
                case 'frtZoneNum_A1':
                    cnt = cnt + 2;
                    if (tabClip.length > tabCt) {
                        document.forms[0].elements[cnt].value = tabClip[tabCt++].toUpperCase();
                    }
                case 'shpgSvcLvlDescTxt_A1':
                    cnt = cnt + 1;
                    if (tabClip.length > tabCt) {
                        document.forms[0].elements[cnt].value = tabClip[tabCt++].toUpperCase();
                    }
                case 'fromSclQty_A1':
                    cnt = cnt + 1;
                    tabCt = tabCt + 1;
                    if (tabClip.length > tabCt) {
                        document.forms[0].elements[cnt].value = tabClip[tabCt++].toUpperCase();
                    }
                case 'shpgFrtRate_A1':
                    cnt = cnt + 1;
                    tabCt = tabCt + 1;
                    if (tabClip.length > tabCt) {
                        document.forms[0].elements[cnt].value = tabClip[tabCt++].toUpperCase();
                    }
                case 'frtRatePerNum_A1':
                    cnt = cnt + 1;
                    tabCt = tabCt + 1;
                    if (tabClip.length > tabCt) {
                        document.forms[0].elements[cnt].value = tabClip[tabCt++].toUpperCase();
                    }
                case 'effFromDt_A1':
                    cnt = cnt + 1;
                    if (tabClip.length > tabCt) {   
                        document.forms[0].elements[cnt].value = tabClip[tabCt++].toUpperCase();
                    }
                default:
                    break;
            }
            document.forms[0].elements[cnt].focus();
            targetCt = targetCt + 1;
        }
    }
    return false;
}

</script>

<%-- **** Task specific area ends here **** --%>
