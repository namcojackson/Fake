/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC125001;

import static com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant.MODE_CANCEL;
import static com.canon.cusa.s21.api.NPZ.NPZC125001.constant.NPZC125001Constant.APPROVE;
import static com.canon.cusa.s21.api.NPZ.NPZC125001.constant.NPZC125001Constant.DATE_TIME_PATTERN;
import static com.canon.cusa.s21.api.NPZ.NPZC125001.constant.NPZC125001Constant.DEF_THRU_DT;
import static com.canon.cusa.s21.api.NPZ.NPZC125001.constant.NPZC125001Constant.DRCT_CPO_CRAT_FLG;
import static com.canon.cusa.s21.api.NPZ.NPZC125001.constant.NPZC125001Constant.EVENT_ID_APPROVAL;
import static com.canon.cusa.s21.api.NPZ.NPZC125001.constant.NPZC125001Constant.MESSAGE_KIND_ERROR;
import static com.canon.cusa.s21.api.NPZ.NPZC125001.constant.NPZC125001Constant.MODE_UPDATE;
import static com.canon.cusa.s21.api.NPZ.NPZC125001.constant.NPZC125001Constant.NPZC1250_FX_SEND_TRGT_TRSMT_METH_TP;
import static com.canon.cusa.s21.api.NPZ.NPZC125001.constant.NPZC125001Constant.NPZC1250_ML_SEND_TRGT;
import static com.canon.cusa.s21.api.NPZ.NPZC125001.constant.NPZC125001Constant.NPZC1250_ML_SEND_TRGT_TRSMT_METH_TP;
import static com.canon.cusa.s21.api.NPZ.NPZC125001.constant.NPZC125001Constant.NPZM0212E;
import static com.canon.cusa.s21.api.NPZ.NPZC125001.constant.NPZC125001Constant.NPZM0258E;
import static com.canon.cusa.s21.api.NPZ.NPZC125001.constant.NPZC125001Constant.NPZM0259E;
import static com.canon.cusa.s21.api.NPZ.NPZC125001.constant.NPZC125001Constant.PROGRAM_ID;
import static com.canon.cusa.s21.api.NPZ.NPZC125001.constant.NPZC125001Constant.REJECT;
import static com.canon.cusa.s21.api.NPZ.NPZC125001.constant.NPZC125001Constant.RTRN_CD_NORMAL;
import static com.canon.cusa.s21.api.NPZ.NPZC125001.constant.NPZC125001Constant.SEND_PO_IF_CRAT_FLG;
import static com.canon.cusa.s21.api.NPZ.NPZC125001.constant.NPZC125001Constant.YMD_SIZE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_PO_TPTMsg;
import business.db.POTMsg;
import business.db.PO_DTLTMsg;
import business.db.PO_DTLTMsgArray;
import business.db.RCV_ASN_VNDTMsg;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
import business.parts.NPZC004001PMsg;
import business.parts.NPZC005001PMsg;
import business.parts.NPZC007001PMsg;
import business.parts.NPZC103001PMsg;
import business.parts.NPZC104001PMsg;
import business.parts.NPZC134001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC004001.NPZC004001;
import com.canon.cusa.s21.api.NPZ.NPZC005001.NPZC005001;
import com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC104001.NPZC104001;
import com.canon.cusa.s21.api.NPZ.NPZC104001.constant.NPZC104001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC130001.NPZC130001TokenObject;
import com.canon.cusa.s21.api.NPZ.NPZC134001.NPZC134001;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreateApprovalHistory;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreateApprovalHistoryBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRSMT_METH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
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
 *<pre>
 * PO/PR Approval from WF API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/05/2016   CITS            M.Ito           Create          N/A
 * 03/21/2016   CSAI            K.Lee           Create          QC#5785
 * 05/04/2016   CITS            K.Lee           Update          QC#5839
 * 05/19/2016   CITS            T.Hakodate      Update          QC#8493
 * 10/24/2017   CITS            S.Katsuma       Update          QC#22028
 * 13/16/2018   CITS            K.Ogino         UPDATE          QC#24788
 * 12/07/2018   Fujitsu         S.Takami        Update          QC#27052
 * 04/13/2020   Fujitsu         T.Ogura         Update          QC#56385
 * 01/20/2023   Hitachi         T.Kuroda        Update          QC#60908
 *</pre>
 */
