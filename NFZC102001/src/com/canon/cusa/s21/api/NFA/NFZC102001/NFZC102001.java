/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.api.NFA.NFZC102001;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.COA_ACCTTMsg;
import business.db.COA_AFFLTMsg;
import business.db.COA_BRTMsg;
import business.db.COA_CCTMsg;
import business.db.COA_CHTMsg;
import business.db.COA_CMPYTMsg;
import business.db.COA_EXTNTMsg;
import business.db.COA_PRODTMsg;
import business.db.COA_PROJTMsg;
import business.parts.NFZC102001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.usa.s21.integration.service.arcs.integration.nfai9010.data.S21CsawdsNFAI9010Reply;
import com.canon.usa.s21.integration.service.arcs.integration.nfai9010.data.S21CsawdsNFAI9010TenParam;
import com.canon.usa.s21.integration.service.arcs.integration.nfai9010.transaction.FaultConfigurationExp;
import com.canon.usa.s21.integration.service.arcs.integration.nfai9010.transaction.FaultDataExp;
import com.canon.usa.s21.integration.service.arcs.integration.nfai9010.transaction.FaultResourceExp;
import com.canon.usa.s21.integration.service.arcs.integration.nfai9010.wrapper.Nfai9010ServiceProxy;
//END 2016/05/12 S.Fujita [QC#7918,MOD]
/**
 * <pre>
 * GL Code Combination Check API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/20/2015   Fujitsu         T.Tanaka        Create          N/A
 * 12/18/2015   Fujitsu         T.Tanaka        Update          QC#2260
 * 12/18/2015   Fujitsu         T.Tanaka        Update          QC#5798
 * 04/25/2016   Fujitsu         T.Tanaka        Update          QC#5793
 * 05/12/2016   Fujitsu         S.Fujita        Update          QC#7918
 * 05/25/2016   Fujitsu         S.Fujita        Update          QC#7918
 * 09/07/2016   Hitachi         T.Tsuchida      Update          QC#14201
 * 09/27/2016   Hitachi         K.Kojima        Update          QC#14609
 * 11/08/2016   Fujitsu         S.Fujita        Update          QC#15827
 * 11/11/2016   Hitachi         T.Tsuchida      Update          QC#15925
 * 05/18/2017   CITS            R.Shimamoto     Update          QC#18176
 * 07/05/2017   CITS            R.Shimamoto     Update          QC#19433
 * 07/18/2017   CITS            K.Ogino         Update          QC#19433
 * 07/21/2017   Fujitsu         M.Ohno          Update          QC#20036
 * 02/08/2019   Hitachi         H.Umeda         Update          QC#29965
 * 2019/03/25   CITS            K.Ogino         Update          QC#30768
 * </pre>
 */
public class NFZC102001 extends S21ApiCommonBase {

    /** */
    public static final String NFAM0077E = "NFAM0077E";
    /** */
    public static final String NFAM0078E = "NFAM0078E";
    /** */
    public static final String NFAM0079E = "NFAM0079E";
    /** */
    public static final String NFAM0080E = "NFAM0080E";
    /** */
    public static final String NFAM0081W = "NFAM0081W";
    /** */
    public static final String NFAM0082E = "NFAM0082E";
    /** */
    public static final String NFAM0083E = "NFAM0083E";
    /** */
    public static final String NFAM0084E = "NFAM0084E";
    /** */
    public static final String NFAM0085E = "NFAM0085E";
    /** */
    public static final String NFAM0086E = "NFAM0086E";
    /** */
    public static final String NFAM0087E = "NFAM0087E";
    /** */
    public static final String NFAM0088E = "NFAM0088E";
    /** */
    public static final String NFAM0089E = "NFAM0089E";
    /** */
    public static final String NFAM0090E = "NFAM0090E";
    /** */
    public static final String NFAM0091E = "NFAM0091E";
    /** */
    public static final String NFAM0092E = "NFAM0092E";
    /** */
    public static final String NFAM0093E = "NFAM0093E";
    /** */
    public static final String NFAM0094E = "NFAM0094E";
    /** */
    public static final String NFAM0095E = "NFAM0095E";
    /** */
    public static final String NFAM0096E = "NFAM0096E";
    /** */
    public static final String NFAM0097E = "NFAM0097E";
    /** */
    public static final String NFAM0098E = "NFAM0098E";
    /** */
    public static final String NFAM0099E = "NFAM0099E";
    /** */
    public static final String NFAM0100E = "NFAM0100E";
    /** */
    public static final String NFAM0101E = "NFAM0101E";
    /** */
    public static final String NFAM0102W = "NFAM0102W";
    /** */
    public static final String NFAM0103E = "NFAM0103E";
    /** */
    public static final String NFAM0104E = "NFAM0104E";
    /** */
    public static final String NFAM0105E = "NFAM0105E";
    /** */
    public static final String NFAM0106E = "NFAM0106E";
    /** */
    public static final String NFAM0107E = "NFAM0107E";
    /** */
    public static final String NFAM0108E = "NFAM0108E";
    /** */
    public static final String NFAM0109E = "NFAM0109E";
    /** */
    public static final String NFAM0110E = "NFAM0110E";
    /** */
    public static final String NFAM0111E = "NFAM0111E";
    /** */
    public static final String NFAM0112E = "NFAM0112E";
    /** */
    public static final String NFAM0113E = "NFAM0113E";
    /** */
    public static final String NFAM0114E = "NFAM0114E";
    /** */
    public static final String NFAM0115E = "NFAM0115E";
    /** */
    public static final String NFAM0116E = "NFAM0116E";
    /** */
    public static final String NFAM0117E = "NFAM0117E";
    /** */
    public static final String NFAM0118E = "NFAM0118E";
    /** */
    public static final String NFAM0119E = "NFAM0119E";
    /** */
    public static final String NFAM0120E = "NFAM0120E";
    /** */
    public static final String NFAM0121E = "NFAM0121E";
    /** */
    public static final String NFAM0122E = "NFAM0122E";
    /** */
    public static final String NFAM0123E = "NFAM0123E";
    /** */
    public static final String NFAM0124E = "NFAM0124E";
    /** */
    public static final String NFAM0125E = "NFAM0125E";
    /** */
    public static final String NFAM0126E = "NFAM0126E";
    /** */
    public static final String NFAM0127E = "NFAM0127E";
    /** */
    public static final String NFAM0128E = "NFAM0128E";
    /** */
    public static final String NFAM0129E = "NFAM0129E";
    /** */
    public static final String NFAM0130E = "NFAM0130E";
    /** */
    public static final String NFAM0131E = "NFAM0131E";
    /** */
    public static final String NFAM0132E = "NFAM0132E";
    /** */
    public static final String NFAM0133E = "NFAM0133E";
    /** */
    public static final String NFAM0134E = "NFAM0134E";
    /** */
    public static final String NFAM0135E = "NFAM0135E";
    /** */
    public static final String NFAM0136E = "NFAM0136E";
    /** */
    public static final String NFAM0137E = "NFAM0137E";
    /** */
    public static final String NFAM0138E = "NFAM0138E";
    /** */
    public static final String NFAM0139E = "NFAM0139E";
    /** */
    public static final String NFAM0140E = "NFAM0140E";
    /** */
    public static final String NFAM0141E = "NFAM0141E";
    /** */
    public static final String NFAM0142E = "NFAM0142E";
    /** */
    public static final String NFAM0143E = "NFAM0143E";
    /** */
    public static final String NFAM0144E = "NFAM0144E";
    /** */
    public static final String NFAM0145E = "NFAM0145E";
    /** */
    public static final String NFAM0146E = "NFAM0146E";
    /** */
    public static final String NFAM0147E = "NFAM0147E";
    /** */
    public static final String NFAM0148E = "NFAM0148E";
    /** */
    public static final String NFAM0149E = "NFAM0149E";
    /** */
    public static final String NFAM0150E = "NFAM0150E";
    /** */
    public static final String NFAM0151E = "NFAM0151E";
    /** GL Import Error */
    public static final String NFAM0152E = "NFAM0152E";

