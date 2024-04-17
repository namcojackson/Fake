/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC190001;

import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.DELY_ADDL_CMNT_TXT_MAX_SIZE;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.DS_ACPO_NUM_SEQ;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM0036E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM0049E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM0188E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM0347E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM0617E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM0619E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM1023E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM1125E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM1132E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM1286E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM1377E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM1379E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM1426E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM1428E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM1446E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM1448E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM1450E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM1514E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM1679E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM1779E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM1912E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM1927E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM2220E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM2239E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM2241E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM2244E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM2245E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM2247E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM2248E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM2254E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.NWZM2255E;
import static com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant.SELL_TO_FIRST_REF_CMNT_TXT_MAX_SIZE;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDPStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.DS_ACPOTMsg;
import business.db.DS_ACPO_TRGT_DTLTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CR_CARDTMsg;
import business.db.DS_CR_CARDTMsgArray;
import business.db.DS_MDLTMsg;
import business.db.DS_ORD_TPTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.MDSETMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.PRC_CATGTMsg;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_SPLY_ORD_TP_RELNTMsg;
import business.db.SVC_SPLY_ORD_TP_RELNTMsgArray;
import business.db.TOCTMsg;
import business.parts.NMZC002001PMsg;
import business.parts.NMZC002001_ContactPointInfoListPMsg;
import business.parts.NMZC260001PMsg;
import business.parts.NMZC260001_defSlsRepListPMsg;
import business.parts.NMZC260001_defSlsRepListPMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_EligibleCheckListPMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_cpoCtacPsnInfoListPMsg;
import business.parts.NWZC150001_cpoDlvyInfoListPMsg;
import business.parts.NWZC150001_cpoSlsCrPMsg;
import business.parts.NWZC150001_priceListPMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;
import business.parts.NWZC180001PMsg;
import business.parts.NWZC190001PMsg;
import business.parts.NWZC190001_ordLineListPMsg;
import business.parts.NWZC190001_ordLineReturnListPMsg;
import business.parts.NWZC203001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC002001.NMZC002001;
import com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC260001.NMZC260001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC190001.constant.NWZC190001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.CovTmplInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetCovTmpl;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Exchange;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmount;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmoutData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangePriceData;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_FUFL_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * My CSA Supply Order Creation API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/02   Fujitsu         A.Kosai         Create          N/A
 * 2017/12/19   Fujitsu         W.Honda         Update          S21_NA#19804(Sol#349)
 * 2017/12/25   Fujitsu         N.Sugiura       Update          QC#23207
 * 2017/12/21   Fujitsu         A.Kosai         Update          S21_NA#20164(Sol#356)
 * 2018/02/08   Fujitsu         A.Kosai         Update          S21_NA#20297(Sol#379)
 * 2018/02/23   Fujitsu         K.Ishizuka      Update          S21_NA#23810
 * 2018/02/26   Fujitsu         Hd.Sugawara     Update          QC#22967
 * 2018/12/14   Fujitsu         K.Kato          Update          QC#29315
 * 2019/10/04   Fujitsu         K.Kato          Update          S21_NA#51372
 * 2022/01/17   Fujitsu         N.Sugiura       Update          S21_NA#59037
 * 
 * </pre>
 */
