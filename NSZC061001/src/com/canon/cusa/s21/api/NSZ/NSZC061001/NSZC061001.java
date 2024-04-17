/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC061001;

import static com.canon.cusa.s21.api.NSZ.NSZC061001.constant.NSZC061001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.RoundingMode.HALF_UP;

import business.db.AHSTMsg;
import business.db.AHSTMsgArray;
import business.db.AHS_ENBL_OVRDTMsg;
import business.db.AHS_ENBL_OVRDTMsgArray;
import business.db.AHS_POST_RELNTMsg;
import business.db.AHS_POST_RELNTMsgArray;
import business.db.CALTMsg;
import business.db.CALTMsgArray;
import business.db.CCYTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.MDSETMsg;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.FSRTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_BILL_TPTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_PRC_SHIFTTMsg;
import business.db.SVC_PRC_SHIFTTMsgArray;
import business.db.SVC_TASKTMsg;
import business.parts.NSZC061001PMsg;
import business.parts.NSZC061001_xxDrumChrgListPMsg;
import business.parts.NSZC061001_xxExpMdseListPMsg;
import business.parts.NSZC061001_xxPartsChrgListPMsg;
import business.parts.NWZC157001PMsg;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPItem;
import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.common.NSX.NSXC001001.CovTmplInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetContr;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.OutputCovTmplInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetCovTmpl;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.SVC_COV_FEAT;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_BILL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TRVL_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Service Pricing API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/17/2015   Hitachi         T.Tsuchida      Create          NA#Service Pricing API
 * 12/02/2015   Hitachi         T.Tsuchida      Update          QC#801
 * 12/15/2015   Hitachi         J.Kim           Update          QC#978
 * 02/22/2016   Hitachi         T.Iwamoto       Update          QC#4514
 * 03/02/2016   Hitachi         T.Iwamoto       Update          QC#4644
 * 03/04/2016   Hitachi         T.Iwamoto       Update          QC#5086
 * 04/12/2016   Hitachi         T.Iwamoto       Update          QC#6693
 * 04/14/2016   Hitachi         A.Kohinata      Update          QC#6927
 * 04/14/2016   Hitachi         A.Kohinata      Update          QC#7020
 * 04/15/2016   Hitachi         A.Kohinata      Update          QC#5489
 * 04/26/2016   Hitachi         T.Iwamoto       Update          QC#7519,6674
 * 05/19/2016   Hitachi         T.Iwamoto       Update          QC#5489
 * 05/20/2016   Hitachi         T.Iwamoto       Update          QC#8480
 * 06/07/2016   Hitachi         Y.Takeno        Update          QC#5489
 * 06/10/2016   Hitachi         T.Iwamoto       Update          QC#8899
 * 08/25/2016   Hitachi         A.Kohinata      Update          QC#13301
 * 09/20/2016   Hitachi         T.Kanasaka      Update          QC#13987
 * 10/13/2016   Hitachi         T.Kanasaka      Update          QC#13987
 * 2017/01/04   Hitachi         K.Kojima        Update          QC#16513
 * 2017/01/19   Hitachi         T.Tomita        Update          QC#17129
 * 2017/02/21   Hitachi         K.Kitachi       Update          QC#17164
 * 2017/03/02   Hitachi         K.Ochiai        Update          QC#17715
 * 2017/05/19   Hitachi         K.Kitachi       Update          QC#18213
 * 2017/06/02   Hitachi         T.Tomita        Update          QC#18768
 * 2017/06/05   Hitachi         K.Kojima        Update          QC#18295
 * 2017/07/26   Hitachi         T.Tomita        Update          QC#17129
 * 2017/10/02   Hitachi         K.Kim           Update          QC#20642
 * 2017/10/02   Hitachi         K.Kim           Update          QC#21168
 * 2017/10/02   Hitachi         K.Kim           Update          QC#21274
 * 2017/10/06   Hitachi         K.Kim           Update          QC#20078
 * 2018/01/18   Hitachi         U.Kim           Update          QC#22668
 * 2018/03/07   Hitachi         T.Tomita        Update          QC#22668
 * 2018/05/23   Hitachi         K.Kitachi       Update          QC#26295
 * 2018/05/25   Hitachi         K.Kim           Update          QC#15410(Sol#246)
 * 2018/06/28   Hitachi         K.Kitachi       Update          QC#26864
 * 2018/07/26   Hitachi         T.Tomita        Update          QC#27418
 * 2018/07/18   CITS            M.Naito         Update          QC#13309
 * 2018/09/14   Hitachi         K.Fujimoto      Update          QC#28160
 * 2018/11/09   Fujitsu         M.Yamada        Update          QC#29042
 * 2018/12/04   Hitachi         K.Kitachi       Update          QC#28635
 * 2018/12/05   Hitachi         K.Kim           Update          QC#28633
 * 2019/01/10   Hitachi         K.Fujimoto      Update          QC#26861
 * 2019/02/22   Hitachi         T.Tomita        Update          QC#30413
 * 2019/03/28   Hitachi         K.Fujimoto      Update          QC#30546
 * 2020/06/15   Hitachi         K.Yamada        Update          QC#56229
 * 2023/03/08   Hitachi         K.Watanabe      Update          QC#60065
 * 2023/03/23   Hitachi         K.Kitachi       Update          QC#60065
 * 2023/05/24   Hitachi         T.Nagae         Update          CSA-QC#61365
 * 2023/07/14   Hitachi         K.Watanabe      Update          QC#61310
 * </pre>
 */
public class NSZC061001 extends S21ApiCommonBase {

    /**
     * NSZC061001ApiMsgIdMgr
     */
    private static final class NSZC061001ApiMsgIdMgr extends S21ApiMessageIdMgr {

        @SuppressWarnings("unchecked")
        private boolean isXxErrMsgId() {
            try {
                Field fld = S21ApiMessageIdMgr.class.getDeclaredField("messageList");
                fld.setAccessible(true);
                Object obj = fld.get(this);
                if (obj == null) {
                    return false;
                } else if (List.class.isAssignableFrom(obj.getClass())) {
                    List<String> msgIdList = (List<String>) obj;
                    for (String msgId : msgIdList) {
                        if (msgId != null && msgId.endsWith("E")) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    return false;
                }
            } catch (Exception ex) {
                throw new S21AbendException(ex);
            }
        }
    }

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    // Add Start 2017/07/26 QC#17129
    /**
     * Parts Price Category Code
     */
    private String partsPrcCatgCd = null;

    /**
     * Drum Price Category Code
     */
    private String drumPrcCatgCd = null;
    // Add End 2017/07/26 QC#17129
    // Add Start 2019/02/22 QC#30413
    private List<String> exclPrtDiscBillTpList;
    // Add End 2019/02/22 QC#30413

    /**
     * Constructor
     */
    public NSZC061001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsgList List<NSZC061001PMsg>
     * @param onBatTp ONBATCH_TYPE
     */
    public void execute(List<NSZC061001PMsg> pMsgList, ONBATCH_TYPE onBatTp) {
        for (NSZC061001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatTp);
        }
    }

    /**
     * execute
     * @param pMsg NSZC061001PMsg
     * @param onBatTp ONBATCH_TYPE
     */
    public void execute(NSZC061001PMsg pMsg, ONBATCH_TYPE onBatTp) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg, new NSZC061001ApiMsgIdMgr());
        execute(msgMap, onBatTp);
        msgMap.flush();
        // START 2017/05/19 K.Kitachi [QC#18213, ADD]
        setMsgTxt(pMsg);
        // END 2017/05/19 K.Kitachi [QC#18213, ADD]
    }

    private void execute(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp) {

        NSZC061001PMsg pMsg = (NSZC061001PMsg) msgMap.getPmsg();

        // add start 2015/12/15 CSA Defect#978
        // START 2017/01/04 K.Kojima [QC#16513,DEL]
        // setDsContractPk(msgMap);
        // END 2017/01/04 K.Kojima [QC#16513,DEL]

        setCcyCd(msgMap);
        // add end 2015/12/15 CSA Defect#978

        if (hasValue(pMsg.xxModeCd)) {

            String xxModeCd = pMsg.xxModeCd.getValue();

            if (PROCESS_MODE_CALL_CREATION.equals(xxModeCd)) {

                callCreation(msgMap, onBatTp);
            } else if (PROCESS_MODE_CALL_CLOSE.equals(xxModeCd)) {

                callClose(msgMap, onBatTp);
            } else {
                msgMap.addXxMsgId(NSZM0039E);
            }
        } else {
            // START 2017/03/02 K.Ochiai [QC#17715, MOD]
            msgMap.addXxMsgId(NSZM0003E);
            // END 2017/03/02 K.Ochiai [QC#17715, MOD]
        }
    }

    /**
     * Call Creation
     * 
     * @param msgMap S21ApiMessageMap
     * @param onBatTp ONBATCH_TYPE
     */
    private void callCreation(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp) {
        vldCallCreation(msgMap, onBatTp);
        if (hasErrMsg(msgMap)) {
            return;
        }

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(msgMap, onBatTp);

        if (hasErrMsg(msgMap)) {
            return;
        }
        // Add Start 2018/07/26 QC#27418
        convMachLocTs(msgMap, svcMachMstrTMsg);
        // Add End 2018/07/26 QC#27418

        procCallCreation(msgMap, svcMachMstrTMsg, onBatTp);

    }

    /**
     * Validate "Call Creation"
     * 
     * @param msgMap S21ApiMessageMap
     * @param onBatTp ONBATCH_TYPE
     */
    private void vldCallCreation(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp) {

        NSZC061001PMsg pMsg = (NSZC061001PMsg) msgMap.getPmsg();

        // START 2017/03/02 K.Ochiai [QC#17715, MOD]
        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NSZM0001E);
        mandatoryCheck(msgMap, pMsg.svcMachMstrPk, NSZM0074E);
        mandatoryCheck(msgMap, pMsg.startDt, NSZM0699E);
        mandatoryCheck(msgMap, pMsg.startTm, NSZM0700E);
        // END 2017/03/02 K.Ochiai [QC#17715, MOD]

        if (hasErrMsg(msgMap)) {
            return;
        }
    }

    /**
     * Mandatory Check
     * 
     * @param msgMap S21ApiMessageMap
     * @param targetItem EZDPItem
     * @param messageId String
     * @param item String
     */
    // START 2017/03/02 K.Ochiai [QC#17715, MOD]
    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }
    // END 2017/03/02 K.Ochiai [QC#17715, MOD]

    /**
     * Get Service Machine Master
     * 
     * @param msgMap S21ApiMessageMap
     * @param onBatTp ONBATCH_TYPE
     * @return SVC_MACH_MSTRTMsg
     */
    private SVC_MACH_MSTRTMsg getSvcMachMstr(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp) {

        NSZC061001PMsg pMsg = (NSZC061001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (svcMachMstrTMsg == null) {
            // START 2017/03/02 K.Ochiai [QC#17715, MOD]
            msgMap.addXxMsgId(NSZM0074E);
            // END 2017/03/02 K.Ochiai [QC#17715, MOD]
        }

        return svcMachMstrTMsg;
    }

    /**
     * Get Service Machine Master
     * 
     * @param glblCmpyCd Global Company Code
     * @param svcMachMstrPk Service Machine Master Primary Key
     * @return SVC_MACH_MSTRTMsg
     */
    private SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    /**
     * Process "Call Creation"
     * 
     * @param msgMap S21ApiMessageMap
     * @param svcMachMstrTMsg Service Machine Master TMessage
     * @param onBatTp ONBATCH_TYPE
     */
    private void procCallCreation(S21ApiMessageMap msgMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg, ONBATCH_TYPE onBatTp) {

        NSZC061001PMsg pMsg = (NSZC061001PMsg) msgMap.getPmsg();

        // START 2017/01/04 K.Kojima [QC#16513,DEL]
        // // mod start 2016/08/25 CSA Defect#13301
        // //DS_CONTR_DTLTMsg dsContrDtlTMsg = NSXC001001GetContr.getContrDtlByMachMstrPk(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.startDt.getValue());
        // DS_CONTR_DTLTMsg dsContrDtlTMsg = NSXC001001GetContr.getEttlAvalContrDtlByMachMstrPk(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.startDt.getValue());
        // if (dsContrDtlTMsg == null) {
        //     //dsContrDtlTMsg = NSXC001001GetContr.getContrDtlByMachMstrPkInclWarranty(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.startDt.getValue());
        //     dsContrDtlTMsg = NSXC001001GetContr.getEttlAvalContrDtlByMachMstrPkInclWarranty(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.startDt.getValue());
        // }
        // // mod end 2016/08/25 CSA Defect#13301
        // END 2017/01/04 K.Kojima [QC#16513,DEL]

        // START 2017/01/04 K.Kojima [QC#16513,ADD]
        List<DS_CONTR_DTLTMsg> contrDtlList = NSXC001001GetContr.getEttlAvalContrDtlByMachMstrPk(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.startDt.getValue());
        // END 2017/01/04 K.Kojima [QC#16513,ADD]

        // START 2018/05/23 K.Kitachi [QC#26295, DEL]
//        CovTmplInfo covTmplInfo = new CovTmplInfo();
        // END 2018/05/23 K.Kitachi [QC#26295, DEL]
        // START 2017/01/04 K.Kojima [QC#16513,MOD]
        // getCovInfo(pMsg, covTmplInfo, dsContrDtlTMsg);
        // setBillTpCd(pMsg, covTmplInfo, SVC_COV_FEAT_CD_IS_BILL_CD);
        // START 2018/05/23 K.Kitachi [QC#26295, DEL]
//        getCovInfo(pMsg, covTmplInfo, contrDtlList);
//        setBillTpCd(pMsg, covTmplInfo, SVC_COV_FEAT_CD_IS_BILL_CD);
        // END 2018/05/23 K.Kitachi [QC#26295, DEL]
        // END 2017/01/04 K.Kojima [QC#16513,MOD]
        // START 2017/01/04 K.Kojima [QC#16513,ADD]
        setDsContractPk(pMsg, contrDtlList);
        // END 2017/01/04 K.Kojima [QC#16513,ADD]

        int dayOfTheWeek = getDayOfTheWeek(pMsg.glblCmpyCd.getValue(), pMsg.startDt.getValue());

        // START 2017/01/04 K.Kojima [QC#16513,MOD]
        // setAhsInfoModeCreate(pMsg, dsContrDtlTMsg, svcMachMstrTMsg, dayOfTheWeek);
        DS_CONTR_DTLTMsg contrDtl = null;
        if (contrDtlList.size() > 0) {
            // START 2018/09/14 K.Fujimoto [QC#28160,ADD]
            //contrDtl = contrDtlList.get(0);
            contrDtl = getAdoptDsContrDtlTMsg(pMsg,contrDtlList,dayOfTheWeek);
            // END 2018/09/14 K.Fujimoto [QC#28160,ADD]
            
        }
        // START 2023/03/23 K.Kitachi [QC#60065, MOD]
//        setAhsInfoModeCreate(pMsg, contrDtl, svcMachMstrTMsg, dayOfTheWeek);
        setAhsCdEnblFlg(pMsg, contrDtl, svcMachMstrTMsg, dayOfTheWeek);
        // END 2023/03/23 K.Kitachi [QC#60065, MOD]
        // END 2017/01/04 K.Kojima [QC#16513,MOD]

        // START 2018/05/23 K.Kitachi [QC#26295, DEL]
//        if (!ZYPConstant.FLG_OFF_N.equals(pMsg.ahsCdEnblFlg.getValue())) {
//            // START 2018/01/18 U.Kim [QC#, MOD]
//            // Map<String, Object> svcLborChrgMap = getSvcLborChrg(pMsg.glblCmpyCd.getValue(), pMsg.startTm.getValue(), dayOfTheWeek, svcMachMstrTMsg, null);
//            Map<String, Object> svcLborChrgMap = getSvcLborChrg(pMsg.glblCmpyCd.getValue(), getStartTm(pMsg), dayOfTheWeek, svcMachMstrTMsg, null);
//            // END 2018/01/18 U.Kim [QC#, MOD]
//
//            // START 2017/10/02 K.Kim [QC#21168,ADD]
//            if (hasValue(pMsg.dsSvcCallTpCd)){
//                DS_SVC_CALL_TPTMsg svcCallTpTMsg = getDsSvcCallTpTMsg(pMsg.glblCmpyCd.getValue(), pMsg.dsSvcCallTpCd.getValue());
//                if (svcCallTpTMsg == null) {
//                    msgMap.addXxMsgId(NSZM0549E);
//                    return;
//                }
//                if (!ALL_BILL_TP_CD.equals(svcCallTpTMsg.svcBillTpCd.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(pMsg.svcBillTpCd, svcCallTpTMsg.svcBillTpCd.getValue());
//                }
//            // END 2017/10/02 K.Kim [QC#21168,ADD]
//            } else {
//                // START 2017/10/02 K.Kim [QC#21274, ADD]
//                if (ZYPConstant.FLG_ON_Y.equals(pMsg.ahsCdEnblFlg.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(pMsg.svcBillTpCd, SVC_BILL_TP._9_CHARGE_FOR_LABOR_ONLY);
//                }
//                // END 2017/10/02 K.Kim [QC#21274, ADD]
//            }
//
//            // START 2017/10/02 K.Kim [QC#21274, MOD]
//            // String covLborChrgFlg = getCovFlg(covTmplInfo, SVC_COV_FEAT_CD_IS_LBOR_CHRG);
//            // setSvcLborChrgModeCreate(pMsg, covLborChrgFlg, svcLborChrgMap);
//            SVC_BILL_TPTMsg svcBillTpTMsg = getSvcBillTpTMsg(pMsg.glblCmpyCd.getValue(), pMsg.svcBillTpCd.getValue());
//            if (svcBillTpTMsg == null) {
//                msgMap.addXxMsgId(NSZM0302E);
//                return;
//            }
//            setSvcLborChrgModeCreate(pMsg, svcBillTpTMsg.lborChrgFlg.getValue(), svcLborChrgMap);
//            // END 2017/10/02 K.Kim [QC#21274, MOD]
//            setSvcTrvlChrgModeCreate(pMsg, svcLborChrgMap, svcMachMstrTMsg);
//        }
        // END 2018/05/23 K.Kitachi [QC#26295, DEL]

        // START 2018/05/23 K.Kitachi [QC#26295, ADD]
        // START 2019/03/28 K.Fujimoto [QC#30546, MOD]
        DS_SVC_CALL_TPTMsg svcCallTpTMsg = null;
        if (hasValue(pMsg.dsSvcCallTpCd)) {
//            DS_SVC_CALL_TPTMsg svcCallTpTMsg = getDsSvcCallTpTMsg(pMsg.glblCmpyCd.getValue(), pMsg.dsSvcCallTpCd.getValue());
            svcCallTpTMsg = getDsSvcCallTpTMsg(pMsg.glblCmpyCd.getValue(), pMsg.dsSvcCallTpCd.getValue());
        // END   2019/03/28 K.Fujimoto [QC#30546, MOD]
            if (svcCallTpTMsg == null) {
                msgMap.addXxMsgId(NSZM0549E);
                return;
            }
            if (!ALL_BILL_TP_CD.equals(svcCallTpTMsg.svcBillTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.svcBillTpCd, svcCallTpTMsg.svcBillTpCd.getValue());
            }
        }
        // START 2023/07/14 K.Watanabe [QC#61310, ADD]
        if (!hasValue(pMsg.svcBillTpCd) && isShowRoomMachine(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.startDt.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcBillTpCd, SVC_BILL_TP._8_NO_CHARGE);
        }
        // END 2023/07/14 K.Watanabe [QC#61310, ADD]
        if (!hasValue(pMsg.svcBillTpCd)) {
            // START 2019/03/28 K.Fujimoto [QC#30546, MOD]
//            if (!ZYPConstant.FLG_ON_Y.equals(pMsg.ahsCdEnblFlg.getValue()) || (hasValue(pMsg.svcMinChrgHrsAot) && pMsg.svcMinChrgHrsAot.getValue().compareTo(BigDecimal.ZERO) == 0)) {
            String svcRcllFlg = ZYPConstant.FLG_OFF_N;
            if (svcCallTpTMsg != null) {
                svcRcllFlg = svcCallTpTMsg.svcRcllFlg.getValue();
            }
            // START 2023/03/23 K.Kitachi [QC#60065, DEL]
//            if (ZYPConstant.FLG_ON_Y.equals(svcRcllFlg)
//                    || !ZYPConstant.FLG_ON_Y.equals(pMsg.ahsCdEnblFlg.getValue())
//                       || (hasValue(pMsg.svcMinChrgHrsAot) && pMsg.svcMinChrgHrsAot.getValue().compareTo(BigDecimal.ZERO) == 0)) {
            // END 2023/03/23 K.Kitachi [QC#60065, DEL]
            // START 2023/03/23 K.Kitachi [QC#60065, ADD]
            if (ZYPConstant.FLG_ON_Y.equals(svcRcllFlg)
                    || !ZYPConstant.FLG_ON_Y.equals(pMsg.ahsCdEnblFlg.getValue())
                    || (hasValue(pMsg.svcMinChrgHrsAot) && pMsg.svcMinChrgHrsAot.getValue().compareTo(BigDecimal.ZERO) == 0)
                    || (contrDtl != null && isAhsForTermCondAhsWrkPgm(pMsg.glblCmpyCd.getValue(), contrDtl.dsContrPk.getValue(), contrDtl.dsContrDtlPk.getValue(), getStartTm(pMsg), dayOfTheWeek))) {
            // END 2023/03/23 K.Kitachi [QC#60065, ADD]
                CovTmplInfo covTmplInfo = new CovTmplInfo();
                getCovInfo(pMsg, covTmplInfo, contrDtlList);
//                setBillTpCd(pMsg, covTmplInfo, SVC_COV_FEAT_CD_IS_BILL_CD);
                setBillTpCd(pMsg, covTmplInfo, SVC_COV_FEAT_CD_IS_BILL_CD, svcRcllFlg);
            // END   2019/03/28 K.Fujimoto [QC#30546, MOD]
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.svcBillTpCd, SVC_BILL_TP._9_CHARGE_FOR_LABOR_ONLY);
            }
        }
        // START 2023/03/23 K.Kitachi [QC#60065, ADD]
        setAhsInfoModeCreate(pMsg, contrDtl, svcMachMstrTMsg, dayOfTheWeek);
        // END 2023/03/23 K.Kitachi [QC#60065, ADD]
        // START 2018/06/28 K.Kitachi [QC#26864, MOD]
//        if (ZYPConstant.FLG_ON_Y.equals(pMsg.ahsCdEnblFlg.getValue())) {
        if (hasValue(pMsg.svcLborUnitAmt)) {
            return;
        }
        SVC_BILL_TPTMsg svcBillTpTMsg = getSvcBillTpTMsg(pMsg.glblCmpyCd.getValue(), pMsg.svcBillTpCd.getValue());
        if (svcBillTpTMsg == null) {
            msgMap.addXxMsgId(NSZM0302E);
            return;
        }
        Map<String, Object> svcLborChrgMap = getSvcLborChrg(pMsg.glblCmpyCd.getValue(), getStartTm(pMsg), dayOfTheWeek, svcMachMstrTMsg, null);
        setSvcLborChrgModeCreate(pMsg, svcBillTpTMsg.lborChrgFlg.getValue(), svcLborChrgMap);
        // START 2018/12/05 K.Kim [QC#28633, MOD]
        // setSvcTrvlChrgModeCreate(pMsg, svcLborChrgMap, svcMachMstrTMsg);
        setSvcTrvlChrgModeCreate(pMsg, svcMachMstrTMsg);
        // END 2018/12/05 K.Kim [QC#28633, MOD]
//        }
        // END 2018/06/28 K.Kitachi [QC#26864, MOD]
        // END 2018/05/23 K.Kitachi [QC#26295, ADD]
    }

    // START 2017/01/04 K.Kojima [QC#16513,DEL]
    // /**
    //  * Set Bill Type Code
    //  * 
    //  * @param covTmplInfo CovTmplInfo
    //  * @param covCd String
    //  * @return Bill Type Code
    //  */
    // private void setBillTpCd(NSZC061001PMsg pMsg, CovTmplInfo covTmplInfo, String covCd) {
    //     // mod start 2016/04/26 CSA Defect#6674
    //     String svcBillTpCd = SVC_BILL_TP.CHARGE;
    //     // mod start 2016/04/26 CSA Defect#6674
    //     List<OutputCovTmplInfo> outputCovTmplInfoList = covTmplInfo.getOutputCovTmplInfoList();
    //     if (outputCovTmplInfoList != null) {
    //         for (int i = 0; i < outputCovTmplInfoList.size(); i++) {
    //             if (covCd.equals(outputCovTmplInfoList.get(i).getSvcCovFeatCd())) {
    //                 if (covCd.equals(SVC_COV_FEAT_CD_IS_BILL_CD)) {
    //                     svcBillTpCd = outputCovTmplInfoList.get(i).getSvcCovDtlValTxt();
    //                     break;
    //                 }
    //             }
    //         }
    //     }
    //     ZYPEZDItemValueSetter.setValue(pMsg.svcBillTpCd, svcBillTpCd);
    // }
    // END 2017/01/04 K.Kojima [QC#16513,DEL]

    // START 2017/01/04 K.Kojima [QC#16513,ADD]
    /**
     * setBillTpCd
     * @param pMsg NSZC061001PMsg
     * @param covTmplInfo CovTmplInfo
     * @param covCd covTmplInfo
     * @param svcRcllFlg
     */
    // START 2019/03/28 K.Fujimoto [QC#30546, MOD]
