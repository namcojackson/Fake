<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20191002142349 --%>
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
			<input type="hidden" name="pageID" value="NWCL0110Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Reprocess Print Request">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<%-- ########## Menu ########## --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
							<tr>
								<td width="96%">
									<li title = "Reprocess Print Request" class="pTab_ON" ><a href="#">Reproc Prt Rqst</a></li>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id ="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id ="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

				<%-- ########## Header ########## --%>
				<div class="pTab_BODY">
					<table border="0" width="99%" align="center">
						<tr>
							<td>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="130">
									<col width="250">
									<col width="110">
									<col width="250">
									<col width="110">
									<col width="250">
									<tr height="20">
										<td class="stab">Output Type</td>
										<td>
											<ezf:select name="xxTpCd" ezfName="xxTpCd" ezfCodeName="xxTpCd_PL" ezfDispName="procNm_PL" otherAttr=" style=\"width:228px;\""/>
										</td>
										<td class="stab">Batch Name</td>
										<td>
											<ezf:select name="invPrtBatTpCd" ezfName="invPrtBatTpCd" ezfBlank="1" ezfCodeName="invPrtBatTpCd_PL" ezfDispName="invPrtBatTpDescTxt_PL" otherAttr=" style=\"width:228px;\""/>
										</td>
                                        <td class="stab">Output Date</td>
                                        <td>
                                            <ezf:inputText name="procDt_FR" ezfName="procDt_FR" value="MM/DD/YYYY" otherAttr=" maxlength=\"10\" size=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('procDt_FR', 4);"> - 
                                            <ezf:inputText name="procDt_TO" ezfName="procDt_TO" value="MM/DD/YYYY" otherAttr=" maxlength=\"10\" size=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('procDt_TO', 4);">
                                        </td>
									</tr>
									<tr height="20">
                                        <td class="stab">Audit Trail Request ID</td>
                                        <td>
                                            <ezf:inputText name="invPrtRqstSq_FR" ezfName="invPrtRqstSq_FR" value="WWWWWWWWW1" otherAttr=" maxlength=\"28\" size=\"14\""/> - 
                                            <ezf:inputText name="invPrtRqstSq_TO" ezfName="invPrtRqstSq_TO" value="WWWWWWWWW1" otherAttr=" maxlength=\"28\" size=\"14\""/>
                                        </td>
										<td class="stab">Print Branch Name</td>
										<td>
											<ezf:select name="invPrtBrCd" ezfName="invPrtBrCd" ezfBlank="1" ezfCodeName="invPrtBrCd_PL" ezfDispName="invPrtBrDescTxt_PL" otherAttr=" style=\"width:228px;\""/>
										</td>
                                        <td class="stab">Printer Name</td>
                                        <td>
                                            <ezf:select name="reprRptBrNum" ezfName="reprRptBrNum" ezfBlank="1" ezfCodeName="reprRptBrNum_PL" ezfDispName="rptTtlNm_PL" otherAttr=" style=\"width:228px;\""/>
                                        </td>
									</tr>
                                    <tr height="20">
										<td class="stab">Batch Request ID (CUPS)</td>
										<td>
											<ezf:inputText name="cupsPrintRqstId_FR" ezfName="cupsPrintRqstId_FR" value="WWWWWWWWW1" otherAttr=" maxlength=\"240\" size=\"14\""/> - 
											<ezf:inputText name="cupsPrintRqstId_TO" ezfName="cupsPrintRqstId_TO" value="WWWWWWWWW1" otherAttr=" maxlength=\"240\" size=\"14\""/>
										</td>
										<td class="stab">Bill#</td>
										<td>
											<ezf:inputText name="conslBillNum_FR" ezfName="conslBillNum_FR" value="WWWWWWWWW1" otherAttr=" maxlength=\"28\" size=\"14\""/> - 
											<ezf:inputText name="conslBillNum_TO" ezfName="conslBillNum_TO" value="WWWWWWWWW1" otherAttr=" maxlength=\"28\" size=\"14\""/>
										</td>
										<td class="stab">&nbsp;</td>
										<td>&nbsp;</td>
                                    </tr>
                                    <tr height="20">
                                        <td class="stab">Invoice Request#</td>
                                        <td>
                                            <ezf:inputText name="xxInvNum_FR" ezfName="xxInvNum_FR" value="WWWWWWWWW1" otherAttr=" maxlength=\"28\" size=\"14\""/> - 
                                            <ezf:inputText name="xxInvNum_TO" ezfName="xxInvNum_TO" value="WWWWWWWWW1" otherAttr=" maxlength=\"28\" size=\"14\""/>
                                        </td>
										<td class="stab">Invoice#</td>
										<td>
											<ezf:inputText name="invNum_FR" ezfName="invNum_FR" value="WWWWWWWWW1WWW" otherAttr=" size=\"14\" maxlength=\"28\""/> - 
											<ezf:inputText name="invNum_TO" ezfName="invNum_TO" value="WWWWWWWWW1WWW" otherAttr=" size=\"14\" maxlength=\"28\""/>
										</td>
										<td class="stab">Show Printed Invoice Only</td>
										<td>
											<table border="0" width="100%">
											<tr>
												<td><ezf:inputCheckBox name="xxChkBox_SP" ezfName="xxChkBox_SP" value="Y" /></td>
												<td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" />&nbsp;<ezf:inputButton name="PrintMassRequest" value="Print Mass Request" htmlClass="pBtn9" /></td>
											</tr>
											</table>
										</td>
                                    </tr>
								</table>
							</td>
						</tr>
					</table>
					<div style="border-bottom:solid 1px #AAAAAA;padding-top:1px;margin-bottom:0;"></div>
					<br>

					<%-- ########## Detail ########## --%>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%@ page import="business.servlet.NWCL0110.constant.NWCL0110Constant" %>
