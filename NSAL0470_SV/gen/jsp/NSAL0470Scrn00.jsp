<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160215102451 --%>
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
			<input type="hidden" name="pageID" value="NSAL0470Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Complete Contract">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>

				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
							<tr>
								<td width="96%">
									<li title = "Complete Contract" class="pTab_ON" ><a href="#">Cplt & Contr</a></li>
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

				<div class="pTab_BODY">
				<br>
<%-- ######################################## DETAIL ######################################## --%>
<%@ page import="business.servlet.NSAL0470.NSAL0470BMsg" %>
<%@ page import="business.servlet.NSAL0470.NSAL0470_ABMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%  NSAL0470BMsg bMsg = (NSAL0470BMsg)databean.getEZDBMsg(); %>

					<table border="0" width="100%">
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
									<col width="50">
									<col width="250">
									<col width="50">
									<col width="180">
									<col width="50">
									<col width="80">
									<col width="70">
									<col width="80">
									<col width="150">
									<col width="30">
									<tr>
										<td class="stab">Validation</td>
										<td><ezf:select name="dsContrVldPk_H3" ezfName="dsContrVldPk_H3" ezfBlank="1" ezfCodeName="dsContrVldPk_H1" ezfDispName="dsContrVldNm_H2" otherAttr=" style=\"width:230;\""/></td>
										<td class="stab">Serial#(*)</td>
										<td><ezf:inputText name="serNum" ezfName="serNum" otherAttr=" size=\"20\""/></td>
										<td class="stab">Outcome</td>
										<td>
											<ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" />
											<ezf:label name="dsContrVldStsDescTxt_H1" ezfName="dsContrVldStsDescTxt_H1" />
										</td>
										<td>
											<ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" />
											<ezf:label name="dsContrVldStsDescTxt_H2" ezfName="dsContrVldStsDescTxt_H2" />
										</td>
										<td>
											<ezf:inputCheckBox name="xxChkBox_H3" ezfName="xxChkBox_H3" value="Y" />
											<ezf:label name="dsContrVldStsDescTxt_H3" ezfName="dsContrVldStsDescTxt_H3" />
										</td>
										<td>
											<ezf:inputCheckBox name="xxChkBox_H4" ezfName="xxChkBox_H4" value="Y" />
											<ezf:label name="dsContrVldStsDescTxt_H4" ezfName="dsContrVldStsDescTxt_H4" />
										</td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
									</tr>
								</table>
							</td>
						</tr>
						
						<tr>
							<td>
								<hr/>
							</td>
						</tr>
					</table>

					<ezf:skip>
					<table border="0" cellpadding="1" cellspacing="0">
						<col width="740">
						<col width="54"  align="center">
						<col width="40"  align="right">
						<col width="16"  align="center">
						<col width="40"  align="right">
						<col width="16"  align="center">
						<col width="40"  align="right">
						<col width="10">
						<col>
						<col width="1">
						<col>
						<tr>
							<td></td>
							<td class="stab">Showing</td>
							<td class="pOut">1</td>
							<td class="stab">to</td>
							<td class="pOut">18</td>
							<td class="stab">of</td>
							<td class="pOut">18</td>
							<td>&nbsp;</td>
							<td><input type="button" class="pBtn3" value="Prev" name="PagePrev"></td>
							<td></td>
							<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
						</tr>
					</table>
					</ezf:skip>

					<table width="100%">
						<tr>
							<td>
								<table width="98%">
									<tr align="right">
										<td>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="A" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<table border="0" cellpadding="0" cellspacing="0" width="98%" align="center">
						<col align="left" valign="top">
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="630">
									<col width="477">
									<tr>
										<td align="right" valign="top">
											<div id="TopLeftTBL" style="overflow-x:none; overflow-y:none; width:632; float:left;">
												<table border="1" cellpadding="1" cellspacing="0"  style="table-layout: fixed;">
													<col width="20" align="center"><!-- Expansion/Contraction -->
													<col width="80" align="center"><!-- Contract# -->
													<col width="80" align="center"><!-- Serial# -->
													<col width="350" align="center"><!-- Validation Description -->
													<col width="100" align="center"><!-- Outcome -->
													<tr>
														<td class="pClothBs">
														<c:set var="allDplyCtrlFlg" scope="page" value="<%= bMsg.xxDplyCtrlFlg_AL.getValue()%>" />
														<c:choose>
                                                               <c:when test="${allDplyCtrlFlg == 'N'}">
                                                                   <ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="AllExpand" >
                                                                       <img src="./img/biz/rightarrow3.png">
                                                                   </ezf:anchor>
                                                               </c:when>
														       <c:when test="${allDplyCtrlFlg == 'Y'}">
                                                                   <ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="AllCollapse" >
                                                                       <img src="./img/biz/dpwnarrow3.png">
                                                                   </ezf:anchor>
                                                               </c:when>
                                                        </c:choose>
														</td>
														<td class="pClothBs">Contract#</td>
														<td class="pClothBs">Serial#</td>
														<td class="pClothBs">Validation Description</td>
														<td class="pClothBs">Outcome</td>
													</tr>
												</table>
											</div>

											<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:414; width:632; float:left;" onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
												<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed;">
													<col width="20" align="center"><!-- Expansion/Contraction -->
													<col width="80" align="center"><!-- Contract# -->
													<col width="80" align="center"><!-- Serial# -->
													<col width="350" align="center"><!-- Validation Description -->
													<col width="100" align="center"><!-- Outcome -->
													<% int i = 0; %>
													<ezf:row ezfHyo="A">
														<c:set var="dplyCtrlFlg" scope="page" value="<%= bMsg.A.no(i).xxDplyCtrlFlg_A.getValue()%>" />
														<% i++; %>
														<tr>

															<td id="xxDplyCtrlFlg_A#{EZF_ROW_NUMBER}">
																<c:choose>
																    <c:when test="${empty dplyCtrlFlg}">
                                                                        &nbsp;
                                                                    </c:when>
																    <c:when test="${dplyCtrlFlg == 'N'}">
                                                                        <ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Expand" >
                                                                            <img src="./img/biz/rightarrow2.png">
                                                                        </ezf:anchor>
                                                                    </c:when>
                                                                    <c:when test="${dplyCtrlFlg == 'Y'}">
                                                                        <ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Collapse">
                                                                            <img src="./img/biz/downarrow2.png">
                                                                        </ezf:anchor>
                                                                    </c:when>
																</c:choose>
															</td>
															
															<td><ezf:inputText name="dsContrNum_A" ezfName="dsContrNum_A" value="12038992" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" id=\"dsContrNum_A#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputText name="serNum_A" ezfName="serNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" id=\"serNum_A#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputText name="dsContrVldNm_A" ezfName="dsContrVldNm_A" value="CANON CHECK BRANCH" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\" id=\"dsContrVldNm_A#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputText name="dsContrVldStsDescTxt_A" ezfName="dsContrVldStsDescTxt_A" value="SUCCESS" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"dsContrVldStsDescTxt_A#{EZF_ROW_NUMBER}\""/></td>
														</tr>
													</ezf:row>
													<ezf:skip>
														<tr>
															<td>&nbsp;</td>
															<td><input type="text" class="pPro" size="10" value="" name="dsContrNum_A" ezfname="dsContrNum_A" id="dsContrNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="" name="serNum_A" ezfname="serNum_A" id="serNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="50" value="CHECK CUST CREDIT HOLD" name="vldDescTxt_A" ezfname="vldDescTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pWar" size="20" value="WARNING" name="vldRsltTxt_A" ezfname="vldRsltTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td><input type="text" class="pPro" size="10" value="" name="dsContrNum_A" ezfname="dsContrNum_A" id="dsContrNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="" name="serNum_A" ezfname="serNum_A" id="serNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="50" value="CHEcK CREDIT CARD AUTHORIZATION" name="vldDescTxt_A" ezfname="vldDescTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="20" value="SUCCESS" name="vldRsltTxt_A" ezfname="vldRsltTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><img src="./img/biz/rightarrow2.png"></td>
															<td><input type="text" class="pPro" size="10" value="" name="dsContrNum_A" ezfname="dsContrNum_A" id="dsContrNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="MSK101010" name="serNum_A" ezfname="serNum_A" id="serNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="50" value="" name="vldDescTxt_A" ezfname="vldDescTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pErr" size="20" value="ERROR" name="vldRsltTxt_A" ezfname="vldRsltTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><img src="./img/biz/rightarrow2.png"></td>
															<td><input type="text" class="pPro" size="10" value="" name="dsContrNum_A" ezfname="dsContrNum_A" id="dsContrNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="MNB05459" name="serNum_A" ezfname="serNum_A" id="serNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="50" value="" name="vldDescTxt_A" ezfname="vldDescTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="20" value="SUCCESS" name="vldRsltTxt_A" ezfname="vldRsltTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><img src="./img/biz/downarrow2.png"></td>
															<td><input type="text" class="pPro" size="10" value="" name="dsContrNum_A" ezfname="dsContrNum_A" id="dsContrNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="TSTG38989" name="serNum_A" ezfname="serNum_A" id="serNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="50" value="CANON CHECK START READ" name="vldDescTxt_A" ezfname="vldDescTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pErr" size="20" value="ERROR" name="vldRsltTxt_A" ezfname="vldRsltTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td><input type="text" class="pPro" size="10" value="" name="dsContrNum_A" ezfname="dsContrNum_A" id="dsContrNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="TSTG38989" name="serNum_A" ezfname="serNum_A" id="serNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="50" value="CHECK CREDIT CARD AUTHORIZATION" name="vldDescTxt_A" ezfname="vldDescTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="20" value="SUCCESS" name="vldRsltTxt_A" ezfname="vldRsltTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td><input type="text" class="pPro" size="10" value="" name="dsContrNum_A" ezfname="dsContrNum_A" id="dsContrNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="TSTG38989" name="serNum_A" ezfname="serNum_A" id="serNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="50" value="CANON CHECK TIERS" name="vldDescTxt_A" ezfname="vldDescTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="20" value="SUCCESS" name="vldRsltTxt_A" ezfname="vldRsltTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td><input type="text" class="pPro" size="10" value="" name="dsContrNum_A" ezfname="dsContrNum_A" id="dsContrNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="TSTG38989" name="serNum_A" ezfname="serNum_A" id="serNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="50" value="CANON USAGE LINE & SUBLINE EFFECTIVITY CHECK" name="vldDescTxt_A" ezfname="vldDescTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="20" value="SUCCESS" name="vldRsltTxt_A" ezfname="vldRsltTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td><input type="text" class="pPro" size="10" value="" name="dsContrNum_A" ezfname="dsContrNum_A" id="dsContrNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="TSTG38989" name="serNum_A" ezfname="serNum_A" id="serNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="50" value="CANON USAGE LINE & SUBLINE TERMINATION CHECK" name="vldDescTxt_A" ezfname="vldDescTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="20" value="SUCCESS" name="vldRsltTxt_A" ezfname="vldRsltTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td><input type="text" class="pPro" size="10" value="" name="dsContrNum_A" ezfname="dsContrNum_A" id="dsContrNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="TSTG38989" name="serNum_A" ezfname="serNum_A" id="serNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="50" value="CANON CHECK MACHINE FSB" name="vldDescTxt_A" ezfname="vldDescTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="20" value="SUCCESS" name="vldRsltTxt_A" ezfname="vldRsltTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td><input type="text" class="pPro" size="10" value="" name="dsContrNum_A" ezfname="dsContrNum_A" id="dsContrNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="TSTG38989" name="serNum_A" ezfname="serNum_A" id="serNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="50" value="CANON CHECK UNIQUE USAGE LINE" name="vldDescTxt_A" ezfname="vldDescTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="20" value="SUCCESS" name="vldRsltTxt_A" ezfname="vldRsltTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="60" value="" name="vldMsgTxt_A" ezfname="vldMsgTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td><input type="text" class="pPro" size="10" value="" name="dsContrNum_A" ezfname="dsContrNum_A" id="dsContrNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="TSTG38989" name="serNum_A" ezfname="serNum_A" id="serNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="50" value="CANON CHECK USG LINE AC RULE" name="vldDescTxt_A" ezfname="vldDescTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="20" value="SUCCESS" name="vldRsltTxt_A" ezfname="vldRsltTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td><input type="text" class="pPro" size="10" value="" name="dsContrNum_A" ezfname="dsContrNum_A" id="dsContrNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="TSTG38989" name="serNum_A" ezfname="serNum_A" id="serNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="50" value="CANON UPDATE SCHEDULE DATE" name="vldDescTxt_A" ezfname="vldDescTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="20" value="SUCCESS" name="vldRsltTxt_A" ezfname="vldRsltTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td><input type="text" class="pPro" size="10" value="" name="dsContrNum_A" ezfname="dsContrNum_A" id="dsContrNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="TSTG38989" name="serNum_A" ezfname="serNum_A" id="serNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="50" value="CANON CHECK ITEMS ALLOWANCE" name="vldDescTxt_A" ezfname="vldDescTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="20" value="SUCCESS" name="vldRsltTxt_A" ezfname="vldRsltTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td><input type="text" class="pPro" size="10" value="" name="dsContrNum_A" ezfname="dsContrNum_A" id="dsContrNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="TSTG38989" name="serNum_A" ezfname="serNum_A" id="serNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="50" value="CANON COUNTER GROUP CHECK" name="vldDescTxt_A" ezfname="vldDescTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="20" value="SUCCESS" name="vldRsltTxt_A" ezfname="vldRsltTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td><input type="text" class="pPro" size="10" value="" name="dsContrNum_A" ezfname="dsContrNum_A" id="dsContrNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="TSTG38989" name="serNum_A" ezfname="serNum_A" id="serNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="50" value="CANON CHECK NEGATIVE INCOICE AMOUNT" name="vldDescTxt_A" ezfname="vldDescTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="20" value="SUCCESS" name="vldRsltTxt_A" ezfname="vldRsltTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td><input type="text" class="pPro" size="10" value="" name="dsContrNum_A" ezfname="dsContrNum_A" id="dsContrNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="TSTG38989" name="serNum_A" ezfname="serNum_A" id="serNum_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="50" value="CANON CHECK USG LINE INV RULE" name="vldDescTxt_A" ezfname="vldDescTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="20" value="SUCCESS" name="vldRsltTxt_A" ezfname="vldRsltTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
													
													</ezf:skip>
												</table>
											</div>
										</td>
										<td align="left" valign="top">
											<div id="topRightTBL" style="overflow-x:hidden; width:460; overflow-y:none;">
												<table border="1" cellpadding="1" cellspacing="0"  style="table-layout: fixed;">
													<col width="852" align="center"><!-- Message -->
													<tr height="23">
														<td class="pClothBs">Message</td>
													</tr>
												</table>
											</div>

											<div id="RightTBL" style="overflow-x:scroll; width:477; overflow-y:scroll; height:431;" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) ); synchroScrollLeft( this.id, new Array( 'topRightTBL' ) );">
												<table border="1" cellpadding="1" cellspacing="0" id="A2" style="table-layout: fixed;">
													<col width="852" align="center"><!-- Message -->
													<ezf:row ezfHyo="A">
														<tr>
															<td><ezf:inputText name="dsContrVldRsltMsgTxt_A" ezfName="dsContrVldRsltMsgTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"120\" id=\"dsContrVldRsltMsgTxt_A#{EZF_ROW_NUMBER}\""/></td>
														</tr>
													</ezf:row>
													<ezf:skip>
														<tr>
															<td><input type="text" class="pPro" size="120" value="Warning occurred." name="vldMsgTxt_A" ezfname="vldMsgTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="120" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8WWWWWWWWW9WWWWWWWWWAWWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="vldMsgTxt_A" ezfname="vldMsgTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="120" value="" name="vldMsgTxt_A" ezfname="vldMsgTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="120" value="" name="vldMsgTxt_A" ezfname="vldMsgTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="120" value="Start read incorrect/missing." name="vldMsgTxt_A" ezfname="vldMsgTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="120" value="" name="vldMsgTxt_A" ezfname="vldMsgTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="120" value="" name="vldMsgTxt_A" ezfname="vldMsgTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="120" value="" name="vldMsgTxt_A" ezfname="vldMsgTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="120" value="" name="vldMsgTxt_A" ezfname="vldMsgTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="120" value="" name="vldMsgTxt_A" ezfname="vldMsgTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="120" value="" name="vldMsgTxt_A" ezfname="vldMsgTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="120" value="" name="vldMsgTxt_A" ezfname="vldMsgTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="120" value="" name="vldMsgTxt_A" ezfname="vldMsgTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="120" value="" name="vldMsgTxt_A" ezfname="vldMsgTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="120" value="" name="vldMsgTxt_A" ezfname="vldMsgTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="120" value="" name="vldMsgTxt_A" ezfname="vldMsgTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="120" value="" name="vldMsgTxt_A" ezfname="vldMsgTxt_A" id="vldDescTxt_A#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
														</tr>
													
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<div id="Bottom" style="overflow-x:none; overflow-y:none; width:1100; float:left;">
									<table align="right">
										<tr>
											<td><ezf:inputButton name="FixOnContract" value="Fix on Contract" htmlClass="pBtn10" /></td>
											<td><ezf:inputButton name="SendTo_SuperVisor" value="Send to Supervisor" htmlClass="pBtn10" /></td>
											<td><ezf:inputButton name="Override_Outcome" value="Override Outcome" htmlClass="pBtn10" /></td>
											<td><ezf:inputButton name="ActivateContract" value="Activate Contract" htmlClass="pBtn10" /></td>
										</tr>
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

<script type="text/javascript">
	function clickImg(prntObj, idx) {
		var eventNm = prntObj.all(0).value;
		sendServer(eventNm, idx);
	}
</script>


<%-- **** Task specific area ends here **** --%>
