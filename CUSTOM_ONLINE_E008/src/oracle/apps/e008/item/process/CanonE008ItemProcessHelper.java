package oracle.apps.e008.item.process;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.Font;
import com.aspose.cells.OperatorType;
import com.aspose.cells.Protection;
import com.aspose.cells.Style;
import com.aspose.cells.Validation;
import com.aspose.cells.ValidationAlertType;
import com.aspose.cells.ValidationType;
import com.aspose.cells.ValidationCollection;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.jspsmart.upload.SmartUpload;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;    
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;  
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

public class CanonE008ItemProcessHelper {
  
    public static String ASPOSE_LIC_FILE = "Aspose.Total.Java.lic";
    static DateFormat formater = new SimpleDateFormat("MMM d, yyyy HH:mm:ss");
    //static Color GRAY_OUT_COLOR = new Color(0xCD, 0xCD, 0xCD);
    //static Color NON_SETUP_COLOR = new Color(197, 217, 241);  
       
    private Validation addWholeNumberValidation(Worksheet worksheet,String min,String max,String errorMessage){
    	ValidationCollection validations = worksheet.getValidations();
        //Creating a Validation object
        int index = validations.add();  
        Validation validation = validations.get(index);
        //Setting the validation type to whole number
        validation.setType(ValidationType.WHOLE_NUMBER);
        //Setting the operator for validation to Between
        validation.setOperator(OperatorType.BETWEEN);
        //Setting the minimum value for the validation
        validation.setFormula1(min);
        //Setting the maximum value for the validation
        validation.setFormula2 (max);
        if(errorMessage!=null){
            validation.setErrorMessage(errorMessage);
        }
        
        validation.setAlertStyle(ValidationAlertType.WARNING);   
        
        //Applying the validation to a range of cells from A1 to B2 using the
        //CellArea structure
//        CellArea area = new CellArea(row,col,row,col);
//        validation.addCellArea(area);
        return validation;
    }
    
/*    private void setReadOnly(Cell cell) {
        setReadOnly(cell,true,true);
    }
    private void setReadOnly(Cell cell,boolean locked) {
        setReadOnly(cell,locked,true);
    }
    private void setReadOnly(Cell cell,boolean locked,boolean grayout) {
        setReadOnly(cell,locked,grayout,GRAY_OUT_COLOR);
    }
    private void setReadOnly(Cell cell,boolean locked,boolean grayout,Color backgroupdColor) {
        Style s = cell.getStyle();
        if(grayout){
            s.setColor(backgroupdColor);
        }
        s.setCellLocked(locked);
        cell.setStyle(s);
    }*/
    
    static String[] HEADER={"ITEM#","MODEL#","ITEM_DESCRIPTION","ITEM TYPE","ITEM TEMPLATE"};
    private void setHeader(Worksheet worksheet, List alCntrReadHdrLbl) {
        int c = 0;
        int r = 0;
        headerstyle(setCellValue(worksheet, r, c++, HEADER[c-1]));
        headerstyle(setCellValue(worksheet, r, c++, HEADER[c-1]));
        headerstyle(setCellValue(worksheet, r, c++, HEADER[c-1]));
        headerstyle(setCellValue(worksheet, r, c++, HEADER[c-1]));

        int iCntrCnt = alCntrReadHdrLbl.size();

        for (int i = 0; i < iCntrCnt; i++) {
            String[] strArr = (String[]) alCntrReadHdrLbl.get(i);
            String hdrLbl = strArr[1];
            String orclCntrNum = strArr[2];
            headerstyle(setCellValue(worksheet, r, c++, hdrLbl + "\n" + orclCntrNum));
        }
        headerstyle(setCellValue(worksheet, r, c++, "Location\n"));
        worksheet.getCells().hideColumn(c);
    }
    private void headerstyle(Cell cell){
        Style s = cell.getStyle();
        Font font=s.getFont();
        font.setBold(true);
        //s.setFont(font);
        s.setTextWrapped(true);
        cell.setStyle(s);
        
    }

    private String getLabel(int j, List alCntrReadHdrLbl) {
        String[] strArr = (String[]) alCntrReadHdrLbl.get(j);
        String hdrLbl = strArr[1];
        return hdrLbl;
    }

    private Cell cell(Worksheet worksheet,int row, int col) {
        Cells cells = worksheet.getCells();
        Cell cell = cells.getCell(row, col);
        return cell;
    }
    
    private Cell setCellValue(Cell cell,
            String value,Boolean isConverted) {

        if(isConverted==null)
            cell.setValue(value);
        else
            cell.setValue(value); //setValue(value,isConverted.booleanValue());
        return cell;
    }
    
    private Cell setCellValue(Worksheet worksheet,
            int row, int col,
            String value,Boolean isConverted) {
        Cells cells = worksheet.getCells();
        Cell cell = cells.getCell(row, col);
        setCellValue(cell, value, isConverted);
        return cell;
    }
    
    private Cell setCellValue(Worksheet worksheet,
            int row, int col,
            String value) {
        return setCellValue(worksheet, row, col, value, Boolean.TRUE);
    }
    
