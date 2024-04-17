<%--
 /*===========================================================================+
 |      Copyright (c) 1999 Oracle Corporation, Redwood Shores, CA, USA       |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  FILENAME                                                                 |
 |            CanonE008ItemSetupSearch.jsp                                   |
 |                                                                           |
 |  DESCRIPTION                                                              |
 |   													  					 |
 |    																		 |
 |                                                                           |
 |  NOTES                                                                    |
 |         Using template                                                    |
 |  DEPENDENCIES                                                             |
 |                           	    										 |
 |                                                                           |
 |  HISTORY                                                                  |
 |                                                                           |
 |   12-22-2015      Madhusudan Duna --  Ver:1                               |
 +===========================================================================*/
--%>
<%@ page language="java" import="oracle.apps.jtf.calendar.util.CalendarUtil" %>
<%@ page import="oracle.apps.jtf.base.Logger" %>
<%@ page import="oracle.apps.jtf.util.GeneralPreference" %>
<%@ page import="oracle.apps.jtf.base.resources.AOLMessageManager" %>
<%@ page import="java.text.*" %>
<%@ page language="java" import="java.math.*" %>
<%@ page import="java.util.*" %>   
<%@ page import="java.sql.*" %>
<%@ page import="oracle.apps.jtf.base.resources.*" %>
<%@ page import="oracle.apps.fnd.common.*" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="oracle.apps.e008.item.process.CanonE008ItemProcessUtil"%>
<%@ page	import="oracle.apps.e008.item.process.CanonE008ItemProcessHelper"%>
<%@ page import="oracle.apps.e008.item.process.CanonE008ItemProcessDAO"%>
<%@ include file="CanonE008ItemProcessCommon.jsp" %> 	
 
<%!    
Hashtable lovs=new Hashtable();
void addLOV(String name,Object result){
    lovs.put(name,CanonE008ItemProcessUtil.first(result)!=null ? (List) CanonE008ItemProcessUtil.first(result) : Collections.EMPTY_LIST);
}    

%>

<%
String strProjectNo="";
String strProjectName="";
String strProjCat="";
String strProjDesc="";
String strItem="";
String strMasterProj="";
String strRequester="";
String strShowData="";
//String strRowsPerPage="15";
String strStatus="OPEN";
String strRoleId="";
String strreqDateFrm="";
String strreqDateTo="";

	String loginUserID = s21Authentication.getIdentityDetails().getUID();
	System.out.println("loginUserID is " + loginUserID);
	String loginUserFullName = CanonS21SessionValidate.getFullName();

	CanonE008ItemProcess obj0 = new CanonE008ItemProcess();

	if (request.getParameter("hProjectNo")!=null)
	{
		//strProjectNo = oracodec.encode(null,request.getParameter("hProjectNo"));
		strProjectNo = request.getParameter("hProjectNo");
	} 

	if (request.getParameter("hProjectName")!=null)
	{
		strProjectName = request.getParameter("hProjectName");
	} 

	if (request.getParameter("hProjCat")!=null)
	{
		strProjCat = request.getParameter("hProjCat");
	} 

	if (request.getParameter("hProjDesc")!=null)
	{
		strProjDesc = request.getParameter("hProjDesc");
	}  
	
	if (request.getParameter("hProjItem")!=null)
	{
		strItem = request.getParameter("hProjItem");
	}  

	if (request.getParameter("hMasterProj")!=null)
	{
		strMasterProj = request.getParameter("hMasterProj");
	}  

	
	if (request.getParameter("hSubmitter")!=null)
	{
		strRequester = request.getParameter("hSubmitter");
	} 

	if (request.getParameter("hShowData")!=null)
	{
		strShowData = request.getParameter("hShowData");
	}
	
/* 	if (request.getParameter("hRowsPerPage")!=null)
	{
		strRowsPerPage = oracodec.encode(null,request.getParameter("hRowsPerPage"));
	}
 */	
	if (request.getParameter("hStatus")!=null)
	{
		strStatus = request.getParameter("hStatus");
	}
	
	if (request.getParameter("roleId")!=null)
	{
		strRoleId = request.getParameter("roleId");
	}

	if (request.getParameter("hreqDateFrom")!=null)
	{
		strreqDateFrm = request.getParameter("hreqDateFrom");
	}
	if (request.getParameter("hreqDateTo")!=null)
	{
		strreqDateTo = request.getParameter("hreqDateTo");
	}
	
    addLOV("masterProj", CanonE008ItemProcessDAO.masterProjectList());
    addLOV("projCat", CanonE008ItemProcessDAO.projectTypeList());
    
    addLOV("saveSearch", CanonE008ItemProcessDAO.getsavedSearchList(loginUserID));
    
