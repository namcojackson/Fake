package business.blap.NFAL0280.common;

import static business.blap.NFAL0280.constant.NFAL0280Constant.ZZZM9001E;
import static business.blap.NFAL0280.constant.NFAL0280Constant.DATE_MONTH_MM_END;
import static business.blap.NFAL0280.constant.NFAL0280Constant.DATE_MONTH_MM_START;
import static business.blap.NFAL0280.constant.NFAL0280Constant.DATE_YEAR_YY_END;
import static business.blap.NFAL0280.constant.NFAL0280Constant.DATE_YEAR_YY_START;
import static business.blap.NFAL0280.constant.NFAL0280Constant.DB_PARAM_DATA_LIKE;
import static business.blap.NFAL0280.constant.NFAL0280Constant.PERIOD_MONTH_MMM_END;
import static business.blap.NFAL0280.constant.NFAL0280Constant.PERIOD_MONTH_MMM_START;
import static business.blap.NFAL0280.constant.NFAL0280Constant.PERIOD_YEAR_YY_END;
import static business.blap.NFAL0280.constant.NFAL0280Constant.PERIOD_YEAR_YY_START;
import static business.blap.NFAL0280.constant.NFAL0280Constant.TRX_RSN_CD_BC_X1;
import static business.blap.NFAL0280.constant.NFAL0280Constant.TRX_RSN_CD_BC_X2;
import static business.blap.NFAL0280.constant.NFAL0280Constant.TRX_RSN_CD_BC_X3;
import static business.blap.NFAL0280.constant.NFAL0280Constant.TRX_RSN_CD_MC_X4;
import static business.blap.NFAL0280.constant.NFAL0280Constant.TRX_RSN_CD_MC_X5;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFAL0280.NFAL0280CMsg;
import business.blap.NFAL0280.NFAL0280Query;
import business.db.AJE_ACRL_INTFCTMsg;

import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/01/30   CITS            M.Okamura       Create          QC#62449
 * 2024/02/16   CITS            M.Okamura       Update          QC#62449
 *</pre>
 */
