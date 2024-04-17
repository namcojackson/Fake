/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB023001;

import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.BATCH_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.CUR_LOC_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.DEST_INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.DSP_TOT_PRC_AMT;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.DSP_UNIT_PRC_AMT;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.EMAIL_PARAM_BATCH_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.EMAIL_PARAM_DATE;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.EMAIL_PARAM_MSG;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.INBD_OTBD_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.INBD_TRX_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.INBD_TRX_RSN_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.INVTY_AVAL_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.INVTY_ORD_TP_FOR_SWH_CHNG;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.INVTY_STK_STS_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.LINE_FEED_CODE;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.LOC_STS_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.MACH_MSTR_LIST;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.MAIL_FROM_GROUP_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.MAIL_TO_GROUP_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.MDL_GRP_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.MDSE_COST_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.MDSE_INVTY_COST_PCT;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.MDSE_INVTY_COST_PCT_ZERO;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.ML_FMT;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.NLBM1065E;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.NLBM1110E;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.OTBD_TRX_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.OTBD_TRX_RSN_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.RTL_WH_CATG_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.RTL_WH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.RWS_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.RWS_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.RWS_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.RWS_REF_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.SALES_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.SCE_ORD_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.SCE_ORD_TP_FOR_SWH_CHNG;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.SHPG_PLN_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.SO_MDSE_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.SO_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.SO_SLP_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.STK_STS_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.SVC_CONFIG_MSTR_PK;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.SVC_MACH_MAINT_AVAL_FLG;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.SVC_MACH_MSTR_LOC_STS_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.SVC_MACH_MSTR_PK;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.SVC_MACH_MSTR_STS_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.SVC_MACH_MSTR_STS_CD_C;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.SVC_MACH_MSTR_STS_CD_R;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.TIME_FORMAT_MM_DD_YYYY_HH_MM;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.TOT_PRC_AMT;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.TRX_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.TRX_HDR_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.TRX_LINE_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.TRX_RSN_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.UNIT_PRC_AMT;
import static com.canon.cusa.s21.batch.NLC.NLCB023001.constant.NLCB023001Constant.ZZM9000E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NLZC003001PMsg;
import business.parts.NLZC200001PMsg;
import business.parts.NLZC205001PMsg;
import business.parts.NLZC206001PMsg;
import business.parts.NLZC207001PMsg;
import business.parts.NLZC215001PMsg;
import business.parts.NLZC302001PMsg;
import business.parts.NLZC304001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC003001PMsg;
import business.parts.NWZC107001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC003001.NLZC003001;
import com.canon.cusa.s21.api.NLZ.NLZC200001.NLZC200001;
import com.canon.cusa.s21.api.NLZ.NLZC205001.NLZC205001;
import com.canon.cusa.s21.api.NLZ.NLZC206001.NLZC206001;
import com.canon.cusa.s21.api.NLZ.NLZC207001.NLZC207001;
import com.canon.cusa.s21.api.NLZ.NLZC215001.NLZC215001;
import com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC302001.NLZC302001;
import com.canon.cusa.s21.api.NLZ.NLZC304001.NLZC304001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PUT_AWAY_CHK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
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
 *<pre>
 * NLCB0230:Showroom re evaluation Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/10/2016   CITS            T.Kikuhara      Create          N/A
 * 12/27/2016   CITS            Y.Fujii         Update          QC#16765
 * 06/22/2017   CITS            K.Ogino         Update          QC#19475
 * 01/10/2018   CITS            K.Ogino         Update          QC#23337
 * 02/01/2018   CITS            T.Gotoda        Update          QC#23868
 * 02/22/2018   CITS            T.Gotoda        Update          QC#24066
 * 04/24/2018   CITS            S.Katsuma       Update          QC#23355
 * 05/16/2018   CITS            T.Hakodate      Update          QC#25814
 *</pre>
 */
public class NLCB023001 extends S21BatchMain {

    /** S21SsmBatchClient */
    private S21SsmBatchClient glSsmBatchClient = null;

    /** Global Company Code */
    private String globalCompanyCode = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Sales Date */
    private String salesDate = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** total data count */
    private int totalCount = 0;

    /** registration data count */
    private int registCount = 0;

    /** error data count */
    private int errorCount = 0;

    /** error message list */
    private List<String> errMsgList = new ArrayList<String>();

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    /** VarCharConstValue INVTY_ORD_TP_FOR_SWH_CHNG */
    private String glInvOrdTpForSwhChng;

    /** VarCharConstValue SCE_ORD_TP_FOR_SWH_CHNG */
    private String glSceOrdTpForSwhChng;

    /** Serial List Max Size for NLZC003001PMsg */
    private final int serialMaxSize = new NLZC003001PMsg().serialInfoList.length();
    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {
        // Initialization S21BatchMain
        new NLCB023001().executeBatch(NLCB023001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        glSsmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        profileService = S21UserProfileServiceFactory.getInstance().getService();

        globalCompanyCode = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(globalCompanyCode)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Global Company Code" });
        }

        salesDate = ZYPDateUtil.getSalesDate(globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Sales Date" });
        }

        glInvOrdTpForSwhChng = ZYPCodeDataUtil.getVarCharConstValue(INVTY_ORD_TP_FOR_SWH_CHNG, globalCompanyCode);
        glSceOrdTpForSwhChng = ZYPCodeDataUtil.getVarCharConstValue(SCE_ORD_TP_FOR_SWH_CHNG, globalCompanyCode);

    }

