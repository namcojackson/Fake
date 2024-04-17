/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC132001;

import static com.canon.cusa.s21.api.NSZ.NSZC132001.constant.NSZC132001Constant.NSZM1383E;
import static com.canon.cusa.s21.api.NSZ.NSZC132001.constant.NSZC132001Constant.NSZM1387E;
import static com.canon.cusa.s21.api.NSZ.NSZC132001.constant.NSZC132001Constant.NSZM1388E;
import static com.canon.cusa.s21.api.NSZ.NSZC132001.constant.NSZC132001Constant.NSZM1392E;
import static com.canon.cusa.s21.api.NSZ.NSZC132001.constant.NSZC132001Constant.NSZM1393E;
import static com.canon.cusa.s21.api.NSZ.NSZC132001.constant.NSZC132001Constant.maxLength;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.List;

import javax.xml.ws.soap.SOAPFaultException;

import business.parts.NSZC132001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.usa.s21.integration.walmart.nsbi1320.request.ObjectFactory;
import com.canon.usa.s21.integration.walmart.nsbi1320.request.S21ApiToWalmartWebhookUnSubscRequest;
import com.canon.usa.s21.integration.walmart.nsbi1320.response.Error;
import com.canon.usa.s21.integration.walmart.nsbi1320.response.S21ApiToWalmartWebhookUnSubscResponse;
import com.canon.usa.s21.integration.walmart.nsbi1320.response.WebhookUnSubscError;
import com.canon.usa.s21.integration.walmart.nsbi1320.wrapper.S21ApiToWalmartWebhookUnSubscProxy;

/**
 * <pre>
 * CSA Walmart Webhook UnSubscribe API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/03/13   Hitachi         T.Usui          Create          QC#60012
 * 2023/05/24   Hitachi         T.Usui          Update          QC#60012
 * 2023/06/05   Hitachi         T.Usui          Update          QC#60012
 *</pre>
 */
public class NSZC132001 extends S21ApiCommonBase {

    /**
     * Constructor
     */
    public NSZC132001() {
        super();
    }

    /**
     * execute
     * @param pMsgList List<NSZC132001PMsg>
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(List<NSZC132001PMsg> pMsgList, final ONBATCH_TYPE onBatchTp) {
        for (NSZC132001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchTp);
        }
    }

    /**
     * execute
     * @param param NSZC132001PMsg
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(NSZC132001PMsg param, final ONBATCH_TYPE onBatchTp) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        if (isMandatoryParam(msgMap)) {
            callWebhookUnSubscribeAPI(msgMap);
        }
        msgMap.flush();
    }

    private boolean isMandatoryParam(S21ApiMessageMap msgMap) {
        NSZC132001PMsg pMsg = (NSZC132001PMsg) msgMap.getPmsg();
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
        return resultParam;
    }

    // NSBI1320 Webhook UnSubscribe Request for Walmart
    private void callWebhookUnSubscribeAPI(S21ApiMessageMap msgMap) {
        NSZC132001PMsg pMsg = (NSZC132001PMsg) msgMap.getPmsg();
        ObjectFactory objFactory = new ObjectFactory();
        S21ApiToWalmartWebhookUnSubscRequest request = objFactory.createS21ApiToWalmartWebhookUnSubscRequest();

        // set parameter
        request.setWebhook(pMsg.custWbhkId.getValue());
        request.setTableName(pMsg.custTblNm.getValue());
        request.setSysId(pMsg.custSysId.getValue());

        S21ApiToWalmartWebhookUnSubscProxy proxy = new S21ApiToWalmartWebhookUnSubscProxy();
        S21ApiToWalmartWebhookUnSubscResponse response = null;

        try {
            // Call WMB
            response = proxy.webhookUnSubscRequest(request);
            // QC#60012 2023/05/24 Mod Start
//            String errorMessage = response.getWebhookUnSubscError().getError().getMessage();
//            if (ZYPCommonFunc.hasValue(errorMessage)) {
//                msgMap.addXxMsgIdWithPrm(NSZM1393E, new String[] {errorMessage });
//            }
            String errorMessage = null;
            if (response != null) {
                WebhookUnSubscError webhookUnSubscError = response.getWebhookUnSubscError();
                if (webhookUnSubscError != null) {
                    Error error = webhookUnSubscError.getError();
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
                        msgMap.addXxMsgId(NSZM1392E);
                    }
                }
            }
            // QC#60012 2023/05/24 Mod End
        } catch (SOAPFaultException e) {
            String errMsg = S21MessageFunc.clspGetMessage(NSZM1392E);
            S21InfoLogOutput.println(errMsg);
            S21InfoLogOutput.println("NSBI1320 : webhookUnSubscribeAPI FaultString : " + e.getFault().getFaultString());
            e.printStackTrace();
            msgMap.addXxMsgId(NSZM1392E);
        } catch (Throwable t) {
            String errMsg = S21MessageFunc.clspGetMessage(NSZM1392E);
            S21InfoLogOutput.println(errMsg);
            t.printStackTrace();
            msgMap.addXxMsgId(NSZM1392E);
        }
    }
}
