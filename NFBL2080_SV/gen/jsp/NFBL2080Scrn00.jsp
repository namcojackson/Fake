<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190521145220 --%>
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

<%@ page import="business.servlet.NFBL2080.NFBL2080BMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%  NFBL2080BMsg bMsg = (NFBL2080BMsg)databean.getEZDBMsg(); %>
		<!-- Set Page ID  -->
		<input type="hidden" name="pageID" value="NFBL2080Scrn00">
		<!-- Set Page Name -->
		<input type="hidden" name="pageName" value="AP Invoice IF Correction">
<center>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td>
<%-- include S21BusinessProcessTAB --%>
<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                <!-- ###TAB - BODY -->
                <div class="pTab_BODY">
<div style="height:70px;">
	<table cellpadding="1" cellspacing="1" border="0" width="1090">
		<col width="95">
		<col width="1">
		<col width="105">
		<col width="5">
		<col width="105">
		<col width="8">
		<col width="85">
		<col width="5">
		<col width="105">
		<col width="5">
		<col width="125">
		<col width="5">
		<col width="75">
		<col width="5">
		<col width="70">
		<col width="5">
		<col width="65">
		<col width="5">
		<col width="70">
		<col width="5">
		<col width="65">
		<tr>
			<td class="stab">Supplier Site Code</td>
			<td></td>
			<td class="fontMin" align="left" colspan="3"><ezf:inputText name="vndCd" ezfName="vndCd" value="WWWWWW" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
			<td></td>
			<td class="stab">Process Status</td>
			<td></td>
			<td class="fontMin" align="left">
				<ezf:select name="vndInvProcStsCd_P1" ezfName="vndInvProcStsCd_P1" ezfBlank="1" ezfCodeName="vndInvProcStsCd" ezfDispName="vndInvProcStsNm" otherAttr=" style=\"width:100px;\" tabindex=\"-1\""/>
			</td>
			<td></td>
			<td class="stab">CSMP Process Status</td>
			<td></td>
			<td class="fontMin" align="left">
				<ezf:select name="invArProcStsCd_P1" ezfName="invArProcStsCd_P1" ezfBlank="1" ezfCodeName="invArProcStsCd" ezfDispName="invArProcStsDescTxt" otherAttr=" style=\"width:100px;\" tabindex=\"-1\""/>
			</td>
			<td></td>
			<td class="stab">Supplier INV#</td>
			<td></td>
			<td class="fontMin" align="left"><ezf:inputText name="vndInvNum" ezfName="vndInvNum" value="WWWWWW" otherAttr=" size=\"10\" maxlength=\"15\" ezftoupper=\"\""/></td>
			<td></td>
			<td class="stab">Supplier SO#</td>
			<td></td>
			<td class="fontMin" align="left"><ezf:inputText name="soNum" ezfName="soNum" value="WWWWWW" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
		</tr>
		<tr>
			<td class="stab">EDI Rcv Date</td>
			<td></td>
			<td class="fontMin" align="left">
				<ezf:inputText name="xxCratDt_S1" ezfName="xxCratDt_S1" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\""/>
				<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxCratDt_S1', 4);" >
			</td>
			<td class="fontMin" align="center">-</td>
			<td class="fontMin" align="right">
				<ezf:inputText name="xxCratDt_S2" ezfName="xxCratDt_S2" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\""/>
				<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxCratDt_S2', 4);" >
			</td>
			<td></td>
			<td class="stab">PO#</td>
			<td></td>
			<td class="fontMin" align="left"><ezf:inputText name="poOrdNum" ezfName="poOrdNum" value="WWWWWW" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
			<td></td>
			<td class="stab">EDI PO#</td>
			<td></td>
			<td class="fontMin" align="left"><ezf:inputText name="ediPoOrdNum" ezfName="ediPoOrdNum" value="WWWWWW" otherAttr=" size=\"10\" maxlength=\"35\" ezftoupper=\"\""/></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td class="stab">Message</td>
			<td></td>
			<td class="fontMin" colspan="8" align="left"><ezf:inputText name="batErrMsgTxt" ezfName="batErrMsgTxt" value="WWWWWW" otherAttr=" size=\"50\" maxlength=\"400\""/></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td colspan="9" align="right">
				<ezf:inputButton name="Click_Search" value="Search" htmlClass="pBtn1" otherAttr=" id=\"Click_Search\" style=\"width: 80px;\""/>
				<ezf:inputButton name="Click_Reprocess" value="Reprocess" htmlClass="pBtn2" otherAttr=" id=\"Click_Reprocess\" style=\"width: 80px;\""/>
				<ezf:inputButton name="Click_Cancel" value="Cancel" htmlClass="pBtn3" otherAttr=" id=\"Click_Cancel\" style=\"width: 80px;\""/>
			</td>
		</tr>
	</table>
</div>
<HR width="100%">
<div>
	<table  cellpadding="0" cellspacing="0" border="0" width="1130">
		<td align="left">
			<ezf:inputButton name="Check_All" value="ChechAll" htmlClass="pBtn4" otherAttr=" id=\"Check_All\" style=\"width: 80px;\""/>
			<ezf:inputButton name="Un_Check_All" value="UncheckAll" htmlClass="pBtn5" otherAttr=" id=\"Un_Check_All\" style=\"width: 80px;\""/>
		</td>
		<td align="right">
			<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
				<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
				<jsp:param name="TableNm"     value="A" />
				<jsp:param name="ShowingFrom" value="xxPageShowFromNum_P1" />
				<jsp:param name="ShowingTo"   value="xxPageShowToNum_P1" />
				<jsp:param name="ShowingOf"   value="xxPageShowOfNum_P1" />
			</jsp:include>
		</td>
	</table>
</div>
<div id="midHdr" style="overflow-x:hidden;width:1110px;">
	<table border="1" cellpadding="0" cellspacing="0" style="width:1185px;">
		<col width="20" align="center">
		<col width="180" align="center">
		<col width="110" align="center">
		<col width="90" align="center">
		<col width="100" align="center">
		<col width="70" align="center">
		<col width="100" align="center">
		<col width="75" align="center">
		<col width="140" align="center">
		<col width="250" align="center">
		<tr>
			<td class="pClothBs">&nbsp;</td>
			<td class="pClothBs">Supplier Site Name</td>
			<td class="pClothBs">Supplier INV#</td>
			<td class="pClothBs">Proc Status</td>
			<td class="pClothBs">CSMP Proc Status</td>
			<td class="pClothBs">PO#</td>
			<td class="pClothBs">EDI PO#</td>
			<td class="pClothBs">EDI Rcv Date</td>
			<td class="pClothBs">Amount</td>
			<td class="pClothBs">Message</td>
		</tr>
	</table>
