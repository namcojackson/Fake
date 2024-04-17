/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLEL0060;

import static business.blap.NLEL0060.constant.NLEL0060Constant.BIZ_ID;
import static business.blap.NLEL0060.constant.NLEL0060Constant.CHECK_BOX_A;
import static business.blap.NLEL0060.constant.NLEL0060Constant.CHECK_BOX_B;
import static business.blap.NLEL0060.constant.NLEL0060Constant.CHECK_BOX_C;
import static business.blap.NLEL0060.constant.NLEL0060Constant.COA_LEN;
import static business.blap.NLEL0060.constant.NLEL0060Constant.COL_CUR_VAL_AMT;
import static business.blap.NLEL0060.constant.NLEL0060Constant.COL_DEPC_CNT;
import static business.blap.NLEL0060.constant.NLEL0060Constant.COL_DS_ASSET_MSTR_PK;
import static business.blap.NLEL0060.constant.NLEL0060Constant.COL_LAST_DEPC_YR_MTH;
import static business.blap.NLEL0060.constant.NLEL0060Constant.COL_PRNT_DS_ASSET_MSTR_PK;
import static business.blap.NLEL0060.constant.NLEL0060Constant.CPO_DTL_LINE_NUM;
import static business.blap.NLEL0060.constant.NLEL0060Constant.CPO_DTL_LINE_SUB_NUM;
import static business.blap.NLEL0060.constant.NLEL0060Constant.DEPC_SMLTN_RQST_STS_CD_NE;
import static business.blap.NLEL0060.constant.NLEL0060Constant.MDSE_TP_CTX_TP_CD;
import static business.blap.NLEL0060.constant.NLEL0060Constant.MSG_ASSET_FOR_DEPC;
import static business.blap.NLEL0060.constant.NLEL0060Constant.MSG_CPO_ORD_NUM;
import static business.blap.NLEL0060.constant.NLEL0060Constant.MSG_CPO_ORD_LINE_NUM;
import static business.blap.NLEL0060.constant.NLEL0060Constant.MSG_DEPC_CNT;
import static business.blap.NLEL0060.constant.NLEL0060Constant.MSG_DEPC_MTH_NUM;
import static business.blap.NLEL0060.constant.NLEL0060Constant.MSG_DS_ACCT_NM;
import static business.blap.NLEL0060.constant.NLEL0060Constant.MSG_LOC;
import static business.blap.NLEL0060.constant.NLEL0060Constant.MSG_MDSE_TP_CD;
import static business.blap.NLEL0060.constant.NLEL0060Constant.MSG_PRNT_DS_ASSET_MSTR_PK;
import static business.blap.NLEL0060.constant.NLEL0060Constant.MSG_SAVE;
import static business.blap.NLEL0060.constant.NLEL0060Constant.MSG_VND_CD;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NLAM0023E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NLBM0009E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NLBM1063E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NLEM0007E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NLEM0032E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NLEM0035E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NLEM0038E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NLEM0041E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NLEM0042E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NLEM0044E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NLEM0046E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NLEM0049E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NLXM1010E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NLZM2273E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NLZM2283E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NZZM0002I;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NZZM0011E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.ON_SITE;
import static business.blap.NLEL0060.constant.NLEL0060Constant.SRCH_OPT_NM;
import static business.blap.NLEL0060.constant.NLEL0060Constant.STR_COMMA;
import static business.blap.NLEL0060.constant.NLEL0060Constant.TAB_ASG;
import static business.blap.NLEL0060.constant.NLEL0060Constant.TAB_DTL;
import static business.blap.NLEL0060.constant.NLEL0060Constant.TAB_FIN;
import static business.blap.NLEL0060.constant.NLEL0060Constant.TBL_DS_ASSET_MSTR;
import static business.blap.NLEL0060.constant.NLEL0060Constant.ZZM9000E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.ZZM9004E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.ZZM9033E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.ZZM9037E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.ZZZM9003I;
import static business.blap.NLEL0060.constant.NLEL0060Constant.ZZZM9006E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.ZZZM9012E;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLEL0060.common.NLEL0060CommonLogic;
import business.blap.NLEL0060.constant.NLEL0060Constant;
import business.db.AMT_CHNG_RSN_TPTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_ASSET_DEPC_SMLTN_RQSTTMsg;
import business.db.DS_ASSET_MSTRTMsg;
import business.db.PRNT_VNDTMsg;
import business.parts.NLZC305001PMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC305001.constant.NLZC305001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_MODE;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NLEL0060BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/04/14   Hitachi         J.Kim           Update          QC#6587
 * 2016/04/15   Hitachi         J.Kim           Update          QC#7043
 * 2016/04/15   Hitachi         T.Tsuchida      Update          QC#7050
 * 2016/04/18   Hitachi         J.Kim           Update          QC#6581
 * 2016/04/20   Hitachi         J.Kim           Update          QC#7112
 * 2016/04/21   Hitachi         J.Kim           Update          QC#7113
 * 2016/04/25   Hitachi         J.Kim           Update          QC#7461
 * 2016/05/10   Hitachi         K.Kojima        Update          QC#7144
 * 2016/05/11   Hitachi         K.Kojima        Update          QC#7113
 * 2016/05/11   Hitachi         K.Kojima        Update          QC#7065
 * 2016/05/12   Hitachi         K.Kojima        Update          QC#8124
 * 2016/06/20   Hitachi         J.Kim           Update          QC#10294
 * 2016/06/30   Hitachi         J.Kim           Update          QC#10864
 * 2016/07/07   Hitachi         J.Kim           Update          QC#11503
 * 2016/07/20   Hitachi         Y.Tsuchimoto    Update          QC#10561
 * 2016/07/27   Hitachi         J.Kim           Update          QC#11438
 * 2016/08/23   Fujitsu         C.Tanaka        Update          QC#11026
 * 2016/08/30   Fujitsu         C.Tanaka        Update          QC#13360
 * 2016/09/26   Fujitsu         C.Tanaka        Update          QC#12697, QC#11899
 * 2016/09/27   Hitachi         J.Kim           Update          QC#13372
 * 2016/10/17   Hitachi         J.Kim           Update          QC#13453
 * 2016/10/19   Hitachi         J.Kim           Update          QC#15345
 * 2016/10/27   Hitachi         J.Kim           Update          QC#11026
 * 2016/11/29   Fujitsu         T.Murai         Update          QC#15823
 * 2016/12/14   Hitachi         E.Kameishi      Update          QC#15988
 * 2017/01/13   Hitachi         J.Kim           Update          QC#17011
 * 2017/02/14   Hitachi         J.Kim           Update          QC#17440
 * 2017/02/21   Hitachi         J.Kim           Update          QC#17589
 * 2017/05/15   CITS            M.Naito         Update          Merge DS Lv2
 * 06/27/2017   CITS            Y.Imazu         Update          QC#19591
 * 10/26/2017   CITS            M.Naito         Update          QC#22052
 * 2017/11/07   Hitachi         J.Kim           Update          QC#16345
 * 2018/02/07   Hitachi         J.Kim           Update          QC#23890
 * 2018/02/26   Hitachi         J.Kim           Update          QC#22792
 * 2018/04/06   Hitachi         J.Kim           Update          QC#24561
 * 2018/04/16   Hitachi         J.Kim           Update          QC#22807
 * 2018/05/17   Hitachi         J.Kim           Update          QC#25879
 * 2018/07/24   Hitachi         J.Kim           Update          QC#24950
 * 2018/08/17   CITS            Y.Iwasaki       Update          QC#24426
 * 2018/08/21   Hitachi         J.Kim           Update          QC#27253
 * 2018/08/28   Fujitsu         S.Ohki          Update          QC#28000
 *</pre>
 */
