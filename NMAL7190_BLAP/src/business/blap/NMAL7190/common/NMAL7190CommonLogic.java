/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7190.common;

import static business.blap.NMAL7190.constant.NMAL7190Constant.COMMA;
import static business.blap.NMAL7190.constant.NMAL7190Constant.CONVTYPE_CODETONAME;
import static business.blap.NMAL7190.constant.NMAL7190Constant.CONVTYPE_NAMETOCODE;
import static business.blap.NMAL7190.constant.NMAL7190Constant.CSV_EXTENSION;
import static business.blap.NMAL7190.constant.NMAL7190Constant.CSV_FILE_NAME;
import static business.blap.NMAL7190.constant.NMAL7190Constant.CSV_HDR;
import static business.blap.NMAL7190.constant.NMAL7190Constant.HIGH_VAL_TM;
import static business.blap.NMAL7190.constant.NMAL7190Constant.LOGIC_MODE_SEARCH;
import static business.blap.NMAL7190.constant.NMAL7190Constant.LOGIC_MODE_SUBMIT;
import static business.blap.NMAL7190.constant.NMAL7190Constant.MAX_PRCD_NUM;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NMAM0163E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NMAM8191E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NMAM8216E;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NUMBER_PATTERN;
import static business.blap.NMAL7190.constant.NMAL7190Constant.NZZM0001W;
import static business.blap.NMAL7190.constant.NMAL7190Constant.UPLOAD_FORMAT_ERROR;
import static business.blap.NMAL7190.constant.NMAL7190Constant.UPLOAD_READ_ERROR;
import static business.blap.NMAL7190.constant.NMAL7190Constant.YYYYMMDD_LENGTH;
import static business.blap.NMAL7190.constant.NMAL7190Constant.DATE_FORMAT_PADDING_ZERO;
import static business.blap.NMAL7190.constant.NMAL7190Constant.SLASH;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDCSVInFile;
import parts.common.EZDCStringItem;
import parts.common.EZDItemAttrDefines;
import parts.common.EZDItemAttribute;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import parts.common.EZDSchemaInfo;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7190.NMAL7190CMsg;
import business.blap.NMAL7190.NMAL7190Query;
import business.blap.NMAL7190.NMAL7190SMsg;
import business.blap.NMAL7190.NMAL7190_ACMsg;
import business.blap.NMAL7190.NMAL7190_ASMsgArray;
import business.db.ACCT_LOCTMsg;
import business.db.ACCT_LOCTMsgArray;
import business.db.COA_CHTMsg;
import business.db.COA_PRODTMsg;
import business.db.COA_PROJTMsg;
import business.db.CSMP_CONTRTMsg;
import business.db.CSMP_CONTRTMsgArray;
import business.db.DS_ACCT_CLSTMsg;
import business.db.DS_ACCT_DLRTMsg;
import business.db.DS_ACCT_DLRTMsgArray;
import business.db.DS_ACCT_TPTMsg;
import business.db.DS_MDL_GRPTMsg;
import business.db.DS_MDL_GRPTMsgArray;
import business.db.DS_ORD_CATGTMsg;
import business.db.DS_ORD_CATGTMsgArray;
import business.db.DS_ORD_LINE_CATGTMsg;
import business.db.DS_ORD_LINE_CATGTMsgArray;
import business.db.DS_ORD_TPTMsg;
import business.db.DS_ORD_TPTMsgArray;
import business.db.INDY_TPTMsg;
import business.db.MDL_NMTMsg;
import business.db.MDL_NMTMsgArray;
import business.db.MDSETMsg;
import business.db.MDSE_ITEM_CLS_TPTMsg;
import business.db.MDSE_ITEM_TPTMsg;
import business.db.MKT_MDLTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PRC_CONTRTMsg;
import business.db.PRC_CONTRTMsgArray;
import business.db.PRC_GRP_OPTMsg;
import business.db.PRC_GRP_OPTMsgArray;
import business.db.PRC_GRP_TRGT_ATTRBTMsg;
import business.db.PRC_GRP_TRGT_ATTRBTMsgArray;
import business.db.PROD_CTRLTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.file.NMAL7190F00FMsg;

import com.canon.cusa.s21.common.NMX.NMXC105001.NMXC105001PriceMasterUtil;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_OP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 *<pre>
 * NMAL7190CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 * 2017/08/14   Fujitsu         M.Ohno          Update          QC#18789(L3)
 * 2017/08/21   Fujitsu         M.Yamada        Update          QC#18785(L3)
 * 2018/08/01   Fujitsu         M.Ishii         Update          QC#26297
 * 2018/12/05   Fujitsu         T.Noguchi       Update          QC#29324
 * 2019/03/13   Fujitsu         S.Kosaka        Update          QC#30725
 * 2023/04/20   Hitachi         H.Watanabe      Update          QC#61200
 *</pre>
 */
public class NMAL7190CommonLogic {

    /**
     * setPrcGrp
     * @param ssmResult S21SsmEZDResult
     * @param glblMsg NMAL7190SMsg
     * @param bizMsg NMAL7190CMsg
     * @param glblCmpyCd String
     */
    public static void setPrcGrp(S21SsmEZDResult ssmResult, NMAL7190SMsg glblMsg, NMAL7190CMsg bizMsg, String glblCmpyCd) {

        // SSM Result
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        // Header
        Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);