//    private void setBillTpCd(NSZC061001PMsg pMsg, CovTmplInfo covTmplInfo, String covCd) {
    private void setBillTpCd(NSZC061001PMsg pMsg, CovTmplInfo covTmplInfo, String covCd, String svcRcllFlg) {
    // END   2019/03/28 K.Fujimoto [QC#30546, MOD]
        String svcBillTpCd = null;
        List<String> svcBillTpCdList = new ArrayList<String>();
        List<OutputCovTmplInfo> outputCovTmplInfoList = covTmplInfo.getOutputCovTmplInfoList();
        if (outputCovTmplInfoList != null) {
            for (int i = 0; i < outputCovTmplInfoList.size(); i++) {
                if (covCd.equals(outputCovTmplInfoList.get(i).getSvcCovFeatCd())) {
                    if (covCd.equals(SVC_COV_FEAT_CD_IS_BILL_CD)) {
                        if (ZYPCommonFunc.hasValue(outputCovTmplInfoList.get(i).getSvcCovDtlValTxt())) {
                            svcBillTpCdList.add(outputCovTmplInfoList.get(i).getSvcCovDtlValTxt());
                        }
                    }
                }
            }
        }

        // START 2019/03/28 K.Fujimoto [QC#30546, MOD]
        // Case:Recall.
        if (svcRcllFlg.equals(ZYPConstant.FLG_ON_Y)) {
            // Case:No Covered.
            if (svcBillTpCdList.size() == 0) {
                svcBillTpCd = SVC_BILL_TP.F1_FIRST_HOUR_NO_CHARGE_FOR_PARTS_AND_LABOR_AND_TRAVEL;
            // Case:Covered.
            } else {
                String regSvcBillTpCd = null;
                if (svcBillTpCdList.size() == 1) {
                    regSvcBillTpCd = svcBillTpCdList.get(0);
                } else {
                    regSvcBillTpCd = getSvcBillTpCd(pMsg.glblCmpyCd.getValue(), svcBillTpCdList);
                }
                svcBillTpCd = getSvcBillTpCdForRecall(regSvcBillTpCd);
            }

        // Case Regular Call.
        } else {
            if (svcBillTpCdList.size() == 0) {
                svcBillTpCd = SVC_BILL_TP.CHARGE;
            } else if (svcBillTpCdList.size() == 1) {
                svcBillTpCd = svcBillTpCdList.get(0);
            } else {
                svcBillTpCd = getSvcBillTpCd(pMsg.glblCmpyCd.getValue(), svcBillTpCdList);
            }
        }
        // END   2019/03/28 K.Fujimoto [QC#30546, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.svcBillTpCd, svcBillTpCd);
    }

    // START 2019/03/28 K.Fujimoto [QC#30546, ADD]
    /**
     * getSvcBillTpCdForRecall
     * @param regSvcBillTpCd Service Bill Type Cd For Regular Call.
     * @return Service Bill Type Cd For Recall. 
     */
    private String getSvcBillTpCdForRecall(String regSvcBillTpCd) {
        String svcBillTpCd;
        // No Covered
        if (regSvcBillTpCd == null) {
            svcBillTpCd = SVC_BILL_TP.F1_FIRST_HOUR_NO_CHARGE_FOR_PARTS_AND_LABOR_AND_TRAVEL;
        // Partial Covered
        } else if (regSvcBillTpCd.equals(SVC_BILL_TP._10_CHARGE_FOR_LABOR_AND_TRAVEL)) {
            svcBillTpCd = SVC_BILL_TP.F0_FIRST_HOUR_NO_CHARGE_FOR_LABOR_AND_TRAVEL;
        // Partial Covered
        } else if (regSvcBillTpCd.equals(SVC_BILL_TP._1_CHARGE_FOR_PARTS_AND_LABOR)) {
            svcBillTpCd = SVC_BILL_TP.F1_FIRST_HOUR_NO_CHARGE_FOR_PARTS_AND_LABOR_AND_TRAVEL;
        // Partial Covered
        } else if (regSvcBillTpCd.equals(SVC_BILL_TP._9_CHARGE_FOR_LABOR_ONLY)) {
            svcBillTpCd = SVC_BILL_TP.F9_FIRST_HOUR_NO_CHARGE_FOR_LABOR;
         // Full Covered
        } else {
            svcBillTpCd = SVC_BILL_TP._4_COVERED_UNDER_CONTRACT;
        }
        return svcBillTpCd;
    }
    // END   2019/03/28 K.Fujimoto [QC#30546, END]

    /**
     * setDsContractPk
     * @param pMsg NSZC061001PMsg
     * @param contrDtlList List<DS_CONTR_DTLTMsg>
     */
    private void setDsContractPk(NSZC061001PMsg pMsg, List<DS_CONTR_DTLTMsg> contrDtlList) {
        BigDecimal dsContrPk = null;
        if (contrDtlList.size() == 0) {
            dsContrPk = null;
        } else if (contrDtlList.size() == 1) {
            dsContrPk = contrDtlList.get(0).dsContrPk.getValue();
        } else {
            List<BigDecimal> dsContrDtkPkList = new ArrayList<BigDecimal>();
            for (int i = 0; i < contrDtlList.size(); i++) {
                dsContrDtkPkList.add(contrDtlList.get(i).dsContrDtlPk.getValue());
            }
            dsContrPk = getDsContrPk(pMsg.glblCmpyCd.getValue(), dsContrDtkPkList);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrPk, dsContrPk);
    }

    // END 2017/01/04 K.Kojima [QC#16513,ADD]

    // START 2017/10/02 K.Kim [QC#21274, DEL]
