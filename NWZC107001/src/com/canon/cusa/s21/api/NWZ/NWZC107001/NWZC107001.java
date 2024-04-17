/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC107001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDItemAttribute;
import parts.common.EZDMsg;
import parts.common.EZDPBigDecimalItem;
import parts.common.EZDPDateItem;
import parts.common.EZDPStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.dbcommon.EZDTBLAccessor;
//import business.db.ATP_BAT_WRKTMsg;
//import business.db.ATP_BAT_WRKTMsgArray;
//import business.db.CAL_RELNTMsg;
//import business.db.CAL_RELNTMsgArray;
import business.db.CMPSNTMsg;
import business.db.CNTYTMsg;
import business.db.INVTYTMsg;
import business.db.INVTYTMsgArray;
import business.db.MDSETMsg;
//import business.db.MDSE_WH_CONDTMsg;
import business.db.OTBD_CARR_VTMsg;
import business.db.OTBD_CARR_VTMsgArray;
//import business.db.ROSS_ORDTMsg;
//import business.db.ROSS_ORDTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.db.TRX_SRC_TPTMsg;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
//import business.db.WHTMsg;
//import business.db.WHTMsgArray;
import business.parts.NWZC002001PMsg;
//2017/04/12 S21_NA#Review structure Lv.1 Del Start
//import business.parts.NWZC103001PMsg;
//import business.parts.NWZC103002PMsg;
//2017/04/12 S21_NA#Review structure Lv.1 Del End
import business.parts.NWZC104001PMsg;
import business.parts.NWZC104002PMsg;
import business.parts.NWZC107001PMsg;
//import business.parts.NWZC108001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC002001.NWZC002001;
//import com.canon.cusa.s21.api.NWZ.NWZC103001.NWZC103001;
import com.canon.cusa.s21.api.NWZ.NWZC104001.NWZC104001;
//import com.canon.cusa.s21.api.NWZ.NWZC108001.NWZC108001;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Export;
import com.canon.cusa.s21.common.NWX.NWXC002007.NWXC002007BackOrder;
import com.canon.cusa.s21.common.NWX.NWXC002007.NWXC002007BackOrderParam;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXCmpsnTMsgThreadLocalCache;
//import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
//import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_SUB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
//import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DIST_PLN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_TO;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HARD_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
//import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
//import com.canon.cusa.s21.framework.log.S21AbendException;


/**
 * <pre>
 * Allocation for non CPO API(NWZC107001) 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/23/2009   Fujitsu         A.Suda          Create          N/A
 * 08/26/2009   Fujitsu         A.Suda          Update          38
 * 09/04/2009   Fujitsu         A.Suda          Update          35
 * 11/17/2009   Fujitsu         A.Suda          Update          Export 
 * 12/11/2009   Fujitsu         R.Watanabe      Update          1976
 * 12/21/2009   Fujitsu         R.Watanabe      Update          2783
 * 04/14/2010   Fujitsu         A.Suda          Update          5676
 * 04/21/2010   Fujitsu         A.Suda          Update          5879
 * 05/13/2010   Fujitsu         A.Suda          Update          5359
 * 05/20/2010   Fujitsu         A.Suda          Update          5902
 * 06/02/2010   Fujitsu         A.Suda          Update          6758
 * 08/27/2010   Fujitsu         A.Suda          Update          203(All2)
 * 2017/04/12   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.1
 * 2017/08/03   Fujitsu         T.Murai         Update          S21_NA#5872(L3#001)
 *</pre>
 */

public class NWZC107001 extends S21ApiCommonBase {

    /**
     * Request Type 1: New Allocation
     */
    public static final String REQ_TP_NEW = "1";

    /**
     * Request Type 2: Cancel
     */
    public static final String REQ_TP_CANCEL = "2";

    /**
     * Request Type 3: New Order Information
     */
    public static final String REQ_TP_ODR_INFOMATION = "3";

    /** SQL Accessor */
    private S21SsmBatchClient ssmBatchClient = null;

    // **********************
    // Message
    // **********************
    public static final String NWZM0188E = "NWZM0188E";
    public static final String NWZM0453E = "NWZM0453E";
    public static final String NWZM0014E = "NWZM0014E";
    public static final String NWZM0027E = "NWZM0027E";
    public static final String NWZM0089E = "NWZM0089E";
    public static final String NWZM0043E = "NWZM0043E";
    public static final String NWZM0209E = "NWZM0209E";
    public static final String NWZM0189E = "NWZM0189E";
    public static final String NWZM0454E = "NWZM0454E";
    public static final String NWZM0455E = "NWZM0455E";
    public static final String NWZM0456E = "NWZM0456E";
    public static final String NWZM0021E = "NWZM0021E";
    public static final String NWZM0047E = "NWZM0047E";
    public static final String NWZM0400E = "NWZM0400E";
    public static final String NWZM0174E = "NWZM0174E";
    public static final String NWZM0185E = "NWZM0185E";
    public static final String NWZM0208E = "NWZM0208E";
    public static final String NWZM0457E = "NWZM0457E";
    public static final String NWZM0358E = "NWZM0358E";
    public static final String NWZM0175E = "NWZM0175E";
    public static final String NWZM0066E = "NWZM0066E";
    public static final String NWZM0458E = "NWZM0458E";
    public static final String NWZM0049E = "NWZM0049E";
    public static final String NWZM0196E = "NWZM0196E";
    public static final String NWZM0178E = "NWZM0178E";
    public static final String NWZM0460E = "NWZM0460E";
    public static final String NWZM0461E = "NWZM0461E";
    public static final String NWZM0179E = "NWZM0179E";
    public static final String NWZM0180E = "NWZM0180E";
    public static final String NWZM0181E = "NWZM0181E";
    public static final String NWZM0346E = "NWZM0346E";
    public static final String NWZM0446E = "NWZM0446E";
    public static final String NWZM0364E = "NWZM0364E";
    public static final String NWZM0447I = "NWZM0447I";
    public static final String NWZM0448E = "NWZM0448E";
    public static final String NWZM0449E = "NWZM0449E";
    public static final String NWZM0450E = "NWZM0450E";
    public static final String NWZM0366I = "NWZM0366I";
    public static final String NWZM0472E = "NWZM0472E";
    public static final String NWZM0451E = "NWZM0451E";
    public static final String NWZM0452E = "NWZM0452E";
    public static final String NWZM0067E = "NWZM0067E";
    public static final String NWZM0075E = "NWZM0075E";
    public static final String NWZM0078E = "NWZM0078E";
    public static final String NWZM0283E = "NWZM0283E";
    public static final String NWZM0082E = "NWZM0082E";
    public static final String NWZM0319E = "NWZM0319E";
    public static final String NWZM0347E = "NWZM0347E";
    public static final String NWZM0023E = "NWZM0023E";
    public static final String NWZM0514E = "NWZM0514E";
    public static final String NWZM0497E = "NWZM0497E";
    public static final String NWZM0076E = "NWZM0076E";
    public static final String NWZM0817E = "NWZM0817E";
    public static final String NWZM0673E = "NWZM0673E";
    public static final String NWZM0948E = "NWZM0948E";
    public static final String NWZM0949E = "NWZM0949E";
    public static final String NWZM0950E = "NWZM0950E";


    // Return Code of S21ApiTBLAccessor
    /** Normal end */
    private static final String RTNCD_NORMAL_END = EZDTBLAccessor.RTNCD_NORMAL;

    /** Oracle Sequence：SHPG_PLN_NUM sequence name */
    private static final String SHPG_PLN_SQ = "SHPG_PLN_SQ";

    /** xxPrtlAcptFlg -ON */
    private static final String PRTL_OK = "1";

    /** xxPrtlAcptFlg -NG */
    private static final String PRTL_NG = "0";

    /** xxItemFlipAcptFlg -NG */
    private static final String ITEM_FLIP_NG = "0";

    /** xxWHFlipAcptFlg -NG */
    private static final String WH_FLIP_NG = "0";

    /** OrderTakenMdseCd -NG */
    private static final String ODR_TAKEN_MDSE_CD_NO = "0";

    /** HARD_ALLOC_REQ_TP_NEW */
    private static final String HARD_ALLOC_REQ_TP_NEW = "1";

    /** TRX_LINE_SUB_NUM_SET */
    private static final String TRX_LINE_SUB_NUM_SET = "000";

    /** MessageID ERROR */
    private static final String ERROR_MESSAGE_CD = "E";

    /**
     * Initial processing
     */
    public NWZC107001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * NWZC107001 Allocation for Non CPO API
     * 
     * <pre>
     * Order information other than the wholesale order are registered as shipping plan information.
     * In addition, the Soft/HardAllocation execution is controlled for the Allocating execution demand.
     * </pre>
     *
     * @param param NWZC107001PMsg Interface
     * @param onBatchType onBatchType
     */

    public void execute(final NWZC107001PMsg param, final ONBATCH_TYPE onBatchType) {
        writeDebugLog("execute<Start>");

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        writeDebugLog("*****");
        writeDebugLog("<ParameterInfomation Log :" + msgMap.toString() + ">");
        writeDebugLog("*****");

        MDSETMsg mdseMsg = this.initProcess(msgMap, param);

        if (hasMessage(msgMap)) {
            msgMap.flush();
            return;
        }

        TRX_SRC_TPTMsg trxSrcTpMsg = this.searchTrxSrcTp(msgMap);

        if (trxSrcTpMsg == null || ZYPConstant.FLG_OFF_N.equals(trxSrcTpMsg.notWsAllocFlg.getValue())) {
            msgMap.addXxMsgId(NWZM0446E);
            msgMap.flush();
            return;
        }

        String rqstTpCd = param.xxRqstTpCd.getValue();
        String expdShipDt = "";

        if (REQ_TP_CANCEL.equals(rqstTpCd)) {
            // execute CancelProcess

            this.cancelProcess(msgMap, onBatchType);

        } else {

            if (MDSE_TP.SET.equals(mdseMsg.mdseTpCd.getValue())) {
                // setItem slsDate
                expdShipDt = param.slsDt.getValue();

            } else {
                if (TRX_SRC_TP.RETAIL.equals(param.trxSrcTpCd.getValue()) || ZYPConstant.FLG_ON_Y.equals(trxSrcTpMsg.softAllocFlg.getValue())) {
                    expdShipDt = this.getExpdShipDt(msgMap, onBatchType, mdseMsg, trxSrcTpMsg);
                    if (hasMessage(msgMap)) {
                        msgMap.flush();
                        return;
                    }
                }
            }

            if (REQ_TP_NEW.equals(rqstTpCd)) {
                // execute NewProcess

                this.newOrderInfoProcess(msgMap, mdseMsg, expdShipDt, onBatchType);

                if (hasMessage(msgMap)) {
                    msgMap.flush();
                    return;
                }

                this.newProcess(msgMap, trxSrcTpMsg, mdseMsg, expdShipDt, onBatchType);

            } else if (REQ_TP_ODR_INFOMATION.equals(rqstTpCd)) {
                // execute backOrderInfoProcess

                this.backOrderInfoProcess(msgMap, mdseMsg, expdShipDt, onBatchType);

            } else {
                msgMap.addXxMsgId(NWZM0209E);
            }
        }
        msgMap.flush();
        writeDebugLog("execute<End>");
    }

    /**
     * NWZC107001 Allocation for Non CPO API (Changeable list type)
     * @param params NWZC107001PMsg Interface
     * @param onBatchType onBatchType
     */

    public void execute(final List<NWZC107001PMsg> params, final ONBATCH_TYPE onBatchType) {
        writeDebugLog("execute<List><Start>");

        int paramsize = params.size();
        S21ApiMessageMap[] newAllocList = new S21ApiMessageMap[paramsize];
        S21ApiMessageMap[] boInfoList = new S21ApiMessageMap[paramsize];
        List<S21ApiMessageMap> setItemList = new ArrayList<S21ApiMessageMap>();
        List<S21ApiMessageMap> cancelList = new ArrayList<S21ApiMessageMap>();
        MDSETMsg[] newMdseMsgList = new MDSETMsg[paramsize];
        MDSETMsg[] boMdseMsgList = new MDSETMsg[paramsize];
        int newAllocListIndex = 0;
        int boInfoListIndex = 0;

        for (NWZC107001PMsg param : params) {

            S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
            writeDebugLog("*****");
            writeDebugLog("<ParameterInfomation Log :" + msgMap.toString() + ">");
            writeDebugLog("*****");

            MDSETMsg mdseMsg = this.initProcess(msgMap, param);

            if (hasMessage(msgMap)) {
                msgMap.flush();
                if (isErrMessage(msgMap)) {
                    // errorMessage end
                    return;
                } else {
                    // infomationMessage continue
                    continue;
                }
            }

            if (REQ_TP_CANCEL.equals(param.xxRqstTpCd.getValue())) {

                cancelList.add(msgMap);

            } else if (REQ_TP_NEW.equals(param.xxRqstTpCd.getValue())) {

                newAllocList[newAllocListIndex] = msgMap;
                newMdseMsgList[newAllocListIndex] = mdseMsg;
                newAllocListIndex++;

            } else {

                boInfoList[boInfoListIndex] = msgMap;
                boMdseMsgList[boInfoListIndex] = mdseMsg;
                boInfoListIndex++;

            }
        }

        //Execute New Allocation

        for (int newProcessIndex = 0; newProcessIndex < newAllocListIndex; newProcessIndex++) {

            S21ApiMessageMap newMsgMap = newAllocList[newProcessIndex];
            MDSETMsg newMdseMsg = newMdseMsgList[newProcessIndex];

            TRX_SRC_TPTMsg trxSrcTpMsg = this.getTrxSrcTp(newMsgMap);

            if (hasMessage(newMsgMap)) {
                newMsgMap.flush();
              return;
            }

            String expdShipDt = this.getExpdShipDt(newMsgMap, onBatchType, newMdseMsg, trxSrcTpMsg);

            if (hasMessage(newMsgMap)) {
                newMsgMap.flush();
              return;
            }

            this.newOrderInfoProcess(newMsgMap, newMdseMsg, expdShipDt, onBatchType);

            if (hasMessage(newMsgMap)) {
                newMsgMap.flush();
              return;
            }

            this.newProcess(newMsgMap, trxSrcTpMsg, newMdseMsg, expdShipDt, onBatchType);

            newMsgMap.flush();
        }

        //Execute B/O Infomation
        for (int boProcessIndex = 0; boProcessIndex < boInfoListIndex; boProcessIndex++) {

            S21ApiMessageMap boMsgMap = boInfoList[boProcessIndex];
            MDSETMsg boMdseMsg = boMdseMsgList[boProcessIndex];

            TRX_SRC_TPTMsg trxSrcTpMsg = this.getTrxSrcTp(boMsgMap);

            if (hasMessage(boMsgMap)) {
                boMsgMap.flush();
              return;
            }

            String expdShipDt = this.getExpdShipDt(boMsgMap, onBatchType, boMdseMsg, trxSrcTpMsg);

            if (hasMessage(boMsgMap)) {
                boMsgMap.flush();
              return;
            }

            this.backOrderInfoProcess(boMsgMap, boMdseMsg, expdShipDt, onBatchType);

            boMsgMap.flush();

        }

        //Execute Cancel Allocation
        for (S21ApiMessageMap canMsgMap : cancelList) {

            NWZC107001PMsg param = (NWZC107001PMsg) canMsgMap.getPmsg();

            this.cancelProcess(canMsgMap, onBatchType);
            canMsgMap.flush();

            if (TRX_LINE_SUB_NUM_SET.equals(param.trxLineSubNum.getValue())) {
                setItemList.add(canMsgMap);
            }

        }

//      // execute Set Cancel Process
//      if (!setItemList.isEmpty()) {
//
//          for (S21ApiMessageMap setmsgMap : setItemList) {
//
//              this.updateSetItem(setmsgMap, cancelList);
//              setmsgMap.flush();
//
//              if (hasMessage(setmsgMap)) {
//                  return;
//              }
//          }
//
//      }


//        S21ApiMessageMap[] allocatedList = new S21ApiMessageMap[paramsize];
//        MDSETMsg[] mdseMsgList = new MDSETMsg[paramsize];
//        TRX_SRC_TPTMsg[] trxSrcTpMsgList = new TRX_SRC_TPTMsg[paramsize];
//        String[] expdShipDtList = new String[paramsize];
//        int allocatedIndex = 0;
//        String expdShipDt = "";
//        List<S21ApiMessageMap> setItemList = new ArrayList<S21ApiMessageMap>();

//        for (NWZC107001PMsg param : params) {
//
//            S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
//            writeDebugLog("*****");
//            writeDebugLog("<ParameterInfomation Log :" + msgMap.toString() + ">");
//            writeDebugLog("*****");
//
//            MDSETMsg mdseMsg = this.initProcess(msgMap, param);
//
//            if (hasMessage(msgMap)) {
//                msgMap.flush();
//                if (isErrMessage(msgMap)) {
//                    // errorMessage end
//                    return;
//                } else {
//                    // infomationMessage continue
//                    continue;
//                }
//            }
//
//            TRX_SRC_TPTMsg trxSrcTpMsg = this.searchTrxSrcTp(msgMap);
//
//            if (trxSrcTpMsg == null || ZYPConstant.FLG_OFF_N.equals(trxSrcTpMsg.notWsAllocFlg.getValue())) {
//                msgMap.addXxMsgId(NWZM0446E);
//                msgMap.flush();
//                return;
//            }
//
//            String rqstTpCd = param.xxRqstTpCd.getValue();
//
//            if (REQ_TP_CANCEL.equals(rqstTpCd)) {
//
//                // execute CancelProcess
//                this.cancelProcess(msgMap, onBatchType);
//                msgMap.flush();
//
//                if (TRX_LINE_SUB_NUM_SET.equals(param.trxLineSubNum.getValue())) {
//                    setItemList.add(msgMap);
//                }
//
//            } else {
//
//                if (MDSE_TP.SET.equals(mdseMsg.mdseTpCd.getValue())) {
//                    // setItem slsDate
//                    expdShipDt = param.slsDt.getValue();
//
//                } else {
//
//                    if (TRX_SRC_TP.RETAIL.equals(param.trxSrcTpCd.getValue()) || ZYPConstant.FLG_ON_Y.equals(trxSrcTpMsg.softAllocFlg.getValue())) {
//                        expdShipDt = this.getExpdShipDt(msgMap, onBatchType);
//                        if (hasMessage(msgMap)) {
//                            msgMap.flush();
//                            return;
//                        }
//                    }
//
//                }
//
//                mdseMsgList[allocatedIndex] = mdseMsg;
//                expdShipDtList[allocatedIndex] = expdShipDt;
//                trxSrcTpMsgList[allocatedIndex] = trxSrcTpMsg;
//
//                if (REQ_TP_NEW.equals(rqstTpCd)) {
//                    this.newOrderInfoProcess(msgMap, mdseMsg, expdShipDt, onBatchType);
//
//                } else if (REQ_TP_ODR_INFOMATION.equals(rqstTpCd)) {
//                    this.backOrderInfoProcess(msgMap, mdseMsg, expdShipDt, onBatchType);
//                    msgMap.flush();
//                }
//
//                if (hasMessage(msgMap)) {
//                    msgMap.flush();
//                    return;
//                }
//            }
//
//            allocatedList[allocatedIndex] = msgMap;
//            allocatedIndex++;
//
//        }
//
//        // execute Set Cancel Process
//        if (!setItemList.isEmpty()) {
//
//            for (S21ApiMessageMap setmsgMap : setItemList) {
//
//                this.setCancelProcess(setmsgMap);
//                setmsgMap.flush();
//
//                if (hasMessage(setmsgMap)) {
//                    return;
//                }
//            }
//
//        }
//
//        // execute New Process
//        for (int newprocessIndex = 0; newprocessIndex < allocatedIndex; newprocessIndex++) {
//
//            S21ApiMessageMap msgMap = allocatedList[newprocessIndex];
//            NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//            String rqstTpCd = param.xxRqstTpCd.getValue();
//
//            if (REQ_TP_NEW.equals(rqstTpCd)) {
//
//                MDSETMsg mdseMsg = mdseMsgList[newprocessIndex];
//                TRX_SRC_TPTMsg trxSrcTpMsg = trxSrcTpMsgList[newprocessIndex];
//                expdShipDt = expdShipDtList[newprocessIndex];
//
//                this.newProcess(msgMap, trxSrcTpMsg, mdseMsg, expdShipDt, onBatchType);
//                msgMap.flush();
//
//                if (hasMessage(msgMap)) {
//                    if (isErrMessage(msgMap)) {
//                        // errorMessage end
//                        return;
//                    } else {
//                        // infomationMessage continue
//                        continue;
//                    }
//                }
//
//            }
//
//        }
//
//        writeDebugLog("execute<List><End>");
    }