        if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
            bizMsg.setMessageInfo(NZZM0001W);
            glblMsg.A.setValidCount(glblMsg.A.length());
        } else {
            if (ssmResult.getQueryResultCount() == 1 && !ZYPCommonFunc.hasValue((BigDecimal) resultMap.get("PRC_GRP_DTL_PK"))) {
                // Header Only
                glblMsg.A.setValidCount(0);
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }
        }

        ZYPEZDItemValueSetter.setValue(glblMsg.prcGrpPk, (BigDecimal) resultMap.get("PRC_GRP_PK"));
        ZYPEZDItemValueSetter.setValue(glblMsg.prcGrpPk_BK, (BigDecimal) resultMap.get("PRC_GRP_PK"));
        ZYPEZDItemValueSetter.setValue(glblMsg.prcGrpNm, (String) resultMap.get("PRC_GRP_NM"));
        ZYPEZDItemValueSetter.setValue(glblMsg.prcGrpDescTxt, (String) resultMap.get("PRC_GRP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(glblMsg.prcGrpTpCd, (String) resultMap.get("PRC_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.prcGrpTrxTpCd, (String) resultMap.get("PRC_GRP_TRX_TP_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.actvFlg, (String) resultMap.get("ACTV_FLG"));
        ZYPEZDItemValueSetter.setValue(glblMsg.effFromDt, (String) resultMap.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(glblMsg.effThruDt, (String) resultMap.get("EFF_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTime, (String) resultMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTimeZone, (String) resultMap.get("EZUPTIMEZONE"));

        if (ZYPCommonFunc.hasValue(glblMsg.prcGrpTpCd)) {
            createPulldownForPrcGrpTrgtTp(bizMsg, glblMsg);
        }

        EZDMsg.copy(glblMsg, null, glblMsg.Y.no(0), null);

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            resultMap = (Map<String, Object>) resultList.get(i);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpDtlPk_A1, (BigDecimal) resultMap.get("PRC_GRP_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpOpEqualFlg_A1, (String) resultMap.get("PRC_GRP_OP_EQUAL_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpOpNotEqualFlg_A1, (String) resultMap.get("PRC_GRP_OP_NOT_EQUAL_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpOpLikeFlg_A1, (String) resultMap.get("PRC_GRP_OP_LIKE_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpOpBtwFlg_A1, (String) resultMap.get("PRC_GRP_OP_BTW_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpConvFlg_A1, (String) resultMap.get("PRC_GRP_CONV_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpTrgtTpCd_A1, (String) resultMap.get("PRC_GRP_TRGT_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpTrgtAttrbCd_A1, (String) resultMap.get("PRC_GRP_TRGT_ATTRB_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpOpCd_A1, (String) resultMap.get("PRC_GRP_OP_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpPrcdNum_A1, (BigDecimal) resultMap.get("PRC_GRP_PRCD_NUM"));
            // 2023/04/20 QC#61200 Add Start
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dsAcctNm_A1, (String) resultMap.get("DS_ACCT_NM"));
            // 2023/04/20 QC#61200 Add End

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpFromTxt_CD, (String) resultMap.get("PRC_GRP_FROM_TXT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpThruTxt_CD, (String) resultMap.get("PRC_GRP_THRU_TXT"));

            if (PRC_GRP_OP.LIKE.equals(glblMsg.A.no(i).prcGrpOpCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpFromTxt_A1, glblMsg.A.no(i).prcGrpFromTxt_CD);
            } else if (PRC_GRP_OP.BETWEEN.equals(glblMsg.A.no(i).prcGrpOpCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpFromTxt_A1, glblMsg.A.no(i).prcGrpFromTxt_CD);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prcGrpThruTxt_A1, glblMsg.A.no(i).prcGrpThruTxt_CD);
            } else if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcGrpFromTxt_CD) && !PRC_GRP_OP.BETWEEN.equals(glblMsg.A.no(i).prcGrpOpCd_A1.getValue()) && !PRC_GRP_OP.LIKE.equals(glblMsg.A.no(i).prcGrpOpCd_A1.getValue())) {
                if (PRC_GRP_TRGT_ATTRB.ITEM_NUMBER.equals(glblMsg.A.no(i).prcGrpTrgtAttrbCd_A1.getValue())) {
                    convertPrcGrpAttrbForItemNumber(glblMsg.A.no(i).prcGrpFromTxt_CD, glblMsg.A.no(i).prcGrpFromTxt_A1, glblMsg.A.no(i).prcGrpConvFlg_A1, glblMsg.A.no(i).prcGrpTrgtAttrbCd_A1.getValue(), glblCmpyCd, LOGIC_MODE_SEARCH);
                } else {
                    convertPrcGrpAttrb(glblMsg.A.no(i).prcGrpFromTxt_CD, glblMsg.A.no(i).prcGrpFromTxt_A1, glblMsg.A.no(i).prcGrpConvFlg_A1, glblMsg.A.no(i).prcGrpTrgtAttrbCd_A1.getValue(), glblCmpyCd, LOGIC_MODE_SEARCH);
                }
            }

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).effFromDt_A1, (String) resultMap.get("DTL_EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).effThruDt_A1, (String) resultMap.get("DTL_EFF_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ezUpTime_A1, (String) resultMap.get("DTL_EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ezUpTimeZone_A1, (String) resultMap.get("DTL_EZUPTIMEZONE"));
        }

        EZDMsg.copy(glblMsg.A, null, glblMsg.Z, null);
    }

    /**
     * getPrcGrpTrgtAttrb
     * @param bizMsgALine NMAL7190_ACMsg
     * @param glblCmpyCd GlobalCompanyCode
     */
    public static void getPrcGrpTrgtAttrb(NMAL7190_ACMsg bizMsgALine, String glblCmpyCd) {
        PRC_GRP_TRGT_ATTRBTMsg tMsg = new PRC_GRP_TRGT_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcGrpTrgtAttrbCd, bizMsgALine.prcGrpTrgtAttrbCd_A1);
        tMsg = (PRC_GRP_TRGT_ATTRBTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpOpEqualFlg_A1, tMsg.prcGrpOpEqualFlg);
            ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpOpNotEqualFlg_A1, tMsg.prcGrpOpNotEqualFlg);
            ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpOpLikeFlg_A1, tMsg.prcGrpOpLikeFlg);
            ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpOpBtwFlg_A1, tMsg.prcGrpOpBtwFlg);
            ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpConvFlg_A1, tMsg.prcGrpConvFlg);
        }
    }

    /**
     * createPulldownForPrcGrpTrgtTp
     * @param bizMsgALine NMAL7190_ACMsg
     */
    public static void createPulldownForPrcGrpTrgtAttrb(NMAL7190_ACMsg bizMsgALine) {

        PRC_GRP_TRGT_ATTRBTMsg prcGrpTrgtAttrb = new PRC_GRP_TRGT_ATTRBTMsg();
        PRC_GRP_TRGT_ATTRBTMsgArray resultList = new PRC_GRP_TRGT_ATTRBTMsgArray();
        ZYPEZDItemValueSetter.setValue(prcGrpTrgtAttrb.prcGrpTrgtTpCd, bizMsgALine.prcGrpTrgtTpCd_A1.getValue());
        resultList = (PRC_GRP_TRGT_ATTRBTMsgArray) S21CodeTableAccessor.findByCondition(prcGrpTrgtAttrb);

        if (resultList.length() > 0) {
            Map<String, String> tMsgKeys = new HashMap<String, String>();
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "prcGrpTrgtAttrbCd");
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "prcGrpTrgtAttrbDescTxt");
            ZYPPulldownValueSetter.set(resultList, tMsgKeys, bizMsgALine.prcGrpTrgtAttrbCd_P, bizMsgALine.prcGrpTrgtAttrbDescTxt_P);
        }
    }

    /**
     * createPulldownForPrcGrpTrgtTp
     * @param bizMsgALine NMAL7190_ACMsg
     * @param bizMsg NMAL7190CMsg
     */
    public static void setPulldownForPrcGrpOp(NMAL7190_ACMsg bizMsgALine, NMAL7190CMsg bizMsg) {

        int insertIdx = 0;
        for (int i = 0; i < bizMsg.prcGrpOpCd_BK.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.prcGrpOpCd_BK.no(i).getValue())) {
                break;
            }
            String tmpOpCd = bizMsg.prcGrpOpCd_BK.no(i).getValue();
            if (PRC_GRP_OP.EQ.equals(tmpOpCd)) {
                if (ZYPCommonFunc.hasValue(bizMsgALine.prcGrpOpEqualFlg_A1) && ZYPConstant.FLG_ON_Y.equals(bizMsgALine.prcGrpOpEqualFlg_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpOpCd_P.no(insertIdx), bizMsg.prcGrpOpCd_BK.no(i));
                    ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpOpDescTxt_P.no(insertIdx), bizMsg.prcGrpOpDescTxt_BK.no(i));
                    insertIdx++;
                }
            }
            if (PRC_GRP_OP.NOT_EQ.equals(tmpOpCd)) {
                if (ZYPCommonFunc.hasValue(bizMsgALine.prcGrpOpNotEqualFlg_A1) && ZYPConstant.FLG_ON_Y.equals(bizMsgALine.prcGrpOpNotEqualFlg_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpOpCd_P.no(insertIdx), bizMsg.prcGrpOpCd_BK.no(i));
                    ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpOpDescTxt_P.no(insertIdx), bizMsg.prcGrpOpDescTxt_BK.no(i));
                    insertIdx++;
                }
            }
            if (PRC_GRP_OP.LIKE.equals(tmpOpCd)) {
                if (ZYPCommonFunc.hasValue(bizMsgALine.prcGrpOpLikeFlg_A1) && ZYPConstant.FLG_ON_Y.equals(bizMsgALine.prcGrpOpLikeFlg_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpOpCd_P.no(insertIdx), bizMsg.prcGrpOpCd_BK.no(i));
                    ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpOpDescTxt_P.no(insertIdx), bizMsg.prcGrpOpDescTxt_BK.no(i));
                    insertIdx++;
                }
            }
            if (PRC_GRP_OP.BETWEEN.equals(tmpOpCd)) {
                if (ZYPCommonFunc.hasValue(bizMsgALine.prcGrpOpBtwFlg_A1) && ZYPConstant.FLG_ON_Y.equals(bizMsgALine.prcGrpOpBtwFlg_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpOpCd_P.no(insertIdx), bizMsg.prcGrpOpCd_BK.no(i));
                    ZYPEZDItemValueSetter.setValue(bizMsgALine.prcGrpOpDescTxt_P.no(insertIdx), bizMsg.prcGrpOpDescTxt_BK.no(i));
                    insertIdx++;
                }
            }
        }
    }

    /**
     * createPulldownForPrcGrpTrgtTp
     * @param bizMsg NMAL7190CMsg
     * @param glblMsg NMAL7190SMsg
     */
    public static void createPulldownForPrcGrpTrgtTp(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        bizMsg.prcGrpTrgtTpCd_P.clear();
        bizMsg.prcGrpTrgtTpDescTxt_P.clear();
        clearDetailPulldown(glblMsg);

        S21SsmEZDResult ssmResultForTT = NMAL7190Query.getInstance().getPrcGrpTrgtTp(bizMsg, glblMsg);

        if (ssmResultForTT.isCodeNormal()) {
            List<Map<String, Object>> resultListForTT = (List<Map<String, Object>>) ssmResultForTT.getResultObject();

            for (int i = 0; i < ssmResultForTT.getQueryResultCount(); i++) {

                Map<String, Object> resultMapForTT = (Map<String, Object>) resultListForTT.get(i);

                ZYPEZDItemValueSetter.setValue(bizMsg.prcGrpTrgtTpCd_P.no(i), (String) resultMapForTT.get("PRC_GRP_TRGT_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.prcGrpTrgtTpDescTxt_P.no(i), (String) resultMapForTT.get("PRC_GRP_TRGT_TP_DESC_TXT"));
            }
        }
    }

    /**
     * clearDetailPulldown
     * @param glblMsg NMAL7190SMsg
     */
    public static void clearDetailPulldown(NMAL7190SMsg glblMsg) {
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            glblMsg.A.no(i).prcGrpTrgtAttrbCd_A1.clear();
            glblMsg.A.no(i).prcGrpTrgtAttrbCd_P.clear();
            glblMsg.A.no(i).prcGrpTrgtAttrbDescTxt_P.clear();
            glblMsg.A.no(i).prcGrpOpEqualFlg_A1.clear();
            glblMsg.A.no(i).prcGrpOpNotEqualFlg_A1.clear();
            glblMsg.A.no(i).prcGrpOpLikeFlg_A1.clear();
            glblMsg.A.no(i).prcGrpOpBtwFlg_A1.clear();
            glblMsg.A.no(i).prcGrpConvFlg_A1.clear();
            glblMsg.A.no(i).prcGrpOpCd_A1.clear();
            glblMsg.A.no(i).prcGrpOpCd_P.clear();
            glblMsg.A.no(i).prcGrpOpDescTxt_P.clear();
            glblMsg.A.no(i).prcGrpFromTxt_A1.clear();
            glblMsg.A.no(i).prcGrpFromTxt_CD.clear();
            glblMsg.A.no(i).prcGrpThruTxt_A1.clear();
            glblMsg.A.no(i).prcGrpThruTxt_CD.clear();
        }
    }

    /**
     * write CsvFile
     * @param bizMsg NMAL7190CMsg
     * @param glblCmpyCd GlobalCompanyCode
     */
    public static void writeCsvFileContInfo(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg, String glblCmpyCd) {

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV_EXTENSION);
        NMAL7190F00FMsg fMsg = new NMAL7190F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        // write header
        csvOutFile.writeHeader(CSV_HDR);

        // write contents
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            // sMsg -> fMsg
            EZDMsg.copy(glblMsg.A.no(i), null, fMsg, null);
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcGrpTrgtTpCd_A1)) {
                ZYPEZDItemValueSetter.setValue(fMsg.prcGrpTrgtTpDescTxt_A1, ZYPCodeDataUtil.getName(PRC_GRP_TRGT_TP.class, glblCmpyCd, glblMsg.A.no(i).prcGrpTrgtTpCd_A1.getValue()));
            }
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcGrpTrgtAttrbCd_A1)) {
                ZYPEZDItemValueSetter.setValue(fMsg.prcGrpTrgtAttrbDescTxt_A1, ZYPCodeDataUtil.getName(PRC_GRP_TRGT_ATTRB.class, glblCmpyCd, glblMsg.A.no(i).prcGrpTrgtAttrbCd_A1.getValue()));
            }
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).prcGrpOpCd_A1)) {
                ZYPEZDItemValueSetter.setValue(fMsg.prcGrpOpDescTxt_A1, ZYPCodeDataUtil.getName(PRC_GRP_OP.class, glblCmpyCd, glblMsg.A.no(i).prcGrpOpCd_A1.getValue()));
            }
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).effFromDt_A1)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FR, formatDt(glblMsg.A.no(i).effFromDt_A1.getValue()));
            }
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).effThruDt_A1)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TH, formatDt(glblMsg.A.no(i).effThruDt_A1.getValue()));
            }
            // 2023/04/20 QC#61200 Add Start
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).dsAcctNm_A1)) {
                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_A1, glblMsg.A.no(i).dsAcctNm_A1.getValue());
            }
            // 2023/04/20 QC#61200 Add End

            csvOutFile.write();
            fMsg.clear();
        }

        csvOutFile.close();
    }

    /**
     * readCsvHeader_UPLOAD
     * @param dt String
     * @return String formated
     */
    private static String formatDt(String dt) {

        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";
        } else if (dt.length() > YYYYMMDD_LENGTH) {
            dt = dt.substring(0, YYYYMMDD_LENGTH);
        }

        return ZYPDateUtil.formatEzd8ToDisp(dt, true);
    }

    /**
     * validateAndCopyToGlblMsg_UPLOAD
     * @param status int
     * @param count int
     * @param uploadCount int
     * @param glblLineMsg NMAL7190_ASMsgArray
     * @param fMsg fMsgNMAL7190F01FMsg
     * @param bizMsg NMAL7190CMsg
     * @return boolean if error then true else false
     */
    public static boolean validateGlblMsg_UPLOAD(int status, int count, int uploadCount, NMAL7190_ASMsgArray glblLineMsg, NMAL7190F00FMsg fMsg, NMAL7190CMsg bizMsg) {

        /****************************************************************
         * Record count check
         ****************************************************************/
        if (count > glblLineMsg.length()) {
            return true;
        }

        /****************************************************************
         * CSV format check
         ****************************************************************/
        if (checkFormatCSV(status, uploadCount, fMsg, bizMsg, CSV_HDR)) {
            return true;
        }

        /****************************************************************
         * Mandatory check
         ****************************************************************/
        if (checkMandatoryCSV(uploadCount, fMsg, bizMsg)) {
            return true;
        }

        return false;
    }

    /**
     * checkFormatCSV
     * @param status int
     * @param uploadCount int
     * @param fMsg fMsgNMAL7190F01FMsg
     * @param bizMsg NMAL7190CMsg
     * @param colStrList String[]
     * @return boolean if error then true else false
     */
    public static boolean checkFormatCSV(int status, int uploadCount, NMAL7190F00FMsg fMsg, NMAL7190CMsg bizMsg, String[] colStrList) {
        if (status == UPLOAD_READ_ERROR) {
            bizMsg.setMessageInfo(NMAM8191E, new String[] {"Line=" + String.valueOf(uploadCount) });
            return true;
        }

        if (UPLOAD_READ_ERROR < status && status <= UPLOAD_READ_ERROR + colStrList.length) {
            int colIdx = status - UPLOAD_READ_ERROR;
            bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(uploadCount) + "row, " + colStrList[colIdx - 1] + ", " + fMsg.getValueString(colStrList[colIdx - 1], -1) });
            return true;
        }
        if (UPLOAD_FORMAT_ERROR < status && status <= UPLOAD_FORMAT_ERROR + colStrList.length) {
            int colIdx = status - UPLOAD_FORMAT_ERROR;
            bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(uploadCount) + "row, " + colStrList[colIdx - 1] + ", " + fMsg.getValueString(colStrList[colIdx - 1], -1) });
            return true;
        }
        // 2019/03/13 QC#30725 Add Start
        EZDSchemaInfo ezdSchemaInfo = new NMAL7190_ACMsg().getSchema();
        // 2019/03/13 QC#30725 Add End
        if (ZYPCommonFunc.hasValue(fMsg.xxDtTxt_FR)) {
            // 2019/03/13 QC#30725 Add Start
            String baseString = fMsg.xxDtTxt_FR.getValue();
            // 2019/03/13 QC#30725 Add End
            if (fMsg.xxDtTxt_FR.getValue().contains("/")) {
                String sepDeleteString = fMsg.xxDtTxt_FR.getValue();
                // 2019/03/13 QC#30725 Add Start
                sepDeleteString = formatDateStr(sepDeleteString);
                // 2019/03/13 QC#30725 Add End
                sepDeleteString = sepDeleteString.replaceAll("/", "");
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FR, ZYPDateUtil.formatDisp8ToEzd(sepDeleteString));
            }
            // 2019/03/13 QC#30725 Mod Start
            //if (!ZYPDateUtil.isValidDate(fMsg.xxDtTxt_FR.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            if (!ZYPDateUtil.isValidDate(fMsg.xxDtTxt_FR.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)
                    || checkItemCnt(fMsg.xxDtTxt_FR.getValue(), ezdSchemaInfo.getAttr("effFromDt_A1").getDigit())) {
                //bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(uploadCount) + "row, Date From, " + fMsg.xxDtTxt_FR.getValue() });
                bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(uploadCount) + "row, Date From, " + baseString });
                // 2019/03/13 QC#30725 Mod End
                return true;
            }
        } else {
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FR, ZYPDateUtil.getSalesDate());
        }
        if (ZYPCommonFunc.hasValue(fMsg.xxDtTxt_TH)) {
            // 2019/03/13 QC#30725 Add Start
            String baseString = fMsg.xxDtTxt_TH.getValue();
            // 2019/03/13 QC#30725 Add End
            if (fMsg.xxDtTxt_TH.getValue().contains("/")) {
                String sepDeleteString = fMsg.xxDtTxt_TH.getValue();
                // 2019/03/13 QC#30725 Add Start
                sepDeleteString = formatDateStr(sepDeleteString);
                // 2019/03/13 QC#30725 Add End
                sepDeleteString = sepDeleteString.replaceAll("/", "");
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TH, ZYPDateUtil.formatDisp8ToEzd(sepDeleteString));
            }
            // 2019/03/13 QC#30725 Mod Start
            //if (!ZYPDateUtil.isValidDate(fMsg.xxDtTxt_TH.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
            if (!ZYPDateUtil.isValidDate(fMsg.xxDtTxt_TH.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)
                    || checkItemCnt(fMsg.xxDtTxt_TH.getValue(), ezdSchemaInfo.getAttr("effThruDt_A1").getDigit())) {
                //bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(uploadCount) + "row, Date To, " + fMsg.xxDtTxt_TH.getValue() });
                bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(uploadCount) + "row, Date To, " + baseString });
                // 2019/03/13 QC#30725 Mod End
                return true;
            }
        }

        if (ZYPCommonFunc.hasValue(fMsg.prcGrpPrcdNum_A1)) {
            if(BigDecimal.ZERO.compareTo(fMsg.prcGrpPrcdNum_A1.getValue()) > 0 //
                    || fMsg.prcGrpPrcdNum_A1.getValue().compareTo(MAX_PRCD_NUM) > 0){
                bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(uploadCount) + "row, Precedence#, " + fMsg.prcGrpPrcdNum_A1.getValue() });
                return true;
            }
        }

        return false;
    }

    /**
     * Mandatory check
     * @param uploadCount int
     * @param fMsg fMsgNMAL7190F01FMsg
     * @param bizMsg NMAL7190CMsg
     * @return boolean if error then true else false
     */
    public static boolean checkMandatoryCSV(int uploadCount, NMAL7190F00FMsg fMsg, NMAL7190CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(fMsg.prcGrpTrgtTpDescTxt_A1)) {
            bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(uploadCount) + "row, Target Context, " + fMsg.prcGrpTrgtTpDescTxt_A1.getValue() });
            return true;
        }

        if (!ZYPCommonFunc.hasValue(fMsg.prcGrpTrgtAttrbDescTxt_A1)) {
            bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(uploadCount) + "row, Target Attribute Name, " + fMsg.prcGrpTrgtAttrbDescTxt_A1.getValue() });
            return true;
        }

        if (!ZYPCommonFunc.hasValue(fMsg.prcGrpOpDescTxt_A1)) {
            bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(uploadCount) + "row, Operator, " + fMsg.prcGrpOpDescTxt_A1.getValue() });
            return true;
        }

        if (!ZYPCommonFunc.hasValue(fMsg.prcGrpFromTxt_A1)) {
            bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(uploadCount) + "row, Target From, " + fMsg.prcGrpFromTxt_A1.getValue() });
            return true;
        }

        return false;
    }

    /**
     * validateAndCopyToGlblMsg_UPLOAD
     * @param count int
     * @param uploadCount int
     * @param glblLineMsg NMAL7190_ASMsgArray
     * @param fMsg fMsgNMAL7190F01FMsg
     * @param bizMsg NMAL7190CMsg
     * @return boolean if error then true else false
     */
    public static boolean copyToGlblMsg_UPLOAD(int count, int uploadCount, NMAL7190_ASMsgArray glblLineMsg, NMAL7190F00FMsg fMsg, NMAL7190CMsg bizMsg) {

        /****************************************************************
         * Copy FMsg to SMsg.
         ****************************************************************/
        boolean isExist = false;
        String tmpPrcGrpTrgtTpCd = null;
        for (int i = 0; i < bizMsg.prcGrpTrgtTpDescTxt_P.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.prcGrpTrgtTpDescTxt_P.no(i).getValue())) {
                break;
            }
            String tmpTpDescTxt = fMsg.prcGrpTrgtTpDescTxt_A1.getValue();
            if (bizMsg.prcGrpTrgtTpDescTxt_P.no(i).getValue().equals(tmpTpDescTxt)) {
                tmpPrcGrpTrgtTpCd = bizMsg.prcGrpTrgtTpCd_P.no(i).getValue();
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(uploadCount) + "row, Target Context, " + fMsg.prcGrpTrgtTpDescTxt_A1.getValue() });
            return true;
        }
        ZYPEZDItemValueSetter.setValue(glblLineMsg.no(count).prcGrpTrgtTpCd_A1, tmpPrcGrpTrgtTpCd);

        isExist = false;
        PRC_GRP_TRGT_ATTRBTMsg prcGrpTrgtAttrb = new PRC_GRP_TRGT_ATTRBTMsg();
        PRC_GRP_TRGT_ATTRBTMsgArray attrbResultList = new PRC_GRP_TRGT_ATTRBTMsgArray();
        ZYPEZDItemValueSetter.setValue(prcGrpTrgtAttrb.prcGrpTrgtTpCd, tmpPrcGrpTrgtTpCd);
        attrbResultList = (PRC_GRP_TRGT_ATTRBTMsgArray) S21CodeTableAccessor.findByCondition(prcGrpTrgtAttrb);
        if (attrbResultList.length() < 1) {
            bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(uploadCount) + "row, Target Attribute Name, " + fMsg.prcGrpTrgtAttrbDescTxt_A1.getValue() });
            return true;
        }
        int attrbIdx = 0;
        for (; attrbIdx < attrbResultList.length(); attrbIdx++) {
            if (fMsg.prcGrpTrgtAttrbDescTxt_A1.getValue().equals(attrbResultList.no(attrbIdx).prcGrpTrgtAttrbDescTxt.getValue())) {
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(uploadCount) + "row, Target Attribute Name, " + fMsg.prcGrpTrgtAttrbDescTxt_A1.getValue() });
            return true;
        }
        ZYPEZDItemValueSetter.setValue(glblLineMsg.no(count).prcGrpTrgtAttrbCd_A1, attrbResultList.no(attrbIdx).prcGrpTrgtAttrbCd);
        ZYPEZDItemValueSetter.setValue(glblLineMsg.no(count).prcGrpOpEqualFlg_A1, attrbResultList.no(attrbIdx).prcGrpOpEqualFlg);
        ZYPEZDItemValueSetter.setValue(glblLineMsg.no(count).prcGrpOpNotEqualFlg_A1, attrbResultList.no(attrbIdx).prcGrpOpNotEqualFlg);
        ZYPEZDItemValueSetter.setValue(glblLineMsg.no(count).prcGrpOpLikeFlg_A1, attrbResultList.no(attrbIdx).prcGrpOpLikeFlg);
        ZYPEZDItemValueSetter.setValue(glblLineMsg.no(count).prcGrpOpBtwFlg_A1, attrbResultList.no(attrbIdx).prcGrpOpBtwFlg);
        ZYPEZDItemValueSetter.setValue(glblLineMsg.no(count).prcGrpConvFlg_A1, attrbResultList.no(attrbIdx).prcGrpConvFlg);

        isExist = false;
        PRC_GRP_OPTMsg prcGrpOp = new PRC_GRP_OPTMsg();
        PRC_GRP_OPTMsgArray opResultList = new PRC_GRP_OPTMsgArray();
        ZYPEZDItemValueSetter.setValue(prcGrpOp.prcGrpOpDescTxt, fMsg.prcGrpOpDescTxt_A1.getValue());
        opResultList = (PRC_GRP_OPTMsgArray) S21CodeTableAccessor.findByCondition(prcGrpOp);
        if (opResultList.length() != 1) {
            bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(uploadCount) + "row, Operator, " + fMsg.prcGrpOpDescTxt_A1.getValue() });
            return true;
        }
        if (PRC_GRP_OP.EQ.equals(opResultList.no(0).prcGrpOpCd.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(glblLineMsg.no(count).prcGrpOpEqualFlg_A1.getValue())) {
                isExist = true;
            }
        }
        if (PRC_GRP_OP.NOT_EQ.equals(opResultList.no(0).prcGrpOpCd.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(glblLineMsg.no(count).prcGrpOpNotEqualFlg_A1.getValue())) {
                isExist = true;
            }
        }
        if (PRC_GRP_OP.LIKE.equals(opResultList.no(0).prcGrpOpCd.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(glblLineMsg.no(count).prcGrpOpLikeFlg_A1.getValue())) {
                isExist = true;
            }
        }
        if (PRC_GRP_OP.BETWEEN.equals(opResultList.no(0).prcGrpOpCd.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(glblLineMsg.no(count).prcGrpOpBtwFlg_A1.getValue())) {
                isExist = true;
            }
        }
        if (!isExist) {
            bizMsg.setMessageInfo(NMAM8191E, new String[] {String.valueOf(uploadCount) + "row, Operator, " + fMsg.prcGrpOpDescTxt_A1.getValue() });
            return true;
        }
        ZYPEZDItemValueSetter.setValue(glblLineMsg.no(count).prcGrpOpCd_A1, opResultList.no(0).prcGrpOpCd.getValue());

        ZYPEZDItemValueSetter.setValue(glblLineMsg.no(count).prcGrpFromTxt_A1, fMsg.prcGrpFromTxt_A1.getValue());
        // 2023/04/20 QC#61200 Add Start    
        if (PRC_GRP_TRGT_ATTRB.ACCOUNT_NUMBER.equals(glblLineMsg.no(count).prcGrpTrgtAttrbCd_A1.getValue())) {
            if (ZYPCommonFunc.hasValue(glblLineMsg.no(count).prcGrpFromTxt_A1)) {
                SELL_TO_CUSTTMsg tMsg = new SELL_TO_CUSTTMsg();
                tMsg.setSQLID("001");
                tMsg.setConditionValue("glblCmpyCd01", attrbResultList.no(attrbIdx).glblCmpyCd.getValue());
                tMsg.setConditionValue("sellToCustCd01", glblLineMsg.no(count).prcGrpFromTxt_A1.getValue());
                SELL_TO_CUSTTMsgArray tMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
                if (tMsgArray.length() == 1) {
                    ZYPEZDItemValueSetter.setValue(glblLineMsg.no(count).dsAcctNm_A1, tMsgArray.no(0).dsAcctNm);
                }
            }
        }
        // 2023/04/20 QC#61200 Add Start
        if (PRC_GRP_OP.BETWEEN.equals(opResultList.no(0).prcGrpOpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(glblLineMsg.no(count).prcGrpThruTxt_A1, fMsg.prcGrpThruTxt_A1.getValue());
        }
        ZYPEZDItemValueSetter.setValue(glblLineMsg.no(count).prcGrpPrcdNum_A1, fMsg.prcGrpPrcdNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(glblLineMsg.no(count).effFromDt_A1, fMsg.xxDtTxt_FR.getValue());
        ZYPEZDItemValueSetter.setValue(glblLineMsg.no(count).effThruDt_A1, fMsg.xxDtTxt_TH.getValue());
        return false;
    }

    /**
     * readHeaderCsvFile.
     * @param mappedFile EZDCSVInFile
     * @param bizMsg NMAL7010CMsg
     * @return boolean
     */
    public static boolean readHeaderCsvFile(EZDCSVInFile mappedFile, NMAL7190CMsg bizMsg) {
        int header = mappedFile.read();
        if (header == 1) {
            bizMsg.setMessageInfo(NMAM8191E, new String[] {"Header Record" });
            return false;
        }
        return true;
    }

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsg EZDSMsg
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsg sMsg, EZDSMsgArray sMsgArray) {

        NMAL7190CMsg bizMsg = (NMAL7190CMsg) cMsg;
        NMAL7190SMsg glblMsg = (NMAL7190SMsg) sMsg;
        EZDMsg.copy(glblMsg, null, bizMsg, null);

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
                NMAL7190_ACMsg cMsgALine = (NMAL7190_ACMsg) cMsgArray.get(indexOfCMsg);
                if (ZYPCommonFunc.hasValue(cMsgALine.prcGrpTrgtTpCd_A1)) {
                    createPulldownForPrcGrpTrgtAttrb(cMsgALine);
                }
                if (ZYPCommonFunc.hasValue(cMsgALine.prcGrpTrgtAttrbCd_A1)) {
                    setPulldownForPrcGrpOp(cMsgALine, bizMsg);
                }

            } else {

                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    /**
     * Update the global Message.
     * @param bizMsg NMAL7190CMsg
     * @param glblMsg NMAL7190SMsg
     */
    public static void updateGlblMsg(NMAL7190CMsg bizMsg, NMAL7190SMsg glblMsg) {
        ZYPEZDItemValueSetter.setValue(glblMsg.prcGrpPk, bizMsg.prcGrpPk);
        ZYPEZDItemValueSetter.setValue(glblMsg.prcGrpPk_BK, bizMsg.prcGrpPk_BK);
        ZYPEZDItemValueSetter.setValue(glblMsg.prcGrpNm, bizMsg.prcGrpNm);
        ZYPEZDItemValueSetter.setValue(glblMsg.prcGrpDescTxt, bizMsg.prcGrpDescTxt);
        ZYPEZDItemValueSetter.setValue(glblMsg.prcGrpTpCd, bizMsg.prcGrpTpCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.prcGrpTrxTpCd, bizMsg.prcGrpTrxTpCd);
        ZYPEZDItemValueSetter.setValue(glblMsg.actvFlg, bizMsg.actvFlg);
        ZYPEZDItemValueSetter.setValue(glblMsg.effFromDt, bizMsg.effFromDt);
        ZYPEZDItemValueSetter.setValue(glblMsg.effThruDt, bizMsg.effThruDt);

        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
        }
    }

    /**
     * <pre>
     * checkDupAttrb
     * </pre>
     * @param msgAry EZDMsgArray
     * @param chkItemNmList Message Item Name for check.
     * @return Duplicate Index
     */
    public static Integer[] checkDupAttrb(EZDMsgArray msgAry, String[] chkItemNmList) {
        return checkDupAttrbByGrp(msgAry, chkItemNmList, null);
    }

    /**
     * <pre>
     * checkDupAttrbByGrp
     * </pre>
     * @param msgAry EZDMsgArray
     * @param chkItemNmList Message Item Name for check
     * @param grpItemNmList Message Item name for grouping
     * @return Duplicate Index
     */
    public static Integer[] checkDupAttrbByGrp(EZDMsgArray msgAry, String[] chkItemNmList, String[] grpItemNmList) {

        if (msgAry.getValidCount() <= 1) {
            return new Integer[0];
        }

        if (chkItemNmList == null || chkItemNmList.length == 0) {
            Integer[] errIdxList = new Integer[msgAry.getValidCount()];
            for (int i = 0; i < errIdxList.length; i++) {
                errIdxList[i] = i;
            }
            return errIdxList;
        }

        List<Integer> errIdxList = new ArrayList<Integer>(msgAry.getValidCount());
        // 2018/08/01 Del Start QC#26297
//        List<String> grpList = new ArrayList<String>();
        // 2018/08/01 Del End QC#26297
        Map<String, Integer> compMap = new HashMap<String, Integer>();

        for (int i = 0; i < msgAry.getValidCount(); i++) {
            EZDMsg msg = msgAry.get(i);
            // 2018/08/01 Del Start QC#26297
//            String grpKey = makeCompVal(msg, grpItemNmList);
            // 2018/08/01 Del End QC#26297
            String compVal = makeCompVal(msg, chkItemNmList);

            // 2018/08/01 Del Start QC#26297
//            if (grpList.contains(grpKey)) {
                // 2018/08/01 Del End QC#26297
            if (compMap.containsKey(compVal)) {
                // 2018/08/01 Mod Start QC#26297
//                  errIdxList.add(i);
//                  errIdxList.add(0, compMap.get(compVal));
                if (!errIdxList.contains(compMap.get(compVal))) {
                    errIdxList.add(i);
                    errIdxList.add(compMap.get(compVal));
                    continue;
                } else {
                    errIdxList.add(i);
                    continue;
                }
                // 2018/08/01 Mod End QC#26297
            }
            compMap.put(compVal, new Integer(i));

            continue;
           // 2018/08/01 Del Start QC#26297
//            }
//            grpList.add(grpKey);
           // 2018/08/01 Del End QC#26297
        }
        return errIdxList.toArray(new Integer[errIdxList.size()]);
    }

    /**
     * <pre>
     * checkDupAttrbByGrp
     * </pre>
     * @param msgAry EZDMsgArray
     * @param effFromItemNm Effective From Item Name
     * @param effThruItemNm Effective Thru Item Name
     * @param grpItemNmList Message Item name for grouping
     * @return Duplicate Index
     */
    public static Integer[] checkDupTermByGrp(EZDMsgArray msgAry, String effFromItemNm, String effThruItemNm, String[] grpItemNmList) {

        if (msgAry.getValidCount() <= 1) {
            return new Integer[0];
        }

        if (!ZYPCommonFunc.hasValue(effFromItemNm) || !ZYPCommonFunc.hasValue(effThruItemNm)) {
            Integer[] errIdxList = new Integer[msgAry.getValidCount()];
            for (int i = 0; i < errIdxList.length; i++) {
                errIdxList[i] = i;
            }
            return errIdxList;
        }

        List<Integer> errIdxList = new ArrayList<Integer>(msgAry.getValidCount());
        List<String[]> msgList = new ArrayList<String[]>();

        for (int i = 0; i < msgAry.getValidCount(); i++) {
            EZDMsg msg = msgAry.get(i);
            String grpKey = makeCompVal(msg, grpItemNmList);
            String effFromDt = msg.getValueString(effFromItemNm, 0);
            String effThruDt = msg.getValueString(effThruItemNm, 0);
            if (!ZYPCommonFunc.hasValue(effThruDt)) {
                effThruDt = "99991231";
            }
            msgList.add(new String[] {grpKey, effFromDt, effThruDt });
        }

        for (int i = 0; i < msgAry.getValidCount() - 1; i++) {

            if (errIdxList.contains(i)) {
                continue;
            }

            String[] msg1 = msgList.get(i);
            String grpKey1 = msg1[0];
            String effFromDt1 = msg1[1];
            String effThruDt1 = msg1[2];

            boolean dupFlg = false;
            for (int j = i + 1; j < msgAry.getValidCount(); j++) {

                String[] msg2 = msgList.get(j);
                String grpKey2 = msg2[0];
                String effFromDt2 = msg2[1];
                String effThruDt2 = msg2[2];

                if (!grpKey1.equals(grpKey2)) {
                    continue;
                }

                if (effFromDt1.compareTo(effFromDt2) < 0) {

                    if (effThruDt1.compareTo(effFromDt2) >= 0) {
                        // error
                        errIdxList.add(j);
                        dupFlg = true;
                    }

                } else if (effFromDt1.compareTo(effFromDt2) > 0) {

                    if (effFromDt1.compareTo(effThruDt2) <= 0) {
                        // error
                        errIdxList.add(j);
                        dupFlg = true;
                    }

                } else {
                    // error
                    errIdxList.add(j);
                    dupFlg = true;
                }

            }

            if (dupFlg) {
                errIdxList.add(0, i);
            }
        }

        return errIdxList.toArray(new Integer[errIdxList.size()]);
    }

    /**
     * <pre>
     * changeDupTermByGrp
     * </pre>
     * @param msgAry EZDMsgArray
     * @param effFromItemNm Effective From Item Name
     * @param effThruItemNm Effective Thru Item Name
     * @param grpItemNmList Message Item name for grouping
     * @return Duplicate Index
     */
    public static Integer[] changeDupTermByGrp(EZDMsgArray msgAry, String effFromItemNm, String effThruItemNm, String[] grpItemNmList) {

        if (msgAry.getValidCount() <= 1) {
            return new Integer[0];
        }

        if (!ZYPCommonFunc.hasValue(effFromItemNm) || !ZYPCommonFunc.hasValue(effThruItemNm)) {
            Integer[] errIdxList = new Integer[msgAry.getValidCount()];
            for (int i = 0; i < errIdxList.length; i++) {
                errIdxList[i] = i;
            }
            return errIdxList;
        }

        List<Integer> errIdxList = new ArrayList<Integer>(msgAry.getValidCount());

        for (int i = 0; i < msgAry.getValidCount() - 1; i++) {

            if (errIdxList.contains(i)) {
                continue;
            }

            EZDMsg msg1 = msgAry.get(i);
            String grpKey1 = makeCompVal(msg1, grpItemNmList);
            String effFromDt1 = msg1.getValueString(effFromItemNm, 0);
            String effThruDt1 = msg1.getValueString(effThruItemNm, 0);
            if (!ZYPCommonFunc.hasValue(effThruDt1)) {
                effThruDt1 = "99991231";
            }

            for (int j = i + 1; j < msgAry.getValidCount(); j++) {
                EZDMsg msg2 = msgAry.get(j);
                String grpKey2 = makeCompVal(msg2, grpItemNmList);
                String effFromDt2 = msg2.getValueString(effFromItemNm, 0);
                String effThruDt2 = msg2.getValueString(effThruItemNm, 0);
                if (!ZYPCommonFunc.hasValue(effThruDt2)) {
                    effThruDt2 = "99991231";
                }

                if (!grpKey1.equals(grpKey2)) {
                    continue;
                }

                if (effFromDt1.compareTo(effFromDt2) < 0) {
                    if (effThruDt1.compareTo(effFromDt2) >= 0) {
                        msg1.setValue(effThruItemNm, -1, ZYPDateUtil.addDays(effFromDt2, -1));
                        effThruDt1 = msg1.getValueString(effThruItemNm, 0);
                    }
                } else if (effFromDt1.compareTo(effFromDt2) > 0) {

                    if (effFromDt1.compareTo(effThruDt2) <= 0) {
                        msg2.setValue(effThruItemNm, -1, ZYPDateUtil.addDays(effFromDt1, -1));
                        effThruDt2 = msg2.getValueString(effThruItemNm, 0);
                    }

                }
            }
        }

        return errIdxList.toArray(new Integer[0]);
    }

    /**
     * <pre>
     * makeCompVal
     * </pre>
     * @param msg EZDMsg
     * @param itemNmList Message Item Name
     * @return
     */
    private static String makeCompVal(EZDMsg msg, String[] itemNmList) {

        if (itemNmList == null) {
            return "";
        }

        StringBuilder compVal = new StringBuilder();

        for (String chkAttrbNm : itemNmList) {
            EZDItemAttribute itemAttrb = msg.getAttr(chkAttrbNm);

            String attrbVal = "";
            switch (itemAttrb.getType()) {
                case EZDItemAttrDefines.TYPE_SEISU_SYOSU:
                    if (ZYPCommonFunc.hasValue(msg.getValueBigDecimal(chkAttrbNm, 0))) {
                        attrbVal = msg.getValueBigDecimal(chkAttrbNm, 0).toString();
                    }
                    break;
                default:
                    attrbVal = msg.getValueString(chkAttrbNm, 0);
            }
            compVal.append(attrbVal);
            compVal.append(",");
        }

        return compVal.toString();
    }

    /**
     * <pre>
     * isNumber
     * </pre>
     * @param val String
     * @return check result
     */
    public static boolean isNumber(String val) {
        String regex = NUMBER_PATTERN;
        Pattern p = Pattern.compile(regex);
        Matcher m1 = p.matcher(val);
        return m1.find();
    }

    /**
     * convertPrcGrpAttrb(Code to Name & Name to Code)
     * @param sMsgALine NMAL7190_ASMsg
     * @param convType Code to Name:0,Name to Code:1
     * @param prcGrpTrgtAttrbCd Price target attribute code
     * @param codeVal code value
     * @param nameVal name value
     * @return Can to get result
     */
    public static boolean convertPrcGrpAttrb(int convType, String prcGrpTrgtAttrbCd, EZDSStringItem codeVal, EZDSStringItem nameVal) {
        boolean isGet = false;

        if (convType == CONVTYPE_NAMETOCODE) {
            isGet = NMXC105001PriceMasterUtil.getPrcGrpTrgtAttrbCd(prcGrpTrgtAttrbCd, nameVal, codeVal);
        } else if (convType == CONVTYPE_CODETONAME) {
            isGet = NMXC105001PriceMasterUtil.getPrcGrpTrgtAttrbNm(prcGrpTrgtAttrbCd, codeVal, nameVal);
        }
        return isGet;
    }

    /**
     * <pre>
     * clearPrcGrpOpPulldown
     * </pre>
     * @param bizMsgALine NMAL7190_ACMsg
     */
    public static void clearPrcGrpTrgtAttrbPulldown(NMAL7190_ACMsg bizMsgALine) {
        bizMsgALine.prcGrpTrgtAttrbCd_A1.clear();
        bizMsgALine.prcGrpTrgtAttrbCd_P.clear();
        bizMsgALine.prcGrpTrgtAttrbDescTxt_P.clear();
        clearPrcGrpTrgtAttrbFlg(bizMsgALine);
        clearPrcGrpOpPulldown(bizMsgALine);
        clearPrcGrpTxt(bizMsgALine);
    }

    /**
     * <pre>
     * clearPrcGrpOpPulldown
     * </pre>
     * @param bizMsgALine NMAL7190_ACMsg
     */
    public static void clearPrcGrpTrgtAttrbFlg(NMAL7190_ACMsg bizMsgALine) {
        bizMsgALine.prcGrpOpEqualFlg_A1.clear();
        bizMsgALine.prcGrpOpNotEqualFlg_A1.clear();
        bizMsgALine.prcGrpOpLikeFlg_A1.clear();
        bizMsgALine.prcGrpOpBtwFlg_A1.clear();
        bizMsgALine.prcGrpConvFlg_A1.clear();
    }

    /**
     * <pre>
     * clearPrcGrpOpPulldown
     * </pre>
     * @param bizMsgALine NMAL7190_ACMsg
     */
    public static void clearPrcGrpOpPulldown(NMAL7190_ACMsg bizMsgALine) {
        bizMsgALine.prcGrpOpCd_A1.clear();
        bizMsgALine.prcGrpOpCd_P.clear();
        bizMsgALine.prcGrpOpDescTxt_P.clear();
    }

    /**
     * <pre>
     * clearPrcGrpOpPulldown
     * </pre>
     * @param bizMsgALine NMAL7190_ACMsg
     */
    public static void clearPrcGrpTxt(NMAL7190_ACMsg bizMsgALine) {
        bizMsgALine.prcGrpFromTxt_A1.clear();
        bizMsgALine.prcGrpFromTxt_CD.clear();
        bizMsgALine.prcGrpThruTxt_A1.clear();
        bizMsgALine.prcGrpThruTxt_CD.clear();
    }

    /**
     * <pre>
     * clearPrcGrpOpPulldown
     * </pre>
     * @param bizMsgALine NMAL7190_ACMsg
     */
    public static boolean convertPrcGrpAttrbForItemNumber(EZDSStringItem prcGrpTxt_FROM, EZDSStringItem prcGrpTxt_TO, EZDSStringItem prcGrpConvFlg, String prcGrpTrgtAttrbCd, String glblCmpyCd, String logicMode) {
        if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
            if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                    return false;
                }
            } else {
                if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                    return false;
                }
            }
        } else {
            String mdseCd = "";
            boolean isOver = false;
            isOver = false;
            ORD_TAKE_MDSETMsg ordTakeInTMsg = new ORD_TAKE_MDSETMsg();

            mdseCd = prcGrpTxt_FROM.getValue();
            if (mdseCd.length() > 8) {
                mdseCd = mdseCd.substring(0, 8);
                isOver = true;
            }
            ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.ordTakeMdseCd, mdseCd);
            ORD_TAKE_MDSETMsg ordTakeOutTMsg = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(ordTakeInTMsg);
            if (ordTakeOutTMsg == null) {
                mdseCd = prcGrpTxt_FROM.getValue();

                MDSETMsg mdseInTMsg = new MDSETMsg();
                ZYPEZDItemValueSetter.setValue(mdseInTMsg.glblCmpyCd, glblCmpyCd);
                if (checkValDigit(prcGrpTxt_FROM.getValue().length(), mdseInTMsg.getAttr("mdseCd"))) {
                    ZYPEZDItemValueSetter.setValue(mdseInTMsg.mdseCd, mdseCd);
                } else {
                    prcGrpTxt_FROM.setErrorInfo(1, NMAM0163E, new String[] {mdseCd, "Merchandise" });
                    return false;
                }
                MDSETMsg mdseOutTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseInTMsg);
                if (mdseOutTMsg == null) {
                    prcGrpTxt_FROM.setErrorInfo(1, NMAM0163E, new String[] {mdseCd, "Merchandise" });
                    return false;
                }
            } else {
                if (isOver) {
                    prcGrpTxt_FROM.setErrorInfo(1, NMAM8216E);
                    return false;
                }

            }

            ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
        }
        return true;
    }

    /**
     * checkDetailExist
     * @param prcGrpTxt_FROM glblMsg.A.no(i).prcGrpThruTxt_A1
     * @param prcGrpTxt_TO glblMsg.A.no(i).prcGrpThruTxt_CD
     * @param prcGrpConvFlg glblMsg.A.no(i).prcGrpConvFlg_A1
     * @param prcGrpTrgtAttrbCd
     * glblMsg.A.no(i).prcGrpTrgtAttrbCd_A1.getValue()
     * @param glblCmpyCd getGlobalCompanyCode()
     * @param logicMode '02':Search, '06':Submit
     */
    public static Boolean convertPrcGrpAttrb(EZDSStringItem prcGrpTxt_FROM, EZDSStringItem prcGrpTxt_TO, EZDSStringItem prcGrpConvFlg, String prcGrpTrgtAttrbCd, String glblCmpyCd, String logicMode) {
        if (PRC_GRP_TRGT_ATTRB.ITEM_TYPE.equals(prcGrpTrgtAttrbCd)) {
            MDSE_ITEM_TPTMsg inTMsg = new MDSE_ITEM_TPTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                MDSE_ITEM_TPTMsg outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                if (checkValDigit(prcGrpTxt_FROM.getValue().length(), inTMsg.getAttr("mdseItemTpCd"))) {
                    ZYPEZDItemValueSetter.setValue(inTMsg.mdseItemTpCd, prcGrpTxt_FROM);
                } else {
                    return false;
                }
                outTMsg = (MDSE_ITEM_TPTMsg) S21CodeTableAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.ITEM_CLASSIFICATION.equals(prcGrpTrgtAttrbCd)) {
            MDSE_ITEM_CLS_TPTMsg inTMsg = new MDSE_ITEM_CLS_TPTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                MDSE_ITEM_CLS_TPTMsg outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                if (checkValDigit(prcGrpTxt_FROM.getValue().length(), inTMsg.getAttr("mdseItemClsTpCd"))) {
                    ZYPEZDItemValueSetter.setValue(inTMsg.mdseItemClsTpCd, prcGrpTxt_FROM);
                } else {
                    return false;
                }
                outTMsg = (MDSE_ITEM_CLS_TPTMsg) S21CodeTableAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.MERCHANDISE_TYPE.equals(prcGrpTrgtAttrbCd)) {
            COA_PROJTMsg inTMsg = new COA_PROJTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                COA_PROJTMsg outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                if (checkValDigit(prcGrpTxt_FROM.getValue().length(), inTMsg.getAttr("coaProjCd"))) {
                    ZYPEZDItemValueSetter.setValue(inTMsg.coaProjCd, prcGrpTxt_FROM);
                } else {
                    return false;
                }
                outTMsg = (COA_PROJTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_CODE.equals(prcGrpTrgtAttrbCd)) {
            COA_PRODTMsg inTMsg = new COA_PRODTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                COA_PRODTMsg outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                if (checkValDigit(prcGrpTxt_FROM.getValue().length(), inTMsg.getAttr("coaProdCd"))) {
                    ZYPEZDItemValueSetter.setValue(inTMsg.coaProdCd, prcGrpTxt_FROM);
                } else {
                    return false;
                }
                outTMsg = (COA_PRODTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.ITEM_MARKETING_MODEL.equals(prcGrpTrgtAttrbCd)) {
            MKT_MDLTMsg inTMsg = new MKT_MDLTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                MKT_MDLTMsg outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                if (checkValDigit(prcGrpTxt_FROM.getValue().length(), inTMsg.getAttr("mktMdlCd"))) {
                    ZYPEZDItemValueSetter.setValue(inTMsg.mktMdlCd, prcGrpTxt_FROM);
                } else {
                    return false;
                }
                outTMsg = (MKT_MDLTMsg) S21CodeTableAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_1.equals(prcGrpTrgtAttrbCd)) {
            PROD_CTRLTMsg inTMsg = new PROD_CTRLTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                PROD_CTRLTMsg outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                if (checkValDigit(prcGrpTxt_FROM.getValue().length(), inTMsg.getAttr("prodCtrlCd"))) {
                    ZYPEZDItemValueSetter.setValue(inTMsg.prodCtrlCd, prcGrpTxt_FROM);
                } else {
                    return false;
                }
                outTMsg = (PROD_CTRLTMsg) S21CodeTableAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    return false;
                } else if (S21StringUtil.isEquals(MDSE_STRU_ELMNT_TP.MERCHANDISE_GROUP, outTMsg.mdseStruElmntTpCd.getValue())) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_2.equals(prcGrpTrgtAttrbCd)) {
            PROD_CTRLTMsg inTMsg = new PROD_CTRLTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                PROD_CTRLTMsg outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                if (checkValDigit(prcGrpTxt_FROM.getValue().length(), inTMsg.getAttr("prodCtrlCd"))) {
                    ZYPEZDItemValueSetter.setValue(inTMsg.prodCtrlCd, prcGrpTxt_FROM);
                } else {
                    return false;
                }
                outTMsg = (PROD_CTRLTMsg) S21CodeTableAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    return false;
                } else if (S21StringUtil.isEquals(MDSE_STRU_ELMNT_TP.PRODUCT_LINE, outTMsg.mdseStruElmntTpCd.getValue())) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_3.equals(prcGrpTrgtAttrbCd)) {
            PROD_CTRLTMsg inTMsg = new PROD_CTRLTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                PROD_CTRLTMsg outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                if (checkValDigit(prcGrpTxt_FROM.getValue().length(), inTMsg.getAttr("prodCtrlCd"))) {
                    ZYPEZDItemValueSetter.setValue(inTMsg.prodCtrlCd, prcGrpTxt_FROM);
                } else {
                    return false;
                }
                outTMsg = (PROD_CTRLTMsg) S21CodeTableAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    return false;
                } else if (S21StringUtil.isEquals(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2, outTMsg.mdseStruElmntTpCd.getValue())) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_4.equals(prcGrpTrgtAttrbCd)) {
            PROD_CTRLTMsg inTMsg = new PROD_CTRLTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                PROD_CTRLTMsg outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                if (checkValDigit(prcGrpTxt_FROM.getValue().length(), inTMsg.getAttr("prodCtrlCd"))) {
                    ZYPEZDItemValueSetter.setValue(inTMsg.prodCtrlCd, prcGrpTxt_FROM);
                } else {
                    return false;
                }
                outTMsg = (PROD_CTRLTMsg) S21CodeTableAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    return false;
                } else if (S21StringUtil.isEquals(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3, outTMsg.mdseStruElmntTpCd.getValue())) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_5.equals(prcGrpTrgtAttrbCd)) {
            PROD_CTRLTMsg inTMsg = new PROD_CTRLTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                PROD_CTRLTMsg outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                if (checkValDigit(prcGrpTxt_FROM.getValue().length(), inTMsg.getAttr("prodCtrlCd"))) {
                    ZYPEZDItemValueSetter.setValue(inTMsg.prodCtrlCd, prcGrpTxt_FROM);
                } else {
                    return false;
                }
                outTMsg = (PROD_CTRLTMsg) S21CodeTableAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.MODEL_NAME.equals(prcGrpTrgtAttrbCd)) {
            MDL_NMTMsg inTMsg = new MDL_NMTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                MDL_NMTMsgArray outTMsg = null;
                inTMsg.setSQLID("001");
                inTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
                inTMsg.setConditionValue("t_MdlNm01", prcGrpTxt_FROM.getValue());
                outTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.MODEL_SERIES_NAME.equals(prcGrpTrgtAttrbCd)) {
            DS_MDL_GRPTMsg inTMsg = new DS_MDL_GRPTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                DS_MDL_GRPTMsgArray outTMsg = null;
                inTMsg.setSQLID("006");
                inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                inTMsg.setConditionValue("mdlGrpDescTxt01", prcGrpTxt_FROM.getValue());
                outTMsg = (DS_MDL_GRPTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.ACCOUNT_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            SELL_TO_CUSTTMsg inTMsg = new SELL_TO_CUSTTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                SELL_TO_CUSTTMsgArray outTMsg = null;
                inTMsg.setSQLID("001");
                inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                inTMsg.setConditionValue("sellToCustCd01", prcGrpTxt_FROM.getValue());
                outTMsg = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.ACCOUNT_NAME.equals(prcGrpTrgtAttrbCd)) {
            SELL_TO_CUSTTMsg inTMsg = new SELL_TO_CUSTTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                SELL_TO_CUSTTMsgArray outTMsg = null;
                inTMsg.setSQLID("505");
                inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                inTMsg.setConditionValue("dsAcctNm01", prcGrpTxt_FROM.getValue());
                outTMsg = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.SIC_CODE.equals(prcGrpTrgtAttrbCd)) {
            INDY_TPTMsg inTMsg = new INDY_TPTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                INDY_TPTMsg outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                if (checkValDigit(prcGrpTxt_FROM.getValue().length(), inTMsg.getAttr("indyTpCd"))) {
                    ZYPEZDItemValueSetter.setValue(inTMsg.indyTpCd, prcGrpTxt_FROM);
                } else {
                    return false;
                }
                outTMsg = (INDY_TPTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.LOCATION_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            ACCT_LOCTMsg inTMsg = new ACCT_LOCTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                ACCT_LOCTMsgArray outTMsg = null;
                inTMsg.setSQLID("001");
                inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                inTMsg.setConditionValue("locNum01", prcGrpTxt_FROM.getValue());
                outTMsg = (ACCT_LOCTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.CLASSIFICATION.equals(prcGrpTrgtAttrbCd)) {
            DS_ACCT_CLSTMsg inTMsg = new DS_ACCT_CLSTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                DS_ACCT_CLSTMsg outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                if (checkValDigit(prcGrpTxt_FROM.getValue().length(), inTMsg.getAttr("dsAcctClsCd"))) {
                    ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctClsCd, prcGrpTxt_FROM);
                } else {
                    return false;
                }
                outTMsg = (DS_ACCT_CLSTMsg) S21CodeTableAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.CATEGORY.equals(prcGrpTrgtAttrbCd)) {
            DS_ACCT_TPTMsg inTMsg = new DS_ACCT_TPTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                DS_ACCT_TPTMsg outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                if (checkValDigit(prcGrpTxt_FROM.getValue().length(), inTMsg.getAttr("dsAcctTpCd"))) {
                    ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctTpCd, prcGrpTxt_FROM);
                } else {
                    return false;
                }
                outTMsg = (DS_ACCT_TPTMsg) S21CodeTableAccessor.findByKey(inTMsg);
                if (outTMsg == null) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.DEALER_CODE.equals(prcGrpTrgtAttrbCd)) {
            DS_ACCT_DLRTMsg inTMsg = new DS_ACCT_DLRTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                DS_ACCT_DLRTMsgArray outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctDlrDescTxt, prcGrpTxt_FROM);
                outTMsg = (DS_ACCT_DLRTMsgArray) S21CodeTableAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.CSMP_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            CSMP_CONTRTMsg inTMsg = new CSMP_CONTRTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                CSMP_CONTRTMsgArray outTMsg = null;
                inTMsg.setSQLID("001");
                inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                inTMsg.setConditionValue("csmpNum01", prcGrpTxt_FROM.getValue());
                outTMsg = (CSMP_CONTRTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.CSA_CSMP_REFERENCE_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            CSMP_CONTRTMsg inTMsg = new CSMP_CONTRTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                CSMP_CONTRTMsgArray outTMsg = null;
                inTMsg.setSQLID("002");
                inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                inTMsg.setConditionValue("dlrRefNum01", prcGrpTxt_FROM.getValue());
                outTMsg = (CSMP_CONTRTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.ASSOCIATION_NAME.equals(prcGrpTrgtAttrbCd)) {
            PRC_CONTRTMsg inTMsg = new PRC_CONTRTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                PRC_CONTRTMsgArray outTMsg = null;
                inTMsg.setSQLID("002");
                inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                inTMsg.setConditionValue("prcContrNm01", prcGrpTxt_FROM.getValue());
                outTMsg = (PRC_CONTRTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.CSA_PRICE_CONTRACT_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            PRC_CONTRTMsg inTMsg = new PRC_CONTRTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                PRC_CONTRTMsgArray outTMsg = null;
                inTMsg.setSQLID("001");
                inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                inTMsg.setConditionValue("prcContrNum01", prcGrpTxt_FROM.getValue());
                outTMsg = (PRC_CONTRTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.ORDER_CATEGORY.equals(prcGrpTrgtAttrbCd)) {
            DS_ORD_CATGTMsg inTMsg = new DS_ORD_CATGTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                DS_ORD_CATGTMsgArray outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.dsOrdCatgDescTxt, prcGrpTxt_FROM);
                outTMsg = (DS_ORD_CATGTMsgArray) S21CodeTableAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.ORDER_TYPE.equals(prcGrpTrgtAttrbCd)) {
            DS_ORD_TPTMsg inTMsg = new DS_ORD_TPTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                DS_ORD_TPTMsgArray outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.dsOrdTpDescTxt, prcGrpTxt_FROM);
                outTMsg = (DS_ORD_TPTMsgArray) S21CodeTableAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        } else if (PRC_GRP_TRGT_ATTRB.LINE_CATEGORY_TYPE.equals(prcGrpTrgtAttrbCd)) {
            DS_ORD_LINE_CATGTMsg inTMsg = new DS_ORD_LINE_CATGTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                DS_ORD_LINE_CATGTMsgArray outTMsg = null;
                inTMsg.setSQLID("001");
                inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                inTMsg.setConditionValue("dsOrdLineCatgDescTxt01", prcGrpTxt_FROM.getValue());
                outTMsg = (DS_ORD_LINE_CATGTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        // QC#18789 2017/08/14 add start
        } else if (PRC_GRP_TRGT_ATTRB.CHANNEL.equals(prcGrpTrgtAttrbCd)) {
            COA_CHTMsg inTMsg = new COA_CHTMsg();
            if (ZYPCommonFunc.hasValue(prcGrpConvFlg) && ZYPConstant.FLG_ON_Y.equals(prcGrpConvFlg.getValue())) {
                if (LOGIC_MODE_SUBMIT.equals(logicMode)) {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, prcGrpTrgtAttrbCd, prcGrpTxt_TO, prcGrpTxt_FROM)) {
                        return false;
                    }
                } else {
                    if (!NMAL7190CommonLogic.convertPrcGrpAttrb(CONVTYPE_CODETONAME, prcGrpTrgtAttrbCd, prcGrpTxt_FROM, prcGrpTxt_TO)) {
                        return false;
                    }
                }
            } else {
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.coaChCd, prcGrpTxt_FROM.getValue());
                inTMsg = (COA_CHTMsg) EZDTBLAccessor.findByKey(inTMsg);

                if (inTMsg == null || !ZYPConstant.FLG_ON_Y.equals(inTMsg.coaEnblFlg.getValue())) {
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(prcGrpTxt_TO, prcGrpTxt_FROM);
            }
            return true;
        }
        // QC#18789 2017/08/14 add end

        return false;
    }

    /**
     * checkDetailExist
     * @param prcGrpTrgtAttrbCd
     * glblMsg.A.no(i).prcGrpTrgtAttrbCd_A1.getValue()
     * @return Master Name
     */
    public static String getMsterName(String prcGrpTrgtAttrbCd) {
        if (PRC_GRP_TRGT_ATTRB.ITEM_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            MDSETMsg inTMsg = new MDSETMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.ITEM_TYPE.equals(prcGrpTrgtAttrbCd)) {
            MDSE_ITEM_TPTMsg inTMsg = new MDSE_ITEM_TPTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.ITEM_CLASSIFICATION.equals(prcGrpTrgtAttrbCd)) {
            MDSE_ITEM_CLS_TPTMsg inTMsg = new MDSE_ITEM_CLS_TPTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.MERCHANDISE_TYPE.equals(prcGrpTrgtAttrbCd)) {
            COA_PROJTMsg inTMsg = new COA_PROJTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_CODE.equals(prcGrpTrgtAttrbCd)) {
            COA_PRODTMsg inTMsg = new COA_PRODTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.ITEM_MARKETING_MODEL.equals(prcGrpTrgtAttrbCd)) {
            MKT_MDLTMsg inTMsg = new MKT_MDLTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_1.equals(prcGrpTrgtAttrbCd)) {
            PROD_CTRLTMsg inTMsg = new PROD_CTRLTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_2.equals(prcGrpTrgtAttrbCd)) {
            PROD_CTRLTMsg inTMsg = new PROD_CTRLTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_3.equals(prcGrpTrgtAttrbCd)) {
            PROD_CTRLTMsg inTMsg = new PROD_CTRLTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_4.equals(prcGrpTrgtAttrbCd)) {
            PROD_CTRLTMsg inTMsg = new PROD_CTRLTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_5.equals(prcGrpTrgtAttrbCd)) {
            PROD_CTRLTMsg inTMsg = new PROD_CTRLTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.MODEL_NAME.equals(prcGrpTrgtAttrbCd)) {
            MDL_NMTMsg inTMsg = new MDL_NMTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.MODEL_SERIES_NAME.equals(prcGrpTrgtAttrbCd)) {
            DS_MDL_GRPTMsg inTMsg = new DS_MDL_GRPTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.ACCOUNT_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            SELL_TO_CUSTTMsg inTMsg = new SELL_TO_CUSTTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.ACCOUNT_NAME.equals(prcGrpTrgtAttrbCd)) {
            SELL_TO_CUSTTMsg inTMsg = new SELL_TO_CUSTTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.SIC_CODE.equals(prcGrpTrgtAttrbCd)) {
            INDY_TPTMsg inTMsg = new INDY_TPTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.LOCATION_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            ACCT_LOCTMsg inTMsg = new ACCT_LOCTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.CLASSIFICATION.equals(prcGrpTrgtAttrbCd)) {
            DS_ACCT_CLSTMsg inTMsg = new DS_ACCT_CLSTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.CATEGORY.equals(prcGrpTrgtAttrbCd)) {
            DS_ACCT_TPTMsg inTMsg = new DS_ACCT_TPTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.DEALER_CODE.equals(prcGrpTrgtAttrbCd)) {
            DS_ACCT_DLRTMsg inTMsg = new DS_ACCT_DLRTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.CSMP_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            CSMP_CONTRTMsg inTMsg = new CSMP_CONTRTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.CSA_CSMP_REFERENCE_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            CSMP_CONTRTMsg inTMsg = new CSMP_CONTRTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.ASSOCIATION_NAME.equals(prcGrpTrgtAttrbCd)) {
            PRC_CONTRTMsg inTMsg = new PRC_CONTRTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.CSA_PRICE_CONTRACT_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            PRC_CONTRTMsg inTMsg = new PRC_CONTRTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.ORDER_CATEGORY.equals(prcGrpTrgtAttrbCd)) {
            DS_ORD_CATGTMsg inTMsg = new DS_ORD_CATGTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.ORDER_TYPE.equals(prcGrpTrgtAttrbCd)) {
            DS_ORD_TPTMsg inTMsg = new DS_ORD_TPTMsg();
            return inTMsg.getTableName();
        } else if (PRC_GRP_TRGT_ATTRB.LINE_CATEGORY_TYPE.equals(prcGrpTrgtAttrbCd)) {
            DS_ORD_LINE_CATGTMsg inTMsg = new DS_ORD_LINE_CATGTMsg();
            return inTMsg.getTableName();
        // QC#18789 2017/08/14 add start
        } else if (PRC_GRP_TRGT_ATTRB.CHANNEL.equals(prcGrpTrgtAttrbCd)) {
            COA_CHTMsg inTMsg = new COA_CHTMsg();
            return inTMsg.getTableName();
        }
        // QC#18789 2017/08/14 add end
        return "";
    }

    /**
     * <pre>
     * checkValDigit
     * </pre>
     * @param targetLength int
     * @param checkAttrb EZDItemAttribute
     * @return result False : NG True : OK
     */
    public static boolean checkValDigit(int targetLength, EZDItemAttribute checkAttrb) {

        if (checkAttrb == null) {
            return false;
        }

        if (targetLength > checkAttrb.getDigit()) {
            return false;
        }
        return true;
    }

    public static boolean isSameValue(BigDecimal val1, BigDecimal val2) {
        if (ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {
            return true;
        }
        return val1.compareTo(val2) == 0;
    }

    // 2018/12/05 QC#29324 Add Start
    /**
     * <pre>
     * setFilterParam
     * </pre>
     * @param params params
     * @param bizMsg NMAL7190CMsg
     * @param glblCmpyCd String
     */
    public static void setFilterParam(Map<String, Object> params, NMAL7190CMsg bizMsg, String glblCmpyCd) {

        String convFlg = "";
        if (ZYPCommonFunc.hasValue(bizMsg.F.no(0).prcGrpTrgtAttrbCd.getValue())) {
            PRC_GRP_TRGT_ATTRBTMsg tMsg = new PRC_GRP_TRGT_ATTRBTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.prcGrpTrgtAttrbCd, bizMsg.F.no(0).prcGrpTrgtAttrbCd);
            tMsg = (PRC_GRP_TRGT_ATTRBTMsg) S21CacheTBLAccessor.findByKey(tMsg);
            if (tMsg != null) {
                convFlg = tMsg.prcGrpConvFlg.getValue();
            }
        }

        params.put("prcGrpTrgtTpCd", bizMsg.F.no(0).prcGrpTrgtTpCd.getValue());
        params.put("prcGrpTrgtAttrbCd", bizMsg.F.no(0).prcGrpTrgtAttrbCd.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.F.no(0).xxPrcQlfyValSrchTxt_TF)) {
            if (ZYPConstant.FLG_ON_Y.equals(convFlg)) {
                params.put("xxPrcQlfyValSrchTxt_TF", convertFilterValue(bizMsg, bizMsg.F.no(0).xxPrcQlfyValSrchTxt_TF));
            } else {
                params.put("xxPrcQlfyValSrchTxt_TF", S21StringUtil.toStringArray(bizMsg.F.no(0).xxPrcQlfyValSrchTxt_TF.getValue(), COMMA));
            }
        }
        // 2023/04/20 QC#61200 Add Start
        params.put("dsAcctNm", bizMsg.F.no(0).dsAcctNm.getValue());
        // 2023/04/20 QC#61200 Add End
        if (ZYPCommonFunc.hasValue(bizMsg.F.no(0).xxPrcQlfyValSrchTxt_TT)) {
            if (ZYPConstant.FLG_ON_Y.equals(convFlg)) {
                params.put("xxPrcQlfyValSrchTxt_TT", convertFilterValue(bizMsg, bizMsg.F.no(0).xxPrcQlfyValSrchTxt_TT));
            } else {
                params.put("xxPrcQlfyValSrchTxt_TT", S21StringUtil.toStringArray(bizMsg.F.no(0).xxPrcQlfyValSrchTxt_TT.getValue(), COMMA));
            }
        }
        params.put("prcGrpPrcdNum", bizMsg.F.no(0).prcGrpPrcdNum.getValue());
        params.put("effFromDt_H1", bizMsg.F.no(0).effFromDt_H1.getValue());
        params.put("effFromDt_H2", bizMsg.F.no(0).effFromDt_H2.getValue());
        params.put("effThruDt_H1", bizMsg.F.no(0).effThruDt_H1.getValue());
        params.put("effThruDt_H2", bizMsg.F.no(0).effThruDt_H2.getValue());
        params.put("highValDt", HIGH_VAL_TM);
    }

    /**
     * <pre>
     * convertFilterValue
     * </pre>
     * @param params params
     * @param valueItem EZDCStringItem
     */
    private static String[] convertFilterValue(NMAL7190CMsg bizMsg, EZDCStringItem valueItem) {
        String[] valueArray = S21StringUtil.toStringArray(valueItem.getValue());
        String[] returnArray = new String[valueArray.length];

        int i = 0;
        Iterator<String> it = Arrays.asList(valueArray).iterator();
        NMAL7190SMsg paramMsg = new NMAL7190SMsg();
        while(it.hasNext()){
            paramMsg.A.no(0).prcGrpFromTxt_A1.setValue(it.next());
            convertPrcGrpAttrb(CONVTYPE_NAMETOCODE, bizMsg.F.no(0).prcGrpTrgtAttrbCd.getValue(), paramMsg.A.no(0).prcGrpFromTxt_CD, paramMsg.A.no(0).prcGrpFromTxt_A1);
            returnArray[i++] = paramMsg.A.no(0).prcGrpFromTxt_CD.getValue();
        }

        return returnArray;
    }
    // 2018/12/05 QC#29324 Add End

    // 2019/03/13 QC#30725 Add Start
    /**
     * formatDateStr
     * @param baseStr String of format -M/-D/YYYY
     * @return String of formated MM/DD/YYYY
     */
    public static String formatDateStr(String baseStr) {
        String[] devStr = baseStr.split(SLASH);
        StringBuilder sb = new StringBuilder();

        // Illegal case
        if (devStr.length != 3 || devStr[0] == null || devStr[1] == null || devStr[2] == null || devStr[0].length() < 1 || devStr[1].length() < 1 || devStr[2].length() != 4) {
            return baseStr;
        } else {
            if (devStr[0].length() == 1) {
                sb.append(DATE_FORMAT_PADDING_ZERO);
            }
            sb.append(devStr[0]);
            sb.append(SLASH);
            if (devStr[1].length() == 1) {
                sb.append(DATE_FORMAT_PADDING_ZERO);
            }
            sb.append(devStr[1]);
            sb.append(SLASH);
            sb.append(devStr[2]);
            return sb.toString();
        }
    }

    /**
     * checkItemCnt
     * @param item String
     * @param itemCnt integer
     * @return boolean
     */
    public static boolean checkItemCnt(String item, int itemCnt) {
        if (itemCnt < item.length()) {
            return true;
        } else {
            return false;
        }
    }
    // 2019/03/13 QC#30725 Add End
}
