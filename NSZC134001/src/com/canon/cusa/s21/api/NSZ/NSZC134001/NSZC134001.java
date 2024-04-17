/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC134001;

import static com.canon.cusa.s21.api.NSZ.NSZC134001.constant.NSZC134001Constant.NSZM1383E;
import static com.canon.cusa.s21.api.NSZ.NSZC134001.constant.NSZC134001Constant.NSZM1385E;
import static com.canon.cusa.s21.api.NSZ.NSZC134001.constant.NSZC134001Constant.NSZM1387E;
import static com.canon.cusa.s21.api.NSZ.NSZC134001.constant.NSZC134001Constant.NSZM1388E;
import static com.canon.cusa.s21.api.NSZ.NSZC134001.constant.NSZC134001Constant.NSZM1389E;
import static com.canon.cusa.s21.api.NSZ.NSZC134001.constant.NSZC134001Constant.NSZM1393E;
import static com.canon.cusa.s21.api.NSZ.NSZC134001.constant.NSZC134001Constant.maxLength;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.List;

import javax.xml.ws.soap.SOAPFaultException;

import business.parts.NSZC134001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.usa.s21.integration.walmart.nsbi1340.request.ObjectFactory;
import com.canon.usa.s21.integration.walmart.nsbi1340.request.S21ApiToWalmartWebhookRegistrationRequest;
import com.canon.usa.s21.integration.walmart.nsbi1340.response.Error;
import com.canon.usa.s21.integration.walmart.nsbi1340.response.S21ApiToWalmartWebhookRegistrationResponse;
import com.canon.usa.s21.integration.walmart.nsbi1340.response.WebhookRegistrationError;
import com.canon.usa.s21.integration.walmart.nsbi1340.wrapper.S21ApiToWalmartWebhookRegistrationProxy;

/**
 * <pre>
 * CSA Walmart Webhook API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/02/20   Hitachi         H.Watanabe      Create          QC#60012
 * 2023/05/24   Hitachi         T.Usui          Update          QC#60012
 * 2023/06/05   Hitachi         T.Usui          Update          QC#60012
 *</pre>
 */
public class NSZC134001 extends S21ApiCommonBase {

    /**
     * Constructor
     */
    public NSZC134001() {
        super();
    }

    /**
     * execute
     * @param pMsgList List<NSZC134001PMsg>
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(List<NSZC134001PMsg> pMsgList, final ONBATCH_TYPE onBatchTp) {
        for (NSZC134001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchTp);
        }
    }

    /**
     * execute
     * @param param NSZC134001PMsg
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(NSZC134001PMsg param, final ONBATCH_TYPE onBatchTp) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        if (isMandatoryParam(msgMap)) {
            callWebhookAPI(msgMap);
        }
        msgMap.flush();
    }

    private boolean isMandatoryParam(S21ApiMessageMap msgMap) {
        NSZC134001PMsg pMsg = (NSZC134001PMsg) msgMap.getPmsg();
        boolean resultParam = true;

        if (!ZYPCommonFunc.hasValue(pMsg.custWbhkId)) {
            resultParam = false;
            msgMap.addXxMsgId(NSZM1387E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.custSysId)) {
            resultParam = false;
            msgMap.addXxMsgId(NSZM1383E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.custTblNm)) {
            resultParam = false;
            msgMap.addXxMsgId(NSZM1388E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.svcTaskWatchFldTxt)) {
            resultParam = false;
            msgMap.addXxMsgId(NSZM1389E);
        }
        return resultParam;
    }

    // NSBI1340 Webhook Request for Walmart
    private void callWebhookAPI(S21ApiMessageMap msgMap) {
        NSZC134001PMsg pMsg = (NSZC134001PMsg) msgMap.getPmsg();
        ObjectFactory objFactory = new ObjectFactory();
        S21ApiToWalmartWebhookRegistrationRequest request = objFactory.createS21ApiToWalmartWebhookRegistrationRequest();

        // set parameter
        request.setWebhook(pMsg.custWbhkId.getValue());
        request.setTableName(pMsg.custTblNm.getValue());
        request.setSysId(pMsg.custSysId.getValue());
        request.setWatchFields(pMsg.svcTaskWatchFldTxt.getValue());
        request.setReturnFields(pMsg.svcTaskRtrnFldTxt.getValue());

        S21ApiToWalmartWebhookRegistrationProxy proxy = new S21ApiToWalmartWebhookRegistrationProxy();
        S21ApiToWalmartWebhookRegistrationResponse response = null;

        try {
            // Call WMB
            response = proxy.webhookRegistrationRequest(request);
            // QC#60012 2023/05/24 Mod Start
//            String errorMessage = response.getWebhookRegistrationError().getError().getMessage();
//            if (ZYPCommonFunc.hasValue(errorMessage)) {
//                msgMap.addXxMsgIdWithPrm(NSZM1393E, new String[] {errorMessage });
//            }
            String errorMessage = null;
            if (response != null) {
                WebhookRegistrationError webhookRegistrationError = response.getWebhookRegistrationError();
                if (webhookRegistrationError != null) {
                    Error error = webhookRegistrationError.getError();
                    if (error != null) {
                        // QC#60012 2023/06/05 Mod Start
                        if (hasValue(error.getMessage()) && error.getMessage().length() > maxLength) {
                            errorMessage = error.getMessage().substring(0, maxLength);
                        } else {
                            errorMessage = error.getMessage();
                        }
                        // QC#60012 2023/06/05 Mod End
                    }
                    if (ZYPCommonFunc.hasValue(errorMessage)) {
                        msgMap.addXxMsgIdWithPrm(NSZM1393E, new String[] {errorMessage });
                    } else {
                        msgMap.addXxMsgId(NSZM1385E);
                    }
                }
            }
            // QC#60012 2023/05/24 Mod End
        } catch (SOAPFaultException e) {
            String errMsg = S21MessageFunc.clspGetMessage(NSZM1385E);
            S21InfoLogOutput.println(errMsg);
            S21InfoLogOutput.println("NSBI1340 : webhookAPI FaultString : " + e.getFault().getFaultString());
            e.printStackTrace();
            msgMap.addXxMsgId(NSZM1385E);
        } catch (Throwable t) {
            String errMsg = S21MessageFunc.clspGetMessage(NSZM1385E);
            S21InfoLogOutput.println(errMsg);
            t.printStackTrace();
            msgMap.addXxMsgId(NSZM1385E);
        }
    }
}
