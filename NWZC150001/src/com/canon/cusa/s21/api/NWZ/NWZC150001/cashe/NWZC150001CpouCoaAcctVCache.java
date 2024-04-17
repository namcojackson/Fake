/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/17/2009   CUSA            K.Tajima        Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cashe;

import parts.common.EZDTMsgArray;
//import business.db.COA_ACCT_VTMsg;
//import business.db.COA_ACCT_VTMsgArray;
import business.db.COA_ACCTTMsg;
import business.db.COA_ACCTTMsgArray;

import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouFindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalDataCacheBase;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

public class NWZC150001CpouCoaAcctVCache extends NWZC150001CpouLocalDataCacheBase {

    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

//    public COA_ACCT_VTMsgArray getTMsgArray(NWZC150001CpouFindCondition findCondition) {
//
//        return (COA_ACCT_VTMsgArray) super.getTMsgArray(COA_ACCT_VTMsg.class, findCondition, tMsgArrayCache);
//    }

    public COA_ACCTTMsgArray getTMsgArray(NWZC150001CpouFindCondition findCondition) {

        return (COA_ACCTTMsgArray) super.getTMsgArray(COA_ACCTTMsg.class, findCondition, tMsgArrayCache);
    }
}