    private Cell setCellFormular(Cell cell,
            String formular) {
        cell.setFormula(formular);
        return cell;
    }    
    private static String getCellValue(Worksheet worksheet,
            int row, int col) {

        Cells cells = worksheet.getCells();
        Cell cell = cells.getCell(row, col);
        
        if (row==0) 
        		return cell.getStringValue();
        else
        	    return CanonE008ItemProcessUtil.protectSpecialCharacters(cell.getStringValue());
    }

/*    private void protectWorksheet(Worksheet sheet, String password) {
        Protection pr = new Protection();
        pr.setDeletingColumnsAllowed(false);
        pr.setDeletingRowsAllowed(false);
        pr.setEditingContentsAllowed(false);
        pr.setEditingObjectsAllowed(false);
        pr.setEditingScenariosAllowed(false);
        pr.setFilteringAllowed(false);
        pr.setFormattingCellsAllowed(false);
        pr.setFormattingColumnsAllowed(false);
        pr.setFormattingRowsAllowed(false);  
        pr.setPassword(password);
        sheet.protect(pr);
    }*/
    /**
     * transform excel to html form values.
     */
    public static String  transform(String projectID, String userid, PageContext pageContext, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletConfig config=pageContext.getServletConfig();
        StringBuffer stringBuffer=new StringBuffer();
        int recordCount=0;
        try {
        	 System.out.println("in transform 1 "+projectID);
            SmartUpload objSmartUpload = new SmartUpload();
            System.out.println("in transform 1.1 "+projectID);
            objSmartUpload.initialize(config, request, response);      
            System.out.println("in transform 1.2 "+projectID);
            objSmartUpload.upload();
            System.out.println("in transform 1.3 "+projectID);
            objSmartUpload.setAllowedFilesList("xlsx,xlsm,xlsb,xltx,xltm,xls,xlt,xlm,xlw");
            System.out.println("in transform 2 "+projectID);	
            com.jspsmart.upload.File updFile = null;
            System.out.println("in transform 3 "+projectID);
            updFile = objSmartUpload.getFiles().getFile(0);
            int fileSize = updFile.getSize();
            System.out.println("in transform 4 "+projectID);
            byte[] fileContent = new byte[fileSize];

            for (int count = 0; count < fileSize; count++) {
                fileContent[count] = updFile.getBinaryData(count);
            }
            //String content = new String(fileContent);
            //Workbook wb = new Workbook();
            ByteArrayInputStream  is=new ByteArrayInputStream(fileContent);
            Workbook wb = new Workbook(is);
            //wb.open(is);
            
           // List canonTemplateList=(List)CanonE008ItemProcessUtil.first(CanonE008ItemProcessDAO.canonTemplateList());
            
           CanonE008ItemProcessDAO obj0 = new CanonE008ItemProcessDAO();
             
            Object[] attributeHeaders = obj0.getItemMainAttributeHeaders(new BigDecimal(projectID),userid);
         	ArrayList templateHeaderList = (ArrayList)attributeHeaders[0];

         	Object[] attributeresult = obj0.getProjItemAdditionalAttrValues(new BigDecimal("1"),new BigDecimal("1"));
         	List attributeList = (List) CanonE008ItemProcessUtil.first(attributeresult);
        	
            
            Worksheet s=wb.getWorksheets().get(0);
            int r;
            
            String[] qplan=null;
            for(r=1;r<=Integer.MAX_VALUE;r++){
                boolean emptyRow=true;
                //for(int f=0;f<FIELD_NAME.length;f++){
                for(int f=0;f<TOTAL_ATTRIBUTE;f++){
                    String cellValue=getCellValue(s,r,f);
                    if(cellValue!=null && !cellValue.trim().equals("")){
                        emptyRow=false;
                        recordCount = recordCount+1;
                        break;
                    }
                }
                System.out.println("recordCount "+recordCount);
                
                if (emptyRow) break;
					 
		         for(int f=0;f<TOTAL_ATTRIBUTE;f++){
		         //for(int f=0;f<FIELD_NAME.length;f++){	
		                    String cellValue=getCellValue(s,r,f);
		                    String itemCode=getCellValue(s,r,0);
		                    //System.out.println(" Inside templateHeader templateName " + itemCode);
		                    String attributeName=getCellValue(s,0,f);
/*		                    if(f==COL_CANON_TEMPLATE){
		                        qplan=plan(canonTemplateList,cellValue);
		                    }else if(f>=COL_ITEM_TYPE && f<=COL_THIRD_PARTY && qplan!=null ){
		                      String v=qplan[f-COL_ITEM_TYPE];
		                      cellValue= v==null || CanonE008ItemProcessUtil.isEmpty(v.trim())? cellValue : v;
		                    }*/
		                    //print(FIELD_NAME[f],cellValue,stringBuffer,itemCode);
		                    int row = r-1; 	
		                    if(f==COL_CANON_TEMPLATE)
		                    {
		                    	print("canonTemplateNames_"+row,cellValue,stringBuffer,itemCode);
		                    	print("item_name_"+row,"",stringBuffer,itemCode);
		                    }
		                    
		                   // System.out.println("attributeName "+attributeName);
		                      
		                    for(int i=0;i<templateHeaderList.size();i++) {	
			   					 CanonE008ItemProcessDAO.attributeheaderInfo tempHeader = (CanonE008ItemProcessDAO.attributeheaderInfo)templateHeaderList.get(i);
			   					 String templateHeader=(String)tempHeader.getAttributeName().toUpperCase();
			   					 //System.out.println("templateHeader "+ templateHeader);
				                    if(templateHeader.trim().equals(attributeName.toUpperCase()))
				                    {   System.out.println(" Inside templateRowName" +row+"-"+i +  " : " + itemCode +  " : " + templateHeader+ " : " + " : " + cellValue);
				                    	print("templateRowName"+row+"-"+i,cellValue,stringBuffer,itemCode);
				                    	
				                    	if (attributeName.toUpperCase().equals("MARKETING MODEL"))
				                    	{	
				                    			print("templateRowName"+(r-1)+"-"+i,cellValue,stringBuffer,itemCode);
				                    			//print("modtemplateRowName"+(r-1)+"-"+i,cellValue,stringBuffer,itemCode);
				                    	} 		
				                    }	
			                    }
		                    
		                    for(int k=0;k<attributeList.size();k++){
									CanonE008ItemProcessDAO.ItemInfoRec itemrec=(CanonE008ItemProcessDAO.ItemInfoRec) attributeList.get(k);
									String additionalHeader=(String)itemrec.getAttribute().toUpperCase();
			   					  	//System.out.println("additionalHeader "+ additionalHeader.toUpperCase());
				                    if(additionalHeader.trim().equals(attributeName.toUpperCase()))
				                    {   System.out.println(" Inside additionalHeader "+ additionalHeader);
				                    	print("templateaddRowName"+row+"-"+k,cellValue,stringBuffer,itemCode);
				                    }	
			                    }
			                   
		                     
                }
            }
            print("excelRecord",recordCount+"",stringBuffer,"");
            
        } catch (Exception ex) {
            throw ex;
        }
        return stringBuffer.toString();
    }
    
