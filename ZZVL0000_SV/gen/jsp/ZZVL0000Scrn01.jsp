<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161202194945 --%>
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
			<input type="hidden" name="pageID" value="ZZVL0000Scrn01">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Set Preferred View Setting">

<center>
	<table width="80%">
		<tr>
			<td>
				<table>
					<col width="100">
					<col width="100">
					<col width="100">
					<tr>
						<td class="stab">Role ID:</td>
						<td class="pOut"><ezf:label name="roleNm_2" ezfName="roleNm_2" /></td>
						<td></td>
					</tr>
					<tr>
						<td class="stab">User ID:</td>
						<td><ezf:inputText name="usrId_2" ezfName="usrId_2" otherAttr=" size=\"15\" maxlength=\"6\" ezftoupper=\"\""/></td>
						<td></td>
					</tr>
					<tr>
						<td class="stab">BizApp ID:</td>
						<td><ezf:inputText name="bizAppId_2" ezfName="bizAppId_2" otherAttr=" size=\"15\" maxlength=\"8\" ezftoupper=\"\""/></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn3" /></td>
					</tr>
				</table>
				
			<td>
		</tr>
		<tr><td><hr></td></tr>
		<tr>
			<td>
				<table>
					<col width="700px">
					<col width="300px">
					<tr>
						<td></td>
						<td>
						<%-- Pagenation --%>
							<table width="100%">
								<tr align="right">
									<td>
										<!--<table border="0" cellpadding="1" cellspacing="0">
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
												<td class="stab">Showing</td>
												<td class="pOut">1</td>
												<td class="stab">to</td>
												<td class="pOut">10</td>
												<td class="stab">of</td>
												<td class="pOut">200</td>
												<td>&nbsp;</td>
												<td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn3" /></td>
												<td></td>
												<td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn3" /></td>
											</tr>
										</table>
										-->
										<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
											<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"     value="B" />
											<jsp:param name="ShowingFrom" value="xxPageShowFromNum_B" />
											<jsp:param name="ShowingTo"   value="xxPageShowToNum_B" />
											<jsp:param name="ShowingOf"   value="xxPageShowOfNum_B" />
										</jsp:include>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border="1" cellpadding="1" cellspacing="0" width="906">
				<col width="40px" align="center">
				<col width="150px" align="center">
				<col width="144px" align="center">
				<col width="240px" align="center">
				<col width="80px" align="center">
				<col width="216px" align="center">
				<tr>
					<td class="pClothBs">&nbsp;</td>
					<td class="pClothBs">Shared Setting Name</td>
					<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'bizAppId_3' )">
						Business Application ID<img id="sortIMG.bizAppId_3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
					</td>
					<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'bizAppNm_2' )">
						Business Application Name<img id="sortIMG.bizAppNm_2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
					</td>
					<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'scrTblNm_2' )">
						Table Name<img id="sortIMG.scrTblNm_2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
					</td>
					<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'scrTblColDefNm_2' )">
						Personal Setting Name<img id="sortIMG.scrTblColDefNm_2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
					</td>
				</tr>
				</table>
				<div id="bottomTBL" style="word-break:break-all; overflow:auto; height:425; width:924">
					<table border="1" cellpadding="1" cellspacing="0"id="B" width="906">
						<col width="40px" align="center">
						<col width="150px" align="center">
						<col width="144px">
						<col width="240px">
						<col width="80px">
						<col width="216px">
						<ezf:row ezfHyo="B">
							<tr>
								<td><ezf:inputCheckBox name="xxChkBox_2" ezfName="xxChkBox_2" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
								<td><ezf:inputText name="scrTblColDefNm_3" ezfName="scrTblColDefNm_3" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
								<td><ezf:label name="bizAppId_3" ezfName="bizAppId_3" ezfHyo="B" ezfArrayNo="0" /></td>
								<td><ezf:label name="bizAppNm_2" ezfName="bizAppNm_2" ezfHyo="B" ezfArrayNo="0" /></td>
								<td><ezf:label name="scrTblNm_2" ezfName="scrTblNm_2" ezfHyo="B" ezfArrayNo="0" /></td>
								<td><ezf:label name="scrTblColDefNm_2" ezfName="scrTblColDefNm_2" ezfHyo="B" ezfArrayNo="0" /></td>
							</tr>
						</ezf:row>
						<ezf:skip>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
							<tr>
								<td><input type="checkbox" class="" value="Y" name="xxChkBox_2" ezfname="xxChkBox_2" ezfhyo="B"></td>
								<td><input type="text" class="pHsu" size="20" maxlength="30" value="" ezftoupper name="scrTblColDefNm_3" ezfname="scrTblColDefNm_3" ezfhyo="B"></td>
								<td><label ezfout name="bizAppId_3" ezfname="bizAppId_3" ezfhyo="B">XXXX0000</label></td>
								<td><label ezfout name="bizAppNm_2" ezfname="bizAppNm_2" ezfhyo="B">PO Change Inquiry</label></td>
								<td><label ezfout name="scrTblNm_2" ezfname="scrTblNm_2" ezfhyo="B">AHEAD</label></td>
								<td><label ezfout name="scrTblColDefNm_2" ezfname="scrTblColDefNm_2" ezfhyo="B">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
							</tr>
						</ezf:skip>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
