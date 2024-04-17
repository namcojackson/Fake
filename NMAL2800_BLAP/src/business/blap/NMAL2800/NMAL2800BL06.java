/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2800;

import static business.blap.NMAL2800.constant.NMAL2800Constant.BIZ_ID;
import static business.blap.NMAL2800.constant.NMAL2800Constant.CSV_FILE;
import static business.blap.NMAL2800.constant.NMAL2800Constant.DEF_DS_ACCT_CLS_CD;
import static business.blap.NMAL2800.constant.NMAL2800Constant.DEF_DS_ACCT_ITRL_FLG;
import static business.blap.NMAL2800.constant.NMAL2800Constant.LOC_NUM;
import static business.blap.NMAL2800.constant.NMAL2800Constant.MAIL_TEMPLETE_ID;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ML_BEF_DS_ACCT_NM;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ML_BEF_PSN_NUM;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ML_DS_ACCT_NUM;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ML_EML_ADDR;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ML_HDR;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ML_LOC_NUM;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ML_PSN_NM;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ML_PSN_NUM;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ML_ROW;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ML_RVW_PROS_NUM;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ML_SLS_REP_NM;
import static business.blap.NMAL2800.constant.NMAL2800Constant.MODE_CD_SRCH;
import static business.blap.NMAL2800.constant.NMAL2800Constant.MODE_CD_UPL;
import static business.blap.NMAL2800.constant.NMAL2800Constant.NMAM0166E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.NMAM0182E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.NMAM8333I;
import static business.blap.NMAL2800.constant.NMAL2800Constant.NMAM8461E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.NMAM8501E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.NMAM8545E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.NMAM8558W;
import static business.blap.NMAL2800.constant.NMAL2800Constant.NMAM8572E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.NMAM8677E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.NZZM0002I;
import static business.blap.NMAL2800.constant.NMAL2800Constant.PROS_ACCT;
import static business.blap.NMAL2800.constant.NMAL2800Constant.PROS_NUM;
import static business.blap.NMAL2800.constant.NMAL2800Constant.SPLIT_VAL_COMMA;
import static business.blap.NMAL2800.constant.NMAL2800Constant.STR_LEN_15;
import static business.blap.NMAL2800.constant.NMAL2800Constant.STR_LEN_18;
import static business.blap.NMAL2800.constant.NMAL2800Constant.STR_LEN_20;
import static business.blap.NMAL2800.constant.NMAL2800Constant.TBL_DS_ACCT_RVW_PROS;
import static business.blap.NMAL2800.constant.NMAL2800Constant.TRTY_ASG_RESRC;
import static business.blap.NMAL2800.constant.NMAL2800Constant.TRTY_NM;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ZYEM0004E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ZZM9000E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ZZZM9004E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ZZZM9006E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ZZZM9012E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ZZZM9013E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDSMsg;
import business.blap.NMAL2800.common.NMAL2800CommonLogic;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.DEF_DPLY_COA_INFOTMsgArray;
import business.db.DS_ACCT_RVW_PROSTMsg;
import business.db.DS_ACCT_RVW_PROSTMsgArray;
import business.db.TRTY_RULETMsg;
import business.file.NMAL2800F01FMsg;
import business.parts.NMZC001001PMsg;
import business.parts.NMZC001002PMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC001001.NMZC001001;
import com.canon.cusa.s21.api.NMZ.NMZC001001.constant.NMZC001001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROS_RVW_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_LOGIC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_OPRD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL2800BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 * 2016/07/26   Fujitsu         N.Sugiura       Update          QC#12394, QC#12417
 * 2016/10/03   Hitachi         T.Mizuki        Update          QC#12418
 * 2016/11/21   fujitsu         R.Nakamura      Update          QC#16082
 * 2017/03/15   Fujitsu         R.Nakamura      Update          QC#15878
 * 2017/06/14   Hitachi         E.Kameishi      Update          QC#18597
 * 2017/06/16   Hitachi         E.Kameishi      Update          QC#18868
 * 2017/06/20   Hitachi         E.Kameishi      Update          QC#18129
 * 2017/10/16   Fujitsu         M.Ohno          Update          QC#21786
 * 2017/10/17   Fujitsu         M.Ohno          Update          QC#21782
 * 2017/10/23   Fujitsu         M.Ohno          Update          QC#21806
 * 2017/10/23   Fujitsu         M.Ohno          Update          QC#21961
 * 2017/12/14   Fujitsu         N.Sugiura       Update          QC#22244
 * 2018/01/16   Fujitsu         Hd.Sugawara     Update          QC#23148
 * 2018/03/02   Fujitsu         Hd.Sugawara     Update          QC#23148-1
 * 2018/03/28   Fujitsu         R.Nakamura      Update          QC#23145
 * 2018/03/29   Fujitsu         R.Nakamura      Update          QC#23141
 *</pre>
 */
