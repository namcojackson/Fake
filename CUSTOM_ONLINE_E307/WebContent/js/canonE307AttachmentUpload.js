
function fnUploadPOAttach(){
	var url = "canonE307FileUploadToTemp.jsp?source=summary&userName="+encodeURIComponent(userName)+"&fsrNum="+fsrNum+"&serial="+serNum;
	window.open(url,'newWin','directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=450,height=250');
}
function uploadItemImage(fileName){
	$('#fileNm').val(fileName);
    var userName = $('#userName').val();
	var fsrNum = $('#fsr').val();
	var serNum = $('#serialNumber').val();
	if(fileName!=''){
		var url = "canonE307AttachmentUpload.jsp";
		var qryStr="source=summary&userName="+encodeURIComponent(userName)+"&fsrNum="+fsrNum+"&serial="+serNum+"&fileName="+fileName;
		
		$.ajax({
			url: url,
			data: qryStr,
			type:"POST",
			cache: false,
			success : function(result)
			{
				if($.trim(result)=='0'){
					$("#eMsg").css({"color": "red", "font-size": "12"});
					$('#eMsg').html('PO is not Uploaded successfully');
					$('#errorWidget').show();
				}else{
					$('#eMsg').html("PO is uploaded Successsfully..");
					$("#eMsg").css({"color": "green", "font-size": "12"}); 
					$('#errorWidget').show();
				}
			}
		});	
	} 
}
