/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC211001;

import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.FORMAT_YYYYMMDD;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MACH_MSTR_STATUS_CHECK_COLUMN;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHECK_ALLOCATION;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHECK_CONFIG;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHECK_EXIST_SERIAL;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHECK_LOCATION;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHECK_MM_STATUS_CRAT;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHECK_MM_STATUS_TRMN;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHECK_ORDER_CONFIG;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHECK_OTHER_ALLOCATION;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHECK_SHIP_SERIAL;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHECK_STK_STS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_CONFIG_ITEM_OTHER_WH;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_CONFIG_ITEM_OTHER_WH_NS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_CONFIG_ITEM_OTHER_WH_NSS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_IN_CUST;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_IN_CUST_NS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_IN_CUST_NSS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_NO_IB;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_NO_IB_NS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_NO_IB_NSS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_ON_SCHD;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_OTHER_WH;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_OTHER_WH_CONFIG_ITEM;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_OTHER_WH_CONFIG_ITEM_NS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_OTHER_WH_CONFIG_ITEM_NSS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_OTHER_WH_NS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_OTHER_WH_NSS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_OTHR_ITEM_OTHR_WH;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_OTHR_ITEM_OTHR_WH_NS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_OTHR_ITEM_OTHR_WH_NSS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_OTHR_ORD;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_OTHR_ORD_NS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_OTHR_ORD_NSS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_WH;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_WH_ITEM_NSS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_WH_NS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_WH_NSS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_WH_OTHER_CONFIG;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_WH_OTHER_CONFIG_ITEM;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_WH_OTHER_CONFIG_ITEM_NS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_WH_OTHER_CONFIG_ITEM_NSS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_WH_OTHER_CONFIG_NS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_CHK_PTR_WH_OTHER_CONFIG_NSS;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.MM_NO_SHIP_TARGET;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.SERIAL_TRX_ERROR_DUPLICATE;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.SERIAL_TRX_ERROR_NOT_SCHEDULE;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.SERIAL_TRX_ERROR_STK_STS_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.SER_TRX_CMNT_NLBM1302W;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.SER_TRX_CMNT_NLZM2317E;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.SER_TRX_CMNT_NLZM2318E;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.SER_TRX_CMNT_NLZM2324E;
import static com.canon.cusa.s21.api.NLZ.NLZC211001.NLZC211001Constant.SER_TRX_CMNT_NLZM2414E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.MDSETMsg;
import business.db.SHIP_SER_NUM_WRKTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_CONF_DTLTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SO_SERTMsg;
import business.db.SO_SERTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.parts.NLZC309001PMsg;
import business.parts.NSZC001001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC309001.NLZC309001;
import com.canon.cusa.s21.api.NLZ.NLZC309001.constant.NLZC309001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Update SO Confirmation
 * Machine Master API Event
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/28/2015   CITS            T.Tokutomi      Create
 * 03/23/2016   CITS            T.Tokutomi      Update          QC#5905
 * 04/26/2016   CSAI            Y.Imazu         Update          QC#7598
 * 06/10/2016   CSAI            Y.Imazu         Update          QC#9772
 * 07/28/2016   CSAI            Y.Imazu         Update          QC#12545
 * 08/02/2016   CSAI            Y.Imazu         Update          QC#12667
 * 11/21/2016   CITS            R.Shimamoto     Update          QC#14286
 * 02/16/2017   CITS            K.Ogino         UPDATE          QC#17393
 * 07/04/2017   CITS            K.Ogino         UPDATE          QC#19685
 * 07/21/2017   CITS            K.Ogino         UPDATE          QC#20008
 * 07/10/2018   CITS            K.Ogino         Update          QC#26243
 * 08/10/2018   CITS            T.hakodate      Update          QC#26585
 * 10/06/2021   CITS            A.Marte         Update          QC#59155
 * 09/22/2023   Hitachi         T.Kuroda        Update          QC#61265
 *</pre>
 */
public class MachMstrApiEvent {

