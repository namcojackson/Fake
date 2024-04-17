package business.blap.NSAL1140.common;

import static business.blap.NSAL1140.constant.NSAL1140Constant.BUSINESS_ID;
import static business.blap.NSAL1140.constant.NSAL1140Constant.COND_VAL_DS_CONTR_PK;
import static business.blap.NSAL1140.constant.NSAL1140Constant.COND_VAL_GLBL_CMPY_CD;
import static business.blap.NSAL1140.constant.NSAL1140Constant.COND_VAL_SRCH_OPT_APL_ID;
import static business.blap.NSAL1140.constant.NSAL1140Constant.COND_VAL_SRCH_OPT_USR_ID;
import static business.blap.NSAL1140.constant.NSAL1140Constant.COND_VAL_SVC_MDSE_CD;
import static business.blap.NSAL1140.constant.NSAL1140Constant.DATE_FORMAT_YYYYMMDD;
import static business.blap.NSAL1140.constant.NSAL1140Constant.DOWNLOAD_LIMIT_COUNT;
import static business.blap.NSAL1140.constant.NSAL1140Constant.FMSG_COL_LENGTH;
import static business.blap.NSAL1140.constant.NSAL1140Constant.MSG_KIND_ERROR;
import static business.blap.NSAL1140.constant.NSAL1140Constant.NSAM0006I;
import static business.blap.NSAL1140.constant.NSAL1140Constant.NSAM0024W;
import static business.blap.NSAL1140.constant.NSAL1140Constant.NSAM0194I;
import static business.blap.NSAL1140.constant.NSAL1140Constant.NZZM0002I;
import static business.blap.NSAL1140.constant.NSAL1140Constant.ROWNUM_B;
import static business.blap.NSAL1140.constant.NSAL1140Constant.SCREEN_ID;
import static business.blap.NSAL1140.constant.NSAL1140Constant.STR_COMMA;
import static business.blap.NSAL1140.constant.NSAL1140Constant.SVC_SPLY_ENFC_APRV;
import static business.blap.NSAL1140.constant.NSAL1140Constant.THRU_DT_LIMIT;
import static business.blap.NSAL1140.constant.NSAL1140Constant.ZZZM9003I;
import static com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil.isValidDate;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCDateItem;
import parts.common.EZDMsg;
import parts.common.EZDPStringItem;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NSAL1140.NSAL1140CMsg;
import business.blap.NSAL1140.NSAL1140Query;
import business.blap.NSAL1140.NSAL1140SMsg;
import business.blap.NSAL1140.NSAL1140_ASMsg;
import business.blap.NSAL1140.constant.NSAL1140Constant;
import business.db.ABUSE_ACTTMsg;
import business.db.ABUSE_ACTTMsgArray;
import business.db.ABUSE_OVWRT_RSNTMsg;
import business.db.ABUSE_OVWRT_RSNTMsgArray;
import business.db.SAVE_SRCH_OPTTMsg;
import business.db.SAVE_SRCH_OPTTMsgArray;
import business.db.SVC_SPLY_CONTR_STSTMsg;
import business.db.SVC_SPLY_CONTR_STSTMsgArray;
import business.file.NSAL1140F00FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Supply Watch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Hitachi         T.Nishimura     Create          N/A
 * 2016/03/25   Hitachi         A.Kohinata      Update          QC#6051
 * 2016/03/29   Hitachi         A.Kohinata      Update          QC#6053
 * 2017/02/06   Hitachi         N.Arai          Update          QC#17197
 * 2017/05/24   Hitachi         K.Kitachi       Update          QC#18315
 *</pre>
 */
public class NSAL1140CommonLogic {

    /**
     * clearMsgForSearch.
     * @param cMsg NSAL1140CMsg
     * @param sMsg NSAL1140SMsg
     */
    public static void clearMsgForSearch(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        cMsg.svcSplyContrStsCd_1V.clear();
        cMsg.xxChkBox_F.clear();
        cMsg.ovwrtAbuseFlg_2V.clear();
        cMsg.psnCd_1V.clear();
        cMsg.abuseOvwrtRsnCd_1V.clear();
        cMsg.svcSplyExprDt.clear();
        cMsg.abuseVarPct.clear();
        cMsg.abuseActCd_1V.clear();
        cMsg.abuseActCmntTxt.clear();
        ZYPTableUtil.clear(sMsg.A);
    }

