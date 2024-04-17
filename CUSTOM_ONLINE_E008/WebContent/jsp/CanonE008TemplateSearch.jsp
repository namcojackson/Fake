<%--
 /*===========================================================================+
 |      Copyright (c) 1999 Oracle Corporation, Redwood Shores, CA, USA       |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  FILENAME                                                                 |
 |            CanonE008TemplateSearch.jsp                                    |
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
 |   02-05-2016      Madhusudan Duna --  Ver:1                              |
 +===========================================================================*/
--%>
<%@ page language="java" import="oracle.apps.jtf.calendar.util.CalendarUtil"%> 
<%@ page import="java.text.*"%>
<%@ page language="java" import="java.math.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessUtil"%>
<%@page import="oracle.apps.e008.item.process.CanonE008TemplateDAO"%>
<%@page	import="oracle.apps.e008.item.process.CanonE008ItemProcessHelper"%>
<%-- <%@include file="CanonE008ItemProcessCommon.jsp"%>    --%>

<%@ page import="canon.apps.common.CanonS21SessionValidate"%>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContext"%>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder"%>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.S21Authentication"%>
<% String ctxPath = request.getContextPath() ; %>
<%!

	Hashtable lovs = new Hashtable();
	//Hashtable attlovs = new Hashtable();
	HashMap<String,ArrayList<String>> attlovs=new HashMap<String,ArrayList<String>>();
	
	void addLOV(String name, Object result) {
		lovs.put(name, CanonE008ItemProcessUtil.first(result) != null
				? (List) CanonE008ItemProcessUtil.first(result)
				: Collections.EMPTY_LIST);
	}
	static final boolean IS_DEV=CanonE008ItemProcessHelper.isDevEnviornment();

	static final List INITIAL_REQUIRED=Arrays.asList(new String[]{
	        "newTemplate",
	        "newtemplateType",
	        "newtemplateStatus"
	    }); 

	void addattributeLOV(String name, Object result) {

		List attributeValueList = (List) CanonE008ItemProcessUtil.first(result);
		ArrayList<String> attval=new ArrayList<String>();
		System.out.println(" addattributeLOV : " );
		for (int i = 0; i < attributeValueList.size(); i++) {
			CanonE008TemplateDAO.CanonE008lovRec attributeRec = (CanonE008TemplateDAO.CanonE008lovRec) attributeValueList.get(i);			
			//System.out.println(" addattributeLOV attributeRec : " + attributeRec.getListcode()+ " : " + attributeRec.getListname());
			attval.add(attributeRec.getListcode());
			attval.add(attributeRec.getListname());			
		}	
		attlovs.put(name,attval); 
	}
	
	static String returndefaultText(Object result){
		List attributeValueList = (List) CanonE008ItemProcessUtil.first(result);
		String attval="";
		System.out.println(" returndefaultText : " );
		System.out.println(" returndefaultText : " );
		for (int i = 0; i < attributeValueList.size(); i++) {
			CanonE008TemplateDAO.CanonE008lovRec attributeRec = (CanonE008TemplateDAO.CanonE008lovRec) attributeValueList.get(i);			
			//System.out.println(" returndefaultText attributeRec : " + attributeRec.getListcode()+ " : " + attributeRec.getListname());
			//attval.add(attributeRec.getListcode());
			attval = attributeRec.getListname();			
		}	
        return attval;
    }

%>

<%

	String strShowData = "N";
	String strStatus = "";
	String strAttribute1 = "";
	String strAttribute2 = "";
	String strAttribute3 = "";
	String strAttribute4 = "";
	String strAttribute5 = "";
	int cnttemplate = 0;
	BigDecimal strhtemplateId = null;
	String strhntemplate = "";
	String strhntemplateType = "";
	String strhntemplateStatus = "";
	String strhntemplateDesc = "";
	String strhnitemAssigned = "";
	String strhnattribute1 = "";
	String strhnattributeValue1 = "";
	String strhnattribute2 = "";
	String strhnattributeValue2 = "";
	String strhnattribute3 = "";
	String strhnattributeValue3 = "";
	String strhnattribute4 = "";
	String strhnattributeValue4 = "";
	String strhnattribute5 = "";
	String strhnattributeValue5 = "";
	
	String strhntemplateCategory="";

	String htemplate = ""; 
	String htemplateType = ""; 
	String htemplateStatus = ""; 
	String htemplatedesc = ""; 
	String hitemassigned = ""; 
	String hattribute1 = ""; 
	String hattribute2 = ""; 
	String hattribute3 = ""; 
	String hattribute4 = ""; 
	String hattribute5 = ""; 
	String hattributeVal1 = ""; 
	String hattributeVal2 = ""; 
	String hattributeVal3 = ""; 
	String hattributeVal4 = ""; 
	String hattributeVal5 = "";	
	
	String htemplateCategory = "";

	if (request.getParameter("hshowData")!=null)
	{
		strShowData =  request.getParameter("hshowData");
	} 	
	
	if (request.getParameter("hntemplateId")!=null)
	{
		strhtemplateId =  CanonE008ItemProcessUtil.toBigDecimal(request.getParameter("hntemplateId"),false);
	} 	
	if ((request.getParameter("hntemplate")!=null) && !("00".equals(request.getParameter("hntemplate"))))
	{
		strhntemplate =  request.getParameter("hntemplate");
	} 	
	if ((request.getParameter("hntemplateType")!=null) && !("00".equals(request.getParameter("hntemplateType")))) 
	{
		strhntemplateType =  request.getParameter("hntemplateType");
	} 	
	if ((request.getParameter("hntemplateStatus")!=null) && !("00".equals(request.getParameter("hntemplateStatus"))))
	{
		strhntemplateStatus =  request.getParameter("hntemplateStatus");
	} 	
	
	//System.out.println(" hntemplatedesc : " +  request.getParameter("hntemplatedesc"));
	
	if (request.getParameter("hntemplatedesc")!=null)
	{
		strhntemplateDesc =  request.getParameter("hntemplatedesc");
	} 	
	if (request.getParameter("hnitemAssigned")!=null)
	{
		strhnitemAssigned =  request.getParameter("hnitemAssigned");
	} 	
	if ((request.getParameter("hnattribute1")!=null) && !("00".equals(request.getParameter("hnattribute1"))))
	{
		strhnattribute1 =  request.getParameter("hnattribute1");
	} 	
	if (request.getParameter("hnattributeVal1")!=null)
	{
		strhnattributeValue1 =  request.getParameter("hnattributeVal1");
	} 	
	if ((request.getParameter("hnattribute2")!=null) && !("00".equals(request.getParameter("hnattribute2"))))
	{
		strhnattribute2 =  request.getParameter("hnattribute2");
	} 	
	if (request.getParameter("hnattributeVal2")!=null)
	{
		strhnattributeValue2 =  request.getParameter("hnattributeVal2");
	} 	
	if ((request.getParameter("hnattribute3")!=null) && !("00".equals(request.getParameter("hnattribute3"))))
	{
		strhnattribute3 =  request.getParameter("hnattribute3");
	} 	
	if (request.getParameter("hnattributeVal3")!=null)
	{
		strhnattributeValue3 =  request.getParameter("hnattributeVal3");
	} 	
	if ((request.getParameter("hnattribute4")!=null) && !("00".equals(request.getParameter("hnattribute4"))))
	{
		strhnattribute4 =  request.getParameter("hnattribute4");
	} 	
	if (request.getParameter("hnattributeVal14")!=null)
	{
		strhnattributeValue4 =  request.getParameter("hnattributeVal4");
	} 	
	if ((request.getParameter("hnattribute5")!=null) && !("00".equals(request.getParameter("hnattribute5"))))
	{
		strhnattribute5 =  request.getParameter("hnattribute5");
	} 	
	if (request.getParameter("hnattributeVal5")!=null)
	{
		strhnattributeValue5 =  request.getParameter("hnattributeVal5");
	} 	
	
	if ((request.getParameter("hntemplateCategory")!=null) && !("00".equals(request.getParameter("hntemplateCategory"))))
	{
		strhntemplateCategory =  request.getParameter("hntemplateCategory");
	} 	
	
	System.out.println(" htemplateId : " + strhtemplateId);
	System.out.println(" strhntemplate : " + strhntemplate);
	System.out.println(" strhntemplateType : " + strhntemplateType);
	System.out.println(" strhntemplateStatus : " + strhntemplateStatus);
	System.out.println(" strhntemplateDesc : " + strhntemplateDesc);
	System.out.println(" strhnitemAssigned : " + strhnitemAssigned);
	System.out.println(" strhnattribute1 : " + strhnattribute1);
	System.out.println(" strhnattributeValue1 : " + strhnattributeValue1);
	System.out.println(" strhnattribute2 : " + strhnattribute2);
	System.out.println(" strhnattributeValue2 : " + strhnattributeValue2);
	System.out.println(" strhnattribute3 : " + strhnattribute3);
	System.out.println(" strhnattributeValue3 : " + strhnattributeValue3);
	System.out.println(" strhnattribute4 : " + strhnattribute4);
	System.out.println(" strhnattributeValue4 : " + strhnattributeValue4);
	System.out.println(" strhnattribute5 : " + strhnattribute5);
	System.out.println(" strhnattributeValue5 : " + strhnattributeValue5);
	System.out.println(" strhntemplateCategory : " + strhntemplateCategory);
	
	Object result[] = null;
	Object templateresult[] = null;
	List itemList = null;
	List templateList = null;
	List TemplAttAssign = null;
	System.out.println("Start getTemplateList:");