    private TRX_SRC_TPTMsg getTrxSrcTp(S21ApiMessageMap msgMap) {

        TRX_SRC_TPTMsg trxSrcTpMsg = this.searchTrxSrcTp(msgMap);

        if (trxSrcTpMsg == null || ZYPConstant.FLG_OFF_N.equals(trxSrcTpMsg.notWsAllocFlg.getValue())) {
              msgMap.addXxMsgId(NWZM0446E);
        }

        return trxSrcTpMsg;

    }

    private String getExpdShipDt(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType, final MDSETMsg mdseMsg, final TRX_SRC_TPTMsg trxSrcTpMsg) {

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
        String expdShipDt = "";

        if (MDSE_TP.SET.equals(mdseMsg.mdseTpCd.getValue())) {
          // setItem slsDate
          expdShipDt = param.slsDt.getValue();

      } else {

          if (TRX_SRC_TP.RETAIL.equals(param.trxSrcTpCd.getValue()) || ZYPConstant.FLG_ON_Y.equals(trxSrcTpMsg.softAllocFlg.getValue())) {

              if (hasValue(param.rsdDt)) {

                  expdShipDt = param.rsdDt.getValue();

              } else {
                  expdShipDt = this.execLeadtimeCalcAPI(msgMap, onBatchType);

              }
          }

      }
        return expdShipDt;

    }


    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//    private void updateSetItem(S21ApiMessageMap msgMap, List<S21ApiMessageMap> cancelList) {
//        writeDebugLog("updateSetItem<Start>");
//
//        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//        BigDecimal setNeedRossOdrQty = BigDecimal.ZERO;
//        BigDecimal setNeedQty = BigDecimal.ZERO;
//
//        ROSS_ORDTMsgArray rossOdrMsgArray = this.searchRossOdrComp(msgMap);
//        if (rossOdrMsgArray.getValidCount() == 0) {
//            msgMap.addXxMsgId(NWZM0450E);
//            return;
//        }
//
//        SHPG_PLNTMsgArray setshpgPlnMsgArray = this.searchShpgPlanForShpgSts(msgMap, SHPG_STS.VALIDATED);
//
//        if (setshpgPlnMsgArray.getValidCount() == 0) {
//            return;
//        }
//
//        ROSS_ORDTMsg setrossOdrMsg = this.searchRossOdrForUpdate(msgMap);
//
//        if (setrossOdrMsg == null) {
//            msgMap.addXxMsgId(NWZM0450E);
//            return;
//        }
//
//        for (int i = 0; i < rossOdrMsgArray.getValidCount(); i++) {
//
//            BigDecimal ordQty = rossOdrMsgArray.no(i).ordQty.getValue();
//
//            if (ordQty.compareTo(BigDecimal.ZERO) == 0) {
//                continue;
//
//            } else {
//
//                String compMdseCd = this.getCompMdseCd(msgMap, rossOdrMsgArray.no(i).rossDtlLineSubNum.getValue());
//                if (hasMessage(msgMap)) {
//                    return;
//                }
//
//                BigDecimal compQty = this.getCompQty(param.glblCmpyCd.getValue(), setshpgPlnMsgArray.no(0).mdseCd.getValue(), compMdseCd, param.slsDt.getValue());
//                setNeedRossOdrQty = ordQty.divide(compQty, 0, BigDecimal.ROUND_HALF_UP);
//                setNeedQty = setNeedRossOdrQty;
//                break;
//            }
//        }
//
//        if (setNeedRossOdrQty.compareTo(BigDecimal.ZERO) == 0) {
//            param.allocQty.setValue(setrossOdrMsg.ordQty.getValue());
//            setshpgPlnMsgArray.no(0).shipPlnCancDt.setValue(param.slsDt.getValue());
//            setshpgPlnMsgArray.no(0).shipPlnCancFlg.setValue(ZYPConstant.FLG_ON_Y);
//            setshpgPlnMsgArray.no(0).shpgStsCd.setValue(SHPG_STS.CANCELLED);
//            setshpgPlnMsgArray.no(0).avalHardAllocQty.setValue(BigDecimal.ZERO);
//            setshpgPlnMsgArray.no(0).avalSoftAllocQty.setValue(BigDecimal.ZERO);
//            setshpgPlnMsgArray.no(0).avalPoQty.setValue(BigDecimal.ZERO);
//            setshpgPlnMsgArray.no(0).avalSoQty.setValue(BigDecimal.ZERO);
//            setshpgPlnMsgArray.no(0).softAllocQty.setValue(BigDecimal.ZERO);
//            setNeedQty = setshpgPlnMsgArray.no(0).ordQty.getValue();
//        } else {
//            param.allocQty.setValue(setrossOdrMsg.ordQty.getValue().subtract(setNeedRossOdrQty));
//        }
//
//        setshpgPlnMsgArray.no(0).softAllocQty.setValue(BigDecimal.ZERO);
//        setshpgPlnMsgArray.no(0).ordQty.setValue(setNeedQty);
//        setrossOdrMsg.ordQty.setValue(setNeedRossOdrQty);
//        this.setShpgPlanTotalAmt(setshpgPlnMsgArray.no(0), setNeedQty);
//
//        this.updateShpgPlan(msgMap, setshpgPlnMsgArray.no(0));
//
//        this.updateRossOdr(msgMap, setrossOdrMsg);

//        NWZC107001PMsg setParam = (NWZC107001PMsg) msgMap.getPmsg();
//
//        BigDecimal cancelImpossibleQty = getCancelImpossibleSoftAllocQty(setParam);
//
//        for (S21ApiMessageMap canMsgMap : cancelList) {
//
//            NWZC107001PMsg param = (NWZC107001PMsg) canMsgMap.getPmsg();
//
//            boolean isAgreeTrxHdrNumFlg = setParam.trxHdrNum.getValue().equals(param.trxHdrNum.getValue());
//            boolean isAgreeTrxLineNumFlg = setParam.trxLineNum.getValue().equals(param.trxLineNum.getValue());
//
//            if (isAgreeTrxHdrNumFlg && isAgreeTrxLineNumFlg) {
//
//                ROSS_ORDTMsgArray rossOdrMsgArray = this.searchRossOdrComp(msgMap);
//                if (rossOdrMsgArray.getValidCount() == 0) {
//                    msgMap.addXxMsgId(NWZM0450E);
//                    return;
//                }
//
//
//                if (cancelImpossibleQty.compareTo(BigDecimal.ZERO) == 0) {
//
//                    allCancelSoftAllocation(canMsgMap);
//
//                } else {
//
//                    partialCancelSoftAllocation(canMsgMap, cancelImpossibleQty);
//                }
//            }
//        }
//
//        writeDebugLog("updateSetItem<End>");
//    }
    // 2017/04/12 S21_NA#Review structure Lv.1 Del End

    private BigDecimal getCancelImpossibleHardAllocQty(NWZC107001PMsg param) {
        writeDebugLog("getCancelImpossibleHardAllocQty<Start>");

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("trxSrcTpCd", param.trxSrcTpCd.getValue());
        queryParam.put("trxHdrNum", param.trxHdrNum.getValue());
        queryParam.put("trxLineNum", param.trxLineNum.getValue());
        queryParam.put("trxLineSubNum", TRX_LINE_SUB_NUM_SET);
        queryParam.put("soPtintFlg", ZYPConstant.FLG_ON_Y);

        List<Map<String, Object>> cancelImpossibleHardAllocList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCancelImpossibleHardAlloc", queryParam);

        Map<String, BigDecimal> cancelImpossibleMap = new HashMap<String, BigDecimal>();

        for (Map<String, Object> cancelImpossibleHardAlloc : cancelImpossibleHardAllocList) {

            String childMdseCd = (String) cancelImpossibleHardAlloc.get("MDSE_CD");
            String prntMdseCd = (String) cancelImpossibleHardAlloc.get("SET_MDSE_CD");

            String trxLineNum = (String)cancelImpossibleHardAlloc.get("TRX_LINE_NUM");
            String trxLineSubNum = (String)cancelImpossibleHardAlloc.get("TRX_LINE_SUB_NUM");
            String key = trxLineNum + trxLineSubNum;

            BigDecimal compQty = getCompQty(param, prntMdseCd, childMdseCd);
            BigDecimal cancelImpossibleQty = (BigDecimal) cancelImpossibleHardAlloc.get("ORD_QTY");

            if (BigDecimal.ZERO.compareTo(compQty) != 0) {

                cancelImpossibleQty = cancelImpossibleQty.divide(compQty, 0, BigDecimal.ROUND_HALF_UP);
            }

            if (cancelImpossibleMap.containsKey(key)) {
                cancelImpossibleMap.put(key, cancelImpossibleQty.add(cancelImpossibleMap.get(key)));
            } else {
                cancelImpossibleMap.put(key, cancelImpossibleQty);
            }
        }

        BigDecimal cancelImpossibleQty = BigDecimal.ZERO;

        Iterator<BigDecimal> ite = cancelImpossibleMap.values().iterator();

        while (ite.hasNext()) {

            BigDecimal cancelImpossibleDetailQty = ite.next();

            if (cancelImpossibleDetailQty.compareTo(cancelImpossibleQty) > 0) {
                cancelImpossibleQty = cancelImpossibleDetailQty;
            }
        }

        writeDebugLog("getCancelImpossibleHardAllocQty<End>");
        return cancelImpossibleQty;
    }

//    private BigDecimal getCancelImpossibleSoftAllocQty(NWZC107001PMsg param) {
//        writeDebugLog("getCancelImpossibleSoftAllocQty<Start>");
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//
//        queryParam.put("xxRqstTpCd", param.xxRqstTpCd.getValue());
//        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
//        queryParam.put("trxSrcTpCd", param.trxSrcTpCd.getValue());
//        queryParam.put("trxHdrNum", param.trxHdrNum.getValue());
//        queryParam.put("trxLineNum", param.trxLineNum.getValue());
//        queryParam.put("trxLineSubNum", TRX_LINE_SUB_NUM_SET);
//
//        List<Map<String, Object>> cancelImpossibleSoftAllocList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCancelImpossibleSoftAlloc", queryParam);
//
//        BigDecimal cancelImpossibleQty = BigDecimal.ZERO;
//
//        for (Map<String, Object> cancelImpossibleSoftAlloc : cancelImpossibleSoftAllocList) {
//
//            String childMdseCd = (String) cancelImpossibleSoftAlloc.get("MDSE_CD");
//            String prntMdseCd = (String) cancelImpossibleSoftAlloc.get("SET_MDSE_CD");
//
//            BigDecimal compQty = getCompQty(param, prntMdseCd, childMdseCd);
//            BigDecimal softAllocQty = (BigDecimal) cancelImpossibleSoftAlloc.get("SOFT_ALLOC_QTY");
//
//            BigDecimal cancelImpossibleSetQty = softAllocQty.divide(compQty, 0, BigDecimal.ROUND_HALF_UP);
//
//            if (cancelImpossibleSetQty.compareTo(cancelImpossibleQty) > 0) {
//                cancelImpossibleQty = cancelImpossibleSetQty;
//            }
//        }
//
//        writeDebugLog("getCancelImpossibleSoftAllocQty<End>");
//        return cancelImpossibleQty;
//    }
//
//    private void allCancelSoftAllocation(S21ApiMessageMap msgMap) {
//
//        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//
//        SHPG_PLNTMsg inMsg = new SHPG_PLNTMsg();
//
//        inMsg.setSQLID("027");
//        inMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
//        inMsg.setConditionValue("trxSrcTpCd01", param.trxSrcTpCd.getValue());
//        inMsg.setConditionValue("trxHdrNum01", param.trxHdrNum.getValue());
//        inMsg.setConditionValue("trxLineNum01", param.trxLineNum.getValue());
//        inMsg.setConditionValue("trxLineSubNum01", param.trxLineSubNum.getValue());
//
//        SHPG_PLNTMsgArray setshpgPlnMsgArray = (SHPG_PLNTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
//
//        BigDecimal setNeedRossOdrQty = BigDecimal.ZERO;
//
//        for (int i = 0; i < setshpgPlnMsgArray.getValidCount(); i++) {
//
//            setshpgPlnMsgArray.no(i).shipPlnCancDt.setValue(param.slsDt.getValue());
//            setshpgPlnMsgArray.no(i).shipPlnCancFlg.setValue(ZYPConstant.FLG_ON_Y);
//            setshpgPlnMsgArray.no(i).shpgStsCd.setValue(SHPG_STS.CANCELLED);
//            setshpgPlnMsgArray.no(i).avalHardAllocQty.setValue(BigDecimal.ZERO);
//            setshpgPlnMsgArray.no(i).avalSoftAllocQty.setValue(BigDecimal.ZERO);
//            setshpgPlnMsgArray.no(i).avalPoQty.setValue(BigDecimal.ZERO);
//            setshpgPlnMsgArray.no(i).avalSoQty.setValue(BigDecimal.ZERO);
//            setshpgPlnMsgArray.no(i).softAllocQty.setValue(BigDecimal.ZERO);
//            setShpgPlanTotalAmt(setshpgPlnMsgArray.no(i), setshpgPlnMsgArray.no(i).ordQty.getValue());
//
//            this.updateShpgPlan(msgMap, setshpgPlnMsgArray.no(i));
//
//            setNeedRossOdrQty = setNeedRossOdrQty.add(setshpgPlnMsgArray.no(i).ordQty.getValue());
//        }
//
//        ROSS_ORDTMsg setrossOdrMsg = this.searchRossOdrForUpdate(msgMap);
//
//        if (setrossOdrMsg == null) {
//            msgMap.addXxMsgId(NWZM0450E);
//            return;
//        }
//
//        setrossOdrMsg.ordQty.setValue(setrossOdrMsg.ordQty.getValue().subtract(setNeedRossOdrQty));
//
//        this.updateRossOdr(msgMap, setrossOdrMsg);
//
//        param.allocQty.setValue(setNeedRossOdrQty);
//    }
//
//    private void partialCancelSoftAllocation(S21ApiMessageMap msgMap, BigDecimal cancelImpossibleQty) {
//
//        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//
//        SHPG_PLNTMsg inMsg = new SHPG_PLNTMsg();
//
//        inMsg.setSQLID("027");
//        inMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
//        inMsg.setConditionValue("trxSrcTpCd01", param.trxSrcTpCd.getValue());
//        inMsg.setConditionValue("trxHdrNum01", param.trxHdrNum.getValue());
//        inMsg.setConditionValue("trxLineNum01", param.trxLineNum.getValue());
//        inMsg.setConditionValue("trxLineSubNum01", param.trxLineSubNum.getValue());
//
//        SHPG_PLNTMsgArray setshpgPlnMsgArray = (SHPG_PLNTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
//
//        BigDecimal setNeedRossOdrQty = BigDecimal.ZERO;
//
//        for (int i = 0; i < setshpgPlnMsgArray.getValidCount(); i++) {
//
//            if (TRX_LINE_SUB_NUM_SET.equals(param.trxLineSubNum.getValue())) {
//
//                BigDecimal ordQty = setshpgPlnMsgArray.no(i).ordQty.getValue();
//
//                if (!isSoftAllocationCancel(setshpgPlnMsgArray.no(i).shpgStsCd.getValue())) {
//
//                    cancelImpossibleQty = cancelImpossibleQty.subtract(setshpgPlnMsgArray.no(i).ordQty.getValue());
//
//                } else {
//
//                    if (ordQty.compareTo(cancelImpossibleQty) > 0) {
//
//                        if (cancelImpossibleQty.compareTo(BigDecimal.ZERO) == 0) {
//
//                            mapCancelShpgPln(param, setshpgPlnMsgArray.no(i));
//
//                        } else {
//
//                            if (setshpgPlnMsgArray.no(i).softAllocQty.getValue().compareTo(cancelImpossibleQty) > 0) {
//                                setshpgPlnMsgArray.no(i).softAllocQty.setValue(cancelImpossibleQty);
//                            }
//
//                            setshpgPlnMsgArray.no(i).ordQty.setValue(cancelImpossibleQty);
//                            cancelImpossibleQty = BigDecimal.ZERO;
//                        }
//
//                    } else {
//
//                        cancelImpossibleQty = cancelImpossibleQty.subtract(setshpgPlnMsgArray.no(i).ordQty.getValue());
//                        mapCancelShpgPln(param, setshpgPlnMsgArray.no(i));
//                    }
//                }
//
//            } else {
//
//                String childMdseCd = setshpgPlnMsgArray.no(i).mdseCd.getValue();
//                String prntMdseCd = setshpgPlnMsgArray.no(i).setMdseCd.getValue();
//
//                BigDecimal compQty = getCompQty(param, prntMdseCd, childMdseCd);
//                BigDecimal softAllocQty = setshpgPlnMsgArray.no(i).softAllocQty.getValue();
//                BigDecimal cancelImpossibleSetQty = cancelImpossibleQty.multiply(compQty);
//
//                if (!isSoftAllocationCancel(setshpgPlnMsgArray.no(i).shpgStsCd.getValue())) {
//
//                    cancelImpossibleQty = cancelImpossibleQty.subtract(setshpgPlnMsgArray.no(i).ordQty.getValue().divide(compQty, 0, BigDecimal.ROUND_HALF_UP));
//
//                } else {
//
//                    if (softAllocQty.compareTo(cancelImpossibleSetQty) > 0) {
//
//                        if (cancelImpossibleSetQty.compareTo(BigDecimal.ZERO) == 0) {
//
//                            mapCancelShpgPln(param, setshpgPlnMsgArray.no(i));
//
//                        } else {
//
//                            if (setshpgPlnMsgArray.no(i).softAllocQty.getValue().compareTo(cancelImpossibleSetQty) > 0) {
//                                setshpgPlnMsgArray.no(i).softAllocQty.setValue(cancelImpossibleSetQty);
//                            }
//
//                            setshpgPlnMsgArray.no(i).ordQty.setValue(cancelImpossibleSetQty);
//                            cancelImpossibleQty = BigDecimal.ZERO;
//                        }
//
//                    } else {
//
//                        cancelImpossibleQty = cancelImpossibleQty.subtract(setshpgPlnMsgArray.no(i).softAllocQty.getValue().divide(compQty, 0, BigDecimal.ROUND_HALF_UP));
//
//                        mapCancelShpgPln(param, setshpgPlnMsgArray.no(i));
//                    }
//                }
//            }
//
//            setShpgPlanTotalAmt(setshpgPlnMsgArray.no(i), setshpgPlnMsgArray.no(i).ordQty.getValue());
//
//            this.updateCancelShpgPlan(msgMap, setshpgPlnMsgArray.no(i));
//
//            setNeedRossOdrQty = setNeedRossOdrQty.add(setshpgPlnMsgArray.no(i).ordQty.getValue());
//        }
//
//        ROSS_ORDTMsg setrossOdrMsg = this.searchRossOdrForUpdate(msgMap);
//
//        if (setrossOdrMsg == null) {
//            msgMap.addXxMsgId(NWZM0450E);
//            return;
//        }
//
//        setrossOdrMsg.ordQty.setValue(setNeedRossOdrQty);
//
//        this.updateRossOdr(msgMap, setrossOdrMsg);
//
//        param.allocQty.setValue(setNeedRossOdrQty);
//    }
//
//    private void mapCancelShpgPln(NWZC107001PMsg param, SHPG_PLNTMsg shpgPlnMsg) {
//
//        shpgPlnMsg.shipPlnCancDt.setValue(param.slsDt.getValue());
//        shpgPlnMsg.shipPlnCancFlg.setValue(ZYPConstant.FLG_ON_Y);
//        shpgPlnMsg.shpgStsCd.setValue(SHPG_STS.CANCELLED);
//        shpgPlnMsg.avalHardAllocQty.setValue(BigDecimal.ZERO);
//        shpgPlnMsg.avalSoftAllocQty.setValue(BigDecimal.ZERO);
//        shpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);
//        shpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);
//        shpgPlnMsg.softAllocQty.setValue(BigDecimal.ZERO);
//        shpgPlnMsg.ordQty.setValue(BigDecimal.ZERO);
//    }
//
//    private boolean isSoftAllocationCancel(String shpgStsCd) {
//
//        return !ZYPCommonFunc.hasValue(shpgStsCd) || SHPG_STS.VALIDATED.equals(shpgStsCd) || SHPG_STS.CANCELLED.equals(shpgStsCd);
//    }

