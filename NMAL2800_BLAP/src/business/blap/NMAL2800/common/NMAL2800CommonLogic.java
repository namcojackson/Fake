/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2800.common;

import static business.blap.NMAL2800.constant.NMAL2800Constant.APP_FUNC_ID;
import static business.blap.NMAL2800.constant.NMAL2800Constant.BIZ_ID;
import static business.blap.NMAL2800.constant.NMAL2800Constant.DT_LEN;
import static business.blap.NMAL2800.constant.NMAL2800Constant.NMAM0052E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.NMAM0072E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.NMAM8114E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.NMAM8554E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.NMAM8555E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.PROS_NUM;
import static business.blap.NMAL2800.constant.NMAL2800Constant.PROS_RVW_STS_CD_ALL;
import static business.blap.NMAL2800.constant.NMAL2800Constant.PROS_RVW_STS_CD_BLANK;
import static business.blap.NMAL2800.constant.NMAL2800Constant.PROS_RVW_STS_DESC_TXT_ALL;
import static business.blap.NMAL2800.constant.NMAL2800Constant.PROS_RVW_STS_DESC_TXT_BLANK;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ZYEM0004E;
import static business.blap.NMAL2800.constant.NMAL2800Constant.ZZM9000E;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_ERROR;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_SUGGEST;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDCSVInFile;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL2800.NMAL2800CMsg;
import business.blap.NMAL2800.NMAL2800Query;
import business.blap.NMAL2800.NMAL2800SMsg;
import business.blap.NMAL2800.NMAL2800_ASMsg;
import business.blap.NMAL2800.NMAL2800_ASMsgArray;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CNTYTMsg;
import business.db.CNTYTMsgArray;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.DEF_DPLY_COA_INFOTMsgArray;
import business.db.DS_ACCT_PROSTMsg;
import business.db.DS_ACCT_PROSTMsgArray;
import business.db.DS_ACCT_RVW_PROSTMsg;
import business.db.DS_ACCT_RVW_PROSTMsgArray;
import business.db.PRIN_CUSTTMsg;
import business.db.PRIN_CUSTTMsgArray;
import business.db.PROS_RVW_STSTMsg;
import business.db.PROS_RVW_STSTMsgArray;
import business.db.SAVE_SRCH_OPTTMsg;
import business.db.SAVE_SRCH_OPTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.STTMsg;
import business.db.TRTY_RULETMsg;
import business.db.TRTY_RULETMsgArray;
import business.db.TRTY_TRK_TRTY_LISTTMsg;
import business.db.TRTY_TRK_TRTY_LISTTMsgArray;
import business.file.NMAL2800F01FMsg;
import business.parts.NMZC003001PMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC003001.NMZC003001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ST;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_LOGIC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NMAL2800CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 * 2016/07/26   Fujitsu         N.Sugiura       Update          QC#12417 
 * 2017/03/15   Fujitsu         R.Nakamura      Update          QC#15878
 * 2017/10/10   Fujitsu         M.Ohno          Update          QC#20662
 * 2017/10/17   Fujitsu         M.Ohno          Update          QC#21782
 * 2017/10/20   Fujitsu         M.Ohno          Update          QC#21866
 * 2017/12/14   Fujitsu         N.Sugiura       Update          QC#22244
 * 2018/01/16   Fujitsu         Hd.Sugawara     Update          QC#23148
 * 2018/03/02   Fujitsu         Hd.Sugawara     Update          QC#23148-1
 * 2018/03/28   Fujitsu         R.Nakamura      Update          QC#23149
 * 2018/04/11   Fujitsu         R.Nakamura      Update          QC#23149-2
 *</pre>
 */
public class NMAL2800CommonLogic {

    /**
     * Create Save Option PullDown list
     * @param bizMsg NMAL2800CMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void createPulldownListSaveOpt(NMAL2800CMsg bizMsg, String userId, String glblCmpyCd) {

        bizMsg.srchOptPk_L.clear();
        bizMsg.srchOptNm_L.clear();

        SAVE_SRCH_OPTTMsg saveSrchOptTMsg = new SAVE_SRCH_OPTTMsg();
        saveSrchOptTMsg.setSQLID("001");
        saveSrchOptTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        saveSrchOptTMsg.setConditionValue("srchOptAplId01", BIZ_ID);
        saveSrchOptTMsg.setConditionValue("srchOptUsrId01", userId);

        SAVE_SRCH_OPTTMsgArray saveSrchOptTMsgArray = (SAVE_SRCH_OPTTMsgArray) EZDTBLAccessor.findByCondition(saveSrchOptTMsg);
        for (int i = 0; i < saveSrchOptTMsgArray.length(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_L.no(i), saveSrchOptTMsgArray.no(i).srchOptPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptNm_L.no(i), saveSrchOptTMsgArray.no(i).srchOptNm);
        }
    }

    /**
     * Create Prospect Review Status PullDown list
     * @param bizMsg NMAL2800CMsg
     * @param glblCmpyCd String
     */
    public static void createPulldownListProsRvwSts(NMAL2800CMsg bizMsg, String glblCmpyCd) {

        bizMsg.prosRvwStsCd_L.clear();
        bizMsg.prosRvwStsDescTxt_L.clear();
        bizMsg.prosRvwStsCd_L2.clear();
        bizMsg.prosRvwStsNm_L2.clear();

        ZYPEZDItemValueSetter.setValue(bizMsg.prosRvwStsCd_L.no(0), PROS_RVW_STS_CD_ALL);
        ZYPEZDItemValueSetter.setValue(bizMsg.prosRvwStsDescTxt_L.no(0), PROS_RVW_STS_DESC_TXT_ALL);
        ZYPEZDItemValueSetter.setValue(bizMsg.prosRvwStsCd_L.no(1), PROS_RVW_STS_CD_BLANK);
        ZYPEZDItemValueSetter.setValue(bizMsg.prosRvwStsDescTxt_L.no(1), PROS_RVW_STS_DESC_TXT_BLANK);

        PROS_RVW_STSTMsg prosRvwStsTMsg = new PROS_RVW_STSTMsg();
        prosRvwStsTMsg.setSQLID("001");
        prosRvwStsTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);