    static final int COLOR_COUNTER=3;
    static final int DLB_CLICK_COLOR_COUNTER=6;
    static final int NO_READ=-999999;
    static final String EMPTY="";
    //static final int COL_CANON_TEMPLATE=4;
    static final int COL_CANON_TEMPLATE=0;
    static final int COL_ITEM_TYPE=5;
    static final int COL_TEMPLATE=6;
    static final int COL_MERCH_TYPE=7;
    static final int COL_PROD_CODE=8;
    static final int COL_THIRD_PARTY=9;
    static final int TOTAL_ATTRIBUTE=132; //100;
    
    // derive color from color=total - bw
    
/*    static String[] FIELD_NAME={"item_code",
        "set_item",
        "model_no",
        "item_description",
        "canon_template",
        "item_type",
        "template",
        "merch_type",
        "prod_code",
        "usa_compensation",
        "usa_compensation2",
        "third_party",
        "supplier_name",
        "supplier_site_name",
        "supplier_item_no",
        "service_item",
        "license_control",
        "maintenance_term",
        "cost",
        "msrp",
        "counter_group",
        "service_mode",
        "speed_segment",
        "supply_category",
        "costing_enabled",
        "cogs",
        "revenue"};
*/
    static String[] templatePlan=new String[5];
    public static String[] plan(List canonTemplateList,String value){
            /*for(int i=0;canonTemplateList!=null && i<canonTemplateList.size();i++){
                CanonE008ItemProcessDAO.CanonTemplateInfo ti=(CanonE008ItemProcessDAO.CanonTemplateInfo)canonTemplateList.get(i);
                if(value!=null && ti.getCanonTemplate().trim().equalsIgnoreCase(value.trim())){
                    templatePlan[0]=ti.getItemType();
                    templatePlan[1]=ti.getTemplate();
                    templatePlan[2]=ti.getMerchType();
                    templatePlan[3]=ti.getProdCode();
                    templatePlan[4]=ti.getThirdParty();
                    return templatePlan;
                }    
            }
            
            System.out.println("canon template not found "+value);
            for(int i=0;i<templatePlan.length;i++){
                templatePlan[i]=null;
            }
            return templatePlan;*/
    	return null;
    }
    
    private static void print(String name,String value,StringBuffer stringBuffer,String itemCode ) throws IOException{
        //String prefix="_xls_";
        //String inputStr="<input type='text' id="+"templateRowName0"+ " name=" + "templateRowName0" +">";
        String inputStr="<input id=\""+name+"\"  type=\"hidden\" name=\""+name+"\" value=\""+value+"\" /> ";
        //String inputStr="<input class=\""+prefix+name+"\"  type=\"hidden\" name=\""+prefix+name+"\" value=\""+value+"\" "+" data-item-code=\""+itemCode+"\"  /> ";
        stringBuffer.append(inputStr);
    }
    public static class WorkbookStream {

        Workbook wb;

        public WorkbookStream(Workbook wb) {
            this.wb = wb;
        }

        public void save(OutputStream out) throws Exception {
            wb.save(out,1);
        }
    }
    