    private BigDecimal getCompQty(NWZC107001PMsg param, String prntMdseCd, String childMdseCd) {

        if (!ZYPCommonFunc.hasValue(prntMdseCd) || !ZYPCommonFunc.hasValue(childMdseCd)) {
            return BigDecimal.ZERO;
        }

        String glblCmpyCd = param.glblCmpyCd.getValue();
        String effFromDt = param.slsDt.getValue();
        String effThruDt = param.slsDt.getValue();

        CMPSNTMsg cmpsnMsg = NWXCmpsnTMsgThreadLocalCache.getInstance().get(glblCmpyCd, prntMdseCd, childMdseCd, effFromDt, effThruDt);

        if (cmpsnMsg == null) {

            return BigDecimal.ZERO;

        } else {

            return cmpsnMsg.childMdseQty.getValue();
        }
    }
//
//    private BigDecimal getCompQty(String glblCmpyCd, String prntMdseCd, String childmdseCode, String ordTs) {
//        writeDebugLog("getCompQty<Start>");
//
//        BigDecimal compQty = BigDecimal.ZERO;
//
//        CMPSNTMsg inCmpsnMsg = new CMPSNTMsg();
//        inCmpsnMsg.setSQLID("003");
//        inCmpsnMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inCmpsnMsg.setConditionValue("prntMdseCd01", prntMdseCd);
//        inCmpsnMsg.setConditionValue("childOrdTakeMdseCd01A", childmdseCode);
//        inCmpsnMsg.setConditionValue("childOrdTakeMdseCd01B", childmdseCode);
//        inCmpsnMsg.setConditionValue("effFromDt01", ordTs);
//        inCmpsnMsg.setConditionValue("effThruDt01", ordTs);
//
//        CMPSNTMsgArray cmpsnMsgArray = (CMPSNTMsgArray) S21ApiTBLAccessor.findByCondition(inCmpsnMsg);
//
//        if (cmpsnMsgArray.getValidCount() > 0) {
//            CMPSNTMsg cmpsnMsg = (CMPSNTMsg) cmpsnMsgArray.get(0);
//            compQty = cmpsnMsg.childMdseQty.getValue();
//        }
//
//        writeDebugLog("getCompQty<End>");
//        return compQty;
//
//    }

    private void newOrderInfoProcess(S21ApiMessageMap msgMap, final MDSETMsg mdseMsg, final String expdShipDt, final ONBATCH_TYPE onBatchType) {
        writeDebugLog("newOrderInfoProcess<Start>");

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();

        String distItemFlg = this.getDistItemFlg(msgMap, mdseMsg);
        BigDecimal setShpgPlnOrdQty = param.xxRqstQty.getValue();
        // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//        BigDecimal rossOrdQty = BigDecimal.ZERO;
        // 2017/04/12 S21_NA#Review structure Lv.1 Del End

        // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//        if (TRX_SRC_TP.RETAIL.equals(param.trxSrcTpCd.getValue())) {
//
//            ROSS_ORDTMsg rossOdrMsg = this.searchRossOdrForUpdate(msgMap);
//
//            if (rossOdrMsg == null) {
//
//                this.insertRossOrd(msgMap, mdseMsg, expdShipDt, distItemFlg, onBatchType);
//                rossOrdQty = param.xxRqstQty.getValue();
//            } else {
//                rossOdrMsg.custIssPoNum.setValue(param.custIssPoNum.getValue());
//                rossOdrMsg.rossOrdTpCd.setValue(param.rossOrdTpCd.getValue());
//                rossOdrMsg.rossOrdSubmtTs.setValue(param.xxOrdTs.getValue());
//                rossOdrMsg.expdShipDt.setValue(expdShipDt);
//                if (ZYPConstant.FLG_OFF_N.equals(distItemFlg) && (DIST_TP.NONE.equals(mdseMsg.invtyDistTpCd.getValue()) && HARD_ALLOC_TP.AUTO.equals(mdseMsg.invtyHardAllocTpCd.getValue()))) {
//                    rossOdrMsg.ordQty.setValue(rossOdrMsg.ordQty.getValue().add(param.xxRqstQty.getValue()));
//                }
//
//                this.updateRossOdr(msgMap, rossOdrMsg);
//                rossOrdQty = rossOdrMsg.ordQty.getValue();
//            }
//
//            BigDecimal sumShpgPlnOrdQty = this.getSumOrdQty(param);
//
//            if (rossOrdQty.compareTo(sumShpgPlnOrdQty) > 0) {
//                // rossOrdQty> sumShpgPlnOrdQty
//                setShpgPlnOrdQty = rossOrdQty.subtract(sumShpgPlnOrdQty);
//            } else if (rossOrdQty.compareTo(sumShpgPlnOrdQty) == 0) {
//                setShpgPlnOrdQty = BigDecimal.ZERO;
//            } else {
//                msgMap.addXxMsgId(NWZM0076E);
//                return;
//            }
//        }
        // 2017/04/12 S21_NA#Review structure Lv.1 Del Start

        SHPG_PLNTMsgArray shpgPlnMsgArray = this.searchShpgPlanForUpdate(msgMap, SHPG_STS.VALIDATED);

        if (shpgPlnMsgArray.length() >= 2) {
            // duplicate
            msgMap.addXxMsgId(NWZM0076E);
            return;

        } else if (shpgPlnMsgArray.length() == 1) {
            // update
            SHPG_PLNTMsg shpgPlnMsg = (SHPG_PLNTMsg) shpgPlnMsgArray.get(0);
            this.setShpgPlanTotalAmt(shpgPlnMsg, shpgPlnMsg.ordQty.getValue().add(setShpgPlnOrdQty));
            shpgPlnMsg.ordQty.setValue(shpgPlnMsg.ordQty.getValue().add(setShpgPlnOrdQty));
            ZYPEZDItemValueSetter.setValue(shpgPlnMsg.invtyLocCd, param.invtyLocCd);
            shpgPlnMsg.mdseCd.setValue(param.mdseCd.getValue());
            shpgPlnMsg.reqShpgSvcLvlCd.setValue(param.shpgSvcLvlCd.getValue());
            shpgPlnMsg.origShpgSvcLvlCd.setValue(param.shpgSvcLvlCd.getValue());

            this.updateShpgPlan(msgMap, shpgPlnMsg);

        } else {
            // insert
            this.insertShpgPln(msgMap, mdseMsg, distItemFlg, param.xxRqstQty.getValue());
            if (hasMessage(msgMap)) {
                return;
            }
        }

        writeDebugLog("newOrderInfoProcess<End>");
    }

    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//    private BigDecimal getSumOrdQty(final NWZC107001PMsg param1) {
//        writeDebugLog("getSumOrdQty<Start>");
//
//        Map<String, String> key = new HashMap<String, String>();
//        key.put("glblCmpyCd", param1.glblCmpyCd.getValue());
//        key.put("trxHdrNum", param1.trxHdrNum.getValue());
//        key.put("trxLineNum", param1.trxLineNum.getValue());
//        key.put("trxLineSubNum", param1.trxLineSubNum.getValue());
//        key.put("trxSrcTpCd", param1.trxSrcTpCd.getValue());
//        key.put("cancelled", SHPG_STS.CANCELLED);
//
//        BigDecimal sumOrdQty = (BigDecimal) ssmBatchClient.queryObject("getSumOrderQty", key);
//
//        if (sumOrdQty == null) {
//            return BigDecimal.ZERO;
//        }
//
//        writeDebugLog("getSumOrdQty <End>");
//        return sumOrdQty;
//    }
    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start

    private void backOrderInfoProcess(S21ApiMessageMap msgMap, final MDSETMsg mdseMsg, final String expdShipDt, final ONBATCH_TYPE onBatchType) {
        writeDebugLog("backOrderInfoProcess<Start>");

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();

        String distItemFlg = this.getDistItemFlg(msgMap, mdseMsg);

        SHPG_PLNTMsgArray shpgPlnMsgArray = this.searchShpgPlanForUpdate(msgMap, SHPG_STS.VALIDATED);

        if (shpgPlnMsgArray.length() >= 2) {
            // duplicate
            msgMap.addXxMsgId(NWZM0076E);
            return;

        } else {

            BigDecimal orderQtySum = this.getSumOrderQtyForSOPrinted(param);
            BigDecimal setOrdQty = BigDecimal.ZERO;

            if (orderQtySum == null) {
                setOrdQty = param.xxRqstQty.getValue();

            } else if (orderQtySum.compareTo(param.xxRqstQty.getValue()) == 0) {
                msgMap.addXxMsgId(NWZM0082E);
                return;
            } else if (orderQtySum.compareTo(param.xxRqstQty.getValue()) > 0) {
                msgMap.addXxMsgId(NWZM0082E);
                return;
            } else {
                setOrdQty = param.xxRqstQty.getValue().subtract(orderQtySum);
            }

            if (shpgPlnMsgArray.length() == 1) {
                // update
                SHPG_PLNTMsg shpgPlnMsg = (SHPG_PLNTMsg) shpgPlnMsgArray.get(0);
                shpgPlnMsg.ordQty.setValue(setOrdQty);
                this.setShpgPlanTotalAmt(shpgPlnMsg, setOrdQty);
                this.updateShpgPlan(msgMap, shpgPlnMsg);

            } else {
                // insert
                this.insertShpgPln(msgMap, mdseMsg, distItemFlg, setOrdQty);
            }
        }
        if (hasMessage(msgMap)) {
            return;
        }

        // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//        if (TRX_SRC_TP.RETAIL.equals(param.trxSrcTpCd.getValue())) {
//
//            ROSS_ORDTMsg rossOdrMsg = this.searchRossOdrForUpdate(msgMap);
//
//            if (rossOdrMsg == null) {
//
//                this.insertRossOrd(msgMap, mdseMsg, expdShipDt, distItemFlg, onBatchType);
//            } else {
//
//                rossOdrMsg.ordQty.setValue(param.xxRqstQty.getValue());
//                this.updateRossOdr(msgMap, rossOdrMsg);
//            }
//
//        }
        // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
        writeDebugLog("backOrderInfoProcess<End>");

    }

    private void newProcess(S21ApiMessageMap msgMap, final TRX_SRC_TPTMsg trxSrcTpMsg, final MDSETMsg mdseMsg, final String expdShipDt, final ONBATCH_TYPE onBatchType) {
        writeDebugLog("newProcess<Start>");

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
        MDSETMsg setmdseMsg = new MDSETMsg();

        if (hasValue(param.setMdseCd)) {

            setmdseMsg = this.getMdse(msgMap, param.setMdseCd.getValue());

            if (hasMessage(msgMap)) {
                return;
            }
        }

        String distItemFlg = this.getDistItemFlg(msgMap, mdseMsg);

        // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//        // execute Soft Allocation API
//        if (ZYPConstant.FLG_ON_Y.equals(trxSrcTpMsg.softAllocFlg.getValue())) {
//
//            this.softAllocNewProcess(msgMap, distItemFlg, expdShipDt, mdseMsg, setmdseMsg, onBatchType);
//            if (hasMessage(msgMap)) {
//                return;
//            }
//
//        }
        // 2017/04/12 S21_NA#Review structure Lv.1 Del End

        // execute Hard Allocation API
        this.hardAllocNewProcess(msgMap, distItemFlg, expdShipDt, mdseMsg, setmdseMsg, onBatchType);
        if (hasMessage(msgMap)) {
            return;
        }

        // execute ATP API
     //   if (TRX_SRC_TP.RETAIL.equals(param.trxSrcTpCd.getValue())) {

         //   this.execATP(msgMap, mdseMsg, onBatchType);
     //   }
        writeDebugLog("newProcess<End>");
    }

    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//    private void execATP(S21ApiMessageMap msgMap, final MDSETMsg mdseMsg, final ONBATCH_TYPE onBatchType) {
//        writeDebugLog("execATP<Start>");
//
//        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//
//        if (MDSE_TP.SET.equals(mdseMsg.mdseTpCd.getValue())) {
//            return;
//        }
//
//        ATP_BAT_WRKTMsgArray atpBatWrkMsgArray = this.searchAtpBatWrk(msgMap);
//        if (atpBatWrkMsgArray.getValidCount() > 0) {
//            return;
//        }
//
//        List<String> atpParamList = this.getATPparameterList(param);
//
//        if (atpParamList == null || atpParamList.isEmpty()) {
//            return;
//        }
//
//        NWZC108001PMsg awzc108001pmsg = new NWZC108001PMsg();
//
//        awzc108001pmsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
//        awzc108001pmsg.procDt.setValue(param.slsDt.getValue());
//        int atpListsize = atpParamList.size();
//
//        for (int i = 0; i < atpParamList.size(); i++) {
//            awzc108001pmsg.ParameterList.no(i).shpgPlnNum.setValue(atpParamList.get(i));
//        }
//
//        awzc108001pmsg.ParameterList.setValidCount(atpListsize);
//
//        NWZC108001 awzc108001 = new NWZC108001();
//
//        awzc108001.execute(awzc108001pmsg, onBatchType);
//
//        if (awzc108001pmsg.xxMsgIdList.getValidCount() > 0) {
//            for (int i = 0; i < awzc108001pmsg.xxMsgIdList.getValidCount(); i++) {
//                String msgId = awzc108001pmsg.xxMsgIdList.no(i).xxMsgId.getValue();
//                msgMap.addXxMsgId(msgId);
//            }
//        }
//
//        writeDebugLog("execATP<End>");
//    }
//
//    private ATP_BAT_WRKTMsgArray searchAtpBatWrk(S21ApiMessageMap msgMap) {
//
//        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//
//        ATP_BAT_WRKTMsg atpBatWrkMsg = new ATP_BAT_WRKTMsg();
//        atpBatWrkMsg.setSQLID("001");
//        atpBatWrkMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
//
//        ATP_BAT_WRKTMsgArray atpBatWrkMsgArray = (ATP_BAT_WRKTMsgArray) S21ApiTBLAccessor.findByCondition(atpBatWrkMsg);
//
//        return atpBatWrkMsgArray;
//
//    }
//
//    private List<String> getATPparameterList(final NWZC107001PMsg param1) {
//        writeDebugLog("getATPparameterList <Start>");
//
//        Map<String, Object> key = new HashMap<String, Object>();
//        key.put("glblCmpyCd", param1.glblCmpyCd.getValue());
//        key.put("trxHdrNum", param1.trxHdrNum.getValue());
//        key.put("trxLineSubNumSetMdse", "000");
//        key.put("trxSrcTpCd", param1.trxSrcTpCd.getValue());
//        key.put("shpgStsCdForValidated", SHPG_STS.VALIDATED);
//        key.put("prtlAcptFlg", param1.xxPrtlAcptFlg.getValue());
//        key.put("shpgStsCdForHardAllocated", SHPG_STS.HARD_ALLOCATED);
//
//        List<String> atpParameterDataList = (List) ssmBatchClient.queryObjectList("getATPparameter", key);
//
//        writeDebugLog("getATPparameterList <End>");
//        return atpParameterDataList;
//
//    }
    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start

