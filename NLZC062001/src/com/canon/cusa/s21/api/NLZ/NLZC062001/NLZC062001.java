/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC062001;

import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.ADJ_VAR_SGN_CD_MINUS;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.ADJ_VAR_SGN_CD_PLUS;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.APPROVE;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.BIND_PARAM_ADJ_VAR_FLG;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.BIND_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.BIND_PARAM_INVTY_LOC_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.BIND_PARAM_LOC_STS_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.BIND_PARAM_MDSE_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.BIND_PARAM_PHYS_INVTY_ADJ_NM;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.BIND_PARAM_PHYS_INVTY_ADJ_STS_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.BIND_PARAM_PHYS_INVTY_NUM;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.BIND_PARAM_STK_STS_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.CANCEL;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.COL_NM_LOC_TP_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.COL_NM_PHYS_INVTY_NUM;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.COMMENT_PREFIX;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.COMMENT_SUBSTR_INDEX;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.CREATE_CLOSE;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DATE_TIME_PATTERN;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DATE_TIME_PATTERN_FOR_MAIL;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DB_RESULT_COL_ADJ_VAR_QTY;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DB_RESULT_COL_MDSE_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DB_RESULT_COL_MSTR_MDSE_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DB_RESULT_COL_PHYS_INVTY_CNT_DTL_PK;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DB_RESULT_COL_PHYS_INVTY_CNT_HDR_PK;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DB_RESULT_COL_PHYS_INVTY_CTRL_PK;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DB_RESULT_COL_PHYS_INVTY_STS_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DB_RESULT_COL_PHYS_INVTY_STS_NM;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DB_RESULT_COL_RCV_SER_TAKE_FLG;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DB_RESULT_COL_RTL_WH_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DB_RESULT_COL_SER_NUM;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DB_RESULT_COL_STK_STS_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DB_RESULT_COL_SUB_TOT_ADJ_VAR_AMT;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DB_RESULT_COL_SUB_TOT_ADJ_VAR_QTY;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DB_RESULT_COL_SVC_CONFIG_MSTR_PK;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DB_RESULT_COL_TOT_ADJ_VAR_GRS_AMT;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DB_RESULT_COL_TOT_ADJ_VAR_NET_AMT;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DB_RESULT_COL_WH_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.DETAIL;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.HEADER;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.LINE_MAX_COUNT;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.MESSAGE_KIND_ERROR;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.NLCM0049E;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.NLCM0163E;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.NLCM0165E;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.NLCM0168E;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.NLCM0169E;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.NLGM0078E;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.NLZM2453E;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.NLZM2454E;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.NLZM2455E;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.NLZM2456E;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.NLZM2457E;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.NLZM2458E;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.NLZM2459W;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.NLZM2460E;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.NLZM2461E;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.NPZM0212E;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.PI_ADJ_NM_PREFIX;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.REJECT;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.RTRN_CD_NORMAL;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.SERIAL_MAX_COUNT;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.STOCK_IN;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.STOCK_OUT;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.TBL_ID_PHYS_INVTY_CTRL;
import static com.canon.cusa.s21.api.NLZ.NLZC062001.constant.NLZC062001Constant.BIND_PARAM_PHYS_INVTY_CTRL_PK;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.PHYS_INVTY_ADJTMsg;
import business.db.PHYS_INVTY_CNT_DTLTMsg;
import business.db.PHYS_INVTY_CNT_HDRTMsg;
import business.db.PHYS_INVTY_CTRLTMsg;
import business.parts.NLZC004001PMsg;
import business.parts.NLZC063001PMsg;
import business.parts.NLZC403001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC004001.NLZC004001;
import com.canon.cusa.s21.api.NLZ.NLZC061001.NLZC061001TokenObject;
import com.canon.cusa.s21.api.NLZ.NLZC063001.NLZC063001;
import com.canon.cusa.s21.api.NLZ.NLZC403001.NLZC403001;
import com.canon.cusa.s21.api.NLZ.NLZC403001.constant.NLZC403001Constant;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreateApprovalHistory;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreateApprovalHistoryBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_ADJ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_CNT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.MailDefinition;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.util.S21NwfDateUtil;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfApproveEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfProcessEndEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfReassignEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfRejectEvent;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;

/**
 * <pre>
 * Tech-PI Approval from WF API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/16/2016   CITS            K.Ogino         Create          N/A
 * 03/25/2016   CITS            K.Ogino         Update          QC#5553
 * 03/27/2016   CITS            K.Ogino         Update          QC#5553
 * 03/28/2016   CITS            K.Ogino         Update          QC#6120
 * 03/31/2016   CITS            K.Ogino         Update          QC#6346
 * 05/17/2016   CITS            K.Ogino         Update          QC#7452
 * 11/07/2016   CITS            S.Endo          Update          QC#14479
 * 11/16/2016   CITS            S.Endo          Update          QC#14479
 * 07/13/2017   CITS            S.Endo          Update          QC#19926
 * 06/11/2018   CITS            K.Ogino         Update          QC#26529
 * 06/12/2018   CITS            T.hakodate      Update          QC#10572
 * 08/01/2018   CITS            S.Katsuma       Update          QC#27404
 *</pre>
 */
