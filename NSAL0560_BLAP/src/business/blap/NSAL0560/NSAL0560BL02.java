/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0560;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.blap.NSAL0300.constant.NSAL0300Constant.ACTV_PENDING_PO_CONTR_STS_NM;
import static business.blap.NSAL0300.constant.NSAL0300Constant.ACTV_RENEWAL_HOLD_CONTR_STS_NM;
import static business.blap.NSAL0300.constant.NSAL0300Constant.PND_ISTL_CONTR_STS_NM;
import static business.blap.NSAL0560.common.NSAL0560CommonLogic.*;
import static business.blap.NSAL0560.constant.NSAL0560Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSAL0560.common.NSAL0560CommonLogic;
import business.db.DS_CONTR_BLLG_SCHDTMsgArray;
import business.db.DS_CONTR_CTRL_STSTMsg;
import business.db.DS_CONTR_DTLTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
/**
 *<pre>
 * Base Pricing Effectivity
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2015/12/07   Hitachi         T.Kanasaka      Update          QC#1472
 * 2015/12/07   Hitachi         T.Kanasaka      Update          QC#1432
 * 2016/01/06   Hitachi         T.Tomita        Update          QC#2578
 * 2016/01/06   Hitachi         K.Yamada        Update          CSA QC#2783
 * 2016/02/08   Hitachi         K.Kishimoto     Update          QC#3884, QC#3891, QC#3898
 * 2016/05/17   Hitachi         T.Tomita        Update          QC#3891
 * 2016/07/28   Hitachi         O.Okuma         Update          QC#12430
 * 2016/08/02   Hitachi         K.Kishimoto     Update          QC#7402
 * 2016/08/30   Hitachi         K.Kishimoto     Update          QC#4010
 * 2016/11/22   Hitachi         T.Tomita        Update          QC#15942
 * 2017/08/21   Hitachi         K.Kitachi       Update          QC#20061
 * 2018/07/17   Hitachi         K.Kishimoto     Update          QC#25959
 * 2018/08/02   CITS            T.Wada          Update          QC#26938
 * 2020/01/15   Hitachi         Y.Takeno        Update          QC#55434
 *</pre>
 */
