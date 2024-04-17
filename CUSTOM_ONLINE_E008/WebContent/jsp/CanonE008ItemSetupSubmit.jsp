<%--
 /*===========================================================================+
 |      Copyright (c) 1999 Oracle Corporation, Redwood Shores, CA, USA       |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  FILENAME                                                                 |
 |            CanonE008ItemSetupSearch.jsp                                     |
 |                                                                           |
 |  DESCRIPTION                                                              |
 |   													  					 |
 |    																		 |
 |                                                                           |
 |  NOTES                                                                    |
 |         Using template                                                    |
 |  DEPENDENCIES                                                             |
 |                           	    											 |
 |                                                                           |
 |  HISTORY                                                                  |
 |                                                                           |
 |   12-22-2015      Madhusudan Duna --  Ver:1                              |
 +===========================================================================*/
--%>
<%@ page language="java" import="oracle.apps.jtf.calendar.util.CalendarUtil" %>
<%@ page import="oracle.apps.jtf.base.Logger" %>
<%@ page import="oracle.apps.jtf.util.GeneralPreference" %>
<%@ page import="oracle.apps.jtf.base.resources.AOLMessageManager" %>
<%@ page import="java.text.*" %>
<!--Canon Custom Java Class Imports-->
<!-- %@ page language="java" import="oracle.apps.ibe.customer.bean.*" %-->
<%@ page language="java" import="java.math.*" %>
<%@ page import="java.util.*" %>   
<%@ page import="java.sql.*" %>
<%@ page import="oracle.apps.jtf.base.resources.*" %>
<%@ page import="oracle.apps.fnd.common.*" %> 
<%@ page import="java.net.URLEncoder" %>
<%@ page import="oracle.apps.e008.item.process.CanonE008ItemProcessDAO" %>
<%@ page import="oracle.apps.e008.item.process.Canon008ItemCreateApi" %>
  


<%@include file="CanonE008ItemProcessCommon.jsp" %>	
<%!
Map temaplteValues=new HashMap();  

public  Object first(Object obj) {
        if (obj instanceof Object[]) {
            Object[] objs = (Object[]) obj;
            return objs.length < 1 ? null : objs[0];
        } else if (obj instanceof List) {
            List l = (List) obj;
            return l.size() < 1 ? null : l.get(0);
        }
        return null;
    }
 void addLOV(String name,Object result){
        temaplteValues.put(name,first(result)!=null ? (List) first(result) : Collections.EMPTY_LIST);
    }  
%>

<%
//System.out.println("Prior to call Canon008ItemCreateApi API");

   Canon008ItemCreateApi canon008ItemCreateApi=new Canon008ItemCreateApi();
canon008ItemCreateApi.createServicerequest(request); 
CanonE008ItemProcessDAO obj0 = new CanonE008ItemProcessDAO();

Map<String,List<String>> temaplateHeaderMap=new HashMap<String,List<String>>();

 Object[] templateList = obj0.templateList();
 ArrayList templateNameList = (ArrayList)templateList[0];
 Object[] attributeHeaders = obj0.getItemMainAttributeHedaers();
	ArrayList templateHeaderList = (ArrayList)attributeHeaders[0];
 int cnttemplate=templateNameList.size();
 int templateHeaderListCount=templateHeaderList.size();
 
