/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0780;

import static business.blap.NSAL0780.constant.NSAL0780Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL0780.common.NSAL0780CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Fleet Rollup Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   Hitachi         A.Kohinata      Create          N/A
 * 2016/03/11   Hitachi         T.Kanasaka      Update          QC4164
 * 2017/03/01   Hitachi         K.Kitachi       Update          QC#17752
 * 2017/03/10   Hitachi         K.Kitachi       Update          QC#17752
 *</pre>
 */
public class NSAL0780BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NSAL0780CMsg cMsg = (NSAL0780CMsg) arg0;
        NSAL0780SMsg sMsg = (NSAL0780SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0780_INIT".equals(screenAplID)) {
                doProcess_NSAL0780_INIT(cMsg, sMsg);
            // START 2016/03/11 T.Kanasaka [QC4164, MOD]
            } else if ("NSAL0780_NSAL0790".equals(screenAplID)) {
                doProcess_NSAL0780Scrn00_Search(cMsg, sMsg);
            // END 2016/03/11 T.Kanasaka [QC4164, MOD]
            } else if ("NSAL0780Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL0780Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSAL0780Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NSAL0780Scrn00_PageJump(cMsg, sMsg);
            } else if ("NSAL0780Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0780Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0780Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0780Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL0780Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0780Scrn00_Search(cMsg, sMsg);
            // START 2017/03/10 K.Kitachi [QC#17752, ADD]
            } else if ("NSAL0780Scrn00_Detail".equals(screenAplID)) {
                doProcess_NSAL0780Scrn00_Detail(cMsg, sMsg);
            // END 2017/03/10 K.Kitachi [QC#17752, ADD]
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0780_INIT(NSAL0780CMsg cMsg, NSAL0780SMsg sMsg) {

        if (NSAL0780CommonLogic.hasValueSearchCondition(cMsg)) {
            setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
            doProcess_NSAL0780Scrn00_Search(cMsg, sMsg);
        } else {
            NSAL0780CommonLogic.clearMsg(cMsg, sMsg);
            setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        }
    }

    private void doProcess_NSAL0780Scrn00_CMN_Clear(NSAL0780CMsg cMsg, NSAL0780SMsg sMsg) {

        NSAL0780CommonLogic.clearMsg(cMsg, sMsg);
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
    }

    private void doProcess_NSAL0780Scrn00_PageJump(NSAL0780CMsg cMsg, NSAL0780SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0780CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = (cMsg.xxPageShowCurNum.getValue().subtract(BigDecimal.ONE)).multiply(new BigDecimal(cMsg.A.length())).intValue();
        NSAL0780CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
        // START 2017/03/01 K.Kitachi [QC#17752, ADD]
        NSAL0780CommonLogic.setTableLayout(cMsg);
        // END 2017/03/01 K.Kitachi [QC#17752, ADD]
    }

    private void doProcess_NSAL0780Scrn00_PageNext(NSAL0780CMsg cMsg, NSAL0780SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0780CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL0780CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
        // START 2017/03/01 K.Kitachi [QC#17752, ADD]
        NSAL0780CommonLogic.setTableLayout(cMsg);
        // END 2017/03/01 K.Kitachi [QC#17752, ADD]
    }

    private void doProcess_NSAL0780Scrn00_PagePrev(NSAL0780CMsg cMsg, NSAL0780SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0780CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL0780CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
        // START 2017/03/01 K.Kitachi [QC#17752, ADD]
        NSAL0780CommonLogic.setTableLayout(cMsg);
        // END 2017/03/01 K.Kitachi [QC#17752, ADD]
    }

    private void doProcess_NSAL0780Scrn00_Search(NSAL0780CMsg cMsg, NSAL0780SMsg sMsg) {

        if (!NSAL0780CommonLogic.hasValueSearchCondition(cMsg)) {
            cMsg.setMessageInfo(NSAM0005E);
            return;
        }

        NSAL0780CommonLogic.getSearchData(cMsg, sMsg);
        NSAL0780CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        // START 2017/03/01 K.Kitachi [QC#17752, ADD]
        NSAL0780CommonLogic.setTableLayout(cMsg);
        // END 2017/03/01 K.Kitachi [QC#17752, ADD]
    }

    // START 2017/03/10 K.Kitachi [QC#17752, ADD]
    private void doProcess_NSAL0780Scrn00_Detail(NSAL0780CMsg cMsg, NSAL0780SMsg sMsg) {
        NSAL0780CommonLogic.createParamForDetailButton(cMsg, sMsg);
    }
    // END 2017/03/10 K.Kitachi [QC#17752, ADD]
}
