/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2030;

import static business.blap.NWAL2030.constant.NWAL2030Constant.*;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2030.common.NWAL2030CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Hold Set Up Screen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Hitachi         K.Kojima        Create          N/A
 * 2016/04/04   Fujitsu         M.Suzuki        Update          CSA QC#6370
 * 2016/04/07   Fujitsu         M.Suzuki        Update          CSA QC#6369
 *</pre>
 */
public class NWAL2030BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            String globalCompanyCode = getGlobalCompanyCode();
            if ("NWAL2030_INIT".equals(screenAplID)) {
                doProcess_NWAL2030_INIT((NWAL2030CMsg) cMsg, globalCompanyCode, (NWAL2030SMsg) sMsg);
            } else if ("NWAL2030Scrn00_AddLine".equals(screenAplID)) {
                doProcess_NWAL2030Scrn00_AddLine((NWAL2030CMsg) cMsg, globalCompanyCode);
            //} else if ("NWAL2030Scrn00_CMN_Reset".equals(screenAplID)) {
            //    doProcess_NWAL2030Scrn00_CMN_Reset((NWAL2030CMsg) cMsg, globalCompanyCode, (NWAL2030SMsg) sMsg);
            // 2016/04/07 S21_NA#6369 MOD Start ---------
            } else if ("NWAL2030Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL2030Scrn00_CMN_Clear((NWAL2030CMsg) cMsg, globalCompanyCode, (NWAL2030SMsg) sMsg);
            // 2016/04/07 S21_NA#6369 MOD End -----------
            } else if ("NWAL2030Scrn00_CMN_Submit".equals(screenAplID)) {
                // 2016/04/04 S21_NA#6370 MOD Start -----------
                //doProcess_NWAL2030Scrn00_CMN_Submit((NWAL2030CMsg) cMsg, globalCompanyCode);
                doProcess_NWAL2030Scrn00_CMN_Submit((NWAL2030CMsg) cMsg, globalCompanyCode, (NWAL2030SMsg) sMsg);
                // 2016/04/04 S21_NA#6370 MOD END --------------
            } else if ("NWAL2030Scrn00_DeleteLine".equals(screenAplID)) {
                doProcess_NWAL2030Scrn00_DeleteLine((NWAL2030CMsg) cMsg, globalCompanyCode);
            } else if ("NWAL2030Scrn00_Search".equals(screenAplID)) {
                // 2016/04/04 S21_NA#6370 MOD Start -----------
                //doProcess_NWAL2030Scrn00_Search((NWAL2030CMsg) cMsg, globalCompanyCode, true);
                doProcess_NWAL2030Scrn00_Search((NWAL2030CMsg) cMsg, globalCompanyCode, true, (NWAL2030SMsg) sMsg);
                // 2016/04/04 S21_NA#6370 MOD END --------------
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NWAL2030_INIT
     * @param cMsg NWAL2030CMsg
     * @param globalCompanyCode String
     */
    private void doProcess_NWAL2030_INIT(NWAL2030CMsg cMsg, String globalCompanyCode, NWAL2030SMsg sMsg) {
        cMsg.A.clear();
        cMsg.L.clear();
        cMsg.W.clear();
        cMsg.A.setValidCount(0);
        cMsg.L.setValidCount(0);
        cMsg.W.setValidCount(0);
        // 2016/04/04 S21_NA#6370 MOD Start ---------
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        // 2016/04/04 S21_NA#6370 MOD End -----------

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(globalCompanyCode));

        NWAL2030Query.getInstance().getPulldownListLevel(cMsg);
        NWAL2030Query.getInstance().getPulldownListWorkflow(cMsg);
    }

    /**
     * doProcess_NWAL2030Scrn00_AddLine
     * @param cMsg NWAL2030CMsg
     * @param globalCompanyCode String
     */
    private void doProcess_NWAL2030Scrn00_AddLine(NWAL2030CMsg cMsg, String globalCompanyCode) {
        cMsg.A.setValidCount(cMsg.A.getValidCount() + 1);
        cMsg.A.no(cMsg.A.getValidCount() - 1).clear();
        cMsg.A.no(cMsg.A.getValidCount() - 1).xxExstFlg_A.setValue(ZYPConstant.FLG_OFF_N);
        for (int numC = 0; numC < cMsg.A.getValidCount(); numC++) {
            for (int numL = 0; numL < cMsg.L.getValidCount(); numL++) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(numC).hldLvlCd_CD.no(numL), cMsg.L.no(numL).hldLvlCd_L);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(numC).hldLvlDescTxt_SC.no(numL), cMsg.L.no(numL).hldLvlDescTxt_L);
            }
            for (int numW = 0; numW < cMsg.W.getValidCount(); numW++) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(numC).ordProcNodeCd_CD.no(numW), cMsg.W.no(numW).ordProcNodeCd_W);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(numC).ordProcNodeDescTxt_SC.no(numW), cMsg.W.no(numW).ordProcNodeDescTxt_W);
            }
        }
    }

    //2016/04/07 S21_NA#6369 Add Start --------------
