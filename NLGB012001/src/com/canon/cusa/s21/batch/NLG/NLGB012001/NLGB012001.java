/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB012001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CAL_RELNTMsg;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.NLBI0040_01TMsg;
import business.db.SHIP_SER_NUM_WRKTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_CONF_DTL_WRKTMsg;
import business.db.SHPG_ORD_CONF_WRKTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SHPG_ORD_DTLTMsgArray;
import business.db.SHPG_ORD_PRO_NUM_LISTTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.db.WMS_INBD_SO_HDRTMsg;
import business.db.WMS_INBD_TRXTMsg;
import business.db.WMS_SO_ASN_DTLTMsg;
import business.db.WMS_SO_ASN_HDRTMsg;
import business.parts.NLZC060001PMsg;
import business.parts.NLZC211001PMsg;
import business.parts.NLZC400001PMsg;
import business.parts.NLZC401001PMsg;

import com.canon.cusa.s21.api.NLG.NLGC001001.NLGC001001;
import com.canon.cusa.s21.api.NLZ.NLZC060001.NLZC060001;
import com.canon.cusa.s21.api.NLZ.NLZC060001.constant.NLZC060001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001;
import com.canon.cusa.s21.api.NLZ.NLZC400001.NLZC400001;
import com.canon.cusa.s21.api.NLZ.NLZC401001.NLZC401001;
import com.canon.cusa.s21.api.NLZ.NLZC401001.constant.NLZC401001Constant;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC014001.NLXC014001;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INTFC_ID;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TASK;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TRNSP_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Batch Program Class for Translate SOConf/PKT/ASN from WMS.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/20/2013   CSAI            K.Kondo         Create          MW Replace Initial
 * 08/20/2015   CSAI            K.Hayashida     Update          ITG#598663 - [CUSA] ASN issue due to multi-load shipment
 * 09/02/2015   CSAI            K.Hayashida     Update          ITG#601006 - [CUSA] S21MW can not process SO confirmation due to memory overflow
 * 09/11/2015   CSAI            K.Hayashida     Update          ITG#591081 - [CUSA] Add search condition to Carrier Mapping Maintenance Screen
 * 11/18/2015   CSAI            K.Hayashida     Update          ITG#613664 - [CUSA] S21MW receives multiple freight charges per SO from WMS
 * 11/30/2015   CSAI            K.Hayashida     Update          ITG#610899 - [CUSA] Performance Improvement for receiving SO confirmation of Tecsys at S21MW
 * 10/21/2016   CSAI            Y.Imazu         Update          QC#15512
 * 10/24/2016   CSAI            T.Tokutomi      Update          QC#14995
 * 11/25/2016   CITS            Y.IWASAKI       Update          QC#16126
 * 04/05/2017   CITS            R.Shimamoto     Update          QC#17981
 * 04/25/2017   Hitachi         K.Kojima        Update          QC#16301
 * 06/02/2017   CITS            M.Naito         Update          QC#18757
 * 07/18/2017   CITS            K.Ogino         Update          QC#19967
 * 07/19/2017   CITS            K.Ogino         Update          QC#19978
 * 08/15/2017   CITS            K.Ogino         Update          QC#20554
 * 09/07/2017   CITS            T.Wada          Update          QC#19930
 * 2017/09/27   CITS            T.Hakodate      Update          QC#21433
 * 2018/02/14   CITS            T.Hakodate      Update          QC#22613
 * 2018/02/27   CITS            T.Hakodate      Update          QC#21913
 * 2018/03/27   CITS            K.Ogino         Update          QC#18794
 * 2018/11/20   Fujitsu         H.Kumagai       Update          QC#28955
 * 01/25/2019   CITS            T.Tokutomi      Update          QC#29979
 * 02/06/2019   Fujitsu         T.Ogura         Update          QC#30182
 * 02/26/2019   CITS            K.Ogino         Update          QC#30522
 * 08/07/2019   CITS            T.Wada          Update          QC#52104
 * 08/12/2019   CITS            K.Ogino         Update          QC#50971
 * 09/02/2019   CITS            R.Shimamoto     Update          QC#53233
 * 09/03/2019   CITS            K.Ogino         Update          QC#53009
 * 10/08/2019   CITS            K.Ogino         Update          QC#53872
 * 11/01/2019   CITS            T.Wada          Update          QC#54135
 * 11/01/2019   CITS            T.Wada          Update          QC#54510
 * 11/07/2019   CITS            T.Wada          Update          QC#52557
 * 11/07/2019   CITS            T.Wada          Update          QC#52802
 * 11/28/2019   CITS            R.Shimamoto     Update          QC#54812
 * 12/07/2019   CITS            T.Wada          Update          QC#54910
 * 01/14/2020   CITS            T.Wada          Update          QC#55361
 * 02/12/2020   CITS            T.Wada          Update          QC#55713
 * 05/25/2020   Fujitsu         T.Ogura         Update          QC#56612
 * 05/26/2020   Fujitsu         T.Ogura         Update          QC#56691
 * 11/20/2020   CITS            A.Marte         Update          QC#57659
 * 05/25/2021   CITS            K.Ogino         Update          QC#58853
 * 08/27/2021   CITS            R.Azucena       Update          QC#59036
 * 03/08/2022   CITS            M.Furugoori     Update          QC#59764
 * 04/04/2022   CITS            R.Azucena       Update          QC#59802
 * 04/21/2022   CITS            K.Iwamoto       Update          QC#59763
 * 04/25/2022   CITS            R.Azucena       Update          QC#59802-1
 * 08/08/2022   CITS            R.Azucena       Update          QC#60416
 * 11/11/2022   CITS            F.Fadriquela    Update          QC#60722
 *</pre>
 */
public class NLGB012001 extends S21BatchMain implements NLGB012001Constant {

    // *********************************************************
    // Instance Variables: Basic
    // *********************************************************
    /** Termination Code of Execution Process */
    private TERM_CD termCd = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** Global Company Code */
    private String glblCmpyCd = "";

    /** Warehouse Group Cd */
    private String whGpCd = "";

    /** MW Replace Group : 3PL */
    private static final String WH_GP_CD_3_THIRD_PARTY_WH = "3";

    /** Source Type : WMS */
    private static final String SRC_TP_CD_WMS = "W";

    /** Message Type : Error */
    private static final String MSG_TYPE_ERROR = "E";

    /** Batch Process Code */
    private String batProcCd = "";

    /** Counter of Records: Successful */
    private int normalRecCount = 0;

    /** Counter of Records: Error */
    private int totalErrCount = 0;

    /** Target Warehouse Code */
    private String[] trgtWhCdAry = null;

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = new ArrayList<Map<String, String>>();

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Error SO Number Map */
    private HashMap<String, String> soNumErrMap = new HashMap<String, String>();

    // ITG#591081 ADD START
    /** Error Map for missing carrier code of SO Confirmation */
    private Map<String, List<Map<String, Object>>> soConfCarrErrListMap = new HashMap<String, List<Map<String, Object>>>();

    /** Error Map for missing carrier code of ASN */
    private Map<String, List<Map<String, String>>> asnCarrErrListMap = new HashMap<String, List<Map<String, String>>>();
    // ITG#591081 ADD END

    /** Cache Map for CAL_RELN */
    private S21LRUMap<String, CAL_RELNTMsg> calRelnMap = new S21LRUMap<String, CAL_RELNTMsg>(100);

    // QC#17981 Add.
    /** NLGB0120_WMS_ORD_TP_LIST */
    private String[] wmsOrdTpListFromVarChar = null;

    /** NLGB0120_VALIDATE_SO_CONF_DATA */
    private String[] validateSoConfDataWmsOrdTp = null;

    /** NLGB0120_CALC_FREIGHT_CHARGE */
    private String[] calculateFreightChargeWmsOrdTp = null;

    /** NLGB0120_UPD_GETWMSINBDSOHDR */
    private String[] updateGetWmsInbdSoHdrWmsOrdTp = null;
    
    // QC#21433 T.Hakodate ADD START
    /** NLGB0120_ADD_SO_CANCEL_TP_LIST */
    private String[] additionalSoCancelOrdTpList = null;
    // QC#21433 T.Hakodate ADD END
    
    /** MW Replace Group : DBS */
    private static final String WH_GP_CD_2_DBS = "2";

    // START 2022/04/04 R.Azucena [QC#59802, ADD]
    // START 2022/08/08 R.Azucena [QC#60416, DEL]
    /** Available Quantity IB Map */
    // private Map<String, BigDecimal> availQtyIBMap = new HashMap<String, BigDecimal>();
    // END 2022/08/08 R.Azucena [QC#60416, DEL]
    // END 2022/04/04 R.Azucena [QC#59802, ADD]

    // *********************************************************
    // Methods
    // *********************************************************
    /**
     * Startups.
     * @param args arcuments
     */
    public static void main(String[] args) {

        new NLGB012001().executeBatch(NLGB012001.class.getSimpleName());
    }

