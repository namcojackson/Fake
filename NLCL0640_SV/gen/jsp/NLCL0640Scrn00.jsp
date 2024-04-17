<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20181212093117 --%>
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
            <input type="hidden" name="pageID" value="NLCL0640Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="Tech PI Count">

            <%-- ********************** --%>
            <%-- *** Upper Tab Area *** --%>
            <%-- ********************** --%>
            <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

            <%-- ######################################## HEADER ######################################## --%>
            <center>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td>
                            <ezf:skip>
                            <div class="pTab_HEAD">
                                <ul>
                                    <li class="pTab_ON"><a href="./NLCL0640Scrn00.html">Tech PI Count</a></li>
                                </ul>
                            </div>

                            <!-- include S21BusinessProcessTAB -->
                            <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                            </ezf:skip>

                            <%-- ######################################## HEADER - DISPLAY ######################################## --%>
                            <div class="pTab_BODY">
                                <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0">
                                    <tr>
                                        <col align="left">
                                    </tr>
                                    <tr>
                                        <td valign="top">
                                            <table border="0" cellpadding="0" cellspacing="0">
                                                <colgroup>
                                                    <col width="130" align="left">
                                                    <col width="450" align="left">
                                                    <col width="130" align="left">
                                                    <col width="380" align="left"> 
                                                </colgroup>
                                                <!-- 1 -->
                                                <tr height="20">
