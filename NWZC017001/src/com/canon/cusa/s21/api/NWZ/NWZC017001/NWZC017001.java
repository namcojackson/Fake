/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC017001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.db.CPO_DTLTMsg;
import business.db.HLD_RSNTMsg;
import business.db.MDSETMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NWXC005001PMsg;
import business.parts.NWZC002001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC002001.NWZC002001;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ContractInfo;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXHldRsnTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Cancelation Date Past Hold API
 * 
 * Calculate (PSD, PDD) for a thing of non-shipment on a shipment date from the present 
 * and set HOLD for the thing which is over than the setting of the cancellation time limit
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/13   Fujitsu         H.Kato          Create          N/A
 * 2009/10/26   Fujitsu         S.Sugino        Update          N/A
 * 2009/11/19   Fujitsu         T.Kaneda        Update          N/A
 * 2009/11/24   Fujitsu         K.Kato          Update          N/A
 * 2010/02/08   Fujitsu         A.Suda          Update          N/A 
 * 2010/05/07   Fujitsu         K.Tajima        Update          2663 (performance tuning)
 * 2010/06/21   Fujitsu         A.Suda          Update          7296
 * 2013/04/05   Fujitsu         F.Saito         Update          WDS#R-OM005-001
 * </pre>
 */
public class NWZC017001 extends S21ApiCommonBase implements HLD_RSN {

    /** Data Company Code is not entered.  */
    public static final String NWZM0001E = "NWZM0001E";
    /** "Shipping Plan Number" is not entered. */
    public static final String NWZM0250E = "NWZM0250E";
    /** "Sales Date" is not entered. */
    public static final String NWZM0676E = "NWZM0676E";
    /** The corresponding data does not exist in "SHPG_PLN". */
    public static final String NWZM0267E = "NWZM0267E";

    /** Flag "Y" */
    private static final String Y = ZYPConstant.FLG_ON_Y;

    /** Line Sub Number : Set Parent */
    private static final String SET_PARENT_LINE_SUB_NUM = "000";

    /** ONLINE or BATCH */
    private ONBATCH_TYPE        onBatchType;
    /** S21ApiMessageMap */
    private S21ApiMessageMap    msgMap;
    /** S21SsmBatchClient */
    private S21SsmBatchClient   ssmClient;

    /** Constructor */
    public NWZC017001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Cancellation Date Past API.
     * 
     * @param validBean     NWXC005001ValidationBean
     * @param onBatchType   ONBATCH_TYPE
     */
    public void execute(final NWXC005001ValidationBean validBean, final ONBATCH_TYPE onBatchType) {

        final NWXC005001PMsg pMsg        = validBean.getInputPMsg();
        final CPO_DTLTMsg    cpoDtlTMsg  = validBean.getCdTMsg();
        final SHPG_PLNTMsg   shpgPlnTMsg = validBean.getSpTMsg();

        this.onBatchType = onBatchType;
        this.msgMap      = new S21ApiMessageMap(pMsg);

        try {

            if (checkReqParams(pMsg)) {
                doProcess(pMsg, cpoDtlTMsg, shpgPlnTMsg);
            }

        } finally {
            if (this.msgMap != null) {
                this.msgMap.flush();
            }
        }
    }

    /**
     * Cancellation Date Past API.
     * 
     * @param validBeanList List<NWXC005001ValidationBean>
     * @param onBatchType   ONBATCH_TYPE
     */
    public void execute(final List<NWXC005001ValidationBean> validBeanList, final ONBATCH_TYPE onBatchType) {
        for (NWXC005001ValidationBean validBean : validBeanList) {
            execute(validBean, onBatchType);
        }
    }

    private void addMsgId(String msgId) {
        if (this.msgMap != null) {
            this.msgMap.addXxMsgId(msgId);
        }
    }

    private boolean checkReqParams(NWXC005001PMsg pMsg) {

        // GLBL_CMPY_CD
        if (!hasValue(pMsg.glblCmpyCd)) {
            addMsgId(NWZM0001E);
            return false;
        }

        // SHPG_PLN_NUM
        if (!hasValue(pMsg.shpgPlnNum_I)) {
            addMsgId(NWZM0250E);
            return false;
        }

        // SLS_DT
        if (!hasValue(pMsg.slsDt)) {
            addMsgId(NWZM0676E);
            return false;
        }

        return true;
    }