    /** In Parameter 9 segments is mandatory. */
    public static final String NFAM0153E = "NFAM0153E";

    // 2017/07/21 QC#20036 Mod Start
//    /** is not found */
//    public static final String NFAM0154E = "NFAM0154E";
//
//    /** is security error */
//    public static final String NFAM0155E = "NFAM0155E";

    /** is not found */
    public static final String NFAM0184E = "NFAM0184E";

    /** is not found */
    public static final String NFAM0185E = "NFAM0185E";

    /** is not found */
    public static final String NFAM0186E = "NFAM0186E";

    /** is not found */
    public static final String NFAM0187E = "NFAM0187E";

    /** is not found */
    public static final String NFAM0188E = "NFAM0188E";

    /** is not found */
    public static final String NFAM0189E = "NFAM0189E";

    /** is not found */
    public static final String NFAM0190E = "NFAM0190E";

    /** is not found */
    public static final String NFAM0191E = "NFAM0191E";

    /** is not found */
    public static final String NFAM0192E = "NFAM0192E";

    /** is not found */
    public static final String NFAM0193E = "NFAM0193E";

    /** is not found */
    public static final String NFAM0194E = "NFAM0194E";

    /** is not found */
    public static final String NFAM0195E = "NFAM0195E";

    /** is security error */
    public static final String NFAM0196E = "NFAM0196E";

    /** is security error */
    public static final String NFAM0197E = "NFAM0197E";

    /** is security error */
    public static final String NFAM0198E = "NFAM0198E";

    /** is security error */
    public static final String NFAM0199E = "NFAM0199E";

    /** is security error */
    public static final String NFAM0200E = "NFAM0200E";

    /** is security error */
    public static final String NFAM0201E = "NFAM0201E";

    /** is security error */
    public static final String NFAM0202E = "NFAM0202E";

    /** is security error */
    public static final String NFAM0203E = "NFAM0203E";

    /** is security error */
    public static final String NFAM0204E = "NFAM0204E";

    /** is security error */
    public static final String NFAM0205E = "NFAM0205E";
    // 2017/07/21 QC#20036 Mod End

    // START 2019/02/08 H.Umeda [QC#29965,ADD]
	/** is ARCS connection error */
    public static final String NFAM0207E = "NFAM0207E";
    // END   2019/02/08 H.Umeda [QC#29965,ADD]

    /** */
    public static final String RTN_CD_NORMAL = "0";

    /** */
    public static final String RTN_CD_ERROR = "1";

    /** */
    public static final int DEBUG_MSG_LVL = 8;

    /** */
    public static final String RQST_TP_ARCS_CHECK = "0";
    /** */
    public static final String GL_CMBN_CHECK_ARCS_API = "0";
    /** */
    public static final String GL_CMBN_CHECK_ARCS_API_THRU = "1";
    /** */
    public static final String GL_CMBN_CHECK_THRU = "2";
    /** */
    public static final String GL_CMBN_CHECK_BOTH_THRU = "3";

    /** */
    public static final String COA_ID_CSA = "50528";

    /** */
    public static final String NG_COA_CMPY = "NG_COA_CMPY";
    /** */
    public static final String NG_COA_BR = "NG_COA_BR";
    /** */
    public static final String NG_COA_CC = "NG_COA_CC";
    /** */
    public static final String NG_COA_ACCT = "NG_COA_ACCT";
    /** */
    public static final String NG_COA_PROD = "NG_COA_PROD";
    /** */
    public static final String NG_COA_CH = "NG_COA_CH";
    /** */
    public static final String NG_COA_AFFL = "NG_COA_AFFL";
    /** */
    public static final String NG_COA_PROJ = "NG_COA_PROJ";
    /** */
    public static final String NG_COA_EXTN = "NG_COA_EXTN";

    /** */
    private boolean isFirst = true;
    /** */
    private boolean isAJE   = false;
    /** */
    private String ajeGlCmbnChk = null;

    /** */
    private String frontGlCmbnChk = null;

    /** */
    private String ajeArcsConn = null;

    // START 2016/09/07 T.Tsuchida [QC#14201,ADD]
    /** */
    private String ajeCrsVldCmpyCd = null;
    // END 2016/09/07 T.Tsuchida [QC#14201,ADD]

    /** */
    private boolean is9Segments = true;

    private static String COAID_COA_CMPY = "1";
    private static String COAID_COA_BR = "2";
    private static String COAID_COA_CC = "3";
    private static String COAID_COA_ACCT = "4";
    private static String COAID_COA_PROD = "5";
    private static String COAID_COA_CH = "6";
    private static String COAID_COA_AFFL = "7";
    private static String COAID_COA_PROJ = "8";
    private static String COAID_COA_EXTN = "9";
    private static String COAERRRSNCD_PARAMTER_CHECK = "0";
    private static String COAERRRSNCD_MASTER_CHECK = "1";
    private static String COAERRRSNCD_COMBINATION_CHECK = "2";
    private static String COAERRRSNCD_ARCSAPI_CHECK = "3";

    /** */
    private S21SsmBatchClient ssmBatchClient;

//    /** */
//    private static Logger LOGGER = Logger.getLogger(NFZC102001.class);

