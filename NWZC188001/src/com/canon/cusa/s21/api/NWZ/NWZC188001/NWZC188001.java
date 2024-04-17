/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC188001;

import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.*;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.BILLING_HLD_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.BOOKED_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.CLOSED_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.CLOSED_LOAN_RETURN_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.CLOSED_LOAN_SOLD_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.CPO_DTL_HLD_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.CPO_HLD_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.CPO_ORD_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.CREDIT_HOLD_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.DELIVERED_TO_SHOP_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.DS_CPO_RTRN_LINE_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.DS_CPO_RTRN_LINE_SUB_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.FNLZ_INV_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.INVTY_LOC_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.INVTY_LOC_CD_BO;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.INVTY_LOC_CD_CR;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.INVTY_LOC_CD_LO;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.INV_CRAT_PROC_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.IN_SHOP_CONFIG_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.LOAN_BAL_QTY;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.NWZM0473E;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.NWZM1308E;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.NWZM2224E;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.NWZM2225E;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.NWZM2226E;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.ON_LOAN_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.OPEN_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.ORD_LINE_STS_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.OUT_OF_WH_INVTY_PROC_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.PCT;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.PEND_DELY_CONF_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.PENDING_INSPECTION_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.PENDING_INSTALLATION_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.PENDING_INVOICE_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.PENDING_RE_SUBMISSION_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.PENDING_SHIP_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.PO_REQ_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.PROFITABILITY_HOLD_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.REF_CPO_DTL_LINE_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.REF_CPO_DTL_LINE_SUB_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.REV_RECOG_METH_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.RTRN_LINE_STS_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.SET_SHPG_PLN_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.SHIPPED_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.SHPG_PLN_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.SHPG_STS_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.SLS_HLD_QTY;
//import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.SO_CANCELED_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.SUPPLY_ABUSE_HOLD_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.SVC_ISTL_REQ_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.SYS_SRC_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.TRX_HDR_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.TRX_LINE_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.TRX_LINE_SUB_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.TRX_LINE_SUB_NUM_000;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.VALIDATION_HLD_FLG;
import static com.canon.cusa.s21.api.NWZ.NWZC188001.constant.NWZC188001Constant.WAITING_RECEIPT_FLG;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.MDSETMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NWZC188001PMsg;
import business.parts.NWZC188001_shpgPlnNumListPMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.REV_RECOG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_PLN_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Display Order Status Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/12   Fujitsu         T.Aoi           Create          N/A
 * 2017/06/07   Fujitsu         T.Aoi           Update          QC#18825
 * 2017/06/14   Fujitsu         T.Aoi           Update          QC#18828
 * 2017/06/16   Fujitsu         T.Aoi           Update          QC#19136
 * 2017/06/17   Fujitsu         T.Aoi           Update          QC#19246
 * 2017/06/23   Fujitsu         T.Aoi           Update          QC#19387
 * 2017/07/31   Fujitsu         T.Aoi           Update          QC#15838
 * 2017/08/23   Fujitsu         S.Iidaka        Update          QC#20549
 * 2017/08/29   Fujitsu         W.Honda         Update          QC#20778
 * 2017/09/27   Fujitsu         S.Takami        Update          QC#21167
 * 2017/10/27   CITS            T.Kikuhara      Update          QC#21821
 * 2017/11/14   Fujitsu         Y.Kanefusa      Update          S21_NA#22126
 * 2017/11/14   Fujitsu         Y.Kanefusa      Update          S21_NA#22490
 * 2017/12/08   Fujitsu         T.Aoi           Update          QC#22930
 * 2018/05/28   Fujitsu         K.Ishizuka      Update          QC#25562
 * 2018/12/14   Fujitsu         T.Murai         Update          QC#29488
 * 2019/01/26   Fujitsu         K.Kato          Update          QC#29932
 * 2019/02/19   Fujitsu         R.Nakamura      Update          QC#30448
 * 2019/03/01   Fujitsu         K.Kato          Update          QC#30599
 * 2019/03/11   Fujitsu         K.Kato          Update          QC#30691
 * 2019/03/11   Fujitsu         Y.Kanefusa      Update          S21_NA#30674
 * 2019/03/19   Fujitsu         K.Kato          Update          QC#30803
 * 2019/03/25   Fujitsu         K.Kato          Update          QC#30923
 * 2019/11/11   Fujitsu         S.Kosaka        Update          QC#54200
 * 2021/06/17   CITS            K.Ogino         Update          QC#58847
 * 2023/01/13   CITS            F.Fadriquela    Update          QC#60973
 * 2023/04/25   Hitachi         A.Kohinata      Update          QC#61337
 * </pre>
 */
