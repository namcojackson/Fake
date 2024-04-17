/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC117001;

import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.DELIVER_TO;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.DEST_RTL_SWH_CD_G;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.EMER_SRC;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.FOUR_DAYS;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.GRP_ID_CLICK_COMMON;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.GRP_ID_NPZC1170;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.ITEM_VALIDATION_FLG;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.KEY_ADD_DAYS;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.KEY_DEF_ADD_TIME;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.KEY_PRCH_REQ_TP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.KEY_RQST_DT_TXT;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.KEY_SHPG_SVC_LVL;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.KEY_SRC_WH;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.KEY_TASK_CHK_FLG;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.MAIL_DATE_FORMAT;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.MAIL_FROM_GROUP_ID;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.MAIL_ITEM_CODE_FORMAT;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.MAIL_ITEM_DESCRIPTIONE_FORMAT;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.MAIL_LINE_SEPARATOR;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.MAIL_PARAM_MESSAGE;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.MAIL_PARAM_TIME;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.MAIL_PARAM_TODAY;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.MAIL_SPACE;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.MAIL_TIME_FORMAT;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.ML_LANG;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.ML_LANG_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.NPZM0206E;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.NPZM0271E;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.NZZM0010E;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.NZZM0012E;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.NPZM0318E;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.ONE_DAY;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.PRIMARY_PICKUP;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.REQ_STATUS;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.SHIPPING_CUT_OFF_END_TIME;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.SHIPPING_CUT_OFF_START_TIME;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.THREE_DAYS;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.TIME_FORMAT;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.TWO_DAYS;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.ZZZM9005W;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.ZZZM9006E;
import static com.canon.cusa.s21.api.NPZ.NPZC117001.constant.NPZC117001Constant.NPZC1170_EXCL_ITEM_TP;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CLICK_TECH_ORD_PRTTMsg;
import business.db.DS_COND_CONSTTMsg;
import business.db.DS_COND_CONSTTMsgArray;
import business.db.MDSETMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.parts.NPZC103001PMsg;
import business.parts.NPZC117001PMsg;
import business.parts.NPZC117001_SearchPartsListPMsg;
import business.parts.NSZC075001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC075001.NSZC075001;
import com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_QLFY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtil;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Tech Order Parts API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/29/2015   Hitachi         T.Harada        Create          
 * 01/04/2016   Fujitsu         S.Nakai         Update          QC#2425
 * 05/31/2016   Hitachi         T.Iwamoto       Update          QC#8686
 * 10/03/2016   Hitachi         K.Yamada        Update          QC#14803
 * 10/21/2016   Hitachi         K.Yamada        Update          QC#15843
 * 2016/10/24   Hitachi         K.Kojima        Update          QC#15483
 * 2016/11/21   Hitachi         A.Kohinata      Update          QC#16054
 * 2016/12/12   Hitachi         K.Kojima        Update          QC#16300
 * 2017/02/21   Hitachi         K.Kojima        Update          QC#16301
 * 2017/10/05   CITS            M.Naito         Update          QC#20990
 * 2017/10/17   CITS            T.Tokutomi      Update          QC#21657
 * 2017/12/12   CITS            K.Ogino         Update          QC#21784
 * 2018/07/24   CITS            T.Tokutomi      Update          QC#27410
 * 2018/10/16   CITS            T.Wada          Update          QC#27440
 * 2018/12/04   CITS            M.Naito         Update          QC#22667
 * 2018/12/17   CITS            T.Tokutomi      Update          QC#29299
 * 2019/01/15   CITS            K.Ogino         Update          QC#29921
 * 2019/07/19   CITS            R.Shimamoto     Update          QC#51760
 * 2019/11/07   CITS            K.Ogino         Update          QC#54586
 * 2019/12/09   CITS            M.Naito         Update          QC#53421
 * 2020/02/18   CITS            T.Wada          Update          QC#55702
 * 2020/03/06   CITS            K.Ogino         Update          QC#55702-1
 * 2020/05/28   CITS            T.Wada          Update          QC#54705
 * 2021/01/14   CITS            K.Ogino         Update          QC#58229
 * 2021/02/08   CITS            K.Ogino         Update          QC#58379
 * 2023/01/16   Hitachi         E.Watabe        Update          QC#60924
 * 2023/06/02   CITS            R.Kurahashi     Update          QC#61128
 * 2023/08/07   Hitachi         T.Kuroda        Update          QC#61648
 * 2023/10/25   Hitachi         T.Kuroda        Update          QC#61494
 * </pre>
 */