public class NMAL2800BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2800CMsg bizMsg = (NMAL2800CMsg) cMsg;
            NMAL2800SMsg glblMsg = (NMAL2800SMsg) sMsg;

            if ("NMAL2800Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL2800Scrn00_Delete_Search".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_Delete_Search(bizMsg, glblMsg);

            } else if ("NMAL2800Scrn00_Save_Search".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_Save_Search(bizMsg, glblMsg);

            } else if ("NMAL2800Scrn00_Upload".equals(screenAplID)) {
                doProcess_NMAL2800Scrn00_Upload(bizMsg, glblMsg);

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
    private void doProcess_NMAL2800Scrn00_CMN_Submit(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        NMAL2800CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        if (MODE_CD_SRCH.equals(bizMsg.xxTpCd_H1.getValue()) && !checkChangeValue(glblMsg)) {
            bizMsg.setMessageInfo(NMAM8333I);
            return;
        }

        boolean errFlg = false;
        String aftTrtyOrgCd = "";
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NMAL2800_ASMsg aSMsg = glblMsg.A.no(i);
            // START 2017/06/16 E.Kameishi [QC#18868,MOD]
            // 2016/07/26 QC#12417 Add Start
            if (ZYPCommonFunc.hasValue(aSMsg.trtyOrgNm_A1) && !aSMsg.trtyOrgNm_A1.getValue().equals(glblMsg.B.no(i).trtyOrgNm_A1.getValue())) {
                aftTrtyOrgCd = NMAL2800CommonLogic.existTrtyOrgCd(aSMsg.trtyOrgNm_A1.getValue());
                if (!ZYPCommonFunc.hasValue(aftTrtyOrgCd)) {
                    aSMsg.trtyOrgNm_A1.setErrorInfo(1, ZZZM9006E, new String[] {TRTY_NM });
                    errFlg = true;
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(aSMsg.aftTrtyOrgCd_A1, aftTrtyOrgCd);
            }
            // END 2017/06/16 E.Kameishi [QC#18868,MOD]
            // Add Start 2018/03/29 QC#23141
            if (PROS_RVW_STS.U.equals(aSMsg.prosRvwStsCd_A1.getValue()) && !ZYPCommonFunc.hasValue(aSMsg.trtyOrgNm_A1)) {
                if (!ZYPCommonFunc.hasValue(aSMsg.candiTrtyNm_A1)) {
                    aSMsg.trtyOrgNm_A1.setErrorInfo(1, ZZZM9006E, new String[] {TRTY_NM });
                    errFlg = true;
                    continue;
                }
                String[] trtyCandiList = aSMsg.candiTrtyNm_A1.getValue().split(SPLIT_VAL_COMMA);
                if (trtyCandiList.length > 1) {
                    aSMsg.trtyOrgNm_A1.setErrorInfo(1, NMAM8677E);
                    errFlg = true;
                    continue;
                } else {
                    ZYPEZDItemValueSetter.setValue(aSMsg.trtyOrgNm_A1, aSMsg.candiTrtyNm_A1);
                }
            }
            // Add End 2018/03/29 QC#23141
            // START 2017/06/20 E.Kameishi [QC#18129,ADD]
            if (PROS_RVW_STS.A.equals(aSMsg.prosRvwStsCd_A1.getValue()) && ZYPCommonFunc.hasValue(aSMsg.trtyOrgNm_A1)) {
                S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().getSalesRep(aSMsg.aftTrtyOrgCd_A1.getValue());
                if (!ssmResult.isCodeNormal()) {
                    aSMsg.trtyOrgNm_A1.setErrorInfo(1, NMAM8545E);
                    errFlg = true;
                    continue;
                }
            }
            // END 2017/06/20 E.Kameishi [QC#18129,ADD]
            // 2016/07/26 QC#12417 Add End
            if (aSMsg.prosRvwStsCd_A1.isError() || aSMsg.trtyOrgNm_A1.isError() || aSMsg.aftDsXrefAcctCd_A1.isError()) {
                errFlg = true;
                continue;
            }

            if (MODE_CD_SRCH.equals(bizMsg.xxTpCd_H1.getValue()) && !ZYPConstant.FLG_ON_Y.equals(aSMsg.xxPgFlg_A1.getValue())) {
                continue;
            } else if (MODE_CD_UPL.equals(bizMsg.xxTpCd_H1.getValue())) {
                ZYPEZDItemValueSetter.setValue(aSMsg.xxPgFlg_A1, ZYPConstant.FLG_ON_Y);
            }
            // 2016/12/14 QC#22244 Mod Start
            // 2016/07/26 QC#12417 Mod Start
            if (PROS_RVW_STS.U.equals(aSMsg.prosRvwStsCd_A1.getValue()) 
                    || (PROS_RVW_STS.D.equals(aSMsg.prosRvwStsCd_A1.getValue()) && ZYPCommonFunc.hasValue(aSMsg.aftLocNum_A1))
                    || (PROS_RVW_STS.D.equals(aSMsg.prosRvwStsCd_A1.getValue()) && ZYPCommonFunc.hasValue(aSMsg.aftDsXrefAcctCd_A1))) {
                if (PROS_RVW_STS.D.equals(aSMsg.prosRvwStsCd_A1.getValue()) && ZYPCommonFunc.hasValue(aSMsg.aftLocNum_A1) && !NMAL2800CommonLogic.existLocNum(aSMsg.aftLocNum_A1.getValue())) {
                    aSMsg.aftLocNum_A1.setErrorInfo(1, ZZZM9006E, new String[] {LOC_NUM });
                    errFlg = true;
                    continue;
                }
                if (PROS_RVW_STS.D.equals(aSMsg.prosRvwStsCd_A1.getValue()) && ZYPCommonFunc.hasValue(aSMsg.aftDsXrefAcctCd_A1) && !NMAL2800CommonLogic.existRvwProsNum(aSMsg.aftDsXrefAcctCd_A1.getValue())) {
                    aSMsg.aftDsXrefAcctCd_A1.setErrorInfo(1, ZZZM9006E, new String[] {PROS_NUM });
                    errFlg = true;
                    continue;
                }
                if (!callCustUpdateApi(aSMsg)) {
                    errFlg = true;
                    continue;
                }
            }
            // 2016/07/26 QC#12417 Mod End
            // 2016/12/14 QC#22244 Add End

            if (!updateDsAcctRvwPros(bizMsg, aSMsg)) {
                errFlg = true;
                continue;
            }

            // 2017/10/23 QC#21806 del start
//            if (PROS_RVW_STS.D.equals(aSMsg.prosRvwStsCd_A1.getValue()) && ZYPCommonFunc.hasValue(aSMsg.aftDsXrefAcctCd_A1)) {
//                if (!updateMergeToPros(aSMsg)) {
//                    errFlg = true;
//                    continue;
//                }
//            }
            // 2017/10/23 QC#21806 del end
            // 2016/07/26 QC#12417 Mod Start
            if (PROS_RVW_STS.U.equals(aSMsg.prosRvwStsCd_A1.getValue()) && ZYPCommonFunc.hasValue(aSMsg.trtyOrgNm_A1)) {
            // 2016/07/26 QC#12417 Mod End
                if (!insertTrtyRule(aSMsg)) {
                    errFlg = true;
                    continue;
                }
            }
        }

        if (errFlg || !postMail(glblMsg)) {
            errFlg = true;
        }

        if (errFlg) {
            NMAL2800CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
            // Del Start 2018/03/02 QC#23148-1
        //}
            // Del End 2018/03/02 QC#23148-1
        NMAL2800CommonLogic.createStatePulldownList(bizMsg);
            // Add Start 2018/03/02 QC#23148-1
        } else {
            NMAL2800CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
        // Add End 2018/03/02 QC#23148-1
    }

    /**
     * Delete_Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_Delete_Search(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        // set API parameter
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        // call Search Option API
        if (NMAL2800CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
            NMAL2800CommonLogic.createPulldownListSaveOpt(bizMsg, getContextUserInfo().getUserId(), glblCmpyCd);
            bizMsg.srchOptPk.clear();
            bizMsg.setMessageInfo(NZZM0002I);
        }
    }

    /**
     * Save_Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_Save_Search(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        if (NMAL2800CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm.setErrorInfo(1, NMAM0182E, new String[] {"Save", "Search Option Name" });
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        String userId = getContextUserInfo().getUserId();

        // set search option value to API parameter
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm) && NMAL2800CommonLogic.isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm);
        } else {
            NMAL2800CommonLogic.setSelectSaveSearchName(bizMsg, pMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, userId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.xxTpCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.lineBizTpCd_H1);
        if (ZYPCommonFunc.hasValue(bizMsg.xxDt10Dt_H1)) {
            if (ZYPDateUtil.isValidDate(bizMsg.xxDt10Dt_H1.getValue(), "yyyyMMdd")) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.xxDt10Dt_H1.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxDt10Dt_H2)) {
            if (ZYPDateUtil.isValidDate(bizMsg.xxDt10Dt_H2.getValue(), "yyyyMMdd")) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.xxDt10Dt_H2.getValue());
            }
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.befDsAcctNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.rvwProsNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.xxScrItem61Txt_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.befPsnNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.orgNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.prosRvwStsCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.dupAcctLocFlg_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.shipAddrAllTxt_H1);
        // 2017/10/17 QC#21782 mod start
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.befShipToCtyAddr_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.befShipToStNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.befShipToPostCd_H1);
        // 2017/10/17 QC#21782 mod end

        // call Search Option API
        if (NMAL2800CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
            NMAL2800CommonLogic.createPulldownListSaveOpt(bizMsg, userId, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk, pMsg.srchOptPk);
            bizMsg.srchOptNm.clear();
            bizMsg.setMessageInfo(NZZM0002I);
        }
    }

    /**
     * Upload Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2800Scrn00_Upload(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);

        String path = bizMsg.xxFileData_U.getTempFilePath();
        if (path == null || path.isEmpty()) {
            bizMsg.xxFileData_U.setErrorInfo(1, ZZM9000E, new String[] {CSV_FILE });
            return;
        }

        NMAL2800F01FMsg fMsg = new NMAL2800F01FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        // Mod Start 2016/11/21 QC#16082
        String csvPath = ZYPExcelUtil.excelToCsvFile(path);
//        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);]
        EZDCSVInFile mappedFile = new EZDCSVInFile(csvPath, fMsg, option);
        // Mod End 2016/11/21 QC#16082

        try {
            NMAL2800CommonLogic.readCsvHeader_UPLOAD(bizMsg, fMsg, mappedFile);
            int status = -1;
            int count = 0;
            boolean hasError = false;

            // load upload data to SMsg
            while ((status = mappedFile.read()) != 1) {
                count++;

                if (!NMAL2800CommonLogic.validateAndCopyToGlblMsg_UPLOAD(status, count, glblMsg.A, fMsg, bizMsg, getGlobalCompanyCode())) {
                    hasError = true;
                }
                fMsg.clear();
            }

            // return if no record inputed
            if (count == 0) {
                bizMsg.setMessageInfo(ZYEM0004E);
                hasError = true;
            }

            if (hasError) {
                return;
            }
            glblMsg.A.setValidCount(count);

        } finally {
            NMAL2800CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
            bizMsg.setCommitSMsg(true);
            // Add Start 2016/11/22 QC#16082
            ZYPExcelUtil.deleteFile(csvPath);
            // Add End 2016/11/22 QC#16082
            // Add Start 2018/01/16 QC#23148
            NMAL2800CommonLogic.createStatePulldownList(bizMsg);
            // Add End 2018/01/16 QC#23148
        }

    }

    private boolean checkChangeValue(NMAL2800SMsg glblMsg) {

        boolean changeFlg = false;

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NMAL2800_ASMsg aSMsg = glblMsg.A.no(i);
            NMAL2800_BSMsg bSMsg = glblMsg.B.no(i);

            if (aSMsg.prosRvwStsCd_A1.isError() || aSMsg.trtyOrgNm_A1.isError() || aSMsg.aftDsXrefAcctCd_A1.isError()) {
                continue;
            }

            if (!aSMsg.prosRvwStsCd_A1.getValue().equals(bSMsg.prosRvwStsCd_A1.getValue()) //
                    || !aSMsg.trtyOrgNm_A1.getValue().equals(bSMsg.trtyOrgNm_A1.getValue()) //
                    || !aSMsg.aftLocNum_A1.getValue().equals(bSMsg.aftLocNum_A1.getValue()) //
                    || !aSMsg.aftDsXrefAcctCd_A1.getValue().equals(bSMsg.aftDsXrefAcctCd_A1.getValue()) //
                    || !aSMsg.aftLocFirstLineAddr_A1.getValue().equals(bSMsg.aftLocFirstLineAddr_A1.getValue()) //
                    || !aSMsg.aftLocScdLineAddr_A1.getValue().equals(bSMsg.aftLocScdLineAddr_A1.getValue()) //
                    || !aSMsg.aftLocThirdLineAddr_A1.getValue().equals(bSMsg.aftLocThirdLineAddr_A1.getValue()) //
                    || !aSMsg.aftLocFrthLineAddr_A1.getValue().equals(bSMsg.aftLocFrthLineAddr_A1.getValue()) //
                    || !aSMsg.aftLocCtyAddr_A1.getValue().equals(bSMsg.aftLocCtyAddr_A1.getValue()) //
                    || !aSMsg.aftLocStCd_A1.getValue().equals(bSMsg.aftLocStCd_A1.getValue()) //
                    || !aSMsg.aftLocPostCd_A1.getValue().equals(bSMsg.aftLocPostCd_A1.getValue()) //
                    || !aSMsg.cntyNm_A1.getValue().equals(bSMsg.cntyNm_A1.getValue()) //
                    || !aSMsg.aftTelNum_A1.getValue().equals(bSMsg.aftTelNum_A1.getValue()) //
                    || !aSMsg.aftFaxNum_A1.getValue().equals(bSMsg.aftFaxNum_A1.getValue()) //
                    || !aSMsg.dsAcctUrl_A1.getValue().equals(bSMsg.dsAcctUrl_A1.getValue()) //
                    || !aSMsg.aftDsAcctDunsNm_A1.getValue().equals(bSMsg.aftDsAcctDunsNm_A1.getValue()) //
                    || (ZYPCommonFunc.hasValue(aSMsg.aftDsLocRevAmt_A1) && !ZYPCommonFunc.hasValue(bSMsg.aftDsLocRevAmt_A1)) //
                    || (!ZYPCommonFunc.hasValue(aSMsg.aftDsLocRevAmt_A1) && ZYPCommonFunc.hasValue(bSMsg.aftDsLocRevAmt_A1)) //
                    || (ZYPCommonFunc.hasValue(aSMsg.aftDsLocRevAmt_A1) && ZYPCommonFunc.hasValue(bSMsg.aftDsLocRevAmt_A1) //
                    && aSMsg.aftDsLocRevAmt_A1.getValue().compareTo(bSMsg.aftDsLocRevAmt_A1.getValue()) != 0) //
                    || !aSMsg.aftDunsNum_A1.getValue().equals(bSMsg.aftDunsNum_A1.getValue()) //
                    || !aSMsg.aftDsCustSicDescTxt_A1.getValue().equals(bSMsg.aftDsCustSicDescTxt_A1.getValue()) //
                    || (ZYPCommonFunc.hasValue(aSMsg.aftDsLocEmpNum_A1) && !ZYPCommonFunc.hasValue(bSMsg.aftDsLocEmpNum_A1)) //
                    || (!ZYPCommonFunc.hasValue(aSMsg.aftDsLocEmpNum_A1) && ZYPCommonFunc.hasValue(bSMsg.aftDsLocEmpNum_A1)) //
                    || (ZYPCommonFunc.hasValue(aSMsg.aftDsLocEmpNum_A1) && ZYPCommonFunc.hasValue(bSMsg.aftDsLocEmpNum_A1) //
                    && aSMsg.aftDsLocEmpNum_A1.getValue().compareTo(bSMsg.aftDsLocEmpNum_A1.getValue()) != 0) //
                    || !aSMsg.aftDsCustSicCd_A1.getValue().equals(bSMsg.aftDsCustSicCd_A1.getValue()) //
                    || !aSMsg.aftDsUltDunsNum_A1.getValue().equals(bSMsg.aftDsUltDunsNum_A1.getValue()) //
                    // Del Start 2018/03/28 QC#23145
//                    || (ZYPCommonFunc.hasValue(aSMsg.aftGlnNum_A1) && !ZYPCommonFunc.hasValue(bSMsg.aftGlnNum_A1)) //
//                    || (!ZYPCommonFunc.hasValue(aSMsg.aftGlnNum_A1) && ZYPCommonFunc.hasValue(bSMsg.aftGlnNum_A1)) //
//                    || (ZYPCommonFunc.hasValue(aSMsg.aftGlnNum_A1) && ZYPCommonFunc.hasValue(bSMsg.aftGlnNum_A1) //
//                    && aSMsg.aftGlnNum_A1.getValue().compareTo(bSMsg.aftGlnNum_A1.getValue()) != 0) //
                    // Del End 2018/03/28 QC#23145
                    || !aSMsg.aftDsPrntDunsNum_A1.getValue().equals(bSMsg.aftDsPrntDunsNum_A1.getValue()) //
                    || !aSMsg.aftHqDunsNum_A1.getValue().equals(bSMsg.aftHqDunsNum_A1.getValue()) //
                    || !aSMsg.aftDsCmpySicCd_A1.getValue().equals(bSMsg.aftDsCmpySicCd_A1.getValue()) //
                    || !aSMsg.aftDsCmpySicDescTxt_A1.getValue().equals(bSMsg.aftDsCmpySicDescTxt_A1.getValue())) {
                changeFlg = true;
                aSMsg.xxPgFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
            }
        }

        return changeFlg;
    }

    private boolean updateDsAcctRvwPros(NMAL2800CMsg bizMsg, NMAL2800_ASMsg aSMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        DS_ACCT_RVW_PROSTMsg dsAcctRvwProsTMsg = new DS_ACCT_RVW_PROSTMsg();

        // get data from DS_ACCT_RVW_PROS
        if (MODE_CD_SRCH.equals(bizMsg.xxTpCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.dsAcctRvwProsPk, aSMsg.dsAcctRvwProsPk_A1);
            dsAcctRvwProsTMsg = (DS_ACCT_RVW_PROSTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsAcctRvwProsTMsg);
            if (dsAcctRvwProsTMsg == null) {
                aSMsg.prosRvwStsCd_A1.setErrorInfo(1, ZZZM9006E, new String[] {TBL_DS_ACCT_RVW_PROS });
                return false;
            }
            if (!ZYPDateUtil.isSameTimeStamp(aSMsg.ezUpTime_A1.getValue(), aSMsg.ezUpTimeZone_A1.getValue(), dsAcctRvwProsTMsg.ezUpTime.getValue(), dsAcctRvwProsTMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(ZZZM9004E);
                return false;
            }
        } else {
            DS_ACCT_RVW_PROSTMsgArray dsAcctRvwProsTMsgArray = NMAL2800CommonLogic.getDsAcctRvwPros(glblCmpyCd, aSMsg.rvwProsNum_A1.getValue());
            if (dsAcctRvwProsTMsgArray != null && dsAcctRvwProsTMsgArray.getValidCount() == 1) {
                dsAcctRvwProsTMsg = (DS_ACCT_RVW_PROSTMsg) dsAcctRvwProsTMsgArray.get(0);
            } else {
                aSMsg.prosRvwStsCd_A1.setErrorInfo(1, ZZZM9006E, new String[] {TBL_DS_ACCT_RVW_PROS });
                return false;
            }
        }

        // set DS_ACCT_RVW_PROS value
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.prosRvwStsCd, aSMsg.prosRvwStsCd_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftTrtyOrgCd, aSMsg.aftTrtyOrgCd_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.trtyOrgNm, aSMsg.trtyOrgNm_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftLocNum, aSMsg.aftLocNum_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsXrefAcctCd, aSMsg.aftDsXrefAcctCd_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftLocFirstLineAddr, aSMsg.aftLocFirstLineAddr_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftLocScdLineAddr, aSMsg.aftLocScdLineAddr_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftLocThirdLineAddr, aSMsg.aftLocThirdLineAddr_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftLocFrthLineAddr, aSMsg.aftLocFrthLineAddr_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftLocCtyAddr, aSMsg.aftLocCtyAddr_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftLocStCd, aSMsg.aftLocStCd_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftLocPostCd, aSMsg.aftLocPostCd_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftTelNum, aSMsg.aftTelNum_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftFaxNum, aSMsg.aftFaxNum_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.dsAcctUrl, aSMsg.dsAcctUrl_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsAcctDunsNm, aSMsg.aftDsAcctDunsNm_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsLocRevAmt, aSMsg.aftDsLocRevAmt_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDunsNum, aSMsg.aftDunsNum_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsCustSicDescTxt, aSMsg.aftDsCustSicDescTxt_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsLocEmpNum, aSMsg.aftDsLocEmpNum_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsCustSicCd, aSMsg.aftDsCustSicCd_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsUltDunsNum, aSMsg.aftDsUltDunsNum_A1);
        // Del Start 2018/03/28 QC#23145
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftGlnNum, aSMsg.aftGlnNum_A1);
        // Del End 2018/03/28 QC#23145
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsPrntDunsNum, aSMsg.aftDsPrntDunsNum_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftHqDunsNum, aSMsg.aftHqDunsNum_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsCmpySicCd, aSMsg.aftDsCmpySicCd_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsCmpySicDescTxt, aSMsg.aftDsCmpySicDescTxt_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.dsXrefAcctTpCd, aSMsg.dsXrefAcctTpCd_A1);

        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.dsAcctNum, aSMsg.dsAcctNum_A1);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.locNum, aSMsg.locNum_A1);

        // Mod Start 2018/03/02 QC#23148-1
        //if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxTpCd_H1.getValue())) {
        if (MODE_CD_UPL.equals(bizMsg.xxTpCd_H1.getValue())) {
            // Mod End 2018/03/02 QC#23148-1
            ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsAcctNm, aSMsg.aftDsAcctNm_A1);
            // Del Start 2018/03/02 QC#23148-1
            //ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsXrefAcctCd, aSMsg.aftDsXrefAcctCd_A1);
            // Del End 2018/03/02 QC#23148-1
        }

        // get County PK
        BigDecimal cntyPk = NMAL2800CommonLogic.getCntyPk(glblCmpyCd, aSMsg);
        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftLocCntyPk, cntyPk);

        // check if Territory Tracker exists
        if (PROS_RVW_STS.A.equals(aSMsg.prosRvwStsCd_A1.getValue())) {
            // START 2017/06/14 E.Kameishi [QC#18597,MOD]
            String flg = ZYPConstant.FLG_ON_Y;
            if (NMAL2800CommonLogic.existTrtyTrkTrtyList(glblCmpyCd, aSMsg.aftTrtyOrgCd_A1.getValue())) {
                flg = ZYPConstant.FLG_OFF_N;
            }
            // END 2017/06/14 E.Kameishi [QC#18597,MOD]
            ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.trtyTrkHldFlg, flg);
        }

        S21FastTBLAccessor.update(dsAcctRvwProsTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsAcctRvwProsTMsg.getReturnCode())) {
            aSMsg.prosRvwStsCd_A1.setErrorInfo(1, ZZZM9013E, new String[] {dsAcctRvwProsTMsg.getReturnCode() });
            return false;
        }

        return true;
    }

//    private boolean updateMergeToPros(NMAL2800_ASMsg aSMsg) {
//
//        String glblCmpyCd = getGlobalCompanyCode();
//
//        DS_ACCT_RVW_PROSTMsg dsAcctRvwProsTMsg = new DS_ACCT_RVW_PROSTMsg();
//
//        // get data from DS_ACCT_RVW_PROS
//        DS_ACCT_RVW_PROSTMsgArray dsAcctRvwProsTMsgArray = NMAL2800CommonLogic.getDsAcctRvwPros(glblCmpyCd, aSMsg.aftDsXrefAcctCd_A1.getValue());
//        if (dsAcctRvwProsTMsgArray != null && dsAcctRvwProsTMsgArray.getValidCount() == 1) {
//            dsAcctRvwProsTMsg = (DS_ACCT_RVW_PROSTMsg) dsAcctRvwProsTMsgArray.get(0);
//        } else {
//            aSMsg.aftDsXrefAcctCd_A1.setErrorInfo(1, ZZZM9006E, new String[] {PROS_ACCT });
//            return false;
//        }
//
//        // set DS_ACCT_RVW_PROS value
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftLocFirstLineAddr, aSMsg.aftLocFirstLineAddr_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftLocScdLineAddr, aSMsg.aftLocScdLineAddr_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftLocThirdLineAddr, aSMsg.aftLocThirdLineAddr_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftLocFrthLineAddr, aSMsg.aftLocFrthLineAddr_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftLocCtyAddr, aSMsg.aftLocCtyAddr_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftLocStCd, aSMsg.aftLocStCd_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftLocPostCd, aSMsg.aftLocPostCd_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftTelNum, aSMsg.aftTelNum_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftFaxNum, aSMsg.aftFaxNum_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.dsAcctUrl, aSMsg.dsAcctUrl_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsAcctDunsNm, aSMsg.aftDsAcctDunsNm_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsLocRevAmt, aSMsg.aftDsLocRevAmt_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDunsNum, aSMsg.aftDunsNum_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsCustSicDescTxt, aSMsg.aftDsCustSicDescTxt_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsLocEmpNum, aSMsg.aftDsLocEmpNum_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsCustSicCd, aSMsg.aftDsCustSicCd_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsUltDunsNum, aSMsg.aftDsUltDunsNum_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftGlnNum, aSMsg.aftGlnNum_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsPrntDunsNum, aSMsg.aftDsPrntDunsNum_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftHqDunsNum, aSMsg.aftHqDunsNum_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsCmpySicCd, aSMsg.aftDsCmpySicCd_A1);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftDsCmpySicDescTxt, aSMsg.aftDsCmpySicDescTxt_A1);
//
//        // get County PK
//        BigDecimal cntyPk = NMAL2800CommonLogic.getCntyPk(glblCmpyCd, aSMsg);
//        ZYPEZDItemValueSetter.setValue(dsAcctRvwProsTMsg.aftLocCntyPk, cntyPk);
//
//        S21FastTBLAccessor.update(dsAcctRvwProsTMsg);
//        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsAcctRvwProsTMsg.getReturnCode())) {
//            aSMsg.prosRvwStsCd_A1.setErrorInfo(1, ZZZM9013E, new String[] {dsAcctRvwProsTMsg.getReturnCode() });
//            return false;
//        }
//
//        return true;
//    }

    private Map<String, String> getUpdateFlg(NMAL2800_ASMsg aSMsg) {

        Map<String, String> map = new HashMap<String, String>();

        // Mod Start 2018/03/28 QC#23145
        if (ZYPCommonFunc.hasValue(aSMsg.aftLocFirstLineAddr_AH) //
                || ZYPCommonFunc.hasValue(aSMsg.aftLocScdLineAddr_AH) //
                || ZYPCommonFunc.hasValue(aSMsg.aftLocThirdLineAddr_AH) //
                || ZYPCommonFunc.hasValue(aSMsg.aftLocFrthLineAddr_AH) //
                || ZYPCommonFunc.hasValue(aSMsg.aftLocCtyAddr_AH) //
                || ZYPCommonFunc.hasValue(aSMsg.aftLocStCd_AH) //
                || ZYPCommonFunc.hasValue(aSMsg.aftLocPostCd_AH) //
                || ZYPCommonFunc.hasValue(aSMsg.aftTelNum_AH) //
                // mod start 2016/10/04 CSA QC#12418
//                || ZYPCommonFunc.hasValue(aSMsg.aftFaxNum_AH) //
                || ZYPCommonFunc.hasValue(aSMsg.aftFaxNum_AH)) {
                // mod end 2016/10/04 CSA QC#12418
//                || ZYPCommonFunc.hasValue(aSMsg.aftGlnNum_AH)) {
            map.put("Loc", ZYPConstant.FLG_ON_Y);
        }
        // Mod End 2018/03/28 QC#23145
        if (ZYPCommonFunc.hasValue(aSMsg.aftDunsNum_AH) //
                || ZYPCommonFunc.hasValue(aSMsg.aftDsUltDunsNum_AH) //
                || ZYPCommonFunc.hasValue(aSMsg.aftDsCustSicCd_AH) //
                || ZYPCommonFunc.hasValue(aSMsg.aftDsCustSicDescTxt_AH) //
                || ZYPCommonFunc.hasValue(aSMsg.aftDsLocRevAmt_AH) //
                || ZYPCommonFunc.hasValue(aSMsg.aftDsLocEmpNum_AH) //
                || ZYPCommonFunc.hasValue(aSMsg.aftDsPrntDunsNum_AH) //
                || ZYPCommonFunc.hasValue(aSMsg.aftHqDunsNum_AH)) {
            map.put("LocDuns", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPCommonFunc.hasValue(aSMsg.aftDsAcctDunsNm_AH) //
                || ZYPCommonFunc.hasValue(aSMsg.aftDsCmpySicCd_AH) //
                || ZYPCommonFunc.hasValue(aSMsg.aftDsCmpySicDescTxt_AH)) {
            map.put("AcctDuns", ZYPConstant.FLG_ON_Y);
        }
        return map;
    }

    private boolean callCustUpdateApi(NMAL2800_ASMsg aSMsg) {

        Map<String, String> updFlgMap = new HashMap<String, String>();
        if (PROS_RVW_STS.D.equals(aSMsg.prosRvwStsCd_A1.getValue())) {
            updFlgMap = getUpdateFlg(aSMsg);
            if (ZYPCommonFunc.hasValue(aSMsg.aftLocNum_A1)){
//            if (updFlgMap.isEmpty()) {
                // 2017/11/20 QC#21961 add start
                S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().getDsAcctNm(aSMsg.aftLocNum_A1.getValue());
                if (ssmResult.isCodeNormal()) {
                    Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();

                    ZYPEZDItemValueSetter.setValue(aSMsg.dsAcctNum_A1, (String) map.get("DS_ACCT_NUM"));
                    ZYPEZDItemValueSetter.setValue(aSMsg.locNum_A1, aSMsg.aftLocNum_A1);
                }
                // 2017/11/20 QC#21961 add end
                // 2017/11/20 QC#21961 del start
//                ZYPEZDItemValueSetter.setValue(aSMsg.dsAcctNum_A1, NMAL2800CommonLogic.getDsAcctNum(getGlobalCompanyCode(), aSMsg.aftLocNum_A1.getValue()));
//                ZYPEZDItemValueSetter.setValue(aSMsg.locNum_A1, aSMsg.aftLocNum_A1);
                // 2017/11/20 QC#21961 del end
                return true;
            }
            // 2016/12/14 QC#22244 Add Start
            if (ZYPCommonFunc.hasValue(aSMsg.aftDsXrefAcctCd_A1)){
                return true;
            }
            // 2016/12/14 QC#22244 Add End
        }

        NMZC001001 api = new NMZC001001();

        // set API parameter
        NMZC001001PMsg pMsg = setNMZC001001PMsg(aSMsg, updFlgMap);
        if (pMsg == null) {
            aSMsg.prosRvwStsCd_A1.setErrorInfo(1, ZZZM9006E, new String[] {PROS_ACCT });
            return false;
        }

        // call Customer Update API
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId = null;
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            msgId = msg.getXxMsgid();
            aSMsg.prosRvwStsCd_A1.setErrorInfo(1, msgId);
            return false;
        } else {
            NMZC001002PMsg locPMsg = pMsg.NMZC001002PMsg.no(0);
            if (ZYPCommonFunc.hasValue(locPMsg.xxMsgIdList.no(0).xxMsgId)) {
                msgId = locPMsg.xxMsgIdList.no(0).xxMsgId.getValue();
                // 2017/10/16 QC#21786 Add Start
                if (NMAM8501E.equals(msgId)) {
                    aSMsg.aftLocFirstLineAddr_A1.setErrorInfo(1, msgId);
                } else {
                    // 2017/10/16 QC#21786 Add End
                    aSMsg.prosRvwStsCd_A1.setErrorInfo(1, msgId);
                }
                return false;
            } else {
                ZYPEZDItemValueSetter.setValue(aSMsg.dsAcctNum_A1, pMsg.dsAcctNum);
                ZYPEZDItemValueSetter.setValue(aSMsg.locNum_A1, locPMsg.locNum);
            }
        }

        return true;
    }

    private NMZC001001PMsg setNMZC001001PMsg(NMAL2800_ASMsg aSMsg, Map<String, String> updFlgMap) {

        String slsDt = ZYPDateUtil.getSalesDate();
        String glblCmpyCd = getGlobalCompanyCode();
        String mode = "";
        String locNum = aSMsg.aftLocNum_A1.getValue();
        Map map = new HashMap();

        // set mode
        if (PROS_RVW_STS.U.equals(aSMsg.prosRvwStsCd_A1.getValue())) {
            mode = NMZC001001Constant.PROCESS_MODE_PROS_CRAT;
        } else if (PROS_RVW_STS.D.equals(aSMsg.prosRvwStsCd_A1.getValue()) && ZYPCommonFunc.hasValue(aSMsg.aftLocNum_A1)) {
            S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().getDsAcctNumForCust(locNum);
            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
                if (list != null && !list.isEmpty()) {
                    map = list.get(0);
                    mode = NMZC001001Constant.PROCESS_MODE_CUST_UPD;
                }

            } else {
                S21SsmEZDResult ssmResult2 = NMAL2800Query.getInstance().getDsAcctNumForPros(locNum);
                if (ssmResult2.isCodeNormal()) {
                    List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult2.getResultObject();
                    if (list != null && !list.isEmpty()) {
                        map = list.get(0);
                        mode = NMZC001001Constant.PROCESS_MODE_PROS_UPD;
                    }
                }
            }
        }
        if (mode.isEmpty()) {
            return null;
        }

        /** set parameter **/
        NMZC001001PMsg pMsg = new NMZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, mode);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);

        if (PROS_RVW_STS.U.equals(aSMsg.prosRvwStsCd_A1.getValue())) {

            // Account
            ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNm, aSMsg.befDsAcctNm_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.dsAcctUrl, aSMsg.dsAcctUrl_A1);

            // get Internal/External
            String dsAcctItrlFlg = ZYPCodeDataUtil.getVarCharConstValue(DEF_DS_ACCT_ITRL_FLG, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.dsAcctItrlFlg, dsAcctItrlFlg);

            // get Classification code
            String dsAcctClsCd = ZYPCodeDataUtil.getVarCharConstValue(DEF_DS_ACCT_CLS_CD, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.dsAcctClsCd, dsAcctClsCd);

            // get Default COA
            DEF_DPLY_COA_INFOTMsgArray defDplyCoaInfotMsgArray = NMAL2800CommonLogic.getDefDplyCoaInfo(glblCmpyCd);
            if (defDplyCoaInfotMsgArray != null && defDplyCoaInfotMsgArray.getValidCount() > 0) {
                DEF_DPLY_COA_INFOTMsg defDplyCoaInfotMsg = (DEF_DPLY_COA_INFOTMsg) defDplyCoaInfotMsgArray.get(0);
                ZYPEZDItemValueSetter.setValue(pMsg.coaChCd, defDplyCoaInfotMsg.coaChCd);
                ZYPEZDItemValueSetter.setValue(pMsg.coaAfflCd, defDplyCoaInfotMsg.coaAfflCd);
            }

            ZYPEZDItemValueSetter.setValue(pMsg.xxAcctCrFlg, ZYPConstant.FLG_OFF_N);

            // Account DUNS
            if (ZYPCommonFunc.hasValue(aSMsg.aftDsAcctDunsNm_A1) || ZYPCommonFunc.hasValue(aSMsg.aftDsCmpySicCd_A1) //
                    || ZYPCommonFunc.hasValue(aSMsg.aftDsCmpySicDescTxt_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxAcctDunsInfoFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(pMsg.dsAcctDunsNm, aSMsg.aftDsAcctDunsNm_A1);
                ZYPEZDItemValueSetter.setValue(pMsg.dsCustSicCd, aSMsg.aftDsCmpySicCd_A1);
                ZYPEZDItemValueSetter.setValue(pMsg.dsCustSicDescTxt, aSMsg.aftDsCmpySicDescTxt_A1);
                ZYPEZDItemValueSetter.setValue(pMsg.dsLastUpdDunsDt, slsDt);
                ZYPEZDItemValueSetter.setValue(pMsg.dsXtrnlRefTxt, aSMsg.rvwProsNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg.dsDataSrcTxt, aSMsg.acctSrcTxt_A1);
            }

            // Location
            NMZC001002PMsg pMsg2 = pMsg.NMZC001002PMsg.no(0);
            // 2016/07/26 QC#12417 Del Start
            // ZYPEZDItemValueSetter.setValue(pMsg2.locNm, aSMsg.befDsAcctNm_A1);
            // 2016/07/26 QC#12417 Del End
            ZYPEZDItemValueSetter.setValue(pMsg2.firstLineAddr, aSMsg.aftLocFirstLineAddr_A1);
            ZYPEZDItemValueSetter.setValue(pMsg2.scdLineAddr, aSMsg.aftLocScdLineAddr_A1);
            ZYPEZDItemValueSetter.setValue(pMsg2.thirdLineAddr, aSMsg.aftLocThirdLineAddr_A1);
            ZYPEZDItemValueSetter.setValue(pMsg2.frthLineAddr, aSMsg.aftLocFrthLineAddr_A1);
            ZYPEZDItemValueSetter.setValue(pMsg2.ctyAddr, aSMsg.aftLocCtyAddr_A1);
            ZYPEZDItemValueSetter.setValue(pMsg2.stCd, aSMsg.aftLocStCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg2.postCd, aSMsg.aftLocPostCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg2.cntyNm, aSMsg.cntyNm_A1);
            ZYPEZDItemValueSetter.setValue(pMsg2.ctryCd, aSMsg.aftCtryCd_A1);
            // Del Start 2018/03/28 QC#23145
//            ZYPEZDItemValueSetter.setValue(pMsg2.glnNum, aSMsg.aftGlnNum_A1);
            // Del End 2018/03/28 QC#23145
            ZYPEZDItemValueSetter.setValue(pMsg2.telNum, aSMsg.aftTelNum_A1);
            ZYPEZDItemValueSetter.setValue(pMsg2.faxNum, aSMsg.aftFaxNum_A1);
            ZYPEZDItemValueSetter.setValue(pMsg2.xxPrinFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pMsg2.effFromDt, slsDt);
            ZYPEZDItemValueSetter.setValue(pMsg2.actvFlg, ZYPConstant.FLG_ON_Y);

            // get LineBizTpCd
            ZYPEZDItemValueSetter.setValue(pMsg2.lineBizTpCd, getLineBizTpCd(aSMsg));

            // Location Cross Reference
            ZYPEZDItemValueSetter.setValue(pMsg2.xxLocXrefInfoFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pMsg2.dsXrefAcctTpCd, aSMsg.dsXrefAcctTpCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg2.dsXrefAcctCd, aSMsg.rvwProsNum_A1);
            ZYPEZDItemValueSetter.setValue(pMsg2.dsXrefAcctNm, aSMsg.befDsAcctNm_A1);

            // Location DUNS
            if (ZYPCommonFunc.hasValue(aSMsg.aftDsCustSicCd_A1) || ZYPCommonFunc.hasValue(aSMsg.aftDsCustSicDescTxt_A1)) {
                ZYPEZDItemValueSetter.setValue(pMsg2.xxLocDunsFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(pMsg2.dunsNum, aSMsg.aftDunsNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.hqDunsNum, aSMsg.aftHqDunsNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.dsUltDunsNum, aSMsg.aftDsUltDunsNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.dsPrntDunsNum, aSMsg.aftDsPrntDunsNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.dsLocEmpNum, aSMsg.aftDsLocEmpNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.dsLocRevAmt, aSMsg.aftDsLocRevAmt_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.dsLastUpdDunsDt, slsDt);
                ZYPEZDItemValueSetter.setValue(pMsg2.dsCustSicCd, aSMsg.aftDsCustSicCd_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.dsCustSicDescTxt, aSMsg.aftDsCustSicDescTxt_A1);
            }

        } else if (PROS_RVW_STS.D.equals(aSMsg.prosRvwStsCd_A1.getValue()) && ZYPCommonFunc.hasValue(aSMsg.aftLocNum_A1)) {

            // Account
            ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, (String) map.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNm, (String) map.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(pMsg.dsAcctItrlFlg, (String) map.get("DS_ACCT_ITRL_FLG"));
            ZYPEZDItemValueSetter.setValue(pMsg.dsAcctClsCd, (String) map.get("DS_ACCT_CLS_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.coaChCd, (String) map.get("COA_CH_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.coaAfflCd, (String) map.get("COA_AFFL_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.dsAcctDlrCd, (String) map.get("DS_ACCT_DLR_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.dsAcctLegalNm, (String) map.get("DS_ACCT_LEGAL_NM"));
            ZYPEZDItemValueSetter.setValue(pMsg.dbaNm, (String) map.get("DBA_NM"));
            ZYPEZDItemValueSetter.setValue(pMsg.rgtnStsCd, (String) map.get("RGTN_STS_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.dsAcctUrl, (String) map.get("DS_ACCT_URL"));

            // Account DUNS
            if (ZYPConstant.FLG_ON_Y.equals(updFlgMap.get("AcctDuns"))) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxAcctDunsInfoFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(pMsg.dsAcctDunsNm, aSMsg.aftDsAcctDunsNm_AH);
                ZYPEZDItemValueSetter.setValue(pMsg.dsCustSicCd, aSMsg.aftDsCmpySicCd_AH);
                ZYPEZDItemValueSetter.setValue(pMsg.dsCustSicDescTxt, aSMsg.aftDsCmpySicDescTxt_AH);
                ZYPEZDItemValueSetter.setValue(pMsg.dsLastUpdDunsDt, slsDt);
                ZYPEZDItemValueSetter.setValue(pMsg.dsAcctDunsNum, (String) map.get("DS_ACCT_DUNS_NUM"));
                ZYPEZDItemValueSetter.setValue(pMsg.dsUltDunsNum, (String) map.get("DS_ULT_DUNS_NUM"));
                ZYPEZDItemValueSetter.setValue(pMsg.dsAcctAltNm, (String) map.get("DS_ACCT_ALT_NM"));
                ZYPEZDItemValueSetter.setValue(pMsg.dsLocEmpNum, (BigDecimal) map.get("DS_LOC_EMP_NUM"));
                ZYPEZDItemValueSetter.setValue(pMsg.dsLocRevAmt, (BigDecimal) map.get("DS_LOC_REV_AMT"));
                ZYPEZDItemValueSetter.setValue(pMsg.dsXtrnlRefTxt, (String) map.get("DS_XTRNL_REF_TXT"));
                ZYPEZDItemValueSetter.setValue(pMsg.dsDataSrcTxt, (String) map.get("DS_DATA_SRC_TXT"));
            }

            // Location
            NMZC001002PMsg pMsg2 = pMsg.NMZC001002PMsg.no(0);
            ZYPEZDItemValueSetter.setValue(pMsg2.locNum, locNum);
            ZYPEZDItemValueSetter.setValue(pMsg2.effFromDt, (String) map.get("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(pMsg2.effThruDt, (String) map.get("EFF_THRU_DT"));
            // 2016/07/08 QC#12417 Mod Start
            ZYPEZDItemValueSetter.setValue(pMsg2.locNm, (String) map.get("DS_LOC_NM"));
            // 2016/07/08 QC#12417 Mod End
            ZYPEZDItemValueSetter.setValue(pMsg2.addlLocNm, (String) map.get("ADDL_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(pMsg2.faxNum, (String) map.get("FAX_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg2.provNm, (String) map.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(pMsg2.lineBizTpCd, (String) map.get("LINE_BIZ_TP_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg2.firstLineAddr, (String) map.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(pMsg2.scdLineAddr, (String) map.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(pMsg2.thirdLineAddr, (String) map.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(pMsg2.frthLineAddr, (String) map.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(pMsg2.ctyAddr, (String) map.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(pMsg2.stCd, (String) map.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg2.postCd, (String) map.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg2.cntyPk, (BigDecimal) map.get("CNTY_PK"));
            ZYPEZDItemValueSetter.setValue(pMsg2.ctryCd, (String) map.get("CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg2.glnNum, (BigDecimal) map.get("GLN_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg2.telNum, (String) map.get("TEL_NUM"));

            BigDecimal ptyLocPk = (BigDecimal) map.get("PTY_LOC_PK");
            if (NMAL2800CommonLogic.existPrinCust(glblCmpyCd, ptyLocPk)) {
                ZYPEZDItemValueSetter.setValue(pMsg2.xxPrinFlg, ZYPConstant.FLG_ON_Y);
            }
            if (mode.equals(NMZC001001Constant.PROCESS_MODE_CUST_UPD)) {
                if (NMAL2800CommonLogic.existShipToCust(glblCmpyCd, ptyLocPk)) {
                    ZYPEZDItemValueSetter.setValue(pMsg2.shipToCustFlg, ZYPConstant.FLG_ON_Y);
                }
                if (NMAL2800CommonLogic.existBillToCust(glblCmpyCd, ptyLocPk)) {
                    ZYPEZDItemValueSetter.setValue(pMsg2.billToCustFlg, ZYPConstant.FLG_ON_Y);
                }
            }

            if (ZYPConstant.FLG_ON_Y.equals(updFlgMap.get("Loc"))) {
                ZYPEZDItemValueSetter.setValue(pMsg2.firstLineAddr, aSMsg.aftLocFirstLineAddr_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.scdLineAddr, aSMsg.aftLocScdLineAddr_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.thirdLineAddr, aSMsg.aftLocThirdLineAddr_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.frthLineAddr, aSMsg.aftLocFrthLineAddr_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.ctyAddr, aSMsg.aftLocCtyAddr_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.stCd, aSMsg.aftLocStCd_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.postCd, aSMsg.aftLocPostCd_A1);
                // Del Start 2018/03/28 QC#23145
//                ZYPEZDItemValueSetter.setValue(pMsg2.glnNum, aSMsg.aftGlnNum_A1);
                // Del End 2018/03/28 QC#23145
                ZYPEZDItemValueSetter.setValue(pMsg2.telNum, aSMsg.aftTelNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.faxNum, aSMsg.aftFaxNum_A1);
            }

            // Location DUNS
            if (ZYPConstant.FLG_ON_Y.equals(updFlgMap.get("LocDuns"))) {
                ZYPEZDItemValueSetter.setValue(pMsg2.xxLocDunsFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(pMsg2.dunsNum, aSMsg.aftDunsNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.hqDunsNum, aSMsg.aftHqDunsNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.dsUltDunsNum, aSMsg.aftDsUltDunsNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.dsPrntDunsNum, aSMsg.aftDsPrntDunsNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.dsLocEmpNum, aSMsg.aftDsLocEmpNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.dsLocRevAmt, aSMsg.aftDsLocRevAmt_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.dsLastUpdDunsDt, slsDt);
                ZYPEZDItemValueSetter.setValue(pMsg2.dsCustSicCd, aSMsg.aftDsCustSicCd_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.dsCustSicDescTxt, aSMsg.aftDsCustSicDescTxt_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.dunsTradeStyleNm, (String) map.get("DUNS_TRADE_STYLE_NM"));
                ZYPEZDItemValueSetter.setValue(pMsg2.dunsActvCd, (String) map.get("DUNS_ACTV_CD"));
                ZYPEZDItemValueSetter.setValue(pMsg2.dunsLineAddr, (String) map.get("DUNS_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(pMsg2.dunsBizNm, (String) map.get("DUNS_BIZ_NM"));
                ZYPEZDItemValueSetter.setValue(pMsg2.dsLastRcvDunsTxt, (String) map.get("DS_LAST_RCV_DUNS_TXT"));
                ZYPEZDItemValueSetter.setValue(pMsg2.dsLastRcvDunsDt, (String) map.get("DS_LAST_RCV_DUNS_DT"));
                ZYPEZDItemValueSetter.setValue(pMsg2.dunsSendCnt, (BigDecimal) map.get("DUNS_SEND_CNT"));
            }
        }

        pMsg.NMZC001002PMsg.setValidCount(1);

        return pMsg;
    }

    private String getLineBizTpCd(NMAL2800_ASMsg aSMsg) {

        String lineBizTpCd = null;

        // get Line of Business Type from Assign To Territory
        if (ZYPCommonFunc.hasValue(aSMsg.aftTrtyOrgCd_A1)) {
            S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().getSalesRep(aSMsg.aftTrtyOrgCd_A1.getValue());
            if (ssmResult.isCodeNormal()) {
                Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
                lineBizTpCd = (String) map.get("LINE_BIZ_TP_CD");
            }
        }
        if (lineBizTpCd == null || lineBizTpCd.isEmpty()) {
            // get Line of Business Type from Current Owner
            if (ZYPCommonFunc.hasValue(aSMsg.befPsnNum_A1)) {
                S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().getCurrentOwnerInfo(aSMsg.befPsnNum_A1.getValue());
                if (ssmResult.isCodeNormal()) {
                    Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
                    lineBizTpCd = (String) map.get("LINE_BIZ_TP_CD");
                }
            }
        }
        return lineBizTpCd;
    }

    private boolean insertTrtyRule(NMAL2800_ASMsg aSMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        TRTY_RULETMsg trtyRuleTMsg = new TRTY_RULETMsg();

        // check Territory for rule
        Map<String, String> map = checkTrtyForRule(aSMsg);
        if (map == null) {
            return false;
        }

        // return if it is not necessary to insert Territory Rule
        if (map.get("info").equals(ZYPConstant.FLG_ON_Y)) {
            return true;
        }

        // insert TRTY_RULE
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRulePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_RULE_SQ));
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.orgStruTpCd, map.get("orgStruTpCd"));
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.orgCd, aSMsg.aftTrtyOrgCd_A1.getValue());
        // Mod Start 2017/03/15 QC#15878
//        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleTpCd, TRTY_RULE_TP.ACCOUNT_OR_SITE_NUMBER);
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleTpCd, TRTY_RULE_TP.LOCATION_NUMBER);
        // Mod End 2017/03/15 QC#15878
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleFromValTxt, aSMsg.locNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.effFromDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleOprdTpCd, TRTY_RULE_OPRD_TP.EQUAL);
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleLogicTpCd, TRTY_RULE_LOGIC_TP.OR);

        S21FastTBLAccessor.insert(trtyRuleTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(trtyRuleTMsg.getReturnCode())) {
            aSMsg.trtyOrgNm_A1.setErrorInfo(1, ZZZM9012E, new String[] {trtyRuleTMsg.getReturnCode() });
            return false;
        }

        return true;
    }

    private Map<String, String> checkTrtyForRule(NMAL2800_ASMsg aSMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        HashMap<String, String> map = new HashMap<String, String>();
        String orgStruTpCd = null;
        String orgCd = aSMsg.aftTrtyOrgCd_A1.getValue();

        // check if Territory is active
        S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().getTrtyInfo(orgCd);
        if (ssmResult.isCodeNotFound()) {
            aSMsg.trtyOrgNm_A1.setErrorInfo(1, NMAM8572E, new String[] {orgCd });
            return null;
        } else {
            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
            orgStruTpCd = (String) resultMap.get("ORG_STRU_TP_CD");
            String salesRep = (String) resultMap.get("PSN_CD");
            // check if Sales Rep exists
            if (salesRep == null || salesRep.isEmpty()) {
                aSMsg.trtyOrgNm_A1.setErrorInfo(2, NMAM8558W);
            }

            map.put("orgStruTpCd", orgStruTpCd);
            map.put("info", ZYPConstant.FLG_OFF_N);

            // check if Sales Rep for Territory exists
            // 2016/07/26 QC#12394 Mod Start
            if (cntTrtyGrpResrc(orgCd, ZYPConstant.FLG_OFF_N) != 1) {
                // check if other type of resources exist
                if (cntTrtyGrpResrc(orgCd, ZYPConstant.FLG_ON_Y) == 0) {
                    aSMsg.trtyOrgNm_A1.setErrorInfo(1, NMAM8461E, new String[] {TRTY_ASG_RESRC });
                    return null;
                }
            }
            // 2016/07/26 QC#12394 Mod End

            // check if other type of Territory Rule exists
            if (NMAL2800CommonLogic.existTrtyRuleOtherType(glblCmpyCd, orgStruTpCd, orgCd)) {
                map.put("info", ZYPConstant.FLG_ON_Y);
            }

            // check if Territory Rule is AND operation
            if (NMAL2800CommonLogic.existTrtyRuleAndLogic(glblCmpyCd, orgStruTpCd, orgCd)) {
                map.put("info", ZYPConstant.FLG_ON_Y);
            }
        }

        return map;
    }

    private int cntTrtyGrpResrc(String orgCd, String nonSlsRepFlg) {

        int cnt = 0;
        S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().getTrtyGrpInfo(orgCd, nonSlsRepFlg);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
            cnt = list.size();
        }

        return cnt;
    }

    /**
     * post Mail
     * @param glblMsg NMAL2800SMsg
     * @return boolean
     */
    private boolean postMail(NMAL2800SMsg glblMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        List<Map<String, Object>> slsRepList = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NMAL2800_ASMsg aSMsg = glblMsg.A.no(i);

            if (!ZYPConstant.FLG_ON_Y.equals(aSMsg.xxPgFlg_A1.getValue()) || !PROS_RVW_STS.U.equals(aSMsg.prosRvwStsCd_A1.getValue())) {
                continue;
            }

            // check if Sales Rep is changed
            String psnNum = null;
            String psnNm = null;
            String befPsnNum = null;

            if (!ZYPCommonFunc.hasValue(aSMsg.befPsnNum_A1)) {
                continue;
            }

            S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().getSalesRep(aSMsg.aftTrtyOrgCd_A1.getValue());
            if (ssmResult.isCodeNormal()) {
                Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
                psnNum = (String) map.get("PSN_NUM");
                psnNm = (String) map.get("PSN_FIRST_NM") + " " + (String) map.get("PSN_LAST_NM");
            } else {
                aSMsg.prosRvwStsCd_A1.setErrorInfo(1, NMAM0166E);
                return false;
            }

            befPsnNum = aSMsg.befPsnNum_A1.getValue();
            if (psnNum.equals(befPsnNum)) {
                continue;
            }

            Map<String, Object> slsRepMap = new HashMap<String, Object>();
            slsRepMap.put(ML_PSN_NUM, psnNum);
            slsRepMap.put(ML_PSN_NM, psnNm);
            slsRepMap.put(ML_DS_ACCT_NUM, aSMsg.dsAcctNum_A1.getValue());
            slsRepMap.put(ML_LOC_NUM, aSMsg.locNum_A1.getValue());
            slsRepMap.put(ML_RVW_PROS_NUM, aSMsg.rvwProsNum_A1.getValue());
            slsRepMap.put(ML_BEF_DS_ACCT_NM, aSMsg.befDsAcctNm_A1.getValue());
            slsRepMap.put(ML_BEF_PSN_NUM, befPsnNum);
            slsRepMap.put(ML_EML_ADDR, aSMsg.emlAddr_A1.getValue());
            slsRepMap.put(ML_SLS_REP_NM, aSMsg.xxScrItem61Txt_A1.getValue());
            slsRepMap.put(ML_ROW, i);
            slsRepList.add(slsRepMap);
        }

        if (slsRepList == null || slsRepList.isEmpty()) {
            return true;
        }

        // sort List
        Collections.sort(slsRepList, new CompareSlsRep());

        // get From
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, BIZ_ID);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (addrFromList != null && addrFromList.size() == 1) {
            from = addrFromList.get(0);
        } else {
            return false;
        }

        // get Template
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLETE_ID);
        if (template == null) {
            return false;
        }

        String nextPsnNum = null;
        String curPsnNum = null;
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < slsRepList.size(); i++) {
            map = slsRepList.get(i);
            curPsnNum = map.get(ML_BEF_PSN_NUM).toString();

            msg.append(getFormatStr(map.get(ML_PSN_NM).toString(), STR_LEN_20) + "  ");
            msg.append(getFormatStr(map.get(ML_DS_ACCT_NUM).toString(), STR_LEN_18) + "  ");
            msg.append(getFormatStr(map.get(ML_LOC_NUM).toString(), STR_LEN_15) + "  ");
            msg.append(getFormatStr(map.get(ML_RVW_PROS_NUM).toString(), STR_LEN_20) + "  ");
            msg.append(getFormatStr(map.get(ML_BEF_DS_ACCT_NM).toString(), STR_LEN_20) + "\n");

            if (i + 1 < slsRepList.size()) {
                nextPsnNum = slsRepList.get(i + 1).get(ML_BEF_PSN_NUM).toString();
            } else {
                nextPsnNum = null;
            }

            if (nextPsnNum == null || !nextPsnNum.equals(curPsnNum)) {
                // get To
                List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>();
                S21MailAddress mlAddr = new S21MailAddress(glblCmpyCd, map.get(ML_EML_ADDR).toString().trim(), map.get(ML_SLS_REP_NM).toString());
                addrToList.add(mlAddr);

                // create Message
                StringBuilder body = new StringBuilder();
                body.append(ML_HDR);
                body.append(msg);
                msg = new StringBuilder();

                // set API parameter
                template.setTemplateParameter("PropectNumber", map.get(ML_RVW_PROS_NUM).toString());
                template.setTemplateParameter("Message", body.toString());

                // call Mail API
                S21Mail mail = new S21Mail(glblCmpyCd);
                mail.setFromAddress(from);
                mail.setToAddressList(addrToList);
                mail.setMailTemplate(template);
                mail.setSubject(template.getSubject());
                mail.postMail();
            }
        }

        return true;
    }

    private String getFormatStr(String str, int length) {

        String formatStr = str;

        if (str != null && str.length() > length) {
            formatStr = str.substring(0, length);
        } else if (str == null) {
            formatStr = "";
        }
        formatStr = String.format("%-" + length + "s", formatStr);

        return formatStr;
    }

    /**
     * Custom Comparator Class
     */
    private static final class CompareSlsRep implements Comparator<Map<String, Object>> {
        @Override
        public int compare(Map<String, Object> map1, Map<String, Object> map2) {

            String psnNum1 = map1.get(ML_BEF_PSN_NUM).toString();
            String psnNum2 = map2.get(ML_BEF_PSN_NUM).toString();

            return psnNum2.compareTo(psnNum1);
        }
    }
}
