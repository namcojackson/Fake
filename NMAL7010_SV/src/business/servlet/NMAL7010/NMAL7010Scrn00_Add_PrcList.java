/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import static business.servlet.NMAL7010.constant.NMAL7010Constant.BIZ_ID;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_EQUIP;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_LEASE_RATE;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_LEASE_RTRN_FEE;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_QTY_DISC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SPLY_PGM;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC_SPEC_CHRG;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC_TIER;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_TI;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7010.NMAL7010CMsg;
import business.servlet.NMAL7010.common.NMAL7010CommonLogic;
import business.servlet.NMAL7010.constant.NMAL7010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7010Scrn00_Add_PrcList
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   SRAA            Y.Chen          Create          QC#2175
 * 2017/02/23   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7010Scrn00_Add_PrcList extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        NMAL7010CMsg bizMsg = new NMAL7010CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        NMAL7010CMsg bizMsg  = (NMAL7010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int selectIdx = 0;
        // Add Start 2017/02/23 QC#16011
        boolean updateAuthFlg = NMAL7010CommonLogic.updateAuthority(getUserProfileService());
        // Add End 2017/02/23 QC#16011
        if (TAB_PRC_LIST_VAL_EQUIP.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            selectIdx = bizMsg.A.getValidCount() - 1;
            // Mod Start 2017/02/24 QC#16011
//            NMAL7010CommonLogic.prcListQlfyTpCtrl(this, NMAL7010Constant.BTN_PRC_QLFY_VAL, selectIdx, scrnMsg.A.no(selectIdx).prcQlfyTpCd_PA.getValue(), scrnMsg);
            NMAL7010CommonLogic.prcListQlfyTpCtrl(this, NMAL7010Constant.BTN_PRC_QLFY_VAL, selectIdx, scrnMsg.A.no(selectIdx).prcQlfyTpCd_PA.getValue(), scrnMsg, updateAuthFlg);
            // Mod End 2017/02/24 QC#16011
            scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).prcQlfyValTxt_PA);
        } else if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            selectIdx = bizMsg.B.getValidCount() - 1;
            // Mod Start 2017/02/23 QC#16011
//            NMAL7010CommonLogic.prcListRateTypeCtrl(this, scrnMsg, selectIdx);
            NMAL7010CommonLogic.prcListRateTypeCtrl(this, scrnMsg, selectIdx, updateAuthFlg);
            // Mod End 2017/02/23 QC#16011
            scrnMsg.setFocusItem(scrnMsg.B.no(selectIdx).prcRateTpCd_PB);
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            selectIdx = bizMsg.C.getValidCount() - 1;
            scrnMsg.setFocusItem(scrnMsg.C.no(selectIdx).mdlNm_PC);
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            selectIdx = bizMsg.D.getValidCount() - 1;
            scrnMsg.setFocusItem(scrnMsg.D.no(selectIdx).mdseCd_PD);
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            selectIdx = bizMsg.E.getValidCount() - 1;
            scrnMsg.setFocusItem(scrnMsg.E.no(selectIdx).mdlNm_PE);
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            selectIdx = bizMsg.F.getValidCount() - 1;
            scrnMsg.setFocusItem(scrnMsg.F.no(selectIdx).prcLeaseCmpyAbbrNm_PF);
        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            selectIdx = bizMsg.G.getValidCount() - 1;
            scrnMsg.setFocusItem(scrnMsg.G.no(selectIdx).prcLeaseCmpyAbbrNm_PG);
        } else if (TAB_PRC_LIST_VAL_TI.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            selectIdx = bizMsg.H.getValidCount() - 1;
            scrnMsg.setFocusItem(scrnMsg.H.no(selectIdx).mdlNm_PH);
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            selectIdx = bizMsg.I.getValidCount() - 1;
            // Mod Start 2017/02/24 QC#16011
//            NMAL7010CommonLogic.prcListQlfyTpCtrl(this, NMAL7010Constant.BTN_PRC_QLFY_VAL, selectIdx, scrnMsg.I.no(selectIdx).prcQlfyTpCd_PI.getValue(), scrnMsg);
            NMAL7010CommonLogic.prcListQlfyTpCtrl(this, NMAL7010Constant.BTN_PRC_QLFY_VAL, selectIdx, scrnMsg.I.no(selectIdx).prcQlfyTpCd_PI.getValue(), scrnMsg, updateAuthFlg);
            // Mod End 2017/02/24 QC#16011
            scrnMsg.setFocusItem(scrnMsg.I.no(selectIdx).prcQlfyValTxt_PI);
        }

        // Mod Start 2017/02/23 QC#16011
//        NMAL7010CommonLogic.btnCtrlSubmit(this, scrnMsg);
//        NMAL7010CommonLogic.scrnAllGUIControl(this, scrnMsg);
        NMAL7010CommonLogic.btnCtrlSubmit(this, scrnMsg, updateAuthFlg);
        NMAL7010CommonLogic.scrnAllGUIControl(this, scrnMsg, getUserProfileService());
        // Mod End 2017/02/23 QC#16011
    }
}