public class NLEL0060BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NLEL0060CMsg bizMsg = (NLEL0060CMsg) cMsg;
            NLEL0060SMsg glblMsg = (NLEL0060SMsg) sMsg;

            if ("NLEL0060Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_Delete_Search".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_Delete_Search(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_Import".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_Import(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_Retire".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_Retire(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_Run_Depreciation".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_Run_Depreciation(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_Save_Search".equals(screenAplID)) {
                doProcess_NLEL0060Scrn00_Save_Search(bizMsg, glblMsg);

            // START 2016/10/07 J.Kim [QC#5521,ADD]
            } else if ("NLEL0060Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);

            } else if ("NLEL0060Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);
                // END 2016/10/07 J.Kim [QC#5521,ADD]

            } else {
                return;

            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_CMN_Submit(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        NLEL0060CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        // START 2016/12/14 E.Kameishi [QC#15988, ADD]
        if (!NLEL0060CommonLogic.checkInput(bizMsg, glblMsg)) {
            NLEL0060CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
            return;
        }
        // END 2016/12/14 E.Kameishi [QC#15988, ADD]
        if (!checkChangeValue(bizMsg, glblMsg)) {
            // START 2016/04/21 J.Kim [QC#7113,DEL]
            // bizMsg.setMessageInfo(NLEM0032E);
            // END 2016/04/21 J.Kim [QC#7113,DEL]
            NLEL0060CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
            return;
        }

        String tab = bizMsg.xxDplyTab.getValue();
        if (TAB_DTL.equals(tab)) {
            // START 2016/04/21 J.Kim [QC#7065,ADD]
            // START 2016/05/11 K.Kojima [QC#7065,MOD]
            // if (!isNumberCheck(bizMsg, glblMsg)) {
            if (!isNumberCheck(bizMsg, glblMsg, false)) {
                // END 2016/05/11 K.Kojima [QC#7065,MOD]
                NLEL0060CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
                return;
            }
            // END 2016/04/21 J.Kim [QC#7065,ADD]
            // START 2017/05/15 M.Naito
            if (!doSubmitForDtl(bizMsg, glblMsg)) {
                NLEL0060CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
                return;
            }
            // END 2017/05/15 M.Naito
        } else if (TAB_ASG.equals(tab)) {
            if (!checkRtrnWhCd(bizMsg, glblMsg)) {
                NLEL0060CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
                return;
            }
            // START 2016/10/17 J.Kim [QC#13453,ADD]
            if (!checkAccount(glblMsg)) {
                NLEL0060CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
                return;
            }
            // END 2016/10/17 J.Kim [QC#13453,ADD]
            // START 2017/05/15 M.Naito
            if (!doSubmitForAsg(bizMsg, glblMsg)) {
                NLEL0060CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
                return;
            }
            // END 2017/05/15 M.Naito
        } else if (TAB_FIN.equals(tab)) {
            if (!checkParent(bizMsg, glblMsg)) {
                NLEL0060CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
                return;
            }

            // START 2016/04/15 J.Kim [QC#7043,ADD]
            if (!checkCurrentValueAmount(bizMsg, glblMsg)) {
                NLEL0060CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
                return;
            }
            // END 2016/04/15 J.Kim [QC#7043,ADD]

            // START 2017/05/17 M.Naito
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxBtnFlg_CA.getValue())) {
                if (!doAdjust(bizMsg, glblMsg)) {
                    NLEL0060CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
                    return;
                }
            } else if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxBtnFlg_CM.getValue())) {
                if (!doMerge(bizMsg, glblMsg)) {
                    NLEL0060CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
                    return;
                }
            }
            // END 2017/05/17 M.Naito
        }
        // START 2017/10/26 M.Naito [QC#22052,ADD]
        bizMsg.setMessageInfo(ZZZM9003I, new String[] {"Submit" });
        // END 2017/10/26 M.Naito [QC#22052,ADD]
    }

    /**
     * Delete_Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_Delete_Search(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        NSZC033001PMsg pMsg = new NSZC033001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (NLEL0060CommonLogic.callNszc0330(bizMsg, pMsg)) {
            NLEL0060CommonLogic.createSaveOptPulldownList(bizMsg, getContextUserInfo().getUserId(), glblCmpyCd);
            bizMsg.srchOptPk.clear();
            bizMsg.setMessageInfo(NZZM0002I);
        }
    }

    /**
     * Import Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_Import(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        NLEL0060CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        if (!NLEL0060CommonLogic.checkInput(bizMsg, glblMsg)) {
            NLEL0060CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
            return;
        }
        // START 2016/04/21 J.Kim [QC#7065,ADD]
        // START 2016/05/11 K.Kojima [QC#6870,MOD]
        // if (!isNumberCheck(bizMsg, glblMsg)) {
        if (!isNumberCheck(bizMsg, glblMsg, true)) {
            // END 2016/05/11 K.Kojima [QC#6870,MOD]
            NLEL0060CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
            return;
        }
        // END 2016/04/21 J.Kim [QC#7065,ADD]

        // START 2016/10/27 J.Kim [QC#11026,MOD]
        if (!NLEL0060CommonLogic.checkAssetManualEntry(bizMsg, glblMsg)) {
            // END 2016/05/11 K.Kojima [QC#6870,MOD]
            NLEL0060CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
            return;
        }
        // END 2016/10/27 J.Kim [QC#11026,MOD]

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.A, CHECK_BOX_A, ZYPConstant.FLG_ON_Y);

        // START 2017/05/15 M.Naito
        if (!doImportForDtl(bizMsg, glblMsg, checkList)) {
            NLEL0060CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
            return;
        }
        // START 2017/10/26 M.Naito [QC#22052,ADD]
        bizMsg.setMessageInfo(ZZZM9003I, new String[] {"Import" });
        // END 2017/10/26 M.Naito [QC#22052,ADD]
        // END 2017/05/15 M.Naito
    }

    /**
     * Retire Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_Retire(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        // START 2018/04/06 J.Kim [QC#24561, DEL]
        // String glblCmpyCd = getGlobalCompanyCode();
        // String slsDt = ZYPDateUtil.getSalesDate();
        // String acctYrMth1 = slsDt;
        // String acctYrMth2 = bizMsg.acctYrMth_T1.getValue();

        // // Get date from ACCT_MTH_CTRL
        // ACCT_MTH_CTRLTMsg acctMthCtrlTMsg = new ACCT_MTH_CTRLTMsg();
        // ZYPEZDItemValueSetter.setValue(acctMthCtrlTMsg.glblCmpyCd, glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(acctMthCtrlTMsg.acctMthCtrlCd, ACCT_MTH_CTRL.DEPRICIATION_CONTROL);

        // acctMthCtrlTMsg = (ACCT_MTH_CTRLTMsg) S21FastTBLAccessor.findByKey(acctMthCtrlTMsg);
        // if (RTNCD_NORMAL.equals(acctMthCtrlTMsg.getReturnCode())) {
        //    acctYrMth2 = acctMthCtrlTMsg.acctYrMth.getValue();
        // }

        // // Check if Retire Date is valid
        // acctYrMth1 = S21StringUtil.subStringByLength(acctYrMth1, 0, 6);
        // acctYrMth2 = S21StringUtil.subStringByLength(acctYrMth2, 0, 6);
        // if (!acctYrMth1.equals(acctYrMth2)) {
        //    bizMsg.acctYrMth_T1.setErrorInfo(1, NLEM0031E, new String[] {ACCT_YR_MTH });
        //    return;
        // }
        // END 2018/04/06 J.Kim [QC#24561, DEL]

        // Copy to Global Message
        NLEL0060CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        // START 2017/05/17 M.Naito
        NLZC305001PMsg pMsg = new NLZC305001PMsg();

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.C, CHECK_BOX_C, ZYPConstant.CHKBOX_ON_Y);
        if (checkList == null || checkList.isEmpty()) {
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                glblMsg.C.no(i).xxChkBox_C1.setErrorInfo(1, NZZM0011E);
            }
            NLEL0060CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
            return;
        }

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());

        NLEL0060_CSMsg cSMsg;
        NLEL0060_BSMsg bSMsg;
        int i = 0;
        for (int j : checkList) {
            pMsg.updDtlList.no(i).clear();
            cSMsg = glblMsg.C.no(j);
            bSMsg = glblMsg.B.no(j);

            // check Location
            if (ZYPCommonFunc.hasValue(bSMsg.xxScrItem10Txt_B1) && !ON_SITE.equals(bSMsg.xxScrItem10Txt_B1.getValue())) {
                bizMsg.setMessageInfo(NLEM0049E);
                return;
            }
            // check updated by another user
            if (!NLEL0060CommonLogic.checkUpdatedByAnotherUser(bizMsg, getGlobalCompanyCode(), cSMsg.ezUpTime_C1.getValue(), cSMsg.ezUpTimeZone_C1.getValue(), cSMsg.dsAssetMstrPk_C1.getValue())) {
                return;
            }
            // do Retire
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).xxProcMd, NLZC305001Constant.PROC_MODE_DSPL);
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).prntDsAssetMstrPk, cSMsg.dsAssetMstrPk_C1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).assetRtireRsnCmntTxt, bizMsg.assetRtireRsnCmntTxt_T1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, PROC_MODE.RETIRED);
            pMsg.updDtlList.setValidCount(1);
            // call asset update Api (ProccessMode : 3)
            if (!NLEL0060CommonLogic.callAssetUpdateApi(bizMsg, pMsg)) {
                return;
            }
            // START 2017/10/26 M.Naito [QC#22052,ADD]
            bizMsg.setMessageInfo(ZZZM9003I, new String[] {"Retire" });
            // END 2017/10/26 M.Naito [QC#22052,ADD]
        }
        // END 2017/05/15 M.Naito
    }

    /**
     * Run_Depriciation Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_Run_Depreciation(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        // Get Asset for Depreciation
        // START 2016/04/15 T.Tsuchida [QC#7050,MOD]
        // Map<String, Object> map = NLEL0060Query.getInstance().getAssetForDepriciation(bizMsg.assetTpCd_T1.getValue());
        // if (map == null || map.isEmpty()) {
        // bizMsg.setMessageInfo(ZZZM9006E, new String[] {MSG_ASSET_FOR_DEPC });
        // return;
        // }
        List<Map<String, Object>> list = NLEL0060Query.getInstance().getAssetForDepriciation(bizMsg.assetTpCd_T1.getValue());

        if (list == null || list.isEmpty()) {

            bizMsg.setMessageInfo(ZZZM9006E, new String[] {MSG_ASSET_FOR_DEPC });
            return;
        }
        // START 2016/04/15 T.Tsuchida [QC#7050,MOD]

        // Save Depreciation Simulation Request
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        DS_ASSET_DEPC_SMLTN_RQSTTMsg dsAssetDepcSmltnRqstTMsg = new DS_ASSET_DEPC_SMLTN_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(dsAssetDepcSmltnRqstTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(dsAssetDepcSmltnRqstTMsg.depcSmltnRqstDtTmTs, dateFormat.format(date));

        // START 2016/04/15 T.Tsuchida [QC#7050,MOD]
        // for (int i = 0; i < map.size(); i++) {
        // ZYPEZDItemValueSetter.setValue(dsAssetDepcSmltnRqstTMsg.dsAssetDepcSmltnRqstPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_DEPC_SMLTN_RQST_SQ));
        // ZYPEZDItemValueSetter.setValue(dsAssetDepcSmltnRqstTMsg.prntDsAssetMstrPk, (BigDecimal) map.get(COL_PRNT_DS_ASSET_MSTR_PK));
        // ZYPEZDItemValueSetter.setValue(dsAssetDepcSmltnRqstTMsg.depcSmltnRqstStsCd, DEPC_SMLTN_RQST_STS_CD_NE);
        // ZYPEZDItemValueSetter.setValue(dsAssetDepcSmltnRqstTMsg.dsAssetMstrPk, (BigDecimal) map.get(COL_DS_ASSET_MSTR_PK));
        // ZYPEZDItemValueSetter.setValue(dsAssetDepcSmltnRqstTMsg.curValAmt, (BigDecimal) map.get(CUR_VAL_AMT));
        // ZYPEZDItemValueSetter.setValue(dsAssetDepcSmltnRqstTMsg.depcCnt, (BigDecimal) map.get(DEPC_CNT));
        // ZYPEZDItemValueSetter.setValue(dsAssetDepcSmltnRqstTMsg.lastDepcYrMth, (String) map.get(LAST_DEPC_YR_MTH));
        //
        // S21FastTBLAccessor.insert(dsAssetDepcSmltnRqstTMsg);
        // if (!RTNCD_NORMAL.equals(dsAssetDepcSmltnRqstTMsg.getReturnCode())) {
        // bizMsg.setMessageInfo(ZZZM9012E, new String[] {dsAssetDepcSmltnRqstTMsg.getReturnCode() });
        // return;
        // }
        // }
        for (int i = 0; i < list.size(); i++) {

            Map<String, Object> map = list.get(i);
            ZYPEZDItemValueSetter.setValue(dsAssetDepcSmltnRqstTMsg.dsAssetDepcSmltnRqstPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_DEPC_SMLTN_RQST_SQ));
            ZYPEZDItemValueSetter.setValue(dsAssetDepcSmltnRqstTMsg.prntDsAssetMstrPk, (BigDecimal) map.get(COL_PRNT_DS_ASSET_MSTR_PK));
            ZYPEZDItemValueSetter.setValue(dsAssetDepcSmltnRqstTMsg.depcSmltnRqstStsCd, DEPC_SMLTN_RQST_STS_CD_NE);
            ZYPEZDItemValueSetter.setValue(dsAssetDepcSmltnRqstTMsg.dsAssetMstrPk, (BigDecimal) map.get(COL_DS_ASSET_MSTR_PK));
            ZYPEZDItemValueSetter.setValue(dsAssetDepcSmltnRqstTMsg.curValAmt, (BigDecimal) map.get(COL_CUR_VAL_AMT));
            ZYPEZDItemValueSetter.setValue(dsAssetDepcSmltnRqstTMsg.depcCnt, (BigDecimal) map.get(COL_DEPC_CNT));
            ZYPEZDItemValueSetter.setValue(dsAssetDepcSmltnRqstTMsg.lastDepcYrMth, (String) map.get(COL_LAST_DEPC_YR_MTH));

            S21FastTBLAccessor.insert(dsAssetDepcSmltnRqstTMsg);
            if (!RTNCD_NORMAL.equals(dsAssetDepcSmltnRqstTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(ZZZM9012E, new String[] {dsAssetDepcSmltnRqstTMsg.getReturnCode() });
                return;
            }
        }
        // END 2016/04/15 T.Tsuchida [QC#7050,MOD]

    }

    /**
     * Save_Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLEL0060Scrn00_Save_Search(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        if (NLEL0060CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm.setErrorInfo(1, NLZM2273E, new String[] {MSG_SAVE, SRCH_OPT_NM });
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        String userId = getContextUserInfo().getUserId();
        NSZC033001PMsg pMsg = new NSZC033001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm) && NLEL0060CommonLogic.isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm);
        } else {
            NLEL0060CommonLogic.setSelectSaveSearchName(bizMsg, pMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, userId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.assetTpCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.assetStsCd_H1);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.assetTagNum_H1);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.serNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.mdseDescShortTxt_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.cpoOrdNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.rtnWhCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.rtlWhNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.dsAssetDescTxt_H1);
        // START 2016/04/18 J.Kim [QC#6581,MOD]
        // ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15,
        // bizMsg.sellToCustCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.shipToCustAcctCd_H1);
        // END 2016/04/18 J.Kim [QC#6581,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.dsAcctNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, bizMsg.custIssPoNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, bizMsg.vndCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, bizMsg.vndNm_H1);

        if (ZYPCommonFunc.hasValue(bizMsg.dsAssetMstrPk_H1)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.dsAssetMstrPk_H1.getValue().toString());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.depcStartDt_H1)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.depcStartDt_H1.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.depcStartDt_H2)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.depcStartDt_H2.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.svcConfigMstrPk_H1)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.svcConfigMstrPk_H1.getValue().toString());
        }
        // START 2018/04/13 J.Kim [QC#22807,MOD]
        //// START 2016/06/30 J.Kim [QC#10864,ADD]
        //if (ZYPCommonFunc.hasValue(bizMsg.depcCoaAcctCd_H1)) {
        //    ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, bizMsg.depcCoaAcctCd_H1);
        //}
        //// END 2016/06/30 J.Kim [QC#10864,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.depcCoaAcctCd_F)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, bizMsg.depcCoaAcctCd_F);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.depcCoaAcctCd_T)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, bizMsg.depcCoaAcctCd_T);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.expCoaBrCd_F)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, bizMsg.expCoaBrCd_F);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.expCoaBrCd_T)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, bizMsg.expCoaBrCd_T);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.expCoaCcCd_F)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, bizMsg.expCoaCcCd_F);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.expCoaCcCd_T)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_25, bizMsg.expCoaCcCd_T);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.expCoaExtnCd_F)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_26, bizMsg.expCoaExtnCd_F);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.expCoaExtnCd_T)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_27, bizMsg.expCoaExtnCd_T);
        }
        // END 2018/04/13 J.Kim [QC#22807,MOD]

        if (NLEL0060CommonLogic.callNszc0330(bizMsg, pMsg)) {
            NLEL0060CommonLogic.createSaveOptPulldownList(bizMsg, userId, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk, pMsg.srchOptPk);
            bizMsg.srchOptNm.clear();
            bizMsg.setMessageInfo(NZZM0002I);
        }
    }

    private boolean checkChangeValue(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        boolean changeFlg = false;
        boolean changed = true;

        String tab = bizMsg.xxDplyTab.getValue();
        if (TAB_DTL.equals(tab)) {
            NLEL0060_ASMsg aSMsg;
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                aSMsg = glblMsg.A.no(i);

                // START 2016/04/20 J.Kim [QC#7113,ADD]
                changeFlg = false;
                // END 2016/04/20 J.Kim [QC#7113,ADD]

                if (ASSET_STS.PENDING.equals(aSMsg.assetStsCd_A1.getValue())) {
                    if (checkPendingChangeValue(aSMsg)) {
                        changeFlg = true;
                    }
                }

                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxBtnFlg_AA.getValue()) && ZYPConstant.FLG_ON_Y.equals(aSMsg.xxChkBox_A1.getValue())) {
                    if (!aSMsg.assetTagNum_A1.getValue().equals(aSMsg.assetTagNum_AB.getValue()) //
                            || !aSMsg.dsAssetDescTxt_A1.getValue().equals(aSMsg.dsAssetDescTxt_AB.getValue()) //
                            || !aSMsg.depcMthNum_A1.getValue().equals(aSMsg.depcMthNum_AB.getValue()) //
                            || !aSMsg.serNum_A1.getValue().equals(aSMsg.serNum_AB.getValue()) //
                            || !aSMsg.shipToCustAcctCd_A1.getValue().equals(aSMsg.shipToCustAcctCd_AB.getValue()) //
                            || !aSMsg.dsAcctNm_A1.getValue().equals(aSMsg.dsAcctNm_AB.getValue()) //
                            || !aSMsg.dtlCmntTxt_A1.getValue().equals(aSMsg.dtlCmntTxt_AB.getValue()) //
                            || !NLEL0060CommonLogic.compareToEqual(aSMsg.totAssetQty_A1.getValue(), aSMsg.totAssetQty_AB.getValue())
                            // START 2016/06/20 J.Kim [QC#10294,ADD]
                            || !NLEL0060CommonLogic.compareToEqual(aSMsg.dsAssetGrpInitBookAmt_A1.getValue(), aSMsg.dsAssetGrpInitBookAmt_AB.getValue())) {
                        // END 2016/06/20 J.Kim [QC#10294,ADD]
                        changeFlg = true;
                        ZYPEZDItemValueSetter.setValue(aSMsg.xxPgFlg_A1, ZYPConstant.FLG_ON_Y);
                    }
                    // START 2016/04/21 J.Kim [QC#7113,ADD]
                    if (!changeFlg) {
                        aSMsg.xxChkBox_A1.setErrorInfo(1, NLEM0032E);
                        changed = false;
                    }
                    // END 2016/04/21 J.Kim [QC#7113,ADD]
                }
            }
        } else if (TAB_ASG.equals(tab)) {
            List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.B, CHECK_BOX_B, ZYPConstant.CHKBOX_ON_Y);
            if (checkList == null || checkList.isEmpty()) {
                bizMsg.setMessageInfo(NLAM0023E, null);
                return true;
            }

            NLEL0060_BSMsg bSMsg;
            for (int i : checkList) {
                bSMsg = glblMsg.B.no(i);

                // START 2016/04/20 J.Kim [QC#7113,ADD]
                changeFlg = false;
                // END 2016/04/20 J.Kim [QC#7113,ADD]
                if (!bSMsg.ctyAddr_B1.getValue().equals(bSMsg.ctyAddr_BB.getValue()) //
                        || !bSMsg.stCd_B1.getValue().equals(bSMsg.stCd_BB.getValue()) //
                        || !bSMsg.postCd_B1.getValue().equals(bSMsg.postCd_BB.getValue()) //
                        || !bSMsg.asgDtlCmntTxt_B1.getValue().equals(bSMsg.asgDtlCmntTxt_BB.getValue()) //
                        // START 2016/11/01 J.Kim [QC#16345,DEL]
                        // || !bSMsg.xxScrItem50Txt_B1.getValue().equals(bSMsg.xxScrItem50Txt_BB.getValue()) //
                        // END 2016/11/01 J.Kim [QC#16345,DEL]
                         // START 2018/08/28 S.Ohki [QC#28000,MOD]
//                        || !bSMsg.slsRepTocCd_B.getValue().equals(bSMsg.slsRepTocCd_BB.getValue()) //
                        || !bSMsg.psnNum_B.getValue().equals(bSMsg.psnNum_BB.getValue()) //
                         // END 2018/08/28 S.Ohki [QC#28000,MOD]
                        || !bSMsg.xxScrItem50Txt_B2.getValue().equals(bSMsg.xxScrItem50Txt_BC.getValue()) //
                        || !bSMsg.xxScrItem10Txt_B1.getValue().equals(bSMsg.xxScrItem10Txt_BB.getValue())) {
                    changeFlg = true;
                }

                // START 2016/04/21 J.Kim [QC#7113,ADD]
                if (!changeFlg) {
                    bSMsg.xxChkBox_B1.setErrorInfo(1, NLEM0032E);
                    changed = false;
                }
                // END 2016/04/21 J.Kim [QC#7113,ADD]
            }

        } else if (TAB_FIN.equals(tab)) {
            if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxBtnFlg_CA.getValue()) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.xxBtnFlg_CM.getValue())) {
                return true;
            }

            List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.C, CHECK_BOX_C, ZYPConstant.CHKBOX_ON_Y);
            if (checkList == null || checkList.isEmpty()) {
                bizMsg.setMessageInfo(NLAM0023E, null);
                return true;
            }
            NLEL0060_CSMsg cSMsg;
            for (int i : checkList) {
                cSMsg = glblMsg.C.no(i);

                // START 2016/04/14 J.Kim [QC#6587,ADD]
                if (!ZYPCommonFunc.hasValue(cSMsg.curValAmt_C1)) {
                    cSMsg.curValAmt_C1.setValue(BigDecimal.ZERO);
                }

                if (!ZYPCommonFunc.hasValue(cSMsg.curValAmt_C2)) {
                    cSMsg.curValAmt_C2.setValue(BigDecimal.ZERO);
                }
                // END 2016/04/14 J.Kim [QC#6587,ADD]

                // START 2016/04/20 J.Kim [QC#7113,ADD]
                changeFlg = false;
                // END 2016/04/20 J.Kim [QC#7113,ADD]
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxBtnFlg_CA.getValue())) {
                    if (!cSMsg.finDtlCmntTxt_C1.getValue().equals(cSMsg.finDtlCmntTxt_CB.getValue()) //
                            // START 2016/04/15 J.Kim [QC#7043,MOD]
                            // ||
                            // !cSMsg.curValAmt_C1.getValue().equals(cSMsg.curValAmt_CC.getValue())
                            // ||
                            // !cSMsg.curValAmt_C2.getValue().equals(cSMsg.curValAmt_CB.getValue()))
                            // {
                            // START 2016/05/11 K.Kojima [QC#7113,MOD]
                            // ||
                            // (cSMsg.curValAmt_C1.getValue().compareTo(cSMsg.curValAmt_CC.getValue())
                            // != 0)
                            // ||
                            // (cSMsg.curValAmt_C2.getValue().compareTo(cSMsg.curValAmt_CB.getValue())
                            // != 0)) {
                            || !NLEL0060CommonLogic.compareToEqual(cSMsg.curValAmt_C1.getValue(), cSMsg.curValAmt_CC.getValue()) || !NLEL0060CommonLogic.compareToEqual(cSMsg.curValAmt_C2.getValue(), cSMsg.curValAmt_CB.getValue())) {
                        // END 2016/05/11 K.Kojima [QC#7113,MOD]
                        // END 2016/04/15 J.Kim [QC#7043,MOD]
                        changeFlg = true;
                    }
                } else if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxBtnFlg_CM.getValue())) {
                    if (!cSMsg.finDtlCmntTxt_C1.getValue().equals(cSMsg.finDtlCmntTxt_CB.getValue())
                    // START 2016/04/20 J.Kim [QC#7112,MOD]
                            // ||
                            // !cSMsg.prntDsAssetMstrPk_C1.getValue().equals(cSMsg.prntDsAssetMstrPk_CB.getValue()))
                            // {
                            // START 2016/05/11 K.Kojima [QC#7113,MOD]
                            // ||
                            // cSMsg.prntDsAssetMstrPk_C1.getValue().compareTo(cSMsg.prntDsAssetMstrPk_CB.getValue())
                            // != 0) {
                            || !NLEL0060CommonLogic.compareToEqual(cSMsg.prntDsAssetMstrPk_C1.getValue(), cSMsg.prntDsAssetMstrPk_CB.getValue())) {
                        // END 2016/05/11 K.Kojima [QC#7113,MOD]
                        // END 2016/04/20 J.Kim [QC#7112,MOD]
                        changeFlg = true;
                    }
                }
                // START 2016/04/21 J.Kim [QC#7113,ADD]
                if (!changeFlg) {
                    cSMsg.xxChkBox_C1.setErrorInfo(1, NLEM0032E);
                    changed = false;
                }
                // END 2016/04/21 J.Kim [QC#7113,ADD]
            }
        }

        if (!changed) {
            bizMsg.setMessageInfo(NLEM0032E);
        }
        return changed;
    }

    private boolean checkPendingChangeValue(NLEL0060_ASMsg aSMsg) {

        boolean changeFlg = false;

        if (ASSET_STS.PENDING.equals(aSMsg.assetStsCd_A1.getValue())) {
            if (!aSMsg.depcMthNum_A1.getValue().equals(aSMsg.depcMthNum_AB.getValue()) //
                    || !aSMsg.dsAssetDescTxt_A1.getValue().equals(aSMsg.dsAssetDescTxt_AB.getValue()) //
                    || !aSMsg.assetTagNum_A1.getValue().equals(aSMsg.assetTagNum_AB.getValue()) //
                    // START 2018/06/20 J.Kim [QC#24844,ADD]
                    //|| !aSMsg.assetLeaseNum_A1.getValue().equals(aSMsg.assetLeaseNum_AB.getValue()) //
                    //|| !aSMsg.leaseStartDt_A1.getValue().equals(aSMsg.leaseStartDt_AB.getValue()) //
                    //|| !aSMsg.leaseEndDt_A1.getValue().equals(aSMsg.leaseEndDt_AB.getValue()) //
                    || !aSMsg.dsContrNum_A1.getValue().equals(aSMsg.dsContrNum_AB.getValue()) //
                    || !aSMsg.contrEffFromDt_A1.getValue().equals(aSMsg.contrEffFromDt_AB.getValue()) //
                    || !aSMsg.contrEffThruDt_A1.getValue().equals(aSMsg.contrEffThruDt_AB.getValue()) //
                    // END 2018/06/20 J.Kim [QC#24844,ADD]
                    || !aSMsg.depcStartDt_A1.getValue().equals(aSMsg.depcStartDt_AB.getValue()) || !aSMsg.serNum_A1.getValue().equals(aSMsg.serNum_AB.getValue()) //
                    || !aSMsg.coaMdseTpCd_A1.getValue().equals(aSMsg.coaMdseTpCd_AB.getValue()) //
                    || !aSMsg.shipToCustAcctCd_A1.getValue().equals(aSMsg.shipToCustAcctCd_AB.getValue()) //
                    || !aSMsg.dsAcctNm_A1.getValue().equals(aSMsg.dsAcctNm_AB.getValue()) //
                    || !aSMsg.cpoOrdNum_A1.getValue().equals(aSMsg.cpoOrdNum_AB.getValue()) //
                    || !aSMsg.dplyLineNum_A1.getValue().equals(aSMsg.dplyLineNum_AB.getValue()) //
                    // START 2018/06/20 J.Kim [QC#24844,ADD]
                    //|| !aSMsg.poOrdNum_A1.getValue().equals(aSMsg.poOrdNum_AB.getValue()) //
                    || !aSMsg.custIssPoNum_A1.getValue().equals(aSMsg.custIssPoNum_AB.getValue()) //
                    // END 2018/06/20 J.Kim [QC#24844,ADD]
                    || !aSMsg.invNum_A1.getValue().equals(aSMsg.invNum_AB.getValue()) //
                    || !aSMsg.vndCd_A1.getValue().equals(aSMsg.vndCd_AB.getValue()) //
                    || !aSMsg.dtlCmntTxt_A1.getValue().equals(aSMsg.dtlCmntTxt_AB.getValue()) //
                    || !NLEL0060CommonLogic.compareToEqual(aSMsg.dsAssetGrpInitBookAmt_A1.getValue(), aSMsg.dsAssetGrpInitBookAmt_AB.getValue())
                    // START 2016/04/20 J.Kim [QC#7112,MOD]
                    // ||
                    // !aSMsg.totAssetQty_A1.getValue().equals(aSMsg.totAssetQty_AB.getValue())
                    // //
                    // ||
                    // !aSMsg.curValAmt_A1.getValue().equals(aSMsg.curValAmt_AB.getValue()))
                    // {
                    // START 2016/05/11 K.Kojima [QC#7113,MOD]
                    // ||
                    // (aSMsg.totAssetQty_A1.getValue().compareTo(aSMsg.totAssetQty_AB.getValue())
                    // != 0)
                    // ||
                    // (aSMsg.curValAmt_A1.getValue().compareTo(aSMsg.curValAmt_AB.getValue())
                    // != 0)) {
                    || !NLEL0060CommonLogic.compareToEqual(aSMsg.totAssetQty_A1.getValue(), aSMsg.totAssetQty_AB.getValue())) {
                // ||
                // !NLEL0060CommonLogic.compareToEqual(aSMsg.curValAmt_A1.getValue(),
                // aSMsg.curValAmt_AB.getValue())) {
                // END 2016/05/11 K.Kojima [QC#7113,MOD]
                // END 2016/04/20 J.Kim [QC#7112,MOD]
                changeFlg = true;
                ZYPEZDItemValueSetter.setValue(aSMsg.xxPgFlg_A1, ZYPConstant.FLG_ON_Y);
            }
        }

        return changeFlg;
    }

    // private void copyEZDMsg(NLEL0060CMsg bizMsg, NLEL0060SMsg
    // glblMsg, int index) {
    // bizMsg.xxPageShowFromNum.setValue(index);
    // NLEL0060CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);
    // }
    // END 2016/04/21 J.Kim [QC#7113,ADD]

    // START 2016/04/15 J.Kim [QC#7043,ADD]
    private boolean checkCurrentValueAmount(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {
        // START 2016/05/11 K.Kojima [QC#7113,ADD]
        boolean resultFlag = true;
        // END 2016/05/11 K.Kojima [QC#7113,ADD]

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.C, CHECK_BOX_C, ZYPConstant.CHKBOX_ON_Y);
        if (checkList == null || checkList.isEmpty()) {
            bizMsg.setMessageInfo(NLAM0023E, null);
            return true;
        }
        NLEL0060_CSMsg cSMsg;
        // START 2016/05/11 K.Kojima [QC#7113,ADD]
        int index = -1;
        // END 2016/05/11 K.Kojima [QC#7113,ADD]
        for (int i : checkList) {
            cSMsg = glblMsg.C.no(i);

            if (!ZYPCommonFunc.hasValue(cSMsg.curValAmt_C1) || cSMsg.curValAmt_C1.getValue().compareTo(cSMsg.curValAmt_CC.getValue()) > 0) {
                // START 2016/05/11 K.Kojima [QC#7113,MOD]
                // bizMsg.C.no(i).curValAmt_C1.setErrorInfo(1,
                // NLEM0038E);
                // START 2016/07/20 Y.Tsuchimoto [QC#10561,MOD]
                BigDecimal messageAmt = cSMsg.curValAmt_CC.getValue();
                if (!ZYPCommonFunc.hasValue(messageAmt)) {
                    messageAmt = BigDecimal.ZERO;
                }
                cSMsg.curValAmt_C1.setErrorInfo(1, NLEM0038E, new String[] {messageAmt.toString() });
                // END 2016/07/20 Y.Tsuchimoto [QC#10561,MOD]
                // END 2016/05/11 K.Kojima [QC#7113,MOD]
                // START 2016/05/11 K.Kojima [QC#7113,MOD]
                // return false;
                resultFlag = false;
                // END 2016/05/11 K.Kojima [QC#7113,MOD]
            }
            // START 2016/05/11 K.Kojima [QC#7113,ADD]
            if (index < 0 && resultFlag == false) {
                index = i;
            }
            // END 2016/05/11 K.Kojima [QC#7113,ADD]
        }

        // START 2016/05/11 K.Kojima [QC#7113,MOD]
        // return true;
        return resultFlag;
        // END 2016/05/11 K.Kojima [QC#7113,MOD]
    }

    // END 2016/04/15 J.Kim [QC#7043,ADD]

    // START 2016/04/26 J.Kim [QC#7065,ADD]
    // START 2016/05/11 K.Kojima [QC#7065,MOD]
    // private boolean isNumberCheck(NLEL0060CMsg bizMsg, NLEL0060SMsg
    // glblMsg) {
    private boolean isNumberCheck(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg, boolean importFlag) {
        // END 2016/05/11 K.Kojima [QC#7065,MOD]
        boolean isCheckFlg = true;
        String glblCmpyCd = getGlobalCompanyCode();

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.A, CHECK_BOX_A, ZYPConstant.FLG_ON_Y);

        if (checkList == null || checkList.isEmpty()) {
            bizMsg.setMessageInfo(NLAM0023E, null);
            return true;
        }

        NLEL0060_ASMsg cSMsg;
        // START 2016/05/11 K.Kojima [QC#7113,ADD]
        int index = -1;
        // END 2016/05/11 K.Kojima [QC#7113,ADD]
        for (int i : checkList) {
            cSMsg = glblMsg.A.no(i);

            isCheckFlg = true;
            if (!ZYPCommonFunc.hasValue(cSMsg.depcMthNum_A1.getValue())) {
                // START 2016/05/11 K.Kojima [QC#7113,MOD]
                // bizMsg.A.no(i).depcMthNum_A1.setErrorInfo(1,
                // ZZM9000E, new String[] {DEPC_MTH_NUM });
                cSMsg.depcMthNum_A1.setErrorInfo(1, ZZM9000E, new String[] {MSG_DEPC_MTH_NUM });
                // END 2016/05/11 K.Kojima [QC#7113,MOD]
                isCheckFlg = false;
            } else {
                if (!ZYPCommonFunc.isNumberCheck(cSMsg.depcMthNum_A1.getValue())) {
                    // START 2016/05/11 K.Kojima [QC#7113,MOD]
                    // bizMsg.A.no(i).depcMthNum_A1.setErrorInfo(1,
                    // ZZM9004E, new String[] {DEPC_MTH_NUM
                    // });
                    cSMsg.depcMthNum_A1.setErrorInfo(1, ZZM9004E, new String[] {MSG_DEPC_MTH_NUM });
                    // END 2016/05/11 K.Kojima [QC#7113,MOD]
                    isCheckFlg = false;
                } else {
                    BigDecimal depcMthMun = new BigDecimal(cSMsg.depcMthNum_A1.getValue());
                    if (depcMthMun.compareTo(BigDecimal.ZERO) < 0) {
                        // START 2016/05/11 K.Kojima [QC#7113,MOD]
                        // bizMsg.A.no(i).depcMthNum_A1.setErrorInfo(1,
                        // ZZM9004E, new String[]
                        // {DEPC_MTH_NUM });
                        cSMsg.depcMthNum_A1.setErrorInfo(1, ZZM9004E, new String[] {MSG_DEPC_MTH_NUM });
                        // END 2016/05/11 K.Kojima [QC#7113,MOD]
                        isCheckFlg = false;
                    }

                    // START 2016/05/11 K.Kojima [QC#7065,ADD]
                    if (importFlag == true) {
                        if (depcMthMun.compareTo(BigDecimal.ZERO) == 0) {
                            cSMsg.depcMthNum_A1.setErrorInfo(1, ZZM9004E, new String[] {MSG_DEPC_MTH_NUM });
                            isCheckFlg = false;
                        }
                        if (!ASSET_STS.PENDING.equals(cSMsg.assetStsCd_A1.getValue())) {
                            cSMsg.xxChkBox_A1.setErrorInfo(1, NLZM2283E);
                            isCheckFlg = false;
                        }
                    }
                    // END 2016/05/11 K.Kojima [QC#7065,ADD]
                }
            }

            if (!ZYPCommonFunc.hasValue(cSMsg.totAssetQty_A1.getValue())) {
                // START 2016/05/11 K.Kojima [QC#7113,MOD]
                // bizMsg.A.no(i).totAssetQty_A1.setErrorInfo(1,
                // ZZM9000E, new String[] {"Units" });
                cSMsg.totAssetQty_A1.setErrorInfo(1, ZZM9000E, new String[] {"Units" });
                // END 2016/05/11 K.Kojima [QC#7113,MOD]
                isCheckFlg = false;
            } else {
                if (cSMsg.totAssetQty_A1.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    // START 2016/05/11 K.Kojima [QC#7113,MOD]
                    // bizMsg.A.no(i).totAssetQty_A1.setErrorInfo(1,
                    // ZZM9004E, new String[] {"Units" });
                    cSMsg.totAssetQty_A1.setErrorInfo(1, ZZM9004E, new String[] {"Units" });
                    // END 2016/05/11 K.Kojima [QC#7113,MOD]
                    isCheckFlg = false;
                }
            }

            // START 2016/06/20 J.Kim [QC#10294,ADD]
            if (!ZYPCommonFunc.hasValue(cSMsg.dsAssetGrpInitBookAmt_A1)) {
                cSMsg.dsAssetGrpInitBookAmt_A1.setErrorInfo(1, ZZM9000E, new String[] {"Asset Value" });
                isCheckFlg = false;
            } else {
                if (cSMsg.dsAssetGrpInitBookAmt_A1.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    cSMsg.dsAssetGrpInitBookAmt_A1.setErrorInfo(1, ZZM9033E, new String[] {"Asset Value" });
                    isCheckFlg = false;
                }
            }
            // END 2016/06/20 J.Kim [QC#10294,ADD]

            // check Life in Months
            if (ASSET_STS.ACTIVATE.equals(cSMsg.assetStsCd_A1.getValue()) && ZYPCommonFunc.hasValue(cSMsg.depcMthNum_A1) && !cSMsg.depcMthNum_A1.getValue().equals(cSMsg.depcMthNum_AB.getValue())) {
                if (Integer.parseInt(cSMsg.depcMthNum_A1.getValue()) <= cSMsg.depcCnt_A1.getValueInt()) {
                    cSMsg.depcMthNum_A1.setErrorInfo(1, NLEM0044E, new String[] {MSG_DEPC_MTH_NUM, MSG_DEPC_CNT });
                    isCheckFlg = false;
                }
            }

            // check MDSE Type
            if (ZYPCommonFunc.hasValue(cSMsg.coaMdseTpCd_A1)) {
                String mdseTpCtxTpCd = ZYPCodeDataUtil.getVarCharConstValue(MDSE_TP_CTX_TP_CD, glblCmpyCd);
                if (!ZYPCommonFunc.hasValue(mdseTpCtxTpCd)) {
                    cSMsg.coaMdseTpCd_A1.setErrorInfo(1, ZZZM9006E, new String[] {MDSE_TP_CTX_TP_CD });
                    isCheckFlg = false;
                }
                if (!NLEL0060Query.getInstance().existCoaMdseTpCd(cSMsg.coaMdseTpCd_A1.getValue(), mdseTpCtxTpCd)) {
                    cSMsg.coaMdseTpCd_A1.setErrorInfo(1, ZZZM9006E, new String[] {MSG_MDSE_TP_CD });
                    isCheckFlg = false;
                }
            }

            // check Vender Code
            PRNT_VNDTMsg vndTMsg = NLEL0060CommonLogic.getVnd(glblCmpyCd, cSMsg.vndCd_A1.getValue());
            if (ZYPCommonFunc.hasValue(cSMsg.vndCd_A1)) {
                if (vndTMsg == null) {
                    cSMsg.vndCd_A1.setErrorInfo(1, ZZZM9006E, new String[] {MSG_VND_CD });
                    isCheckFlg = false;
                } else {
                    ZYPEZDItemValueSetter.setValue(cSMsg.prntVndNm_A1, vndTMsg.prntVndNm);
                }
            }

            // check Customer Name
            if (ZYPCommonFunc.hasValue(cSMsg.dsAcctNm_A1)) {
                String dsAcctNum = NLEL0060Query.getInstance().getDsAcctNum(cSMsg.dsAcctNm_A1.getValue());
                if (ZYPCommonFunc.hasValue(dsAcctNum)) {
                    ZYPEZDItemValueSetter.setValue(cSMsg.shipToCustAcctCd_A1, dsAcctNum);
                } else {
                    cSMsg.dsAcctNm_A1.setErrorInfo(1, ZZZM9006E, new String[] {MSG_DS_ACCT_NM });
                    isCheckFlg = false;
                }
            }

            if (!NLEL0060CommonLogic.compareToEqual(cSMsg.dsAssetGrpInitBookAmt_A1.getValue(), cSMsg.dsAssetGrpInitBookAmt_AB.getValue())) {
                if (!ZYPCommonFunc.hasValue(cSMsg.amtChngRsnTpCd_A1)) {
                    cSMsg.amtChngRsnTpCd_A1.setErrorInfo(1, ZZM9000E, new String[] {"Value Change Reason" });
                    isCheckFlg = false;
                }
            }

            // START 2016/05/11 K.Kojima [QC#7113,ADD]
            if (index < 0 && isCheckFlg == false) {
                index = i;
            }
            // END 2016/05/11 K.Kojima [QC#7113,ADD]
        }

        if (index >= 0) {
            isCheckFlg = false;
        }

        return isCheckFlg;
    }

    // END 2016/04/26 J.Kim [QC#7065,ADD]

    private boolean doAdjust(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        NLZC305001PMsg pMsg = new NLZC305001PMsg();

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.C, CHECK_BOX_C, ZYPConstant.CHKBOX_ON_Y);
        if (checkList == null || checkList.isEmpty()) {
            bizMsg.setMessageInfo(NLAM0023E, null);
            return true;
        }
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());

        int i = 0;
        NLEL0060_CSMsg cSMsg;
        for (int j : checkList) {
            pMsg.updDtlList.no(i).clear();
            cSMsg = glblMsg.C.no(j);

            // get DS_ASSET_MSTR data
            DS_ASSET_MSTRTMsg dsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
            ZYPEZDItemValueSetter.setValue(dsAssetMstrTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(dsAssetMstrTMsg.dsAssetMstrPk, cSMsg.dsAssetMstrPk_C1.getValue());
            dsAssetMstrTMsg = (DS_ASSET_MSTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsAssetMstrTMsg);
            if (dsAssetMstrTMsg == null) {
                bizMsg.setMessageInfo(ZZZM9006E, new String[] {TBL_DS_ASSET_MSTR });
                return false;
            }
            // check updated by another user
            if (!ZYPDateUtil.isSameTimeStamp(cSMsg.ezUpTime_C1.getValue(), cSMsg.ezUpTimeZone_C1.getValue(), dsAssetMstrTMsg.ezUpTime.getValue(), dsAssetMstrTMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NLBM0009E);
                return false;
            }

            if (ASSET_STS.PENDING.equals(cSMsg.assetStsCd_C1.getValue())) {

                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).xxProcMd, NLZC305001Constant.PROC_MODE_UPD_BEFORE_ACTIVATE);
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).prntDsAssetMstrPk, cSMsg.dsAssetMstrPk_C1.getValue());
                if (!cSMsg.finDtlCmntTxt_C1.getValue().equals(cSMsg.finDtlCmntTxt_CB.getValue())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).finDtlCmntTxt, cSMsg.finDtlCmntTxt_C1.getValue());
                }
            } else if (ASSET_STS.ACTIVATE.equals(cSMsg.assetStsCd_C1.getValue())) {

                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).xxProcMd, NLZC305001Constant.PROC_MODE_UPD);
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).prntDsAssetMstrPk, cSMsg.dsAssetMstrPk_C1.getValue());
                // set Depreciation Month Number
                if (!cSMsg.curValAmt_C2.getValue().equals(cSMsg.curValAmt_CB.getValue())) {
                    String depcMthNum = cSMsg.curValAmt_C2.getValue().add(dsAssetMstrTMsg.depcCnt.getValue()).toString();
                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).depcMthNum, depcMthNum);
                }
                // set Current Value Amount(NBV)
                if (!cSMsg.curValAmt_C1.getValue().equals(cSMsg.curValAmt_CC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).curValAmt, cSMsg.curValAmt_C1.getValue());
                }
                if (!cSMsg.finDtlCmntTxt_C1.getValue().equals(cSMsg.finDtlCmntTxt_CB.getValue())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).finDtlCmntTxt, cSMsg.finDtlCmntTxt_C1.getValue());
                }
            }
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).procModeCd, PROC_MODE.UPDATE);
            pMsg.updDtlList.setValidCount(1);
            // call asset update Api (ProccessMode : 4)
            if (!NLEL0060CommonLogic.callAssetUpdateApi(bizMsg, pMsg)) {
                return false;
            }
        }
        return true;
    }

    private boolean doMerge(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        NLZC305001PMsg pMsg = new NLZC305001PMsg();

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.C, CHECK_BOX_C, ZYPConstant.CHKBOX_ON_Y);
        if (checkList == null || checkList.isEmpty()) {
            bizMsg.setMessageInfo(NLAM0023E, null);
            return true;
        }
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());

        int i = 0;
        NLEL0060_CSMsg cSMsg;
        for (int j : checkList) {
            pMsg.updDtlList.no(i).clear();
            cSMsg = glblMsg.C.no(j);
            // check updated by another user
            if (!NLEL0060CommonLogic.checkUpdatedByAnotherUser(bizMsg, getGlobalCompanyCode(), cSMsg.ezUpTime_C1.getValue(), cSMsg.ezUpTimeZone_C1.getValue(), cSMsg.dsAssetMstrPk_C1.getValue())) {
                return false;
            }
            // compare asset number to parent asset number
            if (!cSMsg.dsAssetMstrPk_C1.getValue().equals(cSMsg.prntDsAssetMstrPk_C1.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).dsAssetMstrPk, cSMsg.dsAssetMstrPk_C1.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).xxProcMd, NLZC305001Constant.PROC_MODE_MERGE);
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).prntDsAssetMstrPk, cSMsg.prntDsAssetMstrPk_C1.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).finDtlCmntTxt, cSMsg.finDtlCmntTxt_C1.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).procModeCd, PROC_MODE.MERGE);

                pMsg.updDtlList.setValidCount(1);
                // call asset update Api (ProccessMode : I)
                if (!NLEL0060CommonLogic.callAssetUpdateApi(bizMsg, pMsg)) {
                    return false;
                }
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).xxProcMd, NLZC305001Constant.PROC_MODE_UPD);
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).prntDsAssetMstrPk, cSMsg.dsAssetMstrPk_C1.getValue());
                if (!cSMsg.finDtlCmntTxt_C1.getValue().equals(cSMsg.finDtlCmntTxt_CB.getValue())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).finDtlCmntTxt, cSMsg.finDtlCmntTxt_C1.getValue());
                }
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).procModeCd, PROC_MODE.MERGE);

                pMsg.updDtlList.setValidCount(1);
                // call asset update Api (ProccessMode : 4)
                if (!NLEL0060CommonLogic.callAssetUpdateApi(bizMsg, pMsg)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkParent(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxBtnFlg_CM.getValue())) {
            return true;
        }

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.C, CHECK_BOX_C, ZYPConstant.CHKBOX_ON_Y);
        if (checkList == null || checkList.isEmpty()) {
            bizMsg.setMessageInfo(NLAM0023E, null);
            return true;
        }

        boolean errFlg = false;
        boolean exist = false;
        boolean existPE = false;
        boolean existAC = false;
        boolean parentAC = false;
        NLEL0060_CSMsg sMsg;
        for (int i : checkList) {
            exist = false;
            existPE = false;
            existAC = false;
            sMsg = glblMsg.C.no(i);

            if (!(ASSET_STS.PENDING.equals(sMsg.assetStsCd_C1.getValue()) || ASSET_STS.ACTIVATE.equals(sMsg.assetStsCd_C1.getValue()))) {
                sMsg.xxChkBox_C1.setErrorInfo(1, NLZM2283E);
                continue;
            }

            if (!ZYPCommonFunc.hasValue(sMsg.prntDsAssetMstrPk_C1)) {
                sMsg.prntDsAssetMstrPk_C1.setErrorInfo(1, ZZM9000E, new String[] {MSG_PRNT_DS_ASSET_MSTR_PK });
                errFlg = true;
                continue;
            }
            // START 2016/10/24 J.Kim [QC#11026,MOD]
            // if
            // (!ZYPConstant.FLG_ON_Y.equals(sMsg.manEntryFlg_C1.getValue()))
            // {
            // sMsg.prntDsAssetMstrPk_C1.setErrorInfo(1, NLEM0043E);
            // errFlg = true;
            // }
            // if
            // (!sMsg.prntDsAssetMstrPk_C1.getValue().equals(sMsg.dsAssetMstrPk_C1.getValue())
            // &&
            // !NLEL0060Query.getInstance().isSameBookType(sMsg.prntDsAssetMstrPk_C1.getValue(),
            // sMsg.procModeCd_C1.getValue(),
            // sMsg.assetTpDescTxt_C1.getValue())) {
            if (!sMsg.prntDsAssetMstrPk_C1.getValue().equals(sMsg.dsAssetMstrPk_C1.getValue()) && !NLEL0060Query.getInstance().isSameBookType(sMsg.prntDsAssetMstrPk_C1.getValue(), sMsg.assetTpDescTxt_C1.getValue())) {
                // END 2016/10/24 J.Kim [QC#11026,MOD]
                sMsg.prntDsAssetMstrPk_C1.setErrorInfo(1, NLEM0046E);
                errFlg = true;
            }

            for (int j : checkList) {
                if (sMsg.prntDsAssetMstrPk_C1.getValue().equals(glblMsg.C.no(j).dsAssetMstrPk_C1.getValue())) {
                    exist = true;
                }
                if (sMsg.prntDsAssetMstrPk_C1.getValue().equals(glblMsg.C.no(j).prntDsAssetMstrPk_C1.getValue()) && ASSET_STS.PENDING.equals(glblMsg.C.no(j).assetStsCd_C1.getValue())) {
                    existPE = true;
                }
                if (sMsg.prntDsAssetMstrPk_C1.getValue().equals(glblMsg.C.no(j).prntDsAssetMstrPk_C1.getValue()) && ASSET_STS.ACTIVATE.equals(glblMsg.C.no(j).assetStsCd_C1.getValue())) {
                    existAC = true;
                }
            }

            if (!exist) {
                sMsg.prntDsAssetMstrPk_C1.setErrorInfo(1, NLEM0035E);
                errFlg = true;
            }
            if (!existPE) {
                sMsg.prntDsAssetMstrPk_C1.setErrorInfo(1, NLEM0041E);
                errFlg = true;
            } else {
                if (existAC) {
                    parentAC = false;
                    for (int j : checkList) {
                        if (sMsg.prntDsAssetMstrPk_C1.getValue().equals(glblMsg.C.no(j).dsAssetMstrPk_C1.getValue()) && ASSET_STS.ACTIVATE.equals(glblMsg.C.no(j).assetStsCd_C1.getValue())) {
                            parentAC = true;
                        }
                    }
                    if (!parentAC) {
                        sMsg.prntDsAssetMstrPk_C1.setErrorInfo(1, NLEM0042E);
                        errFlg = true;
                    }
                }
            }

            // START 2018/08/21 J.Kim [QC#27253,MOD]
            for (int idx : checkList) {
                if (PROC_MODE.AP_INVOICE.equals(sMsg.procModeCd_C1.getValue()) || PROC_MODE.PROCURED.equals(sMsg.procModeCd_C1.getValue()) || PROC_MODE.LEASED.equals(sMsg.procModeCd_C1.getValue())) {
                    continue;
                }
                if (!ZYPCommonFunc.hasValue(sMsg.svcConfigMstrPk_C1) || !ZYPCommonFunc.hasValue(glblMsg.C.no(idx).svcConfigMstrPk_C1.getValue())) {
                    sMsg.prntDsAssetMstrPk_C1.setErrorInfo(1, NLEM0007E, new String[] {"Service Config Master PK" });
                    errFlg = true;
                    continue;
                }
                if (sMsg.prntDsAssetMstrPk_C1.getValue().compareTo(glblMsg.C.no(idx).prntDsAssetMstrPk_C1.getValue()) == 0) {
                    if (sMsg.svcConfigMstrPk_C1.getValue().compareTo(glblMsg.C.no(idx).svcConfigMstrPk_C1.getValue()) != 0) {
                        sMsg.prntDsAssetMstrPk_C1.setErrorInfo(1, NLEM0007E, new String[] {"Service Config Master PK" });
                        errFlg = true;
                        continue;
                    }
                }
            }
            // END 2018/08/21 J.Kim [QC#27253,MOD]
        }
        if (errFlg) {
            return false;
        }

        return true;
    }

    private boolean checkRtrnWhCd(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        boolean errFlg = true;

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.B, CHECK_BOX_B, ZYPConstant.CHKBOX_ON_Y);
        if (checkList == null || checkList.isEmpty()) {
            bizMsg.setMessageInfo(NLAM0023E, null);
            return true;
        }

        NLEL0060_BSMsg bMsg;
        for (int i : checkList) {
            bMsg = glblMsg.B.no(i);
            if (ZYPCommonFunc.hasValue(bMsg.xxScrItem10Txt_B1) && !ON_SITE.equals(bMsg.xxScrItem10Txt_B1.getValue())) {
                if (!NLEL0060Query.getInstance().existRtrnWhCd(bMsg.xxScrItem10Txt_B1.getValue())) {
                    glblMsg.B.no(i).xxScrItem10Txt_B1.setErrorInfo(1, ZZZM9006E, new String[] {MSG_LOC });
                    errFlg = false;
                }
            }
        }
        return errFlg;
    }

    // START 2016/10/17 J.Kim [QC#13453,ADD]
    private boolean checkAccount(NLEL0060SMsg glblMsg) {

        boolean errFlg = true;

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.B, CHECK_BOX_B, ZYPConstant.CHKBOX_ON_Y);
        if (checkList == null || checkList.isEmpty()) {
            return true;
        }

        NLEL0060_BSMsg bMsg;
        for (int i : checkList) {
            bMsg = glblMsg.B.no(i);
            // START 2016/11/01 J.Kim [QC#16345,ADD]
            String assetTpCd = bMsg.assetTpCd_B1.getValue();
            // START 2018/02/06 J.Kim [QC#23890,MOD]
            // BigDecimal svcMachMstrPk = bMsg.svcMachMstrPk_B1.getValue();
            // if (ASSET_TP.RENTAL_ASSET.equals(assetTpCd) || (ASSET_TP.EMSD_ASSET.equals(assetTpCd) && ZYPCommonFunc.hasValue(svcMachMstrPk))) {
            String procModeCd = bMsg.procModeCd_B1.getValue();
            // START 2018/05/17 J.Kim [QC#25879,ADD]
            if (!NLEL0060Constant.PROC_MODE_51.equals(procModeCd)) {
                // START 2018/08/28 S.Ohki [QC#28000,MOD]
//                if (ZYPCommonFunc.hasValue(bMsg.slsRepTocCd_B)) {
//                    Map<String, String> saleRepInfo = NLEL0060Query.getInstance().getSalesRep(getGlobalCompanyCode(), bMsg.slsRepTocCd_B.getValue());
//                    if (saleRepInfo == null) {
//                        bMsg.slsRepTocCd_B.setErrorInfo(1, ZZZM9006E, new String[] {"Sales Rep" });
//                        return false;
//                    }
//                }
                if (ZYPCommonFunc.hasValue(bMsg.psnNum_B)) {
                    Map<String, String> saleRepInfo = NLEL0060Query.getInstance().getSalesRep(getGlobalCompanyCode(), bMsg.psnNum_B.getValue());
                    if (saleRepInfo == null) {
                        bMsg.psnNum_B.setErrorInfo(1, ZZZM9006E, new String[] {"Sales Rep" });
                        return false;
                    }
                }
                // END 2018/08/28 S.Ohki [QC#28000,MOD]
            }
            // END 2018/05/17 J.Kim [QC#25879,ADD]
            if (ASSET_TP.RENTAL_ASSET.equals(assetTpCd) || (ASSET_TP.EMSD_ASSET.equals(assetTpCd) && !NLEL0060Constant.PROC_MODE_51.equals(procModeCd))) {
             // END 2018/02/06 J.Kim [QC#23890,MOD]
                continue;
            }
            // END 2016/11/01 J.Kim [QC#16345,ADD]
            // START 2016/11/01 J.Kim [QC#16345,DEL]
            // String assetAccount = bMsg.xxScrItem50Txt_B1.getValue();
            // END 2016/11/01 J.Kim [QC#16345,DEL]
            String expenseAccount = bMsg.xxScrItem50Txt_B2.getValue();
            // START 2016/11/01 J.Kim [QC#16345,DEL]
            // String[] assetStr = assetAccount.split(Pattern.quote(STR_COMMA));
            // END 2016/11/01 J.Kim [QC#16345,DEL]
            String[] expenseStr = expenseAccount.split(Pattern.quote(STR_COMMA));
            // START 2016/11/01 J.Kim [QC#16345,DEL]
            //if (assetStr.length != COA_LEN) {
            //    bMsg.xxScrItem50Txt_B1.setErrorInfo(1, NLEM0007E, new String[] {"Asset Account" });
            //    errFlg = false;
            //}
            // END 2016/11/01 J.Kim [QC#16345,DEL]
            if (expenseStr.length != COA_LEN) {
                bMsg.xxScrItem50Txt_B2.setErrorInfo(1, NLEM0007E, new String[] {"Expense Account" });
                errFlg = false;
            }
            // START 2016/11/01 J.Kim [QC#16345,DEL]
            //if (!checkItem(assetStr)) {
            //    bMsg.xxScrItem50Txt_B1.setErrorInfo(1, NLEM0007E, new String[] {"Asset Account" });
            //
            //    errFlg = false;
            //}
            // END 2016/11/01 J.Kim [QC#16345,DEL]
            if (!checkItem(expenseStr)) {
                bMsg.xxScrItem50Txt_B2.setErrorInfo(1, NLEM0007E, new String[] {"Expense Account" });
                errFlg = false;
            }
        }
        return errFlg;
    }

    private boolean checkItem(String[] asStr) {

        for (String str : asStr) {
            if (!ZYPCommonFunc.hasValue(str)) {
                return false;
            }
        }
        return true;
    }

    // END 2016/10/17 J.Kim [QC#13453,ADD]

    // START 2017/05/15 M.Naito
    /**
     * Check CPO
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     * @return Map<String, Object>
     */
    private Map<String, Object> checkCpoLineNum(NLEL0060CMsg bizMsg, NLEL0060_ASMsg sMsg) {

        boolean changeFlg = false;
        Map<String, Object> cpoLineMap = new HashMap<String, Object>();

        /* 06/27/2017 CSAI Y.Imazu Add QC#19591 START */
        if (!sMsg.cpoOrdNum_A1.getValue().equals(sMsg.cpoOrdNum_AB.getValue())) {

            changeFlg = true;
            /* 06/27/2017 CSAI Y.Imazu Add QC#19591 END */

        } else if (!sMsg.dplyLineNum_A1.getValue().equals(sMsg.dplyLineNum_AB.getValue())) {

            changeFlg = true;
        }

        cpoLineMap.put("changeFlg", changeFlg);

        /* 06/27/2017 CSAI Y.Imazu Add QC#19591 START */
        if (!changeFlg) {

            return cpoLineMap;
        }
        /* 06/27/2017 CSAI Y.Imazu Add QC#19591 END */

        if (!ZYPCommonFunc.hasValue(sMsg.dplyLineNum_A1.getValue())) {

            sMsg.dplyLineNum_A1.setErrorInfo(1, ZZM9000E, new String[] {MSG_CPO_ORD_LINE_NUM });
            return cpoLineMap;
        }

        String[] cpoDtlLineNumList = sMsg.dplyLineNum_A1.getValue().split(Pattern.quote(STR_COMMA));

        CPO_DTLTMsg setCpoDtlTMsg = new CPO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(setCpoDtlTMsg.dsOrdPosnNum, cpoDtlLineNumList[0]);

        if (cpoDtlLineNumList.length > 1) {

            ZYPEZDItemValueSetter.setValue(setCpoDtlTMsg.dsCpoLineNum, new BigDecimal(cpoDtlLineNumList[1]));
        }

        if (cpoDtlLineNumList.length > 2) {

            ZYPEZDItemValueSetter.setValue(setCpoDtlTMsg.dsCpoLineSubNum, new BigDecimal(cpoDtlLineNumList[2]));
        }

        // Get CPO Line Number
        /* 06/27/2017 CSAI Y.Imazu Del QC#19591 START */
        // if (changeFlg) {
        /* 06/27/2017 CSAI Y.Imazu Del QC#19591 END */

        ZYPEZDItemValueSetter.setValue(setCpoDtlTMsg.glblCmpyCd, getGlobalCompanyCode());

        if (!ZYPCommonFunc.hasValue(sMsg.cpoOrdNum_A1)) {

            sMsg.cpoOrdNum_A1.setErrorInfo(1, ZZM9000E, new String[] {MSG_CPO_ORD_NUM });
            return cpoLineMap;
        }

        ZYPEZDItemValueSetter.setValue(setCpoDtlTMsg.cpoOrdNum, sMsg.cpoOrdNum_A1.getValue());

        List<Map<String, Object>> cpoLineNumMapList = new ArrayList<Map<String, Object>>();
        cpoLineNumMapList = NLEL0060Query.getInstance().getCpoLineNum(getGlobalCompanyCode(), setCpoDtlTMsg);

        if (cpoLineNumMapList == null || cpoLineNumMapList.isEmpty()) {

            sMsg.cpoOrdNum_A1.setErrorInfo(1, NLBM1063E);
            sMsg.dplyLineNum_A1.setErrorInfo(1, NLBM1063E);
            return cpoLineMap;

        } else if (cpoLineNumMapList.size() > 1) {

            sMsg.cpoOrdNum_A1.setErrorInfo(1, NLBM1063E);
            sMsg.dplyLineNum_A1.setErrorInfo(1, NLXM1010E);
            return cpoLineMap;

        } else {

            cpoLineMap.put(CPO_DTL_LINE_NUM, cpoLineNumMapList.get(0).get(CPO_DTL_LINE_NUM));
            cpoLineMap.put(CPO_DTL_LINE_SUB_NUM, cpoLineNumMapList.get(0).get(CPO_DTL_LINE_SUB_NUM));
        }

        /* 06/27/2017 CSAI Y.Imazu Del QC#19591 START */
        // }
        /* 06/27/2017 CSAI Y.Imazu Del QC#19591 END */

        return cpoLineMap;
    }

    /**
     * Import (Detail Tab)
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     * @param checkList List<Integer>
     * @return boolean
     */
    private NLZC305001PMsg setNLZC305001PMsgForDtl(NLEL0060_ASMsg aSMsg, Map<String, Object> cpoLineNum, String procModeCd) {

        int i = 0;

        NLZC305001PMsg pMsg = new NLZC305001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).prntDsAssetMstrPk, aSMsg.dsAssetMstrPk_A1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).xxProcMd, NLZC305001Constant.PROC_MODE_UPD_BEFORE_ACTIVATE);

        if (!aSMsg.invNum_A1.getValue().equals(aSMsg.invNum_AB.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).invNum, aSMsg.invNum_A1.getValue());
        }
        if (!aSMsg.depcMthNum_A1.getValue().equals(aSMsg.depcMthNum_AB.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).depcMthNum, aSMsg.depcMthNum_A1.getValue());
        }
        if (!aSMsg.depcStartDt_A1.getValue().equals(aSMsg.depcStartDt_AB.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).depcStartDt, aSMsg.depcStartDt_A1.getValue());
        }
        if ((aSMsg.dsAssetGrpInitBookAmt_A1.getValue()).compareTo(aSMsg.dsAssetGrpInitBookAmt_AB.getValue()) != 0) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).initBookAmt, aSMsg.dsAssetGrpInitBookAmt_A1.getValue());
        }
        if ((cpoLineNum.get("changeFlg").equals(true)) || (!aSMsg.cpoOrdNum_A1.getValue().equals(aSMsg.cpoOrdNum_AB.getValue()))) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).cpoOrdNum, aSMsg.cpoOrdNum_A1.getValue());
        }
        if (ZYPCommonFunc.hasValue((String) cpoLineNum.get(CPO_DTL_LINE_NUM))) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).cpoDtlLineNum, (String) cpoLineNum.get(CPO_DTL_LINE_NUM));
        }
        if (ZYPCommonFunc.hasValue((String) cpoLineNum.get(CPO_DTL_LINE_SUB_NUM))) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).cpoDtlLineSubNum, (String) cpoLineNum.get(CPO_DTL_LINE_SUB_NUM));
        }
        if (!aSMsg.dsAssetDescTxt_A1.getValue().equals(aSMsg.dsAssetDescTxt_AB.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).assetDescTxt, aSMsg.dsAssetDescTxt_A1.getValue());
        }
        // START 2018/06/20 J.Kim [QC#24844,ADD]
        //if (!aSMsg.assetLeaseNum_A1.getValue().equals(aSMsg.assetLeaseNum_AB.getValue())) {
        //    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).assetLeaseNum, aSMsg.assetLeaseNum_A1.getValue());
        //}
        //if (!aSMsg.leaseStartDt_A1.getValue().equals(aSMsg.leaseStartDt_AB.getValue())) {
        //    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).leaseStartDt, aSMsg.leaseStartDt_A1.getValue());
        //}
        //if (!aSMsg.leaseEndDt_A1.getValue().equals(aSMsg.leaseEndDt_AB.getValue())) {
        //    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).leaseEndDt, aSMsg.leaseEndDt_A1.getValue());
        //}
        if (!aSMsg.dsContrNum_A1.getValue().equals(aSMsg.dsContrNum_AB.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).dsContrNum, aSMsg.dsContrNum_A1.getValue());
        }
        if (!aSMsg.contrEffFromDt_A1.getValue().equals(aSMsg.contrEffFromDt_AB.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).contrEffFromDt, aSMsg.contrEffFromDt_A1.getValue());
        }
        if (!aSMsg.contrEffThruDt_A1.getValue().equals(aSMsg.contrEffThruDt_AB.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).contrEffThruDt, aSMsg.contrEffThruDt_A1.getValue());
        }
        // END 2018/06/20 J.Kim [QC#24844,ADD]
        if (!aSMsg.vndCd_A1.getValue().equals(aSMsg.vndCd_AB.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).vndCd, aSMsg.vndCd_A1.getValue());
        }
        if (!aSMsg.custIssPoNum_A1.getValue().equals(aSMsg.custIssPoNum_AB.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).custIssPoNum, aSMsg.custIssPoNum_A1.getValue());
        }
        if (!aSMsg.totAssetQty_A1.getValue().equals(aSMsg.totAssetQty_AB.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).totAssetQty, aSMsg.totAssetQty_A1.getValue());
        }
        if (!aSMsg.coaMdseTpCd_A1.getValue().equals(aSMsg.coaMdseTpCd_AB.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).coaMdseTpCd, aSMsg.coaMdseTpCd_A1.getValue());
        }
        if (!aSMsg.assetTagNum_A1.getValue().equals(aSMsg.assetTagNum_AB.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).assetTagNum, aSMsg.assetTagNum_A1.getValue());
        }
        // START 2018/07/20 J.Kim [QC#24950,ADD]
        if (!aSMsg.dsAcctNm_A1.getValue().equals(aSMsg.dsAcctNm_AB.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).shipToCustAcctCd, aSMsg.shipToCustAcctCd_A1.getValue());
        }
        if (!aSMsg.serNum_A1.getValue().equals(aSMsg.serNum_AB.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).serNum, aSMsg.serNum_A1.getValue());
        }
        if (!aSMsg.dtlCmntTxt_A1.getValue().equals(aSMsg.dtlCmntTxt_AB.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).dtlCmntTxt, aSMsg.dtlCmntTxt_A1.getValue());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, procModeCd);
        // END 2018/07/20 J.Kim [QC#24950,ADD]
        // START 2018/08/01 J.Kim [QC#26901,ADD]
        if ((aSMsg.dsAssetGrpInitBookAmt_A1.getValue()).compareTo(aSMsg.dsAssetGrpInitBookAmt_AB.getValue()) != 0) {
            if (!aSMsg.amtChngRsnTpCd_A1.getValue().equals(aSMsg.amtChngRsnTpCd_AB.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).amtChngRsnTpCd, aSMsg.amtChngRsnTpCd_A1.getValue());
            }

            AMT_CHNG_RSN_TPTMsg amtChngRsnTpTMsg = NLEL0060CommonLogic.setValueChangeReason(aSMsg.amtChngRsnTpCd_A1.getValue());
            if (amtChngRsnTpTMsg != null) {
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(i).adjCoaAcctCd, amtChngRsnTpTMsg.coaAcctCd);
            }
        }
        // END 2018/08/01 J.Kim [QC#26901,ADD]
        pMsg.updDtlList.setValidCount(1);
        return pMsg;
    }

    /**
     * Import (Detail Tab)
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     * @param checkList List<Integer>
     * @return boolean
     */
    private boolean doImportForDtl(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg, List<Integer> checkList) {

        for (int j : checkList) {

            NLEL0060_ASMsg aSMsg = glblMsg.A.no(j);

            // check updated by another user
            if (!NLEL0060CommonLogic.checkUpdatedByAnotherUser(bizMsg, getGlobalCompanyCode(), aSMsg.ezUpTime_A1.getValue(), aSMsg.ezUpTimeZone_A1.getValue(), aSMsg.dsAssetMstrPk_A1.getValue())) {

                return false;
            }

            // Check Order Line Number and get CPO Line#
            Map<String, Object> cpoLineNum = checkCpoLineNum(bizMsg, aSMsg);

            if (cpoLineNum.get("changeFlg").equals(true)) {

                if ((cpoLineNum.get(CPO_DTL_LINE_NUM) == null && cpoLineNum.get(CPO_DTL_LINE_SUB_NUM) == null)) {

                    bizMsg.setMessageInfo(ZZM9037E);
                    return false;
                }
            }

            // call asset update Api (ProccessMode : C)
            if (!NLEL0060CommonLogic.callAssetUpdateApi(bizMsg, setNLZC305001PMsgForDtl(aSMsg, cpoLineNum, PROC_MODE.IMPORT))) {

                return false;
            }

            // do import
            NLZC305001PMsg pMsg = new NLZC305001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, NLZC305001Constant.PROC_MODE_ASSET_ACTIVATE);
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).prntDsAssetMstrPk, aSMsg.dsAssetMstrPk_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, PROC_MODE.IMPORT);
            pMsg.updDtlList.setValidCount(1);

            // call asset update Api (ProccessMode : B)
            if (!NLEL0060CommonLogic.callAssetUpdateApi(bizMsg, pMsg)) {

                return false;
            }
        }

        return true;
    }

    /**
     * CMN_Submit Event (Detail Tab)
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     * @return boolean
     */
    private boolean doSubmitForDtl(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.A, CHECK_BOX_A, ZYPConstant.CHKBOX_ON_Y);

        if (checkList == null || checkList.isEmpty()) {

            bizMsg.setMessageInfo(NLAM0023E, null);
            return false;
        }

        for (int j : checkList) {

            NLEL0060_ASMsg aSMsg = glblMsg.A.no(j);

            // check updated by another user
            if (!NLEL0060CommonLogic.checkUpdatedByAnotherUser(bizMsg, getGlobalCompanyCode(), aSMsg.ezUpTime_A1.getValue(), aSMsg.ezUpTimeZone_A1.getValue(), aSMsg.dsAssetMstrPk_A1.getValue())) {

                return false;
            }

            if (ASSET_STS.PENDING.equals(aSMsg.assetStsCd_A1.getValue())) {

                // Check Order Line Number and get CPO Line#
                Map<String, Object> cpoLineNum = checkCpoLineNum(bizMsg, aSMsg);

                if (cpoLineNum.get("changeFlg").equals(true)) {

                    if (cpoLineNum.get(CPO_DTL_LINE_NUM) == null && cpoLineNum.get(CPO_DTL_LINE_SUB_NUM) == null) {

                        bizMsg.setMessageInfo(ZZM9037E);
                        return false;
                    }
                }

                // call asset update Api (ProccessMode : C)
                if (!NLEL0060CommonLogic.callAssetUpdateApi(bizMsg, setNLZC305001PMsgForDtl(aSMsg, cpoLineNum, PROC_MODE.UPDATE))) {

                    return false;
                }

            } else if (ASSET_STS.ACTIVATE.equals(aSMsg.assetStsCd_A1.getValue())) {

                // update DS_ASSET_MASTR
                NLZC305001PMsg pMsg = new NLZC305001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, NLZC305001Constant.PROC_MODE_UPD);
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).prntDsAssetMstrPk, aSMsg.dsAssetMstrPk_A1.getValue());

                if (!aSMsg.depcMthNum_A1.getValue().equals(aSMsg.depcMthNum_AB.getValue())) {

                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).depcMthNum, aSMsg.depcMthNum_A1.getValue());
                }

                if ((aSMsg.dsAssetGrpInitBookAmt_A1.getValue()).compareTo(aSMsg.dsAssetGrpInitBookAmt_AB.getValue()) != 0) {

                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).initBookAmt, aSMsg.dsAssetGrpInitBookAmt_A1.getValue());
                }

                if (!aSMsg.dsAssetDescTxt_A1.getValue().equals(aSMsg.dsAssetDescTxt_AB.getValue())) {

                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetDescTxt, aSMsg.dsAssetDescTxt_A1.getValue());
                }

                if (!aSMsg.totAssetQty_A1.getValue().equals(aSMsg.totAssetQty_AB.getValue())) {

                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).totAssetQty, aSMsg.totAssetQty_A1.getValue());
                }

                if (!aSMsg.assetTagNum_A1.getValue().equals(aSMsg.assetTagNum_AB.getValue())) {

                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetTagNum, aSMsg.assetTagNum_A1.getValue());
                }

                // START 2018/07/20 J.Kim [QC#24950,ADD]
                if (!aSMsg.dtlCmntTxt_A1.getValue().equals(aSMsg.dtlCmntTxt_AB.getValue())) {

                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dtlCmntTxt, aSMsg.dtlCmntTxt_A1.getValue());
                }
                if (!aSMsg.dsAcctNm_A1.getValue().equals(aSMsg.dsAcctNm_AB.getValue())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).shipToCustAcctCd, aSMsg.shipToCustAcctCd_A1.getValue());
                }
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, PROC_MODE.UPDATE);
                // END 2018/07/20 J.Kim [QC#24950,ADD]
                // START 2018/08/01 J.Kim [QC#26901,ADD]
                if ((aSMsg.dsAssetGrpInitBookAmt_A1.getValue()).compareTo(aSMsg.dsAssetGrpInitBookAmt_AB.getValue()) != 0) {
                    if (!aSMsg.amtChngRsnTpCd_A1.getValue().equals(aSMsg.amtChngRsnTpCd_AB.getValue())) {

                        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).amtChngRsnTpCd, aSMsg.amtChngRsnTpCd_A1.getValue());
                    }

                    AMT_CHNG_RSN_TPTMsg amtChngRsnTpTMsg = NLEL0060CommonLogic.setValueChangeReason(aSMsg.amtChngRsnTpCd_A1.getValue());
                    if (amtChngRsnTpTMsg != null) {
                        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).adjCoaAcctCd, amtChngRsnTpTMsg.coaAcctCd);
                    }
                }
                // END 2018/08/01 J.Kim [QC#26901,ADD]

                pMsg.updDtlList.setValidCount(1);

                // call asset update Api (ProccessMode : 4)
                if (!NLEL0060CommonLogic.callAssetUpdateApi(bizMsg, pMsg)) {

                    return false;
                }
            }
        }

        return true;
    }

    /**
     * CMN_Submit Event (Assignment Tab)
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     * @return boolean
     */
    private boolean doSubmitForAsg(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.B, CHECK_BOX_B, ZYPConstant.CHKBOX_ON_Y);

        if (checkList == null || checkList.isEmpty()) {

            bizMsg.setMessageInfo(NLAM0023E, null);
            return false;
        }

        NLZC305001PMsg pMsg = new NLZC305001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());

        for (int j : checkList) {

            NLEL0060_BSMsg bSMsg = glblMsg.B.no(j);

            // check updated by another user
            if (!NLEL0060CommonLogic.checkUpdatedByAnotherUser(bizMsg, getGlobalCompanyCode(), bSMsg.ezUpTime_B1.getValue(), bSMsg.ezUpTimeZone_B1.getValue(), bSMsg.prntDsAssetMstrPk_B1.getValue())) {

                return false;
            }

            // set Proccess mode and asset number
            pMsg.updDtlList.no(0).clear();
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).prntDsAssetMstrPk, bSMsg.prntDsAssetMstrPk_B1.getValue());

            if (ASSET_STS.ACTIVATE.equals(bSMsg.assetStsCd_B1.getValue())) {

                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, NLZC305001Constant.PROC_MODE_UPD);

            } else {

                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, NLZC305001Constant.PROC_MODE_UPD_BEFORE_ACTIVATE);
            }

            // START 2016/11/01 J.Kim [QC#16345,ADD]
            // Split Expence Account
            if (ZYPCommonFunc.hasValue(bSMsg.xxScrItem50Txt_B2)) {
                String[] expenseStr = bSMsg.xxScrItem50Txt_B2.getValue().split(Pattern.quote(STR_COMMA));
                if (ZYPCommonFunc.hasValue(bSMsg.expCoaCmpyCd_B1)) {
                    if (!expenseStr[0].equals(bSMsg.expCoaCmpyCd_B1.getValue())) {
                        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaCmpyCd, expenseStr[0]);
                    }
                } else {
                    if (!expenseStr[0].equals(getGlobalCompanyCode())) {
                        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaCmpyCd, expenseStr[0]);
                    }
                }
                if (!expenseStr[1].equals(bSMsg.coaBrCd_B1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaBrCd, expenseStr[1]);
                }
                if (!expenseStr[2].equals(bSMsg.coaCcCd_B1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaCcCd, expenseStr[2]);
                }
                if (!expenseStr[3].equals(bSMsg.depcCoaAcctCd_B2.getValue())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaAcctCd, expenseStr[3]);
                }
                if (!expenseStr[4].equals(bSMsg.coaProdCd_B1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaProdCd, expenseStr[4]);
                }
                if (!expenseStr[5].equals(bSMsg.coaChCd_B1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaChCd, expenseStr[5]);
                }
                if (!expenseStr[6].equals(bSMsg.coaAfflCd_B1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaAfflCd, expenseStr[6]);
                }
                if (!expenseStr[7].equals(bSMsg.coaProjCd_B1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaProjCd, expenseStr[7]);
                }
                if (!expenseStr[8].equals(bSMsg.coaExtnCd_B1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaExtnCd, expenseStr[8]);
                }
                // START 2018/02/26 J.Kim [QC#22792,ADD]
                NLEL0060CommonLogic.checkExpenseStr(expenseStr, bSMsg, pMsg);
                // END 2018/02/26 J.Kim [QC#22792,ADD]
            }
            // END 2016/11/01 J.Kim [QC#16345,ADD]

            if (!bSMsg.xxScrItem10Txt_B1.getValue().equals(bSMsg.xxScrItem10Txt_BB.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).rtrnWhCd, bSMsg.xxScrItem10Txt_B1.getValue());
            }
            if (!bSMsg.ctyAddr_B1.getValue().equals(bSMsg.ctyAddr_BB.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).ctyAddr, bSMsg.ctyAddr_B1.getValue());
            }
            if (!bSMsg.stCd_B1.getValue().equals(bSMsg.stCd_BB.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).stCd, bSMsg.stCd_B1.getValue());
            }
            if (!bSMsg.postCd_B1.getValue().equals(bSMsg.postCd_BB.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).postCd, bSMsg.postCd_B1.getValue());
            }

            // START 2016/11/01 J.Kim [QC#16345,DEL]
            //// Split Asset Account
            //String[] assetStr = bSMsg.xxScrItem50Txt_B1.getValue().split(Pattern.quote(STR_COMMA));
            //if (ZYPCommonFunc.hasValue(bSMsg.assetCoaCmpyCd_B1)) {
            //    if (!assetStr[0].equals(bSMsg.assetCoaCmpyCd_B1.getValue())) {
            //        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetCoaCmpyCd, assetStr[0]);
            //    }
            //} else {
            //    if (!assetStr[0].equals(getGlobalCompanyCode())) {
            //        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetCoaCmpyCd, assetStr[0]);
            //    }
            //}
            //if (!assetStr[1].equals(bSMsg.coaBrCd_B1.getValue())) {
            //    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetCoaBrCd, assetStr[1]);
            //}
            //if (!assetStr[2].equals(bSMsg.coaCcCd_B1.getValue())) {
            //    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetCoaCcCd, assetStr[2]);
            //}
            //if (!assetStr[3].equals(bSMsg.assetCoaAcctCd_B2.getValue())) {
            //    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetCoaAcctCd, assetStr[3]);
            //}
            //if (!assetStr[4].equals(bSMsg.coaProdCd_B1.getValue())) {
            //    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetCoaProdCd, assetStr[4]);
            //}
            //if (!assetStr[5].equals(bSMsg.coaChCd_B1.getValue())) {
            //    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetCoaChCd, assetStr[5]);
            //}
            //if (!assetStr[6].equals(bSMsg.coaAfflCd_B1.getValue())) {
            //    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetCoaAfflCd, assetStr[6]);
            //}
            //if (!assetStr[7].equals(bSMsg.coaProjCd_B1.getValue())) {
            //    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetCoaProjCd, assetStr[7]);
            //}
            //if (!assetStr[8].equals(bSMsg.coaExtnCd_B1.getValue())) {
            //    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetCoaExtnCd, assetStr[8]);
            //}
            // END 2016/11/01 J.Kim [QC#16345,DEL]
            if (!bSMsg.asgDtlCmntTxt_B1.getValue().equals(bSMsg.asgDtlCmntTxt_BB.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).asgDtlCmntTxt, bSMsg.asgDtlCmntTxt_B1.getValue());
            }

            // START 2018/05/17 J.Kim [QC#25879,ADD]
            // START 2018/08/28 S.Ohki [QC#28000,MOD]