public class NPZC125001 extends S21ApiCommonBase implements S21NwfApproveEvent<S21NwfContext, S21NwfToken>, S21NwfReassignEvent<S21NwfContext, S21NwfToken>, S21NwfRejectEvent<S21NwfContext, S21NwfToken>,
        S21NwfProcessEndEvent<S21NwfContext, S21NwfToken> {

    // -------------------------------------------------
    // Instance Fields
    // -------------------------------------------------

    /** Approval Date */
    private String apvlDt = null;

    /** Reject Date */
    private String rejectDt = null;

    /** Comments */
    private String comments = null;

    /** Actual Approver */
    private String actApvr = null;

    // 2018/12/07 QC#27052 Add Start
    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Cache Of CTAC_PSN Email Address */
    private HashMap<String, List<Map<String, Object>>> vndCtacMessageToCache = null;

    /** Cache Of Target Transimitt Method Type Code */
    private HashMap<String, String> targetTrsmtMethTpCdCache = null;
    // 2018/12/07 QC#27052 Add End

    /**
     * Constructor
     */
    public NPZC125001() {
        super();

        // 2018/12/07 QC#27052 Add Start
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // 2018/12/07 QC#27052 Add End
    }

    /**
     * S21NwfApproveEvent approve
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfBizException error
     */
    public void approve(String name, S21NwfContext context, S21NwfToken token) throws S21NwfBizException {
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process start [approve]", this);

        // Get TokenObject of WorkFlow
        NPZC130001TokenObject tokenObj = (NPZC130001TokenObject) token.getTokenObj();

        // Approval Date
        this.apvlDt = S21NwfDateUtil.getSystemTimestamp();

        // Comments
        this.comments = token.getTokenObj().getComment();

        // 05/19/2016 CITS T.Hakodate Update QC#8493 START
        // Actual Approver

        String actApvrWk = context.getUserID();

        if (actApvrWk.length() > 8) {
            this.actApvr = actApvrWk.substring(0, 8);
        } else {
            this.actApvr = actApvrWk;
        }

        // 05/19/2016 CITS T.Hakodate Update QC#8493 END

        // Create Approval History
        NPXC001001CreateApprovalHistoryBean inParam = new NPXC001001CreateApprovalHistoryBean();
        inParam.setGlblCmpyCd(tokenObj.getGlblCmpyCd());
        inParam.setApvlHistSrcTpCd(tokenObj.getApvlHistSrcTpCd());
        inParam.setTrxRefNum(tokenObj.getTrxRefNum());
        inParam.setApvlHistInfoTs(this.apvlDt);
        inParam.setApvlHistActTpCd(APVL_HIST_ACT_TP.APPROVED);
        inParam.setApvlByPsnCd(this.actApvr);
        inParam.setApvlHistTxt(this.comments);
        String rtrnCd = NPXC001001CreateApprovalHistory.createApprovalHistory(inParam);

        if (!rtrnCd.equals(RTRN_CD_NORMAL)) {
            throw new S21NwfBizException(NPZM0212E, null);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process end [approve]", this);
    }

    /**
     * S21NwfReassignEvent reassign
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @param fromUser String
     * @param toUser String
     * @param comment String
     * @param actionDate String
     * @throws S21NwfException error
     */
    public void reassign(String name, S21NwfContext context, S21NwfToken token, String fromUser, String toUser, String comment, String actionDate) throws S21NwfException {
        this.reassign(name, context, token, fromUser, toUser, comment);
        return;
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

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process start [reassign]", this);

        // Get TokenObject of WorkFlow
        NPZC130001TokenObject tokenObj = (NPZC130001TokenObject) token.getTokenObj();

        // Create Approval History
        NPXC001001CreateApprovalHistoryBean inParam = new NPXC001001CreateApprovalHistoryBean();
        inParam.setGlblCmpyCd(tokenObj.getGlblCmpyCd());
        inParam.setApvlHistSrcTpCd(tokenObj.getApvlHistSrcTpCd());
        inParam.setTrxRefNum(tokenObj.getTrxRefNum());
        inParam.setApvlHistInfoTs(ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
        inParam.setApvlHistActTpCd(APVL_HIST_ACT_TP.FORWARD);

        String actApvrWk = context.getUserID();
        if (actApvrWk.length() > 8) {
            this.actApvr = actApvrWk.substring(0, 8);
        } else {
            this.actApvr = actApvrWk;
        }

        inParam.setApvlByPsnCd(actApvrWk);
        inParam.setApvlHistTxt(comment);
        String rtrnCd = NPXC001001CreateApprovalHistory.createApprovalHistory(inParam);

        if (!rtrnCd.equals(RTRN_CD_NORMAL)) {
            throw new S21NwfBizException(NPZM0212E, null);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process end [reassign]", this);
    }

    /**
     * S21NwfRejectEvent reject
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfBizException error
     */
    public void reject(String name, S21NwfContext context, S21NwfToken token) throws S21NwfBizException {
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process start [reject]", this);

        // Get TokenObject of WorkFlow
        NPZC130001TokenObject tokenObj = (NPZC130001TokenObject) token.getTokenObj();

        // Reject Date
        this.rejectDt = S21NwfDateUtil.getSystemTimestamp();

        // Comments
        this.comments = token.getTokenObj().getComment();

        // Actual Action user
        String actApvrWk = context.getUserID();

        if (actApvrWk.length() > 8) {
            this.actApvr = actApvrWk.substring(0, 8);
        } else {
            this.actApvr = actApvrWk;
        }

        // Create Approval History
        NPXC001001CreateApprovalHistoryBean inParam = new NPXC001001CreateApprovalHistoryBean();
        inParam.setGlblCmpyCd(tokenObj.getGlblCmpyCd());
        inParam.setApvlHistSrcTpCd(tokenObj.getApvlHistSrcTpCd());
        inParam.setTrxRefNum(tokenObj.getTrxRefNum());
        inParam.setApvlHistInfoTs(this.rejectDt);
        inParam.setApvlHistActTpCd(APVL_HIST_ACT_TP.REJECTED);
        inParam.setApvlByPsnCd(this.actApvr);
        inParam.setApvlHistTxt(this.comments);
        String rtrnCd = NPXC001001CreateApprovalHistory.createApprovalHistory(inParam);

        if (!rtrnCd.equals(RTRN_CD_NORMAL)) {
            throw new S21NwfBizException(NPZM0212E, null);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process end [reject]", this);
    }

    /**
     * S21NwfProcessEndEvent end
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfBizException error
     */
    public void end(String name, S21NwfContext context, S21NwfToken token) throws S21NwfBizException {
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process start [end]", this);

        // Get TokenObject of WorkFlow
        NPZC130001TokenObject tokenObj = (NPZC130001TokenObject) token.getTokenObj();
        if (APPROVE.equals(name)) {
            String actApvrWk = context.getUserID();
            if (actApvrWk.length() > 8) {
                this.actApvr = actApvrWk.substring(0, 8);
            } else {
                this.actApvr = actApvrWk;
            }

            this.apvlDt = S21NwfDateUtil.getSystemTimestamp();
            if (tokenObj.getApvlHistSrcTpCd().equals(APVL_HIST_SRC_TP.TECH_REQUEST) || tokenObj.getApvlHistSrcTpCd().equals(APVL_HIST_SRC_TP.INVENTORY_REQUEST) || tokenObj.getApvlHistSrcTpCd().equals(APVL_HIST_SRC_TP.PO_REQUISITION)) {
                // NPZC1030 - PR Update API
                NPZC103001PMsg npzc103001PMsg = new NPZC103001PMsg();
                ZYPEZDItemValueSetter.setValue(npzc103001PMsg.xxModeCd, MODE_UPDATE);
                ZYPEZDItemValueSetter.setValue(npzc103001PMsg.eventId, EVENT_ID_APPROVAL);
                ZYPEZDItemValueSetter.setValue(npzc103001PMsg.glblCmpyCd, tokenObj.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(npzc103001PMsg.prchReqNum, tokenObj.getTrxRefNum());
                ZYPEZDItemValueSetter.setValue(npzc103001PMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.APPROVED);
                ZYPEZDItemValueSetter.setValue(npzc103001PMsg.prchReqApvlDt, this.apvlDt.substring(0, YMD_SIZE));
                ZYPEZDItemValueSetter.setValue(npzc103001PMsg.prchReqApvlByPsnCd, this.actApvr);
                ZYPEZDItemValueSetter.setValue(npzc103001PMsg.procDt, ZYPDateUtil.getSalesDate());
                NPZC103001 npzc103001 = new NPZC103001();
                npzc103001.execute(npzc103001PMsg, ONBATCH_TYPE.BATCH);

                String msgId = "";
                if (!S21ApiUtil.getXxMsgIdList(npzc103001PMsg).isEmpty()) {
                    for (int i = 0; i < npzc103001PMsg.xxMsgIdList.length(); i++) {
                        msgId = npzc103001PMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                        if (ZYPCommonFunc.hasValue(msgId) && msgId.endsWith(MESSAGE_KIND_ERROR)) {
                            throw new S21NwfBizException(msgId, null);
                        }
                    }
                }
            } else if (tokenObj.getApvlHistSrcTpCd().equals(APVL_HIST_SRC_TP.PO_ENTRY)) {
                // NPZC0040 - PO Status Update API
                NPZC004001PMsg npzc004001PMsg = new NPZC004001PMsg();
                ZYPEZDItemValueSetter.setValue(npzc004001PMsg.glblCmpyCd, tokenObj.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(npzc004001PMsg.poOrdNum, tokenObj.getTrxRefNum());
                npzc004001PMsg.poStsCd.clear();
                npzc004001PMsg.mdseCd.clear();
                npzc004001PMsg.poRcvQty.clear();
                npzc004001PMsg.poOrdDtlLineNum.clear();
                // START 2017/10/24 S.Katsuma QC#22028 ADD
                npzc004001PMsg.poInvQty.clear();
//                ZYPEZDItemValueSetter.setValue(npzc004001PMsg.poInvQty, BigDecimal.ZERO);
                // END 2017/10/24 S.Katsuma QC#22028 ADD
                ZYPEZDItemValueSetter.setValue(npzc004001PMsg.poApvlStsCd, PRCH_REQ_APVL_STS.APPROVED);

                if (this.actApvr.length() > 8) {
                    ZYPEZDItemValueSetter.setValue(npzc004001PMsg.poApvlPsnCd, this.actApvr.substring(0, 8));
                } else {
                    ZYPEZDItemValueSetter.setValue(npzc004001PMsg.poApvlPsnCd, this.actApvr);
                }

                ZYPEZDItemValueSetter.setValue(npzc004001PMsg.poApvlDt, this.apvlDt.substring(0, YMD_SIZE));
                ZYPEZDItemValueSetter.setValue(npzc004001PMsg.poApvlTs, this.apvlDt);
                NPZC004001 npzc004001 = new NPZC004001();
                npzc004001.execute(npzc004001PMsg, ONBATCH_TYPE.BATCH);

                String msgId = "";
                if (!S21ApiUtil.getXxMsgIdList(npzc004001PMsg).isEmpty()) {
                    for (int i = 0; i < npzc004001PMsg.xxMsgIdList.length(); i++) {
                        msgId = npzc004001PMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                        if (ZYPCommonFunc.hasValue(msgId) && msgId.endsWith(MESSAGE_KIND_ERROR)) {
                            throw new S21NwfBizException(msgId, null);
                        }
                    }
                }

                // NPZC1040 - PO Create API by Send PO Mode
                NPZC104001PMsg npzc104001PMsg = new NPZC104001PMsg();
                ZYPEZDItemValueSetter.setValue(npzc104001PMsg.glblCmpyCd, tokenObj.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(npzc104001PMsg.xxModeCd, NPZC104001Constant.MODE_SEND_PO);
                ZYPEZDItemValueSetter.setValue(npzc104001PMsg.procDt, ZYPDateUtil.getCurrentSystemTime("yyyyMMdd"));
                ZYPEZDItemValueSetter.setValue(npzc104001PMsg.xxRqstTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
                ZYPEZDItemValueSetter.setValue(npzc104001PMsg.poOrdNum, tokenObj.getTrxRefNum());
                NPZC104001 npzc104001 = new NPZC104001();
                npzc104001.execute(npzc104001PMsg, ONBATCH_TYPE.BATCH);

                msgId = "";
                if (!S21ApiUtil.getXxMsgIdList(npzc104001PMsg).isEmpty()) {
                    for (int i = 0; i < npzc104001PMsg.xxMsgIdList.length(); i++) {
                        msgId = npzc104001PMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                        if (ZYPCommonFunc.hasValue(msgId) && msgId.endsWith(MESSAGE_KIND_ERROR)) {
                            throw new S21NwfBizException(msgId, null);
                        }
                    }
                }

                String drctCpoCratFlg = ZYPCodeDataUtil.getVarCharConstValue(DRCT_CPO_CRAT_FLG, tokenObj.getGlblCmpyCd());
                if (drctCpoCratFlg == null) {
                    drctCpoCratFlg = ZYPConstant.FLG_OFF_N;
                }

                String sendPoIfCratFlg = ZYPCodeDataUtil.getVarCharConstValue(SEND_PO_IF_CRAT_FLG, tokenObj.getGlblCmpyCd());
                if (sendPoIfCratFlg == null) {
                    sendPoIfCratFlg = ZYPConstant.FLG_OFF_N;
                }

                // QC#14996
                if (ZYPConstant.FLG_ON_Y.equals(sendPoIfCratFlg)) {

                    RCV_ASN_VNDTMsg rcvAsnVnd = getRcvAsnVnd(tokenObj.getGlblCmpyCd(), tokenObj.getHdrAttrb28());
                    DS_PO_TPTMsg dsPoTpTMsg = getDsPoTp(tokenObj.getGlblCmpyCd(), tokenObj.getHdrAttrb11());

                    if (rcvAsnVnd != null && dsPoTpTMsg != null && ZYPConstant.FLG_ON_Y.equals(dsPoTpTMsg.ediSendFlg.getValue())) {

                        // NPZC1340 - Handling direct CPO Creation API
                        NPZC134001PMsg npzc134001PMsg = new NPZC134001PMsg();
                        ZYPEZDItemValueSetter.setValue(npzc134001PMsg.glblCmpyCd, tokenObj.getGlblCmpyCd());
                        ZYPEZDItemValueSetter.setValue(npzc134001PMsg.slsDt, tokenObj.getSlsDt());
                        ZYPEZDItemValueSetter.setValue(npzc134001PMsg.xxProcTpCd, tokenObj.getXxProcTpCd());
                        ZYPEZDItemValueSetter.setValue(npzc134001PMsg.poOrdNum, tokenObj.getTrxRefNum());
                        ZYPEZDItemValueSetter.setValue(npzc134001PMsg.xxdrctCpoCratFlg, drctCpoCratFlg);
                        ZYPEZDItemValueSetter.setValue(npzc134001PMsg.xxsendPoIfCratFlg, sendPoIfCratFlg);
                        NPZC134001 npzc134001 = new NPZC134001();
                        npzc134001.execute(npzc134001PMsg, ONBATCH_TYPE.BATCH);

                        List<String> msgIds = S21ApiUtil.getXxMsgIdList(npzc134001PMsg);

                        for (String msg : msgIds) {

                            // QC#24788
                            if (!msg.endsWith(MESSAGE_KIND_ERROR)) {

                                tokenObj.addMessageInfo(msg, null);
                                token.setTokenObj(tokenObj);

                            } else {

                                throw new S21NwfBizException(msg, null);
                            }

                        }
                    }
                }

            }

            // 2018/12/07 QC#27052 Add Start
            // Prepare for sending eMail
            sendApproveMessage(tokenObj);
            // 2018/12/07 QC#27052 Add End
        } else if (REJECT.equals(name)) {
            if (tokenObj.getApvlHistSrcTpCd().equals(APVL_HIST_SRC_TP.TECH_REQUEST) || tokenObj.getApvlHistSrcTpCd().equals(APVL_HIST_SRC_TP.INVENTORY_REQUEST) || tokenObj.getApvlHistSrcTpCd().equals(APVL_HIST_SRC_TP.PO_REQUISITION)) {
                // NPZC1030 - PR Update API
                NPZC103001PMsg npzc103001PMsg = new NPZC103001PMsg();
                // START 2023/01/20 T.Kuroda [QC#60908, MOD]
                // ZYPEZDItemValueSetter.setValue(npzc103001PMsg.xxModeCd, MODE_UPDATE);
                ZYPEZDItemValueSetter.setValue(npzc103001PMsg.xxModeCd, MODE_CANCEL);
                // END 2023/01/20 T.Kuroda [QC#60908, MOD]
                ZYPEZDItemValueSetter.setValue(npzc103001PMsg.eventId, EVENT_ID_APPROVAL);
                ZYPEZDItemValueSetter.setValue(npzc103001PMsg.glblCmpyCd, tokenObj.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(npzc103001PMsg.prchReqNum, tokenObj.getTrxRefNum());
                ZYPEZDItemValueSetter.setValue(npzc103001PMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.REJECTED);
                ZYPEZDItemValueSetter.setValue(npzc103001PMsg.procDt, ZYPDateUtil.getSalesDate());
                npzc103001PMsg.prchReqApvlDt.clear();
                npzc103001PMsg.prchReqApvlByPsnCd.clear();
                NPZC103001 npzc103001 = new NPZC103001();
                npzc103001.execute(npzc103001PMsg, ONBATCH_TYPE.BATCH);

                String msgId = "";
                if (!S21ApiUtil.getXxMsgIdList(npzc103001PMsg).isEmpty()) {
                    for (int i = 0; i < npzc103001PMsg.xxMsgIdList.length(); i++) {
                        msgId = npzc103001PMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                        if (ZYPCommonFunc.hasValue(msgId) && msgId.endsWith(MESSAGE_KIND_ERROR)) {
                            throw new S21NwfBizException(msgId, null);
                        }
                    }
                }
            } else if (tokenObj.getApvlHistSrcTpCd().equals(APVL_HIST_SRC_TP.PO_ENTRY)) {
                // NPZC0040 - PO Status Update API
                NPZC004001PMsg npzc004001PMsg = new NPZC004001PMsg();
                ZYPEZDItemValueSetter.setValue(npzc004001PMsg.glblCmpyCd, tokenObj.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(npzc004001PMsg.poOrdNum, tokenObj.getTrxRefNum());
                npzc004001PMsg.poStsCd.clear();
                npzc004001PMsg.mdseCd.clear();
                npzc004001PMsg.poRcvQty.clear();
                npzc004001PMsg.poOrdDtlLineNum.clear();
                npzc004001PMsg.poInvQty.clear();
                ZYPEZDItemValueSetter.setValue(npzc004001PMsg.poApvlStsCd, PRCH_REQ_APVL_STS.REJECTED);
                npzc004001PMsg.poApvlPsnCd.clear();
                npzc004001PMsg.poApvlDt.clear();
                npzc004001PMsg.poApvlTs.clear();
                NPZC004001 npzc004001 = new NPZC004001();
                npzc004001.execute(npzc004001PMsg, ONBATCH_TYPE.BATCH);

                String msgId = "";
                if (!S21ApiUtil.getXxMsgIdList(npzc004001PMsg).isEmpty()) {
                    for (int i = 0; i < npzc004001PMsg.xxMsgIdList.length(); i++) {
                        msgId = npzc004001PMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                        if (ZYPCommonFunc.hasValue(msgId) && msgId.endsWith(MESSAGE_KIND_ERROR)) {
                            throw new S21NwfBizException(msgId, null);
                        }
                    }
                }

                // NPZC1040 - PO Create API by Send PO Mode
                NPZC104001PMsg npzc104001PMsg = new NPZC104001PMsg();
                ZYPEZDItemValueSetter.setValue(npzc104001PMsg.glblCmpyCd, tokenObj.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(npzc104001PMsg.xxModeCd, NPZC104001Constant.MODE_SEND_PO);
                ZYPEZDItemValueSetter.setValue(npzc104001PMsg.procDt, ZYPDateUtil.getCurrentSystemTime("yyyyMMdd"));
                ZYPEZDItemValueSetter.setValue(npzc104001PMsg.xxRqstTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
                ZYPEZDItemValueSetter.setValue(npzc104001PMsg.poOrdNum, tokenObj.getTrxRefNum());

                NPZC104001 npzc104001 = new NPZC104001();
                npzc104001.execute(npzc104001PMsg, ONBATCH_TYPE.BATCH);

                msgId = "";
                if (!S21ApiUtil.getXxMsgIdList(npzc104001PMsg).isEmpty()) {
                    for (int i = 0; i < npzc104001PMsg.xxMsgIdList.length(); i++) {
                        msgId = npzc104001PMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                        if (ZYPCommonFunc.hasValue(msgId) && msgId.endsWith(MESSAGE_KIND_ERROR)) {
                            throw new S21NwfBizException(msgId, null);
                        }
                    }
                }
            }
        } else if (S21NwfConst.COND_END_NO_USER.equals(name)) {
            String errMsgId = NPZM0258E;
            if (APVL_HIST_SRC_TP.TECH_REQUEST.equals(tokenObj.getApvlHistSrcTpCd())) {
                errMsgId = NPZM0259E;
            }
            throw new S21NwfBizException(errMsgId, null);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process end [end]", this);
    }

    protected static RCV_ASN_VNDTMsg getRcvAsnVnd(String glblCmpyCd, String poOrdNum) {
        RCV_ASN_VNDTMsg inMsg = new RCV_ASN_VNDTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rcvAsnVndCd, poOrdNum);
        return (RCV_ASN_VNDTMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }

    protected static DS_PO_TPTMsg getDsPoTp(String glblCmpyCd, String dsPoTpCd) {
        DS_PO_TPTMsg inMsg = new DS_PO_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsPoTpCd, dsPoTpCd);
        return (DS_PO_TPTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    // 2018/12/07 QC#27052 Add Start
    private void sendApproveMessage(NPZC130001TokenObject tokenObj) {

        if (!isSendMessageTarget(tokenObj)) {
            return;
        }
        String glblCmpyCd = tokenObj.getGlblCmpyCd();
        String poOrdNum = tokenObj.getTrxRefNum();
        POTMsg poTMsg = getPo(glblCmpyCd, poOrdNum);
        // START 04/13/2020 T.Ogura [QC#56385,MOD]
//        if (poTMsg == null) {
        if (poTMsg == null || ZYPConstant.FLG_ON_Y.equals(poTMsg.poSendFlg.getValue())) {
        // END   04/13/2020 T.Ogura [QC#56385,MOD]
            return;
        }
        PO_DTLTMsg poDtlTMsg = getFirstPoDtl(glblCmpyCd, poOrdNum);
        if (poDtlTMsg == null) {
            return;
        }

        VNDTMsg vndTMsg = null;
        String vndCd = poTMsg.vndCd.getValue();
        if (ZYPCommonFunc.hasValue(vndCd)) {
            vndTMsg = getVnd(glblCmpyCd, vndCd);
        }
        if (vndTMsg == null) {
            return;
        }
        if (!isSendMessageVendeor(vndTMsg)) {
            return;
        }

        // Calling NPZC0070 Create PO Report
        NPZC007001PMsg cratPoRepotApiPMsg = callCreatePoReportApi(tokenObj, poTMsg, poDtlTMsg);
        if (!checkApiErr(cratPoRepotApiPMsg, poOrdNum, vndCd)) {
            return;
        }

        // Calling NPZC0050 API
        NPZC005001PMsg reportApiPMsg = new NPZC005001PMsg();
        ZYPEZDItemValueSetter.setValue(reportApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(reportApiPMsg.userId, poTMsg.poSubmtPsnCd);
        ZYPEZDItemValueSetter.setValue(reportApiPMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(reportApiPMsg.procStartTs, cratPoRepotApiPMsg.rcvRptTs);
        ZYPEZDItemValueSetter.setValue(reportApiPMsg.trsmtMethTpCd, vndTMsg.trsmtMethTpCd);
        ZYPEZDItemValueSetter.setValue(reportApiPMsg.poRptPrintRqstSq, cratPoRepotApiPMsg.poRptPrintRqstSq);

        reportApiPMsg.rptDestId.clear();
        reportApiPMsg.emlFromAddr.clear();
        reportApiPMsg.emlSubjTxt.clear();
        reportApiPMsg.faxSubjNm.clear();

        setPointInfo(reportApiPMsg, vndTMsg);

        // call API
        NPZC005001 purchaseOrderApi = new NPZC005001();
        purchaseOrderApi.execute(reportApiPMsg, ONBATCH_TYPE.ONLINE);

        if (!checkApiErr(reportApiPMsg, poOrdNum, vndCd)) {
            return;
        }

        // START 04/13/2020 T.Ogura [QC#56385,ADD]
        POTMsg updatePoTMsg = getPo(glblCmpyCd, poOrdNum);
        if (updatePoTMsg != null) {
            ZYPEZDItemValueSetter.setValue(updatePoTMsg.poSendFlg, ZYPConstant.FLG_ON_Y);
            S21ApiTBLAccessor.update(updatePoTMsg);
        }
        // END   04/13/2020 T.Ogura [QC#56385,ADD]
    }

    private boolean isSendMessageTarget(NPZC130001TokenObject tokenObj) {

        String glblCmpyCd = tokenObj.getGlblCmpyCd();
        String screenId = tokenObj.getBizScreenId();
        String targetList = ZYPCodeDataUtil.getVarCharConstValue(NPZC1250_ML_SEND_TRGT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(targetList)) {
            targetList = "NPAL1500";
        }
        String[] targetArray = targetList.split(",");
        for (int i = 0; i < targetArray.length; i++) {
            if (S21StringUtil.isEquals(targetArray[i], screenId)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSendMessageVendeor(VNDTMsg vndTMsg) {

        String glblCmpyCd = vndTMsg.glblCmpyCd.getValue();

        boolean isEmailTarget = isMessageTargetEmail(vndTMsg);
        boolean isFaxTarget = isMessageTargetFax(vndTMsg);

        if (!isEmailTarget && !isFaxTarget) {
            return false;
        }
        String dsCtacPntTpCd = null;
        boolean hasReceiverData = false;
        boolean rslt = false;
        if (isEmailTarget) {
            dsCtacPntTpCd = DS_CTAC_PNT_TP.EMAIL_WORK;
            if (ZYPCommonFunc.hasValue(vndTMsg.sendPoEmlAddr)) {
                hasReceiverData  = true;
                rslt = true;
            }
        }
        if (isFaxTarget) {
            dsCtacPntTpCd = DS_CTAC_PNT_TP.FAX_WORK;
            if (ZYPCommonFunc.hasValue(vndTMsg.faxNum)) {
                hasReceiverData  = true;
                rslt = true;
            }
        }
        List<Map<String, Object>> contactMapList = null;
        if (!hasReceiverData) {
            contactMapList = getContactInfo(glblCmpyCd, vndTMsg.vndCd.getValue(), dsCtacPntTpCd);
        }
        if (contactMapList != null && !contactMapList.isEmpty()) {
            rslt = true;
        }
        return rslt;
    }

    private POTMsg getPo(String glblCmpyCd, String poOrdNum) {

        POTMsg poTMsg = new POTMsg();
        ZYPEZDItemValueSetter.setValue(poTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(poTMsg.poOrdNum, poOrdNum);

        return (POTMsg) S21FastTBLAccessor.findByKey(poTMsg);
    }

    private PO_DTLTMsg getFirstPoDtl(String glblCmpyCd, String poOrdNum) {

        PO_DTLTMsg poDtlTMsg = new PO_DTLTMsg();
        poDtlTMsg.setSQLID("002");
        poDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        poDtlTMsg.setConditionValue("poOrdNum01", poOrdNum);
        poDtlTMsg.setConditionValue("poStsCd01", PO_STS.CANCELLED);

        PO_DTLTMsgArray rsltArray = (PO_DTLTMsgArray) EZDTBLAccessor.findByCondition(poDtlTMsg);
        if (rsltArray == null || rsltArray.getValidCount() == 0) {
            return null;
        }
        return rsltArray.no(0);
    }

    private VNDTMsg getVnd(String glblCmpyCd, String vndCd) {

        VNDTMsg vndTMsg = new VNDTMsg();
        vndTMsg.setSQLID("006");
        vndTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        vndTMsg.setConditionValue("vndCd01", vndCd);
        vndTMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        VNDTMsgArray rsltArray = (VNDTMsgArray) EZDTBLAccessor.findByCondition(vndTMsg);
        if (rsltArray == null || rsltArray.getValidCount() == 0) {
            return null;
        }
        return rsltArray.no(0);
    }

    /**
     * Call Create PO Report Api
     * @param rs getPoList ResultSet
     * @param procDt
     * @return NPZC007001PMsg
     */
    private NPZC007001PMsg callCreatePoReportApi(NPZC130001TokenObject tokenObj, POTMsg poTMsg, PO_DTLTMsg poDtlTMsg) {

        // set param
        NPZC007001PMsg cratPoRepotApiPMsg = new NPZC007001PMsg();
        ZYPEZDItemValueSetter.setValue(cratPoRepotApiPMsg.glblCmpyCd, poTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cratPoRepotApiPMsg.whCd, poDtlTMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(cratPoRepotApiPMsg.vndCd, poTMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(cratPoRepotApiPMsg.poOrdNum, poTMsg.poOrdNum);
        ZYPEZDItemValueSetter.setValue(cratPoRepotApiPMsg.usrId, poTMsg.poSubmtPsnCd);
        ZYPEZDItemValueSetter.setValue(cratPoRepotApiPMsg.procDt, tokenObj.getSlsDt());

        // call API
        NPZC007001 createPoReportApi = new NPZC007001();
        createPoReportApi.execute(cratPoRepotApiPMsg, ONBATCH_TYPE.ONLINE);

        return cratPoRepotApiPMsg;
    }

    private boolean checkApiErr(EZDPMsg pMsg, String poOrdNum, String vndCd) {

        boolean result = true;

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            StringBuilder msg = new StringBuilder();
            List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);
            for (String msgId : msgList) {
                msg.append(msgId + ": ");
                msg.append(S21MessageFunc.clspGetMessage(msgId));
                msg.append("\r\n");
            }
            EZDDebugOutput.println(1, PROGRAM_ID + " API Error At NPZC125001 PO Order Num: >>>>> " + poOrdNum, this);
            EZDDebugOutput.println(1, PROGRAM_ID + " API Error At NPZC125001 Vendor Code: >>>>> " + vndCd, this);
            EZDDebugOutput.println(1, PROGRAM_ID + " API Error At NPZC125001 Message: >>>>> " + msg.toString(), this);
            result = false;
        }

        return result;
    }

    private List<Map<String, Object>> getContactInfo(String glblCmpyCd, String vndCd, String dsCtacPntTpCd) {

        if (!ZYPCommonFunc.hasValue(dsCtacPntTpCd)) {
            return null;
        }
        String cacheKey = glblCmpyCd + "-" + vndCd + "-" + dsCtacPntTpCd;
        boolean doSearch = false;
        List<Map<String, Object>> rslt = null;
        if (this.vndCtacMessageToCache == null) {
            this.vndCtacMessageToCache = new HashMap<String, List<Map<String, Object>>>();
            doSearch = true;
        } else {
            rslt = this.vndCtacMessageToCache.get(cacheKey);
            if (rslt == null) {
                doSearch = true;
            }
        }
        if (doSearch) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("defThruDt", DEF_THRU_DT);
            params.put("dsCtacPntTpCd", dsCtacPntTpCd); // DS_CTAC_PNT_TP.EMAIL_WORK
            params.put("glblCmpyCd", glblCmpyCd);
            params.put("vndCd", vndCd);
            params.put("ctacPsnActvFlg", ZYPConstant.FLG_ON_Y);
            params.put("ctacTpCdDI", CTAC_TP.DELIVERY_INSTALL);

            rslt = this.ssmBatchClient.queryObjectList("getContactInfo", params);
            if (rslt != null && !rslt.isEmpty()) {
                this.vndCtacMessageToCache.put(cacheKey, rslt);
            }
        }
        return rslt;
    }

    private boolean isMessageTargetEmail(VNDTMsg vndTMsg) {

        return isMessageTarget(NPZC1250_ML_SEND_TRGT_TRSMT_METH_TP, vndTMsg);
    }

    private boolean isMessageTargetFax(VNDTMsg vndTMsg) {

        return isMessageTarget(NPZC1250_FX_SEND_TRGT_TRSMT_METH_TP, vndTMsg);
    }

    private boolean isMessageTarget(String varCharNm, VNDTMsg vndTMsg) {

        String glblCmpyCd = vndTMsg.glblCmpyCd.getValue();
        String trsmtMethTpCd = vndTMsg.trsmtMethTpCd.getValue();

        String key = glblCmpyCd + "-" + varCharNm;
        String targetVal = null;
        boolean goSearch = false;

        if (this.targetTrsmtMethTpCdCache == null) {
            this.targetTrsmtMethTpCdCache = new HashMap<String, String>();
            goSearch = true;
        } else {
            targetVal = this.targetTrsmtMethTpCdCache.get(key);
            if (targetVal == null) {
                goSearch = true;
            }
        }
        if (goSearch) {
            targetVal = ZYPCodeDataUtil.getVarCharConstValue(varCharNm, glblCmpyCd);
            if (targetVal == null) {
                if (NPZC1250_ML_SEND_TRGT_TRSMT_METH_TP.equals(varCharNm)) {
                    targetVal = TRSMT_METH_TP.EMAIL_PDF;
                } else if (NPZC1250_FX_SEND_TRGT_TRSMT_METH_TP.equals(varCharNm)) {
                    targetVal = TRSMT_METH_TP.FAX;
                }
            }
            if (targetVal != null) {
                this.targetTrsmtMethTpCdCache.put(key, targetVal);
            }
        }

        String[] targetArray = targetVal.split(",");
        for (int i = 0; i < targetArray.length; i++) {
            if (S21StringUtil.isEquals(targetArray[i], trsmtMethTpCd)) {
                return true;
            }
        }
        return false;
    }

    private void setPointInfo(NPZC005001PMsg reportApiPMsg, VNDTMsg vndTMsg) {

        String glblCmpyCd = vndTMsg.glblCmpyCd.getValue();
        String vndCd = vndTMsg.vndCd.getValue();
        if (isMessageTargetEmail(vndTMsg)) {
            if (ZYPCommonFunc.hasValue(vndTMsg.sendPoEmlAddr)) {
                String[] mailAddrArray = vndTMsg.sendPoEmlAddr.getValue().split(",");
                int idx = 0;
                for (; idx < mailAddrArray.length; idx++) {
                    reportApiPMsg.sendPoEmlAddrList.no(idx).emlToAddr.setValue(mailAddrArray[idx]);
                }
                reportApiPMsg.sendPoEmlAddrList.setValidCount(idx);
            } else {
                List<Map<String, Object>> psnMapList = this.getContactInfo(glblCmpyCd, vndCd, DS_CTAC_PNT_TP.EMAIL_WORK);
                if (psnMapList == null || psnMapList.isEmpty()) {
                    return;
                }
                int idx = 0;
                boolean setAddr = false;
                for (Map<String, Object> psnMap : psnMapList) {
                    String emailAddr = (String) psnMap.get("DS_CTAC_PNT_VAL_TXT");
                    if (ZYPCommonFunc.hasValue(emailAddr)) {
                        reportApiPMsg.sendPoEmlAddrList.no(idx).emlToAddr.setValue(emailAddr);
                        setAddr = true;
                        idx++;
                    }
                }
                if (!setAddr) {
                    return;
                } else {
                    reportApiPMsg.sendPoEmlAddrList.setValidCount(idx);
                }
            }
        } else if (isMessageTargetFax(vndTMsg)) {
            boolean setFaxNum = false;
            if (ZYPCommonFunc.hasValue(vndTMsg.faxNum)) {
                String[] faxNumArray = vndTMsg.faxNum.getValue().split(",");
                for (int idx = 0; idx < faxNumArray.length; idx++) {
                    if (ZYPCommonFunc.hasValue(faxNumArray[idx])) {
                        reportApiPMsg.faxNum.setValue(faxNumArray[idx]);
                        setFaxNum = true;
                        break;
                    }
                }
            }
            if (!setFaxNum) {
                List<Map<String, Object>> psnMapList = this.getContactInfo(glblCmpyCd, vndCd, DS_CTAC_PNT_TP.FAX_WORK);
                if (psnMapList == null || psnMapList.isEmpty()) {
                    return;
                }
                for (Map<String, Object> psnMap : psnMapList) {
                    String faxNum = (String) psnMap.get("DS_CTAC_PNT_VAL_TXT");
                    if (ZYPCommonFunc.hasValue(faxNum)) {
                        reportApiPMsg.faxNum.setValue(faxNum);
                        setFaxNum = true;
                        break;
                    }
                }
            }
        }
    }
    // 2018/12/07 QC#27052 Add End
}