    private void cancelProcess(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {
        writeDebugLog("cancelProcess<Start>");

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
        BigDecimal cancelQty = BigDecimal.ZERO;

        BigDecimal cancelImpossibleQty = getCancelImpossibleHardAllocQty(param);

        List<Map<String, Object>> shpgPlanCancelList = this.searchShpgPlanCancel(param);

        if(shpgPlanCancelList == null || shpgPlanCancelList.isEmpty()){
            param.allocQty.setValue(BigDecimal.ZERO);
            msgMap.addXxMsgId(NWZM0817E);
            return;
        }



        if (TRX_LINE_SUB_NUM_SET.equals(param.trxLineSubNum.getValue())) {
            cancelQty = this.updateShpgPlanCancel(msgMap, shpgPlanCancelList, cancelImpossibleQty);
        } else {
            cancelQty = this.updateShpgPlanCancelComp(msgMap, shpgPlanCancelList, cancelImpossibleQty);
        }

        if (BigDecimal.ZERO.compareTo(cancelQty) == 0) {
            param.allocQty.setValue(BigDecimal.ZERO);
            return;
        }

        if (hasMessage(msgMap)) {
            return;
        }

        // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//        if (TRX_SRC_TP.RETAIL.equals(param.trxSrcTpCd.getValue())) {
//
//            ROSS_ORDTMsg rossOdrMsg = this.searchRossOdrForUpdate(msgMap);
//
//            if (rossOdrMsg == null) {
//                msgMap.addXxMsgId(NWZM0450E);
//                return;
//            }
//
//            this.updateRossOdrCancel(msgMap, rossOdrMsg);
//
//            if (hasMessage(msgMap)) {
//                return;
//            }
//        }
        // 2017/04/12 S21_NA#Review structure Lv.1 Del Start

        this.execHardAllocAPIForCancel(msgMap, onBatchType);

        if (hasMessage(msgMap)) {
            return;
        }

        // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//        this.execSoftAllocAPIForCancel(msgMap, onBatchType);
//
//        if (hasMessage(msgMap)) {
//            return;
//        }
        // 2017/04/12 S21_NA#Review structure Lv.1 Del End

        param.allocQty.setValue(cancelQty);

        writeDebugLog("cancelProcess<End>");
    }

    private void hardAllocNewProcess(S21ApiMessageMap msgMap, final String distItemFlg, final String expdShipDt, final MDSETMsg mdseMsg, final MDSETMsg setmdseMsg, final ONBATCH_TYPE onBatchType) {
        writeDebugLog("hardAllocNewProcess<Start>");

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
        BigDecimal totalHardAllocQty = BigDecimal.ZERO;

        if (MDSE_TP.SET.equals(mdseMsg.mdseTpCd.getValue())) {
            return;
        }

        if (TRX_SRC_TP.RETAIL.equals(param.trxSrcTpCd.getValue()) && (ZYPConstant.FLG_OFF_N.equals(distItemFlg) && (DIST_TP.NONE.equals(mdseMsg.invtyDistTpCd.getValue()) && HARD_ALLOC_TP.AUTO.equals(mdseMsg.invtyHardAllocTpCd.getValue())))) {

            if (!hasValue(mdseMsg.daysPriAllocNum)) {
                msgMap.addXxMsgId(NWZM0364E);
                return;
            }

            String errAllocStartedDay = NWXC002007BackOrder.chkAllocStartedDay(mdseMsg.daysPriAllocNum.getValue(), param.glblCmpyCd.getValue(), param.invtyLocCd.getValue(), param.slsDt.getValue(), expdShipDt, onBatchType);
            // check AllocStartedDay
            if(hasValue(errAllocStartedDay)){
                if(!NWZM0950E.equals(errAllocStartedDay)){
                    msgMap.addXxMsgId(errAllocStartedDay);
                }
                param.allocQty.setValue(BigDecimal.ZERO);
                return;
            }

//            int days = mdseMsg.daysPriAllocNum.getValue().negate().intValue();
//            String startDay = ZYPDateUtil.addDays(expdShipDt, days);

////            if (ZYPDateUtil.compare(param.slsDt.getValue(), startDay) < 0) {
//            if (ZYPDateUtil.compare(param.slsDt.getValue(), businessDay) < 0) {
//            // startDay > param.slsDt.getValue()
//                param.allocQty.setValue(BigDecimal.ZERO);
//                return;
//
//            }

//            boolean syncFlg = NWXC002007BackOrder.isSynchronous(param.glblCmpyCd.getValue(), param.mdseCd.getValue(), mdseMsg.invtyDistTpCd.getValue());
//
//            if (syncFlg) {
//                param.allocQty.setValue(totalHardAllocQty);
//                writeDebugLog("MasterSynchronous err");
//                return;
//            }

            NWXC002007BackOrderParam backOrderParam = new NWXC002007BackOrderParam();
            backOrderParam.setGlblCmpyCd(param.glblCmpyCd.getValue());
            backOrderParam.setMdseCd(param.mdseCd.getValue());
            backOrderParam.setTrxHdrNum(param.trxHdrNum.getValue());
            backOrderParam.setTrxLineNum(param.trxLineNum.getValue());
            backOrderParam.setTrxLineSubNum(param.trxLineSubNum.getValue());
            backOrderParam.setTrxSrcTpCd(param.trxSrcTpCd.getValue());
            backOrderParam.setTs(param.xxOrdTs.getValue());
            backOrderParam.setSlsDt(param.slsDt.getValue());
            backOrderParam.setDaysPriAllocNum(mdseMsg.daysPriAllocNum.getValue());
            backOrderParam.setCheckTp(NWXC002007BackOrder.BACKORDER_CHECK_TYPE_NONE);
            //Defect 5902
            backOrderParam.setInvtyLocCd(param.invtyLocCd.getValue());

            boolean buckOdrFlg = NWXC002007BackOrder.isExist(backOrderParam);

            if (buckOdrFlg) {
                param.allocQty.setValue(totalHardAllocQty);
                writeDebugLog("BackOrder err");
                return;
            }

        }

        INVTYTMsgArray invtyMsgArray = this.getInvtyInfo(msgMap);
        List<NWZC104002PMsg> awzc104002pmsgList = new ArrayList<NWZC104002PMsg>();

        if (invtyMsgArray.getValidCount() == 0) {

            if (ZYPConstant.FLG_ON_Y.equals(param.xxPrtlAcptFlg.getValue())) {
                param.allocQty.setValue(BigDecimal.ZERO);
                return;
            } else {
                msgMap.addXxMsgId(NWZM0452E);
                return;
            }

        } else {

            for (int i = 0; i < invtyMsgArray.getValidCount(); i++) {
                NWZC104002PMsg pmsg = new NWZC104002PMsg();
                EZDMsg.copy(invtyMsgArray.no(i), null, pmsg, null);
                awzc104002pmsgList.add(pmsg);

            }

        }

        if (ZYPConstant.FLG_ON_Y.equals(distItemFlg)) {

            List<SoftAllocationData> softAllocDataList = this.getSoftAllocInfo(msgMap, setmdseMsg);

            if (TRX_SRC_TP.RETAIL.equals(param.trxSrcTpCd.getValue()) && softAllocDataList.isEmpty()) {

                msgMap.addXxMsgId(NWZM0452E);
                return;

            } else if (!TRX_SRC_TP.RETAIL.equals(param.trxSrcTpCd.getValue()) && softAllocDataList.isEmpty()) {

                NWZC104001PMsg awzc104001pmsg = this.setHardAllocAPIPmsg(msgMap, mdseMsg, setmdseMsg);

                awzc104001pmsg.distTpCd.setValue(DIST_TP.NONE);
                awzc104001pmsg.hardAllocTpCd.setValue(HARD_ALLOC_TP.AUTO);

                totalHardAllocQty = this.execHardAllocAPIForNew(msgMap, awzc104001pmsg, awzc104002pmsgList, onBatchType);

            } else {

                for (SoftAllocationData softAllocData : softAllocDataList) {

                    NWZC104001PMsg awzc104001pmsg = this.setHardAllocAPIPmsg(msgMap, mdseMsg, setmdseMsg);

                    awzc104001pmsg.mdseCd_SA.setValue(softAllocData.getMdseCd());
                    awzc104001pmsg.invtyLocCd_SA.setValue(softAllocData.getInvtyLocCd());
                    awzc104001pmsg.softAllocPk.setValue(softAllocData.getSoftAllocPk());
                    awzc104001pmsg.shpgSvcLvlCd_SA.setValue(softAllocData.getShpgSvcLvlCd());
                    awzc104001pmsg.xxOrdTakeMdseFlg_SA.setValue(ODR_TAKEN_MDSE_CD_NO);
                    awzc104001pmsg.xxRqstQty.setValue(softAllocData.getHardAllocAvalQty());

                    BigDecimal hardAllocQty = this.execHardAllocAPIForNew(msgMap, awzc104001pmsg, awzc104002pmsgList, onBatchType);

                    if (hasMessage(msgMap)) {
                        return;
                    }

                    totalHardAllocQty = totalHardAllocQty.add(hardAllocQty);
                }
            }
        } else {

            NWZC104001PMsg awzc104001pmsg = this.setHardAllocAPIPmsg(msgMap, mdseMsg, setmdseMsg);
            totalHardAllocQty = this.execHardAllocAPIForNew(msgMap, awzc104001pmsg, awzc104002pmsgList, onBatchType);

        }

        if (hasMessage(msgMap)) {
            return;
        }

        if (ZYPConstant.FLG_OFF_N.equals(param.xxPrtlAcptFlg.getValue())) {
            if (totalHardAllocQty.compareTo(param.xxRqstQty.getValue()) != 0) {
                param.allocQty.setValue(totalHardAllocQty);
                msgMap.addXxMsgId(NWZM0452E);
                return;
            }
        }

        if (!TRX_SRC_TP.RETAIL.equals(param.trxSrcTpCd.getValue()) && ZYPConstant.FLG_ON_Y.equals(param.xxPrtlAcptFlg.getValue())) {

            SHPG_PLNTMsgArray shpgPlnMsgArray = this.searchShpgPlanForShpgSts(msgMap, SHPG_STS.VALIDATED);

            if (shpgPlnMsgArray.getValidCount() > 0) {

                this.logicalRemoveShpgPln(shpgPlnMsgArray);
            }
        }

        param.allocQty.setValue(totalHardAllocQty);

        writeDebugLog("hardAllocNewProcess<End>");
    }

    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//    private String getCalTpCd(S21ApiMessageMap msgMap,final String glblCmpyCd,  final String invtyLocCd){
//
//        CAL_RELNTMsg calRelnTMsg = null ;
//        String calTpCd              = null;
//
//        if(hasValue(invtyLocCd)){
//            // 1.[ Search CAL_TP_CD Table]
//            calRelnTMsg = new CAL_RELNTMsg();
//            calRelnTMsg.glblCmpyCd.setValue(glblCmpyCd);
//            calRelnTMsg.calSubTpCd.setValue(CAL_SUB_TP.WAREHOUSE_CALENDAR);
//            calRelnTMsg.calMultCd.setValue(invtyLocCd);
//            calRelnTMsg = (CAL_RELNTMsg) S21CacheTBLAccessor.findByKey(calRelnTMsg);
//        }
//
//        if (calRelnTMsg != null) {
//            calTpCd = calRelnTMsg.calTpCd.getValue();
//        } else {
//
//            // 2.[ Search CAL_TP_CD Table]
//            CAL_RELNTMsg calRelnTKey = new CAL_RELNTMsg();
//            calRelnTKey.setSQLID("001");
//            calRelnTKey.setConditionValue("glblCmpyCd01", glblCmpyCd);
//            calRelnTKey.setConditionValue("calSubTpCd01", CAL_SUB_TP.COMPANY_CALENDAR);
//
//            CAL_RELNTMsgArray calRelnTRcd = (CAL_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(calRelnTKey);
//
//            if(calRelnTRcd == null || calRelnTRcd.length() ==0){
//                    msgMap.addXxMsgId(NWZM0949E);
//
//            }else if (calRelnTRcd.length() > 1) {
//                // When acquire more than two cases,
//                // process is finished as master setting error.
//                msgMap.addXxMsgId(NWZM0673E);
//            } else {
//                calTpCd = calRelnTRcd.no(0).calTpCd.getValue();
//            }
//        }
//
//        return calTpCd;
//
//    }
//
//    private String getBusinessDay(final String glblCmpyCd, final String startDay, final String calTpCd, final ONBATCH_TYPE onBatchType){
//
//        String businessDay = startDay;
//
//        if(ONBATCH_TYPE.BATCH.equals(onBatchType)){
//
//            try{
//                    ZYPDateUtil.isBusinessDayEx(glblCmpyCd, calTpCd, startDay);
//
//                } catch (S21AbendException e) {
//                    businessDay = ZYPDateUtil.addBusinessDay(calTpCd, startDay, -1);
//
//                }
//        }else{
//
//            if(ZYPDateUtil.isBusinessDayEx(glblCmpyCd, calTpCd, startDay)){
//                //nothing to do
//
//            }else{
//                businessDay = ZYPDateUtil.addBusinessDay(calTpCd, startDay, -1);
//            }
//        }
//
//        return businessDay;
//    }
    // 2017/04/12 S21_NA#Review structure Lv.1 Del End

    private void logicalRemoveShpgPln(SHPG_PLNTMsgArray shpgPlnMsgArray) {
        writeDebugLog("logicalRemoveShpgPln<Start>");

        for (int i = 0; i < shpgPlnMsgArray.getValidCount(); i++) {

            SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
            shpgPlnMsg.glblCmpyCd.setValue(shpgPlnMsgArray.no(i).glblCmpyCd.getValue());
            shpgPlnMsg.shpgPlnNum.setValue(shpgPlnMsgArray.no(i).shpgPlnNum.getValue());
            S21ApiTBLAccessor.logicalRemove(shpgPlnMsg);

        }

        writeDebugLog("logicalRemoveShpgPln<End>");
    }

    private BigDecimal execHardAllocAPIForNew(S21ApiMessageMap msgMap, final NWZC104001PMsg awzc104001pmsg, final List<NWZC104002PMsg> awzc104002pmsgList, ONBATCH_TYPE onBatchType) {

        writeDebugLog("execHardAllocAPIForNew<Start>");
        BigDecimal hardAllocQty = BigDecimal.ZERO;

        NWZC104001 awzc104001api = new NWZC104001();

        awzc104001api.execute(awzc104001pmsg, awzc104002pmsgList, onBatchType);

        if (awzc104001pmsg.xxMsgIdList.getValidCount() > 0) {

            for (int i = 0; i < awzc104001pmsg.xxMsgIdList.getValidCount(); i++) {
                String msgId = awzc104001pmsg.xxMsgIdList.no(i).xxMsgId.getValue();
                msgMap.addXxMsgId(msgId);
                writeDebugLog("msgId :" + msgId);
            }
            return hardAllocQty;
        }

        for (int i = 0; i < awzc104001pmsg.AllocationInfo.getValidCount(); i++) {
            hardAllocQty = hardAllocQty.add(awzc104001pmsg.AllocationInfo.no(i).hardAllocQty.getValue());
        }

        writeDebugLog("execHardAllocAPIForNew<End>");

        return hardAllocQty;

    }

    private List<SoftAllocationData> getSoftAllocInfo(S21ApiMessageMap msgMap, final MDSETMsg setmdseMsg) {
        writeDebugLog("getSoftAllocInfo<Start>");

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();

        String setDistItemFlg = this.getDistItemFlg(msgMap, setmdseMsg);

        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", param.glblCmpyCd.getValue());
        key.put("trxSrcTpCd", param.trxSrcTpCd.getValue());
        key.put("trxHdrNum", param.trxHdrNum.getValue());
        key.put("trxLineNum", param.trxLineNum.getValue());
        key.put("trxLineSubNum", param.trxLineSubNum.getValue());
        if (hasValue(param.setMdseCd) && ZYPConstant.FLG_ON_Y.equals(setDistItemFlg)) {
            key.put("settrxLineSubNum", TRX_LINE_SUB_NUM_SET);
        } else {
            key.put("settrxLineSubNum", param.trxLineSubNum.getValue());
        }
        key.put("shpgStsCd", SHPG_STS.VALIDATED);

        List<SoftAllocationData> softAllocList = (List) ssmBatchClient.queryObjectList("getSoftAllocInfo", key);

        writeDebugLog("getSoftAllocInfo<End>");
        return softAllocList;
    }

    private NWZC104001PMsg setHardAllocAPIPmsg(S21ApiMessageMap msgMap, final MDSETMsg mdseMsg, final MDSETMsg setmdseMsg) {
        writeDebugLog("setHardAllocPmsg<Start>");

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();

        NWZC104001PMsg awzc104001pmsg = new NWZC104001PMsg();

        awzc104001pmsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        awzc104001pmsg.xxRqstTpCd.setValue(HARD_ALLOC_REQ_TP_NEW);
        if (ZYPConstant.FLG_ON_Y.equals(param.xxPrtlAcptFlg.getValue())) {
            awzc104001pmsg.xxPrtlAcptFlg.setValue(PRTL_OK);
        } else {
            awzc104001pmsg.xxPrtlAcptFlg.setValue(PRTL_NG);
        }
        awzc104001pmsg.xxItemFlipAcptFlg.setValue(ITEM_FLIP_NG);
        awzc104001pmsg.xxWhFlipAcptFlg.setValue(WH_FLIP_NG);
        awzc104001pmsg.trxSrcTpCd.setValue(param.trxSrcTpCd.getValue());
        awzc104001pmsg.trxHdrNum.setValue(param.trxHdrNum.getValue());
        awzc104001pmsg.trxLineNum.setValue(param.trxLineNum.getValue());
        awzc104001pmsg.trxLineSubNum.setValue(param.trxLineSubNum.getValue());
        awzc104001pmsg.xxOrdTakeMdseFlg.setValue(ODR_TAKEN_MDSE_CD_NO);
        awzc104001pmsg.mdseCd.setValue(param.mdseCd.getValue());
        awzc104001pmsg.invtyLocCd.setValue(param.invtyLocCd.getValue());
        awzc104001pmsg.locStsCd.setValue(param.locStsCd.getValue());
        awzc104001pmsg.stkStsCd.setValue(param.stkStsCd.getValue());

        if (hasValue(param.setMdseCd) && DIST_TP.DISTRIBUTION.equals(setmdseMsg.invtyDistTpCd.getValue())) {

            awzc104001pmsg.distTpCd.setValue(DIST_TP.DISTRIBUTION);
            awzc104001pmsg.hardAllocTpCd.setValue(HARD_ALLOC_TP.AUTO);

        } else if (DIST_TP.DISTRIBUTION.equals(mdseMsg.invtyDistTpCd.getValue()) && (!STK_STS.GOOD.equals(param.stkStsCd.getValue()))) {

            awzc104001pmsg.distTpCd.setValue(DIST_TP.NONE);
            awzc104001pmsg.hardAllocTpCd.setValue(HARD_ALLOC_TP.MANUAL);

        } else {

            awzc104001pmsg.distTpCd.setValue(mdseMsg.invtyDistTpCd.getValue());
            awzc104001pmsg.hardAllocTpCd.setValue(mdseMsg.invtyHardAllocTpCd.getValue());
        }

        awzc104001pmsg.reqFrtChrgMethCd.setValue(param.frtChrgMethCd.getValue());
        awzc104001pmsg.reqFrtChrgToCd.setValue(param.frtChrgToCd.getValue());
        awzc104001pmsg.shpgSvcLvlCd.setValue(param.shpgSvcLvlCd.getValue());
        awzc104001pmsg.rddDt.setValue(param.rddDt.getValue());
        awzc104001pmsg.rsdDt.setValue(param.rsdDt.getValue());
        awzc104001pmsg.billToCustCd.setValue(param.billToCustCd.getValue());
        awzc104001pmsg.sellToCustCd.setValue(param.sellToCustCd.getValue());
        awzc104001pmsg.shipToCustCd.setValue(param.shipToCustCd.getValue());
        awzc104001pmsg.shipToStCd.setValue(param.shipToStCd.getValue());
        awzc104001pmsg.shipToPostCd.setValue(param.shipToPostCd.getValue());
        awzc104001pmsg.shipCpltCd.setValue(param.shipCpltCd.getValue());
        awzc104001pmsg.setItemShipCpltFlg.setValue(ZYPConstant.FLG_OFF_N);
        awzc104001pmsg.uomCpltFlg.setValue(ZYPConstant.FLG_OFF_N);
        awzc104001pmsg.custUomCd.setValue(PKG_UOM.EACH);
        awzc104001pmsg.xxRqstQty.setValue(param.xxRqstQty.getValue());
        awzc104001pmsg.slsDt.setValue(param.slsDt.getValue());

        writeDebugLog("setHardAllocPmsg<End>");
        return awzc104001pmsg;

    }

    private INVTYTMsgArray getInvtyInfo(S21ApiMessageMap msgMap) {
        writeDebugLog("getInvtyInfo<Start>");

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();

        INVTYTMsg invtyMsg = new INVTYTMsg();

        invtyMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        invtyMsg.setConditionValue("mdseCd01", param.mdseCd.getValue());
        invtyMsg.setConditionValue("locStsCd01", param.locStsCd.getValue());
        invtyMsg.setConditionValue("invtyLocCd01", param.invtyLocCd.getValue());
        invtyMsg.setConditionValue("stkStsCd01", param.stkStsCd.getValue());
        invtyMsg.setConditionValue("invtyAvalQty01", BigDecimal.ZERO);
        invtyMsg.setSQLID("003");

        INVTYTMsgArray invtyMsgArray = (INVTYTMsgArray) S21ApiTBLAccessor.findByCondition(invtyMsg);

        writeDebugLog("getInvtyInfo<End>");
        return invtyMsgArray;

    }

    // 2017/04/12 S21_NA#Review structure Lv.1 Del End
//    private void softAllocNewProcess(S21ApiMessageMap msgMap, final String distItemFlg, final String expdShipDt, final MDSETMsg mdseMsg, final MDSETMsg setmdseMsg, final ONBATCH_TYPE onBatchType) {
//        writeDebugLog("softAllocNewProcess<Start>");
//
//        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//
//        if (ZYPConstant.FLG_OFF_N.equals(distItemFlg) && DIST_TP.NONE.equals(mdseMsg.invtyDistTpCd.getValue())) {
//            return;
//        }
//
//        if (hasValue(param.setMdseCd) && DIST_TP.DISTRIBUTION.equals(setmdseMsg.invtyDistTpCd.getValue())) {
//            return;
//        }
//
//        if (DIST_TP.DISTRIBUTION.equals(mdseMsg.invtyDistTpCd.getValue()) && !STK_STS.GOOD.equals(param.stkStsCd.getValue())) {
//            return;
//        }
//
//        String whSysTpCd = this.getWhSysTpCd(msgMap);
//
//        this.execAllocSegumentCreate(msgMap, whSysTpCd, onBatchType);
//
//        if (hasMessage(msgMap)) {
//            return;
//        }
//
//        List<NWZC103002PMsg> distributionList = this.getDistributionInfo(msgMap, whSysTpCd);
//
//        if (distributionList.isEmpty()) {
//
//            if (TRX_SRC_TP.RETAIL.equals(param.trxSrcTpCd.getValue())) {
//                msgMap.addXxMsgId(NWZM0451E);
//            }
//            return;
//        }
//
//        SHPG_PLNTMsgArray shpgPlnMsgArray = this.searchShpgPlanForUpdate(msgMap, SHPG_STS.VALIDATED);
//
//        if (shpgPlnMsgArray.getValidCount() == 0) {
//            msgMap.addXxMsgId(NWZM0075E);
//            return;
//        }
//
//        this.execSoftAllocAPIForNew(msgMap, distributionList, expdShipDt, mdseMsg, onBatchType);
//
//        writeDebugLog("softAllocNewProcess<End>");
//
//    }
    // 2017/04/12 S21_NA#Review structure Lv.1 Del End

    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//    private List<NWZC103002PMsg> getDistributionInfo(S21ApiMessageMap msgMap, final String whSysTpCd) {
//        writeDebugLog("getDistributionInfo <Start>");
//
//        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//
//        Map<String, Object> key = new HashMap<String, Object>();
//        key.put("glblCmpyCd", param.glblCmpyCd.getValue());
//        key.put("mdseCd", param.mdseCd.getValue());
//        key.put("slsDt", param.slsDt.getValue());
//        key.put("distAvalQty", BigDecimal.ZERO);
//        key.put("distPlnStsCd", DIST_PLN_STS.SUBMITTED);
//        key.put("trxSrcTpCd", param.trxSrcTpCd.getValue());
//        key.put("trxHdrNum", param.trxHdrNum.getValue());
//        key.put("trxLineNum", param.trxLineNum.getValue());
//        key.put("trxLineSubNum", param.trxLineSubNum.getValue());
//        key.put("invtyLocCd", param.invtyLocCd.getValue());
//
//        key.put("whCd", null);
//        if (TRX_SRC_TP.WAREHOUSE_TRANSFER.equals(param.trxSrcTpCd.getValue()) && !WH_SYS_TP.FULFILLMENT.equals(whSysTpCd)) {
//
//            key.put("whCd", whSysTpCd);
//        }
//
//        List<NWZC103002PMsg> distributionList = (List) ssmBatchClient.queryObjectList("getDistributionInfo", key);
//
//        writeDebugLog("getDistributionInfo <End>");
//        return distributionList;
//    }
//
//    private String getWhSysTpCd(S21ApiMessageMap msgMap) {
//        writeDebugLog("getWhSysTpCd <Start>");
//
//        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//
//        WHTMsg whMsg = new WHTMsg();
//
//        whMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
//        whMsg.setConditionValue("whCd01", param.shipToCustCd.getValue());
//        whMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
//        whMsg.setSQLID("014");
//
//        WHTMsgArray whMsgArray = (WHTMsgArray) S21ApiTBLAccessor.findByCondition(whMsg);
//
//        if (whMsgArray.getValidCount() == 0) {
//            return "";
//        }
//
//        writeDebugLog("getWhSysTpCd <End>");
//        return whMsgArray.no(0).whSysTpCd.getValue();
//    }
//
//    private void execSoftAllocAPIForNew(S21ApiMessageMap msgMap, final List<NWZC103002PMsg> awzc103002pmsgList, final String expdShipDt, final MDSETMsg mdseMsg, ONBATCH_TYPE onBatchType) {
//        writeDebugLog("execSoftAllocAPIForNew<Start>");
//
//        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//
//        NWZC103001PMsg awzc103001pmsg = new NWZC103001PMsg();
//        NWZC103001 awzc103001api = new NWZC103001();
//
//        awzc103001pmsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
//        awzc103001pmsg.xxRqstTpCd.setValue(NWZC103001.REQT_TP_NEW);
//        awzc103001pmsg.softAllocAutoFlg.setValue(ZYPConstant.FLG_ON_Y);
//        awzc103001pmsg.trxSrcTpCd.setValue(param.trxSrcTpCd.getValue());
//        awzc103001pmsg.trxHdrNum.setValue(param.trxHdrNum.getValue());
//        awzc103001pmsg.trxLineNum.setValue(param.trxLineNum.getValue());
//        awzc103001pmsg.trxLineSubNum.setValue(param.trxLineSubNum.getValue());
//        awzc103001pmsg.mdseTpCd.setValue(mdseMsg.mdseTpCd.getValue());
//        awzc103001pmsg.mdseCd.setValue(param.mdseCd.getValue());
//        awzc103001pmsg.invtyLocCd.setValue(param.invtyLocCd.getValue());
//        awzc103001pmsg.expdShipDt.setValue(expdShipDt);
//        ZYPEZDItemValueSetter.setValue(awzc103001pmsg.tocCd,param.slsRepTocCd);
//        awzc103001pmsg.shpgSvcLvlCd.setValue(param.shpgSvcLvlCd.getValue());
//        awzc103001pmsg.billToCustCd.setValue(param.billToCustCd.getValue());
//        awzc103001pmsg.sellToCustCd.setValue(param.sellToCustCd.getValue());
//        awzc103001pmsg.shipToCustCd.setValue(param.shipToCustCd.getValue());
//        awzc103001pmsg.uomCpltFlg.setValue(ZYPConstant.FLG_OFF_N);
//        awzc103001pmsg.uomCd.setValue(PKG_UOM.EACH);
//        awzc103001pmsg.xxRqstQty.setValue(param.xxRqstQty.getValue());
//        awzc103001pmsg.slsDt.setValue(param.slsDt.getValue());
//
//        awzc103001api.execute(awzc103001pmsg, awzc103002pmsgList, onBatchType);
//
//        if (awzc103001pmsg.xxMsgIdList.getValidCount() > 0) {
//            for (int i = 0; i < awzc103001pmsg.xxMsgIdList.getValidCount(); i++) {
//                String msgId = awzc103001pmsg.xxMsgIdList.no(i).xxMsgId.getValue();
//                msgMap.addXxMsgId(msgId);
//                writeDebugLog("msgId　:" + msgId);
//            }
//            return;
//        }
//
//        BigDecimal softAllocQty = BigDecimal.ZERO;
//        for (int i = 0; i < awzc103001pmsg.xxAllocInfo.getValidCount(); i++) {
//            softAllocQty = softAllocQty.add(awzc103001pmsg.xxAllocInfo.no(i).softAllocQty.getValue());
//
//        }
//        if (ZYPConstant.FLG_OFF_N.equals(param.xxPrtlAcptFlg.getValue())) {
//            if (!softAllocQty.equals(param.xxRqstQty.getValue())) {
//                param.allocQty.setValue(softAllocQty);
//                msgMap.addXxMsgId(NWZM0451E);
//                return;
//            }
//        }
//
//        if (DIST_TP.DISTRIBUTION.equals(mdseMsg.invtyDistTpCd.getValue()) && MDSE_TP.SET.equals(mdseMsg.mdseTpCd.getValue())) {
//            param.allocQty.setValue(softAllocQty);
//        }
//
//        writeDebugLog("execSoftAllocAPIForNew<End>");
//
//    }
//
//    private void execAllocSegumentCreate(S21ApiMessageMap msgMap, final String whSysTpCd, final ONBATCH_TYPE onBatchType) {
//
//        writeDebugLog("execAllocSegumentCreate<Start>");
//
//        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//
//        NWZC103001PMsg awzc103001pmsg = new NWZC103001PMsg();
//        List<NWZC103002PMsg> awzc103002pmsgList = new ArrayList<NWZC103002PMsg>();
//        NWZC103001 awzc103001api = new NWZC103001();
//
//        awzc103001pmsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
//        awzc103001pmsg.xxRqstTpCd.setValue(NWZC103001.REQT_TP_SEGMENT_CREATE);
//        awzc103001pmsg.xxAllocSegRemkFlg.setValue(ZYPConstant.FLG_ON_Y);
//        awzc103001pmsg.trxSrcTpCd.setValue(param.trxSrcTpCd.getValue());
//        awzc103001pmsg.trxHdrNum.setValue(param.trxHdrNum.getValue());
//        awzc103001pmsg.trxLineNum.setValue(param.trxLineNum.getValue());
//        awzc103001pmsg.trxLineSubNum.setValue(param.trxLineSubNum.getValue());
//        awzc103001pmsg.whSysTpCd.setValue(whSysTpCd);
//        awzc103001pmsg.mdseCd.setValue(param.mdseCd.getValue());
//        awzc103001pmsg.invtyLocCd.setValue(param.invtyLocCd.getValue());
//        ZYPEZDItemValueSetter.setValue(awzc103001pmsg.tocCd,param.slsRepTocCd);
//        awzc103001pmsg.billToCustCd.setValue(param.billToCustCd.getValue());
//        awzc103001pmsg.sellToCustCd.setValue(param.sellToCustCd.getValue());
//        awzc103001pmsg.shipToCustCd.setValue(param.shipToCustCd.getValue());
//        awzc103001pmsg.slsDt.setValue(param.slsDt.getValue());
//
//        awzc103001api.execute(awzc103001pmsg, awzc103002pmsgList, onBatchType);
//
//        if (awzc103001pmsg.xxMsgIdList.getValidCount() > 0) {
//            for (int i = 0; i < awzc103001pmsg.xxMsgIdList.getValidCount(); i++) {
//                String msgId = awzc103001pmsg.xxMsgIdList.no(i).xxMsgId.getValue();
//                msgMap.addXxMsgId(msgId);
//                writeDebugLog("msgId　:" + msgId);
//            }
//        }
//
//        writeDebugLog("execAllocSegumentCreate<End>");
//
//    }
    // 2017/04/12 S21_NA#Review structure Lv.1 Del End

    private TRX_SRC_TPTMsg searchTrxSrcTp(S21ApiMessageMap msgMap) {
        writeDebugLog("searchTrxSrcTp<Start>");

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();

        TRX_SRC_TPTMsg trxSrcTpMsg = new TRX_SRC_TPTMsg();

        trxSrcTpMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        trxSrcTpMsg.trxSrcTpCd.setValue(param.trxSrcTpCd.getValue());

        trxSrcTpMsg = (TRX_SRC_TPTMsg) S21CacheTBLAccessor.findByKey(trxSrcTpMsg);

        writeDebugLog("searchTrxSrcTp<End>");

        return trxSrcTpMsg;

    }

    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//    private void execSoftAllocAPIForCancel(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {
//        writeDebugLog("execSoftAllocAPIForCancel<Start>");
//
//        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//
//        NWZC103001PMsg awzc103001pmsg = new NWZC103001PMsg();
//        List<NWZC103002PMsg> awzc103002pmsgList = new ArrayList<NWZC103002PMsg>();
//        NWZC103001 awzc103001api = new NWZC103001();
//
//        awzc103001pmsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
//        awzc103001pmsg.xxRqstTpCd.setValue(NWZC103001.REQT_TP_CANCEL_OD_LEVEL);
//        awzc103001pmsg.trxHdrNum.setValue(param.trxHdrNum.getValue());
//        awzc103001pmsg.trxLineNum.setValue(param.trxLineNum.getValue());
//        awzc103001pmsg.trxLineSubNum.setValue(param.trxLineSubNum.getValue());
//        awzc103001pmsg.trxSrcTpCd.setValue(param.trxSrcTpCd.getValue());
//        awzc103001pmsg.slsDt.setValue(param.slsDt.getValue());
//
//        awzc103001api.execute(awzc103001pmsg, awzc103002pmsgList, onBatchType);
//
//        if (awzc103001pmsg.xxMsgIdList.getValidCount() > 0) {
//            for (int i = 0; i < awzc103001pmsg.xxMsgIdList.getValidCount(); i++) {
//                String msgId = awzc103001pmsg.xxMsgIdList.no(i).xxMsgId.getValue();
//                writeDebugLog("msgId :" + msgId);
//                msgMap.addXxMsgId(msgId);
//            }
//        }
//        writeDebugLog("execSoftAllocAPIForCancel<End>");
//    }
    // 2017/04/12 S21_NA#Review structure Lv.1 Del End

    private void execHardAllocAPIForCancel(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {
        writeDebugLog("execHardAllocAPIForCancel<Start>");

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();

        NWZC104001PMsg awzc104001pmsg = new NWZC104001PMsg();
        List<NWZC104002PMsg> awzc104002pmsgList = new ArrayList<NWZC104002PMsg>();
        NWZC104001 awzc104001api = new NWZC104001();

        awzc104001pmsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        awzc104001pmsg.xxRqstTpCd.setValue(NWZC104001.REQ_TP_ORDER_CANCEL);
        awzc104001pmsg.trxHdrNum.setValue(param.trxHdrNum.getValue());
        awzc104001pmsg.trxLineNum.setValue(param.trxLineNum.getValue());
        awzc104001pmsg.trxLineSubNum.setValue(param.trxLineSubNum.getValue());
        awzc104001pmsg.trxSrcTpCd.setValue(param.trxSrcTpCd.getValue());
        awzc104001pmsg.slsDt.setValue(param.slsDt.getValue());

        awzc104001api.execute(awzc104001pmsg, awzc104002pmsgList, onBatchType);

        if (awzc104001pmsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < awzc104001pmsg.xxMsgIdList.getValidCount(); i++) {
                String msgId = awzc104001pmsg.xxMsgIdList.no(i).xxMsgId.getValue();
                writeDebugLog("msgId :" + msgId);
                msgMap.addXxMsgId(msgId);
            }
        }
        writeDebugLog("execHardAllocAPIForCancel<End>");
    }

    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//    private void updateRossOdrCancel(S21ApiMessageMap msgMap, ROSS_ORDTMsg rossOdrMsg) {
//        writeDebugLog("updateRossOdrCancel<Start>");
//
//        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//
//        BigDecimal orderQtySum = this.getSumOrdQty(param);
//
//        if (orderQtySum == null) {
//            rossOdrMsg.ordQty.setValue(BigDecimal.ZERO);
//        } else {
//            rossOdrMsg.ordQty.setValue(orderQtySum);
//        }
//
//        this.updateRossOdr(msgMap, rossOdrMsg);
//
//        writeDebugLog("updateRossOdrCancel<End>");
//
//    }
    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start

    private BigDecimal updateShpgPlanCancel(S21ApiMessageMap msgMap, List<Map<String, Object>> shpgPlanCancelList, BigDecimal cancelImpossibleQty) {

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
        BigDecimal cancelQty = BigDecimal.ZERO;
        BigDecimal totalOrdQty = BigDecimal.ZERO;

        for (Map<String, Object> shpgPlanCancel : shpgPlanCancelList) {

            BigDecimal ordQty = (BigDecimal)shpgPlanCancel.get("ORD_QTY");

            if (ZYPConstant.FLG_ON_Y.equals((String)shpgPlanCancel.get("SO_PRINT_FLG"))) {
                cancelImpossibleQty = cancelImpossibleQty.subtract(ordQty);
            } else {
                totalOrdQty = totalOrdQty.add(ordQty);
            }
        }

        for (Map<String, Object> shpgPlanCancel : shpgPlanCancelList) {

            if (ZYPConstant.FLG_OFF_N.equals((String)shpgPlanCancel.get("SO_PRINT_FLG"))) {

                String shpgPlnNum = (String)shpgPlanCancel.get("SHPG_PLN_NUM");
                BigDecimal ordQty = (BigDecimal)shpgPlanCancel.get("ORD_QTY");

                SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
                shpgPlnMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                shpgPlnMsg.shpgPlnNum.setValue(shpgPlnNum);

                shpgPlnMsg = (SHPG_PLNTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(shpgPlnMsg);

                if (cancelImpossibleQty.compareTo(ordQty) > 0) {

                    cancelImpossibleQty = cancelImpossibleQty.subtract(ordQty);

                    cancelQty = cancelQty.add(shpgPlnMsg.ordQty.getValue());

                    shpgPlnMsg.shipPlnCancDt.setValue(param.slsDt.getValue());
                    shpgPlnMsg.shipPlnCancFlg.setValue(ZYPConstant.FLG_ON_Y);
                    shpgPlnMsg.shpgStsCd.setValue(SHPG_STS.CANCELLED);
                    shpgPlnMsg.avalHardAllocQty.setValue(BigDecimal.ZERO);
                    shpgPlnMsg.avalSoftAllocQty.setValue(BigDecimal.ZERO);
                    shpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);
                    shpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);
                    shpgPlnMsg.softAllocQty.setValue(BigDecimal.ZERO);
                    shpgPlnMsg.ordQty.setValue(BigDecimal.ZERO);

                    this.updateShpgPlan(msgMap, shpgPlnMsg);

                    if (hasMessage(msgMap)) {
                        return null;
                    }

                } else {

                    if (BigDecimal.ZERO.compareTo(cancelImpossibleQty) == 0) {

                        shpgPlnMsg.shipPlnCancDt.setValue(param.slsDt.getValue());
                        shpgPlnMsg.shipPlnCancFlg.setValue(ZYPConstant.FLG_ON_Y);
                        shpgPlnMsg.shpgStsCd.setValue(SHPG_STS.CANCELLED);
                        shpgPlnMsg.avalHardAllocQty.setValue(BigDecimal.ZERO);
                        shpgPlnMsg.avalSoftAllocQty.setValue(BigDecimal.ZERO);
                        shpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);
                        shpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);
                        shpgPlnMsg.softAllocQty.setValue(BigDecimal.ZERO);

                        cancelQty = cancelQty.add(shpgPlnMsg.ordQty.getValue());

                    } else {

                        SHPG_PLNTMsg newShpgPlnMsg = new SHPG_PLNTMsg();
                        EZDMsg.copy(shpgPlnMsg, null, newShpgPlnMsg, null);

                        newShpgPlnMsg.shpgPlnNum.setValue(this.getShipgPlnSQ());

                        newShpgPlnMsg.shipPlnCancDt.setValue(param.slsDt.getValue());
                        newShpgPlnMsg.shipPlnCancFlg.setValue(ZYPConstant.FLG_ON_Y);
                        newShpgPlnMsg.shpgStsCd.setValue(SHPG_STS.CANCELLED);
                        newShpgPlnMsg.avalHardAllocQty.setValue(BigDecimal.ZERO);
                        newShpgPlnMsg.avalSoftAllocQty.setValue(BigDecimal.ZERO);
                        newShpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);
                        newShpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);
                        newShpgPlnMsg.softAllocQty.setValue(BigDecimal.ZERO);
                        newShpgPlnMsg.ordQty.setValue(newShpgPlnMsg.ordQty.getValue().subtract(cancelImpossibleQty));

                        this.setShpgPlanTotalAmt(newShpgPlnMsg, newShpgPlnMsg.ordQty.getValue());

                        S21ApiTBLAccessor.insert(newShpgPlnMsg);

                        if (!RTNCD_NORMAL_END.equals(newShpgPlnMsg.getReturnCode())) {
                            msgMap.addXxMsgId(NWZM0448E);
                            return null;
                        }

                        cancelQty = cancelQty.add(shpgPlnMsg.ordQty.getValue().subtract(cancelImpossibleQty));

                        shpgPlnMsg.ordQty.setValue(cancelImpossibleQty);

                        if (shpgPlnMsg.softAllocQty.getValue().compareTo(cancelImpossibleQty) > 0) {

                            shpgPlnMsg.softAllocQty.setValue(cancelImpossibleQty);
                        }

                        this.setShpgPlanTotalAmt(shpgPlnMsg, shpgPlnMsg.ordQty.getValue());
                    }