/*  USA_ACCY-NONSER # LOT
USA_ACCY-NONSER # NO LOT
USA_ACCY-SERIAL # LOT CONTROL
USA_EQUIPMENT LOT CONTROL
USA_EQUIPMENT NO LOT
USA_PARTS NO LOT
USA_SUPPLY LOT CONTROL
USA_SUPPLY NO LOT */
String strProjectNo="";
String strProjectName="";
String strProjectType="";
String strProjectDesc="";
String strMasterProject="";
String strSubmitter="";
String strProjectRequester="";
String strReqDate="";
String strShowData="";
String strRowsPerPage="";
String strStatus="";
String strRoleId="";
 
	

	if (request.getParameter("hProjectNo")!=null)
	{
	strProjectNo = oracodec.encode(null,request.getParameter("hProjectNo"));
	} 

	if (request.getParameter("hProjectName")!=null)
	{
	strProjectName = oracodec.encode(null,request.getParameter("hProjectName"));
	} 

	if (request.getParameter("hProjectType")!=null)
	{
	// strPBName = oracodec.encode(null,request.getParameter("hModelNo"));
	strProjectType = request.getParameter("hProjectType");
	} 

	if (request.getParameter("hProjectDesc")!=null)
	{
	strProjectDesc = oracodec.encode(null,request.getParameter("hProjectDesc"));
	}  
	
	if (request.getParameter("hProjectDesc")!=null)
	{
	strMasterProject = oracodec.encode(null,request.getParameter("hProjectDesc"));
	} 
	
	
	if (request.getParameter("hSubmitter")!=null)
	{
	strSubmitter = oracodec.encode(null,request.getParameter("hSubmitter"));
	} 

	if (request.getParameter("hShowData")!=null)
	{
	strShowData = oracodec.encode(null,request.getParameter("hShowData"));
	}
	
	if (request.getParameter("hRowsPerPage")!=null)
	{
	strRowsPerPage = oracodec.encode(null,request.getParameter("hRowsPerPage"));
	}
	
	if (request.getParameter("hStatus")!=null)
	{
	strStatus = oracodec.encode(null,request.getParameter("hStatus"));
	}
	
	if (request.getParameter("roleId")!=null)
	{
		strRoleId = oracodec.encode(null,request.getParameter("roleId"));
	}
	
%>

<style>
    #report_tbl_first a {
        color: white;
    }
    .select-container {
        overflow: hidden
    }
    .select-container select {
        border-left-width: 0px;
        border-right-width: 0px;
    }

    #project_status
    { 
        border: 1px solid transparent; 
        padding: 0.3em;
        float:left;
        color:blue;
    }
    
    #project_button_div
    {
    }
    
    .required{
        background-color:yellow;
    }
    
    .validated{
        background-color:yellow;
    }
    
    .validated_not_required{
    }

    .not_required{
    }
    
    .owner{
        background-color:cyan;
    }
    
    .select_all a {
        color: blue;
    }
    .select_all a:hover {
        color: red;
    }
    .select_all_div {
        float:left;
        padding-top:10px;
    }    
    
    .project_validate_errors{
        color: blue;
    }
    
    .message {
        font-weight: bold; 
        color: blue;
    }
    
    #divCanonE590Main{
        display:none;
    }
    
    .custom-combobox {
      position: relative;
      display: inline-block;
    }
    .custom-combobox-toggle {
      position: absolute;
      top: 0;
      bottom: 0;
      margin-left: -19px;
      padding: 0;
      /* support: IE7 */
      *height: 1.7em;
      *top: 0.1em;
    }
    .custom-combobox-input {
      margin: 0;
/*      margin-right:20px;*/
      padding: 0.3em;
    }
    
    .ui-validation-error {
        border: 2px solid #cd0a0a; 
    }
    
    #header_table input 
    {
        width:100%;
    }

    #header_table textarea
    {
        width:100%;
    }

    /* begin override canonE480PriceBook.css */
    .search_text {
        font-size: 12px;
    }
        
    
    #report_tbl_first .hd {
    background-color: #2b547e;
    color: #ffffff;
    font-size: 10px;
    font-weight: bold;
    text-align: center;
    text-decoration: none;
}
    
    input{
        font-size: 12px;
    }
    
    td {
        font-size: 12px;
    }
    /* end override canonE480PriceBook.css */
    
    th, td { white-space: nowrap; }
    div.dataTables_wrapper {
        width: 100%;
        margin: 0 auto;
    }
    
</style>


<HTML>

<HEAD>

	<TITLE>Item Project Workbench</TITLE>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!--<link rel="stylesheet" href="canonE480PriceBook.css" type="text/css">-->
	<!--<link rel="stylesheet" type="text/css" href="/OA_HTML/canonGlobal.css">
	<LINK href="canonE480PriceBook.css" rel=stylesheet>-->
	<script type="text/javascript" src="<%=CanonS21SessionValidate.commonRoot(request)%>/canonDefault.js"></script>
	<script type="text/javascript" src="<%=CanonS21SessionValidate.commonRoot(request)%>/canonDropdown.js"></script>

</HEAD>

<script language="javascript">

// var NLSformat = "MM/DD/RRRR";
var NLSformat = 'DD-Mon-RRRR';

	var nooftemplate=0;
	
	var noofAttributes=<%=templateHeaderListCount%>;
