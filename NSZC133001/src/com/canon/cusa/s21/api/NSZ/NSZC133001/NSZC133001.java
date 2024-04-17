/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC133001;

import java.util.List;

import static com.canon.cusa.s21.api.NSZ.NSZC133001.constant.NSZC133001Constant.NSZM1383E;
import static com.canon.cusa.s21.api.NSZ.NSZC133001.constant.NSZC133001Constant.NSZM1384E;
import static com.canon.cusa.s21.api.NSZ.NSZC133001.constant.NSZC133001Constant.NSZM1393E;
import static com.canon.cusa.s21.api.NSZ.NSZC133001.constant.NSZC133001Constant.NSZM1394E;
import static com.canon.cusa.s21.api.NSZ.NSZC133001.constant.NSZC133001Constant.maxLength;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import javax.xml.ws.soap.SOAPFaultException;

import business.parts.NSZC133001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import com.canon.usa.s21.integration.walmart.nsbi1330.request.ObjectFactory;
import com.canon.usa.s21.integration.walmart.nsbi1330.request.S21ToWalmartIncidentUpdateApiRequest;
import com.canon.usa.s21.integration.walmart.nsbi1330.response.Error;
import com.canon.usa.s21.integration.walmart.nsbi1330.response.IncidentUpdateApiError;
import com.canon.usa.s21.integration.walmart.nsbi1330.response.S21ToWalmartIncidentUpdateApiResponse;
import com.canon.usa.s21.integration.walmart.nsbi1330.wrapper.S21ToWalmartIncidentUpdateApiProxy;

/**
 * <pre>
 * CSA Walmart Update Call Info API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/02/20   Hitachi         H.Watanabe      Create          QC#60012
 * 2023/05/24   Hitachi         T.Usui          Update          QC#60012
 * 2023/06/05   Hitachi         T.Usui          Update          QC#60012
 *</pre>
 */
public class NSZC133001 extends S21ApiCommonBase {

    /**
     * Constructor
     */
    public NSZC133001() {
        super();
    }

    /**
     * execute
     * @param pMsgList List<NSZC133001PMsg>
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(List<NSZC133001PMsg> pMsgList, final ONBATCH_TYPE onBatchTp) {
        for (NSZC133001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchTp);
        }
    }

    /**
     * execute
     * @param param NSZC133001PMsg
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(NSZC133001PMsg param, final ONBATCH_TYPE onBatchTp) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        if (isMandatoryParam(msgMap)) {
            callUpdateAPI(msgMap);
        }
        msgMap.flush();
    }

    private boolean isMandatoryParam(S21ApiMessageMap msgMap) {
        NSZC133001PMsg pMsg = (NSZC133001PMsg) msgMap.getPmsg();
        boolean resultParam = true;

        if (!ZYPCommonFunc.hasValue(pMsg.custSysId)) {
            resultParam = false;
            msgMap.addXxMsgId(NSZM1383E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.custCallId)) {
            resultParam = false;
            msgMap.addXxMsgId(NSZM1394E);
        }
        return resultParam;
    }

    // NSBI1330 Update Request for WMB
    private void callUpdateAPI(S21ApiMessageMap msgMap) {

        NSZC133001PMsg pMsg = (NSZC133001PMsg) msgMap.getPmsg();
        ObjectFactory objFactory = new ObjectFactory();
        S21ToWalmartIncidentUpdateApiRequest request = objFactory.createS21ToWalmartIncidentUpdateApiRequest();

        // set parameter
        request.setSysId(pMsg.custSysId.getValue());
        if (ZYPCommonFunc.hasValue(pMsg.fsrNum)) {
            request.setCorrelationId(pMsg.fsrNum.getValue());
        } else {
            request.setCorrelationId(pMsg.custCallId.getValue());
        }
        request.setComments(pMsg.svcCmntTxt.getValue());
        request.setWorkNotes(pMsg.svcWrkTxt.getValue());

        S21ToWalmartIncidentUpdateApiProxy proxy = new S21ToWalmartIncidentUpdateApiProxy();
        S21ToWalmartIncidentUpdateApiResponse response = null;

        try {
            // Call WMB
            response = proxy.updateApiRequest(request);
            // QC#60012 2023/05/24 Mod Start
//            String errorMessage = response.getIncidentUpdateApiError().getError().getMessage();
//            if (ZYPCommonFunc.hasValue(errorMessage)) {
//                msgMap.addXxMsgIdWithPrm(NSZM1393E, new String[] {errorMessage });
//            }
            String errorMessage = null;
            if (response != null) {
                IncidentUpdateApiError incidentUpdateApiError = response.getIncidentUpdateApiError();
                if (incidentUpdateApiError != null) {
                    Error error = incidentUpdateApiError.getError();
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
                        msgMap.addXxMsgId(NSZM1384E);
                    }
                }
            }
            // QC#60012 2023/05/24 Mod End
        } catch (SOAPFaultException e) {
            String errMsg = S21MessageFunc.clspGetMessage(NSZM1384E);
            S21InfoLogOutput.println(errMsg);
            S21InfoLogOutput.println("NSBI1330 : updateAPI FaultString : " + e.getFault().getFaultString());
            e.printStackTrace();
            msgMap.addXxMsgId(NSZM1384E);
        } catch (Throwable t) {
            String errMsg = S21MessageFunc.clspGetMessage(NSZM1384E);
            S21InfoLogOutput.println(errMsg);
            t.printStackTrace();
            msgMap.addXxMsgId(NSZM1384E);
        }
    }

}
