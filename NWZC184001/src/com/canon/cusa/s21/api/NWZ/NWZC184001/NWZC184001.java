/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC184001;

import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1450E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM0012E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM0013E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM0021E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM0036E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM0046E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM0049E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM0188E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1266E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1426E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1428E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1446E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1448E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1514E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1900E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1901E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1902E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1903E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1904E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1905E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1906E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1907E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1908E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1909E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1910E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1911E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1912E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM1927E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM2254E;
import static com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant.NWZM2255E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDPStringItem;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.MDSETMsg;
import business.db.PRC_CATGTMsg;
import business.db.SHPG_SVC_LVL_CARR_RELNTMsg;
import business.db.TOCTMsg;
import business.parts.NMZC260001PMsg;
import business.parts.NMZC260001_defSlsRepListPMsg;
import business.parts.NMZC260001_defSlsRepListPMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_EligibleCheckListPMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC180001PMsg;
import business.parts.NWZC184001PMsg;
import business.parts.NWZC184001_ordLineListPMsg;
import business.parts.NWZC184001_ordLineReturnListPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC260001.NMZC260001;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC184001.constant.NWZC184001Constant;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INTFC_XREF_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * IS Web Store Pricing API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/08/2016   Fujitsu         Kamide          Create
 * 08/04/2016   Fujitsu         N.Sugiura       Update          QC#11812
 * 09/15/2016   Hitachi         Y.Takeno        Update          QC#13158
 * 10/04/2016   Fujitsu         N.Sugiura       Update          QC#13170
 * 03/14/2017   Fujitsu         M.Ohno          Update          QC#16855
 * 2017/04/12   Fujitsu         Y.Kanefusa      Update          S21_NA#18235
 * 2017/05/18   Fujitsu         S.Iidaka        Update          S21_NA#18592
 * 2017/06/09   Fujitsu         S.Takami        Update          S21_NA#18296
 * 2017/12/14   Fujitsu         Hd.Sugawara     Update          QC#19804(Sol#349)
 * 2018/02/26   Fujitsu         Hd.Sugawara     Update          QC#22967
 * 2018/12/13   Fujitsu         M.Ohno          Update          QC#29315
 * 2020/02/18   Fujitsu         Y.Kanefusa      Update          S21_NA#55981
 * 2020/04/24   CITS            K.Ogino         Update          QC#56638
 * </pre>
 */
public class NWZC184001 extends S21ApiCommonBase {

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
    public NWZC184001() {
        ssmBatClnt = S21SsmBatchClient.getClient(getClass());
    }

