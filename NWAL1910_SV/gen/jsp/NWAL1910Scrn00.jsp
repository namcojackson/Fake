<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180920153059 --%>
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
			<input type="hidden" name="pageID" value="NWAL1910Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price History">

			<left>
				<table style="width=100%; table-layout:fixed; margin:10px">
					<table width="520px" style="table-layout:fixed">
						<col width="80" align="right">
						<col width="60" align="left">
						<col width="60" align="right">
						<col width="120" align="left">
						<col width="40" align="right">
						<col width="120" align="left">
						<col width="40" align="right">
							<tr>
								<td>Trx Number</td>
								<td class="pOut" colspan="2" align="left"><ezf:inputText name="ordSrcRefNum" ezfName="ordSrcRefNum" value="20010865" otherAttr=" size=\"20\" maxlength=\"30\" style=\"border:none; background-color:transparent\""/></td>
								<td class="stab" align="right">Config Line#</td>
								<td class="pOut" align="left"><ezf:inputText name="dsOrdPosnNum" ezfName="dsOrdPosnNum" value="1" otherAttr=" size=\"5\" maxlength=\"5\" style=\"border:none; background-color:transparent\""/></td>
								<td class="stab" align="right">Line Number</td>
								<td class="pOut" align="left"><ezf:inputText name="xxLineNum" ezfName="xxLineNum" value="1.1" otherAttr=" size=\"5\" maxlength=\"5\" style=\"border:none; background-color:transparent\""/></td>
							</tr>
					</table>
				</table>
			</left>
			<hr>
			<!-- pagenation start-->
			<table width="100%">
				<tr>
					<td align="right">
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
			<!-- pagenation end-->
			<table border="1" cellpadding="1" cellspacing="0" width="970" style="table-layout:fixed; margin-left:20px">
				<col width="80" align="center">			<!-- Update Date -->
				<col width="40" align="center">			<!-- Update By -->
				<col width="40" align="center">			<!-- Line# -->
				<col width="60" align="center">			<!-- Item# -->
				<col width="160" align="center">		<!-- Item Description -->
				<col width="70" align="center">			<!-- Pricing Group -->
				<col width="50" align="center">			<!-- Price -->
				<tr height="24">
					<td  class="pClothBs">Update Date</td>
					<td  class="pClothBs">Update By</td>
					<td  class="pClothBs">Line#</td>
					<td  class="pClothBs">Item#</td>
					<td  class="pClothBs">Item Description</td>
					<td  class="pClothBs">Pricing Group</td>
					<td  class="pClothBs">Price</td>
				</tr>
			</table>
			<table border="1" cellpadding="1" cellspacing="0" width="970" style="table-layout:fixed;margin-left:20px" id="A">
				<col width="80" align="left">		<!-- Update Date -->
				<col width="40" align="left">		<!-- Update By -->
				<col width="40" align="left">		<!-- Line# -->
				<col width="60" align="right">		<!-- Item# -->
				<col width="160" align="left">		<!-- Item Description -->
				<col width="70" align="left">		<!-- Pricing Group -->
				<col width="50" align="right">		<!-- Price -->
				<ezf:row ezfHyo="A">
				<tr height="21">
					<td><ezf:inputText name="xxDtTm_A" ezfName="xxDtTm_A" value="mm/dd/yyyy hh:MM:ss" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"border:none; background-color:transparent\" size=\"20\" maxlength=\"30\""/></td>
					<td><ezf:inputText name="xxSrUsrId_A" ezfName="xxSrUsrId_A" value="Q99999" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"border:none; background-color:transparent\" size=\"10\" maxlength=\"20\""/></td>
					<td><ezf:inputText name="xxLineNum_A" ezfName="xxLineNum_A" value="1.1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"20\" style=\"border:none; background-color:transparent\""/></td>
					<td><ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="0891B001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" style=\"border:none; background-color:transparent\""/></td>
					<td><ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="PFI-101B (POS)" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"44\" maxlength=\"100\" style=\"border:none; background-color:transparent\""/></td>
					<td><ezf:inputText name="prcDtlGrpDescTxt_A" ezfName="prcDtlGrpDescTxt_A" value="Rounding Factor 0" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"border:none; background-color:transparent\""/></td>
					<td><ezf:inputText name="unitPrcAmt_A" ezfName="unitPrcAmt_A" value="128.98" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"border:none; background-color:transparent; text-align:right\" size=\"10\" maxlength=\"20\""/></td>
				</tr>
				</ezf:row>
				<ezf:skip>
				<tr class="pEvenNumberBGcolor" height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">Rounding Factor 0</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				<tr height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">BasePrice</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				<tr class="pEvenNumberBGcolor" height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">BasePrice</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				<tr height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">BasePrice</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				<tr class="pEvenNumberBGcolor" height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">BasePrice</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				<tr height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">BasePrice</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				<tr class="pEvenNumberBGcolor" height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">BasePrice</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				<tr height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">BasePrice</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				<tr class="pEvenNumberBGcolor" height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">BasePrice</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				<tr height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">BasePrice</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				<tr class="pEvenNumberBGcolor" height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">BasePrice</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				<tr height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">BasePrice</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				<tr class="pEvenNumberBGcolor" height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">BasePrice</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				<tr height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">BasePrice</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				<tr class="pEvenNumberBGcolor" height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">BasePrice</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				<tr height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">BasePrice</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				<tr class="pEvenNumberBGcolor" height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">BasePrice</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				<tr height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">BasePrice</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				<tr class="pEvenNumberBGcolor" height="21">
					<td><label  name="xxDtTm_A" ezfname="xxDtTm_A">mm/dd/yyyy hh:MM:ss</label></td>
					<td><label  name="xxSrUsrId_A" ezfname="xxSrUsrId_A">Q99999</label></td>
					<td><label  name="xxLineNum_A" ezfname="xxLineNum_A">1.1</label></td>
					<td><label  name="mdseCd_A" ezfname="mdseCd_A">0891B001</label></td>
					<td><label  name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A">PFI-101B (POS)</label></td>
					<td><label  name="prcDtlGrpDescTxt_A" ezfname="prcDtlGrpDescTxt_A">BasePrice</label></td>
					<td><label  name="unitPrcAmt_A" ezfname="unitPrcAmt_A">128.98</label></td>
				</tr>
				</ezf:skip>
			</table>

<%-- **** Task specific area ends here **** --%>
