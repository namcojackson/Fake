<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160412133513 --%>
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
			<input type="hidden" name="pageID" value="NLCL0610Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="PI Inquiry">

			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
											<tr title="PI Inquiry">
												<td width="107px" align="center" class="same">PI Inquiry</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							</ezf:skip>

							<div class="pTab_BODY">
								<%-- ######################################## Search Criteria - [START] ################################################## --%>
                        	    <table style="margin-top: 5px; margin-left: 7px;" border="0">
									<tr>
										<col width="110px"  align="left">
										<col width="80px"  align="left">
										<col width="30px"  align="center">
										<col width="120px" align="left">
									</tr>
									<tr height="20">
										<%-- ---------- Warehouse ---------- --%>
										<td class="stab">
											<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OnClick_WhInfo" >Warehouse (*)</ezf:anchor>
										</td>
										<td class="stab">
											<ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="----+----1----+----2" otherAttr=" size=\"16\" maxlength=\"20\" ezftoupper=\"\""/>
										</td>
										<td>
											<ezf:inputButton name="OnClick_SetWhNm" ezfName="WhNm" value=">>" />
										</td>
										<td class="stab">
											<ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="----+----1----+----2----+----3" otherAttr=" size=\"20\" maxlength=\"30\""/>
										</td>
									</tr>
									<tr height="20">
										<%-- ---------- Schedule Date ---------- --%>
										<td class="stab">Schedule Date</td>
										<td class="stab" colspan="3">
											<ezf:inputText name="physInvtyDt_F" ezfName="physInvtyDt_F" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'physInvtyDt_F', 4 );">
											&nbsp;-&nbsp;
											<ezf:inputText name="physInvtyDt_T" ezfName="physInvtyDt_T" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'physInvtyDt_T', 4 );">
										</td>
									</tr>
									<tr height="20">
										<%-- ---------- Physical Inventory ---------- --%>
										<td class="stab">Physical Inventory (*)</td>
										<td colspan="3">
											<ezf:inputText name="physInvtyCtrlNm" ezfName="physInvtyCtrlNm" value="----+----1----+----2----+----3----+----4----+----5----+----6----+----7----+----8----+----9----+----0" otherAttr=" maxlength=\"100\" style=\"width:295px;\" ezftoupper=\"\""/>
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px; margin-top:0px;">
									<col width="150px"  align="left">
									<col width="656px"  align="left"><%-- The horizontal space between Fileters Parts and Paging Parts --%>
									<col width="300px"  align="left">
									<tr>
										<td></td>
										<%-- The horizontal space between Fileters Parts and Paging Parts --%>
										<td>&nbsp;</td>
										<td>
											<div align="right">
												<ezf:inputButton name="OnClick_Search" value="Search" htmlClass="pBtn5" />
											</div>
										</td>
									</tr>
								</table>
								<hr>

								<%-- ######################################## DETAIL ######################################## --%>
								<div align="left">
									<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px; margin-top:0px;">
										<col width="150px"  align="left">
										<col width="656px"  align="left"><%-- The horizontal space between Fileters Parts and Paging Parts --%>
										<col width="300px"  align="left">
										<tr>
											<td></td>
											<%-- The horizontal space between Fileters Parts and Paging Parts --%>
											<td>&nbsp;</td>
											<td>
												<div align="right">
													<ezf:skip>
													<table border="0" cellpadding="1" cellspacing="0">
														<tr>
															<col width="40" align="right">
															<col width="16" align="center">
															<col width="40" align="right">
															<col width="16" align="center">
															<col width="40" align="right">
															<col width="10">
															<col width="0">
															<col width="1">
															<col width="0">
														</tr>
														<tr>
															<td class="stab">Showing</td>
															<td class="pOut">1</td>
															<td class="stab">to</td>
															<td class="pOut">20</td>
															<td class="stab">of</td>
															<td class="pOut">200</td>
															<td>&nbsp;</td>
															<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
															<td></td>
															<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
															<td></td>
														</tr>
													</table>
													</ezf:skip>
				                                    <table cellspacing="0" cellpadding="0" border="0" style="float: right;">
				                                        <tbody>
				                                            <tr align="right">
				                                                <td>
				                                                    <jsp:include page="../tablePagenation/S21TablePagenation.jsp">
				                                                        <jsp:param name="beanId"          value='<%= request.getParameter( "beanId" ) %>' />
				                                                        <jsp:param name="TableNm"         value="A"                 />
				                                                        <jsp:param name="ShowingFrom"     value="xxPageShowFromNum" />
				                                                        <jsp:param name="ShowingTo"       value="xxPageShowToNum"   />
				                                                        <jsp:param name="ShowingOf"       value="xxPageShowOfNum"   />
				                                                    </jsp:include>
				                                                </td>
				                                            </tr>
				                                        </tbody>
				                                    </table>
												</div>
											</td>
										</tr>
									</table>
								</div>
								<table border="0" cellpadding="0" cellspacing="0" width="100%">
									<tr>
										<td align="right" valign="top">
											<%-- ******************************* --%>
											<%-- *** Left Table Area(Header) *** --%>
											<%-- ******************************* --%>
											<table style="table-layout:fixed; margin-left: 2px;" border="1" cellpadding="1" cellspacing="0">
												<col align="center" width="24">
												<col align="center" width="80">
												<col align="center" width="220">
												<tr height="30">
													<td class="pClothBs">&nbsp;</td>
													<td class="pClothBs">WH Code</td>
													<td class="pClothBs">WH</td>
												</tr>
											</table>
											<%-- ******************************* --%>
											<%-- *** Left Table Area(Detail) *** --%>
											<%-- ******************************* --%>
											<div id="LeftTBL" style="table-layout:fixed;overflow-y:hidden; height:343; overflow-x:hidden; margin-left: 2px;"
												onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Left">
													<col align="center" width="24">
													<col align="left" width="80">
													<col align="left" width="220">
													<ezf:row ezfHyo="A">
														<tr height="28" id="id_row{EZF_ROW_NUMBER}">
															<td class="stab"><ezf:inputRadio name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"radio#{EZF_ROW_NUMBER}\""/></td>
															<td>
																<ezf:label name="rtlWhCd_A" ezfName="rtlWhCd_A" ezfHyo="A" ezfArrayNo="0" />
															</td>
															<td>
																<ezf:label name="rtlWhNm_A" ezfName="rtlWhNm_A" ezfHyo="A" ezfArrayNo="0" />
															</td>
														</tr>
													</ezf:row>
												</table>
											</div>
										</td>
										<td valign="top">
											<%-- ******************************** --%>
											<%-- *** Right Table Area(Header) *** --%>
											<%-- ******************************** --%>
											<div id="topRightTBL" style="overflow-y:none; overflow-x:hidden; width:780px;">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" >
													<col align="center" width="80"><%-- SWH Code --%>
													<col align="center" width="220"><%-- SWH --%>
													<col align="center" width="100"><%-- Schedule Date --%>
													<col align="center" width="260"><%-- Physical Inventory --%>
													<col align="center" width="80"><%-- Status --%>
													<col align="center" width="150"><%-- Physical Inventory Date --%>
													<col align="center" width="370"><%-- Description --%>
													<tr height="30">
														<td class="pClothBs">SWH Code</td>
														<td class="pClothBs">SWH</td>
														<td class="pClothBs">Schedule Date</td>
														<td class="pClothBs">Physical Inventory</td>
														<td class="pClothBs">Status</td>
														<td class="pClothBs">Physical Inventory Date</td>
														<td class="pClothBs">Description</td>
													</tr>
												</table>
											</div>
											<%-- ******************************** --%>
											<%-- *** Right Table Area(Detail) *** --%>
											<%-- ******************************** --%>
											<div id="RightTBL" style="overflow-y:scroll; height:360; overflow-x:scroll; width:797px;" 
												onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'topRightTBL' ) );">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0"  id="A_Right">
													<col align="left" width="80"><%-- SWH Code --%>
													<col align="left" width="220"><%-- SWH --%>
													<col align="left" width="100"><%-- Schedule Date --%>
													<col align="left" width="260"><%-- Physical Inventory --%>
													<col align="left" width="80"><%-- Status --%>
													<col align="left" width="150"><%-- Physical Inventory Date --%>
													<col align="left" width="370"><%-- Description --%>
													<ezf:row ezfHyo="A">
													<tr height="28">
														<%-- SWH Code --%>
														<td>
															<ezf:label name="rtlSwhCd_A" ezfName="rtlSwhCd_A" ezfHyo="A" ezfArrayNo="0" />
														</td>
														<%-- SWH --%>
														<td>
															<ezf:label name="rtlSwhNm_A" ezfName="rtlSwhNm_A" ezfHyo="A" ezfArrayNo="0" />
														</td>
														<%-- Schedule Date --%>
														<td>
															<ezf:label name="physInvtyDt_A" ezfName="physInvtyDt_A" ezfHyo="A" ezfArrayNo="0" />
														</td>
														<%-- Physical Inventory --%>
														<td>
															<ezf:inputText name="physInvtyCtrlNm_A" ezfName="physInvtyCtrlNm_A" value="----+----1----+----2----+----3----+----4----+----5----+----6----+----7----+----8----+----9----+----0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"35\" maxlength=\"100\""/>
														</td>
														<%-- Status --%>
														<td>
															<ezf:label name="physInvtyStsNm_A" ezfName="physInvtyStsNm_A" ezfHyo="A" ezfArrayNo="0" />
														</td>
														<%-- Physical Inventory Date --%>
														<td>
															<ezf:label name="xxScrItem20Txt_A" ezfName="xxScrItem20Txt_A" ezfHyo="A" ezfArrayNo="0" />
														</td>
														<%-- Description --%>
														<td>
															<ezf:inputText name="physInvtyCtrlDescNm_A" ezfName="physInvtyCtrlDescNm_A" value="----+----1----+----2----+----3----+----4----+----5----+----6----+----7----+----8----+----9----+----0----+----1----+----2----+----3----+----4----+----5----+----6----+----7----+----8----+----9----+----0----+----1----+----2----+----3----+----4----+----5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"250\""/>
														</td>
													</tr>
													</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>

								<%-- ######################################## FOOTER ######################################## --%>
								<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px; margin-top:0px;">
									<col width="150px"  align="left">
									<col width="656px"  align="left"><%-- The horizontal space between Fileters Parts and Paging Parts --%>
									<col width="300px"  align="left">
									<tr height="3">
										<td colspan="3">&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<%-- The horizontal space between Fileters Parts and Paging Parts --%>
										<td>&nbsp;</td>
										<td>
											<div align="right">
												<ezf:inputButton name="OnClick_Cancel" value="Cancel" htmlClass="pBtn6" />
												<ezf:inputButton name="OnClick_Edit" value="Edit" htmlClass="pBtn6" />
											</div>
										</td>
									</tr>
									<tr height="3">
										<td colspan="3">&nbsp;</td>
									</tr>
								</table>

							</div>

						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