    /**
     * Initialization Routine.
     */
    @Override
    protected void initRoutine() {

        // Initialize Variables
        this.termCd = TERM_CD.NORMAL_END;
        errMsgList = new ArrayList<Map<String, String>>();

        // Getting Global Company Code
        S21UserProfileServiceFactory profFactory = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService prof = profFactory.getService();
        glblCmpyCd = prof.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {

            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_GLBL_CMPY_CD });
        }

        // Getting Excute Warehouse Group Code
        whGpCd = S21BatchUtil.getUserVariable1();

        if (!ZYPCommonFunc.hasValue(whGpCd)) {

            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_WH_GRP_CD });
        }

        // Getting Excute Batch Process Code
        batProcCd = S21BatchUtil.getUserVariable2();

        if (!ZYPCommonFunc.hasValue(batProcCd)) {

            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_BAT_PROCESS_CD });
        }
    }

    /**
     * Main Routine.
     */
    @Override
    protected void mainRoutine() {

        try {

            if (!WH_GP_CD_3_THIRD_PARTY_WH.equals(whGpCd)) {

                trgtWhCdAry = NLGC001001.getTrgtWhCd(glblCmpyCd, whGpCd);

                if (trgtWhCdAry == null) {

                    outputErr(NLGM0047E, new String[] {whGpCd });
                }
            }

            if (WH_GP_CD_3_THIRD_PARTY_WH.equals(whGpCd) || trgtWhCdAry != null) {

                // QC#17981 Add.
                wmsOrdTpListFromVarChar = ZYPCodeDataUtil.getVarCharConstValue(NLGB0120_WMS_ORD_TP_LIST, glblCmpyCd).split(",");
                validateSoConfDataWmsOrdTp = ZYPCodeDataUtil.getVarCharConstValue(NLGB0120_VALIDATE_SO_CONF_DATA, glblCmpyCd).split(",");
                calculateFreightChargeWmsOrdTp = ZYPCodeDataUtil.getVarCharConstValue(NLGB0120_CALC_FREIGHT_CHARGE, glblCmpyCd).split(",");
                updateGetWmsInbdSoHdrWmsOrdTp = ZYPCodeDataUtil.getVarCharConstValue(NLGB0120_UPD_GETWMSINBDSOHDR, glblCmpyCd).split(",");
                // QC#21433 T.Hakodate ADD START
                additionalSoCancelOrdTpList = ZYPCodeDataUtil.getVarCharConstValue("NLGB0120_ADD_SO_CANCEL_TP_LIST", glblCmpyCd).split(",");
                // QC#21433 T.Hakodate ADD END

                if (SO_CONF_FLOW.equals(batProcCd)) {

                    doProcSoFrtChrg();
                    doProcUpdSoConf();

                } else if (PKT_STS_FLOW.equals(batProcCd)) {

                    doProcPktLscSts();
                    doProcPktOscSts();

                } else if (ASN_FLOW.equals(batProcCd)) {

                    doProcAsn();
                }
            }
        } finally {

            // ITG#591081 ADD START
            if (!soConfCarrErrListMap.isEmpty()) {

                sendSoConfErrMail();
            }

            if (!asnCarrErrListMap.isEmpty()) {

                sendAsnErrMail();
            }
            // ITG#591081 ADD END

            if (!errMsgList.isEmpty()) {

                // START 2022/03/08 [QC#59764,ADD]
                String[] mlGrps = null;
                String varCharConst = ZYPCodeDataUtil.getVarCharConstValue(NLGB0120_ML_GRP, glblCmpyCd);
                if (ZYPCommonFunc.hasValue(varCharConst)) {
                    mlGrps = varCharConst.split(",");
                }
                // END 2022/03/08 [QC#59764,ADD]

                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                // START 2022/03/08 [QC#59764,MOD]
//                mail.send(BUSINESS_ID, errMsgList);
                if (mlGrps != null) {
                    mail.send(BUSINESS_ID, errMsgList, mlGrps);
                } else {
                    mail.send(BUSINESS_ID, errMsgList);
                }
                // END 2022/03/08 [QC#59764,MOD]
                commit();
            }
        }
    }

    /**
     * Termination Routine.
     */
    @Override
    protected void termRoutine() {

        // Setting Process Termination Code
        setTermState(this.termCd, this.normalRecCount, this.totalErrCount, this.normalRecCount + this.totalErrCount);
    }

    /**
     * Calculate & Update Freight Charge
     */
    private void doProcSoFrtChrg() {

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Map<String, Object>> oshpList = new ArrayList<Map<String, Object>>();
        ArrayList<Map<String, Object>> shipList = new ArrayList<Map<String, Object>>();
        ArrayList<Map<String, Object>> ochgList = new ArrayList<Map<String, Object>>();

        try {

            /*************************************************************
             * 1. Get WMS_INBD_TRX Data (OSHP)
             ************************************************************/
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            HashMap<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);

            if (!WH_GP_CD_3_THIRD_PARTY_WH.equals(whGpCd)) {

                queryParam.put("wmsTrgtWhCd", trgtWhCdAry);
            }

            queryParam.put("wmsTaskOshp", WMS_TASK.OSHP);

            List<String> wmsOrdTpList = new ArrayList<String>();

            for (String wmsOrdTpCd : wmsOrdTpListFromVarChar) {

                wmsOrdTpList.add(wmsOrdTpCd);
            }

            queryParam.put("wmsOrdTpCd", wmsOrdTpList.toArray(new String[wmsOrdTpList.size()]));
            queryParam.put("procStsCdComp", PROC_STS.COMPLEATED);
            queryParam.put("procStsCdInComp", PROC_STS.IN_COMPLETED);

            if (WH_GP_CD_3_THIRD_PARTY_WH.equals(whGpCd)) {

                stmt = this.ssmLLClient.createPreparedStatement("getSoConfOshpForTpl", queryParam, execParam);

            } else {

                stmt = this.ssmLLClient.createPreparedStatement("getSoConfOshp", queryParam, execParam);
            }

            rs = stmt.executeQuery();

            setSoConfDataHdr(rs, oshpList); // ITG#610899 ADD

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }

        /*************************************************************
         * 2. Get WMS_INBD_TRX Data (SHIP/OCHG)
         ************************************************************/
        setSoConfDataDtl(oshpList, shipList, ochgList); // ITG#610899 ADD

        // Processing status is updated when an error is not
        // canceled, even if it passes over a definite period of time.
        long chkMinIntvl = 0;
        String chkAsnMinIntvl = ZYPCodeDataUtil.getVarCharConstValue(VLD_INTVL_SO_CONF_MIN, glblCmpyCd);

        if (ZYPCommonFunc.hasValue(chkAsnMinIntvl)) {

            chkMinIntvl = Long.parseLong(chkAsnMinIntvl);

        } else {

            chkMinIntvl = DEF_SO_CONF_MIN_INTVL;
        }

        String chkDtTmTs = EZDCommonFunc.toyyyyMMddHHmmss(EZDCommonFunc.toTimeMinute(ZYPDateUtil.getCurrentSystemTime(CUR_DT_TM_FMT)) //
                - (chkMinIntvl * MIN_CALC_MULT_VAL));

        for (Map<String, Object> oshpMap : oshpList) {

            String errorCode = "";
            BigDecimal oshpTrxPk = (BigDecimal) oshpMap.get("WMS_INBD_TRX_PK");
            String otbdOrdNum = (String) oshpMap.get("OTBD_ORD_NUM");
            String otbdOrdTpCd = (String) oshpMap.get("OTBD_ORD_TP_CD");
            String wmsTaskCd = (String) oshpMap.get("WMS_TASK_CD");
            String ezintime = (String) oshpMap.get("EZINTIME");

            if (!Arrays.asList(validateSoConfDataWmsOrdTp).contains(otbdOrdTpCd)) {

                /*************************************************************
                 * 3. Validation
                 ************************************************************/
                // Check INTFC_PROC_STS_CD is (1:Completed)
                for (Map<String, Object> shipMap : shipList) {

                    BigDecimal oshpTrxPkForShip = (BigDecimal) shipMap.get("OSHP_WMS_INBD_TRX_PK");
                    String intfcProcStsCd = (String) shipMap.get("INTFC_PROC_STS_CD");

                    if (oshpTrxPk.equals(oshpTrxPkForShip) && !PROC_STS.COMPLEATED.equals(intfcProcStsCd)) {

                        // QC#52557 Mod Start
//                        errorCode = NSZM0961E;
//                      outputErr(NSZM0961E, new String[] {"INTFC_PROC_STS_CD", PROC_STS.ERROR });
                        BigDecimal shpTrxPk = (BigDecimal) shipMap.get("WMS_INBD_TRX_PK");
                        String strShpTrxPk = "";
                        if (ZYPCommonFunc.hasValue(shpTrxPk)) {
                            strShpTrxPk = shpTrxPk.toString();
                        }
                        outputErr("NLGM0088E", new String[] {strShpTrxPk, otbdOrdNum});
                        // QC#52557 Mod End

                        // QC#52104 Add Start
                        String intfcErrMsgCd = (String) shipMap.get("INTFC_ERR_MSG_CD");
                        if (!isRetry(intfcErrMsgCd)) {
                            updErrorTrxProcStsSoConf(oshpTrxPk, shipList, ochgList, errorCode);
                            outputErr(NLGM0067I, new String[] {oshpTrxPk.toString(), otbdOrdNum });
                        }
                        // QC#52104 Add End

                        if (chkDtTmTs.compareTo(ezintime) > 0) {

                            updErrorTrxProcStsSoConf(oshpTrxPk, shipList, ochgList, errorCode);
                            outputErr(NLGM0067I, new String[] {oshpTrxPk.toString(), otbdOrdNum });
                        }

                        soNumErrMap.put(otbdOrdNum, otbdOrdNum);
                        continue;
                    }
                }

                for (Map<String, Object> ochgMap : ochgList) {

                    BigDecimal oshpTrxPkForOchg = (BigDecimal) ochgMap.get("OSHP_WMS_INBD_TRX_PK");
                    String intfcProcStsCd = (String) ochgMap.get("INTFC_PROC_STS_CD");

                    if (oshpTrxPk.equals(oshpTrxPkForOchg) && !PROC_STS.COMPLEATED.equals(intfcProcStsCd)) {

                        // QC#52557 Mod Start
//                        errorCode = NSZM0961E;
//                      outputErr(NSZM0961E, new String[] {"INTFC_PROC_STS_CD", PROC_STS.ERROR });
                        BigDecimal shpTrxPk = (BigDecimal) ochgMap.get("WMS_INBD_TRX_PK");
                        String strShpTrxPk = "";
                        if (ZYPCommonFunc.hasValue(shpTrxPk)) {
                            strShpTrxPk = shpTrxPk.toString();
                        }
                        outputErr("NLGM0088E", new String[] {strShpTrxPk, otbdOrdNum});
                        // QC#52557 Mod End

                        // QC#52104 Add Start
                        String intfcErrMsgCd = (String) ochgMap.get("INTFC_ERR_MSG_CD");
                        if (!isRetry(intfcErrMsgCd)) {
                            updErrorTrxProcStsSoConf(oshpTrxPk, shipList, ochgList, errorCode);
                            outputErr(NLGM0067I, new String[] {oshpTrxPk.toString(), otbdOrdNum });
                        }
                        // QC#52104 Add End
                        if (chkDtTmTs.compareTo(ezintime) > 0) {

                            updErrorTrxProcStsSoConf(oshpTrxPk, shipList, ochgList, errorCode);
                            outputErr(NLGM0067I, new String[] {oshpTrxPk.toString(), otbdOrdNum });
                        }

                        soNumErrMap.put(otbdOrdNum, otbdOrdNum);
                        continue;
                    }
                }

                // Validation SO Confirmation Data
                if (WMS_TASK.OSHP.equals(wmsTaskCd) && !Arrays.asList(validateSoConfDataWmsOrdTp).contains(otbdOrdTpCd)) {

                    errorCode = validSoConf(oshpMap);

                    if (ZYPCommonFunc.hasValue(errorCode)) {

                        if (chkDtTmTs.compareTo(ezintime) > 0) {

                            updErrorTrxProcStsSoConf(oshpTrxPk, shipList, ochgList, errorCode);
                            outputErr(NLGM0067I, new String[] {oshpTrxPk.toString(), otbdOrdNum });
                        }

                        continue;
                    }
                }
            }

            /*************************************************************
             * 4. Calculation of a Freight Charge
             ************************************************************/
            // QC#17981 Mod.
            // if (WMS_TASK.OSHP.equals(wmsTaskCd) && !WMS_ORD_TP.OUTBOUND_KITTING.equals(otbdOrdTpCd)) {
            if (WMS_TASK.OSHP.equals(wmsTaskCd) && !Arrays.asList(calculateFreightChargeWmsOrdTp).contains(otbdOrdTpCd)) {

                errorCode = updFreightCharge(oshpList, shipList, oshpMap, ochgList);

                if (ZYPCommonFunc.hasValue(errorCode)) {

                    updErrorTrxProcStsSoConf(oshpTrxPk, shipList, ochgList, errorCode);
                    continue;
                }
            }

            /*************************************************************
             * 5. Update Process Status Code (OCHG)
             ************************************************************/
            for (Map<String, Object> ochgMap : ochgList) {

                BigDecimal oshpTrxPkForOchg = (BigDecimal) ochgMap.get("OSHP_WMS_INBD_TRX_PK");

                if (oshpTrxPk.compareTo(oshpTrxPkForOchg) == 0) {

                    updTrxProcSts((BigDecimal) ochgMap.get("WMS_INBD_TRX_PK"), PROC_STS.COMPLEATED, "");
                }
            }

            commit();
        }
    }

    /**
     * Update SO Confirmation
     */
    private void doProcUpdSoConf() {

        S21TransactionTableAccessor trxAccessor = new S21TransactionTableAccessor();
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Processing status is updated when an error is not canceled,
        // even if it passes over a definite period of time.
        long chkMinIntvl = 0;
        String chkAsnMinIntvl = ZYPCodeDataUtil.getVarCharConstValue(VLD_INTVL_SO_CONF_MIN, glblCmpyCd);

        if (ZYPCommonFunc.hasValue(chkAsnMinIntvl)) {

            chkMinIntvl = Long.parseLong(chkAsnMinIntvl);

        } else {

            chkMinIntvl = DEF_SO_CONF_MIN_INTVL;
        }

        String chkDtTmTs = EZDCommonFunc.toyyyyMMddHHmmss(EZDCommonFunc.toTimeMinute(ZYPDateUtil.getCurrentSystemTime(CUR_DT_TM_FMT)) //
                - (chkMinIntvl * MIN_CALC_MULT_VAL));

        /*************************************************************
         * 1. Get WMS_INBD_TRX Data (Ship)
         ************************************************************/
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        // Get Ship Data
        List<Map<String, Object>> wmsInbdTrxShipList = getWmsInbdTrxShipList(execParam);

        if (wmsInbdTrxShipList != null && !wmsInbdTrxShipList.isEmpty()) {

            String soNumPre = null;
            Map<String, Object> wmsInbdTrxShipMapPre = null;

            BigDecimal wrkTrxId = null;
            BigDecimal sqId = BigDecimal.ZERO;
            BigDecimal serSqId = BigDecimal.ZERO;

            ArrayList<BigDecimal> wmsInbdTrxShipPkList = new ArrayList<BigDecimal>();
            ArrayList<BigDecimal> wmsInbdTrxSerlPkList = new ArrayList<BigDecimal>();

            // For PI Check
            ArrayList<String> piErrSoList = new ArrayList<String>();
            ArrayList<String> nonPiSoList = new ArrayList<String>();

            // QC#54510 Add 
            // For Shipment Validation & Carrier Check
            ArrayList<Map<String, String>> valErrList = new ArrayList<Map<String, String>>();

            // QC#53872
            boolean isSerErr = false;
            // START 2021/08/27 R.Azucena[QC#59036, ADD]
            ArrayList<BigDecimal> wmsInbdTrxSerlPkWhErrList = new ArrayList<BigDecimal>();
            ArrayList<BigDecimal> wmsInbdTrxLineNumWhErrList = new ArrayList<BigDecimal>();
            boolean isConfigFlgPre = false;
            // END 2021/08/27 R.Azucena[QC#59036, ADD]

            for (Map<String, Object> wmsInbdTrxShipMap : wmsInbdTrxShipList) {

                String soNum = (String) wmsInbdTrxShipMap.get("OTBD_ORD_NUM");
                String otbdOrdTpCd = (String) wmsInbdTrxShipMap.get("OTBD_ORD_TP_CD");
                String ezintime = (String) wmsInbdTrxShipMap.get("EZINTIME");
                BigDecimal wmsInbdTrxPk = (BigDecimal) wmsInbdTrxShipMap.get("WMS_INBD_TRX_PK");

                /*************************************************************
                 * 2. Check WMS_INBD_TRX Data (Ship)
                 ************************************************************/
                String errorCode = null;

                // OSHP Shipment Validation
                if (soNumErrMap.containsKey(soNum)) {

                    this.totalErrCount++;
                    continue;
                }

                // Shipment Validation
                if (!Arrays.asList(validateSoConfDataWmsOrdTp).contains(otbdOrdTpCd)) {

                    // Column Check
                    errorCode = chkFieldDataShip(wmsInbdTrxShipMap);

                    if (ZYPCommonFunc.hasValue(errorCode)) {
                        // QC#54510 Mod Start
                        Map<String, String> valErrInfo = new HashMap<String, String>();
                        valErrInfo.put(wmsInbdTrxPk.toString(), errorCode);
                        valErrList.add(valErrInfo);
                        // updTrxProcSts(wmsInbdTrxPk, PROC_STS.ERROR, errorCode);
                        // QC#54510 Mod End
                        this.totalErrCount++;
                        continue;
                    }

                    // Carrier Check
                    if (!Arrays.asList(validateSoConfDataWmsOrdTp).contains(otbdOrdTpCd)) {

                        errorCode = validSoConf(wmsInbdTrxShipMap);

                        if (ZYPCommonFunc.hasValue(errorCode)) {

                            if (chkDtTmTs.compareTo(ezintime) > 0) {
                                // QC#54510 Mod Start
                                Map<String, String> valErrInfo = new HashMap<String, String>();
                                valErrInfo.put(wmsInbdTrxPk.toString(), errorCode);
                                valErrList.add(valErrInfo);

                                // updTrxProcSts(wmsInbdTrxPk, PROC_STS.ERROR, errorCode);
                                // QC#54510 Mod End
                                outputErr(NLGM0067I, new String[] {wmsInbdTrxPk.toPlainString() });
                            }

                            this.totalErrCount++;
                            continue;
                        }
                    }
                }

                // PI Check
                if (piErrSoList.contains(soNum)) {

                    S21InfoLogOutput.println(NLBM1347E);
                    this.totalErrCount++;
                    continue;

                } else if (!nonPiSoList.contains(soNum)) {

                    if (!chkPiActivity(soNum)) {

                        piErrSoList.add(soNum);
                        S21InfoLogOutput.println(NLBM1347E);
                        this.totalErrCount++;
                        continue;

                    } else {

                        nonPiSoList.add(soNum);
                    }
                }

                /*************************************************************
                 * 3. Create SO Conf Work Table & Update SO Confirmation
                 ************************************************************/
                // First Record or Change SO#
                if (!ZYPCommonFunc.hasValue(soNumPre) || !soNumPre.equals(soNum)) {

                    // Change SO#
                    if (ZYPCommonFunc.hasValue(soNumPre)) {

                        // Create SHPG_ORD_CONF_WRK & Update SO Confirmation. Mod QC#53872 Start
                        if (isSerErr) {
                            rollback();
                            errorCode = NLGM0087E;
                        // START 2021/08/27 R.Azucena[QC#59036, ADD]
                        } else if (!wmsInbdTrxSerlPkWhErrList.isEmpty()) {
                            rollback();
                            errorCode = NLGM0098E;
                        // END 2021/08/27 R.Azucena[QC#59036, ADD]
                        } else {
                            errorCode = cratShpgOrdConfWrkAndUpdSoConf(wmsInbdTrxShipMapPre, null, wrkTrxId.toPlainString(), sqId.toPlainString(), execParam);
                        }

                        if (ZYPCommonFunc.hasValue(errorCode)) {

                            for (BigDecimal wmsInbdTrxShipPk : wmsInbdTrxShipPkList) {
                                if ("NLZM2521E".equals(errorCode)) {
                                    updTrxProcSts(wmsInbdTrxShipPk, PROC_STS.COMPLEATED, errorCode);
                                    // START 2021/08/27 R.Azucena[QC#59036, ADD]
                                    this.totalErrCount++;
                                    // END 2021/08/27 R.Azucena[QC#59036, ADD]
                                } else if(NLGM0087E.equals(errorCode)) {
                                    updTrxProcSts(wmsInbdTrxShipPk, PROC_STS.IN_COMPLETED, errorCode);
                                // START 2021/08/27 R.Azucena[QC#59036, ADD]
                                    this.totalErrCount++;
                                } else if (NLGM0098E.equals(errorCode)) {
                                    updShipTrxProcStsForWhErr(wmsInbdTrxLineNumWhErrList, wmsInbdTrxShipPk, isConfigFlgPre, errorCode);
                                // END 2021/08/27 R.Azucena[QC#59036, ADD]
                                } else {
                                    updTrxProcSts(wmsInbdTrxShipPk, PROC_STS.ERROR, errorCode);
                                    // START 2021/08/27 R.Azucena[QC#59036, ADD]
                                    this.totalErrCount++;
                                    // END 2021/08/27 R.Azucena[QC#59036, ADD]
                                }
                                // START 2021/08/27 R.Azucena[QC#59036, DEL]
                                // this.totalErrCount++;
                                // END 2021/08/27 R.Azucena[QC#59036, DEL]
                            }

                            for (BigDecimal wmsInbdTrxSerlPk : wmsInbdTrxSerlPkList) {
                                if ("NLZM2521E".equals(errorCode)) {
                                    updTrxProcSts(wmsInbdTrxSerlPk, PROC_STS.COMPLEATED, errorCode);
                                    // START 2021/08/27 R.Azucena[QC#59036, ADD]
                                    this.totalErrCount++;
                                    // END 2021/08/27 R.Azucena[QC#59036, ADD]
                                } else if(NLGM0087E.equals(errorCode)) {
                                    updTrxProcSts(wmsInbdTrxSerlPk, PROC_STS.IN_COMPLETED, errorCode);
                                // START 2021/08/27 R.Azucena[QC#59036, ADD]
                                    this.totalErrCount++;
                                } else if (NLGM0098E.equals(errorCode)) {
                                    updSerlTrxProcStsForWhErr(wmsInbdTrxSerlPkWhErrList, wmsInbdTrxSerlPk, isConfigFlgPre, errorCode);
                                // END 2021/08/27 R.Azucena[QC#59036, ADD]
                                }else {
                                    updTrxProcSts(wmsInbdTrxSerlPk, PROC_STS.ERROR, errorCode);
                                    // START 2021/08/27 R.Azucena[QC#59036, ADD]
                                    this.totalErrCount++;
                                    // END 2021/08/27 R.Azucena[QC#59036, ADD]
                                }
                                // START 2021/08/27 R.Azucena[QC#59036, DEL]
                                // this.totalErrCount++;
                                // END 2021/08/27 R.Azucena[QC#59036, DEL]
                            }

                        } else {

                            for (BigDecimal wmsInbdTrxShipPk : wmsInbdTrxShipPkList) {

                                updTrxProcSts(wmsInbdTrxShipPk, PROC_STS.COMPLEATED, null);
                                this.normalRecCount++;
                            }

                            for (BigDecimal wmsInbdTrxSerlPk : wmsInbdTrxSerlPkList) {

                                updTrxProcSts(wmsInbdTrxSerlPk, PROC_STS.COMPLEATED, null);
                                this.normalRecCount++;
                            }
                        }

                        // QC#54510 Add Start
                        for (Map<String, String> valErrInfo : valErrList) {
                            for(Map.Entry<String, String> errorInfo : valErrInfo.entrySet()){
                                updTrxProcSts(new BigDecimal(errorInfo.getKey()), PROC_STS.ERROR, errorInfo.getValue());
                            }
                        }
                        valErrList = new ArrayList<Map<String,String>>();
                        // QC#54510 Add End
                        commit();
                    }

                    soNumPre = soNum;
                    sqId = BigDecimal.ONE;
                    serSqId = BigDecimal.ONE;
                    wrkTrxId = trxAccessor.getNextTransactionId();

                    wmsInbdTrxShipPkList = new ArrayList<BigDecimal>();
                    wmsInbdTrxSerlPkList = new ArrayList<BigDecimal>();
                    isSerErr = false;
                    // START 2021/08/27 R.Azucena[QC#59036, ADD]
                    wmsInbdTrxSerlPkWhErrList = new ArrayList<BigDecimal>();
                    wmsInbdTrxLineNumWhErrList = new ArrayList<BigDecimal>();
                    // END 2021/08/27 R.Azucena[QC#59036, ADD]
                    // QC#53872 End
                }

                // Create SHPG_ORD_CONF_DTL_WRK
                cratShpgOrdConfDtlWrk(wmsInbdTrxShipMap, soNum, wrkTrxId.toPlainString(), sqId.toPlainString());
                wmsInbdTrxShipPkList.add(wmsInbdTrxPk);
                sqId = sqId.add(BigDecimal.ONE);

                // START 2022/04/04 R.Azucena [QC#59802, ADD]
                // START 2022/08/08 R.Azucena [QC#60416, DEL]
                // String mapKey = getMapKey(wmsInbdTrxShipMap);
                // if (availQtyIBMap.containsKey(mapKey) && !ZYPCommonFunc.hasValue(errorCode)) {
                //     availQtyIBMap.put(mapKey, availQtyIBMap.get(mapKey).subtract((BigDecimal) wmsInbdTrxShipMap.get("WMS_ORD_QTY")));
                // }
                // END 2022/08/08 R.Azucena [QC#60416, DEL]
                // END 2022/04/04 R.Azucena [QC#59802, ADD]

                // Create SHIP_SER_NUM_WRK
                List<Map<String, Object>> wmsInbdTrxSerlList = getWmsInbdTrxSerlList(wmsInbdTrxShipMap, execParam);

                for (Map<String, Object> wmsInbdTrxSerlMap : wmsInbdTrxSerlList) {

                    // QC#53872 Check Machine Master(MDSE_CD and SER_NUM and STK_STS_CD)
                    String mdseCd = (String) wmsInbdTrxSerlMap.get("WMS_MDSE_CD");
                    String serNum = (String) wmsInbdTrxSerlMap.get("SER_NUM");
                    String stkStsCd = (String) wmsInbdTrxSerlMap.get("FROM_STK_STS_CD");

                    // START 2021/08/27 R.Azucena[QC#59036, MOD]
                    //if (ZYPCommonFunc.hasValue(mdseCd) && ZYPCommonFunc.hasValue(serNum) && ZYPCommonFunc.hasValue(stkStsCd) && //
                    //       !isCntSvcMachMstrPk(mdseCd, serNum, (String) stkStsCd)) {
                    //
                    //    isSerErr = true;
                        // QC#55713 Add Start
                    //    if(needsAlrtMail(glblCmpyCd, (BigDecimal)wmsInbdTrxSerlMap.get("WMS_INBD_TRX_PK"))) {
                    //        BigDecimal pk = (BigDecimal) wmsInbdTrxSerlMap.get("WMS_INBD_TRX_PK");
                    //        outputErr(NLGM0087E, new String[] {soNum, mdseCd, serNum, stkStsCd, pk.toString() });
                    //    }
                    // }
                    if (ZYPCommonFunc.hasValue(mdseCd) && ZYPCommonFunc.hasValue(serNum) && ZYPCommonFunc.hasValue(stkStsCd)) {
                        if (!isCntSvcMachMstrPk(mdseCd, serNum, (String) stkStsCd)) {
                            isSerErr = true;
                            // QC#55713 Add Start
                            if (needsAlrtMail(glblCmpyCd, (BigDecimal) wmsInbdTrxSerlMap.get("WMS_INBD_TRX_PK"))) {
                                BigDecimal pk = (BigDecimal) wmsInbdTrxSerlMap.get("WMS_INBD_TRX_PK");
                                outputErr(NLGM0087E, new String[] {soNum, mdseCd, serNum, stkStsCd, pk.toString() });
                            }
                        } else {
                            String strCurLocNum = this.objNullToString(getSvcMachMstrCurLocNum(mdseCd, serNum, (String) stkStsCd));
                            String strRtlWhCD = this.objNullToString(wmsInbdTrxShipMap.get("RTL_WH_CD"));
                            String strRtlSWhCD = this.objNullToString(wmsInbdTrxShipMap.get("RTL_SWH_CD"));
                            String strRtlWhInfo = strRtlWhCD + strRtlSWhCD;

                            if (!strCurLocNum.equals(strRtlWhInfo)) {
                                BigDecimal pk = (BigDecimal) wmsInbdTrxSerlMap.get("WMS_INBD_TRX_PK");
                                wmsInbdTrxSerlPkWhErrList.add(pk);
                                wmsInbdTrxLineNumWhErrList.add((BigDecimal) wmsInbdTrxSerlMap.get("OTBD_ORD_LINE_NUM"));
                                outputErr(NLGM0098E, new String[] {soNum, serNum, strRtlWhCD, strRtlSWhCD, strCurLocNum });
                            }
                        }
                    }
                    // END 2021/08/27 R.Azucena[QC#59036, MOD]

                    // START 2021/08/27 R.Azucena[QC#59036, MOD]
                    // if (!isSerErr) {
                    if (!isSerErr && wmsInbdTrxSerlPkWhErrList.isEmpty()) {
                    // END 2021/08/27 R.Azucena[QC#59036, MOD]
                        cratShipSerNumWrk(wmsInbdTrxSerlMap, soNum, wrkTrxId.toPlainString(), serSqId.toPlainString(), (String) wmsInbdTrxShipMap.get("INVTY_LOC_CD"));
                    }
                    // QC#53872 End

                    wmsInbdTrxSerlPkList.add((BigDecimal) wmsInbdTrxSerlMap.get("WMS_INBD_TRX_PK"));
                    serSqId = serSqId.add(BigDecimal.ONE);
                }

                wmsInbdTrxShipMapPre = wmsInbdTrxShipMap;
                // START 2021/08/27 R.Azucena[QC#59036, ADD]
                isConfigFlgPre = ZYPCommonFunc.hasValue((String) wmsInbdTrxShipMap.get("WMS_CNTNR_ID"));
                // END 2021/08/27 R.Azucena[QC#59036, ADD]
            }

            // Create SHPG_ORD_CONF_WRK & Update SO Confirmation for Last Record
            if (wmsInbdTrxShipMapPre != null && !wmsInbdTrxShipMapPre.isEmpty()) {
                // QC#53872 Start
                String errorCode = "";
                if (isSerErr) {
                    rollback();
                    errorCode = NLGM0087E;
                // START 2021/08/27 R.Azucena[QC#59036, ADD]
                } else if (!wmsInbdTrxSerlPkWhErrList.isEmpty()) {
                    rollback();
                    errorCode = NLGM0098E;
                // END 2021/08/27 R.Azucena[QC#59036, ADD]
                } else {
                    errorCode = cratShpgOrdConfWrkAndUpdSoConf(wmsInbdTrxShipMapPre, null, wrkTrxId.toPlainString(), sqId.toPlainString(), execParam);
                }

                if (ZYPCommonFunc.hasValue(errorCode)) {

                    for (BigDecimal wmsInbdTrxShipPk : wmsInbdTrxShipPkList) {
                        // QC#53009. Mod QC#58853
                        if ("NLZM2521E".equals(errorCode)  || "NLZM1024E".equals(errorCode)) {
                            updTrxProcSts(wmsInbdTrxShipPk, PROC_STS.COMPLEATED, errorCode);
                            // START 2021/08/27 R.Azucena[QC#59036, ADD]
                            this.totalErrCount++;
                            // END 2021/08/27 R.Azucena[QC#59036, ADD]
                        } else if(NLGM0087E.equals(errorCode)) {
                            updTrxProcSts(wmsInbdTrxShipPk, PROC_STS.IN_COMPLETED, errorCode);
                        // START 2021/08/27 R.Azucena[QC#59036, ADD]
                            this.totalErrCount++;
                        } else if (NLGM0098E.equals(errorCode)) {
                            updShipTrxProcStsForWhErr(wmsInbdTrxLineNumWhErrList, wmsInbdTrxShipPk, isConfigFlgPre, errorCode);
                        // END 2021/08/27 R.Azucena[QC#59036, ADD]
                        } else {
                            updTrxProcSts(wmsInbdTrxShipPk, PROC_STS.ERROR, errorCode);
                            // START 2021/08/27 R.Azucena[QC#59036, ADD]
                            this.totalErrCount++;
                            // END 2021/08/27 R.Azucena[QC#59036, ADD]
                        }
                        // START 2021/08/27 R.Azucena[QC#59036, DEL]
                        // this.totalErrCount++;
                        // END 2021/08/27 R.Azucena[QC#59036, DEL]
                    }

                    for (BigDecimal wmsInbdTrxSerlPk : wmsInbdTrxSerlPkList) {
                        // QC#53009. Mod QC#58853
                        if ("NLZM2521E".equals(errorCode)  || "NLZM1024E".equals(errorCode)) {
                            updTrxProcSts(wmsInbdTrxSerlPk, PROC_STS.COMPLEATED, errorCode);
                            // START 2021/08/27 R.Azucena[QC#59036, ADD]
                            this.totalErrCount++;
                            // END 2021/08/27 R.Azucena[QC#59036, ADD]
                        } else if(NLGM0087E.equals(errorCode)) {
                            updTrxProcSts(wmsInbdTrxSerlPk, PROC_STS.IN_COMPLETED, errorCode);
                        // START 2021/08/27 R.Azucena[QC#59036, ADD]
                            this.totalErrCount++;
                        } else if (NLGM0098E.equals(errorCode)) {
                            updSerlTrxProcStsForWhErr(wmsInbdTrxSerlPkWhErrList, wmsInbdTrxSerlPk, isConfigFlgPre, errorCode);
                        // END 2021/08/27 R.Azucena[QC#59036, ADD]
                        }else {
                            updTrxProcSts(wmsInbdTrxSerlPk, PROC_STS.ERROR, errorCode);
                            // START 2021/08/27 R.Azucena[QC#59036, ADD]
                            this.totalErrCount++;
                            // END 2021/08/27 R.Azucena[QC#59036, ADD]
                        }
                        // START 2021/08/27 R.Azucena[QC#59036, DEL]
                        // this.totalErrCount++;
                        // END 2021/08/27 R.Azucena[QC#59036, DEL]
                    }
                // QC#53872 End

                } else {

                    for (BigDecimal wmsInbdTrxShipPk : wmsInbdTrxShipPkList) {

                        updTrxProcSts(wmsInbdTrxShipPk, PROC_STS.COMPLEATED, null);
                        this.normalRecCount++;
                    }

                    for (BigDecimal wmsInbdTrxSerlPk : wmsInbdTrxSerlPkList) {

                        updTrxProcSts(wmsInbdTrxSerlPk, PROC_STS.COMPLEATED, null);
                        this.normalRecCount++;
                    }
                }

                // QC#54510 Add Start
                for (Map<String, String> valErrInfo : valErrList) {
                    for(Map.Entry<String, String> errorInfo : valErrInfo.entrySet()){
                        updTrxProcSts(new BigDecimal(errorInfo.getKey()), PROC_STS.ERROR, errorInfo.getValue());
                    }
                }
                valErrList = new ArrayList<Map<String,String>>();
                // QC#54510 Add End

                commit();

            // QC#55713 Mod Start
            } else {
                if (valErrList.size() > 0) {
                    for (Map<String, String> valErrInfo : valErrList) {
                        for(Map.Entry<String, String> errorInfo : valErrInfo.entrySet()){
                            updTrxProcSts(new BigDecimal(errorInfo.getKey()), PROC_STS.ERROR, errorInfo.getValue());
                        }
                    }
                    valErrList = new ArrayList<Map<String,String>>();
                    commit();
                }
            }
            // QC#55713 Mod End
        }

        /*************************************************************
         * 4. Process For Header Close or Cancel
         ************************************************************/
        // Get WMS_INBD_TRX Data (OSHP/OCS)
        List<Map<String, Object>> wmsInbdTrxOshpAndOscList = getWmsInbdTrxOshpAndOscList(execParam);

        if (wmsInbdTrxOshpAndOscList != null && !wmsInbdTrxOshpAndOscList.isEmpty()) {

            // Create SO Conf Work Table &  Update SO Confirmation
            for (Map<String, Object> wmsInbdTrxOshpAndOscMap : wmsInbdTrxOshpAndOscList) {

                BigDecimal wrkTrxId = trxAccessor.getNextTransactionId();
                cratShpgOrdConfWrkAndUpdSoConf(null, wmsInbdTrxOshpAndOscMap, wrkTrxId.toPlainString(), "1", execParam);
                commit();
            }
        }
    }

    /**
     * chkFieldDataShip
     * @param wmsInbdTrxShipMap Map<String, Object>
     * @return String
     */
    private String chkFieldDataShip(Map<String, Object> wmsInbdTrxShipMap) {

        String errorCode = null;

        BigDecimal wmsInbdTrxPk = (BigDecimal) wmsInbdTrxShipMap.get("WMS_INBD_TRX_PK");

        // START 2022/04/04 R.Azucena [QC#59802, ADD]
        // START 2022/08/08 R.Azucena [QC#60416, DEL]
        // String mapKey = getMapKey(wmsInbdTrxShipMap);
        // if (ZYPConstant.FLG_OFF_N.equals((String) wmsInbdTrxShipMap.get("SER_NUM_TAKE_FLG"))
                // START 2022/04/25 R.Azucena [QC#59802-1, ADD]
        //         && ZYPConstant.FLG_ON_Y.equals((String) wmsInbdTrxShipMap.get("INSTL_BASE_CTRL_FLG"))
                // END 2022/04/25 R.Azucena [QC#59802-1, ADD]
        //         && !availQtyIBMap.containsKey(mapKey)) {
        //     availQtyIBMap.put(mapKey, getAvailQty(wmsInbdTrxShipMap));
        // }
        // END 2022/08/08 R.Azucena [QC#60416, DEL]
        // END 2022/04/04 R.Azucena [QC#59802, ADD]

        // OTBD_ORD_NUM Check
        if (ZYPConstant.FLG_OFF_N.equals((String) wmsInbdTrxShipMap.get("SHPG_ORD_EXIST")) || !ZYPCommonFunc.hasValue((String) wmsInbdTrxShipMap.get("OTBD_ORD_NUM"))) {

            outputErr(NLZM2005E, new String[] {"OTBD_ORD_NUM" });
            errorCode = this.getErrCd(errorCode, NLZM2005E);
        }

        // OTBD_ORD_NUM, OTBD_ORD_LINE_NUM Check 1
        if (!ZYPCommonFunc.hasValue((BigDecimal) wmsInbdTrxShipMap.get("OTBD_ORD_LINE_NUM")) || ZYPConstant.FLG_OFF_N.equals((String) wmsInbdTrxShipMap.get("SHPG_ORD_DTL_EXIST"))) {

            outputErr(NLZM2005E, new String[] {"OTBD_ORD_LINE_NUM" });
            errorCode = this.getErrCd(errorCode, NLZM2005E);
        }

        // OTBD_ORD_NUM, OTBD_ORD_LINE_NUM Check 2
        String strSoPrintFlg = (String) wmsInbdTrxShipMap.get("SO_PRINT_FLG");
        String strShipFlg = (String) wmsInbdTrxShipMap.get("SHIP_FLG");

        if (!(ZYPConstant.FLG_ON_Y.equals(strSoPrintFlg) && ZYPConstant.FLG_OFF_N.equals(strShipFlg))) {

            // QC#55361 Mod Start
            //outputErr(NLZM2006E, new String[] {"OTBD_ORD_NUM", "OTBD_ORD_LINE_NUM" });
            //errorCode = this.getErrCd(errorCode, NLZM2006E);
            outputErr("NLZM2523E", new String[] {(String) wmsInbdTrxShipMap.get("OTBD_ORD_NUM"), wmsInbdTrxPk.toString()});
            errorCode = this.getErrCd(errorCode, "NLZM2523E");
            // QC#55361 Mod End
        }

        // WMS_STK_STS_CD Check1
        String strWmsStkStsCd = (String) wmsInbdTrxShipMap.get("WMS_STK_STS_CD");

        if (!ZYPCommonFunc.hasValue(strWmsStkStsCd) || ZYPConstant.FLG_OFF_N.equals((String) wmsInbdTrxShipMap.get("STK_STS_CD_EXIST"))) {

            outputErr(NLBM1127E, new String[] {"WMS_STK_STS_CD" });
            errorCode = this.getErrCd(errorCode, NLBM1127E);
        }

        // WMS_STK_STS_CD Check2
        String strFromStkStsCd = (String) wmsInbdTrxShipMap.get("FROM_STK_STS_CD");

        if (ZYPCommonFunc.hasValue(strWmsStkStsCd) && !strWmsStkStsCd.equals(strFromStkStsCd)) {

            outputErr(NLZM2312E, new String[] {"FROM_STK_STS_CD" });
            errorCode = this.getErrCd(errorCode, NLZM2312E);
        }

        // WMS_ORD_QTY Check1
        BigDecimal wmsOrdQty = (BigDecimal) wmsInbdTrxShipMap.get("WMS_ORD_QTY");

        if (!ZYPCommonFunc.hasValue(wmsOrdQty) || BigDecimal.ZERO.compareTo(wmsOrdQty) >= 0) {

            // QC#55361 Mod Start
//            outputErr(NLZM2009E, new String[] {"WMS_ORD_QTY" });
//            errorCode = this.getErrCd(errorCode, NLZM2009E);
            outputErr("NLZM2524E", new String[] {(String) wmsInbdTrxShipMap.get("OTBD_ORD_NUM"),wmsOrdQty.toString(), wmsInbdTrxPk.toString() });
            errorCode = this.getErrCd(errorCode, "NLZM2524E");
            // QC#55361 Mod End
        }

        // WMS_ORD_QTY Check2
        if (ZYPConstant.FLG_ON_Y.equals(wmsInbdTrxShipMap.get("SHIP_OVER_ERR"))) {

            // QC#55361 Mod Start
//            outputErr(NLZM2009E, new String[] {"WMS_ORD_QTY" });
//            errorCode = this.getErrCd(errorCode, NLZM2009E);
            outputErr("NLZM2524E", new String[] {(String) wmsInbdTrxShipMap.get("OTBD_ORD_NUM"),wmsOrdQty.toString(), wmsInbdTrxPk.toString() });
            errorCode = this.getErrCd(errorCode, "NLZM2524E");
            // QC#55361 Mod End
        }

        // START 2022/04/04 R.Azucena [QC#59802, ADD]
        // START 2022/08/08 R.Azucena [QC#60416, DEL]
        // WMS_ORD_QTY Check3
        // if (ZYPConstant.FLG_OFF_N.equals((String) wmsInbdTrxShipMap.get("SER_NUM_TAKE_FLG"))
                // START 2022/04/25 R.Azucena [QC#59802-1, ADD]
        //         && ZYPConstant.FLG_ON_Y.equals((String) wmsInbdTrxShipMap.get("INSTL_BASE_CTRL_FLG"))
                // END 2022/04/25 R.Azucena [QC#59802-1, ADD]
        //         && availQtyIBMap.get(mapKey).compareTo(wmsOrdQty) < 0) {
        //     outputErr("NLBM1377E");
        //     errorCode = this.getErrCd(errorCode, "NLBM1377E");
        // }
        // END 2022/08/08 R.Azucena [QC#60416, DEL]
        // END 2022/04/04 R.Azucena [QC#59802, ADD]

        // WMS_MDSE_CD
        String strWmsMdseCD = (String) wmsInbdTrxShipMap.get("WMS_MDSE_CD");
        String strSodMdseCD = (String) wmsInbdTrxShipMap.get("SOD_MDSE_CD");

        if (!ZYPCommonFunc.hasValue(strWmsMdseCD) || !strWmsMdseCD.equals(strSodMdseCD)) {

            outputErr(NLZM2010E, new String[] {"WMS_MDSE_CD" });
            errorCode = this.getErrCd(errorCode, NLZM2010E);
        }

        // PRO_NUM, TMS_TRK_NUM, BOL_NUM
        String strSceOrdTpCdSo = (String) wmsInbdTrxShipMap.get("SCE_ORD_TP_CD");

        if (ZYPCommonFunc.hasValue(strSceOrdTpCdSo) && strSceOrdTpCdSo.equals(SCE_ORD_TP.DIRECT_SALES)) {

            String strShipProNum = (String) wmsInbdTrxShipMap.get("PRO_NUM");
            String strShipTmsTrkNum = (String) wmsInbdTrxShipMap.get("TMS_TRK_NUM");
            String strShipBolNum = (String) wmsInbdTrxShipMap.get("BOL_NUM");

            if (!ZYPCommonFunc.hasValue(strShipProNum) && !ZYPCommonFunc.hasValue(strShipTmsTrkNum) && !ZYPCommonFunc.hasValue(strShipBolNum)) {

                // QC#54910 Mod Start
                if (!isUs((String) wmsInbdTrxShipMap.get("OTBD_ORD_NUM"))) {
                    wmsInbdTrxShipMap.put("PRO_NUM",  (String) wmsInbdTrxShipMap.get("OTBD_ORD_NUM"));
                } else {
                    outputErr(NLZM2011E, new String[] {"PRO_NUM", "TMS_TRK_NUM", "BOL_NUM" });
                    errorCode = this.getErrCd(errorCode, NLZM2011E);
                }
                // QC#54910 Mod End
            }
        }

        // RTL_WH_CD, RTL_SWH_CD Check
        String strRtlWhCD = this.objNullToString(wmsInbdTrxShipMap.get("RTL_WH_CD"));
        String strRtlSWhCD = this.objNullToString(wmsInbdTrxShipMap.get("RTL_SWH_CD"));
        String strRtlWhCdSod = this.objNullToString(wmsInbdTrxShipMap.get("SOD_RTL_WH_CD"));
        String strRtlSWhCdSod = this.objNullToString(wmsInbdTrxShipMap.get("SOD_RTL_SWH_CD"));

        if (!strRtlWhCD.equals(strRtlWhCdSod) || !strRtlSWhCD.equals(strRtlSWhCdSod)) {

            outputErr(NLZM2007E, new String[] {"RTL_WH_CD", "RTL_SWH_CD" });
            errorCode = this.getErrCd(errorCode, NLZM2007E);
        }

        return errorCode;
    }

    /**
     * objNullToString
     * @param obj Object
     * @return String
     */
    private String objNullToString(Object obj) {

        if (obj == null) {

            return "";

        } else {

            return obj.toString();
        }
    }

    /**
     * getErrCd
     * @param errCodeCueernt String
     * @param errCodeNew String
     * @return String
     */
    private String getErrCd(String errCodeCueernt, String errCodeNew) {

        String strRet = "";

        if (ZYPCommonFunc.hasValue(errCodeCueernt)) {

            strRet = errCodeCueernt;

        } else {

            strRet = errCodeNew;
        }

        return strRet;
    }

    // ITG#610899 ADD START
    /**
     * SOConf data is stored in each task list (OSHP).
     * @param rs ResultSet
     * @param oshpList ArrayList<Map<String, Object>>
     * @throws SQLException
     */
    private void setSoConfDataHdr(ResultSet rs, ArrayList<Map<String, Object>> oshpList) throws SQLException {

        Map<String, Object> oshpPkMap = new HashMap<String, Object>();

        while (rs.next()) {

            // START 05/25/2020 T.Ogura [QC#56612,ADD]
            if (!ZYPCommonFunc.hasValue(rs.getString("WMS_SQ_NUM"))) {
                BigDecimal oshpTrxPk = new BigDecimal(rs.getString("WMS_INBD_TRX_PK"));
                String otbdOrdNum = rs.getString("OTBD_ORD_NUM");
                updErrorTrxProcStsSoConf(oshpTrxPk, new ArrayList<Map<String, Object>>(), new ArrayList<Map<String, Object>>(), NLGM0093E);
                outputErr(NLGM0093E, new String[] {oshpTrxPk.toString(), otbdOrdNum});
                continue;
            }
            // END   05/25/2020 T.Ogura [QC#56612,ADD]

            String oshpPk = rs.getString("WMS_INBD_TRX_PK");
            oshpPkMap.put(oshpPk, oshpPk);

            // Extraction of OSHP Data
            HashMap<String, Object> oshpMap = new HashMap<String, Object>();
            oshpMap.put("WMS_INBD_TRX_PK", rs.getBigDecimal("WMS_INBD_TRX_PK"));
            oshpMap.put("WMS_REC_ID", rs.getString("WMS_REC_ID"));
            oshpMap.put("WH_CD", rs.getString("WH_CD"));
            oshpMap.put("WMS_ORD_QTY", rs.getBigDecimal("WMS_ORD_QTY"));
            oshpMap.put("WMS_TRX_DT_TM_TS", rs.getString("WMS_TRX_DT_TM_TS"));
            oshpMap.put("WMS_TASK_CD", rs.getString("WMS_TASK_CD"));
            oshpMap.put("OTBD_ORD_NUM", rs.getString("OTBD_ORD_NUM"));
            oshpMap.put("OTBD_ORD_TP_CD", rs.getString("OTBD_ORD_TP_CD"));
            oshpMap.put("WMS_ORD_STS_CD", rs.getString("WMS_ORD_STS_CD"));
            oshpMap.put("WMS_SHIP_ID", rs.getString("WMS_SHIP_ID"));
            oshpMap.put("WMS_FRT_CHRG_AMT", rs.getBigDecimal("WMS_FRT_CHRG_AMT"));
            oshpMap.put("TMS_FRT_CHRG_AMT", rs.getBigDecimal("TMS_FRT_CHRG_AMT"));
            oshpMap.put("WMS_TOT_WT", rs.getBigDecimal("WMS_TOT_WT"));
            oshpMap.put("WMS_ORG_HOST_ID", rs.getString("WMS_ORG_HOST_ID"));
            oshpMap.put("EZINTIME", rs.getString("EZINTIME").substring(0, LG_MW_DT_TM_TS));
            oshpMap.put("WMS_CARR_CD", rs.getString("WMS_CARR_CD"));
            oshpMap.put("VIA_WMS_CARR_CD", rs.getString("VIA_WMS_CARR_CD"));
            oshpMap.put("WMS_TRNSP_TP_CD", rs.getString("WMS_TRNSP_TP_CD"));
            oshpMap.put("RTL_WH_CD", rs.getString("RTL_WH_CD"));
            oshpMap.put("WMS_SQ_NUM", rs.getString("WMS_SQ_NUM"));
            oshpList.add(oshpMap);
        }
    }

    /**
     * Get WMS_INBD_TRX Data (Ship)
     * @param execParam S21SsmExecutionParameter
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getWmsInbdTrxShipList(S21SsmExecutionParameter execParam) {

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("wmsTaskShip", WMS_TASK.SHIP);
        param.put("procStsCdInComp", PROC_STS.IN_COMPLETED);
        param.put("intfcProcStsCdComp", PROC_STS.COMPLEATED);

        if (!WH_GP_CD_3_THIRD_PARTY_WH.equals(whGpCd)) {

            param.put("wmsTrgtWhCd", trgtWhCdAry);
        }

        List<String> wmsOrdTpList = new ArrayList<String>();

        for (String wmsOrdTpCd : wmsOrdTpListFromVarChar) {

            wmsOrdTpList.add(wmsOrdTpCd);
        }

        // bindParam.put("wmsOrdTpCd", wmsOrdTpList.toArray(new String[wmsOrdTpList.size()]));
        param.put("wmsOrdTpCd", wmsOrdTpList);

        // SQL Select
        String querySsmId = "getWmsInbdTrxShipList";

        if (WH_GP_CD_3_THIRD_PARTY_WH.equals(whGpCd)) {

            querySsmId = "getWmsInbdTrxShipListForTpl";
        }

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList(querySsmId, param, execParam);
    }

    /**
     * Get WMS_INBD_TRX for OSHP
     * @param shipMap Map<String, Object>
     * @param execParam S21SsmExecutionParameter
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getWmsInbdTrxOshpList(Map<String, Object> shipMap, S21SsmExecutionParameter execParam) {

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("wmsTaskOshp", WMS_TASK.OSHP);
        param.put("wmsTaskShip", WMS_TASK.SHIP);
        param.put("wmsTaskOchg", WMS_TASK.OCHG);
        param.put("whCd", (String) shipMap.get("WH_CD"));
        param.put("otbdOrdNum", (String) shipMap.get("OTBD_ORD_NUM"));
        param.put("wmsShipId", (String) shipMap.get("WMS_SHIP_ID"));
        param.put("intfcProcStsCdComp", PROC_STS.COMPLEATED);
        param.put("procStsCdInComp", PROC_STS.IN_COMPLETED);
        param.put("procStsCdErr", PROC_STS.ERROR);
        param.put("wmsSqNum", (BigDecimal) shipMap.get("WMS_SQ_NUM"));

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getWmsInbdTrxOshpList", param, execParam);
    }

    /**
     * Get WMS_INBD_TRX for SERL
     * @param shipMap Map<String, Object>
     * @param execParam S21SsmExecutionParameter
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getWmsInbdTrxSerlList(Map<String, Object> shipMap, S21SsmExecutionParameter execParam) {

        HashMap<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put("glblCmpyCd", this.glblCmpyCd);
        bindParam.put("whCd", shipMap.get("WH_CD"));
        bindParam.put("intfcProcStsCdComp", PROC_STS.COMPLEATED);
        bindParam.put("procStsCdInComp", PROC_STS.IN_COMPLETED);
        bindParam.put("otbdOrdNum", shipMap.get("OTBD_ORD_NUM"));
        bindParam.put("otbdOrdLineNum", shipMap.get("OTBD_ORD_LINE_NUM"));
        bindParam.put("wmsShipId", shipMap.get("WMS_SHIP_ID"));
        bindParam.put("wmsTaskSerl", WMS_TASK.SERL);
        bindParam.put("wmsSqNum", shipMap.get("WMS_SQ_NUM"));
        // QC#53872
        bindParam.put("fromStkStsCd", (String) shipMap.get("FROM_STK_STS_CD"));

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getWmsInbdTrxSerlList", bindParam, execParam);
    }

    /**
     * Get WMS_INBD_TRX for OSHP/OSC
     * @param execParam S21SsmExecutionParameter
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getWmsInbdTrxOshpAndOscList(S21SsmExecutionParameter execParam) {

        HashMap<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put("glblCmpyCd", glblCmpyCd);
        bindParam.put("wmsTaskShip", WMS_TASK.SHIP);
        bindParam.put("wmsTaskOshp", WMS_TASK.OSHP);
        bindParam.put("wmsTaskOsc", WMS_TASK.OSC);
        bindParam.put("wmsTaskOchg", WMS_TASK.OCHG);
        bindParam.put("procStsCdInComp", PROC_STS.IN_COMPLETED);
        bindParam.put("procStsCdErr", PROC_STS.ERROR);
        bindParam.put("wmsOrdStsCan", WMS_ORD_STS.CAN);
        bindParam.put("intfcProcStsCdComp", PROC_STS.COMPLEATED);

        if (!WH_GP_CD_3_THIRD_PARTY_WH.equals(whGpCd)) {

            bindParam.put("wmsTrgtWhCd", trgtWhCdAry);
        }

        List<String> wmsOrdTpList = new ArrayList<String>();

        for (String wmsOrdTpCd : wmsOrdTpListFromVarChar) {

            wmsOrdTpList.add(wmsOrdTpCd);
        }
        
        // QC#21433 T.Hakodate ADD START
        for (String additionalSoCancelOrdTp : additionalSoCancelOrdTpList) {

            wmsOrdTpList.add(additionalSoCancelOrdTp);
        }

        // QC#21433 T.Hakodate ADD END

        bindParam.put("wmsOrdTpCd", wmsOrdTpList.toArray(new String[wmsOrdTpList.size()]));

        // SQL Select
        String strSQL = "getWmsInbdTrxOshpAndOscList";

        if (WH_GP_CD_3_THIRD_PARTY_WH.equals(whGpCd)) {

            strSQL = "getWmsInbdTrxOshpAndOscListForTpl";
        }

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList(strSQL, bindParam, execParam);
    }

    /**
     * SOConf data is stored in each task list (SHIP, OCHG).
     * @param oshpList ArrayList<Map<String, Object>>
     * @param shipList ArrayList<Map<String, Object>>
     * @param ochgList ArrayList<Map<String, Object>>
     * @throws SQLException
     */
    private void setSoConfDataDtl(ArrayList<Map<String, Object>> oshpList, ArrayList<Map<String, Object>> shipList, ArrayList<Map<String, Object>> ochgList) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        for (Map<String, Object> oshpMap : oshpList) {

            // Get SHIP data.
            try {

                HashMap<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", glblCmpyCd);
                queryParam.put("wmsTaskShip", WMS_TASK.SHIP);
                queryParam.put("whCd", oshpMap.get("WH_CD"));
                queryParam.put("procStsCdInComp", PROC_STS.IN_COMPLETED);
                queryParam.put("otbdOrdNum", oshpMap.get("OTBD_ORD_NUM"));
                queryParam.put("wmsShipId", oshpMap.get("WMS_SHIP_ID"));
                queryParam.put("wmsSqNum", oshpMap.get("WMS_SQ_NUM"));

                if (WH_GP_CD_3_THIRD_PARTY_WH.equals(whGpCd)) {

                    stmt = this.ssmLLClient.createPreparedStatement("getSoConfShipForTpl", queryParam, execParam);

                } else {

                    stmt = this.ssmLLClient.createPreparedStatement("getSoConfShip", queryParam, execParam);
                }

                rs = stmt.executeQuery();

                while (rs.next()) {

                    // Extraction of SHIP Data
                    HashMap<String, Object> shipMap = new HashMap<String, Object>();
                    shipMap.put("OSHP_WMS_INBD_TRX_PK", (BigDecimal) oshpMap.get("WMS_INBD_TRX_PK"));
                    shipMap.put("OSHP_WH_CD", (String) oshpMap.get("WH_CD"));
                    shipMap.put("OSHP_WMS_SHIP_ID", (String) oshpMap.get("WMS_SHIP_ID"));
                    shipMap.put("OSHP_OTBD_ORD_NUM", (String) oshpMap.get("OTBD_ORD_NUM"));
                    shipMap.put("OSHP_WMS_FRT_CHRG_AMT", (BigDecimal) oshpMap.get("WMS_FRT_CHRG_AMT"));
                    shipMap.put("OSHP_WMS_TRNSP_TP_CD", (String) oshpMap.get("WMS_TRNSP_TP_CD"));
                    shipMap.put("WMS_INBD_TRX_PK", rs.getBigDecimal("WMS_INBD_TRX_PK"));
                    shipMap.put("WMS_REC_ID", rs.getString("WMS_REC_ID"));
                    shipMap.put("OTBD_ORD_LINE_NUM", rs.getString("OTBD_ORD_LINE_NUM"));
                    shipMap.put("OTBD_ORD_TP_CD", rs.getString("OTBD_ORD_TP_CD"));
                    shipMap.put("WMS_FRT_CHRG_AMT", rs.getBigDecimal("WMS_FRT_CHRG_AMT"));
                    shipMap.put("WMS_TOT_WT", rs.getBigDecimal("WMS_TOT_WT"));
                    shipMap.put("WMS_OUT_CNTNR_NUM", rs.getString("WMS_OUT_CNTNR_NUM"));
                    shipMap.put("BOL_NUM", rs.getString("BOL_NUM"));
                    shipMap.put("PRO_NUM", rs.getString("PRO_NUM"));
                    shipMap.put("TMS_TRK_NUM", rs.getString("TMS_TRK_NUM"));
                    shipMap.put("WMS_CARR_CD", rs.getString("WMS_CARR_CD"));
                    shipMap.put("CARR_SCAC_CD", rs.getString("CARR_SCAC_CD"));
                    shipMap.put("WMS_MDSE_CD", rs.getString("WMS_MDSE_CD"));
                    shipMap.put("WMS_STK_STS_CD", rs.getString("WMS_STK_STS_CD"));
                    shipMap.put("WMS_ORD_QTY", rs.getBigDecimal("WMS_ORD_QTY"));
                    shipMap.put("RTL_WH_CD", rs.getString("RTL_WH_CD"));
                    shipMap.put("RTL_SWH_CD", rs.getString("RTL_SWH_CD"));
                    // QC#14995 Start
                    shipMap.put("SHPG_SVC_LVL_CD", rs.getString("SHPG_SVC_LVL_CD"));
                    shipMap.put("INTFC_PROC_STS_CD", rs.getString("INTFC_PROC_STS_CD"));
                    // QC#14995 End

                    // QC#52104 Start
                    shipMap.put("INTFC_ERR_MSG_CD", rs.getString("INTFC_ERR_MSG_CD"));
                    // QC#52104 End
                    shipList.add(shipMap);

                    if (rs.isFirst()) {

                        oshpMap.put("WMS_OUT_CNTNR_NUM", rs.getString("WMS_OUT_CNTNR_NUM"));
                        oshpMap.put("BOL_NUM", rs.getString("BOL_NUM"));
                        oshpMap.put("PRO_NUM", rs.getString("PRO_NUM"));
                        oshpMap.put("TMS_TRK_NUM", rs.getString("TMS_TRK_NUM"));
                    }
                }

            } catch (SQLException e) {

                sqlExceptionHandler(e);

            } finally {

                S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            }

            // Get OCHG data.
            try {

                HashMap<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", glblCmpyCd);
                queryParam.put("whCd", oshpMap.get("WH_CD"));
                queryParam.put("wmsTaskOchg", WMS_TASK.OCHG);
                queryParam.put("procStsCdInComp", PROC_STS.IN_COMPLETED);
                queryParam.put("otbdOrdNum", oshpMap.get("OTBD_ORD_NUM"));
                queryParam.put("wmsShipId", oshpMap.get("WMS_SHIP_ID"));
                queryParam.put("wmsSqNum", oshpMap.get("WMS_SQ_NUM"));
                stmt = this.ssmLLClient.createPreparedStatement("getSoConfOchg", queryParam, execParam);
                rs = stmt.executeQuery();

                while (rs.next()) {

                    // Extraction of OCHG Data
                    HashMap<String, Object> ochgMap = new HashMap<String, Object>();
                    ochgMap.put("OSHP_WMS_INBD_TRX_PK", (BigDecimal) oshpMap.get("WMS_INBD_TRX_PK"));
                    ochgMap.put("OSHP_WH_CD", (String) oshpMap.get("WH_CD"));
                    ochgMap.put("OSHP_WMS_SHIP_ID", (String) oshpMap.get("WMS_SHIP_ID"));
                    ochgMap.put("OSHP_OTBD_ORD_NUM", (String) oshpMap.get("OTBD_ORD_NUM"));
                    ochgMap.put("WMS_INBD_TRX_PK", rs.getBigDecimal("WMS_INBD_TRX_PK"));
                    ochgMap.put("WMS_REC_ID", rs.getString("WMS_REC_ID"));
                    ochgMap.put("TMS_FRT_CHRG_AMT", rs.getBigDecimal("TMS_FRT_CHRG_AMT"));
                    ochgMap.put("INTFC_PROC_STS_CD", rs.getString("INTFC_PROC_STS_CD"));
                    // QC#52104 Add Start
                    ochgMap.put("INTFC_ERR_MSG_CD", rs.getString("INTFC_ERR_MSG_CD"));
                    // QC#52104 End
                    ochgList.add(ochgMap);
                }

            } catch (SQLException e) {

                sqlExceptionHandler(e);

            } finally {

                S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            }
        }
    }
    // ITG#610899 ADD END

    /**
     * validation of SO Confirmation Data.
     * @param oshpMap
     * @return errorMessage
     */
    private String validSoConf(Map<String, Object> oshpMap) {

        // String wmsCarrCd = oshpMap.get(COL_WMS_CARR_CD); ITG#610899 DEL
        // String wmsOrgHostId = oshpMap.get(COL_WMS_ORG_HOST_ID); ITG#610899 DEL
    	// QC#19930 Start
        //String viaWmsCarrCd = (String) oshpMap.get("VIA_WMS_CARR_CD");
        String viaWmsCarrCd = null;
        if (WH_GP_CD_2_DBS.equals(whGpCd) || WH_GP_CD_3_THIRD_PARTY_WH.equals(whGpCd)) {
            String wmsCarrCd = (String) oshpMap.get("WMS_CARR_CD");
            // QC#29979 Update.
            // START 2019/02/06 T.Ogura [QC#30182,MOD]
//            String whCd = (String) oshpMap.get("WH_CD");
//            Map<String, Object> tplCarrSvc = getTplCarrSvc(wmsCarrCd, whCd);
            if (!ZYPCommonFunc.hasValue(wmsCarrCd)) {
                return "";
            }
            Map<String, Object> tplCarrSvc = getTplCarrSvc(wmsCarrCd);
            // END   2019/02/06 T.Ogura [QC#30182,MOD]
            if (tplCarrSvc != null) {
                viaWmsCarrCd = (String) tplCarrSvc.get("CARR_CD");
            }
        } else {
            viaWmsCarrCd = (String) oshpMap.get("VIA_WMS_CARR_CD");
        }
        // QC#19930 End
        // String otbdOrdNum =  oshpMap.get(COL_OTBD_ORD_NUM); //ITG#610899 DEL

        // a) Ship-Via is valid.
        if (!ZYPCommonFunc.hasValue(viaWmsCarrCd)) {

            // ITG#591081 ADD START
            List<Map<String, Object>> soConfCarrErrList = soConfCarrErrListMap.get(oshpMap.get("WH_CD"));

            if (soConfCarrErrList == null) {

                soConfCarrErrList = new ArrayList<Map<String, Object>>();
                soConfCarrErrListMap.put((String) oshpMap.get("WH_CD"), soConfCarrErrList);
            }

            soConfCarrErrList.add(oshpMap);
            // outputErr(NLGM0062E, new String[] {otbdOrdNum, wmsCarrCd, wmsOrgHostId }); ITG#591081 ADD END
            return NLGM0062E;
        }

        return "";
    }

    /**
     * <PRE>
     * Create SHPG_ORD_CONF_WRK & Update SO Confirmation
     * </PRE>
     * @param wmsInbdTrxShipMap Map<String, Object>
     * @param wmsInbdTrxOshpOscMap Map<String, Object>
     * @param wrkTrxId String
     * @param sqId String
     * @param execParam S21SsmExecutionParameter
     * @return String
     */
    private String cratShpgOrdConfWrkAndUpdSoConf(Map<String, Object> wmsInbdTrxShipMap, Map<String, Object> wmsInbdTrxOshpOscMap, String wrkTrxId, String sqId, S21SsmExecutionParameter execParam) {

        Map<String, Object> wmsInbdTrxOshpMap = new HashMap<String, Object>();
        BigDecimal oshpWmsInbdTrxPk = null;
        String soNum = null;

        if (wmsInbdTrxShipMap != null) {

            soNum = (String) wmsInbdTrxShipMap.get("OTBD_ORD_NUM");

            // Get OSHP Data (One Record)
            List<Map<String, Object>> wmsInbdTrxOshpList = getWmsInbdTrxOshpList(wmsInbdTrxShipMap, execParam);

            if (wmsInbdTrxOshpList != null && !wmsInbdTrxOshpList.isEmpty()) {

                wmsInbdTrxOshpMap = wmsInbdTrxOshpList.get(0);
            }

        } else if (wmsInbdTrxOshpOscMap != null) {

            soNum = (String) wmsInbdTrxOshpOscMap.get("OTBD_ORD_NUM");
            wmsInbdTrxOshpMap = wmsInbdTrxOshpOscMap;
        }

        // Create SHPG_ORD_CONF_WRK
        if (wmsInbdTrxOshpMap != null && !wmsInbdTrxOshpMap.isEmpty()) {

            oshpWmsInbdTrxPk = (BigDecimal) wmsInbdTrxOshpMap.get("WMS_INBD_TRX_PK");

            // Get SCE_ORD_TP_CD
            String s80OrdTpCd = (String) wmsInbdTrxOshpMap.get("OTBD_ORD_TP_CD");
            String sceOrdTpCd = convOrdTpCd(s80OrdTpCd, (String) wmsInbdTrxOshpMap.get("OTBD_ORD_NUM")); // Defect#517

            if (!ZYPCommonFunc.hasValue(sceOrdTpCd)) {

                rollback();
                updTrxProcSts(oshpWmsInbdTrxPk, PROC_STS.ERROR, NLBM1126E);
                outputErr(NLBM1126E);
                this.totalErrCount++;
                return NLBM1126E;
            }

            // SO_PROC_STS_CD
            String soProcStsCd = "S";
            String wmsOrdStsCd = (String) wmsInbdTrxOshpMap.get("WMS_ORD_STS_CD");

            if (ZYPCommonFunc.hasValue(sceOrdTpCd) && WMS_ORD_STS.CAN.equals(wmsOrdStsCd)) {

                soProcStsCd = "O";
            }

            SHPG_ORD_CONF_WRKTMsg shpgOrdConfWrkTMsg = new SHPG_ORD_CONF_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.wrkTrxId, wrkTrxId);
            ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.sqId, sqId);
            ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.soNum, (String) wmsInbdTrxOshpMap.get("OTBD_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.procStsCd, PROC_STS.IN_COMPLETED);
            ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.whCd, (String) wmsInbdTrxOshpMap.get("RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.sceOrdTpCd, sceOrdTpCd);
            ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.s80OrdTpCd, s80OrdTpCd);
            ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.soProcStsCd, soProcStsCd);
            ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.shipDtTmTs, this.getSalesDateTm());
            // QC#53233 Mod Start
            String totShipWt = String.valueOf(wmsInbdTrxOshpMap.get("WMS_TOT_WT"));
            if (ZYPCommonFunc.hasValue(totShipWt)) {
                if (TOT_SHIP_WT_LENGTH < totShipWt.length()) {
                    ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.totShipWt, new BigDecimal(TOT_SHIP_WT_MAX));
                } else {
                    ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.totShipWt, getNumericNullToZero((BigDecimal) wmsInbdTrxOshpMap.get("WMS_TOT_WT")));
                }
            }
            ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.totFrtAmt, getNumericNullToZero((BigDecimal) wmsInbdTrxOshpMap.get("WMS_FRT_CHRG_AMT")));
            // QC#53233 Mod End
            ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.srcTpCd, SRC_TP_CD_WMS);
            EZDTBLAccessor.insert(shpgOrdConfWrkTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdConfWrkTMsg.getReturnCode())) {

                S21InfoLogOutput.println(NLBM1064E);
                S21InfoLogOutput.println("createHeadData SO_NUM:" + (String) wmsInbdTrxShipMap.get("OTBD_ORD_NUM"));
                throw new S21AbendException(NLBM1064E);
            }
        }

        // Update SO Confirmation
        NLZC211001PMsg soConfApiPMsg = new NLZC211001PMsg();
        ZYPEZDItemValueSetter.setValue(soConfApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(soConfApiPMsg.wrkTrxId, wrkTrxId);
        ZYPEZDItemValueSetter.setValue(soConfApiPMsg.soNum, soNum);

        // QC#20554 Mod
        List<String> apiErrMsgList = null;
        try {
            NLZC211001 api = new NLZC211001();
            api.execute(soConfApiPMsg, ONBATCH_TYPE.BATCH);
        } catch (S21AbendException e) {
            apiErrMsgList = S21ApiUtil.getXxMsgIdList(soConfApiPMsg);
            if (apiErrMsgList == null || apiErrMsgList.isEmpty()) {
                apiErrMsgList.add("NLZM2030E");
            }
        }

        // Check API Result
        if (apiErrMsgList == null || apiErrMsgList.isEmpty()) {
                apiErrMsgList = S21ApiUtil.getXxMsgIdList(soConfApiPMsg);
        }
        // QC#20554 Mod End
        if (apiErrMsgList != null && !apiErrMsgList.isEmpty()) {

            String errorCode = null;

            for (String xxMsgId : apiErrMsgList) {
                // QC#53009
                if ("NLZM2521E".equals(xxMsgId)) {
                    outputErrNagativeQty(xxMsgId, soNum);
                // QC#52802 Add Start
                } else if ("NLZM2044W".equals(xxMsgId)){
                    continue;
                // QC#52802 Add End
                // QC#58853 Add Start
                } else if ("NLZM1024E".equals(xxMsgId)) {
                    String srcOrdNum = (String) wmsInbdTrxShipMap.get("SRC_ORD_NUM");
                    String strSceOrdTpCdSo = (String) wmsInbdTrxShipMap.get("SCE_ORD_TP_CD");
                    if (ZYPCommonFunc.hasValue(srcOrdNum) && ZYPCommonFunc.hasValue(strSceOrdTpCdSo) && SCE_ORD_TP.DC_TRANSFER.equals(strSceOrdTpCdSo)) {
                        CPOTMsg cpoTMsg = new CPOTMsg();
                        ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, srcOrdNum);

                        cpoTMsg = (CPOTMsg) EZDTBLAccessor.findByKey(cpoTMsg);

                        if (cpoTMsg != null && CPO_SRC_TP.INVENTORY_REQUEST_ENTRY.equals(cpoTMsg.cpoSrcTpCd.getValue())) {
                            srcOrdNum = cpoTMsg.ordSrcRefNum.getValue();
                        }
                    }
                    Map<String, String> mailParam = new HashMap<String, String>();
                    mailParam.put(NLXMailSend.KEY_MESSAGE_ID, "NLGM0095E");
                    mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage("NLGM0095E", new String[]{soNum, (String) wmsInbdTrxShipMap.get("VIA_WMS_CARR_CD"), srcOrdNum}));
                    errMsgList.add(mailParam);
                    S21InfoLogOutput.println(S21MessageFunc.clspGetMessage("NLGM0095E", new String[]{soNum, (String) wmsInbdTrxShipMap.get("VIA_WMS_CARR_CD"), srcOrdNum}));
                // QC#58853 Add End
                } else {
                    outputErr(xxMsgId);
                }

                if (xxMsgId.endsWith(MSG_TYPE_ERROR) && !ZYPCommonFunc.hasValue(errorCode)) {

                    errorCode = xxMsgId;
                }
            }

            if (ZYPCommonFunc.hasValue(errorCode)) {

                rollback();

                if (ZYPCommonFunc.hasValue(oshpWmsInbdTrxPk)) {
                    // QC#53009. Mod QC#58853
                    if ("NLZM2521E".equals(errorCode) || "NLZM1024E".equals(errorCode)) {
                        updTrxProcSts(oshpWmsInbdTrxPk, PROC_STS.COMPLEATED, errorCode);
                    } else {
                        updTrxProcSts(oshpWmsInbdTrxPk, PROC_STS.ERROR, errorCode);
                    }
                    this.totalErrCount++;
                }

                return errorCode;
            }
        }

        // Update Work Table Data
        updSoConfWrkTbl(wrkTrxId, soNum);

        // Update SO Close Date
        String errorCode = updWmsInbdSoHdrClsDt(wmsInbdTrxOshpMap, soNum);

        // QC#21913 T.Hakodate ADD START
        // create SHPG_ORD_PRO_NUM_LIST
        if (wmsInbdTrxShipMap != null) {
            createShpgOrdProNumList(wmsInbdTrxShipMap);
        }

        // QC#21913 T.Hakodate ADD END

        if (ZYPCommonFunc.hasValue(errorCode)) {

            rollback();
            S21InfoLogOutput.println(errorCode);

            if (ZYPCommonFunc.hasValue(oshpWmsInbdTrxPk)) {

                updTrxProcSts(oshpWmsInbdTrxPk, PROC_STS.ERROR, errorCode);
                this.totalErrCount++;
            }

            return errorCode;
        }

        // Update WMS Inbound Trx Data (OSHP)
        if (ZYPCommonFunc.hasValue(oshpWmsInbdTrxPk)) {

            updTrxProcSts(oshpWmsInbdTrxPk, PROC_STS.COMPLEATED, null);
            this.normalRecCount++;
        }

        return null;
    }

    /**
     * Create SHPG_ORD_CONF_DTL_WRK
     * 
     * @param wmsInbdTrxShipMap Map<String, Object>
     * @param soNum String
     * @param wrkTrxId String
     * @param sqId String
     */
    private void cratShpgOrdConfDtlWrk(Map<String, Object> wmsInbdTrxShipMap, String soNum, String wrkTrxId, String sqId) {

        String strProNum = null;
        String vndCd = null;
        String bolNum = null;
        String shpgSvcLvlCd = null;

        if (WH_GP_CD_3_THIRD_PARTY_WH.equals(whGpCd)) {
            strProNum = (String) wmsInbdTrxShipMap.get("PRO_NUM");
            vndCd = (String) wmsInbdTrxShipMap.get("CARR_SCAC_CD");

            if (!ZYPCommonFunc.hasValue(vndCd)) {
                vndCd = (String) wmsInbdTrxShipMap.get("WMS_CARR_CD");
            }

            bolNum = (String) wmsInbdTrxShipMap.get("BOL_NUM");
            shpgSvcLvlCd = (String) wmsInbdTrxShipMap.get("SHPG_SVC_LVL_CD");

        } else {

            // QC#19930 PRO_NUM
            if (WH_GP_CD_2_DBS.equals(whGpCd)) {
                strProNum = (String) wmsInbdTrxShipMap.get("PRO_NUM");
            } else {
                strProNum = (String) wmsInbdTrxShipMap.get("PRO_NUM");
                if (!ZYPCommonFunc.hasValue(strProNum)) {
                    strProNum = (String) wmsInbdTrxShipMap.get("TMS_TRK_NUM");
                }
                if ("NONE".equals(strProNum)) {
                    strProNum = null;
                }
            }

            // QC#19930 VND_CD&SHPG_SVC_LVL_CD
            if (WH_GP_CD_2_DBS.equals(whGpCd)) {
                // START 2019/02/06 T.Ogura [QC#30182,MOD]
//                // QC#29979 Update.
//                Map<String, Object> tplCarrSvc = getTplCarrSvc(//
//                        (String) wmsInbdTrxShipMap.get("WMS_CARR_CD") //
//                        ,(String) wmsInbdTrxShipMap.get("WH_CD"));
//                if (tplCarrSvc != null) {
//                    vndCd = (String) tplCarrSvc.get("CARR_CD");
//                    shpgSvcLvlCd = (String) tplCarrSvc.get("SHPG_SVC_LVL_CD");
//                }
                String wmsCarrCd = (String) wmsInbdTrxShipMap.get("WMS_CARR_CD");
                if (ZYPCommonFunc.hasValue(wmsCarrCd)) {
                    Map<String, Object> tplCarrSvc = getTplCarrSvc(wmsCarrCd);
                    if (tplCarrSvc != null) {
                        vndCd = (String) tplCarrSvc.get("CARR_CD");
                        shpgSvcLvlCd = (String) tplCarrSvc.get("SHPG_SVC_LVL_CD");
                    }
                } else {
                    vndCd = ZYPCodeDataUtil.getVarCharConstValue(NLGB0120_CARR_CD_IS_NOT_SET, glblCmpyCd);
                }
                // END   2019/02/06 T.Ogura [QC#30182,MOD]
                if (!ZYPCommonFunc.hasValue(shpgSvcLvlCd)) {
                    shpgSvcLvlCd = "";
                }
            } else {
                vndCd = (String) wmsInbdTrxShipMap.get("CARR_SCAC_CD");
                shpgSvcLvlCd = (String) wmsInbdTrxShipMap.get("SHPG_SVC_LVL_CD");
            }
            if (!ZYPCommonFunc.hasValue(vndCd)) {
                vndCd = (String) wmsInbdTrxShipMap.get("WMS_CARR_CD");
            }

            // QC#19930 BOL_NUM
            if (WH_GP_CD_2_DBS.equals(whGpCd)) {
                bolNum = (String) wmsInbdTrxShipMap.get("BOL_NUM");
            } else {
                String carrScacCd = (String) wmsInbdTrxShipMap.get("CARR_SCAC_CD");
                if (ZYPCommonFunc.hasValue(carrScacCd)) {
                    bolNum = (String) wmsInbdTrxShipMap.get("WMS_TRNSP_TP_CD");
                } else {
                    bolNum = (String) wmsInbdTrxShipMap.get("WMS_CARR_CD");
                }

                if (WMS_TRNSP_TP.PCL.equals(bolNum) && ZYPCommonFunc.hasValue(strProNum)) {
                    bolNum = null;
                } else {
                    bolNum = (String) wmsInbdTrxShipMap.get("BOL_NUM");
                    if ("NONE".equals(bolNum)) {
                        //strProNum = null;
                        bolNum = null;
                    }
                }
            }
            
        }

        SHPG_ORD_CONF_DTL_WRKTMsg shpgOrdConfDtlWrkTMsg = new SHPG_ORD_CONF_DTL_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.wrkTrxId, wrkTrxId);
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.sqId, sqId);
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.soSlpNum, ZYPCommonFunc.leftPad(this.objNullToString(wmsInbdTrxShipMap.get("OTBD_ORD_LINE_NUM")), LG_INTFC_OTBD_ORD_LINE_NUM, PAD_VAL_ZERO));
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.proNum, strProNum);
        // 2018/11/20 Mod Start QC#28955
