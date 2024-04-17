/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cashe;


import parts.common.EZDTMsgArray;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;

import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouFindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalDataCacheBase;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

/**
 * <pre>
 * This class is the Cache (VNDTMsgArray).
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/26/2012   CUSA            M.Fuji          Create          N/A
 *</pre>
 */
public class NWZC150001CpouVenderCache extends NWZC150001CpouLocalDataCacheBase {

    /** cache */
    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    public VNDTMsgArray getTMsgArray(NWZC150001CpouFindCondition findCondition) {

        return (VNDTMsgArray) super.getTMsgArray(VNDTMsg.class, findCondition, tMsgArrayCache);
    }
}
