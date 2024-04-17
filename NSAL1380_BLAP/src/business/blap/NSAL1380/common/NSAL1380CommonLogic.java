/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1380.common;

import static business.blap.NSAL1380.constant.NSAL1380Constant.NWAM0007W;
import static business.blap.NSAL1380.constant.NSAL1380Constant.NWAM0933E;
import static business.blap.NSAL1380.constant.NSAL1380Constant.NWAM0934E;
import static business.blap.NSAL1380.constant.NSAL1380Constant.NZZM0002I;
import static business.blap.NSAL1380.constant.NSAL1380Constant.NZZM0003E;
import static business.blap.NSAL1380.constant.NSAL1380Constant.ZZM9000E;
import static business.blap.NSAL1380.constant.NSAL1380Constant.ZZZM9005W;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL1380.NSAL1380CMsg;
import business.blap.NSAL1380.NSAL1380Query;
import business.blap.NSAL1380.NSAL1380_ACMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.SPLY_AGMT_DOC_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_INTVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_AGMT_DOC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 *<pre>
 * Supply Agreement Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/08   Hitachi         N.Arai          Create          N/A
 * 2018/08/09   Fujitsu         K.Ishizuka      Update          S21_NA#25829
 * 2018/09/05   Hitachi         T.Tomita        Update          QC#28055
 *</pre>
 */
public class NSAL1380CommonLogic {

