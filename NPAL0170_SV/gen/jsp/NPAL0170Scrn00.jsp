<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160125143444 --%>
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
			<input type="hidden" name="pageID" value="NPAL0170Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="PO Text Entry">

			<center>
				<!-- HEADER -->
				<table style="width: 790px; MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
					<tr>
						<td style="text-align: left;">
							<table border="0" cellpadding="0" cellspacing="0" style="margin-top:10px;">
								<col width="40px">
								<col width="160px">
								<col width="60px">
								<col width="100px">
								<tr>
									<td class="stab">Req#</td>
									<td><ezf:inputText name="prchReqNum" ezfName="prchReqNum" value="VP00001" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td class="stab">Req Line#</td>
									<td><ezf:inputText name="xxLineNum" ezfName="xxLineNum" value="1" otherAttr=" size=\"8\" style=\"text-align: right;\" maxlength=\"8\" ezftoupper=\"\""/></td>
								</tr>
								<tr>
									<td class="stab">PO#</td>
									<td><ezf:inputText name="poOrdNum" ezfName="poOrdNum" value="VP00001" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td class="stab">PO Line</td>
									<td><ezf:inputText name="poOrdDtlLineNum" ezfName="poOrdDtlLineNum" value="1" otherAttr=" size=\"8\" style=\"text-align: right;\" maxlength=\"8\" ezftoupper=\"\""/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<hr>
				<table style="width: 790px; MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
					<tr>
						<td style="text-align: left;">
							<table border="0" cellpadding="0" cellspacing="0" style="margin-top:10px;">
								<col width="100px">
								<col width="30px">
								<tr>
									<td class="stab">Message Type</td>
									<td><ezf:inputText name="poMsgTpDescTxt" ezfName="poMsgTpDescTxt" value="VP00001" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
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
										<ezf:inputText name="poMsgTxt_A0" ezfName="poMsgTxt_A0" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgPk_A0" ezfName="poMsgPk_A0" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSegId_A0" ezfName="poMsgSegId_A0" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSubmtPsnCd_A0" ezfName="poMsgSubmtPsnCd_A0" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 2 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">2</td>
									<td style="text-align: center;">
										<ezf:inputText name="poMsgTxt_A1" ezfName="poMsgTxt_A1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgPk_A1" ezfName="poMsgPk_A1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSegId_A1" ezfName="poMsgSegId_A1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSubmtPsnCd_A1" ezfName="poMsgSubmtPsnCd_A1" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 3 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">3</td>
									<td style="text-align: center;">
										<ezf:inputText name="poMsgTxt_A2" ezfName="poMsgTxt_A2" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgPk_A2" ezfName="poMsgPk_A2" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSegId_A2" ezfName="poMsgSegId_A2" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSubmtPsnCd_A2" ezfName="poMsgSubmtPsnCd_A2" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 4 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">4</td>
									<td style="text-align: center;">
										<ezf:inputText name="poMsgTxt_A3" ezfName="poMsgTxt_A3" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgPk_A3" ezfName="poMsgPk_A3" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSegId_A3" ezfName="poMsgSegId_A3" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSubmtPsnCd_A3" ezfName="poMsgSubmtPsnCd_A3" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 5 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">5</td>
									<td style="text-align: center;">
										<ezf:inputText name="poMsgTxt_A4" ezfName="poMsgTxt_A4" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgPk_A4" ezfName="poMsgPk_A4" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSegId_A4" ezfName="poMsgSegId_A4" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSubmtPsnCd_A4" ezfName="poMsgSubmtPsnCd_A4" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 6 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">6</td>
									<td style="text-align: center;">
										<ezf:inputText name="poMsgTxt_A5" ezfName="poMsgTxt_A5" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgPk_A5" ezfName="poMsgPk_A5" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSegId_A5" ezfName="poMsgSegId_A5" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSubmtPsnCd_A5" ezfName="poMsgSubmtPsnCd_A5" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 7 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">7</td>
									<td style="text-align: center;">
										<ezf:inputText name="poMsgTxt_A6" ezfName="poMsgTxt_A6" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgPk_A6" ezfName="poMsgPk_A6" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSegId_A6" ezfName="poMsgSegId_A6" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSubmtPsnCd_A6" ezfName="poMsgSubmtPsnCd_A6" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 8 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">8</td>
									<td style="text-align: center;">
										<ezf:inputText name="poMsgTxt_A7" ezfName="poMsgTxt_A7" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgPk_A7" ezfName="poMsgPk_A7" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSegId_A7" ezfName="poMsgSegId_A7" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSubmtPsnCd_A7" ezfName="poMsgSubmtPsnCd_A7" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 9 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">9</td>
									<td style="text-align: center;">
										<ezf:inputText name="poMsgTxt_A8" ezfName="poMsgTxt_A8" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgPk_A8" ezfName="poMsgPk_A8" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSegId_A8" ezfName="poMsgSegId_A8" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSubmtPsnCd_A8" ezfName="poMsgSubmtPsnCd_A8" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
									</td>
								</tr>
								<!-- 10 -->
								<tr style="height: 28px;">
									<td class="stab" style="text-align: right;">10</td>
									<td style="text-align: center;">
										<ezf:inputText name="poMsgTxt_A9" ezfName="poMsgTxt_A9" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgPk_A9" ezfName="poMsgPk_A9" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSegId_A9" ezfName="poMsgSegId_A9" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
										<ezf:inputHidden name="poMsgSubmtPsnCd_A9" ezfName="poMsgSubmtPsnCd_A9" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
