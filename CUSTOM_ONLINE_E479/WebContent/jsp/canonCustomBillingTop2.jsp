<%@page import="java.math.BigDecimal"%>
<%@page import="java.io.IOException" %>
<%-- <%@page language="java" import="oracle.apps.ibe.customer.internal.*" %>	 --%>	
<%@page import="oracle.apps.fnd.common.WebRequestUtil"%>
<%-- <%@page import="oracle.apps.fnd.common.URLTools"%> --%>
<%@page import="oracle.apps.custombilling.util.CanonCustomBillingCommon"%>
<%@page import="oracle.apps.custombilling.util.CanonCustomBillingUtil"%>
<%@page import="oracle.apps.custombilling.dao.CanonCustomBillingSearchingDAO"%>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
    
<%
oracle.apps.fnd.common.WebAppsContext objWebAppsContext1 =
WebRequestUtil.validateContext(request, response);
CanonCustomBillingUtil util = new CanonCustomBillingUtil();

String userNm = objWebAppsContext1.getUserName();
System.out.println(" CustomBillingTop2: User name: "+userNm);
    boolean isUserBiller = util.isUserBiller(userNm);
	//boolean isUserBiller = "Y".equals((String) CanonCustomBillingCommon.first(CanonCustomBillingSearchingDAO.isUserBiller(new BigDecimal(userId))));
%>    
		<div class="page_container_ie"> 
		<div class="page_container">


		<!-- START PAGE TOP SECTION: HEADER, TOP NAVBAR, BREADCRUMB -->
		<!-- START PAGE HEADER -->
		<div id="page_header" class="clearfix">
			<div id="main_logo"><a href="#"><img src="/s21extn/common/images/csa_logo.jpg" alt="Canon Logo"></a></div>

			<div id="corporate_navbar" class="clearfix">
				<div id="support_quick_links">
					<ul>  
						<li><a href="http://www.usa.canon.com/opd/controller?act=OPDDownloadDropDownAct" target="_blank">Drivers &amp; Downloads</a></li>
                    </ul>
				</div>

				<div class="clearfix">
				<div id="quick_links"></div>

				<div align = "right" id="quick_search">
					<form action="" name="">
					<table cellspacing="0" border="0">
						<tr>
 						    <td colspan=5>&nbsp;</td>							
                            <td align="right"><A class=navC1TextDeslct href="/OA_HTML/jtfalout.jsp" target=_parent>Logout</A></td>
						</tr>
					</table>
					</form>

				</div>
				</div>
			</div>
		</div>
		<!-- END PAGE HEADER -->



		<!-- START PAGE TOP NAVBAR -->
		<div id="top_navbar">
			<div class="clearfix">
			  <ul>

                   <li id="service_request">
                           <%-- <a href="<%=URLTools.generateSecureURL("OA.jsp?OAFunc=CANON_E479_INVOICE_SEARCH", objWebAppsContext)%>" >Search Special Bills</a> --%>
                           <a href="/s21extn/e479/jsp/canonCustomBillingInvSearch.jsp" >Search Special Bills</a>
                   </li>
                    <%if (isUserBiller)	{ %>
                    <li id="account_management">
                        <%-- <a href="<%=URLTools.generateSecureURL("OA.jsp?OAFunc=CANON_E479_MANUAL_UPLOAD", objWebAppsContext)%>" >Upload Manual Special Bills</a> --%>
                        <a href="/s21extn/e479/jsp/canonCustomBillingManualUpload.jsp" >Upload Manual Special Bills</a>
                    </li>
                     <% } else { %>
                     <li id="account_management">
                         <a id="dummy_lnk"></a>
                     </li>
                     <% } %>	                               
				<li id="about_us">
					<a id="dummy_lnk"></a>
				</li>
				<li id="home">
					<a onmouseover="showDropdown('dummy', true);" onfocus="showDropdown('dummy', true);" onmouseout="showDropdown('dummy', false);" onblur="showDropdown('dummy', false);" id="dummy_lnk"></a>
				</li>
				<li id="products">
					<a onmouseover="showDropdown('dummy', true);" onfocus="showDropdown('dummy', true);" onmouseout="showDropdown('dummy', false);" onblur="showDropdown('dummy', false);" id="dummy_lnk"></a>
				</li>
				<li id="workflow_solutions">
					<a  onmouseover="showDropdown('dummy', true);" onfocus="showDropdown('dummy', true);" onmouseout="showDropdown('dummy', false);" onblur="showDropdown('dummy', false);" id="dummy_lnk"></a>
				</li>
			  </ul>
			</div>

			<!-- START DROPDOWN -->
			<div id="service_request_sub" class="dropdown clearfix" onmouseover="getDropdownCapture();" onmouseout="showDropdown('service_request', false);">
				<div class="dropdown_subcontainer">
					<ul>
					    <li><a href="/OA_HTML/canonEpartnersOpenSr.jsp">Open Service Calls</a></li>
					    <%--  
						<li><a href="ePartnerClosedSR.htm">Closed Service Calls</a></li>
						--%>
						<li><a href="/OA_HTML/canonEpartnersServiceRequest.jsp">Create Service Call</a></li>
                    </ul>
				</div>
			</div>

			<div id="account_management_sub" class="dropdown clearfix" onmouseover="getDropdownCapture();" onmouseout="showDropdown('account_management', false);">
				<div class="dropdown_subcontainer">
					<ul>
					    <li><a href="/OA_HTML/canonEpartnersAcctMngmt.jsp">Account Management</a></li>
					    <%--
						<li><a href="ePartnerRequestSerial.htm">Request Serial Numbers</a></li>
						--%>
                    </ul>
				</div>
			</div>
			<!-- END DROPDOWN -->
		</div>
		<!-- END PAGE TOP NAVBAR -->


		<!-- START PAGE BREADCRUMB -->
		<div id="home_page_breadcrumb"></div>		<!-- END PAGE BREADCRUMB -->