%>


<script language="javascript">

// var NLSformat = "MM/DD/RRRR";
var NLSformat = 'DD-Mon-RRRR';

/*  var table = $('#projectsearchtbl').DataTable( {
    scrollY:        "100%",
    scrollX:        true,
    scrollCollapse: true,
    paging:         false,
    fixedColumns:   {
        leftColumns: 2,
        rightColumns: 1
    },
} ); */


 
 
/* function setRowsPerPage()    
	{
	var thisForm = document.forms['searchForm'];
	var rowsPerPg = thisForm.hRowsPerPage.value;
	if (thisForm.hRowsPerPage.value == "1000000") 
	{rowsPerPg = "ALL";}
	for (var i=0; i < thisForm.rowsPerPage.length; i++) 
		{
			if (thisForm.rowsPerPage[i].value == rowsPerPg) 
				{thisForm.rowsPerPage[i].selected = true;}
		}

	} */

function setFocus(x)	
   {
   document.getElementById(x).focus();
   }
 
function clearSearch(ele)   
	{		
	$(ele).find(':input').each(function() 
		{
			switch(this.type) 
				{
					case 'password':
					case 'select-multiple':
					case 'select-one':
					case 'text':
					case 'textarea':
					$(this).val('');

				break;
					case 'checkbox':
					case 'radio':
					this.checked = false;
				}
		})
		$("table#advSearch").hide();
		$("#status").val("OPEN");
                $("#rowsPerPage").val("15");
                setFocus('projectNo');
	}
	