public class NWZC188001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmBatchClient;

    /**
     * Constructor
     */
    public NWZC188001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * @param pMsgList List<NWZC188001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWZC188001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NWZC188001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * @param pMsg NWZC188001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC188001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        // Create Message Map
        final S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);

        doProcess(pMsg, msgMap);

        // Flush Message Map
        msgMap.flush();
    }

    /**
     * <pre>
     * doProcess
     * </pre>
     */
    private void doProcess(NWZC188001PMsg pMsg, S21ApiMessageMap msgMap) {

        // Parameter
        NWZC188001PMsg inMsg = (NWZC188001PMsg) msgMap.getPmsg();

        // Check Input Data Param
        if (!checkInParam(msgMap, inMsg)) {
            return;
        }

        List<Map<String, Object>> selfShpgPlnList = new ArrayList<Map<String, Object>>();
        Map<String, Map<String, Object>> prntShpgPlnList = new HashMap<String, Map<String, Object>>();
        List<Map<String, Object>> changeShpgPlnList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> rtrnLineList = new ArrayList<Map<String, Object>>();

        // Get Shipping Plan List
        for (int index = 0; index < inMsg.shpgPlnNumList.getValidCount(); index++) {

            // Set Param(Self Shipping Plan)
            Map<String, Object> selfShpgPlnParam = new HashMap<String, Object>();
            selfShpgPlnParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
            selfShpgPlnParam.put("shpgPlnNum", inMsg.shpgPlnNumList.no(index).shpgPlnNum.getValue());
            // selfShpgPlnParam.put("trxLineSubNum", TRX_LINE_SUB_NUM_000); //2017/12/08 QC#22930 Del

            // execute query
            Map<String, Object> selfShpgPln = (Map<String, Object>) ssmBatchClient.queryObject("getSelfShpgPln", selfShpgPlnParam);
            if (selfShpgPln != null) {

                // 2017/12/08 QC#22930 Mod Start
                String trxLineSubNum = (String) selfShpgPln.get(TRX_LINE_SUB_NUM);
                if (!TRX_LINE_SUB_NUM_000.equals(trxLineSubNum)) {
                    // self Shipping Plan(include child Shipping Plan)
                    selfShpgPlnList.add(selfShpgPln);

                    String setShpgPlnNum = (String) selfShpgPln.get(SET_SHPG_PLN_NUM);

                    if (setShpgPlnNum != null && prntShpgPlnList.get(setShpgPlnNum) == null) {

                        // Set Param
                        Map<String, Object> prntShpgPlnParam = new HashMap<String, Object>();
                        prntShpgPlnParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
                        prntShpgPlnParam.put("shpgPlnNum", setShpgPlnNum);

                        // execute query
                        Map<String, Object> prntShpgPlnMap = (Map<String, Object>) ssmBatchClient.queryObject("getParentShpgPln", prntShpgPlnParam);
                        prntShpgPlnMap.put("INDEX", index);
                        prntShpgPlnList.put(setShpgPlnNum, prntShpgPlnMap);
                    }
                } else {
                    // self Shipping Plan(include Parent Shipping Plan)
                    String prntShpgPlnNum = (String) selfShpgPln.get(SHPG_PLN_NUM);

                    if (prntShpgPlnList.get(prntShpgPlnNum) == null) {

                        // Set Param
                        Map<String, Object> prntShpgPlnParam = new HashMap<String, Object>();
                        prntShpgPlnParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
                        prntShpgPlnParam.put("shpgPlnNum", prntShpgPlnNum);

                        // execute query
                        Map<String, Object> selfPrntShpgPlnMap = (Map<String, Object>) ssmBatchClient.queryObject("getParentShpgPln", prntShpgPlnParam);
                        selfPrntShpgPlnMap.put("INDEX", index);
                        prntShpgPlnList.put(prntShpgPlnNum, selfPrntShpgPlnMap);
                    }
                }
                // 2017/12/08 QC#22930 Mod End
            }
        }

        // Get Return Line List
        for (int index = 0; index < inMsg.rmaLineList.getValidCount(); index++) {

            // Set Param
            Map<String, Object> rtrnParam = new HashMap<String, Object>();
            rtrnParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
            rtrnParam.put("cpoOrdNum", inMsg.cpoOrdNum.getValue());
            rtrnParam.put("dsCpoRtrnLineNum", inMsg.rmaLineList.no(index).dsCpoRtrnLineNum.getValue());
            rtrnParam.put("dsCpoRtrnLineSubNum", inMsg.rmaLineList.no(index).dsCpoRtrnLineSubNum.getValue());

            // execute query
            Map<String, Object> rtrnLine = (Map<String, Object>) ssmBatchClient.queryObject("getRtrn", rtrnParam);
            if (rtrnLine != null) {
                rtrnLineList.add(rtrnLine);
            }
        }

        // Self Shipping Plan
        List<SHPG_PLNTMsg> updateSelfShpgPlnList = new ArrayList<SHPG_PLNTMsg>();

        for (int index = 0; index < selfShpgPlnList.size(); index++) {
            String selfShpgPlnDplyStsCd = "";

            Map<String, Object> selfShpgPln = selfShpgPlnList.get(index);

            selfShpgPlnDplyStsCd = getSelfShpgPlnDplySts(selfShpgPln, inMsg);

            // QC#21821 ADD START
            if (inMsg.cancFlg.getValue() != null && ZYPConstant.FLG_ON_Y.equals(inMsg.cancFlg.getValue())) {
                selfShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.SO_CANCELLED;
            }
            // QC#21821 ADD END

            if (!selfShpgPlnDplyStsCd.equals(selfShpgPln.get("SHPG_PLN_DPLY_STS_CD"))) {

                SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.glblCmpyCd, inMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.shpgPlnNum, (String) selfShpgPln.get("SHPG_PLN_NUM"));
                shpgPlnTMsg = (SHPG_PLNTMsg) S21FastTBLAccessor.findByKeyForUpdate(shpgPlnTMsg);

                if (shpgPlnTMsg == null) {
                    NWZC188001_shpgPlnNumListPMsg megMap = (NWZC188001_shpgPlnNumListPMsg) inMsg.xxMsgIdList.get(index);
                    megMap.xxMsgId.setValue(NWZM2224E);
                    continue;
                }

                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.shpgPlnDplyStsCd, selfShpgPlnDplyStsCd);
                updateSelfShpgPlnList.add(shpgPlnTMsg);
                selfShpgPln.put("INDEX", index);
                selfShpgPln.put("SHPG_PLN_DPLY_STS_CD", selfShpgPlnDplyStsCd);
                changeShpgPlnList.add(selfShpgPln);
            }
        }

        if (updateSelfShpgPlnList.size() > 0) {
            S21FastTBLAccessor.update(updateSelfShpgPlnList.toArray(new SHPG_PLNTMsg[updateSelfShpgPlnList.size()]));
        }

        // Parent Shipping Plan
        List<SHPG_PLNTMsg> updatePrntShpgPlnList = new ArrayList<SHPG_PLNTMsg>();

        for (Map.Entry<String, Map<String, Object>> prntShpgPlnEntrySet : prntShpgPlnList.entrySet()) {
            String prntShpgPlnDplyStsCd = "";

            Map<String, Object> prntShpgPln = prntShpgPlnEntrySet.getValue();

            prntShpgPlnDplyStsCd = getPrntShpgPlnDplySts(prntShpgPln, inMsg);

            // QC#21821 ADD START
            if (inMsg.cancFlg.getValue() != null && ZYPConstant.FLG_ON_Y.equals(inMsg.cancFlg.getValue())) {
                prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.SO_CANCELLED;
            }
            // QC#21821 ADD END

            if (!prntShpgPlnDplyStsCd.equals(prntShpgPln.get("SHPG_PLN_DPLY_STS_CD"))) {

                SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.glblCmpyCd, inMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.shpgPlnNum, (String) prntShpgPln.get("SHPG_PLN_NUM"));
                shpgPlnTMsg = (SHPG_PLNTMsg) S21FastTBLAccessor.findByKeyForUpdate(shpgPlnTMsg);

                if (shpgPlnTMsg == null) {
                    NWZC188001_shpgPlnNumListPMsg megMap = (NWZC188001_shpgPlnNumListPMsg) inMsg.xxMsgIdList.get((Integer) prntShpgPln.get("INDEX"));
                    megMap.xxMsgId.setValue(NWZM2224E);
                    continue;
                }

                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.shpgPlnDplyStsCd, prntShpgPlnDplyStsCd);
                updatePrntShpgPlnList.add(shpgPlnTMsg);
                prntShpgPln.put("SHPG_PLN_DPLY_STS_CD", prntShpgPlnDplyStsCd);
                changeShpgPlnList.add(prntShpgPln);
            }
        }

        if (updatePrntShpgPlnList.size() > 0) {
            S21FastTBLAccessor.update(updatePrntShpgPlnList.toArray(new SHPG_PLNTMsg[updatePrntShpgPlnList.size()]));
        }

        // CPO Detail
        List<CPO_DTLTMsg> updateCpoDtlList = new ArrayList<CPO_DTLTMsg>();
        for (Map<String, Object> changeShpgPln : changeShpgPlnList) {
            String cpoDtlDplyStsCd = "";

            Map<String, Object> cpoDtlFromChangeShpgPlnParam = new HashMap<String, Object>();
            cpoDtlFromChangeShpgPlnParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
            cpoDtlFromChangeShpgPlnParam.put("shpgPlnNum", changeShpgPln.get(SHPG_PLN_NUM));

            List<Map<String, Object>> result = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getCpoDtlFromChangeShpgPln", cpoDtlFromChangeShpgPlnParam);

            cpoDtlDplyStsCd = getCpoDtlDplySts(changeShpgPln, result);

            // QC#21821 ADD START
            if (inMsg.cancFlg.getValue() != null && ZYPConstant.FLG_ON_Y.equals(inMsg.cancFlg.getValue())) {
                cpoDtlDplyStsCd = ORD_LINE_DPLY_STS.SO_CANCELLED;
            }
            // QC#21821 ADD END

            Map<String, Object> cpoDtlMap = result.get(0);
            if (!cpoDtlDplyStsCd.equals((String) cpoDtlMap.get("ORD_LINE_DPLY_STS_CD"))) {

                CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, inMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, (String) cpoDtlMap.get("CPO_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, (String) cpoDtlMap.get("CPO_DTL_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, (String) cpoDtlMap.get("CPO_DTL_LINE_SUB_NUM"));
                cpoDtlTMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(cpoDtlTMsg);

                if (cpoDtlTMsg == null) {
                    NWZC188001_shpgPlnNumListPMsg megMap = (NWZC188001_shpgPlnNumListPMsg) inMsg.xxMsgIdList.get((Integer) changeShpgPln.get("INDEX"));
                    megMap.xxMsgId.setValue(NWZM2225E);
                    continue;
                }

                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.ordLineDplyStsCd, cpoDtlDplyStsCd);
                updateCpoDtlList.add(cpoDtlTMsg);
            }
        }

        if (updateCpoDtlList.size() > 0) {
            S21FastTBLAccessor.update(updateCpoDtlList.toArray(new CPO_DTLTMsg[updateCpoDtlList.size()]));
        }

        // Return Detail
        List<DS_CPO_RTRN_DTLTMsg> updateRtrnDtlList = new ArrayList<DS_CPO_RTRN_DTLTMsg>();

        for (int index = 0; index < rtrnLineList.size(); index++) {
            String rtrnLineDplyStsCd = "";

            Map<String, Object> rtrnLineMap = rtrnLineList.get(index);

            rtrnLineDplyStsCd = getRtrnLineDplySts(rtrnLineMap, inMsg);

            //if (!rtrnLineDplyStsCd.equals((String) rtrnLineMap.get("RTRN_LINE_STS_CD"))) {
            if (!rtrnLineDplyStsCd.equals((String) rtrnLineMap.get("RTRN_LINE_DPLY_STS_CD"))) { // 2018/05/28 S21_NA#25562 Mod

                DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.glblCmpyCd, inMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.cpoOrdNum, (String) rtrnLineMap.get("CPO_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum, (String) rtrnLineMap.get("DS_CPO_RTRN_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum, (String) rtrnLineMap.get("DS_CPO_RTRN_LINE_SUB_NUM"));
                dsCpoRtrnDtlTMsg = (DS_CPO_RTRN_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsCpoRtrnDtlTMsg);

                if (dsCpoRtrnDtlTMsg == null) {
                    NWZC188001_shpgPlnNumListPMsg megMap = (NWZC188001_shpgPlnNumListPMsg) inMsg.xxMsgIdList.get(index);
                    megMap.xxMsgId.setValue(NWZM2225E);
                    continue;
                }

                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.rtrnLineDplyStsCd, rtrnLineDplyStsCd);
                updateRtrnDtlList.add(dsCpoRtrnDtlTMsg);
            }
        }

        if (updateRtrnDtlList.size() > 0) {
            S21FastTBLAccessor.update(updateRtrnDtlList.toArray(new DS_CPO_RTRN_DTLTMsg[updateRtrnDtlList.size()]));
        }

        // Order Header
        String ordHdrDplyStsCd = "";

        CPOTMsg cpoTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, inMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, inMsg.cpoOrdNum.getValue());
        cpoTMsg = (CPOTMsg) S21ApiTBLAccessor.findByKey(cpoTMsg);

        if (cpoTMsg == null) {
            msgMap.addXxMsgId(NWZM2226E);
            return;
        }

        ordHdrDplyStsCd = getOrdHdrDplySts(cpoTMsg, inMsg);

        if (ordHdrDplyStsCd != null && !ordHdrDplyStsCd.equals(cpoTMsg.ordHdrDplyStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(cpoTMsg.ordHdrDplyStsCd, ordHdrDplyStsCd);
            S21ApiTBLAccessor.update(cpoTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cpoTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NWZM2226E);
                return;
            }
        }

    }

    /**
     * <pre>
     * Check Input Parameters
     * </pre>
     * @param msgMap S21ApiMessageMap
     */
    private boolean checkInParam(S21ApiMessageMap msgMap, NWZC188001PMsg inMsg) {

        // Mandatory Check(glblCmpyCd)
        if (!ZYPCommonFunc.hasValue(inMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0473E);
            return false;
        }

        // Mandatory Check(cpoOrdNum)
        if (!ZYPCommonFunc.hasValue(inMsg.cpoOrdNum)) {
            msgMap.addXxMsgId(NWZM1308E);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * getShpgPlnDplySts
     * </pre>
     * @param checkShpgPlnData Map<String, Object>
     * @param inMsg NWZC188001PMsg
     */
    private String getSelfShpgPlnDplySts(Map<String, Object> checkShpgPlnData, NWZC188001PMsg inMsg) {

        String shpgStsCd = (String) checkShpgPlnData.get(SHPG_STS_CD);
        String shpgPlnNum = (String) checkShpgPlnData.get(SHPG_PLN_NUM);
        String invtyLocCd = (String) checkShpgPlnData.get(INVTY_LOC_CD);
        String refCpoDtlLineNum = (String) checkShpgPlnData.get(REF_CPO_DTL_LINE_NUM);
        String poReqFlg = (String) checkShpgPlnData.get(PO_REQ_FLG);
        String openFlg = (String) checkShpgPlnData.get(OPEN_FLG);
        String cpoHldFlg = (String) checkShpgPlnData.get(CPO_HLD_FLG);
        String cpoDtlHldFlg = (String) checkShpgPlnData.get(CPO_DTL_HLD_FLG);
        BigDecimal strSlsHldQty = (BigDecimal) checkShpgPlnData.get(SLS_HLD_QTY);
        if (strSlsHldQty == null) {
            strSlsHldQty = new BigDecimal(0);
        }
        Integer slsHldQty = strSlsHldQty.intValue();
        String revRecogMethCd = (String) checkShpgPlnData.get(REV_RECOG_METH_CD);
        BigDecimal strLoanBalQty = (BigDecimal) checkShpgPlnData.get(LOAN_BAL_QTY);
        if (strLoanBalQty == null) {
            strLoanBalQty = new BigDecimal(0);
        }
        Integer loanBalQty = strLoanBalQty.intValue();
        String ordLineStsCd = (String) checkShpgPlnData.get(ORD_LINE_STS_CD);
        String outOfWhInvtyProcFlg = (String) checkShpgPlnData.get(OUT_OF_WH_INVTY_PROC_FLG);
        String invCratProcFlg = (String) checkShpgPlnData.get(INV_CRAT_PROC_FLG);
        String svcIstlReqFlg = (String) checkShpgPlnData.get(SVC_ISTL_REQ_FLG);
        String ordBookFlg = (String) checkShpgPlnData.get(ORD_BOOK_FLG);
        // QC#58847
        String poOrdNum = (String) checkShpgPlnData.get("PO_ORD_NUM");

        // 2018/12/13 QC#29488 Add Start
        String mdseCd = (String) checkShpgPlnData.get(MDSE_CD);
        // 2019/01/26 QC#29932 Add Start
        MDSETMsg mdseTmsg = getMdse(inMsg.glblCmpyCd.getValue(), mdseCd);
        // 2019/01/26 QC#29932 Add End
        // 2018/12/13 QC#29488 Add End

        String shpgPlnDplyStsCd = "";
        if (SHPG_STS.CANCELLED.equals(shpgStsCd) || ORD_LINE_STS.CANCELLED.equals(ordLineStsCd)) {

            shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CANCELLED;
        } else if (getHld(inMsg, shpgPlnNum, HLD_RSN.S_OR_O_CANCEL)) {

            shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.SO_CANCELLED;
        } else if (getHld(inMsg, shpgPlnNum, HLD_RSN.PURCHASE_ORDER_CANCELL)) {

            shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PO_CANCELLED;
        } else if (ZYPConstant.FLG_OFF_N.equals(ordBookFlg)) {
            shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.ENTERED;
        } else if (SHPG_STS.VALIDATED.equals(shpgStsCd)) {
            // 2019/03/19 QC#30803 Mod Start
            //if (invtyLocCd == null && refCpoDtlLineNum != null) {
            if ((invtyLocCd == null || INVTY_LOC_CD_BO.equals(invtyLocCd) || INVTY_LOC_CD_CR.equals(invtyLocCd) || invtyLocCd.startsWith(INVTY_LOC_CD_LO))
               && refCpoDtlLineNum != null) {
            // 2019/03/19 QC#30803 Mod End

                shpgPlnDplyStsCd = getSelfShpgPlnDplyStsForIntangible(checkShpgPlnData, inMsg, shpgPlnNum);
            } else if (ZYPConstant.FLG_ON_Y.equals(poReqFlg)) {

                shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.WAITING_RECEIPT;
            } else if ((invtyLocCd != null && ((INVTY_LOC_CD_BO.equals(invtyLocCd)
                        || INVTY_LOC_CD_CR.equals(invtyLocCd))
                        || invtyLocCd.startsWith(INVTY_LOC_CD_LO))
                        || (invtyLocCd == null && refCpoDtlLineNum == null))) {
                // 2017/09/27 QC#21167 Mod Start
//                if ((ZYPConstant.FLG_ON_Y.equals(cpoHldFlg) || ZYPConstant.FLG_ON_Y.equals(cpoDtlHldFlg) || slsHldQty > 0) && getHldProcDfn(inMsg, shpgPlnNum)) { // QC#15383 Mod
                String bllgHldTp = getHldProcDfn(inMsg, shpgPlnNum);
                boolean isBllgHld = hasBllgHold(bllgHldTp);
                boolean isIttHld = isIttHld(bllgHldTp);
                if ((ZYPConstant.FLG_ON_Y.equals(cpoHldFlg) || ZYPConstant.FLG_ON_Y.equals(cpoDtlHldFlg) || slsHldQty > 0) && isBllgHld) { // 2017/09/27 QC#21167 Mod End

                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.BILLING_HOLD; // 2017/06/16 QC#19136 Add
                    // 2019/01/26 QC#29932 Del Start
                    //// 2018/12/14 QC#29488 Add Start
                    //MDSETMsg mdseTmsg = getMdse(inMsg.glblCmpyCd.getValue(), mdseCd);
                    //if (ZYPConstant.FLG_OFF_N.equals(mdseTmsg.invtyCtrlFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(mdseTmsg.instlBaseCtrlFlg.getValue())) {
                    //    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INSTALLATION;
                    //}
                    //// 2018/12/14 QC#29488 Add End
                    // 2019/01/26 QC#29932 Del End
                    // 2019/03/01 QC#30599 Add Start
                    if (ZYPConstant.FLG_OFF_N.equals(mdseTmsg.invtyCtrlFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(mdseTmsg.instlBaseCtrlFlg.getValue())) {
                        String machineSts = getMachineSts(inMsg, shpgPlnNum);
                        if (!MACH_MSTR_STS.INSTALLED.equals(machineSts)) {
                            shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_FULFILLMENT;
                        }
                    }
                    // 2019/03/01 QC#30599 Add End
                    // 2017/09/27 QC#21167 Add Start
                    if (isIttHld) {
                        shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_DEALER_INSTALL;
                    }
                    // 2017/09/27 QC#21167 Add End
                } else {

                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INVOICE;

                    // 2019/03/01 QC#30599 Add Start
                    if (ZYPConstant.FLG_OFF_N.equals(mdseTmsg.invtyCtrlFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(mdseTmsg.instlBaseCtrlFlg.getValue())) {
                        String machineSts = getMachineSts(inMsg, shpgPlnNum);
                        if (!MACH_MSTR_STS.INSTALLED.equals(machineSts)) {
                            shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_FULFILLMENT;
                        }
                    }
                    // 2019/03/01 QC#30599 Add End

                }
            } else {

                if (getHardAllocBatWrk(inMsg, shpgPlnNum)) {

                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.BACK_ORDER;
                } else {

                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_ALLOCATION;
                }
            }
        } else if (SHPG_STS.HARD_ALLOCATED.equals(shpgStsCd) || SHPG_STS.S_OR_O_PRINTED.equals(shpgStsCd)) {

            shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.WAITING_PICK;

        } else if (SHPG_STS.PICKED.equals(shpgStsCd)) {

            Map<String, Object> resultForShpgOrd = getShpgOrd(inMsg, shpgPlnNum);

            if (resultForShpgOrd != null && resultForShpgOrd.size() > 0) {
                if (ZYPConstant.FLG_ON_Y.equals(resultForShpgOrd.get(DELIVERED_TO_SHOP_FLG))) {

                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.DELIVERED_TO_SHOP;
                } else if (ZYPConstant.FLG_ON_Y.equals(resultForShpgOrd.get(IN_SHOP_CONFIG_FLG))) {

                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.IN_SHOP_OR_CONFIG;
                } else if (ZYPConstant.FLG_ON_Y.equals(resultForShpgOrd.get(PENDING_SHIP_FLG))) {

                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_SHIP;
                }
            }
        } else if (SHPG_STS.STAGED.equals(shpgStsCd)) {

            shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_SHIP;
        } else if (SHPG_STS.SHIPPED.equals(shpgStsCd)) {
            if (ZYPConstant.FLG_OFF_N.equals(openFlg)) {
                if (ZYPConstant.FLG_ON_Y.equals(outOfWhInvtyProcFlg)) {
                    if (getCpoDtlConvOrd(inMsg, shpgPlnNum)) {

                        shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED_LOAN_SOLD;
                    } else if (getCpoRtrnDtlConvOrd(inMsg, shpgPlnNum)) {

                        shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED_LOAN_RETURN;
                    }
                } else {

                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED;
                }
            } else if (invtyLocCd == null && refCpoDtlLineNum != null) { //2017/06/14 QC#18828 Mod

                shpgPlnDplyStsCd = getSelfShpgPlnDplyStsForIntangible(checkShpgPlnData, inMsg, shpgPlnNum);

            // 2017/08/23 QC#20549 Mod Start
//            } else if (REV_RECOG_METH.BOL.equals(revRecogMethCd) && ZYPConstant.FLG_OFF_N.equals(invCratProcFlg)) {
            } else if (REV_RECOG_METH.BOL.equals(revRecogMethCd) && loanBalQty == 0 && ZYPConstant.FLG_OFF_N.equals(invCratProcFlg)) {
            // 2017/08/23 QC#20549 Mod End

                shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.SHIPPED;
            // QC#58847 Mod. poRefFlg Delete.
            } else if ((ZYPConstant.FLG_OFF_N.equals(poReqFlg) || ZYPCommonFunc.hasValue(poOrdNum)) && !REV_RECOG_METH.BOL.equals(revRecogMethCd)) {

                shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_DELIVERY_CONFIRMATION;
            } else if (!REV_RECOG_METH.BOL.equals(revRecogMethCd) && (loanBalQty > 0 || ZYPConstant.FLG_ON_Y.equals(svcIstlReqFlg))) {

                shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INSTALLATION;
            } else if (loanBalQty > 0) {

                shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.ON_LOAN;
            } else if (ZYPConstant.FLG_OFF_N.equals(cpoHldFlg) && ZYPConstant.FLG_OFF_N.equals(cpoDtlHldFlg) && slsHldQty == 0) {
                if (ZYPConstant.FLG_OFF_N.equals(invCratProcFlg)) {

                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED;
                } else {

                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INVOICE;
                }
            } else if (ZYPConstant.FLG_ON_Y.equals(cpoHldFlg) || ZYPConstant.FLG_ON_Y.equals(cpoDtlHldFlg) || slsHldQty > 0) {
                // 2017/07/31 QC#15838 Mod Start
                // 2017/09/27 QC#21167 Mod Start
//                if (getHldProcDfn(inMsg, shpgPlnNum)) {
                String bllgHldTp = getHldProcDfn(inMsg, shpgPlnNum);
                boolean isBllgHld = hasBllgHold(bllgHldTp);
                boolean isIttHld = isIttHld(bllgHldTp);
                if (isBllgHld) { //  QC#21167 Mod End
                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.BILLING_HOLD;
                    // 2017/09/27 QC#21167 Add Start
                    if (isIttHld) {
                        shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_DEALER_INSTALL;
                    }
                    // 2017/09/27 QC#21167 Add End
                } else {
                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INVOICE;
                }
                // 2017/07/31 QC#15838 Mod End
            }

        } else if (SHPG_STS.P_OR_O_PRINTED.equals(shpgStsCd)) {
            if (invtyLocCd == null && refCpoDtlLineNum != null) {

                shpgPlnDplyStsCd = getSelfShpgPlnDplyStsForIntangible(checkShpgPlnData, inMsg, shpgPlnNum);
            } else {

                shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.WAITING_RECEIPT;
            }
        } else if (SHPG_STS.ARRIVED.equals(shpgStsCd)) {
            if (ZYPConstant.FLG_OFF_N.equals(openFlg)) {
                if (ZYPConstant.FLG_ON_Y.equals(outOfWhInvtyProcFlg)) {
                    if (getCpoDtlConvOrd(inMsg, shpgPlnNum)) {

                        shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED_LOAN_SOLD;
                    } else if (getCpoRtrnDtlConvOrd(inMsg, shpgPlnNum)) {

                        shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED_LOAN_RETURN;
                    }
                } else {

                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED;
                }
            } else if (invtyLocCd == null && refCpoDtlLineNum != null) {

                shpgPlnDplyStsCd = getSelfShpgPlnDplyStsForIntangible(checkShpgPlnData, inMsg, shpgPlnNum);
            // 2017/08/29 QC#20778 Mod Start
//            } else if (!REV_RECOG_METH.BOL.equals(revRecogMethCd) && (loanBalQty > 0 || ZYPConstant.FLG_ON_Y.equals(svcIstlReqFlg))) {
            // 2019/01/26 QC#29932 Mod Start
            //} else if ((!REV_RECOG_METH.BOL.equals(revRecogMethCd)) && ZYPConstant.FLG_ON_Y.equals(svcIstlReqFlg)) {
            // 2019/12/20 QC#54724 Mod Start 
//            } else if ((!REV_RECOG_METH.BOL.equals(revRecogMethCd)) && ZYPConstant.FLG_ON_Y.equals(svcIstlReqFlg) && (null != mdseTmsg && ZYPConstant.FLG_ON_Y.equals(mdseTmsg.invtyCtrlFlg.getValue()))) {
            } else if ((!REV_RECOG_METH.BOL.equals(revRecogMethCd)) && ZYPConstant.FLG_ON_Y.equals(svcIstlReqFlg) //
                    && (null != mdseTmsg && ZYPConstant.FLG_ON_Y.equals(mdseTmsg.invtyCtrlFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(mdseTmsg.instlBaseCtrlFlg.getValue()))) {
            // 2019/12/20 QC#54724 Mod Start 
            // 2019/01/26 QC#29932 Mod End
            // 2017/08/29 QC#20778 Mod End

            // 2017/08/23 QC#20549 Mod Start
                shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INSTALLATION;
//            } else if (loanBalQty > 0 && REV_RECOG_METH.BOL.equals(revRecogMethCd) && ZYPConstant.FLG_ON_Y.equals(invCratProcFlg)) {
            } else if (loanBalQty > 0 &&  ZYPConstant.FLG_OFF_N.equals(svcIstlReqFlg)) {
            // 2017/08/23 QC#20549 Mod End

                shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.ON_LOAN;
            } else if (ZYPConstant.FLG_OFF_N.equals(cpoHldFlg) && ZYPConstant.FLG_OFF_N.equals(cpoDtlHldFlg) && slsHldQty == 0) {
                if (ZYPConstant.FLG_OFF_N.equals(invCratProcFlg)) {

                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED;
                } else {

                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INVOICE;

                    // 2019/03/01 QC#30599 Add Start
                    if (ZYPConstant.FLG_OFF_N.equals(mdseTmsg.invtyCtrlFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(mdseTmsg.instlBaseCtrlFlg.getValue())) {
                        String machineSts = getMachineSts(inMsg, shpgPlnNum);
                        if (!MACH_MSTR_STS.INSTALLED.equals(machineSts)) {
                            shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_FULFILLMENT;
                        }
                    }
                    // 2019/03/01 QC#30599 Add End
                }
            } else if (ZYPConstant.FLG_ON_Y.equals(cpoHldFlg) || ZYPConstant.FLG_ON_Y.equals(cpoDtlHldFlg) || slsHldQty > 0) {
                // 2017/07/31 QC#15838 Mod Start
                // 2017/09/27 QC#21167 Mod Start
//                if (getHldProcDfn(inMsg, shpgPlnNum)) {
                String bllgHldTp = getHldProcDfn(inMsg, shpgPlnNum);
                boolean isBllgHld = hasBllgHold(bllgHldTp);
                boolean isIttHld = isIttHld(bllgHldTp);
                if (isBllgHld) { //  QC#21167 Mod End
                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.BILLING_HOLD;
                    // 2019/03/01 QC#30599 Add Start
                    if (ZYPConstant.FLG_OFF_N.equals(mdseTmsg.invtyCtrlFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(mdseTmsg.instlBaseCtrlFlg.getValue())) {
                        String machineSts = getMachineSts(inMsg, shpgPlnNum);
                        if (!MACH_MSTR_STS.INSTALLED.equals(machineSts)) {
                            shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_FULFILLMENT;
                        }
                    }
                    // 2019/03/01 QC#30599 Add End
                    // 2017/09/27 QC#21167 Add Start
                    if (isIttHld) {
                        shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_DEALER_INSTALL;
                    }
                    // 2017/09/27 QC#21167 Add End
                } else {
                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INVOICE;
                    // 2019/03/01 QC#30599 Add Start
                    if (ZYPConstant.FLG_OFF_N.equals(mdseTmsg.invtyCtrlFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(mdseTmsg.instlBaseCtrlFlg.getValue())) {
                        String machineSts = getMachineSts(inMsg, shpgPlnNum);
                        if (!MACH_MSTR_STS.INSTALLED.equals(machineSts)) {
                            shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_FULFILLMENT;
                        }
                    }
                    // 2019/03/01 QC#30599 Add End
                }
                // 2017/07/31 QC#15838 Mod End
            }
        } else if (SHPG_STS.INSTALLED.equals(shpgStsCd)) {
            if (ZYPConstant.FLG_OFF_N.equals(openFlg)) {
                if (ZYPConstant.FLG_ON_Y.equals(outOfWhInvtyProcFlg)) {
                    if (getCpoDtlConvOrd(inMsg, shpgPlnNum)) {

                        shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED_LOAN_SOLD;
                    } else if (getCpoRtrnDtlConvOrd(inMsg, shpgPlnNum)) {

                        shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED_LOAN_RETURN;
                    }
                } else {

                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED;
                }
            } else if (invtyLocCd == null && refCpoDtlLineNum != null) {

                shpgPlnDplyStsCd = getSelfShpgPlnDplyStsForIntangible(checkShpgPlnData, inMsg, shpgPlnNum);
            } else if (loanBalQty > 0) {

                shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.ON_LOAN;
            } else if (ZYPConstant.FLG_OFF_N.equals(cpoHldFlg) && ZYPConstant.FLG_OFF_N.equals(cpoDtlHldFlg) && slsHldQty == 0) {
                if (ZYPConstant.FLG_OFF_N.equals(invCratProcFlg)) {

                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED;
                } else {

                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INVOICE;
                    // 2019/03/01 QC#30599 Add Start
                    if (ZYPConstant.FLG_OFF_N.equals(mdseTmsg.invtyCtrlFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(mdseTmsg.instlBaseCtrlFlg.getValue())) {
                        String machineSts = getMachineSts(inMsg, shpgPlnNum);
                        if (!MACH_MSTR_STS.INSTALLED.equals(machineSts)) {
                            shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_FULFILLMENT;
                        }
                    }
                    // 2019/03/01 QC#30599 Add End
                }
            } else if (ZYPConstant.FLG_ON_Y.equals(cpoHldFlg) || ZYPConstant.FLG_ON_Y.equals(cpoDtlHldFlg) || slsHldQty > 0) {
//                // 2017/07/31 QC#15838 Mod Start
                // 2017/09/27 QC#21167 Mod Start
//                if (getHldProcDfn(inMsg, shpgPlnNum)) {
                String bllgHldTp = getHldProcDfn(inMsg, shpgPlnNum);
                boolean isBllgHld = hasBllgHold(bllgHldTp);
                boolean isIttHld = isIttHld(bllgHldTp);
                if (isBllgHld) { //  QC#21167 Mod End
                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.BILLING_HOLD;
                    // 2017/09/27 QC#21167 Add Start
                    if (isIttHld) {
                        shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_DEALER_INSTALL;
                    }
                    // 2017/09/27 QC#21167 Add End
                } else {
                    shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INVOICE;
                    // 2019/03/01 QC#30599 Add Start
                    if (ZYPConstant.FLG_OFF_N.equals(mdseTmsg.invtyCtrlFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(mdseTmsg.instlBaseCtrlFlg.getValue())) {
                        String machineSts = getMachineSts(inMsg, shpgPlnNum);
                        if (!MACH_MSTR_STS.INSTALLED.equals(machineSts)) {
                            shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_FULFILLMENT;
                        }
                    }
                    // 2019/03/01 QC#30599 Add End
                }
                // 2017/07/31 QC#15383 Mod End
            }
        } else if (SHPG_STS.INVOICED.equals(shpgStsCd)) {
            if (!getFnlzInvFlg(inMsg, shpgPlnNum)) {

                shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INVOICE;
            } else {

                shpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED;
            }
        } else {

            shpgPlnDplyStsCd = "";
        }

        return shpgPlnDplyStsCd;
    }

    /**
     * <pre>
     * getSelfShpgPlnDplyStsForIntangible
     * </pre>
     * @param checkShpgPlnData Map<String, Object>
     * @param inMsg NWZC188001PMsg
     * @param shpgPlnNum String
     */
    private String getSelfShpgPlnDplyStsForIntangible(Map<String, Object> checkShpgPlnData, NWZC188001PMsg inMsg, String shpgPlnNum) {
        String cpoHldFlg = (String) checkShpgPlnData.get(CPO_HLD_FLG);
        String cpoDtlHldFlg = (String) checkShpgPlnData.get(CPO_DTL_HLD_FLG);
        BigDecimal strSlsHldQty = (BigDecimal) checkShpgPlnData.get(SLS_HLD_QTY);
        // 2018/12/14 QC#29488 Add Start
        String  mdseCd = (String) checkShpgPlnData.get(MDSE_CD);
        // 2018/12/14 QC#29488 Add End
        if (strSlsHldQty == null) {
            strSlsHldQty = new BigDecimal(0);
        }
        Integer slsHldQty = strSlsHldQty.intValue();

        Map<String, Object> refParam = new HashMap<String, Object>();
        refParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        refParam.put("cpoOrdNum", inMsg.cpoOrdNum.getValue());
        refParam.put("refCpoDtlLineNum", (String) checkShpgPlnData.get(REF_CPO_DTL_LINE_NUM));
        refParam.put("refCpoDtlLineSubNum", (String) checkShpgPlnData.get(REF_CPO_DTL_LINE_SUB_NUM));
        refParam.put("shpgStsCd10", SHPG_STS.VALIDATED);
        refParam.put("shpgStsCd38", SHPG_STS.SHIPPED);
        refParam.put("shpgStsCd40", SHPG_STS.P_OR_O_PRINTED);
        refParam.put("shpgStsCd50", SHPG_STS.ARRIVED);
        refParam.put("shpgStsCd60", SHPG_STS.INSTALLED);
        refParam.put("invtyLocCdBo", INVTY_LOC_CD_BO);
        refParam.put("invtyLocCdCr", INVTY_LOC_CD_CR);
        refParam.put("invtyLocCdLo%", INVTY_LOC_CD_LO + PCT);
        refParam.put("revRecogMeth010", REV_RECOG_METH.BOL);
        // 2019/03/19 QC#30803 Mod Start
        refParam.put("svcMachMstrStsCdInstalled", SVC_MACH_MSTR_STS.INSTALLED);
        // 2019/03/19 QC#30803 Mod End

        Map<String, Object> refLineData = (Map<String, Object>) ssmBatchClient.queryObject("getRefLineData", refParam);

        if (ZYPConstant.FLG_ON_Y.equals(refLineData.get("PENDING_INVOICE_FLG"))) {
            // 2017/09/27 QC#21167 Mod Start
//            // 2017/06/17 QC#19246 Mod Start
//            if ((ZYPConstant.FLG_ON_Y.equals(cpoHldFlg) || ZYPConstant.FLG_ON_Y.equals(cpoDtlHldFlg) || slsHldQty > 0) && getHldProcDfn(inMsg, shpgPlnNum)) { // QC#15383 Mod
//                return SHPG_PLN_DPLY_STS.BILLING_HOLD;
//            } else {
//                return SHPG_PLN_DPLY_STS.PENDING_INVOICE;
//            }
//            // 2017/06/17 QC#19246 Mod End

            String bllgHldTp = getHldProcDfn(inMsg, shpgPlnNum);
            boolean isBllgHld = hasBllgHold(bllgHldTp);
            boolean isIttHld = isIttHld(bllgHldTp);
            if ((ZYPConstant.FLG_ON_Y.equals(cpoHldFlg) || ZYPConstant.FLG_ON_Y.equals(cpoDtlHldFlg) || slsHldQty > 0) && isBllgHld) {
                if (isIttHld) {
                    return SHPG_PLN_DPLY_STS.PENDING_DEALER_INSTALL;
                } else {
                    return SHPG_PLN_DPLY_STS.BILLING_HOLD;
                }
            } else {
                return SHPG_PLN_DPLY_STS.PENDING_INVOICE;
            }
            // 2017/09/27 QC#21167 Mod End
        } else if (ZYPConstant.FLG_ON_Y.equals(refLineData.get("CLOSE_FLG"))) {
            return SHPG_PLN_DPLY_STS.CLOSED;
        } else {
            // 2019/01/26 QC#29932 Del Start
            //// 2018/12/14 QC#29488 Add Start
            //MDSETMsg mdseTmsg = getMdse(inMsg.glblCmpyCd.getValue(), mdseCd);
            //if (ZYPConstant.FLG_OFF_N.equals(mdseTmsg.invtyCtrlFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(mdseTmsg.instlBaseCtrlFlg.getValue())) {
            //    return SHPG_PLN_DPLY_STS.PENDING_INSTALLATION;
            //}
            // 2018/12/14 QC#29488 Add End
            // 2019/01/26 QC#29932 Del End

            // 2019/03/11 QC#30691 Add Start
            String bllgHldTp = getHldProcDfn(inMsg, shpgPlnNum);
            boolean isBllgHld = hasBllgHold(bllgHldTp);
            boolean isIttHld = isIttHld(bllgHldTp);
            if ((ZYPConstant.FLG_ON_Y.equals(cpoHldFlg) || ZYPConstant.FLG_ON_Y.equals(cpoDtlHldFlg) || slsHldQty > 0) && isBllgHld) {
                if (isIttHld) {
                    return SHPG_PLN_DPLY_STS.PENDING_DEALER_INSTALL;
                }
            }
            // 2019/03/11 QC#30691 Add End

            return SHPG_PLN_DPLY_STS.PENDING_FULFILLMENT;
        // 2017/06/17 Del Start
        //} else if (ZYPConstant.FLG_ON_Y.equals(refLineData.get("PENDING_INSTALLATION_FLG"))) {
        //    return SHPG_PLN_DPLY_STS.PENDING_INSTALLATION;
        //} else if (ZYPConstant.FLG_ON_Y.equals(refLineData.get("ON_LOAN_FLG"))) {
        //    return SHPG_PLN_DPLY_STS.ON_LOAN;
        // 2017/06/17 Del End
        }
}

    /**
     * <pre>
     * getPrntShpgPlnDplySts
     * </pre>
     * @param prntShpgPln Map<String, Object>
     * @param inMsg NWZC188001PMsg
     */
    private String getPrntShpgPlnDplySts(Map<String, Object> prntShpgPln, NWZC188001PMsg inMsg) {

        String shpgPlnNum = (String) prntShpgPln.get(SHPG_PLN_NUM);
        String trxHdrNum = (String) prntShpgPln.get(TRX_HDR_NUM);
        String trxLineNum = (String) prntShpgPln.get(TRX_LINE_NUM);
        String trxLineSubNum = (String) prntShpgPln.get(TRX_LINE_SUB_NUM);
        String shpgStsCd = (String) prntShpgPln.get(SHPG_STS_CD);
        String poReqFlg = (String) prntShpgPln.get(PO_REQ_FLG);
        String openFlg = (String) prntShpgPln.get(OPEN_FLG);
        String revRecogMethCd = (String) prntShpgPln.get(REV_RECOG_METH_CD);
        BigDecimal strLoanBalQty = (BigDecimal) prntShpgPln.get(LOAN_BAL_QTY);
        if (strLoanBalQty == null) {
            strLoanBalQty = new BigDecimal(0);
        }
        Integer loanBalQty = strLoanBalQty.intValue();
        String cpoHldFlg = (String) prntShpgPln.get(CPO_HLD_FLG);
        String cpoDtlHldFlg = (String) prntShpgPln.get(CPO_DTL_HLD_FLG);
        BigDecimal strSlsHldQty = (BigDecimal) prntShpgPln.get(SLS_HLD_QTY);
        if (strSlsHldQty == null) {
            strSlsHldQty = new BigDecimal(0);
        }
        Integer slsHldQty = strSlsHldQty.intValue();
        String ordLineStsCd = (String) prntShpgPln.get(ORD_LINE_STS_CD);
        String outOfWhInvtyProcFlg = (String) prntShpgPln.get(OUT_OF_WH_INVTY_PROC_FLG);
        String invCratProcFlg = (String) prntShpgPln.get(INV_CRAT_PROC_FLG);
        String svcIstlReqFlg = (String) prntShpgPln.get(SVC_ISTL_REQ_FLG);
        String ordBookFlg = (String) prntShpgPln.get(ORD_BOOK_FLG);

        String prntShpgPlnDplyStsCd = "";
        if (SHPG_STS.CANCELLED.equals(shpgStsCd) || ORD_LINE_STS.CANCELLED.equals(ordLineStsCd)) {

            prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CANCELLED;
        // 2019/03/25 QC#30923 Add End
        } else if (getHldAtChild(inMsg, shpgPlnNum, HLD_RSN.S_OR_O_CANCEL)) {

            prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.SO_CANCELLED;
        } else if (getHldAtChild(inMsg, shpgPlnNum, HLD_RSN.PURCHASE_ORDER_CANCELL)) {

            prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PO_CANCELLED;
        // 2019/03/25 QC#30923 Add End
        } else if (ZYPConstant.FLG_OFF_N.equals(ordBookFlg)) {
            prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.ENTERED;
        } else if (SHPG_STS.VALIDATED.equals(shpgStsCd)) {
            if (ZYPConstant.FLG_ON_Y.equals(poReqFlg)) {

                prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.WAITING_RECEIPT;
            } else {

                Map<String, Object> shpgStsValidatedParam = new HashMap<String, Object>();
                shpgStsValidatedParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
                shpgStsValidatedParam.put("shpgPlnNum", shpgPlnNum);
                shpgStsValidatedParam.put("trxHdrNum", trxHdrNum);
                shpgStsValidatedParam.put("trxLineNum", trxLineNum);
                shpgStsValidatedParam.put("trxLineSubNum", trxLineSubNum);
                shpgStsValidatedParam.put("shpgStsValidated", SHPG_STS.VALIDATED);
                shpgStsValidatedParam.put("invtyLocCdBo", INVTY_LOC_CD_BO);
                shpgStsValidatedParam.put("invtyLocCdCr", INVTY_LOC_CD_CR);
                shpgStsValidatedParam.put("invtyLocCdLo%", INVTY_LOC_CD_LO + PCT);

                Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getDplyStsForShpgStsValidated", shpgStsValidatedParam);

                if (result != null && result.size() > 0) {
                    if (ZYPConstant.FLG_ON_Y.equals(result.get(PENDING_INVOICE_FLG))) {
                        prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INVOICE;
                    } else if (ZYPConstant.FLG_ON_Y.equals(result.get(BACK_ORDER_FLG))) {
                        prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.BACK_ORDER;
                    } else {
                        prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_ALLOCATION;
                    }
                }
            }
        // QC#18825 Add Start
        } else if (SHPG_STS.HARD_ALLOCATED.equals(shpgStsCd)) {
            prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.WAITING_PICK;
        // QC#18825 Add End
        } else if (SHPG_STS.S_OR_O_PRINTED.equals(shpgStsCd)) {
            if (ZYPConstant.FLG_ON_Y.equals(poReqFlg)) {

                Map<String, Object> shpgStsSOPrintedParam = new HashMap<String, Object>();
                shpgStsSOPrintedParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
                shpgStsSOPrintedParam.put("shpgPlnNum", shpgPlnNum);
                shpgStsSOPrintedParam.put("trxHdrNum", trxHdrNum);
                shpgStsSOPrintedParam.put("trxLineNum", trxLineNum);
                shpgStsSOPrintedParam.put("trxLineSubNum", trxLineSubNum);
                shpgStsSOPrintedParam.put("shpgStsSOPrinted", SHPG_STS.P_OR_O_PRINTED);

                Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getDplyStsForShpgStsSOPrinted", shpgStsSOPrintedParam);

                if (result != null && result.size() > 0) {
                    if (ZYPConstant.FLG_ON_Y.equals(result.get(WAITING_RECEIPT_FLG))) {
                        prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.WAITING_RECEIPT;
                    } else {
                        prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.WAITING_PICK;
                    }

                }

            } else {

                Map<String, Object> shpgStsSOPrintedParam2 = new HashMap<String, Object>();
                shpgStsSOPrintedParam2.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
                shpgStsSOPrintedParam2.put("shpgPlnNum", shpgPlnNum);
                shpgStsSOPrintedParam2.put("trxHdrNum", trxHdrNum);
                shpgStsSOPrintedParam2.put("trxLineNum", trxLineNum);
                shpgStsSOPrintedParam2.put("trxLineSubNum", trxLineSubNum);
                shpgStsSOPrintedParam2.put("shpgStsStaged", SHPG_STS.STAGED);
                shpgStsSOPrintedParam2.put("shpgStsPicked", SHPG_STS.PICKED);
                shpgStsSOPrintedParam2.put("pickConfirmed", DS_SO_LINE_STS.PICK_CONFIRMED);
                shpgStsSOPrintedParam2.put("inAssembryShop", DS_SO_LINE_STS.IN_ASSEMBLY_SHOP);
                shpgStsSOPrintedParam2.put("techAssigned", DS_SO_LINE_STS.TECH_ASSIGNED);

                Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getDplyStsForShpgStsSOPrinted2", shpgStsSOPrintedParam2);

                if (result != null && result.size() > 0) {
                    if (ZYPConstant.FLG_ON_Y.equals(result.get(PENDING_SHIP_FLG))) {
                        prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_SHIP;
                    } else if (ZYPConstant.FLG_ON_Y.equals(result.get(DELIVERED_TO_SHOP_FLG))) {
                        prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.DELIVERED_TO_SHOP;
                    } else if (ZYPConstant.FLG_ON_Y.equals(result.get(IN_SHOP_CONFIG_FLG))) {
                        prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.IN_SHOP_OR_CONFIG;
                    } else {
                        prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.WAITING_PICK;
                    }
                }
            }
        } else if (SHPG_STS.SHIPPED.equals(shpgStsCd)) {

            // 2017/12/08 QC#22930 Add Start
            String ittHldRsnCds = ZYPCodeDataUtil.getVarCharConstValue(NWZC1880_PEND_DLR_ISTL_HLD_CD, inMsg.glblCmpyCd.getValue());
            if (ittHldRsnCds == null || ittHldRsnCds.length() == 0) {
                ittHldRsnCds = HLD_RSN.ITT_OUTBOUND_HOLD;
            }

            String[] ittHldRsnCdArray = ittHldRsnCds.split(",");
            List<String> ittHldRsnCdList = new ArrayList<String>(0);
            for (int i = 0; i < ittHldRsnCdArray.length; i++) {
                ittHldRsnCdList.add(ittHldRsnCdArray[i]);
            }
            // 2017/12/08 QC#22930 Add End

            Map<String, Object> shpgStsShippedParam = new HashMap<String, Object>();
            shpgStsShippedParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
            shpgStsShippedParam.put("shpgPlnNum", shpgPlnNum);
            shpgStsShippedParam.put("trxHdrNum", trxHdrNum);
            shpgStsShippedParam.put("trxLineNum", trxLineNum);
            shpgStsShippedParam.put("trxLineSubNum", trxLineSubNum);
            shpgStsShippedParam.put("shpgStsShipped", SHPG_STS.SHIPPED);
            shpgStsShippedParam.put("shpgStsArrived", SHPG_STS.ARRIVED);
            shpgStsShippedParam.put("shpgStsInstalled", SHPG_STS.INSTALLED);
            shpgStsShippedParam.put("revRecogMethCd", REV_RECOG_METH.BOL);
            // 2017/12/08 QC#22930 Add Start
            shpgStsShippedParam.put("ittHldRsnCdList", ittHldRsnCdList);
            shpgStsShippedParam.put("whTp01", WH_TP.COMMON);
            shpgStsShippedParam.put("whTp02", WH_TP.NON_W_OR_H);
            shpgStsShippedParam.put("whTp03", WH_TP.VENDOR);
            // 2017/12/08 QC#22930 Add End
            // START 2023/01/13 F.Fadriquela [QC#60973, ADD]
            shpgStsShippedParam.put("flgY", ZYPConstant.FLG_ON_Y);
            // END 2023/01/13 F.Fadriquela [QC#60973, ADD]

            Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getDplyStsForShipped", shpgStsShippedParam);

            if (result != null && result.size() > 0) {
                if (ZYPConstant.FLG_ON_Y.equals(result.get(SHIPPED_FLG))) {
                    prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.SHIPPED;
                } else if (ZYPConstant.FLG_ON_Y.equals(result.get(PEND_DELY_CONF_FLG))) {
                    prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_DELIVERY_CONFIRMATION;
                } else if (ZYPConstant.FLG_ON_Y.equals(result.get(PENDING_INSTALLATION_FLG))) {
                    prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INSTALLATION;
                } else if (ZYPConstant.FLG_ON_Y.equals(result.get(ON_LOAN_FLG))) {
                    prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.ON_LOAN;
                } else if (ZYPConstant.FLG_ON_Y.equals(result.get(CLOSED_FLG))) {
                    prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED;
                // 2017/12/08 QC#22930 Add Start
                } else if (ZYPConstant.FLG_ON_Y.equals(result.get(PENDING_DEALER_INSTALL_FLG))) {
                    prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_DEALER_INSTALL;
                } else if (ZYPConstant.FLG_ON_Y.equals(result.get(BILLING_HLD_FLG))) {
                    prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.BILLING_HOLD;
                // 2017/12/08 QC#22930 Add End
                } else if (ZYPConstant.FLG_ON_Y.equals(result.get(PENDING_INVOICE_FLG))) {
                    prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INVOICE;
                }
            }
        } else if (SHPG_STS.P_OR_O_PRINTED.equals(shpgStsCd)) {

            prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.WAITING_RECEIPT;
        // Add Start 2019/02/19 QC#30448
        } else if (SHPG_STS.S_OR_O_CANCELLED.equals(shpgStsCd)) {

            prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PO_CANCELLED;
        // Add End 2019/02/19 QC#30448
        } else if (SHPG_STS.ARRIVED.equals(shpgStsCd)) {
            if (ZYPConstant.FLG_OFF_N.equals(openFlg)) {
                if (ZYPConstant.FLG_ON_Y.equals(outOfWhInvtyProcFlg)) {
                    if (getCpoDtlConvOrd(inMsg, shpgPlnNum)) {
                        prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED_LOAN_SOLD;
                    } else if (getCpoRtrnDtlConvOrd(inMsg, shpgPlnNum)) {
                        prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED_LOAN_RETURN;
                    }
                } else {
                    prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED;
                }
            } else if (!REV_RECOG_METH.BOL.equals(revRecogMethCd) && (loanBalQty > 0 || ZYPConstant.FLG_ON_Y.equals(svcIstlReqFlg))) {

                prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INSTALLATION;
            } else if (loanBalQty > 0 && REV_RECOG_METH.BOL.equals(revRecogMethCd) && ZYPConstant.FLG_ON_Y.equals(invCratProcFlg)) {

                prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.ON_LOAN;
            } else if (ZYPConstant.FLG_OFF_N.equals(cpoHldFlg) && ZYPConstant.FLG_OFF_N.equals(cpoDtlHldFlg) && slsHldQty == 0) {
                if (ZYPConstant.FLG_ON_Y.equals(invCratProcFlg)) {

                    prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED;
                } else {

                    prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INVOICE;
                }
            } else if (ZYPConstant.FLG_ON_Y.equals(cpoHldFlg) || ZYPConstant.FLG_ON_Y.equals(cpoDtlHldFlg) || slsHldQty > 0) {
                // 2017/07/31 QC#15383 Mod Start
                // 2017/09/27 QC#21167 Mod Start
//                if (getHldProcDfn(inMsg, shpgPlnNum)) {
                String bllgHldTp = getHldProcDfn(inMsg, shpgPlnNum);
                boolean isBllgHld = hasBllgHold(bllgHldTp);
                boolean isIttHld = isIttHld(bllgHldTp);
                if (isBllgHld) { // QC#21167 Mod End
                    prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.BILLING_HOLD;
                    if (isIttHld) {
                        prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_DEALER_INSTALL;
                    }
                } else {
                    prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INVOICE;
                }
                // 2017/07/31 QC#15838 Mod End
             
            }

        } else if (SHPG_STS.INSTALLED.equals(shpgStsCd)) {

            if (ZYPConstant.FLG_OFF_N.equals(openFlg)) {
                if (ZYPConstant.FLG_ON_Y.equals(outOfWhInvtyProcFlg)) {
                    if (getCpoDtlConvOrd(inMsg, shpgPlnNum)) {
                        prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED_LOAN_SOLD;
                    } else if (getCpoRtrnDtlConvOrd(inMsg, shpgPlnNum)) {
                        prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED_LOAN_RETURN;
                    }

                } else {
                    prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED;
                }
            } else if (loanBalQty > 0) {
                prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.ON_LOAN;
            } else if (ZYPConstant.FLG_OFF_N.equals(cpoHldFlg) && ZYPConstant.FLG_OFF_N.equals(cpoDtlHldFlg) && slsHldQty == 0) {
                if (ZYPConstant.FLG_OFF_N.equals(invCratProcFlg)) {
                    prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED;
                } else {
                    prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INVOICE;
                }
            } else if (ZYPConstant.FLG_ON_Y.equals(cpoHldFlg) || ZYPConstant.FLG_ON_Y.equals(cpoDtlHldFlg) || slsHldQty > 0) {
                // 2017/07/31 QC#15838 Mod Start
                // 2017/09/27 QC#21167 Mod Start
//                if (getHldProcDfn(inMsg, shpgPlnNum)) {
                String bllgHldTp = getHldProcDfn(inMsg, shpgPlnNum);
                boolean isBllgHld = hasBllgHold(bllgHldTp);
                boolean isIttHld = isIttHld(bllgHldTp);
                if (isBllgHld) { //  QC#21167 Mod End
                    prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.BILLING_HOLD;
                    // 2017/09/27 QC#21167 Add Start
                    if (isIttHld) {
                        prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_DEALER_INSTALL;
                    }
                    // 2017/09/27 QC#21167 Add End
                } else {
                    prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INVOICE;
                }
                // 2017/07/31 QC#15838 Mod End
            }
        } else if (SHPG_STS.INVOICED.equals(shpgStsCd)) {
            if (!getFnlzInvFlg(inMsg, shpgPlnNum)) {

                prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.PENDING_INVOICE;
            } else {

                prntShpgPlnDplyStsCd = SHPG_PLN_DPLY_STS.CLOSED;
            }
        } else {

            prntShpgPlnDplyStsCd = "";
        }

        return prntShpgPlnDplyStsCd;
    }

    /**
     * <pre>
     * getCpoDtlDplySts
     * </pre>
     * @param changeShpgPln Map<String, Object>
     * @param result List<Map<String, Object>>
     */
    private String getCpoDtlDplySts(Map<String, Object> changeShpgPln, List<Map<String, Object>> result) {

        int shipQty = 0;
        int invQty = 0;
        int allocQty = 0;
        int ordQty = 0;

        String cpoDtlDplyStsCd = "";
        if (result != null && result.size() > 1) {
            for (Map<String, Object> cpoDtlShpgPln : result) {
                String shpgStsCd = (String) cpoDtlShpgPln.get("SHPG_STS_CD");
                if (SHPG_STS.SHIPPED.equals(cpoDtlShpgPln.get("SHPG_STS_CD"))) {
                    BigDecimal wkQty = (BigDecimal) cpoDtlShpgPln.get("SHPG_PLN_ORD_QTY");
                    if (wkQty == null) {
                        wkQty = new BigDecimal(0);
                    }
                    shipQty += wkQty.intValue();
                }
                if (SHPG_STS.INVOICED.equals(cpoDtlShpgPln.get("SHPG_STS_CD"))) {
                    BigDecimal wkQty = (BigDecimal) cpoDtlShpgPln.get("SHPG_PLN_ORD_QTY");
                    if (wkQty == null) {
                        wkQty = new BigDecimal(0);
                    }
                    invQty += wkQty.intValue();
                }
                if (SHPG_STS.HARD_ALLOCATED.equals(shpgStsCd)
                    || SHPG_STS.S_OR_O_PRINTED.equals(shpgStsCd)
                    || SHPG_STS.PICKED.equals(shpgStsCd)
                    || SHPG_STS.PACKED.equals(shpgStsCd)
                    || SHPG_STS.STAGED.equals(shpgStsCd)) {
                    BigDecimal wkQty = (BigDecimal) cpoDtlShpgPln.get("SHPG_PLN_ORD_QTY");
                    if (wkQty == null) {
                        wkQty = new BigDecimal(0);
                    }
                    allocQty += wkQty.intValue();
                }
            }
            BigDecimal wkOrdQty = (BigDecimal) result.get(0).get("ORD_DTL_ORD_QTY");
            if (wkOrdQty == null) {
                wkOrdQty = new BigDecimal(0);
            }
            ordQty = wkOrdQty.intValue();

            if ((shipQty + invQty) > 0 && ordQty > (shipQty + invQty)) {
                cpoDtlDplyStsCd = ORD_LINE_DPLY_STS.PARTIALLY_SHIPPED;
            } else if (allocQty > 0 && ordQty > allocQty) {
                cpoDtlDplyStsCd = ORD_LINE_DPLY_STS.PARTIALLY_ALLOCATED;
            } else {
                cpoDtlDplyStsCd = (String) changeShpgPln.get("SHPG_PLN_DPLY_STS_CD");
            }
        } else {
            cpoDtlDplyStsCd = (String) changeShpgPln.get("SHPG_PLN_DPLY_STS_CD");
        }

        return cpoDtlDplyStsCd;
    }

    /**
     * <pre>
     * getRtrnLineDplySts
     * </pre>
     * @param rtrnLineMap Map<String, Object>
     * @param inMsg NWZC188001PMsg
     */
    private String getRtrnLineDplySts(Map<String, Object> rtrnLineMap, NWZC188001PMsg inMsg) {

        String cpoOrdNum = (String) rtrnLineMap.get(CPO_ORD_NUM);
        String dsCpoRtrnLineNum = (String) rtrnLineMap.get(DS_CPO_RTRN_LINE_NUM);
        String dsCpoRtrnLineSubNum = (String) rtrnLineMap.get(DS_CPO_RTRN_LINE_SUB_NUM);
        String openFlg = (String) rtrnLineMap.get(OPEN_FLG);
        String rtrnLineStsCd = (String) rtrnLineMap.get(RTRN_LINE_STS_CD);

        String rtrnLineDplyStsCd = "";
        if (ZYPConstant.FLG_OFF_N.equals(openFlg)) {
            if (RTRN_LINE_STS.CLOSED.equals(rtrnLineStsCd)) {
                if (getRtrnFnlzInvFlg(inMsg, cpoOrdNum, dsCpoRtrnLineNum, dsCpoRtrnLineSubNum)) {
                    rtrnLineDplyStsCd = RTRN_LINE_DPLY_STS.PENDING_INVOICE;
                } else {
                    rtrnLineDplyStsCd = RTRN_LINE_DPLY_STS.CLOSED;
                }
            } else if (RTRN_LINE_STS.CANCELLED.equals(rtrnLineStsCd)) {
                rtrnLineDplyStsCd = RTRN_LINE_DPLY_STS.CANCELLED;
            }
        } else {
            if (RTRN_LINE_STS.ENTERED.equals(rtrnLineStsCd)) {
                rtrnLineDplyStsCd = RTRN_LINE_DPLY_STS.ENTERED;
            } else if (RTRN_LINE_STS.BOOKED.equals(rtrnLineStsCd)) {
                // QC#22126 2017/11/14 Mod Start
                // rtrnLineDplyStsCd = RTRN_LINE_DPLY_STS.BOOKED;
                rtrnLineDplyStsCd = RTRN_LINE_DPLY_STS.PENDING_RETURN;
                // QC#22126 2017/11/14 Mod End
            } else if (RTRN_LINE_STS.RWS_CREATED.equals(rtrnLineStsCd)) {
                rtrnLineDplyStsCd = RTRN_LINE_DPLY_STS.PENDING_RETURN;
            } else if (RTRN_LINE_STS.RWS_CANCELLED.equals(rtrnLineStsCd)) {
                rtrnLineDplyStsCd = RTRN_LINE_DPLY_STS.RWS_CANCELLED;
            } else if (RTRN_LINE_STS.PARTIALLY_RECEIVED.equals(rtrnLineStsCd)) {
                rtrnLineDplyStsCd = RTRN_LINE_DPLY_STS.PARTIAL_RECEIVED;
            } else if (RTRN_LINE_STS.RECEIVED.equals(rtrnLineStsCd)) {

                Map<String, Object> rwsParam = new HashMap<String, Object>();

                rwsParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
                rwsParam.put("cpoOrdNum", cpoOrdNum);
                rwsParam.put("dsCpoRtrnLineNum", dsCpoRtrnLineNum);
                rwsParam.put("dsCpoRtrnLineSubNum", dsCpoRtrnLineSubNum);
                rwsParam.put("rwsStsPrinted", RWS_STS.PRINTED);
                rwsParam.put("rwsStsReceiving", RWS_STS.RECEIVING);
                rwsParam.put("rwsStsReceived", RWS_STS.RECEIVED);

                Map<String, Object> resultForRws = (Map<String, Object>) ssmBatchClient.queryObject("getRws", rwsParam);

                if (resultForRws != null && resultForRws.size() > 0) {
                    if (ZYPConstant.FLG_ON_Y.equals(resultForRws.get(PENDING_INSPECTION_FLG))) {
                        rtrnLineDplyStsCd = RTRN_LINE_DPLY_STS.PENDING_INSPECTION;
                    } else if (ZYPConstant.FLG_ON_Y.equals(resultForRws.get(BILLING_HLD_FLG))) {
                        rtrnLineDplyStsCd = RTRN_LINE_DPLY_STS.BILLING_HOLD;
                    } else if (ZYPConstant.FLG_ON_Y.equals(resultForRws.get(CLOSED_FLG))) {
                        rtrnLineDplyStsCd = RTRN_LINE_DPLY_STS.CLOSED;
                    } else if (ZYPConstant.FLG_ON_Y.equals(resultForRws.get(PENDING_INVOICE_FLG))) {
                        rtrnLineDplyStsCd = RTRN_LINE_DPLY_STS.PENDING_INVOICE;
                    }
                }
            }
        }

        return rtrnLineDplyStsCd;
    }

    /**
     * <pre>
     * getOrdHdrDplySts
     * </pre>
     * @param cpoTMsg CPOTMsg
     * @param inMsg NWZC188001PMsg
     */
    private String getOrdHdrDplySts(CPOTMsg cpoTMsg, NWZC188001PMsg inMsg) {

        String openFlg = cpoTMsg.openFlg.getValue();
        String ordHdrStsCd = cpoTMsg.ordHdrStsCd.getValue();
        String submtFlg = cpoTMsg.submtFlg.getValue();
        String diCheckFlg = cpoTMsg.diChkHldFlg.getValue();
        String ordBookReqTs = cpoTMsg.ordBookReqTs.getValue();

        String ordHdrDplyStsCd = "";
        if (ZYPConstant.FLG_OFF_N.equals(openFlg)) {
            if (ORD_HDR_STS.CLOSED.equals(ordHdrStsCd)) {

                List<Map<String, Object>> fnlzInvFlgList = new ArrayList<Map<String, Object>>();

                Map<String, Object> fnlzInvFlgListParam = new HashMap<String, Object>();
                fnlzInvFlgListParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
                fnlzInvFlgListParam.put("cpoOrdNum", inMsg.cpoOrdNum.getValue());
                fnlzInvFlgListParam.put("sysSrcCd", SYS_SRC_CD); // QC#19387 Add

                fnlzInvFlgList = ssmBatchClient.queryObjectList("getfnlzInvFlgList", fnlzInvFlgListParam);

                if (fnlzInvFlgList != null && fnlzInvFlgList.size() > 0) {

                    ordHdrDplyStsCd = ORD_HDR_DPLY_STS.BOOKED;
                } else {

                    ordHdrDplyStsCd = ORD_HDR_DPLY_STS.CLOSED;
                }
            } else if (ORD_HDR_STS.CANCELLED.equals(ordHdrStsCd)) {

                ordHdrDplyStsCd = ORD_HDR_DPLY_STS.CANCELLED;
            }
        } else {

            if (ZYPConstant.FLG_OFF_N.equals(submtFlg) && (ZYPConstant.FLG_OFF_N.equals(diCheckFlg) || !ZYPCommonFunc.hasValue(ordBookReqTs))) {

                ordHdrDplyStsCd = ORD_HDR_DPLY_STS.ENTERED;
            } else if (ZYPConstant.FLG_ON_Y.equals(diCheckFlg)) {

                ordHdrDplyStsCd = ORD_HDR_DPLY_STS.DI_CHECK_HOLD;
            } else {

                Map<String, Object> ordHdrStsParam = new HashMap<String, Object>();
                ordHdrStsParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
                ordHdrStsParam.put("cpoOrdNum", inMsg.cpoOrdNum.getValue());
                ordHdrStsParam.put("hldRsnValHld", HLD_RSN.VALIDATION_HOLD);
                ordHdrStsParam.put("hldRsnSplyYieldValHld", HLD_RSN.SUPPLY_YIELD_VALIDATION_HOLD);
                ordHdrStsParam.put("hldRsnPendOrdHld", HLD_RSN.PENDING_ORDER_HOLD);
                ordHdrStsParam.put("hldRsnContrStsHld", HLD_RSN.CONTRACT_STATUS_HOLD);
                ordHdrStsParam.put("hldRsnSplyEnfoHld", HLD_RSN.SUPPLY_ENFORCEMENT_HOLD);
                ordHdrStsParam.put("hldRsnProftHld", HLD_RSN.PROFITABILITY_HOLD);
                // add start 2023/04/25 QC#61337
                ordHdrStsParam.put("hldRsnManPrcHld", HLD_RSN.MANUAL_PRICE);
                // add end 2023/04/25 QC#61337

                Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getOrdHdrDplySts", ordHdrStsParam);

                if (result != null && result.size() > 0) {
                    if (ZYPConstant.FLG_ON_Y.equals(result.get(VALIDATION_HLD_FLG))) {
                        ordHdrDplyStsCd = ORD_HDR_DPLY_STS.VALIDATION_HOLD;
                    } else if (ZYPConstant.FLG_ON_Y.equals(result.get(SUPPLY_ABUSE_HOLD_FLG))) {
                        ordHdrDplyStsCd = ORD_HDR_DPLY_STS.SUPPLY_ABUSE_HOLD;
                    } else if (ZYPConstant.FLG_ON_Y.equals(result.get(PROFITABILITY_HOLD_FLG))) {
                        ordHdrDplyStsCd = ORD_HDR_DPLY_STS.PROFITABILITY_HOLD;
                    // add start 2023/04/25 QC#61337
                    } else if (ZYPConstant.FLG_ON_Y.equals(result.get(MAN_PRC_HOLD_FLG))) {
                        ordHdrDplyStsCd = ORD_HDR_DPLY_STS.PROFITABILITY_HOLD;
                    // add end 2023/04/25 QC#61337
                    } else if (ZYPConstant.FLG_ON_Y.equals(result.get(CREDIT_HOLD_FLG))) {
                        ordHdrDplyStsCd = ORD_HDR_DPLY_STS.CREDIT_HOLD;
                    } else if (ZYPConstant.FLG_ON_Y.equals(result.get(PENDING_RE_SUBMISSION_FLG))) {
                        ordHdrDplyStsCd = ORD_HDR_DPLY_STS.PENDING_RE_SUBMISSION;
                    } else if (ZYPConstant.FLG_ON_Y.equals(result.get(BOOKED_FLG))) {
                        ordHdrDplyStsCd = ORD_HDR_DPLY_STS.BOOKED;
                    } else {
                        ordHdrDplyStsCd = "";
                    }
                }
            }
        }

        return ordHdrDplyStsCd;
    }

    /**
     * <pre>
     * get Hold Info
     * </pre>
     */
    private boolean getHld(NWZC188001PMsg inMsg, String shpgPlnNum, String hldRsnCd) {

        Map<String, Object> hldParam = new HashMap<String, Object>();
        hldParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        hldParam.put("hldRsnCd", hldRsnCd);
        hldParam.put("shpgPlnNum", shpgPlnNum);

        Map<String, Object> resultForHld = (Map<String, Object>) ssmBatchClient.queryObject("getHld", hldParam);

        if (resultForHld != null && resultForHld.size() > 0) {
            return true;
        }
        return false;
    }

    // 2019/03/25 QC#30923 Add Start
    /**
     * <pre>
     * get Hold Info At Child
     * </pre>
     */
    private boolean getHldAtChild(NWZC188001PMsg inMsg, String shpgPlnNum, String hldRsnCd) {

        Map<String, Object> hldParam = new HashMap<String, Object>();
        hldParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        hldParam.put("hldRsnCd", hldRsnCd);
        hldParam.put("shpgPlnNum", shpgPlnNum);

        Map<String, Object> resultForHld = (Map<String, Object>) ssmBatchClient.queryObject("getHldAtChild", hldParam);

        if (resultForHld != null && resultForHld.size() > 0) {
            return true;
        }
        return false;
    }
    // 2019/03/25 QC#30923 Add End

    // 2017/07/31 QC#15838 Add Start
    /**
     * <pre>
     * getHldProcDfnInfo
     * </pre>
     */
//    private boolean getHldProcDfn(NWZC188001PMsg inMsg, String shpgPlnNum) { 2017/09/27 QC#21167 Mod Interface
    private String getHldProcDfn(NWZC188001PMsg inMsg, String shpgPlnNum) {

        Map<String, Object> hldProcDfnParam = new HashMap<String, Object>();
        hldProcDfnParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        hldProcDfnParam.put("shpgPlnNum", shpgPlnNum);
        hldProcDfnParam.put("whTp01", WH_TP.COMMON);
        hldProcDfnParam.put("whTp02", WH_TP.NON_W_OR_H);
        hldProcDfnParam.put("whTp03", WH_TP.VENDOR);
        hldProcDfnParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
        hldProcDfnParam.put("flgOffN", ZYPConstant.FLG_OFF_N);
        // 2019/11/11 QC#54200 Del Start
        // 2019/01/26 QC#29932 Add Start
        //hldProcDfnParam.put("exceptHldRsn", HLD_RSN.ARRIVED_WAITING_FOR_INSTALLATION);
        // 2019/01/26 QC#29932 Add Start
        // 2019/11/11 QC#54200 Del End

        // 2017/09/27 QC#21167 Mod Start
//        Map<String, Object> resultForHldProcDfn = (Map<String, Object>) ssmBatchClient.queryObject("getHldProcDfn", hldProcDfnParam);
//
//        if (resultForHldProcDfn != null && resultForHldProcDfn.size() > 0) {
//            return true;
//        }
//        return false;

        List<Map<String, Object>> resultForHldProcDfnMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getHldProcDfn", hldProcDfnParam);
        if (resultForHldProcDfnMapList == null || resultForHldProcDfnMapList.isEmpty()) {
            return BLLG_HLD_NO;
        }

        String ittHldRsnCds = ZYPCodeDataUtil.getVarCharConstValue(NWZC1880_PEND_DLR_ISTL_HLD_CD, inMsg.glblCmpyCd.getValue());
        if (ittHldRsnCds == null || ittHldRsnCds.length() == 0) {
            ittHldRsnCds = HLD_RSN.ITT_OUTBOUND_HOLD;
        }
        String[] ittHldRsnCdArray = ittHldRsnCds.split(",");
        List<String> ittHldRsnCdList = new ArrayList<String>(0);
        for (int i = 0; i < ittHldRsnCdArray.length; i++) {
            ittHldRsnCdList.add(ittHldRsnCdArray[i]);
        }

        String rtrnStr = BLLG_HLD_ITT_HLD;
        for (Map<String, Object> resultForHldProcDfnMap : resultForHldProcDfnMapList) {
            String hldRsnCd = (String) resultForHldProcDfnMap.get("HLD_RSN_CD");
            if (!ittHldRsnCdList.contains(hldRsnCd)) {
                return BLLG_HLD_BLLG_HLD;
            }
        }
        return rtrnStr;
        // 2017/09/27 QC#21167 Mod End
    }
    // 2017/07/31 QC#15838 Add End

    /**
     * <pre>
     * get Hard Allocation Bat Work
     * </pre>
     */
    private boolean getHardAllocBatWrk(NWZC188001PMsg inMsg, String shpgPlnNum) {

        Map<String, Object> hrdAllocBatWrkParam = new HashMap<String, Object>();
        hrdAllocBatWrkParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        hrdAllocBatWrkParam.put("shpgPlnNum", shpgPlnNum);

        Map<String, Object> resultForHrdAllocBatWrk = (Map<String, Object>) ssmBatchClient.queryObject("getHardAllocBatWrk", hrdAllocBatWrkParam);

        if (resultForHrdAllocBatWrk != null && resultForHrdAllocBatWrk.size() > 0) {
            if (ZYPConstant.FLG_ON_Y.equals(resultForHrdAllocBatWrk.get(BACK_ORDER_FLG))) {
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     * get Shipping Order
     * </pre>
     */
    private Map<String, Object> getShpgOrd(NWZC188001PMsg inMsg, String shpgPlnNum) {

        Map<String, Object> shpgOrdParam = new HashMap<String, Object>();
        shpgOrdParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        shpgOrdParam.put("shpgPlnNum", shpgPlnNum);
        shpgOrdParam.put("pickConfirmed", DS_SO_LINE_STS.PICK_CONFIRMED);
        shpgOrdParam.put("inAssemblyShop", DS_SO_LINE_STS.IN_ASSEMBLY_SHOP);
        shpgOrdParam.put("techAssigned", DS_SO_LINE_STS.TECH_ASSIGNED);

        return (Map<String, Object>) ssmBatchClient.queryObject("getShpgOrd", shpgOrdParam);
    }

    /**
     * <pre>
     * get Cpo Detail Conversion Order
     * </pre>
     */
    private boolean getCpoDtlConvOrd(NWZC188001PMsg inMsg, String shpgPlnNum) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        param.put("shpgPlnNum", shpgPlnNum);

        Map<String, Object> cpoDtlConvOrdList = (Map<String, Object>) ssmBatchClient.queryObject("getcpoDtlConvOrd", param);

        if (cpoDtlConvOrdList != null && cpoDtlConvOrdList.size() > 0) {
            if (ZYPConstant.FLG_ON_Y.equals(cpoDtlConvOrdList.get(CLOSED_LOAN_SOLD_FLG))) {
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     * get Cpo Return Detail Conversion Order
     * </pre>
     */
    private boolean getCpoRtrnDtlConvOrd(NWZC188001PMsg inMsg, String shpgPlnNum) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        param.put("shpgPlnNum", shpgPlnNum);

        Map<String, Object> cpoRtrnDtlConvOrdList = (Map<String, Object>) ssmBatchClient.queryObject("getCpoRtrnDtlConvOrd", param);

        if (cpoRtrnDtlConvOrdList != null && cpoRtrnDtlConvOrdList.size() > 0) {
            if (ZYPConstant.FLG_ON_Y.equals(cpoRtrnDtlConvOrdList.get(CLOSED_LOAN_RETURN_FLG))) {
                return true;
            }
        }

        return false;
    }

    /**
     * <pre>
     * getfnlzInvFlg
     * </pre>
     */
    private boolean getFnlzInvFlg(NWZC188001PMsg inMsg, String shpgPlnNum) {

        Map<String, Object> fnlzInvParam = new HashMap<String, Object>();
        fnlzInvParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        fnlzInvParam.put("shpgPlnNum", shpgPlnNum);

        Map<String, Object> resultForFnlInvFlg = (Map<String, Object>) ssmBatchClient.queryObject("getFnlzInvFlg", fnlzInvParam);

        if (resultForFnlInvFlg != null && resultForFnlInvFlg.size() > 0) {
            if (ZYPConstant.FLG_ON_Y.equals(resultForFnlInvFlg.get(FNLZ_INV_FLG))) {
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     * getRtrnfnlzInvFlg
     * </pre>
     */
    private boolean getRtrnFnlzInvFlg(NWZC188001PMsg inMsg, String cpoOrdNum, String dsCpoRtrnLineNum, String dsCpoRtrnLineSubNum) {

        Map<String, Object> rtrnFnlzInvParam = new HashMap<String, Object>();
        rtrnFnlzInvParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        rtrnFnlzInvParam.put("cpoOrdNum", cpoOrdNum);
        rtrnFnlzInvParam.put("dsCpoRtrnLineNum", dsCpoRtrnLineNum);
        rtrnFnlzInvParam.put("dsCpoRtrnLineSubNum", dsCpoRtrnLineSubNum);

        Map<String, Object> resultForRtrnFnlInvFlg = (Map<String, Object>) ssmBatchClient.queryObject("getRtrnFnlzInvFlg", rtrnFnlzInvParam);

        if (resultForRtrnFnlInvFlg != null && resultForRtrnFnlInvFlg.size() > 0) {
            if (ZYPConstant.FLG_ON_Y.equals(resultForRtrnFnlInvFlg.get(FNLZ_INV_FLG))) {
                return true;
            }
        }
        return false;
    }

    // 2017/09/27 QC#21167 Add Start
    private boolean hasBllgHold(String bllgHldTp) {

        if (BLLG_HLD_BLLG_HLD.equals(bllgHldTp)) {
            return true;
        }
        if (BLLG_HLD_ITT_HLD.equals(bllgHldTp)) {
            return true;
        }
        return false;
    }

    private boolean isIttHld(String bllgHldTp) {

        if (BLLG_HLD_ITT_HLD.equals(bllgHldTp)) {
            return true;
        }
        return false;
    }
    // 2017/09/27 QC#21167 Add End

    // 2018/12/18 QC#29488 Add Start 
    private MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {
        return NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
    }
    // 2018/12/18 QC#29488 Add End 

    // 2019/03/01 QC#30599 Add Start
    private String getMachineSts(NWZC188001PMsg inMsg, String shpgPlnNum) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
        param.put("shpgPlnNum", shpgPlnNum);

        Map<String, Object> resultGetMachineSts = (Map<String, Object>) ssmBatchClient.queryObject("getMachineSts", param);

        if (resultGetMachineSts != null && resultGetMachineSts.size() > 0) {
            return (String) resultGetMachineSts.get(SVC_MACH_MSTR_STS_CD);
        }
        return null;
    }
    // 2019/03/01 QC#30599 Add End
}