    public static boolean isDevEnviornment(){
/*        String dbname=(String) CanonE008ItemProcessUtil.first(CanonE008ItemProcessDAO.getDbName());
        return "CANDEV".equals(dbname) || "INIDEV".equals(dbname);*/    
    	return true;
    }
    
    
//    <select class="counter_group <%=required_and_owner(item.getItemId(),"COUNTER_GROUP")%>" name="counter_group"  data-item-code="<%=nonNullify(item.getItemCode())%>" >
//      <option value=""></option>
//      <% for (int ii = 0; ii < countergroupList.size(); ii++) {
//              String countergroup = (String) countergroupList.get(ii);
//      %>
//      <option title="<%=countergroup%>" value="<%=countergroup%>"  <%=countergroup.equalsIgnoreCase(item.getCounterGroup()) ? "SELECTED" : ""%> > <%=countergroup%> </option>
//      <%}%>
//   </select>
    /**
     * name: use lower cases
     */
    public static String genSelectHtml(Map lovs,String name,String value,String requiredAndowner,String itemCode,String planval){
        StringBuffer html=new StringBuffer();
        html.append("<select class=\"").append(name).append(" ")
                .append(requiredAndowner)
                .append("\" name=\"").append( name)
                /*.append("\" placeholder=\"").append( itemCode)*/
                .append("\" id=\"").append( name)
                /*.append("\"  data-item-code=\"").append(itemCode)*/
                .append("\"  data-plan-value=\"").append(planval)
                .append("\" >")
                .append("<option title=\"").append(itemCode).append("\" value=\"").append("00").append("\"").append(" class=hideoption").append(">").append(itemCode).append("</option>");
                //.append("<option value=\"\"></option>");
        List list=(List)lovs.get(name);
        if(list==null){
            list=Collections.EMPTY_LIST;
        }
        
        for (int i = 0; i < list.size(); i++) {
            String lovValue = CanonE008ItemProcessUtil.nonNullify((String) list.get(i));            
            boolean selected=lovValue.equalsIgnoreCase(value);            
            html.append("<option title=\"").append(lovValue).append("\" value=\"").append(lovValue).append("\"").append(selected ? "SELECTED" : "").append(">").append(lovValue).append("</option>");
        }
        html.append("</select>");        
        return html.toString();
    }
     
  
    public static String genSelectdataListHtml(Map lovs,String name,String value,String requiredAndowner,String itemCode){
        /*StringBuffer html=new StringBuffer();
        html.append("<input id=template  autocomplete=off type=text list=\"").append(name).append("")
        	.append("\" placeholder=\"").append( itemCode)
        	.append("\"  onchange=\"").append("callTemplateChange('"+value+"'" +")")
        	.append("\" value=\"").append( value)
        	.append("\" >");
        html.append("<datalist style=display:none id=\"").append(name).append("")
                .append("\" >");
                //.append("<option title=\"").append(itemCode).append("\" value=\"").append("00").append("\"").append(" class=hideoption").append(">").append(itemCode).append("</option>");
                //.append("<option value=\"\"></option>");
        List list=(List)lovs.get(name);
        if(list==null){
            list=Collections.EMPTY_LIST;
        }*/
        StringBuffer html=new StringBuffer();
        html.append("<select class=\"").append(name).append(" ")
                .append("\" name=\"").append( "template")
                .append("\" id=\"").append( "template")
                .append("\" value=\"").append( value)
                .append("\" placeholder=\"").append( itemCode)
                .append("\"  onchange=\"").append("callTemplateChange('"+value+"'" +")")
                //.append("\"  data-item-code=\"").append(itemCode)
                //.append("\"  data-plan-value=\"").append(planval)
                .append("\" >")
                .append("<option value=\"\"></option>");
        List list=(List)lovs.get(name);
        if(list==null){
            list=Collections.EMPTY_LIST;
        }        
                
        
        for (int i = 0; i < list.size(); i++) {
            String lovValue = CanonE008ItemProcessUtil.nonNullify((String) list.get(i));            
            boolean selected=lovValue.equalsIgnoreCase(value);            
            html.append("<option title=\"").append(lovValue).append("\" value=\"").append(lovValue).append("\"").append(selected ? "SELECTED" : "").append(">").append(lovValue).append("</option>");
        }
        html.append("</select>");        
        return html.toString();
    }
    
    public static String genSelectdataLMItemHtml(Map lovs,String name,String value,String requiredAndowner,String itemCode){
        /*StringBuffer html=new StringBuffer();
        html.append("<input name=masterProject id=masterProjectM class=expl8datalist autocomplete=off type=text list=\"").append(name).append("")
        	.append("\" placeholder=\"").append( itemCode)
        	//.append("\"  onchange=\"").append("callTemplateChange('"+value+"'" +")")
        	.append("\" value=\"").append( value)
        	.append("\" >");
        html.append("<datalist style=display:none id=\"").append(name).append("")
                .append("\" >");
                .append("<!--[if IE 8]><select disabled style=display:none><![endif]-->");
                //.append("<option title=\"").append(itemCode).append("\" value=\"").append("00").append("\"").append(" class=hideoption").append(">").append(itemCode).append("</option>");
                //.append("<option value=\"\"></option>");
        List list=(List)lovs.get(name);
        if(list==null){
            list=Collections.EMPTY_LIST;
        }*/

        StringBuffer html=new StringBuffer();
        html.append("<select class=\"").append(name).append(" ")
                .append("\" name=\"").append( "masterProjectM")
                .append("\" id=\"").append( "masterProjectM")
                .append("\" value=\"").append( value)
                //.append("\"  data-item-code=\"").append(itemCode)
                //.append("\"  data-plan-value=\"").append(planval)
                .append("\" >")
                .append("<option value=\"\"></option>");
        List list=(List)lovs.get(name);
        if(list==null){
            list=Collections.EMPTY_LIST;
        }        
        
        for (int i = 0; i < list.size(); i++) {
            String lovValue = CanonE008ItemProcessUtil.nonNullify((String) list.get(i));            
            boolean selected=lovValue.equalsIgnoreCase(value);            
            html.append("<option title=\"").append(lovValue).append("\" value=\"").append(lovValue).append("\"").append(selected ? "SELECTED" : "").append(">").append(lovValue).append("</option>");
        }
        /*html.append("<!--[if IE 9]></select><![endif]-->");*/
        html.append("</select>");        
        return html.toString();
    }
    