public class NLZC062001 extends S21ApiCommonBase implements S21NwfApproveEvent<S21NwfContext, S21NwfToken>, S21NwfReassignEvent<S21NwfContext, S21NwfToken>, S21NwfRejectEvent<S21NwfContext, S21NwfToken>,
        S21NwfProcessEndEvent<S21NwfContext, S21NwfToken> {


    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** message list */
    private List<String> msgList = new ArrayList<String>();

    /**
     * Constructor
     */
    public NLZC062001() {
        super();
    }

    /**
     * S21NwfApproveEvent approve
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfBizException error
     */
    public void approve(String name, S21NwfContext context, S21NwfToken token) throws S21NwfBizException {
        // Get TokenObject of WorkFlow
        NLZC061001TokenObject tokenObj = (NLZC061001TokenObject) token.getTokenObj();

        // Create Approval History
        // QC#26529
        String apvlHistSrcTpCd = tokenObj.getCondStr3();
        if (APVL_HIST_SRC_TP.TECHNICIAN_PHYSICAL_INVENTORY.equals(apvlHistSrcTpCd)) {
            createApprovalHistory(tokenObj, APVL_HIST_SRC_TP.TECHNICIAN_PHYSICAL_INVENTORY, APVL_HIST_ACT_TP.APPROVED, context.getUserID());
        } else if (APVL_HIST_SRC_TP.PHYSICAL_INVENTORY_MERCHANDISE.equals(apvlHistSrcTpCd)) {
            createApprovalHistory(tokenObj, APVL_HIST_SRC_TP.PHYSICAL_INVENTORY_MERCHANDISE, APVL_HIST_ACT_TP.APPROVED, context.getUserID());
        } else {
            createApprovalHistory(tokenObj, APVL_HIST_SRC_TP.PHYSICAL_INVENTORY_PARTS, APVL_HIST_ACT_TP.APPROVED, context.getUserID());
        }
    }

    /**
     * S21NwfReassignEvent reassign
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @param fromUser String
     * @param toUser String
     * @param comment String
     * @throws S21NwfException error
     */
    public void reassign(String name, S21NwfContext context, S21NwfToken token, String fromUser, String toUser, String comment) throws S21NwfException {
        // Get TokenObject of WorkFlow
        NLZC061001TokenObject tokenObj = (NLZC061001TokenObject) token.getTokenObj();

        // Create Approval History
        // QC#26529
        String apvlHistSrcTpCd = tokenObj.getCondStr3();
        if (APVL_HIST_SRC_TP.TECHNICIAN_PHYSICAL_INVENTORY.equals(apvlHistSrcTpCd)) {
            createApprovalHistory(tokenObj, APVL_HIST_SRC_TP.TECHNICIAN_PHYSICAL_INVENTORY, APVL_HIST_ACT_TP.FORWARD, context.getUserID());
        } else if (APVL_HIST_SRC_TP.PHYSICAL_INVENTORY_MERCHANDISE.equals(apvlHistSrcTpCd)) {
            createApprovalHistory(tokenObj, APVL_HIST_SRC_TP.PHYSICAL_INVENTORY_MERCHANDISE, APVL_HIST_ACT_TP.FORWARD, context.getUserID());
        } else {
            createApprovalHistory(tokenObj, APVL_HIST_SRC_TP.PHYSICAL_INVENTORY_PARTS, APVL_HIST_ACT_TP.FORWARD, context.getUserID());
        }
    }

    /**
     * S21NwfRejectEvent reject
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfBizException error
     */
    public void reject(String name, S21NwfContext context, S21NwfToken token) throws S21NwfBizException {
        // Get TokenObject of WorkFlow
        NLZC061001TokenObject tokenObj = (NLZC061001TokenObject) token.getTokenObj();

        // Create Approval History
        // QC#26529
        String apvlHistSrcTpCd = tokenObj.getCondStr3();
        if (APVL_HIST_SRC_TP.TECHNICIAN_PHYSICAL_INVENTORY.equals(apvlHistSrcTpCd)) {
            createApprovalHistory(tokenObj, APVL_HIST_SRC_TP.TECHNICIAN_PHYSICAL_INVENTORY, APVL_HIST_ACT_TP.REJECTED, context.getUserID());
        } else if (APVL_HIST_SRC_TP.PHYSICAL_INVENTORY_MERCHANDISE.equals(apvlHistSrcTpCd)) {
            createApprovalHistory(tokenObj, APVL_HIST_SRC_TP.PHYSICAL_INVENTORY_MERCHANDISE, APVL_HIST_ACT_TP.REJECTED, context.getUserID());
        } else {
            createApprovalHistory(tokenObj, APVL_HIST_SRC_TP.PHYSICAL_INVENTORY_PARTS, APVL_HIST_ACT_TP.REJECTED, context.getUserID());
        }
    }

    /**
     * S21NwfProcessEndEvent end
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfBizException error
     */
    public void end(String name, S21NwfContext context, S21NwfToken token) throws S21NwfBizException {
        NLZC061001TokenObject tokenObj = (NLZC061001TokenObject) token.getTokenObj();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        String piMode = getPIMode(tokenObj.getGlblCmpyCd(), tokenObj.getTrxRefNum());
        // START 2018/08/01 S.Katsuma [QC#27404,ADD]
        String slsDt = ZYPDateUtil.getSalesDate(tokenObj.getGlblCmpyCd());
        // END 2018/08/01 S.Katsuma [QC#27404,ADD]
        // Approve or Reject
        if (APPROVE.equals(name)) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(BIND_PARAM_GLBL_CMPY_CD, tokenObj.getGlblCmpyCd());
            paramMap.put(BIND_PARAM_PHYS_INVTY_NUM, tokenObj.getTrxRefNum());

            paramMap.put(BIND_PARAM_ADJ_VAR_FLG, ZYPConstant.FLG_ON_Y);
            paramMap.put(BIND_PARAM_LOC_STS_CD, LOC_STS.DC_STOCK);
            paramMap.put(BIND_PARAM_PHYS_INVTY_ADJ_NM, tokenObj.getPhysInvtyAdjNm());

            List<Map<String, Object>> resultList = getSsmQueryObjectList("adjustmentPiCount", paramMap);

            List<NLZC004001PMsg> pMsgList = new ArrayList<NLZC004001PMsg>();
            List<Map<String, Object>> srcList = new ArrayList<Map<String, Object>>();
            List<PHYS_INVTY_ADJTMsg> adjMsgList = new ArrayList<PHYS_INVTY_ADJTMsg>();
            String preRtlWhCd = null;
            String preWhCd = null;
            String preMdseCd = null;
            String preStkStsCd = null;
            BigDecimal preAdjVarQty = null;
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> record = resultList.get(i);

                if (!pMsgList.isEmpty() && (pMsgList.size() == LINE_MAX_COUNT + 1
                                            || !preRtlWhCd.equals(record.get(DB_RESULT_COL_RTL_WH_CD)))) {
                    picAdjustmentUpdate(tokenObj, pMsgList, srcList);
                    piAdjustInsert(adjMsgList, pMsgList);
                    pMsgList.clear();
                    srcList.clear();
                    adjMsgList.clear();
                }

                if (!checkRecord(tokenObj, record)) {
                    continue;
                }

                if (pMsgList.isEmpty()) {
                    pMsgList.add(createHeaderParam(tokenObj, record, slsDt));
                    srcList.add(record);
                    preRtlWhCd = (String) record.get(DB_RESULT_COL_RTL_WH_CD);
                    preWhCd = (String) record.get(DB_RESULT_COL_WH_CD);
                    preMdseCd = (String) record.get(DB_RESULT_COL_MDSE_CD);
                    preStkStsCd = (String) record.get(DB_RESULT_COL_STK_STS_CD);
                    preAdjVarQty = (BigDecimal) record.get(DB_RESULT_COL_ADJ_VAR_QTY);
                    pMsgList.add(createDetailParam(tokenObj, record, slsDt));
                    srcList.add(record);
                    adjMsgList.add(createPIAdjustMsg(tokenObj, record));
                } else {
                    NLZC004001PMsg lastMsg = pMsgList.get(pMsgList.size() - 1);
                    if (ZYPConstant.FLG_ON_Y.equals(record.get(DB_RESULT_COL_RCV_SER_TAKE_FLG))
                            && preWhCd.equals(record.get(DB_RESULT_COL_WH_CD))
                            && preMdseCd.equals(record.get(DB_RESULT_COL_MDSE_CD))
                            && preStkStsCd.equals(record.get(DB_RESULT_COL_STK_STS_CD))
                            && preAdjVarQty.signum() == ((BigDecimal) record.get(DB_RESULT_COL_ADJ_VAR_QTY)).signum()
                            && lastMsg.serialInfoList.getValidCount() < SERIAL_MAX_COUNT) {
                        addSerial(lastMsg, tokenObj, record);
                        adjMsgList.add(createPIAdjustMsg(tokenObj, record));
                    } else {
                        pMsgList.add(createDetailParam(tokenObj, record, slsDt));
                        srcList.add(record);
                        adjMsgList.add(createPIAdjustMsg(tokenObj, record));
                    }
                }
            }

            if (!pMsgList.isEmpty()) {
                picAdjustmentUpdate(tokenObj, pMsgList, srcList);
                piAdjustInsert(adjMsgList, pMsgList);
            }

            summaryPICountHeader(tokenObj, pMsgList);
            summaryPIControl(tokenObj);
            // START 2018/08/01 S.Katsuma [QC#27404,MOD]
            // NLZC063001 PI Close API Call
//            piClose(tokenObj.getGlblCmpyCd(), tokenObj.getSalesDate(), PHYS_INVTY_CNT_STS.PI_VARIANCE, tokenObj.getTrxRefNum());
            piClose(tokenObj.getGlblCmpyCd(), slsDt, PHYS_INVTY_CNT_STS.PI_VARIANCE, tokenObj.getTrxRefNum());
            // END 2018/08/01 S.Katsuma [QC#27404,ADD]

            sendEmail(context, tokenObj, piMode);
        } else if (REJECT.equals(name)) {
            // NLZC063001 PI Close API Call
            if (LOC_TP.TECHNICIAN.equals(piMode)) {
                // START 2018/08/01 S.Katsuma [QC#27404,MOD]
//                piClose(tokenObj.getGlblCmpyCd(), tokenObj.getSalesDate(), PHYS_INVTY_CNT_STS.PI_REJECTED, tokenObj.getTrxRefNum());
                piClose(tokenObj.getGlblCmpyCd(), slsDt, PHYS_INVTY_CNT_STS.PI_REJECTED, tokenObj.getTrxRefNum());
                // END 2018/08/01 S.Katsuma [QC#27404,MOD]
            } else {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put(BIND_PARAM_GLBL_CMPY_CD, tokenObj.getGlblCmpyCd());
                paramMap.put(BIND_PARAM_PHYS_INVTY_NUM, tokenObj.getTrxRefNum());
                List<BigDecimal> result = (List<BigDecimal>) ssmBatchClient.queryObjectList("getPhysInvtyCtrlPk", paramMap);

                if (result.size() == 0) {
                    throw new S21NwfBizException(NLCM0163E, null);
                }

                for (BigDecimal pk : result) {
                    PHYS_INVTY_CTRLTMsg inMsg = new PHYS_INVTY_CTRLTMsg();
                    ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, tokenObj.getGlblCmpyCd());
                    ZYPEZDItemValueSetter.setValue(inMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                    ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlPk, pk);
                    PHYS_INVTY_CTRLTMsg outMsg = (PHYS_INVTY_CTRLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);

                    if (outMsg == null) {
                        throw new S21NwfBizException(NLCM0163E, null);
                    }
                    ZYPEZDItemValueSetter.setValue(outMsg.physInvtyCntStsCd, PHYS_INVTY_CNT_STS.RECOUNTING);
                    S21ApiTBLAccessor.update(outMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                        throw new S21NwfBizException(NLCM0163E, null);
                    }

                    //update PHYS_INVTY_CNT_HDR set EZCANCELFLAG = '1'
                    paramMap = new HashMap<String, Object>();
                    paramMap.put(BIND_PARAM_GLBL_CMPY_CD, tokenObj.getGlblCmpyCd());
                    paramMap.put(BIND_PARAM_PHYS_INVTY_CTRL_PK, pk);
                    List<BigDecimal> headerPkList =  (List<BigDecimal>) ssmBatchClient.queryObjectList("getPhysInvtyHdrPk", paramMap);

                    if (headerPkList.size() == 0) {
                        throw new S21NwfBizException(NLZM2454E, null);
                    }
                    for (BigDecimal headerPk : headerPkList) {
                        PHYS_INVTY_CNT_HDRTMsg headerMsg = new PHYS_INVTY_CNT_HDRTMsg();
                        ZYPEZDItemValueSetter.setValue(headerMsg.glblCmpyCd, tokenObj.getGlblCmpyCd());
                        ZYPEZDItemValueSetter.setValue(headerMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                        ZYPEZDItemValueSetter.setValue(headerMsg.physInvtyCntHdrPk, headerPk);
                        PHYS_INVTY_CNT_HDRTMsg headerOutMsg = (PHYS_INVTY_CNT_HDRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(headerMsg);
                        if (headerOutMsg == null) {
                            throw new S21NwfBizException(NLZM2454E, null);
                        }
                        S21ApiTBLAccessor.logicalRemove(headerOutMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(headerOutMsg.getReturnCode())) {
                            throw new S21NwfBizException(NLZM2454E, null);
                        }
                    }

                    //update PHYS_INVTY_CNT_DLT set EZCANCELFLAG = '1'
                    paramMap = new HashMap<String, Object>();
                    paramMap.put(BIND_PARAM_GLBL_CMPY_CD, tokenObj.getGlblCmpyCd());
                    paramMap.put(BIND_PARAM_PHYS_INVTY_CTRL_PK, pk);
                    List<BigDecimal> detailPkList =  (List<BigDecimal>) ssmBatchClient.queryObjectList("getPhysInvtyDtlPk", paramMap);

                    if (detailPkList.size() == 0) {
                        throw new S21NwfBizException(NLZM2455E, null);
                    }
                    for (BigDecimal detailPk : detailPkList) {
                        PHYS_INVTY_CNT_DTLTMsg detailMsg = new PHYS_INVTY_CNT_DTLTMsg();
                        ZYPEZDItemValueSetter.setValue(detailMsg.glblCmpyCd, tokenObj.getGlblCmpyCd());
                        ZYPEZDItemValueSetter.setValue(detailMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                        ZYPEZDItemValueSetter.setValue(detailMsg.physInvtyCntDtlPk, detailPk);
                        PHYS_INVTY_CNT_DTLTMsg detailOutMsg = (PHYS_INVTY_CNT_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(detailMsg);
                        if (detailOutMsg == null) {
                            throw new S21NwfBizException(NLZM2455E, null);
                        }
                        S21ApiTBLAccessor.logicalRemove(detailOutMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(detailOutMsg.getReturnCode())) {
                            throw new S21NwfBizException(NLZM2455E, null);
                        }
                    }
                }
            }
        } else if (CANCEL.equals(name)) {
            return;
        } else if (S21NwfConst.COND_END_NO_USER.equals(name)) {
            throw new S21NwfBizException(NLCM0168E, null);
        } else {
            throw new S21NwfBizException(NLCM0169E, null);
        }
    }

    private void picAdjustmentUpdate(NLZC061001TokenObject tokenObj, List<NLZC004001PMsg> pMsgList, List<Map<String, Object>> srcList) throws S21NwfBizException {
        NLZC004001 adjustmentApi = new NLZC004001();
        adjustmentApi.execute(pMsgList, ONBATCH_TYPE.BATCH);
        for (int c = 0; c < pMsgList.size(); c++) {
            if (S21ApiUtil.isXxMsgId(pMsgList.get(c))) {
                List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsgList.get(c));
                for (String xxMsgId : xxMsgIdList) {
                    if (xxMsgId.endsWith(MESSAGE_KIND_ERROR)) {
                        throw new S21NwfBizException(NLCM0165E, null);
                    }
                }
            }
        }
        for (int k = 1; k < pMsgList.size(); k++) {
            PHYS_INVTY_CNT_HDRTMsg inMsg = new PHYS_INVTY_CNT_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, pMsgList.get(k).glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntHdrPk, (BigDecimal) srcList.get(k).get(DB_RESULT_COL_PHYS_INVTY_CNT_HDR_PK));
            PHYS_INVTY_CNT_HDRTMsg tMsg = (PHYS_INVTY_CNT_HDRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (tMsg == null) {
                throw new S21NwfBizException(NLZM2454E, null);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.invtyOrdNum, pMsgList.get(k).invtyOrdNum);
            S21ApiTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21NwfBizException(NLZM2454E, null);
            }
        }
    }

    private void piAdjustInsert(List<PHYS_INVTY_ADJTMsg> adjMsgList, List<NLZC004001PMsg> pMsgList) throws S21NwfBizException {
        for (PHYS_INVTY_ADJTMsg inMsg : adjMsgList) {
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyAdjPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_ADJ_SQ));
            for (int i = 1; i < pMsgList.size(); i++) {
                NLZC004001PMsg pMsg = pMsgList.get(i);
                if (pMsg.mdseCd.getValue().equals(inMsg.mdseCd.getValue())
                        && pMsg.invtyLocCd_D1.getValue().equals(inMsg.whCd.getValue())
                        && pMsg.stkStsCd.getValue().equals(inMsg.stkStsCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(inMsg.invtyOrdNum, pMsg.invtyOrdNum);
                    ZYPEZDItemValueSetter.setValue(inMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
                    ZYPEZDItemValueSetter.setValue(inMsg.physInvtyAdjNm, pMsg.firstInvtyOrdCmntTxt);
                }
            }
            S21ApiTBLAccessor.insert(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                throw new S21NwfBizException(NLZM2453E, null);
            }
        }
    }

    private void updatePICountDetailStatus(NLZC061001TokenObject tokenObj, Map<String, Object> map, String msgId) throws S21NwfBizException {
        PHYS_INVTY_CNT_DTLTMsg inMsg = new PHYS_INVTY_CNT_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, tokenObj.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntDtlPk, (BigDecimal) map.get(DB_RESULT_COL_PHYS_INVTY_CNT_DTL_PK));
        PHYS_INVTY_CNT_DTLTMsg outMsg = (PHYS_INVTY_CNT_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
        if (outMsg == null) {
            throw new S21NwfBizException(NLZM2455E, null);
        }

        ZYPEZDItemValueSetter.setValue(outMsg.physInvtyAdjStsCd, PHYS_INVTY_ADJ_STS.SKIP_ADJUSTMENT_DUE_TO_ERROR);
        ZYPEZDItemValueSetter.setValue(outMsg.physInvtyAdjMsgTxt, msgId);
        S21ApiTBLAccessor.update(outMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
            throw new S21NwfBizException(NLZM2455E, null);
        }
    }

    private PHYS_INVTY_ADJTMsg createPIAdjustMsg(NLZC061001TokenObject tokenObj, Map<String, Object> map) {
        PHYS_INVTY_ADJTMsg tMsg = new PHYS_INVTY_ADJTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, tokenObj.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(tMsg.physInvtyCtrlPk, (BigDecimal) map.get(DB_RESULT_COL_PHYS_INVTY_CTRL_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, (String) map.get(DB_RESULT_COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, (String) map.get(DB_RESULT_COL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.stkStsCd, (String) map.get(DB_RESULT_COL_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.adjVarQty, (BigDecimal) map.get(DB_RESULT_COL_ADJ_VAR_QTY));
        if (tMsg.adjVarQty.getValue().signum() == 1) {
            ZYPEZDItemValueSetter.setValue(tMsg.adjVarSgnCd, ADJ_VAR_SGN_CD_PLUS);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.adjVarSgnCd, ADJ_VAR_SGN_CD_MINUS);
        }
        if (ZYPConstant.FLG_ON_Y.equals(map.get(DB_RESULT_COL_RCV_SER_TAKE_FLG))) {
            ZYPEZDItemValueSetter.setValue(tMsg.serNum, (String) map.get(DB_RESULT_COL_SER_NUM));
        }
        return tMsg;
    }

    private void summaryPIControl(NLZC061001TokenObject tokenObj) throws S21NwfBizException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(BIND_PARAM_GLBL_CMPY_CD, tokenObj.getGlblCmpyCd());
        paramMap.put(BIND_PARAM_PHYS_INVTY_NUM, tokenObj.getTrxRefNum());
        paramMap.put(BIND_PARAM_PHYS_INVTY_ADJ_STS_CD, PHYS_INVTY_ADJ_STS.SKIP_ADJUSTMENT_DUE_TO_ERROR);
        List<Map<String, Object>> result = getSsmQueryObjectList("summaryPIControl", paramMap);
        if (result == null) {
            throw new S21NwfBizException(NLCM0163E, null);
        }

        for (Map<String, Object> record :  result) {
            PHYS_INVTY_CTRLTMsg inMsg = new PHYS_INVTY_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, tokenObj.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlPk, (BigDecimal) record.get(DB_RESULT_COL_PHYS_INVTY_CTRL_PK));
            PHYS_INVTY_CTRLTMsg outMsg = (PHYS_INVTY_CTRLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (outMsg == null) {
                throw new S21NwfBizException(NLCM0163E, null);
            }

            ZYPEZDItemValueSetter.setValue(outMsg.adjGrsAmt, (BigDecimal) record.get(DB_RESULT_COL_TOT_ADJ_VAR_GRS_AMT));
            ZYPEZDItemValueSetter.setValue(outMsg.adjNetAmt, (BigDecimal) record.get(DB_RESULT_COL_TOT_ADJ_VAR_NET_AMT));
            S21ApiTBLAccessor.update(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                throw new S21NwfBizException(NLCM0163E, null);
            }
        }
    }

    private void summaryPICountHeader(NLZC061001TokenObject tokenObj, List<NLZC004001PMsg> pMsgList) throws S21NwfBizException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(BIND_PARAM_GLBL_CMPY_CD, tokenObj.getGlblCmpyCd());
        paramMap.put(BIND_PARAM_PHYS_INVTY_NUM, tokenObj.getTrxRefNum());
        paramMap.put(BIND_PARAM_PHYS_INVTY_ADJ_STS_CD, PHYS_INVTY_ADJ_STS.SKIP_ADJUSTMENT_DUE_TO_ERROR);
        List<Map<String, Object>> result = getSsmQueryObjectList("summaryPICountHeader", paramMap);
        if (result == null) {
            throw new S21NwfBizException(NLZM2454E, null);
        }

        for (Map<String, Object> record :  result) {
            PHYS_INVTY_CNT_HDRTMsg inMsg = new PHYS_INVTY_CNT_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, tokenObj.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntHdrPk, (BigDecimal) record.get(DB_RESULT_COL_PHYS_INVTY_CNT_HDR_PK));
            PHYS_INVTY_CNT_HDRTMsg outMsg = (PHYS_INVTY_CNT_HDRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (outMsg == null) {
                throw new S21NwfBizException(NLZM2454E, null);
            }

            ZYPEZDItemValueSetter.setValue(outMsg.adjVarQty, (BigDecimal) record.get(DB_RESULT_COL_SUB_TOT_ADJ_VAR_QTY));
            ZYPEZDItemValueSetter.setValue(outMsg.adjVarAmt, (BigDecimal) record.get(DB_RESULT_COL_SUB_TOT_ADJ_VAR_AMT));

            for (NLZC004001PMsg pMsg : pMsgList) {
                if (HEADER.equals(pMsg.xxDtTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(outMsg.invtyOrdNum, pMsg.invtyOrdNum);
                    break;
                }
            }
            S21ApiTBLAccessor.update(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                throw new S21NwfBizException(NLZM2454E, null);
            }
        }
    }

    private boolean checkRecord(NLZC061001TokenObject tokenObj, Map<String, Object> map) throws S21NwfBizException {

        if (!PHYS_INVTY_STS.OPEN.equals((String) map.get(DB_RESULT_COL_PHYS_INVTY_STS_CD))) {
            throw new S21NwfBizException(NLZM2456E, new String[] {(String) map.get(DB_RESULT_COL_PHYS_INVTY_STS_NM), tokenObj.getTrxRefNum()});
        }

        if (map.get(DB_RESULT_COL_WH_CD) == null) {
            throw new S21NwfBizException(NLZM2457E, null);
        }

        if (map.get(DB_RESULT_COL_MSTR_MDSE_CD) == null) {
            setErrorInfo(NLZM2458E, new String[] {(String) map.get(DB_RESULT_COL_MDSE_CD)}, tokenObj, map);
            updatePICountDetailStatus(tokenObj, map, NLZM2458E);
            return false;
        }

        if (ZYPConstant.FLG_OFF_N.equals(map.get(DB_RESULT_COL_RCV_SER_TAKE_FLG))
                && ((BigDecimal) map.get(DB_RESULT_COL_ADJ_VAR_QTY)).intValue() == 0) {
            setErrorInfo(NLZM2460E, new String[] {((BigDecimal) map.get(DB_RESULT_COL_ADJ_VAR_QTY)).toPlainString(), (String) map.get(DB_RESULT_COL_MDSE_CD)}, tokenObj, map);
            updatePICountDetailStatus(tokenObj, map, NLZM2460E);
            return false;
        }

        if (ZYPConstant.FLG_ON_Y.equals(map.get(DB_RESULT_COL_RCV_SER_TAKE_FLG))) {
            if (map.get(DB_RESULT_COL_SER_NUM) == null) {
                setErrorInfo(NLZM2461E, new String[] {(String) map.get(DB_RESULT_COL_MDSE_CD)}, tokenObj, map);
                updatePICountDetailStatus(tokenObj, map, NLZM2461E);
                return false;
            }

            if (((BigDecimal) map.get(DB_RESULT_COL_ADJ_VAR_QTY)).intValue() != 1
                    && ((BigDecimal) map.get(DB_RESULT_COL_ADJ_VAR_QTY)).intValue() != -1) {
                setErrorInfo(NLZM2460E, new String[] {((BigDecimal) map.get(DB_RESULT_COL_ADJ_VAR_QTY)).toPlainString(), (String) map.get(DB_RESULT_COL_MDSE_CD)}, tokenObj, map);
                updatePICountDetailStatus(tokenObj, map, NLZM2460E);
                return false;
            }

            NLZC403001PMsg pMsg = new NLZC403001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, tokenObj.getGlblCmpyCd());
            if (((BigDecimal) map.get(DB_RESULT_COL_ADJ_VAR_QTY)).signum() == 1) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NLZC403001Constant.MODE_INBOUND);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NLZC403001Constant.MODE_ADJUSTMENT_DECREASE);
            }
            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (String) map.get(DB_RESULT_COL_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.serNum, (String) map.get(DB_RESULT_COL_SER_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.whCd, (String) map.get(DB_RESULT_COL_WH_CD));
            NLZC403001 nlzc4030 = new NLZC403001();
            nlzc4030.execute(pMsg, ONBATCH_TYPE.BATCH);
            if (!NLZC403001Constant.RETURN_CODE_NORMAL.equals(pMsg.xxRsltStsCd.getValue())) {
                List<String> apiMsgList = S21ApiUtil.getXxMsgIdList(pMsg);
                if (!apiMsgList.isEmpty()) {
                    for (String xxMsgId : apiMsgList) {
                        setErrorInfo(xxMsgId, null, tokenObj, map);
                    }
                    updatePICountDetailStatus(tokenObj, map, apiMsgList.get(0));
                }
                return false;
            }
        }

        // QC#11464
        if (!checkInvtyQty(tokenObj, map)) {
            return false;
        }

        if (ZYPConstant.FLG_OFF_N.equals(map.get(DB_RESULT_COL_RCV_SER_TAKE_FLG))
                && map.get(DB_RESULT_COL_SER_NUM) != null) {
            setErrorInfo(NLZM2459W, new String[] {(String) map.get(DB_RESULT_COL_SER_NUM), (String) map.get(DB_RESULT_COL_MDSE_CD)}, tokenObj, map);
        }

        return true;
    }

    private void setErrorInfo(String msgId, String[] params, NLZC061001TokenObject tokenObj, Map<String, Object> map) {

        String errorMsg = S21MessageFunc.clspGetMessage(msgId, params);
        String serNum = (String) map.get(DB_RESULT_COL_SER_NUM);
        if (serNum == null) {
            serNum = "";
        }
        String msg = String.format("%s  %-7s  %-10s     %s     %-30s  %5s  %9s  %s",
                tokenObj.getTrxRefNum(),
                (String) map.get(DB_RESULT_COL_WH_CD),
                (String) map.get(DB_RESULT_COL_MDSE_CD),
                (String) map.get(DB_RESULT_COL_STK_STS_CD),
                serNum,
                ((BigDecimal) map.get(DB_RESULT_COL_ADJ_VAR_QTY)).toPlainString(),
                msgId,
                errorMsg);

        S21InfoLogOutput.println(msgId, params);
        this.msgList.add(msg);
    }

    private void sendEmail(S21NwfContext context, NLZC061001TokenObject tokenObj, String piMode) {

        if (msgList.isEmpty()) {
            return;
        }

        S21MailGroup fromGrp = new S21MailGroup(tokenObj.getGlblCmpyCd(), MAIL_GROUP_ID_FROM);
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();

        S21Mail mail = new S21Mail(tokenObj.getGlblCmpyCd());

        if (fromAddrList.isEmpty()) {
            return;
        }

        mail.setFromAddress(fromAddrList.get(0));

        List<String> toAddrList = new ArrayList<String>();
        toAddrList.add(context.getUserID());
        if (LOC_TP.TECHNICIAN.equals(piMode)) {
            toAddrList.add(tokenObj.getCondStr1());
        }

        if (!MailDefinition.NORMAL_END.equals(mail.setToUserIdList(toAddrList))) {
            return;
        }

        S21MailTemplate tmpl = new S21MailTemplate(tokenObj.getGlblCmpyCd(), MAIL_TEMPLATE_ID);
        if (!ZYPCommonFunc.hasValue(tmpl.getSubject())) {
            return;
        }

        String newLine = System.getProperty("line.separator");

        StringBuilder msgBuf = new StringBuilder();
        for (String msg : msgList) {
            msgBuf.append(msg);
            msgBuf.append(newLine);
        }

        SimpleDateFormat errTmFmt = new SimpleDateFormat(DATE_TIME_PATTERN_FOR_MAIL);

        String ts = errTmFmt.format(new Date());
        tmpl.setTemplateParameter("TIMESTAMP", ts);
        tmpl.setTemplateParameter("errDate", ts);
        tmpl.setTemplateParameter("message", msgBuf.toString());

        mail.setMailTemplate(tmpl);
        mail.postMail();

    }

    // -----------------------------------------------------------
    // Create NLZC0040 Parameter
    // -----------------------------------------------------------
    /**
     * Header Param
     * @param tokenObj NLZC061001TokenObject
     * @param map Map<String, Object>
     * @return NLZC004001PMsg
     */
    private NLZC004001PMsg createHeaderParam(NLZC061001TokenObject tokenObj, Map<String, Object> map, String slsDt) {
        NLZC004001PMsg pMsg = new NLZC004001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcTpCd, CREATE_CLOSE);
        ZYPEZDItemValueSetter.setValue(pMsg.xxDtTpCd, HEADER);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, tokenObj.getGlblCmpyCd());
        // START 2018/08/01 S.Katsuma [QC#27404,MOD]
