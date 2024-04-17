/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0570;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import static business.blap.NSAL0300.constant.NSAL0300Constant.PND_ISTL_CONTR_STS_NM;
import static business.blap.NSAL0570.constant.NSAL0570Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSAL0570.common.NSAL0570CommonLogic;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.DS_CONTR_BLLG_SCHDTMsgArray;
import business.db.DS_CONTR_CTRL_STSTMsg;
import business.db.DS_CONTR_DTLTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
/**
 *<pre>
 * Overage Pricing Effectivity
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2015/12/04   Hitachi         K.Yamada        Update          CSA QC#1518
 * 2015/12/11   Hitachi         A.Kohinata      Update          QC1820,QC1664
 * 2016/01/06   Hitachi         T.Tomita        Update          QC2783
 * 2016/02/16   Hitachi         K.Kishimoto     Update          QC#3844,QC#3845,QC#3846
 * 2016/04/11   Hitachi         K.Kishimoto     Update          QC#6728
 * 2016/05/20   Hitachi         T.Tomita        Update          QC#4923
 * 2016/07/28   Hitachi         O.Okuma         Update          QC#12430
 * 2016/08/02   Hitachi         K.Kishimoto     Update          QC#7402
 * 2016/08/30   Hitachi         K.Kishimoto     Update          QC#4010
  *2018/08/02   CITS            T.Wada          Update          QC#26938
 * 2020/01/15   Hitachi         Y.Takeno        Update          QC#55434
 * 2020/03/12   Hitachi         K.Kitachi       Update          QC#55662
*</pre>
 */
