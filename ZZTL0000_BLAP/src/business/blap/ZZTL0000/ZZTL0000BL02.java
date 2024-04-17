package business.blap.ZZTL0000;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.parts.ARS30000PMsg;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.batch.S21AsyncBatchDirectForOnline;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.printing.S21FileOutputParameter;
import com.canon.cusa.s21.framework.printing.S21OutputParameter;
import com.canon.cusa.s21.framework.printing.common.S21BOPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportParameter;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.usa.s21.integration.service.S21ibVertexCalculateTaxService.S21ibVertexCalculateTaxServiceProxy;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.AmountType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.CurrencyType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.CustomerType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.LineItemQSIType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.LineItemQSOType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.LocationType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.MeasureType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.NewOrderRequestElement;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.NewOrderResponseElement;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.Product;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.QuotationRequestType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.QuotationResponseType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.SaleTransactionType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.SellerType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.TaxesType;
import com.canon.usa.s21.integration.service.proxy.vertexCalculateTaxService.VertexGateway.VertexEnvelopeType;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2014/12/02   CUSA            Y.Suda          Create          N/A
 * 2016/09/07   Fujitsu         C.Ogaki         Update          659381
 * 2016/09/23   Fujitsu         C.Ogaki         Update          659381
 * 2017/10/17   Fujitsu         M.Yaguchi       Update          N/A
 * 2017/10/19   Fujitsu         C.Ogaki         Update          N/A
 * 2023/08/18   Fujitsu         J.Siddhartha    Update          61709  
 *</pre>
 */
public class ZZTL0000BL02 extends S21BusinessHandler {

    /** Report ID(BO) */
    private final String boRptid = "ZZTF0000";

