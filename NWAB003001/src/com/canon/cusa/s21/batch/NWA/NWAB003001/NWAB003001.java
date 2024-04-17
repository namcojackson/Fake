/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 *
 * Validation Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * -----------------------------------------------------------------------------
 * 08/27/2009   Fujitsu         N.Sakamoto      Create          N/A
 * 10/26/2009   Fujitsu         N.Sakamoto      Update          N/A
 * 11/12/2009   Fujitsu         K.Kato          Update          1760
 * 12/03/2009   Fujitsu         S.Sugino        Update          N/A
 * 12/08/2009   Fujitsu         T.Kaneda        Update          N/A
 * 12/29/2009   Fujitsu         K.Tajima        Update          N/A (refactoring)
 * 01/28/2009   Fujitsu         M.Nakamura      Update          3143,3258
 * 02/08/2009   Fujitsu         A.Suda          Update          N/A
 * 03/02/2010   Fujitsu         T.Kaneda        Update          3589
 * 03/30/2010   Fujitsu         T.Kaneda        Update          N/A (message modify)  
 * 03/30/2010   Fujitsu         H.Nagashima     Update          5181
 * 05/10/2010   Fujitsu         K.Tajima        Update          2663 (performance tuning)
 * 05/26/2010   Fujitsu         A.Suda          Update          6538 
 * 06/21/2010   Fujitsu         A.Suda          Update          7100 
 * 06/21/2010   Fujitsu         A.Suda          Update          7296  
 * 07/08/2010   Fujitsu         K.Tajima        Update          7691
 * 07/22/2010   Fujitsu         A.Suda          Update          31   (All2)
 * 08/06/2010   Fujitsu         K.Tajima        Update          200  (All2)
 * 09/07/2010   Fujitsu         A.Suda          Update          412  (All2)
 * 11/20/2010   Fujitsu         S.Yamamoto      Update          540  (All2)
 * 03/03/2011   Fujitsu         K.Tajima        Update          1762 (Prod)
 * 04/18/2010   Fujitsu         S.Yamamoto      Update          1481 (PROD)
 * 05/23/2013   Fujitsu         S.Yamamoto      Update          #R-OM004
 * 12/19/2014   Fujitsu         S.Tsunaki       Update          CCH-QC98
 * 12/24/2014   Fujitsu         S.Tsunaki       Update          CCH-QC98
 * 07/26/2016   Hitachi         Y.Takeno        Update          S21_NA#10948
 * 08/17/2016   SRAA            K.Aratani       Update          S21_NA#13446
 * 08/24/2016   SRAA            K.Aratani       Update          S21_NA#13714
 * 09/21/2016   Fujitsu         M.Ohno          Update          S21_NA#14646
 * 10/12/2016   Fujitsu         M.Ohno          Update          S21_NA#15097
 * 10/18/2016   Fujitsu         T.Yoshida       Update          S21_NA#14973
 * 2017/06/02   Fujitsu         M.Yamada        Update          S21_NA#17941
 * 12/18/2018   Fujitsu         Y.Kanefusa      Update          S21_NA#29655
 * 
 *</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB003001;


import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.common.S21StringUtil.isEquals;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKey;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKeyForUpdate;
import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static tools.ezdcommon.EZDMessageFunc.clspGetMessage;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map.Entry;

import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CTRYTMsg;
import business.db.MDSETMsg;
import business.db.S21_ORGTMsg;
import business.db.SHPG_PLNTMsg;
import business.db.STTMsg;
import business.parts.NWXC005001PMsg;
import business.parts.NWZC044001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC010001.NWZC010001;
import com.canon.cusa.s21.api.NWZ.NWZC017001.NWZC017001;
import com.canon.cusa.s21.api.NWZ.NWZC018001.NWZC018001;
import com.canon.cusa.s21.api.NWZ.NWZC019001.NWZC019001;
import com.canon.cusa.s21.api.NWZ.NWZC044001.NWZC044001;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Administration;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Country;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.OutputFormat;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Parameter;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Parameters;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Party;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Request;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.ServiceRequest;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.ShipFromCountry;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.ShipToCountry;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Transaction;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.TransactionLine;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Service.Svc;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.response.DeniedParty;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.response.Error;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.response.Response;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.response.Service;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.response.ServiceResponse;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.wrapper.S21ChileKewillPartyScreeningServiceProxy;

/**
 * NWAB0030 Validation Batch
 */
public class NWAB003001 extends S21BatchMain implements HLD_RSN, HLD_LVL {

//    /** SQL_ID : lock CPO with timestamp. */
//    private static final String SQL_ID_LOCK_CPO_WITH_TIMESTAMP = "008";
//
//    /** SQL_ID : lock CPO_DTL with timestamp. */
//    private static final String SQL_ID_LOCK_CPO_DTL_WITH_TIMESTAMP = "019";

    private static final String NWAM0103E = "NWAM0103E";
    private static final String NWAM0188I = "NWAM0188I";
    private static final String NWAM0189E = "NWAM0189E";
    private static final String NWAM0193I = "NWAM0193I";
    private static final String NWAM0195I = "NWAM0195I";
    private static final String NWAM0310I = "NWAM0310I";
    private static final String NWZM0901W = "NWZM0901W";
    // 2016/09/21 QC#14646 Mod Start
//    private static final String NWZM0902W = "NWZM0902W";
//    private static final String NWZM0903W = "NWZM0903W";
    // 2016/09/21 QC#14646 Mod End
    private static final String NWZM0904W = "NWZM0904W";
    private static final String NWZM0905W = "NWZM0905W";
    private static final String NWAM0196E = "NWAM0196E";

    private static final String P_CPO_ORD_NUM_I             = "cpoOrdNum_I";
    private static final String P_CPO_DTL_LINE_NUM_I        = "cpoDtlLineNum_I";
    private static final String P_CPO_DTL_LINE_SUB_NUM_I    = "cpoDtlLineSubNum_I";
    private static final String P_SHPG_PLN_NUM_I            = "shpgPlnNum_I";
    private static final String P_MDSE_CD                   = "MDSE_CD";
    private static final String P_SET_MDSE_CD               = "SET_MDSE_CD";
    private static final String P_BILL_TO_CUST_CD           = "BILL_TO_CUST_CD";
    private static final String P_SELL_TO_CUST_CD           = "SELL_TO_CUST_CD";
    private static final String P_DROP_SHIP_FLG             = "DROP_SHIP_FLG";
    private static final String P_SHIP_TO_CUST_CD           = "SHIP_TO_CUST_CD";
    private static final String P_SHIP_TO_LOC_NM            = "SHIP_TO_LOC_NM";
    private static final String P_SHIP_TO_FIRST_LINE_ADDR   = "SHIP_TO_FIRST_LINE_ADDR";
    private static final String P_SHIP_TO_SCD_LINE_ADDR     = "SHIP_TO_SCD_LINE_ADDR";
    private static final String P_SHIP_TO_THIRD_LINE_ADDR   = "SHIP_TO_THIRD_LINE_ADDR";
    private static final String P_SHIP_TO_FRTH_LINE_ADDR    = "SHIP_TO_FRTH_LINE_ADDR";
    private static final String P_SHIP_TO_CTY_ADDR          = "SHIP_TO_CTY_ADDR";
    private static final String P_SHIP_TO_ST_CD             = "SHIP_TO_ST_CD";
    private static final String P_SHIP_TO_POST_CD           = "SHIP_TO_POST_CD";
    private static final String P_SHIP_TO_CTRY_CD           = "SHIP_TO_CTRY_CD";
    private static final String P_SHIP_TO_PROV_NM           = "SHIP_TO_PROV_NM";
    private static final String P_SHIP_TO_CNTY_NM           = "SHIP_TO_CNTY_NM";
    private static final String P_ST_NM                     = "ST_NM";
    private static final String P_EX_REQ_FLG                = "EX_REQ_FLG";
    private static final String P_DPL_FLG                   = "DPL_FLG";
    private static final String P_EBG_FLG                   = "EBG_FLG";
    private static final String P_RSN_CD                    = "RSN_CD";
    private static final String P_DPL_RSN_TXT               = "DPL_RSN_TXT";
    private static final String P_DPL_TARGET_FLG            = "DPL_TARGET_FLG";
    private static final String P_EBG_TARGET_FLG            = "EBG_TARGET_FLG";

    private static final String ECS_ADMIN_ID                = "ECS_ADMIN_ID";
    private static final String ECS_ADMIN_INCREQ            = "ECS_ADMIN_INCREQ";
    private static final String ECS_ADMIN_PASS              = "ECS_ADMIN_PASS";
    private static final String ECS_ADMIN_TYPE              = "ECS_ADMIN_TYPE";
    private static final String ECS_AVAL_FLG                = "ECS_AVAL_FLG";
    private static final String ECS_REC_DTFORMAT            = "ECS_REC_DTFORMAT";
    private static final String ECS_REQ_DEPLOYMODE          = "ECS_REQ_DEPLOYMODE";
    private static final String ECS_REQ_HANDLER             = "ECS_REQ_HANDLER";
    private static final String ECS_REQ_TYPE                = "ECS_REQ_TYPE";
    private static final String ECS_REQ_VERSION             = "ECS_REQ_VERSION";
    private static final String ECS_SERV_PARMNAME           = "ECS_SERV_PARMNAME";
    private static final String ECS_SERV_PARMVAL            = "ECS_SERV_PARMVAL";
    private static final String ECS_SERV_SHIP_FR_CD         = "ECS_SERV_SHIP_FR_CD";
    private static final String ECS_SERV_SHIP_FR_CTRY       = "ECS_SERV_SHIP_FR_CTRY";
    private static final String ECS_SERV_SHIP_FR_DOMAIN     = "ECS_SERV_SHIP_FR_DOMAIN";
    private static final String ECS_SERV_SHIP_FR_SCHM       = "ECS_SERV_SHIP_FR_SCHM";
    private static final String ECS_SERV_SERVHDLR_DPL       = "ECS_SERV_SERVHDLR_DPL";
    private static final String ECS_SERV_SERVHDLR_ECE       = "ECS_SERV_SERVHDLR_ECE";
    private static final String ECS_SERV_SERVHDLR_EMBGO     = "ECS_SERV_SERVHDLR_EMBGO";
    private static final String ECS_SERV_SERVTP_DPL         = "ECS_SERV_SERVTP_DPL";
    private static final String ECS_SERV_SERVTP_ECE         = "ECS_SERV_SERVTP_ECE";
    private static final String ECS_SERV_SERVTP_EMBGO       = "ECS_SERV_SERVTP_EMBGO";

    private static final String Y = ZYPConstant.FLG_ON_Y;
    private static final String N = ZYPConstant.FLG_OFF_N;

    private static final String LINE_BREAK              = System.getProperty("line.separator");
    private static final String SET_PARENT_LINE_SUB_NUM = "000";

    private static final int HLD_DTL_TXT_DIGIT = new NWZC044001PMsg().getAttr("hldDtlTxt").getDigit();

    private String glblCmpyCd;
    private String slsDt;
    private ValidationBatchMode validationBatchMode;
    private int ecsApiBulkCallSize = 100;

    /** DEFAULT_FETCH_SIZE */
//    public static final int DEFAULT_FETCH_SIZE = 1000;
    public static final int DEFAULT_FETCH_SIZE = 100;

    private TERM_CD termCd = TERM_CD.NORMAL_END;
    private int normalRecCnt;
    private int errRecCnt;
    private int totalRecCnt;

    /**
     * NWZC044001 : Add Hold Info API's pMsg list held by 'DPL/EMBARGO' validation.
     */
    private final List<NWZC044001PMsg> dplHldPMsgList  = new ArrayList<NWZC044001PMsg>();

    /**
     * ECS API parameter list.
     */
    private final List<Map<String, Object>> ecsApiParamList = new ArrayList<Map<String, Object>>();

    /** timestamp of target data(SHPG_PLN) */
    private final Map<String, SHPG_PLNTMsg> targetTimestampSP = new HashMap<String, SHPG_PLNTMsg>();
    /** timestamp of target data(CPO_DTL) */
    private final Map<String, CPO_DTLTMsg> targetTimestampCD = new HashMap<String, CPO_DTLTMsg>();
    /** timestamp of target data(CPO) */
    private final Map<String, CPOTMsg> targetTimestampC = new HashMap<String, CPOTMsg>();

    // S21_NA#14973 Add Start
    /** Processed Order Number List */
    private List<String> processedOrdNumList = null;
    // S21_NA#14973 Add End

    private final S21SsmBatchClient ssmClient;

    // 2016/10/18 NA_QC#15097 Add Start
    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;
    // 2016/10/18 NA_QC#15097 Add End