$(document).ready(function() {


    var table = $('#templateTable').DataTable( {
        scrollY:        "100%",
        scrollX:        true,
        scrollCollapse: true,
        paging:         false,
        fixedColumns:   {
            leftColumns: 2,
            rightColumns: 1
        },
        "columnDefs": [                         
                          {
                              // The `data` parameter refers to the data for the cell (defined by the
                              // `data` option, which defaults to the column being worked with, in
                              // this case `data: 0`.
                              "render": function ( data, type, row ) {
                                  return '<a id="'+(nooftemplate++)+'" data-toggle="modal" data-target="#myModal" class="dt-button"><span>Additional</span></a>';
                              },
                              "targets": (noofAttributes+1)
                          }
                      ]
    } );
   
   
   $('select.canonTemplateClass').on('change', function () {                
             var mainval=$(this).val(); 
             var dropDownId=$(this).attr("id");              
    		 var dropDownNumber = dropDownId.substring(dropDownId.indexOf("_")+1, dropDownId.length);             
             var url= encodeURI('<%=ctxPath%>/e008/jsp/CanonE008GetSelectedTemplateAttributes.jsp?templateName='+escape(mainval));   
            // alert(mainval);         
            	 $.ajax({  
						type: "POST",
						async:false,						
						url: url, 						
						complete: function(data)
							{  
								x =((data.responseText).replace(/^\s+|\s+$/g,''));
								res=x.split("@@");
								 templateAttrVal=res[0].split(",");								
								 for(var i=0;i<templateAttrVal.length;i++)
								 {								 
								 var templateAttr=templateAttrVal[i];								 
								 if(templateAttr.trim()=="null"||templateAttr.trim()=="[null"||templateAttr.trim()=="null]")
								 	templateAttr="";									 								 
								 $("#templateRow"+dropDownNumber+i).val(templateAttr);
								 }															
							}  
					});  //ajax end 
    });
    
    $(document).on("click", ".dt-button", function () {
     
             var additionalButtonId=$(this).attr("id");  
   			 $(".modal-footer #addButtonPopupId").val(additionalButtonId );
    
   
     
});
    
    /* $('input.additionalButton').on('click', function () { 
    var htmlForAdditionalAttributes='<table>'
    									+'<tr><td>Item Type</td><td><input type="text" id="ItemType"></td></tr>'
    									+'<tr><td>Item Number</td><td><input type="text" id="ItemNumber"></td></tr>'
    									+'<tr><td>Item Description</td><td><input type="text" id="ItemDescription"></td></tr>'    									
    									+'</table>';    
    	htmlForAdditionalAttributes=htmlForAdditionalAttributes+'<fieldset><table>'
    									+'<tr><th>Category</th><th>Attribute</th><th>Value</th></tr>'
    									+'<tr><td>Extra Attributes</td><td>Main Engine</td><td><input type="text" id="extAttrMainEngn"></td></tr>'
    									+'<tr><td>Extra Attributes</td><td>Criticality</td><td><input type="text" id="extAttrCritical"></td></tr>'
    									+'<tr><td>Inventory</td><td>Serialized</td><td><input type="text" id="extAttrSeriliazed"></td></tr>'
    									+'<tr><td>Inventory</td><td>UOM</td><td><input type="text" id="extAttrUOM"></td></tr>'
    									+'<tr><td>Accounting</td><td>COGS ACCT</td><td><input type="text" id="extAttrCogsAcct"></td></tr>'    									
    									+'</table></fieldset>'								
    $("#DataDiv").html(htmlForAdditionalAttributes);	 
    $("#DataDiv").dialog("open");
    });  */
       
   
      
    $("#DataDiv").dialog({
				autoOpen: false,
				title: "Additional Item Information",
				modal: true ,
				height: 380,													
				width: 450,					
				resizable: false, 
				closeOnEscape :true,
				open: function(event, ui) {$(".ui-dialog-titlebar-close").hide();},
				buttons:{'Cancel':function(){
					if($("#DataDiv").dialog( "isOpen" )){
						$("#DataDiv").html('');																						
						$("#DataDiv").dialog("close");																																
						$("#DataDiv").dialog("destroy");
					  }			       
					}
				}
			});	
    
   
} );

 function saveProject(save)
	{
		//alert("counttemplate "+getIdVal("countTemplate"));
		//alert(getFormData());
	 var x ;
	//alert("action="+"SAVE" +"&cntTemplate="+getIdVal("countTemplate")+"&attributeCount="+getIdVal("headerListCount")+"&userId=999&"+getFormData());
	$.ajax({  
						type: "POST",
						async:false,						
						url: 'CanonE008ItemWorkbenchProcess.jsp', 
						data: "action="+"SAVE" +"&cntTemplate="+getIdVal("countTemplate")+"&attributeCount="+getIdVal("headerListCount")+"&userId=999"+getFormData() ,
						
						complete: function(data)
							{  
								x =((data.responseText).replace(/^\s+|\s+$/g,''));
								//alert(x);								
							}  
					});  //ajax end
		
	
	}	
	
	function getFormData() {
		var myform = $('#searchForm');
		//var disabled = myform.find(':input:disabled').removeAttr('disabled');
		var ser = $('#searchForm').serialize();
		//disabled.attr('disabled','disabled');
		//alert("serialized " + ser);
		return ser;
	}

 function cancelTermsAndConditions(){
			if($("#DataDiv").dialog( "isOpen" )){
				$("#DataDiv").html('');																						
				$("#DataDiv").dialog("close");																																
				$("#DataDiv").dialog("destroy");
			}
		}
		
		function assignAdditinalAttr()
		{
		var addIndex=$("#addButtonPopupId").val()
		$("#ItemNumber"+addIndex).val($("#ItemNumber").val());
		$("#criticaLityAttr"+addIndex).val($("#extAttrCritical").val());
		
		//alert($("#ItemNumber"+addIndex).val());				
		}