//    /**
//     * doProcess_NWAL2030Scrn00_CMN_Reset
//     * @param cMsg NWAL2030CMsg
//     * @param globalCompanyCode String
//     */
//    private void doProcess_NWAL2030Scrn00_CMN_Reset(NWAL2030CMsg cMsg, String globalCompanyCode, NWAL2030SMsg sMsg) {
//        cMsg.hldRsnCd.clear();
//        cMsg.hldRsnNm.clear();
//        doProcess_NWAL2030_INIT(cMsg, globalCompanyCode, sMsg);
//    }
    /**
     * doProcess_NWAL2030Scrn00_CMN_Clear
     * @param cMsg NWAL2030CMsg
     * @param globalCompanyCode String
     */
    private void doProcess_NWAL2030Scrn00_CMN_Clear(NWAL2030CMsg cMsg, String globalCompanyCode, NWAL2030SMsg sMsg) {
        cMsg.hldRsnCd.clear();
        cMsg.hldRsnNm.clear();
        doProcess_NWAL2030_INIT(cMsg, globalCompanyCode, sMsg);
    }
    //2016/04/07 S21_NA#6369 Add End --------------

    //2016/04/04 S21_NA#6370 MOD Start --------------
    /**
     * doProcess_NWAL2030Scrn00_CMN_Submit
     * @param cMsg NWAL2030CMsg
     * @param globalCompanyCode String
     */
    private void doProcess_NWAL2030Scrn00_CMN_Submit(NWAL2030CMsg cMsg, String globalCompanyCode, NWAL2030SMsg sMsg) {
        doProcess_NWAL2030Scrn00_Search(cMsg, globalCompanyCode, false, sMsg);
    }
    //2016/04/04 S21_NA#6370 MOD End -------------- 

    private void doProcess_NWAL2030Scrn00_DeleteLine(NWAL2030CMsg cMsg, String globalCompanyCode) {
        int copyPos = 0;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NWAL2030_ACMsg acMsg = cMsg.A.no(i);
            if (!ZYPConstant.FLG_ON_Y.equals(acMsg.xxChkBox_A.getValue())) {
                if (i != copyPos) {
                    EZDMsg.copy(acMsg, null, cMsg.A.no(copyPos), null);
                }
                copyPos++;
            }
        }
        if (cMsg.A.getValidCount() == copyPos) {
            cMsg.setMessageInfo(NWAM0186E);
            return;
        }
        cMsg.A.setValidCount(copyPos);
    }

    //2016/04/04 S21_NA#6370 MOD Start --------------
    //private void doProcess_NWAL2030Scrn00_Search(NWAL2030CMsg cMsg, String globalCompanyCode, boolean notFoundMsgFlg) {
    private void doProcess_NWAL2030Scrn00_Search(NWAL2030CMsg cMsg, String globalCompanyCode, boolean notFoundMsgFlg, NWAL2030SMsg sMsg) {
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
    //2016/04/04 S21_NA#6370 MOD End --------------
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        //2016/04/04 S21_NA#6370 MOD
        NWAL2030CommonLogic.getSearchData(cMsg, notFoundMsgFlg, sMsg);
        //2016/04/04 S21_NA#6370 MOD
        for (int numC = 0; numC < cMsg.A.getValidCount(); numC++) {
            for (int numL = 0; numL < cMsg.L.getValidCount(); numL++) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(numC).hldLvlCd_CD.no(numL), cMsg.L.no(numL).hldLvlCd_L);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(numC).hldLvlDescTxt_SC.no(numL), cMsg.L.no(numL).hldLvlDescTxt_L);
            }
            for (int numW = 0; numW < cMsg.W.getValidCount(); numW++) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(numC).ordProcNodeCd_CD.no(numW), cMsg.W.no(numW).ordProcNodeCd_W);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(numC).ordProcNodeDescTxt_SC.no(numW), cMsg.W.no(numW).ordProcNodeDescTxt_W);
            }
        }
    }

}
