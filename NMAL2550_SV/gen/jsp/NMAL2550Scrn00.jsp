<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161128081702 --%>
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
            <input type="hidden" name="pageID" value="NMAL2550Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="GL Common Popup">




<center>
    <table>
        <tr>
            <td>
                <table border="0" cellpadding="2" cellspacing="0" height="120">
                    <col width="80">
                    <col width="65">
                    <col width="30">
                    <col width="495">
                    <col width="45">
                    <tr>
                        <td class="stab"><ezf:anchor name="coaCmpyCd_H2" ezfName="coaCmpyCd_H2" ezfEmulateBtn="1" ezfGuard="Search_Company" >Company</ezf:anchor></td>
                        <td><ezf:inputText name="coaCmpyCd_H1" ezfName="coaCmpyCd_H1" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
                        <td><ezf:inputButton name="Company_Setting" value=">>" htmlClass="pBtn0" /></td>
                        <td><ezf:inputText name="coaCmpyDescTxt_H1" ezfName="coaCmpyDescTxt_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"70\" maxlength=\"50\""/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="stab"><ezf:anchor name="coaBrCd_H2" ezfName="coaBrCd_H2" ezfEmulateBtn="1" ezfGuard="Search_Branch" >Branch</ezf:anchor></td>
                        <td><ezf:inputText name="coaBrCd_H1" ezfName="coaBrCd_H1" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
                        <td><ezf:inputButton name="Branch_Setting" value=">>" htmlClass="pBtn0" /></td>
                        <td><ezf:inputText name="coaBrDescTxt_H1" ezfName="coaBrDescTxt_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"70\" maxlength=\"50\""/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="stab"><ezf:anchor name="coaCcCd_H2" ezfName="coaCcCd_H2" ezfEmulateBtn="1" ezfGuard="Search_CostCenter" >Cost Center</ezf:anchor></td>
                        <td><ezf:inputText name="coaCcCd_H1" ezfName="coaCcCd_H1" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
                        <td><ezf:inputButton name="CostCenter_Setting" value=">>" htmlClass="pBtn0" /></td>
                        <td><ezf:inputText name="coaCcDescTxt_H1" ezfName="coaCcDescTxt_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" otherAttr=" size=\"70\" maxlength=\"240\""/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="stab"><ezf:anchor name="coaAcctCd_H2" ezfName="coaAcctCd_H2" ezfEmulateBtn="1" ezfGuard="Search_Account" >Account</ezf:anchor></td>
                        <td><ezf:inputText name="coaAcctCd_H1" ezfName="coaAcctCd_H1" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
                        <td><ezf:inputButton name="Account_Setting" value=">>" htmlClass="pBtn0" /></td>
                        <td><ezf:inputText name="coaAcctDescTxt_H1" ezfName="coaAcctDescTxt_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"70\" maxlength=\"50\""/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="stab"><ezf:anchor name="coaProdCd_H2" ezfName="coaProdCd_H2" ezfEmulateBtn="1" ezfGuard="Search_Product" >Product</ezf:anchor></td>
                        <td><ezf:inputText name="coaProdCd_H1" ezfName="coaProdCd_H1" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/></td>
                        <td><ezf:inputButton name="Product_Setting" value=">>" htmlClass="pBtn0" /></td>
                        <td><ezf:inputText name="coaProdDescTxt_H1" ezfName="coaProdDescTxt_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"70\" maxlength=\"50\""/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="stab"><ezf:anchor name="coaChCd_H2" ezfName="coaChCd_H2" ezfEmulateBtn="1" ezfGuard="Search_Channel" >Channel</ezf:anchor></td>
                        <td><ezf:inputText name="coaChCd_H1" ezfName="coaChCd_H1" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
                        <td><ezf:inputButton name="Channel_Setting" value=">>" htmlClass="pBtn0" /></td>
                        <td><ezf:inputText name="coaChDescTxt_H1" ezfName="coaChDescTxt_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"70\" maxlength=\"50\""/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="stab"><ezf:anchor name="coaAfflCd_H2" ezfName="coaAfflCd_H2" ezfEmulateBtn="1" ezfGuard="Search_Affiliate" >Intercompany</ezf:anchor></td>
                        <td><ezf:inputText name="coaAfflCd_H1" ezfName="coaAfflCd_H1" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
                        <td><ezf:inputButton name="Affiliate_Setting" value=">>" htmlClass="pBtn0" /></td>
                        <td><ezf:inputText name="coaAfflDescTxt_H1" ezfName="coaAfflDescTxt_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"70\" maxlength=\"50\""/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="stab"><ezf:anchor name="coaProjCd_H2" ezfName="coaProjCd_H2" ezfEmulateBtn="1" ezfGuard="Search_Project" >Merchandise</ezf:anchor></td>
                        <td><ezf:inputText name="coaProjCd_H1" ezfName="coaProjCd_H1" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/></td>
                        <td><ezf:inputButton name="Project_Setting" value=">>" htmlClass="pBtn0" /></td>
                        <td><ezf:inputText name="coaProjDescTxt_H1" ezfName="coaProjDescTxt_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"70\" maxlength=\"50\""/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="stab"><ezf:anchor name="coaExtnCd_H2" ezfName="coaExtnCd_H2" ezfEmulateBtn="1" ezfGuard="Search_Extension" >Business Unit</ezf:anchor></td>
                        <td><ezf:inputText name="coaExtnCd_H1" ezfName="coaExtnCd_H1" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
                        <td><ezf:inputButton name="Extension_Setting" value=">>" htmlClass="pBtn0" /></td>
                        <td><ezf:inputText name="coaExtnDescTxt_H1" ezfName="coaExtnDescTxt_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"70\" maxlength=\"50\""/></td>
                        <td></td>
                    </tr>
                </table>
                <hr>
        <%--
                <table border="0" cellpadding="1" cellspacing="1">
                    <tr height="25">
                        <td width="680" align="right">
                            <table border="0" cellpadding="1" cellspacing="0">
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
                                    <td class="pOut">2</td>
                                    <td class="stab">of</td>
                                    <td class="pOut">2</td>
                                    <td>&nbsp;</td>
                                    <td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn3" /></td>
                                    <td></td>
                                    <td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn3" /></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
        --%>
                <table border="0" cellpadding="1" cellspacing="0" width="95%" align="center">
                    <tr>
                        <td width="320" align="right">
                            <table border="0" cellpadding="1" cellspacing="0" align="left">
                                <col width="120"  align="center">
                                <col width="80"  align="left">
                                <col width="5"  align="left">
                                <col width="10"  align="left">
                                <tr>
                                    <td><ezf:inputText name="xxDtlCd_F1" ezfName="xxDtlCd_F1" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                    <td><ezf:inputText name="dtlDescTxt_F1" ezfName="dtlDescTxt_F1" otherAttr=" size=\"20\" maxlength=\"70\""/></td>
                                    <td>&nbsp;</td>
                                    <td><ezf:inputButton name="Filter" value="Filter" htmlClass="pBtn3" /></td>
                                </tr>
                            </table>
                        </td>
                        <td>
        <%-- Page Controler --%>
                            <table border="0" cellpadding="1" cellspacing="0" width="95%" align="center">
                                <tr>
                                    <td width="100%" align="right">
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
                        </td>
                    </tr>
                </table>
                <table align="center">
                    <tr>
                        <td>
                            <table border="1" cellpadding="1" cellspacing="0">
                                <col width="120" align="center">
                                <col width="550" align="center">

                                <%@ page import="business.servlet.NMAL2550.NMAL2550BMsg" %>
                                <% NMAL2550BMsg bMsg = (NMAL2550BMsg) databean.getEZDBMsg(); %>
                                <% String xxDtlCdLbNm = bMsg.xxDtlCdLbNm.getValue(); %>
                                <% if (xxDtlCdLbNm == null || "".equals(xxDtlCdLbNm)) { %>
                                <tr height="">
                                    <td class="pClothBs"><br></td>
                                    <td class="pClothBs"><br></td>
                                </tr>
                                <% } else { %>
                                <tr height="">
                                    <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDtlCd_A1' )" ><ezf:label name="xxDtlCdLbNm" ezfName="xxDtlCdLbNm" /><img id="sortIMG.xxDtlCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                    <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dtlDescTxt_A1' )" ><ezf:label name="xxDtlNmLbNm" ezfName="xxDtlNmLbNm" /><img id="sortIMG.dtlDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                </tr>
                                <% } %>
                            </table>
                            <div style="overflow-y:scroll; height:254; width:697;">
                                <table border="1" cellpadding="1" cellspacing="0" id="A">
                                    <col width="120" align="center">
                                    <col width="550" align="left">

                                    <tbody>
                                        <ezf:row ezfHyo="A">
                                            <tr height="" id="id_row{EZF_ROW_NUMBER}">
                                                <td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_ConditionCode" ><ezf:label name="xxDtlCd_A1" ezfName="xxDtlCd_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
                                                <td><ezf:inputText name="dtlDescTxt_A1" ezfName="dtlDescTxt_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"78\" maxlength=\"240\" style=\"border:none; background-color:transparent;\""/></td>
                                            </tr>
                                        </ezf:row>
                                        <ezf:skip>
                                            <tr height="">
                                                <td><a href="#" onclick="sendServer('Select_ConditionCode')" ezfhyo="A"><label ezfout name="xxDtlCd_A1" ezfname="xxDtlCd_A1" ezfhyo="A">110000</label></a></td>
                                                <td><label ezfout name="dtlDescTxt_A1" ezfname="dtlDescTxt_A1" ezfhyo="A">Camera Marketing Division: sales</label></td>
                                            </tr>
                                            <tr height="">
                                                <td><a href="#" onclick="sendServer('Select_ConditionCode')" ezfhyo="A"><label ezfout name="xxDtlCd_A1" ezfname="xxDtlCd_A1" ezfhyo="A">110000</label></a></td>
                                                <td><label ezfout name="dtlDescTxt_A1" ezfname="dtlDescTxt_A1" ezfhyo="A">Camera Marketing Division: sales</label></td>
                                            </tr>
                                            <tr height="">
                                                <td><a href="#" onclick="sendServer('Select_ConditionCode')" ezfhyo="A"><label ezfout name="xxDtlCd_A1" ezfname="xxDtlCd_A1" ezfhyo="A">110000</label></a></td>
                                                <td><label ezfout name="dtlDescTxt_A1" ezfname="dtlDescTxt_A1" ezfhyo="A">Camera Marketing Division: sales</label></td>
                                            </tr>
                                            <tr height="">
                                                <td><a href="#" onclick="sendServer('Select_ConditionCode')" ezfhyo="A"><label ezfout name="xxDtlCd_A1" ezfname="xxDtlCd_A1" ezfhyo="A">110000</label></a></td>
                                                <td><label ezfout name="dtlDescTxt_A1" ezfname="dtlDescTxt_A1" ezfhyo="A">Camera Marketing Division: sales</label></td>
                                            </tr>
                                            <tr height="">
                                                <td><a href="#" onclick="sendServer('Select_ConditionCode')" ezfhyo="A"><label ezfout name="xxDtlCd_A1" ezfname="xxDtlCd_A1" ezfhyo="A">110000</label></a></td>
                                                <td><label ezfout name="dtlDescTxt_A1" ezfname="dtlDescTxt_A1" ezfhyo="A">Camera Marketing Division: sales</label></td>
                                            </tr>
                                            <tr height="">
                                                <td><a href="#" onclick="sendServer('Select_ConditionCode')" ezfhyo="A"><label ezfout name="xxDtlCd_A1" ezfname="xxDtlCd_A1" ezfhyo="A">110000</label></a></td>
                                                <td><label ezfout name="dtlDescTxt_A1" ezfname="dtlDescTxt_A1" ezfhyo="A">Camera Marketing Division: sales</label></td>
                                            </tr>
                                            <tr height="">
                                                <td><a href="#" onclick="sendServer('Select_ConditionCode')" ezfhyo="A"><label ezfout name="xxDtlCd_A1" ezfname="xxDtlCd_A1" ezfhyo="A">110000</label></a></td>
                                                <td><label ezfout name="dtlDescTxt_A1" ezfname="dtlDescTxt_A1" ezfhyo="A">Camera Marketing Division: sales</label></td>
                                            </tr>
                                            <tr height="">
                                                <td><a href="#" onclick="sendServer('Select_ConditionCode')" ezfhyo="A"><label ezfout name="xxDtlCd_A1" ezfname="xxDtlCd_A1" ezfhyo="A">110000</label></a></td>
                                                <td><label ezfout name="dtlDescTxt_A1" ezfname="dtlDescTxt_A1" ezfhyo="A">Camera Marketing Division: sales</label></td>
                                            </tr>
                                            <tr height="">
                                                <td><a href="#" onclick="sendServer('Select_ConditionCode')" ezfhyo="A"><label ezfout name="xxDtlCd_A1" ezfname="xxDtlCd_A1" ezfhyo="A">110000</label></a></td>
                                                <td><label ezfout name="dtlDescTxt_A1" ezfname="dtlDescTxt_A1" ezfhyo="A">Camera Marketing Division: sales</label></td>
                                            </tr>
                                            <tr height="">
                                                <td><a href="#" onclick="sendServer('Select_ConditionCode')" ezfhyo="A"><label ezfout name="xxDtlCd_A1" ezfname="xxDtlCd_A1" ezfhyo="A">110000</label></a></td>
                                                <td><label ezfout name="dtlDescTxt_A1" ezfname="dtlDescTxt_A1" ezfhyo="A">Camera Marketing Division: sales</label></td>
                                            </tr>
                                            <tr height="">
                                                <td><a href="#" onclick="sendServer('Select_ConditionCode')" ezfhyo="A"><label ezfout name="xxDtlCd_A1" ezfname="xxDtlCd_A1" ezfhyo="A">110000</label></a></td>
                                                <td><label ezfout name="dtlDescTxt_A1" ezfname="dtlDescTxt_A1" ezfhyo="A">Camera Marketing Division: sales</label></td>
                                            </tr>
                                            <tr height="">
                                                <td><a href="#" onclick="sendServer('Select_ConditionCode')" ezfhyo="A"><label ezfout name="xxDtlCd_A1" ezfname="xxDtlCd_A1" ezfhyo="A">110000</label></a></td>
                                                <td><label ezfout name="dtlDescTxt_A1" ezfname="dtlDescTxt_A1" ezfhyo="A">Camera Marketing Division: sales</label></td>
                                            </tr>
                                            <tr height="">
                                                <td><a href="#" onclick="sendServer('Select_ConditionCode')" ezfhyo="A"><label ezfout name="xxDtlCd_A1" ezfname="xxDtlCd_A1" ezfhyo="A">110000</label></a></td>
                                                <td><label ezfout name="dtlDescTxt_A1" ezfname="dtlDescTxt_A1" ezfhyo="A">Camera Marketing Division: sales</label></td>
                                            </tr>
                                            <tr height="">
                                                <td><a href="#" onclick="sendServer('Select_ConditionCode')" ezfhyo="A"><label ezfout name="xxDtlCd_A1" ezfname="xxDtlCd_A1" ezfhyo="A">110000</label></a></td>
                                                <td><label ezfout name="dtlDescTxt_A1" ezfname="dtlDescTxt_A1" ezfhyo="A">Camera Marketing Division: sales</label></td>
                                            </tr>
                                            <tr height="">
                                                <td><a href="#" onclick="sendServer('Select_ConditionCode')" ezfhyo="A"><label ezfout name="xxDtlCd_A1" ezfname="xxDtlCd_A1" ezfhyo="A">110000</label></a></td>
                                                <td><label ezfout name="dtlDescTxt_A1" ezfname="dtlDescTxt_A1" ezfhyo="A">Camera Marketing Division: sales</label></td>
                                            </tr>
                                            <tr height="">
                                                <td><a href="#" onclick="sendServer('Select_ConditionCode')" ezfhyo="A"><label ezfout name="xxDtlCd_A1" ezfname="xxDtlCd_A1" ezfhyo="A">110000</label></a></td>
                                                <td><label ezfout name="dtlDescTxt_A1" ezfname="dtlDescTxt_A1" ezfhyo="A">XXXXXXXXXXYYYYYYYYYYZZZZZZZZZZXXXXXXXXXXYYYYYYYYYYZZZZZZZZZZXXXXXXXXXX</label></td>
                                            </tr>
                                        </ezf:skip>
                                    </tbody>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</center>


<%-- **** Task specific area ends here **** --%>
