/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0200.common;

import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_FIRST_PROD_CTRL_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_FRTH_PROD_CTRL_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_PL3;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_RGTN_STS_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_ROWNUM;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_SCD_PROD_CTRL_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_THIRD_PROD_CTRL_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_ZEROTH_PROD_CTRL_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.MSG_MDSE;
import static business.blap.NMAL0200.constant.NMAL0200Constant.MSG_PRODUCT_FAMILY;
import static business.blap.NMAL0200.constant.NMAL0200Constant.NMAM8622E;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NMAL0200.NMAL0200CMsg;
import business.blap.NMAL0200.NMAL0200Query;
import business.blap.NMAL0200.NMAL0200SMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NMAL0200 Product Categorization Maintenance
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NMAL0200CommonLogic {

    /**
     * Update the global Message.
     * @param cMsg NMAL0200CMsg
     * @param sMsg NMAL0200SMsg
     */
    public static void updateSMsg(NMAL0200CMsg cMsg, NMAL0200SMsg sMsg) {
        int ixG = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(ixG + i), null);
        }
    }

    /**
     * loadOnePageToCMsg
     * @param cMsg NMAL0200CMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsg NMAL0200SMsg
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(NMAL0200CMsg cMsg, EZDCMsgArray cMsgArray, NMAL0200SMsg sMsg, EZDSMsgArray sMsgArray) {

        ZYPTableUtil.clear(cMsgArray);

        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (cMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        if (startIndex == sMsgArray.getValidCount()) {
            startIndex = startIndex - cMsgArray.length();
        }
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
        cMsg.xxPageShowFromNum.setValue(startIndex + 1);
        cMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    /**
     * Product Control Code Used Check
     * @param cMsg NMAL0200CMsg
     * @param sMsg NMAL0200SMsg
     * @param glblCmpyCd String
     * @param idx int
     * @param maxDisplayRows int
     * @return boolean
     */
    public static boolean chkUsedHrchCd(NMAL0200CMsg cMsg, NMAL0200SMsg sMsg, String glblCmpyCd, int idx, int maxDisplayRows) {
        S21SsmEZDResult result = null;
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put(DB_PARAM_ROWNUM, BigDecimal.ONE);

        // Used Check
        if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP.equals(sMsg.A.no(idx).mdseStruElmntTpCd_A1.getValue())) {
            // PLG;Used MDSE
            ssmParam.put(DB_PARAM_ZEROTH_PROD_CTRL_CD, sMsg.A.no(idx).prodCtrlCd_A1.getValue());
            result = NMAL0200Query.getInstance().findSingleRecord("findMdseProdCtrl", ssmParam);
            if (result.isCodeNormal()) {
                sMsg.A.no(idx).prodCtrlCd_A1.setErrorInfo(1, NMAM8622E, new String[] {MSG_MDSE });
                int errScrnInex = (idx / maxDisplayRows) * maxDisplayRows + 1;
                cMsg.xxPageShowFromNum.setValue(errScrnInex);
                return false;
            }
        } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE.equals(sMsg.A.no(idx).mdseStruElmntTpCd_A1.getValue())) {
            // PL;Used MDSE
            ssmParam.put(DB_PARAM_FIRST_PROD_CTRL_CD, sMsg.A.no(idx).prodCtrlCd_A1.getValue());
            result = NMAL0200Query.getInstance().findSingleRecord("findMdseProdCtrl", ssmParam);
            if (result.isCodeNormal()) {
                sMsg.A.no(idx).prodCtrlCd_A1.setErrorInfo(1, NMAM8622E, new String[] {MSG_MDSE });
                int errScrnInex = (idx / maxDisplayRows) * maxDisplayRows + 1;
                cMsg.xxPageShowFromNum.setValue(errScrnInex);
                return false;
            }
        } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals(sMsg.A.no(idx).mdseStruElmntTpCd_A1.getValue())) {
            // PL3;Used MDSE
            ssmParam.put(DB_PARAM_THIRD_PROD_CTRL_CD, sMsg.A.no(idx).prodCtrlCd_A1.getValue());
            result = NMAL0200Query.getInstance().findSingleRecord("findMdseProdCtrl", ssmParam);
            if (result.isCodeNormal()) {
                sMsg.A.no(idx).prodCtrlCd_A1.setErrorInfo(1, NMAM8622E, new String[] {MSG_MDSE });
                int errScrnInex = (idx / maxDisplayRows) * maxDisplayRows + 1;
                cMsg.xxPageShowFromNum.setValue(errScrnInex);
                return false;
            }
        } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4.equals(sMsg.A.no(idx).mdseStruElmntTpCd_A1.getValue())) {
            // PL4;Used MDSE
            ssmParam.put(DB_PARAM_FRTH_PROD_CTRL_CD, sMsg.A.no(idx).prodCtrlCd_A1.getValue());
            result = NMAL0200Query.getInstance().findSingleRecord("findMdseProdCtrl", ssmParam);
            if (result.isCodeNormal()) {
                sMsg.A.no(idx).prodCtrlCd_A1.setErrorInfo(1, NMAM8622E, new String[] {MSG_MDSE });
                int errScrnInex = (idx / maxDisplayRows) * maxDisplayRows + 1;
                cMsg.xxPageShowFromNum.setValue(errScrnInex);
                return false;
            }
        } else {
            // PL2:Used MDSE And THIRD_PROD_HRCH
            ssmParam.put(DB_PARAM_SCD_PROD_CTRL_CD, sMsg.A.no(idx).prodCtrlCd_A1.getValue());
            result = NMAL0200Query.getInstance().findSingleRecord("findMdseProdCtrl", ssmParam);
            if (result.isCodeNormal()) {
                sMsg.A.no(idx).prodCtrlCd_A1.setErrorInfo(1, NMAM8622E, new String[] {MSG_MDSE });
                int errScrnInex = (idx / maxDisplayRows) * maxDisplayRows + 1;
                cMsg.xxPageShowFromNum.setValue(errScrnInex);
                return false;
            }

            ssmParam.put(DB_PARAM_ROWNUM, BigDecimal.ONE);
            ssmParam.put(DB_PARAM_PL3, MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3);
            result = NMAL0200Query.getInstance().findSingleRecord("findtThirdProdHrch", ssmParam);
            if (result.isCodeNormal()) {
                sMsg.A.no(idx).prodCtrlCd_A1.setErrorInfo(1, NMAM8622E, new String[] {MSG_PRODUCT_FAMILY });
                int errScrnInex = (idx / maxDisplayRows) * maxDisplayRows + 1;
                cMsg.xxPageShowFromNum.setValue(errScrnInex);
                return false;
            }
        }

        return true;
    }

    /**
     * Product Control Code Used Check
     * @param cMsg NMAL0200CMsg
     * @param sMsg NMAL0200SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean chkUsedHrchCd4Submit(NMAL0200CMsg cMsg, NMAL0200SMsg sMsg, String glblCmpyCd) {
        S21SsmEZDResult result = null;
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put(DB_PARAM_ROWNUM, BigDecimal.ONE);

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            // Used Check
            if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP.equals(sMsg.B.no(i).mdseStruElmntTpCd_DL.getValue())) {
                // PLG;Used MDSE
                ssmParam.put(DB_PARAM_ZEROTH_PROD_CTRL_CD, sMsg.B.no(i).prodCtrlCd_DL.getValue());
                result = NMAL0200Query.getInstance().findSingleRecord("findMdseProdCtrl", ssmParam);
                if (result.isCodeNormal()) {
                    cMsg.setMessageInfo(NMAM8622E, new String[] {MSG_MDSE });
                    return false;
                }
            } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE.equals(sMsg.B.no(i).mdseStruElmntTpCd_DL.getValue())) {
                // PL;Used MDSE
                ssmParam.put(DB_PARAM_FIRST_PROD_CTRL_CD, sMsg.B.no(i).prodCtrlCd_DL.getValue());
                result = NMAL0200Query.getInstance().findSingleRecord("findMdseProdCtrl", ssmParam);
                if (result.isCodeNormal()) {
                    cMsg.setMessageInfo(NMAM8622E, new String[] {MSG_MDSE });
                    return false;
                }
            } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals(sMsg.B.no(i).mdseStruElmntTpCd_DL.getValue())) {
                // PL3;Used MDSE
                ssmParam.put(DB_PARAM_THIRD_PROD_CTRL_CD, sMsg.B.no(i).prodCtrlCd_DL.getValue());
                result = NMAL0200Query.getInstance().findSingleRecord("findMdseProdCtrl", ssmParam);
                if (result.isCodeNormal()) {
                    cMsg.setMessageInfo(NMAM8622E, new String[] {MSG_MDSE });
                    return false;
                }
            } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4.equals(sMsg.B.no(i).mdseStruElmntTpCd_DL.getValue())) {
                // PL4;Used MDSE
                ssmParam.put(DB_PARAM_FRTH_PROD_CTRL_CD, sMsg.B.no(i).prodCtrlCd_DL.getValue());
                result = NMAL0200Query.getInstance().findSingleRecord("findMdseProdCtrl", ssmParam);
                if (result.isCodeNormal()) {
                    cMsg.setMessageInfo(NMAM8622E, new String[] {MSG_MDSE });
                    return false;
                }
            } else {
                // PL2:Used MDSE And THIRD_PROD_HRCH
                ssmParam.put(DB_PARAM_SCD_PROD_CTRL_CD, sMsg.B.no(i).prodCtrlCd_DL.getValue());
                result = NMAL0200Query.getInstance().findSingleRecord("findMdseProdCtrl", ssmParam);
                if (result.isCodeNormal()) {
                    cMsg.setMessageInfo(NMAM8622E, new String[] {MSG_MDSE });
                    return false;
                }

                ssmParam.put(DB_PARAM_ROWNUM, BigDecimal.ONE);
                ssmParam.put(DB_PARAM_PL3, MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3);
                result = NMAL0200Query.getInstance().findSingleRecord("findtThirdProdHrch", ssmParam);
                if (result.isCodeNormal()) {
                    cMsg.setMessageInfo(NMAM8622E, new String[] {MSG_MDSE });
                    return false;
                }
            }
        }
        return true;
    }
}
