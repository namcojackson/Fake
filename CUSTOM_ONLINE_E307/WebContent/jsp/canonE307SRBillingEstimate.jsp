<%@ page import="java.util.Date"%>
<%@ page import="java.text.*" %>
<%@ page import="java.io.*,java.awt.Color,java.io.ByteArrayOutputStream,java.io.IOException" %>
<%@ page import="com.lowagie.text.*,com.lowagie.text.pdf.*,com.lowagie.text.pdf.PdfPTable" %>
<%@ page import="com.lowagie.text.pdf.PdfPCell" %>
<%@ page import="com.lowagie.text.FontFactory" %>
<%@ page import="com.lowagie.text.Paragraph" %>
<%@ page import="com.lowagie.text.Image" %>
<%@ page import="com.lowagie.text.pdf.draw.DottedLineSeparator" %>
<%@ page import="com.lowagie.text.pdf.draw.LineSeparator" %>
<%@ page import="com.lowagie.text.pdf.draw.VerticalPositionMark" %>
<%@ page import="com.canon.apps.servreq.dao.CanonE307ChargesDetailsDAO" %>  
<%@ page import="com.canon.apps.servreq.beans.CanonE307BillEstmtTskDtlsRec" %>  
<%@ page import="com.canon.apps.servreq.beans.CanonE307BillEstmtHdrRec" %>  
<%@ page import="com.canon.apps.servreq.beans.CanonE307BillEstmtItmDtlsRec" %>  
<%@ page import="com.canon.apps.servreq.util.*" %>
<%@ page import="java.util.*" %>>
<body>
<%

