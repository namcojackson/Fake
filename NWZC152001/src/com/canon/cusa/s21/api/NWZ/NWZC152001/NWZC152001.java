/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NWZ.NWZC152001;

import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.DOCUMENT_TYPE;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.DOCUMENT_TYPE_RTN;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.EVENT_ID_ORD_CRAT;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.EVENT_ID_ORD_DEL;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.EVENT_ID_ORD_MOD;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.MODE_DEL;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.MODE_MOD;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.MODE_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.NWZM0054E;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.NWZM0188E;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.NWZM0189E;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.NWZM0194E;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.NWZM0209E;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.NWZM0912E;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.NWZM1337E;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.NWZM1338E;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.NWZM1339E;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.NWZM1344E;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.NWZM1345E;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.NWZM1387E;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.NWZM1388E;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.NWZM1389E;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.NWZM1390E;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.NWZM1391E;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.NWZM1392E;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.NWZM1759E;
import static com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant.PROCESS_ID;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDSeqTable;
import parts.dbcommon.EZDTBLAccessor;
import business.db.BIZ_PROC_LOGTMsg;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.DS_CPO_SLS_CRTMsg;
import business.db.DS_CPO_SLS_CR_RECTMsg;
import business.db.TOCTMsg;
import business.parts.NWZC152001PMsg;
import business.parts.NWZC152001_salesRepListPMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SUB_SYS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 * <pre>
 * CPO Sales Credit Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/02   Fujitsu         C.Yokoi         Create          N/A
 * 2015/11/30   Fujitsu         S.Takami        Update          S21_NA#1005
 * 2015/12/21   Fujitsu         S.Takami        Update          S21_NA#2189
 * 2016/02/25   Fujitsu         S.Ohki          Update          S21_NA#3328
 * 2016/04/12   Fujitsu         S.Takami        Update          S21_NA#6834
 * 2016/04/27   Fujitsu         S.Takami        Update          S21_NA#5605
 *</pre>
 */
