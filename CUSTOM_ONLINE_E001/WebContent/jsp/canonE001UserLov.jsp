
<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.canon.apps.e001.CanonE001CommonUtil"%>
<%@page import="com.canon.apps.e001.CanonE001CodeTableDAO"%>
<%@page import="java.util.ArrayList"%>

<div id="userlov_div">
<form id="lov_form" name="lov_form">

<table style="width: 100%;">
<tr>
<td>
<%
        String userName=CanonE001CommonUtil.trimToNull(request.getParameter("user_nm"));
		String lastName=CanonE001CommonUtil.trimToNull(request.getParameter("last_nm"));
		String firstName=CanonE001CommonUtil.trimToNull(request.getParameter("first_nm"));
		int pageNum =CanonE001CommonUtil.toInt(CanonE001CommonUtil.trimToNull(request.getParameter("page_num")),1); 		
		int ct=10; // number of Records per page 
		int start = ((pageNum-1)*ct) + 1 ;
		int end= pageNum*ct;
		int totalLinks =0;
		
		String sortBy =CanonE001CommonUtil.trimToNull(request.getParameter("sort_by"));
		String sortOrder=CanonE001CommonUtil.trimToNull(request.getParameter("sort_order"));
		
		Object[] obj = CanonE001CodeTableDAO.getUserList(userName, firstName, lastName, sortBy, sortOrder, new BigDecimal(pageNum), new BigDecimal(10));
		
		int totalRecords = ((BigDecimal)CanonE001CommonUtil.second(obj)).intValue();
		List<CanonE001CodeTableDAO.UserInfo> arList =null;
		
 %>

<input class="lovsearch_hidden" type="hidden" name="sort_by" id="lovSortBy" value="<%=CanonE001CommonUtil.checkNull(sortBy)%>"/>
<input class="lovsearch_hidden" type="hidden" name="sort_order" id="lovSortOrder" value="<%=CanonE001CommonUtil.checkNull(sortOrder)%>"/>  
<input class="lovsearch_hidden" type="hidden" name="page_num" id="lovPageNum" value="<%=pageNum%>"/>  

<table style="font-size: 12px;width: 100%;">
              <tr>
	                <td>
		                <input type="text" class="lovsearch inTxt" placeholder="User Name" name="user_nm" size="20" style="height: 19px;" value="<%=CanonE001CommonUtil.checkNull(userName)%>" tabindex="1"  />
	                </td>
	                <td>
		                <input type="text" placeholder="Last Name" class="lovsearch inTxt"  name="last_nm" size="10" style="height: 19px;" id="sAccNum" value="<%=CanonE001CommonUtil.checkNull(lastName)%>"  />
	                </td>
	                <td>
		                <input type="text" placeholder="First Name" class="lovsearch inTxt"  name="first_nm" size="10" style="height: 19px;" id="firstName" value="<%=CanonE001CommonUtil.checkNull(firstName)%>"  />
	                </td>
	                <td>
	                 <a id="lov_search" class="btn" href="#" style="float: right;">Search</a>
	                </td>
	                <td>
	                   <a id="lov_clear" style="margin-left:5px;" class="btn" href="#" style="float: right;">Clear</a>
	                </td>
	                
	           </tr>     
	           
	         
 </table>
   <br/>
 <%           		
		
		if(totalRecords>0){ 
		
			arList = (List<CanonE001CodeTableDAO.UserInfo>) CanonE001CommonUtil.first(obj);
			
			totalLinks =(totalRecords%ct>0)?((totalRecords/ct)+1):totalRecords/ct;
			
			String pageLinkMsg="";
    	   
    	    if(totalLinks>1){
    	    	  pageLinkMsg= start+" to "+(end-(ct-arList.size())) +" of "+ totalRecords +" records found.";
    	    }
    	   
 %>
          <table   style="width: 100%; text-align: center;" >
           <tr>
           	<td style="text-align: left;color:blue;font-weight: bold" id="lov_error_message">&nbsp;</td>
           	<td align="right" style="text-align: right;font-size: 11px;font-weight: bold;"><%=pageLinkMsg %>
           </td>
           </tr>
          </table>
          <table class="lovTbl" align="center" style="text-align: center;width: 100%;">
           <thead>
             <tr class='canontableCell' >
              <th></th>              
              <th class="hd"><a
					style="color: #FFFFFF; text-decoration: underline; text-align: left;"
					href="#" data-sort_by="USR_NM" class="lovsorting">User Name</a></th>
              <th class="hd" ><a
					style="color: #FFFFFF; text-decoration: underline; text-align: left;"
					href="#" data-sort_by="LAST_NM" class="lovsorting">Last Name</a></th>
              <th class="hd" ><a
					style="color: #FFFFFF; text-decoration: underline; text-align: left;"
					href="#" data-sort_by="FIRST_NM" class="lovsorting">First Name</a></th>
           </thead>
           <tbody> 
            <%
             int i=0;
	         for(CanonE001CodeTableDAO.UserInfo custBean : arList){
	 		%>
	   	      <tr class="<%= (++i%2)==0? "oddRow":""%>">
	   	          <td width="5%">
	   	            <input type="radio" class="lov_check" name="lov_check"/>
	   	          </td>
	   	      
	   	          <td width="40%">
            			<a class="lov_clickable" href="#" >
				   	    	<%=CanonE001CommonUtil.checkNull( custBean.getUserNm()) %>
						</a>	   	          
	   	          </td>
	   	           <td width="30%"><%=CanonE001CommonUtil.checkNull(custBean.getLastNm())%></td>
	   	           <td width="30%"><%=CanonE001CommonUtil.checkNull(custBean.getFirstNm())%></td>
	   	      </tr>
	   		<%
	         }
            %>
            </tbody>
          </table>	   
        </td>
        </tr>
		   <tr>
		     <td>
				 <table  style="font-size: 11px;width:100%;" id="pgLinks">
			      <tr>
					  <td align="left">
					         <jsp:include page="canonE001Pagination.jsp">
							   <jsp:param value="<%=totalLinks %>" name="totalLinks"/>
							   <jsp:param value="<%=pageNum %>" name="pageNum"/>
							   </jsp:include>
					  </td>
				   </tr>
			 	  </table>
		     </td>
		   </tr>
		</table>		   
		  <% 
		}else{
	     %>
	       <p style="font-size: 11px;font-weight: bold;">No records found for this search criteria.</p>
	     <%		
		  }
          %>

