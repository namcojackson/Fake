/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL2460.common;

import static business.blap.NMAL2460.constant.NMAL2460Constant.BUSINESS_ID;
import static business.blap.NMAL2460.constant.NMAL2460Constant.CSV_HEADER_NUM;
import static business.blap.NMAL2460.constant.NMAL2460Constant.DELETE;
import static business.blap.NMAL2460.constant.NMAL2460Constant.ERROR_CODE_ERROR;
import static business.blap.NMAL2460.constant.NMAL2460Constant.LIMIT_DL_ROWNUM;
import static business.blap.NMAL2460.constant.NMAL2460Constant.NMAM8433E;
import static business.blap.NMAL2460.constant.NMAL2460Constant.NMAM8542E;
import static business.blap.NMAL2460.constant.NMAL2460Constant.NMAM8545E;
import static business.blap.NMAL2460.constant.NMAL2460Constant.NMAM8546E;
import static business.blap.NMAL2460.constant.NMAL2460Constant.NMAM8551E;
import static business.blap.NMAL2460.constant.NMAL2460Constant.NZZM0000E;
import static business.blap.NMAL2460.constant.NMAL2460Constant.NZZM0001W;
import static business.blap.NMAL2460.constant.NMAL2460Constant.SCREEN_ID;
import static business.blap.NMAL2460.constant.NMAL2460Constant.TIME_STAMP_FORMAT;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL2460.NMAL2460CMsg;
import business.blap.NMAL2460.NMAL2460Query;
import business.blap.NMAL2460.NMAL2460SMsg;
import business.blap.NMAL2460.NMAL2460_BCMsg;
import business.blap.NMAL2460.NMAL2460_BSMsg;
import business.blap.NMAL2460.constant.NMAL2460Constant;
import business.db.ACCT_TRTY_RESRC_RQST_DTLTMsg;
import business.db.ACCT_TRTY_RESRC_RQST_HDRTMsg;
import business.db.BIZ_AREA_ORGTMsg;
import business.db.BIZ_AREA_ORGTMsgArray;
import business.db.STTMsg;
import business.db.STTMsgArray;
import business.file.NMAL2460F00FMsg;
import business.file.NMAL2460F01FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002Query;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASG_TRTY_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_CRAT_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_RSLT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SRCH_LYOT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Account Owner Lookup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/27   Hitachi         O.Okuma         Create          N/A
 * 2016/09/13   Fujitsu         C.Yokoi         Update          QC#10091
 * 2016/09/21   Fujitsu         Mz.Takahashi    Update          QC#11068
 * 2017/08/31   Fujitsu         S.Takami        Update          S21_NA#20174
 * 2017/09/13   Fujitsu         S.Takami        Update          S21_NA#21045 (Rollback S21_NA#20174)
 * 2017/09/13   Fujitsu         W.Honda         Update          S21_NA#21044
 * 2017/10/16   Hitachi         J.Kim           Update          QC#21299
 * 2017/10/19   Fujitsu         M.Ohno          Update          QC#21631
 * 2017/11/27   Fujitsu         N.Sugiura       Update          CSA-QC#20597
 * 2017/11/28   Fujitsu         Hd.Sugawara     Update          QC#21044
 * 2018/08/25   Fujitsu         T.Aoi           Update          QC#27457
 *</pre>
 */
public class NMAL2460CommonLogic {

