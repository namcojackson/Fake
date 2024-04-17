<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20200302110725 --%>
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
			<input type="hidden" name="pageID" value="NSBL0400Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Mods Search">

			<center>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td>
							<%-- include S21BusinessProcessTAB --%>
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="Mods Search" class="pTab_ON"><a href="#">Mod Srch</a></li>
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

							<div class="pTab_BODY_In">
								<%-- ## Search Hearder ## --%>
								<table border="0" width="100%" cellpadding="1" cellspacing="5">
									<tr>
										<col width="13%" align="left">
										<col width="16%" align="center">
										<col width="2%" align="center">
										<col width="8%" align="center">
										<col width="2%" align="center">
										<col width="4%" align="center">
										<col width="54%" align="left">
										<td class="stab">Mod Plan ID</td>
										<td>
											<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" width="100%">
												<col width="40%" align="center">
												<col width="30%" align="center">
												<col width="30%" align="center">
												<tr>
													<td class="stab pPro">YYYY</td>
													<td class="stab pPro">MM</td>
													<td class="stab pPRo">DD</td>
												</tr>
												<tr>
<!-- START 2020/03/02 [QC#55899,MOD] -->
<!-- START 2018/07/04 T.Ogura [QC#27065,MOD] -->
 													<td><ezf:inputText name="svcModYr" ezfName="svcModYr" otherAttr=" size=\"4\""/></td>
<!--													<td>
														<select style="width:60;" name="svcModYr" ezfname="svcModYr" ezflist="xxDplyByCtrlItemCd_YY,xxDplyByCtrlItemCdNm_YY,11, ,blank">
															<ezf:skip>
															<option value="1" selected></option>
															<option value="1">2013</option>
															<option value="1">2014</option>
															<option value="1">2015</option>
															<option value="1">2016</option>
															<option value="1">2017</option>
															<option value="1">2018</option>
															<option value="1">2019</option>
															<option value="1">2020</option>
															<option value="1">2021</option>
															<option value="1">2022</option>
															<option value="1">2023</option>
															</ezf:skip>
														</select>
													</td> -->
<!-- END   2018/07/04 T.Ogura [QC#27065,MOD] -->
<!-- END 2020/03/02 [QC#55899,MOD] -->
													<td>
														<ezf:select name="svcModMth" ezfName="svcModMth" ezfBlank="1" ezfCodeName="xxDplyByCtrlItemCd_MM" ezfDispName="xxDplyByCtrlItemCdNm_MM" otherAttr=" style=\"width:45;\""/>
													</td>
													<td>
														<ezf:select name="svcModDay" ezfName="svcModDay" ezfBlank="1" ezfCodeName="xxDplyByCtrlItemCd_DD" ezfDispName="xxDplyByCtrlItemCdNm_DD" otherAttr=" style=\"width:45;\""/>
													</td>
												</tr>
											</table>
										</td>
										<td>-</td>
												<td>
													<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="100%">
														<col width="100%" align="center">
														<tr>
															<td  class="stab pPro">MU</td>
														</tr>
														<tr>
															<td>
																<ezf:select name="svcMnfCd" ezfName="svcMnfCd" ezfBlank="1" ezfCodeName="svcMnfCd_01" ezfDispName="xxDplyByCdNmCnctTxt_01" otherAttr=" style=\"width:75;\""/>
															</td>
														</tr>
													</table>
												</td>
										<td>-</td>
										<td>
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="100%">
												<col width="100%" align="center">
												<tr>
													<td  class="pPro">Seq#</td>
												</tr>
												<tr>
													<td><ezf:inputText name="svcModSqNum" ezfName="svcModSqNum" otherAttr=" size=\"4\""/></td>
												</tr>
											</table>
										</td>
										<td colspan="2" class="stab">
											<table width="75%" cellpadding="1" cellspacing="1">
											<col width="8%" align="center">
											<col width="5%" align="center">
											<col width="5%" align="center">
											<col width="5%" align="center">
											<col width="2%" align="center">
											<col width="5%" align="center">
											<col width="2%" align="center">
											<col width="5%" align="center">
												<td border="0" class="stab">Example</td>
												<td border="1" class="stab pPro">YYYY</td>
												<td border="1" class="stab pPro">MM</td>
												<td border="1" class="stab pPro">DD</td>
												<td class="stab">-</td>
												<td border="1" class="stab pPro">MU</td>
												<td class="stab">-</td>
												<td border="1" class="stab pPro">Seq#</td>
											</table>
											or just Year, or Year & month, or  Year & MU, or Year, Month & MU, etc., or (leave blank)
										</td>
									</tr>
									<tr>
										<td class="stab">Mod Plan Desc(*)</td>
										<td align="left"><ezf:inputText name="svcModNm" ezfName="svcModNm" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="MdseCodePopup" >Item Code(*)</ezf:anchor></td>
										<td align="left"><ezf:inputText name="mdseCd" ezfName="mdseCd" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab">Manufacture Mod#(*)</td>
										<td align="left"><ezf:inputText name="svcMnfModNum" ezfName="svcMnfModNum" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Serial" >Serial#</ezf:anchor></td>
										<td align="left"><ezf:inputText name="serNum" ezfName="serNum" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td></td>
										<td></td>
										<td colspan="4" align="left">
											<table width="95%" cellpadding="1" cellspacing="1">
											<col width="15%" align="center">
											<col width="6%" align="center">
											<col width="11%" align="center">
											<col width="8%" align="center">
											<col width="15%" align="center">
											<col width="28%" align="right">
												<td class="stab">Example if MU =V:40</td>
												<td border="1" class="stab pPro">40</td>
												<td class="stab">or if MU=P:</td>
												<td border="1" class="stab pPro">15M1234</td>
												<td class="stab">or ( Leave blank)</td>
												<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" /></td>
											</table>
										</td>
									</tr>
								</table>

								<%-- ## Search Detail ## --%>
								<table border="0" cellpadding="1" cellspacing="1" align="center" width="99%">
								<hr width="98%" align="left">
									<col width="10%" align="left">
									<col width="50%" align="center">
									<col width="40%" align="right">
									<tr>
										<td><ezf:inputButton name="New" value="New" htmlClass="pBtn8" /></td>
										<td></td>
										<td>
											<div align="right">
												<ezf:skip>
													<table border="0" cellpadding="1" cellspacing="0">
														<col >
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="10">
														<col width="0">
														<col width="1">
														<col width="0">
														<tr>
															<td class="stab">Showing</td>
															<td class="pOut pPro">1</td>
															<td class="stab">to</td>
															<td class="pOut pPro">20</td>
															<td class="stab">of</td>
															<td class="pOut pPro">20</td>
															<td>&nbsp;</td>
															<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
															<td></td>
															<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
															<td width="15"></td>
														</tr>
													</table>
												</ezf:skip>
												<table width="980" border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td align="right">
															<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																<jsp:param name="TableNm"     value="A" />
																<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
																<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
																<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
															</jsp:include>
														</td>
														<td width="15"></td>
													</tr>
												</table>
											</div>
										</td>
									</tr>
								</table>

<%-- #################### DETAIL #################### --%>
								<table border="0" cellpadding="0" cellspacing="0" align="center" width="99%">
									<tr>
										<td valign="top">

								<%-- ########## TOP ########## --%>
											<div id="top" style="overflow-y:hidden; height:; overflow-x:hidden; width:1080; text-align:center;">
												<table border="1" cellpadding="1" cellspacing="0" align="left">
													<col width="100" align="center">
													<col width="100" align="center">
													<col width="150" align="center">
													<col width="350" align="center">
													<col width="350" align="center">

													<tr height="37">
														<td class="pClothBs"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxDplyByCdNmCnctTxt_A')">MU</a><img id="sortIMG.xxDplyByCdNmCnctTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcModPlnId_A')">Mod Plan ID</a><img id="sortIMG.svcModPlnId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcModNm_A')">Mod Plan Description</a><img id="sortIMG.svcModNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcModCmntTxt_A')">Comment</a><img id="sortIMG.svcModCmntTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													</tr>
												</table>
											</div>

								<%-- ########## DETAIL ########## --%>
											<div id="Detail" style="overflow-y:scroll; height:325; overflow-x:hidden; width:1097;">
												<table id="A" border="1" cellpadding="1" cellspacing="0" align="left">
													<col width="100" align="left">
													<col width="100" align="left">
													<col width="150" align="left">
													<col width="350" align="left">
													<col width="350" align="left">
													
													<ezf:row ezfHyo="A">
														<tr height="28">
															<td align="center">
																<ezf:inputButton name="Detail" value="Detail" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn5" otherAttr=" id=\"Detail\""/>
															</td>
															<td><ezf:label name="xxDplyByCdNmCnctTxt_A" ezfName="xxDplyByCdNmCnctTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="svcModPlnId_A" ezfName="svcModPlnId_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="svcModNm_A" ezfName="svcModNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="svcModCmntTxt_A" ezfName="svcModCmntTxt_A" value="123456789012345678901234567890123456789012345678901234567890123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"49\""/></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
															</td>
														</tr>
													</ezf:row>
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


<%-- **** Task specific area ends here **** --%>
