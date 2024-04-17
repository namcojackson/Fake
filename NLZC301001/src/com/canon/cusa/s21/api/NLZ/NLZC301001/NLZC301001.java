/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC301001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDMsg;
import parts.common.EZDPMsg;
import parts.common.EZDPMsgArray;
import parts.common.EZDTBigDecimalItem;
import business.db.MDSETMsg;
import business.db.SCE_ORD_TPTMsg;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_SVC_LVLTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NLZC003001PMsg;
import business.parts.NLZC205001PMsg;
import business.parts.NLZC301001PMsg;
import business.parts.NLZC403001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC003001PMsg;
import business.parts.NWZC107001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC003001.NLZC003001;
import com.canon.cusa.s21.api.NLZ.NLZC205001.NLZC205001;
import com.canon.cusa.s21.api.NLZ.NLZC301001.constant.NLZC301001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC403001.NLZC403001;
import com.canon.cusa.s21.api.NLZ.NLZC403001.constant.NLZC403001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_TO;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * Warehouse Transfer API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/10/22   Hitachi         T.Aoyagi        Create          N/A
 * 2013/03/25   Hitachi         T.Aoyagi        Update          QC847
 * 2013/05/15   Hitachi         T.Aoyagi        Update          QC1093
 * 2013/05/21   Hitachi         T.Aoyagi        Update          QC1224
 * 2013/05/21   Hitachi         T.Tomita        Update          QC1420
 * 2015/03/31   Fujitsu         T.Nishikawa     Update          CSA
 * 2015/10/13   CITS            H.Sugawara      Update          CSA Project
 *</pre>
 */
public class NLZC301001 extends S21ApiCommonBase {

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient;

    /**
     * onlineBatchType
     */
    private ONBATCH_TYPE onlineBatchType = null;

