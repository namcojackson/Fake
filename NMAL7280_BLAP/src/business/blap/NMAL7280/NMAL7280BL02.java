/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7280;

import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import business.file.NMAL7280F00FMsg;
import static business.blap.NMAL7280.constant.NMAL7280Constant.CSV_FILE_NAME;
import static business.blap.NMAL7280.constant.NMAL7280Constant.CSV_FILE_EXTENSION;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_OP_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import static business.blap.NMAL7280.constant.NMAL7280Constant.NMAL7280_INIT;
import static business.blap.NMAL7280.constant.NMAL7280Constant.NMAL7280SCRN00_CMN_DOWNLOAD;
import static business.blap.NMAL7280.constant.NMAL7280Constant.NMAL7280SCRN00_CMN_RESET;
import static business.blap.NMAL7280.constant.NMAL7280Constant.NMAL7280SCRN00_CMN_SUBMIT;
import static business.blap.NMAL7280.constant.NMAL7280Constant.NZZM0000E;

/**
 *<pre>
 * NMAL7280BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         M.Suzuki        Create          N/A
 * 2016/11/08   Fujitsu         T.Murai         Update          S21_NA#6599
 *</pre>
 */
public class NMAL7280BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7280CMsg bizMsg = (NMAL7280CMsg) cMsg;

            if (NMAL7280_INIT.equals(screenAplID)) {
                doProcess_NMAL7280_INIT(bizMsg);
            } else if (NMAL7280SCRN00_CMN_DOWNLOAD.equals(screenAplID)) {
                doProcess_NMAL7280Scrn00_CMN_Download(bizMsg);
            } else if (NMAL7280SCRN00_CMN_RESET.equals(screenAplID)) {
                doProcess_NMAL7280_INIT(bizMsg);
            } else if (NMAL7280SCRN00_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NMAL7280_INIT(bizMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7280_INIT(NMAL7280CMsg bizMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);

        // create Pull-Down
        ZYPCodeDataUtil.createPulldownList(PRC_RULE_OP_TP.class, bizMsg.prcRuleOpTpCd_P, bizMsg.prcRuleOpTpDescTxt_P);

        NMAL7280Query query = NMAL7280Query.getInstance();
        S21SsmEZDResult ssmResult = query.getPriceHeader(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }
        Map< ? , ? > rsltMap = (Map< ? , ? >) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleNm, (String) rsltMap.get("PRC_RULE_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime, (String) rsltMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone, (String) rsltMap.get("EZUPTIMEZONE"));

        query.getConditionGroup(bizMsg);

        // START 2016/11/07 [QC#6599, ADD]
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            String cratNm = bizMsg.A.no(i).xxRecHistCratByNm_A.getValue();
            String updNm = bizMsg.A.no(i).xxRecHistUpdByNm_A.getValue();
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistCratByNm_A, ZYPRecHistUtil.getFullNameForRecHist(cratNm));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistUpdByNm_A, ZYPRecHistUtil.getFullNameForRecHist(updNm));
        }
        // END 2016/11/07 [QC#6599, ADD]

        query.getTrxCond(bizMsg);

    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7280Scrn00_CMN_Download(NMAL7280CMsg bizMsg) {

        // create csv file
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV_FILE_EXTENSION);

        NMAL7280F00FMsg csvMsg = new NMAL7280F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), csvMsg);
        csvOutFile.writeHeader(new String[]{
                "Condition Group"
                , "Condition#"
                , "Operator"
                , "Condition#"
                , "Operator"
                , "Condition#"
                , "Operator"
                , "Condition#"
                , "Operator"
                , "Condition#"
                , "Operator"
                , "Condition#"
                , "Operator"
                , "Condition#"
                , "Operator"
                , "Condition#"
                , "Operator"
                , "Condition#"
                , "Operator"
                , "Condition#"
                , "Operator"
                , "Condition#"
                , "Operator"
                , "Condition#"
                , "Effective Date From"
                , "Effective Date To"});

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            csvMsg.clear();
            EZDMsg.copy(bizMsg.A.no(i), null, csvMsg, null);

            ZYPEZDItemValueSetter.setValue(csvMsg.prcRuleOpTpDescTxt_A1, ZYPCodeDataUtil.getName(PRC_RULE_OP_TP.class, getGlobalCompanyCode(), bizMsg.A.no(i).prcRuleOpTpCd_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(csvMsg.prcRuleOpTpDescTxt_A2, ZYPCodeDataUtil.getName(PRC_RULE_OP_TP.class, getGlobalCompanyCode(), bizMsg.A.no(i).prcRuleOpTpCd_A2.getValue()));
            ZYPEZDItemValueSetter.setValue(csvMsg.prcRuleOpTpDescTxt_A3, ZYPCodeDataUtil.getName(PRC_RULE_OP_TP.class, getGlobalCompanyCode(), bizMsg.A.no(i).prcRuleOpTpCd_A3.getValue()));
            ZYPEZDItemValueSetter.setValue(csvMsg.prcRuleOpTpDescTxt_A4, ZYPCodeDataUtil.getName(PRC_RULE_OP_TP.class, getGlobalCompanyCode(), bizMsg.A.no(i).prcRuleOpTpCd_A4.getValue()));
            ZYPEZDItemValueSetter.setValue(csvMsg.prcRuleOpTpDescTxt_A5, ZYPCodeDataUtil.getName(PRC_RULE_OP_TP.class, getGlobalCompanyCode(), bizMsg.A.no(i).prcRuleOpTpCd_A5.getValue()));
            ZYPEZDItemValueSetter.setValue(csvMsg.prcRuleOpTpDescTxt_A6, ZYPCodeDataUtil.getName(PRC_RULE_OP_TP.class, getGlobalCompanyCode(), bizMsg.A.no(i).prcRuleOpTpCd_A6.getValue()));
            ZYPEZDItemValueSetter.setValue(csvMsg.prcRuleOpTpDescTxt_A7, ZYPCodeDataUtil.getName(PRC_RULE_OP_TP.class, getGlobalCompanyCode(), bizMsg.A.no(i).prcRuleOpTpCd_A7.getValue()));
            ZYPEZDItemValueSetter.setValue(csvMsg.prcRuleOpTpDescTxt_A8, ZYPCodeDataUtil.getName(PRC_RULE_OP_TP.class, getGlobalCompanyCode(), bizMsg.A.no(i).prcRuleOpTpCd_A8.getValue()));
            ZYPEZDItemValueSetter.setValue(csvMsg.prcRuleOpTpDescTxt_A9, ZYPCodeDataUtil.getName(PRC_RULE_OP_TP.class, getGlobalCompanyCode(), bizMsg.A.no(i).prcRuleOpTpCd_A9.getValue()));
            ZYPEZDItemValueSetter.setValue(csvMsg.prcRuleOpTpDescTxt_AA, ZYPCodeDataUtil.getName(PRC_RULE_OP_TP.class, getGlobalCompanyCode(), bizMsg.A.no(i).prcRuleOpTpCd_AA.getValue()));
            ZYPEZDItemValueSetter.setValue(csvMsg.prcRuleOpTpDescTxt_AB, ZYPCodeDataUtil.getName(PRC_RULE_OP_TP.class, getGlobalCompanyCode(), bizMsg.A.no(i).prcRuleOpTpCd_AB.getValue()));

            csvOutFile.write();
        }
        csvOutFile.close();
    }

}