</div>
<div id="midBdy" style="width:1126px; height:222; display:block; overflow:scroll; margin:0px; padding:0px;" 
	onScroll="synchroScrollLeft(this.id, new Array('midHdr'));">
	<table border="1" cellpadding="0" cellspacing="0" style="width:1185px;word-break:break-all;able-layout:fixed;" id="A">
		<col width="20" align="center">
		<col width="180" align="center">
		<col width="110" align="left">
		<col width="90" align="left">
		<col width="100" align="left">
		<col width="70" align="center">
		<col width="100" align="center">
		<col width="75" align="left">
		<col width="140" align="right">
		<col width="250" align="center">
		<tbody>
			<ezf:row ezfHyo="A">
				<tr id="id_row{EZF_ROW_NUMBER}">
					<td align="center"><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_B#{EZF_ROW_NUMBER}\" tabindex=\"4001\""/></td>
					<td><ezf:inputText name="locNm_A1" ezfName="locNm_A1" value="STAPLES" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"60\" tabindex=\"-1\" ezftoupper=\"\""/></td>
					<td><div id="vndInvNum_A#{EZF_ROW_NUMBER}"><ezf:anchor name="Click_vndInvNum" ezfName="Click_vndInvNum" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Click_vndInvNum" otherAttr=" id=\"vndInvNum_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\" tabindex=\"4001\""><ezf:label name="vndInvNum_A1" ezfName="vndInvNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></div></td>
					<td><ezf:label name="vndInvProcStsCd_A1" ezfName="vndInvProcStsCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
					<td><ezf:label name="invArProcStsCd_A1" ezfName="invArProcStsCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
					<td><ezf:inputText name="poOrdNum_A1" ezfName="poOrdNum_A1" value="2211 CONSTRUCTORA ATCO" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" tabindex=\"-1\" ezftoupper=\"\""/></td>
					<td><ezf:inputText name="ediPoOrdNum_A1" ezfName="ediPoOrdNum_A1" value="2211 CONSTRUCTORA ATCO" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"35\" tabindex=\"-1\" ezftoupper=\"\""/></td>
					<td><ezf:label name="xxCratDt_A1" ezfName="xxCratDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
					<td><ezf:label name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
					<td><ezf:select name="batErrMsgTxt_AP" ezfName="batErrMsgTxt_AP" ezfHyo="A" ezfArrayNo="0" ezfCodeName="batErrMsgTxt_AC" ezfDispName="batErrMsgTxt_AD" ezfCodeDispHyo="A" otherAttr=" style=\"width:243px;\" tabindex=\"-1\""/></td>
				</tr>
			</ezf:row>
			<ezf:skip>
				<tr id="id_row{EZF_ROW_NUMBER}">
					<td align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_B#{EZF_ROW_NUMBER}" tabindex="4001"></td>
					<td><input type="text" size="24" maxlength="60" value="STAPLES" name="locNm_A1" ezfname="locNm_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><div id="vndInvNum_A#{EZF_ROW_NUMBER}"><a id="vndInvNum_A#{EZF_ROW_NUMBER}" href="#" name="Click_vndInvNum" ezfName="Click_vndInvNum" ezfHyo="A" ezfanchortext onclick="sendServer('Click_vndInvNum')" tabindex="4001" ><label ezfout name="vndInvNum_A1" ezfName="vndInvNum_A1" ezfHyo="A">11031080</label></a></div></td>
					<td><label ezfout name="vndInvProcStsCd_A1" ezfName="vndInvProcStsCd_A1" ezfHyo="A">S:STATUS</label></td>
					<td><label ezfout name="invArProcStsCd_A1" ezfName="invArProcStsCd_A1" ezfHyo="A">S:STATUS</label></td>
					<td><input type="text" size="8" maxlength="8" value="2211 CONSTRUCTORA ATCO" name="poOrdNum_A1" ezfname="poOrdNum_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><input type="text" size="12" maxlength="35" value="2211 CONSTRUCTORA ATCO" name="ediPoOrdNum_A1" ezfname="ediPoOrdNum_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><label ezfout name="xxCratDt_A1" ezfName="xxCratDt_A1" ezfhyo="A">2016/02/17</label></td>
					<td><label ezfout name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" ezfHyo="A">1124783.32</label></td>
					<td><select style="width:243px;" name="batErrMsgTxt_AP" ezfname="batErrMsgTxt_AP" ezflist="batErrMsgTxt_AC,batErrMsgTxt_AD,99,A ,blank" tabindex="-1" ezfHyo="A"><option>01:Regular Receipt</option></select></td>
				</tr>
				<tr id="id_row{EZF_ROW_NUMBER}">
					<td align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_B#{EZF_ROW_NUMBER}" tabindex="4001"></td>
					<td><input type="text" size="24" maxlength="60" value="STAPLES" name="locNm_A1" ezfname="locNm_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><div id="vndInvNum_A#{EZF_ROW_NUMBER}"><a id="vndInvNum_A#{EZF_ROW_NUMBER}" href="#" name="Click_vndInvNum" ezfName="Click_vndInvNum" ezfHyo="A" ezfanchortext onclick="sendServer('Click_vndInvNum')" tabindex="4001" ><label ezfout name="vndInvNum_A1" ezfName="vndInvNum_A1" ezfHyo="A">11031080</label></a></div></td>
					<td><label ezfout name="vndInvProcStsCd_A1" ezfName="vndInvProcStsCd_A1" ezfHyo="A">S:STATUS</label></td>
					<td><label ezfout name="invArProcStsCd_A1" ezfName="invArProcStsCd_A1" ezfHyo="A">S:STATUS</label></td>
					<td><input type="text" size="8" maxlength="8" value="2211 CONSTRUCTORA ATCO" name="poOrdNum_A1" ezfname="poOrdNum_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><input type="text" size="12" maxlength="35" value="2211 CONSTRUCTORA ATCO" name="ediPoOrdNum_A1" ezfname="ediPoOrdNum_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><label ezfout name="xxCratDt_A1" ezfName="xxCratDt_A1" ezfhyo="A">2016/02/17</label></td>
					<td><label ezfout name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" ezfHyo="A">1124783.32</label></td>
					<td><select style="width:243px;" name="batErrMsgTxt_AP" ezfname="batErrMsgTxt_AP" ezflist="batErrMsgTxt_AC,batErrMsgTxt_AD,99,A ,blank" tabindex="-1" ezfHyo="A"><option>01:Regular Receipt</option></select></td>
				</tr>
				<tr id="id_row{EZF_ROW_NUMBER}">
					<td align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_B#{EZF_ROW_NUMBER}" tabindex="4001"></td>
					<td><input type="text" size="24" maxlength="60" value="STAPLES" name="locNm_A1" ezfname="locNm_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><div id="vndInvNum_A#{EZF_ROW_NUMBER}"><a id="vndInvNum_A#{EZF_ROW_NUMBER}" href="#" name="Click_vndInvNum" ezfName="Click_vndInvNum" ezfHyo="A" ezfanchortext onclick="sendServer('Click_vndInvNum')" tabindex="4001" ><label ezfout name="vndInvNum_A1" ezfName="vndInvNum_A1" ezfHyo="A">11031080</label></a></div></td>
					<td><label ezfout name="vndInvProcStsCd_A1" ezfName="vndInvProcStsCd_A1" ezfHyo="A">S:STATUS</label></td>
					<td><label ezfout name="invArProcStsCd_A1" ezfName="invArProcStsCd_A1" ezfHyo="A">S:STATUS</label></td>
					<td><input type="text" size="8" maxlength="8" value="2211 CONSTRUCTORA ATCO" name="poOrdNum_A1" ezfname="poOrdNum_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><input type="text" size="12" maxlength="35" value="2211 CONSTRUCTORA ATCO" name="ediPoOrdNum_A1" ezfname="ediPoOrdNum_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><label ezfout name="xxCratDt_A1" ezfName="xxCratDt_A1" ezfhyo="A">2016/02/17</label></td>
					<td><label ezfout name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" ezfHyo="A">1124783.32</label></td>
					<td><select style="width:243px;" name="batErrMsgTxt_AP" ezfname="batErrMsgTxt_AP" ezflist="batErrMsgTxt_AC,batErrMsgTxt_AD,99,A ,blank" tabindex="-1" ezfHyo="A"><option>01:Regular Receipt</option></select></td>
				</tr>
				<tr id="id_row{EZF_ROW_NUMBER}">
					<td align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_B#{EZF_ROW_NUMBER}" tabindex="4001"></td>
					<td><input type="text" size="24" maxlength="60" value="STAPLES" name="locNm_A1" ezfname="locNm_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><div id="vndInvNum_A#{EZF_ROW_NUMBER}"><a id="vndInvNum_A#{EZF_ROW_NUMBER}" href="#" name="Click_vndInvNum" ezfName="Click_vndInvNum" ezfHyo="A" ezfanchortext onclick="sendServer('Click_vndInvNum')" tabindex="4001" ><label ezfout name="vndInvNum_A1" ezfName="vndInvNum_A1" ezfHyo="A">11031080</label></a></div></td>
					<td><label ezfout name="vndInvProcStsCd_A1" ezfName="vndInvProcStsCd_A1" ezfHyo="A">S:STATUS</label></td>
					<td><label ezfout name="invArProcStsCd_A1" ezfName="invArProcStsCd_A1" ezfHyo="A">S:STATUS</label></td>
					<td><input type="text" size="8" maxlength="8" value="2211 CONSTRUCTORA ATCO" name="poOrdNum_A1" ezfname="poOrdNum_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><input type="text" size="12" maxlength="35" value="2211 CONSTRUCTORA ATCO" name="ediPoOrdNum_A1" ezfname="ediPoOrdNum_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><label ezfout name="xxCratDt_A1" ezfName="xxCratDt_A1" ezfhyo="A">2016/02/17</label></td>
					<td><label ezfout name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" ezfHyo="A">1124783.32</label></td>
					<td><select style="width:243px;" name="batErrMsgTxt_AP" ezfname="batErrMsgTxt_AP" ezflist="batErrMsgTxt_AC,batErrMsgTxt_AD,99,A ,blank" tabindex="-1" ezfHyo="A"><option>01:Regular Receipt</option></select></td>
				</tr>
				<tr id="id_row{EZF_ROW_NUMBER}">
					<td align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_B#{EZF_ROW_NUMBER}" tabindex="4001"></td>
					<td><input type="text" size="24" maxlength="60" value="STAPLES" name="locNm_A1" ezfname="locNm_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><div id="vndInvNum_A#{EZF_ROW_NUMBER}"><a id="vndInvNum_A#{EZF_ROW_NUMBER}" href="#" name="Click_vndInvNum" ezfName="Click_vndInvNum" ezfHyo="A" ezfanchortext onclick="sendServer('Click_vndInvNum')" tabindex="4001" ><label ezfout name="vndInvNum_A1" ezfName="vndInvNum_A1" ezfHyo="A">11031080</label></a></div></td>
					<td><label ezfout name="vndInvProcStsCd_A1" ezfName="vndInvProcStsCd_A1" ezfHyo="A">S:STATUS</label></td>
					<td><label ezfout name="invArProcStsCd_A1" ezfName="invArProcStsCd_A1" ezfHyo="A">S:STATUS</label></td>
					<td><input type="text" size="8" maxlength="8" value="2211 CONSTRUCTORA ATCO" name="poOrdNum_A1" ezfname="poOrdNum_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><input type="text" size="12" maxlength="35" value="2211 CONSTRUCTORA ATCO" name="ediPoOrdNum_A1" ezfname="ediPoOrdNum_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><label ezfout name="xxCratDt_A1" ezfName="xxCratDt_A1" ezfhyo="A">2016/02/17</label></td>
					<td><label ezfout name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" ezfHyo="A">1124783.32</label></td>
					<td><select style="width:243px;" name="batErrMsgTxt_AP" ezfname="batErrMsgTxt_AP" ezflist="batErrMsgTxt_AC,batErrMsgTxt_AD,99,A ,blank" tabindex="-1" ezfHyo="A"><option>01:Regular Receipt</option></select></td>
				</tr>
				<tr id="id_row{EZF_ROW_NUMBER}">
					<td align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_B#{EZF_ROW_NUMBER}" tabindex="4001"></td>
					<td><input type="text" size="24" maxlength="60" value="STAPLES" name="locNm_A1" ezfname="locNm_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><div id="vndInvNum_A#{EZF_ROW_NUMBER}"><a id="vndInvNum_A#{EZF_ROW_NUMBER}" href="#" name="Click_vndInvNum" ezfName="Click_vndInvNum" ezfHyo="A" ezfanchortext onclick="sendServer('Click_vndInvNum')" tabindex="4001" ><label ezfout name="vndInvNum_A1" ezfName="vndInvNum_A1" ezfHyo="A">11031080</label></a></div></td>
					<td><label ezfout name="vndInvProcStsCd_A1" ezfName="vndInvProcStsCd_A1" ezfHyo="A">S:STATUS</label></td>
					<td><label ezfout name="invArProcStsCd_A1" ezfName="invArProcStsCd_A1" ezfHyo="A">S:STATUS</label></td>
					<td><input type="text" size="8" maxlength="8" value="2211 CONSTRUCTORA ATCO" name="poOrdNum_A1" ezfname="poOrdNum_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><input type="text" size="12" maxlength="35" value="2211 CONSTRUCTORA ATCO" name="ediPoOrdNum_A1" ezfname="ediPoOrdNum_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><label ezfout name="xxCratDt_A1" ezfName="xxCratDt_A1" ezfhyo="A">2016/02/17</label></td>
					<td><label ezfout name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" ezfHyo="A">1124783.32</label></td>
					<td><select style="width:243px;" name="batErrMsgTxt_AP" ezfname="batErrMsgTxt_AP" ezflist="batErrMsgTxt_AC,batErrMsgTxt_AD,99,A ,blank" tabindex="-1" ezfHyo="A"><option>01:Regular Receipt</option></select></td>
				</tr>
				<tr id="id_row{EZF_ROW_NUMBER}">
					<td align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_B#{EZF_ROW_NUMBER}" tabindex="4001"></td>
					<td><input type="text" size="24" maxlength="60" value="STAPLES" name="locNm_A1" ezfname="locNm_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><div id="vndInvNum_A#{EZF_ROW_NUMBER}"><a id="vndInvNum_A#{EZF_ROW_NUMBER}" href="#" name="Click_vndInvNum" ezfName="Click_vndInvNum" ezfHyo="A" ezfanchortext onclick="sendServer('Click_vndInvNum')" tabindex="4001" ><label ezfout name="vndInvNum_A1" ezfName="vndInvNum_A1" ezfHyo="A">11031080</label></a></div></td>
					<td><label ezfout name="vndInvProcStsCd_A1" ezfName="vndInvProcStsCd_A1" ezfHyo="A">S:STATUS</label></td>
					<td><label ezfout name="invArProcStsCd_A1" ezfName="invArProcStsCd_A1" ezfHyo="A">S:STATUS</label></td>
					<td><input type="text" size="8" maxlength="8" value="2211 CONSTRUCTORA ATCO" name="poOrdNum_A1" ezfname="poOrdNum_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><input type="text" size="12" maxlength="35" value="2211 CONSTRUCTORA ATCO" name="ediPoOrdNum_A1" ezfname="ediPoOrdNum_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><label ezfout name="xxCratDt_A1" ezfName="xxCratDt_A1" ezfhyo="A">2016/02/17</label></td>
					<td><label ezfout name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" ezfHyo="A">1124783.32</label></td>
					<td><select style="width:243px;" name="batErrMsgTxt_AP" ezfname="batErrMsgTxt_AP" ezflist="batErrMsgTxt_AC,batErrMsgTxt_AD,99,A ,blank" tabindex="-1" ezfHyo="A"><option>01:Regular Receipt</option></select></td>
				</tr>
				<tr id="id_row{EZF_ROW_NUMBER}">
					<td align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_B#{EZF_ROW_NUMBER}" tabindex="4001"></td>
					<td><input type="text" size="24" maxlength="60" value="STAPLES" name="locNm_A1" ezfname="locNm_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><div id="vndInvNum_A#{EZF_ROW_NUMBER}"><a id="vndInvNum_A#{EZF_ROW_NUMBER}" href="#" name="Click_vndInvNum" ezfName="Click_vndInvNum" ezfHyo="A" ezfanchortext onclick="sendServer('Click_vndInvNum')" tabindex="4001" ><label ezfout name="vndInvNum_A1" ezfName="vndInvNum_A1" ezfHyo="A">11031080</label></a></div></td>
					<td><label ezfout name="vndInvProcStsCd_A1" ezfName="vndInvProcStsCd_A1" ezfHyo="A">S:STATUS</label></td>
					<td><label ezfout name="invArProcStsCd_A1" ezfName="invArProcStsCd_A1" ezfHyo="A">S:STATUS</label></td>
					<td><input type="text" size="8" maxlength="8" value="2211 CONSTRUCTORA ATCO" name="poOrdNum_A1" ezfname="poOrdNum_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><input type="text" size="12" maxlength="35" value="2211 CONSTRUCTORA ATCO" name="ediPoOrdNum_A1" ezfname="ediPoOrdNum_A1" tabindex="-1" readonly ezftoupper ezfhyo="A"></td>
					<td><label ezfout name="xxCratDt_A1" ezfName="xxCratDt_A1" ezfhyo="A">2016/02/17</label></td>
					<td><label ezfout name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" ezfHyo="A">1124783.32</label></td>
					<td><select style="width:243px;" name="batErrMsgTxt_AP" ezfname="batErrMsgTxt_AP" ezflist="batErrMsgTxt_AC,batErrMsgTxt_AD,99,A ,blank" tabindex="-1" ezfHyo="A"><option>01:Regular Receipt</option></select></td>
				</tr>
			</ezf:skip>
			
		</tbody>
	</table>
