<%@page import="oracle.apps.custombilling.dao.CanonCustomBillingSearchingDAO"%>
<%@page import="oracle.apps.custombilling.util.CanonCustomBillingCommon"%>
<%@page import="oracle.apps.fnd.common.WebRequestUtil"%>
<%@page language="java" import="oracle.apps.jtf.calendar.util.CalendarUtil" %>
<%@page import="oracle.apps.jtf.base.Logger" %>
<%@page import="oracle.apps.jtf.util.GeneralPreference" %>
<%@page import="oracle.apps.jtf.base.resources.AOLMessageManager" %>
<%@page language="java" import="java.math.*" %>
<%@page import="static canon.apps.common.CanonS21SessionValidate.commonRoot"%>

<jsp:include page="canonCustomBillingUIInclude.jsp"/>

<HTML>

    <HEAD>

        <TITLE>Custom Billing Template Setup</TITLE>
        <style type="text/css">
            #frmTemplateSetup textarea {
                overflow: auto;
            }
            .table_row_highligh {
                color: black; 
                font-weight: normal; 
                background: #D3D3D3;
            }
            .eventableDataCell { 
                text-align: left; 
                font-size: 11px; 
                color: #000000; 
            }   
            .validateError{
                background-image: url('<%=commonRoot(request)%>/images/spacer.gif');
                color: blue;
                height: 20px;
            }
            .ui-accordion .ui-accordion-content{
                overflow:hidden !important;
            }
            .validity-summary-container{
                padding: 5px;
                display: none; 
                border: 1px solid red;
            }

            .validity-summary-container ul li{
                margin-left: 20px;
                color: blue;
            }            

            fieldset { 
                -moz-border-radius-bottomleft: 7px;
                -moz-border-radius-bottomright: 7px;
                -moz-border-radius-topleft: 5px;
                -moz-border-radius-topright: 7px;
                -webkit-border-radius: 7px;
                border-radius: 3px;
            }            

            .select-container {
                overflow: hidden
            }
            .select-container select {
                border-left-width: 0px;
                border-right-width: 0px;
            }
            
            #page_content 
            {
                padding: 20px 10px 10px 10px;
            }

        </style>

        <script type='text/javascript'>
            
            $.ajaxSetup({ cache: false });
            
            function refreshPage(){
                showPleaseWait();
                var url =  $("#frmTemplateSetup").attr('action');
                var data=$("#frmTemplateSetup").serialize();
                $('input[disabled]').each( function() {
                    data = data + '&' + $(this).attr('name') + '=' + encodeURIComponent($(this).val());
                });
          
                $.post( url, data,
                function( data ) {  
                    hidePleaseWait();           
                    $('#divTemplateSetup').html(data);
                });
            }

            function resetSelectTitles(){
                $('select').attr('title', function(i, title) {
                    return this.value;
                });                
                $('select option').attr('title', function(i, title) {
                    return this.text;
                });                
            }
            function equals(var1,vaar2){
                if(typeof var1 == "undefined" || typeof var2 == "undefined"){
                    return false;
                }
                if(('null' == val1 && 'null' != val2) || ('null' != val1 && 'null' == val2)){
                    return false;
                }
                if(('null' == val1 && 'null' == val2) ){
                    return true;
                }
                return vae1==var2;
            }
            
            function foundDuplicateViewName(value, element){
                var valid=true;
                $("[name='VIEW_NAME']").each(function () {
                    if(element!=this){
                        if(value==this.value) {
                            valid= false;
                            return false;
                        }
                    }
                });
                return valid;
            }

            function toTitleCase(str) {
                return str.toLowerCase().replace(/_/g," ").replace(/(?:^|\s)\w/g, function(match) {
                    return match.toUpperCase();
                });
            }

            function pre_populate_view_alias(viewname_select){
                alias_=$(viewname_select).closest("td").next().children(0);
                newVal=toTitleCase($(viewname_select).val());
                alias_.val(newVal);
                $(viewname_select).data("pre_populate_view_alias",newVal);
            }
            
            function pre_populate_column_alias(columnname_select){
                type_=$(columnname_select).closest("td").prev().children(0);
                if("Standard"==type_.val()){
                    alias_=$(columnname_select).closest("td").next().children(0);
                    newVal=toTitleCase($(columnname_select).val());
                    alias_.val(newVal.toUpperCase());
                    $(columnname_select).data("pre_populate_column_alias",newVal);
                }
            }

            // Adds match validator support to format-check against list of email addresses 
            $.validity.patterns.emailList = /^(([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5}){1,25})+([;.](([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5}){1,25})+)*$/;
            $.validity.messages.emailList = "\#{field} must be formatted as a series of email-addresses separated by semicolons.";
            $.validity.settings.elementSupport=":text, :password, textarea, select, :radio, :checkbox, :hidden";
            $.validity.setup({ outputMode:'summary' });
            
            if (typeof $.fn.prop !== 'function')
                $.fn.prop = function(name, value){
                    if (typeof value === 'undefined') {
                        return this.attr(name);
                    } else {
                        return this.attr(name, value);
                    }
            };
            
            function pageIsValid(){
                $.validity.start();
                
                $("#billerEmail")
                .match("email","Invalid biller email address.");
                
                $("#customerEmail")
                .require("Customer emails is required.")
                .match("emailList","Customer email(s) must be formatted as a series of email-addresses separated by semicolons.");
                
                $("#otherEmail")
                .match("emailList","Other email(s) must be formatted as a series of email-addresses separated by semicolons.");

                var invbreakeRule = $("#invoiceBreak").val()=="" && !($("#multiTab").val()=="" || $("#multiTab").val()=="VIEW");
                $(".viewName")
                    .assert(
                    !invbreakeRule || $(".viewName").size()<2
                    ,"If Multi Tab is not N/A and VIEW, Invoice break is N/A, Only one view is allowed.");

                $(".viewAlias")
                .require("View alias is required.");
                
                $(".columnAlias")
                .require("Column alias is required.");
                
                $(".viewName").distinct(
                    function(val) { 
                        return val.toUpperCase();
                    },
                    'Duplicate view name.'
                );
                    
                var result = $.validity.end();
                return result.valid;
            }
            
            $("#save_button").live('click',function(e){
                if(pageIsValid()){
                    $("#saveFlag").val("Yes");
                    refreshPage();
                }
            });
                
            $("select").live('change',function(){
                this.title=this.value;
                $('option',this).attr('title', function(i, title) {
                    return this.text;
                });                
            });
                
            $(".select-container select")
            .live('mousedown', function(){
                if(this.dontWiden) return;
                var styledWidth = this.offsetWidth;
                if(!$(this).data("origWidth")){
                    $(this).data("origWidth", $(this).css("width"));
                }
                $(this).css("width", "auto");
                var desiredWidth = this.offsetWidth;
                // If this control needs less than it was styled for, then we don't need to bother with widening it.
                if(desiredWidth < styledWidth) {
                    this.dontWiden = true;
                    $(this).css("width", $(this).data("origWidth"));
                }
                    
            })
            .live('blur change', function(){
                $(this).css("width", $(this).data("origWidth"));
                this.dontWiden = false;
            })
            
            $("#add_view").live('click',function(){
                if ($(this).attr("readOnly")=="readonly") return false;
                var html=
                    '<tr class="eventableDataCell">'+
                    '<input name="VIEW_ID" value="-1" type="hidden">'+
                    '<td style="word-break:break-all" nowrap="nowrap">'+
                    '   <div class="select-container">'+
                    '       <select name="VIEW_NAME" style="width:200px" class="viewName">'+
                    '       </select>'+
                    '   </div>'+
                    '</td>'+
                    '<td style="word-break:break-all">'+
                    '    <input name="VIEW_ALIAS" size="20" type="text" class="viewAlias" >'+
                    '</td>'+
                    '<td width="100">'+
                    '   <div class="select-container">'+
                    '    <select class="sortOrder1" name="VIEW_SORT_ORDER_1" style="width:150px">'+
                    '    </select>'+
                    '   </div>'+
                    '</td>'+
                    '<td width="100">'+
                    '   <div class="select-container">'+
                    '    <select class="sortOrder2" name="VIEW_SORT_ORDER_2" style="width:150px">'+
                    '    </select>'+
                    '   </div>'+
                    '</td>'+
                    '<td>'+
                    '   <div class="select-container">'+
                    '    <select class="sortOrder3"  name="VIEW_SORT_ORDER_3" style="width:150px">'+
                    '    </select>'+
                    '   </div>'+
                    '</td>'+
                    '<td class="buttons" > '+
                    '    <div style="width:80px">'+
                    '    <a href="#" class="negative move_up" style="display:inline" >Up</a>'+
                    '    <a href="#" class="negative move_down" style="display:inline" >Down</a>'+
                    '    </div>'+
                    '</td>'+
                    '<td class="buttons" > <a data-view_id="-1" style="width:30;" href="#" class="negative delete_view">X</a></td>'+
                    '</tr>';
                var newTr=$(html);
                $(".select-container",newTr).each(function(){
                    $(this).css("width",$(this).children(0).css("width"));
                });

                $.getJSON("canonCustomBillingTemplateSetupService.jsp",{action:'getViewNames'}, function(j){
                    var options = '';
                    for (var i = 0; i < j.length; i++) {
                        options += '<option value="' + j[i].optionValue + '">' + j[i].optionDisplay + '</option>';
                    }
                    $(".viewName",newTr).html(options);
                    $(".viewName",newTr).trigger("change");
                })

                $('#viewSetupTable tr:last').after(newTr);
                return false;
            });


            $("#add_column").live('click',function(){
                if ($(this).attr("readOnly")=="readonly") return false;
                var html=
                    '<tr class="eventableDataCell">'+
                    '<input name="COLUMN_ID" value="-1" type="hidden">'+
                    '<td style="word-break:break-all" nowrap="nowrap">'+
                    '    <select name="COLUMN_TYPE" style="width:100px" class="columnType">'+
                    '       <option selected="selected" value="Standard">Standard</option>     '+
                    '       <option value="Computed">Computed</option>     '+
                    '       <option value="Concatenated">Concatenated</option>     '+
                    '    </select>'+
                    '</td>'+
                    '<td style="word-break:break-all" nowrap="nowrap">'+
                    '   <div class="select-container">'+
                    '       <select name="COLUMN_NAME" style="width:400px" class="columnName">'+
                    '       </select>'+
                    '   </div>'+
                    '</td>'+
                    '<td style="word-break:break-all">'+
                    '    <input name="COLUMN_ALIAS" size="30" type="text" class="columnAlias" >'+
                    '</td>'+
                    '<td> <div class="columnSubtotal"></div>'+
                    '</td>'+
                    '<td class="buttons" > '+
                    '    <div style="width:80px">'+
                    '    <a href="#" class="negative move_up" style="display:inline" >Up</a>'+
                    '    <a href="#" class="negative move_down" style="display:inline" >Down</a>'+
                    '    </div>'+
                    '</td>'+
                    '<td class="buttons" > <a data-column_id="-1" style="width:30;" href="#" class="negative delete_column">X</a></td>'+
                    '</tr>';
                var newTr=$(html);
                $(".select-container",newTr).each(function(){
                    $(this).css("width",$(this).children(0).css("width"));
                });
                    
                $(".columnType",newTr).trigger("change");
                $('#ColumnSetupTable tr:last').after(newTr);
                return false;
            });

            $(".delete_view").live('click',function(event){
                if ($(this).attr("readOnly")=="readonly") return false;
                var line_id=$(this).data("view_id");
                if("-1"==line_id){
                    var row=$(this).closest("tr");
                    row.remove();
                    event.preventDefault();
                }else{
                    $("#saveFlag").val("delete_view");
                    $("#viewId").val(line_id);
                    refreshPage();
                    selectedRow=-1;
                }
                event.preventDefault();
            });

            $(".delete_column").live('click',function(event){
                if ($(this).attr("readOnly")=="readonly") return false;
                var column_id=$(this).data("column_id");
                if("-1"==column_id){
                    var row=$(this).closest("tr");
                    row.remove();
                    event.preventDefault();
                }else{
                    $("#saveFlag").val("delete_column");
                    $("#columnId").val(column_id);
                    refreshPage();
                }
                event.preventDefault();
            });
            
            $("#complete").live('click',function(event){
                if(pageIsValid()){
                    $("#saveFlag").val("complete");
                    refreshPage();
                }
                event.preventDefault();
            });
            
            $("#incomplete").live('click',function(event){
                $("#saveFlag").val("incomplete");
                refreshPage();
                event.preventDefault();
            });

            $(".move_up,.move_down").live('click',function(){
                if ($(this).attr("readOnly")=="readonly") return false;
                var row = $(this).parents("tr:first");
                if ($(this).is(".move_up")) {
                    row.insertBefore(row.prev());
                } else {
                    row.insertAfter(row.next());
                }
                $("#save_button").click();
                return false;
            });

            $("#delete").live('click',function(event){
                if(confirm('Do you want to delete the template?')){
                    showPleaseWait();
                    $("#saveFlag").val("delete");
                    $('#frmTemplateSetup').submit();
                }
            });

            var selectedRow=-1;
            var methods = {
                init : function(options) {
                    var settings = {
                        'class_name'         : 'table_row_highligh'
                    };                 
                    return this.each(function(){
                        if ( options ) {
                            $.extend( settings, options );
                        }
                        var table=$(this);
                        $(".column_seletor", this).click(function(e){
                            $this=$(this).closest("tr");
                            rowIndex=$this[0].rowIndex;
                            if(rowIndex>0){
                                if(selectedRow != -1){
                                    if(selectedRow==rowIndex) return;
                                    var deselectRow=$("tr",table).get(selectedRow);
                                    if(settings.deselect){
                                        if(!settings.deselect(deselectRow)){
                                            return;
                                        }
                                    }
                                    $(deselectRow).removeClass(settings.class_name);
                                }
                                selectedRow=rowIndex;
                                var row_= $("tr",table).get(rowIndex);
                                $(row_).addClass(settings.class_name);
                                if(settings.select){
                                    settings.select(row_);
                                }
                            }
                        })
                    });

                }
            };

            $.fn.highlight_table= function(method) {
                if ( methods[method] ) {
                    return methods[method].apply( this, Array.prototype.slice.call( arguments, 1 ));
                } else if ( typeof method === 'object' || ! method ) {
                    return methods.init.apply( this, arguments );
                } else {
                    $.error( 'Method ' +  method + ' does not exist.' );
                }   

            }


            $(".viewName").live("change",function(){
                pre_populate_view_alias(this);
                var viewName=$(this).val();
                var so1=$(this).closest("tr").find('.sortOrder1');
                var oldSo1Val=so1.val();
                $.getJSON("canonCustomBillingTemplateSetupService.jsp",{action:'getSortOrder1', viewName: viewName}, function(j){
                    var options = '<option></option>';
                    for (var i = 0; i < j.length; i++) {
                        options += '<option value="' + j[i].optionValue + '">' + j[i].optionDisplay + '</option>';
                    }
                    so1.html(options);
                    so1.val(oldSo1Val);
                    if(!equals(so1.val(),oldSo1Val)){
                        so1.trigger('change');
                    }
                })
            })


            $(".sortOrder1").live("change",function(){
                var so1=$(this);
                var so2=$(this).closest("tr").find('.sortOrder2');
                var viewName=$(this).closest("tr").find("[name='VIEW_NAME']")[0].value;
                var oldSo2Val=so2.val();
                $.getJSON("canonCustomBillingTemplateSetupService.jsp",{action:'getSortOrder2', viewName: viewName, sortOrder1: so1.val() }, function(j){
                    var options = '<option></option>';
                    for (var i = 0; i < j.length; i++) {
                        options += '<option value="' + j[i].optionValue + '">' + j[i].optionDisplay + '</option>';
                    }
                    so2.html(options);
                    so2.val(oldSo2Val);
                    if(!equals(so2.val(),oldSo2Val)){
                        so2.trigger('change');
                    }
                })
        
            })


            $(".sortOrder2").live("change",function(){
                var so2=$(this);
                var so1=$(this).closest("tr").find('.sortOrder1');
                var so3=$(this).closest("tr").find('.sortOrder3');
                var oldSo3Val=so3.val();
                var viewName=$(this).closest("tr").find("[name='VIEW_NAME']")[0].value;
                $.getJSON("canonCustomBillingTemplateSetupService.jsp",{action:'getSortOrder3', viewName: viewName, sortOrder1: so1.val(), sortOrder2: so2.val() }, function(j){
                    var options = '<option></option>';
                    for (var i = 0; i < j.length; i++) {
                        options += '<option value="' + j[i].optionValue + '">' + j[i].optionDisplay + '</option>';
                    }
                    so3.html(options);
                    so3.val(oldSo3Val);
                    so3.trigger("change");
                })
            })

            $(".columnType").live("change",function(){
                var columnType=$(this).val();
                var viewName=$("#viewName").val();
                var columnName=$(this).closest("tr").find('.columnName');
                var oldColumnName=columnName.val();
                $.getJSON("canonCustomBillingTemplateSetupService.jsp",{action:'getColumnList',viewName: viewName, columnType: columnType}, function(j){
                    var options = '';
                    for (var i = 0; i < j.length; i++) {
                        options += '<option value="' + j[i].optionValue + '">' + j[i].optionDisplay + '</option>';
                    }
                    columnName.html(options);
                    columnName.val(oldColumnName);
                    columnName.trigger("change");
                })
            })

            $(".columnName").live("change",function(){
                pre_populate_column_alias(this);
                var columnName=$(this).val();
                var viewName=$("#viewName").val();
                var columnType=$(this).closest("tr").find('.columnType').val().toUpperCase();
                var columnSubtotal=$(this).closest("tr").find('.columnSubtotal');
                if( "STANDARD"==columnType){
                    columnSubtotal.html('<input type="hidden" name="SUBTOTAL"/>');  
                    $.getJSON("canonCustomBillingTemplateSetupService.jsp",{action:'getEligibleSumCols',viewName: viewName}, function(j){
                    	for (var i = 0; i < j.length; i++) {
                            if( j[i].optionValue == columnName){
                               var html='<select name="SUBTOTAL" title="SUBTOTAL">'+
                                '<option></option>'+
                                '<option value="SUM" title="SUM">SUM</option>'+
                                '</select>';
                                 columnSubtotal.html(html);
                                 break;
                            }
                        }
                        
                    });

                    $.getJSON("canonCustomBillingTemplateSetupService.jsp",{action:'getEligibleNonNumericCols',viewName: viewName}, function(j){
                       
                        for (var i = 0; i < j.length; i++) {
                            if( j[i].optionValue == columnName){
                            	var html='<select name="SUBTOTAL" title="SUBTOTAL">'+
                                 '<option></option>'+
                                 '<option value="SUBTOTAL" title="SUBTOTAL">SUBTOTAL</option>'+
                                 '</select>';
                                 columnSubtotal.html(html);
                                 break;
                            }
                        }
                    });
                   
                        
                }else if( "COMPUTED"==columnType){
                    var html='<select name="SUBTOTAL" title="SUBTOTAL">'+
                        '<option></option>'+
                        '<option value="SUM" title="SUM">SUM</option>'+
                        '</select>' ;
                                    
                    columnSubtotal.html(html);
                }else if( "CONCATENATED"==columnType){
                    var html='<select name="SUBTOTAL" title="SUBTOTAL">'+
                    '<option></option>'+
                    '<option value="SUBTOTAL" title="SUBTOTAL">SUBTOTAL</option>'+
                    '</select>' ;
                                
                  columnSubtotal.html(html);
                }else{
                    columnSubtotal.html('<input type="hidden" name="SUBTOTAL"/>');
                }
            })
                
        </script>


    </HEAD>
    <%
        CanonCustomBillingCommon C = new CanonCustomBillingCommon();
        String myRegionName = "";//"JTF_NOTES_CREATE";
        String myPermissionName = "";//"JTF_NOTES_CREATE";
        oracle.apps.fnd.common.WebAppsContext objWebAppsContext =
                WebRequestUtil.validateContext(request, response);

        int userId = objWebAppsContext.getUserId();
        String templateId = request.getParameter("templateId");
        System.out.println("in canonCustomBillingTemplateSetup.jsp templateId is " + templateId);
        String action = C.nonNullify(request.getParameter("action"));
        System.out.println("Action "+action);

        if ("NEW_OR_UPDATE".equals(action)) {
            String status = "1";
            BigDecimal p_template_id =  C.toBigDecimal(templateId, true);
            System.out.println("Inside action: -> p_template_id "+p_template_id);
            if (p_template_id.intValue() == -1) {
                String p_parent_customer = C.nonNullify(request.getParameter("parentCustomerName"));
                String p_group_name = C.nonNullify(request.getParameter("customerGroup"));
                String p_customer_name = C.nonNullify(request.getParameter("customerName"));
                String p_template_level = C.nonNullify(request.getParameter("templateLevel"));
                String p_site_use_id = C.nonNullify(request.getParameter("billToUse"));
                System.out.println("***: in createTemplate " + p_customer_name + "|" + p_group_name + "|" + p_parent_customer + "|" + p_site_use_id + "|" + p_template_level +"|" + userId);
                Object result[] = CanonCustomBillingSearchingDAO.createTemplate(p_customer_name,p_group_name,p_parent_customer,p_site_use_id!=null && !(p_site_use_id.equals("-1")) ? p_site_use_id:null,p_template_level,Integer.toString(userId));
                status = (String) C.first(result);
                BigDecimal x_template_id = (BigDecimal) C.second(result);
                templateId = x_template_id.toString();
            }
        }else if ("COPY".equals(action)) {
            String status = "1";
            BigDecimal p_template_id = C.toBigDecimal(templateId, true);
            if (p_template_id.intValue() != -1) {
                throw new RuntimeException("Invalid Template ID "+p_template_id);
            }
            BigDecimal selectedTemplateId = C.toBigDecimal(request.getParameter("selectedTemplateId"),false);
            String p_parent_customer = C.nonNullify(request.getParameter("parentCustomerName"));
            String p_group_name = C.nonNullify(request.getParameter("groupName"));
            String p_customer_name = C.nonNullify(request.getParameter("customerName"));
            String p_site_use_id = C.nonNullify(request.getParameter("billToUse"));
            String p_template_level = C.nonNullify(request.getParameter("templateLevel"));
            Object result[] = CanonCustomBillingSearchingDAO.copyTemplate(selectedTemplateId, p_customer_name, p_group_name,p_parent_customer, p_site_use_id!=null? p_site_use_id:null, p_template_level,Integer.toString(userId));
            status = (String) C.first(result);
            BigDecimal x_template_id = (BigDecimal) C.second(result);
            templateId = x_template_id.toString();
        }

    %>
    <body >
        <%@ include file="jtfcalincps.jsp" %>
        <%@ include file="canonCustomBillingTop.jsp" %>
        <div id="page_content" class="T2 clearfix" >
            <div id = "divTemplateSetup">
                <jsp:include page="canonCustomBillingTemplateSetupInclude.jsp">
                    <jsp:param name="userId" value="<%=userId%>" />
                    <jsp:param name="templateId" value="<%=templateId%>" />
                </jsp:include>
            </div>
            <div>
            </div>

            <%@ include file="canonCBSAppBottom.jsp"%> 
            <%@ include file="jtfcalincpe.jsp" %>
    </body>
</html>