public class NFAL0280CommonLogic extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

    }

    /**
     * setPulldownSvcInvChrgTpCdList
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NFAL0280CMsg
     */
    public static void setPulldownSvcInvChrgTpCdList(String glblCmpyCd, NFAL0280CMsg cMsg) {

        cMsg.svcInvChrgTpCd_A1.clear();
        cMsg.svcInvChrgTpCd_A2.clear();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcInvChrgTpCdBc", SVC_INV_CHRG_TP.BASE_CHARGE);
        param.put("svcInvChrgTpCdMc", SVC_INV_CHRG_TP.METER_CHARGE);
        // Execute
        S21SsmEZDResult result = NFAL0280Query.getInstance().getSvcInvChrgTpCdList(param);
        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMap = NWXC412001.autoCast(result.getResultObject());

            for (int i = 0; i < resultMap.size(); i++) {
                ZYPPulldownValueSetter.insertFirstData((String) resultMap.get(i).get("SVC_INV_CHRG_TP_CD"), cMsg.svcInvChrgTpCd_A2, (String) resultMap.get(i).get("SVC_INV_CHRG_TP_DESC_TXT"), cMsg.svcInvChrgTpDescTxt_A1);
            }
        }
    }

    /**
     * setTargetMonth
     * @param strYYYYMM String
     * @param cMsg NFAL0280CMsg
     */
    public static void setTargetMonth(String strYYYYMM, NFAL0280CMsg cMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.glPerNm_A1, strYYYYMM);
    }

    /**
     * convertGlPeriod
     * @param strYYYYMM String
     * @return String
     */
    public static String convertGlPeriod(String strYYYYMM) {

        String glPeriod = "";
        String strYYYY = strYYYYMM.substring(DATE_YEAR_YY_START, DATE_YEAR_YY_END);
        String monthNm = strYYYYMM.substring(DATE_MONTH_MM_START, DATE_MONTH_MM_END);
        // When 21XX, it won't work...
        strYYYY = "-".concat(strYYYY);
        String strMM = "";

        if ("01".equals(monthNm)) {
            strMM = "JAN";
        } else if ("02".equals(monthNm)) {
            strMM = "FEB";
        } else if ("03".equals(monthNm)) {
            strMM = "MAR";
        } else if ("04".equals(monthNm)) {
            strMM = "APR";
        } else if ("05".equals(monthNm)) {
            strMM = "MAY";
        } else if ("06".equals(monthNm)) {
            strMM = "JUN";
        } else if ("07".equals(monthNm)) {
            strMM = "JUL";
        } else if ("08".equals(monthNm)) {
            strMM = "AUG";
        } else if ("09".equals(monthNm)) {
            strMM = "SEP";
        } else if ("10".equals(monthNm)) {
            strMM = "OCT";
        } else if ("11".equals(monthNm)) {
            strMM = "NOV";
        } else if ("12".equals(monthNm)) {
            strMM = "DEC";
        }

        glPeriod = strMM.concat(strYYYY);

        return glPeriod;
    }

    /**
     * getYYYYMM
     * @param perNm String
     * @return String
     */
    public static String getYYYYMM(String perNm) {

        if (!ZYPCommonFunc.hasValue(perNm)) {
            return null;
        }

        String monthNm = perNm.substring(PERIOD_MONTH_MMM_START, PERIOD_MONTH_MMM_END);
        monthNm = monthNm.toUpperCase(Locale.US);
        // When 21XX, it won't work...
        String yyyy = "20".concat(perNm.substring(PERIOD_YEAR_YY_START, PERIOD_YEAR_YY_END));
        String mm = "";

        if ("JAN".equals(monthNm)) {
            mm = "01";
        } else if ("FEB".equals(monthNm)) {
            mm = "02";
        } else if ("MAR".equals(monthNm)) {
            mm = "03";
        } else if ("APR".equals(monthNm)) {
            mm = "04";
        } else if ("MAY".equals(monthNm)) {
            mm = "05";
        } else if ("JUN".equals(monthNm)) {
            mm = "06";
        } else if ("JUL".equals(monthNm)) {
            mm = "07";
        } else if ("AUG".equals(monthNm)) {
            mm = "08";
        } else if ("SEP".equals(monthNm)) {
            mm = "09";
        } else if ("OCT".equals(monthNm)) {
            mm = "10";
        } else if ("NOV".equals(monthNm)) {
            mm = "11";
        } else if ("DEC".equals(monthNm)) {
            mm = "12";
        }
        return yyyy.concat(mm);
    }

    /**
     * setPulldownTargetDataToBeReversedList
     * @param glblCmpyCd glblCmpyCd
     * @param strYYYYMM String
     * @param cMsg NFAL0280CMsg
     */
    public static void setPulldownTrgtDataToBeRvrsList(String glblCmpyCd, String strYYYYMM, NFAL0280CMsg cMsg) {

        cMsg.xxDtNm_A1.clear();
        cMsg.xxDtNm_A2.clear();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("cMsg", cMsg);
        param.put("glblCmpyCd", glblCmpyCd);
        if (cMsg.svcInvChrgTpCd_A1.getValue().equals(SVC_INV_CHRG_TP.BASE_CHARGE)) {
            param.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.BASE_CHARGE);
            param.put("svcInvChrgTpX1", TRX_RSN_CD_BC_X1);
            param.put("svcInvChrgTpX2", TRX_RSN_CD_BC_X2);
            param.put("svcInvChrgTpX3", TRX_RSN_CD_BC_X3);
        } else {
            param.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.METER_CHARGE);
            param.put("svcInvChrgTpX4", TRX_RSN_CD_MC_X4);
            param.put("svcInvChrgTpX5", TRX_RSN_CD_MC_X5);
        }
        param.put("trgtMth", strYYYYMM.concat(DB_PARAM_DATA_LIKE));

        // Execute
        S21SsmEZDResult result = NFAL0280Query.getInstance().setPulldownTrgtDataToBeRvrsList(param);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMap = NWXC412001.autoCast(result.getResultObject());

            for (int i = 0; i < resultMap.size(); i++) {
                ZYPPulldownValueSetter.insertFirstData((String) resultMap.get(i).get("EZINTIME"), cMsg.xxDtNm_A2, (String) resultMap.get(i).get("EZINTIME"), cMsg.xxDtTm_A1);
            }
        } else {
            cMsg.setMessageInfo(ZZZM9001E);
        }
    }

    /**
     * copyAjeAcrlIntfc
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NFAL0280CMsg
     * @param glDt String
     * @param strYYYYMM String
     */
    public static void copyAjeAcrlIntfc(String glblCmpyCd, NFAL0280CMsg cMsg, String glDt, String strYYYYMM) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("cMsg", cMsg);
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("xxDtNm", cMsg.xxDtNm_A1);
        if (cMsg.svcInvChrgTpCd_A1.getValue().equals(SVC_INV_CHRG_TP.BASE_CHARGE)) {
            param.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.BASE_CHARGE);
            param.put("svcInvChrgTpX1", TRX_RSN_CD_BC_X1);
            param.put("svcInvChrgTpX2", TRX_RSN_CD_BC_X2);
            param.put("svcInvChrgTpX3", TRX_RSN_CD_BC_X3);
        } else {
            param.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.METER_CHARGE);
            param.put("svcInvChrgTpX4", TRX_RSN_CD_MC_X4);
            param.put("svcInvChrgTpX5", TRX_RSN_CD_MC_X5);
        }
        param.put("trgtMth", strYYYYMM.concat(DB_PARAM_DATA_LIKE));
        // Execute Copy
        S21SsmEZDResult result = NFAL0280Query.getInstance().getAjeAcrlIntfcList(param);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultList = NWXC412001.autoCast(result.getResultObject());
            List<AJE_ACRL_INTFCTMsg> listTmsg = new ArrayList<AJE_ACRL_INTFCTMsg>();
            for (Map<String, Object> resultMap : resultList) {
                BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AJE_ACRL_INTFC_SQ);
                AJE_ACRL_INTFCTMsg insertTMsg = new AJE_ACRL_INTFCTMsg();
                ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(insertTMsg.ajeAcrlIntfcPk, seqNum);
                ZYPEZDItemValueSetter.setValue(insertTMsg.glDt, (String) glDt);
                ZYPEZDItemValueSetter.setValue(insertTMsg.sysSrcCd, (String) resultMap.get("SYS_SRC_CD"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.trxCd, (String) resultMap.get("TRX_CD"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.trxRsnCd, (String) resultMap.get("TRX_RSN_CD"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.dealCcyCd, (String) resultMap.get("DEAL_CCY_CD"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.funcCcyCd, (String) resultMap.get("FUNC_CCY_CD"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.exchRate, (BigDecimal) resultMap.get("EXCH_RATE"));

                BigDecimal jrnlDealAmt = new BigDecimal(resultMap.get("JRNL_DEAL_AMT").toString());
                jrnlDealAmt = jrnlDealAmt.negate();
                ZYPEZDItemValueSetter.setValue(insertTMsg.jrnlDealAmt, jrnlDealAmt);
                BigDecimal jrnlFuncAmt = new BigDecimal(resultMap.get("JRNL_FUNC_AMT").toString());
                jrnlFuncAmt = jrnlFuncAmt.negate();
                ZYPEZDItemValueSetter.setValue(insertTMsg.jrnlFuncAmt, jrnlFuncAmt);

                ZYPEZDItemValueSetter.setValue(insertTMsg.coaCmpyCd, (String) resultMap.get("COA_CMPY_CD"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.coaBrCd, (String) resultMap.get("COA_BR_CD"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.coaCcCd, (String) resultMap.get("COA_CC_CD"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.coaAcctCd, (String) resultMap.get("COA_ACCT_CD"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.coaProdCd, (String) resultMap.get("COA_PROD_CD"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.coaChCd, (String) resultMap.get("COA_CH_CD"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.coaAfflCd, (String) resultMap.get("COA_AFFL_CD"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.coaProjCd, (String) resultMap.get("COA_PROJ_CD"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.coaExtnCd, (String) resultMap.get("COA_EXTN_CD"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.slsRepCrPct, (BigDecimal) resultMap.get("SLS_REP_CR_PCT"));

                ZYPEZDItemValueSetter.setValue(insertTMsg.serNum, (String) resultMap.get("SER_NUM"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.svcMachMstrPk, (BigDecimal) resultMap.get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.svcConfigMstrPk, (BigDecimal) resultMap.get("SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrPk, (BigDecimal) resultMap.get("DS_CONTR_PK"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrNum, (String) resultMap.get("DS_CONTR_NUM"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrDtlPk, (BigDecimal) resultMap.get("DS_CONTR_DTL_PK"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNum, (String) resultMap.get("DS_ACCT_NUM"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.tocCd, (String) resultMap.get("TOC_CD"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.ajeItemCd, (String) resultMap.get("AJE_ITEM_CD"));
                ZYPEZDItemValueSetter.setValue(insertTMsg.ajeItemDescTxt, (String) resultMap.get("AJE_ITEM_DESC_TXT"));

                listTmsg.add(insertTMsg);
            }
            if (listTmsg.size() != 0) {
                // process rest of list
                AJE_ACRL_INTFCTMsg[] arryTMsg = listTmsg.toArray(new AJE_ACRL_INTFCTMsg[listTmsg.size()]);
                S21FastTBLAccessor.insert(arryTMsg);
            }
        }
    }

    /**
     * getAjeAcrlIntfcEzInTime
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NFAL0280CMsg
     * @param glDt String
     * @return String
     */
    public static String getAjeAcrlIntfcEzInTime(String glblCmpyCd, NFAL0280CMsg cMsg, String glDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("cMsg", cMsg);
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("xxDtNm", cMsg.xxDtNm_A1);
        if (cMsg.svcInvChrgTpCd_A1.getValue().equals(SVC_INV_CHRG_TP.BASE_CHARGE)) {
            param.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.BASE_CHARGE);
            param.put("svcInvChrgTpX1", TRX_RSN_CD_BC_X1);
            param.put("svcInvChrgTpX2", TRX_RSN_CD_BC_X2);
            param.put("svcInvChrgTpX3", TRX_RSN_CD_BC_X3);
        } else {
            param.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.METER_CHARGE);
            param.put("svcInvChrgTpX4", TRX_RSN_CD_MC_X4);
            param.put("svcInvChrgTpX5", TRX_RSN_CD_MC_X5);
        }
        param.put("trgtMth", glDt);
        // Execute
        S21SsmEZDResult result = NFAL0280Query.getInstance().getAjeAcrlIntfcEzInTime(param);
        return (String) result.getResultObject();
    }

}
