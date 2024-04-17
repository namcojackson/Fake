package com.canon.cusa.s21.batch.ZZI.ZZIB101001;

import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

//import java.rmi.RemoteException;
//import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.S21CreditCardProcessingServiceProxy;
//import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.exception.ConfigurationException;
//import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.exception.DataException;
//import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.exception.ResourceException;
//import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.exception.TargetSystemException;
//import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.paymentechGateway.ProfileAddElement;
//import com.canon.usa.s21.integration.service.proxy.creditCardProcessingService.paymentechGateway.ProfileResponseElement;

/**
 * Paymentech Access Confirmation Batch Program
 *  This program is created to confirm connection to WMB Server.
 * 
 * Date         Company     Name        Create/Update     Defect No
 * ---------------------------------------------------------------
 * 2/16/2012    Fujitsu     K.Oida      Create              N/A
 */

public class ZZIB101001 extends S21BatchMain {
    
    @Override
    protected void initRoutine() {

    }

    /**
     * Main Routine
     * Call paymentech API(Profile Add) with invalid data
     */
    protected void mainRoutine() {
//        try {
//            
//            S21InfoLogOutput.println("Call Paymentech API : Profile Add");
//            
//            // Call : Profile Add
//            S21CreditCardProcessingServiceProxy s21CreditCardProcessingServiceProxy = new S21CreditCardProcessingServiceProxy();
//            ProfileAddElement addProfileBean = new ProfileAddElement();
//            ProfileResponseElement profileResponse = null;
//            profileResponse = s21CreditCardProcessingServiceProxy.profileAdd(addProfileBean);   
//
//            // Response from Paymentech
//            S21InfoLogOutput.println(profileResponse.toString());
//            
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        } catch (ConfigurationException e) {
//            e.printStackTrace();
//        } catch (ResourceException e) {
//            e.printStackTrace();
//        } catch (TargetSystemException e) {
//            e.printStackTrace();
//        } catch (DataException e) {
//            e.printStackTrace();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Term Routine
     */
    protected void termRoutine() {
        // This program always end normally.
        setTermState(TERM_CD.NORMAL_END, 0, 0,0);
    }

    /**
     * Batch Main
     * @param args
     */
    public static void main(String[] args) {
           new ZZIB101001().executeBatch(ZZIB101001.class.getSimpleName());
    }
}