public class NPZC117001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** onBatchType */
    private ONBATCH_TYPE onBatchType = null;

    /** glblCmpyCd */
    private String glblCmpyCd = null;

    /** timeFormat */
    private String timeFormat = null;

    /** reqStatusApproved */
    private String reqStatusApproved = null;

    /** reqStatusDenied */
    private String reqStatusDenied = null;

    /** retDeliverToTMsg */
    private DS_COND_CONSTTMsg retDeliverToTMsg = null;
    
    // Start 2023/01/17 E.Watabe [QC#60924,ADD]
    /** excludeItemTypeArray */
    private DS_COND_CONSTTMsgArray excludeItemTypeArray = null;
    // End 2023/01/17 E.Watabe [QC#60924,ADD]

    /** prchRepMap */
    private Map<String, String> prchRepMap = null;

    /**
     * Constructor
     */
    public NPZC117001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Execute API.
     * @param pMsgList List<NPZC117001PMsg>
     * @param onBatTp ONBATCH_TYPE
     */
    public void execute(List<NPZC117001PMsg> pMsgList, final ONBATCH_TYPE onBatTp) {
        for (NPZC117001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatTp);
        }
    }

    /**
     * Execute API.
     * @param param NPZC117001PMsg
     * @param onBatTp ONBATCH_TYPE
     */
    public void execute(NPZC117001PMsg param, final ONBATCH_TYPE onBatTp) {
        this.onBatchType = onBatTp;

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (init(msgMap, param)) {

            doProcess(msgMap, param);
        }

        msgMap.flush();
    }

    private boolean init(S21ApiMessageMap msgMap, NPZC117001PMsg param) {
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgIdWithPrm(NZZM0012E, new String[] {"Global Company Code" });
            return false;
        }
        this.glblCmpyCd = param.glblCmpyCd.getValue();

        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            setValue(param.slsDt, ZYPDateUtil.getSalesDate(param.glblCmpyCd.getValue()));
        }
        return true;
    }

    private void doProcess(S21ApiMessageMap msgMap, NPZC117001PMsg param) {

        // get mode
        DS_COND_CONSTTMsg itemVldTMsg = getDsCondConstTMsg(this.glblCmpyCd, GRP_ID_NPZC1170, ITEM_VALIDATION_FLG);
        if (itemVldTMsg == null) {
            msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[] {"DS_COND_CONST:ITEM_VALIDATION_FLG" });
            return;
        }
        String itemValidation = itemVldTMsg.dsCondConstValTxt_01.getValue();

        // mode:Item Validation
        if (itemValidation.equals(param.vldActCd.getValue())) {
            doPartsItemValidation(msgMap, param);

            // mode:Order Parts
        } else {
            setValue(param.vldActCd, itemVldTMsg.dsCondConstValTxt_02);
            doOrderParts(msgMap, param);

        }
    }

    private void doPartsItemValidation(S21ApiMessageMap msgMap, NPZC117001PMsg param) {
        // Check Input
        if (!checkItemValidation(msgMap, param)) {
            return;
        }

        // Search Result
        List<Map<String, String>> retMDSEList = getMDSEList(param);
        BigDecimal techOrdPrtSq = ZYPOracleSeqAccessor.getNumberBigDecimal("TECH_ORD_PRT_SQ");
        if (retMDSEList.size() == 0) {
            insertClickTechOrdByItemValidation(param, null, null, techOrdPrtSq);
            msgMap.addXxMsgId(ZZZM9005W);
            return;
        }

        int mdseCnt = 0;
        for (Map<String, String> retMDSEDtl : retMDSEList) {
            setValue(param.SearchPartsList.no(mdseCnt).mdseCd_02, retMDSEDtl.get("MDSE_CD"));
            setValue(param.SearchPartsList.no(mdseCnt).mdseDescShortTxt_02, retMDSEDtl.get("MDSE_DESC_SHORT_TXT"));
            mdseCnt++;
            if (!insertClickTechOrdByItemValidation(param, retMDSEDtl.get("MDSE_CD"), retMDSEDtl.get("MDSE_DESC_SHORT_TXT"), techOrdPrtSq)) {
                return;
            }
        }
        param.SearchPartsList.setValidCount(mdseCnt);
    }

    private boolean checkItemValidation(S21ApiMessageMap msgMap, NPZC117001PMsg param) {
        if (!ZYPCommonFunc.hasValue(param.mdseDescShortTxt_01)) {
            msgMap.addXxMsgIdWithPrm(NZZM0012E, new String[] {"Item Number or Item Desc" });
            return false;
        }
        return true;
    }

    private List<Map<String, String>> getMDSEList(NPZC117001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        String mdseSearchKey = "%" + pMsg.mdseDescShortTxt_01.getValue() + "%";
        param.put("mdseSearchKey", mdseSearchKey);
        param.put("limitNum", pMsg.SearchPartsList.length());
        return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getMDSEList", param);
    }

    private boolean insertClickTechOrdByItemValidation(NPZC117001PMsg param, String mdseCd, String mdseDescShortTxt, BigDecimal techOrdPrtSq) {
        CLICK_TECH_ORD_PRTTMsg clickTechOrdPrtTMsg = new CLICK_TECH_ORD_PRTTMsg();
        BigDecimal clickTechOrdPrtPk = ZYPOracleSeqAccessor.getNumberBigDecimal("CLICK_TECH_ORD_PRT_SQ");
        setValue(clickTechOrdPrtTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(clickTechOrdPrtTMsg.clickTechOrdPrtPk, clickTechOrdPrtPk);
        setValue(clickTechOrdPrtTMsg.techOrdPrtSq, techOrdPrtSq);
        setValue(clickTechOrdPrtTMsg.vldActCd, param.vldActCd);
        setValue(clickTechOrdPrtTMsg.mdseCdOrShortTxt, param.mdseDescShortTxt_01);
        setValue(clickTechOrdPrtTMsg.mdseCd, mdseCd);
        setValue(clickTechOrdPrtTMsg.mdseDescShortTxt, mdseDescShortTxt);
        setValue(clickTechOrdPrtTMsg.procStsCd, PROC_STS.IN_COMPLETED);

        S21FastTBLAccessor.insert(clickTechOrdPrtTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(clickTechOrdPrtTMsg.getReturnCode())) {
            S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
            msgMap.addXxMsgId(NPZM0206E);
            return false;
        }
        // START 2017/02/21 K.Kojima [QC#16301,ADD]
        // This API is called directly from Clicksoft that will not control any transactions in S21.
        // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
        if (!ZYPCommonFunc.hasValue(param.xxRqstFlg_NC) || !ZYPConstant.FLG_ON_Y.equals(param.xxRqstFlg_NC.getValue())) {
            EZDConnectionMgr.getInstance().commit();
        }
        // END 2017/02/21 K.Kojima [QC#16301,ADD]
        return true;
    }

    private void doOrderParts(S21ApiMessageMap msgMap, NPZC117001PMsg param) {
        if (!checkPartsOrder(msgMap, param)) {
            setValue(param.prchReqStsNm, this.reqStatusDenied);
            param.prchReqNum.clear();
            insertClickTechOrdByOrderParts(param, this.reqStatusDenied, null);
            return;
        }

        // START 2023/08/07 T.Kuroda [QC#61648, ADD]
        if(!checkSatelliteInventoryAvaliability(msgMap, param)) {
            setValue(param.prchReqStsNm, this.reqStatusDenied); 
            param.prchReqNum.clear();
            insertClickTechOrdByOrderParts(param, this.reqStatusDenied, null);

            // Send error mail
            Map<String, Object> queryParams = new HashMap<String, Object>();
            queryParams.put("glblCmpyCd", glblCmpyCd);
            queryParams.put("rqstTechTocCd", param.techTocCd.getValue());
            String techEmlAddr = (String) this.ssmBatchClient.queryObject("getTechEmlAddr", queryParams);
            if (!ZYPCommonFunc.hasValue(techEmlAddr)) {
                return;
            }

            S21MailAddress toAddr = new S21MailAddress(glblCmpyCd, techEmlAddr);
            List<S21MailAddress> toAddrList = new ArrayList<S21MailAddress>();
            toAddrList.add(toAddr);

            StringBuffer mailMessage = new StringBuffer();
            for (int i = 0; i < param.SearchPartsList.getValidCount(); i++) {
                if (i != 0) {
                    mailMessage.append(MAIL_SPACE);
                    mailMessage.append(MAIL_SPACE);
                }
                mailMessage.append(String.format(MAIL_ITEM_CODE_FORMAT, param.SearchPartsList.no(i).mdseCd_02.getValue()));
                mailMessage.append(MAIL_SPACE);
                mailMessage.append(String.format(MAIL_ITEM_DESCRIPTIONE_FORMAT, param.SearchPartsList.no(i).mdseDescShortTxt_02.getValue()));
                mailMessage.append(MAIL_SPACE);
                mailMessage.append(param.SearchPartsList.no(i).prchReqQty.getValue());
                mailMessage.append(MAIL_LINE_SEPARATOR);
            }

            sendErrMail(toAddrList, mailMessage.toString());

            // This API is called directly from Clicksoft that will not control any transactions in S21.
            // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
            if (!ZYPCommonFunc.hasValue(param.xxRqstFlg_NC) || !ZYPConstant.FLG_ON_Y.equals(param.xxRqstFlg_NC.getValue())) {
                EZDConnectionMgr.getInstance().commit();
            }

            return;
        }
        
        // END 2023/08/07 T.Kuroda [QC#61648, ADD]

        callPrUpdaterApi(msgMap, param);
    }

    private boolean checkPartsOrder(S21ApiMessageMap msgMap, NPZC117001PMsg param) {

        // get ConstData
        if (!getConstDataForOrderPartsMode(msgMap, param)) {
            return false;
        }

        // mod start 2016/10/21 CSA Defect#15483
        // mod start 2016/10/03 CSA Defect#14803
        // lgscOrdRmkTxt: mandatoryCheck
        String prchReqTp = this.prchRepMap.get(KEY_PRCH_REQ_TP_CD);
        if (!PRCH_REQ_TP.PREMIUM_RUSH.equals(prchReqTp)
                && !ZYPCommonFunc.hasValue(param.lgscOrdRmkTxt)) {
            msgMap.addXxMsgIdWithPrm(NZZM0012E, new String[] {"Need by Date & Time" });
        }
        // mod end 2016/10/03 CSA Defect#14803
        // mod end 2016/10/21 CSA Defect#15483

        // shipToCustNm: mandatoryCheck
        // START 2018/12/04 M.Naito [QC#22667,MOD]
        if (!PRCH_REQ_TP.PREMIUM_RUSH.equals(prchReqTp) && !ZYPCommonFunc.hasValue(param.shipToCustNm)) {
//        if (!ZYPCommonFunc.hasValue(param.shipToCustNm)) {
        // END 2018/12/04 M.Naito [QC#22667,MOD]
            msgMap.addXxMsgIdWithPrm(NZZM0012E, new String[] {"Deliver To" });
        }

        // techTocCd: mandatoryCheck
        if (!ZYPCommonFunc.hasValue(param.techTocCd)) {
            msgMap.addXxMsgIdWithPrm(NZZM0012E, new String[] {"Technician Number" });
        }

        // destRtlWhCd: mandatoryCheck
        if (!ZYPCommonFunc.hasValue(param.destRtlWhCd)) {
            msgMap.addXxMsgIdWithPrm(NZZM0012E, new String[] {"Receiving Warehouse" });
        }

        // mdseCd_02: mandatoryCheck
        if (param.SearchPartsList.getValidCount() == 0) {
            msgMap.addXxMsgIdWithPrm(NZZM0012E, new String[] {"Item Number" });
        }

        for (int i = 0; i < param.SearchPartsList.getValidCount(); i++) {

            // mdseCd_02: mandatoryCheck
            if (!ZYPCommonFunc.hasValue(param.SearchPartsList.no(i).mdseCd_02)) {
                msgMap.addXxMsgIdWithPrm(NZZM0012E, new String[] {"Item Number" });
            } else {
                // MDSE Master Exist Check
                if (!existMDSE(param.SearchPartsList.no(i).mdseCd_02.getValue())) {
                    msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[] {"Item Number(" + param.SearchPartsList.no(i).mdseCd_02.getValue() + ")" });
                }
                // QC#55702 Add Start. Rollback
//                if (!checkMdsePrchAvalFlg(param.SearchPartsList.no(i).mdseCd_02.getValue())) {
//                    msgMap.addXxMsgIdWithPrm(NZZM0016E, new String[] {"Item Number(" + param.SearchPartsList.no(i).mdseCd_02.getValue() + ")" });
//                }
                // QC#55702 Add End
            }

            // prchReqQty: mandatoryCheck
            if (!ZYPCommonFunc.hasValue(param.SearchPartsList.no(i).prchReqQty)) {
                msgMap.addXxMsgIdWithPrm(NZZM0012E, new String[] {"Requested Quantity" });
            } else {
                // prchReqQty Minimum Check
                if (param.SearchPartsList.no(i).prchReqQty.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                    msgMap.addXxMsgIdWithPrm(NPZM0271E, new String[] {"Requested Quantity", "1" });
                }
            }
        }

        // Deliver To Check
        String deliverTo = param.shipToCustNm.getValue();
        if (ZYPCommonFunc.hasValue(deliverTo)) {
            if (!deliverTo.equals(this.retDeliverToTMsg.dsCondConstValTxt_01.getValue()) && !deliverTo.equals(this.retDeliverToTMsg.dsCondConstValTxt_02.getValue())) {
                msgMap.addXxMsgIdWithPrm(ZZZM9006E, new String[] {"Deliver To" });
            }
        }

        // START 2016/12/12 K.Kojima [QC#16300,ADD]
        if (!checkCallReceiveFieldRequestAPI(param)) {
            // QC#58229 Add
            // END 2016/12/12 K.Kojima [QC#16300,ADD]
//            // Task# Check :Customer
//            if (ZYPCommonFunc.hasValue(deliverTo) && deliverTo.equals(this.retDeliverToTMsg.dsCondConstValTxt_01.getValue())) {
//                if (!ZYPCommonFunc.hasValue(param.svcTaskNum)) {
//                    msgMap.addXxMsgIdWithPrm(NZZM0012E, new String[] {"Task Number" });
//                }
//            }
//            // Task# Check
//            if (ZYPConstant.FLG_ON_Y.equals(this.prchRepMap.get(KEY_TASK_CHK_FLG))) {
//                if (!ZYPCommonFunc.hasValue(param.svcTaskNum)) {
//                    msgMap.addXxMsgIdWithPrm(NZZM0012E, new String[] {"Task Number" });
//                }
//            }

            // START 2016/12/12 K.Kojima [QC#16300,ADD]
        }
        // END 2016/12/12 K.Kojima [QC#16300,ADD]

        // START 2016/12/12 K.Kojima [QC#16300,ADD]
        // Machine Serial No Check
        if (ZYPCommonFunc.hasValue(param.serNum)) {
            if (!existsSerNum(param.serNum.getValue())) {
                msgMap.addXxMsgIdWithPrm(NZZM0012E, new String[] {"Machine Serial No" });
            }
        }
        // Problem Code Check
        if (ZYPCommonFunc.hasValue(param.xtrnlPblmTpRefTxt)) {
            if (!existsSvcPblmTpCd(param.xtrnlPblmTpRefTxt.getValue())) {
                msgMap.addXxMsgIdWithPrm(NZZM0012E, new String[] {"Problem Code" });
            }
        }
        // Task Type Check
        if (ZYPCommonFunc.hasValue(param.xtrnlCallTpRefTxt)) {
            if (!existsDsSvcCallTpCd(param.xtrnlCallTpRefTxt.getValue())) {
                msgMap.addXxMsgIdWithPrm(NZZM0012E, new String[] {"Task Type" });
            }
        }
        // END 2016/12/12 K.Kojima [QC#16300,ADD]

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private boolean getConstDataForOrderPartsMode(S21ApiMessageMap msgMap, NPZC117001PMsg param) {
        // get request status
        DS_COND_CONSTTMsg reqStatusTMsg = getDsCondConstTMsg(glblCmpyCd, GRP_ID_NPZC1170, REQ_STATUS);
        if (reqStatusTMsg == null) {
            msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[] {"DS_COND_CONST:REQ_STATUS" });
        } else {
            this.reqStatusApproved = reqStatusTMsg.dsCondConstValTxt_01.getValue();
            this.reqStatusDenied = reqStatusTMsg.dsCondConstValTxt_02.getValue();
        }

        // get time format
        DS_COND_CONSTTMsg timeFormatTMsg = getDsCondConstTMsg(glblCmpyCd, GRP_ID_CLICK_COMMON, TIME_FORMAT);
        if (timeFormatTMsg != null) {
            this.timeFormat = timeFormatTMsg.dsCondConstValTxt_01.getValue();
        } else {
            this.timeFormat = "h:mm a";
        }

        // get deliver to
        this.retDeliverToTMsg = getDsCondConstTMsg(glblCmpyCd, GRP_ID_NPZC1170, DELIVER_TO);
        if (this.retDeliverToTMsg == null) {
            msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[] {"DS_COND_CONST:DELIVER_TO" });
        }

        // get PrchReqType
        List<DS_COND_CONSTTMsg> dsCondConstMapList = new ArrayList<DS_COND_CONSTTMsg>();
        // START 2019/12/09 M.Naito [QC#53421,MOD]
        dsCondConstMapList = getDsCondConstTMsgList(glblCmpyCd, GRP_ID_NPZC1170);
        if (dsCondConstMapList == null) {
            msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[] {"DS_COND_CONST:Tech Request Type" });
        }