</form>

</div>
 <table   style="width: 100%;" >
    <tr><td style="float: right;margin-right: 10px;text-align:right;"> <a href="#" class="btn lov_select">Select</a></td></tr>
 </table>

<script type="text/javascript">

$(function (){

	var users=[<%
		for(int i=0;arList!=null && i<arList.size();i++){
			CanonE001CodeTableDAO.UserInfo u=(CanonE001CodeTableDAO.UserInfo)arList.get(i);%>
			<%= i==0? "" : ","%> {index:<%=i%>,user_nm:'<%=CanonE001CommonUtil.checkNull(u.getUserNm())%>'}
	<%  }
	%>];

	function setLovValue(data){
	    var modelName=$.lovModelName;
	    $(modelName).trigger("selected",data );
	}  
	
	function searchUser(pageNum) {
		$("#lovPageNum").val(pageNum);
        var url ="canonE001UserLov.jsp";
        var modelName=$.lovModelName;
	    showPleaseWait();
		var qryStr=$("#lov_form").serialize();
		 $.ajax({
				url:url,
				data:qryStr,
		        cache: false,
				success: function(data){     
						  hidePleaseWait();     
						  $(modelName).html("");					
					      $(modelName).html(data);
					       hidePleaseWait();
			 	        } 
		});
	}
	
	$("#userlov_div").on("click",".lov_clickable", function(event){
		event.preventDefault();
		var ridx=$(this).closest("tr")[0].rowIndex;
		setLovValue(users[ridx-1]);
	});
	
	$("#userlov_div").on("click","#lov_search", function(event){
		event.preventDefault();
		searchUser(1);
	});
	
	$("#userlov_div").on("click","#lov_clear", function(event){
		event.preventDefault();
		$("input.lovsearch,input.lovsearch_hidden").val("");
		searchUser(1);
	});

    $("#userlov_div").find(".lovsearch").placeholder();
    
    $("#userlov_div").on("click", ".pageNum", function(event){
		event.preventDefault();
		var page=$(this).data("page");
		searchUser(page);
    });

    $("#userlov_div").on("click", ".lovsorting", function(event){
		event.preventDefault();
		var sort_by= $(this).data("sort_by");
		$("#lovSortBy").val(sort_by);
		$("#lovSortOrder").val($("#lovSortOrder").val()=="asc" ? "desc" : "asc");
		searchUser("1");
    });
    
    $(".lov_select").on("click", function(event){
		event.preventDefault();
		var checked=$("#lov_form").find(".lov_check:checked");
		if(checked.length==0){
			$("#lov_error_message").text("Please select a item.");
		}else{
			var ridx=checked.closest("tr")[0].rowIndex;
        	setLovValue(users[ridx-1]);
		}
		
	});    

});
</script>
