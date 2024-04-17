/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NWZ.NWZC164001;

import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.TERM_COND_CAP_BW_ORG_ATTRB_NM;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.TERM_COND_CAP_CLR_ORG_ATTRB_NM;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.TERM_COND_CAP_LENGTH;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.TERM_COND_CAP_TOT_ORG_ATTRB_NM;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.BUYOUT_APPROVAL;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.CREDIT_APPROVAL;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.DOCUMENT_TYPE;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.PROCESS_ID;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.PROFITABILITY_OR_APPRV;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.SUB_SYS_ID;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.SUPPLY_ABUSE;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.VALIDATION_APPROVAL;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.WF_OPEN_BUYOUT_APPROVAL_HOLD;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.WF_OPEN_CONTRACT_STATUS_HOLD;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.WF_OPEN_CREDIT_APPROVAL_HOLD;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.WF_OPEN_PENDING_ORDER_HOLD;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.WF_OPEN_PROFITABILITY_HOLD;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.WF_OPEN_SUPPLY_ENFORCEMENT_HOLD;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.WF_OPEN_SUPPLY_YIELD_HOLD;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.WF_OPEN_VALIDATION_HOLD;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.WF_PRFT_NO_PROC;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.STYLE_01;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.CONST_KEY_MAN_PRC_APVL_WF_ID;
import static com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant.WF_OPEN_MAN_PRC_APVL;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.DS_CUST_TRX_RULETMsg;
import business.db.DS_CUST_TRX_RULETMsgArray;
import business.db.HLDTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.parts.NWZC156001PMsg;
import business.parts.NWZC156001_svcConfigRefPMsg;
import business.parts.NWZC156002PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157004PMsg;
import business.parts.NWZC164001PMsg;
import business.parts.NWZC188001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC156001.NWZC156001;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC164001.constant.NWZC164001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC188001.NWZC188001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_PRFT_TRX_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_PROC_NODE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.impl.S21NwfTokenImpl;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;

/**
 * <pre>
 * NWZC1640 Order Header Workflow Control API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/07   Fujitsu         H.Nagashima     Create          N/A
 * 2016/03/16   SRAA            K.Aratani       Create          S21_NA#5591
 * 2016/03/17   Fujitsu         S.Yamamoto      Update          S21_NA#2939
 * 2016/03/30   Fujitsu         S.Yamamoto      Update          S21_NA#6292
 * 2016/04/21   Fujitsu         M.Yamada        Update          S21_NA#7257
 * 2016/04/21   SRAA            K.Aratani       Update          S21_NA#7899
 * 2016/05/10   SRA             E.Inada         Update          S21_NA#7707
 * 2016/06/02   Fujitsu         S.Takami        Update          S21_NA#6292-2
 * 2016/06/03   SRAA            K.Aratani       Update          S21_NA#9522
 * 2016/07/25   Fujitsu         T.Ishii         Update          S21_NA#11179
 * 2016/07/28   Fujitsu         T.Ishii         Update          S21_NA#8284(WF_CREDIT_APPROVAL_CONDITION)
 * 2016/08/01   Fujitsu         T.Ishii         Update          S21_NA#12452
 * 2016/08/01   Fujitsu         H.Nagashima     Update          S21_NA#11152
 * 2016/08/05   Fujitsu         T.Yoshida       Update          S21_NA#13106
 * 2016/08/05   Fujitsu         S.Yamamoto      Update          S21_NA#13013
 * 2016/08/16   SRAA            K.Aratani       Update          S21_NA#12904
 * 2016/09/02   Fujitsu         T.Yoshida       Update          S21_NA#10321
 * 2016/09/08   Fujitsu         H.Nagashima     Update          S21_NA#13477
 * 2016/09/16   Fujitsu         N.Sugiura       Update          S21_NA#14402
 * 2016/10/11   Fujitsu         N.Aoyama        Update          S21_NA#14974
 * 2016/10/11   Fujitsu         M.Ohno          Update          S21_NA#16308
 * 2017/01/13   Fujitsu         M.Ohno          Update          S21_NA#16995
 * 2017/04/13   Fujitsu         T.Aoi           Update          S21_NA#18207
 * 2017/04/13   Fujitsu         T.Aoyama        Update          S21_NA#19009
 * 2017/09/29   Fujitsu         T.Murai         Update          S21_NA#21081
 * 2017/10/26   Fujitsu         H.Sugawara      Update          QC#21466
 * 2018/01/15   Hitachi         Y.Takeno        Update          QC#21078
 * 2018/01/24   Fujitsu         H.Ikeda         Update          QC#22095
 * 2018/02/06   Fujitsu         T.Murai         Update          QC#23654
 * 2018/02/27   Fujitsu         H.Ikeda         Update          QC#23994
 * 2018/05/10   Fujitsu         Mz.Takahashi    Update          QC#20149
 * 2018/07/24   Fujitsu         Y.Matsui        Update          QC#26798
 * 2018/07/27   Fujitsu         Y.Matsui        Update          QC#26876
 * 2018/08/27   Fujitsu         Y.Matsui        Update          QC#27766
 * 2018/09/25   Hitachi         K.Kim           Update          QC#28318
 * 2019/01/15   Fujitsu         H.Ikeda         Update          QC#29913
 * 2019/02/05   Hitachi         E.Kameishi      Update          QC#30163
 * 2021/10/18   CITS            F.Deola         Update          QC#59060
 * 2023/04/25   Hitachi         A.Kohinata      Update          QC#61337
 * 2023/06/09   Hitachi         A.Kohinata      Update          QC#61337
 * 2023/09/13   Hitachi         S.Fujita        Update          QC#61783
 *</pre>
 */