/* 	templateresult = CanonE008TemplateDAO.getTemplateList();
	templateList = (List) CanonE008ItemProcessUtil.first(templateresult);
	cnttemplate = templateList.size();
	System.out.println("Start cnttemplate:"+cnttemplate);  */
	System.out.println(" htemplateId : " + strhtemplateId);
	if (strhtemplateId != null  || strhntemplate !=null)
	{	
		System.out.println("Start getTemplateList:111");
		templateresult = CanonE008TemplateDAO.getsearchTemplateList(strhtemplateId,strhntemplate,strhntemplateType,strhntemplateStatus,strhntemplateDesc,strhnitemAssigned,
				strhnattribute1,strhnattributeValue1,strhnattribute2,strhnattributeValue2,strhnattribute3,strhnattributeValue3,strhnattribute4,strhnattributeValue4,strhnattribute5,strhnattributeValue5);
		templateList = (List) CanonE008ItemProcessUtil.first(templateresult);
		cnttemplate = templateList.size();
		
	}
	else
	{
		System.out.println("Start getTemplateList:222");	
		templateresult = CanonE008TemplateDAO.getTemplateList();
		templateList = (List) CanonE008ItemProcessUtil.first(templateresult);
		cnttemplate = templateList.size();
		
	}	
	System.out.println("Start cnttemplate:"+cnttemplate);
	
	
	System.out.println("Start getTemplateAttr:");
	result = CanonE008TemplateDAO.getTemplateAttr(strhntemplateCategory);
	itemList = (List) CanonE008ItemProcessUtil.first(result);
	System.out.println("Itemlist:" + itemList.size());

	addLOV("saveSearch", CanonE008TemplateDAO.getsavedSearchList());

	
	addLOV("templateDP", CanonE008TemplateDAO.templateList());
	addLOV("copytemplate", CanonE008TemplateDAO.templateList());
	addLOV("templateStatus", CanonE008TemplateDAO.TemplateStatusList());
	addLOV("newtemplateStatus", CanonE008TemplateDAO.TemplateStatusList());

	addLOV("attribute", CanonE008TemplateDAO.TemplateAttributeList());
	addLOV("attribute1", CanonE008TemplateDAO.TemplateAttributeList());
	addLOV("attribute2", CanonE008TemplateDAO.TemplateAttributeList());
	addLOV("attribute3", CanonE008TemplateDAO.TemplateAttributeList());
	addLOV("attribute4", CanonE008TemplateDAO.TemplateAttributeList());
	addLOV("attribute5", CanonE008TemplateDAO.TemplateAttributeList());

	addLOV("templateType", CanonE008TemplateDAO.TemplateTypeList());
	addLOV("htemplateType", CanonE008TemplateDAO.TemplateTypeList());
	addLOV("newtemplateType", CanonE008TemplateDAO.TemplateTypeList());
	addLOV("wbDisplay", CanonE008TemplateDAO.WBDisplayList());
	addLOV("cat_name", CanonE008TemplateDAO.templateCategoryList());
	addLOV("htemplatecategory", CanonE008TemplateDAO.templateCategoryList());
	addLOV("approval_group", CanonE008TemplateDAO.ownerGroupList());
	
	for (int j = 0; j < cnttemplate; j++) 
	{
		addLOV("templateType"+j, CanonE008TemplateDAO.TemplateTypeList());
		addLOV("templateStatus"+j, CanonE008TemplateDAO.TemplateStatusList());
	}	
%>

<%!static String[] parameterValues(HttpServletRequest request, String name) {
		String param_prefix = CanonE008ItemProcessUtil.nonNullify(request
				.getParameter("param_prefix"));
		return request.getParameterValues(param_prefix + name);
	}

	static class ItemProcessException extends Exception {
		public ItemProcessException(String message, Throwable cause) {
			super(message, cause);
		}
	}

	static void checkResult(Object[] result) throws ItemProcessException {
		if (result == null) {
			Exception cause = CanonE008TemplateDAO.getException();
			throw new ItemProcessException("Database exception.", cause);
		}
	}

	static void checkErrors(Object[] result, int error_flag_index,
			int error_message_index) throws ItemProcessException {
		checkResult(result);
		String error_flag = (String) result[error_flag_index];
		String error_message = (String) result[error_message_index];
		if ("E".equals(error_flag)) {
			throw new ItemProcessException("Database error.", new Exception(
					error_message));
		}
	}

	 static String nonNullify(Object o){
	        if(o==null){
	            return "";
	        }else if(o instanceof String){
	            return ((String)o).trim();
	        }else {
	            return o.toString();
	        }
	    }
	    
	static String  getelementValue(HttpServletRequest request)
		{
			String var1 =   request.getParameter("template_id1");
			System.out.println("var1 is " + var1);
			return var1  ;
		}	
	
    String initial_required(String fieldName){
        return INITIAL_REQUIRED.indexOf(fieldName)>=0 ? "initial_required required " : "";
    }
%>

<%
/* 	String loginUserID = s21Authentication.getIdentityDetails().getUID();
			
	System.out.println("loginUserID is " + loginUserID);

	String loginUserFullName = CanonS21SessionValidate.getFullName(); */
	String userName = "";
	String userFullName = "";
	String userId = "";
	
	userName = CanonS21SessionValidate.getUserName();//objWebAppsContext.getUserName();
	S21SecurityContext context = S21SecurityContextHolder.getContext();
	S21Authentication s21Authentication = context.getAuthentication();
	userId = s21Authentication.getIdentityDetails().getUID();//objWebAppsContext.getUserId();
%> 

<style type="text/css" class="init">

	/* Ensure that the datatable table scrolls */
	div.dataTables_wrapper {
		width: 1225px; 
		/* width: 50%; */
		margin: 0 auto;
	}

	.custom-combobox {
		position: relative;
		display: inline-block;
		width: 150px;
	}

	.custom-combobox-input {
		margin: 0;
		/* padding: 0px 10px; */
		background: #FFFFFF;
		border:1px solid #cccccc;
		font-family:"Raleway", sans-serif;
		font-size:11px;
		color:#555;
		width:250px;
		height:20px;
		
	}
</style>

<script language="javascript">

	//var NLSformat = 'DD-Mon-RRRR';

	var res = "";

	
			   
	
	
	$(function() {

		var table = $('#templatetbl').DataTable({
			scrollY : "400px",
			scrollX : true,
			scrollCollapse : true,
			//deferRender: true,
			sort: false,
			//processing:false,
			//stateSave : false,
			//serverSide : false,
		    autoWidth: false,
		    searching: false,
			paging : false,
			fixedColumns : {
				leftColumns : 5
			}
		});
		
		
        $.widget( "custom.combobox", {
			_create: function() {
				this.wrapper = $( "<span>" )
					.addClass( "custom-combobox" )
					.insertAfter( this.element );

				this.element.hide();
				this._createAutocomplete();
				//this._createShowAllButton();
			},

			_createAutocomplete: function() {
				var selected = this.element.children( ":selected" ),
					value = selected.val() ? selected.text() : "<%=strhntemplate%>";
					//value = selected.val() ? selected.text() : "";

				this.input = $( "<input>" )
					.appendTo( this.wrapper )
					.val( value )
					//.attr( "title", "Select Template Name" )
					.attr( "name","templateName")
					.attr( "id","templateName")
					.attr("placeholder", "Select Template Name")
					.addClass( "inTxt custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" )
					.autocomplete({
						delay: 0,
						minLength: 0,
						source: $.proxy( this, "_source" )
					})
					.tooltip({
						classes: {
							"ui-tooltip": "ui-state-highlight"
						}
					});

				this._on( this.input, {
					autocompleteselect: function( event, ui ) {
						ui.item.option.selected = true;
						this._trigger( "select", event, {
							item: ui.item.option
						});
					}

					//autocompletechange: "_removeIfInvalid"
				});
			},

			/* _createShowAllButton: function() {
				var input = this.input,
					wasOpen = false;

				$( "<a>" )
					.attr( "tabIndex", -1 )
					.attr( "title", "Show All Items" )
					.tooltip()
					.appendTo( this.wrapper )
					.button({
						icons: {
							primary: "ui-icon-triangle-1-s"
						},
						text: false
					})
					.removeClass( "ui-corner-all" )
					.addClass( "custom-combobox-toggle ui-corner-right" )
					.on( "mousedown", function() {
						wasOpen = input.autocomplete( "widget" ).is( ":visible" );
					})
					.on( "click", function() {
						input.trigger( "focus" );

						// Close if already visible
						if ( wasOpen ) {
							return;
						}

						// Pass empty string as value to search for, displaying all results
						input.autocomplete( "search", "" );
					});
			}, */

			_source: function( request, response ) {
				var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
				response( this.element.children( "option" ).map(function() {
					var text = $( this ).text();
					if ( this.value && ( !request.term || matcher.test(text) ) )
						return {
							label: text,
							value: text,
							option: this
						};
				}) );
			},

			_removeIfInvalid: function( event, ui ) {

				// Selected an item, nothing to do
				if ( ui.item ) {
					return;
				}

				// Search for a match (case-insensitive)
				var value = this.input.val(),
					valueLowerCase = value.toLowerCase(),
					valid = false;
				this.element.children( "option" ).each(function() {
					if ( $( this ).text().toLowerCase() === valueLowerCase ) {
						this.selected = valid = true;
						return false;
					}
				});

				// Found a match, nothing to do
				if ( valid ) {
					return;
				}

				// Remove invalid value
				this.input
					.val( "" )
					.attr( "title", value + " didn't match any item" )
					.tooltip( "open" );
				this.element.val( "" );
				this._delay(function() {
					this.input.tooltip( "close" ).attr( "title", "" );
				}, 2500 );
				this.input.autocomplete( "instance" ).term = "";
			},

			_destroy: function() {
				this.wrapper.remove();
				this.element.show();
			}
		});

		$( "#template" ).combobox();
	
		
	})	
	
	function keyup1(evt, t,obj) {
	    
		/* var tbl = document.getElementById('templatetbl');
		var rows = tbl.getElementsByTagName('tr');
		for (var i = 7; i < rows.length; i++) {
		    //alert(rows[i].cells()  );
			var cells = rows[i].getElementsByTagName("td");
			for (var i = 0; i < cells.length; i++) {
				   var val = cells[i].innerHTML ;
				   alert("val " + val);
				}

		} */
 
		var table = $("templatetbl");

	    table.find('tr').each(function (i) {
	        var $tds = $(this).find('td'),
	            productId = $tds.eq(0).text(),
	            product = $tds.eq(1).text(),
	            Quantity = $tds.eq(2).text();
	        // do something with productId, product, Quantity
	        alert('Row ' + (i + 1) + ':\nId: ' + productId
	              + '\nProduct: ' + product
	              + '\nQuantity: ' + Quantity);
	    });		
		
		res = res + "keyup- keycode:" + evt.keyCode + ", value:" + t.value + "<br/>";
	    //alert("obj "+ obj);
	    $("#"+obj).val(t.value);
	    //alert("values" + t.value);
	    res = "";
	}
	
	function keyup2(t,obj) {
	    $("#"+obj).val(t.value);
//	     document.getElementById('wb_display_h').value = t.value;
	    //alert("values" + t.value + " " + obj);
	    res = "";
	}
	
	function getIdVal(x)
	{
	try
		{
		var m = document.getElementById(x).value;
		//alert(m);
		return m;
		} catch(err)
			{ alert(err);
				return false;}
	}
	//set value for given id
	function setIdVal(x,m)
	{
	try
		{
		 document.getElementById(x).value = m;
		//alert(m);
		//return m;
		} catch(err)
			{ alert(err);
				return false;}
	}
	
	function setFocus(x) {
		// alert();
		document.getElementById(x).focus();
		// alert();
		//setRowsPerPage() ;

		//var element = document.getElementById('activePBFlag');
		//element.value = document.getElementById('hActiveFlag').value;

		// alert();
	}

	function clearSearch() {
		//alert("tttt");
		$('#mainSearch').find(':input').each(function() {
			switch (this.type) {
			case 'password':
			//case 'select-multiple':"Select Template Name"
			//case 'select-one':"Select Template Name"
			case 'text':
			case 'textarea':
				$(this).val('');

				break;
/* 			case 'checkbox':
			case 'radio':
				this.checked = false;
 */			}
		})
		$('#template').prop('selectedIndex',0);
		$('#htemplateType').prop('selectedIndex',0);
		$('#htemplatecategory').prop('selectedIndex',0);
		$('#templateStatus').prop('selectedIndex',0);
		$('#attribute1').prop('selectedIndex',0);
		$('#attribute2').prop('selectedIndex',0);
		$('#attribute3').prop('selectedIndex',0);
		$('#attribute4').prop('selectedIndex',0);
		$('#attribute5').prop('selectedIndex',0);
/* 		$("table#advSearch").hide();
		$("#status").val("OPEN");
		$("#rowsPerPage").val("15");
	setFocus('projectNo'); */	
	}
	
	
