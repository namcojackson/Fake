/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB014001;

import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.ADJ;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.DEF_INTVL_TIME;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.EXPIRED;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.FETCH_SIZE_MAX;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.FORMAT_TIMESTAMP;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.KEY_ERR_MSG_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.KEY_PROC_STS_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.LINE_SUB_NUM_001;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.MACH_AVAIL_FLG;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.MIN_TO_MILLSEC;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NDMM0016E;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NLBM1018E;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NLBM1337E;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NLGM0008E;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NLGM0016E;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NLGM0044E;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NLGM0047E;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NLGM0049E;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NLGM0079;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NLGM0085W;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NLGM0096E;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NLGM0097E;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NLZM2091E;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NLZM2409E;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NLZM2410E;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NLZM2414E;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NLZM2450E;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NLZM2452E;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NLZM2500E;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.NOT_SKIP;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.SKIP;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.SSC;
import static com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant.TRANS_KEY_SEPARATOR;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDCommonFunc;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ADJ_CATGTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.RWS_HDRTMsg;
import business.db.WMS_INBD_TRXTMsg;
import business.parts.NLXC023001PMsg;
import business.parts.NLZC003001PMsg;
import business.parts.NLZC004001PMsg;
import business.parts.NLZC060001PMsg;
import business.parts.NLZC200001PMsg;
import business.parts.NLZC205001PMsg;
import business.parts.NLZC206001PMsg;
import business.parts.NLZC207001PMsg;
import business.parts.NLZC302001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC003001PMsg;
import business.parts.NWZC107001PMsg;

import com.canon.cusa.s21.api.NLG.NLGC001001.NLGC001001;
import com.canon.cusa.s21.api.NLZ.NLZC003001.NLZC003001;
import com.canon.cusa.s21.api.NLZ.NLZC004001.NLZC004001;
import com.canon.cusa.s21.api.NLZ.NLZC004001.constant.NLZC004001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC060001.NLZC060001;
import com.canon.cusa.s21.api.NLZ.NLZC060001.constant.NLZC060001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC200001.NLZC200001;
import com.canon.cusa.s21.api.NLZ.NLZC205001.NLZC205001;
import com.canon.cusa.s21.api.NLZ.NLZC206001.NLZC206001;
import com.canon.cusa.s21.api.NLZ.NLZC207001.NLZC207001;
import com.canon.cusa.s21.api.NLZ.NLZC302001.NLZC302001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.batch.NLG.NLGB014001.constant.NLGB014001Constant;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.common.NLX.NLXC023001.NLXC023001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADJ_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TASK;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** <pre>
 * Adjustment from WMS
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   CSAI            Y.Imazu         Create          N/A
 * 07/18/2016   CITS            T.Wada          Update          
 * 12/22/2016   CITS            R.Shimamoto     Update          QC#16804
 * 02/23/2017   CITS            Y.Fujii         Update          QC#17653
 * 03/03/2017   CITS            Y.Fujii         Update          QC#17653-2
 * 03/06/2017   CITS            T.Wada          Update          QC#17975
 * 03/14/2017   CITS            Y.IWASAKI       Update          QC#17653
 * 05/12/2017   CITS            T.Tokutomi      Update          QC#18581
 * 06/01/2017   CITS            T.Kikuhara      Update          QC#18727
 * 06/06/2017   CITS            R.Shimamoto     Update          QC#18580
 * 06/12/2017   CITS            T.Kikuhara      Update          QC#18727
 * 07/31/2017   CITS            T.Wada          Update          QC#20121
 * 02/05/2018   CITS            K.Ogino         Update          QC#23962
 * 02/16/2018   CITS            T.Gotoda        Update          QC#23845
 * 04/12/2018   CITS            S.Katsuma       Update          SOL#078,160
 * 05/08/2018   CITS            S.Katsuma       Update          QC#25913
 * 05/10/2018   CITS            K.Ogino         Update          QC#25644
 * 05/10/2018   CITS            K.Ogino         Update          QC#26108,QC#25838
 * 07/05/2018   CITS            T.Hakodate      Update          QC#26790
 * 07/10/2018   CITS            T.hakodate      Update          QC#27137
 * 07/23/2018   CITS            Y.Iwasaki       Update          QC#26674
 * 08/16/2018   CITS            S.Katsuma       Update          QC#27470
 * 11/14/2018   CITS            T.hakodate      Update          QC#29166
 * 01/15/2019   CITS            T.Hakodate      Update          QC#29946
 * 04/08/2019   CITS            K.Ogino         Update          QC#31012
 * 08/29/2019   CITS            K.Ogino         Update          QC#52941
 * 10/07/2019   CITS            T.Wada          Update          QC#53360
 * 11/21/2019   Fujitsu         T.Ogura         Update          QC#54633
 * 12/17/2019   CITS            T.Wada          Update          QC#54043
 * 02/06/2020   CITS            T.Wada          Update          QC#55714
 * 03/06/2020   CITS            K.Ogino         Update          QC#56166
 * 05/15/2020   CITS            K.Fukumura      Update          QC#56071
 * 06/04/2020   CITS            M.Furugoori     Update          QC#56720
 * 07/15/2021   CITS            A.Marte         Update          QC#58480
 * 10/20/2021   CITS            A.Marte         Update          QC#59368
 * 12/15/2021   CITS            A.Marte         Update          QC#59573
 * 02/11/2021   CITS            A.Marte         Update          QC#59705
 * </pre>
 */
public class NLGB014001 extends S21BatchMain {

    /** Counter : Normal finished Records */
    private int successCount = 0;

    /** Counter : Error finished Records */
    private int errorCount = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    // START 06/06/2017 QC#18580 MOD.
//    /** Batch Process Date */
//    private String batchProcDt = null;
    /** Sales Date  */
    private String salesDt = null;
    // END 06/06/2017 QC#18580 MOD.

    /** Warehouse Group Code */
    private String whGpCd = null;
    /** Process Mode */
    private String procMode = null;

    /** Term Code */
    private TERM_CD termCd;
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Processed Transaction List */
    private List<BigDecimal> procWmsInbdTrxPkList = new ArrayList<BigDecimal>();

    /** API Error Map */
    private Map<BigDecimal, String> apiErrMap = new HashMap<BigDecimal, String>();

    /** Error Message List */
    private List<Map<String, String>> errMsgList = new ArrayList<Map<String, String>>();

    /** Error Message Code */
    private String glErrMsgCd = null;

    /** isTerminated */
    private boolean isTerminated = false;

    /** execAlloc */
    private boolean execAlloc = false;

    // QC#18727 ADD START
    /** skip interval time for error RCVD WMS_INBOUND_TRX data */
    private String skipIntvlTime = "0";
    // QC#18727 ADD START

    /** Add QC#52941 NLGB0140_SKIP_RCVD_MAIL_HDR */
    private String skipRcvdMailHdr = "";

    /** Add QC#52941 NLGB0140_SKIP_RCVD_MAIL_NEXT */
    private String skipRcvdMailNext = "";

    /** Add QC#53360 */
    private List<String> errMsgList4ExclWmsInbdCheck = new ArrayList<String>();