public class NWZC152001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /**
     * constructor
     */
    public NWZC152001() {
        super();
    }

    /**
     * execute
     * @param pMsg NWZC152001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC152001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        this.msgMap = new S21ApiMessageMap(pMsg);
        doProcess(msgMap);
        msgMap.flush();
    }

    /**
     * execute
     * @param params List<NWZC152001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWZC152001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NWZC152001PMsg param : params) {
            execute(param, onBatchType);
        }
    }

    /**
     * doProcess
     * @param param NWZC152001PMsg
     */
    private void doProcess(S21ApiMessageMap msgMap) {
        NWZC152001PMsg pMsg = (NWZC152001PMsg) msgMap.getPmsg();
        if (!checkInput(pMsg)) {
            return;
        }

        List<DS_CPO_SLS_CRTMsg> insTMsgList = new ArrayList<DS_CPO_SLS_CRTMsg>();
        List<DS_CPO_SLS_CRTMsg> updTMsgList = new ArrayList<DS_CPO_SLS_CRTMsg>();
        List<DS_CPO_SLS_CRTMsg> rmvTMsgList = new ArrayList<DS_CPO_SLS_CRTMsg>();
        List<DS_CPO_SLS_CR_RECTMsg> dsCpoSlsCrRecTMsgList = new ArrayList<DS_CPO_SLS_CR_RECTMsg>();

        // CPO Delivery Information List Check
        NWZC152001_salesRepListPMsg salesRepList = null;
        for (int index = 0; index < pMsg.salesRepList.getValidCount(); index++) {

            salesRepList = pMsg.salesRepList.no(index);

            // Add Insert
            if (MODE_NEW.equals(salesRepList.xxRqstTpCd_A.getValue())) {
                if (!checkDsCpoConfigPk(pMsg, salesRepList)) {
                    msgMap.addXxMsgId(NWZM1389E);
                    return;
                }
                if (!checkSlsRepCd(pMsg, salesRepList)) {
                    msgMap.addXxMsgId(NWZM0054E);
                    return;
                }
                addInsCpoSlsCrTMsg(pMsg, salesRepList, insTMsgList, dsCpoSlsCrRecTMsgList);

                // Add Modify
            } else if (MODE_MOD.equals(salesRepList.xxRqstTpCd_A.getValue())) {
                if (!checkDsCpoConfigPk(pMsg, salesRepList)) {
                    msgMap.addXxMsgId(NWZM1389E);
                    return;
                }
                if (!checkSlsRepCd(pMsg, salesRepList)) {
                    msgMap.addXxMsgId(NWZM0054E);
                    return;
                }
                if (!addUpdCpoSlsCrTMsg(pMsg, salesRepList, updTMsgList, dsCpoSlsCrRecTMsgList)) {
                    msgMap.addXxMsgId(NWZM1391E);
                    return;
                }

                // Add Delete
            } else {
                if (!addRmvCpoSlsCrTMsg(pMsg, salesRepList, rmvTMsgList, dsCpoSlsCrRecTMsgList)) {
                    msgMap.addXxMsgId(NWZM1392E);
                    return;
                }
            }
        }

        // insert (DS_CPO_SLS_CR)
        if (!insTMsgList.isEmpty()) {
            int insCnt = S21FastTBLAccessor.insert(insTMsgList.toArray(new DS_CPO_SLS_CRTMsg[0]));
            if (insCnt != insTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1390E);
                return;
            }
        }

        // update (DS_CPO_SLS_CR)
        if (!updTMsgList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updTMsgList.toArray(new DS_CPO_SLS_CRTMsg[0]));
            if (updCnt != updTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1391E);
                return;
            }
        }

        // delete (DS_CPO_SLS_CR)
        if (!rmvTMsgList.isEmpty()) {
            int rmvCnt = S21FastTBLAccessor.removeLogical(rmvTMsgList.toArray(new DS_CPO_SLS_CRTMsg[0]));
            if (rmvCnt != rmvTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1392E);
                return;
            }
        }

        // insert (DS_CPO_SLS_CR_REC)
        if (!dsCpoSlsCrRecTMsgList.isEmpty()) {
            int insCnt = S21FastTBLAccessor.insert(dsCpoSlsCrRecTMsgList.toArray(new DS_CPO_SLS_CR_RECTMsg[0]));
            if (insCnt != dsCpoSlsCrRecTMsgList.size()) {
                msgMap.addXxMsgId(NWZM1759E);
                return;
            }
        }
    }

    /**
     * checkInput
     * @param param NWZC152001PMsg
     * @return boolean
     */
    private boolean checkInput(NWZC152001PMsg pMsg) {
        // Global Company Code
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0188E);
        }

        Map<String, BigDecimal> pctTotalPerConfig = new HashMap<String, BigDecimal>();// 2015/11/26 S21_NA#1005 Add

        boolean isQuote = false;
        // BigDecimal totalCrPct = BigDecimal.ZERO; 2015/11/26 S21_NA#1005 Del

        // Salesrep List Check
        NWZC152001_salesRepListPMsg salesRepList = null;
        for (int index = 0; index < pMsg.salesRepList.getValidCount(); index++) {

            salesRepList = pMsg.salesRepList.no(index);

            if (!ZYPCommonFunc.hasValue(salesRepList.xxRqstTpCd_A)) {
                msgMap.addXxMsgId(NWZM0189E);
                continue;
            }

            if (MODE_NEW.equals(salesRepList.xxRqstTpCd_A.getValue()) || MODE_MOD.equals(salesRepList.xxRqstTpCd_A.getValue())) {
                // if (!ZYPCommonFunc.hasValue(salesRepList.dsCpoConfigPk_A)) { 2015/11/26 S21_NA#1005 Update IF statement.
                if (ZYPCommonFunc.hasValue(salesRepList.dsOrdPosnNum_A) && !ZYPCommonFunc.hasValue(salesRepList.dsCpoConfigPk_A)) {
                    msgMap.addXxMsgId(NWZM1337E);
                }
                if (!ZYPCommonFunc.hasValue(salesRepList.slsRepCd_A)) {
                    msgMap.addXxMsgId(NWZM0194E);
                }
                if (!ZYPCommonFunc.hasValue(salesRepList.slsRepRoleTpCd_A)) {
                    msgMap.addXxMsgId(NWZM1338E);
                }
                if (!ZYPCommonFunc.hasValue(salesRepList.slsRepCrPct_A)) {
                    msgMap.addXxMsgId(NWZM1344E);
                }
                if (!ZYPCommonFunc.hasValue(salesRepList.slsCrQuotFlg_A)) {
                    msgMap.addXxMsgId(NWZM1345E);
                }

                if (ZYPConstant.FLG_ON_Y.equals(salesRepList.slsCrQuotFlg_A.getValue())) {
                    isQuote = true;
                    if (ZYPCommonFunc.hasValue(salesRepList.slsRepCrPct_A)) {
                        if (BigDecimal.ZERO.compareTo(salesRepList.slsRepCrPct_A.getValue()) == 0) {
                            msgMap.addXxMsgId(NWZM1388E);
                        } else {
                            // 2015/11/26 S21_NA#1005 Update IF statement. Update Start
                            // totalCrPct = totalCrPct.add(salesRepList.slsRepCrPct_A.getValue());
                            String totalKey = "Hdr";
                            if (ZYPCommonFunc.hasValue(salesRepList.dsOrdPosnNum_A)) {
                                totalKey = salesRepList.dsOrdPosnNum_A.getValue();
                                // 2015/12/21 S21_NA#2189 Add Start
                                totalKey = totalKey + ":" + String.valueOf(salesRepList.dsCpoConfigPk_A.getValue());
                                // 2015/12/21 S21_NA#2189 Add End
                            }
                            BigDecimal linePct = pctTotalPerConfig.get(totalKey);
                            if (null == linePct) {
                                linePct = salesRepList.slsRepCrPct_A.getValue();
                            } else {
                                linePct = linePct.add(salesRepList.slsRepCrPct_A.getValue());
                            }
                            pctTotalPerConfig.put(totalKey, linePct);
                            // 2015/11/26 S21_NA#1005 Update IF statement. Update End
                        }
                    }
                }
                if (ZYPConstant.FLG_OFF_N.equals(salesRepList.slsCrQuotFlg_A.getValue())) {
                    salesRepList.slsRepCrPct_A.setValue(BigDecimal.ZERO);
                }

                if (MODE_NEW.equals(salesRepList.xxRqstTpCd_A.getValue()) && !ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
                    msgMap.addXxMsgId(NWZM0912E);
                } else if (MODE_MOD.equals(salesRepList.xxRqstTpCd_A.getValue()) && !ZYPCommonFunc.hasValue(salesRepList.dsCpoSlsCrPk_A)) {
                    msgMap.addXxMsgId(NWZM1339E);
                }

            } else if (MODE_DEL.equals(salesRepList.xxRqstTpCd_A.getValue())) {
                if (!ZYPCommonFunc.hasValue(salesRepList.dsCpoSlsCrPk_A)) {
                    msgMap.addXxMsgId(NWZM1339E);
                }
            } else {
                msgMap.addXxMsgId(NWZM0209E);
            }
        }

        // 2015/11/26 S21_NA#1005 Update IF statement. Update Start
        // if (isQuote && totalCrPct.compareTo(BigDecimal.valueOf(100)) != 0) {
        //     msgMap.addXxMsgId(NWZM1387E);
        // }
        if (isQuote) {
            for (BigDecimal totalCrPct : pctTotalPerConfig.values()) {
                if (totalCrPct.compareTo(BigDecimal.valueOf(100)) != 0) {
                    msgMap.addXxMsgId(NWZM1387E);
                    break;
                }
            }
        }
        // 2015/11/26 S21_NA#1005 Update IF statement. Update End
        return !isError(pMsg);
    }

    /**
     * checkDsCpoConfigPk
     * @param param NWZC152001PMsg
     * @param salesRepList NWZC152001_salesRepListPMsg
     * @return boolean
     */
    private boolean checkDsCpoConfigPk(NWZC152001PMsg pMsg, NWZC152001_salesRepListPMsg salesRepList) {
        // Master Check: DS CPO Configuration PK
        if (ZYPCommonFunc.hasValue(salesRepList.dsCpoConfigPk_A)) {
            DS_CPO_CONFIGTMsg configTMsg = new DS_CPO_CONFIGTMsg();
            ZYPEZDItemValueSetter.setValue(configTMsg.dsCpoConfigPk, salesRepList.dsCpoConfigPk_A);
            ZYPEZDItemValueSetter.setValue(configTMsg.glblCmpyCd, pMsg.glblCmpyCd);

            DS_CPO_CONFIGTMsg tMsgResult = (DS_CPO_CONFIGTMsg) S21CacheTBLAccessor.findByKey(configTMsg);

            // has no result
            if (tMsgResult == null) {
                return false;
            }
            ZYPEZDItemValueSetter.setValue(salesRepList.dsOrdPosnNum_A, tMsgResult.dsOrdPosnNum);
        }
        return true;
    }

    /**
     * checkSlsRepCd
     * @param param NWZC152001PMsg
     * @param salesRepList NWZC152001_salesRepListPMsg
     * @return boolean
     */
    private boolean checkSlsRepCd(NWZC152001PMsg pMsg, NWZC152001_salesRepListPMsg salesRepList) {
        // Master Check: Salesrep(TOC) Code
        if (ZYPCommonFunc.hasValue(salesRepList.slsRepCd_A)) {
            TOCTMsg tocTMsg = new TOCTMsg();
            ZYPEZDItemValueSetter.setValue(tocTMsg.tocCd, salesRepList.slsRepCd_A);
            ZYPEZDItemValueSetter.setValue(tocTMsg.glblCmpyCd, pMsg.glblCmpyCd);

            TOCTMsg tMsgResult = (TOCTMsg) S21CacheTBLAccessor.findByKey(tocTMsg);
            // has no result
            if (tMsgResult == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * addInsCpoSlsCrTMsg
     * @param pMsg NWZC152001PMsg
     * @param salesRepList NWZC152001_salesRepListPMsg
     * @param insTMsgList List<DS_CPO_SLS_CRTMsg>
     */
    private void addInsCpoSlsCrTMsg(NWZC152001PMsg pMsg, NWZC152001_salesRepListPMsg salesRepList, List<DS_CPO_SLS_CRTMsg> insTMsgList, List<DS_CPO_SLS_CR_RECTMsg> dsCpoSlsCrRecTMsgList) {
        DS_CPO_SLS_CRTMsg insTMsg = new DS_CPO_SLS_CRTMsg();

        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(salesRepList.dsCpoSlsCrPk_A, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_SLS_CR_SQ));
        ZYPEZDItemValueSetter.setValue(insTMsg.dsCpoSlsCrPk, salesRepList.dsCpoSlsCrPk_A);
        ZYPEZDItemValueSetter.setValue(insTMsg.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.dsOrdPosnNum, salesRepList.dsOrdPosnNum_A);
        ZYPEZDItemValueSetter.setValue(insTMsg.dsCpoConfigPk, salesRepList.dsCpoConfigPk_A);
        ZYPEZDItemValueSetter.setValue(insTMsg.slsRepTocCd, salesRepList.slsRepCd_A);
        ZYPEZDItemValueSetter.setValue(insTMsg.slsRepRoleTpCd, salesRepList.slsRepRoleTpCd_A);
        ZYPEZDItemValueSetter.setValue(insTMsg.slsRepCrPct, salesRepList.slsRepCrPct_A);
        if (ZYPCommonFunc.hasValue(salesRepList.slsCrQuotFlg_A)) {
            ZYPEZDItemValueSetter.setValue(insTMsg.slsCrQuotFlg, salesRepList.slsCrQuotFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(insTMsg.slsCrQuotFlg, ZYPConstant.FLG_OFF_N);
        }
        insTMsgList.add(insTMsg);

        // Create Bisiness Prcess Log
        // 2016/02/25 S21_NA#3328 Mod Start
        int bizProcLogPk = createBizProcLog(EVENT_ID_ORD_CRAT, insTMsg.dsOrdPosnNum.getValue(), insTMsg.cpoOrdNum.getValue(), salesRepList.configCatgCd_A.getValue());
        // 2016/02/25 S21_NA#3328 Mod End

        // Set Rec Data
        DS_CPO_SLS_CR_RECTMsg insRecTMsg = new DS_CPO_SLS_CR_RECTMsg();
        EZDMsg.copy(insTMsg, null, insRecTMsg, null);
        insRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
        dsCpoSlsCrRecTMsgList.add(insRecTMsg);
    }

    /**
     * addUpdCpoSlsCrTMsg
     * @param pMsg NWZC152001PMsg
     * @param salesRepList NWZC152001_salesRepListPMsg
     * @param updTMsgList List<DS_CPO_SLS_CRTMsg>
     * @return boolean
     */
    private boolean addUpdCpoSlsCrTMsg(NWZC152001PMsg pMsg, NWZC152001_salesRepListPMsg salesRepList, List<DS_CPO_SLS_CRTMsg> updTMsgList, List<DS_CPO_SLS_CR_RECTMsg> dsCpoSlsCrRecTMsgList) {
        DS_CPO_SLS_CRTMsg updTMsgKey = new DS_CPO_SLS_CRTMsg();

        ZYPEZDItemValueSetter.setValue(updTMsgKey.dsCpoSlsCrPk, salesRepList.dsCpoSlsCrPk_A);
        ZYPEZDItemValueSetter.setValue(updTMsgKey.glblCmpyCd, pMsg.glblCmpyCd);

        DS_CPO_SLS_CRTMsg updTMsg = (DS_CPO_SLS_CRTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(updTMsgKey);
        if (updTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(updTMsg.slsRepTocCd, salesRepList.slsRepCd_A);
        ZYPEZDItemValueSetter.setValue(updTMsg.slsRepRoleTpCd, salesRepList.slsRepRoleTpCd_A);
        ZYPEZDItemValueSetter.setValue(updTMsg.slsRepCrPct, salesRepList.slsRepCrPct_A);
        if (ZYPCommonFunc.hasValue(salesRepList.slsCrQuotFlg_A)) {
            ZYPEZDItemValueSetter.setValue(updTMsg.slsCrQuotFlg, salesRepList.slsCrQuotFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(updTMsg.slsCrQuotFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(updTMsg.dsOrdPosnNum, salesRepList.dsOrdPosnNum_A); // 2016/04/27 S21_NA#5605 Add
        updTMsgList.add(updTMsg);

        // Create Bisiness Prcess Log
        // 2016/02/25 S21_NA#3328 Mod Start
        int bizProcLogPk = createBizProcLog(EVENT_ID_ORD_MOD, updTMsg.dsOrdPosnNum.getValue(), updTMsg.cpoOrdNum.getValue(), salesRepList.configCatgCd_A.getValue());
        // 2016/02/25 S21_NA#3328 Mod End

        // Set Rec Data
        DS_CPO_SLS_CR_RECTMsg insRecTMsg = new DS_CPO_SLS_CR_RECTMsg();
        EZDMsg.copy(updTMsg, null, insRecTMsg, null);
        insRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
        dsCpoSlsCrRecTMsgList.add(insRecTMsg);

        return true;
    }

    /**
     * addRmvCpoSlsCrTMsg
     * @param pMsg NWZC152001PMsg
     * @param salesRepList NWZC152001_salesRepListPMsg
     * @param rmvTMsgList List<DS_CPO_SLS_CRTMsg>
     * @return boolean
     */
    private boolean addRmvCpoSlsCrTMsg(NWZC152001PMsg pMsg, NWZC152001_salesRepListPMsg salesRepList, List<DS_CPO_SLS_CRTMsg> rmvTMsgList, List<DS_CPO_SLS_CR_RECTMsg> dsCpoSlsCrRecTMsgList) {
        DS_CPO_SLS_CRTMsg rmvTMsgKey = new DS_CPO_SLS_CRTMsg();

        ZYPEZDItemValueSetter.setValue(rmvTMsgKey.dsCpoSlsCrPk, salesRepList.dsCpoSlsCrPk_A);
        ZYPEZDItemValueSetter.setValue(rmvTMsgKey.glblCmpyCd, pMsg.glblCmpyCd);

        DS_CPO_SLS_CRTMsg rmvTMsg = (DS_CPO_SLS_CRTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(rmvTMsgKey);
        if (rmvTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(rmvTMsg.getReturnCode())) {
            return false;
        }
        rmvTMsgList.add(rmvTMsg);

        // Create Bisiness Prcess Log
        // 2016/02/25 S21_NA#3328 Mod Start
        int bizProcLogPk = createBizProcLog(EVENT_ID_ORD_DEL, rmvTMsg.dsOrdPosnNum.getValue(), rmvTMsg.cpoOrdNum.getValue(), salesRepList.configCatgCd_A.getValue());
        // 2016/02/25 S21_NA#3328 Mod End

        // Set Rec Data
        DS_CPO_SLS_CR_RECTMsg insRecTMsg = new DS_CPO_SLS_CR_RECTMsg();
        EZDMsg.copy(rmvTMsg, null, insRecTMsg, null);
        insRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
        dsCpoSlsCrRecTMsgList.add(insRecTMsg);

        return true;
    }

    /**
     * isError
     * @param msg NWZC152001PMsg
     * @return boolean
     */
    private boolean isError(NWZC152001PMsg msg) {
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
        // 2016/04/12 S21_NA#6834 Mod Start
//        if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
//            ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.docTpCd, DOCUMENT_TYPE);
//        } else {
//            ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.docTpCd, DOCUMENT_TYPE_RTN);
//        }
        if (CONFIG_CATG.INBOUND.equals(configCatgCd)) {
            ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.docTpCd, DOCUMENT_TYPE_RTN);
        } else {
            ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.docTpCd, DOCUMENT_TYPE);
        }
        // 2016/04/12 S21_NA#6834 Mod End
        // 2016/02/25 S21_NA#3328 Mod End
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.docId, dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.prntDocId, cpoOrdNum);
        EZDTBLAccessor.insert(bizProcLogTMsg);

        return bizProcLogPk;
    }
}
