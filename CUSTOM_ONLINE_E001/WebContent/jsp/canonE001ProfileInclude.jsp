<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@page import="canon.apps.pci.util.CanonPCISecurityUtil"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.canon.apps.e001.CanonE001CodeTableDAO"%>
<%@page import="com.canon.apps.e001.CanonE001CommonUtil"%>
<%@page import="java.text.*"%>
<%@page import="java.util.*"%>
<%@page import="java.util.Date"%>
<%@page errorPage="canonE001ErrorPage.jsp"%>

<%!
private String safeHtmlAttr(String str){
	return CanonPCISecurityUtil.htmlAttrEncode(CanonE001CommonUtil.checkNull(str));
}

%>

<%	
	String strAction = request.getParameter("action");
  if("change".equals(strAction)){
	  pageContext.forward("canonE001ProfileDetail.jsp");
  }else if("create_new".equals(strAction)){
	  pageContext.forward("canonE001ProfileDetail.jsp");
  }else{
	  String sortBy = null;
	  String sortOrder = "asc";  
	  if(request.getParameter("sortBy")!=null && !request.getParameter("sortBy").isEmpty()){
		sortBy = request.getParameter("sortBy");
	  }else{
		  sortBy ="PROFILE_NAME";
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
		String fnGetRes="fnGetMachSrchRes";
		
	    Object[] obj = CanonE001CodeTableDAO.profileSearch(CanonS21SessionValidate.getUserName(), request.getParameter("search_profile_name"), sortBy, sortOrder, new BigDecimal(pageNumber), new BigDecimal(10));
		int totalRecords = 0;
		if(CanonE001CommonUtil.second(obj)!=null){
			totalRecords = ((BigDecimal)CanonE001CommonUtil.second(obj)).intValue();
		}
		List<CanonE001CodeTableDAO.ProfileInfo> lstSearchRes = new ArrayList<CanonE001CodeTableDAO.ProfileInfo>();
		if(CanonE001CommonUtil.first(obj)!=null){
	lstSearchRes = (List<CanonE001CodeTableDAO.ProfileInfo>) CanonE001CommonUtil.first(obj);
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
	<h1>Custom Profile</h1>
</div>

<div><h1>&nbsp;&nbsp;Home</h1></div>

<div id="errorWidget"
	style="display: none; padding-bottom: 5px; padding-left: 20px; padding-top: 5px; color: red;">
	<p id="eMsg"></p>
</div>
<div class="table-inner">
	<form id="machSearchFrm" name="machSearchFrm"
		action="canonE001Profile.jsp" method="post">
		<input type='hidden' name='sortOrder' id='sortOrder'
			value="<%=sortOrder%>" /> <input type='hidden' name='sortBy'
			id='sortBy' value="<%=sortBy%>" /> <input type="hidden"
			name="profile_id" id="profile_id" value=""> <input
			type="hidden" name="action" id="action" value="">
 			<input type='hidden' name='pageNumber' id='pageNumber' value="<%=pageNumber%>" />			
		<div class="service">
			<table id="profileSearchTable">
				<tr>
					<th>Profile Search</th>
				</tr>
				<tr>
					<td><input style="width: 400px" type="text"
						id="search_profile_name" name="search_profile_name" class="search_profile_name"
						placeholder="Profile Name"
						value="<%=CanonE001CommonUtil.checkNull(request.getParameter("search_profile_name"))%>"></td>
				</tr>
				<tr>
					<td style="text-align: right;"><a href="#"
						id="searchProfileButton" class="btn">Search</a> <a href="#"
						id="clearButton" class="btn">Clear</a> <a href="#"
						id="createProfileButton" class="btn">Create Profile</a></td>
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
				<th style="width: 40%;"><a
					style="color: #FFFFFF; text-decoration: underline; text-align: left;"
					href="#" data-sort_by="PROFILE_NAME" class="sorting">Profile
						Name</a></th>
				<th style="width: 60%;"><a
					style="color: #FFFFFF; text-decoration: underline; text-align: left;"
					href="#" data-sort_by="PROFILE_DESCRIPTION" class="sorting">Profile
						Description</a></th>
			</tr>
			<%
				if(lstSearchRes!=null && lstSearchRes.size()>0){
					for(int i=0;i<lstSearchRes.size();i++){
						CanonE001CodeTableDAO.ProfileInfo resultObj = lstSearchRes.get(i);
			%>
			<tr class="<%=i%2==0?"even-row": "odd-row"%>">
				<td nowrap><a href=""
					class="profileNameButton"
					data-profile-id="<%=CanonE001CommonUtil.checkNull(resultObj.getProfileId())%>"><%=CanonE001CommonUtil.checkNull(resultObj.getProfileName())%></a>
					<input type="hidden" name="selSerialNumber<%=i%>"
					id="selSerialNumber<%=i%>"
					value="<%=CanonE001CommonUtil.checkNull(resultObj.getProfileName())%>"></td>
				<td><%=safeHtmlAttr(CanonE001CommonUtil.checkNull(resultObj.getProfileDescription()))%>
					<input type="hidden" name="selTagNumber<%=i%>"
					id="selTagNumber<%=i%>"
					value="<%=safeHtmlAttr(CanonE001CommonUtil.checkNull(resultObj.getProfileDescription()))%>"></td>
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

				function searchProfile(pageNum) {
					//clear_status();
					$("#action").val("search");
					var newval=$.trim($("#search_profile_name").val().toUpperCase());
					$("#search_profile_name").val(newval);
					if(pageNum){
						$("#pageNumber").val(pageNum);
					}
					var url = "canonE001ProfileInclude.jsp";
					//blockUsrInterface();
					$.ajaxPost(url,"Error occurred while searching the profile.");
				}
				
				function invokeChangeProfile(profileId){
					$("#profile_id").val(profileId);
					$("#action").val("change");
					$("#machSearchFrm").submit();
				}
				
				function invokeCreateNewProfile(){
					$("#action").val("create_new");
					$("#machSearchFrm").submit();
				}

				$("#searchProfileButton").click(function(event) {
					searchProfile();
					event.preventDefault();
				});

				$("#clearButton").click(function(event) {
					window.location="canonE001Profile.jsp";
				});

				$(".profileNameButton").click(function(event) {
					var profileId = $(this).data("profile-id");
					invokeChangeProfile(profileId);
					event.preventDefault();
				});

				$("#createProfileButton").click(function(event) {
					invokeCreateNewProfile();
					event.preventDefault();
				});
				
			    $('input.search_profile_name').placeholder();
			    
				$(".pageNum").click(function(event) {
					var pageNum= $(this).data("page");
					searchProfile(pageNum);
					event.preventDefault();
				});
			    
				$(".sorting").click(function(event) {
					var sort_by= $(this).data("sort_by");
					$("#sortBy").val(sort_by);
					$("#sortOrder").val($("#sortOrder").val()=="asc" ? "desc" : "asc");
					searchProfile("1");
					event.preventDefault();
				});
			    
				$("#page"+<%=pageNumber%>).css({"color":"white","background-color":"#A10304"});

			});
</script>

<%
	}
%>