<%@ page import="business.servlet.NWCL0110.NWCL0110BMsg" %>
<%@ page import="business.servlet.NWCL0110.NWCL0110_ABMsg" %>
<%  NWCL0110BMsg bMsg = (NWCL0110BMsg)databean.getEZDBMsg(); %>
					<table border="0" cellpadding="0" cellspacing="0" width="100%" align="left">
						<tr>
							<td>&nbsp;</td>
							<td>
								<table border="0" cellpadding="1" cellspacing="0">
									<tr>
										<col>
										<col align="right" width="1070">
										<col>
										<td>
											<ezf:inputButton name="Select_All" value="Select All" htmlClass="pBtn7" />&nbsp;
											<ezf:inputButton name="Un_Select_All" value="UnSelect All" htmlClass="pBtn7" />&nbsp;
										</td>
										<td>
											<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
											<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"     value="A" />
											<jsp:param name="ShowingFrom" value="xxPageShowFromNum_10" />
											<jsp:param name="ShowingTo"   value="xxPageShowToNum_10" />
											<jsp:param name="ShowingOf"   value="xxPageShowOfNum_10" />
											<jsp:param name="ShowingCurrent" value="xxPageShowCurNum_10" />
											<jsp:param name="ShowingTotal"   value="xxPageShowTotNum_10" />
											<jsp:param name="ViewMode"       value="FULL" />
											</jsp:include>
										</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr valign="top">
							<td>
								<div id="LeftTBL_Top" style="height:36; overflow-x:hidden; width:26;">
									<table border="1" cellpadding="0" cellspacing="0">
										<col width="25" align="center"></col>	<!-- Select -->
										<tr height="35" valighn="top">
											<td class="pClothBs"><label>&nbsp;</label></td>
										</tr>
									</table>
								</div>
								<div id="LeftTBL"  style="overflow-x:hidden; overflow-y:hidden; height:366; width:26;"  onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
									<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed">
										<col width="26"  align="center"></col>	<!-- Select -->
										<ezf:row ezfHyo="A">
											<tr height="26" id="id_row{EZF_ROW_NUMBER}">
												<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
											</tr>
										</ezf:row>
										<ezf:skip>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="26">
											<td><input type="checkbox"></td>
										</tr>
									</ezf:skip>
									</table>
								</div>
							</td>
							<td>
								<div id="RightTBL_Top" style="overflow-x:hidden; height:36; overflow-y:hidden; width:1068" onScroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ) );">
									<table border="1" cellpadding="1" cellspacing="0" align="left" style="table-layout: fixed">
										<col width="80" align="center"></col>	<!-- Bill# -->
										<col width="80" align="left"></col>		<!-- Invoice# -->
										<col width="145" align="center"></col>	<!-- Printer Name -->
                                        <col width="81" align="center"></col>   <!-- Audit Trail Request ID -->
                                        <col width="150" align="center"></col>  <!-- Batch Request ID -->
										<col width="25" align="center"></col>	<!-- Output Type(Prt) -->
										<col width="25" align="center"></col>	<!-- Output Type(Eml) -->
										<col width="25" align="center"></col>	<!-- Output Type(PDF) -->
										<col width="25" align="center"></col>	<!-- Output Type(EDI) -->
										<col width="60" align="center"></col>	<!-- Request Number(Prt) -->
										<col width="60" align="center"></col>	<!-- Request Number(Eml) -->
										<col width="60" align="center"></col>	<!-- Request Number(PDF) -->
										<col width="125" align="center"></col>	<!-- Batch Name -->
										<col width="125" align="center"></col>	<!-- Print Branch -->
										<col width="80" align="center"></col>	<!-- OrigBill# -->
										<tr>
											<td class="pClothBs" rowspan="2">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'conslBillNum_A')" id="tHead.Order">Bill#</a>
												<img id="sortIMG.conslBillNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
											<td class="pClothBs" rowspan="2">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'invNum_A')" id="tHead.Order">Invoice<br>Number</a>
												<img id="sortIMG.invNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
											<td class="pClothBs" rowspan="2">Printer Name</td>
                                            <td class="pClothBs" rowspan="2">Audit Trail Request ID</td>
                                            <td class="pClothBs" rowspan="2">Batch<br>Request ID (CUPS)</td>
											<td class="pClothBs" colspan="4">Output Type</td>
											<td class="pClothBs" colspan="3">Request#</td>
											<td class="pClothBs" rowspan="2">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'invPrtBatTpDescTxt_A')" id="tHead.Order">Batch Name</a>
												<img id="sortIMG.invPrtBatTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
											<td class="pClothBs" rowspan="2">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'invPrtBrDescTxt_A')" id="tHead.Order">Print Branch</a>
												<img id="sortIMG.invPrtBrDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
											<td class="pClothBs" rowspan="2">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'origConslBillNum_A')" id="tHead.Order">Original<br>Bill#</a>
												<img id="sortIMG.origConslBillNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
										</tr>
										<tr>
											<td class="pClothBs">Prt</td>
											<td class="pClothBs">Eml</td>
											<td class="pClothBs">PDF</td>
											<td class="pClothBs">EDI</td>
											<td class="pClothBs">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'invPrintRqstNum_A')" id="tHead.Order">Prt</a>
												<img id="sortIMG.invPrintRqstNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
											<td class="pClothBs">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'invEmlRqstNum_A')" id="tHead.Order">Eml</a>
												<img id="sortIMG.invEmlRqstNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
											<td class="pClothBs">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'invFtpRqstNum_A')" id="tHead.Order">PDF</a>
												<img id="sortIMG.invFtpRqstNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
										</tr>
									</table>
								</div>
								<div id="RightTBL" style=" overflow-x:scroll; overflow-y:scroll; height:383; width:1085" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ) );">
									<table border="1" cellpadding="0" cellspacing="0" id="A" style="table-layout: fixed">
										<col width="80"  align="left"></col>	<!-- Bill# -->
										<col width="80"  align="left"></col>	<!-- Invoice# -->
										<col width="145" align="left"></col>	<!-- Printer Name -->
                                        <col width="81"  align="left"></col>    <!-- Audit Trail Request ID -->
                                        <col width="150"  align="left"></col>   <!-- Batch Request ID -->
										<col width="25"  align="center"></col>	<!-- Output Type(Prt) -->
										<col width="25"  align="center"></col>	<!-- Output Type(Eml) -->
										<col width="25"  align="center"></col>	<!-- Output Type(PDF) -->
										<col width="25"  align="center"></col>	<!-- Output Type(EDI) -->
										<col width="60"  align="left"></col>	<!-- Request Number(Prt) -->
										<col width="60"  align="left"></col>	<!-- Request Number(Eml) -->
										<col width="60"  align="left"></col>	<!-- Request Number(PDF) -->
										<col width="125" align="left"></col>	<!-- Batch Name -->
										<col width="125" align="left"></col>	<!-- Print Branch -->
										<col width="80"  align="left"></col>	<!-- Original Bill# -->
										<% int i = 0; %>
										<ezf:row ezfHyo="A">
											<% NWCL0110_ABMsg abMsg = bMsg.A.no(i++); %>
											<tr height="26">
												<td><ezf:label name="conslBillNum_A" ezfName="conslBillNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="invNum_A" ezfName="invNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td>
													<ezf:select name="reprRptBrNum_A" ezfName="reprRptBrNum_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="reprRptBrNum_PL" ezfDispName="rptTtlNm_PL" otherAttr=" style=\"width:135px;\""/>
												</td>
                                                <td><ezf:label name="invPrtRqstSq_A" ezfName="invPrtRqstSq_A" ezfHyo="A" ezfArrayNo="0" /></td>
                                                <td><ezf:label name="cupsPrintRqstId_A" ezfName="cupsPrintRqstId_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td>
												<%
												boolean dspPrtProcStsFlg = !"0".equals(abMsg.invPrtProcStsCd_B.getValue());
												boolean dspEmlProcStsFlg = !"0".equals(abMsg.invEmlProcStsCd_B.getValue());
												boolean dspFtpProcStsFlg = !"0".equals(abMsg.invFtpProcStsCd_B.getValue());
												boolean dspEdiProcStsFlg = !"0".equals(abMsg.invEdiProcStsCd_B.getValue());
												%>
												<% if (!dspPrtProcStsFlg) out.println("<span style='visibility:hidden'>"); %>
													<ezf:inputCheckBox name="invPrtProcStsCd_A" ezfName="invPrtProcStsCd_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
												<% if (!dspPrtProcStsFlg) out.println("</span>"); %>
												</td>
												<td>
												<% if (!dspEmlProcStsFlg) out.println("<span style='visibility:hidden'>"); %>
													<ezf:inputCheckBox name="invEmlProcStsCd_A" ezfName="invEmlProcStsCd_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
												<% if (!dspEmlProcStsFlg) out.println("</span>"); %>
												</td>
												<td>
												<% if (!dspFtpProcStsFlg) out.println("<span style='visibility:hidden'>"); %>
													<ezf:inputCheckBox name="invFtpProcStsCd_A" ezfName="invFtpProcStsCd_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
												<% if (!dspFtpProcStsFlg) out.println("</span>"); %>
												</td>
												<td>
												<% if (!dspEdiProcStsFlg) out.println("<span style='visibility:hidden'>"); %>
													<ezf:inputCheckBox name="invEdiProcStsCd_A" ezfName="invEdiProcStsCd_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
												<% if (!dspEdiProcStsFlg) out.println("</span>"); %>
												</td>
												<td><ezf:label name="invPrintRqstNum_A" ezfName="invPrintRqstNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="invEmlRqstNum_A" ezfName="invEmlRqstNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="invFtpRqstNum_A" ezfName="invFtpRqstNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="invPrtBatTpDescTxt_A" ezfName="invPrtBatTpDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="invPrtBrDescTxt_A" ezfName="invPrtBrDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="origConslBillNum_A" ezfName="origConslBillNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
											</tr>
										</ezf:row>
										<ezf:skip>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
											<tr height="26">
												<td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td><td><select></select></td><td><label>WWWWWWWWW1</label></td><td><label>WWWWWWWWW1</label></td>
												<td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td><td><input type="checkbox"></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
												<td><label>WWWWWWW1></label></td><td><label >WWWWWWW1</label></td><td><label >WWWWWWW1</label></td>
											</tr>
										</ezf:skip>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<style TYPE="text/css">
<!--
	a img{border-style:none;}
-->
</style>


<%-- **** Task specific area ends here **** --%>