//            if (!bSMsg.slsRepTocCd_B.getValue().equals(bSMsg.slsRepTocCd_BB.getValue())) {
//                String slsRepTocCd = bSMsg.slsRepTocCd_B.getValue();
            if (!bSMsg.psnNum_B.getValue().equals(bSMsg.psnNum_BB.getValue())) {
                String slsRepTocCd = bSMsg.psnNum_B.getValue();
            // END 2018/08/28 S.Ohki [QC#28000,MOD]
                String expCoaBrCd = bSMsg.coaBrCd_B.getValue();
                String expCoaCcCd = bSMsg.coaCcCd_B.getValue();
                String expCoaExtnCd = bSMsg.coaExtnCd_B.getValue();
                Map<String, String> saleRepInfo = NLEL0060Query.getInstance().getSalesRep(getGlobalCompanyCode(), bSMsg.psnNum_B.getValue()); // 2018/08/28 S.Ohki [QC#28000,MOD] slsRepTocCd_B -> psnNum_B
                if (saleRepInfo != null) {
                    slsRepTocCd = saleRepInfo.get("TOC_CD");
                    expCoaBrCd = saleRepInfo.get("COA_BR_CD");
                    expCoaCcCd = saleRepInfo.get("COA_CC_CD");
                    expCoaExtnCd = saleRepInfo.get("COA_EXTN_CD");
                }
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).slsRepTocCd, slsRepTocCd);
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaBrCd, expCoaBrCd);
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaCcCd, expCoaCcCd);
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaExtnCd, expCoaExtnCd);
            }
            // END 2018/05/17 J.Kim [QC#25879,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, PROC_MODE.UPDATE);

            pMsg.updDtlList.setValidCount(1);

            // call asset update Api (ProccessMode : 4)
            if (!NLEL0060CommonLogic.callAssetUpdateApi(bizMsg, pMsg)) {

                return false;
            }
        }

        return true;
    }
    // END 2017/05/15 M.Naito
}
