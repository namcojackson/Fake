<%@page import="canon.apps.pci.util.CanonPCISecurityUtil"%>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.canon.apps.e001.CanonE001CodeTableDAO"%>
<%@page import="com.canon.apps.e001.CanonE001CommonUtil"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%@page import="java.util.Date"%>
<%@page import="static com.canon.apps.e001.CanonE001CommonUtil.checkResult"%>
<%@page import="static com.canon.apps.e001.CanonE001CommonUtil.checkErrors"%>
<%@page import="static com.canon.apps.e001.CanonE001CommonUtil.E001Exception"%>
<%@page import="static canon.apps.common.CanonS21SessionValidate.commonRoot"%>
<%@page errorPage="canonE001ErrorPage.jsp"%>


<%!

static String LEVEL_NAMES[]={"APPLICATION","ROLE","USER"};
static String toLevelName(BigDecimal levelNum){
	return levelNum!=null && levelNum.intValue()<4? LEVEL_NAMES[levelNum.intValue()-1] : null;
}

private String safeHtmlAttr(String str){
	return CanonPCISecurityUtil.htmlAttrEncode(CanonE001CommonUtil.checkNull(str));
}

%>
<%
	String strAction = request.getParameter("action");
	String sortBy = "";
	String sortOrder = "asc";
	if (request.getParameter("sortBy") != null) {
		sortBy = request.getParameter("sortBy");
	}
	if (request.getParameter("sortOrder") != null) {
		sortOrder = request.getParameter("sortOrder");
	}
	System.out.println("strAction : " + strAction);
	String profileName=null;
	String profileDescription=null;
	String createProfileError=null;
	List<CanonE001CodeTableDAO.ProfileValueInfo> valueList=null; 
	BigDecimal profileID=CanonE001CommonUtil.toBigDecimal(request.getParameter("profile_id"), false);
	System.out.println("profile ID : " + profileID);
	boolean isCreateNew=false;
	boolean isChange=false;
	boolean isCreated=false;
	if ("create_new".equals(strAction)) {
		isCreateNew=true;
	}else if ("create".equals(strAction)) {
		profileName = request.getParameter("profileName");
		profileDescription = request.getParameter("profileDescription");
		Object[] result=CanonE001CodeTableDAO.createUpdateProfile(
				CanonS21SessionValidate.getUserName(), profileName,
				profileDescription, null);
		if(result==null){
			createProfileError="Unknown database error";
	    	isCreateNew=true;
		}else{
	        String status=(String)CanonE001CommonUtil.second(result);
	    	System.out.println("status : " + status);
	        if("E".equals(status)){
	        	createProfileError=(String)CanonE001CommonUtil.third(result);
		    	System.out.println("error message : " + createProfileError);
		    	isCreateNew=true;
	        }else{
	        	BigDecimal newProfileID=(BigDecimal)CanonE001CommonUtil.first(result);
		        System.out.println("newProfileID is " +newProfileID);
		        profileID=newProfileID;
		        isCreated=true;
				isChange=true;
	        }
		}
		
	}else if("change".equals(strAction)){
		isChange=true;
	}else if("save".equals(strAction)){
		isChange=true;
		
		profileName = request.getParameter("profileName");
		profileDescription = request.getParameter("profileDescription");
		Object[] result=CanonE001CodeTableDAO.createUpdateProfile(
				CanonS21SessionValidate.getUserName(), profileName,
				profileDescription, profileID);
        checkErrors(result, 1,2);
		
 		String[] profile_level_numbers=request.getParameterValues("profile_level_number");
 		String[] profile_level_ids=request.getParameterValues("profile_level_id");
 		String[] profile_level_values=request.getParameterValues("profile_level_value");
 		String[] start_dates=request.getParameterValues("start_date");
 		String[] end_dates=request.getParameterValues("end_date");
 		String[] profile_values=request.getParameterValues("profile_value");
 		List<CanonE001CodeTableDAO.CanonE001PrfValueRec> values=new ArrayList<CanonE001CodeTableDAO.CanonE001PrfValueRec>();
 		for(int i=0; profile_level_numbers!=null && i<profile_level_numbers.length;i++){
 			BigDecimal profileLevelNumber=CanonE001CommonUtil.toBigDecimal(profile_level_numbers[i],false);
 			if(profileLevelNumber!=null){
	 			String profileLevelName=toLevelName(profileLevelNumber);
	 			String profileLevelValue=profile_level_values[i];
	 			BigDecimal profileValueId=CanonE001CommonUtil.toBigDecimal(profile_level_ids[i],false);
	 			Timestamp startDate=CanonE001CommonUtil.toTimestamp5(start_dates[i]);
	 			Timestamp endDate=CanonE001CommonUtil.toTimestamp5(end_dates[i]);
	 			String profileValue=profile_values[i];
	 			CanonE001CodeTableDAO.CanonE001PrfValueRec r=new CanonE001CodeTableDAO.CanonE001PrfValueRec(
	 					profileID,
	 					profileValueId,
	 					profileLevelNumber,
	 					profileLevelName,
	 					profileLevelValue,
	 					startDate,
	 					endDate,
	 					"Y",
	 					profileValue);
	 			values.add(r);
 			}
 		}
 		System.out.println(values);
 		result=CanonE001CodeTableDAO.createUpdateProfileValues(CanonS21SessionValidate.getUserName(), profileID, values);
        checkErrors(result, 0,1);
	}else if("delete".equals(strAction)){
		BigDecimal profileValueId=CanonE001CommonUtil.toBigDecimal(request.getParameter("delete_profile_value_id"),false);
 		Object[] result=CanonE001CodeTableDAO.deleteProfileValues(CanonS21SessionValidate.getUserName(), profileID, profileValueId);
        checkErrors(result, 0,1);
		isChange=true;
	}
	
	if(isChange){
		Object[] result=CanonE001CodeTableDAO.getProfileDetails(CanonS21SessionValidate.getUserName(), profileID);
        checkErrors(result, 2,3);
        CanonE001CodeTableDAO.ProfileDetail pd=(CanonE001CodeTableDAO.ProfileDetail)CanonE001CommonUtil.first((List)CanonE001CommonUtil.first(result));
        if(pd!=null){
        	profileName=pd.getProfileName();
        	profileDescription=pd.getProfileDescription();
        }
        valueList=(List<CanonE001CodeTableDAO.ProfileValueInfo>)CanonE001CommonUtil.second(result);
	}
	