public class NWZC190001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatType = null;

    /** SSM Batch Client */
    private final S21SsmBatchClient ssmBatClnt;

    /** Sales Date */
    private String slsDt = null;

    /**
     * Constructor
     */
    public NWZC190001() {
        ssmBatClnt = S21SsmBatchClient.getClient(getClass());
    }

    public void execute(final List<NWZC190001PMsg> pMsgList, final ONBATCH_TYPE onBatTp) {
        for (NWZC190001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatTp);
        }
    }

    public void execute(final NWZC190001PMsg pMsg, final ONBATCH_TYPE onBatTp) {
        init(pMsg, onBatTp);
        execute();
        setMsgIdFromMsgIdDetailList(pMsg);
        msgMap.flush();
    }

    private void setMsgIdFromMsgIdDetailList(NWZC190001PMsg pMsg) {
        if (pMsg.xxMsgIdList.getValidCount() < 1 && pMsg.xxMsgIdDetailList.getValidCount() > 0) {
            setErrMsg(pMsg, NWZM1927E);
        }
    }

    private void execute() {

        vldPMsg();
        if (hasErr()) {
            return;
        }

        ParamBean paramBean = getParamBean();
        if (hasErr()) {
            return;
        }

        vldOtherCheck(paramBean);
        if (hasErr()) {
            return;
        }

        setPriceInfo(paramBean);
        if (hasErr()) {
            return;
        }

        createAcpo();
        if (hasErr()) {
            return;
        }

        callContactUpdateApi(paramBean);
        if (hasErr()) {
            return;
        }

        callDsCpoUpdateApi(paramBean);
        if (hasErr()) {
            return;
        }

        callCreditCardApi();
    }

    private void init(NWZC190001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        msgMap = new S21ApiMessageMap(pMsg);

        onBatType = onBatTp;

        slsDt = ZYPDateUtil.getSalesDate();
    }

    private void vldPMsg() {

        vldMandatoryCheck();
        if (hasErr()) {
            return;
        }

        vldMasterCheck();
        if (hasErr()) {
            return;
        }
    }

    private boolean hasErr() {

        NWZC190001PMsg pMsg = (NWZC190001PMsg) msgMap.getPmsg();
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            return true;
        }
        if (pMsg.xxMsgIdDetailList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    private void vldMandatoryCheck() {

        NWZC190001PMsg pMsg = (NWZC190001PMsg) msgMap.getPmsg();
        if (!hasValue(pMsg.glblCmpyCd)) {
            setErrMsg(pMsg, NWZM0188E);
        }

        if (!hasValue(pMsg.shpgSvcLvlCd)) {
            setErrMsg(pMsg, NWZM0049E);
        }

        if (!hasValue(pMsg.dropShipFlg)) {
            setErrMsg(pMsg, NWZM0347E);
        }

        if (!hasValue(pMsg.billToCustAcctCd)) {
            setErrMsg(pMsg, NWZM1377E);
        }

        if (!hasValue(pMsg.billToCustCd)) {
            setErrMsg(pMsg, NWZM0617E);
        }

        if (!hasValue(pMsg.shipToCustAcctCd)) {
            setErrMsg(pMsg, NWZM1379E);
        }

        if (!hasValue(pMsg.shipToCustCd)) {
            setErrMsg(pMsg, NWZM0619E);
        }

        if (!hasValue(pMsg.psnFirstNm)) {
            setErrMsg(pMsg, NWZM2244E);
        }

        if (!hasValue(pMsg.psnLastNm)) {
            setErrMsg(pMsg, NWZM2245E);
        }

        // 2017/12/25 QC#23207 Del Start
        // if (!hasValue(pMsg.telNum)) {
        //     setErrMsg(NWZM2246E);
        // }
        // 2017/12/25 QC#23207 Del End

        if (!hasValue(pMsg.emlAddr)) {
            setErrMsg(pMsg, NWZM2247E);
        }

        for (int i = 0; i < pMsg.ordLineList.getValidCount(); i++) {
            NWZC190001_ordLineListPMsg linePMsg = pMsg.ordLineList.no(i);

            if (!hasValue(linePMsg.dsContrPk)) {
                setDetailErrMsg(pMsg, linePMsg, NWZM2239E);
            }

            if (!hasValue(linePMsg.svcMachMstrPk)) {
                setDetailErrMsg(pMsg, linePMsg, NWZM2248E);
            }

            if (!hasValue(linePMsg.mdseCd_M)) {
                setDetailErrMsg(pMsg, linePMsg, NWZM2241E);
            }
        }
        return;
    }

    private void vldMasterCheck() {

        NWZC190001PMsg pMsg = (NWZC190001PMsg) msgMap.getPmsg();

        if (hasValue(pMsg.frtCondCd)) {
            if (!NWXC150001DsCheck.existsFrtCond(pMsg.glblCmpyCd.getValue(), pMsg.frtCondCd.getValue())) {
                setErrMsg(pMsg, NWZM1426E);
            }
        }

        if (hasValue(pMsg.spclHdlgTpCd)) {
            if (!NWXC150001DsCheck.existsSpclHdlgTp(pMsg.glblCmpyCd.getValue(), pMsg.spclHdlgTpCd.getValue())) {
                setErrMsg(pMsg, NWZM1428E);
            }
        }

        if (hasValue(pMsg.shipToStCd)) {
            if (!NWXC150001DsCheck.existsState(pMsg.glblCmpyCd.getValue(), pMsg.shipToStCd.getValue())) {
                setErrMsg(pMsg, NWZM1446E);
            }
        }

        if (hasValue(pMsg.shipToCtryCd)) {
            if (!NWXC150001DsCheck.existsCtry(pMsg.glblCmpyCd.getValue(), pMsg.shipToCtryCd.getValue())) {
                setErrMsg(pMsg, NWZM1448E);
            }
        }
        return;
    }

    private void vldOtherCheck(ParamBean bean) {

        NWZC190001PMsg pMsg = (NWZC190001PMsg) msgMap.getPmsg();

        if (NWXC150001DsCheck.checkDsOrdTpAndDsOrdCatgRelation(pMsg.glblCmpyCd.getValue(), bean.getDsOrdTpCd(), bean.getDsOrdCatgCd())) {
            setErrMsg(pMsg, NWZM1450E);
        }

        checkBillShipSoldRelation(pMsg, bean);

        if (hasValue(pMsg.frtCondCd) && hasValue(pMsg.shpgSvcLvlCd)) {
            // 2018/12/14 QC#29315 Mod Start
            //if (checkFrtCondSvcLvlRelation(pMsg.glblCmpyCd.getValue(), bean.getLineBizTpCd(), pMsg.frtCondCd.getValue(), pMsg.shpgSvcLvlCd.getValue())) {
            if (NWXC150001DsCheck.checkFrtCondSvcLvlRelation(pMsg.glblCmpyCd.getValue()
                                                           , this.slsDt
                                                           , bean.getDsOrdTpCd()
                                                           , pMsg.frtCondCd.getValue()
                                                           , pMsg.shpgSvcLvlCd.getValue()
                                                           , pMsg.carrSvcLvlCd.getValue()
                                                           , bean.getShipToCustAcctCd()
                                                           , bean.getShipToLocNum())) {
            // 2018/12/14 QC#29315 Mod End
                setErrMsg(pMsg, NWZM1912E);
            }
        }

        return;
    }

    // 2018/02/23 S21_NA#23810 Mod Start
//    private void setErrMsg(String msgId) {
//        msgMap.addXxMsgId(msgId);
//    }
//
//    private void setErrMsg(String msgId, String[] msgPrms) {
//        msgMap.addXxMsgIdWithPrm(msgId, msgPrms);
//    }
    
    /**
     * <pre>
     * Set Error Message
     * </pre>
     * @param pMsg
     * @param msgId
     */
    private void setErrMsg(NWZC190001PMsg pMsg, String msgId) {
        setErrMsg(pMsg, msgId, null);
    }

    /**
     * <pre>
     * Set Error Message
     * </pre>
     * @param pMsg
     * @param msgId
     * @param msgPrms
     */
    private void setErrMsg(NWZC190001PMsg pMsg, String msgId, String[] msgPrms) {
        int cnt = pMsg.xxMsgIdList.getValidCount();
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(cnt).xxMsgId, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(cnt).xxMsgTxt, cutMsg(S21MessageFunc.clspGetMessage(msgId, msgPrms)));
        pMsg.xxMsgIdList.setValidCount(cnt + 1);
    }

    /**
     * <pre>
     * Set Error Message For Detail Line
     * </pre>
     * @param pMsg
     * @param linePMsg
     * @param msgId
     */
    private void setDetailErrMsg(NWZC190001PMsg pMsg, NWZC190001_ordLineListPMsg linePMsg, String msgId) {
        setDetailErrMsg(pMsg, linePMsg, msgId, null);
    }

    /**
     * <pre>
     * Set Error Message For Detail Line
     * </pre>
     * @param pMsg
     * @param linePMsg
     * @param msgId
     * @param msgPrms
     */
    private void setDetailErrMsg(NWZC190001PMsg pMsg, NWZC190001_ordLineListPMsg linePMsg, String msgId, String[] msgPrms) {
        int cnt = pMsg.xxMsgIdDetailList.getValidCount();
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdDetailList.no(cnt).ordSrcRefLineNum, linePMsg.ordSrcRefLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdDetailList.no(cnt).mdseCd, linePMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdDetailList.no(cnt).xxMsgId, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdDetailList.no(cnt).xxMsgTxt, cutMsg(S21MessageFunc.clspGetMessage(msgId, msgPrms)));
        pMsg.xxMsgIdDetailList.setValidCount(cnt + 1);
    }

    private String cutMsg(String msg) {
        if (msg == null) {
            return null;
        }
        if (msg.length() > NWZC190001Constant.MAX_MSG_LEN) {
            return msg.substring(0, NWZC190001Constant.MAX_MSG_LEN);
        }
        return msg;
    }
    // 2018/02/23 S21_NA#23810 Mod End

    private String getSalesDate(NWZC190001PMsg pMsg) {

        if (ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            return pMsg.slsDt.getValue();
        } else {
            return this.slsDt;
        }
    }

    /**
     * <pre>
     * Call Pricing API
     * </pre>
     * @param paramBean
     * @return NWZC157001PMsg
     */
    private NWZC157001PMsg callPricingApiForBasePrice(ParamBean paramBean) {

        NWZC190001PMsg pMsg = (NWZC190001PMsg) msgMap.getPmsg();
        String modeCd = NWZC157001.GET_LINE_PRICE;
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        setHdrParam(prcApiPMsg, modeCd, paramBean);
        setLineParam(prcApiPMsg, modeCd, paramBean);
        if (hasErr()) {
            return prcApiPMsg;
        }

        new NWZC157001().execute(prcApiPMsg, this.onBatType);
        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(pMsg, msgId, msgPrms);
            }
        }

        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            if (S21ApiUtil.isXxMsgId(prcApiPMsg.NWZC157002PMsg.no(i))) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg.NWZC157002PMsg.no(i));
                for (S21ApiMessage msg : msgList) {
                    ParamDetailBean detailBean = getParamDetailBean(paramBean, prcApiPMsg.NWZC157002PMsg.no(i).trxLineNum.getValue(), prcApiPMsg.NWZC157002PMsg.no(i).trxLineSubNum.getValue());
                    NWZC190001_ordLineListPMsg linePMsg = getTarget(pMsg, detailBean.getOrdSrcRefLineNum());
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    setDetailErrMsg(pMsg, linePMsg, msgId, msgPrms);
                }
            }
        }

        return prcApiPMsg;
    }

    private void callContactUpdateApi(ParamBean bean) {

        NWZC190001PMsg pMsg = (NWZC190001PMsg) msgMap.getPmsg();

        if (!callContactUpdateApiForMyCsa(pMsg, bean)) {
            return;
        }

        if (!callContactUpdateApiForOrdCtac(pMsg, bean)) {
            return;
        }
    }

    private boolean callContactUpdateApiForMyCsa(NWZC190001PMsg pMsg, ParamBean bean) {

        NMZC002001PMsg apiPMsg = new NMZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, getSalesDate(pMsg));
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsAcctNum, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.locNum, bean.getShipToLocNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.ctacPsnFirstNm, pMsg.psnFirstNm);
        ZYPEZDItemValueSetter.setValue(apiPMsg.ctacPsnLastNm, pMsg.psnLastNm);
        ZYPEZDItemValueSetter.setValue(apiPMsg.ctacTpCd, CTAC_TP.MYCSA);

        NMZC002001_ContactPointInfoListPMsg ctacPntForTel = apiPMsg.ContactPointInfoList.no(0);
        ZYPEZDItemValueSetter.setValue(ctacPntForTel.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        ZYPEZDItemValueSetter.setValue(ctacPntForTel.dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_WORK);
        ZYPEZDItemValueSetter.setValue(ctacPntForTel.dsCtacPntValTxt, pMsg.telNum);

        NMZC002001_ContactPointInfoListPMsg ctacPntForEml = apiPMsg.ContactPointInfoList.no(1);
        ZYPEZDItemValueSetter.setValue(ctacPntForEml.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        ZYPEZDItemValueSetter.setValue(ctacPntForEml.dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);
        ZYPEZDItemValueSetter.setValue(ctacPntForEml.dsCtacPntValTxt, pMsg.emlAddr);

        apiPMsg.ContactPointInfoList.setValidCount(2);

        if (!callContactUpdateApi(pMsg, apiPMsg)) {
            return false;
        }

        bean.setMyCsaCtacPsnPk(apiPMsg.ctacPsnPk.getValue());

        return true;
    }

    private boolean callContactUpdateApiForOrdCtac(NWZC190001PMsg pMsg, ParamBean bean) {

        NMZC002001PMsg apiPMsg = new NMZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, getSalesDate(pMsg));
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsAcctNum, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.locNum, bean.getShipToLocNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.ctacPsnFirstNm, pMsg.psnFirstNm);
        ZYPEZDItemValueSetter.setValue(apiPMsg.ctacPsnLastNm, pMsg.psnLastNm);
        ZYPEZDItemValueSetter.setValue(apiPMsg.ctacTpCd, CTAC_TP.ORDER_CONTACT);

        NMZC002001_ContactPointInfoListPMsg ctacPntForTel = apiPMsg.ContactPointInfoList.no(0);
        ZYPEZDItemValueSetter.setValue(ctacPntForTel.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        ZYPEZDItemValueSetter.setValue(ctacPntForTel.dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_WORK);
        ZYPEZDItemValueSetter.setValue(ctacPntForTel.dsCtacPntValTxt, pMsg.telNum);

        NMZC002001_ContactPointInfoListPMsg ctacPntForEml = apiPMsg.ContactPointInfoList.no(1);
        ZYPEZDItemValueSetter.setValue(ctacPntForEml.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        ZYPEZDItemValueSetter.setValue(ctacPntForEml.dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);
        ZYPEZDItemValueSetter.setValue(ctacPntForEml.dsCtacPntValTxt, pMsg.emlAddr);

        apiPMsg.ContactPointInfoList.setValidCount(2);

        if(!callContactUpdateApi(pMsg, apiPMsg)) {
            return false;
        }

        bean.setOrdCtacCtacPsnPk(apiPMsg.ctacPsnPk.getValue());

        return true;
    }

    private boolean callContactUpdateApi(NWZC190001PMsg pMsg, NMZC002001PMsg apiPMsg) {

        boolean result = true;
        new NMZC002001().execute(apiPMsg, this.onBatType);

        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(pMsg, msgId, msgPrms);
                result = false;
            }
        }

        return result;
    }

    /**
     * <pre>
     * Call CPO Update API
     * </pre>
     * @param paramBean
     * @return No Error : true
     */
    private boolean callDsCpoUpdateApi(ParamBean paramBean) {

        NWZC190001PMsg pMsg = (NWZC190001PMsg) msgMap.getPmsg();

        NWZC150001PMsg apiPMsg = getApiParam(paramBean);
        final List<NWZC150002PMsg> cpoUpdApiOutMsgList = new ArrayList<NWZC150002PMsg>();
        final List<NWZC150003PMsg> cpoUpdApiOutMsgList03 = new ArrayList<NWZC150003PMsg>();

        new NWZC150001().execute(apiPMsg, cpoUpdApiOutMsgList, cpoUpdApiOutMsgList03, this.onBatType);

        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(pMsg, msgId, msgPrms);
            }
        }

        for (NWZC150002PMsg pMsg02 : cpoUpdApiOutMsgList) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg02);
            for (S21ApiMessage msg : msgList) {
                NWZC190001_ordLineListPMsg linePMsg = getTarget(pMsg, paramBean, pMsg02);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setDetailErrMsg(pMsg, linePMsg, msgId, msgPrms);
            }

        }
        if (hasErr()) {
            return false;
        }
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, apiPMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdTs, apiPMsg.cpoOrdTs);

        CPO_DTLTMsgArray cpoDtlArray = getDsCpoDtl(pMsg.glblCmpyCd.getValue(), apiPMsg.cpoOrdNum.getValue());

        int cnt = 0;
        for (NWZC150002PMsg pMsg02 : cpoUpdApiOutMsgList) {
            CPO_DTLTMsg dtlTMsg = null;
            for (int i = 0; i < cpoDtlArray.getValidCount(); i++) {
                CPO_DTLTMsg targetDtlTMsg = cpoDtlArray.no(i);
                if (targetDtlTMsg.cpoDtlLineNum.getValue().equals(pMsg02.cpoDtlLineNum.getValue()) && targetDtlTMsg.cpoDtlLineSubNum.getValue().equals(pMsg02.cpoDtlLineSubNum.getValue())) {
                    dtlTMsg = targetDtlTMsg;
                    break;
                }
            }
            if (dtlTMsg == null) {
                continue;
            }
            NWZC190001_ordLineListPMsg linePMsg = getTarget(pMsg, paramBean, pMsg02);
            NWZC190001_ordLineReturnListPMsg lineRtnPMsg = pMsg.ordLineReturnList.no(cnt);

            ZYPEZDItemValueSetter.setValue(lineRtnPMsg.ordSrcRefLineNum, linePMsg.ordSrcRefLineNum);
            ZYPEZDItemValueSetter.setValue(lineRtnPMsg.dplyLineRefNum_LN, dtlTMsg.cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(lineRtnPMsg.dplyLineRefNum_SN, dtlTMsg.cpoDtlLineSubNum);
            cnt++;
        }
        pMsg.ordLineReturnList.setValidCount(cnt);

        return true;
    }

    private void setPrcElement(NWZC157001PMsg basePricePMsg, NWZC157001PMsg prcApiPMsg) {
        int cnt = prcApiPMsg.NWZC157002PMsg.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NWZC157002PMsg nwzc157002PMsg = prcApiPMsg.NWZC157002PMsg.no(i);

            int xCnt = basePricePMsg.NWZC157002PMsg.getValidCount();
            NWZC157002PMsg baseNWZC157002PMsg = null;
            for (int j = 0; j < xCnt; j++) {
                if (nwzc157002PMsg.trxLineNum.getValue().equals(basePricePMsg.NWZC157002PMsg.no(j).trxLineNum.getValue()) && nwzc157002PMsg.trxLineSubNum.getValue().equals(basePricePMsg.NWZC157002PMsg.no(j).trxLineSubNum.getValue())) {
                    baseNWZC157002PMsg = basePricePMsg.NWZC157002PMsg.no(j);
                    break;
                }
            }
            if (baseNWZC157002PMsg == null) {
                continue;
            }

            int validCnt = baseNWZC157002PMsg.NWZC157003PMsg.getValidCount();
            for (int k = 0; k < validCnt; k++) {
                NWZC157003PMsg basePrcElementPMsg = baseNWZC157002PMsg.NWZC157003PMsg.no(k);
                NWZC157003PMsg prcElementPMsg = nwzc157002PMsg.NWZC157003PMsg.no(k);
                EZDMsg.copy(basePrcElementPMsg, null, prcElementPMsg, null);
            }
            nwzc157002PMsg.NWZC157003PMsg.setValidCount(validCnt);
        }
    }

    private NWZC150001PMsg getApiParam(ParamBean paramBean) {
        NWZC190001PMsg pMsg = (NWZC190001PMsg) msgMap.getPmsg();

        NWZC150001PMsg apiPMsg = new NWZC150001PMsg();

        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NWZC150001Constant.MODE_SUBMIT);
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, getSalesDate(pMsg));
        ZYPEZDItemValueSetter.setValue(apiPMsg.usrId, NWZC190001Constant.BIZ_ID);
        ZYPEZDItemValueSetter.setValue(apiPMsg.bizAppId, NWZC190001Constant.BIZ_ID);

        ZYPEZDItemValueSetter.setValue(apiPMsg.cpoOrdNum, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.cpoOrdTpCd, paramBean.getCpoOrdTpCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsOrdCatgCd, paramBean.getDsOrdCatgCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsOrdTpCd, paramBean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsOrdRsnCd, paramBean.getDsOrdRsnCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.custIssPoNum, pMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.custIssPoDt, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.sysSrcCd, SYS_SRC.S21_ORDER);
        ZYPEZDItemValueSetter.setValue(apiPMsg.cpoSrcTpCd, CPO_SRC_TP.SUPPLY_RELEASE_SCREEN);
        ZYPEZDItemValueSetter.setValue(apiPMsg.ordFuflLvlCd, ORD_FUFL_LVL.UNRESTRICTED);
        ZYPEZDItemValueSetter.setValue(apiPMsg.billToCustAcctCd, pMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.billToCustCd, pMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.shipToCustAcctCd, pMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.sellToCustCd, paramBean.getBillToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.soldToCustLocCd, paramBean.getBillToCustCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.adminPsnCd, NWZC190001Constant.BIZ_ID);
        ZYPEZDItemValueSetter.setValue(apiPMsg.addRddDt, getSalesDate(pMsg));
        ZYPEZDItemValueSetter.setValue(apiPMsg.addRsdDt, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.addShpgSvcLvlCd, pMsg.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.addDropShipFlg, pMsg.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToCustCd, paramBean.getShipToCustCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToLocNm, paramBean.getShipToLocNm());
        // 2017/12/21 S21_NA#20164(Sol#356) Mod Start