    public static String genSelectItemdataListHtml(Map lovs,String name,String value, String strVal, String requiredAndowner,String itemCode){
        
        StringBuffer html=new StringBuffer();
        html.append("<select class=\"").append(name).append(" ")
                .append("\" name=\"").append( strVal)
                .append("\" id=\"").append( strVal)
                .append("\"  onchange=\"").append("callComponentChange('"+strVal+"','"+ requiredAndowner +"'" +")")
                //.append("\"  data-item-code=\"").append(itemCode)
                //.append("\"  data-plan-value=\"").append(planval)
                .append("\" >")
                .append("<option title=\"").append("Select").append("\" value=\"").append("00").append("\"").append(">").append("Select").append("</option>")
                .append("<option value=\"\"></option>");
        List list=(List)lovs.get(name);
        if(list==null){
            list=Collections.EMPTY_LIST;
        }

        html.append("</select>");        
        return html.toString();
        
    }

    public static String genSelecttemplatedataListHtml(Map lovs,String name,String value, String strVal, String row,String templateid){
        
        StringBuffer html=new StringBuffer();
        html.append("<select class=\"").append(name).append(" ")
                .append("\" name=\"").append( strVal)
                .append("\" id=\"").append( strVal)
                .append("\"  onchange=\"").append("callListChange('"+strVal+"','"+ row +"','"+ templateid +"')")
                .append("\" >")
                .append("<option title=\"").append("Select").append("\" value=\"").append("00").append("\"").append(">").append("Select").append("</option>")
                .append("<option value=\"\"></option>");
        List list=(List)lovs.get(name);
        if(list==null){
            list=Collections.EMPTY_LIST;
        }

        html.append("</select>");        
        return html.toString();
        
    }    
    
    
public static String genSelectitemattributedataListHtml(Map lovs,String name,String value, String strVal, String row,String templateid){
        
        StringBuffer html=new StringBuffer();
        html.append("<select class=\"").append(name).append(" ")
                .append("\" name=\"").append( strVal)
                .append("\" id=\"").append( strVal)
                .append("\"  onchange=\"").append("callListChangeAttribute('"+strVal+"','"+ row +"','"+ templateid +"')")
                .append("\" >")
                .append("<option title=\"").append("Select").append("\" value=\"").append("00").append("\"").append(">").append("Select").append("</option>")
                .append("<option value=\"\"></option>");
        List list=(List)lovs.get(name);
        if(list==null){
            list=Collections.EMPTY_LIST;
        }

        html.append("</select>");        
        return html.toString();
        
    }    
    
    public static String genSelectHtml(Map lovs,String name,String value,String requiredAndowner,String itemCode){
        return genSelectHtml(lovs,name,value,requiredAndowner,itemCode,"");
    }

    public static String genSelectHtmlTemplate(Map lovs,String name,String value,String functionstr,String itemCode,String planval){
        StringBuffer html=new StringBuffer();
        html.append("<select class=\"").append(name).append(" ")
                .append("\" name=\"").append( name)
                .append("\" id=\"").append( name)
                .append("\" onchange=\"").append( functionstr)
                //.append("\"  data-item-code=\"").append(itemCode)
                //.append("\"  data-plan-value=\"").append(planval)
                .append("\" >")
                //.append("<option title=\"").append(itemCode).append("\" value=\"").append("00").append("\"").append("class=hideoption").append(">").append(itemCode).append("</option>");
                .append("<option value=\"\"></option>");
        List list=(List)lovs.get(name);
        if(list==null){
            list=Collections.EMPTY_LIST;
        }
        for (int i = 0; i < list.size(); i++) {
            String lovValue = CanonE008ItemProcessUtil.nonNullify((String) list.get(i));            
            boolean selected=lovValue.equalsIgnoreCase(value);            
            html.append("<option title=\"").append(lovValue).append("\" value=\"").append(lovValue).append("\"").append(selected ? "SELECTED" : "").append(">").append(lovValue).append("</option>");
        }
        html.append("</select>");        
        return html.toString();
    }

    public static String genSelectHtmlattribute(Map lovs,String name,String value,String required){
        StringBuffer html=new StringBuffer();
        html.append("<select class=\"").append(name).append(" ")
        		.append(required)
                .append("\" name=\"").append( name)
                .append("\" id=\"").append( name)
                //.append("\"  data-item-code=\"").append(itemCode)
                //.append("\"  data-plan-value=\"").append(planval)
                .append("\" >")
                //.append("<option title=\"").append(itemCode).append("\" value=\"").append("00").append("\"").append("class=hideoption").append(">").append(itemCode).append("</option>");
                .append("<option value=\"\"></option>");
        List list=(List)lovs.get(name);
        if(list==null){
            list=Collections.EMPTY_LIST;
        }
        String selectElement="";
        for (int i = 0; i < list.size(); i++) {
            //String lovValue = CanonE008ItemProcessUtil.nonNullify((String) list.get(i));
        	String lovValue = (String)list.get(i++);
        	String lovName=(String)list.get(i);
        	//CanonE008TemplateDAO.CanonE008lovRec lovValue = (CanonE008TemplateDAO.CanonE008lovRec)list.get(i);
        	boolean selected;
        	if (lovValue!=null)
        		selected=lovValue.equals(value); //  .equalsIgnoreCase(value);
        	else
        		selected=false;
        	
        	if (selected==true)
        		selectElement = selectElement + "<option value=" + lovValue + " "+ "SELECTED>" + lovName +"</option>";
        	else
        		selectElement = selectElement + "<option value=" + lovValue + ">" + lovName +"</option>";

        	
            //html.append("<option title=\"").append(lovValue).append("\" value=\"").append(lovValue).append("\"").append(">").append(lovName).append("</option>");
           //  html.append("<option title=\"").append(lovValue).append("\" value=\"").append(lovValue).append("\"").append(selected ? "SELECTED" : "").append(">").append(lovName).append("</option>");
        }
        html.append(selectElement);
        html.append("</select>");        
        return html.toString();
    }

