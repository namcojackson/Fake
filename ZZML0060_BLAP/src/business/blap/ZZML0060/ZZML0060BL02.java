/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved. Original
 * Author: Q02046 Company: Fujitsu Limited Date: Jul 8, 2009
 */
package business.blap.ZZML0060;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.ZZML0010.common.ZZML0010CommonLogic;
import business.blap.ZZML0060.common.ZZML0060CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * @author Q02673
 */
public class ZZML0060BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZML0060CMsg bizMsg = (ZZML0060CMsg) cMsg;
        ZZML0060SMsg sharedMsg = (ZZML0060SMsg) sMsg;
        String screenAplID = bizMsg.getScreenAplID();

        try {
            super.preDoProcess(cMsg, sMsg);

            if ("ZZML0060_INIT".equals(screenAplID)) {
                doProcess_ZZML0060_INIT(bizMsg, sharedMsg);
            } else if ("ZZML0060Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZML0060Scrn00_Search(bizMsg, sharedMsg);
            } else if ("ZZML0060Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZML0060Scrn00_PageNext(bizMsg, sharedMsg);
            } else if ("ZZML0060Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZML0060Scrn00_PagePrev(bizMsg, sharedMsg);
            } else if ("ZZML0060Scrn00_Edit".equals(screenAplID)) {
                doProcess_ZZML0060Scrn00_Edit(bizMsg, sharedMsg);
            } else if ("ZZML0060Scrn01_CMN_Reset".equals(screenAplID)) {
                doProcess_ZZML0060Scrn01_CMN_Reset(bizMsg, sharedMsg);
            } else if ("ZZML0060Scrn01_CMN_Return".equals(screenAplID)) {
                doProcess_ZZML0060Scrn01_CMN_Return(bizMsg, sharedMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }


    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0060_INIT(ZZML0060CMsg cMsg, ZZML0060SMsg sMsg) {
        ZZML0060CommonLogic.searchZZML0060Scrn00(cMsg, sMsg);
    }


    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0060Scrn00_Search(ZZML0060CMsg cMsg, ZZML0060SMsg sMsg) {
        if (!ZZML0060CommonLogic.chkGlbCmpCd(cMsg)) {
          return;
      }
        ZZML0060CommonLogic.searchZZML0060Scrn00(cMsg, sMsg);
    }


    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0060Scrn00_PageNext(ZZML0060CMsg cMsg, ZZML0060SMsg sMsg) {

        //CurrentPage InputData
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int pageRows = cMsg.A.length();
        ZZML0060CommonLogic.storeEventSourceScrn00(cMsg, sMsg, pagenationFrom - pageRows, pagenationFrom);

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
    private void doProcess_ZZML0060Scrn00_PagePrev(ZZML0060CMsg cMsg, ZZML0060SMsg sMsg) {

        //CurrentPage InputData
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int pageRows = cMsg.A.length();
        int pageValidRows = cMsg.A.getValidCount();
        ZZML0060CommonLogic.storeEventSourceScrn00(cMsg, sMsg, pagenationFrom + pageRows, pagenationFrom + pageRows + pageValidRows);

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
    private void doProcess_ZZML0060Scrn00_Edit(ZZML0060CMsg cMsg, ZZML0060SMsg sMsg) {
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int pagenationTo = cMsg.xxPageShowToNum.getValueInt();
        ZZML0060CommonLogic.storeEventSourceScrn00(cMsg, sMsg, pagenationFrom, pagenationTo);
        cMsg.setCommitSMsg(true);

        ZZML0060CommonLogic.searchZZML0060Scrn01(cMsg, sMsg);
    }
    
    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0060Scrn01_CMN_Reset(ZZML0060CMsg cMsg, ZZML0060SMsg sMsg) {
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int pagenationTo = cMsg.xxPageShowToNum.getValueInt();
        ZZML0060CommonLogic.storeEventSourceScrn00(cMsg, sMsg, pagenationFrom, pagenationTo);
        cMsg.setCommitSMsg(true);

        ZZML0060CommonLogic.searchZZML0060Scrn01(cMsg, sMsg);
    }
    
    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0060Scrn01_CMN_Return(ZZML0060CMsg cMsg, ZZML0060SMsg sMsg) {
        ZZML0060CommonLogic.research(cMsg, sMsg);
    }
}
