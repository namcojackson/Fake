<%@page import="com.canon.apps.servreq.dao.CanonE307ServiceRequestDetailsDao"%>
<%@page import="com.canon.common.CanonCommonUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*,java.io.*"%>
<%@ page import="com.lowagie.text.*,com.lowagie.text.pdf.*,com.lowagie.text.pdf.PdfPTable" %>
<%@page import="parts.dbcommon.EZDDBCICarrier"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService" %>
<%@page import="com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter" %>
<%@page import="com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult" %>
<%@page import="com.canon.cusa.s21.framework.log.S21AbendException" %>
<%@page import="com.canon.cusa.s21.framework.online.blap.S21BusinessHandler" %>
<%@page import="com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort" %>
<%@page import="com.canon.cusa.s21.framework.online.common.sort.S21SortKey" %>
<%@page import="com.canon.cusa.s21.framework.online.common.sort.S21SortTarget" %>
<%@page import="com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService" %>
<%@page import="com.canon.cusa.s21.framework.userprofile.S21UserProfileService" %>
<%@page import="com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory" %>
<%@page import="com.canon.cusa.s21.framework.userprofile.reportprofile.S21ArchivedReportInfo" %>

<%@page import="parts.common.EZDCMsg" %>
<%@page import="parts.common.EZDMsg" %>
<%@page import="parts.common.EZDSMsg" %>
<%@page import="business.blap.ZZPL0030.ZZPL0030CMsg" %>
<%@page import="business.blap.ZZPL0030.constant.ZZPL0030Constant" %>

<%
CanonCommonUtil util = new CanonCommonUtil();
String loginUser = request.getParameter("userName");
long fileId = util.checkNull(request.getParameter("fileId"))!=null?Long.parseLong(request.getParameter("fileId")):0;
String fileName = util.checkNull(request.getParameter("fileName"))==null?String.valueOf(fileId):request.getParameter("fileName");
String csaCompanyCode = "ADB";
SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");

SimpleDateFormat format = new SimpleDateFormat("z");
String timezone = format.format(new Date());
String invokeTimestamp = lsDateFmt.format(new Date());

// Initialize S21DB-Carrier (It should be done. It leads NullPointer exception when didn't initialize.)
// These contents are used as a default data in S21-Standard table. (Update user, time, time-zone, program id, company code)
EZDDBCICarrier.initOnline(loginUser, invokeTimestamp, timezone, csaCompanyCode);
EZDDBCICarrier.setProgID("ZZPL0030");


	byte[] report = null;
	ZZPL0030CMsg cMsg = new ZZPL0030CMsg();
	System.out.println("Inside Attach Report "+fileId);
	S21EIPPrintingService eipService = new S21EIPPrintingService();
	CanonE307ServiceRequestDetailsDao detObj = new CanonE307ServiceRequestDetailsDao();
	String path=""; //"///var/s21/csaextn/updownfiles/download";
    report = eipService.downloadReport(fileId);
    if (report != null) {
    	path = detObj.getDownloadFilePath();

    	System.out.println("Inside Report File Path: "+path);
        cMsg.xxFileData.setTempFilePath(path, fileName, ZZPL0030Constant.EXTENSION_PDF);
        ZYPFileWriter.writeFile(cMsg.xxFileData.getTempFilePath(), report);
        path= cMsg.xxFileData.getTempFilePath();
    } else {
    	System.out.println("Inside Report error");
        cMsg.setMessageInfo("ZZPM0050E", new String[] {String.valueOf(fileId) });
    }

    
   // path = path + File.separator + String.valueOf(fileId)+ZZPL0030Constant.EXTENSION_PDF;
   // path = path + File.separator +fileName+ZZPL0030Constant.EXTENSION_PDF;
    System.out.println("path: "+ path);
	if(path.indexOf(".pdf")>-1){
		 String folderPath = "///"+path;

         File file = new File(folderPath);

         FileInputStream fis = new FileInputStream(file);
         
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         byte[] buf = new byte[256];
          try {
                 for (int readNum; (readNum = fis.read(buf)) != -1;) {
                	 baos.write(buf, 0, readNum); 
                 }

             } catch (IOException ex) {
                 ex.printStackTrace();
             }
         
		 response.setContentType("application/pdf");

		 response.setHeader("Content-disposition","inline; filename=Field Service Report.pdf" );

		 response.setContentLength(baos.size());

		 ServletOutputStream sos= response.getOutputStream();

		 baos.writeTo(sos);

		 sos.flush();
	}
	
	/*try
    {
		File file = new File(path);
		//System.out.println("filePath: "+ path);
		if(file.exists()){
			//System.out.println("Inside file exists");
			file.delete();
		}
    } catch (Exception ex) {
    	System.out.println("Inside Task Report: "+ ex.getMessage());
    }*/

%>