//        DS_COND_CONSTTMsg retReqTpPreRushTMsg = getDsCondConstTMsg(glblCmpyCd, GRP_ID_NPZC1170, REQ_TP_PRE_RUSH);
//        if (retReqTpPreRushTMsg == null) {
//            msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[] {"DS_COND_CONST:REQ_TP_PRE_RUSH" });
//        } else {
//            dsCondConstMapList.add(retReqTpPreRushTMsg);
//        }
//        DS_COND_CONSTTMsg retReqTpRush1TMsg = getDsCondConstTMsg(glblCmpyCd, GRP_ID_NPZC1170, REQ_TP_RUSH_1);
//        if (retReqTpRush1TMsg == null) {
//            msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[] {"DS_COND_CONST:REQ_TP_RUSH_1" });
//        } else {
//            dsCondConstMapList.add(retReqTpRush1TMsg);
//        }
//        DS_COND_CONSTTMsg retReqTpRush2TMsg = getDsCondConstTMsg(glblCmpyCd, GRP_ID_NPZC1170, REQ_TP_RUSH_2);
//        if (retReqTpRush2TMsg == null) {
//            msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[] {"DS_COND_CONST:REQ_TP_RUSH_2" });
//        } else {
//            dsCondConstMapList.add(retReqTpRush2TMsg);
//        }
//        DS_COND_CONSTTMsg retReqTpRush3TMsg = getDsCondConstTMsg(glblCmpyCd, GRP_ID_NPZC1170, REQ_TP_RUSH_3);
//        if (retReqTpRush3TMsg == null) {
//            msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[] {"DS_COND_CONST:REQ_TP_RUSH_3" });
//        } else {
//            dsCondConstMapList.add(retReqTpRush3TMsg);
//        }
//        DS_COND_CONSTTMsg retReqTpStandard2TMsg = getDsCondConstTMsg(glblCmpyCd, GRP_ID_NPZC1170, REQ_TP_STANDARD_2);
//        if (retReqTpStandard2TMsg == null) {
//            msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[] {"DS_COND_CONST:REQ_TP_STANDARD_2" });
//        } else {
//            dsCondConstMapList.add(retReqTpStandard2TMsg);
//        }
//        DS_COND_CONSTTMsg retReqTpStandard3TMsg = getDsCondConstTMsg(glblCmpyCd, GRP_ID_NPZC1170, REQ_TP_STANDARD_3);
//        if (retReqTpStandard3TMsg == null) {
//            msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[] {"DS_COND_CONST:REQ_TP_STANDARD_3" });
//        } else {
//            dsCondConstMapList.add(retReqTpStandard3TMsg);
//        }
//        // add start 2017/10/05 CSA Defect#20990
//        DS_COND_CONSTTMsg retReqTpStandard4TMsg = getDsCondConstTMsg(glblCmpyCd, GRP_ID_NPZC1170, REQ_TP_STANDARD_4);
//        if (retReqTpStandard4TMsg == null) {
//            msgMap.addXxMsgIdWithPrm(NZZM0010E, new String[] {"DS_COND_CONST:REQ_TP_STANDARD_4" });
//        } else {
//            dsCondConstMapList.add(retReqTpStandard4TMsg);
//        }
        // add end 2017/10/05 CSA Defect#20990
        // END 2019/12/09 M.Naito [QC#53421,MOD]
        
        // Start 2023/01/17 E.Watabe [QC#60924,MOD]
        excludeItemTypeArray = getDsCondConstTMsgArray(glblCmpyCd,NPZC1170_EXCL_ITEM_TP);
        // END 2023/01/17 E.Watabe [QC#60924,MOD]

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }

        // set PrchReqInfo
        setPrchReqInfo(param, dsCondConstMapList);
        return true;
    }
    
    // Start 2023/01/17 E.Watabe [QC#60924,ADD]
    private static DS_COND_CONSTTMsgArray getDsCondConstTMsgArray(String glblCmpyCd, String dsCondConstGrpId) {

        DS_COND_CONSTTMsg inMsg = new DS_COND_CONSTTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsCondConstGrpId01", dsCondConstGrpId);
        inMsg.setSQLID("002");
        DS_COND_CONSTTMsgArray resultMsgArray = (DS_COND_CONSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        return resultMsgArray;
    }
    // End 2023/01/17 E.Watabe [QC#60924,ADD]

    private void setPrchReqInfo(NPZC117001PMsg param, List<DS_COND_CONSTTMsg> dsCondConstMapList) {
        this.prchRepMap = new HashMap<String, String>();

        String prchReqTpNm = "";
        if (ZYPCommonFunc.hasValue(param.prchReqTpNm)) {
            prchReqTpNm = param.prchReqTpNm.getValue();
        }

        int idx = 0;
        int defaultNo = 0;
        for (DS_COND_CONSTTMsg constMsg : dsCondConstMapList) {
            // default line
            // mod start 2016/10/21 CSA Defect#15483
            // mod start 2016/10/03 CSA Defect#14803
            if (ZYPConstant.FLG_ON_1.equals(constMsg.dsCondConstValNum_01.getValue().toString())) {
                defaultNo = idx;
            }
            // mod end 2016/10/03 CSA Defect#14803
            idx++;
            // mod end 2016/10/21 CSA Defect#15483

            if (prchReqTpNm.equals(constMsg.dsCondConstValTxt_01.getValue())) {
                // START 2019/12/09 M.Naito [QC#53421,MOD]
//                if (PRCH_REQ_TP.RUSH.equals(constMsg.dsCondConstValTxt_04.getValue()) && !param.lgscOrdRmkTxt.getValue().equals(constMsg.dsCondConstValTxt_03.getValue())) {
                if (PRCH_REQ_TP.RUSH.equals(constMsg.dsCondConstValTxt_04.getValue()) && !param.lgscOrdRmkTxt.getValue().equals(constMsg.dsCondConstValTxt_08.getValue())) {

                    continue;
                }
                // END 2019/12/09 M.Naito [QC#53421,MOD]
                // add start 2016/10/03 CSA Defect#14803
                if (PRCH_REQ_TP.STANDARD.equals(constMsg.dsCondConstValTxt_04.getValue()) && !param.lgscOrdRmkTxt.getValue().equals(constMsg.dsCondConstValTxt_08.getValue())) {
                    continue;
                }
                // add end 2016/10/03 CSA Defect#14803
                // set map
                this.prchRepMap.put(KEY_ADD_DAYS, constMsg.dsCondConstValTxt_02.getValue());
                this.prchRepMap.put(KEY_PRCH_REQ_TP_CD, constMsg.dsCondConstValTxt_04.getValue());
                this.prchRepMap.put(KEY_SRC_WH, constMsg.dsCondConstValTxt_05.getValue());
                this.prchRepMap.put(KEY_SHPG_SVC_LVL, constMsg.dsCondConstValTxt_06.getValue());
                this.prchRepMap.put(KEY_TASK_CHK_FLG, constMsg.dsCondConstValTxt_07.getValue());
                // add start 2016/10/04 CSA Defect#14803
                this.prchRepMap.put(KEY_RQST_DT_TXT, constMsg.dsCondConstValTxt_03.getValue());
                // add end 2016/10/04 CSA Defect#14803
                // START 2016/10/24 K.Kojima [QC#15483,ADD]
                this.prchRepMap.put(KEY_DEF_ADD_TIME, constMsg.dsCondConstValTxt_09.getValue());
                // END 2016/10/24 K.Kojima [QC#15483,ADD]
                // QC#58229
                if (PRCH_REQ_TP.PREMIUM_RUSH.equals(constMsg.dsCondConstValTxt_04.getValue())) {
                    List<DS_COND_CONSTTMsg> delivOptList = getDsCondConstTMsgList(glblCmpyCd, "NPZC1170_DELIV_OPT");
                    for (DS_COND_CONSTTMsg tMsg : delivOptList) {
                        if (ZYPCommonFunc.hasValue(param.lgscOrdRmkTxt) && param.lgscOrdRmkTxt.getValue().equals((tMsg.dsCondConstValTxt_01.getValue()))) {
                            this.prchRepMap.put(KEY_SHPG_SVC_LVL, tMsg.dsCondConstValTxt_02.getValue());
                            break;
                        }
                    }
                }
            }

        }

        // default set
        if (this.prchRepMap.isEmpty()) {
            DS_COND_CONSTTMsg constMsg = dsCondConstMapList.get(defaultNo);
            this.prchRepMap.put(KEY_ADD_DAYS, constMsg.dsCondConstValTxt_02.getValue());
            this.prchRepMap.put(KEY_PRCH_REQ_TP_CD, constMsg.dsCondConstValTxt_04.getValue());
            this.prchRepMap.put(KEY_SRC_WH, constMsg.dsCondConstValTxt_05.getValue());
            this.prchRepMap.put(KEY_SHPG_SVC_LVL, constMsg.dsCondConstValTxt_06.getValue());
            this.prchRepMap.put(KEY_TASK_CHK_FLG, constMsg.dsCondConstValTxt_07.getValue());
        }
    }

    private void callPrUpdaterApi(S21ApiMessageMap msgMap, NPZC117001PMsg param) {

        // START 2017/02/21 K.Kojima [QC#16301,ADD]
        boolean svcTaskNumSetFlag = false;
        String svcTaskNum = param.svcTaskNum.getValue();
        // END 2017/02/21 K.Kojima [QC#16301,ADD]

        // START 2016/12/12 K.Kojima [QC#16300,ADD]
        if (checkCallReceiveFieldRequestAPI(param)) {
            NSZC075001PMsg apiPMsg0750 = new NSZC075001PMsg();
            createApiParam0750(param, apiPMsg0750);
            NSZC075001 receiveFieldRequestAPI = new NSZC075001();
            receiveFieldRequestAPI.execute(apiPMsg0750, this.onBatchType);
            if (apiPMsg0750.xxMsgIdList.getValidCount() != 0) {
                for (int i = 0; i < apiPMsg0750.xxMsgIdList.getValidCount(); i++) {
                    msgMap.addXxMsgId(apiPMsg0750.xxMsgIdList.no(i).xxMsgId.getValue());
                }
                setValue(param.prchReqStsNm, this.reqStatusDenied);
                param.prchReqNum.clear();
                // START 2017/02/21 K.Kojima [QC#16301,ADD]
                // This API is called directly from Clicksoft that will not control any transactions in S21.
                // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
                if (!ZYPCommonFunc.hasValue(param.xxRqstFlg_NC) || !ZYPConstant.FLG_ON_Y.equals(param.xxRqstFlg_NC.getValue())) {
                    EZDConnectionMgr.getInstance().rollback();
                }
                // END 2017/02/21 K.Kojima [QC#16301,ADD]
                insertClickTechOrdByOrderParts(param, this.reqStatusDenied, null);
                return;
            }
            setValue(param.svcTaskNum, apiPMsg0750.svcTaskNum);
            // START 2017/02/21 K.Kojima [QC#16301,ADD]
            svcTaskNumSetFlag = true;
            // END 2017/02/21 K.Kojima [QC#16301,ADD]
        }
        // END 2016/12/12 K.Kojima [QC#16300,ADD]

        NPZC103001PMsg apiPMsg = new NPZC103001PMsg();
        createApiParam(msgMap, param, apiPMsg);

        NPZC103001 prUpdateApi = new NPZC103001();
        prUpdateApi.execute(apiPMsg, this.onBatchType);
        if (apiPMsg.xxMsgIdList.getValidCount() != 0) {
            // START 2017/02/21 K.Kojima [QC#16301,ADD]
            if (svcTaskNumSetFlag == true) {
                setValue(param.svcTaskNum, svcTaskNum);
            }
            // END 2017/02/21 K.Kojima [QC#16301,ADD]
            for (int i = 0; i < apiPMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(apiPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            setValue(param.prchReqStsNm, this.reqStatusDenied);
            param.prchReqNum.clear();
            // START 2017/02/21 K.Kojima [QC#16301,ADD]
            // This API is called directly from Clicksoft that will not control any transactions in S21.
            // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
            if (!ZYPCommonFunc.hasValue(param.xxRqstFlg_NC) || !ZYPConstant.FLG_ON_Y.equals(param.xxRqstFlg_NC.getValue())) {
                EZDConnectionMgr.getInstance().rollback();
            }
            // END 2017/02/21 K.Kojima [QC#16301,ADD]
            insertClickTechOrdByOrderParts(param, this.reqStatusDenied, null);
            return;
        }

        setValue(param.prchReqStsNm, this.reqStatusApproved);
        setValue(param.prchReqNum, apiPMsg.prchReqNum);
        insertClickTechOrdByOrderParts(param, this.reqStatusApproved, apiPMsg.prchReqNum.getValue());
    }

    private boolean createApiParam(S21ApiMessageMap msgMap, NPZC117001PMsg param, NPZC103001PMsg apiPMsg) {

        setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, NPZC103001Constant.MODE_CREATE);
        setValue(apiPMsg.eventId, NPZC103001Constant.EVENT_SUBMIT);
        setValue(apiPMsg.procDt, param.slsDt);
        setValue(apiPMsg.prchReqRecTpCd, PRCH_REQ_REC_TP.TECH_REQUEST);
        setValue(apiPMsg.prchReqTpCd, this.prchRepMap.get("PRCH_REQ_TP_CD"));
        setValue(apiPMsg.prchReqCratByPsnCd, param.techTocCd);
        setValue(apiPMsg.prchReqRqstByPsnCd, param.techTocCd);
        setValue(apiPMsg.prchReqSrcTpCd, PRCH_REQ_SRC_TP.PHONE);
        Map<String, String> fsrInfo = getFsrInfo(param);
        if (fsrInfo != null) {
            setValue(apiPMsg.fsrNum, fsrInfo.get("FSR_NUM"));
            setValue(apiPMsg.svcTaskNum, fsrInfo.get("SVC_TASK_NUM"));
            setValue(apiPMsg.svcMachSerNum, fsrInfo.get("SER_NUM"));
            // QC#27440 Mod Start
            //setValue(apiPMsg.shipToCustCd, fsrInfo.get("SHIP_TO_CUST_CD"));
            if (ZYPCommonFunc.hasValue(param.shipToCustNm) && PRIMARY_PICKUP.equals(param.shipToCustNm.getValue())) {
                // do nothing
            } else {
                setValue(apiPMsg.shipToCustCd, fsrInfo.get("SHIP_TO_CUST_CD"));
            }
            // QC#27440 Mod End

            // QC#21657
            if(ZYPCommonFunc.hasValue(apiPMsg.shipToCustCd)){
                setValue(apiPMsg.poQlfyCd, PO_QLFY.TECH_REQUEST);
            }
        } else {
            // QC#58379 Add
            if (ZYPCommonFunc.hasValue(param.shipToCustNm) && "CUSTOMER".equals(param.shipToCustNm.getValue())) {
                Map<String, Object> rsSvcMachMstr = null;
                List<Map<String, Object>> machList = getSvcMachMstr(param);
                if (machList.size() == 1) {
                    rsSvcMachMstr = machList.get(0);
                } else if (machList.size() > 1) {
                    machList = getSvcMachMstrBySerNumAndMdlNm(param);
                    if (machList.size() == 1) {
                        rsSvcMachMstr = machList.get(0);
                    } else {
                        msgMap.addXxMsgId("NSAM0128E");
                        setValue(param.prchReqStsNm, this.reqStatusDenied);
                        param.prchReqNum.clear();
                        if (!ZYPCommonFunc.hasValue(param.xxRqstFlg_NC) || !ZYPConstant.FLG_ON_Y.equals(param.xxRqstFlg_NC.getValue())) {
                            EZDConnectionMgr.getInstance().rollback();
                        }
                        return false;
                    }
                }

                setValue(apiPMsg.shipToCustCd, (String) rsSvcMachMstr.get("CUR_LOC_NUM"));
                setValue(apiPMsg.poQlfyCd, PO_QLFY.TECH_REQUEST);
            }
        }
        setValue(apiPMsg.rqstTechTocCd, param.techTocCd);
        // QC#27440 Mod Start. QC#29921 Mod Start.
//        if (ZYPCommonFunc.hasValue(param.shipToCustNm) && PRIMARY_PICKUP.equals(param.shipToCustNm.getValue())) {
//            // do nothing
//        } else {
//            setValue(apiPMsg.ctacPsnNm, param.firstInvtyOrdCmntTxt);
//        }
        // QC:51760 Start
        if ( ZYPCommonFunc.hasValue(param.firstInvtyOrdCmntTxt) ) {

        	String firstInvtyOrdCmntTxt = param.firstInvtyOrdCmntTxt.getValue();
            firstInvtyOrdCmntTxt = firstInvtyOrdCmntTxt.replaceAll("\\r\\n|\\r|\\n|\\t", " ");

            setValue(apiPMsg.ctacPsnNm, firstInvtyOrdCmntTxt);

        } else {

        	setValue(apiPMsg.ctacPsnNm, param.firstInvtyOrdCmntTxt);

        }

        //setValue(apiPMsg.ctacPsnNm, param.firstInvtyOrdCmntTxt);
        // QC:51760 End
        // QC#27440 Mod End. QC#29921 Mod End

        // START 2016/10/24 K.Kojima [QC#15483,MOD]
        // String rqstRcvDt = getRqstRcvDt(param.slsDt.getValue());
        // // mod start 2016/10/03 CSA Defect#14803
        // String rqstRcvTm = getRqstRcvTm(this.prchRepMap.get(KEY_RQST_DT_TXT));
        // // mod end 2016/10/03 CSA Defect#14803
        String rqstRcvDt = null;
        String rqstRcvTm = null;
        if (PRCH_REQ_TP.PREMIUM_RUSH.equals(this.prchRepMap.get(KEY_PRCH_REQ_TP_CD))) {
            // QC#58229
            if (SHPG_SVC_LVL.ASAP.equals(this.prchRepMap.get(KEY_SHPG_SVC_LVL))) {
                Calendar cal = getRqstRcvDtTmForPremiumRush(this.prchRepMap.get(KEY_DEF_ADD_TIME));
                rqstRcvDt = getRqstRcvDtForPremiumRush(cal);
                rqstRcvTm = getRqstRcvTm(getRqstRcvTmForPremiumRush(cal));
            } else if (SHPG_SVC_LVL.CUSTOMER_PICK_UP.equals(this.prchRepMap.get(KEY_SHPG_SVC_LVL))) {
                Calendar cal = getRqstRcvDtTmForPremiumRush(this.prchRepMap.get(KEY_DEF_ADD_TIME));
                rqstRcvDt = getRqstRcvDtForPremiumRush(cal);
                rqstRcvTm = getRqstRcvTm(getRqstRcvTmForPremiumRush(cal));
            } else if (SHPG_SVC_LVL.SCHD_DELIVERY.equals(this.prchRepMap.get(KEY_SHPG_SVC_LVL)) && ZYPCommonFunc.hasValue(param.spclInstnCmntTxt)) {
                String tmpTxt = param.spclInstnCmntTxt.getValue();
                tmpTxt = ZYPDateUtil.DateFormatter(tmpTxt, "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss");
                rqstRcvDt = tmpTxt.substring(0, 8);
                rqstRcvTm = tmpTxt.substring(8, 14);
            } else {
                Calendar cal = getRqstRcvDtTmForPremiumRush(this.prchRepMap.get(KEY_DEF_ADD_TIME));
                rqstRcvDt = getRqstRcvDtForPremiumRush(cal);
                rqstRcvTm = getRqstRcvTm(getRqstRcvTmForPremiumRush(cal));
            }
            
        } else {
            rqstRcvDt = getRqstRcvDt(param.slsDt.getValue());
            rqstRcvTm = getRqstRcvTm(this.prchRepMap.get(KEY_RQST_DT_TXT));
        }
        // END 2016/10/24 K.Kojima [QC#15483,MOD]
        // del start 2016/11/21 CSA Defect#16054
//        Map<String, String> rtlWhInfo = getRtlWhInfo(param);
        // del end 2016/11/21 CSA Defect#16054

        for (int i = 0; i < param.SearchPartsList.getValidCount(); i++) {
            setValue(apiPMsg.prchReqInfo.no(i).rqstRcvDt, rqstRcvDt);
            setValue(apiPMsg.prchReqInfo.no(i).rqstRcvTm, rqstRcvTm);
            setValue(apiPMsg.prchReqInfo.no(i).shpgSvcLvlCd, this.prchRepMap.get(KEY_SHPG_SVC_LVL));

            // add start 2016/11/21 CSA Defect#16054
            Map<String, String> rtlWhInfo = getRtlWhInfo(param, param.SearchPartsList.no(i).mdseCd_02.getValue());
            // add end 2016/11/21 CSA Defect#16054

            if (rtlWhInfo != null) {
                setValue(apiPMsg.prchReqInfo.no(i).carrCd, rtlWhInfo.get("PRF_CARR_CD"));
                setValue(apiPMsg.prchReqInfo.no(i).procrTpCd, rtlWhInfo.get("PROCR_TP_CD"));
                setValue(apiPMsg.prchReqInfo.no(i).srcInvtyLocCd, rtlWhInfo.get("SRC_INVTY_LOC_CD"));
                if (PROCR_TP.SUPPLIER.equals(apiPMsg.prchReqInfo.no(i).procrTpCd.getValue())) {
                    // WH / Supplier
                    setValue(apiPMsg.prchReqInfo.no(i).prntVndCd, rtlWhInfo.get("DEF_SRC_RTL_WH_CD"));
                    // SWH / Site
                    setValue(apiPMsg.prchReqInfo.no(i).vndCd, rtlWhInfo.get("DEF_SRC_RTL_SWH_CD"));
                }
            }
            setValue(apiPMsg.prchReqInfo.no(i).destInvtyLocCd, param.destRtlWhCd.getValue() + DEST_RTL_SWH_CD_G);
            setValue(apiPMsg.prchReqInfo.no(i).mdseCd, param.SearchPartsList.no(i).mdseCd_02);
            setValue(apiPMsg.prchReqInfo.no(i).prchReqQty, param.SearchPartsList.no(i).prchReqQty);
        }
        apiPMsg.prchReqInfo.setValidCount(param.SearchPartsList.getValidCount());

        return true;
    }

    private String getRqstRcvDt(String slsDt) {
        int addDays = 0;
        if (ZYPCommonFunc.hasValue(this.prchRepMap.get(KEY_ADD_DAYS))) {
            addDays = Integer.parseInt(this.prchRepMap.get(KEY_ADD_DAYS));
        }
        // START 2023/10/25 T.Kuroda [QC#61494, DEL]
        //return S21CalendarUtil.addDay(slsDt, addDays);
        // END 2023/10/25 T.Kuroda [QC#61494, DEL]

        // START 2023/10/25 T.Kuroda [QC#61494, ADD]
        try {
            String shpgSvcLvl = null;
            if (ZYPCommonFunc.hasValue(this.prchRepMap.get(KEY_SHPG_SVC_LVL))) {
                shpgSvcLvl = this.prchRepMap.get(KEY_SHPG_SVC_LVL);
            }
            String prchReqTp = this.prchRepMap.get(KEY_PRCH_REQ_TP_CD);

            // Order Creation Day of the week
            int dayOfWeek = ZYPDateUtil.getDayOfWeek(slsDt);

            // Get system time
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = dateFormat.parse(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmm"));
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            int hour = now.get(Calendar.HOUR_OF_DAY);
            int minute = now.get(Calendar.MINUTE);

            Calendar currentTime = Calendar.getInstance();
            currentTime.set(Calendar.HOUR_OF_DAY, hour);
            currentTime.set(Calendar.MINUTE, minute);
            currentTime.set(Calendar.SECOND, 0);
            currentTime.set(Calendar.MILLISECOND, 0);

            // Get shipping cut time
            String shipCutOffStart = ZYPCodeDataUtil.getVarCharConstValue(SHIPPING_CUT_OFF_START_TIME, glblCmpyCd);
            String[] shippingCutOffStart = new String[2];
            shippingCutOffStart[0] = shipCutOffStart.substring(0, 2);
            shippingCutOffStart[1] = shipCutOffStart.substring(2, 4);
            Calendar shippingCutOffStartTime = Calendar.getInstance();
            shippingCutOffStartTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(shippingCutOffStart[0]));
            shippingCutOffStartTime.set(Calendar.MINUTE, Integer.parseInt(shippingCutOffStart[1]));
            shippingCutOffStartTime.set(Calendar.SECOND, 0);
            shippingCutOffStartTime.set(Calendar.MILLISECOND, 0);

            String shipCutOffEnd = ZYPCodeDataUtil.getVarCharConstValue(SHIPPING_CUT_OFF_END_TIME, glblCmpyCd);
            String[] shippingCutOffEnd = new String[2];
            shippingCutOffEnd[0] = shipCutOffEnd.substring(0, 2);
            shippingCutOffEnd[1] = shipCutOffEnd.substring(2, 4);
            Calendar shippingCutOffEndTime = Calendar.getInstance();
            shippingCutOffEndTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(shippingCutOffEnd[0]));
            shippingCutOffEndTime.set(Calendar.MINUTE, Integer.parseInt(shippingCutOffEnd[1]));
            shippingCutOffEndTime.set(Calendar.SECOND, 0);
            shippingCutOffEndTime.set(Calendar.MILLISECOND, 0);

            // Set request receive date
            String  rqstRcvDt = slsDt;

            if(PRCH_REQ_TP.RUSH.equals(prchReqTp)) {
                if(dayOfWeek >= ZYPDateUtil.WEEK_MONDAY && dayOfWeek <= ZYPDateUtil.WEEK_THURSDAY) {

                    if((currentTime.after(shippingCutOffEndTime) && currentTime.before(shippingCutOffStartTime))
                            || currentTime.equals(shippingCutOffStartTime)) {
                        // sales date + 1 day
                        rqstRcvDt = ZYPDateUtil.addDays(slsDt, addDays);
                    } else {
                        rqstRcvDt = ZYPDateUtil.addBusinessDay(glblCmpyCd, slsDt, TWO_DAYS);
                    }

                } else if (dayOfWeek == ZYPDateUtil.WEEK_FRIDAY) {
                    if(SHPG_SVC_LVL.NEXT_DAY_AIR_E_AM.equals(shpgSvcLvl)
                            || SHPG_SVC_LVL.NEXT_DAY_AIR.equals(shpgSvcLvl)
                            || SHPG_SVC_LVL.NEXT_DAY_AIR_SAVER.equals(shpgSvcLvl)) {

                        if((currentTime.after(shippingCutOffEndTime) && currentTime.before(shippingCutOffStartTime))
                                || currentTime.equals(shippingCutOffStartTime)) {
                            // Next Monday
                            rqstRcvDt = ZYPDateUtil.addDays(slsDt, THREE_DAYS);
                        } else {
                            // Next Tuesday
                            rqstRcvDt = ZYPDateUtil.addDays(slsDt, FOUR_DAYS);
                        }

                    } else if (SHPG_SVC_LVL.SATURDAY.equals(shpgSvcLvl)) {

                         if((currentTime.after(shippingCutOffEndTime) && currentTime.before(shippingCutOffStartTime))
                                 || currentTime.equals(shippingCutOffStartTime)) {
                             // Next Saturday
                             rqstRcvDt = ZYPDateUtil.addDays(slsDt, ONE_DAY);
                         } else {
                             // Next Monday
                             rqstRcvDt = ZYPDateUtil.addDays(slsDt, THREE_DAYS);
                         }

                     } else {
                         // sales date + 1 day
                         rqstRcvDt = ZYPDateUtil.addDays(slsDt, addDays);
                     }

                } else {
                    if(SHPG_SVC_LVL.NEXT_DAY_AIR_E_AM.equals(shpgSvcLvl)
                            || SHPG_SVC_LVL.NEXT_DAY_AIR.equals(shpgSvcLvl)
                            || SHPG_SVC_LVL.NEXT_DAY_AIR_SAVER.equals(shpgSvcLvl)) {

                        if(dayOfWeek == ZYPDateUtil.WEEK_SATURDAY) {
                            // Next Tuesday
                            rqstRcvDt = ZYPDateUtil.addDays(slsDt, THREE_DAYS);
                        }else if (dayOfWeek == ZYPDateUtil.WEEK_SUNDAY) {
                            // Next Tuesday
                            rqstRcvDt = ZYPDateUtil.addDays(slsDt, TWO_DAYS);
                        }

                    } else {
                        // sales date + 1 day
                         rqstRcvDt = ZYPDateUtil.addDays(slsDt, addDays);
                     }
                }

            } else if (PRCH_REQ_TP.STANDARD.equals(prchReqTp) || PRCH_REQ_TP.MIN_MAX.equals(prchReqTp)) {

                if(SHPG_SVC_LVL.GROUND_SERVICE.equals(shpgSvcLvl)) {
                    // sales date + 5 days
                    rqstRcvDt = ZYPDateUtil.addDays(slsDt, addDays);

                    if((currentTime.after(shippingCutOffEndTime) && currentTime.before(shippingCutOffStartTime))
                            || currentTime.equals(shippingCutOffStartTime)) {
                        if(ZYPDateUtil.getDayOfWeek(rqstRcvDt) == ZYPDateUtil.WEEK_SATURDAY 
                                || ZYPDateUtil.getDayOfWeek(rqstRcvDt) == ZYPDateUtil.WEEK_SUNDAY) {
                            rqstRcvDt = ZYPDateUtil.addBusinessDay(glblCmpyCd, rqstRcvDt, TWO_DAYS);
                        }
                    } else {
                        rqstRcvDt = ZYPDateUtil.addDays(rqstRcvDt, ONE_DAY);

                        if(ZYPDateUtil.getDayOfWeek(rqstRcvDt) == ZYPDateUtil.WEEK_SATURDAY
                                || ZYPDateUtil.getDayOfWeek(rqstRcvDt) == ZYPDateUtil.WEEK_SUNDAY) {
                            rqstRcvDt = ZYPDateUtil.addBusinessDay(glblCmpyCd, rqstRcvDt, TWO_DAYS);
                        }
                    }
                } else {
                    // sales date + 2 days
                    rqstRcvDt = ZYPDateUtil.addDays(slsDt, addDays);
                }

            } else {
                // sales date + 2 days
                rqstRcvDt = S21CalendarUtil.addDay(slsDt, addDays);
            }

            return rqstRcvDt;

        } catch (Exception e) {
            return S21CalendarUtil.addDay(slsDt, addDays);
        }
        // END 2023/10/25 T.Kuroda [QC#61494, ADD]
    }

    private String getRqstRcvTm(String rqstRcvDtTxt) {

        if (!ZYPCommonFunc.hasValue(rqstRcvDtTxt)) {
            // QC#27410 Update. "HHmmss" => "HHmm"
            return "0000";
        }

        try {
            SimpleDateFormat sfd = new SimpleDateFormat(this.timeFormat, Locale.US);
            Date rqstRcvTm = sfd.parse(rqstRcvDtTxt);
            // QC#27410 Update. "HHmmss" => "HHmm"
            sfd = new SimpleDateFormat("HHmm");
            return sfd.format(rqstRcvTm);
        } catch (ParseException e) {
            // QC#27410 Update. "HHmmss" => "HHmm"
            return "0000";
        }
    }

    private boolean existMDSE(String mdseCd) {
        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        mdseTMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);
        if (mdseTMsg == null) {
            return false;
        }
        // Start 2023/01/17 E.Watabe [QC#60924,MOD]
        for(int i =0; i< excludeItemTypeArray.length(); i++){
            if(mdseTMsg.mdseItemTpCd.getValue().equals(excludeItemTypeArray.no(i).dsCondConstValTxt_01.getValue())){
                return false;
            }
        }
         // End 2023/01/17 E.Watabe [QC#60924,MOD]
        
        return true;

    }

    private DS_COND_CONSTTMsg getDsCondConstTMsg(String gcc, String grpId, String constCd) {
        DS_COND_CONSTTMsg dsCondConstTMsg = new DS_COND_CONSTTMsg();
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.glblCmpyCd, gcc);
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.dsCondConstGrpId, grpId);
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.dsCondConstCd, constCd);
        return (DS_COND_CONSTTMsg) S21ApiTBLAccessor.findByKey(dsCondConstTMsg);
    }

    // START 2019/12/09 M.Naito [QC#53421,ADD]
    private List<DS_COND_CONSTTMsg> getDsCondConstTMsgList(String gcc, String dsCondConstGrpId) {
        List<DS_COND_CONSTTMsg> dsCondConstMapList = new ArrayList<DS_COND_CONSTTMsg>();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsCondConstGrpId", dsCondConstGrpId);
        queryParam.put("dsCondConstPremiumRush", PRCH_REQ_TP.PREMIUM_RUSH);
        queryParam.put("dsCondConstRush", PRCH_REQ_TP.RUSH);
        queryParam.put("dsCondConstStandard", PRCH_REQ_TP.STANDARD);
        List dsCondConstList = this.ssmBatchClient.queryObjectList("getDsCondConst", queryParam);
        for (int i = 0; i < dsCondConstList.size(); i++) {
            DS_COND_CONSTTMsg dsCondConstTMsg = (DS_COND_CONSTTMsg) dsCondConstList.get(i);
            dsCondConstMapList.add(dsCondConstTMsg);
        }
        return dsCondConstMapList;
    }
    // END 2019/12/09 M.Naito [QC#53421,ADD]

    private Map<String, String> getFsrInfo(NPZC117001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.svcTaskNum)) {
            return null;
        }
