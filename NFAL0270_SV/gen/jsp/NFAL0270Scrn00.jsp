<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230612161320 --%>
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
			<input type="hidden" name="pageID" value="NFAL0270Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="AJE Invoice Model Group Allocation Maintenance">

<center>
    <table cellSpacing="0" cellPadding="0" width="100%" border="0">
        <tbody>
            <tr>
                <td>
                    <%-- ********************** --%>
                    <%-- *** Upper Tab Area *** --%>
                    <%-- ********************** --%>
                    <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                    <ezf:skip>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
                            <tr>
                                <td width="1070px" height="28px" valign="bottom">
                                    <table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
                                        <tr title="AJE Invoice Model Group Allocation Maintenance">
                                            <td width="107px" align="center" class="same">InvAllocMnt</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </ezf:skip>

                    <div class="pTab_BODY">
                        <!-- ################################################## Header - [START] ################################################## -->
                        <!-- ##### Search Criteria [START] ##### -->
                        <table height="20">
                            <tr>
                                <td class="stab" width="100"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_mdlGrpId" >Model Group ID(*)</ezf:anchor></td>
                                <td><ezf:inputText name="mdlGrpId_H" ezfName="mdlGrpId_H" value="xx" otherAttr=" size=\"10\" maxlength=\"22\" style=\"text-align:right;\" ezftoupper=\"\""/></td>
                                <td class="stab" width="120">Model Group Name(*)</td>
                                <td ><ezf:inputText name="mdlGrpNm_H" ezfName="mdlGrpNm_H" value="xx" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
                            </tr>
                            <tr>
                                <td class="stab" width="100">Charge Type</td>
                                <td>
                                   <ezf:select name="svcInvChrgTpCd_H" ezfName="svcInvChrgTpCd_H" ezfBlank="1" ezfCodeName="svcInvChrgTpCd_PD" ezfDispName="svcInvChrgTpDescTxt_PD" otherAttr=" style=\"width:150px\" id=\"svcInvChrgTpCd\""/>
                                </td>
                                <td class="stab" width="130">Allocation Type</td>
                                <td>
                                   <ezf:select name="svcAllocTpCd_H" ezfName="svcAllocTpCd_H" ezfBlank="1" ezfCodeName="svcAllocTpCd_PD" ezfDispName="svcAllocTpDescTxt_PD" otherAttr=" style=\"width:320px\" id=\"svcAllocTpCd\""/>
                                </td>
                                <td width="130">&nbsp</td>
                                <td colspan="3" class="stab" align="right">
                                   <ezf:inputButton name="Search" value="Search" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" />
                                </td>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                        <hr>
                        <!-- ##### Search Criteria [END] ##### -->
                        <!-- ##### Search Result [START] ##### -->
                        <table style="width: 100%;" >
                            <colgroup>
                                <col align="left" width="">
                                <col align="right" width="">
                            </colgroup>
                            <tbody>
                                <tr>
                                    <td>
                                        <!-- ##### Preferred View [START] ##### -->
                                        <ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
                                        <!-- ##### Preferred View [END] ##### -->
                                    </td>
                                    <td>
                                    <!-- ##### Pagination [START] ##### -->
                                    <ezf:skip>
                                         <table border="0" cellpadding="1" cellspacing="0">
                                             <col >
                                                 <col width="40" align="right">
                                                 <col width="16" align="center">
                                                 <col width="40" align="right">
                                                 <col width="16" align="center">
                                                 <col width="40" align="right">
                                                 <col width="10">
                                                 <col width="0">
                                                 <col width="1">
                                                 <col width="0">
                                                 <tr>
                                                     <td class="stab">Showing</td>
                                                     <td class="pOut">1</td>
                                                     <td class="stab">to</td>
                                                     <td class="pOut">20</td>
                                                     <td class="stab">of</td>
                                                     <td class="pOut">200</td>
                                                     <td>&nbsp;</td>
                                                     <td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
                                                     <td></td>
                                                     <td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
                                                     <td></td>
                                                 </tr>
                                         </table>
                                    </ezf:skip>
                                    <jsp:include page="../tablePagenation/S21TablePagenation.jsp">
                                        <jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
                                        <jsp:param name="TableNm"     value="A" />
                                        <jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
                                        <jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
                                        <jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
                                    </jsp:include>
                                    <!-- ##### Pagination [END] ##### -->
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <!-- ######################################## TO (COMMON)PAGENATION ###################################### --!>

                        <!-- ################################################## Detail   - [START] ################################################## -->
                        <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td valign="top">
                                    <div id="parentBoxA">
                                        <div style="float:left; display:block"><!-- left table -->
                                             <div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
                                             <div id="leftTbl" style="float:left; display:block; height:383px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
                                        </div><!-- left table -->
                                        <%-- ******************************** --%>
                                        <%-- *** Right Table Area(Header) *** --%>
                                        <%-- ******************************** --%>
                                        <div style="float:left"> <!-- right table -->
                                            <div id="rightTblHead" style="width:1109px; display:block; overflow:hidden; margin:0px; padding:0px;" >
                                                <table id="A_HEAD" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="1199px">
                                                <col width="30" align="center"><!-- Check Box -->
                                                <col width="150" align="center"><!-- Model Group ID -->
                                                <col width="150" align="center"><!-- Model Group Name -->
                                                <col width="200" align="center"><!-- Charge Type -->
                                                <col width="270" align="center"><!-- Allocation Type -->
                                                <col width="100" align="center"><!-- Equipment Percentage -->
                                                <col width="100" align="center"><!-- Service Percentage -->
                                                <col width="100" align="center"><!-- Supply Percentage -->
                                                <!-- <tr valign="middle">-->
                                                <tr height="40"">
                                                    <td id="AH0" class="pClothBs colFix">&nbsp;</td>
                                                    <td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdlGrpId_A' )">Model Group ID<img id="sortIMG.mdlGrpId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                    <td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdlGrpNm_A' )">Model Group Name<img id="sortIMG.mdlGrpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                    <td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcInvChrgTpCd_A' )">Charge Type<img id="sortIMG.svcInvChrgTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                    <td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcAllocTpCd_A' )">Allocation Type<img id="sortIMG.svcAllocTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                    <td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'equipAllocPct_A' )">Equipment Percentage<img id="sortIMG.equipAllocPct_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                    <td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcAllocPct_A' )">Service Percentage<img id="sortIMG.svcAllocPct_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                    <td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'splyAllocPct_A' )">Supply Percentage<img id="sortIMG.splyAllocPct_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                </tr>
                                            </table>
                                        </div>
                                        <%-- ******************************** --%>
                                        <%-- *** Right Table Area(Detail) *** --%>
                                        <%-- ******************************** --%>
                                        <div  id="rightTbl" style="width:1126;  height:400px; display:block; overflow:scroll; margin:0px; padding:0px;" >
                                            <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Right" width="700px">
                                                <col width="30" align="center"><!-- Check Box -->
                                                <col width="150" align="center"><!-- Model Group ID -->
                                                <col width="150" align="center"><!-- Model Group Name -->
                                                <col width="200" align="center"><!-- Charge Type -->
                                                <col width="270" align="center"><!-- Allocation Type -->
                                                <col width="100"  align="center"><!-- Equipment Percentage -->
                                                <col width="100" align="center"><!-- Service Percentage -->
                                                <col width="100"  align="center"><!-- Supply Percentage -->
                                                <ezf:row ezfHyo="A">
                                                    <tr height="25">
                                                        <!-- Check Box -->
                                                        <td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBoxBtn{EZF_ROW_NUMBER}\""/></td>
                                                        <!-- Model Group ID -->
                                                        <td>
                                                           <ezf:inputButton name="OpenWin_mdlGrpId_A" value="M" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                                           <ezf:inputText name="mdlGrpId_A" ezfName="mdlGrpId_A" value="85" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"22\" style=\"text-align:right;\""/>
                                                        </td>
                                                        <!-- Model Group Name -->
                                                        <td><ezf:inputText name="mdlGrpNm_A" ezfName="mdlGrpNm_A" value="ADVC9065" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"20\""/></td>
                                                        <!-- Charge Type -->
                                                        <td align="center">
                                                           <ezf:select name="svcInvChrgTpCd_A" ezfName="svcInvChrgTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcInvChrgTpCd_PD" ezfDispName="svcInvChrgTpDescTxt_PD" otherAttr=" id=\"\" style=\"width:160px;\""/>
                                                        </td>
                                                        <!-- Allocation Type -->
                                                        <td align="center">
                                                           <ezf:select name="svcAllocTpCd_A" ezfName="svcAllocTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcAllocTpCd_PD" ezfDispName="svcAllocTpDescTxt_PD" otherAttr=" id=\"\" style=\"width:230px;\""/>
                                                        </td>
                                                        <!-- Equipment Percentage -->
                                                        <td><ezf:inputText name="equipAllocPct_A" ezfName="equipAllocPct_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"5\" id=\"\" style=\"text-align:right;\" ezftoupper=\"\""/></td>
                                                        <!-- Service Percentage -->
                                                        <td><ezf:inputText name="svcAllocPct_A" ezfName="svcAllocPct_A" value="60.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"5\" id=\"\" style=\"text-align:right;\" ezftoupper=\"\""/></td>
                                                        <!-- Supply Percentage -->
                                                        <td><ezf:inputText name="splyAllocPct_A" ezfName="splyAllocPct_A" value="40.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"5\" id=\"\" style=\"text-align:right;\" ezftoupper=\"\""/></td>
                                                    </tr>
                                                </ezf:row>
                                            </table>
                                        </div>
                                    </div>
                                    <script type="text/javascript" defer>
                                        S21TableUI.initialize("parentBoxA", "A_HEAD", "A_Right", -1, true);
                                    </script>
                                    <table>
                                       <tr height="20">
                                          <td valign="bottom">
                                            <table border="0" cellpadding="1" cellspacing="0" width="100%">
                                               <col width="82">
                                               <col width="82">
                                               <col width="">
                                               <tr>
                                                  <td><ezf:inputButton name="Add_Line" value="Insert Row" htmlClass="pBtn6" /></td>
                                                  <td><ezf:inputButton name="Del_Line" value="Delete Row" htmlClass="pBtn6" /></td>
                                                  <td>&nbsp;</td>
                                               </tr>
                                            </table>
                                          </td>
                                       </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <!-- ################################################## Search Result   - [E N D] ################################################## -->
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
</center>
<div style="display:none;">
	<ezf:inputButton name="btn11" value="btn11" otherAttr=" id=\"btn11\""/>
</div>
<%-- ###SCRIPT --%>
<script type="text/javascript">
             setKeyDownHandler();

	function setKeyDownHandler() {

		if( window.addEventListener ) {
		    window.addEventListener("keyup", emulateFuncKeyDown, false);
		} else if( document.attachEvent ) {
		    document.attachEvent("onkeyup", emulateFuncKeyDown);
		} else {
		    document.onkeyup = emulateFuncKeyDown;
		}
	}

	function emulateFuncKeyDown() {

		var code = event.keyCode;
		//alert("keyCode:["+event.keyCode+"]");

		if(event.keyCode == 122 ) {
			event.keyCode = null;
			event.returnValue = false;
		}

		switch(code) {
		// F11
		case 122:
			//sendServer("Line_Add");
			emulateOnClickEvent("btn11");
			break;
		default:
			break;
		}
		return;
	}

	function emulateOnClickEvent(elementId) {

		var elem = document.getElementById(elementId);
		if( /*@cc_on ! @*/ false ) {
			elem.fireEvent("onclick");
		} else {
			var evt = document.createEvent("MouseEvents");
			evt.initEvent("click", false, true);
			elem.dispatchEvent(evt);
		}
	}


</script>


<%-- **** Task specific area ends here **** --%>