/* 	window.onload = function () {
		$('#attribute1').editableSelect({ effects: 'default' });
	} */

	$(document).ready(function() {
		
/* 		$(".numericOnly").keypress(function (e) {
			//alert("22");
			if (String.fromCharCode(e.keyCode).match(/[^0-9]/g)) return true;
		});	 */
		
		 $('input[type="text"]').each(function (){
			 $(this).addClass("inTxt");	  
		});	

	 	$("#searchForm input[type=text]").each(function() {
		    var input = $(this);
		    if (input.val() == input.attr('placeholder')) {
		      input.val('');
		    }
		 });
		 
		$('[placeholder]').focus(function() {
		  var input = $(this);  	  
		  if (input.val().toLowerCase() == input.attr('placeholder').toLowerCase()) {
		    input.val('');
		    input.removeClass('placeholder');
		  }
		}).blur(function() {
		  var input = $(this);
		  if (input.val() == '' || input.val().toLowerCase() == input.attr('placeholder').toLowerCase()) {
		    input.addClass('placeholder');
		    input.val(input.attr('placeholder'));
		  }
		}).blur(); 
			
		
		$(".numericOnly").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && e.which != 46 && (e.which < 48 || e.which > 57)) {
		        
				//display error message
		        $("#errmsg").html("Digits Only").show().fadeOut("slow");
		               return false;
		    }

		   });
			
		
		/* 		$('#templatetbl').DataTable( {
	        dom: 'Bfrtip',
	        buttons: [
	            'copy', 'csv', 'excel', 'pdf', 'print'
	        ]
	    } ); */
		
