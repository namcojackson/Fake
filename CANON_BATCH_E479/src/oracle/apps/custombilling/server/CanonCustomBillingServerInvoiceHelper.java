package oracle.apps.custombilling.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import canon.apps.common.CanonAsposeUtil;

import canon.excel.cells.Cell;
import canon.excel.cells.CellArea;
import canon.excel.cells.Cells;
import canon.excel.cells.ConsolidationFunction;
import canon.excel.cells.FileFormatType;
import canon.excel.cells.Protection;
import canon.excel.cells.Worksheet;

import canon.excel.cells.Workbook;
import canon.excel.cells.Worksheets;

/**
 *
 * @author Q05058
 */
public class CanonCustomBillingServerInvoiceHelper extends CanonCustomBillingServerInvoiceHelperBase {

	public static final String NULL_STR = "null";
    public static final String TEMPLATE_FILE="CanonCustomBillingServerTemplate.xls";
    // [+-]?
    // (?:
    //  (?:0)|                              Zero
    //  (?:[123456789][\\d,]*\\d*)|         Integer
    //  (?:[\\d,]*\\.\\d*)                  Number with decimal places
    // )
    static Pattern PARRETN_DEIMAL_NUM = Pattern.compile("[+-]?(?:(?:0)|(?:[123456789][\\d,]*\\d*)|(?:[\\d,]*\\.\\d*))");

    private static boolean isNumber(String str) {
        if(isEmpty(str))return false;
        Matcher matcher = PARRETN_DEIMAL_NUM.matcher(str);
        return matcher.matches();
    }
    
    private static boolean isEmpty(String s){
        return s==null || s.trim().length()==0;
    }
    
    public static BigDecimal BigDecimal_ZERO = new BigDecimal("0");