</script>
<body>

<form name="searchForm" id="searchForm" method="post">
<!-- Search -->
			<input type="hidden" id="hProjectNo" value="<%=strProjectNo%>"> 
			<input type="hidden" id="hProjectName" value="<%=strProjectName%>"> 
			<input type="hidden" id="hProjectType" value="<%=strProjectType%>"> 
			<input type="hidden" id="hProjectDesc" value="<%=strProjectDesc%>">
			<input type="hidden" id="hMasterProject" value="<%=strProjectDesc%>">
			<input type="hidden" id="hProjectRequester" value="<%=strProjectRequester%>"> 			 
			<input type="hidden" id="hSubmitter" value="<%=strSubmitter%>"> 
			<input type="hidden" id="hRowsPerPage" value="<%=strRowsPerPage%>">
			<input type="hidden" id="hStatus" value="<%=strStatus%>">
			<input type="hidden" id="hShowData" value="<%=strShowData%>"> 
			<input type="hidden" id="hLoginUserName" value="<%=userName%>">
			<input type="hidden" id="hLoginUserId" value="<%=userId%>">
			<input type="hidden" id="hLoginUserFullName" value="<%=userFullName%>">
			<input type="hidden" id="roleId" name="roleId" value="<%=strRoleId%>"/>
			<input type="hidden" id="resp_appl_id" name="resp_appl_id" value="<%=strApplId%>"/>
	<span style="background-color: #FFFFFF;color: #336699; font-size: 17; font-weight: bolder; height: 26px;">Item Project Workbench Search</span>		
	
	<fieldset><b> <legend></legend></b>
	<br>
	
		<table >
		    <tr>
		    <td>
			<table style="align:left;margin-bottom:30%">
				<tr>
					<td NOWRAP class ="search_text"  width="100px" align = "left"><b>Project Number </b></td><td> <input type="text" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g,'');" id="projectNo" name ="projectNo" style="width:150px;" value = "<%=strProjectNo%>"  onkeypress=""  onKeyUp =""></td>
					<td><b></td>
				</tr>
				<tr>
					<td NOWRAP class ="search_text"  width="100px" align = "left"><b>Project Name  </b></td><td> <input type="text" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g,'');" id="projectName" name ="projectName" style="width:150px;" value = "<%=strProjectName%>"  onkeypress=""  onKeyUp =""></td>
					<td><b></td>
				</tr>
				<tr>
					<td NOWRAP class ="search_text"  width="100px" align = "left"><b>Project Type  </b></td><td> <input type="text" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g,'');" id="projectType" name ="projectType" style="width:150px;" value = "<%=strProjectType%>"  onkeypress=""  onKeyUp =""></td>
					<td><b></td>
				</tr>
				<tr>
					<td NOWRAP class ="search_text"  width="150px" align = "left"><b>Project Decription  </b></td><td> <input type="text" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g,'');" id="projectDesc" name ="projectDesc" style="width:150px;" value = "<%=strProjectDesc%>"  onkeypress=""  onKeyUp =""></td>
					<td><b></td>
				</tr>
				<tr>
					<td NOWRAP class ="search_text"  width="100px" align = "left"><b>Master Project  </b></td><td> <input type="text" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g,'');" id="masterProj" name ="masterProj" style="width:150px;" value = "<%=strMasterProject%>"  onkeypress=""  onKeyUp =""></td>
					<td><b></td>
				</tr>
			</table>
			</td>			

			<td>
			</td>
						
			<td>
			<table align= "right" >
			    <tr>
					<td NOWRAP class ="search_text"  width="150px" align = "left"><b>Project Requester   </b></td><td> <input type="text" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g,'');" id="projectRequester" name ="projectRequester" style="width:150px;" value = "<%=strProjectRequester%>"  onkeypress=""  onKeyUp =""></td>
					<td width="30px" class="search_text" align="left">Status  </td>
					<td colspan="" class="non_required_field">
						<select name="status" id="status">
							<option value="OPEN" <%= "OPEN".equals(strStatus)? "SELECTED" : ""%> title="All Exclude Closed">Open</option>
							<option value="ALL" <%= "ALL".equals(strStatus)? "SELECTED" : ""%> title="All Include Closed">All</option>
							<option value="ENTERED" <%= "ENTERED".equals(strStatus)? "SELECTED" : ""%> >Entered</option>
							<option value="ITEM MASTER REVIEW" <%= "ITEM MASTER REVIEW".equals(strStatus)? "SELECTED" : ""%> >Item Master Review</option>
							<option value="PENDING APPROVAL" <%= "PENDING APPROVAL".equals(strStatus)? "SELECTED" : ""%> >Pending Approval</option>
							<option value="PENDING ITEM CREATION" <%= "PENDING ITEM CREATION".equals(strStatus)? "SELECTED" : ""%> >Pending Item Creation</option>
							<option value="AWAITING PRICING" <%= "AWAITING PRICING".equals(strStatus)? "SELECTED" : ""%> >Awaiting Pricing</option>
							<option value="CLOSED" <%= "CLOSED".equals(strStatus)? "SELECTED" : ""%> >Closed</option>
							<option value="PENDING APPROVAL ACCOUNTING" <%= "PENDING APPROVAL ACCOUNTING".equals(strStatus)? "SELECTED" : ""%> >Pending Approval Accounting</option>
							<option value="PENDING APPROVAL INCENTIVE COMP" <%= "PENDING APPROVAL INCENTIVE COMP".equals(strStatus)? "SELECTED" : ""%> >Pending Approval Incentive Comp</option>
							<option value="PENDING APPROVAL SERVICE" <%= "PENDING APPROVAL SERVICE".equals(strStatus)? "SELECTED" : ""%> >Pending Approval Service</option>
						</select>									
					</td> 	
					<td>
					 <button class="ui-state-default ui-corner-all" type="button" style="">
							<span class="ui-button-text">Approval History</span>
							</button>
					</td>		
				</tr>
				<tr>
					<td NOWRAP class ="search_text"  width="150px" align = "left"><b>Requested Date </b></td>
					<td> <input type="text" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g,'');" id="reqDate" name ="reqDate" style="width:150px;" value = "<%=strReqDate%>"  onkeypress=""  onKeyUp =""></td>
				</tr>
				<tr>
					<td NOWRAP class ="search_text"  width="150px" align = "left"><b>Notes  </b> <br>
					<button style="margin-left:50px;" class="ui-state-default ui-corner-all" type="button">
							<span class="ui-button-text">Add Note</span>
							</button></td>
					<td colspan="4">
					<textarea rows="10" cols="50" id="projectNote" name="projectNote" ></textarea>
					</td>
 				</tr>				
		   		
     		</table>
			</td>
			</tr>
			<tr>
				<td class="search_text" align="left">&nbsp; </td>	
				<td align="center"> 
							

						</td>  																	    
					<td align="right">						
							<button class="ui-state-default ui-corner-all" type="button" onclick="clearSearch(this.form);">
							<span class="ui-button-text">Validate</span>
							</button>
							<button class="ui-state-default ui-corner-all" type="button" onclick="validate();">
							<span class="ui-button-text">Upload Item</span>
							</button>
							<button class="ui-state-default ui-corner-all" type="button" onclick="saveProject(this.form);">
							<span class="ui-button-text">Delete Project</span>
							</button>
							<button class="ui-state-default ui-corner-all" type="button" onclick="saveProject(this.form);">
							<span class="ui-button-text">Submit Project</span>
							</button>
						</td>
																						   	
				</tr>
		</table>
		</br>
	</fieldset>
