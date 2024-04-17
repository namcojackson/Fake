/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2460.common;

import static business.servlet.NMAL2460.constant.NMAL2460Constant.BIZ_ID;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_CMN_APL;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_CMN_APR;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_CMN_CLR;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_CMN_DEL;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_CMN_DWL;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_CMN_RJT;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_CMN_RST;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_CMN_RTN;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_CMN_SAV;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_CMN_SUB;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_01;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_02;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_03;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_04;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_05;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_06;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_07;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_08;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_09;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_10;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_11;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_12;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_13;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_14;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_15;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_16;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_17;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_18;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_19;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_OPENWIN_TRTYDTL_20;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_REQ_HIS;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SEARCH;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_01;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_02;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_03;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_04;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_05;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_06;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_07;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_08;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_09;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_10;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_11;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_12;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_13;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_14;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_15;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_16;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_17;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_18;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_19;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_SRCH_TRTY_INFO_20;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_UP_FILE;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.BTN_VIEW_ACCT;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.CLMN_ACCT_TRTY_RESRC_RQST_HDR_PK;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.CLMN_RQST_CRAT_SYS_TP_DESC_TXT;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.CLMN_RQST_CRAT_TS;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.CLMN_RQST_RSLT_CMNT_TXT;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.CLMN_RQST_RSLT_TP_DESC_TXT;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.CLMN_RQST_USR_ID;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.CLMN_RQST_USR_NM;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.DISPLAY_NM_CMNT;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.DISPLAY_NM_EMP_ID;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.DISPLAY_NM_EMP_NM;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.DISPLAY_NM_RQST_DT;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.DISPLAY_NM_RQST_ID;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.DISPLAY_NM_RQST_STS;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.DISPLAY_NM_RQST_TP;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.FUNC_ID_EDT;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.FUNC_ID_INQ;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.NMAM0192E;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.NMAM0204E;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.NMAM8461E;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.NMAM8548E;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.PRM_LENGTH_NMAL2530;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.PRM_LENGTH_NMAL2570;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.PRM_LENGTH_NMAL2630;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.PRM_LENGTH_NMAL6760;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.SCREEN_ID;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBStringItem;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL2460.NMAL2460BMsg;
import business.servlet.NMAL2460.NMAL2460_BBMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SRCH_LYOT_TP;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/27   Hitachi         O.Okuma          Create          N/A
 * 2016/09/02   SRAA            Y.Chen           Update          QC#12193
 * 2017/11/28   Fujitsu         Hd.Sugawara      Update          QC#21044
 * 2018/03/07   Fujitsu         K.Ishizuka       Update          QC#23152
 *</pre>
 */
public class NMAL2460CommonLogic {
    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2460BMsg
     */
    public static final void controlScreen(EZDCommonHandler handler, NMAL2460BMsg scrnMsg) {
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        activateButtons(handler, scrnMsg, functionList);
    }