    public static BigDecimal toBigDecimal(String s, boolean nullToZero) {
        if (s != null) {
            try {
                return new BigDecimal(s.trim());
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return nullToZero ? BigDecimal_ZERO : null;
    }

    private static String getCellStringValue(Cell cell){
        return cell.getStringValue();
    }
    
    public static String escapeCSV(String s) {
        return s == null ? "" : s.replaceAll("\"", "\"\"");
    }
    
    public static class InvoiceOutputStream {

        private Workbook workbook;
        HashMap data;

        public InvoiceOutputStream(Workbook workbook, HashMap data) {
            this.workbook = workbook;
            this.data = data;
        }

        public void save(OutputStream os) {
            try {
                for ( int i =0; i < workbook.getWorksheets().size(); i++){
                    Worksheet worksheet = workbook.getWorksheets().getSheet(i);
                    if(i==0) protectWorksheet(worksheet,"canonCSA");
                    worksheet.setFirstVisibleRow(0);
                    worksheet.setFirstVisibleColumn(0);
                }
                workbook.save(os, FileFormatType.EXCEL97TO2003);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        public void saveText(PrintWriter writer){
            
            Worksheets wss=workbook.getWorksheets();
            Worksheet ws=wss.getSheet(1);
            Cells cs=ws.getCells();
            int colCount=cs.getMaxColumn();
            int rowCount=cs.getMaxRow()-1;

            for(int row=0;row<rowCount;row++){
                for(int col=0;col<=colCount;col++){
                    Cell cell=cs.getCell(row,col);
                    String cellValue=getCellStringValue(cell);
                    if(col==0){
                        writer.print("\"" + escapeCSV(cellValue) + "\"" );
                    }else{
                        writer.print(",\"" + escapeCSV(cellValue) + "\"");
                    }
                }
                writer.println();
            }
            writer.flush();
            writer.close();
        }
        
        public HashMap getData() {
            return data;
        }
        
        public int getSize() {
            return workbook.getWorksheets().size();
        }

        public String getTextFileName() {
            return workbook.getWorksheets().getSheet(1).getName()+".txt";
        }
        
        private void  protectWorksheet(Worksheet sheet, String password){
            Protection pr = sheet.getProtection();
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
        }
        
    }

    public static class Invoice {

        private OrderGroup orderGroup;
        private ExcelBase.Formular totalAmountDue = new ExcelBase.EmptyFormular();
        private ExcelBase.Formular charges = new ExcelBase.EmptyFormular();
        private ExcelBase.Formular stateTax = new ExcelBase.EmptyFormular();
        private ExcelBase.Formular countyTax = new ExcelBase.EmptyFormular();
        private ExcelBase.Formular cityTax = new ExcelBase.EmptyFormular();
        private ExcelBase.Formular groundTotal = new ExcelBase.EmptyFormular();
        private ExcelBase.Formular totalDue = new ExcelBase.EmptyFormular();
        private Detail detail;

        public OrderGroup getOrderGroup() {
            return orderGroup;
        }

        public void setOrderGroup(OrderGroup orderGroup) {
            this.orderGroup = orderGroup;
        }

        public ExcelBase.Formular getTotalAmountDue() {
            return totalAmountDue;
        }

        public void setTotalAmountDue(ExcelBase.Formular totalAmountDue) {
            this.totalAmountDue = totalAmountDue;
        }

        public ExcelBase.Formular getCharges() {
            return charges;
        }

        public void setCharges(ExcelBase.Formular charges) {
            this.charges = charges;
        }

        public ExcelBase.Formular getCityTax() {
            return cityTax;
        }

        public void setCityTax(ExcelBase.Formular cityTax) {
            this.cityTax = cityTax;
        }

        public ExcelBase.Formular getCountyTax() {
            return countyTax;
        }

        public void setCountyTax(ExcelBase.Formular countyTax) {
            this.countyTax = countyTax;
        }

        public ExcelBase.Formular getGroundTotal() {
            return groundTotal;
        }

        public void setGroundTotal(ExcelBase.Formular groundTotal) {
            this.groundTotal = groundTotal;
        }

        public ExcelBase.Formular getStateTax() {
            return stateTax;
        }

        public void setStateTax(ExcelBase.Formular stateTax) {
            this.stateTax = stateTax;
        }

        public ExcelBase.Formular getTotalDue() {
            return totalDue;
        }

        public void setTotalDue(ExcelBase.Formular totalDue) {
            this.totalDue = totalDue;
        }

        public Detail getDetail() {
            return detail;
        }

        public void setDetail(Detail detail) {
            this.detail = detail;
        }

        public String toString() {
            return "Invoice{" + "orderGroup=" + orderGroup + ", totalAmountDue=" + totalAmountDue + ", charges=" + charges + ", stateTax=" + stateTax + ", countyTax=" + countyTax + ", cityTax=" + cityTax + ", groundTotal=" + groundTotal + ", totalDue=" + totalDue + ", detail=" + detail + '}';
        }
    }

    public static class OrderGroup {

        private List orders = new ArrayList();
        private BigDecimal stateTax;
        private BigDecimal countyTax;
        private BigDecimal cityTax;
        private ExcelBase.Formular total = new ExcelBase.EmptyFormular();
        private ExcelBase.Formular totalTax = new ExcelBase.EmptyFormular();

        public OrderGroup() {
        }

        public OrderGroup(List orders, BigDecimal stateTax, BigDecimal countyTax, BigDecimal cityTax) {
            this.orders = orders;
            this.stateTax = stateTax;
            this.countyTax = countyTax;
            this.cityTax = cityTax;
        }

        public BigDecimal getCityTax() {
            return cityTax;
        }

        public void setCityTax(BigDecimal cityTax) {
            this.cityTax = cityTax;
        }

        public BigDecimal getCountyTax() {
            return countyTax;
        }

        public void setCountyTax(BigDecimal countyTax) {
            this.countyTax = countyTax;
        }

        public List getOrders() {
            return orders;
        }

        public void setOrders(List orders) {
            this.orders = orders;
        }

        public BigDecimal getStateTax() {
            return stateTax;
        }

        public void setStateTax(BigDecimal stateTax) {
            this.stateTax = stateTax;
        }

        public ExcelBase.Formular getTotal() {
            return total;
        }

        public void setTotal(ExcelBase.Formular total) {
            this.total = total;
        }

        public ExcelBase.Formular getTotalTax() {
            return totalTax;
        }

        public void setTotalTax(ExcelBase.Formular totalTax) {
            this.totalTax = totalTax;
        }

        public void addSaleOrder(CanonCustomBillingServerInvoiceDAO.SaleInfo si){
            for (int i = 0; i < orders.size(); i++) {
                Order o = (Order) orders.get(i);
                if (o.getOrderType().equals(si.getOrdertype())) {
                    o.addSaleProduct(si);
                    return;
                }
            }
            Order o = new Order(si.getOrdertype());
            o.addSaleProduct(si);
            orders.add(o);
            
        }
        public void addOrder(CanonCustomBillingServerSummaryBean s) {
            for (int i = 0; i < orders.size(); i++) {
                Order o = (Order) orders.get(i);
                if (o.getOrderType().equals(s.getOrdertype())) {
                    o.addProduct(s);
                    return;
                }
            }
            Order o = new Order(s.getOrdertype());
            o.addProduct(s);
            orders.add(o);
        }

        public String toString() {
            return "OrderGroup{" + "orders=" + orders + ", stateTax=" + stateTax + ", countyTax=" + countyTax + ", cityTax=" + cityTax + ", total=" + total + ", totalTax=" + totalTax + '}';
        }
    }

    public static class Order {

        private String orderType;
        private List products = new ArrayList();
        private ExcelBase.Formular total = new ExcelBase.EmptyFormular();

        public Order(String orderType) {
            this.orderType = orderType;
        }

        public Order(String orderType, List products) {
            this.orderType = orderType;
            this.products = products;
        }

        public ExcelBase.Formular getTotal() {
            return total;
        }

        public void setTotal(ExcelBase.Formular total) {
            this.total = total;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public List getProducts() {
            return products;
        }

        public void setProducts(List products) {
            this.products = products;
        }

        
        public void addSaleProduct(CanonCustomBillingServerInvoiceDAO.SaleInfo si) {
            for (int i = 0; i < products.size(); i++) {
                Product p = (Product) products.get(i);
                if (p.getProductType() == si.getProducttype()) {
                    p.addSaleProductGroup(si);
                    return;
                }
            }
            Product p = new Product(si.getProducttype());
            p.addSaleProductGroup(si);
            products.add(p);

        }
        public void addProduct(CanonCustomBillingServerSummaryBean s) {
            for (int i = 0; i < products.size(); i++) {
                Product p = (Product) products.get(i);
                if (p.getProductType() == s.getProducttype()) {
                    p.addProductGroup(s);
                    return;
                }
            }
            Product p = new Product(s.getProducttype());
            p.addProductGroup(s);
            products.add(p);

        }

        public String toString() {
            return "Order{" + "orderType=" + orderType + ", products=" + products + ", total=" + total + '}';
        }
    }

    public static class Product {

        private String productType;
        private List productGroups = new ArrayList();

        public Product(String productType) {
            this.productType = productType;
        }

        public Product(String productType, List productGroups) {
            this.productType = productType;
            this.productGroups = productGroups;
        }

        public List getProductGroups() {
            return productGroups;
        }

        public void setProductGroups(List productGroups) {
            this.productGroups = productGroups;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }
        
        private boolean hideBase(CanonCustomBillingServerSummaryBean s){
            return s.getProducttype() == null 
                    || s.getProducttype().equals("NA") 
                    || ( "SALE".equals(s.getOrdertype()) || "PARTS/LABOR".equals(s.getOrdertype()) || "SUPPLY".equals(s.getOrdertype()) );
        }
        
        public void addSaleProductGroup(CanonCustomBillingServerInvoiceDAO.SaleInfo si) {
            if (si.getTotalCount().intValue() != 0) {
                productGroups.add(new ProductGroup(si.getModelName(), si.getTotalCount(), si.getTotalCharge()));
            }
        }
        
        public void addProductGroup(CanonCustomBillingServerSummaryBean s) {
            if (s.getBasecount().intValue() != 0) {
                productGroups.add(new ProductGroup(hideBase(s)? "" : "BASE", s.getBasecount(), s.getBasecharge()));
            }
            if (s.getServicecount().intValue() != 0) {
                productGroups.add(new ProductGroup(s.getOrdertype() + " SERVICE", s.getServicecount(), s.getServicecharge()));
            }
            if (s.getAttachcount().intValue() != 0) {
                productGroups.add(new ProductGroup("ATTACHMENTS", s.getAttachcount(), s.getAttachcharge()));
            }
            if (s.getUsagecount().intValue() != 0) {
                productGroups.add(new ProductGroup("USAGE", s.getUsagecount(), s.getUsagecharge()));
            }
        }

        public String toString() {
            return "Product{" + "productType=" + productType + ", productGroups=" + productGroups + '}';
        }
    }

    public static class ProductGroup {

        private String productGroupName;
        private BigDecimal quantity;
        private BigDecimal amount;

        public ProductGroup(String productGroupName, BigDecimal quantity, BigDecimal amount) {
            this.productGroupName = productGroupName;
            this.quantity = quantity;
            this.amount = amount;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getProductGroupName() {
            return productGroupName;
        }

        public void setProductGroupName(String productGroupName) {
            this.productGroupName = productGroupName;
        }

        public BigDecimal getQuantity() {
            return quantity;
        }

        public void setQuantity(BigDecimal quantity) {
            this.quantity = quantity;
        }

        public String toString() {
            return "ProductGroup{" + "productGroupName=" + productGroupName + ", quantity=" + quantity + ", amount=" + amount + '}';
        }
    }

    public static class Detail {

        List tabs = new ArrayList();
        Tab currentTab;

        public void addToTabs(CanonCustomBillingServerDetailBean d) {
            if (currentTab == null || !d.getTabName().equals(currentTab.getTabName())) {
                if (currentTab != null) {
                    currentTab.createFormulars();
                }
                currentTab = new Tab(d.getTabName());
                tabs.add(currentTab);
            }
            currentTab.addToSections(d);
        }

        public List getTabs() {
            return tabs;
        }

        public void setTabs(List tabs) {
            this.tabs = tabs;
        }

        public void createFormulars() {
            if (currentTab != null) {
                currentTab.createFormulars();
            }
        }

        public String toString() {
            return "Detail{" + "tabs=" + new ExcelBase.ToStringHelper("\n").toString(tabs) + '}';
        }
    }

    public static class Tab {

        String tabName;
        List sections = new ArrayList();
        Section currentSection;
        int rowIndex = 0;

        public Tab(String tabName) {
            this.tabName = tabName;
        }

        public List getSections() {
            return sections;
        }

        public void setSections(List sections) {
            this.sections = sections;
        }

        public String getTabName() {
            return tabName;
        }

        public void createFormulars() {
            if (currentSection != null) {
                currentSection.createFormulars(rowIndex);
            }
        }

        public void setTabName(String tabName) {
            this.tabName = tabName;
        }

        public void addToSections(CanonCustomBillingServerDetailBean l) {
            if ("HEADER".equals(l.getControlType())) {
                if (currentSection != null) {
                    currentSection.createFormulars(rowIndex);
                    rowIndex++;
                }
                currentSection = new Section();
                sections.add(currentSection);
                currentSection.setHeader(l);
            } else if ("AGGREGATE".equals(l.getControlType())) {
                if (currentSection == null) {
                    throw new NullPointerException("Invalid Control Type, expect HEADER " + l);
                }
                currentSection.setAggregate(l);
            } else if ("DETAIL".equals(l.getControlType())) {
                if (currentSection == null) {
                    throw new NullPointerException("Invalid Control Type, expect HEADER " + l);
                }
                currentSection.addLine(l);
            } else {
                throw new RuntimeException("Invalid Control Type " + l);
            }
            rowIndex++;
        }

        public void testMethod() {
            System.out.println("In testMethod. tabName is " + tabName);
        }

        public String toString() {
            return "Tab{" + "tabName=" + tabName + ", sections=" + new ExcelBase.ToStringHelper("\n").toString(sections) + '}';
        }
    }

    static final Object COLUMN_TYPE_SUM="SUM";
    static final Object COLUMN_TYPE_NUMBER="NUMBER";
    static final Object COLUMN_TYPE_STRING="STRING";
    
    public static class Section {

        private List headerList;
        private List linesList = new ArrayList();
        private List aggregateList;
        private CanonCustomBillingServerDetailBean aggregate;
        private Object[] columnTypes;
        CellArea region=null;
        
        public List getAggregate() {
            return aggregateList;
        }

        public void setAggregate(CanonCustomBillingServerDetailBean aggregate) {
            this.aggregate = aggregate;
            List l = toList(aggregate, headerList.size(), false);
            columnTypes = new Object[l.size()];
            for (int i = 0; i < l.size(); i++) {
                Object aggr = l.get(i);
                columnTypes[i]=aggr != null && ("SUM".equalsIgnoreCase(aggr.toString())) ? COLUMN_TYPE_SUM: null;
            }
        }

        private boolean isAggragate(int i) {
            return columnTypes != null && COLUMN_TYPE_SUM == columnTypes[i];
        }
        
        // expose to template engine
        public boolean isAggragate2(Number i) {
            return isAggragate(i.intValue());
        }

        public void convertTypes() {
            for(int col=0;col<columnTypes.length;col++){
                if(isNumberType(col)){
                   for(int i=0;i<linesList.size();i++){
                       List l=(List)linesList.get(i);
                       Object o=l.get(col);
                       if(o!=null && o instanceof String && isNumber((String)o)){
                           l.set(col, toBigDecimal((String) o, false));
                       }
                   }
                }
            }
        }
        
        public boolean showGrandTotal() {
            for(int col=0;col<columnTypes.length;col++){
                if(isAggragate(col)){
                    return true;
                }
            }
            return false;
        }
        
        private boolean isNumberType(int col) {
           Object type=columnTypes[col];
           if(type!=null) {
               return COLUMN_TYPE_NUMBER==type;
           }else{
               boolean is_number=true;
               boolean has_number=false;
               for(int i=0;i<linesList.size();i++){
                   List l=(List)linesList.get(i);
                   Object o=l.get(col);
                   if(o==null || o instanceof Number) continue;
                   if(o instanceof String){
                        String s=(String)o;
                        if(!isEmpty(s)){
                            boolean isnum=isNumber(s);
                            has_number=has_number||isnum;
                            if(!isnum){
                                is_number=false;
                                break;
                            }
                        }
                   }else{
                        is_number=false;
                        break;
                   }
               }
               is_number=is_number && has_number;
               columnTypes[col]=is_number? COLUMN_TYPE_NUMBER: COLUMN_TYPE_STRING;
               return is_number;
           }
           
        }
        
        // expose to template engine
        public boolean isNumberType2(Number i) {
            return isNumberType(i.intValue());
        }
        
        public void createSubtotal(Worksheet sheet){
            
            Cells cells = sheet.getCells();

            int groupBy=0;
            List l = toList(aggregate, headerList.size(), false);
            int[] sumColumns=new int[headerList.size()];
            int sumColumnCount=0;
            for (int i = 1; i < l.size(); i++) {
                String aggr = (String) l.get(i);
                if ("SUBTOTAL".equalsIgnoreCase(aggr)) {
                    groupBy=i;
                } else if ("SUM".equalsIgnoreCase(aggr)) {
                    sumColumns[sumColumnCount++]=i;
                }
            }
            int[] cols=new int[sumColumnCount];
            System.arraycopy(sumColumns, 0, cols, 0, sumColumnCount);
            cells.subtotal(region, groupBy, ConsolidationFunction.SUM, cols);
            
        }
        
        public CellArea getRegion(){
            return region;
        }
        
        private List toIntList(int [] l){
            List r=new ArrayList();
            for(int i=0;i<l.length;i++){
                r.add(new Integer(l[i]));
            }
            return r;
        }
        
        public boolean hasSubtotal() {
            List l = toList(aggregate, headerList.size(), false);
            for (int i = 1; i < l.size(); i++) {
                String aggr = (String) l.get(i);
                if ("SUBTOTAL".equalsIgnoreCase(aggr)) {
                    return true;
                }
            }
            return false;
        }
        
        public void createFormulars(int rowIndex) {
            List l = toList(aggregate, headerList.size(), false);
            aggregateList = new ArrayList();
            for (int i = 1; i < l.size(); i++) {
                String aggr = (String) l.get(i);
                if ("SUM".equalsIgnoreCase(aggr)) {
                    String formula = "SUM(" + ExcelBase.cellName(rowIndex - linesList.size() - 1, i) + ":" + ExcelBase.cellName(rowIndex - 2, i) + ")";
                    ExcelBase.Formular f = new ExcelBase.SimpleFormular(formula);
                    aggregateList.add(f);
                } else {
                    aggregateList.add(null);
                }
            }
        }

        private List toList(CanonCustomBillingServerDetailBean detail, int size, boolean convert) {
            List l = new ArrayList();
            boolean found = false;
            for (int i = 12; i < (size > 0 ? size + 12 : 100); i++) {
                String propName = "field" + i;
                Object o = ExcelBase.getPropertyValue(detail, propName);
                if (NULL_STR.equals(o)) {
                    o = null;
                }
                if (convert && isAggragate(i - 12)) {
                    o = toBigDecimal((String) o, false);
                }
                if ((size < 0 && o == null && found)) {
                    break;
                }
                if (o != null) {
                    found = true;
                }
                l.add(o);
            }
            return l;
        }

        public List getHeader() {
            return headerList;
        }

        public void setHeader(CanonCustomBillingServerDetailBean header) {
            headerList = toList(header, -1, false);
        }

        public List getLines() {
            return linesList;
        }

        public void addLine(CanonCustomBillingServerDetailBean l) {
            linesList.add(toList(l, headerList.size(), true));
        }
//        public String toString() {
//            return "Section{" + "header=" + header + ", lines=" + new ToStringHelper("\n").toString(lines) + ", aggregate=" + aggregate + '}';
//        }
        public void addCell(Cell c){
            if(region==null){
                region= canon.excel.cells.CellsHelper.newCellArea(c.getRowIndex(),c.getColumnIndex(),c.getRowIndex(),c.getColumnIndex());
            }else{
                if(c.getRowIndex()<region.getStartRow())
                    region.setStartRow(c.getRowIndex());
                if(c.getRowIndex()>region.getEndRow())
                    region.setEndRow(c.getRowIndex());
                if(c.getColumnIndex()<region.getStartColumn())
                    region.setStartColumn(c.getColumnIndex());
                if(c.getColumnIndex()>region.getEndColumn())
                    region.setEndColumn(c.getColumnIndex());
            }
        }
    }

    public static class AverageDetail {

        private List headerList;
        private List machineList = new ArrayList();

        public List getMachineList() {
            return machineList;
        }

        public List getHeader() {
            return headerList;
        }

        public void setHeader(CanonCustomBillingServerDetailBean header) {
            headerList = toList(header);
        }

        public String toString() {
            return "AverageDetail{" + "headerList=" + headerList + ", machineList=" + machineList + '}';
        }

        private List toList(Object detail) {
            List l = new ArrayList();
            boolean found = false;
            for (int i = 1; i < 100; i++) {
                String propName = "field" + i;
                Object o = ExcelBase.getPropertyValue(detail, propName);
                if (NULL_STR.equals(o)) {
                    o = null;
                }
                if (o != null && isNumber((String) o)) {
                    o = toBigDecimal((String) o, false);
                }
                if (o == null && found) {
                    break;
                }
                if (o != null) {
                    found = true;
                }
                l.add(o);
            }
            return l;
        }

        public void addToMachines(CanonCustomBillingServerDetailBean l) {
            if ("HEADER".equals(l.getControlType())) {
                setHeader(l);
            } else if ("DETAIL".equals(l.getControlType())) {
                AverageMachineLine line = new AverageMachineLine(l);
                for (int i = 0; i < machineList.size(); i++) {
                    AverageMachine m = (AverageMachine) machineList.get(i);
                    if (m.getMachineInfo().equals(line)) {
                        m.addLine(line);
                        return;
                    }
                }
                machineList.add(new AverageMachine(line));
            } else {
                throw new RuntimeException("Invalid Control Type " + l);
            }
        }

        public static class AverageMachine {

            AverageMachineLine machineInfo;
            List lineList = new ArrayList(); // list of AverageMachineLine
            private ExcelBase.Formular totalAmountDue = new ExcelBase.EmptyFormular();
//            private ExcelBase.Formular stateTax = new ExcelBase.EmptyFormular();
//            private ExcelBase.Formular countyTax = new ExcelBase.EmptyFormular();
//            private ExcelBase.Formular cityTax = new ExcelBase.EmptyFormular();
            private ExcelBase.Formular totalTax = new ExcelBase.EmptyFormular();

            public AverageMachine(AverageMachineLine machineInfo) {
                this.machineInfo = machineInfo;
                addLine(machineInfo);
            }

            public ExcelBase.Formular getTotalTax() {
                return totalTax;
            }

            public void setTotalTax(ExcelBase.Formular totalTax) {
                this.totalTax = totalTax;
            }

            public AverageMachineLine getMachineInfo() {
                return machineInfo;
            }

            public void setMachineInfo(AverageMachineLine machineInfo) {
                this.machineInfo = machineInfo;
            }

            public List getLineList() {
                return lineList;
            }

            public void setLineList(List lineList) {
                this.lineList = lineList;
            }

            public ExcelBase.Formular getTotalAmountDue() {
                return totalAmountDue;
            }

            public void setTotalAmountDue(ExcelBase.Formular totalAmountDue) {
                this.totalAmountDue = totalAmountDue;
            }

            public void addLine(AverageMachineLine line) {
                lineList.add(line);
            }

            public String toString() {
                return "AverageMachine{" + "machineInfo=" + machineInfo + ", lineList=" + lineList + ", totalAmountDue=" + totalAmountDue + '}';
            }
        }

        public class AverageMachineLine extends HashMap {

            private CanonCustomBillingServerDetailBean lineDetail;
            static final String MODEL = "MODEL";
            static final String SERIAL = "SERIAL";
            private ExcelBase.Formular total = new ExcelBase.EmptyFormular();
            private ExcelBase.Formular totalCopies = new ExcelBase.EmptyFormular();
            private ExcelBase.Formular billableCopies = new ExcelBase.EmptyFormular();

            public AverageMachineLine(CanonCustomBillingServerDetailBean lineDetail) {
                this.lineDetail = lineDetail;
                for (int i = 0; i < headerList.size(); i++) {
                    String propName = "field" + (i + 1);
                    Object o = ExcelBase.getPropertyValue(lineDetail, propName);
                    if (NULL_STR.equals(o)) {
                        o = null;
                    }
                    if (o != null && isNumber((String) o)) {
                        o = toBigDecimal((String) o, false);
                    }
                    if (o != null) {
                        String fieldname = (String) headerList.get(i);
                        put(fieldname, o);
                    }
                }
            }

            public ExcelBase.Formular getBillableCopies() {
                return billableCopies;
            }

            public void setBillableCopies(ExcelBase.Formular billableCopies) {
                this.billableCopies = billableCopies;
            }

            public ExcelBase.Formular getTotal() {
                return total;
            }

            public void setTotal(ExcelBase.Formular total) {
                this.total = total;
            }

            public ExcelBase.Formular getTotalCopies() {
                return totalCopies;
            }

            public void setTotalCopies(ExcelBase.Formular totalCopies) {
                this.totalCopies = totalCopies;
            }

            public CanonCustomBillingServerDetailBean getLineDetail() {
                return lineDetail;
            }

            public void setLineDetail(CanonCustomBillingServerDetailBean lineDetail) {
                this.lineDetail = lineDetail;
            }

            public String getModel() {
                return (String) get(MODEL);
            }

            public void setModel(String model) {
                put(MODEL, model);
            }

            public String getSerial() {
                return (String) get(SERIAL);
            }

            public void setSerial(String serial) {
                put(SERIAL, serial);
            }

            public boolean equals(Object obj) {
                if (obj == null) {
                    return false;
                }
                if (getClass() != obj.getClass()) {
                    return false;
                }
                final AverageMachineLine other = (AverageMachineLine) obj;
                if ((this.getModel() == null) ? (other.getModel() != null) : !this.getModel().equals(other.getModel())) {
                    return false;
                }
                if ((this.getSerial() == null) ? (other.getSerial() != null) : !this.getSerial().equals(other.getSerial())) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                int hash = 3;
                hash = 89 * hash + (this.getModel() != null ? this.getModel().hashCode() : 0);
                hash = 89 * hash + (this.getSerial() != null ? this.getSerial().hashCode() : 0);
                return hash;
            }
        }
    }

    private static CanonCustomBillingServerInvSearchBean toBean(CanonCustomBillingServerControlBean c) {
        CanonCustomBillingServerInvSearchBean b = new CanonCustomBillingServerInvSearchBean();
        b.setInvoiceId(c.getInvId());
        b.setUrnNumber(c.getUrnNmuber());
        boolean found = false;
        HashMap h = new HashMap();
        for (int i = 1; i < 100; i++) {
            String propName = "field" + i;
            Object o = ExcelBase.getPropertyValue(c, propName);
            if (NULL_STR.equals(o)) {
                o = null;
            }
            if (o == null && found) {
                break;
            }
            if (o != null) {
                found = true;
            }
            String propValue = (String) o;
            int splitIdx = propValue.indexOf("=");
            if (splitIdx > 0) {
                String name = propValue.substring(0, splitIdx);
                String value = propValue.substring(splitIdx + 1);
                h.put(name, value);
            }
        }
        /*
        Summary
        Type=EXCEL
        Region=WESTERN
        Biller=Nichola Secka
        BillerEmail=abc@email.com
        CustomerEmail=fgh@email.com
        OtherEmail=xyz@email.com
        ReviewRequired=Y
        AccountName=XYZ
        CustomerName=MUTUAL OF OMAHA INSURANCE CO
        BilltoLoc=123456
        BillNumber=708944682
        BillDate=5/12/2007
        InvoiceBreak=NULL
        URN=123456
        Company1=Oce Imagistics Inc. d/b/a
        Company2=Oce North America Document Printing Systems
        Phone=(203) 123 4567
        Fax=(203) 432 7654
        BillPeriod=MAY2007
        TotalAmountDue=10179.65
         */
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        b.setInvoiceType((String) h.get("Type"));
        b.setInvRegion((String) h.get("Region"));
        b.setBillerName((String) h.get("Biller"));
        b.setBillerEmail((String) h.get("BillerEmail"));
        b.setCustomerEmail((String) h.get("CustomerEmail"));
        b.setOtherEmail((String) h.get("OtherEmail"));
        b.setReviewRequired((String) h.get("ReviewRequired"));
        b.setProfileName((String) h.get("AccountName"));
        b.setCustomerName((String) h.get("CustomerName"));
        b.setCustomerGroup((String) h.get("CustomerGroup"));
        b.setParentCustomerName((String) h.get("ParentCustomer"));
        b.setBillLocation((String) h.get("BilltoLoc"));
        b.setBillNumber((String) h.get("BillNumber"));
        try {
            Date d = dateFormat.parse((String) h.get("BillDate"));
            b.setBillDate(new Timestamp(d.getTime()));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace(); 
        }
        b.setInvoiceBreak((String) h.get("InvoiceBreak"));
        b.setBillPeriod((String) h.get("BillPeriod"));
        b.setAmountDue((String) h.get("TotalAmountDue"));
        b.setCompany1((String) h.get("Company1"));
        b.setCompany2((String) h.get("Company2"));
        b.setPhone((String) h.get("Phone"));
        b.setFax((String) h.get("Fax"));
        b.setNegativeRead((String) h.get("NegativeRead"));
        b.setHighDollar((String) h.get("HighDollar"));
        b.setEmailTextData((String) h.get("EmailTextData")); 
        
        return b;
    }

    public static HashMap getData(String urnNumber) {
        HashMap data = new HashMap();
        Object[] result = CanonCustomBillingServerInvoiceDAO.getUrnInfo(urnNumber);
        CanonCustomBillingServerControlBean control = (CanonCustomBillingServerControlBean) ((List) result[0]).get(0);

        CanonCustomBillingServerRemittanceStubBean remit = (CanonCustomBillingServerRemittanceStubBean) ((List) result[1]).get(0);
        List summaryData = (List) result[3];
        CanonCustomBillingServerTaxBean taxData = (CanonCustomBillingServerTaxBean) ((List) result[4]).get(0);

        OrderGroup orderGroup = new OrderGroup();
        orderGroup.setStateTax(taxData.getStatetax());
        orderGroup.setCountyTax(taxData.getCountytax());
        orderGroup.setCityTax(taxData.getCitytax());

        for (int i = 0; i < summaryData.size(); i++) {
            CanonCustomBillingServerSummaryBean summ = (CanonCustomBillingServerSummaryBean) summaryData.get(i);
            orderGroup.addOrder(summ);
        }
        
        Object []result2=CanonCustomBillingServerInvoiceDAO.getSaleInfo(urnNumber);
        if(result2.length>0){
            List list=(List)result2[0];
            for(int i=0;i<list.size();i++){
                CanonCustomBillingServerInvoiceDAO.SaleInfo saleInfo=(CanonCustomBillingServerInvoiceDAO.SaleInfo)list.get(i);
                orderGroup.addSaleOrder(saleInfo);
            }
        }
        
        Invoice inv = new Invoice();
        inv.setOrderGroup(orderGroup);

        List detailData = (List) result[2];
        Detail detail = new Detail();
        for (int i = 0; i < detailData.size(); i++) {
            CanonCustomBillingServerDetailBean d = (CanonCustomBillingServerDetailBean) detailData.get(i);
            detail.addToTabs(d);
        }

        detail.createFormulars();
        inv.setDetail(detail);
        data.put("control", toBean(control));
        data.put("inv", inv);
        data.put("remit", remit);
        data.put("comments", result[5]);
        data.put("term", result[6]);
        return data;

    }

    public static InvoiceOutputStream createInvoice(String templateFileName, HashMap data) throws IOException {
        InputStream is= CanonCustomBillingServerInvoiceHelper.class.getResourceAsStream(templateFileName);
        Workbook newwb = ExcelBase.ExcelTemplate.getInstance(templateFileName,is).createInvoice(data);
        is.close();
        return new InvoiceOutputStream(newwb, data);

    }

    public static InvoiceOutputStream createInvoice(String urnNumber) throws Exception {
        CanonAsposeUtil.loadLicense();
        HashMap data = getData(urnNumber);
        return createInvoice(TEMPLATE_FILE, data);
    }

    public static String parseCellValue(PrintWriter pw,String s){
        Pattern pattern = Pattern.compile("<%.*?%>");
        Matcher matcher = pattern.matcher(s);
        int start=0;
        StringBuffer b=new StringBuffer();
        while (matcher.find()) {
            String f=matcher.group();
            pw.print(f.substring(2,f.length()-2));
            b.append(s.substring(start, matcher.start()));
            start=matcher.end();
        }
        if(start!=s.length()-1){
            b.append(s.substring(start, s.length()));
        }
        return b.toString();
    }

    public static void main(String args[]) throws Exception {
    	CanonCustomBillingServerUtil.printConnectionInfo();
//        HashMap data =getData("533");
//        System.out.println("data is "+data);
//        String urn="1002320";
//        String urn="547"; // subtotal
//        String urn="104"; // subtotal, 2 section
//        String urn="492"; // no subtotal
//        String urn="533"; // subtotal
//        String urn="614"; // subtotal
//        StringWriter stringWriter = new StringWriter();
//        PrintWriter writer = new PrintWriter(stringWriter);
//        String s="&item <%{ test1 } &item2 { hello } %>  <% if(){}Hello %>";
//        System.out.println(parseCellValue(writer,s));
//        if(stringWriter.getBuffer().length()>0){
//            System.out.println(stringWriter.getBuffer());
//        }
//        
//        oracle.apps.custombilling.server.CanonCustomBillingServerInvoiceHelperBase.ExcelBase.WorkBookContext context=new oracle.apps.custombilling.server.CanonCustomBillingServerInvoiceHelperBase.ExcelBase.WorkBookContext();
//        
//        MethodUtils.invokeMethod(context, "println", new Object[]{"Hello"});
//        System.out.println(MethodUtils.invokeMethod(context, "isEmpty", new Object[]{null}));
//        createInvoice("556626").save(new java.io.FileOutputStream("556626.xls"));     // has SUM
        createInvoice("614").save(new java.io.FileOutputStream("614.xls"));     // has SUM
        createInvoice("11160").save(new java.io.FileOutputStream("11160.xls")); // has SUBTOTAL
        createInvoice("11645").save(new java.io.FileOutputStream("11645.xls")); // has SUM
        createInvoice("11604").save(new java.io.FileOutputStream("11604.xls")); // No SUM
        

//        System.out.println(isNumber(null));
//        System.out.println(isNumber(""));
//        System.out.println(isNumber("abc123"));
//        System.out.println(isNumber("123abc"));
//        System.out.println(isNumber("1"));
//        System.out.println(isNumber("012323"));
//        System.out.println(isNumber("123230"));
//        System.out.println(isNumber("+12,32.30"));
//        System.out.println(isNumber("0.30"));
        
//        HashMap data= getData("533");
//        Invoice inv=(Invoice)data.get("inv");
//        System.out.println(data);
//        Detail d=inv.getDetail();
//        List tabs=d.getTabs();
//        Tab tab1=(Tab)tabs.get(0);
//        System.out.println(tab1);
//        List secs=tab1.getSections();
//        Section sec=(Section)secs.get(0);
//        System.out.println(sec);
//        System.out.println(sec.isNumberType(4));
//        System.out.println(sec.isNumberType(4));
        
//        ExcelTemplate et=ExcelTemplate.getInstance("C:\\Temp\\custombilling\\custombillingTemplate\\CanonCustomBillingServerTemplate.xls");
//        et.root.dump("");
//        Workbook wb=new Workbook();
//        wb.open("6126_3241.xls");
//        InvoiceOutputStream ios=new InvoiceOutputStream(wb,null);
//        ios.saveText(new PrintWriter(ios.getTextFileName()));
    }

    
}
