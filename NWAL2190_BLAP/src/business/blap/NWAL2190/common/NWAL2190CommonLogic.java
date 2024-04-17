/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2190.common;

import static business.blap.NWAL2190.constant.NWAL2190Constant.NWAM0007W;
import static business.blap.NWAL2190.constant.NWAL2190Constant.NZZM0002I;
import static business.blap.NWAL2190.constant.NWAL2190Constant.ZZZM9005W;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import business.blap.NWAL2190.NWAL2190CMsg;
import business.blap.NWAL2190.NWAL2190Query;
import business.blap.NWAL2190.NWAL2190_ACMsg;
import business.blap.NWAL2190.constant.NWAL2190Constant.XX_PAGE;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_AGMT_DOC_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 *
 * Supply Agreement Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/02   Fujitsu         S.Iidaka        Create          N/A
 * 2017/03/10   Fujitsu         S.Iidaka        Update          S21_NA#17789-2
 * 2018/08/09   Fujitsu         K.Ishizuka      Update          S21_NA#25829
 *</pre>
 */
public class NWAL2190CommonLogic {

    /**
     * Search Screen Data
     * @param bizMsg NWAL2190CMsg
     */
    public static void search(NWAL2190CMsg bizMsg) {
        ZYPTableUtil.clear(bizMsg.A);
        if (!ZYPCommonFunc.hasValue(bizMsg.prcMtrPkgPk)) {
            bizMsg.prcMtrPkgPk.setValue(BigDecimal.ZERO);
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.mdlId)) {
            bizMsg.mdlId.setValue(BigDecimal.ZERO);
        }

        S21SsmEZDResult ssmResult = NWAL2190Query.getInstance().getHeaderInfoTmpl(bizMsg);

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

        ssmResult = NWAL2190Query.getInstance().getHeaderInfoPln(bizMsg);

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
        ssmResult = NWAL2190Query.getInstance().getDetailInfo(bizMsg);

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

        if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {
            bizMsg.setMessageInfo(NZZM0002I);
        }
    }

    /**
     * @param scrnMsg
     * @return
     */
    public static boolean isImport(String xxPageCd) {
        return XX_PAGE.PAGE_IMPT.getCode().equals(xxPageCd);
    }

    public static <T> T ssmResultCast(Object ssmResultObj) {
        T rtnObj = (T) ssmResultObj;
        return rtnObj;
    }
    
    // 2018/08/09 S21_NA#25829 Add Start
    private static void calcTermCap(NWAL2190CMsg bizMsg) {

        BigDecimal totalAnualTermCap = new BigDecimal(0);
        
        if (SPLY_AGMT_DOC_TP.SCHEDULING_AGREEMENT.equals(bizMsg.splyAgmtDocTpCd.getValue())) {
            BigDecimal totalTermCap = new BigDecimal(0);

            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                NWAL2190_ACMsg aCMsg = bizMsg.A.no(i);
                totalAnualTermCap = totalAnualTermCap.add(aCMsg.xxTotQtyCnt_A.getValue());
                totalTermCap = totalTermCap.add(aCMsg.xxTotQtyCnt_AT.getValue());
            }
            bizMsg.annTermCapNum.clear();
            bizMsg.qtyContrCapQty.clear();
            bizMsg.qtyContrCapQty_H.clear();
            setValue(bizMsg.annTermCapNum, totalAnualTermCap);
            setValue(bizMsg.qtyContrCapQty, totalTermCap);
            setValue(bizMsg.qtyContrCapQty_H, totalTermCap);
        } else if (SPLY_AGMT_DOC_TP.QUANTITY_CONTRACT.equals(bizMsg.splyAgmtDocTpCd.getValue())){
            if(ZYPCommonFunc.hasValue(bizMsg.qtyContrCapQty) && BigDecimal.ZERO.compareTo(bizMsg.qtyContrCapQty.getValue()) < 0){
                totalAnualTermCap = (bizMsg.qtyContrCapQty.getValue().divide(bizMsg.shpgIntvlMthNum.getValue(), 2, BigDecimal.ROUND_UP)).multiply(new BigDecimal(12)).setScale(0, BigDecimal.ROUND_DOWN);
            }
            bizMsg.annTermCapNum.clear();
            setValue(bizMsg.annTermCapNum, totalAnualTermCap);
        }
    }
    // 2018/08/09 S21_NA#25829 Add End
}
