<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180712085957 --%>
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
			<input type="hidden" name="pageID" value="NWAL1870Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Supersede Confirmation Popup">


			<center>
			<table border="0" cellpadding="0" width="910">
				<div class="pTab_BODY">
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" >
								<col align="center" width="130">
								<col align="center" width="">
								<tr>
								    <td>
										&nbsp;
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr>
								    <td>
								    &nbsp;
								    </td>
									<td align="left">
										<br>
									    <font size="2">
											Supersede setting isn't locked.<br>
											If there is no problem to supersede, please execute the "Supersede".<br>
									    </font>
									    <br>
									</td>
								</tr>
								<tr>
								    <td>
										&nbsp;
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
			</table>
			<hr>
			<br>
<%-- ######################################## DETAIL ######################################## --%>
			<table>
					<tr>
                        <td align="center">
							<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
								<col align="center" width="120">
								<col align="center" width="150">
								<col align="center" width="220">
								<col align="center" width="150">
								<tr height="22">
									<td class="pClothBs">&nbsp;</td>
									<td class="pClothBs" align="center">Item#</td>
									<td class="pClothBs" align="center">Description</td>
									<td class="pClothBs" align="center">Manufacture#</td>
									
								</tr>
								<tr style="height:25">
										<td align="left">Original Item</td>
										<td align="left"><ezf:inputText name="mdseCd_I" ezfName="mdseCd_I" value="WWWWWWWWWWWWWWWW" otherAttr=" size=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td align="left"><ezf:inputText name="mdseDescShortTxt_I" ezfName="mdseDescShortTxt_I" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" otherAttr=" size=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td align="left"><ezf:inputText name="mnfItemCd_I" ezfName="mnfItemCd_I" value="WWWWWWWWWWWWWWWW" otherAttr=" size=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
								</tr>
								<tr style="height:25">
										<td align="left">Supersede Item</td>
										<td align="left"><ezf:inputText name="mdseCd_S" ezfName="mdseCd_S" value="WWWWWWWWWWWWWWWW" otherAttr=" size=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td align="left"><ezf:inputText name="mdseDescShortTxt_S" ezfName="mdseDescShortTxt_S" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" otherAttr=" size=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td align="left"><ezf:inputText name="mnfItemCd_S" ezfName="mnfItemCd_S" value="WWWWWWWWWWWWWWWW" otherAttr=" size=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
                        <td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="center">
							<table border="0" cellpadding="0" cellspacing="0" width="300">
								<col align="center">
								<col align="center">
								<col align="center">
							    <tr>
							        <td>
							            &nbsp;
							        </td>
							        <td>
							            &nbsp;
							        </td>
							        <td>
							            &nbsp;
							        </td>
							    </tr>
							    <tr>
								    <td>
								        <ezf:inputButton name="CMN_Supersede" value="Supersede" htmlClass="pBtn6" otherAttr=" style=\"width:90;\""/>
								    </td>
								    <td>
								            &nbsp;
								    </td>
								    <td>
								        <ezf:inputButton name="CMN_keepOriginal" value="Keep Original" htmlClass="pBtn6" otherAttr=" style=\"width:90;\""/>
								    </td>
							    </tr>
							</table>
						</td>
					</tr>
				</div>
			</table>
			</center>



<%-- **** Task specific area ends here **** --%>
