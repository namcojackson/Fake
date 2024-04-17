/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC135001;

import static com.canon.cusa.s21.api.NSZ.NSZC135001.constant.NSZC135001Constant.NSZM1383E;
import static com.canon.cusa.s21.api.NSZ.NSZC135001.constant.NSZC135001Constant.NSZM1386E;
import static com.canon.cusa.s21.api.NSZ.NSZC135001.constant.NSZC135001Constant.NSZM1390E;
import static com.canon.cusa.s21.api.NSZ.NSZC135001.constant.NSZC135001Constant.NSZM1393E;
import static com.canon.cusa.s21.api.NSZ.NSZC135001.constant.NSZC135001Constant.NSZM1394E;
import static com.canon.cusa.s21.api.NSZ.NSZC135001.constant.NSZC135001Constant.maxLength;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.List;

import javax.xml.ws.soap.SOAPFaultException;

import business.parts.NSZC135001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.usa.s21.integration.walmart.nsbi1350.request.ObjectFactory;
import com.canon.usa.s21.integration.walmart.nsbi1350.request.S21ToWalmartIncidentHoldApiRequest;
import com.canon.usa.s21.integration.walmart.nsbi1350.response.Error;
import com.canon.usa.s21.integration.walmart.nsbi1350.response.IncidentHoldApiError;
import com.canon.usa.s21.integration.walmart.nsbi1350.response.S21ToWalmartIncidentHoldApiResponse;
import com.canon.usa.s21.integration.walmart.nsbi1350.wrapper.S21ToWalmartIncidentHoldApiProxy;

/**
 * <pre>
 * CSA Walmart  Hold API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/02/20   Hitachi         H.Watanabe      Create          QC#60012
 * 2023/05/24   Hitachi         T.Usui          Update          QC#60012
 * 2023/06/05   Hitachi         T.Usui          Update          QC#60012
 *</pre>
 */
public class NSZC135001 extends S21ApiCommonBase {

    /**
     * Constructor
     */
    public NSZC135001() {
        super();
    }

    /**
     * execute
     * @param pMsgList List<NSZC135001PMsg>
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(List<NSZC135001PMsg> pMsgList, final ONBATCH_TYPE onBatchTp) {
        for (NSZC135001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchTp);
        }
    }

    /**
     * execute
     * @param param NSZC135001PMsg
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(NSZC135001PMsg param, final ONBATCH_TYPE onBatchTp) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        if (isMandatoryParam(msgMap)) {
            callHoldAPI(msgMap);
        }
        msgMap.flush();
    }

    private boolean isMandatoryParam(S21ApiMessageMap msgMap) {
        NSZC135001PMsg pMsg = (NSZC135001PMsg) msgMap.getPmsg();
        boolean resultParam = true;

        if (!ZYPCommonFunc.hasValue(pMsg.custSysId)) {
            resultParam = false;
            msgMap.addXxMsgId(NSZM1383E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.custCallId)) {
            resultParam = false;
            msgMap.addXxMsgId(NSZM1394E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.custRsnCd)) {
            resultParam = false;
            msgMap.addXxMsgId(NSZM1390E);
        }
        return resultParam;
    }

    // NSBI1350 Hold Request for Walmart
    private void callHoldAPI(S21ApiMessageMap msgMap) {
        NSZC135001PMsg pMsg = (NSZC135001PMsg) msgMap.getPmsg();
        ObjectFactory objFactory = new ObjectFactory();
        S21ToWalmartIncidentHoldApiRequest request = objFactory.createS21ToWalmartIncidentHoldApiRequest();

        // set parameter
        request.setSysId(pMsg.custSysId.getValue());
        request.setCorrelationId(pMsg.custCallId.getValue());
        request.setHoldReason(pMsg.custRsnCd.getValue());
        request.setComments(pMsg.svcCmntTxt.getValue());
        request.setStatusNote(pMsg.svcStsTxt.getValue());

        S21ToWalmartIncidentHoldApiProxy proxy = new S21ToWalmartIncidentHoldApiProxy();
        S21ToWalmartIncidentHoldApiResponse response = null;

        try {
            // Call WMB
            response = proxy.holdApiRequest(request);
            // QC#60012 2023/05/24 Mod Start
//            String errorMessage = response.getIncidentHoldApiError().getError().getMessage();
//            if (ZYPCommonFunc.hasValue(errorMessage)) {
//                msgMap.addXxMsgIdWithPrm(NSZM1393E, new String[] {errorMessage });
//            }
            String errorMessage = null;
            if (response != null) {
                IncidentHoldApiError incidentHoldApiError = response.getIncidentHoldApiError();
                if (incidentHoldApiError != null) {
                    Error error = incidentHoldApiError.getError();
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
                        msgMap.addXxMsgId(NSZM1386E);
                    }
                }
            }
            // QC#60012 2023/05/24 Mod End
        } catch (SOAPFaultException e) {
            String errMsg = S21MessageFunc.clspGetMessage(NSZM1386E);
            S21InfoLogOutput.println(errMsg);
            S21InfoLogOutput.println("NSBI1350 : holdAPI FaultString : " + e.getFault().getFaultString());
            e.printStackTrace();
            msgMap.addXxMsgId(NSZM1386E);
        } catch (Throwable t) {
            String errMsg = S21MessageFunc.clspGetMessage(NSZM1386E);
            S21InfoLogOutput.println(errMsg);
            t.printStackTrace();
            msgMap.addXxMsgId(NSZM1386E);
        }
    }
}