function searchProject()
	{ 
			 //alert("Submitting");
			 document.getElementById("hProjectNo").value =getIdVal("projectNo");
			 document.getElementById("hProjectName").value = getIdVal("projectName");
			 document.getElementById("hProjCat").value = getIdVal("projCat");
			 document.getElementById("hProjDesc").value = getIdVal("projectDesc");
			 document.getElementById("hProjItem").value = getIdVal("SearchItem");
			 document.getElementById("hMasterProj").value = getIdVal("masterProj");
			 document.getElementById("hSubmitter").value = getIdVal("requester");
			 document.getElementById("hStatus").value = getIdVal("status");
			 document.getElementById("roleId").value = getIdVal("roleId");
			 document.getElementById("hreqDateFrom").value = getIdVal("reqDateFrom");
			 document.getElementById("hreqDateTo").value = getIdVal("reqDateTo");
			 
			 //alert("here1");
/* 			 var rowsPerPg =  getIdVal("rowsPerPage"); 
			 if (rowsPerPg =="ALL") {
					 document.getElementById("hRowsPerPage").value = "1000000";	
					} else
						{document.getElementById("hRowsPerPage").value = rowsPerPg;	}  */

				 var url =	"CanonE008ItemSetupSearch.jsp?page=0&hProjectNo="+encodeURIComponent(getIdVal("projectNo")) ;
			     url=url+"&hProjectName="+encodeURIComponent(getIdVal("projectName"));
			     url=url+"&hProjCat="+encodeURIComponent(getIdVal("projCat"));
			     url=url+"&hProjDesc="+encodeURIComponent(getIdVal("projectDesc"));
			     url=url+"&hProjItem="+encodeURIComponent(getIdVal("SearchItem"));
			     url=url+"&hMasterProj="+encodeURIComponent(getIdVal("masterProj"));
                 url=url+"&hSubmitter="+encodeURIComponent(getIdVal("requester"));
                 url=url+"&hreqDateFrom="+encodeURIComponent(getIdVal("reqDateFrom"));
                 url=url+"&hreqDateTo="+encodeURIComponent(getIdVal("reqDateTo"));
				 url=url+"&hStatus="+getIdVal("status");
				 url=url+"&hShowData=Y";
				 url=url+'&roleId='+getIdVal('roleId');
			   //url= encodeURI(url);
			   // alert(url) ;
			
			 document.forms['searchForm'].action = url;			
			 document.forms['searchForm'].submit();
//		   	}
	
	}
	
	
	function saveSearch()
	{
	//alert("Saved Search");
	var name="";
	var divID ="";
	divID="savesearch-dialog";
	name = "Save Search";
			//var myPos = [ $(window).width() / 2, 50 ];
			var myPos = { my: "center top+100", at: "center top+50", of: window };
			//alert("Should Open New create Template");
			$('#'+divID).css('display','block');
			$('#'+divID).dialog(
				{
					title: "Save Search",
					modal:true,
					//height: 100,													
					width: 250,	
					closeOnEscape :false,
					position: myPos,
					resizable: false, 
					open: function(event, ui) {$(".ui-dialog-titlebar-close").hide();},
					buttons: { "Close" : function(){
												$(".error").removeClass('error');
												$(this).dialog("close");
												$(this).dialog("destroy");
												$("#"+divID+" input[type='text']").each(function (){$(this).val(""); });	
												$("#"+divID+" select").each(function (){$(this).val(""); });	
												$('#'+divID).css('display','none');												 
												   },	
							"Save": function() { 
													//alert("no errors test");
													var s="";
													//alert("TEST "+ getIdVal("projectNo")+getIdVal("projectName")+
													//		 + getIdVal("projectDesc")+getIdVal("masterProj")+getIdVal("requester")+
													//		 getIdVal("status")+getIdVal("hLoginUserId")+getIdVal("save_search"));
													var xm = savesearchCriteria (getIdVal("projectNo"),getIdVal("projectName"),
																getIdVal("projCat"),	getIdVal("projectDesc"),getIdVal("masterProj"),getIdVal("requester"),
																 getIdVal("status"),getIdVal("reqDateFrom"),getIdVal("reqDateTo"),getIdVal("hLoginUserId"),getIdVal("save_search"));
													
													var res =xm.split("@@@");
												    //BigDecimal newtemplateId = res[2]; 
													//alert("Template Id" + res[2]);
													 if (res[0]=='S')
														{
														    //$("#htemplateId").val(res[2]);
															show_error("Saved Successfully.");
															$(this).dialog("close");
															$(this).dialog("destroy");
															$('#'+divID).css('display','none');
	
														     var url = "CanonE008ItemSetupSearch.jsp?";
													        //alert("url " + url);
															 document.forms['searchForm'].action = url;			
															 document.forms['searchForm'].submit();  
	
														} else {
															show_error('Saved Search Failed - '+res[1]);
															   }
	
										   }
							
							}	
				})
		}


	function savesearchCriteria(strprojectNo,strprojectName,strprojCat,strprojDesc,strmasterProj,strrequester,
			strstatus, strreqDateFrom,
			strreqDateTo,struser,strsevedname)
		{
		var x ;
		$.ajax({  
		type: "POST",
		async:false,						
		url: 'CanonE008ItemWorkbenchProcess.jsp', 
		data: "action="+"SAVESEARCH"+"&projectNumber="+strprojectNo+"&projectName="+strprojectName+"&projectType="+strprojCat+"&projectDesc="+strprojDesc+"&masterProject="+strmasterProj+"&projectRequester="+strrequester
				+"&approval_Satus="+strstatus+"&reqFromDate="+strreqDateFrom
				+"&reqToDate="+strreqDateTo+"&userId="+struser+"&savedName="+strsevedname,
		complete: function(data)
		{  
			x =((data.responseText).replace(/^\s+|\s+$/g,''));
			//alert(x);								
		}  
		});  //ajax end
		
		return x;
		
		}	

	
    function callSavedSearchChange(Id) {                
 		var selectBox = document.getElementById(Id);
        var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	    //alert(selectedValue);
        //var url= encodeURI('<%=ctxPath%>/e008/jsp/CanonE008TemplateProcess.jsp?"action="+"GETSAVESEARCH"+"&savedName='+escape(selectedValue));
        //alert(url);         
       	 $.ajax({  
					type: "POST",
					async:false,						
					url: 'CanonE008ItemWorkbenchProcess.jsp', 
					data: "action="+"GETSAVESEARCH"+"&savedName="+selectedValue+"&userId="+getIdVal("hLoginUserId"),
					complete: function(data)
						{  
							 x =((data.responseText).replace(/^\s+|\s+$/g,''));
							 res=x.split("@@");
							 templateAttrVal=res[0].split(",");
							 //alert("templateAttrVal"+templateAttrVal )
//							 alert("templateAttrVal.length " + templateAttrVal.length);
							 var defvalue = "";
							 for(var i=0;i<templateAttrVal.length;i++)
							 {
								 var templateAttr=templateAttrVal[i];
								 
								 if ((templateAttr.trim() != null) && (templateAttr.trim() != "null") && (templateAttr.trim() != ""))
								     {
									 defvalue = templateAttr.trim();
								     //alert("111")
								     }
								 else
									 {
									 defvalue = "";
								     //alert("2222");
									 }

								 if (i=="1")
								 	$("#projectNo").val(defvalue);

								 if (i=="2")
								 	$("#projectName").val(defvalue);

								 if (i=="3")
									 	$("#projCat").val(defvalue);

								 if (i=="4")
									 	$("#projectDesc").val(defvalue);

								 if (i=="5")
								 		$("#masterProj").val(defvalue);

								 if (i=="6")
									 	$("#requester").val(defvalue);
								 
								 if (i=="7")
									 	$("#status").val(defvalue);

								 if (i=="8")
									 	$("#reqDateFrom").val(defvalue);

								 if (i=="9")
									 	$("#reqDateTo").val(defvalue);

 							 }															
						}  
				});  //ajax end 
	}; 

	
    function show_error(errmsg,response){
        defaultSingleErrorHandler.handle(errmsg);
        defaultSingleErrorHandler.showError();
        <%-- <% if( IS_DEV ){ %>
            if(typeof response != "undefined"){
              $("#project_status" ).append('&nbsp;<a class="jsp_error" href="#">View error detail (visible only in dev environment)</a>');
              $("#project_status .jsp_error").click(function (){
                 var newWindow = window.open("","Error","width=400,height=500,scrollbars=1,resizable=1")
                 newWindow .document.open()
                 newWindow .document.write(response)
                 newWindow .document.close()
              });
            }
        <%} %>     --%>
    }
	
    function clear_status() {
        var project_status = $("#project_status" );
        project_status
          .text("")
          .removeClass( "ui-state-highlight" );
        $(".ui-validation-error").removeClass("ui-validation-error");
      }    

	function clearSearch() {
		//alert("tttt");
		$('#mainSearch').find(':input').each(function() {
			switch (this.type) {
			case 'password':
			case 'text':
			case 'textarea':
				$(this).val('');

				break;
			}
		})
 		$('#projCat').prop('selectedIndex',0);
		$('#masterProj').prop('selectedIndex',0);
		$('#status').prop('selectedIndex',0);
	}    
    
	var defaultSingleErrorHandler=makeSingleErrorHandler();

   
    function makeSingleErrorHandler(){
        var error;
        var bValid=true;
        return {
            handle:function(t,o){
                error={"component":o,"message":t};
                bValid=false;
            },
            showError:function(){
                default_alert_handler(error.message,error.component);
            },
            done:function(){
            },
            valid:function(){
                return bValid;
            }
        }
    }

    function default_alert_handler(t,o) {
        clear_status();
        if(o){
            o.addClass( "ui-validation-error" );
        }
        var html=
            '<div title="Alert">'
                +'<p>'
                +    '<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 50px 0;"></span>'
                + t 
                + '</p>'+
            '</div>';
        $( html ).dialog({
          modal: true,
          buttons: {
            Ok: function() {
              $( this ).dialog( "close" );
            }
          }
        });
    }
    
	function setPBTypeInfo(x)
	{
	// alert(x);
		setIdVal("pbType",x);
		
	}
	
	function setItemInfo(id,itm,desc)
	{
		setIdVal("Item",itm);
	}
	

	function setModelInfo(mod)
	{
		setIdVal("srvModel",mod);
	}
	
        var newWindow = null;
        function closeWin(){
            if (newWindow != null){
                if(!newWindow.closed)
                    newWindow.close();
            }
        }
        
        function popUpWin(title, url, type, strWidth, strHeight){
            closeWin(); 
            //calls function to close pop-up if already open, 
            //to ensure it's re-opened every time, retainining focus
            type = type.toLowerCase();
            if (type == "fullscreen"){
                strWidth = screen.availWidth-15; // adjust vertical scroll bar width
                strHeight = screen.availHeight-36; // adjust horizon scroll bar height
            }
            var tools="";
            if (type == "standard") tools = "resizable,toolbar=yes,location=yes,scrollbars=yes,menubar=yes,width="+strWidth+",height="+strHeight+",top=0,left=0";
            if (type == "console" || type == "fullscreen") tools = "resizable,toolbar=no, location=no,scrollbars=yes,width="+strWidth+",height="+strHeight+",left=0,top=0";
            newWindow = window.open(url, title, tools);
            newWindow.focus();
        }
        
	function createProject(hdrId)
	{
		var url = encodeURI('<%=ctxPath%>/e008/jsp/canonE008Project.jsp?roleId='+getIdVal('roleId'));
                //popUpWin('Project',url,"fullscreen");
		document.forms['searchForm'].action = url;	
		document.forms['searchForm'].submit();        
	}