    public static String genSelectItemHtmlattribute(Map lovs,String name,String value,String required,String attributename,String row){
        StringBuffer html=new StringBuffer();
        if (required == "noneditrequired readonly")
        {    html.append("<select class=\"").append(name).append(" ")
	        		.append(required)
	                .append("\" name=\"").append( name)
	                .append("\" id=\"").append( name)
	                .append("\" >");
	                //.append("<option value=\"\"></option>");
        }
        else
        {    html.append("<select class=\"").append(name).append(" ")
    		.append(required)
            .append("\" name=\"").append( name)
            .append("\" id=\"").append( name)
            //.append("\" onFocus=\"").append("changeFunc('"+name+"','"+attributename+"','" + value + "');")
            .append("\" onFocus=\"").append("changeFunc('"+name+"','"+attributename+"','" + value + "','" + row + "');")
            .append("\" >")
            .append("<option value=\"\"></option>");
        }	
        
        List list=(List)lovs.get(name);
        if(list==null){
            list=Collections.EMPTY_LIST;
        }
        String selectElement="";
        for (int i = 0; i < list.size(); i++) {
        	String lovValue = (String)list.get(i++);
        	String lovName=(String)list.get(i);
        	boolean selected;
        	if (lovValue!=null)
        		selected=lovValue.equals(value); //  .equalsIgnoreCase(value);
        	else
        		selected=false;
        	
        	if (selected==true)
        		selectElement = selectElement + "<option value=" + lovValue + " "+ "SELECTED>" + lovName +"</option>";
        	else
        		selectElement = selectElement + "<option value=" + lovValue + ">" + lovName +"</option>";

        	
            //html.append("<option title=\"").append(lovValue).append("\" value=\"").append(lovValue).append("\"").append(selected ? "SELECTED" : "").append(">").append(lovName).append("</option>");
        }
        html.append(selectElement);
        html.append("</select>");        
        return html.toString();
    }

    public static String genSelectItemHtmlmainattribute(Map lovs,String name,String value,String required,String attributename,String row){
        StringBuffer html=new StringBuffer();
        if (required == "noneditrequired readonly")
        {    html.append("<select class=\"").append(name).append(" ")
	        		.append(required)
	                .append("\" name=\"").append( name)
	                .append("\" id=\"").append( name)
	                .append("\" >");
	                //.append("<option value=\"\"></option>");
        }
        else
        {    html.append("<select class=\"").append(name).append(" ")
    		.append(required)
            .append("\" name=\"").append( name)
            .append("\" id=\"").append( name);
            
            if (!attributename.equals("SUPPLIER_SITE"))
            {	
            	html.append("\" onFocus=\"").append("changeFunc('"+name+"','"+attributename+"','" + value + "','" + row + "');");
            }
            else
            {
            	html.append("\" onFocus=\"").append("changeFuncSupplier('"+name+"','"+attributename+"','" + value +"','" + row + "');");
            	
            }	
            html.append("\" >")
            .append("<option value=\"\"></option>");
        }	
        
        List list=(List)lovs.get(name);
        if(list==null){
            list=Collections.EMPTY_LIST;
        }
        String selectElement="";
        for (int i = 0; i < list.size(); i++) {
        	String lovValue = (String)list.get(i++);
        	String lovName=(String)list.get(i);
        	boolean selected;
        	if (lovValue!=null)
        		selected=lovValue.equals(value); //  .equalsIgnoreCase(value);
        	else
        		selected=false;
        	
        	
        	if (selected==true)
        		selectElement = selectElement + "<option SELECTED value='" + lovValue + "'>" + lovName +"</option>";
        	else
        		selectElement = selectElement + "<option value=" + lovValue + ">" + lovName +"</option>";
				
        	
            //html.append("<option title=\"").append(lovValue).append("\" value=\"").append(lovValue).append("\"").append(selected ? "SELECTED" : "").append(">").append(lovName).append("</option>");
        }
        html.append(selectElement);
        html.append("</select>");        
        return html.toString();
    }
    