    /** Report ID(EIP) */
    private final String eipRptid = "ZZPF0000";

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("ZZTL0000Scrn00_EIP_Validation".equals(screenAplID)) {
                doProcess_ZZTL0000Scrn00_EIP_Validation((ZZTL0000CMsg) cMsg);
            } else if ("ZZTL0000Scrn00_BO_Validation".equals(screenAplID)) {
                doProcess_ZZTL0000Scrn00_BO_Validation((ZZTL0000CMsg) cMsg);
            } else if ("ZZTL0000Scrn00_Run_All_Job".equals(screenAplID)) {
                doProcess_ZZTL0000Scrn00_Run_All_Job((ZZTL0000CMsg) cMsg);
            // 2016/09/07 C.Ogaki Add Start
            } else if ("ZZTL0000Scrn00_Vertex_Validation".equals(screenAplID)) {
                doProcess_ZZTL0000Scrn00_Vertex_Validation((ZZTL0000CMsg) cMsg);
            // 2016/09/07 C.Ogaki Add End
            // 2016/09/20 C.Ogaki Add Start
            // 2017/10/17 M.Yaguchi comment out 
//            } else if ("ZZTL0000Scrn00_Kewill_Validation".equals(screenAplID)) {
//                doProcess_ZZTL0000Scrn00_Kewill_Validation((ZZTL0000CMsg) cMsg);
            // 2016/09/20 C.Ogaki Add End
             // 2017/10/17 M.Yaguchi comment out 
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * BO validation process
     * @param cMsg
     */
    private void doProcess_ZZTL0000Scrn00_BO_Validation(ZZTL0000CMsg cMsg) {
        ZZTL0000CMsg bizMsg = (ZZTL0000CMsg) cMsg;
        S21BOPrintingService service = new S21BOPrintingService();
        
        service.open();
        S21ReportParameter param = new S21ReportParameter();
        param.setReportId(boRptid);
        param.setReportBranchNo("01");
        param.setReportTitle("BO validataion online");
        
        S21FileOutputParameter fileParam = service.getFileParameter();
        fileParam.setFormatType(S21OutputParameter.FORMAT_TYPE_PDF);
        
        byte[] pdf = service.onlineReport(null, param, null);
        if (pdf != null) {
            StringBuffer fileName = new StringBuffer();
            fileName.append(String.valueOf(System.currentTimeMillis()));
            bizMsg.xxFileData.setTempFilePath(null, fileName.toString(), ".pdf");
            ZYPFileWriter.writeFile(bizMsg.xxFileData.getTempFilePath(), pdf);
        } else {
            throw new S21AbendException("get report bytes failure");
        }
    }

    /**
     * EIP validation process
     * @param cMsg
     */
    private void doProcess_ZZTL0000Scrn00_EIP_Validation(ZZTL0000CMsg cMsg) {
        ZZTL0000CMsg bizMsg = (ZZTL0000CMsg) cMsg;

        S21EIPPrintingService service = new S21EIPPrintingService();
        S21ReportRequestBean request = new S21ReportRequestBean(eipRptid);
        request.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
        request.setRptArcFlg(true);
        request.setRptTtlNm("EIP Printing Validation: online");

        byte[] pdf = service.onlineReport(request);

        if (pdf != null) {
            StringBuffer fileName = new StringBuffer();
            fileName.append(String.valueOf(System.currentTimeMillis()));
            bizMsg.xxFileData.setTempFilePath(null, fileName.toString(), ".pdf");
            ZYPFileWriter.writeFile(bizMsg.xxFileData.getTempFilePath(), pdf);
        } else {
            throw new S21AbendException("get report bytes failure");
        }
    }

    private void doProcess_ZZTL0000Scrn00_Run_All_Job(ZZTL0000CMsg cMsg) {
        ZZTL0000CMsg bizMsg = (ZZTL0000CMsg) cMsg;

        //Direct BATCH CALL
        ARS30000PMsg ars30k = new ARS30000PMsg();
        ars30k.ProcessCondition.setValue("0"); // "0": Register
        ars30k.EZREQBusinessApplicationID.setValue("S21SWD_ZZTNR0000");
        ars30k.EZREQRegisteredDepartmentCode.setValue(getGlobalCompanyCode());
        ars30k.EZREQRegisteredUserID.setValue(getContextUserInfo().getUserId());

        S21AsyncBatchDirectForOnline drequest = new S21AsyncBatchDirectForOnline("S21SWD_ZZTNR0000");
        S21AsyncBatchDirectForOnline.EXIT_CODE returnCode = drequest.executeBatch(ars30k);

        if (S21AsyncBatchDirectForOnline.EXIT_CODE.WARN_RUNNINGJOB.equals(returnCode)) {

            cMsg.setMessageInfo("NATM0001W");

        } else if (S21AsyncBatchDirectForOnline.EXIT_CODE.ERROR_REQUESTREGIST.equals(returnCode)) {

            bizMsg.setMessageInfo("ZZBM0048E");

        } else {
            bizMsg.setMessageInfo("ZZKM0005I", new String[] {"Run all jobs."});
        }
    }

// 2016/09/07 C.Ogaki Add Start
    /**
     * Vertex validation process
     * @param cMsg
     */
    private void doProcess_ZZTL0000Scrn00_Vertex_Validation(ZZTL0000CMsg cMsg) {
            //2017/10/19 C.Ogaki Add Start
        // Vertex O Series
        ZZTL0000CMsg bizMsg = (ZZTL0000CMsg) cMsg;
        S21ibVertexCalculateTaxServiceProxy proxy;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

            NewOrderRequestElement vtxRequest = new NewOrderRequestElement();
            VertexEnvelopeType envelope = new VertexEnvelopeType();

            LineItemQSIType line = new LineItemQSIType();
                 
             String glblCmpyCd = getGlobalCompanyCode();
            // Sales Date(Calendar object)
            Date date = null;
            try {
                String saleDate = ZYPDateUtil.getSalesDate();
                if(saleDate.isEmpty()){
                	saleDate = "20230702";
                }
                date = dateFormat.parse(saleDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // Currency
            CurrencyType currency = new CurrencyType();
            currency.setIsoCurrencyCodeAlpha("USD");

            // Seller Info
            SellerType seller = new SellerType();
            
            // Seller info1 : Company, Division
            seller.setCompany("ADB");
            //seller.setDivision("CUSA");

            // Seller info2 : Ship From Address
            LocationType shipFrom = new LocationType();
            shipFrom.setCity(ZYPCodeDataUtil.getVarCharConstValue("ZZTL0000_VERTEX_SHIP_FR_CITY", glblCmpyCd));
            shipFrom.setMainDivision(ZYPCodeDataUtil.getVarCharConstValue("ZZTL0000_VERTEX_SHIP_FR_STATE", glblCmpyCd));
            shipFrom.setCountry(ZYPCodeDataUtil.getVarCharConstValue("ZZTL0000_VERTEX_SHIP_FR", glblCmpyCd));
            shipFrom.setPostalCode(ZYPCodeDataUtil.getVarCharConstValue("ZZTL0000_VERTEX_SHIP_FR_POSCD", glblCmpyCd));
            seller.setPhysicalOrigin(shipFrom);

            // Seller info3 : Office Acceptance(Bill to)
            LocationType officeAcceptance = new LocationType();
            officeAcceptance.setCity(ZYPCodeDataUtil.getVarCharConstValue("ZZTL0000_VERTEX_OA_CITY", glblCmpyCd));
            officeAcceptance.setMainDivision(ZYPCodeDataUtil.getVarCharConstValue("ZZTL0000_VERTEX_OA_STATE", glblCmpyCd));
            officeAcceptance.setCountry(ZYPCodeDataUtil.getVarCharConstValue("ZZTL0000_VERTEX_OA", glblCmpyCd));
            officeAcceptance.setPostalCode(ZYPCodeDataUtil.getVarCharConstValue("ZZTL0000_VERTEX_OA_POSCD", glblCmpyCd));
            seller.setAdministrativeOrigin(officeAcceptance);

            // Customer Info
            CustomerType customer = new CustomerType();

            // Customer Info1 : Ship To Address
            LocationType shipTo = new LocationType();
            shipTo.setCity(ZYPCodeDataUtil.getVarCharConstValue("ZZTL0000_VERTEX_SHIP_TO_CITY", glblCmpyCd));
            shipTo.setMainDivision(ZYPCodeDataUtil.getVarCharConstValue("ZZTL0000_VERTEX_SHIP_TO_STATE", glblCmpyCd));
            shipTo.setCountry(ZYPCodeDataUtil.getVarCharConstValue("ZZTL0000_VERTEX_SHIP_TO", glblCmpyCd));
            shipTo.setPostalCode(ZYPCodeDataUtil.getVarCharConstValue("ZZTL0000_VERTEX_SHIP_TO_POSCD", glblCmpyCd));
            customer.setDestination(shipTo);

          // Line Number
            line.setLineItemNumber(new BigInteger("1"));

            // Transaction Type
            line.setTransactionType(SaleTransactionType.SALE);

            // ProductCode
            Product product = new Product();
            product.set_value("4814B004AA");
            line.setProduct(product);

            // Quantity
            MeasureType lineQty = new MeasureType();
            lineQty.set_value(new BigDecimal("1"));
            line.setQuantity(lineQty);

            // Unit Price
            AmountType unitPrice = new AmountType();
            unitPrice.set_value(new BigDecimal("0"));
            line.setUnitPrice(unitPrice);

            // Extended Amount
            AmountType extAmount = new AmountType();
            extAmount.set_value(new BigDecimal("100"));
            line.setExtendedPrice(extAmount);
            
            List<LineItemQSIType> lineList = new ArrayList<LineItemQSIType>();
            lineList.add(line);

            // Create Vertex Request
            QuotationRequestType quoRequest = new QuotationRequestType();
            quoRequest.setDocumentNumber("1");
            quoRequest.setDocumentDate(calendar);
            quoRequest.setTransactionId("1");
            quoRequest.setTransactionType(SaleTransactionType.SALE);
            quoRequest.setCurrency(currency);
            quoRequest.setSeller(seller);
            quoRequest.setCustomer(customer);
            quoRequest.setLineItem((LineItemQSIType[]) lineList.toArray(new LineItemQSIType[0]));

            envelope.setQuotationRequest(quoRequest);
            vtxRequest.setVertexEnvelope(envelope);

            proxy = new S21ibVertexCalculateTaxServiceProxy();
            NewOrderResponseElement vtxResponse = proxy.newOrder(vtxRequest);

            BigDecimal taxRt = new BigDecimal(0);
            BigDecimal taxAmount = new BigDecimal(0);
            QuotationResponseType quoResponse = vtxResponse.getVertexEnvelope().getQuotationResponse();
            LineItemQSOType[] vtxLineArray = quoResponse.getLineItem();
            for (int i=0; i < vtxLineArray.length; i++) {
                TaxesType[] taxes = vtxLineArray[i].getTaxes();
                for (int j = 0; j < taxes.length; j++) {
                    if (ZYPCommonFunc.hasValue(taxes[j].getJurisdiction().getJurisdictionLevel().getValue())) {
                        taxAmount = taxAmount.add(taxes[j].getCalculatedTax().get_value());
                        taxRt = taxRt.add(taxes[j].getEffectiveRate());
                    }
                }
            }
            bizMsg.acctTaxAmt.setValue(taxRt.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
        }
        catch (RemoteException e) {
            throw new S21AbendException(e);
        }
        catch (Throwable e) {
            e.printStackTrace();
            throw new S21AbendException("Unexpected Error");
        }
        //2017/10/19 C.Ogaki Add End
    }

}