//    /**
//     * Get Coverage Flag
//     * 
//     * @param covTmplInfo CovTmplInfo
//     * @param covCd String
//     * @return coverage Flag(Y or N)
//     */
//    private String getCovFlg(CovTmplInfo covTmplInfo, String covCd) {
//        String rtnVal = ZYPConstant.FLG_ON_Y;
//        List<OutputCovTmplInfo> outputCovTmplInfoList = covTmplInfo.getOutputCovTmplInfoList();
//        if (outputCovTmplInfoList != null) {
//            for (int i = 0; i < outputCovTmplInfoList.size(); i++) {
//                if (covCd.equals(outputCovTmplInfoList.get(i).getSvcCovFeatCd())) {
//                    if (covCd.equals(SVC_COV_FEAT_CD_IS_LBOR_CHRG)) {
//                        rtnVal = getChangeFlg(outputCovTmplInfoList.get(i).getSvcCovDtlValTxt());
//                        break;
//                    } else if (covCd.equals(SVC_COV_FEAT_CD_IS_PRT_CHRG)) {
//                        rtnVal = getChangeFlg(outputCovTmplInfoList.get(i).getSvcCovDtlValTxt());
//                        break;
//                    } else if (covCd.equals(SVC_COV_FEAT_CD_IS_EXP_CHRG)) {
//                        rtnVal = getChangeFlg(outputCovTmplInfoList.get(i).getSvcCovDtlValTxt());
//                        break;
//                    } else if (covCd.equals(SVC_COV_FEAT_CD_IS_DRUM_CHRG)) {
//                        rtnVal = getChangeFlg(outputCovTmplInfoList.get(i).getSvcCovDtlValTxt());
//                        break;
//                    }
//                }
//            }
//        }
//        return rtnVal;
//    }
//
//    /**
//     * Get Change Flag
//     * 
//     * @param covFlg String
//     * @return coverage Flag(Y or N)
//     */
//    private String getChangeFlg(String covFlg) {
//        if (ZYPConstant.FLG_ON_Y.equals(covFlg)) {
//            return ZYPConstant.FLG_OFF_N;
//        }
//        return ZYPConstant.FLG_ON_Y;
//    }
    // END 2017/10/02 K.Kim [QC#21274, DEL]

    // START 2018/09/14 K.Fujimoto [QC#28160,ADD]
    /**
     * If there is only one TMsg in Contract Detail TMsgAry, return the TMsg.
     * If there are TMsgs in Contract Detail TMsgAry, 
     * return the TMsg.Return Contract Detail TMsg on the following priority. 
     * [Piriority]
     * 1. Working program, No Billing  (return TMsg)
     * 2. Working program, Billing     (return TMsg)
     * 3. No Working program           (return null)
     * 
     * @param pMsg NSZC061001PMsg
     * @param dsContrDtlTMsgAry Direct Sales Contract Detail TMsg Array
     * @param dayOfTheWeek Day Of The Week
     */
    private DS_CONTR_DTLTMsg getAdoptDsContrDtlTMsg(NSZC061001PMsg pMsg,List<DS_CONTR_DTLTMsg> dsContrDtlTMsgAry, int dayOfTheWeek){
        
        DS_CONTR_DTLTMsg adoptContrDtlTMsg = null;
        if (dsContrDtlTMsgAry.size() == 1){
            return dsContrDtlTMsgAry.get(0);
        }
        
        // After Hours
        for(DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgAry){
            // Working program
            if (dsContrDtlTMsg != null && isAhsForTermCondAhsWrkPgm(pMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), getStartTm(pMsg), dayOfTheWeek)) {
                // START 2023/03/08 K.Watanabe [QC#60065,DEL]
                //String ahsBillFlg = getAhsBillFlg(pMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
                
                // Priority 1. Working program, No Billing
                //if(ZYPConstant.FLG_OFF_N.equals(ahsBillFlg)){
                //    adoptContrDtlTMsg = dsContrDtlTMsg;
                //    return adoptContrDtlTMsg;
                
                // Priority 2. Working program, Billing
                //}else{
                //    adoptContrDtlTMsg = dsContrDtlTMsg;
                //}
                // END 2023/03/08 K.Watanabe [QC#60065,DEL]
                // START 2023/03/08 K.Watanabe [QC#60065,ADD]
                adoptContrDtlTMsg = dsContrDtlTMsg;
                // END 2023/03/08 K.Watanabe [QC#60065,ADD]
            }
        }
        
        return adoptContrDtlTMsg;
        
    }
    // END 2018/09/14 K.Fujimoto [QC#28160,ADD]

    /**
     * Set After Hours Shift Information
     * 
     * @param pMsg NSZC061001PMsg
     * @param dsContrDtlTMsg Direct Sales Contract Detail TMsg Array
     * @param svcMachMstrTMsg Service Machine Master TMsg
     * @param dayOfTheWeek Day Of The Week
     */
    private void setAhsInfoModeCreate(NSZC061001PMsg pMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, int dayOfTheWeek) {
        // mod start 2016/04/15 CSA Defect#5489
        // START 2018/01/18 U.Kim [QC#22668, MOD]
        // SVC_PRC_SHIFTTMsg svcPrcShiftTmsg = getSvcPrcShiftInfo(pMsg.glblCmpyCd.getValue(), pMsg.startTm.getValue(), dayOfTheWeek, svcMachMstrTMsg.svcByLineBizTpCd.getValue());
        SVC_PRC_SHIFTTMsg svcPrcShiftTmsg = getSvcPrcShiftInfo(pMsg.glblCmpyCd.getValue(), getStartTm(pMsg), dayOfTheWeek, svcMachMstrTMsg.svcByLineBizTpCd.getValue());
        // START 2018/01/18 U.Kim [QC#22668, MOD]
        String ahsEnblFlg = getAhsEnblFlg(svcPrcShiftTmsg);

        // Not After Hours
        if (ZYPConstant.FLG_OFF_N.equals(ahsEnblFlg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.ahsCdEnblFlg, "");
            // START 2017/10/02 K.Kim [QC#21274, ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.xxAftHrsPopDplyFlg, ZYPConstant.FLG_OFF_N);
            // END 2017/10/02 K.Kim [QC#21274, ADD]
            return;
        }

        // After Hours
        // START 2017/10/02 K.Kim [QC#21274, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.xxAftHrsPopDplyFlg, ZYPConstant.FLG_ON_Y);
        // END 2017/10/02 K.Kim [QC#21274, ADD]
        if (dsContrDtlTMsg != null) {
            if (isAhsForTermCondAhsWrkPgm(pMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), getStartTm(pMsg), dayOfTheWeek)) {
                // START 2023/03/08 K.Watanabe [QC#60065,DEL]
                //String ahsBillFlg = getAhsBillFlg(pMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
                //if (ZYPConstant.FLG_OFF_N.equals(ahsBillFlg)) {
                //    ZYPEZDItemValueSetter.setValue(pMsg.ahsCdEnblFlg, ZYPConstant.FLG_ON_Y);
                //    ZYPEZDItemValueSetter.setValue(pMsg.svcMinChrgHrsAot, BigDecimal.ZERO);
                //    ZYPEZDItemValueSetter.setValue(pMsg.svcLborUnitAmt, BigDecimal.ZERO);
                //    ZYPEZDItemValueSetter.setValue(pMsg.svcTrvlUnitAmt, BigDecimal.ZERO);
                //} else {
                //    BigDecimal ahsBillRate = null;
                //    if (ZYPConstant.FLG_ON_Y.equals(ahsBillFlg)) {
                //        ahsBillRate = getAhsBillRate(pMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
                //    }
                //    ZYPEZDItemValueSetter.setValue(pMsg.ahsCdEnblFlg, ZYPConstant.FLG_ON_Y);
                //    ZYPEZDItemValueSetter.setValue(pMsg.svcMinChrgHrsAot, BigDecimal.ONE);
                //    ZYPEZDItemValueSetter.setValue(pMsg.svcLborUnitAmt, ahsBillRate);
                //}
                // END 2023/03/08 K.Watanabe [QC#60065,DEL]
                // START 2023/03/08 K.Watanabe [QC#60065,ADD]
                ZYPEZDItemValueSetter.setValue(pMsg.ahsCdEnblFlg, ZYPConstant.FLG_ON_Y);
                // START 2023/03/23 K.Kitachi [QC#60065, DEL]
//                ZYPEZDItemValueSetter.setValue(pMsg.svcMinChrgHrsAot, BigDecimal.ZERO);
//                ZYPEZDItemValueSetter.setValue(pMsg.svcLborUnitAmt, BigDecimal.ZERO);
//                ZYPEZDItemValueSetter.setValue(pMsg.svcTrvlUnitAmt, BigDecimal.ZERO);
                // END 2023/03/23 K.Kitachi [QC#60065, DEL]
                // END 2023/03/08 K.Watanabe [QC#60065,ADD]
                // START 2023/03/23 K.Kitachi [QC#60065, ADD]
                SVC_BILL_TPTMsg svcBillTpTMsg = getSvcBillTpTMsg(pMsg.glblCmpyCd.getValue(), pMsg.svcBillTpCd.getValue());
                if (svcBillTpTMsg != null && ZYPConstant.FLG_ON_Y.equals(svcBillTpTMsg.lborChrgFlg.getValue())) {
                    BigDecimal ahsBillRate = getAhsBillRate(pMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
                    ZYPEZDItemValueSetter.setValue(pMsg.svcMinChrgHrsAot, BigDecimal.ONE);
                    ZYPEZDItemValueSetter.setValue(pMsg.svcLborUnitAmt, ahsBillRate);
                }
                // END 2023/03/23 K.Kitachi [QC#60065, ADD]

                // START 2018/07/18 M.Naito [QC#13309, ADD]
                //check Special Pricing
                Map<String, Object> svcLborDiscMap = getSvcLborDiscInfo(pMsg.glblCmpyCd.getValue(), svcMachMstrTMsg.svcMachMstrPk.getValue(), pMsg.startDt.getValue());
                if (svcLborDiscMap != null) {
                    BigDecimal svcLborDiscAmt = null;
                    if (hasValue((BigDecimal) svcLborDiscMap.get("SPCL_PRC_AMT"))) {
                        svcLborDiscAmt = (BigDecimal) svcLborDiscMap.get("SPCL_PRC_AMT");
                    } else if (hasValue((BigDecimal) svcLborDiscMap.get("SVC_LBOR_DISC_PCT"))) {
                        String ccyCd = pMsg.ccyCd.getValue();
                        Integer scale = getDealCcyDigit(pMsg.glblCmpyCd.getValue(), ccyCd);
                        svcLborDiscAmt = pMsg.svcLborUnitAmt.getValue().multiply(RATE_100.subtract((BigDecimal) svcLborDiscMap.get("SVC_LBOR_DISC_PCT"))).divide(RATE_100, scale, BigDecimal.ROUND_UP);
                    }
                    ZYPEZDItemValueSetter.setValue(pMsg.svcLborUnitAmt, svcLborDiscAmt);
                }
                // END 2018/07/18 M.Naito [QC#13309, ADD]
                return;
            }
        }

        // START 2018/07/18 M.Naito [QC#13309, ADD]
        // check Temporary Entitlement
        Map<String, Object> tempEttlMap = getTempEttlInfo(pMsg.glblCmpyCd.getValue(), svcMachMstrTMsg.svcMachMstrPk.getValue(), pMsg.startDt.getValue(), getStartTm(pMsg), dayOfTheWeek);
        if (tempEttlMap != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.ahsCdEnblFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pMsg.svcMinChrgHrsAot, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(pMsg.svcLborUnitAmt, (BigDecimal) tempEttlMap.get("LBOR_FEE_AMT"));
            return;
        }
        // END 2018/07/18 M.Naito [QC#13309, ADD]

        String ahsCd = getAhsCd(svcPrcShiftTmsg);
        // START 2018/01/18 U.Kim [QC#22668, MOD]
        // AHS_ENBL_OVRDTMsgArray ahsEnblOvrdTMsgArray = getAhsEnblOvrd(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), ahsCd, pMsg.startDt.getValue(), pMsg.startTm.getValue());
        AHS_ENBL_OVRDTMsgArray ahsEnblOvrdTMsgArray = getAhsEnblOvrd(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), ahsCd, pMsg.startDt.getValue(), getStartTm(pMsg));
        // END 2018/01/18 U.Kim [QC#22668, MOD]
        if (ahsEnblOvrdTMsgArray.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(pMsg.ahsCdEnblFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pMsg.svcMinChrgHrsAot, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(pMsg.svcLborUnitAmt, ahsEnblOvrdTMsgArray.no(0).svcLborUnitAmt);
            ZYPEZDItemValueSetter.setValue(pMsg.svcTrvlUnitAmt, ahsEnblOvrdTMsgArray.no(0).svcTrvlUnitAmt);
        } else {
            // START 2017/10/06 K.Kim [QC#20078,MOD]
            String afterHourAllwFlg = getAfterHourAllwFlg(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
            if (ZYPConstant.FLG_ON_Y.equals(afterHourAllwFlg)){
                // mod start 2020/06/15 QC#56229
                ahsEnblFlg = getAhsCdEnblFlg(pMsg.glblCmpyCd.getValue(), svcMachMstrTMsg.curLocNum.getValue(), svcMachMstrTMsg.svcByLineBizTpCd.getValue(), svcMachMstrTMsg.fldSvcBrCd.getValue(), ahsCd, pMsg.svcCallSrcTpCd.getValue());
                // mod end 2020/06/15 QC#56229
            // START 2018/05/23 K.Kitachi [QC#26295, ADD]
            } else {
                ahsEnblFlg = ZYPConstant.FLG_OFF_N;
            // END 2018/05/23 K.Kitachi [QC#26295, ADD]
            }
            // END 2017/10/06 K.Kim [QC#20078,MOD]
            if (ZYPConstant.FLG_ON_Y.equals(ahsEnblFlg)) {
                ZYPEZDItemValueSetter.setValue(pMsg.ahsCdEnblFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.ahsCdEnblFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMinChrgHrsAot, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(pMsg.svcLborUnitAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(pMsg.svcTrvlUnitAmt, BigDecimal.ZERO);
                // START 2018/01/18 U.Kim [QC#22668, ADD]
                ZYPEZDItemValueSetter.setValue(pMsg.xxAftHrsPopDplyFlg, ZYPConstant.FLG_OFF_N);
                // END 2018/01/18 U.Kim [QC#22668, ADD]
            }
        }
        // mod end 2016/04/15 CSA Defect#5489
    }

    // START 2017/01/04 K.Kojima [QC#16513,DEL]
    // /**
    //  * Get Coverage Information
    //  * 
    //  * @param pMsg NSZC061001PMsg
    //  * @param covTmplInfo CovTmplInfo
    //  * @param dsContrDtlTMsg DS_CONTR_DTLTMsg
    //  * @return OutputCovTmplInfo
    //  */
    // private void getCovInfo(NSZC061001PMsg pMsg, CovTmplInfo covTmplInfo, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
    //     if (dsContrDtlTMsg != null) {
    //         covTmplInfo.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
    //         covTmplInfo.setSvcPgmMdseCd(dsContrDtlTMsg.svcPgmMdseCd.getValue());
    //         covTmplInfo.setSlsDt(pMsg.startDt.getValue());
    //         NSXC001001GetCovTmpl nsxc001001GetCovTmpl = new NSXC001001GetCovTmpl();
    //         nsxc001001GetCovTmpl.getCovTmpl(covTmplInfo);
    //     }
    // }
    // END 2017/01/04 K.Kojima [QC#16513,DEL]

    // START 2017/01/04 K.Kojima [QC#16513,ADD]
    /**
     * Get Coverage Information
     * @param pMsg NSZC061001PMsg
     * @param covTmplInfo CovTmplInfo
     * @param contrDtlList List<DS_CONTR_DTLTMsg>
     */
    private void getCovInfo(NSZC061001PMsg pMsg, CovTmplInfo covTmplInfo, List<DS_CONTR_DTLTMsg> contrDtlList) {
        List<OutputCovTmplInfo> list = new ArrayList<OutputCovTmplInfo>();
        for (int i = 0; i < contrDtlList.size(); i++) {
            // START 2017/06/05 K.Kojima [QC#18295,ADD]
            String svcPgmMdseCd = getSvcPgmMdseCd(pMsg.glblCmpyCd.getValue(), contrDtlList.get(i).dsContrDtlPk.getValue());
            // END 2017/06/05 K.Kojima [QC#18295,ADD]
            covTmplInfo.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
            // START 2017/06/05 K.Kojima [QC#18295,MOD]
            // covTmplInfo.setSvcPgmMdseCd(contrDtlList.get(i).svcPgmMdseCd.getValue());
            covTmplInfo.setSvcPgmMdseCd(svcPgmMdseCd);
            // END 2017/06/05 K.Kojima [QC#18295,MOD]
            covTmplInfo.setSlsDt(pMsg.startDt.getValue());
            NSXC001001GetCovTmpl nsxc001001GetCovTmpl = new NSXC001001GetCovTmpl();
            nsxc001001GetCovTmpl.getCovTmpl(covTmplInfo);
            // START 2017/02/21 K.Kitachi [QC#17164, ADD]
            if (covTmplInfo.getOutputCovTmplInfoList() == null) {
                continue;
            }
            // END 2017/02/21 K.Kitachi [QC#17164, ADD]
            list.addAll(covTmplInfo.getOutputCovTmplInfoList());
        }
        covTmplInfo.setOutputCovTmplInfoList(list);
    }

    // END 2017/01/04 K.Kojima [QC#16513,ADD]

    /**
     * Get Day of the Week
     * 
     * @param glblCmpyCd Global Company Code
     * @param startDt Start Date
     * @return integer
     */
    private int getDayOfTheWeek(String glblCmpyCd, String startDt) {
        int dayOfTheWeek = DAT_OF_THE_WEEK_IS_HOLIDAY;
        // START 2023/05/24 T.Nagae [CSA-QC#61365, MOD]
//        CALTMsgArray calTMsgArray = getCal(glblCmpyCd, CAL_TP.CSA_HOLIDAY, DT_ATRB_VAL_CD, startDt);
        CALTMsgArray calTMsgArray = getCal(glblCmpyCd, CAL_TP.SVC_HOLIDAY, DT_ATRB_VAL_CD, startDt);
        // END   2023/05/24 T.Nagae [CSA-QC#61365, MOD]
        if (calTMsgArray.getValidCount() == 0) {
            Calendar cal = Calendar.getInstance();
            int yyyy = Integer.parseInt(startDt.substring(0, SUB_STR_POS_4));
            int mm = Integer.parseInt(startDt.substring(SUB_STR_POS_4, SUB_STR_POS_6));
            int dd = Integer.parseInt(startDt.substring(SUB_STR_POS_6, SUB_STR_POS_8));
            cal.set(yyyy, mm - 1, dd);
            dayOfTheWeek = cal.get(Calendar.DAY_OF_WEEK);
        }
        return dayOfTheWeek;
    }

    /**
     * Get Calendar
     * 
     * @param glblCmpyCd Global Company Code
     * @param calTpCd Calendar Type Code
     * @param dtAttrbValCd Date Attribute Value Code
     * @param calDt Calendar Date
     * @return CALTMsgArray
     */
    private CALTMsgArray getCal(String glblCmpyCd, String calTpCd, String dtAttrbValCd, String calDt) {
        CALTMsg tMsg = new CALTMsg();
        tMsg.setSQLID("903");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("calTpCd01", calTpCd);
        tMsg.setConditionValue("dtAttrbValCd01", dtAttrbValCd);
        tMsg.setConditionValue("calDt01", calDt);
        return (CALTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    // mod start 2016/04/15 CSA Defect#5489
    private SVC_PRC_SHIFTTMsg getSvcPrcShiftInfo(String glblCmpyCd, String startTm, int dayOfTheWeek, String svcByLineBizTpCd) {
        SVC_PRC_SHIFTTMsgArray svcPrcShiftTMsgArray = getSvcPrcShift(glblCmpyCd, startTm, dayOfTheWeek, svcByLineBizTpCd);
        if (svcPrcShiftTMsgArray.getValidCount() == 0) {
            return null;
        }
        return svcPrcShiftTMsgArray.no(0);
    }

    private String getAhsEnblFlg(SVC_PRC_SHIFTTMsg svcPrcShiftTMsg) {
        if (svcPrcShiftTMsg == null) {
            return ZYPConstant.FLG_ON_Y;
        }
        return svcPrcShiftTMsg.svcPrcShiftAhsFlg.getValue();
    }

    private String getAhsCd(SVC_PRC_SHIFTTMsg svcPrcShiftTMsg) {
        if (svcPrcShiftTMsg == null) {
            return "";
        }
        return svcPrcShiftTMsg.svcPrcShiftNum.getValue();
    }
    // mod end 2016/04/15 CSA Defect#5489

    // START 2023/03/08 K.Watanabe [QC#60065,DEL]
    // Mod Start 2018/03/07 QC#22668
    ///**
    // * Get After Hours Shift Billing Flag
    // * 
    // * @param glblCmpyCd Global Company Code
    // * @param dsContrPk Direct Sales Contract Primary Key
    // * @param dsContrDtlPk Direct Sales Contract Detail Primary Key
    // * @return String
    // */
    //private String getAhsBillFlg(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
    //    String svcTermCondAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(AH_BILL_FLG, glblCmpyCd);
    //    Map<String, Object> rtnMap = getSvcTermAttrbMapValCd(glblCmpyCd, svcTermCondAttrbNm, dsContrPk, dsContrDtlPk);
    //    if (rtnMap == null) {
    //        return null;
    //    }
    //    return (String) rtnMap.get("SVC_TERM_ATTRB_MAP_VAL_CD");
    //}
    // Mod End 2018/03/07 QC#22668
    // END 2023/03/08 K.Watanabe [QC#60065,DEL]

    // Add Start 2018/03/07 QC#22668
    private boolean isAhsForTermCondAhsWrkPgm(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String startTm, int dayOfTheWeek) {
        String svcTermCondAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_AFTER_HOURS_WRK_PGM, glblCmpyCd);
        Map<String, Object> rtnMap = getSvcTermAttrbMapValCd(glblCmpyCd, svcTermCondAttrbNm, dsContrPk, dsContrDtlPk);
        if (rtnMap == null) {
            return false;
        }

        String ahsCd = (String) rtnMap.get("SVC_TERM_ATTRB_MAP_VAL_CD");
        if (!hasValue(ahsCd)) {
            return false;
        }
        AHSTMsgArray ahsTMsgArray = getAhs(glblCmpyCd, ahsCd, startTm, dayOfTheWeek);
        if (ahsTMsgArray.getValidCount() == 0) {
            return false;
        }

        return true;
    }
    // Add End 2018/03/07 QC#22668

    /**
     * Get After Hours Shift Billing Rate
     * 
     * @param glblCmpyCd Global Company Code
     * @param dsContrPk Direct Sales Contract Primary Key
     * @param dsContrDtlPk Direct Sales Contract Detail Primary Key
     * @return BigDecimal
     */
    private BigDecimal getAhsBillRate(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        BigDecimal rtnVal = null;
        String svcTermCondAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(AH_BILL_RATE, glblCmpyCd);
        Map<String, Object> rtnMap = getSvcTermAttrbMapValCd(glblCmpyCd, svcTermCondAttrbNm, dsContrPk, dsContrDtlPk);
        if (rtnMap != null) {
            String billRate = (String) rtnMap.get("SVC_TERM_ATTRB_MAP_VAL_CD");
            if (ZYPCommonFunc.isNumberCheck(billRate)) {
                rtnVal = new BigDecimal(billRate);
            } else {
                rtnVal = null;
            }
        }
        return rtnVal;
    }

    /**
     * Get Service Term Attribute Map Value Code
     * 
     * @param glblCmpyCd Global Company Code
     * @param svcTermCondAttrbNm Service Term Condition Attribute Name
     * @param dsContrPk Direct Sales Contract Primary Key
     * @param dsContrDtlPk Direct Sales Contract Detail Primary Key
     * @return BigDecimal
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getSvcTermAttrbMapValCd(String glblCmpyCd, String svcTermCondAttrbNm, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcTermCondAttrbNm", svcTermCondAttrbNm);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        return (Map<String, Object>) ssmBatchClient.queryObject("getSvcTermAttrbMapValCd", ssmPrm);
    }

    /**
     * Get Coverage By Service Task
     * 
     * @param glblCmpyCd Global Company Code
     * @param svcTaskNum Service Task Number
     * @return SVC_BILL_TPTMsg
     */
    @SuppressWarnings("unchecked")
    private SVC_BILL_TPTMsg getSvcBillTp(String glblCmpyCd, String svcTaskNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcTaskNum", svcTaskNum);

        Map<String, Object> rsltMap = (Map<String, Object>) ssmBatchClient.queryObject("getCovBySvcTask", ssmPrm);

        if (rsltMap == null) {
            return null;
        }
        return getSvcBillTpTMsg(glblCmpyCd, (String) rsltMap.get("SVC_BILL_TP_CD"));
    }

    /**
     * Get Service Bill Type
     * 
     * @param glblCmpyCd Global Company Code
     * @param svcBillTpCd Service Bill Type Code
     * @return SVC_BILL_TPTMsg
     */
    private static SVC_BILL_TPTMsg getSvcBillTpTMsg(String glblCmpyCd, String svcBillTpCd) {
        SVC_BILL_TPTMsg tMsg = new SVC_BILL_TPTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.svcBillTpCd, svcBillTpCd);
        return (SVC_BILL_TPTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    /**
     * Get After Hours Shift Code
     * 
     * @param glblCmpyCd Global Company Code
     * @param ahsCd After Hours Shift Code
     * @param startTm Start Time
     * @param dayOfTheWeek Day Of The Week
     * @return String
     */
    private AHSTMsgArray getAhs(String glblCmpyCd, String ahsCd, String startTm, int dayOfTheWeek) {
        AHSTMsg tMsg = new AHSTMsg();
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        // add start 2016/04/15 CSA Defect#5489
        tMsg.setConditionValue("ahsCd01", ahsCd);
        // add end 2016/04/15 CSA Defect#5489
        if (dayOfTheWeek == Calendar.SUNDAY) {
            tMsg.setSQLID("001");
            tMsg.setConditionValue("ahsSunFromTm01", startTm);
            tMsg.setConditionValue("ahsSunToTm01", startTm);
        } else if (dayOfTheWeek == Calendar.MONDAY) {
            tMsg.setSQLID("002");
            tMsg.setConditionValue("ahsMonFromTm01", startTm);
            tMsg.setConditionValue("ahsMonToTm01", startTm);
        } else if (dayOfTheWeek == Calendar.TUESDAY) {
            tMsg.setSQLID("003");
            tMsg.setConditionValue("ahsTueFromTm01", startTm);
            tMsg.setConditionValue("ahsTueToTm01", startTm);
        } else if (dayOfTheWeek == Calendar.WEDNESDAY) {
            tMsg.setSQLID("004");
            tMsg.setConditionValue("ahsWedFromTm01", startTm);
            tMsg.setConditionValue("ahsWedToTm01", startTm);
        } else if (dayOfTheWeek == Calendar.THURSDAY) {
            tMsg.setSQLID("005");
            tMsg.setConditionValue("ahsThuFromTm01", startTm);
            tMsg.setConditionValue("ahsThuToTm01", startTm);
        } else if (dayOfTheWeek == Calendar.FRIDAY) {
            tMsg.setSQLID("006");
            tMsg.setConditionValue("ahsFriFromTm01", startTm);
            tMsg.setConditionValue("ahsFriToTm01", startTm);
        } else if (dayOfTheWeek == Calendar.SATURDAY) {
            tMsg.setSQLID("007");
            tMsg.setConditionValue("ahsSatFromTm01", startTm);
            tMsg.setConditionValue("ahsSatToTm01", startTm);
        } else if (dayOfTheWeek == DAT_OF_THE_WEEK_IS_HOLIDAY) {
            tMsg.setSQLID("008");
            // mod start 2017/06/02 CSA Defect#18768
            tMsg.setConditionValue("ahsHolFromTm01", startTm);
            tMsg.setConditionValue("ahsHolToTm01", startTm);
            // mod end 2017/06/02 CSA Defect#18768
        } else {
            return new AHSTMsgArray();
        }
        return (AHSTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    /**
     * Get After Hours Shift Enable Over
     * 
     * @param glblCmpyCd Global Company Code
     * @param svcMachMstrPk Service Machine Master Primary Key
     * @param ahsCd After Hours Shift Code
     * @param startDt Start Date
     * @param startTm Start Time
     * @return AHS_HRSTMsgArray
     */
    private AHS_ENBL_OVRDTMsgArray getAhsEnblOvrd(String glblCmpyCd, BigDecimal svcMachMstrPk, String ahsCd, String startDt, String startTm) {
        AHS_ENBL_OVRDTMsg tMsg = new AHS_ENBL_OVRDTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        // mod start 2016/05/19 CSA Defect#5489
        tMsg.setConditionValue("svcPrcShiftNum01", ahsCd);
        // mod end 2016/15/19 CSA Defect#5489
        tMsg.setConditionValue("effFromDt01", startDt.concat(startTm));
        tMsg.setConditionValue("effThruDt01", startDt.concat(startTm));
        return (AHS_ENBL_OVRDTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    /**
     * Get After Hours Shift Code Enable Flag
     * 
     * @param glblCmpyCd Global Company Code
     * @param curLocNum Current Location Number
     * @param svcByLineBizTpCd Serviced By Line of Business Type Code
     * @param fldSvcBrCd Field Service Branch Code
     * @param ahsCd Direct Sales Contract Detail Primary Key
     * @param svcCallSrcTpCd Service call source type code
     * @return String
     */
    private String getAhsCdEnblFlg(String glblCmpyCd, String curLocNum, String svcByLineBizTpCd, String fldSvcBrCd, String ahsCd, String svcCallSrcTpCd) {
        String rtnVal = "";
        // add start 2020/06/15 QC#56229
        if (SVC_CALL_SRC_TP.CROSS_SERVICE.equals(svcCallSrcTpCd)) {
            return ZYPConstant.FLG_OFF_N;
        }
        // add end 2020/06/15 QC#56229
        // Add Start 2018/07/26 QC#27418
        if (excludePostReln(glblCmpyCd, svcByLineBizTpCd)) {
            return ZYPConstant.FLG_ON_Y;
        }
        // Add End 2018/07/26 QC#27418
        SHIP_TO_CUSTTMsgArray shipToCust = getShipToCust(glblCmpyCd, curLocNum);
        if (shipToCust.getValidCount() > 0) {
            AHS_POST_RELNTMsgArray ahsPostRelnTMsgArray = getSvcMachMstr(glblCmpyCd, shipToCust.no(0).postCd.getValue(), svcByLineBizTpCd, fldSvcBrCd, ahsCd);
            if (ahsPostRelnTMsgArray.getValidCount() > 0) {
                rtnVal = ahsPostRelnTMsgArray.no(0).ahsCdEnblFlg.getValue();
            }
        }
        return rtnVal;
    }

    /**
     * Get Ship To Customer
     * 
     * @param glblCmpyCd Global Company Code
     * @param shipToCustCd Ship To Customer Code
     * @return SHIP_TO_CUSTTMsgArray
     */
    private SHIP_TO_CUSTTMsgArray getShipToCust(String glblCmpyCd, String shipToCustCd) {
        SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();
        tMsg.setSQLID("004");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        return (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    /**
     * Get After Hours Post Relation
     * 
     * @param glblCmpyCd Global Company Code
     * @param postCd Post Code
     * @param svcByLineBizCd Serviced By Line of Business Code
     * @param svcBrCd Service Branch Code
     * @param ahsCd Direct Sales Contract Detail Primary Key
     * @return AHS_POST_RELNTMsgArray
     */
    private AHS_POST_RELNTMsgArray getSvcMachMstr(String glblCmpyCd, String postCd, String svcByLineBizCd, String svcBrCd, String ahsCd) {
        AHS_POST_RELNTMsg tMsg = new AHS_POST_RELNTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("postCd01", postCd);
        tMsg.setConditionValue("svcLineBizCd01", svcByLineBizCd);
        tMsg.setConditionValue("svcBrCd01", svcBrCd);
        // mod start 2016/05/19 CSA Defect#5489
        tMsg.setConditionValue("svcPrcShiftNum01", ahsCd);
        // mod end 2016/05/19 CSA Defect#5489
        AHS_POST_RELNTMsgArray ahsPostRelnTmsgArray = (AHS_POST_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (ahsPostRelnTmsgArray.getValidCount() == 0) {
            tMsg.setConditionValue("svcLineBizCd01", ALL_BIZ_LINE_TP_CD);
            ahsPostRelnTmsgArray = (AHS_POST_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        }
        return ahsPostRelnTmsgArray;
    }

    /**
     * Get Service Labor Charge
     * 
     * @param glblCmpyCd Global Company Code
     * @param startTm Start Time
     * @param dayOfTheWeek Day Of The Week
     * @param svcMachMstrTMsg Service Machine Master TMsg
     * @param svcTaskNum
     * @return Map<String, Object>
     */
    private Map<String, Object> getSvcLborChrg(String glblCmpyCd, String startTm, int dayOfTheWeek, SVC_MACH_MSTRTMsg svcMachMstrTMsg, String svcTaskNum) {
        Map<String, Object> rtnMap = null;
        // mod start 2016/04/15 CSA Defect#5489
        SVC_PRC_SHIFTTMsgArray svcPrcShiftTMsgArray = getSvcPrcShift(glblCmpyCd, startTm, dayOfTheWeek, svcMachMstrTMsg.svcByLineBizTpCd.getValue());
        // mod end 2016/04/15 CSA Defect#5489
        if (svcPrcShiftTMsgArray.getValidCount() > 0) {
            // mod start 2016/06/07 CSA Defect#5489
            rtnMap = getSvcLborChrg(glblCmpyCd, svcMachMstrTMsg.svcMachMstrPk.getValue(), svcPrcShiftTMsgArray.no(0).svcPrcShiftNum.getValue(), svcMachMstrTMsg.svcByLineBizTpCd.getValue(), svcTaskNum);
            // mod end 2016/06/07 CSA Defect#5489
        }
        return rtnMap;
    }

    /**
     * Get Service Pricing Shift
     * 
     * @param glblCmpyCd Global Company Code
     * @param startTm Start Time
     * @param dayOfTheWeek Day Of The Week
     * @param svcByLineBizTpCd
     * @return SVC_PRC_SHIFTTMsgArray
     */
    private SVC_PRC_SHIFTTMsgArray getSvcPrcShift(String glblCmpyCd, String startTm, int dayOfTheWeek, String svcByLineBizTpCd) {
        SVC_PRC_SHIFTTMsg tMsg = new SVC_PRC_SHIFTTMsg();
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        // add start 2016/04/15 CSA Defect#5489
        tMsg.setConditionValue("svcLineBizCd01", svcByLineBizTpCd);
        tMsg.setConditionValue("svcPrcShiftActvFlg01", ZYPConstant.FLG_ON_Y);
        // add end 2016/04/15 CSA Defect#5489
        if (dayOfTheWeek == Calendar.SUNDAY) {
            tMsg.setSQLID("001");
            tMsg.setConditionValue("svcPrcSunStartValTxt01", startTm);
            tMsg.setConditionValue("svcPrcSunEndValTxt01", startTm);
        } else if (dayOfTheWeek == Calendar.MONDAY) {
            tMsg.setSQLID("002");
            tMsg.setConditionValue("svcPrcMonStartValTxt01", startTm);
            tMsg.setConditionValue("svcPrcMonEndValTxt01", startTm);
        } else if (dayOfTheWeek == Calendar.TUESDAY) {
            tMsg.setSQLID("003");
            tMsg.setConditionValue("svcPrcTueStartValTxt01", startTm);
            tMsg.setConditionValue("svcPrcTueEndValTxt01", startTm);
        } else if (dayOfTheWeek == Calendar.WEDNESDAY) {
            tMsg.setSQLID("004");
            tMsg.setConditionValue("svcPrcWedStartValTxt01", startTm);
            tMsg.setConditionValue("svcPrcWedEndValTxt01", startTm);
        } else if (dayOfTheWeek == Calendar.THURSDAY) {
            tMsg.setSQLID("005");
            tMsg.setConditionValue("svcPrcThuStartValTxt01", startTm);
            tMsg.setConditionValue("svcPrcThuEndValTxt01", startTm);
        } else if (dayOfTheWeek == Calendar.FRIDAY) {
            tMsg.setSQLID("006");
            tMsg.setConditionValue("svcPrcFriStartValTxt01", startTm);
            tMsg.setConditionValue("svcPrcFriEndValTxt01", startTm);
        } else if (dayOfTheWeek == Calendar.SATURDAY) {
            tMsg.setSQLID("007");
            tMsg.setConditionValue("svcPrcSatStartValTxt01", startTm);
            tMsg.setConditionValue("svcPrcSatEndValTxt01", startTm);
        } else if (dayOfTheWeek == DAT_OF_THE_WEEK_IS_HOLIDAY) {
            tMsg.setSQLID("008");
            // mod start 2017/06/02 CSA Defect#18768
            tMsg.setConditionValue("svcPrcHolStartValTxt01", startTm);
            tMsg.setConditionValue("svcPrcHolEndValTxt01", startTm);
            // mod end 2017/06/02 CSA Defect#18768
        } else {
            return new SVC_PRC_SHIFTTMsgArray();
        }
        SVC_PRC_SHIFTTMsgArray svcPrcShiftMsgArray = (SVC_PRC_SHIFTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (svcPrcShiftMsgArray.getValidCount() == 0) {
            tMsg.setConditionValue("svcLineBizCd01", ALL_BIZ_LINE_TP_CD);
            svcPrcShiftMsgArray = (SVC_PRC_SHIFTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        }
        return svcPrcShiftMsgArray;
    }

    /**
     * Get Service Labor Charge
     * 
     * @param glblCmpyCd Global Company Code
     * @param svcMachMstrPk Service Machine Master Primary Key
     * @param svcPrcShiftNum Service Pricing Shift Number
     * @param svcByLineBizTpCd Serviced By Line of Business Type Code
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    // mod start 2016/06/06 CSA Defect#5489, 2016/06/10 CSA Defect#8899
    private Map<String, Object> getSvcLborChrg(String glblCmpyCd, BigDecimal svcMachMstrPk, String svcPrcShiftNum, String svcLineBizCd, String svcTaskNum) {

        List<Map<String, Object>> mdlList = getMdlList(glblCmpyCd, svcMachMstrPk, svcTaskNum);
        for (Map<String, Object> mdlInfo : mdlList) {
            Map<String, Object> ssmPrm = new HashMap<String, Object>();
            ssmPrm.put("glblCmpyCd", glblCmpyCd);
            ssmPrm.put("svcPrcShiftNum", svcPrcShiftNum);
            ssmPrm.put("svcLineBizCd", svcLineBizCd);
            ssmPrm.put("mdlGrpId", mdlInfo.get("MDL_GRP_ID"));
            Map<String, Object> svcLborChrgMap = (Map<String, Object>) ssmBatchClient.queryObject("getSvcLborChrg", ssmPrm);
            if (svcLborChrgMap == null) {
                ssmPrm.put("svcLineBizCd", ALL_BIZ_LINE_TP_CD);
                svcLborChrgMap = (Map<String, Object>) ssmBatchClient.queryObject("getSvcLborChrg", ssmPrm);
            }
            if (svcLborChrgMap != null) {
                return svcLborChrgMap;
            }
        }
        return null;
    }
    // mod end 2016/06/06 CSA Defect#5489, 2016/06/10 CSA Defect#8899

    @SuppressWarnings("unchecked")
    // add 2016/06/10 CSA Defect#8899
    private List<Map<String, Object>> getMdlList(String glblCmpyCd, BigDecimal svcMachMstrPk, String svcTaskNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("svcTaskNum", svcTaskNum);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getMdlList", ssmPrm);
    }


    /**
     * Set Service Labor Charge
     * 
     * @param pMsg NSZC061001PMsg
     * @param svcLborChrgFlg Service Labor Charge Flag
     * @param svcLborChrgMap Service Labor Charge Map
     */
    private void setSvcLborChrgModeCreate(NSZC061001PMsg pMsg, String svcLborChrgFlg, Map<String, Object> svcLborChrgMap) {
        if (svcLborChrgMap != null
            && ZYPConstant.FLG_ON_Y.equals(svcLborChrgFlg)) {
            if (!hasValue(pMsg.svcLborUnitAmt)) {
                BigDecimal svcMinChrgHrsAot = (BigDecimal) svcLborChrgMap.get("SVC_MIN_CHRG_HRS_AOT");
                BigDecimal svcLborUnitAmt = (BigDecimal) svcLborChrgMap.get("SVC_LBOR_UNIT_AMT");
                ZYPEZDItemValueSetter.setValue(pMsg.svcMinChrgHrsAot, svcMinChrgHrsAot);
                ZYPEZDItemValueSetter.setValue(pMsg.svcLborUnitAmt, svcMinChrgHrsAot.multiply(svcLborUnitAmt));
                // add start 2015/12/16 CSA Defect#978
                String svcChrgCcyCd = (String) svcLborChrgMap.get("SVC_CHRG_CCY_CD");
                if (!pMsg.ccyCd.getValue().equals(svcChrgCcyCd)) {
                    exchFuncUnitPrice(pMsg, svcChrgCcyCd, true);
                    exchDealUnitPrice(pMsg, true);
                }
                // add end 2015/12/16 CSA Defect#978
            }
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.svcMinChrgHrsAot, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(pMsg.svcLborUnitAmt, BigDecimal.ZERO);
        }

        // START 2018/07/18 M.Naito [QC#13309, ADD]
        //check Special Pricing
        Map<String, Object> svcLborDiscMap = getSvcLborDiscInfo(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.startDt.getValue());
        if (svcLborDiscMap != null) {
            BigDecimal svcLborDiscAmt = null;
            if (hasValue((BigDecimal) svcLborDiscMap.get("SPCL_PRC_AMT"))) {
                svcLborDiscAmt = (BigDecimal) svcLborDiscMap.get("SPCL_PRC_AMT");
            } else if (hasValue((BigDecimal) svcLborDiscMap.get("SVC_LBOR_DISC_PCT"))) {
                String ccyCd = pMsg.ccyCd.getValue();
                Integer scale = getDealCcyDigit(pMsg.glblCmpyCd.getValue(), ccyCd);
                svcLborDiscAmt = pMsg.svcLborUnitAmt.getValue().multiply(RATE_100.subtract((BigDecimal) svcLborDiscMap.get("SVC_LBOR_DISC_PCT"))).divide(RATE_100, scale, BigDecimal.ROUND_UP);
            }
            if (hasValue(svcLborDiscAmt)) {
                ZYPEZDItemValueSetter.setValue(pMsg.svcLborUnitAmt, svcLborDiscAmt);
            }
        }
        // END 2018/07/18 M.Naito [QC#13309, ADD]
    }
    // START 2018/12/05 K.Kim [QC#28633, MOD]
    /**
     * Set Service Travel Charge
     * 
     * @param pMsg NSZC061001PMsg
     * @param svcMachMstrTMsg Service Machine Master TMsg
     */
    // private void setSvcTrvlChrgModeCreate(NSZC061001PMsg pMsg, Map<String, Object> svcLborChrgMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
    private void setSvcTrvlChrgModeCreate(NSZC061001PMsg pMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        // //mod start 2016/04/12 CSA Defect#6693
        // String lborChrgFlg = getSvcBillTpTMsg(pMsg.glblCmpyCd.getValue(), pMsg.svcBillTpCd.getValue()).lborChrgFlg.getValue();
        // if (svcLborChrgMap != null && ZYPConstant.FLG_ON_Y.equals(lborChrgFlg)
        //     && ZYPConstant.FLG_ON_Y.equals((String) svcLborChrgMap.get("SVC_PRC_TRVL_CHRG_FLG"))) {
        // //mod end 2016/04/12 CSA Defect#6693
        String trvlChrgFlg = getSvcBillTpTMsg(pMsg.glblCmpyCd.getValue(), pMsg.svcBillTpCd.getValue()).trvlChrgFlg.getValue();
        if (ZYPConstant.FLG_ON_Y.equals(trvlChrgFlg)) {
        // END 2018/12/05 K.Kim [QC#28633, MOD]
            Map<String, Object> svcTrvlChrgMap = getSvcTrvlChrg(pMsg.glblCmpyCd.getValue(), svcMachMstrTMsg.svcMachMstrPk.getValue(), svcMachMstrTMsg.svcZnCd.getValue(), svcMachMstrTMsg.svcByLineBizTpCd.getValue(), null);
            if (!hasValue(pMsg.svcTrvlUnitAmt) && svcTrvlChrgMap != null) {
                ZYPEZDItemValueSetter.setValue(pMsg.svcTrvlUnitAmt, (BigDecimal) svcTrvlChrgMap.get("SVC_TRVL_UNIT_AMT"));
                // add start 2015/12/16 CSA Defect#978
                String svcChrgCcyCd = (String) svcTrvlChrgMap.get("SVC_CHRG_CCY_CD");
                if (!pMsg.ccyCd.getValue().equals(svcChrgCcyCd)) {
                    exchFuncUnitPrice(pMsg, svcChrgCcyCd, false);
                    exchDealUnitPrice(pMsg, false);
                }
                // add end 2015/12/16 CSA Defect#978
            }
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.svcTrvlUnitAmt, BigDecimal.ZERO);
        }
    }

    /**
     * Get Service Travel Charge
     * 
     * @param glblCmpyCd Global Company Code
     * @param svcMachMstrPk Service Machine Master Primary Key
     * @param svcZnCd Service Zone Code
     * @param svcByLineBizTpCd Serviced By Line of Business Type Code
     * @param svcTaskNum Serviced Task Number
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    // mod start 2016/06/10 CSA Defect#8899
    private Map<String, Object> getSvcTrvlChrg(String glblCmpyCd, BigDecimal svcMachMstrPk, String svcZnCd, String svcLineBizCd, String svcTaskNum) {
        List<Map<String, Object>> mdlList = getMdlList(glblCmpyCd, svcMachMstrPk, svcTaskNum);
        for (Map<String, Object> mdlInfo : mdlList) {
            Map<String, Object> ssmPrm = new HashMap<String, Object>();
            ssmPrm.put("glblCmpyCd", glblCmpyCd);
            ssmPrm.put("svcZnCd", svcZnCd);
            ssmPrm.put("svcLineBizCd", svcLineBizCd);
            ssmPrm.put("mdlGrpId", mdlInfo.get("MDL_GRP_ID"));
            Map<String, Object> svcTrvlChrgMap = (Map<String, Object>) ssmBatchClient.queryObject("getSvcTrvlChrg", ssmPrm);
            if (svcTrvlChrgMap == null) {
                ssmPrm.put("svcLineBizCd", ALL_BIZ_LINE_TP_CD);
                svcTrvlChrgMap = (Map<String, Object>) ssmBatchClient.queryObject("getSvcTrvlChrg", ssmPrm);
            }
            if (svcTrvlChrgMap != null) {
                return svcTrvlChrgMap;
            }
        }
        return null;
    }
    // mod end 2016/06/10 CSA Defect#8899

    /**
     * Call Close
     * 
     * @param msgMap S21ApiMessageMap
     * @param onBatTp ONBATCH_TYPE
     */
    private void callClose(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp) {

        vldCallClose(msgMap, onBatTp);
        if (hasErrMsg(msgMap)) {
            return;
        }

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(msgMap, onBatTp);

        if (hasErrMsg(msgMap)) {
            return;
        }

        procCallClose(msgMap, svcMachMstrTMsg, onBatTp);

    }

    /**
     * Validate "Call Close"
     * 
     * @param msgMap S21ApiMessageMap
     * @param onBatTp ONBATCH_TYPE
     */
    private void vldCallClose(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp) {

        NSZC061001PMsg pMsg = (NSZC061001PMsg) msgMap.getPmsg();

        // START 2017/03/02 K.Ochiai [QC#17715, MOD]
        if (!hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NSZM0001E);
        }

        if (!hasValue(pMsg.svcMachMstrPk)) {
            msgMap.addXxMsgId(NSZM0074E);
        }

        if (!hasValue(pMsg.fsrNum)) {
            msgMap.addXxMsgId(NSZM0291E);
        }

//        if (pMsg.svcTaskNumList.getValidCount() <= 0) {
//            msgMap.addXxMsgId(NSZM0302E);
//        }

        for (int i = 0; i < pMsg.svcTaskNumList.getValidCount(); i++) {
            if (!hasValue(pMsg.svcTaskNumList.no(i).svcTaskNum)) {
                msgMap.addXxMsgId(NSZM0082E);
            }
            if (!hasValue(pMsg.svcTaskNumList.no(i).fsrVisitArvDt)) {
                msgMap.addXxMsgId(NSZM0214E);
            }
            if (!hasValue(pMsg.svcTaskNumList.no(i).fsrVisitArvTm)) {
                msgMap.addXxMsgId(NSZM0215E);
            }
            if (!hasValue(pMsg.svcTaskNumList.no(i).svcLborTmNum)) {
                msgMap.addXxMsgId(NSZM0247E);
            }
            if (!hasValue(pMsg.svcTaskNumList.no(i).svcTrvlTmNum)) {
                msgMap.addXxMsgId(NSZM0218E);
            }
        }
        // END 2017/03/02 K.Ochiai [QC#17715, MOD]

        if (hasErrMsg(msgMap)) {
            return;
        }
    }

    /**
     * Process "Call Close"
     * 
     * @param msgMap S21ApiMessageMap
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param onBatTp ONBATCH_TYPE
     */
    private void procCallClose(S21ApiMessageMap msgMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg, ONBATCH_TYPE onBatTp) {

        NSZC061001PMsg pMsg = (NSZC061001PMsg) msgMap.getPmsg();

        // START 2018/05/25 K.Kim [QC#15410(Sol#246),MOD]
        // DS_CONTR_DTLTMsg dsContrDtlTMsg = NSXC001001GetContr.getContrDtlByMachMstrPk(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.startDt.getValue());
        // if (dsContrDtlTMsg == null) {
        //     dsContrDtlTMsg = NSXC001001GetContr.getContrDtlByMachMstrPkInclWarranty(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.startDt.getValue());
        // }
        DS_CONTR_DTLTMsg dsContrDtlTMsg = null;
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList = NSXC001001GetContr.getContrDtlByMachMstrPkList(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.startDt.getValue());
        if (dsContrDtlTMsgList.size() == 0) {
            dsContrDtlTMsgList = NSXC001001GetContr.getContrDtlByMachMstrPkInclWarrantyList(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.startDt.getValue());
        }
        // END 2018/05/25 K.Kim [QC#15410(Sol#246),MOD]

        FSRTMsg fsrTMsg = getFSR(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (fsrTMsg == null) {
            // START 2017/03/02 K.Ochiai [QC#17715, MOD]
            msgMap.addXxMsgId(NSZM0291E);
            // END 2017/03/02 K.Ochiai [QC#17715, MOD]
            return;
        }

        setSvcTaskInfo(pMsg);
        // Add Start 2019/02/22 QC#30413
        String billTpList = ZYPCodeDataUtil.getVarCharConstValue(EXCL_PRT_DISC_SVC_BILL_TP, pMsg.glblCmpyCd.getValue());
        if (hasValue(billTpList)) {
            this.exclPrtDiscBillTpList = Arrays.asList(billTpList.split(","));
        } else {
            this.exclPrtDiscBillTpList = new ArrayList<String>();
        }
        // Add End 2019/02/22 QC#30413
        // START 2018/05/25 K.Kim [QC#15410(Sol#246),MOD]
        int j = 0;
        do {
            if (dsContrDtlTMsgList.size() != 0){
                dsContrDtlTMsg = dsContrDtlTMsgList.get(j);
            }
            // Add Start 2017/07/26 QC#17129
            String svcLineBizCd = svcMachMstrTMsg.svcByLineBizTpCd.getValue();
            // QC#29042 del
//          if (dsContrDtlTMsg != null) {
//              DS_CONTRTMsg dsContrTMsg = getDsContrTMsgForPk(dsContrDtlTMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrPk.getValue());
//              if (dsContrTMsg != null) {
//                  svcLineBizCd = dsContrTMsg.svcLineBizCd.getValue();
//              }
//          }
            this.partsPrcCatgCd = getVarCharConstPrcCatgCd(pMsg.glblCmpyCd.getValue(), COA_PROJ.PARTS, svcLineBizCd);
            this.drumPrcCatgCd = getVarCharConstPrcCatgCd(pMsg.glblCmpyCd.getValue(), COA_PROJ.DRUM, svcLineBizCd);
            // Add End 2017/07/26 QC#17129

            for (int i = 0; i < pMsg.svcTaskNumList.getValidCount(); i++) {
                SVC_BILL_TPTMsg svcBillTPTMsg = getSvcBillTp(pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNumList.no(i).svcTaskNum.getValue());
                if (svcBillTPTMsg == null) {
                    msgMap.addXxMsgId(NSZM0302E);
                    return;
                }

                int dayOfTheWeek = getDayOfTheWeek(pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNumList.no(i).fsrVisitArvDt.getValue());

                setAhsInfoModeClose(pMsg, dsContrDtlTMsg, svcMachMstrTMsg, dayOfTheWeek, i);

                // mod start 2016/04/26 CSA Defect#7519
                //if (!ZYPConstant.FLG_OFF_N.equals(pMsg.xxSvcTaskChrgList.no(i).ahsCdEnblFlg.getValue())) {
                Map<String, Object> svcLborChrgMap = getSvcLborChrg(pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNumList.no(i).fsrVisitArvTm.getValue(), dayOfTheWeek, svcMachMstrTMsg, pMsg.svcTaskNumList.no(i).svcTaskNum.getValue());
                setSvcLborChrgModeClose(pMsg, svcBillTPTMsg.lborChrgFlg.getValue(), svcLborChrgMap, svcBillTPTMsg.freeHrsNum.getValue(), i);
                // mod start 2016/04/12 CSA Defect#6693
                // START 2018/12/05 K.Kim [QC#28633, MOD]
                // setSvcTrvlChrgModeClose(pMsg, svcBillTPTMsg.lborChrgFlg.getValue(), svcLborChrgMap, svcMachMstrTMsg, i);
                setSvcTrvlChrgModeClose(pMsg, svcBillTPTMsg.trvlChrgFlg.getValue(), svcMachMstrTMsg, i);
                // END 2018/12/05 K.Kim [QC#28633, MOD]
                // mod end 2016/04/12 CSA Defect#6693
                // START 2018/12/04 K.Kitachi [QC#28635, MOD]
//                setPrtChrg(msgMap, onBatTp, pMsg, svcBillTPTMsg.prtChrgFlg.getValue(), svcMachMstrTMsg, fsrTMsg, i);
//                setExpChrg(msgMap, onBatTp, pMsg, svcBillTPTMsg.expChrgFlg.getValue(), svcMachMstrTMsg, fsrTMsg, i);
//                setDrumChrg(msgMap, onBatTp, pMsg, svcBillTPTMsg.drumChrgFlg.getValue(), svcMachMstrTMsg, fsrTMsg, i);
                setPrtChrg(msgMap, onBatTp, pMsg, svcBillTPTMsg.prtChrgFlg.getValue(), svcMachMstrTMsg, fsrTMsg, i, dsContrDtlTMsg);
                setExpChrg(msgMap, onBatTp, pMsg, svcBillTPTMsg.expChrgFlg.getValue(), svcMachMstrTMsg, fsrTMsg, i, dsContrDtlTMsg);
                setDrumChrg(msgMap, onBatTp, pMsg, svcBillTPTMsg.drumChrgFlg.getValue(), svcMachMstrTMsg, fsrTMsg, i, dsContrDtlTMsg);
                // END 2018/12/04 K.Kitachi [QC#28635, MOD]
                //}
                // mod end 2016/04/26 CSA Defect#7519
            }
            j++;
        } while (j < dsContrDtlTMsgList.size());
        // END 2018/05/25 K.Kim [QC#15410(Sol#246),MOD]
    }

    /**
     * Get Field Service Report
     * 
     * @param glblCmpyCd Global Company Code
     * @param fsrNum Field Service Report Number
     * @return FSRTMsg
     */
    private FSRTMsg getFSR(String glblCmpyCd, String fsrNum) {
        FSRTMsg tMsg = new FSRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.fsrNum, fsrNum);
        return (FSRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    /**
     * Set Service Task Information
     * 
     * @param pMsg NSZC061001PMsg
     */
    private void setSvcTaskInfo(NSZC061001PMsg pMsg) {
        // mod start 2015/12/16 CSA Defect#978
        int i = 0;
        for (; i < pMsg.svcTaskNumList.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(i).svcTaskNum, pMsg.svcTaskNumList.no(i).svcTaskNum);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(i).svcLborTmNum, pMsg.svcTaskNumList.no(i).svcLborTmNum);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(i).svcTrvlTmNum, pMsg.svcTaskNumList.no(i).svcTrvlTmNum);
        }
        pMsg.xxSvcTaskChrgList.setValidCount(i);
        int cntDrumChrg = 0;
        int cntPartsChrg = 0;
        i = 0;
        for (; i < pMsg.partsMdseList.getValidCount(); i++) {
            MDSETMsg mdseTMsg = getMdse(pMsg.glblCmpyCd.getValue(), pMsg.partsMdseList.no(i).mdseCd.getValue());
            if (mdseTMsg != null) {
                if (COA_PROJ.DRUM.equals(mdseTMsg.coaMdseTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.xxDrumChrgList.no(cntDrumChrg).svcTaskNum, pMsg.partsMdseList.no(i).svcTaskNum);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxDrumChrgList.no(cntDrumChrg).mdseCd, pMsg.partsMdseList.no(i).mdseCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxDrumChrgList.no(cntDrumChrg).svcPrtQty, pMsg.partsMdseList.no(i).svcPrtQty);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxDrumChrgList.no(cntDrumChrg).pkgUomCd, pMsg.partsMdseList.no(i).pkgUomCd);
                    cntDrumChrg++;
                } else {
                    ZYPEZDItemValueSetter.setValue(pMsg.xxPartsChrgList.no(cntPartsChrg).svcTaskNum, pMsg.partsMdseList.no(i).svcTaskNum);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxPartsChrgList.no(cntPartsChrg).mdseCd, pMsg.partsMdseList.no(i).mdseCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxPartsChrgList.no(cntPartsChrg).svcPrtQty, pMsg.partsMdseList.no(i).svcPrtQty);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxPartsChrgList.no(cntPartsChrg).pkgUomCd, pMsg.partsMdseList.no(i).pkgUomCd);
                    cntPartsChrg++;
                }
            }
        }
        pMsg.xxDrumChrgList.setValidCount(cntDrumChrg);
        pMsg.xxPartsChrgList.setValidCount(cntPartsChrg);

        i = 0;
        for (; i < pMsg.expMdseList.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxExpMdseList.no(i).svcTaskNum, pMsg.expMdseList.no(i).svcTaskNum);
            ZYPEZDItemValueSetter.setValue(pMsg.xxExpMdseList.no(i).mdseCd, pMsg.expMdseList.no(i).mdseCd);
            ZYPEZDItemValueSetter.setValue(pMsg.xxExpMdseList.no(i).expQty, pMsg.expMdseList.no(i).expQty);
            ZYPEZDItemValueSetter.setValue(pMsg.xxExpMdseList.no(i).pkgUomCd, pMsg.expMdseList.no(i).pkgUomCd);
        }
        pMsg.xxExpMdseList.setValidCount(i);
        // mod end 2015/12/16 CSA Defect#978
    }

    /**
     * Get Merchandise
     * 
     * @param glblCmpyCd Global Company Code
     * @param mdseCd Merchandise
     * @return FSRTMsg
     */
    private MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg tMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
        return (MDSETMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    /**
     * Set After Hours Shift Information
     * 
     * @param pMsg NSZC061001PMsg
     * @param dsContrDtlTMsgArray Direct Sales Contract Detail TMsg Array
     * @param svcMachMstrTMsg Service Machine Master TMsg
     * @param dayOfTheWeek Day Of The Week
     * @param cntSvcTask Count Service Task
     */
    private void setAhsInfoModeClose(NSZC061001PMsg pMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, int dayOfTheWeek, int cntSvcTask) {
        // START 2018/07/18 M.Naito [QC#13309, ADD]
        // check Temporary Entitlement
        FSRTMsg fsrTMsg = getFSR(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (fsrTMsg != null && hasValue(fsrTMsg.tempEttlNum)) {
            BigDecimal svcLborUnitAmt = getTempEttlLborFee(pMsg.glblCmpyCd.getValue(), fsrTMsg.tempEttlNum.getValue(), pMsg.svcMachMstrPk.getValue());

            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).ahsCdEnblFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitHrsAot, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitAmt, svcLborUnitAmt);
            return;
        }
        // END 2018/07/18 M.Naito [QC#13309, ADD]

        // mod start 2016/04/15 CSA Defect#5489
        SVC_PRC_SHIFTTMsg svcPrcShiftTmsg = getSvcPrcShiftInfo(pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNumList.no(cntSvcTask).fsrVisitArvTm.getValue(), dayOfTheWeek, svcMachMstrTMsg.svcByLineBizTpCd.getValue());
        String ahsEnblFlg = getAhsEnblFlg(svcPrcShiftTmsg);

        // Not After Hours
        if (ZYPConstant.FLG_OFF_N.equals(ahsEnblFlg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).ahsCdEnblFlg, "");
            return;
        }

        // After Hours
        if (dsContrDtlTMsg == null) {
            return;
        }
        // Mod Start 2018/03/07 QC#22668
        if (isAhsForTermCondAhsWrkPgm(pMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), pMsg.svcTaskNumList.no(cntSvcTask).fsrVisitArvTm.getValue(), dayOfTheWeek)) {
            // START 2023/03/08 K.Watanabe [QC#60065,DEL]
            //String ahsBillFlg = getAhsBillFlg(pMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            //if (ZYPConstant.FLG_OFF_N.equals(ahsBillFlg)) {
            //    ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).ahsCdEnblFlg, ZYPConstant.FLG_ON_Y);
            //    ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitHrsAot, BigDecimal.ZERO);
            //    ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitAmt, BigDecimal.ZERO);
            //    ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlUnitHrsAot, BigDecimal.ZERO);
            //    ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlUnitAmt, BigDecimal.ZERO);
            //} else {
            //    BigDecimal ahsBillRate = null;
            //    if (ZYPConstant.FLG_ON_Y.equals(ahsBillFlg)) {
            //        ahsBillRate = getAhsBillRate(pMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            //    }
            //    ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).ahsCdEnblFlg, ZYPConstant.FLG_ON_Y);
            //    ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitHrsAot, BigDecimal.ONE);
            //    ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitAmt, ahsBillRate);
            //}
            //return;
            // END 2023/03/08 K.Watanabe [QC#60065,DEL]
            // START 2023/03/08 K.Watanabe [QC#60065,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).ahsCdEnblFlg, ZYPConstant.FLG_ON_Y);
            // START 2023/03/23 K.Kitachi [QC#60065, ADD]
            SVC_BILL_TPTMsg svcBillTpTMsg = getSvcBillTp(pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNumList.no(cntSvcTask).svcTaskNum.getValue());
            if (svcBillTpTMsg != null && ZYPConstant.FLG_ON_Y.equals(svcBillTpTMsg.lborChrgFlg.getValue())) {
                BigDecimal ahsBillRate = getAhsBillRate(pMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitHrsAot, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitAmt, ahsBillRate);
            }
            // END 2023/03/23 K.Kitachi [QC#60065, ADD]
            // START 2023/03/23 K.Kitachi [QC#60065, DEL]
//        } else {
//            BigDecimal ahsBillRate = null;
//            ahsBillRate = getAhsBillRate(pMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
//            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).ahsCdEnblFlg, ZYPConstant.FLG_ON_Y);
//            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitHrsAot, BigDecimal.ONE);
//            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitAmt, ahsBillRate);
            // END 2023/03/23 K.Kitachi [QC#60065, DEL]
            // END 2023/03/08 K.Watanabe [QC#60065,ADD]
        }
        // Mod End 2018/03/07 QC#22668
        String ahsCd = getAhsCd(svcPrcShiftTmsg);
        AHS_ENBL_OVRDTMsgArray ahsEnblOvrdTMsgArray = getAhsEnblOvrd(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), ahsCd, pMsg.svcTaskNumList.no(cntSvcTask).fsrVisitArvDt.getValue(), pMsg.svcTaskNumList.no(cntSvcTask).fsrVisitArvTm.getValue());
        if (ahsEnblOvrdTMsgArray.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).ahsCdEnblFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitHrsAot, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitAmt, ahsEnblOvrdTMsgArray.no(0).svcLborUnitAmt);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlUnitHrsAot, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlUnitAmt, ahsEnblOvrdTMsgArray.no(0).svcTrvlUnitAmt);
        }
        // mod end 2016/04/15 CSA Defect#5489
    }

    /**
     * Set Service Labor Charge
     * 
     * @param pMsg NSZC061001PMsg
     * @param svcLborChrgFlg Service Labor Charge Flag
     * @param svcLborChrgMap Service Labor Charge Map
     * @param cntSvcTask Count Service Task
     */
    private void setSvcLborChrgModeClose(NSZC061001PMsg pMsg, String svcLborChrgFlg, Map<String, Object> svcLborChrgMap, BigDecimal freeHrsNum, int cntSvcTask) {
        if (svcLborChrgMap == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborTmNum, pMsg.svcTaskNumList.no(cntSvcTask).svcLborTmNum);
        // mod end 2016/04/14 CSA Defect#6927
        if (!hasValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitAmt)) {
            BigDecimal svcLborUnitHrsAot = (BigDecimal) svcLborChrgMap.get("SVC_MIN_CHRG_HRS_AOT");
            if (!hasValue(svcLborUnitHrsAot)) {
                svcLborUnitHrsAot = BigDecimal.ZERO;
            }
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitHrsAot, svcLborUnitHrsAot);
            // mod start 2016/10/13 CSA Defect#13987
            // mod start 2016/09/20 CSA Defect#13987
//            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitAmt, (BigDecimal) svcLborChrgMap.get("SVC_LBOR_UNIT_AMT"));
            if (hasValue(pMsg.svcTaskNumList.no(cntSvcTask).ovrdSvcChrgUnitAmt_LB)) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitAmt, pMsg.svcTaskNumList.no(cntSvcTask).ovrdSvcChrgUnitAmt_LB);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitAmt, (BigDecimal) svcLborChrgMap.get("SVC_LBOR_UNIT_AMT"));
            }
            // mod end 2016/09/20 CSA Defect#13987
            // mod end 2016/10/13 CSA Defect#13987
        }
        // START 2018/07/18 M.Naito [QC#13309, ADD]
        // check Temporary Entitlement
        FSRTMsg fsrTMsg = getFSR(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (fsrTMsg != null && !hasValue(fsrTMsg.tempEttlNum)) {
            // check Special Pricing
            Map<String, Object> svcLborDiscMap = getSvcLborDiscInfo(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.startDt.getValue());
            if (svcLborDiscMap != null) {
                BigDecimal svcLborDiscAmt = BigDecimal.ZERO;
                if (hasValue((BigDecimal) svcLborDiscMap.get("SPCL_PRC_AMT"))) {
                    svcLborDiscAmt = (BigDecimal) svcLborDiscMap.get("SPCL_PRC_AMT");
                } else if (hasValue((BigDecimal) svcLborDiscMap.get("SVC_LBOR_DISC_PCT"))) {
                    String ccyCd = pMsg.ccyCd.getValue();
                    Integer scale = getDealCcyDigit(pMsg.glblCmpyCd.getValue(), ccyCd);
                    svcLborDiscAmt = pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitAmt.getValue().multiply(RATE_100.subtract((BigDecimal) svcLborDiscMap.get("SVC_LBOR_DISC_PCT"))).divide(RATE_100, scale, BigDecimal.ROUND_UP);
                }
                ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitAmt, svcLborDiscAmt);
            }
        }
        // END 2018/07/18 M.Naito [QC#13309, ADD]

        // mod Start 2016/04/26 CSA Defect#7519
        ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).intgMdseCd_L, (String) svcLborChrgMap.get("INTG_MDSE_CD"));
        // mod end 2016/04/26 CSA Defect#7519
        // mod end 2016/04/14 CSA Defect#6927
        BigDecimal svcLborChrgAmt = BigDecimal.ZERO;
        BigDecimal svcLborTmNum = BigDecimal.ZERO;
        // mod start 2016/10/13 CSA Defect#13987
        // mod start 2016/09/20 CSA Defect#13987
        if (hasValue(pMsg.svcTaskNumList.no(cntSvcTask).svcChrgQty_LB)) {
            if (pMsg.svcTaskNumList.no(cntSvcTask).svcChrgQty_LB.getValue().compareTo(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitHrsAot.getValue().multiply(DIVIDE_TIME_60MIN)) <= 0) {
                svcLborTmNum = pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitHrsAot.getValue().multiply(DIVIDE_TIME_60MIN);
            } else {
                svcLborTmNum = pMsg.svcTaskNumList.no(cntSvcTask).svcChrgQty_LB.getValue().divide(DIVIDE_TIME_15MIN, BigDecimal.ROUND_UP).multiply(DIVIDE_TIME_15MIN);
            }
        } else {
            // mod start 2016/04/14 CSA Defect#6927
            if (pMsg.svcTaskNumList.no(cntSvcTask).svcLborTmNum.getValue().compareTo(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitHrsAot.getValue().multiply(DIVIDE_TIME_60MIN)) <= 0) {
            // mod end 2016/04/14 CSA Defect#6927
                svcLborTmNum = pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitHrsAot.getValue().multiply(DIVIDE_TIME_60MIN);
            } else {
                svcLborTmNum = pMsg.svcTaskNumList.no(cntSvcTask).svcLborTmNum.getValue().divide(DIVIDE_TIME_15MIN, BigDecimal.ROUND_UP).multiply(DIVIDE_TIME_15MIN);
            }
        }
        // mod end 2016/09/20 CSA Defect#13987
        // mod end 2016/10/13 CSA Defect#13987
        svcLborChrgAmt = svcLborTmNum.divide(DIVIDE_TIME_60MIN, 2, BigDecimal.ROUND_UP).multiply(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitAmt.getValue()).setScale(2, BigDecimal.ROUND_UP);
        ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborDealAmt, svcLborChrgAmt);
        // mod start 2016/05/20 CSA Defect#8480
        if (ZYPConstant.FLG_ON_Y.equals(svcLborChrgFlg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborDiscRate, BigDecimal.ZERO);
            if (hasValue(freeHrsNum)) {
                BigDecimal svcLborDealDiscAmt = freeHrsNum.multiply(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitAmt.getValue()).setScale(2, BigDecimal.ROUND_UP);
                ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborDealDiscAmt, svcLborDealDiscAmt);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborDealDiscAmt, BigDecimal.ZERO);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborDiscRate, RATE_100);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborDealDiscAmt, svcLborChrgAmt);
        }
        // mod end 2016/05/20 CSA Defect#8480
        // add start 2015/12/16 CSA Defect#978
        String svcChrgCcyCd = (String) svcLborChrgMap.get("SVC_CHRG_CCY_CD");
        if (!pMsg.ccyCd.getValue().equals(svcChrgCcyCd)) {
            exchFuncUnitPrice(pMsg, svcChrgCcyCd, cntSvcTask, true);
            exchDealUnitPrice(pMsg, cntSvcTask, true);
        }
        // add end 2015/12/16 CSA Defect#978

        // START 2017/10/02 K.Kim [QC#20642,ADD]
        if (hasValue(pMsg.svcTaskNumList.no(cntSvcTask).ovrdSvcChrgUnitAmt_LB)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborDealAmt, pMsg.svcTaskNumList.no(cntSvcTask).ovrdSvcChrgUnitAmt_LB);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborDiscRate, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborDealDiscAmt, BigDecimal.ZERO);
        }
        // END 2017/10/02 K.Kim [QC#20642,ADD]
    }

    // START 2018/12/05 K.Kim [QC#28633, MOD]
    /**
     * Set Service Travel Charge
     * 
     * @param pMsg NSZC061001PMsg
     * @param trvlChrgFlg 
     * @param svcMachMstrTMsg Service Machine Master TMsg
     * @param cntSvcTask Count Service Task
     */
    // private void setSvcTrvlChrgModeClose(NSZC061001PMsg pMsg, String svcLborChrgFlg, Map<String, Object> svcLborChrgMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg, int cntSvcTask) {
    private void setSvcTrvlChrgModeClose(NSZC061001PMsg pMsg, String trvlChrgFlg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, int cntSvcTask) {
    //     if (svcLborChrgMap == null) {
    //         return;
    //     }
    // END 2018/12/05 K.Kim [QC#28633, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlTmNum, pMsg.svcTaskNumList.no(cntSvcTask).svcTrvlTmNum.getValue());
        // mod start 2016/02/22 CSA Defect#4514
        SVC_TASKTMsg svcTaskTMsg = getSvcZonCd(pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNumList.no(cntSvcTask).svcTaskNum.getValue());
        String svcZnCd = null;
        if (svcTaskTMsg != null) {
            svcZnCd = svcTaskTMsg.svcZnCd.getValue();
        }
        Map<String, Object> svcTrvlChrgMap = getSvcTrvlChrg(pMsg.glblCmpyCd.getValue(), svcMachMstrTMsg.svcMachMstrPk.getValue(), svcZnCd, svcMachMstrTMsg.svcByLineBizTpCd.getValue(), pMsg.svcTaskNumList.no(cntSvcTask).svcTaskNum
                .getValue());
        // mod end 2016/02/22 CSA Defect#4514
        if (svcTrvlChrgMap == null) {
            return;
        }
        // mod start 2016/04/14 CSA Defect#6927,#7020
        if (!hasValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlUnitAmt)) {
            BigDecimal svcTrvlUnitHrsAot = (BigDecimal) svcTrvlChrgMap.get("SVC_MIN_CHRG_HRS_AOT");
            if (!hasValue(svcTrvlUnitHrsAot)) {
                svcTrvlUnitHrsAot = BigDecimal.ZERO;
            }
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlUnitHrsAot, svcTrvlUnitHrsAot);
            // mod start 2016/10/13 CSA Defect#13987
            // mod start 2016/09/20 CSA Defect#13987
            if (hasValue(pMsg.svcTaskNumList.no(cntSvcTask).ovrdSvcChrgUnitAmt_TR)) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlUnitAmt, pMsg.svcTaskNumList.no(cntSvcTask).ovrdSvcChrgUnitAmt_TR);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlUnitAmt, (BigDecimal) svcTrvlChrgMap.get("SVC_TRVL_UNIT_AMT"));
            }
            // mod end 2016/09/20 CSA Defect#13987
        }
        // mod Start 2016/04/26 CSA Defect#7519
        ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).intgMdseCd_T, (String) svcTrvlChrgMap.get("INTG_MDSE_CD"));
        // mod Start 2016/04/26 CSA Defect#7519
        BigDecimal svcTrvlChrgAmt = BigDecimal.ZERO;
        BigDecimal svcTrvlTmNum = BigDecimal.ZERO;
        if (SVC_TRVL_CHRG_TP.FLAT_RATE.equals(svcTrvlChrgMap.get("SVC_TRVL_CHRG_TP_CD"))) {
            svcTrvlChrgAmt = pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlUnitAmt.getValue();
        } else if (SVC_TRVL_CHRG_TP.HOURLY_CHARGE.equals(svcTrvlChrgMap.get("SVC_TRVL_CHRG_TP_CD"))) {
            // mod start 2016/10/13 CSA Defect#13987
            // mod start 2016/09/20 CSA Defect#13987
            if (hasValue(pMsg.svcTaskNumList.no(cntSvcTask).svcChrgQty_TR)) {
                if (pMsg.svcTaskNumList.no(cntSvcTask).svcChrgQty_TR.getValue().compareTo(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlUnitHrsAot.getValue().multiply(DIVIDE_TIME_60MIN)) <= 0) {
                    svcTrvlTmNum = pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlUnitHrsAot.getValue().multiply(DIVIDE_TIME_60MIN);
                } else {
                    svcTrvlTmNum = pMsg.svcTaskNumList.no(cntSvcTask).svcChrgQty_TR.getValue();
                }
            } else {
                if (pMsg.svcTaskNumList.no(cntSvcTask).svcTrvlTmNum.getValue().compareTo(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlUnitHrsAot.getValue().multiply(DIVIDE_TIME_60MIN)) <= 0) {
                    svcTrvlTmNum = pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlUnitHrsAot.getValue().multiply(DIVIDE_TIME_60MIN);
                } else {
                    svcTrvlTmNum = pMsg.svcTaskNumList.no(cntSvcTask).svcTrvlTmNum.getValue();
                }
            }
            // mod end 2016/09/20 CSA Defect#13987
            // mod end 2016/10/13 CSA Defect#13987
            svcTrvlChrgAmt = svcTrvlTmNum.divide(DIVIDE_TIME_60MIN, 2, BigDecimal.ROUND_UP).multiply(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlUnitAmt.getValue()).setScale(2, BigDecimal.ROUND_UP);
        }
        // mod end 2016/04/14 CSA Defect#6927,#7020
        ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlDealAmt, svcTrvlChrgAmt);
        // START 2018/12/05 K.Kim [QC#28633, MOD]
        //     // mod start 2016/04/12 CSA Defect#6693
        // if (ZYPConstant.FLG_ON_Y.equals(svcLborChrgFlg) && ZYPConstant.FLG_ON_Y.equals(svcLborChrgMap.get("SVC_PRC_TRVL_CHRG_FLG"))) {
        if (ZYPConstant.FLG_ON_Y.equals(trvlChrgFlg)) {
        // END 2018/12/05 K.Kim [QC#28633, MOD]
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlDiscRate, BigDecimal.ZERO);
            // mod end 2016/04/12 CSA Defect#6693
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlDiscRate, RATE_100);
        }
        // add start 2015/12/16 CSA Defect#978
        String svcChrgCcyCd = (String) svcTrvlChrgMap.get("SVC_CHRG_CCY_CD");
        if (!pMsg.ccyCd.getValue().equals(svcChrgCcyCd)) {
            exchFuncUnitPrice(pMsg, svcChrgCcyCd, cntSvcTask, false);
            exchDealUnitPrice(pMsg, cntSvcTask, false);
        }
        // add end 2015/12/16 CSA Defect#978

        // START 2017/10/02 K.Kim [QC#20642,ADD]
        if (hasValue(pMsg.svcTaskNumList.no(cntSvcTask).ovrdSvcChrgUnitAmt_TR)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlDealAmt, pMsg.svcTaskNumList.no(cntSvcTask).ovrdSvcChrgUnitAmt_TR);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlDiscRate, BigDecimal.ZERO);
        }
        // END 2017/10/02 K.Kim [QC#20642,ADD]
    }

    /**
     * Set Parts Charge
     * 
     * @param msgMap S21ApiMessageMap
     * @param onBatTp ONBATCH_TYPE
     * @param pMsg NSZC061001PMsg
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param fsrTMsg FSRTMsg
     * @param cntSvcTask Count Service Task
     * @param dsContrDtlTMsg DS_CONTR_DTLTMsg
     */
    // START 2018/12/04 K.Kitachi [QC#28635, MOD]
