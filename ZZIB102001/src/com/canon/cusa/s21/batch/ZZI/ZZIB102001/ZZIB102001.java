package com.canon.cusa.s21.batch.ZZI.ZZIB102001;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import parts.common.EZDAbendException;
import parts.common.EZDPropertyLoader;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
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
 * Vertex Monitoring Batch Program
 * This program is created to confirm connection to WMB Server.
 * 
 * Date         Company     Name        Create/Update     Defect No
 * ------------------------------------------------------------------
 * 10/23/2017   Fujitsu     C.Ogaki      Create              N/A
 */
public class ZZIB102001 extends S21BatchMain {

    private static String proxyKey; // proxy name

    private static String propName = "s21ib.properties"; // properties file name

    private static String proxyTimeoutKey; // timeout key name

    private static String proxyEndpointKey; // endpoint key name

    private static String proxyTimeoutVal; // timeout value

    private static String proxyEndpointVal; // endpoint value

    private static TERM_CD termCd = TERM_CD.NORMAL_END; // teminate code

    private static int retryInterval = 5000; // interval time for retry

    private static int retryCnt = 3; // retry default value
    
    private static final String detailMsg = "Please see the joblog";

    /**
     * Init Routine
     */
    @Override
    protected void initRoutine() {
        // proxy key
        proxyKey = getUserVariable1();

        // retry count
        String retryCntStr = getUserVariable4();
        if (retryCntStr != null && retryCntStr.length() > 0) {
            retryCnt = Integer.parseInt(retryCntStr);
        }

        // retry interval (msec)
        String retryIntervalStr = getUserVariable5();
        if (retryIntervalStr != null && retryIntervalStr.length() > 0) {
            retryInterval = Integer.parseInt(retryIntervalStr);
        }

        proxyTimeoutKey = proxyKey + ".proxy.timeout";
        proxyEndpointKey = proxyKey + ".proxy.endpoint";

        Properties prop = ezdPropertyLoader(propName);
        proxyTimeoutVal = prop.getProperty(proxyTimeoutKey);
        proxyEndpointVal = prop.getProperty(proxyEndpointKey);

        printProperties();
    }

    /**
     * Main Routine
     */
    @Override
    protected void mainRoutine() {
        for (int i=0; i<retryCnt; i++) {
            try {
                calcTaxRequest();
            }
            catch (Exception e) {
                if (i >= retryCnt-1) {
                    S21InfoLogOutput.println("[ERROR] Detected Vertex Communication Environment Error");
                    //termCd = TERM_CD.ABNORMAL_END;

                    String eMsg = e.getMessage();
                    if (eMsg == null) {
                        eMsg = detailMsg;
                    }
                    String[] msg = {e.getClass().getSimpleName(), eMsg};
                    throw new S21AbendException(e, "ZZIM0081E", msg);
                }
                else {
                    S21InfoLogOutput.println("[WARN] Detected Vertex Communication Environment Error [" + i + "] and retrying...");
                    sleep(retryInterval);
                    continue;
                }
            }
            break;
        }
    }

    /**
     * Term Routine
     */
    @Override
    protected void termRoutine() {
        setTermState(termCd, 0, 0, 0);
    }

    /**
     * Batch Main
     * @param args
     */
    public static void main(String[] args) {
        new ZZIB102001().executeBatch(ZZIB102001.class.getSimpleName());
    }

    /**
     * Sleep
     * @param millSec
     */
    private static void sleep(long millSec){
        try {
            Thread.sleep(millSec);
        }
        catch (InterruptedException ie) {
        }
    }

    /**
     * Call Vertex API 
     * @throws RemoteException
     * @throws Exception
     */
    public void calcTaxRequest() throws RemoteException, Exception {

        S21ibVertexCalculateTaxServiceProxy proxy;
        try{
            proxy = new S21ibVertexCalculateTaxServiceProxy();

            NewOrderRequestElement vtxRequest = createTestData();

            S21InfoLogOutput.println("[Call]   calculateTax()");
            NewOrderResponseElement vtxResponse = proxy.newOrder(vtxRequest);
            S21InfoLogOutput.println("[Return] calculateTax()");

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
                if (taxRt != null) {
                    S21InfoLogOutput.println("[TransactionCombinedRate] " + (i+1) + " : " + taxRt);
                } else {
                    S21InfoLogOutput.println("[WARN] transactionCombinedRate is null");
                }
                if (taxAmount != null) {
                    S21InfoLogOutput.println("[TransactionTotalTaxAmount] " + (i+1) + " : " + taxAmount);
                } else {
                  S21InfoLogOutput.println("[WARN] transactionTotalTaxAmount is null");
                }
            }
        }
        catch (RemoteException e) {
            S21InfoLogOutput.println("[ERROR] RemoteException " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
        catch (Throwable e) {
            S21InfoLogOutput.println("[ERROR] Throwable " + e.getMessage());
            e.printStackTrace();
            throw new S21AbendException(e.getMessage());
        }
    }

    /**
     * Read Properties
     * @param pname
     * @return
     */
    private Properties ezdPropertyLoader(String pname) {
        InputStream in = EZDPropertyLoader.class.
            getClassLoader().getResourceAsStream(pname);
        Properties prop = new Properties();
        if (in == null) {
            return prop;
        }
        try {
            prop.load(new BufferedInputStream(in));
            return prop;
        } catch (IOException ex) {
            throw new EZDAbendException(ex);
        }
    }

    /**
     * Print Properties and user name
     */
    private void printProperties() {
        S21InfoLogOutput.println("<Property definition>");
        S21InfoLogOutput.println("------------------------------------------------");
        S21InfoLogOutput.println("User name = " + System.getProperty("user.name"));
        S21InfoLogOutput.println("Properties information of " + propName );
        S21InfoLogOutput.println(proxyTimeoutKey + "=" + proxyTimeoutVal);
        S21InfoLogOutput.println(proxyEndpointKey + "=" + proxyEndpointVal);
        S21InfoLogOutput.println("------------------------------------------------");
    }

   /**
    * Create Test Data
    * @return NewOrderRequestElement test data
    */
    private NewOrderRequestElement createTestData() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        
        NewOrderRequestElement vtxRequest = new NewOrderRequestElement();
        VertexEnvelopeType envelope = new VertexEnvelopeType();

        LineItemQSIType line = new LineItemQSIType();

        // Sales Date(Calendar object)
        Date date = null;
        try {
            date = dateFormat.parse("20160831");
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
        seller.setCompany("001");
        seller.setDivision("ABR");

        // Seller info2 : Ship From Address
        LocationType shipFrom = new LocationType();
        shipFrom.setCity("LAKE SUCCESS");
        shipFrom.setMainDivision("NASSAU");
        shipFrom.setCountry("US");
        shipFrom.setPostalCode("11042");
        seller.setPhysicalOrigin(shipFrom);

        // Seller info3 : Office Acceptance(Bill to)
        LocationType officeAcceptance = new LocationType();
        officeAcceptance.setCity("ARLINGTON");
        officeAcceptance.setMainDivision("ARLINGTON");
        officeAcceptance.setCountry("US");
        officeAcceptance.setPostalCode("22203");
        seller.setAdministrativeOrigin(officeAcceptance);

        // Customer Info
        CustomerType customer = new CustomerType();

        // Customer Info1 : Ship To Address
        LocationType shipTo = new LocationType();
        shipTo.setCity("SANTA FE");
        shipTo.setMainDivision("SANTA FE");
        shipTo.setCountry("US");
        shipTo.setPostalCode("87501");
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

        return vtxRequest;
    }

}