    public static String genSelectItemHtmlModal(Map lovs,String name,String value,String required,String attributename,String row){
        StringBuffer html=new StringBuffer();
        html.append("<div class=\"").append("select-editable").append("\" >");
        if (required == "noneditrequired readonly")
        {    html.append("<select class=\"").append("mod"+name).append(" ")
		        	.append(required)
		        	.append("\" onchange=\"").append( "this.nextElementSibling.value=this.value")
	                .append("\" name=\"").append( "mod"+name)
	                .append("\" id=\"").append( "mod"+name)
	                .append("\" >");
	                //.append("<option value=\"\"></option>");
        }
        else  
        {    html.append("<select class=\"").append("mod"+name).append(" ")
    		.append(required)
    		.append("\" onchange=\"").append( "this.nextElementSibling.value=this.value")
            .append("\" name=\"").append( "mod"+name)
            .append("\" id=\"").append( "mod"+name)
          //.append("\" onFocus=\"").append("changeFunc('"+name+"','"+attributename+"','" + value + "');")
            .append("\" onFocus=\"").append("changeFunc('"+"mod"+name+"','"+attributename+"','" + value + "','" + row + "');")
            .append("\" >")
            .append("<option value=\"\"></option>");
        }	    
        
        List list=(List)lovs.get("mod"+name);
        if(list==null){
            list=Collections.EMPTY_LIST;
        }
        String selectElement="";
        for (int i = 0; i < list.size(); i++) {
        	String lovValue = (String)list.get(i++);
        	String lovName=(String)list.get(i);
        	boolean selected;
        	if (lovValue!=null)
        		selected=lovValue.equals(value); //  .equalsIgnoreCase(value);
        	else
        		selected=false;
        	
        	if (selected==true)
        		selectElement = selectElement + "<option value=" + lovValue + " "+ "SELECTED>" + lovName +"</option>";
        	else
        		selectElement = selectElement + "<option value=" + lovValue + ">" + lovName +"</option>";
        	
            //html.append("<option title=\"").append(lovValue).append("\" value=\"").append(lovValue).append("\"").append(selected ? "SELECTED" : "").append(">").append(lovName).append("</option>");
        }
        html.append(selectElement);
        html.append("</select>"); 
       	//html.append("<input type=\"text").append("\"name=\"").append("mod"+name).append("\"id=\"").append("mod"+name).append("\"value=\"").append(value);
        html.append("<input type=\"text").append("\"name=\"").append(name).append("\"id=\"").append(name).append("\"value=\"").append(value);
       	html.append("\" class=\"").append(required);
        html.append("\" />");
        html.append("</div>");
        return html.toString();
    }
    
    public static String gentemplateSelectHtmlattribute(Map lovs,String name,String value,String required,String attributename,String templateid){
        StringBuffer html=new StringBuffer();
        html.append("<select class=\"").append(name).append(" ")
        		.append(required)
                .append("\" name=\"").append( name)
                .append("\" id=\"").append( name);
                
                if (!attributename.equals("SUPPLIER_SITE"))
	            {	
	            	//html.append("\" onFocus=\"").append("changeFunc('"+name+"','"+attributename+"','" + value + "');");
                	html.append("\" onFocus=\"").append("changeFunc('"+name+"','"+attributename+"','" + value +"','" + templateid + "');");
	            }
	            else
	            {
	            	html.append("\" onFocus=\"").append("changeFuncSupplier('"+name+"','"+attributename+"','" + value +"','" + templateid + "');");
	            	
	            }
                //.append("\"  data-item-code=\"").append(itemCode)
                //.append("\"  data-plan-value=\"").append(planval)
                html.append("\" >")
                //.append("<option title=\"").append(itemCode).append("\" value=\"").append("00").append("\"").append("class=hideoption").append(">").append(itemCode).append("</option>");
                .append("<option value=\"\"></option>");
        List list=(List)lovs.get(name);
        if(list==null){
            list=Collections.EMPTY_LIST;
        }
        String selectElement="";
        for (int i = 0; i < list.size(); i++) {
            //String lovValue = CanonE008ItemProcessUtil.nonNullify((String) list.get(i));
        	String lovValue = (String)list.get(i++);
        	String lovName=(String)list.get(i);
        	//CanonE008TemplateDAO.CanonE008lovRec lovValue = (CanonE008TemplateDAO.CanonE008lovRec)list.get(i);
        	boolean selected;
        	if (lovValue!=null)
        		selected=lovValue.equals(value); //  .equalsIgnoreCase(value);
        	else
        		selected=false;
        	
        	if (selected==true)
        		selectElement = selectElement + "<option value=" + lovValue + " "+ "SELECTED>" + lovName +"</option>";
        	else
        		selectElement = selectElement + "<option value=" + lovValue + ">" + lovName +"</option>";
        	
            //html.append("<option title=\"").append(lovValue).append("\" value=\"").append(lovValue).append("\"").append(">").append(lovName).append("</option>");
           // html.append("<option title=\"").append(lovValue).append("\" value=\"").append(lovValue).append("\"").append(selected ? "SELECTED" : "").append(">").append(lovName).append("</option>");
        }
        html.append(selectElement);
        html.append("</select>");        
        return html.toString();
    }
    
    public static String genSelectHtmlsavedsearch(Map lovs,String name,String value){
        StringBuffer html=new StringBuffer();
        html.append("<select class=\"").append(name).append(" ")
                .append("\" name=\"").append( name)
                .append("\" id=\"").append( name)
                .append("\"  onchange=\"").append("callSavedSearchChange('saveSearch')")
                .append("\" >")
                .append("<option title=\"").append("\" value=\"").append("DEFAULT").append("\"").append(">").append("Default").append("</option>");
                //.append("<option value=\"\"></option>");
        List list=(List)lovs.get(name);
        if(list==null){
            list=Collections.EMPTY_LIST;
        }
        for (int i = 0; i < list.size(); i++) {
            String lovValue = CanonE008ItemProcessUtil.nonNullify((String) list.get(i));            
            boolean selected=lovValue.equalsIgnoreCase(value);            
            html.append("<option title=\"").append(lovValue).append("\" value=\"").append(lovValue).append("\"").append(selected ? "SELECTED" : "").append(">").append(lovValue).append("</option>");
        }
        html.append("</select>");        
        return html.toString();
    }
    