//    private void setPrtChrg(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp, NSZC061001PMsg pMsg, String svcChrgFlg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, FSRTMsg fsrTMsg, int cntSvcTask) {
    private void setPrtChrg(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp, NSZC061001PMsg pMsg, String svcChrgFlg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, FSRTMsg fsrTMsg, int cntSvcTask, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
    // END 2018/12/04 K.Kitachi [QC#28635, MOD]
        // add start 2015/12/15 CSA Defect#978
        if (pMsg.xxPartsChrgList.getValidCount() == 0) {
            return;
        }
        // add end 2015/12/15 CSA Defect#978

        NWZC157001PMsg nwzc157001PMsg = buildNSZC157001PMsgForPrtChrg(pMsg, svcMachMstrTMsg, fsrTMsg, cntSvcTask);

        new NWZC157001().execute(nwzc157001PMsg, onBatTp);

        // mod start 2017/01/19 CSA Defect#17129
//        rcvMsgFromApi(msgMap, nwzc157001PMsg);
//
//        if (hasErrMsg(msgMap)) {
//            return;
//        }
        // mod end 2017/01/19 CSA Defect#17129

        BigDecimal svcPrtUnitAmt = BigDecimal.ZERO;
        BigDecimal svcPrtQty = BigDecimal.ZERO;
        BigDecimal svcPrtDealAmt = BigDecimal.ZERO;
        // Add Start 2019/02/22 QC#30413
        Map<String, Object> svcExclPrtMap = null;
        // Add End 2019/02/22 QC#30413
        // Mod Start 2019/01/10 K.Fujimoto QC#26861
        int priceCnt = 0;
        for (int i = 0; i <  pMsg.xxPartsChrgList.getValidCount(); i++) {
            if (pMsg.xxPartsChrgList.no(i).svcTaskNum.getValue().equals(pMsg.svcTaskNumList.no(cntSvcTask).svcTaskNum.getValue())) {
                if (nwzc157001PMsg.NWZC157004PMsg.getValidCount() > priceCnt) {
                    // if (nwzc157001PMsg.NWZC157004PMsg.getValidCount() > 0) {
                        svcPrtUnitAmt = nwzc157001PMsg.NWZC157004PMsg.no(priceCnt).xxUnitNetPrcAmt.getValue();
                        priceCnt++;
                        // svcPrtUnitAmt = nwzc157001PMsg.NWZC157004PMsg.no(0).xxUnitNetPrcAmt.getValue();
                        svcPrtQty = pMsg.xxPartsChrgList.no(i).svcPrtQty.getValue();
                        ZYPEZDItemValueSetter.setValue(pMsg.xxPartsChrgList.no(i).svcPrtUnitAmt, svcPrtUnitAmt);
                        ZYPEZDItemValueSetter.setValue(pMsg.xxPartsChrgList.no(i).svcPrtDealAmt, svcPrtUnitAmt.multiply(svcPrtQty));
                    }
                    // Add Start 2019/02/22 QC#30413
                    svcExclPrtMap = getSvcExclPrt(pMsg.glblCmpyCd.getValue(), pMsg.xxPartsChrgList.no(i).mdseCd.getValue(), pMsg.svcMachMstrPk.getValue(), fsrTMsg.sellToCustCd.getValue(), dsContrDtlTMsg, pMsg.svcTaskNumList.no(cntSvcTask).fsrVisitArvDt.getValue());
                    // Add End 2019/02/22 QC#30413

                    if (ZYPConstant.FLG_ON_Y.equals(svcChrgFlg)) {
                        ZYPEZDItemValueSetter.setValue(pMsg.xxPartsChrgList.no(i).svcPrtDiscRate, BigDecimal.ZERO);
                    // START 2018/12/04 K.Kitachi [QC#28635, ADD]
                    // Mod Start 2019/02/22 QC#30413
//                    } else if (isExistSvcExclPrt(pMsg.glblCmpyCd.getValue(), pMsg.xxPartsChrgList.no(i).mdseCd.getValue(), pMsg.svcMachMstrPk.getValue(), fsrTMsg.sellToCustCd.getValue(), dsContrDtlTMsg)) {
//                        ZYPEZDItemValueSetter.setValue(pMsg.xxPartsChrgList.no(i).svcPrtDiscRate, BigDecimal.ZERO);
                    } else if (svcExclPrtMap != null) {
                        setDiscExclPartsPrice(pMsg.xxPartsChrgList.no(i), pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNumList.no(cntSvcTask).svcTaskNum.getValue(), svcExclPrtMap, pMsg.ccyCd.getValue());
                    // Mod End 2019/02/22 QC#30413
                    // END 2018/12/04 K.Kitachi [QC#28635, ADD]
                    } else {
                        ZYPEZDItemValueSetter.setValue(pMsg.xxPartsChrgList.no(i).svcPrtDiscRate, RATE_100);
                    }
                    // mod start 2015/12/16 CSA Defect#978
                    // Mod Start 2017/07/26 QC#17129
//                    if (nwzc157001PMsg.xxPrcList.getValidCount() > 0) {
//                        ZYPEZDItemValueSetter.setValue(pMsg.xxPartsChrgList.no(i).prcCatgCd_L1, nwzc157001PMsg.xxPrcList.no(i).prcCatgCd);
//                    }
                    ZYPEZDItemValueSetter.setValue(pMsg.xxPartsChrgList.no(i).prcCatgCd_L1, this.partsPrcCatgCd);
                    // Mod End 2017/07/26 QC#17129
                    if (hasValue(pMsg.xxPartsChrgList.no(cntSvcTask).svcPrtDealAmt)) {
                        svcPrtDealAmt = svcPrtDealAmt.add(pMsg.xxPartsChrgList.no(cntSvcTask).svcPrtDealAmt.getValue());
                    }
                    // mod end 2015/12/16 CSA Defect#978
            }
        }
        // Mod End   2019/01/10 K.Fujimoto QC#26861
        ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcPrtDealAmt, svcPrtDealAmt);
    }

    /**
     * Get Pricing API parameter for "Parts Charge"
     * 
     * @param pMsg NSZC061001PMsg
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param fsrTMsg FSRTMsg
     * @param cntSvcTask Count Service Task
     * @return NWZC157001PMsg
     */
    private NWZC157001PMsg buildNSZC157001PMsgForPrtChrg(NSZC061001PMsg pMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, FSRTMsg fsrTMsg, int cntSvcTask) {
        NWZC157001PMsg apiPMsg = new NWZC157001PMsg();
        DecimalFormat dformat = new DecimalFormat(DECIMAL_FORMAT);

        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        // Mod Start 2017/07/26 QC#17129
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NWZC157001.GET_BASE_PRICE);
        // Mod End 2017/07/26 QC#17129
        ZYPEZDItemValueSetter.setValue(apiPMsg.prcBaseDt, pMsg.svcTaskNumList.no(cntSvcTask).fsrVisitArvDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.prcCtxTpCd, PRC_CTX_TP.SERVICE_PRICING);
        ZYPEZDItemValueSetter.setValue(apiPMsg.lineBizTpCd, svcMachMstrTMsg.svcByLineBizTpCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsAcctNum, fsrTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
        // add start 2015/12/15 CSA Defect#978
        ZYPEZDItemValueSetter.setValue(apiPMsg.ccyCd, pMsg.ccyCd);
        // add end 2015/12/15 CSA Defect#978
        int cnt = 0;
        for (int i = 0; i <  pMsg.xxPartsChrgList.getValidCount(); i++) {
            if (pMsg.xxPartsChrgList.no(i).svcTaskNum.getValue().equals(pMsg.svcTaskNumList.no(cntSvcTask).svcTaskNum.getValue())) {
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).trxLineNum, dformat.format(i));
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).trxLineSubNum, dformat.format(BigDecimal.ONE));
                if (ZYPConstant.FLG_OFF_N.equals(fsrTMsg.billToCustUpdFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).billToCustCd, fsrTMsg.billToCustCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).billToCustCd, fsrTMsg.billToUpdCustCd);
                }
                if (ZYPConstant.FLG_OFF_N.equals(fsrTMsg.shipToCustUpdFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).shipToCustCd, fsrTMsg.shipToCustCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).shipToCustCd, fsrTMsg.shipToUpdCustCd);
                }
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).dsAcctNum_BL, fsrTMsg.billToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).dsAcctNum_SH, fsrTMsg.shipToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).mdseCd, pMsg.xxPartsChrgList.no(i).mdseCd);
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).pkgUomCd, pMsg.xxPartsChrgList.no(i).pkgUomCd);
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).ordQty, pMsg.xxPartsChrgList.no(i).svcPrtQty);
                // Add Start 2017/07/26 QC#17129
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).prcCatgCd, this.partsPrcCatgCd);
                // Add End 2017/07/26 QC#17129

                BigDecimal uomQty = BigDecimal.ZERO;
                MDSE_STORE_PKGTMsg mdseStorePkgTMsg = getMdseStorePkgFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.xxPartsChrgList.no(i).mdseCd.getValue(), pMsg.xxPartsChrgList.no(i).pkgUomCd.getValue());
                if (mdseStorePkgTMsg != null) {
                    uomQty = pMsg.xxPartsChrgList.no(i).svcPrtQty.getValue().multiply(mdseStorePkgTMsg.inEachQty.getValue());
                }
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).ordCustUomQty, uomQty);

                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).invQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).xxPrcCloFlg, ZYPConstant.FLG_OFF_N);

                SHIP_TO_CUSTTMsgArray shipToCust = getShipToCust(pMsg.glblCmpyCd.getValue(), apiPMsg.NWZC157002PMsg.no(cnt).shipToCustCd.getValue());
                if (shipToCust.getValidCount() > 0) {
                    ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).ctryCd_SH, shipToCust.no(0).ctryCd);
                    // mod start 2016/03/04 CSA Defect#5086
                    ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).ctyAddr_SH, shipToCust.no(0).ctyAddr);
                    ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).stCd_SH, shipToCust.no(0).stCd);
                    // mod end 2016/03/04 CSA Defect#5086
                }
                cnt++;
            }
        }
        apiPMsg.NWZC157002PMsg.setValidCount(cnt);

        return apiPMsg;
    }

    /**
     * Get Merchandise Storage Package
     * 
     * @param glblCmpyCd Global Company Code
     * @param mdseCd Merchandise Code
     * @param pkgUomCd Package Unit of Measure Code
     * @return MDSE_STORE_PKGTMsg
     */
    private MDSE_STORE_PKGTMsg getMdseStorePkgFindByKey(String glblCmpyCd, String mdseCd, String pkgUomCd) {
        MDSE_STORE_PKGTMsg paramTMsg = new MDSE_STORE_PKGTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.mdseCd, mdseCd);
        setValue(paramTMsg.pkgUomCd, pkgUomCd);
        return (MDSE_STORE_PKGTMsg) S21ApiTBLAccessor.findByKey(paramTMsg);
    }

    /**
     * Set Expense Charge
     * 
     * @param msgMap S21ApiMessageMap
     * @param onBatTp ONBATCH_TYPE
     * @param pMsg NSZC061001PMsg
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param fsrTMsg FSRTMsg
     * @param cntSvcTask Count Service Task
     * @param dsContrDtlTMsg DS_CONTR_DTLTMsg
     */
    // START 2018/12/04 K.Kitachi [QC#28635, MOD]