    /**
     */
    public NFZC102001() {
        super();
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param param NFZC102001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NFZC102001PMsg param, final ONBATCH_TYPE onBatchType) {

        debugLog("execute : start");

        if (param == null) {
            return;
        }

        // START 2016/09/27 K.Kojima [QC#14609,MOD]
        // StackTraceElement ste =
        // Thread.currentThread().getStackTrace()[2];
        // if (ste.getFileName().contains("NFA")) {
        // isAJE = true;
        // }
        if (ZYPCommonFunc.hasValue(param.bizAppId)) {
            if (param.bizAppId.getValue().contains("NFZ") || param.bizAppId.getValue().contains("NFAB")) {
                isAJE = true;
            }
        }
        // END 2016/09/27 K.Kojima [QC#14609,MOD]

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        /********************************************/
        /** Initial                                 */
        /********************************************/
        if (isFirst) {
            this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
            // QC#30768
            this.ajeGlCmbnChk = ZYPCodeDataUtil.getVarCharConstValue("AJE_GL_CMBN_CHK", param.glblCmpyCd.getValue());
            if (S21StringUtil.isEmpty(this.ajeGlCmbnChk)) {
                msgMap.addXxMsgIdWithPrm(NFAM0184E, new String[]{"VAR_CHAR_CONST AJE_GL_CMBN_CHK" });
//                msgMap.addXxMsgId(NFAM0184E);
                msgMap.flush();
                return;
            }

            this.frontGlCmbnChk = ZYPCodeDataUtil.getVarCharConstValue("FRONT_GL_CMBN_CHK", param.glblCmpyCd.getValue());
            if (S21StringUtil.isEmpty(this.frontGlCmbnChk)) {
                msgMap.addXxMsgIdWithPrm(NFAM0185E, new String[]{"VAR_CHAR_CONST FRONT_GL_CMBN_CHK" });
//                msgMap.addXxMsgId(NFAM0185E);
                msgMap.flush();
                return;
            }

            this.ajeArcsConn = ZYPCodeDataUtil.getVarCharConstValue("AJE_ARCS_CONN", param.glblCmpyCd.getValue());
            if (S21StringUtil.isEmpty(this.ajeArcsConn)) {
                msgMap.addXxMsgIdWithPrm(NFAM0186E, new String[]{"VAR_CHAR_CONST AJE_ARCS_CONN" });
//                msgMap.addXxMsgId(NFAM0186E);
                msgMap.flush();
                return;
            }

            // START 2016/09/07 T.Tsuchida [QC#14201,ADD]
            this.ajeCrsVldCmpyCd = ZYPCodeDataUtil.getVarCharConstValue("AJE_CRS_VLD_CMPY_CD", param.glblCmpyCd.getValue());
            if (S21StringUtil.isEmpty(this.ajeCrsVldCmpyCd)) {
                msgMap.addXxMsgIdWithPrm(NFAM0187E, new String[]{"VAR_CHAR_CONST AJE_CRS_VLD_CMPY_CD" });
//                msgMap.addXxMsgId(NFAM0187E);
                msgMap.flush();
                return;
            }
            // END 2016/09/07 T.Tsuchida [QC#14201,ADD]
            isFirst = false;
        }

        /********************************************/
        /** Parameter Check                         */
        /********************************************/
        if (!checkParam(msgMap)) {
            msgMap.flush();
            return;
        }

        // Set Default Arcs API Check Flag
        if (!ZYPCommonFunc.hasValue(param.xxArcsApiChkFlg)) {
            ZYPEZDItemValueSetter.setValue(param.xxArcsApiChkFlg, param.xxGlCmbnChkFlg);
        }

        // QC#19433 Start
        /****************************************************/
        /** Check Pattern(Master or Master and Combination  */
        /****************************************************/
        int inputCnt = 0;
        if (S21StringUtil.isNotEmpty(param.coaCmpyCd.getValue())) {
            inputCnt++;
        }
        if (S21StringUtil.isNotEmpty(param.coaBrCd.getValue())) {
            inputCnt++;
        }
        if (S21StringUtil.isNotEmpty(param.coaCcCd.getValue())) {
            inputCnt++;
        }
        if (S21StringUtil.isNotEmpty(param.coaAcctCd.getValue())) {
            inputCnt++;
        }
        if (S21StringUtil.isNotEmpty(param.coaProdCd.getValue())) {
            inputCnt++;
        }
        if (S21StringUtil.isNotEmpty(param.coaChCd.getValue())) {
            inputCnt++;
        }
        if (S21StringUtil.isNotEmpty(param.coaAfflCd.getValue())) {
            inputCnt++;
        }
        if (S21StringUtil.isNotEmpty(param.coaProjCd.getValue())) {
            inputCnt++;
        }
        if (S21StringUtil.isNotEmpty(param.coaExtnCd.getValue())) {
            inputCnt++;
        }

        /********************************************/
        /** Master Check                            */
        /********************************************/
        // Input Count < 3 Master Check Onl
        if (inputCnt < 3 || inputCnt > 2) {
            if (param.xxMstChkFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                if (!masterCheck(msgMap)) {
                    msgMap.flush();
                    return;
                }
                /********************************************/
                /* COA GL Segment Not Allow Security Check  */
                /********************************************/
                if (ZYPCommonFunc.hasValue(param.resrcObjNm.getValue())) {
                    if (!notAllwSecCheck(msgMap)) {
                        msgMap.flush();
                        return;
                    }
                }
            }
            if (inputCnt < 3) {
                return;
            }
        }
        // QC#19433 End

        /********************************************/
        /* Only Master Check by VAR_CHAR_CONST      */
        /* Through S21 Combination Check            */
        /********************************************/
        if (isAJE) {
            if (ZYPCommonFunc.hasValue(this.ajeGlCmbnChk)) {
                if (this.ajeGlCmbnChk.equals(GL_CMBN_CHECK_BOTH_THRU)) {
                    return;
                }
            }
        } else {
            if (ZYPCommonFunc.hasValue(this.frontGlCmbnChk)) {
                if (this.frontGlCmbnChk.equals(GL_CMBN_CHECK_BOTH_THRU)) {
                    return;
                }
            }
        }

        /********************************************/
        /**  S21 COA_GL_CMBN                        */
        /**  ARCS API                               */
        /********************************************/
        if (param.xxGlCmbnChkFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {

            // START 05/18/2017 [QC#18176 MOD]
            if (isAJE) {

                ajeProcessCheck(msgMap, onBatchType, param);

            } else {

                frontProcessCheck(msgMap, onBatchType, param);

            }

//            if (!check9SegmentCombination_s21(msgMap, onBatchType)) {
//                if (isAJE) {
//                    if (this.ajeGlCmbnChk.equals(GL_CMBN_CHECK_ARCS_API_THRU)) {
//                        setCombinationError(msgMap, NFAM0151E, COAERRRSNCD_COMBINATION_CHECK);
//                        msgMap.flush();
//                        return;
//                    }
//                } else {
//                    if (this.frontGlCmbnChk.equals(GL_CMBN_CHECK_ARCS_API_THRU)) {
//                        setCombinationError(msgMap, NFAM0151E, COAERRRSNCD_COMBINATION_CHECK);
//                        msgMap.flush();
//                        return;
//                    }
//                }
//                /********************************************/
//                /** ARCS API                                */
//                /********************************************/
//                // START 2016/09/27 K.Kojima [QC#14609,DEL]
//                // if
//                // (param.xxArcsApiChkFlg.getValue().equals(ZYPConstant.FLG_ON_Y))
//                // {
//                // END 2016/09/27 K.Kojima [QC#14609,DEL]
//                    if (this.is9Segments) {
//                        check9SegmentCombination_ARCS(msgMap, onBatchType);
//                        if (msgMap.getMsgMgr().isXxMsgId()) {
//                            msgMap.flush();
//                        }
//                        return;
//                    } else {
//                        /* Combination Error */
//                        setCombinationError(msgMap, NFAM0151E, COAERRRSNCD_COMBINATION_CHECK);
//                        msgMap.flush();
//                        return;
//                    }
//                // START 2016/09/27 K.Kojima [QC#14609,DEL]
//                // }
//                // END 2016/09/27 K.Kojima [QC#14609,DEL]
//            }
            // END 05/18/2017 [QC#18176 MOD]
        }
        debugLog("execute : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param params List<NFZC102001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */

    public void execute(final List<NFZC102001PMsg> params, final ONBATCH_TYPE onBatchType) {

        if (params != null) {
            for (NFZC102001PMsg msg : params) {
                this.is9Segments = true;
                execute(msg, onBatchType);
            }
            return;
        }
    }

    /**
     * 
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     * @return Boolean
     */
    public boolean check9SegmentCombination_s21(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        // START 05/18/2017 [QC#18176 MOD]
//        // QC#5793
//        if (isAJE) {
//            if (this.ajeGlCmbnChk.equals(GL_CMBN_CHECK_THRU)) {
//                return true;
//            }
//        } else {
//            if (this.frontGlCmbnChk.equals(GL_CMBN_CHECK_THRU)) {
//                return true;
//            }
//        }
        // END 05/18/2017 [QC#18176 MOD]

        NFZC102001PMsg param = (NFZC102001PMsg) msgMap.getPmsg();

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("coaCmpyCd", param.coaCmpyCd.getValue());
        queryParam.put("coaBrCd", param.coaBrCd.getValue());
        queryParam.put("coaCcCd", param.coaCcCd.getValue());
        queryParam.put("coaAcctCd", param.coaAcctCd.getValue());
        queryParam.put("coaProdCd", param.coaProdCd.getValue());
        queryParam.put("coaChCd", param.coaChCd.getValue());
        queryParam.put("coaAfflCd", param.coaAfflCd.getValue());

        if (param.coaProjCd.getValue().length() == 2) {
            String coaProjCd = "00";
            coaProjCd = coaProjCd.concat(param.coaProjCd.getValue());
            queryParam.put("coaProjCd", coaProjCd);
        } else {
            queryParam.put("coaProjCd", param.coaProdCd.getValue());
        }
              queryParam.put("coaExtnCd", param.coaExtnCd.getValue());

        Map ssmRes = (Map) ssmBatchClient.queryObject("check9Segment", queryParam);
        BigDecimal count = (BigDecimal) ssmRes.get("COUNT");
        if (count.equals(BigDecimal.ZERO)) {
//            msgMap.addXxMsgId(NFAM0151E);
//            param.setReturnCode(RTN_CD_ERROR);
//            msgMap.setPmsg(param);
            return false;
        }
        return true;
    }

    /**
     * 
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    public void check9SegmentCombination_ARCS(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        // START 2016/05/12 S.Fujita [QC#7918,MOD]
//        try {
//            EsbMessage esbMessage = new EsbMessage();
//            String result = new NFZC102001().invoke(esbMessage, msgMap);
//        } catch (Exception e) {
//            System.exit(1);
//        }

        String responseStr = null;

        //TODO Comment out on local execution.
        S21CsawdsNFAI9010Reply response = invokeTenParam(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            msgMap.flush();
            return;
        }

        responseStr = response.getCvrValidation();
        // START 2016/05/25 S.Fujita [QC#7918,MOD]
//        if (ZYPCommonFunc.hasValue(responseStr)) {
//            msgMap.addXxMsgId(NFAM0152E);
//            msgMap.addXxMsgIdWithPrm(NFAM0152E, new String[]{responseStr});
//        }

        // START 2016/11/08 S.Fujita [QC#15827,DEL]
//        S21InfoLogOutput.println("s21CsawdsNFAI9010TenParam Response");
//        S21InfoLogOutput.println(response.getCvrValidation());
        // END   2016/11/08 S.Fujita [QC#15827,DEL]

        if(ZYPCommonFunc.hasValue(responseStr)) {
             if(!isNum(responseStr)) {
                 msgMap.addXxMsgIdWithPrm(NFAM0152E, new String[]{responseStr});
             }
        }
        // END   2016/05/25 S.Fujita [QC#7918,MOD]
        // END 2016/05/12 S.Fujita [QC#7918,MOD]
    }

    // START 2016/05/25 S.Fujita [QC#7918,ADD]
    /**
     * isNum
     * @param number
     * @return
     */
    static boolean isNum(String number) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^[0-9]*$");
        java.util.regex.Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
    // END   2016/05/25 S.Fujita [QC#7918,ADD]

    // START 2016/05/12 S.Fujita [QC#7918,MOD]
    /**
     * 
     * @param msgMap
     * @return
     */
    private S21CsawdsNFAI9010Reply invokeTenParam(S21ApiMessageMap msgMap) {

        NFZC102001PMsg param = (NFZC102001PMsg) msgMap.getPmsg();

        // START 2016/09/07 T.Tsuchida [QC#14201,MOD]
//        S21CsawdsNFAI9010TenParam request = new S21CsawdsNFAI9010TenParam();
//        request.setPCoaId(COA_ID_CSA);
//        request.setPSeg1(param.coaCmpyCd.getValue());
//        request.setPSeg2(param.coaBrCd.getValue());
//        request.setPSeg3(param.coaCcCd.getValue());
//        request.setPSeg4(param.coaAcctCd.getValue());
//        request.setPSeg5(param.coaProdCd.getValue());
//        request.setPSeg6(param.coaChCd.getValue());
//        request.setPSeg7(param.coaAfflCd.getValue());
//        // START 2016/05/25 S.Fujita [QC#7918,MOD]
////        request.setPSeg8(param.coaProjCd.getValue());
//        if(param.coaProjCd.getValue().length() == 2) {
//            String coaProjCd = "00";
//            coaProjCd = coaProjCd.concat(param.coaProjCd.getValue());
//            request.setPSeg8(coaProjCd);
//        } else {
//            request.setPSeg8(param.coaProjCd.getValue());
//        }
//        // END   2016/05/25 S.Fujita [QC#7918,MOD]
//        request.setPSeg9(param.coaExtnCd.getValue());
//
//        S21CsawdsNFAI9010Reply response = null;
//
//        try {
//            response = new Nfai9010ServiceProxy().s21CsawdsNFAI9010TenParam(request);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//            msgMap.addXxMsgId("NFCM0041E");
//        } catch (FaultResourceExp e) {
//            e.printStackTrace();
//            msgMap.addXxMsgId("NFCM0041E");
//        } catch (FaultDataExp e) {
//            e.printStackTrace();
//            msgMap.addXxMsgId("NFCM0041E");
//        } catch (FaultConfigurationExp e) {
//            e.printStackTrace();
//            msgMap.addXxMsgId("NFCM0041E");
//        } catch (Exception e) {
//            e.printStackTrace();
//            msgMap.addXxMsgId("NFCM0041E");
//        } catch (Throwable e) {
//            e.printStackTrace();
//            msgMap.addXxMsgId("NFCM0041E");
//        }

        S21CsawdsNFAI9010TenParam request = new S21CsawdsNFAI9010TenParam();
        S21CsawdsNFAI9010Reply response = null;
        String[] ajeCrsVldCmpyCdArray = this.ajeCrsVldCmpyCd.split(",");
        for (String val : ajeCrsVldCmpyCdArray) {
            request.setPCoaId(COA_ID_CSA);
            request.setPSeg1(val);
            request.setPSeg2(param.coaBrCd.getValue());
            request.setPSeg3(param.coaCcCd.getValue());
            request.setPSeg4(param.coaAcctCd.getValue());
            request.setPSeg5(param.coaProdCd.getValue());
            request.setPSeg6(param.coaChCd.getValue());
            request.setPSeg7(param.coaAfflCd.getValue());
            if(param.coaProjCd.getValue().length() == 2) {
                String coaProjCd = "00";
                coaProjCd = coaProjCd.concat(param.coaProjCd.getValue());
                request.setPSeg8(coaProjCd);
            } else {
                request.setPSeg8(param.coaProjCd.getValue());
            }
            request.setPSeg9(param.coaExtnCd.getValue());

            try {
                response = new Nfai9010ServiceProxy().s21CsawdsNFAI9010TenParam(request);
                return response;
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (FaultResourceExp e) {
                e.printStackTrace();
            } catch (FaultDataExp e) {
                e.printStackTrace();
            } catch (FaultConfigurationExp e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        // START 2016/11/11 T.Tsuchida [QC#15925,MOD]
        //msgMap.addXxMsgId("NFCM0041E");
        // START 2019/02/08 H.Umeda [QC#29965,MOD]
        // setCombinationError(msgMap, "NFCM0041E", COAERRRSNCD_ARCSAPI_CHECK);
        setCombinationError(msgMap, "NFAM0207E", COAERRRSNCD_ARCSAPI_CHECK);
        // END   2019/02/08 H.Umeda [QC#29965,MOD]
        // END 2016/11/11 T.Tsuchida [QC#15925,MOD]
        // END 2016/09/07 T.Tsuchida [QC#14201,MOD]
        return response;
    }

//    /**
//     * 
//     * @param esbMessage EsbMessage
//     * @return String
//     * @throws PropertiesFileNotFoundException PropertiesFileNotFoundException
//     * @throws PropertyNotDefinedException PropertyNotDefinedException
//     * @throws EsbProxyException EsbProxyException
//     */
//    @SuppressWarnings("unchecked")
//       
//    public String invoke(EsbMessage esbMessage, S21ApiMessageMap msgMap) 
//    throws PropertiesFileNotFoundException, PropertyNotDefinedException, EsbProxyException {
//
//        NFZC102001PMsg param = (NFZC102001PMsg) msgMap.getPmsg();
//
//        esbMessage.setStringPayload(" ");
//
//        Map<String, String> arcsMessagesMap = new LinkedHashMap<String, String>();
//
//        //for 10 parameters
//        arcsMessagesMap.put("p_seg0", COA_ID_CSA);
//        arcsMessagesMap.put("p_seg1", param.coaCmpyCd.getValue());
//        arcsMessagesMap.put("p_seg2", param.coaBrCd.getValue());
//        arcsMessagesMap.put("p_seg3", param.coaCcCd.getValue());
//        arcsMessagesMap.put("p_seg4", param.coaAcctCd.getValue());
//        arcsMessagesMap.put("p_seg5", param.coaProdCd.getValue());
//        arcsMessagesMap.put("p_seg6", param.coaChCd.getValue());
//        arcsMessagesMap.put("p_seg7", param.coaAfflCd.getValue());
//        arcsMessagesMap.put("p_seg8", param.coaProjCd.getValue());
//        arcsMessagesMap.put("p_seg9", param.coaExtnCd.getValue());
//
//        esbMessage.setArcsMessages(arcsMessagesMap);
//
//        try {
//            return new ArcsS21Proxy().invoke(esbMessage);
//        } catch (Exception e) {
//            LOGGER.error("Failed to invoke ArcsS21Proxy.invoke().", e);
//            throw new EsbProxyException(e);
//        }
//    }
    // END 2016/05/12 S.Fujita [QC#7918,MOD]

    /**
     * 
     * @param msgMap
     */
    private boolean checkParam(S21ApiMessageMap msgMap) {

        NFZC102001PMsg param = (NFZC102001PMsg) msgMap.getPmsg();

        if (S21StringUtil.isEmpty(param.coaCmpyCd.getValue()) || 
            S21StringUtil.isEmpty(param.coaBrCd.getValue()) ||
            S21StringUtil.isEmpty(param.coaCcCd.getValue()) ||
            S21StringUtil.isEmpty(param.coaAcctCd.getValue()) ||
            S21StringUtil.isEmpty(param.coaProdCd.getValue()) ||
            S21StringUtil.isEmpty(param.coaChCd.getValue()) ||
            S21StringUtil.isEmpty(param.coaAfflCd.getValue()) ||
            S21StringUtil.isEmpty(param.coaProjCd.getValue()) ||
            S21StringUtil.isEmpty(param.coaExtnCd.getValue())) {
            this.is9Segments = false;
        }

        // START QC#19433 Mod.
        if (param.xxArcsApiChkFlg.getValue().equals(ZYPConstant.FLG_ON_Y)
        		&& param.xxGlCmbnChkFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {

        	if (!this.is9Segments && isAJE) {

        		if (ZYPCommonFunc.hasValue(this.ajeGlCmbnChk)
        				&& !this.ajeGlCmbnChk.equals(GL_CMBN_CHECK_BOTH_THRU)) {
        			/* In Parameter 9 segments is mandatory. */
                    set9SegmentsError(msgMap, NFAM0153E, COAERRRSNCD_PARAMTER_CHECK);
                    msgMap.setPmsg(param);
                    return false;
                }

            } else if (!this.is9Segments && !isAJE) {

            	if (ZYPCommonFunc.hasValue(this.frontGlCmbnChk)
            			&& !this.frontGlCmbnChk.equals(GL_CMBN_CHECK_BOTH_THRU)) {
            		/* In Parameter 9 segments is mandatory. */
                    set9SegmentsError(msgMap, NFAM0153E, COAERRRSNCD_PARAMTER_CHECK);
                    msgMap.setPmsg(param);
                    return false;
                }
            }
        }
        // END QC#19433 Mod.
        param.setReturnCode(RTN_CD_NORMAL);
        msgMap.setPmsg(param);
        return true;
    }

    /**
     * 
     * @param msgMap S21ApiMessageMap
     * @return
     */
    private boolean masterCheck(S21ApiMessageMap msgMap) {

        NFZC102001PMsg param = (NFZC102001PMsg) msgMap.getPmsg();

        boolean errFlg = false;
        int no = 0;
        // QC#30768
        if (!S21StringUtil.isEmpty(param.coaCmpyCd.getValue())) {
            COA_CMPYTMsg coaCmpy = new COA_CMPYTMsg();
            ZYPEZDItemValueSetter.setValue(coaCmpy.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(coaCmpy.coaCmpyCd, param.coaCmpyCd.getValue());
            coaCmpy = (COA_CMPYTMsg) EZDTBLAccessor.findByKey(coaCmpy);
            if (coaCmpy == null) {
                param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_CMPY);
                param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                msgMap.addXxMsgIdWithPrm(NFAM0188E, new String[]{"COA_CMPY_CD"});
//                msgMap.addXxMsgId(NFAM0188E);
                no++;
                errFlg = true;
            } else {
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(coaCmpy.getReturnCode())) {
                    param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_CMPY);
                    param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                    msgMap.addXxMsgIdWithPrm(NFAM0188E, new String[]{"COA_CMPY_CD"});
//                    msgMap.addXxMsgId(NFAM0188E);
                    errFlg = true;
                }
            }
        }
        if (!S21StringUtil.isEmpty(param.coaBrCd.getValue())) {
            COA_BRTMsg coaBr = new COA_BRTMsg();
            ZYPEZDItemValueSetter.setValue(coaBr.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(coaBr.coaBrCd, param.coaBrCd.getValue());
            coaBr = (COA_BRTMsg) EZDTBLAccessor.findByKey(coaBr);
            if (coaBr == null) {
                param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_BR);
                param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                msgMap.addXxMsgIdWithPrm(NFAM0189E, new String[]{"COA_BR_CD"});
//                msgMap.addXxMsgId(NFAM0189E);
                no++;
                errFlg = true;
            } else {
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(coaBr.getReturnCode())) {
                    param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_BR);
                    param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                    msgMap.addXxMsgIdWithPrm(NFAM0189E, new String[]{"COA_BR_CD"});
//                    msgMap.addXxMsgId(NFAM0189E);
                    errFlg = true;
                }
            }
        }
        if (!S21StringUtil.isEmpty(param.coaCcCd.getValue())) {
            COA_CCTMsg coaCc = new COA_CCTMsg();
            ZYPEZDItemValueSetter.setValue(coaCc.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(coaCc.coaCcCd, param.coaCcCd.getValue());
            coaCc = (COA_CCTMsg) EZDTBLAccessor.findByKey(coaCc);
            if (coaCc == null) {
                param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_CC);
                param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                msgMap.addXxMsgIdWithPrm(NFAM0190E, new String[]{"COA_CC_CD"});
//                msgMap.addXxMsgId(NFAM0190E);
                no++;
                errFlg = true;
            } else {
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(coaCc.getReturnCode())) {
                    param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_CC);
                    param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                    msgMap.addXxMsgIdWithPrm(NFAM0190E, new String[]{"COA_CC_CD"});
//                    msgMap.addXxMsgId(NFAM0190E);
                    errFlg = true;
                }
            }
        }
        if (!S21StringUtil.isEmpty(param.coaAcctCd.getValue())) {
            COA_ACCTTMsg coaAcct = new COA_ACCTTMsg();
            ZYPEZDItemValueSetter.setValue(coaAcct.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(coaAcct.coaAcctCd, param.coaAcctCd.getValue());
            coaAcct = (COA_ACCTTMsg) EZDTBLAccessor.findByKey(coaAcct);
            if (coaAcct == null) {
                param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_ACCT);
                param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                msgMap.addXxMsgIdWithPrm(NFAM0191E, new String[]{"COA_ACCT_CD"});
//                msgMap.addXxMsgId(NFAM0191E);
                no++;
                errFlg = true;
            } else {
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(coaAcct.getReturnCode())) {
                    param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_ACCT);
                    param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                    msgMap.addXxMsgIdWithPrm(NFAM0191E, new String[]{"COA_ACCT_CD"});
//                    msgMap.addXxMsgId(NFAM0191E);
                    errFlg = true;
                }
            }
        }
        if (!S21StringUtil.isEmpty(param.coaProdCd.getValue())) {
            COA_PRODTMsg coaProd = new COA_PRODTMsg();
            ZYPEZDItemValueSetter.setValue(coaProd.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(coaProd.coaProdCd, param.coaProdCd.getValue());
            coaProd = (COA_PRODTMsg) EZDTBLAccessor.findByKey(coaProd);
            if (coaProd == null) {
                param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_PROD);
                param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                msgMap.addXxMsgIdWithPrm(NFAM0192E, new String[]{"COA_PROD_CD"});
//                msgMap.addXxMsgId(NFAM0192E);
                no++;
                errFlg = true;
            } else {
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(coaProd.getReturnCode())) {
                    param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_PROD);
                    param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                    msgMap.addXxMsgIdWithPrm(NFAM0192E, new String[]{"COA_PROD_CD"});
//                    msgMap.addXxMsgId(NFAM0192E);
                    errFlg = true;
                }
            }
        }
        if (!S21StringUtil.isEmpty(param.coaChCd.getValue())) {
            COA_CHTMsg coaCh = new COA_CHTMsg();
            ZYPEZDItemValueSetter.setValue(coaCh.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(coaCh.coaChCd, param.coaChCd.getValue());
            coaCh = (COA_CHTMsg) EZDTBLAccessor.findByKey(coaCh);
            if (coaCh == null) {
                param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_CH);
                param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                msgMap.addXxMsgIdWithPrm(NFAM0193E, new String[]{"COA_CH_CD"});
//                msgMap.addXxMsgId(NFAM0193E);
                no++;
                errFlg = true;
            } else {
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(coaCh.getReturnCode())) {
                    param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_CH);
                    param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                    msgMap.addXxMsgIdWithPrm(NFAM0193E, new String[]{"COA_CH_CD"});
//                    msgMap.addXxMsgId(NFAM0193E);
                    errFlg = true;
                }
            }
        }
        if (!S21StringUtil.isEmpty(param.coaAfflCd.getValue())) {
            COA_AFFLTMsg coaAffl = new COA_AFFLTMsg();
            ZYPEZDItemValueSetter.setValue(coaAffl.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(coaAffl.coaAfflCd, param.coaAfflCd.getValue());
            coaAffl = (COA_AFFLTMsg) EZDTBLAccessor.findByKey(coaAffl);
            if (coaAffl == null) {
                param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_AFFL);
                param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                msgMap.addXxMsgIdWithPrm(NFAM0194E, new String[]{"COA_AFFL_CD"});
//                msgMap.addXxMsgId(NFAM0194E);
                no++;
                errFlg = true;
            } else {
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(coaAffl.getReturnCode())) {
                    param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_AFFL);
                    param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                    msgMap.addXxMsgIdWithPrm(NFAM0194E, new String[]{"COA_AFFL_CD"});
//                    msgMap.addXxMsgId(NFAM0194E);
                    errFlg = true;
                }
            }
        }
        if (!S21StringUtil.isEmpty(param.coaProjCd.getValue())) {
            COA_PROJTMsg coaProj = new COA_PROJTMsg();
            ZYPEZDItemValueSetter.setValue(coaProj.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(coaProj.coaProjCd, param.coaProjCd.getValue());
            coaProj = (COA_PROJTMsg) EZDTBLAccessor.findByKey(coaProj);
            if (coaProj == null) {
                param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_PROJ);
                param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                msgMap.addXxMsgIdWithPrm(NFAM0195E, new String[]{"COA_PROJ_CD"});
//                msgMap.addXxMsgId(NFAM0195E);
                no++;
                errFlg = true;
            } else {
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(coaProj.getReturnCode())) {
                    param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_PROJ);
                    param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                    msgMap.addXxMsgIdWithPrm(NFAM0195E, new String[]{"COA_PROJ_CD"});
//                    msgMap.addXxMsgId(NFAM0195E);
                    errFlg = true;
                }
            }
        }
        if (!S21StringUtil.isEmpty(param.coaExtnCd.getValue())) {
            COA_EXTNTMsg coaExtn = new COA_EXTNTMsg();
            ZYPEZDItemValueSetter.setValue(coaExtn.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(coaExtn.coaExtnCd, param.coaExtnCd.getValue());
            coaExtn = (COA_EXTNTMsg) EZDTBLAccessor.findByKey(coaExtn);
            if (coaExtn == null) {
                param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_EXTN);
                param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                msgMap.addXxMsgIdWithPrm(NFAM0196E, new String[]{"COA_EXTN_CD"});
//                msgMap.addXxMsgId(NFAM0196E);
                no++;
                errFlg = true;
            } else {
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(coaExtn.getReturnCode())) {
                    param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_EXTN);
                    param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                    msgMap.addXxMsgIdWithPrm(NFAM0196E, new String[]{"COA_EXTN_CD"});
//                    msgMap.addXxMsgId(NFAM0196E);
                    errFlg = true;
                }
            }
        }

        if (errFlg) {
            return false;
        }
        return true;
    }

    // START 2016/09/07 T.Tsuchida [QC#14201,DEL]
    // Not Use