public class NSAL0560BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0560CMsg cMsg = (NSAL0560CMsg) arg0;
        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0560_INIT".equals(screenAplID)) {
                doProcess_NSAL0560_INIT(cMsg);
            } else if ("NSAL0560Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NSAL0560Scrn00_DeleteRow(cMsg);
            } else if ("NSAL0560Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NSAL0560Scrn00_InsertRow(cMsg);
            } else if ("NSAL0560Scrn00_OnChange_RecalcTermAmount".equals(screenAplID)) {
                doProcess_NSAL0560Scrn00_OnChange_RecalcTermAmount(cMsg);
            } else if ("NSAL0560Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0560_INIT(cMsg);
            // START 2016/07/28 O.Okuma [QC#12430, ADD]
            } else if ("NSAL0560Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0560_INIT(cMsg);
            // END 2016/07/28 O.Okuma [QC#12430, ADD]
            // START 2016/11/22 T.Tomita [QC#15942, ADD]
            } else if ("NSAL0560_NSAL0330".equals(screenAplID)) {
                doProcess_NSAL0560_INIT(cMsg);
            // END 2016/11/22 T.Tomita [QC#15942, ADD]
            }
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NSAL0560_INIT(NSAL0560CMsg cMsg) {

        // START 2015/12/07 T.Kanasaka [QC#1472, MOD]
        initParam(cMsg);
        NSAL0560CommonLogic.createPullDown(cMsg);
        findContrInfo(cMsg);
        // END 2015/12/07 T.Kanasaka [QC#1472, MOD]
        // Mod Start 02/08/2016 <QC#7402>
        // START 2017/08/21 K.Kitachi [QC#20061, MOD]
        boolean isAggMach = NSAL0560Query.getInstance().existsDsContrDtlAggMach(getGlobalCompanyCode(), cMsg.dsContrDtlPk_H1.getValue());
        boolean isAggLine = NSAL0560Query.getInstance().existsDsContrDtlAggLine(getGlobalCompanyCode(), cMsg.dsContrDtlPk_H1.getValue());
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (NSAL0560Query.getInstance().existsDsContrBllgSchd(getGlobalCompanyCode(), cMsg.A.no(i).dsContrPrcEffPk_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxExstFlg_X1, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxExstFlg_X1, ZYPConstant.FLG_OFF_N);
            }
            // START 2018/07/17 [QC#25959, MOD]
            if (isAggMach) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxExstFlg_X2, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxExstFlg_X2, ZYPConstant.FLG_ON_Y);
            }
            if (isAggLine) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxExstFlg_X3, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxExstFlg_X3, ZYPConstant.FLG_OFF_N);
            }
            // END 2018/07/17 [QC#25959, MOD]
        }
        // END 2017/08/21 K.Kitachi [QC#20061, MOD]
        // Mod End  02/08/2016 <QC#7402>
        //Add Start 02/08/2016 <QC#3891>
        cMsg.svcMemoRsnCd_H3.clear();
        cMsg.svcCmntTxt_H1.clear();
        if (cMsg.A.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxSelNum, "0");
        }
        //Add End   02/08/2016 <QC#3891>

        NSAL0560CommonLogic.copyAMsgToBMsg(cMsg);
    }

    private void doProcess_NSAL0560Scrn00_DeleteRow(NSAL0560CMsg cMsg) {

        int selectIndex = cMsg.xxRadioBtn_A.getValueInt();
        // mod start 2016/05/17 CSA Defect#3891
        if (!hasValue(cMsg.xxRadioBtn_A)) {
            cMsg.setMessageInfo(NSAM0034E);
            return;
        }
        // mod end 2016/05/17 CSA Defect#3891
        if (hasValue(cMsg.A.no(selectIndex).dsContrPrcEffPk_A1)) {
            if (isErrorForValidationCheck(cMsg, cMsg.A.no(selectIndex))) {
                return;
            }
        }

        if (hasValue(cMsg.A.no(selectIndex).dsContrPrcEffPk_A1)) {
            setValue(cMsg.A.no(selectIndex).dsContrDtlStsCd_A1, DS_CONTR_CTRL_STS.CANCELLED);
            setValue(cMsg.A.no(selectIndex).dsContrDtlStsNm_A1, dsContrCtrlStsNm(cMsg, DS_CONTR_CTRL_STS.CANCELLED));
            return;
        }

        // clear
        for (int cnt = selectIndex + 1; cnt < cMsg.A.getValidCount(); cnt++) {
            EZDMsg.copy(cMsg.A.no(cnt), null, cMsg.A.no(cnt - 1), null);
        }
        cMsg.A.setValidCount(cMsg.A.getValidCount() - 1);
    }

    private void doProcess_NSAL0560Scrn00_InsertRow(NSAL0560CMsg cMsg) {

        int index = cMsg.A.getValidCount();

        if (cMsg.A.length() < index + 1) {
            cMsg.setMessageInfo(NSAM0112E);
            return;
        }

        // START 2020/01/15 [QC#55434, ADD]
        if (checkDetailRows(cMsg)) {
            return;
        }
        // END   2020/01/15 [QC#55434, ADD]

        NSAL0560_ACMsg acMsg = cMsg.A.no(index);
        acMsg.clear();

        NSAL0560_ACMsg lastAcMsg = null;
        if (index != 0) {
            lastAcMsg = cMsg.A.no(index - 1);
        }

        setLastAddRowData(acMsg, cMsg, index, lastAcMsg);
        cMsg.A.setValidCount(index + 1);
    }

    private void setLastAddRowData(NSAL0560_ACMsg acMsg, NSAL0560CMsg cMsg, int index, NSAL0560_ACMsg lastAcMsg) {
        if (index == 0) {
            ZYPEZDItemValueSetter.setValue(acMsg.dsContrPrcEffSqNum_A1, BigDecimal.ONE);
        } else {
            ZYPEZDItemValueSetter.setValue(acMsg.dsContrPrcEffSqNum_A1, NSAL0560CommonLogic.getNextSeqNum(lastAcMsg));
        }
        if (index == 0) {
            ZYPEZDItemValueSetter.setValue(acMsg.contrPrcEffFromDt_A1, cMsg.contrEffFromDt_H1);
        } else {
            String bllgSchdFromDt = NSAL0560Query.getInstance().getMinBllgSchdFromDt(getGlobalCompanyCode(), acMsg.dsContrPrcEffPk_A1.getValue());
            if (ZYPCommonFunc.hasValue(bllgSchdFromDt)) {
                ZYPEZDItemValueSetter.setValue(acMsg.contrPrcEffFromDt_A1, bllgSchdFromDt);
            } else {
                if (lastAcMsg.contrPrcEffThruDt_A1.getValue().equals(cMsg.contrEffThruDt_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(acMsg.contrPrcEffFromDt_A1, cMsg.contrEffThruDt_H1);
                // START 2020/01/15 [QC#55434, MOD]
                // } else {
                } else if (ZYPCommonFunc.hasValue(lastAcMsg.contrPrcEffThruDt_A1)) {
                // END   2020/01/15 [QC#55434, MOD]
                    ZYPEZDItemValueSetter.setValue(acMsg.contrPrcEffFromDt_A1, ZYPDateUtil.addDays(lastAcMsg.contrPrcEffThruDt_A1.getValue(), 1));
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(acMsg.contrPrcEffThruDt_A1, cMsg.contrEffThruDt_H1);

        DS_CONTR_DTLTMsg tmsg = NSAL0560Query.getInstance().getDsContrDtl(getGlobalCompanyCode(), cMsg.dsContrDtlPk_H1.getValue());
        if (NSAL0560Query.getInstance().existsDsContrDtlAggMach(getGlobalCompanyCode(), cMsg.dsContrDtlPk_H1.getValue())) {
            acMsg.bllgCycleCd_A3.clear();
        } else {
            if (index == 0) {
                ZYPEZDItemValueSetter.setValue(acMsg.bllgCycleCd_A3, tmsg.baseBllgCycleCd);
            } else {
                ZYPEZDItemValueSetter.setValue(acMsg.bllgCycleCd_A3, lastAcMsg.bllgCycleCd_A3);
            }
        }
        if (NSAL0560Query.getInstance().existsDsContrDtlAggLine(getGlobalCompanyCode(), cMsg.dsContrDtlPk_H1.getValue())) {
            acMsg.basePrcDealAmt_A1.clear();
        } else {
            if (index == 0) {
                ZYPEZDItemValueSetter.setValue(acMsg.basePrcDealAmt_A1, tmsg.basePrcDealAmt);
            } else {
                ZYPEZDItemValueSetter.setValue(acMsg.basePrcDealAmt_A1, lastAcMsg.basePrcDealAmt_A1);
            }
        }
        // START 2015/12/07 T.Kanasaka [QC#1472, MOD]
        // Mod Start 02/08/2016 <QC#7402>
        ZYPEZDItemValueSetter.setValue(acMsg.basePrcTermDealAmtRate_A1, getTermAmt(cMsg, acMsg, getGlobalCompanyCode(), cMsg.dsContrDtlPk_H1.getValue()));
        // Mod End   02/08/2016 <QC#7402>
        // END 2015/12/07 T.Kanasaka [QC#1472, MOD]
        Map<String, Object> dsContrCtrlSts = NSAL0560Query.getInstance().getDsContrCtrlSts(getGlobalCompanyCode(), cMsg.dsContrDtlPk_H1.getValue());
        if (dsContrCtrlSts != null && !dsContrCtrlSts.isEmpty()) {
            ZYPEZDItemValueSetter.setValue(acMsg.dsContrDtlStsCd_A1, (String) dsContrCtrlSts.get("DS_CONTR_CTRL_STS_CD"));
            ZYPEZDItemValueSetter.setValue(acMsg.dsContrDtlStsNm_A1, (String) dsContrCtrlSts.get("DS_CONTR_CTRL_STS_NM"));
            // add start 2016/01/06 CSA Defect#2783
            ZYPEZDItemValueSetter.setValue(acMsg.dsContrPrcEffStsCd_A2, (String) dsContrCtrlSts.get("DS_CONTR_DTL_STS_CD"));
            ZYPEZDItemValueSetter.setValue(acMsg.qltyAsrnHldFlg_A2, (String) dsContrCtrlSts.get("QLTY_ASRN_HLD_FLG"));
            ZYPEZDItemValueSetter.setValue(acMsg.mtrHldFlg_A2, (String) dsContrCtrlSts.get("MTR_HLD_FLG"));
            ZYPEZDItemValueSetter.setValue(acMsg.contrHldFlg_A2, (String) dsContrCtrlSts.get("CONTR_HLD_FLG"));
            ZYPEZDItemValueSetter.setValue(acMsg.bllgHldFlg_A2, (String) dsContrCtrlSts.get("BLLG_HLD_FLG"));
            // add end 2016/01/06 CSA Defect#2783
        }
        acMsg.cratDt_A1.clear();
        // del start 2016/05/17 CSA Defect#3891
//        acMsg.svcMemoRsnNm_A1.clear();
        // del end 2016/05/17 CSA Defect#3891
    }

    private void doProcess_NSAL0560Scrn00_OnChange_RecalcTermAmount(NSAL0560CMsg cMsg) {
        // START 2015/12/07 T.Kanasaka [QC#1472, ADD]
        int targetRow = cMsg.rowSqNum_A.getValueInt();
        // Mod Start 02/08/2016 <QC#7402>
        setValue(cMsg.A.no(targetRow).basePrcTermDealAmtRate_A1, getTermAmt(cMsg, cMsg.A.no(targetRow), getGlobalCompanyCode(), cMsg.dsContrDtlPk_H1.getValue()));
        // Mod End   02/08/2016 <QC#7402>
        // END 2015/12/07 T.Kanasaka [QC#1472, ADD]
    }

    private void initParam(NSAL0560CMsg cMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());
    }

    private void findContrInfo(NSAL0560CMsg cMsg) {

        // Header
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("dsContrDtlPk", cMsg.dsContrDtlPk_H1.getValue());
        S21SsmEZDResult ssmResult = NSAL0560Query.getInstance().getContrDtl(queryMap, cMsg);

        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAM0013E);
            return;
        }

        // Detail
        queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("dsContrDtlPk", cMsg.dsContrDtlPk_H1.getValue());
        queryMap.put("baseChrgFlg", ZYPConstant.FLG_ON_Y);
        // Add Start 08/30/2016 <QC#4010>
        queryMap.put("cancelSts", DS_CONTR_DTL_STS.CANCELLED);
        // Add End   08/30/2016 <QC#4010>
        queryMap.put("rowNum", SEARCH_LIMIT_CNT + 1);

        // Add Start 2018/07/27 QC#26938
        String pndIstlContrStsNm = ZYPCodeDataUtil.getVarCharConstValue(PND_ISTL_CONTR_STS_NM, getGlobalCompanyCode());
        queryMap.put("entered", DS_CONTR_CTRL_STS.ENTERED);
        queryMap.put("pendingInstallation", pndIstlContrStsNm);
        queryMap.put("w4i", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        // Add End   2018/07/27 QC#26938

        ssmResult = NSAL0560Query.getInstance().getContrPrcEff(queryMap, cMsg.A);

        if (ssmResult.getQueryResultCount() > SEARCH_LIMIT_CNT) {
            cMsg.setMessageInfo(NZZM0001W);
        }

        // START 2016/01/06 T.Tomita [QC#2578, DEL]
//        // START 2015/12/07 T.Kanasaka [QC#1472, ADD]
//        setTermAmt(cMsg);
//        // END 2015/12/07 T.Kanasaka [QC#1472, ADD]
        // END START 2016/01/06 T.Tomita [QC#2578, DEL]
        cMsg.xxRadioBtn_A.setValue(0);

        return;
    }

    private boolean isErrorForValidationCheck(NSAL0560CMsg cMsg, NSAL0560_ACMsg aCMsg) {
        if (existInvoicedBllgSchd(cMsg, aCMsg)
                || checkDsContrCtrlSts(cMsg, aCMsg)) {
            return true;
        }
        return false;
    }

    private boolean existInvoicedBllgSchd(NSAL0560CMsg cMsg, NSAL0560_ACMsg aCMsg) {
        DS_CONTR_BLLG_SCHDTMsgArray outTMsgArray = NSAL0560Query.getInvoicedBllgSchd(cMsg, aCMsg);
        if (outTMsgArray.getValidCount() > 0) {
            cMsg.setMessageInfo(NSAM0368E);
            return true;
        }
        return false;

    }

    private boolean checkDsContrCtrlSts(NSAL0560CMsg cMsg, NSAL0560_ACMsg aCMsg) {
        String dsContrCtrlSts = aCMsg.dsContrDtlStsCd_A1.getValue();
        if (!hasValue(dsContrCtrlSts)
                || (!DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlSts)
                        && !DS_CONTR_CTRL_STS.ENTERED.equals(dsContrCtrlSts)
                        && !DS_CONTR_CTRL_STS.QA_HOLD.equals(dsContrCtrlSts))) {
            cMsg.setMessageInfo(NSAM0369E);
            return true;
        }
        return false;
    }

    private String dsContrCtrlStsNm(NSAL0560CMsg cMsg, String dsContrStrlStsCd) {
        DS_CONTR_CTRL_STSTMsg inTMsg = new DS_CONTR_CTRL_STSTMsg();
        inTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        inTMsg.dsContrCtrlStsCd.setValue(dsContrStrlStsCd);
        DS_CONTR_CTRL_STSTMsg outTMsg = (DS_CONTR_CTRL_STSTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg.dsContrCtrlStsNm.getValue();
    }

    // START 2016/01/06 T.Tomita [QC#2578, DEL]
//    // START 2015/12/07 T.Kanasaka [QC#1472, ADD]
//    private void setTermAmt(NSAL0560CMsg cMsg) {
//        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
//            setValue(cMsg.A.no(i).basePrcTermDealAmtRate_A1, getTermAmt(cMsg.A.no(i), getGlobalCompanyCode(), cMsg.dsContrDtlPk_H1.getValue()));
//        }
//    }
//    // END 2015/12/07 T.Kanasaka [QC#1472, ADD]
    // END 2016/01/06 T.Tomita [QC#2578, DEL]

    // START 2020/01/15 [QC#55434, ADD]
    private boolean checkDetailRows(NSAL0560CMsg cMsg) {
        boolean retFlg = false;

        // mandatory check
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (!DS_CONTR_CTRL_STS.CANCELLED.equals(cMsg.A.no(i).dsContrDtlStsCd_A1.getValue())) {
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).contrPrcEffFromDt_A1)) {
                    cMsg.A.no(i).contrPrcEffFromDt_A1.setErrorInfo(1, ZZZM9025E, new String[] {"Start Date" });
                    retFlg = true;
                }
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).contrPrcEffThruDt_A1)) {
                    cMsg.A.no(i).contrPrcEffThruDt_A1.setErrorInfo(1, ZZZM9025E, new String[] {"End Date" });
                    retFlg = true;
                }
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).bllgCycleCd_A3)) {
                    cMsg.A.no(i).bllgCycleCd_A3.setErrorInfo(1, ZZZM9025E, new String[] {"Frequency" });
                    retFlg = true;
                }
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).basePrcDealAmt_A1)) {
                    cMsg.A.no(i).basePrcDealAmt_A1.setErrorInfo(1, ZZZM9025E, new String[] {"Price/Period" });
                    retFlg = true;
                }
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).basePrcTermDealAmtRate_A1)) {
                    cMsg.A.no(i).basePrcTermDealAmtRate_A1.setErrorInfo(1, ZZZM9025E, new String[] {"Term Amount" });
                    retFlg = true;
                }
            }
        }
        return retFlg;
    }
    // END   2020/01/15 [QC#55434, ADD]
}