<input style="display:none;" name="action" id="action" value="" />
		<input style="display:none;" name="countTemplate" id="countTemplate" value="<%=cnttemplate %>" />
		<input style="display:none;" name="headerListCount" id="headerListCount" value="<%=templateHeaderListCount %>" />
		
<table id="templateTable" class="stripe row-border order-column" cellspacing="0" width="100%">
        <thead>
            <tr id="report_tbl_first" style="background-color: #2b547e;color:#ffffff;font-size:12px;">
                <th>Template</th>
                <%
	
	for(int i=0;i<templateHeaderList.size();i++){	
	String templateHeader=(String)templateHeaderList.get(i);	
	 %>
	 
	 <th><%=templateHeader%></th>
	 <%} %>
	 <th>Additional Attributes</th>                
            </tr>
        </thead>
        <tbody>
         <% for(int j=0;j<cnttemplate;j++){ %>
         	
            <tr>
            <td id="template_0">
            <select name="canonTemplateNames_<%=j%>" id="canonTemplate_<%=j%>" class="canonTemplateClass" >
            <option value="-1"></option>
            <%
           
             templateNameList = (ArrayList)templateList[0];
            for(int i=0;i<templateNameList.size();i++){	
				String templateName=(String)templateNameList.get(i);	
             %>                        
							<option value="<%=templateName%>" ><%=templateName%></option>
							<% } %>
							</select>
							</td>
							<%
							for(int i=0;i<templateHeaderList.size();i++){	
									
							 %>
                <td><input type="text" id="templateRow<%=j%><%=i%>" name="templateRowName<%=j%><%=i%>"></td>
                <%} %>
                <td style="text-align:right;"> </td>                
            </tr> 
            <% }%>                 
                                  
            </tbody>
            </table>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">ADDITIONAL ITEM INFORMATION</h4>
      </div>
      <div class="modal-body">

		<div class="col-sm-12 col-md-12 col-lg-12 dactive" id="canonAddtInfoPrintServices">
			<div class="panel panel-primary">
			  	<div class="panel-body">
								  	
			<table  style="width: 90%; margin: 0px 20px 20px 20px;"> 
				<tbody>
				<tr><td>Item Type</td><td><input type="text" id="ItemType"></td></tr>
    									<tr><td>Item Number</td><td><input type="text" id="ItemNumber" name="ItemNumber"></td></tr>
    									<tr><td>Item Description</td><td><input type="text" id="ItemDescription"></td></tr>    									
    									</table>    
    							<fieldset><table>
    									<tr><th>Category</th><th>Attribute</th><th>Value</th></tr>
    									<tr><td>Extra Attributes</td><td>Main Engine</td><td><input type="text" id="extAttrMainEngn"></td></tr>
    									<tr><td>Extra Attributes</td><td>Criticality</td><td><input type="text" id="extAttrCritical"></td></tr>
    									<tr><td>Inventory</td><td>Serialized</td><td><input type="text" id="extAttrSeriliazed"></td></tr>
    									<tr><td>Inventory</td><td>UOM</td><td><input type="text" id="extAttrUOM"></td></tr>
    									<tr><td>Accounting</td><td>COGS ACCT</td><td><input type="text" id="extAttrCogsAcct"></td></tr>    									
    									</table></fieldset>	