</div>
<div align="center">
	<table width="100%">
		<col valign="top">
		<tr>
			<td>
				<div class="pTab_HEAD">
					<ul>
						<li id="Header" title="Header" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_01" ezfEmulateBtn="1" ezfGuard="Header" >Header</ezf:anchor></li>
						<li id="Detail" title="Detail" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_02" ezfEmulateBtn="1" ezfGuard="Detail" >Detail</ezf:anchor></li>
					</ul>
				</div>
				<c:choose>
					<c:when test="${pageScope._ezddatabean.xxDplyTab==''}">
						<script type="text/javascript">
							document.getElementById("Header").className="pTab_OFF";
							document.getElementById("Detail").className="pTab_OFF";
						</script>
						<%-- TAB_Header Start --%>
						<div class="pTab_BODY_In" style="height:195px;border-top:solid 6px #505050;" >
						</div>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${pageScope._ezddatabean.xxDplyTab=='Header'}">
						<script type="text/javascript">
							document.getElementById("Header").className="pTab_ON";
							document.getElementById("Detail").className="pTab_OFF";
						</script>
						<%-- TAB_Header Start --%>
						<div class="pTab_BODY_In" style="height:195px;" >
						<table width="700" align="left" cellpadding="1" cellspacing="1">
							<col width="100">
							<col width="200">
							<col width="100">
							<col width="300">
							<tr>
								<td class="stab">Supplier Site Name</td>
								<td class="stab" align="left"><ezf:inputText name="locNm_H1" ezfName="locNm_H1" value="XXXXXXXXX" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
								<td class="stab">Proc Status</td>
								<td class="stab" align="left"><ezf:inputText name="vndInvProcStsCd_H1" ezfName="vndInvProcStsCd_H1" value="XXXXXXXXX" otherAttr=" size=\"1\" maxlength=\"1\" ezftoupper=\"\""/></td>
							</tr>
							<tr>
								<td class="stab">Supplier INV#</td>
								<td class="stab" align="left"><ezf:inputText name="vndInvNum_H1" ezfName="vndInvNum_H1" value="XXXXXXXXX" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
								<td class="stab">PO#</td>
								<td class="stab" align="left"><ezf:inputText name="poOrdNum_H1" ezfName="poOrdNum_H1" value="XXXXXXXXX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
							</tr>
							<tr>
								<td class="stab">Supplier SO#</td>
								<td class="stab" align="left"><ezf:inputText name="soNum_H1" ezfName="soNum_H1" value="XXXXXXXXX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
								<td class="stab">EDI PO#</td>
								<td class="stab" align="left"><ezf:inputText name="ediPoOrdNum_H1" ezfName="ediPoOrdNum_H1" value="XXXXXXXXX" otherAttr=" size=\"20\" maxlength=\"35\" ezftoupper=\"\""/></td>
							</tr>
							<tr>
								<td class="stab">Customer PO#</td>
								<td class="stab" align="left"><ezf:inputText name="custIssPoNum_H1" ezfName="custIssPoNum_H1" value="XXXXXXXXX" otherAttr=" size=\"20\" maxlength=\"35\" ezftoupper=\"\""/></td>
								<td class="stab">EDI Rcv Date</td>
								<td class="stab" align="left"><ezf:inputText name="xxCratDt_H1" ezfName="xxCratDt_H1" value="XXXXXXXXX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
							</tr>
							<tr>
								<td class="stab">Message</td>
								<td class="stab" align="left" colspan="3">
									<ezf:select name="batErrMsgTxt_HP" ezfName="batErrMsgTxt_HP" ezfCodeName="batErrMsgTxt_HC" ezfDispName="batErrMsgTxt_HD" otherAttr=" style=\"width:250px;\" tabindex=\"-1\""/>
								</td>
							</tr>
						</table>
						</div>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${pageScope._ezddatabean.xxDplyTab=='Detail'}">
						<script type="text/javascript">
							document.getElementById("Header").className="pTab_OFF";
							document.getElementById("Detail").className="pTab_ON";
						</script>
						<%-- TAB_Detail Start --%>
						<div class="pTab_BODY_In" style="height:195px;" >
						<table align="left">
							<tr>
								<td>
									<div id="btmHdr" style="overflow-x:hidden;width:1100px;">
										<table border="1" cellpadding="0" cellspacing="0" style="width:1195px;">
											<col width="80" align="center">
											<col width="80" align="center">
											<col width="140" align="center">
											<col width="170" align="center">
											<col width="75" align="center">
											<col width="100" align="center">
											<col width="80" align="center">
											<col width="80" align="center">
											<col width="320" align="center">
											<tr>
												<td class="pClothBs">Rcv PO Line#</td>
												<td class="pClothBs">SO#</td>
												<td class="pClothBs">Item CD</td>
												<td class="pClothBs">Item NM</td>
												<td class="pClothBs">INV QTY</td>
												<td class="pClothBs">Line Amt</td>
												<td class="pClothBs">PO#</td>
												<td class="pClothBs">EDI PO#</td>
												<td class="pClothBs">Message</td>
											</tr>
										</table>
									</div>
									
									<div id="btmBdy" style="width:1116px; height:163px; display:block; overflow:scroll; margin:0px; padding:0px;"
										onScroll="synchroScrollLeft(this.id, new Array('btmHdr'));">
										<table border="1" cellpadding="0" cellspacing="0" style="width:1195px;word-break:break-all;able-layout:fixed;" id="B">
											<col width="80" align="left">
											<col width="80" align="left">
											<col width="140" align="center">
											<col width="170" align="center">
											<col width="75" align="center">
											<col width="100" align="right">
											<col width="80" align="left">
											<col width="80" align="left">
											<col width="320" align="center">
											<tbody>
												<ezf:row ezfHyo="B">
													<tr id="id_row{EZF_ROW_NUMBER}">
														<td><ezf:inputText name="ediPoOrdDtlLineNum_B1" ezfName="ediPoOrdDtlLineNum_B1" value="001" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"6\" tabindex=\"-1\" ezftoupper=\"\""/></td>
														<td><ezf:label name="soNum_B1" ezfName="soNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
														<td>
															<ezf:inputText name="mdseCd_B1" ezfName="mdseCd_B1" value="STAPLES" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"14\" tabindex=\"-1\" ezftoupper=\"\""/>
															<ezf:inputButton name="OpenWin_Mdse" value="I" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_Mdse#{EZF_ROW_NUMBER}\" style=\"width: 20px;\""/>
														</td>
														<td><ezf:inputText name="mdseNm_B1" ezfName="mdseNm_B1" value="STAPLES" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"23\" tabindex=\"-1\" ezftoupper=\"\""/></td>
														<td><ezf:inputText name="shipQty_B1" ezfName="shipQty_B1" value="3" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"9\" tabindex=\"-1\" ezftoupper=\"\""/></td>
														<td><ezf:label name="dealGrsTotPrcAmt_B1" ezfName="dealGrsTotPrcAmt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
														<% if (ZYPCommonFunc.hasValue(bMsg.xxInvCratFlg_H1)) { %>
															<td>
																<ezf:anchor name="poOrdNum_B1" ezfName="poOrdNum_B1" ezfHyo="B" ezfArrayNo="0" onClick="submitFromPluralFields('Click_VRNum', 'ord', {EZF_ROW_NUMBER}); return false;" otherAttr=" id=\"poOrdNum_B1#{EZF_ROW_NUMBER}\" ezfanchortext=\"\" tabindex=\"4001\"">
																	<ezf:label name="poOrdNum_B1" ezfName="poOrdNum_B1" ezfHyo="B" ezfArrayNo="0" />
																</ezf:anchor>
															</td>
															<td>
																<ezf:anchor name="ediPoOrdNum_B1" ezfName="ediPoOrdNum_B1" ezfHyo="B" ezfArrayNo="0" onClick="submitFromPluralFields('Click_VRNum', 'edi', {EZF_ROW_NUMBER}); return false;" otherAttr=" id=\"ediPoOrdNum_B1#{EZF_ROW_NUMBER}\" ezfanchortext=\"\" tabindex=\"4001\"">
																	<ezf:label name="ediPoOrdNum_B1" ezfName="ediPoOrdNum_B1" ezfHyo="B" ezfArrayNo="0" />
																</ezf:anchor>
															</td>
														<% } else { %>
															<td>
																<ezf:anchor name="poOrdNum_B1" ezfName="poOrdNum_B1" ezfHyo="B" ezfArrayNo="0" onClick="submitFromPluralFields('Click_PoNum', 'ord', {EZF_ROW_NUMBER}); return false;" otherAttr=" id=\"poOrdNum_B1#{EZF_ROW_NUMBER}\" ezfanchortext=\"\" tabindex=\"4001\"">
																	<ezf:label name="poOrdNum_B1" ezfName="poOrdNum_B1" ezfHyo="B" ezfArrayNo="0" />
																</ezf:anchor>
															</td>
															<td>
																<ezf:anchor name="ediPoOrdNum_B1" ezfName="ediPoOrdNum_B1" ezfHyo="B" ezfArrayNo="0" onClick="submitFromPluralFields('Click_PoNum', 'edi', {EZF_ROW_NUMBER}); return false;" otherAttr=" id=\"ediPoOrdNum_B1#{EZF_ROW_NUMBER}\" ezfanchortext=\"\" tabindex=\"4001\"">
																	<ezf:label name="ediPoOrdNum_B1" ezfName="ediPoOrdNum_B1" ezfHyo="B" ezfArrayNo="0" />
																</ezf:anchor>
															</td>
														<% } %>
														<td><ezf:inputText name="batErrMsgTxt_B1" ezfName="batErrMsgTxt_B1" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"44\" tabindex=\"-1\" ezftoupper=\"\""/></td>
													</tr>
												</ezf:row>
												<ezf:skip>
													<tr id="id_row{EZF_ROW_NUMBER}">
														<td><input type="text" size="6" value="001" name="ediPoOrdDtlLineNum_B1" ezfname="ediPoOrdDtlLineNum_B1" tabindex="-1" ezftoupper ezfhyo="B"></td>
														<td><label ezfout name="soNum_B1" ezfName="soNum_B1" ezfHyo="B">S:STATUS</label></td>
														<td>
															<input type="text" size="14" value="STAPLES" name="mdseCd_B1" ezfname="mdseCd_B1" tabindex="-1" readonly ezftoupper ezfhyo="B">
															<input type="button" class="pBtn1" value="I" onclick="sendServer(this)" name="OpenWin_Mdse" ezfhyo="B" id="OpenWin_Mdse#{EZF_ROW_NUMBER}" style="width: 20px;">
														</td>
														<td><input type="text" size="23" value="STAPLES" name="mdseNm_B1" ezfname="mdseNm_B1" tabindex="-1" readonly ezftoupper ezfhyo="B"></td>
														<td><input type="text" size="9" value="3" name="shipQty_B1" ezfname="shipQty_B1" tabindex="-1" ezftoupper ezfhyo="B"></td>
														<td><label ezfout name="dealGrsTotPrcAmt_B1" ezfName="dealGrsTotPrcAmt_B1" ezfHyo="B">S:STATUS</label></td>
														<td><div id="poOrdNum_B#{EZF_ROW_NUMBER}"><a id="poOrdNum_B#{EZF_ROW_NUMBER}" href="#" name="Click_PoNum" ezfName="Click_PoNum" ezfHyo="B" ezfanchortext onclick="submitFromPluralFields('Click_PoNum', 'ord')" tabindex="4001" ><label ezfout name="poOrdNum_B1" ezfName="poOrdNum_B1" ezfHyo="B">11031080</label></a></div></td>
														<td><div id="ediPoOrdNum_B#{EZF_ROW_NUMBER}"><a id="ediPoOrdNum_B#{EZF_ROW_NUMBER}" href="#" name="Click_PoNum" ezfName="Click_PoNum" ezfHyo="B" ezfanchortext onclick="submitFromPluralFields('Click_PoNum', 'edi')" tabindex="4001" ><label ezfout name="ediPoOrdNum_B1" ezfName="ediPoOrdNum_B1" ezfHyo="B">11031080</label></a></div></td>
														<td><input type="text" size="44" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" name="batErrMsgTxt_B1" ezfname="batErrMsgTxt_B1" tabindex="-1" readonly ezftoupper ezfhyo="B"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}">
														<td><input type="text" size="6" value="001" name="ediPoOrdDtlLineNum_B1" ezfname="ediPoOrdDtlLineNum_B1" tabindex="-1" ezftoupper ezfhyo="B"></td>
														<td><label ezfout name="soNum_B1" ezfName="soNum_B1" ezfHyo="B">S:STATUS</label></td>
														<td>
															<input type="text" size="14" value="STAPLES" name="mdseCd_B1" ezfname="mdseCd_B1" tabindex="-1" readonly ezftoupper ezfhyo="B">
															<input type="button" class="pBtn1" value="I" onclick="sendServer(this)" name="OpenWin_Mdse" ezfhyo="B" id="OpenWin_Mdse#{EZF_ROW_NUMBER}" style="width: 20px;">
														</td>
														<td><input type="text" size="23" value="STAPLES" name="mdseNm_B1" ezfname="mdseNm_B1" tabindex="-1" readonly ezftoupper ezfhyo="B"></td>
														<td><input type="text" size="9" value="3" name="shipQty_B1" ezfname="shipQty_B1" tabindex="-1" ezftoupper ezfhyo="B"></td>
														<td><label ezfout name="dealGrsTotPrcAmt_B1" ezfName="dealGrsTotPrcAmt_B1" ezfHyo="B">S:STATUS</label></td>
														<td><label ezfout name="poOrdNum_B1" ezfName="poOrdNum_B1" ezfHyo="B">12345678</label></td>
														<td><div id="ediPoOrdNum_B#{EZF_ROW_NUMBER}"><a id="ediPoOrdNum_B#{EZF_ROW_NUMBER}" href="#" name="Click_PoNum" ezfName="Click_PoNum" ezfHyo="B" ezfanchortext onclick="sendServer('Click_PoNum')" tabindex="4001" ><label ezfout name="ediPoOrdNum_B1" ezfName="ediPoOrdNum_B1" ezfHyo="B">11031080</label></a></div></td>
														<td><input type="text" size="44" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" name="batErrMsgTxt_B1" ezfname="batErrMsgTxt_B1" tabindex="-1" readonly ezftoupper ezfhyo="B"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}">
														<td><input type="text" size="6" value="001" name="ediPoOrdDtlLineNum_B1" ezfname="ediPoOrdDtlLineNum_B1" tabindex="-1" ezftoupper ezfhyo="B"></td>
														<td><label ezfout name="soNum_B1" ezfName="soNum_B1" ezfHyo="B">S:STATUS</label></td>
														<td>
															<input type="text" size="14" value="STAPLES" name="mdseCd_B1" ezfname="mdseCd_B1" tabindex="-1" readonly ezftoupper ezfhyo="B">
															<input type="button" class="pBtn1" value="I" onclick="sendServer(this)" name="OpenWin_Mdse" ezfhyo="B" id="OpenWin_Mdse#{EZF_ROW_NUMBER}" style="width: 20px;">
														</td>
														<td><input type="text" size="23" value="STAPLES" name="mdseNm_B1" ezfname="mdseNm_B1" tabindex="-1" readonly ezftoupper ezfhyo="B"></td>
														<td><input type="text" size="9" value="3" name="shipQty_B1" ezfname="shipQty_B1" tabindex="-1" ezftoupper ezfhyo="B"></td>
														<td><label ezfout name="dealGrsTotPrcAmt_B1" ezfName="dealGrsTotPrcAmt_B1" ezfHyo="B">S:STATUS</label></td>
														<td><label ezfout name="poOrdNum_B1" ezfName="poOrdNum_B1" ezfHyo="B">12345678</label></td>
														<td><div id="ediPoOrdNum_B#{EZF_ROW_NUMBER}"><a id="ediPoOrdNum_B#{EZF_ROW_NUMBER}" href="#" name="Click_PoNum" ezfName="Click_PoNum" ezfHyo="B" ezfanchortext onclick="sendServer('Click_PoNum')" tabindex="4001" ><label ezfout name="ediPoOrdNum_B1" ezfName="ediPoOrdNum_B1" ezfHyo="B">11031080</label></a></div></td>
														<td><input type="text" size="44" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" name="batErrMsgTxt_B1" ezfname="batErrMsgTxt_B1" tabindex="-1" readonly ezftoupper ezfhyo="B"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}">
														<td><input type="text" size="6" value="001" name="ediPoOrdDtlLineNum_B1" ezfname="ediPoOrdDtlLineNum_B1" tabindex="-1" ezftoupper ezfhyo="B"></td>
														<td><label ezfout name="soNum_B1" ezfName="soNum_B1" ezfHyo="B">S:STATUS</label></td>
														<td>
															<input type="text" size="14" value="STAPLES" name="mdseCd_B1" ezfname="mdseCd_B1" tabindex="-1" readonly ezftoupper ezfhyo="B">
															<input type="button" class="pBtn1" value="I" onclick="sendServer(this)" name="OpenWin_Mdse" ezfhyo="B" id="OpenWin_Mdse#{EZF_ROW_NUMBER}" style="width: 20px;">
														</td>
														<td><input type="text" size="23" value="STAPLES" name="mdseNm_B1" ezfname="mdseNm_B1" tabindex="-1" readonly ezftoupper ezfhyo="B"></td>
														<td><input type="text" size="9" value="3" name="shipQty_B1" ezfname="shipQty_B1" tabindex="-1" ezftoupper ezfhyo="B"></td>
														<td><label ezfout name="dealGrsTotPrcAmt_B1" ezfName="dealGrsTotPrcAmt_B1" ezfHyo="B">S:STATUS</label></td>
														<td><label ezfout name="poOrdNum_B1" ezfName="poOrdNum_B1" ezfHyo="B">12345678</label></td>
														<td><div id="ediPoOrdNum_B#{EZF_ROW_NUMBER}"><a id="ediPoOrdNum_B#{EZF_ROW_NUMBER}" href="#" name="Click_PoNum" ezfName="Click_PoNum" ezfHyo="B" ezfanchortext onclick="sendServer('Click_PoNum')" tabindex="4001" ><label ezfout name="ediPoOrdNum_B1" ezfName="ediPoOrdNum_B1" ezfHyo="B">11031080</label></a></div></td>
														<td><input type="text" size="44" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" name="batErrMsgTxt_B1" ezfname="batErrMsgTxt_B1" tabindex="-1" readonly ezftoupper ezfhyo="B"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}">
														<td><input type="text" size="6" value="001" name="ediPoOrdDtlLineNum_B1" ezfname="ediPoOrdDtlLineNum_B1" tabindex="-1" ezftoupper ezfhyo="B"></td>
														<td><label ezfout name="soNum_B1" ezfName="soNum_B1" ezfHyo="B">S:STATUS</label></td>
														<td>
															<input type="text" size="14" value="STAPLES" name="mdseCd_B1" ezfname="mdseCd_B1" tabindex="-1" readonly ezftoupper ezfhyo="B">
															<input type="button" class="pBtn1" value="I" onclick="sendServer(this)" name="OpenWin_Mdse" ezfhyo="B" id="OpenWin_Mdse#{EZF_ROW_NUMBER}" style="width: 20px;">
														</td>
														<td><input type="text" size="23" value="STAPLES" name="mdseNm_B1" ezfname="mdseNm_B1" tabindex="-1" readonly ezftoupper ezfhyo="B"></td>
														<td><input type="text" size="9" value="3" name="shipQty_B1" ezfname="shipQty_B1" tabindex="-1" ezftoupper ezfhyo="B"></td>
														<td><label ezfout name="dealGrsTotPrcAmt_B1" ezfName="dealGrsTotPrcAmt_B1" ezfHyo="B">S:STATUS</label></td>
														<td><label ezfout name="poOrdNum_B1" ezfName="poOrdNum_B1" ezfHyo="B">12345678</label></td>
														<td><div id="ediPoOrdNum_B#{EZF_ROW_NUMBER}"><a id="ediPoOrdNum_B#{EZF_ROW_NUMBER}" href="#" name="Click_PoNum" ezfName="Click_PoNum" ezfHyo="B" ezfanchortext onclick="sendServer('Click_PoNum')" tabindex="4001" ><label ezfout name="ediPoOrdNum_B1" ezfName="ediPoOrdNum_B1" ezfHyo="B">11031080</label></a></div></td>
														<td><input type="text" size="44" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" name="batErrMsgTxt_B1" ezfname="batErrMsgTxt_B1" tabindex="-1" readonly ezftoupper ezfhyo="B"></td>
													</tr>

													<tr id="id_row{EZF_ROW_NUMBER}">
														<td><input type="text" size="6" value="001" name="ediPoOrdDtlLineNum_B1" ezfname="ediPoOrdDtlLineNum_B1" tabindex="-1" ezftoupper ezfhyo="B"></td>
														<td><label ezfout name="soNum_B1" ezfName="soNum_B1" ezfHyo="B">S:STATUS</label></td>
														<td>
															<input type="text" size="14" value="STAPLES" name="mdseCd_B1" ezfname="mdseCd_B1" tabindex="-1" readonly ezftoupper ezfhyo="B">
															<input type="button" class="pBtn1" value="I" onclick="sendServer(this)" name="OpenWin_Mdse" ezfhyo="B" id="OpenWin_Mdse#{EZF_ROW_NUMBER}" style="width: 20px;">
														</td>
														<td><input type="text" size="23" value="STAPLES" name="mdseNm_B1" ezfname="mdseNm_B1" tabindex="-1" readonly ezftoupper ezfhyo="B"></td>
														<td><input type="text" size="9" value="3" name="shipQty_B1" ezfname="shipQty_B1" tabindex="-1" ezftoupper ezfhyo="B"></td>
														<td><label ezfout name="dealGrsTotPrcAmt_B1" ezfName="dealGrsTotPrcAmt_B1" ezfHyo="B">S:STATUS</label></td>
														<td><label ezfout name="poOrdNum_B1" ezfName="poOrdNum_B1" ezfHyo="B">12345678</label></td>
														<td><div id="ediPoOrdNum_B#{EZF_ROW_NUMBER}"><a id="ediPoOrdNum_B#{EZF_ROW_NUMBER}" href="#" name="Click_PoNum" ezfName="Click_PoNum" ezfHyo="B" ezfanchortext onclick="sendServer('Click_PoNum')" tabindex="4001" ><label ezfout name="ediPoOrdNum_B1" ezfName="ediPoOrdNum_B1" ezfHyo="B">11031080</label></a></div></td>
														<td><input type="text" size="44" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" name="batErrMsgTxt_B1" ezfname="batErrMsgTxt_B1" tabindex="-1" readonly ezftoupper ezfhyo="B"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}">
														<td><input type="text" size="6" value="001" name="ediPoOrdDtlLineNum_B1" ezfname="ediPoOrdDtlLineNum_B1" tabindex="-1" ezftoupper ezfhyo="B"></td>
														<td><label ezfout name="soNum_B1" ezfName="soNum_B1" ezfHyo="B">S:STATUS</label></td>
														<td>
															<input type="text" size="14" value="STAPLES" name="mdseCd_B1" ezfname="mdseCd_B1" tabindex="-1" readonly ezftoupper ezfhyo="B">
															<input type="button" class="pBtn1" value="I" onclick="sendServer(this)" name="OpenWin_Mdse" ezfhyo="B" id="OpenWin_Mdse#{EZF_ROW_NUMBER}" style="width: 20px;">
														</td>
														<td><input type="text" size="23" value="STAPLES" name="mdseNm_B1" ezfname="mdseNm_B1" tabindex="-1" readonly ezftoupper ezfhyo="B"></td>
														<td><input type="text" size="9" value="3" name="shipQty_B1" ezfname="shipQty_B1" tabindex="-1" ezftoupper ezfhyo="B"></td>
														<td><label ezfout name="dealGrsTotPrcAmt_B1" ezfName="dealGrsTotPrcAmt_B1" ezfHyo="B">S:STATUS</label></td>
														<td><label ezfout name="poOrdNum_B1" ezfName="poOrdNum_B1" ezfHyo="B">12345678</label></td>
														<td><div id="ediPoOrdNum_B#{EZF_ROW_NUMBER}"><a id="ediPoOrdNum_B#{EZF_ROW_NUMBER}" href="#" name="Click_PoNum" ezfName="Click_PoNum" ezfHyo="B" ezfanchortext onclick="sendServer('Click_PoNum')" tabindex="4001" ><label ezfout name="ediPoOrdNum_B1" ezfName="ediPoOrdNum_B1" ezfHyo="B">11031080</label></a></div></td>
														<td><input type="text" size="44" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" name="batErrMsgTxt_B1" ezfname="batErrMsgTxt_B1" tabindex="-1" readonly ezftoupper ezfhyo="B"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}">
														<td><input type="text" size="6" value="001" name="ediPoOrdDtlLineNum_B1" ezfname="ediPoOrdDtlLineNum_B1" tabindex="-1" ezftoupper ezfhyo="B"></td>
														<td><label ezfout name="soNum_B1" ezfName="soNum_B1" ezfHyo="B">S:STATUS</label></td>
														<td>
															<input type="text" size="14" value="STAPLES" name="mdseCd_B1" ezfname="mdseCd_B1" tabindex="-1" readonly ezftoupper ezfhyo="B">
															<input type="button" class="pBtn1" value="I" onclick="sendServer(this)" name="OpenWin_Mdse" ezfhyo="B" id="OpenWin_Mdse#{EZF_ROW_NUMBER}" style="width: 20px;">
														</td>
														<td><input type="text" size="23" value="STAPLES" name="mdseNm_B1" ezfname="mdseNm_B1" tabindex="-1" readonly ezftoupper ezfhyo="B"></td>
														<td><input type="text" size="9" value="3" name="shipQty_B1" ezfname="shipQty_B1" tabindex="-1" ezftoupper ezfhyo="B"></td>
														<td><label ezfout name="dealGrsTotPrcAmt_B1" ezfName="dealGrsTotPrcAmt_B1" ezfHyo="B">S:STATUS</label></td>
														<td><label ezfout name="poOrdNum_B1" ezfName="poOrdNum_B1" ezfHyo="B">12345678</label></td>
														<td><div id="ediPoOrdNum_B#{EZF_ROW_NUMBER}"><a id="ediPoOrdNum_B#{EZF_ROW_NUMBER}" href="#" name="Click_PoNum" ezfName="Click_PoNum" ezfHyo="B" ezfanchortext onclick="sendServer('Click_PoNum')" tabindex="4001" ><label ezfout name="ediPoOrdNum_B1" ezfName="ediPoOrdNum_B1" ezfHyo="B">11031080</label></a></div></td>
														<td><input type="text" size="44" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" name="batErrMsgTxt_B1" ezfname="batErrMsgTxt_B1" tabindex="-1" readonly ezftoupper ezfhyo="B"></td>
													</tr>
												</ezf:skip>
										</table>
									</div>
								</td>
							</tr>
						</table>
						</div>
					</c:when>
				</c:choose>
			</td>
		</tr>
	</table>
</div>
                </div>
            </td>
        </tr>
    </table>
</center>

<%-- **** Task specific area ends here **** --%>