//    private boolean countNotAllwSec(S21ApiMessageMap msgMap) {
//        NFZC102001PMsg param = (NFZC102001PMsg) msgMap.getPmsg();
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
//        queryParam.put("resrcObjNm", param.resrcObjNm.getValue());
//        Map ssmRes = (Map) ssmBatchClient.queryObject("countNotAllowSec", queryParam);
//
//        BigDecimal count = (BigDecimal) ssmRes.get("COUNT");
//
//        if (count.equals(BigDecimal.ZERO)) {
//            msgMap.addXxMsgIdWithPrm(NFAM0154E, new String[]{param.resrcObjNm.getValue()});
//            return false;
//        }
//
//        return true;
//    }
    // END 2016/09/07 T.Tsuchida [QC#14201,DEL]

    /**
     * 
     * @param msgMap S21ApiMessageMap
     * @return
     */
    private boolean notAllwSecCheck(S21ApiMessageMap msgMap) {

//        if(!countNotAllwSec(msgMap)) {
//            return false;
//        }

        NFZC102001PMsg param = (NFZC102001PMsg) msgMap.getPmsg();
        boolean errFlg = false;

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("coaCmpyCd", param.coaCmpyCd.getValue());
        queryParam.put("coaBrCd", param.coaBrCd.getValue());
        queryParam.put("coaCcCd", param.coaCcCd.getValue());
        queryParam.put("coaAcctCd", param.coaAcctCd.getValue());
        queryParam.put("coaProdCd", param.coaProdCd.getValue());
        queryParam.put("coaChCd", param.coaChCd.getValue());
        queryParam.put("coaAfflCd", param.coaAfflCd.getValue());
        queryParam.put("coaProjCd", param.coaProjCd.getValue());
        queryParam.put("coaExtnCd", param.coaExtnCd.getValue());
        queryParam.put("resrcObjNm", param.resrcObjNm.getValue());

        List<Map> ssmResList = (List<Map>) ssmBatchClient.queryObjectList("checkNotAllowSec", queryParam);
        if (ssmResList.isEmpty()) {
            return true;
        }
        int no = 0;
        // QC#30768
        for (Map<String, String>ssmRes : ssmResList) {
            if (ssmRes.containsValue(NG_COA_CMPY)) {
                param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_CMPY);
                param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                msgMap.addXxMsgIdWithPrm(NFAM0197E, new String[]{"COA_CMPY_CD"});
//                msgMap.addXxMsgId(NFAM0197E);
                errFlg = true;
                no++;
            }
            if (ssmRes.containsValue(NG_COA_BR)) {
                param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_BR);
                param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                msgMap.addXxMsgIdWithPrm(NFAM0198E, new String[]{"COA_BR_CD"});
//                msgMap.addXxMsgId(NFAM0198E);
                errFlg = true;
                no++;
            }
            if (ssmRes.containsValue(NG_COA_CC)) {
                param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_CC);
                param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                msgMap.addXxMsgIdWithPrm(NFAM0199E, new String[]{"COA_CC_CD"});