    /** Add QC#53360 */
    private boolean isSkipErrMsg = false;

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {

        new NLGB014001().executeBatch(NLGB014001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.termCd = TERM_CD.NORMAL_END;

        // Global Company Code
        S21UserProfileService prof = S21UserProfileServiceFactory.getInstance().getService();
        glblCmpyCd = prof.getGlobalCompanyCode();

        if (S21StringUtil.isEmpty(glblCmpyCd)) {

            throw new S21AbendException(NLGM0049E, new String[] {"Global Company Code" });
        }

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblCmpyTMsg);

        if (null == glblCmpyTMsg) {

            throw new S21AbendException(NLGM0044E, new String[] {"GLBL_CMPY", "GLBL_CMPY_CD", glblCmpyCd });
        }

        // START 06/06/2017 QC#18580 MOD.
        // Batch Date
//        batchProcDt = ZYPDateUtil.getBatProcDate(glblCmpyCd, BUSINESS_ID);
//
//        if (S21StringUtil.isEmpty(batchProcDt)) {
//
//            throw new S21AbendException(NDMM0016E);
//        }
        this.salesDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        if (S21StringUtil.isEmpty(salesDt)) {

            throw new S21AbendException(NDMM0016E);
        }
        // END 06/06/2017 QC#18580 MOD.

        // Warehouse Group Code
        whGpCd = S21BatchUtil.getUserVariable1();

        if (!ZYPCommonFunc.hasValue(whGpCd)) {

            throw new S21AbendException(NLGM0049E, new String[] {"Warehouse Group Code" });
        }

        // Proc Mode
        procMode = S21BatchUtil.getUserVariable3();

        // QC#18727 ADD START
        // RCVD and ERROR WMS_INBOUND_TRX DATA Skip Time Interval.
        String varCharConst = ZYPCodeDataUtil.getVarCharConstValue("VLD_INTVL_SS_CHANGE_MIN", glblCmpyCd);
        long errIntvl = DEF_INTVL_TIME;
        if (ZYPCommonFunc.hasValue(varCharConst)) {
            errIntvl = Long.parseLong(varCharConst) * MIN_TO_MILLSEC;
        }
        this.skipIntvlTime = EZDCommonFunc.toyyyyMMddHHmmssSSS(EZDCommonFunc.toTimeMinute(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss")) //
                - errIntvl);
        // QC#18727 ADD END

        // QC#52941 Start
        varCharConst = ZYPCodeDataUtil.getVarCharConstValue("NLGB0140_SKIP_RCVD_MAIL_HDR", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(varCharConst)) {
            this.skipRcvdMailHdr = varCharConst;
        } else {
            this.skipRcvdMailHdr = "The STAT Order can not process because there is an error RCVD Order.";
        }

        varCharConst = ZYPCodeDataUtil.getVarCharConstValue("NLGB0140_SKIP_RCVD_MAIL_NEXT", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(varCharConst)) {
            this.skipRcvdMailNext = varCharConst;
        } else {
            this.skipRcvdMailNext = "Please Manual Receive by Receiving Orders Receipt Screen.";
        }
        // QC#52941 End
        
        //QC#53360 Add
        String valErrMsgExclWmsInbdCheck = ZYPCodeDataUtil.getVarCharConstValue("ERR_MSG_EXCL_WMS_INBD_CHECK", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(valErrMsgExclWmsInbdCheck)) {
            errMsgList4ExclWmsInbdCheck = Arrays.asList(valErrMsgExclWmsInbdCheck.split(","));
        }
    }

    @Override
    protected void mainRoutine() {

        // Package Code
        String packCdSetOwnrFlg = ZYPCodeDataUtil.getVarCharConstValue("WMS_PACK_CD_SET_OWNER_CD_FLG", glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(packCdSetOwnrFlg)) {
            packCdSetOwnrFlg = ZYPConstant.FLG_OFF_N;
        }

        // Target WHs
        String[] trgtWhCdAry = NLGC001001.getTrgtWhCd(glblCmpyCd, whGpCd);

        if (trgtWhCdAry == null) {
            // QC#52941
            setErrorMsg(NLGM0047E, new String[] {whGpCd }, null, null, null, null, null, null, true);
            termCd = TERM_CD.ABNORMAL_END;
        } else {
            if (ZYPCommonFunc.hasValue(procMode)) {
                if (ADJ.equals(procMode)) {
                    doAdjust(packCdSetOwnrFlg, trgtWhCdAry);
                } else if (SSC.equals(procMode)) {
                    doStkStsChange(packCdSetOwnrFlg, trgtWhCdAry);
                }
            } else {
                doAdjust(packCdSetOwnrFlg, trgtWhCdAry);
                doStkStsChange(packCdSetOwnrFlg, trgtWhCdAry);
            }
            /*************************************************************
             * 5. Send Error Email
             ************************************************************/
            if (!errMsgList.isEmpty()) {
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(BUSINESS_ID, errMsgList);
                commit();   // Commit For Mail Data
                termCd = TERM_CD.WARNING_END;
            }
        }
    }

    @Override
    protected void termRoutine() {

        // Print result.
        if (errorCount == 0) {

            S21InfoLogOutput.println("Adjustment from WMS Batch is normally end.");

        } else {

            S21InfoLogOutput.println("Adjustment from WMS is abnormally end.");
        }

        // Set term code and total count.
        setTermState(this.termCd, successCount, errorCount, (successCount + errorCount));
    }
    /**
     * 
     */
    private void doAdjust(String packCdSetOwnrFlg, String[] trgtWhCdAry) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            /*************************************************************
             * 1. Get Adjustment Data
             ************************************************************/
            S21SsmExecutionParameter excParam = new S21SsmExecutionParameter();
            excParam.setFetchSize(FETCH_SIZE_MAX);
            excParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            excParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            excParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("wmsTrgtWhCd", trgtWhCdAry);
            queryParam.put("packCdSetOwnrFlg", packCdSetOwnrFlg);
            queryParam.put("wmsTaskCadj", WMS_TASK.CADJ);
            queryParam.put("wmsTaskSera", WMS_TASK.SERA);
            queryParam.put("procStsCd", PROC_STS.IN_COMPLETED);

            stmt = this.ssmLLClient.createPreparedStatement("getAdjData", queryParam, excParam);
            rs = stmt.executeQuery();

            // commit control key
            String commitKey = "";

            // adj OrdApi Header Key
            String adjOrdApiHeaderKey = "";

            List<NLZC004001PMsg> adjOrdApiPMsgArray = new ArrayList<NLZC004001PMsg>();
            // START 2021/07/15 A.Marte [QC#58480, ADD]
            // For every config in transaction store it first in adjOrdApiPMsgTempArray array, 
            // when items in config are complete, then copy to adjOrdApiPMsgArray
            List<NLZC004001PMsg> adjOrdApiPMsgTempArray = new ArrayList<NLZC004001PMsg>();

            // store all wms_inbd_trx_pk of config in a list
            List<BigDecimal> configWmsInbdTrxPkList = new ArrayList<BigDecimal>();

            List<String> configInfoList = new ArrayList<String>();
            String trxConfigMstrPk = "";
            boolean hasConfigSpec = false;
            // END 2021/07/15 A.Marte [QC#58480, ADD]

            // QC#29946 add start
            String whCd = "";
            String wmsMdseCd = "";
            String wmsInbdTrxPkString = "";
            // QC#29946 add end

            while (rs.next()) {

                // START 2019/11/21 T.Ogura [QC#54633,ADD]
                String invtyCtrlFlg = rs.getString("INVTY_CTRL_FLG");
                if (ZYPCommonFunc.hasValue(invtyCtrlFlg) && ZYPConstant.FLG_OFF_N.equals(invtyCtrlFlg)) {
                    updTrxProcSts(rs.getBigDecimal("WMS_INBD_TRX_PK"), PROC_STS.COMPLEATED, null);
                    successCount++;
                    commit();
                    continue;
                }
                // END   2019/11/21 T.Ogura [QC#54633,ADD]

                // QC#29946 add start
                whCd = rs.getString("RTL_WH_CD");
                wmsMdseCd = rs.getString("MDSE_CD");
                wmsInbdTrxPkString = rs.getBigDecimal("WMS_INBD_TRX_PK").toString();
                // QC#29946 add end

                /*************************************************************
                 * 2. Create PMsg & Execute API(NLZC0040)
                 ************************************************************/
                if (!isSameTransaction(commitKey, rs)) {

                    // START 2021/07/15 A.Marte [QC#58480, ADD]
                    // process the previous transaction
                    // process adjusment if previous transaction has complete config items
                    if (hasConfigSpec && !trxConfigMstrPk.isEmpty()) {
                        checkConfigItems(trxConfigMstrPk, whCd, configInfoList, configWmsInbdTrxPkList, adjOrdApiPMsgArray, adjOrdApiPMsgTempArray);

                        if (adjOrdApiPMsgArray.size() > 1) {
                            executeAdjOrdApiTrns(adjOrdApiPMsgArray, whCd, wmsMdseCd, wmsInbdTrxPkString);
                        }
                        adjOrdApiPMsgTempArray.clear();
                        configWmsInbdTrxPkList.clear();
                        configInfoList.clear();

                    } else {
                        executeAdjOrdApiTrns(adjOrdApiPMsgArray, whCd, wmsMdseCd, wmsInbdTrxPkString);
                    }
                    // END 2021/07/15 A.Marte [QC#58480, ADD]

                    // START 2021/07/15 A.Marte [QC#58480, DEL]
                    // Adjustment Order API is executed
                    // QC#29946 add start
                    // executeAdjOrdApiTrns(adjOrdApiPMsgArray);
                    // executeAdjOrdApiTrns(adjOrdApiPMsgArray, whCd, wmsMdseCd, wmsInbdTrxPkString);
                    // END 2021/07/15 A.Marte [QC#58480, DEL]

                    commitKey = getCommitKey(rs);
                    adjOrdApiHeaderKey = getAdjOrdApiHeaderKey(rs);

                    // Create Adjustment Order API PMsg for Header
                    procWmsInbdTrxPkList.clear();
                    adjOrdApiPMsgArray.clear();
                    adjOrdApiPMsgArray.add(cratAdjOrdHdrApiPMsg(rs.getString("RTL_WH_CD"), rs.getBigDecimal("WMS_INBD_TRX_PK")));

                    // START 2021/07/15 A.Marte [QC#58480, ADD]
                    //reset of saved config checker flags for new transaction
                    hasConfigSpec = false;
                    trxConfigMstrPk = "";
                    // END 2021/07/15 A.Marte [QC#58480, ADD]

                } else if (!isSameAdjOrdApiHeader(adjOrdApiHeaderKey, rs)) {

                    // Create Adjustment Order API PMsg for Header
                    adjOrdApiPMsgArray.add(cratAdjOrdHdrApiPMsg(rs.getString("RTL_WH_CD"), rs.getBigDecimal("WMS_INBD_TRX_PK")));

                    adjOrdApiHeaderKey = getAdjOrdApiHeaderKey(rs);
                }

                /*************************************************************
                 * 3. Check Adjustment Data
                 ************************************************************/
                procWmsInbdTrxPkList.add(rs.getBigDecimal("WMS_INBD_TRX_PK"));

                /* If MDSE comes with serial, data comes not only SERA, but also CADJ as well.
                 * Skip processing if WMS_TASK_CD='CADJ' and MDSE.RCV_SER_TAKE_FLG='Y', and just turn PROC_STS to COMPLETED.
                 */
                if (WMS_TASK.CADJ.equals(rs.getString("WMS_TASK_CD")) && ZYPConstant.FLG_ON_Y.equals(rs.getString("RCV_SER_TAKE_FLG"))) {
                    // START 2021/07/15 A.Marte [QC#58480, ADD]
                    updTrxProcSts(rs.getBigDecimal("WMS_INBD_TRX_PK"), PROC_STS.COMPLEATED, null);
                    // END 2021/07/15 A.Marte [QC#58480, ADD]
                    continue;
                }

                /*************************************************************
                 * 4. Create Adjustment Order Detail
                 ************************************************************/
                // Create Adjustment Order API PMsg for Detail
                NLZC004001PMsg adjOrdApiPMsg = new NLZC004001PMsg();

                // START 2021/07/15 A.Marte [QC#58480, ADD]
                // check if line is config type first
                boolean isLineConfigSpec = false;
                String svcConfigMstrPk = rs.getString("SVC_CONFIG_MSTR_PK");
                if (ZYPCommonFunc.hasValue(svcConfigMstrPk) && isNumber(svcConfigMstrPk)) {
                    if (!trxConfigMstrPk.isEmpty() && !isSameConfigItem(trxConfigMstrPk, svcConfigMstrPk)) {
                        //copy adjOrdApiPMsgTempArray to adjOrdApiPMsgArray if all items in previous config are complete
                        checkConfigItems(trxConfigMstrPk, whCd, configInfoList, configWmsInbdTrxPkList, adjOrdApiPMsgArray, adjOrdApiPMsgTempArray);

                        adjOrdApiPMsgTempArray.clear();
                        configWmsInbdTrxPkList.clear();

                        //get the config items of the new config
                        configInfoList = getConfigItems(svcConfigMstrPk);

                    } else if (trxConfigMstrPk.isEmpty()) {
                         //get the configList for the first config
                        configInfoList = getConfigItems(svcConfigMstrPk);
                    }
                    configWmsInbdTrxPkList.add(rs.getBigDecimal("WMS_INBD_TRX_PK"));
                    ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.svcConfigMstrPk, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));

                    isLineConfigSpec = true;
                    hasConfigSpec = true;
                    trxConfigMstrPk = svcConfigMstrPk;

                } else if (hasConfigSpec && !trxConfigMstrPk.isEmpty()) {
                    //copy adjOrdApiPMsgTempArray to adjOrdApiPMsgArray if all items in previous config are complete
                    checkConfigItems(trxConfigMstrPk, whCd, configInfoList, configWmsInbdTrxPkList, adjOrdApiPMsgArray, adjOrdApiPMsgTempArray);

                    adjOrdApiPMsgTempArray.clear();
                    configWmsInbdTrxPkList.clear();
                    configInfoList.clear();
                    hasConfigSpec = false;
                    trxConfigMstrPk = "";
                }
                // END 2021/07/15 A.Marte [QC#58480, ADD]

                ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.xxProcTpCd, NLZC004001Constant.PROC_TP_CRAT);
                ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.xxDtTpCd, NLZC004001Constant.DT_TP_DTL);
                ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.slsDt, this.salesDt);
                ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.invtyOrdTpCd, INVTY_ORD_TP.ADJUSTMENT);
                ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.invtyLocCd_D1, rs.getString("INVTY_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.locStsCd_D1, LOC_STS.DC_STOCK);
                // START 2018/08/16 S.Katsuma [QC#27470,ADD]
                ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.invtyLocCd, rs.getString("INVTY_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.locStsCd, LOC_STS.DC_STOCK);
                // END 2018/08/16 S.Katsuma [QC#27470,ADD]
                ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.mdseCd, rs.getString("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.stkStsCd, rs.getString("STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.ordQty, rs.getBigDecimal("WMS_ORD_QTY"));

                // START 2021/07/15 A.Marte [QC#58480, ADD]
                if (isLineConfigSpec) {
                    configInfoList.remove(wmsMdseCd);
                }
                // END 2021/07/15 A.Marte [QC#58480, ADD]

                // QC#56071 2020/05/15 Start
////                // START 2018/04/12 S.Katsuma [SOL#078,160,MOD]
//////                ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.adjCatgCd, ADJ_CATG.CYCLE_COUNT);
////                ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.adjCatgCd, convWmsRsnCdToAdjCatgCd(rs.getString("WMS_RSN_CD"), rs.getString("MDSE_CD")));
////                ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.adjTrxTpCd, ADJ_TRX_TP.CYCLE_COUNT_ADJUSTMENTS);
////                // END 2018/04/12 S.Katsuma [SOL#078,160,MOD]
                if (NLGB014001Constant.WH_GP_CD_TECSYS.equals(this.whGpCd)) {
                    // ------------------------------------------------------------------
                    // For Tecsys
                    // ------------------------------------------------------------------
                    // ADJ_CATG_CD
                    ADJ_CATGTMsg adjCatgTMsg = getAdjCatg(rs.getString("CYCLE_CNT_RSN_CD"));
                    if (adjCatgTMsg != null) {
                        ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.adjCatgCd, rs.getString("CYCLE_CNT_RSN_CD"));
                    }
                    else {
                        ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.adjCatgCd, ADJ_CATG.OTHER);
                    }
                    // INVTY_ORD_LINE_CMT_TXT
                    ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.invtyOrdLineCmntTxt, GetCycleCountResounComments(adjOrdApiPMsg.adjCatgCd.getValue(), rs.getString("CYCLE_CNT_RSN_CMNT_TXT")));
                } else {
                    // ------------------------------------------------------------------
                    // For Not Tecsys
                    // ------------------------------------------------------------------
                    // ADJ_CATG_CD
                    ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.adjCatgCd, convWmsRsnCdToAdjCatgCd(rs.getString("WMS_RSN_CD"), rs.getString("MDSE_CD")));
                    // INVTY_ORD_LINE_CMT_TXT
                    ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.invtyOrdLineCmntTxt, rs.getString("CYCLE_CNT_RSN_CMNT_TXT"));
                }
                ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.adjTrxTpCd, ADJ_TRX_TP.CYCLE_COUNT_ADJUSTMENTS);

                // QC#56071 2020/05/15 End

                // START 2020/06/04 [QC#56720, ADD]
                ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.cycleCntRsnCd, rs.getString("CYCLE_CNT_RSN_CD"));
                // END 2020/06/04 [QC#56720, ADD]

                // START 2021/07/15 A.Marte [QC#58480, DEL]
                // QC#54043 Mod Start
                // String svcConfigMstrPk = rs.getString("SVC_CONFIG_MSTR_PK");
                // if (ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
                //     if (isNumber(svcConfigMstrPk)) {
                //         ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.svcConfigMstrPk, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
                //     }
                // }
                // QC#54043 Mod End
                // END 2021/07/15 A.Marte [QC#58480, DEL]


                ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.xxTrxRefPk, rs.getBigDecimal("WMS_INBD_TRX_PK"));

                // START 2021/10/19 A.Marte [QC#59368, ADD]
                // IB Trackable ?
                boolean isIbTrackable = false;
                if (ZYPCommonFunc.hasValue(rs.getString("INSTL_BASE_CTRL_FLG")) && (ZYPConstant.FLG_ON_Y.equals(rs.getString("INSTL_BASE_CTRL_FLG")))) {
                    isIbTrackable = true;
                }
                // START 2021/10/19 A.Marte [QC#59368, ADD]

                if (BigDecimal.ZERO.compareTo(rs.getBigDecimal("WMS_ORD_QTY")) < 0) {

                    ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.xxRqstTpCd, NLZC004001Constant.RQST_STOCK_IN);

                } else {

                    ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.xxRqstTpCd, NLZC004001Constant.RQST_STOCK_OUT);
                    // START 2021/07/15 A.Marte [QC#58480, ADD]
                    // START 2021/10/20 A.Marte [QC#59368, MOD]
                    // Check if  Item is Valid for Stock-Out for IB Item
                    if(isIbTrackable){
                        if (!isStockOutAvailable(adjOrdApiPMsg, isLineConfigSpec)) {
                            setErrorMsg(NLGM0096E, new String[] {adjOrdApiPMsg.xxTrxRefPk.getValue().toString(), wmsMdseCd, whCd}, null, null, null, null, null, null, true);
                            updTrxProcSts(rs.getBigDecimal("WMS_INBD_TRX_PK"), PROC_STS.COMPLEATED, NLGM0096E);
                            continue;
                        }
                    }
                    // END 2021/10/20 A.Marte [QC#59368, MOD]
                    // END 2021/07/15 A.Marte [QC#58480, ADD]
                }

                if (ZYPCommonFunc.hasValue(rs.getString("SER_NUM"))) {
                    ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.serialInfoList.no(0).serNum, rs.getString("SER_NUM"));
                    adjOrdApiPMsg.serialInfoList.setValidCount(1);
                }
                // QC#25838
                // NLXC0010 get Inventory Item Cost
                NLXC001001GetInventoryItemCostBean bean = new NLXC001001GetInventoryItemCostBean();
                bean.setGlblCmpyCd(glblCmpyCd);
                bean.setInvtyLocCd(adjOrdApiPMsg.invtyLocCd_D1.getValue());
                bean.setMdseCd(adjOrdApiPMsg.mdseCd.getValue());
                bean.setQty(adjOrdApiPMsg.ordQty.getValue());
                NLXC001001GetInventoryItemCostBean retNLXC001001GetInventoryItemCostBean = NLXC001001GetInventoryItemCost.getInventoryItemCost(bean);
                BigDecimal totPrcAmt = retNLXC001001GetInventoryItemCostBean.getTotPrcAmt();

                if (totPrcAmt != null) {
                    ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.invtyOrdLineCostAmt, totPrcAmt);
                }

                // START 2021/07/15 A.Marte [QC#58480, ADD]
                if (!isLineConfigSpec) {
                    adjOrdApiPMsgArray.add(adjOrdApiPMsg);
                } else {
                    adjOrdApiPMsgTempArray.add(adjOrdApiPMsg);
                }

                // Set default PROC_STS_CD to 1, if error occurs for the line it will be set in executeAdjOrdApiTrns
                updTrxProcSts(rs.getBigDecimal("WMS_INBD_TRX_PK"), PROC_STS.COMPLEATED, null);

            }

            // if there is only 1 config in a transaction, copy adjOrdApiPMsgTempArray to adjOrdApiPMsgArray if all items are complete
            if (hasConfigSpec && !trxConfigMstrPk.isEmpty()) {
                checkConfigItems(trxConfigMstrPk, whCd, configInfoList, configWmsInbdTrxPkList, adjOrdApiPMsgArray, adjOrdApiPMsgTempArray);
            }
            // END 2021/07/15 A.Marte [QC#58480, ADD]

            // Adjustment Order API is executed for Remained Data
            // START 2021/07/15 A.Marte [QC#58480, MOD]
            // Execute Adjustment order api for remaining data other than header
            //if (adjOrdApiPMsgArray.size() > 0) {
            if (adjOrdApiPMsgArray.size() > 1) {
            // END 2021/07/15 A.Marte [QC#58480, MOD]

                // QC#29946 add start
                // executeAdjOrdApiTrns(adjOrdApiPMsgArray);
                executeAdjOrdApiTrns(adjOrdApiPMsgArray, whCd, wmsMdseCd, wmsInbdTrxPkString);
                // QC#29946 add end
            }

            // QC#26790 ADD START
            procWmsInbdTrxPkList.clear();
            // QC#26790 ADD END

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }


    }


    /**
     * get Commit Key
     * @param rs ResultSet
     * @return String
     */
    private String getCommitKey(ResultSet rs) throws SQLException {

        String keyIntfcTrxId = rs.getString("INTFC_TRX_ID");
        String keyRtlWhCd = rs.getString("RTL_WH_CD");
        //String keyMdseCd = rs.getString("MDSE_CD");

        // Current CommitKey is INTFC_TRX_ID+RTL_WH_CD
        String transKey = ZYPCommonFunc.concatString(keyIntfcTrxId, TRANS_KEY_SEPARATOR, keyRtlWhCd);

        return transKey;
    }

    /**
     * check Transaction Key
     * @param transKey String
     * @param rs ResultSet
     * @return boolean
     */
    private boolean isSameTransaction(String transKey, ResultSet rs) throws SQLException {

        String nextTrnsKey = getCommitKey(rs);

        if (ZYPCommonFunc.hasValue(nextTrnsKey) && nextTrnsKey.equals(transKey)) {
            return true;
        }

        return false;
    }

    /**
     * get adjOrdApiHeaderKey
     * @param rs ResultSet
     * @return String
     */
    private String getAdjOrdApiHeaderKey(ResultSet rs) throws SQLException {

        String commitKey = getCommitKey(rs);
        //String qtySign = KEY_QTY_SIGN_PLUS;

        // Current HeaderKey is same as CommitKey
        String apiHeaderKey = commitKey;

        return apiHeaderKey;
    }

    /**
     * check adjOrdApiHeader Key
     * @param transKey String
     * @param rs ResultSet
     * @return boolean
     */
    private boolean isSameAdjOrdApiHeader(String apiHeaderKey, ResultSet rs) throws SQLException {

        String nextApiHeaderKey = getAdjOrdApiHeaderKey(rs);

        if (ZYPCommonFunc.hasValue(nextApiHeaderKey) && nextApiHeaderKey.equals(apiHeaderKey)) {
            return true;
        }

        return false;
    }

    /**
     * Create API PMsg for Adjustment Order Header
     * @param rtlWhCd String
     * @param wmsInbdTrxPk BigDecimal
     * @return NLZC004001PMsg
     */
    private NLZC004001PMsg cratAdjOrdHdrApiPMsg(String rtlWhCd, BigDecimal wmsInbdTrxPk) {

        NLZC004001PMsg adjOrdApiPMsg = new NLZC004001PMsg();
        ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.slsDt, this.salesDt);
        ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.xxProcTpCd, NLZC004001Constant.PROC_TP_CRAT);
        ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.xxDtTpCd, NLZC004001Constant.DT_TP_HDR);
        ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.invtyOrdTpCd, INVTY_ORD_TP.ADJUSTMENT);
        ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.adjTrxTpCd, ADJ_TRX_TP.CYCLE_COUNT_ADJUSTMENTS);
        ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.invtyLocCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        //ZYPEZDItemValueSetter.setValue(adjOrdApiPMsg.xxTrxRefPk, wmsInbdTrxPk);

        return adjOrdApiPMsg;
    }

    /**
     * Adjustment Order API Result Check
     * @param adjOrdApiPMsgArray List<NLZC004001PMsg>
     * @return boolean
     */
    // QC#29946 add start
    // private boolean isAdjOrdApiResult(List<NLZC004001PMsg>
    // adjOrdApiPMsgArray) {
    private boolean isAdjOrdApiResult(List<NLZC004001PMsg> adjOrdApiPMsgArray, String whCd, String wmsMdseCd, String wmsInbdTrxPkString) {
        // QC#29946 add end

        // QC#53360 Add Start
        isSkipErrMsg = true;
        boolean flSkipEnable = true;
        // QC#53360 Add End

        boolean isAdjOrdApiResult = true;

        for (NLZC004001PMsg adjOrdApiPMsg : adjOrdApiPMsgArray) {

            List<String> msgIdList = S21ApiUtil.getXxMsgIdList(adjOrdApiPMsg);

            if (!msgIdList.isEmpty()) {

                for (String msgId : msgIdList) {
                    // QC#29946 add start
                    //outputErr(NLGM0016E, new String[] {"NLZC004001", msgId, S21MessageFunc.clspGetMessage(msgId) });
                    // QC#52941

                    // QC#55714 Mod Start. QC#56166
//                  setErrorMsg(NLGM0016E, new String[] {"NLZC004001", wmsInbdTrxPkString, wmsMdseCd, whCd, S21MessageFunc.clspGetMessage(msgId) }, null, null, null, null, null, null, true);
                    String pk = wmsInbdTrxPkString;
                    if (ZYPCommonFunc.hasValue(adjOrdApiPMsg.xxTrxRefPk)) {
                        pk = adjOrdApiPMsg.xxTrxRefPk.getValue().toString();
                    }
                    String mdseCd = wmsMdseCd;
                    if (ZYPCommonFunc.hasValue(adjOrdApiPMsg.mdseCd)) {
                        mdseCd = adjOrdApiPMsg.mdseCd.getValue();
                    }
                    setErrorMsg(NLGM0016E, new String[] {"NLZC004001", pk, mdseCd, whCd, S21MessageFunc.clspGetMessage(msgId) }, null, null, null, null, null, null, true);
                    // QC#55714 Mod End

                    // QC#29946 mod end

                    // QC#17653
                    // Handle warning as error to avoid inconsistency of inventory data.
                    if (msgId.endsWith("E") || msgId.endsWith("W")) {

                        apiErrMap.put(adjOrdApiPMsg.xxTrxRefPk.getValue(), msgId);
                        isAdjOrdApiResult = false;
                    }

                    // QC#53360 Add Start
                    if (!isInclSkipList(msgId)) {
                        flSkipEnable = false;
                    }
                    // QC#53360 Add End
                }
            }
        }
        // QC#53360 Add Start
        if (!flSkipEnable) {
            isSkipErrMsg = false;
        }
        // QC#53360 Add End

        return isAdjOrdApiResult;
    }

    /**
     * Adjustment Order API is executed
     * @param adjOrdApiPMsgArray List<NLZC004001PMsg>
     */
        // QC#29946 add start
    //private void executeAdjOrdApiTrns(List<NLZC004001PMsg> adjOrdApiPMsgArray) {
    private void executeAdjOrdApiTrns(List<NLZC004001PMsg> adjOrdApiPMsgArray, String whCd, String wmsMdseCd, String wmsInbdTrxPkString) {
        // QC#29946 add end

        NLZC004001 adjOrdApi = new NLZC004001();

        List<List<NLZC004001PMsg>> execApiTrnsList = new ArrayList<List<NLZC004001PMsg>>();
        List<NLZC004001PMsg> execApiList = null;

        // divide api parameter
        for (NLZC004001PMsg pMsg : adjOrdApiPMsgArray) {
            if (NLZC004001Constant.DT_TP_HDR.equals(pMsg.xxDtTpCd.getValue())) {
                execApiList = new ArrayList<NLZC004001PMsg>();
                execApiTrnsList.add(execApiList);
            }
            execApiList.add(pMsg);
        }

        boolean isTransSuccess = true;

        // Adjustment Order API is executed
        for (List<NLZC004001PMsg> list : execApiTrnsList) {

            adjOrdApi.execute(list, ONBATCH_TYPE.BATCH);

         // QC#29946 add start
            // if (!isAdjOrdApiResult(list)) {
            if (!isAdjOrdApiResult(list, whCd, wmsMdseCd, wmsInbdTrxPkString)) {
                // QC#29946 add end
                rollback();
                isTransSuccess = false;
                break;
            }
        }

        if (isTransSuccess) {
            for (BigDecimal procWmsInbdTrxPk : procWmsInbdTrxPkList) {
                // Update Process Status Code
                updTrxProcSts(procWmsInbdTrxPk, PROC_STS.COMPLEATED, null);
            }
            successCount+=procWmsInbdTrxPkList.size();
        } else {
            for (BigDecimal procWmsInbdTrxPk : procWmsInbdTrxPkList) {
                String errMsgCd = null;
                if (apiErrMap.containsKey(procWmsInbdTrxPk)) {
                    errMsgCd = apiErrMap.get(procWmsInbdTrxPk);
                } else {
                    //QC#53360 Mod
                    if (!isSkipErrMsg) {
                        errMsgCd = NLGM0079;
                    }
                }
                
                
                // Update Process Status Code
                //QC#53360 Mod
                if (isSkipErrMsg && !ZYPCommonFunc.hasValue(errMsgCd)) {
                    updTrxProcSts(procWmsInbdTrxPk, PROC_STS.IN_COMPLETED, null);
                } else {
                    if (isSkipErrMsg) {
                        updTrxProcSts(procWmsInbdTrxPk, PROC_STS.COMPLEATED, errMsgCd);
                    } else {
                        updTrxProcSts(procWmsInbdTrxPk, PROC_STS.ERROR, errMsgCd);                        
                    }
                }
            }
            errorCount+=procWmsInbdTrxPkList.size();
        }

        commit();
    }

    // START 2021/07/15 A.Marte [QC#58480, ADD]
    /**
     * checkConfigItems
     * @param String trxConfigMstrPk
     * @param String whCd
     * @param List<String> configInfoList
     * @param List<BigDecimal> configWmsInbdTrxPkList
     * @param List<NLZC004001PMsg> adjOrdApiPMsgArray
     * @param List<NLZC004001PMsg> adjOrdApiPMsgTempArray
     * @return
     */
    private void checkConfigItems(String trxConfigMstrPk, String whCd, List<String> configInfoList, List<BigDecimal> configWmsInbdTrxPkList, List<NLZC004001PMsg> adjOrdApiPMsgArray, List<NLZC004001PMsg> adjOrdApiPMsgTempArray) {
        //if there are missing items in the config, set error
        boolean incompleteConfig = setErrorForIncompleteConfig(configInfoList, configWmsInbdTrxPkList, trxConfigMstrPk, whCd);

        //if config items are complete, add to all items to adjOrdApiPMsgArray
        if (!incompleteConfig) {
            copyToValidList(adjOrdApiPMsgArray, adjOrdApiPMsgTempArray);
        }
    }
    /**
     * setErrorForIncompleteConfig
     * @param List<String> configInfoList
     * @param List<BigDecimal> configWmsInbdTrxPkList
     * @param String trxConfigMstrPk
     * @param String whCd
     * @return boolean
     */
    private boolean setErrorForIncompleteConfig(List<String> configInfoList, List<BigDecimal> configWmsInbdTrxPkList, String trxConfigMstrPk, String whCd) {
        boolean isIncompleteConfig = false;

        if (configInfoList.size() > 0) {
            //if there are missing config items in the request, set error for all items
            for (BigDecimal configWmsInbdTrxPk : configWmsInbdTrxPkList) {
                setErrorMsg(NLGM0097E, new String[] {trxConfigMstrPk, configWmsInbdTrxPk.toString(), whCd}, null, null, null, null, null, null, true);
                updTrxProcSts(configWmsInbdTrxPk, PROC_STS.COMPLEATED, NLGM0097E);
                procWmsInbdTrxPkList.remove(configWmsInbdTrxPk);
            }
            isIncompleteConfig = true;
        }
        return isIncompleteConfig;
    }

    /**
     * isSameConfigItem
     * @param String prevConfigPk, String curConfigPk
     * @return boolean
     */
    private static void copyToValidList(List<NLZC004001PMsg> validList, List<NLZC004001PMsg> tempList) {
        for (NLZC004001PMsg item : tempList) {
            validList.add(item);
        }
    }

    /**
     * isSameConfigItem
     * @param String prevConfigPk, String curConfigPk
     * @return boolean
     */

    private boolean isSameConfigItem(String prevConfigPk, String curConfigPk) {
        return prevConfigPk.equals(curConfigPk);
    }

    /**
     * isStockOutAvailable
     * @param pMsg, isConfigSpec
     * @return boolean
     */
    private boolean isStockOutAvailable(NLZC004001PMsg pMsg, boolean isConfigSpec) {

        boolean isAvailable = true;

        //Decrease Item Stock/StockOut
        if (BigDecimal.ZERO.compareTo(pMsg.ordQty.getValue()) > 0) {
            // Check Available Non-Config Item
            if (!isConfigSpec) {

                List<Map<String, Object>> machMstrInfoList = searchNonConfig(pMsg.mdseCd.getValue(), pMsg.invtyLocCd_D1.getValue(), pMsg.stkStsCd.getValue(), pMsg.ordQty.getValue().abs().intValue());

                if (machMstrInfoList == null || machMstrInfoList.size() == 0) {
                    isAvailable = false;
                }
            }
        }
        return isAvailable;
    }

    // END 2021/07/15 A.Marte [QC#58480, ADD]


    // QC#53360 Add Start
    /**
     * isInclSkipList
     * @param msgId
     * @return
     */
    private boolean isInclSkipList(String msgId) {

        // Const「 ERR_MSG_EXCL_WMS_INBD_CHECK」SetUp Check
        if (errMsgList4ExclWmsInbdCheck.isEmpty()) {
            return false;
        }

        for (int i=0; i<errMsgList4ExclWmsInbdCheck.size(); i++) {
            if (msgId.equals(errMsgList4ExclWmsInbdCheck.get(i))) {
                return true;
            }
        }
        return false;
    }
    // QC#53360 Add End

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

        WMS_INBD_TRXTMsg updWmsInbdTrxTMsg = (WMS_INBD_TRXTMsg) EZDTBLAccessor.findByKeyForUpdate(wmsInbdTrxTMsg);
        if (updWmsInbdTrxTMsg == null) {
            throw new S21AbendException(NLGM0044E, new String[] {"WMS_INBD_TRX", "WMS_INBD_TRX_PK", wmsInbdTrxPk.toString() });            
        }

        ZYPEZDItemValueSetter.setValue(updWmsInbdTrxTMsg.procStsCd, procStsCd);

        if (ZYPCommonFunc.hasValue(errMsgCd)) {
            ZYPEZDItemValueSetter.setValue(updWmsInbdTrxTMsg.errMsgCd, errMsgCd);
            EZDTBLAccessor.updateSelectionField(updWmsInbdTrxTMsg, new String[] {KEY_PROC_STS_CD, KEY_ERR_MSG_CD });

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updWmsInbdTrxTMsg.getReturnCode())) {
                throw new S21AbendException(NLGM0008E, new String[] {"WMS_INBD_TRX", "WMS_INBD_TRX_PK", wmsInbdTrxPk.toString() });            
            }
        } else {
            EZDTBLAccessor.updateSelectionField(updWmsInbdTrxTMsg, new String[] {KEY_PROC_STS_CD });

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updWmsInbdTrxTMsg.getReturnCode())) {
                throw new S21AbendException(NLGM0008E, new String[] {"WMS_INBD_TRX", "WMS_INBD_TRX_PK", wmsInbdTrxPk.toString() });            
            }
        }
    }

    /**
     * doStkStsChange
     * @param packCdSetOwnrFlg
     * @param trgtWhCdAry
     */
    private void doStkStsChange(String packCdSetOwnrFlg, String[] trgtWhCdAry) {

        Map<String, String> piMap= new HashMap<String, String>(); 

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            /*************************************************************
             * 1. Get StockStatus Data
             ************************************************************/
            S21SsmExecutionParameter excParam = new S21SsmExecutionParameter();
            excParam.setFetchSize(FETCH_SIZE_MAX);
            excParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            excParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            excParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("wmsTrgtWhCd", trgtWhCdAry);
            queryParam.put("packCdSetOwnrFlg", packCdSetOwnrFlg);
            queryParam.put("wmsTaskStat", WMS_TASK.STAT);
            queryParam.put("procStsCd", PROC_STS.IN_COMPLETED);
            queryParam.put("rcvd", WMS_TASK.RCVD);
            queryParam.put("return", SCE_ORD_TP.RETURN);

            // QC#29166 add start
            BigDecimal min = new BigDecimal("60");
            BigDecimal sec = new BigDecimal("60");
            BigDecimal millSec = new BigDecimal("1000");

            BigDecimal skipErrorRetryHour = ZYPCodeDataUtil.getNumConstValue("WMS_STAT_ERROR_RETRY_HOUR", glblCmpyCd);
            String skipErrorStatTsEnd = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
            String skipErrorStatTsStart = null;

            if (ZYPCommonFunc.hasValue(skipErrorRetryHour)) {

                skipErrorRetryHour = skipErrorRetryHour.multiply(min);
                skipErrorRetryHour = skipErrorRetryHour.multiply(sec);
                skipErrorRetryHour = skipErrorRetryHour.multiply(millSec);
                skipErrorRetryHour = skipErrorRetryHour.setScale(0, BigDecimal.ROUND_HALF_UP);

                long errIntvl = Long.parseLong(skipErrorRetryHour.toString());
                skipErrorStatTsStart = EZDCommonFunc.toyyyyMMddHHmmssSSS(EZDCommonFunc.toTimeMinute(skipErrorStatTsEnd) - errIntvl);
            } else {
                skipErrorStatTsStart = EZDCommonFunc.toyyyyMMddHHmmssSSS(EZDCommonFunc.toTimeMinute(skipErrorStatTsEnd));
            }

            queryParam.put("skipErrorStatTsStart", skipErrorStatTsStart);
            queryParam.put("skipErrorStatTsEnd", skipErrorStatTsEnd);
            queryParam.put("procStsCdError", PROC_STS.ERROR);
            // QC#29166 add end

            stmt = this.ssmLLClient.createPreparedStatement("getStatData", queryParam, excParam);
            rs = stmt.executeQuery();
            // QC#52941
            List<String> skipKeyList = new ArrayList<String>();
            List<String> inbdOrdList = new ArrayList<String>();

            while (rs.next()) {

                // QC13112
                BigDecimal wmsInbdTrxPk = rs.getBigDecimal("WMS_INBD_TRX_PK");

                // QC#18727 ADD. Mod QC#52941
                // STAT Data
                String statWhCd = rs.getString("WH_CD");
                String statWmsMdseCd = rs.getString("WMS_MDSE_CD");
                String statWmsOldStkStsCd = rs.getString("WMS_OLD_STK_STS_CD");
                String ezIntime = rs.getString("EZINTIME");
                String serNum = rs.getString("SER_NUM");
                String statMdseCd = rs.getString("MDSE_CD");
                String invtyLocCd = rs.getString("INVTY_LOC_CD");

                StringBuilder sb = new StringBuilder();
                sb.append(statWhCd);
                sb.append(statWmsMdseCd);
                sb.append(statWmsOldStkStsCd);
                if(ZYPCommonFunc.hasValue(serNum)) {
                    sb.append(serNum);
                }
                String sscSkipKey = sb.toString();

                if (skipKeyList.contains(sscSkipKey)) {
                    updTrxProcSts(wmsInbdTrxPk, PROC_STS.COMPLEATED, null);
                    successCount++;
                    commit();
                    continue;
                }

                int retNum = chkErrRcvDataSkip(statWhCd, statWmsMdseCd, statWmsOldStkStsCd, ezIntime, serNum, statMdseCd, invtyLocCd, skipKeyList, inbdOrdList); // 0:not skip / 1:skip / 2:expired
                if (retNum == 1) {
                    continue;
                } else if (retNum == 2) {
                    updTrxProcSts(wmsInbdTrxPk, PROC_STS.COMPLEATED, null);
                    successCount++;
                    commit();
                    continue;
                }
                // QC#18727 END. Mod QC#52941

                // QC#29946 add start
                String whCd = rs.getString("RTL_WH_CD");
                String wmsMdseCd = rs.getString("WMS_MDSE_CD");
                String wmsInbdTrxPkString = rs.getBigDecimal("WMS_INBD_TRX_PK").toString();
                // QC#29946 add end

                String inbdOrdNum = rs.getString("INBD_ORD_NUM");
                if (ZYPCommonFunc.hasValue(inbdOrdNum)) {
                    // START 2021/12/15 A.Marte [QC#59573, MOD]
                    //updTrxProcSts(wmsInbdTrxPk, PROC_STS.COMPLEATED, null);
                    //successCount++;
                    //commit();
                    //continue;
                    if (!isSCEReturnType(inbdOrdNum)) {
                        updTrxProcSts(wmsInbdTrxPk, PROC_STS.COMPLEATED, null);
                        successCount++;
                        commit();
                        continue;
                    }
                    // START 2021/12/15 A.Marte [QC#59573, MOD]
                }

                // QC#17975 PI Check Start
                if (ZYPCommonFunc.hasValue(invtyLocCd)) {
                    if (piMap.containsKey(invtyLocCd)) {
                        if (!NLZC060001Constant.RETURN_CODE_NORMAL.equals(piMap.get(invtyLocCd))) {
                            continue;
                        }
                    } else {
                        NLZC060001PMsg pMsg = new NLZC060001PMsg();
                        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, invtyLocCd);
                        NLZC060001 nlzc060001 = new NLZC060001();
                        nlzc060001.execute(pMsg, ONBATCH_TYPE.BATCH);

                        if (ZYPCommonFunc.hasValue(pMsg.xxRsltStsCd) && !pMsg.xxRsltStsCd.getValue().equals(NLZC060001Constant.RETURN_CODE_NORMAL)) {
                            piMap.put(pMsg.invtyLocCd.getValue(), pMsg.xxRsltStsCd.getValue());
                            for (int j = 0; j < pMsg.xxMsgIdList.getValidCount(); j++) {
                                String message = S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(j).xxMsgId.getValue());
                                S21InfoLogOutput.println(message);
                            }
                            continue;
                        } else if (pMsg.xxMsgIdList.getValidCount() > 0) {
                            for (int j = 0; j < pMsg.xxMsgIdList.getValidCount(); j++) {
                                String message = S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(j).xxMsgId.getValue());
                                S21InfoLogOutput.println(message);
                            }
                            continue;
                        } else {
                            piMap.put(pMsg.invtyLocCd.getValue(), NLZC060001Constant.RETURN_CODE_NORMAL);
                        }

                    }
                }
                // QC#17975 PI Check End

                List<Map<String, Object>> machMstrInfoList = new ArrayList<Map<String, Object>>();
                // IB Trackable ?
                boolean isIbTrackable = false;
                if (ZYPCommonFunc.hasValue(rs.getString("INSTL_BASE_CTRL_FLG")) && (ZYPConstant.FLG_ON_Y.equals(rs.getString("INSTL_BASE_CTRL_FLG")))) {
                    isIbTrackable = true;
                }
                // SerializedItem ?
                boolean isSerializedItem = false;
                if (ZYPCommonFunc.hasValue(rs.getString("SHPG_SER_TAKE_FLG")) && (ZYPConstant.FLG_ON_Y.equals(rs.getString("SHPG_SER_TAKE_FLG")))) {
                    isSerializedItem = true;
                }
                // Serial Spec ?
                boolean isSerialSpec = false;
                if (ZYPCommonFunc.hasValue(rs.getString("SER_NUM"))) {
                    isSerialSpec = true;
                }
                // Config ?
                // QC#23845 MOD START
                boolean isConfigSpec = false;
                String svcConfigMstrPk = rs.getString("SVC_CONFIG_MSTR_PK");
                if (ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
                    if (isNumber(svcConfigMstrPk)) {
                        isConfigSpec = true;
                    }
                }
                // QC#23845 MOD END

                // START 2022/02/11 A.Marte [QC#59705, ADD]
                boolean isRMAConfigSpec = false;

                String rmaNum = rs.getString("RMA_NUM");
                if (ZYPCommonFunc.hasValue(rmaNum)) {
                    // If RMA line, check if INBD_ORD_NUM matches
                    boolean isMatchingRMAConfig = isMatchingRMAInbdOrdNum(rmaNum, inbdOrdNum);
                    if (isMatchingRMAConfig || isSerialSpec) {
                        // If request is RMA-linked, get config info from RMA match line
                        svcConfigMstrPk = getConfigFromRMAMatchLine(rs, rmaNum, isMatchingRMAConfig, isSerialSpec);
                        if (ZYPCommonFunc.hasValue(svcConfigMstrPk) && isNumber(svcConfigMstrPk)) {
                            isRMAConfigSpec = true;
                            isConfigSpec = true;
                        }
                    }
                }
                // END 2022/02/11 A.Marte [QC#59705, ADD]

                execAlloc = false;
                isTerminated = false;
                
                /*************************************************************
                 * 2. Create Stock Status Change Order
                 ************************************************************/
                // ////////////////////////////////////////////////////////////
                // 1 NLZC0030 - Inventory Order API Call
                // ////////////////////////////////////////////////////////////
                // START 2022/02/11 A.Marte [QC#59705, MOD]
                String invtyOrdNum = execInventoryOrder(rs, isConfigSpec, isRMAConfigSpec, svcConfigMstrPk);
                // END 2022/02/11 A.Marte [QC#59705, MOD]
                if (!ZYPCommonFunc.hasValue(invtyOrdNum)) {
                    // QC#29946 mod start
                    // outputErr(NLGM0016E, new String[]
                    // {"NLZC003001", glErrMsgCd,
                    // S21MessageFunc.clspGetMessage(glErrMsgCd) });
                    // QC#52941
                    setErrorMsg(NLGM0016E, new String[] {"NLZC003001", wmsInbdTrxPkString, wmsMdseCd, whCd, S21MessageFunc.clspGetMessage(glErrMsgCd) }, null, null, null, null, null, null, true);
                    // QC#29946 mod end
                    updErrTrxProcSts(wmsInbdTrxPk);
                    continue;
                }
                List<Map<String, Object>> invtyOrdInfoList = getInvtyOrdInfo(invtyOrdNum);
                
                for (Map<String, Object> invtyOrdInfo : invtyOrdInfoList) {
                    // START 2022/02/11 A.Marte [QC#59705, MOD]
                    Map<String, Object> stkStsChgInfo = setStkStsChgInfo(invtyOrdInfo, rs, isConfigSpec, isRMAConfigSpec, svcConfigMstrPk);
                    // END 2022/02/11 A.Marte [QC#59705, MOD]

                    // ////////////////////////////////////////////////////////////
                    // 2 NWZC1070 - Allocation For Non CPO API Call
                    // ////////////////////////////////////////////////////////////
                    if (!execDivAllocation(stkStsChgInfo)) {
                        // QC#29946 mod start
                        //outputErr(NLGM0016E, new String[] {"NWZC107001", glErrMsgCd, S21MessageFunc.clspGetMessage(glErrMsgCd) });
                        // QC#52941
                        setErrorMsg(NLGM0016E, new String[] {"NWZC107001", wmsInbdTrxPkString, wmsMdseCd, whCd, S21MessageFunc.clspGetMessage(glErrMsgCd) }, null, null, null, null, null, null, true);
                        // QC#29946 mod end
                        updErrTrxProcSts(wmsInbdTrxPk);
                        continue;
                    }

                    // ////////////////////////////////////////////////////////////
                    // 3 NSZC0010 - Machine Master Update API Call
                    // ////////////////////////////////////////////////////////////
                    if (isIbTrackable) {
                        // QC#29946 mod start
                        //machMstrInfoList = execSvcMachMstrAllocation(stkStsChgInfo, isSerializedItem, isSerialSpec, isConfigSpec);
                        // START 2021/12/15 A.Marte [QC#59573, MOD]
                        //machMstrInfoList = execSvcMachMstrAllocation(stkStsChgInfo, isSerializedItem, isSerialSpec, isConfigSpec, wmsInbdTrxPkString, wmsMdseCd, whCd);
                        machMstrInfoList = execSvcMachMstrAllocation(stkStsChgInfo, isSerializedItem, isSerialSpec, isConfigSpec, wmsInbdTrxPkString, wmsMdseCd, whCd, inbdOrdNum);
                        // END 2021/12/15 A.Marte [QC#59573, ADD]
                        // QC#29946 mod end
                        // QC#18727 MOD START
                        if (machMstrInfoList == null) {
                            // QC#52941
                            setErrorMsg(glErrMsgCd, null, null, null, null, null, null, null, true);
                            updErrTrxProcSts(wmsInbdTrxPk);
                            continue;
                        }
                        // QC#18727 MOD END
                    }

                    // ////////////////////////////////////////////////////////////
                    // 4 NWZC0030 - Shipping Plan Update API Call
                    // ////////////////////////////////////////////////////////////
                    if (!execShippingPlanUpdate(stkStsChgInfo)) {
                        // QC#29946 mod start
                        //outputErr(NLGM0016E, new String[] {"NWZC003001", glErrMsgCd, S21MessageFunc.clspGetMessage(glErrMsgCd) });
                        // QC#52941
                        setErrorMsg(NLGM0016E, new String[] {"NWZC003001", wmsInbdTrxPkString, wmsMdseCd, whCd, S21MessageFunc.clspGetMessage(glErrMsgCd) }, null, null, null, null, null, null, true);
                        // QC#29946 mod end
                        updErrTrxProcSts(wmsInbdTrxPk);
                        continue;
                    }

                    // ////////////////////////////////////////////////////////////
                    // 5 NLXC0230 - Get Shipping Plan Number
                    // ////////////////////////////////////////////////////////////
                    String shpgPlnNum = getShpgPlnNum(invtyOrdNum);
                    if (!ZYPCommonFunc.hasValue(shpgPlnNum)) {
                        // QC#29946 mod start
                        //outputErr(NLGM0016E, new String[] {"NLXC023001", glErrMsgCd, S21MessageFunc.clspGetMessage(glErrMsgCd) });
                        // QC#52941
                        setErrorMsg(NLGM0016E, new String[] {"NLXC023001", wmsInbdTrxPkString, wmsMdseCd, whCd, S21MessageFunc.clspGetMessage(glErrMsgCd) }, null, null, null, null, null, null, true);
                        // QC#29946 mod end
                        updErrTrxProcSts(wmsInbdTrxPk);
                        continue;
                    }

                    // ////////////////////////////////////////////////////////////
                    // 6 NLZC2050 - SO API
                    // ////////////////////////////////////////////////////////////
                    String soNum = execShippingOrder(shpgPlnNum);
                    if (!ZYPCommonFunc.hasValue(soNum)) {
                        // QC#29946 mod start
                        //outputErr(NLGM0016E, new String[] {"NLZC205001", glErrMsgCd, S21MessageFunc.clspGetMessage(glErrMsgCd) });
                        // QC#52941
                        setErrorMsg(NLGM0016E, new String[] {"NLZC205001", wmsInbdTrxPkString, wmsMdseCd, whCd, S21MessageFunc.clspGetMessage(glErrMsgCd) }, null, null, null, null, null, null, true);
                        // QC#29946 mod end
                        updErrTrxProcSts(wmsInbdTrxPk);
                        continue;
                    }

                    // /////////////////////////////////////////////////
                    // 8 NLZC2000 - RWS API
                    // /////////////////////////////////////////////////
                    String rwsNum = execRWSCreation(soNum);
                    if (!ZYPCommonFunc.hasValue(rwsNum)) {
                        // QC#29946 mod start
                        //outputErr(NLGM0016E, new String[] {"NLZC200001", glErrMsgCd, S21MessageFunc.clspGetMessage(glErrMsgCd) });
                        // QC#52941
                        setErrorMsg(NLGM0016E, new String[] {"NLZC200001", wmsInbdTrxPkString, wmsMdseCd, whCd, S21MessageFunc.clspGetMessage(glErrMsgCd) }, null, null, null, null, null, null, true);
                        // QC#29946 mod end
                        updErrTrxProcSts(wmsInbdTrxPk);
                        continue;
                    }
                    /*************************************************************
                     * 3. Execute Stock Status Change
                     ************************************************************/
                    RWS_HDRTMsg rwsHdrMap = getRwsHdr(rwsNum);

                    // /////////////////////////////////////////////////
                    // 1 NLZC2060 - S21 DC Put Away Confirmation API
                    // /////////////////////////////////////////////////
                    if (!execRwsPutAwayConf(rwsHdrMap, stkStsChgInfo)) {
                        // QC#29946 mod start
                        //outputErr(NLGM0016E, new String[] {"NLZC206001", glErrMsgCd, S21MessageFunc.clspGetMessage(glErrMsgCd) });
                        // QC#52941
                        setErrorMsg(NLGM0016E, new String[] {"NLZC206001", wmsInbdTrxPkString, wmsMdseCd, whCd, S21MessageFunc.clspGetMessage(glErrMsgCd) }, null, null, null, null, null, null, true);
                        // QC#29946 mod end
                        updErrTrxProcSts(wmsInbdTrxPk);
                        continue;
                    }
                    // /////////////////////////////////////////////////
                    // 2 NLZC2070 - S21 DC RWS Completion API
                    // /////////////////////////////////////////////////
                    if (!execRwsCompletion(rwsHdrMap, stkStsChgInfo)) {
                        // QC#29946 mod start
                        //outputErr(NLGM0016E, new String[] {"NLZC207001", glErrMsgCd, S21MessageFunc.clspGetMessage(glErrMsgCd) });
                        // QC#52941
                        setErrorMsg(NLGM0016E, new String[] {"NLZC207001", wmsInbdTrxPkString, wmsMdseCd, whCd, S21MessageFunc.clspGetMessage(glErrMsgCd) }, null, null, null, null, null, null, true);
                        // QC#29946 mod end
                        updErrTrxProcSts(wmsInbdTrxPk);
                        continue;

                    }
                    // /////////////////////////////////////////////////
                    // 4 NLZC3020 - Serial Transaction Update API
                    // /////////////////////////////////////////////////
                    if (isSerialSpec) {
                        if (!execSerialTransaction(stkStsChgInfo, soNum)) {
                            // QC#29946 mod start
                            //outputErr(NLGM0016E, new String[] {"NLZC302001", glErrMsgCd, S21MessageFunc.clspGetMessage(glErrMsgCd) });
                            // QC#52941
                            setErrorMsg(NLGM0016E, new String[] {"NLZC302001", wmsInbdTrxPkString, wmsMdseCd, whCd, S21MessageFunc.clspGetMessage(glErrMsgCd) }, null, null, null, null, null, null, true);
                            // QC#29946 mod end
                            updErrTrxProcSts(wmsInbdTrxPk);
                            continue;
                        }
                    }
                    // /////////////////////////////////////////////////
                    // 5 NSZC0010 - Machine Master Update API Call
                    // /////////////////////////////////////////////////
                    // The case Machine Master the Machine Master was Allocation
                    if (execAlloc) {
                        boolean apiErr = false;
                        for (Map<String, Object> machMstrInfo : machMstrInfoList) {
                            // ALLOCATION_OFF
                            if (!execSvcMachMstrAllocationOff(invtyOrdNum, machMstrInfo)) {
                                // QC#29946 mod start
                                //outputErr(NLGM0016E, new String[] {"NSZC001001", glErrMsgCd, S21MessageFunc.clspGetMessage(glErrMsgCd) });
                                // QC#52941
                                setErrorMsg(NLGM0016E, new String[] {"NSZC001001", wmsInbdTrxPkString, wmsMdseCd, whCd, S21MessageFunc.clspGetMessage(glErrMsgCd) }, null, null, null, null, null, null, true);
                                // QC#29946 mod end
                                apiErr = true;
                                break;
                            }
                            // UPDATE_INVENTORY
                            if (!execSvcMachMstrUpdateInventry(invtyOrdNum, machMstrInfo, stkStsChgInfo)) {
                                // QC#29946 mod start
                                //outputErr(NLGM0016E, new String[] {"NSZC001001", glErrMsgCd, S21MessageFunc.clspGetMessage(glErrMsgCd) });
                                // QC#52941
                                setErrorMsg(NLGM0016E, new String[] {"NSZC001001", wmsInbdTrxPkString, wmsMdseCd, whCd, S21MessageFunc.clspGetMessage(glErrMsgCd) }, null, null, null, null, null, null, true);
                                // QC#29946 mod end
                                apiErr = true;
                                break;
                            }
                        }
                        if (apiErr) {
                            updErrTrxProcSts(wmsInbdTrxPk);
                            continue;
                        }
                    }

                    // If Terminated of Machine Master exists
                    if (isTerminated) {
                        String mdseCd = (String) stkStsChgInfo.get("MDSE_CD");
                        if (mdseCd.equals((String) machMstrInfoList.get(0).get("MDSE_CD"))) {
                            // UPDATE_WAREHOUSE
                            if (!execSvcMachMstrUpdateWh(stkStsChgInfo, machMstrInfoList.get(0))) {
                                // QC#29946 mod start
                                //outputErr(NLGM0016E, new String[] {"NSZC001001", glErrMsgCd, S21MessageFunc.clspGetMessage(glErrMsgCd) });
                                // QC#52941
                                setErrorMsg(NLGM0016E, new String[] {"NSZC001001", wmsInbdTrxPkString, wmsMdseCd, whCd, S21MessageFunc.clspGetMessage(glErrMsgCd) }, null, null, null, null, null, null, true);
                                // QC#29946 mod end
                                updErrTrxProcSts(wmsInbdTrxPk);
                                continue;
                            }
                        } else { // If the Merchandise Code is
                                 // different
                            // ITEM_CHANGE
                            if (!execSvcMachMstrItemChange(stkStsChgInfo, machMstrInfoList.get(0))) {
                                // QC#29946 mod start
                                //outputErr(NLGM0016E, new String[] {"NSZC001001", glErrMsgCd, S21MessageFunc.clspGetMessage(glErrMsgCd) });
                                // QC#52941
                                setErrorMsg(NLGM0016E, new String[] {"NSZC001001", wmsInbdTrxPkString, wmsMdseCd, whCd, S21MessageFunc.clspGetMessage(glErrMsgCd) }, null, null, null, null, null, null, true);
                                // QC#29946 mod end
                                updErrTrxProcSts(wmsInbdTrxPk);
                                continue;
                            }
                        }
                    }
                    /*************************************************************
                     * 4. Update Process Status for Success Data
                     ************************************************************/
                    updTrxProcSts(wmsInbdTrxPk, PROC_STS.COMPLEATED, null);
                    successCount++;
                    commit();
                } // end of for
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * checkSerial
     * @param machMstrInfoMap
     * @param mdseCd
     * @param invtyLocCd
     * @param stsStsCd
     * @return
     */
    private boolean checkSerial(Map<String, Object> machMstrInfoMap, String mdseCd, String invtyLocCd, String stsStsCd) {

        // Allocated by other order
        if (ZYPCommonFunc.hasValue((String) machMstrInfoMap.get("TRX_HDR_NUM"))) {
            glErrMsgCd = NLZM2409E;
            return false;
        }
        if (ZYPConstant.FLG_OFF_N.equals((String) machMstrInfoMap.get("SVC_MACH_MAINT_AVAL_FLG"))) {
            glErrMsgCd = NLZM2409E;
            return false;
        }

        // That Merchandise Code is consistent with the WMS data taken
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            glErrMsgCd = NLZM2450E;
            return false;
        }
        if (!mdseCd.equals((String) machMstrInfoMap.get("MDSE_CD"))) {
            glErrMsgCd = NLZM2450E;
            return false;
        }

        // That the Current Location is consistent with the WMS data
        // taken
        if (!ZYPCommonFunc.hasValue(invtyLocCd)) {
            glErrMsgCd = NLBM1337E;
            return false;
        }
        if (!invtyLocCd.equals((String) machMstrInfoMap.get("CUR_LOC_NUM"))) {
            glErrMsgCd = NLBM1337E;
            return false;
        }

        // Stock Status (From Stock Status) that is consistent with
        // WMS data taken
        if (!ZYPCommonFunc.hasValue(stsStsCd)) {
            glErrMsgCd = NLZM2414E;
            return false;
        }
        if (!stsStsCd.equals((String) machMstrInfoMap.get("STK_STS_CD"))) {
            glErrMsgCd = NLZM2414E;
            return false;
        }

        // Status of Status is present in the WH (Created, Removed) it is
        if (!SVC_MACH_MSTR_STS.CREATED.equals((String) machMstrInfoMap.get("SVC_MACH_MSTR_STS_CD"))
                && !SVC_MACH_MSTR_STS.REMOVED.equals((String) machMstrInfoMap.get("SVC_MACH_MSTR_STS_CD"))) {
            glErrMsgCd = NLZM2410E;
            return false;
        }


        return true;
    }

    /**
     * setStkStsChgInfo
     * @param invtyOrdInfo
     * @param rs
     * @param isConfigSpec
     * @return
     * @throws SQLException
     */
    // START 2022/02/11 A.Marte [QC#59705, MOD]
    private Map<String, Object> setStkStsChgInfo(Map<String, Object> invtyOrdInfo, ResultSet rs, boolean isConfigSpec, boolean isRMAConfigSpec, String svcConfigMstrPk) throws SQLException {
    // END 2022/02/11 A.Marte [QC#59705, MOD]
        Map<String, Object> stkStsChgInfo = new HashMap<String, Object>();

        stkStsChgInfo.put("INVTY_ORD_NUM", (String) invtyOrdInfo.get("INVTY_ORD_NUM"));
        stkStsChgInfo.put("INVTY_LOC_CD", (String) invtyOrdInfo.get("INVTY_LOC_CD"));
        stkStsChgInfo.put("MDSE_CD", (String) invtyOrdInfo.get("MDSE_CD"));
        stkStsChgInfo.put("SER_NUM", (String) rs.getString("SER_NUM"));
        stkStsChgInfo.put("ORD_QTY", (BigDecimal) invtyOrdInfo.get("ORD_QTY"));
        stkStsChgInfo.put("FROM_STK_STS_CD", (String) invtyOrdInfo.get("FROM_STK_STS_CD"));
        stkStsChgInfo.put("TO_STK_STS_CD", (String) invtyOrdInfo.get("TO_STK_STS_CD"));
        stkStsChgInfo.put("RTL_WH_CD", (String) invtyOrdInfo.get("RTL_WH_CD"));
        stkStsChgInfo.put("SHPG_SER_TAKE_FLG", rs.getString("SHPG_SER_TAKE_FLG"));
        // QC#23845 MOD START
        // START 2022/02/11 A.Marte [QC#59705, MOD]
        if (isRMAConfigSpec) {
            stkStsChgInfo.put("SVC_CONFIG_MSTR_PK", new BigDecimal(svcConfigMstrPk));
        } else if (isConfigSpec) {
            stkStsChgInfo.put("SVC_CONFIG_MSTR_PK", rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
        }
        // END 2022/02/11 A.Marte [QC#59705, MOD]
        stkStsChgInfo.put("RMA_NUM", rs.getString("RMA_NUM"));
        // QC#23845 MOD END

        return stkStsChgInfo;
    }

    /**
     * updErrTrxProcSts
     * @param wmsInbdTrxPk
     */
    private void updErrTrxProcSts(BigDecimal wmsInbdTrxPk) {

        // Rollback for Transaction Data
        rollback();

        updTrxProcSts(wmsInbdTrxPk, PROC_STS.ERROR, glErrMsgCd);

        // Commit for Status Data
        commit();

        errorCount++;

        glErrMsgCd = null;

    }
    /**
     * execInventoryOrder
     * @param rs
     * @param isConfigSpec
     * @return
     * @throws SQLException
     */
    // START 2022/02/11 A.Marte [QC#59705, MOD]
    private String execInventoryOrder(ResultSet rs, boolean isConfigSpec, boolean isRMAConfigSpec, String svcConfigMstrPk) throws SQLException {
    // END 2022/02/11 A.Marte [QC#59705, MOD]

        List<NLZC003001PMsg> invtyOrdUpdApiPMsgList = new ArrayList<NLZC003001PMsg>();

        NLZC003001PMsg pMsg4Hdr = new NLZC003001PMsg();
        NLZC003001PMsg pMsg4Dtl = new NLZC003001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.xxDtTpCd, NLZC003001.DT_TP_HDR);
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.invtyOrdTpCd, INVTY_ORD_TP.STOCK_STATUS_CHANGE);

        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.invtyLocCd, rs.getString("RTL_WH_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.trxRsnCd, TRX_RSN.STOCK_STATUS_CHANGE_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.invtyOrdStsCd, INVTY_ORD_STS.APPROVED);
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        invtyOrdUpdApiPMsgList.add(pMsg4Hdr);

        ZYPEZDItemValueSetter.setValue(pMsg4Dtl.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg4Dtl.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
        ZYPEZDItemValueSetter.setValue(pMsg4Dtl.xxDtTpCd, NLZC003001.DT_TP_DTL);
        ZYPEZDItemValueSetter.setValue(pMsg4Dtl.invtyOrdTpCd, INVTY_ORD_TP.STOCK_STATUS_CHANGE);
        ZYPEZDItemValueSetter.setValue(pMsg4Dtl.invtyOrdLineNum, LINE_SUB_NUM_001);
        ZYPEZDItemValueSetter.setValue(pMsg4Dtl.invtyLocCd, rs.getString("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg4Dtl.mdseCd, rs.getString("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg4Dtl.stkStsCd, rs.getString("WMS_OLD_STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg4Dtl.toStkStsCd, rs.getString("STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg4Dtl.invtyLocCd_D1, rs.getString("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg4Dtl.locStsCd_D1, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pMsg4Dtl.ordQty, rs.getBigDecimal("WMS_PROC_QTY"));
        ZYPEZDItemValueSetter.setValue(pMsg4Dtl.invtyOrdDtlStsCd, INVTY_ORD_STS.APPROVED);
        // QC#23962
        // QC#23845 MOD START
        // START 2022/02/11 A.Marte [QC#59705, MOD]
        if (isRMAConfigSpec) {
            ZYPEZDItemValueSetter.setValue(pMsg4Dtl.svcConfigMstrPk, new BigDecimal(svcConfigMstrPk));
        } else if (isConfigSpec) {
            ZYPEZDItemValueSetter.setValue(pMsg4Dtl.svcConfigMstrPk, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
        }
        // START 2022/02/11 A.Marte [QC#59705, END]
        // QC#23845 MOD END

        if (ZYPConstant.FLG_ON_Y.equals(rs.getString("SHPG_SER_TAKE_FLG"))) {
            ZYPEZDItemValueSetter.setValue(pMsg4Dtl.serialInfoList.no(0).invtyOrdLineNum, LINE_SUB_NUM_001);
            ZYPEZDItemValueSetter.setValue(pMsg4Dtl.serialInfoList.no(0).serNum, rs.getString("SER_NUM"));
            pMsg4Dtl.serialInfoList.setValidCount(1);
        }
        invtyOrdUpdApiPMsgList.add(pMsg4Dtl);

        NLZC003001 invtyOrdUpdApi = new NLZC003001();
        invtyOrdUpdApi.execute(invtyOrdUpdApiPMsgList, ONBATCH_TYPE.BATCH);

        for (NLZC003001PMsg outPMsg : invtyOrdUpdApiPMsgList) {
            if (!S21ApiUtil.getXxMsgIdList(outPMsg).isEmpty()) {
                for (int j = 0; j < outPMsg.xxMsgIdList.length(); j++) {
                    String msgId = outPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    if (ZYPCommonFunc.hasValue(msgId)) {
                        if (msgId.endsWith("E")) {
                            glErrMsgCd = msgId;
                            return null;
                        }
                    }
                }
            }
        }

        return invtyOrdUpdApiPMsgList.get(0).invtyOrdNum.getValue();
    }

    /**
     * execDivAllocation
     * @param stkStsChgInfo
     * @return
     */
    private boolean execDivAllocation(Map<String, Object> stkStsChgInfo) {

        NWZC107001PMsg allocApiPMsg = new NWZC107001PMsg();

        ZYPEZDItemValueSetter.setValue(allocApiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxSysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxRqstTpCd, NWZC107001.REQ_TP_NEW);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxPrtlAcptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxRsnCd, TRX_RSN.STOCK_STATUS_CHANGE_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxLineSubNum, LINE_SUB_NUM_001);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.invtyLocCd, (String) stkStsChgInfo.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.stkStsCd, (String) stkStsChgInfo.get("FROM_STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxOrdTs, this.salesDt);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxHdrNum, (String) stkStsChgInfo.get("INVTY_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxLineNum, LINE_SUB_NUM_001);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.mdseCd, (String) stkStsChgInfo.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxRqstQty, (BigDecimal) stkStsChgInfo.get("ORD_QTY"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.slsDt, this.salesDt);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.rsdDt, this.salesDt);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxUnitPrc, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxSlsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.billToCustCd, (String) stkStsChgInfo.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.sellToCustCd, (String) stkStsChgInfo.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.shipToCustCd, (String) stkStsChgInfo.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
        NWZC107001 allocApi = new NWZC107001();
        allocApi.execute(allocApiPMsg, ONBATCH_TYPE.BATCH);

        if (!S21ApiUtil.getXxMsgIdList(allocApiPMsg).isEmpty()) {

            for (int j = 0; j < allocApiPMsg.xxMsgIdList.length(); j++) {
                String msgId = allocApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    if (msgId.endsWith("E")) {
                        glErrMsgCd = msgId;
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
     * execSvcMachMstrAllocation
     * @param stkStsChgInfo
     * @param isSerializedItem
     * @param isSerialSpec
     * @param isConfigSpec
     * @return
     * @throws SQLException
     */
    // START 2021/12/15 A.Marte [QC#59573, MOD]
    //private List<Map<String, Object>> execSvcMachMstrAllocation(Map<String, Object> stkStsChgInfo, boolean isSerializedItem, boolean isSerialSpec, boolean isConfigSpec , String wmsInbdTrxPkString , String wmsMdseCd , String whCd ) throws SQLException {
    private List<Map<String, Object>> execSvcMachMstrAllocation(Map<String, Object> stkStsChgInfo, boolean isSerializedItem, boolean isSerialSpec, boolean isConfigSpec , String wmsInbdTrxPkString , String wmsMdseCd , String whCd, String wmsInbdOrdNum) throws SQLException {
    // END 2021/12/15 A.Marte [QC#59573, MOD]
        String invtyOrdNum = (String) stkStsChgInfo.get("INVTY_ORD_NUM");
        String mdseCd = (String) stkStsChgInfo.get("MDSE_CD");
        String invtyLocCd = (String) stkStsChgInfo.get("INVTY_LOC_CD");
        String serNum = (String) stkStsChgInfo.get("SER_NUM");
        String wmsOldStkStsCd = (String) stkStsChgInfo.get("FROM_STK_STS_CD");
        String rmaNum = (String) stkStsChgInfo.get("RMA_NUM");

        // QC#23845 MOD START
        BigDecimal svcConfigMstrPk = null;
        if (stkStsChgInfo.containsKey("SVC_CONFIG_MSTR_PK")) {
            svcConfigMstrPk = (BigDecimal) stkStsChgInfo.get("SVC_CONFIG_MSTR_PK");
        }
        // QC#23845 MOD END

        BigDecimal wmsProcQty = (BigDecimal) stkStsChgInfo.get("ORD_QTY");

        List<Map<String, Object>> machMstrInfoList = new ArrayList<Map<String, Object>>();

        // Serialized Item?
        if (isSerializedItem) {
            // Serial specified ?
            if (isSerialSpec) {
                Map<String, Object> machMstrInfoMap = getSvcMachMstrInfo(serNum, mdseCd);
                if (machMstrInfoMap != null) {
                    // Validation Check
                    if (checkSerial(machMstrInfoMap, mdseCd, invtyLocCd, wmsOldStkStsCd)) {
                        // If Check all OK, the to Allocation the
                        // appropriate Machine Master.
                        machMstrInfoList.add(machMstrInfoMap);
                        execAlloc = true;
                    } else {
                        // If Status is not a Terminated
                        if (!SVC_MACH_MSTR_STS.TERMINATED.equals((String) machMstrInfoMap.get("SVC_MACH_MSTR_STS_CD"))) {
                            // Error
                            return null;
                        } else {
                            // the Status is a Terminated, so revive
                            // the Machine Master, to continue the
                            // process without the Error.
                            isTerminated = true;
                            machMstrInfoList.add(machMstrInfoMap);
                            return machMstrInfoList;
                        }
                    }
                    //
                } else {
                    // QC#18727 MOD START
                    glErrMsgCd = NLZM2500E;
                    return null;
                    // QC#18727 MOD END
                }

            } else {
                // If there is no Serial specified in Serialized Item
                // Error
                glErrMsgCd = NLZM2091E;
                return null;
            }
        } else { // Non-Serialized
            if (isConfigSpec) {
                // QC# 20121 Mod Start
                machMstrInfoList = searchConfig(svcConfigMstrPk, invtyLocCd, wmsOldStkStsCd, mdseCd , wmsProcQty);
                // QC# 20121 Mod End
                // QC#27137 MOD START------------
                if (machMstrInfoList.size() > 0) {
                    // If there is no data to be processed Qty content
//                    if (machMstrInfoList.size() != wmsProcQty.intValue()) {
//                        // Error
//                        this.glErrMsgCd = NLZM2430E;
//                        return null;
//                    }
                    // WMS process qty is greater than s21 machine qty.
                    if (machMstrInfoList.size() < wmsProcQty.intValue()) {
                        this.glErrMsgCd = NLGM0085W;
                    }
                    execAlloc = true;
                } else {
                    // Error
                    this.glErrMsgCd = NLZM2452E;
                    return null;
                }
                // QC#27137 MOD END ------------

            } else {
                // QC#18727 MOD START
                // QC#25644
                if (ZYPCommonFunc.hasValue(rmaNum)) {
                    // START 2021/12/15 A.Marte [QC#59573, MOD]
                    //machMstrInfoList = searchNonConfigRMA(mdseCd, invtyLocCd, wmsOldStkStsCd, wmsProcQty.intValue(), rmaNum);
                    machMstrInfoList = searchNonConfigRMA(mdseCd, invtyLocCd, wmsOldStkStsCd, wmsProcQty.intValue(), rmaNum, wmsInbdOrdNum);
                    // END 2021/12/15 A.Marte [QC#59573, MOD]
                    if (machMstrInfoList.size() == 0) {
                        machMstrInfoList = searchNonConfig(mdseCd, invtyLocCd, wmsOldStkStsCd, wmsProcQty.intValue());
                        if (machMstrInfoList.size() == 0) {
                            glErrMsgCd = NLZM2500E;
                            return null;
                        }
                    }
                } else {
                    machMstrInfoList = searchNonConfig(mdseCd, invtyLocCd, wmsOldStkStsCd, wmsProcQty.intValue());
                    if (machMstrInfoList.size() == 0) {
                        glErrMsgCd = NLZM2500E;
                        return null;
                    }
                }
                // QC#18727 MOD END
                execAlloc = true;
            }
        }

        if (execAlloc) {
            boolean apiErr = false;
            for (Map<String, Object> machMstrInfo : machMstrInfoList) {
                // Exec Allocation API
                if (!execSvcMachMstrAllocApi(invtyOrdNum, (BigDecimal) machMstrInfo.get("SVC_MACH_MSTR_PK"))) {
                    apiErr = true;
                    break;
                }
            }
            if (apiErr) {
                // QC#29946 mod start
                //outputErr(NLGM0016E, new String[] {"NSZC001001", glErrMsgCd, S21MessageFunc.clspGetMessage(glErrMsgCd) });
                // QC#52941
                setErrorMsg(NLGM0016E, new String[] {"NSZC001001", wmsInbdTrxPkString, wmsMdseCd, whCd, S21MessageFunc.clspGetMessage(glErrMsgCd) }, null, null, null, null, null, null, true);
                // QC#29946 mod end
                return null;
            }
        }
        return machMstrInfoList;
    }

    /**
     * execSvcMachMstrAllocApi
     * @param invtyOrdNum
     * @param svcMachMstrPk
     * @return
     * @throws SQLException
     */
    private boolean execSvcMachMstrAllocApi(String invtyOrdNum, BigDecimal svcMachMstrPk) throws SQLException {

        NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.slsDt, this.salesDt);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, ProcessMode.ALLOCATION_ON.code);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.trxHdrNum, invtyOrdNum);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.trxLineNum, LINE_SUB_NUM_001);

        NSZC001001 api = new NSZC001001();
        api.execute(svcMachMstrApiPMsg, ONBATCH_TYPE.BATCH);

        if (!S21ApiUtil.getXxMsgIdList(svcMachMstrApiPMsg).isEmpty()) {
            for (int j = 0; j < svcMachMstrApiPMsg.xxMsgIdList.length(); j++) {
                String msgId = svcMachMstrApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    if (msgId.endsWith("E")) {
                        glErrMsgCd = msgId;
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
     * execShippingPlanUpdate
     * @param stkStsChgInfo
     * @return
     * @throws SQLException
     */
    private boolean execShippingPlanUpdate(Map<String, Object> stkStsChgInfo) throws SQLException {

        List<NWZC003001PMsg> shpgPlnUpdApiPMsgList = new ArrayList<NWZC003001PMsg>();
        NWZC003001PMsg shpgPlnUpdApiPMsg = new NWZC003001PMsg();

        ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.shpgModeCd, NWZC003001.MODE_SHIPPINGREQUEST);
        ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.trxLineSubNum, LINE_SUB_NUM_001);
        ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.trxHdrNum, (String) stkStsChgInfo.get("INVTY_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.trxLineNum, LINE_SUB_NUM_001);
        ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.avalSoQty, (BigDecimal) stkStsChgInfo.get("ORD_QTY"));
        shpgPlnUpdApiPMsgList.add(shpgPlnUpdApiPMsg);

        NWZC003001 shpgPlnUpdApi = new NWZC003001();
        shpgPlnUpdApi.execute(shpgPlnUpdApiPMsgList, ONBATCH_TYPE.BATCH);

        for (NWZC003001PMsg outPMsg : shpgPlnUpdApiPMsgList) {
            if (!S21ApiUtil.getXxMsgIdList(outPMsg).isEmpty()) {
                for (int j = 0; j < outPMsg.xxMsgIdList.length(); j++) {
                    String msgId = outPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    if (ZYPCommonFunc.hasValue(msgId)) {
                        if (msgId.endsWith("E")) {
                            glErrMsgCd = msgId;
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * getShpgPlnNum
     * @param invtyOrdNum
     * @return
     */
    private String getShpgPlnNum(String invtyOrdNum) {

        List<NLXC023001PMsg> getShpgPlnNumPMsgList = new ArrayList<NLXC023001PMsg>();

        NLXC023001PMsg getShpgPlnNumPMsg = new NLXC023001PMsg();

        ZYPEZDItemValueSetter.setValue(getShpgPlnNumPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(getShpgPlnNumPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(getShpgPlnNumPMsg.trxHdrNum, invtyOrdNum);
        ZYPEZDItemValueSetter.setValue(getShpgPlnNumPMsg.trxLineNum, LINE_SUB_NUM_001);
        ZYPEZDItemValueSetter.setValue(getShpgPlnNumPMsg.trxLineSubNum, LINE_SUB_NUM_001);
        getShpgPlnNumPMsgList.add(getShpgPlnNumPMsg);
        NLXC023001 getShpgPlnNumApi = new NLXC023001();
        getShpgPlnNumApi.execute(getShpgPlnNumPMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(getShpgPlnNumPMsg).isEmpty()) {
            for (int j = 0; j < getShpgPlnNumPMsg.xxMsgIdList.length(); j++) {
                String msgId = getShpgPlnNumPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    if (msgId.endsWith("E")) {
                        glErrMsgCd = msgId;
                        return null;
                    }
                }
            }
        }

        String shpgPlnNum = getShpgPlnNumPMsg.shpgPlnNum.getValue();
        return shpgPlnNum;
    }

    /**
     * execShippingOrder
     * @param shpgPlnNum
     * @return
     */
    private String execShippingOrder(String shpgPlnNum) {

        List<NLZC205001PMsg> soApiPMsgList = new ArrayList<NLZC205001PMsg>();

        NLZC205001PMsg soApiPMsg = new NLZC205001PMsg();
        ZYPEZDItemValueSetter.setValue(soApiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(soApiPMsg.sceOrdTpCd, NLXSceConst.SCE_ORD_TP_CD_SC);
        ZYPEZDItemValueSetter.setValue(soApiPMsg.shpgFrceFlg, NLZC205001.SHPG_FRCE_FLG_OFF);
        ZYPEZDItemValueSetter.setValue(soApiPMsg.shpgPlnNum, shpgPlnNum); // Shipping
        ZYPEZDItemValueSetter.setValue(soApiPMsg.xxModeCd, NLZC205001.MODE_NEW);
        // Plan
        // Number

        soApiPMsgList.add(soApiPMsg);

        NLZC205001 soApi = new NLZC205001();
        soApi.execute(soApiPMsgList, ONBATCH_TYPE.BATCH);

        for (NLZC205001PMsg outPMsg : soApiPMsgList) {
            if (!S21ApiUtil.getXxMsgIdList(outPMsg).isEmpty()) {
                for (int j = 0; j < outPMsg.xxMsgIdList.length(); j++) {
                    String msgId = outPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    if (ZYPCommonFunc.hasValue(msgId)) {
                        if (msgId.endsWith("E")) {
                            glErrMsgCd = msgId;
                            return null;
                        }
                    }
                }
            }
        }
        return soApiPMsgList.get(0).soNum.getValue();
    }

    /**
     * execRWSCreation
     * @param soNum
     * @return
     */
    private String execRWSCreation(String soNum) {

        NLZC200001PMsg rwsApiPMsg = new NLZC200001PMsg();

        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.inbdSrcTpCd, INBD_SRC_TP.SO);
        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.soNum, soNum);

        NLZC200001 rwsApi = new NLZC200001();
        rwsApi.execute(rwsApiPMsg, ONBATCH_TYPE.BATCH);

        for (int i = 0; i < rwsApiPMsg.xxMsgIdList.length(); i++) {
            String msgId = rwsApiPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            if (ZYPCommonFunc.hasValue(msgId)) {
                if (msgId.endsWith("E")) {
                    glErrMsgCd = msgId;
                    return null;
                }
            }
        }
        return rwsApiPMsg.RWSNumList.no(0).rwsNum.getValue();

    }
    /**
     * execRwsPutAwayConf
     * @param rwsHdrTMsg
     * @param stkStsChgInfo
     * @return
     */
    private boolean execRwsPutAwayConf(RWS_HDRTMsg rwsHdrTMsg, Map<String, Object> stkStsChgInfo) {

        NLZC206001PMsg pMsg = new NLZC206001PMsg();

        // Set Param
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.salesDt);

        ZYPEZDItemValueSetter.setValue(pMsg.RWSPutAwayList.no(0).rwsNum, rwsHdrTMsg.rwsNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.RWSPutAwayList.no(0).rwsDtlLineNum, LINE_SUB_NUM_001);
        ZYPEZDItemValueSetter.setValue(pMsg.RWSPutAwayList.no(0).invtyStkStsCd, (String) stkStsChgInfo.get("TO_STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.RWSPutAwayList.no(0).sceOrdTpCd, rwsHdrTMsg.sceOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.RWSPutAwayList.no(0).rwsStkDtTmTs, ZYPDateUtil.getCurrentSystemTime(FORMAT_TIMESTAMP));
        ZYPEZDItemValueSetter.setValue(pMsg.RWSPutAwayList.no(0).rwsStkQty, (BigDecimal) stkStsChgInfo.get("ORD_QTY"));
        ZYPEZDItemValueSetter.setValue(pMsg.RWSPutAwayList.no(0).whCd, (String) stkStsChgInfo.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.RWSPutAwayList.no(0).mdseCd, (String) stkStsChgInfo.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.RWSPutAwayList.no(0).rwsRefNum, rwsHdrTMsg.rwsRefNum.getValue());

        // Set Serial
        int serCount = 0;
        if (ZYPConstant.FLG_ON_Y.equals((String) stkStsChgInfo.get("SHPG_SER_TAKE_FLG"))) {
            ZYPEZDItemValueSetter.setValue(pMsg.RcvSerNumList.no(0).rwsDtlLineNum, LINE_SUB_NUM_001);
            ZYPEZDItemValueSetter.setValue(pMsg.RcvSerNumList.no(0).serNum, (String) stkStsChgInfo.get("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.RcvSerNumList.no(0).mdseCd, (String) stkStsChgInfo.get("MDSE_CD"));
            serCount++;
        }

        pMsg.RWSPutAwayList.setValidCount(1);
        pMsg.RcvSerNumList.setValidCount(serCount);

        NLZC206001 putAwayApi = new NLZC206001();
        putAwayApi.execute(pMsg, ONBATCH_TYPE.BATCH);

        for (int i = 0; i < pMsg.xxMsgIdList.length(); i++) {
            String msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            if (ZYPCommonFunc.hasValue(msgId)) {
                if (msgId.endsWith("E")) {
                    glErrMsgCd = msgId;
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * execRwsCompletion
     * @param rwsHdrMap
     * @param stkStsChgInfo
     * @return
     */
    private boolean execRwsCompletion(RWS_HDRTMsg rwsHdrMap, Map<String, Object> stkStsChgInfo) {

        NLZC207001PMsg pMsg = new NLZC207001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.salesDt);
        ZYPEZDItemValueSetter.setValue(pMsg.rwsNum, rwsHdrMap.rwsNum.getValue());

        if (rwsHdrMap.sceOrdTpCd != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, rwsHdrMap.sceOrdTpCd.getValue());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.rwsCloDtTmTs, ZYPDateUtil.getCurrentSystemTime(FORMAT_TIMESTAMP));
        ZYPEZDItemValueSetter.setValue(pMsg.whCd, (String) stkStsChgInfo.get("RTL_WH_CD"));
        if (rwsHdrMap.rwsRefNum != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.rwsRefNum, rwsHdrMap.rwsRefNum.getValue());
        }
        NLZC207001 rwsApi = new NLZC207001();
        rwsApi.execute(pMsg, ONBATCH_TYPE.BATCH);

        for (int i = 0; i < pMsg.xxMsgIdList.length(); i++) {
            String msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            if (ZYPCommonFunc.hasValue(msgId)) {
                if (msgId.endsWith("E")) {
                    glErrMsgCd = msgId;
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * execSerialTransaction
     * @param stkStsChgInfo
     * @param soNum
     * @return
     */
    private boolean execSerialTransaction(Map<String, Object> stkStsChgInfo, String soNum) {

        NLZC302001PMsg serTrxApipMsg = new NLZC302001PMsg();
        ZYPEZDItemValueSetter.setValue(serTrxApipMsg.glblCmpyCd, this.glblCmpyCd);

        ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(0).serNum, (String) stkStsChgInfo.get("SER_NUM"));
        ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(0).mdseCd, (String) stkStsChgInfo.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(0).serTrxTs, ZYPDateUtil.getCurrentSystemTime(FORMAT_TIMESTAMP));
        ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(0).serTrxSrcTpCd, SER_TRX_SRC_TP.SO);
        ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(0).serTrxSrcHdrNum, soNum);
        ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(0).serTrxSrcLineNum, LINE_SUB_NUM_001);
        ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(0).serTrxRefNum, (String) stkStsChgInfo.get("INVTY_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(0).fromLocCd, (String) stkStsChgInfo.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(0).toLocCd, (String) stkStsChgInfo.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(0).manCratFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(0).locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(0).serTrxEventCd, SER_TRX_EVENT.STOCK_STATUS_CHANGE);
        ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(0).toStkStsCd, (String) stkStsChgInfo.get("TO_STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(0).fromStkStsCd, (String) stkStsChgInfo.get("FROM_STK_STS_CD"));

        serTrxApipMsg.UpdateDetailList.setValidCount(1);

        NLZC302001 serTranApi = new NLZC302001();
        serTranApi.execute(serTrxApipMsg, ONBATCH_TYPE.BATCH);

        for (int i = 0; i < serTrxApipMsg.xxMsgIdList.length(); i++) {
            String msgId = serTrxApipMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            if (ZYPCommonFunc.hasValue(msgId)) {
                if (msgId.endsWith("E")) {
                    glErrMsgCd = msgId;
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * execSvcMachMstrAllocationOff
     * @param invtyOrdNum
     * @param machMstrInfo
     * @return
     */
    private boolean execSvcMachMstrAllocationOff(String invtyOrdNum, Map<String, Object> machMstrInfo) {

        NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.slsDt, this.salesDt);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrPk, (BigDecimal) machMstrInfo.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);

        NSZC001001 api = new NSZC001001();
        api.execute(svcMachMstrApiPMsg, ONBATCH_TYPE.BATCH);

        if (!S21ApiUtil.getXxMsgIdList(svcMachMstrApiPMsg).isEmpty()) {
            for (int j = 0; j < svcMachMstrApiPMsg.xxMsgIdList.length(); j++) {
                String msgId = svcMachMstrApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    if (msgId.endsWith("E")) {
                        glErrMsgCd = msgId;
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
     * execSvcMachMstrUpdateInventry
     * @param invtyOrdNum
     * @param machMstrInfo
     * @param stkStsChgInfo
     * @return
     */
    private boolean execSvcMachMstrUpdateInventry(String invtyOrdNum, Map<String, Object> machMstrInfo, Map<String, Object> stkStsChgInfo) {

        NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.slsDt, this.salesDt);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, ProcessMode.UPDATE_INVENTORY.code);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrPk, (BigDecimal) machMstrInfo.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcConfigMstrPk, (BigDecimal) machMstrInfo.get("SVC_CONFIG_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrStsCd, (String) machMstrInfo.get("SVC_MACH_MSTR_STS_CD"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.curLocNum, (String) machMstrInfo.get("CUR_LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.stkStsCd, (String) stkStsChgInfo.get("TO_STK_STS_CD"));

        NSZC001001 api = new NSZC001001();
        api.execute(svcMachMstrApiPMsg, ONBATCH_TYPE.BATCH);

        if (!S21ApiUtil.getXxMsgIdList(svcMachMstrApiPMsg).isEmpty()) {
            for (int j = 0; j < svcMachMstrApiPMsg.xxMsgIdList.length(); j++) {
                String msgId = svcMachMstrApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    if (msgId.endsWith("E")) {
                        glErrMsgCd = msgId;
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
     * execSvcMachMstrUpdateWh
     * @param stkStsChgInfo
     * @param machMstrInfo
     * @return
     */
    private boolean execSvcMachMstrUpdateWh(Map<String, Object> stkStsChgInfo, Map<String, Object> machMstrInfo) {

        NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.slsDt, this.salesDt);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, ProcessMode.UPDATE_WAREHOUSE.code);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrPk, (BigDecimal) machMstrInfo.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.serNum, (String) stkStsChgInfo.get("SER_NUM"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.mdseCd, (String) stkStsChgInfo.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.effFromDt, this.salesDt);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.curLocNum, (String) stkStsChgInfo.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachQty, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.stkStsCd, (String) stkStsChgInfo.get("TO_STK_STS_CD"));

        NSZC001001 api = new NSZC001001();
        api.execute(svcMachMstrApiPMsg, ONBATCH_TYPE.BATCH);

        if (!S21ApiUtil.getXxMsgIdList(svcMachMstrApiPMsg).isEmpty()) {
            for (int j = 0; j < svcMachMstrApiPMsg.xxMsgIdList.length(); j++) {
                String msgId = svcMachMstrApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    if (msgId.endsWith("E")) {
                        glErrMsgCd = msgId;
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
     * execSvcMachMstrItemChange
     * @param stkStsChgInfo
     * @param machMstrInfo
     * @return
     */
    private boolean execSvcMachMstrItemChange(Map<String, Object> stkStsChgInfo, Map<String, Object> machMstrInfo) {

        NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.slsDt, this.salesDt);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, ProcessMode.ITEM_CHANGE.code);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrPk, (BigDecimal) machMstrInfo.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcConfigMstrPk, (BigDecimal) machMstrInfo.get("SVC_CONFIG_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.serNum, (String) stkStsChgInfo.get("SER_NUM"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.mdseCd, (String) stkStsChgInfo.get("MDSE_CD"));

        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.effFromDt, this.salesDt);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachQty, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.curLocNum, (String) stkStsChgInfo.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.stkStsCd, (String) stkStsChgInfo.get("TO_STK_STS_CD"));

        NSZC001001 api = new NSZC001001();
        api.execute(svcMachMstrApiPMsg, ONBATCH_TYPE.BATCH);

        if (!S21ApiUtil.getXxMsgIdList(svcMachMstrApiPMsg).isEmpty()) {
            for (int j = 0; j < svcMachMstrApiPMsg.xxMsgIdList.length(); j++) {
                String msgId = svcMachMstrApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    if (msgId.endsWith("E")) {
                        glErrMsgCd = msgId;
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * getSvcMachMstr
     * @param serNum
     * @param mdseCd
     * @return
     */
    private Map<String, Object> getSvcMachMstrInfo(String serNum, String mdseCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("serNum", serNum);
        queryParam.put("mdseCd", mdseCd);
        Map<String, Object> ret = (Map<String, Object>) this.ssmBatchClient.queryObject("getSvcMachMstrInfo", queryParam);
        return ret;
    }

    /**
     * getInvtyOrdInfo
     * @param invtyOrdNum
     * @return
     */
    private List<Map<String, Object>> getInvtyOrdInfo(String invtyOrdNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invtyOrdNum", invtyOrdNum);
        List<Map<String, Object>> ret = this.ssmBatchClient.queryObjectList("getInvtyOrdInfo", queryParam);
        return ret;
    }

    /**
     * searchConfig
     * @param configId
     * @param invtyLocCd
     * @param wmsOldStkStsCd
     * @return
     */
    private List<Map<String, Object>> searchConfig(BigDecimal configId, String invtyLocCd, String wmsOldStkStsCd, String mdse_cd , BigDecimal wmsProcQty) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("configId", configId);
        queryParam.put("svcMachMstrStsCrat", SVC_MACH_MSTR_STS.CREATED);
        queryParam.put("svcMachMstrStsRmv", SVC_MACH_MSTR_STS.REMOVED);
        queryParam.put("invtyLocCd", invtyLocCd);
        queryParam.put("wmsOldStkStsCd", wmsOldStkStsCd);
        queryParam.put("mdseCd", mdse_cd);
        // QC#27137 ADD START
        queryParam.put("rowNum", wmsProcQty);
        // QC#27137 ADD END
        List<Map<String, Object>> ret = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("searchConfig", queryParam);
        return ret;
    }

    /**
     * searchNonConfig
     * @param mdseCd
     * @param invtyLocCd
     * @param wmsOldStkStsCd
     * @return
     */
//    private List<Map<String, Object>> searchNonConfig(String mdseCd, String invtyLocCd, String wmsOldStkStsCd) {
    private List<Map<String, Object>> searchNonConfig(String mdseCd, String invtyLocCd, String wmsOldStkStsCd, int wmsProcQty) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("svcMachMstrStsCrat", SVC_MACH_MSTR_STS.CREATED);
        queryParam.put("svcMachMstrStsRmv", SVC_MACH_MSTR_STS.REMOVED);
        queryParam.put("invtyLocCd", invtyLocCd);
        queryParam.put("wmsOldStkStsCd", wmsOldStkStsCd);
        // QC#16804 Mod.
        queryParam.put("wmsProcQty", wmsProcQty);
        List<Map<String, Object>> ret = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("searchNonConfig", queryParam);
        return ret;
    }

    /**
     * searchNonConfigRMA
     * @param mdseCd
     * @param invtyLocCd
     * @param wmsOldStkStsCd
     * @param rmaNum
     * @return
     */
    // START 2021/12/15 A.Marte [QC#59573, MOD] 
    //private List<Map<String, Object>> searchNonConfigRMA(String mdseCd, String invtyLocCd, String wmsOldStkStsCd, int wmsProcQty, String rmaNum) {
    private List<Map<String, Object>> searchNonConfigRMA(String mdseCd, String invtyLocCd, String wmsOldStkStsCd, int wmsProcQty, String rmaNum, String inbdOrdNum) {
    // END 2021/12/15 A.Marte [QC#59573, MOD]
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("svcMachMstrStsRmv", SVC_MACH_MSTR_STS.REMOVED);
        queryParam.put("invtyLocCd", invtyLocCd);
        queryParam.put("wmsOldStkStsCd", wmsOldStkStsCd);
        queryParam.put("wmsProcQty", wmsProcQty);
        queryParam.put("rmaNum", rmaNum);
        // START 2021/12/15 A.Marte [QC#59573, ADD]
        queryParam.put("rmaMatch", ZYPConstant.FLG_OFF_N);

        if (isMatchingRMAInbdOrdNum(rmaNum, inbdOrdNum)) {
            queryParam.put("rmaMatch", ZYPConstant.FLG_ON_Y);
        }
        // END 2021/12/15 A.Marte [QC#59573, ADD]

        List<Map<String, Object>> ret = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("searchNonConfigRMA", queryParam);
        return ret;
    }

    /**
     * getResHdr
     * @param rwsNum
     * @return
     */
    private RWS_HDRTMsg getRwsHdr(String rwsNum) {
        RWS_HDRTMsg inMsg = new RWS_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rwsNum, rwsNum);
        RWS_HDRTMsg outMsg = (RWS_HDRTMsg) EZDTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    /**
     * Check exist error status received WMS_INBOUND_TRX data.
     * when error date is still exist before interval time.
     * then return false. else true. Mod QC#52941
     * @return 0:not skip / 1:skip / 2:expired
     */
    private int chkErrRcvDataSkip(String whCd, String wmsMdseCd, String wmsOldStkStsCd, String ezIntime, String serNum, String mdseCd, String invtyLocCd, List<String> skipKeyList, List<String> inbdOrdList) throws SQLException {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("whCd", whCd);
        queryParam.put("wmsMdseCd", wmsMdseCd);
        queryParam.put("wmsOldStxStsCd", wmsOldStkStsCd);
        queryParam.put("procStsCd", PROC_STS.ERROR);
        queryParam.put("rcvd", WMS_TASK.RCVD);

        // QC#31012
        if (ZYPCommonFunc.hasValue(serNum)) {
            queryParam.put("serNum", serNum);
        }

        List<Map<String, Object>> ret = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("findErrRvcWmsInbdTrx", queryParam);

        if (ret == null || ret.isEmpty()) {
            return NOT_SKIP;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(whCd);
        sb.append(wmsMdseCd);
        sb.append(wmsOldStkStsCd);
        if(ZYPCommonFunc.hasValue(serNum)) {
            sb.append(serNum);
        }
        String sscSkipKey = sb.toString();

        boolean isExpired = false;

        for (int i = 0; i < ret.size(); i++) {

            Map<String, Object> rcvdErrMap = ret.get(i);
            BigDecimal wmsInbdTrxPk = (BigDecimal) rcvdErrMap.get("WMS_INBD_TRX_PK");
            String inbdOrdNum = (String) rcvdErrMap.get("INBD_ORD_NUM");
            BigDecimal inbdOrdLineNum = (BigDecimal) rcvdErrMap.get("INBD_ORD_LINE_NUM");
            String rcvdSerNum = (String) rcvdErrMap.get("SER_NUM");
            BigDecimal wmsProcQty = (BigDecimal) rcvdErrMap.get("WMS_PROC_QTY");

            if (ZYPCommonFunc.hasValue(wmsInbdTrxPk) && this.skipIntvlTime.compareTo(ezIntime) < 0) {
                // QC#52941
                isExpired = true;

                if (!skipKeyList.contains(sscSkipKey)) {
                    skipKeyList.add(sscSkipKey);
                }

                setErrorMsg(null, null, inbdOrdNum, inbdOrdLineNum, invtyLocCd, mdseCd, rcvdSerNum, wmsProcQty, false);
                updTrxProcSts(wmsInbdTrxPk, PROC_STS.COMPLEATED, null);

                if (inbdOrdList.contains(inbdOrdNum)) {
                    continue;
                }

                // PCLS Compleated
                queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", glblCmpyCd);
                queryParam.put("whCd", whCd);
                queryParam.put("inbdOrdNum",inbdOrdNum);
                queryParam.put("pcls", WMS_TASK.PCLS);

                BigDecimal pclsPk = (BigDecimal) this.ssmBatchClient.queryObject("findSkipPCLSWmsInbdTrx", queryParam);

                if (ZYPCommonFunc.hasValue(pclsPk)) {
                    updTrxProcSts(pclsPk, PROC_STS.COMPLEATED, null);
                    inbdOrdList.add(inbdOrdNum);
                }
            }
        }

        if (isExpired) {
            return EXPIRED;
        }

        return SKIP;
       
    }

    /**
     * isNumber
     * @param val
     * @return
     */
    private boolean isNumber(String val) {

        String regex = "\\A[-]?[0-9]+\\z";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(val);
        return m.find();
    }

    // START 2018/05/08 S.Katsuma [QC#25913,ADD]
    /**
     * Convert WMS Reason Code to Adjustment Category Code
     * @param String wmsRsnCd
     * @param String mdseCd
     * @return ADJ_CATGTMsg
     */
    private String convWmsRsnCdToAdjCatgCd(String wmsRsnCd, String mdseCd) {
        String val = null;

        if (wmsRsnCd != null && wmsRsnCd.length() < 3) {
            ADJ_CATGTMsg adjCatgTMsg = getAdjCatg(wmsRsnCd);
            if (adjCatgTMsg != null) {
                return wmsRsnCd;
            }
        }

        Map<String, Object> partsOrItemCd = getPartsOrItem(mdseCd);
        if (partsOrItemCd == null || partsOrItemCd.isEmpty()) {
            val = ADJ_CATG.MERCH_CYCLE_COUNT;
        } else {
            val = ADJ_CATG.PARTS_CYCLE_COUNT;
        }

        return val;
    }

    /**
     * Get Adjustment Category
     * @param String adjCatgCd
     * @return ADJ_CATGTMsg
     */
    private ADJ_CATGTMsg getAdjCatg(String adjCatgCd) {
        ADJ_CATGTMsg adjCatgTMsg = new ADJ_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(adjCatgTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(adjCatgTMsg.adjCatgCd, adjCatgCd);

        return (ADJ_CATGTMsg) S21ApiTBLAccessor.findByKey(adjCatgTMsg);
    }

    /**
     * Get Parts Or Item
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return Map<String, Object>
     */
    private Map<String, Object> getPartsOrItem(String mdseCd) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("partsItem", MDSE_TP_CTX_TP.PARTS_ITEM);
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getMdseOrPartsCd", queryParam);

        return result;
    }
    // END 2018/05/08 S.Katsuma [QC#25913,ADD]

    // QC#56071 2020/05/15 Start
    private String GetCycleCountResounComments(String cycleCountResounCd, String cycleCountResounComments){
        String retValue = null;

        if (ADJ_CATG.OTHER.equals(cycleCountResounCd) && !ZYPCommonFunc.hasValue(cycleCountResounComments)) {
            retValue = NLGB014001Constant.ADUST_RESON_DEFAULT_COMMENTS;
        }
        else {
            retValue = cycleCountResounComments;
        }
        return retValue;
    }
     // QC#56071 2020/05/15 Start

    // Add QC#52941 Start
    private void setErrorMsg(String msgId, String[] msgParam, String inbdOrdNum, BigDecimal inbdOrdLineNum, String invtyLocCd, String mdseCd, String serNum, BigDecimal wmsProcQty, boolean isApiError) {
        StringBuilder builder = new StringBuilder();

        Map<String, String> mailParam = new HashMap<String, String>();

        if (isApiError) {
            builder.append(generateApiErrorMailMsg(msgId, msgParam));
            mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
            mailParam.put(NLXMailSend.KEY_MESSAGE, builder.toString());
            errMsgList.add(mailParam);
            S21InfoLogOutput.println(msgId, msgParam);
        } else {
            builder.append(generateRcvdSkipMailMsg(inbdOrdNum, inbdOrdLineNum, invtyLocCd, mdseCd, serNum, wmsProcQty));
            mailParam.put(NLXMailSend.KEY_MESSAGE_ID, NLBM1018E);
            mailParam.put(NLXMailSend.KEY_MESSAGE, builder.toString());
            errMsgList.add(mailParam);
            S21InfoLogOutput.println(builder.toString());
        }
    }

    private String generateApiErrorMailMsg(String msgId, String[] msgParam) {
        StringBuilder builder = new StringBuilder();
        builder.append(S21MessageFunc.clspGetMessage(msgId, msgParam));
        return builder.toString();
    }

    private String generateRcvdSkipMailMsg(String inbdOrdNum, BigDecimal inbdOrdLineNum, String invtyLocCd, String mdseCd, String serNum, BigDecimal wmsProcQty) {
        StringBuilder builder = new StringBuilder();
        builder.append(skipRcvdMailHdr); // VAR_CHAR
        builder.append(NLGB014001Constant.LINE_SEPT);
        builder.append(NLGB014001Constant.VAL_BLANK_14);
        builder.append("Receipt Order#  : ");
        builder.append(inbdOrdNum);
        builder.append(NLGB014001Constant.LINE_SEPT);
        builder.append(NLGB014001Constant.VAL_BLANK_14);
        builder.append("Line#           : ");
        if (ZYPCommonFunc.hasValue(inbdOrdLineNum)) {
            String lineNum = ZYPCommonFunc.leftPad(inbdOrdLineNum.toPlainString(), 3, "0");
            builder.append(lineNum);
        } else {
            builder.append("");
        }
        builder.append(NLGB014001Constant.LINE_SEPT);
        builder.append(NLGB014001Constant.VAL_BLANK_14);
        builder.append("Warehouse       : ");
        builder.append(invtyLocCd);
        builder.append(NLGB014001Constant.LINE_SEPT);
        builder.append(NLGB014001Constant.VAL_BLANK_14);
        if (ZYPCommonFunc.hasValue(serNum)) {
            builder.append("Item# / Serial# : ");
            builder.append(mdseCd);
            builder.append(" / ");
            builder.append(serNum);
        } else {
            builder.append("Item#           : ");
            builder.append(mdseCd);
        }
        builder.append(NLGB014001Constant.LINE_SEPT);
        builder.append(NLGB014001Constant.VAL_BLANK_14);
        builder.append("Qty             : ");
        builder.append(wmsProcQty);
        builder.append(NLGB014001Constant.LINE_SEPT);
        builder.append(NLGB014001Constant.VAL_BLANK_14);
        builder.append("Next Action     : ");
        builder.append(skipRcvdMailNext); // VAR_CHAR
        builder.append(NLGB014001Constant.LINE_SEPT);
        builder.append(NLGB014001Constant.VAL_SEP_LINE);
        return builder.toString();
    }
    // Add QC#52941 End
    
        /**
     * getSvcMachMstr
     * @param serNum
     * @param mdseCd
     * @return
     */
    private Map<String, Object> getAvailNonConfig(String serNum, String mdseCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("serNum", serNum);
        queryParam.put("mdseCd", mdseCd);
        Map<String, Object> ret = (Map<String, Object>) this.ssmBatchClient.queryObject("getSvcMachMstrInfo", queryParam);
        return ret;
    }

    // START 2021/07/15 A.Marte [QC#58480, ADD]
    /**
     * getConfigItems
     * @param svcConfigMstrPk
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<String> getConfigItems(String svcConfigMstrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("svcConfigMstrPk", svcConfigMstrPk);
        queryParam.put("svcMachMaintAvalFlg", MACH_AVAIL_FLG);
        queryParam.put("svcMachMstrStsCrat", SVC_MACH_MSTR_STS.CREATED);
        queryParam.put("svcMachMstrStsRmv", SVC_MACH_MSTR_STS.REMOVED);
        queryParam.put("dcStock", LOC_STS.DC_STOCK);

        List<String> mdseList = this.ssmBatchClient.queryObjectList("getConfigItems", queryParam);
        List<String> configMdseList = new ArrayList<String>();

        if(mdseList != null && !mdseList.isEmpty()){
            configMdseList = mdseList;
        }

        return configMdseList;
    }
    // END 2021/07/15 A.Marte [QC#58480, ADD]

    // START 2021/12/15 A.Marte [QC#59573, ADD]
    /**
     * isMatchingRMAInbdOrdNum
     * @param rmaNum
     * @param inbdOrdNum
     * @return
     */
    private boolean isMatchingRMAInbdOrdNum(String rmaNum, String inbdOrdNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("rmaNum", rmaNum);
        queryParam.put("inbdOrdNum", inbdOrdNum);
        if (!ZYPCommonFunc.hasValue(inbdOrdNum)) {
            queryParam.put("inbdOrdNum", "");
        }

        BigDecimal ret = (BigDecimal) this.ssmBatchClient.queryObject("isMatchingRMAInbdOrdNum", queryParam);

        return (ret.intValue() > 0);

    }

     /**
     * isSCEReturnType
     * @param inbdOrdNum
     * @return
     */
    private boolean isSCEReturnType(String inbdOrdNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("return", SCE_ORD_TP.RETURN);
        queryParam.put("inbdOrdNum", inbdOrdNum);

        BigDecimal ret = (BigDecimal) this.ssmBatchClient.queryObject("isSCEReturnType", queryParam);

        return (ret.intValue() > 0);

    }
    // END 2021/12/15 A.Marte [QC#59573, ADD]

    // START 2022/02/11 A.Marte [QC#59705, ADD]
    /**
     * getConfigFromRMAMatchLine
     * @param rs
     * @param rmaNum
     * @return
     * @throws SQLException
     */
    private String getConfigFromRMAMatchLine(ResultSet rs, String rmaNum, boolean isMatchingRMAConfig, boolean isSerialSpec) throws SQLException {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("mdseCd", rs.getString("MDSE_CD"));
        queryParam.put("svcMachMstrStsRmv", SVC_MACH_MSTR_STS.REMOVED);
        queryParam.put("invtyLocCd", rs.getString("INVTY_LOC_CD"));
        queryParam.put("wmsOldStkStsCd", rs.getString("WMS_OLD_STK_STS_CD"));
        queryParam.put("wmsProcQty", rs.getBigDecimal("WMS_PROC_QTY").intValue());
        queryParam.put("rmaNum", rmaNum);

        if (isSerialSpec) {
            queryParam.put("serNum", rs.getString("SER_NUM"));
            if (!isMatchingRMAConfig) {
                queryParam.put("rmaNum", "");
            }
        }

        String svcConfigMasterPk = (String) this.ssmBatchClient.queryObject("getConfigFromRMAMatchLine", queryParam);
        if (!ZYPCommonFunc.hasValue(svcConfigMasterPk)) {
            svcConfigMasterPk = null;
        }
        return svcConfigMasterPk;
    }
    // END 2022/02/11 A.Marte [QC#59705, ADD]
}
