<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180228113346 --%>
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
			<input type="hidden" name="pageID" value="NMAL0250Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="BOM Text Entry">

			<center>
				<!-- HEADER -->
				<table style="width: 790px; MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
					<tr>
						<td style="text-align: left;">
							<table border="0" cellpadding="0" cellspacing="0" style="margin-top:10px;">
								<col width="160px">
								<col width="160px">
								<col width="60px">
								<col width="100px">
							<tr>
								<td>BOM Item#</a></td>
								<td>
									<ezf:inputText name="mdseCd" ezfName="mdseCd" value="1234567890123456" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
								</td>
							</tr>
							<tr>
								<td>BOM Item Description</td>
								<td>
									<ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="123456789012345XXXXXXXXXX12345" otherAttr=" size=\"60\" maxlength=\"250\" ezftoupper=\"\""/>
								</td>
							</tr>
							</table>
						</td>
					</tr>
				</table>
				<hr>
				<!-- DETAIL -->
				<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
					<tr>
						<td>
							<table border="1" cellpadding="0" cellspacing="0" style="width: 480px; margin-top:10px;">
								<col width="40px">
								<col width="440px">
								<tr style="text-align: center; height: 28px;">
									<td class="pClothBs">#</td>
									<td class="pClothBs">Comment</td>
								</tr>
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">1</td>
									<td style="text-align: center;">
										<ezf:inputText name="cmpsnMsgTxt_A0" ezfName="cmpsnMsgTxt_A0" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgPk_A0" ezfName="cmpsnMsgPk_A0" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgSegId_A0" ezfName="cmpsnMsgSegId_A0" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 2 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">2</td>
									<td style="text-align: center;">
										<ezf:inputText name="cmpsnMsgTxt_A1" ezfName="cmpsnMsgTxt_A1" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgPk_A1" ezfName="cmpsnMsgPk_A1" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgSegId_A1" ezfName="cmpsnMsgSegId_A1" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 3 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">3</td>
									<td style="text-align: center;">
										<ezf:inputText name="cmpsnMsgTxt_A2" ezfName="cmpsnMsgTxt_A2" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgPk_A2" ezfName="cmpsnMsgPk_A2" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgSegId_A2" ezfName="cmpsnMsgSegId_A2" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 4 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">4</td>
									<td style="text-align: center;">
										<ezf:inputText name="cmpsnMsgTxt_A3" ezfName="cmpsnMsgTxt_A3" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgPk_A3" ezfName="cmpsnMsgPk_A3" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgSegId_A3" ezfName="cmpsnMsgSegId_A3" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 5 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">5</td>
									<td style="text-align: center;">
										<ezf:inputText name="cmpsnMsgTxt_A4" ezfName="cmpsnMsgTxt_A4" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgPk_A4" ezfName="cmpsnMsgPk_A4" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgSegId_A4" ezfName="cmpsnMsgSegId_A4" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 6 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">6</td>
									<td style="text-align: center;">
										<ezf:inputText name="cmpsnMsgTxt_A5" ezfName="cmpsnMsgTxt_A5" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgPk_A5" ezfName="cmpsnMsgPk_A5" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgSegId_A5" ezfName="cmpsnMsgSegId_A5" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 7 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">7</td>
									<td style="text-align: center;">
										<ezf:inputText name="cmpsnMsgTxt_A6" ezfName="cmpsnMsgTxt_A6" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgPk_A6" ezfName="cmpsnMsgPk_A6" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgSegId_A6" ezfName="cmpsnMsgSegId_A6" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 8 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">8</td>
									<td style="text-align: center;">
										<ezf:inputText name="cmpsnMsgTxt_A7" ezfName="cmpsnMsgTxt_A7" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgPk_A7" ezfName="cmpsnMsgPk_A7" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgSegId_A7" ezfName="cmpsnMsgSegId_A7" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 9 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">9</td>
									<td style="text-align: center;">
										<ezf:inputText name="cmpsnMsgTxt_A8" ezfName="cmpsnMsgTxt_A8" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgPk_A8" ezfName="cmpsnMsgPk_A8" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgSegId_A8" ezfName="cmpsnMsgSegId_A8" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 10 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">10</td>
									<td style="text-align: center;">
										<ezf:inputText name="cmpsnMsgTxt_A9" ezfName="cmpsnMsgTxt_A9" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgPk_A9" ezfName="cmpsnMsgPk_A9" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgSegId_A9" ezfName="cmpsnMsgSegId_A9" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 11 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">11</td>
									<td style="text-align: center;">
										<ezf:inputText name="cmpsnMsgTxt_AA" ezfName="cmpsnMsgTxt_AA" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgPk_AA" ezfName="cmpsnMsgPk_AA" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgSegId_AA" ezfName="cmpsnMsgSegId_AA" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 12 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">12</td>
									<td style="text-align: center;">
										<ezf:inputText name="cmpsnMsgTxt_AB" ezfName="cmpsnMsgTxt_AB" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgPk_AB" ezfName="cmpsnMsgPk_AB" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgSegId_AB" ezfName="cmpsnMsgSegId_AB" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 13 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">13</td>
									<td style="text-align: center;">
										<ezf:inputText name="cmpsnMsgTxt_AC" ezfName="cmpsnMsgTxt_AC" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgPk_AC" ezfName="cmpsnMsgPk_AC" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgSegId_AC" ezfName="cmpsnMsgSegId_AC" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 14 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">14</td>
									<td style="text-align: center;">
										<ezf:inputText name="cmpsnMsgTxt_AD" ezfName="cmpsnMsgTxt_AD" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgPk_AD" ezfName="cmpsnMsgPk_AD" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgSegId_AD" ezfName="cmpsnMsgSegId_AD" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 15 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">15</td>
									<td style="text-align: center;">
										<ezf:inputText name="cmpsnMsgTxt_AE" ezfName="cmpsnMsgTxt_AE" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgPk_AE" ezfName="cmpsnMsgPk_AE" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
										<ezf:inputHidden name="cmpsnMsgSegId_AE" ezfName="cmpsnMsgSegId_AE" otherAttr=" size=\"60\" maxlength=\"90\" ezftoupper=\"\""/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
