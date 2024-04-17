/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved. Original
 * Author: Q02046 Company: Fujitsu Limited Date: Jun 3, 2009
 */
package business.blap.ZZZL0032;

import java.util.HashMap;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmEZDClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 */
public final class ZZZL0032Query {

    /**
     * myInstance.
     */
    private static ZZZL0032Query myInstance = new ZZZL0032Query();

    /**
     * Method name: ZZSQ100200Join
     * <dd>The method explanation: Processing for init.
     */
    private ZZZL0032Query() {
        super();
    }

    /**
     * Method name: getInstance.
     * <dd>The method explanation: Processing for get Instance
     * @return myInstance
     */
    public static ZZZL0032Query getInstance() {
        return myInstance;
    }

    /**
     * Gets the company list on criteria.
     * @param pMsg the msg
     * @return the eZDP msg
     */
    public int getEventIdList(ZZZL0032CMsg cMsg) {

        int hitCount = 0;

        ZZZL0032_ACMsgArray resultObject = cMsg.A;
        S21SsmEZDClient ssmClient = S21SsmEZDClient.getClient(this.getClass());

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String bizId = cMsg.bizId.getValue();
        String eventId = cMsg.scrAppId.getValue();

        HashMap queryParam = new HashMap();
        queryParam.put("glblCmpyCd", glblCmpyCd.trim().toUpperCase());
        if (bizId != null && bizId.length() != 0) {
            queryParam.put("bizId", bizId.trim().toUpperCase()); 
        }
        if (eventId != null && eventId.length() != 0) {
            queryParam.put("eventId", eventId.trim()); 
        }
        S21SsmEZDResult result = ssmClient.queryEZDMsgArray("getEventIdList", queryParam, resultObject);
        
        // No Data
        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo("ZZZM9005W");
            return hitCount;
        }
        
        hitCount = resultObject.getValidCount();
        return hitCount;
    }

}
