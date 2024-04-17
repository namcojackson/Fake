 
$.fn.canonFileImport = function(options) {
	var response, returnReponse, element, status = true, iframe;

	options = $.extend({}, $.fn.canonFileImport.defaults, options);

	// Add the iframe.
	if (!$('#' + options.iframeID).length) {
		$('body')
				.append(
						'<iframe id="' + options.iframeID + '" name="' + options.iframeID + '" style="display:none" />')
				.append(	
				'<form target="'+options.iframeID +'" action="'+ options.action +'" id="'+options.formID+'" enctype="multipart/form-data" method="POST" style="position:fixed;top:-1000px">'+
					'<input id="'+options.fileInputID+'" type="file" name="'+options.fileInputID+'">'+
				'</form>');
		
	}

	return $(this).each(function() {
		frm = $("#"+options.formID);

		// Submit listener.
		frm.submit(function() {
			// If status is false then abort.
			status = options.post.apply(this);

			if (status === false) {
				return status;
			}

			var iframe=$('#' + options.iframeID).off("load");
			iframe.on("load",function() {
				$("#"+options.fileInputID).val("");
				try {
					response = iframe.contents().find('body');
					returnReponse = response.html();
					
	            	var re = /__CANON__([\s\S]*?)__CANON__/g; 
	            	var m;
	            	var importResult;
	            	if ((m = re.exec(returnReponse)) !== null) {
	            	    importResult=$.parseJSON(m[1]);
	            	}else{
	            		importResult={"error_flag":true, "error":'Invalid response.'};
	            	}
					options.complete.apply(this, [ importResult ]);
					setTimeout(function() {
						response.html('');
					}, 1);

				} catch (e) {
					options.error.apply(this, [ e ]);
				}
			});
		});
		
		$(this).on("click",function(e) {
			$("#"+options.fileInputID).trigger('click');
		});		

		$("#"+options.fileInputID).on("change",function(e) {
			if($(this).val()!==""){
				frm.submit();
			}
		});	
		
	});
};

$.fn.canonFileImport.defaults = {
	iframeID : 'iframe-post-form', // Iframe ID.
	post : function() {
	}, // Form onsubmit.
	complete : function(response) {
	}, // After response from the server has been received.
	error : function(err) {
	}, // server error occurs.
	formID : 'file-import-form', // upload Form ID.
	fileInputID : 'file-import-input' // file input ID.
};