    @Override
    protected void mainRoutine() {

        // Get TRX_CD and TRX_RSN_CD by SCE Order Type
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(GLBL_CMPY_CD, globalCompanyCode);
        param.put(SCE_ORD_TP_CD, glSceOrdTpForSwhChng);
        List<Map<String, Object>> sceOrdTpList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getSceOrdTp", param);
        String inbdTrxCd = "";
        String inbdTrxRsnCd = "";
        String otbdTrxCd = "";
        String otbdTrxRsnCd = "";
        if (sceOrdTpList != null && sceOrdTpList.size() > 0) {
            for (Map<String, Object> sceOrdTpMap : sceOrdTpList) {
                if (INBD_OTBD.INBOUND.equals((String) sceOrdTpMap.get(INBD_OTBD_CD))) {
                   inbdTrxCd = (String) sceOrdTpMap.get(TRX_CD);
                   inbdTrxRsnCd = (String) sceOrdTpMap.get(TRX_RSN_CD);
                }
                if (INBD_OTBD.OUTBOUND.equals((String) sceOrdTpMap.get(INBD_OTBD_CD))) {
                   otbdTrxCd = (String) sceOrdTpMap.get(TRX_CD);
                   otbdTrxRsnCd = (String) sceOrdTpMap.get(TRX_RSN_CD);
                }
            }
        } else {
            String xxMsgTxt = S21MessageFunc.clspGetMessage(NLBM1110E);
            errMsgList.add(xxMsgTxt);
            return;
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean skipFlg = false;
        boolean needReval = false;
        
        try {
            // Get Inventory Info for Show room (Main data)
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(RTL_WH_CATG_CD, RTL_WH_CATG.SHOWROOM);
            paramMap.put(SALES_DT, salesDate);
            paramMap.put(LOC_STS_CD, LOC_STS.DC_STOCK);
            // START 2018/04/24 S.Katsuma [QC#23355,ADD]
            paramMap.put(MDSE_INVTY_COST_PCT_ZERO, BigDecimal.ZERO);
            // END 2018/04/24 S.Katsuma [QC#23355,ADD]
            // START 2018/05/16 T.Hakodate[QC#25814,ADD]
            paramMap.put(SVC_MACH_MAINT_AVAL_FLG, ZYPConstant.FLG_ON_Y);
            paramMap.put(SVC_MACH_MSTR_STS_CD_C, SVC_MACH_MSTR_STS.CREATED);
            paramMap.put(SVC_MACH_MSTR_STS_CD_R, SVC_MACH_MSTR_STS.REMOVED);
            // END   2018/05/16 T.Hakodate [QC#25814,ADD]
            

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setMaxRows(0);
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            preparedStatement = ssmLlcClient.createPreparedStatement("getInventoryInfo", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            // START 2018/05/16 T.Hakodate[QC#25814,ADD]
            BigDecimal oldSvcConfigMstrPk = BigDecimal.valueOf(-1);
            BigDecimal revaluatedConfigPk = BigDecimal.valueOf(-1);
            Map<String, String> revaluatedRecord = new HashMap<String, String>();

            String cacheKey = "";
            String destRtlSwhCd = "";
            String destInvtyLocCd = "";
            // END 2018/05/16 T.Hakodate[QC#25814,ADD]

            List<Map<String, Object>> swhTransferList = new ArrayList<Map<String, Object>>();
            String oldRtlWhCd = "";
            while (resultSet.next()) {
                // Set ResultSet To Map
                ResultSetMetaData rsm = resultSet.getMetaData();
                Map<String, Object> inventoryInfo = new HashMap<String, Object>();
                for (int i = 1; i < rsm.getColumnCount() + 1; i++) {
                    String colName = rsm.getColumnName(i);
                    Object colVal = resultSet.getObject(colName);
                    inventoryInfo.put(colName, colVal);
                }

                String rtlWhCd = (String) inventoryInfo.get(RTL_WH_CD);
                String rtlSwhCd = (String) inventoryInfo.get(RTL_SWH_CD);
                String mdseCd = (String) inventoryInfo.get(MDSE_CD);
                // START 2018/05/16 T.Hakodate[QC#25814,MOD]
                BigDecimal curSvcConfigMstrPk = (BigDecimal) inventoryInfo.get(SVC_CONFIG_MSTR_PK);
     
                // Execute SWH Transfer Process When Different
                // RTL_WH_CD or next Config
                // if (!oldRtlWhCd.equals(rtlWhCd)) {
                if (!oldRtlWhCd.equals(rtlWhCd) || oldSvcConfigMstrPk.compareTo(curSvcConfigMstrPk) != 0) {
                    // END 2018/05/16 T.Hakodate [QC#25814,MOD]
                    if (totalCount > 0) {
                        // QC#24066 MOD START
                        // Execute SWH Transfer Process
                        if (!swhTransferProc(swhTransferList)) {
                            errorCount = errorCount + swhTransferList.size();
                            rollback();
                        } else {
                            registCount = registCount + swhTransferList.size();
                             commit();
//                            rollback();
                        }
                        // QC#24066 MOD END
                    }
                    // In this case. new RTL_WH_CD Group List
                    oldRtlWhCd = rtlWhCd;
                    swhTransferList = new ArrayList<Map<String, Object>>();
                    // START 2018/05/16 T.Hakodate[QC#25814,ADD]
                    oldSvcConfigMstrPk = curSvcConfigMstrPk;
                    // END 2018/05/16 T.Hakodate[QC#25814,ADD]
                }

                totalCount++;

                // START 2018/05/16 T.Hakodate[QC#25814,ADD]
                // not exists config.
                if (curSvcConfigMstrPk.compareTo(BigDecimal.valueOf(-1)) == 0) {
                    cacheKey = rtlWhCd + rtlSwhCd + mdseCd;
                    if (revaluatedRecord.containsKey(cacheKey)) {
                        continue;
                    }
                    revaluatedRecord.put(cacheKey, "not IB");
                }

                // END 2018/05/16 T.Hakodate[QC#25814,ADD]
                if ((curSvcConfigMstrPk.compareTo(BigDecimal.valueOf(-1)) == 0) || curSvcConfigMstrPk.compareTo(revaluatedConfigPk) != 0) {

                    // Get Default Sub Warehouse
                    NLZC215001PMsg pMsg = new NLZC215001PMsg();
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
                    ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NLZC215001Constant.MODE_VAL_SHRM);
                    ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, mdseCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.srcRtlWhCd, rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.destRtlWhCd, rtlWhCd);
                    // START 2018/05/16 T.Hakodate[QC#25814,ADD]
                    BigDecimal mdlGrpId = (BigDecimal) inventoryInfo.get(MDL_GRP_ID);
                    if (ZYPCommonFunc.hasValue(mdlGrpId)) {
                        ZYPEZDItemValueSetter.setValue(pMsg.mdlGrpId, mdlGrpId);
                    }
                    // END 2018/05/16 T.Hakodate[QC#25814,ADD]
                    NLZC215001 nlzc215001 = new NLZC215001();
                    nlzc215001.execute(pMsg, ONBATCH_TYPE.BATCH);
                    if (pMsg.xxMsgIdList.getValidCount() != 0) {
                        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                            String xxMsgTxt = S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                            errMsgList.add(String.format(ML_FMT, xxMsgTxt, rtlWhCd, rtlSwhCd, mdseCd));
                        }
                        skipFlg = true;
                        continue;
                    }

                    destRtlSwhCd = pMsg.destRtlSwhCd.getValue();
                    destInvtyLocCd = rtlWhCd + destRtlSwhCd;
                    revaluatedConfigPk = curSvcConfigMstrPk;

                    // Check SWH Transfer Need
                    if (ZYPCommonFunc.hasValue(rtlSwhCd) && ZYPCommonFunc.hasValue(destRtlSwhCd) && rtlSwhCd.equals(destRtlSwhCd)) {

                        needReval = false;
                        // If same SWH_CD Not Need SWH Transfer
                        continue;
                        
                    } else {
                        
                        needReval = true;
                    }
                }
                
                if (needReval) {

                    // Get Inventory Item Cost
                    NLXC001001GetInventoryItemCostBean bean = new NLXC001001GetInventoryItemCostBean();
                    bean.setGlblCmpyCd(globalCompanyCode);
                    bean.setInvtyLocCd((String) inventoryInfo.get(INVTY_LOC_CD));
                    bean.setMdseCd(mdseCd);
                    bean.setQty((BigDecimal) inventoryInfo.get(INVTY_AVAL_QTY));
                    NLXC001001GetInventoryItemCostBean retBean = NLXC001001GetInventoryItemCost.getInventoryItemCost(bean);
                    if (!retBean.getErrList().isEmpty()) {
                        for (String xxMsgId : retBean.getErrList()) {
                            String xxMsgTxt = S21MessageFunc.clspGetMessage(xxMsgId);
                            errMsgList.add(String.format(ML_FMT, xxMsgTxt, rtlWhCd, rtlSwhCd, mdseCd));
                        }
                        skipFlg = true;
                        continue;
                    }

                    // START 2018/05/16 T.Hakodate[QC#25814,ADD]
                    // exists Config
                    List<Map<String, Object>> machineMasterList = new ArrayList<Map<String, Object>>();
                    if (ZYPCommonFunc.hasValue(curSvcConfigMstrPk) && curSvcConfigMstrPk.compareTo(BigDecimal.ZERO) > 0) {

                        Map<String, Object> machineMasterMap = new HashMap<String, Object>();
                        machineMasterMap.put(SVC_MACH_MSTR_PK, (BigDecimal) inventoryInfo.get(SVC_MACH_MSTR_PK));
                        machineMasterMap.put(SER_NUM, (String) inventoryInfo.get(SER_NUM));
                        machineMasterMap.put(SVC_CONFIG_MSTR_PK, (BigDecimal) inventoryInfo.get(SVC_CONFIG_MSTR_PK));
                        machineMasterMap.put(STK_STS_CD, (String) inventoryInfo.get(STK_STS_CD));
                        machineMasterMap.put(SVC_MACH_MSTR_STS_CD, (String) inventoryInfo.get(SVC_MACH_MSTR_STS_CD));

                        machineMasterList.add(machineMasterMap);

                    } else {

                        // Get Serial Info
                        param = new HashMap<String, Object>();
                        param.put(GLBL_CMPY_CD, globalCompanyCode);
                        param.put(CUR_LOC_NUM, (String) inventoryInfo.get(INVTY_LOC_CD));
                        param.put(MDSE_CD, mdseCd);
                        param.put(SVC_MACH_MSTR_LOC_STS_CD, (String) inventoryInfo.get(LOC_STS_CD));
                        param.put(SVC_MACH_MAINT_AVAL_FLG, ZYPConstant.FLG_ON_Y);
                        List<String> svcMachMstrStsCd = new ArrayList<String>();
                        svcMachMstrStsCd.add(SVC_MACH_MSTR_STS.CREATED);
                        svcMachMstrStsCd.add(SVC_MACH_MSTR_STS.REMOVED);
                        param.put(SVC_MACH_MSTR_STS_CD, svcMachMstrStsCd);
                        machineMasterList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getMachineMasterInfo", param);
                    }

                    // QC#23868, QC#24066 MOD START
                    int idx = 0;
                    List<Map<String, Object>> serialList = new ArrayList<Map<String, Object>>();
                    while (true) {

                        if (!machineMasterList.isEmpty()) {

                            Map<String, Object> machineMasterMap = machineMasterList.get(idx);
                            serialList.add(machineMasterMap);

                            BigDecimal svcConfigMstrPk = (BigDecimal) machineMasterMap.get(SVC_CONFIG_MSTR_PK);
                            if (!ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
                                // Non Config
                                if (idx + 1 < machineMasterList.size() && serialList.size() < serialMaxSize) {
                                    idx++;
                                    continue;
                                }
                            }
                        }

                        // Set SWH Transfer List grouping by RTL_WH_CD
                        Map<String, Object> swhTransferMap = new HashMap<String, Object>();
                        swhTransferMap.put(INBD_TRX_CD, inbdTrxCd);
                        swhTransferMap.put(INBD_TRX_RSN_CD, inbdTrxRsnCd);
                        swhTransferMap.put(OTBD_TRX_CD, otbdTrxCd);
                        swhTransferMap.put(OTBD_TRX_RSN_CD, otbdTrxRsnCd);
                        swhTransferMap.put(RTL_WH_CD, rtlWhCd);
                        swhTransferMap.put(RTL_SWH_CD, rtlSwhCd);
                        swhTransferMap.put(INVTY_LOC_CD, (String) inventoryInfo.get(INVTY_LOC_CD));
                        swhTransferMap.put(LOC_STS_CD, (String) inventoryInfo.get(LOC_STS_CD));
                        swhTransferMap.put(MDSE_CD, mdseCd);
                        swhTransferMap.put(STK_STS_CD, (String) inventoryInfo.get(STK_STS_CD));

                        if (serialList.size() > 0) {
                            swhTransferMap.put(INVTY_AVAL_QTY, BigDecimal.valueOf(serialList.size()));
                        } else {
                            swhTransferMap.put(INVTY_AVAL_QTY, (BigDecimal) inventoryInfo.get(INVTY_AVAL_QTY));
                        }

                        swhTransferMap.put(DEST_INVTY_LOC_CD, destInvtyLocCd);
                        swhTransferMap.put(UNIT_PRC_AMT, retBean.getUnitPrcAmt());
                        swhTransferMap.put(TOT_PRC_AMT, retBean.getTotPrcAmt());
                        swhTransferMap.put(DSP_UNIT_PRC_AMT, retBean.getDspUnitPrcAmt());
                        swhTransferMap.put(DSP_TOT_PRC_AMT, retBean.getDspTotPrcAmt());
                        swhTransferMap.put(MDSE_COST_TP_CD, retBean.getMdseCostTpCd());
                        swhTransferMap.put(MDSE_INVTY_COST_PCT, retBean.getMdseInvtyCostPct());
                        swhTransferMap.put(MACH_MSTR_LIST, serialList);

                        swhTransferList.add(swhTransferMap);
                        idx++;
                        if (idx >= machineMasterList.size()) {
                            break;
                        } else {
                            serialList = new ArrayList<Map<String, Object>>();
                        }
                    }
                    // QC#23868, QC#24066 MOD END
                }
            } // End resultSet Loop

            // Execute SWH Transfer Process When Last Recored
            if (!skipFlg && !swhTransferList.isEmpty()) {
                // QC#24066 MOD START
                if (!swhTransferProc(swhTransferList)) {
                    errorCount = errorCount + swhTransferList.size();
                    rollback();
                } else {
                    registCount = registCount + swhTransferList.size();
                    commit();
//                    rollback();
                }
                // QC#24066 MOD END
            }

        } catch (SQLException e) {
            rollback();
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return;
    }