//    private void setExpChrg(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp, NSZC061001PMsg pMsg, String svcChrgFlg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, FSRTMsg fsrTMsg, int cntSvcTask) {
    private void setExpChrg(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp, NSZC061001PMsg pMsg, String svcChrgFlg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, FSRTMsg fsrTMsg, int cntSvcTask, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
    // END 2018/12/04 K.Kitachi [QC#28635, MOD]
        // add start 2015/12/15 CSA Defect#978
        if (pMsg.xxExpMdseList.getValidCount() == 0) {
            return;
        }
        // add end 2015/12/15 CSA Defect#978

// mod start 2016/03/02 CSA Defect#4644
//        NWZC157001PMsg nwzc157001PMsg = buildNSZC157001PMsgForExpChrg(pMsg, svcMachMstrTMsg, fsrTMsg, cntSvcTask);
//
//        new NWZC157001().execute(nwzc157001PMsg, onBatTp);
//
//        rcvMsgFromApi(msgMap, nwzc157001PMsg);
//
//        if (hasErrMsg(msgMap)) {
//            return;
//        }
//
//        BigDecimal svcExpUnitAmt = BigDecimal.ZERO;
//        BigDecimal svcExpQty = BigDecimal.ZERO;
//        BigDecimal svcExpDealAmt = BigDecimal.ZERO;
        // Add Start 2019/02/22 QC#30413
        Map<String, Object> svcExclPrtMap = null;
        // Add End 2019/02/22 QC#30413
        for (int i = 0; i <  pMsg.xxExpMdseList.getValidCount(); i++) {
//            if (nwzc157001PMsg.NWZC157004PMsg.getValidCount() > 0) {
//                svcExpUnitAmt = nwzc157001PMsg.NWZC157004PMsg.no(0).xxUnitNetPrcAmt.getValue();
//                svcExpQty = pMsg.xxExpMdseList.no(i).expQty.getValue();
//                ZYPEZDItemValueSetter.setValue(pMsg.xxExpMdseList.no(i).xxSvcExpUnitAmt, svcExpUnitAmt);
//                ZYPEZDItemValueSetter.setValue(pMsg.xxExpMdseList.no(i).xxSvcExpDealAmt, svcExpUnitAmt.multiply(svcExpQty));
//            }
            // Add Start 2019/02/22 QC#30413
            svcExclPrtMap = getSvcExclPrt(pMsg.glblCmpyCd.getValue(), pMsg.xxExpMdseList.no(i).mdseCd.getValue(), pMsg.svcMachMstrPk.getValue(), fsrTMsg.sellToCustCd.getValue(), dsContrDtlTMsg, pMsg.svcTaskNumList.no(cntSvcTask).fsrVisitArvDt.getValue());
            // Add End 2019/02/22 QC#30413
            if (ZYPConstant.FLG_ON_Y.equals(svcChrgFlg)) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxExpMdseList.no(i).xxSvcExpDiscRate, BigDecimal.ZERO);
            // START 2018/12/04 K.Kitachi [QC#28635, ADD]
            // Mod Start 2019/02/22 QC#30413
//            } else if (isExistSvcExclPrt(pMsg.glblCmpyCd.getValue(), pMsg.xxExpMdseList.no(i).mdseCd.getValue(), pMsg.svcMachMstrPk.getValue(), fsrTMsg.sellToCustCd.getValue(), dsContrDtlTMsg)) {
//                ZYPEZDItemValueSetter.setValue(pMsg.xxExpMdseList.no(i).xxSvcExpDiscRate, BigDecimal.ZERO);
            } else if (svcExclPrtMap != null) {
                setDiscExclPartsPrice(pMsg.xxExpMdseList.no(i), pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNumList.no(cntSvcTask).svcTaskNum.getValue(), svcExclPrtMap, pMsg.ccyCd.getValue());
            // Mod End 2019/02/22 QC#30413
            // END 2018/12/04 K.Kitachi [QC#28635, ADD]
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.xxExpMdseList.no(i).xxSvcExpDiscRate, RATE_100);
            }
            // mod start 2015/12/16 CSA Defect#978