//      ZYPEZDItemValueSetter.setValue(pMsg.slsDt, tokenObj.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        // END 2018/08/01 S.Katsuma [QC#27404,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.invtyOrdTpCd, INVTY_ORD_TP.ADJUSTMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, (String) map.get(DB_RESULT_COL_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(pMsg.adjTrxTpCd, ADJ_TRX_TP.PHYSICAL_INVENTORY_ADJUSTMENT);
        if (ZYPCommonFunc.hasValue(tokenObj.getPhysInvtyAdjNm())) {
            if (tokenObj.getPhysInvtyAdjNm().length() > COMMENT_SUBSTR_INDEX) {
                ZYPEZDItemValueSetter.setValue(pMsg.firstInvtyOrdCmntTxt, tokenObj.getPhysInvtyAdjNm().substring(0, COMMENT_SUBSTR_INDEX));
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.firstInvtyOrdCmntTxt, tokenObj.getPhysInvtyAdjNm());
            }
        }
        String allComment = tokenObj.getComment();
        if (ZYPCommonFunc.hasValue(allComment)) {
            int byteNum = allComment.length();
            int beginIndex = 0;
            int endIndex = COMMENT_SUBSTR_INDEX;
            if (endIndex < byteNum) {
                // 64 byte substring
                String firstInvtyOrdCmntTxt = allComment.substring(beginIndex, endIndex);
                ZYPEZDItemValueSetter.setValue(pMsg.scdInvtyOrdCmntTxt, firstInvtyOrdCmntTxt);
                beginIndex = beginIndex + COMMENT_SUBSTR_INDEX;
                endIndex = endIndex + COMMENT_SUBSTR_INDEX;
                if (endIndex < byteNum) {
                    // 128 byte substring
                    String scdInvtyOrdCmntTxt = allComment.substring(beginIndex, endIndex);
                    ZYPEZDItemValueSetter.setValue(pMsg.thirdInvtyOrdCmntTxt, scdInvtyOrdCmntTxt);

                } else {
                    String scdInvtyOrdCmntTxt = allComment.substring(beginIndex, byteNum);
                    ZYPEZDItemValueSetter.setValue(pMsg.thirdInvtyOrdCmntTxt, scdInvtyOrdCmntTxt);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.scdInvtyOrdCmntTxt, allComment);
            }
        }
        return pMsg;
    }

    /**
     * Detail Param
     * @param tokenObj NLZC061001TokenObject
     * @param map Map<String, Object>
     * @return NLZC004001PMsg
     */
    private NLZC004001PMsg createDetailParam(NLZC061001TokenObject tokenObj, Map<String, Object> map, String slsDt) {
        NLZC004001PMsg pMsg = new NLZC004001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcTpCd, CREATE_CLOSE);
        ZYPEZDItemValueSetter.setValue(pMsg.xxDtTpCd, DETAIL);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, tokenObj.getGlblCmpyCd());
        // START 2018/08/01 S.Katsuma [QC#27404,MOD]