    public static String genSelectHtmlProjTemplate(Map lovs,String name,String value,String strid,String planval,String required){
        StringBuffer html=new StringBuffer();
        html.append("<select class=\"").append("proj-main-template").append(" ")
        		.append(required)
                .append("\" name=\"").append( strid)
                .append("\" id=\"").append(strid)
                .append("\" style=\"").append("width: 240px")
                .append("\"  onchange=\"").append("callTemplateChange('"+strid+"',"+planval+")")
                //.append("\"  data-plan-value=\"").append(planval)
                .append("\" >")
        .append("<option value=\"\"></option>");
        List list=(List)lovs.get(name); 
        if(list==null){
            list=Collections.EMPTY_LIST;
        }
        for (int i = 0; i < list.size(); i++) {
            String lovValue = CanonE008ItemProcessUtil.nonNullify((String) list.get(i));            
            boolean selected=lovValue.equalsIgnoreCase(value);            
            html.append("<option title=\"").append(lovValue).append("\" value=\"").append(lovValue).append("\"").append(selected ? "SELECTED" : "").append(">").append(lovValue).append("</option>");
        }
        html.append("</select>");        
        return html.toString();
    }
       
    
    public static String genSelectHtmlForSW(Map lovs,String name,String value,String requiredAndowner,String itemCode,String planval,String merchType){
        StringBuffer html=new StringBuffer();
        if("53".equalsIgnoreCase(merchType)||"54".equalsIgnoreCase(merchType))
        {
        	html.append("<select class=\"").append(name).append(" ")
            .append(requiredAndowner)
            .append("\" name=\"").append( name)
            .append("\"  data-item-code=\"").append(itemCode)
            .append("\"  data-plan-value=\"").append(planval)
            .append("\" >")
            .append("<option value=\"\"></option>");        
        }
        else
        {
        	html.append("<select disabled class=\"").append(name).append(" ")
            .append(requiredAndowner)
            .append("\" name=\"").append( name)
            .append("\"  data-item-code=\"").append(itemCode)
            .append("\"  data-plan-value=\"").append(planval)
            .append("\" >")
            .append("<option value=\"\"></option>");
        }
        List list=(List)lovs.get(name);
        if(list==null){
            list=Collections.EMPTY_LIST;
        }
        for (int i = 0; i < list.size(); i++) {
            String lovValue = CanonE008ItemProcessUtil.nonNullify((String) list.get(i));
            boolean selected=lovValue.equalsIgnoreCase(value);            
            html.append("<option title=\"").append(lovValue).append("\" value=\"").append(lovValue).append("\"").append(selected ? "SELECTED" : "").append(">").append(lovValue).append("</option>");
//            String optionclass=selected && requiredAndowner.indexOf("required")>=0 ? "required": "not_required";
//            html.append("<option class=\"").append(optionclass).append("\" title=\"").append(lovValue).append("\" value=\"").append(lovValue).append("\"").append(selected ? "SELECTED" : "").append(">").append(lovValue).append("</option>");
        }
        html.append("</select>");
        return html.toString();
    }
    
    public static void main(String args[]){
//        try {
//            String strBid="S1026821_1026821";
//            CanonBSDSiteMeterReadDAO readDao = new CanonBSDSiteMeterReadDAO();
//            List alCntrReadHdrLbl = (List)readDao.getCntrReadHrdLbls();
//            List downloadCntrReads = (List)readDao.getBidCntrReads(strBid, null, "", 20, null,"", "N","1", "asc", 1, 5);
//            
//            CanonBSDSiteMeterReadHelper meterReadHelper = new CanonBSDSiteMeterReadHelper();
//            WorkbookStream workbookStream = meterReadHelper.genMeterReadWorkbookStream(strBid, alCntrReadHdrLbl, downloadCntrReads);
//            workbookStream.save(new java.io.FileOutputStream("C:\\Temp\\S1026821_1026821.xls"));
            
//            List savedRead=getSavedRead("S1359965_1359965",20,"0330110224");
//            List alCurrentCntrReads = (List)savedRead.get(0);
//            List alLastBilledCntrReads = (List)savedRead.get(1);
//            
//            String file="C:\\Users\\Q05058\\Documents\\canon\\E590 New Item Setup\\590-Itemupload-Template v1.xls";
//            Workbook wb = new Workbook();
//            FileInputStream is=new FileInputStream(file);
//            wb.open(is);
//
//            StringBuffer stringBuffer=new StringBuffer(); 
//            System.out.println(stringBuffer);
            
/*            Object []result = CanonE008ItemProcessDAO.countergroupList();
            List countergroupList = CanonE008ItemProcessUtil.first(result)!=null ? (List) CanonE008ItemProcessUtil.first(result) : Collections.EMPTY_LIST;
            Map lovs=new HashMap();
            lovs.put("countergroup",countergroupList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
*/        
        
    }
}