//        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.vndCd, vndCd);
        if (ZYPCommonFunc.hasValue(vndCd)) {
            if (vndCd.length() > 20) {
                ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.vndCd, vndCd.substring(0,20));
            } else {
                ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.vndCd, vndCd);
            }
        }
        // 2018/11/20 Mod End QC#28955
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.mdseCd, (String) wmsInbdTrxShipMap.get("WMS_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.whCd, (String) wmsInbdTrxShipMap.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.fromStkStsCd, (String) wmsInbdTrxShipMap.get("FROM_STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.s80StkStsCd, (String) wmsInbdTrxShipMap.get("S80_STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.bolNum, bolNum);
        // QC#53233 Mod Start
        String totShipWt = String.valueOf(wmsInbdTrxShipMap.get("WMS_TOT_WT"));
        if (ZYPCommonFunc.hasValue(totShipWt)) {
        	if (TOT_SHIP_WT_LENGTH < totShipWt.length()) {
        		ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.totShipWt, new BigDecimal(TOT_SHIP_WT_MAX));
        	} else {
        		ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.totShipWt, getNumericNullToZero((BigDecimal) wmsInbdTrxShipMap.get("WMS_TOT_WT")));
        	}
        }
        // QC#53233 Mod End
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.totFrtAmt, getNumericNullToZero((BigDecimal) wmsInbdTrxShipMap.get("WMS_FRT_CHRG_AMT")));
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.shipQty, getNumericNullToZero((BigDecimal) wmsInbdTrxShipMap.get("WMS_ORD_QTY")));
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.soProcStsCd, "S");
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.srcTpCd, SRC_TP_CD_WMS);
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.ucc128Cd, (String) wmsInbdTrxShipMap.get("WMS_OUT_CNTNR_NUM"));
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.shipDtTmTs, this.getSalesDateTm());
        // QC#19930