    private static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * Clear Message
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     */
    public static void clearMsg(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg) {
        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.C);
    }

    /**
     * Create Pull Down
     * @param cMsg NMAL2460CMsg
     */
    public static void createPullDown(NMAL2460CMsg cMsg) {
        // Account Category
        ZYPCodeDataUtil.createPulldownList(DS_ACCT_TP.class, cMsg.dsAcctTpCd_L, cMsg.dsAcctTpDescTxt_L);
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctTpCd_H, DS_ACCT_TP.CUSTOMER);

        // Search Layout Type
        ZYPCodeDataUtil.createPulldownList(SRCH_LYOT_TP.class, cMsg.srchLyotTpCd_L, cMsg.srchLyotTpDescTxt_L);

        // Territory Group Type
        ZYPCodeDataUtil.createPulldownList(TRTY_GRP_TP.class, cMsg.trtyGrpTpCd_L, cMsg.trtyGrpTpDescTxt_L);

        // Line Business Type
        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, cMsg.lineBizTpCd_L, cMsg.lineBizTpDescTxt_L);

        // State
        // 2018/08/25 QC#27457 Mod Start
        //ZYPCodeDataUtil.createPulldownList(ST.class, cMsg.stCd_L, cMsg.stDescTxt_L);
        //for (int i = 0; i < cMsg.stCd_L.length(); i++) {
        //    cMsg.stDescTxt_L.no(i).setValue(cMsg.stCd_L.no(i).getValue());
        //}
        STTMsg condition = new STTMsg();
        condition.setSQLID("003");
        condition.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        condition.setConditionValue("ctryCd01", CTRY.UNITED_STATES_OF_AMERICA);

        STTMsgArray tMsgArray = (STTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            for (int i = 0; i < tMsgArray.getValidCount(); i++) {
                cMsg.stCd_L.no(i).setValue(tMsgArray.no(i).stCd.getValue());
                cMsg.stDescTxt_L.no(i).setValue(tMsgArray.no(i).stCd.getValue());
            }
        }
        // 2018/08/25 QC#27457 Mod End

        // Business Area
        BIZ_AREA_ORGTMsg tMsg = new BIZ_AREA_ORGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ezCancelFlag, "0");
        ZYPEZDItemValueSetter.setValue(tMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
        ZYPEZDItemValueSetter.setValue(tMsg.slsFlg, ZYPConstant.FLG_ON_Y);

        BIZ_AREA_ORGTMsgArray tMsgAry = (BIZ_AREA_ORGTMsgArray) ZYPCodeDataUtil.findByCondition(tMsg);
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "bizAreaOrgCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "bizAreaOrgDescTxt");

        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.bizAreaOrgCd_L, cMsg.bizAreaOrgDescTxt_L);
    }

    /**
     * get LineBizRoleTp
     * @param cMsg NMAL2460CMsg
     */
    public static List<Map<String, Object>> getLineBizRoleTp() {
        S21SsmEZDResult ssmResult = NMAL2460Query.getInstance().getLineBizRoleTp();

        if (ssmResult.isCodeNormal()) {
            return (List<Map<String, Object>>) ssmResult.getResultObject();
        }
        return null;
    }

    /**
     * set Title Label
     * @param cMsg NMAL2460CMsg
     */
    public static void setTitleLabel(NMAL2460CMsg cMsg) {
        S21SsmEZDResult ssmResult = NMAL2460Query.getInstance().getTitleLabel(cMsg.dsAcctTpCd_H.getValue());

        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> titleList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (Map<String, Object> titleMap : titleList) {
                if (ASG_TRTY_ATTRB.ATTRIBUTE1_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_01, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_01, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE2_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_02, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_02, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE3_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_03, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_03, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE4_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_04, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_04, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE5_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_05, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_05, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE6_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_06, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_06, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE7_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_07, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_07, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE8_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_08, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_08, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE9_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_09, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_09, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE10_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_10, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_10, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE11_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_11, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_11, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE12_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_12, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_12, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE13_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_13, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_13, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE14_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_14, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_14, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE15_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_15, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_15, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE16_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_16, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_16, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE17_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_17, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_17, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE18_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_18, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_18, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE19_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_19, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_19, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                } else if (ASG_TRTY_ATTRB.ATTRIBUTE20_ID.equals((String) titleMap.get("ASG_TRTY_ATTRB_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.lineBizRoleTpNm_20, (String) titleMap.get("LINE_BIZ_ROLE_TP_NM"));
                    // Mod Start 2017/11/28 QC#21044
                    ZYPEZDItemValueSetter.setValue(cMsg.slsCrQuotFlg_20, (String) titleMap.get("SLS_CR_QUOT_FLG"));
                    // Mod End 2017/11/28 QC#21044
                }
            }
        }
    }

    /**
     * clear Search Result
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     */
    public static void clearSearchResult(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg) {
        cMsg.srchLyotTpCd.clear();
        cMsg.rqstRsltCmntTxt.clear();
        if (cMsg.A.getValidCount() > 0) {
            cMsg.A.clear();
            cMsg.A.setValidCount(0);
            sMsg.A.clear();
            sMsg.A.setValidCount(0);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_A, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_A, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_A, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowCurNum_A, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowTotNum_A, BigDecimal.ZERO);
        }

        if (cMsg.B.getValidCount() > 0) {
            cMsg.B.clear();
            cMsg.B.setValidCount(0);
            sMsg.B.clear();
            sMsg.B.setValidCount(0);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_B, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_B, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_B, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowCurNum_B, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowTotNum_B, BigDecimal.ZERO);
        }
    }

    /**
     * setPagenation
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     */
    public static void setPagenation(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchLyotTpCd)) {
            return;
        }

        if (SRCH_LYOT_TP.LAYOUT_1.equals(cMsg.srchLyotTpCd.getValue())) {
            int pageFromNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
            setPagenationForA(cMsg, sMsg, pageFromNum);
        } else {
            int pageFromNum = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
            setPagenationForB(cMsg, sMsg, pageFromNum);
        }
    }

    /**
     * defineTerritoryName
     * @param bcMsg NMAL2460_BCMsg
     * @param scrnColumnidx String
     * @return String
     */
    public static String defineTerritoryName(NMAL2460_BCMsg bcMsg, String scrnColumnidx) {
        if (ASG_TRTY_ATTRB.ATTRIBUTE1_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_B1.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE2_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_B2.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE3_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_B3.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE4_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_B4.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE5_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_B5.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE6_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_B6.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE7_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_B7.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE8_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_B8.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE9_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_B9.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE10_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_BA.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE11_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_BB.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE12_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_BC.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE13_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_BD.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE14_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_BE.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE15_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_BF.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE16_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_BG.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE17_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_BH.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE18_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_BI.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE19_ID.equals(scrnColumnidx)) {
            return bcMsg.orgNm_BJ.getValue();
        }
        return bcMsg.orgNm_BK.getValue();
    }

    // Add Start 2017/11/28 QC#21044
    /**
     * defineNonSalesRepFlag
     * @param cMsg NMAL2460CMsg
     * @param scrnColumnidx String
     * @return String
     */
    public static String defineNonSalesRepFlag(NMAL2460CMsg cMsg) {
        String slsCrQuotFlg = null;
        String scrnColumnidx = cMsg.asgTrtyAttrbCd.getValue();

        if (ASG_TRTY_ATTRB.ATTRIBUTE1_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_01.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE2_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_02.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE3_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_03.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE4_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_04.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE5_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_05.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE6_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_06.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE7_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_07.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE8_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_08.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE9_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_09.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE10_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_10.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE11_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_11.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE12_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_12.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE13_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_13.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE14_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_14.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE15_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_15.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE16_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_16.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE17_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_17.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE18_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_18.getValue();
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE19_ID.equals(scrnColumnidx)) {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_19.getValue();
        } else {
            slsCrQuotFlg = cMsg.slsCrQuotFlg_20.getValue();
        }

        String nonSlsRepFlg = null;
        if (ZYPConstant.FLG_ON_Y.equals(slsCrQuotFlg)) {
            // If LINE_BIZ_ROLE_TP.SLS_CR_QUOT_FLG = 'Y', Sales Rep Territory.
            // -> DS_ORG_RESRC_RELN.NON_SLS_REP_FLG = 'N'
            nonSlsRepFlg = ZYPConstant.FLG_OFF_N;
        } else if (ZYPConstant.FLG_OFF_N.equals(slsCrQuotFlg)) {
            // If LINE_BIZ_ROLE_TP.SLS_CR_QUOT_FLG = 'N', Specialist Territory.
            // -> DS_ORG_RESRC_RELN.NON_SLS_REP_FLG = 'Y'
            nonSlsRepFlg = ZYPConstant.FLG_ON_Y;
        }

        return nonSlsRepFlg;
    }
    // Add End 2017/11/28 QC#21044

    /**
     * setMessageToTerritoryName
     * @param bsMsg bsMsg
     * @param scrnColumnidx scrnColumnidx
     * @param scrnColumnidx scrnColumnidx
     * @param messageCode String
     */
    public static void setMessageToTerritoryName(NMAL2460_BSMsg bsMsg, String scrnColumnidx, int errorCode, String messageCode, String[] messageArgs) {
        if (ASG_TRTY_ATTRB.ATTRIBUTE1_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_B1.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_B1.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE2_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_B2.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_B2.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE3_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_B3.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_B3.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE4_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_B4.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_B4.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE5_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_B5.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_B5.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE6_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_B6.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_B6.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE7_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_B7.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_B7.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE8_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_B8.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_B8.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE9_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_B9.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_B9.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE10_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_BA.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_BA.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE11_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_BB.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_BB.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE12_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_BC.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_BC.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE13_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_BD.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_BD.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE14_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_BE.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_BE.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE15_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_BF.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_BF.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE16_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_BG.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_BG.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE17_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_BH.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_BH.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE18_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_BI.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_BI.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE19_ID.equals(scrnColumnidx)) {
            if (messageArgs == null) {
                bsMsg.orgNm_BJ.setErrorInfo(errorCode, messageCode);
            } else {
                bsMsg.orgNm_BJ.setErrorInfo(errorCode, messageCode, messageArgs);
            }
            return;
        }
        bsMsg.orgNm_BK.setErrorInfo(errorCode, messageCode);
    }

    /**
     * setTerritoryDetail
     * @param bcMsg NMAL2460_BCMsg
     * @param resultMap Map<String, Object>
     * @param scrnClmnIdx String
     */
    public static void setTerritoryDetail(NMAL2460_BCMsg bcMsg, Map<String, Object> resultMap, String scrnClmnIdx) {
        if (ASG_TRTY_ATTRB.ATTRIBUTE1_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_B1, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_B1, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_B1, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_B1, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_B1, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_B1, (String) resultMap.get("PSN_NAME"));
            return;

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE2_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_B2, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_B2, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_B2, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_B2, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_B2, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_B2, (String) resultMap.get("PSN_NAME"));
            return;

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE3_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_B3, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_B3, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_B3, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_B3, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_B3, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_B3, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE4_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_B4, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_B4, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_B4, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_B4, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_B4, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_B4, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE5_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_B5, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_B5, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_B5, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_B5, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_B5, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_B5, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE6_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_B6, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_B6, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_B6, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_B6, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_B6, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_B6, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE7_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_B7, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_B7, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_B7, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_B7, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_B7, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_B7, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE8_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_B8, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_B8, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_B8, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_B8, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_B8, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_B8, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE9_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_B9, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_B9, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_B9, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_B9, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_B9, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_B9, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE10_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_BA, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_BA, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_BA, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_BA, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_BA, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_BA, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE11_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_BB, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_BB, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_BB, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_BB, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_BB, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_BB, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE12_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_BC, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_BC, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_BC, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_BC, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_BC, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_BC, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE13_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_BD, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_BD, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_BD, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_BD, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_BD, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_BD, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE14_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_BE, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_BE, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_BE, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_BE, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_BE, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_BE, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE15_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_BF, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_BF, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_BF, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_BF, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_BF, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_BF, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE16_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_BG, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_BG, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_BG, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_BG, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_BG, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_BG, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE17_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_BH, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_BH, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_BH, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_BH, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_BH, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_BH, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE18_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_BI, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_BI, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_BI, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_BI, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_BI, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_BI, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE19_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_BJ, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_BJ, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_BJ, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_BJ, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_BJ, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_BJ, (String) resultMap.get("PSN_NAME"));
            return;
        }
        ZYPEZDItemValueSetter.setValue(bcMsg.acctTrtyOrgCd_BK, (String) resultMap.get("ORG_CD"));
        ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnCd_BK, (String) resultMap.get("PSN_CD"));
        ZYPEZDItemValueSetter.setValue(bcMsg.trtyGrpTpDescTxt_BK, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bcMsg.orgNm_BK, (String) resultMap.get("ORG_NM"));
        ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNum_BK, (String) resultMap.get("PSN_NUM"));
        ZYPEZDItemValueSetter.setValue(bcMsg.xxAcctTrtyPsnNm_BK, (String) resultMap.get("PSN_NAME"));
        return;
    }

    /**
     * set Territory Detail To BSMsg
     * @param bsMsg NMAL2460_BSMsg
     * @param resultMap Map<String, Object>
     * @param scrnClmnIdx String
     */
    public static void setTerritoryDetailToBSMsg(NMAL2460_BSMsg bsMsg, Map<String, Object> resultMap, String scrnClmnIdx) {
        if (ASG_TRTY_ATTRB.ATTRIBUTE1_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_B1, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_B1, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_B1, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_B1, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_B1, (String) resultMap.get("PSN_NAME"));
            return;

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE2_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_B2, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_B2, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_B2, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_B2, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_B2, (String) resultMap.get("PSN_NAME"));
            return;

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE3_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_B3, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_B3, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_B3, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_B3, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_B3, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE4_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_B4, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_B4, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_B4, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_B4, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_B4, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE5_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_B5, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_B5, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_B5, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_B5, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_B5, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE6_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_B6, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_B6, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_B6, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_B6, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_B6, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE7_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_B7, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_B7, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_B7, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.orgNm_B7, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_B7, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_B7, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE8_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_B8, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_B8, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_B8, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.orgNm_B8, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_B8, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_B8, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE9_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_B9, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_B9, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_B9, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_B9, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_B9, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE10_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_BA, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_BA, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_BA, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_BA, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_BA, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE11_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_BB, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_BB, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_BB, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_BB, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_BB, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE12_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_BC, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_BC, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_BC, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_BC, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_BC, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE13_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_BD, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_BD, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_BD, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_BD, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_BD, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE14_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_BE, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_BE, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_BE, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.orgNm_BE, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_BE, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_BE, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE15_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_BF, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_BF, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_BF, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_BF, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_BF, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE16_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_BG, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_BG, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_BG, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_BG, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_BG, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE17_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_BH, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_BH, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_BH, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.orgNm_BH, (String) resultMap.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_BH, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_BH, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE18_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_BI, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_BI, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_BI, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_BI, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_BI, (String) resultMap.get("PSN_NAME"));
            return;
        } else if (ASG_TRTY_ATTRB.ATTRIBUTE19_ID.equals(scrnClmnIdx)) {
            ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_BJ, (String) resultMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_BJ, (String) resultMap.get("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_BJ, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_BJ, (String) resultMap.get("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_BJ, (String) resultMap.get("PSN_NAME"));
            return;
        }
        ZYPEZDItemValueSetter.setValue(bsMsg.acctTrtyOrgCd_BK, (String) resultMap.get("ORG_CD"));
        ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnCd_BK, (String) resultMap.get("PSN_CD"));
        ZYPEZDItemValueSetter.setValue(bsMsg.trtyGrpTpDescTxt_BK, (String) resultMap.get("TRTY_GRP_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNum_BK, (String) resultMap.get("PSN_NUM"));
        ZYPEZDItemValueSetter.setValue(bsMsg.xxAcctTrtyPsnNm_BK, (String) resultMap.get("PSN_NAME"));
        return;
    }

    /**
     * setPagenationForA
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     * @param pagenationFrom int
     */
    private static void setPagenationForA(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * setPagenationForB
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     * @param pagenationFrom int
     */
    private static void setPagenationForB(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.B.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.B.getValidCount()) {
                EZDMsg.copy(cMsg.B.get(cnt - pagenationFrom), null, sMsg.B.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param isNext boolean
     */
    public static void pagenation(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg, boolean isNext) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchLyotTpCd)) {
            cMsg.A.setValidCount(0);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_A, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_A, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_A, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowCurNum_A, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowTotNum_A, BigDecimal.ZERO);

            cMsg.B.setValidCount(0);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_B, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_B, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_B, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowCurNum_B, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowTotNum_B, BigDecimal.ZERO);

            return;
        }

        int pagenationFrom = 0;
        if (SRCH_LYOT_TP.LAYOUT_1.equals(cMsg.srchLyotTpCd.getValue())) {
            if (cMsg.xxPageShowFromNum_A.getValueInt() == 0) {
                pagenationForA(cMsg, sMsg, 0);
                return;
            }

            if (isNext) {
                pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt() + cMsg.A.length() - 1;
            } else {
                pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt() - cMsg.A.length() - 1;
            }
            pagenationForA(cMsg, sMsg, pagenationFrom);
        } else {
            if (cMsg.xxPageShowFromNum_B.getValueInt() == 0) {
                pagenationForB(cMsg, sMsg, 0);
                return;
            }

            if (isNext) {
                pagenationFrom = cMsg.xxPageShowFromNum_B.getValueInt() + cMsg.B.length() - 1;
            } else {
                pagenationFrom = cMsg.xxPageShowFromNum_B.getValueInt() - cMsg.B.length() - 1;
            }
            pagenationForB(cMsg, sMsg, pagenationFrom);
        }
    }

    /**
     * pagenationForJump <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void pagenationForJump(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg) {

        int pagenationFrom = 0;
        if (SRCH_LYOT_TP.LAYOUT_1.equals(cMsg.srchLyotTpCd.getValue())) {
            pagenationFrom = convertPageNumToFirstRowIndexForA(cMsg);
            pagenationForA(cMsg, sMsg, pagenationFrom);
        } else {
            pagenationFrom = convertPageNumToFirstRowIndexForB(cMsg);
            pagenationForB(cMsg, sMsg, pagenationFrom);
        }
    }

    /**
     * pagenationForA
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param itemIndex int
     */
    private static void pagenationForA(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg, int itemIndex) {

        int startIndex = (itemIndex / cMsg.A.length()) * cMsg.A.length();
        int num = 0;
        for (int i = startIndex; i < sMsg.A.getValidCount(); i++) {
            if (num >= cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(num), null);
            num++;
        }
        cMsg.A.setValidCount(num);
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_A, BigDecimal.valueOf(startIndex + 1));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_A, BigDecimal.valueOf(startIndex + cMsg.A.getValidCount()));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_A, BigDecimal.valueOf(sMsg.A.getValidCount()));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowCurNum_A, BigDecimal.valueOf(startIndex + 1).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowTotNum_A, BigDecimal.valueOf(sMsg.A.getValidCount()).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
    }

    /**
     * pagenationForB
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param itemIndex int
     */
    private static void pagenationForB(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg, int itemIndex) {

        int startIndex = (itemIndex / cMsg.B.length()) * cMsg.B.length();
        int num = 0;
        for (int i = startIndex; i < sMsg.B.getValidCount(); i++) {
            if (num >= cMsg.B.length()) {
                break;
            }
            EZDMsg.copy(sMsg.B.get(i), null, cMsg.B.get(num), null);
            num++;
        }
        cMsg.B.setValidCount(num);
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_B, BigDecimal.valueOf(startIndex + 1));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_B, BigDecimal.valueOf(startIndex + cMsg.B.getValidCount()));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_B, BigDecimal.valueOf(sMsg.B.getValidCount()));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowCurNum_B, BigDecimal.valueOf(startIndex + 1).divide(BigDecimal.valueOf(cMsg.B.length()), 0, BigDecimal.ROUND_UP));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowTotNum_B, BigDecimal.valueOf(sMsg.B.getValidCount()).divide(BigDecimal.valueOf(cMsg.B.length()), 0, BigDecimal.ROUND_UP));
    }

    /**
     * convertPageNumToFirstRowIndexForA
     * @param cMsg NMAL2460CMsg
     * @return int
     */
    private static int convertPageNumToFirstRowIndexForA(NMAL2460CMsg cMsg) {
        if (cMsg.A.length() * (cMsg.xxPageShowCurNum_A.getValueInt() - 1) > (cMsg.xxPageShowOfNum_A.getValueInt())) {
            return cMsg.xxPageShowCurNum_A.getValueInt() - 1;
        }
        return cMsg.A.length() * (cMsg.xxPageShowCurNum_A.getValueInt() - 1);
    }

    private static int convertPageNumToFirstRowIndexForB(NMAL2460CMsg cMsg) {
        if (cMsg.B.length() * (cMsg.xxPageShowCurNum_B.getValueInt() - 1) > (cMsg.xxPageShowOfNum_B.getValueInt())) {
            return cMsg.xxPageShowCurNum_B.getValueInt() - 1;
        }
        return cMsg.B.length() * (cMsg.xxPageShowCurNum_B.getValueInt() - 1);
    }

    /**
     * writeCsvFile for Layout1
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFileForLayout1(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg, ResultSet rs) throws SQLException {

        NMAL2460F00FMsg fMsg = new NMAL2460F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(new String[] {"LocationID", "Account#", "Account Category", "Account Name", "Address", "City", "State", "Postal Code", "Territory", "LOB", "LOB Role", "Resource#", "ResourceName", "EmployeeID",
                "Creation Date", "SIC Code", "Classification", "Account Group Name1", "Account Group Name2", "Account Group Name3", "Account Group Name4", "Account Group Name5", "Manual Entry", "Organization Name", "Organization ID" });

        if (!rs.next()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        // write contents
        do {
            if (rs.getRow() >= LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(NZZM0001W, null);
                break;
            }
            // resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.locNum_C, rs.getString("LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum_C, rs.getString("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctTpDescTxt_C, rs.getString("DS_ACCT_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_C, rs.getString("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxAllLineAddr_C, rs.getString("ADDRESS"));
            ZYPEZDItemValueSetter.setValue(fMsg.ctyAddr_C, rs.getString("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.stNm_C, rs.getString("ST_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.postCd_C, rs.getString("POST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.orgNm_C1, rs.getString("TRTY_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.trtyGrpTpDescTxt_C, rs.getString("TRTY_GRP_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.lineBizRoleTpDescTxt_C, rs.getString("LINE_BIZ_ROLE_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.psnNum_C, rs.getString("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxPsnFirstMidLastNm_C, rs.getString("PSN_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.psnCd_C, rs.getString("PSN_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxCratDt_C, rs.getString("EZINTIME"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsCustSicCd_C, rs.getString("DS_CUST_SIC_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctClsDescTxt_C, rs.getString("DS_ACCT_CLS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.firstDsAcctGrpTpCd_C, rs.getString("FIRST_DS_ACCT_GRP_TP_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.scdDsAcctGrpTpCd_C, rs.getString("SCD_DS_ACCT_GRP_TP_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.thirdDsAcctGrpTpCd_C, rs.getString("THIRD_DS_ACCT_GRP_TP_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.frthDsAcctGrpTpCd_C, rs.getString("FRTH_DS_ACCT_GRP_TP_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.fifthDsAcctGrpTpCd_C, rs.getString("FIFTH_DS_ACCT_GRP_TP_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.manEntryFlg_C, rs.getString("MAN_ENTRY_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.orgNm_C2, rs.getString("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.orgCd_C, rs.getString("ORG_CD"));
            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * createCsvFileHeaderNameArray
     * @param glblCmpyCd String
     * @return String[]
     */
    public static String[] createCsvFileHeaderNameArray(String glblCmpyCd) {

         List<String> hdrElementList = new ArrayList<String>();
         // START 2017/10/16 J.Kim [QC#21299,MOD]
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_LOC_NUM", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ACCT_NUM", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ACCT_CATG", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ACCT_NM", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ADDR", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_CTY", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ST", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_POST", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_CRAT_DT", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_SIC_CD", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_CLS_DESC_TXT", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_NM_01", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_NM_02", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_NM_03", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_NM_04", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_NM_05", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_MAN_FLG", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_01", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_01", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_01", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_01", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_01", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_01", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_02", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_02", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_02", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_02", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_02", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_02", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_03", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_03", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_03", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_03", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_03", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_03", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_04", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_04", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_04", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_04", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_04", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_04", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_05", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_05", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_05", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_05", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_05", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_05", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_06", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_06", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_06", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_06", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_06", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_06", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_07", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_07", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_07", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_07", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_07", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_07", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_08", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_08", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_08", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_08", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_08", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_08", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_09", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_09", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_09", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_09", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_09", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_09", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_10", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_10", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_10", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_10", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_10", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_10", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_11", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_11", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_11", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_11", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_11", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_11", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_12", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_12", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_12", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_12", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_12", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_12", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_13", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_13", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_13", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_13", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_13", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_13", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_14", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_14", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_14", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_14", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_14", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_14", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_15", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_15", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_15", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_15", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_15", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_15", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_16", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_16", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_16", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_16", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_16", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_16", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_17", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_17", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_17", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_17", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_17", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_17", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_18", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_18", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_18", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_18", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_18", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_18", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_19", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_19", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_19", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_19", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_19", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_19", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_CD_20", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_GRP_TP_NM_20", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_ORG_NM_20", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_CD_20", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NM_20", glblCmpyCd));
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_PSN_NUM_20", glblCmpyCd));
         // END 2017/10/16 J.Kim [QC#21299,MOD]
         hdrElementList.add(ZYPCodeDataUtil.getVarCharConstValue("NMAL2460_CSV_HDR_CMNT", glblCmpyCd));

        return hdrElementList.toArray(new String[hdrElementList.size()]);
    }

    /**
     * hasChangeTeritoryAttribute
     * @param bsMsg NMAL2460_BSMsg
     * @return boolean
     */
    public static boolean hasChangeTeritoryAttribute(NMAL2460_BSMsg bsMsg) {
        if (isChanged(bsMsg.orgNm_O1.getValue(), bsMsg.orgNm_B1.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_O2.getValue(), bsMsg.orgNm_B2.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_O3.getValue(), bsMsg.orgNm_B3.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_O4.getValue(), bsMsg.orgNm_B4.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_O5.getValue(), bsMsg.orgNm_B5.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_O6.getValue(), bsMsg.orgNm_B6.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_O7.getValue(), bsMsg.orgNm_B7.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_O8.getValue(), bsMsg.orgNm_B8.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_O9.getValue(), bsMsg.orgNm_B9.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_OA.getValue(), bsMsg.orgNm_BA.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_OB.getValue(), bsMsg.orgNm_BB.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_OC.getValue(), bsMsg.orgNm_BC.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_OD.getValue(), bsMsg.orgNm_BD.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_OE.getValue(), bsMsg.orgNm_BE.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_OF.getValue(), bsMsg.orgNm_BF.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_OG.getValue(), bsMsg.orgNm_BG.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_OH.getValue(), bsMsg.orgNm_BH.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_OI.getValue(), bsMsg.orgNm_BI.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_OJ.getValue(), bsMsg.orgNm_BJ.getValue(), false)) {
            return true;
        }
        if (isChanged(bsMsg.orgNm_OK.getValue(), bsMsg.orgNm_BK.getValue(), false)) {
            return true;
        }
        return false;
    }

    /**
     * writeCsvFile for Layout2
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     * @param rs ResultSet
     * @param glblCmpyCd String
     * @throws SQLException SQLException
     */
    public static void writeCsvFileForLayout2(NMAL2460CMsg cMsg, NMAL2460SMsg sMsg, ResultSet rs, String glblCmpyCd) throws SQLException {

        NMAL2460F01FMsg fMsg = new NMAL2460F01FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        String[] csvHeader = NMAL2460CommonLogic.createCsvFileHeaderNameArray(glblCmpyCd);
        if (!checkCsvHeader(csvHeader)) {
            cMsg.setMessageInfo(NMAM8433E, new String[] {"CSV Header" });
            return;
        }

        csvOutFile.writeHeader(csvHeader);

        if (!rs.next()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }
        do {
            if (rs.getRow() >= LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(NZZM0001W, null);
                break;
            }
            // resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctTpDescTxt, rs.getString("DS_ACCT_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.locNum, rs.getString("LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyResrcAddr, S21StringUtil.subStringByLength(rs.getString("ADDRESS"), 1, 240));
            ZYPEZDItemValueSetter.setValue(fMsg.ctyAddr, rs.getString("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.stCd, rs.getString("ST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.postCd, rs.getString("POST_CD"));
            // START 2017/10/03 J.Kim [QC#21299,DEL]
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_01, rs.getString("ORG_NM_01"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_02, rs.getString("ORG_NM_02"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_03, rs.getString("ORG_NM_03"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_04, rs.getString("ORG_NM_04"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_05, rs.getString("ORG_NM_05"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_06, rs.getString("ORG_NM_06"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_07, rs.getString("ORG_NM_07"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_08, rs.getString("ORG_NM_08"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_09, rs.getString("ORG_NM_09"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_10, rs.getString("ORG_NM_10"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_11, rs.getString("ORG_NM_11"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_12, rs.getString("ORG_NM_12"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_13, rs.getString("ORG_NM_13"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_14, rs.getString("ORG_NM_14"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_15, rs.getString("ORG_NM_15"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_16, rs.getString("ORG_NM_16"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_17, rs.getString("ORG_NM_17"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_18, rs.getString("ORG_NM_18"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_19, rs.getString("ORG_NM_19"));
            // ZYPEZDItemValueSetter.setValue(fMsg.befAcctTrtyOrgNm_20, rs.getString("ORG_NM_20"));
            // END 2017/10/03 J.Kim [QC#21299,DEL]
            // START 2017/10/03 J.Kim [QC#21299,ADD]
            // <Creation Date>
            ZYPEZDItemValueSetter.setValue(fMsg.xxCratDt, rs.getString("EZINTIME"));
            // <SIC Code>
            ZYPEZDItemValueSetter.setValue(fMsg.dsCustSicCd, rs.getString("DS_CUST_SIC_CD"));
            // <Classification>
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctClsDescTxt, rs.getString("DS_ACCT_CLS_DESC_TXT"));
            // <Account Group Name1>
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctGrpNm_01, rs.getString("FIRST_DS_ACCT_GRP_TP_CD"));
            // <Account Group Name2>
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctGrpNm_02, rs.getString("SCD_DS_ACCT_GRP_TP_CD"));
            // <Account Group Name3>
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctGrpNm_03, rs.getString("THIRD_DS_ACCT_GRP_TP_CD"));
            // <Account Group Name4>
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctGrpNm_04, rs.getString("FRTH_DS_ACCT_GRP_TP_CD"));
            // <Account Group Name5>
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctGrpNm_05, rs.getString("FIFTH_DS_ACCT_GRP_TP_CD"));
            // (Uploadble)<Manual Entry>(<Alphabet Numeric Character>(1))
            ZYPEZDItemValueSetter.setValue(fMsg.manEntryFlg, rs.getString("MAN_ENTRY_FLG"));
            // <Organization>
            ZYPEZDItemValueSetter.setValue(fMsg.orgCd, rs.getString("ORG_CD"));
            // <Organization ID>
            ZYPEZDItemValueSetter.setValue(fMsg.orgNm, rs.getString("ORG_NM"));
            // <Account Manager Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_01, rs.getString("ORG_CD_01"));
            // <Account Manager Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_01, rs.getString("TRTY_GRP_TP_DESC_TXT_01"));
            // (Uploadable)<Account Manager Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_01, rs.getString("ORG_NM_01"));
            // <Account Manager Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_01, rs.getString("PSN_NUM_01"));
            // <Account Manager Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_01, rs.getString("PSN_NM_01"));
            // <Account Manager Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_01, rs.getString("PSN_CD_01"));
            // <EMSD Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_02, rs.getString("ORG_CD_02"));
            // <EMSD Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_02, rs.getString("TRTY_GRP_TP_DESC_TXT_02"));
            // (Uploadable)<EMSD Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_02, rs.getString("ORG_NM_02"));
            // <EMSD Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_02, rs.getString("PSN_CD_02"));
            // <EMSD Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_02, rs.getString("PSN_NM_02"));
            // <EMSD Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_02, rs.getString("PSN_NUM_02"));
            // <ESS Writer Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_03, rs.getString("ORG_CD_03"));
            // <ESS Writer Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_03, rs.getString("TRTY_GRP_TP_DESC_TXT_03"));
            // (Uploadable)<ESS Writer Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_03, rs.getString("ORG_NM_03"));
            // <ESS Writer Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_03, rs.getString("PSN_CD_03"));
            // <ESS Writer Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_03, rs.getString("PSN_NM_03"));
            // <ESS Writer Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_03, rs.getString("PSN_NUM_03"));
            // <ESS Installer Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_04, rs.getString("ORG_CD_04"));
            // <ESS Installer Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_04, rs.getString("TRTY_GRP_TP_DESC_TXT_04"));
            // (Uploadable)<ESS Installer Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_04, rs.getString("ORG_NM_04"));
            // <ESS Installer Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_04, rs.getString("PSN_CD_04"));
            // <ESS Installer Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_04, rs.getString("PSN_NM_04"));
            // <ESS Installer Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_04, rs.getString("PSN_NUM_04"));
            // <PPS Writer Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_05, rs.getString("ORG_CD_05"));
            // <PPS Writer Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_05, rs.getString("TRTY_GRP_TP_DESC_TXT_05"));
            // (Uploadable)<PPS Writer Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_05, rs.getString("ORG_NM_05"));
            // <PPS Writer Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_05, rs.getString("PSN_CD_05"));
            // <PPS Writer Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_05, rs.getString("PSN_NM_05"));
            // <PPS Writer Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_05, rs.getString("PSN_NUM_05"));
            // <PPS Installer Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_06, rs.getString("ORG_CD_06"));
            // <PPS Installer Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_06, rs.getString("TRTY_GRP_TP_DESC_TXT_06"));
            // (Uploadable)<PPS Installer Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_06, rs.getString("ORG_NM_06"));
            // <PPS Installer Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_06, rs.getString("PSN_CD_06"));
            // <PPS Installer Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_06, rs.getString("PSN_NM_06"));
            // <PPS Installer Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_06, rs.getString("PSN_NUM_06"));
            // <LFS Writer Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_07, rs.getString("ORG_CD_07"));
            // <LFS Writer Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_07, rs.getString("TRTY_GRP_TP_DESC_TXT_07"));
            // (Uploadable)<LFS Writer Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_07, rs.getString("ORG_NM_07"));
            // <LFS Writer Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_07, rs.getString("PSN_CD_07"));
            // <LFS Writer Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_07, rs.getString("PSN_NM_07"));
            // <LFS Writer Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_07, rs.getString("PSN_NUM_07"));
            // <LFS Installer Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_08, rs.getString("ORG_CD_08"));
            // <LFS Installer Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_08, rs.getString("TRTY_GRP_TP_DESC_TXT_08"));
            // (Uploadable)<LFS Installer Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_08, rs.getString("ORG_NM_08"));
            // <LFS Installer Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_08, rs.getString("PSN_CD_08"));
            // <LFS Installer Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_08, rs.getString("PSN_NM_08"));
            // <LFS Installer Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_08, rs.getString("PSN_NUM_08"));
            // <Specialist SPE Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_09, rs.getString("ORG_CD_09"));
            // <Specialist SPE Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_09, rs.getString("TRTY_GRP_TP_DESC_TXT_09"));
            // (Uploadable)<Specialist SPE Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_09, rs.getString("ORG_NM_09"));
            // <Specialist SPE Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_09, rs.getString("PSN_CD_09"));
            // <Specialist SPE Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_09, rs.getString("PSN_NM_09"));
            // <Specialist SPE Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_09, rs.getString("PSN_NUM_09"));
            // <Specialist SPP Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_10, rs.getString("ORG_CD_10"));
            // <Specialist SPP Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_10, rs.getString("TRTY_GRP_TP_DESC_TXT_10"));
            // (Uploadable)<Specialist SPP Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_10, rs.getString("ORG_NM_10"));
            // <Specialist SPP Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_10, rs.getString("PSN_CD_10"));
            // <Specialist SPP Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_10, rs.getString("PSN_NM_10"));
            // <Specialist SPP Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_10, rs.getString("PSN_NUM_10"));
            // <Specialist SPR Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_11, rs.getString("ORG_CD_11"));
            // <Specialist SPR Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_11, rs.getString("TRTY_GRP_TP_DESC_TXT_11"));
            // (Uploadable)<Specialist SPR Territory Name>(<Alphabet Numeric CharacacctTrtyOrgNm_10ter>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_11, rs.getString("ORG_NM_11"));
            // <Specialist SPR Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_11, rs.getString("PSN_CD_11"));
            // <Specialist SPR Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_11, rs.getString("PSN_NM_11"));
            // <Specialist SPR Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_11, rs.getString("PSN_NUM_11"));
            // <Specialist SPS Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_12, rs.getString("ORG_CD_12"));
            // <Specialist SPS Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_12, rs.getString("TRTY_GRP_TP_DESC_TXT_12"));
            // (Uploadable)<Specialist SPS Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_12, rs.getString("ORG_NM_12"));
            // <Specialist SPS Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_12, rs.getString("PSN_CD_12"));
            // <Specialist SPS Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_12, rs.getString("PSN_NM_12"));
            // <Specialist SPS Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_12, rs.getString("PSN_NUM_12"));
            // <DG Writer Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_13, rs.getString("ORG_CD_13"));
            // <DG Writer Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_13, rs.getString("TRTY_GRP_TP_DESC_TXT_13"));
            // (Uploadable)<DG Writer Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_13, rs.getString("ORG_NM_13"));
            // <DG Writer Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_13, rs.getString("PSN_CD_13"));
            // <DG Writer Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_13, rs.getString("PSN_NM_13"));
            // <DG Writer Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_13, rs.getString("PSN_NUM_13"));
            // <TD Writer Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_14, rs.getString("ORG_CD_14"));
            // <TD Writer Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_14, rs.getString("TRTY_GRP_TP_DESC_TXT_14"));
            // (Uploadable)<TD Writer Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_14, rs.getString("ORG_NM_14"));
            // <TD Writer Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_14, rs.getString("PSN_CD_14"));
            // <TD Writer Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_14, rs.getString("PSN_NM_14"));
            // <TD Writer Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_14, rs.getString("PSN_NUM_14"));
            // <IS Writer Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_15, rs.getString("ORG_CD_15"));
            // <IS Writer Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_15, rs.getString("TRTY_GRP_TP_DESC_TXT_15"));
            // (Uploadable)<IS Writer Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_15, rs.getString("ORG_NM_15"));
            // <IS Writer Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_15, rs.getString("PSN_CD_15"));
            // <IS Writer Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_15, rs.getString("PSN_NM_15"));
            // <IS Writer Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_15, rs.getString("PSN_NUM_15"));
            // <ESS Tele Writer Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_16, rs.getString("ORG_CD_16"));
            // <ESS Tele Writer Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_16, rs.getString("TRTY_GRP_TP_DESC_TXT_16"));
            // <ESS Tele Writer Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_16, rs.getString("ORG_NM_16"));
            // <ESS Tele Writer Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_16, rs.getString("PSN_CD_16"));
            // <ESS Tele Writer Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_16, rs.getString("PSN_NM_16"));
            // <ESS Tele Writer Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_16, rs.getString("PSN_NUM_16"));
            // <Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_17, rs.getString("ORG_CD_17"));
            // <Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_17, rs.getString("TRTY_GRP_TP_DESC_TXT_17"));
            // (Uploadable)<Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_17, rs.getString("ORG_NM_17"));
            // <Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_17, rs.getString("PSN_CD_17"));
            // <Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_17, rs.getString("PSN_NM_17"));
            // <Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_17, rs.getString("PSN_NUM_17"));
            // <Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_18, rs.getString("ORG_CD_18"));
            // <Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_18, rs.getString("TRTY_GRP_TP_DESC_TXT_18"));
            // (Uploadable)<Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_18, rs.getString("ORG_NM_18"));
            // <Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_18, rs.getString("PSN_CD_18"));
            // <Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_18, rs.getString("PSN_NM_18"));
            // <Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_18, rs.getString("PSN_NUM_18"));
            // <Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_19, rs.getString("ORG_CD_19"));
            // <Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_19, rs.getString("TRTY_GRP_TP_DESC_TXT_19"));
            // (Uploadable)<Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_19, rs.getString("ORG_NM_19"));
            // <Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_19, rs.getString("PSN_CD_19"));
            // <Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_19, rs.getString("PSN_NM_19"));
            // <Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_19, rs.getString("PSN_NUM_19"));
            // <Territory ID>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgCd_20, rs.getString("ORG_CD_20"));
            // <Territory Group>
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyGrpTpNm_20, rs.getString("TRTY_GRP_TP_DESC_TXT_20"));
            // (Uploadable)<Territory Name>(<Alphabet Numeric Character>(50))
            ZYPEZDItemValueSetter.setValue(fMsg.acctTrtyOrgNm_20, rs.getString("ORG_NM_20"));
            // <Resource #>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnCd_20, rs.getString("PSN_CD_20"));
            // <Resource Name>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNm_20, rs.getString("PSN_NM_20"));
            // <Employee ID>
            ZYPEZDItemValueSetter.setValue(fMsg.trtyAnlsPsnNum_20, rs.getString("PSN_NUM_20"));
            // END 2017/10/03 J.Kim [QC#21299,ADD]
            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * checkCsvHeader
     * @param csvHeader String[]
     * @return boolean
     */
    public static boolean checkCsvHeader(String[] csvHeader) {
        if (csvHeader.length == CSV_HEADER_NUM) {
            for (int i = 0; i < csvHeader.length; i++) {
                if (!ZYPCommonFunc.hasValue(csvHeader[i])) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * setTerritoryDelete
     * @param bsMsg NMAL2460_BSMsg
     * @return boolean
     */
    public static void setTerritoryDelete(NMAL2460_BSMsg bsMsg) {
        if (isDelete(bsMsg.orgNm_O1.getValue(), bsMsg.orgNm_B1.getValue())) {
            bsMsg.acctTrtyOrgCd_B1.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_O2.getValue(), bsMsg.orgNm_B2.getValue())) {
            bsMsg.acctTrtyOrgCd_B2.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_O3.getValue(), bsMsg.orgNm_B3.getValue())) {
            bsMsg.acctTrtyOrgCd_B3.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_O4.getValue(), bsMsg.orgNm_B4.getValue())) {
            bsMsg.acctTrtyOrgCd_B4.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_O5.getValue(), bsMsg.orgNm_B5.getValue())) {
            bsMsg.acctTrtyOrgCd_B5.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_O6.getValue(), bsMsg.orgNm_B6.getValue())) {
            bsMsg.acctTrtyOrgCd_B6.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_O7.getValue(), bsMsg.orgNm_B7.getValue())) {
            bsMsg.acctTrtyOrgCd_B7.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_O8.getValue(), bsMsg.orgNm_B8.getValue())) {
            bsMsg.acctTrtyOrgCd_B8.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_O9.getValue(), bsMsg.orgNm_B9.getValue())) {
            bsMsg.acctTrtyOrgCd_B9.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_OA.getValue(), bsMsg.orgNm_BA.getValue())) {
            bsMsg.acctTrtyOrgCd_BA.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_OB.getValue(), bsMsg.orgNm_BB.getValue())) {
            bsMsg.acctTrtyOrgCd_BB.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_OC.getValue(), bsMsg.orgNm_BC.getValue())) {
            bsMsg.acctTrtyOrgCd_BC.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_OD.getValue(), bsMsg.orgNm_BD.getValue())) {
            bsMsg.acctTrtyOrgCd_BD.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_OE.getValue(), bsMsg.orgNm_BE.getValue())) {
            bsMsg.acctTrtyOrgCd_BE.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_OF.getValue(), bsMsg.orgNm_BF.getValue())) {
            bsMsg.acctTrtyOrgCd_BF.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_OG.getValue(), bsMsg.orgNm_BG.getValue())) {
            bsMsg.acctTrtyOrgCd_BG.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_OH.getValue(), bsMsg.orgNm_BH.getValue())) {
            bsMsg.acctTrtyOrgCd_BH.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_OI.getValue(), bsMsg.orgNm_BI.getValue())) {
            bsMsg.acctTrtyOrgCd_BI.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_OJ.getValue(), bsMsg.orgNm_BJ.getValue())) {
            bsMsg.acctTrtyOrgCd_BJ.setValue(DELETE);
        }
        if (isDelete(bsMsg.orgNm_OK.getValue(), bsMsg.orgNm_BK.getValue())) {
            bsMsg.acctTrtyOrgCd_BK.setValue(DELETE);
        }
    }

    /**
     * clearDeleteTerritory
     * @param bsMsg NMAL2460_BSMsg
     * @return boolean
     */
    public static void clearDeleteTerritory(NMAL2460_BSMsg bsMsg) {
        if (isDelete(bsMsg.orgNm_O1.getValue(), bsMsg.orgNm_B1.getValue())) {
            bsMsg.acctTrtyOrgCd_B1.clear();
            bsMsg.xxAcctTrtyPsnCd_B1.clear();
            bsMsg.xxAcctTrtyPsnNm_B1.clear();
            bsMsg.xxAcctTrtyPsnNum_B1.clear();
            bsMsg.trtyGrpTpDescTxt_B1.clear();
        }
        if (isDelete(bsMsg.orgNm_O2.getValue(), bsMsg.orgNm_B2.getValue())) {
            bsMsg.acctTrtyOrgCd_B2.clear();
            bsMsg.xxAcctTrtyPsnCd_B2.clear();
            bsMsg.xxAcctTrtyPsnNm_B2.clear();
            bsMsg.xxAcctTrtyPsnNum_B2.clear();
            bsMsg.trtyGrpTpDescTxt_B2.clear();
        }
        if (isDelete(bsMsg.orgNm_O3.getValue(), bsMsg.orgNm_B3.getValue())) {
            bsMsg.acctTrtyOrgCd_B3.clear();
            bsMsg.xxAcctTrtyPsnCd_B3.clear();
            bsMsg.xxAcctTrtyPsnNm_B3.clear();
            bsMsg.xxAcctTrtyPsnNum_B3.clear();
            bsMsg.trtyGrpTpDescTxt_B3.clear();
        }
        if (isDelete(bsMsg.orgNm_O4.getValue(), bsMsg.orgNm_B4.getValue())) {
            bsMsg.acctTrtyOrgCd_B4.clear();
            bsMsg.xxAcctTrtyPsnCd_B4.clear();
            bsMsg.xxAcctTrtyPsnNm_B4.clear();
            bsMsg.xxAcctTrtyPsnNum_B4.clear();
            bsMsg.trtyGrpTpDescTxt_B4.clear();
        }
        if (isDelete(bsMsg.orgNm_O5.getValue(), bsMsg.orgNm_B5.getValue())) {
            bsMsg.acctTrtyOrgCd_B5.clear();
            bsMsg.xxAcctTrtyPsnCd_B5.clear();
            bsMsg.xxAcctTrtyPsnNm_B5.clear();
            bsMsg.xxAcctTrtyPsnNum_B5.clear();
            bsMsg.trtyGrpTpDescTxt_B5.clear();
        }
        if (isDelete(bsMsg.orgNm_O6.getValue(), bsMsg.orgNm_B6.getValue())) {
            bsMsg.acctTrtyOrgCd_B6.clear();
            bsMsg.xxAcctTrtyPsnCd_B6.clear();
            bsMsg.xxAcctTrtyPsnNm_B6.clear();
            bsMsg.xxAcctTrtyPsnNum_B6.clear();
            bsMsg.trtyGrpTpDescTxt_B6.clear();
        }
        if (isDelete(bsMsg.orgNm_O7.getValue(), bsMsg.orgNm_B7.getValue())) {
            bsMsg.acctTrtyOrgCd_B7.clear();
            bsMsg.xxAcctTrtyPsnCd_B7.clear();
            bsMsg.xxAcctTrtyPsnNm_B7.clear();
            bsMsg.xxAcctTrtyPsnNum_B7.clear();
            bsMsg.trtyGrpTpDescTxt_B7.clear();
        }
        if (isDelete(bsMsg.orgNm_O8.getValue(), bsMsg.orgNm_B8.getValue())) {
            bsMsg.acctTrtyOrgCd_B8.clear();
            bsMsg.xxAcctTrtyPsnCd_B8.clear();
            bsMsg.xxAcctTrtyPsnNm_B8.clear();
            bsMsg.xxAcctTrtyPsnNum_B8.clear();
            bsMsg.trtyGrpTpDescTxt_B8.clear();
        }
        if (isDelete(bsMsg.orgNm_O9.getValue(), bsMsg.orgNm_B9.getValue())) {
            bsMsg.acctTrtyOrgCd_B9.clear();
            bsMsg.xxAcctTrtyPsnCd_B9.clear();
            bsMsg.xxAcctTrtyPsnNm_B9.clear();
            bsMsg.xxAcctTrtyPsnNum_B9.clear();
            bsMsg.trtyGrpTpDescTxt_B9.clear();
        }
        if (isDelete(bsMsg.orgNm_OA.getValue(), bsMsg.orgNm_BA.getValue())) {
            bsMsg.acctTrtyOrgCd_BA.clear();
            bsMsg.xxAcctTrtyPsnCd_BA.clear();
            bsMsg.xxAcctTrtyPsnNm_BA.clear();
            bsMsg.xxAcctTrtyPsnNum_BA.clear();
            bsMsg.trtyGrpTpDescTxt_BA.clear();
        }
        if (isDelete(bsMsg.orgNm_OB.getValue(), bsMsg.orgNm_BB.getValue())) {
            bsMsg.acctTrtyOrgCd_BB.clear();
            bsMsg.xxAcctTrtyPsnCd_BB.clear();
            bsMsg.xxAcctTrtyPsnNm_BB.clear();
            bsMsg.xxAcctTrtyPsnNum_BB.clear();
            bsMsg.trtyGrpTpDescTxt_BB.clear();
        }
        if (isDelete(bsMsg.orgNm_OC.getValue(), bsMsg.orgNm_BC.getValue())) {
            bsMsg.acctTrtyOrgCd_BC.clear();
            bsMsg.xxAcctTrtyPsnCd_BC.clear();
            bsMsg.xxAcctTrtyPsnNm_BC.clear();
            bsMsg.xxAcctTrtyPsnNum_BC.clear();
            bsMsg.trtyGrpTpDescTxt_BC.clear();
        }
        if (isDelete(bsMsg.orgNm_OD.getValue(), bsMsg.orgNm_BD.getValue())) {
            bsMsg.acctTrtyOrgCd_BD.clear();
            bsMsg.xxAcctTrtyPsnCd_BD.clear();
            bsMsg.xxAcctTrtyPsnNm_BD.clear();
            bsMsg.xxAcctTrtyPsnNum_BD.clear();
            bsMsg.trtyGrpTpDescTxt_BD.clear();
        }
        if (isDelete(bsMsg.orgNm_OE.getValue(), bsMsg.orgNm_BE.getValue())) {
            bsMsg.acctTrtyOrgCd_BE.clear();
            bsMsg.xxAcctTrtyPsnCd_BE.clear();
            bsMsg.xxAcctTrtyPsnNm_BE.clear();
            bsMsg.xxAcctTrtyPsnNum_BE.clear();
            bsMsg.trtyGrpTpDescTxt_BE.clear();
        }
        if (isDelete(bsMsg.orgNm_OF.getValue(), bsMsg.orgNm_BF.getValue())) {
            bsMsg.acctTrtyOrgCd_BF.clear();
            bsMsg.xxAcctTrtyPsnCd_BF.clear();
            bsMsg.xxAcctTrtyPsnNm_BF.clear();
            bsMsg.xxAcctTrtyPsnNum_BF.clear();
            bsMsg.trtyGrpTpDescTxt_BF.clear();
        }
        if (isDelete(bsMsg.orgNm_OG.getValue(), bsMsg.orgNm_BG.getValue())) {
            bsMsg.acctTrtyOrgCd_BG.clear();
            bsMsg.xxAcctTrtyPsnCd_BG.clear();
            bsMsg.xxAcctTrtyPsnNm_BG.clear();
            bsMsg.xxAcctTrtyPsnNum_BG.clear();
            bsMsg.trtyGrpTpDescTxt_BG.clear();
        }
        if (isDelete(bsMsg.orgNm_OH.getValue(), bsMsg.orgNm_BH.getValue())) {
            bsMsg.acctTrtyOrgCd_BH.clear();
            bsMsg.xxAcctTrtyPsnCd_BH.clear();
            bsMsg.xxAcctTrtyPsnNm_BH.clear();
            bsMsg.xxAcctTrtyPsnNum_BH.clear();
            bsMsg.trtyGrpTpDescTxt_BH.clear();
        }
        if (isDelete(bsMsg.orgNm_OI.getValue(), bsMsg.orgNm_BI.getValue())) {
            bsMsg.acctTrtyOrgCd_BI.clear();
            bsMsg.xxAcctTrtyPsnCd_BI.clear();
            bsMsg.xxAcctTrtyPsnNm_BI.clear();
            bsMsg.xxAcctTrtyPsnNum_BI.clear();
            bsMsg.trtyGrpTpDescTxt_BI.clear();
        }
        if (isDelete(bsMsg.orgNm_OJ.getValue(), bsMsg.orgNm_BJ.getValue())) {
            bsMsg.acctTrtyOrgCd_BJ.clear();
            bsMsg.xxAcctTrtyPsnCd_BJ.clear();
            bsMsg.xxAcctTrtyPsnNm_BJ.clear();
            bsMsg.xxAcctTrtyPsnNum_BJ.clear();
            bsMsg.trtyGrpTpDescTxt_BJ.clear();
        }
        if (isDelete(bsMsg.orgNm_OK.getValue(), bsMsg.orgNm_BK.getValue())) {
            bsMsg.acctTrtyOrgCd_BK.clear();
            bsMsg.xxAcctTrtyPsnCd_BK.clear();
            bsMsg.xxAcctTrtyPsnNm_BK.clear();
            bsMsg.xxAcctTrtyPsnNum_BK.clear();
            bsMsg.trtyGrpTpDescTxt_BK.clear();
        }
    }

    /**
     * checkChangeTeritoryAttribute
     * @param cMsg NMAL2460CMsg
     * @param bsMsg NMAL2460_BSMsg
     * @param isSuccess boolean
     * @return boolean
     */
    // 2017/11/27 CSA-QC#20597 Mod Start
    // Mod Start 2017/09/13 QC#21044
    // Mod Start 2017/11/28 QC#21044
    //public static boolean checkChangeTeritoryAttribute(NMAL2460_BSMsg bsMsg, String glblCmpyCd, boolean isSuccess) {
    public static boolean checkChangeTeritoryAttribute(NMAL2460CMsg cMsg, NMAL2460_BSMsg bsMsg, String glblCmpyCd, boolean isSuccess) {
        // Mod End 2017/11/28 QC#21044
    // public static boolean checkChangeTeritoryAttribute(NMAL2460_BSMsg bsMsg, String glblCmpyCd, boolean isSuccess, List<TRTY_RULETMsg> DelTrtyRuleList, List<TRTY_RULETMsg> AddTrtyRuleList, Map<String, NMZC270001PMsg> ruleValidationPMsgMap) {
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_B1) && !bsMsg.orgNm_B1.getValue().equals(bsMsg.orgNm_O1.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_B1.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE1_ID, bsMsg.orgNm_B1.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE1_ID, bsMsg.orgNm_B1.getValue(), cMsg.slsCrQuotFlg_01.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE1_ID, bsMsg.orgNm_B1.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_O1.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_B2) && !bsMsg.orgNm_B2.getValue().equals(bsMsg.orgNm_O2.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_B2.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE2_ID, bsMsg.orgNm_B2.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE2_ID, bsMsg.orgNm_B2.getValue(), cMsg.slsCrQuotFlg_02.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE2_ID, bsMsg.orgNm_B2.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_O2.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_B3) && !bsMsg.orgNm_B3.getValue().equals(bsMsg.orgNm_O3.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_B3.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE3_ID, bsMsg.orgNm_B3.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE3_ID, bsMsg.orgNm_B3.getValue(), cMsg.slsCrQuotFlg_03.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE3_ID, bsMsg.orgNm_B3.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_O3.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_B4) && !bsMsg.orgNm_B4.getValue().equals(bsMsg.orgNm_O4.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_B4.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE4_ID, bsMsg.orgNm_B4.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE4_ID, bsMsg.orgNm_B4.getValue(), cMsg.slsCrQuotFlg_04.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE4_ID, bsMsg.orgNm_B4.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_O4.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_B5) && !bsMsg.orgNm_B5.getValue().equals(bsMsg.orgNm_O5.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_B5.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE5_ID, bsMsg.orgNm_B5.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE5_ID, bsMsg.orgNm_B5.getValue(), cMsg.slsCrQuotFlg_05.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE5_ID, bsMsg.orgNm_B5.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_O5.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_B6) && !bsMsg.orgNm_B6.getValue().equals(bsMsg.orgNm_O6.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_B6.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE6_ID, bsMsg.orgNm_B6.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE6_ID, bsMsg.orgNm_B6.getValue(), cMsg.slsCrQuotFlg_06.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE6_ID, bsMsg.orgNm_B6.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_O6.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_B7) && !bsMsg.orgNm_B7.getValue().equals(bsMsg.orgNm_O7.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_B7.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE7_ID, bsMsg.orgNm_B7.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE7_ID, bsMsg.orgNm_B7.getValue(), cMsg.slsCrQuotFlg_07.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE7_ID, bsMsg.orgNm_B7.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_O7.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_B8) && !bsMsg.orgNm_B8.getValue().equals(bsMsg.orgNm_O8.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_B8.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE8_ID, bsMsg.orgNm_B8.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE8_ID, bsMsg.orgNm_B8.getValue(), cMsg.slsCrQuotFlg_08.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE8_ID, bsMsg.orgNm_B8.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_O8.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_B9) && !bsMsg.orgNm_B9.getValue().equals(bsMsg.orgNm_O9.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_B9.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE9_ID, bsMsg.orgNm_B9.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE9_ID, bsMsg.orgNm_B9.getValue(), cMsg.slsCrQuotFlg_09.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE9_ID, bsMsg.orgNm_B9.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_O9.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_BA) && !bsMsg.orgNm_BA.getValue().equals(bsMsg.orgNm_OA.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_BA.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE10_ID, bsMsg.orgNm_BA.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE10_ID, bsMsg.orgNm_BA.getValue(), cMsg.slsCrQuotFlg_10.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE10_ID, bsMsg.orgNm_BA.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_OA.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_BB) && !bsMsg.orgNm_BB.getValue().equals(bsMsg.orgNm_OB.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_BB.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE11_ID, bsMsg.orgNm_BB.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE11_ID, bsMsg.orgNm_BB.getValue(), cMsg.slsCrQuotFlg_11.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE11_ID, bsMsg.orgNm_BB.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_OB.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_BC) && !bsMsg.orgNm_BC.getValue().equals(bsMsg.orgNm_OC.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_BC.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE12_ID, bsMsg.orgNm_BC.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE12_ID, bsMsg.orgNm_BC.getValue(), cMsg.slsCrQuotFlg_12.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE12_ID, bsMsg.orgNm_BC.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_OC.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_BD) && !bsMsg.orgNm_BD.getValue().equals(bsMsg.orgNm_OD.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_BD.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE13_ID, bsMsg.orgNm_BD.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE13_ID, bsMsg.orgNm_BD.getValue(), cMsg.slsCrQuotFlg_13.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE13_ID, bsMsg.orgNm_BD.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_OD.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_BE) && !bsMsg.orgNm_BE.getValue().equals(bsMsg.orgNm_OE.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_BE.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE14_ID, bsMsg.orgNm_BE.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE14_ID, bsMsg.orgNm_BE.getValue(), cMsg.slsCrQuotFlg_14.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE14_ID, bsMsg.orgNm_BE.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_OE.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_BF) && !bsMsg.orgNm_BF.getValue().equals(bsMsg.orgNm_OF.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_BF.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE15_ID, bsMsg.orgNm_BF.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE15_ID, bsMsg.orgNm_BF.getValue(), cMsg.slsCrQuotFlg_15.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE15_ID, bsMsg.orgNm_BF.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_OF.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_BG) && !bsMsg.orgNm_BG.getValue().equals(bsMsg.orgNm_OG.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_BG.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE16_ID, bsMsg.orgNm_BG.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE16_ID, bsMsg.orgNm_BG.getValue(), cMsg.slsCrQuotFlg_16.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE16_ID, bsMsg.orgNm_BG.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_OG.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_BH) && !bsMsg.orgNm_BH.getValue().equals(bsMsg.orgNm_OH.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_BH.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE17_ID, bsMsg.orgNm_BH.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE17_ID, bsMsg.orgNm_BH.getValue(), cMsg.slsCrQuotFlg_17.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE17_ID, bsMsg.orgNm_BH.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_OH.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_BI) && !bsMsg.orgNm_BI.getValue().equals(bsMsg.orgNm_OI.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_BI.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE18_ID, bsMsg.orgNm_BI.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE18_ID, bsMsg.orgNm_BI.getValue(), cMsg.slsCrQuotFlg_18.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE18_ID, bsMsg.orgNm_BI.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_OI.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_BJ) && !bsMsg.orgNm_BJ.getValue().equals(bsMsg.orgNm_OJ.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_BJ.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE19_ID, bsMsg.orgNm_BJ.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE19_ID, bsMsg.orgNm_BJ.getValue(), cMsg.slsCrQuotFlg_19.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE19_ID, bsMsg.orgNm_BJ.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_OJ.getValue())) {
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bsMsg.orgNm_BK) && !bsMsg.orgNm_BK.getValue().equals(bsMsg.orgNm_OK.getValue()) && !NMAL2460Constant.DELETE.equals(bsMsg.acctTrtyOrgCd_BK.getValue())) {
            // Mod Start 2017/11/28 QC#21044
            //if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE20_ID, bsMsg.orgNm_BK.getValue())) {
            if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE20_ID, bsMsg.orgNm_BK.getValue(), cMsg.slsCrQuotFlg_20.getValue())) {
                // Mod End 2017/11/28 QC#21044
            // if (!checkMaster(bsMsg, glblCmpyCd, ASG_TRTY_ATTRB.ATTRIBUTE20_ID, bsMsg.orgNm_BK.getValue(), DelTrtyRuleList, AddTrtyRuleList, ruleValidationPMsgMap, bsMsg.acctTrtyOrgCd_OK.getValue())) {
                isSuccess = false;
            }
        }
        return isSuccess;
    }
    // Mod Start 2017/09/13 QC#21044
    // 2017/11/27 CSA-QC#20597 Mod End

    /**
     * checkMaster
     * @param bsMsg NMAL2460_BSMsg
     * @param asgTrtyAttrb String
     * @param trtyNm String
     * @param slsCrQuotFlg String
     * @return boolean
     */
    // 2017/11/27 CSA-QC#20597 Mod Start
    // Mod Start 2017/09/13 QC#21044
    // Mod Start 2017/11/28 QC#21044
    //private static boolean checkMaster(NMAL2460_BSMsg bsMsg, String glblCmpyCd, String asgTrtyAttrb, String trtyNm) {
    private static boolean checkMaster(NMAL2460_BSMsg bsMsg, String glblCmpyCd, String asgTrtyAttrb, String trtyNm, String slsCrQuotFlg) {
        // Mod End 2017/11/28 QC#21044
//    private static boolean checkMaster(NMAL2460_BSMsg bsMsg
//                                        , String glblCmpyCd
//                                        , String asgTrtyAttrb
//                                        , String trtyNm
//                                        , List<TRTY_RULETMsg> DelTrtyRuleList
//                                        , List<TRTY_RULETMsg> AddTrtyRuleList
//                                        , Map<String, NMZC270001PMsg> ruleValidationPMsgMap
//                                        , String beforeAcctTrtyOrgCd) {
    // Mod End 2017/09/13 QC#21044
    // 2017/11/27 CSA-QC#20597 Mod Start
        // Master Check : Territory
        S21SsmEZDResult ssmResult = NMAL2460Query.getInstance().checkActiveTerritory(trtyNm);
        if (ssmResult.isCodeNormal()) {
            if ((Integer) ssmResult.getResultObject() > 1) {
                setMessageToTerritoryName(bsMsg, asgTrtyAttrb, ERROR_CODE_ERROR, NMAM8542E, null);
                return false;
            }
        }

        // get Territory Info
        // Mod Start 2017/11/28 QC#21044
        //ssmResult = NMAL2460Query.getInstance().getTerritoryDetail(trtyNm);
        String nonSlsRepFlg = null;
        if (ZYPConstant.FLG_ON_Y.equals(slsCrQuotFlg)) {
            // If LINE_BIZ_ROLE_TP.SLS_CR_QUOT_FLG = 'Y', Sales Rep Territory.
            // -> DS_ORG_RESRC_RELN.NON_SLS_REP_FLG = 'N'
            nonSlsRepFlg = ZYPConstant.FLG_OFF_N;
        } else if (ZYPConstant.FLG_OFF_N.equals(slsCrQuotFlg)) {
            // If LINE_BIZ_ROLE_TP.SLS_CR_QUOT_FLG = 'N', Specialist Territory.
            // -> DS_ORG_RESRC_RELN.NON_SLS_REP_FLG = 'Y'
            nonSlsRepFlg = ZYPConstant.FLG_ON_Y;
        }
        ssmResult = NMAL2460Query.getInstance().getTerritoryDetail(trtyNm, nonSlsRepFlg);
        // Mod End 2017/11/28 QC#21044
        if (!ssmResult.isCodeNormal()) {
            setMessageToTerritoryName(bsMsg, asgTrtyAttrb, ERROR_CODE_ERROR, NMAM8551E, null);
            return false;
        }

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        Map<String, Object> trtyDtlMap = (Map<String, Object>) resultList.get(0);

        // check exist Active salesrep role resource in territory
        ssmResult = NMAL2460Query.getInstance().getLineBizRankNum(asgTrtyAttrb, bsMsg.dsAcctTpCd_B.getValue(), (String) trtyDtlMap.get("TRTY_GRP_TP_CD"));
        if (ZYPCommonFunc.hasValue((String) ssmResult.getResultObject())) {
            ssmResult = NMAL2460Query.getInstance().checkActiveResorce(trtyNm, true);
            if (!ssmResult.isCodeNormal()){
                setMessageToTerritoryName(bsMsg, asgTrtyAttrb, ERROR_CODE_ERROR, NMAM8545E, null);
                return false;
            } else {
                if ((Integer)ssmResult.getResultObject() == 0) {
                    setMessageToTerritoryName(bsMsg, asgTrtyAttrb, ERROR_CODE_ERROR, NMAM8545E, null);
                    return false;
                }
            }

        // Check exist resource in territory
        } else {
            ssmResult = NMAL2460Query.getInstance().checkActiveResorce(trtyNm, false);
            if (!ssmResult.isCodeNormal()){
                setMessageToTerritoryName(bsMsg, asgTrtyAttrb, ERROR_CODE_ERROR, NMAM8546E, null);
                return false;
            } else {
                if ((Integer)ssmResult.getResultObject() == 0) {
                    setMessageToTerritoryName(bsMsg, asgTrtyAttrb, ERROR_CODE_ERROR, NMAM8546E, null);
                    return false;
                }
            }
        }

 // 2017/11/27 CSA-QC#20597 Del Start
//        // 2017/09/13 CSA-QC#21044 Mod Start
//        // 2016/09/13 CSA-QC#10091 Add Start
//        // call Territory Rule Validation API
////        if (!callNMZC270001TrtyRuleValidationAPI(bsMsg, glblCmpyCd, asgTrtyAttrb, trtyDtlMap)) {
////            return false;
////        }
//        // 2016/09/13 CSA-QC#10091 Add End
//        String orgStruTpCd = ORG_STRU_TP.TERRITORY_STRUCTURE;
//        String locNum = bsMsg.locNum_B.getValue();
//        String afterAcctTrtyOrgCd = (String) trtyDtlMap.get("ORG_CD");
//
//        // Insert TRTY_RULETMsg
//        if (NMAL2460Query.getInstance().existsTerritoryRule(locNum, afterAcctTrtyOrgCd, orgStruTpCd)) {
//            return true;
//        }
//        if (NMAL2460Query.getInstance().isRuleLogicAllOr(afterAcctTrtyOrgCd, orgStruTpCd)) {
//            return true;
//        }
//
//        S21SsmEZDResult afterRuleResult = NMAL2460Query.getInstance().getTrtyRule(afterAcctTrtyOrgCd, bsMsg.locNum_B.getValue());
//        BigDecimal afterRulePk = (BigDecimal) afterRuleResult.getResultObject();
//
//        if (!ZYPCommonFunc.hasValue(afterRulePk)) {
//            TRTY_RULETMsg afterTMsg = new TRTY_RULETMsg();
//
//            ZYPEZDItemValueSetter.setValue(afterTMsg.glblCmpyCd, glblCmpyCd);
//            BigDecimal trtyRulePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_RULE_SQ);
//            ZYPEZDItemValueSetter.setValue(afterTMsg.trtyRulePk, trtyRulePk);
//            ZYPEZDItemValueSetter.setValue(afterTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
//            ZYPEZDItemValueSetter.setValue(afterTMsg.orgCd, afterAcctTrtyOrgCd);
//            ZYPEZDItemValueSetter.setValue(afterTMsg.trtyRuleTpCd, TRTY_RULE_TP.LOCATION_NUMBER);
//            ZYPEZDItemValueSetter.setValue(afterTMsg.trtyRuleFromValTxt, locNum);
//            ZYPEZDItemValueSetter.setValue(afterTMsg.effFromDt, ZYPDateUtil.getSalesDate());
//            ZYPEZDItemValueSetter.setValue(afterTMsg.trtyRuleOprdTpCd, TRTY_RULE_OPRD_TP.EQUAL);
//            ZYPEZDItemValueSetter.setValue(afterTMsg.trtyRuleLogicTpCd, TRTY_RULE_LOGIC_TP.OR);
//
//            AddTrtyRuleList.add(afterTMsg);
//        }
//
//        // Update TRTY_RULETMsg
//        S21SsmEZDResult beforeRuleResult = NMAL2460Query.getInstance().getTrtyRule(beforeAcctTrtyOrgCd, bsMsg.locNum_B.getValue());
//        BigDecimal beforeRulePk = (BigDecimal) beforeRuleResult.getResultObject();
//
//        if (ZYPCommonFunc.hasValue(beforeRulePk)) {
//            TRTY_RULETMsg befTMsg = new TRTY_RULETMsg();
//            ZYPEZDItemValueSetter.setValue(befTMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(befTMsg.trtyRulePk, beforeRulePk);
//            befTMsg = (TRTY_RULETMsg) EZDTBLAccessor.findByKey(befTMsg);
//
//            ZYPEZDItemValueSetter.setValue(befTMsg.effThruDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(), -1));
//            DelTrtyRuleList.add(befTMsg);
//        }
//
//        // Rule Validation API PMsg
//        NMZC270001PMsg pMsg = setNMZC270001TrtyRuleValidationPMsg(bsMsg, glblCmpyCd, asgTrtyAttrb, trtyDtlMap);
//        ruleValidationPMsgMap.put(asgTrtyAttrb, pMsg);
        // 2017/09/13 CSA-QC#21044 Mod End
// 2017/11/27 CSA-QC#20597 Del End
        setTerritoryDetailToBSMsg(bsMsg, trtyDtlMap, asgTrtyAttrb);
        return true;
    }

    /**
     * set Insert AcctTrtyResrcRqstHdr
     * @param insHdrTMsgList
     * NMAL2460List<ACCT_TRTY_RESRC_RQST_HDRTMsg>
     * @param bsMsg NMAL2460_BSMsg
     * @param glblCmpyCd String
     * @param hdrSq BigDecimal
     * @param cmntTxt String
     * @param usrId String
     * @return List<ACCT_TRTY_RESRC_RQST_HDRTMsg>
     */
    public static List<ACCT_TRTY_RESRC_RQST_HDRTMsg> setInsertAcctTrtyResrcRqstHdr(List<ACCT_TRTY_RESRC_RQST_HDRTMsg> insHdrTMsgList, String glblCmpyCd, BigDecimal hdrSq, String usrId) {
        ACCT_TRTY_RESRC_RQST_HDRTMsg acctTrtyResrcRqstHdrTMsg = new ACCT_TRTY_RESRC_RQST_HDRTMsg();

        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstHdrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstHdrTMsg.acctTrtyResrcRqstHdrPk, hdrSq);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstHdrTMsg.rqstUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstHdrTMsg.rqstCratTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstHdrTMsg.rqstCratSysTpCd, RQST_CRAT_SYS_TP.ONLINE);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstHdrTMsg.rqstRsltTpCd, RQST_RSLT_TP.SUBMITTED);
        // ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstHdrTMsg.rqstRsltCmntTxt, cmntTxt);

        insHdrTMsgList.add(acctTrtyResrcRqstHdrTMsg);
        return insHdrTMsgList;
    }

    /**
     * isChanged
     * @param beforeCmpr String
     * @param afterCmpr String
     * @return boolean
     */
    public static boolean isChanged(String beforeCmpr, String afterCmpr, boolean isFlag) {
        if (isFlag) {
            if (ZYPConstant.FLG_OFF_N.equals(beforeCmpr)) {
                beforeCmpr = "";
            }
            if (ZYPConstant.FLG_OFF_N.equals(afterCmpr)) {
                afterCmpr = "";
            }
        }

        if (ZYPCommonFunc.hasValue(beforeCmpr)) {
            if (!ZYPCommonFunc.hasValue(afterCmpr)) {
                return true;
            } else if (!beforeCmpr.equals(afterCmpr)) {
                return true;
            }
        } else {
            if (ZYPCommonFunc.hasValue(afterCmpr)) {
                return true;
            }
        }
        return false;
    }

    /**
     * isDelete
     * @param beforeCmpr String
     * @param afterCmpr String
     * @return boolean
     */
    public static boolean isDelete(String beforeCmpr, String afterCmpr) {
        if (ZYPCommonFunc.hasValue(beforeCmpr)) {
            if (!ZYPCommonFunc.hasValue(afterCmpr)) {
                return true;
            }
        }
        return false;
    }

    /**
     * set Insert AcctTrtyResrcRqstDtl
     * @param insDtlTMsgList
     * NMAL2460List<ACCT_TRTY_RESRC_RQST_DTLTMsg>
     * @param bsMsg NMAL2460_BSMsg
     * @param hdrSq BigDecimal
     * @param glblCmpyCd String
     * @return List<ACCT_TRTY_RESRC_RQST_DTLTMsg>
     */
    public static List<ACCT_TRTY_RESRC_RQST_DTLTMsg> setInsertAcctTrtyResrcRqstDtl(List<ACCT_TRTY_RESRC_RQST_DTLTMsg> insDtlTMsgList, NMAL2460_BSMsg bsMsg, BigDecimal hdrSq, String rsltCmntTxt, String glblCmpyCd) {

        ACCT_TRTY_RESRC_RQST_DTLTMsg acctTrtyResrcRqstDtlMsg = new ACCT_TRTY_RESRC_RQST_DTLTMsg();
        BigDecimal dtlSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ACCT_TRTY_RESRC_RQST_DTL_SQ);

        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.acctTrtyResrcRqstDtlPk, dtlSq);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.acctTrtyResrcRqstHdrPk, hdrSq);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.dsAcctNum, bsMsg.dsAcctNum_B);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.dsAcctNm, bsMsg.dsAcctNm_B);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.locNum, bsMsg.locNum_B);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.dsAcctTpCd, bsMsg.dsAcctTpCd_B);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.firstLineAddr, bsMsg.firstLineAddr_B);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.scdLineAddr, bsMsg.scdLineAddr_B);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.thirdLineAddr, bsMsg.thirdLineAddr_B);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.frthLineAddr, bsMsg.frthLineAddr_B);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.ctyAddr, bsMsg.ctyAddr_B);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.stCd, bsMsg.stCd_B);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.postCd, bsMsg.postCd_B);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_01, bsMsg.acctTrtyOrgCd_O1);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_02, bsMsg.acctTrtyOrgCd_O2);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_03, bsMsg.acctTrtyOrgCd_O3);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_04, bsMsg.acctTrtyOrgCd_O4);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_05, bsMsg.acctTrtyOrgCd_O5);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_06, bsMsg.acctTrtyOrgCd_O6);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_07, bsMsg.acctTrtyOrgCd_O7);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_08, bsMsg.acctTrtyOrgCd_O8);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_09, bsMsg.acctTrtyOrgCd_O9);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_10, bsMsg.acctTrtyOrgCd_OA);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_11, bsMsg.acctTrtyOrgCd_OB);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_12, bsMsg.acctTrtyOrgCd_OC);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_13, bsMsg.acctTrtyOrgCd_OD);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_14, bsMsg.acctTrtyOrgCd_OE);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_15, bsMsg.acctTrtyOrgCd_OF);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_16, bsMsg.acctTrtyOrgCd_OG);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_17, bsMsg.acctTrtyOrgCd_OH);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_18, bsMsg.acctTrtyOrgCd_OI);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_19, bsMsg.acctTrtyOrgCd_OJ);
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.befAcctTrtyOrgCd_20, bsMsg.acctTrtyOrgCd_OK);
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_B1) && !bsMsg.orgNm_B1.getValue().equals(bsMsg.orgNm_O1.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_B1.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_01, bsMsg.acctTrtyOrgCd_B1);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_B2) && !bsMsg.orgNm_B2.getValue().equals(bsMsg.orgNm_O2.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_B2.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_02, bsMsg.acctTrtyOrgCd_B2);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_B3) && !bsMsg.orgNm_B3.getValue().equals(bsMsg.orgNm_O3.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_B3.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_03, bsMsg.acctTrtyOrgCd_B3);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_B4) && !bsMsg.orgNm_B4.getValue().equals(bsMsg.orgNm_O4.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_B4.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_04, bsMsg.acctTrtyOrgCd_B4);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_B5) && !bsMsg.orgNm_B5.getValue().equals(bsMsg.orgNm_O5.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_B5.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_05, bsMsg.acctTrtyOrgCd_B5);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_B6) && !bsMsg.orgNm_B6.getValue().equals(bsMsg.orgNm_O6.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_B6.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_06, bsMsg.acctTrtyOrgCd_B6);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_B7) && !bsMsg.orgNm_B7.getValue().equals(bsMsg.orgNm_O7.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_B7.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_07, bsMsg.acctTrtyOrgCd_B7);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_B8) && !bsMsg.orgNm_B8.getValue().equals(bsMsg.orgNm_O8.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_B8.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_08, bsMsg.acctTrtyOrgCd_B8);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_B9) && !bsMsg.orgNm_B9.getValue().equals(bsMsg.orgNm_O9.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_B9.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_09, bsMsg.acctTrtyOrgCd_B9);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_BA) && !bsMsg.orgNm_BA.getValue().equals(bsMsg.orgNm_OA.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_BA.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_10, bsMsg.acctTrtyOrgCd_BA);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_BB) && !bsMsg.orgNm_BB.getValue().equals(bsMsg.orgNm_OB.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_BB.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_11, bsMsg.acctTrtyOrgCd_BB);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_BC) && !bsMsg.orgNm_BC.getValue().equals(bsMsg.orgNm_OC.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_BC.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_12, bsMsg.acctTrtyOrgCd_BC);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_BD) && !bsMsg.orgNm_BD.getValue().equals(bsMsg.orgNm_OD.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_BD.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_13, bsMsg.acctTrtyOrgCd_BD);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_BE) && !bsMsg.orgNm_BE.getValue().equals(bsMsg.orgNm_OE.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_BE.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_14, bsMsg.acctTrtyOrgCd_BE);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_BF) && !bsMsg.orgNm_BF.getValue().equals(bsMsg.orgNm_OF.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_BF.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_15, bsMsg.acctTrtyOrgCd_BF);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_BG) && !bsMsg.orgNm_BG.getValue().equals(bsMsg.orgNm_OG.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_BG.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_16, bsMsg.acctTrtyOrgCd_BG);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_BH) && !bsMsg.orgNm_BH.getValue().equals(bsMsg.orgNm_OH.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_BH.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_17, bsMsg.acctTrtyOrgCd_BH);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_BI) && !bsMsg.orgNm_BI.getValue().equals(bsMsg.orgNm_OI.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_BI.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_18, bsMsg.acctTrtyOrgCd_BI);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_BJ) && !bsMsg.orgNm_BJ.getValue().equals(bsMsg.orgNm_OJ.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_BJ.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_19, bsMsg.acctTrtyOrgCd_BJ);
        }
        if ((ZYPCommonFunc.hasValue(bsMsg.orgNm_BK) && !bsMsg.orgNm_BK.getValue().equals(bsMsg.orgNm_OK.getValue())) || DELETE.equals(bsMsg.acctTrtyOrgCd_BK.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.aftAcctTrtyOrgCd_20, bsMsg.acctTrtyOrgCd_BK);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bsMsg.manEntryFlg_B.getValue())) {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.manEntryFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.manEntryFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(acctTrtyResrcRqstDtlMsg.massUpdRsnCmntTxt, rsltCmntTxt);

        insDtlTMsgList.add(acctTrtyResrcRqstDtlMsg);
        return insDtlTMsgList;
    }

