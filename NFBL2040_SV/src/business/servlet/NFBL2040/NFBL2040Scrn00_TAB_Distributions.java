/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL2040.NFBL2040CMsg;
//import business.servlet.NFBL2040.constant.NFBL2040Constant;

import business.blap.NFBL2040.NFBL2040CMsg;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;
import business.servlet.NFBL2040.constant.NFBL2040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/29   Hitachi         K.Kojima        Update          QC#12665
 * 2016/09/28   Hitachi         Y.Tsuchimoto    Update          QC#14797
 * 2016/10/06   Hitachi         Y.Tsuchimoto    Update          QC#15036
 * 2017/01/13   Hitachi         E.Kameishi      Update          QC#16949
 * 2017/10/18   CITS            T.Tokutomi      Update          QC#21642
 * 2020/03/16   Fujitsu         H.Mizukami      Update          QC#55993
 *</pre>
 */
public class NFBL2040Scrn00_TAB_Distributions extends S21CommonHandler implements NFBL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // QC#21642 add Error check.
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if(!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxCmntTxt_A1)){
                scrnMsg.A.no(i).xxCmntTxt_A1.setErrorInfo(1, "ZZMM0005E", new String[]{"Charge Account"});
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxCmntTxt_A1);
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageTblNm, TABLE_D);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // QC#21642 add Error check.
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxCmntTxt_A1);
        }

        // START 2020/03/16 [QC#55993, ADD]
        NFBL2040CommonLogic.offHoldReleaseCheck(scrnMsg);
        // END   2020/03/16 [QC#55993, ADD]
        scrnMsg.putErrorScreen();

        // START 2016/09/28 Y.Tsuchimoto [QC#14797,MOD]
        String dispMode = NFBL2040CommonLogic.getDisplayMode(this, scrnMsg);
        if (DISPLAY_MODE_OTH_SCR_EDIT.equals(dispMode) || DISPLAY_MODE_OTH_SCR_REF.equals(dispMode) || DISPLAY_MODE_NOT_OTH_SCR_EDIT.equals(dispMode) || DISPLAY_MODE_NOT_OTH_SCR_REF.equals(dispMode)) {
            // DISTRIBUTIONS TAB
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                scrnMsg.D.no(i).xxDtlLineNum_D1.setInputProtected(true);  // Line Number
                scrnMsg.D.no(i).xxDtlLineNum_D2.setInputProtected(true);  // Distribution Line Number
                scrnMsg.D.no(i).invDt_D1.setInputProtected(true);         // Date
                scrnMsg.D.no(i).jrnlFuncDrAmt_D1.setInputProtected(true); // Debit
                scrnMsg.D.no(i).jrnlFuncCrAmt_D1.setInputProtected(true); // Credit
                // START 2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
                scrnMsg.D.no(i).apAcctDescTxt_D1.setInputProtected(true);   // Account Description
                // END   2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
                scrnMsg.D.no(i).xxCmntTxt_D1.setInputProtected(true);     // Account Code
            }
        }
        // END   2016/09/28 Y.Tsuchimoto [QC#14797,MOD]

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_02, TAB_DISTRIBUTIONS);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageTblNm, TABLE_D);
        /** Set alternate rows background color */
        NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);

        // START 2016/07/29 K.Kojima [QC#12665,ADD]
        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
        // END 2016/07/29 K.Kojima [QC#12665,ADD]
        // START 2017/01/13 E.Kameishi [QC#16949,ADD]
        scrnMsg.setFocusItem(scrnMsg.xxAllocTpCd_D);
        // START 2017/01/13 E.Kameishi [QC#16949,ADD]
        // START 2020/03/16 [QC#55993, ADD]
        NFBL2040CommonLogic.clearHoldReleasePending(scrnMsg, true);
        // END   2020/03/16 [QC#55993, ADD]
    }
}