    /**
     * callMachineMasterUpdateForNonSerial
     * @param shpgOrdTMsg SHPG_ORDTMsg
     * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
     * @param shpgOrdConfDtlTMsg SHPG_ORD_CONF_DTLTMsg
     * @param shipQty BigDecimal
     * @param isAssetWh boolean
     * @param onBatch ONBATCH_TYPE
     * @param ssmBatchClient ssmBatchClient
     * @return errMsgList
     */
    // START 2021/10/06 A.Marte [QC#59155, MOD]
    //public static ArrayList<MachineMasterResultBean> callMachineMasterUpdateForNonSerial(SHPG_ORDTMsg shpgOrdTMsg, SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtlTMsg,
    //        BigDecimal shipQty, boolean isAssetWh, ONBATCH_TYPE onBatch) {
    public static ArrayList<MachineMasterResultBean> callMachineMasterUpdateForNonSerial(SHPG_ORDTMsg shpgOrdTMsg, SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtlTMsg,
            BigDecimal shipQty, boolean isAssetWh, ONBATCH_TYPE onBatch, S21SsmBatchClient ssmBatchClient) {
    // END 2021/10/06 A.Marte [QC#59155, MOD]

        // Get Machine Master info
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        svcMachMstrTMsg.setSQLID("010");
        svcMachMstrTMsg.setConditionValue("glblCmpyCd01", shpgOrdDtlTMsg.glblCmpyCd.getValue());
        // QC#19685 Start
        svcMachMstrTMsg.setConditionValue("mdseCd01", shpgOrdDtlTMsg.mdseCd.getValue());
        // QC#19685 End
        svcMachMstrTMsg.setConditionValue("trxHdrNum01", shpgOrdDtlTMsg.trxHdrNum.getValue());
        svcMachMstrTMsg.setConditionValue("trxLineNum01", shpgOrdDtlTMsg.trxLineNum.getValue());
        svcMachMstrTMsg.setConditionValue("trxLineSubNum01", shpgOrdDtlTMsg.trxLineSubNum.getValue());
        svcMachMstrTMsg.setConditionValue("svcMachMstrStsCd01A", SVC_MACH_MSTR_STS.CREATED);
        svcMachMstrTMsg.setConditionValue("svcMachMstrStsCd01B", SVC_MACH_MSTR_STS.REMOVED);

        SVC_MACH_MSTRTMsgArray allocSvcMachMstrTMsgArray = (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(svcMachMstrTMsg);
        // QC#26243 Start
        SVC_MACH_MSTRTMsgArray targetAllocSvcMachMstrTMsgArray = (SVC_MACH_MSTRTMsgArray) svcMachMstrTMsg.getNewInstance().getMyArray();
        List<SVC_MACH_MSTRTMsg> smmTMsgList = new ArrayList<SVC_MACH_MSTRTMsg>();

        for (int i = 0; i < allocSvcMachMstrTMsgArray.getValidCount(); i++) {

            SVC_MACH_MSTRTMsg allocSvcMachMstrTMsg = allocSvcMachMstrTMsgArray.no(i);
            if (LOC_STS.DC_STOCK.equals(allocSvcMachMstrTMsg.svcMachMstrLocStsCd.getValue())) {
                smmTMsgList.add(allocSvcMachMstrTMsg);
            }
        }

        SVC_MACH_MSTRTMsg[] array = smmTMsgList.toArray(new SVC_MACH_MSTRTMsg[smmTMsgList.size()]);
        targetAllocSvcMachMstrTMsgArray.setMsgList(array);
        targetAllocSvcMachMstrTMsgArray.setValidCount(array.length);

        String sceOrdTpCd = shpgOrdTMsg.sceOrdTpCd.getValue();
        allocSvcMachMstrTMsgArray = targetAllocSvcMachMstrTMsgArray;
        // QC#26243 End

        // 1st Update
        if (allocSvcMachMstrTMsgArray.getValidCount() > shipQty.intValue()) {

            allocSvcMachMstrTMsgArray.setValidCount(shipQty.intValue());
        }

        if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {

            ArrayList<NSZC001001PMsg> param = setMachMstrApiParamForUpdate(ProcessMode.TRANSFER_OUT.code, shpgOrdTMsg, allocSvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, null);

            param = callMachineMasterUpdateAPI(param, onBatch);
            ArrayList<MachineMasterResultBean> msg = convertPMsgError(param);

            if (!msg.isEmpty()) {

                return msg;
            }

        } else if (checkDiposalMachMstr(sceOrdTpCd)) {

            ArrayList<NSZC001001PMsg> param = setMachMstrApiParamForUpdate(ProcessMode.ALLOCATION_OFF.code, shpgOrdTMsg, allocSvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, null);

            // Allocation off
            param = callMachineMasterUpdateAPI(param, onBatch);
            ArrayList<MachineMasterResultBean> allocResultMsg = convertPMsgError(param);

            if (!allocResultMsg.isEmpty()) {

                return allocResultMsg;
            }

            // Dispose
            ArrayList<NSZC001001PMsg> disposeParam = setMachMstrApiParamForUpdate(ProcessMode.DISPOSAL.code, shpgOrdTMsg, allocSvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, null);
            disposeParam = callMachineMasterUpdateAPI(disposeParam, onBatch);

            ArrayList<MachineMasterResultBean> disposeResultMsg = convertPMsgError(disposeParam);

            if (!disposeResultMsg.isEmpty()) {

                return disposeResultMsg;
            }

            // Asset Staging for Disposal
            if (isAssetWh) {

                ArrayList<MachineMasterResultBean> assetStgngApiResultMsg = callAssetStgngApiForDspl(shpgOrdTMsg.glblCmpyCd.getValue(), allocSvcMachMstrTMsgArray, onBatch);

                if (!assetStgngApiResultMsg.isEmpty()) {

                    return assetStgngApiResultMsg;
                }
            }

        } else if (checkUpdateMachMstr(sceOrdTpCd)) {

            ArrayList<NSZC001001PMsg> param = setMachMstrApiParamForUpdate(ProcessMode.UPDATE_INVENTORY.code, shpgOrdTMsg, allocSvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, null);
            param = callMachineMasterUpdateAPI(param, onBatch);
            ArrayList<MachineMasterResultBean> updResultMsg = convertPMsgError(param);

            if (!updResultMsg.isEmpty()) {

                return updResultMsg;
            }
        }

        int rmnQty = shipQty.intValue() - allocSvcMachMstrTMsgArray.getValidCount();
        int newCratQty = 0;

        // 2nd Update
        if (rmnQty > 0) {

            svcMachMstrTMsg.clear();
            svcMachMstrTMsg.setSQLID("009");
            svcMachMstrTMsg.setConditionValue("glblCmpyCd01", shpgOrdDtlTMsg.glblCmpyCd.getValue());
            svcMachMstrTMsg.setConditionValue("mdseCd01", shpgOrdDtlTMsg.mdseCd.getValue());
            svcMachMstrTMsg.setConditionValue("curLocNum01", shpgOrdDtlTMsg.invtyLocCd.getValue());
            svcMachMstrTMsg.setConditionValue("svcMachMaintAvalFlg01", ZYPConstant.FLG_ON_Y);
            svcMachMstrTMsg.setConditionValue("svcMachMstrStsCd01A", SVC_MACH_MSTR_STS.CREATED);
            svcMachMstrTMsg.setConditionValue("svcMachMstrStsCd01B", SVC_MACH_MSTR_STS.REMOVED);

            SVC_MACH_MSTRTMsgArray nonAllocSvcMachMstrTMsgArray = (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(svcMachMstrTMsg);

            // START 2021/10/06 A.Marte [QC#59155, ADD]
            if (checkDiposalMachMstr(shpgOrdTMsg.sceOrdTpCd.getValue())) {

                HashMap<String, String> sqlParam = new HashMap<String, String>();
                sqlParam.put("glblCmpyCd", shpgOrdDtlTMsg.glblCmpyCd.getValue());
                sqlParam.put("mdseCd", shpgOrdDtlTMsg.mdseCd.getValue());
                sqlParam.put("curLocNum", shpgOrdDtlTMsg.invtyLocCd.getValue());
                sqlParam.put("CRAT", SVC_MACH_MSTR_STS.CREATED);
                sqlParam.put("RMV", SVC_MACH_MSTR_STS.REMOVED);
                sqlParam.put("svcMachMaintAvalFlg", ZYPConstant.FLG_ON_Y);
                sqlParam.put("stkStsCd", shpgOrdConfDtlTMsg.fromStkStsCd.getValue());

                List<SVC_MACH_MSTRTMsg> nonAllocSmmTMsgList = (List<SVC_MACH_MSTRTMsg>) ssmBatchClient.queryObjectList("getDisposalNonAllocSvcMachMstr", sqlParam);
                SVC_MACH_MSTRTMsg[] nonAllocDisposalArray = nonAllocSmmTMsgList.toArray(new SVC_MACH_MSTRTMsg[nonAllocSmmTMsgList.size()]);

                nonAllocSvcMachMstrTMsgArray = (SVC_MACH_MSTRTMsgArray) svcMachMstrTMsg.getNewInstance().getMyArray();
                nonAllocSvcMachMstrTMsgArray.setMsgList(nonAllocDisposalArray);
                nonAllocSvcMachMstrTMsgArray.setValidCount(nonAllocDisposalArray.length);

            }
            // END 2021/10/06 A.Marte [QC#59155, ADD]

            if (nonAllocSvcMachMstrTMsgArray.getValidCount() > rmnQty) {

                nonAllocSvcMachMstrTMsgArray.setValidCount(rmnQty);
            }

            if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {

                ArrayList<NSZC001001PMsg> param = setMachMstrApiParamForUpdate(ProcessMode.ALLOCATION_ON.code, shpgOrdTMsg, nonAllocSvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, null);

                param = callMachineMasterUpdateAPI(param, onBatch);
                ArrayList<MachineMasterResultBean> allocOnResultMsg = convertPMsgError(param);

                if (!allocOnResultMsg.isEmpty()) {

                    return allocOnResultMsg;
                }

                // Transfer out
                ArrayList<NSZC001001PMsg> trnsfParam = setMachMstrApiParamForUpdate(ProcessMode.TRANSFER_OUT.code, shpgOrdTMsg, nonAllocSvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, null);
                trnsfParam = callMachineMasterUpdateAPI(trnsfParam, onBatch);
                ArrayList<MachineMasterResultBean> trnsfResultMsg = convertPMsgError(trnsfParam);

                if (!trnsfResultMsg.isEmpty()) {

                    return trnsfResultMsg;
                }

            } else if (checkDiposalMachMstr(sceOrdTpCd)) {

                // Dispose
                ArrayList<NSZC001001PMsg> param = setMachMstrApiParamForUpdate(ProcessMode.DISPOSAL.code, shpgOrdTMsg, nonAllocSvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, null);
                param = callMachineMasterUpdateAPI(param, onBatch);
                ArrayList<MachineMasterResultBean> disposeResultMsg = convertPMsgError(param);

                if (!disposeResultMsg.isEmpty()) {

                    return disposeResultMsg;
                }

                // Asset Staging for Disposal
                if (isAssetWh) {

                    ArrayList<MachineMasterResultBean> assetStgngApiResultMsg = callAssetStgngApiForDspl(shpgOrdTMsg.glblCmpyCd.getValue(), nonAllocSvcMachMstrTMsgArray, onBatch);

                    if (!assetStgngApiResultMsg.isEmpty()) {

                        return assetStgngApiResultMsg;
                    }
                }

            } else if (checkUpdateMachMstr(sceOrdTpCd)) {

                ArrayList<NSZC001001PMsg> param = setMachMstrApiParamForUpdate(ProcessMode.ALLOCATION_ON.code, shpgOrdTMsg, nonAllocSvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, null);
                param = callMachineMasterUpdateAPI(param, onBatch);
                ArrayList<MachineMasterResultBean> allocOnResultMsg = convertPMsgError(param);

                if (!allocOnResultMsg.isEmpty()) {

                    return allocOnResultMsg;
                }

                // Update inventory
                ArrayList<NSZC001001PMsg> trnsfParam = setMachMstrApiParamForUpdate(ProcessMode.UPDATE_INVENTORY.code, shpgOrdTMsg, nonAllocSvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, null);
                trnsfParam = callMachineMasterUpdateAPI(trnsfParam, onBatch);
                ArrayList<MachineMasterResultBean> updResultMsg = convertPMsgError(trnsfParam);

                if (!updResultMsg.isEmpty()) {

                    return updResultMsg;
                }
            }

            newCratQty = rmnQty - nonAllocSvcMachMstrTMsgArray.getValidCount();
        }

        // 3rd Update
        if (newCratQty > 0 && !checkDiposalMachMstr(sceOrdTpCd)) {

            // Create Machine Master
            SVC_MACH_MSTRTMsg dummySvcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
            SVC_MACH_MSTRTMsgArray dummySvcMachMstrTMsgArray = (SVC_MACH_MSTRTMsgArray) dummySvcMachMstrTMsg.getNewInstance().getMyArray();
            dummySvcMachMstrTMsgArray.setMsgList(new SVC_MACH_MSTRTMsg[newCratQty]);
            dummySvcMachMstrTMsgArray.setValidCount(newCratQty);

            ArrayList<NSZC001001PMsg> insWhParam = setMachMstrApiParamForUpdate(ProcessMode.INSERT_WAREHOUSE.code, shpgOrdTMsg, dummySvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, null);

            insWhParam = callMachineMasterUpdateAPI(insWhParam, onBatch);
            ArrayList<MachineMasterResultBean> insWhResultMsg = convertPMsgError(insWhParam);

            if (!insWhResultMsg.isEmpty()) {

                return insWhResultMsg;
            }

            // Allocation
            SVC_MACH_MSTRTMsg[] cratSvcMachMstrArray = new SVC_MACH_MSTRTMsg[insWhParam.size()];

            for (int i = 0; i < insWhParam.size(); i++) {

                SVC_MACH_MSTRTMsg newCratSvcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
                ZYPEZDItemValueSetter.setValue(newCratSvcMachMstrTMsg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(newCratSvcMachMstrTMsg.svcMachMstrPk, insWhParam.get(i).svcMachMstrPk.getValue());
                cratSvcMachMstrArray[i] = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(newCratSvcMachMstrTMsg);
            }

            SVC_MACH_MSTRTMsgArray newCratSvcMachMstrTMsgArray = new SVC_MACH_MSTRTMsgArray();
            newCratSvcMachMstrTMsgArray.setMsgList(cratSvcMachMstrArray);
            newCratSvcMachMstrTMsgArray.setValidCount(insWhParam.size());

            ArrayList<NSZC001001PMsg> allocParam = setMachMstrApiParamForUpdate(ProcessMode.ALLOCATION_ON.code, shpgOrdTMsg, newCratSvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, null);

            allocParam = callMachineMasterUpdateAPI(allocParam, onBatch);
            ArrayList<MachineMasterResultBean> allocResultMsg = convertPMsgError(allocParam);

            if (!allocResultMsg.isEmpty()) {

                return allocResultMsg;
            }

            // Transfer Out or Update
            ArrayList<NSZC001001PMsg> updParam = new ArrayList<NSZC001001PMsg>();

            if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {

                updParam = setMachMstrApiParamForUpdate(ProcessMode.TRANSFER_OUT.code, shpgOrdTMsg, newCratSvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, null);
                updParam = callMachineMasterUpdateAPI(updParam, onBatch);

            } else if (checkUpdateMachMstr(sceOrdTpCd)) {

                updParam = setMachMstrApiParamForUpdate(ProcessMode.UPDATE_INVENTORY.code, shpgOrdTMsg, newCratSvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, null);
                updParam = callMachineMasterUpdateAPI(updParam, onBatch);
            }

            ArrayList<MachineMasterResultBean> updResultMsg = convertPMsgError(updParam);

            if (!updResultMsg.isEmpty()) {

                return updResultMsg;
            }
        }

        // Not Error
        return new ArrayList<MachineMasterResultBean>();
    }

    /**
     * callAssetStgngApiForDspl
     * @param glblCmpyCd String
     * @param svcMachMstrTMsgArray SVC_MACH_MSTRTMsgArray
     * @param onBatch ONBATCH_TYPE
     * @return ArrayList<MachineMasterResultBean>
     */
    private static ArrayList<MachineMasterResultBean> callAssetStgngApiForDspl(String glblCmpyCd, SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray, ONBATCH_TYPE onBatch) {

        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        ArrayList<MachineMasterResultBean> msgList = new ArrayList<MachineMasterResultBean>();
        ArrayList<NLZC309001PMsg> assetStgnApiPMsgList = new ArrayList<NLZC309001PMsg>();

        for (int i = 0; i < svcMachMstrTMsgArray.getValidCount(); i++) {

            SVC_MACH_MSTRTMsg svcMachMstrTMsg = svcMachMstrTMsgArray.no(i);

            NLZC309001PMsg assetStgnApiPMsg = new NLZC309001PMsg();
            ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.slsDt, slsDt);
            ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.procModeCd, NLZC309001Constant.PROC_MODE_ASSET_ADJ_OR_DISPOSAL);
            ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.mdseCd, svcMachMstrTMsg.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk.getValue());
            assetStgnApiPMsgList.add(assetStgnApiPMsg);
        }

        if (!assetStgnApiPMsgList.isEmpty()) {

            NLZC309001 assetStgnApi = new NLZC309001();
            assetStgnApi.execute(assetStgnApiPMsgList, onBatch);

            for (NLZC309001PMsg assetStgnApiPMsg : assetStgnApiPMsgList) {

                if (!S21ApiUtil.getXxMsgIdList(assetStgnApiPMsg).isEmpty()) {

                    for (String apiErrMsg : S21ApiUtil.getXxMsgIdList(assetStgnApiPMsg)) {

                        MachineMasterResultBean msg = new MachineMasterResultBean();
                        msg.setAssetErrMsgId(apiErrMsg);
                        msgList.add(msg);
                    }
                }
            }
        }

        return msgList;
    }