//      ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.shpgSvcLvlCd, (String) wmsInbdTrxShipMap.get("SHPG_SVC_LVL_CD"));
        ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.shpgSvcLvlCd, shpgSvcLvlCd);

        EZDTBLAccessor.insert(shpgOrdConfDtlWrkTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdConfDtlWrkTMsg.getReturnCode())) {

            S21InfoLogOutput.println(NLBM1064E);
            S21InfoLogOutput.println("createHeadData SO_NUM:" + soNum);
            throw new S21AbendException(NLBM1064E);
        }

        updTrxProcSts((BigDecimal) wmsInbdTrxShipMap.get("WMS_INBD_TRX_PK"), PROC_STS.COMPLEATED, null);
    }

    /**
     * Create SHIP_SER_NUM_WRK
     * 
     * @param wmsInbdTrxSerlMap Map<String, Object>
     * @param soNum String
     * @param wrkTrxId String
     * @param sqId String
     * @param invtyLocCd String
     */
    private void cratShipSerNumWrk(Map<String, Object> wmsInbdTrxSerlMap, String soNum, String wrkTrxId, String sqId, String invtyLocCd) {

        SHIP_SER_NUM_WRKTMsg shipSerNumWrkTMsg = new SHIP_SER_NUM_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.wrkTrxId, wrkTrxId);
        ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.sqId, sqId);
        ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.mdseCd, (String) wmsInbdTrxSerlMap.get("WMS_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.whCd, invtyLocCd);
        ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.serNum, (String) wmsInbdTrxSerlMap.get("SER_NUM"));
        ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.serTakeDtTmTs, (String) wmsInbdTrxSerlMap.get("WMS_TRX_DT_TM_TS"));
        ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.soSlpNum, ZYPCommonFunc.leftPad(this.objNullToString(wmsInbdTrxSerlMap.get("OTBD_ORD_LINE_NUM")), LG_INTFC_OTBD_ORD_LINE_NUM, PAD_VAL_ZERO));
        ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.serIntfcProcStsCd, "0");
        ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.srcTpCd, SRC_TP_CD_WMS);

        EZDTBLAccessor.insert(shipSerNumWrkTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shipSerNumWrkTMsg.getReturnCode())) {

            S21InfoLogOutput.println(NLBM1064E);
            S21InfoLogOutput.println("createHeadData SO_NUM:" + soNum);
            throw new S21AbendException(NLBM1064E);
        }

        updTrxProcSts((BigDecimal) wmsInbdTrxSerlMap.get("WMS_INBD_TRX_PK"), PROC_STS.COMPLEATED, null);
    }

    /**
     * Get Sales Date Time stamp
     * @return yyyymmdd + hhmmss
     */
    private String getSalesDateTm() {

        String yyyymmdd = ZYPDateUtil.getSalesDate();
        String hhmmss = ZYPDateUtil.getCurrentSystemTime("HHmmss");

        return yyyymmdd + hhmmss;
    }

    /**
     * getNumericNullToZero
     * @param strVal BigDecimal
     * @return BigDecimal
     */
    private BigDecimal getNumericNullToZero(BigDecimal strVal) {

        if (ZYPCommonFunc.hasValue(strVal)) {

            return strVal;

        } else {

            return BigDecimal.ZERO;
        }
    }

    /**
     * Get Order Type Code to convert S21
     * @param s80OrdTpCd String
     * @param soNum String
     * @return String
     */
    private String convOrdTpCd(String s80OrdTpCd, String soNum) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("s80OrdTpCd", s80OrdTpCd);
        queryParam.put("inbdOtbdCd", INBD_OTBD.OUTBOUND);
        List<String> sceOrdTpLst = (List<String>) this.ssmBatchClient.queryObjectList("getSceOrdTp", queryParam);

        if (sceOrdTpLst == null || sceOrdTpLst.isEmpty()) {

            return null;
        }

        if (sceOrdTpLst.size() == 1) {

            return sceOrdTpLst.get(0);

        } else {

            String chkSceOrdTpCd = null;

            if (ZYPCommonFunc.hasValue(soNum)) {

                SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.soNum, soNum);
                shpgOrdTMsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKey(shpgOrdTMsg);

                if (shpgOrdTMsg != null) {

                    chkSceOrdTpCd = shpgOrdTMsg.sceOrdTpCd.getValue();

                } else {

                    return null;
                }
            }

            for (String sceOrdTpCd : sceOrdTpLst) {

                if (ZYPCommonFunc.hasValue(sceOrdTpCd) && sceOrdTpCd.equals(chkSceOrdTpCd)) {

                    return sceOrdTpCd;
                }
            }
        }

        return null;
    }

    /**
     * SO Confirmation update of Freight Charge
     * @param oshpList ArrayList<Map<String, Object>>
     * @param shipList ArrayList<Map<String, Object>>
     * @param oshpMap Map<String, Object>
     * @param ochgList ArrayList<Map<String, Object>>
     * @return String
     */
    private String updFreightCharge(ArrayList<Map<String, Object>> oshpList, ArrayList<Map<String, Object>> shipList, Map<String, Object> oshpMap, ArrayList<Map<String, Object>> ochgList) {

        String oshpWhCd = (String) oshpMap.get("WH_CD");
        String oshpWmsShipId = (String) oshpMap.get("WMS_SHIP_ID");
        String oshpBolNum = (String) oshpMap.get("BOL_NUM");
        String oshpProNum = (String) oshpMap.get("PRO_NUM");
        String oshpTmsTrkNum = (String) oshpMap.get("TMS_TRK_NUM");

        if (!ZYPCommonFunc.hasValue(oshpProNum)) {

            oshpProNum = oshpTmsTrkNum;
        }

        // The Total of Freight Charge
        BigDecimal totalFrtCharge = BigDecimal.ZERO;

        for (Map<String, Object> ochgMap : ochgList) {

            String whCd = (String) ochgMap.get("WH_CD");
            String wmsShipId = (String) ochgMap.get("WMS_SHIP_ID");

            if (oshpWhCd.equals(whCd) && oshpWmsShipId.equals(wmsShipId)) {

                if (ZYPCommonFunc.hasValue((BigDecimal) ochgMap.get("TMS_FRT_CHRG_AMT"))) {

                    // ITG#613664 MOD START
                    // totalFrtCharge = new BigDecimal(recMap.get(COL_OCHG_TMS_FRT_CHRG_AMT));
                    totalFrtCharge = totalFrtCharge.add((BigDecimal) ochgMap.get("TMS_FRT_CHRG_AMT"));
                    // ITG#613664 MOD END
                }
            }
        }

        // Extraction of Total Weight
        ArrayList<Map<String, Object>> wtList = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> shipMap : shipList) {

            String whCd = (String) shipMap.get("WH_CD");
            String wmsShipId = (String) shipMap.get("WMS_SHIP_ID");
            String otbdOrdNum = (String) shipMap.get("OTBD_ORD_NUM");
            String wmsOutCntnrNum = (String) shipMap.get("WMS_OUT_CNTNR_NUM");
            BigDecimal wmsTotWt = (BigDecimal) shipMap.get("WMS_TOT_WT");

            if (oshpWhCd.equals(whCd) && oshpWmsShipId.equals(wmsShipId)) {

                // Duplication is eliminated.
                boolean duplicate = false;

                for (Map<String, Object> keyMap : wtList) {

                    String keyOtbdOrdNum = (String) keyMap.get("otbdOrdNum");
                    String keyWmsOutCntnrNum = (String) keyMap.get("wmsOutCntnrNum");
                    BigDecimal keyWmsTotWt = (BigDecimal) keyMap.get("wmsTotWt");

                    if (keyOtbdOrdNum.equals(otbdOrdNum) //
                            && keyWmsOutCntnrNum.equals(wmsOutCntnrNum) //
                            && keyWmsTotWt.compareTo(wmsTotWt) == 0) {

                        duplicate = true;
                        break;
                    }
                }

                if (!duplicate) {

                    Map<String, Object> wtMap = new HashMap<String, Object>();
                    wtMap.put("otbdOrdNum", otbdOrdNum);
                    wtMap.put("wmsOutCntnrNum", wmsOutCntnrNum);
                    wtMap.put("wmsTotWt", wmsTotWt);
                    wtList.add(wtMap);
                }
            }
        }

        // The Total of Total Weight
        BigDecimal totalShipWt = BigDecimal.ZERO;
        Map<String, BigDecimal> inbdOrdNumMap = new HashMap<String, BigDecimal>();

        for (Map<String, Object> wtMap : wtList) {

            String otbdOrdNum = (String) wtMap.get("otbdOrdNum");

            if (!inbdOrdNumMap.containsKey(otbdOrdNum)) {

                BigDecimal totalWt = BigDecimal.ZERO;

                for (Map<String, Object> wtMap2 : wtList) {

                    String otbdOrdNum2 = (String) wtMap2.get("otbdOrdNum");

                    if (otbdOrdNum.equals(otbdOrdNum2)) {

                        BigDecimal wmsTotWt = null;

                        if (ZYPCommonFunc.hasValue((BigDecimal) wtMap2.get("wmsTotWt"))) {

                            wmsTotWt = (BigDecimal) wtMap2.get("wmsTotWt");

                        } else {

                            wmsTotWt = BigDecimal.ZERO;
                        }

                        if (BigDecimal.ZERO.compareTo(wmsTotWt) == 0) {

                            totalWt = BigDecimal.ONE;
                        }

                        totalWt = totalWt.add(wmsTotWt);
                        totalShipWt = totalShipWt.add(wmsTotWt);
                    }
                }

                inbdOrdNumMap.put(otbdOrdNum, totalWt);
            }
        }

        // Update freight charge using ratio
        for (Map.Entry<String, BigDecimal> e : inbdOrdNumMap.entrySet()) {

            String otbdOrdNum = e.getKey();
            BigDecimal totalWt = e.getValue();
            BigDecimal wmsFrtChrgAmt = BigDecimal.ZERO;

            if (BigDecimal.ZERO.compareTo(totalShipWt) != 0) {

                // Calculation of freight charge
                wmsFrtChrgAmt = totalFrtCharge.multiply(totalWt).divide(totalShipWt, 2, BigDecimal.ROUND_HALF_UP);
            }

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("whCd", oshpWhCd);
            queryParam.put("wmsShipId", oshpWmsShipId);
            queryParam.put("otbdOrdNum", otbdOrdNum);
            queryParam.put("wmsTaskCd", WMS_TASK.OSHP);
            List<WMS_INBD_TRXTMsg> wmsInbdTrxPkList = this.ssmBatchClient.queryObjectList("locFrtChrg", queryParam);

            for (int i = 0; i < wmsInbdTrxPkList.size(); i++) {

                WMS_INBD_TRXTMsg wmsInbdTrxTmsg = (WMS_INBD_TRXTMsg) wmsInbdTrxPkList.get(i);
                ZYPEZDItemValueSetter.setValue(wmsInbdTrxTmsg.wmsFrtChrgAmt, wmsFrtChrgAmt);
                ZYPEZDItemValueSetter.setValue(wmsInbdTrxTmsg.wmsTotWt, totalWt);
                EZDTBLAccessor.updateSelectionField(wmsInbdTrxTmsg, new String[] {"wmsFrtChrgAmt", "wmsTotWt" });

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxTmsg.getReturnCode())) {

                    outputErr(NLGM0046E, new String[] {wmsInbdTrxTmsg.getTableName() //
                            , NLXCMsgHelper.toListedString("GLBL_CMPY_CD", "WMS_INBD_TRX_PK") //
                            , NLXCMsgHelper.toListedString(wmsInbdTrxTmsg.glblCmpyCd.getValue() //
                                    , wmsInbdTrxTmsg.wmsInbdTrxPk.getValue()) });
                    return NLGM0046E;

                } else {

                    // Re-setting the freight charge that the updated
                    String oshpTrxPk = wmsInbdTrxTmsg.wmsInbdTrxPk.getValue().toString();

                    for (Map<String, Object> recMap : oshpList) {

                        if (oshpTrxPk.equals(recMap.get("WMS_INBD_TRX_PK"))) {

                            recMap.remove("WMS_FRT_CHRG_AMT");
                            recMap.remove("WMS_TOT_WT");
                            recMap.put("WMS_FRT_CHRG_AMT", wmsFrtChrgAmt);
                            recMap.put("WMS_TOT_WT", totalWt);
                            break;
                        }
                    }
                }
            }
        }

        // Set the total freight charge on one BOL/Pro# combination on the shipment.
        if (BigDecimal.ZERO.compareTo(totalFrtCharge) != 0 && !inbdOrdNumMap.isEmpty()) {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("whCd", oshpWhCd);
            queryParam.put("wmsShipId", oshpWmsShipId);
            queryParam.put("wmsTaskCd", WMS_TASK.SHIP);
            queryParam.put("bolNum", oshpBolNum);
            queryParam.put("proNum", oshpProNum);
            List<WMS_INBD_TRXTMsg> wmsInbdTrxPkList = this.ssmBatchClient.queryObjectList("locFrtChrgBol", queryParam);

            for (WMS_INBD_TRXTMsg wmsInbdTrxTmsg : wmsInbdTrxPkList) {

                ZYPEZDItemValueSetter.setValue(wmsInbdTrxTmsg.wmsFrtChrgAmt, totalFrtCharge);
                EZDTBLAccessor.updateSelectionField(wmsInbdTrxTmsg, new String[] {"wmsFrtChrgAmt" });

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxTmsg.getReturnCode())) {

                    outputErr(NLGM0046E, new String[] {wmsInbdTrxTmsg.getTableName() //
                            , NLXCMsgHelper.toListedString("GLBL_CMPY_CD", "WMS_INBD_TRX_PK") //
                            , NLXCMsgHelper.toListedString(wmsInbdTrxTmsg.glblCmpyCd.getValue() //
                                    , wmsInbdTrxTmsg.wmsInbdTrxPk.getValue()) });
                    return NLGM0046E;

                } else {

                    // Re-setting the freight charge that the updated
                    String shipTrxPk = wmsInbdTrxTmsg.wmsInbdTrxPk.getValue().toString();

                    for (Map<String, Object> recMap : shipList) {
                        if (shipTrxPk.equals(recMap.get("WMS_INBD_TRX_PK"))) {
                            recMap.remove("WMS_FRT_CHRG_AMT");
                            recMap.put("WMS_FRT_CHRG_AMT", totalFrtCharge);
                            break;
                        }
                    }
                }
            }
        }

        return "";
    }

    /**
     * Update SO Confirmation Work Table
     * @param wrkTrxId String
     * @param soNum String
     */
    private void updSoConfWrkTbl(String wrkTrxId, String soNum) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("wrkTrxId", wrkTrxId);
        param.put("soNum", soNum);
        param.put("procStsCd", PROC_STS.IN_COMPLETED);

        // Update SHPG_ORD_CONF_WRK & WMS_INBD_SO_HDR
        List<SHPG_ORD_CONF_WRKTMsg> shpgOrdConfWrkList = (List<SHPG_ORD_CONF_WRKTMsg>) this.ssmBatchClient.queryObjectList("getShpgOrdConfWrkForUpd", param);

        if (shpgOrdConfWrkList != null && !shpgOrdConfWrkList.isEmpty()) {

            for (SHPG_ORD_CONF_WRKTMsg shpgOrdConfWrkTMsg : shpgOrdConfWrkList) {

                shpgOrdConfWrkTMsg = (SHPG_ORD_CONF_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(shpgOrdConfWrkTMsg);

                if (shpgOrdConfWrkTMsg == null) {

                    S21InfoLogOutput.println(NLBM1063E);
                    S21InfoLogOutput.println("UpdateHeadData SO_NUM:" + soNum);
                    throw new S21AbendException(NLBM1063E);
                }

                // Update SHPG_ORD_CONF_WRK
                ZYPEZDItemValueSetter.setValue(shpgOrdConfWrkTMsg.procStsCd, PROC_STS.COMPLEATED);
                EZDTBLAccessor.update(shpgOrdConfWrkTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdConfWrkTMsg.getReturnCode())) {

                    S21InfoLogOutput.println(NLBM1064E);
                    S21InfoLogOutput.println("UpdateHeadData SO_NUM:" + soNum);
                    throw new S21AbendException(NLBM1064E);
                }
            }
        }

        // Get SHPG_ORD_CONF_DTL_WRK
        List<SHPG_ORD_CONF_DTL_WRKTMsg> shpgOrdConfDtlWrkList = (List<SHPG_ORD_CONF_DTL_WRKTMsg>) this.ssmBatchClient.queryObjectList("getShpgOrdConfDtlWrkForUpd", param);

        if (shpgOrdConfDtlWrkList != null && !shpgOrdConfDtlWrkList.isEmpty()) {

            for (SHPG_ORD_CONF_DTL_WRKTMsg shpgOrdConfDtlWrkTMsg : shpgOrdConfDtlWrkList) {

                shpgOrdConfDtlWrkTMsg = (SHPG_ORD_CONF_DTL_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(shpgOrdConfDtlWrkTMsg);

                if (shpgOrdConfDtlWrkTMsg == null) {

                    S21InfoLogOutput.println(NLBM1063E);
                    S21InfoLogOutput.println("UpdateDetailData SO_NUM:" + soNum);
                    throw new S21AbendException(NLBM1063E);
                }

                // Update SHPG_ORD_CONF_DTL_WRK
                ZYPEZDItemValueSetter.setValue(shpgOrdConfDtlWrkTMsg.procStsCd, PROC_STS.COMPLEATED);
                EZDTBLAccessor.update(shpgOrdConfDtlWrkTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdConfDtlWrkTMsg.getReturnCode())) {

                    S21InfoLogOutput.println(NLBM1064E);
                    S21InfoLogOutput.println("UpdateDetailData SO_NUM:" + soNum);
                    throw new S21AbendException(NLBM1064E);
                }
            }
        }

        // Get SHIP_SER_NUM_WRK
        List<SHIP_SER_NUM_WRKTMsg> shipSerNumWrkList = (List<SHIP_SER_NUM_WRKTMsg>) this.ssmBatchClient.queryObjectList("getShipSerNumWrkForUpd", param);

        if (shipSerNumWrkList != null && !shipSerNumWrkList.isEmpty()) {

            for (SHIP_SER_NUM_WRKTMsg shipSerNumWrkTMsg : shipSerNumWrkList) {

                shipSerNumWrkTMsg = (SHIP_SER_NUM_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(shipSerNumWrkTMsg);

                if (shipSerNumWrkTMsg == null) {

                    S21InfoLogOutput.println(NLBM1063E);
                    S21InfoLogOutput.println("UpdateSerialData SO_NUM:" + soNum);
                    throw new S21AbendException(NLBM1063E);
                }

                // Update SHIP_SER_NUM_WRK
                ZYPEZDItemValueSetter.setValue(shipSerNumWrkTMsg.procStsCd, PROC_STS.COMPLEATED);
                EZDTBLAccessor.update(shipSerNumWrkTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shipSerNumWrkTMsg.getReturnCode())) {

                    S21InfoLogOutput.println(NLBM1064E);
                    S21InfoLogOutput.println("UpdateSerialData SO_NUM:" + soNum);
                    throw new S21AbendException(NLBM1064E);
                }
            }
        }
    }

    /**
     * PKT Status Data Transfer to S21 Interface Table (NLBI0030_01)
     */
    private void doProcPktLscSts() {

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            HashMap<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("wmsTrgtWhCd", trgtWhCdAry);
            queryParam.put("wmsTaskCdArray", new String[] {WMS_TASK.LSC });
            List<String> wmsOrdStsCdList = new ArrayList<String>();
            wmsOrdStsCdList.add(WMS_ORD_STS.PICK);
            // QC#22613 T.Hakodate DEL START
            //wmsOrdStsCdList.add(WMS_ORD_STS.SHOP);
            //wmsOrdStsCdList.add(WMS_ORD_STS.TECH);
            // QC#22613 T.Hakodate DEL END
            queryParam.put("wmsOrdStsCdArray", wmsOrdStsCdList.toArray(new String[wmsOrdStsCdList.size()]));
            queryParam.put("procStsCd", PROC_STS.IN_COMPLETED);
            stmt = this.ssmLLClient.createPreparedStatement("getPktSts", queryParam, execParam);
            rs = stmt.executeQuery();
            String slsDt = ZYPDateUtil.getSalesDate();
            // QC#50971
            List<BigDecimal> svcMachMstrPkList = new ArrayList<BigDecimal>();

            // QC#54135 Add Start
            String[] shpgStsPktSkip = null;
            String shpgStsPktSkips = ZYPCodeDataUtil.getVarCharConstValue(NLGB0120_SHPG_STS_CD_SKIP_PKT, glblCmpyCd);
            if (ZYPCommonFunc.hasValue(shpgStsPktSkips)) {
                shpgStsPktSkip = shpgStsPktSkips.split(",");
            }
            // QC#54135 Add End
            while (rs.next()) {

                String errorCode = "";
                String wmsInbdTrxPk = rs.getString("WMS_INBD_TRX_PK");
                String whCd = rs.getString("WH_CD");
                String wmsTrxDtTmTs = rs.getString("WMS_TRX_DT_TM_TS");
                String otbdOrdNum = rs.getString("OTBD_ORD_NUM");
                String wmsOrdStsCd = rs.getString("WMS_ORD_STS_CD");
                String otbdOrdLineNum = rs.getString("OTBD_ORD_LINE_NUM");
                BigDecimal wmsOrdQty = rs.getBigDecimal("WMS_ORD_QTY");
                String dsSoLineStsCd = rs.getString("DS_SO_LINE_STS_CD");
                String wmsMdseCd = rs.getString("WMS_MDSE_CD");
                String serNum = rs.getString("SER_NUM");
                String wmsLocId = rs.getString("WMS_LOC_ID");
                
                // QC#22613 T.Hakodate ADD START
                String wmsTaskCd = rs.getString("WMS_TASK_CD");
                // QC#22613 T.Hakodate ADD END

                // START 2022/11/11 F.Fadriquela [QC#60772 ADD]
                String wmsStkStsCd = rs.getString("WMS_STK_STS_CD");
                String svcConfigMstrPK = rs.getString("WMS_CNTNR_ID");
                String shpgSerTakeFlg = rs.getString("SHPG_SER_TAKE_FLG");
                String instlBaseCtrlFlg = rs.getString("INSTL_BASE_CTRL_FLG");

                // Check if Stock Status of IB is different from Stock Status of WMS data taken
                if (ZYPConstant.FLG_ON_Y.equals(instlBaseCtrlFlg) && !isItemAvailable(shpgSerTakeFlg, serNum, wmsMdseCd, wmsStkStsCd, svcConfigMstrPK, otbdOrdNum, otbdOrdLineNum)) {
                    updTrxProcSts(new BigDecimal(wmsInbdTrxPk), PROC_STS.IN_COMPLETED, NLZM2414E);
                    commit();
                    continue;
                }
                // END 2022/11/11 F.Fadriquela [QC#60772 ADD]

                // QC#54135 ADD Start
                if (isSkipShpgSts(glblCmpyCd, otbdOrdNum, otbdOrdLineNum, shpgStsPktSkip)) {
                    this.normalRecCount++;
                    updTrxProcSts(new BigDecimal(wmsInbdTrxPk), PROC_STS.COMPLEATED, "");
                    commit();
                    continue;
                    
                }
                // QC#54135 ADD End
                
                NLZC400001PMsg nlzc400001PMsg = new NLZC400001PMsg();
                ZYPEZDItemValueSetter.setValue(nlzc400001PMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(nlzc400001PMsg.slsDt, slsDt);
                ZYPEZDItemValueSetter.setValue(nlzc400001PMsg.pktStsUpdateInfo.no(0).soNum, otbdOrdNum);
                ZYPEZDItemValueSetter.setValue(nlzc400001PMsg.pktStsUpdateInfo.no(0).soSlpNum, ZYPCommonFunc.leftPad(otbdOrdLineNum, 3, "0"));
                ZYPEZDItemValueSetter.setValue(nlzc400001PMsg.pktStsUpdateInfo.no(0).dsSoLineStsCd, dsSoLineStsCd);
                ZYPEZDItemValueSetter.setValue(nlzc400001PMsg.pktStsUpdateInfo.no(0).pktStsTs, wmsTrxDtTmTs);
                ZYPEZDItemValueSetter.setValue(nlzc400001PMsg.pktStsUpdateInfo.no(0).pktStsQty, wmsOrdQty);
                // QC#22613 T.Hakodate ADD START
                ZYPEZDItemValueSetter.setValue(nlzc400001PMsg.pktStsUpdateInfo.no(0).wmsOrdStsCd, wmsOrdStsCd);
                ZYPEZDItemValueSetter.setValue(nlzc400001PMsg.pktStsUpdateInfo.no(0).wmsTaskCd, wmsTaskCd);
                // QC#22613 T.Hakodate ADD   END
                nlzc400001PMsg.pktStsUpdateInfo.setValidCount(1);

                NLZC400001 nlzc400001 = new NLZC400001();
                nlzc400001.execute(nlzc400001PMsg, ONBATCH_TYPE.BATCH);
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nlzc400001PMsg);

                if (msgList.size() > 0) {

                    for (S21ApiMessage msg : msgList) {

                        String msgId = msg.getXxMsgid();

                        if (msgId.endsWith("E")) {

                            errorCode = msgId;
                            break;
                        }
                    }
                }

                SHPG_ORD_DTLTMsg soDtlInMsg = new SHPG_ORD_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(soDtlInMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(soDtlInMsg.soNum, otbdOrdNum);
                ZYPEZDItemValueSetter.setValue(soDtlInMsg.soSlpNum, ZYPCommonFunc.leftPad(otbdOrdLineNum, 3, "0"));
                SHPG_ORD_DTLTMsg soDtlOutMsg = (SHPG_ORD_DTLTMsg) EZDTBLAccessor.findByKey(soDtlInMsg);

                if (soDtlOutMsg == null) {

                    errorCode = "NLZM2391E";
                }

                if (!ZYPCommonFunc.hasValue(errorCode) && ZYPCommonFunc.hasValue(serNum)) {

                    NLZC401001PMsg nlzc401001PMsg = new NLZC401001PMsg();
                    ZYPEZDItemValueSetter.setValue(nlzc401001PMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(nlzc401001PMsg.slsDt, slsDt);
                    // QC#19967 Start
                    ZYPEZDItemValueSetter.setValue(nlzc401001PMsg.serInfo.no(0).xxProcTpCd, NLZC401001Constant.MODE_PICKUP_SERIAL);
                    // QC#19967 End
                    ZYPEZDItemValueSetter.setValue(nlzc401001PMsg.serInfo.no(0).mdseCd, wmsMdseCd);
                    ZYPEZDItemValueSetter.setValue(nlzc401001PMsg.serInfo.no(0).serNum, serNum);
                    ZYPEZDItemValueSetter.setValue(nlzc401001PMsg.serInfo.no(0).soNum, soDtlOutMsg.soNum);
                    ZYPEZDItemValueSetter.setValue(nlzc401001PMsg.serInfo.no(0).soSlpNum, soDtlOutMsg.soSlpNum);
                    ZYPEZDItemValueSetter.setValue(nlzc401001PMsg.serInfo.no(0).trxHdrNum, soDtlOutMsg.trxHdrNum);
                    ZYPEZDItemValueSetter.setValue(nlzc401001PMsg.serInfo.no(0).trxLineNum, soDtlOutMsg.trxLineNum);
                    ZYPEZDItemValueSetter.setValue(nlzc401001PMsg.serInfo.no(0).trxLineSubNum, soDtlOutMsg.trxLineSubNum);
                    nlzc401001PMsg.serInfo.setValidCount(1);

                    NLZC401001 nlzc401001 = new NLZC401001();
                    nlzc401001.execute(nlzc401001PMsg, ONBATCH_TYPE.BATCH);

                    msgList = S21ApiUtil.getXxMsgList(nlzc401001PMsg);

                    if (msgList.size() > 0) {

                        for (S21ApiMessage msg : msgList) {

                            String msgId = msg.getXxMsgid();

                            if (msgId.endsWith("E")) {

                                errorCode = msgId;
                                break;
                            }
                        }
                    }
                }
                // QC#22613 T.Hakodate ADD START
                // update pick configid from WMS
                String pickSvcConfigMstrPK = rs.getString("WMS_CNTNR_ID");
                if (ZYPCommonFunc.hasValue(pickSvcConfigMstrPK) && pickSvcConfigMstrPK.matches("^[0-9]+$")) {
                    ZYPEZDItemValueSetter.setValue(soDtlOutMsg.pickSvcConfigMstrPk, new BigDecimal(rs.getString("WMS_CNTNR_ID")));
                    EZDTBLAccessor.update(soDtlOutMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(soDtlOutMsg.getReturnCode())) {
                        outputErr(NLGM0046E, new String[] {soDtlOutMsg.getTableName() //
                                , NLXCMsgHelper.toListedString("GLBL_CMPY_CD", "SO_NUM", "SO_SLP_NUM") //
                                , NLXCMsgHelper.toListedString(soDtlOutMsg.glblCmpyCd.getValue() //
                                        , soDtlOutMsg.soNum.getValue() //
                                        , soDtlOutMsg.soSlpNum.getValue()) });
                        errorCode = NLZM2130E;

                    } else {

                        // QC#50971 Update CPO_DTL.SVC_MACH_MSTR_PK Start
                        boolean isUpd = false;
                        String trxHdrNum = soDtlOutMsg.trxHdrNum.getValue();
                        String trxLineNum = soDtlOutMsg.trxLineNum.getValue();
                        String trxLineSubNum = soDtlOutMsg.trxLineSubNum.getValue();

                        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
                        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, trxHdrNum);
                        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, trxLineNum);
                        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, trxLineSubNum);

                        CPO_DTLTMsg cpoDtlOutTMsg = (CPO_DTLTMsg) EZDTBLAccessor.findByKey(cpoDtlTMsg);

                        if (cpoDtlOutTMsg != null) {
                            List<BigDecimal> result = getSvcMachMstrPk(new BigDecimal(pickSvcConfigMstrPK), wmsMdseCd, serNum);
                            if (result != null && result.size() == 1) {
                                BigDecimal svcMachMstrPk = result.get(0);
                                if (ZYPCommonFunc.hasValue(svcMachMstrPk) && !svcMachMstrPk.equals(cpoDtlOutTMsg.svcMachMstrPk.getValue())) {

                                    ZYPEZDItemValueSetter.setValue(cpoDtlOutTMsg.svcMachMstrPk, svcMachMstrPk);
                                    isUpd = true;
                                }

                            } else if (result != null && result.size() > 1) {

                                for (int i = 0; i < result.size(); i++) {

                                    BigDecimal svcMachMstrPk = result.get(i);

                                    if (!svcMachMstrPkList.contains(svcMachMstrPk) //
                                            && ZYPCommonFunc.hasValue(svcMachMstrPk) //
                                            && !svcMachMstrPk.equals(cpoDtlOutTMsg.svcMachMstrPk.getValue())) {

                                        ZYPEZDItemValueSetter.setValue(cpoDtlOutTMsg.svcMachMstrPk, svcMachMstrPk);
                                        svcMachMstrPkList.add(svcMachMstrPk);
                                        isUpd = true;
                                        break;
                                    }

                                }
                            }

                            if (result != null && isUpd) {
                                EZDTBLAccessor.update(cpoDtlOutTMsg);
                                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cpoDtlOutTMsg.getReturnCode())) {
                                    outputErr(NLGM0046E, new String[] {cpoDtlOutTMsg.getTableName() //
                                            , NLXCMsgHelper.toListedString("GLBL_CMPY_CD", "CPO_ORD_NUM", "CPO_DTL_LINE_NUM", "CPO_DTL_LINE_SUB_NUM") //
                                            , NLXCMsgHelper.toListedString(cpoDtlOutTMsg.glblCmpyCd.getValue() //
                                                    , cpoDtlOutTMsg.cpoOrdNum.getValue() //
                                                    , cpoDtlOutTMsg.cpoDtlLineNum.getValue() //
                                                    , cpoDtlOutTMsg.cpoDtlLineSubNum.getValue()) });
                                    errorCode = NLZM2130E;
                                }
                            }
                        }
                        // QC#50971 Update CPO_DTL.SVC_MACH_MSTR_PK End
                    }
                }
                // QC#22613 T.Hakodate ADD END
 
                // QC#18757
                // QC#16126
                // QC#19978
                // Validate Tech existence
