package oracle.apps.e580.itt.workbench;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import canon.apps.common.CanonAsposeUtil;
import canon.excel.cells.FileFormatType;
import canon.excel.cells.PageOrientationType;
import canon.excel.cells.PageSetup;
import canon.excel.cells.PaperSizeType;
import canon.excel.cells.PdfCompliance;
import canon.excel.cells.Protection;
import canon.excel.cells.Workbook;
import canon.excel.cells.Worksheet;
import oracle.apps.jtf.aom.transaction.TransactionScope;
import parts.common.EZDPMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDDBCICarrier;

public class CanonE580ITTWorkbenchPOHelper extends CanonE580ITTWorkbenchPOHelperBase {

    static final String NULL_STR = "null";
    public static final String TEMPLATE_FILE="CanonITTWBTemplate.xls";
    static final String base64Template="";
    // [+-]?
    // (?:
    //  (?:0)|                              Zero
    //  (?:[123456789][\\d,]*\\d*)|         Integer
    //  (?:[\\d,]*\\.\\d*)                  Number with decimal places
    // )
    static final Pattern PARRETN_DEIMAL_NUM = Pattern.compile("[+-]?(?:(?:0)|(?:[123456789][\\d,]*\\d*)|(?:[\\d,]*\\.\\d*))");
    static final String ACCESSORY="ACCESSORY";

    private static String expenseItemCode(){
    	return (String)CanonE580ITTWorkbenchUtil.first(CanonE580ITTWorkbenchDAO.getExpenseItem());
    }

    private static boolean isNumber(String str) {
        if(isEmpty(str))return false;
        Matcher matcher = PARRETN_DEIMAL_NUM.matcher(str);
        return matcher.matches();
    }
    
    private static boolean isEmpty(String s){
        return s==null || s.trim().length()==0;
    }
    
    public static BigDecimal BigDecimal_ZERO = CanonE580ITTWorkbenchUtil.BigDecimal_ZERO;

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

    public static class POPDFOutputStream {

        private Workbook workbook;
        HashMap data;

        public POPDFOutputStream(Workbook workbook, HashMap data) {
            this.workbook = workbook;
            this.data = data;
        }

