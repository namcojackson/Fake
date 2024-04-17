/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1120;

import static business.blap.NSAL1120.common.NSAL1120CommonLogic.cheackMeterDetails;
import static business.blap.NSAL1120.common.NSAL1120CommonLogic.clearMsg;
import static business.blap.NSAL1120.common.NSAL1120CommonLogic.pagenation;
import static business.blap.NSAL1120.common.NSAL1120CommonLogic.pagenationForInit;
import static business.blap.NSAL1120.common.NSAL1120CommonLogic.pagenationForReview;
import static business.blap.NSAL1120.common.NSAL1120CommonLogic.setPagenation;
import static business.blap.NSAL1120.common.NSAL1120CommonLogic.setPagenationForAll;
import static business.blap.NSAL1120.constant.NSAL1120Constant.MODE_CODE;
import static business.blap.NSAL1120.constant.NSAL1120Constant.MODE_CODE_BASE;
import static business.blap.NSAL1120.constant.NSAL1120Constant.MODE_CODE_USAGE;
import static business.blap.NSAL1120.constant.NSAL1120Constant.NSAM0015E;
import static business.blap.NSAL1120.constant.NSAL1120Constant.NZZM0001W;
import static business.blap.NSAL1120.constant.NSAL1120Constant.NZZM0012E;
import static business.blap.NSAL1120.constant.NSAL1120Constant.ZZZM9001E;
import static business.blap.NSAL1120.constant.NSAL1120Constant.PAGE_MODE_NEXT;
import static business.blap.NSAL1120.constant.NSAL1120Constant.PAGE_MODE_PREV;
import static business.blap.NSAL1120.constant.NSAL1120Constant.SPCL_FLT_MDSE_CD;
import static business.blap.NSAL1120.constant.NSAL1120Constant.TABLE_B;
import static business.blap.NSAL1120.constant.NSAL1120Constant.ZZM8100I;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.db.SVC_CR_REBIL_DTLTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
/**
 *<pre>
 * Meter and Pricing Details
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         O.Okuma         Create          N/A
 * 2016/03/23   Hitachi         O.Okuma         Update          QC#5421
 * 2016/12/07   Hitachi         N.Arai          Create          QC#14204
 * 2017/09/15   Hitachi         E.Kameishi      Update          QC#18636
 * 2017/09/21   Hitachi         U.Kim           Update          QC#21218
 * 2017/10/02   Hitachi         U.Kim           Update          QC#18749
 * 2018/07/19   Hitachi         K.Kojima        Update          QC#26791
 * 2018/08/27   Hitachi         K.Kishimoto     Update          QC#24555
 * 2022/04/04   CITS            E.Sanchez       Update          QC#59914
 *</pre>
 */