    /**
     * clearMsgForInit.
     * @param cMsg NSAL1140CMsg
     * @param sMsg NSAL1140SMsg
     */
    public static void clearMsgForInit(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg) {
        cMsg.abuseBcktCd_1V.clear();
        cMsg.shipToCustAcctCd_01.clear();
        cMsg.dsAcctNm_01.clear();
        cMsg.abuseFlg_1V.clear();
        cMsg.ovwrtAbuseFlg_1V.clear();
        cMsg.svcContrBrCd.clear();
        cMsg.dsAcctGrpNm.clear();
        cMsg.procDt_01.setValue(getMonthFirstDateStr(cMsg));
        cMsg.procDt_02.setValue(getMonthLastDateStr(cMsg));
        cMsg.srchOptPk_1V.clear();
        cMsg.srchOptNm_02.clear();
        cMsg.dsContrEdiCd_1V.clear();
        // del start 2016/02/24 CSA Defect#4122
        // cMsg.dsContrNum.clear();
        // del end 2016/02/24 CSA Defect#4122
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        cMsg.svcSplyContrStsCd_1V.clear();
        cMsg.xxChkBox_F.clear();
        cMsg.ovwrtAbuseFlg_2V.clear();
        cMsg.psnCd_1V.clear();
        cMsg.abuseOvwrtRsnCd_1V.clear();
        cMsg.svcSplyExprDt.clear();
        cMsg.abuseVarPct.clear();
        cMsg.abuseActCd_1V.clear();
        cMsg.abuseActCmntTxt.clear();
        ZYPTableUtil.clear(sMsg.A);
    }

    private static String getMonthFirstDateStr(NSAL1140CMsg cMsg) {
        // Present Date
        String salesDate = cMsg.slsDt.getValue();
        Date dt = null;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD);
        try {
            dt = sdf.parse(salesDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            cal.set(Calendar.DATE, 1);
            dt = new Date(cal.getTimeInMillis());
        } catch (ParseException e) {
            return null;
        }
        // Set first date of Month

        return sdf.format(dt);
    }

    private static String getMonthLastDateStr(NSAL1140CMsg cMsg) {
        // Present Date
        String salesDate = cMsg.slsDt.getValue();
        Date dt = null;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD);
        try {
            dt = sdf.parse(salesDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            cal.set(Calendar.DATE, 1);
            int max = cal.getActualMaximum(Calendar.DATE);
            // Set Last date of Month
            cal.set(Calendar.DATE, max);
            dt = new Date(cal.getTimeInMillis());
        } catch (ParseException e) {
            return null;
        }

        return sdf.format(dt);
    }

    /**
     * createPullDown.
     * @param cMsg NSAL1140CMsg
     * @param srchOptUsrId String
     */
    public static void createPullDown(NSAL1140CMsg cMsg, String srchOptUsrId) {
        // header
        // Bucket
        createBucketPullDown(cMsg);
        // Enforcement Flag
        createEnforcementFlagPullDown(cMsg);
        // Overwrite Flag
        createOverwriteFlagPullDown(cMsg);
        // Contract EDI
        ZYPCodeDataUtil.createPulldownList("DS_CONTR_EDI", cMsg.dsContrEdiCd_1C, cMsg.dsContrEdiDescTxt_1D);
        // Saved Search Options
        createSavedSearchOptionsPullDown(cMsg, srchOptUsrId);
        // footer
        // Status
        createStatusPullDown(cMsg);
        // Overwrite Enforcement
        createOverwriteEnforcementPullDown(cMsg);
        // Approver
        createApproverPullDown(cMsg);
        // Overwrite Reason
        createOverwriteReasonPullDown(cMsg);
        // Action
        createActionPullDown(cMsg);

    }

    private static void createOverwriteEnforcementPullDown(NSAL1140CMsg cMsg) {
        // empty,Yes,No
        ZYPPulldownValueSetter.insertFirstData("N", cMsg.ovwrtAbuseFlg_2C, "N", cMsg.ovwrtAbuseFlg_2D);
        ZYPPulldownValueSetter.insertFirstData("Y", cMsg.ovwrtAbuseFlg_2C, "Y", cMsg.ovwrtAbuseFlg_2D);
    }

