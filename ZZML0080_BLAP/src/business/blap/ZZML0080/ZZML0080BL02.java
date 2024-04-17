/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved. Original
 * Author: Q02046 Company: Fujitsu Limited Date: Jul 8, 2009
 */
package business.blap.ZZML0080;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.ZZML0080.common.ZZML0080CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * @author Q02673
 */
public class ZZML0080BL02 extends S21BusinessHandler {

    /**
     * @param cMsg
     * @param sMsg
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZML0080CMsg bizMsg = (ZZML0080CMsg) cMsg;
        ZZML0080SMsg sharedMsg = (ZZML0080SMsg) sMsg;
        String screenAplID = bizMsg.getScreenAplID();

        try {
            super.preDoProcess(cMsg, sMsg);
            if ("ZZML0080_INIT".equals(screenAplID)) {
                this.doProcess_ZZML0080_INIT(bizMsg, sharedMsg);
            } else if ("ZZML0080Scrn00_Search".equals(screenAplID)) {
                this.doProcess_ZZML0080Scrn00_Search(bizMsg, sharedMsg);
            } else if ("ZZML0080Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZML0080Scrn00_PageNext(bizMsg, sharedMsg);
            } else if ("ZZML0080Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZML0080Scrn00_PagePrev(bizMsg, sharedMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }


    /**
     * @param cMsg EZDCMsg
     * @param sMsg EZDSmsg
     */
    private void doProcess_ZZML0080_INIT(ZZML0080CMsg cMsg, ZZML0080SMsg sMsg) {
        ZZML0080CommonLogic.searchZZML0080(cMsg, sMsg);
    }


    /**
     * @param cMsg EZDCMsg
     * @param sMsg EZDSmsg
     */
    private void doProcess_ZZML0080Scrn00_Search(ZZML0080CMsg cMsg, ZZML0080SMsg sMsg) {
        ZZML0080CommonLogic.searchZZML0080(cMsg, sMsg);
    }


    /**
     * @param cMsg EZDCMsg
     * @param sMsg EZDSmsg
     */
    private void doProcess_ZZML0080Scrn00_PageNext(ZZML0080CMsg cMsg, ZZML0080SMsg sMsg) {

        //CurrentPage InputData
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int pageRows = cMsg.A.length();
        ZZML0080CommonLogic.storeEventSource(cMsg, sMsg, pagenationFrom - pageRows, pagenationFrom);

        //NextPage ShowData
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length() && i < sMsg.A.getValidCount(); i++) {
            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }


    /**
     * @param cMsg EZDCMsg
     * @param sMsg EZDSmsg
     */
    private void doProcess_ZZML0080Scrn00_PagePrev(ZZML0080CMsg cMsg, ZZML0080SMsg sMsg) {

        //CurrentPage InputData
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int pageRows = cMsg.A.length();
        int pageValidRows = cMsg.A.getValidCount();
        ZZML0080CommonLogic.storeEventSource(cMsg, sMsg, pagenationFrom + pageRows, pagenationFrom + pageRows + pageValidRows);

        //PrevPage ShowData
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length() && i < sMsg.A.getValidCount(); i++) {
            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }
}
