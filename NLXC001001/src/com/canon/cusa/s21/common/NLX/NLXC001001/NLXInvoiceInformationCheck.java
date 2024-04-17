/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC001001;

import java.sql.SQLException;
import java.util.List;

import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;


/**
 * <pre>
 * 
 *  Date        Company         Name            Create/Update   Defect No
 *  ----------------------------------------------------------------------
 *  06/18/2009  Fujitsu         M.Irisawa       Create          N/A
 *  11/09/2009  Fujitsu         A.Akabane       Update          RQ349
 *  12/29/2009  Fujitsu         S.Tsuboi        Update          2879
 *  06/02/2010  Fujitsu         M.Yamada        Update          6313
 *  06/16/2010  Fujitsu         S.Yoshida       Update          7166
 *  07/13/2010  Fujitsu         T.Ishii         Update          7370
 *  11/22/2010  Fujitsu         M.Yamada        Update          ImptInvCarrCd check
 *</pre>
 */
public class NLXInvoiceInformationCheck {

    // Result Code
    // --------------------------------------------------------
    /** Normal Code */
    public static final String NORMAL_CODE = "0";
    /** Error Code*/
    public static final String ERROR_CODE = "1";

    // Message ID
    // --------------------------------------------------------
    /** The corresponding data does not exist. */
    public static final String NLAM1001E = "NLAM1001E";
    /** A value is not entered in the required field. */
    public static final String NLAM1002E = "NLAM1002E";
    /** A value is not set in the required parameter field. */
    public static final String NLAM1006E = "NLAM1006E";
    /** The parameter length is invalid. */
    public static final String NLAM1008E = "NLAM1008E";
    /** There are no codes in the Code Master. */
    public static final String NLAM1013E = "NLAM1013E";
    /** The required Merchandise data is not set. */
    public static final String NLAM1111E = "NLAM1111E";
    /** The Tariff data for the merchandise is not set. */
    public static final String NLAM1112E = "NLAM1112E";
    /** The Cost data for the merchandise is not set. */
    public static final String NLAM1113E = "NLAM1113E";
    /** The Safety data for the merchandise is not set. */
    public static final String NLAM1114E = "NLAM1114E";
    /** The SafetyFCC data for the merchandise is invalid. */
    public static final String NLAM1115E = "NLAM1115E";
    /** The SafetyFDA data for the merchandise is invalid. */
    public static final String NLAM1116E = "NLAM1116E";
    /** The SafetyTSCA data for the merchandise is invalid. */
    public static final String NLAM1117E = "NLAM1117E";
    /** The MDSE Task Management data is not set. */
    public static final String NLAM1149E = "NLAM1149E";
    /** The MDSE parts detail information is not set. */
    public static final String NLAM1154E = "NLAM1154E";

    // statementId
    // --------------------------------------------------------
    /** Type03 */
    private static final String CHECKTYPE_03 = "checktype03";
    /** Type10 */
    private static final String CHECKTYPE_10 = "checktype10";
    /** Type10_3 */
    private static final String CHECKTYPE_10_3 = "checktype10_3";
    /** Type17 */
    private static final String CHECKTYPE_17 = "checktype17";
    /** Type20 */
    private static final String CHECKTYPE_20 = "checktype20";
    /** Type21 */
    private static final String CHECKTYPE_21 = "checktype21";
    /** Type22 */
    private static final String CHECKTYPE_22 = "checktype22";

    /** RGTN_STS_CD */
    private static final String RGTN_STS_CD = "RGTN_STS_CD";
    /** MDSE_NM */
    private static final String MDSE_NM = "MDSE_NM";
    /** MDSE_CSTM_NM */
    private static final String MDSE_CSTM_NM = "MDSE_CSTM_NM";
    /** ZEROTH_PROD_CTRL_CD */
    private static final String ZEROTH_PROD_CTRL_CD = "ZEROTH_PROD_CTRL_CD";
    /** FIRST_PROD_CTRL_CD */
    private static final String FIRST_PROD_CTRL_CD = "FIRST_PROD_CTRL_CD";
    /** INVTY_CTRL_FLG */
    private static final String INVTY_CTRL_FLG = "INVTY_CTRL_FLG";
    /** TRF_CD */
    private static final String TRF_CD = "TRF_CD";
    /** ISF_MDSE.TRF_CD */
    private static final String IM_TRF_CD = "IM_TRF_CD";
    /** TRF.TRF_CD */
    private static final String T_TRF_CD = "T_TRF_CD";
    /** TRF.TRF_CD by ISF_MDSE */
    private static final String T_TRF_CD_ISF = "T_TRF_CD_ISF";
    /** MDSE_TASK_CD */
    private static final String MDSE_TASK_CD = "MDSE_TASK_CD";
    /** P_DESC_PART */
    private static final String P_DESC_PART = "P_DESC_PART";
    /** P_IND_FCC */
    private static final String P_IND_FCC = "P_IND_FCC";
    /** P_IND_FDA */
    private static final String P_IND_FDA = "P_IND_FDA";
    /** P_IND_TSCA */
    private static final String P_IND_TSCA = "P_IND_TSCA";

    /** SQL_PARA glblCmpyCd */
    private static final String SQL_PARA_GLBL_CMPY_CD = "glblCmpyCd";
    /** SQL_PARA key1 */
    private static final String SQL_PARA_KEY1 = "key1";
    /** SQL_PARA batProcDate */
    private static final String SQL_PARA_BAT_PROC_DATE = "batProcDate";
    /** SQL_PARA rgtnStsCd */
    private static final String SQL_PARA_RGTN_STS_CD = "rgtnStsCd";

    /** SQL_PARA rgtnStsCd */
    private static final String SQL_PARA_VND_TP_CD = "vndTpCd";

    // ADC Flag
    // --------------------------------------------------------
    /** ADC_FLG_0 */
    private static final String ADC_FLG_0 = "0";
    /** ADC_FLG_1 */
    private static final String ADC_FLG_1 = "1";
    /** ADC_FLG_2 */
    private static final String ADC_FLG_2 = "2";
    /** ADC_FLG_9 */
    private static final String ADC_FLG_9 = "9";

    // Digit
    // --------------------------------------------------------
    /** Global Company Code */
    private static final int DIGIT_GLBLCMPYCD = 4;
    /** Check Type */
    private static final int DIGIT_CHECKTYPE = 2;

    // Input parameters
    // --------------------------------------------------------
    /** Global Company Code */
    private String glblCmpyCdM = null;
    /** Check Type */
    private String checktypeM = null;

    /** Key List */
    private List<String> keyListM = null;
    /** Message List */
    private List<String> msgListM = null;

