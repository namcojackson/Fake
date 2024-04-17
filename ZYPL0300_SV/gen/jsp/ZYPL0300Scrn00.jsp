<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20131107071854 --%>
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
<fmt:setBundle basename="I18N_ZYPL0300Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="ZYPL0300Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.ZYPL0300Scrn00.title" bundle="${I18N_SCREEN_ID}">Attachments Upload / Download</fmt:message>">
			
<center>
	<table width="100%">
		<tr>
			<td>
				
<%-- +++++ Header : START ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
				<table align="center" border="0">
					<tr>
						<td>
							<table>
								<col width="66">
								<col width="">
								
								<tr valign="">
									<td class="stab"><fmt:message key="i18n.ZYPL0300Scrn00.label.1" bundle="${I18N_SCREEN_ID}">File Path</fmt:message></td>
									<td><ezf:inputFile name="xxFileData" ezfName="xxFileData" otherAttr=" size=\"75\""/></td>
								</tr>
							</table>
							
							<table>
								<col width="66">
								<col width="">
								<col width="">
								
								<tr valign="">
									<td class="stab" valign="top"><fmt:message key="i18n.ZYPL0300Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Description</fmt:message></td>
									<td><ezf:textArea name="attDataDescTxt_I" ezfName="attDataDescTxt_I" otherAttr=" rows=\"3\" cols=\"73\""/></td>
									<td valign="bottom"><ezf:inputButton name="Upload" value="Upload" htmlClass="cBtn" /></td>
							</table>
						</td>
					</tr>
				</table>
<%-- +++++ Header : END   ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
				
				<hr>
				
