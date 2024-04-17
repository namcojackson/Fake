<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.canon.apps.e001.CanonE001CodeTableDAO"%>
<%@page import="com.canon.apps.e001.CanonE001CommonUtil"%>
<%@page import="java.text.*"%>
<%@page import="java.util.*"%>
<%@page import="java.util.Date"%>
<%@page import="static com.canon.apps.e001.CanonE001CommonUtil.checkResult"%>
<%@page import="static com.canon.apps.e001.CanonE001CommonUtil.checkErrors"%>
<%@page import="static com.canon.apps.e001.CanonE001CommonUtil.E001Exception"%>
<%@page errorPage="canonE001ErrorPage.jsp"%>

<%
  	
  String strAction = request.getParameter("action");
  if("clear".equals(strAction)){
  	session.removeAttribute("search_cd_name");
  	response.sendRedirect(request.getRequestURI());
  }else if("change".equals(strAction)){
	  pageContext.forward("canonE001CodeTableDetail.jsp");
  }else if("create_new".equals(strAction)){
	  pageContext.forward("canonE001CodeTableDetail.jsp");
  }else{
	  String sortBy = null;
	  String sortOrder = "asc";  
	  if(request.getParameter("sortBy")!=null && !request.getParameter("sortBy").isEmpty()){
		sortBy = request.getParameter("sortBy");
	  }else{
		  sortBy ="CD_NAME";
	  }
	  
	  if(request.getParameter("sortOrder")!=null && !request.getParameter("sortOrder").isEmpty()){
		sortOrder = request.getParameter("sortOrder");
	  }	
	  System.out.println("strAction : "+ strAction);
%>

<%
		int pageNumber = (request.getParameter("pageNumber")==null)?1:Integer.parseInt(request.getParameter("pageNumber"));
		int count=10; 
		int start = ((pageNumber-1)*count) + 1 ;
		int end= pageNumber*count;
		String contextPath =request.getContextPath();
		int totalLks=0;
	  	String searchCdName= request.getParameter("search_cd_name");
	  	if(searchCdName==null){
	  		searchCdName=(String)session.getAttribute("search_cd_name");
	  	}
	    Object[] obj = CanonE001CodeTableDAO.codeNameSearch(CanonS21SessionValidate.getUserName(), searchCdName, sortBy, sortOrder, new BigDecimal(pageNumber), new BigDecimal(10));
        checkErrors(obj, 2,3);
        session.setAttribute("search_cd_name",searchCdName);
		
	    int totalRecords = 0;
		if(CanonE001CommonUtil.second(obj)!=null){
			totalRecords = ((BigDecimal)CanonE001CommonUtil.second(obj)).intValue();
		}
		List<CanonE001CodeTableDAO.CodeTableInfo> lstSearchRes = new ArrayList<CanonE001CodeTableDAO.CodeTableInfo>();
		if(CanonE001CommonUtil.first(obj)!=null){
			lstSearchRes = (List<CanonE001CodeTableDAO.CodeTableInfo>) CanonE001CommonUtil.first(obj);
		}
		totalLks = (totalRecords%count>0)?((totalRecords/count)+1):totalRecords/count;		
		String pageLinkMsg="";
 	   
	    if(totalLks>1){
	    	  pageLinkMsg= start+" to "+(end-(count-lstSearchRes.size())) +" of "+ totalRecords +" records found.";
	    }
	    System.out.println("pageLinkMsg : " + pageLinkMsg);
	    String strErrStyle="align:center;";
	    if(lstSearchRes.size()>10){
	    	strErrStyle="height: 400px;  overflow-y:auto;align:center;";
	    }
%>
<div id="page_top">
	<h1>Custom Code Table</h1>
</div>
<div><h1>&nbsp;&nbsp;Home</h1></div>
<div id="errorWidget"
	style="display: none; padding-bottom: 5px; padding-left: 20px; padding-top: 5px; color: red;">
	<p id="eMsg"></p>
</div>
<div class="table-inner">
	<form id="machSearchFrm" name="machSearchFrm"
		action="canonE001CodeTable.jsp" method="post">
		<input type='hidden' name='sortOrder' id='sortOrder'
			value="<%=sortOrder%>" /> <input type='hidden' name='sortBy'
			id='sortBy' value="<%=sortBy%>" /> <input type="hidden"
			name="cd_id" id="cd_id" value=""> <input
			type="hidden" name="action" id="action" value="">
 			<input type='hidden' name='pageNumber' id='pageNumber' value="1" />			
		<div class="service">
			<table id="profileSearchTable">
				<tr>
					<th>Code Table Search</th>
				</tr>
				<tr>
					<td><input style="width: 400px" type="text"
						id="search_cd_name" name="search_cd_name" class="search_cd_name"
						placeholder="Code Table Name"
						value="<%=CanonE001CommonUtil.checkNull(searchCdName)%>"></td>
				</tr>
				<tr>
					<td style="text-align: right;"><a href="#"
						id="searchCodeTableButton" class="btn">Search</a> <a href="#"
						id="clearButton" class="btn">Clear</a> <a href="#"
						id="createCodeTableButton" class="btn">Create Code Table</a></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div id="resultsDiv">

	<div class="table-inner">
		<table width="100%" id="pgLinks" cellspacing="0">
			<tr>
				<td width="50%" id="pagination"><jsp:include
						page="canonE001Pagination.jsp">
						<jsp:param value="<%=totalLks%>" name="totalLinks" />
						<jsp:param value="<%=pageNumber%>" name="pageNum" />
					</jsp:include></td>
				<%
					if(pageLinkMsg!="" && pageLinkMsg!=null){
				%>
				<td width="50%" align="right" id="showing"><b>Showing</b>&nbsp;<%=pageLinkMsg%></td>
				<%
					}
				%>
			</tr>
		</table>
		<table class="supplies-table" cellspacing="1">
			<tr>
				<th style="width: 30%;"><a
					style="color: #FFFFFF; text-decoration: underline; text-align: left;"
					href="#" data-sort_by="CD_NAME" class="sorting">Code Table
						Name</a></th>
				<th style="width: 50%;"><a
					style="color: #FFFFFF; text-decoration: underline; text-align: left;"
					href="#" data-sort_by="CD_DESCRIPTION" class="sorting">Code Table
						Description</a></th>
				<th style="width: 20%;"><a
					style="color: #FFFFFF; text-decoration: underline; text-align: left;"
					href="#" data-sort_by="CD_VIEW_NAME" class="sorting">View Name
						</a></th>
			</tr>
			<%
				if(lstSearchRes!=null && lstSearchRes.size()>0){
					for(int i=0;i<lstSearchRes.size();i++){
						CanonE001CodeTableDAO.CodeTableInfo resultObj = lstSearchRes.get(i);
			%>
			<tr class="<%=i%2==0?"even-row": "odd-row"%>">
				<td nowrap><a href=""
					class="cdNameButton"
					data-cd-id="<%=CanonE001CommonUtil.checkNull(resultObj.getCdId())%>"><%=CanonE001CommonUtil.checkNull(resultObj.getCdName())%></a>
					</td>
				<td><%=CanonE001CommonUtil.checkNull(resultObj.getCdDescription())%></td>
				<td><%=CanonE001CommonUtil.checkNull(resultObj.getCdViewName())%></td>
			</tr>
			<%
					}
				}else{
			%>
			<tr class='eventableDataCell'>
				<td colspan=10 align="center">No data found for this criteria.</td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</div>

<table>
	<tr>
		<td>&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;</td>
	</tr>
</table>

<script>
	$(document).ready(
			function() {

				function searchCodeTable(pageNum) {
					//clear_status();
					$("#action").val("search");
					var newval=$.trim($("#search_cd_name").val().toUpperCase());
					$("#search_cd_name").val(newval);
					if(pageNum){
						$("#pageNumber").val(pageNum);
					}
					var url = "canonE001CodeTableInclude.jsp";
					$.ajaxPost(url,"error occurred while searching for code table.");
					
				}
				
				function invokeChangeCodeTable(cdId){
					$("#cd_id").val(cdId);
					$("#action").val("change");
					$("#machSearchFrm").submit();
				}
				
				function invokeCreateNewCodeTable(){
					$("#action").val("create_new");
					$("#machSearchFrm").submit();
				}

				$("#searchCodeTableButton").click(function(event) {
					searchCodeTable();
					event.preventDefault();
				});

				$("#clearButton").click(function(event) {
					$("#action").val("clear");
					$("#machSearchFrm").submit();
				});

				$(".cdNameButton").click(function(event) {
					var cdId = $(this).data("cd-id");
					invokeChangeCodeTable(cdId);
					event.preventDefault();
				});

				$("#createCodeTableButton").click(function(event) {
					invokeCreateNewCodeTable();
					event.preventDefault();
				});
				
			    $('input.search_cd_name').placeholder();
			    
				$(".pageNum").click(function(event) {
					var pageNum= $(this).data("page");
					searchCodeTable(pageNum);
					event.preventDefault();
				});
			    
				$(".sorting").click(function(event) {
					var sort_by= $(this).data("sort_by");
					$("#sortBy").val(sort_by);
					$("#sortOrder").val($("#sortOrder").val()=="asc" ? "desc" : "asc");
					searchCodeTable("1");
					event.preventDefault();
				});
			    
				$("#page"+<%=pageNumber%>).css({"color":"white","background-color":"#A10304"});

			});
</script>

<%
	}
%>