    private void doProcess(NWXC005001PMsg pMsg, CPO_DTLTMsg cpoDtlTMsg, SHPG_PLNTMsg shpgPlnTMsg) {

        if (!hasValue(cpoDtlTMsg.cancDelyLimitDt.getValue()) && !hasValue(cpoDtlTMsg.cancShipLimitDt.getValue())) {
            return;
        }

        if (SET_PARENT_LINE_SUB_NUM.equals(shpgPlnTMsg.trxLineSubNum.getValue())) {
            return;
        }

        // --------------------------------------------------
        // call [NWZC002001 : Leadtime Calcualtion API]
        // --------------------------------------------------
        final NWZC002001PMsg leadTimeCalcApiPMsg = new NWZC002001PMsg();
        setValue(leadTimeCalcApiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        setValue(leadTimeCalcApiPMsg.mdseCd,        shpgPlnTMsg.mdseCd.getValue());
        setValue(leadTimeCalcApiPMsg.ordQty,        shpgPlnTMsg.ordQty.getValue());
        setValue(leadTimeCalcApiPMsg.shpgSvcLvlCd,  shpgPlnTMsg.reqShpgSvcLvlCd.getValue());
        setValue(leadTimeCalcApiPMsg.frtChrgMethCd, shpgPlnTMsg.reqFrtChrgMethCd.getValue());
        setValue(leadTimeCalcApiPMsg.frtChrgToCd,   shpgPlnTMsg.reqFrtChrgToCd.getValue());
        setValue(leadTimeCalcApiPMsg.xxRddDt,       "");
        setValue(leadTimeCalcApiPMsg.xxPsdDt,       "");
        setValue(leadTimeCalcApiPMsg.xxRwsdDt,      pMsg.slsDt.getValue());
        setValue(leadTimeCalcApiPMsg.invtyLocCd,    shpgPlnTMsg.invtyLocCd.getValue());
        setValue(leadTimeCalcApiPMsg.shipToPostCd,  shpgPlnTMsg.shipToPostCd.getValue());
        setValue(leadTimeCalcApiPMsg.shipToCustCd,  shpgPlnTMsg.shipToCustCd.getValue());
        setValue(leadTimeCalcApiPMsg.sellToCustCd,  shpgPlnTMsg.sellToCustCd.getValue());
        setValue(leadTimeCalcApiPMsg.shipToStCd,    shpgPlnTMsg.shipToStCd.getValue());
        setValue(leadTimeCalcApiPMsg.uomCd,         shpgPlnTMsg.custUomCd.getValue());
        setValue(leadTimeCalcApiPMsg.slsDt,         pMsg.slsDt.getValue());
        // ADD START 2013/04/05 WDS#R-OM005-001
        setValue(leadTimeCalcApiPMsg.configFlg,     getConfigFlg(pMsg.glblCmpyCd.getValue(), shpgPlnTMsg));
        // ADD END   2013/04/05 WDS#R-OM005-001
        setValue(leadTimeCalcApiPMsg.xxPddDt,       "");
        setValue(leadTimeCalcApiPMsg.xxPsdDt,       "");
        setValue(leadTimeCalcApiPMsg.xxWsdDt,       "");

        new NWZC002001().execute(leadTimeCalcApiPMsg, onBatchType);

        // has errors.
        if (leadTimeCalcApiPMsg.xxMsgIdList.getValidCount() > 0) {
            EZDMsg.copy(leadTimeCalcApiPMsg.xxMsgIdList, null, pMsg.xxMsgIdList, null);
            return;
        }


        boolean isHld = false;

        // --------------------------------------------------
        // check cancel Date
        // --------------------------------------------------
        String pddDt           = leadTimeCalcApiPMsg.xxPddDt.getValue();
        String psdDt           = leadTimeCalcApiPMsg.xxPsdDt.getValue();
        String cancDelyLimitDt = cpoDtlTMsg.cancDelyLimitDt.getValue();
        String cancShipLimitDt = cpoDtlTMsg.cancShipLimitDt.getValue();

        // compare cancDelyLimitDt with PddDt
        if (hasValue(cancDelyLimitDt) && pddDt.compareTo(cancDelyLimitDt) > 0) {
            isHld = true;
        }

        // compare cancShipLimitDt with PsdDt
        if (hasValue(cancShipLimitDt) && psdDt.compareTo(cancShipLimitDt) > 0) {
            isHld = true;
        }

        // in the case of the Hold, acquire Exception information
        if (isHld) {

            // --------------------------------------------------
            // Existence confirmation of the HOLD information
            // --------------------------------------------------
            if (isAlreadyHld(pMsg)) {
                return;
            }

            // --------------------------------------------------
            // acquisition of the Exception information
            // --------------------------------------------------
// 06/21/2010 Defect 7296            
//            final HLD_RSNTMsg hldRsnTMsg = NWXHldRsnTMsgThreadLocalCache.getInstance().get(pMsg.glblCmpyCd.getValue(), CANCELLATION_DATA_PAST);
            final HLD_RSNTMsg hldRsnTMsg = NWXHldRsnTMsgThreadLocalCache.getInstance().get(pMsg.glblCmpyCd.getValue(), CANCELLATION_DATE_PAST);
            if (Y.equals(hldRsnTMsg.exReqFlg.getValue())) {
                final MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(pMsg.glblCmpyCd.getValue(), shpgPlnTMsg.mdseCd.getValue());
                if (isException(pMsg, shpgPlnTMsg, mdseTMsg.mdseCd.getValue())) {
                    isHld = false;
                }
            }

            if (isHld) {
                setValue(pMsg.cpoOrdNum_O,        pMsg.cpoOrdNum_I);
                setValue(pMsg.cpoDtlLineNum_O,    pMsg.cpoDtlLineNum_I);
                setValue(pMsg.cpoDtlLineSubNum_O, pMsg.cpoDtlLineSubNum_I);
                setValue(pMsg.shpgPlnNum_O,       pMsg.shpgPlnNum_I);
// 06/21/2010 Defect 7296 
//                setValue(pMsg.hldRsnCd,           CANCELLATION_DATA_PAST);
                setValue(pMsg.hldRsnCd,           CANCELLATION_DATE_PAST);
            }
        }
    }

    private boolean isAlreadyHld(NWXC005001PMsg pMsg) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd",  pMsg.glblCmpyCd.getValue());
        ssmParam.put("shpgPlnNum",  pMsg.shpgPlnNum_I.getValue());
// 06/21/2010 Defect 7296   
        ssmParam.put("hldRsn",      CANCELLATION_DATE_PAST);
//        ssmParam.put("hldRsn",      CANCELLATION_DATA_PAST);

        return (Integer) ssmClient.queryObject("getHldCnt", ssmParam) > 0;
    }