//        // Primary Pick Up > no data
//        if (pMsg.shipToCustNm.getValue().equals(this.retDeliverToTMsg.dsCondConstValTxt_02.getValue())) {
//            return null;
//        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcTaskNum", pMsg.svcTaskNum.getValue());
        param.put("closed", SVC_TASK_STS.CLOSED);
        param.put("cancelled", SVC_TASK_STS.CANCELLED);
        return (Map<String, String>) this.ssmBatchClient.queryObject("getFsrInfo", param);
    }

    // mod start 2016/11/21 CSA Defect#16054
    private Map<String, String> getRtlWhInfo(NPZC117001PMsg pMsg, String mdseCd) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        // QC#54586
        param.put("destRtlWhCd", pMsg.destRtlWhCd.getValue());
        param.put("slsDt", pMsg.slsDt.getValue());

        Map<String, String> rtlWhInfoMap = (Map<String, String>) this.ssmBatchClient.queryObject("getRtlWhInfo", param);

        String srcWh = this.prchRepMap.get("SRC_WH");
        if (EMER_SRC.equals(srcWh)) {
            if (rtlWhInfoMap != null && ZYPCommonFunc.hasValue(rtlWhInfoMap.get("EMER_SRC_PROCR_TP_CD")) && ZYPCommonFunc.hasValue(rtlWhInfoMap.get("EMER_SRC_INVTY_LOC_CD"))) {
                rtlWhInfoMap.put("PROCR_TP_CD", rtlWhInfoMap.get("EMER_SRC_PROCR_TP_CD"));
                rtlWhInfoMap.put("SRC_INVTY_LOC_CD", rtlWhInfoMap.get("EMER_SRC_INVTY_LOC_CD"));
                return rtlWhInfoMap;
            }
        }

        getProcurementSource(pMsg, mdseCd, rtlWhInfoMap);
        return rtlWhInfoMap;
    }
    // mod end 2016/11/21 CSA Defect#16054

    private boolean insertClickTechOrdByOrderParts(NPZC117001PMsg param, String prchReqStsNm, String prchReqNum) {
        BigDecimal techOrdPrtSq = ZYPOracleSeqAccessor.getNumberBigDecimal("TECH_ORD_PRT_SQ");

        if (param.SearchPartsList.getValidCount() == 0) {
            CLICK_TECH_ORD_PRTTMsg clickTechOrdPrtTMsg = new CLICK_TECH_ORD_PRTTMsg();
            if (!insertClickTechOrdByOrderParts(param, prchReqStsNm, prchReqNum, techOrdPrtSq, clickTechOrdPrtTMsg, null)) {
                return false;
            }
            return true;
        }

        for (int i = 0; i < param.SearchPartsList.getValidCount(); i++) {
            CLICK_TECH_ORD_PRTTMsg clickTechOrdPrtTMsg = new CLICK_TECH_ORD_PRTTMsg();
            if (!insertClickTechOrdByOrderParts(param, prchReqStsNm, prchReqNum, techOrdPrtSq, clickTechOrdPrtTMsg, param.SearchPartsList.no(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean insertClickTechOrdByOrderParts(NPZC117001PMsg param, String prchReqStsNm, String prchReqNum, BigDecimal techOrdPrtSq, CLICK_TECH_ORD_PRTTMsg clickTechOrdPrtTMsg, NPZC117001_SearchPartsListPMsg searchPartsListPMsg) {
        BigDecimal clickTechOrdPrtPk = ZYPOracleSeqAccessor.getNumberBigDecimal("CLICK_TECH_ORD_PRT_SQ");
        setValue(clickTechOrdPrtTMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(clickTechOrdPrtTMsg.clickTechOrdPrtPk, clickTechOrdPrtPk);
        setValue(clickTechOrdPrtTMsg.techOrdPrtSq, techOrdPrtSq);
        setValue(clickTechOrdPrtTMsg.vldActCd, param.vldActCd);
        setValue(clickTechOrdPrtTMsg.prchReqTpNm, param.prchReqTpNm);
        setValue(clickTechOrdPrtTMsg.prchReqTpNm, param.prchReqTpNm);
        // mod start 2016/10/03 CSA Defect#14803
        setValue(clickTechOrdPrtTMsg.lgscOrdRmkTxt, param.lgscOrdRmkTxt);
        // mod end 2016/10/03 CSA Defect#14803
        setValue(clickTechOrdPrtTMsg.shipToCustNm, param.shipToCustNm);
        setValue(clickTechOrdPrtTMsg.firstInvtyOrdCmntTxt, param.firstInvtyOrdCmntTxt);
        setValue(clickTechOrdPrtTMsg.svcTaskNum, param.svcTaskNum);
        setValue(clickTechOrdPrtTMsg.rqstTechTocCd, param.techTocCd);
        setValue(clickTechOrdPrtTMsg.prchReqStsNm, prchReqStsNm);
        setValue(clickTechOrdPrtTMsg.prchReqNum, prchReqNum);
        setValue(clickTechOrdPrtTMsg.destRtlWhCd, param.destRtlWhCd);
        if (searchPartsListPMsg != null) {
            setValue(clickTechOrdPrtTMsg.prchReqQty, searchPartsListPMsg.prchReqQty);
            setValue(clickTechOrdPrtTMsg.mdseCd, searchPartsListPMsg.mdseCd_02);
            setValue(clickTechOrdPrtTMsg.mdseDescShortTxt, searchPartsListPMsg.mdseDescShortTxt_02);
        }
        setValue(clickTechOrdPrtTMsg.procStsCd, PROC_STS.IN_COMPLETED);
        setValue(clickTechOrdPrtTMsg.clickKeyTxt, param.clickKeyTxt.getValue());

        S21FastTBLAccessor.insert(clickTechOrdPrtTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(clickTechOrdPrtTMsg.getReturnCode())) {
            S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
            msgMap.addXxMsgId(NPZM0206E);
            msgMap.flush();
            return false;
        }
        // START 2017/02/21 K.Kojima [QC#16301,ADD]
        // This API is called directly from Clicksoft that will not control any transactions in S21.
        // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
        if (!ZYPCommonFunc.hasValue(param.xxRqstFlg_NC) || !ZYPConstant.FLG_ON_Y.equals(param.xxRqstFlg_NC.getValue())) {
            EZDConnectionMgr.getInstance().commit();
        }
        // END 2017/02/21 K.Kojima [QC#16301,ADD]
        return true;
    }

    // START 2016/10/24 K.Kojima [QC#15483,ADD]
    /**
     * getRqstRcvDtTmForPremiumRush
     * @param dsCondConstValTxt_09 Sring
     * @return Calendar
     */
    private Calendar getRqstRcvDtTmForPremiumRush(String dsCondConstValTxt_09) {
        try {
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
            Date dt = df.parse(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmm"));
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            int currentMinute = cal.get(Calendar.MINUTE);
            int addHour;
            if (dsCondConstValTxt_09 == null) {
                addHour = 0;
            } else {
                addHour = Integer.parseInt(dsCondConstValTxt_09);
            }
            cal.add(Calendar.HOUR, addHour);
            if (currentMinute > 30) {
                cal.set(Calendar.MINUTE, 00);
                cal.add(Calendar.HOUR, 1);
            } else {
                cal.set(Calendar.MINUTE, 30);
            }
            return cal;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * getRqstRcvDtForPremiumRush
     * @param cal Calendar
     * @return String
     */
    private String getRqstRcvDtForPremiumRush(Calendar cal) {
        SimpleDateFormat sfdDate = new SimpleDateFormat("yyyyMMdd", Locale.US);
        return sfdDate.format(cal.getTime());
    }

    /**
     * getRqstRcvTmForPremiumRush
     * @param cal Calendar
     * @return String
     */
    private String getRqstRcvTmForPremiumRush(Calendar cal) {
        SimpleDateFormat sfdTime = new SimpleDateFormat("h:mm a", Locale.US);
        return sfdTime.format(cal.getTime());
    }
    // END 2016/10/24 K.Kojima [QC#15483,ADD]

    // add start 2016/11/21 CSA Defect#16054
    // QC#29299 update method.
    private Map<String, String> getProcurementSource(NPZC117001PMsg param, String mdseCd, Map<String, String> rtlWhInfoMap) {
        // QC#61128 Mod Start
        //NPZC108001PMsg apiPMsg = new NPZC108001PMsg();
        //setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        //setValue(apiPMsg.slsDt, param.slsDt);
        //setValue(apiPMsg.mdseCd, mdseCd);
        //setValue(apiPMsg.invtyLocCd, param.destRtlWhCd.getValue() + DEST_RTL_SWH_CD_G);

        //NPZC108001 api = new NPZC108001();
        //api.execute(apiPMsg, this.onBatchType);
        //if (apiPMsg.xxMsgIdList.getValidCount() == 0) {
        //    if (ZYPCommonFunc.hasValue(apiPMsg.procrTpCd)) {
        //        rtlWhInfoMap.put("PROCR_TP_CD", apiPMsg.procrTpCd.getValue());
        //    }
        //    rtlWhInfoMap.put("SRC_INVTY_LOC_CD", apiPMsg.srcRtlWhCd.getValue() + apiPMsg.srcRtlSwhCd.getValue());
        //    rtlWhInfoMap.put("DEF_SRC_RTL_WH_CD", apiPMsg.srcRtlWhCd.getValue());
        //    rtlWhInfoMap.put("DEF_SRC_RTL_SWH_CD", apiPMsg.srcRtlSwhCd.getValue());
        //}
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("glblCmpyCd", this.glblCmpyCd);
        queryParams.put("invtyLocCd", param.destRtlWhCd.getValue() + DEST_RTL_SWH_CD_G);
        queryParams.put("mdseCd", mdseCd);
        queryParams.put("salesDate", param.slsDt);

        // searches DS Warehouse Info.
        Map<String, String> warehouseInfo = this.queryObject("searchDsWarehouseInfo", queryParams);
        if (!warehouseInfo.isEmpty()) {
            if (ZYPCommonFunc.hasValue(warehouseInfo.get("PROCR_TP_CD"))) {
                rtlWhInfoMap.put("PROCR_TP_CD", warehouseInfo.get("PROCR_TP_CD"));
            }
            rtlWhInfoMap.put("SRC_INVTY_LOC_CD", warehouseInfo.get("SRC_RTL_WH_CD") + warehouseInfo.get("SRC_RTL_SWH_CD"));
            rtlWhInfoMap.put("DEF_SRC_RTL_WH_CD", warehouseInfo.get("SRC_RTL_WH_CD"));
            rtlWhInfoMap.put("DEF_SRC_RTL_SWH_CD", warehouseInfo.get("SRC_RTL_SWH_CD"));
        // If the result is empty, searches DS Merchandise Info.
        } else {
                Map<String, String> mdseInfo = this.queryObject("searchDSMerchandiseInfo", queryParams);
                if (!mdseInfo.isEmpty()) {
                    if (ZYPCommonFunc.hasValue(mdseInfo.get("PROCR_TP_CD"))) {
                        rtlWhInfoMap.put("PROCR_TP_CD", mdseInfo.get("PROCR_TP_CD"));
                    }
                    rtlWhInfoMap.put("SRC_INVTY_LOC_CD", mdseInfo.get("SRC_RTL_WH_CD") + mdseInfo.get("SRC_RTL_SWH_CD"));
                    rtlWhInfoMap.put("DEF_SRC_RTL_WH_CD", mdseInfo.get("SRC_RTL_WH_CD"));
                    rtlWhInfoMap.put("DEF_SRC_RTL_SWH_CD", mdseInfo.get("SRC_RTL_SWH_CD"));
                }
        }
        // QC#61128 Mod End

        return rtlWhInfoMap;
    }
    // add end 2016/11/21 CSA Defect#16054

    // QC#61128 Add Start
    private Map<String, String> queryObject(String statementId, Map<String, Object> queryParams) {
        Object result = this.ssmBatchClient.queryObject(statementId, queryParams);
        if (result != null) {
            return (Map<String, String>) this.ssmBatchClient.queryObject(statementId, queryParams);
        } else {
            return Collections.emptyMap();
        }
    }
    // QC#61128 Add End

    // START 2016/12/12 K.Kojima [QC#16300,ADD]
    /**
     * 
     */
    private boolean checkCallReceiveFieldRequestAPI(NPZC117001PMsg param) {
        if (ZYPCommonFunc.hasValue(param.serNum) && ZYPCommonFunc.hasValue(param.xtrnlPblmTpRefTxt) && ZYPCommonFunc.hasValue(param.xtrnlCallTpRefTxt)) {
            return true;
        }
        return false;
    }

    /**
     * existsSerNum
     * @param serNum
     * @return boolean
     */
    private boolean existsSerNum(String serNum) {
        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("serNum01", serNum);
        SVC_MACH_MSTRTMsgArray tMsgArray = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.length() >= 0) {
            return true;
        }
        return false;
    }

    /**
     * existsSvcPblmTpCd
     * @param xtrnlPblmTpRefTxt String
     * @return boolean
     */
    private boolean existsSvcPblmTpCd(String xtrnlPblmTpRefTxt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("xtrnlPblmTpRefTxt", xtrnlPblmTpRefTxt);
        String svcPblmTpCd = (String) ssmBatchClient.queryObject("getSvcPblmTpCd", params);
        if (svcPblmTpCd != null) {
            return true;
        }
        return false;
    }

    /**
     * existsDsSvcCallTpCd
     * @param xtrnlCallTpRefTxt String
     * @return boolean
     */
    private boolean existsDsSvcCallTpCd(String xtrnlCallTpRefTxt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("xtrnlCallTpRefTxt", xtrnlCallTpRefTxt);
        String dsSvcCallTpCd = (String) ssmBatchClient.queryObject("getDsSvcCallTpCd", params);
        if (dsSvcCallTpCd != null) {
            return true;
        }
        return false;
    }

    /**
     * createApiParam0750
     * @param param NPZC117001PMsg
     * @param apiPMsg NSZC075001PMsg
     */
    private void createApiParam0750(NPZC117001PMsg param, NSZC075001PMsg apiPMsg) {
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, param.slsDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.fldRqstSrcPgmCd, NSZC075001Constant.FLD_RQST_SRC_PGM_PARTS);
        ZYPEZDItemValueSetter.setValue(apiPMsg.serNum, param.serNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xtrnlCallTpRefTxt, param.xtrnlCallTpRefTxt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xtrnlPblmTpRefTxt, param.xtrnlPblmTpRefTxt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.psnCd, param.techTocCd);
        // START 2017/02/21 K.Kojima [QC#16301,ADD]
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxRqstFlg_NC, ZYPConstant.FLG_ON_Y);
        // END 2017/02/21 K.Kojima [QC#16301,ADD]

        // QC54705 Add Start
        ZYPEZDItemValueSetter.setValue(apiPMsg.t_MdlNm, param.t_MdlNm);
        // QC54705 Add End
    }
    // END 2016/12/12 K.Kojima [QC#16300,ADD]

    // QC#55702 Add Start. Rollback
//    /**
//     * checkMdsePrchAvalFlg
//     */
//    private boolean checkMdsePrchAvalFlg(String mdseCd) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", this.glblCmpyCd);
//        params.put("mdseCd", mdseCd);
//        String prchAvalflg = (String) ssmBatchClient.queryObject("getMdsePrchAvalFlg", params);
//        if (ZYPCommonFunc.hasValue(prchAvalflg) && ZYPConstant.FLG_OFF_N.equals(prchAvalflg)) {
//            return false;
//        }
//        return true;

//    }
    // QC#55702 Add End

    // QC#58379 Add
    private List<Map<String, Object>> getSvcMachMstr(NPZC117001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("serNum", pMsg.serNum.getValue());
        param.put("machTpCd", SVC_MACH_TP.MACHINE);
        List<Map<String, Object>> rs = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSvcMachMstr", param);
        return rs;
    }

    private List<Map<String, Object>> getSvcMachMstrBySerNumAndMdlNm(NPZC117001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("serNum", pMsg.serNum.getValue());
        param.put("machTpCd", SVC_MACH_TP.MACHINE);
        param.put("t_MdlNm", pMsg.t_MdlNm.getValue());
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSvcMachMstrBySerNumAndMdlNm", param);
    }
    // QC#58379 End

// START 2023/08/07 T.Kuroda [QC#61648, ADD]
    private boolean checkSatelliteInventoryAvaliability(S21ApiMessageMap msgMap, NPZC117001PMsg param) {
        if (this.prchRepMap.get(KEY_PRCH_REQ_TP_CD).equals(PRCH_REQ_TP.PREMIUM_RUSH)
                && (this.prchRepMap.get(KEY_SHPG_SVC_LVL).equals(SHPG_SVC_LVL.CUSTOMER_PICK_UP)
                        || this.prchRepMap.get(KEY_SHPG_SVC_LVL).equals(SHPG_SVC_LVL.SCHD_DELIVERY))) {
            for (int i = 0; i < param.SearchPartsList.getValidCount(); i++) {
                BigDecimal reqQty = param.SearchPartsList.no(i).prchReqQty.getValue();

                Map<String, String> rtlWhInfo = getRtlWhInfo(param, param.SearchPartsList.no(i).mdseCd_02.getValue());
                String invtyLocCd = rtlWhInfo.get("EMER_SRC_INVTY_LOC_CD");

                BigDecimal openTrOrdQty = null;
                BigDecimal avaQty = null;

                if (ZYPCommonFunc.hasValue(invtyLocCd)) {
                    Map<String, Object> queryParams = new HashMap<String, Object>();
                    queryParams.put("glblCmpyCd", glblCmpyCd);
                    queryParams.put("mdseCd", param.SearchPartsList.no(i).mdseCd_02.getValue());
                    queryParams.put("srcInvtyLocCd", invtyLocCd);
                    queryParams.put("prchReqApvlFlg", ZYPConstant.FLG_ON_Y);
                    queryParams.put("prchReqStsCd", PRCH_REQ_STS.OPEN);
                    queryParams.put("prchReqLineStsCd", new String[] {
                            PRCH_REQ_LINE_STS.OPEN
                            , PRCH_REQ_LINE_STS.AWAITING_SHIPPING
                            , PRCH_REQ_LINE_STS.PARTIALLY_SHIPPED
                            , PRCH_REQ_LINE_STS.PARTIALLY_RECEIVED});
                    queryParams.put("prchReqRelStsCd", new String[] {
                            PRCH_REQ_REL_STS.IN_COMPLETED
                            , PRCH_REQ_REL_STS.ERROR});
                    openTrOrdQty = (BigDecimal) this.ssmBatchClient.queryObject("getOpenTrOrdQty", queryParams);

                    queryParams.put("invtyLocCd", invtyLocCd);
                    queryParams.put("locStsCd", LOC_STS.DC_STOCK);
                    queryParams.put("stkStsCd", STK_STS.GOOD);
                    avaQty = (BigDecimal) this.ssmBatchClient.queryObject("getAvaQty", queryParams);
                }

                if (!ZYPCommonFunc.hasValue(openTrOrdQty) || !ZYPCommonFunc.hasValue(avaQty)
                        || reqQty.add(openTrOrdQty).compareTo(avaQty) == 1) {
                    msgMap.addXxMsgId(NPZM0318E);
                    return false;
                }
            }
        }
        return true;
    }

    private void sendErrMail(List<S21MailAddress> toAddrList, String message) {
        // Mail instance
        S21Mail mail = new S21Mail(glblCmpyCd);

        // Get mail group
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_FROM_GROUP_ID);

        // Set address
        mail.setToAddressList(toAddrList);
        mail.setFromAddress(groupFrom.getMailAddress().get(0));

        // Mail Param Timestamp
        Date execDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        SimpleDateFormat timeFormat = new SimpleDateFormat();
        dateFormat.applyLocalizedPattern(MAIL_DATE_FORMAT);
        timeFormat.applyLocalizedPattern(MAIL_TIME_FORMAT);

        // Mail template instance
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);

        // Set template parameter
        template.setTemplateParameter(MAIL_PARAM_TODAY, dateFormat.format(execDate));
        template.setTemplateParameter(MAIL_PARAM_TIME, timeFormat.format(execDate));
        template.setTemplateParameter(MAIL_PARAM_MESSAGE, message);

        // Set mail subject
        mail.setSubject(template.getSubject(ML_LANG), ML_LANG_CD);
        mail.setMailTemplate(template);

        // Post
        mail.postMail();
    }
// END 2023/08/07 T.Kuroda [QC#61648, ADD]

}