        public void save(OutputStream os) {
            try {            	
                for ( int i =0; i < workbook.getWorksheets().size(); i++){
                    Worksheet worksheet = workbook.getWorksheets().getSheet(i);
//                    protectWorksheet(worksheet,"canonCSA");
                    worksheet.setFirstVisibleRow(0);
                    worksheet.setFirstVisibleColumn(0);
                }
                workbook.save(os, FileFormatType.EXCEL97TO2003);                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        public void savePDF(OutputStream os) {
            try {            	
                for ( int i =0; i < workbook.getWorksheets().size(); i++){
                    Worksheet worksheet = workbook.getWorksheets().getSheet(i);
                    PageSetup pageSetup = worksheet.getPageSetup();
                    worksheet.setFirstVisibleRow(0);
                    worksheet.setFirstVisibleColumn(0);
                    pageSetup.setPaperSize(PaperSizeType.PAGE_LETTER);
                    pageSetup.setOrientation(PageOrientationType.PORTRAIT);
                    pageSetup.setLeftMargin(.8);
                    pageSetup.setRightMargin(1);
                    pageSetup.setTopMargin(.9);
                    pageSetup.setBottomMargin(.9);                    
                }
                
//                workbook.getSaveOptions().setPdfCompliance(PdfCompliance.PDFA1B);
                workbook.savePDF(os, FileFormatType.PDF, PdfCompliance.PDFA1B );                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        public HashMap getData() {
            return data;
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

    public static class PurchaseOrder {

        private String purchaseOrderNumber;
        private String csaOracleOrderNumber;
        private String branch;
        private Date date;
        private String deliveryDateRequired;
        private ITTAdmin ittAdmin;
        private Dealer dealer;
        private ShipToCustomer shipToCustomer;
        private Map modelSummarys=new HashMap();
        private Map modelDetails=new HashMap();
        private List expenseLines=new ArrayList();
        private String mailInvoicesTo;
        private String additionalInstructions; 
        private ExcelBase.Formular totalQuantity = new ExcelBase.EmptyFormular();
        private ExcelBase.Formular totalExtendedCost = new ExcelBase.EmptyFormular();
        private ExcelBase.Formular totalInstallCredit = new ExcelBase.EmptyFormular();
        private ExcelBase.Formular totalFinderFee = new ExcelBase.EmptyFormular();
        private ExcelBase.Formular totalDealerInstallComp = new ExcelBase.EmptyFormular();
        private ExcelBase.Formular total = new ExcelBase.EmptyFormular();
        private ExcelBase.Formular groundTotal = new ExcelBase.EmptyFormular();
        private ExcelBase.Formular totalDueToDealer = new ExcelBase.EmptyFormular();
        private ExcelBase.Formular expenseTotal = new ExcelBase.EmptyFormular();
        final String EXPENSE_LINE_ITEM_CODE=expenseItemCode();
        private boolean isEss=true;

        public void addMaintenanceInfo(CanonE580ITTWorkbenchDAO.PDFMaintInfo maint) throws Exception{
            String model=maint.getEquipModel();
            ModelDetail modelDetail=(ModelDetail)modelDetails.get(model);
            if(modelDetail==null){
                modelDetail=new ModelDetail(model);
                modelDetails.put(model, modelDetail);
            }
            ServiceAgreement agreement=modelDetail.getServiceAgreement();
            isEss="ESS".equalsIgnoreCase(maint.getLob());
            agreement.setBasePrice(maint.getBasePrice());
            agreement.addMeterDetail(maint.getMeterType(), maint.getCopyInclusion(), maint.getOverageRate(), maint.getMultiplier());
            agreement.setTerm(maint.getTerm());
            agreement.setBaseBillingCycle(CanonE580ITTWorkbenchUtil.nonNullify(maint.getBaseBillCycle()));
            agreement.setOverageBillingCycle(CanonE580ITTWorkbenchUtil.nonNullify(maint.getOverageBillCycle()));
            agreement.setServiceType(CanonE580ITTWorkbenchUtil.nonNullify(maint.getContractType()));

            ModelSummary modelSummary=(ModelSummary)modelSummarys.get(maint.getEquipModel());
            if(modelSummary==null){
                modelSummary=new ModelSummary(model);
                modelSummarys.put(model, modelSummary);
            }
            modelSummary.setContractType(maint.getContractType());
            modelSummary.setTerm(maint.getTerm());
        }
                
        public void addLine(CanonE580ITTWorkbenchDAO.PDFLineInfo l){
            if (EXPENSE_LINE_ITEM_CODE.equals(l.getItem())){
                addExpenseLine(l);
            }else{
                String model=l.getEquipModel()==null? ACCESSORY : l.getEquipModel();
                ModelSummary modelSummary=(ModelSummary)modelSummarys.get(model);
                if(modelSummary==null){
                    modelSummary=new ModelSummary(model);
                    modelSummarys.put(model, modelSummary);
                }
                modelSummary.summaryUp(l.getOrderedQuantity().intValue(), 
                            CanonE580ITTWorkbenchUtil.toBigDecimal(l.getItemPurchasePrice(),true),
                            l.getInstallCredit(),
                            CanonE580ITTWorkbenchUtil.toBigDecimal(l.getFinderFee(),true),
                            l.getMerchandise(),
                            l.getCountFlag());

                ModelDetail modelDetail=(ModelDetail)modelDetails.get(model);
                if(modelDetail==null){
                    modelDetail=new ModelDetail(model);
                    modelDetails.put(model, modelDetail);
                }

                modelDetail.addGoodLine(l);
            }
        }

        public void addExpenseLine(CanonE580ITTWorkbenchDAO.PDFLineInfo line){
            BigDecimal cost=CanonE580ITTWorkbenchUtil.toBigDecimal(line.getItemPurchasePrice(), true);
            String item=CanonE580ITTWorkbenchUtil.nonNullify(line.getItemDescription());
            expenseLines.add(new ExpenseLine(line.getOrderedQuantity().intValue(),item,cost));
        }
        
		
		public boolean getIsEss() {
			return isEss;
		}
        
        public Dealer getDealer() {
            return dealer;
        }

        public void setDealer(Dealer dealer) {
            this.dealer = dealer;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
        
        public ShipToCustomer getShipToCustomer() {
            return shipToCustomer;
        }

        public void setShipToCustomer(ShipToCustomer shipToCustomer) {
            this.shipToCustomer = shipToCustomer;
        }

        public String getPurchaseOrderNumber() {
            return purchaseOrderNumber;
        }

        public void setPurchaseOrderNumber(String purchaseOrderNumber) {
            this.purchaseOrderNumber = purchaseOrderNumber;
        }
        
        public String getDeliveryDateRequired() {
            return deliveryDateRequired;
        }

        public void setDeliveryDateRequired(String deliveryDateRequired) {
            this.deliveryDateRequired = deliveryDateRequired;
        }

        public ITTAdmin getIttAdmin() {
            return ittAdmin;
        }

        public void setIttAdmin(ITTAdmin ittAdmin) {
            this.ittAdmin = ittAdmin;
        }

        public Map getModelSummarys() {
            return modelSummarys;
        }

        public List getModelSummaryList() {
            List l=new ArrayList();
            for(Iterator i=modelSummarys.entrySet().iterator();i.hasNext();){
                Map.Entry e=(Map.Entry)i.next();
                if(ACCESSORY!=e.getKey()){
                    l.add(e.getValue());
                }
            }
            Collections.sort(l, new Comparator(){
                public int compare(Object o1, Object o2) {
                    ModelSummary m1=(ModelSummary) o1;
                    ModelSummary m2=(ModelSummary) o2;
                    return m1.getModel().compareTo(m2.getModel());
                }
                
            });
            for(Iterator i=modelSummarys.entrySet().iterator();i.hasNext();){
                Map.Entry e=(Map.Entry)i.next();
                if(ACCESSORY==e.getKey()){
                    l.add(e.getValue());
                }
            }
            return l;
        }

        public List getModelDetailList() {
            List l=new ArrayList();
            for(Iterator i=modelDetails.entrySet().iterator();i.hasNext();){
                Map.Entry e=(Map.Entry)i.next();
                if(ACCESSORY!=e.getKey()){
                    l.add(e.getValue());
                }
            }
            Collections.sort(l, new Comparator(){
                public int compare(Object o1, Object o2) {
                    ModelDetail m1=(ModelDetail) o1;
                    ModelDetail m2=(ModelDetail) o2;
                    return m1.getModel().compareTo(m2.getModel());
                }
            });
            for(Iterator i=modelDetails.entrySet().iterator();i.hasNext();){
                Map.Entry e=(Map.Entry)i.next();
                if(ACCESSORY==e.getKey()){
                    l.add(e.getValue());
                }
            }
            return l;
        }
        
        private void addDealer(CanonE580ITTWorkbenchDAO.PDFHeaderInfo header){
            this.dealer=new Dealer(header.getDealer(),
                    header.getLocation(),
                    header.getDealerContact(),
                    header.getDealerContactNumber(),
                    header.getDealerEmail(),
                    CanonE580ITTWorkbenchUtil.phoneNumber(header.getDlrFaxAreaCode(), header.getDlrFax()),
                    CanonE580ITTWorkbenchUtil.nonNullify(header.getDealerShipToCnaCode()));
        }
        
        private void addShipToCustomer(CanonE580ITTWorkbenchDAO.PDFHeaderInfo header){
            this.shipToCustomer=new ShipToCustomer(header.getShipToCustomer(),
                    header.getAddress1(),
                    header.getAddress2(),
                    header.getCity(),
                    header.getState(),
                    header.getPostalCode(),
                    header.getCustomerContact(),
                    header.getCustomerContactPhone(),
                    header.getCustomerContactFax(),
                    header.getCustomerContactEmail());
        }

        private void addIttAdmin(CanonE580ITTWorkbenchDAO.PDFHeaderInfo header){
                this.ittAdmin=new ITTAdmin(header.getIttAdminName(), 
                header.getIttAdminPhone(), 
                header.getIttAdminFax(), 
                header.getIttAdminEmail());
        }
        
        public void setModelSummarys(Map modelSummarys) {
            this.modelSummarys = modelSummarys;
        }

        public ExcelBase.Formular getTotalQuantity() {
            return totalQuantity;
        }

        public void setTotalQuantity(ExcelBase.Formular totalQuantity) {
            this.totalQuantity = totalQuantity;
        }

        public ExcelBase.Formular getTotalExtendedCost() {
            return totalExtendedCost;
        }

        public void setTotalExtendedCost(ExcelBase.Formular totalExtendedCost) {
            this.totalExtendedCost = totalExtendedCost;
        }

        public ExcelBase.Formular getTotalInstallCredit() {
            return totalInstallCredit;
        }

        public void setTotalInstallCredit(ExcelBase.Formular totalInstallCredit) {
            this.totalInstallCredit = totalInstallCredit;
        }

        
        public ExcelBase.Formular getTotalFinderFee() {
            return totalFinderFee;
        }

        public void setTotalFinderFee(ExcelBase.Formular totalFinderFee) {
            this.totalFinderFee = totalFinderFee;
        }

        public ExcelBase.Formular getTotal() {
            return total;
        }

        public void setTotal(ExcelBase.Formular total) {
            this.total = total;
        }

        public ExcelBase.Formular getGroundTotal() {
            return groundTotal;
        }

        public void setGroundTotal(ExcelBase.Formular groundTotal) {
            this.groundTotal = groundTotal;
        }

        public ExcelBase.Formular getTotalDueToDealer() {
            return totalDueToDealer;
        }

        public void setTotalDueToDealer(ExcelBase.Formular totalDueToDealer) {
            this.totalDueToDealer = totalDueToDealer;
        }
        
        public List getExpenseLines(){
            return expenseLines;
        }

        public ExcelBase.Formular getExpenseTotal() {
            return expenseTotal;
        }

        public void setExpenseTotal(ExcelBase.Formular expenseTotal) {
            this.expenseTotal = expenseTotal;
        }

        public ExcelBase.Formular getTotalDealerInstallComp() {
            return totalDealerInstallComp;
        }

        public void setTotalDealerInstallComp(ExcelBase.Formular totalDealerInstallComp) {
            this.totalDealerInstallComp = totalDealerInstallComp;
        }

        public String getMailInvoicesTo() {
            return mailInvoicesTo;
        }

        public void setMailInvoicesTo(String mailInvoicesTo) {
            this.mailInvoicesTo = mailInvoicesTo;
        }

        public String getAdditionalInstructions() {
            return additionalInstructions;
        }

        public void setAdditionalInstructions(String additionalInstructions) {
            this.additionalInstructions = additionalInstructions;
        }

        public String getCsaOracleOrderNumber() {
            return csaOracleOrderNumber;
        }

        public void setCsaOracleOrderNumber(String csaOracleOrderNumber) {
            this.csaOracleOrderNumber = csaOracleOrderNumber;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

    }

    public static class ModelSummary{
        private String model;
        private int quantity=0;
        private String contractType;
        private BigDecimal term;
        private BigDecimal extendedCost=BigDecimal_ZERO;
        private BigDecimal finderFee=BigDecimal_ZERO;
        private BigDecimal installCredit=BigDecimal_ZERO;

        public ModelSummary(String model){
            this.model=model;
        }
        
        
        public void summaryUp(int quatity,BigDecimal cost,BigDecimal installCredit, BigDecimal finderFee,String merchandise,String countFlag){
            if(GoodLine.isMachine(merchandise,countFlag)){
                this.quantity=this.quantity+quatity;
            }
            BigDecimal qty=new BigDecimal(quatity);
            if(cost!=null) this.extendedCost=this.extendedCost.add(cost.multiply(qty));
            if(installCredit!=null) this.installCredit=this.installCredit.add(installCredit.multiply(qty));
            if(finderFee!=null) this.finderFee=this.finderFee.add(finderFee.multiply(qty));
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getContractType() {
            return contractType;
        }

        public void setContractType(String contractType) {
            this.contractType = contractType;
        }

        public BigDecimal getTerm() {
            return term;
        }

        public void setTerm(BigDecimal term) {
            this.term = term;
        }

        public BigDecimal getExtendedCost() {
            return extendedCost;
        }

        public void setExtendedCost(BigDecimal extendedCost) {
            this.extendedCost = extendedCost;
        }

        public BigDecimal getFinderFee() {
            return finderFee;
        }

        public void setFinderFee(BigDecimal finderFee) {
            this.finderFee = finderFee;
        }

        public BigDecimal getInstallCredit() {
            return installCredit;
        }

        public void setInstallCredit(BigDecimal installCredit) {
            this.installCredit = installCredit;
        }

    }

    public static class ShipToCustomer{
        private String name;
        private String address1;
        private String address2;
        private String city;
        private String state;
        private String zip;
        private String contact;
        private String phone;
        private String fax;
        private String email;

        public ShipToCustomer(){
        }
        
        public ShipToCustomer(String name, String address1, String address2, String city, String state, String zip, String contact, String phone,String fax, String email) {
            this.name = name;
            this.address1 = address1;
            this.address2 = address2;
            this.city = city;
            this.state = state;
            this.zip = zip;
            this.contact = contact;
            this.phone = phone;
            this.fax=fax;
            this.email=email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String toString() {
            return "ShipToCustomer{" + "name=" + name + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", state=" + state + ", zip=" + zip + ", contact=" + contact + ", phone=" + phone + ", fax=" + fax + ", email=" + email + '}';
        }
        
        
    }
    
    public static class ITTAdmin{
        private String name;
        private String phone;
        private String fax;
        private String email;

        public ITTAdmin(String name, String phone, String fax, String email) {
            this.name = name;
            this.phone = phone;
            this.fax = fax;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String toString() {
            return "ITTAdmin{" + "name=" + name + ", phone=" + phone + ", fax=" + fax + ", email=" + email + '}';
        }
        
    }
    

    public static class Dealer {
        private String name;
        private String location;
        private String contact;
        private String phone;
        private String email;
        private String fax;
        private String dealerShipToCnaCode;

        public Dealer(){
        }

        public Dealer(String name, String location, String contact, String phone, String email, String fax, String dealerShipToCnaCode) {
            this.name = name;
            this.location = location;
            this.contact = contact;
            this.phone = phone;
            this.email = email;
            this.fax = fax;
            this.dealerShipToCnaCode = dealerShipToCnaCode;
        }
        
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }
        public String getDealerShipToCnaCode() {
            return dealerShipToCnaCode;
        }

        public void setDealerShipToCnaCode(String dealerShipToCnaCode) {
            this.dealerShipToCnaCode = dealerShipToCnaCode;
        }
        
        public String toString() {
            return "Dealer{" + "name=" + name + ", location=" + location + ", contact=" + contact + ", phone=" + phone + ", email=" + email + ", fax=" + fax + '}';
        }
        
    }
    
    static int FIRST_PAGE_GOOD_LINE_SIZE=1000;
    static int ADDITION_PAGE_SIZE=1000;
    public static class ModelDetail {
        private ServiceAgreement serviceAgreement=new ServiceAgreement();
        private String model;
        private List first=new ArrayList();
        private List additional=new ArrayList();
        private ExcelBase.Formular total = new ExcelBase.EmptyFormular();

        public ModelDetail(String model) {
            this.model = model;
        }
        
        public void addGoodLine(CanonE580ITTWorkbenchDAO.PDFLineInfo line){
            if(first.size()<FIRST_PAGE_GOOD_LINE_SIZE){
                first.add(new GoodLine(line.getOrderedQuantity().intValue(),
                    line.getItemPurchasePrice(),
                    CanonE580ITTWorkbenchUtil.isEmpty(line.getItem())? CanonE580ITTWorkbenchUtil.nonNullify(line.getItemDescription()):
                    CanonE580ITTWorkbenchUtil.nonNullify(line.getItem()) + " - "+CanonE580ITTWorkbenchUtil.nonNullify(line.getItemDescription()),
                    line.getMerchandise(),
                    line.getCountFlag()
                    ));
            }else{
                List last=null;
                if(additional.size()==0){
                    last=new ArrayList();
                    additional.add(last);
                }else{
                    last=(List)additional.get(additional.size()-1);
                }
                if(last.size()>=ADDITION_PAGE_SIZE){
                    last=new ArrayList();
                    additional.add(last);
                }
                last.add(new GoodLine(line.getOrderedQuantity().intValue(),
                        line.getItemPurchasePrice(),
                        CanonE580ITTWorkbenchUtil.isEmpty(line.getItem())? CanonE580ITTWorkbenchUtil.nonNullify(line.getItemDescription()):
                        CanonE580ITTWorkbenchUtil.nonNullify(line.getItem()) + " - "+CanonE580ITTWorkbenchUtil.nonNullify(line.getItemDescription()),
                        line.getMerchandise(),
                        line.getCountFlag()
                        ));
                
            }
            
        }

        public ServiceAgreement getServiceAgreement() {
            return serviceAgreement;
        }

        public void setServiceAgreement(ServiceAgreement serviceAgreement) {
            this.serviceAgreement = serviceAgreement;
        }
        
        public List getAdditionLines() {
            return additional;
        }

        public void setAdditionLines(List additionLines) {
            this.additional = additionLines;
        }
        
        public List getLines() {
            return first;
        }

        public void setLines(List lines) {
            this.first = lines;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public List getFirst() {
            return first;
        }

        public void setFirst(List first) {
            this.first = first;
        }

        public List getAdditional() {
            return additional;
        }

        public void setAdditional(List additional) {
            this.additional = additional;
        }

        public ExcelBase.Formular getTotal() {
            return total;
        }

        public void setTotal(ExcelBase.Formular total) {
            this.total = total;
        }

        public String toString() {
            return "ModelDetail{" + "serviceAgreement=" + serviceAgreement + ", model=" + model + ", first=" + first + ", additional=" + additional + ", total=" + total + '}';
        }

    }

    public static class ExpenseLine {
        private int qty;
        private String item;
        private BigDecimal unitCost=BigDecimal_ZERO;
        private BigDecimal extendedCost=BigDecimal_ZERO;

        public ExpenseLine(int qty, String item,BigDecimal unitCost) {
            this.qty = qty;
            this.item = item;
            this.unitCost=unitCost;
            if(unitCost!=null){
                BigDecimal q=new BigDecimal(qty);
                this.extendedCost=unitCost.multiply(q);
            }    
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public BigDecimal getExtendedCost() {
            return extendedCost;
        }

        public void setExtendedCost(BigDecimal extendedCost) {
            this.extendedCost = extendedCost;
        }

        public BigDecimal getUnitCost() {
            return unitCost;
        }

        public void setUnitCost(BigDecimal unitCost) {
            this.unitCost = unitCost;
        }

        public String toString() {
            return "ExpenseLine{" + "qty=" + qty + ", item=" + item + ", unitCost=" + unitCost + ", extendedCost=" + extendedCost + '}';
        }
        
    }

    public static class GoodLine {

        private int qty;
        private String unitCost;
        private String item;
        private String merchandise;
        private String countFlag;
        
        private ExcelBase.Formular extendedCost = new ExcelBase.EmptyFormular();

        public GoodLine(){
        };
        public GoodLine(int qty, String unitCost,String item,String merchandise,String countFlag) {
            this.qty = qty;
            this.unitCost = unitCost;
            this.item = item;
            this.merchandise= merchandise;
            this.countFlag=countFlag;
        }

        public String getMerchandise() {
            return merchandise;
        }

        public void setMerchandise(String merchandise) {
            this.merchandise = merchandise;
        }
        
        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public String getUnitCost() {
            return unitCost;
        }

        public void setUnitCost(String unitCost) {
            this.unitCost = unitCost;
        }

        public ExcelBase.Formular getExtendedCost() {
            return extendedCost;
        }

        public void setExtendedCost(ExcelBase.Formular extendedCost) {
            this.extendedCost = extendedCost;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }
        
        public boolean productSourcedByCSA(){
            return "Shipping to Dealer".equals(unitCost);
        }
        
        public boolean isMachine(){
            return isMachine(merchandise,countFlag);
        }
        
        public static boolean isMachine(String merchandise,String countFlag){
            return ("10".equals(merchandise) || "11".equals(merchandise)) && "Y".equals(countFlag);
        }

        
        public String toString() {
            return "Line{" + "qty=" + qty + ", unitCost=" + unitCost + ", extendedCost=" + extendedCost + ", item=" + item + '}';
        }

    }
    
    public static class additionLine {
        String name;
        List lines;

        public additionLine(String name, List lines) {
            this.name = name;
            this.lines = lines;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List getLines() {
            return lines;
        }

        public void setLines(List lines) {
            this.lines = lines;
        }

        public String toString() {
            return "additionLine{" + "name=" + name + ", lines=" + lines + '}';
        }
        
    }


    public static class ServiceAgreement {
        private String serviceType;
        private BigDecimal bwBasePrice;
        private BigDecimal clrBasePrice;
        private BigDecimal bwCopyInclusion;
        private BigDecimal clrCopyInclusion;
        private BigDecimal bwOverageRate;
        private BigDecimal clrOverageRate;
        private String meterSetup;
        private BigDecimal term;
        private String baseBillingCycle;
        private String overageBillingCycle;
        private String mailInvoiceTo;
        private List meterDetails=new ArrayList();
        private BigDecimal basePrice;

        public ServiceAgreement(){
        }
        
        
        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public BigDecimal getBwBasePrice() {
            return bwBasePrice;
        }

        public void setBwBasePrice(BigDecimal bwBasePrice) {
            this.bwBasePrice = bwBasePrice;
        }

        public BigDecimal getClrBasePrice() {
            return clrBasePrice;
        }

        public void setClrBasePrice(BigDecimal clrBasePrice) {
            this.clrBasePrice = clrBasePrice;
        }

        public BigDecimal getBwCopyInclusion() {
            return bwCopyInclusion;
        }

        public void setBwCopyInclusion(BigDecimal bwCopyInclusion) {
            this.bwCopyInclusion = bwCopyInclusion;
        }

        public BigDecimal getClrCopyInclusion() {
            return clrCopyInclusion;
        }

        public void setClrCopyInclusion(BigDecimal clrCopyInclusion) {
            this.clrCopyInclusion = clrCopyInclusion;
        }

        public BigDecimal getBwOverageRate() {
            return bwOverageRate;
        }

        public void setBwOverageRate(BigDecimal bwOverageRate) {
            this.bwOverageRate = bwOverageRate;
        }

        public BigDecimal getClrOverageRate() {
            return clrOverageRate;
        }

        public void setClrOverageRate(BigDecimal clrOverageRate) {
            this.clrOverageRate = clrOverageRate;
        }

        public String getMeterSetup() {
            return meterSetup;
        }

        public void setMeterSetup(String meterSetup) {
            this.meterSetup = meterSetup;
        }

        public BigDecimal getTerm() {
            return term;
        }

        public void setTerm(BigDecimal term) {
            this.term = term;
        }


        public String getMailInvoiceTo() {
            return mailInvoiceTo;
        }

        public void setMailInvoiceTo(String mailInvoiceTo) {
            this.mailInvoiceTo = mailInvoiceTo;
        }

        public String getBaseBillingCycle() {
            return baseBillingCycle;
        }

        public void setBaseBillingCycle(String baseBillingCycle) {
            this.baseBillingCycle = baseBillingCycle;
        }

        public String getOverageBillingCycle() {
            return overageBillingCycle;
        }

        public void setOverageBillingCycle(String overageBillingCycle) {
            this.overageBillingCycle = overageBillingCycle;
        }

		public List getMeterDetails() {
			return meterDetails;
		}

		public void setMeterDetails(List meterDetails) {
			this.meterDetails=meterDetails;
		}
		
		public BigDecimal getBasePrice() {
			return basePrice;
		}

		public void setBasePrice(BigDecimal basePrice) {
			this.basePrice = basePrice;
		}


		public void addMeterDetail(String meterType, BigDecimal copyInclusion, BigDecimal overageRate, BigDecimal multiplier) {
			meterDetails.add(new MeterDetail(meterType,copyInclusion,overageRate,multiplier));
		}

		public String toString() {
			return "ServiceAgreement [serviceType=" + serviceType + ", bwBasePrice=" + bwBasePrice + ", clrBasePrice="
					+ clrBasePrice + ", bwCopyInclusion=" + bwCopyInclusion + ", clrCopyInclusion=" + clrCopyInclusion
					+ ", bwOverageRate=" + bwOverageRate + ", clrOverageRate=" + clrOverageRate + ", meterSetup="
					+ meterSetup + ", term=" + term + ", baseBillingCycle=" + baseBillingCycle
					+ ", overageBillingCycle=" + overageBillingCycle + ", mailInvoiceTo=" + mailInvoiceTo
					+ ", meterDetails=" + meterDetails + ", basePrice=" + basePrice + "]";
		}
		
    }
    
    public static class MeterDetail {
        private String meterType;
        private BigDecimal copyInclusion;
        private BigDecimal overageRate;
        private BigDecimal multiplier;
        
		public MeterDetail(String meterType, BigDecimal copyInclusion, BigDecimal overageRate, BigDecimal multiplier) {
			this.meterType = meterType;
			this.copyInclusion = copyInclusion;
			this.overageRate = overageRate;
			this.multiplier = multiplier;
		}
		public String getMeterType() {
			return meterType;
		}
		public void setMeterType(String meterType) {
			this.meterType = meterType;
		}
		public BigDecimal getCopyInclusion() {
			return copyInclusion;
		}
		public void setCopyInclusion(BigDecimal copyInclusion) {
			this.copyInclusion = copyInclusion;
		}
		public BigDecimal getOverageRate() {
			return overageRate;
		}
		public void setOverageRate(BigDecimal overageRate) {
			this.overageRate = overageRate;
		}
		public BigDecimal getMultiplier() {
			return multiplier;
		}
		public void setMultiplier(BigDecimal multiplier) {
			this.multiplier = multiplier;
		}
		
		public String toString() {
			return "MeterDetail [meterType=" + meterType + ", copyInclusion=" + copyInclusion + ", overageRate="
					+ overageRate + ", multiplier=" + multiplier + "]";
		}
        
    }

    
    public static HashMap getData(String ittNumber,String lineNums, String userName) throws Exception {
    	System.out.println("getData "+ittNumber+"|"+ lineNums+"|"+userName );
        PurchaseOrder po=getPurchaseOrder(ittNumber,lineNums,userName);
        HashMap data = new HashMap();
        data.put("po", po);
        return data;
    }
    
    public static PurchaseOrder getPurchaseOrder(String ittNumber,String lineNums, String userName) throws Exception {
        PurchaseOrder po=new PurchaseOrder();
        po.setDate(new Date());
        
        Object[] result=CanonE580ITTWorkbenchDAO.ittCreatePoPdf(ittNumber,lineNums,userName);
        
        CanonE580ITTWorkbenchDAO.PDFHeaderInfo header=CanonE580ITTWorkbenchUtil.first(result)==null? null: (CanonE580ITTWorkbenchDAO.PDFHeaderInfo)CanonE580ITTWorkbenchUtil.first(CanonE580ITTWorkbenchUtil.first(result));
        List lines=CanonE580ITTWorkbenchUtil.second(result)==null? Collections.EMPTY_LIST: (List)CanonE580ITTWorkbenchUtil.second(result);
        List maints=CanonE580ITTWorkbenchUtil.third(result)==null? Collections.EMPTY_LIST: (List)CanonE580ITTWorkbenchUtil.third(result);        
        String x_addnl_instr=result.length>6? CanonE580ITTWorkbenchUtil.nonNullify((String) result[6]) : "";
        po.setPurchaseOrderNumber(header.getIttNumber());
        po.setCsaOracleOrderNumber(header.getOrderNumber());
        po.setBranch(header.getSalesBranch());
        po.setAdditionalInstructions( x_addnl_instr.length()>500? x_addnl_instr.substring(0, 500) :  x_addnl_instr );
        po.setDeliveryDateRequired(CanonE580ITTWorkbenchUtil.formatDate(header.getScheduledDeliveryDate()));
        po.addDealer(header);
        po.addIttAdmin(header);
        po.addShipToCustomer(header);
        po.setMailInvoicesTo(CanonE580ITTWorkbenchUtil.nonNullify(header.getMailInvoicesTo()));
        for(int i=0;i<lines.size();i++){
            CanonE580ITTWorkbenchDAO.PDFLineInfo line=(CanonE580ITTWorkbenchDAO.PDFLineInfo )lines.get(i);
            po.addLine(line);
        }
        for(int i=0;i<maints.size();i++){
            CanonE580ITTWorkbenchDAO.PDFMaintInfo maint=(CanonE580ITTWorkbenchDAO.PDFMaintInfo)maints.get(i);
            po.addMaintenanceInfo(maint);
        }
//        System.out.println("model deails "+po.getModelDetailList());
        return po;
    }

    public static POPDFOutputStream createPOPDFOutputStream(String templateFileName, HashMap data) throws IOException {
        InputStream templateInputStream=getTemplateDataSrc(templateFileName);
    	Workbook newwb = ExcelBase.ExcelTemplate.getInstance(templateFileName,templateInputStream, 300).createInvoice(data);        
        return new POPDFOutputStream(newwb, data);

    }
    public static String BSD_PATH = "CANON_E473_BSD_PATH";
    public static String ITT_PATH = "CANON_E580_ITT_PATH";
    public static String ASPOSE_LIC_FILE = "Aspose.Total.Java.lic";
    
    public static POPDFOutputStream createPOPDFOutputStream(String ittNumber,String lineNums, String userName,String contextPath) throws Exception {    	
    	CanonAsposeUtil.loadLicense();
//        HashMap data = getData(ittNumber, lineNums, userName);
        PurchaseOrder po=getPurchaseOrder(ittNumber,lineNums,userName);
        HashMap data = new HashMap();
        data.put("po", po);
        System.out.println("After get Data + isEss "+po.getIsEss());
        return createPOPDFOutputStream(TEMPLATE_FILE, data);
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

    
//    public static void main(String args[]) throws Exception {
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
//        createInvoice("614").save(new FileOutputStream("614.xls"));
//        createInvoice("533").save(new FileOutputStream("533.xls"));
//        createInvoice("10128").save(new FileOutputStream("10128.xls"));
//        createInvoice("2888").save(new java.io.FileOutputStream("2888.xls"));

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
//    }
    
        
//    public static void main(String args[]) throws Exception {
////        test("IT-20012441-01");
//        test("IT-20012695-01");
//        test("IT-20012439-01");
//        test("IT-20012942-01");
//    }
//
//  public static void test(String ittNumber) throws Exception {
//      String pdfFileName=ittNumber+".pdf";
//      createPOPDFOutputStream(ittNumber,null,"Q05058","").savePDF(new java.io.FileOutputStream(pdfFileName));
//  }
    
//    public static void test(String ittNumber) throws Exception {
//        HashMap data=getData(ittNumber,null,"Jun Wang");
//        String xlsFileName=ittNumber+".xls";
//        String pdfFileName=ittNumber+".pdf";
//        createPOPDFOutputStream(TEMPLATE_FILE, data).savePDF(new java.io.FileOutputStream(pdfFileName));
//    }
    
    static InputStream getTemplateDataSrc(String templateFileName){
    	System.out.println("in getTemplateDataSrc");
    	try {
            return CanonE580ITTWorkbenchPOHelper.class.getResourceAsStream(templateFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }

    public static CanonE580ITTWorkbenchCallback<Object> s21APIDummyCallBack() {
    	return new S21APIDummyCallBack();
    }

    public static class S21APIDummyCallBack implements CanonE580ITTWorkbenchCallback<Object>{
		@Override
		public void onSuccess(Object... obj) {
		}
		@Override
		public void onError(String error) {
		}
		@Override
		public void onFinally() {
		}
		@Override
		public void init(Object... objs) {
		};
	}
    
	
	static class CanonE580HoldReleaseApiCallBack implements CanonE580ITTWorkbenchCallback<Object>{
		CanonE580ITTWorkbenchCallback<Object> daocallback=null;
		CanonE580HoldReleaseApiCallBack(CanonE580ITTWorkbenchCallback<Object> daocallback){
			this.daocallback=daocallback;
		}
		@Override
		public void onSuccess(Object... objs) {
			List<String[]> tmpList=(List<String[]>)objs[0];
			for(String[] item:tmpList) {
				String p_itt_number=item[0];
				String so_number = item[1];
				String line_number = item[2];
				String loginUser = item[3];		
				CanonE580ITTWorkbenchDAO.dealerInstallConfirm(
						p_itt_number, so_number, line_number,
						loginUser, null,daocallback);
			}
			
		}
		
		/**
		 * No necessary to save the error, since we roll back everything on errors.
		 */
		@Override
		public void onError(String error) {
		}
		
		@Override
		public void onFinally() {
		}

		@Override
		public void init(Object... objs) {
		}
		
	}
    static final String ERROR="ERROR : An application processing error occurred.";
    static final String SUCCESS="Success";
    
    public static class DealerInstallConfirmAPI implements CanonE580ITTWorkbenchS21Api{
		public String[] createServicerequest(final HttpServletRequest request) {
			/*
			 *  Calling 3 api in sequence. api1->api2->api3
			 *  
			 *	1. NLZC405001 - Update Delivery/Pickup Schedule from eCarriers API - CanonE580CarrierS21Api
			 *	2. NWZC033001 - Hold Release API - CanonE580HoldReleaseApi
			 *	3. NSZC001001 - Machine Master Update API	- CanonE580MachineMasterUpdateApi			   	   
			 *
			 */
			final String[] err=new String[2];
			Connection connection=null;
			
			try {
				connection=TransactionScope.getConnection();
				connection.setAutoCommit(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(connection==null) {
				err[0]=ERROR;
				err[1]="A database error occured.";
				logMsg(err[0]+"|"+err[1]);
				return err;
			}
			
            logMsg("custom apps connection "+connection);
			
			/**
			 * handling database transactions and cleanup resources. 
			 */
			final CanonE580ITTWorkbenchCallback<Object> daoCallbackProxy=new DefaultDaoCallback(connection) {
				@Override
				public void onError(String error) {
					logMsg("daoCallbackProxy.onError "+error);
					super.onError(error);// roll back transaction
					// set error flag
					err[1]=error;
				}
			};
			
			/**
			 * handling the release hold api transaction life cycle or onError, daoCallbackProxy do the real work.
			 */
			CanonE580ITTWorkbenchCallback<Object> dummyDaoCallback=new DefaultDaoCallback(connection) {
			 	@Override
				public void onSuccess(Object... t) {
				}

				@Override
				public void onError(String error) {
					logMsg("dummyDaoCallback.onError "+error);
					// set error flag
					err[1]=error;
				}

				@Override
				public void onFinally() {
				}
			};
			
			/**
			 * last api in the chain, handle everything except init.
			 */
			final CanonE580ITTWorkbenchCallback<Object> callback3=new S21APIDummyCallBack(){
				boolean foundError=false;
				@Override
				public void onError(String error) {
					logMsg("callback3.onError "+error);
					foundError=true;
					err[1]=error;
					/*
					 * no rollback here since commit may invoke this too.
					 * see onFinally 
					 */
				}
				@Override
				public void onFinally() {
					if(foundError) {
						daoCallbackProxy.onError(err[1]);
						logMsg("s21 ezd conn mgr roll back");
						EZDConnectionMgr.getInstance().rollback();
					}else {
						/**
						 *  commit transaction, invoke onError if commit fails
						 */
						daoCallbackProxy.onSuccess(); 
						if(err[1]==null) {
							/** see daoCallbackProxy.onError
							 * check dao error after commit.
							 */
							logMsg("s21 ezd conn mgr commit");
							EZDConnectionMgr.getInstance().commit();
						}else {
							logMsg("s21 ezd conn mgr roll back");
							EZDConnectionMgr.getInstance().rollback();
						}
					}
				}

			};
			/**
			 * second api in the chain, only handle errors, no onSuccess to commit nor onFianlly to release the resources
			 */
			final CanonE580ITTWorkbenchCallback<Object> callback2=new CanonE580HoldReleaseApiCallBack(dummyDaoCallback){
				@Override
				public void onSuccess(Object... obj) {
					super.onSuccess(obj);
					// check dao error on save.
					if(err[1]!=null) {
						onError("Database Error");
					}else {
						new CanonE580MachineMasterUpdateApi(callback3).createServicerequest(request);
					}
				}
				@Override
				public void onError(String error) {
					// super onError has nothing to do.
					logMsg("callback2.onError "+error);
					daoCallbackProxy.onError(error);
					logMsg("s21 ezd conn mgr roll back");
					EZDConnectionMgr.getInstance().rollback();
					err[1]=error;
				}
			};
			
			/**
			 * first api in the chain, handle errors, and initial api. no onSuccess to commit nor onFianlly to release the resources
			 */
			final CanonE580ITTWorkbenchCallback<Object> callback1=new S21APIDummyCallBack(){
				@Override
				public void init(Object... obj) {
					String loginUser=(String)obj[0];
					String invokeTimestamp=(String)obj[1];
					String timezone=(String)obj[2];
					String userCompanyCode=(String)obj[3];
					String progID=(String)obj[4];
					EZDDBCICarrier.initOnline(loginUser, invokeTimestamp, timezone, userCompanyCode);
					EZDDBCICarrier.setProgID(progID);
				}
				@Override
				public void onSuccess(Object... obj) {
					new CanonE580HoldReleaseApi(callback2).createServicerequest(request);
				}
				@Override
				public void onError(String error) {
					logMsg("callback1.onError "+error);
					logMsg("s21 ezd conn mgr roll back");
					EZDConnectionMgr.getInstance().rollback();
					// at this time, database transaction is not started yet. so no roll back on databases.
					err[1]=error;
				}
			};
			
			try {
				new CanonE580CarrierS21Api(callback1).createServicerequest(request);
			}catch(Exception ex) {
				ex.printStackTrace();
				logMsg("s21 ezd conn mgr roll back");
				EZDConnectionMgr.getInstance().rollback();
				err[1]="System Error";
			}finally {
				logMsg("s21 ezd conn mgr release resource");
				EZDConnectionMgr.getInstance().releaseResource();
				daoCallbackProxy.onFinally(); // release db resources
			}
			err[0]=err[1]==null? SUCCESS :ERROR;
			logMsg(err[0]+"|"+err[1]);
			
			return err;
		}
    	
    }
    
    public static class DefaultDaoCallback implements CanonE580ITTWorkbenchCallback<Object>, CanonE580ITTWorkbenchDAO.ConnectionAware {
 	   Connection connection=null;
 	   public DefaultDaoCallback(Connection connection) {
 		   this.connection=connection;
 	   }
	 	   
	 	@Override
	 	public void init(Object... obj) {
	 	}
	
	 	/**
	 	 * success handling, call onError if commit failed.
	 	 */
	 	@Override
	 	public void onSuccess(Object... t) {
	 		try {
	            logMsg("custom apps commit transaction");
	 			connection.commit();
	 		} catch (SQLException e) {
	 			e.printStackTrace();
	 			onError(e.getMessage());
	 		}
	 	}
	
	 	@Override
	 	public void onError(String error) {
	 		try {
	 			logMsg("custom apps rollback transaction");
	 			connection.rollback();
	 		} catch (SQLException e) {
	 			e.printStackTrace();
	 		}
	 		
	 	}
	
	 	@Override
	 	public void onFinally() {
	      if (connection != null) {
	          try {
	        	  logMsg("custom apps release connection");
	              TransactionScope.releaseConnection(connection);
	          } catch (Exception ex) {
	              ex.printStackTrace();
	          }
	      }
	      
	 	}
	
		public Connection getConnection() {
			return connection;
		}
 	
 	   
    }
    
	public static String getEZDError(EZDPMsg pmsg){
		if (S21ApiUtil.isXxMsgId(pmsg)) {
			StringBuffer sb = new StringBuffer("");
			List<String> msgList = S21ApiUtil.getXxMsgIdList(pmsg);
			for (String msg : msgList) {
				sb.append(S21MessageFunc.clspGetMessage(msg) + "\n");
			}
			return sb.toString();
		}else {
			return null;
		}
	}
	
    
	public static String getEZDError(List<?> l){
		List<EZDPMsg> pMsgList=(List<EZDPMsg>)l;
		LinkedHashSet<String> set1=new LinkedHashSet<String>();
		for(EZDPMsg pmsg: pMsgList) {
			String err=getEZDError(pmsg);
			if(err!=null && !set1.contains(err)) {
				set1.add(err);
			}
			
		}
		if(set1.isEmpty()) {
			return null;
		}else {
			return join(set1.iterator(),',');	
		}
	}
	
	private static final String EMPTY="";
	public static String join(final Iterator<?> iterator, final char separator) {

	    // handle null, zero and one elements before building a buffer
	    if (iterator == null) {
	        return null;
	    }
	    if (!iterator.hasNext()) {
	        return EMPTY;
	    }
	    final Object first = iterator.next();
	    if (!iterator.hasNext()) {
	        return first==null? "":first.toString();
	    }

	    // two or more elements
	    final StringBuilder buf = new StringBuilder(256); // Java default is 16, probably too small
	    if (first != null) {
	        buf.append(first);
	    }

	    while (iterator.hasNext()) {
	        buf.append(separator);
	        final Object obj = iterator.next();
	        if (obj != null) {
	            buf.append(obj);
	        }
	    }

	    return buf.toString();
	}
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void logMsg(String str) {
		System.out.println("[e580] [" + sdf.format(Calendar.getInstance().getTime()) + "] : " + str);
	}
    
}