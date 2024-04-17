<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171220162153 --%>
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
			<input type="hidden" name="pageID" value="ZZIL0040Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="API Inbound Queuing Monitor">


<center>
	<table border="0" cellpadding="1" cellspacing="0" width="100%">
		<tr>
			<td>
				<!-- include S21BusinessProcessTAB -->
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
<!-- **************************************** HEADER **************************************** -->
					<table border="0" cellpadding="1" cellspacing="0" align="center">
						<tr>
							<td>
								<table border="0" cellpadding="1" cellspacing="0" width="1080">
									<col width="110">
									<col width="261">
									<col width="92">
									<col width="110">
									<col width="380">
									<col>
									<tr height="37">
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectInterfaceId" >Interface ID</ezf:anchor></td>
										<td><ezf:inputText name="intfcId_H" ezfName="intfcId_H" value="123456789012345678901234567890" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td class="stab">Processed Flag</td>
										<td>
											<ezf:select name="intfcRqstStsTxt_HS" ezfName="intfcRqstStsTxt_HS" ezfCodeName="intfcRqstStsTxt_HC" ezfDispName="intfcRqstStsTxt_HD" otherAttr=" style=\"width: 99px\""/>
										</td>
										<td><div style="border: 1px solid #cccccc;padding:1px;"><label>* FAILED1: Error to invoke Business API<br>* FAILED2: Business Error occurred from Business API</label></div></td>
										<td></td>
									</tr>
								</table>
								<table border="0" cellpadding="1" cellspacing="0" width="1080">
									<col width="110">
									<col width="111">
									<col width="5">
									<col width="30">
									<col width="75">
									<col width="30">
									<col width="92">
									<col width="111">
									<col width="5">
									<col width="30">
									<col>
									<tr>
										<td class="stab">Started Date From</td>
										<td>
											<ezf:inputText name="xxFromDt_SF" ezfName="xxFromDt_SF" value="05/01/2008" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt_SF', 4);" >
										</td>
										<td>&nbsp;</td>
										<td class="stab">Time</td>
										<td>
											<ezf:select name="xxHrs_S1" ezfName="xxHrs_S1" ezfBlank="1" ezfCodeName="xxHrs_SF" ezfDispName="xxHrsMn_SF" />
										</td>
										<td>&nbsp;</td>
										<td class="stab">Started Date To</td>
										<td>
											<ezf:inputText name="xxToDt_ST" ezfName="xxToDt_ST" value="05/01/2008" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxToDt_ST', 4);" >
										</td>
										<td>&nbsp;</td>
										<td class="stab">Time</td>
										<td>
											<ezf:select name="xxHrs_S2" ezfName="xxHrs_S2" ezfBlank="1" ezfCodeName="xxHrs_ST" ezfDispName="xxHrsMn_ST" />
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="1" cellspacing="0" width="1080">
									<col width="110">
									<col width="111">
									<col width="5">
									<col width="30">
									<col width="75">
									<col width="30">
									<col width="92">
									<col width="111">
									<col width="5">
									<col width="30">
									<col width="75">
									<col width="30">
									<col>

									<tr>
										<td class="stab">End Date From</td>
										<td>
											<ezf:inputText name="xxFromDt_EF" ezfName="xxFromDt_EF" value="05/01/2008" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt_EF', 4);" >
										</td>
										<td>&nbsp;</td>
										<td class="stab">Time</td>
										<td>
											<ezf:select name="xxHrs_E1" ezfName="xxHrs_E1" ezfBlank="1" ezfCodeName="xxHrs_EF" ezfDispName="xxHrsMn_EF" />
										</td>
										<td>&nbsp;</td>
										<td class="stab">End Date To</td>
										<td>
											<ezf:inputText name="xxToDt_ET" ezfName="xxToDt_ET" value="05/01/2008" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxToDt_ET', 4);" >
										</td>
										<td>&nbsp;</td>
										<td class="stab">Time</td>
										<td>
											<ezf:select name="xxHrs_E2" ezfName="xxHrs_E2" ezfBlank="1" ezfCodeName="xxHrs_ET" ezfDispName="xxHrsMn_ET" />
										</td>
										<td>&nbsp;</td>
										<td align="right">
											<ezf:inputButton name="Search" value="Search" htmlClass="cBtn" otherAttr=" style=\"width: 100px\""/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

<!-- **************************************** HEADER **************************************** -->
					<hr width="99%">