//                if (!ZYPCommonFunc.hasValue(errorCode) && WMS_ORD_STS.TECH.equals(wmsOrdStsCd)) {
//
//                    if (!ZYPCommonFunc.hasValue(wmsLocId) || getTechMstr(wmsLocId, slsDt) == null) {
//
//                        errorCode = "NLGM0081E";
//                    }
//                }
//
//                if (!ZYPCommonFunc.hasValue(errorCode) && WMS_ORD_STS.TECH.equals(wmsOrdStsCd)) {
//
//                    queryParam = new HashMap<String, Object>();
//                    queryParam.put("glblCmpyCd", glblCmpyCd);
//                    queryParam.put("soNum", otbdOrdNum);
//                    queryParam.put("soSlpNum", ZYPCommonFunc.leftPad(otbdOrdLineNum, 3, "0"));
//                    String baseCmptFlg = (String) this.ssmBatchClient.queryObject("getBaseComponentFlag", queryParam);
//
//                    if (ZYPConstant.FLG_ON_Y.equals(baseCmptFlg)) {
//
//                        // Get W/H Calendar code
//                        String whCalCd = null;
//                        CAL_RELNTMsg calReln = getCalReln(CAL_SUB_TP.WAREHOUSE_CALENDAR, whCd);
//
//                        if (calReln == null) {
//
//                            // If WH Calendar doesn't set up ,use CUSA Calendar.
//                            whCalCd = this.glblCmpyCd;
//
//                        } else {
//
//                            whCalCd = calReln.calTpCd.getValue();
//                        }
//
//                        NSZC075001PMsg nszc075001PMsg = new NSZC075001PMsg();
//                        ZYPEZDItemValueSetter.setValue(nszc075001PMsg.glblCmpyCd, glblCmpyCd);
//                        ZYPEZDItemValueSetter.setValue(nszc075001PMsg.slsDt, slsDt);
//                        ZYPEZDItemValueSetter.setValue(nszc075001PMsg.fldRqstSrcPgmCd, NSZC075001Constant.FLD_RQST_SRC_PGM_WMS);
//                        ZYPEZDItemValueSetter.setValue(nszc075001PMsg.svcPblmTpCd, "9000");
//                        ZYPEZDItemValueSetter.setValue(nszc075001PMsg.serNum, serNum);
//
//                        if (ZYPDateUtil.isBusinessDay(whCalCd, slsDt)) {
//
//                            ZYPEZDItemValueSetter.setValue(nszc075001PMsg.dsSvcCallTpCd, DS_SVC_CALL_TP.SHOP_SETUP);
//
//                        } else {
//
//                            ZYPEZDItemValueSetter.setValue(nszc075001PMsg.dsSvcCallTpCd, DS_SVC_CALL_TP.SHOP_SETUP_2);
//                        }
//
//                        // ZYPEZDItemValueSetter.setValue(apiPMsg.svcPblmTpCd, SVC_PBLM_TP.(String) rcvTaskInfoMap.get(SVC_PBLM_TP_CD));
//                        ZYPEZDItemValueSetter.setValue(nszc075001PMsg.psnCd, wmsLocId);
//                        // ZYPEZDItemValueSetter.setValue(apiPMsg.xxAllPsnNm, param.CsaFieldRequestList.no(0).techNm);
//                        // ZYPEZDItemValueSetter.setValue(apiPMsg.svcCmntTxt, param.CsaFieldRequestList.no(0).svcCmntTxt);
//                        ZYPEZDItemValueSetter.setValue(nszc075001PMsg.psnCd, wmsLocId);
//                        ZYPEZDItemValueSetter.setValue(nszc075001PMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
//                        // START 2017/02/20 K.Kojima [QC#16301,ADD]
//                        ZYPEZDItemValueSetter.setValue(nszc075001PMsg.xxRqstFlg_NC, ZYPConstant.FLG_ON_Y);
//                        // END 2017/02/20 K.Kojima [QC#16301,ADD]
//
//                        NSZC075001 nszc075001 = new NSZC075001();
//                        nszc075001.execute(nszc075001PMsg, ONBATCH_TYPE.BATCH);
//
//                        msgList = S21ApiUtil.getXxMsgList(nszc075001PMsg);
//
//                        if (msgList.size() > 0) {
//
//                            for (S21ApiMessage msg : msgList) {
//
//                                String msgId = msg.getXxMsgid();
//
//                                if (msgId.endsWith("E")) {
//
//                                    errorCode = msgId;
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                }

                if (ZYPCommonFunc.hasValue(errorCode)) {

                    this.totalErrCount++;
                    rollback();
                    // Update Process Status Code(ERROR)
                    updTrxProcSts(new BigDecimal(wmsInbdTrxPk), PROC_STS.ERROR, errorCode);
                    commit();

                } else {

                    this.normalRecCount++;
                    // Update Process Status Code(COMPLEATED)
                    updTrxProcSts(new BigDecimal(wmsInbdTrxPk), PROC_STS.COMPLEATED, "");
                    commit();
                }
            }

            if (this.normalRecCount > 0) {
                // Insert into INTERFACE_TRANSACTION
                // trxAccessor.createIntegrationRecordForBatch(INTFC_ID.RWS_COMPLETE_MW,
                // trxId);
                commit();
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * PKT Status Data Transfer to S21 Interface Table (NLBI0030_01)
     */
    private void doProcPktOscSts() {

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            HashMap<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("wmsTrgtWhCd", trgtWhCdAry);
            queryParam.put("wmsTaskCdArray", new String[] {WMS_TASK.OSC });
            List<String> wmsOrdStsCdList = new ArrayList<String>();
            // QC#22613 T.Hakodate ADD START
            wmsOrdStsCdList.add(WMS_ORD_STS.SCH);
            wmsOrdStsCdList.add(WMS_ORD_STS.PICK);
            wmsOrdStsCdList.add(WMS_ORD_STS.SHOP);
            wmsOrdStsCdList.add(WMS_ORD_STS.TECH);
            wmsOrdStsCdList.add(WMS_ORD_STS.STGE);
            wmsOrdStsCdList.add(WMS_ORD_STS.PREP);
            wmsOrdStsCdList.add(WMS_ORD_STS.PACK);
            // QC#22613 T.Hakodate ADD END

            queryParam.put("wmsOrdStsCdArray", wmsOrdStsCdList.toArray(new String[wmsOrdStsCdList.size()]));
            queryParam.put("procStsCd", PROC_STS.IN_COMPLETED);
            stmt = this.ssmLLClient.createPreparedStatement("getPktSts", queryParam, execParam);
            rs = stmt.executeQuery();

            String slsDt = ZYPDateUtil.getSalesDate();

            // START 05/26/2020 T.Ogura [QC#56691,ADD]
            String[] shpgStsPktSkip = null;
            String shpgStsPktSkips = ZYPCodeDataUtil.getVarCharConstValue(NLGB0120_SHPG_STS_CD_SKIP_PKT, glblCmpyCd);
            if (ZYPCommonFunc.hasValue(shpgStsPktSkips)) {
                shpgStsPktSkip = shpgStsPktSkips.split(",");
            }
            // END   05/26/2020 T.Ogura [QC#56691,ADD]

            while (rs.next()) {

                String errorCode = "";
                String wmsInbdTrxPk = rs.getString("WMS_INBD_TRX_PK");
                String wmsTrxDtTmTs = rs.getString("WMS_TRX_DT_TM_TS");
                String otbdOrdNum = rs.getString("OTBD_ORD_NUM");
                String dsSoLineStsCd = rs.getString("DS_SO_LINE_STS_CD");
                // QC#22613 T.Hakodate ADD START
                String wmsTaskCd = rs.getString("WMS_TASK_CD");
                String wmsOrdStsCd = rs.getString("WMS_ORD_STS_CD");
                // QC#22613 T.Hakodate ADD END

                // START 05/26/2020 T.Ogura [QC#56691,ADD]
                if (isSkipShpgSts(glblCmpyCd, otbdOrdNum, rs.getString("OTBD_ORD_LINE_NUM"), shpgStsPktSkip)) {
                    this.normalRecCount++;
                    updTrxProcSts(new BigDecimal(wmsInbdTrxPk), PROC_STS.COMPLEATED, "");
                    commit();
                    continue;
                }
                // END   05/26/2020 T.Ogura [QC#56691,ADD]

                SHPG_ORD_DTLTMsg inMsg = new SHPG_ORD_DTLTMsg();
                inMsg.setSQLID("001");
                inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                inMsg.setConditionValue("soNum01", otbdOrdNum);
                SHPG_ORD_DTLTMsgArray dtlArr = (SHPG_ORD_DTLTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

                if (dtlArr == null || dtlArr.getValidCount() == 0) {

                    errorCode = NLZM2475E;

                } else {

                    NLZC400001PMsg nlzc400001PMsg = new NLZC400001PMsg();
                    ZYPEZDItemValueSetter.setValue(nlzc400001PMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(nlzc400001PMsg.slsDt, slsDt);

                    // QC#18794 start
                    int cnt = 0;
                    for (int i = 0; i < dtlArr.getValidCount(); i++) {

                        if (ZYPCommonFunc.hasValue(dtlArr.no(i).shpgPlnNum)) {
                            cnt++;
                            ZYPEZDItemValueSetter.setValue(nlzc400001PMsg.pktStsUpdateInfo.no(i).soNum, otbdOrdNum);
                            ZYPEZDItemValueSetter.setValue(nlzc400001PMsg.pktStsUpdateInfo.no(i).soSlpNum, dtlArr.no(i).soSlpNum);
                            ZYPEZDItemValueSetter.setValue(nlzc400001PMsg.pktStsUpdateInfo.no(i).dsSoLineStsCd, dsSoLineStsCd);
                            ZYPEZDItemValueSetter.setValue(nlzc400001PMsg.pktStsUpdateInfo.no(i).pktStsTs, wmsTrxDtTmTs);
                            ZYPEZDItemValueSetter.setValue(nlzc400001PMsg.pktStsUpdateInfo.no(i).pktStsQty, dtlArr.no(i).shpgQty);
                            // QC#22613 T.Hakodate ADD START
                            ZYPEZDItemValueSetter.setValue(nlzc400001PMsg.pktStsUpdateInfo.no(i).wmsOrdStsCd, wmsOrdStsCd);
                            ZYPEZDItemValueSetter.setValue(nlzc400001PMsg.pktStsUpdateInfo.no(i).wmsTaskCd, wmsTaskCd);
                            // QC#22613 T.Hakodate ADD   END
                            nlzc400001PMsg.pktStsUpdateInfo.setValidCount(cnt);
                        } else {
                            continue;
                        }
                        // QC#18794 end
                    }

                    NLZC400001 nlzc400001 = new NLZC400001();
                    nlzc400001.execute(nlzc400001PMsg, ONBATCH_TYPE.BATCH);

                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nlzc400001PMsg);

                    if (msgList.size() > 0) {

                        for (S21ApiMessage msg : msgList) {

                            String msgId = msg.getXxMsgid();

                            if (msgId.endsWith("E")) {

                                errorCode = msgId;
                                break;
                            }
                        }
                    }
                }

                if (ZYPCommonFunc.hasValue(errorCode)) {

                    this.totalErrCount++;
                    rollback();
                    // Update Process Status Code(ERROR)
                    updTrxProcSts(new BigDecimal(wmsInbdTrxPk), PROC_STS.ERROR, errorCode);
                    commit();

                } else {

                    this.normalRecCount++;
                    // Update Process Status Code(COMPLEATED)
                    updTrxProcSts(new BigDecimal(wmsInbdTrxPk), PROC_STS.COMPLEATED, "");
                    commit();
                }
            }

            if (this.normalRecCount > 0) {

                commit();
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * ASN Data Transfer to S21 Interface Table (NLBI0040_01)
     */
    private void doProcAsn() {

        BigDecimal trxId = null;
        S21TransactionTableAccessor trxAccessor = new S21TransactionTableAccessor();
        HashMap<String, WMS_SO_ASN_HDRTMsg> instAsnHdrMap = new HashMap<String, WMS_SO_ASN_HDRTMsg>();
        HashMap<String, List<WMS_SO_ASN_DTLTMsg>> instAsnDtlMap = new HashMap<String, List<WMS_SO_ASN_DTLTMsg>>();

        // ASN data is searched.a WMS_SO_ASN_HDR data and
        // WMS_SO_ASN_DTL data are stored in HashMap and returned.
        HashMap<String, String> shipPkMap = searchAsnData(instAsnHdrMap, instAsnDtlMap);

        // Registration processing of WMS_SO_ASN_HDR or WMS_SO_ASN_DTL or NLBI0040_01
        for (Map.Entry<String, List<WMS_SO_ASN_DTLTMsg>> e : instAsnDtlMap.entrySet()) {

            BigDecimal unitId = BigDecimal.ONE;
            String errorCode = "";
            String wmsInbdTrxPk = e.getKey();
            List<WMS_SO_ASN_DTLTMsg> instAsnDtlList = e.getValue();

            // Insert into WMS_SO_ASN_HDR
            WMS_SO_ASN_HDRTMsg wmsSoAsnHdrTMsg = instAsnHdrMap.get(wmsInbdTrxPk);
            String wmsSoAsnHdrSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_SO_ASN_HDR_SQ).toString();
            ZYPEZDItemValueSetter.setValue(wmsSoAsnHdrTMsg.wmsSoAsnHdrId, wmsSoAsnHdrSq);
            S21FastTBLAccessor.insert(wmsSoAsnHdrTMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(wmsSoAsnHdrTMsg.getReturnCode())) {

                this.totalErrCount++;
                rollback();
                errorCode = NLGM0045E;
                outputErr(NLGM0045E, new String[] {wmsSoAsnHdrTMsg.getTableName(), "WMS_INBD_TRX" //
                        , "WMS_INBD_TRX_PK", wmsInbdTrxPk });
                // Update Process Status Code(ASN)
               updTrxProcSts(new BigDecimal(wmsInbdTrxPk), PROC_STS.ERROR, errorCode);

                for (Map.Entry<String, String> e2 : shipPkMap.entrySet()) {

                    // Update Process Status Code(SHIP)
                    if (wmsInbdTrxPk.equals(e2.getValue())) {

                        updTrxProcSts(new BigDecimal(e2.getKey()), PROC_STS.ERROR, errorCode);
                    }
                }

                commit();
                continue;
            }

            // Creating Transaction ID
            trxId = trxAccessor.getNextTransactionId();

            for (int i = 0; i < instAsnDtlList.size(); i++) {

                WMS_SO_ASN_DTLTMsg wmsSoAsnDtlTMsg = instAsnDtlList.get(i);
                String whCd = wmsSoAsnDtlTMsg.whCd.getValue();
                String wmsSoId = wmsSoAsnDtlTMsg.wmsSoId.getValue();
                String wmsLineNum = NLGC001001.bigDecimalToString(wmsSoAsnDtlTMsg.wmsLineNum.getValue());

                // Insert into WMS_SO_ASN_DTL
                String wmsSoAsnDtlSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_SO_ASN_DTL_SQ).toString();
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.wmsSoAsnDtlId, wmsSoAsnDtlSq);
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.wmsSoAsnHdrId, wmsSoAsnHdrSq);
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.wmsProcFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.asnCustFlg, ZYPConstant.FLG_ON_Y);
                S21FastTBLAccessor.insert(wmsSoAsnDtlTMsg);

                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(wmsSoAsnDtlTMsg.getReturnCode())) {

                    errorCode = NLGM0045E;
                    outputErr(NLGM0045E, new String[] {wmsSoAsnDtlTMsg.getTableName(), "WMS_INBD_TRX" //
                            , NLXCMsgHelper.toListedString("WH_CD", "OTBD_ORD_NUM", "OTBD_ORD_LINE_NUM") //
                            , NLXCMsgHelper.toListedString(whCd, wmsSoId, wmsLineNum) });
                    break;
                }

                // Creating Fixed length Data
                StringBuilder asnOrgDataIf = new StringBuilder();
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.whCd.getValue(), LG_INTFC_WH_CD));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(ZYPCommonFunc.leftPad(wmsSoAsnHdrSq, LG_INTFC_WMS_SO_ASN_HDR_ID, PAD_VAL_ZERO), LG_INTFC_WMS_SO_ASN_HDR_ID));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(ZYPCommonFunc.leftPad(wmsSoAsnDtlSq, LG_INTFC_WMS_SO_ASN_DTL_ID, PAD_VAL_ZERO), LG_INTFC_WMS_SO_ASN_DTL_ID));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(INTFC_REC_TP_ASN, LG_INTFC_REC_TP));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(INTFC_DTL_TP, LG_INTFC_DTL_TP));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.whCd.getValue(), LG_INTFC_WH_CD));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.wmsBolNum.getValue(), LG_INTFC_WMS_BOL_NUM));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.mstrBolNum.getValue(), LG_INTFC_MSTR_BOL_NUM));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.wmsSoId.getValue(), LG_INTFC_WMS_SO_ID));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.wmsLoadId.getValue(), LG_INTFC_WMS_LOAD_ID));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.wmsSoShipId.getValue(), LG_INTFC_WMS_SO_SHIP_ID));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(NLGC001001.confIntfcDtTmTsFmt(wmsSoAsnDtlTMsg.wmsShipDtTmTs.getValue()), LG_INTFC_WMS_SHIP_TM_TS));
                String wmsTotWt = NLGC001001.decimalFormat(wmsSoAsnDtlTMsg.wmsTotWt.getValue(), QTY_FMT);
                asnOrgDataIf.append(NLGC001001.convFixedLgRightAlign(wmsTotWt, LG_INTFC_WMS_TOT_WT));
                String frtChrgCostAmt = "";

                if (!ZYPCommonFunc.hasValue(wmsSoAsnDtlTMsg.frtChrgCostAmt.getValue())) {

                    frtChrgCostAmt = WT_FMT;

                } else if (BigDecimal.ZERO.compareTo(wmsSoAsnDtlTMsg.frtChrgCostAmt.getValue()) < 0) {

                    frtChrgCostAmt = NLGC001001.decimalFormat(wmsSoAsnDtlTMsg.frtChrgCostAmt.getValue().abs(), MINUS_WT_FMT);
                } else {

                    frtChrgCostAmt = NLGC001001.decimalFormat(wmsSoAsnDtlTMsg.frtChrgCostAmt.getValue(), WT_FMT);
                }

                asnOrgDataIf.append(NLGC001001.convFixedLgRightAlign(frtChrgCostAmt, LG_INTFC_FRT_CHRG_COST_AMT));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.carrScacCd.getValue(), LG_INTFC_CARR_SCAC_CD));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.wmsCarrNm.getValue(), LG_INTFC_WMS_CARR_NM));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.carrSvcCd.getValue(), LG_INTFC_CARR_SVC_CD));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.carrProId.getValue(), LG_INTFC_CARR_PRO_ID));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign("", LG_INTFC_REF));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.wmsTrnspTpCd.getValue(), LG_WMS_TRNSP_TP_CD));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.wmsPkgNum.getValue(), LG_WMS_PKG_NUM));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.wmsTrkNum.getValue(), LG_WMS_TRK_NUM));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.wmsUcc128Cd.getValue(), LG_WMS_UCC128_CD));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.prntUcc128Cd.getValue(), LG_PRNT_UCC128_CD));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.wmsMdseCd.getValue(), LG_WMS_MDSE_CD));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.wmsStkStsCd.getValue(), LG_ASN_WMS_STK_STS_CD));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(ZYPCommonFunc.leftPad(wmsLineNum, LG_WMS_LINE_NUM, PAD_VAL_ZERO), LG_WMS_LINE_NUM));
                String wmsOrdQty = NLGC001001.bigDecimalToString(wmsSoAsnDtlTMsg.wmsOrdQty.getValue());
                asnOrgDataIf.append(NLGC001001.convFixedLgRightAlign(ZYPCommonFunc.leftPad(wmsOrdQty, LG_WMS_ORD_QTY, PAD_VAL_ZERO), LG_WMS_ORD_QTY));
                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(wmsSoAsnDtlTMsg.custAcctNum.getValue(), LG_CUST_ACCT_NUM));
                String ezInTime = wmsSoAsnDtlTMsg.ezInTime.getValue();

                if (ZYPCommonFunc.hasValue(ezInTime) && ezInTime.length() > LG_MW_DT_TM_TS) {

                    ezInTime = ezInTime.substring(0, LG_MW_DT_TM_TS);
                }

                asnOrgDataIf.append(NLGC001001.convFixedLgLeftAlign(NLGC001001.confIntfcDtTmTsFmt(ezInTime), LG_EZINTIME));

                // Insert into NLBI0040_01
                NLBI0040_01TMsg nlbi0040TMsg = new NLBI0040_01TMsg();
                ZYPEZDItemValueSetter.setValue(nlbi0040TMsg.interfaceId, INTFC_ID.ASN_SOURCE_MW);
                ZYPEZDItemValueSetter.setValue(nlbi0040TMsg.transactionId, trxId);
                ZYPEZDItemValueSetter.setValue(nlbi0040TMsg.segmentId, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(nlbi0040TMsg.unitId, unitId);
                ZYPEZDItemValueSetter.setValue(nlbi0040TMsg.seqNumber, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(nlbi0040TMsg.asnOrgDataIf, asnOrgDataIf.toString());
                S21FastTBLAccessor.insert(nlbi0040TMsg);

                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(nlbi0040TMsg.getReturnCode())) {

                    errorCode = NLGM0045E;
                    outputErr(NLGM0045E, new String[] {nlbi0040TMsg.getTableName(), "WMS_INBD_TRX" //
                            , NLXCMsgHelper.toListedString("WH_CD", "OTBD_ORD_NUM", "OTBD_ORD_LINE_NUM") //
                            , NLXCMsgHelper.toListedString(whCd, wmsSoId, wmsLineNum) });
                    break;
                }

                unitId = unitId.add(BigDecimal.ONE);
            }

            if (ZYPCommonFunc.hasValue(errorCode)) {

                this.totalErrCount++;
                rollback();

                // Update Process Status Code(ASN)
                updTrxProcSts(new BigDecimal(wmsInbdTrxPk), PROC_STS.ERROR, errorCode);

                for (Map.Entry<String, String> e2 : shipPkMap.entrySet()) {

                    // Update Process Status Code(SHIP)
                    if (wmsInbdTrxPk.equals(e2.getValue())) {

                        updTrxProcSts(new BigDecimal(e2.getKey()), PROC_STS.ERROR, errorCode);
                    }
                }

                commit();

            } else {

                this.normalRecCount++;
                // Update Process Status Code(ASN)
                updTrxProcSts(new BigDecimal(wmsInbdTrxPk), PROC_STS.COMPLEATED, "");

                // Insert into INTERFACE_TRANSACTION
                trxAccessor.createIntegrationRecordForBatch(INTFC_ID.ASN_SOURCE_MW, trxId);
                commit();
            }
        }
    }

    /**
     * ASN data is searched.a WMS_SO_ASN_HDR data and WMS_SO_ASN_DTL
     * data are stored in HashMap and returned.The key of HashMap is
     * WMS_INBD_TRX_PK.
     * @param instAsnHdrMap
     * @param instAsnDtlMap
     * @return shipPkMap
     */
    private HashMap<String, String> searchAsnData(HashMap<String, WMS_SO_ASN_HDRTMsg> instAsnHdrMap, HashMap<String, List<WMS_SO_ASN_DTLTMsg>> instAsnDtlMap) {

        List<String> wmsInbdTrxPks = new ArrayList<String>();
        List<WMS_SO_ASN_DTLTMsg> asnDtlList = new ArrayList<WMS_SO_ASN_DTLTMsg>();
        WMS_SO_ASN_DTLTMsg wmsSoAsnDtlTMsg = null;

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HashMap<String, String> shipPkMap = new HashMap<String, String>();
        HashMap<String, String> skipPkMap = new HashMap<String, String>();

        try {

            // Search for ASN or SHIP data to be processed from WMS_INBD_TRX.
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            HashMap<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("wmsTrgtWhCd", trgtWhCdAry);
            queryParam.put("wmsTaskAsn", WMS_TASK.ASN);
            queryParam.put("wmsTaskShip", WMS_TASK.SHIP);
            queryParam.put("procStsCd", PROC_STS.IN_COMPLETED);
            queryParam.put("procStsCdComp", PROC_STS.COMPLEATED);
            queryParam.put("wmsCarrCdExpt", KEY_VAL_WMS_CARR_CD);
            stmt = this.ssmLLClient.createPreparedStatement("getAsn", queryParam, execParam);
            rs = stmt.executeQuery();
            long cureDtTm = EZDCommonFunc.toTimeMinute(ZYPDateUtil.getCurrentSystemTime(CUR_DT_TM_FMT));

            // Processing status is updated when an error is not
            // canceled, even if it passes over a definite period of time.
            long chkMinIntvl = 0;
            String chkAsnMinIntvl = ZYPCodeDataUtil.getVarCharConstValue(VLD_INTVL_ASN_MIN, glblCmpyCd);

            if (ZYPCommonFunc.hasValue(chkAsnMinIntvl)) {

                chkMinIntvl = Long.parseLong(chkAsnMinIntvl);

            } else {

                chkMinIntvl = DEF_ASN_MIN_INTVL;
            }

            String chkDtTmTs = EZDCommonFunc.toyyyyMMddHHmmss(cureDtTm - (chkMinIntvl * MIN_CALC_MULT_VAL));

            // past 10 minutes from created the transaction
            long stdbyMin = 0;
            String stdbyAsnMin = ZYPCodeDataUtil.getVarCharConstValue(PROC_STDBY_ASN_MIN, glblCmpyCd);

            if (ZYPCommonFunc.hasValue(stdbyAsnMin)) {

                stdbyMin = Long.parseLong(stdbyAsnMin);

            } else {

                stdbyMin = DEF_PROC_STDBY_ASN_MIN;
            }

            String stdbyDtTmTs = EZDCommonFunc.toyyyyMMddHHmmss(cureDtTm - (stdbyMin * MIN_CALC_MULT_VAL));

            while (rs.next()) {

                String shipPk = rs.getString("SHIP_WMS_INBD_TRX_PK");

                if (ZYPCommonFunc.hasValue(shipPk)) {

                    shipPkMap.put(shipPk, rs.getString("WMS_INBD_TRX_PK"));
                }
            }

            rs.beforeFirst();

            while (rs.next()) {

                String wmsInbdTrxPk = rs.getString("WMS_INBD_TRX_PK");
                String asnWmsShipId = rs.getString("ASN_WMS_SHIP_ID");
                String asnBolNum = rs.getString("ASN_BOL_NUM");
                String otbdOrdNum = rs.getString("OTBD_ORD_NUM");
                // String otbdOrdLineNum = rs.getString(COL_OTBD_ORD_LINE_NUM); //ITG#610899 DEL
                String wmsMdseCd = rs.getString("WMS_MDSE_CD");
                String wmsSoMdseCd = rs.getString("WMS_SO_MDSE_CD");
                String wmsCarrCd = rs.getString("WMS_CARR_CD");
                // String wmsOrgHostId = rs.getString(COL_WMS_ORG_HOST_ID); //ITG#610899 DEL
                String carrScacCd = rs.getString("CARR_SCAC_CD");
                String wmsTrnspTpCd = rs.getString("WMS_TRNSP_TP_CD");
                // BigDecimal wmsShipQty = rs.getBigDecimal(COL_WMS_SHIP_QTY); //ITG#610899 DEL
                // BigDecimal totalQty = rs.getBigDecimal(COL_TOTAL_QTY); //ITG#610899 DEL
                String whCd = rs.getString("WH_CD");
                String custAcctNum = rs.getString("CUST_ACCT_NUM");
                String ezintime = rs.getString("EZINTIME").substring(0, LG_MW_DT_TM_TS);

                if (stdbyDtTmTs.compareTo(ezintime) < 0) {

                    // The time for processing has not passed, it does not process.
                    continue;
                }

                if (skipPkMap.containsKey(wmsInbdTrxPk)) {

                    continue;

                } else {

                    // ****************************************
                    // Validation ASN Data
                    // ****************************************
                    String validErrMsg = "";

                    if (!ZYPCommonFunc.hasValue(wmsSoMdseCd)) {

                        // Validate All the SO items exist.
                        outputErr(NLGM0064E, new String[] {wmsInbdTrxPk, asnWmsShipId, asnBolNum });
                        validErrMsg = NLGM0064E;

                    } else if (!ZYPCommonFunc.hasValue(carrScacCd) || !ZYPCommonFunc.hasValue(wmsTrnspTpCd)) {

                        // Validate CARR_SCAC_CD and WMS_CARR_CD exist in WMS_SHIP_VIA.
                        // ITG#591081 ADD START
                        // outputErr(NLGM0065E, new String[] {otbdOrdNum, wmsCarrCd, wmsOrgHostId });
                        validErrMsg = NLGM0065E;
                        List<Map<String, String>> asnCarrErrList = asnCarrErrListMap.get(whCd);

                        if (asnCarrErrList == null) {

                            asnCarrErrList = new ArrayList<Map<String, String>>();
                            asnCarrErrListMap.put(whCd, asnCarrErrList);
                        }

                        Map<String, String> asnDataMap = new HashMap<String, String>();
                        asnDataMap.put(EMAIL_MAP_KEY.WH_CD.toString(), whCd);
                        asnDataMap.put(EMAIL_MAP_KEY.WMS_SHIP_ID.toString(), asnWmsShipId);
                        asnDataMap.put(EMAIL_MAP_KEY.BOL_NUM.toString(), asnBolNum);
                        asnDataMap.put(EMAIL_MAP_KEY.WMS_CARR_CD.toString(), wmsCarrCd);
                        asnCarrErrList.add(asnDataMap);
                        // ITG#591081 ADD END
                        // ITG#598663 UPD START
                        // } else if (totalQty.compareTo(wmsShipQty) != 0) {
                        // // Validate SO download and shipping quantity are in agreement.
                        // outputErr(NLGM0066E, new String[] {otbdOrdNum, otbdOrdLineNum, totalQty.toString(), wmsShipQty.toString()});
                        // validErrMsg = NLGM0066E;
                        // ITG#598663 UPD END
                    }

                    // Processing status is updated when an error is not canceled, even if it passes over a definite period of time.
                    // When an error is not canceled, PROC_STS_CD is updated to ERROR.
                    if (ZYPCommonFunc.hasValue(validErrMsg)) {

                        skipPkMap.put(wmsInbdTrxPk, wmsInbdTrxPk);

                        if (chkDtTmTs.compareTo(ezintime) > 0) {

                            this.totalErrCount++;

                            // Update Process Status Code(ASN)
                            updTrxProcSts(new BigDecimal(wmsInbdTrxPk), PROC_STS.ERROR, validErrMsg);

                            // Update Process Status Code(SHIP)
                            for (Map.Entry<String, String> e : shipPkMap.entrySet()) {

                                if (wmsInbdTrxPk.equals(e.getValue())) {

                                    String shipPk = e.getKey();
                                    updTrxProcSts(new BigDecimal(shipPk), PROC_STS.ERROR, validErrMsg);
                                }
                            }

                            commit();
                            // output validate error message
                            outputErr(NLGM0068I, new String[] {wmsInbdTrxPk, otbdOrdNum });
                        }

                        continue;
                    }
                }

                // Extraction of WMS_SO_ASN_DTL Data
                wmsSoAsnDtlTMsg = new WMS_SO_ASN_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.glblCmpyCd, glblCmpyCd);

                // Setting of WMS_BOL_NUM or WMS_TOT_WT or WMS_TRK_NUM
                String wmsBolNum = "";
                String bolNum = rs.getString("BOL_NUM");
                String proNum = rs.getString("PRO_NUM");
                String tmsTrkNum = rs.getString("TMS_TRK_NUM");
                BigDecimal wmsTotWt = NLGC001001.stringToBigDecimal(rs.getString("WMS_TOT_WT"));
                BigDecimal sumWmsTotWt = NLGC001001.stringToBigDecimal(rs.getString("SUM_WMS_TOT_WT"));

                if (!ZYPCommonFunc.hasValue(proNum)) {

                    proNum = tmsTrkNum;
                }

                if (WMS_TRNSP_TP.PCL.equals(wmsTrnspTpCd)) {

                    if (CON_PRO_NUM_NONE.equals(proNum)) {

                        wmsBolNum = "";

                    } else {

                        wmsBolNum = proNum;
                    }

                } else {

                    wmsBolNum = bolNum;
                    wmsTotWt = sumWmsTotWt;
                }

                // Setting of CARR_PRO_ID
                String carrProId = "";

                if (!CON_PRO_NUM_NONE.equals(proNum)) {

                    carrProId = proNum;
                }

                // Setting of WMS_UCC_128_CD or PRINT_UCC_128_CD
                String wmsUcc128Cd = "";
                String prntUcc128Cd = "";
                String wmsCntnrId = rs.getString("WMS_CNTNR_ID");
                String wmsOutCntnrNum = rs.getString("WMS_OUT_CNTNR_NUM");
                String pfxWmsCntnrId = wmsCntnrId.substring(LG_CUT_WMS_CNTNR_FROM, LG_CUT_WMS_CNTNR_TO);
                String pfxwmsOutCntnrNum = wmsOutCntnrNum.substring(LG_CUT_WMS_CNTNR_FROM, LG_CUT_WMS_CNTNR_TO);

                if (wmsCntnrId.equals(wmsOutCntnrNum)) {

                    if (WMS_CNTNR_TOP_VAL.equals(pfxWmsCntnrId)) {

                        wmsUcc128Cd = wmsCntnrId;
                    }

                } else if (pfxWmsCntnrId.equals(WMS_CNTNR_LIKE000)) {

                    wmsUcc128Cd = wmsCntnrId;
                }

                if (wmsCntnrId.equals(wmsOutCntnrNum)) {

                    if (!WMS_CNTNR_TOP_VAL.equals(pfxWmsCntnrId)) {

                        prntUcc128Cd = wmsCntnrId;
                    }

                } else if (pfxwmsOutCntnrNum.equals(WMS_CNTNR_LIKE001)) {

                    prntUcc128Cd = wmsOutCntnrNum;

                } else if (pfxWmsCntnrId.equals(WMS_CNTNR_LIKE001)) {

                    prntUcc128Cd = wmsCntnrId;
                }

                // Setting of CONSL_SO_ID or CONSL_LINE_NUM
                String wmsSoId = "";
                BigDecimal wmsLineNum = null;
                String conslSoId = "";
                String conslLineNum = "";
                String origSoId = rs.getString("ORIG_SO_ID");

                if (ZYPCommonFunc.hasValue(origSoId)) {

                    wmsSoId = origSoId;
                    wmsLineNum = rs.getBigDecimal("ORIG_LINE_NUM");
                    conslSoId = rs.getString("WMS_SO_ID");
                    conslLineNum = rs.getString("WMS_LINE_NUM");

                } else {

                    wmsSoId = rs.getString("OTBD_ORD_NUM");
                    wmsLineNum = rs.getBigDecimal("OTBD_ORD_LINE_NUM");
                }

                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.wmsBolNum, wmsBolNum);
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.whCd, whCd);
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.wmsSoId, wmsSoId);
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.wmsLoadId, whCd.concat(bolNum));
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.wmsSoShipId, rs.getString("WMS_SO_SHIP_ID"));
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.wmsShipDtTmTs, rs.getString("WMS_SHIP_DT_TM_TS"));
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.wmsTotWt, NLXC014001.nullToZero(wmsTotWt));
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.carrScacCd, rs.getString("CARR_SCAC_CD"));
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.carrSvcCd, rs.getString("CARR_SCAC_CD"));
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.carrProId, carrProId);
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.wmsTrkNum, tmsTrkNum);
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.wmsUcc128Cd, wmsUcc128Cd);
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.prntUcc128Cd, prntUcc128Cd);
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.wmsMdseCd, wmsMdseCd);
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.wmsStkStsCd, rs.getString("WMS_STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.wmsOrdQty, NLXC014001.nullToZero(rs.getBigDecimal("WMS_ORD_QTY")));
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.wmsLineNum, NLXC014001.nullToZero(wmsLineNum));
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.custAcctNum, custAcctNum);
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.conslSoId, conslSoId);
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.conslLineNum, conslLineNum);
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.rowPosnNum, rs.getString("ROW_POSN_NUM"));
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.rowSqNum, NLGC001001.stringToBigDecimal(rs.getString("ROW_SQ_NUM")));
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.wmsTrnspTpCd, rs.getString("WMS_TRNSP_TP_CD"));
                ZYPEZDItemValueSetter.setValue(wmsSoAsnDtlTMsg.wmsCarrNm, rs.getString("WMS_CARR_NM"));

                asnDtlList.add(wmsSoAsnDtlTMsg);
                wmsInbdTrxPks.add(wmsInbdTrxPk);
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }

        // Only normal data is acquired from the acquired data.
        List<WMS_SO_ASN_DTLTMsg> normalAsnDtl = new ArrayList<WMS_SO_ASN_DTLTMsg>();
        HashMap<String, String> normalPkMap = new HashMap<String, String>();

        for (int i = 0; i < asnDtlList.size(); i++) {

            wmsSoAsnDtlTMsg = asnDtlList.get(i);
            String whCd = wmsSoAsnDtlTMsg.whCd.getValue();
            String wmsInbdTrxPk = wmsInbdTrxPks.get(i);

            if (!normalPkMap.containsKey(wmsInbdTrxPk)) {

                normalAsnDtl = new ArrayList<WMS_SO_ASN_DTLTMsg>();
                // Extraction of WMS_SO_ASN_HDR Data
                WMS_SO_ASN_HDRTMsg wmsSoAsnHdrTMsg = new WMS_SO_ASN_HDRTMsg();
                ZYPEZDItemValueSetter.setValue(wmsSoAsnHdrTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(wmsSoAsnHdrTMsg.whCd, whCd);
                ZYPEZDItemValueSetter.setValue(wmsSoAsnHdrTMsg.wmsProcFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(wmsSoAsnHdrTMsg.asnCustFlg, ZYPConstant.FLG_OFF_N);
                instAsnHdrMap.put(wmsInbdTrxPk, wmsSoAsnHdrTMsg);
                instAsnDtlMap.put(wmsInbdTrxPk, normalAsnDtl);
                normalPkMap.put(wmsInbdTrxPk, wmsInbdTrxPk);
            }

            normalAsnDtl.add(wmsSoAsnDtlTMsg);
        }

        return shipPkMap;
    }

    /**
     * Error information is updated to PROC_STS_CD of WMS_INBD_TRX.
     * @param oshpTrxPk BigDecimal
     * @param shipList ArrayList<Map<String, Object>>
     * @param ochgList ArrayList<Map<String, Object>>
     * @param errorCode String
     */
    private void updErrorTrxProcStsSoConf(BigDecimal oshpTrxPk, ArrayList<Map<String, Object>> shipList, ArrayList<Map<String, Object>> ochgList, String errorCode) {

        this.totalErrCount++;
        rollback();

        // Update Process Status Code(OSHP)
        updTrxProcSts(oshpTrxPk, PROC_STS.ERROR, errorCode);

        // Update Process Status Code(SHIP)
        for (Map<String, Object> shipMap : shipList) {

            BigDecimal oshpTrxPkForShip = (BigDecimal) shipMap.get("OSHP_WMS_INBD_TRX_PK");

            if (oshpTrxPk.compareTo(oshpTrxPkForShip) == 0) {

                updTrxProcSts((BigDecimal) shipMap.get("WMS_INBD_TRX_PK"), PROC_STS.ERROR, errorCode);
            }
        }

        // Update Process Status Code(OCHG)
        for (Map<String, Object> ochgMap : ochgList) {

            BigDecimal oshpTrxPkForOchg = (BigDecimal) ochgMap.get("OSHP_WMS_INBD_TRX_PK");

            if (oshpTrxPk.compareTo(oshpTrxPkForOchg) == 0) {

                updTrxProcSts((BigDecimal) ochgMap.get("WMS_INBD_TRX_PK"), PROC_STS.ERROR, errorCode);
            }
        }

        commit();
    }

    /**
     * update WMS_INBD_TRX Table of PROC_STS_CD and ERR_MSG_CD
     * @param wmsInbdTrxPk BigDecimal
     * @param procStsCd String
     * @param errMsgCd String
     */
    private void updTrxProcSts(BigDecimal wmsInbdTrxPk, String procStsCd, String errMsgCd) {

        WMS_INBD_TRXTMsg wmsInbdTrxTMsg = new WMS_INBD_TRXTMsg();
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsInbdTrxPk, wmsInbdTrxPk);
        wmsInbdTrxTMsg = (WMS_INBD_TRXTMsg) EZDTBLAccessor.findByKeyForUpdate(wmsInbdTrxTMsg);

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.procStsCd, procStsCd);

        if (ZYPCommonFunc.hasValue(errMsgCd)) {

            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.errMsgCd, errMsgCd);

        } else {

            wmsInbdTrxTMsg.errMsgCd.clear();
        }

        EZDTBLAccessor.updateSelectionField(wmsInbdTrxTMsg, new String[] {KEY_PROC_STS_CD, KEY_ERR_MSG_CD });
    }

    /**
     * Update WMS Inbound SO Header Close Date.
     * @param wmsInbdTrxOshpMap Map<String, Object>
     * @param soNum String
     * @return String
     */
    private String updWmsInbdSoHdrClsDt(Map<String, Object> wmsInbdTrxOshpMap, String soNum) {

        if (wmsInbdTrxOshpMap != null && !wmsInbdTrxOshpMap.isEmpty()) {

            String wmsTaskCd = (String) wmsInbdTrxOshpMap.get("WMS_TASK_CD");
            String whCd = (String) wmsInbdTrxOshpMap.get("WH_CD");
            String otbdOrdNum = (String) wmsInbdTrxOshpMap.get("OTBD_ORD_NUM");
            String otbdOrdTpCd = (String) wmsInbdTrxOshpMap.get("OTBD_ORD_TP_CD");
            String wmsTrxDtTmTs = (String) wmsInbdTrxOshpMap.get("WMS_TRX_DT_TM_TS");
            String curDt = ZYPDateUtil.getCurrentSystemTime(CUR_DT_TM_FMT);

            if (!ZYPCommonFunc.hasValue(wmsTrxDtTmTs)) {

                wmsTrxDtTmTs = curDt;
            }

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("whCd", whCd);

            // QC#17981 Mod.
            // if (!WMS_ORD_TP.INBOUND_ITEM_CHANGE.equals(otbdOrdTpCd) && !WMS_ORD_TP.INBOUND_STOCK_STATUS_CHANGE.equals(otbdOrdTpCd)) {
            if (!Arrays.asList(updateGetWmsInbdSoHdrWmsOrdTp).contains(otbdOrdTpCd)) {

                // For Order Type item Change(R) or stock status change(S), to update the cancellation date or close date, of WMS_INBD_SO_HDR.
                // If it has been canceled or already closed, it is an error.
                String updColNm = KEY_WMS_CANC_DT_TM_TS;

                if (WMS_TASK.OSHP.equals(wmsTaskCd)) {

                    updColNm = KEY_SHIP_DT_TM_TS;
                }

                queryParam.put("wmsSoId", otbdOrdNum);
                List<WMS_INBD_SO_HDRTMsg> soHdrList = this.ssmBatchClient.queryObjectList("getWmsInbdSoHdr", queryParam);

                if (!soHdrList.isEmpty()) {

                    WMS_INBD_SO_HDRTMsg wmsInbdSoHdrTMsg = soHdrList.get(0);

                    if (!ZYPCommonFunc.hasValue(wmsInbdSoHdrTMsg.wmsCancDtTmTs.getValue()) && !ZYPCommonFunc.hasValue(wmsInbdSoHdrTMsg.shipDtTmTs.getValue())) {

                        ZYPEZDItemValueSetter.setValue(wmsInbdSoHdrTMsg.wmsCancDtTmTs, curDt);
                        ZYPEZDItemValueSetter.setValue(wmsInbdSoHdrTMsg.shipDtTmTs, wmsTrxDtTmTs);
                        EZDTBLAccessor.updateSelectionField(wmsInbdSoHdrTMsg, new String[] {updColNm });

                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdSoHdrTMsg.getReturnCode())) {

                            outputErr(NLGM0046E, new String[] {wmsInbdSoHdrTMsg.getTableName() //
                                    , NLXCMsgHelper.toListedString("GLBL_CMPY_CD", "WH_CD", "WMS_SQ_NUM") //
                                    , NLXCMsgHelper.toListedString(wmsInbdSoHdrTMsg.glblCmpyCd.getValue() //
                                            , wmsInbdSoHdrTMsg.whCd.getValue() //
                                            , wmsInbdSoHdrTMsg.wmsSqNum.getValue()) });
                            return NLGM0046E;
                        }

                    } else {

                        if (ZYPCommonFunc.hasValue(wmsInbdSoHdrTMsg.wmsCancDtTmTs.getValue())) {

                            outputErr(NLGM0053E, new String[] {otbdOrdNum, wmsInbdSoHdrTMsg.getTableName(), "WMS_CANC_DT_TM_TS" //
                                    , wmsInbdSoHdrTMsg.wmsCancDtTmTs.getValue() });
                        } else {

                            outputErr(NLGM0053E, new String[] {otbdOrdNum, wmsInbdSoHdrTMsg.getTableName(), "SHIP_DT_TM_TS" //
                                    , wmsInbdSoHdrTMsg.shipDtTmTs.getValue() });
                        }

                        return NLGM0053E;
                    }
                }
            }

        } else {

            // Check SO Detail Status
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("soNum", soNum);
            queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
            queryParam.put("flgN", ZYPConstant.FLG_OFF_N);
            List<Map<String, String>> soDtlStsMapList = (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getShpgOrdDtlSts", queryParam);

            if (soDtlStsMapList != null && !soDtlStsMapList.isEmpty()) {

                boolean isShipped = false;

                for (Map<String, String> soDtlStsMap : soDtlStsMapList) {

                    String soPrintFlg = (String) soDtlStsMap.get("SO_PRINT_FLG");
                    String shipFlg = (String) soDtlStsMap.get("SHIP_FLG");

                    if (ZYPConstant.FLG_ON_Y.equals(soPrintFlg) && ZYPConstant.FLG_ON_Y.equals(shipFlg)) {

                        isShipped = true;
                        break;
                    }
                }

                // Get WMS_INBD_TRX.WMS_INBD_SO_HDR
                String wmsTrxDtTmTs = null;

                queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", glblCmpyCd);
                queryParam.put("whCd", (String) soDtlStsMapList.get(0).get("WMS_WH_CD"));
                queryParam.put("wmsSoId", soNum);

                if (isShipped) {

                    queryParam.put("wmsTaskOshp", WMS_TASK.SHIP);

                } else {

                    queryParam.put("wmsTaskOsc", WMS_TASK.OSC);
                    queryParam.put("wmsOrdStsCan", WMS_ORD_STS.CAN);
                }

                List<String> wmsTrxDtTmTsMapList = (List<String>) this.ssmBatchClient.queryObjectList("getWmsTrxDtTmTs", queryParam);

                if (wmsTrxDtTmTsMapList != null && !wmsTrxDtTmTsMapList.isEmpty()) {

                    wmsTrxDtTmTs = wmsTrxDtTmTsMapList.get(0);

                } else {

                    wmsTrxDtTmTs = this.getSalesDateTm();
                }

                // Get WMS_INBD_SO_HDR
                queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", glblCmpyCd);
                queryParam.put("whCd", (String) soDtlStsMapList.get(0).get("WMS_WH_CD"));
                queryParam.put("wmsSoId", soNum);
                List<WMS_INBD_SO_HDRTMsg> wmsInbdSoHdrList = (List<WMS_INBD_SO_HDRTMsg>) this.ssmBatchClient.queryObjectList("getWmsInbdSoHdr", queryParam);

                if (wmsInbdSoHdrList != null && !wmsInbdSoHdrList.isEmpty()) {

                    for (WMS_INBD_SO_HDRTMsg wmsInbdSoHdrTMsg : wmsInbdSoHdrList) {

                        wmsInbdSoHdrTMsg = (WMS_INBD_SO_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(wmsInbdSoHdrTMsg);

                        if (wmsInbdSoHdrTMsg == null) {

                            S21InfoLogOutput.println(NLBM1063E);
                            S21InfoLogOutput.println("UpdateInboudSoData SO_NUM:" + soNum);
                            throw new S21AbendException(NLBM1063E);
                        }

                        // Update WMS_INBD_SO_HDR
                        if (isShipped) {

                            ZYPEZDItemValueSetter.setValue(wmsInbdSoHdrTMsg.shipDtTmTs, wmsTrxDtTmTs);

                        } else {

                            ZYPEZDItemValueSetter.setValue(wmsInbdSoHdrTMsg.wmsCancDtTmTs, wmsTrxDtTmTs);
                        }

                        EZDTBLAccessor.update(wmsInbdSoHdrTMsg);

                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdSoHdrTMsg.getReturnCode())) {

                            S21InfoLogOutput.println(NLBM1064E);
                            S21InfoLogOutput.println("UpdateInboudSoData SO_NUM:" + soNum);
                            throw new S21AbendException(NLBM1064E);
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * Add Error ID and Message
     * @param msgId String
     */
    private void outputErr(String msgId) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId));
        errMsgList.add(mailParam);
        S21InfoLogOutput.println(msgId);
    }

    /**
     * Add Error ID and Message
     * @param msgId String
     * @param msgParam String[]
     */
    private void outputErr(String msgId, String[] msgParam) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        errMsgList.add(mailParam);
        S21InfoLogOutput.println(msgId, msgParam);
    }

    /**
     * QC#53009. Add Error ID and Message.
     * @param msgId String
     * @param soNum String
     */
    private void outputErrNagativeQty(String msgId, String soNum) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage("NLZM2522E", new String[]{soNum}));
        errMsgList.add(mailParam);
        S21InfoLogOutput.println(msgId);
    }

    // ITG#591081 ADD START
    /**
     * Send error email notification about missing carrier code
     * mapping for SO Confirmation.
     */
    private void sendSoConfErrMail() {

        for (String whCd : soConfCarrErrListMap.keySet()) {

            List<Map<String, Object>> soConfCarrErrList = soConfCarrErrListMap.get(whCd);
            S21Mail mail = new S21Mail(glblCmpyCd);

            // Get From Mail Address
            S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
            groupFrom.setMailKey1(MAIL_KEY_FROM);
            List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
            S21MailAddress fromAddress = addrFromList.get(0);
            mail.setFromAddress(fromAddress);

            // Get To Mail Address
            S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
            groupTo.setMailKey1(whCd);
            List<S21MailAddress> addrToList = groupTo.getMailAddress();

            if (addrToList.isEmpty()) {

                throw new S21AbendException(NPAM0063E, new String[] {MAIL_GROUP_ID_TO, whCd });
            }

            mail.setToAddressList(addrToList);

            // Get Template
            S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_SO_CONF_ERR);

            if (!ZYPCommonFunc.hasValue(template.getSubject())) {

                throw new S21AbendException(NPAM0064E, new String[] {MAIL_TEMPLATE_ID_SO_CONF_ERR });
            }

            StringBuilder sb = new StringBuilder();
            sb.append(DTL_MSG_HDR_SO_CONF);

            for (Map<String, Object> oshpMap : soConfCarrErrList) {

                sb.append(LINE_FEED_CODE);
                sb.append((oshpMap.get("WH_CD") + BLANK_100).substring(0, WIDTH_WH_CD));
                sb.append(BLANK_2 + (oshpMap.get("OTBD_ORD_NUM") + BLANK_100).substring(0, WIDTH_SO_NUM));
                sb.append(BLANK_2 + (oshpMap.get("WMS_CARR_CD") + BLANK_100).substring(0, WIDTH_WMS_CARR_CD));
            }

            template.setTemplateParameter(MT_WH_CD, whCd);
            template.setTemplateParameter(MT_DTL_MSG, sb.toString());
            mail.setMailTemplate(template);
            mail.postMail();
            commit();
        }
    }

    /**
     * Send error email notification about missing carrier code
     * mapping for ASN.
     */
    private void sendAsnErrMail() {

        for (String whCd : asnCarrErrListMap.keySet()) {

            List<Map<String, String>> asnCarrErrList = asnCarrErrListMap.get(whCd);
            S21Mail mail = new S21Mail(glblCmpyCd);

            // Get From Mail Address
            S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
            groupFrom.setMailKey1(MAIL_KEY_FROM);
            List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
            S21MailAddress fromAddress = addrFromList.get(0);
            mail.setFromAddress(fromAddress);

            // Get To Mail Address
            S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
            groupTo.setMailKey1(whCd);
            List<S21MailAddress> addrToList = groupTo.getMailAddress();

            if (addrToList.isEmpty()) {

                throw new S21AbendException(NPAM0063E, new String[] {MAIL_GROUP_ID_TO, whCd });
            }

            mail.setToAddressList(addrToList);

            // Get Template
            S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_ASN_ERR);

            if (!ZYPCommonFunc.hasValue(template.getSubject())) {

                throw new S21AbendException(NPAM0064E, new String[] {MAIL_TEMPLATE_ID_ASN_ERR });
            }

            StringBuilder sb = new StringBuilder();
            sb.append(DTL_MSG_HDR_ASN);

            for (Map<String, String> asnDataMap : asnCarrErrList) {

                sb.append(LINE_FEED_CODE);
                sb.append((asnDataMap.get(EMAIL_MAP_KEY.WH_CD.toString()) + BLANK_100).substring(0, WIDTH_WH_CD));
                sb.append(BLANK_2 + (asnDataMap.get(EMAIL_MAP_KEY.WMS_SHIP_ID.toString()) + BLANK_100).substring(0, WIDTH_WMS_SHIP_ID));
                sb.append(BLANK_2 + (asnDataMap.get(EMAIL_MAP_KEY.BOL_NUM.toString()) + BLANK_100).substring(0, WIDTH_BOL_NUM));
                sb.append(BLANK_2 + (asnDataMap.get(EMAIL_MAP_KEY.WMS_CARR_CD.toString()) + BLANK_100).substring(0, WIDTH_WMS_CARR_CD));
            }

            template.setTemplateParameter(MT_WH_CD, whCd);
            template.setTemplateParameter(MT_DTL_MSG, sb.toString());
            mail.setMailTemplate(template);
            mail.postMail();
            commit();
        }
    }
    // ITG#591081 ADD END

    /**
     * Get CAL_RELN
     * @param calSubTpCd CAL_SUB_TP_CD
     * @param calMultCd CAL_MULT_CD
     * @return CAL_RELNTMsg
     */
    private CAL_RELNTMsg getCalReln(String calSubTpCd, String calMultCd) {

        // Create key of cache map as search condition
        String searchCondition = this.glblCmpyCd + calSubTpCd + calMultCd;

        // Get result by the key from the map
        CAL_RELNTMsg calReln = this.calRelnMap.get(searchCondition);

        if (calReln == null) {

            // If not stored in the map, search on the condition
            calReln = new CAL_RELNTMsg();
            calReln.glblCmpyCd.setValue(this.glblCmpyCd);
            calReln.calSubTpCd.setValue(calSubTpCd);
            calReln.calMultCd.setValue(calMultCd);
            calReln = (CAL_RELNTMsg) EZDTBLAccessor.findByKey(calReln);

            // Store the condition and the result in the map
            this.calRelnMap.put(searchCondition, calReln);
        }

        return calReln;
    }

    /**
     * Get Tech Master
     * @param techTocCd String
     * @param salesDate String
     * @return Map<String, Object>
     */
    private Map<String, Object> getTechMstr(String techTocCd, String salesDate) {

        HashMap<String, Object> queryParam = new HashMap<String, Object>();
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("techTocCd", techTocCd);
        queryParam.put("salesDate", salesDate);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getTechMstr", queryParam, execParam);
    }

    /**
     * Check PI Activity
     * @param soNum String
     * @return boolean (true : OK, false : NG)
     */
    private boolean chkPiActivity(String soNum) {

        NLZC060001PMsg pMsg = new NLZC060001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.soNum, soNum);

        NLZC060001 nlzc060001 = new NLZC060001();
        nlzc060001.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (NLZC060001Constant.RETURN_CODE_ERROR.equals(pMsg.xxRsltStsCd.getValue())) {

            return false;
        }

        return true;
    }

    // QC#29979 Update Method.
    /**
     * getTplCarrCd
     * @param wmsCarrCd ,{@code null} OK.
     * @return Map<String, Object>
     */
    // START 2019/02/06 T.Ogura [QC#30182,MOD]
