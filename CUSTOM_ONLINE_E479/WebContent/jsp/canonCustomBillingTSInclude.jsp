<%@page import="java.math.BigDecimal"%>
<%@ page import="oracle.apps.custombilling.util.CanonCustomBillingConstants"%>
<%@ page import="oracle.apps.custombilling.beans.CanonCustomBillingSearchingBean"%>
<%@ page import="oracle.apps.custombilling.dao.CanonCustomBillingSearchingDAO"%>
<%@ page import="oracle.apps.custombilling.util.CanonCustomBillingCommon"%>
<%
    CanonCustomBillingCommon C=new CanonCustomBillingCommon();
    String action= C.nonNullify(request.getParameter("action"));
    int userId = C.toInt(request.getParameter("userId"));
    System.out.println(" in canonCustomBillingTSInclude action is "+action);
    if("NEW_OR_UPDATE".equals(action) || "COPY".equals(action)){
    	  String p_parent_customer = request.getParameter("parentCustomerName");
          String p_group_name = request.getParameter("customerGroup");
          String p_customer_name = request.getParameter("customerName");
          String p_template_level = request.getParameter("templateLevel");
          System.out.println("###: in TSIncludejsp " + p_customer_name + "|" + p_group_name + "|" + p_parent_customer + "|" +  p_template_level );
        pageContext.forward("canonCustomBillingTemplateSetup.jsp");
    }else{

    String searchString = null;

    String searchCustomerProfile = CanonCustomBillingCommon.nonNullify(
            request.getParameter("searchCustomerProfile"));
    
    String searchCustomerGroup = CanonCustomBillingCommon.nonNullify(
            request.getParameter("searchCustomerGroup"));

    String searchParentCustomer = CanonCustomBillingCommon.nonNullify(
            request.getParameter("searchParentCustomer"));

    String searchBillToSite = CanonCustomBillingCommon.nonNullify(
            request.getParameter("searchBillToSite"));

    String searchCustomerName = CanonCustomBillingCommon.nonNullify(
            request.getParameter("searchCustomerName"));

    String searchType = CanonCustomBillingCommon.nonNullify(request.getParameter("searchType"));

    String acctNum = CanonCustomBillingCommon.nonNullify(
            request.getParameter("acctNum"));

    String psn = CanonCustomBillingCommon.nonNullify(
            request.getParameter("psn"));
    String selectedTemplate= CanonCustomBillingCommon.nonNullify(
            request.getParameter("selectedTemplate"));
    String selectedTemplateId= CanonCustomBillingCommon.nonNullify(
            request.getParameter("selectedTemplateId"));

    if (searchType.equalsIgnoreCase(CanonCustomBillingConstants.SEARCH_TYPE_GROUP)) {
        searchString = searchCustomerGroup;
    }else if (searchType.equalsIgnoreCase(CanonCustomBillingConstants.SEARCH_TYPE_PARENT_CUSTOMER)) {
        searchString = searchParentCustomer;
    } else if (searchType.equalsIgnoreCase(CanonCustomBillingConstants.SEARCH_TYPE_SITE)) {
        searchString = searchBillToSite;
    } else if (searchType.equalsIgnoreCase(CanonCustomBillingConstants.SEARCH_TYPE_CUSTOMER)) {
        searchString = searchCustomerName;
    }

	int totalRecords  = 0;  
    CanonCustomBillingSearchingDAO customDao = new CanonCustomBillingSearchingDAO();
    java.util.ArrayList searchList = new java.util.ArrayList();
    System.out.println("searchString--aaa-->" + searchString);
    System.out.println("searchType-bbb-" + searchType);
    
	if (searchType!=null && searchType.length()> 0){
		searchList = customDao.getCustomBillSearchDtls(searchString, searchType);
		totalRecords = searchList.size();
	}

    int i = 0;

%>



<form name="mainForm" id ="mainForm" method="post" 
      action="canonCustomBillingTSInclude.jsp">

    <input type="hidden" name ="searchType" id ="searchType" value="<%=searchType%>">
    <input type="hidden" name="templateId" >
    <input type="hidden" name="action">
    <input type="hidden" name="selectedTemplate" id="selectedTemplate" value="<%=selectedTemplate%>">
    <input type="hidden" name="selectedTemplateId" id="selectedTemplateId" value="<%=selectedTemplateId%>">
    <input type="hidden" name="parentCustomerName" >
    <input type="hidden" name="customerGroup" >
    <input type="hidden" name="customerName" >
    <input type="hidden" name="templateLevel" >
    <input type="hidden" name="billToUse" >
    
    <table align="center" width="850" border="0" cellspacing="0" cellpadding="0" summary="">
        <tr>
            <td class="sectionHeader1"><h1>Create/Search Template </h1><br></td>
        </tr>
    </table>

    <table align="center" width="850" border="0" cellspacing="0" cellpadding="0" summary="">
        <tr>
            <td>
                <div id="accordion" style="font-size:70.5%;">

                    <h4><a href="#">Search By Customer Group</a></h4>
                    <div>
                        <p>
                        <table >
                            <tr valign="middle">
                                <td class="bsd_text" align="left"><b>Customer Group: </b></td> 
                                <td class="bsd_text" align="left">
                                    <input type="text" name="searchCustomerGroup" 
                                           id="searchCustomerGroup"    value="<%=searchCustomerGroup%>" ></td> 
                                <td>&nbsp;</td>	
                            <input type="hidden" name="customerGroup" id="customerGroup" >

                            <td class="buttons">
                                <a href="#" class="negative customerGroupDot"  style="text-align='center';font-family='Verdana,Arial,sans-serif';font-size='12px';height='15px';margin:0 0 0 0;padding:2px 2px 2px 2px;">...</a>
                            </td>					
                            <td class="buttons" colspan="3" valign="bottom">
                                <a href="#" 
                                   onclick="searchTemplate('<%=CanonCustomBillingConstants.SEARCH_TYPE_GROUP%>',0)"
                                   class="negative" style="height='25px';width='100px';">
                                    Search
                                </a> 

                            </td> 			
                            <td class="buttons" colspan="3" valign="bottom">
                                <a id="cleanCustomerGroup" href="#" 
                                   class="negative" style="height='25px';width='100px';">   
                                    Clear
                                </a> 
                            </td> 			
                            </tr>
                            <tr><td colspan="6">&nbsp;</td></tr>
                        </table>
                        </p>
                    </div>
                    
                     <h4><a href="#">Search By Parent Customer</a></h4>
                    <div>
                        <p>
                        <table >
                            <tr valign="middle">
                                <td class="bsd_text" align="left"><b>Parent Customer: </b></td> 
                                <td class="bsd_text" align="left">
                                    <input type="text" name="searchParentCustomer" 
                                           id="searchParentCustomer"    value="<%=searchParentCustomer%>" ></td> 
                                <td>&nbsp;</td>	
                            <input type="hidden" name="parentCustomer" id="parentCustomer" >

                            <td class="buttons">
                                <a href="#" class="negative parentCustomerDot"  style="text-align='center';font-family='Verdana,Arial,sans-serif';font-size='12px';height='15px';margin:0 0 0 0;padding:2px 2px 2px 2px;">...</a>
                            </td>					
                            <td class="buttons" colspan="3" valign="bottom">
                                <a href="#" 
                                   onclick="searchTemplate('<%=CanonCustomBillingConstants.SEARCH_TYPE_PARENT_CUSTOMER%>',1)"
                                   class="negative" style="height='25px';width='100px';">
                                    Search
                                </a> 

                            </td> 			
                            <td class="buttons" colspan="3" valign="bottom">
                                <a id="cleanParentCustomer" href="#" 
                                   class="negative" style="height='25px';width='100px';">   
                                    Clear
                                </a> 
                            </td> 			
                            </tr>
                            <tr><td colspan="6">&nbsp;</td></tr>
                        </table>
                        </p>
                    </div>

                    <h4><a href="#">Search By Customer Name</a></h4>
                    <div>
                        <p>
                        <table>
                            <tr>
                                <td class="bsd_text" align="left"><b>Customer Name#:</b></td> 
                                <td class="bsd_text" align="left">
                                    <input type="text" name="searchCustomerName" 
                                           id="searchCustomerName" value="<%=searchCustomerName%>" ></td> 
                            <input type="hidden" name="customerName" id="customerName" > 
                            <td class="bsd_text">&nbsp;</td>
                            <td class="buttons">
                                <a href="#" class="negative customerNameDot"  style="text-align='center';font-family='Verdana,Arial,sans-serif';font-size='12px';height='15px';margin:0 0 0 0;padding:2px 2px 2px 2px;">...</a>
                            </td>						
                            <td class="buttons" colspan="3">
                                <a href="#" 
                                   onclick="searchTemplate('<%=CanonCustomBillingConstants.SEARCH_TYPE_CUSTOMER%>',2)"
                                   class="negative" style="height='25px';width='100px';">   
                                    Search
                                </a> 
                            </td> 			
                            <td class="buttons" colspan="3" valign="bottom">
                                <a id="clearCustomerName" href="#" 
                                   class="negative" style="height='25px';width='100px';">   
                                    Clear
                                </a> 
                            </td> 			
                            </tr>
                        </table>
                        </p>
                    </div>

                    <h4><a href="#">Search By Bill To Location</a></h4>
                    <div>
                        <p>
                        <table>
                            <tr>
                                <td class="bsd_text" align="left"><b>Location:</b></td> 
                                <td class="bsd_text" align="left">
                                    <input type="text" name="searchBillToSite" 
                                           id="searchBillToSite" value="<%=searchBillToSite%>" ></td>
                                <input type="hidden" name="BillTo" id="BillTo" > 
                                <td class="bsd_text">&nbsp;</td>	
                                <td class="buttons">
                                    <a href="#" class="negative billToSiteDot"  style="text-align='center';font-family='Verdana,Arial,sans-serif';font-size='12px';height='15px';margin:0 0 0 0;padding:2px 2px 2px 2px;">...</a>
                                </td>						

                                <td class="buttons"  colspan="3" valign="middle">
                                    <a href="#" 
                                       onclick="searchTemplate('<%=CanonCustomBillingConstants.SEARCH_TYPE_SITE%>',3)"
                                       class="negative" style="height='25px';width='100px';">   
                                        Search
                                    </a> 
                                </td> 		
                                <td class="buttons" colspan="3" valign="bottom">
                                    <a id="clearBillToSite" href="#" 
                                       class="negative" style="height='25px';width='100px';">   
                                        Clear
                                    </a> 
                                </td> 			

                            </tr>
                        </table>         
                    </div>

                </div>    

            </td>
        </tr>
    </table>

    <table align="center" width="850" border="0" cellspacing="0" cellpadding="0" summary="">
        <tr>
            <td>&nbsp;</td>
        </tr>

    </table>

    <table align="center" width="850" border="0" cellspacing="1" cellpadding="2" summary="" id="templateTable">
        <tr class="canontableCell">
            <th height="30" style="text-align:left;">
                Template ID</th>
            <th height="30"  style="text-align:left;">
                Parent Customer Name </th>     
			<th height="30"  style="text-align:left;">
                Group Name </th>     
            <th height="30"  style="text-align:left;">
                Customer  Name</th>
            <th height="30" style="text-align:left;">Bill To Use</th>	
            <th height="30"  nowrap="nowrap" style="text-align:left;">
                Other Email
            </th>
            <th height="30"   nowrap="nowrap" style="text-align:left;">
                Customer Email
            </th>
            <th height="30"   nowrap="nowrap" style="text-align:left;">
                Status
            </th>

            <th height="30" style="text-align:left;">Action</th>

        </tr>

        <%
            if (totalRecords == 0 && searchType!=null && searchType.length()> 0) {
        %>
        <tr>
            <td cellpadding="9" align="left" class="eventableDataCell" colspan="9">
                <span class="bsd_text">No records found for entered search criteria. Please change your search criteria and try again.</span>
            </td>       
        </tr>  

        <%} else {

            for (i = 0; i < totalRecords; i++) {
                CanonCustomBillingSearchingBean bean =
                        (CanonCustomBillingSearchingBean) searchList.get(i);
                String templateId = CanonCustomBillingCommon.nonNullify(bean.getHeaderId());
                System.out.println("in TS Include JSP-> TemplateID"+templateId);
                String parentCustomerName = CanonCustomBillingCommon.nonNullify(bean.getParentCustomerName());
				String customerGrp = CanonCustomBillingCommon.nonNullify(bean.getGroupName());
                String customersName = CanonCustomBillingCommon.nonNullify(bean.getCustomerName());
                String billToUse = CanonCustomBillingCommon.nonNullify(bean.getBillToUse());
                String templateLevel = CanonCustomBillingCommon.nonNullify(bean.getTemplateLevel());
                String otherEmail = bean.getOtherEmail();
                String customerEmail = bean.getCustomerEmail();
                
                System.out.println(" value of grp "+customerGrp);
                
                String bclr = "";

                if ((i % 2) == 0) {
                    bclr = "eventableDataCell";

                } else {
                    bclr = "oddtableDataCell";

                }

        %>
        <tr class='<%=bclr%>' data-template='{"templateId":<%=templateId%>,"parentCustomerName":"<%=parentCustomerName%>","customerGroup":"<%=customerGrp%>","customerName":"<%=customersName%>","billToUse":"<%=billToUse%>","templateLevel":"<%=templateLevel%>"}'>
            <td style="word-break:break-all" >
                <%="-1".equals(templateId)? "": templateId%>
            </td>
            <td style="word-break:break-all" nowrap="nowrap">
                <%=parentCustomerName%>
            </td>
             <td style="word-break:break-all" nowrap="nowrap">
                <%=customerGrp%>
            </td>          
            <td style="word-break:break-all" nowrap="nowrap">
                <%=customersName%>
            </td> 
            <td style="word-break:break-all" nowrap="nowrap">
                <%="-1".equals(billToUse)? "": billToUse%>
            </td>  
            <td style="word-break:break-all" >
                <%=CanonCustomBillingCommon.nonNullify(otherEmail)%>
            </td>       

            <td style="word-break:break-all">
                <%=CanonCustomBillingCommon.nonNullify(customerEmail)%>
            </td>       
            <td style="word-break:break-all">
                <%=CanonCustomBillingCommon.capital(CanonCustomBillingCommon.nonNullify(bean.getStatusFlag()))%>
            </td>       

            <td style="word-break:break-all" nowrap="nowrap">
                <a href="#" class="action" > <%="-1".equals(templateId) ?  "New" : "Update" %> </a><br>
                <%  if("-1".equals(templateId)){ 
                        if(!selectedTemplateId.equals("") && !selectedTemplateId.equals("-1")){
                          
                %>
                <a href="#" class="paste" >Copy From Selected Template</a>
                <%      }  
                    }else{%>
                <a href="#" class="copy" >Select For Copy</a>
                <%}%>
                
            </td>       

        </tr>
        <%
                }


            }
        %>
    </table>
    <div id="selected_template" class="bsd_text" data-template='<%=selectedTemplate%>' style="width: 850px;text-align: center;"></div>
        <script type='text/javascript'>
            selectTemplateForCopy();
        </script>        


</form>
    
<%
}
%>
    