//        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, tokenObj.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        // END 2018/08/01 S.Katsuma [QC#27404,MOD]
        BigDecimal adjVarQty = (BigDecimal) map.get(DB_RESULT_COL_ADJ_VAR_QTY);
        if (adjVarQty.compareTo(BigDecimal.ZERO) == 1) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, STOCK_IN);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, STOCK_OUT);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.invtyOrdTpCd, INVTY_ORD_TP.ADJUSTMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, (String) map.get(DB_RESULT_COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(pMsg.adjTrxTpCd, ADJ_TRX_TP.PHYSICAL_INVENTORY_ADJUSTMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (String) map.get(DB_RESULT_COL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, (String) map.get(DB_RESULT_COL_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd_D1, (String) map.get(DB_RESULT_COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.locStsCd_D1, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pMsg.ordQty, adjVarQty);
        if (ZYPCommonFunc.hasValue(tokenObj.getPhysInvtyAdjNm())) {
            if (tokenObj.getPhysInvtyAdjNm().length() > COMMENT_SUBSTR_INDEX) {
                ZYPEZDItemValueSetter.setValue(pMsg.firstInvtyOrdCmntTxt, tokenObj.getPhysInvtyAdjNm().substring(0, COMMENT_SUBSTR_INDEX));
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.firstInvtyOrdCmntTxt, tokenObj.getPhysInvtyAdjNm());
            }
        }
        String allComment = tokenObj.getComment();
        if (ZYPCommonFunc.hasValue(allComment)) {
            int byteNum = allComment.length();
            int beginIndex = 0;
            int endIndex = COMMENT_SUBSTR_INDEX;
            if (endIndex < byteNum) {
                // 64 byte substring
                String firstInvtyOrdCmntTxt = allComment.substring(beginIndex, endIndex);
                ZYPEZDItemValueSetter.setValue(pMsg.scdInvtyOrdCmntTxt, firstInvtyOrdCmntTxt);
                beginIndex = beginIndex + COMMENT_SUBSTR_INDEX;
                endIndex = endIndex + COMMENT_SUBSTR_INDEX;
                if (endIndex < byteNum) {
                    // 128 byte substring
                    String scdInvtyOrdCmntTxt = allComment.substring(beginIndex, endIndex);
                    ZYPEZDItemValueSetter.setValue(pMsg.thirdInvtyOrdCmntTxt, scdInvtyOrdCmntTxt);

                } else {
                    String scdInvtyOrdCmntTxt = allComment.substring(beginIndex, byteNum);
                    ZYPEZDItemValueSetter.setValue(pMsg.thirdInvtyOrdCmntTxt, scdInvtyOrdCmntTxt);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.scdInvtyOrdCmntTxt, allComment);
            }
        }

        ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, (BigDecimal) map.get(DB_RESULT_COL_SVC_CONFIG_MSTR_PK));
        if (ZYPConstant.FLG_ON_Y.equals(map.get(DB_RESULT_COL_RCV_SER_TAKE_FLG))) {
            ZYPEZDItemValueSetter.setValue(pMsg.serialInfoList.no(pMsg.serialInfoList.getValidCount()).serNum, (String) map.get(DB_RESULT_COL_SER_NUM));
            pMsg.serialInfoList.setValidCount(pMsg.serialInfoList.getValidCount() + 1);
        }
        return pMsg;
    }

    /**
     * Add Serial
     * @param pMsg NLZC004001PMsg
     * @param tokenObj NLZC061001TokenObject
     * @param map Map<String, Object>
     */
    private void addSerial(NLZC004001PMsg pMsg, NLZC061001TokenObject tokenObj, Map<String, Object> map) {
        BigDecimal adjVarQty = (BigDecimal) map.get(DB_RESULT_COL_ADJ_VAR_QTY);
        ZYPEZDItemValueSetter.setValue(pMsg.ordQty, pMsg.ordQty.getValue().add(adjVarQty));
        ZYPEZDItemValueSetter.setValue(pMsg.serialInfoList.no(pMsg.serialInfoList.getValidCount()).serNum, (String) map.get(DB_RESULT_COL_SER_NUM));
        pMsg.serialInfoList.setValidCount(pMsg.serialInfoList.getValidCount() + 1);
    }

    // -----------------------------------------------------------
    // Data base Query
    // -----------------------------------------------------------
    /**
     * db query
     * @param sqlId String
     * @param paramMap Map<String, Object>
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getSsmQueryObjectList(String sqlId, Map<String, Object> paramMap) {
        List<Map<String, Object>> mapResList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(sqlId, paramMap);
        return mapResList;
    }

    // -----------------------------------------------------------
    // PI Close API
    // -----------------------------------------------------------
    private void piClose(String glblCmpyCd, String salesDate, String physInvtyCntStsCd, String physInvtyNum) throws S21NwfBizException {
        NLZC063001PMsg pMsg = new NLZC063001PMsg();
        NLZC063001 api = new NLZC063001();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.salesDate, salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyCntStsCd, physInvtyCntStsCd);
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyNum, physInvtyNum);
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
            for (String xxMsgId : xxMsgIdList) {
                if (xxMsgId.endsWith(MESSAGE_KIND_ERROR)) {
                    throw new S21NwfBizException(xxMsgId, null);
                }
            }
        }
    }

    // -----------------------------------------------------------
    // Create approval history API
    // -----------------------------------------------------------
    private void createApprovalHistory(NLZC061001TokenObject tokenObj, String apvlHistSrcTpCd, String apvlHistActTpCd, String actOpUser) throws S21NwfBizException {
        String glblCmpyCd = tokenObj.getGlblCmpyCd();
        String trxRefNum = tokenObj.getTrxRefNum();
        String endTimestamp = S21NwfDateUtil.getSystemTimestamp();
        String comments = tokenObj.getComment();
        String physInvtyAdjNm = tokenObj.getPhysInvtyAdjNm();

        NPXC001001CreateApprovalHistoryBean inParam = new NPXC001001CreateApprovalHistoryBean();
        inParam.setGlblCmpyCd(glblCmpyCd);
        inParam.setApvlHistSrcTpCd(apvlHistSrcTpCd);
        inParam.setTrxRefNum(trxRefNum);
        if (ZYPCommonFunc.hasValue(endTimestamp)) {
            inParam.setApvlHistInfoTs(endTimestamp);
        } else {
            inParam.setApvlHistInfoTs(ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
        }
        inParam.setApvlHistActTpCd(apvlHistActTpCd);
        inParam.setApvlByPsnCd(actOpUser);
        String apvlHistTxt = "";
        if (ZYPCommonFunc.hasValue(physInvtyAdjNm)) {
            apvlHistTxt = PI_ADJ_NM_PREFIX + physInvtyAdjNm + ",";
        }
        if (ZYPCommonFunc.hasValue(comments)) {
            apvlHistTxt = apvlHistTxt + COMMENT_PREFIX + comments;
        }
        int index = apvlHistTxt.lastIndexOf(",");

        if (apvlHistTxt.length() > 0 && index == apvlHistTxt.length() - 1) {
            inParam.setApvlHistTxt(apvlHistTxt.substring(0, index));
        } else {
            inParam.setApvlHistTxt(apvlHistTxt);
        }

        String rtrnCd = NPXC001001CreateApprovalHistory.createApprovalHistory(inParam);
        if (!rtrnCd.equals(RTRN_CD_NORMAL)) {
            throw new S21NwfBizException(NPZM0212E, null);
        }
    }

    // -----------------------------------------------------------
    // GetPIMode
    // -----------------------------------------------------------
    private String getPIMode(String glblCmpyCd , String trxRefNum) throws S21NwfBizException  {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(BIND_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        paramMap.put(BIND_PARAM_PHYS_INVTY_NUM, trxRefNum);
        String result = (String) ssmBatchClient.queryObject("getPIMode", paramMap);

        String[] tmp = {TBL_ID_PHYS_INVTY_CTRL + "." + COL_NM_LOC_TP_CD, COL_NM_PHYS_INVTY_NUM, trxRefNum};
        if (!ZYPCommonFunc.hasValue(result) || (!LOC_TP.WAREHOUSE.equals(result) && !LOC_TP.TECHNICIAN.equals(result))) {
            throw new S21NwfBizException(NLCM0049E, tmp);
        }
        return result;
    }

    // -----------------------------------------------------------
    // Check Invty Qty  QC#11464
    // -----------------------------------------------------------
    private boolean checkInvtyQty(NLZC061001TokenObject tokenObj, Map<String, Object> map) throws S21NwfBizException {

        String mdseCd = (String) map.get(DB_RESULT_COL_MDSE_CD);
        String invtyLocCd = (String) map.get(DB_RESULT_COL_WH_CD);
        String stkStsCd = (String) map.get(DB_RESULT_COL_STK_STS_CD);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(BIND_PARAM_GLBL_CMPY_CD, tokenObj.getGlblCmpyCd());
        paramMap.put(BIND_PARAM_MDSE_CD, mdseCd);
        paramMap.put(BIND_PARAM_INVTY_LOC_CD, invtyLocCd);
        paramMap.put(BIND_PARAM_STK_STS_CD, stkStsCd);
        BigDecimal invQty = (BigDecimal) ssmBatchClient.queryObject("getInvtyQty", paramMap);

        //QC#10572 MOD START
        BigDecimal adjVarQty = (BigDecimal) map.get(DB_RESULT_COL_ADJ_VAR_QTY);
//        if (!ZYPCommonFunc.hasValue(invQty) || BigDecimal.ZERO.compareTo(invQty) >= 0) {
        if ((ZYPCommonFunc.hasValue(adjVarQty) && BigDecimal.ZERO.compareTo(adjVarQty) > 0) && (!ZYPCommonFunc.hasValue(invQty) || BigDecimal.ZERO.compareTo(invQty) >= 0)) {
            // QC#10572 MOD END
            setErrorInfo(NLGM0078E, new String[] {invtyLocCd, mdseCd, stkStsCd, LOC_STS.DC_STOCK, ((BigDecimal) map.get(DB_RESULT_COL_ADJ_VAR_QTY)).toPlainString() }, tokenObj, map);
            updatePICountDetailStatus(tokenObj, map, NLZM2458E);
            return false;
        }

        return true;
    }
}