//            if (nwzc157001PMsg.xxPrcList.getValidCount() > 0) {
//                ZYPEZDItemValueSetter.setValue(pMsg.xxExpMdseList.no(i).prcCatgCd_L2, nwzc157001PMsg.xxPrcList.no(i).prcCatgCd);
//            }
//            if (hasValue(pMsg.xxExpMdseList.no(cntSvcTask).xxSvcExpDealAmt)) {
//                svcExpDealAmt = svcExpDealAmt.add(pMsg.xxExpMdseList.no(cntSvcTask).xxSvcExpDealAmt.getValue());
//            }
            // mod end 2015/12/16 CSA Defect#978
        }
//        ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).xxSvcExpDealAmt, svcExpDealAmt);
// mod 2016/03/02 CSA Defect#4644
    }

    /**
     * Get Pricing API parameter for "Expense Charge"
     * 
     * @param pMsg NSZC061001PMsg
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param fsrTMsg FSRTMsg
     * @param cntSvcTask Count Service Task
     * @return NWZC157001PMsg
     */
    private NWZC157001PMsg buildNSZC157001PMsgForExpChrg(NSZC061001PMsg pMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, FSRTMsg fsrTMsg, int cntSvcTask) {

        NWZC157001PMsg apiPMsg = new NWZC157001PMsg();
        DecimalFormat dformat = new DecimalFormat(DECIMAL_FORMAT);

        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        // Mod Start 2017/07/26 QC#17129
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NWZC157001.GET_BASE_PRICE);
        // Mod End 2017/07/26 QC#17129
        ZYPEZDItemValueSetter.setValue(apiPMsg.prcBaseDt, pMsg.svcTaskNumList.no(cntSvcTask).fsrVisitArvDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.prcCtxTpCd, PRC_CTX_TP.SERVICE_PRICING);
        ZYPEZDItemValueSetter.setValue(apiPMsg.lineBizTpCd, svcMachMstrTMsg.svcByLineBizTpCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsAcctNum, fsrTMsg.sellToCustCd);
        // add start 2015/12/15 CSA Defect#978
        ZYPEZDItemValueSetter.setValue(apiPMsg.ccyCd, pMsg.ccyCd);
        // add end 2015/12/15 CSA Defect#978
        ZYPEZDItemValueSetter.setValue(apiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
        for (int i = 0; i <  pMsg.xxExpMdseList.getValidCount(); i++) {
            if (pMsg.xxExpMdseList.no(i).svcTaskNum.getValue().equals(pMsg.svcTaskNumList.no(cntSvcTask).svcTaskNum.getValue())) {
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).trxLineNum, dformat.format(i));
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).trxLineSubNum, dformat.format(BigDecimal.ONE));
                if (ZYPConstant.FLG_OFF_N.equals(fsrTMsg.billToCustUpdFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).billToCustCd, fsrTMsg.billToCustCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).billToCustCd, fsrTMsg.billToUpdCustCd);
                }
                if (ZYPConstant.FLG_OFF_N.equals(fsrTMsg.shipToCustUpdFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).shipToCustCd, fsrTMsg.shipToCustCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).shipToCustCd, fsrTMsg.shipToUpdCustCd);
                }
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).dsAcctNum_BL, fsrTMsg.billToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).dsAcctNum_SH, fsrTMsg.shipToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).mdseCd, pMsg.xxExpMdseList.no(i).mdseCd);
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).pkgUomCd, pMsg.xxExpMdseList.no(i).pkgUomCd);
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).ordQty, pMsg.xxExpMdseList.no(i).expQty);
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).invQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
            }
        }

        return apiPMsg;
    }

    /**
     * Set Drum Charge
     * 
     * @param msgMap S21ApiMessageMap
     * @param onBatTp ONBATCH_TYPE
     * @param pMsg NSZC061001PMsg
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param fsrTMsg FSRTMsg
     * @param cntSvcTask Count Service Task
     * @param dsContrDtlTMsg DS_CONTR_DTLTMsg
     */
    // START 2018/12/04 K.Kitachi [QC#28635, MOD]