// 2017/11/27 CSA-QC#20597 Del Start
//     2017/09/13 CSA-QC#21044 Mod Start
//    /**
//     * call NMZC270001 Territory Rule Validation API
//     * @param sMsg NMAL2460SMsg
//     * @param glblCmpyCd String
//     * @param asgTrtyAttrb String
//     * @param trtyDtlMap Map<String, Object>
//     * @return boolean
//     */
////    public static boolean callNMZC270001TrtyRuleValidationAPI(NMAL2460_BSMsg bsMsg, String glblCmpyCd, String asgTrtyAttrb, Map<String, Object> trtyDtlMap) {
//    public static boolean callNMZC270001TrtyRuleValidationAPI(Map<String
//                                                              , NMZC270001PMsg> ruleValidationPMsgMap
//                                                              , NMAL2460_BSMsg bsMsg) {
////        // 2016/09/13 CSA-QC#10091 Add Start
////        // NMZC2700 Territory Rule Validation API
////        NMZC270001 nmzc270001 = new NMZC270001();
////        NMZC270001PMsg paramMsg = new NMZC270001PMsg();
////
////        ZYPEZDItemValueSetter.setValue(paramMsg.glblCmpyCd, glblCmpyCd);
////        ZYPEZDItemValueSetter.setValue(paramMsg.orgCd, (String) trtyDtlMap.get("ORG_CD"));
////        ZYPEZDItemValueSetter.setValue(paramMsg.firstOrgCd, (String) trtyDtlMap.get("FIRST_ORG_CD"));
////        ZYPEZDItemValueSetter.setValue(paramMsg.trtyTpCd, (String) trtyDtlMap.get("TRTY_TP_CD"));
////        ZYPEZDItemValueSetter.setValue(paramMsg.orgTierCd, (String) trtyDtlMap.get("ORG_TIER_CD"));
////        ZYPEZDItemValueSetter.setValue(paramMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
////        ZYPEZDItemValueSetter.setValue(paramMsg.trtyGrpTpCd, (String) trtyDtlMap.get("TRTY_GRP_TP_CD"));
////        ZYPEZDItemValueSetter.setValue(paramMsg.effFromDt_T, (String) trtyDtlMap.get("EFF_FROM_DT"));
////        ZYPEZDItemValueSetter.setValue(paramMsg.slsDt, ZYPDateUtil.getSalesDate());
////        if (ZYPCommonFunc.hasValue((String) trtyDtlMap.get("EFF_THRU_DT"))) {
////            ZYPEZDItemValueSetter.setValue(paramMsg.effThruDt_T, (String) trtyDtlMap.get("EFF_THRU_DT"));
////        }
////        // Mod Start 2017/03/15 QC#15878
//////        ZYPEZDItemValueSetter.setValue(paramMsg.trtyRuleList.no(0).trtyRuleTpCd, TRTY_RULE_TP.ACCOUNT_OR_SITE_NUMBER);
////        ZYPEZDItemValueSetter.setValue(paramMsg.trtyRuleList.no(0).trtyRuleTpCd, TRTY_RULE_TP.LOCATION_NUMBER);
////        // Mod End 2017/03/15 QC#15878
////        ZYPEZDItemValueSetter.setValue(paramMsg.trtyRuleList.no(0).trtyRuleOprdTpCd, TRTY_RULE_OPRD_TP.EQUAL);
////        ZYPEZDItemValueSetter.setValue(paramMsg.trtyRuleList.no(0).trtyRuleFromValTxt, bsMsg.locNum_B.getValue());
////        ZYPEZDItemValueSetter.setValue(paramMsg.trtyRuleList.no(0).trtyRuleLogicTpCd, TRTY_RULE_LOGIC_TP.OR);
////        ZYPEZDItemValueSetter.setValue(paramMsg.trtyRuleList.no(0).effFromDt_R, ZYPDateUtil.getSalesDate());
////
////        paramMsg.trtyRuleList.setValidCount(1);
//        // 1
//        String asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE1_ID;
//        NMZC270001PMsg paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 2
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE2_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 3
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE3_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 4
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE4_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 5
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE5_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 6
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE6_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 7
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE7_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 8
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE8_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 9
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE9_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 10
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE10_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 11
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE11_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 12
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE12_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 13
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE13_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 14
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE14_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 15
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE15_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 16
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE16_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 17
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE17_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 18
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE18_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 19
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE19_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        // 20
//        asgTrtyAttrb = ASG_TRTY_ATTRB.ATTRIBUTE20_ID;
//        paramMsg = ruleValidationPMsgMap.get(asgTrtyAttrb);
//        if (paramMsg != null
//                && !callNMZC270001TrtyRuleValidationAPI(paramMsg, asgTrtyAttrb, bsMsg)) {
//            return false;
//        }
//        return true;
//        // 2016/09/13 CSA-QC#10091 Add End
//    }
// 2017/11/27 CSA-QC#20597 Del End
// 2017/11/27 CSA-QC#20597 Del Start
//    public static boolean callNMZC270001TrtyRuleValidationAPI(NMZC270001PMsg paramMsg, String asgTrtyAttrb, NMAL2460_BSMsg bsMsg) {
//        NMZC270001 nmzc270001 = new NMZC270001();
//        nmzc270001.execute(paramMsg, ONBATCH_TYPE.ONLINE);
//
//        NMZC270001_trtyRuleListPMsg subPMsg = paramMsg.trtyRuleList.no(0);
//        if (ZYPCommonFunc.hasValue(subPMsg.xxMsgId_R.getValue())) {
//            String messageCode = subPMsg.xxMsgId_R.getValue();
//            String messageArg = subPMsg.xxMsgPrmTxt_R1.getValue();
//
//            int errorCode = ERROR_CODE_ERROR;
//            if (messageCode.endsWith(MESSAGE_KIND_WARNING)) {
//                errorCode = ERROR_CODE_WARNING;
//            }
//
//            String[] messageArgs = null;
//            if (ZYPCommonFunc.hasValue(messageArg)) {
//                messageArgs = new String[] {messageArg };
//            }
//
//            setMessageToTerritoryName(bsMsg, asgTrtyAttrb, errorCode, messageCode, messageArgs);
//            return false;
//        }
//        return true;
//    }
// 2017/11/27 CSA-QC#20597 Del End
// 2017/11/27 CSA-QC#20597 Del Start
//    /**
//     * call NMZC270001 Territory Rule Validation API
//     * @param sMsg NMAL2460SMsg
//     * @param glblCmpyCd String
//     * @param asgTrtyAttrb String
//     * @param trtyDtlMap Map<String, Object>
//     * @return boolean
//     */
//    public static NMZC270001PMsg setNMZC270001TrtyRuleValidationPMsg(NMAL2460_BSMsg bsMsg, String glblCmpyCd, String asgTrtyAttrb, Map<String, Object> trtyDtlMap) {
//        // NMZC2700 Territory Rule Validation API
//        NMZC270001 nmzc270001 = new NMZC270001();
//        NMZC270001PMsg paramMsg = new NMZC270001PMsg();
//
//        ZYPEZDItemValueSetter.setValue(paramMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(paramMsg.orgCd, (String) trtyDtlMap.get("ORG_CD"));
//        ZYPEZDItemValueSetter.setValue(paramMsg.firstOrgCd, (String) trtyDtlMap.get("FIRST_ORG_CD"));
//        ZYPEZDItemValueSetter.setValue(paramMsg.trtyTpCd, (String) trtyDtlMap.get("TRTY_TP_CD"));
//        ZYPEZDItemValueSetter.setValue(paramMsg.orgTierCd, (String) trtyDtlMap.get("ORG_TIER_CD"));
//        ZYPEZDItemValueSetter.setValue(paramMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
//        ZYPEZDItemValueSetter.setValue(paramMsg.trtyGrpTpCd, (String) trtyDtlMap.get("TRTY_GRP_TP_CD"));
//        ZYPEZDItemValueSetter.setValue(paramMsg.effFromDt_T, (String) trtyDtlMap.get("EFF_FROM_DT"));
//        ZYPEZDItemValueSetter.setValue(paramMsg.slsDt, ZYPDateUtil.getSalesDate());
//        if (ZYPCommonFunc.hasValue((String) trtyDtlMap.get("EFF_THRU_DT"))) {
//            ZYPEZDItemValueSetter.setValue(paramMsg.effThruDt_T, (String) trtyDtlMap.get("EFF_THRU_DT"));
//        }
//        ZYPEZDItemValueSetter.setValue(paramMsg.trtyRuleList.no(0).trtyRuleTpCd, TRTY_RULE_TP.LOCATION_NUMBER);
//        ZYPEZDItemValueSetter.setValue(paramMsg.trtyRuleList.no(0).trtyRuleOprdTpCd, TRTY_RULE_OPRD_TP.EQUAL);
//        ZYPEZDItemValueSetter.setValue(paramMsg.trtyRuleList.no(0).trtyRuleFromValTxt, bsMsg.locNum_B.getValue());
//        ZYPEZDItemValueSetter.setValue(paramMsg.trtyRuleList.no(0).trtyRuleLogicTpCd, TRTY_RULE_LOGIC_TP.OR);
//        ZYPEZDItemValueSetter.setValue(paramMsg.trtyRuleList.no(0).effFromDt_R, ZYPDateUtil.getSalesDate());
//
//        paramMsg.trtyRuleList.setValidCount(1);
//        return paramMsg;
//    }
//    // 2017/09/13 CSA-QC#21044 Mod End
// 2017/11/27 CSA-QC#20597 Del End
    /**
     * Initialize backup Territory
     * @param sMsg NMAL2460SMsg
     */
    public static void initializeBackupTerritory(NMAL2460SMsg sMsg) {
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).xxValUpdFlg_B.getValue())) {
                continue;
            }

            clearDeleteTerritory(sMsg.B.no(i));

            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_O1, sMsg.B.no(i).acctTrtyOrgCd_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_O2, sMsg.B.no(i).acctTrtyOrgCd_B2);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_O3, sMsg.B.no(i).acctTrtyOrgCd_B3);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_O4, sMsg.B.no(i).acctTrtyOrgCd_B4);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_O5, sMsg.B.no(i).acctTrtyOrgCd_B5);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_O6, sMsg.B.no(i).acctTrtyOrgCd_B6);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_O7, sMsg.B.no(i).acctTrtyOrgCd_B7);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_O8, sMsg.B.no(i).acctTrtyOrgCd_B8);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_O9, sMsg.B.no(i).acctTrtyOrgCd_B9);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_OA, sMsg.B.no(i).acctTrtyOrgCd_BA);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_OB, sMsg.B.no(i).acctTrtyOrgCd_BB);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_OC, sMsg.B.no(i).acctTrtyOrgCd_BC);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_OD, sMsg.B.no(i).acctTrtyOrgCd_BD);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_OE, sMsg.B.no(i).acctTrtyOrgCd_BE);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_OF, sMsg.B.no(i).acctTrtyOrgCd_BF);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_OG, sMsg.B.no(i).acctTrtyOrgCd_BG);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_OH, sMsg.B.no(i).acctTrtyOrgCd_BH);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_OI, sMsg.B.no(i).acctTrtyOrgCd_BI);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_OJ, sMsg.B.no(i).acctTrtyOrgCd_BJ);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).acctTrtyOrgCd_OK, sMsg.B.no(i).acctTrtyOrgCd_BK);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_O1, sMsg.B.no(i).orgNm_B1);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_O2, sMsg.B.no(i).orgNm_B2);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_O3, sMsg.B.no(i).orgNm_B3);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_O4, sMsg.B.no(i).orgNm_B4);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_O5, sMsg.B.no(i).orgNm_B5);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_O6, sMsg.B.no(i).orgNm_B6);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_O7, sMsg.B.no(i).orgNm_B7);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_O8, sMsg.B.no(i).orgNm_B8);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_O9, sMsg.B.no(i).orgNm_B9);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_OA, sMsg.B.no(i).orgNm_BA);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_OB, sMsg.B.no(i).orgNm_BB);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_OC, sMsg.B.no(i).orgNm_BC);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_OD, sMsg.B.no(i).orgNm_BD);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_OE, sMsg.B.no(i).orgNm_BE);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_OF, sMsg.B.no(i).orgNm_BF);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_OG, sMsg.B.no(i).orgNm_BG);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_OH, sMsg.B.no(i).orgNm_BH);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_OI, sMsg.B.no(i).orgNm_BI);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_OJ, sMsg.B.no(i).orgNm_BJ);
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).orgNm_OK, sMsg.B.no(i).orgNm_BK);
        }
    }


    /*
     * Save Search Option
     */
    public static void callNszc0330forDeleteSearch(NMAL2460CMsg bizMsg, NMAL2460SMsg glblMsg, String userId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_ID);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, userId, glblCmpyCd);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I" //
                    , new String[] {converter.convLabel2i18nLabel(SCREEN_ID, "Delete Search") });
        }
    }

    public static boolean isExistSaveSearchName(NMAL2460CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) //
                        && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public static void callNszc0330forSaveSearch(NMAL2460CMsg bizMsg, NMAL2460SMsg glblMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S) //
                || isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);
        } else {
            setSelectSaveSearchName(pMsg, bizMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.bizAreaOrgCd_H);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.dsAcctNm_H);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.orgNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.fullPsnNm_H);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.orgNm_H2);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.dsAcctTpCd_H);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.dsAcctNum_H);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.trtyGrpTpCd_H);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.psnCd_H);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.lineBizTpCd_H);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.srchLyotTpCd_H);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.xxCratDt_HF.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.xxLineCatgSrchTxt_H);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.ctyAddr_H);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.postCd_HF);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.xxCratDt_HT.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, bizMsg.locNum_H);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, bizMsg.stCd_H);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, bizMsg.postCd_HT);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, usrId, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I" //
                    , new String[] {converter.convLabel2i18nLabel(SCREEN_ID, "Save Search") });
        }
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param bizMsg NYEL8810CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NMAL2460CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_L.no(i));
            }
        }
        return;
    }


    private static boolean isSameSaveSearchName(NMAL2460CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {
                if (cMsg.srchOptNm_S.getValue().equals(cMsg.srchOptNm_L.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    
    private static boolean callNszc0330(NMAL2460CMsg bizMsg, NSZC033001PMsg pMsg) {
        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        bizMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NMAL2460CMsg
     * @param srchOptUsrId user id
     */
    public static void createSavedSearchOptionsPullDown(NMAL2460CMsg bizMsg, String srchOptUsrId, String glblCmpyCd) {
        S21SsmEZDResult ssmResult = NYXC880002Query.getInstance().getSavedSearchOptionList(srchOptUsrId, BUSINESS_ID, glblCmpyCd);
        if (!ssmResult.isCodeNormal()) {
            bizMsg.srchOptPk_L.clear();
            bizMsg.srchOptNm_L.clear();
            return;
        }

        bizMsg.srchOptPk_L.clear();
        bizMsg.srchOptNm_L.clear();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_L.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }

    }

    /**
     * callNszc0330forSearchOption
     * @param cMsg NMAL2460CMsg
     * @param sMsg NMAL2460SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NMAL2460CMsg bizMsg, NMAL2460SMsg glblMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return; // error
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.bizAreaOrgCd_H,   pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_H,   pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.orgNm_H1, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.fullPsnNm_H,  pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.orgNm_H2, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctTpCd_H, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum_H,  pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(bizMsg.trtyGrpTpCd_H,    pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(bizMsg.psnCd_H,  pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd_H,    pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(bizMsg.srchLyotTpCd_H,   pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCratDt_HF,  pMsg.srchOptTxt_12.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxLineCatgSrchTxt_H,   pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(bizMsg.ctyAddr_H,    pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(bizMsg.postCd_HF,    pMsg.srchOptTxt_15);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCratDt_HT,  pMsg.srchOptTxt_16.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.locNum_H, pMsg.srchOptTxt_17);
        ZYPEZDItemValueSetter.setValue(bizMsg.stCd_H,   pMsg.srchOptTxt_18);
        ZYPEZDItemValueSetter.setValue(bizMsg.postCd_HT,    pMsg.srchOptTxt_19);
    }

    // 2017/10/19 QC#21631 add start
    /**
     * defPostCd
     * @param cMsg NMAL2460CMsg
     */
    public static void defPostCd(NMAL2460CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.dsAcctNm_H) && !ZYPCommonFunc.hasValue(cMsg.dsAcctNum_H) //
                && !ZYPCommonFunc.hasValue(cMsg.orgNm_H1) && !ZYPCommonFunc.hasValue(cMsg.trtyGrpTpCd_H) //
                && !ZYPCommonFunc.hasValue(cMsg.fullPsnNm_H) && !ZYPCommonFunc.hasValue(cMsg.psnCd_H) //
                && !ZYPCommonFunc.hasValue(cMsg.orgNm_H2) && !ZYPCommonFunc.hasValue(cMsg.lineBizTpCd_H) //
                && !ZYPCommonFunc.hasValue(cMsg.xxCratDt_HF) && !ZYPCommonFunc.hasValue(cMsg.xxCratDt_HT) //
                && !ZYPCommonFunc.hasValue(cMsg.xxLineCatgSrchTxt_H) && !ZYPCommonFunc.hasValue(cMsg.locNum_H) //
                && !ZYPCommonFunc.hasValue(cMsg.ctyAddr_H) && !ZYPCommonFunc.hasValue(cMsg.stCd_H)) {

            if (ZYPCommonFunc.hasValue(cMsg.postCd_HF) && !ZYPCommonFunc.hasValue(cMsg.postCd_HT) && !cMsg.postCd_HF.getValue().startsWith("%")) {
                StringBuilder postCd = new StringBuilder();
                if (cMsg.postCd_HF.getValue().length() > 5) {
                    postCd.append(cMsg.postCd_HF.getValue().substring(0, 5));
                } else {
                    postCd.append(cMsg.postCd_HF.getValue());
                }
                postCd.append("-9999");

                ZYPEZDItemValueSetter.setValue(cMsg.postCd_HT, postCd.toString());
            } else if (!ZYPCommonFunc.hasValue(cMsg.postCd_HF) && ZYPCommonFunc.hasValue(cMsg.postCd_HT) && !cMsg.postCd_HT.getValue().startsWith("%")) {
                ZYPEZDItemValueSetter.setValue(cMsg.postCd_HF, cMsg.postCd_HT);
            }
        }
    }
    // 2017/10/19 QC#21631 add end
}