    @Override
    protected void termRoutine() {

        if (errMsgList.size() > 0) {
            postErrorMail();
        }

        // Set EndCode and registration data Count
        if (errorCount > 0) {
            termCd = TERM_CD.WARNING_END;
        }

        setTermState(termCd, registCount, errorCount, totalCount);
    }

    /**
     * <pre>
     * SWH Transfer Process
     * </pre>
     * @param List<Map<String, Object>> swhTransferList
     */
    private boolean swhTransferProc(List<Map<String, Object>> swhTransferList) {

        int loopCnt = 0;
        String invtyOrdNum = "";
        NLZC003001 inventoryOrderAPI = new NLZC003001();
        for (Map<String, Object> swhTransferMap : swhTransferList) {

            String rtlWhCd = (String) swhTransferMap.get(RTL_WH_CD);
            String rtlSwhCd = (String) swhTransferMap.get(RTL_SWH_CD);
            String mdseCd = (String) swhTransferMap.get(MDSE_CD);

            // Execute Inventory Order API Header
            if (loopCnt == 0) {
                NLZC003001PMsg invOrdHeadPmsg = execInventoryOrderAPIHeader(swhTransferMap, inventoryOrderAPI);
                if (S21ApiUtil.isXxMsgId(invOrdHeadPmsg)) {
                    for (int i = 0; i < invOrdHeadPmsg.xxMsgIdList.getValidCount(); i++) {
                        String xxMsgTxt = S21MessageFunc.clspGetMessage(invOrdHeadPmsg.xxMsgIdList.no(i).xxMsgId.getValue());
                        errMsgList.add(String.format(ML_FMT, xxMsgTxt, rtlWhCd, rtlSwhCd, mdseCd));
                    }
                    return false;
                }
                // Set Inventory Order Number
                invtyOrdNum = invOrdHeadPmsg.invtyOrdNum.getValue();
            }

            loopCnt++;

            // Execute Inventory Order API Detail
            String wkLineNum = String.format("%03d", loopCnt);
            NLZC003001PMsg invOrdDtlPmsg = execInventoryOrderAPIDetail(swhTransferMap, invtyOrdNum, wkLineNum, inventoryOrderAPI);
            if (S21ApiUtil.isXxMsgId(invOrdDtlPmsg)) {
                for (int i = 0; i < invOrdDtlPmsg.xxMsgIdList.getValidCount(); i++) {
                    String xxMsgTxt = S21MessageFunc.clspGetMessage(invOrdDtlPmsg.xxMsgIdList.no(i).xxMsgId.getValue());
                    errMsgList.add(String.format(ML_FMT, xxMsgTxt, rtlWhCd, rtlSwhCd, mdseCd));
                }
                return false;
            }

            // Execute Allocation for Non CPO API
            NWZC107001PMsg allocForNonCPOPmsg = execAllocationForNonCPOAPI(swhTransferMap, invtyOrdNum, wkLineNum);
            if (S21ApiUtil.isXxMsgId(allocForNonCPOPmsg)) {
                for (int i = 0; i < allocForNonCPOPmsg.xxMsgIdList.getValidCount(); i++) {
                    String xxMsgTxt = S21MessageFunc.clspGetMessage(allocForNonCPOPmsg.xxMsgIdList.no(i).xxMsgId.getValue());
                    errMsgList.add(String.format(ML_FMT, xxMsgTxt, rtlWhCd, rtlSwhCd, mdseCd));
                }
                return false;
            }

            // Execute Shipping Plan Update API
            List<NWZC003001PMsg> shipPlnPmsgList = execShpgPlnUpdAPI(swhTransferMap, invtyOrdNum, wkLineNum);
            for (NWZC003001PMsg shipPlnPmsg : shipPlnPmsgList) {
                if (S21ApiUtil.isXxMsgId(shipPlnPmsg)) {
                    for (int i = 0; i < shipPlnPmsg.xxMsgIdList.getValidCount(); i++) {
                        String xxMsgTxt = S21MessageFunc.clspGetMessage(shipPlnPmsg.xxMsgIdList.no(i).xxMsgId.getValue());
                        errMsgList.add(String.format(ML_FMT, xxMsgTxt, rtlWhCd, rtlSwhCd, mdseCd));
                    }
                    return false;
                }
            }

            // Execute SO API
            List<NLZC205001PMsg> soPmsgList = execSoAPI(swhTransferMap, invtyOrdNum, wkLineNum);
            for (NLZC205001PMsg soPmsg : soPmsgList) {
                if (S21ApiUtil.isXxMsgId(soPmsg)) {
                    for (int i = 0; i < soPmsg.xxMsgIdList.getValidCount(); i++) {
                        String xxMsgTxt = S21MessageFunc.clspGetMessage(soPmsg.xxMsgIdList.no(i).xxMsgId.getValue());
                        errMsgList.add(String.format(ML_FMT, xxMsgTxt, rtlWhCd, rtlSwhCd, mdseCd));
                    }
                    return false;
                }
            }

            // Execute RWS API
            List<NLZC200001PMsg> rwsPmsgList = execRwsAPI(soPmsgList);
            for (NLZC200001PMsg rwsPmsg : rwsPmsgList) {
                if (S21ApiUtil.isXxMsgId(rwsPmsg)) {
                    for (int i = 0; i < rwsPmsg.xxMsgIdList.getValidCount(); i++) {
                        String xxMsgTxt = S21MessageFunc.clspGetMessage(rwsPmsg.xxMsgIdList.no(i).xxMsgId.getValue());
                        errMsgList.add(String.format(ML_FMT, xxMsgTxt, rtlWhCd, rtlSwhCd, mdseCd));
                    }
                    return false;
                }
            }

            // Get All RWS_NUM
            List<String> rwsNumList = new ArrayList <String>();
            for (NLZC200001PMsg rwsPmsg : rwsPmsgList) {
                for (int i = 0; i < rwsPmsg.RWSNumList.getValidCount(); i++) {
                    rwsNumList.add(rwsPmsg.RWSNumList.no(i).rwsNum.getValue());
                }
            }

            // Get RWS Serial Info
            Map<String, Object> param = new HashMap<String, Object>();
            param.put(GLBL_CMPY_CD, globalCompanyCode);
            param.put(RWS_NUM, rwsNumList);
            List<Map<String, Object>> rwsSerList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getRwsSerialInfo", param);

            // Execute RWS Serial API
            List<NLZC304001PMsg> rwsSerialPmsgList = execRwsSerialAPI(rwsSerList);
            for (NLZC304001PMsg rwsSerialPmsg : rwsSerialPmsgList) {
                if (S21ApiUtil.isXxMsgId(rwsSerialPmsg)) {
                    for (int i = 0; i < rwsSerialPmsg.xxMsgIdList.getValidCount(); i++) {
                        String xxMsgTxt = S21MessageFunc.clspGetMessage(rwsSerialPmsg.xxMsgIdList.no(i).xxMsgId.getValue());
                        errMsgList.add(String.format(ML_FMT, xxMsgTxt, rtlWhCd, rtlSwhCd, mdseCd));
                    }
                    return false;
                }
            }

            // Execute Put Away Confirmation API
            NLZC206001PMsg putAwayPmsg = execPutAwayConfAPI(rwsSerList);
            if (S21ApiUtil.isXxMsgId(putAwayPmsg)) {
                for (int i = 0; i < putAwayPmsg.xxMsgIdList.getValidCount(); i++) {
                    String xxMsgTxt = S21MessageFunc.clspGetMessage(putAwayPmsg.xxMsgIdList.no(i).xxMsgId.getValue());
                    errMsgList.add(String.format(ML_FMT, xxMsgTxt, rtlWhCd, rtlSwhCd, mdseCd));
                }
                return false;
            }

            // Execute RWS Complete API
            List<NLZC207001PMsg> rwsComplPmsgList = execRwsCompAPI(rwsSerList, swhTransferMap);
            for (NLZC207001PMsg rwsComplPmsg : rwsComplPmsgList) {
                if (S21ApiUtil.isXxMsgId(rwsComplPmsg)) {
                    for (int i = 0; i < rwsComplPmsg.xxMsgIdList.getValidCount(); i++) {
                        String xxMsgTxt = S21MessageFunc.clspGetMessage(rwsComplPmsg.xxMsgIdList.no(i).xxMsgId.getValue());
                        errMsgList.add(String.format(ML_FMT, xxMsgTxt, rtlWhCd, rtlSwhCd, mdseCd));
                    }
                    return false;
                }
            }

            // Execute Machine Master Update API
            List<NSZC001001PMsg> macMstPmsgList = execMacMstAPI(swhTransferMap);
            for (NSZC001001PMsg macMstPmsg : macMstPmsgList) {
                if (S21ApiUtil.isXxMsgId(macMstPmsg)) {
                    for (int i = 0; i < macMstPmsg.xxMsgIdList.getValidCount(); i++) {
                        String xxMsgTxt = S21MessageFunc.clspGetMessage(macMstPmsg.xxMsgIdList.no(i).xxMsgId.getValue());
                        errMsgList.add(String.format(ML_FMT, xxMsgTxt, rtlWhCd, rtlSwhCd, mdseCd));
                    }
                    return false;
                }
            }

            // Execute Serial Transaction API
            NLZC302001PMsg serTrnPmsg = execSerTrnAPI(rwsSerList, swhTransferMap, invtyOrdNum);
            if (S21ApiUtil.isXxMsgId(serTrnPmsg)) {
                for (int i = 0; i < serTrnPmsg.xxMsgIdList.getValidCount(); i++) {
                    String xxMsgTxt = S21MessageFunc.clspGetMessage(serTrnPmsg.xxMsgIdList.no(i).xxMsgId.getValue());
                    errMsgList.add(String.format(ML_FMT, xxMsgTxt, rtlWhCd, rtlSwhCd, mdseCd));
                }
                return false;
            }

        } // End Loop

        return true;
    }


