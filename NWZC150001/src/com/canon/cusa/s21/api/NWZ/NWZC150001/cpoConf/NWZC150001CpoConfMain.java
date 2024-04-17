/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf;

import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_CONFIG_MODIFY;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_CONFIG_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.DOCUMENT_TYPE;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.DOCUMENT_TYPE_RTN;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.EVENT_ID_ORD_CRAT;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.EVENT_ID_ORD_MOD;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.NWZM0012E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.NWZM0013E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.NWZM0188E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.NWZM0912E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.NWZM1337E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.NWZM1374E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.NWZM1375E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.NWZM1376E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.NWZM1377E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.NWZM1378E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.NWZM1379E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.NWZM1380E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.NWZM1381E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.NWZM1382E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.NWZM1383E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.NWZM1758E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.constants.NWZC150001CpoConfConstant.PROCESS_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDMsg;
import parts.dbcommon.EZDSeqTable;
import parts.dbcommon.EZDTBLAccessor;
import business.db.BIZ_PROC_LOGTMsg;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.DS_CPO_CONFIG_RECTMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_cpoConfigPMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SUB_SYS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 * <pre>
 * CPO Config Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/31   Fujitsu         C.Yokoi         Create          N/A
 * 2016/01/06   Fujitsu         T.Ishii         Update          S21_NA#2595
 * 2016/02/25   Fujitsu         S.Ohki          Update          S21_NA#3328
 * 2016/04/20   Fujitsu         S.Takami        Update          S21_NA#5605
 * 2016/08/09   Fujitsu         Y.Taoka         Update          S21_NA#8388
 * 2016/11/01   Fujitsu         W.Honda         Update          S21_NA#14897
 * 2018/02/07   Fujitsu         Y.Kanefusa      Update          S21_NA#24012
 * 2018/06/22   Fujitsu         T.Aoi           Update          S21_NA#20154
 * 2018/11/15   Fujitsu         K.Ishizuka      Update          S21_NA#27299
 *</pre>
 */
