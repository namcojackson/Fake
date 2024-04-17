/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1230.common;

import static business.blap.NPAL1230.constant.NPAL1230Constant.NZZM0010E;
import static business.blap.NPAL1230.constant.NPAL1230Constant.BIZ_APP_ID;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_COA_MDSE_TP_CD;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_EFF_FROM_DT;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_MDSE_CD;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_SRCH_OPT_APL_ID;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_PRNT_VND_CD;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_VND_CD;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NPAL1230.NPAL1230CMsg;
import business.blap.NPAL1230.NPAL1230Query;
import business.blap.NPAL1230.NPAL1230SMsg;
import business.parts.NPZC115001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC115001.NPZC115001;
import com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NPAL1230 ASL Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/12   CITS            T.Gotoda        Create          N/A
 * 2018/01/12   CITS            S.Katsuma       Update          QC#12226
 * 2021/01/15   CITS            J.Evangelista   Update          QC#58165
 * 2023/01/26   Hitachi         S.Dong          Update          QC#60966
 *</pre>
 */
public class NPAL1230CommonLogic {

    /**
     * Create Pulldown Merchandise Type
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownMerchandiseType(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.coaMdseTpCd_PD.clear();
        cMsg.coaProjDescTxt_PD.clear();

        // Set Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        // Execute
        S21SsmEZDResult result = NPAL1230Query.getInstance().getMerchandiseTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.coaMdseTpCd_PD.no(i), (String) recode.get("COA_PROJ_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.coaProjDescTxt_PD.no(i), (String) recode.get("COA_PROJ_DESC_TXT"));

                if (i >= cMsg.coaMdseTpCd_PD.length() - 1) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown UOM Type
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownUomType(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.vndUomCd_PD.clear();
        cMsg.vndUomDescTxt_PD.clear();

        // Set Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        // Execute
        S21SsmEZDResult result = NPAL1230Query.getInstance().getUomTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.vndUomCd_PD.no(i), (String) recode.get("VND_UOM_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.vndUomDescTxt_PD.no(i), (String) recode.get("VND_UOM_DESC_TXT"));

                if (i >= cMsg.vndUomCd_PD.length() - 1) {
                    break;
                }
            }
        }
    }

    /**
     * Set Item Description
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     * @param glblCmpyCd String
     */
    public static void setItemDescription(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg, String glblCmpyCd) {

        String mdseDescShortTxt = getItemDescription(glblCmpyCd, cMsg.mdseCd.getValue());

        if (ZYPCommonFunc.hasValue(mdseDescShortTxt)) {
            ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt, mdseDescShortTxt);

        } else {
            cMsg.mdseDescShortTxt.clear();
        }
    }

    /**
     * Get Item Description
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return itemDescription
     */
    public static String getItemDescription(String glblCmpyCd, String mdseCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_MDSE_CD, mdseCd);

        S21SsmEZDResult result = NPAL1230Query.getInstance().getItemDescription(ssmParam);

        if (result.isCodeNormal()) {
            return (String) result.getResultObject();
        } else {
            return null;
        }
    }

    /**
     * Get aslDtlPk
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param vndCd String
     * @param effFromDt String
     * @return aslDtlPk
     */
    public static BigDecimal getAslDtlPk(String glblCmpyCd, String mdseCd, String vndCd, String effFromDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_MDSE_CD, mdseCd);
        ssmParam.put(DB_PARAM_VND_CD, vndCd);
        ssmParam.put(DB_PARAM_EFF_FROM_DT, effFromDt);

        S21SsmEZDResult result = NPAL1230Query.getInstance().getAslDtlPk(ssmParam);

        if (result.isCodeNormal()) {
            return (BigDecimal) result.getResultObject();
        } else {
            return null;
        }
    }

    /**
     * Get coaMdseTpCd
     * @param glblCmpyCd String
     * @param coaMdseTpCd String
     * @return coaMdseTpCd
     */
    public static String getCoaMdseTp(String glblCmpyCd, String coaMdseTpCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_COA_MDSE_TP_CD, coaMdseTpCd);

        S21SsmEZDResult result = NPAL1230Query.getInstance().getCoaMdseTp(ssmParam);

        if (result.isCodeNormal()) {
            return (String) result.getResultObject();
        } else {
            return null;
        }
    }

    /**
     * Set Supplier Name
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     * @param glblCmpyCd String
     */
    public static void setSupplierName(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRNT_VND_CD, cMsg.prntVndCd);

        S21SsmEZDResult result = NPAL1230Query.getInstance().getSupplierName(ssmParam);

        if (result.isCodeNormal()) {
            String prntVndNm = (String) result.getResultObject();

            ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm, prntVndNm);

        } else {
            cMsg.prntVndNm.clear();
        }
    }

    // QC#21170 Add Parameter sMsg
    /**
     * Execute API NPZC115001
     * @param cMsg NPAL1230CMsg
     * @return result
     */
    public static boolean executeAslUpdateApi(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {
        // Search Option API(NSZC0330) is executed
        NPZC115001 api = new NPZC115001();

        NPZC115001PMsg pMsg = new NPZC115001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC115001Constant.ENTRY_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(pMsg.aslHdrPk, cMsg.aslHdrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.aslNm, cMsg.aslNm);
        ZYPEZDItemValueSetter.setValue(pMsg.aslDescTxt, cMsg.aslDescTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, cMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(pMsg.aslStartDt, cMsg.aslStartDt);
        ZYPEZDItemValueSetter.setValue(pMsg.aslEndDt, cMsg.aslEndDt);
        // Set ASL Detail List
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).aslDtlPk, sMsg.A.no(i).aslDtlPk_A);
            ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).mdseCd, sMsg.A.no(i).mdseCd_A);
            ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).vndUomCd, sMsg.A.no(i).vndUomCd_A);
            ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).splyItemNum, sMsg.A.no(i).splyItemNum_A);
            ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).vndCd, sMsg.A.no(i).vndCd_A);
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).primSplyFlg_A)) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).primSplyFlg, sMsg.A.no(i).primSplyFlg_A);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).primSplyFlg, ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).unitPrcAmt, sMsg.A.no(i).unitPrcAmt_A);
            ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).baseOrdQty, sMsg.A.no(i).baseOrdQty_A);
            //ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).vndUomQty, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).vndUomQty, sMsg.A.no(i).vndUomQty_A);
            ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).vndIncrOrdQty, sMsg.A.no(i).vndIncrOrdQty_A);
            ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).vndMinOrdQty, sMsg.A.no(i).vndMinOrdQty_A);
            // QC#21170 2018/04/11 Start
            BigDecimal vndLtDaysNum = sMsg.A.no(i).vndMinOrdQty_A.getValue();
            if (ZYPCommonFunc.hasValue(vndLtDaysNum)) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).vndLtDaysNum, sMsg.A.no(i).vndLtDaysNum_A);
            }
            else {
                pMsg.xxAslDtlList.no(i).vndLtDaysNum.clear();
            }
            // QC#21170 2018/04/11 End
            // START 2023/01/26 S.Dong [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).vndShipLtDaysNum, sMsg.A.no(i).vndShipLtDaysNum_A);
            // END 2023/01/26 S.Dong [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).effFromDt, sMsg.A.no(i).effFromDt_A);
            ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).effThruDt, sMsg.A.no(i).effThruDt_A);
            ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).aslItemCmntTxt, sMsg.A.no(i).aslItemCmntTxt_A);
        }
        pMsg.xxAslDtlList.setValidCount(sMsg.A.getValidCount());

        // Set AQL Qualifier List
        for (int i = 0; i < sMsg.Q.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxAslQlfyRelnList.no(i).aslQlfyRelnPk, sMsg.Q.no(i).aslQlfyRelnPk_Q);
            ZYPEZDItemValueSetter.setValue(pMsg.xxAslQlfyRelnList.no(i).aslQlfyTpCd, sMsg.Q.no(i).aslQlfyTpCd_Q);
            ZYPEZDItemValueSetter.setValue(pMsg.xxAslQlfyRelnList.no(i).aslQlfyRefCd, sMsg.Q.no(i).aslQlfyRefCd_Q);
        }
        pMsg.xxAslQlfyRelnList.setValidCount(sMsg.Q.getValidCount());

        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    cMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        return false;
                    }
                }
            }
        } else {

            ZYPEZDItemValueSetter.setValue(cMsg.aslHdrPk, pMsg.aslHdrPk);
        }

        return true;
    }

    // START 2018/01/12 S.Katsuma [QC#12226,ADD]
    /**
     * Set Supplier Site Name
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     * @param glblCmpyCd String
     */
    public static void getSupplierSiteName(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg, String glblCmpyCd) {

        int selectNum = cMsg.xxNum.getValueInt();
        String locNm = getSupplierSiteName(glblCmpyCd, cMsg.A.no(selectNum).vndCd_A.getValue());

        if (ZYPCommonFunc.hasValue(locNm)) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(selectNum).locNm_A, locNm);

        } else {
            cMsg.A.no(selectNum).vndCd_A.setErrorInfo(1, NZZM0010E, new String[] {cMsg.A.no(selectNum).vndCd_A.getValue() });
        }
    }

    public static String getSupplierSiteName(String glblCmpyCd, String vndCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_VND_CD, vndCd);

        S21SsmEZDResult result = NPAL1230Query.getInstance().getSupplierSiteName(ssmParam);

        if (result.isCodeNormal()) {
            return (String) result.getResultObject();
        } else {
            return null;
        }
    }
    // END 2018/01/12 S.Katsuma [QC#12226,ADD]

    // START 2021/01/15 J.Evangelista [QC#58165,ADD]
    /**
     * Get Supplier Item Code Max Length
     * @param glblCmpyCd String
     * @param vndCd String
     * @return supplierItemCodeMaxLength
     */
    public static String getSupplierItemCodeMaxLength(String glblCmpyCd, String vndCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_SRCH_OPT_APL_ID, BIZ_APP_ID);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_VND_CD, vndCd);

        S21SsmEZDResult result = NPAL1230Query.getInstance().getSupplierItemCodeMaxLength(ssmParam);

        if (result.isCodeNormal()) {
            return (String) result.getResultObject();
        } else {
            return null;
        }
    }
    // END   2021/01/15 J.Evangelista [QC#58165,ADD]
}