    /**
     * <pre>
     * set protect to search result table.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2460BMsg
     */
    public static final void protectSearchResult(EZDCommonHandler handler, NMAL2460BMsg scrnMsg) {
        if (SRCH_LYOT_TP.LAYOUT_2.equals(scrnMsg.srchLyotTpCd.getValue())) {
            scrnMsg.B.setInputProtected(true);
            scrnMsg.rqstRsltCmntTxt.setInputProtected(false);

//            boolean isEnable;                // 2016/06/16 CSA-QC# Delete
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                NMAL2460_BBMsg bbMsg = scrnMsg.B.no(i);
                if (ZYPConstant.FLG_ON_Y.equals(bbMsg.xxValUpdFlg_B.getValue())) {
                    protectAllAttribute(handler, scrnMsg, i, false);
                    continue;
                } else {
                    protectAllAttribute(handler, scrnMsg, i, true);
                }

                
                // 2016/06/16 CSA-QC# Delete Start
//                scrnMsg.B.no(i).manEntryFlg_B.setInputProtected(false);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_O1);
//                bbMsg.orgNm_B1.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_01, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_01, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_O2);
//                bbMsg.orgNm_B2.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_02, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_02, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_O3);
//                bbMsg.orgNm_B3.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_03, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_03, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_O4);
//                bbMsg.orgNm_B4.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_04, i, isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_04, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_O5);
//                bbMsg.orgNm_B5.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_05, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_05, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_O6);
//                bbMsg.orgNm_B6.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_06, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_06, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_O7);
//                bbMsg.orgNm_B7.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_07, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_07, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_O8);
//                bbMsg.orgNm_B8.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_08, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_08, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_O9);
//                bbMsg.orgNm_B9.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_09, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_09, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_BA);
//                bbMsg.orgNm_BA.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_10, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_10, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_BB);
//                bbMsg.orgNm_BB.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_11, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_11, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_BC);
//                bbMsg.orgNm_BC.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_12, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_12, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_BD);
//                bbMsg.orgNm_BD.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_13, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_13, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_BE);
//                bbMsg.orgNm_BE.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_14, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_14, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_BF);
//                bbMsg.orgNm_BF.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_15, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_15, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_BG);
//                bbMsg.orgNm_BG.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_16, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_16, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_BH);
//                bbMsg.orgNm_BH.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_17, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_17, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_BI);
//                bbMsg.orgNm_BI.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_18, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_18, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_BJ);
//                bbMsg.orgNm_BJ.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_19, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_19, i, isEnable);
//
//                isEnable = ZYPCommonFunc.hasValue(bbMsg.acctTrtyOrgCd_BK);
//                bbMsg.orgNm_BK.setInputProtected(!isEnable);
//                handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_20, i, isEnable);
//                handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_20, i, isEnable);
                // 2016/06/16 CSA-QC# Delete End
            }
        } else {
            scrnMsg.A.setInputProtected(true);
            scrnMsg.rqstRsltCmntTxt.setInputProtected(true);
        }
    }

    /**
     * protectAllAttribute
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2460BMsg
     * @param i int
     * @param isEnable boolean
     */
    private static void protectAllAttribute(EZDCommonHandler handler, NMAL2460BMsg scrnMsg, int i, boolean isEnable) {
        scrnMsg.B.no(i).manEntryFlg_B.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_B1.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_B2.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_B3.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_B4.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_B5.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_B6.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_B7.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_B8.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_B9.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_BA.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_BB.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_BC.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_BD.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_BE.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_BF.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_BG.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_BH.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_BI.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_BJ.setInputProtected(!isEnable);
        scrnMsg.B.no(i).orgNm_BK.setInputProtected(!isEnable);

        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_01, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_02, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_03, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_04, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_05, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_06, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_07, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_08, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_09, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_10, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_11, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_12, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_13, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_14, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_15, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_16, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_17, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_18, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_19, i, isEnable);
        handler.setButtonEnabled(BTN_OPENWIN_TRTYDTL_20, i, isEnable);

        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_01, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_02, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_03, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_04, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_05, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_06, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_07, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_08, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_09, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_10, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_11, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_12, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_13, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_14, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_15, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_16, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_17, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_18, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_19, i, isEnable);
        handler.setButtonEnabled(BTN_SRCH_TRTY_INFO_20, i, isEnable);
    }

    /**
     * activateButtons
     * @param handler S21CommonHandler
     * @param scrnMsg NMAL2460BMsg
     * @param functionList List<String>
     */
    private static void activateButtons(EZDCommonHandler handler, NMAL2460BMsg scrnMsg, List<String> functionList) {
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

        boolean isProtect = true;

        if (functionList == null || functionList.isEmpty()) {
            scrnMsg.setMessageInfo(NMAM0204E);

        } else if (functionList.contains(FUNC_ID_EDT)) {
            isProtect = false;
            if (scrnMsg.A.getValidCount() > 0 || scrnMsg.B.getValidCount() > 0) {
                handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
            }
            if (scrnMsg.B.getValidCount() > 0) {
                handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
            }
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);

        } else if (functionList.contains(FUNC_ID_INQ)) {
            isProtect = false;
            if (scrnMsg.A.getValidCount() > 0 || scrnMsg.B.getValidCount() > 0) {
                handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
            }
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
            removeSrchLyotTpList(scrnMsg); // 2018/03/07 S21_NA#23152 Add
        }

        protectSearchCriteria(scrnMsg, isProtect);
        setButtonEnabled(handler, scrnMsg, !isProtect);
        controlScreenField(handler, scrnMsg);
        protectSearchResult(handler, scrnMsg);
    }
    
    // 2018/03/07 S21_NA#23152 Add Start
    private static void removeSrchLyotTpList(NMAL2460BMsg scrnMsg) {
        List<String> aftCdList = new ArrayList<String>();
        List<String> aftTxtList = new ArrayList<String>();
        for (int i = 0; i < scrnMsg.srchLyotTpCd_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.srchLyotTpCd_L.no(i))) {
                break;
            }
            if (!SRCH_LYOT_TP.LAYOUT_2.equals(scrnMsg.srchLyotTpCd_L.no(i).getValue())) {
                aftCdList.add(scrnMsg.srchLyotTpCd_L.no(i).getValue());
                aftTxtList.add(scrnMsg.srchLyotTpDescTxt_L.no(i).getValue());
            }
        }
        scrnMsg.srchLyotTpCd_L.clear();
        scrnMsg.srchLyotTpDescTxt_L.clear();

        for (int i = 0; i < aftCdList.size(); i++) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.srchLyotTpCd_L.no(i), aftCdList.get(i));
            ZYPEZDItemValueSetter.setValue(scrnMsg.srchLyotTpDescTxt_L.no(i), aftTxtList.get(i));
        }
    }
    // 2018/03/07 S21_NA#23152 Add End

    /**
     * activateButtonsForSearch
     * @param handler S21CommonHandler
     * @param scrnMsg NMAL2460BMsg
     */
    public static void controlScreenField(EZDCommonHandler handler, NMAL2460BMsg scrnMsg) {
        scrnMsg.xxPageShowCurNum_A.setInputProtected(false);
        scrnMsg.xxPageShowCurNum_B.setInputProtected(false);
        handler.setButtonEnabled(BTN_VIEW_ACCT, false);

        if (SRCH_LYOT_TP.LAYOUT_1.equals(scrnMsg.srchLyotTpCd.getValue())) {
            if (scrnMsg.xxPageShowCurNum_A.getValue().compareTo(BigDecimal.ONE) > 0) {
                scrnMsg.xxPageShowCurNum_A.setInputProtected(true);
            }
            if (scrnMsg.A.getValidCount() > 0) {
                handler.setButtonEnabled(BTN_VIEW_ACCT, true);
            }
        } else if (SRCH_LYOT_TP.LAYOUT_2.equals(scrnMsg.srchLyotTpCd.getValue())) {
            if (scrnMsg.xxPageShowCurNum_B.getValue().compareTo(BigDecimal.ONE) > 0) {
                scrnMsg.xxPageShowCurNum_B.setInputProtected(true);
            }
            if (scrnMsg.B.getValidCount() > 0) {
                handler.setButtonEnabled(BTN_VIEW_ACCT, true);
            }
        }
    }

    /**
     * setButtonEnabled
     * @param handler S21CommonHandler
     * @param scrnMsg NMAL2460BMsg
     * @param isEnabled boolean
     */
    private static void setButtonEnabled(EZDCommonHandler handler, NMAL2460BMsg scrnMsg, boolean isEnabled) {
        handler.setButtonEnabled(BTN_SEARCH, isEnabled);
        handler.setButtonEnabled(BTN_REQ_HIS, isEnabled);
        handler.setButtonEnabled(BTN_UP_FILE, isEnabled);
    }

    /**
     * protectSearchCriteria
     * @param scrnMsg NMAL2460BMsg
     * @param protectflg boolean
     */
    private static void protectSearchCriteria(NMAL2460BMsg scrnMsg, boolean protectflg) {
        scrnMsg.bizAreaOrgCd_H.setInputProtected(protectflg);
        scrnMsg.dsAcctTpCd_H.setInputProtected(protectflg);
        scrnMsg.dsAcctNm_H.setInputProtected(protectflg);
        scrnMsg.dsAcctNum_H.setInputProtected(protectflg);
        scrnMsg.bizAreaOrgCd_H.setInputProtected(protectflg);
        scrnMsg.dsAcctNum_H.setInputProtected(protectflg);
        scrnMsg.orgNm_H1.setInputProtected(protectflg);
        scrnMsg.trtyGrpTpCd_H.setInputProtected(protectflg);
        scrnMsg.fullPsnNm_H.setInputProtected(protectflg);
        scrnMsg.psnCd_H.setInputProtected(protectflg);
        scrnMsg.orgNm_H2.setInputProtected(protectflg);
        scrnMsg.lineBizTpCd_H.setInputProtected(protectflg);
        scrnMsg.xxCratDt_HF.setInputProtected(protectflg);
        scrnMsg.xxCratDt_HT.setInputProtected(protectflg);
        scrnMsg.xxLineCatgSrchTxt_H.setInputProtected(protectflg);
        scrnMsg.locNum_H.setInputProtected(protectflg);
        scrnMsg.ctyAddr_H.setInputProtected(protectflg);
        scrnMsg.stCd_H.setInputProtected(protectflg);
        scrnMsg.postCd_HF.setInputProtected(protectflg);
        scrnMsg.postCd_HT.setInputProtected(protectflg);
        scrnMsg.srchLyotTpCd_H.setInputProtected(protectflg);
        scrnMsg.dsAcctNm_LK.setInputProtected(protectflg);
        scrnMsg.dsAcctNum_LK.setInputProtected(protectflg);
        scrnMsg.orgNm_LK.setInputProtected(protectflg);
        scrnMsg.fullPsnNm_LK.setInputProtected(protectflg);
        scrnMsg.orgNm_L2.setInputProtected(protectflg);
        scrnMsg.locNum_LK.setInputProtected(protectflg);
        scrnMsg.xxComnColOrdTxt.setInputProtected(protectflg);
        scrnMsg.rqstRsltCmntTxt.setInputProtected(protectflg);
        if (protectflg) {
            scrnMsg.dsAcctNm_LK.clear();
            scrnMsg.dsAcctNum_LK.clear();
            scrnMsg.orgNm_LK.clear();
            scrnMsg.fullPsnNm_LK.clear();
            scrnMsg.orgNm_L2.clear();
            scrnMsg.locNum_LK.clear();
        } else {
            scrnMsg.dsAcctNm_LK.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.dsAcctNum_LK.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.orgNm_LK.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.fullPsnNm_LK.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.orgNm_L2.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.locNum_LK.setValue(ZYPConstant.FLG_ON_Y);
        }
    }

    /**
     * setRowColors
     * @param scrnMsg NMAL2460BMsg
     */
    public static void setRowColors(NMAL2460BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);

        if (SRCH_LYOT_TP.LAYOUT_1.equals(scrnMsg.srchLyotTpCd.getValue())) {
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        } else {
            tblColor.setAlternateRowsBG("B", scrnMsg.B);
        }
    }

    /**
     * addCheck Search Critera
     * @param scrnMsg NMAL2460BMsg
     */
    public static void addCheckSearchCriteria(NMAL2460BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.bizAreaOrgCd_H);
        scrnMsg.addCheckItem(scrnMsg.dsAcctTpCd_H);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H);
        scrnMsg.addCheckItem(scrnMsg.bizAreaOrgCd_H);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H);
        scrnMsg.addCheckItem(scrnMsg.orgNm_H1);
        scrnMsg.addCheckItem(scrnMsg.trtyGrpTpCd_H);
        scrnMsg.addCheckItem(scrnMsg.fullPsnNm_H);
        scrnMsg.addCheckItem(scrnMsg.psnCd_H);
        scrnMsg.addCheckItem(scrnMsg.orgNm_H2);
        scrnMsg.addCheckItem(scrnMsg.lineBizTpCd_H);
        scrnMsg.addCheckItem(scrnMsg.xxCratDt_HF);
        scrnMsg.addCheckItem(scrnMsg.xxCratDt_HT);
        scrnMsg.addCheckItem(scrnMsg.xxLineCatgSrchTxt_H);
        scrnMsg.addCheckItem(scrnMsg.locNum_H);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr_H);
        scrnMsg.addCheckItem(scrnMsg.stCd_H);
        scrnMsg.addCheckItem(scrnMsg.postCd_HF);
        scrnMsg.addCheckItem(scrnMsg.postCd_HT);
        scrnMsg.addCheckItem(scrnMsg.srchLyotTpCd_H);
    }

    /**
     * addCheck Search Result
     * @param scrnMsg NMAL2460BMsg
     */
    public static void addCheckSearchResult(NMAL2460BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.rqstRsltCmntTxt);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_B2);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_B3);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_B4);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_B5);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_B6);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_B7);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_B8);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_B9);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_BA);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_BB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_BC);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_BD);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_BE);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_OF);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_BF);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_BG);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_BH);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_BI);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_BJ);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_BK);
        }
    }

    /**
     * clearParam
     * @param scrnMsg NMAL2460BMsg
     */
    public static void clearParam(NMAL2460BMsg scrnMsg) {

        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();
        scrnMsg.xxPopPrm_14.clear();
        scrnMsg.xxPopPrm_15.clear();
        scrnMsg.xxPopPrm_16.clear();
    }

    /**
     * setParamForResourceLookup
     * @param scrnMsg NMAL2460BMsg
     * @return Object[]
     */
    public static Object[] setParamForResourceLookup(NMAL2460BMsg scrnMsg) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, scrnMsg.psnCd_H);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, scrnMsg.fullPsnNm_H);

        Object[] params = new Object[PRM_LENGTH_NMAL2570];

        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;

        return params;
    }

    /**
     * setParamForTerritoryPopup
     * @param scrnMsg NMAL2460BMsg
     * @return Object[]
     */
    public static Object[] setParamForTerritoryPopup(NMAL2460BMsg scrnMsg) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, scrnMsg.orgNm_H1);

        Object[] params = new Object[PRM_LENGTH_NMAL2630];

        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;
        params[i++] = scrnMsg.xxPopPrm_05;
        params[i++] = scrnMsg.xxPopPrm_06;
        params[i++] = scrnMsg.xxPopPrm_07;
        params[i++] = scrnMsg.xxPopPrm_08;
        // Add Start 2017/11/28 QC#21044
        params[i++] = scrnMsg.xxPopPrm_09;
        params[i++] = scrnMsg.xxPopPrm_10;
        params[i++] = scrnMsg.xxPopPrm_11;
        // Add End 2017/11/28 QC#21044

        return params;
    }

    // Mod Start 2017/11/28 QC#21044
    ///**
    // * setParamForTerritoryPopupDtl
    // * @param scrnMsg NMAL2460BMsg
    // * @param orgNm EZDBStringItem
    // * @return Object[]
    // */
    //public static Object[] setParamForTerritoryPopupDtl(NMAL2460BMsg scrnMsg, EZDBStringItem orgNm) {
    /**
     * setParamForTerritoryPopupDtl
     * @param scrnMsg NMAL2460BMsg
     * @param orgNm EZDBStringItem
     * @param slsCrQuotFlg EZDBStringItem
     * @return Object[]
     */
    public static Object[] setParamForTerritoryPopupDtl(NMAL2460BMsg scrnMsg, EZDBStringItem orgNm, EZDBStringItem slsCrQuotFlg) {
        // Mod End 2017/11/28 QC#21044

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, orgNm);
        // Add Start 2017/11/28 QC#21044
        if (ZYPConstant.FLG_ON_Y.equals(slsCrQuotFlg.getValue())) {
            // If LINE_BIZ_ROLE_TP.SLS_CR_QUOT_FLG = 'Y', Sales Rep Territory.
            // -> DS_ORG_RESRC_RELN.NON_SLS_REP_FLG = 'N'
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_11, ZYPConstant.FLG_OFF_N);
        } else if (ZYPConstant.FLG_OFF_N.equals(slsCrQuotFlg.getValue())) {
            // If LINE_BIZ_ROLE_TP.SLS_CR_QUOT_FLG = 'N', Specialist Territory.
            // ->  DS_ORG_RESRC_RELN.NON_SLS_REP_FLG = 'Y'
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_11, ZYPConstant.FLG_ON_Y);
        }
        // Add End 2017/11/28 QC#21044

        Object[] params = new Object[PRM_LENGTH_NMAL2630];

        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;
        params[i++] = scrnMsg.xxPopPrm_05;
        params[i++] = scrnMsg.xxPopPrm_06;
        params[i++] = scrnMsg.xxPopPrm_07;
        params[i++] = scrnMsg.xxPopPrm_08;
        // Add Start 2017/11/28 QC#21044
        params[i++] = scrnMsg.xxPopPrm_09;
        params[i++] = scrnMsg.xxPopPrm_10;
        // NON_SLS_REP_FLG
        params[i++] = scrnMsg.xxPopPrm_11;
        // Add End 2017/11/28 QC#21044

        return params;
    }

    /**
     * setParamForOrganizationPopup
     * @param scrnMsg NMAL2460BMsg
     * @return Object[]
     */
    public static Object[] setParamForOrganizationPopup(NMAL2460BMsg scrnMsg) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, scrnMsg.orgNm_H2);
        // QC#12193
        scrnMsg.xxPopPrm_01.setValue(BIZ_AREA_ORG.SALES);

        Object[] params = new Object[PRM_LENGTH_NMAL2530];

        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;
        params[i++] = scrnMsg.xxPopPrm_05;
        params[i++] = scrnMsg.xxPopPrm_06;
        params[i++] = scrnMsg.xxPopPrm_07;

        return params;
    }

    /**
     * setParamForAccountPopup
     * @param scrnMsg NMAL2460BMsg
     * @return Object[]
     */
    public static Object[] setParamForAccountPopup(NMAL2460BMsg scrnMsg) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, scrnMsg.dsAcctNum_H);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, scrnMsg.dsAcctNm_H);

        Object[] params = new Object[PRM_LENGTH_NMAL6760];

        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;
        params[i++] = scrnMsg.xxPopPrm_05;
        params[i++] = scrnMsg.xxPopPrm_06;
        params[i++] = scrnMsg.xxPopPrm_07;
        params[i++] = scrnMsg.xxPopPrm_08;
        params[i++] = scrnMsg.xxPopPrm_09;
        params[i++] = scrnMsg.xxPopPrm_10;
        params[i++] = scrnMsg.xxPopPrm_11;
        params[i++] = scrnMsg.xxPopPrm_12;
        params[i++] = scrnMsg.xxPopPrm_13;
        params[i++] = scrnMsg.xxPopPrm_14;
        params[i++] = scrnMsg.xxPopPrm_15;
        params[i++] = scrnMsg.xxPopPrm_16;

        return params;
    }

    /**
     * setParamForAccountPopup
     * @param scrnMsg NMAL2460BMsg
     * @return Object[]
     */
    public static Object[] setParamForLocSrch(NMAL2460BMsg scrnMsg) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, scrnMsg.locNum_H);

        Object[] params = new Object[PRM_LENGTH_NMAL6760];

        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;
        params[i++] = scrnMsg.xxPopPrm_05;
        params[i++] = scrnMsg.xxPopPrm_06;
        params[i++] = scrnMsg.xxPopPrm_07;
        params[i++] = scrnMsg.xxPopPrm_08;
        params[i++] = scrnMsg.xxPopPrm_09;
        params[i++] = scrnMsg.xxPopPrm_10;
        params[i++] = scrnMsg.xxPopPrm_11;
        params[i++] = scrnMsg.xxPopPrm_12;
        params[i++] = scrnMsg.xxPopPrm_13;
        params[i++] = scrnMsg.xxPopPrm_14;
        params[i++] = scrnMsg.xxPopPrm_15;
        params[i++] = scrnMsg.xxPopPrm_16;

        return params;
    }

    /**
     * Method name : setRqstHstSelectStr
     * @param glblCmpyCd String
     * @return String
     */
    public static String setRqstHstSelectStr(String glblCmpyCd) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT ");
        sb.append("    ASG.ACCT_TRTY_RESRC_RQST_HDR_PK ");
        sb.append(" ,  ASG.RQST_USR_ID ");
        sb.append(" ,  TO_CHAR( TO_TIMESTAMP(ASG.RQST_CRAT_TS, 'YYYYMMDDHH24MISSFF3'),'MM/DD/YYYY HH24:MI:SS') AS RQST_CRAT_TS ");
        sb.append(" ,  ASG.RQST_RSLT_CMNT_TXT ");
        sb.append(" ,  AUTH.FIRST_NM || ' ' || AUTH.LAST_NM AS RQST_USR_NM ");
        sb.append(" ,  RSLT.RQST_RSLT_TP_DESC_TXT ");
        sb.append(" ,  CRAT.RQST_CRAT_SYS_TP_DESC_TXT ");
        sb.append(" ,  ASG.GLBL_CMPY_CD ");
        sb.append(" ,  ASG.EZCANCELFLAG ");
        sb.append("FROM ");
        sb.append("    ACCT_TRTY_RESRC_RQST_HDR ASG ");
        sb.append(" ,  AUTH_PSN                 AUTH ");
        sb.append(" ,  RQST_RSLT_TP             RSLT ");
        sb.append(" ,  RQST_CRAT_SYS_TP         CRAT ");
        sb.append("WHERE ");
        sb.append("    ASG.GLBL_CMPY_CD        = '");
        sb.append(glblCmpyCd);
        sb.append("' ");
        sb.append("AND ASG.GLBL_CMPY_CD        = AUTH.GLBL_CMPY_CD ");
        sb.append("AND ASG.RQST_USR_ID         = AUTH.USR_NM ");
        sb.append("AND ASG.GLBL_CMPY_CD        = RSLT.GLBL_CMPY_CD ");
        sb.append("AND ASG.RQST_RSLT_TP_CD     = RSLT.RQST_RSLT_TP_CD ");
        sb.append("AND ASG.GLBL_CMPY_CD        = CRAT.GLBL_CMPY_CD ");
        sb.append("AND ASG.RQST_CRAT_SYS_TP_CD = CRAT.RQST_CRAT_SYS_TP_CD ");
        sb.append("AND ASG.EZCANCELFLAG        = '0' ");
        sb.append("AND AUTH.EZCANCELFLAG       = '0' ");
        sb.append("AND RSLT.EZCANCELFLAG       = '0' ");
        sb.append("AND CRAT.EZCANCELFLAG       = '0' ");

        return sb.toString();
    }

    /**
     * setRqstHstWhereList
     * @return List<Objext[]> List
     */
    public static List<Object[]> setRqstHstWhereList() {
        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = DISPLAY_NM_RQST_ID;
        whereArray0[1] = CLMN_ACCT_TRTY_RESRC_RQST_HDR_PK;
        whereArray0[2] = null;
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = DISPLAY_NM_EMP_ID;
        whereArray1[1] = CLMN_RQST_USR_ID;
        whereArray1[2] = null;
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = DISPLAY_NM_EMP_NM;
        whereArray2[1] = CLMN_RQST_USR_NM;
        whereArray2[2] = null;
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[4];
        whereArray3[0] = DISPLAY_NM_RQST_DT;
        whereArray3[1] = CLMN_RQST_CRAT_TS;
        whereArray3[2] = null;
        whereArray3[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);

        Object[] whereArray4 = new Object[4];
        whereArray4[0] = DISPLAY_NM_RQST_STS;
        whereArray4[1] = CLMN_RQST_RSLT_TP_DESC_TXT;
        whereArray4[2] = null;
        whereArray4[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray4);

        Object[] whereArray5 = new Object[4];
        whereArray5[0] = DISPLAY_NM_RQST_TP;
        whereArray5[1] = CLMN_RQST_CRAT_SYS_TP_DESC_TXT;
        whereArray5[2] = null;
        whereArray5[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray5);

        Object[] whereArray6 = new Object[4];
        whereArray6[0] = DISPLAY_NM_CMNT;
        whereArray6[1] = CLMN_RQST_RSLT_CMNT_TXT;
        whereArray6[2] = null;
        whereArray6[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray6);

        return whereList;
    }

    /**
     * setRqstHstColumnList
     * @return List<Objext[]> columnList
     */
    public static List<Object[]> setRqstHstColumnList() {
        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] setColumnArray0 = new Object[4];
        setColumnArray0[0] = DISPLAY_NM_RQST_ID;
        setColumnArray0[1] = CLMN_ACCT_TRTY_RESRC_RQST_HDR_PK;
        setColumnArray0[2] = BigDecimal.valueOf(10);
        setColumnArray0[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(setColumnArray0);

        Object[] setColumnArray1 = new Object[4];
        setColumnArray1[0] = DISPLAY_NM_EMP_ID;
        setColumnArray1[1] = CLMN_RQST_USR_ID;
        setColumnArray1[2] = BigDecimal.valueOf(10);
        setColumnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(setColumnArray1);

        Object[] setColumnArray2 = new Object[4];
        setColumnArray2[0] = DISPLAY_NM_EMP_NM;
        setColumnArray2[1] = CLMN_RQST_USR_NM;
        setColumnArray2[2] = BigDecimal.valueOf(15);
        setColumnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(setColumnArray2);

        Object[] setColumnArray3 = new Object[4];
        setColumnArray3[0] = DISPLAY_NM_RQST_DT;
        setColumnArray3[1] = CLMN_RQST_CRAT_TS;
        setColumnArray3[2] = BigDecimal.valueOf(15);
        setColumnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(setColumnArray3);

        Object[] setColumnArray4 = new Object[4];
        setColumnArray4[0] = DISPLAY_NM_RQST_STS;
        setColumnArray4[1] = CLMN_RQST_RSLT_TP_DESC_TXT;
        setColumnArray4[2] = BigDecimal.valueOf(13);
        setColumnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(setColumnArray4);

        Object[] setColumnArray5 = new Object[4];
        setColumnArray5[0] = DISPLAY_NM_RQST_TP;
        setColumnArray5[1] = CLMN_RQST_CRAT_SYS_TP_DESC_TXT;
        setColumnArray5[2] = BigDecimal.valueOf(13);
        setColumnArray5[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(setColumnArray5);

        Object[] setColumnArray6 = new Object[4];
        setColumnArray6[0] = DISPLAY_NM_CMNT;
        setColumnArray6[1] = CLMN_RQST_RSLT_CMNT_TXT;
        setColumnArray6[2] = BigDecimal.valueOf(50);
        setColumnArray6[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(setColumnArray6);

        return columnList;
    }

    /**
     * setRqstHstSortList
     * @return List<Objext[]> sortCondList
     */
    public static List<Object[]> setRqstHstSortList() {
        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = CLMN_RQST_CRAT_TS;
        sortConditionArray0[1] = "DESC";
        sortCondList.add(sortConditionArray0);

        return sortCondList;
    }

    /**
     * checkInput
     * @param scrnMsg
     */
    public static void checkInput(NMAL2460BMsg scrnMsg){

        // Error if all search criteria are blank
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_H) && !ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H) && !ZYPCommonFunc.hasValue(scrnMsg.orgNm_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.trtyGrpTpCd_H) && !ZYPCommonFunc.hasValue(scrnMsg.fullPsnNm_H) && !ZYPCommonFunc.hasValue(scrnMsg.psnCd_H)
                && !ZYPCommonFunc.hasValue(scrnMsg.orgNm_H2) && !ZYPCommonFunc.hasValue(scrnMsg.lineBizTpCd_H) && !ZYPCommonFunc.hasValue(scrnMsg.xxCratDt_HF)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxCratDt_HT) && !ZYPCommonFunc.hasValue(scrnMsg.xxLineCatgSrchTxt_H) && !ZYPCommonFunc.hasValue(scrnMsg.locNum_H)
                && !ZYPCommonFunc.hasValue(scrnMsg.ctyAddr_H) && !ZYPCommonFunc.hasValue(scrnMsg.stCd_H) && !ZYPCommonFunc.hasValue(scrnMsg.postCd_HF)
                && !ZYPCommonFunc.hasValue(scrnMsg.postCd_HT)) {
            scrnMsg.setMessageInfo(NMAM0192E);
            throw new EZDFieldErrorException();
        }

        // Error if Business area is not selected
        if (!ZYPCommonFunc.hasValue(scrnMsg.bizAreaOrgCd_H)) {
            scrnMsg.bizAreaOrgCd_H.setErrorInfo(1, NMAM8461E, new String[] {"Business Area" });
            scrnMsg.addCheckItem(scrnMsg.bizAreaOrgCd_H);
        }

        // Error if Search Layout is not selected
        if (!ZYPCommonFunc.hasValue(scrnMsg.srchLyotTpCd_H)) {
            scrnMsg.srchLyotTpCd_H.setErrorInfo(1, NMAM8461E, new String[] {"Search Layout" });
            scrnMsg.addCheckItem(scrnMsg.srchLyotTpCd_H);
        }

        // Check postal Code format
        if (ZYPCommonFunc.hasValue(scrnMsg.postCd_HF) && ZYPCommonFunc.hasValue(scrnMsg.postCd_HT)) {
            if (scrnMsg.postCd_HF.getValue().startsWith("%")) {
                scrnMsg.postCd_HF.setErrorInfo(1, NMAM8548E);
                scrnMsg.addCheckItem(scrnMsg.postCd_HF);
            }
            if (scrnMsg.postCd_HT.getValue().startsWith("%")) {
                scrnMsg.postCd_HT.setErrorInfo(1, NMAM8548E);
                scrnMsg.addCheckItem(scrnMsg.postCd_HT);
            }
        }

        scrnMsg.putErrorScreen();

    }
}