/* 	function openProject(hdrId)
	{
		var url = encodeURI('<%=ctxPath%>/e008/jsp/canonE008Project.jsp?project_number='+hdrId+'&roleId='+getIdVal('roleId'));
                popUpWin('Project',url,"fullscreen");
	}	
 */	
	function openProject(hdrId)
	{
	 	blockUsrInterface();
	 	var urlDetail = encodeURI('<%=ctxPath%>/e008/jsp/canonE008Project.jsp?project_number='+hdrId+'&roleId='+getIdVal('roleId'));
		//$('#searchForm').val( $("#toolTip textarea").val());
		document.forms['searchForm'].action = urlDetail;	
		document.forms['searchForm'].submit();
		unBlockUsrInterface();
	}
	
	$(document).ready(function()
	{
	
/*  	    var table = $('#projectsearchtbl').DataTable( {
	    	scrollY : "100px",
			scrollX : true,
			scrollCollapse : true,
			paging : true,
	          fixedColumns:   {
	            leftColumns: 1,
	            rightColumns: 1
	        },  
		    oLanguage: { sSearch: '<b><i>Attribute Search Text</i></b>' }
	
	    } );  */
	
   	    $('input.date-picker').datepicker();
	   	$(".datePicker").each(function() {
	   			$(this).datepicker( {
	   			    dateFormat: 'dd-M-yy',
	   				changeMonth : true,
	   				changeYear : true
	   			});
	    	});

	    
/* 	    function resetSelectTitles(){
	            $('select').attr('title', function(i, title) {
	                var o=$(this).find(":selected")[0];
	                return o.title? o.title: o.text;
	            }).change(function(){
	                var o=$(this).find(":selected")[0];
	                this.title=o.title? o.title: o.text;
	            });
	            
	            $('select option').attr('title', function(i, title) {
	                return this.title? this.title: this.text;
	            });                
	        }
 *//* 	        
		$("#reqDateFrom").datepicker();
	
		$('#reqDateTo').datepicker({dateFormat: 'dd-M-yy', changeMonth: true, changeYear: true }); */
	        
	    //resetSelectTitles();

   		  
	});
	
