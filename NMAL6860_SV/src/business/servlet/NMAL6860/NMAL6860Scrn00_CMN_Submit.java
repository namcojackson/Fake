/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860;

import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.disabledButtonProperties;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.initializeButtonProperties;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.TAB_NM_GENERAL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import parts.transitioncommon.EZDTransition;
import business.blap.NMAL6860.NMAL6860CMsg;
import business.servlet.NMAL6860.common.NMAL6860CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRNT_VND_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/16   CITS            T.Gotoda        Create          N/A
 * 2016/10/03   CITS            R.Shimamoto     Update          QC#12768
 * 2019/12/23   Fujitsu         R.Nakamura      Update          QC#54971-1
 * 2020/10/28   CITS            R.Kurahashi     Update          QC#57732
 * 2020/12/12   CITS            R.Kurahashi     Update          QC#57732-1
 * 2021/03/01   CITS            G.Delgado       Update          QC#56057
 *</pre>
 */
public class NMAL6860Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;

        if (TAB_NM_GENERAL.equals(scrnMsg.xxDplyTab.getValue())) {
            NMAL6860CommonLogic.addCheckGeneralTab(scrnMsg);
        } else {
            NMAL6860CommonLogic.addCheckDetailTab(scrnMsg);
        }

        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            setNextTransition(EZDTransition.STAY, null);
            return;
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return NMAL6860CommonLogic.copyScrnMsgToBizMsgForUpdate((NMAL6860BMsg) bMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;
        NMAL6860CMsg bizMsg = (NMAL6860CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            // START 2021/03/01 G.Delgado [QC#56057,ADD]
            scrnMsg.addCheckItem(scrnMsg.prntVndTpDescTxt);
            // END 2021/03/01 G.Delgado [QC#56057,ADD]
            // START 2020/10/28 R.Kurahashi [QC#57732,ADD]
            scrnMsg.addCheckItem(scrnMsg.prntVndNm);
            // END 2020/10/28 R.Kurahashi [QC#57732,END]
            if (TAB_NM_GENERAL.equals(scrnMsg.xxDplyTab.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
                scrnMsg.addCheckItem(scrnMsg.vndPmtTermDescTxt_H1);

                // Add Check Item for Address Validation
                for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
                    scrnMsg.addCheckItem(scrnMsg.A.no(index).ctryCd_A);
                    scrnMsg.addCheckItem(scrnMsg.A.no(index).xxComnScrFirstValTxt_A);
                    scrnMsg.addCheckItem(scrnMsg.A.no(index).xxComnScrScdValTxt_A);
                    scrnMsg.addCheckItem(scrnMsg.A.no(index).ctyAddr_A);
                    scrnMsg.addCheckItem(scrnMsg.A.no(index).postCd_A);
                    scrnMsg.addCheckItem(scrnMsg.A.no(index).stCd_A);
                    scrnMsg.addCheckItem(scrnMsg.A.no(index).cntyNm_A);

                    scrnMsg.addCheckItem(scrnMsg.A.no(index).xxComnScrFirstValTxt_AL);
                    scrnMsg.addCheckItem(scrnMsg.A.no(index).xxComnScrFirstValTxt_AP);
                    scrnMsg.addCheckItem(scrnMsg.A.no(index).xxComnScrFirstValTxt_AV);
                }
            } else {
                scrnMsg.addCheckItem(scrnMsg.billToCustCd_H2);
                scrnMsg.addCheckItem(scrnMsg.vndPmtTermDescTxt_H2);

                scrnMsg.addCheckItem(scrnMsg.xxComnScrFirstValTxt_H2);
                scrnMsg.addCheckItem(scrnMsg.xxComnScrScdValTxt_H2);

                // Add Start 2019/12/24 QC#54971-1
                for (int index = 0; index < scrnMsg.B.getValidCount(); index++) {
                    scrnMsg.addCheckItem(scrnMsg.B.no(index).ctacPsnLastNm_B);
                    scrnMsg.addCheckItem(scrnMsg.B.no(index).ctacPsnFirstNm_B);
                    scrnMsg.addCheckItem(scrnMsg.B.no(index).dsCtacPsnTtlCd_B);
                    scrnMsg.addCheckItem(scrnMsg.B.no(index).dsCtacPntValTxt_BT);
                    scrnMsg.addCheckItem(scrnMsg.B.no(index).dsCtacPntValTxt_BF);
                    scrnMsg.addCheckItem(scrnMsg.B.no(index).dsCtacPsnDeptCd_B);
                    scrnMsg.addCheckItem(scrnMsg.B.no(index).ctacTpCd_B);
                    scrnMsg.addCheckItem(scrnMsg.B.no(index).dsCtacPntValTxt_BE);
                    scrnMsg.addCheckItem(scrnMsg.B.no(index).inacDt_B);
                }
                // Add End 2019/12/24 QC#54971-1
            }

            scrnMsg.putErrorScreen();
            return;
        }

        // Del Start 2019/12/23 QC#54971-1
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_NM_GENERAL);
        // Del End 2019/12/23 QC#54971-1
        scrnMsg.setFocusItem(scrnMsg.prntVndNm);

        // sets the input fields of Common Header.
        NMAL6860CommonLogic.setInputProtectedForCommonHeader(scrnMsg);

        // START 2021/03/01 G.Delgado [QC#56057,MOD]
        if (scrnMsg.prntVndTpDescTxt.isInputProtected()) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_D1, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_D1, ZYPConstant.FLG_OFF_N);
        }

        // Mod Start 2019/12/23 QC#54971-1
        // if (TAB_NM_GENERAL.equals(scrnMsg.xxDplyTab.getValue())) {
        // // sets the input fields of General Tab.
        // NMAL6860CommonLogic.setInputProtectedForGeneralTab(scrnMsg);
        // } else {
        // // sets the input fields of Detail Tab.
        // NMAL6860CommonLogic.setInputProtectedForDetailTab(scrnMsg);
        // }
        // Mod End 2019/12/23 QC#54971-1

        // sets the input fields of General Tab.
        NMAL6860CommonLogic.setInputProtectedForGeneralTab(scrnMsg);
        // sets the input fields of Detail Tab.
        NMAL6860CommonLogic.setInputProtectedForDetailTab(scrnMsg);

        initializeButtonProperties(this, scrnMsg);
        // END 2021/03/01 G.Delgado [QC#56057,MOD]

        // START 2020/12/12 R.Kurahashi [QC#57732-1,ADD]
        if (scrnMsg.prntVndTpCd.getValue().equals(PRNT_VND_TP.ARREFUND) && ZYPCommonFunc.hasValue(scrnMsg.prntVndCd)) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                disabledButtonProperties(this, i);
            }
        }
        // END 2020/12/12 R.Kurahashi [QC#57732-1,ADD]

        NMAL6860CommonLogic.setCursorRuleForSwhDetail(scrnMsg);
    }
}
