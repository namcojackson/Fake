/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC410001;

import static com.canon.cusa.s21.api.NLZ.NLZC410001.constant.NLZC410001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.common.EZDPItem;
import business.db.TECH_INVTY_AGING_WRKTMsg;
import business.parts.NLZC410001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC410001.constant.NLZC410001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCharacterConversionUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_INFO_RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TECH_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.BaseObjectWrapper;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAInventoryTypeReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAMyInventory;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperationsResponse;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ObjectFactory;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperation;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.W6RequestedProperties;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.wrapper.ClickSoftwareOptimizationService;

/**
 * <pre>
 * Receive Field Request from Click Software API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/11   Hitachi         K.Kojima        Update          QC#16905
 * 2017/01/19   Hitachi         K.Kojima        Update          QC#16905
 * 2017/01/23   Hitachi         K.Kojima        Update          QC#16905
 * 2017/07/31   Hitachi         K.Kojima        Update          QC#20212
 * 2018/02/07   Hitachi         U.Kim           Update          QC#17647
 * 2018/05/18   CITS            M.Naito         Update          QC#18713
 * 2019/08/07   Hitachi         K.Fujimoto      Update          QC#52406
 * 2019/11/01   CITS            K.Ogino         Update          QC#53833
 * 2019/09/12   Hitachi         K.Fujimoto      Update          QC#53380
 * 2020/02/03   Hitachi         A.Kohinata      Update          QC#55717
 * 2020/02/13   Hitachi         K.Kitachi       Update          QC#55717
 * 2020/03/30   CITS            T.Wada          Update          QC#55791
 * 2020/04/25   CITS            T.Wada          Update          QC#55791-1
 * 2020/07/14   CITS            T.Wada          Update          QC#56895
 * 2020/09/10   CITS            T.Wada          Update          QC#57428
 * 2020/11/6    CITS            T.Wada          Update          QC#57801
 * 2022/10/19   Hitachi         K.Kishimoto     Update          QC#60712
 * 
 * </pre>
 */
public class NLZC410001 extends S21ApiCommonBase {

    /** ssm LowLevel Client */
    private S21SsmLowLevelCodingClient ssmLowClient;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Send Total Count */
    private int totalCount = 0;

    /** Send Error Count */
    private int errorCount = 0;

    /** Click Soft */
    private ClickSoftwareOptimizationService proxy = new ClickSoftwareOptimizationService();

    /** Click Soft Object Factory */
    private ObjectFactory objFactory = new ObjectFactory();

    private String sendClickProcStsCd = null;

    // QC#56895 Add Start
    private BigDecimal intvlMins = null;
    // QC#56895 Add End

    // START 2022/10/19 [QC#60712, ADD]
    /** OFS Multi Byte Convert Flag */
    private  boolean ofsMultiByteConvertFlg = false;
    // END   2022/10/19 [QC#60712, ADD]

    /**
     * Constructor
     */
    public NLZC410001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NLZC410001PMsg
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(final NLZC410001PMsg param, final ONBATCH_TYPE onBatchTp) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        try {
            if (checkParameter(msgMap)) {
                initial(msgMap);
                doProcess(msgMap);
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            ZYPEZDItemValueSetter.setValue(param.xxTotCnt, BigDecimal.valueOf(this.totalCount));
            ZYPEZDItemValueSetter.setValue(param.xxTotCnt_E, BigDecimal.valueOf(this.errorCount));
            msgMap.flush();
        }
    }

    /**
     * initial
     */
    private void initial(S21ApiMessageMap msgMap) {
        this.ssmLowClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    /**
     * checkParameter
     * @param msgMap S21ApiMessageMap
     * @return boolean
     */
    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NLZC410001PMsg pMsg = (NLZC410001PMsg) msgMap.getPmsg();
        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NLZM2504E);
        mandatoryCheck(msgMap, pMsg.procDt, NLZM2505E);
        mandatoryCheck(msgMap, pMsg.xxModeCd, NLZM2506E);
        if (ZYPCommonFunc.hasValue(pMsg.xxModeCd) && MODE_DAILIY.equals(pMsg.xxModeCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(pMsg.xxUpdTs) && !ZYPCommonFunc.hasValue(pMsg.mdseCd) && !ZYPCommonFunc.hasValue(pMsg.invtyLocCd)) {
                msgMap.addXxMsgId(NLZM2507E);
            }
        }
        // START 2017/07/31 K.Kojima [QC#20212,ADD]
        if (ZYPCommonFunc.hasValue(pMsg.xxModeCd) && MODE_NIGHTLY.equals(pMsg.xxModeCd.getValue())) {
            mandatoryCheck(msgMap, pMsg.xxModeCd_NT, NLZM2512E);
        }
        // END 2017/07/31 K.Kojima [QC#20212,ADD]
        if (msgMap.getMsgMgr().isXxMsgId()) {
            errorCount++;
            return false;
        }
        return true;
    }

    /**
     * doProcess
     * @param msgMap S21ApiMessageMap
     * @throws SQLException
     */
    // START 2020/02/13 K.Kitachi [QC#55717, ADD]
    private void doProcess(S21ApiMessageMap msgMap) throws SQLException {
        NLZC410001PMsg pMsg = (NLZC410001PMsg) msgMap.getPmsg();
        // START 2022/10/19 [QC#60712, ADD]
        String strOFSMultiByteConvertFlg = ZYPCodeDataUtil.getVarCharConstValue("OFS_MULTI_BYTE_CONVERT_FLG", pMsg.glblCmpyCd.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(strOFSMultiByteConvertFlg)) {
            ofsMultiByteConvertFlg = true;
        }
        // END   2022/10/19 [QC#60712, ADD]
        if (ZYPCommonFunc.hasValue(pMsg.xxUpdTs) && MODE_DAILIY.equals(pMsg.xxModeCd.getValue())) {

            // QC#56895 ADD Start
            intvlMins = ZYPCodeDataUtil.getNumConstValue("NSZC4100_RTRY_INTVL_MINS", pMsg.glblCmpyCd.getValue());
            if (!ZYPCommonFunc.hasValue(intvlMins)) {
                intvlMins = new BigDecimal(DEF_INTVL_MINS);
            }
            // QC#56895 ADD End

            // QC#55791 Add Start
            // Recovers Inventory delete records that resulted in an error in the SendClick processing.
            reProcForDeleteError(msgMap);
            // Reprocess Inventory records that resulted in an error in the SendClick processing
            reProcDaily(msgMap);
            // QC#55791 Add End

            List<Map<String, Object>> ownerChangeList = getOwnerChangeInfo(pMsg.glblCmpyCd.getValue(), pMsg.xxUpdTs.getValue());
            if (ownerChangeList.size() > 0) {
                ownerChangeProcess(msgMap, ownerChangeList);
            }
        }
        // QC#55791 Add Start
        if (MODE_NIGHTLY.equals(pMsg.xxModeCd.getValue())) {
            // Reprocess Inventory records that all Inventory that changed on the day.
            reProcNightly(msgMap);
        }
        // QC#55791 Add End

        doProcess(msgMap, null);
    }
    // END 2020/02/13 K.Kitachi [QC#55717, ADD]

    // START 2020/02/13 K.Kitachi [QC#55717, MOD]