    /**
     * callMachineMasterUpdateForCancel
     * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
     * @param reCloseFlg boolean
     * @param onBatch ONBATCH_TYPE
     * @param ssmBatchClient S21SsmBatchClient
     * @param sceOrdTpCd String
     * @return errMsgList
     */
    public static ArrayList<MachineMasterResultBean> callMachineMasterUpdateForCancel(SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, boolean reCloseFlg, ONBATCH_TYPE onBatch, S21SsmBatchClient ssmBatchClient, String sceOrdTpCd) {

        MDSETMsg mdseInfo = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseInfo.glblCmpyCd, shpgOrdDtlTMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(mdseInfo.mdseCd, shpgOrdDtlTMsg.mdseCd.getValue());
        mdseInfo = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseInfo);

        if (mdseInfo != null && ZYPConstant.FLG_ON_Y.equals(mdseInfo.instlBaseCtrlFlg.getValue())) {

            if (ZYPConstant.FLG_ON_Y.equals(shpgOrdDtlTMsg.serNumTakeFlg.getValue())) {

                HashMap<String, String> sqlParam = new HashMap<String, String>();
                sqlParam.put("glblCmpyCd", shpgOrdDtlTMsg.glblCmpyCd.getValue());
                sqlParam.put("soNum", shpgOrdDtlTMsg.soNum.getValue());
                sqlParam.put("soSlpNum", shpgOrdDtlTMsg.soSlpNum.getValue());
                sqlParam.put("trxHdrNum", shpgOrdDtlTMsg.trxHdrNum.getValue());
                sqlParam.put("trxLineNum", shpgOrdDtlTMsg.trxLineNum.getValue());
                sqlParam.put("trxLineSubNum", shpgOrdDtlTMsg.trxLineSubNum.getValue());

                if (!reCloseFlg) {

                    sqlParam.put("notExistFlg", ZYPConstant.FLG_ON_Y);
                }

                List<BigDecimal> svcMachMstrPkList;
                svcMachMstrPkList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getSvcMachMstrPkFromSoSer", sqlParam);

                ArrayList<NSZC001001PMsg> param = setMachMstrApiParamForAllocOff(shpgOrdDtlTMsg.glblCmpyCd.getValue(), (ArrayList<BigDecimal>) svcMachMstrPkList);

                // Call Machine Master
                param = callMachineMasterUpdateAPI(param, onBatch);
                ArrayList<MachineMasterResultBean> resultBean = convertPMsgError(param);

                if (!resultBean.isEmpty()) {

                    return resultBean;
                }

            } else {

                // No Serial
                // Get Machine Master info
                // QC#14286 Mod.
                HashMap<String, String> sqlParam = new HashMap<String, String>();
                sqlParam.put("glblCmpyCd", shpgOrdDtlTMsg.glblCmpyCd.getValue());
                sqlParam.put("soNum", shpgOrdDtlTMsg.soNum.getValue());
                sqlParam.put("soSlpNum", shpgOrdDtlTMsg.soSlpNum.getValue());
                sqlParam.put("trxHdrNum", shpgOrdDtlTMsg.trxHdrNum.getValue());
                sqlParam.put("trxLineNum", shpgOrdDtlTMsg.trxLineNum.getValue());
                if (SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTpCd) || SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd)) {
                  sqlParam.put("trxLineSubNum", shpgOrdDtlTMsg.trxLineSubNum.getValue());
                }
                // QC#23438
                sqlParam.put("svcMachMstrStsCdW4R", SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);

                List<BigDecimal> svcMachMstrPkList;
                svcMachMstrPkList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getSvcMachMstrPkFromSoNoSer", sqlParam);

                ArrayList<NSZC001001PMsg> param = setMachMstrApiParamForAllocOff(shpgOrdDtlTMsg.glblCmpyCd.getValue(), (ArrayList<BigDecimal>) svcMachMstrPkList);

                // Call Machine Master
                param = callMachineMasterUpdateAPI(param, onBatch);
                ArrayList<MachineMasterResultBean> resultBean = convertPMsgError(param);

