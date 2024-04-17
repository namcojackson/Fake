<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180301105952 --%>
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
			<input type="hidden" name="pageID" value="NMAL6770Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Contact Search Popup">
			
<%@ page import="business.servlet.NMAL6770.NMAL6770Bean" %>

<center>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
				<table border="0" cellpadding="1" cellspacing="0" align="center">
					<col width="100">
					<col width="180">
					<col width="5">
					<col width="90">
					<col width="180">
					<col width="5">
					<col width="90">
					<col width="220">
					<tr>
						<td class="stab">Relationship to CSA</td>
						<td>
							<ezf:select name="ctacTpCd_H1" ezfName="ctacTpCd_H1" ezfBlank="1" ezfCodeName="ctacTpCd_H2" ezfDispName="ctacTpDescTxt_H1" />
						</td>
						<td></td>
						<td class="stab">Email Address(*)</td>
						<td><ezf:inputText name="dsCtacPntValTxt_H2" ezfName="dsCtacPntValTxt_H2" otherAttr=" size=\"20\" maxlength=\"100\" ezftoupper=\"\""/></td>
						<td></td>
						<td class="stab" colspan="3">
							<ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" />Active
							<ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" />E-Mail
							<ezf:inputCheckBox name="xxChkBox_H3" ezfName="xxChkBox_H3" value="Y" />Include Location for entered Account#
						</td>
					</tr>
					<tr>
						<td class="stab">Account Name (*)</td>
						<td><ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
						<td></td>
						<td class="stab">First Name (*)</td>
						<td><ezf:inputText name="ctacPsnFirstNm_H1" ezfName="ctacPsnFirstNm_H1" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
						<td></td>
						<td class="stab">Phone-Work (*)</td>
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
							<ezf:select name="dsCtacPsnTtlCd_H1" ezfName="dsCtacPsnTtlCd_H1" ezfBlank="1" ezfCodeName="dsCtacPsnTtlCd_H2" ezfDispName="dsCtacPsnTtlDescTxt_H1" />
						</td>
					</tr>
					<tr>
						<td class="stab">Location# (*)</td>
						<td><ezf:inputText name="locNum_H1" ezfName="locNum_H1" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
						<td></td>
						<td class="stab">Location Name (*)</td>
						<td><ezf:inputText name="dsLocNm_H1" ezfName="dsLocNm_H1" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td class="stab">Bill To Code (*)</td>
						<td><ezf:inputText name="billToCustCd_H1" ezfName="billToCustCd_H1" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
						<td></td>
						<td class="stab">Ship To Code (*)</td>
						<td><ezf:inputText name="shipToCustCd_H1" ezfName="shipToCustCd_H1" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
						<td colspan="2"></td>
						<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
					</tr>
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

				<div id="topTBL" align="left" style="overflow-x:hidden; width:983; overflow-y:none; height:20;">
					<table border="1" cellpadding="1" cellspacing="0" width="2252" style="table-layout:fixed;">
						<col width="23">
						<col width="80">
						<col width="80">
						<col width="150">
						<col width="80">
						<col width="185">
						<col width="150">
						<col width="115">
						<col width="115">
						<col width="65">
						<col width="115">
						<col width="150">
						<col width="95">
						<col width="55">
						<col width="95">
						<col width="95">
						<col width="95">
						<col width="95">
						<col width="197">
						<col width="80">
						<col width="82">
						<tr>
							<td class="pClothBs">&nbsp;</td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctacPsnNum_A1' )">Contact ID<img id="sortIMG.ctacPsnNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNum_A1' )">Account#<img id="sortIMG.dsAcctNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNm_A1' )">Account Name<img id="sortIMG.dsAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'locNum_A1' )">Location#<img id="sortIMG.locNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDplyByItemNm_A1' )">Address<img id="sortIMG.xxDplyByItemNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<!-- Add Start 2017/12/06 Hd.Sugawara QC#21897 -->
							<!-- <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctacTpNm_A1' )">Relationship to CSA<img id="sortIMG.ctacTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td> -->
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctacTpDescTxt_A1' )">Relationship to CSA<img id="sortIMG.ctacTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<!-- Add End   2017/12/06 Hd.Sugawara QC#21897 -->
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctacPsnFirstNm_A1' )">First Name<img id="sortIMG.ctacPsnFirstNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctacPsnLastNm_A1' )">Last Name<img id="sortIMG.ctacPsnLastNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsCtacPsnSaltNm_A1' )">Salutation<img id="sortIMG.dsCtacPsnSaltNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsCtacPsnTtlNm_A1' )">Title<img id="sortIMG.dsCtacPsnTtlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsCtacPntValTxt_A2' )">E-Mail Address<img id="sortIMG.dsCtacPntValTxt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsCtacPntValTxt_A1' )">Phone-Work<img id="sortIMG.dsCtacPntValTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsCtacPsnExtnNum_A1' )">Ext<img id="sortIMG.dsCtacPsnExtnNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsCtacPntValTxt_A3' )">Phone-Fax<img id="sortIMG.dsCtacPntValTxt_A3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsCtacPntValTxt_A4' )">Phone-Assistant<img id="sortIMG.dsCtacPntValTxt_A4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsCtacPntValTxt_A5' )">Phone-Mobile<img id="sortIMG.dsCtacPntValTxt_A5" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsCtacPntTpNm_A1' )">Primary Contact Type<img id="sortIMG.dsCtacPntTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctacPsnCmntTxt_A1' )">Note<img id="sortIMG.ctacPsnCmntTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToCustCd_A1' )">Bill To Code<img id="sortIMG.billToCustCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shipToCustCd_A1' )">Ship To Code<img id="sortIMG.shipToCustCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						</tr>
					</table>
				</div>
				<% if ("M".equals(((NMAL6770Bean)databean).getXxModeCd_H1())) { %>
				<div id="bottomTBL" style="overflow:scroll; width:1000; height:225; word-break:break-all;" onscroll="synchroBottomScroll();">
				<% } else { %>
				<div id="bottomTBL" style="overflow:scroll; width:1000; height:376; word-break:break-all;" onscroll="synchroBottomScroll();">
				<% } %>
					<table border="1" cellpadding="1" cellspacing="0" id="A" width="2252" style="table-layout:fixed;">
						<col width="23">
						<col width="80">
						<col width="80">
						<col width="150">
						<col width="80">
						<col width="185">
						<col width="150">
						<col width="115">
						<col width="115">
						<col width="65">
						<col width="115">
						<col width="150">
						<col width="95">
						<col width="55">
						<col width="95">
						<col width="95">
						<col width="95">
						<col width="95">
						<col width="197">
						<col width="80">
						<col width="82">
						<tbody>
						<ezf:row ezfHyo="A">
							<tr>
								<td>
									<% if ("M".equals(((NMAL6770Bean)databean).getXxModeCd_H1())) { %>
									<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" />
									<% } else { %>
									&nbsp;
									<% } %>
								</td>
								<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SelectContactId" ><ezf:label name="ctacPsnNum_A1" ezfName="ctacPsnNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
								<td><ezf:inputText name="dsAcctNum_A1" ezfName="dsAcctNum_A1" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"28\""/></td>
								<td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\""/></td>
								<td><ezf:inputText name="locNum_A1" ezfName="locNum_A1" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\""/></td>
								<td><ezf:inputText name="xxDplyByItemNm_A1" ezfName="xxDplyByItemNm_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"250\""/></td>
								<!-- Add Start 2017/12/06 Hd.Sugawara QC#21897 -->
								<!-- <td><input type="text" class="pPro" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2" name="ctacTpNm_A1" ezfname="ctacTpNm_A1" ezfhyo="A"></td> -->
								<td><ezf:inputText name="ctacTpDescTxt_A1" ezfName="ctacTpDescTxt_A1" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
								<!-- Add End   2017/12/06 Hd.Sugawara QC#21897 -->
								<td><ezf:inputText name="ctacPsnFirstNm_A1" ezfName="ctacPsnFirstNm_A1" value="XXXXXXXXX1XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
								<td><ezf:inputText name="ctacPsnLastNm_A1" ezfName="ctacPsnLastNm_A1" value="XXXXXXXXX1XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
								<td><ezf:label name="dsCtacPsnSaltNm_A1" ezfName="dsCtacPsnSaltNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
								<td><ezf:inputText name="dsCtacPsnTtlNm_A1" ezfName="dsCtacPsnTtlNm_A1" value="XXXXXXXXX1XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
								<td><ezf:inputText name="dsCtacPntValTxt_A2" ezfName="dsCtacPntValTxt_A2" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"320\""/></td>
								<td><ezf:inputText name="dsCtacPntValTxt_A1" ezfName="dsCtacPntValTxt_A1" value="XXXXXXXXX1XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\""/></td>
								<td><ezf:inputText name="dsCtacPsnExtnNum_A1" ezfName="dsCtacPsnExtnNum_A1" value="XXXXXXXXX1XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"10\""/></td>
								<td><ezf:inputText name="dsCtacPntValTxt_A3" ezfName="dsCtacPntValTxt_A3" value="XXXXXXXXX1XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\""/></td>
								<td><ezf:inputText name="dsCtacPntValTxt_A4" ezfName="dsCtacPntValTxt_A4" value="XXXXXXXXX1XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\""/></td>
								<td><ezf:inputText name="dsCtacPntValTxt_A5" ezfName="dsCtacPntValTxt_A5" value="XXXXXXXXX1XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\""/></td>
								<td><ezf:inputText name="dsCtacPntTpNm_A1" ezfName="dsCtacPntTpNm_A1" value="XXXXXXXXX1XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\""/></td>
								<td><ezf:inputText name="ctacPsnCmntTxt_A1" ezfName="ctacPsnCmntTxt_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"1000\""/></td>
								<td><ezf:inputText name="billToCustCd_A1" ezfName="billToCustCd_A1" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\""/></td>
								<td><ezf:inputText name="shipToCustCd_A1" ezfName="shipToCustCd_A1" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\""/></td>
						</tr>
						</ezf:row>
						<ezf:skip>
						</ezf:skip>
						</tbody>
					</table>
				</div>
				
				<% if ("M".equals(((NMAL6770Bean)databean).getXxModeCd_H1())) { %>
				<%-- ######################################## Selected Rows ###################################### --%>
				<table border="0" cellpadding="0" cellspacing="0" style="margin-top:10px;">
					<col width="885">
					<col width="60">
					<col width="60">
					<tr>
						<td class="stab">Selected Contact</td>
						<td><ezf:inputButton name="AddSelected" value="Add" htmlClass="pBtn3" /></td>
						<td><ezf:inputButton name="DeleteSelected" value="Delete" htmlClass="pBtn3" /></td>
					</tr>
				</table>
				<div id="topTBL_B" align="left" style="overflow-x:hidden; width:983; overflow-y:none; height:20;">
					<table border="1" cellpadding="1" cellspacing="0" width="2252" style="table-layout:fixed;">
						<col width="23">
						<col width="80">
						<col width="80">
						<col width="150">
						<col width="80">
						<col width="185">
						<col width="150">
						<col width="115">
						<col width="115">
						<col width="65">
						<col width="115">
						<col width="150">
						<col width="95">
						<col width="55">
						<col width="95">
						<col width="95">
						<col width="95">
						<col width="95">
						<col width="197">
						<col width="80">
						<col width="82">
						<tr>
							<td class="pClothBs">&nbsp;</td>
							<td class="pClothBs">Contact ID</td>
							<td class="pClothBs">Account#</td>
							<td class="pClothBs">Account Name</td>
							<td class="pClothBs">Location#</td>
							<td class="pClothBs">Address</td>
							<td class="pClothBs">Relationship to CSA</td>
							<td class="pClothBs">First Name</td>
							<td class="pClothBs">Last Name</td>
							<td class="pClothBs">Salutation</td>
							<td class="pClothBs">Title</td>
							<td class="pClothBs">E-Mail Address</td>
							<td class="pClothBs">Phone-Work</td>
							<td class="pClothBs">Ext</td>
							<td class="pClothBs">Phone-Fax</td>
							<td class="pClothBs">Phone-Assistant</td>
							<td class="pClothBs">Phone-Mobile</td>
							<td class="pClothBs">Primary Contact Type</td>
							<td class="pClothBs">Note</td>
							<td class="pClothBs">Bill To Code</td>
							<td class="pClothBs">Ship To Code</td>
						</tr>
					</table>
				</div>
				<div id="bottomTBL_B" style="overflow:scroll; width:1000; height:92; word-break:break-all;" onscroll="synchroBottomScroll_B();">
					<table border="1" cellpadding="1" cellspacing="0" id="B" width="2252" style="table-layout:fixed;">
						<col width="23">
						<col width="80">
						<col width="80">
						<col width="150">
						<col width="80">
						<col width="185">
						<col width="150">
						<col width="115">
						<col width="115">
						<col width="65">
						<col width="115">
						<col width="150">
						<col width="95">
						<col width="55">
						<col width="95">
						<col width="95">
						<col width="95">
						<col width="95">
						<col width="197">
						<col width="80">
						<col width="82">
						<tbody>
						<ezf:row ezfHyo="B">
							<tr>
								<td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
								<td><ezf:label name="ctacPsnNum_B1" ezfName="ctacPsnNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
								<td><ezf:inputText name="dsAcctNum_B1" ezfName="dsAcctNum_B1" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"28\""/></td>
								<td><ezf:inputText name="dsAcctNm_B1" ezfName="dsAcctNm_B1" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\""/></td>
								<td><ezf:inputText name="locNum_B1" ezfName="locNum_B1" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\""/></td>
								<td><ezf:inputText name="xxDplyByItemNm_B1" ezfName="xxDplyByItemNm_B1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"250\""/></td>
								<!-- Add Start 2017/12/06 Hd.Sugawara QC#21897 -->
								<!-- <td><input type="text" class="pPro" size="20" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2" name="ctacTpNm_B1" ezfname="ctacTpNm_B1" ezfhyo="B"></td> -->
								<td><ezf:inputText name="ctacTpDescTxt_B1" ezfName="ctacTpDescTxt_B1" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
								<!-- Add End   2017/12/06 Hd.Sugawara QC#21897 -->
								<td><ezf:inputText name="ctacPsnFirstNm_B1" ezfName="ctacPsnFirstNm_B1" value="XXXXXXXXX1XXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
								<td><ezf:inputText name="ctacPsnLastNm_B1" ezfName="ctacPsnLastNm_B1" value="XXXXXXXXX1XXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
								<td><ezf:label name="dsCtacPsnSaltNm_B1" ezfName="dsCtacPsnSaltNm_B1" ezfHyo="B" ezfArrayNo="0" /></td>
								<td><ezf:inputText name="dsCtacPsnTtlNm_B1" ezfName="dsCtacPsnTtlNm_B1" value="XXXXXXXXX1XXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
								<td><ezf:inputText name="dsCtacPntValTxt_B2" ezfName="dsCtacPntValTxt_B2" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"320\""/></td>
								<td><ezf:inputText name="dsCtacPntValTxt_B1" ezfName="dsCtacPntValTxt_B1" value="XXXXXXXXX1XXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\""/></td>
								<td><ezf:inputText name="dsCtacPsnExtnNum_B1" ezfName="dsCtacPsnExtnNum_B1" value="XXXXXXXXX1XXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"10\""/></td>
								<td><ezf:inputText name="dsCtacPntValTxt_B3" ezfName="dsCtacPntValTxt_B3" value="XXXXXXXXX1XXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\""/></td>
								<td><ezf:inputText name="dsCtacPntValTxt_B4" ezfName="dsCtacPntValTxt_B4" value="XXXXXXXXX1XXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\""/></td>
								<td><ezf:inputText name="dsCtacPntValTxt_B5" ezfName="dsCtacPntValTxt_B5" value="XXXXXXXXX1XXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\""/></td>
								<td><ezf:inputText name="dsCtacPntTpNm_B1" ezfName="dsCtacPntTpNm_B1" value="XXXXXXXXX1XXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\""/></td>
								<td><ezf:inputText name="ctacPsnCmntTxt_B1" ezfName="ctacPsnCmntTxt_B1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"1000\""/></td>
								<td><ezf:inputText name="billToCustCd_B1" ezfName="billToCustCd_B1" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\""/></td>
								<td><ezf:inputText name="shipToCustCd_B1" ezfName="shipToCustCd_B1" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\""/></td>
						</tr>
						</ezf:row>
						<ezf:skip>
						</ezf:skip>
						</tbody>
					</table>
				</div>
				<% } %>
				
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
	function synchroBottomScroll_B() {
		var bottomTBL = document.getElementById( 'bottomTBL_B' );
		var topTBL    = document.getElementById( 'topTBL_B'    );
		topTBL.scrollLeft = bottomTBL.scrollLeft;
	}
</script>


<%-- **** Task specific area ends here **** --%>
