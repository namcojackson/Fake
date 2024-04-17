/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Q02046
 * Company: Fujitsu Limited
 * Date: Jul 8, 2009
 */
package business.blap.ZZML0010;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.ZZML0010.common.ZZML0010CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * @author Q02673
 */
public class ZZML0010BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZML0010CMsg bizMsg = (ZZML0010CMsg) cMsg;
        String screenAplID = bizMsg.getScreenAplID();

        try {
            super.preDoProcess(cMsg, sMsg);

            if ("ZZML0010_INIT".equals(screenAplID)) {
                doProcess_ZZML0010_INIT(bizMsg, sMsg);
            } else if ("ZZML0010Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_ZZML0010Scrn00_CMN_Reset(bizMsg, sMsg);
            } else if ("ZZML0010Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZML0010_Search(bizMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0010_INIT(ZZML0010CMsg cMsg, EZDSMsg sMsg) {
    }

    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0010Scrn00_CMN_Reset(ZZML0010CMsg cMsg, EZDSMsg sMsg) {
    }

    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0010_Search(ZZML0010CMsg cMsg, EZDSMsg sMsg) {
        ZZML0010CommonLogic.setLanguageList(cMsg);
        if (!ZZML0010CommonLogic.chkGlbCmpCd(cMsg)) {
//            cMsg.clear();
//            sMsg.clear();
            return;
        }
        ZZML0010CommonLogic.search(cMsg);
    }
}