/* 		//alert("Testing 121");
		function resetSelectTitles() {
			$('select').attr('title', function(i, title) {
				var o = $(this).find(":selected")[0];
				return o.title ? o.title : o.text;
			}).change(function() {
				var o = $(this).find(":selected")[0];
				this.title = o.title ? o.title : o.text;
			});

			$('select option').attr('title', function(i, title) {
				return this.title ? this.title : this.text;
			});
		}

		resetSelectTitles(); */

	});

	 
	function getFormData() {
		var myform = $('#searchForm');
		//var disabled = myform.find(':input:disabled').removeAttr('disabled');
		var ser = $('#searchForm').serialize();
		//disabled.attr('disabled','disabled');
		//alert("serialized " + ser);
		return ser;
	}

	function submitTemplateChanges() {
		/*  clear_status();
		 var handler=makeMultiErrorHandler();
		 validateInput(handler);
		 handler.done();
		 if(!handler.valid()){
		     handler.showError();
		     return;
		 } */

		var html = '<div id="dialog-confirm" title="Submit For Saving">'
				+ '<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>Are you sure to submit for Template changes for Saving?</p></div>';
		$(html)
				.dialog(
						{
							resizable : false,
							height : 150,
							modal : true,
							buttons : {
								Cancel : function() {
									$(this).dialog("close");
								},
								"Yes" : function() {
									//blockUsrInterface();
								    var xm = saveTemplate();
									//unBlockUsrInterface();
									$.unblockUI(); 
								    var res =xm.split("@@@");
								    //BigDecimal newtemplateId = res[2]; 
									//alert("res = " + res);
									 if ((res[0]=='S') && (res[2]=='S') && (res[4]=='S'))
										{
										    //$("#htemplateId").val(res[2]);
											show_error("Template changes Saved.");
										    $(this).dialog("close");
											$(this).dialog("destroy");
											//$('#'+divID).css('display','none');
											searchTemplate();
/* 									      	var url = "CanonE008TemplateMaster.jsp?";
											 document.forms['searchForm'].action = url;			
											 document.forms['searchForm'].submit();  
 */
										} else 
										{
											show_error('Failed to Save the Template changes. ');
												//alert('Error '+ res[1]);
										    $(this).dialog("close");
											$(this).dialog("destroy");
											//$('#'+divID).css('display','none');

										}
								  
								}
							}
						});

	}

	function saveTemplate()
	{
		//alert("counttemplate "+getIdVal("countTemplate"));
	var x ;
	blockUsrInterface();
	$.ajax({  
						type: "POST",
						async:false,						
						url: 'CanonE008TemplateProcess.jsp', 
						data: "action="+"SAVE" +"&cntTemplate="+getIdVal("countTemplate")+"&userId=999"+getFormData() ,
						complete: function(data)
							{  
								x =((data.responseText).replace(/^\s+|\s+$/g,''));
								//alert(x);								
							}  
					});  //ajax end
					unBlockUsrInterface();
	return x;
	
	}	

	function clearSelectList(list) {
	    // when length is 0, the evaluation will return false.
	    while (list.options.length) {
	        // continue to remove the first option until no options remain.
	        list.remove(0);
	    }
	   // list.options[0].value="";
	   // list.options[0].text="";
	}

	function callListChange(Id,row,templateId) {           
		//alert("Id " + Id + " " + row);
		
        $('#'+templateId+'searchList'+row).hide();
        $('#'+templateId+'searchBox'+row).show();
	    
 		var selectBox = document.getElementById(Id);
		//alert(selectBox.value);
		//alert($("#"+Id+" option:selected").text());
        var selectedValue = selectBox.value;
        var selectedtext = $("#"+Id+" option:selected").text();  //selectBox.value;
        
		 $("#"+templateId +"default_value"+row).val(selectedValue);
		 $("#"+templateId +"default_text"+row).val(selectedtext);
	}; 	
	
	function changefilterFunc(compid,compval,attributename,defaultvalue) {
			
			//var selectBox = document.getElementById(compid);
	    
			//var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	    
			//alert("defaultvalue "+ defaultvalue);
			//alert("selectedValue "+ selectedValue);
			
 			//if (selectedValue!=defaultvalue)
 				//defaultvalue = selectedValue;
 		
			 //var url = encodeURI('<%=ctxPath%>/e008/jsp/CanonE008TemplateProcess.jsp?action='+'GETLOV'+'&compList='+compval);   
            //alert("compid "+ compid);
            //alert("compval "+ compval);
            blockUsrInterface();
         	 $.ajax({  
						type: "POST",
						async:false,						
						url: 'CanonE008TemplateProcess.jsp', 
						data: "action="+"GETLOV"+"&attributename="+ attributename+"&selectvalue="+compval
							  +"&userId=999",
						complete: function(data)
							{  
								
							x =((data.responseText).replace(/^\s[]+|\s+$/g,''));
							//alert(x);
							res=x.split("@@")
							lovVal=res[0].split(",");
							 var defvalue = "";
							 var mainoption ="";
							 $("#"+compid).empty();
							 //alert("lovVal.length "+ lovVal.length)
							 //clearSelectList(selectBox);
							 var blankoption = $('<option value="">'+'</option>');
	  				  		 $("#"+compid).append(blankoption);
							 for(var i=0;i<lovVal.length;i++)
							 {
								 var lovValue=lovVal[i];
								 lovValue=lovValue.replace('[','').replace(']','');
								 defvalue = $.trim(lovValue);
								 var option = document.createElement("option");
								    main = lovValue.split("*")
								    option.value = $.trim(main[0]);
								    option.text = $.trim(main[1]);
								    
								    if (option.value==defaultvalue)
								    	mainoption = $('<option selected ="" value="'+ option.value +'">'+option.text+'</option>');
								    else
								    	mainoption = $('<option value="'+ option.value +'">'+option.text+'</option>');
								    
		  					        $("#"+compid).append(mainoption);
		  					        
							 }
																				
							}  
					});
         	unBlockUsrInterface();
			}
	
	
	function changeFunc(selectboxName,attributeName,defaultvalue,templateid) { 
	
		var selectBox = document.getElementById(selectboxName);
	    
		var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	    //document.getElementById(selectboxName).innerHTML = "";
		//alert("TEST");
		
 		if (selectedValue!=defaultvalue)
 			defaultvalue = selectedValue;
 		
		var productLevel3Value ="";
			
		var productLevel3Box = document.getElementById(templateid+'product_level3'); 
		//alert("productLevel3Box "+productLevel3Box);
		var productLevel3Code = document.getElementById(productLevel3Box.value);
		
		var productLevel3SelectValue = productLevel3Code.value;

 		
 		
		var x ;
		blockUsrInterface();
		$.ajax({  
					type: "POST",
					async:false,						
					url: 'CanonE008TemplateProcess.jsp', 
					data: "action="+"GETLOV"+"&attributename="+ attributeName+"&selectvalue="+"" 
					+"&productlevel3="+ productLevel3SelectValue+"&userId=999",
					complete: function(data)
						{  
							x =((data.responseText).replace(/^\s[]+|\s+$/g,''));
							//alert(x);
							res=x.split("@@")
							lovVal=res[0].split(",");
							 var defvalue = "";
							 //alert("lovVal.length "+ lovVal.length)
							 clearSelectList(selectBox);
							 for(var i=0;i<lovVal.length;i++)
							 {
								 var lovValue=lovVal[i];
								 lovValue=lovValue.replace('[','').replace(']','');
								 defvalue = $.trim(lovValue);
								 var option = document.createElement("option");
								    main = lovValue.split("*")
								    option.value = $.trim(main[0]);
								    option.text = $.trim(main[1]);
								    selectBox.options[i+1] = option;
								    if (option.value==defaultvalue)
								    	selectBox.options[i+1].selected = true;
							 }
						}  
				});  //ajax end
		
		//return x;  
		unBlockUsrInterface();		  
		
		
	}    

	function changeFuncSupplier(selectboxName,attributeName,defaultvalue,templateid) { 
		
		var selectBox = document.getElementById(selectboxName);
	    
		var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	    //document.getElementById(selectboxName).innerHTML = "";
		//alert("TEST");
		
 		var supplierBox = document.getElementById(templateid+'item_supplier'); 
 	    var supplierCode = document.getElementById(supplierBox.value);
 					
 		//alert("supplierCode " + supplierCode.value);
 					
		if (attributeName="SUPPLIER_SITE") 
			supplierValue = supplierCode.value;
		
		if (selectedValue!=defaultvalue)
 			defaultvalue = selectedValue;
 		
		var x ;
		blockUsrInterface();
		$.ajax({  
					type: "POST",
					async:false,						
					//url: 'CanonE008TemplateProcess.jsp', 
					//data: "action="+"GETLOV"+"&attributename="+ attributeName+"&selectvalue="+"" 
					//	  +"&userId=999",
					url: 'CanonE008TemplateProcess.jsp', 
						data: "action="+"GETLOV"+"&attributename="+ attributeName+"&selectvalue="+""+"&suppliercode="+supplierValue 
							  +"&userId=999",
						  
					complete: function(data)
						{  
							x =((data.responseText).replace(/^\s[]+|\s+$/g,''));
							//alert(x);
							res=x.split("@@")
							lovVal=res[0].split(",");
							 var defvalue = "";
							 //alert("lovVal.length "+ lovVal.length)
							 clearSelectList(selectBox);
							 for(var i=0;i<lovVal.length;i++)
							 {
								 var lovValue=lovVal[i];
								 lovValue=lovValue.replace('[','').replace(']','');
								 defvalue = $.trim(lovValue);
								 var option = document.createElement("option");
								    main = lovValue.split("*")
								    option.value = $.trim(main[0]);
								    option.text = $.trim(main[1]);
								    selectBox.options[i+1] = option;
								    if (option.value==defaultvalue)
								    	selectBox.options[i+1].selected = true;
							 }
						}  
				});  //ajax end
		
		//return x;  
		unBlockUsrInterface();		  
		
		
	}    
	
	function createNewTemplate(strtemplatename,strtemplatetype,strtemplatestatus,strtemplatedesc,strtemplatecopy)
	{
	var x ;
	$.ajax({  
				type: "POST",
				async:false,						
				url: 'CanonE008TemplateProcess.jsp', 
				data: "action="+"CREATE"+"&templateName="+strtemplatename+"&templateStatus="+strtemplatestatus+"&templateType="+strtemplatetype+"&templateCopy="+strtemplatecopy
				      +"&templateDesc="+strtemplatedesc
					  +"&userId=999",
				complete: function(data)
					{  
						x =((data.responseText).replace(/^\s+|\s+$/g,''));
						//alert(x);								
					}  
			});  //ajax end
	
	return x;
	
	}	
	
    
    function createTemplate()
	{
	 // alert(what);
	var name="";
	var divID ="";
	divID="create-template-dialog";
	name = "Template";
			//var myPos = [ $(window).width() / 2, 50 ];
			var myPos = { my: "center top+100", at: "center top+50", of: window };
			//alert("Should Open New create Template");
			//$('#'+divID).css('display','block');
			$('#'+divID).dialog(
				{
					title: "Create New Template",
					modal:true,
					autoOpen :false,
					//height: 100,													
					width: 800,	
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
													//alert("TEST "+ document.getElementById("newtemplateType").options[document.getElementById("newtemplateType").selectedIndex].text);
													var xm = createNewTemplate(getIdVal("newTemplate"),
															getIdVal("hnewtemplateType"),
															getIdVal("hnewtemplateStatus"),
															//"Parts",
															//"Active",
															getIdVal("newtemplateDesc"),
															getIdVal("hcopytemplate")
															//"X"
															);
													
													var res =xm.split("@@@");
												    //BigDecimal newtemplateId = res[2]; 
													//alert("Template Id" + res[2]);
													 if (res[0]=='S')
														{
														    //$("#htemplateId").val(res[2]);
															show_error("New Template Saved");
															$(this).dialog("close");
															$(this).dialog("destroy");
															$('#'+divID).css('display','none');

													      	/* var url = "CanonE008TemplateSearch.jsp?hntemplateId="+res[2];
													        //alert("url " + url);
															// document.forms['searchForm'].action = url;			
															// document.forms['searchForm'].submit();  

															 //$("#action").val("search");
													         //var url = $("#searchForm").attr('action');
													         //blockUsrInterface();
													         $.post(url, $("#searchForm").serialize(), function(data) {
													             $("#divCanonE008TemplateMain").html("");
													             $("#divCanonE008TemplateMain").html(data);
													             //unBlockUsrInterface();
													         }); */
													         
													         
													         refreshPage(res[2],getIdVal("newTemplate"),getIdVal("htemplateType"),getIdVal("templateStatus"),getIdVal("htemplate_description"),getIdVal("hitemassigned"),
																	 getIdVal("attribute1"),getIdVal("attributeValue1"),getIdVal("attribute2"),getIdVal("attributeValue2"),
																			 getIdVal("attribute3"),getIdVal("attributeValue3"),getIdVal("attribute4"),getIdVal("attributeValue4"),
																			 getIdVal("attribute5"),getIdVal("attributeValue5"),getIdVal("htemplatecategory"));
													         
															 
														} else {
															show_error('New Template Failed - '+res[1]);
															   }

										   }
							
							}	
				});
				$('#'+divID).dialog("open");
				setTimeout(function (){
				   $(document).css({"z-index":"1"});
					$(".ui-widget-overlay").css({"z-index":"2"});
					$(".ui-dialog").css({"z-index":"3"});
				},1000);
  				

	}
 
	function searchTemplate() {
		/*  clear_status();
		 var handler=makeMultiErrorHandler();
		 validateInput(handler);
		 handler.done();
		 if(!handler.valid()){
		     handler.showError();
		     return;
		 } */
	     //alert("htemplate_description "+ getIdVal("htemplate_description"));
		 
 		 refreshPage(null,getIdVal("templateName"),getIdVal("htemplateType"),getIdVal("templateStatus"),getIdVal("htemplate_description"),getIdVal("hitemassigned"),
				 getIdVal("attribute1"),getIdVal("attributeValue1"),getIdVal("attribute2"),getIdVal("attributeValue2"),
						 getIdVal("attribute3"),getIdVal("attributeValue3"),getIdVal("attribute4"),getIdVal("attributeValue4"),
						 getIdVal("attribute5"),getIdVal("attributeValue5"),getIdVal("htemplatecategory")	);
		 
	}

    function refreshPage(htemplateId,htemplate,htemplateType,templateStatus,htemplatedesc,hitemassigned,
			hattribute1,hattributeVal1,hattribute2,hattributeVal2,hattribute3,hattributeVal3,hattribute4,hattributeVal4,hattribute5,hattributeVal5,htemplateCategory	)
    {
    	
    	 if (htemplate=="Select Template Name")	 
    		 var htemplate="";
    	 if (hattributeVal1=="Attribute Value")	 
    		 var hattributeVal1="";
    	 if (hattributeVal2=="Attribute Value")	 
    		 var hattributeVal2="";
    	 if (hattributeVal3=="Attribute Value")	 
    		 var hattributeVal3="";
    	 if (hattributeVal4=="Attribute Value")	 
    		 var hattributeVal4="";
    	 if (hattributeVal5=="Attribute Value")	 
    		 var hattributeVal5="";
    	 if (hitemassigned=="Item Assigned")	 
    		 var hitemassigned="";
    	 if (htemplatedesc=="Template Description")	 
    		 var htemplatedesc="";

    	var objForm = document.searchForm;

      	var url = "CanonE008TemplateSearch.jsp?hntemplateId="+htemplateId+"&hntemplate="+htemplate+"&hntemplateType="+htemplateType+
     			"&hntemplateStatus="+templateStatus+"&hntemplatedesc="+htemplatedesc+"&hnitemassigned="+hitemassigned+
     			"&hnattribute1="+hattribute1+"&hnattributeVal1="+hattributeVal1+"&hnattribute2="+hattribute2+"&hnattributeVal2="+hattributeVal2+
     			"&hnattribute3="+hattribute3+"&hnattributeVal3="+hattributeVal3+"&hnattribute4="+hattribute4+"&hnattributeVal4="+hattributeVal4+
     			"&hnattribute5="+hattribute5+"&hnattributeVal5="+hattributeVal5+"&hntemplateCategory="+htemplateCategory+"&hshowData=Y";  
         //alert("url " + url);
         //objForm.action = url;
         //objForm.submit();
         
		 //document.forms['searchForm'].action = url;			
		 //document.forms['searchForm'].submit();  
		 
		 $("#action").val("search");
         //var url = $("#searchForm").attr('action');
         blockUsrInterface();
         $.post(url, $("#searchForm").serialize(), function(data) {
             $("#divCanonE008TemplateMain").html("");
             $("#divCanonE008TemplateMain").html(data);
             unBlockUsrInterface();
         });

	 	
    }    
	
    function error_message(data){
        var re=new RegExp("^<!--ERROR_MESSAGE(.*)ERROR_MESSAGE-->","m");
        var match=re.exec(data);
        if (match){
            return match[1];
        }else{
            return "";
        }
    }	

    var defaultSingleErrorHandler=makeSingleErrorHandler();
    
    function show_error(errmsg,response){
        defaultSingleErrorHandler.handle(errmsg);
        defaultSingleErrorHandler.showError();
        <% if( IS_DEV ){ %>
            if(typeof response != "undefined"){
              $("#project_status" ).append('&nbsp;<a class="jsp_error" href="#">View error detail (visible only in dev environment)</a>');
              $("#project_status .jsp_error").click(function (){
                 var newWindow = window.open("","Error","width=400,height=500,scrollbars=1,resizable=1")
                 newWindow .document.open()
                 newWindow .document.write(response)
                 newWindow .document.close()
              });
            }
        <%} %>    
    }

    function update_status( t) {
        var project_status = $("#project_status" );
        project_status
          .text( t )
          .addClass( "ui-state-highlight" );
        setTimeout(function() {
          project_status.removeClass( "ui-state-highlight", 1500 );
        }, 500 );
    }
    
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

    function clear_status() {
        var project_status = $("#project_status" );
        project_status
          .text("")
          .removeClass( "ui-state-highlight" );
        $(".ui-validation-error").removeClass("ui-validation-error");
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

	function savesearchCriteria(strtemplatename,strtemplatetype,strcattype,strtemplatestatus,strtemplatedesc,stritemAssigned,
								strattribute1, strattribute1value,
								strattribute2, strattribute2value,
								strattribute3, strattribute3value,
								strattribute4, strattribute4value,
								strattribute5, strattribute5value,struser,strsevedname)
	{
	var x ;
	$.ajax({  
				type: "POST",
				async:false,						
				url: 'CanonE008TemplateProcess.jsp', 
				data: "action="+"SAVESEARCH"+"&templateName="+strtemplatename+"&templateType="+strtemplatetype+"&catType="+strcattype+"&templateStatus="+strtemplatestatus+"&templateDesc="+strtemplatedesc+"&itemAssigned="+stritemAssigned
							+"&attribute1="+strattribute1+"&attribute1value="+strattribute1value
							+"&attribute2="+strattribute2+"&attribute2value="+strattribute2value
							+"&attribute3="+strattribute3+"&attribute3value="+strattribute3value
							+"&attribute4="+strattribute4+"&attribute4value="+strattribute4value
							+"&attribute5="+strattribute5+"&attribute5value="+strattribute5value
					  +"&userId="+struser+"&savedName="+strsevedname,
				complete: function(data)
					{  
						x =((data.responseText).replace(/^\s+|\s+$/g,''));
						//alert(x);								
					}  
			});  //ajax end
	
	return x;
	
	}	
    
    function exceldownload()
	{
    	window.open('data:application/vnd.ms-excel,' + encodeURIComponent($('#templatetbl').html()));	
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
													//alert("TEST "+ document.getElementById("newtemplateType").options[document.getElementById("newtemplateType").selectedIndex].text);
 													var xm = savesearchCriteria (getIdVal("template"),getIdVal("htemplateType"),
 															getIdVal("htemplatecategory"),	getIdVal("templateStatus"),getIdVal("htemplate_description"),getIdVal("hitemassigned"),
 															 getIdVal("attribute1"),getIdVal("attributeValue1"),getIdVal("attribute2"),getIdVal("attributeValue2"),
 															 getIdVal("attribute3"),getIdVal("attributeValue3"),getIdVal("attribute4"),getIdVal("attributeValue4"),
 															 getIdVal("attribute5"),getIdVal("attributeValue5"),getIdVal("hLoginUserId"),getIdVal("save_search"))
													
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

 													      	var url = "CanonE008TemplateMaster.jsp?";
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
    

    function callSavedSearchChange(Id) {                
 		var selectBox = document.getElementById(Id);
        var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	    //alert(selectedValue);
        //var url= encodeURI('<%=ctxPath%>/e008/jsp/CanonE008TemplateProcess.jsp?"action="+"GETSAVESEARCH"+"&savedName='+escape(selectedValue));
        //alert(url);         
       	 $.ajax({  
					type: "POST",
					async:false,						
					url: 'CanonE008TemplateProcess.jsp', 
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
								 
								 //if ((templateAttr.trim() != null) && (templateAttr.trim() != "null") && (templateAttr.trim() != ""))
								 if (($.trim(templateAttr) != null) && ($.trim(templateAttr) != "null") && ($.trim(templateAttr) != ""))	 
								     {
									 defvalue = $.trim(templateAttr);
								     //alert("111")
								     }
								 else
									 {
									 defvalue = "";
								     //alert("2222");
									 }
								 
								 //alert(" i-" +i);
								 //alert(" templateAttr-" +defvalue);
								 
								 if (i=="1")
								 	$("#template").val(defvalue);

								 if (i=="2")
								 	$("#htemplateType").val(defvalue);

								 if (i=="3")
									 	$("#htemplatecategory").val(defvalue);

								 if (i=="4")
									 	$("#templateStatus").val(defvalue);

								 if (i=="5")
								 		$("#hitemassigned").val(defvalue);

								 if (i=="6")
									 	$("#htemplate_description").val(defvalue);
								 
								 if (i=="7")
									 	$("#attribute1").val(defvalue);

								 if (i=="8")
									 	$("#attributeValue1").val(defvalue);

								 if (i=="9")
									 	$("#attribute2").val(defvalue);

								 if (i=="10")
									 	$("#attributeValue2").val(defvalue);
								 
								 if (i=="11")
									 	$("#attribute3").val(defvalue);

								 if (i=="12")
									 	$("#attributeValue3").val(defvalue);
								 
								 if (i=="13")
									 	$("#attribute4").val(defvalue);

								 if (i=="14")
									 	$("#attributeValue4").val(defvalue);

								 if (i=="15")
									 	$("#attribute5").val(defvalue);

								 if (i=="16")
									 	$("#attributeValue5").val(defvalue);
 							 }															
						}  
				});  //ajax end 
	}; 
    
	function showProgressCursor()
	{
	   $("#progressMessageLbl").html("Loading....");
	   $("#progressMessage").show();
	}
    
	var ray={
			ajax:function(st)
				{
					this.show('load');
				},
			show:function(el)
				{
					this.getID(el).style.display='';
				},
			getID:function(el)
				{
					return document.getElementById(el);
				}
			}
	
	//$("#divCanonE008TemplateMain").show();
	
	function exceldownload()
	{
	    //var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
	    var tab_text="<table border='2px'>";
	    var head_text="";
	    var cel_text="";
	    var textRange; var j=0;
	    tab = document.getElementById('templatetbl'); // id of table
	    
	    var refTab = document.getElementById("templatetbl")
	    
		// Header Excel Row
	    for(j = 1 ; j < 5 ; j++) 
	    {  
	    	tab_text=tab_text+"<tr>";
	    	//tab_text=tab_text+tab.rows[j].innerHTML +"</tr>";
		    var headcol=5;
		    var templId=0;
		    
	        for (var y = 0; y < tab.rows[j].cells.length-4; y++) 
	        	{
	        		
	        	 	if ((j==1) && (y==headcol))
	        		{	
	        			tab_text=tab_text+"<td rowspan=1 colspan=3>";	
	        			head_text= tab.rows[j].cells[y].innerHTML;
	        			headcol = headcol+1;
	        		}
	        		else if ((j==2) && (y==headcol))
	        		{	
	        			tab_text=tab_text+"<td rowspan=1 colspan=3>";
	        			var inputType = $('#'+'templateType'+(templId)).get(0).tagName;
	        			//alert(inputType);
	        			
	        			if(inputType=='SELECT') 
	        			{
		        			var sel = document.getElementById('templateType'+templId);
		        			var opt = sel.options[sel.selectedIndex];
		        			head_text = opt.text;
	        			}
	        			headcol = headcol+1;
	        			templId = templId+1;
	        		}
	        		else if ((j==3) && (y==headcol))
	        		{	
	        			tab_text=tab_text+"<td rowspan=1 colspan=3>";
	        			var inputType = $('#'+'templateStatus'+(templId)).get(0).tagName;
	        			//alert(inputType);
	        			
	        			if(inputType=='SELECT') 
	        			{
		        			var sel = document.getElementById('templateStatus'+templId);
		        			var opt = sel.options[sel.selectedIndex];
		        			head_text = opt.text;
	        			}
	        			headcol = headcol+1;
	        			templId = templId+1;
	        		}
	        		else if ((j==4) && (y==headcol))
	        		{	
	        			tab_text=tab_text+"<td rowspan=1 colspan=3>";
	        			var inputType = $('#'+'template_desc'+templId).get(0).tagName;
	        			//alert(inputType);
	        			
	        			if(inputType=='TEXTAREA') 
	        			{
		        			var sel = document.getElementById('template_desc'+templId);
		        			head_text = sel.value;
	        			}
	        			headcol = headcol+1;
	        			templId = templId+1;
	        		}
	        		else
	        		{
	        			if (y==4)
		        			tab_text=tab_text+"<td bgcolor='#003b4e'><FONT COLOR='#ffff00'>";
		        		else	
	        				tab_text=tab_text+"<td><FONT COLOR='#000000'>";
	        				
	        			head_text= tab.rows[j].cells[y].innerHTML;
	        		}
	       			tab_text=tab_text+head_text;
			        tab_text=tab_text+"</FONT></td>";
	        	}
	        tab_text=tab_text+"</tr>";
	        //alert("tab_text "+ tab_text); 
 	    
	    //tab_text= tab_text.replace(/th/g, "td");
	    //alert("Replace tab_text "+ tab_text);
	    }
	    
	    //Datatable Rows
	    //alert(tab.rows.length);
        for(k = 5 ; k < tab.rows.length-1 ; k++) 
	    {     
	       // tab_text=tab_text+tab.rows[j].innerHTML +"</tr>";
	        
	        tab_text=tab_text+"<tr>";
		    var tempId=0;
		    var reqtempId=0;
		    var	validtempId=0;
		    var tempcol=7;
		    var reqtempcol=5
			var validtempcol=6;
			
	        for (var y = 0; y < tab.rows[k].cells.length-4; y++) {
	        
	    			if (k==5)
    	    			tab_text=tab_text+"<td bgcolor='#003b4e'><FONT COLOR='#ffff00'>";
    	    		else if (y==4)
        	    		tab_text=tab_text+"<td bgcolor='#003b4e'><FONT COLOR='#ffff00'>";
    	    		else
	        			tab_text=tab_text+"<td><FONT COLOR='#000000'>"; 
	        			    
	        		//cel_text= tab.rows[jj].cells[yy].innerHTML;
	        		//alert(document.getElementById('wb_display_h'+(jj-5)).value);
	        		if (k!=5)
	        		{
		        		if (y==0)
		        			cel_text= document.getElementById('wb_display_h'+(k-6)).value;
		        		else if (y==1)
		        			cel_text= document.getElementById('display_sort_h'+(k-6)).value;
		        		else if (y==2)
		        			cel_text= document.getElementById('cat_name_h'+(k-6)).value;
		        		else if (y==3)
		        			cel_text= document.getElementById('approval_group_h'+(k-6)).value;
	 	        		else if (y==reqtempcol) 
	        			{ 
 	        			
		 	       	        var templateIdStr = document.getElementById('template_id'+reqtempId).value;
			 	   			reqtempId=reqtempId+1;
			 	   			reqtempcol= reqtempcol + 3

	 	        			var inputType = $('#'+templateIdStr+'req_value'+(k-6)).get(0).tagName;
 	        				//alert(inputType);
		        			
		        			if(inputType=='INPUT') 
		        			{
		        				//alert(1);	
			        			var sel = document.getElementById(templateIdStr+'req_value'+(k-6));
			        			if (sel.checked) 
			        				cel_text = "Yes";
			        			else
			        				cel_text = "";
			        			
		        			} 
	        			} 
	 	        		else if (y==validtempcol) 
	        			{ 
 	        			
		 	       	        var templateIdStr = document.getElementById('template_id'+validtempId).value;
			 	   			validtempId=validtempId+1;
			 	   			validtempcol= validtempcol + 3

	 	        			var inputType = $('#'+templateIdStr+'valid_value'+(k-6)).get(0).tagName;
 	        				//alert(inputType);
		        			
		        			if(inputType=='INPUT') 
		        			{
		        				//alert(1);	
			        			var sel = document.getElementById(templateIdStr+'valid_value'+(k-6));
			        			if (sel.checked) 
			        				cel_text = "Yes";
			        			else
			        				cel_text = "";
			        			
		        			} 
	        			} 
	 	        		else if (y==tempcol) 
		        			{ 
	 	        			
			 	       	        var templateIdStr = document.getElementById('template_id'+tempId).value;
				 	   			tempId=tempId+1;
				 	   			tempcol= tempcol + 3

		 	        			var inputType = $('#'+templateIdStr+'default_value'+(k-6)).get(0).tagName;
	 	        				//alert(inputType);
			        			
			        			if(inputType=='SELECT') 
			        			{
			        				//alert(1);	
				        			var sel = document.getElementById(templateIdStr+'default_value'+(k-6));
				        			var opt = sel.options[sel.selectedIndex];
				        			cel_text = opt.text;
				        			
			        			} 
			        			//alert(2);
			        			if(inputType=='INPUT') 
			        			{
			        				//alert(3);
			        				cel_text =document.getElementById(templateIdStr+'default_value'+(k-6)).value;
			        			}
			        			//alert(4);
		        			} 
		        		else
		        			cel_text= tab.rows[k].cells[y].innerHTML;
	        		}	
		        	else
		        	{
		        		cel_text= tab.rows[k].cells[y].innerHTML;
		        		
		        	}
		        		
	        		//alert(cel_text);
	        		
	        		tab_text=tab_text+cel_text;
		        	
			        tab_text=tab_text+"</FONT></td>";
	        	
	        }
	        
	        tab_text=tab_text+"</tr>"; 
	    }
 
	    tab_text=tab_text+"</table>";
	    //tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
	    //tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
	    //tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

	    var ua = window.navigator.userAgent;
	    var msie = ua.indexOf("MSIE "); 
    
    
	   if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
	    {
	        txtArea1.document.open("txt/html","replace");
	        txtArea1.document.write(tab_text);
	        txtArea1.document.close();
	        txtArea1.focus(); 
	        sa=txtArea1.document.execCommand("SaveAs",true,"*.xls");
	       // alert("Template download successfully. ");
	    }  
	    else                 //other browser not tested on IE 11
	        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));
	   
	   if (sa)
		   alert("Template download successfully. ");
	   
	    //return (sa);
	}	
	
