package com.canon.cusa.s21.api.NWZ.NWZC229001;


import java.rmi.RemoteException;

import org.apache.log4j.Logger;


import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.S21CreditCardProcessingCpgmServiceProxy;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.exception.ConfigurationException;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.exception.DataException;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.exception.ResourceException;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.exception.TargetSystemException;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.paymentechGateway.NewOrderRequestElement;
import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.cpgm.paymentechGateway.NewOrderResponseElement;

/**
 * <pre> 
 *  This class is the Test Module Class to check behavior of CPGM I/F jar.
 *  
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/19/2023   Hitachi         M.Hashino       Create          QC#55645
 *</pre>
 */

public class PaymentechCpgmTransaction {
    private static Logger LOGGER = Logger.getLogger(PaymentechCpgmTransaction.class);

    S21CreditCardProcessingCpgmServiceProxy s21CreditCardProcessingCpgmServiceProxy;
    public PaymentechCpgmTransaction(){
        s21CreditCardProcessingCpgmServiceProxy = new S21CreditCardProcessingCpgmServiceProxy();
    }


    /* achTransaction */
    public NewOrderResponseElement achTransaction()
        {
        LOGGER.info("ACH Transaction");
        PaymentechTimestamp ts;
        ts = PaymentechTimestamp.getInstance();
        LOGGER.info("ACH ts:" + ts);
        System.out.println("ACH ts set.." + ts);

        NewOrderResponseElement authResponse = null;

        NewOrderRequestElement achBean = new NewOrderRequestElement();
        LOGGER.info("ACH achBean:" + achBean);
        System.out.println("ACH achBean 1..");
        achBean.setIndustryType(PaymentechConstants.INDUSTRY_TYPE);
        achBean.setTransType("AC"); //not EC
        achBean.setBin(PaymentechConstants.BIN);
        achBean.setMerchantID(PaymentechConstants.CPGM_MERCHANT_ID);
        achBean.setTerminalID(PaymentechConstants.TERMINAL_ID);
        achBean.setCardBrand(PaymentechConstants.INDUSTRY_TYPE);
        achBean.setCcCardVerifyPresenceInd("1");
        achBean.setEcpCheckRT("601100000");
        achBean.setEcpCheckDDA("6011000000000004");
        achBean.setEcpBankAcctType("X");
        achBean.setEcpAuthMethod("I");
        achBean.setEcpDelvMethod("A");
        achBean.setAvsZip("44135-1304");
        achBean.setAvsName("PNC BANK, NATIONAL ASSOCIATION");
        achBean.setAddProfileFromOrder("A");
        achBean.setProfileOrderOverideInd("NO");
        achBean.setOrderID("10000131" + Long.toString(ts.getNextOrderID()));
        achBean.setAmount("239441");
        achBean.setRetryTrace(achBean.getOrderID());
        achBean.setCustomerEmail("deborah.hurtig@pnc.com");
        achBean.setComments(achBean.getOrderID() + " Test Web Service ACH Transaction");
        LOGGER.info("ACH achBean set:");
        System.out.println("ACH achBean set..");

        try {
            s21CreditCardProcessingCpgmServiceProxy = new S21CreditCardProcessingCpgmServiceProxy();
            System.out.println("new S21CreditCardProcessingCpgmServiceProxy..");
            //PaymentechHelper.displayGetters(authBean);
            authResponse = s21CreditCardProcessingCpgmServiceProxy.newOrder(achBean);
            System.out.println("new S21CreditCardProcessingCpgmServiceProxy..newOrder...");

            System.out.println(authResponse.getAuthorizationCode());

            System.out.println("NewOrderResponseElement getApprovalStatus:" + authResponse.getApprovalStatus());
            System.out.println("NewOrderResponseElement getAuthorizationCode:" + authResponse.getAuthorizationCode());
            System.out.println("NewOrderResponseElement getAvsRespCode:" + authResponse.getAvsRespCode());
            System.out.println("NewOrderResponseElement getBin:" + authResponse.getBin());
            System.out.println("NewOrderResponseElement getCardBrand:" + authResponse.getCardBrand());
            System.out.println("NewOrderResponseElement getCcAccountNum:" + authResponse.getCcAccountNum());
            System.out.println("NewOrderResponseElement getCountryFraudFilterStatus:" + authResponse.getCountryFraudFilterStatus());
            System.out.println("NewOrderResponseElement getCustomerName:" + authResponse.getCustomerName());
            System.out.println("NewOrderResponseElement getCustomerRefNum:" + authResponse.getCustomerRefNum());
            System.out.println("NewOrderResponseElement getCvvRespCode:" + authResponse.getCvvRespCode());
            System.out.println("NewOrderResponseElement getDebitBillerReferenceNumber:" + authResponse.getDebitBillerReferenceNumber());
            System.out.println("NewOrderResponseElement getDebitPinNetworkID:" + authResponse.getDebitPinNetworkID());
            System.out.println("NewOrderResponseElement getDebitPinSurchargeAmount:" + authResponse.getDebitPinSurchargeAmount());
            System.out.println("NewOrderResponseElement getDebitPinTraceNumber:" + authResponse.getDebitPinTraceNumber());
            System.out.println("NewOrderResponseElement getFraudScoreProcMsg:" + authResponse.getFraudScoreProcMsg());
            System.out.println("NewOrderResponseElement getFraudScoreProcStatus:" + authResponse.getFraudScoreProcStatus());
            System.out.println("NewOrderResponseElement getGiftCardInd:" + authResponse.getGiftCardInd());
            System.out.println("NewOrderResponseElement getHostAVSRespCode:" + authResponse.getHostAVSRespCode());
            System.out.println("NewOrderResponseElement getHostCVVRespCode:" + authResponse.getHostCVVRespCode());
            System.out.println("NewOrderResponseElement getHostRespCode:" + authResponse.getHostRespCode());
            System.out.println("NewOrderResponseElement getIndustryType:" + authResponse.getIndustryType());
            System.out.println("NewOrderResponseElement getIsoCountryCode:" + authResponse.getIsoCountryCode());
            System.out.println("NewOrderResponseElement getLastRetryDate:" + authResponse.getLastRetryDate());
            System.out.println("NewOrderResponseElement getMbMicroPaymentDaysLeft:" + authResponse.getMbMicroPaymentDaysLeft());
            System.out.println("NewOrderResponseElement getMbMicroPaymentDollarsLeft:" + authResponse.getMbMicroPaymentDollarsLeft());
            System.out.println("NewOrderResponseElement getMbStatus:" + authResponse.getMbStatus());
            System.out.println("NewOrderResponseElement getMcRecurringAdvCode:" + authResponse.getMcRecurringAdvCode());
            System.out.println("NewOrderResponseElement getMerchantID:" + authResponse.getMerchantID());
            System.out.println("NewOrderResponseElement getOrderID:" + authResponse.getOrderID());
            System.out.println("NewOrderResponseElement getPartialAuthOccurred:" + authResponse.getPartialAuthOccurred());
            System.out.println("NewOrderResponseElement getProcStatus:" + authResponse.getProcStatus());
            System.out.println("NewOrderResponseElement getProcStatusMessage:" + authResponse.getProcStatusMessage());
            System.out.println("NewOrderResponseElement getProfileProcStatus:" + authResponse.getProfileProcStatus());
            System.out.println("NewOrderResponseElement getProfileProcStatusMsg:" + authResponse.getProfileProcStatusMsg());
            System.out.println("NewOrderResponseElement getRedeemedAmount:" + authResponse.getRedeemedAmount());
            System.out.println("NewOrderResponseElement getRemainingBalance:" + authResponse.getRemainingBalance());
            System.out.println("NewOrderResponseElement getRequestAmount:" + authResponse.getRequestAmount());
            System.out.println("NewOrderResponseElement getRespCode:" + authResponse.getRespCode());
            System.out.println("NewOrderResponseElement getRespCodeMessage:" + authResponse.getRespCodeMessage());
            System.out.println("NewOrderResponseElement getRespDateTime:" + authResponse.getRespDateTime());
            System.out.println("NewOrderResponseElement getRetryAttempCount:" + authResponse.getRetryAttempCount());
            System.out.println("NewOrderResponseElement getRetryTrace:" + authResponse.getRetryTrace());
            System.out.println("NewOrderResponseElement getTerminalID:" + authResponse.getTerminalID());
            System.out.println("NewOrderResponseElement getTransType:" + authResponse.getTransType());
            System.out.println("NewOrderResponseElement getTxRefIdx:" + authResponse.getTxRefIdx());
            System.out.println("NewOrderResponseElement getTxRefNum:" + authResponse.getTxRefNum());
            System.out.println("NewOrderResponseElement getVersion:" + authResponse.getVersion());
            System.out.println("NewOrderResponseElement getVisaVbVRespCode:" + authResponse.getVisaVbVRespCode());
            System.out.println("NewOrderResponseElement getAvsPhone:" + authResponse.getAvsPhone());

            //PaymentechHelper.displayGetters(authResponse);
            return authResponse;
        } catch (RemoteException e) {
            LOGGER.error("RemoteException " , e);
            e.printStackTrace();
            return null;
        } catch (ConfigurationException e) {
            LOGGER.error("ConfigurationException ", e);
            return null;
        } catch (ResourceException e) {
            LOGGER.error("ResourceException ", e);
            return null;
        } catch (TargetSystemException e) {
            LOGGER.error("TargetSystemException ", e);
            return null;
        } catch (DataException e) {
            LOGGER.error("DataException ", e);
            return null;
        }
    }


    public static void main(String[] args) {
        PaymentechCpgmTransaction PaymentechTransaction = new PaymentechCpgmTransaction();
        try {
            LOGGER.info("Start S21 Paymentech Proxy Test.");
            PaymentechTransaction.achTransaction();
            //newOrderResponse = PaymentechTransaction.returnCreditCard(); 
            //ProfileResponseElement profileResponse = PaymentechTransaction.addProfile();
            //ProfileResponseElement profileResponse1 = PaymentechTransaction.fetchProfile();
            //ProfileResponseElement profileResponse2 = PaymentechTransaction.deleteProfile();
            //ProfileResponseElement profileResponse3 = PaymentechTransaction.changeProfile();
            //newOrderResponse = PaymentechTransaction.AuthCCardwithProfile();
            LOGGER.info("S21 Paymentech Proxy Test Complete.");
        } catch (Exception e) {
            LOGGER.error("S21 Paymentech Proxy Test Failed.", e);
            e.printStackTrace();
        }
    }

}

