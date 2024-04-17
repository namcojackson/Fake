/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL1110.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFBL1110.NFBL1110CMsg;
import business.blap.NFBL1110.NFBL1110Query;
import business.blap.NFBL1110.NFBL1110SMsg;
import business.blap.NFBL1110.constant.NFBL1110Constant;
import business.db.AP_DS_WF_STSTMsg;
import business.db.AP_MAINT_INV_STSTMsg;
import business.db.CM_MAINT_INVTMsg;
import business.db.CM_MAINT_INV_BATTMsg;
import business.db.CM_MAINT_INV_MTRTMsg;
import business.db.CM_MAINT_INV_SERTMsg;
import business.db.DOC_MGT_CATGTMsg;
import business.parts.NFZC505001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDown;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownConst;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownParameter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * AP Invoice Maintenance Batch Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   CUSA            Y.Aikawa        Create          N/A
 * 2016/08/04   Hitachi         J.Kim           Update          QC#12712
 * 2016/08/04   Fujitsu         T.Murai         Update          QC#12692
 * 2016/08/05   Hitachi         K.Kojima        Update          QC#12971
 * 2016/08/22   Fujitsu         T.Murai         Update          QC#12947
 * 2016/08/23   Fujitsu         T.Murai         Update          QC#12830,12729,12965
 * 2016/09/09   Hitachi         K.Kojima        Update          QC#12725
 * 2016/09/29   Fujitsu         W.Honda         Update          Unit Test#201
 * 2017/03/17   Hitachi         E.Kameishi      Update          QC#14205
 * 2017/11/13   CITS            K.Ogino         Update          QC#21686
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 * </pre>
 */
public class NFBL1110CommonLogic implements NFBL1110Constant {

    // START 2016/08/05 K.Kojima [QC#12971,DEL]
    // /** S21UserProfileService Instance */
    // public static final S21UserProfileService PROFILE_SERVICE =
    // S21UserProfileServiceFactory.getInstance().getService();
    // /** Global Company Code */
    // public static final String GLBL_CMPY_CD =
    // PROFILE_SERVICE.getGlobalCompanyCode();
    // /** User ID */
    // public static final String USER_ID =
    // S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
    // /** User Name */
    // public static final String USER_NM =
    // S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getFullName();
    // END 2016/08/05 K.Kojima [QC#12971,DEL]

    /**
     * Method name: getApBatNum
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static String getApBatNum(NFBL1110CMsg bizMsg, String glblCmpyCd) {
        EZDDebugOutput.println(5, "getApBatNum================================START", null);
        CM_MAINT_INVTMsg cmMaintInv = new CM_MAINT_INVTMsg();
        ZYPEZDItemValueSetter.setValue(cmMaintInv.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cmMaintInv.apInvNum, bizMsg.apInvNum_IH.getValue());
        ZYPEZDItemValueSetter.setValue(cmMaintInv.apVndCd, bizMsg.apVndCd_HD.getValue());
        cmMaintInv = (CM_MAINT_INVTMsg) EZDTBLAccessor.findByKey(cmMaintInv);
        EZDDebugOutput.println(5, "getApBatNum================================END", null);
        if (cmMaintInv == null) {
            return null;
        } else {
            return cmMaintInv.apBatNum.getValue();
        }
    }

    /**
     * Method name: getBatPkByBatNum
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    @SuppressWarnings("unchecked")
    public static BigDecimal getBatPkByBatNum(String apBatNum) {
        S21SsmEZDResult ssmResult = NFBL1110Query.getInstance().getBatPkByBatNum(apBatNum);
        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            if (resultList.size() == 1) {
                Map map = (Map) resultList.get(0);
                return (BigDecimal) map.get(CM_MAINT_INV_BAT_PK);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Method name: updateRunningValue
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void updateRunningValue(NFBL1110CMsg bizMsg) {
        BigDecimal apRunTotAmt =  BigDecimal.ZERO;
        for (int i = 0; i < bizMsg.Y.getValidCount(); i++) {
            apRunTotAmt = apRunTotAmt.add(bizMsg.Y.no(i).apInvAmt_Y1.getValue());
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.apRunTotAmt_BA, apRunTotAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.apRunTotCnt_BA, new BigDecimal(bizMsg.Y.getValidCount()));
    }

    /**
     * Method name: getInvList
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    @SuppressWarnings("unchecked")
    public static void saveInvList(NFBL1110CMsg bizMsg, List invList) {

        bizMsg.Y.clear();
        bizMsg.Y.setValidCount(invList.size());

        BigDecimal apRunTotAmt = BigDecimal.ZERO;

        for (int i = 0; i < bizMsg.Y.getValidCount(); i++) {
            Map map = (Map) invList.get(i);
            ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(i).apInvNum_Y1, (String) map.get(AP_INV_NUM));
            ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(i).apVndCd_Y1, (String) map.get(AP_VND_CD));
            ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(i).apInvAmt_Y1, (BigDecimal) map.get(AP_INV_AMT));
            // START 2016/09/29 W.Honda [Unit Test#201,ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(i).docMgtDocId_Y1, (BigDecimal) map.get(DOC_MGT_DOC_ID));
            ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(i).docMgtCatgCd_Y1, (String) map.get(DOC_MGT_CATG_CD));
            // END   2016/09/29 W.Honda [Unit Test#201,ADD]
            apRunTotAmt = apRunTotAmt.add((BigDecimal) map.get(AP_INV_AMT));
        }
        // set running value
        ZYPEZDItemValueSetter.setValue(bizMsg.apRunTotAmt_BA, apRunTotAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.apRunTotCnt_BA, new BigDecimal(invList.size()));

    }

    /**
     * Method name: searchRecordByInvInfo
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param apInvNum String
     * @param apVndCd String
     * @param ssmResult S21SsmEZDResult
     */
    public static S21SsmEZDResult searchRecordByInvInfo(String apInvNum, String apVndCd) {
        S21SsmEZDResult ssmResult = NFBL1110Query.getInstance().searchRecordByInvInfo(apInvNum, apVndCd);
        return ssmResult;

    }

    /**
     * Method name: searchRecordByBatchPk
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cmMaintInvBatPk BigDecimal
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult searchRecordByBatchPk(BigDecimal cmMaintInvBatPk) {
        S21SsmEZDResult ssmResult = NFBL1110Query.getInstance().searchRecordByBatchPk(cmMaintInvBatPk);
        return ssmResult;

    }

    /**
     * Method name: getTotalRecordCount
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cmMaintInvBatPk BigDecimal
     * @return Integer
     */
    @SuppressWarnings("unchecked")
    public static int getTotalRecordCount(BigDecimal cmMaintInvBatPk) {
        S21SsmEZDResult ssmResult = NFBL1110Query.getInstance().getTotalRecordCount(cmMaintInvBatPk);
        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            Map map = (Map) resultList.get(0);
            BigDecimal totRecCnt = (BigDecimal) map.get(TOT_REC_CNT);
            return totRecCnt.intValue();
        }
        return 0;
    }

    /**
     * Method name: getInvLineTotalRecordCount
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param apInvNum String
     * @param apVndCd String
     * @return Integer
     */
    @SuppressWarnings("unchecked")
    public static int getInvLineTotalRecordCount(String apInvNum, String apVndCd) {
        S21SsmEZDResult ssmResult = NFBL1110Query.getInstance().getInvLineTotalRecordCount(apInvNum, apVndCd);
        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            Map map = (Map) resultList.get(0);
            BigDecimal totRecCnt = (BigDecimal) map.get(TOT_REC_CNT);
            return totRecCnt.intValue();
        }
        return 0;
    }

    /**
     * Method name: setDBInfoToScreenCommon
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    @SuppressWarnings("unchecked")
    // START 2016/09/29 W.Honda [Unit Test#201,MOD]
//    public static void setDBInfoToScreenCommon(EZDCMsg cMsg, List resultList) {
    public static void setDBInfoToScreenCommon(EZDCMsg cMsg, List resultList, String glblCmpyCd) {
    // START 2016/09/29 W.Honda [Unit Test#201,MOD]
        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;

        String prevApInvNum = EMPTY_STRING;
        String prevApVndCd = EMPTY_STRING;
        String prevSerNum = EMPTY_STRING;
        int intCntrTpCount = 0;
        int indexResult = 0;

        for (int i = 0; i < bizMsg.A.getValidCount(); ) {
            Map map = new HashMap();
            if (resultList.size() > indexResult) {
                map = (Map) resultList.get(indexResult);
                // Del Start 2016/08/23 QC#12830
//                if (i != 0) {
//                    if (!prevApInvNum.equals((String)map.get(AP_INV_NUM))
//                    ||  !prevApVndCd.equals((String)map.get(AP_VND_CD))
//                    ||  !prevSerNum.equals((String)map.get(SER_NUM))
//                    ) {
//                        // set Previous row's information
////                        int intRestOfRows = INT_6 - intCntrTpCount;
////                        for (int j = 0; j < intRestOfRows; j++) {
//                            // Invoice Line Info
//                            bizMsg.A.no(i).ezUpTime_SE.clear();
//                            bizMsg.A.no(i).ezUpTimeZone_SE.clear();
//                            bizMsg.A.no(i).serNum_A1.clear();
//                            bizMsg.A.no(i).ovrdSerNum_A1.clear();
//                            bizMsg.A.no(i).startDt_A1.clear();
//                            bizMsg.A.no(i).endDt_A1.clear();
//                            bizMsg.A.no(i).baseAmt_A1.clear();
//                            // Set blank for Meter.
//                            bizMsg.A.no(i).ezUpTime_ME.clear();
//                            bizMsg.A.no(i).ezUpTimeZone_ME.clear();
//                            // Create [Counter Type] pulldown.
//                            NFBL1110CommonLogic.createCntrTpPulldown(bizMsg, i);
//                            bizMsg.A.no(i).cntrTpCd_A1.clear();
//                            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).startReadMtrCnt_A1, BigDecimal.ZERO);
//                            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).endReadMtrCnt_A1, BigDecimal.ZERO);
//                            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).readMtrCnt_A1, BigDecimal.ZERO);
//                            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).apTolAmt_A1, BigDecimal.ZERO);
//                            i++;
////                        }
//                        intCntrTpCount = 0;
//                    }
//                }
                // Del End 2016/08/23 QC#12830
                if (i == 0) {
                    // For Submit button for deleting invoice.
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxInvDelFlg, ZYPConstant.FLG_ON_Y);
                    // Batch Header Info
                    ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_BA, (String) map.get(CMIB_EZUPTIME));
                    ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_BA, (String) map.get(CMIB_EZUPTIMEZONE));
                    ZYPEZDItemValueSetter.setValue(bizMsg.cmMaintInvBatPk_BA, (BigDecimal) map.get(CM_MAINT_INV_BAT_PK));
                    ZYPEZDItemValueSetter.setValue(bizMsg.apBatNum_BA, (String) map.get(AP_BAT_NUM));
                    ZYPEZDItemValueSetter.setValue(bizMsg.apBatDt_BA, (String) map.get(AP_BAT_DT));
                    ZYPEZDItemValueSetter.setValue(bizMsg.apCtrlAmt_BA, (BigDecimal) map.get(AP_CTRL_AMT));
                    ZYPEZDItemValueSetter.setValue(bizMsg.apCtrlCnt_BA, (BigDecimal) map.get(AP_CTRL_CNT));
                    ZYPEZDItemValueSetter.setValue(bizMsg.apRunTotAmt_BA, (BigDecimal) map.get(AP_RUN_TOT_AMT));
                    ZYPEZDItemValueSetter.setValue(bizMsg.apRunTotCnt_BA, (BigDecimal) map.get(AP_RUN_TOT_CNT));
                    //START 2017/03/16 E.Kameishi [QC#14205, ADD]
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs_BA, (String) map.get(XX_REC_HIST_CRAT_TS_BA));
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm_BA, ZYPRecHistUtil.getFullNameForRecHist((String) map.get(XX_REC_HIST_CRAT_BY_NM_BA)));
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs_BA, (String) map.get(XX_REC_HIST_UPD_TS_BA));
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm_BA, ZYPRecHistUtil.getFullNameForRecHist((String) map.get(XX_REC_HIST_UPD_BY_NM_BA)));
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm_BA, (String) map.get(XX_REC_HIST_TBL_NM_BA));
                    //END 2017/03/16 E.Kameishi [QC#14205, ADD]
                    // Invoice Header Info
                    ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_IH, (String) map.get(CMI_EZUPTIME));
                    ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_IH, (String) map.get(CMI_EZUPTIMEZONE));
                    ZYPEZDItemValueSetter.setValue(bizMsg.apDsWfStsCd_IH, (String) map.get(AP_DS_WF_STS_CD));
                    ZYPEZDItemValueSetter.setValue(bizMsg.apDsWfStsNm_IH, (String) map.get(AP_DS_WF_STS_NM));
                    ZYPEZDItemValueSetter.setValue(bizMsg.apMaintInvStsCd_IH, (String) map.get(AP_MAINT_INV_STS_CD));
                    ZYPEZDItemValueSetter.setValue(bizMsg.apMaintInvStsNm_IH, (String) map.get(AP_MAINT_INV_STS_NM));
                    ZYPEZDItemValueSetter.setValue(bizMsg.apInvNum_IH, (String) map.get(AP_INV_NUM));
                    ZYPEZDItemValueSetter.setValue(bizMsg.invDt_IH, (String) map.get(INV_DT));
                    // START 2016/09/09 K.Kojima [QC#12725,MOD]
                    // ZYPEZDItemValueSetter.setValue(bizMsg.apvrUsrNm_IH,
                    // (String) map.get(APVR_USR_NM));
                    ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_IH, (String) map.get(APVR_USR_NM));
                    // END 2016/09/09 K.Kojima [QC#12725,MOD]
                    ZYPEZDItemValueSetter.setValue(bizMsg.prntVndCd_IH, (String) map.get(PRNT_VND_CD));
                    ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm_IH, (String) map.get(PRNT_VND_NM));
                    ZYPEZDItemValueSetter.setValue(bizMsg.apVndCd_HD, (String) map.get(AP_VND_CD));
                    ZYPEZDItemValueSetter.setValue(bizMsg.vndSiteNm_IH, (String) map.get(VND_SITE_NM));
                    ZYPEZDItemValueSetter.setValue(bizMsg.apInvAmt_IH, (BigDecimal) map.get(AP_INV_AMT));
                    ZYPEZDItemValueSetter.setValue(bizMsg.apMiscAmt_IH, (BigDecimal) map.get(AP_MISC_AMT));
                    ZYPEZDItemValueSetter.setValue(bizMsg.apTaxAmt_IH, (BigDecimal) map.get(AP_TAX_AMT));
                    ZYPEZDItemValueSetter.setValue(bizMsg.lateFeeAmt_IH, (BigDecimal) map.get(LATE_FEE_AMT));
                    //START 2017/03/16 E.Kameishi [QC#14205, ADD]
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs, (String) map.get(XX_REC_HIST_CRAT_TS));
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm, ZYPRecHistUtil.getFullNameForRecHist((String) map.get(XX_REC_HIST_CRAT_BY_NM)));
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs, (String) map.get(XX_REC_HIST_UPD_TS));
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm, ZYPRecHistUtil.getFullNameForRecHist((String) map.get(XX_REC_HIST_UPD_BY_NM)));
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm, (String) map.get(XX_REC_HIST_TBL_NM));
                    //END 2017/03/16 E.Kameishi [QC#14205, ADD]
                    // Comment Info
                    ZYPEZDItemValueSetter.setValue(bizMsg.invCmntTxt_CO, (String) map.get(INV_CMNT_TXT));
                    ZYPEZDItemValueSetter.setValue(bizMsg.apAdjAmt_CO, (BigDecimal) map.get(AP_ADJ_AMT));
                    ZYPEZDItemValueSetter.setValue(bizMsg.apAdjRsnCd_CO, (String) map.get(AP_ADJ_RSN_CD));
                    // START 2016/09/29 W.Honda [Unit Test#201,ADD]
                    // Therefore Document Info
                    if (ZYPCommonFunc.hasValue(bizMsg.apMaintInvStsCd_IH)
                            && !AP_MAINT_INV_STS_CD_00.equals(bizMsg.apMaintInvStsCd_IH.getValue())) {
                        Map<String, Object> docInfoMap = NFBL1110CommonLogic.getAttDataForTherefore(glblCmpyCd, bizMsg.apInvNum_IH.getValue(), bizMsg.apVndCd_HD.getValue());

                        if (docInfoMap != null) {
                            ZYPEZDItemValueSetter.setValue(bizMsg.docMgtDocId, new BigDecimal((String) docInfoMap.get(DOC_MGT_DOC_ID)));
                            ZYPEZDItemValueSetter.setValue(bizMsg.docMgtDocId_IH, new BigDecimal((String) docInfoMap.get(DOC_MGT_DOC_ID)));
                            ZYPEZDItemValueSetter.setValue(bizMsg.docMgtCatgCd, (String) docInfoMap.get(DOC_MGT_CATG_CD));
                            ZYPEZDItemValueSetter.setValue(bizMsg.xxSrvUrlTxt, bizMsg.xxSrvUrlTxt_BK.getValue() + bizMsg.docMgtDocId.getValue().toString());
                        }
                    }
                    // END   2016/09/29 W.Honda [Unit Test#201,ADD]
                }
                // Invoice Line Info
                
                // Add 2016/08/22 QC#12830
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxGrpFlg_A1, ZYPConstant.FLG_ON_Y);

                if (!prevSerNum.equals((String)map.get(SER_NUM))) {
                    // Add 2016/08/22 QC#12830
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxGrpFlg_A1, ZYPConstant.FLG_OFF_N);

                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ezUpTime_SE, (String) map.get(CMIS_EZUPTIME));
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ezUpTimeZone_SE, (String) map.get(CMIS_EZUPTIMEZONE));
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).serNum_A1, (String) map.get(SER_NUM));
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ovrdSerNum_A1, (String) map.get(OVRD_SER_NUM));
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).startDt_A1, (String) map.get(START_DT));
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).endDt_A1, (String) map.get(END_DT));
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).baseAmt_A1, (BigDecimal) map.get(BASE_AMT));
                }
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ezUpTime_ME, (String) map.get(CMIM_EZUPTIME));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ezUpTimeZone_ME, (String) map.get(CMIM_EZUPTIMEZONE));
                // Create [Counter Type] pulldown.
                NFBL1110CommonLogic.createCntrTpPulldown(bizMsg, i);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).cntrTpCd_A1, (String) map.get(CNTR_TP_CD));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).startReadMtrCnt_A1, (BigDecimal) map.get(START_READ_MTR_CNT));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).endReadMtrCnt_A1, (BigDecimal) map.get(END_READ_MTR_CNT));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).readMtrCnt_A1, (BigDecimal) map.get(READ_MTR_CNT));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).apTolAmt_A1, (BigDecimal) map.get(AP_TOL_AMT));
                //START 2017/03/16 E.Kameishi [QC#14205, ADD]
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistCratTs_A1, (String) map.get(XX_REC_HIST_CRAT_TS_A1));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) map.get(XX_REC_HIST_CRAT_BY_NM_A1)));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistUpdTs_A1, (String) map.get(XX_REC_HIST_UPD_TS_A1));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) map.get(XX_REC_HIST_UPD_BY_NM_A1)));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistTblNm_A1, (String) map.get(XX_REC_HIST_TBL_NM_A1));
                //END 2017/03/16 E.Kameishi [QC#14205, ADD]
                prevApInvNum = (String) map.get(AP_INV_NUM);
                prevApVndCd = (String) map.get(AP_VND_CD);
                prevSerNum = (String) map.get(SER_NUM);
                intCntrTpCount++;
                indexResult++;
            } 
            // Del Start 2016/08/22 QC#12830
//            else {
//                int intRestOfRows = INT_6 - intCntrTpCount;
//                for (int j = 0; j < intRestOfRows; j++) {
//                    // Invoice Line Info
//                    bizMsg.A.no(i).ezUpTime_SE.clear();
//                    bizMsg.A.no(i).ezUpTimeZone_SE.clear();
//                    bizMsg.A.no(i).serNum_A1.clear();
//                    bizMsg.A.no(i).ovrdSerNum_A1.clear();
//                    bizMsg.A.no(i).startDt_A1.clear();
//                    bizMsg.A.no(i).endDt_A1.clear();
//                    bizMsg.A.no(i).baseAmt_A1.clear();
//                    // Set blank for Meter.
//                    bizMsg.A.no(i).ezUpTime_ME.clear();
//                    bizMsg.A.no(i).ezUpTimeZone_ME.clear();
//                    // Create [Counter Type] pulldown.
//                    NFBL1110CommonLogic.createCntrTpPulldown(bizMsg, i);
//                    bizMsg.A.no(i).cntrTpCd_A1.clear();
//                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).startReadMtrCnt_A1, BigDecimal.ZERO);
//                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).endReadMtrCnt_A1, BigDecimal.ZERO);
//                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).readMtrCnt_A1, BigDecimal.ZERO);
//                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).apTolAmt_A1, BigDecimal.ZERO);
//                    i++;
//                }
//            }
            // Del End 2016/08/22 QC#12830
            i++;
        }
    }

    /**
     * Method name: setCurrentInvIndex
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void setCurrentInvIndex(NFBL1110CMsg bizMsg) {

        for (int i = 0; i < bizMsg.Y.getValidCount(); i++) {
            if (bizMsg.apInvNum_IH.getValue().equals(bizMsg.Y.no(i).apInvNum_Y1.getValue())
            &&  bizMsg.apVndCd_HD.getValue().equals(bizMsg.Y.no(i).apVndCd_Y1.getValue())
            ) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxListNum_Y, new BigDecimal(i));
            }
        }
    }

    /**
     * Method name: keepAllInvInfoOnSMsg
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    @SuppressWarnings("unchecked")
    // START 2016/09/29 W.Honda [Unit Test#201,ADD]
//    public static void keepAllInvInfoOnSMsg(NFBL1110CMsg bizMsg, NFBL1110SMsg globalMsg) {
    public static void keepAllInvInfoOnSMsg(NFBL1110CMsg bizMsg, NFBL1110SMsg globalMsg, String glblCmpyCd) {
    // END   2016/09/29 W.Honda [Unit Test#201,ADD]

        globalMsg.A.clear();
        globalMsg.A.setValidCount(0);
        int intValidCount = 0;
        List resultList = new ArrayList();
        S21SsmEZDResult ssmResult = NFBL1110CommonLogic.searchRecordByBatchPk(bizMsg.cmMaintInvBatPk_BA.getValue());
        if (ssmResult.isCodeNormal()) {
            resultList = (List) ssmResult.getResultObject();
            int totRecCnt = getTotalRecordCount(bizMsg.cmMaintInvBatPk_BA.getValue());
            // Mod Start 2016/08/22 QC#12830
//            if ((totRecCnt / INT_6) > (globalMsg.A.length() / INT_6)) {
//                intValidCount = globalMsg.A.length() / INT_6 * INT_6;
//            } else {
            if (totRecCnt > globalMsg.A.length()) {
                intValidCount = globalMsg.A.length();
            // Mod End 2016/08/22 QC#12830
            } else {
                intValidCount = totRecCnt;
            }
            globalMsg.A.setValidCount(intValidCount);
    // START 2016/09/29 W.Honda [Unit Test#201,ADD]
//            setAllInvInfoToSMsg(bizMsg, globalMsg, resultList);
            setAllInvInfoToSMsg(bizMsg, globalMsg, resultList, glblCmpyCd);
    // END   2016/09/29 W.Honda [Unit Test#201,ADD]
        }
    }

    /**
     * Method name: setAllInvInfoToSMsg
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global Component Interface Message
     * @param resultList List
     * @param intInvIndex int
     * @param intValidCount int
     */
    @SuppressWarnings("unchecked")
    // START 2016/09/29 W.Honda [Unit Test#201,ADD]
