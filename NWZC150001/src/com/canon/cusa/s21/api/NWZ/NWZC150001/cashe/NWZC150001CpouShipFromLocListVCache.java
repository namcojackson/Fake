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
import business.db.SHIP_FROM_LOC_LIST_VTMsg;
import business.db.SHIP_FROM_LOC_LIST_VTMsgArray;

import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouFindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalDataCacheBase;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;

public class NWZC150001CpouShipFromLocListVCache extends NWZC150001CpouLocalDataCacheBase {

    private final S21LRUMap<String, EZDTMsgArray> tMsgArrayCache = new S21LRUMap<String, EZDTMsgArray>();

    public SHIP_FROM_LOC_LIST_VTMsgArray getTMsgArray(NWZC150001CpouFindCondition findCondition) {

        return (SHIP_FROM_LOC_LIST_VTMsgArray) super.getTMsgArray(SHIP_FROM_LOC_LIST_VTMsg.class, findCondition, tMsgArrayCache);
    }

}