                    cancelImpossibleQty = BigDecimal.ZERO;

                    this.updateShpgPlan(msgMap, shpgPlnMsg);

                    if (hasMessage(msgMap)) {
                        return null;
                    }
                }
            }
        }

        return cancelQty;
    }

    private BigDecimal updateShpgPlanCancelComp(S21ApiMessageMap msgMap, final List<Map<String, Object>> shpgPlanCancelList, BigDecimal cancelImpossibleQty) {

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
        BigDecimal totalOrdQty = BigDecimal.ZERO;

        String childMdseCd = (String) shpgPlanCancelList.get(0).get("MDSE_CD");
        String prntMdseCd = (String) shpgPlanCancelList.get(0).get("SET_MDSE_CD");

        BigDecimal compQty = getCompQty(param, prntMdseCd, childMdseCd);

        if (BigDecimal.ZERO.compareTo(compQty) != 0) {
            cancelImpossibleQty = cancelImpossibleQty.multiply(compQty);
        }

        for (Map<String, Object> shpgPlanCancel : shpgPlanCancelList) {

            BigDecimal ordQty = (BigDecimal)shpgPlanCancel.get("ORD_QTY");

            if (ZYPConstant.FLG_ON_Y.equals((String)shpgPlanCancel.get("SO_PRINT_FLG"))) {
                cancelImpossibleQty = cancelImpossibleQty.subtract(ordQty);
            } else {
                totalOrdQty = totalOrdQty.add(ordQty);
            }
        }

        BigDecimal cancelQty = totalOrdQty.subtract(cancelImpossibleQty);
        BigDecimal wkCancelQty = totalOrdQty.subtract(cancelImpossibleQty);

        for (Map<String, Object> shpgPlanCancel : shpgPlanCancelList) {

            if (ZYPConstant.FLG_OFF_N.equals((String)shpgPlanCancel.get("SO_PRINT_FLG"))) {

                String shpgPlanNum = (String)shpgPlanCancel.get("SHPG_PLN_NUM");

                SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
                shpgPlnMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                shpgPlnMsg.shpgPlnNum.setValue(shpgPlanNum);

                shpgPlnMsg = (SHPG_PLNTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(shpgPlnMsg);

                if (shpgPlnMsg == null) {
                    msgMap.addXxMsgId(NWZM0075E);
                    return null;
                }

                String setMdseCd = shpgPlnMsg.setMdseCd.getValue();

                if (ZYPCommonFunc.hasValue(setMdseCd) && BigDecimal.ZERO.compareTo(cancelImpossibleQty) != 0) {

                    wkCancelQty = updateShpgPlanCancelComp(msgMap, shpgPlnMsg, wkCancelQty);

                    if (hasMessage(msgMap)) {
                        return null;
                    }

                    if (BigDecimal.ZERO.compareTo(wkCancelQty) == 0) {
                        break;
                    }

                } else {

                    shpgPlnMsg.shipPlnCancDt.setValue(param.slsDt.getValue());
                    shpgPlnMsg.shipPlnCancFlg.setValue(ZYPConstant.FLG_ON_Y);
                    shpgPlnMsg.shpgStsCd.setValue(SHPG_STS.CANCELLED);
                    shpgPlnMsg.avalHardAllocQty.setValue(BigDecimal.ZERO);
                    shpgPlnMsg.avalSoftAllocQty.setValue(BigDecimal.ZERO);
                    shpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);
                    shpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);
                    shpgPlnMsg.softAllocQty.setValue(BigDecimal.ZERO);

                    this.updateShpgPlan(msgMap, shpgPlnMsg);

                    if (hasMessage(msgMap)) {
                        return null;
                    }
                }
            }
        }
        return cancelQty;
    }

    private BigDecimal updateShpgPlanCancelComp(S21ApiMessageMap msgMap, SHPG_PLNTMsg shpgPlnMsg, BigDecimal cancelQty) {

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();

        if (BigDecimal.ZERO.compareTo(shpgPlnMsg.ordQty.getValue().subtract(cancelQty)) >= 0) {

            shpgPlnMsg.shipPlnCancDt.setValue(param.slsDt.getValue());
            shpgPlnMsg.shipPlnCancFlg.setValue(ZYPConstant.FLG_ON_Y);
            shpgPlnMsg.shpgStsCd.setValue(SHPG_STS.CANCELLED);
            shpgPlnMsg.avalHardAllocQty.setValue(BigDecimal.ZERO);
            shpgPlnMsg.avalSoftAllocQty.setValue(BigDecimal.ZERO);
            shpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);
            shpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);
            shpgPlnMsg.softAllocQty.setValue(BigDecimal.ZERO);

            this.updateShpgPlan(msgMap, shpgPlnMsg);

            return cancelQty.subtract(shpgPlnMsg.ordQty.getValue());

        } else {

            SHPG_PLNTMsg origShpgPlnMsg = new SHPG_PLNTMsg();
            EZDMsg.copy(shpgPlnMsg, null, origShpgPlnMsg, null);

            shpgPlnMsg.shipPlnCancDt.setValue(param.slsDt.getValue());
            shpgPlnMsg.shipPlnCancFlg.setValue(ZYPConstant.FLG_ON_Y);
            shpgPlnMsg.shpgStsCd.setValue(SHPG_STS.CANCELLED);
            shpgPlnMsg.avalHardAllocQty.setValue(BigDecimal.ZERO);
            shpgPlnMsg.avalSoftAllocQty.setValue(BigDecimal.ZERO);
            shpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);
            shpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);

            this.updateShpgPlan(msgMap, shpgPlnMsg);

            if (!RTNCD_NORMAL_END.equals(shpgPlnMsg.getReturnCode())) {
                msgMap.addXxMsgId(NWZM0448E);
                return BigDecimal.ZERO;
            }