//    public static void setAllInvInfoToSMsg(NFBL1110CMsg bizMsg, NFBL1110SMsg globalMsg, List resultList) {
    public static void setAllInvInfoToSMsg(NFBL1110CMsg bizMsg, NFBL1110SMsg globalMsg, List resultList, String glblCmpyCd) {
    // END   2016/09/29 W.Honda [Unit Test#201,ADD]

        int intInvIndex = -1;
        int intCntrTpCount = 0;
        List<Map> invList = new ArrayList<Map>();
        String prevApInvNum = EMPTY_STRING;
        String prevApVndCd = EMPTY_STRING;
        String prevSerNum = EMPTY_STRING;
        int prevSerNumIndex = -1;
        int indexResult = 0;

        for (int i = 0; i < globalMsg.A.getValidCount(); ) {
            Map map = new HashMap();
            if (resultList.size() > indexResult) {
                map = (Map) resultList.get(indexResult);
                if (!prevApInvNum.equals((String)map.get(AP_INV_NUM))
                ||  !prevApVndCd.equals((String)map.get(AP_VND_CD))) {
                    intInvIndex++;
                    Map<String, Object> invMap = new HashMap<String, Object>();
                    invMap.put(XX_LIST_NUM, new BigDecimal(intInvIndex));
                    invMap.put(AP_INV_NUM, (String)map.get(AP_INV_NUM));
                    invMap.put(AP_VND_CD, (String)map.get(AP_VND_CD));
                    invMap.put(AP_INV_AMT, (BigDecimal)map.get(AP_INV_AMT));
                    // START 2016/09/29 W.Honda [Unit Test#201,ADD]
                    // Therefore Document Info
                    if (ZYPCommonFunc.hasValue((String) map.get(AP_MAINT_INV_STS_CD))
                            && !AP_MAINT_INV_STS_CD_00.equals((String) map.get(AP_MAINT_INV_STS_CD))) {
                        Map<String, Object> docInfoMap = NFBL1110CommonLogic.getAttDataForTherefore(glblCmpyCd, (String)map.get(AP_INV_NUM), (String) map.get(AP_VND_CD));

                        if (docInfoMap != null) {
                            invMap.put(DOC_MGT_DOC_ID, new BigDecimal((String) docInfoMap.get(DOC_MGT_DOC_ID)));
                            invMap.put(DOC_MGT_CATG_CD, (String) docInfoMap.get(DOC_MGT_CATG_CD));
                        }
                    }
                    // END   2016/09/29 W.Honda [Unit Test#201,ADD]
                    invList.add(invMap);
                }
            }

            if (i != 0) {
                if (resultList.size() <= indexResult
                || !prevApInvNum.equals((String)map.get(AP_INV_NUM))
                || !prevApVndCd.equals((String)map.get(AP_VND_CD))
                || !prevSerNum.equals((String)map.get(SER_NUM))
                ) {
                    // set Previous row's information
                    // Del Start 2016/08/23 QC#12830
//                    int intRestOfRows = INT_6 - intCntrTpCount;
//                    for (int j = 0; j < intRestOfRows; j++) {
//                        // Keep invoice index
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxListNum_A1, globalMsg.A.no(prevSerNumIndex).xxListNum_A1.getValue());
//                        // Invoice Header Info (Duplicate info on SMessage)
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTime_IH, globalMsg.A.no(prevSerNumIndex).ezUpTime_IH.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTimeZone_IH, globalMsg.A.no(prevSerNumIndex).ezUpTimeZone_IH.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apDsWfStsCd_IH, globalMsg.A.no(prevSerNumIndex).apDsWfStsCd_IH.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apDsWfStsNm_IH, globalMsg.A.no(prevSerNumIndex).apDsWfStsNm_IH.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apMaintInvStsCd_IH, globalMsg.A.no(prevSerNumIndex).apMaintInvStsCd_IH.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apMaintInvStsNm_IH, globalMsg.A.no(prevSerNumIndex).apMaintInvStsNm_IH.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apInvNum_IH, globalMsg.A.no(prevSerNumIndex).apInvNum_IH.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invDt_IH, globalMsg.A.no(prevSerNumIndex).invDt_IH.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apvrUsrNm_IH, globalMsg.A.no(prevSerNumIndex).apvrUsrNm_IH.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).prntVndNm_IH, globalMsg.A.no(prevSerNumIndex).prntVndNm_IH.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apVndCd_HD, globalMsg.A.no(prevSerNumIndex).apVndCd_HD.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).vndSiteNm_IH, globalMsg.A.no(prevSerNumIndex).vndSiteNm_IH.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apInvAmt_IH, globalMsg.A.no(prevSerNumIndex).apInvAmt_IH.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apMiscAmt_IH, globalMsg.A.no(prevSerNumIndex).apMiscAmt_IH.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apTaxAmt_IH, globalMsg.A.no(prevSerNumIndex).apTaxAmt_IH.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).lateFeeAmt_IH, globalMsg.A.no(prevSerNumIndex).lateFeeAmt_IH.getValue());
//                        // Invoice Line Info
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTime_SE, globalMsg.A.no(prevSerNumIndex).ezUpTime_SE.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTimeZone_SE, globalMsg.A.no(prevSerNumIndex).ezUpTimeZone_SE.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).serNum_A1, globalMsg.A.no(prevSerNumIndex).serNum_A1.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ovrdSerNum_A1, globalMsg.A.no(prevSerNumIndex).ovrdSerNum_A1.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).startDt_A1, globalMsg.A.no(prevSerNumIndex).startDt_A1.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).endDt_A1, globalMsg.A.no(prevSerNumIndex).endDt_A1.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).baseAmt_A1, globalMsg.A.no(prevSerNumIndex).baseAmt_A1.getValue());
//                        // Set blank for Meter.
//                        globalMsg.A.no(i).ezUpTime_ME.clear();
//                        globalMsg.A.no(i).ezUpTimeZone_ME.clear();
//                        globalMsg.A.no(i).cntrTpCd_A1.clear();
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).startReadMtrCnt_A1, BigDecimal.ZERO);
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).endReadMtrCnt_A1, BigDecimal.ZERO);
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).readMtrCnt_A1, BigDecimal.ZERO);
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apTolAmt_A1, BigDecimal.ZERO);
//                        // Comment Info (Duplicate info on SMessage)
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invCmntTxt_CO, globalMsg.A.no(prevSerNumIndex).invCmntTxt_CO.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apAdjAmt_CO, globalMsg.A.no(prevSerNumIndex).apAdjAmt_CO.getValue());
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apAdjRsnCd_CO, globalMsg.A.no(prevSerNumIndex).apAdjRsnCd_CO.getValue());
//                        i++;
//                    }
                    // Del End 2016/08/23 QC#12830
                    intCntrTpCount = 0;
                }
            }

            if (resultList.size() > indexResult) {
                // Keep invoice index
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxListNum_A1, new BigDecimal(intInvIndex));
                // Invoice Header Info (Duplicate info on SMessage)
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTime_IH, (String) map.get(CMI_EZUPTIME));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTimeZone_IH, (String) map.get(CMI_EZUPTIMEZONE));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apDsWfStsCd_IH, (String) map.get(AP_DS_WF_STS_CD));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apDsWfStsNm_IH, (String) map.get(AP_DS_WF_STS_NM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apMaintInvStsCd_IH, (String) map.get(AP_MAINT_INV_STS_CD));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apMaintInvStsNm_IH, (String) map.get(AP_MAINT_INV_STS_NM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apInvNum_IH, (String) map.get(AP_INV_NUM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invDt_IH, (String) map.get(INV_DT));
                // START 2016/09/09 K.Kojima [QC#12725,MOD]
                // ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apvrUsrNm_IH,
                // (String) map.get(APVR_USR_NM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).varCharConstVal_IH, (String) map.get(APVR_USR_NM));
                // END 2016/09/09 K.Kojima [QC#12725,MOD]
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).prntVndCd_IH, (String) map.get(PRNT_VND_CD));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).prntVndNm_IH, (String) map.get(PRNT_VND_NM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apVndCd_HD, (String) map.get(AP_VND_CD));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).vndSiteNm_IH, (String) map.get(VND_SITE_NM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apInvAmt_IH, (BigDecimal) map.get(AP_INV_AMT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apMiscAmt_IH, (BigDecimal) map.get(AP_MISC_AMT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apTaxAmt_IH, (BigDecimal) map.get(AP_TAX_AMT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).lateFeeAmt_IH, (BigDecimal) map.get(LATE_FEE_AMT));
                // Invoice Line Info
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTime_SE, (String) map.get(CMIS_EZUPTIME));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTimeZone_SE, (String) map.get(CMIS_EZUPTIMEZONE));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).serNum_A1, (String) map.get(SER_NUM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ovrdSerNum_A1, (String) map.get(OVRD_SER_NUM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).startDt_A1, (String) map.get(START_DT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).endDt_A1, (String) map.get(END_DT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).baseAmt_A1, (BigDecimal) map.get(BASE_AMT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTime_ME, (String) map.get(CMIM_EZUPTIME));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTimeZone_ME, (String) map.get(CMIM_EZUPTIMEZONE));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).cntrTpCd_A1, (String) map.get(CNTR_TP_CD));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).startReadMtrCnt_A1, (BigDecimal) map.get(START_READ_MTR_CNT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).endReadMtrCnt_A1, (BigDecimal) map.get(END_READ_MTR_CNT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).readMtrCnt_A1, (BigDecimal) map.get(READ_MTR_CNT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apTolAmt_A1, (BigDecimal) map.get(AP_TOL_AMT));
                // Add 2016/08/23QC#12830
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxGrpFlg_A1, ZYPConstant.FLG_ON_Y);
                
                // Comment Info (Duplicate info on SMessage)
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invCmntTxt_CO, (String) map.get(INV_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apAdjAmt_CO, (BigDecimal) map.get(AP_ADJ_AMT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apAdjRsnCd_CO, (String) map.get(AP_ADJ_RSN_CD));
                prevApInvNum = (String) map.get(AP_INV_NUM);
                prevApVndCd = (String) map.get(AP_VND_CD);
                prevSerNum = (String) map.get(SER_NUM);
                if (intCntrTpCount == 0) {
                    // Keep index for previous serial number.
                    prevSerNumIndex = i;

                    // Add 2016/08/23QC#12830
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxGrpFlg_A1, ZYPConstant.FLG_OFF_N);
                    
                }
                intCntrTpCount++;
                i++;
                indexResult++;
            }
        }
        saveInvList(bizMsg, invList);
    }

    /**
     * Method name: setOneInvInfoToSMsg
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global Component Interface Message
     * @param resultList List
     * @param intInvIndex int
     * @param intValidCount int
     */
    @SuppressWarnings("unchecked")
    public static void setOneInvInfoToSMsg(NFBL1110CMsg bizMsg, NFBL1110SMsg globalMsg, List resultList, int intInvIndex, int intValidCount) {

        int currentIndex = globalMsg.A.getValidCount();

        for (int i = 0; i < intValidCount; i++) {
            Map map = (Map) resultList.get(i);
            // Keep invoice index
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).xxListNum_A1, new BigDecimal(intInvIndex));
            // Invoice Header Info (Duplicate info on SMessage)
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).ezUpTime_IH, (String) map.get(CMI_EZUPTIME));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).ezUpTimeZone_IH, (String) map.get(CMI_EZUPTIMEZONE));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).apDsWfStsCd_IH, (String) map.get(AP_DS_WF_STS_CD));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).apDsWfStsNm_IH, (String) map.get(AP_DS_WF_STS_NM));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).apMaintInvStsCd_IH, (String) map.get(AP_MAINT_INV_STS_CD));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).apMaintInvStsNm_IH, (String) map.get(AP_MAINT_INV_STS_NM));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).apInvNum_IH, (String) map.get(AP_INV_NUM));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).invDt_IH, (String) map.get(INV_DT));
            // START 2016/09/09 K.Kojima [QC#12725,MOD]
            // ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).apvrUsrNm_IH,
            // (String) map.get(APVR_USR_NM));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).varCharConstVal_IH, (String) map.get(APVR_USR_NM));
            // END 2016/09/09 K.Kojima [QC#12725,MOD]
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).prntVndCd_IH, (String) map.get(PRNT_VND_CD));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).prntVndNm_IH, (String) map.get(PRNT_VND_NM));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).apVndCd_HD, (String) map.get(AP_VND_CD));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).vndSiteNm_IH, (String) map.get(VND_SITE_NM));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).apInvAmt_IH, (BigDecimal) map.get(AP_INV_AMT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).apMiscAmt_IH, (BigDecimal) map.get(AP_MISC_AMT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).apTaxAmt_IH, (BigDecimal) map.get(AP_TAX_AMT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).lateFeeAmt_IH, (BigDecimal) map.get(LATE_FEE_AMT));
            // Invoice Line Info
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).ezUpTime_SE, (String) map.get(CMIS_EZUPTIME));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).ezUpTimeZone_SE, (String) map.get(CMIS_EZUPTIMEZONE));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).serNum_A1, (String) map.get(SER_NUM));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).ovrdSerNum_A1, (String) map.get(OVRD_SER_NUM));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).startDt_A1, (String) map.get(START_DT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).endDt_A1, (String) map.get(END_DT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).baseAmt_A1, (BigDecimal) map.get(BASE_AMT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).ezUpTime_ME, (String) map.get(CMIM_EZUPTIME));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).ezUpTimeZone_ME, (String) map.get(CMIM_EZUPTIMEZONE));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).cntrTpCd_A1, (String) map.get(CNTR_TP_CD));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).startReadMtrCnt_A1, (BigDecimal) map.get(START_READ_MTR_CNT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).endReadMtrCnt_A1, (BigDecimal) map.get(END_READ_MTR_CNT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).readMtrCnt_A1, (BigDecimal) map.get(READ_MTR_CNT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).apTolAmt_A1, (BigDecimal) map.get(AP_TOL_AMT));
            // Comment Info (Duplicate info on SMessage)
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).invCmntTxt_CO, (String) map.get(INV_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).apAdjAmt_CO, (BigDecimal) map.get(AP_ADJ_AMT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(currentIndex).apAdjRsnCd_CO, (String) map.get(AP_ADJ_RSN_CD));
            currentIndex++;
        }
        globalMsg.A.setValidCount(currentIndex);
    }

    /**
     * Method name: keepCurrentInvInfo
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    @SuppressWarnings("unchecked")
    public static boolean keepCurrentInvInfo(NFBL1110CMsg bizMsg, NFBL1110SMsg globalMsg, String glblCmpyCd) {

        // START 2016/08/04 J.Kim [QC#12712,ADD]
        if (!checkInvDt(bizMsg, glblCmpyCd)) {
            return false;
        }
        // END 2016/08/04 J.Kim [QC#12712,ADD]

        if (bizMsg.A.getValidCount() > 0) {
            // Check Supplier existence before save invoice information
            if (!NFBL1110CommonLogic.checkPrntVnd(bizMsg)) {
                bizMsg.prntVndCd_IH.setErrorInfo(1, NFBM0041E, new String[] {"Supplier" });
                return false;
            }
            // Check Site Name existence before save invoice information
            if (!NFBL1110CommonLogic.checkLocNm(bizMsg)) {
                // START 2017/12/22 [QC#22831, MOD]
                bizMsg.vndSiteNm_IH.setErrorInfo(1, NFBM0041E, new String[] {"Supplier Site Name" });
                // END   2017/12/22 [QC#22831, MOD]
                return false;
            }
            // Add Start 2016/08/22 S21_NA#12947
            // Check Site Code
            if (!ZYPCommonFunc.hasValue(bizMsg.apVndCd_HD)) {
                if (!getApVndCd(bizMsg)) {
                    bizMsg.vndSiteNm_IH.setErrorInfo(1, NFBM0237E);
                    bizMsg.prntVndCd_IH.setErrorInfo(1, NFBM0237E);
                    return false;
                }
            }
            // Add End 2016/08/22 S21_NA#12947
        }
        //  Check Serial# existence before save invoice information
        // Add Start 2016/08/04 QC#12692
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).serNum_A1) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).ovrdSerNum_A1.getValue())) {
                if (!NFBL1110CommonLogic.checkSerial(bizMsg, bizMsg.A.no(i).serNum_A1.getValue(), glblCmpyCd)) {
                    bizMsg.A.no(i).serNum_A1.setErrorInfo(1, NFBM0228E);
                    return false;
                }
            }
        }
        // Add End 2016/08/04 QC#12692    
        
        if (bizMsg.invFlg_NE.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            // New Invoice
            int totalCountAfterSave = globalMsg.A.getValidCount() + bizMsg.A.getValidCount(); 
            if (totalCountAfterSave > globalMsg.A.length()) {
                bizMsg.setMessageInfo(NWAM0222E, new String[] {"Invoice" });
                return false;
            }
            int indexSMsg = globalMsg.A.getValidCount();

            String prevEzUpTime_SE = EMPTY_STRING;
            String prevEzUpTimeZone_SE = EMPTY_STRING;
            String prevSerNum_A1 = EMPTY_STRING;
            String prevOvrdSerNum_A1 = EMPTY_STRING;
            String prevStartDt_A1 = EMPTY_STRING;
            String prevEndDt_A1 = EMPTY_STRING;
            BigDecimal prevBaseAmt_A1 = BigDecimal.ZERO;

            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (i == 0) {
                    // Add new invoice
                    ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(bizMsg.Y.getValidCount()).apInvNum_Y1, bizMsg.apInvNum_IH.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(bizMsg.Y.getValidCount()).apVndCd_Y1, bizMsg.apVndCd_HD.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(bizMsg.Y.getValidCount()).apInvAmt_Y1, bizMsg.apInvAmt_IH.getValue());
                    // START 2016/09/29 W.Honda [Unit Test#201,ADD]
                    ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(bizMsg.Y.getValidCount()).docMgtDocId_Y1, bizMsg.docMgtDocId.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(bizMsg.Y.getValidCount()).docMgtCatgCd_Y1, bizMsg.docMgtCatgCd.getValue());
                    // END   2016/09/29 W.Honda [Unit Test#201,ADD]
                    bizMsg.Y.setValidCount(bizMsg.Y.getValidCount()+1);
                }
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).xxListNum_A1, new BigDecimal(bizMsg.Y.getValidCount()-1));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).ezUpTime_IH, bizMsg.ezUpTime_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).ezUpTimeZone_IH, bizMsg.ezUpTimeZone_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).apDsWfStsCd_IH, bizMsg.apDsWfStsCd_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).apDsWfStsNm_IH, bizMsg.apDsWfStsNm_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).apMaintInvStsCd_IH, bizMsg.apMaintInvStsCd_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).apMaintInvStsNm_IH, bizMsg.apMaintInvStsNm_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).apInvNum_IH, bizMsg.apInvNum_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).invDt_IH, bizMsg.invDt_IH.getValue());
                // START 2016/09/09 K.Kojima [QC#12725,MOD]
                // ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).apvrUsrNm_IH,
                // bizMsg.apvrUsrNm_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).varCharConstVal_IH, bizMsg.varCharConstVal_IH.getValue());
                // END 2016/09/09 K.Kojima [QC#12725,MOD]
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).prntVndCd_IH, bizMsg.prntVndCd_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).prntVndNm_IH, bizMsg.prntVndNm_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).apVndCd_HD, bizMsg.apVndCd_HD.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).vndSiteNm_IH, bizMsg.vndSiteNm_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).apInvAmt_IH, bizMsg.apInvAmt_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).apMiscAmt_IH, bizMsg.apMiscAmt_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).apTaxAmt_IH, bizMsg.apTaxAmt_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).lateFeeAmt_IH, bizMsg.lateFeeAmt_IH.getValue());
                // Mod Start 2016/08/22 QC#12830
                // int remainder = i % INT_6;
                // if (remainder == 0) {
                if (ZYPConstant.FLG_OFF_N.equals(bizMsg.A.no(i).xxGrpFlg_A1.getValue())) {
                    // Mod End 2016/08/22 QC#12830
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).ezUpTime_SE, bizMsg.A.no(i).ezUpTime_SE.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).ezUpTimeZone_SE, bizMsg.A.no(i).ezUpTimeZone_SE.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).serNum_A1, bizMsg.A.no(i).serNum_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).ovrdSerNum_A1, bizMsg.A.no(i).ovrdSerNum_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).startDt_A1, bizMsg.A.no(i).startDt_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).endDt_A1, bizMsg.A.no(i).endDt_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).baseAmt_A1, bizMsg.A.no(i).baseAmt_A1.getValue());
                    // Keep serial value for next line.
                    prevEzUpTime_SE = bizMsg.A.no(i).ezUpTime_SE.getValue();
                    prevEzUpTimeZone_SE = bizMsg.A.no(i).ezUpTimeZone_SE.getValue();
                    prevSerNum_A1 = bizMsg.A.no(i).serNum_A1.getValue();
                    prevOvrdSerNum_A1 = bizMsg.A.no(i).ovrdSerNum_A1.getValue();
                    prevStartDt_A1 = bizMsg.A.no(i).startDt_A1.getValue();
                    prevEndDt_A1 = bizMsg.A.no(i).endDt_A1.getValue();
                    prevBaseAmt_A1 = bizMsg.A.no(i).baseAmt_A1.getValue();
                } else {
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).ezUpTime_SE, prevEzUpTime_SE);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).ezUpTimeZone_SE, prevEzUpTimeZone_SE);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).serNum_A1, prevSerNum_A1);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).ovrdSerNum_A1, prevOvrdSerNum_A1);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).startDt_A1, prevStartDt_A1);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).endDt_A1, prevEndDt_A1);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).baseAmt_A1, prevBaseAmt_A1);
                }

                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).ezUpTime_ME, bizMsg.A.no(i).ezUpTime_ME.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).ezUpTimeZone_ME, bizMsg.A.no(i).ezUpTimeZone_ME.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).cntrTpCd_A1, bizMsg.A.no(i).cntrTpCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).startReadMtrCnt_A1, bizMsg.A.no(i).startReadMtrCnt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).endReadMtrCnt_A1, bizMsg.A.no(i).endReadMtrCnt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).readMtrCnt_A1, bizMsg.A.no(i).readMtrCnt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).apTolAmt_A1, bizMsg.A.no(i).apTolAmt_A1.getValue());

                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).invCmntTxt_CO, bizMsg.invCmntTxt_CO.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).apAdjAmt_CO, bizMsg.apAdjAmt_CO.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).apAdjRsnCd_CO, bizMsg.apAdjRsnCd_CO.getValue());
                // Add 2016/08/22 QC#12830
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(indexSMsg).xxGrpFlg_A1, bizMsg.A.no(i).xxGrpFlg_A1);

                indexSMsg++;
            }
            globalMsg.A.setValidCount(indexSMsg);
        } else {
            // Existing Invoice
            List<Map> listBeforeCurrentInvInfo = new ArrayList<Map>();
            List<Map> listAfterCurrentInvInfo = new ArrayList<Map>();
            int currentInvIndex = bizMsg.xxListNum_Y.getValue().intValue();

            // Update invoice list
            ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(currentInvIndex).apInvNum_Y1, bizMsg.apInvNum_IH.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(currentInvIndex).apVndCd_Y1, bizMsg.apVndCd_HD.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(currentInvIndex).apInvAmt_Y1, bizMsg.apInvAmt_IH.getValue());
            // START 2016/09/29 W.Honda [Unit Test#201,ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(currentInvIndex).docMgtDocId_Y1, bizMsg.docMgtDocId.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(currentInvIndex).docMgtCatgCd_Y1, bizMsg.docMgtCatgCd.getValue());
            // END   2016/09/29 W.Honda [Unit Test#201,ADD]

            // Save invoice information except current invoice to list
            boolean beforeCompleteFlag = false;
            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (globalMsg.A.no(i).xxListNum_A1.getValueInt() == currentInvIndex) {
                    beforeCompleteFlag = true;
                } else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(XX_LIST_NUM, globalMsg.A.no(i).xxListNum_A1.getValue());
                    map.put(CMI_EZUPTIME, globalMsg.A.no(i).ezUpTime_IH.getValue());
                    map.put(CMI_EZUPTIMEZONE, globalMsg.A.no(i).ezUpTimeZone_IH.getValue());
                    map.put(AP_DS_WF_STS_CD, globalMsg.A.no(i).apDsWfStsCd_IH.getValue());
                    map.put(AP_DS_WF_STS_NM, globalMsg.A.no(i).apDsWfStsNm_IH.getValue());
                    map.put(AP_MAINT_INV_STS_CD, globalMsg.A.no(i).apMaintInvStsCd_IH.getValue());
                    map.put(AP_MAINT_INV_STS_NM, globalMsg.A.no(i).apMaintInvStsNm_IH.getValue());
                    map.put(AP_INV_NUM, globalMsg.A.no(i).apInvNum_IH.getValue());
                    map.put(INV_DT, globalMsg.A.no(i).invDt_IH.getValue());
                    // START 2016/09/09 K.Kojima [QC#12725,MOD]
                    // map.put(APVR_USR_NM,
                    // globalMsg.A.no(i).apvrUsrNm_IH.getValue());
                    map.put(APVR_USR_NM, globalMsg.A.no(i).varCharConstVal_IH.getValue());
                    // END 2016/09/09 K.Kojima [QC#12725,MOD]
                    map.put(PRNT_VND_CD, globalMsg.A.no(i).prntVndCd_IH.getValue());
                    map.put(PRNT_VND_NM, globalMsg.A.no(i).prntVndNm_IH.getValue());
                    map.put(AP_VND_CD, globalMsg.A.no(i).apVndCd_HD.getValue());
                    map.put(VND_SITE_NM, globalMsg.A.no(i).vndSiteNm_IH.getValue());
                    map.put(AP_INV_AMT, globalMsg.A.no(i).apInvAmt_IH.getValue());
                    map.put(AP_MISC_AMT, globalMsg.A.no(i).apMiscAmt_IH.getValue());
                    map.put(AP_TAX_AMT, globalMsg.A.no(i).apTaxAmt_IH.getValue());
                    map.put(LATE_FEE_AMT, globalMsg.A.no(i).lateFeeAmt_IH.getValue());
                    map.put(CMIS_EZUPTIME, globalMsg.A.no(i).ezUpTime_SE.getValue());
                    map.put(CMIS_EZUPTIMEZONE, globalMsg.A.no(i).ezUpTimeZone_SE.getValue());
                    map.put(SER_NUM, globalMsg.A.no(i).serNum_A1.getValue());
                    map.put(OVRD_SER_NUM, globalMsg.A.no(i).ovrdSerNum_A1.getValue());
                    map.put(START_DT, globalMsg.A.no(i).startDt_A1.getValue());
                    map.put(END_DT, globalMsg.A.no(i).endDt_A1.getValue());
                    map.put(BASE_AMT, globalMsg.A.no(i).baseAmt_A1.getValue());
                    map.put(CMIM_EZUPTIME, globalMsg.A.no(i).ezUpTime_ME.getValue());
                    map.put(CMIM_EZUPTIMEZONE, globalMsg.A.no(i).ezUpTimeZone_ME.getValue());
                    map.put(CNTR_TP_CD, globalMsg.A.no(i).cntrTpCd_A1.getValue());
                    map.put(START_READ_MTR_CNT, globalMsg.A.no(i).startReadMtrCnt_A1.getValue());
                    map.put(END_READ_MTR_CNT, globalMsg.A.no(i).endReadMtrCnt_A1.getValue());
                    map.put(READ_MTR_CNT, globalMsg.A.no(i).readMtrCnt_A1.getValue());
                    map.put(AP_TOL_AMT, globalMsg.A.no(i).apTolAmt_A1.getValue());
                    map.put(INV_CMNT_TXT, globalMsg.A.no(i).invCmntTxt_CO.getValue());
                    map.put(AP_ADJ_AMT, globalMsg.A.no(i).apAdjAmt_CO.getValue());
                    map.put(AP_ADJ_RSN_CD, globalMsg.A.no(i).apAdjRsnCd_CO.getValue());
                    // Add 2016/08/22 QC#12830
                    map.put("XX_GRP_FLG", globalMsg.A.no(i).xxGrpFlg_A1.getValue());

                    if (!beforeCompleteFlag) {
                        listBeforeCurrentInvInfo.add(map);
                    } else {
                        listAfterCurrentInvInfo.add(map);
                    }
                }
            }
            
            int totalCountAfterSave = listBeforeCurrentInvInfo.size() + 
                                       listAfterCurrentInvInfo.size() + 
                                       bizMsg.A.getValidCount();
            if (totalCountAfterSave > globalMsg.A.length()) {
                bizMsg.setMessageInfo(NWAM0222E, new String[] {"Invoice" });
                return false;
            }

            globalMsg.A.clear();
            globalMsg.A.setValidCount(0);

            int i = 0;
            // Return invoice information before current invoice from list to SMessage
            for (; i < listBeforeCurrentInvInfo.size(); ) {
                Map map = (Map) listBeforeCurrentInvInfo.get(i);
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxListNum_A1, (BigDecimal)map.get(XX_LIST_NUM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTime_IH, (String)map.get(CMI_EZUPTIME));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTimeZone_IH, (String)map.get(CMI_EZUPTIMEZONE));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apDsWfStsCd_IH, (String)map.get(AP_DS_WF_STS_CD));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apDsWfStsNm_IH, (String)map.get(AP_DS_WF_STS_NM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apMaintInvStsCd_IH, (String)map.get(AP_MAINT_INV_STS_CD));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apMaintInvStsNm_IH, (String)map.get(AP_MAINT_INV_STS_NM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apInvNum_IH, (String)map.get(AP_INV_NUM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invDt_IH, (String)map.get(INV_DT));
                // START 2016/09/09 K.Kojima [QC#12725,MOD]
                // ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apvrUsrNm_IH,
                // (String)map.get(APVR_USR_NM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).varCharConstVal_IH, (String) map.get(APVR_USR_NM));
                // END 2016/09/09 K.Kojima [QC#12725,MOD]
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).prntVndCd_IH, (String)map.get(PRNT_VND_CD));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).prntVndNm_IH, (String)map.get(PRNT_VND_NM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apVndCd_HD, (String)map.get(AP_VND_CD));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).vndSiteNm_IH, (String)map.get(VND_SITE_NM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apInvAmt_IH, (BigDecimal)map.get(AP_INV_AMT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apMiscAmt_IH, (BigDecimal)map.get(AP_MISC_AMT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apTaxAmt_IH, (BigDecimal)map.get(AP_TAX_AMT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).lateFeeAmt_IH, (BigDecimal)map.get(LATE_FEE_AMT));

                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTime_SE, (String)map.get(CMIS_EZUPTIME));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTimeZone_SE, (String)map.get(CMIS_EZUPTIMEZONE));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).serNum_A1, (String)map.get(SER_NUM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ovrdSerNum_A1, (String)map.get(OVRD_SER_NUM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).startDt_A1, (String)map.get(START_DT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).endDt_A1, (String)map.get(END_DT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).baseAmt_A1, (BigDecimal)map.get(BASE_AMT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTime_ME, (String)map.get(CMIM_EZUPTIME));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTimeZone_ME, (String)map.get(CMIM_EZUPTIMEZONE));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).cntrTpCd_A1, (String)map.get(CNTR_TP_CD));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).startReadMtrCnt_A1, (BigDecimal)map.get(START_READ_MTR_CNT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).endReadMtrCnt_A1, (BigDecimal)map.get(END_READ_MTR_CNT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).readMtrCnt_A1, (BigDecimal)map.get(READ_MTR_CNT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apTolAmt_A1, (BigDecimal)map.get(AP_TOL_AMT));
                // Add Start 2016/08/23 QC#12830
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxGrpFlg_A1, (String)map.get("XX_GRP_FLG"));

                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invCmntTxt_CO, (String)map.get(INV_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apAdjAmt_CO, (BigDecimal)map.get(AP_ADJ_AMT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apAdjRsnCd_CO, (String)map.get(AP_ADJ_RSN_CD));

                i++;
            }
            // Save Current invoice information to SMessage
            String prevEzUpTime_SE = EMPTY_STRING;
            String prevEzUpTimeZone_SE = EMPTY_STRING;
            String prevSerNum_A1 = EMPTY_STRING;
            String prevOvrdSerNum_A1 = EMPTY_STRING;
            String prevStartDt_A1 = EMPTY_STRING;
            String prevEndDt_A1 = EMPTY_STRING;
            BigDecimal prevBaseAmt_A1 = BigDecimal.ZERO;
            for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxListNum_A1, bizMsg.xxListNum_Y.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTime_IH, bizMsg.ezUpTime_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTimeZone_IH, bizMsg.ezUpTimeZone_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apDsWfStsCd_IH, bizMsg.apDsWfStsCd_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apDsWfStsNm_IH, bizMsg.apDsWfStsNm_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apMaintInvStsCd_IH, bizMsg.apMaintInvStsCd_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apMaintInvStsNm_IH, bizMsg.apMaintInvStsNm_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apInvNum_IH, bizMsg.apInvNum_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invDt_IH, bizMsg.invDt_IH.getValue());
                // START 2016/09/09 K.Kojima [QC#12725,MOD]
                // ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apvrUsrNm_IH,
                // bizMsg.apvrUsrNm_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).varCharConstVal_IH, bizMsg.varCharConstVal_IH.getValue());
                // END 2016/09/09 K.Kojima [QC#12725,MOD]
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).prntVndCd_IH, bizMsg.prntVndCd_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).prntVndNm_IH, bizMsg.prntVndNm_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apVndCd_HD, bizMsg.apVndCd_HD.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).vndSiteNm_IH, bizMsg.vndSiteNm_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apInvAmt_IH, bizMsg.apInvAmt_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apMiscAmt_IH, bizMsg.apMiscAmt_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apTaxAmt_IH, bizMsg.apTaxAmt_IH.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).lateFeeAmt_IH, bizMsg.lateFeeAmt_IH.getValue());

                // Mod Start 2016/08/22 QC#12830
                // int remainder = i % INT_6;
                // if (remainder == 0) {
                if (ZYPConstant.FLG_OFF_N.equals(bizMsg.A.no(j).xxGrpFlg_A1.getValue())) {
                    // Mod End 2016/08/22 QC#12830
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTime_SE, bizMsg.A.no(j).ezUpTime_SE.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTimeZone_SE, bizMsg.A.no(j).ezUpTimeZone_SE.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).serNum_A1, bizMsg.A.no(j).serNum_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ovrdSerNum_A1, bizMsg.A.no(j).ovrdSerNum_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).startDt_A1, bizMsg.A.no(j).startDt_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).endDt_A1, bizMsg.A.no(j).endDt_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).baseAmt_A1, bizMsg.A.no(j).baseAmt_A1.getValue());
                    // Keep serial value for next line.
                    prevEzUpTime_SE = bizMsg.A.no(j).ezUpTime_SE.getValue();
                    prevEzUpTimeZone_SE = bizMsg.A.no(j).ezUpTimeZone_SE.getValue();
                    prevSerNum_A1 = bizMsg.A.no(j).serNum_A1.getValue();
                    prevOvrdSerNum_A1 = bizMsg.A.no(j).ovrdSerNum_A1.getValue();
                    prevStartDt_A1 = bizMsg.A.no(j).startDt_A1.getValue();
                    prevEndDt_A1 = bizMsg.A.no(j).endDt_A1.getValue();
                    prevBaseAmt_A1 = bizMsg.A.no(j).baseAmt_A1.getValue();
                } else {
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTime_SE, prevEzUpTime_SE);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTimeZone_SE, prevEzUpTimeZone_SE);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).serNum_A1, prevSerNum_A1);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ovrdSerNum_A1, prevOvrdSerNum_A1);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).startDt_A1, prevStartDt_A1);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).endDt_A1, prevEndDt_A1);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).baseAmt_A1, prevBaseAmt_A1);
                }
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTime_ME, bizMsg.A.no(j).ezUpTime_ME.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTimeZone_ME, bizMsg.A.no(j).ezUpTimeZone_ME.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).cntrTpCd_A1, bizMsg.A.no(j).cntrTpCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).startReadMtrCnt_A1, bizMsg.A.no(j).startReadMtrCnt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).endReadMtrCnt_A1, bizMsg.A.no(j).endReadMtrCnt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).readMtrCnt_A1, bizMsg.A.no(j).readMtrCnt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apTolAmt_A1, bizMsg.A.no(j).apTolAmt_A1.getValue());
                // Add 2016/08/22 QC#12830
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxGrpFlg_A1, bizMsg.A.no(j).xxGrpFlg_A1);

                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invCmntTxt_CO, bizMsg.invCmntTxt_CO.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apAdjAmt_CO, bizMsg.apAdjAmt_CO.getValue());
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apAdjRsnCd_CO, bizMsg.apAdjRsnCd_CO.getValue());

                i++;
            }
            int currentCnt = i;
            int listIndex = 0;
            // Return invoice information after current invoice from list to SMessage
            for (; i < listAfterCurrentInvInfo.size() + currentCnt; ) {
                Map map = (Map) listAfterCurrentInvInfo.get(listIndex);
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxListNum_A1, (BigDecimal)map.get(XX_LIST_NUM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTime_IH, (String)map.get(CMI_EZUPTIME));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTimeZone_IH, (String)map.get(CMI_EZUPTIMEZONE));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apDsWfStsCd_IH, (String)map.get(AP_DS_WF_STS_CD));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apDsWfStsNm_IH, (String)map.get(AP_DS_WF_STS_NM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apMaintInvStsCd_IH, (String)map.get(AP_MAINT_INV_STS_CD));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apMaintInvStsNm_IH, (String)map.get(AP_MAINT_INV_STS_NM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apInvNum_IH, (String)map.get(AP_INV_NUM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invDt_IH, (String)map.get(INV_DT));
                // START 2016/09/09 K.Kojima [QC#12725,MOD]
                // ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apvrUsrNm_IH,
                // (String)map.get(APVR_USR_NM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).varCharConstVal_IH, (String) map.get(APVR_USR_NM));
                // END 2016/09/09 K.Kojima [QC#12725,MOD]
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).prntVndCd_IH, (String)map.get(PRNT_VND_CD));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).prntVndNm_IH, (String)map.get(PRNT_VND_NM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apVndCd_HD, (String)map.get(AP_VND_CD));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).vndSiteNm_IH, (String)map.get(VND_SITE_NM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apInvAmt_IH, (BigDecimal)map.get(AP_INV_AMT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apMiscAmt_IH, (BigDecimal)map.get(AP_MISC_AMT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apTaxAmt_IH, (BigDecimal)map.get(AP_TAX_AMT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).lateFeeAmt_IH, (BigDecimal)map.get(LATE_FEE_AMT));

                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTime_SE, (String)map.get(CMIS_EZUPTIME));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTimeZone_SE, (String)map.get(CMIS_EZUPTIMEZONE));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).serNum_A1, (String)map.get(SER_NUM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ovrdSerNum_A1, (String)map.get(OVRD_SER_NUM));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).startDt_A1, (String)map.get(START_DT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).endDt_A1, (String)map.get(END_DT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).baseAmt_A1, (BigDecimal)map.get(BASE_AMT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTime_ME, (String)map.get(CMIM_EZUPTIME));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ezUpTimeZone_ME, (String)map.get(CMIM_EZUPTIMEZONE));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).cntrTpCd_A1, (String)map.get(CNTR_TP_CD));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).startReadMtrCnt_A1, (BigDecimal)map.get(START_READ_MTR_CNT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).endReadMtrCnt_A1, (BigDecimal)map.get(END_READ_MTR_CNT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).readMtrCnt_A1, (BigDecimal)map.get(READ_MTR_CNT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apTolAmt_A1, (BigDecimal)map.get(AP_TOL_AMT));
                // Add Start 2016/08/23 QC#12830
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxGrpFlg_A1, (String)map.get("XX_GRP_FLG"));

                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invCmntTxt_CO, (String)map.get(INV_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apAdjAmt_CO, (BigDecimal)map.get(AP_ADJ_AMT));
                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).apAdjRsnCd_CO, (String)map.get(AP_ADJ_RSN_CD));

                i++;
                listIndex++;
            }
            globalMsg.A.setValidCount(i);
        }
        updateRunningValue(bizMsg);
        return true;
    }

    // START 2016/08/04 J.Kim [QC#12712,ADD]
    private static boolean checkInvDt(NFBL1110CMsg bizMsg, String glblCmpyCd) {

        boolean rtnVal = true;
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (ZYPCommonFunc.hasValue(bizMsg.invDt_IH) && ZYPCommonFunc.hasValue(slsDt) && ZYPDateUtil.compare(bizMsg.invDt_IH.getValue(), slsDt) > 0) {
            bizMsg.invDt_IH.setErrorInfo(1, NFBM0106E);
            rtnVal = false;
        }
        return rtnVal;
    }
    // END 2016/08/04 J.Kim [QC#12712,ADD]

    /**
     * Method name: checkRunningAmount
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global Area Information
     */
    public static boolean checkRunningAmount(NFBL1110CMsg bizMsg, NFBL1110SMsg globalMsg) {
        if (bizMsg.apRunTotAmt_BA.getValue().compareTo(bizMsg.apCtrlAmt_BA.getValue()) == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Method name: checkRunningCount
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global Area Information
     */
    public static boolean checkRunningCount(NFBL1110CMsg bizMsg, NFBL1110SMsg globalMsg) {
        if (bizMsg.apRunTotCnt_BA.getValue().compareTo(bizMsg.apCtrlCnt_BA.getValue()) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method name: setInvInfoFromSMsg
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global Component Interface Message
     * @param intInvIndex int 
     */
    public static boolean setInvInfoFromSMsg (NFBL1110CMsg bizMsg, NFBL1110SMsg globalMsg, int intInvIndex) {
        // Start index for the specified invoice
        boolean foundStartIndex = false;
        int intScreenLineIndex = 0;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (foundStartIndex && globalMsg.A.no(i).xxListNum_A1.getValueInt() != intInvIndex) {
                break;
            }
            if (!foundStartIndex && globalMsg.A.no(i).xxListNum_A1.getValueInt() == intInvIndex) {
                foundStartIndex = true;

                // Header Info
                ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_IH, globalMsg.A.no(i).ezUpTime_IH.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_IH, globalMsg.A.no(i).ezUpTimeZone_IH.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.apDsWfStsCd_IH, globalMsg.A.no(i).apDsWfStsCd_IH.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.apDsWfStsNm_IH, globalMsg.A.no(i).apDsWfStsNm_IH.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.apMaintInvStsCd_IH, globalMsg.A.no(i).apMaintInvStsCd_IH.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.apMaintInvStsNm_IH, globalMsg.A.no(i).apMaintInvStsNm_IH.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.apInvNum_IH, globalMsg.A.no(i).apInvNum_IH.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.invDt_IH, globalMsg.A.no(i).invDt_IH.getValue());
                // START 2016/09/09 K.Kojima [QC#12725,MOD]
                // ZYPEZDItemValueSetter.setValue(bizMsg.apvrUsrNm_IH,
                // globalMsg.A.no(i).apvrUsrNm_IH.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_IH, globalMsg.A.no(i).varCharConstVal_IH.getValue());
                // END 2016/09/09 K.Kojima [QC#12725,MOD]
                ZYPEZDItemValueSetter.setValue(bizMsg.prntVndCd_IH, globalMsg.A.no(i).prntVndCd_IH.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm_IH, globalMsg.A.no(i).prntVndNm_IH.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.apVndCd_HD, globalMsg.A.no(i).apVndCd_HD.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.vndSiteNm_IH, globalMsg.A.no(i).vndSiteNm_IH.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.apInvAmt_IH, globalMsg.A.no(i).apInvAmt_IH.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.apMiscAmt_IH, globalMsg.A.no(i).apMiscAmt_IH.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.apTaxAmt_IH, globalMsg.A.no(i).apTaxAmt_IH.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.lateFeeAmt_IH, globalMsg.A.no(i).lateFeeAmt_IH.getValue());
                // START 2016/09/29 W.Honda [Unit Test#201,ADD]
                ZYPEZDItemValueSetter.setValue(bizMsg.docMgtDocId, bizMsg.Y.no(intInvIndex).docMgtDocId_Y1);
                ZYPEZDItemValueSetter.setValue(bizMsg.docMgtCatgCd, bizMsg.Y.no(intInvIndex).docMgtCatgCd_Y1);
                if (ZYPCommonFunc.hasValue(bizMsg.apMaintInvStsCd_IH)
                        && !AP_MAINT_INV_STS_CD_00.equals(bizMsg.apMaintInvStsCd_IH.getValue())) {

                    ZYPEZDItemValueSetter.setValue(bizMsg.docMgtDocId_IH, bizMsg.Y.no(intInvIndex).docMgtDocId_Y1);
                    if (ZYPCommonFunc.hasValue(bizMsg.Y.no(intInvIndex).docMgtDocId_Y1)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxSrvUrlTxt, bizMsg.xxSrvUrlTxt_BK.getValue() + bizMsg.Y.no(intInvIndex).docMgtDocId_Y1.getValue().toString());
                    }
                }
                // END   2016/09/29 W.Honda [Unit Test#201,ADD]
                // Comment Info
                ZYPEZDItemValueSetter.setValue(bizMsg.invCmntTxt_CO, globalMsg.A.no(i).invCmntTxt_CO.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.apAdjAmt_CO, globalMsg.A.no(i).apAdjAmt_CO.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.apAdjRsnCd_CO, globalMsg.A.no(i).apAdjRsnCd_CO.getValue());
            }
            if (foundStartIndex) {
                // Line Info
                // Mod Start 2016/08/22 QC#12830
//                int remainder = intScreenLineIndex % INT_6;
//                if (remainder == 0) {
                if (ZYPConstant.FLG_OFF_N.equals(globalMsg.A.no(i).xxGrpFlg_A1.getValue())) {
                    // Mod End 2016/08/22 QC#12830
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(intScreenLineIndex).ezUpTime_SE, globalMsg.A.no(i).ezUpTime_SE.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(intScreenLineIndex).ezUpTimeZone_SE, globalMsg.A.no(i).ezUpTimeZone_SE.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(intScreenLineIndex).serNum_A1, globalMsg.A.no(i).serNum_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(intScreenLineIndex).ovrdSerNum_A1, globalMsg.A.no(i).ovrdSerNum_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(intScreenLineIndex).startDt_A1, globalMsg.A.no(i).startDt_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(intScreenLineIndex).endDt_A1, globalMsg.A.no(i).endDt_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(intScreenLineIndex).baseAmt_A1, globalMsg.A.no(i).baseAmt_A1.getValue());
                }
                // Create [Counter Type] pulldown.
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(intScreenLineIndex).ezUpTime_ME, globalMsg.A.no(i).ezUpTime_ME.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(intScreenLineIndex).ezUpTimeZone_ME, globalMsg.A.no(i).ezUpTimeZone_ME.getValue());
                NFBL1110CommonLogic.createCntrTpPulldown(bizMsg, intScreenLineIndex);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(intScreenLineIndex).cntrTpCd_A1, globalMsg.A.no(i).cntrTpCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(intScreenLineIndex).startReadMtrCnt_A1, globalMsg.A.no(i).startReadMtrCnt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(intScreenLineIndex).endReadMtrCnt_A1, globalMsg.A.no(i).endReadMtrCnt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(intScreenLineIndex).readMtrCnt_A1, globalMsg.A.no(i).readMtrCnt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(intScreenLineIndex).apTolAmt_A1, globalMsg.A.no(i).apTolAmt_A1.getValue());
                // Add 2016/08/22 QC#12830
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(intScreenLineIndex).xxGrpFlg_A1, globalMsg.A.no(i).xxGrpFlg_A1);

                intScreenLineIndex++;
            }
        }
        if (foundStartIndex) {
            bizMsg.A.setValidCount(intScreenLineIndex);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method name: deleteCurrentInvInfoFromY
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    @SuppressWarnings("unchecked")
    public static void deleteCurrentInvInfoFromY(NFBL1110CMsg bizMsg) {

        int currentInvIndex = bizMsg.xxListNum_Y.getValue().intValue();

        List<Map> listNewKeyInfo = new ArrayList<Map>();
        for (int i = 0; i < bizMsg.Y.getValidCount(); i++) {
            if (i != currentInvIndex) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(AP_INV_NUM, bizMsg.Y.no(i).apInvNum_Y1.getValue());
                map.put(AP_VND_CD, bizMsg.Y.no(i).apVndCd_Y1.getValue());
                map.put(AP_INV_AMT, bizMsg.Y.no(i).apInvAmt_Y1.getValue());
                // START 2016/09/29 W.Honda [Unit Test#201,ADD]
                map.put(DOC_MGT_DOC_ID, bizMsg.Y.no(i).docMgtDocId_Y1.getValue());
                map.put(DOC_MGT_CATG_CD, bizMsg.Y.no(i).docMgtCatgCd_Y1.getValue());
                // END   2016/09/29 W.Honda [Unit Test#201,ADD]
                listNewKeyInfo.add(map);
            }
        }
        bizMsg.Y.setValidCount(bizMsg.Y.getValidCount()-1);
        for (int i = 0; i < listNewKeyInfo.size(); i++) {
            Map map = (Map)listNewKeyInfo.get(i);
            ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(i).apInvNum_Y1, (String)map.get(AP_INV_NUM));
            ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(i).apVndCd_Y1, (String)map.get(AP_VND_CD));
            ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(i).apInvAmt_Y1, (BigDecimal)map.get(AP_INV_AMT));
            // START 2016/09/29 W.Honda [Unit Test#201,ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(i).docMgtDocId_Y1, (BigDecimal)map.get(DOC_MGT_DOC_ID));
            ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(i).docMgtCatgCd_Y1, (String)map.get(DOC_MGT_CATG_CD));
            // END   2016/09/29 W.Honda [Unit Test#201,ADD]
        }
    }

    /**
     * Method name: deleteCurrentInvInfoFromS
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global Component Interface Message
     */
    @SuppressWarnings("unchecked")
    public static void deleteCurrentInvInfoFromS(NFBL1110CMsg bizMsg, NFBL1110SMsg globalMsg) {

        List<Map> listAfterCurrentInvInfo = new ArrayList<Map>();
        int currentInvIndex = bizMsg.xxListNum_Y.getValue().intValue();
        int afterInvIndex = bizMsg.xxListNum_Y.getValue().intValue();
        int newValidCount = 0;

        // Save invoice information except current invoice to list
        boolean beforeCurInvCompleteFlag = false;
        int intStartIndex = 0;
        String prevApInvNum = EMPTY_STRING;
        String prevApVndCd = EMPTY_STRING;

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (globalMsg.A.no(i).xxListNum_A1.getValueInt() == currentInvIndex) {
                if (!beforeCurInvCompleteFlag) { 
                    beforeCurInvCompleteFlag = true;
                    // Keep next adding SMsg index
                    intStartIndex = i;
                }
                // Clear SMsg info for current invoice.
                globalMsg.A.no(i).clear();
            } else {
                if (beforeCurInvCompleteFlag) {
                    if (!prevApInvNum.equals(EMPTY_STRING) || !prevApVndCd.equals(EMPTY_STRING)) {
                        afterInvIndex++;
                    }
                    // Keep invoice info having index after current invoice. 
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(XX_LIST_NUM, new BigDecimal(afterInvIndex));
                    map.put(CMI_EZUPTIME, globalMsg.A.no(i).ezUpTime_IH.getValue());
                    map.put(CMI_EZUPTIMEZONE, globalMsg.A.no(i).ezUpTimeZone_IH.getValue());
                    map.put(AP_DS_WF_STS_CD, globalMsg.A.no(i).apDsWfStsCd_IH.getValue());
                    map.put(AP_DS_WF_STS_NM, globalMsg.A.no(i).apDsWfStsNm_IH.getValue());
                    map.put(AP_MAINT_INV_STS_CD, globalMsg.A.no(i).apMaintInvStsCd_IH.getValue());
                    map.put(AP_MAINT_INV_STS_NM, globalMsg.A.no(i).apMaintInvStsNm_IH.getValue());
                    map.put(AP_INV_NUM, globalMsg.A.no(i).apInvNum_IH.getValue());
                    map.put(INV_DT, globalMsg.A.no(i).invDt_IH.getValue());
                    // START 2016/09/09 K.Kojima [QC#12725,MOD]
                    // map.put(APVR_USR_NM,
                    // globalMsg.A.no(i).apvrUsrNm_IH.getValue());
                    map.put(APVR_USR_NM, globalMsg.A.no(i).varCharConstVal_IH.getValue());
                    // END 2016/09/09 K.Kojima [QC#12725,MOD]
                    map.put(PRNT_VND_CD, globalMsg.A.no(i).prntVndCd_IH.getValue());
                    map.put(PRNT_VND_NM, globalMsg.A.no(i).prntVndNm_IH.getValue());
                    map.put(AP_VND_CD, globalMsg.A.no(i).apVndCd_HD.getValue());
                    map.put(VND_SITE_NM, globalMsg.A.no(i).vndSiteNm_IH.getValue());
                    map.put(AP_INV_AMT, globalMsg.A.no(i).apInvAmt_IH.getValue());
                    map.put(AP_MISC_AMT, globalMsg.A.no(i).apMiscAmt_IH.getValue());
                    map.put(AP_TAX_AMT, globalMsg.A.no(i).apTaxAmt_IH.getValue());
                    map.put(LATE_FEE_AMT, globalMsg.A.no(i).lateFeeAmt_IH.getValue());
                    map.put(CMIS_EZUPTIME, globalMsg.A.no(i).ezUpTime_SE.getValue());
                    map.put(CMIS_EZUPTIMEZONE, globalMsg.A.no(i).ezUpTimeZone_SE.getValue());
                    map.put(SER_NUM, globalMsg.A.no(i).serNum_A1.getValue());
                    map.put(OVRD_SER_NUM, globalMsg.A.no(i).ovrdSerNum_A1.getValue());
                    map.put(START_DT, globalMsg.A.no(i).startDt_A1.getValue());
                    map.put(END_DT, globalMsg.A.no(i).endDt_A1.getValue());
                    map.put(BASE_AMT, globalMsg.A.no(i).baseAmt_A1.getValue());
                    map.put(CMIM_EZUPTIME, globalMsg.A.no(i).ezUpTime_ME.getValue());
                    map.put(CMIM_EZUPTIMEZONE, globalMsg.A.no(i).ezUpTimeZone_ME.getValue());
                    map.put(CNTR_TP_CD, globalMsg.A.no(i).cntrTpCd_A1.getValue());
                    map.put(START_READ_MTR_CNT, globalMsg.A.no(i).startReadMtrCnt_A1.getValue());
                    map.put(END_READ_MTR_CNT, globalMsg.A.no(i).endReadMtrCnt_A1.getValue());
                    map.put(READ_MTR_CNT, globalMsg.A.no(i).readMtrCnt_A1.getValue());
                    map.put(AP_TOL_AMT, globalMsg.A.no(i).apTolAmt_A1.getValue());
                    map.put(INV_CMNT_TXT, globalMsg.A.no(i).invCmntTxt_CO.getValue());
                    map.put(AP_ADJ_AMT, globalMsg.A.no(i).apAdjAmt_CO.getValue());
                    map.put(AP_ADJ_RSN_CD, globalMsg.A.no(i).apAdjRsnCd_CO.getValue());
                    listAfterCurrentInvInfo.add(map);
                    // Clear SMsg info for current invoice.
                    globalMsg.A.no(i).clear();

                    prevApInvNum = globalMsg.A.no(i).apInvNum_IH.getValue();
                    prevApVndCd = globalMsg.A.no(i).apVndCd_HD.getValue();
                }
                newValidCount++;
            }
        }

        for (int i = 0; i < listAfterCurrentInvInfo.size(); i++) {
            Map map = (Map) listAfterCurrentInvInfo.get(i);
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).xxListNum_A1, (BigDecimal)map.get(XX_LIST_NUM));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).ezUpTime_IH, (String)map.get(CMI_EZUPTIME));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).ezUpTimeZone_IH, (String)map.get(CMI_EZUPTIMEZONE));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).apDsWfStsCd_IH, (String)map.get(AP_DS_WF_STS_CD));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).apDsWfStsNm_IH, (String)map.get(AP_DS_WF_STS_NM));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).apMaintInvStsCd_IH, (String)map.get(AP_MAINT_INV_STS_CD));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).apMaintInvStsNm_IH, (String)map.get(AP_MAINT_INV_STS_NM));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).apInvNum_IH, (String)map.get(AP_INV_NUM));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).invDt_IH, (String)map.get(INV_DT));
            // START 2016/09/09 K.Kojima [QC#12725,MOD]
            // ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).apvrUsrNm_IH,
            // (String)map.get(APVR_USR_NM));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).varCharConstVal_IH, (String) map.get(APVR_USR_NM));
            // END 2016/09/09 K.Kojima [QC#12725,MOD]
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).prntVndCd_IH, (String)map.get(PRNT_VND_CD));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).prntVndNm_IH, (String)map.get(PRNT_VND_NM));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).apVndCd_HD, (String)map.get(AP_VND_CD));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).vndSiteNm_IH, (String)map.get(VND_SITE_NM));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).apInvAmt_IH, (BigDecimal)map.get(AP_INV_AMT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).apMiscAmt_IH, (BigDecimal)map.get(AP_MISC_AMT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).apTaxAmt_IH, (BigDecimal)map.get(AP_TAX_AMT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).lateFeeAmt_IH, (BigDecimal)map.get(LATE_FEE_AMT));

            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).ezUpTime_SE, (String)map.get(CMIS_EZUPTIME));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).ezUpTimeZone_SE, (String)map.get(CMIS_EZUPTIMEZONE));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).serNum_A1, (String)map.get(SER_NUM));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).ovrdSerNum_A1, (String)map.get(OVRD_SER_NUM));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).startDt_A1, (String)map.get(START_DT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).endDt_A1, (String)map.get(END_DT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).baseAmt_A1, (BigDecimal)map.get(BASE_AMT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).ezUpTime_ME, (String)map.get(CMIM_EZUPTIME));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).ezUpTimeZone_ME, (String)map.get(CMIM_EZUPTIMEZONE));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).cntrTpCd_A1, (String)map.get(CNTR_TP_CD));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).startReadMtrCnt_A1, (BigDecimal)map.get(START_READ_MTR_CNT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).endReadMtrCnt_A1, (BigDecimal)map.get(END_READ_MTR_CNT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).readMtrCnt_A1, (BigDecimal)map.get(READ_MTR_CNT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).apTolAmt_A1, (BigDecimal)map.get(AP_TOL_AMT));

            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).invCmntTxt_CO, (String)map.get(INV_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).apAdjAmt_CO, (BigDecimal)map.get(AP_ADJ_AMT));
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(intStartIndex).apAdjRsnCd_CO, (String)map.get(AP_ADJ_RSN_CD));

            intStartIndex++;
        }
        globalMsg.A.setValidCount(newValidCount);
    }
    

    /**
     * Method name: setBatchHeaderInfo
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void setBatchHeaderInfo(NFBL1110CMsg bizMsg) {



    }

    
    
    /**
     * Method name: checkLocNm
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static boolean checkLocNm(NFBL1110CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NFBL1110Query.getInstance().checkLocNm(bizMsg);

        if (ssmResult.isCodeNormal()) {
            return true;
        }
        return false;
    }

    /**
     * Method name: checkPrntVnd
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static boolean checkPrntVnd(NFBL1110CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NFBL1110Query.getInstance().checkPrntVnd(bizMsg);

        if (ssmResult.isCodeNormal()) {
            return true;
        }
        return false;
    }

    /**
     * Method name: createAdjReasonPulldownList
     * <dd>The method explanation: Create pulldown list.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @SuppressWarnings("unchecked")
    public static void createAdjReasonPulldownList(EZDCMsg cMsg) {

        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;

        S21SsmEZDResult ssmResult = NFBL1110Query.getInstance().getApAdjRsnPulldownValue();

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);

                ZYPEZDItemValueSetter.setValue(bizMsg.apAdjRsnCd_C.no(i), (String) map.get(AP_ADJ_RSN_CD));
                ZYPEZDItemValueSetter.setValue(bizMsg.apAdjRsnNm_D.no(i), (String) map.get(AP_ADJ_RSN_NM));
            }
        }

    }

    /**
     * Method name: saveCntrTpPulldownListValue
     * <dd>The method explanation: Save records for [Counter Type] pulldown list
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @SuppressWarnings("unchecked")
    public static void saveCntrTpPulldownListValue(EZDCMsg cMsg) {

        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;

        S21SsmEZDResult ssmResult = NFBL1110Query.getInstance().getCntrTpPulldownListValue();

        List resultList = new ArrayList();
        if (ssmResult.isCodeNormal()) {
            resultList = (List) ssmResult.getResultObject();
            int validCount = resultList.size();
            if (bizMsg.P.length() < resultList.size()) {
                validCount = bizMsg.P.length();
            }
            bizMsg.P.setValidCount(validCount);
            for (int i = 0; i < bizMsg.P.getValidCount(); i++) {
                Map map = (Map) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.P.no(i).cntrTpCd_P1, (String) map.get(CNTR_TP_CD));
                ZYPEZDItemValueSetter.setValue(bizMsg.P.no(i).cntrTpNm_P1, (String) map.get(CNTR_TP_NM));
            }
        }
    }

    /**
     * Method name: createCntrTpPulldown
     * <dd>The method explanation: Create pulldown list.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param index int
     */
    public static void createCntrTpPulldown(EZDCMsg cMsg, int index) {

        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;

        bizMsg.A.no(index).cntrTpCd_C.clear();
        bizMsg.A.no(index).cntrTpNm_D.clear();
        for (int i = 0; i < bizMsg.P.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).cntrTpCd_C.no(i), bizMsg.P.no(i).cntrTpCd_P1.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).cntrTpNm_D.no(i), bizMsg.P.no(i).cntrTpNm_P1.getValue());
        }
 
    }

    /**
     * Method name: setPrntVndNm
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    @SuppressWarnings("unchecked")
    public static void setPrntVndNm(NFBL1110CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NFBL1110Query.getInstance().getPrntVndNm(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            Map map = (Map) resultList.get(0);
            ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm_IH, (String) map.get(PRNT_VND_NM));
        } else {
            bizMsg.prntVndCd_IH.setErrorInfo(1, NFBM0069E);
        }
    }

    /**
     * Method name: initBatchHeader
     * <dd>The method explanation: Clear Batch Header.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void initBatchHeader(EZDCMsg cMsg) {

        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;

        bizMsg.cmMaintInvBatPk_BA.clear();
        bizMsg.apBatNum_BA.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.apCtrlAmt_BA, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.apCtrlCnt_BA, BigDecimal.ZERO);
        // Set default batch date
        ZYPEZDItemValueSetter.setValue(bizMsg.apBatDt_BA, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
        ZYPEZDItemValueSetter.setValue(bizMsg.apRunTotAmt_BA, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.apRunTotCnt_BA, BigDecimal.ZERO);

    }

    /**
     * Method name: initInvoiceHeader
     * <dd>The method explanation: Clear Invoice Header.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void initInvoiceHeader(EZDCMsg cMsg) {

        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;

        // START 2016/09/09 K.Kojima [QC#12725,MOD]
        // bizMsg.apvrUsrNm_IH.clear();
        bizMsg.varCharConstVal_IH.clear();
        // END 2016/09/09 K.Kojima [QC#12725,MOD]
        ZYPEZDItemValueSetter.setValue(bizMsg.apInvAmt_IH, BigDecimal.ZERO);
        bizMsg.apDsWfStsNm_IH.clear();
        bizMsg.prntVndNm_IH.clear();
        bizMsg.prntVndCd_IH.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.apMiscAmt_IH, BigDecimal.ZERO);
        bizMsg.apInvNum_IH.clear();
        bizMsg.apVndCd_HD.clear();
        bizMsg.vndSiteNm_IH.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.apTaxAmt_IH, BigDecimal.ZERO);
        bizMsg.invDt_IH.clear();
        bizMsg.apMaintInvStsNm_IH.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.lateFeeAmt_IH, BigDecimal.ZERO);
        // START 2016/09/29 W.Honda [Unit Test#201,ADD]
        bizMsg.docMgtDocId.clear();
        bizMsg.docMgtDocId_IH.clear();
        bizMsg.docMgtCatgCd.clear();
        bizMsg.xxSrvUrlTxt.clear();
        // END   2016/09/29 W.Honda [Unit Test#201,ADD]

    }

    /**
     * Method name: initNewSerEntry
     * <dd>The method explanation: Clear field values for [Add Serial] button.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void initNewSerEntry(EZDCMsg cMsg) {

        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;

        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_AD, ZYPConstant.FLG_OFF_N);
        bizMsg.serNum_AD.clear();
        bizMsg.ovrdSerNum_AD.clear();
        bizMsg.startDt_AD.clear();
        bizMsg.endDt_AD.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.baseAmt_AD, BigDecimal.ZERO);

    }

    /**
     * Method name: initInvoiceDetail
     * <dd>The method explanation: Clear Invoice Detail.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void initInvoiceDetail(EZDCMsg cMsg) {

        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
    }

    /**
     * Method name: initComment
     * <dd>The method explanation: Clear comment.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void initComment(EZDCMsg cMsg) {

        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;

        bizMsg.invCmntTxt_CO.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.apAdjAmt_CO, BigDecimal.ZERO);
        bizMsg.apAdjRsnCd_CO.clear();

    }

    /**
     * Method name: initSMsg
     * <dd>The method explanation: Clear SMsg.
     * <dd>Remarks:
     * @param sMsg Global area information
     */
    public static void initSMsg(EZDSMsg sMsg) {

        NFBL1110SMsg globalMsg = (NFBL1110SMsg) sMsg;

        globalMsg.A.clear();
        globalMsg.A.setValidCount(0);

    }

    /**
     * Method name: clearRunningValue
     * <dd>The method explanation: Clear Running Value.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void clearRunningValue(EZDCMsg cMsg) {

        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;

        bizMsg.Y.clear();
        bizMsg.Y.setValidCount(0);

    }

    /**
     * Method name: initializeScreenValue
     * <dd>The method explanation: Initialization
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void initializeScreenValue(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;
        NFBL1110SMsg globalMsg = (NFBL1110SMsg) sMsg;

        // For Submit button for deleting invoice.
        ZYPEZDItemValueSetter.setValue(bizMsg.xxInvDelFlg, ZYPConstant.FLG_OFF_N);
        // Clear Batch Header.
        NFBL1110CommonLogic.initBatchHeader(bizMsg);
        // Clear Invoice Header.
        NFBL1110CommonLogic.initInvoiceHeader(bizMsg);
        // Clear Invoice index
        bizMsg.xxListNum_Y.clear(); 
        // Reset flag for new invoice or not to new invoice
        ZYPEZDItemValueSetter.setValue(bizMsg.invFlg_NE, ZYPConstant.FLG_ON_Y);
        // Clear input field for [Add Serial] button.
        NFBL1110CommonLogic.initNewSerEntry(bizMsg);
        // Clear Invoice Detail.
        NFBL1110CommonLogic.initInvoiceDetail(bizMsg);
        // Clear Comment
        NFBL1110CommonLogic.initComment(bizMsg);
        // Clear SMsg information
        NFBL1110CommonLogic.initSMsg(globalMsg);
        // Clear Running Value
        NFBL1110CommonLogic.clearRunningValue(bizMsg);

    }

    /**
     * Method name: createMaintInvBatCommon
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global Component Interface Message
     * @param apDsWfStsCd String
     * @param apMaintInvStsCd String
     * @param glblCmpyCd String
     * @param userId String
     * @param userNm String
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    // START 2016/09/09 K.Kojima [QC#12725,MOD]
    // public static boolean createMaintInvBatCommon(NFBL1110CMsg
    // bizMsg, NFBL1110SMsg globalMsg, String apDsWfStsCd, String
    // apMaintInvStsCd, String glblCmpyCd, String userId, String
    // userNm) {
    public static boolean createMaintInvBatCommon(NFBL1110CMsg bizMsg, NFBL1110SMsg globalMsg, String apDsWfStsCd, String apMaintInvStsCd, String glblCmpyCd) {
        // END 2016/09/09 K.Kojima [QC#12725,MOD]
        BigDecimal cmMaintInvBatPk = BigDecimal.ZERO;
        String apBatNum = EMPTY_STRING;
        String stdCcyCd = getCcyCd();
        if (ZYPCommonFunc.hasValue(bizMsg.cmMaintInvBatPk_BA)) {
            cmMaintInvBatPk = bizMsg.cmMaintInvBatPk_BA.getValue();
            apBatNum = bizMsg.apBatNum_BA.getValue();
            // Delete existing CM_MAINT_INV_BAT table record.
            CM_MAINT_INV_BATTMsg cmMaintInvBat = new CM_MAINT_INV_BATTMsg();
            ZYPEZDItemValueSetter.setValue(cmMaintInvBat.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cmMaintInvBat.cmMaintInvBatPk, bizMsg.cmMaintInvBatPk_BA.getValue());
            cmMaintInvBat = (CM_MAINT_INV_BATTMsg) S21FastTBLAccessor.findByKeyForUpdate(cmMaintInvBat);
            if (cmMaintInvBat == null) {
                bizMsg.setMessageInfo(NFBM0155E);
                return false;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_BA.getValue(), bizMsg.ezUpTimeZone_BA.getValue(), cmMaintInvBat.ezUpTime.getValue(), cmMaintInvBat.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NFBM0155E);
                    return false;
                }
            }
            EZDTBLAccessor.remove(cmMaintInvBat);
            // Exclusive Control
            String prevApInvNum = EMPTY_STRING;
            String prevApVndCd = EMPTY_STRING;
            String prevSerNum = EMPTY_STRING;
            String prevStartDt = EMPTY_STRING;

            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

                if (!prevApInvNum.equals(globalMsg.A.no(i).apInvNum_IH.getValue())
                ||  !prevApVndCd.equals(globalMsg.A.no(i).apVndCd_HD.getValue())) {
                    CM_MAINT_INVTMsg cmMaintInv = new CM_MAINT_INVTMsg();
                    ZYPEZDItemValueSetter.setValue(cmMaintInv.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmMaintInv.apInvNum, globalMsg.A.no(i).apInvNum_IH.getValue());
                    ZYPEZDItemValueSetter.setValue(cmMaintInv.apVndCd, globalMsg.A.no(i).apVndCd_HD.getValue());
                    // ZYPEZDItemValueSetter.setValue(cmMaintInv.apBatNum, apBatNum);
                    cmMaintInv = (CM_MAINT_INVTMsg) S21FastTBLAccessor.findByKey(cmMaintInv);
                    if (cmMaintInv != null) {
                        if (!ZYPDateUtil.isSameTimeStamp(globalMsg.A.no(i).ezUpTime_IH.getValue(), globalMsg.A.no(i).ezUpTimeZone_IH.getValue(), cmMaintInv.ezUpTime.getValue(), cmMaintInv.ezUpTimeZone.getValue())) {
                            bizMsg.setMessageInfo(NFBM0155E);
                            return false;
                        }
                    }
                }
                if (!prevApInvNum.equals(globalMsg.A.no(i).apInvNum_IH.getValue())
                ||  !prevApVndCd.equals(globalMsg.A.no(i).apVndCd_HD.getValue())
                ||  !prevSerNum.equals(globalMsg.A.no(i).serNum_A1.getValue())
                ||  !prevStartDt.equals(globalMsg.A.no(i).startDt_A1.getValue())
                ) {
                    CM_MAINT_INV_SERTMsg cmMaintInvSer = new CM_MAINT_INV_SERTMsg();

                    ZYPEZDItemValueSetter.setValue(cmMaintInvSer.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmMaintInvSer.apInvNum, globalMsg.A.no(i).apInvNum_IH.getValue());
                    ZYPEZDItemValueSetter.setValue(cmMaintInvSer.apVndCd, globalMsg.A.no(i).apVndCd_HD.getValue());
                    ZYPEZDItemValueSetter.setValue(cmMaintInvSer.serNum, globalMsg.A.no(i).serNum_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(cmMaintInvSer.startDt, globalMsg.A.no(i).startDt_A1.getValue());

                    cmMaintInvSer = (CM_MAINT_INV_SERTMsg) S21FastTBLAccessor.findByKey(cmMaintInvSer);
                    if (cmMaintInvSer != null) {
                        if (!ZYPDateUtil.isSameTimeStamp(globalMsg.A.no(i).ezUpTime_SE.getValue(), globalMsg.A.no(i).ezUpTimeZone_SE.getValue(), cmMaintInvSer.ezUpTime.getValue(), cmMaintInvSer.ezUpTimeZone.getValue())) {
                            bizMsg.setMessageInfo(NFBM0155E);
                            return false;
                        }
                    }
                }
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).cntrTpCd_A1)) {
                    CM_MAINT_INV_MTRTMsg cmMaintInvMtr = new CM_MAINT_INV_MTRTMsg();

                    ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.apInvNum, globalMsg.A.no(i).apInvNum_IH.getValue());
                    ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.apVndCd, globalMsg.A.no(i).apVndCd_HD.getValue());
                    ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.serNum, globalMsg.A.no(i).serNum_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.startDt, globalMsg.A.no(i).startDt_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.cntTpCd, globalMsg.A.no(i).cntrTpCd_A1.getValue());

                    cmMaintInvMtr = (CM_MAINT_INV_MTRTMsg) S21FastTBLAccessor.findByKey(cmMaintInvMtr);
                    if (cmMaintInvMtr != null) {
                        if (!ZYPDateUtil.isSameTimeStamp(globalMsg.A.no(i).ezUpTime_ME.getValue(), globalMsg.A.no(i).ezUpTimeZone_ME.getValue(), cmMaintInvMtr.ezUpTime.getValue(), cmMaintInvMtr.ezUpTimeZone.getValue())) {
                            bizMsg.setMessageInfo(NFBM0155E);
                            return false;
                        }
                    }
                }
                prevApInvNum = globalMsg.A.no(i).apInvNum_IH.getValue();
                prevApVndCd = globalMsg.A.no(i).apVndCd_HD.getValue();
                prevSerNum = globalMsg.A.no(i).serNum_A1.getValue();
                prevStartDt = globalMsg.A.no(i).startDt_A1.getValue();
            }
            S21SsmEZDResult ssmResult = NFBL1110Query.getInstance().getInvListByBatNum(bizMsg);
            if (ssmResult.isCodeNormal()) {
                List resultList = (List) ssmResult.getResultObject();
                CM_MAINT_INVTMsg[] cmMaintInvTmsgs = new CM_MAINT_INVTMsg[resultList.size()];
                for (int i = 0; i < resultList.size(); i++) {
                    Map map = (Map) resultList.get(i);
                    // Delete existing CM_MAINT_INV table record.
                    CM_MAINT_INVTMsg cmMaintInv = new CM_MAINT_INVTMsg();
                    ZYPEZDItemValueSetter.setValue(cmMaintInv.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmMaintInv.apInvNum, (String) map.get(AP_INV_NUM));
                    ZYPEZDItemValueSetter.setValue(cmMaintInv.apVndCd, (String) map.get(AP_VND_CD));
                    cmMaintInv = (CM_MAINT_INVTMsg) S21FastTBLAccessor.findByKeyForUpdate(cmMaintInv);
                    cmMaintInvTmsgs[i] = cmMaintInv;
                    // Delete existing CM_MAINT_INV_SER table record.
                    CM_MAINT_INV_SERTMsg cmMaintInvSer = new CM_MAINT_INV_SERTMsg();
                    ZYPEZDItemValueSetter.setValue(cmMaintInvSer.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmMaintInvSer.apInvNum, (String) map.get(AP_INV_NUM));
                    ZYPEZDItemValueSetter.setValue(cmMaintInvSer.apVndCd, (String) map.get(AP_VND_CD));
                    S21FastTBLAccessor.removeByPartialValue(cmMaintInvSer, new String[] {"glblCmpyCd", "apInvNum", "apVndCd" });
                    // Delete existing CM_MAINT_INV_MTR table record.
                    CM_MAINT_INV_MTRTMsg cmMaintInvMtr = new CM_MAINT_INV_MTRTMsg();
                    ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.apInvNum, (String) map.get(AP_INV_NUM));
                    ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.apVndCd, (String) map.get(AP_VND_CD));
                    S21FastTBLAccessor.removeByPartialValue(cmMaintInvMtr, new String[] {"glblCmpyCd", "apInvNum", "apVndCd" });
                }
                S21FastTBLAccessor.removePhysical(cmMaintInvTmsgs);
            }
        } else {
            cmMaintInvBatPk = ZYPOracleSeqAccessor.getNumberBigDecimal(CM_MAINT_INV_BAT_SQ);
            apBatNum = createApBatNumFromPk(cmMaintInvBatPk);
        }

        // QC#21686 Start
        String chkApMaintInvStsCd = NFBL1110Query.getInstance().chkInvRecordSts(bizMsg);
        if (ZYPCommonFunc.hasValue(chkApMaintInvStsCd) //
                && !AP_MAINT_INV_STS_CD_00.equals(chkApMaintInvStsCd)) {

            String errMsg = "Inv#:" + bizMsg.apInvNum_IH.getValue() + ", Supplier:" + bizMsg.prntVndCd_IH.getValue() + ", Site Name:" + bizMsg.vndSiteNm_IH.getValue();
            bizMsg.apInvNum_IH.setErrorInfo(1, NFBM0181E, new String[]{errMsg});
            bizMsg.vndSiteNm_IH.setErrorInfo(1, NFBM0181E, new String[]{errMsg});
            bizMsg.prntVndCd_IH.setErrorInfo(1, NFBM0181E, new String[]{errMsg});
            return false;

        }
        // QC#21686 End
        CM_MAINT_INV_BATTMsg cmMaintInvBat = new CM_MAINT_INV_BATTMsg();

        ZYPEZDItemValueSetter.setValue(cmMaintInvBat.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cmMaintInvBat.cmMaintInvBatPk, cmMaintInvBatPk);
        ZYPEZDItemValueSetter.setValue(cmMaintInvBat.apBatNum, apBatNum);
        ZYPEZDItemValueSetter.setValue(cmMaintInvBat.apBatDt, bizMsg.apBatDt_BA.getValue());
        ZYPEZDItemValueSetter.setValue(cmMaintInvBat.apCtrlAmt, bizMsg.apCtrlAmt_BA.getValue());
        ZYPEZDItemValueSetter.setValue(cmMaintInvBat.apCtrlCnt, bizMsg.apCtrlCnt_BA.getValue());
        ZYPEZDItemValueSetter.setValue(cmMaintInvBat.apRunTotAmt, bizMsg.apRunTotAmt_BA.getValue());
        ZYPEZDItemValueSetter.setValue(cmMaintInvBat.apRunTotCnt, bizMsg.apRunTotCnt_BA.getValue());
        ZYPEZDItemValueSetter.setValue(cmMaintInvBat.apBatFlg, ZYPConstant.FLG_OFF_N);

        EZDTBLAccessor.create(cmMaintInvBat);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmMaintInvBat.getReturnCode())) {
            bizMsg.setMessageInfo(NFBM0028E);
            return false;
        }

        String prevApInvNum = EMPTY_STRING;
        String prevApVndCd = EMPTY_STRING;
        String prevSerNum = EMPTY_STRING;
        String prevStartDt = EMPTY_STRING;

        List<CM_MAINT_INVTMsg> listCmMaintInv = new ArrayList<CM_MAINT_INVTMsg>();
        List<CM_MAINT_INV_SERTMsg> listCmMaintInvSer = new ArrayList<CM_MAINT_INV_SERTMsg>();
        List<CM_MAINT_INV_MTRTMsg> listCmMaintInvMtr = new ArrayList<CM_MAINT_INV_MTRTMsg>();

        // Calculate AP_TOL_AMT for CM_MAINT_INV_SER table.
        Map<List, BigDecimal> mapSerApTolAmt = new HashMap<List, BigDecimal>();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            List<String> listSerKey = new ArrayList<String>();
            listSerKey.add(globalMsg.A.no(i).apInvNum_IH.getValue());
            listSerKey.add(globalMsg.A.no(i).apVndCd_HD.getValue());
            listSerKey.add(globalMsg.A.no(i).serNum_A1.getValue());
            listSerKey.add(globalMsg.A.no(i).startDt_A1.getValue());
            BigDecimal serApTolAmt = (BigDecimal) mapSerApTolAmt.get(listSerKey);
            if (serApTolAmt == null) {
                serApTolAmt = globalMsg.A.no(i).apTolAmt_A1.getValue();
            } else {
                serApTolAmt = serApTolAmt.add(globalMsg.A.no(i).apTolAmt_A1.getValue());
            }
            mapSerApTolAmt.put(listSerKey, serApTolAmt);
        }

        // Add Start 2016/08/24 QC#12695
        // for Sort
        int serLineNum = 0;
        int mtrLineNum = 0;
        // Add End 2016/08/24 QC#12695

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            if (!prevApInvNum.equals(globalMsg.A.no(i).apInvNum_IH.getValue())
            ||  !prevApVndCd.equals(globalMsg.A.no(i).apVndCd_HD.getValue())) {

                CM_MAINT_INVTMsg cmMaintInv = new CM_MAINT_INVTMsg();

                ZYPEZDItemValueSetter.setValue(cmMaintInv.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmMaintInv.apInvNum, globalMsg.A.no(i).apInvNum_IH.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInv.apVndCd, globalMsg.A.no(i).apVndCd_HD.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInv.apBatNum, apBatNum);
                ZYPEZDItemValueSetter.setValue(cmMaintInv.invDt, globalMsg.A.no(i).invDt_IH.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInv.vndSiteNm, globalMsg.A.no(i).vndSiteNm_IH.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInv.apDsWfStsCd, apDsWfStsCd);
                ZYPEZDItemValueSetter.setValue(cmMaintInv.apMaintInvStsCd, apMaintInvStsCd);
                // START 2016/09/09 K.Kojima [QC#12725,DEL]
                // ZYPEZDItemValueSetter.setValue(cmMaintInv.apvrUsrId,
                // userId);
                // ZYPEZDItemValueSetter.setValue(cmMaintInv.apvrUsrNm,
                // userNm);
                // END 2016/09/09 K.Kojima [QC#12725,DEL]
                ZYPEZDItemValueSetter.setValue(cmMaintInv.dealCcyCd, stdCcyCd);
                ZYPEZDItemValueSetter.setValue(cmMaintInv.apInvAmt, globalMsg.A.no(i).apInvAmt_IH.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInv.apMiscAmt, globalMsg.A.no(i).apMiscAmt_IH.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInv.apTaxAmt, globalMsg.A.no(i).apTaxAmt_IH.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInv.lateFeeAmt, globalMsg.A.no(i).lateFeeAmt_IH.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInv.apAdjAmt, globalMsg.A.no(i).apAdjAmt_CO.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInv.apAdjRsnCd, globalMsg.A.no(i).apAdjRsnCd_CO.getValue());
                // ZYPEZDItemValueSetter.setValue(cmMaintInv.apDocNum, globalMsg.A.no(i).apDocNum_.getValue()); // TODO
                cmMaintInv.apTolChkStsCd.clear();
                cmMaintInv.apNtfyStsCd.clear();
                ZYPEZDItemValueSetter.setValue(cmMaintInv.invCmntTxt, globalMsg.A.no(i).invCmntTxt_CO.getValue());
                listCmMaintInv.add(cmMaintInv);
            }
            if (!prevApInvNum.equals(globalMsg.A.no(i).apInvNum_IH.getValue())
            ||  !prevApVndCd.equals(globalMsg.A.no(i).apVndCd_HD.getValue())
            ||  !prevSerNum.equals(globalMsg.A.no(i).serNum_A1.getValue())
            ||  !prevStartDt.equals(globalMsg.A.no(i).startDt_A1.getValue())
            ) {

                List<String> listSerApTolAmtKey = new ArrayList<String>();
                listSerApTolAmtKey.add(globalMsg.A.no(i).apInvNum_IH.getValue());
                listSerApTolAmtKey.add(globalMsg.A.no(i).apVndCd_HD.getValue());
                listSerApTolAmtKey.add(globalMsg.A.no(i).serNum_A1.getValue());
                listSerApTolAmtKey.add(globalMsg.A.no(i).startDt_A1.getValue());
                BigDecimal serApTolAmt = (BigDecimal) mapSerApTolAmt.get(listSerApTolAmtKey);

                CM_MAINT_INV_SERTMsg cmMaintInvSer = new CM_MAINT_INV_SERTMsg();

                ZYPEZDItemValueSetter.setValue(cmMaintInvSer.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmMaintInvSer.apInvNum, globalMsg.A.no(i).apInvNum_IH.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInvSer.apVndCd, globalMsg.A.no(i).apVndCd_HD.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInvSer.serNum, globalMsg.A.no(i).serNum_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInvSer.startDt, globalMsg.A.no(i).startDt_A1.getValue());
                cmMaintInvSer.oldSerNum.clear();
                ZYPEZDItemValueSetter.setValue(cmMaintInvSer.endDt, globalMsg.A.no(i).endDt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInvSer.baseAmt, globalMsg.A.no(i).baseAmt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInvSer.ovrdSerNum, globalMsg.A.no(i).ovrdSerNum_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInvSer.apTolAmt, serApTolAmt);
                // Add Start 2016/08/24 QC#12695
                ZYPEZDItemValueSetter.setValue(cmMaintInvSer.cmMaintInvSerLineNum, new BigDecimal(++serLineNum));
                mtrLineNum = 0;
                // Add End 2016/08/24 QC#12695

                listCmMaintInvSer.add(cmMaintInvSer);
            }
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).cntrTpCd_A1)) {
                CM_MAINT_INV_MTRTMsg cmMaintInvMtr = new CM_MAINT_INV_MTRTMsg();

                ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.apInvNum, globalMsg.A.no(i).apInvNum_IH.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.apVndCd, globalMsg.A.no(i).apVndCd_HD.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.serNum, globalMsg.A.no(i).serNum_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.startDt, globalMsg.A.no(i).startDt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.cntTpCd, globalMsg.A.no(i).cntrTpCd_A1.getValue());
                cmMaintInvMtr.oldSerNum.clear();
                ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.startReadMtrCnt, globalMsg.A.no(i).startReadMtrCnt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.endReadMtrCnt, globalMsg.A.no(i).endReadMtrCnt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.readMtrCnt, globalMsg.A.no(i).readMtrCnt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.invCovAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.endDt, globalMsg.A.no(i).endDt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.apTolAmt, globalMsg.A.no(i).apTolAmt_A1.getValue());
                // Add Start 2016/08/24 QC#12695
                ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.cmMaintInvSerLineNum, new BigDecimal(serLineNum));
                ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.cmMaintInvMtrLineNum, new BigDecimal(++mtrLineNum));
                // Add End 2016/08/24 QC#12695

                listCmMaintInvMtr.add(cmMaintInvMtr);
            }

            prevApInvNum = globalMsg.A.no(i).apInvNum_IH.getValue();
            prevApVndCd = globalMsg.A.no(i).apVndCd_HD.getValue();
            prevSerNum = globalMsg.A.no(i).serNum_A1.getValue();
            prevStartDt = globalMsg.A.no(i).startDt_A1.getValue();
        }
        CM_MAINT_INVTMsg[] arrCmMaintInv = listCmMaintInv.toArray(new CM_MAINT_INVTMsg[listCmMaintInv.size()]);
        int iRet = S21FastTBLAccessor.insert(arrCmMaintInv);
        if (iRet == 0) {
            bizMsg.setMessageInfo(NFBM0028E);
            return false;
        }
        CM_MAINT_INV_SERTMsg[] arrCmMaintInvSer = listCmMaintInvSer.toArray(new CM_MAINT_INV_SERTMsg[listCmMaintInvSer.size()]);
        iRet = S21FastTBLAccessor.insert(arrCmMaintInvSer);
        if (iRet == 0) {
            bizMsg.setMessageInfo(NFBM0028E);
            return false;
        }
        if (listCmMaintInvMtr.size() > 0) {
            CM_MAINT_INV_MTRTMsg[] arrCmMaintInvMtr = listCmMaintInvMtr.toArray(new CM_MAINT_INV_MTRTMsg[listCmMaintInvMtr.size()]);
            iRet = S21FastTBLAccessor.insert(arrCmMaintInvMtr);
            if (iRet == 0) {
                bizMsg.setMessageInfo(NFBM0028E);
                return false;
            }
        }

        // START 2016/09/21 W.Honda [Unit Test#201,ADD]
        for (CM_MAINT_INVTMsg cmMaintInv : listCmMaintInv) {
            int invIndex = 0;
            for (; invIndex < bizMsg.Y.getValidCount(); invIndex++) {
                if (cmMaintInv.apVndCd.getValue().equals(bizMsg.Y.no(invIndex).apVndCd_Y1.getValue())
                        && cmMaintInv.apInvNum.getValue().equals(bizMsg.Y.no(invIndex).apInvNum_Y1.getValue())) {
                    break;
                }
            }

            if (!ZYPCommonFunc.hasValue(cmMaintInv.apMaintInvStsCd)
                    || AP_MAINT_INV_STS_CD_00.equals(cmMaintInv.apMaintInvStsCd.getValue())) {
                // this Invoice is not attach target, because this Invoice is save.
                continue;
            }

            if (!ZYPCommonFunc.hasValue(bizMsg.Y.no(invIndex).docMgtDocId_Y1)
                    || !ZYPCommonFunc.hasValue(bizMsg.Y.no(invIndex).docMgtCatgCd_Y1)) {
                // this Invoice is not Attach Therefore Document.
                continue;
            }

            String attDocTpCd = null;
            DOC_MGT_CATGTMsg docMgtCatgTMsg = new DOC_MGT_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(docMgtCatgTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(docMgtCatgTMsg.docMgtCatgCd, bizMsg.Y.no(invIndex).docMgtCatgCd_Y1);
            docMgtCatgTMsg = (DOC_MGT_CATGTMsg) S21FastTBLAccessor.findByKey(docMgtCatgTMsg);
            if (docMgtCatgTMsg != null) {
                attDocTpCd = docMgtCatgTMsg.attDocTpCd.getValue();
            }

            callApiForTherefore(bizMsg
                    , glblCmpyCd
                    , invIndex
                    , attDocTpCd
                    , true);

            if (MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
                return false;
            }
        }
        // END 2016/09/21 W.Honda [Unit Test#201,ADD]

        if (!ZYPCommonFunc.hasValue(bizMsg.cmMaintInvBatPk_BA)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.cmMaintInvBatPk_BA, cmMaintInvBatPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.apBatNum_BA, apBatNum);
        }
        return true;
    }

    /**
     * Method name: updateCmMaintInvCommon
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global Component Interface Message
     * @param apDsWfStsCd String
     * @param apMaintInvStsCd String
     * @param glblCmpyCd String
     */
    public static boolean updateCmMaintInvCommon(NFBL1110CMsg bizMsg, NFBL1110SMsg globalMsg, String apDsWfStsCd, String apMaintInvStsCd, String glblCmpyCd) {

        CM_MAINT_INVTMsg cmMaintInv = new CM_MAINT_INVTMsg();
        ZYPEZDItemValueSetter.setValue(cmMaintInv.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cmMaintInv.apInvNum, bizMsg.apInvNum_IH.getValue());
        ZYPEZDItemValueSetter.setValue(cmMaintInv.apVndCd, bizMsg.apVndCd_HD.getValue());
        cmMaintInv = (CM_MAINT_INVTMsg) S21FastTBLAccessor.findByKeyForUpdate(cmMaintInv);
        if (cmMaintInv != null) {
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_IH.getValue(), bizMsg.ezUpTimeZone_IH.getValue(), cmMaintInv.ezUpTime.getValue(), cmMaintInv.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NFBM0155E);
                return false;
            }
            ZYPEZDItemValueSetter.setValue(cmMaintInv.apDsWfStsCd, apDsWfStsCd);
            ZYPEZDItemValueSetter.setValue(cmMaintInv.apMaintInvStsCd, apMaintInvStsCd);
            ZYPEZDItemValueSetter.setValue(cmMaintInv.invCmntTxt, bizMsg.invCmntTxt_CO.getValue());
            ZYPEZDItemValueSetter.setValue(cmMaintInv.apAdjAmt, bizMsg.apAdjAmt_CO.getValue());
            ZYPEZDItemValueSetter.setValue(cmMaintInv.apAdjRsnCd, bizMsg.apAdjRsnCd_CO.getValue());

            S21FastTBLAccessor.update(cmMaintInv);
            return true;
        } else {
            bizMsg.setMessageInfo(NFBM0155E);
            return false;
        }
    }

    /**
     * Method name: getApDsWfStsNm
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param apDsWfStsCd String
     * @param glblCmpyCd String
     * @return String
     */
    public static String getApDsWfStsNm(String apDsWfStsCd, String glblCmpyCd) {
        EZDDebugOutput.println(5, "getApDsWfStsNm================================START", null);
        AP_DS_WF_STSTMsg apDsWfSts = new AP_DS_WF_STSTMsg();
        ZYPEZDItemValueSetter.setValue(apDsWfSts.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apDsWfSts.apDsWfStsCd, apDsWfStsCd);
        apDsWfSts = (AP_DS_WF_STSTMsg) EZDTBLAccessor.findByKey(apDsWfSts);
        EZDDebugOutput.println(5, "getApDsWfStsNm================================END", null);
        if (apDsWfSts == null) {
            return null;
        } else {
            return apDsWfSts.apDsWfStsNm.getValue();
        }
    }

    /**
     * Method name: getApMaintInvStsNm
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param apMaintInvStsCd String
     * @param glblCmpyCd String
     * @return String
     */
    public static String getApMaintInvStsNm(String apMaintInvStsCd, String glblCmpyCd) {
        EZDDebugOutput.println(5, "getApMaintInvStsNm================================START", null);
        AP_MAINT_INV_STSTMsg apMaintInvSts = new AP_MAINT_INV_STSTMsg();
        ZYPEZDItemValueSetter.setValue(apMaintInvSts.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apMaintInvSts.apMaintInvStsCd, apMaintInvStsCd);
        apMaintInvSts = (AP_MAINT_INV_STSTMsg) EZDTBLAccessor.findByKey(apMaintInvSts);
        EZDDebugOutput.println(5, "getApMaintInvStsNm================================END", null);
        if (apMaintInvSts == null) {
            return null;
        } else {
            return apMaintInvSts.apMaintInvStsNm.getValue();
        }
    }

    /**
     * Method name: createApBatNumFromPk
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cmMaintInvBatPk BigDecimal
     * @return String
     */
    public static String createApBatNumFromPk(BigDecimal cmMaintInvBatPk) {
        if (ZYPCommonFunc.hasValue(cmMaintInvBatPk)) {
            // Mod 2016/08/23 QC#12729
            // DecimalFormat decimalFormat = new DecimalFormat( STR_20_DIGIT_ZERO );
            DecimalFormat decimalFormat = new DecimalFormat( STR_13_DIGIT_ZERO );

            return decimalFormat.format(cmMaintInvBatPk);
        } else {
            return null;
        }
    }

    /**
     * Method name: clearScreenInfo
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global Component Interface Message
     */
    public static void clearScreenInfo(NFBL1110CMsg bizMsg, NFBL1110SMsg globalMsg) {
        // Clear Batch Header.
        NFBL1110CommonLogic.initBatchHeader(bizMsg);
        // Clear Invoice Header.
        NFBL1110CommonLogic.initInvoiceHeader(bizMsg);
        // Clear Invoice index
        ZYPEZDItemValueSetter.setValue(bizMsg.xxListNum_Y, BigDecimal.ZERO);
        // Reset flag for new invoice or not to new invoice
        ZYPEZDItemValueSetter.setValue(bizMsg.invFlg_NE, ZYPConstant.FLG_ON_Y);
        // Clear input field for [Add Serial] button.
        NFBL1110CommonLogic.initNewSerEntry(bizMsg);
        // Clear Invoice Detail.
        NFBL1110CommonLogic.initInvoiceDetail(bizMsg);
        // Clear Comment
        NFBL1110CommonLogic.initComment(bizMsg);
    }

    /**
     * Method name: getCcyCd
     * <dd>The method explanation: get Currency Code.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    @SuppressWarnings("unchecked")
    public static String getCcyCd() {
        S21SsmEZDResult ssmResult = NFBL1110Query.getInstance().getCcyCd();
        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                return (String) map.get(STD_CCY_CD);
            }
        }
        return null;
    }

    /**
     * Method name: deleteMaintInvBatCommon
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global Component Interface Message
     * @param glblCmpyCd String
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean deleteMaintInvBatCommon(NFBL1110CMsg bizMsg, NFBL1110SMsg globalMsg, String glblCmpyCd) {
        if (ZYPCommonFunc.hasValue(bizMsg.cmMaintInvBatPk_BA)) {
            // Delete existing CM_MAINT_INV_BAT table record.
            CM_MAINT_INV_BATTMsg cmMaintInvBat = new CM_MAINT_INV_BATTMsg();
            ZYPEZDItemValueSetter.setValue(cmMaintInvBat.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cmMaintInvBat.cmMaintInvBatPk, bizMsg.cmMaintInvBatPk_BA.getValue());
            cmMaintInvBat = (CM_MAINT_INV_BATTMsg) S21FastTBLAccessor.findByKeyForUpdate(cmMaintInvBat);
            if (cmMaintInvBat == null) {
                bizMsg.setMessageInfo(NFBM0155E);
                return false;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_BA.getValue(), bizMsg.ezUpTimeZone_BA.getValue(), cmMaintInvBat.ezUpTime.getValue(), cmMaintInvBat.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NFBM0155E);
                    return false;
                }
            }
            EZDTBLAccessor.remove(cmMaintInvBat);

            S21SsmEZDResult ssmResult = NFBL1110Query.getInstance().getInvListByBatNum(bizMsg);
            if (ssmResult.isCodeNormal()) {
                List resultList = (List) ssmResult.getResultObject();
                CM_MAINT_INVTMsg[] cmMaintInvTmsgs = new CM_MAINT_INVTMsg[resultList.size()];
                for (int i = 0; i < resultList.size(); i++) {
                    Map map = (Map) resultList.get(i);
                    // Delete existing CM_MAINT_INV table record.
                    CM_MAINT_INVTMsg cmMaintInv = new CM_MAINT_INVTMsg();
                    ZYPEZDItemValueSetter.setValue(cmMaintInv.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmMaintInv.apInvNum, (String) map.get(AP_INV_NUM));
                    ZYPEZDItemValueSetter.setValue(cmMaintInv.apVndCd, (String) map.get(AP_VND_CD));
                    cmMaintInv = (CM_MAINT_INVTMsg) S21FastTBLAccessor.findByKeyForUpdate(cmMaintInv);
                    cmMaintInvTmsgs[i] = cmMaintInv;
                    // Delete existing CM_MAINT_INV_SER table record.
                    CM_MAINT_INV_SERTMsg cmMaintInvSer = new CM_MAINT_INV_SERTMsg();
                    ZYPEZDItemValueSetter.setValue(cmMaintInvSer.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmMaintInvSer.apInvNum, (String) map.get(AP_INV_NUM));
                    ZYPEZDItemValueSetter.setValue(cmMaintInvSer.apVndCd, (String) map.get(AP_VND_CD));
                    S21FastTBLAccessor.removeByPartialValue(cmMaintInvSer, new String[] {"glblCmpyCd", "apInvNum", "apVndCd" });
                    // Delete existing CM_MAINT_INV_MTR table record.
                    CM_MAINT_INV_MTRTMsg cmMaintInvMtr = new CM_MAINT_INV_MTRTMsg();
                    ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.apInvNum, (String) map.get(AP_INV_NUM));
                    ZYPEZDItemValueSetter.setValue(cmMaintInvMtr.apVndCd, (String) map.get(AP_VND_CD));
                    S21FastTBLAccessor.removeByPartialValue(cmMaintInvMtr, new String[] {"glblCmpyCd", "apInvNum", "apVndCd" });
                }
                S21FastTBLAccessor.removePhysical(cmMaintInvTmsgs);
            }
        }

        return true;
    }
    // Add Start 2016/08/04 QC#12692
    /**
     * Check Serial Number
     * @param bizMsg NFBL1110CMsg
     * @param serNum String
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkSerial(NFBL1110CMsg bizMsg, String serNum, String glblCmpyCd) {

        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd).substring(0, 8);
        Integer serCount = NFBL1110Query.getInstance().countSerNum(bizMsg, serNum, getYearsAgo(slsDt));

        if (serCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param slsDt
     * @return String
     */
    private static String getYearsAgo(String slsDt) {

        final SimpleDateFormat dtFormatter = new SimpleDateFormat("yyyyMMdd");
        String str = "";

        try {
            Date date = dtFormatter.parse(slsDt);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.YEAR, -1);
            date = cal.getTime();
            str = dtFormatter.format(date);

        } catch (ParseException pe) {
            EZDDebugOutput.println(1, "getBeforeYear() param:" + slsDt + ", format:" + dtFormatter, null);
        }
        return str;
    }
    // Add End 2016/08/04 QC#12692

    // Add Start 2016/08/22 S21_NA#12947
    /**
     * get ApVndCd by locNm, Supplier
     * @param bizMsg NFBL1110CMsg
     * @return boolean
     */
    private static boolean getApVndCd(NFBL1110CMsg bizMsg) {

        String apVndCd = NFBL1110Query.getInstance().getApVndCd(bizMsg);

        if (ZYPCommonFunc.hasValue(apVndCd)) {
            bizMsg.apVndCd_HD.setValue(apVndCd);
            return true;
        }
        return false;
    }
    // Add End 2016/08/22 S21_NA#12947

    // START 2016/09/21 W.Honda [Unit Test#201,ADD]
    /**
     * callApiForTherefore
     * @param bizMsg Business Component Interface Message
     * @param glblCmpyCd String
     */
    private static void callApiForTherefore(NFBL1110CMsg bizMsg, String glblCmpyCd, int invIndex, String attDocTpCd, boolean isCreate) {
        // Create API Parameter
        ZYPFileUpDownParameter params = new ZYPFileUpDownParameter();
        String attGrpTxt = createAttGrpTxt(glblCmpyCd, bizMsg.Y.no(invIndex).apInvNum_Y1.getValue(), bizMsg.Y.no(invIndex).apVndCd_Y1.getValue());

        params.setBusinessId(BIZ_ID);
        params.setAttDataGrp(attGrpTxt);
        params.setBusinessNm(PARAMS_FUNCTION_NAME);
        params.setAttDocTpCd(attDocTpCd);
        params.setThereforeDocId(bizMsg.Y.no(invIndex).docMgtDocId_Y1.getValue().toString());

        NFZC505001PMsg pMsg = new NFZC505001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.docMgtDocId, bizMsg.Y.no(invIndex).docMgtDocId_Y1);
        ZYPEZDItemValueSetter.setValue(pMsg.docMgtCatgCd, bizMsg.Y.no(invIndex).docMgtCatgCd_Y1);
        ZYPEZDItemValueSetter.setValue(pMsg.apVndCd, bizMsg.Y.no(invIndex).apVndCd_Y1);
        ZYPEZDItemValueSetter.setValue(pMsg.apVndInvNum, bizMsg.Y.no(invIndex).apInvNum_Y1);

        params.setBizApiIdTherefore(THEREFORE_ATT_BIZ_ID);
        if (isCreate) {
            params.setArgsForBizApiTherefore(new Object[] {pMsg, ONBATCH_TYPE.ONLINE, ZYPFileUpDownConst.BIZAPI_TYPE_CREATE});
        } else {
            params.setArgsForBizApiTherefore(new Object[] {pMsg, ONBATCH_TYPE.ONLINE, ZYPFileUpDownConst.BIZAPI_TYPE_DELETE});
        }

        // Call Therefore Doc Attach API
        int attDataKey = ZYPFileUpDown.uploadTherefore(params);

        // error handling
        if (attDataKey == -1) {
            bizMsg.setMessageInfo(NFBM0262E);
        }

        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
        for (int i = 0; i < msgList.size(); i++) {
            bizMsg.setMessageInfo(msgList.get(0).getXxMsgid());
        }
    }

    /**
     * Method name: searchRecordByInvInfo
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param apInvNum String
     * @param apVndCd String
     * @param ssmResult S21SsmEZDResult
     */
    public static String createAttGrpTxt(String glblCmpyCd, String apInvNum, String apVndCd) {
        StringBuilder sqNum = new StringBuilder();
        sqNum.append(glblCmpyCd);
        sqNum.append(PARAMS_AP_VND_CD_KEY);
        sqNum.append(apVndCd);
        sqNum.append(PARAMS_AP_VND_INV_NUM_KEY);
        sqNum.append(apInvNum);

        return sqNum.toString();
    }

    /**
     * Method name: searchRecordByInvInfo
     * <dd>The method explanation:  
     * <dd>Remarks:
     * @param apInvNum String
     * @param apVndCd String
     * @param ssmResult S21SsmEZDResult
     */
    public static Map<String, Object> getAttDataForTherefore(String glblCmpyCd, String apInvNum, String apVndCd) {
        String attGrpTxt = createAttGrpTxt(glblCmpyCd, apInvNum, apVndCd);

        S21SsmEZDResult ssmResult = NFBL1110Query.getInstance().getAttDataForTherefore(attGrpTxt);

        if (ssmResult.isCodeNormal()) {
            return (Map<String, Object>) ssmResult.getResultObject();
        }

        return null;
    }
    // END 2016/09/21 W.Honda [Unit Test#201,ADD]
}