</script>

<HTML>

<HEAD>

	<TITLE>Item Project Workbench</TITLE>

</HEAD>

<body onload="setFocus('projectNo')">

<!-- <header> -->
   <div class="logo-img">
		<div class="utility-nav">
			<div>
			<a href="<%=ctxPath%>/e008/jsp/CanonE008ItemSetupSearch.jsp">Home</a>
			</div>
		</div>
		<div class="logo">
		<a href="#">
		<img width="210" height="98" title="Canon Solutons America" alt="Canon Solutons America" src="<%=ctxPath%>/common/images/csa_logo.jpg">
		</a>
		</div>
	</div>
	<!-- <nav> -->
<!-- </header>  -->

<script src="<%=ctxPath%>/common/jquery/jquery.scrolltable.js" type="text/javascript"></script>

<div id="main_content">
	<div id="page_top">
	<h1>Item Project Workbench Search</h1>
	</div>
	<div class="table-inner">
	<form name="searchForm" method="post">
		<input type="hidden" id="hProjectNo" value="<%=strProjectNo%>"> 
		<input type="hidden" id="hProjectName" value="<%=strProjectName%>"> 
		<input type="hidden" id="hProjCat" value="<%=strProjCat%>"> 
		<input type="hidden" id="hProjDesc" value="<%=strProjDesc%>">
		<input type="hidden" id="hProjItem" value="<%=strItem%>"> 
		<input type="hidden" id="hMasterProj" value="<%=strMasterProj%>">
		<input type="hidden" id="hSubmitter" value="<%=strRequester%>"> 
		<input type="hidden" id="hreqDateFrom" value="<%=strreqDateFrm%>">
		<input type="hidden" id="hreqDateTo" value="<%=strreqDateTo%>">
		<%-- <input type="hidden" id="hRowsPerPage" value="<%=strRowsPerPage%>"> --%>
		<input type="hidden" id="hStatus" value="<%=strStatus%>">
		<input type="hidden" id="hShowData" value="<%=strShowData%>"> 
		<input type="hidden" id="hLoginUserName" value="<%=loginUserFullName%>">
		<input type="hidden" id="hLoginUserId" value="<%=loginUserID%>">
		<input type="hidden" id="hLoginUserFullName" value="<%=loginUserFullName%>">
		<input type="hidden" id="roleId" name="roleId" value="<%=strRoleId%>"/>
		<input type="hidden" id="resp_appl_id" name="resp_appl_id" value="<%=strApplId%>"/>
		
		<div class="service">
			<table id="mainSearch" class="tbl" cellspacing="5">
				<tbody>
					<tr>
						<th colspan="4">Project Information</th>
					</tr>
					<tr>
					     
						<td NOWRAP align = "left">Project Number</td>
						<td><input class="inTxt" type="text" id="projectNo" name ="projectNo" value = "<%=strProjectNo%>" ></td>
						<td NOWRAP align = "left">Project Requester</td>
						<td><input class="inTxt" type="text" id="requester" name ="requester" value = "<%=strRequester%>"></td>
					</tr>
					<tr>
						<td NOWRAP   align = "left">Project Name</td>
						<td><input class="inTxt" type="text" id="projectName" name ="projectName" value = "<%=strProjectName%>"></td>
						<td  align="left">Status  </td>
						<td colspan="" class="non_required_field">
							<select name="status" id="status">
								<option value="OPEN" <%= "OPEN".equals(strStatus)? "SELECTED" : ""%> title="All Exclude Closed">Open</option>
								<option value="ALL" <%= "ALL".equals(strStatus)? "SELECTED" : ""%> title="All Include Closed">All</option>
								<option value="ENTERED" <%= "ENTERED".equals(strStatus)? "SELECTED" : ""%> >Entered</option>
								<option value="ITEM MASTER REVIEW" <%= "ITEM MASTER REVIEW".equals(strStatus)? "SELECTED" : ""%> >Item Master Review</option>
								<option value="PENDING APPROVAL" <%= "PENDING APPROVAL".equals(strStatus)? "SELECTED" : ""%> >Pending Approval</option>
								<option value="PENDING ITEM CREATION" <%= "PENDING ITEM CREATION".equals(strStatus)? "SELECTED" : ""%> >Pending Item Creation</option>
								<option value="AWAITING PRICING" <%= "AWAITING PRICING".equals(strStatus)? "SELECTED" : ""%> >Awaiting Pricing</option>
								<option value="FAILED" <%= "FAILED".equals(strStatus)? "SELECTED" : ""%> >Failed</option>
								<option value="CLOSED" <%= "CLOSED".equals(strStatus)? "SELECTED" : ""%> >Closed</option>
								<option value="CANCELLED" <%= "CANCELLED".equals(strStatus)? "SELECTED" : ""%> >Cancelled</option>
								<option value="PENDING APPROVAL ACCOUNTING" <%= "PENDING APPROVAL ACCOUNTING".equals(strStatus)? "SELECTED" : ""%> >Pending Approval Accounting</option>
								<option value="PENDING APPROVAL INCENTIVE COMP" <%= "PENDING APPROVAL INCENTIVE COMP".equals(strStatus)? "SELECTED" : ""%> >Pending Approval Incentive Comp</option>
								<option value="PENDING APPROVAL SERVICE" <%= "PENDING APPROVAL SERVICE".equals(strStatus)? "SELECTED" : ""%> >Pending Approval Service</option>
								<option value="PENDING APPROVAL SUPPLIES" <%= "PENDING APPROVAL SUPPLIES".equals(strStatus)? "SELECTED" : ""%> >Pending Approval Supplies</option>
								<option value="PENDING APPROVAL SOLUTIONS" <%= "PENDING APPROVAL SOLUTIONS".equals(strStatus)? "SELECTED" : ""%> >Pending Approval Solutions</option>
								<option value="PENDING APPROVAL MARKETING" <%= "PENDING APPROVAL MARKETING".equals(strStatus)? "SELECTED" : ""%> >Pending Approval Marketing</option>
								<option value="PENDING APPROVAL PRICING" <%= "PENDING APPROVAL PRICING".equals(strStatus)? "SELECTED" : ""%> >Pending Approval Pricing</option>							
							</select>									
						</td> 			

					</tr>
					<tr>
						<td NOWRAP  align = "left">Project Category</td>
						<td><%=CanonE008ItemProcessHelper.genSelectHtml(lovs,"projCat",strProjCat,null,"")%></td>
						<%-- <td><%=CanonE008ItemProcessHelper.genSelectHtml(lovs,"projCat",strProjCat,null,null,null)%></td> --%>
						<td NOWRAP align = "left">Requested Date From</td> 
						<td><input type="text" id="reqDateFrom" name ="reqDateFrom" class="datePicker" value = "<%=strreqDateFrm%>" ></td>
					</tr>
					<tr>
						<td NOWRAP align = "left">Project Description</td>
						<td><input class="inTxt" type="text" id="projectDesc" name ="projectDesc" value = "<%=strProjDesc%>"></td>
	 					<td NOWRAP align = "left">Requested Date To</td>
						<td> <input type="text" id="reqDateTo" name ="reqDateTo" class="datePicker" value = "<%=strreqDateTo%>" ></td>
					</tr>
					<tr>
						<td NOWRAP align = "left">Master Project</td>
						<td><%=CanonE008ItemProcessHelper.genSelectHtml(lovs,"masterProj",strMasterProj,null,"")%></td>
						<%-- <td><%=CanonE008ItemProcessHelper.genSelectHtml(lovs,"masterProj",strMasterProj,null,null,null)%></td> --%>
						<td NOWRAP align = "left">Item Search</td>
						<td><input class="inTxt" type="text" id="SearchItem" name ="SearchItem" value = "<%=strItem%>"></td>
					</tr>
					<tr></tr>
					<tr>
							<td></td>
							<td></td>
							<td></td>
							<td style="text-align: right;" colspan="2">
								<a class="btn" href="javascript:searchProject()">Search</a>
								<a class="btn" href="javascript:clearSearch(this.form)">Clear</a>
								<a class="btn" href="javascript:createProject(this.form)">Create New</a>
								<!-- <a class="btn" href="javascript:exceldownload()">Excel</a> -->
							</td>
					</tr>	
					
				</tbody>
			</table>
			<div class="service">
				<table id="savesearchTbl" cellspacing="5">
					<tbody>
					<tr>
						<th colspan="2">Saved Search Option</th>
					</tr>
					<tr>
					    <td align="center"><%=CanonE008ItemProcessHelper.genSelectHtmlsavedsearch(lovs,"saveSearch", "")%></td>
						<td style="text-align: left;">
							<a class="btn" href="javascript:saveSearch()">Save Search</a>
						</td>
					</tr>	
				</table>
			</div>	
		</div>		
		<div id="hieghtDiv" class="rmtProbDesc" style="display: ;">
			<div id="imgProbDiv">
			<%
			if (strShowData.equals("Y"))
			{
			ArrayList results = obj0.getProjectDetails(strProjectNo,strProjectName,strProjCat,strProjDesc,strMasterProj,strRequester,strreqDateFrm,strreqDateTo,strStatus,strItem);
			int cnt = results.size();
			System.out.println("cnt is:"+cnt);
			//out.println(cnt);s
			%>
			<%if (cnt == 0)
			{
			%>	
			<table>
			<tr>
			<td class="search_res" color = "#FF0000" colspan="12" align="center"> No records found. Please adjust your search criteria.&nbsp;</td>
			</tr>
			</table>
		
		  <%} else {
		  
		  			System.out.println("@@@SM ->11");
					String navPage = "canonE008ibeCZzdNavigator.jsp";
					/* TableNavigator navigator = null; */
					String r ="";
					System.out.println("@@@SM ->12");
/* 					if (strRowsPerPage.equals("ALL"))
					{r="1000000";}
					else {r=strRowsPerPage;}
 */					
					r="1000000";
					System.out.println("@@@SM ->13");
					int pageSize = Integer.parseInt(r);
					
					System.out.println("@@@SM ->14");
					final String thisUrl = "CanonE590ItemSetupSearch.jsp";
					
					System.out.println("@@@SM ->15");
				
					//String url = "CanonE008ItemSetupSearch.jsp?page=0&hRowsPerPage="+pageSize;
					String url = "CanonE008ItemSetupSearch.jsp?page=0";
					//url = url+"&resp_id="+strRespId+"&resp_appl_id="+strApplId;
					url = url+"&hProjectNo="+java.net.URLEncoder.encode(strProjectNo);
					url = url+"&hProjectName="+java.net.URLEncoder.encode(strProjectName);
					url = url+"&hProjCat="+java.net.URLEncoder.encode(strProjCat);
					url = url+"&hProjDesc="+java.net.URLEncoder.encode(strProjDesc);
					url = url+"&hProjItem="+java.net.URLEncoder.encode(strItem);
					url = url+"&hMasterProj="+java.net.URLEncoder.encode(strMasterProj);
					url = url+"&hSubmitter="+java.net.URLEncoder.encode(strRequester);
					url = url+"&hreqDateFrom="+java.net.URLEncoder.encode(strreqDateFrm);
	                url = url+"&hreqDateTo="+java.net.URLEncoder.encode(strreqDateTo);					
					url = url+"&hStatus="+java.net.URLEncoder.encode(strStatus);
					url = url+"&hShowData="+java.net.URLEncoder.encode(strShowData);
					System.out.println("@@@SM -> url : " + url);
				    //out.println(url);  
					/* navigator = TableNavigator.get(request);
					if(navigator == null)
					navigator = new TableNavigator(cnt, pageSize);
					navigator.setURL(url);
					navigator.enableLinkClickAttribute();	 */
	  
				%>
				 
				<%-- <%@include file="canonE008ibeCZzdNavigator.jsp" %> --%>
				<div class="table-inner">
				<table class="template-table" id="projectsearchtbl" cellspacing="1">	
				<!-- <table class="altrowstable" id="projectsearchtbl" border="0" width="100%" cellspacing="2" cellpadding="4" > -->
					<tr id="report_tbl_first"> 	
						<th align="left" >Project Number</th>
					    <th align="left" >Project Name</th>
					    <th align="left" >Project Type</th>
					    <th align="left" >Project Description</th>
					    <th align="left" >Master Project</th>  
						<th align="left" >Requestor</th>  
						<th align="left" >Status</th>
						<th align="left" >Requested Date</th>
						<th align="left" >No of Items</th>  
					</tr>
					<%
					String vProjectNo = "";	
					String vProjectName = "";	
					String vProjectType = "";
					String vProjectDesc = "";
					String vMasterProject = "";
					String vStatus = "";
					String vNoOfItems = "";
					String vSubmitter = "";
					String vCreationDate = "";
					String vCreatedBy = "";
					String vUserName = "";
					String vUserFullName = "";//description of fnd_user
					String vAccessLevel = "";
		
					int beginIndex = 0;
					System.out.println("@@@SM ->17" );
					String tmp = request.getParameter("navBeginIndex");
					System.out.println("@@@SM ->18" );
					if(tmp != null)
					beginIndex = Integer.parseInt(tmp) - 1;  
					System.out.println("@@@SM ->19");
					//SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss"); 

					for(int i = beginIndex; (i<cnt && i<(beginIndex + pageSize)); i++) 
							{ System.out.println("@@@SM -> x= "+cnt);
								CanonE008AttributeBean pbObj = (CanonE008AttributeBean)results.get(i);
								
								vProjectNo = pbObj.getAttribute1()==null?"":pbObj.getAttribute1();
								vProjectName = pbObj.getAttribute2()==null?"":pbObj.getAttribute2();
								vProjectType =  pbObj.getAttribute3()==null?"":pbObj.getAttribute3();
								vProjectDesc = pbObj.getAttribute4()==null?"":pbObj.getAttribute4();
								vMasterProject = pbObj.getAttribute5()==null?"":pbObj.getAttribute5();
								vSubmitter = pbObj.getAttribute6()==null?"":pbObj.getAttribute6();
								vStatus = pbObj.getAttribute7()==null?"":pbObj.getAttribute7();
								//vModelNo =  pbObj.getAttribute3()==null?"":pbObj.getAttribute3();
								//vItemNo = pbObj.getAttribute4()==null?"":pbObj.getAttribute4();
								vCreationDate = pbObj.getAttribute8()==null?"":pbObj.getAttribute8();
								vNoOfItems  = pbObj.getAttribute9()==null?"":pbObj.getAttribute9();
								vCreatedBy = pbObj.getAttribute10()==null?"":pbObj.getAttribute10();
								vUserName = pbObj.getAttribute11()==null?"":pbObj.getAttribute11();
								vUserFullName = pbObj.getAttribute12()==null?"":pbObj.getAttribute12();
								vAccessLevel = pbObj.getAttribute13()==null?"":pbObj.getAttribute13();
								// System.out.println("@@@SM -> i= "+i);
								String className = "eventableDataCell";
													
								/* if(i%2==0)
									className = "eventableDataCell";
								else
									className = "oddtableDataCell"; */ className=""; 
					%>
							
					<%-- <tr class="<%=className%>"  onmouseover="this.style.cursor='hand';"> --%>
					<tr class='<%= i%2==0? "even" : "odd" %>'  onmouseover="this.style.cursor='hand';">
					
							<td align="center" onclick="openProject('<%=vProjectNo%>');" ><%=vProjectNo%></td>
							<td align="left" onclick="openProject('<%=vProjectNo%>');" ><%=vProjectName%></td>
							<td align="left" onclick="openProject('<%=vProjectNo%>');" ><%=vProjectType%></td>
							<td align="left" onclick="openProject('<%=vProjectNo%>');" ><%=vProjectDesc%></td>
							<td align="left" onclick="openProject('<%=vProjectNo%>');" ><%=vMasterProject%></td>
							<td align="left" onclick="openProject('<%=vProjectNo%>');" ><%=vSubmitter%></td>
							<td align="left" onclick="openProject('<%=vProjectNo%>');" ><%=vStatus%></td>
							<td align="left" onclick="openProject('<%=vProjectNo%>');" ><%=vCreationDate%></td>
							<td align="left" onclick="openProject('<%=vProjectNo%>');" ><%=vNoOfItems%></td>
							<!--td align="left" onclick="openProject('<%=vProjectNo%>');" >&nbsp;&nbsp;&nbsp;&nbsp;<%=vSubmitter%>&nbsp;(<%=vSubmitter%>)</td-->
					</tr>
					<%} //for loop end %>
				</table>
				</div>
				 			
				</form>
				<%}	 //else cnt==0
				%>					

			<%}//Show data%>
			</div>
		</div>

	<div id="DataDiv"></div>
	<div id="savesearch-dialog" title="Save Search" class="model-table" style = "display:none">
		<p>Please give name for the search criteria.</P>
		<div>
			<textarea name="save_search" id="save_search"style="width: 200px; height: 30px"></textarea>
		</div>
	</div>

</body>
</html>