public class NSAL1120BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1120CMsg cMsg = (NSAL1120CMsg) arg0;
        NSAL1120SMsg sMsg = (NSAL1120SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1120_INIT".equals(screenAplID)) {
                doProcess_NSAL1120_INIT(cMsg, sMsg);
            } else if ("NSAL1120Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1120Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSAL1120Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1120Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL1120Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1120Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL1120Scrn00_Review".equals(screenAplID)) {
                doProcess_NSAL1120Scrn00_Review(cMsg, sMsg);
            } else if ("NSAL1120Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL1120Scrn00_CMN_Reset(cMsg, sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1120_INIT(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        if (!hasValue(cMsg.xxModeCd)) {
            cMsg.setMessageInfo(NZZM0012E, new String[] {MODE_CODE });
            return;
        }
        init(cMsg, sMsg);
    }

    private void doProcess_NSAL1120Scrn00_CMN_Submit(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {
        init(cMsg, sMsg);
    }

    private void doProcess_NSAL1120Scrn00_PageNext(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        if (TABLE_B.equals(cMsg.xxModeInd.getValue())) {
            if (!cheackMeterDetails(cMsg)) {
                return;
            }
        }
        setPagenation(cMsg, sMsg);
        pagenation(cMsg, sMsg, PAGE_MODE_NEXT);
    }

    private void doProcess_NSAL1120Scrn00_PagePrev(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        if (TABLE_B.equals(cMsg.xxModeInd.getValue())) {
            if (!cheackMeterDetails(cMsg)) {
                return;
            }
        }
        setPagenation(cMsg, sMsg);
        pagenation(cMsg, sMsg, PAGE_MODE_PREV);
    }

    private void doProcess_NSAL1120Scrn00_Review(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        setPagenationForAll(cMsg, sMsg);
        ZYPTableUtil.clear(sMsg.C);

        if (getPricingData(cMsg, sMsg)) {
            pagenationForReview(cMsg, sMsg);
            cMsg.xxPageShowOfNum_C.setValue(sMsg.C.getValidCount());
        }
    }

    private void doProcess_NSAL1120Scrn00_CMN_Reset(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {
        init(cMsg, sMsg);
    }

    private void init(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {
        clearMsg(cMsg, sMsg);
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        // START 2017/10/02 U.Kim [QC#18749, ADD]
        setSvcCrRebilPk(cMsg);
        // END 2017/10/02 U.Kim [QC#18749, ADD]

        if (MODE_CODE_BASE.equals(cMsg.xxModeCd.getValue())) {
            getBaseData(cMsg, sMsg);
        } else if (MODE_CODE_USAGE.equals(cMsg.xxModeCd.getValue())) {
            if (getMeterData(cMsg, sMsg)) {
                getPricingData(cMsg, sMsg);
            }
        } else {
            cMsg.setMessageInfo(NZZM0012E, new String[] {MODE_CODE });
        }

        pagenationForInit(cMsg, sMsg);
        cMsg.xxPageShowOfNum_A.setValue(sMsg.A.getValidCount());
        cMsg.xxPageShowOfNum_B.setValue(sMsg.B.getValidCount());
        cMsg.xxPageShowOfNum_C.setValue(sMsg.C.getValidCount());
    }

    private boolean getBaseData(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        S21SsmEZDResult ssmResult;
        // START 2017/10/02 U.Kim [QC#18749, MOD]
        // if (hasValue(cMsg.svcInvLinePk)) {
        if (checkInvMode(cMsg)) {
        // END 2017/10/02 U.Kim [QC#18749, MOD]
            ssmResult = NSAL1120Query.getInstance().getBaseDataForInv(cMsg, sMsg);
        } else {
            ssmResult = NSAL1120Query.getInstance().getBaseDataForRebil(cMsg, sMsg);
        }

        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
            setBaseData(cMsg, sMsg, list);

            if (list.size() > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(ZZM8100I);
            }
        } else {
            cMsg.setMessageInfo(ZZZM9001E);
            return false;
        }
        return true;
    }

    private boolean getMeterData(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        S21SsmEZDResult ssmResult;
        // START 2017/10/02 U.Kim [QC#18749, MOD]
        // if (hasValue(cMsg.svcInvLinePk)) {
        if (checkInvMode(cMsg)) {
        // END 2017/10/02 U.Kim [QC#18749, MOD]
            ssmResult = NSAL1120Query.getInstance().getMeterDataForInv(cMsg, sMsg);
        } else {
            ssmResult = NSAL1120Query.getInstance().getMeterDataForRebil(cMsg, sMsg);
        }

        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
            setMeterData(cMsg, sMsg, list);

            if (list.size() > sMsg.B.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(ZZM8100I);
            }
        } else {
            cMsg.setMessageInfo(ZZZM9001E);
            return false;
        }
        return true;
    }

    private boolean getPricingData(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.B, "xxChkBox_B", ZYPConstant.CHKBOX_ON_Y);

        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSAM0015E);
            return false;
        }
        S21SsmEZDResult ssmResult;
        if (hasValue(cMsg.svcInvLinePk)) {
            if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue())) {
                ssmResult = NSAL1120Query.getInstance().getPricingDataForInvFleet(cMsg, sMsg, selectedRows);
            } else {
                ssmResult = NSAL1120Query.getInstance().getPricingDataForInvNonFleet(cMsg, sMsg, selectedRows);
            }
        } else {
            if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue())) {
                ssmResult = NSAL1120Query.getInstance().getPricingDataForRebilFleet(cMsg, sMsg, selectedRows);
            } else {
                ssmResult = NSAL1120Query.getInstance().getPricingDataForRebilNonFleet(cMsg, sMsg, selectedRows);
            }
        }

        if (ssmResult.isCodeNormal()) {
            setTierCount(cMsg, sMsg);

            if (ssmResult.getQueryResultCount() > sMsg.C.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(ZZM8100I);
            }
        } else {
            cMsg.setMessageInfo(ZZZM9001E);
            return false;
        }
        return true;
    }

    private void setBaseData(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg, List<Map<String, Object>> list) {

        Map<String, Object> headerMap = list.get(0);
        setValue(cMsg.custIncdtId, (String) headerMap.get("CUST_INCDT_ID"));
        setValue(cMsg.svcCrRebilStsDescTxt, (String) headerMap.get("SVC_CR_REBIL_STS_DESC_TXT"));
        setValue(cMsg.dsContrCatgCd, (String) headerMap.get("DS_CONTR_CATG_CD"));
        // add start 2016/04/18 CSA Defect#7056
        setValue(cMsg.dsContrCatgAbbrNm, (String) headerMap.get("DS_CONTR_CATG_ABBR_NM"));
        // add end 2016/04/18 CSA Defect#7056
        setValue(cMsg.svcCrRebilDescTxt, (String) headerMap.get("SVC_CR_REBIL_DESC_TXT"));
        setValue(cMsg.dsContrNum, (String) headerMap.get("DS_CONTR_NUM"));
        setValue(cMsg.ezUpTime, (String) headerMap.get("EZUPTIME"));
        setValue(cMsg.ezUpTimeZone, (String) headerMap.get("EZUPTIMEZONE"));
        // START 2017/10/02 U.Kim [QC#18749, ADD]
        if (checkInvMode(cMsg)) {
            setValue(cMsg.svcCrRebilStsCd, (String) headerMap.get("SVC_CR_REBIL_STS_CD"));
        }
        // END 2017/10/02 U.Kim [QC#18749, ADD]

        String serNumFltAgg = getSerNumForFltAgg(cMsg);
        int i = 0;
        for (; i < list.size(); i++) {
            if (i < sMsg.A.length()) {
                Map<String, Object> map = list.get(i);
                setValue(sMsg.A.no(i).svcCrRebilPk_A, (BigDecimal) map.get("SVC_CR_REBIL_PK"));
                setValue(sMsg.A.no(i).svcCrRebilDtlPk_A, (BigDecimal) map.get("SVC_CR_REBIL_DTL_PK"));
                setValue(sMsg.A.no(i).origSvcInvNum_A, (String) map.get("ORIG_SVC_INV_NUM"));

                if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue())) {
                    setValue(sMsg.A.no(i).xxScrItem40Txt_A, serNumFltAgg);
                    setValue(sMsg.A.no(i).mdseCd_A, ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, cMsg.glblCmpyCd.getValue()));
                } else {
                    setValue(sMsg.A.no(i).serNum_A, (String) map.get("SER_NUM"));
                    setValue(sMsg.A.no(i).xxScrItem40Txt_A, (String) map.get("SER_NUM"));
                    setValue(sMsg.A.no(i).mdseCd_A, (String) map.get("MDSE_CD"));
                }

                // START 2022/04/04 E.Sanchez [QC#59914,ADD]
                setValue(sMsg.A.no(i).dsContrDtlPk_A, (BigDecimal) map.get("DS_CONTR_DTL_PK"));
                setValue(sMsg.A.no(i).svcMachMstrPk_A, (BigDecimal) map.get("SVC_MACH_MSTR_PK"));
                setValue(sMsg.A.no(i).mdseDescShortTxt_A, (String) map.get("MDSE_DESC_SHORT_TXT"));
                // END 2022/04/04 E.Sanchez [QC#59914,ADD]
                setValue(sMsg.A.no(i).baseBllgFromDt_A, (String) map.get("BASE_BLLG_FROM_DT"));
                setValue(sMsg.A.no(i).baseBllgThruDt_A, (String) map.get("BASE_BLLG_THRU_DT"));
                setValue(sMsg.A.no(i).oldBaseDealAmt_A, (BigDecimal) map.get("OLD_BASE_DEAL_AMT"));
                setValue(sMsg.A.no(i).newBaseDealAmt_A, (BigDecimal) map.get("NEW_BASE_DEAL_AMT"));
                // START 2018/08/27 [QC#24555, ADD]
                setValue(sMsg.A.no(i).oldBaseUnitAmt_A, (BigDecimal) map.get("OLD_BASE_UNIT_AMT"));
                setValue(sMsg.A.no(i).newBaseUnitAmt_A, (BigDecimal) map.get("NEW_BASE_UNIT_AMT"));
                // END   2018/08/27 [QC#24555, ADD]