    /**
     * Execute Inventory Order API Header
     */
    private NLZC003001PMsg execInventoryOrderAPIHeader(Map<String, Object> swhTransferMap, NLZC003001 inventoryOrderAPI) {
        NLZC003001PMsg pmsg = new NLZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
        ZYPEZDItemValueSetter.setValue(pmsg.xxDtTpCd, NLZC003001.DT_TP_HDR);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pmsg.invtyOrdTpCd, glInvOrdTpForSwhChng);
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, (String) swhTransferMap.get(RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, (String) swhTransferMap.get(LOC_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, (String) swhTransferMap.get(OTBD_TRX_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, (String) swhTransferMap.get(OTBD_TRX_RSN_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyOrdStsCd, INVTY_ORD_STS.APPROVED);
        ZYPEZDItemValueSetter.setValue(pmsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        inventoryOrderAPI.execute(pmsg, ONBATCH_TYPE.BATCH);
        return pmsg;
    }

    /**
     * Execute Inventory Order API Detail
     */
    private NLZC003001PMsg execInventoryOrderAPIDetail(Map<String, Object> swhTransferMap, String invtyOrdNum, String wkLineNum, NLZC003001 inventoryOrderAPI) {
        NLZC003001PMsg pmsg = new NLZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
        ZYPEZDItemValueSetter.setValue(pmsg.xxDtTpCd, NLZC003001.DT_TP_DTL);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pmsg.invtyOrdNum, invtyOrdNum);
        ZYPEZDItemValueSetter.setValue(pmsg.invtyOrdTpCd, glInvOrdTpForSwhChng);
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, (String) swhTransferMap.get(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pmsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(pmsg.invtyOrdLineNum, wkLineNum);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, (String) swhTransferMap.get(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, (String) swhTransferMap.get(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd_D1, (String) swhTransferMap.get(DEST_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd_D1, (String) swhTransferMap.get(LOC_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.toStkStsCd, (String) swhTransferMap.get(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.ordQty, (BigDecimal) swhTransferMap.get(INVTY_AVAL_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyOrdDtlStsCd, INVTY_ORD_STS.APPROVED);
        List<Map<String, Object>> serialList = (List<Map<String, Object>>) swhTransferMap.get(MACH_MSTR_LIST);
        if (!serialList.isEmpty()) {
            // QC#24066 ADD START
            BigDecimal svcConfigMstrPk = (BigDecimal) serialList.get(0).get(SVC_CONFIG_MSTR_PK);
            if (ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
                ZYPEZDItemValueSetter.setValue(pmsg.svcConfigMstrPk, svcConfigMstrPk);
            }
            // QC#24066 ADD END

            // QC#23868 MOD START
            int validCount = 0;
            for (int i = 0; i < serialList.size(); i++) {
                String serNum = (String) serialList.get(i).get(SER_NUM);
                if (ZYPCommonFunc.hasValue(serNum)) {
                    ZYPEZDItemValueSetter.setValue(pmsg.serialInfoList.no(i).invtyOrdNum, invtyOrdNum);
                    ZYPEZDItemValueSetter.setValue(pmsg.serialInfoList.no(i).invtyOrdLineNum, wkLineNum);
                    ZYPEZDItemValueSetter.setValue(pmsg.serialInfoList.no(i).serNum, serNum);
                    validCount++;
                }
            }
            pmsg.serialInfoList.setValidCount(validCount);
            // QC#23868 MOD END
        }
        inventoryOrderAPI.execute(pmsg, ONBATCH_TYPE.BATCH);
        return pmsg;
    }

    /**
     * Execute Allocation for Non CPO API
     */
    private NWZC107001PMsg execAllocationForNonCPOAPI(Map<String, Object> swhTransferMap, String invtyOrdNum, String wkLineNum) {
        NWZC107001PMsg pmsg = new NWZC107001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NWZC107001.REQ_TP_NEW);
        ZYPEZDItemValueSetter.setValue(pmsg.xxPrtlAcptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pmsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, (String) swhTransferMap.get(OTBD_TRX_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, (String) swhTransferMap.get(OTBD_TRX_RSN_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.trxHdrNum, invtyOrdNum);
        ZYPEZDItemValueSetter.setValue(pmsg.trxLineNum, wkLineNum);
        ZYPEZDItemValueSetter.setValue(pmsg.trxLineSubNum, String.format("%03d", 1));
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, (String) swhTransferMap.get(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, (String) swhTransferMap.get(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, (String) swhTransferMap.get(LOC_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, (String) swhTransferMap.get(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, (BigDecimal) swhTransferMap.get(INVTY_AVAL_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.rddDt, salesDate);
        ZYPEZDItemValueSetter.setValue(pmsg.xxUnitPrc, (BigDecimal) swhTransferMap.get(UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(pmsg.xxSlsAmt, (BigDecimal) swhTransferMap.get(TOT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(pmsg.billToCustCd, (String) swhTransferMap.get(DEST_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.sellToCustCd, (String) swhTransferMap.get(DEST_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.shipToCustCd, (String) swhTransferMap.get(DEST_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pmsg.slsDt, salesDate);
        // QC#23337
        ZYPEZDItemValueSetter.setValue(pmsg.rsdDt, salesDate);
        new NWZC107001().execute(pmsg, ONBATCH_TYPE.BATCH);
        return pmsg;
    }

    /**
     * Execute Shipping Plan Update API
     */
    private List<NWZC003001PMsg> execShpgPlnUpdAPI(Map<String, Object> swhTransferMap, String invtyOrdNum, String wkLineNum) {
        NWZC003001PMsg pmsg = new NWZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pmsg.shpgModeCd, NWZC003001.MODE_SHIPPINGREQUEST);
        ZYPEZDItemValueSetter.setValue(pmsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.trxHdrNum, invtyOrdNum);
        ZYPEZDItemValueSetter.setValue(pmsg.trxLineNum, wkLineNum);
        ZYPEZDItemValueSetter.setValue(pmsg.trxLineSubNum, String.format("%03d", 1));
        ZYPEZDItemValueSetter.setValue(pmsg.avalSoQty, (BigDecimal) swhTransferMap.get(INVTY_AVAL_QTY));
        List<NWZC003001PMsg> pMsgList = new ArrayList<NWZC003001PMsg>();
        pMsgList.add(pmsg);
        new NWZC003001().execute(pMsgList, ONBATCH_TYPE.BATCH);
        return pMsgList;
    }

    /**
     * Execute SO API
     */
    private List<NLZC205001PMsg> execSoAPI(Map<String, Object> swhTransferMap, String invtyOrdNum, String wkLineNum) {

        List<NLZC205001PMsg> pMsgList = new ArrayList<NLZC205001PMsg>();

        // Get SHPG_PLN_NUM Before Execute API
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(GLBL_CMPY_CD, globalCompanyCode);
        param.put(TRX_HDR_NUM, invtyOrdNum);
        param.put(TRX_LINE_NUM, wkLineNum);
        List<Map<String, Object>> shpgPlnList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getShpgPln", param);
        if (shpgPlnList.isEmpty()) {
            return pMsgList;
        }

        for (Map<String, Object> shpgPln : shpgPlnList) {
            NLZC205001PMsg pmsg = new NLZC205001PMsg();
            ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(pmsg.sceOrdTpCd, glSceOrdTpForSwhChng);
            ZYPEZDItemValueSetter.setValue(pmsg.shpgPlnNum, (String) shpgPln.get(SHPG_PLN_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.shpgFrceFlg, NLZC205001.SHPG_FRCE_FLG_ON);
            ZYPEZDItemValueSetter.setValue(pmsg.xxModeCd, NLZC205001.MODE_NEW);
            pMsgList.add(pmsg);
        }
        new NLZC205001().execute(pMsgList, ONBATCH_TYPE.BATCH);
        return pMsgList;
    }

    /**
     * Execute RWS API
     */
    private List<NLZC200001PMsg> execRwsAPI(List<NLZC205001PMsg> soApiPmsgList) {
        List<NLZC200001PMsg> pmsgList = new ArrayList<NLZC200001PMsg>();
        for (NLZC205001PMsg soApiPmsg : soApiPmsgList) {
            NLZC200001PMsg pmsg = new NLZC200001PMsg();
            ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
            ZYPEZDItemValueSetter.setValue(pmsg.inbdSrcTpCd, INBD_SRC_TP.SO);
            ZYPEZDItemValueSetter.setValue(pmsg.soNum, soApiPmsg.soNum);
            new NLZC200001().execute(pmsg, ONBATCH_TYPE.BATCH);
            pmsgList.add(pmsg);
        }
        return pmsgList;
    }

    /**
     * Execute RWS Serial API
     */
    private List<NLZC304001PMsg> execRwsSerialAPI(List<Map<String, Object>> rwsSerList) {

        List<NLZC304001PMsg> pmsgList = new ArrayList<NLZC304001PMsg>();
        NLZC304001PMsg pMsg = new NLZC304001PMsg();

        int serListCount = 0;
        int i = 0;
        String oldRwsNum = "";

        // RWS_NUM Loop
        for (Map<String, Object> rwsSer : rwsSerList) {
            serListCount++;

            String serNum = (String) rwsSer.get(SER_NUM);
            if (ZYPCommonFunc.hasValue(serNum)) {

                String rwsNum = (String) rwsSer.get(RWS_NUM);
                if (i == 0) {
                    oldRwsNum = rwsNum;
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
                    ZYPEZDItemValueSetter.setValue(pMsg.rwsNum, rwsNum);
                }

                if (oldRwsNum.equals(rwsNum)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).rwsDtlLineNum, (String) rwsSer.get(RWS_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).serNum, serNum);
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).mdseCd, (String) rwsSer.get(SO_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).putAwayChkStsCd, PUT_AWAY_CHK_STS.NO_NEED);
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).serNumSendFlg, ZYPConstant.FLG_OFF_N);
                    pMsg.SerialList.setValidCount(i + 1);
                    i++;
                }

                if (!oldRwsNum.equals(rwsNum)) {
                    // Execute API
                    new NLZC304001().execute(pMsg, ONBATCH_TYPE.BATCH);
                    pmsgList.add(pMsg);

                    oldRwsNum = rwsNum;
                    i = 0;
                    pMsg = new NLZC304001PMsg();
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
                    ZYPEZDItemValueSetter.setValue(pMsg.rwsNum, rwsNum);
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).rwsDtlLineNum, (String) rwsSer.get(RWS_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).serNum, serNum);
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).mdseCd, (String) rwsSer.get(SO_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).putAwayChkStsCd, PUT_AWAY_CHK_STS.NO_NEED);
                    ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).serNumSendFlg, ZYPConstant.FLG_OFF_N);
                    pMsg.SerialList.setValidCount(i + 1);
                    i++;
                }

                // Last Data
                if (serListCount == rwsSerList.size()) {
                    // Execute API
                    new NLZC304001().execute(pMsg, ONBATCH_TYPE.BATCH);
                    pmsgList.add(pMsg);
                }
            }
        }

        return pmsgList;
    }

    /**
     * Execute Put Away Confirmation from S21 DC API
     */
    private NLZC206001PMsg execPutAwayConfAPI(List<Map<String, Object>> rwsSerList) {

        NLZC206001PMsg pMsg = new NLZC206001PMsg();

        int rwsDtlCnt = 0;
        int rwsSerCnt = 0;

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);

        // QC#19475 START
        String beforeRwsNum = "";
        String beforeRwsDtlLineNum = "";

        for (Map<String, Object> rwsSer : rwsSerList) {
            if (!beforeRwsNum.equals((String) rwsSer.get(RWS_NUM)) || !beforeRwsDtlLineNum.equals((String) rwsSer.get(RWS_DTL_LINE_NUM))) {
                ZYPEZDItemValueSetter.setValue(pMsg.RWSPutAwayList.no(rwsDtlCnt).rwsNum, (String) rwsSer.get(RWS_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.RWSPutAwayList.no(rwsDtlCnt).rwsDtlLineNum, (String) rwsSer.get(RWS_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.RWSPutAwayList.no(rwsDtlCnt).invtyStkStsCd, (String) rwsSer.get(INVTY_STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.RWSPutAwayList.no(rwsDtlCnt).sceOrdTpCd, (String) rwsSer.get(SCE_ORD_TP_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.RWSPutAwayList.no(rwsDtlCnt).rwsStkDtTmTs, salesDate + ZYPDateUtil.getCurrentSystemTime("HHmmss"));
                ZYPEZDItemValueSetter.setValue(pMsg.RWSPutAwayList.no(rwsDtlCnt).rwsStkQty, (BigDecimal) rwsSer.get(RWS_QTY));
                ZYPEZDItemValueSetter.setValue(pMsg.RWSPutAwayList.no(rwsDtlCnt).whCd, (String) rwsSer.get(INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.RWSPutAwayList.no(rwsDtlCnt).mdseCd, (String) rwsSer.get(MDSE_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.RWSPutAwayList.no(rwsDtlCnt).rwsRefNum, (String) rwsSer.get(RWS_REF_NUM));
                rwsDtlCnt++;
            }

            // Set Serial
            String serNum = (String) rwsSer.get(SER_NUM);
            if (ZYPCommonFunc.hasValue(serNum)) {
                ZYPEZDItemValueSetter.setValue(pMsg.RcvSerNumList.no(rwsSerCnt).rwsDtlLineNum, (String) rwsSer.get(RWS_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.RcvSerNumList.no(rwsSerCnt).serNum, serNum);
                ZYPEZDItemValueSetter.setValue(pMsg.RcvSerNumList.no(rwsSerCnt).mdseCd, (String) rwsSer.get(SO_MDSE_CD));
                rwsSerCnt++;
            }

            beforeRwsNum = (String) rwsSer.get(RWS_NUM);
            beforeRwsDtlLineNum = (String) rwsSer.get(RWS_DTL_LINE_NUM);
            // QC#19475 END
        }
        pMsg.RWSPutAwayList.setValidCount(rwsDtlCnt);
        pMsg.RcvSerNumList.setValidCount(rwsSerCnt);

        new NLZC206001().execute(pMsg, ONBATCH_TYPE.BATCH);
        return pMsg;
    }


    /**
     * Execute RWS Complete API
     */
    private List<NLZC207001PMsg> execRwsCompAPI(List<Map<String, Object>> rwsSerList, Map<String, Object> swhTransferMap) {

        List<NLZC207001PMsg> pmsgList = new ArrayList<NLZC207001PMsg>();

        String oldRwsNum = "";
        for (Map<String, Object> rwsSer : rwsSerList) {
            String rwsNum = (String) rwsSer.get(RWS_NUM);
            if (!oldRwsNum.equals(rwsNum)) {
                oldRwsNum = rwsNum;
                NLZC207001PMsg pMsg = new NLZC207001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
                ZYPEZDItemValueSetter.setValue(pMsg.rwsNum, rwsNum);
                ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, glSceOrdTpForSwhChng);
                ZYPEZDItemValueSetter.setValue(pMsg.rwsCloDtTmTs, salesDate + ZYPDateUtil.getCurrentSystemTime("HHmmss"));
                ZYPEZDItemValueSetter.setValue(pMsg.whCd, (String) swhTransferMap.get(RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.rwsRefNum, (String) rwsSer.get(RWS_REF_NUM));
                new NLZC207001().execute(pMsg, ONBATCH_TYPE.BATCH);
                pmsgList.add(pMsg);
            }
        }

        return pmsgList;
    }

    /**
     * Execute Machine Master Update API
     */
    private List<NSZC001001PMsg> execMacMstAPI(Map<String, Object> swhTransferMap) {

        List<NSZC001001PMsg> pmsgList = new ArrayList<NSZC001001PMsg>();

        List<Map<String, Object>> machMstrList = (List<Map<String, Object>>) swhTransferMap.get(MACH_MSTR_LIST);
        for (Map<String, Object> machMstr : machMstrList) {
            BigDecimal svcMachMstrPk = (BigDecimal) machMstr.get(SVC_MACH_MSTR_PK);
            String stkStsCd = (String) machMstr.get(STK_STS_CD);
            if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                NSZC001001PMsg pMsg = new NSZC001001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.UPDATE_INVENTORY.code);
                ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, (BigDecimal) machMstr.get(SVC_CONFIG_MSTR_PK));
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, (String) machMstr.get(SVC_MACH_MSTR_STS_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
                ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, stkStsCd);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, (String) swhTransferMap.get(LOC_STS_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, (String) swhTransferMap.get(DEST_INVTY_LOC_CD));
                pmsgList.add(pMsg);

                pMsg = new NSZC001001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
                ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, stkStsCd);
                pmsgList.add(pMsg);

            }
        }

        if (pmsgList.size() > 0) {
            new NSZC001001().execute(pmsgList, ONBATCH_TYPE.BATCH);
        }

        return pmsgList;

    }

    /**
     * Execute Serial Transaction API
     */
    private NLZC302001PMsg execSerTrnAPI(List<Map<String, Object>> rwsSerList, Map<String, Object> swhTransferMap, String invtyOrdNum) {

        NLZC302001PMsg pMsg = new NLZC302001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);

        int i = 0;
        for (Map<String, Object> rwsSer : rwsSerList) {
            String serNum = (String) rwsSer.get(SER_NUM);
            if (ZYPCommonFunc.hasValue(serNum)) {
                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(i).serNum, serNum);
                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(i).mdseCd, (String) rwsSer.get(SO_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(i).serTrxTs, salesDate + ZYPDateUtil.getCurrentSystemTime("HHmmss"));
                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(i).serTrxEventCd, SER_TRX_EVENT.SUB_WAREHOUSE_CHANGE);
                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(i).serTrxSrcTpCd, SER_TRX_SRC_TP.SO);
                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(i).serTrxSrcHdrNum, (String) rwsSer.get(SO_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(i).serTrxSrcLineNum, (String) rwsSer.get(SO_SLP_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(i).serTrxRefNum, invtyOrdNum);
                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(i).fromLocCd, (String) swhTransferMap.get(INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(i).toLocCd, (String) swhTransferMap.get(DEST_INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(i).manCratFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(i).toStkStsCd, (String) rwsSer.get(INVTY_STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.UpdateDetailList.no(i).locStsCd, LOC_STS.DC_STOCK);
                pMsg.UpdateDetailList.setValidCount(i + 1);
                i++;
            }
        }

        if (i > 0) {
            new NLZC302001().execute(pMsg, ONBATCH_TYPE.BATCH);
        }

        return pMsg;
    }

    /**
     * Post Error Mail
     */
    private void postErrorMail() {

        // Get Error Info
        StringBuilder message = new StringBuilder();
        for (String errMsg : errMsgList) {
            message.append(errMsg);
            message.append(LINE_FEED_CODE);
        }
        S21InfoLogOutput.println(message.toString());

        // Get Mail Address From
        S21MailGroup groupFrom = new S21MailGroup(globalCompanyCode, MAIL_FROM_GROUP_ID);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        S21MailAddress addrFrom = null;
        if (!addrFromList.isEmpty()) {
            addrFrom = addrFromList.get(0);
        }

        // Get Mail Address To
        S21MailGroup groupTo = new S21MailGroup(globalCompanyCode, MAIL_TO_GROUP_ID);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList.isEmpty()) {
            throw new S21AbendException(NLBM1065E);
        }

        // Get Mail Subject and Body
        S21MailTemplate template = new S21MailTemplate(globalCompanyCode, MAIL_TEMPLATE_ID);
        template.setTemplateParameter(EMAIL_PARAM_BATCH_ID, BATCH_ID);
        String currentTime = ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT_MM_DD_YYYY_HH_MM);
        template.setTemplateParameter(EMAIL_PARAM_DATE, currentTime);
        template.setTemplateParameter(EMAIL_PARAM_MSG, message.toString());

        // Call Mail API
        S21Mail mail = new S21Mail(globalCompanyCode);
        mail.setFromAddress(addrFrom);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
    }
}