//            SHPG_PLNTMsg newShpgPlnMsg = new SHPG_PLNTMsg();
//            EZDMsg.copy(shpgPlnMsg, null, newShpgPlnMsg, null);
//
//            newShpgPlnMsg.shpgPlnNum.setValue(this.getShipgPlnSQ());
//
//            newShpgPlnMsg.shipPlnCancDt.setValue(param.slsDt.getValue());
//            newShpgPlnMsg.shipPlnCancFlg.setValue(ZYPConstant.FLG_ON_Y);
//            newShpgPlnMsg.shpgStsCd.setValue(SHPG_STS.CANCELLED);
//            newShpgPlnMsg.avalHardAllocQty.setValue(BigDecimal.ZERO);
//            newShpgPlnMsg.avalSoftAllocQty.setValue(BigDecimal.ZERO);
//            newShpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);
//            newShpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);
//            newShpgPlnMsg.softAllocQty.setValue(BigDecimal.ZERO);
//            newShpgPlnMsg.ordQty.setValue(cancelQty);
//
//            this.setShpgPlanTotalAmt(newShpgPlnMsg, newShpgPlnMsg.ordQty.getValue());
//
//            S21ApiTBLAccessor.insert(newShpgPlnMsg);
//
//            if (!RTNCD_NORMAL_END.equals(newShpgPlnMsg.getReturnCode())) {
//                msgMap.addXxMsgId(NWZM0448E);
//                return BigDecimal.ZERO;
//            }

            origShpgPlnMsg.shpgPlnNum.setValue(this.getShipgPlnSQ());
            ZYPEZDItemValueSetter.setValue(origShpgPlnMsg.shpgStsCd, SHPG_STS.VALIDATED);
            ZYPEZDItemValueSetter.setValue(origShpgPlnMsg.rteStsCd, RTE_STS.UN_ROUTED);
            ZYPEZDItemValueSetter.setValue(origShpgPlnMsg.spTotDealFrtAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(origShpgPlnMsg.spTotFuncFrtAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(origShpgPlnMsg.avalHardAllocQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(origShpgPlnMsg.avalSoftAllocQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(origShpgPlnMsg.avalSoQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(origShpgPlnMsg.avalPoQty, BigDecimal.ZERO);
            origShpgPlnMsg.soNum.clear();
            origShpgPlnMsg.poOrdNum.clear();
            origShpgPlnMsg.thirdPtyInvNum.clear();
            origShpgPlnMsg.actlShpgSvcLvlCd.clear();
            origShpgPlnMsg.actlFrtChrgToCd.clear();
            origShpgPlnMsg.actlFrtChrgMethCd.clear();
            origShpgPlnMsg.carrCd.clear();
            origShpgPlnMsg.carrNm.clear();
            origShpgPlnMsg.bolNum.clear();
            origShpgPlnMsg.proNum.clear();
            origShpgPlnMsg.invNum.clear();
            origShpgPlnMsg.psdDt.clear();
            origShpgPlnMsg.pddDt.clear();
            origShpgPlnMsg.actlShipDt.clear();
            origShpgPlnMsg.actlArvDt.clear();
            origShpgPlnMsg.shipCmntFirstLineTxt.clear();
            origShpgPlnMsg.shipCmntScdLineTxt.clear();
            origShpgPlnMsg.shipCmntThirdLineTxt.clear();
            origShpgPlnMsg.shipCmntFrthLineTxt.clear();
            ZYPEZDItemValueSetter.setValue(origShpgPlnMsg.shipAvalFlg, ZYPConstant.FLG_OFF_N);

            ZYPEZDItemValueSetter.setValue(origShpgPlnMsg.soCloseFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(origShpgPlnMsg.shipPlnHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(origShpgPlnMsg.shipPlnCancFlg, ZYPConstant.FLG_OFF_N);
            shpgPlnMsg.shipPlnCancDt.clear();
            ZYPEZDItemValueSetter.setValue(origShpgPlnMsg.crHldQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(origShpgPlnMsg.slsHldQty,BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(origShpgPlnMsg.crChkQty,BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(origShpgPlnMsg.setPrcDetGrpSq, BigDecimal.ZERO);
            origShpgPlnMsg.reqShpgMethCd.clear();
            origShpgPlnMsg.reqShpgTermCd.clear();
            origShpgPlnMsg.doNum.clear();
            origShpgPlnMsg.actlShpgMethCd.clear();
            origShpgPlnMsg.actlShpgTermCd.clear();
            origShpgPlnMsg.etaDt.clear();
            ZYPEZDItemValueSetter.setValue(origShpgPlnMsg.sendEmlCpltFlg, ZYPConstant.FLG_OFF_N);
            origShpgPlnMsg.ordQty.setValue(origShpgPlnMsg.ordQty.getValue().subtract(cancelQty));

            if (origShpgPlnMsg.softAllocQty.getValue().compareTo(origShpgPlnMsg.ordQty.getValue()) > 0) {
                origShpgPlnMsg.softAllocQty.setValue(origShpgPlnMsg.ordQty.getValue());
            }

            this.setShpgPlanTotalAmt(origShpgPlnMsg, origShpgPlnMsg.ordQty.getValue());

            S21FastTBLAccessor.insert(origShpgPlnMsg);
            //this.updateShpgPlan(msgMap, origShpgPlnMsg);

            return BigDecimal.ZERO;
        }
    }

    private void setShpgPlanTotalAmt(SHPG_PLNTMsg shpgPlnMsg, final BigDecimal setOrdQty) {

        shpgPlnMsg.spTotDealSlsAmt.setValue(roundHalfUp(multiply(shpgPlnMsg.spDealUnitPrcAmt.getValue(), setOrdQty)));
        shpgPlnMsg.spTotDealNetAmt.setValue(roundHalfUp(multiply(shpgPlnMsg.spDealNetUnitPrcAmt.getValue(), setOrdQty)));
        shpgPlnMsg.spTotFuncSlsAmt.setValue(roundHalfUp(multiply(shpgPlnMsg.spFuncUnitPrcAmt.getValue(), setOrdQty)));
        shpgPlnMsg.spTotFuncNetAmt.setValue(roundHalfUp(multiply(shpgPlnMsg.spFuncNetUnitPrcAmt.getValue(), setOrdQty)));
    }

    private MDSETMsg getMdse(S21ApiMessageMap msgMap, final String mdseCd) {

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();

        if (!hasValue(param.mdseCd)) {
            msgMap.addXxMsgId(NWZM0021E);
            return null;
        }

        MDSETMsg mdseMsg = new MDSETMsg();

        mdseMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        mdseMsg.mdseCd.setValue(mdseCd);

        mdseMsg = (MDSETMsg) S21FastTBLAccessor.findByKey(mdseMsg);

        if (null == mdseMsg) {
            msgMap.addXxMsgId(NWZM0364E);
            return null;
        }

        return mdseMsg;

    }

    // Del Start S21_NA#5872
//    private MDSE_WH_CONDTMsg getMdseWHCond(S21ApiMessageMap msgMap) {
//
//        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//
//        MDSE_WH_CONDTMsg mdseWhCondMsg = new MDSE_WH_CONDTMsg();
//
//        mdseWhCondMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
//        mdseWhCondMsg.mdseCd.setValue(param.mdseCd.getValue());
//        mdseWhCondMsg.whCd.setValue(param.invtyLocCd.getValue());
//
//        mdseWhCondMsg = (MDSE_WH_CONDTMsg) S21FastTBLAccessor.findByKey(mdseWhCondMsg);
//
//        return mdseWhCondMsg;
//
//    }
    // Del End S21_NA#5872

    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//    private ROSS_ORDTMsgArray searchRossOdrComp(S21ApiMessageMap msgMap) {
//
//        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//
//        ROSS_ORDTMsg rossOdrMsg = new ROSS_ORDTMsg();
//
//        rossOdrMsg.setSQLID("002");
//        rossOdrMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
//        rossOdrMsg.setConditionValue("rossOrdNum01", param.trxHdrNum.getValue());
//        rossOdrMsg.setConditionValue("rossDtlLineNum01", param.trxLineNum.getValue());
//
//        ROSS_ORDTMsgArray rossOdrMsgArray = (ROSS_ORDTMsgArray) S21ApiTBLAccessor.findByCondition(rossOdrMsg);
//
//        return rossOdrMsgArray;
//
//    }
//
//    private ROSS_ORDTMsg searchRossOdrForUpdate(S21ApiMessageMap msgMap) {
//
//        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//
//        ROSS_ORDTMsg rossOdrMsg = new ROSS_ORDTMsg();
//
//        rossOdrMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
//        rossOdrMsg.rossOrdNum.setValue(param.trxHdrNum.getValue());
//        rossOdrMsg.rossDtlLineNum.setValue(param.trxLineNum.getValue());
//        rossOdrMsg.rossDtlLineSubNum.setValue(param.trxLineSubNum.getValue());
//
//        rossOdrMsg = (ROSS_ORDTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(rossOdrMsg);
//
//        return rossOdrMsg;
//
//    }
//
//    private void updateRossOdr(S21ApiMessageMap msgMap, ROSS_ORDTMsg rossOdrMsg) {
//
//        S21ApiTBLAccessor.update(rossOdrMsg);
//
//        if (!RTNCD_NORMAL_END.equals(rossOdrMsg.getReturnCode())) {
//
//            msgMap.addXxMsgId(NWZM0450E);
//        }
//    }
    // 2017/04/12 S21_NA#Review structure Lv.1 Del End

    private void updateShpgPlan(S21ApiMessageMap msgMap, SHPG_PLNTMsg shpgPlnMsg) {

        S21ApiTBLAccessor.update(shpgPlnMsg);

        if (!RTNCD_NORMAL_END.equals(shpgPlnMsg.getReturnCode())) {

            msgMap.addXxMsgId(NWZM0078E);
        }
    }

    private void chkVendorDrop(S21ApiMessageMap msgMap) {
        writeDebugLog("chkVendorDrop<Start>");

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();

        if (!hasValue(param.invtyLocCd)) {
            msgMap.addXxMsgId(NWZM0047E);
            return;
        }

        VNDTMsg vndMsg = new VNDTMsg();

        vndMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        vndMsg.setConditionValue("vndCd01", param.invtyLocCd.getValue());
        vndMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        vndMsg.setSQLID("010");

        VNDTMsgArray vndMsgArray = (VNDTMsgArray) S21ApiTBLAccessor.findByCondition(vndMsg);

        if (vndMsgArray.getValidCount() > 0) {
            msgMap.addXxMsgId(NWZM0447I);
        }
        writeDebugLog("chkVendorDrop<End>");
    }

    private void chkMdse(S21ApiMessageMap msgMap, final MDSETMsg mdseMsg) {

        if (hasMessage(msgMap)) {
            return;
        }

        if (!MDSE_TP.SET.equals(mdseMsg.mdseTpCd.getValue()) && ZYPConstant.FLG_OFF_N.equals(mdseMsg.invtyCtrlFlg.getValue())) {
            msgMap.addXxMsgId(NWZM0366I);
        }

    }

    private OTBD_CARR_VTMsg getCarrNm(S21ApiMessageMap msgMap) {

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();

        OTBD_CARR_VTMsg inMsg = new OTBD_CARR_VTMsg();

        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        inMsg.setConditionValue("carrCd01", param.carrCd.getValue());

        OTBD_CARR_VTMsgArray carrMsgArray = (OTBD_CARR_VTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);

        if (carrMsgArray.getValidCount() == 0) {
            msgMap.addXxMsgId(NWZM0067E);
            return null;
        }

        return carrMsgArray.no(0);

    }

//    private String getExpdShipDt(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {
//        writeDebugLog("getExpdShipDt<Start>");
//        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//
//        String expdShipDt = "";
//
//        if (hasValue(param.rsdDt)) {
//
//            expdShipDt = param.rsdDt.getValue();
//        } else {
//            expdShipDt = this.execLeadtimeCalcAPI(msgMap, onBatchType);
//
//        }
//        writeDebugLog("getExpdShipDt<End>");
//        return expdShipDt;
//
//    }

    private String execLeadtimeCalcAPI(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        writeDebugLog("execLeadtimeCalcAPI<Start>");

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
        NWZC002001PMsg awzc002001pmsg = new NWZC002001PMsg();

        awzc002001pmsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        awzc002001pmsg.mdseCd.setValue(param.mdseCd.getValue());
        awzc002001pmsg.ordQty.setValue(param.xxRqstQty.getValue());
        awzc002001pmsg.shpgSvcLvlCd.setValue(param.shpgSvcLvlCd.getValue());
        awzc002001pmsg.frtChrgMethCd.setValue(param.frtChrgMethCd.getValue());
        awzc002001pmsg.frtChrgToCd.setValue(param.frtChrgToCd.getValue());
        awzc002001pmsg.xxRddDt.setValue(param.rddDt.getValue());
        awzc002001pmsg.invtyLocCd.setValue(param.invtyLocCd.getValue());
        awzc002001pmsg.shipToCustCd.setValue(param.shipToCustCd.getValue());
        awzc002001pmsg.sellToCustCd.setValue(param.sellToCustCd.getValue());
        awzc002001pmsg.uomCd.setValue(PKG_UOM.EACH);
        awzc002001pmsg.slsDt.setValue(param.slsDt.getValue());
        awzc002001pmsg.shipToPostCd.setValue(param.shipToPostCd.getValue());
        awzc002001pmsg.shipToStCd.setValue(param.shipToStCd.getValue());

        NWZC002001 awzc002001Api = new NWZC002001();

        awzc002001Api.execute(awzc002001pmsg, onBatchType);

        if (awzc002001pmsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < awzc002001pmsg.xxMsgIdList.getValidCount(); i++) {
                String msgId = awzc002001pmsg.xxMsgIdList.no(i).xxMsgId.getValue();
                msgMap.addXxMsgId(msgId);
            }
            return null;
        }

        writeDebugLog("execLeadtimeCalcAPI<End>");
        return awzc002001pmsg.xxPsdDt.getValue();
    }

    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//    private void insertRossOrd(S21ApiMessageMap msgMap, final MDSETMsg mdseMsg, final String expdShipDt, final String distItemFlg, final ONBATCH_TYPE onBatchType) {
//        writeDebugLog("insertRossOrd<Start>");
//
//        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//
//        ROSS_ORDTMsg insRossOdrMsg = new ROSS_ORDTMsg();
//
//        insRossOdrMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
//        insRossOdrMsg.rossOrdNum.setValue(param.trxHdrNum.getValue());
//        insRossOdrMsg.rossDtlLineNum.setValue(param.trxLineNum.getValue());
//        insRossOdrMsg.rossDtlLineSubNum.setValue(param.trxLineSubNum.getValue());
//        insRossOdrMsg.custIssPoNum.setValue(param.custIssPoNum.getValue());
//        insRossOdrMsg.rossOrdTpCd.setValue(param.rossOrdTpCd.getValue());
//        insRossOdrMsg.rossOrdSubmtTs.setValue(param.xxOrdTs.getValue());
//        insRossOdrMsg.expdShipDt.setValue(expdShipDt);
//        insRossOdrMsg.distItemFlg.setValue(distItemFlg);
//        insRossOdrMsg.ordQty.setValue(param.xxRqstQty.getValue());
//
//        EZDTBLAccessor.insert(insRossOdrMsg);
//
//        if (!RTNCD_NORMAL_END.equals(insRossOdrMsg.getReturnCode())) {
//            msgMap.addXxMsgId(NWZM0449E);
//        }
//
//        writeDebugLog("insertRossOrd<End>");
//    }
    // 2017/04/12 S21_NA#Review structure Lv.1 Del End

    private String getDistItemFlg(S21ApiMessageMap msgMap, final MDSETMsg mdseMsg) {

        writeDebugLog("getDistItemFlg<Start>");

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();

        if (MDSE_TP.SET.equals(mdseMsg.mdseTpCd.getValue()) && DIST_TP.DISTRIBUTION.equals(mdseMsg.invtyDistTpCd.getValue())) {
            param.stkStsCd.setValue(STK_STS.GOOD);
        }

        if (hasValue(param.setMdseCd)) {

            MDSETMsg setMdseMsg = this.getMdse(msgMap, param.setMdseCd.getValue());

            if (hasMessage(msgMap)) {
                return null;
            }

            if (DIST_TP.DISTRIBUTION.equals(setMdseMsg.invtyDistTpCd.getValue())) {

                return ZYPConstant.FLG_ON_Y;
            }
        }

        if (!STK_STS.GOOD.equals(param.stkStsCd.getValue())) {
            return ZYPConstant.FLG_OFF_N;
        }

        if (DIST_TP.DISTRIBUTION.equals(mdseMsg.invtyDistTpCd.getValue())) {
            return ZYPConstant.FLG_ON_Y;
        }

        writeDebugLog("getDistItemFlg<End>");

        return ZYPConstant.FLG_OFF_N;

    }

    private void insertShpgPln(S21ApiMessageMap msgMap, final MDSETMsg mdseMsg, final String distItemFlg, final BigDecimal setOrdQty) {
        writeDebugLog("insertShpgPln<Start>");

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
        SHPG_PLNTMsg insShpgPlnMsg = new SHPG_PLNTMsg();
        insShpgPlnMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        insShpgPlnMsg.shpgPlnNum.setValue(this.getShipgPlnSQ());
        insShpgPlnMsg.shpgPlnDplyLineNum.setValue(this.getMaxDisplayNumber(param));
        insShpgPlnMsg.trxSrcTpCd.setValue(param.trxSrcTpCd.getValue());
        insShpgPlnMsg.trxHdrNum.setValue(param.trxHdrNum.getValue());
        insShpgPlnMsg.trxLineNum.setValue(param.trxLineNum.getValue());
        insShpgPlnMsg.trxLineSubNum.setValue(param.trxLineSubNum.getValue());
        insShpgPlnMsg.billToCustCd.setValue(param.billToCustCd.getValue());
        insShpgPlnMsg.sellToCustCd.setValue(param.sellToCustCd.getValue());
        insShpgPlnMsg.shipToCustCd.setValue(param.shipToCustCd.getValue());

        if (hasValue(param.dropShipFlg)) {
            insShpgPlnMsg.dropShipFlg.setValue(param.dropShipFlg.getValue());
        } else {
            insShpgPlnMsg.dropShipFlg.setValue(ZYPConstant.FLG_OFF_N);
        }

        insShpgPlnMsg.trxCd.setValue(param.trxCd.getValue());
        insShpgPlnMsg.trxRsnCd.setValue(param.trxRsnCd.getValue());
        insShpgPlnMsg.shpgStsCd.setValue(SHPG_STS.VALIDATED);
        insShpgPlnMsg.rteStsCd.setValue(RTE_STS.UN_ROUTED);
        insShpgPlnMsg.reqShpgMethCd.clear();
        insShpgPlnMsg.reqShpgTermCd.clear();
        insShpgPlnMsg.reqShpgSvcLvlCd.setValue(param.shpgSvcLvlCd.getValue());
        insShpgPlnMsg.origShpgSvcLvlCd.setValue(param.shpgSvcLvlCd.getValue());
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.reqFrtChrgMethCd, param.frtChrgMethCd);
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.reqFrtChrgToCd, param.frtChrgToCd);
        insShpgPlnMsg.spDealUnitPrcAmt.setValue(param.xxUnitPrc.getValue());
        insShpgPlnMsg.spDealNetUnitPrcAmt.setValue(param.xxUnitPrc.getValue());
        insShpgPlnMsg.spTotDealSlsAmt.setValue(roundHalfUp(multiply(param.xxUnitPrc.getValue(), setOrdQty)));
        insShpgPlnMsg.spTotDealNetAmt.setValue(roundHalfUp(multiply(param.xxUnitPrc.getValue(), setOrdQty)));
        insShpgPlnMsg.spTotDealFrtAmt.setValue(BigDecimal.ZERO);
        insShpgPlnMsg.spFuncUnitPrcAmt.setValue(param.xxUnitPrc.getValue());
        insShpgPlnMsg.spFuncNetUnitPrcAmt.setValue(param.xxUnitPrc.getValue());
        insShpgPlnMsg.spTotFuncSlsAmt.setValue(roundHalfUp(multiply(param.xxUnitPrc.getValue(), setOrdQty)));
        insShpgPlnMsg.spTotFuncNetAmt.setValue(roundHalfUp(multiply(param.xxUnitPrc.getValue(), setOrdQty)));
        insShpgPlnMsg.spTotFuncFrtAmt.setValue(BigDecimal.ZERO);

//        if (!MDSE_TP.SET.equals(mdseMsg.mdseTpCd.getValue()) && ZYPConstant.FLG_OFF_N.equals(distItemFlg) && HARD_ALLOC_TP.AUTO.equals(mdseMsg.invtyHardAllocTpCd.getValue())) {
//
//            insShpgPlnMsg.avalHardAllocQty.setValue(setOrdQty);
//
//        } else {

            insShpgPlnMsg.avalHardAllocQty.setValue(BigDecimal.ZERO);
//        }

        insShpgPlnMsg.avalSoftAllocQty.setValue(BigDecimal.ZERO);
        insShpgPlnMsg.avalSoQty.setValue(BigDecimal.ZERO);
        insShpgPlnMsg.avalPoQty.setValue(BigDecimal.ZERO);
        insShpgPlnMsg.soNum.clear();
        insShpgPlnMsg.doNum.clear();
        insShpgPlnMsg.poOrdNum.clear();
        insShpgPlnMsg.thirdPtyInvNum.clear();
        insShpgPlnMsg.actlShpgMethCd.clear();
        insShpgPlnMsg.actlShpgTermCd.clear();
        insShpgPlnMsg.actlShpgSvcLvlCd.clear();
        insShpgPlnMsg.actlFrtChrgToCd.clear();
        insShpgPlnMsg.actlFrtChrgMethCd.clear();
        insShpgPlnMsg.carrCd.setValue(param.carrCd.getValue());

        if (hasValue(param.carrCd)) {
            OTBD_CARR_VTMsg carrMsg = this.getCarrNm(msgMap);
            if (hasMessage(msgMap)) {
                return;
            }
            ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.carrNm, carrMsg.carrNm);
        } else {
            insShpgPlnMsg.carrNm.clear();
        }

        insShpgPlnMsg.bolNum.clear();
        insShpgPlnMsg.proNum.clear();
        insShpgPlnMsg.invNum.clear();
        insShpgPlnMsg.ccyCd.setValue(CCY.US_DOLLAR);
        insShpgPlnMsg.exchRate.setValue(BigDecimal.ONE);
        insShpgPlnMsg.ordUomQty.setValue(BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.rddDt, param.rddDt);
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.rsdDt, param.rsdDt);
        insShpgPlnMsg.psdDt.clear();
        insShpgPlnMsg.pddDt.clear();
        insShpgPlnMsg.etaDt.clear();
        insShpgPlnMsg.actlShipDt.clear();
        insShpgPlnMsg.actlArvDt.clear();
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.shipToLocNm, param.xxShipToName);
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.shipToAddlLocNm, param.xxShipToNameAddl);
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.shipToFirstLineAddr, param.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.shipToScdLineAddr, param.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.shipToThirdLineAddr, param.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.shipToFrthLineAddr, param.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.shipToFirstRefCmntTxt, param.shipToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.shipToScdRefCmntTxt, param.shipToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.shipToCtyAddr, param.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.shipToStCd, param.shipToStCd);
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.shipToProvAddr, param.xxShipToProvName);
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.shipToCntyNm, param.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.shipToPostCd, param.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.shipToCtryCd, param.shipToCtryCd);
        insShpgPlnMsg.dslpInfoFlg.setValue(ZYPConstant.FLG_OFF_N);
        insShpgPlnMsg.custUomCd.setValue(PKG_UOM.EACH);
        insShpgPlnMsg.rqstCarrCd.clear();
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.carrAcctNum, param.carrAcctNum);
        insShpgPlnMsg.carrAcctShipNum.clear();
        insShpgPlnMsg.shipCmntFirstLineTxt.clear();
        insShpgPlnMsg.shipCmntScdLineTxt.clear();
        insShpgPlnMsg.shipCmntThirdLineTxt.clear();
        insShpgPlnMsg.shipCmntFrthLineTxt.clear();
        insShpgPlnMsg.shipAvalFlg.setValue(ZYPConstant.FLG_OFF_N);
        insShpgPlnMsg.poReqFlg.setValue(ZYPConstant.FLG_OFF_N);
        insShpgPlnMsg.soCloseFlg.setValue(ZYPConstant.FLG_OFF_N);
        insShpgPlnMsg.mdseCd.setValue(param.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.invtyLocCd, param.invtyLocCd);
        if (MDSE_TP.SET.equals(mdseMsg.mdseTpCd.getValue())) {
            param.stkStsCd.setValue(STK_STS.GOOD);
            insShpgPlnMsg.stkStsCd.setValue(STK_STS.GOOD);
        } else {
            insShpgPlnMsg.stkStsCd.setValue(param.stkStsCd.getValue());
        }
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.shipCpltCd, param.shipCpltCd);
        insShpgPlnMsg.uomCpltFlg.setValue(ZYPConstant.FLG_OFF_N);
        insShpgPlnMsg.pmtTermCd.clear();
        insShpgPlnMsg.cashDiscTermCd.clear();
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.slsRepTocCd, param.slsRepTocCd);
        insShpgPlnMsg.shipPlnHldFlg.setValue(ZYPConstant.FLG_OFF_N);
        insShpgPlnMsg.shipPlnCancFlg.setValue(ZYPConstant.FLG_OFF_N);
        insShpgPlnMsg.shipPlnCancDt.clear();
        insShpgPlnMsg.softAllocQty.setValue(BigDecimal.ZERO);
        insShpgPlnMsg.ordQty.setValue(setOrdQty);
        insShpgPlnMsg.crHldQty.setValue(BigDecimal.ZERO);
        insShpgPlnMsg.slsHldQty.setValue(BigDecimal.ZERO);
        insShpgPlnMsg.crChkQty.setValue(BigDecimal.ZERO);
        insShpgPlnMsg.setPrcDetGrpSq.setValue(BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insShpgPlnMsg.setMdseCd, param.setMdseCd);
        insShpgPlnMsg.setItemShipCpltFlg.setValue(ZYPConstant.FLG_OFF_N);
        insShpgPlnMsg.sendEmlCpltFlg.setValue(ZYPConstant.FLG_OFF_N);
        insShpgPlnMsg.sysSrcCd.setValue(param.xxSysSrcCd.getValue());
        insShpgPlnMsg.revRecogFlg.setValue(ZYPConstant.FLG_OFF_N);

        boolean exportFlg = false;
        exportFlg = NWXC001001Export.isExportForShipTo(param.glblCmpyCd.getValue(), param.shipToCustCd.getValue());
        if (exportFlg) {
            insShpgPlnMsg.exptFlg.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            insShpgPlnMsg.exptFlg.setValue(ZYPConstant.FLG_OFF_N);
        }

        // 06/02/2010 Defect 6758 add
        insShpgPlnMsg.pmtTermCashDiscCd.clear();


        //Defect 5359
        if(hasValue(param.setMdseCd)){
            SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
            shpgPlnMsg.setSQLID("020");
            shpgPlnMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
            shpgPlnMsg.setConditionValue("trxHdrNum01", param.trxHdrNum.getValue());
            shpgPlnMsg.setConditionValue("trxLineNum01", param.trxLineNum.getValue());
            shpgPlnMsg.setConditionValue("trxLineSubNum01",TRX_LINE_SUB_NUM_SET);
            shpgPlnMsg.setConditionValue("trxSrcTpCd01", param.trxSrcTpCd.getValue());
            shpgPlnMsg.setConditionValue("shpgStsCd01", SHPG_STS.VALIDATED);

            SHPG_PLNTMsgArray shpgPlnMsgArray = (SHPG_PLNTMsgArray) S21ApiTBLAccessor.findByCondition(shpgPlnMsg);

            if (shpgPlnMsgArray.getValidCount() == 0) {

                msgMap.addXxMsgId(NWZM0075E);
                return;
            }else{

                insShpgPlnMsg.setShpgPlnNum.setValue(shpgPlnMsgArray.no(0).shpgPlnNum.getValue());
            }
        }

        S21FastTBLAccessor.insert(insShpgPlnMsg);

        if (!RTNCD_NORMAL_END.equals(insShpgPlnMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0448E);
        }

        writeDebugLog("insertShpgPln<End>");

    }

    private String getShipgPlnSQ() {
        writeDebugLog("getShipgPlnSQ<Start>");

        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        EZDItemAttribute mdseAttr = shpgPlnMsg.getAttr("shpgPlnNum");
        int digit = mdseAttr.getDigit();

        String shpgPlnCd = ZYPOracleSeqAccessor.getNumberString(SHPG_PLN_SQ, digit);

        writeDebugLog("getShipgPlnSQ<End>");
        return shpgPlnCd;

    }

    private BigDecimal getSumOrderQtyForSOPrinted(final NWZC107001PMsg param) {
        writeDebugLog("getSumOrderQtyForSOPrinted <Start>");

        BigDecimal orderQtySum = BigDecimal.ZERO;

        Map<String, String> key = new HashMap<String, String>();
        key.put("glblCmpyCd", param.glblCmpyCd.getValue());
        key.put("trxHdrNum", param.trxHdrNum.getValue());
        key.put("trxLineNum", param.trxLineNum.getValue());
        key.put("trxLineSubNum", param.trxLineSubNum.getValue());
        key.put("trxSrcTpCd", param.trxSrcTpCd.getValue());
        key.put("soPtintFlg", ZYPConstant.FLG_ON_Y);

        orderQtySum = (BigDecimal) ssmBatchClient.queryObject("getSumOrderQtyForSOPrinted", key);

        writeDebugLog("getSumOrderQtyForSOPrinted <End>");
        return orderQtySum;
    }

    private void getShipToInfo(S21ApiMessageMap msgMap) {

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();

        SHIP_TO_CUSTTMsg shipToCustMsg = new SHIP_TO_CUSTTMsg();
        shipToCustMsg.setSQLID("002");
        shipToCustMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        shipToCustMsg.setConditionValue("shipToCustCd01", param.shipToCustCd.getValue());
        shipToCustMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        SHIP_TO_CUSTTMsgArray shipToCustMsgArray = (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(shipToCustMsg);

        if (shipToCustMsgArray.getValidCount() == 0) {
            msgMap.addXxMsgId(NWZM0023E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(param.shipToPostCd, shipToCustMsgArray.no(0).postCd);
        ZYPEZDItemValueSetter.setValue(param.shipToStCd, shipToCustMsgArray.no(0).stCd);
        ZYPEZDItemValueSetter.setValue(param.xxShipToName, shipToCustMsgArray.no(0).locNm);
        ZYPEZDItemValueSetter.setValue(param.xxShipToNameAddl, shipToCustMsgArray.no(0).addlLocNm);
        ZYPEZDItemValueSetter.setValue(param.shipToFirstLineAddr, shipToCustMsgArray.no(0).firstLineAddr);
        ZYPEZDItemValueSetter.setValue(param.shipToScdLineAddr, shipToCustMsgArray.no(0).scdLineAddr);
        ZYPEZDItemValueSetter.setValue(param.shipToThirdLineAddr, shipToCustMsgArray.no(0).thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(param.shipToFrthLineAddr, shipToCustMsgArray.no(0).frthLineAddr);
        ZYPEZDItemValueSetter.setValue(param.shipToFirstRefCmntTxt, shipToCustMsgArray.no(0).firstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(param.shipToScdRefCmntTxt, shipToCustMsgArray.no(0).scdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(param.shipToCtyAddr, shipToCustMsgArray.no(0).ctyAddr);
        ZYPEZDItemValueSetter.setValue(param.shipToCtryCd, shipToCustMsgArray.no(0).ctryCd);
        ZYPEZDItemValueSetter.setValue(param.xxShipToProvName, shipToCustMsgArray.no(0).provNm);

        CNTYTMsg cntyMsg = new CNTYTMsg();
        cntyMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        cntyMsg.cntyPk.setValue(shipToCustMsgArray.no(0).cntyPk.getValue());
        cntyMsg = (CNTYTMsg) S21ApiTBLAccessor.findByKey(cntyMsg);
        if (cntyMsg != null) {
            param.shipToCntyNm.setValue(cntyMsg.cntyNm.getValue());
        }
    }

    private SHPG_PLNTMsgArray searchShpgPlanForShpgSts(S21ApiMessageMap msgMap, final String shpgStsCd) {

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();

        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        shpgPlnMsg.setSQLID("020");
        shpgPlnMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        shpgPlnMsg.setConditionValue("trxHdrNum01", param.trxHdrNum.getValue());
        shpgPlnMsg.setConditionValue("trxLineNum01", param.trxLineNum.getValue());
        shpgPlnMsg.setConditionValue("trxLineSubNum01", param.trxLineSubNum.getValue());
        shpgPlnMsg.setConditionValue("trxSrcTpCd01", param.trxSrcTpCd.getValue());
        shpgPlnMsg.setConditionValue("shpgStsCd01", shpgStsCd);

        SHPG_PLNTMsgArray shpgPlnMsgArray = (SHPG_PLNTMsgArray) S21ApiTBLAccessor.findByCondition(shpgPlnMsg);

        return shpgPlnMsgArray;

    }

    private SHPG_PLNTMsgArray searchShpgPlanForUpdate(S21ApiMessageMap msgMap, final String shpgStsCd) {

        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();

        SHPG_PLNTMsg shpgPlnMsg = new SHPG_PLNTMsg();
        shpgPlnMsg.setSQLID("020");
        shpgPlnMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        shpgPlnMsg.setConditionValue("trxHdrNum01", param.trxHdrNum.getValue());
        shpgPlnMsg.setConditionValue("trxLineNum01", param.trxLineNum.getValue());
        shpgPlnMsg.setConditionValue("trxLineSubNum01", param.trxLineSubNum.getValue());
        shpgPlnMsg.setConditionValue("trxSrcTpCd01", param.trxSrcTpCd.getValue());
        shpgPlnMsg.setConditionValue("shpgStsCd01", shpgStsCd);

        SHPG_PLNTMsgArray shpgPlnMsgArray = (SHPG_PLNTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(shpgPlnMsg);

        return shpgPlnMsgArray;

    }

    private List<Map<String, Object>> searchShpgPlanCancel(final NWZC107001PMsg param) {
        writeDebugLog("searchShpgPlanCancel <Start>");

        Map<String, String> key = new HashMap<String, String>();
        key.put("glblCmpyCd", param.glblCmpyCd.getValue());
        key.put("trxHdrNum", param.trxHdrNum.getValue());
        key.put("trxLineNum", param.trxLineNum.getValue());
        key.put("trxLineSubNum", param.trxLineSubNum.getValue());
        key.put("trxSrcTpCd", param.trxSrcTpCd.getValue());

        if (!TRX_LINE_SUB_NUM_SET.equals(param.trxLineSubNum.getValue())) {
            key.put("shpgStsCd", SHPG_STS.SAVED);
        }

        List<Map<String, Object>> shpgPlanCancelList = (List) ssmBatchClient.queryObjectList("searchShpgPlanCancel", key);

        return shpgPlanCancelList;
    }

    // 2017/04/12 S21_NA#Review structure Lv.1 Del Start
//    private String getCompMdseCd(S21ApiMessageMap msgMap, final String trxLineSubNum) {
//        writeDebugLog("getCompMdseCd <Start>");
//
//        NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();
//
//        Map<String, String> key = new HashMap<String, String>();
//        key.put("glblCmpyCd", param.glblCmpyCd.getValue());
//        key.put("trxHdrNum", param.trxHdrNum.getValue());
//        key.put("trxLineNum", param.trxLineNum.getValue());
//        key.put("trxLineSubNum", trxLineSubNum);
//        key.put("trxSrcTpCd", param.trxSrcTpCd.getValue());
//
//        List<String> compMdseCdList = (List) ssmBatchClient.queryObjectList("getCompMdseCd", key);
//
//        if (compMdseCdList == null || compMdseCdList.isEmpty()) {
//
//            msgMap.addXxMsgId(NWZM0075E);
//        }
//
//        return compMdseCdList.get(0);
//    }
    // 2017/04/12 S21_NA#Review structure Lv.1 Del End

    private String getMaxDisplayNumber(final NWZC107001PMsg param) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", param.glblCmpyCd.getValue());
        map.put("trxHdrNum", param.trxHdrNum.getValue());
        map.put("trxLineNum", param.trxLineNum.getValue());
        map.put("trxLineSubNum", param.trxLineSubNum.getValue());
        map.put("trxSrcTpCd", param.trxSrcTpCd.getValue());

        String dispNum = (String) this.ssmBatchClient.queryObject("getMaxDisplayNumber", map);
        if (ZYPCommonFunc.hasValue(dispNum)) {
            return ZYPCommonFunc.leftPad(String.valueOf(Integer.parseInt(dispNum) + 1), 3, "0");
        } else {
            return "001";
        }
    }

    private MDSETMsg initProcess(S21ApiMessageMap msgMap, final NWZC107001PMsg param) {
        writeDebugLog("initProcess<Start>");

        this.chkCommonInputParam(msgMap, param);

        if (hasMessage(msgMap)) {
            return null;
        }

        if (REQ_TP_CANCEL.equals(param.xxRqstTpCd.getValue())) {
            return null;
        }

        MDSETMsg mdseMsg = this.getMdse(msgMap, param.mdseCd.getValue());

        this.chkMdse(msgMap, mdseMsg);

        if (hasMessage(msgMap)) {
            return null;
        }

        if (!MDSE_TP.SET.equals(mdseMsg.mdseTpCd.getValue())) {

            this.chkVendorDrop(msgMap);

            if (hasMessage(msgMap)) {
                return null;
            }
        }

        // Del Start S21_NA#5872
//        if (!MDSE_TP.SET.equals(mdseMsg.mdseTpCd.getValue()) && (DIST_TP.NONE.equals(mdseMsg.invtyDistTpCd.getValue()) && HARD_ALLOC_TP.AUTO.equals(mdseMsg.invtyHardAllocTpCd.getValue()))) {
//
//            MDSE_WH_CONDTMsg mdseWhCondMsg = this.getMdseWHCond(msgMap);
//
//            if (mdseWhCondMsg != null) {
//                mdseMsg.invtyHardAllocTpCd.setValue(mdseWhCondMsg.invtyHardAllocTpCd.getValue());
//            }
//
//        }
        // Del End S21_NA#5872

        if (REQ_TP_NEW.equals(param.xxRqstTpCd.getValue())) {
            this.chkNewProcessParam(msgMap, param, mdseMsg);

        } else if (REQ_TP_ODR_INFOMATION.equals(param.xxRqstTpCd.getValue())) {
            this.chkBuckOrderInfoProcessParam(msgMap, param, mdseMsg);

        } else {
            msgMap.addXxMsgId(NWZM0209E);
        }

        writeDebugLog("initProcess<End>");
        return mdseMsg;

    }

    private void chkCommonInputParam(S21ApiMessageMap msgMap, final NWZC107001PMsg param) {
        writeDebugLog("chkCommonInputParam<Start>");

        if (!hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0188E);
            return;
        }

        if (!hasValue(param.xxRqstTpCd)) {
            msgMap.addXxMsgId(NWZM0189E);
            return;
        }

        if (!hasValue(param.trxSrcTpCd)) {
            msgMap.addXxMsgId(NWZM0014E);
            return;
        }

        if (!hasValue(param.trxHdrNum)) {
            msgMap.addXxMsgId(NWZM0027E);
            return;
        }

        if (!hasValue(param.trxLineNum)) {
            msgMap.addXxMsgId(NWZM0089E);
            return;
        }

        if (!hasValue(param.trxLineSubNum)) {
            msgMap.addXxMsgId(NWZM0043E);
            return;
        }

        if (!hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NWZM0346E);
            return;
        }

        writeDebugLog("chkCommonInputParam<End>");
    }

    private void chkNewProcessParam(S21ApiMessageMap msgMap, final NWZC107001PMsg param, final MDSETMsg mdseMsg) {
        writeDebugLog("chkNewProcessParam<Start>");

        if (!hasValue(param.xxSysSrcCd)) {
            msgMap.addXxMsgId(NWZM0453E);
            return;
        }

        if (!hasValue(param.trxCd)) {
            msgMap.addXxMsgId(NWZM0454E);
            return;
        }

        if (!hasValue(param.trxRsnCd)) {
            msgMap.addXxMsgId(NWZM0455E);
            return;
        }

        if (TRX_SRC_TP.RETAIL.equals(param.trxSrcTpCd.getValue())) {

            if (!hasValue(param.rossOrdTpCd)) {
                msgMap.addXxMsgId(NWZM0456E);
                return;
            }

            if (!hasValue(param.frtChrgMethCd)) {
                msgMap.addXxMsgId(NWZM0358E);
                return;
            }

            if (!hasValue(param.frtChrgToCd)) {
                msgMap.addXxMsgId(NWZM0175E);
                return;
            }

            if (!hasValue(param.xxOrdTs)) {
                msgMap.addXxMsgId(NWZM0196E);
                return;
            }

            if (!hasValue(param.shpgSvcLvlCd)) {
                msgMap.addXxMsgId(NWZM0049E);
                return;
            }
        } else {

            if (TRX_SRC_TP.WAREHOUSE_TRANSFER.equals(param.trxSrcTpCd.getValue())) {
                if (!hasValue(param.shpgSvcLvlCd)) {
                    msgMap.addXxMsgId(NWZM0049E);
                    return;
                }

            } else {
                if (!hasValue(param.shpgSvcLvlCd)) {
                    param.shpgSvcLvlCd.setValue(SHPG_SVC_LVL.GROUND_SERVICE);
                }
            }

            if (!hasValue(param.frtChrgMethCd)) {
                param.frtChrgMethCd.setValue(FRT_CHRG_METH.N_OR_A);

            }

            if (!hasValue(param.frtChrgToCd)) {
                param.frtChrgToCd.setValue(FRT_CHRG_TO.CANON);
            }

        }

        if (!hasValue(param.locStsCd)) {
            msgMap.addXxMsgId(NWZM0400E);
            return;
        }

        if (!hasValue(param.stkStsCd)) {
            msgMap.addXxMsgId(NWZM0174E);
            return;
        }

        if (!hasValue(param.xxRqstQty)) {
            msgMap.addXxMsgId(NWZM0185E);
            return;
        } else {
            if (param.xxRqstQty.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                msgMap.addXxMsgId(NWZM0208E);
                return;
            }
        }

//      Defect 5676
//      Defect 5879
        if(TRX_SRC_TP.RETAIL.equals(param.trxSrcTpCd.getValue())){
            if (!hasValue(param.slsRepTocCd))  {
                msgMap.addXxMsgId(NWZM0457E);
                return;
            }
        }

        if (hasValue(param.frtChrgMethCd) && (FRT_CHRG_METH.CUST_ACCOUNT.equals(param.frtChrgMethCd.getValue()))) {

            if (!hasValue(param.carrCd)) {
                msgMap.addXxMsgId(NWZM0066E);
                return;
            }

            if (!hasValue(param.carrAcctNum)) {
                msgMap.addXxMsgId(NWZM0458E);
                return;
            }

        }

        if (!MDSE_TP.SET.equals(mdseMsg.mdseTpCd.getValue())) {
            if (!hasValue(param.rddDt) && !hasValue(param.rsdDt)) {
                msgMap.addXxMsgId(NWZM0178E);
                return;
            }
        }

        if (!hasValue(param.xxUnitPrc)) {
            msgMap.addXxMsgId(NWZM0460E);
            return;
        }

        if (!hasValue(param.xxSlsAmt)) {
            msgMap.addXxMsgId(NWZM0461E);
            return;
        }

        if (!hasValue(param.billToCustCd)) {
            msgMap.addXxMsgId(NWZM0179E);
            return;
        }

        if (!hasValue(param.sellToCustCd)) {
            msgMap.addXxMsgId(NWZM0180E);
            return;
        }

        if (!hasValue(param.shipToCustCd)) {
            msgMap.addXxMsgId(NWZM0181E);
            return;
        }

        if (!hasValue(param.dropShipFlg) || ZYPConstant.FLG_OFF_N.equals(param.dropShipFlg.getValue())) {

            if (!TRX_SRC_TP.VENDOR_RETURN.equals(param.trxSrcTpCd.getValue())) {
                this.getShipToInfo(msgMap);
            }
            if (hasMessage(msgMap)) {
                return;
            }

        } else {

            /* QC2150
             * when drop shipping to outside the U.S.,
             * do not need to check post code and state.
             */
            if (CTRY.UNITED_STATES_OF_AMERICA.equals(param.shipToCtryCd.getValue())) {
                if (!hasValue(param.shipToPostCd)) {
                    msgMap.addXxMsgId(NWZM0514E);
                    return;
                }

                if (!hasValue(param.shipToStCd)) {
                    msgMap.addXxMsgId(NWZM0497E);
                    return;
                }
            }
        }

        writeDebugLog("chkNewProcessParam<End>");
    }

    private void chkBuckOrderInfoProcessParam(S21ApiMessageMap msgMap, final NWZC107001PMsg param, final MDSETMsg mdseMsg) {
        writeDebugLog("chkBuckOrderInfoProcessParam<Start>");

        if (!hasValue(param.xxSysSrcCd)) {
            msgMap.addXxMsgId(NWZM0453E);
            return;
        }

        if (!hasValue(param.trxCd)) {
            msgMap.addXxMsgId(NWZM0454E);
            return;
        }

        if (!hasValue(param.trxRsnCd)) {
            msgMap.addXxMsgId(NWZM0455E);
            return;
        }

        if (!hasValue(param.rossOrdTpCd)) {
            msgMap.addXxMsgId(NWZM0456E);
            return;
        }

        if (!hasValue(param.locStsCd)) {
            msgMap.addXxMsgId(NWZM0400E);
            return;
        }

        if (!hasValue(param.stkStsCd)) {
            msgMap.addXxMsgId(NWZM0174E);
            return;
        }

        if (!hasValue(param.xxRqstQty)) {
            msgMap.addXxMsgId(NWZM0185E);
            return;
        } else {
            if (param.xxRqstQty.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                msgMap.addXxMsgId(NWZM0208E);
                return;
            }
        }
//      Defect 5676
//      Defect 5879
        if(TRX_SRC_TP.RETAIL.equals(param.trxSrcTpCd.getValue())){
            if (!hasValue(param.slsRepTocCd)) {
                msgMap.addXxMsgId(NWZM0457E);
                return;
            }
        }

        if (!hasValue(param.frtChrgMethCd)) {
            msgMap.addXxMsgId(NWZM0358E);
            return;
        }

        if (!hasValue(param.frtChrgToCd)) {
            msgMap.addXxMsgId(NWZM0175E);
            return;
        }
        if (hasValue(param.frtChrgMethCd) && (FRT_CHRG_METH.CUST_ACCOUNT.equals(param.frtChrgMethCd.getValue()))) {

            if (!hasValue(param.carrCd)) {
                msgMap.addXxMsgId(NWZM0066E);
                return;
            }

            if (!hasValue(param.carrAcctNum)) {
                msgMap.addXxMsgId(NWZM0458E);
                return;
            }
        }

        if (!hasValue(param.shpgSvcLvlCd)) {
            msgMap.addXxMsgId(NWZM0049E);
            return;
        }

        if (!MDSE_TP.SET.equals(mdseMsg.mdseTpCd.getValue())) {
            if (!hasValue(param.rddDt) && !hasValue(param.rsdDt)) {
                msgMap.addXxMsgId(NWZM0178E);
                return;
            }
        }

        if (!hasValue(param.xxOrdTs)) {
            msgMap.addXxMsgId(NWZM0196E);
            return;
        }

        if (!hasValue(param.xxUnitPrc)) {
            msgMap.addXxMsgId(NWZM0460E);
            return;
        }

        if (!hasValue(param.xxSlsAmt)) {
            msgMap.addXxMsgId(NWZM0461E);
            return;
        }

        if (!hasValue(param.billToCustCd)) {
            msgMap.addXxMsgId(NWZM0179E);
            return;
        }

        if (!hasValue(param.sellToCustCd)) {
            msgMap.addXxMsgId(NWZM0180E);
            return;
        }

        if (!hasValue(param.shipToCustCd)) {
            msgMap.addXxMsgId(NWZM0181E);
            return;
        }

        if (!hasValue(param.dropShipFlg) || ZYPConstant.FLG_OFF_N.equals(param.dropShipFlg.getValue())) {

            if (!TRX_SRC_TP.VENDOR_RETURN.equals(param.trxSrcTpCd.getValue())) {
                this.getShipToInfo(msgMap);
            }
            if (hasMessage(msgMap)) {
                return;
            }

        } else {

            if (!hasValue(param.shipToPostCd)) {
                msgMap.addXxMsgId(NWZM0514E);
                return;
            }

            if (!hasValue(param.shipToStCd)) {
                msgMap.addXxMsgId(NWZM0497E);
                return;
            }

        }

        writeDebugLog("chkBuckOrderInfoProcessParam<End>");
    }

    private boolean hasValue(EZDPStringItem ezdpStringItem) {
        return ZYPCommonFunc.hasValue(ezdpStringItem);
    }

    private boolean hasValue(EZDTBigDecimalItem ezdtBigDecimalItem) {
        return ZYPCommonFunc.hasValue(ezdtBigDecimalItem);
    }

    private boolean hasValue(EZDPBigDecimalItem ezdpBigDecimalItem) {
        return ZYPCommonFunc.hasValue(ezdpBigDecimalItem);
    }

    private boolean hasValue(EZDPDateItem ezdpDateItem) {
        return ZYPCommonFunc.hasValue(ezdpDateItem);
    }

    private boolean hasValue(BigDecimal value) {
        return ZYPCommonFunc.hasValue(value);
    }
    private boolean hasValue(String value) {
        return ZYPCommonFunc.hasValue(value);
    }

    private boolean hasMessage(S21ApiMessageMap msgMap) {

        if (!msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private boolean isErrMessage(S21ApiMessageMap msgMap) {

        if (msgMap.getMsgMgr().isXxMsgId()) {
            NWZC107001PMsg param = (NWZC107001PMsg) msgMap.getPmsg();

            for (int i = 0; i < param.xxMsgIdList.getValidCount(); i++) {

                String msgId = param.xxMsgIdList.no(i).xxMsgId.getValue();
                writeDebugLog("msgId :" + msgId);
                if (msgId.endsWith(ERROR_MESSAGE_CD)) {
                    return true;

                }
            }
        }
        return false;
    }

    private BigDecimal multiply(BigDecimal big1, BigDecimal big2) {
        BigDecimal b1 = big1;
        BigDecimal b2 = big2;
        if (!hasValue(b1)) {
            b1 = new BigDecimal(0);
        }
        if (!hasValue(b2)) {
            b2 = new BigDecimal(0);
        }
        return b1.multiply(b2);
    }

    private BigDecimal roundHalfUp(BigDecimal big) {

        return big.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private void writeDebugLog(String str) {
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(1, "[Allocation for non CPO API] " + str, this);
        }
    }

}
