/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0200;

import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_FIRST_PROD_CTRL_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_FRTH_PROD_CTRL_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_MDSE_STRU_ELMNT_TP_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_PROD_CTRL_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_RGTN_STS_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_ROWNUM;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_SCD_PROD_CTRL_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_THIRD_PROD_CTRL_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_ZEROTH_PROD_CTRL_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.EVENT_NM_NMAL0200_CMN_SUBMIT;
import static business.blap.NMAL0200.constant.NMAL0200Constant.MDSE;
import static business.blap.NMAL0200.constant.NMAL0200Constant.MDSE_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.MSG_DESC;
import static business.blap.NMAL0200.constant.NMAL0200Constant.MSG_HC;
import static business.blap.NMAL0200.constant.NMAL0200Constant.MSG_LEVEL;
import static business.blap.NMAL0200.constant.NMAL0200Constant.NMAM0282E;
import static business.blap.NMAL0200.constant.NMAL0200Constant.NMAM0836E;
import static business.blap.NMAL0200.constant.NMAL0200Constant.NMAM8000E;
import static business.blap.NMAL0200.constant.NMAL0200Constant.NMAM8105E;
import static business.blap.NMAL0200.constant.NMAL0200Constant.NMAM8121E;
import static business.blap.NMAL0200.constant.NMAL0200Constant.NMAM8175E;
import static business.blap.NMAL0200.constant.NMAL0200Constant.NMAM8509E;
import static business.blap.NMAL0200.constant.NMAL0200Constant.NMAM8633E;
import static business.blap.NMAL0200.constant.NMAL0200Constant.PROD_CTRL;
import static business.blap.NMAL0200.constant.NMAL0200Constant.PROD_CTRL_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.SUBMIT;
import static business.blap.NMAL0200.constant.NMAL0200Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL0200.common.NMAL0200CommonLogic;
import business.db.MDSETMsg;
import business.db.PROD_CTRLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NMAL0200 Product Categorization Maintenance
 * Function Name : update business process
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2016   CITS            K.Ogino         Create          N/A
 * 08/10/2016   CITS            K.Ogino         Update          QC#12363
 * 12/27/2018   Fujitsu         C.Hara          Update          QC#29695
 *</pre>
 */