public class NWZC164001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = null;

    /** S21Logger */
    private S21Logger logger = S21LoggerFactory.getLogger(NWZC164001.class);

    /** global company code */
    private String glblCmpyCd = null;

    /** Attribute Cut Length  */
    private BigDecimal attributeCutLength = null;

    /**
     * Constructor
     */
    public NWZC164001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * execute
     * </pre>
     * @param param NWZC164001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC164001PMsg param, final ONBATCH_TYPE onBatchType) {

        writeStartLog("execute");

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("InputData=" + param.toString());
        }

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        try {
            doProcess(msgMap, onBatchType);
            NWZC188001PMsg pMsg = new NWZC188001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, param.cpoOrdNum);

            NWZC188001 ordStsUpdtApi = new NWZC188001();
            ordStsUpdtApi.execute(pMsg, ONBATCH_TYPE.ONLINE);

            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
        // QC#5591
        } catch (S21NwfBizException e) {
            // debug
            e.printStackTrace();
            //QC#12904
            //Profitability Workflow
            if (hasValue(e.getMessageInfo().getCode()) && NWZC164001Constant.NWZM1982E.equals(e.getMessageInfo().getCode())) {
                msgMap.addXxMsgId(e.getMessageInfo().getCode());
            //Credit Review Workflow
            } else if (hasValue(e.getMessageInfo().getCode()) && NWZC164001Constant.NWZM1929E.equals(e.getMessageInfo().getCode())) {
                msgMap.addXxMsgId(e.getMessageInfo().getCode());
            //Validation Workflow
            } else if (hasValue(e.getMessageInfo().getCode()) && NWZC164001Constant.NWZM1989E.equals(e.getMessageInfo().getCode())) {
                msgMap.addXxMsgId(e.getMessageInfo().getCode());
            //Supply Abuse (Contact Status) Workflow
            } else if (hasValue(e.getMessageInfo().getCode()) && NWZC164001Constant.NWZM1984E.equals(e.getMessageInfo().getCode())) {
                msgMap.addXxMsgId(e.getMessageInfo().getCode());
            //Supply Abuse (Pending Order) Workflow
            } else if (hasValue(e.getMessageInfo().getCode()) && NWZC164001Constant.NWZM1985E.equals(e.getMessageInfo().getCode())) {
                msgMap.addXxMsgId(e.getMessageInfo().getCode());
            //Supply Abuse (Supply Enforcement) Workflow
            } else if (hasValue(e.getMessageInfo().getCode()) && NWZC164001Constant.NWZM1986E.equals(e.getMessageInfo().getCode())) {
                msgMap.addXxMsgId(e.getMessageInfo().getCode());
            //Supply Abuse (Yield) Workflow
            } else if (hasValue(e.getMessageInfo().getCode()) && NWZC164001Constant.NWZM1987E.equals(e.getMessageInfo().getCode())) {
                msgMap.addXxMsgId(e.getMessageInfo().getCode());
            //Lease Buyout Workflow
            } else if (hasValue(e.getMessageInfo().getCode()) && NWZC164001Constant.NWZM1988E.equals(e.getMessageInfo().getCode())) {
                msgMap.addXxMsgId(e.getMessageInfo().getCode());
            //Orher Workflows
            } else if (hasValue(e.getMessageInfo().getCode()) && NWZC164001Constant.NWZM1983E.equals(e.getMessageInfo().getCode())) {
                msgMap.addXxMsgId(e.getMessageInfo().getCode());
            //Else Error
            } else {
                //A system error occurred. Please contact your system administrator.
                msgMap.addXxMsgId(NWZC164001Constant.NWZM0474E);
            }
        } catch (S21NwfException e) {
            // debug
            e.printStackTrace();
            //A system error occurred. Please contact your system administrator.
            msgMap.addXxMsgId(NWZC164001Constant.NWZM0474E);
        // 2016/06/02 S21_NA#6292-2 Add Start
        } catch (Exception e) {
            // debug
            e.printStackTrace();
            //A system error occurred. Please contact your system administrator.
            msgMap.addXxMsgId(NWZC164001Constant.NWZM0474E);
        }
        // 2016/06/02 S21_NA#6292-2 Add End

        msgMap.flush();

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("OutputData=" + param.toString());
        }

        writeEndLog("execute");
    }

    /**
     * <pre>
     * execute
     * </pre>
     * @param params List<NWZC164001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWZC164001PMsg> params, final ONBATCH_TYPE onBatchType) {

        writeStartLog("execute:params");

        for (NWZC164001PMsg param : params) {
            execute(param, onBatchType);
        }

        writeEndLog("execute:params");
    }

    private void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) throws S21NwfException {

        NWZC164001PMsg param = (NWZC164001PMsg) msgMap.getPmsg();

        checkInput(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // cut length
        attributeCutLength = ZYPCodeDataUtil.getNumConstValue("NWZC164001_ATT_CUT_LEN", param.glblCmpyCd.getValue());

        List<NWZC164001Bean> byotApvlBeanList = new ArrayList<NWZC164001Bean>();
        List<NWZC164001Bean> resList = getTargetData(param);
        boolean crWfStartFlg = false;

        // add start 2023/04/25 QC#61337
        // In advance, get Manual Price Approval Bean Data
        NWZC164001Bean manualPriceApprovalBean = null;
        for (NWZC164001Bean bean : resList) {
            if (ORD_PROC_NODE.MAN_PRC_APPROVAL.equals(bean.getOrdProcNodeCd()) && bean.getHldPk() != null) {
                manualPriceApprovalBean = copyBean(bean);
            }
        }
        boolean isPrfitabilityApprovalStart = false;
        // add end 2023/04/25 QC#61337

        for (NWZC164001Bean bean : resList) {

            // ##### Validation Approval #####
            //if (ORD_PROC_NODE.VALIDATION_APPROVAL.equals(bean.getOrdProcNodeCd())) {
            // 2017/04/13 S21_NA#18207 Mod Start
            //if (VALIDATION_APPROVAL.equals(bean.getOrdProcNodeCd())) {
            if (VALIDATION_APPROVAL.equals(bean.getOrdProcNodeCd()) && bean.getHldPk() != null) {
            // 2017/04/13 S21_NA#18207 Mod End
                if (startValidationApprovalWorkflow(msgMap, bean)) {
                    break;
                }
            }

            // ##### Profitability Approval #####
            //if (ORD_PROC_NODE.PROFITABILITY_OR_APPRV.equals(bean.getOrdProcNodeCd())) {
            // 2017/04/13 S21_NA#18207 Mod Start
            if (PROFITABILITY_OR_APPRV.equals(bean.getOrdProcNodeCd())) {
                if (bean.getHldPk() != null) {
                    // QC#2526 START Open after Profitability API is tested
                    // mod start 2023/04/25 QC#61337
                    //if (startProfitabilityApprovalWorkflow(msgMap, bean)) {
                    if (startProfitabilityApprovalWorkflow(msgMap, bean, manualPriceApprovalBean)) {
                        isPrfitabilityApprovalStart = true;
                    // mod end 2023/04/25 QC#61337
                        break;
                    }
                    // QC#2526 END Open after Profitability API is tested
                } else {

                    // ### get header data ###
                    Map<String, Object> paramMap = new HashMap<String, Object>();
                    paramMap.put("glblCmpyCd",  bean.getGlblCmpyCd());
                    paramMap.put("cpoOrdNum",   bean.getCpoOrdNum());

                    Map<String, Object> mapResHdr = getSsmQueryObject(msgMap, "getPrftApvlHdrData", paramMap);
                    if (mapResHdr == null) {
                        return;
                    }

                    // ### get line data ### //
                    paramMap = new HashMap<String, Object>();
                    paramMap.put("glblCmpyCd",  bean.getGlblCmpyCd());
                    paramMap.put("trxHdrNum",   bean.getCpoOrdNum());
                    paramMap.put("outBound", CONFIG_CATG.OUTBOUND);
                    paramMap.put("inBound", CONFIG_CATG.INBOUND);
                    paramMap.put("ordPrftTrxCatgOutBound", ORD_PRFT_TRX_CATG.OUTBOUND);
                    paramMap.put("ordPrftTrxCatgInBound", ORD_PRFT_TRX_CATG.INBOUND);
                    paramMap.put("slsDt", ZYPDateUtil.getSalesDate());

                    List<Map<String, Object>> mapResList = getSsmQueryObjectListWithOutException(msgMap, "getPrftApvlLineData", paramMap);
                    if (mapResList.isEmpty()) {
                        return;
                    }
                    
                    // 2017/06/13 S21_NA#19009 Mod Start
                    paramMap = new HashMap<String, Object>();
                    paramMap.put("glblCmpyCd", bean.getGlblCmpyCd());
                    paramMap.put("trxHdrNum", bean.getCpoOrdNum());
                    Map<String, Object> mapOrdPrft = (Map<String, Object>) ssmClient.queryObject("getOrdPrft", paramMap);
                    if (mapOrdPrft == null) {
                        // call profitability API
                        NWZC156001PMsg prftApiPMsg = new NWZC156001PMsg();
                        if (!callPrftCalcApi(msgMap, prftApiPMsg, mapResHdr, mapResList)) {
                            return;
                        }
                    }
                    // If the Ord_Prft data is empty, call PrftCalcApi. 
                    // 2017/06/13 S21_NA#19009 Mod End

                }
            }
            // 2017/04/13 S21_NA#18207 Mod End

            // add start 2023/06/09 QC#61337
            // ##### Manual Price Approval #####
            if (ORD_PROC_NODE.MAN_PRC_APPROVAL.equals(bean.getOrdProcNodeCd())) {
                if (!isPrfitabilityApprovalStart && manualPriceApprovalBean != null) {
                    startManualPriceApprovalWorkflow(msgMap, manualPriceApprovalBean);
                    break;
                }
            }
            // add end 2023/06/09 QC#61337

            // ##### Supply Abuse #####
            //if (ORD_PROC_NODE.SUPPLY_ABUSE.equals(bean.getOrdProcNodeCd())) {
            // 2017/04/13 S21_NA#18207 Mod Start
            //if (SUPPLY_ABUSE.equals(bean.getOrdProcNodeCd())) {
            if (SUPPLY_ABUSE.equals(bean.getOrdProcNodeCd()) && bean.getHldPk() != null) {
            // 2017/04/13 S21_NA#18207 Mod End
                String wfKey = createWfKey(bean.getCpoOrdNum(), bean.getOrdProcNodeCd(), bean.getSplyAbuseNodePrflCd(), bean.getHldRsnCd());
                // Yield Validation Hold
                if (HLD_RSN.SUPPLY_YIELD_VALIDATION_HOLD.equals(bean.getHldRsnCd())) {
                    if (startSuplyAbuseYieldApprovalWorkflow(msgMap, bean, wfKey)) {
                        break;
                    }

                // Pending Order Hold
                } else if (HLD_RSN.PENDING_ORDER_HOLD.equals(bean.getHldRsnCd())) {
                    if (startSuplyAbusePendingOrderApprovalWorkflow(msgMap, bean, wfKey)) {
                        break;
                    }

                // Contract Status Hold
                } else if (HLD_RSN.CONTRACT_STATUS_HOLD.equals(bean.getHldRsnCd())) {
                    if (startSuplyAbuseContractStatusApprovalWorkflow(msgMap, bean, wfKey)) {
                        break;
                    }

                // Supply Enforcement Hold
                } else if (HLD_RSN.SUPPLY_ENFORCEMENT_HOLD.equals(bean.getHldRsnCd())) {
                    if (startSuplyAbuseEnforcementHoldApprovalWorkflow(msgMap, bean, wfKey)) {
                        break;
                    }

                }
            }

            // ##### Credit Approval #####
            // 2017/04/13 S21_NA#18207 Mod Start
            //if (!crWfStartFlg && CREDIT_APPROVAL.equals(bean.getOrdProcNodeCd())) {
            if (!crWfStartFlg && CREDIT_APPROVAL.equals(bean.getOrdProcNodeCd()) && bean.getHldPk() != null) {
            // 2017/04/13 S21_NA#18207 Mod Start
                crWfStartFlg = true;
                startCreditApprovalWorkflow(msgMap, bean);
                break;
            }

            // ##### Buyout Approval #####
            //if (ORD_PROC_NODE.BUYOUT_APPROVAL.equals(bean.getOrdProcNodeCd())) {
            // 2017/04/13 S21_NA#18207 Mod Start
            //if (BUYOUT_APPROVAL.equals(bean.getOrdProcNodeCd())) {
            if (BUYOUT_APPROVAL.equals(bean.getOrdProcNodeCd()) && bean.getHldPk() != null) {
            // 2017/04/13 S21_NA#18207 Mod End
                byotApvlBeanList.add(bean);
            }

            // add start 2023/04/25 QC#61337
            // del start 2023/06/09 QC#61337
            //if (!isPrfitabilityApprovalStart && manualPriceApprovalBean != null) {
            //    startManualPriceApprovalWorkflow(msgMap, manualPriceApprovalBean);
            //}
            // del end 2023/06/09 QC#61337
            // add end 2023/04/25 QC#61337

        }

        // ##### Buyout Approval Workflow #####
        if (!byotApvlBeanList.isEmpty()) {
            startBuyoutApprovalWorkflow(msgMap, byotApvlBeanList);
        }

    }

    private void checkInput(S21ApiMessageMap msgMap) {

        NWZC164001PMsg param = (NWZC164001PMsg) msgMap.getPmsg();

        if (!hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZC164001Constant.NMZM0011E);
        } else {
            glblCmpyCd = param.glblCmpyCd.getValue();
        }

        if (!hasValue(param.cpoOrdNum)) {
            msgMap.addXxMsgId(NWZC164001Constant.NWZM1205E);
        }

        if (!hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NWZC164001Constant.NWZM0978E);
        }

    }

    @SuppressWarnings("unchecked")
    private List<NWZC164001Bean> getTargetData(NWZC164001PMsg param) {

        // Set SQL Parameters
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",     param.glblCmpyCd.getValue());
        paramMap.put("cpoOrdNum",      param.cpoOrdNum.getValue());
        paramMap.put("slsDt",          param.slsDt.getValue());
        paramMap.put("relFlg",         ZYPConstant.FLG_OFF_N);
        paramMap.put("hldApplyAvalFlg", ZYPConstant.FLG_ON_Y);

        List<Map<String, Object>> mapResList = (List<Map<String, Object>>) ssmClient.queryObjectList("getWfTarget", paramMap);

        List<NWZC164001Bean> resBeanList = new ArrayList<NWZC164001Bean>();

        for (Map<String, Object> mapRes : mapResList) {
            NWZC164001Bean bean = new NWZC164001Bean();
            bean.setGlblCmpyCd(param.glblCmpyCd.getValue());
            bean.setCpoOrdNum(param.cpoOrdNum.getValue());
            bean.setHldPk((BigDecimal) mapRes.get("HLD_PK"));
            bean.setHldRsnCd((String) mapRes.get("HLD_RSN_CD"));
            bean.setHldDt((String) mapRes.get("HLD_DT"));
            bean.setHldQty((BigDecimal) mapRes.get("HLD_QTY"));
            bean.setCpoOrdNum((String) mapRes.get("CPO_ORD_NUM"));
            bean.setCpoDtlLineNum((String) mapRes.get("CPO_DTL_LINE_NUM"));
            bean.setCpoDtlLineSubNum((String) mapRes.get("CPO_DTL_LINE_SUB_NUM"));
            bean.setShpgPlnNum((String) mapRes.get("SHPG_PLN_NUM"));
            bean.setOrdProcNodeCd((String) mapRes.get("ORD_PROC_NODE_CD"));
            bean.setOrdProcNodeNm((String) mapRes.get("VLD_APVL_NODE_PRFL_CD"));
            bean.setVldApvlNodePrflCd((String) mapRes.get("CR_APVL_NODE_PRFL_CD"));
            bean.setCrApvlNodePrflCd((String) mapRes.get("CR_APVL_NODE_PRFL_CD"));
            bean.setPrftdApvlNodePrflCd((String) mapRes.get("PRFT_APVL_NODE_PRFL_CD"));
            bean.setSplyAbuseNodePrflCd((String) mapRes.get("SPLY_ABUSE_NODE_PRFL_CD"));
            bean.setOrdProcNodePrflNmVa((String) mapRes.get("ORD_PROC_NODE_PRFL_NM_VA"));
            bean.setOrdProcNodePrflNmCa((String) mapRes.get("ORD_PROC_NODE_PRFL_NM_CA"));
            bean.setOrdProcNodePrflNmPa((String) mapRes.get("ORD_PROC_NODE_PRFL_NM_PA"));
            bean.setOrdProcNodePrflNmSa((String) mapRes.get("ORD_PROC_NODE_PRFL_NM_SA"));
            bean.setOrdProcNodePrflNmBa((String) mapRes.get("ORD_PROC_NODE_PRFL_NM_BA"));
            bean.setOrdProcNodeNm((String) mapRes.get("ORD_PROC_NODE_NM"));
            // 2017/04/13 S21_NA#18207 Add Start
            bean.setHldRsnNm((String) mapRes.get("HLD_RSN_NM"));
            // 2017/04/13 S21_NA#18207 Add End
            // START 2018/09/25 [QC#28318,ADD]
            bean.setSlsDt(param.slsDt.getValue());
            // END 2018/09/25 [QC#28318,ADD]
            // add start 2023/04/25 QC#61337
            bean.setManPrcNodePrflCd((String) mapRes.get("MAN_PRC_NODE_PRFL_CD"));
            bean.setOrdProcNodePrflNmMa((String) mapRes.get("ORD_PROC_NODE_PRFL_NM_MA"));
            // add end 2023/04/25 QC#61337

            resBeanList.add(bean);
        }

        return resBeanList;
    }

    // ***** start Validation Approval Workflow *****
    private boolean startValidationApprovalWorkflow(S21ApiMessageMap msgMap, NWZC164001Bean bean) throws S21NwfException {

        String wfId   = getWorkflowId(NWZC164001Constant.CONST_KEY_VLD_APVL_WF_ID);
        String wfKey  = createWfKey(bean.getCpoOrdNum(), bean.getOrdProcNodeCd(), bean.getVldApvlNodePrflCd(), bean.getHldRsnCd());

        // ### check already started ###
        if (isWorkflowStarted(msgMap, wfId, wfKey)) {
            msgMap.addXxMsgId(NWZC164001Constant.NWZM1754E);
            return false;
        }

        // ### get header data ###
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",  bean.getGlblCmpyCd());
        paramMap.put("cpoOrdNum",   bean.getCpoOrdNum());

        Map<String, Object> mapResHdr = getSsmQueryObject(msgMap, "getValdApvlHdrData", paramMap);
        if (mapResHdr == null) {
            return false;
        }

        NWZC164001TokenObject tokenBiz = new NWZC164001TokenObject();

        // ### get line data ###
        paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",  bean.getGlblCmpyCd());
        paramMap.put("cpoOrdNum",   bean.getCpoOrdNum());
        // Add Start 2016/12/08 M.Ohno S21_NA#16308
        paramMap.put("ordLineStsCancel", ORD_LINE_STS.CANCELLED);
        paramMap.put("rtrnLineStsCancel", RTRN_LINE_STS.CANCELLED);
        // Add End   2016/12/08 M.Ohno S21_NA#16308

        List<Map<String, Object>> mapResList = getSsmQueryObjectList(msgMap, "getValdApvlLineData", paramMap);
        if (mapResList.isEmpty()) {
            return false;
        }

        // ### Set Attribute ###
        tokenBiz.setAttribute1Lbl("Order#");
        tokenBiz.setAttribute1(cutString(bean.getCpoOrdNum()));
        tokenBiz.setAttribute2Lbl("SOLD TO");
        tokenBiz.setAttribute2(cutString((String) mapResHdr.get("DS_ACCT_NM")));
        tokenBiz.setAttribute3Lbl("CATEGORY / REASON");
        tokenBiz.setAttribute3(cutString((String) mapResHdr.get("CATG_RSN")));
        tokenBiz.setAttribute4Lbl("BUS UNIT / BRANCH");
        tokenBiz.setAttribute4(cutString((String) mapResHdr.get("BUS_UNIT_BR")));
        // Mod Start 2017/10/26 QC#21466
        //tokenBiz.setAttribute5Lbl("Loan Order Total Cost");
        tokenBiz.setAttribute5Lbl("Order Total Cost");
        // Mod End 2017/10/26 QC#21466
        // 2016/09/16 S21_NA#14402 Mod Start
        // tokenBiz.setAttribute5(cutString(formatAmount((BigDecimal) mapResHdr.get("ENT_CPO_TOT_DEAL_NET_AMT"))));
        tokenBiz.setAttribute5(cutString(formatAmount((BigDecimal) mapResHdr.get("THIS_MTH_TOT_STD_COST_AMT"))));
        // 2016/09/16 S21_NA#14402 Mod End

        // 2018/05/09 QC#20149 Add Start
        tokenBiz.setDtlAttrb17((String) mapResHdr.get("SELL_TO_CUST_CD"));
        tokenBiz.setDtlAttrb18((String) mapResHdr.get("DS_ACCT_NM"));
        // 2018/05/09 QC#20149 Add End

        // ### set line data ###
        for (Map<String, Object> mapRes : mapResList) {
            NWZC164001TokenObjectLine lineData = new NWZC164001TokenObjectLine();
            // Config Category Name
            lineData.setLineAttrb1((String) mapRes.get("CONFIG_CATG_NM"));
            // Model
            lineData.setLineAttrb2((String) mapRes.get("MDL_NM"));
            // Qty
            lineData.setLineAttrb3(String.valueOf(mapRes.get("ORD_QTY")));
            tokenBiz.addLineData(lineData);
        }

        // set data
        tokenBiz.setOrdProcNodeCd(bean.getOrdProcNodeCd());
        tokenBiz.setOrdProcNodeNm(bean.getOrdProcNodeNm());
        tokenBiz.setOrdProcNodePrflCd(bean.getVldApvlNodePrflCd());

        // Set Condition Data
        tokenBiz.setBizId(wfId);
        tokenBiz.setCondStr1(bean.getOrdProcNodePrflNmVa());

        // start workflow
        startWorkflow(msgMap, wfId, wfKey, tokenBiz, bean);
        printBizProcLog(msgMap, WF_OPEN_VALIDATION_HOLD, bean);

        return true;
    }

    private void printBizProcLog(S21ApiMessageMap msgMap, String eventId, NWZC164001Bean bean) {
        final S21BusinessProcessLogMsg bizLogMsg = new S21BusinessProcessLogMsg();
        bizLogMsg.setSubSysId(SUB_SYS_ID);
        bizLogMsg.setProcId(PROCESS_ID);
        bizLogMsg.setEventId(eventId);
        bizLogMsg.setDocTpCd(DOCUMENT_TYPE);
        //        bizLogMsg.setDocId(docId);
        bizLogMsg.setPrntDocId(bean.getCpoOrdNum());
        //        bizLogMsg.setBizProcCmntTxt_01(bizProcCmntTxt_01);
        //        bizLogMsg.setBizProcCmntTxt_02(getHldCmntTxt(bean));
        //        bizLogMsg.setBizProcCmntTxt_03(bizProcCmntTxt_03);

        S21BusinessProcessLog.print(bizLogMsg);
    }

    // ***** start Profitability Approval Workflow *****
    // mod start 2023/04/25 QC#61337
    //private boolean startProfitabilityApprovalWorkflow(S21ApiMessageMap msgMap, NWZC164001Bean bean) throws S21NwfException {
    private boolean startProfitabilityApprovalWorkflow(S21ApiMessageMap msgMap, NWZC164001Bean bean, NWZC164001Bean manualPriceApprovalBean) throws S21NwfException {
    // mod end 2023/04/25 QC#61337

        String wfId  = getWorkflowId(NWZC164001Constant.CONST_KEY_PRFT_APVL_WF_ID);
        String wfKey = createWfKey(bean.getCpoOrdNum(), bean.getOrdProcNodeCd(), bean.getPrftdApvlNodePrflCd(), bean.getHldRsnCd());

        // ### check already started ###
        if (isWorkflowStarted(msgMap, wfId, wfKey)) {
            msgMap.addXxMsgId(NWZC164001Constant.NWZM1754E);
            return false;
        }

        // ### get header data ###
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",  bean.getGlblCmpyCd());
        paramMap.put("cpoOrdNum",   bean.getCpoOrdNum());

        Map<String, Object> mapResHdr = getSsmQueryObject(msgMap, "getPrftApvlHdrData", paramMap);
        if (mapResHdr == null) {
            return false;
        }

        NWZC164001TokenObject tokenBiz = new NWZC164001TokenObject();

        // ### get line data ### // 2016/03/10 S21_NA#2939
        paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",  bean.getGlblCmpyCd());
        paramMap.put("trxHdrNum",   bean.getCpoOrdNum());
        paramMap.put("outBound", CONFIG_CATG.OUTBOUND);
        paramMap.put("inBound", CONFIG_CATG.INBOUND);
        paramMap.put("ordPrftTrxCatgOutBound", ORD_PRFT_TRX_CATG.OUTBOUND);
        paramMap.put("ordPrftTrxCatgInBound", ORD_PRFT_TRX_CATG.INBOUND);
        paramMap.put("slsDt", ZYPDateUtil.getSalesDate()); // 2016/03/10 S21_NA#6292

        // 2016/06/02 S21_NA#6292-2 Mod Start
        // List<Map<String, Object>> mapResList = getSsmQueryObjectList(msgMap, "getPrftApvlLineData", paramMap);
        List<Map<String, Object>> mapResList = getSsmQueryObjectListWithOutException(msgMap, "getPrftApvlLineData", paramMap);
        // 2016/06/02 S21_NA#6292-2 Mod End
        if (mapResList.isEmpty()) {
            return false;
        }

        // 2017/04/13 S21_NA#18207 Restoration Start
        // 2016/10/11 QC#14974 DEL START
        // call profitability API
        NWZC156001PMsg prftApiPMsg = new NWZC156001PMsg();
        if (!callPrftCalcApi(msgMap, prftApiPMsg, mapResHdr, mapResList)) {
            return false;
        }
        // 2016/10/11 QC#14974 DEL E N D
        // 2017/04/13 S21_NA#18207 Restoration End

        // 2016/10/11 QC#14974 ADD START
        paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", bean.getGlblCmpyCd());
        paramMap.put("trxHdrNum", bean.getCpoOrdNum());
        Map<String, Object> mapOrdPrft = getSsmQueryObject(msgMap, "getOrdPrft", paramMap);
        if (mapOrdPrft.isEmpty()) {
            return false;
        }
        // 2016/10/11 QC#14974 ADD E N D

        // ### Set Attribute ###
        tokenBiz.setAttribute1Lbl("Order#");
        tokenBiz.setAttribute1(cutString(bean.getCpoOrdNum()));
        tokenBiz.setAttribute2Lbl("GP");
        // 2016/03/10 S21_NA#6292

        // 2016/10/11 QC#14974 UPD START
        // if (prftApiPMsg.altGrsPrftPct.getValueInt() != 0) {
        // tokenBiz.setAttribute2(cutString(String.valueOf(prftApiPMsg.altGrsPrftPct.getValue())));
        // } else {
        // tokenBiz.setAttribute2(cutString(String.valueOf(prftApiPMsg.grsPrftPct.getValue())));
        // }
        BigDecimal altGrsPrftPct = (BigDecimal) mapOrdPrft.get("ALT_GRS_PRFT_PCT");
        if (ZYPCommonFunc.hasValue(altGrsPrftPct) && BigDecimal.ZERO.compareTo(altGrsPrftPct) != 0) {
            tokenBiz.setAttribute2(cutString(String.valueOf(altGrsPrftPct)));
            tokenBiz.setDtlAttrb4(cutString(String.valueOf(altGrsPrftPct)));
            tokenBiz.setCondNum1(altGrsPrftPct);
        } else {
            BigDecimal grsPrftPct = (BigDecimal) mapOrdPrft.get("GRS_PRFT_PCT");
            tokenBiz.setAttribute2(cutString(String.valueOf(grsPrftPct)));
            tokenBiz.setDtlAttrb4(String.valueOf(grsPrftPct));
            tokenBiz.setCondNum1(grsPrftPct);
        }
        // 2016/10/11 QC#14974 UPD E N D

        tokenBiz.setAttribute3Lbl("SOLD TO");
        tokenBiz.setAttribute3(cutString((String) mapResHdr.get("DS_ACCT_NM")));
        tokenBiz.setAttribute4Lbl("CATEGORY / REASON");
        tokenBiz.setAttribute4(cutString((String) mapResHdr.get("CATG_RSN")));
        tokenBiz.setAttribute5Lbl("BUS UNIT / BRANCH");
        tokenBiz.setAttribute5(cutString((String) mapResHdr.get("BUS_UNIT_BR")));

        // ### Set Detail Attribute ###
        // Negotiated Deal
        tokenBiz.setDtlAttrb1(formatAmount((BigDecimal) mapResHdr.get("NEGO_DEAL_AMT")));

        // 2018/05/09 QC#20149 Add Start
        tokenBiz.setDtlAttrb17((String) mapResHdr.get("SELL_TO_CUST_CD"));
        tokenBiz.setDtlAttrb18((String) mapResHdr.get("DS_ACCT_NM"));
        // 2018/05/09 QC#20149 Add End

        // 2016/10/11 QC#14974 UPD START
        // Final Floor
        // tokenBiz.setDtlAttrb2(formatAmount(prftApiPMsg.totFuncFinalFlAmt.getValue()));
        tokenBiz.setDtlAttrb2(formatAmount((BigDecimal) mapOrdPrft.get("TOT_FUNC_FINAL_FL_AMT")));
        // Incentive Comp Sales Amount
        tokenBiz.setDtlAttrb3(formatAmount((BigDecimal) mapOrdPrft.get("TOT_FUNC_REP_REV_AMT")));
        // Incentive Comp GP$

        // 2016/10/11 QC#14974 DEL START
        // 2016/03/10 S21_NA#6292
        // if (prftApiPMsg.altGrsPrftPct.getValueInt() != 0) {
        // S21_NA#11152
        // // QC#9522
        // //tokenBiz.setAttribute2(cutString(String.valueOf(prftApiPMsg.funcAltGrsPrftAmt.getValue())));
        // tokenBiz.setAttribute2(cutString(String.valueOf(prftApiPMsg.altGrsPrftPct.getValue())));
        // tokenBiz.setDtlAttrb4(cutString(String.valueOf(prftApiPMsg.altGrsPrftPct.getValue())));
        // } else {
        // S21_NA#11152
        // // QC#9522
        // //tokenBiz.setDtlAttrb4(formatAmount(prftApiPMsg.funcGrsPrftAmt.getValue()));
        // tokenBiz.setDtlAttrb4(formatAmount(prftApiPMsg.grsPrftPct.getValue()));
        // tokenBiz.setDtlAttrb4(String.valueOf(prftApiPMsg.grsPrftPct.getValue()));
        // }
        // 2016/10/11 QC#14974 DEL E N D

        // set data
        tokenBiz.setOrdProcNodeCd(bean.getOrdProcNodeCd());
        tokenBiz.setOrdProcNodePrflCd(bean.getPrftdApvlNodePrflCd());

        // Set Condition Data
        tokenBiz.setBizId(wfId);
        tokenBiz.setCondStr1(bean.getOrdProcNodePrflNmPa());
        tokenBiz.setCondStr2((String) mapResHdr.get("DS_ORD_CATG_NM"));
        tokenBiz.setCondStr3((String) mapResHdr.get("DS_ORD_TP_NM"));
        tokenBiz.setCondStr4((String) mapResHdr.get("COA_EXTN_CD"));
        tokenBiz.setCondStr5((String) mapResHdr.get("COA_BR_CD"));

        // 2016/10/11 QC#14974 DEL START
        // 2016/03/10 S21_NA#6292
        // if (prftApiPMsg.altGrsPrftPct.getValueInt() != 0) {
        // tokenBiz.setCondNum1(prftApiPMsg.altGrsPrftPct.getValue());
        // } else {
        // tokenBiz.setCondNum1(prftApiPMsg.grsPrftPct.getValue());
        // }
        // 2016/10/11 QC#14974 DEL END

        // S21_NA#11179 modify start
        try {

            startWorkflow(msgMap, wfId, wfKey, tokenBiz, bean);

            printBizProcLog(msgMap, WF_OPEN_PROFITABILITY_HOLD, bean);
        } catch (S21NwfBizException e) {

            e.printStackTrace();
            if (hasValue(e.getMessageInfo().getCode()) && NWZC164001Constant.NWZM1982E.equals(e.getMessageInfo().getCode()) && S21StringUtil.isEquals(WF_PRFT_NO_PROC, tokenBiz.getCondStr9())) {

                // no process -> success
            } else {

                throw e;
            }
        }
        // S21_NA#11179 modify end

        // add start 2023/04/25 QC#61337
        if (manualPriceApprovalBean != null) {
            startManualPriceApprovalWorkflow(msgMap, manualPriceApprovalBean);
        }
        // add end 2023/04/25 QC#61337

        return true;
    }

    // 2017/04/13 S21_NA#18207 Restoration Start
    // 2016/10/11 QC#14974 DEL START
    private boolean callPrftCalcApi(S21ApiMessageMap msgMap, NWZC156001PMsg prftApiPMsg, Map<String, Object> mapResHdr, List<Map<String, Object>> mapResList) {

        NWZC164001PMsg param = (NWZC164001PMsg) msgMap.getPmsg();

        ZYPEZDItemValueSetter.setValue(prftApiPMsg.glblCmpyCd,      glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.slsDt,           param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.xxModeCd,        "02");
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.trxHdrNum,       param.cpoOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.dsOrdCatgCd,     (String) mapResHdr.get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.dsOrdTpCd,       (String) mapResHdr.get("DS_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.dsOrdRsnCd,      (String) mapResHdr.get("DS_ORD_RSN_CD"));
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.prcBaseDt,       (String) mapResHdr.get("PRC_BASE_DT"));
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.prcCalcDt,       (String) mapResHdr.get("PRC_CALC_DT"));
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.funcNegoDealAmt, (BigDecimal) mapResHdr.get("NEGO_DEAL_AMT"));
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.csmpContrNum,    (String) mapResHdr.get("CSMP_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.dlrRefNum,       (String) mapResHdr.get("DLR_REF_NUM"));
        ZYPEZDItemValueSetter.setValue(prftApiPMsg.dsAcctNum,       (String) mapResHdr.get("SELL_TO_CUST_CD"));

        NWZC156001_svcConfigRefPMsg svcConfigRefPMsg = null;
        int cnt = 0;
        for (Map<String, Object> mapResLine : mapResList) {
            svcConfigRefPMsg = prftApiPMsg.svcConfigRef.no(cnt++);
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.ordPrftTrxCatgCd, (String) mapResLine.get("CONFIG_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.trxLineNum, (String) mapResLine.get("TRX_LINE_NUM")); // 2016/03/17 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.trxLineSubNum, (String) mapResLine.get("TRX_LINE_SUB_NUM")); // 2016/03/17 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.dsOrdPosnNum, (String) mapResLine.get("DS_ORD_POSN_NUM")); // 2016/03/17 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.dsCpoLineNum, (BigDecimal) mapResLine.get("DS_CPO_LINE_NUM")); // 2016/03/17 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.dsCpoLineSubNum, (BigDecimal) mapResLine.get("DS_CPO_LINE_SUB_NUM")); // 2016/03/17 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.mdseCd, (String) mapResLine.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.rtlWhCd, (String) mapResLine.get("RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.rtlSwhCd, (String) mapResLine.get("RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.dsOrdLineCatgCd, (String) mapResLine.get("DS_ORD_LINE_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.ordQty, (BigDecimal) mapResLine.get("ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.custUomCd, (String) mapResLine.get("CUST_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.trxRefLineNum, (String) mapResLine.get("REF_CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.trxRefLineSubNum, (String) mapResLine.get("REF_CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.flPrcListCd, (String) mapResLine.get("FL_PRC_LIST_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.csmpPrcListCd, (String) mapResLine.get("CSMP_PRC_LIST_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.prcCatgCd, (String) mapResLine.get("PRC_CATG_CD")); // QC#7707
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.funcManFlAdjAmt, (BigDecimal) mapResLine.get("FUNC_MAN_FL_ADJ_AMT")); // 2016/03/17 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.funcUnitListPrcAmt, (BigDecimal) mapResLine.get("FUNC_PRC_LIST_PRC_AMT")); // 2016/03/17 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.funcNetUnitPrcAmt, (BigDecimal) mapResLine.get("FUNC_NET_UNIT_PRC_AMT")); // 2016/03/17 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.funcNetSellPrcAmt, (BigDecimal) mapResLine.get("FUNC_NET_AMT")); // 2016/03/17 S21_NA#2939
//            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.funcNetUnitPrcAmt, (BigDecimal) mapResLine.get("ENT_DEAL_NET_UNIT_PRC_AMT")); // 2016/03/17 S21_NA#2939
//            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.funcNetSellPrcAmt, (BigDecimal) mapResLine.get("ENT_CPO_DTL_DEAL_NET_AMT")); // 2016/03/17 S21_NA#2939

            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.funcSvcCostTrnsfAmt, (BigDecimal) mapResLine.get("FUNC_SVC_COST_TRNSF_AMT")); // 2016/03/17 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.funcManRepRevAdjAmt, (BigDecimal) mapResLine.get("FUNC_MAN_REP_REV_ADJ_AMT")); // 2016/03/17 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.funcSvcRevTrnsfAmt, (BigDecimal) mapResLine.get("FUNC_SVC_REV_TRNSF_AMT")); // 2016/03/17 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.cpoDtlFuncSlsAmt, (BigDecimal) mapResLine.get("CPO_DTL_FUNC_SLS_AMT")); // 2016/03/17 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.ordCustUomQty, (BigDecimal) mapResLine.get("ORD_CUST_UOM_QTY")); // 2016/03/17 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.csmpContrNum, (String) mapResLine.get("DTL_CSMP_CONTR_NUM")); // 2016/03/17 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.dlrRefNum, (String) mapResLine.get("DTL_DLR_REF_NUM")); // 2016/03/17 S21_NA#2939

            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.ordLineStsCd, (String) mapResLine.get("ORD_LINE_STS_CD"));
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.billToCustCd, (String) mapResLine.get("BILL_TO_CUST_CD")); // 2016/03/17 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.shipToCustCd, (String) mapResLine.get("SHIP_TO_CUST_CD"));

            ZYPEZDItemValueSetter.setValue(svcConfigRefPMsg.funcUnitStdCostAmt, (BigDecimal) mapResLine.get("THIS_MTH_TOT_STD_COST_AMT")); // 2016/03/17 S21_NA#2939
        }
        prftApiPMsg.svcConfigRef.setValidCount(cnt);

        List<NWZC156002PMsg> prftApiPMsg2List = new ArrayList<NWZC156002PMsg>();
        NWZC156001 prftCalcApi = new NWZC156001();
        prftCalcApi.execute(prftApiPMsg, prftApiPMsg2List, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(prftApiPMsg)) {
            for (int i = 0; i < prftApiPMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(prftApiPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return false;
        }
        for (NWZC156002PMsg prftApiPMsg2 : prftApiPMsg2List) {
            for (int i = 0; i < prftApiPMsg2.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(prftApiPMsg2.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return false;
        }
        return true;
    }
    // 2016/10/11 QC#14974 DEL E N D
    // 2017/04/13 S21_NA#18207 Restoration End

    // ***** start Supply Abuse Yield Approval Workflow *****
    private boolean startSuplyAbuseYieldApprovalWorkflow(S21ApiMessageMap msgMap, NWZC164001Bean bean, String wfKey) throws S21NwfException {

        String wfId  = getWorkflowId(NWZC164001Constant.CONST_KEY_SPLY_ABUSE_YIELD_APVL_WF_ID);

        // ### check already started ###
        if (isWorkflowStarted(msgMap, wfId, wfKey)) {
            msgMap.addXxMsgId(NWZC164001Constant.NWZM1754E);
            return false;
        }

        // ### get header data ###
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",  bean.getGlblCmpyCd());
        paramMap.put("cpoOrdNum",   bean.getCpoOrdNum());

        Map<String, Object> mapResHdr = getSsmQueryObject(msgMap, "getSplyAbuseYieldApvlHdrData", paramMap);
        if (mapResHdr == null) {
            return false;
        }

        // ### get line data ###
        paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",  bean.getGlblCmpyCd());
        paramMap.put("cpoOrdNum",   bean.getCpoOrdNum());

        List<Map<String, Object>> mapResList = getSsmQueryObjectList(msgMap, "getSplyAbuseYieldApvlLineData", paramMap);
        if (mapResList.isEmpty()) {
            return false;
        }

        NWZC164001TokenObject tokenBiz = new NWZC164001TokenObject();

        String cpoOrdNum = bean.getCpoOrdNum();
        String ordDt = formatDate((String) mapResHdr.get("CPO_ORD_TS"));
        String custCd = (String) mapResHdr.get("SELL_TO_CUST_CD");
        String custNm = (String) mapResHdr.get("DS_ACCT_NM");
        String catgRsn = (String) mapResHdr.get("CATG_RSN");
        // ### Set Attribute ###
        tokenBiz.setAttribute1Lbl("Order#");
        tokenBiz.setAttribute1(cutString(cpoOrdNum));
        tokenBiz.setAttribute2Lbl("Order Date");
        tokenBiz.setAttribute2(cutString(ordDt));
        tokenBiz.setAttribute3Lbl("Customer Number");
        tokenBiz.setAttribute3(cutString(custCd));
        tokenBiz.setAttribute4Lbl("Customer");
        tokenBiz.setAttribute4(cutString(custNm));
        tokenBiz.setAttribute5Lbl("CATEGORY / REASON");
        tokenBiz.setAttribute5(cutString(catgRsn));

        // ### Set Detail Attribute ###
        // Order Number
        tokenBiz.setDtlAttrb1(cpoOrdNum);
        // Order Date
        tokenBiz.setDtlAttrb2(ordDt);
        // Customer Number
        tokenBiz.setDtlAttrb3(custCd);
        // Customer
        tokenBiz.setDtlAttrb4(custNm);
        // Pretax Order Amount
        tokenBiz.setDtlAttrb5(formatAmount((BigDecimal) mapResHdr.get("ENT_CPO_TOT_DEAL_NET_AMT")));
        // CATEGORY / REASON
        tokenBiz.setDtlAttrb6(catgRsn);
        // Order Status
        tokenBiz.setDtlAttrb7((String) mapResHdr.get("ORD_HDR_STS_NM"));
        // Ship Service Level
        tokenBiz.setDtlAttrb8((String) mapResHdr.get("SHPG_SVC_LVL_NM"));
        // Carrier Service Level
        tokenBiz.setDtlAttrb9((String) mapResHdr.get("CARR_SVC_LVL_NM"));
        // Ship to
        tokenBiz.setDtlAttrb10(getAddShipToAddr(mapResHdr));

        // 2018/05/09 QC#20149 Add Start
        tokenBiz.setDtlAttrb17((String) mapResHdr.get("SELL_TO_CUST_CD"));
        tokenBiz.setDtlAttrb18((String) mapResHdr.get("DS_ACCT_NM"));
        // 2018/05/09 QC#20149 Add End
        
        // ### Set Line Attribute ###
        for (Map<String, Object> mapRes : mapResList) {
            NWZC164001TokenObjectLine lineData = new NWZC164001TokenObjectLine();
            // Line#
            lineData.setLineAttrb1((String) mapRes.get("LINE_NUM"));
            // Merchandise
            lineData.setLineAttrb2((String) mapRes.get("MDSE"));
            tokenBiz.addLineData(lineData);
        }

        // set data
        tokenBiz.setOrdProcNodeCd(bean.getOrdProcNodeCd());
        tokenBiz.setOrdProcNodeNm(bean.getOrdProcNodeNm());
        tokenBiz.setOrdProcNodePrflCd(bean.getSplyAbuseNodePrflCd());

        // start workflow
        startWorkflow(msgMap, wfId, wfKey, tokenBiz, bean);
        printBizProcLog(msgMap, WF_OPEN_SUPPLY_YIELD_HOLD, bean);

        return true;
    }


    // ***** start Supply Abuse Pending Order Approval Workflow *****
    private boolean startSuplyAbusePendingOrderApprovalWorkflow(S21ApiMessageMap msgMap, NWZC164001Bean bean, String wfKey) throws S21NwfException {

        String wfId  = getWorkflowId(NWZC164001Constant.CONST_KEY_SPLY_ABUS_PND_ORD_APVL_WF_ID);

        // ### check already started ###
        if (isWorkflowStarted(msgMap, wfId, wfKey)) {
            msgMap.addXxMsgId(NWZC164001Constant.NWZM1754E);
            return false;
        }

        // ### get header data ###
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",  bean.getGlblCmpyCd());
        paramMap.put("cpoOrdNum",   bean.getCpoOrdNum());

        Map<String, Object> mapResHdr = getSsmQueryObject(msgMap, "getSplyAbusePndOrdApvlHdrData", paramMap);
        if (mapResHdr == null) {
            return false;
        }

        // ### get line data ###
        paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",      bean.getGlblCmpyCd());
        paramMap.put("cpoOrdNum",       bean.getCpoOrdNum());
        paramMap.put("ordCatgCtxTp",    ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);

        List<Map<String, Object>> mapResList = getSsmQueryObjectList(msgMap, "getSplyAbusePndOrdApvlLineData", paramMap);
        if (mapResList.isEmpty()) {
            return false;
        }

        NWZC164001TokenObject tokenBiz = new NWZC164001TokenObject();

        String cpoOrdNum = bean.getCpoOrdNum();
        String ordDt = formatDate((String) mapResHdr.get("CPO_ORD_TS"));
        String custCd = (String) mapResHdr.get("SELL_TO_CUST_CD");
        String custNm = (String) mapResHdr.get("DS_ACCT_NM");
        String catgRsn = (String) mapResHdr.get("CATG_RSN");
        // ### Set Attribute ###
        tokenBiz.setAttribute1Lbl("Order#");
        tokenBiz.setAttribute1(cutString(cpoOrdNum));
        tokenBiz.setAttribute2Lbl("Order Date");
        tokenBiz.setAttribute2(cutString(ordDt));
        tokenBiz.setAttribute3Lbl("Customer Number");
        tokenBiz.setAttribute3(cutString(custCd));
        tokenBiz.setAttribute4Lbl("Customer");
        tokenBiz.setAttribute4(cutString(custNm));
        tokenBiz.setAttribute5Lbl("CATEGORY / REASON");
        tokenBiz.setAttribute5(cutString(catgRsn));

        // ### Set Detail Attribute ###
        // Order Number
        tokenBiz.setDtlAttrb1(cpoOrdNum);
        // Order Date
        tokenBiz.setDtlAttrb2(ordDt);
        // Pretax Order Amount
        tokenBiz.setDtlAttrb3(formatAmount((BigDecimal) mapResHdr.get("ENT_CPO_TOT_DEAL_NET_AMT")));
        // CATEGORY / REASON
        tokenBiz.setDtlAttrb4(catgRsn);
        // Order Status
        tokenBiz.setDtlAttrb5((String) mapResHdr.get("ORD_HDR_STS_NM"));
        // Ship Service Level
        tokenBiz.setDtlAttrb6((String) mapResHdr.get("SHPG_SVC_LVL_NM"));
        // Carrier Service Level
        tokenBiz.setDtlAttrb7((String) mapResHdr.get("CARR_SVC_LVL_NM"));
        // Customer Number
        tokenBiz.setDtlAttrb8(custCd);
        // Customer
        tokenBiz.setDtlAttrb9(custNm);
        // ship to
        tokenBiz.setDtlAttrb10(getAddShipToAddr(mapResHdr));

        // 2018/05/09 QC#20149 Add Start
        tokenBiz.setDtlAttrb17(custCd);
        tokenBiz.setDtlAttrb18(custNm);
        // 2018/05/09 QC#20149 Add End

        // ### Set Line Attribute ###
        for (Map<String, Object> mapRes : mapResList) {
            NWZC164001TokenObjectLine lineData = new NWZC164001TokenObjectLine();
            // Serial
            lineData.setLineAttrb1((String) mapRes.get("SER_NUM"));
            // Duplication Order#
            lineData.setLineAttrb2((String) mapRes.get("CPO_ORD_NUM"));
            // Duplication Line#
            lineData.setLineAttrb3((String) mapRes.get("LINE_NUM"));
            // Inventory Item
            lineData.setLineAttrb4((String) mapRes.get("MDSE"));
            tokenBiz.addLineData(lineData);
        }

        // set data
        tokenBiz.setOrdProcNodeCd(bean.getOrdProcNodeCd());
        tokenBiz.setOrdProcNodeNm(bean.getOrdProcNodeNm());
        tokenBiz.setOrdProcNodePrflCd(bean.getSplyAbuseNodePrflCd());

        // start workflow
        startWorkflow(msgMap, wfId, wfKey, tokenBiz, bean);
        printBizProcLog(msgMap, WF_OPEN_PENDING_ORDER_HOLD, bean);

        return true;
    }

    // ***** start Supply Abuse Contract Status Approval Workflow *****
    private boolean startSuplyAbuseContractStatusApprovalWorkflow(S21ApiMessageMap msgMap, NWZC164001Bean bean, String wfKey) throws S21NwfException {

        String wfId  = getWorkflowId(NWZC164001Constant.CONST_KEY_SPLY_ABUS_CONTR_STS_APVL_WF_ID);

        // ### check already started ###
        if (isWorkflowStarted(msgMap, wfId, wfKey)) {
            msgMap.addXxMsgId(NWZC164001Constant.NWZM1754E);
            return false;
        }

        // ### get header data ###
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",  bean.getGlblCmpyCd());
        paramMap.put("cpoOrdNum",   bean.getCpoOrdNum());

        Map<String, Object> mapResHdr = getSsmQueryObject(msgMap, "getSplyAbuseContrStsApvlHdrData", paramMap);
        if (mapResHdr == null) {
            return false;
        }

        // ### get line data ###
        paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",  bean.getGlblCmpyCd());
        paramMap.put("cpoOrdNum",   bean.getCpoOrdNum());

        List<Map<String, Object>> mapResList = getSsmQueryObjectList(msgMap, "getSplyAbuseContrStsApvlLineData", paramMap);
        if (mapResList.isEmpty()) {
            return false;
        }

        NWZC164001TokenObject tokenBiz = new NWZC164001TokenObject();
        String cpoOrdNum = bean.getCpoOrdNum();
        String ordDt = formatDate((String) mapResHdr.get("CPO_ORD_TS"));
        String ordPreTaxAmt = formatAmount((BigDecimal) mapResHdr.get("ENT_CPO_TOT_DEAL_NET_AMT"));
        String custNm = (String) mapResHdr.get("DS_ACCT_NM");
        String crLimitAmt = formatAmount((BigDecimal) mapResHdr.get("CR_LIMIT_AMT"));
        // ### Set Attribute ###
        tokenBiz.setAttribute1Lbl("Order#");
        tokenBiz.setAttribute1(cutString(cpoOrdNum));
        tokenBiz.setAttribute2Lbl("Order Date");
        tokenBiz.setAttribute2(cutString(ordDt));
        tokenBiz.setAttribute3Lbl("Order Pre Tax Amount");
        tokenBiz.setAttribute3(cutString(ordPreTaxAmt));
        tokenBiz.setAttribute4Lbl("Customer");
        tokenBiz.setAttribute4(cutString(custNm));
        tokenBiz.setAttribute5Lbl("Customer Credit Limit Exceeds");
        tokenBiz.setAttribute5(cutString(crLimitAmt));

        // ### Set Detail Attribute ###
        // Order Number
        tokenBiz.setDtlAttrb1(cpoOrdNum);
        // Order Date
        tokenBiz.setDtlAttrb2(ordDt);
        // Order Pre Tax Amount
        tokenBiz.setDtlAttrb3(ordPreTaxAmt);
        // Customer
        tokenBiz.setDtlAttrb4(custNm);
        // Customer Number
        tokenBiz.setDtlAttrb5((String) mapResHdr.get("SELL_TO_CUST_CD"));
        // Customer Credit Limit Exceeds$
        tokenBiz.setDtlAttrb6(crLimitAmt);

        // 2018/05/09 QC#20149 Add Start
        tokenBiz.setDtlAttrb17((String) mapResHdr.get("SELL_TO_CUST_CD"));
        tokenBiz.setDtlAttrb18(custNm);
        // 2018/05/09 QC#20149 Add End

        // ### Set Line Attribute ###
        for (Map<String, Object> mapRes : mapResList) {
            NWZC164001TokenObjectLine lineData = new NWZC164001TokenObjectLine();
            // Serial
            lineData.setLineAttrb1((String) mapRes.get("SER_NUM"));
            // Contract Number
            lineData.setLineAttrb2((String) mapRes.get("DS_CONTR_NUM"));
            // Line Status
            lineData.setLineAttrb3((String) mapRes.get("DS_CONTR_DTL_STS_NM"));
            tokenBiz.addLineData(lineData);
        }

        // set data
        tokenBiz.setOrdProcNodeCd(bean.getOrdProcNodeCd());
        tokenBiz.setOrdProcNodeNm(bean.getOrdProcNodeNm());
        tokenBiz.setOrdProcNodePrflCd(bean.getSplyAbuseNodePrflCd());

        // start workflow
        startWorkflow(msgMap, wfId, wfKey, tokenBiz, bean);
        printBizProcLog(msgMap, WF_OPEN_CONTRACT_STATUS_HOLD, bean);

        return true;
    }

    // ***** start Supply Abuse Enforcement Hold Approval Workflow *****
    private boolean startSuplyAbuseEnforcementHoldApprovalWorkflow(S21ApiMessageMap msgMap, NWZC164001Bean bean, String wfKey) throws S21NwfException {

        String wfId  = getWorkflowId(NWZC164001Constant.CONST_KEY_SPLY_ABUSE_ENF_HLD_APVL_WF_ID);

        // ### check already started ###
        if (isWorkflowStarted(msgMap, wfId, wfKey)) {
            msgMap.addXxMsgId(NWZC164001Constant.NWZM1754E);
            return false;
        }

        // ### get header data ###
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",  bean.getGlblCmpyCd());
        paramMap.put("cpoOrdNum",   bean.getCpoOrdNum());

        Map<String, Object> mapResHdr = getSsmQueryObject(msgMap, "getSplyAbuseSuplyEnfApvlHdrData", paramMap);
        if (mapResHdr == null) {
            return false;
        }

        // ### get line data ###
        paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",  bean.getGlblCmpyCd());
        paramMap.put("cpoOrdNum",   bean.getCpoOrdNum());

        Map<String, Object> mapResLine = getSsmQueryObject(msgMap, "getSplyAbuseSuplyEnfApvlLineData", paramMap);
        if (mapResLine == null) {
            return false;
        }

        // START 2018/09/25 [QC#28318,ADD]
        BigDecimal dsContrPk = (BigDecimal) mapResLine.get("DS_CONTR_PK");
        BigDecimal totalTonersQty = BigDecimal.ZERO;

        // Term & Condition
        Map<String, Object> stcaResult = setCapTonerUpperLimit(bean.getGlblCmpyCd(), dsContrPk, bean.getSlsDt());
        // Cap - Total Toner Original
        if (stcaResult.get(TERM_COND_CAP_TOT_ORG_ATTRB_NM) != null) {
            totalTonersQty = (BigDecimal) stcaResult.get(TERM_COND_CAP_TOT_ORG_ATTRB_NM);
        } else {
            // Cap - B/W Toner Original
            if (stcaResult.get(TERM_COND_CAP_BW_ORG_ATTRB_NM) != null) {
                totalTonersQty = (BigDecimal) stcaResult.get(TERM_COND_CAP_BW_ORG_ATTRB_NM);
            }
            // Cap - Color Toner Original
            if (stcaResult.get(TERM_COND_CAP_CLR_ORG_ATTRB_NM) != null) {
                totalTonersQty = totalTonersQty.add((BigDecimal) stcaResult.get(TERM_COND_CAP_CLR_ORG_ATTRB_NM));
            }
        }
        // END 2018/09/25 [QC#28318,ADD]

        NWZC164001TokenObject tokenBiz = new NWZC164001TokenObject();
        String cpoOrdNum = bean.getCpoOrdNum();
        String ordDt = formatDate((String) mapResHdr.get("CPO_ORD_TS"));
        String custCd = (String) mapResHdr.get("SELL_TO_CUST_CD");
        String custNm = (String) mapResHdr.get("DS_ACCT_NM");
        String catgRsn = (String) mapResHdr.get("CATG_RSN");
        String ordAmt = formatAmount((BigDecimal) mapResHdr.get("ENT_CPO_TOT_DEAL_NET_AMT"));
        // START 2018/09/25 [QC#28318,MOD]
        // BigDecimal tonersQty = (BigDecimal) mapResLine.get("TOTAL_TONERS_QTY");
        // BigDecimal allowedQty = (BigDecimal) mapResLine.get("TOTAL_ALLOWED_QTY");
        BigDecimal tonersQty = totalTonersQty;
        BigDecimal allowedQty = totalTonersQty.subtract((BigDecimal) mapResLine.get("TOTAL_USED_QTY"));
        // END 2018/09/25 [QC#28318,MOD]
        BigDecimal usedQty = (BigDecimal) mapResLine.get("TOTAL_USED_QTY");

        // ### Set Attribute ###
        tokenBiz.setAttribute1Lbl("Order#");
        tokenBiz.setAttribute1(cutString(cpoOrdNum));
        tokenBiz.setAttribute2Lbl("Customer");
        tokenBiz.setAttribute2(cutString(custNm));
        tokenBiz.setAttribute3Lbl("Total Toners");
        tokenBiz.setAttribute3(cutString(tonersQty.toString()));
        tokenBiz.setAttribute4Lbl("Total Allowed");
        tokenBiz.setAttribute4(cutString(allowedQty.toString()));
        tokenBiz.setAttribute5Lbl("Total Used");
        tokenBiz.setAttribute5(cutString(usedQty.toString()));

        // Order Number
        tokenBiz.setDtlAttrb1(cpoOrdNum);
        // Order Date
        tokenBiz.setDtlAttrb2(ordDt);
        // Customer Number
        tokenBiz.setDtlAttrb3(custCd);
        // Customer
        tokenBiz.setDtlAttrb4(custNm);
        // Total Toners
        tokenBiz.setDtlAttrb5(tonersQty.toString());
        // Total Allowed
        tokenBiz.setDtlAttrb6(allowedQty.toString());
        // Total Used
        tokenBiz.setDtlAttrb7(usedQty.toString());
        // Pretax Order Amount
        tokenBiz.setDtlAttrb8(ordAmt);
        // CATEGORY / REASON
        tokenBiz.setDtlAttrb9(catgRsn);
        // Order Status
        tokenBiz.setDtlAttrb10((String) mapResHdr.get("ORD_HDR_STS_NM"));
        // Ship Service Level
        tokenBiz.setDtlAttrb11((String) mapResHdr.get("SHPG_SVC_LVL_NM"));
        // Carrier Service Level
        tokenBiz.setDtlAttrb12((String) mapResHdr.get("CARR_SVC_LVL_NM"));
        // ship to
        tokenBiz.setDtlAttrb13(getAddShipToAddr(mapResHdr));

        // 2018/05/09 QC#20149 Add Start
        tokenBiz.setDtlAttrb17(custCd);
        tokenBiz.setDtlAttrb18(custNm);
        // 2018/05/09 QC#20149 Add End

        // set data
        tokenBiz.setOrdProcNodeCd(bean.getOrdProcNodeCd());
        tokenBiz.setOrdProcNodeNm(bean.getOrdProcNodeNm());
        tokenBiz.setOrdProcNodePrflCd(bean.getSplyAbuseNodePrflCd());

        // start workflow
        startWorkflow(msgMap, wfId, wfKey, tokenBiz, bean);
        printBizProcLog(msgMap, WF_OPEN_SUPPLY_ENFORCEMENT_HOLD, bean);

        return true;
    }

    // ***** start Credit Approval Workflow *****
    private boolean startCreditApprovalWorkflow(S21ApiMessageMap msgMap, NWZC164001Bean bean) throws S21NwfException {

        String wfId  = getWorkflowId(NWZC164001Constant.CONST_KEY_CR_APVL_WF_ID);
        String wfKey = createWfKey(bean.getCpoOrdNum(), bean.getOrdProcNodeCd(), bean.getCrApvlNodePrflCd(), bean.getHldRsnCd());

        NWZC164001PMsg param = (NWZC164001PMsg) msgMap.getPmsg();

        // ### check already started ###
        if (isWorkflowStarted(msgMap, wfId, wfKey)) {
            msgMap.addXxMsgId(NWZC164001Constant.NWZM1754E);
            return false;
        }

        // ### get header data ###
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",  glblCmpyCd);
        paramMap.put("cpoOrdNum",   bean.getCpoOrdNum());
        paramMap.put("rgtnStsCd",   RGTN_STS.READY_FOR_ORDER_TAKING);

        Map<String, Object> mapResHdr = getSsmQueryObject(msgMap, "getCreditApvlHdrData", paramMap);
        if (mapResHdr == null) {
            return false;
        }

        // ### get AR data ###
        paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",              glblCmpyCd);
        paramMap.put("slsDt",                   param.slsDt.getValue());
        paramMap.put("arCashApplyStsUnapplied", AR_CASH_APPLY_STS.UNAPPLIED);
        paramMap.put("arCashApplyStsPartial",   AR_CASH_APPLY_STS.PARTIAL);
        // paramMap.put("billToCustCd",            (String) mapResHdr.get("BILL_TO_CUST_CD")); // Del 2017/09/29 S21_NA#21081
        paramMap.put("arTrxTpRcp",              AR_TRX_TP.RECEIPT); // Add 2017/09/29 S21_NA#21081
        paramMap.put("billToCustAcctCd",        (String) mapResHdr.get("DS_ACCT_NUM"));
        paramMap.put("arTrxTpCrm",              AR_TRX_TP.CREDIT_MEMO); // Add 2018/02/06 S21_NA#23654
        // START 2021/10/19 F.Deola [QC#59060,DEL]
//        paramMap.put("gracePerDaysAot",        (BigDecimal) mapResHdr.get("GRACE_PER_DAYS_AOT")); // Add 2018/02/06 S21_NA#23654
        // END 2021/10/19 F.Deola [QC#59060,DEL]
        // START 2019/01/15 H.Ikeda [QC#29913,ADD]
        paramMap.put("arTrxTpInv",              AR_TRX_TP.INVOICE);
        paramMap.put("arTrxTpDed",              AR_TRX_TP.DEDUCTION);
        // END   2019/01/15 H.Ikeda [QC#29913,ADD]
        Map<String, Object> mapResAr = getSsmQueryObject(msgMap, "getCreditApvlArData", paramMap);
        if (mapResAr == null) {
            return false;
        }

        String cpoOrdNum = bean.getCpoOrdNum();
        String catgRsn = (String) mapResHdr.get("CATG_RSN");
        String busUnitBr = (String) mapResHdr.get("BUS_UNIT_BR");
        String custNm = (String) mapResHdr.get("LOC_NM");
        //QC13477
//        BigDecimal ordTotAmt = (BigDecimal) mapResHdr.get("ENT_CPO_TOT_DEAL_NET_AMT");
        // 2017/01/13 S21_NA#16995 Mod Start
        // BigDecimal ordTotAmt = (BigDecimal) mapResHdr.get("LINE_DEAL_SUB_TOT_AMT");
        BigDecimal ordTotAmt = (BigDecimal) mapResHdr.get("ORD_TOT_DEAL_SUB_TOT_AMT");
        // 2017/01/13 S21_NA#16995 Mod End

        NWZC164001TokenObject tokenBiz = new NWZC164001TokenObject();

        // ### Set Attribute ###
        tokenBiz.setAttribute1Lbl("Order#");
        tokenBiz.setAttribute1(cutString(bean.getCpoOrdNum()));
        tokenBiz.setAttribute2Lbl("CATEGORY / REASON");
        tokenBiz.setAttribute2(cutString(catgRsn));
        tokenBiz.setAttribute3Lbl("BUS UNIT / BRANCH");
        tokenBiz.setAttribute3(cutString(busUnitBr));
        tokenBiz.setAttribute4Lbl("Customer Name");
        // START 2018/01/12 [QC#21078, MOD]
        // tokenBiz.setAttribute4(cutString(custNm));
        tokenBiz.setAttribute4(cutString((String) mapResHdr.get("DS_ACCT_NUM") + " " + custNm));
        // END   2018/01/12 [QC#21078, MOD]
        tokenBiz.setAttribute5Lbl("Order Total");
        tokenBiz.setAttribute5(cutString(formatAmount(ordTotAmt)));

        // ### Set Detail Attribute ###
        // Order Number
        tokenBiz.setDtlAttrb1(cpoOrdNum);
        // Order Date
        tokenBiz.setDtlAttrb2(formatDateTime((String) mapResHdr.get("CPO_ORD_TS")));
        // Customer Name
        tokenBiz.setDtlAttrb3(custNm);
        // Customer Number
        tokenBiz.setDtlAttrb4((String) mapResHdr.get("DS_ACCT_NUM"));
        // Credit Limit
        BigDecimal crLimitAmt = (BigDecimal) mapResHdr.get("CR_LIMIT_AMT");
        tokenBiz.setDtlAttrb5(formatAmount(crLimitAmt));
        // Account Open AR
        tokenBiz.setDtlAttrb6(formatAmount((BigDecimal) mapResAr.get("ACCT_OPEN_AR_AMT")));
        // Account Past Due by
        // START 2018/07/24 Y.Matsui [QC#26798,MOD]
        tokenBiz.setDtlAttrb7(String.valueOf(mapResAr.get("ACCT_PAST_DUE_BY")));
        // END   2018/07/24 Y.Matsui [QC#26798,MOD]
        // Account Past Due Amount
        tokenBiz.setDtlAttrb8(formatAmount((BigDecimal) mapResAr.get("ACCT_PAST_DUE_AMT")));
        // Contract Grace Period
        // 2017/08/04 S21_NA#15606 Add Start
        BigDecimal gracePerDaysAot = (BigDecimal) mapResHdr.get("GRACE_PER_DAYS_AOT");
        if (gracePerDaysAot == null) {
            gracePerDaysAot = BigDecimal.ZERO;
        }
        tokenBiz.setDtlAttrb9(String.valueOf(gracePerDaysAot) + " days");
        String contrNum = (String) mapResHdr.get("CONTR_NUM");
        tokenBiz.setDtlAttrb10(contrNum);

        // 2018/02/27 S21_NA#23994 Mod Start
        // 2018/01/24 S21_NA#22095 Add Start
        // Hard Hold Flag
        tokenBiz.setDtlAttrb11((String) mapResHdr.get("CUST_HARD_HLD_FLG"));
        // Account Status
        tokenBiz.setDtlAttrb12((String) mapResHdr.get("DS_CLT_ACCT_STS_DESC_TXT"));
        // Order Amount
        tokenBiz.setDtlAttrb13(formatAmount((BigDecimal) mapResHdr.get("LINE_DEAL_SUB_TOT_AMT")));
        // Order Sub Total
        tokenBiz.setDtlAttrb14(formatAmount((BigDecimal) mapResHdr.get("LINE_DEAL_NET_AMT")));
        // Order Tax
        tokenBiz.setDtlAttrb15(formatAmount((BigDecimal) mapResHdr.get("LINE_DEAL_TAX_AMT")));
        // 2018/02/27 S21_NA#23994 Mod End
        // Contract Grace Period
        BigDecimal contrGracePerDaysAot = (BigDecimal) mapResHdr.get("CONTR_GRACE_PER_DAYS_AOT");
        if (contrGracePerDaysAot == null) {
            contrGracePerDaysAot = BigDecimal.ZERO;
        }
        tokenBiz.setDtlAttrb16(String.valueOf(contrGracePerDaysAot) + " days");
        // 2018/01/24 S21_NA#22095 Add End

        // 2018/05/09 QC#20149 Add Start
        tokenBiz.setDtlAttrb17((String) mapResHdr.get("DS_ACCT_NUM"));
        tokenBiz.setDtlAttrb18(custNm);
        // 2018/05/09 QC#20149 Add End

        // START 2018/07/24 Y.Matsui [QC#26798,ADD]
        String dsOrdCatgCd = (String) mapResHdr.get("DS_ORD_CATG_CD");
        String dsOrdTpCd = (String) mapResHdr.get("DS_ORD_TP_CD");
        String billToCustCd = (String) mapResHdr.get("BILL_TO_CUST_CD");
        String billToCustAcctCd = (String) mapResHdr.get("BILL_TO_CUST_ACCT_CD");
        tokenBiz.setDtlAttrb19(getPoReqFlg(dsOrdCatgCd, dsOrdTpCd, billToCustAcctCd, billToCustCd));
        tokenBiz.setDtlAttrb20((String) mapResHdr.get("CUST_ISS_PO_NUM"));
        tokenBiz.setDtlAttrb21((String) mapResHdr.get("PMT_TERM_CASH_DISC_DESC_TXT_O"));
        tokenBiz.setDtlAttrb22((String) mapResHdr.get("PMT_TERM_CASH_DISC_DESC_TXT_A"));
        // END   2018/07/24 Y.Matsui [QC#26798,ADD]

        // 2017/08/04 S21_NA#15606 Add End
        // S21_NA#10321 Mod Start
        // String contrVrsnEffThruDt = (String) mapResHdr.get("CONTR_VRSN_EFF_THRU_DT");
        // 2017/08/04 S21_NA#15606 Del Start
//        String contrInfo = (String) mapResHdr.get("CONTR_INFO");
//        int index = contrInfo.indexOf(",");
//        String contrVrsnEffThruDt = contrInfo.substring(index + 1);
//        // S21_NA#10321 Mod End
//        if (contrVrsnEffThruDt.equals(NWZC164001Constant.MAX_EFF_THRU_DT)) {
//            tokenBiz.setDtlAttrb9(contrVrsnEffThruDt + " days");
//        } else {
//            int contrGracePeriod = ZYPDateUtil.getDiffDays(contrVrsnEffThruDt, param.slsDt.getValue());
//            tokenBiz.setDtlAttrb9(String.valueOf(contrGracePeriod) + " days");
//        }
//        // Contract Number
//        // S21_NA#10321 Mod Start
//        // tokenBiz.setDtlAttrb10((String) mapResHdr.get("CONTR_NUM"));
//        String contrNum = contrInfo.substring(0, index);
//        if (!hasValue(contrNum)) {
//            contrNum = null;
//        }
//        tokenBiz.setDtlAttrb10(contrNum);
        // 2017/08/04 S21_NA#15606 Del End
        // S21_NA#10321 Mod End

        // set data
        tokenBiz.setOrdProcNodeCd(bean.getOrdProcNodeCd());
        tokenBiz.setOrdProcNodeNm(bean.getOrdProcNodeNm());
        tokenBiz.setOrdProcNodePrflCd(bean.getCrApvlNodePrflCd());
        tokenBiz.setBillToCustCd((String) mapResHdr.get("BILL_TO_CUST_CD"));
        tokenBiz.setBillToCustAcctCd((String) mapResHdr.get("DS_ACCT_NUM"));
        //QC#7899
        //tokenBiz.setCrLimitAmt(crLimitAmt);
        tokenBiz.setOrdTotAmt(ordTotAmt);

        // Set Condition Data
        tokenBiz.setBizId(wfId);
        tokenBiz.setCondStr1(wfId);
        // S21_NA#12452 modify start
        // if ("40".equals(tokenBiz.getOrdProcNodeCd())) {
        if ("40".equals(tokenBiz.getOrdProcNodePrflCd())) {
            // S21_NA#12452 modify end
            tokenBiz.setOrdProcNodePrflEquipFlg(ZYPConstant.FLG_ON_Y);
            // START 2019/02/05 E.Kameishi [QC#30163,ADD]
            tokenBiz.setStyle01(STYLE_01);
            // END 2019/02/05 E.Kameishi [QC#30163,ADD]
        } else {
            tokenBiz.setOrdProcNodePrflEquipFlg(ZYPConstant.FLG_OFF_N);
        }
        // START 2019/02/05 E.Kameishi [QC#30163,ADD]
        tokenBiz.setDtlAttrb23(getCreditHoldName(bean));
        // END 2019/02/05 E.Kameishi [QC#30163,ADD]

        // start workflow
        startWorkflow(msgMap, wfId, wfKey, tokenBiz, bean);
        printBizProcLog(msgMap, WF_OPEN_CREDIT_APPROVAL_HOLD, bean);

        return true;
    }

    // ***** start Buyout Approval Workflow *****
    private boolean startBuyoutApprovalWorkflow(S21ApiMessageMap msgMap, List<NWZC164001Bean> beanList) throws S21NwfException {

        String wfId  = getWorkflowId(NWZC164001Constant.CONST_KEY_BYOT_APVL_WF_ID);

        for (NWZC164001Bean bean : beanList) {
            String wfKey = createWfKey(bean.getCpoOrdNum(), bean.getCpoDtlLineNum(), bean.getCpoDtlLineSubNum(), bean.getOrdProcNodeCd(), bean.getHldRsnCd());

            // ### check already started ###
            if (isWorkflowStarted(msgMap, wfId, wfKey)) {
                // set information message
                // S21_NA#13013 Del
//                msgMap.addXxMsgId(NWZC164001Constant.NWZM1755I);
                continue;
            }

            // START 2018/08/27 [QC#27766, ADD]
            if (checkWorkflowStarted(msgMap, wfId, wfKey)) {
                continue;
            }
            // END   2018/08/27 [QC#27766, ADD]

            // ### aggrigate buyout amount
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", bean.getGlblCmpyCd());
            paramMap.put("cpoOrdNum",  bean.getCpoOrdNum());
            paramMap.put("hldRsnCd",   bean.getHldRsnCd());
            BigDecimal chkRqstTotAmt = (BigDecimal) ssmClient.queryObject("aggrigateBuyoutAmount", paramMap);

            // ### get header data ###
            paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd",        bean.getGlblCmpyCd());
            paramMap.put("cpoOrdNum",         bean.getCpoOrdNum());
            paramMap.put("cpoDtlLineNum",     bean.getCpoDtlLineNum());
            paramMap.put("cpoDtlLineSubNum",  bean.getCpoDtlLineSubNum());

            Map<String, Object> mapResHdr = getSsmQueryObject(msgMap, "getByotApvlHdrData", paramMap);
            if (mapResHdr == null) {
                return false;
            }

            NWZC164001TokenObject tokenBiz = new NWZC164001TokenObject();
            String cpoOrdNum = bean.getCpoOrdNum();
            String ordDt = formatDate((String) mapResHdr.get("CPO_ORD_TS"));
            String zoneBranch = (String) mapResHdr.get("ZONE_BRANCH");
            String catgRsn = (String) mapResHdr.get("CATG_RSN");
            String totAmt = formatAmount(chkRqstTotAmt);
            String ordLineAmt = formatAmount((BigDecimal) mapResHdr.get("ENT_CPO_DTL_DEAL_NET_AMT"));
            String custNm = (String) mapResHdr.get("DS_ACCT_NM");

            // ### Set Attribute ###
            tokenBiz.setAttribute1Lbl("Order#");
            tokenBiz.setAttribute1(cutString(cpoOrdNum));
            //S21_NA#2524 Modify START
            tokenBiz.setAttribute2Lbl("ZONE / BRANCH");
            tokenBiz.setAttribute2(cutString(zoneBranch));
            tokenBiz.setAttribute3Lbl("CATEGORY / REASON");
            tokenBiz.setAttribute3(cutString(catgRsn));
            //S21_NA#2524 Modify END
            tokenBiz.setAttribute4Lbl("Check Request Total Amount");
            tokenBiz.setAttribute4(cutString(totAmt));
            tokenBiz.setAttribute5Lbl("Customer");
            tokenBiz.setAttribute5(cutString(custNm));

            // ### Set Detail Attribute ###
            // Order Number
            tokenBiz.setDtlAttrb1(cpoOrdNum);
            // Order Date
            tokenBiz.setDtlAttrb2(ordDt);
            //S21_NA#2524 Add START
            // ZONE / BRANCH
            tokenBiz.setDtlAttrb3(zoneBranch);
            // CATEGORY / REASON
            tokenBiz.setDtlAttrb4(catgRsn);
            //S21_NA#2524 Add END
            // Check Request Total Amount
            tokenBiz.setDtlAttrb5(totAmt);
            // Specific Check Request Order Line#
            tokenBiz.setDtlAttrb6((String) mapResHdr.get("LINE_NUM"));
            // Specific Check Request Order Amount$
            tokenBiz.setDtlAttrb7(ordLineAmt);
            // Customer
            tokenBiz.setDtlAttrb8(custNm);

            //header
            tokenBiz.setDtlAttrb10(catgRsn);
            tokenBiz.setDtlAttrb11((String) mapResHdr.get("BUS_UNIT_BR"));
            tokenBiz.setDtlAttrb12(custNm);
            tokenBiz.setDtlAttrb13(ordLineAmt);

            // 2018/05/09 QC#20149 Add Start
            tokenBiz.setDtlAttrb17((String) mapResHdr.get("SELL_TO_CUST_CD"));
            tokenBiz.setDtlAttrb18(custNm);
            // 2018/05/09 QC#20149 Add End

            // ### Set Line Attribute ###
            for (NWZC164001Bean beanLine : beanList) {
                NWZC164001TokenObjectLine lineData = new NWZC164001TokenObjectLine();

                // ### get line data ###
                paramMap = new HashMap<String, Object>();
                paramMap.put("glblCmpyCd",          beanLine.getGlblCmpyCd());
                paramMap.put("cpoOrdNum",           beanLine.getCpoOrdNum());
                paramMap.put("cpoDtlLineNum",       beanLine.getCpoDtlLineNum());
                paramMap.put("cpoDtlLineSubNum",    beanLine.getCpoDtlLineSubNum());
                paramMap.put("hldRsnCd",            beanLine.getHldRsnCd());
                paramMap.put("relFlgOff",           ZYPConstant.FLG_OFF_N);
                Map<String, Object> mapResLine = getSsmQueryObject(msgMap, "getByotApvlLineData", paramMap);
                if (mapResLine.isEmpty()) {
                    return false;
                }

                // Line#
                lineData.setLineAttrb1((String) mapResLine.get("LINE_NUM"));
                // ITEM CODE
                lineData.setLineAttrb2((String) mapResLine.get("MDSE_CD"));
                // ITEM DESCRIPTION
                lineData.setLineAttrb3((String) mapResLine.get("MDSE_NM"));
                // Line Amount
                lineData.setLineAttrb4(formatAmount((BigDecimal) mapResLine.get("ENT_CPO_DTL_DEAL_NET_AMT")));
                tokenBiz.addLineData(lineData);
            }

            // set data
            tokenBiz.setCpoDtlLineNum(bean.getCpoDtlLineNum());
            tokenBiz.setCpoDtlLineSubNum(bean.getCpoDtlLineSubNum());
            tokenBiz.setOrdProcNodeCd(bean.getOrdProcNodeCd());
            tokenBiz.setOrdProcNodeNm(bean.getOrdProcNodeNm());
            tokenBiz.setOrdProcNodePrflCd(bean.getByotNodePrflCd());

            // Set Condition Data
            tokenBiz.setBizId(wfId);
            tokenBiz.setCondStr1((String) mapResHdr.get("MDSE_CD"));
            tokenBiz.setCondStr2((String) mapResHdr.get("ORG_NM"));
            tokenBiz.setCondNum1((BigDecimal) mapResHdr.get("ENT_CPO_DTL_DEAL_NET_AMT"));

            // start workflow
            startWorkflow(msgMap, wfId, wfKey, tokenBiz, bean);
            printBizProcLog(msgMap, WF_OPEN_BUYOUT_APPROVAL_HOLD, bean);
        }

        return true;
    }

    private String getAddShipToAddr(Map<String, Object> mapRes) {

        // QC#13106 Mod Start
        // StringBuffer sb = new StringBuffer();
        StringBuilder sb = new StringBuilder();
        // QC#13106 Mod End
        String shipToLocNm = (String) mapRes.get("ADD_SHIP_TO_LOC_NM");
        String firstLineAddr = (String) mapRes.get("ADD_SHIP_TO_FIRST_LINE_ADDR");
        String scdLineAddr = (String) mapRes.get("ADD_SHIP_TO_SCD_LINE_ADDR");
        String thrdLineAddr = (String) mapRes.get("ADD_SHIP_TO_THIRD_LINE_ADDR");
        String frthLineAddr = (String) mapRes.get("ADD_SHIP_TO_FRTH_LINE_ADDR");
        String ctySt = (String) mapRes.get("ADD_SHIP_TO_CTY_ST");
        String post = (String) mapRes.get("ADD_SHIP_TO_POST_CD");
        if (hasValue(shipToLocNm) && shipToLocNm.length() > 0) {
            sb.append(shipToLocNm).append("<BR>");
        }
        if (hasValue(firstLineAddr) && firstLineAddr.length() > 0) {
            sb.append(firstLineAddr).append("<BR>");
        }
        if (hasValue(scdLineAddr) && scdLineAddr.length() > 0) {
            sb.append(scdLineAddr).append("<BR>");
        }
        if (hasValue(thrdLineAddr) && thrdLineAddr.length() > 0) {
            sb.append(thrdLineAddr).append("<BR>");
        }
        if (hasValue(frthLineAddr) && frthLineAddr.length() > 0) {
            sb.append(frthLineAddr).append("<BR>");
        }
        if (hasValue(ctySt) && ctySt.length() > 0) {
            sb.append(ctySt).append("<BR>");
        }
        if (hasValue(post) && post.length() > 0) {
            sb.append(post).append("<BR>");
        }

        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getSsmQueryObject(S21ApiMessageMap msgMap, String sqlId, Map<String, Object> paramMap) {

        Map<String, Object> mapRes = (Map<String, Object>) ssmClient.queryObject(sqlId, paramMap);

        if (mapRes == null) {
            // System Error
            throw new S21AbendException(NWZC164001Constant.NWZM0474E);
        }

        return mapRes;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSsmQueryObjectList(S21ApiMessageMap msgMap, String sqlId, Map<String, Object> paramMap) {

        List<Map<String, Object>> mapResList = (List<Map<String, Object>>) ssmClient.queryObjectList(sqlId, paramMap);

        if (mapResList.isEmpty()) {
            // System Error
            throw new S21AbendException(NWZC164001Constant.NWZM0474E);
        }
        return mapResList;
    }

    // 2016/06/02 S21_NA#7968 Add Start
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSsmQueryObjectListWithOutException(S21ApiMessageMap msgMap, String sqlId, Map<String, Object> paramMap) {

        List<Map<String, Object>> mapResList = (List<Map<String, Object>>) ssmClient.queryObjectList(sqlId, paramMap);

        if (null == mapResList) {
            mapResList = new ArrayList<Map<String, Object>>(0);
        }
        return mapResList;
    }
    // 2016/06/02 S21_NA#7968 Add End

    private boolean isWorkflowStarted(S21ApiMessageMap msgMap, String wfId, String wfKey) {
        // START 2018/07/27 [QC#26876, DEL]
//        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
//        S21NwfContext context = null;
//
//        try {
//            context = factory.getContex();
//
//            List<S21NwfProcess>  processes = context.getProcess(wfId, wfKey);
//            for (S21NwfProcess p : processes) {
//                if (p.isActive()) {
//                    return true;
//                }
//            }
//        } catch (S21NwfSystemException e) {
//            // System Error
//            throw new S21AbendException(NWZC164001Constant.NWZM0474E);
//        }
        // END   2018/07/27 [QC#26876, DEL]
        return false;
    }

    // START 2018/08/27 [QC#27766, ADD]
    private boolean checkWorkflowStarted(S21ApiMessageMap msgMap, String wfId, String wfKey) {
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;

        try {
            context = factory.getContex();

            List<S21NwfProcess>  processes = context.getProcess(wfId, wfKey);
            for (S21NwfProcess p : processes) {
                if (p.isActive()) {
                    return true;
                }
            }
        } catch (S21NwfSystemException e) {
            // System Error
            throw new S21AbendException(NWZC164001Constant.NWZM0474E);
        }
        return false;
    }
    // END   2018/08/27 [QC#27766, ADD]

    private void startWorkflow(S21ApiMessageMap msgMap, String wfId, String wfKey, NWZC164001TokenObject tokenBiz, NWZC164001Bean bean) throws S21NwfException {

        NWZC164001PMsg param = (NWZC164001PMsg) msgMap.getPmsg();

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        S21NwfProcess process = null;

        try {
            context = factory.getContex();
            // START 2018/07/27 [QC#26876, MOD]
//            process = context.newProcess(wfId);
//            process.setDocumentId(wfKey);
            process = context.getProcessNoEnd(wfId, wfKey);
            // END   2018/07/27 [QC#26876, MOD]
            process.setGroup(bean.getCpoOrdNum());
        } catch (S21NwfException e) {
            // System Error
            // add start 2023/04/25 QC#61337
            if (existPrfApvlManPrcApvl(param, wfId)) {
                return;
            }
            // add end 2023/04/25 QC#61337
            throw e;
        }

        S21NwfTokenImpl token = (S21NwfTokenImpl) process.getToken();
        token.setTokenObj(tokenBiz);

        tokenBiz.setGlblCmpyCd(glblCmpyCd);
        tokenBiz.setCpoOrdNum(param.cpoOrdNum.getValue());
        tokenBiz.setSlsDt(param.slsDt.getValue());
        tokenBiz.setHldPk(bean.getHldPk());
        tokenBiz.setHldRsnCd(bean.getHldRsnCd());
        tokenBiz.setDocId(wfKey);
        tokenBiz.setBizScreenId(NWZC164001Constant.CONST_ORDER_ENTRY_SCREEN_ID);
        tokenBiz.setBizScreenParams(param.cpoOrdNum);
        // START 2018/01/12 [QC#21078, ADD]
        if (CREDIT_APPROVAL.equals(bean.getOrdProcNodeCd())) {
            tokenBiz.setBizScreenIdSbj4(NWZC164001Constant.CONST_COLLECTION_HEADER_SCREEN_ID);
            tokenBiz.setBizScreenParamsSbj4(tokenBiz.getBillToCustAcctCd());
        }
        // END   2018/01/12 [QC#21078, ADD]

        try {
            token.start();
        } catch (S21NwfException e) {
            // System Error
            throw e;
        }

    }

    private String getWorkflowId(String constKey) {

        String wfId = ZYPCodeDataUtil.getVarCharConstValue(constKey, glblCmpyCd);

        if (wfId == null) {
            throw new S21AbendException("[Error]Not Found 'VAR_CHAR_CONST' : varCharConstNm=" + constKey);
        }

        return wfId;
    }

    private String formatDate(String timestampStr) {
        if (!hasValue(timestampStr)) {
            return timestampStr;
        }
        String dateStr = timestampStr.substring(0, 8);
        return ZYPDateUtil.formatEzd8ToDisp(dateStr);
    }

    private String formatDateTime(String timestampStr) {

        if (!hasValue(timestampStr)) {
            return timestampStr;

        } else if (timestampStr.length() == 8) {
            return formatDate(timestampStr);
        }

        String dateStr = timestampStr.substring(0, 14);

        return ZYPDateUtil.formatEzd14ToDisp(dateStr);
    }

    private String formatAmount(BigDecimal amt) {

        if (!hasValue(amt)) {
            return null;
        }

        NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);

        return df.format(amt);
    }

    private String createWfKey(Object... keys) {

        StringBuilder sb = new StringBuilder();
        for (Object o : keys) {
            if (o != null) {
                sb.append(o);
            }
        }
        return sb.toString().intern();
    }

    private void writeStartLog(String methodNm) {
        writeDebugLog("[START]NWZC16401_" + methodNm + "-------");
    }

    private void writeEndLog(String methodNm) {
        writeDebugLog("[END]NWZC16401_" + methodNm + "-------");
    }
    private void writeDebugLog(String str) {
        logger.debug("[Order Header Workflow Control API] " + str);
    }

    private String cutString(String val) {
        if (!hasValue(val)) {
            return val;
        }
        if (attributeCutLength == null) {
            return val;
        }
        if (val.length() <= attributeCutLength.intValue()) {
            return val;
        }
        return val.substring(0, attributeCutLength.intValue());
    }

    // START 2018/07/24 Y.Matsui [QC#26798,ADD]
    private String getPoReqFlg(String dsOrdCatgCd, String dsOrdTpCd, String billToCustAcctCd, String billToCustCd) {
        String dsTrxRuleTpCd = getDsTrxRuleTpCd(dsOrdCatgCd, dsOrdTpCd);
        if (!ZYPCommonFunc.hasValue(dsTrxRuleTpCd)) {
            return ZYPConstant.FLG_OFF_N;
        }

        String localPoReqFlg = getLocalPoReqFlg(billToCustCd, dsTrxRuleTpCd);
        if (ZYPCommonFunc.hasValue(localPoReqFlg)) {
            return localPoReqFlg;
        }

        String accountPoReqFlg = getAccountPoReqFlg(billToCustAcctCd, dsTrxRuleTpCd);
        if (ZYPCommonFunc.hasValue(accountPoReqFlg)) {
            return accountPoReqFlg;
        }
        return ZYPConstant.FLG_OFF_N;
    }

    private String getDsTrxRuleTpCd(String dsOrdCatgCd, String dsOrdTpCd) {

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("003");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);
        condition.setConditionValue("dsOrdTpCd01", dsOrdTpCd);

        ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) S21ApiTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);

        tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) S21ApiTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        return null;
    }

    private String getLocalPoReqFlg(String billToCustCd, String dsTrxRuleTpCd) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("billToCustCd", billToCustCd);
        ssmParam.put("dsTrxRuleTpCd", dsTrxRuleTpCd);
        return (String) ssmClient.queryObject("getLocalPoReqFlg", ssmParam);
    }

    private String getAccountPoReqFlg(String billToCustAcctCd, String dsTrxRuleTpCd) {

        DS_CUST_TRX_RULETMsg condition = new DS_CUST_TRX_RULETMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("dsAcctNum01", billToCustAcctCd);
        condition.setConditionValue("dsTrxRuleTpCd01", dsTrxRuleTpCd);

        DS_CUST_TRX_RULETMsgArray tmsgArray = (DS_CUST_TRX_RULETMsgArray) S21ApiTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).dsPoReqFlg.getValue();
        }

        return null;
    }
    // END   2018/07/24 Y.Matsui [QC#26798,ADD]

    // START 2018/09/25 [QC#28318,ADD]
    private Map<String, Object> setCapTonerUpperLimit(String glblCmpyCd, BigDecimal dsContrPk, String slsDt) {

        String[] tonerOriginalNm = new String[TERM_COND_CAP_LENGTH];
        // Cap - Cap - Total Toner Original
        tonerOriginalNm[0] = TERM_COND_CAP_TOT_ORG_ATTRB_NM;
        // Cap - B/W Toner Original
        tonerOriginalNm[1] = TERM_COND_CAP_BW_ORG_ATTRB_NM;
        // Cap - Color Toner Original
        tonerOriginalNm[2] = TERM_COND_CAP_CLR_ORG_ATTRB_NM;

        Map<String, Object> termRunAttrbNm = new HashMap<String, Object>();
        for (int i = 0; i < tonerOriginalNm.length; i++) {
            String capOriginalAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(tonerOriginalNm[i], glblCmpyCd);
            // SVC_TERM_COND_ATTRB
            Map<String, Object> stcaInParam = new HashMap<String, Object>();
            stcaInParam.put("glblCmpyCd", glblCmpyCd);
            stcaInParam.put("capBwOrgAttrbNm", capOriginalAttrbNm);
            stcaInParam.put("salseDate", slsDt);

            BigDecimal svcTermCondAttrbPk = (BigDecimal) ssmClient.queryObject("geSvcTermCondAttrbInfo", stcaInParam);
            if (svcTermCondAttrbPk == null) {
                continue;
            }

            // SVC_TERM_COND
            Map<String, Object> stcInParam = new HashMap<String, Object>();
            stcInParam.put("glblCmpyCd", glblCmpyCd);
            stcInParam.put("dsContrPk", dsContrPk);
            stcInParam.put("svcTermCondAttrbPk", svcTermCondAttrbPk);

            // Mach Lv
            BigDecimal stcResult = (BigDecimal) ssmClient.queryObject("getSvcTermCondInfoForMach", stcInParam);
            if (stcResult == null) {
                // Contr Lv
                stcResult = (BigDecimal) ssmClient.queryObject("getSvcTermCondInfoForContr", stcInParam);
            }

            if (stcResult == null) {
                continue;
            }

            if (i == 0) {
                termRunAttrbNm.put(TERM_COND_CAP_TOT_ORG_ATTRB_NM, stcResult);
            } else if (i == 1) {
                termRunAttrbNm.put(TERM_COND_CAP_BW_ORG_ATTRB_NM, stcResult);
            } else {
                termRunAttrbNm.put(TERM_COND_CAP_CLR_ORG_ATTRB_NM, stcResult);
            }
        }
        return termRunAttrbNm;
    }
    // END 2018/09/25 [QC#28318,ADD]

    // START 2019/02/05 E.Kameishi [QC#30163,ADD]
    @SuppressWarnings("unchecked")
    private String getCreditHoldName(NWZC164001Bean bean) {

        // Set SQL Parameters
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",      bean.getGlblCmpyCd());
        paramMap.put("cpoOrdNum",       bean.getCpoOrdNum());
        paramMap.put("slsDt",           bean.getSlsDt());
        paramMap.put("relFlg",          ZYPConstant.FLG_OFF_N);
        paramMap.put("hldApplyAvalFlg", ZYPConstant.FLG_ON_Y);
        paramMap.put("creditHld",       HLD_TP.CREDIT_HOLD);
        paramMap.put("creditApproval",  CREDIT_APPROVAL);

        List<Map<String, Object>> mapResList = (List<Map<String, Object>>) ssmClient.queryObjectList("getCreditHoldRsn", paramMap);

        String hldRsnCrPrfl = null;

        for (Map<String, Object> mapRes : mapResList) {
            String hldRsnCd = (String) mapRes.get("HLD_RSN_CD");
            if (HLD_RSN.CREDIT_PROFILE.equals(hldRsnCd)) {
                hldRsnCrPrfl = (String) mapRes.get("HLD_RSN_NM");
            } else {
                return (String) mapRes.get("HLD_RSN_NM");
            }
        }
        return hldRsnCrPrfl;
    }
    // END 2019/02/05 E.Kameishi [QC#30163,ADD]

    // add start 2023/04/25 QC#61337
    private NWZC164001Bean copyBean(NWZC164001Bean bean) {

        NWZC164001Bean targetBean = new NWZC164001Bean();
        targetBean.setGlblCmpyCd(bean.getGlblCmpyCd());
        targetBean.setCpoOrdNum(bean.getCpoOrdNum());
        targetBean.setHldPk(bean.getHldPk());
        targetBean.setHldRsnCd(bean.getHldRsnCd());
        targetBean.setHldDt(bean.getHldDt());
        targetBean.setHldQty(bean.getHldQty());
        targetBean.setCpoOrdNum(bean.getCpoOrdNum());
        targetBean.setCpoDtlLineNum(bean.getCpoDtlLineNum());
        targetBean.setCpoDtlLineSubNum(bean.getCpoDtlLineSubNum());
        targetBean.setShpgPlnNum(bean.getShpgPlnNum());
        targetBean.setOrdProcNodeCd(bean.getOrdProcNodeCd());
        targetBean.setOrdProcNodeNm(bean.getOrdProcNodeNm());
        targetBean.setVldApvlNodePrflCd(bean.getVldApvlNodePrflCd());
        targetBean.setCrApvlNodePrflCd(bean.getCrApvlNodePrflCd());
        targetBean.setPrftdApvlNodePrflCd(bean.getPrftdApvlNodePrflCd());
        targetBean.setSplyAbuseNodePrflCd(bean.getSplyAbuseNodePrflCd());
        targetBean.setOrdProcNodePrflNmVa(bean.getOrdProcNodePrflNmVa());
        targetBean.setOrdProcNodePrflNmCa(bean.getOrdProcNodePrflNmCa());
        targetBean.setOrdProcNodePrflNmPa(bean.getOrdProcNodePrflNmPa());
        targetBean.setOrdProcNodePrflNmSa(bean.getOrdProcNodePrflNmSa());
        targetBean.setOrdProcNodePrflNmBa(bean.getOrdProcNodePrflNmBa());
        targetBean.setOrdProcNodeNm(bean.getOrdProcNodeNm());
        targetBean.setHldRsnNm(bean.getHldRsnNm());
        targetBean.setSlsDt(bean.getSlsDt());
        targetBean.setManPrcNodePrflCd(bean.getManPrcNodePrflCd());
        targetBean.setOrdProcNodePrflNmMa(bean.getOrdProcNodePrflNmMa());

        return targetBean;
    }

    private boolean startManualPriceApprovalWorkflow(S21ApiMessageMap msgMap, NWZC164001Bean bean) throws S21NwfException {

        String wfId = getWorkflowId(CONST_KEY_MAN_PRC_APVL_WF_ID);
        String wfKey = createWfKey(bean.getCpoOrdNum(), bean.getOrdProcNodeCd(), bean.getManPrcNodePrflCd(), bean.getHldRsnCd());

        // ### check already started ###
        if (isWorkflowStarted(msgMap, wfId, wfKey)) {
            msgMap.addXxMsgId(NWZC164001Constant.NWZM1754E);
            return false;
        }

        // ### get header data ###
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", bean.getGlblCmpyCd());
        paramMap.put("cpoOrdNum", bean.getCpoOrdNum());

        Map<String, Object> hdrMap = getSsmQueryObject(msgMap, "getManPrcApvlHdrData", paramMap);
        if (hdrMap == null) {
            return false;
        }

        // ### get line data ###
        paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", bean.getGlblCmpyCd());
        paramMap.put("cpoOrdNum", bean.getCpoOrdNum());
        paramMap.put("prcCondTpManualPrice", PRC_COND_TP.MANUAL_PRICE);
        paramMap.put("prcDtlGrpBasePrice", PRC_DTL_GRP.BASE_PRICE);
        List<String> prcDtlGrpCdList = new ArrayList<String>();
        prcDtlGrpCdList.add(PRC_DTL_GRP.FREIGHT);
        prcDtlGrpCdList.add(PRC_DTL_GRP.HANDLING_FEE);
        prcDtlGrpCdList.add(PRC_DTL_GRP.FUEL_SURCHARGE);
        prcDtlGrpCdList.add(PRC_DTL_GRP.SHIPPING_FEE);
        prcDtlGrpCdList.add(PRC_DTL_GRP.RESTOCKING_FEE);
        prcDtlGrpCdList.add(PRC_DTL_GRP.ROUNDING_FACTOR_1);
        prcDtlGrpCdList.add(PRC_DTL_GRP.ROUNDING_FACTOR_2);
        paramMap.put("prcDtlGrpCdList", prcDtlGrpCdList);

        List<Map<String, Object>> lineMapList = getSsmQueryObjectList(msgMap, "getManPrcApvlLineData", paramMap);
        if (lineMapList.isEmpty()) {
            return false;
        }

        // Call Pricing API
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        if (!callPricingApi(msgMap, prcApiPMsg, hdrMap, lineMapList)) {
            return false;
        }

        NWZC164001TokenObject tokenBiz = new NWZC164001TokenObject();

        String cpoOrdNum = bean.getCpoOrdNum();
        String totAmt = formatAmountToStr((BigDecimal) hdrMap.get("ORD_TOT_DEAL_SUB_TOT_AMT"));
        String dsAcctNm = (String) hdrMap.get("DS_ACCT_NM");
        String catgRsn = (String) hdrMap.get("CATG_RSN");
        String busUnitBr = (String) hdrMap.get("BUS_UNIT_BR");

        // ### Set Attribute ###
        tokenBiz.setAttribute1Lbl("Order#");
        tokenBiz.setAttribute1(cutString(cpoOrdNum));
        tokenBiz.setAttribute2Lbl("Total $");
        tokenBiz.setAttribute2(cutString(totAmt));
        tokenBiz.setAttribute3Lbl("SOLD TO");
        tokenBiz.setAttribute3(cutString(dsAcctNm));
        tokenBiz.setAttribute4Lbl("CATEGORY / REASON");
        tokenBiz.setAttribute4(cutString(catgRsn));
        tokenBiz.setAttribute5Lbl("BUS UNIT / BRANCH");
        tokenBiz.setAttribute5(cutString(busUnitBr));

        // ### Set Detail Attribute ###
        // ORDER#
        tokenBiz.setDtlAttrb1(cpoOrdNum);
        // Total $
        tokenBiz.setDtlAttrb2(totAmt);
        // SOLD TO
        tokenBiz.setDtlAttrb3(dsAcctNm);
        // CATEGORY/REASON
        tokenBiz.setDtlAttrb4(catgRsn);
        // BUS UNIT/BRANCH:
        tokenBiz.setDtlAttrb5(busUnitBr);

        tokenBiz.setDtlAttrb17((String) hdrMap.get("SELL_TO_CUST_CD"));
        tokenBiz.setDtlAttrb18((String) hdrMap.get("DS_ACCT_NM"));

        // ### Set Line Attribute ###
        // Line Price
        for (Map<String, Object> lineMap : lineMapList) {

            if (ZYPConstant.FLG_OFF_N.equals((String) lineMap.get("MAN_PRC_CHNG_FLG"))) {
                continue;
            }

            String cpoDtlLineNum = (String) lineMap.get("CPO_DTL_LINE_NUM");
            String cpoDtlLineSubNum = (String) lineMap.get("CPO_DTL_LINE_SUB_NUM");
            BigDecimal manualLineAmt = (BigDecimal) lineMap.get("CPO_DTL_DEAL_NET_AMT");
            BigDecimal originalLineAmt = BigDecimal.ZERO;

            for (int i = 0; i < prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
                NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(i);
                if (cpoDtlLineNum.equals(prcTotalPMsg.trxLineNum.getValue()) && cpoDtlLineSubNum.equals(prcTotalPMsg.trxLineSubNum.getValue())) {
                    originalLineAmt = prcTotalPMsg.xxTotNetPrcAmt.getValue();
                    break;
                }
            }
            NWZC164001TokenObjectLine lineData = new NWZC164001TokenObjectLine();
            // Line#
            lineData.setLineAttrb1((String) lineMap.get("DPLY_LINE_NUM"));
            // Item#
            lineData.setLineAttrb2((String) lineMap.get("MDSE_CD"));
            // Decription
            lineData.setLineAttrb3((String) lineMap.get("MDSE_NM"));
            // Original
            lineData.setLineAttrb4(formatAmount(originalLineAmt));
            // Manual
            lineData.setLineAttrb5(formatAmount(manualLineAmt));
            // Variance
            lineData.setLineAttrb6(formatAmount(manualLineAmt.subtract(originalLineAmt)));
            tokenBiz.addLineData(lineData);
        }

        // Charges
        if (ZYPConstant.FLG_ON_Y.equals((String) lineMapList.get(0).get("MAN_CHRG_CHNG_FLG"))) {
            BigDecimal manualChrgAmt = (BigDecimal) hdrMap.get("ORD_TOT_DEAL_CHRG_AMT");
            BigDecimal originalChrAmt = BigDecimal.ZERO;
            for (int i = 0; i < prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
                NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(i);
                originalChrAmt = originalChrAmt.add(prcTotalPMsg.xxTotFrtAmt.getValue());
            }

            NWZC164001TokenObjectLine lineData = new NWZC164001TokenObjectLine();
            // Line#
            lineData.setLineAttrb1("Charges");
            // Item#
            lineData.setLineAttrb2("");
            // Decription
            lineData.setLineAttrb3("");
            // Original
            lineData.setLineAttrb4(formatAmount(originalChrAmt));
            // Manual
            lineData.setLineAttrb5(formatAmount(manualChrgAmt));
            // Variance
            lineData.setLineAttrb6(formatAmount(manualChrgAmt.subtract(originalChrAmt)));
            tokenBiz.addLineData(lineData);
        }

        // set data
        tokenBiz.setOrdProcNodeCd(bean.getOrdProcNodeCd());
        tokenBiz.setOrdProcNodeNm(bean.getOrdProcNodeNm());
        tokenBiz.setOrdProcNodePrflCd(bean.getManPrcNodePrflCd());

        // Set Condition Data
        tokenBiz.setBizId(wfId);
        tokenBiz.setCondStr1(bean.getOrdProcNodePrflNmMa());
        tokenBiz.setCondStr2((String) hdrMap.get("DS_ORD_CATG_NM"));
        tokenBiz.setCondStr3((String) hdrMap.get("DS_ORD_TP_NM"));

        // START 2023/09/13 S.Fujita [QC#61783,ADD]
        paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", bean.getGlblCmpyCd());
        paramMap.put("cpoOrdNum", bean.getCpoOrdNum());
        paramMap.put("ordSrcSupplyQuote", CPO_SRC_TP.SUPPLY_QUOTE_ENTRY);
        Map<String, Object> rtnMap = (Map<String, Object>) ssmClient.queryObject("getManPrcApvlTxtData", paramMap);
        if (rtnMap != null) {
            tokenBiz.setComment((String) rtnMap.get("SPLY_QUOTE_CMNT_TXT"));
        }
        // END 2023/09/13 S.Fujita [QC#61783,ADD]

        startWorkflow(msgMap, wfId, wfKey, tokenBiz, bean);
        printBizProcLog(msgMap, WF_OPEN_MAN_PRC_APVL, bean);

        return true;
    }

    private boolean existPrfApvlManPrcApvl(NWZC164001PMsg pMsg, String wfId) {

        boolean isPrfApvlWf = S21StringUtil.isEquals(getWorkflowId(NWZC164001Constant.CONST_KEY_PRFT_APVL_WF_ID), wfId);
        boolean isManPrcApvlWf = S21StringUtil.isEquals(getWorkflowId(CONST_KEY_MAN_PRC_APVL_WF_ID), wfId);
        if (!isPrfApvlWf && !isManPrcApvlWf) {
            return false;
        }

        HLDTMsg condition = new HLDTMsg();
        condition.setSQLID("016");
        condition.setMaxCount(1);
        condition.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        condition.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());
        condition.setConditionValue("relFlg01", ZYPConstant.FLG_OFF_N);
        if (isPrfApvlWf) {
            condition.setConditionValue("hldRsnCd01", HLD_RSN.PROFITABILITY_HOLD);
        } else {
            condition.setConditionValue("hldRsnCd01", HLD_RSN.MANUAL_PRICE);
        }
        return S21ApiTBLAccessor.count(condition) > 0;
    }

    private String formatAmountToStr(BigDecimal amt) {
        if (!hasValue(amt)) {
            return null;
        }
        amt = amt.setScale(2, BigDecimal.ROUND_HALF_UP);
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(amt);
    }

    private boolean callPricingApi(S21ApiMessageMap msgMap, NWZC157001PMsg prcApiPMsg, Map<String, Object> hdrMap, List<Map<String, Object>> lineMapList) {

        NWZC164001PMsg param = (NWZC164001PMsg) msgMap.getPmsg();

        // Header
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_ORDER_ALL);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, param.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, (String) hdrMap.get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, (String) hdrMap.get("DS_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, (String) hdrMap.get("LINE_BIZ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, (String) hdrMap.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.cpoSrcTpCd, (String) hdrMap.get("CPO_SRC_TP_CD"));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.custIssPoNum, (String) hdrMap.get("CUST_ISS_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsPmtMethCd, (String) hdrMap.get("DS_PMT_METH_CD"));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.spclHdlgTpCd, (String) hdrMap.get("SPCL_HDLG_TP_CD"));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);

        // Line
        int cnt = 0;
        for (Map<String, Object> lineMap : lineMapList) {
            NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(cnt++);

            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsOrdPosnNum, (String) lineMap.get("DS_ORD_POSN_NUM"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineNum, (String) lineMap.get("CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineSubNum, (String) lineMap.get("CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcBaseDt, (String) lineMap.get("PRC_BASE_DT"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.billToCustCd, (String) lineMap.get("BILL_TO_CUST_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shipToCustCd, (String) lineMap.get("SHIP_TO_CUST_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_SH, (String) lineMap.get("SHIP_TO_CUST_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_BL, (String) lineMap.get("BILL_TO_CUST_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCatgCd, (String) lineMap.get("PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaBrCd, (String) hdrMap.get("COA_BR_CD"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ccyCd, (String) lineMap.get("CCY_CD"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdseCd, (String) lineMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.pkgUomCd, (String) lineMap.get("CUST_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsOrdLineCatgCd, (String) lineMap.get("DS_ORD_LINE_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordQty, (BigDecimal) lineMap.get("ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordCustUomQty, (BigDecimal) lineMap.get("ORD_CUST_UOM_QTY"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctyAddr_SH, (String) lineMap.get("SHIP_TO_CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.stCd_SH, (String) lineMap.get("SHIP_TO_ST_CD"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctryCd_SH, (String) lineMap.get("SHIP_TO_CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.postCd_SH, (String) lineMap.get("SHIP_TO_POST_CD"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtCondCd, (String) lineMap.get("FRT_COND_CD"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shpgSvcLvlCd, (String) lineMap.get("SHPG_SVC_LVL_CD"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtChrgToCd, (String) lineMap.get("FRT_CHRG_TO_CD"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtChrgMethCd, (String) lineMap.get("FRT_CHRG_METH_CD"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
        }
        prcApiPMsg.NWZC157002PMsg.setValidCount(cnt);

        // call NWZC1570 Pricing API
        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            for (int i = 0; i < prcApiPMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(prcApiPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return false;
        }
        boolean hasErr = false;
        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            if (S21ApiUtil.isXxMsgId(prcApiPMsg.NWZC157002PMsg.no(i))) {
                for (int j = 0; j < prcApiPMsg.xxMsgIdList.getValidCount(); j++) {
                    msgMap.addXxMsgId(prcApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue());
                    hasErr = true;
                }
            }
        }
        if (hasErr) {
            return false;
        }
        return true;
    }
    // add end 2023/04/25 QC#61337
}