<!-- **************************************** DETAIL **************************************** -->
					<table border="0" cellpadding="0" cellspacing="0" align="center">
						<tr>
							<td valign="top">
							<!-- ********* PAGENATION ********* -->
								<table width="100%">
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
							<!-- ********* PAGENATION ********* -->
							<!-- ********* DATA ********* -->
								<table width="100%">
									<tr>
										<td>
											<table width="100%" border="0" cellpadding="0" cellspacing="0">
												<col valign="top" align="right">
												<col valign="top" align="left">
												<tr>
												<!-- ********* Left TBL : START ********* -->
													<td>
													<!-- ##### Left TBL - Top -->
														<table border="1" cellpadding="1" cellspacing="0" width="263">
															<col width="72" align="left">
															<col width="72" align="center">
															<col width="101" align="center">
															<tr height="28">
																<td class="pClothBs" align="center">Detail</td>
																<td class="pClothBs" align="center">Status</td>
																<td class="pClothBs" align="center">Change Status</td>
															</tr>
														</table>
														<div id="LeftTBL" style="overflow-y:hidden;overflow-x:hidden;width:263px;height:390px;" onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
															<table border="1" cellpadding="1" cellspacing="0" id="A_LeftTBL" width="263">
																<col width="72" align="center">
																<col width="72" align="center">
																<col width="101" align="center">
																<tbody>
																<ezf:row ezfHyo="A">
																	<tr height="28">
																		<td align="center"><ezf:inputButton name="View" value="View" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" /></td>
																		<td><ezf:label name="intfcRqstStsTxt_A" ezfName="intfcRqstStsTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td>
																			<ezf:select name="intfcRqstStsTxt_AS" ezfName="intfcRqstStsTxt_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="intfcRqstStsTxt_AC" ezfDispName="intfcRqstStsTxt_AD" ezfCodeDispHyo="A" otherAttr=" style=\"width: 99px\""/>
																		</td>
																	</tr>
																</ezf:row>
																<ezf:skip>
																	<tr height="28">
																		<td align="center"><input type="button" class="pBtn6" name="View" value="View"></td>
																		<td><label ezfOut>COMPLETED</label></td>
																		<td>
																			<select style="width: 99px" name="intfcRqstStsTxt_BA" ezfname="intfcRqstStsTxt_BA" ezfhyo="A" ezflist="intfcRqstStsTxt_BC,intfcRqstStsTxt_BD,99,A,blank">
																				<option selected>FAILED1</option>
																				<option>WAITING</option>
																			</select>
																		</td>
																	</tr>
																	<tr height="28">
																		<td align="center"><input type="button" class="pBtn6" name="View" value="View"></td>
																		<td><label ezfOut>COMPLETED</label></td>
																		<td>
																			<select style="width: 99px" name="intfcRqstStsTxt_BA" ezfname="intfcRqstStsTxt_BA" ezfhyo="A" ezflist="intfcRqstStsTxt_BC,intfcRqstStsTxt_BD,99,A,blank">
																				<option selected>FAILED1</option>
																				<option>WAITING</option>
																			</select>
																		</td>
																	</tr>
																	<tr height="28">
																		<td align="center"><input type="button" class="pBtn6" name="View" value="View"></td>
																		<td><label ezfOut>COMPLETED</label></td>
																		<td>
																			<select style="width: 99px" name="intfcRqstStsTxt_BA" ezfname="intfcRqstStsTxt_BA" ezfhyo="A" ezflist="intfcRqstStsTxt_BC,intfcRqstStsTxt_BD,99,A,blank">
																				<option selected>FAILED1</option>
																				<option>WAITING</option>
																			</select>
																		</td>
																	</tr>
																	<tr height="28">
																		<td align="center"><input type="button" class="pBtn6" name="View" value="View"></td>
																		<td><label ezfOut>COMPLETED</label></td>
																		<td>
																			<select style="width: 99px" name="intfcRqstStsTxt_BA" ezfname="intfcRqstStsTxt_BA" ezfhyo="A" ezflist="intfcRqstStsTxt_BC,intfcRqstStsTxt_BD,99,A,blank">
																				<option selected>FAILED1</option>
																				<option>WAITING</option>
																			</select>
																		</td>
																	</tr>
																	<tr height="28">
																		<td align="center"><input type="button" class="pBtn6" name="View" value="View"></td>
																		<td><label ezfOut>COMPLETED</label></td>
																		<td>
																			<select style="width: 99px" name="intfcRqstStsTxt_BA" ezfname="intfcRqstStsTxt_BA" ezfhyo="A" ezflist="intfcRqstStsTxt_BC,intfcRqstStsTxt_BD,99,A,blank">
																				<option selected>FAILED1</option>
																				<option>WAITING</option>
																			</select>
																		</td>
																	</tr>
																	<tr height="28">
																		<td align="center"><input type="button" class="pBtn6" name="View" value="View"></td>
																		<td><label ezfOut>COMPLETED</label></td>
																		<td>
																			<select style="width: 99px" name="intfcRqstStsTxt_BA" ezfname="intfcRqstStsTxt_BA" ezfhyo="A" ezflist="intfcRqstStsTxt_BC,intfcRqstStsTxt_BD,99,A,blank">
																				<option selected>FAILED1</option>
																				<option>WAITING</option>
																			</select>
																		</td>
																	</tr>
																	<tr height="28">
																		<td align="center"><input type="button" class="pBtn6" name="View" value="View"></td>
																		<td><label ezfOut>COMPLETED</label></td>
																		<td>
																			<select style="width: 99px" name="intfcRqstStsTxt_BA" ezfname="intfcRqstStsTxt_BA" ezfhyo="A" ezflist="intfcRqstStsTxt_BC,intfcRqstStsTxt_BD,99,A,blank">
																				<option selected>FAILED1</option>
																				<option>WAITING</option>
																			</select>
																		</td>
																	</tr>
																</ezf:skip>
																</tbody>
															</table>
														</div>
													</td>
													<!-- ********* Left TBL : E N D ********* -->
													<!-- ********* Right TBL : START ********* -->
													<td>
														<div id="RightTBL_Top" style="overflow-y:hidden; height:; overflow-x:hidden; width:840px;" onScroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ) );">
															<table align="center" cellpadding="1" cellspacing="0" border="1" width="1400" style="table-layout:fixed">
																<col width="184" align="center">
																<col width="184" align="center">
																<col width="228" align="center">
																<col width="144" align="center">
																<col width="144" align="center">
																<col width="480" align="center">
																<tr align="center" height="28">
																	<td class="pClothBs" align="center">Start Time</td>
																	<td class="pClothBs" align="center">End Time</td>
																	<td class="pClothBs" align="center">Request Primary Key</td>
																	<td class="pClothBs" align="center">Interface ID</td>
																	<td class="pClothBs" align="center">Business API ID</td>
																	<td class="pClothBs" align="center">JMS Message ID</td>
																</tr>
															</table>
														</div>
														<div id="RightTBL" style="word-break:break-all; overflow-y:scroll; overflow-x:scroll; height:407px;width:857px" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ) );">
															<table cellpadding="1" cellspacing="0" border="1" width="1400" id="A_RightTBL" style="table-layout:fixed">
																<col width="184" align="center">
																<col width="184" align="center">
																<col width="228" align="right">
																<col width="144" align="left">
																<col width="144" align="left">
																<col width="480" align="left">
																<ezf:row ezfHyo="A">
																	<tr height="28">
																		<td><ezf:label name="xxDtTm_AS" ezfName="xxDtTm_AS" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:label name="xxDtTm_AE" ezfName="xxDtTm_AE" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:label name="mdbInbdIntfcRqstPk_A" ezfName="mdbInbdIntfcRqstPk_A" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:label name="intfcId_A" ezfName="intfcId_A" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:label name="bizApiId_A" ezfName="bizApiId_A" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:label name="rqstJmsMsgId_A" ezfName="rqstJmsMsgId_A" ezfHyo="A" ezfArrayNo="0" /></td>
																	</tr>
																</ezf:row>
																<ezf:skip>
																	<tr height="28">
																		<td><label ezfOut ezfhyo="A">08/09/2010 09:00:03:171</label></td>
																		<td><label ezfOut ezfhyo="A">08/09/2010 09:00:03:171</label></td>
																		<td><label ezfOut ezfhyo="A">22</label></td>
																		<td><label ezfOut ezfhyo="A">ZZZI0010</label></td>
																		<td><label ezfOut ezfhyo="A">ZZZC015201</label></td>
																		<td><label ezfOut ezfhyo="A">ID:414d51204949425432414232322020207f290c5ab5be3922</label></td>
																	</tr>
																	<tr height="28">
																		<td><label ezfOut ezfhyo="A">08/09/2010 09:00:03:171</label></td>
																		<td><label ezfOut ezfhyo="A">08/09/2010 09:00:03:171</label></td>
																		<td><label ezfOut ezfhyo="A">1111111111222222222233333333</label></td>
																		<td><label ezfOut ezfhyo="A">AAAAAAAAAABBBBBBBBBBCCCCCCCCCC</label></td>
																		<td><label ezfOut ezfhyo="A">AAAAAAAAAABB</label></td>
																		<td><label ezfOut ezfhyo="A">AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDDDDDDDDDDEEEEEEEEEEFFFFFFFFFFGGGGGGGGGG</label></td>
																	</tr>
																	<tr height="28">
																		<td><label ezfOut ezfhyo="A">08/09/2010 09:00:03:171</label></td>
																		<td><label ezfOut ezfhyo="A">08/09/2010 09:00:03:171</label></td>
																		<td><label ezfOut ezfhyo="A">1111111111222222222233333333</label></td>
																		<td><label ezfOut ezfhyo="A">AAAAAAAAAABBBBBBBBBBCCCCCCCCCC</label></td>
																		<td><label ezfOut ezfhyo="A">AAAAAAAAAABB</label></td>
																		<td><label ezfOut ezfhyo="A">AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDDDDDDDDDDEEEEEEEEEEFFFFFFFFFFGGGGGGGGGG</label></td>
																	</tr>
																	<tr height="28">
																		<td><label ezfOut ezfhyo="A">08/09/2010 09:00:03:171</label></td>
																		<td><label ezfOut ezfhyo="A">08/09/2010 09:00:03:171</label></td>
																		<td><label ezfOut ezfhyo="A">1111111111222222222233333333</label></td>
																		<td><label ezfOut ezfhyo="A">AAAAAAAAAABBBBBBBBBBCCCCCCCCCC</label></td>
																		<td><label ezfOut ezfhyo="A">AAAAAAAAAABB</label></td>
																		<td><label ezfOut ezfhyo="A">AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDDDDDDDDDDEEEEEEEEEEFFFFFFFFFFGGGGGGGGGG</label></td>
																	</tr>
																	<tr height="28">
																		<td><label ezfOut ezfhyo="A">08/09/2010 09:00:03:171</label></td>
																		<td><label ezfOut ezfhyo="A">08/09/2010 09:00:03:171</label></td>
																		<td><label ezfOut ezfhyo="A">1111111111222222222233333333</label></td>
																		<td><label ezfOut ezfhyo="A">AAAAAAAAAABBBBBBBBBBCCCCCCCCCC</label></td>
																		<td><label ezfOut ezfhyo="A">AAAAAAAAAABB</label></td>
																		<td><label ezfOut ezfhyo="A">AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDDDDDDDDDDEEEEEEEEEEFFFFFFFFFFGGGGGGGGGG</label></td>
																	</tr>
																	<tr height="28">
																		<td><label ezfOut ezfhyo="A">08/09/2010 09:00:03:171</label></td>
																		<td><label ezfOut ezfhyo="A">08/09/2010 09:00:03:171</label></td>
																		<td><label ezfOut ezfhyo="A">1111111111222222222233333333</label></td>
																		<td><label ezfOut ezfhyo="A">AAAAAAAAAABBBBBBBBBBCCCCCCCCCC</label></td>
																		<td><label ezfOut ezfhyo="A">AAAAAAAAAABB</label></td>
																		<td><label ezfOut ezfhyo="A">AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDDDDDDDDDDEEEEEEEEEEFFFFFFFFFFGGGGGGGGGG</label></td>
																	</tr>
																	<tr height="28">
																		<td><label ezfOut ezfhyo="A">08/09/2010 09:00:03:171</label></td>
																		<td><label ezfOut ezfhyo="A">08/09/2010 09:00:03:171</label></td>
																		<td><label ezfOut ezfhyo="A">1111111111222222222233333333</label></td>
																		<td><label ezfOut ezfhyo="A">AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDDDDDDDDDDEEEEE</label></td>
																		<td><label ezfOut ezfhyo="A">AAAAAAAAAABB</label></td>
																		<td><label ezfOut ezfhyo="A">AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDDDDDDDDDDEEEEEEEEEEFFFFFFFFFFGGGGGGGGGG</label></td>
																	</tr>
																</ezf:skip>
															</table>
														</div>
													</td>
													<!-- ********* Right TBL : END ********* -->
												</tr>
											</table>
										</td>
									</tr>
								</table>
							<!-- ********* DATA ********* -->
							</td>
						</tr>
					</table>
<!-- **************************************** DETAIL **************************************** -->
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