    /**
     * Changed Value Exist Check
     * @param bizMsg NSAL1380CMsg
     * @return boolean
     */
    public static Boolean checkChangedHeaderValueExist(NSAL1380CMsg bizMsg) {
        if (checkChangeValue(bizMsg.splyBaseAmt.getValue(), bizMsg.splyBaseAmt_H.getValue()) //
            || checkChangeValue(bizMsg.qtyContrCapQty.getValue(), bizMsg.qtyContrCapQty_H.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * Changed Value Exist Check
     * @param lineMsg NSAL1380CMsg
     * @return boolean
     */
    public static Boolean checkChangedDetailValueExist(NSAL1380_ACMsg lineMsg) {
        if (checkChangeValue(lineMsg.shpgIntvlCd_A.getValue(), lineMsg.shpgIntvlCd_H.getValue()) //
                || checkChangeValue(lineMsg.firstShipQty_A.getValue(), lineMsg.firstShipQty_H.getValue())
                || checkChangeValue(lineMsg.otmShipQty_A.getValue(), lineMsg.otmShipQty_H.getValue())
                || checkChangeValue(lineMsg.schdAllwQty_A.getValue(), lineMsg.schdAllwQty_H.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * Changed Value Exist Check
     * @param str1 String
     * @param str2 String
     * @return boolean
     */
    public static Boolean checkChangeValue(String str1, String str2) {
        if (str1 == null) {
            if (str2 == null) {
                return false;
            } else {
                return true;
            }
        } else if (str1.equals(str2)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Changed Value Exist Check
     * @param str1 BigDecimal
     * @param str2 BigDecimal
     * @return boolean
     */
    public static Boolean checkChangeValue(BigDecimal str1, BigDecimal str2) {
        if (str1 == null) {
            if (str2 == null) {
                return false;
            } else {
                return true;
            }
        } else if (str1.equals(str2)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Search Screen Data
     * @param bizMsg NSAL1380CMsg
     */
    // public static void search(NSAL1380CMsg bizMsg) {
    public static void search(String glblCmpyCd, NSAL1380CMsg bizMsg) {
        ZYPTableUtil.clear(bizMsg.A);
        if (!ZYPCommonFunc.hasValue(bizMsg.prcMtrPkgPk)) {
            bizMsg.prcMtrPkgPk.setValue(BigDecimal.ZERO);
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.mdlId)) {
            bizMsg.mdlId.setValue(BigDecimal.ZERO);
        }

        // START 2017/10/16 [QC#20001, ADD]
        DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtl(glblCmpyCd, bizMsg);
        if (dsContrDtlTMsg == null) {
            bizMsg.setMessageInfo(NZZM0003E);
            return;
        }
        if (isShellLine(dsContrDtlTMsg)) {
            S21SsmEZDResult ssmResult = NSAL1380Query.getInstance().getSchdCratTmplPkByDsContrDtlGrpNum(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            }
            List<Map<String, Object>> resList = ssmResultCast(ssmResult.getResultObject());
            if (resList != null && !resList.isEmpty()) {
                setValue(bizMsg.dsContrDtlGrpNum, bizMsg.dsContrDtlPk);
                setValue(bizMsg.dsContrDtlPk, (BigDecimal)resList.get(0).get("DS_CONTR_DTL_PK"));
            }
        }
        // END   2017/10/16 [QC#20001, ADD]

        S21SsmEZDResult ssmResult = NSAL1380Query.getInstance().getHeaderInfoTmpl(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            setValue(bizMsg.splyBaseAmt, BigDecimal.ZERO);
            setValue(bizMsg.qtyContrCapQty, BigDecimal.ZERO);
        } else {
            List<Map<String, Object>> resList = ssmResultCast(ssmResult.getResultObject());
            if (resList != null && !resList.isEmpty()) {
                setValue(bizMsg.schdCratTmplPk, (BigDecimal) resList.get(0).get("SCHD_CRAT_TMPL_PK"));
                setValue(bizMsg.splyBaseAmt, (BigDecimal) resList.get(0).get("SPLY_BASE_AMT"));
                setValue(bizMsg.qtyContrCapQty, (BigDecimal) resList.get(0).get("QTY_CONTR_CAP_QTY"));
                setValue(bizMsg.ezUpTime, (String) resList.get(0).get("EZUPTIME"));
                setValue(bizMsg.ezUpTimeZone, (String) resList.get(0).get("EZUPTIMEZONE"));

                setValue(bizMsg.splyBaseAmt_H, (BigDecimal) resList.get(0).get("SPLY_BASE_AMT"));
                setValue(bizMsg.qtyContrCapQty_H, (BigDecimal) resList.get(0).get("QTY_CONTR_CAP_QTY"));
            }
        }

        ssmResult = NSAL1380Query.getInstance().getHeaderInfoPln(bizMsg);

        if (!ssmResult.isCodeNotFound()) {
            List<Map<String, Object>> resList = ssmResultCast(ssmResult.getResultObject());
            if (resList != null && !resList.isEmpty()) {
                setValue(bizMsg.splyAgmtPlnPk, (BigDecimal) resList.get(0).get("SPLY_AGMT_PLN_PK"));
                setValue(bizMsg.splyAgmtPlnNm, (String) resList.get(0).get("SPLY_AGMT_PLN_NM"));
                setValue(bizMsg.splyAgmtPlnShortNm, (String) resList.get(0).get("SPLY_AGMT_PLN_SHORT_NM"));
                setValue(bizMsg.splyAgmtPlnDescTxt, (String) resList.get(0).get("SPLY_AGMT_PLN_DESC_TXT"));
                setValue(bizMsg.splyAgmtPlnTpCd, (String) resList.get(0).get("SPLY_AGMT_PLN_TP_CD"));
                setValue(bizMsg.splyAgmtPlnTpDescTxt, (String) resList.get(0).get("SPLY_AGMT_PLN_TP_DESC_TXT"));
                setValue(bizMsg.splyAgmtDocTpCd, (String) resList.get(0).get("SPLY_AGMT_DOC_TP_CD"));
                setValue(bizMsg.splyAgmtDocTpDescTxt, (String) resList.get(0).get("SPLY_AGMT_DOC_TP_DESC_TXT"));
                setValue(bizMsg.annTermCapNum, (BigDecimal) resList.get(0).get("ANN_TERM_CAP_NUM"));
            }
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.schdCratTmplPk)) {
            return;
        }
        ssmResult = NSAL1380Query.getInstance().getDetailInfo(bizMsg);

        if (ssmResult.getQueryResultCount() > bizMsg.A.length()) {
            bizMsg.A.setValidCount(bizMsg.A.length() - 1);
        } else {
            bizMsg.A.setValidCount(ssmResult.getQueryResultCount());
        }

        if (!ssmResult.isCodeNotFound()) {
            List<Map<String, Object>> resList = ssmResultCast(ssmResult.getResultObject());

            for (int i = 0; i < resList.size(); i++) {
                if (i > bizMsg.A.getValidCount()) {
                    bizMsg.setMessageInfo(NWAM0007W);
                    break;
                }

                Map<String, Object> map = resList.get(i);

                setValue(bizMsg.A.no(i).schdCratTmplPk_A, (BigDecimal) map.get("SCHD_CRAT_TMPL_PK"));
                setValue(bizMsg.A.no(i).schdCratTmplLineNum_A, (BigDecimal) map.get("SCHD_CRAT_TMPL_LINE_NUM"));
                setValue(bizMsg.A.no(i).imgSplyTpNm_A, (String) map.get("IMG_SPLY_TP_NM"));
                setValue(bizMsg.A.no(i).mdseCd_A, (String) map.get("MDSE_CD"));
                setValue(bizMsg.A.no(i).mnfItemCd_A, (String) map.get("MNF_ITEM_CD"));
                setValue(bizMsg.A.no(i).mdseDescShortTxt_A, (String) map.get("MDSE_DESC_SHORT_TXT"));
                setValue(bizMsg.A.no(i).shpgIntvlCd_A, (String) map.get("SHPG_INTVL_CD"));
                setValue(bizMsg.A.no(i).firstShipQty_A, (BigDecimal) map.get("FIRST_SHIP_QTY"));
                setValue(bizMsg.A.no(i).otmShipQty_A, (BigDecimal) map.get("OTM_SHIP_QTY"));
                setValue(bizMsg.A.no(i).schdAllwQty_A, (BigDecimal) map.get("SCHD_ALLW_QTY"));
                setValue(bizMsg.A.no(i).xxTotQtyCnt_A, (BigDecimal) map.get("ANN_ETTL_CNT"));
                setValue(bizMsg.A.no(i).xxTotQtyCnt_AT, (BigDecimal) map.get("TERM_TOT_ETTL_CNT"));
                setValue(bizMsg.A.no(i).ezUpTime_A, (String) map.get("EZUPTIME"));
                setValue(bizMsg.A.no(i).ezUpTimeZone_A, (String) map.get("EZUPTIMEZONE"));

                setValue(bizMsg.A.no(i).shpgIntvlCd_H, (String) map.get("SHPG_INTVL_CD"));
                setValue(bizMsg.A.no(i).firstShipQty_H, (BigDecimal) map.get("FIRST_SHIP_QTY"));
                setValue(bizMsg.A.no(i).otmShipQty_H, (BigDecimal) map.get("OTM_SHIP_QTY"));
                setValue(bizMsg.A.no(i).schdAllwQty_H, (BigDecimal) map.get("SCHD_ALLW_QTY"));
            }
        } else {
            bizMsg.setMessageInfo(ZZZM9005W);
            return;
        }
        
        calcTermCap(bizMsg); // 2018/08/09 S21_NA#25829 Add

        // Add Start 2018/09/05 QC#28055
        String scrtEntAvalFlg = NSAL1380Query.getInstance().getScrtEntAvalFlg(glblCmpyCd, bizMsg.dsContrDtlPk.getValue());
        if (!ZYPCommonFunc.hasValue(scrtEntAvalFlg)) {
            scrtEntAvalFlg = ZYPConstant.FLG_ON_Y;
        }
        setValue(bizMsg.scrEntAvalFlg, scrtEntAvalFlg);
        // Add End 2018/09/05 QC#28055

        if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {
            bizMsg.setMessageInfo(NZZM0002I);
        }
    }

    public static <T> T ssmResultCast(Object ssmResultObj) {
        T rtnObj = (T) ssmResultObj;
        return rtnObj;
    }

    /**
     * checkDocTp
     * @param glblCmpyCd String
     * @param bizMsg NSAL1380CMsg
     * @return String
     */
    public static boolean checkDocTp(String glblCmpyCd, NSAL1380CMsg bizMsg) {
        SPLY_AGMT_DOC_TPTMsg tMsg = new SPLY_AGMT_DOC_TPTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.splyAgmtDocTpCd, bizMsg.splyAgmtDocTpCd);

        tMsg = (SPLY_AGMT_DOC_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);

        String hdrFlag = tMsg.hdrLvlQtyEntryFlg.getValue();
        String dtlFlg = tMsg.dtlLvlQtyEntryFlg.getValue();
        boolean checkFlag = true;
        if (ZYPConstant.FLG_ON_Y.equals(hdrFlag)) {
            if (!ZYPCommonFunc.hasValue(bizMsg.annTermCapNum)) {
                bizMsg.annTermCapNum.setErrorInfo(1, ZZM9000E, new String[] {"Annual Term Cap"});
                return false;
            }
        }
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL1380_ACMsg acMsg = bizMsg.A.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(hdrFlag)) {
                if (!SHPG_INTVL.UPON_REQUEST_BY_CUSTOMER.equals(acMsg.shpgIntvlCd_A.getValue())) {
                    acMsg.shpgIntvlCd_A.setErrorInfo(1, NWAM0933E, new String[] {ZYPCodeDataUtil.getName(SPLY_AGMT_DOC_TP.class, glblCmpyCd, bizMsg.splyAgmtDocTpCd.getValue()), ZYPCodeDataUtil.getName(SHPG_INTVL.class, glblCmpyCd, SHPG_INTVL.UPON_REQUEST_BY_CUSTOMER) });
                    checkFlag = false;
                }
            } else if (ZYPConstant.FLG_ON_Y.equals(dtlFlg)) {
                if (SHPG_INTVL.UPON_REQUEST_BY_CUSTOMER.equals(acMsg.shpgIntvlCd_A.getValue())) {
                    acMsg.shpgIntvlCd_A.setErrorInfo(1, NWAM0934E, new String[] {ZYPCodeDataUtil.getName(SPLY_AGMT_DOC_TP.class, glblCmpyCd, bizMsg.splyAgmtDocTpCd.getValue()), ZYPCodeDataUtil.getName(SHPG_INTVL.class, glblCmpyCd, SHPG_INTVL.UPON_REQUEST_BY_CUSTOMER) });
                    checkFlag = false;
                }
            }
        }
        return checkFlag;
    }

    // START 2017/10/16 [QC#20001, ADD]
    /**
     * getDsContrDtl
     * 
     * @param glblCmpyCd String
     * @param bizMsg NSAL1380CMsg
     * @return DS_CONTR_DTLTMsg
     */
    public static DS_CONTR_DTLTMsg getDsContrDtl(String glblCmpyCd, NSAL1380CMsg bizMsg) {
        DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, bizMsg.dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(dsContrDtlTMsg);
    }

    /**
     * isShellLine
     * 
     * @param glblCmpyCd String
     * @param bizMsg NSAL1380CMsg
     * @return boolean
     */
    public static boolean isShellLine(DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        if (dsContrDtlTMsg == null) {
            return false;
        }
        if (DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
            return true;
        } else {
            return false;
        }
    }
    // END   2017/10/16 [QC#20001, ADD]
    
    // 2018/08/09 S21_NA#25829 Add Start
    private static void calcTermCap(NSAL1380CMsg bizMsg) {

        BigDecimal totalAnualTermCap = new BigDecimal(0);
        if (SPLY_AGMT_DOC_TP.SCHEDULING_AGREEMENT.equals(bizMsg.splyAgmtDocTpCd.getValue())) {

            BigDecimal totalTermCap = new BigDecimal(0);

            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                NSAL1380_ACMsg aCMsg = bizMsg.A.no(i);
                totalAnualTermCap = totalAnualTermCap.add(aCMsg.xxTotQtyCnt_A.getValue());
                totalTermCap = totalTermCap.add(aCMsg.xxTotQtyCnt_AT.getValue());
            }
            bizMsg.annTermCapNum.clear();
            bizMsg.qtyContrCapQty.clear();
            bizMsg.qtyContrCapQty_H.clear();
            setValue(bizMsg.annTermCapNum, totalAnualTermCap);
            setValue(bizMsg.qtyContrCapQty, totalTermCap);
            setValue(bizMsg.qtyContrCapQty_H, totalTermCap);
        } else if (SPLY_AGMT_DOC_TP.QUANTITY_CONTRACT.equals(bizMsg.splyAgmtDocTpCd.getValue())) {
            if (ZYPCommonFunc.hasValue(bizMsg.qtyContrCapQty) && BigDecimal.ZERO.compareTo(bizMsg.qtyContrCapQty.getValue()) < 0) {
                totalAnualTermCap = (bizMsg.qtyContrCapQty.getValue().divide(bizMsg.termMthAot.getValue(), 2, BigDecimal.ROUND_UP)).multiply(new BigDecimal(12)).setScale(0, BigDecimal.ROUND_DOWN);
            }
            bizMsg.annTermCapNum.clear();
            setValue(bizMsg.annTermCapNum, totalAnualTermCap);
        }
    }
    // 2018/08/09 S21_NA#25829 Add End
}