public class NMAL0200BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if (EVENT_NM_NMAL0200_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_CMN_Submit((NMAL0200CMsg) cMsg, (NMAL0200SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * CMN_Submit
     * @param cMsg NMAL0200CMsg
     * @param sMsg NMAL0200SMsg
     */
    private void doProcess_CMN_Submit(NMAL0200CMsg cMsg, NMAL0200SMsg sMsg) {

        NMAL0200CommonLogic.updateSMsg(cMsg, sMsg);
        String glblCmpyCd = getGlobalCompanyCode();
        int maxDisplayRows = cMsg.A.length();
        int maxIndex = sMsg.A.getValidCount();

        // Check List
        List<String> prodCtrlCdList = new ArrayList<String>();
        List<String> prodFamilyList = new ArrayList<String>();
        Map<Integer, String> prodLineMap = new HashMap<Integer, String>();

        // 2018/12/27 QC#29695 Add Start
        if (cMsg.A.getValidCount() == 0 && sMsg.B.getValidCount() == 0) {
                cMsg.setMessageInfo(NMAM8105E);
                return;
            }
        // 2018/12/27 QC#29695 Add End
        try {
            // Logical Remove Process
            if (sMsg.B.getValidCount() > 0) {
                boolean result = NMAL0200CommonLogic.chkUsedHrchCd4Submit(cMsg, sMsg, glblCmpyCd);
                if (!result) {
                    return;
                }
                result = logicalRemoveProdCtrl(cMsg, sMsg, glblCmpyCd);
                if (!result) {
                    return;
                }
            }

            for (int i = 0; i < maxIndex; i++) {
                // Mandatory Fields Validation Check
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseStruElmntTpCd_A1)) {
                    sMsg.A.no(i).mdseStruElmntTpCd_A1.setErrorInfo(1, NMAM0836E, new String[] {MSG_LEVEL });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).prodCtrlCd_A1)) {
                    sMsg.A.no(i).prodCtrlCd_A1.setErrorInfo(1, NMAM0836E, new String[] {MSG_HC });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }

                // Duplicate check
                String prodCtrl = sMsg.A.no(i).prodCtrlCd_A1.getValue();
                if (prodCtrlCdList.contains(prodCtrl)) {
                    sMsg.A.no(i).prodCtrlCd_A1.setErrorInfo(1, NMAM8509E, null);
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                } else {
                    prodCtrlCdList.add(sMsg.A.no(i).prodCtrlCd_A1.getValue());
                }
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).prodCtrlNm_A1)) {
                    sMsg.A.no(i).prodCtrlNm_A1.setErrorInfo(1, NMAM0836E, new String[] {MSG_DESC });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }

                // Product Family List
                if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2.equals(sMsg.A.no(i).mdseStruElmntTpCd_A1.getValue())) {
                    if (!prodFamilyList.contains(sMsg.A.no(i).prodCtrlCd_A1.getValue())) {
                        prodFamilyList.add(sMsg.A.no(i).prodCtrlCd_A1.getValue());
                    }
                }

                // Product Line List
                if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals(sMsg.A.no(i).mdseStruElmntTpCd_A1.getValue())) {
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(i).scdProdHrchCd_A1)) {
                        prodLineMap.put(i, sMsg.A.no(i).scdProdHrchCd_A1.getValue());
                    }
                }
            }

            // Product Line Check
            if (!prodLineMap.isEmpty()) {
                for (Entry<Integer, String> entry : prodLineMap.entrySet()) {
                    Map<String, Object> ssmParam = new HashMap<String, Object>();
                    ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                    ssmParam.put(DB_PARAM_MDSE_STRU_ELMNT_TP_CD, MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2);
                    ssmParam.put(DB_PARAM_PROD_CTRL_CD, entry.getValue());
                    ssmParam.put(DB_PARAM_ROWNUM, BigDecimal.ONE);
                    S21SsmEZDResult dbResult = NMAL0200Query.getInstance().findSingleRecord("chkProdCtrlRelationship", ssmParam);
                    if (dbResult.isCodeNormal()) {
                        // DB Check OK
                        continue;
                    }
                    if (prodFamilyList.contains(entry.getValue())) {
                        // Screen Check OK
                        continue;
                    }
                    sMsg.A.no(entry.getKey()).scdProdHrchCd_A1.setErrorInfo(1, NMAM8633E, null);
                    int errScrnInex = (entry.getKey() / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }
            }

            // DB All Check
            for (int i = 0; i < maxIndex; i++) {
                if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxMstChkFlg_A1.getValue())) {

                    String mdseStruElmntTpCd = sMsg.A.no(i).mdseStruElmntTpCd_A1.getValue();

                    // Update Line
                    PROD_CTRLTMsg tMsg = updateChkProdCtrl(cMsg, sMsg, glblCmpyCd, i);
                    if (tMsg == null) {
                        cMsg.setMessageInfo(NMAM8175E, new String[] {PROD_CTRL, PROD_CTRL_CD, String.valueOf(sMsg.A.no(i).prodCtrlCd_A1.getValue()) });
                        return;
                    }

                    if (!MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals(mdseStruElmntTpCd)) {
                        if (tMsg.prodCtrlNm.getValue().equals(sMsg.A.no(i).prodCtrlNm_A1.getValue())) {
                            // No Update Line
                            continue;
                        }
                        tMsg = updateProdCtrl(cMsg, sMsg, i, tMsg);
                    } else {

                        if (tMsg.prodCtrlNm.getValue().equals(sMsg.A.no(i).prodCtrlNm_A1.getValue()) && tMsg.scdProdHrchCd.getValue().equals(sMsg.A.no(i).scdProdHrchCd_A1.getValue())) {
                            // No Update Line
                            continue;
                        }
                        ZYPEZDItemValueSetter.setValue(tMsg.scdProdHrchCd, sMsg.A.no(i).scdProdHrchCd_A1);

                        tMsg = updateProdCtrl(cMsg, sMsg, i, tMsg);
                    }

                    // Update MDSE
                    Map<String, Object> ssmParam = new HashMap<String, Object>();
                    ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                    ssmParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);

                    if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP.equals(mdseStruElmntTpCd)) {
                        ssmParam.put(DB_PARAM_ZEROTH_PROD_CTRL_CD, tMsg.prodCtrlCd.getValue());
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE.equals(mdseStruElmntTpCd)) {
                        ssmParam.put(DB_PARAM_FIRST_PROD_CTRL_CD, tMsg.prodCtrlCd.getValue());
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2.equals(mdseStruElmntTpCd)) {
                        ssmParam.put(DB_PARAM_SCD_PROD_CTRL_CD, tMsg.prodCtrlCd.getValue());
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals(mdseStruElmntTpCd)) {
                        ssmParam.put(DB_PARAM_THIRD_PROD_CTRL_CD, tMsg.prodCtrlCd.getValue());
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4.equals(mdseStruElmntTpCd)) {
                        ssmParam.put(DB_PARAM_FRTH_PROD_CTRL_CD, tMsg.prodCtrlCd.getValue());
                    } else {
                        sMsg.A.no(i).mdseStruElmntTpCd_A1.setErrorInfo(1, NMAM8121E, null);
                        int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                        cMsg.xxPageShowFromNum.setValue(errScrnInex);
                        return;
                    }

                    boolean result = updateMdse(ssmParam, cMsg, sMsg, glblCmpyCd, i);
                    if (!result) {
                        return;
                    }
                } else {
                    // New Line
                    PROD_CTRLTMsg tMsg = null;
                    Map<String, Object> ssmParam = new HashMap<String, Object>();
                    ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                    ssmParam.put(DB_PARAM_PROD_CTRL_CD, sMsg.A.no(i).prodCtrlCd_A1.getValue());
                    S21SsmEZDResult ssmResult = NMAL0200Query.getInstance().findSingleRecord("countProdCtrl", ssmParam);

                    if (ssmResult.isCodeNormal()) {
                        // DB registered Line
                        tMsg = updateChkProdCtrl(cMsg, sMsg, glblCmpyCd, i);
                        if (tMsg == null) {
                            cMsg.setMessageInfo(NMAM8175E, new String[] {PROD_CTRL, PROD_CTRL_CD, String.valueOf(sMsg.A.no(i).prodCtrlCd_A1.getValue()) });
                            return;
                        }
                        tMsg = updateProdCtrl(cMsg, sMsg, i, tMsg);
                        if (tMsg == null) {
                            // No Update Line
                            continue;
                        }

                        // Update MDSE
                        ssmParam = new HashMap<String, Object>();
                        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                        ssmParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
                        ssmParam.put(DB_PARAM_PROD_CTRL_CD, tMsg.prodCtrlCd.getValue());

                        String mdseStruElmntTpCd = sMsg.A.no(i).mdseStruElmntTpCd_A1.getValue();

                        if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP.equals(mdseStruElmntTpCd)) {
                            ssmParam.put(DB_PARAM_ZEROTH_PROD_CTRL_CD, mdseStruElmntTpCd);
                        } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE.equals(mdseStruElmntTpCd)) {
                            ssmParam.put(DB_PARAM_FIRST_PROD_CTRL_CD, mdseStruElmntTpCd);
                        } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2.equals(mdseStruElmntTpCd)) {
                            ssmParam.put(DB_PARAM_FIRST_PROD_CTRL_CD, mdseStruElmntTpCd);
                        } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals(mdseStruElmntTpCd)) {
                            ssmParam.put(DB_PARAM_FIRST_PROD_CTRL_CD, mdseStruElmntTpCd);
                        } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4.equals(mdseStruElmntTpCd)) {
                            ssmParam.put(DB_PARAM_FIRST_PROD_CTRL_CD, mdseStruElmntTpCd);
                        } else {
                            sMsg.A.no(i).mdseStruElmntTpCd_A1.setErrorInfo(1, NMAM8121E, null);
                            int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                            cMsg.xxPageShowFromNum.setValue(errScrnInex);
                            return;
                        }

                        Boolean result = updateMdse(ssmParam, cMsg, sMsg, glblCmpyCd, i);
                        if (!result) {
                            return;
                        }
                    } else {
                        // Insert Line
                        tMsg = insertProdCtrl(cMsg, sMsg, glblCmpyCd, i);
                        if (tMsg == null) {
                            // DB Duplicated Line
                            sMsg.A.no(i).mdseStruElmntTpCd_A1.setErrorInfo(1, NMAM8509E, null);
                            int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                            cMsg.xxPageShowFromNum.setValue(errScrnInex);
                            return;
                        }

                    }
                }
            }
            cMsg.setMessageInfo(ZZZM9003I, new String[] {SUBMIT });
        } finally {
            NMAL0200CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
        }
    }

    /**
     * Update MDSE Table
     * @param ssmParam Map<String, Object>
     * @param cMsg NMAL0200CMsg
     * @param sMsg NMAL0200SMsg
     * @param glblCmpyCd String
     * @param idx int
     * @param prodCtrlNm String
     * @return boolean
     */
    private boolean updateMdse(Map<String, Object> ssmParam, NMAL0200CMsg cMsg, NMAL0200SMsg sMsg, String glblCmpyCd, int idx) {

        S21SsmEZDResult result = NMAL0200Query.getInstance().findMultipleRecord("findMdseProdCtrl", ssmParam);
        if (result.isCodeNormal()) {
            List<String> resultList = (List<String>) result.getResultObject();
            for (String mdseCd : resultList) {

                String mdseStruElmntTpCd = sMsg.A.no(idx).mdseStruElmntTpCd_A1.getValue();
                String prodCtrlNm = sMsg.A.no(idx).prodCtrlNm_A1.getValue();

                MDSETMsg mdseTMsg = new MDSETMsg();
                ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
                mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(mdseTMsg);
                if (mdseTMsg == null) {
                    cMsg.setMessageInfo(NMAM8175E, new String[] {MDSE, MDSE_CD, sMsg.A.no(idx).prodCtrlCd_A1.getValue() });
                    return false;
                }
                if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP.equals(mdseStruElmntTpCd)) {
                    ZYPEZDItemValueSetter.setValue(mdseTMsg.zerothProdCtrlNm, prodCtrlNm);
                } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE.equals(mdseStruElmntTpCd)) {
                    ZYPEZDItemValueSetter.setValue(mdseTMsg.firstProdCtrlNm, prodCtrlNm);
                } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2.equals(mdseStruElmntTpCd)) {
                    ZYPEZDItemValueSetter.setValue(mdseTMsg.scdProdCtrlNm, prodCtrlNm);
                } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals(mdseStruElmntTpCd)) {
                    ZYPEZDItemValueSetter.setValue(mdseTMsg.thirdProdCtrlNm, prodCtrlNm);
                } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4.equals(mdseStruElmntTpCd)) {
                    ZYPEZDItemValueSetter.setValue(mdseTMsg.frthProdCtrlNm, prodCtrlNm);
                }

                EZDTBLAccessor.update(mdseTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mdseTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM8000E, new String[] {MDSE, mdseTMsg.mdseCd.getValue() });
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Logical Remove PROD_CTRL Table
     * @param cMsg NMAL0200CMsg
     * @param sMsg NMAL0200SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean logicalRemoveProdCtrl(NMAL0200CMsg cMsg, NMAL0200SMsg sMsg, String glblCmpyCd) {

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            PROD_CTRLTMsg tMsg = lockProdCtrlForUpdate(cMsg, glblCmpyCd, sMsg.B.no(i).prodCtrlCd_DL.getValue(), sMsg.B.no(i).ezUpTime_DL.getValue(), sMsg.B.no(i).ezUpTimeZone_DL.getValue());
            if (NMAM8175E.equals(cMsg.getMessageCode())) {
                return false;
            }

            if (tMsg == null) {
                return false;
            }

            EZDTBLAccessor.logicalRemove(tMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM8000E, new String[] {PROD_CTRL, sMsg.B.no(i).prodCtrlCd_DL.getValue() });
                return false;
            }

        }

        return true;
    }

    /**
     * Update PROD_CTRL Table
     * @param cMsg NMAL0200CMsg
     * @param sMsg NMAL0200SMsg
     * @param glblCmpyCd String
     * @param idx int
     * @return PROD_CTRLTMsg
     */
    private PROD_CTRLTMsg updateChkProdCtrl(NMAL0200CMsg cMsg, NMAL0200SMsg sMsg, String glblCmpyCd, int idx) {

        PROD_CTRLTMsg tMsg = lockProdCtrlForUpdate(cMsg, glblCmpyCd, sMsg.A.no(idx).prodCtrlCd_A1.getValue(), sMsg.A.no(idx).ezUpTime_A1.getValue(), sMsg.A.no(idx).ezUpTimeZone_A1.getValue());
        if (NMAM8175E.equals(cMsg.getMessageCode())) {
            return tMsg;
        }

        if (tMsg == null) {
            return tMsg;
        }

        return tMsg;
    }

    /**
     * Update PROD_CTRL Table
     * @param cMsg NMAL0200CMsg
     * @param sMsg NMAL0200SMsg
     * @param idx int
     * @param tMsg PROD_CTRLTMsg
     * @return PROD_CTRLTMsg
     */
    private PROD_CTRLTMsg updateProdCtrl(NMAL0200CMsg cMsg, NMAL0200SMsg sMsg, int idx, PROD_CTRLTMsg tMsg) {

        ZYPEZDItemValueSetter.setValue(tMsg.prodCtrlNm, sMsg.A.no(idx).prodCtrlNm_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.prodCtrlDescTxt, sMsg.A.no(idx).prodCtrlNm_A1);

        EZDTBLAccessor.update(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NMAM8000E, new String[] {PROD_CTRL, sMsg.A.no(idx).prodCtrlCd_A1.getValue() });
            return tMsg;
        }

        return tMsg;
    }

    /**
     * find PROD_CTRL Table
     * @param cMsg NMAL0200CMsg
     * @param sMsg NMAL0200SMsg
     * @param glblCmpyCd String
     * @param idx int
     * @return PROD_CTRLTMsg
     */
    private PROD_CTRLTMsg insertProdCtrl(NMAL0200CMsg cMsg, NMAL0200SMsg sMsg, String glblCmpyCd, int idx) {

        PROD_CTRLTMsg tMsg = new PROD_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prodCtrlCd, sMsg.A.no(idx).prodCtrlCd_A1);

        tMsg = (PROD_CTRLTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            tMsg = new PROD_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.mdseStruElmntTpCd, sMsg.A.no(idx).mdseStruElmntTpCd_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.prodCtrlCd, sMsg.A.no(idx).prodCtrlCd_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.prodCtrlNm, sMsg.A.no(idx).prodCtrlNm_A1);
            ZYPEZDItemValueSetter.setValue(tMsg.prodCtrlDescTxt, sMsg.A.no(idx).prodCtrlNm_A1);

            String mdseStruElmntTpCd = sMsg.A.no(idx).mdseStruElmntTpCd_A1.getValue();
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP.equals(mdseStruElmntTpCd)) {
                ssmParam.put(DB_PARAM_MDSE_STRU_ELMNT_TP_CD, MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP);
                S21SsmEZDResult result = NMAL0200Query.getInstance().findSingleRecord("getMaxHrchSortNum", ssmParam);
                if (result.isCodeNormal()) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prodCtrlSortNum, ((BigDecimal) result.getResultObject()).add(BigDecimal.ONE));
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.prodCtrlSortNum, BigDecimal.ONE);
                }

            } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE.equals(mdseStruElmntTpCd)) {

                ssmParam.put(DB_PARAM_MDSE_STRU_ELMNT_TP_CD, MDSE_STRU_ELMNT_TP.PRODUCT_LINE);
                S21SsmEZDResult result = NMAL0200Query.getInstance().findSingleRecord("getMaxHrchSortNum", ssmParam);
                if (result.isCodeNormal()) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prodCtrlSortNum, ((BigDecimal) result.getResultObject()).add(BigDecimal.ONE));
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.prodCtrlSortNum, BigDecimal.ONE);
                }

            } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2.equals(mdseStruElmntTpCd)) {

                ssmParam.put(DB_PARAM_MDSE_STRU_ELMNT_TP_CD, MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2);
                S21SsmEZDResult result = NMAL0200Query.getInstance().findSingleRecord("getMaxHrchSortNum", ssmParam);
                if (result.isCodeNormal()) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prodCtrlSortNum, ((BigDecimal) result.getResultObject()).add(BigDecimal.ONE));
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.prodCtrlSortNum, BigDecimal.ONE);
                }

            } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals(mdseStruElmntTpCd)) {

                ssmParam.put(DB_PARAM_MDSE_STRU_ELMNT_TP_CD, MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3);
                S21SsmEZDResult result = NMAL0200Query.getInstance().findSingleRecord("getMaxHrchSortNum", ssmParam);
                if (result.isCodeNormal()) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prodCtrlSortNum, ((BigDecimal) result.getResultObject()).add(BigDecimal.ONE));
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.prodCtrlSortNum, BigDecimal.ONE);
                }
                ZYPEZDItemValueSetter.setValue(tMsg.scdProdHrchCd, sMsg.A.no(idx).scdProdHrchCd_A1);

            } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4.equals(mdseStruElmntTpCd)) {

                ssmParam.put(DB_PARAM_MDSE_STRU_ELMNT_TP_CD, MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4);
                S21SsmEZDResult result = NMAL0200Query.getInstance().findSingleRecord("getMaxHrchSortNum", ssmParam);
                if (result.isCodeNormal()) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prodCtrlSortNum, ((BigDecimal) result.getResultObject()).add(BigDecimal.ONE));
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.prodCtrlSortNum, BigDecimal.ONE);
                }

            } else {
                cMsg.setMessageInfo(NMAM8509E, null);
                return null;
            }

            EZDTBLAccessor.insert(tMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0282E, new String[] {PROD_CTRL });
                return null;
            }
        } else {
            return null;
        }
        return tMsg;
    }

    /**
     * PROD_CTRL Table Recode Lock
     * @param cMsg NMAL0200CMsg
     * @param glblCmpyCd String
     * @param prodCtrlCd String
     * @param ezUpTime String
     * @param ezUpTimeZone String
     * @return PROD_CTRLTMsg
     */
    private PROD_CTRLTMsg lockProdCtrlForUpdate(NMAL0200CMsg cMsg, String glblCmpyCd, String prodCtrlCd, String ezUpTime, String ezUpTimeZone) {

        PROD_CTRLTMsg tMsg = new PROD_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prodCtrlCd, prodCtrlCd);

        tMsg = (PROD_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

        if (tMsg == null) {
            return null;
        }

        String currentEzUpTime = tMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = tMsg.ezUpTimeZone.getValue();

        if (ZYPCommonFunc.hasValue(ezUpTime) && ZYPCommonFunc.hasValue(ezUpTimeZone)) {
            if (!ZYPDateUtil.isSameTimeStamp(ezUpTime, ezUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                cMsg.setMessageInfo(NMAM8175E, new String[] {PROD_CTRL, PROD_CTRL_CD, String.valueOf(tMsg.prodCtrlCd.getValue()) });
                return null;
            }
        }

        return tMsg;
    }
}