//    private void setDrumChrg(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp, NSZC061001PMsg pMsg, String svcChrgFlg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, FSRTMsg fsrTMsg, int cntSvcTask) {
    private void setDrumChrg(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp, NSZC061001PMsg pMsg, String svcChrgFlg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, FSRTMsg fsrTMsg, int cntSvcTask, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
    // END 2018/12/04 K.Kitachi [QC#28635, MOD]
        // add start 2015/12/15 CSA Defect#978
        if (pMsg.xxDrumChrgList.getValidCount() == 0) {
            return;
        }
        // add end 2015/12/15 CSA Defect#978
        NWZC157001PMsg nwzc157001PMsg = buildNSZC157001PMsgForDrumChrg(pMsg, svcMachMstrTMsg, fsrTMsg, cntSvcTask);
        new NWZC157001().execute(nwzc157001PMsg, onBatTp);

        // mod start 2017/01/19 CSA Defect#17129
//        rcvMsgFromApi(msgMap, nwzc157001PMsg);
//
//        if (hasErrMsg(msgMap)) {
//            return;
//        }
        // mod end 2017/01/19 CSA Defect#17129

        BigDecimal svcDrumUnitAmt = BigDecimal.ZERO;
        BigDecimal svcDrumQty = BigDecimal.ZERO;
        BigDecimal svcDrumDealAmt = BigDecimal.ZERO;
        // Add Start 2019/02/22 QC#30413
        Map<String, Object> svcExclPrtMap = null;
        int priceCnt = 0;
        // Add End 2019/02/22 QC#30413
        for (int i = 0; i <  pMsg.xxDrumChrgList.getValidCount(); i++) {
            // Mod Start 2019/02/22 QC#30413
            if (nwzc157001PMsg.NWZC157004PMsg.getValidCount() > priceCnt) {
                svcDrumUnitAmt = nwzc157001PMsg.NWZC157004PMsg.no(priceCnt).xxUnitNetPrcAmt.getValue();
                svcDrumQty = pMsg.xxDrumChrgList.no(i).svcPrtQty.getValue();
                priceCnt++;
                ZYPEZDItemValueSetter.setValue(pMsg.xxDrumChrgList.no(i).xxSvcDrumUnitAmt, svcDrumUnitAmt);
                ZYPEZDItemValueSetter.setValue(pMsg.xxDrumChrgList.no(i).xxSvcDrumDealAmt, svcDrumUnitAmt.multiply(svcDrumQty));
            }
            // Mod End 2019/02/22 QC#30413
            // Add Start 2019/02/22 QC#30413
            svcExclPrtMap = getSvcExclPrt(pMsg.glblCmpyCd.getValue(), pMsg.xxDrumChrgList.no(i).mdseCd.getValue(), pMsg.svcMachMstrPk.getValue(), fsrTMsg.sellToCustCd.getValue(), dsContrDtlTMsg, pMsg.svcTaskNumList.no(cntSvcTask).fsrVisitArvDt.getValue());
            // Add End 2019/02/22 QC#30413
            if (ZYPConstant.FLG_ON_Y.equals(svcChrgFlg)) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxDrumChrgList.no(i).xxSvcDrumDiscRate, BigDecimal.ZERO);
            // START 2018/12/04 K.Kitachi [QC#28635, ADD]
            // Mod Start 2019/02/22 QC#30413
//            } else if (isExistSvcExclPrt(pMsg.glblCmpyCd.getValue(), pMsg.xxDrumChrgList.no(i).mdseCd.getValue(), pMsg.svcMachMstrPk.getValue(), fsrTMsg.sellToCustCd.getValue(), dsContrDtlTMsg)) {
//                ZYPEZDItemValueSetter.setValue(pMsg.xxDrumChrgList.no(i).xxSvcDrumDiscRate, BigDecimal.ZERO);
            } else if (svcExclPrtMap != null) {
                setDiscExclPartsPrice(pMsg.xxDrumChrgList.no(i), pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNumList.no(cntSvcTask).svcTaskNum.getValue(), svcExclPrtMap, pMsg.ccyCd.getValue());
            // Mod End 2019/02/22 QC#30413
            // END 2018/12/04 K.Kitachi [QC#28635, ADD]
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.xxDrumChrgList.no(i).xxSvcDrumDiscRate, RATE_100);
            }
            // mod start 2015/12/16 CSA Defect#978
            // Mod Start 2017/07/26 QC#17129
