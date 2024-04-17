<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170719143920 --%>
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
            <input type="hidden" name="pageID" value="NMAL2600Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="Territory Admin">
            <jsp:include page="../tab/S21BusinessProcessTAB.jsp"></jsp:include>

<div class="pTab_BODY">
<center>
    <table width="100%">
        <col valign="top">
        <tr>
            <td>
                <!-- <div class="pTab_BODY"> -->
				<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:0px;margin-bottom:-5px;">
				<!--<table border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">-->
<!--
					<col width="100">
					<col width="270">
					<col width="100">
					<col width="500">
-->
					<col width="147">
					<col width="345">
					<col width="110">
					<col width="300">

					<col width="">
					<tr>
						<td class="stab">Saved Search Options</td>
						<td>
							<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_L" ezfDispName="srchOptNm_L" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
						</td>
						<td class="stab">Search Option Name</td>
						<td class="stab"><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
						<td>
							<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
							<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
						</td>
					</tr>
				</table>
				<br />
                <table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
                    <col valign="top" width="100">
                    <col valign="top" width="270">
                    <col valign="top" width="100">
                    <col valign="top" width="150">
                    <col valign="top" width="350">
                    <tr>
                        <td class="stab">Business Area</td>
                        <td>
                            <ezf:select name="bizAreaOrgCd_P1" ezfName="bizAreaOrgCd_P1" ezfCodeName="bizAreaOrgCd_H1" ezfDispName="bizAreaOrgNm_H1" />
                        </td>
                        <td class="stab">Territory Type</td>
                        <td>
                            <ezf:select name="trtyTpCd_P1" ezfName="trtyTpCd_P1" ezfBlank="1" ezfCodeName="trtyTpCd_H1" ezfDispName="trtyTpNm_H1" />
                        </td>
                        <td rowspan="5">
                            <fieldset>
                                <legend>Show</legend>
                                <table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
                                    <tr>
                                        <td class="stab">
                                            <label>
                                                <ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" value="1" />Top Territories Only
                                            </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="stab">
                                            <label>
                                                <ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" value="2" />All Territories Expanded
                                            </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="stab">
                                            <label>
                                                <ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" value="3" />Territory & Children Only
                                            </label>
                                        </td>
                                    </tr>
                                    <!--
                                    <tr>
                                        <td class="stab">
                                            <label>
                                                <input name="xxRadioBtn_H1" type="radio" value="4" ezfname="xxRadioBtn_H1">Territory, Children & Territory Rules
                                            </label>
                                        </td>
                                    </tr>
                                    -->
                                </table>
                            </fieldset>
                        </td>
                    </tr>
                    <tr>
                        <td class="stab">Territory Name(*)</td>
                        <td>
                            <ezf:inputText name="orgNm_H1" ezfName="orgNm_H1" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
                        </td>
                        <td class="stab">Rank</td>
                        <td>
                            <ezf:select name="orgTierCd_P1" ezfName="orgTierCd_P1" ezfBlank="1" ezfCodeName="orgTierCd_H1" ezfDispName="orgTierNm_H1" />
                        </td>
                    </tr>
                    <tr>
                        <td class="stab">Resource Name(*)</td>
                        <td>
                            <ezf:inputText name="xxPsnNm_H1" ezfName="xxPsnNm_H1" otherAttr=" size=\"30\" maxlength=\"30\""/>
                        </td>
                        <td class="stab">Territory Group</td>
                        <td>
                            <ezf:select name="trtyGrpTpCd_P1" ezfName="trtyGrpTpCd_P1" ezfBlank="1" ezfCodeName="trtyGrpTpCd_H1" ezfDispName="trtyGrpTpNm_H1" />
                        </td>
                    </tr>
                    <tr>
                        <td class="stab">Employee ID(*)</td>
                        <td>
                            <ezf:inputText name="psnCd_H1" ezfName="psnCd_H1" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
                        </td>
                    </tr>
                    <tr>
                        <td class="stab">Resource #(*)</td>
                        <td>
                            <ezf:inputText name="psnNum_H1" ezfName="psnNum_H1" otherAttr=" maxlength=\"50\" size=\"30\""/>
                        </td>
                    </tr>
                    <tr>
                        <td class="stab">Start Date</td>
                        <td>
                            <ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" value="02/26/2015" otherAttr=" ezftoupper=\"\" size=\"10\""/>
                            <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);" >
                        </td>
                        <td colspan="2" class="stab">
                            <label>
                                <ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" />Include Inactive
                            </label>
                        </td>
                        <td>
                            <ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" />
                            <ezf:inputButton name="Link_Territory" value="Create New" htmlClass="pBtn8" />
                            <ezf:inputButton name="Link_MassUpdate" value="Mass Update" htmlClass="pBtn8" />
                        </td>
                    </tr>
                </table>
                <fieldset>
                    <c:choose>
                        <c:when test="${pageScope._ezddatabean.xxDplyTab=='AdvancedSearch'}">
                            <legend>
                                <ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Link_AdvancedSearch" >Advanced Search</ezf:anchor>
                                <img src="./img/biz/downarrow1.png" style="height:14px;" align="middle">
                            </legend>
                            <table style="table-layout:fixed;">
                                <col width="460" valign="top">
                                <col width="160" valign="top">
                                <tr>
                                    <td>&nbsp</td>
                                    <td>
                                        <ezf:inputButton name="InsertRow" value="Add" htmlClass="pBtn5" />
                                        <ezf:inputButton name="DeleteRow" value="Delete" htmlClass="pBtn5" />
                                    </td>
                                </tr>
                            </table>
                            <table border="0" cellpadding="0" cellspacing="0">
                                <col align="left" valign="top">
                                <tr>
                                    <td>
                                        <table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
                                            <col align="center" width="30">
                                            <col align="center" width="200">
                                            <col align="center" width="200">
                                            <col align="center" width="80">
                                            <col align="center" width="80">
                                            <tr height="18">
                                                <td class="pClothBs">&nbsp</td>
                                                <td class="pClothBs">Territory Rule Type</td>
                                                <td class="pClothBs">Operand</td>
                                                <td class="pClothBs">Value From</td>
                                                <td class="pClothBs">Value To</td>
                                            </tr>
                                        </table>
                                        <div style="overflow-x:hidden; width:610; height:74px; overflow-y:scroll;">
                                        <table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
                                            <col width="30" align="center">
                                            <col width="200">
                                            <col width="200">
                                            <col width="80">
                                            <col width="80">
                                            <ezf:row ezfHyo="A">
                                                <tr>
                                                    <td>
                                                        <ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:select name="trtyRuleTpCd_P1" ezfName="trtyRuleTpCd_P1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="trtyRuleTpCd_A1" ezfDispName="trtyRuleTpNm_A1" otherAttr=" style=\"width:196px\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:select name="trtyRuleOprdTpCd_P1" ezfName="trtyRuleOprdTpCd_P1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="trtyRuleOprdTpCd_A1" ezfDispName="trtyRuleOprdTpNm_A1" otherAttr=" style=\"width:196px\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="trtyRuleFromValTxt_A1" ezfName="trtyRuleFromValTxt_A1" value="0000000001" ezfHyo="A" ezfArrayNo="0" htmlClass="stab" otherAttr=" size=\"10\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="trtyRuleThruValTxt_A1" ezfName="trtyRuleThruValTxt_A1" value="1000000001" ezfHyo="A" ezfArrayNo="0" htmlClass="stab" otherAttr=" size=\"10\""/>
                                                    </td>
                                                </tr>
                                            </ezf:row>
                                        </table>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </c:when>
                        <c:when test="${pageScope._ezddatabean.xxDplyTab==''}">
                            <legend>
                                <ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Link_AdvancedSearch" >Advanced Search</ezf:anchor>
                                <img src="./img/biz/rightarrow1.png" style="height:14px;" align="middle">
                            </legend>
                        </c:when>
                    </c:choose>
                </fieldset>
                <!--</div> -->
            </td>
        </tr>
    </table>
    <table style="table-layout:fixed;">
        <col width="610">
        <tr>
            <td>&nbsp</td>
            <td>
                <ezf:inputButton name="OpenWin_ResourceAssign" value="Resource Assignment" htmlClass="pBtn12" />
                <ezf:inputButton name="OpenWin_AccountRules" value="Show Account" htmlClass="pBtn8" />
                <ezf:inputButton name="OpenWin_TerritoryRules" value="Show Rules" htmlClass="pBtn8" />
                <ezf:inputButton name="OpenWin_Territory" value="Show Territory" htmlClass="pBtn8" />
            </td>
        </tr>
    </table>
    <table width="100%">
        <col valign="top">
        <tr>
            <td>
                <table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" >
                    <col width="600">
                    <col width="230">
                    <col width="210">
                    <col width="40">
                    <tr height="24">
                        <td align="center" class="pClothBs">Territory Name</td>
                        <td align="center" class="pClothBs">Resource Name</td>
                        <td align="center" class="pClothBs">Role</td>
                        <td align="center" class="pClothBs">&nbsp</td>
                    </tr>
                </table>
                <%@ page import="business.servlet.NMAL2600.NMAL2600BMsg" %>
                <% NMAL2600BMsg bizMsg = (NMAL2600BMsg)databean.getEZDBMsg(); %>
                    <%if("Y".equals(bizMsg.xxRsltFlg.getValue())) { %>
                    <c:choose>
                        <c:when test="${pageScope._ezddatabean.xxDplyTab=='AdvancedSearch'}">
                            <div style="overflow-x:hidden; overflow-y:scroll; width:1100; height:205;" >
                        </c:when>
                        <c:when test="${pageScope._ezddatabean.xxDplyTab==''}">
                            <div style="overflow-x:hidden; overflow-y:scroll; width:1100; height:325;" >
                        </c:when>
                    </c:choose>
                    <% if( ((NMAL2600Bean)databean).getTreeView() != null ) { %>
                    <uji:treeView
                         bean        = "${__screenName__}"
                         indentIcon    = "./img/treeView/child.gif;./img/treeView/lastchild.gif;./img/treeView/hasmorechild.gif;./img/treeView/nomorechild.gif;"
                         insets        = "0"
                         cellSpacing    = "0"
                         background    = "#FFFFFF"
                         stripeBackground    = "#D7E2E2"
                         css        = "pTreeView"
                         noWrap        = "true"
                         borderWidth    = "1"
                         ruleWidth    = "1"
                         rules        = "all"
                         dataEscape    = "false;"
                         property            = "treeView"
                         dataCellType        = "data;data;data;radio;"
                         columnWidth        = "600;230;210;40;"
                         dataAlignmentHorizontal    = ";left;left;center;"
                     />
                    <% } %>
                       </div>
                <% } %>
            </td>
        </tr>
    </table>
</center>
<%if("Y".equals(bizMsg.xxRsltFlg.getValue())) { %>
<% if( ((NMAL2600Bean)databean).getTreeView() != null ) { %>
    <%-- include S21TreeView.jsp --%>
    <%@ page import="business.servlet.NMAL2600.NMAL2600Bean" %>
        <jsp:include page="../treeView/S21TreeView.jsp">
        <jsp:param name="treeView" value="<%= ((NMAL2600Bean)databean).getTreeView().isDisableAllFields() %>" />
        <jsp:param name="TreeViewPropertyNameList" value="treeView" />
        <jsp:param name="isFocusTreeView" value="<%= ((NMAL2600Bean)databean).getTreeView().isSetFocusTreeView() %>" />
        <jsp:param name="setFocusTreeViewName" value="treeView" />
    </jsp:include>
<% } %>
<% } %>
</div>

<%-- **** Task specific area ends here **** --%>
