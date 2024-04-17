<%@page import="canon.apps.common.CanonFileImportUtil"%>
<%@page trimDirectiveWhitespaces="true" %>
<%@page import="java.math.BigDecimal"%>
<%@page import=" com.canon.apps.e001.CanonE001CodeTableUtil"%>
<%@page import="com.canon.apps.e001.CanonE001CommonUtil"%>
<%@page import="java.util.List"%>
<%
	String action=request.getParameter("action");
	BigDecimal cdID = CanonE001CommonUtil.toBigDecimal(
			request.getParameter("cd_id"), false);
	if("download".equals(action)){
		try {
			CanonE001CodeTableUtil.CdOutputStream csv = CanonE001CodeTableUtil
					.exportCSV(cdID);
			response.setContentType(csv.getContentType());
			response.setHeader("Content-disposition",
					"attachment; filename=" + csv.getFileName());
			csv.save(response.getOutputStream());
	
		} catch (Exception e) {
			out.println(e.getMessage());
			e.printStackTrace();
		}
	}else if("upload".equals(action)){
		CanonE001CodeTableUtil.importCSV(cdID, response.getWriter(), CanonFileImportUtil.getFileUploadStream(request));
	}else if("import_new".equals(action)){
		CanonE001CodeTableUtil.importNew(response.getWriter(), CanonFileImportUtil.getFileUploadStream(request));
	}	
%>