</script>

<%-- <link rel="stylesheet" href='<%=ctxPath%>/e008/css/normalize.css' ype='text/css'>
<script type="text/javascript" src='<%=ctxPath%>/e008/js/modernizr.js' type="text/javascript"></script>
<script>
	 Modernizr.load({
		 	test: Modernizr.datalistelem,
		 	nope: ['<%=ctxPath%>/e008/js/jquery.datalist.js', '<%=ctxPath%>/e008/js/load.datalist.js']
		 });     
</script>  --%>
<iframe id="txtArea1" style="display:none"></iframe>
<div id="main_content">
	<div id="page_top">
	<h1>Item  Template  Master</h1>
	</div>
	<div class="table-inner">
	<form name="searchForm" method="post" id="searchForm" action="CanonE008TemplateSearch.jsp" >
		<input style="display:none;" name="action" id="action" value="" />
		<input style="display:none;" name="countTemplate" id="countTemplate" value="<%=cnttemplate %>" />
		<input type="hidden" id="hLoginUserName" value="<%=userName%>">
		<input type="hidden" id="hLoginUserId" value="<%=userId%>"> <input	type="hidden" id="hLoginUserFullName" value="<%=userFullName%>">
    			
		<div class="service">
			<table id="mainSearch" cellspacing="5">
				<tbody>
					<tr>
					<th colspan="2">Template Information</th>
					<th colspan="2">Attribute Information</th>
					</tr>
					<tr>
							<!-- <td NOWRAP >Template Name</td> -->
							<td><%=CanonE008ItemProcessHelper.genSelectdataListHtml(lovs,"templateDP", strhntemplate, "", "Select Template Name" )%></td>
							<!-- <td><img onclick="javascript:createTemplate();" src="<%=ctxPath%>/common/images/download.png"></td> -->
							 <td style="text-align: left;">
								<!-- <a class="btn" href="javascript:createTemplate()">Add</a> -->
							</td>
							