    /**
     * exec IS Web Store Pricing API (List Parameter)
     * @param pMsgList API Parameter List
     * @param onBatTp ONBATCH type
     */
    public void execute(final List<NWZC184001PMsg> pMsgList, final ONBATCH_TYPE onBatTp) {
        for (NWZC184001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatTp);
        }
    }

    /**
     * exec IS Web Store Pricing API
     * @param pMsg API Parameter
     * @param onBatTp ONBATCH type
     */
    public void execute(final NWZC184001PMsg pMsg, final ONBATCH_TYPE onBatTp) {
        printParam(pMsg, "NWZC184001 IN-PARAM");
        init(pMsg, onBatTp);
        execute();
        setMsgIdFromMsgIdDetailList(pMsg);
        msgMap.flush();
        printParam(pMsg, "NWZC184001 OUT-PARAM");
    }

    private void setMsgIdFromMsgIdDetailList(NWZC184001PMsg pMsg) {
        if (pMsg.xxMsgIdList.getValidCount() < 1 && pMsg.xxMsgIdDetailList.getValidCount() > 0) {
            setErrMsg(pMsg, NWZM1927E);
        }
    }

    private void printParam(NWZC184001PMsg pMsg, String title) {
        EZDDebugOutput.println(1, "----------" + title + "----------Start", this);
        EZDDebugOutput.println(1, pMsg.toString(), this);
        EZDDebugOutput.println(1, "----------" + title + "----------End", this);
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

        NWZC184001PMsg pMsg = (NWZC184001PMsg) msgMap.getPmsg();
        String mode = pMsg.xxPrcCalcModeCd.getValue();
        if (NWZC184001Constant.MODE_ONLY_NET_PRC.equals(mode)) {
            getOnlyNetSellPrice(paramBean);
        } else if (NWZC184001Constant.MODE_NET_PRC_FRT_TAX.equals(mode)) {
            getAllOrderPrice(paramBean);
        } else {
            setErrMsg(pMsg, NWZM0013E);
        }

    }

    private void init(NWZC184001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

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

        NWZC184001PMsg pMsg = (NWZC184001PMsg) msgMap.getPmsg();
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            return true;
        }
        if (pMsg.xxMsgIdDetailList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    private void vldMandatoryCheck() {

        NWZC184001PMsg pMsg = (NWZC184001PMsg) msgMap.getPmsg();
        if (!hasValue(pMsg.glblCmpyCd)) {
            setErrMsg(pMsg, NWZM0188E);
        }

        if (!hasValue(pMsg.xxPrcCalcModeCd)) {
            setErrMsg(pMsg, NWZM0012E);
        }

        if (!hasValue(pMsg.lgcyOrdTpCd)) {
            setErrMsg(pMsg, NWZM1900E);
        }

        if (!hasValue(pMsg.lgcyOrdRsnCd)) {
            setErrMsg(pMsg, NWZM1901E);
        }

        if (!hasValue(pMsg.billToLocNum)) {
            setErrMsg(pMsg, NWZM1902E);
        }

        if (!hasValue(pMsg.soldToLocNum)) {
            setErrMsg(pMsg, NWZM1903E);
        }

        if (!hasValue(pMsg.shipToLocNum)) {
            setErrMsg(pMsg, NWZM1904E);
        }

        if (NWZC184001Constant.MODE_NET_PRC_FRT_TAX.equals(pMsg.xxPrcCalcModeCd.getValue())) {
            if (!hasValue(pMsg.frtCondCd)) {
                setErrMsg(pMsg, NWZM1266E);
            }

            if (!hasValue(pMsg.shpgSvcLvlCd)) {
                setErrMsg(pMsg, NWZM0049E);
            }
        }

        for (int i = 0; i < pMsg.ordLineList.getValidCount(); i++) {
            NWZC184001_ordLineListPMsg linePMsg = pMsg.ordLineList.no(i);
            if (!hasValue(linePMsg.ordSrcRefLineNum)) {
                setDetailErrMsg(pMsg, linePMsg, NWZM1905E);
            }

            if (!hasValue(linePMsg.mdseCd)) {
                setDetailErrMsg(pMsg, linePMsg, NWZM0021E);
            }

            if (!hasValue(linePMsg.ordQty)) {
                setDetailErrMsg(pMsg, linePMsg, NWZM0046E);
            }
        }
        return;
    }

    private void vldMasterCheck() {

        NWZC184001PMsg pMsg = (NWZC184001PMsg) msgMap.getPmsg();

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

        NWZC184001PMsg pMsg = (NWZC184001PMsg) msgMap.getPmsg();

        if (NWXC150001DsCheck.checkDsOrdTpAndDsOrdCatgRelation(pMsg.glblCmpyCd.getValue(), bean.getDsOrdTpCd(), bean.getDsOrdCatgCd())) {
            setErrMsg(pMsg, NWZM1450E);
        }

        checkBillShipSoldRelation(pMsg, bean);

        if (hasValue(pMsg.shpgSvcLvlCd) && hasValue(pMsg.carrCd)) {
            if (checkCarrCdAndShpgSvcLvlRelation(pMsg.glblCmpyCd.getValue(), pMsg.shpgSvcLvlCd.getValue(), pMsg.carrCd.getValue())) {
                setErrMsg(pMsg, NWZM1911E);
            }
        }

        if (hasValue(pMsg.frtCondCd) && hasValue(pMsg.shpgSvcLvlCd)) {
            // 2018/12/13 S21_NA#29315 Add Start
            String carrSvcLvl = null ;
            if (hasValue(pMsg.carrCd)) {
                SHPG_SVC_LVL_CARR_RELNTMsg inTMsg = new SHPG_SVC_LVL_CARR_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.shpgSvcLvlCd, pMsg.shpgSvcLvlCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.carrCd, pMsg.carrCd);
                inTMsg = (SHPG_SVC_LVL_CARR_RELNTMsg) S21FastTBLAccessor.findByKey(inTMsg);
                if (inTMsg != null) {
                    carrSvcLvl = inTMsg.carrSvcLvlCd.getValue();
                }
            }
            // 2018/12/13 S21_NA#29315 Add End
            // 2018/12/13 S21_NA#29315 Mod Start
//            if (checkFrtCondSvcLvlRelation(pMsg.glblCmpyCd.getValue(), bean.getLineBizTpCd(), pMsg.frtCondCd.getValue(), pMsg.shpgSvcLvlCd.getValue())) {
            if (NWXC150001DsCheck.checkFrtCondSvcLvlRelation(pMsg.glblCmpyCd.getValue() //
                    , this.slsDt //
                    , bean.getDsOrdTpCd() //
                    , pMsg.frtCondCd.getValue() //
                    , pMsg.shpgSvcLvlCd.getValue() //
                    , carrSvcLvl //
                    , bean.getShipToCustAcctCd() //
                    , bean.getShipToLocNum())) {
                setErrMsg(pMsg, NWZM1912E);
            }
            // 2018/12/13 S21_NA#29315 Add End
        }

        return;
    }

    private void setErrMsg(NWZC184001PMsg pMsg, String msgId) {
        setErrMsg(pMsg, msgId, null);
    }

    private void setErrMsg(NWZC184001PMsg pMsg, String msgId, String[] msgPrms) {
        int cnt = pMsg.xxMsgIdList.getValidCount();
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(cnt).xxMsgId, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(cnt).xxMsgTxt, cutMsg(S21MessageFunc.clspGetMessage(msgId, msgPrms)));
        pMsg.xxMsgIdList.setValidCount(cnt + 1);
    }

    private void setDetailErrMsg(NWZC184001PMsg pMsg, NWZC184001_ordLineListPMsg linePMsg, String msgId) {
        setDetailErrMsg(pMsg, linePMsg, msgId, null);
    }

    private void setDetailErrMsg(NWZC184001PMsg pMsg, NWZC184001_ordLineListPMsg linePMsg, String msgId, String[] msgPrms) {
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
        if (msg.length() > NWZC184001Constant.MAX_MSG_LEN) {
            return msg.substring(0, NWZC184001Constant.MAX_MSG_LEN);
        }
        return msg;
    }

    /**
     * <pre>
     * Call Pricing API Mode
     * </pre>
     * @param paramBean
     * @return No Error : true
     */
    private boolean getOnlyNetSellPrice(ParamBean paramBean) {

        NWZC184001PMsg pMsg = (NWZC184001PMsg) msgMap.getPmsg();
        String modeCd = NWZC157001.GET_LINE_PRICE;
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        setHdrParam(prcApiPMsg, modeCd, paramBean);
        setLineParam(prcApiPMsg, modeCd, paramBean);

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
                NWZC184001_ordLineListPMsg linePMsg = getTarget(pMsg, prcApiPMsg.NWZC157002PMsg.no(i));
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg.NWZC157002PMsg.no(i));
                for (S21ApiMessage msg : msgList) {
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    setDetailErrMsg(pMsg, linePMsg, msgId, msgPrms);
                }
            }
        }
        if (hasErr()) {
            return false;
        }

        int cnt = 0;
        for (int i = 0; i < prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
            NWZC184001_ordLineListPMsg linePMsg = getTarget(pMsg, prcApiPMsg.NWZC157004PMsg.no(i).trxLineNum.getValue());

            NWZC184001_ordLineReturnListPMsg ordLineRtnPMsg = pMsg.ordLineReturnList.no(cnt);

            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.ordSrcRefLineNum, linePMsg.ordSrcRefLineNum);
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.mdseCd, linePMsg.mdseCd);
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.dealUnitPrcAmt, prcApiPMsg.NWZC157004PMsg.no(i).xxUnitGrsPrcAmt);
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.dealDiscAmt, prcApiPMsg.NWZC157004PMsg.no(i).xxTotDiscAmt);
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.dealNetUnitPrcAmt, prcApiPMsg.NWZC157004PMsg.no(i).xxUnitNetPrcAmt);
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.ordQty, linePMsg.ordQty);
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.dealNetSlsAmt, prcApiPMsg.NWZC157004PMsg.no(i).xxSlsAmt);
            cnt++;
        }
        pMsg.ordLineReturnList.setValidCount(cnt);
        return true;
    }

    /**
     * <pre>
     * Call Pricing API Mode
     * </pre>
     * @param paramBean
     * @return NWZC157001PMsg
     */
    private NWZC157001PMsg callPricingApiForBasePrice(ParamBean paramBean) {

        NWZC184001PMsg pMsg = (NWZC184001PMsg) msgMap.getPmsg();
        String modeCd = NWZC157001.GET_BASE_PRICE;
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
                NWZC184001_ordLineListPMsg linePMsg = getTarget(pMsg, prcApiPMsg.NWZC157002PMsg.no(i));
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg.NWZC157002PMsg.no(i));
                for (S21ApiMessage msg : msgList) {
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    setDetailErrMsg(pMsg, linePMsg, msgId, msgPrms);
                }
            }
        }

        return prcApiPMsg;
    }

    /**
     * <pre>
     * Call Pricing API Mode
     * </pre>
     * @param paramBean
     * @return No Error : true
     */
    private boolean getAllOrderPrice(ParamBean paramBean) {

        NWZC157001PMsg basePricePMsg = callPricingApiForBasePrice(paramBean);
        if (hasErr()) {
            return false;
        }

        NWZC184001PMsg pMsg = (NWZC184001PMsg) msgMap.getPmsg();
        String modeCd = NWZC157001.GET_ORDER_ALL;

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        setHdrParam(prcApiPMsg, modeCd, paramBean);

        setLineParam(prcApiPMsg, modeCd, paramBean);
        if (hasErr()) {
            return false;
        }

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
                NWZC184001_ordLineListPMsg linePMsg = getTarget(pMsg, prcApiPMsg.NWZC157002PMsg.no(i));
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg.NWZC157002PMsg.no(i));
                for (S21ApiMessage msg : msgList) {
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    setDetailErrMsg(pMsg, linePMsg, msgId, msgPrms);
                }
            }
        }
        if (hasErr()) {
            return false;
        }

        int cnt = 0;
        BigDecimal ordTotDealChrgAmt = BigDecimal.ZERO;
        BigDecimal ordTotDealTaxAmt = BigDecimal.ZERO;
        for (int i = 0; i < prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
            NWZC184001_ordLineListPMsg linePMsg = getTarget(pMsg, prcApiPMsg.NWZC157004PMsg.no(i).trxLineNum.getValue());
            NWZC184001_ordLineReturnListPMsg ordLineRtnPMsg = pMsg.ordLineReturnList.no(cnt);
            ordLineRtnPMsg.ordSrcRefLineNum.setValue(linePMsg.ordSrcRefLineNum.getValue());
            ordLineRtnPMsg.mdseCd.setValue(linePMsg.mdseCd.getValue());
            ordLineRtnPMsg.dealUnitPrcAmt.setValue(prcApiPMsg.NWZC157004PMsg.no(i).xxUnitGrsPrcAmt.getValue());
            ordLineRtnPMsg.dealDiscAmt.setValue(prcApiPMsg.NWZC157004PMsg.no(i).xxTotDiscAmt.getValue());
            ordLineRtnPMsg.dealNetUnitPrcAmt.setValue(prcApiPMsg.NWZC157004PMsg.no(i).xxUnitNetPrcAmt.getValue());
            ordLineRtnPMsg.ordQty.setValue(linePMsg.ordQty.getValue());
            ordLineRtnPMsg.dealNetSlsAmt.setValue(prcApiPMsg.NWZC157004PMsg.no(i).xxSlsAmt.getValue());
            cnt++;

            if (hasValue(prcApiPMsg.NWZC157004PMsg.no(i).xxTotFrtSubAmt)) {
                ordTotDealChrgAmt = ordTotDealChrgAmt.add(prcApiPMsg.NWZC157004PMsg.no(i).xxTotFrtSubAmt.getValue());
            }
            if (hasValue(prcApiPMsg.NWZC157004PMsg.no(i).xxTotTaxAmt)) {
                ordTotDealTaxAmt = ordTotDealTaxAmt.add(prcApiPMsg.NWZC157004PMsg.no(i).xxTotTaxAmt.getValue());
            }
        }
        pMsg.ordLineReturnList.setValidCount(cnt);

        ZYPEZDItemValueSetter.setValue(pMsg.ordTotDealChrgAmt, ordTotDealChrgAmt);
        ZYPEZDItemValueSetter.setValue(pMsg.ordTotDealTaxAmt, ordTotDealTaxAmt);
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
    }

    private ParamBean getParamBean() {
        NWZC184001PMsg pMsg = (NWZC184001PMsg) msgMap.getPmsg();
        ParamBean bean = new ParamBean();

        getSoldTo(pMsg, bean);

        getShipTo(pMsg, bean);

        getBillTo(pMsg, bean);

        getOrdCatgAndOrdTp(pMsg, bean);
        if (hasErr()) {
            return null;
        }

        getLineBizTpCd(pMsg, bean);
        if (hasErr()) {
            return null;
        }

        getDsOrdLineCatg(pMsg, bean);
        if (hasErr()) {
            return null;
        }

        List<String> writers = getWriterLineBizRoleTp(pMsg.glblCmpyCd.getValue());
        getSlsRep(pMsg, bean, writers);
        if (hasErr()) {
            return null;
        }

        getPrcList(pMsg, bean);

        List<ParamDetailBean> list = new ArrayList<ParamDetailBean>();
        bean.setDetailBeanList(list);
        int cnt = pMsg.ordLineList.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NWZC184001_ordLineListPMsg ordLine = pMsg.ordLineList.no(i);
            ParamDetailBean detailBean = new ParamDetailBean();
            detailBean.setOrdSrcRefLineNum(ordLine.ordSrcRefLineNum.getValue());

            if (!getMdseCd(detailBean, ordLine, pMsg, bean.getSellToCustCd())) {
                continue;
            }

            if (NWZC184001Constant.MODE_NET_PRC_FRT_TAX.equals(pMsg.xxPrcCalcModeCd.getValue())) {
                getDefWhCd(bean, detailBean, ordLine, pMsg, "");
            }
            list.add(detailBean);
        }
        return bean;
    }

    private void getPrcList(NWZC184001PMsg pMsg, ParamBean bean) {
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
        if (hasValue(pMsg.slsDt)) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, pMsg.slsDt);
        } else {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, this.slsDt);
        }
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, bean.getDsOrdCatgCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, bean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, bean.getLineBizTpCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, bean.getSellToCustCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);
        // 2016/09/15 QC#13158 Add Start
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd, bean.getCoaBrCd());
        // 2016/09/15 QC#13158 Add End

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

    private boolean getMdseCd(ParamDetailBean detailBean, NWZC184001_ordLineListPMsg ordLine, NWZC184001PMsg pMsg, String sellToCustCd) {

        List<Map<String, Object>> mdseInfoList = getMdseInfo(pMsg.glblCmpyCd.getValue(), ordLine.mdseCd.getValue(), sellToCustCd);
        if (mdseInfoList.size() == 0) {
            setDetailErrMsg(pMsg, ordLine, NWZM0036E);
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
        return true;
    }

    private boolean getDefWhCd(ParamBean bean, ParamDetailBean detailBean, NWZC184001_ordLineListPMsg ordLine, NWZC184001PMsg pMsg, String dsOrdPosnNum) {
        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(pMsg.glblCmpyCd.getValue(), detailBean.getMdseCd());
        if (mdseTMsg == null) {
            setDetailErrMsg(pMsg, ordLine, NWZM0036E);
            return false;
        }

        if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue())) {
            return true;
        }

        boolean result = true;
        NWZC180001PMsg apiMsg = new NWZC180001PMsg();
        ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxModeCd, NWZC180001Constant.PROC_MODE_OTBD);
        ZYPEZDItemValueSetter.setValue(apiMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdCatgCd, bean.getDsOrdCatgCd());
        ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdTpCd, bean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(apiMsg.mdseCd, detailBean.getMdseCd());
        if (hasValue(pMsg.shipToPostCd)) {
            ZYPEZDItemValueSetter.setValue(apiMsg.postCd, pMsg.shipToPostCd.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(apiMsg.postCd, bean.getShipToPostCd());
        }

        ZYPEZDItemValueSetter.setValue(apiMsg.ordQty, ordLine.ordQty);

        // call NWZC1800 Default WH API
        new NWZC180001().execute(apiMsg, this.onBatType);

        if (S21ApiUtil.isXxMsgId(apiMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                if (msgId.endsWith("E")) {
                    setDetailErrMsg(pMsg, ordLine, msgId, msgPrms);
                    result = false;
                }
            }
        } else {
            detailBean.setRtlWhCd(apiMsg.rtlWhCd.getValue());
        }

        return result;
    }

    // Mod Start 2018/02/26 QC#22967
    //private boolean checkBillShipSoldRelation(NWZC184001PMsg pMsg, ParamBean bean) {
    private void checkBillShipSoldRelation(NWZC184001PMsg pMsg, ParamBean bean) {
        // Mod End 2018/02/26 QC#22967

        // Add Start 2018/02/26 QC#22967
        // Check Sold To(Customer Code) <- Ship To(Account Number)
        // relation.
        callCustInfoGetApiForCheckRelation(pMsg, //
                bean.getSoldToCustLocCd(), bean.getShipToCustAcctCd(), //
                NWZM2254E);

        // Check Bill To(Customer Code) <- Sold To(Account Number)
        // relation.
        callCustInfoGetApiForCheckRelation(pMsg, //
                bean.getBillToCustCd(), bean.getSellToCustCd(), //
                NWZM2255E);
        // Add End 2018/02/26 QC#22967

        // Del Start 2018/02/26 QC#22967
//        // 2017/06/09 S21_NA#18296 Mod Start
//        // QC#18235 2017/04/12 Mod Start
//        // NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApi(pMsg.glblCmpyCd.getValue(), bean.getBillToCustCd(), bean.getShipToCustCd(), bean.getSellToCustCd(), this.onBatType);
//        // QC#18592 2017/05/18 Mod Start
////        NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApi(pMsg.glblCmpyCd.getValue(), bean.getBillToCustCd(), bean.getShipToCustCd(), bean.getShipToCustAcctCd(), this.onBatType);
//        // QC#18592 2017/05/18 Mod End
//        // QC#18235 2017/04/12 Mod End
//        NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApi(//
//                pMsg.glblCmpyCd.getValue(), //
//                bean.getBillToCustCd(), //
//                bean.getSellToCustCd(), //
//                bean.getShipToCustAcctCd(), //
//                this.onBatType);
//        // 2017/06/09 S21_NA#18296 Mod End
//       if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {
//            for (int ix = 0; ix < custInfoGetApiMsg.xxMsgIdList.getValidCount(); ix++) {
//                String[] p = new String[4];
//                p[0] = custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_1.getValue();
//                p[1] = custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_2.getValue();
//                p[2] = custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_3.getValue();
//                p[3] = custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_4.getValue();
//                setErrMsg(pMsg, custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgId.getValue(), p);
//            }
//            return true;
//        }
//        for (int i = 0; i < custInfoGetApiMsg.EligibleCheckList.getValidCount(); i++) {
//            NMZC610001_EligibleCheckListPMsg eligiblePMsg = custInfoGetApiMsg.EligibleCheckList.no(i);
//            // 2017/06/09 S21_NA#18296 Mod Start
//            // 2016/10/04 S21_NA#13170 Mod Start
////            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) //
////                    || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnShipToFlg_S.getValue())) {
////            // 2016/10/04 S21_NA#13170 Mod End
////                setErrMsg(pMsg, NWZM1455E);
////                return true;
////            }
//            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) //
//                    || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnRecipFlg.getValue())) {
//                setErrMsg(pMsg, NWZM1455E);
//                return true;
//            }
//            // 2017/06/09 S21_NA#18296 Mod End
//        }
//        return false;
        // Del End 2018/02/26 QC#22967
    }

    // Add Start 2018/02/26 QC#22967
    /**
     * @param pMsg NWZC184001PMsg
     * @param billToCustCd String
     * @param acctNum String
     * @param errMsgId String
     */
    private void callCustInfoGetApiForCheckRelation(NWZC184001PMsg pMsg, String billToCustCd, String acctNum, String errMsgId) {
        NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApiBillTo( //
                pMsg.glblCmpyCd.getValue(), billToCustCd, acctNum, this.onBatType);

        if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {
            for (int ix = 0; ix < custInfoGetApiMsg.xxMsgIdList.getValidCount(); ix++) {
                String[] p = new String[4];
                p[0] = custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_1.getValue();
                p[1] = custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_2.getValue();
                p[2] = custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_3.getValue();
                p[3] = custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_4.getValue();
                setErrMsg(pMsg, custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgId.getValue(), p);
            }
        }

        for (int i = 0; i < custInfoGetApiMsg.EligibleCheckList.getValidCount(); i++) {
            NMZC610001_EligibleCheckListPMsg eligiblePMsg = custInfoGetApiMsg.EligibleCheckList.no(i);
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

    private boolean checkCarrCdAndShpgSvcLvlRelation(String glblCmpyCd, String shpgSvcLvlCd, String carrCd) {

        if (!hasValue(carrCd)) {
            return false;
        }
        Integer cnt = getCarrCdAndShpgSvcLvlRelationCnt(glblCmpyCd, shpgSvcLvlCd, carrCd);
        return (cnt == 0);
    }

    private void getOrdCatgAndOrdTp(NWZC184001PMsg pMsg, ParamBean bean) {
        List<Map<String, Object>> list = getXtrnlIntfcXref(pMsg.glblCmpyCd.getValue(), pMsg.lgcyOrdTpCd.getValue(), pMsg.lgcyOrdRsnCd.getValue());
        if (list.size() == 0) {
            setErrMsg(pMsg, NWZM1906E);
            return;
        }
        Map<String, Object> xtrnlIntfcXref = list.get(0);

        bean.setDsOrdCatgCd((String) xtrnlIntfcXref.get("TRGT_ATTRB_TXT_02"));
        bean.setDsOrdTpCd((String) xtrnlIntfcXref.get("TRGT_ATTRB_TXT_03"));
    }

    private void getLineBizTpCd(NWZC184001PMsg pMsg, ParamBean bean) {
        List<Map<String, Object>> list = getDefPrcCatgCd(pMsg.glblCmpyCd.getValue(), bean.getDsOrdTpCd(), this.slsDt);
        if (list.size() == 0) {
            setErrMsg(pMsg, NWZM1514E);
            return;
        }
        Map<String, Object> defPrcCatgCd = list.get(0);

        bean.setLineBizTpCd((String) defPrcCatgCd.get("LINE_BIZ_TP_CD"));
    }

    private void getSoldTo(NWZC184001PMsg pMsg, ParamBean bean) {

        List<Map<String, Object>> list = getBillToCustByLocNum(pMsg.glblCmpyCd.getValue(), pMsg.soldToLocNum.getValue());
        if (list == null || list.size() == 0) {
            setErrMsg(pMsg, NWZM1907E);
            return;
        }
        Map<String, Object> soldTo = list.get(0);

        bean.setSellToCustCd((String) soldTo.get("SELL_TO_CUST_CD"));
        bean.setSoldToCustLocCd((String) soldTo.get("BILL_TO_CUST_CD"));

    }

    private void getShipTo(NWZC184001PMsg pMsg, ParamBean bean) {

        List<Map<String, Object>> list = getShipToCustByLocNum(pMsg.glblCmpyCd.getValue(), pMsg.shipToLocNum.getValue());
        if (list == null || list.size() == 0) {
            setErrMsg(pMsg, NWZM1909E);
            return;
        }
        Map<String, Object> shipTo = list.get(0);

        bean.setShipToCustCd((String) shipTo.get("SHIP_TO_CUST_CD"));
        bean.setShipToCustAcctCd((String) shipTo.get("SELL_TO_CUST_CD"));
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

    private void getBillTo(NWZC184001PMsg pMsg, ParamBean bean) {

        List<Map<String, Object>> list = getBillToCustByLocNum(pMsg.glblCmpyCd.getValue(), pMsg.billToLocNum.getValue());
        if (list == null || list.size() == 0) {
            setErrMsg(pMsg, NWZM1908E);
            return;
        }
        Map<String, Object> billTo = list.get(0);

        bean.setBillToCustCd((String) billTo.get("BILL_TO_CUST_CD"));
        bean.setBillToCustAcctCd((String) billTo.get("SELL_TO_CUST_CD"));

    }

    private void getDsOrdLineCatg(NWZC184001PMsg pMsg, ParamBean bean) {

        List<Map<String, Object>> list = getDsOrdLineProcDfn(pMsg.glblCmpyCd.getValue(), bean.getDsOrdTpCd());
        if (list == null || list.size() == 0) {
            setErrMsg(pMsg, NWZM1910E);
            return;
        }
        Map<String, Object> dsOrdLineProcDfn = list.get(0);

        bean.setDsOrdLineCatdCd((String) dsOrdLineProcDfn.get("DS_ORD_LINE_CATG_CD"));
    }

    private NWZC184001_ordLineListPMsg getTarget(NWZC184001PMsg pMsg, NWZC157002PMsg prcApi02PMsg) {
        return getTarget(pMsg, prcApi02PMsg.trxLineNum.getValue());
    }

    private NWZC184001_ordLineListPMsg getTarget(NWZC184001PMsg pMsg, String ordSrcRefLineNum) {
        NWZC184001_ordLineListPMsg linePMsg = null;
        for (int j = 0; j < pMsg.ordLineList.getValidCount(); j++) {
            if (ordSrcRefLineNum.equals(pMsg.ordLineList.no(j).ordSrcRefLineNum.getValue())) {
                linePMsg = pMsg.ordLineList.no(j);
                break;
            }
        }
        return linePMsg;
    }

    private void setHdrParam(NWZC157001PMsg prcApiPMsg, String modeCd, ParamBean paramBean) {

        NWZC184001PMsg pMsg = (NWZC184001PMsg) msgMap.getPmsg();

        // Header
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        prcApiPMsg.xxModeCd.setValue(modeCd);
        if (hasValue(pMsg.slsDt)) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, pMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, pMsg.slsDt);
        } else {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, this.slsDt);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, this.slsDt);
        }

        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, paramBean.getDsOrdCatgCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, paramBean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, paramBean.getLineBizTpCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, paramBean.getSellToCustCd());

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
        NWZC184001PMsg pMsg = (NWZC184001PMsg) msgMap.getPmsg();

        int cnt = pMsg.ordLineList.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NWZC184001_ordLineListPMsg ordLine = pMsg.ordLineList.no(i);
            NWZC157002PMsg nwzc157002PMsg = prcApiPMsg.NWZC157002PMsg.no(i);

            ParamDetailBean detailBean = null;
            for (ParamDetailBean b : paramBean.getDetailBeanList()) {
                if (ordLine.ordSrcRefLineNum.getValue().equals(b.getOrdSrcRefLineNum())) {
                    detailBean = b;
                    break;
                }
            }

            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.trxLineNum, ordLine.ordSrcRefLineNum);
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.trxLineSubNum, "1");
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.shipToCustCd, paramBean.getShipToCustCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.dsAcctNum_SH, paramBean.getShipToCustAcctCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.billToCustCd, paramBean.getBillToCustCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.dsAcctNum_BL, paramBean.getBillToCustAcctCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.prcCatgCd, paramBean.getPrcCatgCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ccyCd, paramBean.getCcyCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.mdseCd, detailBean.getMdseCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.pkgUomCd, detailBean.getPkgUomCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.dsOrdLineCatgCd, paramBean.getDsOrdLineCatdCd());
            if (hasValue(detailBean.getInEachQty()) && BigDecimal.ZERO.compareTo(detailBean.getInEachQty()) != 0) {
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ordQty, ordLine.ordQty.getValue().divide(detailBean.getInEachQty(), 0, RoundingMode.DOWN));
            } else {
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ordQty, ordLine.ordQty);
            }

            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ordCustUomQty, ordLine.ordQty);
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
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.rtlWhCd, detailBean.getRtlWhCd());  // QC#55981 2020/02/20
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

    private boolean getSlsRep(NWZC184001PMsg pMsg, ParamBean bean, List<String> writers) {

        // call NMZC2600 Default Sales Rep API
        NMZC260001PMsg nMZC260001PMsg = new NMZC260001PMsg();
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.shipToCustCd, bean.getShipToCustCd());
        // 08/04/2016 QC#11812 Add Start
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.dsOrdTpCd, bean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.dsOrdCatgCd, bean.getDsOrdCatgCd()); // QC#55981 2020/02/20 Add
        // 08/04/2016 QC#11812 Add End
        // 2020/04/27 QC#56638 Add Start
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.billToCustCd, bean.getSoldToCustLocCd());

        if (!callDefSlsRepApi(pMsg, nMZC260001PMsg)) {
            return false;
        }

        // 2017/03/14 S21_NA#16855 Add Start
        // Mod Start 2017/12/14 QC#19804(Sol#349)
        //String trtyGrpTpCd = getTrtyGrpTpCdFromDsOrdTpCd(bean.getDsOrdTpCd(), pMsg.glblCmpyCd.getValue());
        List<String> trtyGrpTpCdList = getTrtyGrpTpCdFromDsOrdTpCd(bean.getDsOrdTpCd(), pMsg.glblCmpyCd.getValue());
        // Mod End 2017/12/14 QC#19804(Sol#349)
        // 2017/03/14 S21_NA#16855 Add End

        NMZC260001_defSlsRepListPMsgArray defSlsRepMsgArray = nMZC260001PMsg.defSlsRepList;
        for (int i = 0; i < defSlsRepMsgArray.getValidCount(); i++) {
            NMZC260001_defSlsRepListPMsg defSlsRepPMsg = defSlsRepMsgArray.no(i);

            // 2017/03/14 S21_NA#16855 Mod Start
//            if (defSlsRepPMsg.lineBizTpCd.getValue().equals(bean.getLineBizTpCd())) {
            // Mod Start 2017/12/14 QC#19804(Sol#349)
            //if (defSlsRepPMsg.lineBizTpCd.getValue().equals(bean.getLineBizTpCd()) //
            //        && (!ZYPCommonFunc.hasValue(trtyGrpTpCd) //
            //        || trtyGrpTpCd.equals(defSlsRepPMsg.trtyGrpTpCd.getValue()))) {
            boolean matchFlag = false;
            if (trtyGrpTpCdList != null) {
                if (trtyGrpTpCdList.contains(defSlsRepPMsg.trtyGrpTpCd.getValue())) {
                    matchFlag = true;
                }
            } else {
                if (defSlsRepPMsg.lineBizTpCd.getValue().equals(bean.getLineBizTpCd())) {
                    matchFlag = true;
                }
            }

            if (matchFlag) {
                // Mod End 2017/12/14 QC#19804(Sol#349)
            // 2017/03/14 S21_NA#16855 Mod End

                String lineBizRoleTpCd = defSlsRepPMsg.lineBizRoleTpCd.getValue();
                if (isWriter(writers, lineBizRoleTpCd)) {
                    bean.setSlsRepOrSlsTeamTocCd(defSlsRepPMsg.tocCd.getValue());
                    TOCTMsg toc = getToc(pMsg.glblCmpyCd.getValue(), defSlsRepPMsg.tocCd.getValue());
                    if (toc != null) {
                        bean.setCoaExtnCd(toc.coaExtnCd.getValue());
                        // 2016/09/15 QC#13158 Add Start
                        bean.setCoaBrCd(toc.coaBrCd.getValue());
                        // 2016/09/15 QC#13158 Add End
                    }
                    break;
                }
            }
        }

        return true;
    }

    private boolean callDefSlsRepApi(NWZC184001PMsg pMsg, NMZC260001PMsg nmzc260001PMsg) {
        boolean result = true;
        new NMZC260001().execute(nmzc260001PMsg, this.onBatType);

        if (S21ApiUtil.isXxMsgId(nmzc260001PMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nmzc260001PMsg);
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

    private boolean isWriter(List<String> writers, String lineBizRoleTpCd) {

        for (String writer : writers) {
            if (writer.equals(lineBizRoleTpCd)) {
                return true;
            }
        }
        return false;
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
    private List<Map<String, Object>> getXtrnlIntfcXref(String glblCmpyCd, String lgcyOrdTpCd, String lgcyOrdRsnCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", CPO_SRC_TP.IS_WEB);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP.ORDER_TYPE_MAPPING);
        ssmParam.put("srcAttrbTxt02", lgcyOrdTpCd);
        ssmParam.put("srcAttrbTxt03", lgcyOrdRsnCd);

        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getXtrnlIntfcXref", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getBillToCustByLocNum(String glblCmpyCd, String locNum) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("locNum", locNum);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getBillToCustByLocNum", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getShipToCustByLocNum(String glblCmpyCd, String locNum) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("locNum", locNum);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getShipToCustByLocNum", ssmParam);
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
    private List<Map<String, Object>> getDsOrdLineProcDfn(String glblCmpyCd, String dsOrdTpCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        ssmParam.put("flagY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("dsOrdLineDrctnCd", DS_ORD_LINE_DRCTN.OUTBOUND);

        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getDsOrdLineProcDfn", ssmParam);
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

    private Integer getCarrCdAndShpgSvcLvlRelationCnt(String glblCmpyCd, String shpgSvcLvlCd, String carrCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
        ssmParam.put("carrCd", carrCd);

        return (Integer) ssmBatClnt.queryObject("getCarrCdAndShpgSvcLvlRelationCnt", ssmParam);
    }

    private Integer getFrtCondSvcLvlRelationCnt(String glblCmpyCd, String lineBizTpCd, String frtCondCd, String shpgSvcLvlCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("lineBizTpCd", lineBizTpCd);
        ssmParam.put("frtCondCd", frtCondCd);
        ssmParam.put("shpgSvcLvlCd", shpgSvcLvlCd);

        return (Integer) ssmBatClnt.queryObject("getFrtCondSvcLvlRelationCnt", ssmParam);
    }

    // 2017/03/14 S21_NA#16855 Add Start
    // Mod Start 2017/12/14 QC#19804(Sol#349)
    ///**
    // * <pre>
    // * Get Territory Group Type Code From DS Order Type Code
    // * @param String dsOrdTpCd
    // * @param String glblCmpyCd
    // * </pre>
    // */
    //private static String getTrtyGrpTpCdFromDsOrdTpCd(String dsOrdTpCd, String glblCmpyCd) {
    /**
     * <pre>
     * Get Territory Group Type Code From DS Order Type Code
     * @param String dsOrdTpCd
     * @param String glblCmpyCd
     * </pre>
     */
    private static List<String> getTrtyGrpTpCdFromDsOrdTpCd(String dsOrdTpCd, String glblCmpyCd) {
        // Mod End 2017/12/14 QC#19804(Sol#349)

        if (!ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            return null;
        }

        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.dsOrdTpCd, dsOrdTpCd);

        dsOrdTpProcDfnTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(dsOrdTpProcDfnTMsg);
        if (dsOrdTpProcDfnTMsg != null) {
            // Mod Start 2017/12/14 QC#19804(Sol#349)
            //return dsOrdTpProcDfnTMsg.trtyGrpTpCd.getValue();
            String trtyGrpTpTxt = dsOrdTpProcDfnTMsg.trtyGrpTpTxt.getValue();

            List<String> trtyGrpTpCdList =  null;
            if (ZYPCommonFunc.hasValue(trtyGrpTpTxt)){
                trtyGrpTpCdList = Arrays.asList(trtyGrpTpTxt.split(","));
            }

            return trtyGrpTpCdList;
            // Mod End 2017/12/14 QC#19804(Sol#349)
        }

        return null;
    }
    // 2017/03/14 S21_NA#16855 Add End
}