    private static void createActionPullDown(NSAL1140CMsg cMsg) {
        // all record in ABUSE_ACT and empty
        ABUSE_ACTTMsg inMsg = new ABUSE_ACTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue(COND_VAL_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ABUSE_ACTTMsgArray tMsgAry = (ABUSE_ACTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "abuseActCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "abuseActDescTxt");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.abuseActCd_1C, cMsg.abuseActDescTxt_1D);

    }

    private static void createOverwriteReasonPullDown(NSAL1140CMsg cMsg) {
        // all record in ABUSE_OVWRT_RSN and empty
        ABUSE_OVWRT_RSNTMsg inMsg = new ABUSE_OVWRT_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue(COND_VAL_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ABUSE_OVWRT_RSNTMsgArray tMsgAry = (ABUSE_OVWRT_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "abuseOvwrtRsnCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "abuseOvwrtRsnNm");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.abuseOvwrtRsnCd_1C, cMsg.abuseOvwrtRsnNm_1D);

    }

    private static void createApproverPullDown(NSAL1140CMsg cMsg) {
        // all Approver record SVC_BR_RESRC_RELN and empty
        // START 2017/05/24 K.Kitachi [QC#18315, MOD]
//        SVC_BR_RESRC_RELNTMsg inMsg = new SVC_BR_RESRC_RELNTMsg();
//        inMsg.setSQLID("002");
//        inMsg.setConditionValue(COND_VAL_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
//        inMsg.setConditionValue("svcOrgFuncRoleTpCd01", SVC_SPLY_ENFC_APRV);
//        SVC_BR_RESRC_RELNTMsgArray tMsgAry = (SVC_BR_RESRC_RELNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
//
//        Map<String, String> tMsgKeys = new HashMap<String, String>();
//        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "psnCd");
//        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "psnCd");
//        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.psnCd_1C, cMsg.psnCd_1D);

        int index = 0;
        List<Map<String, Object>> mapList = getApproverPulldownList(cMsg);
        for (Map<String, Object> map : mapList) {
            setValue(cMsg.psnCd_1C.no(index), (String) map.get("PSN_CD"));
            setValue(cMsg.fullPsnNm_1D.no(index), (String) map.get("FULL_PSN_NM"));
            index++;
        }
        // END 2017/05/24 K.Kitachi [QC#18315, MOD]
    }

    private static void createStatusPullDown(NSAL1140CMsg cMsg) {
        // all record in SVC_SPLY_CONTR_STS with empty
        SVC_SPLY_CONTR_STSTMsg inMsg = new SVC_SPLY_CONTR_STSTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue(COND_VAL_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        SVC_SPLY_CONTR_STSTMsgArray tMsgAry = (SVC_SPLY_CONTR_STSTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcSplyContrStsCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcSplyContrStsDescTxt");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcSplyContrStsCd_1C, cMsg.svcSplyContrStsDescTxt_1D);

    }

    private static void createSavedSearchOptionsPullDown(NSAL1140CMsg cMsg, String srchOptUsrId) {
        cMsg.srchOptPk_1C.clear();
        cMsg.srchOptNm_1D.clear();
        SAVE_SRCH_OPTTMsg sso = new SAVE_SRCH_OPTTMsg();
        sso.setSQLID("001");
        sso.setConditionValue(COND_VAL_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        sso.setConditionValue(COND_VAL_SRCH_OPT_APL_ID, BUSINESS_ID);
        sso.setConditionValue(COND_VAL_SRCH_OPT_USR_ID, srchOptUsrId);
        SAVE_SRCH_OPTTMsgArray result = (SAVE_SRCH_OPTTMsgArray) EZDTBLAccessor.findByCondition(sso);

        for (int i = 0; i < result.getValidCount(); i++) {
            setValue(cMsg.srchOptPk_1C.no(i), result.no(i).srchOptPk);
            setValue(cMsg.srchOptNm_1D.no(i), result.no(i).srchOptNm);
        }

    }

    private static void createOverwriteFlagPullDown(NSAL1140CMsg cMsg) {
        // empty,Yes,No
        ZYPPulldownValueSetter.insertFirstData("N", cMsg.ovwrtAbuseFlg_1C, "N", cMsg.ovwrtAbuseFlg_1D);
        ZYPPulldownValueSetter.insertFirstData("Y", cMsg.ovwrtAbuseFlg_1C, "Y", cMsg.ovwrtAbuseFlg_1D);
    }

    private static void createEnforcementFlagPullDown(NSAL1140CMsg cMsg) {
        // empty,Yes,No
        ZYPPulldownValueSetter.insertFirstData("N", cMsg.abuseFlg_1C, "N", cMsg.abuseFlg_1D);
        ZYPPulldownValueSetter.insertFirstData("Y", cMsg.abuseFlg_1C, "Y", cMsg.abuseFlg_1D);
    }

    private static void createBucketPullDown(NSAL1140CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList("ABUSE_BCKT", cMsg.abuseBcktCd_1C, cMsg.abuseBcktDescTxt_1D);
    }

    /**
     * @param cMsg NSAL1140CMsg
     * @return boolean
     */
    public static boolean isErrorSearchCondition(NSAL1140CMsg cMsg) {
        boolean result = true;
        if (ZYPCommonFunc.hasValue(cMsg.abuseBcktCd_1V)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(cMsg.abuseFlg_1V)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(cMsg.ovwrtAbuseFlg_1V)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(cMsg.svcContrBrCd)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(cMsg.dsContrEdiCd_1V)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(cMsg.shipToCustAcctCd_01)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(cMsg.dsAcctGrpNm)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(cMsg.dsContrNum)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(cMsg.dsAcctNm_01)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(cMsg.procDt_01)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(cMsg.procDt_02)) {
            result = false;
        } else {
            result = true;
        }
        if (result) {
            cMsg.setMessageInfo(NSAL1140Constant.NSAM0017E);
        }
        return result;
    }

    /**
     * getSearchData.
     * @param cMsg NSAL1140CMsg
     * @param sMsg NSAL1140SMsg
     */
    public static void getSearchData(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg) {
        Map<String, Object> sc = createSearchCondition(cMsg, sMsg.A.length() + 1);
        S21SsmEZDResult ssmResult = NSAL1140Query.getInstance().getSearchData(sc, sMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > 1000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NSAM0024W, new String[] {Integer.toString(sMsg.A.length()), Integer.toString(sMsg.A.length()) });
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
        } else {
            // No result
            cMsg.setMessageInfo(NSAM0194I);
        }
    }

    /**
     * createSearchCondition.
     * @param cMsg NSAL1140CMsg
     * @param cnt int
     * @return Map<String, Object>
     */
    public static Map<String, Object> createSearchCondition(NSAL1140CMsg cMsg, int cnt) {
        Map<String, Object> sc = new HashMap<String, Object>();
        sc.put(COND_VAL_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue()); // GLBL_CMPY_CD
        // =
        // glblCmpyCd
        sc.put("procDt01", cMsg.procDt_01.getValue()); // PROC_DT >=
        // procDt01
        sc.put("procDt02", cMsg.procDt_02.getValue()); // PROC_DT <=
        // procDt02
        sc.put("abuseBcktCd01", cMsg.abuseBcktCd_1V.getValue()); // ABUSE_BCKT_CD
        sc.put("abuseFlg01", cMsg.abuseFlg_1V.getValue()); // ABUSE_FLG
        sc.put("ovwrtAbuseFlg01", cMsg.ovwrtAbuseFlg_1V.getValue()); // OVWRT_ABUSE_FLG
        sc.put("svcContrBrCd01", cMsg.svcContrBrCd.getValue()); // SVC_CONTR_BR_CD
        sc.put("dsContrEdiCd01", cMsg.dsContrEdiCd_1V.getValue()); // DS_CONTR_EDI_CD
        sc.put("shipToCustAcctCdList", getCustomerNumList(cMsg)); // SHIP_TO_CUST_ACCT_CD
        // IN
        // Customer#
        sc.put("dsContrNumList", getContractNumList(cMsg)); // DS_CONTR_NUM
        // IN
        // Contract#
        sc.put("dsAcctNm01", cMsg.dsAcctNm_01.getValue()); // DS_ACCT_NM
        // LIKE
        sc.put("dsAcctGrpNm01", cMsg.dsAcctGrpNm.getValue()); // DS_ACCT_NM
        // LIKE
        sc.put("slsDt01", cMsg.slsDt.getValue()); // EFF_FROM_DT >=
        // slsDt
        sc.put("slsDt02", cMsg.slsDt.getValue()); // EFF_THRU_DT >=
        // slsDt
        sc.put("rowNum", cnt);
        sc.put("thruDtLimit", THRU_DT_LIMIT);
        return sc;

    }

    private static List<String> getCustomerNumList(NSAL1140CMsg cMsg) {
        if (hasValue(cMsg.shipToCustAcctCd_01)) {
            String[] customerArray = cMsg.shipToCustAcctCd_01.getValue().split(",");
            List<String> customerList = new ArrayList<String>();
            for (int i = 0; i < customerArray.length; i++) {
                customerList.add(customerArray[i]);
            }

            return customerList;
        }
        return null;
    }

    private static List<String> getContractNumList(NSAL1140CMsg cMsg) {
        if (hasValue(cMsg.dsContrNum)) {
            String[] contractArray = cMsg.dsContrNum.getValue().split(",");
            List<String> contractList = new ArrayList<String>();
            for (int i = 0; i < contractArray.length; i++) {
                contractList.add(contractArray[i]);
            }

            return contractList;
        }
        return null;
    }

    /**
     * writeCsvFile.
     * @param cMsg NSAL1140CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFile(NSAL1140CMsg cMsg, ResultSet rs) throws SQLException {

        NSAL1140F00FMsg fMsg = new NSAL1140F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(getFMsgColOrder(new int[FMSG_COL_LENGTH]));

        // write header
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);

        // write contents
        do {
            if (rs.getRow() > DOWNLOAD_LIMIT_COUNT) {
                cMsg.setMessageInfo(NSAM0006I, new String[] {Integer.toString(DOWNLOAD_LIMIT_COUNT) });
                break;
            }
            // result set -> fMsg
            setValue(fMsg.abuseFlg, rs.getString("ABUSE_FLG"));
            setValue(fMsg.ovwrtAbuseFlg, rs.getString("OVWRT_ABUSE_FLG"));
            setValue(fMsg.abuseOvwrtRsnDescTxt, rs.getString("ABUSE_OVWRT_RSN_DESC_TXT"));
            setValue(fMsg.othContrAbuseFlg, rs.getString("OTH_CONTR_ABUSE_FLG"));
            setValue(fMsg.xxDtTm_PD, formatDate(rs.getString("PROC_DT")));
            setValue(fMsg.termCondChkFlg, rs.getString("TERM_COND_CHK_FLG"));
            setValue(fMsg.xxDtTm_TC, formatDate(rs.getString("TERM_COND_CHK_DT")));
            setValue(fMsg.shipToCustAcctCd, rs.getString("SHIP_TO_CUST_ACCT_CD"));
            setValue(fMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
            setValue(fMsg.dsAcctGrpDescTxt, rs.getString("DS_ACCT_GRP_DESC_TXT"));
            setValue(fMsg.dsContrNum, rs.getString("DS_CONTR_NUM"));
            setValue(fMsg.svcContrBrDescTxt, rs.getString("SVC_CONTR_BR_DESC_TXT"));
            setValue(fMsg.svcRgDescTxt, rs.getString("SVC_RG_DESC_TXT"));
            setValue(fMsg.dsContrEdiDescTxt, rs.getString("DS_CONTR_EDI_DESC_TXT"));
            setValue(fMsg.svcCovTmplTpDescTxt, rs.getString("SVC_COV_TMPL_TP_DESC_TXT"));
            setValue(fMsg.noMainUnitCnt, rs.getBigDecimal("NO_MAIN_UNIT_CNT"));
            setValue(fMsg.bllgCycleDescTxt, rs.getString("BLLG_CYCLE_DESC_TXT"));
            setValue(fMsg.xxDtTm_CS, formatDate(rs.getString("CONTR_VRSN_EFF_FROM_DT")));
            setValue(fMsg.bwPrrtQty, rs.getBigDecimal("BW_PRRT_QTY"));
            setValue(fMsg.colorPrrtQty, rs.getBigDecimal("COLOR_PRRT_QTY"));
            setValue(fMsg.bwUsedQty, rs.getBigDecimal("BW_USED_QTY"));
            setValue(fMsg.colorUsedQty, rs.getBigDecimal("COLOR_USED_QTY"));
            setValue(fMsg.abuseVarPct, rs.getBigDecimal("ABUSE_VAR_PCT"));
            setValue(fMsg.totRevAmt, rs.getBigDecimal("TOT_REV_AMT"));
            setValue(fMsg.totCostAmt, rs.getBigDecimal("TOT_COST_AMT"));
            setValue(fMsg.prftAmt, rs.getBigDecimal("PRFT_AMT"));
            setValue(fMsg.abuseBcktCd, rs.getString("ABUSE_BCKT_CD"));
            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    private static String formatDate(String date) {
        if (hasValue(date)) {
         // START 2017/02/06 N.Arai [QC#17197, MOD]
            // String result = convertDateFormat(date);
            String result = ZYPDateUtil.formatEzd8ToDisp(date);
         // END 2017/02/06 N.Arai [QC#17197, MOD]

            if (result != null) {
                date = result;
            }
        }
        return date;
    }

 // START 2017/02/06 N.Arai [QC#17197, MOD]
//    private static String convertDateFormat(String strDate) {
//        SimpleDateFormat dateFormatIn = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD);
//        SimpleDateFormat dateFormatOut = new SimpleDateFormat("dd-MMM-yy", Locale.US);
//        String result = null;
//        try {
//            Date resultDate = dateFormatIn.parse(strDate);
//            result = dateFormatOut.format(resultDate);
//
//        } catch (ParseException e) {
//            return result;
//        }
//        return result;
//    }
 // END 2017/02/06 N.Arai [QC#17197, MOD]

    private static void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSAL1140F00FMsg fMsg, NSAL1140CMsg cMsg) {

        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        final String[] csvHeader = new String[] {labelConv.convLabel2i18nLabel(SCREEN_ID, "WATCH LIST INFO,,,,,,,CONTRACT INFO,,,,,,,,,,,USED VS. ALLOWED INFO,,,,,,,,BUCKETS\r\nSupply Enforcement"),
                labelConv.convLabel2i18nLabel(SCREEN_ID, "Overwrite Enforcement"), labelConv.convLabel2i18nLabel(SCREEN_ID, "Overwrite Reason"), labelConv.convLabel2i18nLabel(SCREEN_ID, "Other Contract On Enforcement"),
                labelConv.convLabel2i18nLabel(SCREEN_ID, "Process Date"), labelConv.convLabel2i18nLabel(SCREEN_ID, "T&C's"), labelConv.convLabel2i18nLabel(SCREEN_ID, "T&C's Dt"), labelConv.convLabel2i18nLabel(SCREEN_ID, "Customer#"),
                labelConv.convLabel2i18nLabel(SCREEN_ID, "Customer Name"), labelConv.convLabel2i18nLabel(SCREEN_ID, "Profile Name"), labelConv.convLabel2i18nLabel(SCREEN_ID, "Contract#"), labelConv.convLabel2i18nLabel(SCREEN_ID, "Branch"),
                labelConv.convLabel2i18nLabel(SCREEN_ID, "Region"), labelConv.convLabel2i18nLabel(SCREEN_ID, "Contract EDI"), labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Program"),
                labelConv.convLabel2i18nLabel(SCREEN_ID, "No of Active Machines"), labelConv.convLabel2i18nLabel(SCREEN_ID, "Usage Bill Cycle"), labelConv.convLabel2i18nLabel(SCREEN_ID, "Min Contract Start Date"),
                labelConv.convLabel2i18nLabel(SCREEN_ID, "BW Allowed"), labelConv.convLabel2i18nLabel(SCREEN_ID, "CLR Allowed"), labelConv.convLabel2i18nLabel(SCREEN_ID, "BW Used"), labelConv.convLabel2i18nLabel(SCREEN_ID, "CLR Used"),
                labelConv.convLabel2i18nLabel(SCREEN_ID, "Variance% (Used / Allowed)"), labelConv.convLabel2i18nLabel(SCREEN_ID, "Supply Revenue"), labelConv.convLabel2i18nLabel(SCREEN_ID, "Supply Cost"),
                labelConv.convLabel2i18nLabel(SCREEN_ID, "Profit"), labelConv.convLabel2i18nLabel(SCREEN_ID, "Current") };
        csvOutFile.writeHeader(csvHeader, fMsg, getFMsgColOrder(new int[FMSG_COL_LENGTH]));
    }

    private static int[] getFMsgColOrder(int[] fMsgColOrder) {
        for (int i = 0; i < fMsgColOrder.length; i++) {
            fMsgColOrder[i] = i;
        }
        return fMsgColOrder;
    }

    /**
     * callNszc0330forSaveSearch.
     * @param cMsg NSAL1140CMsg
     * @param sMsg NSAL1140SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forSaveSearch(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg, String srchOptUsrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!hasValue(cMsg.srchOptNm_02) //
                || isSameSaveSearchName(cMsg)) {
            setValue(pMsg.srchOptPk, cMsg.srchOptPk_1V);
        }
        if (hasValue(cMsg.srchOptNm_02)) {
            setValue(pMsg.srchOptNm, cMsg.srchOptNm_02);
        } else {
            setSelectSaveSearchName(pMsg, cMsg);
        }
        setValue(pMsg.srchOptAplId, BUSINESS_ID);
        setValue(pMsg.srchOptUsrId, srchOptUsrId);

        setValue(pMsg.srchOptTxt_01, cMsg.abuseBcktCd_1V.getValue());
        setValue(pMsg.srchOptTxt_02, cMsg.shipToCustAcctCd_01.getValue());
        setValue(pMsg.srchOptTxt_03, cMsg.dsAcctNm_01.getValue());
        setValue(pMsg.srchOptTxt_04, cMsg.abuseFlg_1V.getValue());
        setValue(pMsg.srchOptTxt_05, cMsg.ovwrtAbuseFlg_1V.getValue());
        setValue(pMsg.srchOptTxt_06, cMsg.svcContrBrCd.getValue());
        setValue(pMsg.srchOptTxt_07, cMsg.dsAcctGrpNm.getValue());
        setValue((EZDPStringItem) pMsg.srchOptTxt_08, cMsg.procDt_01.getValue());
        setValue((EZDPStringItem) pMsg.srchOptTxt_09, cMsg.procDt_02.getValue());
        setValue(pMsg.srchOptTxt_10, cMsg.dsContrEdiCd_1V.getValue());
        setValue(pMsg.srchOptTxt_11, cMsg.dsContrNum.getValue());
        if (callNszc0330(cMsg, pMsg)) {
            createSavedSearchOptionsPullDown(cMsg, srchOptUsrId);
            setValue(cMsg.srchOptPk_1V, pMsg.srchOptPk);
            cMsg.srchOptNm_02.clear();
            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Save Search" });
        }
    }

    private static boolean isSameSaveSearchName(NSAL1140CMsg cMsg) {
        if (!hasValue(cMsg.srchOptPk_1V)) {
            return false;
        }
        if (!hasValue(cMsg.srchOptNm_02)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_1D.length(); i++) {
            if (!hasValue(cMsg.srchOptNm_1D.no(i))) {
                return false;
            }
            if (cMsg.srchOptPk_1V.getValue().compareTo(cMsg.srchOptPk_1C.no(i).getValue()) == 0) {
                if (cMsg.srchOptNm_02.getValue().equals(cMsg.srchOptNm_1D.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * isExistSaveSearchName
     * @param cMsg NSAL1140CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NSAL1140CMsg cMsg) {
        if (!hasValue(cMsg.srchOptNm_02)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_1D.length(); i++) {
            if (!hasValue(cMsg.srchOptNm_1D.no(i))) {
                return false;
            }
            if (cMsg.srchOptNm_02.getValue().equals(cMsg.srchOptNm_1D.no(i).getValue())) {
                if (hasValue(cMsg.srchOptPk_1V) //
                        && cMsg.srchOptPk_1V.getValue().compareTo(cMsg.srchOptPk_1C.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName.
     * @param pMsg NSZC033001PMsg
     * @param cMsg NSAL1140CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NSAL1140CMsg cMsg) {
        if (!hasValue(cMsg.srchOptPk_1V)) {
            return;
        }

        for (int i = 0; i < cMsg.srchOptNm_1D.length(); i++) {
            if (!hasValue(cMsg.srchOptNm_1D.no(i))) {
                return;
            }
            if (cMsg.srchOptPk_1V.getValue().compareTo(cMsg.srchOptPk_1C.no(i).getValue()) == 0) {
                setValue(pMsg.srchOptNm, cMsg.srchOptNm_1D.no(i));
            }
        }
        return;
    }

    /**
     * callNszc0330forDeleteSearch.
     * @param cMsg NSAL1140CMsg
     * @param sMsg NSAL1140SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forDeleteSearch(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg, String srchOptUsrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        setValue(pMsg.srchOptPk, cMsg.srchOptPk_1V);
        setValue(pMsg.srchOptAplId, BUSINESS_ID);

        if (callNszc0330(cMsg, pMsg)) {
            createSavedSearchOptionsPullDown(cMsg, srchOptUsrId);
            cMsg.srchOptNm_02.clear();
            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Delete Search" });
        }
    }

    private static boolean callNszc0330(NSAL1140CMsg cMsg, NSZC033001PMsg pMsg) {
        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    cMsg.setMessageInfo(msgId);
                    if (msgId.endsWith(MSG_KIND_ERROR)) {
                        cMsg.srchOptPk_1V.setErrorInfo(1, msgId);
                        cMsg.srchOptNm_02.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static void setSrchOptForDate(EZDCDateItem cItem, String pItem) {
        if (isValidDate(pItem, DATE_FORMAT_YYYYMMDD)) {
            setValue(cItem, pItem);
        } else {
            cItem.clear();
        }
    }

    /**
     * callNszc0330forSearchOption.
     * @param cMsg NSAL1140CMsg
     * @param sMsg NSAL1140SMsg
     * @param srchOptUsrId String
     */

    public static void callNszc0330forSearchOption(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg, String srchOptUsrId) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        setValue(pMsg.srchOptUsrId, srchOptUsrId);
        setValue(pMsg.srchOptPk, cMsg.srchOptPk_1V);
        setValue(pMsg.srchOptAplId, BUSINESS_ID);

        if (!callNszc0330(cMsg, pMsg)) {
            return;
        }
        setValue(cMsg.abuseBcktCd_1V, pMsg.srchOptTxt_01.getValue());
        setValue(cMsg.shipToCustAcctCd_01, pMsg.srchOptTxt_02.getValue());
        setValue(cMsg.dsAcctNm_01, pMsg.srchOptTxt_03.getValue());
        setValue(cMsg.abuseFlg_1V, pMsg.srchOptTxt_04.getValue());
        setValue(cMsg.ovwrtAbuseFlg_1V, pMsg.srchOptTxt_05.getValue());
        setValue(cMsg.svcContrBrCd, pMsg.srchOptTxt_06.getValue());
        setValue(cMsg.dsAcctGrpNm, pMsg.srchOptTxt_07.getValue());
        setSrchOptForDate(cMsg.procDt_01, pMsg.srchOptTxt_08.getValue());
        setSrchOptForDate(cMsg.procDt_02, pMsg.srchOptTxt_09.getValue());
        setValue(cMsg.dsContrEdiCd_1V, pMsg.srchOptTxt_10.getValue());
        setValue(cMsg.dsContrNum, pMsg.srchOptTxt_11.getValue());

    }

    /***
     * Setting the List about Status to "cMsg.B".
     * @param target NSAL1140_ASMsg
     * @param cMsg NSAL1140CMsg
     */
    public static void getFotterData(NSAL1140_ASMsg target, NSAL1140CMsg cMsg) {
        // find List about Status
        getStatusHistory(target, cMsg);

        if (!hasValue(cMsg.svcSplyContrStsCd_1V)) {
            setValue(cMsg.svcSplyContrStsCd_1V, cMsg.B.no(0).svcSplyContrStsCd_B);
            setValue(cMsg.xxChkBox_F, target.termCondChkFlg_A);
        }
    }

    private static void getStatusHistory(NSAL1140_ASMsg target, NSAL1140CMsg cMsg) {
        Map<String, Object> sc = new HashMap<String, Object>();
        sc.put(COND_VAL_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue()); // GLBL_CMPY_CD
        // =
        // glblCmpyCd
        sc.put(COND_VAL_DS_CONTR_PK, target.dsContrPk.getValue()); // DS_CONTR_PK
        // =
        // dsContrPk01
        sc.put(COND_VAL_SVC_MDSE_CD, target.svcPgmMdseCd.getValue()); // SVC_PGM_MDSE_CD
        // =
        // svcPgmMdseCd01
        sc.put("rowNum", ROWNUM_B);

        NSAL1140Query.getInstance().getStatusHistory(sc, cMsg);
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL1140CMsg
     * @param sMsg NSAL1140SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg, int pagenationFrom) {

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
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSAL1140CMsg cMsg, NSAL1140SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    // START 2017/05/24 K.Kitachi [QC#18315, ADD]
    private static List<Map<String, Object>> getApproverPulldownList(NSAL1140CMsg cMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        String constVal = ZYPCodeDataUtil.getVarCharConstValue(SVC_SPLY_ENFC_APRV, cMsg.glblCmpyCd.getValue());
        String[] svcOrgFuncRoleTpCdList = constVal.split(STR_COMMA);
        param.put("svcOrgFuncRoleTpCdList", svcOrgFuncRoleTpCdList);
        return NSAL1140Query.getInstance().getApproverPulldownList(param);
    }
    // END 2017/05/24 K.Kitachi [QC#18315, ADD]
}