    /**
     * Constructor
     */
    public NLZC301001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Warehouse Transfer API
     * @param inpPrmPMsg List<NLZC301001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NLZC301001PMsg> inpPrmPMsg, final ONBATCH_TYPE onBatchType) {

        this.onlineBatchType = onBatchType;

        doProcess(inpPrmPMsg);

    }

    private void doProcess(List<NLZC301001PMsg> pMsgList) {

        if (pMsgList.size() < 1) {
            return;
        }

        // input check
        if (!checkInputList(pMsgList)) {
            return;
        }

        // sort
        doSort(pMsgList);

        // create header list
        List<List<NLZC301001PMsg>> headerList = createInvOrderHeaderList(pMsgList);

        // call API
        for (List<NLZC301001PMsg> detailList : headerList) {

            SCE_ORD_TPTMsg sceOrdTpTMsg = selectSCE_ORD_TP(detailList.get(0));

            if (!callNLZC0030(detailList, sceOrdTpTMsg)) {
                continue;
            }

            if (!callNWZC1070(detailList, sceOrdTpTMsg)) {
                continue;
            }

            if (!callNWZC0030(detailList)) {
                continue;
            }

            if (!callNLZC2050(detailList)) {
                continue;
            }

            if (!callNSZC0010(detailList)) {
                continue;
            }
        }

    }

    private boolean checkInputList(List<NLZC301001PMsg> pMsgList) {

        S21ApiMessageMap msgMap = null;

        for (NLZC301001PMsg pMsg : pMsgList) {
            msgMap = new S21ApiMessageMap(pMsg);

            if (!checkInput(msgMap)) {
                msgMap.flush();
                break;
            }
        }

        // Error
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }

        // Normal
        return true;
    }

    private boolean checkInput(S21ApiMessageMap msgMap) {

        NLZC301001PMsg param = (NLZC301001PMsg) msgMap.getPmsg();

        if (!hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NLZC301001Constant.NLZM0003E);
        }
        if (!hasValue(param.xxInvtyOrdCratFlg)) {
            msgMap.addXxMsgId(NLZC301001Constant.NLZM2272E);
        }
        if (!hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NLZC301001Constant.NLZM2079E);
        }
        if (!hasValue(param.fromLocCd)) {
            msgMap.addXxMsgId(NLZC301001Constant.NLZM2285E);
        }
        if (!hasValue(param.toLocCd)) {
            msgMap.addXxMsgId(NLZC301001Constant.NLZM2286E);
        }
        if (!hasValue(param.fromInvtyLocCd)) {
            msgMap.addXxMsgId(NLZC301001Constant.NLZM2080E);
        }
        if (!hasValue(param.toInvtyLocCd)) {
            msgMap.addXxMsgId(NLZC301001Constant.NLZM2081E);
        }
        if (!hasValue(param.locStsCd)) {
            msgMap.addXxMsgId(NLZC301001Constant.NLZM0007E);
        }
        if (!hasValue(param.mdseCd)) {
            msgMap.addXxMsgId(NLZC301001Constant.NLZM0005E);
        }
        if (!hasValue(param.ordQty)) {
            msgMap.addXxMsgId(NLZC301001Constant.NLZM2083E);
        }
        if (!hasValue(param.stkStsCd)) {
            msgMap.addXxMsgId(NLZC301001Constant.NLZM0008E);
        }
        if (hasValue(param.delyDt) && hasValue(param.shipDt)) {
            msgMap.addXxMsgId(NLZC301001Constant.NLZM2085E);
        }
        if (!hasValue(param.delyDt) && !hasValue(param.shipDt)) {
            msgMap.addXxMsgId(NLZC301001Constant.NLZM2085E);
        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private void doSort(List<NLZC301001PMsg> pMsgList) {

        NLZC301001Util dLZC301001Util = new NLZC301001Util();
        dLZC301001Util.sort(pMsgList);
    }

    private List<List<NLZC301001PMsg>> createInvOrderHeaderList(List<NLZC301001PMsg> pMsgList) {

        List<List<NLZC301001PMsg>> headerList = new ArrayList<List<NLZC301001PMsg>>();
        List<NLZC301001PMsg> detailList = new ArrayList<NLZC301001PMsg>();

        NLZC301001PMsg prePMsg = new NLZC301001PMsg();

        for (NLZC301001PMsg pMsg : pMsgList) {

            if (!matchPMsg(prePMsg, pMsg)
                    || detailList.size() == NLZC301001Constant.DETAILLIST_MAX_SIZE) {

                detailList = new ArrayList<NLZC301001PMsg>();

                prePMsg = pMsg;
                detailList.add(pMsg);
                headerList.add(detailList);

                continue;
            }

            detailList.add(pMsg);
        }

        return headerList;
    }

    private boolean callNLZC0030(List<NLZC301001PMsg> detailList, SCE_ORD_TPTMsg sceOrdTpTMsg) {

        // header
        NLZC301001PMsg header = detailList.get(0);
        if (!ZYPConstant.FLG_ON_Y.equals(header.xxInvtyOrdCratFlg.getValue())) {
            return true;
        }
        NLZC003001PMsg dLZC003001PMsg = getNLZC003001PMsgHeader(header, sceOrdTpTMsg);

        NLZC003001 dLZC003001 = new NLZC003001();
        dLZC003001.execute(dLZC003001PMsg, this.onlineBatchType);

        if (!checkApiResultHeader(dLZC003001PMsg.xxMsgIdList, detailList)) {
            return false;
        }

        header.invtyOrdNum.setValue(dLZC003001PMsg.invtyOrdNum.getValue());

        boolean isError = false;
        int idx = 0;

        // detail
        for (NLZC301001PMsg pMsg : detailList) {

            dLZC003001PMsg = getNLZC003001PMsgDetail(pMsg,
                    header.invtyOrdNum.getValue(), idx);

            dLZC003001.execute(dLZC003001PMsg, this.onlineBatchType);

            if (!checkApiResult(dLZC003001PMsg.xxMsgIdList, pMsg)) {
                isError = true;
                continue;
            }

            pMsg.invtyOrdNum.setValue(dLZC003001PMsg.invtyOrdNum.getValue());
            pMsg.invtyOrdLineNum.setValue(dLZC003001PMsg.invtyOrdLineNum.getValue());

            idx++;
        }

        if (isError) {
            return false;
        }
        return true;
    }

    private boolean callNWZC1070(List<NLZC301001PMsg> detailList, SCE_ORD_TPTMsg sceOrdTpTMsg) {

        NWZC107001 dWZC107001 = new NWZC107001();

        boolean isError = false;
        int idx = 0;

        for (NLZC301001PMsg pMsg : detailList) {

            NWZC107001PMsg dWZC107001PMsg = getNWZC107001PMsg(pMsg, idx, sceOrdTpTMsg);

            dWZC107001.execute(dWZC107001PMsg, this.onlineBatchType);

            if (!checkApiResult(dWZC107001PMsg.xxMsgIdList, pMsg)) {
                isError = true;
                continue;
            }

            idx++;
        }

        if (isError) {
            return false;
        }
        return true;
    }

    private boolean callNWZC0030(List<NLZC301001PMsg> detailList) {

        List<NWZC003001PMsg> pMsgList = new ArrayList<NWZC003001PMsg>();

        int idx = 0;

        for (NLZC301001PMsg pMsg : detailList) {

            pMsgList.add(getNWZC003001PMsg(pMsg, idx));

            idx++;
        }

        NWZC003001 dWZC003001 = new NWZC003001();

        dWZC003001.execute(pMsgList, this.onlineBatchType);

        if (!checkApiResultList(pMsgList, detailList)) {
            return false;
        }

        return true;
    }

    private boolean callNLZC2050(List<NLZC301001PMsg> detailList) {

        List<NLZC205001PMsg> pMsgList = new ArrayList<NLZC205001PMsg>();
        for (int idx = 0; idx < detailList.size(); idx++) {
            NLZC301001PMsg pMsg = detailList.get(idx);
            pMsgList.add(getNLZC205001PMsg(pMsg, idx));
        }

        NLZC205001 nLZC205001 = new NLZC205001();

        // SO API
        nLZC205001.execute(pMsgList, this.onlineBatchType);

        if (!checkApiResultNLZC205001(pMsgList, detailList)) {
            return false;
        }

        List<NLZC205001PMsg> soPMsgList = new ArrayList<NLZC205001PMsg>();

        // SO_NUM
        for (int idx = 0; idx < detailList.size(); idx++) {
            NLZC301001PMsg pMsg = detailList.get(idx);
            ZYPEZDItemValueSetter.setValue(pMsg.soNum, pMsgList.get(0).soNum);
            soPMsgList.add(pMsgList.get(0));
        }

        return true;
    }

    private boolean callNSZC0010(List<NLZC301001PMsg> detailList) {

        List<NSZC001001PMsg> pMsgList = new ArrayList<NSZC001001PMsg>();

        for (NLZC301001PMsg pMsg : detailList) {

            Set<String> updSerNums = new HashSet<String>();

            if (pMsg.SerialInfo.getValidCount() < 1) {
                continue;
            } else if (!isIBTrackable(pMsg)) {
                continue;
            } else {
                // Serial Validation API
                NLZC403001 nLZC403001 = new NLZC403001();
                NLZC403001PMsg nLZC403001PMsg = new NLZC403001PMsg();
                for (int idx = 0; idx < pMsg.SerialInfo.getValidCount(); idx++) {
                    nLZC403001PMsg = getNLZC403001PMsg(pMsg, idx);
                    nLZC403001.execute(nLZC403001PMsg, this.onlineBatchType);

                    // Check Result Status Code
                    String rsltStsCd = nLZC403001PMsg.xxRsltStsCd.getValue();
                    if (NLZC403001Constant.RETURN_CODE_ERROR.equals(rsltStsCd)) {
                        return false;
                    } else if (NLZC403001Constant.RETURN_CODE_NORMAL.equals(rsltStsCd)) {
                        // Normal(Validated)
                        updSerNums.add(nLZC403001PMsg.serNum.getValue());
                    }
                }
            }

            // get Machine Master PK
            List<SVC_MACH_MSTRTMsg> pkList = selectSVC_MACH_MSTR(pMsg);
            for (SVC_MACH_MSTRTMsg svcMachMstr : pkList) {
                if (updSerNums.contains(svcMachMstr.serNum)) {
                    // Serial Number for Update
                    pMsgList.add(getNSZC001001PMsg(pMsg,
                            svcMachMstr.svcMachMstrPk));
                }
            }
        }

        // Machine Master Update API
        NSZC001001 nSZC001001 = new NSZC001001();

        nSZC001001.execute(pMsgList, this.onlineBatchType);

        if (!checkApiResultNSZC001001(pMsgList, detailList)) {
            return false;
        }

        return true;
    }

    private NLZC003001PMsg getNLZC003001PMsgHeader(NLZC301001PMsg pMsg, SCE_ORD_TPTMsg sceOrdTpTMsg) {

        NLZC003001PMsg dLZC003001PMsg = new NLZC003001PMsg();

        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.xxDtTpCd, NLZC003001.DT_TP_HDR);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.invtyOrdTpCd, pMsg.invtyOrdTpCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.invtyLocCd, pMsg.fromLocCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.locStsCd, pMsg.locStsCd);
        if (sceOrdTpTMsg != null) {
            ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.trxCd, sceOrdTpTMsg.trxCd);
            ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.trxRsnCd, sceOrdTpTMsg.trxRsnCd);
        }
        if (!hasValue(pMsg.shpgSvcLvlCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.shpgSvcLvlCd
                    , ZYPCodeDataUtil.getVarCharConstValue(NLZC301001Constant.TECH_TO_WH_DEF_SHPG_SVC_LVL_CD, pMsg.glblCmpyCd.getValue()));
        }
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.shpgSvcLvlCd, pMsg.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.dcTrnsfRddDt, pMsg.delyDt);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.dcTrnsfRsdDt, pMsg.shipDt);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.dsplTpCd, pMsg.dsplTpCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.invtyOrdStsCd, INVTY_ORD_STS.APPROVED);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.firstInvtyOrdCmntTxt, pMsg.firstInvtyOrdCmntTxt);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.scdInvtyOrdCmntTxt, pMsg.scdInvtyOrdCmntTxt);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.thirdInvtyOrdCmntTxt, pMsg.thirdInvtyOrdCmntTxt);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.frthInvtyOrdCmntTxt, pMsg.frthInvtyOrdCmntTxt);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.trxSrcTpCd, pMsg.trxSrcTpCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);

        return dLZC003001PMsg;
    }

    private NLZC003001PMsg getNLZC003001PMsgDetail(NLZC301001PMsg pMsg, String hdrInvtyOrdNum, int idx) {

        NLZC003001PMsg dLZC003001PMsg = new NLZC003001PMsg();
        String invtyOrdLineNum = ZYPCommonFunc.leftPad(String.valueOf(idx + 1),
                NLZC301001Constant.LENGTH_LINE_NUM, NLZC301001Constant.ZERO);

        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.xxDtTpCd, NLZC003001.DT_TP_DTL);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.invtyLocCd, pMsg.fromInvtyLocCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.invtyOrdTpCd, pMsg.invtyOrdTpCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.invtyOrdDtlStsCd, INVTY_ORD_STS.APPROVED);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.invtyOrdNum, hdrInvtyOrdNum);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.invtyOrdLineNum, invtyOrdLineNum);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.mdseCd, pMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.stkStsCd, pMsg.stkStsCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.shipToCustCd, pMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.invtyLocCd_D1, pMsg.toInvtyLocCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.locStsCd_D1, pMsg.locStsCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.toStkStsCd, pMsg.stkStsCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.ordQty, pMsg.ordQty);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.prchReqNum, pMsg.prchReqNum);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.prchReqLineNum, pMsg.prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.prchReqLineSubNum, pMsg.prchReqLineSubNum);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.prchReqRecTpCd, pMsg.prchReqRecTpCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.trxRefSrcTpCd, pMsg.trxRefSrcTpCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.trxRefNum, pMsg.trxRefNum);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.trxRefLineNum, pMsg.trxRefLineNum);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.trxRefLineSubNum, pMsg.trxRefLineSubNum);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.fsrNum, pMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.soTpCd, pMsg.soTpCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.adjCatgCd, pMsg.adjCatgCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.invtyOrdLineCmntTxt, pMsg.invtyOrdLineCmntTxt);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.invtyOrdLineCostAmt, pMsg.invtyOrdLineCostAmt);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.adjAcctAliasNm, pMsg.adjAcctAliasNm);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.coaCmpyCd, pMsg.coaCmpyCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.coaBrCd, pMsg.coaBrCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.coaAcctCd, pMsg.coaAcctCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.coaProdCd, pMsg.coaProdCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.coaChCd, pMsg.coaChCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.coaCcCd, pMsg.coaCcCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.coaAfflCd, pMsg.coaAfflCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.coaExtnCd, pMsg.coaExtnCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.coaProjCd, pMsg.coaProjCd);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.rmvConfigFlg, pMsg.rmvConfigFlg);

        for (int i = 0; i < pMsg.SerialInfo.length(); i++) {
            ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.serialInfoList.no(i).invtyOrdNum, hdrInvtyOrdNum);
            ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.serialInfoList.no(i).invtyOrdLineNum, invtyOrdLineNum);
            ZYPEZDItemValueSetter.setValue(dLZC003001PMsg.serialInfoList.no(i).serNum,
                    pMsg.SerialInfo.no(i).serNum);
        }
        return dLZC003001PMsg;
    }

    private NWZC107001PMsg getNWZC107001PMsg(NLZC301001PMsg pMsg, int idx, SCE_ORD_TPTMsg sceOrdTpTMsg) {

        NWZC107001PMsg dWZC107001PMsg = new NWZC107001PMsg();
        SHPG_SVC_LVLTMsg shpgSvcLvlTMsg = selectSHPG_SVC_LVL(pMsg);

        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.xxSysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.xxRqstTpCd, NWZC107001.REQ_TP_NEW);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.xxPrtlAcptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.trxSrcTpCd, pMsg.trxSrcTpCd);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.trxCd, sceOrdTpTMsg.trxCd);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.trxRsnCd, sceOrdTpTMsg.trxRsnCd);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.trxHdrNum, pMsg.invtyOrdNum);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.trxLineNum,
                ZYPCommonFunc.leftPad(String.valueOf(idx + 1), NLZC301001Constant.LENGTH_LINE_NUM,
                        NLZC301001Constant.ZERO));
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.trxLineSubNum, NLZC301001Constant.START_NUM);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.mdseCd, pMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.invtyLocCd, pMsg.fromInvtyLocCd);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.locStsCd, pMsg.locStsCd);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.stkStsCd, pMsg.stkStsCd);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.xxRqstQty, pMsg.ordQty);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.frtChrgMethCd, getFrtChrgMethCd(shpgSvcLvlTMsg));
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.frtChrgToCd, getFrtChrgToCd(shpgSvcLvlTMsg));
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.carrCd, pMsg.carrCd);
        if (!hasValue(pMsg.shpgSvcLvlCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.shpgSvcLvlCd
                    , ZYPCodeDataUtil.getVarCharConstValue(NLZC301001Constant.TECH_TO_WH_DEF_SHPG_SVC_LVL_CD, pMsg.glblCmpyCd.getValue()));
        }
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.shpgSvcLvlCd, pMsg.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.xxOrdTs,
                ZYPDateUtil.getCurrentSystemTime(NLZC301001Constant.YYYYMMDDHHMMSSSSS));
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.rddDt, pMsg.delyDt);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.rsdDt, pMsg.shipDt);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.xxUnitPrc, new BigDecimal(0));
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.xxSlsAmt, new BigDecimal(0));
        if (hasValue(pMsg.toInvtyLocCd)) {
            ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.billToCustCd, pMsg.toInvtyLocCd);
            ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.sellToCustCd, pMsg.toInvtyLocCd);
            ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.shipToCustCd, pMsg.toInvtyLocCd);
        }
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.dropShipFlg, pMsg.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.xxShipToName, pMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.xxShipToNameAddl, pMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.shipToFirstLineAddr, pMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.shipToScdLineAddr, pMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.shipToThirdLineAddr, pMsg.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.shipToFrthLineAddr, pMsg.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.shipToCtyAddr, pMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.shipToCntyNm, pMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.xxShipToProvName, pMsg.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.shipToStCd, pMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.shipToPostCd, pMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.shipToCtryCd, pMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.shipToFirstRefCmntTxt, pMsg.shipToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.shipToScdRefCmntTxt, pMsg.shipToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(dWZC107001PMsg.slsDt, pMsg.slsDt);

        return dWZC107001PMsg;
    }

    private NWZC003001PMsg getNWZC003001PMsg(NLZC301001PMsg pMsg, int idx) {

        NWZC003001PMsg dWZC003001PMsg = new NWZC003001PMsg();

        ZYPEZDItemValueSetter.setValue(dWZC003001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dWZC003001PMsg.shpgModeCd, NWZC003001.MODE_SHIPPINGREQUEST);
        ZYPEZDItemValueSetter.setValue(dWZC003001PMsg.trxSrcTpCd, pMsg.trxSrcTpCd);
        ZYPEZDItemValueSetter.setValue(dWZC003001PMsg.trxHdrNum, pMsg.invtyOrdNum);
        ZYPEZDItemValueSetter.setValue(dWZC003001PMsg.trxLineNum,
                ZYPCommonFunc.leftPad(String.valueOf(idx + 1), NLZC301001Constant.LENGTH_LINE_NUM,
                        NLZC301001Constant.ZERO));
        ZYPEZDItemValueSetter.setValue(dWZC003001PMsg.trxLineSubNum, NLZC301001Constant.START_NUM);
        ZYPEZDItemValueSetter.setValue(dWZC003001PMsg.avalSoQty, pMsg.ordQty);
        ZYPEZDItemValueSetter.setValue(dWZC003001PMsg.carrCd, pMsg.carrCd);

        return dWZC003001PMsg;
    }

    private NLZC205001PMsg getNLZC205001PMsg(NLZC301001PMsg pMsg, int idx) {
        NLZC205001PMsg nLZC205001PMsg = new NLZC205001PMsg();

        ZYPEZDItemValueSetter.setValue(nLZC205001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nLZC205001PMsg.sceOrdTpCd, pMsg.sceOrdTpCd);
        ZYPEZDItemValueSetter.setValue(nLZC205001PMsg.shpgPlnNum,
                selectSHPG_PLN(pMsg).shpgPlnNum);
        ZYPEZDItemValueSetter.setValue(nLZC205001PMsg.shpgFrceFlg, NLZC205001.SHPG_FRCE_FLG_ON);
        ZYPEZDItemValueSetter.setValue(nLZC205001PMsg.xxModeCd, NLZC205001.MODE_NEW);
        return nLZC205001PMsg;
    }

    private NSZC001001PMsg getNSZC001001PMsg(NLZC301001PMsg pMsg, EZDTBigDecimalItem svcMachMstrPk) {
        NSZC001001PMsg nSZC001001PMsg = new NSZC001001PMsg();

        ZYPEZDItemValueSetter.setValue(nSZC001001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nSZC001001PMsg.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(nSZC001001PMsg.xxModeCd, ProcessMode.ALLOCATION_ON.toString());
        ZYPEZDItemValueSetter.setValue(nSZC001001PMsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(nSZC001001PMsg.trxHdrNum, pMsg.prchReqNum);
        ZYPEZDItemValueSetter.setValue(nSZC001001PMsg.trxLineNum, pMsg.prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(nSZC001001PMsg.trxLineSubNum, pMsg.prchReqLineSubNum);
        return nSZC001001PMsg;
    }

    private NLZC403001PMsg getNLZC403001PMsg(NLZC301001PMsg pMsg, int idx) {
        NLZC403001PMsg nLZC403001PMsg = new NLZC403001PMsg();

        ZYPEZDItemValueSetter.setValue(nLZC403001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nLZC403001PMsg.xxModeCd, NLZC403001Constant.MODE_OUTBOUND);
        ZYPEZDItemValueSetter.setValue(nLZC403001PMsg.mdseCd, pMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(nLZC403001PMsg.serNum, pMsg.SerialInfo.no(idx).serNum);
        return nLZC403001PMsg;
    }

    private boolean checkApiResultHeader(EZDPMsgArray msgIdList, List<NLZC301001PMsg> pMsgList) {
        if (msgIdList.getValidCount() > 0) {
            S21ApiMessageMap msgMap;
            for (EZDPMsg pMsg : pMsgList) {
                msgMap = new S21ApiMessageMap(pMsg);
                NLZC301001PMsg dLZC301001PMsg = (NLZC301001PMsg) msgMap.getPmsg();
                EZDMsg.copy(msgIdList, null, dLZC301001PMsg.xxMsgIdList, null);
                dLZC301001PMsg.xxMsgIdList.setValidCount(msgIdList.getValidCount());
                msgMap.flush();
            }
            return false;
        }
        return true;
    }

    private boolean checkApiResult(EZDPMsgArray msgIdList, EZDPMsg pMsg) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);

        if (msgIdList.getValidCount() > 0) {
            NLZC301001PMsg dLZC301001PMsg = (NLZC301001PMsg) msgMap.getPmsg();
            EZDMsg.copy(msgIdList, null, dLZC301001PMsg.xxMsgIdList, null);
            dLZC301001PMsg.xxMsgIdList.setValidCount(msgIdList.getValidCount());
            msgMap.flush();
            return false;
        }

        return true;
    }

    private boolean checkApiResultList(List<NWZC003001PMsg> pMsgList, List<NLZC301001PMsg> detailList) {

        boolean isError = false;
        int idx = 0;

        for (NWZC003001PMsg pMsg : pMsgList) {

            S21ApiMessageMap msgMap = new S21ApiMessageMap(detailList.get(idx));

            if (pMsg.xxMsgIdList.getValidCount() > 0) {

                NLZC301001PMsg dLZC301001PMsg = (NLZC301001PMsg) msgMap.getPmsg();
                EZDMsg.copy(pMsg.xxMsgIdList, null, dLZC301001PMsg.xxMsgIdList, null);
                dLZC301001PMsg.xxMsgIdList.setValidCount(pMsg.xxMsgIdList.getValidCount());
                msgMap.flush();
                isError = true;
            }
        }

        if (isError) {
            return false;
        }

        return true;
    }

    private boolean checkApiResultNLZC205001(List<NLZC205001PMsg> pMsgList, List<NLZC301001PMsg> detailList) {
        boolean isError = false;
        int idx = 0;

        for (NLZC205001PMsg pMsg : pMsgList) {

            S21ApiMessageMap msgMap = new S21ApiMessageMap(detailList.get(idx));

            if (pMsg.xxMsgIdList.getValidCount() > 0) {

                NLZC301001PMsg dLZC301001PMsg = (NLZC301001PMsg) msgMap.getPmsg();
                EZDMsg.copy(pMsg.xxMsgIdList, null, dLZC301001PMsg.xxMsgIdList, null);
                dLZC301001PMsg.xxMsgIdList.setValidCount(pMsg.xxMsgIdList.getValidCount());
                msgMap.flush();
                isError = true;
            }
        }

        if (isError) {
            return false;
        }

        return true;
    }

    private boolean checkApiResultNSZC001001(List<NSZC001001PMsg> pMsgList, List<NLZC301001PMsg> detailList) {
        boolean isError = false;
        int idx = 0;

        for (NSZC001001PMsg pMsg : pMsgList) {

            S21ApiMessageMap msgMap = new S21ApiMessageMap(detailList.get(idx));

            if (pMsg.xxMsgIdList.getValidCount() > 0) {

                NLZC301001PMsg dLZC301001PMsg = (NLZC301001PMsg) msgMap.getPmsg();
                EZDMsg.copy(pMsg.xxMsgIdList, null, dLZC301001PMsg.xxMsgIdList, null);
                dLZC301001PMsg.xxMsgIdList.setValidCount(pMsg.xxMsgIdList.getValidCount());
                msgMap.flush();
                isError = true;
            }
        }

        if (isError) {
            return false;
        }

        return true;
    }

    private String getFrtChrgMethCd(SHPG_SVC_LVLTMsg tMsg) {

        if (tMsg != null && SHPG_SVC_TP.PICK_UP.equals(tMsg.shpgSvcTpCd.getValue())) {
            return FRT_CHRG_METH.PICK_UP_NO_CHARGE;
        }

        return FRT_CHRG_METH.N_OR_A;
    }

    private String getFrtChrgToCd(SHPG_SVC_LVLTMsg tMsg) {

        if (tMsg != null && SHPG_SVC_TP.PICK_UP.equals(tMsg.shpgSvcTpCd.getValue())) {
            return FRT_CHRG_TO.CUSTOMER;
        }

        return FRT_CHRG_TO.CANON;
    }

    private SHPG_SVC_LVLTMsg selectSHPG_SVC_LVL(NLZC301001PMsg pMsg) {

        SHPG_SVC_LVLTMsg tMsg = new SHPG_SVC_LVLTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, pMsg.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);

        return (SHPG_SVC_LVLTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    private SCE_ORD_TPTMsg selectSCE_ORD_TP(NLZC301001PMsg pMsg) {

        SCE_ORD_TPTMsg tMsg = new SCE_ORD_TPTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.inbdOtbdCd, INBD_OTBD.OUTBOUND);
        ZYPEZDItemValueSetter.setValue(tMsg.sceOrdTpCd, pMsg.sceOrdTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);

        return (SCE_ORD_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);

    }

    private SHPG_PLNTMsg selectSHPG_PLN(NLZC301001PMsg pMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("invtyOrdNum", pMsg.invtyOrdNum.getValue());
        queryParam.put("invtyOrdLineNum", pMsg.invtyOrdLineNum.getValue());

        List<SHPG_PLNTMsg> ssmResult = (List<SHPG_PLNTMsg>) ssmBatchClient.queryObjectList(
                "queryShpgPln", queryParam);
        if (ssmResult != null) {
            return ssmResult.get(0);
        }
        return null;
    }

    private List<SVC_MACH_MSTRTMsg> selectSVC_MACH_MSTR(NLZC301001PMsg pMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("mdseCd", pMsg.mdseCd.getValue());
        String[] serNums = new String[pMsg.SerialInfo.getValidCount()];
        for (int i = 0; i < pMsg.SerialInfo.getValidCount(); i++) {
            serNums[i] = pMsg.SerialInfo.no(i).serNum.getValue();
        }
        queryParam.put("serNums", serNums);

        return (List<SVC_MACH_MSTRTMsg>) ssmBatchClient.queryObjectList(
                "querySvcMachMstr", queryParam);
    }

    private MDSETMsg selectDS_MDSE_INFO(NLZC301001PMsg pMsg) {

        MDSETMsg tMsg = new MDSETMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, pMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);

        return (MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    private boolean matchPMsg(NLZC301001PMsg pMsg1, NLZC301001PMsg pMsg2) {

        if (!matchValue(pMsg1.fromLocCd.getValue(), pMsg2.fromLocCd.getValue())) {
            return false;
        }
        if (!matchValue(pMsg1.toLocCd.getValue(), pMsg2.toLocCd.getValue())) {
            return false;
        }
        if (!matchValue(pMsg1.dropShipFlg.getValue(), pMsg2.dropShipFlg.getValue())) {
            return false;
        }
        if (!matchValue(pMsg1.shipToCustCd.getValue(), pMsg2.shipToCustCd.getValue())) {
            return false;
        }
        if (!matchValue(pMsg1.shpgSvcLvlCd.getValue(), pMsg2.shpgSvcLvlCd.getValue())) {
            return false;
        }
        if (!matchValue(pMsg1.soTpCd.getValue(), pMsg2.soTpCd.getValue())) {
            return false;
        }
        if (!matchValue(pMsg1.delyDt.getValue(), pMsg2.delyDt.getValue())) {
            return false;
        }
        if (!matchValue(pMsg1.fsrNum.getValue(), pMsg2.fsrNum.getValue())) {
            return false;
        }
        if (!matchValue(pMsg1.prchReqNum.getValue(), pMsg2.prchReqNum.getValue())) {
            return false;
        }
        return true;

    }

    private boolean matchValue(String value1, String value2) {

        return nullToBlank(value1).equals(nullToBlank(value2));
    }

    private String nullToBlank(String value) {
        if (hasValue(value)) {
            return value;
        }
        return NLZC301001Constant.BLANK;
    }

    private boolean isIBTrackable(NLZC301001PMsg pMsg) {

        return ZYPConstant.FLG_ON_Y.equals(selectDS_MDSE_INFO(pMsg).instlBaseCtrlFlg);
    }

}