//        ZYPEZDItemValueSetter.setValue(apiPMsg.sellToFirstRefCmntTxt, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.sellToFirstRefCmntTxt, getShpgCmntTxt(pMsg, SELL_TO_FIRST_REF_CMNT_TXT_MAX_SIZE));
        // 2017/12/21 S21_NA#20164(Sol#356) Mod End

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.dropShipFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToAddlLocNm, pMsg.shipToAddlLocNm.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToFirstLineAddr, pMsg.shipToFirstLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToScdLineAddr, pMsg.shipToScdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToThirdLineAddr, pMsg.shipToThirdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToFrthLineAddr, pMsg.shipToFrthLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToCtyAddr, pMsg.shipToCtyAddr.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToStCd, pMsg.shipToStCd.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToProvNm, pMsg.shipToProvNm.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToPostCd, pMsg.shipToPostCd.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToCtryCd, pMsg.shipToCtryCd.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToCntyNm, pMsg.shipToCntyNm.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipTo01RefCmntTxt, "");
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipTo02RefCmntTxt, "");
        } else {
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToAddlLocNm, paramBean.getShipToAddlLocNm());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToFirstLineAddr, paramBean.getShipToFirstLineAddr());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToScdLineAddr, paramBean.getShipToScdLineAddr());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToThirdLineAddr, paramBean.getShipToThirdLineAddr());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToFrthLineAddr, paramBean.getShipToFrthLineAddr());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToCtyAddr, paramBean.getShipToCtyAddr());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToStCd, paramBean.getShipToStCd());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToProvNm, paramBean.getShipToProvNm());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToPostCd, paramBean.getShipToPostCd());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToCtryCd, paramBean.getShipToCtryCd());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToCntyNm, paramBean.getShipToCntyNm());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipTo01RefCmntTxt, paramBean.getShipToFirstRefCmntTxt());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipTo02RefCmntTxt, paramBean.getShipToScdRefCmntTxt());
        }

        ZYPEZDItemValueSetter.setValue(apiPMsg.addPmtTermCashDiscCd, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.carrAcctNum, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.ordSgnDt, getSalesDate(pMsg));

        ZYPEZDItemValueSetter.setValue(apiPMsg.slsRepCd, paramBean.getSlsRepOrSlsTeamTocCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.prcBaseDt, getSalesDate(pMsg));
        ZYPEZDItemValueSetter.setValue(apiPMsg.prcCalcDt, getSalesDate(pMsg));
        apiPMsg.negoDealAmt.clear();
        ZYPEZDItemValueSetter.setValue(apiPMsg.prcCatgCd, paramBean.getPrcCatgCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.flPrcListCd, paramBean.getDefPrcCatgCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.csmpContrNum, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.prcContrNum, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.aquNum, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.ordSrcImptDt, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.ordSrcRefNum, pMsg.ordSrcRefNum);
        apiPMsg.loanPerDaysAot.clear();
        ZYPEZDItemValueSetter.setValue(apiPMsg.leaseCmpyPoNum, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.leasePrchOptCd, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.leaseTermCd, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.leasePmtFreqCd, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.ordLogTpCd, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.dlrRefNum, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.frtCondCd, pMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.carrSvcLvlCd, pMsg.carrSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.spclHdlgTpCd, pMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.prePmtChkNum, "");
        apiPMsg.prePmtAmt.clear();
        ZYPEZDItemValueSetter.setValue(apiPMsg.prePmtTpCd, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.crRebilRsnCatgCd, "");

        if (ZYPCommonFunc.hasValue(pMsg.crCardCustRefNum)) {
            DS_CR_CARDTMsgArray dsCrCardArray = getDsCrCard(pMsg.glblCmpyCd.getValue(), pMsg.crCardCustRefNum.getValue());
            if (dsCrCardArray.length() > 0) {
                ZYPEZDItemValueSetter.setValue(apiPMsg.dsCrCardPk, dsCrCardArray.no(0).dsCrCardPk.getValue());
            } else {
                apiPMsg.dsCrCardPk.clear();
            }
        } else {
            apiPMsg.dsCrCardPk.clear();
        }

        ZYPEZDItemValueSetter.setValue(apiPMsg.xxValUpdFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiPMsg.orgRqstDelyDt, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.sendInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.invCmntTxt, "");

        int posnNum = 0;
        BigDecimal prevDsContrPk = BigDecimal.ZERO;
        for (int idx = 0; idx < pMsg.ordLineList.getValidCount(); idx++) {

            NWZC190001_ordLineListPMsg ordLine = pMsg.ordLineList.no(idx);

            if (prevDsContrPk.compareTo(ordLine.dsContrPk.getValue()) != 0) {

                posnNum++;
                prevDsContrPk = ordLine.dsContrPk.getValue();

                setConfigUpdApiReqDtlPMsg(pMsg, ordLine, apiPMsg, paramBean, String.valueOf(posnNum));

                setCpoUpdApiReqSalesCredit(pMsg, apiPMsg, paramBean, String.valueOf(posnNum));

                setCpoUpdApiReqDeliveryInfo(pMsg, apiPMsg, paramBean, String.valueOf(posnNum));
            }

            setCpoUpdApiReqDtlPMsg(pMsg, ordLine, apiPMsg, paramBean, String.valueOf(posnNum));
        }

        setCpoUpdApiReqSalesCredit(pMsg, apiPMsg, paramBean, null);

        setCpoCtacPsnInfo(pMsg, apiPMsg, paramBean, null);

        setCpoUpdApiReqDeliveryInfo(pMsg, apiPMsg, paramBean, null);

        setCpoUpdApiReqPriceCalcBase(pMsg, apiPMsg, paramBean);

        return apiPMsg;
    }

    private void setCpoUpdApiReqSalesCredit(NWZC190001PMsg pMsg, NWZC150001PMsg apiPMsg, ParamBean paramBean, String posnNum) {

        int idx = apiPMsg.cpoSlsCr.getValidCount();

        NWZC150001_cpoSlsCrPMsg cpoSlsCr = apiPMsg.cpoSlsCr.no(idx++);
        ZYPEZDItemValueSetter.setValue(cpoSlsCr.xxRqstTpCd, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
        ZYPEZDItemValueSetter.setValue(cpoSlsCr.dsOrdPosnNum, posnNum);
        if (ZYPCommonFunc.hasValue(posnNum)) {
            ZYPEZDItemValueSetter.setValue(cpoSlsCr.configCatgCd, CONFIG_CATG.OUTBOUND);
        } else {
            cpoSlsCr.configCatgCd.clear();
        }
        ZYPEZDItemValueSetter.setValue(cpoSlsCr.slsRepCd, paramBean.getSlsRepOrSlsTeamTocCd());
        ZYPEZDItemValueSetter.setValue(cpoSlsCr.slsRepRoleTpCd, paramBean.getLineBizRoleTpCd());
        ZYPEZDItemValueSetter.setValue(cpoSlsCr.slsRepCrPct, new BigDecimal(100));
        ZYPEZDItemValueSetter.setValue(cpoSlsCr.slsCrQuotFlg, ZYPConstant.FLG_ON_Y);
        apiPMsg.cpoSlsCr.setValidCount(idx);
    }

    private void setCpoUpdApiReqDeliveryInfo(NWZC190001PMsg pMsg, NWZC150001PMsg apiPMsg, ParamBean paramBean, String posnNum) {

        NWZC150001_cpoDlvyInfoListPMsg dlvyInfo = apiPMsg.cpoDlvyInfoList.no(0);
        ZYPEZDItemValueSetter.setValue(dlvyInfo.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELY_INFO_NEW);
        ZYPEZDItemValueSetter.setValue(dlvyInfo.dsOrdPosnNum, posnNum);
        ZYPEZDItemValueSetter.setValue(dlvyInfo.configCatgCd, CONFIG_CATG.OUTBOUND);
        // 2018/02/08 S21_NA#20297 Mod Start
//        String shpgCmntTxt = pMsg.xxShpgLbTxt.getValue();
        String shpgCmntTxt = ZYPCommonFunc.hasValue(pMsg.xxShpgLbTxt) ?
                pMsg.xxShpgLbTxt.getValue() : paramBean.getDefShpgCmnt();
        // 2018/02/08 S21_NA#20297 Mod End
        if (shpgCmntTxt.length() > DELY_ADDL_CMNT_TXT_MAX_SIZE) {
            shpgCmntTxt = shpgCmntTxt.substring(0, DELY_ADDL_CMNT_TXT_MAX_SIZE);
        }
        ZYPEZDItemValueSetter.setValue(dlvyInfo.delyAddlCmntTxt, shpgCmntTxt);
        apiPMsg.cpoDlvyInfoList.setValidCount(1);
    }

    private void setCpoCtacPsnInfo(NWZC190001PMsg pMsg, NWZC150001PMsg apiPMsg, ParamBean paramBean, String posnNum) {

        int idx = apiPMsg.cpoCtacPsnInfoList.getValidCount();

        NWZC150001_cpoCtacPsnInfoListPMsg ctacPsnInfoForMyCsa = apiPMsg.cpoCtacPsnInfoList.no(idx++);
        ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsa.xxRqstTpCd, NWZC150001Constant.RQST_TP_CTAC_PSN_NEW);
        ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsa.dsOrdPosnNum, posnNum);
        if (ZYPCommonFunc.hasValue(posnNum)) {
            ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsa.configCatgCd, CONFIG_CATG.OUTBOUND);
        } else {
            ctacPsnInfoForMyCsa.configCatgCd.clear();
        }
        ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsa.ctacTpCd, CTAC_TP.MYCSA);
        ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsa.ctacPsnPk, paramBean.getMyCsaCtacPsnPk());
        ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsa.firstNm, pMsg.psnFirstNm);
        ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsa.lastNm, pMsg.psnLastNm);
        ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsa.sortNum, new BigDecimal(1));
        ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsa.telNum, pMsg.telNum);
        ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsa.emlAddr, pMsg.emlAddr);

        NWZC150001_cpoCtacPsnInfoListPMsg ctacPsnInfoForOrdCtac = apiPMsg.cpoCtacPsnInfoList.no(idx++);
        EZDMsg.copy(ctacPsnInfoForMyCsa, null, ctacPsnInfoForOrdCtac, null);
        ZYPEZDItemValueSetter.setValue(ctacPsnInfoForOrdCtac.ctacTpCd, CTAC_TP.ORDER_CONTACT);
        ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsa.ctacPsnPk, paramBean.getOrdCtacCtacPsnPk());
        ZYPEZDItemValueSetter.setValue(ctacPsnInfoForOrdCtac.sortNum, new BigDecimal(2));

        apiPMsg.cpoCtacPsnInfoList.setValidCount(idx);
    }

    private void setCpoUpdApiReqPriceCalcBase(NWZC190001PMsg pMsg, NWZC150001PMsg apiPMsg, ParamBean paramBean) {

        int priceListPMsgCount = 0;

        for (NWZC157003PMsg pMsg03 : paramBean.getPrcElemList()) {
            NWZC150001_priceListPMsg priceListPMsg = apiPMsg.priceList.no(priceListPMsgCount);

            if (!CONFIG_CATG.OUTBOUND.equals(pMsg03.configCatgCd.getValue())) {
                continue;
            }

            EZDMsg.copy(pMsg03, null, priceListPMsg, null);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineNum, pMsg03.trxLineNum);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineSubNum, pMsg03.trxLineSubNum);

            priceListPMsgCount++;
        }
        apiPMsg.priceList.setValidCount(priceListPMsgCount);
    }

    private void setConfigUpdApiReqDtlPMsg(NWZC190001PMsg pMsg, NWZC190001_ordLineListPMsg linePMsg, NWZC150001PMsg apiPMsg, ParamBean paramBean, String posnNum) {

        int idx = apiPMsg.cpoConfig.getValidCount();

        NWZC150001_cpoConfigPMsg cpoConfigPMsg = apiPMsg.cpoConfig.no(idx++);
        ParamDetailBean detailBean = getParamDetailBean(paramBean, linePMsg);

        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_CONFIG_NEW);
        cpoConfigPMsg.dsCpoConfigPk.clear();
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsOrdPosnNum, posnNum);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configTpCd, CONFIG_TP.NEW);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.svcConfigMstrPk, detailBean.getSvcConfigMstrPk());
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlId, detailBean.getMdlId());
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlDescTxt, detailBean.getMdlDescTxt());
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustAcctCd, pMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustCd, pMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustAcctCd, pMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustCd, pMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dropShipFlg, pMsg.dropShipFlg);

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.dropShipFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToLocNm, pMsg.shipToLocNm);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToAddlLocNm, pMsg.shipToAddlLocNm);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFirstLineAddr, pMsg.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToScdLineAddr, pMsg.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToThirdLineAddr, pMsg.shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFrthLineAddr, pMsg.shipToFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo01RefCmntTxt, "");
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo02RefCmntTxt, "");
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtyAddr, pMsg.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToStCd, pMsg.shipToStCd);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToProvNm, pMsg.shipToProvNm);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCntyNm, pMsg.shipToCntyNm);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToPostCd, pMsg.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtryCd, pMsg.shipToCtryCd);
        } else {
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToLocNm, paramBean.getShipToLocNm());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToAddlLocNm, paramBean.getShipToAddlLocNm());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFirstLineAddr, paramBean.getShipToFirstLineAddr());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToScdLineAddr, paramBean.getShipToScdLineAddr());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToThirdLineAddr, paramBean.getShipToThirdLineAddr());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFrthLineAddr, paramBean.getShipToFrthLineAddr());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo01RefCmntTxt, paramBean.getShipToFirstRefCmntTxt());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo02RefCmntTxt, paramBean.getShipToScdRefCmntTxt());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtyAddr, paramBean.getShipToCtyAddr());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToStCd, paramBean.getShipToStCd());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToProvNm, paramBean.getShipToProvNm());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCntyNm, paramBean.getShipToCntyNm());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToPostCd, paramBean.getShipToPostCd());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtryCd, paramBean.getShipToCtryCd());
        }

        cpoConfigPMsg.pickSvcConfigMstrPk.clear();

        apiPMsg.cpoConfig.setValidCount(idx);
    }

    private void setCpoUpdApiReqDtlPMsg(NWZC190001PMsg pMsg, NWZC190001_ordLineListPMsg linePMsg, NWZC150001PMsg apiPMsg, ParamBean paramBean, String posnNum) {

        int idx = apiPMsg.A.getValidCount();

        NWZC150001_APMsg dtlPMsg = apiPMsg.A.no(idx++);
        ParamDetailBean detailBean = getParamDetailBean(paramBean, linePMsg);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxRqstTpCd_A1, NWZC150001Constant.RQST_TP_DTL_NEW);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineNum_A1, detailBean.getCpoDtlLineNum());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineSubNum_A1, detailBean.getCpoDtlLineSubNum());

        ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, detailBean.getMdseCd());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.origMdseCd_A1, linePMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dropShipFlg_A1, pMsg.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCustCd_A1, paramBean.getShipToCustCd());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToLocNm_A1, paramBean.getShipToLocNm());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToAddlLocNm_A1, paramBean.getShipToAddlLocNm());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstLineAddr_A1, paramBean.getShipToFirstLineAddr());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdLineAddr_A1, paramBean.getShipToScdLineAddr());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToThirdLineAddr_A1, paramBean.getShipToThirdLineAddr());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFrthLineAddr_A1, paramBean.getShipToFrthLineAddr());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtyAddr_A1, paramBean.getShipToCtyAddr());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToStCd_A1, paramBean.getShipToStCd());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToProvNm_A1, paramBean.getShipToProvNm());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToPostCd_A1, paramBean.getShipToPostCd());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtryCd_A1, paramBean.getShipToCtryCd());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCntyNm_A1, paramBean.getShipToCntyNm());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstRefCmntTxt_A1, paramBean.getShipToFirstRefCmntTxt());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdRefCmntTxt_A1, paramBean.getShipToScdRefCmntTxt());
        if (hasValue(detailBean.getInEachQty()) && BigDecimal.ZERO.compareTo(detailBean.getInEachQty()) != 0) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty_A1, linePMsg.ordCustUomQty.getValue().divide(detailBean.getInEachQty(), 0, RoundingMode.DOWN));
        } else {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty_A1, linePMsg.ordCustUomQty.getValue());
        }
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordCustUomQty_A1, linePMsg.ordCustUomQty);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd_A1, detailBean.getInvtyLocCd());
        dtlPMsg.entDealNetUnitPrcAmt_A1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ccyCd_A1, paramBean.getCcyCd());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rddDt_A1, getSalesDate(pMsg));
        dtlPMsg.rsdDt_A1.clear();
        dtlPMsg.shipCpltCd_A1.clear();
        dtlPMsg.stkStsCd_A1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd_A1, paramBean.getSlsRepOrSlsTeamTocCd());
        dtlPMsg.custMdseCd_A1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.custUomCd_A1, detailBean.getPkgUomCd());
        dtlPMsg.svcConfigMstrPk_A1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdPosnNum_A1, posnNum);
        if (hasValue(detailBean.getInPoundWt())) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.unitNetWt_A1, linePMsg.ordCustUomQty.getValue().multiply(detailBean.getInPoundWt()));
        } else {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.unitNetWt_A1, linePMsg.ordCustUomQty);
        }

        NWZC157004PMsg prcInfo = detailBean.getPrcInfo();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotBaseAmt_A1, prcInfo.xxTotBaseAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotDiscAmt_A1, prcInfo.xxTotDiscAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotSpclPrcAmt_A1, prcInfo.xxTotSpclPrcAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotNetDiscAmt_A1, prcInfo.xxTotNetDiscAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotNetPrcAmt_A1, prcInfo.xxTotNetPrcAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotFrtAmt_A1, prcInfo.xxTotFrtAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotSpclChrgAmt_A1, prcInfo.xxTotSpclChrgAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotTaxAmt_A1, prcInfo.xxTotTaxAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxSlsAmt_A1, prcInfo.xxSlsAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotAmt_A1, prcInfo.xxTotAmt);

        dtlPMsg.vndInvtyLocCd_A1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.frtCondCd_A1, pMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsContrNum_A1, detailBean.getDsContrNum());
        dtlPMsg.dsContrSqNum_A1.clear();
        dtlPMsg.dsCpoConfigPk_A1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_A1, detailBean.getOrdLineCatgCd());
        // 2022/01/17 S21_NA#59037 Add Start
        // ZYPEZDItemValueSetter.setValue(dtlPMsg.ordLineSrcCd_A1, "");
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordLineSrcCd_A1, detailBean.getOrdLineSrcCd());
        // 2022/01/17 S21_NA#59037 Add End
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd_A1, detailBean.getRtlWhCd());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlSwhCd_A1, detailBean.getRtlSwhCd());
        dtlPMsg.serNum_A1.clear();

        ZYPEZDItemValueSetter.setValue(dtlPMsg.flPrcListCd_A1, paramBean.getDefPrcCatgCd());

        NWZC157003PMsg prcCalc = null;
        for (NWZC157003PMsg pMsg03 : paramBean.getPrcElemList()) {
            if (CONFIG_CATG.OUTBOUND.equals(pMsg03.configCatgCd.getValue()) && detailBean.getCpoDtlLineNum().equals(pMsg03.trxLineNum.getValue()) && detailBean.getCpoDtlLineSubNum().equals(pMsg03.trxLineSubNum.getValue())
                    && PRC_DTL_GRP.BASE_PRICE.equals(pMsg03.prcDtlGrpCd.getValue())) {
                prcCalc = pMsg03;
                break;
            }
        }
        if (prcCalc != null) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dealPrcListPrcAmt_A1, prcCalc.autoPrcAmtRate);
            BigDecimal funcAmt = calcFuncAmt(pMsg.glblCmpyCd.getValue(), paramBean.getCcyCd(), getSalesDate(pMsg), prcCalc.autoPrcAmtRate.getValue());
            if (funcAmt == null) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.funcPrcListPrcAmt_A1, prcCalc.autoPrcAmtRate);
            } else {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.funcPrcListPrcAmt_A1, funcAmt);
            }
        }

        dtlPMsg.refCpoDtlLineNum_A1.clear();
        dtlPMsg.refCpoDtlLineSubNum_A1.clear();
        dtlPMsg.dplyLineRefNum_A1.clear();
        dtlPMsg.crRebilCd_A1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.prcBaseDt_A1, apiPMsg.prcBaseDt);
        dtlPMsg.splyTpCd_A1.clear();
        dtlPMsg.splyNm_A1.clear();
        // 2019/10/04 S21_NA#51372 Add Start
        dtlPMsg.prntVndNm_A1.clear();
        // 2019/10/04 S21_NA#51372 Add End
        dtlPMsg.splyFirstAddr_A1.clear();
        dtlPMsg.splyCtyAddr_A1.clear();
        dtlPMsg.splyStCd_A1.clear();
        dtlPMsg.splyPostCd_A1.clear();
        dtlPMsg.csmpContrNum_A1.clear();
        dtlPMsg.dlrRefNum_A1.clear();
        dtlPMsg.csmpPrcListCd_A1.clear();
        dtlPMsg.rntlTrmnDt_A1.clear();
        dtlPMsg.bllgAttrbCustAcctCd_A1.clear();
        dtlPMsg.firstBllgAttrbNm_A1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.firstBllgAttrbValTxt_A1, detailBean.getCtrlFldTxt01());
        dtlPMsg.scdBllgAttrbNm_A1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.scdBllgAttrbValTxt_A1, detailBean.getCtrlFldTxt02());
        dtlPMsg.thirdBllgAttrbNm_A1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.thirdBllgAttrbValTxt_A1, detailBean.getCtrlFldTxt03());
        dtlPMsg.frthBllgAttrbNm_A1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.frthBllgAttrbValTxt_A1, detailBean.getCtrlFldTxt04());
        dtlPMsg.fifthBllgAttrbNm_A1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.fifthBllgAttrbValTxt_A1, detailBean.getCtrlFldTxt05());
        dtlPMsg.sixthBllgAttrbNm_A1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.sixthBllgAttrbValTxt_A1, detailBean.getCtrlFldTxt06());
        dtlPMsg.sbstMdseCd_A1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineNum_A1, linePMsg.ordSrcRefLineNum);
        dtlPMsg.ordSrcRefLineSubNum_A1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.carrSvcLvlCd_A1, pMsg.carrSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.supdLockFlg_A1, ZYPConstant.FLG_ON_Y);
        dtlPMsg.prcListEquipConfigNum_A1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.prcCatgCd_A1, paramBean.getPrcCatgCd());
        dtlPMsg.loanBalQty_A1.clear();
        dtlPMsg.funcSvcRevTrnsfAmt_A1.clear();
        dtlPMsg.dealSvcRevTrnsfAmt_A1.clear();
        dtlPMsg.funcSvcCostTrnsfAmt_A1.clear();
        dtlPMsg.dealSvcCostTrnsfAmt_A1.clear();
        dtlPMsg.funcManFlAdjAmt_A1.clear();
        dtlPMsg.dealManFlAdjAmt_A1.clear();
        dtlPMsg.funcManRepRevAdjAmt_A1.clear();
        dtlPMsg.dealManRepRevAdjAmt_A1.clear();
        dtlPMsg.shopItemFlg_A1.clear();
        dtlPMsg.ordUpldRefNum_A1.clear();
        ZYPEZDItemValueSetter.setValue(dtlPMsg.baseCmptFlg_A1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.svcMachMstrPk_A1, linePMsg.svcMachMstrPk);

        apiPMsg.A.setValidCount(idx);
    }

    private BigDecimal calcFuncAmt(String glblCmpyCd, String dealCcyCd, String slsDt, BigDecimal dealAmt) {

        NWXC001001ExchangeData exchData = new NWXC001001ExchangeData();
        exchData.setGlblCmpyCd(glblCmpyCd);
        exchData.setCcyCd(dealCcyCd);
        exchData.setSlsDt(slsDt);
        List<NWXC001001ExchangePriceData> priceDataList = new ArrayList<NWXC001001ExchangePriceData>();
        NWXC001001ExchangeAmoutData exchAmtData = new NWXC001001ExchangeAmoutData();

        NWXC001001ExchangeAmount grsAmt = new NWXC001001ExchangeAmount();
        grsAmt.setDealAmt(dealAmt);

        exchAmtData.setGrsAmt(grsAmt);
        priceDataList.add(exchAmtData);

        exchData.setPriceData(priceDataList);

        NWXC001001Exchange.exchFuncUnitPrice(exchData);
        if (!exchData.getXxMsgIdList().isEmpty()) {
            return null;
        }
        BigDecimal funcAmt = null;
        for (NWXC001001ExchangePriceData prcData : exchData.getPriceData()) {
            for (NWXC001001ExchangeAmount amount : prcData.getAmountList()) {
                funcAmt = amount.getFuncAmt();
            }
        }
        return funcAmt;
    }

    private ParamDetailBean getParamDetailBean(ParamBean paramBean, NWZC190001_ordLineListPMsg linePMsg) {
        return getParamDetailBean(paramBean, linePMsg.ordSrcRefLineNum.getValue());
    }

    private ParamDetailBean getParamDetailBean(ParamBean paramBean, String ordSrcRefLineNum) {
        ParamDetailBean result = null;
        for (ParamDetailBean detailBean : paramBean.getDetailBeanList()) {
            if (detailBean.getOrdSrcRefLineNum().equals(ordSrcRefLineNum)) {
                result = detailBean;
                break;
            }
        }
        return result;
    }

    private ParamDetailBean getParamDetailBean(ParamBean paramBean, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        ParamDetailBean result = null;
        for (ParamDetailBean detailBean : paramBean.getDetailBeanList()) {
            if (detailBean.getCpoDtlLineNum().equals(cpoDtlLineNum) && detailBean.getCpoDtlLineSubNum().equals(cpoDtlLineSubNum)) {
                result = detailBean;
                break;
            }
        }
        return result;
    }

    private String getNextCpoDtlLineNum(String lineNum) {

        char[] digit1 = S21StringUtil.subStringByLength(lineNum, 0, 1).toCharArray();
        int digit23 = Integer.parseInt(S21StringUtil.subStringByLength(lineNum, 1, 2));

        digit23++;
        digit23 = digit23 % 100;
        if (digit23 == 0) {
            if (digit1[0] == 0x0039) {
                digit1[0] = 0x0041;
            } else {
                digit1[0]++;
            }
        }

        return String.valueOf(digit1) + ZYPCommonFunc.leftPad(String.valueOf(digit23), 2, "0");
    }

    private ParamBean getParamBean() {
        NWZC190001PMsg pMsg = (NWZC190001PMsg) msgMap.getPmsg();
        ParamBean bean = new ParamBean();

        getShipTo(pMsg, bean);

        getBillTo(pMsg, bean);

        if (pMsg.ordLineList.getValidCount() > 0) {
            NWZC190001_ordLineListPMsg linePMsg = pMsg.ordLineList.no(0);

            getFirstMachInfo(pMsg, linePMsg, bean);

            getCovTmpl(pMsg, bean);

            getSvcSplyOrdTpReln(pMsg, bean);

            getDsOrdTp(pMsg, bean);

            // 2018/02/08 S21_NA#20297(Sol#379) Add Start
            getDefShpgCmnt(pMsg, bean);
            // 2018/02/08 S21_NA#20297(Sol#379) Add End
        }

        getDefPrcCatgCd(pMsg, bean);
        if (hasErr()) {
            return null;
        }

        List<String> writers = getWriterLineBizRoleTp(pMsg.glblCmpyCd.getValue());
        getSlsRep(pMsg, bean, writers);
        if (hasErr()) {
            return null;
        }

        List<ParamDetailBean> list = new ArrayList<ParamDetailBean>();
        bean.setDetailBeanList(list);
        int cnt = pMsg.ordLineList.getValidCount();
        String cpoDtlLineNum = "000";
        for (int i = 0; i < cnt; i++) {
            NWZC190001_ordLineListPMsg ordLine = pMsg.ordLineList.no(i);
            ParamDetailBean detailBean = new ParamDetailBean();
            detailBean.setOrdSrcRefLineNum(ordLine.ordSrcRefLineNum.getValue());
            detailBean.setOrdLineCatgCd(bean.getOrdLineCatgCd());

            getDsContr(detailBean, ordLine, pMsg);

            getMachInfo(detailBean, ordLine, pMsg);

            if (!getMdseCd(detailBean, ordLine, pMsg, bean.getBillToCustAcctCd())) {
                continue;
            }

            getDefWhCd(bean, detailBean, ordLine, pMsg, "");

            cpoDtlLineNum = getNextCpoDtlLineNum(cpoDtlLineNum);
            detailBean.setCpoDtlLineNum(cpoDtlLineNum);
            detailBean.setCpoDtlLineSubNum(NWZC190001Constant.SUB_NUM);

            list.add(detailBean);
        }

        return bean;
    }

    private boolean setPriceInfo(ParamBean bean) {

        NWZC190001PMsg pMsg = (NWZC190001PMsg) msgMap.getPmsg();

        getPrcList(pMsg, bean);
        if (hasErr()) {
            return false;
        }

        NWZC157001PMsg basePricePMsg = callPricingApiForBasePrice(bean);
        if (hasErr()) {
            return false;
        }

        String modeCd = NWZC157001.GET_ORDER_ALL;

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        setHdrParam(prcApiPMsg, modeCd, bean);
        setLineParam(prcApiPMsg, modeCd, bean);

        setPrcElement(basePricePMsg, prcApiPMsg);

        new NWZC157001().execute(prcApiPMsg, this.onBatType);
        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(pMsg, msgId, msgPrms);
            }
        }

        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            if (S21ApiUtil.isXxMsgId(prcApiPMsg.NWZC157002PMsg.no(i))) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg.NWZC157002PMsg.no(i));
                for (S21ApiMessage msg : msgList) {
                    ParamDetailBean detailBean = getParamDetailBean(bean, prcApiPMsg.NWZC157002PMsg.no(i).trxLineNum.getValue(), prcApiPMsg.NWZC157002PMsg.no(i).trxLineSubNum.getValue());
                    NWZC190001_ordLineListPMsg linePMsg = getTarget(pMsg, detailBean.getOrdSrcRefLineNum());
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    setDetailErrMsg(pMsg, linePMsg, msgId, msgPrms);
                }
            }
        }
        if (hasErr()) {
            return false;
        }

        List<NWZC157003PMsg> prcElemList = new ArrayList<NWZC157003PMsg>();
        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg pMsg02 = prcApiPMsg.NWZC157002PMsg.no(i);
            for (int j = 0; j < pMsg02.NWZC157003PMsg.getValidCount(); j++) {
                NWZC157003PMsg pMsg03 = pMsg02.NWZC157003PMsg.no(j);
                NWZC157003PMsg prcElem = new NWZC157003PMsg();
                EZDMsg.copy(pMsg03, null, prcElem, null);
                prcElemList.add(prcElem);
            }
        }
        bean.setPrcElemList(prcElemList);

        for (int i = 0; i < prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
            NWZC157004PMsg prcInfo = new NWZC157004PMsg();
            EZDMsg.copy(prcApiPMsg.NWZC157004PMsg.no(i), null, prcInfo, null);

            ParamDetailBean detailBean = getParamDetailBean(bean, prcApiPMsg.NWZC157004PMsg.no(i).trxLineNum.getValue(), prcApiPMsg.NWZC157004PMsg.no(i).trxLineSubNum.getValue());
            detailBean.setPrcInfo(prcInfo);
        }
        return true;
    }

    private void getPrcList(NWZC190001PMsg pMsg, ParamBean bean) {
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, getSalesDate(pMsg));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, bean.getDsOrdCatgCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, bean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, bean.getLineBizTpCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, bean.getBillToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd, bean.getCoaBrCd());

        new NWZC157001().execute(prcApiPMsg, this.onBatType);
        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(pMsg, msgId, msgPrms);
            }
            return;
        }

        bean.setPrcCatgCd(prcApiPMsg.xxPrcList.no(0).prcCatgCd.getValue());

        PRC_CATGTMsg prcCatgTMsg = getPrcCatg(pMsg.glblCmpyCd.getValue(), bean.getPrcCatgCd());
        if (prcCatgTMsg != null) {
            bean.setCcyCd(prcCatgTMsg.ccyCd.getValue());
        }
    }

    private boolean getMdseCd(ParamDetailBean detailBean, NWZC190001_ordLineListPMsg ordLine, NWZC190001PMsg pMsg, String sellToCustCd) {

        List<Map<String, Object>> mdseInfoList = getMdseInfo(pMsg.glblCmpyCd.getValue(), ordLine.mdseCd.getValue(), sellToCustCd);
        if (mdseInfoList.size() == 0) {
            setErrMsg(pMsg, NWZM0036E);
            return false;
        }
        Map<String, Object> mdseInfo = mdseInfoList.get(0);

        detailBean.setMdseCd((String) mdseInfo.get("MDSE_CD"));
        detailBean.setPkgUomCd((String) mdseInfo.get("BASE_PKG_UOM_CD"));

        BigDecimal inEachQty = (BigDecimal) mdseInfo.get("IN_EACH_QTY");
        if (inEachQty == null || BigDecimal.ZERO.compareTo(inEachQty) == 0) {
            inEachQty = BigDecimal.ONE;
        }
        detailBean.setInEachQty(inEachQty);
        detailBean.setInPoundWt((BigDecimal) mdseInfo.get("IN_POUND_WT"));
        return true;
    }

    private boolean getDefWhCd(ParamBean bean, ParamDetailBean detailBean, NWZC190001_ordLineListPMsg ordLine, NWZC190001PMsg pMsg, String dsOrdPosnNum) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(pMsg.glblCmpyCd.getValue(), detailBean.getMdseCd());
        if (mdseTMsg == null) {
            setErrMsg(pMsg, NWZM0036E);
            return false;
        }

        if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue())) {
            return true;
        }

        boolean result = true;
        NWZC180001PMsg apiMsg = new NWZC180001PMsg();
        ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxModeCd, NWZC180001Constant.PROC_MODE_OTBD);
        ZYPEZDItemValueSetter.setValue(apiMsg.slsDt, getSalesDate(pMsg));
        ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdCatgCd, bean.getDsOrdCatgCd());
        ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdTpCd, bean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(apiMsg.mdseCd, mdseTMsg.mdseCd);
        if (hasValue(pMsg.shipToPostCd)) {
            ZYPEZDItemValueSetter.setValue(apiMsg.postCd, pMsg.shipToPostCd.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(apiMsg.postCd, bean.getShipToPostCd());
        }

        ZYPEZDItemValueSetter.setValue(apiMsg.ordQty, ordLine.ordCustUomQty);

        new NWZC180001().execute(apiMsg, this.onBatType);

        if (S21ApiUtil.isXxMsgId(apiMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                if (msgId.endsWith("E")) {
                    setErrMsg(pMsg, msgId, msgPrms);
                    result = false;
                }
            }
        } else {
            String dsWhCd = ZYPCodeDataUtil.getVarCharConstValue("DROP_SHIP_RTL_WH_CD", pMsg.glblCmpyCd.getValue());
            if (dsWhCd.equals(apiMsg.rtlWhCd.getValue())) {
                detailBean.setRtlWhCd(apiMsg.rtlWhCd.getValue());
                detailBean.setRtlSwhCd(apiMsg.rtlSwhCd.getValue());
                detailBean.setInvtyLocCd(apiMsg.vndCd.getValue());
                detailBean.setOrdLineSrcCd(apiMsg.ordLineSrcCd.getValue()); // // 2022/01/17 S21_NA#59037 Add
            } else {
                detailBean.setRtlWhCd(apiMsg.rtlWhCd.getValue());
                detailBean.setRtlSwhCd(apiMsg.rtlSwhCd.getValue());
                detailBean.setInvtyLocCd(S21StringUtil.concatStrings(apiMsg.rtlWhCd.getValue(), apiMsg.rtlSwhCd.getValue()));
                detailBean.setOrdLineSrcCd(apiMsg.ordLineSrcCd.getValue()); // // 2022/01/17 S21_NA#59037 Add
            }
        }

        return result;
    }

    // Mod Start 2018/02/26 QC#22967
    //private boolean checkBillShipSoldRelation(NWZC190001PMsg pMsg, ParamBean bean) {
    private void checkBillShipSoldRelation(NWZC190001PMsg pMsg, ParamBean bean) {
    // Mod End 2018/02/26 QC#22967

        // Add Start 2018/02/26 QC#22967
        // Check Sold To(Customer Code) <- Ship To(Account Number)
        // relation.
        callCustInfoGetApiForCheckRelation(pMsg, //
                bean.getBillToCustCd(), bean.getShipToCustAcctCd(), //
                NWZM2254E);

        // Check Bill To(Customer Code) <- Sold To(Account Number)
        // relation.
        callCustInfoGetApiForCheckRelation(pMsg, //
                pMsg.billToCustCd.getValue(), bean.getBillToCustAcctCd(), //
                NWZM2255E);
        // Add End 2018/02/26 QC#22967

        // Del Start 2018/02/26 QC#22967
//        NMZC610001PMsg custInfoGetApiPMsg = NWXC150001DsCheck.callCustInfoGetApi(pMsg.glblCmpyCd.getValue(),
//                bean.getBillToCustCd(),
//                bean.getBillToCustAcctCd(),
//                bean.getShipToCustAcctCd(),
//                this.onBatType);
//
//        if (S21ApiUtil.isXxMsgId(custInfoGetApiPMsg)) {
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(custInfoGetApiPMsg);
//            for (S21ApiMessage msg : msgList) {
//                setErrMsg(pMsg, msg.getXxMsgid(), msg.getXxMsgPrmArray());
//            }
//            return true;
//        }
//
//        for (int i = 0; i < custInfoGetApiPMsg.EligibleCheckList.getValidCount(); i++) {
//            NMZC610001_EligibleCheckListPMsg eligiblePMsg = custInfoGetApiPMsg.EligibleCheckList.no(i);
//            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue())
//                    || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnRecipFlg.getValue())) {
//                setErrMsg(pMsg, NWZM1455E);
//                return true;
//            }
//        }
//        return false;
        // Del End 2018/02/26 QC#22967
    }

    // Add Start 2018/02/26 QC#22967
    /**
     * @param pMsg NWZC190001PMsg
     * @param billToCustCd String
     * @param acctNum String
     * @param errMsgId String
     */
    private void callCustInfoGetApiForCheckRelation(NWZC190001PMsg pMsg, String billToCustCd, String acctNum, String errMsgId) {
        NMZC610001PMsg custInfoGetApiPMsg = NWXC150001DsCheck.callCustInfoGetApiBillTo( //
                pMsg.glblCmpyCd.getValue(), billToCustCd, acctNum, this.onBatType);

        if (S21ApiUtil.isXxMsgId(custInfoGetApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(custInfoGetApiPMsg);
            for (S21ApiMessage msg : msgList) {
                setErrMsg(pMsg, msg.getXxMsgid(), msg.getXxMsgPrmArray());
            }
        }

        for (int i = 0; i < custInfoGetApiPMsg.EligibleCheckList.getValidCount(); i++) {
            NMZC610001_EligibleCheckListPMsg eligiblePMsg = custInfoGetApiPMsg.EligibleCheckList.no(i);
            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue())) {
                setErrMsg(pMsg, errMsgId);
            }
        }
    }
    // Add End 2018/02/26 QC#22967

    private boolean checkFrtCondSvcLvlRelation(String glblCmpyCd, String lineBizTpCd, String frtCondCd, String shpgSvcLvlCd) {
        Integer cnt = getFrtCondSvcLvlRelationCnt(glblCmpyCd, lineBizTpCd, frtCondCd, shpgSvcLvlCd);
        return (cnt == 0);
    }

    private void getFirstMachInfo(NWZC190001PMsg pMsg, NWZC190001_ordLineListPMsg linePMsg, ParamBean bean) {

        DS_CONTRTMsg dsContr = getDsContr(pMsg.glblCmpyCd.getValue(), linePMsg.dsContrPk.getValue());
        if (dsContr == null) {
            setErrMsg(pMsg, NWZM1679E);
            return;
        }

        SVC_MACH_MSTRTMsg svcMachMstr = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), linePMsg.svcMachMstrPk.getValue());
        if (svcMachMstr == null) {
            setErrMsg(pMsg, NWZM2220E);
            return;
        }

        bean.setSvcPgmMdseCd(dsContr.svcPgmMdseCd.getValue());
        bean.setLineBizTpCd(svcMachMstr.svcByLineBizTpCd.getValue());
    }

    private void getCovTmpl(NWZC190001PMsg pMsg, ParamBean bean) {

        NSXC001001GetCovTmpl covTmpl = new NSXC001001GetCovTmpl();
        CovTmplInfo tmplInfo = new CovTmplInfo();
        tmplInfo.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        tmplInfo.setSlsDt(getSalesDate(pMsg));
        tmplInfo.setSvcPgmMdseCd(bean.getSvcPgmMdseCd());

        bean.setSuplIncl(covTmpl.isSuplIncl(tmplInfo));
        bean.setLaserPlusContr(covTmpl.isLaserPlusContr(tmplInfo));
    }

    private void getSvcSplyOrdTpReln(NWZC190001PMsg pMsg, ParamBean bean) {

        SVC_SPLY_ORD_TP_RELNTMsgArray svcSplyOrdTpRelnArray = getSvcSplyOrdTpReln(
                pMsg.glblCmpyCd.getValue(),
                bean.getLineBizTpCd(),
                convertFlgToYN(bean.isSuplIncl()),
                convertFlgToYN(bean.isLaserPlusContr()));

        if (svcSplyOrdTpRelnArray.length() > 0) {

            SVC_SPLY_ORD_TP_RELNTMsg svcSplyOrdTpReln = svcSplyOrdTpRelnArray.no(0);

            bean.setDsOrdCatgCd(svcSplyOrdTpReln.dsOrdCatgCd.getValue());
            bean.setDsOrdTpCd(svcSplyOrdTpReln.dsOrdTpCd.getValue());
            bean.setDsOrdRsnCd(svcSplyOrdTpReln.dsOrdRsnCd.getValue());
            bean.setOrdLineCatgCd(svcSplyOrdTpReln.dsOrdLineCatgCd.getValue());
        }
    }

    private void getDsOrdTp(NWZC190001PMsg pMsg, ParamBean bean) {

        DS_ORD_TPTMsg dsOrdTp = getDsOrdTp(pMsg.glblCmpyCd.getValue(), bean.getDsOrdTpCd());
        if (dsOrdTp == null) {
            setErrMsg(pMsg, NWZM1286E);
            return;
        }

        bean.setCpoOrdTpCd(dsOrdTp.cpoOrdTpCd.getValue());
    }

    private void getDefPrcCatgCd(NWZC190001PMsg pMsg, ParamBean bean) {
        List<Map<String, Object>> list = getDefPrcCatgCd(pMsg.glblCmpyCd.getValue(), bean.getDsOrdTpCd(), getSalesDate(pMsg));
        if (list.size() == 0) {
            setErrMsg(pMsg, NWZM1514E);
            return;
        }
        Map<String, Object> defPrcCatgCd = list.get(0);

        bean.setDefPrcCatgCd((String) defPrcCatgCd.get("DEF_PRC_CATG_CD"));
    }

    private void getDsContr(ParamDetailBean detailBean, NWZC190001_ordLineListPMsg ordLine, NWZC190001PMsg pMsg) {

        DS_CONTRTMsg dsContr = getDsContr(pMsg.glblCmpyCd.getValue(), ordLine.dsContrPk.getValue());
        if (dsContr == null) {
            setErrMsg(pMsg, NWZM1679E);
            return;
        }

        detailBean.setDsContrNum(dsContr.dsContrNum.getValue());
    }

    private void getMachInfo(ParamDetailBean detailBean, NWZC190001_ordLineListPMsg ordLine, NWZC190001PMsg pMsg) {

        SVC_MACH_MSTRTMsg svcMachMstr = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), ordLine.svcMachMstrPk.getValue());
        if (svcMachMstr == null) {
            setErrMsg(pMsg, NWZM2220E);
            return;
        }

        SVC_CONFIG_MSTRTMsg svcConfigMstr = getSvcConfigMstr(pMsg.glblCmpyCd.getValue(), svcMachMstr.svcConfigMstrPk.getValue());
        if (svcConfigMstr == null) {
            setErrMsg(pMsg, NWZM1779E);
            return;
        }

        detailBean.setSvcConfigMstrPk(svcConfigMstr.svcConfigMstrPk.getValue());
        detailBean.setCtrlFldTxt01(svcMachMstr.ctrlFldTxt_01.getValue());
        detailBean.setCtrlFldTxt02(svcMachMstr.ctrlFldTxt_02.getValue());
        detailBean.setCtrlFldTxt03(svcMachMstr.ctrlFldTxt_03.getValue());
        detailBean.setCtrlFldTxt04(svcMachMstr.ctrlFldTxt_04.getValue());
        detailBean.setCtrlFldTxt05(svcMachMstr.ctrlFldTxt_05.getValue());
        detailBean.setCtrlFldTxt06(svcMachMstr.ctrlFldTxt_06.getValue());

        DS_MDLTMsg dsMdl = getDsMdl(pMsg.glblCmpyCd.getValue(), svcConfigMstr.mdlId.getValue());
        if (dsMdl != null) {
            detailBean.setMdlId(dsMdl.mdlId.getValue());
            detailBean.setMdlDescTxt(dsMdl.mdlDescTxt.getValue());
        }
    }

    private void getShipTo(NWZC190001PMsg pMsg, ParamBean bean) {

        List<Map<String, Object>> list = getShipToCust(pMsg.glblCmpyCd.getValue(), pMsg.shipToCustCd.getValue());
        if (list == null || list.size() == 0) {
            setErrMsg(pMsg, NWZM1125E);
            return;
        }
        Map<String, Object> shipTo = list.get(0);

        bean.setShipToCustCd((String) shipTo.get("SHIP_TO_CUST_CD"));
        bean.setShipToCustAcctCd((String) shipTo.get("SELL_TO_CUST_CD"));
        bean.setShipToLocNum((String) shipTo.get("LOC_NUM"));
        bean.setShipToAddlLocNm((String) shipTo.get("ADDL_LOC_NM"));
        bean.setShipToLocNm((String) shipTo.get("LOC_NM"));
        bean.setShipToFirstLineAddr((String) shipTo.get("FIRST_LINE_ADDR"));
        bean.setShipToScdLineAddr((String) shipTo.get("SCD_LINE_ADDR"));
        bean.setShipToThirdLineAddr((String) shipTo.get("THIRD_LINE_ADDR"));
        bean.setShipToFrthLineAddr((String) shipTo.get("FRTH_LINE_ADDR"));
        bean.setShipToPostCd((String) shipTo.get("POST_CD"));
        bean.setShipToCtyAddr((String) shipTo.get("CTY_ADDR"));
        bean.setShipToStCd((String) shipTo.get("ST_CD"));
        bean.setShipToProvNm((String) shipTo.get("PROV_NM"));
        bean.setShipToCtryCd((String) shipTo.get("CTRY_CD"));
        bean.setShipToCntyNm((String) shipTo.get("CNTY_NM"));
        bean.setShipToFirstRefCmntTxt((String) shipTo.get("FIRST_REF_CMNT_TXT"));
        bean.setShipToScdRefCmntTxt((String) shipTo.get("SCD_REF_CMNT_TXT"));
    }

    private void getBillTo(NWZC190001PMsg pMsg, ParamBean bean) {

        List<Map<String, Object>> list = getBillToCust(pMsg.glblCmpyCd.getValue(), pMsg.billToCustCd.getValue());
        if (list == null || list.size() == 0) {
            setErrMsg(pMsg, NWZM1132E);
            return;
        }
        Map<String, Object> billTo = list.get(0);

        bean.setBillToCustCd((String) billTo.get("BILL_TO_CUST_CD"));
        bean.setBillToCustAcctCd((String) billTo.get("SELL_TO_CUST_CD"));

    }

    private NWZC190001_ordLineListPMsg getTarget(NWZC190001PMsg pMsg, ParamBean bean, NWZC150002PMsg pMsg02) {
        ParamDetailBean target = getParamDetailBean(bean, pMsg02.cpoDtlLineNum.getValue(), pMsg02.cpoDtlLineSubNum.getValue());
        if (target == null) {
            return null;
        }
        return getTarget(pMsg, target.getOrdSrcRefLineNum());
    }

    private NWZC190001_ordLineListPMsg getTarget(NWZC190001PMsg pMsg, String ordSrcRefLineNum) {
        NWZC190001_ordLineListPMsg linePMsg = null;
        for (int j = 0; j < pMsg.ordLineList.getValidCount(); j++) {
            if (ordSrcRefLineNum.equals(pMsg.ordLineList.no(j).ordSrcRefLineNum.getValue())) {
                linePMsg = pMsg.ordLineList.no(j);
                break;
            }
        }
        return linePMsg;
    }

    private void setHdrParam(NWZC157001PMsg prcApiPMsg, String modeCd, ParamBean paramBean) {

        NWZC190001PMsg pMsg = (NWZC190001PMsg) msgMap.getPmsg();

        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        prcApiPMsg.xxModeCd.setValue(modeCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, getSalesDate(pMsg));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, getSalesDate(pMsg));

        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, paramBean.getDsOrdCatgCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, paramBean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, paramBean.getLineBizTpCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, paramBean.getBillToCustAcctCd());

        ZYPEZDItemValueSetter.setValue(prcApiPMsg.cpoSrcTpCd, CPO_SRC_TP.IS_WEB);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);

        if (NWZC157001.GET_ORDER_ALL.equals(modeCd)) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.custIssPoNum, pMsg.custIssPoNum);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.spclHdlgTpCd, pMsg.spclHdlgTpCd);
        }
    }

    private void setLineParam(NWZC157001PMsg prcApiPMsg, String modeCd, ParamBean paramBean) {
        NWZC190001PMsg pMsg = (NWZC190001PMsg) msgMap.getPmsg();

        int cnt = pMsg.ordLineList.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NWZC190001_ordLineListPMsg ordLine = pMsg.ordLineList.no(i);
            NWZC157002PMsg nwzc157002PMsg = prcApiPMsg.NWZC157002PMsg.no(i);

            ParamDetailBean detailBean = getParamDetailBean(paramBean, ordLine);

            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.trxLineNum, detailBean.getCpoDtlLineNum());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.trxLineSubNum, detailBean.getCpoDtlLineSubNum());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.shipToCustCd, paramBean.getShipToCustCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.dsAcctNum_SH, paramBean.getShipToCustAcctCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.billToCustCd, paramBean.getBillToCustCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.dsAcctNum_BL, paramBean.getBillToCustAcctCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.prcCatgCd, paramBean.getPrcCatgCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ccyCd, paramBean.getCcyCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.mdseCd, detailBean.getMdseCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.pkgUomCd, detailBean.getPkgUomCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.dsOrdLineCatgCd, detailBean.getOrdLineCatgCd());
            if (hasValue(detailBean.getInEachQty()) && BigDecimal.ZERO.compareTo(detailBean.getInEachQty()) != 0) {
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ordQty, ordLine.ordCustUomQty.getValue().divide(detailBean.getInEachQty(), 0, RoundingMode.DOWN));
            } else {
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ordQty, ordLine.ordCustUomQty);
            }

            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ordCustUomQty, ordLine.ordCustUomQty);
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.invQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);

            if (NWZC157001.GET_LINE_PRICE.equals(modeCd)) {
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ctyAddr_SH, paramBean.getShipToCtyAddr());
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.stCd_SH, paramBean.getShipToStCd());
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ctryCd_SH, paramBean.getShipToCtryCd());
            } else if (NWZC157001.GET_ORDER_ALL.equals(modeCd)) {
                setValue(nwzc157002PMsg.firstLineAddr_SH, pMsg.shipToFirstLineAddr, paramBean.getShipToFirstLineAddr());
                setValue(nwzc157002PMsg.scdLineAddr_SH, pMsg.shipToScdLineAddr, paramBean.getShipToScdLineAddr());
                setValue(nwzc157002PMsg.ctyAddr_SH, pMsg.shipToCtyAddr, paramBean.getShipToCtyAddr());
                setValue(nwzc157002PMsg.stCd_SH, pMsg.shipToStCd, paramBean.getShipToStCd());
                setValue(nwzc157002PMsg.cntyNm_SH, pMsg.shipToCntyNm, paramBean.getShipToCntyNm());
                setValue(nwzc157002PMsg.postCd_SH, pMsg.shipToPostCd, paramBean.getShipToPostCd());
                setValue(nwzc157002PMsg.ctryCd_SH, pMsg.shipToCtryCd, paramBean.getShipToCtryCd());

                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.slsRepOrSlsTeamTocCd, paramBean.getSlsRepOrSlsTeamTocCd());
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.frtCondCd, pMsg.frtCondCd);
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.shpgSvcLvlCd, pMsg.shpgSvcLvlCd);
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.coaExtnCd, paramBean.getCoaExtnCd());
            }
        }
        prcApiPMsg.NWZC157002PMsg.setValidCount(cnt);
    }

    private void setValue(EZDPStringItem target, EZDPStringItem first, String second) {
        if (hasValue(first)) {
            ZYPEZDItemValueSetter.setValue(target, first);
        } else {
            ZYPEZDItemValueSetter.setValue(target, second);
        }
    }

    private boolean getSlsRep(NWZC190001PMsg pMsg, ParamBean bean, List<String> writers) {

        NMZC260001PMsg defRepApiPMsg = new NMZC260001PMsg();
        ZYPEZDItemValueSetter.setValue(defRepApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(defRepApiPMsg.shipToCustCd, bean.getShipToCustCd());
        ZYPEZDItemValueSetter.setValue(defRepApiPMsg.dsOrdTpCd, bean.getDsOrdTpCd());

        if (!callDefSlsRepApi(pMsg, defRepApiPMsg)) {
            return false;
        }

        // 2017/12/12 S21_NA#19804(Sol#349) Mod Start
//        String trtyGrpTpCd = getTrtyGrpTpCdFromDsOrdTpCd(bean.getDsOrdTpCd(), pMsg.glblCmpyCd.getValue());
        String trtyGrpTpTxt = getTrtyGrpTpTxtFromDsOrdTpCd(bean.getDsOrdTpCd(), pMsg.glblCmpyCd.getValue());
        List<String> trtyGrpTpCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(trtyGrpTpTxt)) {
            trtyGrpTpCdList = Arrays.asList(trtyGrpTpTxt.split(","));
        }
        // 2017/12/12 S21_NA#19804(Sol#349) Mod End

        NMZC260001_defSlsRepListPMsgArray defSlsRepMsgArray = defRepApiPMsg.defSlsRepList;
        for (int i = 0; i < defSlsRepMsgArray.getValidCount(); i++) {
            NMZC260001_defSlsRepListPMsg defSlsRepPMsg = defSlsRepMsgArray.no(i);

            // 2017/12/12 S21_NA#19804(Sol#349) Mod Start
//            if (defSlsRepPMsg.lineBizTpCd.getValue().equals(bean.getLineBizTpCd())
//                  && (!ZYPCommonFunc.hasValue(trtyGrpTpCd)
//                  || trtyGrpTpCd.equals(defSlsRepPMsg.trtyGrpTpCd.getValue()))) {
            if ((trtyGrpTpCdList.isEmpty() && defSlsRepPMsg.lineBizTpCd.getValue().equals(bean.getLineBizTpCd()))
                    || (trtyGrpTpCdList.size() > 0 && trtyGrpTpCdList.contains(defSlsRepPMsg.trtyGrpTpCd.getValue()))) {
            // 2017/12/12 S21_NA#19804(Sol#349) Mod End
                String lineBizRoleTpCd = defSlsRepPMsg.lineBizRoleTpCd.getValue();
                if (isWriter(writers, lineBizRoleTpCd)) {
                    bean.setSlsRepOrSlsTeamTocCd(defSlsRepPMsg.tocCd.getValue());
                    bean.setLineBizRoleTpCd(defSlsRepPMsg.lineBizRoleTpCd.getValue());
                    TOCTMsg toc = getToc(pMsg.glblCmpyCd.getValue(), defSlsRepPMsg.tocCd.getValue());
                    if (toc != null) {
                        bean.setCoaExtnCd(toc.coaExtnCd.getValue());
                        bean.setCoaBrCd(toc.coaBrCd.getValue());
                    }
                    break;
                }
            }
        }

        return true;
    }

    private boolean callDefSlsRepApi(NWZC190001PMsg pMsg, NMZC260001PMsg defRepApiPMsg) {
        boolean result = true;
        new NMZC260001().execute(defRepApiPMsg, this.onBatType);

        if (S21ApiUtil.isXxMsgId(defRepApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(defRepApiPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(pMsg, msgId, msgPrms);
                result = false;
            }
        }

        return result;
    }

    private boolean isWriter(List<String> writers, String lineBizRoleTpCd) {

        for (String writer : writers) {
            if (writer.equals(lineBizRoleTpCd)) {
                return true;
            }
        }
        return false;
    }

    private String convertFlgToYN(boolean val) {

        if (val) {
            return ZYPConstant.FLG_ON_Y;
        } else {
            return ZYPConstant.FLG_OFF_N;
        }
    }

    private void createAcpo() {

        NWZC190001PMsg pMsg = (NWZC190001PMsg) msgMap.getPmsg();

        int targetLineNum = 0;
        BigDecimal prevDsContrPk = BigDecimal.ZERO;
        List<DS_ACPOTMsg> dsAcpoList = new ArrayList<DS_ACPOTMsg>();
        for (int idx = 0; idx < pMsg.ordLineList.getValidCount(); idx++) {

            NWZC190001_ordLineListPMsg linePMsg = pMsg.ordLineList.no(idx);

            if (prevDsContrPk.compareTo(linePMsg.dsContrPk.getValue()) != 0) {

                targetLineNum = 0;
                prevDsContrPk = linePMsg.dsContrPk.getValue();
                dsAcpoList.clear();

                DS_CONTR_DTLTMsgArray dsContrDtlArray = getDsContrDtl(pMsg.glblCmpyCd.getValue(), linePMsg.dsContrPk.getValue(), linePMsg.svcMachMstrPk.getValue());
                for (int dtlCnt = 0; dtlCnt < dsContrDtlArray.getValidCount(); dtlCnt++) {
                    DS_CONTR_DTLTMsg dsContrDtl = dsContrDtlArray.no(dtlCnt);

                    DS_ACPOTMsg dsAcpoTmsg = new DS_ACPOTMsg();
                    if (!createAcpo(dsAcpoTmsg, pMsg, dsContrDtl.dsContrDtlPk.getValue())) {
                        setErrMsg(pMsg, NWZM1023E, new String[] {"DS_ACPO", dsAcpoTmsg.dsAcpoNum.getValue()});
                        return;
                    }

                    dsAcpoList.add(dsAcpoTmsg);
                }
            }

            for (DS_ACPOTMsg dsAcpoTmsg : dsAcpoList) {

                targetLineNum++;

                String dsAcpoNum = dsAcpoTmsg.dsAcpoNum.getValue();
                String dsAcpoTrgtLineNum = String.format("%03d", targetLineNum);
                if (!createAcpoTrgtDtl(pMsg, linePMsg, dsAcpoNum, dsAcpoTrgtLineNum)) {
                    setErrMsg(pMsg, NWZM1023E, new String[] {"DS_ACPO_TRGT_DTL", String.format("%s,%s", dsAcpoNum, dsAcpoTrgtLineNum)});
                    return;
                }
            }
        }
    }

    private boolean createAcpo(DS_ACPOTMsg dsAcpoTmsg, NWZC190001PMsg pMsg, BigDecimal dsContrDtlPk) {

        ZYPEZDItemValueSetter.setValue(dsAcpoTmsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsAcpoTmsg.dsAcpoNum, ZYPNumbering.getUniqueID(pMsg.glblCmpyCd.getValue(), DS_ACPO_NUM_SEQ));
        ZYPEZDItemValueSetter.setValue(dsAcpoTmsg.dsContrDtlPk, dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(dsAcpoTmsg.addDropShipFlg, pMsg.dropShipFlg);

        S21FastTBLAccessor.insert(dsAcpoTmsg);

        return EZDTBLAccessor.RTNCD_NORMAL.equals(dsAcpoTmsg.getReturnCode());
    }

    private boolean createAcpoTrgtDtl(NWZC190001PMsg pMsg, NWZC190001_ordLineListPMsg linePMsg, String dsAcpoNum, String dsAcpoTrgtLineNum) {

        DS_ACPO_TRGT_DTLTMsg dsAcpoTrgtDtlTmsg = new DS_ACPO_TRGT_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(dsAcpoTrgtDtlTmsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsAcpoTrgtDtlTmsg.dsAcpoNum, dsAcpoNum);
        ZYPEZDItemValueSetter.setValue(dsAcpoTrgtDtlTmsg.dsAcpoTrgtLineNum, dsAcpoTrgtLineNum);
        ZYPEZDItemValueSetter.setValue(dsAcpoTrgtDtlTmsg.shipQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dsAcpoTrgtDtlTmsg.qtyCtrlNoLimitFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsAcpoTrgtDtlTmsg.mdseCd, linePMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(dsAcpoTrgtDtlTmsg.trgtOrdQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dsAcpoTrgtDtlTmsg.dropShipFlg, pMsg.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(dsAcpoTrgtDtlTmsg.manPrcFlg, ZYPConstant.FLG_OFF_N);

        S21FastTBLAccessor.insert(dsAcpoTrgtDtlTmsg);

        return EZDTBLAccessor.RTNCD_NORMAL.equals(dsAcpoTrgtDtlTmsg.getReturnCode());
    }

    private boolean callCreditCardApi() {

        NWZC190001PMsg pMsg = (NWZC190001PMsg) msgMap.getPmsg();

        if (!isCreditPayment(pMsg)) {
            return true;
        }

        NWZC203001PMsg apiPMsg = new NWZC203001PMsg();
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_TRX);
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, getSalesDate(pMsg));
        ZYPEZDItemValueSetter.setValue(apiPMsg.crCardCustRefNum, pMsg.crCardCustRefNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.crCardAuthRefNum, pMsg.crCardAuthRefNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.sellToCustCd, pMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.crCardAuthDt, getSalesDate(pMsg));
        ZYPEZDItemValueSetter.setValue(apiPMsg.crCardAuthAmt, getTotDealAmt(pMsg));
        ZYPEZDItemValueSetter.setValue(apiPMsg.crCardTrxTpCd, CR_CARD_TRX_TP.CPO);
        ZYPEZDItemValueSetter.setValue(apiPMsg.firstTrxInfoTxt, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.AUTHORIZED_COMPLETED);

        return callCreditCardApi(pMsg, apiPMsg);
    }

    private boolean callCreditCardApi(NWZC190001PMsg pMsg, NWZC203001PMsg apiPMsg) {
        boolean result = true;
        new NWZC203001().execute(apiPMsg, this.onBatType);

        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(pMsg, msgId, msgPrms);
                result = false;
            }
        }

        return result;
    }

    private boolean isCreditPayment(NWZC190001PMsg pMsg) {

        return ZYPCommonFunc.hasValue(pMsg.crCardAuthRefNum);
    }

    private BigDecimal getTotDealAmt(NWZC190001PMsg pMsg) {

        CPOTMsg cpo = getCpo(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue());

        if (cpo == null) {
            return null;
        }

        return cpo.cpoTotDealNetAmt.getValue();
    }

    private DS_CONTRTMsg getDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg prmTMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    private DS_CONTR_DTLTMsgArray getDsContrDtl(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal svcMachMstrPk) {
        DS_CONTR_DTLTMsg prmTMsg = new DS_CONTR_DTLTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrPk01", dsContrPk);
        prmTMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        return (DS_CONTR_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(prmTMsg);
    }

    private SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg prmTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    private SVC_SPLY_ORD_TP_RELNTMsgArray getSvcSplyOrdTpReln(String glblCmpyCd, String lineBizTpCd, String suplInclFlg, String laserPlusFlg) {
        SVC_SPLY_ORD_TP_RELNTMsg prmTMsg = new SVC_SPLY_ORD_TP_RELNTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("lineBizTpCd01", lineBizTpCd);
        prmTMsg.setConditionValue("splyInclFlg01", suplInclFlg);
        prmTMsg.setConditionValue("laserPlusFlg01", laserPlusFlg);

        return (SVC_SPLY_ORD_TP_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(prmTMsg);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getDefPrcCatgCd(String glblCmpyCd, String dsOrdTpCd, String slsDt) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        ssmParam.put("slsDt", slsDt);

        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getDefPrcCatgCd", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getBillToCust(String glblCmpyCd, String billToCustCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("billToCustCd", billToCustCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getBillToCust", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getShipToCust(String glblCmpyCd, String shipToCustCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shipToCustCd", shipToCustCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getShipToCust", ssmParam);
    }

    private SVC_CONFIG_MSTRTMsg getSvcConfigMstr(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        SVC_CONFIG_MSTRTMsg prmTMsg = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcConfigMstrPk, svcConfigMstrPk);
        return (SVC_CONFIG_MSTRTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    private DS_MDLTMsg getDsMdl(String glblCmpyCd, BigDecimal mdlId) {
        DS_MDLTMsg prmTMsg = new DS_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.mdlId, mdlId);
        return (DS_MDLTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getMdseInfo(String glblCmpyCd, String mdseCd, String sellToCustCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("dsAcctNum", sellToCustCd);
        ssmParam.put("flagY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getMdseInfo", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<String> getWriterLineBizRoleTp(String glblCmpyCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("primRepRoleFlg", ZYPConstant.FLG_ON_Y);

        return (List<String>) ssmBatClnt.queryObjectList("getWriterLineBizRoleTp", ssmParam);
    }

    private TOCTMsg getToc(String glblCmpyCd, String tocCd) {
        TOCTMsg prmTMsg = new TOCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.tocCd, tocCd);
        return (TOCTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    private PRC_CATGTMsg getPrcCatg(String glblCmpyCd, String prcCatgCd) {
        PRC_CATGTMsg prmTMsg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.prcCatgCd, prcCatgCd);
        return (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    private DS_ORD_TPTMsg getDsOrdTp(String glblCmpyCd, String dsOrdTpCd) {
        DS_ORD_TPTMsg prmTMsg = new DS_ORD_TPTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsOrdTpCd, dsOrdTpCd);
        return (DS_ORD_TPTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    private Integer getFrtCondSvcLvlRelationCnt(String glblCmpyCd, String lineBizTpCd, String frtCondCd, String shpgSvcLvlCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("lineBizTpCd", lineBizTpCd);
        ssmParam.put("frtCondCd", frtCondCd);
        ssmParam.put("shpgSvcLvlCd", shpgSvcLvlCd);

        return (Integer) ssmBatClnt.queryObject("getFrtCondSvcLvlRelationCnt", ssmParam);
    }

    private CPO_DTLTMsgArray getDsCpoDtl(String glblCmpyCd, String cpoOrdNum) {
        CPO_DTLTMsg prmTMsg = new CPO_DTLTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);

        return (CPO_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(prmTMsg);
    }

    private CPOTMsg getCpo(String glblCmpyCd, String cpoOrdNum) {
        CPOTMsg prmTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.cpoOrdNum, cpoOrdNum);
        return (CPOTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    private DS_CR_CARDTMsgArray getDsCrCard(String glblCmpyCd, String crCardCustRefNum) {
        DS_CR_CARDTMsg prmTMsg = new DS_CR_CARDTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("crCardCustRefNum01", crCardCustRefNum);

        return (DS_CR_CARDTMsgArray) S21ApiTBLAccessor.findByCondition(prmTMsg);
    }

    /**
     * <pre>
     * Get Territory Group Type Code From DS Order Type Code
     * @param String dsOrdTpCd
     * @param String glblCmpyCd
     * </pre>
     */
    // 2017/12/19 S21_NA#19804(Sol#349) Mod Start
//    private static String getTrtyGrpTpCdFromDsOrdTpCd(String dsOrdTpCd, String glblCmpyCd) {
    private static String getTrtyGrpTpTxtFromDsOrdTpCd(String dsOrdTpCd, String glblCmpyCd) {
    // 2017/12/19 S21_NA#19804(Sol#349) Mod Start

        if (!ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            return null;
        }

        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.dsOrdTpCd, dsOrdTpCd);

        dsOrdTpProcDfnTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(dsOrdTpProcDfnTMsg);
        if (dsOrdTpProcDfnTMsg != null) {
            // 2017/12/19 S21_NA#19804(Sol#349) Mod Start
//            return dsOrdTpProcDfnTMsg.trtyGrpTpCd.getValue();
            return dsOrdTpProcDfnTMsg.trtyGrpTpTxt.getValue();
            // 2017/12/19 S21_NA#19804(Sol#349) Mod End
        }

        return null;
    }

    // 2017/12/21 S21_NA#20164(Sol#356) Add Start
    private static String getShpgCmntTxt(NWZC190001PMsg pMsg, int size) {

        String shpgCmntTxt = pMsg.xxShpgLbTxt.getValue();

        if (shpgCmntTxt.length() > size) {
            shpgCmntTxt = shpgCmntTxt.substring(0, size);
        }

        return shpgCmntTxt;
    }
    // 2017/12/21 S21_NA#20164(Sol#356) Add End

    // 2018/02/08 S21_NA#20297(Sol#397) Add Start
    private void getDefShpgCmnt(NWZC190001PMsg pMsg, ParamBean bean) {

        if (ZYPCommonFunc.hasValue(pMsg.xxShpgLbTxt)) {
            return;
        }

        NMZC610001PMsg apiPMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_INSTRUCTION);
        ZYPEZDItemValueSetter.setValue(apiPMsg.lineBizTpCd, bean.getLineBizTpCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsBizAreaCd, getBizAreaCd(pMsg, bean));
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsCustMsgTpCd, DS_CUST_MSG_TP.SHIP);
        ZYPEZDItemValueSetter.setValue(apiPMsg.billToCustCd, pMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.shipToCustCd, pMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsAcctNum_I1, pMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, getSalesDate(pMsg));

        if(!callCustomerInformationGetApi(pMsg, apiPMsg)) {
            return;
        }

        StringBuilder defShpgCmnt = new StringBuilder();
        for (int i = 0; i < apiPMsg.InstructionList.getValidCount(); i++) {
            if (defShpgCmnt.length() > 0) {
                defShpgCmnt.append(System.getProperty("line.separator"));
            }
            defShpgCmnt.append(apiPMsg.InstructionList.no(i).dsCustMsgTxt.getValue());
        }

        bean.setDefShpgCmnt(defShpgCmnt.toString());
    }

    private boolean callCustomerInformationGetApi(NWZC190001PMsg pMsg, NMZC610001PMsg apiPMsg) {

        boolean result = true;
        new NMZC610001().execute(apiPMsg, this.onBatType);

        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(pMsg, msgId, msgPrms);
                result = false;
            }
        }

        return result;
    }

    private String getBizAreaCd(NWZC190001PMsg pMsg, ParamBean bean) {

        ORD_CATG_BIZ_CTXTMsg param = new ORD_CATG_BIZ_CTXTMsg();
        param.setSQLID("002");
        param.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        param.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        param.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        param.setConditionValue("dsOrdCatgCd01", bean.getDsOrdCatgCd());

        ORD_CATG_BIZ_CTXTMsgArray result = (ORD_CATG_BIZ_CTXTMsgArray) S21ApiTBLAccessor.findByCondition(param);

        if (result == null || result.getValidCount() == 0) {
            return null;
        }

        return result.no(0).scdBizCtxAttrbTxt.getValue();
    }
    // 2018/02/08 S21_NA#20297(Sol#397) Add End
}