// START 2016/12/07 N.Arai [QC#14204, MOD]
                setValue(sMsg.A.no(i).xxRecHistCratTs_A, (String) map.get("XX_REC_HIST_CRAT_TS"));
                setValue(sMsg.A.no(i).xxRecHistCratByNm_A, (String) map.get("XX_REC_HIST_CRAT_BY_NM"));
                setValue(sMsg.A.no(i).xxRecHistUpdTs_A, (String) map.get("XX_REC_HIST_UPD_TS"));
                setValue(sMsg.A.no(i).xxRecHistUpdByNm_A, (String) map.get("XX_REC_HIST_UPD_BY_NM"));
                setValue(sMsg.A.no(i).xxRecHistTblNm_A, (String) map.get("XX_REC_HIST_TBL_NM"));
// END 2016/12/07 N.Arai [QC#14204, MOD]
            } else {
                break;
            }
        }
        sMsg.A.setValidCount(i);
    }

    private void setMeterData(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg, List<Map<String, Object>> list) {

        Map<String, Object> headerMap = list.get(0);
        setValue(cMsg.custIncdtId, (String) headerMap.get("CUST_INCDT_ID"));
        setValue(cMsg.svcCrRebilStsDescTxt, (String) headerMap.get("SVC_CR_REBIL_STS_DESC_TXT"));
        setValue(cMsg.svcCrRebilDescTxt, (String) headerMap.get("SVC_CR_REBIL_DESC_TXT"));
        setValue(cMsg.dsContrCatgCd, (String) headerMap.get("DS_CONTR_CATG_CD"));
        // add start 2016/04/18 CSA Defect#7056
        setValue(cMsg.dsContrCatgAbbrNm, (String) headerMap.get("DS_CONTR_CATG_ABBR_NM"));
        // add end 2016/04/18 CSA Defect#7056
        setValue(cMsg.svcCrRebilDescTxt, (String) headerMap.get("SVC_CR_REBIL_DESC_TXT"));
        setValue(cMsg.dsContrNum, (String) headerMap.get("DS_CONTR_NUM"));
        setValue(cMsg.ezUpTime, (String) headerMap.get("EZUPTIME"));
        setValue(cMsg.ezUpTimeZone, (String) headerMap.get("EZUPTIMEZONE"));
        // START 2017/10/02 U.Kim [QC#18749, ADD]
        if (checkInvMode(cMsg)) {
            setValue(cMsg.svcCrRebilStsCd, (String) headerMap.get("SVC_CR_REBIL_STS_CD"));
        }
        // END 2017/10/02 U.Kim [QC#18749, ADD]

        int i = 0;
        for (; i < list.size(); i++) {
            if (i < sMsg.B.length()) {
                Map<String, Object> map = list.get(i);
                setValue(sMsg.B.no(i).xxRowNum_B, new BigDecimal(i));
                setValue(sMsg.B.no(i).svcCrRebilMtrReadPk_B, (BigDecimal) map.get("SVC_CR_REBIL_MTR_READ_PK"));
                setValue(sMsg.B.no(i).svcCrRebilMtrBllgPk_B, (BigDecimal) map.get("SVC_CR_REBIL_MTR_BLLG_PK"));
                setValue(sMsg.B.no(i).svcMachMstrPk_B, (BigDecimal) map.get("SVC_MACH_MSTR_PK"));
                setValue(sMsg.B.no(i).svcInvLineMtrPk_B, (BigDecimal) map.get("SVC_INV_LINE_MTR_PK"));
                setValue(sMsg.B.no(i).bllgMtrLbCd_B, (String) map.get("BLLG_MTR_LB_CD"));
                setValue(sMsg.B.no(i).origSvcInvNum_B, (String) map.get("ORIG_SVC_INV_NUM"));
                setValue(sMsg.B.no(i).mtrLbCd_B, (String) map.get("MTR_LB_CD"));

                setValue(sMsg.B.no(i).serNum_B, (String) map.get("SER_NUM"));

                setValue(sMsg.B.no(i).startMtrReadDt_B, (String) map.get("START_MTR_READ_DT"));
                setValue(sMsg.B.no(i).endMtrReadDt_B, (String) map.get("END_MTR_READ_DT"));
                setValue(sMsg.B.no(i).mtrLbDescTxt_B, (String) map.get("MTR_LB_DESC_TXT"));
                setValue(sMsg.B.no(i).oldStartReadMtrCnt_B, (BigDecimal) map.get("OLD_START_READ_MTR_CNT"));
                setValue(sMsg.B.no(i).oldEndReadMtrCnt_B, (BigDecimal) map.get("OLD_END_READ_MTR_CNT"));
                //START 2017/09/15 E.Kameishi [QC#18636,MOD]
                setValue(sMsg.B.no(i).oldTestMtrCnt_B, (BigDecimal) map.get("OLD_TEST_MTR_CNT"));
//                setValue(sMsg.B.no(i).oldStartTestMtrCnt_B, (BigDecimal) map.get("OLD_START_TEST_MTR_CNT"));
//                setValue(sMsg.B.no(i).oldEndTestMtrCnt_B, (BigDecimal) map.get("OLD_END_TEST_MTR_CNT"));
                //END 2017/09/15 E.Kameishi [QC#18636,MOD]
                setValue(sMsg.B.no(i).newStartReadMtrCnt_B, (BigDecimal) map.get("NEW_START_READ_MTR_CNT"));
                setValue(sMsg.B.no(i).newEndReadMtrCnt_B, (BigDecimal) map.get("NEW_END_READ_MTR_CNT"));
                //START 2017/09/15 E.Kameishi [QC#18636,MOD]
                setValue(sMsg.B.no(i).newTestMtrCnt_B, (BigDecimal) map.get("NEW_TEST_MTR_CNT"));
//                setValue(sMsg.B.no(i).newStartTestMtrCnt_B, (BigDecimal) map.get("NEW_START_TEST_MTR_CNT"));
//                setValue(sMsg.B.no(i).newEndTestMtrCnt_B, (BigDecimal) map.get("NEW_END_TEST_MTR_CNT"));
                //END 2017/09/15 E.Kameishi [QC#18636,MOD]
// START 2016/12/07 N.Arai [QC#14204, MOD]
                setValue(sMsg.B.no(i).xxRecHistCratTs_B, (String) map.get("XX_REC_HIST_CRAT_TS"));
                setValue(sMsg.B.no(i).xxRecHistCratByNm_B, (String) map.get("XX_REC_HIST_CRAT_BY_NM"));
                setValue(sMsg.B.no(i).xxRecHistUpdTs_B, (String) map.get("XX_REC_HIST_UPD_TS"));
                setValue(sMsg.B.no(i).xxRecHistUpdByNm_B, (String) map.get("XX_REC_HIST_UPD_BY_NM"));
                setValue(sMsg.B.no(i).xxRecHistTblNm_B, (String) map.get("XX_REC_HIST_TBL_NM"));
// END 2016/12/07 N.Arai [QC#14204, MOD]
            } else {
                break;
            }
        }
        sMsg.B.setValidCount(i);
        // mod start 2016/04/21 CSA Defect#7251
        chkOnToBllgMtrMappedFirstLine(sMsg);
        // mod end 2016/04/21 CSA Defect#7251
    }

    // add start 2016/04/21 CSA Defect#7251
    private void chkOnToBllgMtrMappedFirstLine(NSAL1120SMsg sMsg) {
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL1120_BSMsg bsMsg = sMsg.B.no(i);
            // START 2018/07/19 K.Kojima [QC#26791,MOD]
            // if (hasValue(bsMsg.bllgMtrLbCd_B)) {
            //     setValue(bsMsg.xxChkBox_B, ZYPConstant.CHKBOX_ON_Y);
            //     // START 2017/09/21 U.Kim [QC#21218, DEL]
            //     //break;
            //     // END 2017/09/21 U.Kim [QC#21218, DEL]
            // }
            setValue(bsMsg.xxChkBox_B, ZYPConstant.CHKBOX_ON_Y);
            // END 2018/07/19 K.Kojima [QC#26791,MOD]
        }
    }
    // add end 2016/04/21 CSA Defect#7251

    private void setTierCount(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        int tierCnt = 1;
        BigDecimal parentRowNum = null;
        BigDecimal svcCrRebilMtrBllgPk = null;
        BigDecimal svcInvLineMtrPk = null;
        String mtrLbCd = null;

        String serNumFltAgg = getSerNumForFltAgg(cMsg);

        for (int i = 0; i < sMsg.C.getValidCount(); i++) {

            //setValue(sMsg.C.no(i).xxRowNum_C2, new BigDecimal(i));
            if (!hasValue(sMsg.C.no(i).serNum_C)) {
                setValue(sMsg.C.no(i).xxScrItem40Txt_C, serNumFltAgg);
            } else {
                setValue(sMsg.C.no(i).xxScrItem40Txt_C, sMsg.C.no(i).serNum_C);
            }

            if (cheackSameMeter(svcCrRebilMtrBllgPk, svcInvLineMtrPk, sMsg.C.no(i).svcCrRebilMtrBllgPk_C.getValue(), sMsg.C.no(i).svcInvLineMtrPk_C.getValue())) {

                //setValue(sMsg.C.no(i).xxRowNum_C1, parentRowNum);
                setValue(sMsg.C.no(i).xxDplyCtrlFlg_C1, ZYPConstant.CHKBOX_ON_Y);

                if (sMsg.C.no(i).mtrLbCd_C.getValue().equals(mtrLbCd)) {
                    setValue(sMsg.C.no(i).xxListNum_C, new BigDecimal(tierCnt++));
                    setValue(sMsg.C.no(i).xxDplyCtrlFlg_C2, ZYPConstant.CHKBOX_ON_Y);
                } else {
                    tierCnt = 1;
                    setValue(sMsg.C.no(i).xxListNum_C, new BigDecimal(tierCnt++));
                    sMsg.C.no(i).xxDplyCtrlFlg_C2.clear();
                    mtrLbCd = sMsg.C.no(i).mtrLbCd_C.getValue();
                }
            } else {
                //parentRowNum = getParentRowNum(sMsg, sMsg.C.no(i).svcCrRebilMtrBllgPk_C.getValue(), sMsg.C.no(i).svcInvLineMtrPk_C.getValue());
                tierCnt = 1;

                setValue(sMsg.C.no(i).xxListNum_C, new BigDecimal(tierCnt++));
                sMsg.C.no(i).xxDplyCtrlFlg_C1.clear();
                sMsg.C.no(i).xxDplyCtrlFlg_C2.clear();

                //setValue(sMsg.C.no(i).xxRowNum_C1, parentRowNum);
                svcCrRebilMtrBllgPk = sMsg.C.no(i).svcCrRebilMtrBllgPk_C.getValue();
                svcInvLineMtrPk = sMsg.C.no(i).svcInvLineMtrPk_C.getValue();

                mtrLbCd = sMsg.C.no(i).mtrLbCd_C.getValue();
            }
        }
    }

    private boolean cheackSameMeter(BigDecimal preSvcCrRebilMtrBllgPk, BigDecimal preSvcInvLineMtrPk, BigDecimal svcCrRebilMtrBllgPk, BigDecimal invLineMtrPk) {

        if (preSvcCrRebilMtrBllgPk == null && preSvcInvLineMtrPk == null) {
            return false;
        } else if (preSvcCrRebilMtrBllgPk == null && preSvcInvLineMtrPk.compareTo(invLineMtrPk) == 0) {
            return true;
        } else if (preSvcInvLineMtrPk == null && preSvcCrRebilMtrBllgPk.compareTo(svcCrRebilMtrBllgPk) == 0) {
            return true;
        } else {
            return false;
        }
    }

    private BigDecimal getParentRowNum(NSAL1120SMsg sMsg, BigDecimal svcCrRebilMtrBllgPk, BigDecimal svcInvLineMtrPk) {

        if (svcCrRebilMtrBllgPk == null) {
            for (int i = 0; i < sMsg.B.getValidCount(); i++) {
                if (sMsg.B.no(i).svcInvLineMtrPk_B.getValue().compareTo(svcInvLineMtrPk) == 0) {
                    return sMsg.B.no(i).xxRowNum_B.getValue();
                }
            }
        }
        else {
            for (int i = 0; i < sMsg.B.getValidCount(); i++) {
                if (sMsg.B.no(i).svcCrRebilMtrBllgPk_B.getValue().compareTo(svcCrRebilMtrBllgPk) == 0) {
                    return sMsg.B.no(i).xxRowNum_B.getValue();
                }
            }
        }
        return null;
    }
    private String getSerNumForFltAgg(NSAL1120CMsg cMsg) {

        StringBuilder contrNumBldr = new StringBuilder(cMsg.dsContrCatgAbbrNm.getValue());
        contrNumBldr.append("_");
        contrNumBldr.append(cMsg.dsContrNum.getValue());
        return contrNumBldr.toString();
    }
    // START 2017/10/02 U.Kim [QC#18749, ADD]
    private boolean checkInvMode(NSAL1120CMsg cMsg) {
        
        if (hasValue(cMsg.svcCrRebilPk) && !hasValue(cMsg.svcCrRebilDtlPk)) {
            return true;
        }
        return false;
    }
    private void setSvcCrRebilPk(NSAL1120CMsg cMsg) {
        if (!hasValue(cMsg.svcCrRebilPk) && hasValue(cMsg.svcCrRebilDtlPk)) {
            SVC_CR_REBIL_DTLTMsg tMsg = new SVC_CR_REBIL_DTLTMsg();
            setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
            setValue(tMsg.svcCrRebilDtlPk, cMsg.svcCrRebilDtlPk);
            tMsg = (SVC_CR_REBIL_DTLTMsg) EZDTBLAccessor.findByKey(tMsg);
            if (tMsg != null) {
                setValue(cMsg.svcCrRebilPk, tMsg.svcCrRebilPk);
            }
        }
    }
    // END 2017/10/02 U.Kim [QC#18749, ADD]
}