//            if (nwzc157001PMsg.xxPrcList.getValidCount() > 0) {
//                ZYPEZDItemValueSetter.setValue(pMsg.xxDrumChrgList.no(i).prcCatgCd_L3, nwzc157001PMsg.xxPrcList.no(i).prcCatgCd);
//            }
            ZYPEZDItemValueSetter.setValue(pMsg.xxDrumChrgList.no(i).prcCatgCd_L3, this.drumPrcCatgCd);
            // Mod End 2017/07/26 QC#17129
            if (hasValue(pMsg.xxDrumChrgList.no(cntSvcTask).xxSvcDrumDealAmt)) {
                svcDrumDealAmt = svcDrumDealAmt.add(pMsg.xxDrumChrgList.no(cntSvcTask).xxSvcDrumDealAmt.getValue());
            }
            // mod end 2015/12/16 CSA Defect#978
        }
        // Mod Start 2017/07/26 QC#17129
        ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).xxSvcDrumDealAmt, svcDrumDealAmt);
        // Mod End 2017/07/26 QC#17129
    }

    /**
     * Get Pricing API parameter for "Drum Charge"
     * 
     * @param pMsg NSZC061001PMsg
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param fsrTMsg FSRTMsg
     * @param cntSvcTask Count Service Task
     * @return NWZC157001PMsg
     */
    private NWZC157001PMsg buildNSZC157001PMsgForDrumChrg(NSZC061001PMsg pMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, FSRTMsg fsrTMsg, int cntSvcTask) {

        NWZC157001PMsg apiPMsg = new NWZC157001PMsg();
        DecimalFormat dformat = new DecimalFormat(DECIMAL_FORMAT);

        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        // Mod Start 2017/07/26 QC#17129
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NWZC157001.GET_BASE_PRICE);
        // Mod End 2017/07/26 QC#17129
        ZYPEZDItemValueSetter.setValue(apiPMsg.prcBaseDt, pMsg.svcTaskNumList.no(cntSvcTask).fsrVisitArvDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.prcCtxTpCd, PRC_CTX_TP.SERVICE_PRICING);
        ZYPEZDItemValueSetter.setValue(apiPMsg.lineBizTpCd, svcMachMstrTMsg.svcByLineBizTpCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsAcctNum, fsrTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
        // add start 2015/12/15 CSA Defect#978
        ZYPEZDItemValueSetter.setValue(apiPMsg.ccyCd, pMsg.ccyCd);
        // add end 2015/12/15 CSA Defect#978
        // mod start 2017/01/19 CSA Defect#17129
        int cnt = 0;
        for (int i = 0; i <  pMsg.xxDrumChrgList.getValidCount(); i++) {
            if (pMsg.xxDrumChrgList.no(i).svcTaskNum.getValue().equals(pMsg.svcTaskNumList.no(cntSvcTask).svcTaskNum.getValue())) {
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).trxLineNum, dformat.format(i));
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).trxLineSubNum, dformat.format(BigDecimal.ONE));
                if (ZYPConstant.FLG_OFF_N.equals(fsrTMsg.billToCustUpdFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).billToCustCd, fsrTMsg.billToCustCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).billToCustCd, fsrTMsg.billToUpdCustCd);
                }
                if (ZYPConstant.FLG_OFF_N.equals(fsrTMsg.shipToCustUpdFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).shipToCustCd, fsrTMsg.shipToCustCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).shipToCustCd, fsrTMsg.shipToUpdCustCd);
                }
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).dsAcctNum_BL, fsrTMsg.billToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).dsAcctNum_SH, fsrTMsg.shipToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).mdseCd, pMsg.xxDrumChrgList.no(i).mdseCd);
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).pkgUomCd, pMsg.xxDrumChrgList.no(i).pkgUomCd);
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).ordQty, pMsg.xxDrumChrgList.no(i).svcPrtQty);
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).invQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(i).xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
                // Add Start 2017/07/26 QC#17129
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).prcCatgCd, this.partsPrcCatgCd);
                // Add End 2017/07/26 QC#17129

                BigDecimal uomQty = BigDecimal.ZERO;
                MDSE_STORE_PKGTMsg mdseStorePkgTMsg = getMdseStorePkgFindByKey(pMsg.glblCmpyCd.getValue(), pMsg.xxDrumChrgList.no(i).mdseCd.getValue(), pMsg.xxDrumChrgList.no(i).pkgUomCd.getValue());
                if (mdseStorePkgTMsg != null) {
                    uomQty = pMsg.xxDrumChrgList.no(i).svcPrtQty.getValue().multiply(mdseStorePkgTMsg.inEachQty.getValue());
                }
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).ordCustUomQty, uomQty);

                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).invQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).xxPrcCloFlg, ZYPConstant.FLG_OFF_N);

                SHIP_TO_CUSTTMsgArray shipToCust = getShipToCust(pMsg.glblCmpyCd.getValue(), apiPMsg.NWZC157002PMsg.no(cnt).shipToCustCd.getValue());
                if (shipToCust.getValidCount() > 0) {
                    ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).ctryCd_SH, shipToCust.no(0).ctryCd);
                    ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).ctyAddr_SH, shipToCust.no(0).ctyAddr);
                    ZYPEZDItemValueSetter.setValue(apiPMsg.NWZC157002PMsg.no(cnt).stCd_SH, shipToCust.no(0).stCd);
                }
                cnt++;
            }
        }
        apiPMsg.NWZC157002PMsg.setValidCount(cnt);
        // mod end 2017/01/19 CSA Defect#17129

        return apiPMsg;
    }

    /**
     * Determine if the S21ApiMessageMap contains an error message.
     * 
     * @param msgMap S21ApiMessageMap
     * @return true if S21ApiMessageMap contains an error message.
     */
    private boolean hasErrMsg(S21ApiMessageMap msgMap) {
        NSZC061001ApiMsgIdMgr msgMgr = (NSZC061001ApiMsgIdMgr) msgMap.getMsgMgr();
        return msgMgr.isXxErrMsgId();
    }

    /**
     * Receive messages from API.
     * 
     * @param msgMap S21ApiMessageMap
     * @param apiPMsg API parameter
     */
    private void rcvMsgFromApi(S21ApiMessageMap msgMap, EZDPMsg apiPMsg) {
        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
        for (S21ApiMessage msg : msgList) {
            String xxMsgId = msg.getXxMsgid();
            String[] xxMsgPrm = msg.getXxMsgPrmArray();
            msgMap.addXxMsgIdWithPrm(xxMsgId, xxMsgPrm);
        }
    }

    // add start 2015/12/15 CSA Defect#978
    // START 2017/01/04 K.Kojima [QC#16513,DEL]
    // /**
    //  * Set DsContract parameter
    //  * @param msgMap S21ApiMessageMap
    //  */
    // private void setDsContractPk(S21ApiMessageMap msgMap) {
    // 
    //     NSZC061001PMsg pMsg = (NSZC061001PMsg) msgMap.getPmsg();
    // 
    //     DS_CONTRTMsg dsContrTMsg = NSXC001001GetContr.getContrByMachMstrPkInclWarranty(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.startDt.getValue());
    //     if (dsContrTMsg == null) {
    //         return;
    //     }
    // 
    //     ZYPEZDItemValueSetter.setValue(pMsg.dsContrPk, dsContrTMsg.dsContrPk);
    // }
    // END 2017/01/04 K.Kojima [QC#16513,DEL]

    /**
     * Set stdCcyCd parameter.
     * @param msgMap S21ApiMessageMap
     */
    private void setCcyCd(S21ApiMessageMap msgMap) {

        NSZC061001PMsg pMsg = (NSZC061001PMsg) msgMap.getPmsg();

        if (!hasValue(pMsg.ccyCd)) {
            GLBL_CMPYTMsg inMsg = new GLBL_CMPYTMsg();
            setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            GLBL_CMPYTMsg outMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(inMsg);
            if (outMsg == null) {
                return;
            }
            ZYPEZDItemValueSetter.setValue(pMsg.ccyCd, outMsg.stdCcyCd);
        }
    }

    private void exchFuncUnitPrice(NSZC061001PMsg pMsg, String svcChrgCcyCd, int cntSvcTask, boolean svcLborUnitFlg) {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String startDt = pMsg.startDt.getValue();
        Map<String, Object> getExchRateData = (Map<String, Object>) getExchRateData(glblCmpyCd, svcChrgCcyCd, startDt);
        if (getExchRateData == null) {
            return;
        }

        Integer scale = getStdCcyDigit(pMsg.glblCmpyCd.getValue());
        String acctArthTpCd = (String) getExchRateData.get("ACCT_ARTH_TP_CD");
        BigDecimal actlExchRate = (BigDecimal) getExchRateData.get("ACTL_EXCH_RATE");

        if (svcLborUnitFlg) {
            BigDecimal lborAmt = pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitAmt.getValue();
            BigDecimal svcLborFunUnitAmt = calcAmt(lborAmt, acctArthTpCd, actlExchRate, scale);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitAmt, svcLborFunUnitAmt);
        } else {
            BigDecimal trvlAmt = pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlUnitAmt.getValue();
            BigDecimal svcTrvlFunUnitAmt = calcAmt(trvlAmt, acctArthTpCd, actlExchRate, scale);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlUnitAmt, svcTrvlFunUnitAmt);
        }
    }

    private void exchFuncUnitPrice(NSZC061001PMsg pMsg, String svcChrgCcyCd, boolean svcLborUnitFlg) {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String startDt = pMsg.startDt.getValue();
        Map<String, Object> getExchRateData = (Map<String, Object>) getExchRateData(glblCmpyCd, svcChrgCcyCd, startDt);
        if (getExchRateData == null) {
            return;
        }

        Integer scale = getStdCcyDigit(pMsg.glblCmpyCd.getValue());
        String acctArthTpCd = (String) getExchRateData.get("ACCT_ARTH_TP_CD");
        BigDecimal actlExchRate = (BigDecimal) getExchRateData.get("ACTL_EXCH_RATE");

        if (svcLborUnitFlg) {
            BigDecimal svcLborFunUnitAmt = calcAmt(pMsg.svcLborUnitAmt.getValue(), acctArthTpCd, actlExchRate, scale);
            ZYPEZDItemValueSetter.setValue(pMsg.svcLborUnitAmt, svcLborFunUnitAmt);
        } else {
            BigDecimal svcTrvlFunUnitAmt = calcAmt(pMsg.svcTrvlUnitAmt.getValue(), acctArthTpCd, actlExchRate, scale);
            ZYPEZDItemValueSetter.setValue(pMsg.svcTrvlUnitAmt, svcTrvlFunUnitAmt);
        }
    }

    private void exchDealUnitPrice(NSZC061001PMsg pMsg, int cntSvcTask, boolean svcLborUnitFlg) {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String ccyCd = pMsg.ccyCd.getValue();
        String startDt = pMsg.startDt.getValue();
        Integer scale = getDealCcyDigit(glblCmpyCd, ccyCd);

        Map<String, Object> getExchRateData = (Map<String, Object>) getExchRateData(glblCmpyCd, ccyCd, startDt);
        if (getExchRateData == null) {
            return;
        }

        String acctArthTpCd = (String) getExchRateData.get("ACCT_ARTH_TP_CD");
        BigDecimal actlExchRate = (BigDecimal) getExchRateData.get("ACTL_EXCH_RATE");

        if (MULTIPLICATION.equals(acctArthTpCd)) {
            acctArthTpCd = DIVISION;
        } else {
            acctArthTpCd = MULTIPLICATION;
        }

        if (svcLborUnitFlg) {
            BigDecimal lborAmt = pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitAmt.getValue();
            BigDecimal svcLborInvDealUnitAmt = calcAmt(lborAmt, acctArthTpCd, actlExchRate, scale);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcLborUnitAmt, svcLborInvDealUnitAmt);
        } else {
            BigDecimal trvlAmt = pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlUnitAmt.getValue();
            BigDecimal svcTrvlInvDealUnitAmt = calcAmt(trvlAmt, acctArthTpCd, actlExchRate, scale);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskChrgList.no(cntSvcTask).svcTrvlUnitAmt, svcTrvlInvDealUnitAmt);
        }
    }

    private void exchDealUnitPrice(NSZC061001PMsg pMsg, boolean svcLborUnitFlg) {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String ccyCd = pMsg.ccyCd.getValue();
        String startDt = pMsg.startDt.getValue();
        Integer scale = getDealCcyDigit(glblCmpyCd, ccyCd);

        Map<String, Object> getExchRateData = (Map<String, Object>) getExchRateData(glblCmpyCd, ccyCd, startDt);
        if (getExchRateData == null) {
            return;
        }

        String acctArthTpCd = (String) getExchRateData.get("ACCT_ARTH_TP_CD");
        BigDecimal actlExchRate = (BigDecimal) getExchRateData.get("ACTL_EXCH_RATE");

        if (MULTIPLICATION.equals(acctArthTpCd)) {
            acctArthTpCd = DIVISION;
        } else {
            acctArthTpCd = MULTIPLICATION;
        }

        if (svcLborUnitFlg) {
            BigDecimal svcLborInvDealUnitAmt = calcAmt(pMsg.svcLborUnitAmt.getValue(), acctArthTpCd, actlExchRate, scale);
            ZYPEZDItemValueSetter.setValue(pMsg.svcLborUnitAmt, svcLborInvDealUnitAmt);
        } else {
            BigDecimal svcTrvlInvDealUnitAmt = calcAmt(pMsg.svcTrvlUnitAmt.getValue(), acctArthTpCd, actlExchRate, scale);
            ZYPEZDItemValueSetter.setValue(pMsg.svcTrvlUnitAmt, svcTrvlInvDealUnitAmt);
        }
    }

    private static Integer getDealCcyDigit(String glblCmpyCd, String dealCcyCd) {

        CCYTMsg ccyTMsg = new CCYTMsg();
        setValue(ccyTMsg.glblCmpyCd, glblCmpyCd);
        setValue(ccyTMsg.ccyCd, dealCcyCd);

        ccyTMsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(ccyTMsg);
        if (ccyTMsg == null) {
            return null;
        } else {
            // AFT_DECL_PNT_DIGIT_NUM
            final BigDecimal aftDeclPntDigitNum = ccyTMsg.aftDeclPntDigitNum.getValue();
            if (aftDeclPntDigitNum == null) {
                return null;
            } else {
                return aftDeclPntDigitNum.intValue();
            }
        }
    }

    private static BigDecimal calcAmt(BigDecimal amt, String acctArthTpCd, BigDecimal actlExchRate, int scale) {

        BigDecimal retAmt = null;

        if (MULTIPLICATION.equals(acctArthTpCd)) {
            if (amt != null) {
                retAmt = amt.multiply(actlExchRate).setScale(scale, HALF_UP);
            }
        } else {
            if (amt != null) {
                retAmt = amt.divide(actlExchRate, scale, HALF_UP);
            }
        }
        return retAmt;
    }

    private Integer getStdCcyDigit(String glblCmpyCd) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return (Integer) ssmBatchClient.queryObject("getStdCcyDigit", ssmParam);
    }

    /**
     * The exchange rate is acquired.
     * @param glblCmpyCd Global Company Code.
     * @param ccyCd Currency Code.
     * @param trxDt Sales Date.
     * @return Exchange rate and computational method
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getExchRateData(String glblCmpyCd, String ccyCd, String trxDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("ccyCd", ccyCd);
        ssmPrm.put("trxDt", trxDt);
        return (Map<String, Object>) ssmBatchClient.queryObject("getExchRateData", ssmPrm);
    }
    // add end 2015/12/15 CSA Defect#978

    // add start 2016/02/22 CSA Defect#4514
    private SVC_TASKTMsg getSvcZonCd(String glblCmpyCd, String svcTaskNum) {
        SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, svcTaskNum);
        return (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }
    // add end 2016/02/22 CSA Defect#4514

    // START 2017/01/04 K.Kojima [QC#16513,ADD]
    private String getSvcBillTpCd(String glblCmpyCd, List<String> svcBillTpCDList) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcBillTpCDList", svcBillTpCDList);
        return (String) ssmBatchClient.queryObject("getSvcBillTpCd", ssmPrm);
    }

    private BigDecimal getDsContrPk(String glblCmpyCd, List<BigDecimal> dsContrDtkPkList) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtkPkList", dsContrDtkPkList);
        ssmPrm.put("svcCovFeatCd", SVC_COV_FEAT.BILL_CD);
        ssmPrm.put("flgN", ZYPConstant.FLG_OFF_N);
        ssmPrm.put("dsContrCatgCd", DS_CONTR_CATG.WARRANTY);
        // START 2017/06/05 K.Kojima [QC#18295,ADD]
        ssmPrm.put("dsContrDtlTpCdFleet", DS_CONTR_DTL_TP.FLEET);
        // END 2017/06/05 K.Kojima [QC#18295,ADD]
        return (BigDecimal) ssmBatchClient.queryObject("getDsContrPk", ssmPrm);
    }
    // END 2017/01/04 K.Kojima [QC#16513,ADD]

    // START 2017/05/19 K.Kitachi [QC#18213, ADD]
    private void setMsgTxt(NSZC061001PMsg pMsg) {
        String msgId;
        String msgPrmTxt;
        String msgTxt;
        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
            msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            msgPrmTxt = pMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue();
            msgTxt = getMsgTxt(msgId, new String[] {msgPrmTxt });
            if (!hasValue(msgTxt)) {
                continue;
            }
            setValue(pMsg.xxMsgIdList.no(i).xxMsgTxt, msgTxt);
        }
    }

    private String getMsgTxt(String msgId, String[] msgPrm) {
        if (!hasValue(msgId)) {
            return null;
        }
        String msgTxt = S21MessageFunc.clspGetMessage(msgId, msgPrm);
        if (!hasValue(msgTxt)) {
            return null;
        }
        if (msgTxt.length() > SUB_STR_POS_10) {
            msgTxt = msgTxt.substring(SUB_STR_POS_10);
        }
        return msgTxt;
    }
    // END 2017/05/19 K.Kitachi [QC#18213, ADD]

    // START 2017/06/05 K.Kojima [QC#18295,ADD]
    private String getSvcPgmMdseCd(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("dsContrDtlTpCdFleet", DS_CONTR_DTL_TP.FLEET);
        return (String) ssmBatchClient.queryObject("getSvcPgmMdseCd", ssmPrm);
    }
    // END 2017/06/05 K.Kojima [QC#18295,ADD]

    // Add Start 2017/07/26 QC#17129
    private DS_CONTRTMsg getDsContrTMsgForPk(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    private String getVarCharConstPrcCatgCd(String glblCmpyCd, String coaMdseTpCd, String svcLineBizCd) {
        String prcCatgCd = null;
        String varCharConstNm = null;
        if (COA_PROJ.DRUM.equals(coaMdseTpCd)) {
            // Drum
            if (LINE_BIZ_TP.ESS.equals(svcLineBizCd)) {
                varCharConstNm = DRUM_ESS_PRC_CATG_CD;
            } else if (LINE_BIZ_TP.LFS.equals(svcLineBizCd)) {
                varCharConstNm = DRUM_LFS_PRC_CATG_CD;
            } else if (LINE_BIZ_TP.PPS.equals(svcLineBizCd)) {
                varCharConstNm = DRUM_PPS_PRC_CATG_CD;
            }
        } else {
            // Parts
            if (LINE_BIZ_TP.ESS.equals(svcLineBizCd)) {
                varCharConstNm = PARTS_ESS_PRC_CATG_CD;
            } else if (LINE_BIZ_TP.LFS.equals(svcLineBizCd)) {
                varCharConstNm = PARTS_LFS_PRC_CATG_CD;
            } else if (LINE_BIZ_TP.PPS.equals(svcLineBizCd)) {
                varCharConstNm = PARTS_PPS_PRC_CATG_CD;
            }
        }

        if (!hasValue(varCharConstNm)) {
            return null;
        }
        prcCatgCd = ZYPCodeDataUtil.getVarCharConstValue(varCharConstNm, glblCmpyCd);
        return prcCatgCd;
    }
    // Add End 2017/07/26 QC#17129

    // START 2017/10/02 K.Kim [QC#21168,ADD]
    /**
     * Get Service Call Type
     * 
     * @param glblCmpyCd Global Company Code
     * @param dsSvcCallTpCd Service Call Type Code
     * @return DS_SVC_CALL_TPTMsg
     */
    private DS_SVC_CALL_TPTMsg getDsSvcCallTpTMsg(String glblCmpyCd, String dsSvcCallTpCd) {
        DS_SVC_CALL_TPTMsg inMsg = new DS_SVC_CALL_TPTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsSvcCallTpCd, dsSvcCallTpCd);
        return (DS_SVC_CALL_TPTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }
    // END 2017/10/02 K.Kim [QC#21168,ADD]

    // START 2017/10/06 K.Kim [QC#20078,ADD]
    /**
     * Get After Hour Allow Flag
     * 
     * @param glblCmpyCd Global Company Code
     * @param svcMachMstrPk Service Machine Master Pk
     * @return afterHourAllwFlg String
     */
    private String getAfterHourAllwFlg(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        return (String) ssmBatchClient.queryObject("getAfterHourAllwFlg", ssmPrm);
    }
    // END 2017/10/06 K.Kim [QC#20078,ADD]

    // START 2018/01/18 U.Kim [QC#22668, ADD]
    private String getStartTm(NSZC061001PMsg pMsg) {
        String setTime = pMsg.startTm.getValue();
        SimpleDateFormat sdfForTime = new SimpleDateFormat(TIME_FORMAT);
        Date newTime = new Date();
        try {
            newTime = sdfForTime.parse(setTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdfForTime.parse(setTime));
            cal.add(Calendar.MINUTE, ZYPCodeDataUtil.getNumConstValue(AHS_CALC_MIN, pMsg.glblCmpyCd.getValue()).intValue());
            newTime = new Date(cal.getTimeInMillis());
        } catch (ParseException e) {
            return setTime;
        }
        return sdfForTime.format(newTime);
    }
    // END 2018/01/18 U.Kim [QC#22668, ADD]
    // Add Start 2018/07/26 QC#27418
    private boolean excludePostReln(String glblCmpyCd, String svcLineBizCd) {
        if (!hasValue(svcLineBizCd)) {
            return false;
        }

        String constVal = ZYPCodeDataUtil.getVarCharConstValue(AHS_POST_RELN_EXCL_LOB, glblCmpyCd);
        if (!hasValue(constVal)) {
            return false;
        }
        String[] excludeLOB = constVal.split(",");
        for (int i = 0; i < excludeLOB.length; i++) {
            if (svcLineBizCd.equals(excludeLOB[i])) {
                return true;
            }
        }
        return false;
    }

    private void convMachLocTs(S21ApiMessageMap msgMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        NSZC061001PMsg pMsg = (NSZC061001PMsg) msgMap.getPmsg();
        SHIP_TO_CUSTTMsg shipToCustTMsg = getShipToCustTMsg(pMsg.glblCmpyCd.getValue(), svcMachMstrTMsg.curLocNum.getValue());
        if (shipToCustTMsg == null) {
            return;
        }

        int mode = NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC;
        String dateTime = pMsg.startDt.getValue() + pMsg.startTm.getValue();
        String ctryCd = shipToCustTMsg.ctryCd.getValue();
        String postCd = shipToCustTMsg.postCd.getValue();

        SvcTimeZoneInfo info = NSXC001001SvcTimeZone.convertTime(mode, dateTime, ctryCd, postCd);
        if (info == null || !hasValue(info.getDateTime())) {
            return;
        }
        String convTs = info.getDateTime();
        if (!hasValue(convTs) || convTs.length() < 14) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(pMsg.startDt, convTs.substring(0, 8));
        ZYPEZDItemValueSetter.setValue(pMsg.startTm, convTs.substring(8, 14));
    }

    private SHIP_TO_CUSTTMsg getShipToCustTMsg(String glblCmpyCd, String shipToCustCd) {
        SHIP_TO_CUSTTMsg inMsg = new SHIP_TO_CUSTTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        SHIP_TO_CUSTTMsgArray resultList = (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (resultList.getValidCount() == 0) {
            return null;
        }
        return resultList.no(0);
    }
    // Add End 2018/07/26 QC#27418

    // START 2018/07/18 M.Naito [QC#13309, ADD]
    private Map<String, Object> getTempEttlInfo(String glblCmpyCd, BigDecimal svcMachMstrPk, String startDt, String startTm, int dayOfTheWeek) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("startDt", startDt);
        ssmPrm.put("startTm", startTm);
        ssmPrm.put("dayOfTheWeek", dayOfTheWeek);
        return (Map<String, Object>) ssmBatchClient.queryObject("getTempEttlInfo", ssmPrm);
    }

    private Map<String, Object> getSvcLborDiscInfo(String glblCmpyCd, BigDecimal svcMachMstrPk, String startDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("startDt", startDt);
        return (Map<String, Object>) ssmBatchClient.queryObject("getSvcLborDiscInfo", ssmPrm);
    }

    private BigDecimal getTempEttlLborFee(String glblCmpyCd, String tempEttlNum, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("tempEttlNum", tempEttlNum);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        return (BigDecimal) ssmBatchClient.queryObject("getTempEttlLborFee", ssmPrm);
    }
    // END 2018/07/18 M.Naito [QC#13309, ADD]

    // START 2018/12/04 K.Kitachi [QC#28635, ADD]
    // Mod Start 2019/02/22 QC#30413
    private Map<String, Object> getSvcExclPrt(String glblCmpyCd, String mdseCd, BigDecimal svcMachMstrPk, String sellToCustCd, DS_CONTR_DTLTMsg dsContrDtlTMsg, String slsDt) {
        Map<String, Object> ssmParam = createParamIsExistSvcExclPrt(glblCmpyCd, mdseCd, svcMachMstrPk, sellToCustCd, dsContrDtlTMsg, slsDt);
        // Item Level
        Map<String, Object> svcExclPrtMap = getSvcExclPrt(ssmParam, CHK_LVL_ITEM);
        if (svcExclPrtMap != null) {
            return svcExclPrtMap;
        }
        // Serial Level
        svcExclPrtMap = getSvcExclPrt(ssmParam, CHK_LVL_SER);
        if (svcExclPrtMap != null) {
            return svcExclPrtMap;
        }
        // Contract Level
        if (dsContrDtlTMsg != null) {
            svcExclPrtMap = getSvcExclPrt(ssmParam, CHK_LVL_CONTR);
            if (svcExclPrtMap != null) {
                return svcExclPrtMap;
            }
        }
        // Customer Level
        svcExclPrtMap = getSvcExclPrt(ssmParam, CHK_LVL_CUST);
        if (svcExclPrtMap != null) {
            return svcExclPrtMap;
        }
        // Model Level
        svcExclPrtMap = getSvcExclPrt(ssmParam, CHK_LVL_MDL);
        if (svcExclPrtMap != null) {
            return svcExclPrtMap;
        }
        // Model Group Level
        svcExclPrtMap = getSvcExclPrt(ssmParam, CHK_LVL_MDL_GRP);
        if (svcExclPrtMap != null) {
            return svcExclPrtMap;
        }
        // Service Program Level
        if (dsContrDtlTMsg != null) {
            svcExclPrtMap = getSvcExclPrt(ssmParam, CHK_LVL_SVC_PGM);
            if (svcExclPrtMap != null) {
                return svcExclPrtMap;
            }
        }
        return null;
    }

    private Map<String, Object> createParamIsExistSvcExclPrt(String glblCmpyCd, String mdseCd, BigDecimal svcMachMstrPk, String sellToCustCd, DS_CONTR_DTLTMsg dsContrDtlTMsg, String slsDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("sellToCustCd", sellToCustCd);
        if (dsContrDtlTMsg != null) {
            ssmParam.put("dsContrPk", dsContrDtlTMsg.dsContrPk.getValue());
            ssmParam.put("svcPgmMdseCd", dsContrDtlTMsg.svcPgmMdseCd.getValue());
        }
        ssmParam.put("slsDt", slsDt);
        return ssmParam;
    }

    private Map<String, Object> getSvcExclPrt(Map<String, Object> ssmParam, String checkLevel) {
        ssmParam.put("checkLevel", checkLevel);
//        BigDecimal count = (BigDecimal) this.ssmBatchClient.queryObject("isExistSvcExclPrt", ssmParam);
        Map<String, Object> svcExclPrtMap = (Map<String, Object>) this.ssmBatchClient.queryObject("isExistSvcExclPrt", ssmParam);
//        if (count.compareTo(BigDecimal.ZERO) > 0) {
//            return true;
//        }
        return svcExclPrtMap;
    }

    private void setDiscExclPartsPrice(NSZC061001_xxPartsChrgListPMsg partsChrg, String glblCmpyCd, String svcTaskNum, Map<String, Object> svcExclPrtMap, String ccyCd) {
        if (isDiscBillTp(glblCmpyCd, svcTaskNum)) {
            setValue(partsChrg.svcPrtUnitAmt, calcDiscUnitAmt(glblCmpyCd, partsChrg.svcPrtUnitAmt.getValue(), svcExclPrtMap, ccyCd));
            setValue(partsChrg.svcPrtDealAmt, calcDiscDealAmt(partsChrg.svcPrtUnitAmt.getValue(), partsChrg.svcPrtQty.getValue()));
            setValue(partsChrg.svcPrtDiscRate, BigDecimal.ZERO);
        } else {
            setValue(partsChrg.svcPrtDiscRate, BigDecimal.ZERO);
        }
    }

    private void setDiscExclPartsPrice(NSZC061001_xxExpMdseListPMsg expMdse, String glblCmpyCd, String svcTaskNum, Map<String, Object> svcExclPrtMap, String ccyCd) {
        if (isDiscBillTp(glblCmpyCd, svcTaskNum)) {
            setValue(expMdse.xxSvcExpUnitAmt, calcDiscUnitAmt(glblCmpyCd, expMdse.xxSvcExpUnitAmt.getValue(), svcExclPrtMap, ccyCd));
            setValue(expMdse.xxSvcExpDealAmt, calcDiscDealAmt(expMdse.xxSvcExpUnitAmt.getValue(), expMdse.expQty.getValue()));
            setValue(expMdse.xxSvcExpDiscRate, BigDecimal.ZERO);
        } else {
            setValue(expMdse.xxSvcExpDiscRate, BigDecimal.ZERO);
        }
    }

    private void setDiscExclPartsPrice(NSZC061001_xxDrumChrgListPMsg drumChrg, String glblCmpyCd, String svcTaskNum, Map<String, Object> svcExclPrtMap, String ccyCd) {
        if (isDiscBillTp(glblCmpyCd, svcTaskNum)) {
            setValue(drumChrg.xxSvcDrumUnitAmt, calcDiscUnitAmt(glblCmpyCd, drumChrg.xxSvcDrumUnitAmt.getValue(), svcExclPrtMap, ccyCd));
            setValue(drumChrg.xxSvcDrumDealAmt, calcDiscDealAmt(drumChrg.xxSvcDrumUnitAmt.getValue(), drumChrg.svcPrtQty.getValue()));
            setValue(drumChrg.xxSvcDrumDiscRate, BigDecimal.ZERO);
        } else {
            setValue(drumChrg.xxSvcDrumDiscRate, BigDecimal.ZERO);
        }
    }

    private boolean isDiscBillTp(String glblCmpyCd, String svcTaskNum) {
        SVC_TASKTMsg task = getSvcZonCd(glblCmpyCd, svcTaskNum);
        if (task != null && hasValue(task.svcBillTpCd) && this.exclPrtDiscBillTpList.contains(task.svcBillTpCd.getValue())) {
            return true;
        }
        return false;
    }

    private BigDecimal calcDiscUnitAmt(String glblCmpyCd, BigDecimal unitAmt, Map<String, Object> svcExclPrtMap, String ccyCd) {
        BigDecimal calcAmt = BigDecimal.ZERO;
        if (!hasValue(unitAmt)) {
            return unitAmt;
        }

        Integer scale = getDealCcyDigit(glblCmpyCd, ccyCd);
        BigDecimal prtDiscRate = (BigDecimal) svcExclPrtMap.get("SVC_PRT_DISC_RATE");
        if (!hasValue(prtDiscRate)) {
            prtDiscRate = BigDecimal.ZERO;
        }
        BigDecimal prtDiscAmt = (BigDecimal) svcExclPrtMap.get("SVC_PRT_DISC_AMT");
        if (!hasValue(prtDiscAmt)) {
            prtDiscAmt = BigDecimal.ZERO;
        }
        BigDecimal discAmt = unitAmt.multiply(prtDiscRate).divide(new BigDecimal(100), scale, BigDecimal.ROUND_UP).add(prtDiscAmt);
        calcAmt = unitAmt.subtract(discAmt);
        if (BigDecimal.ZERO.compareTo(calcAmt) > 0) {
            calcAmt = BigDecimal.ZERO;
        }
        return calcAmt;
    }

    private BigDecimal calcDiscDealAmt(BigDecimal unitAmt, BigDecimal qty) {
        if (!hasValue(unitAmt) || !hasValue(qty)) {
            return null;
        }
        return unitAmt.multiply(qty);
    }
    // Mod End 2019/02/22 QC#30413
    // END 2018/12/04 K.Kitachi [QC#28635, ADD]

    // START 2023/03/23 K.Kitachi [QC#60065, ADD]
    private void setAhsCdEnblFlg(NSZC061001PMsg pMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, int dayOfTheWeek) {
        SVC_PRC_SHIFTTMsg svcPrcShiftTmsg = getSvcPrcShiftInfo(pMsg.glblCmpyCd.getValue(), getStartTm(pMsg), dayOfTheWeek, svcMachMstrTMsg.svcByLineBizTpCd.getValue());
        String ahsEnblFlg = getAhsEnblFlg(svcPrcShiftTmsg);

        // Not After Hours
        if (ZYPConstant.FLG_OFF_N.equals(ahsEnblFlg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.ahsCdEnblFlg, "");
            return;
        }

        // After Hours
        if (dsContrDtlTMsg != null) {
            if (isAhsForTermCondAhsWrkPgm(pMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), getStartTm(pMsg), dayOfTheWeek)) {
                ZYPEZDItemValueSetter.setValue(pMsg.ahsCdEnblFlg, ZYPConstant.FLG_ON_Y);
                return;
            }
        }

        // check Temporary Entitlement
        Map<String, Object> tempEttlMap = getTempEttlInfo(pMsg.glblCmpyCd.getValue(), svcMachMstrTMsg.svcMachMstrPk.getValue(), pMsg.startDt.getValue(), getStartTm(pMsg), dayOfTheWeek);
        if (tempEttlMap != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.ahsCdEnblFlg, ZYPConstant.FLG_ON_Y);
            return;
        }

        String ahsCd = getAhsCd(svcPrcShiftTmsg);
        AHS_ENBL_OVRDTMsgArray ahsEnblOvrdTMsgArray = getAhsEnblOvrd(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), ahsCd, pMsg.startDt.getValue(), getStartTm(pMsg));
        if (ahsEnblOvrdTMsgArray.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(pMsg.ahsCdEnblFlg, ZYPConstant.FLG_ON_Y);
        } else {
            String afterHourAllwFlg = getAfterHourAllwFlg(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
            if (ZYPConstant.FLG_ON_Y.equals(afterHourAllwFlg)) {
                ahsEnblFlg = getAhsCdEnblFlg(pMsg.glblCmpyCd.getValue(), svcMachMstrTMsg.curLocNum.getValue(), svcMachMstrTMsg.svcByLineBizTpCd.getValue(), svcMachMstrTMsg.fldSvcBrCd.getValue(), ahsCd, pMsg.svcCallSrcTpCd.getValue());
            } else {
                ahsEnblFlg = ZYPConstant.FLG_OFF_N;
            }
            if (ZYPConstant.FLG_ON_Y.equals(ahsEnblFlg)) {
                ZYPEZDItemValueSetter.setValue(pMsg.ahsCdEnblFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.ahsCdEnblFlg, ZYPConstant.FLG_OFF_N);
            }
        }
    }
    // END 2023/03/23 K.Kitachi [QC#60065, ADD]

    // START 2023/07/14 K.Watanabe [QC#61310, ADD]
    private boolean isShowRoomMachine(String glblCmpyCd, BigDecimal svcMachMstrPk, String startDt) {
        boolean isShowRoomMachine = false;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        List<String> svcMachMstrStsCdList = new ArrayList<String>();
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.CREATED);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.REMOVED);
        paramMap.put("svcMachMstrStsCdList", svcMachMstrStsCdList);
        paramMap.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        paramMap.put("rtlWhCatgCd", RTL_WH_CATG.SHOWROOM);
        paramMap.put("startDt", startDt);
        BigDecimal showRoomMachineCnt = (BigDecimal) this.ssmBatchClient.queryObject("getShowRoomMachineCnt", paramMap);
        if (showRoomMachineCnt != null && showRoomMachineCnt.compareTo(BigDecimal.ZERO) > 0) {
            isShowRoomMachine = true;
        }
        return isShowRoomMachine;
    }
    // END 2023/07/14 K.Watanabe [QC#61310, ADD]
}