//                msgMap.addXxMsgId(NFAM0199E);
                errFlg = true;
                no++;
            }
            if (ssmRes.containsValue(NG_COA_ACCT)) {
                param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_ACCT);
                param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                msgMap.addXxMsgIdWithPrm(NFAM0200E, new String[]{"COA_ACCT_CD"});
//                msgMap.addXxMsgId(NFAM0200E);
                errFlg = true;
                no++;
            }
            if (ssmRes.containsValue(NG_COA_PROD)) {
                param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_PROD);
                param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                msgMap.addXxMsgIdWithPrm(NFAM0201E, new String[]{"COA_PROD_CD"});
//                msgMap.addXxMsgId(NFAM0201E);
                errFlg = true;
                no++;
            }
            if (ssmRes.containsValue(NG_COA_CH)) {
                param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_CH);
                param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                msgMap.addXxMsgIdWithPrm(NFAM0202E, new String[]{"COA_CH_CD"});
//                msgMap.addXxMsgId(NFAM0202E);
                errFlg = true;
                no++;
            }
            if (ssmRes.containsValue(NG_COA_AFFL)) {
                param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_AFFL);
                param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                msgMap.addXxMsgIdWithPrm(NFAM0203E, new String[]{"COA_AFFL_CD"});
//                msgMap.addXxMsgId(NFAM0203E);
                errFlg = true;
                no++;
            }
            if (ssmRes.containsValue(NG_COA_PROJ)) {
                param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_PROJ);
                param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                msgMap.addXxMsgIdWithPrm(NFAM0204E, new String[]{"COA_PROJ_CD"});