<%-- +++++ Body : START ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
				<%-- @Table Header --%>
				<table border="1" cellpadding="1" cellspacing="0">
					<col width="20"  align="center">
					<col width="30"  align="center">
					<col width="420" align="center">
					<col width="164"  align="center">
					<col width="64"  align="center">
					<col width="49"  align="center">
					
					<tr>
						<td class="pClothBs">&nbsp;</td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxNum' )"><fmt:message key="i18n.ZYPL0300Scrn00.label.3" bundle="${I18N_SCREEN_ID}">No.</fmt:message><img id="sortIMG.xxNum" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'attDataNm' )"><fmt:message key="i18n.ZYPL0300Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Name</fmt:message><img id="sortIMG.attDataNm" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezInTime' )"><fmt:message key="i18n.ZYPL0300Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Uploaded Date</fmt:message><img id="sortIMG.ezInTime" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'attDataVol' )"><fmt:message key="i18n.ZYPL0300Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Size</fmt:message><img id="sortIMG.attDataVol" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'attDataDescTxt_FG' )"><fmt:message key="i18n.ZYPL0300Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Desc</fmt:message><img id="sortIMG.attDataDescTxt_FG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					</tr>
				</table>
				
				<%-- @Table Body --%>
				<div style="overflow-y:scroll; height:242; overflow-x:none; width:">
					<table border="1" cellpadding="1" cellspacing="0" id="A">
						<col width="20"  align="center">
						<col width="30"  align="right">
						<col width="420" align="left">
						<col width="164" align="center">
						<col width="64"  align="center">
						<col width="50"  align="center">
						
						<tbody>
							<ezf:row ezfHyo="A">
								<tr>
									<td><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="xxNum" ezfName="xxNum" ezfHyo="A" ezfArrayNo="0" /></td>
									<td style="word-break: break-all;"><ezf:anchor name="attDataNm" ezfName="attDataNm" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Download" otherAttr=" ezfanchortext=\"\""><ezf:label name="attDataNm" ezfName="attDataNm" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
									<td><ezf:label name="ezInTime_DT" ezfName="ezInTime_DT" ezfHyo="A" ezfArrayNo="0" />&nbsp;<ezf:label name="ezInTime_TM" ezfName="ezInTime_TM" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="attDataVol_O" ezfName="attDataVol_O" ezfHyo="A" ezfArrayNo="0" /><ezf:label name="xxFileVolUnit" ezfName="xxFileVolUnit" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:anchor name="attDataDescTxt_FG" ezfName="attDataDescTxt_FG" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ShowDescription" otherAttr=" ezfanchortext=\"\""><ezf:label name="attDataDescTxt_FG" ezfName="attDataDescTxt_FG" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
								</tr>
							</ezf:row>
							<ezf:skip>
								<tr class="pEvenNumberBGcolor">
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>2</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_2.pdf</label></a></td>
									<td><label ezfout>04/02/2009</label>&nbsp;<label ezfout>02:02:02</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><label ezfout>&nbsp;</label></td>
								</tr>
								<tr>
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>3</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_3.pdf</label></a></td>
									<td><label ezfout>04/03/2009</label>&nbsp;<label ezfout>03:03:03</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><a href="#"><label ezfout>Y</label></a></td>
								</tr>
								<tr class="pEvenNumberBGcolor">
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>4</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_4.pdf</label></a></td>
									<td><label ezfout>04/04/2009</label>&nbsp;<label ezfout>04:04:04</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><label ezfout>&nbsp;</label></td>
								</tr>
								<tr>
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>5</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_5.pdf</label></a></td>
									<td><label ezfout>04/05/2009</label>&nbsp;<label ezfout>05:05:05</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><a href="#"><label ezfout>Y</label></a></td>
								</tr>						
								<tr class="pEvenNumberBGcolor">
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>6</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_6.pdf</label></a></td>
									<td><label ezfout>04/06/2009</label>&nbsp;<label ezfout>06:06:06</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><label ezfout>&nbsp;</label></td>
								</tr>
								<tr>
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>7</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_7.pdf</label></a></td>
									<td><label ezfout>04/07/2009</label>&nbsp;<label ezfout>07:07:07</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><a href="#"><label ezfout>Y</label></a></td>
								</tr>
								<tr class="pEvenNumberBGcolor">
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>8</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_8.pdf</label></a></td>
									<td><label ezfout>04/08/2009</label>&nbsp;<label ezfout>08:08:08</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><label ezfout>&nbsp;</label></td>
								</tr>
								<tr>
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>9</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_9.pdf</label></a></td>
									<td><label ezfout>04/09/2009</label>&nbsp;<label ezfout>09:09:09</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><a href="#"><label ezfout>Y</label></a></td>
								</tr>
								<tr class="pEvenNumberBGcolor">
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>10</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_10.pdf</label></a></td>
									<td><label ezfout>04/10/2009</label>&nbsp;<label ezfout>10:10:10</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><label ezfout>&nbsp;</label></td>
								</tr>
								<tr>
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>11</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_11.pdf</label></a></td>
									<td><label ezfout>04/11/2009</label>&nbsp;<label ezfout>11:11:11</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><a href="#"><label ezfout>Y</label></a></td>
								</tr>
								<tr class="pEvenNumberBGcolor">
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>12</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_12.pdf</label></a></td>
									<td><label ezfout>04/12/2009</label>&nbsp;<label ezfout>12:12:12</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><label ezfout>&nbsp;</label></td>
								</tr>
								<tr>
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>13</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_13.pdf</label></a></td>
									<td><label ezfout>04/13/2009</label>&nbsp;<label ezfout>13:13:13</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><a href="#"><label ezfout>Y</label></a></td>
								</tr>
								<tr class="pEvenNumberBGcolor">
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>14</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_14.pdf</label></a></td>
									<td><label ezfout>04/14/2009</label>&nbsp;<label ezfout>14:14:14</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><label ezfout>&nbsp;</label></td>
								</tr>
								<tr>
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>15</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_15.pdf</label></a></td>
									<td><label ezfout>04/15/2009</label>&nbsp;<label ezfout>15:15:15</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><a href="#"><label ezfout>Y</label></a></td>
								</tr>
							</ezf:skip>
						</tbody>
					</table>
				</div>
				
				<table border="0" height="40">
					<col width="">
					<col width="">
					<col width="536">
					<col width="">
					<tr valign="bottom">
						<td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="cBtn" /></td>
						<td><ezf:inputButton name="UnSelectAll" value="Un Select All" htmlClass="cBtn" /></td>
						<td>&nbsp;</td>
						<td><ezf:inputButton name="Delete" value="Delete" htmlClass="cBtn" /></td>
					</tr>
				</table>
<%-- +++++ Body : END   ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
				
				<hr>
				
<%-- +++++ Footer : START ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
				<table align="center">
					<col width="">
					<tr>
						<td><ezf:textArea name="attDataDescTxt_O" ezfName="attDataDescTxt_O" otherAttr=" rows=\"4\" cols=\"90\""/></td>
					</tr>
				</table>
<%-- +++++ Footer : END   ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>

			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
