/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFBL2040.NFBL2040CMsg;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;
import business.servlet.NFBL2040.constant.NFBL2040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_AP_INV_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/21   Hitachi         T.Tsuchida      Update          QC#12039
 * 2016/07/29   Hitachi         K.Kojima        Update          QC#12665
 * 2016/09/14   Hitachi         Y.Tsuchimoto    Update          QC#13333
 * 2016/09/15   Hitachi         Y.Tsuchimoto    Update          QC#13156
 * 2016/11/15   Hitachi         K. Kasai        Update          QC#15904
 * 2017/01/13   Hitachi         E.Kameishi      Update          QC#16949
 * 2018/05/25   CITS            K.Ogino         Update          QC#25902,QC#25190,QC#25141
 * 2020/03/16   Fujitsu         H.Mizukami      Update          QC#55993
 * 2020/03/23   Fujitsu         H.Ikeda         Update          QC#53413
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 *</pre>
 */
public class NFBL2040Scrn00_DeleteRow extends S21CommonHandler implements NFBL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        // START 2016/07/21 T.Tsuchida [QC#12039,ADD]
        // START 2016/09/15 Y.Tsuchimoto [QC#13156,MOD]
//        if (!ZYPCommonFunc.hasValue(scrnMsg.cmApInvTpCd_S)
//                || !CM_AP_INV_TP.STANDARD.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.cmApInvTpCd_S)
                || CM_AP_INV_TP.PO_MATCH.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
        // END 2016/09/15 Y.Tsuchimoto [QC#13156,MOD]
        // END 2016/07/21 T.Tsuchida [QC#12039,ADD]
            if (scrnMsg.cmApInvTpCd_HD.isClear()
            &&  scrnMsg.poNum_HD.isClear()
            // START QC#25902,QC#25190,QC#25141
            &&  scrnMsg.delyOrdNum_HD.isClear()
            &&  scrnMsg.vndRtrnNum_HD.isClear()) {
            // END QC#25902,QC#25190,QC#25141
                // NACM0090E=0,Please search for invoices first.
                scrnMsg.setMessageInfo(NACM0090E);
                throw new EZDFieldErrorException();
            }
        // START 2016/07/21 T.Tsuchida [QC#12039,ADD]
        }
        // END 2016/07/21 T.Tsuchida [QC#12039,ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/11/15 [QC#15904, ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
        }
        scrnMsg.putErrorScreen();
        // END 2016/11/15 [QC#15904, ADD]
        if (MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
            // START 2020/03/16 [QC#55993, ADD]
            NFBL2040CommonLogic.clearHoldReleasePending(scrnMsg, true);
            // END   2020/03/16 [QC#55993, ADD]
            throw new EZDFieldErrorException();
        }

        // START 2016/09/15 Y.Tsuchimoto [QC#13156,MOD]
        String dispMode = NFBL2040CommonLogic.getDisplayMode(this, scrnMsg);
        if (DISPLAY_MODE_NOT_OTH_SCR_EDIT.equals(dispMode)) {
            // Common
            NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditCommon(scrnMsg);
            // START 2020/03/23 [QC#53413, MOD]
            // mod start 2022/02/15 QC#57090
            //boolean mtBtnFlg = false;
            //if (ZYPCommonFunc.hasValue(scrnMsg.vndRtrnNum)) {
            //    mtBtnFlg = false;
            //} else {
            //    mtBtnFlg = true;
            //}
            ////NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg);
            //NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg, mtBtnFlg);
            NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg, true);
            // mod end 2022/02/15 QC#57090
            // END   2020/03/23 [QC#53413, MOD]
            // START 2016/11/15 [QC#15904, DEL]
//            NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditForChangeCmApInvTpCd(scrnMsg);
//            NFBL2040CommonLogic.setButtonNotFromOthScrForEditForChangeCmApInvTpCd(this, scrnMsg);
            // END 2016/11/15 [QC#15904, DEL]

            // START   2017/02/23 E.Kameishi [QC#12988,MOD]
            NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditForLinesTab(scrnMsg, true);
            // END   2017/02/23 E.Kameishi [QC#12988,MOD]
            NFBL2040CommonLogic.setButtonNotFromOthScrForEditForEditForLinesTab(this, scrnMsg, false);
        } else {
            /** Initialize button control */
            NFBL2040CommonLogic.initControl(this, scrnMsg);
        }
        // END   2016/09/15 Y.Tsuchimoto [QC#13156,MOD]

        /** Set alternate rows background color */
        NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);
        // START 2017/01/13 E.Kameishi [QC#16949,MOD]
        /** Set focus when opening screen */
        //scrnMsg.setFocusItem(scrnMsg.cmApInvTpCd_S);
        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.dplyVndNm);
        }
        NFBL2040CommonLogic.setFocusRule(scrnMsg);
        // END 2017/01/13 E.Kameishi [QC#16949,MOD]

        // START 2016/07/29 K.Kojima [QC#12665,ADD]
        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
        // END 2016/07/29 K.Kojima [QC#12665,ADD]
        // START 2020/03/16 [QC#55993, ADD]
        NFBL2040CommonLogic.clearHoldReleasePending(scrnMsg, true);
        // END   2020/03/16 [QC#55993, ADD]
    }
}
