<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20181012144044 --%>
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
			<input type="hidden" name="pageID" value="NMAL6790Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Contact Point Popup">
<center>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
				<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
					<col width="100">
					<col width="180">
					<col width="5">
					<col width="90">
					<col width="180">
					<col width="5">
					<col width="90">
					<col width="180">
					<tr>
						<td class="stab">Relationship to CSA</td>
						<td>
							<ezf:select name="ctacTpCd_H1" ezfName="ctacTpCd_H1" ezfBlank="1" ezfCodeName="ctacTpCd_H2" ezfDispName="ctacTpDescTxt_H1" otherAttr=" style=\"width:146px;\""/>
						</td>
						<td></td>
						<td class="stab">Email Address(*)</td>
						<td><ezf:inputText name="dsCtacPntValTxt_H2" ezfName="dsCtacPntValTxt_H2" otherAttr=" size=\"20\" maxlength=\"100\" ezftoupper=\"\""/></td>
						<td></td>
						<td colspan="2">
							<ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" />Active
							<ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" />E-Mail
						</td>
					</tr>
					<tr>
						<td class="stab">Account Name (*)</td>
						<td><ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
						<td></td>
						<td class="stab">First Name (*)</td>
						<td><ezf:inputText name="ctacPsnFirstNm_H1" ezfName="ctacPsnFirstNm_H1" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
						<td></td>
						<td class="stab">Work Phone (*)</td>
						<td><ezf:inputText name="dsCtacPntValTxt_H1" ezfName="dsCtacPntValTxt_H1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
					</tr>
					<tr>
						<td class="stab">Account# (*)</td>
						<td><ezf:inputText name="dsAcctNum_H1" ezfName="dsAcctNum_H1" otherAttr=" size=\"20\" maxlength=\"28\" ezftoupper=\"\""/></td>
						<td></td>
						<td class="stab">Last Name (*)</td>
						<td><ezf:inputText name="ctacPsnLastNm_H1" ezfName="ctacPsnLastNm_H1" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
						<td></td>
						<td class="stab">Title</td>
						<td>
							<ezf:select name="dsCtacPsnTtlCd_H1" ezfName="dsCtacPsnTtlCd_H1" ezfBlank="1" ezfCodeName="dsCtacPsnTtlCd_H2" ezfDispName="dsCtacPsnTtlDescTxt_H1" otherAttr=" style=\"width:146px;\""/>
						</td>
					</tr>
					<tr>
						<td class="stab">Location# (*)</td>
						<td><ezf:inputText name="locNum_H1" ezfName="locNum_H1" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
						<td></td>
						<td class="stab">Location Name (*)</td>
						<td><ezf:inputText name="dsLocNm_H1" ezfName="dsLocNm_H1" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
						<td></td>
						<!--<td></td>-->
						<!--<td><input type="button" class="pBtn6" value="Search" name="Search" onclick="sendServer(this)"></td>-->
						<td class="stab">Department</td>
						<td>
							<ezf:select name="dsCtacPsnDeptCd_H1" ezfName="dsCtacPsnDeptCd_H1" ezfBlank="1" ezfCodeName="dsCtacPsnDeptCd_H2" ezfDispName="dsCtacPsnDeptDescTxt_H1" otherAttr=" style=\"width:146px;\""/>
						</td>
					</tr>
					<tr>
						<td class="stab">Serial# (*)</td>
						<td><ezf:inputText name="serNum_H1" ezfName="serNum_H1" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
						<td></td>
						<td class="stab">Address (*)</td>
						<td><ezf:inputText name="xxDplyByItemNm_H1" ezfName="xxDplyByItemNm_H1" otherAttr=" size=\"20\" maxlength=\"250\" ezftoupper=\"\""/></td>
						<td></td>
						<!--
						<td class="stab">IB Role</td>
						<td>
							<select name="svcCtacTpCd_H1" ezfname="svcCtacTpCd_H1" ezflist="svcCtacTpCd_H2,svcCtacTpDescTxt_H1,99, ,blank" style="width:146px;">
								<option></option>
								<option selected>ACCOUNTS PAYABLE</option>
								<option>ACCOUNTS RECEIVABLE</option>
								<option>EXECUTIVE</option>
								<option>IT</option>
							</select>
						</td>
						-->
					</tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
				</table>

				<hr>

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

				<div id="topTBL" align="left" style="overflow-x:hidden; width:988; overflow-y:none; height:20;">
				<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
						<col width="55">
						<col width="80">
						<col width="120">
						<col width="120">
						<col width="120">
						<col width="86">
						<col width="158">
						<col width="86">
						<col width="190">
						<col width="50">
						<col width="50">
						<col width="100">
						<col width="120">
						<col width="120">
						<col width="100">
						<col width="226">
					<tr>
						<td class="pClothBs" align="center"></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctacPsnNum_A1' )">Contact ID<img id="sortIMG.ctacPsnNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctacTpNm_A1' )">Relationship to CSA<img id="sortIMG.ctacTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctacPsnLastNm_A1' )">Last<img id="sortIMG.ctacPsnLastNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctacPsnFirstNm_A1' )">First<img id="sortIMG.ctacPsnFirstNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNum_A1' )">Account Number<img id="sortIMG.dsAcctNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNm_A1' )">Account Name<img id="sortIMG.dsAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'locNum_A1' )">Location Number<img id="sortIMG.locNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDplyByItemNm_A1' )">Address<img id="sortIMG.xxDplyByItemNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxChkBox_A1' )">Active<img id="sortIMG.xxChkBox_A1_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxChkBox_A2' )">Primary<img id="sortIMG.xxChkBox_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsCtacPsnDeptNm_A1' )">Department<img id="sortIMG.dsCtacPsnDeptNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsCtacPsnTtlNm_A1' )">Title<img id="sortIMG.dsCtacPsnTtlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'serNum_A1' )">Serial<img id="sortIMG.serNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsCtacPntTpNm_A1' )">Primary Contact Type<img id="sortIMG.dsCtacPntTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctacPsnCmntTxt_A1' )">Note<img id="sortIMG.ctacPsnCmntTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td></td>
					</tr>
				</table>
				
				</div>
				<div id="bottomTBL" style="overflow:scroll; width:1005; height:160; word-break:break-all;" onscroll="synchroBottomScroll();">
					<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed;">
						<col width="55">
						<col width="80">
						<col width="120">
						<col width="120">
						<col width="120">
						<col width="86">
						<col width="158">
						<col width="86">
						<col width="190">
						<col width="50">
						<col width="50">
						<col width="100">
						<col width="120">
						<col width="120">
						<col width="100">
						<col width="226">
						<tbody>
						<ezf:row ezfHyo="A">
							<tr>
								<td align="center"><ezf:inputButton name="ShowContactPointDtl" value="Select" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn3" /></td>
								<td><ezf:label name="ctacPsnNum_A1" ezfName="ctacPsnNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
								<td><ezf:inputText name="ctacTpNm_A1" ezfName="ctacTpNm_A1" value="XXXXXXXXX1XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
								<td><ezf:inputText name="ctacPsnLastNm_A1" ezfName="ctacPsnLastNm_A1" value="XXXXXXXXX1XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
								<td><ezf:inputText name="ctacPsnFirstNm_A1" ezfName="ctacPsnFirstNm_A1" value="XXXXXXXXX1XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
								<td><ezf:inputText name="dsAcctNum_A1" ezfName="dsAcctNum_A1" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"28\""/></td>
								<td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\""/></td>
								<td><ezf:inputText name="locNum_A1" ezfName="locNum_A1" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\""/></td>
								<td><ezf:inputText name="xxDplyByItemNm_A1" ezfName="xxDplyByItemNm_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"250\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_A2" ezfName="xxChkBox_A2" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
								<td><ezf:inputText name="dsCtacPsnDeptNm_A1" ezfName="dsCtacPsnDeptNm_A1" value="XXXXXXXXX1XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\""/></td>
								<td><ezf:inputText name="dsCtacPsnTtlNm_A1" ezfName="dsCtacPsnTtlNm_A1" value="XXXXXXXXX1XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
								<td><ezf:inputText name="serNum_A1" ezfName="serNum_A1" value="XXXXXXXXX1XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
								<td><ezf:inputText name="dsCtacPntTpNm_A1" ezfName="dsCtacPntTpNm_A1" value="XXXXXXXXX1XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\""/></td>
								<td><ezf:inputText name="ctacPsnCmntTxt_A1" ezfName="ctacPsnCmntTxt_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"1000\""/></td>
							</tr>
						</ezf:row>
						</tbody>
					</table>
				</div>
				<BR>
				<table width="100%">
					<tr align="left">
					</tr>
					<tr align="left">
						<td><ezf:inputButton name="Select" value="Select" htmlClass="pBtn6" />
						<ezf:inputButton name="Unselect" value="Unselect" htmlClass="pBtn6" /></td>
					</tr>
				</table>

				<div id="topTBL" align="left" style="overflow-x:hidden; width:753; overflow-y:none; height:20;">
				<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
					<col width="24">
					<col width="154">
					<col width="296">
					<col width="156">
					<col width="60">
					<col width="60">
					<tr>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxChkBox_B1' )"><img id="sortIMG.xxChkBox_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'dsCtacPntTpNm_B1' )">Contact Type<img id="sortIMG.dsCtacPntTpNm_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'dsCtacPntValTxt_B1' )">Value<img id="sortIMG.dsCtacPntValTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'dsCtacPsnExtnNum_B1' )">Extension<img id="sortIMG.dsCtacPsnExtnNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxChkBox_B2' )">Opt Out<img id="sortIMG.xxChkBox_B2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxChkBox_B3' )">Active<img id="sortIMG.xxChkBox_B3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					</tr>
				</table>
				</div>

				<div id="bottomTBL" style="overflow-y:scroll; overflow-x:hidden; width:770; height:146; word-break:break-all;" onscroll="synchroBottomScroll();">
					<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout: fixed;">
						<col width="24">
						<col width="154">
						<col width="296">
						<col width="156">
						<col width="60">
						<col width="60">
						<tbody>
						<ezf:row ezfHyo="B">
							<tr>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
								<td><ezf:inputText name="dsCtacPntTpNm_B1" ezfName="dsCtacPntTpNm_B1" value="XXXXXXXXX1XXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\""/></td>
								<td><ezf:inputText name="dsCtacPntValTxt_B1" ezfName="dsCtacPntValTxt_B1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"320\""/></td>
								<td><ezf:inputText name="dsCtacPsnExtnNum_B1" ezfName="dsCtacPsnExtnNum_B1" value="XXXXXXXXX1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B2" ezfName="xxChkBox_B2" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
								<td align="center"><ezf:inputCheckBox name="xxChkBox_B3" ezfName="xxChkBox_B3" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
							</tr>
						</ezf:row>
						<ezf:skip>
							<tr>
								<td align="center"><input type="checkbox" name="xxChkBox_B1" value="Y" ezfname="xxChkBox_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="20" maxlength="60" value="XXXXXXXXX1XXXXX" name="dsCtacPntTpNm_B1" ezfname="dsCtacPntTpNm_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="40" maxlength="320" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" name="dsCtacPntValTxt_B1" ezfname="dsCtacPntValTxt_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="20" maxlength="10" value="XXXXXXXXX1" name="dsCtacPsnExtnNum_B1" ezfname="dsCtacPsnExtnNum_B1" ezfhyo="B"></td>
								<td align="center"><input type="checkbox" name="xxChkBox_B2" value="Y" ezfname="xxChkBox_B2" ezfhyo="B"></td>
								<td align="center"><input type="checkbox" name="xxChkBox_B3" value="Y" ezfname="xxChkBox_B3" ezfhyo="B"></td>
							</tr>
							<tr>
								<td align="center"><input type="checkbox" name="xxChkBox_B1" value="Y" ezfname="xxChkBox_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="20" maxlength="60" value="XXXXXXXXX1XXXXX" name="dsCtacPntTpNm_B1" ezfname="dsCtacPntTpNm_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="40" maxlength="320" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" name="dsCtacPntValTxt_B1" ezfname="dsCtacPntValTxt_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="20" maxlength="10" value="XXXXXXXXX1" name="dsCtacPsnExtnNum_B1" ezfname="dsCtacPsnExtnNum_B1" ezfhyo="B"></td>
								<td align="center"><input type="checkbox" name="xxChkBox_B2" value="Y" ezfname="xxChkBox_B2" ezfhyo="B"></td>
								<td align="center"><input type="checkbox" name="xxChkBox_B3" value="Y" ezfname="xxChkBox_B3" ezfhyo="B"></td>
							</tr>
							<tr>
								<td align="center"><input type="checkbox" name="xxChkBox_B1" value="Y" ezfname="xxChkBox_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="20" maxlength="60" value="XXXXXXXXX1XXXXX" name="dsCtacPntTpNm_B1" ezfname="dsCtacPntTpNm_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="40" maxlength="320" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" name="dsCtacPntValTxt_B1" ezfname="dsCtacPntValTxt_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="20" maxlength="10" value="XXXXXXXXX1" name="dsCtacPsnExtnNum_B1" ezfname="dsCtacPsnExtnNum_B1" ezfhyo="B"></td>
								<td align="center"><input type="checkbox" name="xxChkBox_B2" value="Y" ezfname="xxChkBox_B2" ezfhyo="B"></td>
								<td align="center"><input type="checkbox" name="xxChkBox_B3" value="Y" ezfname="xxChkBox_B3" ezfhyo="B"></td>
							</tr>
							<tr>
								<td align="center"><input type="checkbox" name="xxChkBox_B1" value="Y" ezfname="xxChkBox_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="20" maxlength="60" value="XXXXXXXXX1XXXXX" name="dsCtacPntTpNm_B1" ezfname="dsCtacPntTpNm_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="40" maxlength="320" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" name="dsCtacPntValTxt_B1" ezfname="dsCtacPntValTxt_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="20" maxlength="10" value="XXXXXXXXX1" name="dsCtacPsnExtnNum_B1" ezfname="dsCtacPsnExtnNum_B1" ezfhyo="B"></td>
								<td align="center"><input type="checkbox" name="xxChkBox_B2" value="Y" ezfname="xxChkBox_B2" ezfhyo="B"></td>
								<td align="center"><input type="checkbox" name="xxChkBox_B3" value="Y" ezfname="xxChkBox_B3" ezfhyo="B"></td>
							</tr>
							<tr>
								<td align="center"><input type="checkbox" name="xxChkBox_B1" value="Y" ezfname="xxChkBox_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="20" maxlength="60" value="XXXXXXXXX1XXXXX" name="dsCtacPntTpNm_B1" ezfname="dsCtacPntTpNm_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="40" maxlength="320" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" name="dsCtacPntValTxt_B1" ezfname="dsCtacPntValTxt_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="20" maxlength="10" value="XXXXXXXXX1" name="dsCtacPsnExtnNum_B1" ezfname="dsCtacPsnExtnNum_B1" ezfhyo="B"></td>
								<td align="center"><input type="checkbox" name="xxChkBox_B2" value="Y" ezfname="xxChkBox_B2" ezfhyo="B"></td>
								<td align="center"><input type="checkbox" name="xxChkBox_B3" value="Y" ezfname="xxChkBox_B3" ezfhyo="B"></td>
							</tr>
							<tr>
								<td align="center"><input type="checkbox" name="xxChkBox_B1" value="Y" ezfname="xxChkBox_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="20" maxlength="60" value="XXXXXXXXX1XXXXX" name="dsCtacPntTpNm_B1" ezfname="dsCtacPntTpNm_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="40" maxlength="320" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" name="dsCtacPntValTxt_B1" ezfname="dsCtacPntValTxt_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="20" maxlength="10" value="XXXXXXXXX1" name="dsCtacPsnExtnNum_B1" ezfname="dsCtacPsnExtnNum_B1" ezfhyo="B"></td>
								<td align="center"><input type="checkbox" name="xxChkBox_B2" value="Y" ezfname="xxChkBox_B2" ezfhyo="B"></td>
								<td align="center"><input type="checkbox" name="xxChkBox_B3" value="Y" ezfname="xxChkBox_B3" ezfhyo="B"></td>
							</tr>
							<tr>
								<td align="center"><input type="checkbox" name="xxChkBox_B1" value="Y" ezfname="xxChkBox_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="20" maxlength="60" value="XXXXXXXXX1XXXXX" name="dsCtacPntTpNm_B1" ezfname="dsCtacPntTpNm_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="40" maxlength="320" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" name="dsCtacPntValTxt_B1" ezfname="dsCtacPntValTxt_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="20" maxlength="10" value="XXXXXXXXX1" name="dsCtacPsnExtnNum_B1" ezfname="dsCtacPsnExtnNum_B1" ezfhyo="B"></td>
								<td align="center"><input type="checkbox" name="xxChkBox_B2" value="Y" ezfname="xxChkBox_B2" ezfhyo="B"></td>
								<td align="center"><input type="checkbox" name="xxChkBox_B3" value="Y" ezfname="xxChkBox_B3" ezfhyo="B"></td>
							</tr>
							<tr>
								<td align="center"><input type="checkbox" name="xxChkBox_B1" value="Y" ezfname="xxChkBox_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="20" maxlength="60" value="XXXXXXXXX1XXXXX" name="dsCtacPntTpNm_B1" ezfname="dsCtacPntTpNm_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="40" maxlength="320" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" name="dsCtacPntValTxt_B1" ezfname="dsCtacPntValTxt_B1" ezfhyo="B"></td>
								<td><input type="text" class="pPro" size="20" maxlength="10" value="XXXXXXXXX1" name="dsCtacPsnExtnNum_B1" ezfname="dsCtacPsnExtnNum_B1" ezfhyo="B"></td>
								<td align="center"><input type="checkbox" name="xxChkBox_B2" value="Y" ezfname="xxChkBox_B2" ezfhyo="B"></td>
								<td align="center"><input type="checkbox" name="xxChkBox_B3" value="Y" ezfname="xxChkBox_B3" ezfhyo="B"></td>
							</tr>
						</ezf:skip>
						</tbody>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- ###SCRIPT --%>
<script type="text/javascript">
	function synchroBottomScroll() {
		var bottomTBL = document.getElementById( 'bottomTBL' );
		var topTBL    = document.getElementById( 'topTBL'    );
		topTBL.scrollLeft = bottomTBL.scrollLeft;
	}
</script>


<%-- **** Task specific area ends here **** --%>
