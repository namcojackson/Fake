/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6880;

import static business.blap.NMAL6880.constant.NMAL6880Constant.BIZ_APP_ID;
import static business.blap.NMAL6880.constant.NMAL6880Constant.DB_PARAM_EFF_FROM_DT;
import static business.blap.NMAL6880.constant.NMAL6880Constant.DB_PARAM_EFF_THRU_DT;
import static business.blap.NMAL6880.constant.NMAL6880Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NMAL6880.constant.NMAL6880Constant.DB_PARAM_ROWNUM;
import static business.blap.NMAL6880.constant.NMAL6880Constant.DB_PARAM_RTL_SWH_CD;
import static business.blap.NMAL6880.constant.NMAL6880Constant.DB_PARAM_RTL_WH_CD;
import static business.blap.NMAL6880.constant.NMAL6880Constant.DB_PARAM_SALES_DATE;
import static business.blap.NMAL6880.constant.NMAL6880Constant.DB_PARAM_VND_SHIP_TO_CUST_CD;
import static business.blap.NMAL6880.constant.NMAL6880Constant.DB_PARAM_VND_SHIP_TO_XREF_PK_LIST;
import static business.blap.NMAL6880.constant.NMAL6880Constant.DB_PARAM_VND_XREF_TP_CD;
import static business.blap.NMAL6880.constant.NMAL6880Constant.DELETE;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_CANCEL_DETAIL_LINE;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_CMN_COL_CLEAR;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_CMN_COL_SAVE;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_CMN_SUBMIT;
import static business.blap.NMAL6880.constant.NMAL6880Constant.MAX_EFF_THRU_DATE;
import static business.blap.NMAL6880.constant.NMAL6880Constant.MSG_END_DATE;
import static business.blap.NMAL6880.constant.NMAL6880Constant.MSG_RTL_SWH_CD;
import static business.blap.NMAL6880.constant.NMAL6880Constant.MSG_RTL_WH_CD;
import static business.blap.NMAL6880.constant.NMAL6880Constant.MSG_START_DATE;
import static business.blap.NMAL6880.constant.NMAL6880Constant.MSG_VND_SHIP_TO_CUST_CD;
import static business.blap.NMAL6880.constant.NMAL6880Constant.NMAM0282E;
import static business.blap.NMAL6880.constant.NMAL6880Constant.NMAM0803E;
import static business.blap.NMAL6880.constant.NMAL6880Constant.NMAM0836E;
import static business.blap.NMAL6880.constant.NMAL6880Constant.NMAM8000E;
import static business.blap.NMAL6880.constant.NMAL6880Constant.NMAM8121E;
import static business.blap.NMAL6880.constant.NMAL6880Constant.NMAM8175E;
import static business.blap.NMAL6880.constant.NMAL6880Constant.NMAM8506E;
import static business.blap.NMAL6880.constant.NMAL6880Constant.NMAM8509E;
import static business.blap.NMAL6880.constant.NMAL6880Constant.SUBMIT;
import static business.blap.NMAL6880.constant.NMAL6880Constant.VND_SHIP_TO_XREF;
import static business.blap.NMAL6880.constant.NMAL6880Constant.VND_SHIP_TO_XREF_PK;
import static business.blap.NMAL6880.constant.NMAL6880Constant.XX_CHK_BOX_A1;
import static business.blap.NMAL6880.constant.NMAL6880Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6880.common.NMAL6880CommonLogic;
import business.db.VND_SHIP_TO_XREFTMsg;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_XREF_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NMAL6880 TPC09 WH Mapping Maintenance
 * Function Name : update business process
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/09/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NMAL6880BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NMAL6880_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_CMN_Submit((NMAL6880CMsg) cMsg, (NMAL6880SMsg) sMsg);
            } else if (EVENT_NM_NMAL6880_CANCEL_DETAIL_LINE.equals(screenAplID)) {
                doProcess_CancelDetailLine((NMAL6880CMsg) cMsg, (NMAL6880SMsg) sMsg);
            } else if (EVENT_NM_NMAL6880_CMN_COL_CLEAR.equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NMAL6880CMsg) cMsg, (NMAL6880SMsg) sMsg);
            } else if (EVENT_NM_NMAL6880_CMN_COL_SAVE.equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NMAL6880CMsg) cMsg, (NMAL6880SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * CMN_Submit
     * @param cMsg NMAL6880CMsg
     * @param sMsg NMAL6880SMsg
     */
    private void doProcess_CMN_Submit(NMAL6880CMsg cMsg, NMAL6880SMsg sMsg) {

        NMAL6880CommonLogic.updateGlblMsg(cMsg, sMsg);

        String glblCmpyCd = getGlobalCompanyCode();
        String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);

        int maxDisplayRows = cMsg.A.length();
        int scrnStartIndex = (cMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;
        int maxIndex = sMsg.A.getValidCount();

        // All Pk List
        List<BigDecimal> vndShipToXrefPkList = new ArrayList<BigDecimal>();

        try {
            // All Validation Check
            for (int i = 0; i < maxIndex; i++) {
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).vndShipToCustCd_A1)) {
                    sMsg.A.no(i).vndShipToCustCd_A1.setErrorInfo(1, NMAM0836E, new String[] {MSG_VND_SHIP_TO_CUST_CD });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }

                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlWhCd_A1)) {
                    sMsg.A.no(i).rtlWhCd_A1.setErrorInfo(1, NMAM0836E, new String[] {MSG_RTL_WH_CD });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }

                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlSwhCd_A1)) {
                    sMsg.A.no(i).rtlSwhCd_A1.setErrorInfo(1, NMAM0836E, new String[] {MSG_RTL_SWH_CD });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }

                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).effFromDt_A1)) {
                    sMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM0836E, new String[] {MSG_START_DATE });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }

                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).effFromDt_A1)) {
                    String effFromDt = sMsg.A.no(i).effFromDt_A1.getValue();
                    if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).vndShipToXrefPk_A1) && ZYPDateUtil.compare(effFromDt, salesDate) < 0) {
                        sMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM0803E, new String[] {MSG_START_DATE });
                        int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                        cMsg.xxPageShowFromNum.setValue(errScrnInex);
                        return;
                    }

                    if (ZYPCommonFunc.hasValue(sMsg.A.no(i).effThruDt_A1)) {
                        String effThruDt = sMsg.A.no(i).effThruDt_A1.getValue();
                        if (ZYPDateUtil.compare(effThruDt, effFromDt) < 0) {
                            sMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM8506E, new String[] {MSG_END_DATE, MSG_START_DATE });
                            sMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAM8506E, new String[] {MSG_END_DATE, MSG_START_DATE });
                            int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                            cMsg.xxPageShowFromNum.setValue(errScrnInex);
                            return;
                        }
                    }
                }

                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).vndShipToXrefPk_A1)) {
                    vndShipToXrefPkList.add(sMsg.A.no(i).vndShipToXrefPk_A1.getValue());
                }

                String vndShipToCustCd = sMsg.A.no(i).vndShipToCustCd_A1.getValue();
                // global Detail Loop
                for (int j = i + 1; j < maxIndex; j++) {
                    if (vndShipToCustCd.equals(sMsg.A.no(j).vndShipToCustCd_A1.getValue())) {
                        // Check Eff Date
                        long scrnEffFromDt = new Long(sMsg.A.no(i).effFromDt_A1.getValue());
                        long glEffFromDt = new Long(sMsg.A.no(j).effFromDt_A1.getValue());
                        long scrnEffThruDt = MAX_EFF_THRU_DATE;
                        long glEffThruDt = MAX_EFF_THRU_DATE;
                        if (ZYPCommonFunc.hasValue(sMsg.A.no(i).effThruDt_A1)) {
                            scrnEffThruDt = new Long(sMsg.A.no(i).effThruDt_A1.getValue());
                        }
                        if (ZYPCommonFunc.hasValue(sMsg.A.no(j).effThruDt_A1)) {
                            glEffThruDt = new Long(sMsg.A.no(j).effThruDt_A1.getValue());
                        }
                        if (ZYPCommonFunc.isCheckSuutiHanni(scrnEffFromDt, glEffFromDt, glEffThruDt)) {
                            sMsg.A.no(j).vndShipToCustCd_A1.setErrorInfo(1, NMAM8509E, null);
                            sMsg.A.no(j).rtlWhCd_A1.setErrorInfo(1, NMAM8509E, null);
                            sMsg.A.no(j).rtlSwhCd_A1.setErrorInfo(1, NMAM8509E, null);
                            sMsg.A.no(j).effFromDt_A1.setErrorInfo(1, NMAM8509E, null);
                            sMsg.A.no(j).effThruDt_A1.setErrorInfo(1, NMAM8509E, null);
                            int errScrnInex = (j / maxDisplayRows) * maxDisplayRows + 1;
                            cMsg.xxPageShowFromNum.setValue(errScrnInex);
                            return;
                        }
                        if (ZYPCommonFunc.isCheckSuutiHanni(scrnEffThruDt, glEffFromDt, glEffThruDt)) {
                            sMsg.A.no(j).vndShipToCustCd_A1.setErrorInfo(1, NMAM8509E, null);
                            sMsg.A.no(j).rtlWhCd_A1.setErrorInfo(1, NMAM8509E, null);
                            sMsg.A.no(j).rtlSwhCd_A1.setErrorInfo(1, NMAM8509E, null);
                            sMsg.A.no(j).effFromDt_A1.setErrorInfo(1, NMAM8509E, null);
                            sMsg.A.no(j).effThruDt_A1.setErrorInfo(1, NMAM8509E, null);
                            int errScrnInex = (j / maxDisplayRows) * maxDisplayRows + 1;
                            cMsg.xxPageShowFromNum.setValue(errScrnInex);
                            return;
                        }
                    }
                }
            }
            // DB All Check
            for (int i = 0; i < maxIndex; i++) {
                // Check New Line
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).vndShipToXrefPk_A1)) {
                    // VND_SHIP_TO_XREF Master Check
                    Map<String, Object> ssmParam = new HashMap<String, Object>();
                    ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                    ssmParam.put(DB_PARAM_VND_XREF_TP_CD, VND_XREF_TP.CUSA_TO_CSA);
                    ssmParam.put(DB_PARAM_VND_SHIP_TO_XREF_PK_LIST, vndShipToXrefPkList);
                    ssmParam.put(DB_PARAM_VND_SHIP_TO_CUST_CD, sMsg.A.no(i).vndShipToCustCd_A1.getValue());
                    ssmParam.put(DB_PARAM_EFF_FROM_DT, sMsg.A.no(i).effFromDt_A1.getValue());
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(i).effThruDt_A1)) {
                        ssmParam.put(DB_PARAM_EFF_THRU_DT, sMsg.A.no(i).effThruDt_A1.getValue());
                    } else {
                        ssmParam.put(DB_PARAM_EFF_THRU_DT, String.valueOf(MAX_EFF_THRU_DATE));
                    }

                    S21SsmEZDResult result = NMAL6880Query.getInstance().countVendorShipToXref(ssmParam);

                    if (result.isCodeNormal()) {
                        BigDecimal retCnt = (BigDecimal) result.getResultObject();
                        if (BigDecimal.ZERO.compareTo(retCnt) == -1) {
                            sMsg.A.no(i).vndShipToCustCd_A1.setErrorInfo(1, NMAM8509E, null);
                            sMsg.A.no(i).rtlWhCd_A1.setErrorInfo(1, NMAM8509E, null);
                            sMsg.A.no(i).rtlSwhCd_A1.setErrorInfo(1, NMAM8509E, null);
                            sMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM8509E, null);
                            sMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAM8509E, null);
                            int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                            cMsg.xxPageShowFromNum.setValue(errScrnInex);
                            return;
                        }
                    } else {
                        sMsg.A.no(i).vndShipToCustCd_A1.setErrorInfo(1, NMAM8509E, null);
                        sMsg.A.no(i).rtlWhCd_A1.setErrorInfo(1, NMAM8509E, null);
                        sMsg.A.no(i).rtlSwhCd_A1.setErrorInfo(1, NMAM8509E, null);
                        sMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM8509E, null);
                        sMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAM8509E, null);
                        int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                        cMsg.xxPageShowFromNum.setValue(errScrnInex);
                        return;
                    }
                }

                // WH Master Check
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                ssmParam.put(DB_PARAM_VND_XREF_TP_CD, VND_XREF_TP.CUSA_TO_CSA);
                ssmParam.put(DB_PARAM_RTL_WH_CD, sMsg.A.no(i).rtlWhCd_A1.getValue());
                ssmParam.put(DB_PARAM_SALES_DATE, salesDate);
                ssmParam.put(DB_PARAM_ROWNUM, 1);

                S21SsmEZDResult result = NMAL6880Query.getInstance().findRtlSwh(ssmParam);

                if (!result.isCodeNormal()) {
                    sMsg.A.no(i).rtlWhCd_A1.setErrorInfo(1, NMAM8121E, new String[] {MSG_RTL_WH_CD });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }

                // WH / SWH Combination Check
                ssmParam = new HashMap<String, Object>();
                ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                ssmParam.put(DB_PARAM_VND_XREF_TP_CD, VND_XREF_TP.CUSA_TO_CSA);
                ssmParam.put(DB_PARAM_RTL_WH_CD, sMsg.A.no(i).rtlWhCd_A1.getValue());
                ssmParam.put(DB_PARAM_RTL_SWH_CD, sMsg.A.no(i).rtlSwhCd_A1.getValue());
                ssmParam.put(DB_PARAM_SALES_DATE, salesDate);
                ssmParam.put(DB_PARAM_ROWNUM, 1);

                result = NMAL6880Query.getInstance().findRtlSwh(ssmParam);

                if (!result.isCodeNormal()) {
                    sMsg.A.no(i).rtlWhCd_A1.setErrorInfo(1, NMAM8121E, new String[] {MSG_RTL_WH_CD });
                    sMsg.A.no(i).rtlSwhCd_A1.setErrorInfo(1, NMAM8121E, new String[] {MSG_RTL_SWH_CD });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }

                String invtyLocCd = (String) result.getResultObject();

                // Check EnableWH
                NMXC100001EnableWHData enableWhData = NMXC100001EnableWH.checkEnableWH(glblCmpyCd, invtyLocCd, BIZ_APP_ID, null, ZYPConstant.FLG_OFF_N, new String[] {});
                if (enableWhData.getXxMsgId() != null) {
                    sMsg.A.no(i).rtlWhCd_A1.setErrorInfo(1, enableWhData.getXxMsgId(), null);
                    sMsg.A.no(i).rtlSwhCd_A1.setErrorInfo(1, enableWhData.getXxMsgId(), null);
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum.setValue(errScrnInex);
                    return;
                }

                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).vndShipToXrefPk_A1)) {
                    // New Line
                    VND_SHIP_TO_XREFTMsg tMsg = new VND_SHIP_TO_XREFTMsg();
                    BigDecimal vndShipToXrefPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.VND_SHIP_TO_XREF_SQ);
                    ZYPEZDItemValueSetter.setValue(tMsg.vndShipToXrefPk, vndShipToXrefPk);
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.invtyLocCd, invtyLocCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, sMsg.A.no(i).rtlWhCd_A1);
                    ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, sMsg.A.no(i).rtlSwhCd_A1);
                    ZYPEZDItemValueSetter.setValue(tMsg.vndXrefTpCd, VND_XREF_TP.CUSA_TO_CSA);
                    ZYPEZDItemValueSetter.setValue(tMsg.vndShipToCustCd, sMsg.A.no(i).vndShipToCustCd_A1);
                    ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, sMsg.A.no(i).effFromDt_A1);
                    ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, sMsg.A.no(i).effThruDt_A1);
                    EZDTBLAccessor.insert(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NMAM0282E, new String[] {VND_SHIP_TO_XREF });
                        return;
                    }
                } else {
                    // Update Line
                    BigDecimal vndShipToXrefPk = sMsg.A.no(i).vndShipToXrefPk_A1.getValue();
                    VND_SHIP_TO_XREFTMsg tMsg = lockVndShipToXrefForUpdate(cMsg, sMsg, glblCmpyCd, i);
                    if (NMAM8175E.equals(cMsg.getMessageCode())) {
                        return;
                    }
                    if (tMsg == null) {
                        cMsg.setMessageInfo(NMAM8175E, new String[] {VND_SHIP_TO_XREF, VND_SHIP_TO_XREF_PK, String.valueOf(vndShipToXrefPk) });
                        return;
                    }
                    ZYPEZDItemValueSetter.setValue(tMsg.invtyLocCd, invtyLocCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, sMsg.A.no(i).rtlWhCd_A1);
                    ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, sMsg.A.no(i).rtlSwhCd_A1);
                    ZYPEZDItemValueSetter.setValue(tMsg.vndXrefTpCd, VND_XREF_TP.CUSA_TO_CSA);
                    ZYPEZDItemValueSetter.setValue(tMsg.vndShipToCustCd, sMsg.A.no(i).vndShipToCustCd_A1);
                    ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, sMsg.A.no(i).effFromDt_A1);
                    ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, sMsg.A.no(i).effThruDt_A1);
                    EZDTBLAccessor.update(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NMAM8000E, new String[] {VND_SHIP_TO_XREF, String.valueOf(tMsg.vndShipToXrefPk.getValue()) });
                        return;
                    }
                }

            }
            cMsg.setMessageInfo(ZZZM9003I, new String[] {SUBMIT });
        } finally {
            NMAL6880CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
        }

    }

    /**
     * CancelDetailLine
     * @param cMsg NMAL6880CMsg
     * @param sMsg NMAL6880SMsg
     */
    private void doProcess_CancelDetailLine(NMAL6880CMsg cMsg, NMAL6880SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NMAL6880CommonLogic.updateGlblMsg(cMsg, sMsg);
        List<Integer> delIdx = ZYPTableUtil.getSelectedRows(sMsg.A, XX_CHK_BOX_A1, ZYPConstant.CHKBOX_ON_Y);

        List<Integer> deleteIdxList = new ArrayList<Integer>();
        try {
            for (int idx : delIdx) {
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(idx).vndShipToXrefPk_A1)) {
                    deleteIdxList.add(idx);
                } else {
                    BigDecimal vndShipToXrefPk = sMsg.A.no(idx).vndShipToXrefPk_A1.getValue();
                    VND_SHIP_TO_XREFTMsg tMsg = lockVndShipToXrefForUpdate(cMsg, sMsg, glblCmpyCd, idx);
                    if (NMAM8175E.equals(cMsg.getMessageCode())) {
                        return;
                    }
                    if (tMsg == null) {
                        cMsg.setMessageInfo(NMAM8175E, new String[] {VND_SHIP_TO_XREF, VND_SHIP_TO_XREF_PK, String.valueOf(vndShipToXrefPk) });
                        return;
                    }
                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NMAM8000E, new String[] {VND_SHIP_TO_XREF, String.valueOf(tMsg.vndShipToXrefPk.getValue()) });
                        return;
                    }
                    deleteIdxList.add(idx);
                }
            }
            if (deleteIdxList.size() > 0) {
                ZYPTableUtil.deleteRows(sMsg.A, deleteIdxList);
                cMsg.setMessageInfo(ZZZM9003I, new String[] {DELETE });
            }

            if (cMsg.xxPageShowFromNum.getValueInt() > sMsg.A.getValidCount()) {
                ZYPEZDItemValueSetter.setValue(sMsg.xxPageShowFromNum, BigDecimal.valueOf(sMsg.A.getValidCount() - 1));
            }
        } finally {
            NMAL6880CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
        }
    }

    /**
     * VND_SHIP_TO_XREF Table Recode Lock
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param cMsg NPAL1280CMsg
     * @param idx int
     * @return VND_SHIP_TO_XREFTMsg
     */
    private static VND_SHIP_TO_XREFTMsg lockVndShipToXrefForUpdate(NMAL6880CMsg cMsg, NMAL6880SMsg sMsg, String glblCmpyCd, int idx) {

        VND_SHIP_TO_XREFTMsg tMsg = new VND_SHIP_TO_XREFTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.vndShipToXrefPk, sMsg.A.no(idx).vndShipToXrefPk_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);

        tMsg = (VND_SHIP_TO_XREFTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg == null) {
            return null;
        }

        String findEzUpTime = sMsg.A.no(idx).ezUpTime_A1.getValue();
        String findEzUpTimeZone = sMsg.A.no(idx).ezUpTimeZone_A1.getValue();
        String currentEzUpTime = tMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = tMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            cMsg.setMessageInfo(NMAM8175E, new String[] {VND_SHIP_TO_XREF, VND_SHIP_TO_XREF_PK, String.valueOf(tMsg.vndShipToXrefPk.getValue()) });
            return null;
        }
        return tMsg;
    }
}