        PROS_RVW_STSTMsgArray prosRvwStsTMsgArray = (PROS_RVW_STSTMsgArray) EZDTBLAccessor.findByCondition(prosRvwStsTMsg);
        int cnt = 2;
        for (int i = 0; i < prosRvwStsTMsgArray.length(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prosRvwStsCd_L.no(cnt), prosRvwStsTMsgArray.no(i).prosRvwStsCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.prosRvwStsDescTxt_L.no(cnt), prosRvwStsTMsgArray.no(i).prosRvwStsDescTxt);
            ZYPEZDItemValueSetter.setValue(bizMsg.prosRvwStsCd_L2.no(i), prosRvwStsTMsgArray.no(i).prosRvwStsCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.prosRvwStsNm_L2.no(i), prosRvwStsTMsgArray.no(i).prosRvwStsNm);
            cnt++;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.prosRvwStsCd_H1, PROS_RVW_STS_CD_BLANK);
    }

    /**
     * Call NSZC0330 for Search Option
     * @param bizMsg NMAL2800CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    public static boolean callSrchOptApi(NMAL2800CMsg bizMsg, NSZC033001PMsg pMsg) {

        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int i = 0; i < pMsg.xxMsgIdList.length(); i++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(i).xxMsgId)) {
                    String msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        bizMsg.srchOptPk.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * isExistSaveSearchName
     * @param bizMsg NLAL2020CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NMAL2800CMsg bizMsg) {

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk) && bizMsg.srchOptPk.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * isSameSaveSearchName
     * @param bizMsg NMAL2800CMsg
     * @return boolean
     */
    public static boolean isSameSaveSearchName(NMAL2800CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (bizMsg.srchOptPk.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                if (bizMsg.srchOptNm.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param bizMsg NLAL2020CMsg
     * @param pMsg NSZC033001PMsg
     */
    public static void setSelectSaveSearchName(NMAL2800CMsg bizMsg, NSZC033001PMsg pMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_L.no(i));
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_L.no(i));
            }
        }
        return;
    }

    /**
     * readCsvHeader_UPLOAD
     * @param bizMsg NMAL2800CMsg
     * @param fMsg NMAL2800F01FMsg
     * @param mappedFile EZDCSVInFile
     */
    public static void readCsvHeader_UPLOAD(NMAL2800CMsg bizMsg, NMAL2800F01FMsg fMsg, EZDCSVInFile mappedFile) {

        /*
         * if ZYECSVUploadFMsg read the csv file header, some errors
         * be detected. because csv header is not consistent with FMsg
         * format. So if read the header, it has to clear() the FMsg.
         * if there is no header, it will be skipped the first row.
         */
        int header = mappedFile.read();

        if (header == 1) {
            bizMsg.setMessageInfo(ZYEM0004E);
        }
        fMsg.clear();
    }

    /**
     * validateAndCopyToGlblMsg_UPLOAD
     * @param status int
     * @param count int
     * @param glblLineMsg NMAL2800_ASMsgArray
     * @param fMsg fMsgNMAL2800F01FMsg
     * @param bizMsg NMAL2800CMsg
     * @param glblCmpyCd String
     * @return boolean if error then false else true
     */
    public static boolean validateAndCopyToGlblMsg_UPLOAD(//
            int status //
            , int count //
            , NMAL2800_ASMsgArray glblLineMsg //
            , NMAL2800F01FMsg fMsg //
            , NMAL2800CMsg bizMsg //
            , String glblCmpyCd) {

        /****************************************************************
         * Record count check
         ****************************************************************/
        if (count > glblLineMsg.length()) {
            bizMsg.setMessageInfo(NMAM8114E);
            return false;
        }
        int row = count - 1;

        /****************************************************************
         * CSV format check
         ****************************************************************/
        if (status == 1000) {
            bizMsg.setMessageInfo(NMAM0052E, new String[] {"CSV" });
            return false;
        }

        /****************************************************************
         * Mandatory check
         ****************************************************************/
        if (!ZYPCommonFunc.hasValue(fMsg.rvwProsNum.getValue())) {
            bizMsg.setMessageInfo(ZZM9000E, new String[] {"Prospect Number" });
            return false;
        }

        /****************************************************************
         * Error check
         ****************************************************************/
        NMAL2800_ASMsg aSMsg = glblLineMsg.no(row);

        // check duplicate
        for (int j = 0; j < glblLineMsg.getValidCount(); j++) {
            if (row != j && aSMsg.rvwProsNum_A1.getValue().equals(glblLineMsg.no(j).rvwProsNum_A1.getValue())) {
                aSMsg.prosRvwStsCd_A1.setErrorInfo(1, NMAM0072E, new String[] {PROS_NUM });
                glblLineMsg.no(j).prosRvwStsCd_A1.setErrorInfo(1, NMAM0072E, new String[] {PROS_NUM });
            }
        }

        // check if prospect exists
        DS_ACCT_RVW_PROSTMsgArray tMsgArray = getDsAcctRvwPros(glblCmpyCd, fMsg.rvwProsNum.getValue());
        DS_ACCT_RVW_PROSTMsg tMsg = null;
        if (tMsgArray != null && tMsgArray.getValidCount() == 1) {
            tMsg = (DS_ACCT_RVW_PROSTMsg) tMsgArray.get(0);
            if (ZYPCommonFunc.hasValue(tMsg.prosRvwStsCd)) {
                aSMsg.prosRvwStsCd_A1.setErrorInfo(1, NMAM8555E);
            }
        } else {
            aSMsg.prosRvwStsCd_A1.setErrorInfo(1, NMAM8554E);
        }

        /****************************************************************
         * Get data from DB
         ****************************************************************/
        // get Current Owner Name and Line of Business Type
        if (ZYPCommonFunc.hasValue(fMsg.befPsnNum)) {
            S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().getCurrentOwnerInfo(fMsg.befPsnNum.getValue());
            if (ssmResult.isCodeNormal()) {
                Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(aSMsg.xxScrItem61Txt_A1, (String) map.get("XX_SCR_ITEM_61_TXT"));
                ZYPEZDItemValueSetter.setValue(aSMsg.lineBizTpDescTxt_A1, (String) map.get("LINE_BIZ_TP_DESC_TXT"));
            }
        }

        // get Sales Rep
        // Mod Start 2018/01/16 QC#23148
        //if (ZYPCommonFunc.hasValue(fMsg.aftTrtyOrgCd)) {
        //    String psnNm = "";
        //    S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().getSalesRep(fMsg.aftTrtyOrgCd.getValue());
        //    if (!ssmResult.isCodeNotFound()) {
        //        Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
        //        psnNm = (String) map.get("PSN_FIRST_NM") + " " + (String) map.get("PSN_LAST_NM");
        //    }
        //    ZYPEZDItemValueSetter.setValue(aSMsg.xxScrItem61Txt_A2, psnNm);
        //}
        if (ZYPCommonFunc.hasValue(fMsg.trtyOrgNm)) {
            String xxScrItem61Txt = "";
            S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().getSalesRepByTrtyOrgNm(fMsg.trtyOrgNm.getValue());
            if (!ssmResult.isCodeNotFound()) {
                List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
                Map<String, Object> map = (Map<String, Object>) list.get(0);
                xxScrItem61Txt = (String) map.get("XX_SCR_ITEM_61_TXT_A2");
            }
            ZYPEZDItemValueSetter.setValue(aSMsg.xxScrItem61Txt_A2, xxScrItem61Txt);
        }
        // Mod End 2018/01/16 QC#23148

        /****************************************************************
         * Copy FMsg to SMsg.
         ****************************************************************/
        // 2017/10/20 QC#21866 add start
        ZYPEZDItemValueSetter.setValue(aSMsg.prosRvwStsCd_A1, fMsg.prosRvwStsCd);
        // 2017/10/20 QC#21866 add end
        ZYPEZDItemValueSetter.setValue(aSMsg.rvwProsNum_A1, fMsg.rvwProsNum);
        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(aSMsg.befDsAcctNm_A1, tMsg.befDsAcctNm);
            ZYPEZDItemValueSetter.setValue(aSMsg.dsXrefAcctTpCd_A1, tMsg.dsXrefAcctTpCd);
            // 2017/10/17 QC#21782 mod start
//            ZYPEZDItemValueSetter.setValue(aSMsg.befBillToFirstLineAddr_A1, tMsg.befBillToFirstLineAddr);
//            ZYPEZDItemValueSetter.setValue(aSMsg.befBillToScdLineAddr_A1, tMsg.befBillToScdLineAddr);
//            ZYPEZDItemValueSetter.setValue(aSMsg.befBillToThirdLineAddr_A1, tMsg.befBillToThirdLineAddr);
//            ZYPEZDItemValueSetter.setValue(aSMsg.befBillToFrthLineAddr_A1, tMsg.befBillToFrthLineAddr);
//            ZYPEZDItemValueSetter.setValue(aSMsg.befBillToCtyAddr_A1, tMsg.befBillToCtyAddr);
//            ZYPEZDItemValueSetter.setValue(aSMsg.befBillToStCd_A1, tMsg.befBillToStCd);
//            ZYPEZDItemValueSetter.setValue(aSMsg.befBillToPostCd_A1, tMsg.befBillToPostCd);
//            ZYPEZDItemValueSetter.setValue(aSMsg.befBillToCntyNm_A1, tMsg.befBillToCntyNm);
            ZYPEZDItemValueSetter.setValue(aSMsg.befLocFirstLineAddr_A1, tMsg.befLocFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(aSMsg.befLocScdLineAddr_A1, tMsg.befLocScdLineAddr);
            ZYPEZDItemValueSetter.setValue(aSMsg.befLocThirdLineAddr_A1, tMsg.befLocThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(aSMsg.befLocFrthLineAddr_A1, tMsg.befLocFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(aSMsg.befShipToCtyAddr_A1, tMsg.befShipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(aSMsg.befShipToStNm_A1, tMsg.befShipToStNm);
            ZYPEZDItemValueSetter.setValue(aSMsg.befShipToPostCd_A1, tMsg.befShipToPostCd);
            ZYPEZDItemValueSetter.setValue(aSMsg.befShipToCntyNm_A1, tMsg.befShipToCntyNm);
            // 2017/10/17 QC#21782 mod end
            ZYPEZDItemValueSetter.setValue(aSMsg.befDunsNum_A1, tMsg.befDunsNum);
            ZYPEZDItemValueSetter.setValue(aSMsg.befDsLocEmpNum_A1, tMsg.befDsLocEmpNum);
            ZYPEZDItemValueSetter.setValue(aSMsg.befDsCustSicCd_A1, tMsg.befDsCustSicCd);
            ZYPEZDItemValueSetter.setValue(aSMsg.befDsUltDunsNum_A1, tMsg.befDsUltDunsNum);
            ZYPEZDItemValueSetter.setValue(aSMsg.befTelNum_A1, tMsg.befTelNum);
            ZYPEZDItemValueSetter.setValue(aSMsg.befFaxNum_A1, tMsg.befFaxNum);
            ZYPEZDItemValueSetter.setValue(aSMsg.befPsnNum_A1, tMsg.befPsnNum);
            ZYPEZDItemValueSetter.setValue(aSMsg.xtrnlCratDtTmTs_A1, tMsg.xtrnlCratDtTmTs);
            ZYPEZDItemValueSetter.setValue(aSMsg.xtrnlLastCratDtTmTs_A1, tMsg.xtrnlLastCratDtTmTs);
            ZYPEZDItemValueSetter.setValue(aSMsg.matchAcctLocNum_A1, tMsg.matchAcctLocNum);

            if (ZYPCommonFunc.hasValue(aSMsg.xtrnlCratDtTmTs_A1)) {
                String dt1 = aSMsg.xtrnlCratDtTmTs_A1.getValue().substring(0, DT_LEN);
                ZYPEZDItemValueSetter.setValue(aSMsg.xxDtTxt_A1, ZYPDateUtil.formatEzd8ToDisp(dt1));
            }
            if (ZYPCommonFunc.hasValue(aSMsg.xtrnlLastCratDtTmTs_A1)) {
                String dt2 = aSMsg.xtrnlLastCratDtTmTs_A1.getValue().substring(0, DT_LEN);
                ZYPEZDItemValueSetter.setValue(aSMsg.xxDtTxt_A2, ZYPDateUtil.formatEzd8ToDisp(dt2));
            }
        }
        // Del Start 2018/01/16 QC#23148
        //ZYPEZDItemValueSetter.setValue(aSMsg.trtyOrgNm_A1, fMsg.trtyOrgNm);
        // Del End 2018/01/16 QC#23148
        ZYPEZDItemValueSetter.setValue(aSMsg.candiTrtyNm_A1, fMsg.candiTrtyNm);
        ZYPEZDItemValueSetter.setValue(aSMsg.trtyOrgNm_A1, fMsg.trtyOrgNm);
        // Del Start 2018/01/16 QC#23148
        //ZYPEZDItemValueSetter.setValue(aSMsg.aftLocNum_A1, fMsg.aftLocNum);
        // Del End 2018/01/16 QC#23148
        // Add Start 2018/03/02 QC#23148-1
        ZYPEZDItemValueSetter.setValue(aSMsg.aftLocNum_A1, fMsg.aftLocNum);
        // Add End 2018/03/02 QC#23148-1
        ZYPEZDItemValueSetter.setValue(aSMsg.aftDsAcctNm_A1, fMsg.aftDsAcctNm);
        // Mod Start 2018/01/16 QC#23148
        //ZYPEZDItemValueSetter.setValue(aSMsg.aftLocFirstLineAddr_A1, fMsg.aftLocFirstLineAddr);
        //ZYPEZDItemValueSetter.setValue(aSMsg.aftLocScdLineAddr_A1, fMsg.aftLocScdLineAddr);
        //ZYPEZDItemValueSetter.setValue(aSMsg.aftLocThirdLineAddr_A1, fMsg.aftLocThirdLineAddr);
        //ZYPEZDItemValueSetter.setValue(aSMsg.aftLocFrthLineAddr_A1, fMsg.aftLocFrthLineAddr);
        //ZYPEZDItemValueSetter.setValue(aSMsg.aftLocCtyAddr_A1, fMsg.aftLocCtyAddr);
        //ZYPEZDItemValueSetter.setValue(aSMsg.aftLocStCd_A1, fMsg.aftLocStCd);
        //ZYPEZDItemValueSetter.setValue(aSMsg.aftLocPostCd_A1, fMsg.aftLocPostCd);
        //ZYPEZDItemValueSetter.setValue(aSMsg.aftCtryCd_A1, fMsg.aftCtryCd);
        ZYPEZDItemValueSetter.setValue(aSMsg.aftLocFirstLineAddr_A1, fMsg.befLocFirstLineAddr_A2);
        ZYPEZDItemValueSetter.setValue(aSMsg.aftLocScdLineAddr_A1, fMsg.befLocScdLineAddr_A2);
        ZYPEZDItemValueSetter.setValue(aSMsg.aftLocThirdLineAddr_A1, fMsg.befLocThirdLineAddr_A2);
        ZYPEZDItemValueSetter.setValue(aSMsg.aftLocFrthLineAddr_A1, fMsg.befLocFrthLineAddr_A2);
        ZYPEZDItemValueSetter.setValue(aSMsg.aftLocCtyAddr_A1, fMsg.befShipToCtyAddr_A2);
        // Mod Start 2018/03/28 QC#23149
//        ZYPEZDItemValueSetter.setValue(aSMsg.aftLocStCd_A1, fMsg.aftLocStCd);
        if (ZYPCommonFunc.hasValue(fMsg.stDescTxt)) {
            String stCd = "";
            S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().getLocStCd(fMsg.stDescTxt.getValue());
            if (!ssmResult.isCodeNotFound()) {
                List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
                Map<String, Object> map = (Map<String, Object>) list.get(0);
                stCd = (String) map.get("ST_CD");
            }
            ZYPEZDItemValueSetter.setValue(aSMsg.aftLocStCd_A1, stCd);
        }
        // Mod End 2018/03/28 QC#23149
        ZYPEZDItemValueSetter.setValue(aSMsg.aftLocPostCd_A1, fMsg.befShipToPostCd_A2);

        String ctryCd = null;
        // Mod Start 2018/04/11 QC#23149-2
        String befCtryNm = null;
        if (ZYPCommonFunc.hasValue(fMsg.befCtryNm_A2)) {
//            ctryCd = getCtryCdForUploadDownload(fMsg.befCtryNm_A2.getValue());
            befCtryNm = fMsg.befCtryNm_A2.getValue();
        } else {
            befCtryNm = CTRY.UNITED_STATES_OF_AMERICA;
        }
        ctryCd = getCtryCdForUploadDownload(befCtryNm);
        // Mod End 2018/04/11 QC#23149-2
        ZYPEZDItemValueSetter.setValue(aSMsg.aftCtryCd_A1, ctryCd);
        // Mod End 2018/01/16 QC#23148

        ZYPEZDItemValueSetter.setValue(aSMsg.cntyNm_A1, fMsg.cntyNm);
        ZYPEZDItemValueSetter.setValue(aSMsg.aftDsXrefAcctCd_A1, fMsg.aftDsXrefAcctCd);
        ZYPEZDItemValueSetter.setValue(aSMsg.aftTelNum_A1, fMsg.aftTelNum);
        ZYPEZDItemValueSetter.setValue(aSMsg.aftFaxNum_A1, fMsg.aftFaxNum);
        ZYPEZDItemValueSetter.setValue(aSMsg.aftDsAcctDunsNm_A1, fMsg.aftDsAcctDunsNm);
        ZYPEZDItemValueSetter.setValue(aSMsg.aftDsLocRevAmt_A1, fMsg.aftDsLocRevAmt);
        ZYPEZDItemValueSetter.setValue(aSMsg.aftDunsNum_A1, fMsg.aftDunsNum);
        ZYPEZDItemValueSetter.setValue(aSMsg.aftDsCustSicDescTxt_A1, fMsg.aftDsCustSicDescTxt);
        ZYPEZDItemValueSetter.setValue(aSMsg.aftDsLocEmpNum_A1, fMsg.aftDsLocEmpNum);
        ZYPEZDItemValueSetter.setValue(aSMsg.aftDsCustSicCd_A1, fMsg.aftDsCustSicCd);
        ZYPEZDItemValueSetter.setValue(aSMsg.aftDsUltDunsNum_A1, fMsg.aftDsUltDunsNum);
        // Del Start 2018/01/16 QC#23148
        //ZYPEZDItemValueSetter.setValue(aSMsg.aftGlnNum_A1, fMsg.aftGlnNum);
        // Del End 2018/01/16 QC#23148
        ZYPEZDItemValueSetter.setValue(aSMsg.aftDsPrntDunsNum_A1, fMsg.aftDsPrntDunsNum);
        ZYPEZDItemValueSetter.setValue(aSMsg.aftHqDunsNum_A1, fMsg.aftHqDunsNum);
        ZYPEZDItemValueSetter.setValue(aSMsg.aftDsCmpySicCd_A1, fMsg.aftDsCmpySicCd);
        ZYPEZDItemValueSetter.setValue(aSMsg.aftDsCmpySicDescTxt_A1, fMsg.aftDsCmpySicDescTxt);
        // Add Start 2018/01/16 QC#23148
        ZYPEZDItemValueSetter.setValue(aSMsg.resrcTrtyOrgNm_A1, fMsg.resrcTrtyOrgNm);
        // Add End 2018/01/16 QC#23148

        return true;
    }

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NMAL2800CMsg bizMsg = (NMAL2800CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {

            if (i < sMsgArray.getValidCount()) {

                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);

            } else {

                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());

        NMAL2800CommonLogic.createStatePulldownList(bizMsg);
    }

    /**
     * Update the global Message.
     * @param bizMsg NMAL2800CMsg
     * @param glblMsg NMAL2800SMsg
     */
    public static void updateGlblMsg(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMessageInfo info1 = getErrorInfo(glblMsg.A.no(ixG + i), "prosRvwStsCd_A1");
            EZDMessageInfo info2 = getErrorInfo(glblMsg.A.no(ixG + i), "aftTrtyOrgCd_A1");
            EZDMessageInfo info3 = getErrorInfo(glblMsg.A.no(ixG + i), "xtrnlProsRvwCmntTxt_A1");
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
            if (info1 != null) {
                glblMsg.A.no(ixG + i).prosRvwStsCd_A1.setErrorInfo(info1.getErrorKbn(), info1.getCode(), info1.getParameter());
            }
            if (info2 != null) {
                glblMsg.A.no(ixG + i).trtyOrgNm_A1.setErrorInfo(info2.getErrorKbn(), info2.getCode(), info2.getParameter());
            }
            if (info3 != null) {
                glblMsg.A.no(ixG + i).aftDsXrefAcctCd_A1.setErrorInfo(info3.getErrorKbn(), info3.getCode(), info3.getParameter());
            }
        }
    }

    private static EZDMessageInfo getErrorInfo(EZDSMsg smsg, String key) {
        EZDMessageInfo info;
        try {
            // Invoke EZDSMsg#getErrorInfo(String, int)
            Method method = EZDSMsg.class.getDeclaredMethod("getErrorInfo", String.class, int.class);
            method.setAccessible(true);
            info = (EZDMessageInfo) method.invoke(smsg, key, -1);
        } catch (SecurityException e) {
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        } catch (InvocationTargetException e) {
            return null;
        }
        return info;
    }

    /**
     * Jump to error page
     * @param bizMsg NMAL2800CMsg
     * @param glblMsg NMAL2800SMsg
     */
    public static void jumpToErrorPage(NMAL2800CMsg bizMsg, NMAL2800SMsg glblMsg) {

        int errPage = 1;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NMAL2800_ASMsg aSMsg = glblMsg.A.no(i);
            if (aSMsg.prosRvwStsCd_A1.isError() || aSMsg.trtyOrgNm_A1.isError() || aSMsg.aftDsXrefAcctCd_A1.isError()) {
                errPage = (i / bizMsg.A.length()) * bizMsg.A.length();
                break;
            }
        }

        bizMsg.xxPageShowFromNum.setValue(errPage);
        NMAL2800CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * Get default COA information
     * @param glblCmpyCd String
     * @return DEF_DPLY_COA_INFOTMsgArray
     */
    public static DEF_DPLY_COA_INFOTMsgArray getDefDplyCoaInfo(String glblCmpyCd) {

        DEF_DPLY_COA_INFOTMsg tMsg = new DEF_DPLY_COA_INFOTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("appFuncId01", APP_FUNC_ID);

        return (DEF_DPLY_COA_INFOTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
    }

    /**
     * Get County PK
     * @param glblCmpyCd String
     * @param sMsg NMAL2800_ASMsg
     * @return BigDecimal
     */
    public static BigDecimal getCntyPk(String glblCmpyCd, NMAL2800_ASMsg sMsg) {

        BigDecimal cntyPk = null;

        CNTYTMsg tMsg = new CNTYTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("cntyNm01", sMsg.cntyNm_A1.getValue());
        tMsg.setConditionValue("stCd01", sMsg.aftLocStCd_A1.getValue());

        CNTYTMsgArray tMsgArray = (CNTYTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            tMsg = (CNTYTMsg) tMsgArray.get(0);
            cntyPk = tMsg.cntyPk.getValue();
        }

        return cntyPk;
    }

    /**
     * Check if PRIN_CUST exists
     * @param glblCmpyCd String
     * @param ptyLocPk BigDecimal
     * @return boolean
     */
    public static boolean existPrinCust(String glblCmpyCd, BigDecimal ptyLocPk) {

        PRIN_CUSTTMsg tMsg = new PRIN_CUSTTMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("ptyLocPk01", ptyLocPk);

        PRIN_CUSTTMsgArray tMsgArray = (PRIN_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            return true;
        }

        return false;
    }

    /**
     * Check if SHIP_TO_CUST exists
     * @param glblCmpyCd String
     * @param ptyLocPk BigDecimal
     * @return boolean
     */
    public static boolean existShipToCust(String glblCmpyCd, BigDecimal ptyLocPk) {

        SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();
        tMsg.setSQLID("027");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("ptyLocPk01", ptyLocPk);

        SHIP_TO_CUSTTMsgArray tMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            return true;
        }

        return false;
    }

    /**
     * Check if BILL_TO_CUST exists
     * @param glblCmpyCd String
     * @param ptyLocPk BigDecimal
     * @return boolean
     */
    public static boolean existBillToCust(String glblCmpyCd, BigDecimal ptyLocPk) {

        BILL_TO_CUSTTMsg tMsg = new BILL_TO_CUSTTMsg();
        tMsg.setSQLID("038");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("ptyLocPk01", ptyLocPk);

        BILL_TO_CUSTTMsgArray tMsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            return true;
        }

        return false;
    }

    /**
     * Get DS_ACCT_RVW_PROS
     * @param glblCmpyCd String
     * @param rvwProsNum String
     * @return DS_ACCT_RVW_PROSTMsgArray
     */
    public static DS_ACCT_RVW_PROSTMsgArray getDsAcctRvwPros(String glblCmpyCd, String rvwProsNum) {

        DS_ACCT_RVW_PROSTMsg tMsg = new DS_ACCT_RVW_PROSTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("rvwProsNum01", rvwProsNum);

        return (DS_ACCT_RVW_PROSTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
    }

    /**
     * Check if other type of Territory Rule exists
     * @param glblCmpyCd String
     * @param orgStruTpCd String
     * @param orgCd String
     * @return boolean
     */
    public static boolean existTrtyRuleOtherType(String glblCmpyCd, String orgStruTpCd, String orgCd) {

        String slsDt = ZYPDateUtil.getSalesDate();

        TRTY_RULETMsg tMsg = new TRTY_RULETMsg();
        tMsg.setSQLID("004");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("orgCd01", orgCd);
        tMsg.setConditionValue("orgStruTpCd01", orgStruTpCd);
        tMsg.setConditionValue("effFromDt01", slsDt);
        tMsg.setConditionValue("effThruDt01", slsDt);
        // Mod Start 2017/03/15 QC#15878
//        tMsg.setConditionValue("trtyRuleTpCd01", TRTY_RULE_TP.ACCOUNT_OR_SITE_NUMBER);
        tMsg.setConditionValue("trtyRuleTpCd01", TRTY_RULE_TP.LOCATION_NUMBER);
        // Mod End 2017/03/15 QC#15878

        TRTY_RULETMsgArray tMsgArray = (TRTY_RULETMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            return true;
        }

        return false;
    }

    /**
     * Check if Territory Rule is for Location rule
     * @param glblCmpyCd String
     * @param orgStruTpCd String
     * @param orgCd String
     * @return boolean
     */
    public static boolean existTrtyRuleAndLogic(String glblCmpyCd, String orgStruTpCd, String orgCd) {

        String slsDt = ZYPDateUtil.getSalesDate();

        TRTY_RULETMsg tMsg = new TRTY_RULETMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("orgCd01", orgCd);
        tMsg.setConditionValue("orgStruTpCd01", orgStruTpCd);
        tMsg.setConditionValue("effFromDt01", slsDt);
        tMsg.setConditionValue("effThruDt01", slsDt);
        // Mod Start 2017/03/15 QC#15878
//        tMsg.setConditionValue("trtyRuleTpCd01", TRTY_RULE_TP.ACCOUNT_OR_SITE_NUMBER);
        tMsg.setConditionValue("trtyRuleTpCd01", TRTY_RULE_TP.LOCATION_NUMBER);
        // Mod End 2017/03/15 QC#15878
        tMsg.setConditionValue("TRTY_RULE_LOGIC_TP_CD", TRTY_RULE_LOGIC_TP.OR);

        TRTY_RULETMsgArray tMsgArray = (TRTY_RULETMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            return true;
        }

        return false;
    }

    /**
     * Check if Territory Tracker exist
     * @param glblCmpyCd String
     * @param orgCd String
     * @return boolean
     */
    public static boolean existTrtyTrkTrtyList(String glblCmpyCd, String orgCd) {

        TRTY_TRK_TRTY_LISTTMsg tMsg = new TRTY_TRK_TRTY_LISTTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("trtyOrgCd01", orgCd);

        TRTY_TRK_TRTY_LISTTMsgArray tMsgArray = (TRTY_TRK_TRTY_LISTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            return true;
        }

        return false;
    }

    /**
     * Get DS_ACCT_NUM
     * @param glblCmpyCd String
     * @param locNum String
     * @return S21_PSNTMsgArray
     */
    public static String getDsAcctNum(String glblCmpyCd, String locNum) {

        String dsAcctNum = null;

        DS_ACCT_PROSTMsg tMsg = new DS_ACCT_PROSTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("locNum01", locNum);

        DS_ACCT_PROSTMsgArray tMsgArray = (DS_ACCT_PROSTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            tMsg = (DS_ACCT_PROSTMsg) tMsgArray.get(0);
            dsAcctNum = tMsg.dsAcctNum.getValue();
        }

        return dsAcctNum;
    }
    // 2016/07/26 QC#12417 Add Start
    /**
     * createStatePulldownList
     * @param bizMsg NMAL2800CMsg
     */
    public static void createStatePulldownList(NMAL2800CMsg bizMsg){
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // Mod Start 2018/04/12 QC#23149-2
            bizMsg.A.no(i).stCd_LL.clear();
            bizMsg.A.no(i).stDescTxt_LL.clear();
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).aftCtryCd_A1)) {

//                bizMsg.A.no(i).stCd_LL.clear();
//                bizMsg.A.no(i).stDescTxt_LL.clear();

                S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().getStateList(bizMsg.A.no(i).aftCtryCd_A1.getValue());

                if (ssmResult.isCodeNormal()){
                    List<STTMsg> stTMsgList = (List) ssmResult.getResultObject();
                    for (int j = 0; j < stTMsgList.size(); j++) {
                        bizMsg.A.no(i).stCd_LL.no(j).setValue(stTMsgList.get(j).stCd.getValue());
                        bizMsg.A.no(i).stDescTxt_LL.no(j).setValue(stTMsgList.get(j).stDescTxt.getValue());
                    }
                }
            } else {
                ZYPCodeDataUtil.createPulldownList(ST.class, bizMsg.A.no(i).stCd_LL, bizMsg.A.no(i).stDescTxt_LL);
            }
            // Mod End 2018/04/12 QC#23149-2
        }
    }

    /**
     * Get Country Code
     * @param ctryNm String
     * @return String
     */
    public static String getCtryCd(String ctryNm) {

        if (ZYPCommonFunc.hasValue(ctryNm)) {
            String ctryCd = NMAL2800Query.getInstance().getCtryCd(ctryNm);
            if (ZYPCommonFunc.hasValue(ctryCd)) {
                return ctryCd;
            }

        }

        return CTRY.UNITED_STATES_OF_AMERICA;

    }

    /**
     * Check if Location Number exist in PROS_PTY_LOC_WRK
     * @param glblCmpyCd String
     * @param locNum String
     * @return boolean
     */
    public static boolean existLocNum(String locNum) {
        // 2017/10/10 QC#20662 mod start
//        PROS_PTY_LOC_WRKTMsg tMsg = new PROS_PTY_LOC_WRKTMsg();
//        tMsg.setSQLID("001");
//        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        tMsg.setConditionValue("locNum01", locNum);
//        tMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
//
//        PROS_PTY_LOC_WRKTMsgArray tMsgArray = (PROS_PTY_LOC_WRKTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
//        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
//            return true;
//        }
//
//        return false;
        S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().checkExistLocNum(locNum, ZYPDateUtil.getSalesDate());
        Integer resCnt = (Integer) ssmResult.getResultObject();
        if (resCnt <= 0) {
            return false;
        }

        return true;
        // 2017/10/10 QC#20662 mod end
    }
    // 2016/12/14 QC#22244 Add Start
    /**
     * Check if Reviw Prospect Number exist in DS_ACCT_RVW_PROS
     * @param rvwProsNum String
     * @return boolean
     */
    public static boolean existRvwProsNum(String rvwProsNum) {
        S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().checkExistRvwProsNum(rvwProsNum);
        Integer resCnt = (Integer) ssmResult.getResultObject();
        if (resCnt <= 0) {
            return false;
        }

        return true;
    }
    // 2016/12/14 QC#22244 Add End
    /**
     * Check if Territory Name exist 
     * @param trtyOrgNm String
     * @return boolean
     */
    public static String existTrtyOrgCd(String trtyOrgNm) {

        return  NMAL2800Query.getInstance().getTrtyOrgCd(trtyOrgNm);

    }
    // 2016/07/26 QC#12417 Add End

    // Add Start 2018/01/16 QC#23148
    /**
     * Get Country Code for upload / download
     * @param ctryNm String
     * @return String
     */
    public static String getCtryCdForUploadDownload(String ctryNm) {

        if (ZYPCommonFunc.hasValue(ctryNm)) {
            String ctryCd = NMAL2800Query.getInstance().getCtryCd(ctryNm);
            if (ZYPCommonFunc.hasValue(ctryCd)) {
                return ctryCd;
            }
        }

        return null;
    }

    /**
     * getCntyNmForDownload
     * @param glblCmpyCd
     * @param rs
     * @return String
     * @throws SQLException
     */
    public static String getCntyNmForDownload(String glblCmpyCd, ResultSet rs) throws SQLException {

        String befLocFirstLineAddr = rs.getString("BEF_LOC_FIRST_LINE_ADDR");
        String befLocScdLineAddr = rs.getString("BEF_LOC_SCD_LINE_ADDR");
        String befShipToCtyAddr = rs.getString("BEF_SHIP_TO_CTY_ADDR");
        String befShipToStNm = rs.getString("BEF_SHIP_TO_ST_NM");
        String befShipToPostCd = rs.getString("BEF_SHIP_TO_POST_CD");
        String befCtryNm = rs.getString("BEF_CTRY_NM");

        if (!ZYPCommonFunc.hasValue(befLocFirstLineAddr) || //
                !ZYPCommonFunc.hasValue(befShipToCtyAddr) || //
                !ZYPCommonFunc.hasValue(befShipToStNm) || //
                !ZYPCommonFunc.hasValue(befShipToPostCd) || //
                !ZYPCommonFunc.hasValue(befCtryNm)) {
            return null;
        }

        String ctryCd = getCtryCdForUploadDownload(befCtryNm);

        if (!ZYPCommonFunc.hasValue(ctryCd)) {
            return null;
        }

        // Address Validation API
        NMZC003001PMsg paramMsg = new NMZC003001PMsg();

        paramMsg.glblCmpyCd.setValue(glblCmpyCd);
        paramMsg.firstLineAddr.setValue(befLocFirstLineAddr);

        if(ZYPCommonFunc.hasValue(befLocScdLineAddr)) {
            paramMsg.scdLineAddr.setValue(befLocScdLineAddr);
        }

        paramMsg.ctyAddr.setValue(befShipToCtyAddr);
        paramMsg.stCd.setValue(befShipToStNm);
        paramMsg.postCd.setValue(befShipToPostCd);
        paramMsg.ctryCd.setValue(ctryCd);

        new NMZC003001().execute(paramMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(paramMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
            if (msgList.size() > 0) {
                return null;
            }
        }

        if (RTRN_CD_ERROR.equals(paramMsg.xxVldStsCd_01.getValue()) || //
                RTRN_CD_ERROR.equals(paramMsg.xxVldStsCd_02.getValue()) || //
                RTRN_CD_ERROR.equals(paramMsg.xxVldStsCd_03.getValue()) || //
                RTRN_CD_ERROR.equals(paramMsg.xxVldStsCd_04.getValue()) || //
                RTRN_CD_ERROR.equals(paramMsg.xxVldStsCd_05.getValue()) || //
                RTRN_CD_ERROR.equals(paramMsg.xxVldStsCd_06.getValue()) || //
                RTRN_CD_ERROR.equals(paramMsg.xxVldStsCd_07.getValue()) || //
                RTRN_CD_SUGGEST.equals(paramMsg.xxVldStsCd_01.getValue()) || //
                RTRN_CD_SUGGEST.equals(paramMsg.xxVldStsCd_02.getValue()) || //
                RTRN_CD_SUGGEST.equals(paramMsg.xxVldStsCd_03.getValue()) || //
                RTRN_CD_SUGGEST.equals(paramMsg.xxVldStsCd_04.getValue()) || //
                RTRN_CD_SUGGEST.equals(paramMsg.xxVldStsCd_05.getValue()) || //
                RTRN_CD_SUGGEST.equals(paramMsg.xxVldStsCd_06.getValue()) || //
                RTRN_CD_SUGGEST.equals(paramMsg.xxVldStsCd_07.getValue())) {
            return null;
        }

        String cntyNm = paramMsg.cntyNm.getValue();

        return cntyNm;
    }

    /**
     * getLineBizTpDescTxt
     * @param befPsnNum String
     * @return Map<String, Object>
     */
    public static String getLineBizTpDescTxt(String befPsnNum) {

        String result = null;

        if (ZYPCommonFunc.hasValue(befPsnNum)) {
            S21SsmEZDResult ssmResult = NMAL2800Query.getInstance().getCurrentOwnerInfo(befPsnNum);
            if (ssmResult.isCodeNormal()) {
                Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
                result = (String) map.get("LINE_BIZ_TP_DESC_TXT");
            }
        }

        return result;
    }
    // Add End 2018/01/16 QC#23148
}
