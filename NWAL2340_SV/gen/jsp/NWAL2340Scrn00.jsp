<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171004131436 --%>
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
			<input type="hidden" name="pageID" value="NWAL2340Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Address MASS APPLY Popup">
<div style="margin-left:5px; padding:5;">
<fieldset>
	<table border="0" cellpadding="1" cellspacing="0" height="110"  rules="none"  style="padding: 1px; margin-bottom: 2px;">
                    <!-- <%-- ######################################## FROM (COMMON)HEADER ######################################## --%> -->
                    <tr>
                        <td>
                            <table style="margin-top: 5px;" border="0" cellspacing="1" cellpadding="0">
                                <tr>
                                    <td>
                                        <table border="0" cellpadding="1" cellspacing="0">
                                            <col width="5" align="left">
                                            <col width="400" align="left">
                                            <col width="5" align="left">
                                            <col width="100" align="left">
                                            <col width="250" align="left">

                                            <tr>
                                                <td></td>
                                                <td>
                                                    <table border="1" cellpadding="1" cellspacing="0">
                                                        <col width="200" align="center">
                                                        <col width="200" align="center">
                                                        <tr>
                                                            <td class="pClothBs">Customer Number</td>
															<!-- Mod Start 2017/10/04 H.Sugawara QC#19922 -->
                                                            <!-- <td class="pClothBs">Location Number</td> -->
                                                            <td class="pClothBs">Ship To Code</td>
															<!-- Mod End   2017/10/04 H.Sugawara QC#19922 -->
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <ezf:inputText name="shipToCustAcctCd_H" ezfName="shipToCustAcctCd_H" />
                                                                <ezf:inputButton name="OpenWin_ShipTo_H" value="..." htmlClass="pBtn0" />
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="shipToCustCd_H" ezfName="shipToCustCd_H" />
                                                                <ezf:inputButton name="OpenWin_ShipTo_H" value="..." htmlClass="pBtn0" />
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                                <td></td>
                                                <td><ezf:inputButton name="MassApply" value="Mass Apply" htmlClass="pBtn8" otherAttr=" id=\"\""/></td>
                                                <td></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <!-- <%-- ######################################## TO (COMMON)HEADER ########################################## --%> -->
                    <!-- <%-- ######################################## FROM (COMMON)DETAIL ######################################## --%> -->
                    <tr>
                        <td align="center">
                            <table>
                                <tr>
                                    <td>
                                        <div id="hdrTbl" style="overflow-y:none; height:; width:;" onScroll="onScroll = synchroScrollLeft(this.id, new Array('dtlTbl'));">
                                            <table border="1" cellpadding="1" cellspacing="0" height="38" style="table-layout: fixed">
                                                <col width="30" align="center"> <!-- CheckBox -->
                                                <col width="65" align="center">   <!-- Address Group -->
                                                <col width="148" align="center">  <!-- Name -->
                                                <col width="120" align="center">  <!-- Customer Number -->
                                                <col width="120" align="center">  <!-- Location Number -->
                                                <col width="460" align="center">  <!-- Address -->
                                                <tr>
                                                    <td class="pClothBs">
                                                        <ezf:inputCheckBox name="xxChkBox_H" ezfName="xxChkBox_H" value="Y" onClick="sendServer('OnChange_CheckAll')" otherAttr=" id=\"submtFlg_BA\""/>
                                                    </td>
                                                    <td class="pClothBs">Address Group</td>
                                                    <td class="pClothBs">Name</td>
                                                    <td class="pClothBs">Customer Number</td>
													<!-- Mod Start 2017/10/04 H.Sugawara QC#19922 -->
                                                    <!-- <td class="pClothBs">Location Number</td> -->
                                                    <td class="pClothBs">Ship To Code</td>
													<!-- Mod End   2017/10/04 H.Sugawara QC#19922 -->
                                                    <td class="pClothBs">Address</td>
                                                </tr>
                                            </table>
                                        </div>
                                        <div id="dtlTbl" style="overflow-y:scroll; height:420; width:;" onScroll="onScroll = synchroScrollLeft(this.id, new Array('hdrTbl'));">
                                            <table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed">
                                                <col width="30" align="center"> <!-- CheckBox -->
                                                <col width="65" align="center">   <!-- Address Group -->
                                                <col width="148" align="center">  <!-- Name -->
                                                <col width="120" align="center">  <!-- Customer Number -->
                                                <col width="120" align="center">   <!-- Location Number -->
                                                <col width="460" align="center">  <!-- Address -->
                                                <ezf:row ezfHyo="A">
                                                    <tr height="25">
                                                        <!-- CheckBox -->
                                                        <td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
                                                        <!-- Address Group -->
                                                        <td><ezf:label name="addrLbTxt_A" ezfName="addrLbTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
                                                        <!-- Name -->
                                                        <td>
                                                        	<ezf:inputText name="shipToCustAcctNm_A" ezfName="shipToCustAcctNm_A" value="WWWWWWWWW1WWWWWWWWWX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"360\""/>
                                                        </td>
                                                        <!-- Customer Number -->	
                                                        <td>
                                                            <ezf:inputText name="shipToCustAcctCd_A" ezfName="shipToCustAcctCd_A" value="WWWWWWWWW1WWWWWWWWWX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
                                                            <ezf:inputButton name="OpenWin_ShipTo_D" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                                        </td>
                                                        <!-- Location Number -->
                                                        <td>
                                                            <ezf:inputText name="shipToCustCd_A" ezfName="shipToCustCd_A" value="WWWWWWWWW1WWWWWWWWWX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
                                                            <ezf:inputButton name="OpenWin_ShipTo_LD" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                                        </td>
                                                        <!-- Address -->
                                                        <td>
                                                            <ezf:inputText name="xxAllLineAddr_A" ezfName="xxAllLineAddr_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWWX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"64\" maxlength=\"400\" ezftoupper=\"\""/>
                                                        </td>
                                                    </tr>
                                                </ezf:row>
                                            </table>
                                        </div>

                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <!-- <%-- ######################################## TO (COMMON)DETAIL ########################################## --%> -->
</fieldset>
</div>


<%-- **** Task specific area ends here **** --%>