//                msgMap.addXxMsgId(NFAM0204E);
                errFlg = true;
                no++;
            }
            if (ssmRes.containsValue(NG_COA_EXTN)) {
                param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_EXTN);
                param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(COAERRRSNCD_MASTER_CHECK);
                msgMap.addXxMsgIdWithPrm(NFAM0205E, new String[]{"COA_EXTN_CD"});
//                msgMap.addXxMsgId(NFAM0205E);
                errFlg = true;
                no++;
            }
        }

        if (errFlg) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void setCombinationError(S21ApiMessageMap msgMap, String errId, String errRsn) {

        NFZC102001PMsg param = (NFZC102001PMsg) msgMap.getPmsg();
        int no = 0;
        if (!S21StringUtil.isEmpty(param.coaCmpyCd.getValue())) {
            param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_CMPY);
            param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(errRsn);
            msgMap.addXxMsgId(errId);
            no++;
        }
        if (!S21StringUtil.isEmpty(param.coaBrCd.getValue())) {
            param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_BR);
            param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(errRsn);
            msgMap.addXxMsgId(errId);
            no++;
        }
        if (!S21StringUtil.isEmpty(param.coaCcCd.getValue())) {
            param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_CC);
            param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(errRsn);
            msgMap.addXxMsgId(errId);
            no++;
        }
        if (!S21StringUtil.isEmpty(param.coaAcctCd.getValue())) {
            param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_ACCT);
            param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(errRsn);
            msgMap.addXxMsgId(errId);
            no++;
        }
        if (!S21StringUtil.isEmpty(param.coaProdCd.getValue())) {
            param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_PROD);
            param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(errRsn);
            msgMap.addXxMsgId(errId);
            no++;
        }
        if (!S21StringUtil.isEmpty(param.coaChCd.getValue())) {
            param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_CH);
            param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(errRsn);
            msgMap.addXxMsgId(errId);
            no++;
        }
        if (!S21StringUtil.isEmpty(param.coaAfflCd.getValue())) {
            param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_AFFL);
            param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(errRsn);
            msgMap.addXxMsgId(errId);
            no++;
        }
        if (!S21StringUtil.isEmpty(param.coaProjCd.getValue())) {
            param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_PROJ);
            param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(errRsn);
            msgMap.addXxMsgId(errId);
            no++;
        }
        if (!S21StringUtil.isEmpty(param.coaExtnCd.getValue())) {
            param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_EXTN);
            param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(errRsn);
            msgMap.addXxMsgId(errId);
            no++;
        }
    }

    /**
     * 
     * @param msgMap
     * @param errId
     * @param errRsn
     */
    private void set9SegmentsError(S21ApiMessageMap msgMap, String errId, String errRsn) {

        NFZC102001PMsg param = (NFZC102001PMsg) msgMap.getPmsg();
        int no = 0;
        if (S21StringUtil.isEmpty(param.coaCmpyCd.getValue())) {
            param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_CMPY);
            param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(errRsn);
            msgMap.addXxMsgId(errId);
            no++;
        }
        if (S21StringUtil.isEmpty(param.coaBrCd.getValue())) {
            param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_BR);
            param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(errRsn);
            msgMap.addXxMsgId(errId);
            no++;
        }
        if (S21StringUtil.isEmpty(param.coaCcCd.getValue())) {
            param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_CC);
            param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(errRsn);
            msgMap.addXxMsgId(errId);
            no++;
        }
        if (S21StringUtil.isEmpty(param.coaAcctCd.getValue())) {
            param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_ACCT);
            param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(errRsn);
            msgMap.addXxMsgId(errId);
            no++;
        }
        if (S21StringUtil.isEmpty(param.coaProdCd.getValue())) {
            param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_PROD);
            param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(errRsn);
            msgMap.addXxMsgId(errId);
            no++;
        }
        if (S21StringUtil.isEmpty(param.coaChCd.getValue())) {
            param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_CH);
            param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(errRsn);
            msgMap.addXxMsgId(errId);
            no++;
        }
        if (S21StringUtil.isEmpty(param.coaAfflCd.getValue())) {
            param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_AFFL);
            param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(errRsn);
            msgMap.addXxMsgId(errId);
            no++;
        }
        if (S21StringUtil.isEmpty(param.coaProjCd.getValue())) {
            param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_PROJ);
            param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(errRsn);
            msgMap.addXxMsgId(errId);
            no++;
        }
        if (S21StringUtil.isEmpty(param.coaExtnCd.getValue())) {
            param.xxMsgIdList.no(no).xxCoaTpCd.setValue(COAID_COA_EXTN);
            param.xxMsgIdList.no(no).xxCoaErrRsnCd.setValue(errRsn);
            msgMap.addXxMsgId(errId);
            no++;
        }
    }

    /**
     * 
     * @param logmsg
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(DEBUG_MSG_LVL, logmsg, this);
    }

    /**
     * ajeProcessCheck
     * @param msgMap
     * @param onBatchType
     */
    private void ajeProcessCheck(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType, NFZC102001PMsg param) {

        if (this.ajeGlCmbnChk.equals(GL_CMBN_CHECK_ARCS_API)) {
            /********************************************/
        	/** Check Mode : None                       */
            /********************************************/
            // GL_COA_CMBN Check API Call.
            if (!check9SegmentCombination_s21(msgMap, onBatchType)) {

            	EZDDebugOutput.println(1, "S21 Combination Check Error.", this);

            	// ARCS API Call
            	if (param.xxArcsApiChkFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            		EZDDebugOutput.println(1, "ARCS API Call.", this);
                    if (this.is9Segments) {
                        check9SegmentCombination_ARCS(msgMap, onBatchType);
                        if (msgMap.getMsgMgr().isXxMsgId()) {
                            msgMap.flush();
                        }
                    } else {
                        /* Combination Error */
                        setCombinationError(msgMap, NFAM0151E, COAERRRSNCD_COMBINATION_CHECK);
                        msgMap.flush();
                    }
                // QC#19433 Mod.
            	} else {
            		setCombinationError(msgMap, NFAM0151E, COAERRRSNCD_COMBINATION_CHECK);
            		msgMap.flush();
            		return;
            	}
            }

        } else if (this.ajeGlCmbnChk.equals(GL_CMBN_CHECK_ARCS_API_THRU)) {
            /********************************************/
        	/** Check Mode : ARCS API Thrugh            */
            /********************************************/
            // GL_COA_CMBN Check API Call.
            if (!check9SegmentCombination_s21(msgMap, onBatchType)) {
                setCombinationError(msgMap, NFAM0151E, COAERRRSNCD_COMBINATION_CHECK);
                msgMap.flush();
            }

        } else if (this.ajeGlCmbnChk.equals(GL_CMBN_CHECK_THRU)) {
            /********************************************/
        	/** Check Mode : GL_COA_CMBN Check Thrugh   */
            /********************************************/
        	if (param.xxArcsApiChkFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
        		EZDDebugOutput.println(1, "ARCS API Call.", this);
                // ARCS API Call
                if (this.is9Segments) {
                    check9SegmentCombination_ARCS(msgMap, onBatchType);
                    if (msgMap.getMsgMgr().isXxMsgId()) {
                        msgMap.flush();
                    }
                } else {
                    /* Combination Error */
                    setCombinationError(msgMap, NFAM0151E, COAERRRSNCD_COMBINATION_CHECK);
                    msgMap.flush();
                }
        	}
        }
    }

    /**
     * S21ApiMessageMap
     * @param msgMap
     * @param onBatchType
     */
    private void frontProcessCheck(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType, NFZC102001PMsg param) {

        if (this.frontGlCmbnChk.equals(GL_CMBN_CHECK_ARCS_API)) {
            /********************************************/
            /** Check Mode : None                       */
            /********************************************/
            // GL_COA_CMBN Check API Call.
            if (!check9SegmentCombination_s21(msgMap, onBatchType)) {

            	EZDDebugOutput.println(1, "S21 Combination Check Error.", this);

            	// ARCS API Call
            	if (param.xxArcsApiChkFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            		EZDDebugOutput.println(1, "ARCS API Call.", this);
                    if (this.is9Segments) {
                        check9SegmentCombination_ARCS(msgMap, onBatchType);
                        if (msgMap.getMsgMgr().isXxMsgId()) {
                            msgMap.flush();
                        }
                    } else {
                        /* Combination Error */
                        setCombinationError(msgMap, NFAM0151E, COAERRRSNCD_COMBINATION_CHECK);
                        msgMap.flush();
                    }
                // QC#19433 Mod.
            	} else {
            		setCombinationError(msgMap, NFAM0151E, COAERRRSNCD_COMBINATION_CHECK);
            		msgMap.flush();
            		return;
            	}
            }

        } else if (this.frontGlCmbnChk.equals(GL_CMBN_CHECK_ARCS_API_THRU)) {
            /********************************************/
            /** Check Mode : ARCS API Thrugh            */
            /********************************************/
            // GL_COA_CMBN Check API Call.
            if (!check9SegmentCombination_s21(msgMap, onBatchType)) {
                setCombinationError(msgMap, NFAM0151E, COAERRRSNCD_COMBINATION_CHECK);
                msgMap.flush();
            }

        } else if (this.frontGlCmbnChk.equals(GL_CMBN_CHECK_THRU)) {
            /********************************************/
            /** Check Mode : GL_COA_CMBN Check Thrugh   */
            /********************************************/
        	if (param.xxArcsApiChkFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
        		EZDDebugOutput.println(1, "ARCS API Call.", this);
                // ARCS API Call
                if (this.is9Segments) {
                    check9SegmentCombination_ARCS(msgMap, onBatchType);
                    if (msgMap.getMsgMgr().isXxMsgId()) {
                        msgMap.flush();
                    }
                } else {
                    /* Combination Error */
                    setCombinationError(msgMap, NFAM0151E, COAERRRSNCD_COMBINATION_CHECK);
                    msgMap.flush();
                }
        	}
        }
    }

}
