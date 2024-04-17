/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved. Original
 * Author: Q02046 Company: Fujitsu Limited Date: Jul 8, 2009
 */
package business.blap.ZZML0070;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.ZZML0070.common.ZZML0070CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * @author Q02673
 */
public class ZZML0070BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZML0070CMsg bizMsg = (ZZML0070CMsg) cMsg;
        ZZML0070SMsg sharedMsg = (ZZML0070SMsg) sMsg;
        String screenAplID = bizMsg.getScreenAplID();

        try {
            super.preDoProcess(cMsg, sMsg);

            if ("ZZML0070_INIT".equals(screenAplID)) {
                doProcess_ZZML0070_INIT(bizMsg, sharedMsg);
            } else if ("ZZML0070Scrn00_Search".equals(screenAplID) 
                    || "ZZML0070_ZZML0060".equals(screenAplID)) {
                doProcess_ZZML0070Scrn00_Search(bizMsg, sharedMsg);
            } else if ("ZZML0070Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZML0070Scrn00_PageNext(bizMsg, sharedMsg);
            } else if ("ZZML0070Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZML0070Scrn00_PagePrev(bizMsg, sharedMsg);
            } else if ("ZZML0070Scrn00_Edit".equals(screenAplID)) {
                doProcess_ZZML0070Scrn00_Edit(bizMsg, sharedMsg);
            } else if ("ZZML0070_ZZML0071".equals(screenAplID)) {
                doProcess_ZZML0070_ZZML0071(bizMsg, sharedMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }


    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0070_INIT(ZZML0070CMsg cMsg, ZZML0070SMsg sMsg) {
        ZZML0070CommonLogic.searchZZML0070Scrn00(cMsg, sMsg);
    }


    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0070Scrn00_Search(ZZML0070CMsg cMsg, ZZML0070SMsg sMsg) {
        ZZML0070CommonLogic.searchZZML0070Scrn00(cMsg, sMsg);
    }


    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0070Scrn00_PageNext(ZZML0070CMsg cMsg, ZZML0070SMsg sMsg) {

        //CurrentPage InputData
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int pageRows = cMsg.A.length();
        ZZML0070CommonLogic.storeEventSourceScrn00(cMsg, sMsg, pagenationFrom - pageRows, pagenationFrom);

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
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0070Scrn00_PagePrev(ZZML0070CMsg cMsg, ZZML0070SMsg sMsg) {

        //CurrentPage InputData
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int pageRows = cMsg.A.length();
        int pageValidRows = cMsg.A.getValidCount();
        ZZML0070CommonLogic.storeEventSourceScrn00(cMsg, sMsg, pagenationFrom + pageRows, pagenationFrom + pageRows + pageValidRows);

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


    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0070Scrn00_Edit(ZZML0070CMsg cMsg, ZZML0070SMsg sMsg) {
        int index = cMsg.xxNum_E.getValueInt();
        cMsg.mlUsrAddrPk_E.setValue(cMsg.A.no(index).mlUsrAddrPk_A.getValue());
    }
    
    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0070_ZZML0071(ZZML0070CMsg cMsg, ZZML0070SMsg sMsg) {
//        ZZML0070CommonLogic.searchZZML0070Scrn00(cMsg, sMsg);
        ZZML0070CommonLogic.searchZZML0070Scrn00_returnZZML0070Scrn01(cMsg, sMsg);
    }
    
}