public class NSAL0570BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0570CMsg cMsg = (NSAL0570CMsg) arg0;
        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0570_INIT".equals(screenAplID)) {
                doProcess_NSAL0570_INIT(cMsg);
            } else if ("NSAL0570Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NSAL0570Scrn00_DeleteRow(cMsg);
            } else if ("NSAL0570Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NSAL0570Scrn00_InsertRow(cMsg);
            } else if ("NSAL0570Scrn00_DeletePrcAllowRow".equals(screenAplID)) {
                doProcess_NSAL0570Scrn00_DeletePrcAllowRow(cMsg);
            } else if ("NSAL0570Scrn00_InsertPrcAllowRow".equals(screenAplID)) {
                doProcess_NSAL0570Scrn00_InsertPrcAllowRow(cMsg);
            } else if ("NSAL0570Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0570_INIT(cMsg);
            // START 2016/07/28 O.Okuma [QC#12430, ADD]
            } else if ("NSAL0570Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0570_INIT(cMsg);
            // END 2016/07/28 O.Okuma [QC#12430, ADD]
            }
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NSAL0570_INIT(NSAL0570CMsg cMsg) {

        // START 2015/12/11 [QC1820, MOD]
        NSAL0570CommonLogic.createPullDown(cMsg, getGlobalCompanyCode());
        // END 2015/12/11 [QC1820, MOD]

        initParam(cMsg);
        findContrInfo(cMsg);
        // Mod Start 02/08/2016 <QC#7402>
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (NSAL0570Query.getInstance().existsDsContrBllgSchd(getGlobalCompanyCode(), cMsg.A.no(i).dsContrPrcEffPk_A1.getValue())) {
                setValue(cMsg.A.no(i).xxExstFlg_X1, FLG_ON_Y);
            } else {
                setValue(cMsg.A.no(i).xxExstFlg_X1, FLG_OFF_N);
            }
            if (NSAL0570Query.getInstance().existsDsContrDtlAggMach(getGlobalCompanyCode(), cMsg.A.no(i).dsContrPrcEffPk_A1.getValue())) {
                setValue(cMsg.A.no(i).xxExstFlg_X2, FLG_ON_Y);
            } else {
                setValue(cMsg.A.no(i).xxExstFlg_X2, FLG_OFF_N);
            }
            if (NSAL0570Query.getInstance().existsDsContrDtlAggLine(getGlobalCompanyCode(), cMsg.A.no(i).dsContrPrcEffPk_A1.getValue())) {
                setValue(cMsg.A.no(i).xxExstFlg_X3, FLG_ON_Y);
            } else {
                setValue(cMsg.A.no(i).xxExstFlg_X3, FLG_OFF_N);
            }
        // Mod End   02/08/2016 <QC#7402>
        }
        //Add Start 02/16/2016 <QC#3846>
        cMsg.svcMemoRsnCd_H3.clear();
        cMsg.svcCmntTxt_H1.clear();
        if (cMsg.A.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxRadioBtn_A, BigDecimal.ZERO);
        }
        //Add End   02/16/2016 <QC#3846>

        // del start 2015/12/04 CSA Defect#1518
        //NSAL0570CommonLogic.copyAMsgToBMsg(cMsg);
        // del end 2015/12/04 CSA Defect#1518
    }

    private void doProcess_NSAL0570Scrn00_DeleteRow(NSAL0570CMsg cMsg) {

        // mod start 2015/12/04 CSA Defect#1518
        if (!hasValue(cMsg.xxRadioBtn_A)) {
            cMsg.setMessageInfo(NSAM0034E);
            return;
        }
        int selectIndexFrom = cMsg.xxRadioBtn_A.getValueInt();
        // mod end 2015/12/04 CSA Defect#1518

        // START 2020/01/15 [QC#55434, ADD]
        if (checkDetailRows(cMsg)) {
            return;
        }
        // END   2020/01/15 [QC#55434, ADD]

        if (hasValue(cMsg.A.no(selectIndexFrom).dsContrPrcEffPk_A1)) {
            if (isErrorForValidationCheck(cMsg, cMsg.A.no(selectIndexFrom))) {
                return;
            }
        }

        //get Index Thru
        int selectIndexThru = getSelectIndexThru(cMsg, selectIndexFrom);

        if (hasValue(cMsg.A.no(selectIndexFrom).dsContrPrcEffPk_A1)) {
            String dsContrCtrlStsNmCancel = dsContrCtrlStsNm(cMsg, DS_CONTR_CTRL_STS.CANCELLED);
            for (int i = selectIndexFrom; i < selectIndexThru; i++) {
                setValue(cMsg.A.no(i).dsContrDtlStsCd_A1, DS_CONTR_CTRL_STS.CANCELLED);
                setValue(cMsg.A.no(i).dsContrDtlStsNm_A1, dsContrCtrlStsNmCancel);
            }
            return;
        }

        // clear
        for (int cnt = selectIndexThru; cnt < cMsg.A.getValidCount(); cnt++) {
            EZDMsg.copy(cMsg.A.no(cnt), null, cMsg.A.no(cnt - selectIndexThru + selectIndexFrom), null);
        }
        for (int delCnt = cMsg.A.getValidCount() - (selectIndexThru - selectIndexFrom); delCnt < cMsg.A.getValidCount(); delCnt++) {
            cMsg.A.no(delCnt).clear();
        }
        cMsg.A.setValidCount(cMsg.A.getValidCount() - (selectIndexThru - selectIndexFrom));
    }

    private void doProcess_NSAL0570Scrn00_InsertRow(NSAL0570CMsg cMsg) {

        // START 2016/05/20 T.Tomita [QC#4923, ADD]
        NSAL0570CommonLogic.copyPeBlock(cMsg);
        // END 2016/05/20 T.Tomita [QC#4923, ADD]
        int index = cMsg.A.getValidCount();

        if (DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.dsContrDtlTpCd_A.getValue())) {
            int sqLineCnt = 0;
            BigDecimal preSqNum = cMsg.A.no(index - 1).dsContrPrcEffSqNum_A1.getValue();
            for (int i = index - 1; i >= 0; i--) {
                BigDecimal curSqNum = cMsg.A.no(i).dsContrPrcEffSqNum_A1.getValue();
                if (preSqNum.compareTo(curSqNum) != 0) {
                    break;
                }
                sqLineCnt++;
            }
            if (cMsg.A.length() < index + sqLineCnt) {
                cMsg.setMessageInfo(NSAM0112E);
                return;
            }
            // START 2020/01/15 [QC#55434, ADD]
            if (checkDetailRows(cMsg)) {
                return;
            }
            // END   2020/01/15 [QC#55434, ADD]

            setLastAddRowDataForAgg(cMsg, index, sqLineCnt);
        } else {
            if (cMsg.A.length() < index + 1) {
                cMsg.setMessageInfo(NSAM0112E);
                return;
            }
            // START 2020/01/15 [QC#55434, ADD]
            if (checkDetailRows(cMsg)) {
                return;
            }
            // END   2020/01/15 [QC#55434, ADD]

            NSAL0570_ACMsg acMsg = cMsg.A.no(index);
            acMsg.clear();

            NSAL0570_ACMsg lastAcMsg = null;
            if (index != 0) {
                lastAcMsg = cMsg.A.no(index - 1);
            }

            setLastAddRowData(acMsg, cMsg, index, lastAcMsg);
            cMsg.A.setValidCount(index + 1);
        }
    }

    private void setLastAddRowDataForAgg(NSAL0570CMsg cMsg, int index, int sqLineCnt) {
        int stIdx = index - sqLineCnt;
        int edIdx = index - 1;
        int toIdx = index;
        BigDecimal peSqNum = cMsg.A.no(stIdx).dsContrPrcEffSqNum_A1.getValue().add(BigDecimal.ONE);
        for (int i = stIdx; i < edIdx + 1; i++) {
            NSAL0570_ACMsg toMsg = cMsg.A.no(toIdx);
            NSAL0570_ACMsg fromMsg = cMsg.A.no(i);
            setValue(toMsg.dsContrPrcEffSqNum_A1, peSqNum);
            setValue(toMsg.contrPrcEffFromDt_A1, fromMsg.contrPrcEffThruDt_A1);
            setValue(toMsg.contrPrcEffThruDt_A1, fromMsg.contrPrcEffThruDt_A1);
            setValue(toMsg.bllgCycleCd_A3, fromMsg.bllgCycleCd_A3);
            setValue(toMsg.bllgMinCnt_A1, fromMsg.bllgMinCnt_A1);
            setValue(toMsg.bllgMinAmtRate_A1, fromMsg.bllgMinAmtRate_A1);
            setValue(toMsg.bllgRollOverRatio_A1, fromMsg.bllgRollOverRatio_A1);
            setValue(toMsg.bllgFreeCopyCnt_A1, fromMsg.bllgFreeCopyCnt_A1);
            setValue(toMsg.xsMtrCopyQty_A1, fromMsg.xsMtrCopyQty_A1);
            setValue(toMsg.xsMtrAmtRate_A1, fromMsg.xsMtrAmtRate_A1);
            toMsg.dsContrDtlStsCd_A1.clear();
            toMsg.dsContrDtlStsNm_A1.clear();
            toMsg.cratDt_A1.clear();
            // START 2016/05/20 T.Tomita [QC#4923, DEL]
//            toMsg.svcMemoRsnNm_A1.clear();
//            toMsg.svcMemoRsnCd_A3.clear();
//            toMsg.svcCmntTxt_A1.clear();
            // END 2016/05/20 T.Tomita [QC#4923, DEL]
            toMsg.dsContrPrcEffPk_A1.clear();
            DS_CONTR_DTLTMsg dsContrDtlTMsg = NSAL0570Query.getInstance().getDsContrDtl(getGlobalCompanyCode(), cMsg.dsContrDtlPk_H1.getValue());
            setValue(toMsg.dsContrPrcEffStsCd_A2, dsContrDtlTMsg.dsContrDtlStsCd);
            setValue(toMsg.qltyAsrnHldFlg_A2, fromMsg.qltyAsrnHldFlg_A2);
            setValue(toMsg.mtrHldFlg_A2, fromMsg.mtrHldFlg_A2);
            setValue(toMsg.contrHldFlg_A2, fromMsg.contrHldFlg_A2);
            setValue(toMsg.bllgHldFlg_A2, fromMsg.bllgHldFlg_A2);
            toMsg.ezUpTime_A1.clear();
            toMsg.ezUpTimeZone_A1.clear();
            toMsg.contrXsCopyPk_A1.clear();
            toIdx++;
        }
        cMsg.A.setValidCount(index + sqLineCnt);
    }

    private void setLastAddRowData(NSAL0570_ACMsg acMsg, NSAL0570CMsg cMsg, int index, NSAL0570_ACMsg lastAcMsg) {
        if (index == 0) {
            setValue(acMsg.dsContrPrcEffSqNum_A1, BigDecimal.ONE);
            setValue(acMsg.contrPrcEffFromDt_A1, cMsg.contrEffFromDt_H1);
        } else {
            setValue(acMsg.dsContrPrcEffSqNum_A1, NSAL0570CommonLogic.getNextSeqNum(lastAcMsg));
            String bllgSchdFromDt = NSAL0570Query.getInstance().getMinBllgSchdFromDt(getGlobalCompanyCode(), acMsg.dsContrPrcEffPk_A1.getValue());
            if (ZYPCommonFunc.hasValue(bllgSchdFromDt)) {
                setValue(acMsg.contrPrcEffFromDt_A1, bllgSchdFromDt);
            } else if (lastAcMsg.contrPrcEffThruDt_A1.getValue().equals(cMsg.contrEffThruDt_H1.getValue())) {
                setValue(acMsg.contrPrcEffFromDt_A1, cMsg.contrEffThruDt_H1);
            // START 2020/01/15 [QC#55434, MOD]
            // } else {
            } else if (ZYPCommonFunc.hasValue(lastAcMsg.contrPrcEffThruDt_A1)) {
            // END   2020/01/15 [QC#55434, MOD]
                setValue(acMsg.contrPrcEffFromDt_A1, ZYPDateUtil.addDays(lastAcMsg.contrPrcEffThruDt_A1.getValue(), 1));
            }
        }
        setValue(acMsg.contrPrcEffThruDt_A1, cMsg.contrEffThruDt_H1);

        if (hasValue(lastAcMsg.bllgCycleCd_A3)) {
            setValue(acMsg.bllgCycleCd_A3, lastAcMsg.bllgCycleCd_A3);
        } else {
            setValue(acMsg.bllgCycleCd_A3, cMsg.mtrBllgCycleCd_A);
        }
        setValue(acMsg.bllgMinCnt_A1, lastAcMsg.bllgMinCnt_A1);
        setValue(acMsg.bllgMinAmtRate_A1, lastAcMsg.bllgMinAmtRate_A1);
        setValue(acMsg.bllgRollOverRatio_A1, lastAcMsg.bllgRollOverRatio_A1);
        setValue(acMsg.bllgFreeCopyCnt_A1, lastAcMsg.bllgFreeCopyCnt_A1);
        if (hasValue(lastAcMsg.xsMtrAmtRate_A1)) {
            setValue(acMsg.xsMtrAmtRate_A1, lastAcMsg.xsMtrAmtRate_A1);
            setValue(acMsg.xsMtrCopyQty_A1, lastAcMsg.xsMtrCopyQty_A1);
        } else {
            CONTR_XS_COPYTMsgArray getContrCsCopy = NSAL0570Query.getInstance().getContrXsCopy(getGlobalCompanyCode(), cMsg.dsContrBllgMtrPk_H1.getValue());
            if (getContrCsCopy != null) {
                setValue(acMsg.xsMtrAmtRate_A1, getContrCsCopy.no(0).xsMtrAmtRate);
                setValue(acMsg.xsMtrCopyQty_A1, getContrCsCopy.no(0).xsMtrCopyQty);
            }
        }
        setValue(acMsg.dsContrDtlStsCd_A1, cMsg.dsContrCtrlStsCd_A);
        setValue(acMsg.dsContrDtlStsNm_A1, cMsg.dsContrCtrlStsNm_A);
        // START 2016/01/06 [QC2783, ADD]
        DS_CONTR_DTLTMsg dsContrDtlTMsg = NSAL0570Query.getInstance().getDsContrDtl(getGlobalCompanyCode(), cMsg.dsContrDtlPk_H1.getValue());
        setValue(acMsg.dsContrPrcEffStsCd_A2, dsContrDtlTMsg.dsContrDtlStsCd);
        setValue(acMsg.qltyAsrnHldFlg_A2, dsContrDtlTMsg.qltyAsrnHldFlg);
        setValue(acMsg.mtrHldFlg_A2, dsContrDtlTMsg.mtrHldFlg);
        setValue(acMsg.contrHldFlg_A2, dsContrDtlTMsg.contrHldFlg);
        setValue(acMsg.bllgHldFlg_A2, dsContrDtlTMsg.bllgHldFlg);
        // END 2016/01/06 [QC2783, ADD]
        acMsg.cratDt_A1.clear();
        // START 2016/05/20 T.Tomita [QC#4923, DEL]
//        acMsg.svcMemoRsnNm_A1.clear();
        // END 2016/05/20 T.Tomita [QC#4923, DEL]
    }

    private void doProcess_NSAL0570Scrn00_DeletePrcAllowRow(NSAL0570CMsg cMsg) {

        // mod start 2015/12/04 CSA Defect#1518
        if (!hasValue(cMsg.xxRadioBtn_B)) {
            cMsg.setMessageInfo(NSAM0034E);
            return;
        }
        int selectIndex = cMsg.xxRadioBtn_B.getValueInt();

        int sameSeqLineCnt = getSameSeqLineCnt(cMsg, selectIndex);
        if (sameSeqLineCnt <= 1) {
            cMsg.setMessageInfo(NSAM0198E, new String[] {"Price & Allowance"});
            return;
        }

        //check Aggregate Line
        if (NSAL0570Query.getInstance().existAggregateContr(cMsg) && !checkAggregateLine(cMsg, selectIndex)) {
            cMsg.setMessageInfo(NSAM0376E);
            return;
        }

        ZYPTableUtil.deleteRows(cMsg.A, Arrays.asList(selectIndex));
        // mod end 2015/12/04 CSA Defect#1518
    }

    private void doProcess_NSAL0570Scrn00_InsertPrcAllowRow(NSAL0570CMsg cMsg) {

        //specify row number
        int rowNum = cMsg.rowSqNum_A.getValueInt();
        //check Aggregate Line
        if (NSAL0570Query.getInstance().existAggregateContr(cMsg) && !checkAggregateLine(cMsg, rowNum)) {
            cMsg.setMessageInfo(NSAM0376E);
            return;
        }
        //add row
        int aValidCnt = cMsg.A.getValidCount();
        if (aValidCnt > 0) {
            for (int i = aValidCnt; i > rowNum; i--) {
                EZDMsg.copy(cMsg.A.no(i - 1), null, cMsg.A.no(i), null);
                if (i == rowNum + 1) {
                    if (cMsg.D.getValidCount() > 0) {
                        setValue(cMsg.A.no(i).xsMtrAmtRate_A1, cMsg.D.no(cMsg.D.getValidCount() - 1).xsMtrAmtRate_D);
                        setValue(cMsg.A.no(i).xsMtrCopyQty_A1, BigDecimal.valueOf(cMsg.A.no(i).xsMtrCopyQty_A1.getValueInt() + 1));
                    } else {
                        cMsg.A.no(i).xsMtrAmtRate_A1.clear();
                        cMsg.A.no(i).xsMtrCopyQty_A1.clear();
                    }
                    // START 2020/03/12 K.Kitachi [QC#55662, ADD]
                    cMsg.A.no(i).contrXsCopyPk_A1.clear();
                    // END 2020/03/12 K.Kitachi [QC#55662, ADD]
                } else if (i > rowNum + 1) {
                    EZDMsg.copy(cMsg.A.no(i - 1), null, cMsg.A.no(i), null);
                }
            }
        }
        cMsg.A.setValidCount(cMsg.A.getValidCount() + 1);
    }

    private boolean checkAggregateLine(NSAL0570CMsg cMsg, int rowNum) {
        if (getAggregateLineCnt(cMsg, rowNum) == cMsg.A.getValidCount()) {
            return true;
        }
        return false;
    }

    private int getAggregateLineCnt(NSAL0570CMsg cMsg, int rowNum) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", cMsg.glblCmpyCd);
        queryMap.put("dsContrDtlPk", cMsg.dsContrDtlPk_H1);
        queryMap.put("dsContrBllgMtrPk", cMsg.dsContrBllgMtrPk_H1);
        queryMap.put("useChrgFlg", FLG_ON_Y);
        queryMap.put("fromDt", cMsg.A.no(rowNum).contrPrcEffFromDt_A1);
        queryMap.put("thruDt", cMsg.A.no(rowNum).contrPrcEffThruDt_A1);
        S21SsmEZDResult ssmResult = NSAL0570Query.getInstance().getPrntPrcEffMtr(queryMap, cMsg.D);
        return ssmResult.getQueryResultCount();
    }

    private void initParam(NSAL0570CMsg cMsg) {
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());
    }

    private void findContrInfo(NSAL0570CMsg cMsg) {

        // Header
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("dsContrBllgMtrPk", cMsg.dsContrBllgMtrPk_H1.getValue());
        queryMap.put("dsContrDtlPk", cMsg.dsContrDtlPk_H1.getValue());
        S21SsmEZDResult ssmResult = NSAL0570Query.getInstance().getContrDtl(queryMap, cMsg);

        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAM0013E);
            return;
        }

        // Detail
        queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("dsContrDtlPk", cMsg.dsContrDtlPk_H1.getValue());
        queryMap.put("usgChrgFlg", FLG_ON_Y);
        queryMap.put("dsContrBllgMtrPk", cMsg.dsContrBllgMtrPk_H1.getValue());
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

        ssmResult = NSAL0570Query.getInstance().getContrPrcEff(queryMap, cMsg.A);

        if (ssmResult.getQueryResultCount() > SEARCH_LIMIT_CNT) {
            cMsg.setMessageInfo(NZZM0001W);
        }

        cMsg.xxRadioBtn_A.setValue(0);

        return;
    }

    private boolean isErrorForValidationCheck(NSAL0570CMsg cMsg, NSAL0570_ACMsg aCMsg) {
        if (existInvoicedBllgSchd(cMsg, aCMsg)
                || checkDsContrCtrlSts(cMsg, aCMsg)) {
            return true;
        }
        return false;
    }

    private boolean existInvoicedBllgSchd(NSAL0570CMsg cMsg, NSAL0570_ACMsg aCMsg) {
        DS_CONTR_BLLG_SCHDTMsgArray outTMsgArray = NSAL0570Query.getInvoicedBllgSchd(cMsg, aCMsg);
        if (outTMsgArray.length() > 0) {
            cMsg.setMessageInfo(NSAM0368E);
            return true;
        }
        return false;

    }

    private boolean checkDsContrCtrlSts(NSAL0570CMsg cMsg, NSAL0570_ACMsg aCMsg) {
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

    private String dsContrCtrlStsNm(NSAL0570CMsg cMsg, String dsContrStrlStsCd) {
        DS_CONTR_CTRL_STSTMsg inTMsg = new DS_CONTR_CTRL_STSTMsg();
        inTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        inTMsg.dsContrCtrlStsCd.setValue(dsContrStrlStsCd);
        DS_CONTR_CTRL_STSTMsg outTMsg = (DS_CONTR_CTRL_STSTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg.dsContrCtrlStsNm.getValue();
    }

    /**
     * @param cMsg
     * @param selectIndexFrom
     */
    private int getSelectIndexThru(NSAL0570CMsg cMsg, int selectIndexFrom) {
        int selectIndexThru;
        int sqNum = cMsg.A.no(selectIndexFrom).dsContrPrcEffSqNum_A1.getValueInt();
        selectIndexThru = selectIndexFrom;
        for (int i = selectIndexFrom; i < cMsg.A.getValidCount(); i++) {
            if (sqNum == cMsg.A.no(i).dsContrPrcEffSqNum_A1.getValueInt()) {
                selectIndexThru++;
            } else {
                break;
            }
        }
        return selectIndexThru;
    }

    private int getSameSeqLineCnt(NSAL0570CMsg cMsg, int selectIndexFrom) {
        int sameSeqLineCnt = 0;
        int sqNum = cMsg.A.no(selectIndexFrom).dsContrPrcEffSqNum_A1.getValueInt();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (sqNum == cMsg.A.no(i).dsContrPrcEffSqNum_A1.getValueInt()) {
                sameSeqLineCnt++;
            // START 2015/12/11 [QC1664, DEL]
            //} else {
            //    break;
            // END 2015/12/11 [QC1664, DEL]
            }
        }
        return sameSeqLineCnt;
    }

    // START 2020/01/15 [QC#55434, ADD]
    private boolean checkDetailRows(NSAL0570CMsg cMsg) {
        boolean retFlg = false;

        // mandatory check
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (!DS_CONTR_CTRL_STS.CANCELLED.equals(cMsg.A.no(i).dsContrDtlStsCd_A1.getValue())) {
                if (!hasValue(cMsg.A.no(i).contrPrcEffFromDt_A1)) {
                    cMsg.A.no(i).contrPrcEffFromDt_A1.setErrorInfo(1, ZZZM9025E, new String[] {"Start Date" });
                    retFlg = true;
                }
                if (!hasValue(cMsg.A.no(i).contrPrcEffThruDt_A1)) {
                    cMsg.A.no(i).contrPrcEffThruDt_A1.setErrorInfo(1, ZZZM9025E, new String[] {"End Date" });
                    retFlg = true;
                }
                if (!hasValue(cMsg.A.no(i).bllgCycleCd_A3)) {
                    cMsg.A.no(i).bllgCycleCd_A3.setErrorInfo(1, ZZZM9025E, new String[] {"Frequency" });
                    retFlg = true;
                }
                if (!hasValue(cMsg.A.no(i).xsMtrCopyQty_A1)) {
                    cMsg.A.no(i).xsMtrCopyQty_A1.setErrorInfo(1, ZZZM9025E, new String[] {"Allowance" });
                    retFlg = true;
                }
                if (!hasValue(cMsg.A.no(i).xsMtrAmtRate_A1)) {
                    cMsg.A.no(i).xsMtrAmtRate_A1.setErrorInfo(1, ZZZM9025E, new String[] {"Price" });
                    retFlg = true;
                }
            }
        }

        return retFlg;
    }
    // END   2020/01/15 [QC#55434, ADD]
}
