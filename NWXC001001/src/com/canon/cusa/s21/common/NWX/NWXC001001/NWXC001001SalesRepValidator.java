/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/11/2011   Fujitsu         K.Tajima        Create          Def#1493 Sales Rep should be validated in Order Entry Screen
 *</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * Sales Rep Validator.
 * 
 * @author K.Tajima
 */
public class NWXC001001SalesRepValidator {

    /**
     * NWZM0955E : The "Sales Rep" you entered cannot be used.
     */
    public static final String MSG_ID_INVALID_SLS_REP = "NWZM0955E";
    
    private static final S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(NWXC001001SalesRepValidator.class);

    private NWXC001001SalesRepValidator() {
    }
    
    /**
     * Is it valid Sales Rep?
     * 
     * @param   glblCmpyCd  GLBL_CMPY_CD
     * @param   tocCd       TOC_CD
     * 
     * @return  true/valid,  false/invalid
     */
    public static boolean isValidSalesRep(String glblCmpyCd, String tocCd) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("tocCd",      tocCd);

        return FLG_ON_Y.equals(ssmClient.queryObject("isValidSalesRep", ssmParam));
    }
    
}
