function showPleaseWait() {
	$.blockUI( {
		css : {
			border : 'none',
			padding : '15px',
			backgroundColor : '#000',
			'-webkit-border-radius' : '10px',
			'-moz-border-radius' : '10px',
			opacity : .5,
			color : '#fff',
			cursor : 'default'
		},
		overlayCSS : {
			cursor : 'default'
		},
		baseZ : 9000,
		message : '<h1> Please Wait...</h1><img src="/s21extn/e008/images/canonE008Wait.gif"/>'
	});
}

function hidePleaseWait() {
	$.unblockUI();
}