    // Output parameters
    // --------------------------------------------------------
    /** Value1 */
    private String value1 = null;
    /** Value2 */
    private String value2 = null;
    /** Value3 */
    private String value3 = null;
    /** Value4 */
    private String value4 = null;
    /** Value5 */
    private String value5 = null;
    /** Value6 */
    private String value6 = null;
    /** Value7 */
    private String value7 = null;
    /** Value8 */
    private String value8 = null;
    /** Value9 */
    private String value9 = null;

    // Other
    // --------------------------------------------------------
    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLowLevelCodingClient = null;


    /**
     * <pre>
     * Main Routin
     * </pre>
     * @param  glblCmpyCd    Global Company Code
     * @param  checktype     Check Type
     * @param  key           Key List
     * @param  value         Value List
     * @param  msgList       Massage List
     * @param  inOnBatchType Online Batch Type
     * @return Result code
     * @throws SQLException SQLException
     */
    public String mainRoutin(String glblCmpyCd, String checktype, List<String> key,
            List<String> value, List<String> msgList, ONBATCH_TYPE inOnBatchType) throws SQLException {

//        // Get S21SsmLowLevelCodingClient
//        this.ssmLowLevelCodingClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
//
//        // initial
//        this.value1 = null;
//        this.value2 = null;
//        this.value3 = null;
//        this.value4 = null;
//        this.value5 = null;
//        this.value6 = null;
//        this.value7 = null;
//        this.value8 = null;
//        this.value9 = null;
//
//        // Set parameters.
//        this.glblCmpyCdM = glblCmpyCd;
//        this.checktypeM  = checktype;
//        this.msgListM    = msgList;
//        this.keyListM    = key;
//
//        // Check parameters.
//        if (!this.checkParameters(msgList)) {
//            return ERROR_CODE;
//        }
//
//        String returnCode = checkCodeMaster();
//
//        if (value != null) {
//            value.clear();
//            value.add(this.value1);
//            value.add(this.value2);
//            value.add(this.value3);
//            value.add(this.value4);
//            value.add(this.value5);
//            value.add(this.value6);
//            value.add(this.value7);
//            value.add(this.value8);
//            value.add(this.value9);
//        }

        return NORMAL_CODE;
    }
//
//    /**
//     * <pre>
//     * Check parameters.
//     * </pre>
//     * @param msgList Message List
//     * @return
//     */
//    private boolean checkParameters(List<String> msgList) {
//
//        // Check Global Company Code and Check Type.
//        if (!ZYPCommonFunc.hasValue(this.glblCmpyCdM)
//                || !ZYPCommonFunc.hasValue(this.checktypeM)) {
//            msgList.add(NLAM1006E);
//            return false;
//        }
//        if (this.glblCmpyCdM.length() > DIGIT_GLBLCMPYCD
//                || this.checktypeM.length() > DIGIT_CHECKTYPE) {
//            msgList.add(NLAM1008E);
//            return false;
//        }
//
//        // Check Key List.
//        if (this.keyListM == null
//                || this.keyListM.isEmpty()) {
//            msgList.add(NLAM1006E);
//            return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * <pre>
//     * Check Key List.
//     * </pre>
//     * @param msgList Message List
//     * @return
//     */
//    private boolean checkKeyList(int idx) {
//
//        if (this.keyListM.size() < idx + 1
//                || !ZYPCommonFunc.hasValue(this.keyListM.get(idx))) {
//            this.msgListM.add(NLAM1006E);
//            return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * <pre>
//     * Check Code Master.
//     * </pre>
//     * @throws SQLException
//     */
//    private String checkCodeMaster() throws SQLException {
//        String returnCode = null;
//
//        if (NLXSceConst.CHECKTYPE_INV_UPD_01.equals(this.checktypeM)) {
//            returnCode = type01();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_02.equals(this.checktypeM)) {
//            returnCode = type02();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_03.equals(this.checktypeM)) {
//            returnCode = type03();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_04.equals(this.checktypeM)) {
//            returnCode = type04();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_05.equals(this.checktypeM)) {
//            returnCode = type05();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_06.equals(this.checktypeM)) {
//            returnCode = type06();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_07.equals(this.checktypeM)) {
//            returnCode = type07();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_08.equals(this.checktypeM)) {
//            returnCode = type08();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_09.equals(this.checktypeM)) {
//            returnCode = type09();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_10.equals(this.checktypeM)) {
//            returnCode = type10();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_11.equals(this.checktypeM)) {
//            returnCode = type11();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_12.equals(this.checktypeM)) {
//            returnCode = type12();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_13.equals(this.checktypeM)) {
//            returnCode = type13();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_14.equals(this.checktypeM)) {
//            returnCode = type14();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_15.equals(this.checktypeM)) {
//            returnCode = type15();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_16.equals(this.checktypeM)) {
//            returnCode = type16();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_17.equals(this.checktypeM)) {
//            returnCode = type17();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_18.equals(this.checktypeM)) {
//            returnCode = type18();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_19.equals(this.checktypeM)) {
//            returnCode = type19();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_20.equals(this.checktypeM)) {
//            returnCode = type20();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_21.equals(this.checktypeM)) {
//            returnCode = type21();
//        } else if (NLXSceConst.CHECKTYPE_INV_UPD_22.equals(this.checktypeM)) {
//            // IMPT_INV_CARR_CD
//            returnCode = type22();
//        }
//        return returnCode;
//    }
//
//    /**
//     * <pre>
//     * Check Currency.
//     * </pre>
//     * @return String Return Code
//     */
//    private String type01() {
//
//        CCYTMsg tMsg = null;
//        if (ZYPCommonFunc.hasValue(this.keyListM.get(0))) {
//            tMsg = (CCYTMsg) ZYPCodeDataUtil.findByCode(
//                    NLXSceConst.TBL_CCY, this.glblCmpyCdM, this.keyListM.get(0));
//        }
//
//        if (tMsg != null) {
//            return NORMAL_CODE;
//        } else {
//            this.msgListM.add(NLAM1001E);
//            return ERROR_CODE;
//        }
//    }
//
//    /**
//     * <pre>
//     * Check Shipping Method.
//     * </pre>
//     * @return String Return Code
//     */
//    private String type02() {
//
//        IMPT_INV_SHPG_METHTMsg tMsg = null;
//        if (ZYPCommonFunc.hasValue(this.keyListM.get(0))) {
//            tMsg = (IMPT_INV_SHPG_METHTMsg) ZYPCodeDataUtil.findByCode(
//                    NLXSceConst.TBL_IMPT_INV_SHPG_METH, this.glblCmpyCdM, this.keyListM.get(0));
//        }
//
//        if (tMsg != null) {
//            return NORMAL_CODE;
//        } else {
//            this.msgListM.add(NLAM1001E);
//            return ERROR_CODE;
//        }
//    }
//
//    /**
//     * <pre>
//     * Check Consignee.
//     * </pre>
//     * @return String Return Code
//     * @throws SQLException SQLException
//     */
//    private String type03() throws SQLException {
//
//        String returnCode = null;
//        String methKey2 = null;
//
//        if (!this.checkKeyList(1)) {
//            return ERROR_CODE;
//        }
//        methKey2 = this.keyListM.get(1);
//
////        if (!ZYPCommonFunc.hasValue(this.key2)) {
////            msgListM.add(NLAM1006E);
////            rtnCd = RTN_CD_ERROR;
////        } else {
////            methKey2 = keyListM.get(1);
////        }
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put(SQL_PARA_GLBL_CMPY_CD,  this.glblCmpyCdM);
//        queryParam.put(SQL_PARA_KEY1,          this.keyListM.get(0));
//        queryParam.put(SQL_PARA_BAT_PROC_DATE, methKey2);
//        PreparedStatement pstmt = null;
//        ResultSet ssmResult = null;
//
//        try {
//            pstmt = this.ssmLowLevelCodingClient.createPreparedStatement(CHECKTYPE_03, queryParam);
//            ssmResult = pstmt.executeQuery();
//            if (ssmResult.next()) {
//                returnCode = NORMAL_CODE;
//            } else {
//                this.msgListM.add(NLAM1001E);
//                returnCode = ERROR_CODE;
//            }
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(pstmt, ssmResult);
//        }
//
//        return returnCode;
//    }
//
//    /**
//     * <pre>
//     * Check Container Type.
//     * </pre>
//     * @return String Return Code
//     */
//    private String type04() {
//
//        IMPT_INV_CNTNR_TPTMsg tMsg = null;
//        if (ZYPCommonFunc.hasValue(this.keyListM.get(0))) {
//            tMsg = (IMPT_INV_CNTNR_TPTMsg) ZYPCodeDataUtil.findByCode(
//                    NLXSceConst.TBL_IMPT_INV_CNTNR_TP, this.glblCmpyCdM, this.keyListM.get(0));
//        }
//
//        if (tMsg != null) {
//            return NORMAL_CODE;
//        } else {
//            this.msgListM.add(NLAM1001E);
//            return ERROR_CODE;
//        }
//    }
//
//    /**
//     * <pre>
//     * Check Freight Payer.
//     * </pre>
//     * @return String Return Code
//     */
//    private String type05() {
//
//        FRT_PAYERTMsg tMsg = null;
//        if (ZYPCommonFunc.hasValue(this.keyListM.get(0))) {
//            tMsg = (FRT_PAYERTMsg) ZYPCodeDataUtil.findByCode(
//                    NLXSceConst.TBL_FRT_PAYER, this.glblCmpyCdM, this.keyListM.get(0));
//        }
//
//        if (tMsg != null) {
//            return NORMAL_CODE;
//        } else {
//            this.msgListM.add(NLAM1001E);
//            return ERROR_CODE;
//        }
//    }
//
//    /**
//     * <pre>
//     * Check Insurance Payer.
//     * </pre>
//     * @return String Return Code
//     */
//    private String type06() {
//
//        INS_PAYERTMsg tMsg = null;
//        if (ZYPCommonFunc.hasValue(this.keyListM.get(0))) {
//            tMsg = (INS_PAYERTMsg) ZYPCodeDataUtil.findByCode(
//                    NLXSceConst.TBL_INS_PAYER, this.glblCmpyCdM, this.keyListM.get(0));
//        }
//
//        if (tMsg != null) {
//            return NORMAL_CODE;
//        } else {
//            this.msgListM.add(NLAM1001E);
//            return ERROR_CODE;
//        }
//    }
//
//    /**
//     * <pre>
//     * Check Shipping Term.
//     * </pre>
//     * @return String Return Code
//     */
//    private String type07() {
//
//        IMPT_INV_SHPG_TERMTMsg tMsg = null;
//        if (ZYPCommonFunc.hasValue(this.keyListM.get(0))) {
//            tMsg = (IMPT_INV_SHPG_TERMTMsg) ZYPCodeDataUtil.findByCode(
//                    NLXSceConst.TBL_IMPT_INV_SHPG_TERM, this.glblCmpyCdM, this.keyListM.get(0));
//        }
//
//        if (tMsg != null) {
//            return NORMAL_CODE;
//        } else {
//            this.msgListM.add(NLAM1001E);
//            return ERROR_CODE;
//        }
//    }
//
//    /**
//     * <pre>
//     * Check Port Load.
//     * </pre>
//     * @return String Return Code
//     */
//    private String type08() {
//
//        IMPT_INV_PORT_LOADTMsg tMsg = null;
//        if (ZYPCommonFunc.hasValue(this.keyListM.get(0))) {
//            tMsg = (IMPT_INV_PORT_LOADTMsg) ZYPCodeDataUtil.findByCode(
//                    NLXSceConst.TBL_IMPT_INV_PORT_LOAD, this.glblCmpyCdM, this.keyListM.get(0));
//        }
//
//        if (tMsg != null) {
//            return NORMAL_CODE;
//        } else {
//            this.msgListM.add(NLAM1001E);
//            return ERROR_CODE;
//        }
//    }
//
//    /**
//     * <pre>
//     * Check Quotation.
//     * </pre>
//     * @return String Return Code
//     */
//    private String type09() {
//
//        IMPT_INV_QUOTTMsg tMsg = null;
//        if (ZYPCommonFunc.hasValue(this.keyListM.get(0))) {
//            tMsg = (IMPT_INV_QUOTTMsg) ZYPCodeDataUtil.findByCode(
//                    NLXSceConst.TBL_IMPT_INV_QUOT, this.glblCmpyCdM, this.keyListM.get(0));
//        }
//
//        if (tMsg != null) {
//            return NORMAL_CODE;
//        } else {
//            this.msgListM.add(NLAM1001E);
//            return ERROR_CODE;
//        }
//    }
//
//    /**
//     * <pre>
//     * Check Merchandise.
//     * </pre>
//     * @return String Return Code
//     * @throws SQLException SQLException
//     */
//    private String type10() throws SQLException {
//
//        String returnCode = null;
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put(SQL_PARA_GLBL_CMPY_CD, this.glblCmpyCdM);
//        queryParam.put(SQL_PARA_KEY1,         this.keyListM.get(0));
//        queryParam.put(SQL_PARA_RGTN_STS_CD,  RGTN_STS.TERMINATED);
//        PreparedStatement pstmt = null;
//        ResultSet ssmResult = null;
//
//        try {
//
//            // Find Merchandise.
//            pstmt = ssmLowLevelCodingClient.createPreparedStatement(CHECKTYPE_10, queryParam);
//            ssmResult = pstmt.executeQuery();
//
//            // Data does not exist.
//            if (!ssmResult.next()) {
//                this.msgListM.add(NLAM1001E);
//                return ERROR_CODE;
//            }
//
//            String rgtnStsCd = ssmResult.getString(RGTN_STS_CD);
//            if (RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING.equals(rgtnStsCd)
//                    || RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd)) {
//
//                // 1.10.4
//                String mdseNm      = ssmResult.getString(MDSE_NM);
//                String zthPrdCtlCd = ssmResult.getString(ZEROTH_PROD_CTRL_CD);
//                String fstPrdCtlCd = ssmResult.getString(FIRST_PROD_CTRL_CD);
//                String invtyCtrlCd = ssmResult.getString(INVTY_CTRL_FLG);
//                returnCode = setMdseNm(mdseNm, zthPrdCtlCd, fstPrdCtlCd, invtyCtrlCd);
//
//            } else {
//
//                // 1.10.3
//                // Find Merchandise Task Control Work.
//                Map<String, String> queryParamMdse = new HashMap<String, String>();
//                queryParamMdse.put(SQL_PARA_GLBL_CMPY_CD, this.glblCmpyCdM);
//                queryParamMdse.put(SQL_PARA_KEY1,         this.keyListM.get(0));
//                PreparedStatement pstmtMdse = ssmLowLevelCodingClient.createPreparedStatement(CHECKTYPE_10_3, queryParamMdse);
//                ResultSet ssmResultMdse = pstmtMdse.executeQuery();
//
//                try {
//
//                    boolean flgT0010 = false;
//                    boolean flgT0120 = false;
//                    boolean flgT0160 = false;
//                    boolean flgT0051 = false;
//
//                    // Get task code
//                    String taskCd = null;
//                    while (ssmResultMdse.next()) {
//                        taskCd = ssmResultMdse.getString(MDSE_TASK_CD);
//                        if (BASIC_TASK.equals(taskCd)) {
//                            flgT0010 = true;
//                        } else if (CUSTOMS_TASK.equals(taskCd)) {
//                            flgT0120 = true;
//                        } else if (COST_TASK.equals(taskCd)) {
//                            flgT0160 = true;
//                        } else if (SAFETY_TASK_CUSTOMS.equals(taskCd)) {
//                            flgT0051 = true;
//                        }
//                    }
//
//                    String mdseNm = null;
//                    String zthPrdCtlCd = null;
//                    String fstPrdCtlCd = null;
//                    String invtyCtrlCd = null;
//
//                    // Check Task Code.
//                    if (!flgT0010 && !flgT0120 && !flgT0160 && !flgT0051) {
//                        this.msgListM.add(NLAM1149E);
//                        return ERROR_CODE;
//                    }
//                    if (!flgT0010) {
//                        this.msgListM.add(NLAM1111E);
//
//                    } else {
//                        mdseNm      = ssmResult.getString(MDSE_NM);
//                        zthPrdCtlCd = ssmResult.getString(ZEROTH_PROD_CTRL_CD);
//                        fstPrdCtlCd = ssmResult.getString(FIRST_PROD_CTRL_CD);
//                        invtyCtrlCd = ssmResult.getString(INVTY_CTRL_FLG);
//                        returnCode = setMdseNm(mdseNm, zthPrdCtlCd, fstPrdCtlCd, invtyCtrlCd);
//                    }
//
//                    if (!flgT0120) {
//                        this.msgListM.add(NLAM1112E);
//                    }
//                    if (!flgT0160) {
//                        if (ZYPCommonFunc.hasValue(invtyCtrlCd) && FLG_ON_Y.equals(invtyCtrlCd)) {
//                            this.msgListM.add(NLAM1113E);
//                        }
//                    }
//                    if (!flgT0051) {
//                        this.msgListM.add(NLAM1114E);
//                    }
//
//                    if (this.msgListM != null && this.msgListM.size() > 0) {
//                        returnCode = ERROR_CODE;
//                    }
//
//                } finally {
//                    S21SsmLowLevelCodingClient.closeResource(pstmtMdse, ssmResultMdse);
//                }
//            }
//
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(pstmt, ssmResult);
//        }
//
//        return returnCode;
//    }
//
//    /**
//     * <pre>
//     * Check Merchandise for Parts.
//     * </pre>
//     * @return String Return Code
//     */
//    private String type11() {
//
//        String returnCode = NORMAL_CODE;
//
//        USMM4100TMsg tMsg4100 = new USMM4100TMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg4100.p_PartsNum, this.keyListM.get(0));
//        tMsg4100 = (USMM4100TMsg) EZDTBLAccessor.findByKey(tMsg4100);
//
//        if (tMsg4100 == null) {
//            this.msgListM.add(NLAM1001E);
//            return ERROR_CODE;
//        }
//
//        if (!ZYPCommonFunc.hasValue(tMsg4100.p_NumTsusT88.getValue())) {
//            this.msgListM.add(NLAM1112E);
//            returnCode = ERROR_CODE;
//        }
//
//        String pIndFcc = tMsg4100.p_IndFcc.getValue();
//        if (!checkFccInfo(pIndFcc)) {
//            returnCode = ERROR_CODE;
//        }
//        String pIndFda = tMsg4100.p_IndFda.getValue();
//        if (!checkFdaInfo(pIndFda)) {
//            returnCode = ERROR_CODE;
//        }
//        String pIndTsca = tMsg4100.p_IndTsca.getValue();
//        if (!checkTscaInfo(pIndTsca)) {
//            returnCode = ERROR_CODE;
//        }
//        String pDescPart = tMsg4100.p_DescPart.getValue();
//        if (ZYPCommonFunc.hasValue(pDescPart)) {
//            this.value1 = pDescPart;
//        }
//
//        return returnCode;
//    }
//
//    /**
//     * <pre>
//     * Check Original Country.
//     * </pre>
//     * @return String Return Code
//     */
//    private String type12() {
//
//        IMPT_INV_CTRY_ORIGTMsg tMsg = null;
//        if (ZYPCommonFunc.hasValue(this.keyListM.get(0))) {
//            tMsg = (IMPT_INV_CTRY_ORIGTMsg) ZYPCodeDataUtil.findByCode(
//                    NLXSceConst.TBL_IMPT_INV_CTRY_ORIG, this.glblCmpyCdM, this.keyListM.get(0));
//        }
//
//        if (tMsg != null) {
//            return NORMAL_CODE;
//        } else {
//            this.msgListM.add(NLAM1001E);
//            return ERROR_CODE;
//        }
//    }
//
//    /**
//     * <pre>
//     * Check Port.
//     * </pre>
//     * @return String Return Code
//     */
//    private String type13() {
//
//        IMPT_INV_PORTTMsg tMsg = null;
//        if (ZYPCommonFunc.hasValue(this.keyListM.get(0))) {
//            tMsg = (IMPT_INV_PORTTMsg) ZYPCodeDataUtil.findByCode(
//                    NLXSceConst.TBL_IMPT_INV_PORT, this.glblCmpyCdM, this.keyListM.get(0));
//        }
//
//        if (tMsg != null) {
//            return NORMAL_CODE;
//        } else {
//            this.msgListM.add(NLAM1001E);
//            return ERROR_CODE;
//        }
//    }
//
//    /**
//     * <pre>
//     * Check Unit.
//     * </pre>
//     * @return String Return Code
//     */
//    private String type14() {
//
//        IMPT_INV_UNITTMsg tMsg = null;
//        if (ZYPCommonFunc.hasValue(this.keyListM.get(0))) {
//            tMsg = (IMPT_INV_UNITTMsg) ZYPCodeDataUtil.findByCode(
//                    NLXSceConst.TBL_IMPT_INV_UNIT, this.glblCmpyCdM, this.keyListM.get(0));
//        }
//
//        if (tMsg != null) {
//            return NORMAL_CODE;
//        } else {
//            this.msgListM.add(NLAM1001E);
//            return ERROR_CODE;
//        }
//    }
//
//    /**
//     * <pre>
//     * Check anti Dumping.
//     * </pre>
//     * @return String Return Code
//     */
//    private String type15() {
//
//        String methKey2 = null;
//        String rtnCd = null;
//
//        if (!this.checkKeyList(1)) {
//            return ERROR_CODE;
//        }
//        methKey2 = this.keyListM.get(1);
//
////        if (!ZYPCommonFunc.hasValue(this.key2)) {
////            msgListM.add(NLAM1006E);
////            rtnCd = RTN_CD_ERROR;
////        } else {
////            methKey2 = keyListM.get(1);
////        }
//
//        MDSETMsg mdseTMsg = new MDSETMsg();
//        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, this.glblCmpyCdM);
//        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd,     this.keyListM.get(0));
//        mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);
//
//        if (mdseTMsg == null) {
//            this.msgListM.add(NLAM1001E);
//            return ERROR_CODE;
//        }
//
//        TRFTMsg tMsg = new TRFTMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCdM);
//        ZYPEZDItemValueSetter.setValue(tMsg.trfCd,      mdseTMsg.trfCd.getValue());
//        tMsg = (TRFTMsg) EZDTBLAccessor.findByKey(tMsg);
//
//        if (tMsg == null) {
//            this.msgListM.add(NLAM1001E);
//            return ERROR_CODE;
//        }
//
//        String antiDumpFlg = tMsg.antiDumpFlg.getValue();
//        if (ZYPCommonFunc.hasValue(antiDumpFlg)) {
//            if (ADC_FLG_0.equals(methKey2)
//                    || ADC_FLG_1.equals(methKey2)
//                    || ADC_FLG_9.equals(methKey2)) {
//                if (FLG_ON_Y.equals(antiDumpFlg)) {
//                    this.value1 = ADC_FLG_9;
//                } else if (FLG_OFF_N.equals(antiDumpFlg)) {
//                    this.value1 = ADC_FLG_1;
//                }
//            } else if (ADC_FLG_2.equals(methKey2)) {
//                this.value1 = ADC_FLG_2;
//            } else {
//                return ERROR_CODE;
//            }
//            rtnCd = NORMAL_CODE;
//        }
//
//        return rtnCd;
//    }
//
//    /**
//     * <pre>
//     * Check anti Dumping for parts.
//     * </pre>
//     * @return String Return Code
//     */
//    private String type16() {
//
//        String methKey2 = null;
//        String rtnCd = null;
//
//        if (!this.checkKeyList(1)) {
//            return ERROR_CODE;
//        }
//        methKey2 = this.keyListM.get(1);
//
////        if (!ZYPCommonFunc.hasValue(this.key2)) {
////            msgListM.add(NLAM1006E);
////            rtnCd = RTN_CD_ERROR;
////        } else {
////            methKey2 = keyListM.get(1);
////        }
//
//        USMM4100TMsg tMsg4100 = new USMM4100TMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg4100.p_PartsNum, this.keyListM.get(0));
//        tMsg4100 = (USMM4100TMsg) EZDTBLAccessor.findByKey(tMsg4100);
//
//        if (tMsg4100 == null) {
//            this.msgListM.add(NLAM1001E);
//            return ERROR_CODE;
//        }
//
//        TRFTMsg tMsg = new TRFTMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCdM);
//        ZYPEZDItemValueSetter.setValue(tMsg.trfCd, tMsg4100.p_NumTsusT88.getValue());
//        tMsg = (TRFTMsg) EZDTBLAccessor.findByKey(tMsg);
//
//        if (tMsg == null) {
//            this.msgListM.add(NLAM1001E);
//            return ERROR_CODE;
//        }
//
//        String antiDumpFlg = tMsg.antiDumpFlg.getValue();
//        if (ZYPCommonFunc.hasValue(antiDumpFlg)) {
//            if (ADC_FLG_0.equals(methKey2)
//                    || ADC_FLG_1.equals(methKey2)
//                    || ADC_FLG_9.equals(methKey2)) {
//                if (FLG_ON_Y.equals(antiDumpFlg)) {
//                    this.value1 = ADC_FLG_9;
//                } else if (FLG_OFF_N.equals(antiDumpFlg)) {
//                    this.value1 = ADC_FLG_1;
//                }
//            } else if (ADC_FLG_2.equals(methKey2)) {
//                this.value1 = ADC_FLG_2;
//            } else {
//                return ERROR_CODE;
//            }
//            rtnCd = NORMAL_CODE;
//        }
//
//        return rtnCd;
//    }
//
//    /**
//     * <pre>
//     * Check ISF Merchandise.
//     * </pre>
//     * @throws SQLException SQLException
//     */
//    private String type17() throws SQLException {
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put(SQL_PARA_GLBL_CMPY_CD, this.glblCmpyCdM);
//        queryParam.put(SQL_PARA_KEY1,         this.keyListM.get(0));
//        PreparedStatement pstmt = null;
//        ResultSet ssmResult = null;
//
//        try {
//
//            // Find Merchandise.
//            pstmt = ssmLowLevelCodingClient.createPreparedStatement(CHECKTYPE_17, queryParam);
//            ssmResult = pstmt.executeQuery();
//
//            // Data does not exist.
//            if (!ssmResult.next()) {
//                this.msgListM.add(NLAM1001E);  // mdse not found
//                this.msgListM.add(NLAM1112E);  // tariff not found
//                return ERROR_CODE;
//            }
//
//            // Set Merchandise Name.
//            this.value4 = ssmResult.getString(MDSE_CSTM_NM);
//
//            String trfCd = ssmResult.getString(IM_TRF_CD);
//            if (!ZYPCommonFunc.hasValue(trfCd)) {
//                this.msgListM.add(NLAM1112E);
//                return ERROR_CODE;
//            }
//
//            // Set Tariff Code
//            this.value5 = trfCd;
//
//            if (!ZYPCommonFunc.hasValue(ssmResult.getString(T_TRF_CD_ISF))) {
//                this.msgListM.add(NLAM1013E);
//                return ERROR_CODE;
//            }
//
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(pstmt, ssmResult);
//        }
//
//        return NORMAL_CODE;
//    }
//
//    /**
//     * <pre>
//     * Check Seller.
//     * </pre>
//     * @return String Return Code
//     */
//    private String type18() {
//
//        // Find Vendor.
//        VNDTMsg vndTmsg = new VNDTMsg();
//        vndTmsg.setConditionValue("glblCmpyCd01", this.glblCmpyCdM);
//        vndTmsg.setConditionValue("vndCd01",      this.keyListM.get(0));
//        vndTmsg.setMaxCount(1);
//        vndTmsg.setSQLID("023");
//        VNDTMsgArray vndTmsgArray =
//            (VNDTMsgArray) EZDTBLAccessor.findByCondition(vndTmsg);
//
//        if (vndTmsgArray.getValidCount() == 0) {
//            this.msgListM.add(NLAM1001E);
//            return ERROR_CODE;
//        }
//
//        // Set return parameter is Seller name.
//        vndTmsg = (VNDTMsg) vndTmsgArray.get(0);
//        this.value1 = vndTmsg.locNm.getValue();
//
//        return NORMAL_CODE;
//    }
//
//    /**
//     * <pre>
//     * Check Buyer.
//     * </pre>
//     * @return String Return Code
//     */
//    private String type19() {
//
//        // ISF Constant for Buyer.
//        IMPT_ISF_CONSTTMsg isfConstTmsg = new IMPT_ISF_CONSTTMsg();
//        isfConstTmsg.glblCmpyCd.setValue(this.glblCmpyCdM);
//        isfConstTmsg.isfConstCd.setValue(this.keyListM.get(0));
//        isfConstTmsg.isfConstTpCd.setValue(ISF_CONST_TP.BYR);
//        isfConstTmsg = (IMPT_ISF_CONSTTMsg) EZDTBLAccessor.findByKey(isfConstTmsg);
//
//        if (isfConstTmsg == null) {
//            this.msgListM.add(NLAM1001E);
//            return ERROR_CODE;
//        }
//
//        // Set return parameter is Buyer name.
//        this.value1 = isfConstTmsg.isfConstNm.getValue();
//
//        return NORMAL_CODE;
//    }
//
//    /**
//     * <pre>
//     * Check Merchandise with Tariff.
//     * </pre>
//     * @return String Return Code
//     * @throws SQLException SQLException
//     */
//    private String type20() throws SQLException {
//
//        String returnCode = NORMAL_CODE;
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put(SQL_PARA_GLBL_CMPY_CD, this.glblCmpyCdM);
//        queryParam.put(SQL_PARA_KEY1,         this.keyListM.get(0));
//        queryParam.put(SQL_PARA_RGTN_STS_CD,  RGTN_STS.TERMINATED);
//        PreparedStatement pstmt = null;
//        ResultSet ssmResult = null;
//
//        try {
//
//            // Find Merchandise.
//            pstmt = ssmLowLevelCodingClient.createPreparedStatement(CHECKTYPE_20, queryParam);
//            ssmResult = pstmt.executeQuery();
//
//            // Data does not exist.
//            if (!ssmResult.next()) {
//                this.msgListM.add(NLAM1001E);  // mdse not found
//                this.msgListM.add(NLAM1112E);  // tariff not found
//                return ERROR_CODE;
//            }
//
//            String rgtnStsCd = ssmResult.getString(RGTN_STS_CD);
//            if (RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING.equals(rgtnStsCd)
//                    || RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd)) {
//
//                // 1.10.4
//                this.value1 = ssmResult.getString(MDSE_NM);
//                this.value2 = ssmResult.getString(ZEROTH_PROD_CTRL_CD);
//                this.value3 = ssmResult.getString(FIRST_PROD_CTRL_CD);
//                this.value4 = ssmResult.getString(MDSE_CSTM_NM);
//
//            } else {
//
//                // 1.10.3
//                // Find Merchandise Task Control Work.
//                Map<String, String> queryParamMdse = new HashMap<String, String>();
//                queryParamMdse.put(SQL_PARA_GLBL_CMPY_CD, this.glblCmpyCdM);
//                queryParamMdse.put(SQL_PARA_KEY1,         this.keyListM.get(0));
//                PreparedStatement pstmtMdse = ssmLowLevelCodingClient.createPreparedStatement(CHECKTYPE_10_3, queryParamMdse);
//                ResultSet ssmResultMdse = pstmtMdse.executeQuery();
//
//                try {
//
//                    boolean flgT0010 = false;
//                    boolean flgT0120 = false;
//                    boolean flgT0160 = false;
//                    boolean flgT0051 = false;
//
//                    // Get task code
//                    String taskCd = null;
//                    while (ssmResultMdse.next()) {
//                        taskCd = ssmResultMdse.getString(MDSE_TASK_CD);
//                        if (BASIC_TASK.equals(taskCd)) {
//                            flgT0010 = true;
//                        } else if (CUSTOMS_TASK.equals(taskCd)) {
//                            flgT0120 = true;
//                        } else if (COST_TASK.equals(taskCd)) {
//                            flgT0160 = true;
//                        } else if (SAFETY_TASK_CUSTOMS.equals(taskCd)) {
//                            flgT0051 = true;
//                        }
//                    }
//
//                    // Check Task Code.
//                    if (!flgT0010 && !flgT0120 && !flgT0160 && !flgT0051) {
//                        this.msgListM.add(NLAM1149E);
//                        return ERROR_CODE;
//                    }
//                    if (!flgT0010) {
//                        this.msgListM.add(NLAM1111E);
//                    }
//                    if (!flgT0120) {
//                        this.msgListM.add(NLAM1112E);
//                    }
//                    if (!flgT0160) {
//                        this.msgListM.add(NLAM1113E);
//                    }
//                    if (!flgT0051) {
//                        this.msgListM.add(NLAM1114E);
//                    }
//
//                    if (this.msgListM != null && this.msgListM.size() > 0) {
//                        returnCode = ERROR_CODE;
//                    } else {
//                        // 1.10.4
//                        this.value1 = ssmResult.getString(MDSE_NM);
//                        this.value2 = ssmResult.getString(ZEROTH_PROD_CTRL_CD);
//                        this.value3 = ssmResult.getString(FIRST_PROD_CTRL_CD);
//                        this.value4 = ssmResult.getString(MDSE_CSTM_NM);
//                    }
//
//                } finally {
//                    S21SsmLowLevelCodingClient.closeResource(pstmtMdse, ssmResultMdse);
//                }
//            }
//
//            // Check Tariff Master.
//            if (!this.msgListM.contains(NLAM1112E)) {
//
//                this.value5 = ssmResult.getString(TRF_CD);
//                this.value6 = ssmResult.getString(IM_TRF_CD);
//                this.value7 = ssmResult.getString(T_TRF_CD);
//                this.value8 = ssmResult.getString(T_TRF_CD_ISF);
//
//                if (!ZYPCommonFunc.hasValue(this.value5)
//                        || !ZYPCommonFunc.hasValue(this.value7)) {
//
//                    // Check Tariff code
//                    if (!ZYPCommonFunc.hasValue(this.value5)
//                            || !ZYPCommonFunc.hasValue(this.value6)) {
//                        this.msgListM.add(NLAM1112E);
//                        returnCode = ERROR_CODE;
//                    }
//
//                    // Check Tariff master
//                    if (!ZYPCommonFunc.hasValue(this.value7)
//                            || !ZYPCommonFunc.hasValue(this.value8)) {
//                        this.msgListM.add(NLAM1013E);
//                        returnCode  = ERROR_CODE;
//                    }
//                }
//
//            } else {
//                this.value9 = FLG_ON_Y;
//            }
//
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(pstmt, ssmResult);
//        }
//
//        return returnCode;
//    }
//
//    /**
//     * <pre>
//     * Check Merchandise for Parts with Tariff.
//     * </pre>
//     * @return String Return Code
//     * @throws SQLException SQLException
//     */
//    private String type21() throws SQLException {
//
//        String returnCode = NORMAL_CODE;
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put(SQL_PARA_GLBL_CMPY_CD, this.glblCmpyCdM);
//        queryParam.put(SQL_PARA_KEY1,         this.keyListM.get(0));
//        PreparedStatement pstmt = null;
//        ResultSet ssmResult = null;
//
//        try {
//
//            // Find Merchandise.
//            pstmt = ssmLowLevelCodingClient.createPreparedStatement(CHECKTYPE_21, queryParam);
//            ssmResult = pstmt.executeQuery();
//
//            if (!ssmResult.next()) {
//                this.msgListM.add(NLAM1001E);  // mdse not found
//                this.msgListM.add(NLAM1112E);  // tariff not found
//                return ERROR_CODE;
//            }
//
//            // Set Merchandise Customs Name
//            this.value4 = ssmResult.getString(MDSE_CSTM_NM);
//            this.value5 = ssmResult.getString(TRF_CD);
//            this.value6 = ssmResult.getString(IM_TRF_CD);
//            this.value7 = ssmResult.getString(T_TRF_CD);
//            this.value8 = ssmResult.getString(T_TRF_CD_ISF);
//
//            if (!ZYPCommonFunc.hasValue(this.value5)
//                    || !ZYPCommonFunc.hasValue(this.value7)) {
//
//                // Check Tariff code
//                if (!ZYPCommonFunc.hasValue(this.value5)
//                        || !ZYPCommonFunc.hasValue(this.value6)) {
//                    this.msgListM.add(NLAM1112E);
//                    returnCode = ERROR_CODE;
//                }
//
//                // Check Tariff master
//                if (!ZYPCommonFunc.hasValue(this.value7)
//                        || !ZYPCommonFunc.hasValue(this.value8)) {
//                    this.msgListM.add(NLAM1013E);
//                    returnCode  = ERROR_CODE;
//                }
//            }
//
//            String pIndFcc = ssmResult.getString(P_IND_FCC);
//            if (!checkFccInfo(pIndFcc)) {
//                returnCode = ERROR_CODE;
//            }
//            String pIndFda = ssmResult.getString(P_IND_FDA);
//            if (!checkFdaInfo(pIndFda)) {
//                returnCode = ERROR_CODE;
//            }
//            String pIndTsca = ssmResult.getString(P_IND_TSCA);
//            if (!checkTscaInfo(pIndTsca)) {
//                returnCode = ERROR_CODE;
//            }
//            String pDescPart = ssmResult.getString(P_DESC_PART);
//            if (ZYPCommonFunc.hasValue(pDescPart)) {
//                this.value1 = pDescPart;
//            }
//
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(pstmt, ssmResult);
//        }
//
//        return returnCode;
//    }
//
//    /**
//     * <pre>
//     * Check for ImptInvCarrCd.
//     * </pre>
//     * @return String Return Code
//     * @throws SQLException SQLException
//     */
//    private String type22() throws SQLException {
//
//        String returnCode = NORMAL_CODE;
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put(SQL_PARA_GLBL_CMPY_CD, this.glblCmpyCdM);
//        queryParam.put(SQL_PARA_KEY1,         this.keyListM.get(0));
//        queryParam.put(SQL_PARA_VND_TP_CD,    VND_TP.DIVERSION);
//        PreparedStatement pstmt = null;
//        ResultSet ssmResult = null;
//
//        try {
//            // Find Vendor(Diversion).
//            pstmt = ssmLowLevelCodingClient.createPreparedStatement(CHECKTYPE_22, queryParam);
//            ssmResult = pstmt.executeQuery();
//
//            if (!ssmResult.next()) {
//                this.msgListM.add(NLAM1001E);  // vnd not found
//                return ERROR_CODE;
//            }
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(pstmt, ssmResult);
//        }
//        return returnCode;
//    }
//
//
//    /**
//     * <pre>
//     * Check Mercahndise Name.
//     * </pre>
//     * @param mdseNm Merchandise Name
//     * @param fstPrdCtlCd First Product Control Code
//     * @return String Return Code
//     */
//    private String setMdseNm(String mdseNm, String zthPrdCtlCd, String fstPrdCtlCd, String invtyCtrlCd) {
//
//            this.value1 = mdseNm;
//            this.value2 = zthPrdCtlCd;
//            this.value3 = fstPrdCtlCd;
//            this.value6 = invtyCtrlCd;
//            return NORMAL_CODE;
//    }
//
//    /**
//     * <pre>
//     * Check FCC Informations.
//     * </pre>
//     * @param  pIndFcc FCC
//     * @return boolean result
//     */
//    private boolean checkFccInfo(String pIndFcc) {
//
//        boolean blnErr = true;
//
//        if (FLG_ON_Y.equals(pIndFcc)) {
//            USMT4900TMsg tMsg4900 = new USMT4900TMsg();
//            ZYPEZDItemValueSetter.setValue(tMsg4900.p_PartsNum, this.keyListM.get(0));
//            tMsg4900 = (USMT4900TMsg) EZDTBLAccessor.findByKey(tMsg4900);
//
//            if (tMsg4900 == null) {
//                this.msgListM.add(NLAM1154E);
//                return false;
//            }
//
//            if (!checkFccDtl(tMsg4900)) {
//                this.msgListM.add(NLAM1115E);
//                blnErr = false;
//            }
//
//            return blnErr;
//
//        } else {
//            blnErr = true;
//        }
//
//        return blnErr;
//    }
//
//    /**
//     * <pre>
//     * Check FDA Informations.
//     * </pre>
//     * @param  pIndFda FDA
//     * @return boolean result
//     */
//    private boolean checkFdaInfo(String pIndFda) {
//
//        boolean blnErr = true;
//
//        if (FLG_ON_Y.equals(pIndFda)) {
//            USMT4900TMsg tMsg4900 = new USMT4900TMsg();
//            ZYPEZDItemValueSetter.setValue(tMsg4900.p_PartsNum, this.keyListM.get(0));
//            tMsg4900 = (USMT4900TMsg) EZDTBLAccessor.findByKey(tMsg4900);
//
//            if (tMsg4900 == null) {
//                this.msgListM.add(NLAM1154E);
//                return false;
//            }
//
//            if (!checkFdaDtl(tMsg4900)) {
//                this.msgListM.add(NLAM1116E);
//                blnErr = false;
//            }
//
//            return blnErr;
//
//        } else {
//            blnErr = true;
//        }
//
//        return blnErr;
//    }
//
//    /**
//     * <pre>
//     * Check TSCA Informations.
//     * </pre>
//     * @param  pIndTsca TSCA
//     * @return boolean  result
//     */
//    private boolean checkTscaInfo(String pIndTsca) {
//
//        boolean blnErr = false;
//
//        if (FLG_ON_Y.equals(pIndTsca)) {
//            blnErr = true;
//
//        } else {
//            blnErr = true;
//        }
//
//        return blnErr;
//    }
//
//    /**
//     * <pre>
//     * Check FCC Details.
//     * </pre>
//     * @param  tMsg4900 USMT4900TMsg
//     * @return boolean  result
//     */
//    private boolean checkFccDtl(USMT4900TMsg tMsg4900) {
//
//        boolean checkResFlg = true;
//
//        String pIndFcc15     = tMsg4900.p_IndFccPart15.getValue();
//        String pIndFcc68     = tMsg4900.p_IndFccPart68.getValue();
//        String pFccRegNo     = tMsg4900.p_FccRegNo.getValue();
//        String pIndFccVerif  = tMsg4900.p_IndFccVerif.getValue();
//        String pIndFccCertif = tMsg4900.p_IndFccCertif.getValue();
//        String pIndFccDoc    = tMsg4900.p_IndFccDoc.getValue();
//        String pFccId        = tMsg4900.p_FccId.getValue();
//        String pFccForm740   = tMsg4900.p_FccForm740.getValue();
//        String pFccMfrId     = tMsg4900.p_FccMfrId.getValue();
//        String pFccTradeName = tMsg4900.p_FccTradeName.getValue();
//
//        if (!ZYPCommonFunc.hasValue(pIndFcc15)) {
//            checkResFlg = false;
//        }
//
//        if (!ZYPCommonFunc.hasValue(pIndFcc68)) {
//            checkResFlg = false;
//
//        } else {
//
//            if (FLG_ON_Y.equals(pIndFcc68)
//                    && !ZYPCommonFunc.hasValue(pFccRegNo)) {
//                checkResFlg = false;
//            }
//        }
//
//        if (!ZYPCommonFunc.hasValue(pIndFccVerif)
//                && !ZYPCommonFunc.hasValue(pIndFccCertif)
//                && !ZYPCommonFunc.hasValue(pIndFccDoc)) {
//            checkResFlg = false;
//        }
//
//        if (!FLG_ON_Y.equals(pIndFccVerif)
//                && !FLG_ON_Y.equals(pIndFccCertif)
//                && !FLG_ON_Y.equals(pIndFccDoc)) {
//            checkResFlg = false;
//        }
//
//        if (FLG_ON_Y.equals(pIndFccCertif)
//                && !ZYPCommonFunc.hasValue(pFccId)) {
//            checkResFlg = false;
//        }
//
//        if (!ZYPCommonFunc.hasValue(pFccForm740)
//                || !ZYPCommonFunc.hasValue(pFccMfrId)
//                || !ZYPCommonFunc.hasValue(pFccTradeName)) {
//            checkResFlg = false;
//        }
//
//        return checkResFlg;
//    }
//
//    /**
//     * <pre>
//     * Check FDA Details.
//     * </pre>
//     * @param  tMsg4900 USMT4900TMsg
//     * @return boolean  result
//     */
//    private boolean checkFdaDtl(USMT4900TMsg tMsg4900) {
//
//        boolean checkResFlg = true;
//
//        String pIndFdaEqu      = tMsg4900.p_IndFdaEqu.getValue();
//        String pFdaAccNo       = tMsg4900.p_FdaAccNo.getValue();
//        String pCodeItemFdaEqu = tMsg4900.p_CodeItemFdaEqu.getValue();
//        String pFdaProdType    = tMsg4900.p_FdaProdType.getValue();
//        String pIndFdaDev      = tMsg4900.p_IndFdaDev.getValue();
//        String pFdaDcNo        = tMsg4900.p_FdaDcNo.getValue();
//        String pFdaDocType     = tMsg4900.p_FdaDocType.getValue();
//        String pFdaType        = tMsg4900.p_FdaType.getValue();
//        String pFdaCategory    = tMsg4900.p_FdaCategory.getValue();
//        String pFdaRegNo       = tMsg4900.p_FdaRegNo.getValue();
//        String pCodeItemFdaDev = tMsg4900.p_CodeItemFdaDev.getValue();
//        String pFdaMfrId       = tMsg4900.p_FdaMfrId.getValue();
//        String pFdaTradeName   = tMsg4900.p_FdaTradeName.getValue();
//
//        if (ZYPCommonFunc.hasValue(pIndFdaEqu)) {
//            if (FLG_ON_Y.equals(pIndFdaEqu)) {
//                if (!ZYPCommonFunc.hasValue(pFdaAccNo)
//                        || !ZYPCommonFunc.hasValue(pCodeItemFdaEqu)
//                        || !ZYPCommonFunc.hasValue(pFdaProdType)) {
//                    checkResFlg = false;
//                }
//            }
//
//        } else {
//            checkResFlg = false;
//        }
//
//        if (ZYPCommonFunc.hasValue(pIndFdaDev)) {
//            if (FLG_ON_Y.equals(pIndFdaDev)) {
//                if (!ZYPCommonFunc.hasValue(pFdaDcNo)
//                        || !ZYPCommonFunc.hasValue(pFdaDocType)
//                        || !ZYPCommonFunc.hasValue(pFdaType)
//                        || !ZYPCommonFunc.hasValue(pFdaCategory)
//                        || !ZYPCommonFunc.hasValue(pFdaRegNo)
//                        || !ZYPCommonFunc.hasValue(pCodeItemFdaDev)) {
//                    checkResFlg = false;
//                }
//            }
//
//        } else {
//            checkResFlg = false;
//        }
//
//        if (!ZYPCommonFunc.hasValue(pFdaMfrId)
//                || !ZYPCommonFunc.hasValue(pFdaTradeName)) {
//            checkResFlg = false;
//        }
//
//        return checkResFlg;
//    }

}