<%--   					<input style="display:none;" id="hhattribute1" name="hhattribute1" value="<%=strhnattribute1%>">
						   <%
  							String stratt1 = "keyup2(this,"+"'hhattribute1"+"')";
							%> --%>
							<%-- <td id="attribute1"><%=CanonE008ItemProcessHelper.genSelectHtmlTemplate(lovs,"attribute1", strhnattribute1,stratt1, "Select Attribute Name", "")%></td> --%>
   						    
   						    <td><%=CanonE008ItemProcessHelper.genSelectHtml(lovs,"attribute1", strhnattribute1, "", "Select Attribute Name" )%></td>
							<td><input type="text" class="inTxt" placeholder="Attribute Value"	id="attributeValue1" name="attributeVal1"	value="<%=strhnattributeValue1%>"></td>						
					</tr>	
					<tr>
							<!-- <td NOWRAP >Template Type</td> -->
							<td><%=CanonE008ItemProcessHelper.genSelectHtml(lovs,"htemplateType", strhntemplateType, "", "Select Template Type")%></td>
							<td></td>
<%-- 						   <input style="display:none;"  id="hhattribute2" name="hhattribute2" value="<%=strhnattribute2%>"> 
						    <%
  							String stratt2 = "keyup2(this,"+"'hhattribute2"+"')";
							%>
 --%>							<td><%=CanonE008ItemProcessHelper.genSelectHtml(lovs,"attribute2", strhnattribute2, "", "Select Attribute Name" )%></td>
							<%-- <td id="attribute2"><%=CanonE008ItemProcessHelper.genSelectHtmlTemplate(lovs,"attribute2", strhnattribute2,stratt2, "Select Attribute Name", "")%></td> --%>
							<td><input type="text" class="inTxt" placeholder="Attribute Value" id="attributeValue2" name="attributeVal2" value="<%=strhnattributeValue2%>"></td>
					</tr>
					<tr>
							<!-- <td NOWRAP>Template Category</td> -->
							<td><%=CanonE008ItemProcessHelper.genSelectHtml(lovs,"htemplatecategory", strhntemplateCategory,"", "Select Attribute Group")%></td>
							<td></td>