//    private Map<String, Object> getTplCarrSvc(String wmsCarrCd, String wmsWhCd) {
    private Map<String, Object> getTplCarrSvc(String wmsCarrCd) {
    // END   2019/02/06 T.Ogura [QC#30182,MOD]

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("wmsCarrCd", wmsCarrCd);
        // QC#30522
        queryParam.put("whGpCd", this.whGpCd);
//        queryParam.put("wmsWhCd", wmsWhCd);    // 2019/02/06 T.Ogura [QC#30182,DEL]

        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getTplCarrSvc", queryParam);

        return result;
    }

    // QC#21913 T.Hakodate ADD START
    // createShpgOrdProNumList
    private void createShpgOrdProNumList(Map<String, Object> wmsInbdTrxShipMap) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("wmsTaskCd", (String) wmsInbdTrxShipMap.get("WMS_TASK_CD"));
        queryParam.put("wmsShipId", (String) wmsInbdTrxShipMap.get("WMS_SHIP_ID"));
        queryParam.put("intfcTrxId", (String) wmsInbdTrxShipMap.get("INTFC_TRX_ID"));

        List<Map<String, Object>> proNumList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getProNumList", queryParam);

        ArrayList<String> processedProNumList = new ArrayList<String>();

        // 2019/11/28 QC#54812 ADD START
        String mstrProNum = (String) wmsInbdTrxShipMap.get("TMS_TRK_NUM");
        // 2019/11/28 QC#54812 ADD END

        for (Map<String, Object> proNum : proNumList) {

            if (processedProNumList.contains((String) proNum.get("TMS_TRK_NUM"))) {
                continue;
            }

            SHPG_ORD_PRO_NUM_LISTTMsg shpgOrdProNumListTmsg = new SHPG_ORD_PRO_NUM_LISTTMsg();

            ZYPEZDItemValueSetter.setValue(shpgOrdProNumListTmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(shpgOrdProNumListTmsg.sceOrdTpCd, (String) proNum.get("SCE_ORD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(shpgOrdProNumListTmsg.inbdOtbdCd, INBD_OTBD.OUTBOUND);
            ZYPEZDItemValueSetter.setValue(shpgOrdProNumListTmsg.wmsCntnrId, (String) proNum.get("WMS_CNTNR_ID"));
            ZYPEZDItemValueSetter.setValue(shpgOrdProNumListTmsg.wmsCarrCd, (String) proNum.get("WMS_CARR_CD"));
            ZYPEZDItemValueSetter.setValue(shpgOrdProNumListTmsg.wmsFrtChrgAmt, (BigDecimal) proNum.get("WMS_FRT_CHRG_AMT"));
            ZYPEZDItemValueSetter.setValue(shpgOrdProNumListTmsg.shpgOrdProNumListPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SHPG_ORD_PRO_NUM_LIST_SQ));
            // 2019/11/28 QC#54812 MOD START
//            ZYPEZDItemValueSetter.setValue(shpgOrdProNumListTmsg.mstrProNum, (String) wmsInbdTrxShipMap.get("TMS_TRK_NUM"));
            if (!ZYPCommonFunc.hasValue(mstrProNum)) {
                ZYPEZDItemValueSetter.setValue(shpgOrdProNumListTmsg.mstrProNum, (String) proNum.get("TMS_TRK_NUM"));
            } else {
                ZYPEZDItemValueSetter.setValue(shpgOrdProNumListTmsg.mstrProNum, mstrProNum);
            }
            // 2019/11/28 QC#54812 MOD END
            ZYPEZDItemValueSetter.setValue(shpgOrdProNumListTmsg.proNum, (String) proNum.get("TMS_TRK_NUM"));
            ZYPEZDItemValueSetter.setValue(shpgOrdProNumListTmsg.trxHdrNum, (String) wmsInbdTrxShipMap.get("OTBD_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(shpgOrdProNumListTmsg.proCratDtTmTs, (String) ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss"));
            ZYPEZDItemValueSetter.setValue(shpgOrdProNumListTmsg.proSendFlg, ZYPConstant.FLG_ON_Y);
            // START 2020/11/20 A.Marte [QC#57659, ADD]
            ZYPEZDItemValueSetter.setValue(shpgOrdProNumListTmsg.carrEncTrkUrl, (String) wmsInbdTrxShipMap.get("CARR_ENC_TRK_URL"));
            // END 2020/11/20 A.Marte [QC#57659, ADD]

            EZDTBLAccessor.insert(shpgOrdProNumListTmsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdProNumListTmsg.getReturnCode())) {

                S21InfoLogOutput.println(NPAM1499E, new String[] {shpgOrdProNumListTmsg.getTableName(), (String) wmsInbdTrxShipMap.get("OTBD_ORD_NUM") });
                throw new S21AbendException(NPAM1499E, new String[] {shpgOrdProNumListTmsg.getTableName(), (String) wmsInbdTrxShipMap.get("OTBD_ORD_NUM") });
            }
            processedProNumList.add((String) proNum.get("TMS_TRK_NUM"));
        }
    }
    // QC#21913 T.Hakodate ADD END

    /**
     * isRetry
     */
    // QC#52104 Add Start
    private boolean isRetry(String intfcProcStsCd) {
        String[] nonRetryErrMsgLst = null;

        
        String nonRetryErrMsgVarChar = ZYPCodeDataUtil.getVarCharConstValue(NLGB0120_NO_RETRY_ERRMSG_LST, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(nonRetryErrMsgVarChar)) {
            return true;
        }
        nonRetryErrMsgLst = nonRetryErrMsgVarChar.split(",");
        for (int i=0; i<nonRetryErrMsgLst.length; i++) {
            if (nonRetryErrMsgLst[i].equals(intfcProcStsCd)) {
                return false;
            }
        }
        return true;
    }
    // QC#52104 Add End

    // QC#50971 Add
    private List<BigDecimal> getSvcMachMstrPk(BigDecimal svcConfigMstrPk, String mdseCd, String serNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("svcConfigMstrPk", svcConfigMstrPk);
        queryParam.put("mdseCd", mdseCd);
        if (ZYPCommonFunc.hasValue(serNum)) {
            queryParam.put("serNum", serNum);
        }

        List<BigDecimal> result = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getSvcMachMstrPk", queryParam);

        return result;
    }

    // QC#53872 Add
    private boolean isCntSvcMachMstrPk(String mdseCd, String serNum, String stkStsCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("stkStsCd", stkStsCd);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("serNum", serNum);

        BigDecimal ret = (BigDecimal) this.ssmBatchClient.queryObject("cntSvcMachMstrPk", queryParam);

        if (ret == null) {
            return false;
        }

        if (BigDecimal.ZERO.compareTo(ret) == 0) {
            return false;
        }

        return true;
    }
    // QC#53872 End

    // QC#54135 Add Start
    /**
     * isSkipShpgSts
     * @param glblCmpyCd
     * @param otbdOrdNum
     * @param otbdOrdLineNum
     * @param shpgStsPktSkip
     * @return
     */
    // QC#54135 Add End
    private boolean isSkipShpgSts(String glblCmpyCd, String otbdOrdNum, String otbdOrdLineNum, String[] shpgStsPktSkip) {
        if (shpgStsPktSkip == null) {
            return false;
        }
        // 2022/04/18 MOD QC#59763 START
        String shpgStsCd;
        if (otbdOrdLineNum.equals("0") || otbdOrdLineNum == null) {
            SHPG_ORDTMsg soInMsg = new SHPG_ORDTMsg();
            ZYPEZDItemValueSetter.setValue(soInMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(soInMsg.soNum, otbdOrdNum);
            SHPG_ORDTMsg soOutMsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKey(soInMsg);
            if (soOutMsg != null) {
                if (soOutMsg.shpgStsCd != null && shpgStsPktSkip != null) {
                    shpgStsCd = soOutMsg.shpgStsCd.getValue();
                    for (String cnst : shpgStsPktSkip) {
                        if (cnst.equals(shpgStsCd)) {
                            return true;
                        }
                    }
                }
            }
        }
        // 2022/04/18 MOD QC#59763 END
        SHPG_ORD_DTLTMsg soDtlInMsg = new SHPG_ORD_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(soDtlInMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(soDtlInMsg.soNum, otbdOrdNum);
        ZYPEZDItemValueSetter.setValue(soDtlInMsg.soSlpNum, ZYPCommonFunc.leftPad(otbdOrdLineNum, 3, "0"));
        SHPG_ORD_DTLTMsg soDtlOutMsg = (SHPG_ORD_DTLTMsg) EZDTBLAccessor.findByKey(soDtlInMsg);
        if (soDtlOutMsg != null) {
            if (soDtlOutMsg.shpgStsCd != null && shpgStsPktSkip != null) {
                shpgStsCd = soDtlOutMsg.shpgStsCd.getValue();
                for (String cnst : shpgStsPktSkip) {
                    if(cnst.equals(shpgStsCd)) {
                        return true;
                    }
                }
            }
        }
        return false;

    }
    // QC#54910 Add Start
    private boolean isUs(String soNum) {
        if(!ZYPCommonFunc.hasValue(soNum)){
            return false;
        }
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("soNum", soNum);
        queryParam.put("soCustDataTpCd", SO_CUST_DATA_TP.SHIP_TO);

        String ctryCd = (String) this.ssmBatchClient.queryObject("getCtryCd", queryParam);

        if (ZYPCommonFunc.hasValue(ctryCd) && CTRY.UNITED_STATES_OF_AMERICA.equals(ctryCd)) {
            return true;
        } else {
            return false;
        }
    }
    // QC#54910 Add End

    // QC#55713 Add Start
    private boolean needsAlrtMail(String glblCmpyCd, BigDecimal wmsInbdTrxPk) {
        WMS_INBD_TRXTMsg inTmsg = new WMS_INBD_TRXTMsg();
        ZYPEZDItemValueSetter.setValue(inTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTmsg.wmsInbdTrxPk, wmsInbdTrxPk);
        WMS_INBD_TRXTMsg outTmsg = (WMS_INBD_TRXTMsg) EZDTBLAccessor.findByKey(inTmsg);
        if (outTmsg != null && outTmsg.errMsgCd != null) {
            if(NLGM0087E.equals(outTmsg.errMsgCd.getValue())) {
                return false;

            }
        }
        return true;
    }
    // QC#55713 Add End

    // START 2021/08/27 R.Azucena[QC#59036, ADD]
    private String getSvcMachMstrCurLocNum(String mdseCd, String serNum, String stkStsCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("stkStsCd", stkStsCd);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("serNum", serNum);
        return (String) this.ssmBatchClient.queryObject("getSvcMachMstrCurLocNum", queryParam);
    }

    private BigDecimal getWmsInbdTrxOtbdOrdLineNum(String wmsInbdTrxPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("wmsInbdTrxPk", wmsInbdTrxPk);
        return (BigDecimal) this.ssmBatchClient.queryObject("getWmsInbdTrxOtbdOrdLineNum", queryParam);
    }

    private void updShipTrxProcStsForWhErr(ArrayList<BigDecimal> wmsInbdTrxLineNumWhErrList, BigDecimal wmsInbdTrxShipPk, boolean isConfigFlgPre, String errorCode) {
        if (isConfigFlgPre) {
            updTrxProcSts(wmsInbdTrxShipPk, PROC_STS.COMPLEATED, errorCode);
            this.totalErrCount++;
        } else {
            BigDecimal wmsInbdTrxOtbdOrdLineNum = getWmsInbdTrxOtbdOrdLineNum(wmsInbdTrxShipPk.toString());
            if (wmsInbdTrxLineNumWhErrList.contains(wmsInbdTrxOtbdOrdLineNum)) {
                updTrxProcSts(wmsInbdTrxShipPk, PROC_STS.COMPLEATED, errorCode);
                this.totalErrCount++;
            } else {
                updTrxProcSts(wmsInbdTrxShipPk, PROC_STS.COMPLEATED, null);
                this.normalRecCount++;
            }
        }
    }

    private void updSerlTrxProcStsForWhErr(ArrayList<BigDecimal> wmsInbdTrxSerlPkWhErrList, BigDecimal wmsInbdTrxSerlPk, boolean isConfigFlgPre, String errorCode) {
        if (isConfigFlgPre) {
            updTrxProcSts(wmsInbdTrxSerlPk, PROC_STS.COMPLEATED, errorCode);
            this.totalErrCount++;
        } else {
            if (wmsInbdTrxSerlPkWhErrList.contains(wmsInbdTrxSerlPk)) {
                updTrxProcSts(wmsInbdTrxSerlPk, PROC_STS.COMPLEATED, errorCode);
                this.totalErrCount++;
            } else {
                updTrxProcSts(wmsInbdTrxSerlPk, PROC_STS.COMPLEATED, null);
                this.normalRecCount++;
            }
        }
    }
    // END 2021/08/27 R.Azucena[QC#59036, ADD]

    // START 2022/04/04 R.Azucena [QC#59802, ADD]
    // START 2022/08/08 R.Azucena [QC#60416, DEL]
    /**
     * getAvailQty
     * @param wmsInbdTrxShipMap Map<String, Object>
     * @return BigDecimal
     */
    // private BigDecimal getAvailQty(Map<String, Object> wmsInbdTrxShipMap) {
    //     Map<String, Object> queryParam = new HashMap<String, Object>();
    //     queryParam.put("glblCmpyCd", this.glblCmpyCd);
    //     queryParam.put("mdseCd", (String) wmsInbdTrxShipMap.get("WMS_MDSE_CD"));
    //     queryParam.put("stkStsCd", (String) wmsInbdTrxShipMap.get("WMS_STK_STS_CD"));
    //     queryParam.put("curLocNum", S21StringUtil.concatStrings((String) wmsInbdTrxShipMap.get("SOD_RTL_WH_CD"), (String) wmsInbdTrxShipMap.get("SOD_RTL_SWH_CD")));
    //     queryParam.put("svcMachMstrStsCdList", new String[] {SVC_MACH_MSTR_STS.CREATED, SVC_MACH_MSTR_STS.REMOVED });
    //     queryParam.put("svcMachMaintAvalFlg", ZYPConstant.FLG_ON_Y);

    //     BigDecimal pickSvcConfigMstrPk = (BigDecimal) wmsInbdTrxShipMap.get("PICK_SVC_CONFIG_MSTR_PK");
    //     if (ZYPCommonFunc.hasValue(pickSvcConfigMstrPk)) {
    //         queryParam.put("pickSvcConfigMstrPk", pickSvcConfigMstrPk);
    //     }

    //     return (BigDecimal) this.ssmBatchClient.queryObject("getAvailSingleQty", queryParam);
    // }

    /**
     * convertNullToZero
     * @param val BigDecimal
     * @return BigDecimal
     */
    // private static BigDecimal convertNullToZero(BigDecimal val) {
    //     if (val == null) {
    //         return BigDecimal.ZERO;
    //     }

    //     return val;
    // }

    /**
     * getMapKey
     * @param wmsInbdTrxShipMap Map<String, Object>
     * @return String
     */
    // private static String getMapKey(Map<String, Object> wmsInbdTrxShipMap) {
    //     return S21StringUtil.concatStrings((String) wmsInbdTrxShipMap.get("WMS_MDSE_CD"),
    //             ":", (String) wmsInbdTrxShipMap.get("WMS_STK_STS_CD"),
    //             ":", (String) wmsInbdTrxShipMap.get("SOD_RTL_WH_CD"),
    //             ":", (String) wmsInbdTrxShipMap.get("SOD_RTL_SWH_CD"),
    //             ":", convertNullToZero((BigDecimal) wmsInbdTrxShipMap.get("PICK_SVC_CONFIG_MSTR_PK")).toString());
    // }
    // END 2022/08/08 R.Azucena [QC#60416, DEL]
    // END 2022/04/04 R.Azucena [QC#59802, ADD]

    // START 2022/11/11 F.Fadriquela [QC#59802, ADD]
    /**
     * isItemAvailable
     * @param shpgSerTakeFlg String
     * @param serNum String
     * @param wmsMdseCd String
     * @param wmsStkStsCd String
     * @param svcConfigMstrPK String
     * @param otbdOrdNum String
     * @param otbdOrdLineNum String
     * @return boolean
     */
    private boolean isItemAvailable(String shpgSerTakeFlg, String serNum, String wmsMdseCd, String wmsStkStsCd, String svcConfigMstrPk, String otbdOrdNum, String otbdOrdLineNum) {

        //Get INVTY_LOC_CD from SHPG_ORD_DTL
        SHPG_ORD_DTLTMsg soDtlInMsg = new SHPG_ORD_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(soDtlInMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(soDtlInMsg.soNum, otbdOrdNum);
        ZYPEZDItemValueSetter.setValue(soDtlInMsg.soSlpNum, ZYPCommonFunc.leftPad(otbdOrdLineNum, 3, "0"));
        SHPG_ORD_DTLTMsg soDtlOutMsg = (SHPG_ORD_DTLTMsg) EZDTBLAccessor.findByKey(soDtlInMsg);

        if (soDtlOutMsg == null) {
            return true; //Error
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("mdseCd", wmsMdseCd);
        queryParam.put("invtyLocCd", soDtlOutMsg.invtyLocCd.getValue());
        queryParam.put("stkStsCd", wmsStkStsCd);
        queryParam.put("svcMachMaintAvalFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("svcMachMstrStsCdList", new String[] {SVC_MACH_MSTR_STS.CREATED, SVC_MACH_MSTR_STS.REMOVED });

        if ((ZYPConstant.FLG_ON_Y.equals(shpgSerTakeFlg)) && ZYPCommonFunc.hasValue(serNum)) {
            queryParam.put("serNum", serNum);
        }
        if (ZYPCommonFunc.hasValue(svcConfigMstrPk) && svcConfigMstrPk.matches("^[0-9]+$")) {
            queryParam.put("svcConfigMstrPk", svcConfigMstrPk);
        }

        BigDecimal svcMachMstrCount = (BigDecimal) this.ssmBatchClient.queryObject("getSvcMachMstrItemCnt", queryParam);

        if (!BigDecimal.ZERO.equals(svcMachMstrCount)) {
            return true;
        }

        return false;
    }
    // END 2022/11/11 F.Fadriquela [QC#59802, ADD]
}
