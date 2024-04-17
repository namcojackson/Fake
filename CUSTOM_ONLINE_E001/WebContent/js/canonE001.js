
if (!window.console) window.console = {};
if (!window.console.log) window.console.log = function () { };

var defaultSingleErrorHandler=makeSingleErrorHandler();

String.prototype.toProperCase = function () {
    return this.replace(/_/g, ' ').replace(/\w\S*/g, function(txt){
    	return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
    });
};

function showPleaseWait() {
    $.blockUI({ css: { border: 'none',
       padding: '15px',
       backgroundColor: '#000',
       '-webkit-border-radius': '10px',
       '-moz-border-radius': '10px',
       opacity: .5,
       color: '#fff',
       cursor: 'default'
    }, overlayCSS: { cursor: 'default' }, baseZ: 9000
    });
}
	   
function hidePleaseWait() {
  $.unblockUI();
}

function setupDatePicker(){
    $(".datePicker").datepicker({
		 dateFormat: 'M dd yy',
		 changeMonth: true,
		 changeYear: true
	 }).attr('readonly', true)
	   .keyup(function(event){
	        if(event.keyCode == 8 || event.keyCode==46){
	            this.value="";
	            $.datepicker._clearDate(this);
	        }
	    });
}

function askConfirmation(title,question,yes_func,no_func){
   var html='<div title="'+title+'">'
       +'<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>'
       +question+'</p>'
       +'</div>';

   $( html ).dialog({
     modal: true,
     width: "320",
     height: "auto",
     buttons: {
       Yes: function() {
           if (yes_func) {
               yes_func($(this));
           }
       },
       No: function() {
           $( this ).dialog( "close" );
           if (no_func) {
        	   no_func($(this));
           }
       }
     }
   });
}   

	function showError(message) {
		$("#eMsg").html(message);
		$("#errorWidget").show();
	}
	
    function popupError( t, title ) {
        var html=
            '<div id="popup_error_dialog" title="'+ (title? title: "Validation Error") +'">'
                +'<p class="popup_error">'
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
	
	function clear_status() {
		var project_status = $("#eMsg");
		project_status.text("").removeClass("ui-state-highlight");
		$(".error").removeClass("error");
	}

	
    function update_status( t) {
        var project_status = $("#eMsg" );
        project_status.html( t );
        //setTimeout(function() {
        //  project_status.removeClass( "ui-state-highlight", 1500 );
        //}, 500 );
		$("#errorWidget").show();
    }

	var lastErrorComponent;
	function makeSingleErrorHandler() {
		var error;
		var bValid = true;
		return {
			handle : function(t, o) {
				error = {
					"component" : o,
					"message" : t
				};
				bValid = false;
			},
			showError : function() {
				if (error.component) error.component.addClass("error");
				showError(error.message);
				if (lastErrorComponent && error.component && lastErrorComponent[0]==error.component[0]){
					popupError(error.message);
				}
				lastErrorComponent=error.component;
				if(error.component)error.component.focus();
			},
			done : function() {
			},
			valid : function() {
				return bValid;
			}
		}
	}
    
	function empty(str) {
		return !str || !/[^\s]+/.test(str);
	}

	function checkRegexp(o, regexp, n, handler) {
		if (!(regexp.test(o.val()))) {
			handler.handle(n, o);
			return false;
		} else {
			return true;
		}
	}

	function checkRequired(o, n, handler) {
		if (empty(o.val())) {
			handler.handle(n + " is required", o);
			return false;
		} else {
			return true;
		}
	}

	function checkLength(o, n, min, max, handler) {
		if (o.val().length > max || o.val().length < min) {
			show_alert("Length of " + n + " must be between " + min
					+ " and " + max + ".", handler, o);
			return false;
		} else {
			return true;
		}
	}

	function checkMaxLength(o, n, max, handler) {
		if (o.val().length > max) {
			handler.handle(n + " - Maximum length (" + max
					+ ") exceeded.", o);
			return false;
		} else {
			return true;
		}
	}
	
    function checkDateTimeNoLater( o, n, o2, o2_name, handler) {
        var d2=o2.datepicker("getDate");
        var d1=o.datepicker("getDate");
        if (d1.getTime()>d2.getTime()){
        	handler.handle( n + " must be less than or equal to "+o2_name, o);
          return false;
        } else {
          return true;
        }
    }

    function checkIfTrue( n, valid) {
        if (!valid) {
        	show_alert(n);
          return false;
        } else {
          return true;
        }
    }
    
	function valueEquals(elm1,elm2){
		return elm1.val()===elm2.val();		
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

	function checkIsNumeric(o, n, handler) {
		if (!$.isNumeric(o.val())) {
			handler.handle(n + " must be a numeric value.", o);
			return false;
		} else {
			return true;
		}
	}
	
	
	function getFormData() {
		var myform = $('#machSearchFrm');
		var disabled = myform.find(':input:disabled').removeAttr(
				'disabled');
		var serialized = myform.serialize();
		disabled.attr('disabled', 'disabled');
		return serialized;
	}
	
	function FormChanges(form) {

		// get form
		if (typeof form == "string") form = document.getElementById(form);
		if (!form || !form.nodeName || form.nodeName.toLowerCase() != "form") return null;
		
		// find changed elements
		var changed = [], n, c, def, o, ol, opt;
		for (var e = 0, el = form.elements.length; e < el; e++) {
			n = form.elements[e];
			c = false;
			
			switch (n.nodeName.toLowerCase()) {
			
				// select boxes
				case "select":
					def = 0;
					for (o = 0, ol = n.options.length; o < ol; o++) {
						opt = n.options[o];
						c = c || (opt.selected != opt.defaultSelected);
						if (opt.defaultSelected) def = o;
					}
					if (c && !n.multiple) c = (def != n.selectedIndex);
					break;
				
				// input / textarea
				case "textarea":
				case "input":
					
					switch (n.type.toLowerCase()) {
						case "checkbox":
						case "radio":
							// checkbox / radio
							c = (n.checked != n.defaultChecked);
							break;
						default:
							// standard values
							c = (n.value != n.defaultValue);
							break;				
					}
					break;
			}
			
			if (c) changed.push(n);
		}
		
		return changed;

	}	
	
	(function ( $ ) {
		
		$.ajaxPost = function (url, errMsg, successMsg){
			showPleaseWait();
			return $.ajax({
				type : "POST",
				url : url,
				data : getFormData(),
				success : function(data) {
					hidePleaseWait();
					if(error_message(data)){
					    show_error(errMsg, data);
					}else{
						$("#main_content").html("");
						$("#main_content").html(data);
						if (successMsg){
							update_status(successMsg);
						}
					}
				},
				error : function(request) {
					hidePleaseWait();
					var response = request.responseText;
					if(error_message(response)){
					    show_error(errMsg, response);
					}else{
						show_error(errMsg);
					}
				}
			});
		}
		
	 
	    $.fn.lov = function(action, options ) {
	        var settings = $.extend({
	            // These are the defaults.
	            width: 700
	        }, options );
	        
	        return this.each(function(idx,elem) {
	        	//if(console) console.log(elem);
	        	var e=$(elem);
	        	if(!e.hasClass("hasLov")){
	        		var lov=$('<a href="#" class="lov"><img src="'+$.s21extnCommonRoot+'/images/download.png" class="lov_icon"></a>');
	        		e.addClass("hasLov");
	        		e.after(lov);
	        		elem.lov=lov;
	        	}
	        	
	        	if(action==="hide"){
	        		elem.lov.hide();
	        	}else if(action==="show"){
	        		elem.lov.show();
	        	}
	        	
	        });
	        
	    }
	    
		if(!$.canonPopupLineMessages){
			$.canonPopupLineMessages=function (){
				var msg='<div style="max-height: 300px;margin-left:10px"><ul><li>'+
					$.canon_line_messages.join("</li><li>")+
					"</li></ul></div>";
				popupError(msg);
			}
		}
	 
	}( jQuery ));
	