<%--  						    <input style="display:none;"  id="hhattribute3" name="hhattribute3" value="<%=strhnattribute3%>">
						    <%
  							 String stratt3 = "keyup2(this,"+"'hhattribute3"+"')";
							 %> --%>
							<td><%=CanonE008ItemProcessHelper.genSelectHtml(lovs,"attribute3", strhnattribute3, "", "Select Attribute Name" )%></td>
							<%-- <td id="attribute3"><%=CanonE008ItemProcessHelper.genSelectHtmlTemplate(lovs, "attribute3", strhnattribute3,stratt3, "Select Attribute Name", "")%></td> --%>
							<td><input type="text"	class="inTxt" placeholder="Attribute Value" id="attributeValue3" name="attributeVal3" value="<%=strhnattributeValue3%>"></td>
					</tr>
					<tr>
							<!-- <td NOWRAP>Template Status</td> -->
							<td><%=CanonE008ItemProcessHelper.genSelectHtml(lovs,	"templateStatus", strhntemplateStatus, "", "Select Template Status")%></td>
							<td></td>
						    <%-- <input style="display:none;"  id="hhattribute4" name="hhattribute4" value="<%=strhnattribute4%>">
						    <%
  								String stratt4 = "keyup2(this,"+"'hhattribute4"+"')";
							%> --%>
							<td><%=CanonE008ItemProcessHelper.genSelectHtml(lovs,"attribute4", strhnattribute4, "", "Select Attribute Name" )%></td>
							<%-- <td id="attribute4"><%=CanonE008ItemProcessHelper.genSelectHtmlTemplate(lovs,"attribute4", strhnattribute4,stratt4, "Select Attribute Name", "")%></td> --%>
							<td><input type="text"	class="inTxt" placeholder="Attribute Value" id="attributeValue4" name="attributeVal4" value="<%=strhnattributeValue4%>"></td>
					</tr>
					<tr>
							<!-- <td NOWRAP>Item Assigned</td> -->
							<td><input type="text"	class="inTxt" placeholder="Item Assigned" id="hitemassigned" value="<%=strhnitemAssigned%>" name="hitemassigned"  value=""></td>
							<td></td>
						    <%-- <input style="display:none;"  id="hhattribute5" name="hhattribute5" value="<%=strhnattribute5%>">
						   	<%
  								String stratt5 = "keyup2(this,"+"'hhattribute5"+"')";
							%> --%>
							<td><%=CanonE008ItemProcessHelper.genSelectHtml(lovs,"attribute5", strhnattribute5, "", "Select Attribute Name" )%></td>
							<%-- <td id="attribute5"><%=CanonE008ItemProcessHelper.genSelectHtmlTemplate(lovs,"attribute5", strhnattribute5,stratt5, "Select Attribute Name", "")%></td> --%>
							<td><input type="text"	class="inTxt" placeholder="Attribute Value" id="attributeValue5" name="attributeVal5" value="<%=strhnattributeValue5%>"></td>
					</tr>
					<tr>
							<!-- <td NOWRAP>Template Description</td> -->
							<td><textarea class="inTxt" placeholder="Template Description" rows="3" cols="20" name="htemplate_description" id="htemplate_description" Value="<%=strhntemplateDesc%>"></textarea></td>
							<td></td>
							<td></td>
							<td style="text-align: right;" colspan="2">
								<a class="btn" href="javascript:searchTemplate()">Search</a>
								<a class="btn" href="javascript:clearSearch()">Clear</a>
							
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

		<div class="template_btn">
			<table class="whdr" >
				<tbody>
					<tr>
						<td align="right" >
							<!-- <a class="btn" href="javascript:exceldownload()">Excel</a> -->
							<%
								if (strShowData.equals("Y")) {
							%>
							<a href="javascript:exceldownload()"><img height="24" width="38" style="margin-bottom: -8px;" src="<%=ctxPath%>/e008/images/excel.gif" /></a>
							<% } %>
							<a class="btn" href="javascript:createTemplate()">Add New Template</a>
							<%
								if (strShowData.equals("Y")) {
							%>
							<a class="btn" href="javascript:submitTemplateChanges()">Save Template</a>
							<% } %>
						</td>
					</tr>
				</tbody>
			</table>
		</div>


			<div id="imgProbDiv">
			<%
				if (strShowData.equals("Y")) {
					int cnt = Integer.parseInt("1");
					//out.println(cnt);
					//final String thisUrl = "CanonE008TemplateMaster.jsp";
			%>		
			    <div class="table-inner">
					
				<table class="template-table" id="templatetbl" cellspacing="1">
					<thead>
						<tr>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
							<th style="display:none;">Template Id</th>
							<%
								System.out.println("templateList:" + templateList.size());
									for (int k = 0; k < templateList.size(); k++) {
										CanonE008TemplateDAO.CanonE008TemplateListRec templateRec = (CanonE008TemplateDAO.CanonE008TemplateListRec) templateList
												.get(k);
							%>
							<th style="display:none;" align="left" colspan="3"><input class="inTxt" id="template_id<%=k%>" name="template_id<%=k%>" value="<%=templateRec.getTemplateid() %>"></th>
							<%
								}
							%>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
						</tr>

						<tr>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th nowrap align="left">Template Name</th>
							<%
								System.out.println("templateList:" + templateList.size());
									for (int k = 0; k < templateList.size(); k++) {
										CanonE008TemplateDAO.CanonE008TemplateListRec templateRec = (CanonE008TemplateDAO.CanonE008TemplateListRec) templateList
												.get(k);
							%>
							<th align="left" colspan="3" id="template_name" name="template_name<%=k%>"><%=templateRec.getTemplatename()%></th>
			  			    <%--<div><input class="inTxt" id="template_id<%=k%>" name="template_id<%=k%>" value="<%=templateRec.getTemplateid() %>"></div>
			  			     <script>
			  			  		document.getElementById("template_id<%=k%>").style.display="none";
			  			    </script> --%>
							<%
								}
							%>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
						</tr>
						<tr>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th nowrap align="left">Template Type</th>
							<%
								System.out.println("templateList:" + templateList.size());
									for (int k = 0; k < templateList.size(); k++) {
										CanonE008TemplateDAO.CanonE008TemplateListRec templateRec = (CanonE008TemplateDAO.CanonE008TemplateListRec) templateList.get(k);
							%>
							<% String templateTypestr = "templateType"+k; %>
			
							<th align="left" colspan="3"><%=CanonE008ItemProcessHelper.genSelectHtml(lovs,templateTypestr, templateRec.getTemplatetype(), "","")%></th>
							<%
								}
							%>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
						</tr>
						<tr>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th nowrap align="left">Template Status</th>
							<%
								System.out.println("templateList:" + templateList.size());
									for (int k = 0; k < templateList.size(); k++) {
										CanonE008TemplateDAO.CanonE008TemplateListRec templateRec = (CanonE008TemplateDAO.CanonE008TemplateListRec) templateList
												.get(k);
							%>
							<% String templateStatusstr = "templateStatus"+k; %>
							<th align="left" colspan="3"><%=CanonE008ItemProcessHelper.genSelectHtml(lovs,templateStatusstr, templateRec.getTemplatestatus(),"", "")%></th>
							<%
								}
							%>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
						</tr>
						<tr>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th nowrap align="left">Template Description</th>
							<%
								System.out.println("templateList:" + templateList.size());
									for (int k = 0; k < templateList.size(); k++) {
										CanonE008TemplateDAO.CanonE008TemplateListRec templateRec = (CanonE008TemplateDAO.CanonE008TemplateListRec) templateList
												.get(k);
							%>
							<th align="left" colspan="3"><textarea rows="2" cols="30" name="template_desc<%=k%>" id="template_desc<%=k%>"><%=CanonE008ItemProcessUtil.nonNullify(templateRec.getTemplatedescription())%></textarea></th>
							<%
								}
							%>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
						</tr>
						<tr>
							<th align="left">Workbench Display</th>
							<th align="left">Display Sort</th>
							<th align="left">Attribute Group</th>
							<th align="left">Approval Group Owner</th>
							<th align="left">Attribute Name</th>
							<%
								for (int i = 0; i < cnttemplate; i++) {
							%>
							<th align="left"  id="req">Req</th>
							<th align="left"  id="valid">Valid</th>
							<th align="left"  id="Default">Default</th>
							<%
								}
							%>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
							<th style="display:none;"></th>
						</tr>
					</thead>
			
					<tbody>

						<%
							//out.println("Itemlist1:" + itemList.size());
								for (int i = 0; i < itemList.size(); i++) {
									CanonE008TemplateDAO.CanonE008TemplateAttributeRec attributeRec = (CanonE008TemplateDAO.CanonE008TemplateAttributeRec) itemList.get(i);
						%>
			
			
						<tr id="attr_<%=attributeRec.getAttributeid()%>" >
							<input style="display:none;" name="attribute_id" value="<%=attributeRec.getAttributeid()%>">
							<%
							String strFunc = "keyup2(this,"+"'wb_display_h"+i+"')";
							String strcatFunc = "keyup2(this,"+"'cat_name_h"+i+"')";
							String stragroupFunc = "keyup2(this,"+"'approval_group_h"+i+"')";
							%>
							<td align="left" ><%=CanonE008ItemProcessHelper.genSelectHtmlTemplate(lovs,"wbDisplay", attributeRec.getWorkbenchdisplay(),strFunc,"", "")%></td>
			 				<td align="left"><input style="width:25px" class="inTxt" type="text" onkeyup="keyup1(event,this,'display_sort_h<%=i%>');" id="display_sort" name="display_sort" value="<%=attributeRec.getDisplaysort() %>"></td>	 
							<td><%=CanonE008ItemProcessHelper.genSelectHtmlTemplate(lovs,"cat_name", attributeRec.getCategoryname(),strcatFunc,"", "")%></td>
							<td><%=CanonE008ItemProcessHelper.genSelectHtmlTemplate(lovs,"approval_group", attributeRec.getApprovalgroupowner(),stragroupFunc,"", "")%></td>
							<td align="left" name="attribute_name"	style="font-size: 12px; color: #FFF380; background-color: #003b4e;" ><%=attributeRec.getAttributename()%></td>
							<%
								//out.println("template cnt " + cnttemplate);
							    String reqVal = "";
							    String validdVal = "";
								for (int ii = 0; ii < cnttemplate; ii++) 
								{
									//System.out.println("template ii " + ii);
									CanonE008TemplateDAO.CanonE008TemplateListRec templateRec = (CanonE008TemplateDAO.CanonE008TemplateListRec) templateList.get(ii);
									//System.out.println("Start getTemplateAttrAssign: template_id" + templateRec.getTemplateid()  );
									BigDecimal template_id = templateRec.getTemplateid() ;
									result = CanonE008TemplateDAO.getTemplateAttrAssign(template_id,attributeRec.getAttributeid());
									TemplAttAssign = (List) CanonE008ItemProcessUtil.first(result);
									//System.out.println("TemplAttAssign:" + TemplAttAssign.size());
									
									 CanonE008TemplateDAO.CanonE008TemplateAttrAssignListRec attributeAssignRec = (CanonE008TemplateDAO.CanonE008TemplateAttrAssignListRec) TemplAttAssign.get(0); 
									 reqVal ="";
									 validdVal = "";
									 reqVal = attributeAssignRec.getRequired();
									 validdVal = attributeAssignRec.getValid();
									 //out.println("template_id " + template_id);
									 //out.println("attribute_id " + attributeRec.getAttributeid());
									 //out.println("req_val " + reqVal);
									 
									 if (reqVal.equals("YES")) { 
										
							%>
							<td align="center"><input type="checkbox" name="<%=template_id%>req_value<%=i%>" id="<%=template_id%>req_value<%=i%>" checked="checked"></td>
							<%
									 }
			 						 else { 
							%>			
							<td align="center"><input type="checkbox" name="<%=template_id%>req_value<%=i%>" id="<%=template_id%>req_value<%=i%>"></td>
							<%
									 }
							%>				
							<%		if (validdVal.equals("YES")) { 
										
							%>	
							<td align="center"><input type="checkbox" name="<%=template_id%>valid_value<%=i%>"  id="<%=template_id%>valid_value<%=i%>" checked></td>
							<%
							}
							else {
							%>	
							<td align="center"><input type="checkbox"  name="<%=template_id%>valid_value<%=i%>" id="<%=template_id%>valid_value<%=i%>"></td>
							<%
							}
							
 							if(attributeRec.getLastUpdateName().equals("Y"))
							{	
								String strvalidfeildname = template_id+"default_value"+i;
								//addLOV(strvalidfeildname, CanonE008TemplateDAO.getitemattvalueList(attributeRec.getCreatedByName()));
								//if (attributeRec.getCreatedByName().equals("MERCHANDISE_TYPE"))
									//addattributeLOV(strvalidfeildname, CanonE008TemplateDAO.getitemattdefvalueList(attributeRec.getCreatedByName(),attributeAssignRec.getDefaultvalue()));
								//else
									//addattributeLOV(strvalidfeildname, CanonE008TemplateDAO.getitemattvalueList(attributeRec.getCreatedByName()));
 							
								if ((!attributeRec.getCreatedByName().equals("SUPPLIER")) && 
											(!attributeRec.getCreatedByName().equals("PRODUCT_LEVEL5")) &&
											(!attributeRec.getCreatedByName().equals("SERVICE_MODEL")) &&
											(!attributeRec.getCreatedByName().equals("MARKETING_MODEL")) &&
											(!attributeRec.getCreatedByName().equals("MATERIAL_GROUP1")) && 
											(!attributeRec.getCreatedByName().equals("DEFAULT_SRC_WH")) &&
											(!attributeRec.getCreatedByName().equals("TARIFF_CODE"))) 
								{
									addattributeLOV(strvalidfeildname, CanonE008TemplateDAO.getitemattdefvalueList(attributeRec.getCreatedByName(),attributeAssignRec.getDefaultvalue()));	
 							%>
 										
							<%-- <td align="center"><input class="inTxt" type="text" name="<%=template_id%>default_value<%=i%>" value="<%=CanonE008ItemProcessUtil.nonNullify(attributeAssignRec.getDefaultvalue() )%>"></td> --%>
 							<td align="center"><%=CanonE008ItemProcessHelper.gentemplateSelectHtmlattribute(attlovs,strvalidfeildname, attributeAssignRec.getDefaultvalue(),"",attributeRec.getCreatedByName(),""+template_id)%></td>
							 							
 							<% if (attributeRec.getCreatedByName().equals("PRODUCT_LEVEL3"))
			            	{	
			            	%>
		            			<input type="hidden" id="<%=template_id%>product_level3" name="<%=template_id%>product_level3" value="<%=template_id%>default_value<%=i%>">
			            	<% } %>
 							
 							<% } else {  
 								String defaulttext = returndefaultText(CanonE008TemplateDAO.getitemattdefvalueList(attributeRec.getCreatedByName(),attributeAssignRec.getDefaultvalue()));
 								%>
 							<td>
				                	<div id="<%=template_id%>searchBox<%=i%>">
				                		<input style="display:none; " class="inTxt" type="text" id="<%=template_id%>default_value<%=i%>"  name="<%=template_id%>default_value<%=i%>" VALUE="<%=CanonE008ItemProcessUtil.nonNullify(attributeAssignRec.getDefaultvalue())%>">
				                	    <input style="width: 90px;" class="inTxt" type="text"  onchange="checkemptyChange(<%=template_id%>,<%=i%>)" id="<%=template_id%>default_text<%=i%>"  name="<%=template_id%>default_text<%=i%>" VALUE="<%=defaulttext%>">
			                			<button type='button' id="<%=template_id%>find<%=i%>" onclick="callChange(<%=template_id%>,<%=i%>,'<%=attributeRec.getCreatedByName()%>','<%=attributeAssignRec.getDefaultvalue()%>')"><img id="myImg" src="<%=ctxPath%>/e008/images/canonSearch.png"></button>
				                	</div>		
				                	<div id="<%=template_id%>searchList<%=i%>" style="display:none;" ><%=CanonE008ItemProcessHelper.genSelecttemplatedataListHtml(lovs, strvalidfeildname, "",i+strvalidfeildname, ""+i, ""+template_id )%></div>
				            </td>
								<% if (attributeRec.getCreatedByName().equals("SUPPLIER"))
					            	{	
					            	%>
					            		<input type="hidden" id="<%=template_id%>item_supplier" name="<%=template_id%>item_supplier" value="<%=template_id%>default_value<%=i%>">
					            		
					            	<% } %>

							<%
 								}
							}
							else
							{
								if((attributeRec.getCreatedByName().equals("STANDARD_COST")) || (attributeRec.getCreatedByName().equals("ASSET_RECOVERY_COST"))) 
								{
							%>
 							<td align="center"><input onkeypress="return validateFloatKeyPress(this,event);" type="text" id="<%=template_id%>default_value<%=i%>" name="<%=template_id%>default_value<%=i%>" value="<%=CanonE008ItemProcessUtil.nonNullify(attributeAssignRec.getDefaultvalue())%>"></td>
 							<% } else { %>
							<td align="center"><input class="inTxt" type="text" id="<%=template_id%>default_value<%=i%>" name="<%=template_id%>default_value<%=i%>" value="<%=CanonE008ItemProcessUtil.nonNullify(attributeAssignRec.getDefaultvalue())%>"></td>							
							<% } } %> 

							<%
							}
							%>
							<td style="display:none;"><input id="wb_display_h<%=i%>" name="wb_display_h<%=i%>" value="<%=attributeRec.getWorkbenchdisplay()%>"></td>
							<td style="display:none;"><input id="display_sort_h<%=i%>" name="display_sort_h<%=i%>" value="<%=attributeRec.getDisplaysort()%>"></td> 
							<td style="display:none;"><input id="cat_name_h<%=i%>" name="cat_name_h<%=i%>" value="<%=attributeRec.getCategoryname()%>"></td>
							<td style="display:none;"><input id="approval_group_h<%=i%>" name="approval_group_h<%=i%>" value="<%=attributeRec.getApprovalgroupowner()%>"></td> 
						</tr>
						<%
							}
						%>
					</tbody>
			
				</table>
				
				</div>				
				<% } %>
			</div>
		</div>
 			<script>

 			
 				$('[name="display_sort"]').on('blur',function(){
 				  var value = $(this).val();
 				  
 				  var id = $(this);  //attr('id');
 				  $('[name="display_sort"]').not(this).each(function(){
 				     if($(this).val() == value) {
 				    	$(id).focus();
 				    	 alert('Duplicate Display Sort - '+ value);
 				     }
 				     
 				  	   
 				  }) 
 				});
 			
 			
 				function checkemptyChange(Id,row) {  
 					
 					//alert(" checkemptyChange Id " + Id);
 					var mainval=$('#'+Id+'default_text'+row).val();
 					//alert("mainval " + mainval);
 					if(mainval=="") 
 						$('#'+Id+'default_value'+row).val("");
 				
				}

 				function validateFloatKeyPress(el, evt) {
 				    var charCode = (evt.which) ? evt.which : event.keyCode;
 				    var number = el.value.split('.');
 				    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
 				        return false;
 				    }
 				    //just one dot
 				    if(number.length>1 && charCode == 46){
 				         return false;
 				    }
 				    //get the carat position
 				    //var caratPos = getSelectionStart(el);
 				    var dotPos = el.value.indexOf(".");
 				    //if( caratPos > dotPos && dotPos>-1 && (number[1].length > 1)){
 				    if( dotPos>-1 && (number[1].length > 1)){	
 				        return false;
 				    }
 				    return true;
 				}

 				function getSelectionStart(o) {
 					if (o.createTextRange) {
 						var r = document.selection.createRange().duplicate()
 						r.moveEnd('character', o.value.length)
 						if (r.text == '') return o.value.length
 						return o.value.lastIndexOf(r.text)
 					} else return o.selectionStart
 				}
 				
 				function callChange(Id,row,attributename,defaultval) {    
									//$('#'+Id+'searchList'+row).hide();
									//$('#'+Id+'find'+row).click(function () {
										   // alert("Id " + Id);
										    //alert("row " + row);
					  						 var mainval=$('#'+Id+'default_text'+row).val();
					  						 var length = $('#'+Id+'default_text'+row).val().length;
					  						 //alert("length "+length);
					  						 if (length>=3)
					  						 {	 
					  							 //alert(" roWid" + row+Id+'default_value'+row);
					  							 changefilterFunc(row+''+Id+'default_value'+row,mainval,attributename,defaultval);
					  							 //callCompassign("ComponentId0",mainval);
						  						$('#'+Id+'searchBox'+row).hide();
				  						        $('#'+Id+'searchList'+row).show();
					  						 }
				  						 	else
				  							 {
				  								alert("Enter atleast 3 letters ");
				  							 }
					  						 
								    //});
									
									/* $('#searchList31').change(function() {
										        //if ($(this).val() === 'Create your own question') {
										            $('#'+Id+'searchList'+row).hide();
										            $('#'+Id+'searchBox'+row).show();
										        //}
										    }); */
 				}	
 			</script>
		
		<div class="model-table"  id="create-template-dialog" title="create_template" style = "display:none">
		<table>
			<tr>
				<input type ="hidden"  id="hcopytemplate" name="hcopytemplate" value="">
				<td NOWRAP><b>Template Name </b></td>
				<td><input class ="<%=initial_required("newTemplate")%>" type="text" onblur="this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g,'');"	id="newTemplate" name="newTemplate"  value=""></td>
				<%
		  			String strFunccopy = "keyup2(this,"+"'hcopytemplate"+"')";
				%>
				<td NOWRAP><b>Copy From Template </b></td>
				<td id="copytemplate"><%=CanonE008ItemProcessHelper.genSelectHtmlTemplate(lovs,"copytemplate", "",strFunccopy,"", "")%></td>
			</tr>	
			<tr>
					<input  type ="hidden" id="hnewtemplateType" name="hnewtemplateType" value="">
					<td NOWRAP><b>Template Type </b></td>
				<%
		  			String strFunc = "keyup2(this,"+"'hnewtemplateType"+"')";
				%>
				<td id="newtemplateType"><%=CanonE008ItemProcessHelper.genSelectHtmlTemplate(lovs,"newtemplateType", "",strFunc,"", "")%></td>
			</tr>
			<tr>
				<input  type ="hidden" id="hnewtemplateStatus" name="hnewtemplateStatus" value="">
				<td NOWRAP><b>Template Status </b></td>
				<%
				  String strFuncsta = "keyup2(this,"+"'hnewtemplateStatus"+"')";
				%>
				<td id="newtemplateStatus"><%=CanonE008ItemProcessHelper.genSelectHtmlTemplate(lovs,"newtemplateStatus", "",strFuncsta,"", "")%></td>
			</tr>
			<tr>
				<td NOWRAP><b>Template Description </b></td>
				<td><textarea class="inTxt" name="newtemplateDesc" id="newtemplateDesc"></textarea></td>
			</tr>

	</table>						
	</div>
		
		
	</form>
	</div>
	<br>
	</div>
	<div id="savesearch-dialog" title="Save Search" class="model-table" style = "display:none">
		<p>Please give name for the search criteria.</P>
		<div>
			<textarea name="save_search" id="save_search"style="width: 200px; height: 30px"></textarea>
		</div>
	</div>
	

		
  