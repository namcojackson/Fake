/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0490;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0490.NSAL0490CMsg;
import business.servlet.NSAL0490.common.NSAL0490CommonLogic;
import business.servlet.NSAL0490.constant.NSAL0490Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/09   Fujitsu         T.Yoshida       Create          N/A
 * 2015/10/07   Hitachi         Y.Tsuchimoto    Update          N/A
 * 2016/05/19   Hitachi         K.Kasai         Update          QC#447
 * 2016/05/30   Hitachi         K.Kasai         Update          QC#6675
 * 2017/12/22   Hitachi         U.Kim           Update          QC#22448
 * 2018/01/15   Hitachi         K.Kojima        Update          QC#23352
 *</pre>
 */
public class NSAL0490_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), NSAL0490Constant.BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;

        // set Invoker screen value.
        Serializable arg = super.getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params.length == 1) {
                EZDBStringItem param0 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdlNm_PR, param0.getValue());
            }
        }

        NSAL0490CMsg bizMsg = new NSAL0490CMsg();
        bizMsg.setBusinessID(NSAL0490Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NSAL0490Constant.FUNCTION_SEARCH);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;
        NSAL0490CMsg bizMsg = (NSAL0490CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0490CommonLogic.initControlCommonButton(this);

        if (NSAL0490CommonLogic.hasUpdateFuncId()) {
            NSAL0490CommonLogic.controlCommonButton(this, scrnMsg, true);
            NSAL0490CommonLogic.controlInitField(this, scrnMsg, true);

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRsltFlg.getValue())) {
                NSAL0490CommonLogic.controlSearchField(this, scrnMsg, false);
            }
            NSAL0490CommonLogic.controlItemConfigField(this, scrnMsg);
        } else {
            NSAL0490CommonLogic.controlCommonButton(this, scrnMsg, false);
            NSAL0490CommonLogic.controlInitField(this, scrnMsg, false);
            NSAL0490CommonLogic.setPageForItemConfig(scrnMsg);

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRsltFlg.getValue())) {
                NSAL0490CommonLogic.controlSearchField(this, scrnMsg, false);
            } else {
                NSAL0490CommonLogic.controlSearchField(this, scrnMsg, true);
            }
        }

        NSAL0490CommonLogic.controlInitTabBtn(scrnMsg);
        NSAL0490CommonLogic.setTblBackColorItemConfig(scrnMsg);

        scrnMsg.xxRadioBtn_A.setValue(0);
        scrnMsg.setFocusItem(scrnMsg.mdlNm);
        scrnMsg.xxDplyTab.setValue(NSAL0490Constant.TAB_ITEM_CONFIG);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;

        // set Header
        scrnMsg.mdlNm.setNameForMessage(NSAL0490Constant.ITEM_NM_MDL_NM);
        scrnMsg.mdlDescTxt.setNameForMessage(NSAL0490Constant.ITEM_NM_MDL_DESP);
        scrnMsg.mdlGrpNm.setNameForMessage(NSAL0490Constant.ITEM_NM_MDL_GRP);

        // set Item Configurations
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).prntMdseCd_A.setNameForMessage(NSAL0490Constant.ITEM_NM_PRNT_MDSE_CD);
            scrnMsg.A.no(i).childMdseCd_A.setNameForMessage(NSAL0490Constant.ITEM_NM_CHILD_MDSE_CD);
            scrnMsg.A.no(i).effFromDt_A.setNameForMessage(NSAL0490Constant.ITEM_NM_START_DT);
            scrnMsg.A.no(i).effThruDt_A.setNameForMessage(NSAL0490Constant.ITEM_NM_END_DT);
        }

        // set Service Rules
        scrnMsg.rcllIntvlDaysAot.setNameForMessage(NSAL0490Constant.ITEM_NM_RCLL_INTVL_DAYS_AOT);
        scrnMsg.rcllCopyVolCnt.setNameForMessage(NSAL0490Constant.ITEM_NM_RCLL_COPY_VOL_CNT);
        scrnMsg.rcllGlblIntvlDaysAot.setNameForMessage(NSAL0490Constant.ITEM_NM_RCLL_GLBL_INTVL_DAYS_AOT);
        scrnMsg.rcllGlblCopyVolCnt.setNameForMessage(NSAL0490Constant.ITEM_NM_RCLL_GLBL_COPY_VOL_CNT);
        // mod start 2016/05/30 CSA Defect#6675
        scrnMsg.xxRtoTaskTmNum.setNameForMessage(NSAL0490Constant.ITEM_NM_RSP_TM_UP_MN_AOT);
        // mod end 2016/05/30 CSA Defect#6675
        scrnMsg.xsVisitCnt.setNameForMessage(NSAL0490Constant.ITEM_NM_XS_VISIT_CNT);
        scrnMsg.phoneFixIntvlDaysAot.setNameForMessage(NSAL0490Constant.ITEM_NM_PHONE_FIX_INTVL_DAYS_AOT);
        scrnMsg.copyVolDaysAot.setNameForMessage(NSAL0490Constant.ITEM_NM_COPY_VOL_DAYS_AOT);
        scrnMsg.maxCopyPerDayTotCnt.setNameForMessage(NSAL0490Constant.ITEM_NM_MAX_COPY_PER_DAY_TOT_CNT);
        scrnMsg.maxCopyPerDayBlackCnt.setNameForMessage(NSAL0490Constant.ITEM_NM_MAX_COPY_PER_DAY_BLACK_CNT);
        scrnMsg.maxCopyTestCnt.setNameForMessage(NSAL0490Constant.ITEM_NM_MAX_COPY_TEST_CNT);
        scrnMsg.mdlSpeedBlackRate.setNameForMessage(NSAL0490Constant.ITEM_NM_MDL_SPEED_BLACK_RATE);
        scrnMsg.mdlSpeedColorRate.setNameForMessage(NSAL0490Constant.ITEM_NM_MDL_SPEED_COLOR_RATE);
        // 2015/10/07 CSA Y.Tsuchimoto Add Start
        scrnMsg.machInFldInacMthAot.setNameForMessage(NSAL0490Constant.ITEM_NM_MACH_IN_FLD_INAC_MTH_AOT);
        scrnMsg.mdlDurnTmNum.setNameForMessage(NSAL0490Constant.ITEM_NM_MDL_DURN_TM_NUM);
        // 2015/10/07 CSA Y.Tsuchimoto Add End

        // set Supply Mapping
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).mdseCd_B.setNameForMessage(NSAL0490Constant.ITEM_NM_MDSE_CD);
            scrnMsg.B.no(i).splyTolPct_B.setNameForMessage(NSAL0490Constant.ITEM_NM_SPLY_TOI_PCT);
            scrnMsg.B.no(i).custStkQty_B.setNameForMessage(NSAL0490Constant.ITEM_NM_CUST_STK_QTY);
            // 2015/10/07 CSA Y.Tsuchimoto Add Start
            scrnMsg.B.no(i).splyInitQty_B.setNameForMessage(NSAL0490Constant.ITEM_NM_SPLY_INIT_QTY);
            scrnMsg.B.no(i).splyContrQty_B.setNameForMessage(NSAL0490Constant.ITEM_NM_SPLY_CONTR_QTY);
            // 2015/10/07 CSA Y.Tsuchimoto Add End
            scrnMsg.B.no(i).effFromDt_B.setNameForMessage(NSAL0490Constant.ITEM_NM_START_DT);
            scrnMsg.B.no(i).effThruDt_B.setNameForMessage(NSAL0490Constant.ITEM_NM_END_DT);
        }
        // add start 2016/05/19 CSA Defect#447
        //set End Of Life
        scrnMsg.dsMdlEolStsCd_D1.setNameForMessage(NSAL0490Constant.ITEM_NM_STATUS);
        scrnMsg.dsMdlEolStsCd_D2.setNameForMessage(NSAL0490Constant.ITEM_NM_STATUS);
        scrnMsg.dsMdlEolStsCd_D3.setNameForMessage(NSAL0490Constant.ITEM_NM_STATUS);
        scrnMsg.dsMdlEolDt_D1.setNameForMessage(NSAL0490Constant.ITEM_NM_DATE);
        scrnMsg.dsMdlEolDt_D2.setNameForMessage(NSAL0490Constant.ITEM_NM_DATE);
        scrnMsg.dsMdlEolDt_D3.setNameForMessage(NSAL0490Constant.ITEM_NM_DATE);
        scrnMsg.eolSvcContrCmntTxt.setNameForMessage(NSAL0490Constant.ITEM_NM_SVC_CONTR_INFO);
        scrnMsg.eolTmMatCmntTxt.setNameForMessage(NSAL0490Constant.ITEM_NM_TM_INFO);
        scrnMsg.eolTechSprtCmntTxt.setNameForMessage(NSAL0490Constant.ITEM_NM_TECH_SUPT_INFO);
        scrnMsg.eolOthCmntTxt.setNameForMessage(NSAL0490Constant.ITEM_NM_OTH_INFO);
        // START 2017/12/22 U.Kim [QC#22448, ADD]
        scrnMsg.eolDisptCmntTxt.setNameForMessage(NSAL0490Constant.ITEM_NM_DISPT_CMNT);
        // END 2017/12/22 U.Kim [QC#22448, ADD]
        // add end 2016/05/19 CSA Defect#447
        // START 2018/01/15 K.Kojima [QC#23352,ADD]
        scrnMsg.svcMdlTpCd.setNameForMessage(NSAL0490Constant.ITEM_NM_MODEL_TYPE);
        // END 2018/01/15 K.Kojima [QC#23352,ADD]
    }
}
