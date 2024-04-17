<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20081230084539 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="ZZZL9100Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Sample">

<center>
	<table>
		<tr>
			<td>
			
				<table>
					<tr>
						<td class="stab">Business Application ID</td>
						<td><ezf:inputText name="ezBusinessID" ezfName="ezBusinessID" value="XXXL9999" otherAttr=" size=\"8\" maxlength=\"8\""/></td>
						<td>&nbsp;</td>
						<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
					</tr>
				</table>
				
				<hr>
				
				<table border="1" cellpadding="1" cellspacing="0">
					<col width="30"  align="center">
					<col width="130" align="center">
					<col width="130" align="center">
					<col width="130" align="center">
					<col width="130" align="center">
					<col width="130" align="center">
					<tr>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxNo_A1'   )"          >No<img id="sortIMG.xxNo_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezBusinessID_A1'   )"  >EZBUSINESSID<img id="sortIMG.ezBusinessID_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezCancelFlag_A1' )"    >EZCANCELFLAG<img id="sortIMG.ezCancelFlag_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezOnlStartTime_A1'   )">EZONLSTARTTIME<img id="sortIMG.ezOnlStartTime_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezOnlEndTime_A1'   )"  >EZONLENDTIME<img id="sortIMG.ezOnlEndTime_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezOnlStopFlag_A1'   )" >EZONLSTOPFLAG<img id="sortIMG.ezOnlStopFlag_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					</tr>
				</table>
				
				<div style="overflow:auto; height:482;">
				<table border="1" cellpadding="1" cellspacing="0" id="A">
					<col width="30"  align="center">
					<col width="130" align="center">
					<col width="130" align="center">
					<col width="130" align="center">
					<col width="130" align="center">
					<col width="130" align="center">
					<ezf:row ezfHyo="A">
						<tr>
							<td><ezf:label name="xxNo_A1" ezfName="xxNo_A1" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="ezBusinessID_A1" ezfName="ezBusinessID_A1" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="ezCancelFlag_A1" ezfName="ezCancelFlag_A1" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="ezOnlStartTime_A1" ezfName="ezOnlStartTime_A1" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="ezOnlEndTime_A1" ezfName="ezOnlEndTime_A1" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="ezOnlStopFlag_A1" ezfName="ezOnlStopFlag_A1" ezfHyo="A" ezfArrayNo="0" /></td>
						</tr>
					</ezf:row>
					<ezf:skip>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
						<tr>
							<td><label ezfout name="xxNo_A1" ezfname="xxNo_A1" ezfhyo="A">1</label></td>
							<td><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">EZBUSINESSID</label></td>
							<td><label ezfout name="ezCancelFlag_A1" ezfname="ezCancelFlag_A1" ezfhyo="A">EZCANCELFLAG</label></td>
							<td><label ezfout name="ezOnlStartTime_A1" ezfname="ezOnlStartTime_A1" ezfhyo="A">EZONLSTARTTIME</label></td>
							<td><label ezfout name="ezOnlEndTime_A1" ezfname="ezOnlEndTime_A1" ezfhyo="A">EZONLENDTIME</label></td>
							<td><label ezfout name="ezOnlStopFlag_A1" ezfname="ezOnlStopFlag_A1" ezfhyo="A">EZONLSTOPFLAG</label></td>
						</tr>
					</ezf:skip>
					</tbody>
				</table>
				</div>
				
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