<!-- <tr class="KJEOddRow"><td class="KJELabel KJECellBorder KJECell60">Level within Org</td><td class="KJECell KJECell40">Yes</td></tr>
<tr class="KJEEvenRow"><td class="KJELabel KJECellBorder">Area of Responsibility</td><td class="KJECell">Yes</td></tr>
<tr class="KJEOddRow"><td class="KJELabel KJECellBorder">Can Survey</td><td class="KJECell">Yes</td></tr>
<tr class="KJEEvenRow"><td class="KJELabel KJECellBorder">Referenceable per Sales</td><td class="KJECell">Yes</td></tr>
<tr class="KJEOddRow"><td class="KJELabel KJECellBorder">Sales reference</td><td class="KJECell"></td></tr>
<tr class="KJEEvenRow"><td class="KJELabel KJECellBorder">Analyst reference</td><td class="KJECell"></td></tr>
<tr class="KJEOddRow"><td class="KJELabel KJECellBorder">Case Study</td><td class="KJECell">Yes</td></tr>
<tr class="KJEEvenRow"><td class="KJELabel KJECellBorder">Marketing Event</td><td class="KJECell">Yes</td></tr>
<tr class="KJEOddRow"><td class="KJELabel KJECellBorder">Meeting Frequency</td><td class="KJECell">Yes</td></tr>
<tr class="KJEEvenRow"><td class="KJELabel KJECellBorder">Sales reference phone</td><td class="KJECell">Yes</td></tr>
<tr class="KJEOddRow"><td class="KJELabel KJECellBorder">Site Tour</td><td class="KJECell">No</td></tr>
<tr class="KJEEvenRow"><td class="KJELabel KJECellBorder">Video Audio</td><td class="KJECell">No</td></tr>
<tr class="KJEOddRow"><td class="KJELabel KJECellBorder">Testimonial</td><td class="KJECell">Yes</td></tr>
<tr class="KJEEvenRow"><td class="KJELabel KJECellBorder">Press Release</td><td class="KJECell">Yes</td></tr>
<tr class="KJEOddRow"><td class="KJELabel KJECellBorder">Conference Speaking</td><td class="KJECell">No</td></tr>
<tr class="KJEEvenRow"><td class="KJELabel KJECellBorder">Study Participant</td><td class="KJECell">Yes</td></tr>
<tr class="KJEOddRow"><td class="KJELabel KJECellBorder">Customer Advisory Brd</td><td class="KJECell">Yes</td></tr>
<tr class="KJEEvenRow"><td class="KJELabel KJECellBorder">Media Interview</td><td class="KJECell">Yes</td></tr>
<tr class="KJEOddRow"><td class="KJELabel KJECellBorder">Can be named reference</td><td class="KJECell">No</td></tr>
			</tbody></table> -->
					<div class="row EditForm" style="display: none;">				
						<div class="col-sm-12 col-md-12 col-lg-12">
							
						</div>							
					</div>
				</div>
			</div>
		</div>      
      
      </div>
      <div class="modal-footer">
      		<input type="hidden" name="addButtonPopupId" id="addButtonPopupId" value=""/>
			<a class="dt-button buttons-create pull-right " id="addId" tabindex="0" aria-controls="" onclick="assignAdditinalAttr();"><span>Add</span></a>
			<a style="display: none;" class="dt-button buttons-create pull-right " tabindex="0" aria-controls=""><span>ADD</span></a>
      </div>
    </div>
  </div>
