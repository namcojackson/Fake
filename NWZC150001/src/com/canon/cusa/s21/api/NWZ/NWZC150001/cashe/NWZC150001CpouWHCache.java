/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/05/2010   CUSA            S.Yamamoto      Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cashe;

import parts.common.EZDTMsgArray;
import business.db.WHTMsg;
import business.db.WHTMsgArray;

import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouFindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalDataCacheBase;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

public class NWZC150001CpouWHCache extends NWZC150001CpouLocalDataCacheBase {

    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    public WHTMsgArray getTMsgArray(NWZC150001CpouFindCondition findCondition) {

        return (WHTMsgArray) super.getTMsgArray(WHTMsg.class, findCondition, tMsgArrayCache);
    }

}