//    private void doProcess(S21ApiMessageMap msgMap) throws SQLException {
    private void doProcess(S21ApiMessageMap msgMap, List<Map<String, Object>> ownerChangeList) throws SQLException {
    // END 2020/02/13 K.Kitachi [QC#55717, MOD]
        NLZC410001PMsg pMsg = (NLZC410001PMsg) msgMap.getPmsg();
        String maxSnapShotDt = getMaxSnapShotDt(pMsg.glblCmpyCd.getValue());

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // START 2017/01/23 K.Kojima [QC#16905,MOD]
            // ps = getSendData(pMsg, maxSnapShotDt);
            if (MODE_DAILIY.equals(pMsg.xxModeCd.getValue())) {
                // START 2020/02/13 K.Kitachi [QC#55717, MOD]
//                ps = getSendData(pMsg, maxSnapShotDt);
                if (ownerChangeList == null) {
                    ps = getSendData(pMsg, maxSnapShotDt);
                } else {
                    // QC#56895 MOD START
                    List<Map<String, Object>> ownerChangeInvtyLocList = new ArrayList<Map<String, Object>>();
                    for (Map<String, Object> ownerChange : ownerChangeList) {
                        List<Map<String, Object>> invtyLocCdList = getInvtyLocCdList(pMsg.glblCmpyCd.getValue(), (String) ownerChange.get(RTL_WH_CD), (String) ownerChange.get(TECH_TOC_CD_BEF));
                        for (Map<String, Object> invtyLocCd : invtyLocCdList) {
                            ownerChangeInvtyLocList.add(invtyLocCd) ;
                        }
                    }
//                  ps = getSendDataForOwnerChange(pMsg, maxSnapShotDt, ownerChangeList);
                    ps = getSendDataForOwnerChange(pMsg, maxSnapShotDt, ownerChangeInvtyLocList);
                	// QC#56895 MOD END
                }
                // END 2020/02/13 K.Kitachi [QC#55717, MOD]
            } else {
                ps = getSendDataForNightly(pMsg, maxSnapShotDt);
            }
            // END 2017/01/23 K.Kojima [QC#16905,MOD]
            rs = ps.executeQuery();

            StandardOperations standardOperations = objFactory.createStandardOperations();
            List<CSAMyInventory> myInventoryList = new ArrayList<CSAMyInventory>();
            // START 2019/08/15 K.Fujimoto [QC#52406,ADD]
            StandardOperations standardOperationsForDelete = objFactory.createStandardOperations();
            List<CSAMyInventory> myInventoryListForDelete = new ArrayList<CSAMyInventory>();
            // END   2019/08/15 K.Fujimoto [QC#52406,ADD]
            // START 2017/01/23 K.Kojima [QC#16905,ADD]
            List<TECH_INVTY_AGING_WRKTMsg> insertList = new ArrayList<TECH_INVTY_AGING_WRKTMsg>();
            List<TECH_INVTY_AGING_WRKTMsg> updateList = new ArrayList<TECH_INVTY_AGING_WRKTMsg>();
            // END 2017/01/23 K.Kojima [QC#16905,ADD]

            BigDecimal wmbMaxCount = ZYPCodeDataUtil.getNumConstValue(WMB_MAX_LENGTH, pMsg.glblCmpyCd.getValue());
            if (wmbMaxCount == null) {
                wmbMaxCount = BigDecimal.ZERO;
            }

            int sendTargetCnt = 0;
            // START 2019/08/15 K.Fujimoto [QC#52406,ADD]
            int sendTargetCntForDelete = 0;
            // END   2019/08/15 K.Fujimoto [QC#52406,ADD]
            Map<String, Object> currentMap = null;
            Map<String, Object> preMap = null;

            int diffQty = 0;

            while (rs.next()) {
                currentMap = getTargetMap(rs);

                if (preMap == null) {
                    diffQty = getInteger(currentMap, INVTY_QTY);
                } else if (checkChangeInventory(currentMap, preMap)) {
                    if (diffQty != 0) {
                        if (!MODE_NIGHTLY.equals(pMsg.xxModeCd.getValue()) && ZYPCommonFunc.hasValue(getString(preMap, SER_NUM))) {
                            // START 2017/01/23 K.Kojima
                            // [QC#16905,MOD]
                            // myInventoryList.add(setCSAMyInventory(pMsg.xxModeCd.getValue(),
                            // preMap, diffQty));
                            // START 2019/08/15 K.Fujimoto [QC#52406,MOD]
                            //setSendData(myInventoryList, insertList, updateList, pMsg, preMap, diffQty);
                            setSendData(myInventoryList, myInventoryListForDelete, insertList, updateList, pMsg, preMap, diffQty);
                            // END   2019/08/15 K.Fujimoto [QC#52406,MOD]
                            // END 2017/01/23 K.Kojima [QC#16905,MOD]
                        }
                    }
                    diffQty = getInteger(currentMap, INVTY_QTY);
                }

                if (BigDecimal.ZERO.compareTo(wmbMaxCount) < 0) {
                    if (sendTargetCnt + myInventoryList.size() >= wmbMaxCount.intValue() && sendTargetCnt == 0) {
                        sendTargetCnt = setStandardOperations(standardOperations, myInventoryList, sendTargetCnt);
                        myInventoryList = new ArrayList<CSAMyInventory>();
                        callWmbApi(msgMap, standardOperations);
                        // START 2017/01/23 K.Kojima [QC#16905,ADD]
                        insertUpdateWork(insertList, updateList);
                        insertList = new ArrayList<TECH_INVTY_AGING_WRKTMsg>();
                        updateList = new ArrayList<TECH_INVTY_AGING_WRKTMsg>();
                        // END 2017/01/23 K.Kojima [QC#16905,ADD]
                        standardOperations = objFactory.createStandardOperations();
                        sendTargetCnt = 0;
                    } else if (sendTargetCnt + myInventoryList.size() > wmbMaxCount.intValue()) {
                        callWmbApi(msgMap, standardOperations);
                        // START 2017/01/23 K.Kojima [QC#16905,ADD]
                        insertUpdateWork(insertList, updateList);
                        insertList = new ArrayList<TECH_INVTY_AGING_WRKTMsg>();
                        updateList = new ArrayList<TECH_INVTY_AGING_WRKTMsg>();
                        // END 2017/01/23 K.Kojima [QC#16905,ADD]
                        standardOperations = objFactory.createStandardOperations();
                        sendTargetCnt = 0;
                    }

                    // START 2019/08/15 K.Fujimoto [QC#52406,ADD]
                    if (sendTargetCntForDelete + myInventoryListForDelete.size() >= wmbMaxCount.intValue() && sendTargetCntForDelete == 0) {
                        sendTargetCntForDelete  = setStandardOperationsForDelete(standardOperationsForDelete, myInventoryListForDelete, sendTargetCntForDelete);
                        myInventoryListForDelete = new ArrayList<CSAMyInventory>();
                        callWmbApi(msgMap, standardOperationsForDelete);
                        standardOperationsForDelete = objFactory.createStandardOperations();
                        sendTargetCntForDelete = 0;
                    } else if (sendTargetCntForDelete + myInventoryListForDelete.size() > wmbMaxCount.intValue()) {
                        callWmbApi(msgMap, standardOperationsForDelete);
                        standardOperationsForDelete = objFactory.createStandardOperations();
                        sendTargetCntForDelete = 0;
                    }
                    // END   2019/08/15 K.Fujimoto [QC#52406,ADD]
                    
                }

                if (checkChangeTechnician(currentMap, preMap)) {
                    sendTargetCnt = setStandardOperations(standardOperations, myInventoryList, sendTargetCnt);
                    myInventoryList = new ArrayList<CSAMyInventory>();
                    // START 2019/08/15 K.Fujimoto [QC#52406,ADD]
                    if (myInventoryListForDelete.size() != 0) {
                      sendTargetCntForDelete  = setStandardOperationsForDelete(standardOperationsForDelete, myInventoryListForDelete, sendTargetCntForDelete);
                      myInventoryListForDelete = new ArrayList<CSAMyInventory>();
                    }
                    // END   2019/08/15 K.Fujimoto [QC#52406,ADD]
                }

                // START 2017/01/23 K.Kojima [QC#16905,MOD]
                // myInventoryList.add(setCSAMyInventory(pMsg.xxModeCd.getValue(),
                // currentMap, 0));
                // START 2019/08/15 K.Fujimoto [QC#52406,MOD]
                //setSendData(myInventoryList, insertList, updateList, pMsg, currentMap, 0);
                setSendData(myInventoryList, myInventoryListForDelete, insertList, updateList, pMsg, currentMap, 0);
                // END   2019/08/15 K.Fujimoto [QC#52406,MOD]
                // END 2017/01/23 K.Kojima [QC#16905,MOD]

                preMap = currentMap;
                diffQty = diffQty - getInteger(currentMap, SNAP_INVTY_QTY);
            }
            if (preMap != null) {
                if (diffQty != 0) {
                    if (!MODE_NIGHTLY.equals(pMsg.xxModeCd.getValue()) && ZYPCommonFunc.hasValue(getString(preMap, SER_NUM))) {
                        // START 2017/01/23 K.Kojima [QC#16905,MOD]
                        // myInventoryList.add(setCSAMyInventory(pMsg.xxModeCd.getValue(),
                        // preMap, diffQty));
                        // START 2019/08/15 K.Fujimoto [QC#52406,MOD]
                        //setSendData(myInventoryList, insertList, updateList, pMsg, preMap, diffQty);
                        setSendData(myInventoryList, myInventoryListForDelete, insertList, updateList, pMsg, preMap, diffQty);
                        // END   2019/08/15 K.Fujimoto [QC#52406,MOD]
                        // END 2017/01/23 K.Kojima [QC#16905,MOD]
                    }
                }
            }
            if (myInventoryList.size() != 0) {
                setStandardOperations(standardOperations, myInventoryList, sendTargetCnt);
                callWmbApi(msgMap, standardOperations);
                // START 2017/01/23 K.Kojima [QC#16905,ADD]
                insertUpdateWork(insertList, updateList);
                // END 2017/01/23 K.Kojima [QC#16905,ADD]
            }
            // START 2019/08/15 K.Fujimoto [QC#52406,ADD]
            // mod start 2020/02/03 QC#55717
            //if (myInventoryListForDelete.size() != 0) {
            if (sendTargetCntForDelete + myInventoryListForDelete.size() > 0) {
            // mod end 2020/02/03 QC#55717
                sendTargetCntForDelete  = setStandardOperationsForDelete(standardOperationsForDelete, myInventoryListForDelete, sendTargetCntForDelete);
                callWmbApi(msgMap, standardOperationsForDelete);
            }
            // END   2019/08/15 K.Fujimoto [QC#52406,ADD]
        } catch (SQLException e) {
            throw e;
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * setStandardOperations
     * @param standardOperations StandardOperations
     * @param myInventoryList List<CSAMyInventory>
     * @param sendTargetCnt int
     * @return int
     */
    private int setStandardOperations(StandardOperations standardOperations, List<CSAMyInventory> myInventoryList, int sendTargetCnt) {
        for (CSAMyInventory myInventory : myInventoryList) {
            StandardOperation standardOperation = objFactory.createStandardOperation();
            standardOperation.setAction("UpdateOrCreate");
            W6RequestedProperties props = objFactory.createW6RequestedProperties();
            standardOperation.setRequestedProperties(props);

            BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
            bow.setObject(myInventory);
            standardOperation.setObject(bow);
            standardOperations.getOperation().add(standardOperation);
            sendTargetCnt++;
            this.totalCount++;
        }
        return sendTargetCnt;
    }

    // START 2019/08/07 K.Fujimoto [QC#52406,ADD]
    /**
     * setStandardOperationsForDelete
     * @param standardOperations StandardOperations
     * @param myInventoryListForDelete List<CSAMyInventory>
     * @param sendTargetCnt int
     * @return int
     */
    private int setStandardOperationsForDelete(StandardOperations standardOperations, List<CSAMyInventory> myInventoryListForDelete, int sendTargetCnt) {
        for (CSAMyInventory myInventory : myInventoryListForDelete) {
            StandardOperation standardOperation = objFactory.createStandardOperation();
            standardOperation.setAction("Delete");
            W6RequestedProperties props = objFactory.createW6RequestedProperties();
            standardOperation.setRequestedProperties(props);
            BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
            bow.setObject(myInventory);
            standardOperation.setObject(bow);
            standardOperations.getOperation().add(standardOperation);
            sendTargetCnt++;
            this.totalCount++;
        }
        return sendTargetCnt;
    }
    // END   2019/08/07 K.Fujimoto [QC#52406,ADD]

    /**
     * setSendData
     * @param myInventoryList List<CSAMyInventory>
     * @param deleteMyInventoryList List<CSAMyInventory>
     * @param insertList List<TECH_INVTY_AGING_WRKTMsg>
     * @param updateList List<TECH_INVTY_AGING_WRKTMsg>
     * @param pMsg NLZC410001PMsg
     * @param targetMap Map<String, Object>
     * @param qty int
     */
    // START 2017/01/23 K.Kojima [QC#16905,MOD]
    // private CSAMyInventory setCSAMyInventory(String xxModeCd,
    // Map<String, Object> targetMap, int qty) {
    // START 2019/08/07 K.Fujimoto [QC#52406,MOD]
    private void setSendData(List<CSAMyInventory> myInventoryList, List<CSAMyInventory> deleteMyInventoryList, List<TECH_INVTY_AGING_WRKTMsg> insertList, List<TECH_INVTY_AGING_WRKTMsg> updateList, NLZC410001PMsg pMsg, Map<String, Object> targetMap, int qty) {
//    private void setSendData(List<CSAMyInventory> myInventoryList, List<TECH_INVTY_AGING_WRKTMsg> insertList, List<TECH_INVTY_AGING_WRKTMsg> updateList, NLZC410001PMsg pMsg, Map<String, Object> targetMap, int qty) {
        // START 2019/08/07 K.Fujimoto [QC#52406,MOD]
        // END 2017/01/23 K.Kojima [QC#16905,MOD]

        // START 2017/01/23 K.Kojima [QC#16905,ADD]
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String procDt = pMsg.procDt.getValue();
        String xxModeCd = pMsg.xxModeCd.getValue();
        // END 2017/01/23 K.Kojima [QC#16905,ADD]

        CSAMyInventory myInventory = objFactory.createCSAMyInventory();
        myInventory.setTechnicianID(getString(targetMap, TECH_TOC_CD));
        myInventory.setPartNo(getString(targetMap, MDSE_CD));

        CSAInventoryTypeReference myInventoryReference = objFactory.createCSAInventoryTypeReference();
        myInventoryReference.getContent().add(getString(targetMap, RTL_SWH_CD));
        myInventory.setInventoryType(myInventoryReference);

        String partSerialNo = getString(targetMap, SER_NUM);
        Integer quantity = getInteger(targetMap, INVTY_QTY);
        Integer snapQuantity = getInteger(targetMap, SNAP_INVTY_QTY);
        if (ZYPCommonFunc.hasValue(partSerialNo)) {
            quantity = snapQuantity;
        }
        Integer age029 = getInteger(targetMap, FIRST_MTH_INVTY_QTY);
        Integer age3059 = getInteger(targetMap, SCD_MTH_INVTY_QTY);
        Integer age6089 = getInteger(targetMap, THIRD_MTH_INVTY_QTY);
        Integer age90 = getInteger(targetMap, OVER_FRTH_MTH_INVTY_QTY);

        if (!MODE_NIGHTLY.equals(xxModeCd)) {
            if (!ZYPCommonFunc.hasValue(partSerialNo)) {
                if (quantity >= snapQuantity) {
                    age029 += quantity - snapQuantity;
                } else {
                    int count = snapQuantity - quantity;
                    Integer[] intList = new Integer[] {age90, age6089, age3059, age029 };
                    for (int i = 0; i < intList.length; i++) {
                        if (count > 0) {
                            if (intList[i] <= count) {
                                count = count - intList[i];
                                intList[i] = 0;
                            } else {
                                intList[i] = intList[i] - count;
                                count = 0;
                            }
                        }
                    }
                    age029 = intList[3];
                    age3059 = intList[2];
                    age6089 = intList[1];
                    age90 = intList[0];
                }
            } else if (qty != 0) {
                partSerialNo = null;
                quantity = qty;
                age029 = qty;
                age3059 = 0;
                age6089 = 0;
                age90 = 0;
            }
        }

        myInventory.setPartSerialNo(partSerialNo);
        myInventory.setDescription(getString(targetMap, MDSE_NM));
        myInventory.setQuantity(quantity);
        myInventory.setReturnControlled(setRtrnFlg(getString(targetMap, RTRN_CTRL_FLG)));
        // START 2017/01/23 K.Kojima [QC#16905,MOD]
        // myInventory.setPrice(getInteger(targetMap,
        // THIS_MTH_TOT_STD_COST_AMT));
        // START 2018/02/07 U.Kim [QC#17647, ADD]
        // START 2018/05/18 M.Naito [QC#18713, MOD]
//        myInventory.setPrice(getInteger(targetMap, PRC_LIST_EQUIP_PRC_AMT).intValue());
        myInventory.setPrice(getBigDecimal(targetMap, PRC_LIST_EQUIP_PRC_AMT).floatValue());
        // END 2018/05/18 M.Naito [QC#18713, MOD]
        myInventory.setMAXQTY(getStringForMaxMinQty(targetMap, MAX_INVTY_QTY));
        myInventory.setMINQTY(getStringForMaxMinQty(targetMap, MIN_INVTY_QTY));
        // END 2018/02/07 U.Kim [QC#17647, ADD]
        myInventory.setUnitCost(getBigDecimal(targetMap, THIS_MTH_TOT_STD_COST_AMT).floatValue());
        // END 2017/01/23 K.Kojima [QC#16905,MOD]
        myInventory.setAge029(age029);
        myInventory.setAge3059(age3059);
        myInventory.setAge6089(age6089);
        myInventory.setAge90(age90);
        myInventory.setEngineerStampID(getString(targetMap, WH_MGR_PSN_CD));
        myInventory.setWHCode(getString(targetMap, RTL_WH_CD));
        myInventory.setWarehouse(getString(targetMap, WH_NM));
        myInventory.setSurveyRequired(setRtrnFlg(getString(targetMap, PRT_SRVY_REQ_FLG)));
        // START 2019/09/12 K.Fujimoto [QC#53380,ADD]
        String rtlWhCatgCd = getString(targetMap, RTL_WH_CATG_CD);
        boolean csaOnSite = false;
        if (rtlWhCatgCd.equals(RTL_WH_CATG.CUSTOMER)) {
            csaOnSite = true;
        }
        myInventory.setCSAOnSite(csaOnSite);
        // END   2019/09/12 K.Fujimoto [QC#53380,ADD]
        // START 2017/01/23 K.Kojima [QC#16905,MOD]
        // return myInventory;
        myInventoryList.add(myInventory);
        // END 2017/01/23 K.Kojima [QC#16905,MOD]

        // START 2017/01/23 K.Kojima [QC#16905,ADD]
        // QC#53833
        if (!ZYPCommonFunc.hasValue(pMsg.mdseCd)) {
            BigDecimal techInvtyAgingWrkPk = null;
            if (qty != 0) {
                techInvtyAgingWrkPk = getDailySerialRecord(glblCmpyCd, getString(targetMap, MDSE_CD), getString(targetMap, INVTY_LOC_CD));
            } else {
                techInvtyAgingWrkPk = getBigDecimal(targetMap, TECH_INVTY_AGING_WRK_PK);
            }
            TECH_INVTY_AGING_WRKTMsg tMsg = new TECH_INVTY_AGING_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            if (techInvtyAgingWrkPk != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.techInvtyAgingWrkPk, techInvtyAgingWrkPk);
                // QC#53833
                tMsg = (TECH_INVTY_AGING_WRKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
                if (tMsg.techInvtyAgingWrkDt.getValue().equals(procDt)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.techInvtyAgingWrkNum, tMsg.techInvtyAgingWrkNum.getValue().add(BigDecimal.ONE));
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.techInvtyAgingWrkDt, procDt);
                    ZYPEZDItemValueSetter.setValue(tMsg.techInvtyAgingWrkNum, BigDecimal.ONE);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.techInvtyAgingWrkPk, (BigDecimal) ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TECH_INVTY_AGING_WRK_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.techInvtyAgingWrkDt, procDt);
                ZYPEZDItemValueSetter.setValue(tMsg.techInvtyAgingWrkNum, BigDecimal.ONE);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.techTocCd, getString(targetMap, TECH_TOC_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, getString(targetMap, MDSE_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.invtyLocCd, getString(targetMap, INVTY_LOC_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, getString(targetMap, RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, getString(targetMap, RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.serNum, partSerialNo);
            // START 2022/10/19 [QC#60712, MOD]
//            ZYPEZDItemValueSetter.setValue(tMsg.mdseNm, getString(targetMap, MDSE_NM));
            ZYPEZDItemValueSetter.setValue(tMsg.mdseNm, ofsMultiByteConvert(getString(targetMap, MDSE_NM)));
            // END   2022/10/19 [QC#60712, MOD]
            ZYPEZDItemValueSetter.setValue(tMsg.invtyQty, BigDecimal.valueOf(quantity));
            ZYPEZDItemValueSetter.setValue(tMsg.rtrnCtrlFlg, getString(targetMap, RTRN_CTRL_FLG));
            ZYPEZDItemValueSetter.setValue(tMsg.thisMthTotStdCostAmt, getBigDecimal(targetMap, THIS_MTH_TOT_STD_COST_AMT));
            ZYPEZDItemValueSetter.setValue(tMsg.firstMthInvtyQty, BigDecimal.valueOf(age029));
            ZYPEZDItemValueSetter.setValue(tMsg.scdMthInvtyQty, BigDecimal.valueOf(age3059));
            ZYPEZDItemValueSetter.setValue(tMsg.thirdMthInvtyQty, BigDecimal.valueOf(age6089));
            ZYPEZDItemValueSetter.setValue(tMsg.overFrthMthInvtyQty, BigDecimal.valueOf(age90));
            ZYPEZDItemValueSetter.setValue(tMsg.whMgrPsnCd, getString(targetMap, WH_MGR_PSN_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.whNm, getString(targetMap, WH_NM));
            ZYPEZDItemValueSetter.setValue(tMsg.siteSrvyReqFlg, getString(targetMap, PRT_SRVY_REQ_FLG));
            // START 2018/02/07 U.Kim [QC#17647, ADD]
            ZYPEZDItemValueSetter.setValue(tMsg.maxInvtyQty, getBigDecimal(targetMap, MAX_INVTY_QTY));
            ZYPEZDItemValueSetter.setValue(tMsg.minInvtyQty, getBigDecimal(targetMap, MIN_INVTY_QTY));
            ZYPEZDItemValueSetter.setValue(tMsg.prcListEquipPrcAmt, getBigDecimal(targetMap, PRC_LIST_EQUIP_PRC_AMT));
            // END 2018/02/07 U.Kim [QC#17647, ADD]

            // QC#55791 Add Start
            ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_OFF_N);
            // QC#55791 Add End

            if (techInvtyAgingWrkPk != null) {
                updateList.add(tMsg);
            } else {
                insertList.add(tMsg);
            }
        }
        // END 2017/01/23 K.Kojima [QC#16905,ADD]
        // START 2019/08/07 K.Fujimoto [QC#52406,ADD]
        if(!MODE_DAILIY.equals(pMsg.xxModeCd.getValue()) && (!getString(targetMap, WH_MGR_PSN_CD).equals(getString(targetMap, WH_MGR_PSN_CD_BEF)) || !getString(targetMap, TECH_TOC_CD).equals(getString(targetMap, TECH_TOC_CD_BEF)))){
            setSendDataForDelete(deleteMyInventoryList, targetMap);
        }
        // END   2019/08/07 K.Fujimoto [QC#52406,ADD]
    }

    // START 2019/08/07 K.Fujimoto [QC#52406,ADD]
    /**
     * setSendDataForDelete
     * @param myInventoryList List<CSAMyInventory>
     * @param deleteList List<TECH_INVTY_AGING_WRKTMsg>
     * @param targetMap Map<String, Object>
     */
    private void setSendDataForDelete(List<CSAMyInventory> deleteMyInventoryList, Map<String, Object> targetMap) {

        CSAMyInventory myInventory = objFactory.createCSAMyInventory();
        myInventory.setTechnicianID(getString(targetMap, TECH_TOC_CD_BEF));
        myInventory.setPartNo(getString(targetMap, MDSE_CD));

        CSAInventoryTypeReference myInventoryReference = objFactory.createCSAInventoryTypeReference();
        myInventoryReference.getContent().add(getString(targetMap, RTL_SWH_CD));
        myInventory.setInventoryType(myInventoryReference);

        String partSerialNo = getString(targetMap, SER_NUM);
        Integer quantity = getInteger(targetMap, INVTY_QTY);
        Integer snapQuantity = getInteger(targetMap, SNAP_INVTY_QTY);
        if (ZYPCommonFunc.hasValue(partSerialNo)) {
            quantity = snapQuantity;
        }
        Integer age029 = getInteger(targetMap, FIRST_MTH_INVTY_QTY);
        Integer age3059 = getInteger(targetMap, SCD_MTH_INVTY_QTY);
        Integer age6089 = getInteger(targetMap, THIRD_MTH_INVTY_QTY);
        Integer age90 = getInteger(targetMap, OVER_FRTH_MTH_INVTY_QTY);

        myInventory.setPartSerialNo(partSerialNo);
        myInventory.setDescription(getString(targetMap, MDSE_NM));
        myInventory.setQuantity(quantity);
        myInventory.setReturnControlled(setRtrnFlg(getString(targetMap, RTRN_CTRL_FLG)));
        myInventory.setPrice(getBigDecimal(targetMap, PRC_LIST_EQUIP_PRC_AMT).floatValue());
        myInventory.setMAXQTY(getStringForMaxMinQty(targetMap, MAX_INVTY_QTY));
        myInventory.setMINQTY(getStringForMaxMinQty(targetMap, MIN_INVTY_QTY));
        myInventory.setUnitCost(getBigDecimal(targetMap, THIS_MTH_TOT_STD_COST_AMT).floatValue());
        myInventory.setAge029(age029);
        myInventory.setAge3059(age3059);
        myInventory.setAge6089(age6089);
        myInventory.setAge90(age90);
        myInventory.setEngineerStampID(getString(targetMap, WH_MGR_PSN_CD_BEF));
        myInventory.setWHCode(getString(targetMap, RTL_WH_CD));
        myInventory.setWarehouse(getString(targetMap, WH_NM));
        myInventory.setSurveyRequired(setRtrnFlg(getString(targetMap, PRT_SRVY_REQ_FLG)));
        // START 2019/09/12 K.Fujimoto [QC#53380,ADD]
        String rtlWhCatgCd = getString(targetMap, RTL_WH_CATG_CD);
        boolean csaOnSite = false;
        if (rtlWhCatgCd.equals(RTL_WH_CATG.CUSTOMER)) {
            csaOnSite = true;
        }
        myInventory.setCSAOnSite(csaOnSite);
        // END   2019/09/12 K.Fujimoto [QC#53380,ADD]
        deleteMyInventoryList.add(myInventory);
    }
    // END   2019/08/07 K.Fujimoto [QC#52406,ADD]
    /**
     * getMaxSnapShotDt
     * @return String
     */
    private String getMaxSnapShotDt(String glblCmpyCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("techProcStsCd", TECH_PROC_STS.PROCESSED);
        return (String) this.ssmBatchClient.queryObject("getMaxSnapShotDt", param);
    }

    /**
     * getSendData
     * @param maxSnapShotDt String
     * @return PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement getSendData(NLZC410001PMsg pMsg, String maxSnapShotDt) throws SQLException {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("procDt", pMsg.procDt.getValue());
        param.put("maxSnapShotDt", maxSnapShotDt);

        // QC#55791 Mod Start
        //param.put("xxUpdTs", pMsg.xxUpdTs.getValue());
        if (ZYPCommonFunc.hasValue(pMsg.xxUpdTs.getValue())) {
            long diffMin = 10 * 60 * 1000; // 10min
            String xxUpdTs = EZDCommonFunc.toyyyyMMddHHmmssSSS((EZDCommonFunc.toTimeMinute(pMsg.xxUpdTs.getValue()) - diffMin));
            param.put("xxUpdTs", xxUpdTs);
        }
        // QC#55791 Mod End

        param.put("mdseCd", pMsg.mdseCd.getValue());
        param.put("invtyLocCd", pMsg.invtyLocCd.getValue());
        param.put("locStsCd", LOC_STS.DC_STOCK);
        param.put("locTpCd", LOC_TP.TECHNICIAN);
        param.put("dateFormat", ZYPDateUtil.TYPE_YYYYMMDD);
        // START 2018/02/07 U.Kim [QC#17647, ADD]
        param.put("prcListTpCd", PRC_LIST_TP.PARTS);
        param.put("onFlg", ZYPConstant.FLG_ON_Y);
        param.put("offFlg", ZYPConstant.FLG_OFF_N);
        param.put("prcQlfyTpCd", PRC_QLFY_TP.ITEM_CODE);
        // END 2018/02/07 U.Kim [QC#17647, ADD]
        return this.ssmLowClient.createPreparedStatement("getSendData", param, execParam);
    }

    // START 2017/01/23 K.Kojima [QC#16905,ADD]
    /**
     * getSendDataForNightly
     * @param maxSnapShotDt String
     * @return PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement getSendDataForNightly(NLZC410001PMsg pMsg, String maxSnapShotDt) throws SQLException {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("procDt", pMsg.procDt.getValue());
        param.put("maxSnapShotDt", maxSnapShotDt);
        param.put("locStsCd", LOC_STS.DC_STOCK);
        param.put("locTpCd", LOC_TP.TECHNICIAN);
        param.put("dateFormat", ZYPDateUtil.TYPE_YYYYMMDD);
        // START 2017/07/31 K.Kojima [QC#20212,ADD]
        param.put("techTocCdRegexpLineString", getTechTocCdRegexpLineString(pMsg.xxModeCd_NT.getValue()));
        // END 2017/07/31 K.Kojima [QC#20212,ADD]
        // START 2018/02/07 U.Kim [QC#17647, ADD]
        param.put("prcListTpCd", PRC_LIST_TP.PARTS);
        param.put("onFlg", ZYPConstant.FLG_ON_Y);
        param.put("offFlg", ZYPConstant.FLG_OFF_N);
        param.put("prcQlfyTpCd", PRC_QLFY_TP.ITEM_CODE);
        // END 2018/02/07 U.Kim [QC#17647, ADD]
        return this.ssmLowClient.createPreparedStatement("getSendDataForNightly", param, execParam);
    }

    /**
     * getDailySerialRecord
     * @return String
     */
    private BigDecimal getDailySerialRecord(String glblCmpyCd, String mdseCd, String invtyLocCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("mdseCd", mdseCd);
        param.put("invtyLocCd", invtyLocCd);
        return (BigDecimal) this.ssmBatchClient.queryObject("getDailySerialRecord", param);
    }

    // END 2017/01/23 K.Kojima [QC#16905,ADD]

    /**
     * callWmbApi
     * @param msgMap S21ApiMessageMap
     * @param standardOperations StandardOperations
     */
    private void callWmbApi(S21ApiMessageMap msgMap, StandardOperations standardOperations) {

        // QC#55791 Add
        sendClickProcStsCd = PROC_STS.IN_COMPLETED;

        ExecuteMultipleOperations emo = objFactory.createExecuteMultipleOperations();
        emo.setOneTransaction(true);
        emo.setContinueOnError(true);
        emo.setOperations(standardOperations);

        try {
            ExecuteMultipleOperationsResponse response = this.proxy.executeMultipleOperations(INTERFACE_ID, emo);
        } catch (Exception e) {
            errorCount++;
            msgMap.addXxMsgId(NLAM1315E);
            e.printStackTrace();
            // QC#55791 Add
            sendClickProcStsCd = PROC_STS.ERROR;

            return;
        } catch (Throwable t) {
            errorCount++;
            msgMap.addXxMsgId(NLAM1315E);
            t.printStackTrace();
            // QC#55791 Add
            sendClickProcStsCd = PROC_STS.ERROR;
            return;
        }
        // QC#55791 Add
        sendClickProcStsCd = PROC_STS.COMPLEATED;
    }

    /**
     * getTargetMap
     * @param rs ResultSet
     * @return Map<String, Object>
     * @throws SQLException
     */
    private Map<String, Object> getTargetMap(ResultSet rs) throws SQLException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(TECH_TOC_CD, rs.getObject(TECH_TOC_CD));
        // START 2019/08/15 K.Fujimoto [QC#52406,ADD]
        resultMap.put(TECH_TOC_CD_BEF, rs.getObject(TECH_TOC_CD_BEF));
        // END 2019/08/15 K.Fujimoto [QC#52406,ADD]
        resultMap.put(MDSE_CD, rs.getObject(MDSE_CD));
        resultMap.put(INVTY_LOC_CD, rs.getObject(INVTY_LOC_CD));
        resultMap.put(RTL_WH_CD, rs.getObject(RTL_WH_CD));
        resultMap.put(RTL_SWH_CD, rs.getObject(RTL_SWH_CD));
        resultMap.put(SER_NUM, rs.getObject(SER_NUM));
        resultMap.put(MDSE_NM, rs.getObject(MDSE_NM));
        resultMap.put(INVTY_QTY, rs.getObject(INVTY_QTY));
        resultMap.put(SNAP_INVTY_QTY, rs.getObject(SNAP_INVTY_QTY));
        resultMap.put(RTRN_CTRL_FLG, rs.getObject(RTRN_CTRL_FLG));
        resultMap.put(THIS_MTH_TOT_STD_COST_AMT, rs.getObject(THIS_MTH_TOT_STD_COST_AMT));
        resultMap.put(FIRST_MTH_INVTY_QTY, rs.getObject(FIRST_MTH_INVTY_QTY));
        resultMap.put(SCD_MTH_INVTY_QTY, rs.getObject(SCD_MTH_INVTY_QTY));
        resultMap.put(THIRD_MTH_INVTY_QTY, rs.getObject(THIRD_MTH_INVTY_QTY));
        resultMap.put(OVER_FRTH_MTH_INVTY_QTY, rs.getObject(OVER_FRTH_MTH_INVTY_QTY));
        // START 2019/08/15 K.Fujimoto [QC#52406,ADD]
        resultMap.put(WH_MGR_PSN_CD_BEF, rs.getObject(WH_MGR_PSN_CD_BEF));
        // END 2019/08/15 K.Fujimoto [QC#52406,ADD]
        resultMap.put(WH_MGR_PSN_CD, rs.getObject(WH_MGR_PSN_CD));
        resultMap.put(WH_NM, rs.getObject(WH_NM));
        resultMap.put(PRT_SRVY_REQ_FLG, rs.getObject(PRT_SRVY_REQ_FLG));
        // START 2017/01/23 K.Kojima [QC#16905,ADD]
        resultMap.put(TECH_INVTY_AGING_WRK_PK, rs.getObject(TECH_INVTY_AGING_WRK_PK));
        // END 2017/01/23 K.Kojima [QC#16905,ADD]
        // START 2018/02/07 U.Kim [QC#17647, ADD]
        resultMap.put(MIN_INVTY_QTY, rs.getObject(MIN_INVTY_QTY));
        resultMap.put(MAX_INVTY_QTY, rs.getObject(MAX_INVTY_QTY));
        resultMap.put(PRC_LIST_EQUIP_PRC_AMT, rs.getObject(PRC_LIST_EQUIP_PRC_AMT));
        // END 2018/02/07 U.Kim [QC#17647, ADD]
        // START 2019/09/12 K.Fujimoto [QC#53380,ADD]
        resultMap.put(RTL_WH_CATG_CD, rs.getObject(RTL_WH_CATG_CD));
        // END   2019/09/12 K.Fujimoto [QC#53380,ADD]
        return resultMap;
    }

    /**
     * checkChangeTechnician
     * @param currentMap Map<String, Object>
     * @param preMap Map<String, Object>
     * @return boolean
     */
    private boolean checkChangeTechnician(Map<String, Object> currentMap, Map<String, Object> preMap) {
        if (preMap == null) {
            return false;
        }
        String techTocCd = (String) currentMap.get(TECH_TOC_CD);
        String preTechTocCd = (String) preMap.get(TECH_TOC_CD);
        if (techTocCd.equals(preTechTocCd)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * checkChangeInventory
     * @param currentMap Map<String, Object>
     * @param preMap Map<String, Object>
     * @return boolean
     */
    private boolean checkChangeInventory(Map<String, Object> currentMap, Map<String, Object> preMap) {
        if (preMap == null) {
            return false;
        }
        String mdseCd = (String) currentMap.get(MDSE_CD);
        String preMdseCd = (String) preMap.get(MDSE_CD);
        String invtyLocCd = (String) currentMap.get(INVTY_LOC_CD);
        String preInvtyLocCd = (String) preMap.get(INVTY_LOC_CD);
        if (mdseCd.equals(preMdseCd) && invtyLocCd.equals(preInvtyLocCd)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * mandatoryCheck
     * @param msgMap S21ApiMessageMap
     * @param targetItem EZDPItem
     * @param messageId String
     */
    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!ZYPCommonFunc.hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    /**
     * getString
     * @param targetRecord Map<String, Object>
     * @param key Object
     * @return String
     */
    private String getString(Map<String, Object> targetRecord, Object key) {
        return (String) targetRecord.get(key);
    }

    // START 2017/01/23 K.Kojima [QC#16905,ADD]
    /**
     * getBigDecimal
     * @param targetRecord Map<String, Object>
     * @param key Object
     * @return BigDecimal
     */
    private BigDecimal getBigDecimal(Map<String, Object> targetRecord, Object key) {
        return (BigDecimal) targetRecord.get(key);
    }

    // END 2017/01/23 K.Kojima [QC#16905,ADD]

    /**
     * getInteger
     * @param targetRecord Map<String, Object>
     * @param key Object
     * @return Integer
     */
    private Integer getInteger(Map<String, Object> targetRecord, Object key) {
        BigDecimal decimal = (BigDecimal) targetRecord.get(key);
        if (decimal == null) {
            return 0;
        }
        return decimal.intValue();
    }

    /**
     * setRtrnFlg
     * @param rtrnCTrlFlg String
     * @return boolean
     */
    private boolean setRtrnFlg(String rtrnCTrlFlg) {
        boolean rtrnFlg = false;
        if (ZYPConstant.FLG_ON_Y.equals(rtrnCTrlFlg)) {
            rtrnFlg = true;
        }
        return rtrnFlg;
    }

    // START 2017/01/23 K.Kojima [QC#16905,ADD]
    /**
     * insertUpdateWork
     * @param insertList List<TECH_INVTY_AGING_WRKTMsg>
     * @param updateList List<TECH_INVTY_AGING_WRKTMsg>
     */
//    private void insertUpdateWork(List<TECH_INVTY_AGING_WRKTMsg> insertList, List<TECH_INVTY_AGING_WRKTMsg> updateList) {
    private void insertUpdateWork(List<TECH_INVTY_AGING_WRKTMsg> insertListPrev, List<TECH_INVTY_AGING_WRKTMsg> updateListPrev) {
        // QC#55791 Add Start
        List<TECH_INVTY_AGING_WRKTMsg> insertList = new ArrayList<TECH_INVTY_AGING_WRKTMsg>();
        List<TECH_INVTY_AGING_WRKTMsg> updateList = new ArrayList<TECH_INVTY_AGING_WRKTMsg>();

        for (int i=0; i<insertListPrev.size(); i++) {
            TECH_INVTY_AGING_WRKTMsg tMsg = insertListPrev.get(i);
            ZYPEZDItemValueSetter.setValue(tMsg.sendClickLastProcTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
            // Confirm API Exec Status
            if (PROC_STS.COMPLEATED.equals(this.sendClickProcStsCd)) {
                ZYPEZDItemValueSetter.setValue(tMsg.sendClickProcStsCd, PROC_STS.COMPLEATED);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.sendClickProcStsCd, PROC_STS.ERROR);
                
            }
            ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_OFF_N);
            insertList.add(tMsg);
        }
        for (int i=0; i<updateListPrev.size(); i++) {
            TECH_INVTY_AGING_WRKTMsg tMsg = updateListPrev.get(i);
            ZYPEZDItemValueSetter.setValue(tMsg.sendClickLastProcTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
            // Confirm API Exec Status
            if (PROC_STS.COMPLEATED.equals(this.sendClickProcStsCd)) {
                ZYPEZDItemValueSetter.setValue(tMsg.sendClickProcStsCd, PROC_STS.COMPLEATED);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.sendClickProcStsCd, PROC_STS.ERROR);
                
            }
            ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_OFF_N);
            updateList.add(tMsg);
        }
        // QC#55791 Add End

        if (insertList.size() > 0) {
            S21FastTBLAccessor.insert(insertList.toArray(new TECH_INVTY_AGING_WRKTMsg[insertList.size()]));
        }
        if (updateList.size() > 0) {
            S21FastTBLAccessor.update(updateList.toArray(new TECH_INVTY_AGING_WRKTMsg[updateList.size()]));
        }
    }
    // END 2017/01/23 K.Kojima [QC#16905,ADD]

    // START 2017/07/31 K.Kojima [QC#20212,ADD]
    private String getTechTocCdRegexpLineString(String nightProcessMode) {
        if (nightProcessMode.equals(NLZC410001Constant.NIGHTY_SPLIT_MODE_OTHER)) {
            return null;
        } else {
            return "%" + nightProcessMode;
        }
    }
    // END 2017/07/31 K.Kojima [QC#20212,ADD]

    // START 2018/02/07 U.Kim [QC#17647, ADD]
    /**
     * getStringForMaxMinQty
     * @param targetRecord Map<String, Object>
     * @param key Object
     * @return String
     */
    private String getStringForMaxMinQty(Map<String, Object> targetRecord, Object key) {
        BigDecimal decimal = (BigDecimal) targetRecord.get(key);
        if (decimal == null) {
            return null;
        }
        return decimal.toString();
    }
    // END 2018/02/07 U.Kim [QC#17647, ADD]

    // START 2020/02/13 K.Kitachi [QC#55717, ADD]
    private void ownerChangeProcess(S21ApiMessageMap msgMap, List<Map<String, Object>> ownerChangeList) throws SQLException {
        NLZC410001PMsg pMsg = (NLZC410001PMsg) msgMap.getPmsg();
        StandardOperations standardOperationsForDelete = objFactory.createStandardOperations();
        List<CSAMyInventory> myInventoryListForDelete = new ArrayList<CSAMyInventory>();
        List<Map<String, Object>> inventoryDeleteList;
        for (Map<String, Object> ownerChange : ownerChangeList) {
            inventoryDeleteList = getInventoryDeleteInfo(pMsg.glblCmpyCd.getValue(), (String) ownerChange.get(RTL_WH_CD), (String) ownerChange.get(TECH_TOC_CD_BEF));
            for (Map<String, Object> inventoryDelete : inventoryDeleteList) {
                setSendDataForDelete(myInventoryListForDelete, inventoryDelete);
            }
        }
        if (myInventoryListForDelete.size() > 0) {
            setStandardOperationsForDelete(standardOperationsForDelete, myInventoryListForDelete, 0);
            callWmbApi(msgMap, standardOperationsForDelete);
            // QC#55791 Add Start
            // if WmbApi Error, Regist result of Delete information
            if (PROC_STS.ERROR.equals(this.sendClickProcStsCd)) {
                registErrorInfo(ownerChangeList, pMsg);
            } 
            // QC#55791 Add End 
        }
        doProcess(msgMap, ownerChangeList);
    }

    private List<Map<String, Object>> getOwnerChangeInfo(String glblCmpyCd, String lastUpdTs) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("lastUpdTs", lastUpdTs);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getOwnerChangeInfo", param);
    }

    private List<Map<String, Object>> getInventoryDeleteInfo(String glblCmpyCd, String rtlWhCd, String techTocCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rtlWhCd", rtlWhCd);
        param.put("techTocCd", techTocCd);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getInventoryDeleteInfo", param);
    }

    private PreparedStatement getSendDataForOwnerChange(NLZC410001PMsg pMsg, String maxSnapShotDt, List<Map<String, Object>> ownerChangeList) throws SQLException {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("procDt", pMsg.procDt.getValue());
        param.put("maxSnapShotDt", maxSnapShotDt);
        List<String> invtyLocCdList = new ArrayList<String>();
        for (Map<String, Object> ownerChange : ownerChangeList) {
            if (!invtyLocCdList.contains((String) ownerChange.get(INVTY_LOC_CD))) {
                invtyLocCdList.add((String) ownerChange.get(INVTY_LOC_CD));
            }
        }
        param.put("invtyLocCdList", invtyLocCdList);
        param.put("locStsCd", LOC_STS.DC_STOCK);
        param.put("locTpCd", LOC_TP.TECHNICIAN);
        param.put("dateFormat", ZYPDateUtil.TYPE_YYYYMMDD);
        param.put("prcListTpCd", PRC_LIST_TP.PARTS);
        param.put("onFlg", ZYPConstant.FLG_ON_Y);
        param.put("offFlg", ZYPConstant.FLG_OFF_N);
        param.put("prcQlfyTpCd", PRC_QLFY_TP.ITEM_CODE);
        return this.ssmLowClient.createPreparedStatement("getSendData", param, execParam);
    }
    // END 2020/02/13 K.Kitachi [QC#55717, ADD]

    // QC#55791 Add Start
    /**
     * updateErrorInfo
     * @param inventoryDeleteList
     * @param pMsg
     */
    private void updateErrorInfo(List<Map<String, Object>> inventoryDeleteList, NLZC410001PMsg pMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        for (Map<String, Object> inventoryDelete : inventoryDeleteList) {
            TECH_INVTY_AGING_WRKTMsg tMsg = getTechInvtyAgingWrk(glblCmpyCd, (BigDecimal) inventoryDelete.get(TECH_INVTY_AGING_WRK_PK));
            if (ZYPCommonFunc.hasValue(tMsg.techInvtyAgingWrkPk)) {
                ZYPEZDItemValueSetter.setValue(tMsg.sendClickLastProcTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
                if (PROC_STS.COMPLEATED.equals(sendClickProcStsCd)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.sendClickProcStsCd, PROC_STS.COMPLEATED);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.sendClickProcStsCd, PROC_STS.ERROR);
                }
                S21FastTBLAccessor.update(tMsg);
            }
        }
    }
    /**
     * registErrorInfo
     * @param ownerChangeList
     * @param pMsg
     */
    private void registErrorInfo(List<Map<String, Object>> ownerChangeList, NLZC410001PMsg pMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String procDt = pMsg.procDt.getValue();
        List<TECH_INVTY_AGING_WRKTMsg> insertList = new ArrayList<TECH_INVTY_AGING_WRKTMsg>();
        List<Map<String, Object>> inventoryDeleteList;
    
        for (Map<String, Object> ownerChange : ownerChangeList) {
            inventoryDeleteList = getInventoryDeleteInfo(glblCmpyCd, (String) ownerChange.get(RTL_WH_CD), (String) ownerChange.get(TECH_TOC_CD_BEF));

            for (Map<String, Object> inventoryDelete : inventoryDeleteList) {
                TECH_INVTY_AGING_WRKTMsg tMsg = new TECH_INVTY_AGING_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.techInvtyAgingWrkPk, (BigDecimal) ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TECH_INVTY_AGING_WRK_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.techInvtyAgingWrkDt, procDt);
                ZYPEZDItemValueSetter.setValue(tMsg.techInvtyAgingWrkNum, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, (String) inventoryDelete.get(RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, (String) inventoryDelete.get(RTL_SWH_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.techTocCd, (String) inventoryDelete.get(TECH_TOC_CD_BEF));
                ZYPEZDItemValueSetter.setValue(tMsg.invtyLocCd, (String) ownerChange.get(INVTY_LOC_CD));

                String partSerialNo = getString(inventoryDelete, SER_NUM);
                Integer quantity = getInteger(inventoryDelete, INVTY_QTY);
                Integer snapQuantity = getInteger(inventoryDelete, SNAP_INVTY_QTY);
                if (ZYPCommonFunc.hasValue(partSerialNo)) {
                    quantity = snapQuantity;
                }
                Integer age029 = getInteger(inventoryDelete,  FIRST_MTH_INVTY_QTY);
                Integer age3059 = getInteger(inventoryDelete,  SCD_MTH_INVTY_QTY);
                Integer age6089 = getInteger(inventoryDelete, THIRD_MTH_INVTY_QTY);
                Integer age90 = getInteger(inventoryDelete, OVER_FRTH_MTH_INVTY_QTY);

                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, getString(inventoryDelete, MDSE_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.serNum, partSerialNo);
                // START 2022/10/19 [QC#60712, MOD]
//                ZYPEZDItemValueSetter.setValue(tMsg.mdseNm, getString(inventoryDelete, MDSE_NM));
                ZYPEZDItemValueSetter.setValue(tMsg.mdseNm, ofsMultiByteConvert(getString(inventoryDelete, MDSE_NM)));
                // END   2022/10/19 [QC#60712, MOD]
                ZYPEZDItemValueSetter.setValue(tMsg.invtyQty, BigDecimal.valueOf(quantity));

                if (setRtrnFlg(getString(inventoryDelete, RTRN_CTRL_FLG))) {
                    ZYPEZDItemValueSetter.setValue(tMsg.rtrnCtrlFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.rtrnCtrlFlg, ZYPConstant.FLG_OFF_N);
                }
                if (setRtrnFlg(getString(inventoryDelete, PRT_SRVY_REQ_FLG ))) {
                    ZYPEZDItemValueSetter.setValue(tMsg.siteSrvyReqFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.siteSrvyReqFlg, ZYPConstant.FLG_OFF_N);
                }

                ZYPEZDItemValueSetter.setValue(tMsg.thisMthTotStdCostAmt, getBigDecimal(inventoryDelete, THIS_MTH_TOT_STD_COST_AMT));
                ZYPEZDItemValueSetter.setValue(tMsg.firstMthInvtyQty, BigDecimal.valueOf(age029));
                ZYPEZDItemValueSetter.setValue(tMsg.scdMthInvtyQty, BigDecimal.valueOf(age3059));
                ZYPEZDItemValueSetter.setValue(tMsg.thirdMthInvtyQty, BigDecimal.valueOf(age6089));
                ZYPEZDItemValueSetter.setValue(tMsg.overFrthMthInvtyQty, BigDecimal.valueOf(age90));
                ZYPEZDItemValueSetter.setValue(tMsg.whMgrPsnCd, getString(inventoryDelete, WH_MGR_PSN_CD_BEF));
                ZYPEZDItemValueSetter.setValue(tMsg.whNm, getString(inventoryDelete, WH_NM));
                ZYPEZDItemValueSetter.setValue(tMsg.maxInvtyQty, getBigDecimal(inventoryDelete, MAX_INVTY_QTY));
                ZYPEZDItemValueSetter.setValue(tMsg.minInvtyQty, getBigDecimal(inventoryDelete, MIN_INVTY_QTY));
                ZYPEZDItemValueSetter.setValue(tMsg.prcListEquipPrcAmt, getBigDecimal(inventoryDelete, PRC_LIST_EQUIP_PRC_AMT));

                ZYPEZDItemValueSetter.setValue(tMsg.sendClickLastProcTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
                ZYPEZDItemValueSetter.setValue(tMsg.sendClickProcStsCd, PROC_STS.ERROR);
                ZYPEZDItemValueSetter.setValue(tMsg.delFlg, ZYPConstant.FLG_ON_Y);
                S21FastTBLAccessor.insert(tMsg);

            }
        }
    }

    /**
     * getInventoryInfoForDeleteError
     */
    private List<Map<String, Object>> getInventoryInfoForDeleteError(String glblCmpyCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("error", PROC_STS.ERROR);
        param.put("flgY", ZYPConstant.FLG_ON_Y);
        // QC#56895 Add Start
        param.put("intvlMins", this.intvlMins);
        // QC#56895 Add End
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getInventoryInfoForDeleteError", param);
    }
    /**
     * getInventoryInfoForReProcDaily
     * @param glblCmpyCd
     * @return
     */
    private List<Map<String, Object>> getInventoryInfoForReProcDaily(String glblCmpyCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("error", PROC_STS.ERROR);
        param.put("completed", PROC_STS.COMPLEATED);
        param.put("flgN", ZYPConstant.FLG_OFF_N);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getInventoryInfoForReProcDaily", param);
    }
    /**
     * getInventoryInfoForReProcNightly
     * @param glblCmpyCd
     * @param pMsg
     * @return
     */
    private List<Map<String, Object>> getInventoryInfoForReProcNightly(String glblCmpyCd, NLZC410001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("techTocCdRegexpLineString", getTechTocCdRegexpLineString(pMsg.xxModeCd_NT.getValue()));
        // QC#55791-1 Mod Start
        param.put("xxUpdTs", pMsg.xxUpdTs.getValue());
        //param.put("procDt", pMsg.procDt.getValue());
        // QC#55791-1 Mod End
        param.put("completed", PROC_STS.COMPLEATED);
        param.put("flgN", ZYPConstant.FLG_OFF_N);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getInventoryInfoForReProcNightly", param);
    }

    // QC#57801 DEL&ADD Start
    /**
     * reProcForDeleteError
     * @param msgMap
     * @throws SQLException
     */
//    private void reProcForDeleteError(S21ApiMessageMap msgMap) throws SQLException {
//        NLZC410001PMsg pMsg = (NLZC410001PMsg) msgMap.getPmsg();
//        StandardOperations standardOperations = objFactory.createStandardOperations();
//        List<CSAMyInventory> myInventoryList = new ArrayList<CSAMyInventory>();
//        List<Map<String, Object>> inventoryList;
//
//        inventoryList = getInventoryInfoForDeleteError(pMsg.glblCmpyCd.getValue());
//
//        for (Map<String, Object> inventoryInfo : inventoryList) {
//            setSendDataForDelete(myInventoryList, inventoryInfo);
//        }
//        if (myInventoryList.size() > 0) {
//            setStandardOperationsForDelete(standardOperations, myInventoryList, 0);
//            callWmbApi(msgMap, standardOperations);
//            updateErrorInfo(inventoryList, pMsg);
//        }
//    }
    private void reProcForDeleteError(S21ApiMessageMap msgMap) throws SQLException {
        NLZC410001PMsg pMsg = (NLZC410001PMsg) msgMap.getPmsg();
        BigDecimal wmbMaxCount = ZYPCodeDataUtil.getNumConstValue(WMB_MAX_LENGTH, pMsg.glblCmpyCd.getValue());
        if (wmbMaxCount == null) {
            wmbMaxCount = BigDecimal.ZERO;
        }
        StandardOperations standardOperations = objFactory.createStandardOperations();
        List<CSAMyInventory> myInventoryList = new ArrayList<CSAMyInventory>();
        List<Map<String, Object>> inventoryList;

        inventoryList = getInventoryInfoForDeleteError(pMsg.glblCmpyCd.getValue());
        if (inventoryList.size() == 0) {
            return;
        }
        List<Map<String, Object>> inventoryListTmp = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> inventoryInfo : inventoryList) {
            inventoryListTmp.add(inventoryInfo);
            if (inventoryListTmp.size() >= wmbMaxCount.intValue()) {
                for (Map<String, Object> inventoryInfoTmp : inventoryListTmp) {
                	setSendDataForDelete(myInventoryList, inventoryInfoTmp);
                }
                if (myInventoryList.size() > 0) {
                	setStandardOperationsForDelete(standardOperations, myInventoryList, 0);
                    callWmbApi(msgMap, standardOperations);
                    updateErrorInfo(inventoryListTmp, pMsg);
                    inventoryListTmp.clear();
                    myInventoryList.clear();
                    standardOperations = objFactory.createStandardOperations();
                }
            }
        }
        if (inventoryListTmp.size() > 0) {
            for (Map<String, Object> inventoryInfoTmp : inventoryListTmp) {
            	setSendDataForDelete(myInventoryList, inventoryInfoTmp);
            }
            if (myInventoryList.size() > 0) {
            	setStandardOperationsForDelete(standardOperations, myInventoryList, 0);
                callWmbApi(msgMap, standardOperations);
                updateErrorInfo(inventoryListTmp, pMsg);
                inventoryListTmp.clear();
                myInventoryList.clear();
                standardOperations = objFactory.createStandardOperations();
            }
        }

    }
    // QC#57801 DEL&ADD End
    // QC#57801 DEL&ADD Start
    /**
     * reProcDaily
     * @param msgMap
     * @throws SQLException
     */
//  private void reProcDaily(S21ApiMessageMap msgMap) throws SQLException {
//  NLZC410001PMsg pMsg = (NLZC410001PMsg) msgMap.getPmsg();
//  StandardOperations standardOperations = objFactory.createStandardOperations();
//  List<CSAMyInventory> myInventoryList = new ArrayList<CSAMyInventory>();
//  List<Map<String, Object>> inventoryList;
//
//  inventoryList = getInventoryInfoForReProcDaily(pMsg.glblCmpyCd.getValue());
//
//  for (Map<String, Object> inventoryInfo : inventoryList) {
//      setSendDataForReProc(myInventoryList, pMsg, inventoryInfo);
//
//  }
//  if (myInventoryList.size() > 0) {
//      setStandardOperations(standardOperations, myInventoryList, 0);
//      callWmbApi(msgMap, standardOperations);
//      updateErrorInfo(inventoryList, pMsg);
//  }
//}
    private void reProcDaily(S21ApiMessageMap msgMap) throws SQLException {
        NLZC410001PMsg pMsg = (NLZC410001PMsg) msgMap.getPmsg();
        BigDecimal wmbMaxCount = ZYPCodeDataUtil.getNumConstValue(WMB_MAX_LENGTH, pMsg.glblCmpyCd.getValue());
        if (wmbMaxCount == null) {
            wmbMaxCount = BigDecimal.ZERO;
        }
        StandardOperations standardOperations = objFactory.createStandardOperations();
        List<CSAMyInventory> myInventoryList = new ArrayList<CSAMyInventory>();
        List<Map<String, Object>> inventoryList;

        inventoryList = getInventoryInfoForReProcDaily(pMsg.glblCmpyCd.getValue());

        if (inventoryList.size() == 0) {
            return;
        }
        List<Map<String, Object>> inventoryListTmp = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> inventoryInfo : inventoryList) {
            inventoryListTmp.add(inventoryInfo);
            if (inventoryListTmp.size() >= wmbMaxCount.intValue()) {
                for (Map<String, Object> inventoryInfoTmp : inventoryListTmp) {
                    setSendDataForReProc(myInventoryList, pMsg, inventoryInfoTmp);
                }
                if (myInventoryList.size() > 0) {
                    setStandardOperations(standardOperations, myInventoryList, 0);
                    callWmbApi(msgMap, standardOperations);
                    updateErrorInfo(inventoryListTmp, pMsg);
                    inventoryListTmp.clear();
                    myInventoryList.clear();
                    standardOperations = objFactory.createStandardOperations();
                }
            }
        }
        if (inventoryListTmp.size() > 0) {
            for (Map<String, Object> inventoryInfoTmp : inventoryListTmp) {
                setSendDataForReProc(myInventoryList, pMsg, inventoryInfoTmp);
            }
            if (myInventoryList.size() > 0) {
                setStandardOperations(standardOperations, myInventoryList, 0);
                callWmbApi(msgMap, standardOperations);
                updateErrorInfo(inventoryListTmp, pMsg);
                inventoryListTmp.clear();
                standardOperations = objFactory.createStandardOperations();
            }
        }

    }

    // QC#57801 DEL&ADD Start
    /**
     * reProcNightly
     * @param msgMap
     * @throws SQLException
     */
//    private void reProcNightly(S21ApiMessageMap msgMap) throws SQLException {
//        NLZC410001PMsg pMsg = (NLZC410001PMsg) msgMap.getPmsg();
//        StandardOperations standardOperations = objFactory.createStandardOperations();
//        List<CSAMyInventory> myInventoryList = new ArrayList<CSAMyInventory>();
//        List<Map<String, Object>> inventoryList;
//
//        inventoryList = getInventoryInfoForReProcNightly(pMsg.glblCmpyCd.getValue(), pMsg);
//
//        for (Map<String, Object> inventoryInfo : inventoryList) {
//            setSendDataForReProc(myInventoryList, pMsg, inventoryInfo);
//        }
//        if (myInventoryList.size() > 0) {
//            setStandardOperations(standardOperations, myInventoryList, 0);
//            callWmbApi(msgMap, standardOperations);
//            updateErrorInfo(inventoryList, pMsg);
//        }
//    }
    private void reProcNightly(S21ApiMessageMap msgMap) throws SQLException {

        NLZC410001PMsg pMsg = (NLZC410001PMsg) msgMap.getPmsg();
        
        BigDecimal wmbMaxCount = ZYPCodeDataUtil.getNumConstValue(WMB_MAX_LENGTH, pMsg.glblCmpyCd.getValue());
        if (wmbMaxCount == null) {
            wmbMaxCount = BigDecimal.ZERO;
        }

        StandardOperations standardOperations = objFactory.createStandardOperations();
        List<CSAMyInventory> myInventoryList = new ArrayList<CSAMyInventory>();
        List<Map<String, Object>> inventoryList;

        inventoryList = getInventoryInfoForReProcNightly(pMsg.glblCmpyCd.getValue(), pMsg);
        if (inventoryList.size() == 0) {
            return;
        }

        List<Map<String, Object>> inventoryListTmp = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> inventoryInfo : inventoryList) {
            inventoryListTmp.add(inventoryInfo);
            if (inventoryListTmp.size() >= wmbMaxCount.intValue()) {
                for (Map<String, Object> inventoryInfoTmp : inventoryListTmp) {
                    setSendDataForReProc(myInventoryList, pMsg, inventoryInfoTmp);
                }
                if (myInventoryList.size() > 0) {
                    setStandardOperations(standardOperations, myInventoryList, 0);
                    callWmbApi(msgMap, standardOperations);
                    updateErrorInfo(inventoryListTmp, pMsg);
                    inventoryListTmp.clear();
                    myInventoryList.clear();
                    standardOperations = objFactory.createStandardOperations();
                }
            }
        }
        if (inventoryListTmp.size() > 0) {
            for (Map<String, Object> inventoryInfoTmp : inventoryListTmp) {
                setSendDataForReProc(myInventoryList, pMsg, inventoryInfoTmp);
            }
            if (myInventoryList.size() > 0) {
                setStandardOperations(standardOperations, myInventoryList, 0);
                callWmbApi(msgMap, standardOperations);
                updateErrorInfo(inventoryListTmp, pMsg);
                inventoryListTmp.clear();
                myInventoryList.clear();
                standardOperations = objFactory.createStandardOperations();
            }
        }
    }
    // QC#57801 DEL&ADD End

    /**
     * setSendDataForReProc
     * @param myInventoryList
     * @param pMsg
     * @param targetMap
     */
    private void setSendDataForReProc(
        List<CSAMyInventory> myInventoryList, 
        NLZC410001PMsg pMsg, 
        Map<String, Object> targetMap) {

        CSAMyInventory myInventory = objFactory.createCSAMyInventory();
        myInventory.setTechnicianID(getString(targetMap, TECH_TOC_CD));
        myInventory.setPartNo(getString(targetMap, MDSE_CD));

        CSAInventoryTypeReference myInventoryReference = objFactory.createCSAInventoryTypeReference();
        myInventoryReference.getContent().add(getString(targetMap, RTL_SWH_CD));
        myInventory.setInventoryType(myInventoryReference);

        String partSerialNo = getString(targetMap, SER_NUM);
        Integer quantity = getInteger(targetMap, INVTY_QTY);
        Integer snapQuantity = getInteger(targetMap, SNAP_INVTY_QTY);
        if (ZYPCommonFunc.hasValue(partSerialNo)) {
            quantity = snapQuantity;
        }
        Integer age029 = getInteger(targetMap, FIRST_MTH_INVTY_QTY);
        Integer age3059 = getInteger(targetMap, SCD_MTH_INVTY_QTY);
        Integer age6089 = getInteger(targetMap, THIRD_MTH_INVTY_QTY);
        Integer age90 = getInteger(targetMap, OVER_FRTH_MTH_INVTY_QTY);

        myInventory.setPartSerialNo(partSerialNo);
        myInventory.setDescription(getString(targetMap, MDSE_NM));
        myInventory.setQuantity(quantity);
        myInventory.setReturnControlled(setRtrnFlg(getString(targetMap, RTRN_CTRL_FLG)));
        myInventory.setPrice(getBigDecimal(targetMap, PRC_LIST_EQUIP_PRC_AMT).floatValue());
        myInventory.setMAXQTY(getStringForMaxMinQty(targetMap, MAX_INVTY_QTY));
        myInventory.setMINQTY(getStringForMaxMinQty(targetMap, MIN_INVTY_QTY));
        myInventory.setUnitCost(getBigDecimal(targetMap, THIS_MTH_TOT_STD_COST_AMT).floatValue());
        myInventory.setAge029(age029);
        myInventory.setAge3059(age3059);
        myInventory.setAge6089(age6089);
        myInventory.setAge90(age90);
        myInventory.setEngineerStampID(getString(targetMap, WH_MGR_PSN_CD));
        myInventory.setWHCode(getString(targetMap, RTL_WH_CD));
        myInventory.setWarehouse(getString(targetMap, WH_NM));
        myInventory.setSurveyRequired(setRtrnFlg(getString(targetMap, PRT_SRVY_REQ_FLG)));
        String rtlWhCatgCd = getString(targetMap, RTL_WH_CATG_CD);
        boolean csaOnSite = false;
        if (rtlWhCatgCd.equals(RTL_WH_CATG.CUSTOMER)) {
            csaOnSite = true;
        }
        myInventory.setCSAOnSite(csaOnSite);
        myInventoryList.add(myInventory);
    }
    /**
     * getTechInvtyAgingWrk
     * @param glblCmpyCd
     * @param techInvtyAgingWrkPk
     * @return
     */
    private TECH_INVTY_AGING_WRKTMsg getTechInvtyAgingWrk(String glblCmpyCd, BigDecimal techInvtyAgingWrkPk) {
        TECH_INVTY_AGING_WRKTMsg inMsg = new TECH_INVTY_AGING_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.techInvtyAgingWrkPk, techInvtyAgingWrkPk);
        TECH_INVTY_AGING_WRKTMsg outMsg = (TECH_INVTY_AGING_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
        return outMsg;
    }
    // QC#55791 Add End

    // QC#56895 Add Start
    /**
     * getInvtyLocCdList
     */
    private List<Map<String, Object>> getInvtyLocCdList(String glblCmpyCd, String rtlWhCd, String techTocCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rtlWhCd", rtlWhCd);
        param.put("techTocCd", techTocCd);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getInvtyLocCdList", param);
    }
    // QC#56895 Add End

    // START 2022/10/19 [QC#60712, ADD]
    /** OFS Multi Byte Convert Flag */
    private String ofsMultiByteConvert(String inStr) {
        if (this.ofsMultiByteConvertFlg == false) {
            return inStr;
        }
        String outStr = ZYPCharacterConversionUtil.convertCharacter(inStr);
        return outStr;
    }
    // END   2022/10/19 [QC#60712, ADD]
}
