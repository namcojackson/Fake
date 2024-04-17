/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved. Original
 * Author: Q02046 Company: Fujitsu Limited Date: Jun 3, 2009
 */
package business.blap.ZZSL1110;

import java.math.BigDecimal;
import java.util.HashMap;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmEZDClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * @author $Author: Piotr Cebula $
 * @version $Revision: 1.19 $ $Date: 2009/07/16 14:58:51 $
 */
public final class ZZSL1110Query {

    /**
     * myInstance.
     */
    private static ZZSL1110Query myInstance = new ZZSL1110Query();

    /**
     * Method name: ZZSQ100200Join
     * <dd>The method explanation: Processing for init.
     */
    private ZZSL1110Query() {
        super();
    }

    /**
     * Method name: getInstance.
     * <dd>The method explanation: Processing for get Instance
     * @return myInstance
     */
    public static ZZSL1110Query getInstance() {
        return myInstance;
    }

    /**
     * Gets the company list on criteria.
     * @param pMsg the msg
     * @return the eZDP msg
     */
    public int getCompanyListOnCriteria(ZZSL1110CMsg cMsg) {

        int hitCount = 0;

        ZZSL1110_ACMsgArray resultObject = cMsg.A;
        S21SsmEZDClient ssmClient = S21SsmEZDClient.getClient(this.getClass());

        String compCode = cMsg.glblCmpyCd.getValue();
        String compName = cMsg.glblCmpyNm.getValue();

        BigDecimal minRow = cMsg.xxRowNum_MN.getValue();
        BigDecimal maxRow = cMsg.xxRowNum_MX.getValue();

        HashMap queryParam = new HashMap();
        queryParam.put("compCode", compCode.trim().toUpperCase() + "%");
        queryParam.put("compName", compName.trim().toUpperCase() + "%");
        queryParam.put("minRow", minRow);
        queryParam.put("maxRow", maxRow);

        S21SsmEZDResult result = ssmClient.queryEZDMsgArray("getCompanyListOnCriteria", queryParam, resultObject);

        // No Data
        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo("ZZZM9005W");
            return hitCount;
        }
        
        hitCount = resultObject.getValidCount();
        return hitCount;
    }

}