%>
<div id="page_top">
	<h1>Custom Profile</h1>
</div>
<div id="errorWidget"
	style="<%=CanonE001CommonUtil.isEmpty(createProfileError) ?"display: none":""%>; padding-bottom: 5px; padding-left: 20px; padding-top: 5px; color: red;">
	<p id="eMsg"><%=CanonE001CommonUtil.checkNull(createProfileError)%></p>
</div>
<div class="table-inner">
	<form id="machSearchFrm" name="machSearchFrm"
		action="canonE001ProfileDetail.jsp" method="post">
		<input type="hidden" name="profile_id" id="profile_id" value="<%=CanonE001CommonUtil.checkNull(profileID)%>">		
		<input type='hidden' name='sortOrder' id='sortOrder'
			value="<%=sortOrder%>" /> <input type='hidden' name='sortBy'
			id='sortBy' value="<%=sortBy%>" /> 
			<input type="hidden" name="action" id="action" value="">
			<input type="hidden" name="delete_profile_value_id" id="delete_profile_value_id" value="">
			
		<div class="service">
			<table id="createProfileTable" >
				<tr>
					<th colspan="2">Profile Name & Description</th>
				</tr>
				<tr>
					<td>Profile Name</td>
					<td><input style="width: 400px" type="text" id="profileName"
						name="profileName"
						value="<%=CanonE001CommonUtil.checkNull(profileName)%>"
						<% if(isChange) { %>
							readonly="readonly" class="profile_name rdl"
						<% }else { %>
							class="profile_name required"
						<% }  %>
						></td>
				</tr>
				<tr>
					<td>Profile Description</td>
					<td><input class="required" style="width: 400px" type="text"
						id="profileDescription" name="profileDescription"
						value="<%=safeHtmlAttr(CanonE001CommonUtil.checkNull(profileDescription))%>"></td>
				</tr>
				<tr>
					<td style="text-align: right;">
					<% if(isCreateNew)  { %>
					<a href="#" id="createButton"
						class="btn">Create</a>
					<% } %>	
						</td>
				</tr>
			</table>
		<% if(isChange){  %>			
		<table class="supplies-table" cellspacing="1" style="float:left; width:80%;background:none;">
			<tr>
				<th style="width: 15%;">Level</th>
				<th style="width: 15%;">Level Value</th>
				<th style="width: 15%;">Start Date</th>
				<th style="width: 15%;">End Date</th>
				<th style="width: 15%;" title="Date value format YYYYMMDD">Profile Value</th>
				<th style="width: 5%;">Remove</th>
			</tr>
			<%
				if( valueList!=null && valueList.size()>0){
					for(int i=0;i<valueList.size();i++){
						CanonE001CodeTableDAO.ProfileValueInfo value = valueList.get(i);
						boolean isApplicationLevel=value.getProfileLevelNumber()!=null && value.getProfileLevelNumber().intValue()==1;
						int multiLine=CanonE001CommonUtil.lineCount(value.getProfileValue());
						
			%>
			<tr class="odd-row" data-profile_level_id="<%=CanonE001CommonUtil.checkNull(value.getProfileValueId())%>">
				<input type="hidden" name="profile_level_id" value="<%=CanonE001CommonUtil.checkNull(value.getProfileValueId())%>">
				<input type="hidden" name="profile_level_number" class="profile_level_number" value="<%=CanonE001CommonUtil.checkNull(value.getProfileLevelNumber())%>">
				<td class="profile_level" nowrap><%=CanonE001CommonUtil.checkNull(value.getProfileLevelName()) %></td>
				<td> 
					<% if (isApplicationLevel) { %> 
						<input type="hidden" name="profile_level_value"  class="profile_level_value" value="<%=CanonE001CommonUtil.checkNull(value.getProfileLevelValue())%>">
					<% }else{ %>
						<input type="text" name="profile_level_value"  class="profile_level_value required" 
						value="<%=CanonE001CommonUtil.checkNull(value.getProfileLevelValue())%>">
					<% } %>
				</td>
				<td>
					<input type="text" class="datePicker start_date" name="start_date" value="<%=CanonE001CommonUtil.formatDateTime5(value.getStartDate())%>">
				</td>
				<td>
					<input type="text" class="datePicker end_date" name="end_date" value="<%=CanonE001CommonUtil.formatDateTime5(value.getEndDate())%>">
				</td>
				<td>
					<%if(multiLine>1){%>
					<textarea maxlength="2000" name="profile_value" class="profile_value required" rows="<%=multiLine%>" style="width:90%;border: 1px solid #aac;border-radius: 0px;padding: 3px; margin: 0px 5px 0px 5px;" ><%=safeHtmlAttr(value.getProfileValue())%></textarea>
					<%}else{%>
					<input type="text" maxlength="2000" name="profile_value" class="profile_value required" value="<%=safeHtmlAttr(value.getProfileValue())%>">
					<%}%>
				</td>
				<td>
					<a href="#" class="btn deleteValueButton"  title="Delete">X</a>
				</td>
			</tr>
			<%
					}
				}else{
			%>

				<tr class="odd-row" data-profile_level_id="">
					<input type="hidden" name="profile_level_id" value="">
					<input type="hidden" name="profile_level_name"
						class="profile_level_name" value="APPLICATION">
					<td class="profile_level" nowrap=""><select name="profile_level_number"
						class="profile_level_number"><option></option>
							<option value="1" selected>APPLICATION</option>
							<option value="2">ROLE</option>
							<option value="3">USER</option></select></td>
					<td><input type="text" name="profile_level_value"
						class="profile_level_value required" value="" style="display: none;">
						</td>
					<td><input type="text"
						class="datePicker start_date" name="start_date" value=""></td>
					<td><input type="text"
						class="datePicker end_date" name="end_date" value=""></td>
					<td><input type="text" maxlength="2000" name="profile_value" class="profile_value required" value=""></td>
					<td><a href="#" class="btn deleteValueButton"  title="Delete">x</a></td>
				</tr>
				<%		
				}
			%>
		</table>
		<table style="float:left; width:80%; border:none;">
			<tr>
				<td style="text-align: right;">
					<a href="#" id="addNewButton" class="btn">Add New</a>
					<a href="#" id="saveButton" class="btn">Save</a>
				</td>
			</tr>
		    <% if(CanonE001CommonUtil.getDevFlag()){ %>
			<tr>
				<td style="text-align: left;">
					<a href="#" id="test_profile" style="margin-right: 100px;font-size:small" title="Test Profile API">Test Profile API</a>
				</td>
			</tr>
		    <% } %>    
		</table>
		<% } %>			
			
		</div>
	</form>
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


				function checkProfileName(o, n, handler) {
					return checkRegexp(o, /^[a-zA-Z0-9_]+$/,
							"Only alphanumeric characters and underscores are allowed in "
									+ n, handler);
				}

				function validateInput(handler) {
					checkRequired($("#profileName"), "Profile Name", handler);
					handler.valid() && checkProfileName($("#profileName"),"Profile Name", handler);
					handler.valid() && checkRequired($("#profileDescription"),"Profile Description", handler);

					$("#profileName").val($("#profileName").val().toUpperCase());
					
					return handler;
				}
		        
				function createProfile() {
					clear_status();
					var handler = makeSingleErrorHandler();
					validateInput(handler);
					handler.done();
					if (!handler.valid()) {
						handler.showError();
					}else{
						$("#action").val("create");
						$("#profileName").val($("#profileName").val().toUpperCase());
						$("#machSearchFrm").submit();
					}
				}
				
				function dateHasConflict(s1,e1,s2,e2){
					var t_s1=s1==null? 0: s1.getTime();
					var t_e1=e1==null? 9007199254740991: e1.getTime();
					var t_s2=s2==null? 0: s2.getTime();
					var t_e2=e2==null? 9007199254740991: e2.getTime();
					
					return !(t_e2<t_s1 || t_s2>t_e1);
				}
				
				function checkConflictDate(tr1, handler){
					var conflict=false;
					$(tr1).prevAll(".supplies-table .odd-row").each(function(){
		            	var tr2=$(this);
						if(valueEquals(tr1.find(".profile_level_number"),tr2.find(".profile_level_number")) &&
							valueEquals(tr1.find(".profile_level_value"),tr2.find(".profile_level_value")) &&
							dateHasConflict(tr1.find(".start_date").datepicker("getDate"),tr1.find(".end_date").datepicker("getDate"),
									tr2.find(".start_date").datepicker("getDate"),tr2.find(".end_date").datepicker("getDate")))
						{
							conflict=true;
							handler.handle("Invalid date range", tr2);							
							return false;
						}
		            });
					return conflict;
				}
				
				
				function checkDuplicate(tr1, handler){
					var dup=false;
					$(tr1).prevAll(".supplies-table .odd-row").each(function(){
		            	var tr2=$(this);
						if(valueEquals(tr1.find(".profile_level_number"),tr2.find(".profile_level_number")) &&
							valueEquals(tr1.find(".profile_level_value"),tr2.find(".profile_level_value")) &&
							valueEquals(tr1.find(".start_date"),tr2.find(".start_date")) &&
							valueEquals(tr1.find(".end_date"),tr2.find(".end_date"))){
							dup=true;
							handler.handle("Duplicate profile value found", tr2);							
							return false;
						}
		            });
					return dup;
				}
				
				function validateSaveProfileInput(handler) {
					checkRequired($("#profileDescription"),"Profile Description", handler);
					if(handler.valid()){
			            $(".supplies-table .odd-row").each(function(){
			            	var tr=$(this);
							var profileValueID=tr.data("profile_level_id");
							var levelNumElm=tr.find(".profile_level_number");
							var levelNum=levelNumElm.val();
							if(levelNum=="2" || levelNum=="3"){
								var elm=tr.find(".profile_level_value");
								checkRequired(elm,"Level Value", handler);
							}else if(levelNum==""){
								if(!empty(tr.find(".profile_level_value").val())  
										|| !empty(tr.find(".start_date").val())   
										|| !empty(tr.find(".end_date").val()) 
										|| !empty(tr.find(".profile_value").val()) ){
									checkRequired(levelNumElm,"Level", handler);
								}else{
									return true;
								}
							}
							var startDateElm=tr.find(".start_date");
							var endDateElm=tr.find(".end_date");
							handler.valid() && ( (empty(startDateElm.val()) || empty(endDateElm.val())) 
				                    || checkDateTimeNoLater(startDateElm,"Start Date", endDateElm, "End Date", handler));
							
							handler.valid() && checkRequired(tr.find(".profile_value"),"Profile Value", handler);
							handler.valid() && checkDuplicate(tr, handler);
							handler.valid() && checkConflictDate(tr, handler);
							if(!handler.valid()) return false;
			            });
					}
					
					return handler;
				}
				
				function saveProfile() {
					clear_status();
					var handler = makeSingleErrorHandler();
					validateSaveProfileInput(handler);
					handler.done();
					if (!handler.valid()) {
						handler.showError();
					} else {
						var url = "canonE001ProfileDetailInclude.jsp";
						$("#action").val("save");
						$.ajaxPost(url,"Failed to save profile.","All changes have been saved!");
					}
				}

				function deleteProfileValue(profileValueID) {
			        askConfirmation("Delete Profile Value","Are you sure to delete the selected profile value?",function(dialog){
						clear_status();
			            dialog.dialog( "close" );
						var url = "canonE001ProfileDetailInclude.jsp";
						$("#action").val("delete");
						$("#delete_profile_value_id").val(profileValueID);
						$.ajaxPost(url,"Failed to delete the profile value.");
			        });
				}
				
				function addNewValue(){
					var newRow='<tr class="odd-row" data-profile_level_id="">'+
					'<input type="hidden" name="profile_level_id" value="">'+
					'<input type="hidden" name="profile_level_name" class="profile_level_name" value="">'+
					'<td class="profile_level" nowrap=""><select name="profile_level_number" class="profile_level_number"><option></option><option value="1">APPLICATION</option><option value="2">ROLE</option><option value="3">USER</option></select></td>'+
					'<td> '+
					'		<input type="text" name="profile_level_value" class="profile_level_value required" value="" style="display:none">'+
					'</td>'+
					'<td>'+
					'	<input type="text" class="datePicker start_date" name="start_date" value="">'+
					'</td>'+
					'<td>'+
					'	<input type="text" class="datePicker end_date" name="end_date" value="" >'+
					'</td>'+
					'<td>'+
					'	<input type="text" maxlength="2000" name="profile_value" class="profile_value required" value="">'+
					'</td>'+
					'<td>'+
					'	<a href="#" class="btn deleteValueButton" title="Delete">x</a>'+
					'</td>'+
					'</tr>';
					
					$(".supplies-table").append(newRow);
					
				}
				
		         function openTestDialog(profileName){
		             var modelName ="#dialog-form"; 
		             var url ="canonE001ProfileTest.jsp";
		             var qryString="profile_name="+profileName;
		             
		          	   $(modelName).dialog({
		          		   			width: 620,
		          					title: "Test Profile API",
		          					modal: true ,
		          	                resizable: false,      
		          					buttons: { "Ok": function() {   
		  	    	                                    $(modelName).html("");
		  	    	                                    $(this).dialog("close");
		  	    	                                    $(this).dialog("destroy");
		      	                                    }
		          	               }					
		          				});
		          	      
		          	      $.ajax({
		          					url: url,
		          	                cache: false,
		          	                data:qryString,
		          					success: function(data){     
		          			                $("#dialog-form").html("");					
		          	                        $("#dialog-form").html(data);
		           		          	        $("#dialog-form" ).dialog("open");
		          					}             
		          				});
		          	  
		           }      
				
		         function setRowStyles(tbl) {
		        		$(tbl + " tbody tr:odd").each(function() {
		        			$(this).addClass("oddRow");
		        		});
		        	}
		         
		         function openUserNameSearch(options){
		             var modelName ="#dialog-form";
		             $.lovModelName=modelName;
		             var url ="canonE001UserLov.jsp";
					 showPleaseWait();
		             
		          	   $(modelName).dialog({
		          		   			width: 700,
		          					title: "User Name Search",
		          					modal: true ,
		          	                resizable: false,
		          	                close: function( event, ui ) {
	        				            $(modelName).off("selected");
		          	                    $(modelName).html("");
	          	                    	$(modelName).dialog("close");
	          	                    	$(modelName).dialog("destroy");     
		          	                }		          	                
		          				});
		          	      
		          	      $.ajax({
		          					url: url,
		          	                cache: false,
		          	                data:options,
		          					success: function(data){     
											hidePleaseWait();
		          			                $(modelName).html("");					
		          	                        $(modelName).html(data);
		           		          	        $(modelName).dialog("open");
		          					}
		          				});
		          	  
	            }
				
		         
		         function openRoleNameSearch(options){
		             var modelName ="#dialog-form";
		             $.lovModelName=modelName;
		             var url ="canonE001RoleLov.jsp";
					 showPleaseWait();
		          	   $(modelName).dialog({
		          		   			width: 700,
		          					title: "Role Search",
		          					modal: true ,
		          	                resizable: false,
		          	                close: function( event, ui ) {
	        				            $(modelName).off("selected");
		          	                    $(modelName).html("");
	          	                    	$(modelName).dialog("close");
	          	                    	$(modelName).dialog("destroy");     
		          	                }		          	                
		          				});
		          	      
		          	      $.ajax({
		          					url: url,
		          	                cache: false,
		          	                data:options,
		          					success: function(data){     
											hidePleaseWait();
		          			                $(modelName).html("");					
		          	                        $(modelName).html(data);
		           		          	        $(modelName).dialog("open");
		          					}
		          				});
		          	  
	            }
		         
				$("#createButton").click(function(event) {
					createProfile();
					event.preventDefault();
				});
				
				$("#saveButton").click(function(event) {
					saveProfile();
					event.preventDefault();
				});
				
				$("#addNewButton").click(function(event) {
					addNewValue();
					setupDatePicker();
					event.preventDefault();
				});

				$(".supplies-table").on("click",".deleteValueButton",function(event) {
			        var tr = $(this).closest("tr");
					var profileValueID=tr.data("profile_level_id");
					if(empty(profileValueID)){
						tr.remove();
						event.preventDefault();
					}else{
						deleteProfileValue(profileValueID);
						event.preventDefault();
					}
				});
				
				$(".supplies-table").on("change",".profile_level_number",function(event){
					event.preventDefault();
					var level=$(this).val();
					if(level=="1" || level=="") {
						$(this).closest("tr").find(".profile_level_value").hide().val("").lov("hide");
					}else{
						$(this).closest("tr").find(".profile_level_value").show().val("").lov("show");
					}
					
					if(level=="2"|| level=="3") {
						$(this).closest("tr").find(".profile_level_value").prop('readonly', true).lov("show");
					}else{
						$(this).closest("tr").find(".profile_level_value").prop('readonly', false).lov("hide");
					}
				});
				
 				$('#test_profile').click(function(event) {
					event.preventDefault();
					var profileName="<%=CanonE001CommonUtil.checkNull(profileName)%>";
					openTestDialog(profileName);
				});
 				
				$(".supplies-table").on("click",".lov",function(event) {
					event.preventDefault();
			        var tr = $(this).closest("tr");
					var profileLevelNumber=tr.find(".profile_level_number").val();
		        	var elm=tr.find(".profile_level_value");
					if(profileLevelNumber==="3"){
						openUserNameSearch({sort_by:"LAST_NM",sort_order:"asc"});
				        $("#dialog-form").on("selected", function(event,data){
				            elm.val(data.user_nm);
				            $("#dialog-form").off("selected");
      	                    $("#dialog-form").html("");
  	                    	$("#dialog-form").dialog("close");
				            
				        });
					}else if(profileLevelNumber==="2"){
						openRoleNameSearch({sort_by:"ROLE_NM",sort_order:"asc"});
				        $("#dialog-form").on("selected", function(event,data){
				            elm.val(data.user_nm);
				            $("#dialog-form").off("selected");
      	                    $("#dialog-form").html("");
  	                    	$("#dialog-form").dialog("close");
				        });
					}
					
				});
 				
				
				$('#main_content').on("keypress","input.profile_value", function (e) {            
				    if (e.keyCode == 13) {
				        e.preventDefault(); 
					    var newChild=$('<textarea maxlength="2000" rows="2" style="width:90%;border: 1px solid #aac;border-radius: 0px;padding: 3px; margin: 0px 5px 0px 5px; resize:both;">')[0];
						var attrs = {};     
					    $.each(this.attributes, function() {
					       $(newChild).attr(this.name,this.value);
					    });
					    var parent=this.parentNode;
					    parent.replaceChild(newChild, this);
				    	$(newChild).val($(this).val()).focus();
						var elemLen = newChild.value.length;
					    newChild.selectionStart = elemLen;
					    newChild.selectionEnd = elemLen;
				    	newChild.focus();
				    }
				});
				

				$("input[type='text'].profile_level_value:visible").prop('readonly', true).lov();
				
				setupDatePicker();
				
				$(".rdl").each(function (){
					  var ele=$(this);
					  var tp = $(ele).attr("type");
					  if(tp=="text")
						  $(ele).addClass("rdl").attr("readonly","readonly"); 
					  else   
						  $(ele).addClass("rdl").attr("disabled","true");	  
				 });
				
				 <% if(isCreated) {%>
				 	update_status("New profile has been created.");				 	
				 <% } %>
				 
			});
</script>