public class NWZC150001CpoConfMain extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /**
     * constructor
     */
    public NWZC150001CpoConfMain() {
        super();
    }

    /**
     * execute
     * @param pMsg NWZC150001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @param cpouBean NWZC150001CpouBean
     */
    // 2018/06/22 QC#20154 Mod Start
    //public void execute(final NWZC150001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
    public void execute(final NWZC150001PMsg pMsg, final ONBATCH_TYPE onBatchType, NWZC150001CpouBean cpouBean) {
    // 2018/06/22 QC#20154 Mod End
        this.msgMap = new S21ApiMessageMap(pMsg);

        // 2018/06/22 QC#20154 Mod Start
        //doProcess(pMsg);
        doProcess(pMsg, cpouBean);
        // 2018/06/22 QC#20154 Mod End
        msgMap.flush();
    }

    /**
     * execute
     * @param params List<NWZC150001PMsg>
     * @param onBatchType ONBATCH_TYPE
     * @param cpouBean NWZC150001CpouBean
     */
    // 2018/06/22 QC#20154 Mod Start
    //public void execute(final List<NWZC150001PMsg> params, final ONBATCH_TYPE onBatchType) {
    public void execute(final List<NWZC150001PMsg> params, final ONBATCH_TYPE onBatchType, NWZC150001CpouBean cpouBean) {
    // 2018/06/22 QC#20154 Mod End
        for (NWZC150001PMsg param : params) {
            // 2018/06/22 QC#20154 Mod Start
            //execute(param, onBatchType);
            execute(param, onBatchType, cpouBean);
            // 2018/06/22 QC#20154 Mod End
        }
    }

    /**
     * doProcess
     * @param param NWZC150001PMsg
     * @param cpouBean NWZC150001CpouBean
     */
    // 2018/06/22 QC#20154 Mod Start
    //private void doProcess(NWZC150001PMsg pMsg) {
    private void doProcess(NWZC150001PMsg pMsg, NWZC150001CpouBean cpouBean) {
    // 2018/06/22 QC#20154 Mod End

        if (!checkInput(pMsg)) {
            return;
        }

        List<DS_CPO_CONFIGTMsg> insTMsgList = new ArrayList<DS_CPO_CONFIGTMsg>();
        List<DS_CPO_CONFIGTMsg> updTMsgList = new ArrayList<DS_CPO_CONFIGTMsg>();
        List<DS_CPO_CONFIG_RECTMsg> dsCpoConfigRecTMsgList = new ArrayList<DS_CPO_CONFIG_RECTMsg>();

        String cpoOrdNum = pMsg.cpoOrdNum.getValue();
        String configCreateTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");

        // 2018/06/22 QC#20154 Add Start
        List<BigDecimal> shipToChgToWmsSendList = new ArrayList<BigDecimal>();
        // 2018/06/22 QC#20154 Add End

        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg configMsg = pMsg.cpoConfig.no(i);
            String dsOrdPosnNum = configMsg.dsOrdPosnNum.getValue();

            if (RQST_TP_CONFIG_NEW.equals(configMsg.xxRqstTpCd.getValue())) {
                DS_CPO_CONFIGTMsg insTMsg = new DS_CPO_CONFIGTMsg();
                setValue(insTMsg.glblCmpyCd, pMsg.glblCmpyCd);
                setValue(configMsg.dsCpoConfigPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CPO_CONFIG_SQ));
                setValue(insTMsg.dsCpoConfigPk, configMsg.dsCpoConfigPk);
                setValue(insTMsg.cpoOrdNum, cpoOrdNum);
                setValue(insTMsg.dsOrdPosnNum, dsOrdPosnNum);
                setValue(insTMsg.configCatgCd, configMsg.configCatgCd);
                setValue(insTMsg.configTpCd, configMsg.configTpCd);
                setValue(insTMsg.svcConfigMstrPk, configMsg.svcConfigMstrPk);
                setValue(insTMsg.mdlId, configMsg.mdlId);
                setValue(insTMsg.mdlDescTxt, configMsg.mdlDescTxt);
                setValue(insTMsg.billToCustAcctCd, configMsg.billToCustAcctCd);
                setValue(insTMsg.billToCustLocCd, configMsg.billToCustCd);
                setValue(insTMsg.shipToCustAcctCd, configMsg.shipToCustAcctCd);
                setValue(insTMsg.shipToCustLocCd, configMsg.shipToCustCd);
                setValue(insTMsg.dropShipFlg, configMsg.dropShipFlg);
                setValue(insTMsg.shipToLocNm, configMsg.shipToLocNm);
                setValue(insTMsg.shipToAddlLocNm, configMsg.shipToAddlLocNm);
                setValue(insTMsg.shipToFirstLineAddr, configMsg.shipToFirstLineAddr);
                setValue(insTMsg.shipToScdLineAddr, configMsg.shipToScdLineAddr);
                setValue(insTMsg.shipToThirdLineAddr, configMsg.shipToThirdLineAddr);
                setValue(insTMsg.shipToFrthLineAddr, configMsg.shipToFrthLineAddr);
                setValue(insTMsg.shipToFirstRefCmntTxt, configMsg.shipTo01RefCmntTxt);
                setValue(insTMsg.shipToScdRefCmntTxt, configMsg.shipTo02RefCmntTxt);
                setValue(insTMsg.shipToCtyAddr, configMsg.shipToCtyAddr);
                setValue(insTMsg.shipToStCd, configMsg.shipToStCd);
                setValue(insTMsg.shipToProvNm, configMsg.shipToProvNm);
                setValue(insTMsg.shipToCntyNm, configMsg.shipToCntyNm);
                setValue(insTMsg.shipToPostCd, configMsg.shipToPostCd);
                setValue(insTMsg.shipToCtryCd, configMsg.shipToCtryCd);
                setValue(insTMsg.configCratTs, configCreateTs);
                setValue(insTMsg.pickSvcConfigMstrPk, configMsg.pickSvcConfigMstrPk); // S21_NA#2595
                // 2016/11/01 S21_NA#14897 Mod Start
//                setValue(insTMsg.dclnSvcCd, configMsg.dclnSvcCd_A); // S21_NA#8388 ADD
                if (!ZYPCommonFunc.hasValue(configMsg.dclnSvcCd)) {
                    setValue(insTMsg.dclnSvcCd, ZYPConstant.FLG_OFF_0);
                } else {
                    setValue(insTMsg.dclnSvcCd, configMsg.dclnSvcCd);
                }
                // 2016/11/01 S21_NA#14897 Mod End
                // 2018/11/15 S21_NA#27299 Add Start
                setValue(insTMsg.origShipToCustLocCd, configMsg.origShipToCustLocCd);
                setValue(insTMsg.origShipToLocNm, configMsg.origShipToLocNm);
                setValue(insTMsg.origShipToAddlLocNm, configMsg.origShipToAddlLocNm);
                setValue(insTMsg.origShipFirstLineAddr, configMsg.origShipFirstLineAddr);
                setValue(insTMsg.origShipScdLineAddr, configMsg.origShipScdLineAddr);
                setValue(insTMsg.origShipThirdLineAddr, configMsg.origShipThirdLineAddr);
                setValue(insTMsg.origShipFrthLineAddr, configMsg.origShipFrthLineAddr);
                setValue(insTMsg.origShipToCtyAddr, configMsg.origShipToCtyAddr);
                setValue(insTMsg.origShipToStCd, configMsg.origShipToStCd);
                setValue(insTMsg.origShipToCntyNm, configMsg.origShipToCntyNm);
                setValue(insTMsg.origShipToPostCd, configMsg.origShipToPostCd);
                setValue(insTMsg.origShipToCtryCd, configMsg.origShipToCtryCd);
                // 2018/11/15 S21_NA#27299 Add End
                insTMsgList.add(insTMsg);

                // Create Bisiness Prcess Log
                // 2016/02/25 S21_NA#3328 Mod Start
                int bizProcLogPk = createBizProcLog(EVENT_ID_ORD_CRAT, dsOrdPosnNum, cpoOrdNum, configMsg.configCatgCd.getValue());
                // 2016/02/25 S21_NA#3328 Mod End

                // Set Rec Data
                DS_CPO_CONFIG_RECTMsg insRecTMsg = new DS_CPO_CONFIG_RECTMsg();
                EZDMsg.copy(insTMsg, null, insRecTMsg, null);
                insRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
                dsCpoConfigRecTMsgList.add(insRecTMsg);

            } else {
                DS_CPO_CONFIGTMsg updTMsgKey = new DS_CPO_CONFIGTMsg();

                setValue(updTMsgKey.dsCpoConfigPk, configMsg.dsCpoConfigPk);
                setValue(updTMsgKey.glblCmpyCd, pMsg.glblCmpyCd);

                DS_CPO_CONFIGTMsg updTMsg = (DS_CPO_CONFIGTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(updTMsgKey);
                if (updTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM1381E);
                    return;
                }

                // 2018/06/22 QC#20154 Add Start
                if (CONFIG_CATG.OUTBOUND.equals(updTMsg.configCatgCd.getValue())) {
                    if (isShipToChgToConfig(updTMsg, configMsg)) {
                        shipToChgToWmsSendList.add(updTMsg.dsCpoConfigPk.getValue());
                    }
                }
                // 2018/06/22 QC#20154 Add End

                // add update TMsg
                setValue(updTMsg.svcConfigMstrPk, configMsg.svcConfigMstrPk);
                // 2016/04/20 S21_NA#5605 Add Start
                if (ZYPCommonFunc.hasValue(dsOrdPosnNum) //
                        && !S21StringUtil.isEquals(updTMsg.dsOrdPosnNum.getValue(), dsOrdPosnNum)) {
                    setValue(updTMsg.dsOrdPosnNum, dsOrdPosnNum);
                }
                // 2016/04/20 S21_NA#5605 Add End
                setValue(updTMsg.mdlId, configMsg.mdlId);
                setValue(updTMsg.mdlDescTxt, configMsg.mdlDescTxt);
                setValue(updTMsg.configTpCd, configMsg.configTpCd); // QC#24012 2018/02/07 Add
                setValue(updTMsg.billToCustAcctCd, configMsg.billToCustAcctCd);
                setValue(updTMsg.billToCustLocCd, configMsg.billToCustCd);
                setValue(updTMsg.shipToCustAcctCd, configMsg.shipToCustAcctCd);
                setValue(updTMsg.shipToCustLocCd, configMsg.shipToCustCd);
                setValue(updTMsg.dropShipFlg, configMsg.dropShipFlg);
                setValue(updTMsg.shipToLocNm, configMsg.shipToLocNm);
                setValue(updTMsg.shipToAddlLocNm, configMsg.shipToAddlLocNm);
                setValue(updTMsg.shipToFirstLineAddr, configMsg.shipToFirstLineAddr);
                setValue(updTMsg.shipToScdLineAddr, configMsg.shipToScdLineAddr);
                setValue(updTMsg.shipToThirdLineAddr, configMsg.shipToThirdLineAddr);
                setValue(updTMsg.shipToFrthLineAddr, configMsg.shipToFrthLineAddr);
                setValue(updTMsg.shipToFirstRefCmntTxt, configMsg.shipTo01RefCmntTxt);
                setValue(updTMsg.shipToScdRefCmntTxt, configMsg.shipTo02RefCmntTxt);
                setValue(updTMsg.shipToCtyAddr, configMsg.shipToCtyAddr);
                setValue(updTMsg.shipToStCd, configMsg.shipToStCd);
                setValue(updTMsg.shipToProvNm, configMsg.shipToProvNm);
                setValue(updTMsg.shipToCntyNm, configMsg.shipToCntyNm);
                setValue(updTMsg.shipToPostCd, configMsg.shipToPostCd);
                setValue(updTMsg.shipToCtryCd, configMsg.shipToCtryCd);
                setValue(updTMsg.pickSvcConfigMstrPk, configMsg.pickSvcConfigMstrPk); // S21_NA#2595
                // 2016/11/01 S21_NA#14897 Mod Start
//              setValue(updTMsg.dclnSvcCd, configMsg.dclnSvcCd_A); // S21_NA#8388 ADD
                if (!ZYPCommonFunc.hasValue(configMsg.dclnSvcCd)) {
                    setValue(updTMsg.dclnSvcCd, ZYPConstant.FLG_OFF_0);
                } else {
                    setValue(updTMsg.dclnSvcCd, configMsg.dclnSvcCd);
                }
                // 2016/11/01 S21_NA#14897 Mod End
                // 2018/11/15 S21_NA#27299 Add Start
                setValue(updTMsg.origShipToCustLocCd, configMsg.origShipToCustLocCd);
                setValue(updTMsg.origShipToLocNm, configMsg.origShipToLocNm);
                setValue(updTMsg.origShipToAddlLocNm, configMsg.origShipToAddlLocNm);
                setValue(updTMsg.origShipFirstLineAddr, configMsg.origShipFirstLineAddr);
                setValue(updTMsg.origShipScdLineAddr, configMsg.origShipScdLineAddr);
                setValue(updTMsg.origShipThirdLineAddr, configMsg.origShipThirdLineAddr);
                setValue(updTMsg.origShipFrthLineAddr, configMsg.origShipFrthLineAddr);
                setValue(updTMsg.origShipToCtyAddr, configMsg.origShipToCtyAddr);
                setValue(updTMsg.origShipToStCd, configMsg.origShipToStCd);
                setValue(updTMsg.origShipToCntyNm, configMsg.origShipToCntyNm);
                setValue(updTMsg.origShipToPostCd, configMsg.origShipToPostCd);
                setValue(updTMsg.origShipToCtryCd, configMsg.origShipToCtryCd);
                // 2018/11/15 S21_NA#27299 Add End
                updTMsgList.add(updTMsg);

                // Create Bisiness Prcess Log
                // 2016/02/25 S21_NA#3328 Mod Start
                int bizProcLogPk = createBizProcLog(EVENT_ID_ORD_MOD, dsOrdPosnNum, cpoOrdNum, configMsg.configCatgCd.getValue());
                // 2016/02/25 S21_NA#3328 Mod End

                // Set Rec Data
                DS_CPO_CONFIG_RECTMsg insRecTMsg = new DS_CPO_CONFIG_RECTMsg();
                EZDMsg.copy(updTMsg, null, insRecTMsg, null);
                insRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
                dsCpoConfigRecTMsgList.add(insRecTMsg);
            }

        }

        // insert (DS_CPO_CONFIG)
        if (!insTMsgList.isEmpty()) {
            int insCnt = S21FastTBLAccessor.insert(insTMsgList.toArray(new DS_CPO_CONFIGTMsg[0]));
            if (insCnt != insTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1382E);
                return;
            }
        }

        // update (DS_CPO_CONFIG)
        if (!updTMsgList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updTMsgList.toArray(new DS_CPO_CONFIGTMsg[0]));
            if (updCnt != updTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1383E);
                return;
            }
        }

        // insert (DS_CPO_CONFIG_REC)
        if (!dsCpoConfigRecTMsgList.isEmpty()) {
            int insCnt = S21FastTBLAccessor.insert(dsCpoConfigRecTMsgList.toArray(new DS_CPO_CONFIG_RECTMsg[0]));
            if (insCnt != dsCpoConfigRecTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1758E);
                return;
            }
        }
        
        // 2018/06/22 QC#20154 Add Start
        if (cpouBean != null) {
            cpouBean.setShipToChgToWmsSendList(shipToChgToWmsSendList);
        }
        // 2018/06/22 QC#20154 Add End
    }

    /**
     * checkInput
     * @param param NWZC150001PMsg
     * @return boolean
     */
    private boolean checkInput(final NWZC150001PMsg pMsg) {
        // Global Company Code
        if (!hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0188E);
        }

        // Contact Person Information List Check
        NWZC150001_cpoConfigPMsg cpoConfigList = null;
        for (int index = 0; index < pMsg.cpoConfig.getValidCount(); index++) {

            cpoConfigList = pMsg.cpoConfig.no(index);

            if (!hasValue(cpoConfigList.xxRqstTpCd)) {
                msgMap.addXxMsgId(NWZM0012E);
            }

            if (RQST_TP_CONFIG_NEW.equals(cpoConfigList.xxRqstTpCd.getValue())) {

                if (!hasValue(pMsg.cpoOrdNum)) {
                    msgMap.addXxMsgId(NWZM0912E);
                }
                if (!hasValue(cpoConfigList.dsOrdPosnNum)) {
                    msgMap.addXxMsgId(NWZM1374E);
                }
                if (!hasValue(cpoConfigList.configCatgCd)) {
                    msgMap.addXxMsgId(NWZM1375E);
                }
                if (!hasValue(cpoConfigList.configTpCd)) {
                    msgMap.addXxMsgId(NWZM1376E);
                }
                if (!hasValue(cpoConfigList.billToCustAcctCd)) {
                    msgMap.addXxMsgId(NWZM1377E);
                }
                if (!hasValue(cpoConfigList.billToCustCd)) {
                    msgMap.addXxMsgId(NWZM1378E);
                }
                if (!hasValue(cpoConfigList.shipToCustAcctCd)) {
                    msgMap.addXxMsgId(NWZM1379E);
                }
                if (!hasValue(cpoConfigList.shipToCustCd)) {
                    msgMap.addXxMsgId(NWZM1380E);
                }
            } else if (RQST_TP_CONFIG_MODIFY.equals(cpoConfigList.xxRqstTpCd.getValue())) {
                if (!hasValue(cpoConfigList.dsCpoConfigPk)) {
                    msgMap.addXxMsgId(NWZM1337E);
                }
            } else {
                msgMap.addXxMsgId(NWZM0013E);
            }
        }
        return !isError(pMsg);
    }

    /**
     * isError
     * @param msg NWZC150001PMsg
     * @return boolean
     */
    private boolean isError(NWZC150001PMsg msg) {
        if (msg.xxMsgIdList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Create Bisiness Prcess Log
     * @param eventId Event ID
     * @param dsOrdPosnNum DS Order Position Number
     * @param cpoOrdNum CPO Order Number
     * @return Business Process Log PK
     */
    private int createBizProcLog(String eventId, String dsOrdPosnNum, String cpoOrdNum, String configCatgCd) { // 2016/02/25 S21_NA#3328 Mod

        // Get Business Process Log PK
        int bizProcLogPk = EZDSeqTable.getNumberInt(ZYPOracleSeqConstant.BIZ_PROC_LOG_SQ);

        BIZ_PROC_LOGTMsg bizProcLogTMsg = new BIZ_PROC_LOGTMsg();
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.bizProcLogPk, BigDecimal.valueOf(bizProcLogPk));
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.trxId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.subSysId, SUB_SYS.NWZ);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.procId, PROCESS_ID);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.eventId, eventId);
        // 2016/02/25 S21_NA#3328 Mod Start
        if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
            ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.docTpCd, DOCUMENT_TYPE);
        } else {
            ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.docTpCd, DOCUMENT_TYPE_RTN);
        }
        // 2016/02/25 S21_NA#3328 Mod End
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.docId, dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.prntDocId, cpoOrdNum);
        EZDTBLAccessor.insert(bizProcLogTMsg);

        return bizProcLogPk;
    }

    // 2018/06/22 QC#20154 Add Start
    /**
     * isShipToChgToConfig
     * @param updTMsg DS_CPO_CONFIGTMsg
     * @param configMsg NWZC150001_cpoConfigPMsg
     */
    private boolean isShipToChgToConfig(DS_CPO_CONFIGTMsg updTMsg, NWZC150001_cpoConfigPMsg configMsg) {

        if (!S21StringUtil.isEquals(updTMsg.shipToCustAcctCd.getValue(), configMsg.shipToCustAcctCd.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(updTMsg.shipToCustLocCd.getValue(), configMsg.shipToCustCd.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(updTMsg.dropShipFlg.getValue(), configMsg.dropShipFlg.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(updTMsg.shipToLocNm.getValue(), configMsg.shipToLocNm.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(updTMsg.shipToAddlLocNm.getValue(), configMsg.shipToAddlLocNm.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(updTMsg.shipToFirstLineAddr.getValue(), configMsg.shipToFirstLineAddr.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(updTMsg.shipToScdLineAddr.getValue(), configMsg.shipToScdLineAddr.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(updTMsg.shipToThirdLineAddr.getValue(), configMsg.shipToThirdLineAddr.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(updTMsg.shipToFrthLineAddr.getValue(), configMsg.shipToFrthLineAddr.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(updTMsg.shipToFirstRefCmntTxt.getValue(), configMsg.shipTo01RefCmntTxt.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(updTMsg.shipToScdRefCmntTxt.getValue(), configMsg.shipTo02RefCmntTxt.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(updTMsg.shipToCtyAddr.getValue(), configMsg.shipToCtyAddr.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(updTMsg.shipToStCd.getValue(), configMsg.shipToStCd.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(updTMsg.shipToProvNm.getValue(), configMsg.shipToProvNm.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(updTMsg.shipToCntyNm.getValue(), configMsg.shipToCntyNm.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(updTMsg.shipToPostCd.getValue(), configMsg.shipToPostCd.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(updTMsg.shipToCtryCd.getValue(), configMsg.shipToCtryCd.getValue())) {
            return true;
        }

        return false;
    }
    // 2018/06/22 QC#20154 Add End
}