</div>
<%for(int i=0;i<cnttemplate;i++){ %>
<input type="hidden" id="ItemType<%=i%>">
<input type="hidden" id="ItemNumber<%=i%>" name="ItemNumber<%=i%>">
<input type="hidden" id="ItemDesc<%=i%>" name="ItemNumber<%=i%>">
<input type="hidden" id="mainEngineAttr<%=i%>" name="mainEngineAttr<%=i%>">
<input type="hidden" id="criticaLityAttr<%=i%>" name="criticaLityAttr<%=i%>">
<input type="hidden" id="seriliazedAttr<%=i%>">
<input type="hidden" id="uomAttr<%=i%>">
<input type="hidden" id="cocsAcctAttr<%=i%>">
<% }%>
    
</form>

<!--<div id="CreationDiv">   </div>-->
</body>
<script type="text/javascript">

$(document).ready(function() {		
	 $(".datePicker").datepicker({
		 dateFormat: 'dd-M-yy',
		 changeMonth: true,
		 changeYear: true
	 });	 
	 
	 $(".dt-button").css("background","#dfeffc repeat-x scroll 50% 50%");
	 $(".dt-button").css("border","1px solid #c5dbec");
	 $(".dt-button").css("color","#2e6e9e");
	 $(".dt-button").css("font-weight","bold");
    	 
});
</script>
</html>






