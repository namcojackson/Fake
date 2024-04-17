/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB104001;

import javax.xml.ws.soap.SOAPFaultException;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.usa.s21.integration.walmart.nsbi1320.request.ObjectFactory;
import com.canon.usa.s21.integration.walmart.nsbi1320.request.S21ApiToWalmartWebhookUnSubscRequest;
import com.canon.usa.s21.integration.walmart.nsbi1320.response.Error;
import com.canon.usa.s21.integration.walmart.nsbi1320.response.S21ApiToWalmartWebhookUnSubscResponse;
import com.canon.usa.s21.integration.walmart.nsbi1320.response.WebhookUnSubscError;
import com.canon.usa.s21.integration.walmart.nsbi1320.wrapper.S21ApiToWalmartWebhookUnSubscProxy;

/**
 * Walmart API Outbound Interface Connectivity Test
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 2024/03/08   CSAI           K.Lee            Update          
 *</pre>
 */
public class NSBB104001 extends S21BatchMain {

    /** Normal Counter */
    private int normCnt = 0;

    /** Error Counter */
    private int errCnt = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Term Code */
    private TERM_CD termCd = null;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB104001().executeBatch(NSBB104001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.termCd = TERM_CD.NORMAL_END;

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException("ZZZM9025E", new String[] {"Global Company Code" });
        }
    }

    @Override
    protected void mainRoutine() {

        ObjectFactory objFactory = new ObjectFactory();
        S21ApiToWalmartWebhookUnSubscRequest request = objFactory.createS21ApiToWalmartWebhookUnSubscRequest();

        // set parameter
        request.setWebhook("");
        request.setTableName("");
        request.setSysId("");

        S21ApiToWalmartWebhookUnSubscProxy proxy = new S21ApiToWalmartWebhookUnSubscProxy();
        S21ApiToWalmartWebhookUnSubscResponse response = null;

        try {
            // Call WMB
            response = proxy.webhookUnSubscRequest(request);
            String errorMessage = null;
            if (response != null) {
                WebhookUnSubscError webhookUnSubscError = response.getWebhookUnSubscError();
                if (webhookUnSubscError != null) {
                    Error error = webhookUnSubscError.getError();
                    if (error != null) {
                        errorMessage = error.getMessage();
                    }
                    if (ZYPCommonFunc.hasValue(errorMessage)) {
                        String errMsg = S21MessageFunc.clspGetMessage("NSZM1393E", new String[] {errorMessage});
                        S21InfoLogOutput.println(errMsg);
                    } else {
                        String errMsg = S21MessageFunc.clspGetMessage("NSZM1392E");
                        S21InfoLogOutput.println(errMsg);
                        throw new S21AbendException("NSZM1392E");
                    }
                }
            }
        } catch (SOAPFaultException e) {
            String errMsg = S21MessageFunc.clspGetMessage("NSZM1392E");
            S21InfoLogOutput.println(errMsg);
            S21InfoLogOutput.println("NSBI1320 : webhookUnSubscribeAPI FaultString : " + e.getFault().getFaultString());
            e.printStackTrace();
            throw new S21AbendException("NSZM1392E");
        } catch (Throwable t) {
        	String errMsg = S21MessageFunc.clspGetMessage("NSZM1392E");
            S21InfoLogOutput.println(errMsg);
            t.printStackTrace();
            throw new S21AbendException("NSZM1392E");
        }
    }

    @Override
    protected void termRoutine() {
        // Set termination code and total commit count.
        setTermState(this.termCd, normCnt, errCnt, normCnt + errCnt);
    }
}