    private boolean isException(NWXC005001PMsg pMsg, SHPG_PLNTMsg shpgPlnTMsg, String mdseCd) {

        String glblCmpyCd   = pMsg.glblCmpyCd.getValue();
        String shipToCustCd = shpgPlnTMsg.shipToCustCd.getValue();
        String billToCustCd = shpgPlnTMsg.billToCustCd.getValue();
        String sellToCustCd = shpgPlnTMsg.sellToCustCd.getValue();
// 06/21/2010 Defect 7296  
//        return NWXC001001ContractInfo.chkContractInfo(glblCmpyCd, mdseCd, shipToCustCd, sellToCustCd, billToCustCd, CANCELLATION_DATA_PAST, null);
        return NWXC001001ContractInfo.chkContractInfo(glblCmpyCd, mdseCd, shipToCustCd, sellToCustCd, billToCustCd, CANCELLATION_DATE_PAST, null);
    }

    // ADD START 2013/04/05 WDS#R-OM005-001
    /**
     * Get Configuration Item Flag
     * @param glblCmpyCd Global Company Code
     * @param shpgPlnTMsg SHPG_PLNTMsg
     * @return CPO_DTL.CONFIG_ITEM_FLG
     */
    private String getConfigFlg(String glblCmpyCd, SHPG_PLNTMsg shpgPlnTMsg) {
        CPO_DTLTMsg prm = new CPO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(prm.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prm.cpoOrdNum, shpgPlnTMsg.trxHdrNum);
        ZYPEZDItemValueSetter.setValue(prm.cpoDtlLineNum, shpgPlnTMsg.trxLineNum);
        ZYPEZDItemValueSetter.setValue(prm.cpoDtlLineSubNum, shpgPlnTMsg.trxLineSubNum);

        // Get CPO_DTLTMsg
        CPO_DTLTMsg dsCpoDtl = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(prm);
        if (dsCpoDtl == null) {
            return null;
        }

        // Return Configuration Item Flag
        return dsCpoDtl.configItemFlg.getValue();
    }
    // ADD END   2013/04/05 WDS#R-OM005-001
}