                if (!resultBean.isEmpty()) {

                    return resultBean;
                }
            }
        }

        // Not Error
        return new ArrayList<MachineMasterResultBean>();
    }

    /**
     * callMachineMasterUpdateForSerial
     * @param shpgOrdTMsg SHPG_ORDTMsg
     * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
     * @param shpgOrdConfDtlTMsg SHPG_ORD_CONF_DTLTMsg
     * @param shipSerNumWrkTMsg SHIP_SER_NUM_WRKTMsg
     * @param mmStatus String
     * @param shipSerNum String
     * @param isAssetWh boolean
     * @param onBatch ONBATCH_TYPE
     * @return errMsgList
     */
    public static ArrayList<MachineMasterResultBean> callMachineMasterUpdateForSerial(SHPG_ORDTMsg shpgOrdTMsg, SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtlTMsg,
            SHIP_SER_NUM_WRKTMsg shipSerNumWrkTMsg, String mmStatus, String shipSerNum, boolean isAssetWh, ONBATCH_TYPE onBatch) {

        String sceOrdTp = shpgOrdTMsg.sceOrdTpCd.getValue();

        // Get svcMachMstr
        SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray = getSvcMachMstr(shipSerNumWrkTMsg);

        // Error Status
        if (mmStatus.equals(MM_CHK_PTR_WH_OTHER_CONFIG) || mmStatus.equals(MM_CHK_PTR_WH_OTHER_CONFIG_ITEM) || mmStatus.equals(MM_CHK_PTR_OTHER_WH_CONFIG_ITEM) || mmStatus.equals(MM_CHK_PTR_WH_OTHER_CONFIG_NSS)
                || mmStatus.equals(MM_CHK_PTR_WH_OTHER_CONFIG_ITEM_NSS) || mmStatus.equals(MM_CHK_PTR_OTHER_WH_CONFIG_ITEM_NSS) || mmStatus.equals(MM_CHK_PTR_WH_OTHER_CONFIG_NS) || mmStatus.equals(MM_CHK_PTR_WH_OTHER_CONFIG_ITEM_NS)
                || mmStatus.equals(MM_CHK_PTR_OTHER_WH_CONFIG_ITEM_NS)) {
            return new ArrayList<MachineMasterResultBean>();
        }

        if (mmStatus.startsWith(MM_CHK_PTR_ON_SCHD)) {

            if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTp)) {

                return callMachMstrUpdTrnsfOnly(shpgOrdTMsg, svcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shipSerNum, onBatch);

            } else if (checkUpdateMachMstr(sceOrdTp)) {

                return callMachMstrUpdInvtyOnly(shpgOrdTMsg, svcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shipSerNum, onBatch);

            } else if (checkDiposalMachMstr(sceOrdTp)) {

                ArrayList<MachineMasterResultBean> machMstrApiResultMsg = callMachMstrUpdAllocToDispose(shpgOrdTMsg, svcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shipSerNum, onBatch);

                // Asset Staging for Disposal
                if (isAssetWh && machMstrApiResultMsg.isEmpty()) {

                    return callAssetStgngApiForDspl(shpgOrdTMsg.glblCmpyCd.getValue(), svcMachMstrTMsgArray, onBatch);

                } else {

                    return machMstrApiResultMsg;
                }
            }

        } else if (mmStatus.startsWith(MM_CHK_PTR_WH) || mmStatus.startsWith(MM_CHK_PTR_CONFIG_ITEM_OTHER_WH) || mmStatus.startsWith(MM_CHK_PTR_OTHER_WH) || mmStatus.startsWith(MM_CHK_PTR_WH_NSS)
                || mmStatus.startsWith(MM_CHK_PTR_CONFIG_ITEM_OTHER_WH_NSS) || mmStatus.startsWith(MM_CHK_PTR_OTHER_WH_NSS) || mmStatus.startsWith(MM_CHK_PTR_WH_NS) || mmStatus.startsWith(MM_CHK_PTR_CONFIG_ITEM_OTHER_WH_NS)
                || mmStatus.startsWith(MM_CHK_PTR_OTHER_WH_NS)) {

            if (SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTp) || SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTp)) {

                return callMachMstrUpdAllocOnOnly(shpgOrdTMsg, svcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shipSerNum, onBatch);

            } else if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTp)) {

                return callMachMstrUpdAllocOnToTrans(shpgOrdTMsg, svcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shipSerNum, onBatch);

            } else if (checkUpdateMachMstr(sceOrdTp)) {

                return callMachMstrUpdAllocOnToInvty(shpgOrdTMsg, svcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shipSerNum, onBatch);

            } else if (checkDiposalMachMstr(sceOrdTp)) {

                ArrayList<MachineMasterResultBean> machMstrApiResultMsg = callMachMstrUpdDisposeOnly(shpgOrdTMsg, svcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shipSerNum, onBatch);

                // Asset Staging for Disposal
                if (isAssetWh && machMstrApiResultMsg.isEmpty()) {

                    return callAssetStgngApiForDspl(shpgOrdTMsg.glblCmpyCd.getValue(), svcMachMstrTMsgArray, onBatch);

                } else {

                    return machMstrApiResultMsg;
                }
            }

        } else if (mmStatus.startsWith(MM_CHK_PTR_NO_IB) || mmStatus.startsWith(MM_CHK_PTR_NO_IB_NSS) || mmStatus.startsWith(MM_CHK_PTR_NO_IB_NS)) {

            // Create Machine Master
            SVC_MACH_MSTRTMsg dummySvcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
            SVC_MACH_MSTRTMsgArray dummySvcMachMstrTMsgArray = (SVC_MACH_MSTRTMsgArray) dummySvcMachMstrTMsg.getNewInstance().getMyArray();
            dummySvcMachMstrTMsgArray.setMsgList(new SVC_MACH_MSTRTMsg[1]);
            dummySvcMachMstrTMsgArray.setValidCount(1);

            if (SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTp)) {

                return callMachMstrUpdinsWhToTrans(shpgOrdTMsg, dummySvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shipSerNum, onBatch, false);

            } else if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTp)) {

                return callMachMstrUpdinsWhToTrans(shpgOrdTMsg, dummySvcMachMstrTMsgArray, shpgOrdDtlTMsg,  shpgOrdConfDtlTMsg, shipSerNum, onBatch, true);

            } else if (checkUpdateMachMstr(sceOrdTp)) {

                return callMachMstrUpdinsWhToInvty(shpgOrdTMsg, dummySvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shipSerNum, onBatch);
            }
        }

        // No Error
        return new ArrayList<MachineMasterResultBean>();
    }

    /**
     * callMachMstrUpdTrnsfOnly
     * @param so SHPG_ORDTMsg
     * @param smmArray SVC_MACH_MSTRTMsgArray
     * @param sod SHPG_ORD_DTLTMsg
     * @param socd SHPG_ORD_CONF_DTLTMsg
     * @param shipSerNum SHIP_SER_NUMTMsg
     * @param onBatch ONBATCH_TYPE
     * @return errMsgList
     */
    private static ArrayList<MachineMasterResultBean> callMachMstrUpdTrnsfOnly(SHPG_ORDTMsg so, SVC_MACH_MSTRTMsgArray smmArray, SHPG_ORD_DTLTMsg sod, SHPG_ORD_CONF_DTLTMsg socd, String shipSerNum,
            ONBATCH_TYPE onBatch) {

        ArrayList<NSZC001001PMsg> pMsg = setMachMstrApiParamForUpdate(ProcessMode.TRANSFER_OUT.code, so, smmArray, sod, socd, shipSerNum);
        pMsg = callMachineMasterUpdateAPI(pMsg, onBatch);

        return convertPMsgError(pMsg);
    }

    /**
     * callMachMstrUpdInvty
     * @param so SHPG_ORDTMsg
     * @param smmArray SVC_MACH_MSTRTMsgArray
     * @param sod SHPG_ORD_DTLTMsg
     * @param socd SHPG_ORD_CONF_DTLTMsg
     * @param shipSerNum SHIP_SER_NUMTMsg
     * @param onBatch ONBATCH_TYPE
     * @return errMsgList
     */
    private static ArrayList<MachineMasterResultBean> callMachMstrUpdInvtyOnly(SHPG_ORDTMsg so, SVC_MACH_MSTRTMsgArray smmArray, SHPG_ORD_DTLTMsg sod, SHPG_ORD_CONF_DTLTMsg socd, String shipSerNum,
            ONBATCH_TYPE onBatch) {

        ArrayList<NSZC001001PMsg> pMsg = setMachMstrApiParamForUpdate(ProcessMode.UPDATE_INVENTORY.code, so, smmArray, sod, socd, shipSerNum);
        pMsg = callMachineMasterUpdateAPI(pMsg, onBatch);

        return convertPMsgError(pMsg);
    }

    /**
     * callMachMstrUpdAllocToDispose
     * @param so SHPG_ORDTMsg
     * @param smmArray SVC_MACH_MSTRTMsgArray
     * @param sod SHPG_ORD_DTLTMsg
     * @param socd SHPG_ORD_CONF_DTLTMsg
     * @param shipSerNum SHIP_SER_NUMTMsg
     * @param onBatch ONBATCH_TYPE
     * @return errMsgList
     */
    private static ArrayList<MachineMasterResultBean> callMachMstrUpdAllocToDispose(SHPG_ORDTMsg so, SVC_MACH_MSTRTMsgArray smmArray, SHPG_ORD_DTLTMsg sod, SHPG_ORD_CONF_DTLTMsg socd, String shipSerNum,
            ONBATCH_TYPE onBatch) {

        ArrayList<NSZC001001PMsg> allocPMsg = setMachMstrApiParamForUpdate(ProcessMode.ALLOCATION_OFF.code, so, smmArray, sod, socd, shipSerNum);
        allocPMsg = callMachineMasterUpdateAPI(allocPMsg, onBatch);

        ArrayList<MachineMasterResultBean> allocResult = convertPMsgError(allocPMsg);

        if (allocResult.isEmpty()) {

            ArrayList<NSZC001001PMsg> disposePMsg = setMachMstrApiParamForUpdate(ProcessMode.DISPOSAL.code, so, smmArray, sod, socd, shipSerNum);
            disposePMsg = callMachineMasterUpdateAPI(disposePMsg, onBatch);
            return convertPMsgError(disposePMsg);

        } else {

            return allocResult;
        }
    }

    /**
     * callMachMstrUpdAllocOnOnly
     * @param so SHPG_ORDTMsg
     * @param smmArray SVC_MACH_MSTRTMsgArray
     * @param sod SHPG_ORD_DTLTMsg
     * @param socd SHPG_ORD_CONF_DTLTMsg
     * @param shipSerNum SHIP_SER_NUMTMsg
     * @param onBatch ONBATCH_TYPE
     * @return errMsgList
     */
    private static ArrayList<MachineMasterResultBean> callMachMstrUpdAllocOnOnly(SHPG_ORDTMsg so, SVC_MACH_MSTRTMsgArray smmArray, SHPG_ORD_DTLTMsg sod, SHPG_ORD_CONF_DTLTMsg socd, String shipSerNum,
            ONBATCH_TYPE onBatch) {

        ArrayList<NSZC001001PMsg> pMsg = setMachMstrApiParamForUpdate(ProcessMode.ALLOCATION_ON.code, so, smmArray, sod, socd, shipSerNum);
        pMsg = callMachineMasterUpdateAPI(pMsg, onBatch);

        return convertPMsgError(pMsg);
    }

    /**
     * callMachMstrUpdAllocOnToTrans
     * @param so SHPG_ORDTMsg
     * @param smmArray SVC_MACH_MSTRTMsgArray
     * @param sod SHPG_ORD_DTLTMsg
     * @param socd SHPG_ORD_CONF_DTLTMsg
     * @param shipSerNum SHIP_SER_NUMTMsg
     * @param onBatch ONBATCH_TYPE
     * @return errMsgList
     */
    private static ArrayList<MachineMasterResultBean> callMachMstrUpdAllocOnToTrans(SHPG_ORDTMsg so, SVC_MACH_MSTRTMsgArray smmArray, SHPG_ORD_DTLTMsg sod, SHPG_ORD_CONF_DTLTMsg socd, String shipSerNum,
            ONBATCH_TYPE onBatch) {

        ArrayList<NSZC001001PMsg> allocMsg = setMachMstrApiParamForUpdate(ProcessMode.ALLOCATION_ON.code, so, smmArray, sod, socd, shipSerNum);
        allocMsg = callMachineMasterUpdateAPI(allocMsg, onBatch);

        ArrayList<MachineMasterResultBean> allocResult = convertPMsgError(allocMsg);

        if (allocResult.isEmpty()) {

            ArrayList<NSZC001001PMsg> transMsg = setMachMstrApiParamForUpdate(ProcessMode.TRANSFER_OUT.code, so, smmArray, sod, socd, shipSerNum);
            transMsg = callMachineMasterUpdateAPI(transMsg, onBatch);
            return convertPMsgError(transMsg);

        } else {

            return allocResult;
        }
    }

    /**
     * callMachMstrUpdAllocOnToInvty
     * @param so SHPG_ORDTMsg
     * @param smmArray SVC_MACH_MSTRTMsgArray
     * @param sod SHPG_ORD_DTLTMsg
     * @param socd SHPG_ORD_CONF_DTLTMsg
     * @param shipSerNum SHIP_SER_NUMTMsg
     * @param onBatch ONBATCH_TYPE
     * @return errMsgList
     */
    private static ArrayList<MachineMasterResultBean> callMachMstrUpdAllocOnToInvty(SHPG_ORDTMsg so, SVC_MACH_MSTRTMsgArray smmArray, SHPG_ORD_DTLTMsg sod, SHPG_ORD_CONF_DTLTMsg socd, String shipSerNum,
            ONBATCH_TYPE onBatch) {

        ArrayList<NSZC001001PMsg> allocMsg = setMachMstrApiParamForUpdate(ProcessMode.ALLOCATION_ON.code, so, smmArray, sod, socd, shipSerNum);
        allocMsg = callMachineMasterUpdateAPI(allocMsg, onBatch);

        ArrayList<MachineMasterResultBean> allocResult = convertPMsgError(allocMsg);

        if (allocResult.isEmpty()) {

            ArrayList<NSZC001001PMsg> invtyMsg = setMachMstrApiParamForUpdate(ProcessMode.UPDATE_INVENTORY.code, so, smmArray, sod, socd, shipSerNum);
            invtyMsg = callMachineMasterUpdateAPI(invtyMsg, onBatch);
            return convertPMsgError(invtyMsg);

        } else {

            return allocResult;
        }
    }

    /**
     * callMachMstrUpdDisposeOnly
     * @param so SHPG_ORDTMsg
     * @param smmArray SVC_MACH_MSTRTMsgArray
     * @param sod SHPG_ORD_DTLTMsg
     * @param socd SHPG_ORD_CONF_DTLTMsg
     * @param shipSerNum SHIP_SER_NUMTMsg
     * @param onBatch ONBATCH_TYPE
     * @return errMsgList
     */
    private static ArrayList<MachineMasterResultBean> callMachMstrUpdDisposeOnly(SHPG_ORDTMsg so, SVC_MACH_MSTRTMsgArray smmArray, SHPG_ORD_DTLTMsg sod, SHPG_ORD_CONF_DTLTMsg socd, String shipSerNum,
            ONBATCH_TYPE onBatch) {

        ArrayList<NSZC001001PMsg> pMsg = setMachMstrApiParamForUpdate(ProcessMode.DISPOSAL.code, so, smmArray, sod, socd, shipSerNum);
        pMsg = callMachineMasterUpdateAPI(pMsg, onBatch);

        return convertPMsgError(pMsg);
    }

    /**
     * callMachMstrUpdinsWhToTrans
     * @param shpgOrdTMsg SHPG_ORDTMsg
     * @param svcMachMstrTMsgArray SVC_MACH_MSTRTMsgArray
     * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
     * @param shpgOrdConfDtlTMsg SHPG_ORD_CONF_DTLTMsg
     * @param shipSerNum SHIP_SER_NUMTMsg
     * @param onBatch ONBATCH_TYPE
     * @param isNeedTransOut boolean
     * @return errMsgList
     */
    private static ArrayList<MachineMasterResultBean> callMachMstrUpdinsWhToTrans(SHPG_ORDTMsg shpgOrdTMsg, SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray, SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtlTMsg,
            String shipSerNum, ONBATCH_TYPE onBatch, boolean isNeedTransOut) {

        // Create Machine Master
        ArrayList<NSZC001001PMsg> insWhMsg = setMachMstrApiParamForUpdate(ProcessMode.INSERT_WAREHOUSE.code, shpgOrdTMsg, svcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shipSerNum);

        insWhMsg = callMachineMasterUpdateAPI(insWhMsg, onBatch);
        ArrayList<MachineMasterResultBean> insWhResult = convertPMsgError(insWhMsg);

        if (insWhResult.isEmpty()) {

            // Allocation
            SVC_MACH_MSTRTMsg[] cratSvcMachMstrArray = new SVC_MACH_MSTRTMsg[insWhMsg.size()];

            for (int i = 0; i < insWhMsg.size(); i++) {

                SVC_MACH_MSTRTMsg newCratSvcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
                ZYPEZDItemValueSetter.setValue(newCratSvcMachMstrTMsg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(newCratSvcMachMstrTMsg.svcMachMstrPk, insWhMsg.get(i).svcMachMstrPk.getValue());
                cratSvcMachMstrArray[i] = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(newCratSvcMachMstrTMsg);
            }

            SVC_MACH_MSTRTMsgArray newCratSvcMachMstrTMsgArray = new SVC_MACH_MSTRTMsgArray();
            newCratSvcMachMstrTMsgArray.setMsgList(cratSvcMachMstrArray);
            newCratSvcMachMstrTMsgArray.setValidCount(insWhMsg.size());

            ArrayList<NSZC001001PMsg> allocMsg = setMachMstrApiParamForUpdate(ProcessMode.ALLOCATION_ON.code, shpgOrdTMsg, newCratSvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shipSerNum);

            allocMsg = callMachineMasterUpdateAPI(allocMsg, onBatch);
            ArrayList<MachineMasterResultBean> allocResult = convertPMsgError(allocMsg);

            if (!isNeedTransOut) {

                return allocResult;
            }

            if (allocResult.isEmpty()) {

                // Transfer Out
                ArrayList<NSZC001001PMsg> transMsg = setMachMstrApiParamForUpdate(ProcessMode.TRANSFER_OUT.code, shpgOrdTMsg, newCratSvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shipSerNum);

                transMsg = callMachineMasterUpdateAPI(transMsg, onBatch);
                return convertPMsgError(transMsg);

            } else {

                return allocResult;
            }

        } else {

            return insWhResult;
        }
    }

    /**
     * callMachMstrUpdinsWhToInvty
     * @param shpgOrdTMsg SHPG_ORDTMsg
     * @param svcMachMstrTMsgArray SVC_MACH_MSTRTMsgArray
     * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
     * @param shpgOrdConfDtlTMsg SHPG_ORD_CONF_DTLTMsg
     * @param shipSerNum SHIP_SER_NUMTMsg
     * @param onBatch ONBATCH_TYPE
     * @return errMsgList
     */
    private static ArrayList<MachineMasterResultBean> callMachMstrUpdinsWhToInvty(SHPG_ORDTMsg shpgOrdTMsg, SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray, SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtlTMsg,
            String shipSerNum, ONBATCH_TYPE onBatch) {

        // Create Machine Master
        ArrayList<NSZC001001PMsg> insWhMsg = setMachMstrApiParamForUpdate(ProcessMode.INSERT_WAREHOUSE.code, shpgOrdTMsg, svcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shipSerNum);

        insWhMsg = callMachineMasterUpdateAPI(insWhMsg, onBatch);
        ArrayList<MachineMasterResultBean> insWhResult = convertPMsgError(insWhMsg);

        if (insWhResult.isEmpty()) {

            // Allocation
            SVC_MACH_MSTRTMsg[] cratSvcMachMstrArray = new SVC_MACH_MSTRTMsg[insWhMsg.size()];

            for (int i = 0; i < insWhMsg.size(); i++) {

                SVC_MACH_MSTRTMsg newCratSvcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
                ZYPEZDItemValueSetter.setValue(newCratSvcMachMstrTMsg.glblCmpyCd, shpgOrdTMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(newCratSvcMachMstrTMsg.svcMachMstrPk, insWhMsg.get(i).svcMachMstrPk.getValue());
                cratSvcMachMstrArray[i] = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(newCratSvcMachMstrTMsg);
            }

            SVC_MACH_MSTRTMsgArray newCratSvcMachMstrTMsgArray = new SVC_MACH_MSTRTMsgArray();
            newCratSvcMachMstrTMsgArray.setMsgList(cratSvcMachMstrArray);
            newCratSvcMachMstrTMsgArray.setValidCount(insWhMsg.size());

            ArrayList<NSZC001001PMsg> allocMsg = setMachMstrApiParamForUpdate(ProcessMode.ALLOCATION_ON.code, shpgOrdTMsg, newCratSvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shipSerNum);

            allocMsg = callMachineMasterUpdateAPI(allocMsg, onBatch);
            ArrayList<MachineMasterResultBean> allocResult = convertPMsgError(allocMsg);
            if (allocResult.isEmpty()) {

                // Update
                ArrayList<NSZC001001PMsg> updMsg = setMachMstrApiParamForUpdate(ProcessMode.UPDATE_INVENTORY.code, shpgOrdTMsg, newCratSvcMachMstrTMsgArray, shpgOrdDtlTMsg, shpgOrdConfDtlTMsg, shipSerNum);

                updMsg = callMachineMasterUpdateAPI(updMsg, onBatch);
                return convertPMsgError(updMsg);

            } else {

                return allocResult;
            }

        } else {

            return insWhResult;
        }
    }

    /**
     * callMachineMasterUpdateAPI
     * @param param ArrayList<NSZC001001PMsg>
     * @return true:Success false: Error
     */
    private static ArrayList<NSZC001001PMsg> callMachineMasterUpdateAPI(ArrayList<NSZC001001PMsg> param, ONBATCH_TYPE onBatch) {

        NSZC001001 api = new NSZC001001();
        api.execute(param, onBatch);

        return param;
    }

    /**
     * convertPMsgError
     * @param param ArrayList<NSZC001001PMsg>
     * @return errMsg ArrayList<MachineMasterResultBean>
     */
    private static ArrayList<MachineMasterResultBean> convertPMsgError(ArrayList<NSZC001001PMsg> param) {

        ArrayList<MachineMasterResultBean> msgList = new ArrayList<MachineMasterResultBean>();

        for (NSZC001001PMsg pMsg : param) {

            if (S21ApiUtil.isXxMsgId(pMsg)) {

                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

                    MachineMasterResultBean msg = new MachineMasterResultBean();
                    msg.setMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                    msg.setMsgPrmTxt1(pMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue());
                    msg.setMsgPrmTxt2(pMsg.xxMsgIdList.no(i).xxMsgPrmTxt_1.getValue());
                    msgList.add(msg);
                }
            }
        }

        return msgList;
    }

    /**
     * setMachMstrApiParamForUpdate
     * @param procMode String
     * @param shpgOrdTMsg SHPG_ORDTMsg
     * @param svcMachMstrTMsgArray SVC_MACH_MSTRTMsgArray
     * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
     * @param shpgOrdConfDtlTMsg SHPG_ORD_CONF_DTLTMsg
     * @param shipSerNum String
     * @return ArrayList<NSZC001001PMsg>
     */
    private static ArrayList<NSZC001001PMsg> setMachMstrApiParamForUpdate(String procMode, SHPG_ORDTMsg shpgOrdTMsg, SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray, SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, 
            SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtlTMsg, String shipSerNum) {

        ArrayList<NSZC001001PMsg> paramList = new ArrayList<NSZC001001PMsg>();

        String glblCmpyCd = shpgOrdTMsg.glblCmpyCd.getValue();
        String sceOrdTp = shpgOrdTMsg.sceOrdTpCd.getValue();
        String shipDt = shpgOrdConfDtlTMsg.shipDtTmTs.getValue().substring(0, FORMAT_YYYYMMDD.length());

        for (int i = 0; i < svcMachMstrTMsgArray.getValidCount(); i++) {

            NSZC001001PMsg pMsg = new NSZC001001PMsg();
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = svcMachMstrTMsgArray.no(i);

            if (ProcessMode.ALLOCATION_OFF.code.equals(procMode)) {

                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, procMode);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk.getValue());

            } else if (ProcessMode.ALLOCATION_ON.code.equals(procMode)) {

                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, procMode);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, shpgOrdDtlTMsg.trxHdrNum.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.trxLineNum, shpgOrdDtlTMsg.trxLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.trxLineSubNum, shpgOrdDtlTMsg.trxLineSubNum.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, svcMachMstrTMsg.svcMachMstrLocStsCd.getValue());

            } else if (ProcessMode.TRANSFER_OUT.code.equals(procMode)) {

                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, procMode);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, svcMachMstrTMsg.svcMachMstrStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, shpgOrdConfDtlTMsg.fromStkStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.shipFromWhCd, shpgOrdConfDtlTMsg.whCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.shipDt, shipDt);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_TRANSIT);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, LOC_STS.IN_TRANSIT_WH);
                ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, shpgOrdDtlTMsg.toInvtyLocCd.getValue());

            } else if (ProcessMode.DISPOSAL.code.equals(procMode)) {

                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, procMode);
                ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.serNum, svcMachMstrTMsg.serNum.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.TERMINATED);
                ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, shpgOrdConfDtlTMsg.fromStkStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.effThruDt, shipDt);
                // START 2023/09/22 T.Kuroda [QC#61265, MOD]
                //ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, svcMachMstrTMsg.svcMachUsgStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.GONE);
                // END   2022/09/22 T.Kuroda [QC#61265, MOD]

            } else if (ProcessMode.UPDATE_INVENTORY.code.equals(procMode)) {

                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, procMode);
                ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, svcMachMstrTMsg.svcMachMstrStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, shpgOrdConfDtlTMsg.fromStkStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, shpgOrdDtlTMsg.toInvtyLocCd.getValue());

                if (SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTp)) {
                    // QC#26585 MOD START
                    // ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd,
                    // LOC_STS.IN_TRANSIT);
                    ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, LOC_STS.WORK_IN_PROCESS_SUBCONTRACT);
                    // QC#26585 MOD START

                } else if (SCE_ORD_TP.KITTING.equals(sceOrdTp)) {

                    ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, LOC_STS.WORK_IN_PROCESS_COMPONENT);

                } else if (SCE_ORD_TP.UN_KITTING.equals(sceOrdTp)) {

                    ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, LOC_STS.WORK_IN_PROCESS_KIT);
                }

            } else if (ProcessMode.INSERT_WAREHOUSE.code.equals(procMode)) {

                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, procMode);
                ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, shpgOrdConfDtlTMsg.mdseCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
                ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, shpgOrdConfDtlTMsg.fromStkStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(pMsg.effFromDt, shipDt);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachQty, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
                ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, shpgOrdConfDtlTMsg.whCd.getValue());

                if (shipSerNum != null) {

                    ZYPEZDItemValueSetter.setValue(pMsg.serNum, shipSerNum);
                }
            }

            paramList.add(pMsg);
        }

        return paramList;
    }

    /**
     * setMachMstrApiParamForAllocOff
     * @param glblCmpyCd String
     * @param svcMachMstrPkList ArrayList<BigDecimal>
     * @return
     */
    private static ArrayList<NSZC001001PMsg> setMachMstrApiParamForAllocOff(String glblCmpyCd, ArrayList<BigDecimal> svcMachMstrPkList) {

        ArrayList<NSZC001001PMsg> pMsgList = new ArrayList<NSZC001001PMsg>();

        for (BigDecimal svcMachMstrPk : svcMachMstrPkList) {

            NSZC001001PMsg pMsg = new NSZC001001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrPk);

            pMsgList.add(pMsg);
        }

        return pMsgList;
    }

    /**
     * checkMachineMasterAPIForSerial
     * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
     * @param socdList List<SHPG_ORD_CONF_DTLTMsg>
     * @param serNumWrk SHIP_SER_NUM_WRKTMsg
     * @return Serial trx error code.
     */
    public static String checkMachineMasterAPIForSerial(SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, List<SHPG_ORD_CONF_DTLTMsg> socdList, SHIP_SER_NUM_WRKTMsg serNumWrk) {

        MDSETMsg mdseInfoTMsg = getMdseInfo(shpgOrdDtlTMsg);
        SVC_MACH_MSTRTMsgArray smmArray = getSvcMachMstr(serNumWrk);
        SO_SERTMsgArray soSerArray = getSoSer(serNumWrk);

        // check shipping
        if (!ZYPConstant.FLG_ON_Y.equals(shpgOrdDtlTMsg.serNumTakeFlg.getValue())) {

            // Error not Terget
            return MM_NO_SHIP_TARGET;
        }

        if (mdseInfoTMsg == null || !ZYPConstant.FLG_ON_Y.equals(mdseInfoTMsg.instlBaseCtrlFlg.getValue())) {

            // Error not Terget
            return MM_NO_SHIP_TARGET;
        }

        // Status check
        String[] mmStatus = new String[MACH_MSTR_STATUS_CHECK_COLUMN];

        // Check Transaction Number
        mmStatus = checkTrxNum(shpgOrdDtlTMsg, smmArray, mmStatus);

        // Check Serial
        mmStatus = checkSoSerial(soSerArray, serNumWrk, mmStatus);

        // Check Machine Master Status
        mmStatus = checkMashMstrSts(smmArray, mmStatus);

        // Check Location
        mmStatus = checkLocation(smmArray, socdList, mmStatus);

        // Check Configuration
        mmStatus = checkConfig(smmArray, shpgOrdDtlTMsg, mmStatus);

        // Stock Status (QC#20008 Add)
        mmStatus = checkStockSts(smmArray, shpgOrdDtlTMsg, mmStatus);

        // join mmStatus
        String status = mmStatus[MM_CHECK_ALLOCATION] + mmStatus[MM_CHECK_EXIST_SERIAL] + mmStatus[MM_CHECK_SHIP_SERIAL] + mmStatus[MM_CHECK_MM_STATUS_TRMN] + mmStatus[MM_CHECK_MM_STATUS_CRAT] + mmStatus[MM_CHECK_OTHER_ALLOCATION]
                + mmStatus[MM_CHECK_LOCATION] + mmStatus[MM_CHECK_CONFIG] + mmStatus[MM_CHECK_ORDER_CONFIG] + mmStatus[MM_CHECK_STK_STS];

        return status;
    }

    /**
     * getMdseInfo
     * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
     * @return MDSETMsg
     */
    private static MDSETMsg getMdseInfo(SHPG_ORD_DTLTMsg shpgOrdDtlTMsg) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, shpgOrdDtlTMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, shpgOrdDtlTMsg.mdseCd.getValue());

        return (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);
    }

    /**
     * getSvcMachMstr
     * @param shipSerNumWrkTMsg SHIP_SER_NUM_WRKTMsg
     * @return SVC_MACH_MSTRTMsgArray
     */
    private static SVC_MACH_MSTRTMsgArray getSvcMachMstr(SHIP_SER_NUM_WRKTMsg shipSerNumWrkTMsg) {

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        svcMachMstrTMsg.setSQLID("001");
        svcMachMstrTMsg.setConditionValue("glblCmpyCd01", shipSerNumWrkTMsg.glblCmpyCd.getValue());
        svcMachMstrTMsg.setConditionValue("serNum01", shipSerNumWrkTMsg.serNum.getValue());
        svcMachMstrTMsg.setConditionValue("mdseCd01", shipSerNumWrkTMsg.mdseCd.getValue());

        return (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(svcMachMstrTMsg);
    }

    /**
     * getSoSer
     * @param shipSerNumWrkTMsg SHIP_SER_NUM_WRKTMsg
     * @return SO_SERTMsgArray
     */
    private static SO_SERTMsgArray getSoSer(SHIP_SER_NUM_WRKTMsg shipSerNumWrkTMsg) {

        SO_SERTMsg soSerTMsg = new SO_SERTMsg();
        soSerTMsg.setSQLID("002");
        soSerTMsg.setConditionValue("glblCmpyCd01", shipSerNumWrkTMsg.glblCmpyCd.getValue());
        soSerTMsg.setConditionValue("soNum01", shipSerNumWrkTMsg.soNum.getValue());
        soSerTMsg.setConditionValue("mdseCd01", shipSerNumWrkTMsg.mdseCd.getValue());

        return (SO_SERTMsgArray) S21ApiTBLAccessor.findByCondition(soSerTMsg);
    }

    /**
     * checkTrxNum
     * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
     * @param svcMachMstrTMsgArray SVC_MACH_MSTRTMsgArray
     * @param mmStatus String[]
     * @return mmStatus String[]
     */
    private static String[] checkTrxNum(SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray, String[] mmStatus) {

        for (int i = 0; i < svcMachMstrTMsgArray.getValidCount(); i++) {

            if (shpgOrdDtlTMsg.trxHdrNum.getValue().equals(svcMachMstrTMsgArray.no(i).trxHdrNum.getValue()) && shpgOrdDtlTMsg.trxLineNum.getValue().equals(svcMachMstrTMsgArray.no(i).trxLineNum.getValue())
                    && shpgOrdDtlTMsg.trxLineSubNum.getValue().equals(svcMachMstrTMsgArray.no(i).trxLineSubNum.getValue())) {

                mmStatus[MM_CHECK_ALLOCATION] = ZYPConstant.FLG_ON_Y;

            } else if (ZYPCommonFunc.hasValue(svcMachMstrTMsgArray.no(i).trxHdrNum.getValue()) && ZYPCommonFunc.hasValue(svcMachMstrTMsgArray.no(i).trxLineNum.getValue())
                    && ZYPCommonFunc.hasValue(svcMachMstrTMsgArray.no(i).trxLineSubNum.getValue())) {

                mmStatus[MM_CHECK_OTHER_ALLOCATION] = ZYPConstant.FLG_ON_Y;
            }
        }

        if (mmStatus[MM_CHECK_ALLOCATION] == null) {

            mmStatus[MM_CHECK_ALLOCATION] = ZYPConstant.FLG_OFF_N;
        }

        if (mmStatus[MM_CHECK_OTHER_ALLOCATION] == null) {

            mmStatus[MM_CHECK_OTHER_ALLOCATION] = ZYPConstant.FLG_OFF_N;
        }

        return mmStatus;
    }

    /**
     * checkSoSerial
     * @param soSerTMsgArray SO_SERTMsgArray
     * @param shipSerNumWrkTMsg SHIP_SER_NUM_WRKTMsg
     * @param mmStatus String[]
     * @return mmStatus String[]
     */
    private static String[] checkSoSerial(SO_SERTMsgArray soSerTMsgArray, SHIP_SER_NUM_WRKTMsg shipSerNumWrkTMsg, String[] mmStatus) {

        for (int i = 0; i < soSerTMsgArray.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(soSerTMsgArray.no(i).serNum)) {

                mmStatus[MM_CHECK_EXIST_SERIAL] = ZYPConstant.FLG_ON_Y;

                if (soSerTMsgArray.no(i).serNum.getValue().equals(shipSerNumWrkTMsg.soNum.getValue())) {

                    mmStatus[MM_CHECK_SHIP_SERIAL] = ZYPConstant.FLG_ON_Y;
                }
            }
        }

        if (mmStatus[MM_CHECK_EXIST_SERIAL] == null) {

            mmStatus[MM_CHECK_EXIST_SERIAL] = ZYPConstant.FLG_OFF_N;
        }

        if (mmStatus[MM_CHECK_SHIP_SERIAL] == null) {

            mmStatus[MM_CHECK_SHIP_SERIAL] = ZYPConstant.FLG_OFF_N;
        }

        return mmStatus;
    }

    /**
     * checkMashMstrSts
     * @param svcMachMstrTMsgArray SVC_MACH_MSTRTMsgArray
     * @param mmStatus String[]
     * @return mmStatus String[]
     */
    private static String[] checkMashMstrSts(SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray, String[] mmStatus) {

        for (int i = 0; i < svcMachMstrTMsgArray.getValidCount(); i++) {

            if (SVC_MACH_MSTR_STS.DUPLICATE.equals(svcMachMstrTMsgArray.no(i).svcMachMstrStsCd.getValue()) || SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrTMsgArray.no(i).svcMachMstrStsCd.getValue())) {

                mmStatus[MM_CHECK_MM_STATUS_TRMN] = ZYPConstant.FLG_OFF_N;

            } else {

                mmStatus[MM_CHECK_MM_STATUS_TRMN] = ZYPConstant.FLG_ON_Y;
            }

            if (SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrTMsgArray.no(i).svcMachMstrStsCd.getValue()) || SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrTMsgArray.no(i).svcMachMstrStsCd.getValue())) {

                mmStatus[MM_CHECK_MM_STATUS_CRAT] = ZYPConstant.FLG_ON_Y;

            } else {

                mmStatus[MM_CHECK_MM_STATUS_CRAT] = ZYPConstant.FLG_OFF_N;
            }
        }

        if (mmStatus[MM_CHECK_MM_STATUS_TRMN] == null) {

            mmStatus[MM_CHECK_MM_STATUS_TRMN] = ZYPConstant.FLG_OFF_N;
        }

        if (mmStatus[MM_CHECK_MM_STATUS_CRAT] == null) {

            mmStatus[MM_CHECK_MM_STATUS_CRAT] = ZYPConstant.FLG_OFF_N;
        }

        return mmStatus;
    }

    /**
     * checkLocation
     * @param svcMachMstrTMsgArray SVC_MACH_MSTRTMsgArray
     * @param shpgOrdConfDtlTMsgList List<SHPG_ORD_CONF_DTLTMsg>
     * @param mmStatus String[]
     * @return mmStatus String[]
     */
    private static String[] checkLocation(SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray, List<SHPG_ORD_CONF_DTLTMsg> shpgOrdConfDtlTMsgList, String[] mmStatus) {

        for (int i = 0; i < svcMachMstrTMsgArray.getValidCount(); i++) {

            for (SHPG_ORD_CONF_DTLTMsg shpgOrdConfDtlTMsg : shpgOrdConfDtlTMsgList) {

                if (ZYPCommonFunc.hasValue(svcMachMstrTMsgArray.no(i).curLocNum)) {

                    if (svcMachMstrTMsgArray.no(i).curLocNum.getValue().equals(shpgOrdConfDtlTMsg.whCd.getValue())) {

                        mmStatus[MM_CHECK_LOCATION] = ZYPConstant.FLG_ON_Y;
                    }

                } else {

                    break;
                }
            }
        }

        if (mmStatus[MM_CHECK_LOCATION] == null) {

            mmStatus[MM_CHECK_LOCATION] = ZYPConstant.FLG_OFF_N;
        }

        return mmStatus;
    }

    /**
     * checkConfig
     * @param svcMachMstrTMsgArray SVC_MACH_MSTRTMsgArray
     * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
     * @param mmStatus String[]
     * @return mmStatus String[]
     */
    private static String[] checkConfig(SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray, SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, String[] mmStatus) {

        for (int i = 0; i < svcMachMstrTMsgArray.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(svcMachMstrTMsgArray.no(i).svcConfigMstrPk)) {

                mmStatus[MM_CHECK_CONFIG] = ZYPConstant.FLG_ON_Y;

                if (svcMachMstrTMsgArray.no(i).svcConfigMstrPk.getValue().equals(shpgOrdDtlTMsg.pickSvcConfigMstrPk.getValue())) {

                    mmStatus[MM_CHECK_ORDER_CONFIG] = ZYPConstant.FLG_ON_Y;
                } else {

                    mmStatus[MM_CHECK_ORDER_CONFIG] = ZYPConstant.FLG_OFF_N;
                }

            } else {

                mmStatus[MM_CHECK_CONFIG] = ZYPConstant.FLG_OFF_N;

                if (ZYPCommonFunc.hasValue(shpgOrdDtlTMsg.pickSvcConfigMstrPk)) {

                    mmStatus[MM_CHECK_ORDER_CONFIG] = ZYPConstant.FLG_OFF_N;
                } else {

                    mmStatus[MM_CHECK_ORDER_CONFIG] = ZYPConstant.FLG_ON_Y;
                }
            }

        }

        if (mmStatus[MM_CHECK_CONFIG] == null) {

            mmStatus[MM_CHECK_CONFIG] = ZYPConstant.FLG_OFF_N;
        }

        if (mmStatus[MM_CHECK_ORDER_CONFIG] == null) {

            mmStatus[MM_CHECK_ORDER_CONFIG] = ZYPConstant.FLG_OFF_N;
        }

        return mmStatus;
    }

    /**
     * check Stock Status (QC#20008 Add)
     * @param svcMachMstrTMsgArray SVC_MACH_MSTRTMsgArray
     * @param shpgOrdDtlTMsg SHPG_ORD_DTLTMsg
     * @param mmStatus String[]
     * @return mmStatus String[]
     */
    private static String[] checkStockSts(SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray, SHPG_ORD_DTLTMsg shpgOrdDtlTMsg, String[] mmStatus) {

        for (int i = 0; i < svcMachMstrTMsgArray.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(svcMachMstrTMsgArray.no(i).stkStsCd)) {

                mmStatus[MM_CHECK_STK_STS] = ZYPConstant.FLG_ON_Y;

                if (!svcMachMstrTMsgArray.no(i).stkStsCd.getValue().equals(shpgOrdDtlTMsg.fromStkStsCd.getValue())) {

                    mmStatus[MM_CHECK_STK_STS] = ZYPConstant.FLG_OFF_N;

                }
            }
        }

        if (mmStatus[MM_CHECK_STK_STS] == null) {

            mmStatus[MM_CHECK_STK_STS] = ZYPConstant.FLG_ON_Y;
        }

        return mmStatus;
    }

    /**
     * checkSerialTrxError
     * @param mmStatus String
     * @return Error Message. [0]=Error Status. [1]=Error Comment.
     */
    public static String[] checkSerialTrxError(String mmStatus) {

        if (mmStatus.startsWith(MM_CHK_PTR_OTHR_ORD) || mmStatus.startsWith(MM_CHK_PTR_OTHR_ORD_NSS) || mmStatus.startsWith(MM_CHK_PTR_OTHR_ORD_NS)) {

            return new String[] {SERIAL_TRX_ERROR_DUPLICATE, SER_TRX_CMNT_NLZM2317E };

        } else if (mmStatus.startsWith(MM_CHK_PTR_OTHR_ITEM_OTHR_WH) || mmStatus.startsWith(MM_CHK_PTR_OTHR_ITEM_OTHR_WH_NSS) || mmStatus.startsWith(MM_CHK_PTR_OTHR_ITEM_OTHR_WH_NS) || mmStatus.startsWith(MM_CHK_PTR_WH_OTHER_CONFIG)
                || mmStatus.startsWith(MM_CHK_PTR_WH_OTHER_CONFIG_ITEM) || mmStatus.startsWith(MM_CHK_PTR_OTHER_WH_CONFIG_ITEM) || mmStatus.startsWith(MM_CHK_PTR_WH_OTHER_CONFIG_NSS)
                || mmStatus.startsWith(MM_CHK_PTR_WH_OTHER_CONFIG_ITEM_NSS) || mmStatus.startsWith(MM_CHK_PTR_OTHER_WH_CONFIG_ITEM_NSS) || mmStatus.startsWith(MM_CHK_PTR_WH_OTHER_CONFIG_NS)
                || mmStatus.startsWith(MM_CHK_PTR_WH_OTHER_CONFIG_ITEM_NS) || mmStatus.startsWith(MM_CHK_PTR_OTHER_WH_CONFIG_ITEM_NS)) {

            return new String[] {SERIAL_TRX_ERROR_DUPLICATE, SER_TRX_CMNT_NLZM2324E };

        } else if (mmStatus.startsWith(MM_CHK_PTR_IN_CUST) || mmStatus.startsWith(MM_CHK_PTR_IN_CUST_NSS) || mmStatus.startsWith(MM_CHK_PTR_IN_CUST_NS)) {

            return new String[] {SERIAL_TRX_ERROR_DUPLICATE, SER_TRX_CMNT_NLZM2318E };

        } else if (mmStatus.startsWith(MM_CHK_PTR_WH_NSS) || mmStatus.startsWith(MM_CHK_PTR_CONFIG_ITEM_OTHER_WH_NSS) || mmStatus.startsWith(MM_CHK_PTR_OTHER_WH_NSS) || mmStatus.startsWith(MM_CHK_PTR_NO_IB_NSS)
                || mmStatus.startsWith(MM_CHK_PTR_WH_ITEM_NSS)) {

            return new String[] {SERIAL_TRX_ERROR_NOT_SCHEDULE, SER_TRX_CMNT_NLBM1302W };

        } else if (mmStatus.endsWith(ZYPConstant.FLG_OFF_N)) {
            // QC#20008 Add
            return new String[] {SERIAL_TRX_ERROR_STK_STS_CD, SER_TRX_CMNT_NLZM2414E };
        }

        // No Error
        return null;
    }

    /**
     * checkDiposalMachMstr
     * @param sceOrdTpCd String
     * @return boolean
     */
    private static boolean checkDiposalMachMstr(String sceOrdTpCd) {

        if (SCE_ORD_TP.REFURBISH_EXPENSE.equals(sceOrdTpCd) || SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(sceOrdTpCd) || SCE_ORD_TP.DISPOSAL.equals(sceOrdTpCd)) {

            return true;
        }

        return false;
    }

    /**
     * checkUpdateMachMstr
     * @param sceOrdTpCd String
     * @return boolean
     */
    private static boolean checkUpdateMachMstr(String sceOrdTpCd) {

        if (SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd) || SCE_ORD_TP.KITTING.equals(sceOrdTpCd) || SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd)) {

            return true;
        }

        return false;
    }

    /**
     * callAllocOffMachMstr
     * @param glblCmpyCd String
     * @param slsDt String
     * @param svcMachMstrPkList ArrayList<BigDecimal>
     * @param onBatch ONBATCH_TYPE
     * @return ArrayList<MachineMasterResultBean>
     */
    public static ArrayList<MachineMasterResultBean> callAllocOffMachMstr(String glblCmpyCd, String slsDt, ArrayList<BigDecimal> svcMachMstrPkList, ONBATCH_TYPE onBatch) {

        ArrayList<NSZC001001PMsg> allocOffList = new ArrayList<NSZC001001PMsg>();

        for (int i = 0; i < svcMachMstrPkList.size(); i++) {

            NSZC001001PMsg machMstrPmsg = new NSZC001001PMsg();
            ZYPEZDItemValueSetter.setValue(machMstrPmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(machMstrPmsg.slsDt, slsDt);
            ZYPEZDItemValueSetter.setValue(machMstrPmsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);
            ZYPEZDItemValueSetter.setValue(machMstrPmsg.svcMachMstrPk, svcMachMstrPkList.get(i));
            allocOffList.add(machMstrPmsg);
        }

        // Execute
        allocOffList = callMachineMasterUpdateAPI(allocOffList, onBatch);

        // Error check
        ArrayList<MachineMasterResultBean> resultBean = convertPMsgError(allocOffList);

        if (!resultBean.isEmpty()) {

            return resultBean;
        }

        // No Error
        return new ArrayList<MachineMasterResultBean>();
    }

    /**
     * callRelCompMachMstr
     * @param glblCmpyCd String
     * @param slsDt String
     * @param compList ArrayList<Map<String, Object>>
     * @param onBatch ONBATCH_TYPE
     * @return ArrayList<MachineMasterResultBean>
     */
    public static ArrayList<MachineMasterResultBean> callRelCompMachMstr(String glblCmpyCd, String slsDt, ArrayList<Map<String, Object>> compList, ONBATCH_TYPE onBatch) {

        ArrayList<NSZC001001PMsg> relCompList = new ArrayList<NSZC001001PMsg>();

        for (Map<String, Object> map : compList) {

            String oldMdseCd = (String) map.get("CMPT_MDSE_CD");
            String newMdseCd = (String) map.get("RMNF_TO_CMPT_MDSE_CD");
            String oldSerNum = (String) map.get("CMPT_SER_NUM");
            String newSerNum = (String) map.get("RMNF_TO_CMPT_SER_NUM");

            if (isSameVal(oldMdseCd, newMdseCd) && isSameVal(oldSerNum, newSerNum)) {

                continue;
            }

            NSZC001001PMsg machMstrPmsg = new NSZC001001PMsg();
            ZYPEZDItemValueSetter.setValue(machMstrPmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(machMstrPmsg.slsDt, slsDt);
            ZYPEZDItemValueSetter.setValue(machMstrPmsg.xxModeCd, ProcessMode.UPDATE_WAREHOUSE.code);
            ZYPEZDItemValueSetter.setValue(machMstrPmsg.svcConfigMstrPk, (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(machMstrPmsg.svcMachMstrPk, (BigDecimal) map.get("SVC_MACH_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(machMstrPmsg.serNum, newSerNum);
            ZYPEZDItemValueSetter.setValue(machMstrPmsg.mdseCd, newMdseCd);
            ZYPEZDItemValueSetter.setValue(machMstrPmsg.svcMachMstrStsCd, (String) map.get("SVC_MACH_MSTR_STS_CD"));
            ZYPEZDItemValueSetter.setValue(machMstrPmsg.stkStsCd, STK_STS.GOOD);
            ZYPEZDItemValueSetter.setValue(machMstrPmsg.effFromDt, (String) map.get("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(machMstrPmsg.svcMachUsgStsCd, (String) map.get("SVC_MACH_USG_STS_CD"));
            ZYPEZDItemValueSetter.setValue(machMstrPmsg.svcMachQty, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(machMstrPmsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(machMstrPmsg.curLocNum, (String) map.get("CUR_LOC_NUM"));

            if (!isSameVal(oldSerNum, newSerNum)) {

                ZYPEZDItemValueSetter.setValue(machMstrPmsg.oldSerNum, oldSerNum);
            }

            relCompList.add(machMstrPmsg);
        }

        // Execute
        relCompList = callMachineMasterUpdateAPI(relCompList, onBatch);

        // Error check
        ArrayList<MachineMasterResultBean> resultBean = convertPMsgError(relCompList);

        if (!resultBean.isEmpty()) {

            return resultBean;
        }

        // No Error
        return new ArrayList<MachineMasterResultBean>();
    }

    /**
     * isSameVal
     * @param val1 String
     * @param val2 String
     * @return boolean true : Same, false : def
     */
    private static boolean isSameVal(String val1, String val2) {

        if (!ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return true;

        } else if (!ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return false;

        } else if (val1.equals(val2)) {

            return true;
        }

        return false;
    }

    /**
     * callNewCompMachMstr
     * @param glblCmpyCd String
     * @param slsDt String
     * @param mainMach Map<String, Object>
     * @param compList ArrayList<Map<String, Object>>
     * @param onBatch ONBATCH_TYPE
     * @return ArrayList<MachineMasterResultBean>
     */
    public static ArrayList<MachineMasterResultBean> callNewCompMachMstr(String glblCmpyCd, String slsDt, Map<String, Object> mainMach, ArrayList<Map<String, Object>> compList, ONBATCH_TYPE onBatch) {

        String oldSerNum = (String) mainMach.get("CMPT_SER_NUM");
        String newSerNum = (String) mainMach.get("RMNF_TO_CMPT_SER_NUM");

        NSZC001001PMsg newComp = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(newComp.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(newComp.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(newComp.xxModeCd, ProcessMode.UPDATE_WAREHOUSE.code);
        ZYPEZDItemValueSetter.setValue(newComp.svcConfigMstrPk, (BigDecimal) mainMach.get("SVC_CONFIG_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(newComp.svcMachMstrPk, (BigDecimal) mainMach.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(newComp.serNum, newSerNum);
        ZYPEZDItemValueSetter.setValue(newComp.mdseCd, (String) mainMach.get("RMNF_TO_CMPT_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(newComp.svcMachMstrStsCd, (String) mainMach.get("SVC_MACH_MSTR_STS_CD"));
        ZYPEZDItemValueSetter.setValue(newComp.stkStsCd, STK_STS.GOOD);
        ZYPEZDItemValueSetter.setValue(newComp.effFromDt, (String) mainMach.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(newComp.svcMachUsgStsCd, (String) mainMach.get("SVC_MACH_USG_STS_CD"));
        ZYPEZDItemValueSetter.setValue(newComp.svcMachQty, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(newComp.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(newComp.curLocNum, (String) mainMach.get("CUR_LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(newComp.svcMachTpCd, SVC_MACH_TP.MACHINE);

        if (!isSameVal(oldSerNum, newSerNum)) {

            ZYPEZDItemValueSetter.setValue(newComp.oldSerNum, oldSerNum);
        }

        // Set component(Main Machine)
        int index = 0;
        ZYPEZDItemValueSetter.setValue(newComp.xxCmptMachList.no(index).svcMachMstrPk, (BigDecimal) mainMach.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(newComp.xxCmptMachList.no(index).mdseCd, (String) mainMach.get("RMNF_TO_CMPT_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(newComp.xxCmptMachList.no(index).svcMachTpCd, SVC_MACH_TP.MACHINE);
        index++;

        for (Map<String, Object> map : compList) {

            ZYPEZDItemValueSetter.setValue(newComp.xxCmptMachList.no(index).svcMachMstrPk, (BigDecimal) map.get("SVC_MACH_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(newComp.xxCmptMachList.no(index).mdseCd, (String) map.get("RMNF_TO_CMPT_MDSE_CD"));

            if (ZYPConstant.FLG_ON_Y.equals((String) map.get("RMV_CONFIG_FLG"))) {

                ZYPEZDItemValueSetter.setValue(newComp.xxCmptMachList.no(index).effThruDt, slsDt);
            }

            ZYPEZDItemValueSetter.setValue(newComp.xxCmptMachList.no(index).svcMachTpCd, SVC_MACH_TP.ACCESSORY);

            index++;
        }

        ArrayList<NSZC001001PMsg> apiParamList = new ArrayList<NSZC001001PMsg>();
        apiParamList.add(newComp);

        newComp.xxCmptMachList.setValidCount(index);

        // Execute
        apiParamList = callMachineMasterUpdateAPI(apiParamList, onBatch);

        // Error check
        ArrayList<MachineMasterResultBean> resultBean = convertPMsgError(apiParamList);

        if (!resultBean.isEmpty()) {

            return resultBean;
        }

        // No Error
        return new ArrayList<MachineMasterResultBean>();
    }
}