    private NWAB003001() {
        ssmClient = S21SsmBatchClient.getClient(this.getClass());
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass()); // S21_NA#15097 Add Start
        processedOrdNumList = new ArrayList<String>(); // S21_NA#14973 Add
    }

    public static void main(String[] args) {
        new NWAB003001().executeBatch();
    }

    @Override
    protected void initRoutine() {

        // --------------------------------------------------
        // GLBL_CMPY_CD
        // --------------------------------------------------
        glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(glblCmpyCd)) {
            throw new S21AbendException(NWAM0103E);
        }

        // --------------------------------------------------
        // SALES_DT
        // --------------------------------------------------
        slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        // --------------------------------------------------
        // Validation Batch Mode
        // 1 = Daytime
        // 2 = Night
        // 3 = before revenue recognition
        // --------------------------------------------------
        String validationMode = getUserVariable1();
        for (ValidationBatchMode mode : ValidationBatchMode.values()) {
            if (mode.toString().equals(validationMode)) {
                this.validationBatchMode = mode;
                break;
            }
        }
        if (validationMode == null) {
            this.validationBatchMode = ValidationBatchMode.DAYTIME;
            infoLogOut(NWAM0193I, "Validation Batch Mode");
            infoLogOut(NWAM0195I, ValidationBatchMode.DAYTIME.toString(), "Validation Batch Mode");
        }

        // --------------------------------------------------
        // ECS API bulk call records count
        // --------------------------------------------------
        String ecsApiBulkCallSizeStr = getUserVariable2();
        if (hasValue(ecsApiBulkCallSizeStr)) {
            try {
                ecsApiBulkCallSize = Integer.parseInt(ecsApiBulkCallSizeStr.trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // TERM_CD
        termCd = TERM_CD.NORMAL_END;

        writePerformanceProfilingLog(getClass(), "+++<<Environment Variable>>+++++++++++++++++++++++++++");
        writePerformanceProfilingLog(getClass(), " - glblCmpyCd                      = " + this.glblCmpyCd);
        writePerformanceProfilingLog(getClass(), " - slsDt                           = " + this.slsDt);
        writePerformanceProfilingLog(getClass(), " - Validation Batch Mode           = " + this.validationBatchMode);
        writePerformanceProfilingLog(getClass(), " - ECS API bulk call records count = " + this.ecsApiBulkCallSize);
        writePerformanceProfilingLog(getClass(), "+++<<Environment Variable>>+++++++++++++++++++++++++++");
    }

    @Override
    protected void mainRoutine() {

        // --------------------------------------------------
        // 1. get [HLD_RSN] informations.
        // --------------------------------------------------
        // 2016/10/12 NA_QC#15097 Del Start
//        final List<HldRsnInfo> hldRsnInfoList = getHldRsnInfoList();
//        if (isEmpty(hldRsnInfoList)) {
//            infoLogOut(NWAM0188I, new HLD_RSNTMsg().getTableName());
//            return;
//        }
        // 2016/10/12 NA_QC#15097 Del End
        // --------------------------------------------------
        // 2. execute Validation.
        // --------------------------------------------------
        // 2016/10/12 NA_QC#15097 Mod
        //final List<NWZC044001PMsg> hldPMsgList = execValidation(hldRsnInfoList); 
        final List<NWZC044001PMsg> hldPMsgList = execValidation();

        // --------------------------------------------------
        // 3. call ECS API.
        // --------------------------------------------------
        writePerformanceProfilingLog(getClass(), "### ecsApiParamList.size() = " + this.ecsApiParamList.size());

        if (!isEmpty(this.ecsApiParamList)) {
            execEcsCheck();
        }

        // --------------------------------------------------
        // 4. add [HLD] by '(NWZC044001)Add Hold Info API'.
        // --------------------------------------------------
        hldPMsgList.addAll(this.dplHldPMsgList);
        writePerformanceProfilingLog(getClass(), "### dplHldPMsgList.size() = " + this.dplHldPMsgList.size());
        writePerformanceProfilingLog(getClass(), "### hldPMsgList.size()    = " + hldPMsgList.size());

        if (!isEmpty(hldPMsgList)) {
            execAddHldInfo(hldPMsgList);
        }

        if (errRecCnt > 0) {
            this.termCd = TERM_CD.WARNING_END;
        } else {
            this.termCd = TERM_CD.NORMAL_END;
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);
    }

    private boolean callAddHldInfoAPI(List<NWZC044001PMsg> apiPMsgList) {

        // --------------------------------------------------
        // NWZC044001 : Add Hold Info API
        // --------------------------------------------------
        new NWZC044001().execute(apiPMsgList, ONBATCH_TYPE.BATCH);

        boolean isNormalEnd = true;

        for (NWZC044001PMsg pMsg : apiPMsgList) {
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                isNormalEnd = false;
                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                    final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    infoLogOut(NWAM0189E, createApiErrMsgByAddHoldInfoAPI(pMsg, xxMsgId));
                }
            }
        }

        return isNormalEnd;
    }

    private Response callEcsXmlAPI(List<Map<String, Object>> ecsParamList, S21ChileKewillPartyScreeningServiceProxy serviceProxy) {
        final String methodNm = "callEcsXmlAPI";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            final Request req = new Request();
            req.setType(ZYPCodeDataUtil.getVarCharConstValue(ECS_REQ_TYPE, glblCmpyCd));
            req.setVersion(ZYPCodeDataUtil.getVarCharConstValue(ECS_REQ_VERSION, glblCmpyCd));
            req.setDateFormat(ZYPCodeDataUtil.getVarCharConstValue(ECS_REC_DTFORMAT, glblCmpyCd));
            req.setDeploymentMode(ZYPCodeDataUtil.getVarCharConstValue(ECS_REQ_DEPLOYMODE, glblCmpyCd));
            req.setHandler(ZYPCodeDataUtil.getVarCharConstValue(ECS_REQ_HANDLER, glblCmpyCd));

            final OutputFormat outputFormat = new OutputFormat();
            outputFormat.setType(ZYPCodeDataUtil.getVarCharConstValue(ECS_ADMIN_TYPE, glblCmpyCd));

            final Administration admin = new Administration();
            admin.setSubscriberID(ZYPCodeDataUtil.getVarCharConstValue(ECS_ADMIN_ID, glblCmpyCd));
            admin.setSubscriberPassword(ZYPCodeDataUtil.getVarCharConstValue(ECS_ADMIN_PASS, glblCmpyCd));
            admin.setRequestDate(slsDt);
            admin.setOutputFormat(outputFormat);
            admin.setIncludeRequest(ZYPCodeDataUtil.getVarCharConstValue(ECS_ADMIN_INCREQ, glblCmpyCd));
            req.setAdministration(admin);

            // ECS
            final com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Service[] service = new com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Service[1];
            service[0] = new com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Service();
            service[0].setType(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_SERVTP_ECE, glblCmpyCd));
            service[0].setHandler(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_SERVHDLR_ECE, glblCmpyCd));

            // DeniedParty
            final Svc[] serviceNest = new Svc[3];
            serviceNest[0] = new Svc();
            serviceNest[0].setType(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_SERVTP_DPL, glblCmpyCd));
            serviceNest[0].setHandler(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_SERVHDLR_DPL, glblCmpyCd));

            final Parameter[] paraForDnp = new Parameter[1];
            paraForDnp[0] = new Parameter();
            paraForDnp[0].setName(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_PARMNAME, glblCmpyCd));
            paraForDnp[0].setValue(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_PARMVAL, glblCmpyCd));

            final Parameters parametersForDnp = new Parameters();
            parametersForDnp.getParameter().addAll(Arrays.asList(paraForDnp));
            serviceNest[0].setParameters(parametersForDnp);

            // Embergo
            final Parameter[] paraForEbg = new Parameter[1];
            paraForEbg[0] = new Parameter();
            paraForEbg[0].setName(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_PARMNAME, glblCmpyCd));
            paraForEbg[0].setValue(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_PARMVAL, glblCmpyCd));

            final Parameters parametersForEbg = new Parameters();
            parametersForEbg.getParameter().addAll(Arrays.asList(paraForEbg));

            serviceNest[1] = new Svc();
            serviceNest[1].setType(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_SERVTP_EMBGO, glblCmpyCd));
            serviceNest[1].setHandler(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_SERVHDLR_EMBGO, glblCmpyCd));
            serviceNest[1].setParameters(parametersForEbg);

            service[0].getService().addAll(Arrays.asList(serviceNest));
            req.getService().add(service[0]);

            final ShipFromCountry sfc = new ShipFromCountry();
            sfc.setValue(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_SHIP_FR_CTRY, glblCmpyCd));
            sfc.setScheme(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_SHIP_FR_SCHM, glblCmpyCd));
            sfc.setDomain(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_SHIP_FR_DOMAIN, glblCmpyCd));
            sfc.setCode(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_SHIP_FR_CD, glblCmpyCd));

            final ShipToCountry stc = new ShipToCountry();
            stc.setValue(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_SHIP_FR_CTRY, glblCmpyCd));
            stc.setScheme(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_SHIP_FR_SCHM, glblCmpyCd));
            stc.setDomain(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_SHIP_FR_DOMAIN, glblCmpyCd));
            stc.setCode(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_SHIP_FR_CD, glblCmpyCd));


            final Map<String, Transaction> trxMap = new TreeMap<String, Transaction>();

            for (Map<String, Object> ecsParam : ecsParamList) {

                // 04/10/2012 CCI Team defect #128 ACY L-241 DPL Screening - Set the parameters with ACY_ update start =>
                // String cpoOrdNum = (String) ecsParam.get(P_CPO_ORD_NUM_I);
                String cpoOrdNum = glblCmpyCd + "_" + (String) ecsParam.get(P_CPO_ORD_NUM_I);
                writePerformanceProfilingLog(getClass(), " +++++ DPL Screening Parameter, glbl_cmpy_cd + \"_\" + cpo_ord_num:[" + cpoOrdNum + "]");
                // 04/10/2012 CCI Team defect #128 ACY L-241 DPL Screening - Set the parameters with ACY_ update end <=

                // --------------------------------------------------
                // Transaction
                // --------------------------------------------------
                final Transaction trx;
                if (trxMap.containsKey(cpoOrdNum)) {
                    trx = trxMap.get(cpoOrdNum);
                } else {
                    trx = new Transaction();
                    trx.setUserDefined(cpoOrdNum);
                    trx.setTransactionDate(slsDt);
                    trx.setShipFromCountry(sfc);
                    trx.setShipToCountry(stc);
                    trxMap.put(cpoOrdNum, trx);
                }

                // --------------------------------------------------
                // Party
                // --------------------------------------------------
                final Party party = new Party();
                party.setType(LOC_ROLE_TP.SHIP_TO);
                party.setUserDefined(cpoOrdNum + "." + nullToEmpty(ecsParam.get(P_CPO_DTL_LINE_NUM_I)) + "." + nullToEmpty(ecsParam.get(P_CPO_DTL_LINE_SUB_NUM_I)));
                party.setName(nullToEmpty(ecsParam.get(P_SHIP_TO_LOC_NM)));
                party.setAddress(nullToEmpty(ecsParam.get(P_SHIP_TO_FIRST_LINE_ADDR)) + nullToEmpty(ecsParam.get(P_SHIP_TO_SCD_LINE_ADDR)) + nullToEmpty(ecsParam.get(P_SHIP_TO_THIRD_LINE_ADDR)) + nullToEmpty(ecsParam.get(P_SHIP_TO_FRTH_LINE_ADDR)));
                party.setCity(nullToEmpty((String) ecsParam.get(P_SHIP_TO_CTY_ADDR)));
                if (!hasValue((String) ecsParam.get(P_SHIP_TO_ST_CD))) {
                    party.setSubDivision(nullToEmpty(ecsParam.get(P_SHIP_TO_PROV_NM)));
                } else {
                    party.setSubDivision(nullToEmpty(ecsParam.get(P_ST_NM)));
                }
                party.setPostalCode(nullToEmpty(ecsParam.get(P_SHIP_TO_POST_CD)));

                // --------------------------------------------------
                // Country
                // --------------------------------------------------
                final Country cntry = new Country();
                cntry.setValue(getCtryNm(nullToEmpty((String) ecsParam.get(P_SHIP_TO_CTRY_CD))));
                cntry.setScheme(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_SHIP_FR_SCHM, glblCmpyCd));
                cntry.setDomain(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_SHIP_FR_DOMAIN, glblCmpyCd));
                cntry.setCode(nullToEmpty(ecsParam.get(P_SHIP_TO_CTRY_CD)));
                party.setCountry(cntry);

                // --------------------------------------------------
                // TransactionLine
                // --------------------------------------------------
                final TransactionLine trxLine = new TransactionLine();
                trxLine.setUserDefined(cpoOrdNum + "." + nullToEmpty(ecsParam.get(P_CPO_DTL_LINE_NUM_I)) + "." + nullToEmpty(ecsParam.get(P_CPO_DTL_LINE_SUB_NUM_I)));
                trxLine.getParty().add(party);

                trx.getTransactionLine().add(trxLine);
            }

            // --------------------------------------------------
            // call ECS Service.
            // --------------------------------------------------
            final ServiceRequest svReq = new ServiceRequest();
            svReq.getTransaction().addAll(trxMap.values());
            req.setServiceRequest(svReq);

            // writePerformanceProfilingLog
            for (Transaction trx : trxMap.values()) {
                final Set<String> userDefinedSet = new HashSet<String>();
                for (TransactionLine trxLine : trx.getTransactionLine()) {
                    userDefinedSet.add(trxLine.getUserDefined());
                }
                writePerformanceProfilingLog(getClass(), "  - " + userDefinedSet);
            }

            try {
                // call ECS Service.
                return serviceProxy.newRequest(req);
            } catch (Throwable e) {
                String exMsg = e.getMessage();
                if (!hasValue(exMsg)) {
                    exMsg = e.toString();
                }
                infoLogOut(NWZM0905W, exMsg);
                return null;
            }

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private void checkByEcsResult(Map<String, Object> ecsParam, com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.response.Service service) {

        // --------------------------------------------------
        // Error
        // --------------------------------------------------
        final List<Error> errs = service.getError();
        if (errs != null && errs.size() > 0) {

            final StringBuilder dplRsnTxtSb = new StringBuilder();
            for (Error err : errs) {
                dplRsnTxtSb.append(makeDplTextForErr(err.getMessage()));
            }

            String dplRsnTxt = dplRsnTxtSb.toString();
            if (dplRsnTxt.length() > HLD_DTL_TXT_DIGIT) {
                dplRsnTxt = dplRsnTxt.substring(0, HLD_DTL_TXT_DIGIT);
            }
            ecsParam.put(P_DPL_RSN_TXT, dplRsnTxt);
            ecsParam.put(P_DPL_FLG,     Y);

            writePerformanceProfilingLog(getClass(), "  - checkByEcsResult.Error : " + service.getUserDefined() + " dplRsnTxt=" + dplRsnTxt);
            return;
        }


        final String serviceTp = nullToEmpty(service.getType());

        // --------------------------------------------------
        // DeniedParty
        // --------------------------------------------------
        if (serviceTp.equals(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_SERVTP_DPL, glblCmpyCd))) {
            if (Y.equals(ecsParam.get(P_DPL_TARGET_FLG))) {

                // DeniedParty
                List<DeniedParty> deniedPartys = service.getDeniedParty();
                // START 12/19/2014 S.Tsunaki [CCH-QC98,MOD]
                //if (deniedPartys != null) {
                if (deniedPartys != null && deniedPartys.size() > 0 && checkContainsDeniedParty(deniedPartys)) {
                // END 12/19/2014 S.Tsunaki [CCH-QC98,MOD]
                    ecsParam.put(P_DPL_RSN_TXT, makeDplText(deniedPartys.toArray(new DeniedParty[0])));
                    ecsParam.put(P_DPL_FLG, Y);
                }

                // RedFlagWords
                String redFlagWords = service.getRedFlagWords();
                if (hasValue(redFlagWords)) {
                    String dplRsnTxt = new StringBuilder(nullToEmpty((String) ecsParam.get(P_DPL_RSN_TXT))).append("Red Flag Word(s):").append(redFlagWords).toString();
                    if (dplRsnTxt.length() > HLD_DTL_TXT_DIGIT) {
                        dplRsnTxt = dplRsnTxt.substring(0, HLD_DTL_TXT_DIGIT);
                    }
                    ecsParam.put(P_DPL_RSN_TXT, dplRsnTxt);
                    ecsParam.put(P_DPL_FLG, Y);
                }
            }

        // --------------------------------------------------
        // Embargo
        // --------------------------------------------------
        } else if (serviceTp.equals(ZYPCodeDataUtil.getVarCharConstValue(ECS_SERV_SERVTP_EMBGO, glblCmpyCd))) {
            if (Y.equals(ecsParam.get(P_EBG_TARGET_FLG))) {
                setEmbargoFlg(ecsParam, service.getEmbargo());
            }
        }
    }

    private String createApiErrMsg(String hldRsnCdNm, Map<String, Object> valParam, String errMsgId) {

        String cpoOrdNum        = (String) valParam.get(P_CPO_ORD_NUM_I);
        String cpoDtlLineNum    = (String) valParam.get(P_CPO_DTL_LINE_NUM_I);
        String cpoDtlLineSubNum = (String) valParam.get(P_CPO_DTL_LINE_SUB_NUM_I);
        String shpgPlnNum       = (String) valParam.get(P_SHPG_PLN_NUM_I);

        return createApiErrMsg(hldRsnCdNm, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum, shpgPlnNum, errMsgId);
    }

    /**
     * <pre>
     * Edit error message when an error occurred in API
     * <pre>
     * @param hldRsnCdNm
     * @param cpoOrdNum
     * @param cpoDtlLineNum
     * @param cpoDtlLineSubNum
     * @param shpgPlnNum
     * @param errMsgId
     * @return
     */
    private String createApiErrMsg(String hldRsnCdNm, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum, String shpgPlnNum, String errMsgId) {

        StringBuilder sb = new StringBuilder(225);

        sb.append("API : ").append(hldRsnCdNm);
        sb.append(", Global Company Code : ").append(glblCmpyCd);
        sb.append(", CPO Order Number : ").append(cpoOrdNum);

        if (hasValue(cpoDtlLineNum)) {
            sb.append(", CPO Detail Line Number : ").append(cpoDtlLineNum);
        }

        if (hasValue(cpoDtlLineSubNum)) {
            sb.append(", CPO Detail Line Sub Number : ").append(cpoDtlLineSubNum);
        }

        if (hasValue(shpgPlnNum)) {
            sb.append(", Shipping Plan Number : ").append(shpgPlnNum);
        }

        sb.append(", API Error ID : ").append(errMsgId);

        return sb.toString();
    }

    private String createApiErrMsgByAddHoldInfoAPI(NWZC044001PMsg pMsg, String errMsgId) {

        String cpoOrdNum        = pMsg.cpoOrdNum.getValue();
        String cpoDtlLineNum    = pMsg.cpoDtlLineNum.getValue();
        String cpoDtlLineSubNum = pMsg.cpoDtlLineSubNum.getValue();
        String shpgPlnNum       = pMsg.shpgPlnNum.getValue();

        return createApiErrMsg("[NWZC044001:Add Hold Info API]", cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum, shpgPlnNum, errMsgId);
    }

    private Map<String, Object> createEcsApiParam(Map<String, Object> value) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(P_CPO_ORD_NUM_I,              value.get(P_CPO_ORD_NUM_I));
        param.put(P_CPO_DTL_LINE_NUM_I,         value.get(P_CPO_DTL_LINE_NUM_I));
        param.put(P_CPO_DTL_LINE_SUB_NUM_I,     value.get(P_CPO_DTL_LINE_SUB_NUM_I));
        param.put(P_SHIP_TO_LOC_NM,             value.get(P_SHIP_TO_LOC_NM));
        param.put(P_SHIP_TO_FIRST_LINE_ADDR,    value.get(P_SHIP_TO_FIRST_LINE_ADDR));
        param.put(P_SHIP_TO_SCD_LINE_ADDR,      value.get(P_SHIP_TO_SCD_LINE_ADDR));
        param.put(P_SHIP_TO_THIRD_LINE_ADDR,    value.get(P_SHIP_TO_THIRD_LINE_ADDR));
        param.put(P_SHIP_TO_FRTH_LINE_ADDR,     value.get(P_SHIP_TO_FRTH_LINE_ADDR));
        param.put(P_SHIP_TO_CTY_ADDR,           value.get(P_SHIP_TO_CTY_ADDR));
        param.put(P_SHIP_TO_ST_CD,              value.get(P_SHIP_TO_ST_CD));
        param.put(P_SHIP_TO_PROV_NM,            value.get(P_SHIP_TO_PROV_NM));
        param.put(P_ST_NM,                      value.get(P_ST_NM));
        param.put(P_SHIP_TO_POST_CD,            value.get(P_SHIP_TO_POST_CD));
        param.put(P_SHIP_TO_CTRY_CD,            value.get(P_SHIP_TO_CTRY_CD));
        return param;
    }

    private List<NWZC044001PMsg> createEcsHldList() {

        final List<NWZC044001PMsg> ecsHldPMsgList = new ArrayList<NWZC044001PMsg>();

        for (Map<String, Object> ecsApiParam : this.ecsApiParamList) {

            // DPL
            if (Y.equals((String) ecsApiParam.get(P_DPL_FLG))) {

                NWZC044001PMsg dplHldPMsg = new NWZC044001PMsg();
                ecsHldPMsgList.add(dplHldPMsg);

                setValue(dplHldPMsg.glblCmpyCd,       glblCmpyCd);
                setValue(dplHldPMsg.cpoOrdNum,        (String) ecsApiParam.get(P_CPO_ORD_NUM_I));
                setValue(dplHldPMsg.cpoDtlLineNum,    (String) ecsApiParam.get(P_CPO_DTL_LINE_NUM_I));
                setValue(dplHldPMsg.cpoDtlLineSubNum, (String) ecsApiParam.get(P_CPO_DTL_LINE_SUB_NUM_I));
                setValue(dplHldPMsg.hldRsnCd,         DPL_SCREENING);
                setValue(dplHldPMsg.hldDtlTxt,        (String) ecsApiParam.get(P_DPL_RSN_TXT));
                setValue(dplHldPMsg.slsDt,            slsDt);
            }

            // Embargo
            if (Y.equals((String) ecsApiParam.get(P_EBG_FLG))) {

                NWZC044001PMsg ebgHldPMsg = new NWZC044001PMsg();
                ecsHldPMsgList.add(ebgHldPMsg);

                setValue(ebgHldPMsg.glblCmpyCd,       glblCmpyCd);
                setValue(ebgHldPMsg.cpoOrdNum,        (String) ecsApiParam.get(P_CPO_ORD_NUM_I));
                setValue(ebgHldPMsg.cpoDtlLineNum,    (String) ecsApiParam.get(P_CPO_DTL_LINE_NUM_I));
                setValue(ebgHldPMsg.cpoDtlLineSubNum, (String) ecsApiParam.get(P_CPO_DTL_LINE_SUB_NUM_I));
                setValue(ebgHldPMsg.hldRsnCd,         EMBARGO);
                setValue(ebgHldPMsg.hldDtlTxt,        (String) ecsApiParam.get(P_DPL_RSN_TXT));
                setValue(ebgHldPMsg.slsDt, slsDt);
            }
        }

        return ecsHldPMsgList;
    }

    private void execAddHldInfo(List<NWZC044001PMsg> hldPMsgList) {
        final String methodNm = "execAddHldInfo";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            final Map<String, List<NWZC044001PMsg>> addHldApiPMsgMap = new HashMap<String, List<NWZC044001PMsg>>();

            // --------------------------------------------------
            // grouping [NWZC044001PMsg] by 'CPO_ORD_NUM'.
            // --------------------------------------------------
            for (NWZC044001PMsg hldPMsg : hldPMsgList) {
                String cpoOrdNum   = hldPMsg.cpoOrdNum.getValue();
                String groupingKey = cpoOrdNum;
                if (!addHldApiPMsgMap.containsKey(groupingKey)) {
                    addHldApiPMsgMap.put(groupingKey, new ArrayList<NWZC044001PMsg>());
                }
                addHldApiPMsgMap.get(groupingKey).add(hldPMsg);
            }

            this.totalRecCnt  = 0;
            this.normalRecCnt = 0;
            this.errRecCnt    = 0;
            // --------------------------------------------------
            // records lock & status check.
            // --------------------------------------------------
            List<String> lockedOrderNumList = new ArrayList<String>(); // QC#17941
            cpoOrdNumLoop : for (Entry<String, List<NWZC044001PMsg>> entry : addHldApiPMsgMap.entrySet()) {

                String cpoOrdNum = entry.getKey();
                // QC#17941
                if (lockedOrderNumList.contains(cpoOrdNum)) {
                    continue;
                }
                CPOTMsg cTMsg = targetTimestampC.get(cpoOrdNum); // QC#17941

                // CPO
                final CPOTMsg cpoTMsg = lockCpo(cpoOrdNum);
                if (cpoTMsg == null // QC#17941
                        || !ZYPDateUtil.isSameTimeStamp(cTMsg.ezUpTime.getValue(), cTMsg.ezUpTimeZone.getValue(), cpoTMsg.ezUpTime.getValue(), cpoTMsg.ezUpTimeZone.getValue())) { // QC#17941
                    // lock failure -> next 'CPO_ORD_NUM'
                    infoLogOut(NWAM0196E, S21StringUtil.concatStrings("CPO(", cpoOrdNum, ")"));
//                    addHldApiPMsgMap.remove(entry.getKey()); // QC#17941
                    lockedOrderNumList.add(cpoOrdNum); // QC#17941
                    rollback(); // QC#17941
                    continue;
                } else {
                    // ORD_HDR_STS != 'VALIDATED'
                    String ordHdrStsCd = cpoTMsg.ordHdrStsCd.getValue();
                    if (!asList(ORD_HDR_STS.VALIDATED).contains(ordHdrStsCd)) {
//                        addHldApiPMsgMap.remove(entry.getKey());
                        lockedOrderNumList.add(cpoOrdNum); // QC#17941
                        rollback(); // QC#17941
                        continue;
                    }
                }

                final Iterator<NWZC044001PMsg> it = entry.getValue().iterator();
                while (it.hasNext()) {

                    final NWZC044001PMsg addHldApiPMsg = it.next();
                    String shpgPlnNum       = addHldApiPMsg.shpgPlnNum.getValue();
                    String cpoDtlLineNum    = addHldApiPMsg.cpoDtlLineNum.getValue();
                    String cpoDtlLineSubNum = addHldApiPMsg.cpoDtlLineSubNum.getValue();
                    // QC#17941
                    SHPG_PLNTMsg spTMsg = targetTimestampSP.get(shpgPlnNum);
                    CPO_DTLTMsg cdTMsg = targetTimestampCD.get(S21StringUtil.concatStrings(//
                            cpoOrdNum, ",", cpoDtlLineNum, ",", cpoDtlLineSubNum, ","));

                    // SHPG_PLN
                    if (hasValue(shpgPlnNum)) {
                        final SHPG_PLNTMsg shpgPlnTMsg = lockShpgPln(shpgPlnNum);
                        if (shpgPlnTMsg == null || spTMsg == null // QC#17941
                                || !ZYPDateUtil.isSameTimeStamp(spTMsg.ezUpTime.getValue(), spTMsg.ezUpTimeZone.getValue(), shpgPlnTMsg.ezUpTime.getValue(), shpgPlnTMsg.ezUpTimeZone.getValue())) { // QC#17941
                            // lock failure -> next 'CPO_ORD_NUM'
                            infoLogOut(NWAM0196E, S21StringUtil.concatStrings("SHPG_PLN(", cpoOrdNum, ".", cpoDtlLineNum, ".", cpoDtlLineSubNum, " ", shpgPlnNum, ")"));
//                            addHldApiPMsgMap.remove(entry.getKey()); // QC#17941
                            lockedOrderNumList.add(cpoOrdNum); // QC#17941
                            rollback(); // QC#17941
                            continue cpoOrdNumLoop;
                        } else {
                            // SHPG_STS != 'VALIDATED', 'HARD_ALLOCATED'
                            String shpgStsCd = shpgPlnTMsg.shpgStsCd.getValue();
                            if (!asList(SHPG_STS.VALIDATED, SHPG_STS.HARD_ALLOCATED).contains(shpgStsCd)) {
                                it.remove();
                            }
                        }
                    // CPO_DTL
                    } else if (hasValue(cpoDtlLineNum) && hasValue(cpoDtlLineSubNum)) {
                        final CPO_DTLTMsg cpoDtlTMsg = lockCpoDtl(cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
                        if (cpoDtlTMsg == null || cdTMsg == null // QC#17941
                                || !ZYPDateUtil.isSameTimeStamp(cdTMsg.ezUpTime.getValue(), cdTMsg.ezUpTimeZone.getValue(), cpoDtlTMsg.ezUpTime.getValue(), cpoDtlTMsg.ezUpTimeZone.getValue())) { // QC#17941
                            // lock failure -> next 'CPO_ORD_NUM'
                            infoLogOut(NWAM0196E, S21StringUtil.concatStrings("CPO_DTL(", cpoOrdNum, ".", cpoDtlLineNum, ".", cpoDtlLineSubNum, ")"));
//                            addHldApiPMsgMap.remove(entry.getKey()); // QC#17941
                            lockedOrderNumList.add(cpoOrdNum); // QC#17941
                            rollback(); // QC#17941
                            continue cpoOrdNumLoop;
                        } else {
                            // ORD_LINE_STS != 'VALIDATED', 'PARTIALLY_SHIPPED'
                            String ordLineStsCd = cpoDtlTMsg.ordLineStsCd.getValue();
                            if (!asList(ORD_LINE_STS.VALIDATED, ORD_LINE_STS.PARTIALLY_SHIPPED).contains(ordLineStsCd)) {
                                it.remove();
                            }
                        }
                    }
                }

                // QC#17941
                // --------------------------------------------------
                // call [NWZC044001 : Add Hold Info API]
                // --------------------------------------------------
                final List<NWZC044001PMsg> addHldApiPMsgList = entry.getValue();
                writePerformanceProfilingLog(getClass(), "### CPO_ORD_NUM = " + entry.getKey() + " List<NWZC044001PMsg>>.size() = " + addHldApiPMsgList.size());

                if (!isEmpty(addHldApiPMsgList)) {
                    this.totalRecCnt += addHldApiPMsgList.size();
                    boolean isNormalEnd = callAddHldInfoAPI(addHldApiPMsgList);
                    if (isNormalEnd) {
                        commit();
                        this.normalRecCnt += addHldApiPMsgList.size();
                    } else {
                        rollback();
                    }
                }
            }

            // --------------------------------------------------
            // call [NWZC044001 : Add Hold Info API]
            // --------------------------------------------------
//            this.totalRecCnt  = 0;
//            this.normalRecCnt = 0;
//            this.errRecCnt    = 0;
//
//            for (Entry<String, List<NWZC044001PMsg>> entry : addHldApiPMsgMap.entrySet()) {
//
//                final List<NWZC044001PMsg> addHldApiPMsgList = entry.getValue();
//                writePerformanceProfilingLog(getClass(), "### CPO_ORD_NUM = " + entry.getKey() + " List<NWZC044001PMsg>>.size() = " + addHldApiPMsgList.size());
//
//                this.totalRecCnt += addHldApiPMsgList.size();
//
//                if (!isEmpty(addHldApiPMsgList)) {
//                    boolean isNormalEnd = callAddHldInfoAPI(addHldApiPMsgList);
//                    if (isNormalEnd) {
//                        commit();
//                        this.normalRecCnt += addHldApiPMsgList.size();
//                    } else {
//                        rollback();
//                    }
//                }
//            }

            this.errRecCnt = this.totalRecCnt - this.normalRecCnt;

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private void execEcsCheck() {
        final String methodNm = "execEcsCheck";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            if (!isEmpty(this.ecsApiParamList)) {

                String ecsAvalFlg = ZYPCodeDataUtil.getVarCharConstValue(ECS_AVAL_FLG, glblCmpyCd);
                if (Y.equals(ecsAvalFlg)) {

                    final S21ChileKewillPartyScreeningServiceProxy serviceProxy;
                    try {
                        serviceProxy = new S21ChileKewillPartyScreeningServiceProxy();
                    } catch (Exception e) {
                        throw toS21AbendException(e);
                    }


                    final Map<String, List<Map<String, Object>>> ecsParamMapByOrder = new TreeMap<String, List<Map<String, Object>>>();
                    final Map<String, String> rsnCdMap = new HashMap<String, String>();

                    for (Map<String, Object> valParam : this.ecsApiParamList) {

                        String cpoOrdNum        = (String) valParam.get(P_CPO_ORD_NUM_I);
                        String cpoDtlLineNum    = (String) valParam.get(P_CPO_DTL_LINE_NUM_I);
                        String cpoDtlLineSubNum = (String) valParam.get(P_CPO_DTL_LINE_SUB_NUM_I);

                        final List<Map<String, Object>> ecsParamListByOrder;
                        if (ecsParamMapByOrder.containsKey(cpoOrdNum)) {
                            ecsParamListByOrder = ecsParamMapByOrder.get(cpoOrdNum);
                        } else {
                            ecsParamListByOrder = new ArrayList<Map<String, Object>>();
                            ecsParamMapByOrder.put(cpoOrdNum, ecsParamListByOrder);
                        }

                        // It do not include the details of the same branch number in a list.
                        String cpoOrdSubLineKey = cpoOrdNum + cpoDtlLineNum + cpoDtlLineSubNum;
                        if (!rsnCdMap.containsKey(cpoOrdSubLineKey)) {

                            final Map<String, Object> ecsApiParam = createEcsApiParam(valParam);
                            ecsParamListByOrder.add(ecsApiParam);

                            final String hldRsnCd = (String) valParam.get(P_RSN_CD);
                            if (DPL_SCREENING.equals(hldRsnCd)) {
                                ecsApiParam.put(P_DPL_TARGET_FLG, Y);
                            } else if (EMBARGO.equals(hldRsnCd)) {
                                ecsApiParam.put(P_EBG_TARGET_FLG, Y);
                            }

                            rsnCdMap.put(cpoOrdSubLineKey, hldRsnCd);

                        } else {
                            if (!valParam.get(P_RSN_CD).equals(rsnCdMap.get(cpoOrdSubLineKey))) {
                                setTargetFlg(ecsParamListByOrder, cpoOrdSubLineKey, valParam);
                            }
                        }
                    }

                    final List<Map<String, Object>> ecsParamList = new ArrayList<Map<String, Object>>();

                    for (Entry<String, List<Map<String, Object>>> ecsParamMapEntry : ecsParamMapByOrder.entrySet()) {
                        List<Map<String, Object>> ecsParamListByOrder = ecsParamMapEntry.getValue();
                        if (!isEmpty(ecsParamListByOrder)) {
                            ecsParamList.addAll(ecsParamListByOrder);
                            // --------------------------------------------------
                            // call Ecs Xml API
                            // --------------------------------------------------
                            if (ecsParamList.size() >= ecsApiBulkCallSize) {
                                execEcsCheck(ecsParamList, serviceProxy);
                                ecsParamList.clear();
                            }
                        }
                    }

                    // --------------------------------------------------
                    // call Ecs Xml API
                    // --------------------------------------------------
                    if (!ecsParamList.isEmpty()) {
                        execEcsCheck(ecsParamList, serviceProxy);
                    }

                } else {
                    for (Map<String, Object> valParam : this.ecsApiParamList) {
                        if (DPL_SCREENING.equals(valParam.get(P_RSN_CD))) {
                            valParam.put(P_DPL_TARGET_FLG, Y);
                            valParam.put(P_DPL_FLG,        Y);
                        } else if (EMBARGO.equals(valParam.get(P_RSN_CD))) {
                            valParam.put(P_EBG_TARGET_FLG, Y);
                            valParam.put(P_EBG_FLG,        Y);
                        }
                        valParam.put(P_DPL_RSN_TXT, clspGetMessage(NWAM0310I));
                    }
                }

                final List<NWZC044001PMsg> ecsHldPMsgList = createEcsHldList();
                mergeDplHldList(ecsHldPMsgList);
            }

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private boolean execEcsCheck(List<Map<String, Object>> ecsParamList, S21ChileKewillPartyScreeningServiceProxy serviceProxy) {

        // --------------------------------------------------
        // call Ecs Xml API
        // --------------------------------------------------
        final Response res = callEcsXmlAPI(ecsParamList, serviceProxy);

        if (res != null) {
            // --------------------------------------------------
            // check Ecs Xml API Result.
            // --------------------------------------------------
            ServiceResponse serviceRes = res.getServiceResponse();
            if (serviceRes != null) {
                List<Service> services = serviceRes.getService();
                if (services != null && services.size() > 0) {
                    for (Map<String, Object> ecsParam : ecsParamList) {
                        final String cpoOrdNum        = nullToEmpty((String) ecsParam.get(P_CPO_ORD_NUM_I));
                        // 04/10/2012 CCI Team defect #128 ACY L-241 DPL Screening - Set the parameters with ACY_ add start =>
                        final String cpoOrdNumGlbl    = glblCmpyCd + "_" + nullToEmpty((String) ecsParam.get(P_CPO_ORD_NUM_I));
                        // 04/10/2012 CCI Team defect #128 ACY L-241 DPL Screening - Set the parameters with ACY_ add end <=
                        final String cpoDtlLineNum    = nullToEmpty((String) ecsParam.get(P_CPO_DTL_LINE_NUM_I));
                        final String cpoDtlLineSubNum = nullToEmpty((String) ecsParam.get(P_CPO_DTL_LINE_SUB_NUM_I));
                        for (Service service : services) {
                            String[] lineNumArray = makeLineNumber(service.getUserDefined());
                            // 04/10/2012 CCI Team defect #128 ACY L-241 DPL Screening - Set the parameters with ACY_ update start =>
                            // if (cpoOrdNum.equals(lineNumArray[0]) && cpoDtlLineNum.equals(lineNumArray[1]) && cpoDtlLineSubNum.equals(lineNumArray[2])) {
                            boolean isSameCpoOrdNum = cpoOrdNum.equals(lineNumArray[0]) || cpoOrdNumGlbl.equals(lineNumArray[0]);
                            writePerformanceProfilingLog(getClass(), "execEcsCheck() +++++ DPL Screening, target of check?[" + cpoOrdNum + "], [" + cpoOrdNumGlbl + "], isSameCpoOrdNum:[" + isSameCpoOrdNum + "]");
                            if (isSameCpoOrdNum && cpoDtlLineNum.equals(lineNumArray[1]) && cpoDtlLineSubNum.equals(lineNumArray[2])) {
                                writePerformanceProfilingLog(getClass(), "execEcsCheck() +++++ DPL Screening, check target!!");
                            // 04/10/2012 CCI Team defect #128 ACY L-241 DPL Screening - Set the parameters with ACY_ add end <=
                                checkByEcsResult(ecsParam, service);
                            }
                        }
                    }
                }
            }

            setEcsRetunValue(ecsParamList);
        }

        return true;
    }

    // 2016/10/12 NA_QC#15097 Mod
    private List<NWZC044001PMsg> execValidation() {
    //private List<NWZC044001PMsg> execValidation(List<HldRsnInfo> hldRsnInfoList) {
        final String methodNm = "execValidation";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            final Map<String, String> ssmParam = new HashMap<String, String>();

            final String statementId;

            switch (validationBatchMode) {

                case DAYTIME:
                    statementId = "getShpgPlnListByDaytime";

                    ssmParam.put("glblCmpyCd",      glblCmpyCd);
                    ssmParam.put("trxSrcTpCd",      TRX_SRC_TP.WHOLE_SALES);
                    ssmParam.put("trxLineSubNum",   SET_PARENT_LINE_SUB_NUM);
                    ssmParam.put("shpgStsCdV",      SHPG_STS.VALIDATED);        // VALIDATED
                    ssmParam.put("shpgStsCdHa",     SHPG_STS.HARD_ALLOCATED);   // HARD_ALLOCATED
                    break;

                case NIGHT:
                    statementId = "getShpgPlnListByNight";

                    ssmParam.put("glblCmpyCd",      glblCmpyCd);
                    ssmParam.put("trxSrcTpCd",      TRX_SRC_TP.WHOLE_SALES);
                    ssmParam.put("shpgStsCdV",      SHPG_STS.VALIDATED);        // VALIDATED
                    ssmParam.put("shpgStsCdHa",     SHPG_STS.HARD_ALLOCATED);   // HARD_ALLOCATED
                    break;

                case BEFORE_REVENUE_RECOGNITION:
                    statementId = "getShpgPlnListByBeforeRevenueRecognition";

                    ssmParam.put("glblCmpyCd",      glblCmpyCd);
                    ssmParam.put("trxSrcTpCd",      TRX_SRC_TP.WHOLE_SALES);
                    ssmParam.put("trxLineSubNum",   SET_PARENT_LINE_SUB_NUM);
                    ssmParam.put("shpgStsCdV",      SHPG_STS.VALIDATED);        // VALIDATED
                    break;

                default:
                    return emptyList();
            }

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            execParam.setFetchSize(DEFAULT_FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            stmt = this.ssmLLClient.createPreparedStatement(statementId, ssmParam, execParam);
            rs = stmt.executeQuery();

            // 2016/10/12 NA_QC#15097 Mod
            return (List<NWZC044001PMsg>) (new OrderValidator()).doProcessQueryResult(rs);
            //return (List<NWZC044001PMsg>) ssmClient.queryObjectList(statementId, ssmParam, new OrderValidator());
            //return (List<NWZC044001PMsg>) ssmClient.queryObjectList(statementId, ssmParam, new OrderValidator(hldRsnInfoList));

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
            return null;
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private String getCtryNm(String ctryCd) {

        CTRYTMsg reqTMsg = new CTRYTMsg();
        reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
        reqTMsg.ctryCd.setValue(ctryCd);

        CTRYTMsg resTMsg = (CTRYTMsg) S21CacheTBLAccessor.findByKey(reqTMsg);
        if (resTMsg != null) {
            return resTMsg.ctryNm.getValue();
        } else {
            return "";
        }
    }

    // 2016/10/12 NA_QC#15097 Del Start
//    @SuppressWarnings("unchecked")
//    private List<HldRsnInfo> getHldRsnInfoList() {
//
//        final Map<String, String> ssmParam = new HashMap<String, String>();
//        ssmParam.put("glblCmpyCd",          glblCmpyCd);
//        ssmParam.put("validationBatchMode", validationBatchMode.toString());
//
//        return (List<HldRsnInfo>) ssmClient.queryObjectList("getHldRsnInfoList", ssmParam, new HldRsnInfoCreator());
//    }
    // 2016/10/12 NA_QC#15097 Del End

    private MDSETMsg getMdse(String mdseCd) {
        return NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
    }

    private String getStNm(String stCd) {

        if (!hasValue(stCd)) {
            return "";
        }

        STTMsg stTMsg = new STTMsg();
        setValue(stTMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(stTMsg.stCd,       stCd);

        stTMsg = (STTMsg) S21CacheTBLAccessor.findByKey(stTMsg);

        if (stTMsg != null) {
            return stTMsg.stNm.getValue();
        } else {
            return "";
        }
    }

    private boolean hasCpoOrdNumInSub(NWZC044001PMsg ecsHldPMsg) {

        for (NWZC044001PMsg dplHldPMsg : this.dplHldPMsgList) {

            boolean isEqualsCpoOrdNum           = isEquals(dplHldPMsg.cpoOrdNum.getValue(),        ecsHldPMsg.cpoOrdNum.getValue());
            boolean isEqualsCpoDtlLineNum       = isEquals(dplHldPMsg.cpoDtlLineNum.getValue(),    ecsHldPMsg.cpoDtlLineNum.getValue());
            boolean isEqualsCpoDtlLineSubNum    = isEquals(dplHldPMsg.cpoDtlLineSubNum.getValue(), ecsHldPMsg.cpoDtlLineSubNum.getValue());
            boolean isEqualsHldRsnCd            = isEquals(dplHldPMsg.hldRsnCd.getValue(),         ecsHldPMsg.hldRsnCd.getValue());

            if (isEqualsCpoOrdNum && isEqualsCpoDtlLineNum && isEqualsCpoDtlLineSubNum && isEqualsHldRsnCd) {
                String hldDtlTxt = dplHldPMsg.hldDtlTxt.getValue() + "ShipTo(DropShip): " + LINE_BREAK + ecsHldPMsg.hldDtlTxt.getValue();
                if (hldDtlTxt.length() > HLD_DTL_TXT_DIGIT) {
                    hldDtlTxt = hldDtlTxt.substring(0, HLD_DTL_TXT_DIGIT);
                }
                dplHldPMsg.hldDtlTxt.setValue(hldDtlTxt);
                return true;
            }
        }

        return false;
    }

    private CPOTMsg lockCpo(String cpoOrdNum) {

        CPOTMsg reqCpoTMsg = new CPOTMsg();
        reqCpoTMsg.glblCmpyCd.setValue(glblCmpyCd);
        reqCpoTMsg.cpoOrdNum.setValue(cpoOrdNum);

        return (CPOTMsg) findByKeyForUpdate(reqCpoTMsg);
    }
    // QC#17941
//    private CPOTMsg lockCpo(CPOTMsg cpoTMsg) {
//        if (cpoTMsg == null) {
//            return null;
//        }
//        CPOTMsg reqCpoTMsg = new CPOTMsg();
//        reqCpoTMsg.setSQLID(SQL_ID_LOCK_CPO_WITH_TIMESTAMP);
//        reqCpoTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        reqCpoTMsg.setConditionValue("cpoOrdNum01", cpoTMsg.cpoOrdNum.getValue());
//        reqCpoTMsg.setConditionValue("ezUpTime01", cpoTMsg.ezUpTime.getValue());
//        reqCpoTMsg.setConditionValue("ezUpTimeZone01", cpoTMsg.ezUpTimeZone.getValue());
//        CPOTMsgArray cpoAry = ((CPOTMsgArray) EZDTBLAccessor.findByConditionForUpdate(reqCpoTMsg));
//        if (cpoAry.length() == 0) {
//            return null;
//        }
//
//        return cpoAry.no(0);
//    }

    private CPO_DTLTMsg lockCpoDtl(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        CPO_DTLTMsg reqTMsg = new CPO_DTLTMsg();
        reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
        reqTMsg.cpoOrdNum.setValue(cpoOrdNum);
        reqTMsg.cpoDtlLineNum.setValue(cpoDtlLineNum);
        reqTMsg.cpoDtlLineSubNum.setValue(cpoDtlLineSubNum);

        return (CPO_DTLTMsg) findByKeyForUpdate(reqTMsg);
    }
//    private CPO_DTLTMsg lockCpoDtl(CPO_DTLTMsg cdTMsg) {
//        if (cdTMsg == null) {
//            return null;
//        }
//
//        CPO_DTLTMsg reqTMsg = new CPO_DTLTMsg();
//        reqTMsg.setSQLID(SQL_ID_LOCK_CPO_DTL_WITH_TIMESTAMP);
//        reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
//        reqTMsg.cpoOrdNum.setValue(cpoOrdNum);
//        reqTMsg.cpoDtlLineNum.setValue(cpoDtlLineNum);
//        reqTMsg.cpoDtlLineSubNum.setValue(cpoDtlLineSubNum);
//
//        return (CPO_DTLTMsg) findByKeyForUpdate(reqTMsg);
//    }

    private SHPG_PLNTMsg lockShpgPln(String shpgPlnNum) {

        SHPG_PLNTMsg reqTMsg = new SHPG_PLNTMsg();
        reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
        reqTMsg.shpgPlnNum.setValue(shpgPlnNum);

        return (SHPG_PLNTMsg) findByKeyForUpdate(reqTMsg);
    }
//    private SHPG_PLNTMsg lockShpgPln(SHPG_PLNTMsg spTMsg) {
//
//        SHPG_PLNTMsg reqTMsg = new SHPG_PLNTMsg();
//        reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
//        reqTMsg.shpgPlnNum.setValue(spTMsg);
//
//        return (SHPG_PLNTMsg) findByKeyForUpdate(reqTMsg);
//    }

    private String makeDplText(DeniedParty[] deniedPartys) {

        final StringBuilder sb = new StringBuilder();

        for (DeniedParty deniedParty : deniedPartys) {
            sb.append("TYPE:").append(deniedParty.getType()).append(LINE_BREAK);
            sb.append("NAME:").append(deniedParty.getName()).append(LINE_BREAK);
            sb.append("ADDRESS:").append(deniedParty.getAddress()).append(LINE_BREAK);
            // QC#29655 2018/12/18 Add Start
            // sb.append("WORD:").append(deniedParty.getType()).append(LINE_BREAK);
            sb.append("WORD:").append(deniedParty.getWords()).append(LINE_BREAK);
            // QC#29655 2018/12/18 Add End
        }

        String result = sb.toString();
        if (result.length() > HLD_DTL_TXT_DIGIT) {
            result = result.substring(0, HLD_DTL_TXT_DIGIT);
        }

        return result;
    }

    // START 12/24/2014 S.Tsunaki [CCH-QC98,ADD]
    private boolean checkContainsDeniedParty(List<DeniedParty> deniedPartys) {

        if (deniedPartys == null) {
            return false;
        }

        for (DeniedParty deniedParty : deniedPartys) {
            if (hasValue(deniedParty.getType())
              || hasValue(deniedParty.getName())
              || hasValue(deniedParty.getAddress())
              || hasValue(deniedParty.getWords())) {
                return true;
            }
        }
        return false;
    }
    // START 12/24/2014 S.Tsunaki [CCH-QC98,ADD]

    private String makeDplTextForErr(String message) {

        StringBuilder sb = new StringBuilder();

        sb.append("TYPE:").append(LINE_BREAK);
        sb.append("NAME:").append(LINE_BREAK);
        sb.append("ADDRESS:").append(LINE_BREAK);
        sb.append("WORD:").append(message).append(LINE_BREAK);

        String result = sb.toString();
        if (result.length() > HLD_DTL_TXT_DIGIT) {
            result = result.substring(0, HLD_DTL_TXT_DIGIT);
        }

        return result;
    }

    private String[] makeLineNumber(String lineNumber) {

        String[] separateLineNumber = new String[3];
        if (lineNumber != null) {
            StringTokenizer st = new StringTokenizer(lineNumber, ".");
            int i = 0;
            while (st.hasMoreTokens()) {
                separateLineNumber[i] = st.nextToken();
                i++;
            }
        }

        return separateLineNumber;
    }

    private void mergeDplHldList(List<NWZC044001PMsg> ecsHldPMsgList) {
        for (NWZC044001PMsg ecsHldPMsg : ecsHldPMsgList) {
            if (!hasCpoOrdNumInSub(ecsHldPMsg)) {
                dplHldPMsgList.add(ecsHldPMsg);
            }
        }
    }

// 2016/09/21 QC#14646 Mod Start
//    private void setBillToDplFlg(Map<String, Object> screeningData, String dplStsCd, String dplRsnTxt) {
//
//        final StringBuilder dplRsnTxtSb = new StringBuilder();
//        dplRsnTxtSb.append("BillTo:").append(dplRsnTxt).append(LINE_BREAK);
//
//        setDplFlg(screeningData, dplStsCd, dplRsnTxtSb.toString());
//    }
// 2016/09/21 QC#14646 Mod End

    private void setDplFlg(Map<String, Object> screeningData, String dplStsCd, String dplRsnTxt) {

        if (!(ZYPConstant.FLG_ON_1.equals(dplStsCd))) {

            // DPL_FLG
            screeningData.put(P_DPL_FLG, Y);

            // DPL_RSN_TXT
            if (screeningData.containsKey(P_DPL_RSN_TXT)) {
                dplRsnTxt = ((String) screeningData.get(P_DPL_RSN_TXT)).concat(dplRsnTxt);
            }
            screeningData.put(P_DPL_RSN_TXT, dplRsnTxt);
        }
    }

    private void setEcsRetunValue(List<Map<String, Object>> objectEcsAPIListByOrd) {

        for (Map<String, Object> detail : objectEcsAPIListByOrd) {

            String cpoOrdNum        = (String) detail.get(P_CPO_ORD_NUM_I);
            String cpoDtlLineNum    = (String) detail.get(P_CPO_DTL_LINE_NUM_I);
            String cpoDtlLineSubNum = (String) detail.get(P_CPO_DTL_LINE_SUB_NUM_I);
            boolean dplTarget       = Y.equals((String) detail.get(P_DPL_TARGET_FLG));
            boolean ebgTarget       = Y.equals((String) detail.get(P_EBG_TARGET_FLG));
            String dplFlg           = (String) detail.get(P_DPL_FLG);
            String ebgFlg           = (String) detail.get(P_EBG_FLG);
            String dplRsnTxt        = (String) detail.get(P_DPL_RSN_TXT);

            for (Map<String, Object> ecsApiParam : this.ecsApiParamList) {

                if (cpoOrdNum.equals((String) ecsApiParam.get(P_CPO_ORD_NUM_I)) && cpoDtlLineNum.equals((String) ecsApiParam.get(P_CPO_DTL_LINE_NUM_I)) && cpoDtlLineSubNum.equals((String) ecsApiParam.get(P_CPO_DTL_LINE_SUB_NUM_I))) {
                    if (dplTarget && DPL_SCREENING.equals(ecsApiParam.get(P_RSN_CD)) && Y.equals(dplFlg)) {
                        ecsApiParam.put(P_DPL_FLG, dplFlg);
                        if (hasValue(dplRsnTxt)) {
                            String txt = (String) ecsApiParam.get(P_DPL_RSN_TXT);
                            if (hasValue(txt)) {
                                ecsApiParam.put(P_DPL_RSN_TXT, txt.concat(dplRsnTxt));
                            } else {
                                ecsApiParam.put(P_DPL_RSN_TXT, dplRsnTxt);
                            }
                        }
                    }
                    if (ebgTarget && EMBARGO.equals(ecsApiParam.get(P_RSN_CD)) && Y.equals(ebgFlg)) {
                        ecsApiParam.put(P_EBG_FLG, ebgFlg);
                    }
                }
            }
        }
    }

    private void setEmbargoFlg(Map<String, Object> screeningMap, String embgoFlg) {

        if (Y.equals(embgoFlg)) {
            screeningMap.put(P_EBG_FLG, Y);
        }
    }

// 2016/09/21 QC#14646 Mod Start
//    private void setSellToDplFlg(Map<String, Object> screeningData, String dplStsCd, String dplRsnTxt) {
//
//        final StringBuilder dplRsnTxtSb = new StringBuilder();
//        dplRsnTxtSb.append("SellTo:").append(dplRsnTxt).append(LINE_BREAK);
//
//        setDplFlg(screeningData, dplStsCd, dplRsnTxtSb.toString());
//    }
 // 2016/09/21 QC#14646 Mod End

    private void setShipToDplFlg(Map<String, Object> screeningData, String dplStsCd, String dplRsnTxt) {

        final StringBuilder dplRsnTxtSb = new StringBuilder();
        dplRsnTxtSb.append("ShipTo:").append(dplRsnTxt).append(LINE_BREAK);

        setDplFlg(screeningData, dplStsCd, dplRsnTxtSb.toString());
    }

    private void setTargetFlg(List<Map<String, Object>> objectEcsAPIListByOrd, String cpoOrdSubLineKey, Map<String, Object> valParam) {

        for (Map<String, Object> objMap : objectEcsAPIListByOrd) {
            String lineKey = (String) objMap.get(P_CPO_ORD_NUM_I) + (String) objMap.get(P_CPO_DTL_LINE_NUM_I) + (String) objMap.get(P_CPO_DTL_LINE_SUB_NUM_I);
            if (cpoOrdSubLineKey.equals(lineKey)) {
                if (DPL_SCREENING.equals(valParam.get(P_RSN_CD))) {
                    objMap.put(P_DPL_TARGET_FLG, Y);
                } else if (EMBARGO.equals(valParam.get(P_RSN_CD))) {
                    objMap.put(P_EBG_TARGET_FLG, Y);
                }
                break;
            }
        }
    }

    private static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    private static String nullToEmpty(Object obj) {
        String result = null;
        if (obj == null) {
            result = "";
        } else {
            result = (String) obj;
        }
        return result;
    }

    private static S21AbendException toS21AbendException(Exception e) {
        if (e instanceof S21AbendException) {
            return (S21AbendException) e;
        } else {
            return new S21AbendException(e);
        }
    }

    private static void writePerformanceProfilingLog(Class clazz, String str) {
        final StringBuilder sb = new StringBuilder();
        sb.append("#Performance Profiling# [").append(clazz.getSimpleName()).append("] : ").append(str);
        System.out.println(sb.toString());
    }

    private static void writePerformanceProfilingLogEnd(Class clazz, String methodNm) {
        final StringBuilder sb = new StringBuilder();
        sb.append("#Performance Profiling# [").append(clazz.getSimpleName()).append("] : ").append(methodNm).append(" - [E N D] ").append(System.currentTimeMillis());
        System.out.println(sb.toString());
    }

    private static void writePerformanceProfilingLogStart(Class clazz, String methodNm) {
        final StringBuilder sb = new StringBuilder();
        sb.append("#Performance Profiling# [").append(clazz.getSimpleName()).append("] : ").append(methodNm).append(" - [START] ").append(System.currentTimeMillis());
        System.out.println(sb.toString());
    }

    private static final class Cache {

        private final Map<String, CPOTMsg>      cpo     = new HashMap<String, CPOTMsg>();
        private final Map<String, CPO_DTLTMsg>  cpoDtl  = new HashMap<String, CPO_DTLTMsg>();
        private final Map<String, SHPG_PLNTMsg> shpgPln = new HashMap<String, SHPG_PLNTMsg>();
        //QC#13446
        private final Map<String, String>      allLineCreditFlg     = new HashMap<String, String>();

        private final Set<String> doneCpoDtlLvlValidationSet = new HashSet<String>();

        private final S21LRUMap<String, List<Map<String, String>>> billToSellToScreeningData = new S21LRUMap<String, List<Map<String, String>>>(200);
        private final S21LRUMap<String, List<Map<String, String>>> shipToScreeningData       = new S21LRUMap<String, List<Map<String, String>>>(200);
        private final S21LRUMap<String, List<Map<String, Object>>> hldCtrlTaxExemCache       = new S21LRUMap<String, List<Map<String, Object>>>();
    }

    private static final class HldRsnInfo {

        private String hldRsnCd;
        private String hldRsnNm;
        private String hldLvlCd;
        private String applyOrdSrcTpCd;
        private String applyOrdTpCd;
        private String exReqFlg;
        // START ADD S.Yamamoto [OM004]
        private String applyDsOrdTpCd;
        // END   ADD S.Yamamoto [OM004]

        private HldRsnInfo(ResultSet rs) throws SQLException {

            this.hldRsnCd        = rs.getString("HLD_RSN_CD");
            this.hldRsnNm        = rs.getString("HLD_RSN_NM");
            this.hldLvlCd        = rs.getString("HLD_LVL_CD");
            this.applyOrdSrcTpCd = rs.getString("APPLY_ORD_SRC_TP_CD");
            this.applyOrdTpCd    = rs.getString("APPLY_ORD_TP_CD");
            this.exReqFlg        = rs.getString("EX_REQ_FLG");
            // START ADD S.Yamamoto [OM004]
            this.applyDsOrdTpCd    = rs.getString("APPLY_DS_ORD_TP_CD");
            // END   ADD S.Yamamoto [OM004]
        }

        public String getApplyOrdSrcTpCd() {
            return applyOrdSrcTpCd;
        }

        public String getApplyOrdTpCd() {
            return applyOrdTpCd;
        }

        public String getExReqFlg() {
            return exReqFlg;
        }

        public String getHldLvlCd() {
            return hldLvlCd;
        }

        public String getHldRsnCd() {
            return hldRsnCd;
        }

        public String getHldRsnNm() {
            return hldRsnNm;
        }

        // START ADD S.Yamamoto [OM004]
        public String getApplyDsOrdTpCd() {
            return applyDsOrdTpCd;
        }
        // END   ADD S.Yamamoto [OM004]
    }

    // 2016/10/12 NA_QC#15097 Del Start
//    private static final class HldRsnInfoCreator extends S21SsmListResultSetHandlerSupport {
//
//        protected List<HldRsnInfo> doProcessQueryResult(ResultSet rs) throws SQLException {
//
//            // records.
//            rs.last();
//            int records = rs.getRow();
//            rs.first();
//
//            final List<HldRsnInfo> hldRsnInfoList = new ArrayList<HldRsnInfo>();
//
//            if (records > 0) {
//                do {
//                    hldRsnInfoList.add(new HldRsnInfo(rs));
//                } while (rs.next());
//            }
//
//            return hldRsnInfoList;
//        }
//    }
    // 2016/10/12 NA_QC#15097 Del End

    private static class OrderInfo {

        private String cpoSrcTpCd;
//        private String cpoOrdTpCd;
//        private String cpoDtlOrdTpCd;
        private String shpgPlnNum;
        private String trxHdrNum;
        private String trxLineNum;
        private String trxLineSubNum;
        private String shpgStsCd;
        private String mdseCd;
        private String setMdseCd;
        private String poReqFlg;
        private String billToCustCd;
        private String sellToCustCd;
        private String dropShipFlg;
        private String shipToCustCd;
        private String shipToLocNm;
        private String shipToFirstLineAddr;
        private String shipToScdLineAddr;
        private String shipToThirdLineAddr;
        private String shipToFrthLineAddr;
        private String shipToCtyAddr;
        private String shipToStCd;
        private String shipToPostCd;
        private String shipToCtryCd;
        private String shipToProvNm;
        private String shipToCntyNm;
        private String cancDelyLimitDt;
        private String cancShipLimitDt;
        private String slsRepOrSlsTeamTocCd ;
        private String spShipToCtryCd ;
        private String spShipToStCd ;
        private BigDecimal crChkQty;
        private BigDecimal crHldQty;
        // START ADD S.Yamamoto [OM004]
//        private String dsOrdTpCd;
        // END   ADD S.Yamamoto [OM004]
        // 2016/10/12 NA_QC#15097 Add Start
        private String hldRsnCd;
        private String hldRsnNm;
        private String hldLvlCd;
//        private String applyOrdSrcTpCd;
//        private String applyOrdTpCd;
        private String exReqFlg;
        private String applyDsOrdTpCd;
        // 2016/10/12 NA_QC#15097 Add End

        // QC#17941
        /** SHPG_PLN.EZUPTIME */
        private String ezuptimeSP;

        /** SHPG_PLN.EZUPTIMEZONE */
        private String ezuptimezoneSP;

        /** CPO_DTL.EZUPTIME */
        private String ezuptimeCD;

        /** CPO_DTL.EZUPTIMEZONE */
        private String ezuptimezoneCD;

        /** CPO.EZUPTIME */
        private String ezuptimeC;

        /** CPO.EZUPTIMEZONE */
        private String ezuptimezoneC;

        private OrderInfo(ResultSet rs) throws SQLException {

            this.shpgPlnNum           = rs.getString("SHPG_PLN_NUM");
            this.trxHdrNum            = rs.getString("TRX_HDR_NUM");
            this.trxLineNum           = rs.getString("TRX_LINE_NUM");
            this.trxLineSubNum        = rs.getString("TRX_LINE_SUB_NUM");
            this.shpgStsCd            = rs.getString("SHPG_STS_CD");
            this.mdseCd               = rs.getString("MDSE_CD");
            this.setMdseCd            = rs.getString("SET_MDSE_CD");
            this.poReqFlg             = rs.getString("PO_REQ_FLG");
//            this.cpoOrdTpCd           = rs.getString("CPO_ORD_TP_CD");
            this.cpoSrcTpCd           = rs.getString("CPO_SRC_TP_CD");
//            this.cpoDtlOrdTpCd        = rs.getString("CD_CPO_ORD_TP_CD");
            this.billToCustCd         = rs.getString("BILL_TO_CUST_CD");
            this.sellToCustCd         = rs.getString("SELL_TO_CUST_CD");
            this.dropShipFlg          = rs.getString("DROP_SHIP_FLG");
            this.shipToCustCd         = rs.getString("SHIP_TO_CUST_CD");
            this.shipToLocNm          = rs.getString("SHIP_TO_LOC_NM");
            this.shipToFirstLineAddr  = rs.getString("SHIP_TO_FIRST_LINE_ADDR");
            this.shipToScdLineAddr    = rs.getString("SHIP_TO_SCD_LINE_ADDR");
            this.shipToThirdLineAddr  = rs.getString("SHIP_TO_THIRD_LINE_ADDR");
            this.shipToFrthLineAddr   = rs.getString("SHIP_TO_FRTH_LINE_ADDR");
            this.shipToCtyAddr        = rs.getString("SHIP_TO_CTY_ADDR");
            this.shipToStCd           = rs.getString("SHIP_TO_ST_CD");
            this.shipToPostCd         = rs.getString("SHIP_TO_POST_CD");
            this.shipToCtryCd         = rs.getString("SHIP_TO_CTRY_CD");
            this.shipToProvNm         = rs.getString("SHIP_TO_PROV_NM");
            this.shipToCntyNm         = rs.getString("SHIP_TO_CNTY_NM");
            this.slsRepOrSlsTeamTocCd = rs.getString("SLS_REP_OR_SLS_TEAM_TOC_CD");
            this.spShipToCtryCd       = rs.getString("SP_SHIP_TO_CTRY_CD");
            this.spShipToStCd         = rs.getString("SP_SHIP_TO_ST_CD");
            this.crChkQty             = rs.getBigDecimal("CR_CHK_QTY");
            this.crHldQty             = rs.getBigDecimal("CR_HLD_QTY");
            // START ADD S.Yamamoto [OM004]
//            this.dsOrdTpCd            = rs.getString("DS_ORD_TP_CD");
            // END   ADD S.Yamamoto [OM004]
            // 2016/10/12 NA_QC#15097 Add Start
            this.hldRsnCd        = rs.getString("HLD_RSN_CD");
            this.hldRsnNm        = rs.getString("HLD_RSN_NM");
            this.hldLvlCd        = rs.getString("HLD_LVL_CD");
//            this.applyOrdSrcTpCd = rs.getString("APPLY_ORD_SRC_TP_CD");
//            this.applyOrdTpCd    = rs.getString("APPLY_ORD_TP_CD");
            this.exReqFlg        = rs.getString("EX_REQ_FLG");
            this.applyDsOrdTpCd    = rs.getString("APPLY_DS_ORD_TP_CD");
            // 2016/10/12 NA_QC#15097 Add End

            // QC#17941
            this.ezuptimeSP     = rs.getString("EZUPTIME_SP");
            this.ezuptimezoneSP = rs.getString("EZUPTIMEZONE_SP");
            this.ezuptimeCD     = rs.getString("EZUPTIME_CD");
            this.ezuptimezoneCD = rs.getString("EZUPTIMEZONE_CD");
            this.ezuptimeC      = rs.getString("EZUPTIME_C");
            this.ezuptimezoneC  = rs.getString("EZUPTIMEZONE_C");
        }

        private String getBillToCustCd() {
            return billToCustCd;
        }

        private String getCancDelyLimitDt() {
            return cancDelyLimitDt;
        }

        private String getCancShipLimitDt() {
            return cancShipLimitDt;
        }
        // 2016/10/12 NA_QC#15097 Mod Start
//
//        private String getCpoDtlOrdTpCd() {
//            return cpoDtlOrdTpCd;
//        }
//
//        private String getCpoOrdTpCd() {
//            return cpoOrdTpCd;
//        }
        // 2016/10/12 NA_QC#15097 Mod End

        private String getCpoSrcTpCd() {
            return cpoSrcTpCd;
        }

        private BigDecimal getCrChkQty() {
            return crChkQty;
        }

        private BigDecimal getCrHldQty() {
            return crHldQty;
        }

        private String getDropShipFlg() {
            return dropShipFlg;
        }

        private String getMdseCd() {
            return mdseCd;
        }

        private String getPoReqFlg() {
            return poReqFlg;
        }

        private String getSellToCustCd() {
            return sellToCustCd;
        }

        private String getSetMdseCd() {
            return setMdseCd;
        }

        private String getShipToCntyNm() {
            return shipToCntyNm;
        }

        private String getShipToCtryCd() {
            return shipToCtryCd;
        }

        private String getShipToCtyAddr() {
            return shipToCtyAddr;
        }

        private String getShipToCustCd() {
            return shipToCustCd;
        }

        private String getShipToFirstLineAddr() {
            return shipToFirstLineAddr;
        }

        private String getShipToFrthLineAddr() {
            return shipToFrthLineAddr;
        }

        private String getShipToLocNm() {
            return shipToLocNm;
        }

        private String getShipToPostCd() {
            return shipToPostCd;
        }

        private String getShipToProvNm() {
            return shipToProvNm;
        }

        private String getShipToScdLineAddr() {
            return shipToScdLineAddr;
        }

        private String getShipToStCd() {
            return shipToStCd;
        }

        private String getShipToThirdLineAddr() {
            return shipToThirdLineAddr;
        }

        private String getShpgPlnNum() {
            return shpgPlnNum;
        }

        private String getShpgStsCd() {
            return shpgStsCd;
        }

        private String getSlsRepOrSlsTeamTocCd() {
            return slsRepOrSlsTeamTocCd;
        }

        private String getSpShipToCtryCd() {
            return spShipToCtryCd;
        }

        private String getSpShipToStCd() {
            return spShipToStCd;
        }

        private String getTrxHdrNum() {
            return trxHdrNum;
        }

        private String getTrxLineNum() {
            return trxLineNum;
        }

        private String getTrxLineSubNum() {
            return trxLineSubNum;
        }
        // 2016/10/12 NA_QC#15097 Mod Start
//        // START ADD S.Yamamoto [OM004]
//        private String getDsOrdTpCd() {
//            return dsOrdTpCd;
//        }
//        // END   ADD S.Yamamoto [OM004]
//        // 2016/10/12 NA_QC#15097 Add Start
//        public String getApplyOrdSrcTpCd() {
//            return applyOrdSrcTpCd;
//        }
//
//        public String getApplyOrdTpCd() {
//            return applyOrdTpCd;
//        }
        // 2016/10/12 NA_QC#15097 Mod Start

        public String getExReqFlg() {
            return exReqFlg;
        }

        public String getHldLvlCd() {
            return hldLvlCd;
        }

        public String getHldRsnCd() {
            return hldRsnCd;
        }

        public String getHldRsnNm() {
            return hldRsnNm;
        }

        public String getApplyDsOrdTpCd() {
            return applyDsOrdTpCd;
        }
        // 2016/10/12 NA_QC#15097 Add End

        // QC#17941
        public String getEzuptimeSP() {
            return ezuptimeSP;
        }

        public String getEzuptimezoneSP() {
            return ezuptimezoneSP;
        }

        public String getEzuptimeCD() {
            return ezuptimeCD;
        }

        public String getEzuptimezoneCD() {
            return ezuptimezoneCD;
        }

        public String getEzuptimeC() {
            return ezuptimeC;
        }

        public String getEzuptimezoneC() {
            return ezuptimezoneC;
        }

    }

    // 2016/10/12 NA_QC#15097 Mod
    // private final class OrderValidator extends S21SsmListResultSetHandlerSupport {
    private final class OrderValidator {
        private final Cache cache = new Cache();

//        private final List<HldRsnInfo>    hldRsnInfoList;
        private final Map<String, String> validationApiMap;

        // 2016/10/12 NA_QC#15097 Mod
        private OrderValidator() {
        //private OrderValidator(List<HldRsnInfo> hldRsnInfoList) {

//            this.hldRsnInfoList = hldRsnInfoList;

            this.validationApiMap = new HashMap<String, String>();
            validationApiMap.put(SHIPPING_CONTRACT_RESTRICTION, NWZC010001.class.getName()); // Shipping Contract Restriction API
            validationApiMap.put(CANCELLATION_DATE_PAST,        NWZC017001.class.getName()); // Cancellation Date Past Hold API
            validationApiMap.put(SHIPPING_HOLD,                 NWZC018001.class.getName()); // Shipping Hold API
            validationApiMap.put(OVER_DUE,                      NWZC019001.class.getName()); // Over Due Hold API
        }

        //@Override
        protected List<NWZC044001PMsg> doProcessQueryResult(ResultSet rs) throws SQLException {
            final String methodNm = "doProcessQueryResult";
            writePerformanceProfilingLogStart(getClass(), methodNm);

            try {

                // records.
                // 2016/10/12 NA_QC#15097 Mod Start
                rs.next();
                // rs.last();
                int records = rs.getRow();
                rs.first();
                //writePerformanceProfilingLog(getClass(), " #records = " + records);
                // 2016/10/12 NA_QC#15097 Mod End

                if (records == 0) {
                    infoLogOut(NWAM0188I, new SHPG_PLNTMsg().getTableName());
                    return emptyList();
                }

                return validate(rs);

            } finally {
                writePerformanceProfilingLogEnd(getClass(), methodNm);
            }
        }

        protected List<NWZC044001PMsg> validate(ResultSet rs) throws SQLException {
            final String methodNm = "validate";
            writePerformanceProfilingLogStart(getClass(), methodNm);

            try {

                final List<NWZC044001PMsg> hldPMsgList = new ArrayList<NWZC044001PMsg>();

                // HLD_CTRL_ORG
                final List<Map<String, Object>> hldCtrlOrgList = getHldCtrlOrgList();
                if (hldCtrlOrgList.isEmpty()) {
                    return hldPMsgList;
                }

                final Set<String> doneOrderSet = new HashSet<String>();

                do {

                    // Order Info
                    final OrderInfo orderInfo = new OrderInfo(rs);

                    // QC#17941
                    SHPG_PLNTMsg spTMsg = new SHPG_PLNTMsg();
                    ZYPEZDItemValueSetter.setValue(spTMsg.shpgPlnNum, orderInfo.getShpgPlnNum());
                    ZYPEZDItemValueSetter.setValue(spTMsg.ezUpTime, orderInfo.getEzuptimeSP());
                    ZYPEZDItemValueSetter.setValue(spTMsg.ezUpTimeZone, orderInfo.getEzuptimezoneSP());
                    targetTimestampSP.put(orderInfo.getShpgPlnNum(), spTMsg);

                    CPO_DTLTMsg cdTMsg = new CPO_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(cdTMsg.cpoOrdNum, orderInfo.getTrxHdrNum());
                    ZYPEZDItemValueSetter.setValue(cdTMsg.cpoDtlLineNum, orderInfo.getTrxLineNum());
                    ZYPEZDItemValueSetter.setValue(cdTMsg.cpoDtlLineSubNum, orderInfo.getTrxLineSubNum());
                    ZYPEZDItemValueSetter.setValue(cdTMsg.ezUpTime, orderInfo.getEzuptimeCD());
                    ZYPEZDItemValueSetter.setValue(cdTMsg.ezUpTimeZone, orderInfo.getEzuptimezoneCD());
                    targetTimestampCD.put(//
                            createCacheKey(orderInfo.getTrxHdrNum(), orderInfo.getTrxLineNum(), orderInfo.getTrxLineSubNum()), cdTMsg);

                    // is changed 'CPO_ORD_NUM'?
                    final String doneOrderKey = createCacheKey(orderInfo.getCpoSrcTpCd(), orderInfo.getTrxHdrNum());
                    if (!doneOrderSet.contains(doneOrderKey)) {
                        this.cache.cpo.clear();
                        this.cache.cpoDtl.clear();
                        this.cache.shpgPln.clear();
                        this.cache.doneCpoDtlLvlValidationSet.clear();
                        doneOrderSet.add(doneOrderKey);

                        // QC#17941
                        CPOTMsg cTMsg = new CPOTMsg();
                        ZYPEZDItemValueSetter.setValue(cTMsg.cpoOrdNum, orderInfo.getTrxHdrNum());
                        ZYPEZDItemValueSetter.setValue(cTMsg.ezUpTime, orderInfo.getEzuptimeC());
                        ZYPEZDItemValueSetter.setValue(cTMsg.ezUpTimeZone, orderInfo.getEzuptimezoneC());
                        targetTimestampC.put(orderInfo.getTrxHdrNum(), cTMsg);
                    }

                    // 2016/10/12 NA_QC#15097 Mod Start
                    // HLD_RSN
//                    for (HldRsnInfo hldRsnInfo : hldRsnInfoList) {
//
//                        // START ADD S.Yamamoto [OM004]
//                        if (!hldRsnInfo.getApplyDsOrdTpCd().equals(orderInfo.getDsOrdTpCd())) {
//                            continue;
//                        }
                        // END   ADD S.Yamamoto [OM004]

                        //final String hldRsnCd = hldRsnInfo.getHldRsnCd();
                    final String hldRsnCd = orderInfo.getHldRsnCd();

                    if (!checkHoldCtrlOrg(hldRsnCd, glblCmpyCd, orderInfo.getSlsRepOrSlsTeamTocCd(), hldCtrlOrgList)) {
                        continue;
                    }

                    if (MISSING_TAX_EXEMPT_CERTIFICATE.equals(hldRsnCd)) {
                        if (!checkHoldCtrlTaxExem(orderInfo)) {
                        //if (!checkHoldCtrlTaxExem(orderInfo, hldRsnInfo)) { // 2016/10/12 NA_QC#15097 Mod
                            continue;
                        }
                    }

                    if (CANCELLATION_DATE_PAST.equals(hldRsnCd)) {
                        if (!hasValue(orderInfo.getCancDelyLimitDt()) && !hasValue(orderInfo.getCancShipLimitDt())) {
                            continue;
                        }
                    }

                    if (ValidationBatchMode.NIGHT == validationBatchMode) {
                        if (!asList(DPL_SCREENING, EMBARGO, CANCELLATION_DATE_PAST, OVER_DUE).contains(hldRsnCd)) {
                            if (isEquals(SHPG_STS.VALIDATED, orderInfo.getShpgStsCd())) {
                                String whType = getWhType(orderInfo.getMdseCd(), orderInfo.getPoReqFlg());
                                if (WH_TP.COMMON.equals(whType)) {
                                    continue;
                                }
                            }
                        }
                        if (OVER_DUE.equals(hldRsnCd)) {
                            // Def#1481 Modify
                            if (ZERO.compareTo(orderInfo.getCrChkQty()) == 0 && ZERO.compareTo(orderInfo.getCrHldQty()) == 0) {
                            //if (ZERO.compareTo(orderInfo.getCrChkQty()) == 0) {
                                continue;
                            }
                        }
                    }

                    // ***** validation
                    if (this.validationApiMap.keySet().contains(hldRsnCd) || isDPL(hldRsnCd)) {
                        //hldPMsgList.addAll(validation(orderInfo, hldRsnInfo)); // 2016/10/12 NA_QC#15097 Mod
                        hldPMsgList.addAll(validation(orderInfo));
                    }
//                    }
                      // 2016/10/12 NA_QC#15097 Mod End
                } while (rs.next());

                return hldPMsgList;

            } finally {
                writePerformanceProfilingLogEnd(getClass(), methodNm);
            }
        }

        //QC#13446
        //List<NWZC044001PMsg> callValidationAPI(String hldRsnCd, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDtlTMsg, SHPG_PLNTMsg shpgPlnTMsg) {
        //QC#13714
        //List<NWZC044001PMsg> callValidationAPI(String hldRsnCd, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDtlTMsg, SHPG_PLNTMsg shpgPlnTMsg, String allLineCreditFlg) {
        List<NWZC044001PMsg> callValidationAPI(String hldRsnCd, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDtlTMsg, SHPG_PLNTMsg shpgPlnTMsg, String allLineCreditFlg) {

            String cpoOrdNum        = null;
            String cpoDtlLineNum    = null;
            String cpoDtlLineSubNum = null;
            String shpgPlnNum       = null;

            // SHPG_PLN
            if (shpgPlnTMsg != null) {
                cpoOrdNum        = shpgPlnTMsg.trxHdrNum.getValue();
                cpoDtlLineNum    = shpgPlnTMsg.trxLineNum.getValue();
                cpoDtlLineSubNum = shpgPlnTMsg.trxLineSubNum.getValue();
                shpgPlnNum       = shpgPlnTMsg.shpgPlnNum.getValue();
            // CPO_DTL
            } else if (cpoDtlTMsg != null) {
                cpoOrdNum        = cpoDtlTMsg.cpoOrdNum.getValue();
                cpoDtlLineNum    = cpoDtlTMsg.cpoDtlLineNum.getValue();
                cpoDtlLineSubNum = cpoDtlTMsg.cpoDtlLineSubNum.getValue();
            // CPO
            } else {
                cpoOrdNum        = cpoTMsg.cpoOrdNum.getValue();
            }

            final NWXC005001PMsg apiPMsg = new NWXC005001PMsg();
            setValue(apiPMsg.glblCmpyCd,         glblCmpyCd);
            setValue(apiPMsg.slsDt,              slsDt);
            setValue(apiPMsg.cpoOrdNum_I,        cpoOrdNum);
            setValue(apiPMsg.cpoDtlLineNum_I,    cpoDtlLineNum);
            setValue(apiPMsg.cpoDtlLineSubNum_I, cpoDtlLineSubNum);
            setValue(apiPMsg.shpgPlnNum_I,       shpgPlnNum);

            //QC#13714
            //final NWXC005001ValidationBean apiBean = new NWXC005001ValidationBean(apiPMsg, cpoTMsg, cpoDtlTMsg, shpgPlnTMsg);
            final NWXC005001ValidationBean apiBean = new NWXC005001ValidationBean(apiPMsg, cpoTMsg, cpoDtlTMsg, shpgPlnTMsg);

            // Validation API
            S21ApiCommonBase validationAPI = null;
            try {
                validationAPI = (S21ApiCommonBase) Class.forName(this.validationApiMap.get(hldRsnCd)).newInstance();
            } catch (Exception e) {
                throw toS21AbendException(e);
            }

            /**************************************************
             * NWZC010001 : Shipping Contract Restriction API
             **************************************************/
            if (validationAPI instanceof NWZC010001) {
                ((NWZC010001) validationAPI).execute(apiBean, ONBATCH_TYPE.BATCH);

            /**************************************************
             * NWZC017001 : Cancellation Date Past Hold API
             **************************************************/
            } else if (validationAPI instanceof NWZC017001) {
                ((NWZC017001) validationAPI).execute(apiBean, ONBATCH_TYPE.BATCH);

            /**************************************************
             * NWZC018001 : Shipping Hold API
             **************************************************/
            } else if (validationAPI instanceof NWZC018001) {
                ((NWZC018001) validationAPI).execute(apiBean, ONBATCH_TYPE.BATCH);

            /**************************************************
             * NWZC019001 : Over Due Hold API
             **************************************************/
            } else if (validationAPI instanceof NWZC019001) {
                //QC#13446
                if (!Y.equals(allLineCreditFlg) && !processedOrdNumList.contains(cpoOrdNum)) { // S21_NA#14973 Mod
                    ((NWZC019001) validationAPI).execute(apiBean, ONBATCH_TYPE.BATCH);
                }

            } else {
                return emptyList();
            }

            return toHldPMsg(hldRsnCd, apiPMsg);
        }

        List<NWZC044001PMsg> toHldPMsg(String hldRsnCd, NWXC005001PMsg resApiPMsg) {

            final List<NWZC044001PMsg> hldPMsgList = new ArrayList<NWZC044001PMsg>();

            if (resApiPMsg.xxMsgIdList.getValidCount() > 0) {

                String errMessageId = resApiPMsg.xxMsgIdList.no(0).xxMsgId.getValue();
                infoLogOut(errMessageId);
                throw new S21AbendException(errMessageId);
            }

            final String rtnHldRsnCd = resApiPMsg.hldRsnCd.getValue();

            if (hasValue(rtnHldRsnCd)) {
                // S21_NA#14973 Mod Start
                if (HLD_RSN.OVER_DUE.equals(rtnHldRsnCd)) {

                    // QC#17941
//                    NWZC044001PMsg hldPMsg = null;
//
//                    for (int i = 0; i < resApiPMsg.shpgPlnList.getValidCount(); i++) {
//                        hldPMsg = new NWZC044001PMsg();
//                        NWXC005001_shpgPlnListPMsg shpgPlnPMsg = resApiPMsg.shpgPlnList.no(i);
//                        setValue(hldPMsg.glblCmpyCd,       glblCmpyCd);
//                        setValue(hldPMsg.cpoOrdNum,        shpgPlnPMsg.cpoOrdNum);
//                        setValue(hldPMsg.cpoDtlLineNum,    shpgPlnPMsg.cpoDtlLineNum);
//                        setValue(hldPMsg.cpoDtlLineSubNum, shpgPlnPMsg.cpoDtlLineSubNum);
//                        setValue(hldPMsg.shpgPlnNum,       shpgPlnPMsg.shpgPlnNum);
//                        setValue(hldPMsg.hldRsnCd,         shpgPlnPMsg.hldRsnCd);
//                        setValue(hldPMsg.slsDt,            slsDt);
//                        hldPMsgList.add(hldPMsg);
//
//                        writePerformanceProfilingLog(getClass(), "    #Hld = hldRsnCd=[" + hldRsnCd + "], cpoOrdNum=[" + hldPMsg.cpoOrdNum.getValue() + "." + hldPMsg.cpoDtlLineNum.getValue() + "." + hldPMsg.cpoDtlLineSubNum.getValue() + "] shpgPlnNum=[" + hldPMsg.shpgPlnNum.getValue() + "]");
//                    }
//                    processedOrdNumList.add(resApiPMsg.shpgPlnList.no(0).cpoOrdNum.getValue());

                    NWZC044001PMsg hldPMsg = new NWZC044001PMsg();
                    setValue(hldPMsg.glblCmpyCd,       glblCmpyCd);
                    setValue(hldPMsg.cpoOrdNum,        resApiPMsg.cpoOrdNum_O);
                    setValue(hldPMsg.hldRsnCd,         resApiPMsg.hldRsnCd);
                    setValue(hldPMsg.slsDt,            slsDt);
                    hldPMsgList.add(hldPMsg);

                    writePerformanceProfilingLog(getClass(), "    #Hld = hldRsnCd=[" + hldRsnCd + "], cpoOrdNum=[" + hldPMsg.cpoOrdNum.getValue() + "]");
                    processedOrdNumList.add(resApiPMsg.cpoOrdNum_O.getValue());

                } else {
                    final NWZC044001PMsg hldPMsg = new NWZC044001PMsg();
                    hldPMsgList.add(hldPMsg);

                    setValue(hldPMsg.glblCmpyCd,       glblCmpyCd);
                    setValue(hldPMsg.cpoOrdNum,        resApiPMsg.cpoOrdNum_O);
                    setValue(hldPMsg.cpoDtlLineNum,    resApiPMsg.cpoDtlLineNum_O);
                    setValue(hldPMsg.cpoDtlLineSubNum, resApiPMsg.cpoDtlLineSubNum_O);
                    setValue(hldPMsg.shpgPlnNum,       resApiPMsg.shpgPlnNum_O);
                    setValue(hldPMsg.hldRsnCd,         rtnHldRsnCd);
                    setValue(hldPMsg.slsDt,            slsDt);

                    writePerformanceProfilingLog(getClass(), "    #Hld = hldRsnCd=[" + hldRsnCd + "], cpoOrdNum=[" + hldPMsg.cpoOrdNum.getValue() + "." + hldPMsg.cpoDtlLineNum.getValue() + "." + hldPMsg.cpoDtlLineSubNum.getValue() + "] shpgPlnNum=[" + hldPMsg.shpgPlnNum.getValue() + "]");
                }
                // S21_NA#14973 Mod End
            }

            return hldPMsgList;
        }

         // 2016/10/12 NA_QC#15097 Mod
        List<NWZC044001PMsg> validation(OrderInfo orderInfo) {
        //List<NWZC044001PMsg> validation(OrderInfo orderInfo, HldRsnInfo hldRsnInfo) {

            // 2016/10/12 NA_QC#15097 Mod Start
            final String cpoSrcTpCd      = orderInfo.getCpoSrcTpCd();
            //final String cpoOrdTpCd      = orderInfo.getCpoOrdTpCd();
            //final String cpoDtlOrdTpCd   = orderInfo.getCpoDtlOrdTpCd();
            final String trxHdrNum       = orderInfo.getTrxHdrNum();
            final String trxLineNum      = orderInfo.getTrxLineNum();
            final String trxLineSubNum   = orderInfo.getTrxLineSubNum();
            final String shpgPlnNum      = orderInfo.getShpgPlnNum();
            // START ADD S.Yamamoto [OM004]
            //final String dsOrdTpCd       = orderInfo.getDsOrdTpCd();
            // END   ADD S.Yamamoto [OM004]

            // 2016/10/12 NA_QC#15097 Mod Start
            final String hldLvlCd        = orderInfo.getHldLvlCd();
            final String hldRsnCd        = orderInfo.getHldRsnCd();
            final String hldRsnNm        = orderInfo.getHldRsnNm();
            //final String applyOrdSrcTpCd = orderInfo.getApplyOrdSrcTpCd();
            //final String applyOrdTpCd    = orderInfo.getApplyOrdTpCd();
            final String exReqFlg        = orderInfo.getExReqFlg();
            // START ADD S.Yamamoto [OM004]
            //final String applyDsOrdTpCd    = orderInfo.getApplyDsOrdTpCd();
            // END   ADD S.Yamamoto [OM004]
            // 2016/10/12 NA_QC#15097 Mod End

            /**************************************************
             * CPO_LEVEL
             **************************************************/
            if (isEquals(CPO_LEVEL, hldLvlCd)) {
                // START MODIFY S.Yamamoto [OM004]
                //if (isEquals(applyOrdSrcTpCd, cpoSrcTpCd) && isEquals(applyOrdTpCd, cpoOrdTpCd) && isEquals(applyDsOrdTpCd,dsOrdTpCd)) { // 2016/10/12 NA_QC#15097 Del
//                if (isEquals(applyOrdSrcTpCd, cpoSrcTpCd) && isEquals(applyOrdTpCd, cpoOrdTpCd)) {
                // END   MODIFY S.Yamamoto [OM004]

                    // DPL_SCREENING || EMBARGO
                    if (isDPL(hldRsnCd)) {

                        final Map<String, Object> valParam = new HashMap<String, Object>();
                        valParam.put(P_CPO_ORD_NUM_I,          trxHdrNum);
                        valParam.put(P_CPO_DTL_LINE_NUM_I,     "");
                        valParam.put(P_CPO_DTL_LINE_SUB_NUM_I, "");
                        valParam.put(P_SHPG_PLN_NUM_I,         "");
                        valParam.put(P_EX_REQ_FLG,             exReqFlg);

                        final NWZC044001PMsg dplHldPMsg = validateDPL(hldRsnCd, valParam);
                        if (dplHldPMsg != null) {
                            dplHldPMsgList.add(dplHldPMsg);
                        }

                    // call Validation API
                    } else {

                        final CPOTMsg      cpoTMsg     = getCpo(trxHdrNum);
                        //QC#13714
                        final CPO_DTLTMsg  cpoDtlTMsg  = null;
                        //QC#13714
                        final SHPG_PLNTMsg shpgPlnTMsg = null;
                        final String allLineCreditFlg = getIsAllLineCredit(trxHdrNum);

                        writePerformanceProfilingLog(getClass(), " - HLD_LVL=[CPO],      HLD_RSN_CD=[" + hldRsnCd + "], HLD_RSN_NM=[" + hldRsnNm + "] (" + trxHdrNum + ")");
                        //QC#13714
                        //return callValidationAPI(hldRsnCd, cpoTMsg, cpoDtlTMsg, shpgPlnTMsg, allLineCreditFlg);
                        return callValidationAPI(hldRsnCd, cpoTMsg, cpoDtlTMsg, shpgPlnTMsg, allLineCreditFlg);
                    }
                //}

            /**************************************************
             * CPO_DETAIL_LEVEL
             **************************************************/
            } else if (isEquals(CPO_DETAIL_LEVEL, hldLvlCd)) {
                // START MODIFY S.Yamamoto [OM004]
                //if (isEquals(applyOrdSrcTpCd, cpoSrcTpCd) && isEquals(applyOrdTpCd, cpoDtlOrdTpCd) && isEquals(applyDsOrdTpCd, dsOrdTpCd)) { // 2016/10/12 NA_QC#15097 Del
//                if (isEquals(applyOrdSrcTpCd, cpoSrcTpCd) && isEquals(applyOrdTpCd, cpoDtlOrdTpCd)) {
                // END   MODIFY S.Yamamoto [OM004]

                    // already done validation?
                    final String doneValidationKey = createCacheKey(cpoSrcTpCd, trxHdrNum, trxLineNum, trxLineSubNum, hldRsnCd);
                    if (this.cache.doneCpoDtlLvlValidationSet.contains(doneValidationKey)) {
                        return emptyList();
                    } else {
                        this.cache.doneCpoDtlLvlValidationSet.add(doneValidationKey);
                    }

                    // DPL_SCREENING || EMBARGO
                    if (isDPL(hldRsnCd)) {

                        final Map<String, Object> valParam = new HashMap<String, Object>();
                        valParam.put(P_CPO_ORD_NUM_I,           trxHdrNum);
                        valParam.put(P_CPO_DTL_LINE_NUM_I,      trxLineNum);
                        valParam.put(P_CPO_DTL_LINE_SUB_NUM_I,  trxLineSubNum);
                        valParam.put(P_SHPG_PLN_NUM_I,          "");
                        valParam.put(P_EX_REQ_FLG,              exReqFlg);
                        valParam.put(P_MDSE_CD,                 orderInfo.getMdseCd());
                        valParam.put(P_SET_MDSE_CD,             orderInfo.getSetMdseCd());
                        valParam.put(P_BILL_TO_CUST_CD,         orderInfo.getBillToCustCd());
                        valParam.put(P_SELL_TO_CUST_CD,         orderInfo.getSellToCustCd());
                        valParam.put(P_DROP_SHIP_FLG,           orderInfo.getDropShipFlg());
                        valParam.put(P_SHIP_TO_CUST_CD,         orderInfo.getShipToCustCd());
                        valParam.put(P_SHIP_TO_LOC_NM,          orderInfo.getShipToLocNm());
                        valParam.put(P_SHIP_TO_FIRST_LINE_ADDR, orderInfo.getShipToFirstLineAddr());
                        valParam.put(P_SHIP_TO_SCD_LINE_ADDR,   orderInfo.getShipToScdLineAddr());
                        valParam.put(P_SHIP_TO_THIRD_LINE_ADDR, orderInfo.getShipToThirdLineAddr());
                        valParam.put(P_SHIP_TO_FRTH_LINE_ADDR,  orderInfo.getShipToFrthLineAddr());
                        valParam.put(P_SHIP_TO_CTY_ADDR,        orderInfo.getShipToCtyAddr());
                        valParam.put(P_SHIP_TO_ST_CD,           orderInfo.getShipToStCd());
                        valParam.put(P_ST_NM,                   getStNm(orderInfo.getShipToStCd()));
                        valParam.put(P_SHIP_TO_POST_CD,         orderInfo.getShipToPostCd());
                        valParam.put(P_SHIP_TO_CTRY_CD,         orderInfo.getShipToCtryCd());
                        valParam.put(P_SHIP_TO_PROV_NM,         orderInfo.getShipToProvNm());
                        valParam.put(P_SHIP_TO_CNTY_NM,         orderInfo.getShipToCntyNm());

                        final NWZC044001PMsg dplHldPMsg = validateDPL(hldRsnCd, valParam);
                        if (dplHldPMsg != null) {
                            dplHldPMsgList.add(dplHldPMsg);
                        }

                    // call Validation API
                    } else {

                        final CPOTMsg      cpoTMsg     = getCpo(trxHdrNum);
                        //QC#13714
                        final CPO_DTLTMsg  cpoDtlTMsg  = getCpoDtl(trxHdrNum, trxLineNum, trxLineSubNum);
                        //QC#13714
                        final SHPG_PLNTMsg shpgPlnTMsg = null;
                        final String allLineCreditFlg = getIsAllLineCredit(trxHdrNum);

                        writePerformanceProfilingLog(getClass(), " - HLD_LVL=[CPO_DTL],  HLD_RSN_CD=[" + hldRsnCd + "], HLD_RSN_NM=[" + hldRsnNm + "] (" + trxHdrNum + "." + trxLineNum + "." + trxLineSubNum + ")");
                        //QC#13714
                        //return callValidationAPI(hldRsnCd, cpoTMsg, cpoDtlTMsg, shpgPlnTMsg, allLineCreditFlg);
                        return callValidationAPI(hldRsnCd, cpoTMsg, cpoDtlTMsg, shpgPlnTMsg, allLineCreditFlg);
                    }
                //}

            /**************************************************
             * SHIPPING_PLAN_LEVEL
             **************************************************/
            } else if (isEquals(SHIPPING_PLAN_LEVEL, hldLvlCd)) {
                // START MODIFY S.Yamamoto [OM004]
                //if (isEquals(applyOrdSrcTpCd, cpoSrcTpCd) && isEquals(applyOrdTpCd, cpoDtlOrdTpCd) && isEquals(applyDsOrdTpCd, dsOrdTpCd)) { // 2016/10/12 NA_QC#15097 Del
//                if (isEquals(applyOrdSrcTpCd, cpoSrcTpCd) && isEquals(applyOrdTpCd, cpoDtlOrdTpCd)) {
                    // END   MODIFY S.Yamamoto [OM004]

                    // DPL_SCREENING || EMBARGO
                    if (isDPL(hldRsnCd)) {

                        final Map<String, Object> valParam = new HashMap<String, Object>();
                        valParam.put(P_CPO_ORD_NUM_I,          trxHdrNum);
                        valParam.put(P_CPO_DTL_LINE_NUM_I,     trxLineNum);
                        valParam.put(P_CPO_DTL_LINE_SUB_NUM_I, trxLineSubNum);
                        valParam.put(P_SHPG_PLN_NUM_I,         shpgPlnNum);
                        valParam.put(P_EX_REQ_FLG,             exReqFlg);

                        final NWZC044001PMsg dplHldPMsg = validateDPL(hldRsnCd, valParam);
                        if (dplHldPMsg != null) {
                            dplHldPMsgList.add(dplHldPMsg);
                        }

                    // call Validation API
                    } else {

                        final CPOTMsg      cpoTMsg     = getCpo(trxHdrNum);
                        //QC#13714
                        final CPO_DTLTMsg  cpoDtlTMsg  = getCpoDtl(trxHdrNum, trxLineNum, trxLineSubNum);
                        //QC#13714
                        final SHPG_PLNTMsg shpgPlnTMsg = getShpgPln(shpgPlnNum);
                        final String allLineCreditFlg = getIsAllLineCredit(trxHdrNum);

                        writePerformanceProfilingLog(getClass(), " - HLD_LVL=[SHPG_PLN], HLD_RSN_CD=[" + hldRsnCd + "], HLD_RSN_NM=[" + hldRsnNm + "] (" + trxHdrNum + "." + trxLineNum + "." + trxLineSubNum + "." + shpgPlnNum + ")");
                        //QC#13714
                        //return callValidationAPI(hldRsnCd, cpoTMsg, cpoDtlTMsg, shpgPlnTMsg, allLineCreditFlg);
                        return callValidationAPI(hldRsnCd, cpoTMsg, cpoDtlTMsg, shpgPlnTMsg, allLineCreditFlg);
                    }
                //}
            }

            return emptyList();
        }

        private boolean checkHoldCtrlOrg(String targetHldRsnCd, String glblCmpyCd, String slsRepOrSlsTeamTocCd, List<Map<String, Object>> holdCtrlOrgList) {

            //---------------------------------------------------------------------
            //If data doesn't exist in the HLD_CTRL_ORG table, Hold is not created. 
            //---------------------------------------------------------------------
            //TOC_CD
            S21_ORGTMsg orgMsg = getTocCd(glblCmpyCd, slsRepOrSlsTeamTocCd);
            if (orgMsg == null) {
                return false;
            }

            for (Map<String, Object> holdCtrlOrg : holdCtrlOrgList) {

                String hldRsnCd    = (String) holdCtrlOrg.get("HLD_RSN_CD");
                String tocCd       = (String) holdCtrlOrg.get("TOC_CD");
                String eighthOrgCd = (String) holdCtrlOrg.get("EIGHTH_ORG_CD");
                String svnthOrgCd  = (String) holdCtrlOrg.get("SVNTH_ORG_CD");
                String sixthOrgCd  = (String) holdCtrlOrg.get("SIXTH_ORG_CD");
                String fifthOrgCd  = (String) holdCtrlOrg.get("FIFTH_ORG_CD");
                String frthOrgCd   = (String) holdCtrlOrg.get("FRTH_ORG_CD");
                String thirdOrgCd  = (String) holdCtrlOrg.get("THIRD_ORG_CD");
                String scdOrgCd    = (String) holdCtrlOrg.get("SCD_ORG_CD");
                String firstOrgCd  = (String) holdCtrlOrg.get("FIRST_ORG_CD");

                if (!isEquals(targetHldRsnCd, hldRsnCd)) {
                    continue;
                }


                if (hasValue(firstOrgCd) && isEquals("*", firstOrgCd)) {
                    return true;


                } else if (hasValue(tocCd)) {

                    if (isEquals(tocCd, orgMsg.tocCd.getValue())) {
                        return true;

                    }

                } else if (hasValue(eighthOrgCd)) {

                    if (isEquals(eighthOrgCd, orgMsg.eighthOrgCd.getValue())
                            && isEquals(svnthOrgCd, orgMsg.svnthOrgCd.getValue())
                            && isEquals(sixthOrgCd, orgMsg.sixthOrgCd.getValue())
                            && isEquals(fifthOrgCd, orgMsg.fifthOrgCd.getValue())
                            && isEquals(frthOrgCd,  orgMsg.frthOrgCd.getValue())
                            && isEquals(thirdOrgCd, orgMsg.thirdOrgCd.getValue())
                            && isEquals(scdOrgCd,   orgMsg.scdOrgCd.getValue())
                            && isEquals(firstOrgCd, orgMsg.firstOrgCd.getValue())) {
                        return true;
                    }
                } else if (hasValue(svnthOrgCd)) {

                    if (isEquals(svnthOrgCd, orgMsg.svnthOrgCd.getValue())
                            && isEquals(sixthOrgCd, orgMsg.sixthOrgCd.getValue())
                            && isEquals(fifthOrgCd, orgMsg.fifthOrgCd.getValue())
                            && isEquals(frthOrgCd,  orgMsg.frthOrgCd.getValue())
                            && isEquals(thirdOrgCd, orgMsg.thirdOrgCd.getValue())
                            && isEquals(scdOrgCd,   orgMsg.scdOrgCd.getValue())
                            && isEquals(firstOrgCd, orgMsg.firstOrgCd.getValue())) {
                        return true;

                    }
                } else if (hasValue(sixthOrgCd)) {

                    if (isEquals(sixthOrgCd, orgMsg.sixthOrgCd.getValue())
                            && isEquals(fifthOrgCd, orgMsg.fifthOrgCd.getValue())
                            && isEquals(frthOrgCd,  orgMsg.frthOrgCd.getValue())
                            && isEquals(thirdOrgCd, orgMsg.thirdOrgCd.getValue())
                            && isEquals(scdOrgCd,   orgMsg.scdOrgCd.getValue())
                            && isEquals(firstOrgCd, orgMsg.firstOrgCd.getValue())) {
                        return true;

                    }
                } else if (hasValue(fifthOrgCd)) {

                    if (isEquals(fifthOrgCd, orgMsg.fifthOrgCd.getValue())
                            && isEquals(frthOrgCd,  orgMsg.frthOrgCd.getValue())
                            && isEquals(thirdOrgCd, orgMsg.thirdOrgCd.getValue())
                            && isEquals(scdOrgCd,   orgMsg.scdOrgCd.getValue())
                            && isEquals(firstOrgCd, orgMsg.firstOrgCd.getValue())) {
                        return true;

                    }
                } else if (hasValue(frthOrgCd)) {

                    if (isEquals(frthOrgCd, orgMsg.frthOrgCd.getValue())
                            && isEquals(thirdOrgCd, orgMsg.thirdOrgCd.getValue())
                            && isEquals(scdOrgCd,   orgMsg.scdOrgCd.getValue())
                            && isEquals(firstOrgCd, orgMsg.firstOrgCd.getValue())) {
                        return true;

                    }
                } else if (hasValue(thirdOrgCd)) {

                    if (isEquals(thirdOrgCd, orgMsg.thirdOrgCd.getValue())
                            && isEquals(scdOrgCd,   orgMsg.scdOrgCd.getValue())
                            && isEquals(firstOrgCd, orgMsg.firstOrgCd.getValue())) {
                        return true;

                    }
                } else if (hasValue(scdOrgCd)) {

                    if (isEquals(scdOrgCd, orgMsg.scdOrgCd.getValue())
                            && isEquals(firstOrgCd, orgMsg.firstOrgCd.getValue())) {
                        return true;

                    }
                } else if (hasValue(firstOrgCd)) {

                    if (isEquals(firstOrgCd, orgMsg.firstOrgCd.getValue())) {
                        return true;

                    }
                }
            }
            return false;
        }

        /** check HLD_CTRL_TAX_EXEM */
        @SuppressWarnings("unchecked")
        // 2016/10/12 NA_QC#15097 Mod Start
        private boolean checkHoldCtrlTaxExem(final OrderInfo orderInfo) {
        //private boolean checkHoldCtrlTaxExem(final OrderInfo orderInfo, final HldRsnInfo hldRsnInfo) {

            //---------------------------------------------------------------------
            // If country and state doesn't have any sales tax, tax Hold is not create.
            //--------------------------------------------------------------------- 
            String paramCtryCd = "";
            String paramStCd = "";

            String hldLvlCd        = orderInfo.getHldLvlCd();
            if (isEquals(CPO_DETAIL_LEVEL, hldLvlCd)) {
                // [CPO_DTL] Level
                paramCtryCd = orderInfo.getShipToCtryCd();
                paramStCd = orderInfo.getShipToStCd();

            } else if (isEquals(SHIPPING_PLAN_LEVEL, hldLvlCd)) {
                // [SHPG_PLN] Level
                paramCtryCd = orderInfo.getSpShipToCtryCd();
                paramStCd = orderInfo.getSpShipToStCd();
            }

            // find & cache
            String key = glblCmpyCd;
            List<Map<String, Object>> hldCtrlTaxExemCacheList = this.cache.hldCtrlTaxExemCache.get(key);

            // -------------
            // find 
            // -------------
            if (hldCtrlTaxExemCacheList == null || hldCtrlTaxExemCacheList.isEmpty()) {

                Map<String, String> queryParam = new HashMap<String, String>();
                queryParam.put("glblCmpyCd", glblCmpyCd);

                List<Map<String, Object>> taxExemList = (List<Map<String, Object>>) ssmClient.queryObjectList("queryHoldCtrlTaxExem", queryParam);

                if (taxExemList == null || taxExemList.isEmpty()) {
                    // -------------
                    // create tax hold
                    // -------------
                    List<Map<String, Object>> listEmptyMap = new ArrayList<Map<String, Object>>();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("none", "none");
                    listEmptyMap.add(map);
                    this.cache.hldCtrlTaxExemCache.put(key, listEmptyMap);

                    return true;

                } else {
                    boolean isTaxExemFlg = true;

                    for (Map<String, Object> taxExem : taxExemList) {

                        String ctryCd = (String) taxExem.get("CTRY_CD");
                        String stCd = (String) taxExem.get("ST_CD");

                        if (hasValue(ctryCd) && hasValue(stCd)) {

                            if (isEquals(ctryCd, paramCtryCd) && isEquals(stCd, paramStCd)) {
                                isTaxExemFlg = false;
                                break;
                            }

                        } else if (hasValue(ctryCd) && !hasValue(stCd)) {

                            if (isEquals(ctryCd, paramCtryCd) && !hasValue(paramStCd)) {
                                isTaxExemFlg = false;
                                break;
                            }

                        } else if (!hasValue(ctryCd) && hasValue(stCd)) {

                            if (!hasValue(paramCtryCd) && isEquals(stCd, paramStCd)) {
                                isTaxExemFlg = false;
                                break;
                            }
                        }
                    }

                    this.cache.hldCtrlTaxExemCache.put(key, taxExemList);
                    return isTaxExemFlg;
                }
            }
            // -------------
            // cache
            // -------------
            for (Map<String, Object> taxExemCache : hldCtrlTaxExemCacheList) {

                String isTaxExem = (String) taxExemCache.get("none");

                if (isTaxExem == null) {

                    String ctryCd = (String) taxExemCache.get("CTRY_CD");
                    String stCd = (String) taxExemCache.get("ST_CD");

                    if (hasValue(ctryCd) && hasValue(stCd)) {

                        if (isEquals(ctryCd, paramCtryCd) && isEquals(stCd, paramStCd)) {
                            return false;
                        }

                    } else if (hasValue(ctryCd) && !hasValue(stCd)) {

                        if (isEquals(ctryCd, paramCtryCd) && !hasValue(paramStCd)) {
                            return false;
                        }

                    } else if (!hasValue(ctryCd) && hasValue(stCd)) {

                        if (!hasValue(paramCtryCd) && isEquals(stCd, paramStCd)) {
                            return false;
                        }
                    }

                } else {

                    return true;
                }
            }

            return true;
        }

        private String createCacheKey(String... strs) {
            StringBuilder sb = new StringBuilder();
            for (String str : strs) {
                sb.append(str).append(",");
            }
            return sb.toString();
        }

        private boolean execDplHld(Map<String, Object> valParam) {
            final String methodNm = "execDplHld";

            boolean validFlg = true;

            valParam.put(P_DPL_FLG, N);
            valParam.put(P_EBG_FLG, N);

            MDSETMsg mdseTMsg = getMdse((String) valParam.get(P_MDSE_CD));
            if (mdseTMsg == null) {
                infoLogOut(NWZM0901W, createApiErrMsg(methodNm, valParam, NWZM0901W));
                termCd = TERM_CD.WARNING_END;
                return validFlg = false;
            }

//            if (Y.equals((String) valParam.get(P_EX_REQ_FLG))) {
//                // is Exception?
//                if (isException(valParam, mdseTMsg)) {
//                    return validFlg = false;
//                }
//            }

            // --------------------------------------------------
            // 'Drop Ship'
            // --------------------------------------------------
            if (Y.equals((String) valParam.get(P_DROP_SHIP_FLG))) {
                // In the case of DropShip, it add 'valParam' in a list to call ECS_API later.
                ecsApiParamList.add(valParam);
            }

            // 2016/09/21 QC#14646 Mod Start
            // --------------------------------------------------
            // Screening by 'BILL_TO/SELL_TO'
            // --------------------------------------------------
//            if (!screeningByBillToShipTo(valParam)) {
//                termCd = TERM_CD.WARNING_END;
//                return validFlg = false;
//            }
            // 2016/09/21 QC#14646 Mod End

            // --------------------------------------------------
            // Screening by 'SHIP_TO'
            // --------------------------------------------------
            if (!Y.equals((String) valParam.get(P_DROP_SHIP_FLG))) {
                if (!screeningByShipTo(valParam)) {
                    termCd = TERM_CD.WARNING_END;
                    return validFlg = false;
                }
            }

            return validFlg;
        }

        private CPOTMsg getCpo(String cpoOrdNum) {

            final String cacheKey = cpoOrdNum;

            CPOTMsg resTMsg = this.cache.cpo.get(cacheKey);

            if (resTMsg == null) {
                CPOTMsg reqTMsg = new CPOTMsg();
                reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
                reqTMsg.cpoOrdNum.setValue(cpoOrdNum);
                resTMsg = (CPOTMsg) findByKey(reqTMsg);
                if (resTMsg != null) {
                    this.cache.cpo.put(cacheKey, resTMsg);
                }
            }

            return resTMsg;
        }

        //QC#13446
        private String getIsAllLineCredit(String cpoOrdNum) {

            final String cacheKey = cpoOrdNum;

            String allLineCreditFlg = this.cache.allLineCreditFlg.get(cacheKey);

            if (allLineCreditFlg == null) {
                boolean bIsAllLineCredit = isAllLineCredit(glblCmpyCd, cpoOrdNum);
                if (bIsAllLineCredit) {
                    allLineCreditFlg = Y;
                    this.cache.allLineCreditFlg.put(cacheKey, Y);
                } else {
                    allLineCreditFlg = N;
                    this.cache.allLineCreditFlg.put(cacheKey, N);
                }
            }

            return allLineCreditFlg;
        }
        private CPO_DTLTMsg getCpoDtl(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

            final String cacheKey = createCacheKey(cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);

            CPO_DTLTMsg resTMsg = this.cache.cpoDtl.get(cacheKey);

            if (resTMsg == null) {
                CPO_DTLTMsg reqTMsg = new CPO_DTLTMsg();
                reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
                reqTMsg.cpoOrdNum.setValue(cpoOrdNum);
                reqTMsg.cpoDtlLineNum.setValue(cpoDtlLineNum);
                reqTMsg.cpoDtlLineSubNum.setValue(cpoDtlLineSubNum);
                resTMsg = (CPO_DTLTMsg) findByKey(reqTMsg);
                if (resTMsg != null) {
                    this.cache.cpoDtl.put(cacheKey, resTMsg);
                }
            }

            return resTMsg;
        }

        @SuppressWarnings("unchecked")
        private List<Map<String, Object>> getHldCtrlOrgList() {

            List<Map<String, Object>> hldCtrlOrgList = new ArrayList<Map<String, Object>>();

             // 2016/10/12 NA_QC#15097 Del Start
//            final Set<String> hldRsnCdSet = new HashSet<String>();
//            for (HldRsnInfo hldRsnInfo : hldRsnInfoList) {
//                hldRsnCdSet.add(hldRsnInfo.getHldRsnCd());
//            }
//            
//            if (hldRsnCdSet.isEmpty()) {
//                return hldCtrlOrgList;
//            }
            // 2016/10/12 NA_QC#15097 Del End

            // HLD_CTRL_ORG
            final Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd",  glblCmpyCd);
            ssmParam.put("validationBatchMode", validationBatchMode.toString());

            hldCtrlOrgList = ssmClient.queryObjectList("getHldCtrlOrgList", ssmParam);
            return hldCtrlOrgList;
        }

        private SHPG_PLNTMsg getShpgPln(String shpgPlnNum) {

            final String cacheKey = shpgPlnNum;

            SHPG_PLNTMsg resTMsg = this.cache.shpgPln.get(cacheKey);

            if (resTMsg == null) {
                SHPG_PLNTMsg reqTMsg = new SHPG_PLNTMsg();
                reqTMsg.glblCmpyCd.setValue(glblCmpyCd);
                reqTMsg.shpgPlnNum.setValue(shpgPlnNum);
                resTMsg = (SHPG_PLNTMsg) findByKey(reqTMsg);
                if (resTMsg != null) {
                    this.cache.shpgPln.put(cacheKey, resTMsg);
                }
            }

            return resTMsg;
        }

        private S21_ORGTMsg getTocCd(final String glblCmpyCd, final String tocCd) {

            S21_ORGTMsg orgMsg = new S21_ORGTMsg();
            orgMsg.glblCmpyCd.setValue(glblCmpyCd);
            orgMsg.tocCd.setValue(tocCd);

            return (S21_ORGTMsg) S21CacheTBLAccessor.findByKey(orgMsg);

        }

        private String getWhType(String mdseCd, String poReqFlg) {

            String whType = null;

            if (Y.equals(poReqFlg)) {

                whType = WH_TP.VENDOR;

            } else {

                MDSETMsg mdseMsg = getMdse(mdseCd);

                if (mdseMsg != null) {
                    String invtyCtrlFlg = mdseMsg.invtyCtrlFlg.getValue();
                    if (Y.equals(invtyCtrlFlg)) {
                        whType = WH_TP.COMMON;
                    } else {
                        whType = WH_TP.NON_W_OR_H;
                    }
                }
            }

            return whType;
        }

        private boolean isAlreadyHld(Map<String, Object> valParam) {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd",       glblCmpyCd);
            ssmParam.put("cpoOrdNum",        valParam.get(P_CPO_ORD_NUM_I));
            ssmParam.put("cpoDtlLineNum",    valParam.get(P_CPO_DTL_LINE_NUM_I));
            ssmParam.put("cpoDtlLineSubNum", valParam.get(P_CPO_DTL_LINE_SUB_NUM_I));
            ssmParam.put("hldRsnCd",         valParam.get(P_RSN_CD));

            return (Integer) ssmClient.queryObject("getHldCnt", ssmParam) > 0;
        }

        private boolean isDPL(String hldRsnCd) {
            return DPL_SCREENING.equals(hldRsnCd) || EMBARGO.equals(hldRsnCd);
        }

//        private boolean isException(Map<String, Object> screeningData, MDSETMsg mdseTMsg) {
//
//            String shipToCustCd = (String) screeningData.get(P_SHIP_TO_CUST_CD);
//            String billToCustCd = (String) screeningData.get(P_BILL_TO_CUST_CD);
//            String sellToCustCd = (String) screeningData.get(P_SELL_TO_CUST_CD);
//            String rsnCd        = (String) screeningData.get(P_RSN_CD);
//
//            return NWXC001001ContractInfo.chkContractInfo(glblCmpyCd, mdseTMsg.mdseCd.getValue(), shipToCustCd, sellToCustCd, billToCustCd, rsnCd, null, slsDt);
//        }

// 2016/09/21 QC#14646 Mod Start
//        /**
//         * Check Bill To ,Sell Toby by Master
//         */
//        @SuppressWarnings("unchecked")
//        private boolean screeningByBillToShipTo(Map<String, Object> valParam) {
//            final String methodNm = "screeningByBillToShipTo";
//
//            String billToCustCd = (String) valParam.get(P_BILL_TO_CUST_CD);
//            String sellToCustCd = (String) valParam.get(P_SELL_TO_CUST_CD);
//
//            final String cacheKey = createCacheKey(billToCustCd, sellToCustCd);
//
//            List<Map<String, String>> screeningDataList = this.cache.billToSellToScreeningData.get(cacheKey);
//
//            if (screeningDataList == null) {
//
//                final Map<String, Object> ssmParam = new HashMap<String, Object>();
//                ssmParam.put("glblCmpyCd", glblCmpyCd);
//                // BILL_TO
//                List<String> billCdList = new ArrayList<String>();
//                billCdList.add(billToCustCd);
//                ssmParam.put("billCdList", billCdList);
//                // SELL_TO
//                List<String> sellCdList = new ArrayList<String>();
//                sellCdList.add(sellToCustCd);
//                ssmParam.put("sellCdList", sellCdList);
//
//                screeningDataList = (List<Map<String, String>>) ssmClient.queryObjectList("getBillToSellToScreeningData", ssmParam);
//                if (!isEmpty(screeningDataList)) {
//                    this.cache.billToSellToScreeningData.put(cacheKey, screeningDataList);
//                }
//            }
//
//            if (isEmpty(screeningDataList)) {
//                infoLogOut(NWZM0902W, createApiErrMsg(methodNm, valParam, NWZM0902W));
//                return false;
//            }
//
//            // --------------------------------------------------
//            // Screening
//            // --------------------------------------------------
//            final String hldRsnCd = (String) valParam.get(P_RSN_CD);
//            for (Map<String, String> screeningData : screeningDataList) {
//                // DPL
//                if (DPL_SCREENING.equals(hldRsnCd)) {
//                    setBillToDplFlg(valParam, (String) screeningData.get("BT_DPL_STS_CD"), (String) screeningData.get("BT_DPL_RSN_TXT"));
//                    if (screeningData.get("SE_DPL_STS_CD") != null) {
//                        setSellToDplFlg(valParam, (String) screeningData.get("SE_DPL_STS_CD"), (String) screeningData.get("SE_DPL_RSN_TXT"));
//                    } else {
//                        infoLogOut(NWZM0903W, createApiErrMsg(methodNm, valParam, NWZM0903W));
//                        return false;
//                    }
//                // Embargo
//                } else if (EMBARGO.equals(hldRsnCd)) {
//                    setEmbargoFlg(valParam, (String) screeningData.get("BT_EMBGO_FLG"));
//                    if (screeningData.get("SE_DPL_STS_CD") != null) {
//                        setEmbargoFlg(valParam, (String) screeningData.get("SE_EMBGO_FLG"));
//                    } else {
//                        infoLogOut(NWZM0903W, createApiErrMsg(methodNm, valParam, NWZM0903W));
//                        return false;
//                    }
//                } else {
//                    // nothing to do.
//                }
//            }
//
//            return true;
//        }
// 2016/09/21 QC#14646 Mod End

        @SuppressWarnings("unchecked")
        private boolean screeningByShipTo(Map<String, Object> valParam) {
            final String methodNm = "screeningByShipTo";

            String shipToCustCd = (String) valParam.get(P_SHIP_TO_CUST_CD);

            final String cacheKey = createCacheKey(shipToCustCd);

            List<Map<String, String>> screeningDataList = this.cache.shipToScreeningData.get(cacheKey);

            if (screeningDataList == null) {

                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                // SHIP_TO
                List<String> shipToCdList = new ArrayList<String>();
                shipToCdList.add(shipToCustCd);
                ssmParam.put("shipToCdList", shipToCdList);

                screeningDataList = (List<Map<String, String>>) ssmClient.queryObjectList("getShipToScreeningData", ssmParam);
                if (!isEmpty(screeningDataList)) {
                    this.cache.shipToScreeningData.put(cacheKey, screeningDataList);
                }
            }

            if (isEmpty(screeningDataList)) {
                infoLogOut(NWZM0904W, createApiErrMsg(methodNm, valParam, NWZM0904W));
                return false;
            }

            // --------------------------------------------------
            // Screening
            // --------------------------------------------------
            final String hldRsnCd = (String) valParam.get(P_RSN_CD);
            for (Map<String, String> screeningData : screeningDataList) {
                // DPL
                if (DPL_SCREENING.equals(hldRsnCd)) {
                    setShipToDplFlg(valParam, (String) screeningData.get("DPL_STS_CD"), (String) screeningData.get("DPL_RSN_TXT"));
                // Embargo
                } else if (EMBARGO.equals(hldRsnCd)) {
                    setEmbargoFlg(valParam, (String) screeningData.get("EMBGO_FLG"));
                } else {
                    // nothing to do.
                }
            }

            return true;
        }

        private NWZC044001PMsg validateDPL(String hldRsnCd, Map<String, Object> valParam) {

            if (!isDPL(hldRsnCd)) {
                return null;
            }

            final String cpoOrdNum        = (String) valParam.get(P_CPO_ORD_NUM_I);
            final String cpoDtlLineNum    = (String) valParam.get(P_CPO_DTL_LINE_NUM_I);
            final String cpoDtlLineSubNum = (String) valParam.get(P_CPO_DTL_LINE_SUB_NUM_I);
            final String setMdseCd        = (String) valParam.get(P_SET_MDSE_CD);

            // not set component.
            if (SET_PARENT_LINE_SUB_NUM.equals(cpoDtlLineSubNum) || !hasValue(setMdseCd)) {

                valParam.put(P_RSN_CD, hldRsnCd);

                // is already held by 'DPL/Embargo'?
                if (!isAlreadyHld(valParam)) {
                    // execute Hold by 'DPL/Embargo'
                    if (execDplHld(valParam)) {

                        boolean isHld = false;

                        // DPL
                        if (DPL_SCREENING.equals(hldRsnCd)) {
                            if (!Y.equals(valParam.get(P_DROP_SHIP_FLG))) {
                                if (Y.equals(valParam.get(P_DPL_FLG))) {
                                    isHld = true;
                                }
                            }
                        // Embargo
                        } else if (EMBARGO.equals(hldRsnCd)) {
                            if (Y.equals(valParam.get(P_EBG_FLG))) {
                                isHld = true;
                            }
                        }

                        if (isHld) {
                            final NWZC044001PMsg dplHldPMsg = new NWZC044001PMsg();
                            setValue(dplHldPMsg.glblCmpyCd,       glblCmpyCd);
                            setValue(dplHldPMsg.cpoOrdNum,        cpoOrdNum);
                            setValue(dplHldPMsg.cpoDtlLineNum,    cpoDtlLineNum);
                            setValue(dplHldPMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
                            setValue(dplHldPMsg.hldRsnCd,         hldRsnCd);
                            setValue(dplHldPMsg.hldDtlTxt,        (String) valParam.get(P_DPL_RSN_TXT));
                            setValue(dplHldPMsg.slsDt,            slsDt);
                            return dplHldPMsg;
                        }
                    }
                }
            }

            return null;
        }
    }

    private static enum ValidationBatchMode {

        DAYTIME("1"),

        NIGHT("2"),

        BEFORE_REVENUE_RECOGNITION("3");

        private String mode;

        private ValidationBatchMode(String mode) {
            this.mode = mode;
        }

        public String toString() {
            return this.mode;
        }
    }

    //QC#13446
    private boolean isAllLineCredit(String glblCmpyCd, String cpoOrdNum) {

        if (!hasValue(glblCmpyCd)) {
            return false;
        }
        if (!hasValue(cpoOrdNum)) {
            return false;
        }
        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("ordLineStsCancel", ORD_LINE_STS.CANCELLED);
        ssmParam.put("crRebilCd", CR_REBIL.CREDIT);

        BigDecimal cnt = (BigDecimal) ssmClient.queryObject("cntNotAllLineCredit", ssmParam);

        return (BigDecimal.ZERO.compareTo(cnt) >= 0);
    }
}