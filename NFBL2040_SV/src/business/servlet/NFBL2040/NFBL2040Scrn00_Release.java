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

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/29   Hitachi         K.Kojima        Update          QC#12665
 * 2016/08/05   Hitachi         Y.Tsuchimoto    Update          QC#12201
 * 2016/08/26   Hitachi         Y.Tsuchimoto    Update          QC#12043
 * 2016/09/26   Fujitsu         W.Honda         Update          Unit Test#201
 * 2017/01/13   Hitachi         E.Kameishi      Update          QC#16948
 * 2017/01/24   Hitachi         E.Kameishi      Update          QC#16948
 * 2018/01/05   Hitachi         Y.Takeno        Update          QC#22143
 * 2018/03/08   Hitachi         Y.Takeno        Update          QC#24275
 * 2018/08/01   Hitachi         Y.Takeno        Update          QC#27025-1
 * 2020/03/16   Fujitsu         H.Mizukami      Update          QC#55993
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 *</pre>
 */
public class NFBL2040Scrn00_Release extends S21CommonHandler implements NFBL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        // START 2017/01/13 E.Kameishi [QC#16948,MOD]
        NFBL2040CommonLogic.checkHoldRelease(scrnMsg);
//        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
//            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxChkBox_H1.getValue())) {
//                if (!ZYPCommonFunc.hasValue(scrnMsg.H.no(i).pmtHldRelRsnCd_H1.getValue())) {
//                    scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setErrorInfo(1, NLAM0047E, new String[] {"Release Reason" });
//                    scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldRelRsnCd_H1);
//                }
//                // START 2016/09/26 W.Honda [Unit Test#201,MOD]
//                if (PMT_HLD.THEREFORE.equals(scrnMsg.H.no(i).pmtHldCd_H1.getValue())) {
//                    List funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);
//
//                    if (!funcList.contains(FUNC_ID_NFBL2040T030)) {
//                        scrnMsg.H.no(i).pmtHldCd_H1.setErrorInfo(1, NFBM0254E);
//                        scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldCd_H1);
//                    }
//                }
//                // END 2016/09/26 W.Honda [Unit Test#201,MOD]
//            }
//        }
        // START 2016/08/05 Y.Tsuchimoto [QC#12201,ADD]
        int validCount = 0;
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxChkBox_H1.getValue())) {
                if (!scrnMsg.H.no(i).xxChkBox_H1.isInputProtected()) {
                    validCount = validCount + 1;
                }
            }
        }
        if (validCount == 0) {
            scrnMsg.setMessageInfo(NFBM0017E, new String[] {"Hold Release" });
            throw new EZDFieldErrorException();
        }
        // END   2016/08/05 Y.Tsuchimoto [QC#12201,ADD]
        // END 2017/01/13 E.Kameishi [QC#16948,MOD]
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_40);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2018/03/08 [QC#24275, ADD]
        NFBL2040CommonLogic.checkHoldRelease(scrnMsg);
        scrnMsg.putErrorScreen();
        // END   2018/03/08 [QC#24275, ADD]

        if (MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.setFocusItem(scrnMsg.cmApInvTpCd_S);

        // START 2016/08/26 Y.Tsuchimoto [QC#12043,MOD]
        // START 2018/08/01 [QC#27025-1, MOD]
        if (!MESSAGE_KIND_E.equals(bizMsg.getMessageKind()) 
                && scrnMsg.acctInvStsCd.getValue().equals(ACCT_INV_STS.AUTHORIZED)) {
            NFBL2040CommonLogic.initControl(this, scrnMsg);
        // START 2020/03/16 [QC#55993, MOD]
        } else if (!MESSAGE_KIND_W.equals(bizMsg.getMessageKind())){
        // END   2020/03/16 [QC#55993, MOD]
            NFBL2040CommonLogic.setButtonAfterRelease(this, scrnMsg);
            NFBL2040CommonLogic.setInputProtectedForAfterRelease(scrnMsg);
        }
        // END   2018/08/01 [QC#27025-1, MOD]
        // END   2016/08/26 Y.Tsuchimoto [QC#12043,MOD]

        // START 2016/07/29 K.Kojima [QC#12665,ADD]
        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
        // END 2016/07/29 K.Kojima [QC#12665,ADD]
        // add start 2022/02/15 QC#57090
        NFBL2040CommonLogic.clearMultiPoOrMultiVndRtrnData(scrnMsg);
        // add end 2022/02/15 QC#57090
        // START 2018/01/05 [QC#22143, DEL]
        // START 2017/01/24 E.Kameishi [QC#16948,ADD]
        // String acctInvSts = scrnMsg.acctInvStsCd.getValue();
        // if (!acctInvSts.equals(ACCT_INV_STS.ENTERED)) {
        //     this.setButtonEnabled(BTN_NORMAL_INSERT_ROW_HOLD, false);
        //     this.setButtonEnabled(BTN_NORMAL_DELETE_ROW_HOLD, false);
        //
        // }
        // this.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
        // END 2017/01/24 E.Kameishi [QC#16948,ADD]
        // END 2018/01/05 [QC#22143, DEL]
    }
}