<!-- START 2018/12/11 T.Ogura [QC#28755,MOD] -->
<!--                                                    <td class="stab">Technician Name</td> -->
<!--                                                    <td> -->
<!--                                                        <input type="text" class="pPro" size="15" maxlength="8" value="----+---" name="techTocCd" ezfname="techTocCd" ezftoupper > -->
<!--                                                        <input type="text"  class="pPro" size="20" maxlength="45" value="----+----1----+----2----+----3----+----4----+" name="techNm" ezfname="techNm" > -->
<!--                                                    </td> -->
                                                    <td class="stab">Location</td>
                                                    <td>
                                                        <ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="----+----1----+----2" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/>
                                                        <ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="----+----1----+----2----+----3" otherAttr=" size=\"30\" maxlength=\"30\""/>
                                                    </td>
<!-- END   2018/12/11 T.Ogura [QC#28755,MOD] -->
                                                    <td class="stab">Scheduled Date</td>
                                                    <td><ezf:inputText name="physInvtyDt" ezfName="physInvtyDt" value="01/01/2015" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
                                                </tr>
                                                <!-- 2 -->
                                                <tr height="20">
                                                    <td>&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                    <td class="stab">Tech Physical</td>
                                                    <td><ezf:inputText name="physInvtyCtrlNm" ezfName="physInvtyCtrlNm" value="----+----1----+----2----+----3----+----4----+----5----+----6----+----7----+----8----+----9----+----0" otherAttr=" size=\"35\" maxlength=\"100\" ezftoupper=\"\""/></td>
                                                </tr>
                                                <!-- 3 -->
                                                <tr height="20">
                                                    <td>&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </div>

<!-- START 2018/12/03 T.Ogura [QC#27978,DEL] -->
<!--                            <hr> -->
<!--                             -->
<!--                            <%-- ######################################## HEADER - INPUT ######################################## --%> -->
<!--                            <div> -->
<!--                                <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0" cellpadding="0" cellspacing="0"> -->
<!--                                    <tr> -->
<!--                                        <col width="130" align="left"> -->
<!--                                        <col width="480" align="left" nowrap="nowrap"> -->
<!--                                        <col width="100" align="left"> -->
<!--                                    </tr> -->
                                    <!-- 1 -->
<!--                                    <tr height="20"> -->
<!--                                        <td class="stab">Tech Location</td> -->
<!--                                        <td> -->
<!--                                            <select name="techLocCd_SL" ezfname="techLocCd_SL" style="width:280px" ezflist="techLocCd_PD,locNm_PD,99, ,noblank"> -->
<!--                                                <option></option> -->
<!--                                                <option>----+----1----+----2----+----3</option> -->
<!--                                            </select> -->
<!--                                        </td> -->
<!--                                        <td>&nbsp;</td> -->
<!--                                    </tr> -->
                                    <!-- 2 -->
<!--                                    <tr height="20"> -->
<!--                                        <td class="stab">Item Number</td> -->
<!--                                        <td> -->
<!--                                            <input type="text" size="30" maxlength="16" value="----+----1----+-" name="mdseCd" ezfname="mdseCd" ezftoupper > -->
<!--                                            <input type="button" class="pBtn4" value=">>" name="Search_MdseName" onclick="sendServer(this)"> -->
<!--                                            <input type="text" class="pPro" size="30" maxlength="250" value="----+----1----+----2----+----3----+----4----+----5----+----6----+----7----+----8----+----9----+----0----+----1----+----2----+----3----+----4----+----5----+----6----+----7----+----8----+----9----+----0----+----1----+----2----+----3----+----4----+----5" name="mdseDescShortTxt" ezfname="mdseDescShortTxt" > -->
<!--                                        </td> -->
<!--                                        <td>&nbsp;</td> -->
<!--                                    </tr> -->
                                    <!-- 3 -->
<!--                                    <tr height="20"> -->
<!--                                        <td class="stab">Quantity</td> -->
<!--                                        <td> -->
<!--                                            <input type="text" size="30" maxlength="15" value="9,999,999,999" name="cntInpQty" ezfname="cntInpQty" style="text-align:right;" > -->
<!--                                        </td> -->
<!--                                        <td>&nbsp;</td> -->
<!--                                    </tr> -->
                                    <!-- 4 -->
<!--                                    <tr height="20"> -->
<!--                                        <td class="stab">Serial</td> -->
<!--                                        <td> -->
<!--                                            <input type="text" size="30" maxlength="30" value="----+----1----+----2----+----3" name="serNum" ezfname="serNum" ezftoupper > -->
<!--                                        </td> -->
<!--                                        <td> -->
<!--                                            <input type="button" class="pBtn5" value="Add" name="Add_CountItem" onclick="sendServer(this)"> -->
<!--                                        </td> -->
<!--                                    </tr> -->
<!--                                </table> -->
<!--                            </div> -->
<!-- END   2018/12/03 T.Ogura [QC#27978,DEL] -->

                            <hr>

                            <%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
                            <table style="margin-left: 5px; width: 1108;">
                                <tr>
                                    <col width="180px" align="left"><%-- ===== space ===== --%>
                                    <col width="371px" align="left"><%-- ===== space ===== --%>
                                    <col width="550px" align="right"><%-- Pagenation View --%>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td>
                                        <div align="right">
                                            <ezf:skip>
                                                <table border="0" cellpadding="1" cellspacing="0">
                                                    <colgroup>
                                                        <col width="196px" align="right">
                                                        <col width="5px"   align="left">
                                                        <col width="40px"  align="right">
                                                        <col width="16px"  align="center">
                                                        <col width="16px"  align="center">
                                                        <col width="16px"  align="center">
                                                        <col width="16px"  align="center">
                                                        <col width="5px"   align="left">
                                                        <col width="40px"  align="right">
                                                        <col width="5px"   align="left">
                                                        <col width="40px"  align="right">
                                                        <col width="40px"  align="right">
                                                    </colgroup>
	                                                <tr>
	                                                    <td class="stab">Results 1 - 40 of 200</td>
	                                                    <td>&nbsp;</td>
	                                                    <td class="stab">Showing</td>
	                                                    <td><input type="text" style="text-align:right;" size="2" maxlength="2" value="1" ></td>
	                                                    <td class="stab">/</td>
	                                                    <td class="pOut">20</td>
	                                                    <td class="stab">Page</td>
	                                                    <td>&nbsp;</td>
	                                                    <td><input type="button" class="pBtn3" value="Jump" name="PagePrev" onclick="sendServer(this)"></td>
	                                                    <td></td>
	                                                    <td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
	                                                    <td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
	                                                </tr>
                                                </table>
                                            </ezf:skip>
                                            <table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 5px;">
                                                <tbody>
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
                                                </tbody>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                            <%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

                            <%-- ######################################## DETAIL ######################################## --%>
                            <div>
                                <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td>
                                            <div id="topTBL" style="overflow-y:none; height:; overflow-x:hidden; width:1106;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('btmTBL'));">
                                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
                                                    <colgroup>
                                                        <col width="220"   align="center"><%-- Tech Location --%>
                                                        <col width="120"   align="center"><%-- Item Number --%>
                                                        <col width="420"   align="center"><%-- Item Description --%>
                                                        <col width="120"   align="center"><%-- Quantity --%>
                                                        <col width="220"   align="center"><%-- Serial --%>
                                                    </colgroup>
                                                    <tr height="35">
                                                        <td class="pClothBs">Tech Location</td>
                                                        <td class="pClothBs">Item Number</td>
                                                        <td class="pClothBs">Item Description</td>
                                                        <td class="pClothBs">Quantity</td>
                                                        <td class="pClothBs">Serial</td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="vertical-align:top;">
<!-- START 2018/12/03 T.Ogura [QC#27978,MOD] -->
<!--                                            <div id="btmTBL" style="overflow-y:scroll; height:250; overflow-x:scroll; width:1125;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('topTBL'));"> -->
                                            <div id="btmTBL" style="overflow-y:scroll; height:380; overflow-x:scroll; width:1119;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('topTBL'));">
<!-- END   2018/12/03 T.Ogura [QC#27978,MOD] -->
                                                <table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" id="A">
                                                    <colgroup>
                                                        <col width="220" align="left">
                                                        <col width="120" align="left">
                                                        <col width="420" align="left">
                                                        <col width="120" align="right">
                                                        <col width="220" align="left">
                                                    </colgroup>
                                                    <ezf:row ezfHyo="A">
                                                    <tr>
                                                        <td><ezf:label name="locNm_A" ezfName="locNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
                                                        <td><ezf:label name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
                                                        <td><ezf:label name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
                                                        <td><ezf:label name="cntInpQty_A" ezfName="cntInpQty_A" ezfHyo="A" ezfArrayNo="0" /></td>
                                                        <td><ezf:label name="serNum_A" ezfName="serNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    </tr>
                                                    </ezf:row>
                                                </table>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <%-- ######################################## FOOTER ######################################## --%>
                            <div>
                                <table border="0">
                                    <tr height="5">
                                        <td></td>
                                    </tr>
                                    <tr height="20">
                                        <td valign="bottom">
                                            <table border="0" cellpadding="1" cellspacing="0" width="100%">
                                                <colgroup>
                                                    <col width="550">
                                                    <col width="90">
                                                    <col width="200">
                                                    <col width="10">
                                                    <col width="40">
                                                </colgroup>
                                                <tr>
                                                    <td>&nbsp;</td>
                                                    <td><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TemplateFileForUpload" >Import File</ezf:anchor></td>
                                                    <td><ezf:inputFile name="xxFileData_UP" ezfName="xxFileData_UP" otherAttr=" size=\"50\" maxlength=\"9999\" ezftoupper=\"\""/></td>
                                                    <td>&nbsp;</td>
                                                    <td><ezf:inputButton name="Import_CountItems" value="Import" htmlClass="pBtn6" /></td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            </center>



<%-- **** Task specific area ends here **** --%>