response.setContentType( "application/pdf" );
response.setHeader("Content-Disposition", "attachment; filename=\"CanonE307BillingEstimatePDF.pdf\"");
Document document = new Document(PageSize.A4.rotate(), 10, 10, 10, 10);
try {
	  DecimalFormat twoDForm = new DecimalFormat("#.##");
	  String strSrNum=request.getParameter("fsrNum")==null?"":request.getParameter("fsrNum"); 
	  SimpleDateFormat sdf = new SimpleDateFormat("dd'-'MMM'-'yyyy");
	  java.util.Date todays_date = new java.util.Date();
	  String ctxPath = request.getContextPath();
	  System.out.println("ctxPath : "+ ctxPath);
	  String servPath = request.getServletPath();
	  System.out.println("servPath.."+servPath);
	//  String imgSrc=ctxPath+"/common/images/canon_E182_eManageLogo.jpg";
	  String imgSrc = ctxPath+"/common/images/bg_header.jpg";
	//  String imgSrc = "/common/images/csa_logo.jpg";
	//  String imgSrc ="http://s21dev.cusa.canon.com/s21extn/common/images/bg_header.jpg";
	  System.out.println("imgSrc : "+ imgSrc);

	  ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	  PdfWriter writer= PdfWriter.getInstance( document, buffer );
	//  writer.setCompressionLevel(0);
	  document.open();
	//  com.lowagie.text.Image img =
//				com.lowagie.text.Image.getInstance(imgSrc);	
//	  img.setAlignment(Image.ALIGN_LEFT);
	//  img.setAbsolutePosition(0, 0);
//	  img.scaleAbsolute(825, 80);
	//  img.scaleToFit(826, 1100);
	//	document.add(img);
	  int NumUserColumns = 3;
	  PdfPTable usernametable = new PdfPTable(NumUserColumns);
	 

	  Font Hdr = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new Color(0x00, 0x00, 0x00));
	  Font Promptb = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD, new Color(0x99, 0x66, 0x00));
	  Font Prompt = FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.NORMAL, new Color(0x00, 0x00, 0x00));
	  BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); 

	  System.out.println("Inside Billing Estimate 2 ");	  
	  Paragraph pHdrData = new Paragraph("Billing Estimate", Hdr);
	  pHdrData.setSpacingAfter(2f);
	  pHdrData.setAlignment("center");
	  pHdrData.setSpacingAfter(10f);		
	  pHdrData.setSpacingBefore(10f);	
	  document.add(pHdrData);
	  
	  CanonE307ChargesDetailsDAO chrgObj = new CanonE307ChargesDetailsDAO();
	  CanonE307ServiceReqSumryAPIUtil utilObj = new CanonE307ServiceReqSumryAPIUtil();
	  Object objs[]= chrgObj.getBillingEstmtDtls(strSrNum);
	  CanonE307BillEstmtHdrRec objBean = (CanonE307BillEstmtHdrRec)objs[0];
	  Chunk tab1 = new Chunk();
	  PdfPCell cellEmpty = new PdfPCell(new Paragraph(""));	
	  cellEmpty.setBorder(Rectangle.NO_BORDER);
	  
	  float[] widths1 = { 1f, 1f, 1f, 1f, 1f};
	  PdfPTable table = new PdfPTable(widths1);	  
	  PdfPCell cell = new PdfPCell();
	  if(objBean!=null){
		  table.setWidthPercentage(70);
		  cell = new PdfPCell(new Paragraph("Customer Name: ",
	  				 FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);
		  cell = new PdfPCell(new Paragraph(objBean.getStrCustName(),
				  FontFactory.getFont(FontFactory.HELVETICA, 11)));	
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);	
		  table.addCell(cellEmpty);
		  cell = new PdfPCell(new Paragraph("Service Request: ", 
				  FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);
		  cell = new PdfPCell(new Paragraph(objBean.getStrSrRqstNum(),
				  FontFactory.getFont(FontFactory.HELVETICA, 11)));
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);
		  table.setSpacingAfter(3f);		
		  table.setSpacingBefore(5f);	
		  document.add(table);
		  
		  table = new PdfPTable(widths1);
		  table.setWidthPercentage(70);
		  cell = new PdfPCell(new Paragraph("Customer Address: ",
				  FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));	
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);
		  cell = new PdfPCell(new Paragraph(objBean.getStrCustAddress(),
				  FontFactory.getFont(FontFactory.HELVETICA, 11)));	
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);	
		  table.addCell(cellEmpty);
		  cell = new PdfPCell(new Paragraph("Model: ",
				  FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);
		  cell = new PdfPCell(new Paragraph(objBean.getStrMdl(),
				  FontFactory.getFont(FontFactory.HELVETICA, 11)));
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);
		  table.setSpacingAfter(3f);		
		  table.setSpacingBefore(5f);	
		  document.add(table);
		  
		  table = new PdfPTable(widths1);
		  table.setWidthPercentage(70);
		  cell = new PdfPCell(new Paragraph("City: ",
				  FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));	
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);
		  cell = new PdfPCell(new Paragraph(objBean.getStrCustCity(),
				  FontFactory.getFont(FontFactory.HELVETICA, 11)));	
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);	
		  table.addCell(cellEmpty);
		  cell = new PdfPCell(new Paragraph("Serial#: ",
				  FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);
		  cell = new PdfPCell(new Paragraph(objBean.getStrSrlNum(),
				  FontFactory.getFont(FontFactory.HELVETICA, 11)));
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);
		  table.setSpacingAfter(3f);		
		  table.setSpacingBefore(5f);	
		  document.add(table);		  

		  table = new PdfPTable(widths1);
		  table.setWidthPercentage(70);
		  cell = new PdfPCell(new Paragraph("Postal Code: ",
				  FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));	
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);
		  cell = new PdfPCell(new Paragraph(objBean.getStrCustZip(),
				  FontFactory.getFont(FontFactory.HELVETICA, 11)));
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);	
		  table.addCell(cellEmpty);
		  cell = new PdfPCell(new Paragraph("Tag#: ",
				  FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);
		  cell = new PdfPCell(new Paragraph(objBean.getStrTgNum(),
				  FontFactory.getFont(FontFactory.HELVETICA, 11)));
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);
		  table.setSpacingAfter(3f);		
		  table.setSpacingBefore(5f);	
		  document.add(table);		  
		  
		  table = new PdfPTable(widths1);
		  table.setWidthPercentage(70);
		  cell = new PdfPCell(new Paragraph("Account Number: ",
				  FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));	
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);
		  cell = new PdfPCell(new Paragraph(objBean.getStrCustAccNum(),
				  FontFactory.getFont(FontFactory.HELVETICA, 11)));	
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);	
		  table.addCell(cellEmpty);
		  cell = new PdfPCell(new Paragraph("Solution Name: ",
				  FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);
		  cell = new PdfPCell(new Paragraph(objBean.getStrSolNm(),
				  FontFactory.getFont(FontFactory.HELVETICA, 11)));
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);
		  table.setSpacingAfter(3f);		
		  table.setSpacingBefore(5f);	
		  document.add(table);
		  
		  table = new PdfPTable(widths1);
		  table.setWidthPercentage(70);
		  cell = new PdfPCell(new Paragraph("Contact: ",
				  FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));	
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);
		  cell = new PdfPCell(new Paragraph(objBean.getStrCustContact(),
				  FontFactory.getFont(FontFactory.HELVETICA, 11)));	
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);	
		  table.addCell(cellEmpty);
		  cell = new PdfPCell(new Paragraph("Email Address: ",
				  FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);
		  cell = new PdfPCell(new Paragraph(objBean.getStrCustEmail(),
				  FontFactory.getFont(FontFactory.HELVETICA, 11)));
		  cell.setBorder(Rectangle.NO_BORDER);
		  table.addCell(cell);
		  table.setSpacingAfter(3f);		
		  table.setSpacingBefore(5f);
		  document.add(table);		  		  		  
	  }
    

	/*   Chunk ch = new Chunk("Task List",
				FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD));	
		ch.setUnderline(0.2f, -2f);	
		document.add(ch);	*/
		
	    float[] widths2 = { 1f, 1f, 1f, 1f};
		table = new PdfPTable(widths2);
		table.setWidthPercentage(70);
		cell = new PdfPCell();
		cell.setColspan(4);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setColspan(4);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);	
	    cell = new PdfPCell(new Phrase("Task List",
				   FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLDITALIC )));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);	
		cell.setColspan(4);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
 		cell = new PdfPCell();
		cell.setColspan(4);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Paragraph("Task",
										   FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
		cell.setBackgroundColor(new Color(154,204,255));		    
		table.addCell(cell);
      	
		cell = new PdfPCell(new Paragraph("Service Start Date",
				  				 FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
		cell.setBackgroundColor(new Color(154,204,255));		    
		table.addCell(cell);

		cell = new PdfPCell(new Paragraph("Service End Date",
		   						FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
		cell.setBackgroundColor(new Color(154,204,255));		    
		table.addCell(cell);

		cell = new PdfPCell(new Paragraph("Technician",
				   			FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
		cell.setBackgroundColor(new Color(154,204,255));		    
		table.addCell(cell);
		document.add(table);
		
		ArrayList<CanonE307BillEstmtTskDtlsRec> tskLst = chrgObj.getBllEstmtTskDtls(strSrNum);
		if(tskLst!=null && tskLst.size()>0){
			System.out.println("tskLst size: "+ tskLst.size());
			for(CanonE307BillEstmtTskDtlsRec tskLstBean : tskLst){
				table = new PdfPTable(widths2);
				table.setWidthPercentage(70);
				cell = new PdfPCell(new Paragraph(tskLstBean.getStrTaskNum(),
   						FontFactory.getFont(FontFactory.HELVETICA, 8)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
					table.addCell(cell);
				cell = new PdfPCell(new Paragraph(utilObj.getTmZone(objBean.getStrCustZip(),objBean.getStrCntryCd(),tskLstBean.getStrActualStrtDt()),
   						FontFactory.getFont(FontFactory.HELVETICA, 8)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(utilObj.getTmZone(objBean.getStrCustZip(),objBean.getStrCntryCd(),tskLstBean.getStrActualEndDt()),
		   					FontFactory.getFont(FontFactory.HELVETICA, 8)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(tskLstBean.getStrTechNm(),
			   						FontFactory.getFont(FontFactory.HELVETICA, 8)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
				table.addCell(cell);
				document.add(table);
			//	System.out.println("Tech Name : "+ tskLstBean.getStrTechNm());
			}
			table = new PdfPTable(widths2);
			table.setWidthPercentage(70);
			cell = new PdfPCell(new Paragraph(""));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(""));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(""));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(""));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			table.setSpacingAfter(10f);
			table.setSpacingBefore(10f);	
			document.add(table);
		}

	    float[] widths4 = {1f};
	    /*    PdfPTable outrTable = new PdfPTable(widths4);
	    outrTable.setWidthPercentage(80);
	    cell = new PdfPCell(new Paragraph(""));
	    outrTable.addCell(cell);*/

	    
		
	    float[] widths3 = { 1f, 1f, 2f, 1f, 1f, 1f, 1f};
		table = new PdfPTable(widths3);
		table.setWidthPercentage(70);
		
		//row1
		cell= new PdfPCell(new Phrase("Charge Details",  FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLDITALIC )));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(7);
        table.addCell(cell);
	
    	cell = new PdfPCell();
		cell.setColspan(7);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
        
		cell = new PdfPCell(new Paragraph("Line",
										   FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
		cell.setBackgroundColor(new Color(154,204,255));		    
		table.addCell(cell);
      	
		cell = new PdfPCell(new Paragraph("Item Number",
				  				 FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
		cell.setBackgroundColor(new Color(154,204,255));		    
		table.addCell(cell);

		cell = new PdfPCell(new Paragraph("Description",
		   						FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
		cell.setBackgroundColor(new Color(154,204,255));		    
		table.addCell(cell);

		cell = new PdfPCell(new Paragraph("Qty",
				   			FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
		cell.setBackgroundColor(new Color(154,204,255));		    
		table.addCell(cell);
		
		cell = new PdfPCell(new Paragraph("UOM",
	   			FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
		cell.setBackgroundColor(new Color(154,204,255));		    
		table.addCell(cell);	

		cell = new PdfPCell(new Paragraph("List Price",
	   			FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
		cell.setBackgroundColor(new Color(154,204,255));		    
		table.addCell(cell);	
		
		cell = new PdfPCell(new Paragraph("Net Price",
	   			FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD )));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
		cell.setBackgroundColor(new Color(154,204,255));		    
		table.addCell(cell);		
		
		document.add(table);
		ArrayList<CanonE307BillEstmtItmDtlsRec> itmLst = chrgObj.getBllEstmtItmDtls(strSrNum);
		
		double netTotal=0.0;
        if(itmLst!=null && itmLst.size()>0){
        	System.out.println("Item List .."+ itmLst.size());
        	for(CanonE307BillEstmtItmDtlsRec itmBean : itmLst){
        		netTotal = netTotal+itmBean.getNetPrice();
				table = new PdfPTable(widths3);
				table.setWidthPercentage(70);
				cell = new PdfPCell(new Paragraph(itmBean.getStrLineNum(),
   						FontFactory.getFont(FontFactory.HELVETICA, 8)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
					table.addCell(cell);
				cell = new PdfPCell(new Paragraph(itmBean.getStrItmNum(),
   						FontFactory.getFont(FontFactory.HELVETICA, 8)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(itmBean.getStrDescription(),
		   					FontFactory.getFont(FontFactory.HELVETICA, 8)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(twoDForm.format(itmBean.getQty()), FontFactory.getFont(FontFactory.HELVETICA, 8)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(itmBean.getStrUom(),
	   					FontFactory.getFont(FontFactory.HELVETICA, 8)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);					
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(twoDForm.format(itmBean.getLstPrice()), FontFactory.getFont(FontFactory.HELVETICA, 8)));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);					
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(twoDForm.format(itmBean.getNetPrice()), FontFactory.getFont(FontFactory.HELVETICA, 8)));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);					
				table.addCell(cell);
				
		/*		PdfPTable outerTable =new PdfPTable(widths4);
				outerTable.setWidthPercentage(90);
			    PdfPCell containerCell = new PdfPCell(new Phrase(""));
			    containerCell.addElement(table);
			    outerTable.addCell(containerCell);*/
			    
			  //  PdfPTable outerTable = new PdfPTable(1);
				document.add(table); 
        	}
        }
        table = new PdfPTable(widths3);
		table.setWidthPercentage(70);
        cell = new PdfPCell(new Paragraph("Grand Total",
				   FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD )));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);	
		cell.setColspan(6);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		 cell = new PdfPCell(new Paragraph(Double.toString(netTotal),
				   FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD )));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);	
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
	//	document.add(table); 
	    cell = new PdfPCell();
		cell.setColspan(7);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
//		document.add(table); 
		
	    cell = new PdfPCell(new Paragraph("This is an Estimate not a Bill",
                  FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(255, 0, 0))));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);	
		cell.setColspan(7);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		document.add(table); 
		
      document.close();
      
      System.out.println("Inside Billing Estimate6 "); 
    //  DataOutput output = new DataOutputStream( response.getOutputStream() ); 
     byte[] bytes = buffer.toByteArray();
      response.setContentLength(bytes.length);
      response.getOutputStream().write(bytes);
      response.getOutputStream().flush();
      System.out.println("Inside Billing Estimate7 ");  
	
}catch(DocumentException de) {
  System.out.println(de.getMessage());
}
System.out.println("After catch");
%>
</body>
</html>