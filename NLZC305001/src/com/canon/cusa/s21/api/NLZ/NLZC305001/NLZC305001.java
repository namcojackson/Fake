/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC305001;

import static com.canon.cusa.s21.api.NLZ.NLZC305001.constant.NLZC305001Constant.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AMT_CHNG_RSN_TPTMsg;
import business.db.ASSET_STSTMsg;
import business.db.ASSET_TPTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_ASSET_HIST_COL_MAPTMsg;
import business.db.DS_ASSET_HIST_COL_MAPTMsgArray;
import business.db.DS_ASSET_MSTRTMsg;
import business.db.DS_ASSET_MSTR_TRKTMsg;
import business.db.DS_ASSET_STGNGTMsg;
import business.db.DS_ASSET_TRXTMsg;
import business.db.DS_COND_CONSTTMsg;
import business.db.INVTY_TRXTMsg;
import business.db.MDSETMsg;
import business.db.PRNT_VNDTMsg;
import business.db.PRNT_VNDTMsgArray;
import business.db.PROC_MODETMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NLZC305001PMsg;
import business.parts.NLZC305001_updDtlListPMsg;
import business.parts.NFACommonJrnlEntryConstant.CoaSegment;

import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_CALC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_SEG_LKUP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CRAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_MODE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Asset Update API
 * This API creates or updates tables below;
 * Direct Sales Asset Master
 * Direct Sales Asset Transaction
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/13/2013   CSAI            Y.Imazu         Create          N/A
 * 08/14/2013   CSAI            Y.Imazu         Update          Defect#1509,1648
 * 09/13/2013   CSAI            Y.Imazu         Update          Defect#2275,2349
 * 2014/08/08   Fujitsu         M.Yamada        Update          CCH-RD105
 * 2014/08/15   Fujitsu         M.Yamada        Update          CCH-RD066
 * 01/04/2016   CSAI            Y.Imazu         Update          CSA
 * 04/07/2016   Hitachi         T.Tsuchida      Update          QC#6702
 * 04/11/2016   Hitachi         T.Tsuchida      Update          QC#6842
 * 04/12/2016   CSAI            Y.Imazu         Update          QC#6901
 * 04/12/2016   CSAI            Y.Imazu         Update          QC#6791
 * 05/13/2016   Hitachi         T.Tsuchida      Update          QC#8092
 * 2016/09/16   Hitachi         J.Kim           Update          QC#10360
 * 2016/09/27   Fujitsu         C.Tanaka        Update          QC#11899
 * 2016/09/28   Fujitsu         C.Tanaka        Update          QC#14408
 * 2016/10/20   Hitachi         J.Kim           Update          QC#13360
 * 2017/02/14   Hitachi         J.Kim           Update          QC#17440
 * 2017/02/23   Hitachi         J.Kim           Update          QC#17589
 * 2017/05/18   CITS            K.Ogino         Update          QC#18528
 * 2017/06/21   CITS            K.Ogino         Update          QC#19396
 * 2017/11/17   Hitachi         J.Kim           Update          QC#17088
 * 2017/12/04   Hitachi         J.Kim           Update          QC#18127
 * 2018/01/15   Hitachi         J.Kim           Update          QC#21965
 * 2018/01/18   Hitachi         J.Kim           Update          QC#17985
 * 2018/01/23   Hitachi         J.Kim           Update          QC#23418
 * 2018/01/29   Hitachi         J.Kim           Update          QC#21915
 * 2018/02/07   Hitachi         J.Kim           Update          QC#23890
 * 2018/02/08   Hitachi         J.Kim           Update          QC#23770
 * 2018/02/08   Hitachi         J.Kim           Update          QC#24024
 * 2018/04/17   Hitachi         J.Kim           Update          QC#22807
 * 2018/04/24   Hitachi         J.Kim           Update          QC#25791
 * 2018/06/07   Hitachi         J.Kim           Update          QC#26321
 * 2018/06/25   Hitachi         J.Kim           Update          QC#24844
 * 2018/07/24   Hitachi         J.Kim           Update          QC#24950
 * 2018/08/13   Hitachi         J.Kim           Update          QC#22267
 * 2018/08/21   Hitachi         J.Kim           Update          QC#27747
 * 2018/09/21   Hitachi         J.Kim           Update          QC#28341
 * 2019/06/05   Fujitsu         S.Yamamoto      Update          QC#50717
 * 2022/11/01   CITS            T.Masuyama      Update          QC#60751
 * </pre>
 */
public class NLZC305001 extends S21ApiCommonBase {

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String slsDt;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE paramOnBatchType;

    /** NLZC305001PMsg */
    private NLZC305001PMsg paramPMsg;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /** S21SsmBatchClient */
    private static S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NLZC305001.class);

    /** Constructor */
    public NLZC305001() {

        super();
    }

    /**
     * Execute Asset Update API
     * @param pMsgList List<NLZC305001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NLZC305001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {

        for (NLZC305001PMsg pMsg : pMsgList) {

            execute(pMsg, onBatchType);
        }
    }

    /**
     * Execute Asset Update API
     * @param pMsg NLZC305001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NLZC305001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        msgMap = new S21ApiMessageMap(pMsg);
        paramOnBatchType = onBatchType;
        paramPMsg = pMsg;

        /*************************************************************
         * 1. Check Level1 API Parameter
         ************************************************************/
        if (chkReqParams(pMsg)) {

            // Process for each update list data
            for (int i = 0; i < pMsg.updDtlList.getValidCount(); i++) {

                NLZC305001_updDtlListPMsg param = pMsg.updDtlList.no(i);

                /*********************************************************
                 * 2. Check Process Mode
                 ********************************************************/
                if (!chkProcMdParams(param)) {

                    continue;
                }

                /*********************************************************
                 * 3. Check DS Asset Master
                 ********************************************************/
                DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg = null;
                if (ZYPCommonFunc.hasValue(param.dsAssetMstrPk)) {

                    oldDsAssetMstrTMsg = getDsAssetMstr(param.dsAssetMstrPk.getValue());

                    if (oldDsAssetMstrTMsg == null) {

                        setParamErrMsg(param, NLZM2160E);
                        continue;

                    }
                }

                /*********************************************************
                 * 4. Check Asset Type
                 ********************************************************/
                ASSET_TPTMsg assetTpTMsg = null;
                if (ZYPCommonFunc.hasValue(param.assetTpCd)) {

                    assetTpTMsg = getAssetTp(param.assetTpCd.getValue());

                    if (assetTpTMsg == null) {

                        setParamErrMsg(param, NLZM2184E);
                        continue;
                    }

                }

                /*********************************************************
                 * 5. Check Inventory Transaction Data
                 ********************************************************/
                if (ZYPCommonFunc.hasValue(param.invtyTrxPk)) {

                    INVTY_TRXTMsg invtyTrxTMsg = getInvtyTrx(param.invtyTrxPk.getValue());

                    if (invtyTrxTMsg == null) {

                        setParamErrMsg(param, NLZM2238E);
                        continue;
                    }

                }

                /*********************************************************
                 * 6. Check Service Machine Master Data
                 ********************************************************/
                SVC_MACH_MSTRTMsg svcMachMstrTMsg = null;
                if (ZYPCommonFunc.hasValue(param.svcMachMstrPk)) {

                    svcMachMstrTMsg = getSvcMachMstr(param.svcMachMstrPk.getValue());

                    if (svcMachMstrTMsg == null) {

                        setParamErrMsg(param, NSZM0006E);
                        return;
                    }

                }

                /*********************************************************
                 * 7. Check DS Asset Staging Data
                 ********************************************************/
                DS_ASSET_STGNGTMsg dsAssetStgngTMsg = null;
                if (ZYPCommonFunc.hasValue(param.dsAssetStgngPk)) {

                    dsAssetStgngTMsg = getDsAssetStgng(param.dsAssetStgngPk.getValue());

                    if (dsAssetStgngTMsg == null) {

                        setParamErrMsg(param, NSZM0006E);
                        return;
                    }

                }

                /*********************************************************
                 * 8. Check Parent DS Asset Master Check
                 ********************************************************/
                DS_ASSET_MSTRTMsg parentDsAssetMstrTMsg = null;
                if (ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {

                    parentDsAssetMstrTMsg = getDsAssetMstr(param.prntDsAssetMstrPk.getValue());

                    if (parentDsAssetMstrTMsg == null) {

                        setParamErrMsg(param, NLZM2370E);
                        continue;

                    }
                }

                /*********************************************************
                 * 9. Service Config Master Check
                 ********************************************************/
                if (ZYPCommonFunc.hasValue(param.svcConfigMstrPk)) {

                    SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = getSvcConfigMstr(param.svcConfigMstrPk.getValue());

                    if (svcConfigMstrTMsg == null) {

                        setParamErrMsg(param, NLZM2365E);
                        continue;

                    }
                }

                /*********************************************************
                 * 10. Specify & Execute Event
                 ********************************************************/
                // DS Asset Master Tracking Column mapping
                Map<String, String> trkItemMap = getAssetHistColMapFindByCondition(glblCmpyCd);

                // 1.Revenue Recognition
                if (PROC_MODE_REV_RECOG.equals(param.xxProcMd.getValue())) {

                    doProcessRvnRecog(param, oldDsAssetMstrTMsg, trkItemMap);

                    // 2.Return
                } else if (PROC_MODE_RTRN.equals(param.xxProcMd.getValue())) {

                    doProcessRtrn(param, oldDsAssetMstrTMsg, parentDsAssetMstrTMsg, trkItemMap);

                    // 3.Disposal
                } else if (PROC_MODE_DSPL.equals(param.xxProcMd.getValue())) {

                    doProcessDspl(param, oldDsAssetMstrTMsg, parentDsAssetMstrTMsg, trkItemMap);

                    // 4.Update
                } else if (PROC_MODE_UPD.equals(param.xxProcMd.getValue())) {

                    doProcessUpd(param, oldDsAssetMstrTMsg, parentDsAssetMstrTMsg, trkItemMap, svcMachMstrTMsg);

                    // 5.Depreciation
                } else if (PROC_MODE_DEPC.equals(param.xxProcMd.getValue())) {

                    doProcessDepc(param, oldDsAssetMstrTMsg, trkItemMap);

                    // 6.Ship from Asset WH
                } else if (PROC_MODE_SHIP_FROM_ASSET_WH.equals(param.xxProcMd.getValue())) {

                    doProcessShipFromAssetWH(param, oldDsAssetMstrTMsg, trkItemMap, dsAssetStgngTMsg);

                    // 7.Update by Service Exchange
                } else if (PROC_MODE_UPDATE_BY_SERVICE_EXCHANGE.equals(param.xxProcMd.getValue())) {

                    doProcessUpdateByServiceExchange(param, oldDsAssetMstrTMsg, dsAssetStgngTMsg, trkItemMap);

                    // A.Pending Asset Entry
                } else if (PROC_MODE_PENDING_ASSET_ENTRY.equals(param.xxProcMd.getValue())) {

                    doProcessPendingAssetEntry(param, assetTpTMsg, dsAssetStgngTMsg, svcMachMstrTMsg, trkItemMap);

                    // B.Asset Activate
                } else if (PROC_MODE_ASSET_ACTIVATE.equals(param.xxProcMd.getValue())) {

                    doProcessAssetActivate(param, oldDsAssetMstrTMsg, trkItemMap);

                    // C.Update Before Activate
                } else if (PROC_MODE_UPD_BEFORE_ACTIVATE.equals(param.xxProcMd.getValue())) {

                    doProcessUpdBeforeActivate(param, oldDsAssetMstrTMsg, parentDsAssetMstrTMsg, trkItemMap, svcMachMstrTMsg);

                    // D.Asset Entry from AP
                } else if (PROC_MODE_ASSET_ENTRY_FROM_AP.equals(param.xxProcMd.getValue())) {

                    doProcessAssetEntryFromAP(param, dsAssetStgngTMsg, assetTpTMsg, svcMachMstrTMsg, trkItemMap);

                    // E.Asset Manual Entry
                } else if (PROC_MODE_ASSET_MAN_ENTRY.equals(param.xxProcMd.getValue())) {

                    doProcessAssetManEntry(param, assetTpTMsg, svcMachMstrTMsg, trkItemMap);

                    // H.Initial Cost Calculation
                } else if (PROC_MODE_INIT_COST_CALCULATION.equals(param.xxProcMd.getValue())) {

                    doProcessInitCostCaluc(param, oldDsAssetMstrTMsg, trkItemMap);

                    // I.Merge
                } else if (PROC_MODE_MERGE.equals(param.xxProcMd.getValue())) {

                    doProcessMerge(param, parentDsAssetMstrTMsg, trkItemMap);

                    // J.Summary
                } else if (PROC_MODE_SUMMARY.equals(param.xxProcMd.getValue())) {

                    doProcessSummary(param, parentDsAssetMstrTMsg, trkItemMap);

                } else {

                    setParamErrMsg(param, NAZM0065E);
                }
            }
        }

        msgMap.flush();
    }

    /**
     * Check Request Parameter (Global Company Code & Sales Date)
     * @param pMsg NLZC305001PMsg
     * @return boolean Check Result
     */
    private boolean chkReqParams(NLZC305001PMsg pMsg) {

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {

            msgMap.addXxMsgId(NTZM0147E);
            S21InfoLogOutput.println(NTZM0147E);
            return false;

        } else if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {

            msgMap.addXxMsgId(NTZM0148E);
            S21InfoLogOutput.println(NTZM0148E);
            return false;
        }

        glblCmpyCd = pMsg.glblCmpyCd.getValue();
        slsDt = pMsg.slsDt.getValue();
        return true;
    }

    /**
     * Check Detail Request Parameter (Process Mode)
     * @param param NLZC305001_updDtlListPMsg
     * @return boolean Check Result
     */
    private boolean chkProcMdParams(NLZC305001_updDtlListPMsg param) {

        if (!ZYPCommonFunc.hasValue(param.xxProcMd)) {

            setParamErrMsg(param, NLZM2087E);
            return false;

        } else if (!Arrays.asList(getPROC_MODE()).contains(param.xxProcMd.getValue())) {

            setParamErrMsg(param, NLZM2088E);
            return false;

        }

        return true;
    }

    /**
     * Revenue Recognition
     * @param param NLZC305001_updDtlListPMsg
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param trakItemMap Map<String, String>
     */
    private void doProcessRvnRecog(NLZC305001_updDtlListPMsg param, DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, Map<String, String> trakItemMap) {

        if (ZYPCommonFunc.hasValue(param.dsAssetMstrPk) //
                && !ZYPCommonFunc.hasValue(param.invtyTrxPk) //
                && !ZYPCommonFunc.hasValue(param.assetTpCd)) {

            if (ZYPCommonFunc.hasValue(param.invNum)) {
                // 1-4. Sales pattern
                doProcessSalesRev(param, oldDsAssetMstrTMsg, trakItemMap);
            } else {
                // 1-5. Expense pattern
                doProcessExpRev(param, oldDsAssetMstrTMsg, trakItemMap);
            }
        } else {

            setParamErrMsg(param, NAZM0065E);

        }
    }

    /**
     * Revenue Recognition for Asset (Sales)
     * @param param NLZC305001_updDtlListPMsg
     * @param dsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param trkItemMap Map<String, String>
     */
    private void doProcessSalesRev(NLZC305001_updDtlListPMsg param, DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, Map<String, String> trkItemMap) {

        /*************************************************************
         * 1. Check API Parameter
         ************************************************************/
        if (!ZYPCommonFunc.hasValue(param.invtyTrxPk)) {

            if (!ZYPCommonFunc.hasValue(param.slsRepTocCd)) {

                setParamErrMsg(param, NAZM0120E);
                return;

            } else if (!ZYPCommonFunc.hasValue(param.billToCustCd)) {

                setParamErrMsg(param, NWZM0988E);
                return;
            }
        }

        /*************************************************************
         * 2. Check DS Asset Master Active Asset Flag
         ************************************************************/
        if (!ZYPConstant.FLG_ON_Y.equals(oldDsAssetMstrTMsg.actvAssetFlg.getValue())) {

            setParamErrMsg(param, NLBM1290E);
            return;

        }

        /*************************************************************
         * 3. Amount Summary - Old
         ************************************************************/
        BigDecimal prntDsAssetMstrPk = oldDsAssetMstrTMsg.prntDsAssetMstrPk.getValue();
        Map<String, Object> assetMstrInfoMap = doProcessAmountSummary(prntDsAssetMstrPk);
        if (assetMstrInfoMap == null || assetMstrInfoMap.isEmpty()) {
            setParamErrMsg(param, NLZM2160E);
            return;
        }

        /*************************************************************
         * 4. Update DS Asset Master
         ************************************************************/
        DS_ASSET_MSTRTMsg updDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
        EZDTMsg.copy(oldDsAssetMstrTMsg, null, updDsAssetMstrTMsg, null);

        // Update DS Asset Master
        if (!updDsAssetMstrRev(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param, trkItemMap)) {

            return;
        }

        /*************************************************************
         * 5. Create DS Asset Transaction
         ************************************************************/
        // Create DS Asset Transaction
        insertDsAssetTrxSalesRev(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param);

        /*************************************************************
         * 6. Create DS Asset Tracking - Amount
         ************************************************************/
         setAssetTrackingAmount(param, updDsAssetMstrTMsg, assetMstrInfoMap, prntDsAssetMstrPk, trkItemMap);
    }

    /**
     * Revenue Recognition for Asset (Expense)
     * @param param NLZC305001_updDtlListPMsg
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param trkItemMap Map<String, String>
     */
    private void doProcessExpRev(NLZC305001_updDtlListPMsg param, DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, Map<String, String> trkItemMap) {

        /*************************************************************
         * 1. Check API Parameter
         ************************************************************/
        if (!ZYPCommonFunc.hasValue(param.invtyTrxPk)) {

            if (!ZYPCommonFunc.hasValue(param.slsRepTocCd)) {

                setParamErrMsg(param, NAZM0120E);
                return;

            } else if (!ZYPCommonFunc.hasValue(param.billToCustCd)) {

                setParamErrMsg(param, NWZM0988E);
                return;

            } else if (!ZYPCommonFunc.hasValue(param.coaProdCd)) {

                setParamErrMsg(param, NLZM2156E);
                return;

            } else if (!ZYPCommonFunc.hasValue(param.coaAcctCd)) {

                setParamErrMsg(param, NLZM2157E);
                return;

            } else if (!ZYPCommonFunc.hasValue(param.coaBrCd)) {

                setParamErrMsg(param, NLZM2158E);
                return;

            } else if (!ZYPCommonFunc.hasValue(param.coaCcCd)) {

                setParamErrMsg(param, NLZM2159E);
                return;
            }
        }

        /*************************************************************
         * 2. Check DS Asset Master Active Asset Flag
         ************************************************************/
        if (!ZYPConstant.FLG_ON_Y.equals(oldDsAssetMstrTMsg.actvAssetFlg.getValue())) {

            setParamErrMsg(param, NLBM1290E);
            return;

        }

        /*************************************************************
         * 3. Amount Summary - Old
         ************************************************************/
        BigDecimal prntDsAssetMstrPk = oldDsAssetMstrTMsg.prntDsAssetMstrPk.getValue();
        Map<String, Object> assetMstrInfoMap = doProcessAmountSummary(prntDsAssetMstrPk);
        if (assetMstrInfoMap == null || assetMstrInfoMap.isEmpty()) {
            setParamErrMsg(param, NLZM2160E);
            return;
        }

        /*************************************************************
         * 4. Update DS Asset Master
         ************************************************************/
        DS_ASSET_MSTRTMsg updDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
        EZDTMsg.copy(oldDsAssetMstrTMsg, null, updDsAssetMstrTMsg, null);

        // Update DS Asset Master
        if (!updDsAssetMstrRev(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param, trkItemMap)) {

            return;
        }

        /*************************************************************
         * 5. Create DS Asset Transaction
         ************************************************************/
        // Create DS Asset Transaction
        insertDsAssetTrxExpRev(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param);

        /*************************************************************
         * 6. Create DS Asset Tracking - Amount
         ************************************************************/
         setAssetTrackingAmount(param, updDsAssetMstrTMsg, assetMstrInfoMap, prntDsAssetMstrPk, trkItemMap);
    }

    /**
     * Return
     * @param param NLZC305001_updDtlListPMsg
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param trkItemMap Map<String, String>
     */
    private void doProcessRtrn(NLZC305001_updDtlListPMsg param, DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg parentDsAssetMstrTMsg, Map<String, String> trkItemMap) {

        /*************************************************************
         * 1. Check API Parameter
         ************************************************************/
        if (!ZYPCommonFunc.hasValue(param.dsAssetMstrPk)) {

            setParamErrMsg(param, NLZM2510E);
            return;
        }

        if (!ZYPCommonFunc.hasValue(param.dsAssetStgngPk)) {

            setParamErrMsg(param, NLZM2356E);
            return;
        }

        /*************************************************************
         * 2. Check DS Asset Master Active Asset Flag
         ************************************************************/
        if ((ZYPCommonFunc.hasValue(param.dsAssetMstrPk) && !ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk))) {

            if (!ZYPConstant.FLG_ON_Y.equals(oldDsAssetMstrTMsg.actvAssetFlg.getValue())) {

                setParamErrMsg(param, NLBM1290E);
                return;

            }

            /*************************************************************
             * 3-1. Amount Summary - Old
             ************************************************************/
            BigDecimal prntDsAssetMstrPk = oldDsAssetMstrTMsg.prntDsAssetMstrPk.getValue();
            Map<String, Object> assetMstrInfoMap = doProcessAmountSummary(prntDsAssetMstrPk);
            if (assetMstrInfoMap == null || assetMstrInfoMap.isEmpty()) {
                setParamErrMsg(param, NLZM2160E);
                return;
            }

            /*************************************************************
             * 3-2. Update DS Asset Master for Current
             ************************************************************/
            DS_ASSET_MSTRTMsg updDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
            EZDTMsg.copy(oldDsAssetMstrTMsg, null, updDsAssetMstrTMsg, null);

            // Update DS Asset Master
            if (!updDsAssetMstrRtrnCur(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param, trkItemMap)) {

                return;
            }

            /*************************************************************
             * 3-3. Create DS Asset Tracking - Amount
             ************************************************************/
            setAssetTrackingAmount(param, updDsAssetMstrTMsg, assetMstrInfoMap, prntDsAssetMstrPk, trkItemMap);

        } else if (ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(QRY_PARAM_PRNT_DS_ASSET_MSTR_PK, param.prntDsAssetMstrPk.getValue());
            queryParam.put(QRY_PARAM_ACTV_ASSET_FLG, ZYPConstant.FLG_ON_Y);

            List<Map<String, Object>> assetMstrInfoMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getParentAssetMstr", queryParam);

            if (assetMstrInfoMapList == null || assetMstrInfoMapList.isEmpty()) {

                setParamErrMsg(param, NLBM1290E);
                return;
            }

            /*************************************************************
             * 3-1. Amount Summary
             ************************************************************/
            BigDecimal prntDsAssetMstrPk = oldDsAssetMstrTMsg.prntDsAssetMstrPk.getValue();
            Map<String, Object> assetMstrInfoMap = doProcessAmountSummary(prntDsAssetMstrPk);
            if (assetMstrInfoMap == null || assetMstrInfoMap.isEmpty()) {
                setParamErrMsg(param, NLZM2160E);
                return;
            }

            /*************************************************************
             * 3-2. Update DS Asset Master for Parent
             ************************************************************/
            for (Map<String, Object> updMap : assetMstrInfoMapList) {

                oldDsAssetMstrTMsg = setDsAssetMstrTMsg(updMap);
                DS_ASSET_MSTRTMsg updDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
                EZDTMsg.copy(oldDsAssetMstrTMsg, null, updDsAssetMstrTMsg, null);

                // Update DS Asset Master
                if (!updDsAssetMstrRtrnParent(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param, trkItemMap)) {

                    return;
                }
            }

            /*************************************************************
             * 4. Create DS Asset Tracking - Amount
             ************************************************************/
            setAssetTrackingAmount(param, null, assetMstrInfoMap, prntDsAssetMstrPk, trkItemMap);
        }
    }

    /**
     * Disposal
     * @param param NLZC305001_updDtlListPMsg
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param trkItemMap Map<String, String>
     */
    private void doProcessDspl(NLZC305001_updDtlListPMsg param, DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg parentDsAssetMstrTMsg, Map<String, String> trkItemMap) {

        /*************************************************************
         * 1. Check API Parameter
         ************************************************************/
        if (!ZYPCommonFunc.hasValue(param.dsAssetMstrPk) && !ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {

            setParamErrMsg(param, NLZM2510E);
            return;
        }

        /*************************************************************
         * 2. Check DS Asset Master Active Asset Flag
         ************************************************************/
        if (ZYPCommonFunc.hasValue(param.dsAssetMstrPk)) {

            if (!ZYPConstant.FLG_ON_Y.equals(oldDsAssetMstrTMsg.actvAssetFlg.getValue())) {

                setParamErrMsg(param, NLBM1290E);
                return;

            }

            /*************************************************************
             * 3-1. Amount Summary - Old
             ************************************************************/
            BigDecimal prntDsAssetMstrPk = oldDsAssetMstrTMsg.prntDsAssetMstrPk.getValue();
            Map<String, Object> assetMstrInfoMap = doProcessAmountSummary(prntDsAssetMstrPk);
            if (assetMstrInfoMap == null || assetMstrInfoMap.isEmpty()) {
                setParamErrMsg(param, NLZM2160E);
                return;
            }

            /*************************************************************
             * 3-2. Update DS Asset Master for Current
             ************************************************************/
            DS_ASSET_MSTRTMsg updDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
            EZDTMsg.copy(oldDsAssetMstrTMsg, null, updDsAssetMstrTMsg, null);

            // Update DS Asset Master
            if (!updDsAssetMstrDsplCur(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param, trkItemMap)) {

                return;
            }

            /*************************************************************
             * 4-1. Create DS Asset Transaction
             ************************************************************/
            // Create DS Asset Transaction
            insertDsAssetTrxDspl(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param);

            /*************************************************************
             * 4-2. Create DS Asset Tracking - Amount
             ************************************************************/
            setAssetTrackingAmount(param, updDsAssetMstrTMsg, assetMstrInfoMap, prntDsAssetMstrPk, trkItemMap);

        } else if (ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(QRY_PARAM_PRNT_DS_ASSET_MSTR_PK, param.prntDsAssetMstrPk.getValue());
            queryParam.put(QRY_PARAM_ACTV_ASSET_FLG, ZYPConstant.FLG_ON_Y);

            List<Map<String, Object>> assetMstrInfoMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getParentAssetMstr", queryParam);

            if (assetMstrInfoMapList == null || assetMstrInfoMapList.isEmpty()) {

                setParamErrMsg(param, NLBM1290E);
                return;
            }

            /*************************************************************
             * 3-1. Amount Summary
             ************************************************************/
            BigDecimal prntDsAssetMstrPk = param.prntDsAssetMstrPk.getValue();
            Map<String, Object> assetMstrInfoMap = doProcessAmountSummary(prntDsAssetMstrPk);
            if (assetMstrInfoMap == null || assetMstrInfoMap.isEmpty()) {
                setParamErrMsg(param, NLZM2160E);
                return;
            }

            /*************************************************************
             * 3-2. Update DS Asset Master for Parent
             ************************************************************/
            for (Map<String, Object> updMap : assetMstrInfoMapList) {

                oldDsAssetMstrTMsg = setDsAssetMstrTMsg(updMap);
                DS_ASSET_MSTRTMsg updDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
                EZDTMsg.copy(oldDsAssetMstrTMsg, null, updDsAssetMstrTMsg, null);

                // Update DS Asset Master
                if (!updDsAssetMstrDsplParent(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param, trkItemMap)) {

                    return;
                }

                /*************************************************************
                 * 4-2. Create DS Asset Transaction
                 ************************************************************/
                // Create DS Asset Transaction
                insertDsAssetTrxDspl(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param);

            }

            /*************************************************************
             * 5. Create DS Asset Tracking - Amount
             ************************************************************/
             setAssetTrackingAmount(param, null, assetMstrInfoMap, prntDsAssetMstrPk, trkItemMap);
        }
    }

    /**
     * Update
     * @param param NLZC305001_updDtlListPMsg
     * @param curDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param parentDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param trkItemMap Map<String, String>
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     */
    private void doProcessUpd(NLZC305001_updDtlListPMsg param, DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg parentDsAssetMstrTMsg, Map<String, String> trkItemMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {

        /*************************************************************
         * 1. Check API Parameter
         ************************************************************/
        if (!ZYPCommonFunc.hasValue(param.dsAssetMstrPk) && !ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {

            setParamErrMsg(param, NLZM2510E);
            return;
        }

        List<Map<String, Object>> assetMstrInfoMapList = null;
        if (!ZYPCommonFunc.hasValue(param.dsAssetMstrPk) && ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(QRY_PARAM_PRNT_DS_ASSET_MSTR_PK, param.prntDsAssetMstrPk.getValue());
            queryParam.put(QRY_PARAM_ACTV_ASSET_FLG, ZYPConstant.FLG_ON_Y);

            assetMstrInfoMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getParentAssetMstr", queryParam);

            if (assetMstrInfoMapList == null || assetMstrInfoMapList.isEmpty()) {

                setParamErrMsg(param, NLBM1290E);
                return;
            }
        }

        /*************************************************************
         * 2. Check DS Asset Master Active Asset Flag
         ************************************************************/
        if (ZYPCommonFunc.hasValue(param.dsAssetMstrPk)) {

            // Current Asset Master Process.
            if (!ZYPConstant.FLG_ON_Y.equals(oldDsAssetMstrTMsg.actvAssetFlg.getValue())) {

                setParamErrMsg(param, NLBM1290E);
                return;

            }

            /*************************************************************
             * 3. Check Serial Number and Merchandise Code
             ************************************************************/
            if (ZYPCommonFunc.hasValue(param.mdseCd) || ZYPCommonFunc.hasValue(param.serNum)) {

                String mdseCd = oldDsAssetMstrTMsg.mdseCd.getValue();
                String serNum = oldDsAssetMstrTMsg.serNum.getValue();

                if (ZYPCommonFunc.hasValue(param.mdseCd)) {

                    mdseCd = param.mdseCd.getValue();
                }

                if (ZYPCommonFunc.hasValue(param.serNum)) {

                    serNum = param.serNum.getValue();
                }

                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                queryParam.put(QRY_PARAM_MDSE_CD, mdseCd);
                queryParam.put(QRY_PARAM_SER_NUM, serNum);
                queryParam.put(QRY_PARAM_ACTV_ASSET_FLG_Y, ZYPConstant.FLG_ON_Y);
                queryParam.put(QRY_PARAM_ACTV_ASSET_FLG_N, ZYPConstant.FLG_OFF_N);

                List<DS_ASSET_MSTRTMsg> chkDsAssetMstrList = (List<DS_ASSET_MSTRTMsg>) ssmBatchClient.queryObjectList("getDsAssetMstrPk", queryParam);

                for (DS_ASSET_MSTRTMsg chkDsAssetMstrTMsg : chkDsAssetMstrList) {

                    // DS_ASSET_MSTR_PK <> Check Result.PK
                    if (!oldDsAssetMstrTMsg.dsAssetMstrPk.getValue().equals(chkDsAssetMstrTMsg.dsAssetMstrPk.getValue())) {

                        boolean chkErrFlg = true;

                        // Check P Message Update Detail List
                        for (int i = 0; i < paramPMsg.updDtlList.getValidCount(); i++) {

                            if (chkDsAssetMstrTMsg.dsAssetMstrPk.getValue().equals(paramPMsg.updDtlList.no(i).dsAssetMstrPk.getValue())) {

                                if (ZYPCommonFunc.hasValue(paramPMsg.updDtlList.no(i).mdseCd) && !paramPMsg.updDtlList.no(i).mdseCd.getValue().equals(chkDsAssetMstrTMsg.mdseCd.getValue())) {

                                    chkErrFlg = false;

                                } else if (ZYPCommonFunc.hasValue(paramPMsg.updDtlList.no(i).serNum) && !paramPMsg.updDtlList.no(i).serNum.getValue().equals(chkDsAssetMstrTMsg.serNum.getValue())) {

                                    chkErrFlg = false;
                                }
                            }
                        }

                        if (chkErrFlg) {

                            setParamErrMsg(param, NLZM2161E);
                            return;
                        }
                    }
                }
            }

            /*************************************************************
             * 3. Amount Summary - Old
             ************************************************************/
            BigDecimal prntDsAssetMstrPk = oldDsAssetMstrTMsg.prntDsAssetMstrPk.getValue();
            Map<String, Object> assetMstrInfoMap = doProcessAmountSummary(prntDsAssetMstrPk);
            if (assetMstrInfoMap == null || assetMstrInfoMap.isEmpty()) {
                setParamErrMsg(param, NLZM2160E);
                return;
            }

            /*************************************************************
             * 4. Update DS Asset Master
             ************************************************************/
            DS_ASSET_MSTRTMsg updDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
            EZDTMsg.copy(oldDsAssetMstrTMsg, null, updDsAssetMstrTMsg, null);

            // Update DS Asset Master
            if (!updDsAssetMstrUpdCur(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param, trkItemMap, svcMachMstrTMsg)) {

                return;
            }

            /*************************************************************
             * 5. Create DS Asset Transaction
             ************************************************************/
            // Create DS Asset Transaction
            if (ZYPCommonFunc.hasValue(param.curValAmt) && param.curValAmt.getValue().compareTo(updDsAssetMstrTMsg.curValAmt.getValue()) != 0) {
                insertDsAssetTrxUpdCur(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param);
            }

            if (ZYPCommonFunc.hasValue(param.initBookAmt) && param.initBookAmt.getValue().compareTo(updDsAssetMstrTMsg.initBookAmt.getValue()) != 0) {
                insertDsAssetTrxUpdInitBookAmt(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param);
            }

            setAssetTrackingAmount(param, updDsAssetMstrTMsg, assetMstrInfoMap, prntDsAssetMstrPk, trkItemMap);

        } else if (ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {

            /*************************************************************
             * 3. Check Serial Number and Merchandise Code
             ************************************************************/
            if (ZYPCommonFunc.hasValue(param.mdseCd) || ZYPCommonFunc.hasValue(param.serNum)) {

                String mdseCd = parentDsAssetMstrTMsg.mdseCd.getValue();
                String serNum = parentDsAssetMstrTMsg.serNum.getValue();

                if (ZYPCommonFunc.hasValue(param.mdseCd)) {

                    mdseCd = param.mdseCd.getValue();
                }

                if (ZYPCommonFunc.hasValue(param.serNum)) {

                    serNum = param.serNum.getValue();
                }

                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                queryParam.put(QRY_PARAM_MDSE_CD, mdseCd);
                queryParam.put(QRY_PARAM_SER_NUM, serNum);
                queryParam.put(QRY_PARAM_ACTV_ASSET_FLG_Y, ZYPConstant.FLG_ON_Y);
                queryParam.put(QRY_PARAM_ACTV_ASSET_FLG_N, ZYPConstant.FLG_OFF_N);

                List<DS_ASSET_MSTRTMsg> chkDsAssetMstrList = (List<DS_ASSET_MSTRTMsg>) ssmBatchClient.queryObjectList("getDsAssetMstrPk", queryParam);

                for (DS_ASSET_MSTRTMsg chkDsAssetMstrTMsg : chkDsAssetMstrList) {

                    // DS_ASSET_MSTR_PK <> Check Result.PK
                    if (!parentDsAssetMstrTMsg.dsAssetMstrPk.getValue().equals(chkDsAssetMstrTMsg.dsAssetMstrPk.getValue())) {

                        boolean chkErrFlg = true;

                        // Check P Message Update Detail List
                        for (int i = 0; i < paramPMsg.updDtlList.getValidCount(); i++) {

                            if (chkDsAssetMstrTMsg.dsAssetMstrPk.getValue().equals(paramPMsg.updDtlList.no(i).dsAssetMstrPk.getValue())) {

                                if (ZYPCommonFunc.hasValue(paramPMsg.updDtlList.no(i).mdseCd) && !paramPMsg.updDtlList.no(i).mdseCd.getValue().equals(chkDsAssetMstrTMsg.mdseCd.getValue())) {

                                    chkErrFlg = false;

                                } else if (ZYPCommonFunc.hasValue(paramPMsg.updDtlList.no(i).serNum) && !paramPMsg.updDtlList.no(i).serNum.getValue().equals(chkDsAssetMstrTMsg.serNum.getValue())) {

                                    chkErrFlg = false;
                                }
                            }
                        }

                        if (chkErrFlg) {

                            setParamErrMsg(param, NLZM2161E);
                            return;
                        }
                    }
                }
            }

            /*************************************************************
             * 4. Total Amount
             ************************************************************/
            BigDecimal totCurValAmt = BigDecimal.ZERO;
            BigDecimal totInitBookAmt = BigDecimal.ZERO;
            BigDecimal totOrigStdCostAmt = BigDecimal.ZERO;

            if (ZYPCommonFunc.hasValue(param.initBookAmt) || ZYPCommonFunc.hasValue(param.curValAmt)) {
                for (Map<String, Object> oldDsAssetMstrInfoMap : assetMstrInfoMapList) {
                    totCurValAmt = totCurValAmt.add((BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_CUR_VAL_AMT));
                    totInitBookAmt = totInitBookAmt.add((BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_INIT_BOOK_AMT));
                    // START 2018/09/21 J.Kim [QC#28341, ADD]
                    if (ZYPCommonFunc.hasValue((BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_ORIG_STD_COST_AMT))) {
                        totOrigStdCostAmt = totOrigStdCostAmt.add((BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_ORIG_STD_COST_AMT));
                    }
                    // END 2018/09/21 J.Kim [QC#28341, ADD]
                }
            }

            BigDecimal prntDsAssetMstrPk = param.prntDsAssetMstrPk.getValue();
            Map<String, Object> assetMstrInfoMap = doProcessAmountSummary(prntDsAssetMstrPk);
            if (assetMstrInfoMap == null || assetMstrInfoMap.isEmpty()) {
                setParamErrMsg(param, NLZM2160E);
                return;
            }

            /*************************************************************
             * 5. Adjustment Total Cost
             ************************************************************/
            Map<String, BigDecimal> splAmtMap = setCostAmt(param, assetMstrInfoMapList, totCurValAmt, totInitBookAmt, totOrigStdCostAmt);

            for (int i = 0; i < assetMstrInfoMapList.size(); i++) {

                Map<String, Object> oldDsAssetMstrInfoMap = assetMstrInfoMapList.get(i);

                oldDsAssetMstrTMsg = setDsAssetMstrTMsg(oldDsAssetMstrInfoMap);
                DS_ASSET_MSTRTMsg updDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
                EZDTMsg.copy(oldDsAssetMstrTMsg, null, updDsAssetMstrTMsg, null);

                if (ZYPCommonFunc.hasValue(param.depcMthNum)) {
                    ZYPEZDItemValueSetter.setValue(parentDsAssetMstrTMsg.depcMthNum, param.depcMthNum);
                }
                /*************************************************************
                 * 6. Update DS Asset Master
                 ************************************************************/
                // Update DS Asset Master
                if (!updDsAssetMstrUpdParent(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param, trkItemMap, svcMachMstrTMsg, totOrigStdCostAmt, totCurValAmt, parentDsAssetMstrTMsg, splAmtMap)) {

                    return;
                }

                /*************************************************************
                 * 6. Create DS Asset Transaction
                 ************************************************************/
                // Create DS Asset Transaction
                if (ZYPCommonFunc.hasValue(param.curValAmt) // 
                        && param.curValAmt.getValue().compareTo(oldDsAssetMstrTMsg.curValAmt.getValue()) != 0 //
                        && updDsAssetMstrTMsg.curValAmt.getValue().compareTo(oldDsAssetMstrTMsg.curValAmt.getValue()) != 0) {

                    insertDsAssetTrxUpdCur(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param);
                }

                if (ZYPCommonFunc.hasValue(param.initBookAmt) // 
                        && param.initBookAmt.getValue().compareTo(oldDsAssetMstrTMsg.initBookAmt.getValue()) != 0 // 
                        && updDsAssetMstrTMsg.initBookAmt.getValue().compareTo(oldDsAssetMstrTMsg.initBookAmt.getValue()) != 0) {

                    insertDsAssetTrxUpdInitBookAmt(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param);
                }
            }

            /*************************************************************
             * 7. New Total Amount
             ************************************************************/
            if (ZYPCommonFunc.hasValue(param.initBookAmt) || ZYPCommonFunc.hasValue(param.curValAmt)) {
                // Insert Asset Tracking Data
                setAssetTrackingAmount(param, null, assetMstrInfoMap, prntDsAssetMstrPk, trkItemMap);
            }
        }
    }

    /**
     * H. Initial Cost Calculation
     * @param param NLZC305001_updDtlListPMsg
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param trkItemMap Map<String, String>
     */
    private void doProcessInitCostCaluc(NLZC305001_updDtlListPMsg param, DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, Map<String, String> trkItemMap) {

        /*************************************************************
         * 1. Check API Parameter
         ************************************************************/
        if (!ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {

            setParamErrMsg(param, NLZM2511E);
            return;
        }

        /*************************************************************
         * 2. Get Information for Asset Creation
         ************************************************************/
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(QRY_PARAM_PRNT_DS_ASSET_MSTR_PK, param.prntDsAssetMstrPk.getValue());
        queryParam.put(QRY_PARAM_ASSET_POST_FLG, ZYPConstant.FLG_OFF_N);

        List<Map<String, Object>> initCostCalucInfoMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getInitCostCalucInfo", queryParam);

        if (initCostCalucInfoMapList == null || initCostCalucInfoMapList.isEmpty()) {

            setParamErrMsg(param, NLZM2160E);
            return;
        }

        /*************************************************************
         * 3. Check Asset Info Validation
         ************************************************************/
        for (Map<String, Object> initCostCalucInfoMap : initCostCalucInfoMapList) {

            BigDecimal svcMachMstrPk = (BigDecimal) initCostCalucInfoMap.get(MAP_KEY_SVC_MACH_MSTR_PK);

            if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {

                setParamErrMsg(param, NLZM2433E);
                return;

            }
        }

        /*************************************************************
         * 4. Set Initial Cost Calculation
         ************************************************************/
        setInitCostCaluc(param, oldDsAssetMstrTMsg, trkItemMap, initCostCalucInfoMapList);

        /*************************************************************
         * 5. Amount Summary
         ************************************************************/
        DS_ASSET_MSTRTMsg prntDsAssetMstrTMsg = getDsAssetMstr(param.prntDsAssetMstrPk.getValue());
        doProcessSummary(param, prntDsAssetMstrTMsg, trkItemMap);
    }

    /**
     * Depreciation
     * @param param NLZC305001_updDtlListPMsg
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param trkItemMap Map<String, String>
     */
    private void doProcessDepc(NLZC305001_updDtlListPMsg param, DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, Map<String, String> trkItemMap) {

        /*************************************************************
         * 1. Check API Parameter
         ************************************************************/
        if (!ZYPCommonFunc.hasValue(param.dsAssetMstrPk)) {

            setParamErrMsg(param, NLZM2153E);
            return;

        } else if (!ZYPCommonFunc.hasValue(param.curValAmt)) {

            setParamErrMsg(param, NLZM2162E);
            return;

        } else if (!ZYPCommonFunc.hasValue(param.depcCnt)) {

            setParamErrMsg(param, NLZM2163E);
            return;

        } else if (!ZYPCommonFunc.hasValue(param.lastDepcYrMth)) {

            setParamErrMsg(param, NLZM2164E);
            return;
        }

        /*************************************************************
         * 2. Check DS Asset Master Active Asset Flag
         ************************************************************/
        if (!ZYPConstant.FLG_ON_Y.equals(oldDsAssetMstrTMsg.actvAssetFlg.getValue())) {

            setParamErrMsg(param, NLBM1290E);
            return;

        }
        /*************************************************************
         * 3. Update DS Asset Master
         ************************************************************/
        DS_ASSET_MSTRTMsg updDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
        EZDTMsg.copy(oldDsAssetMstrTMsg, null, updDsAssetMstrTMsg, null);

        // Update DS Asset Master
        if (!updDsAssetMstrDepc(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param, trkItemMap)) {

            return;
        }

        /*************************************************************
         * 4. Create DS Asset Transaction
         ************************************************************/
        // Create DS Asset Transaction
        insertDsAssetTrxDepc(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param);
    }

    /**
     * Ship from Asset WH
     * @param param NLZC305001_updDtlListPMsg
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param trkItemMap Map<String, String>
     * @param dsAssetStgngTMsg DS_ASSET_STGNGTMsg
     */
    private void doProcessShipFromAssetWH(NLZC305001_updDtlListPMsg param, DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, Map<String, String> trkItemMap, DS_ASSET_STGNGTMsg dsAssetStgngTMsg) {
        /*************************************************************
         * 1. Check API Parameter
         ************************************************************/
        if (!ZYPCommonFunc.hasValue(param.dsAssetMstrPk)) {

            setParamErrMsg(param, NLZM2153E);
            return;

        } else if (!ZYPCommonFunc.hasValue(param.dsAssetStgngPk)) {

            setParamErrMsg(param, NLZM2356E);
            return;
        }

        /*************************************************************
         * 2. Check DS Asset Master Active Asset Flag
         ************************************************************/
        if (!ZYPConstant.FLG_ON_Y.equals(oldDsAssetMstrTMsg.actvAssetFlg.getValue())) {

            setParamErrMsg(param, NLBM1290E);
            return;

        }
        /*************************************************************
         * 3. Update DS Asset Master
         ************************************************************/
        DS_ASSET_MSTRTMsg updDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
        EZDTMsg.copy(oldDsAssetMstrTMsg, null, updDsAssetMstrTMsg, null);

        // Update DS Asset Master
        if (!updDsAssetMstrShipFromAssetWH(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param, trkItemMap, dsAssetStgngTMsg)) {

            return;
        }
    }

    // START 2017/12/04 J.Kim [QC#18127,ADD]
    /**
     * Update By Service Exchange
     * @param param NLZC305001_updDtlListPMsg
     * @param dsAssetStgngTMsg DS_ASSET_STGNGTMsg
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     */
    private void doProcessUpdateByServiceExchange(NLZC305001_updDtlListPMsg param, DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_STGNGTMsg dsAssetStgngTMsg, Map<String, String> trkItemMap) {

        /*************************************************************
         * 1. Check API Parameter
         ************************************************************/
        if (!ZYPCommonFunc.hasValue(param.dsAssetMstrPk)) {

            setParamErrMsg(param, NLZM2153E);
            return;
        }

        if (!ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {

            setParamErrMsg(param, NLZM2153E);
            return;
        }

        if (!ZYPCommonFunc.hasValue(param.dsAssetStgngPk)) {

            setParamErrMsg(param, NLZM2356E);
            return;
        }

        if (!ZYPCommonFunc.hasValue(param.svcMachMstrPk)) {

            setParamErrMsg(param, NSZM0074E);
            return;
        }

        if (!ZYPCommonFunc.hasValue(param.mdseCd)) {

            setParamErrMsg(param, NWZM0996E);
            return;
        }

        // Update DS Asset Master
        /*************************************************************
         * 3. Update DS Asset Master for Current
         ************************************************************/
        DS_ASSET_MSTRTMsg updDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
        EZDTMsg.copy(oldDsAssetMstrTMsg, null, updDsAssetMstrTMsg, null);

        // Update DS Asset Master
        if (!updDsAssetMstrSrvEx(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, dsAssetStgngTMsg, param, trkItemMap)) {
            return;
        }
    }

    /**
     * Update Asset Master - Service Exchange
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param dsAssetStgngTMsg DS_ASSET_STGNGTMsg
     * @param param NLZC305001_updDtlListPMsg
     * @param trkItemMap Map<String, String>
     * @return true/false
     */
    private boolean updDsAssetMstrSrvEx(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, DS_ASSET_STGNGTMsg dsAssetStgngTMsg, NLZC305001_updDtlListPMsg param, Map<String, String> trkItemMap) {

        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.mdseCd, param.mdseCd);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.serNum, dsAssetStgngTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.svcMachMstrPk, param.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoOrdNum, dsAssetStgngTMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineNum, dsAssetStgngTMsg.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineSubNum, dsAssetStgngTMsg.cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsOrdPosnNum, dsAssetStgngTMsg.dsOrdPosnNum);
        // START 2018/01/18 J.Kim [QC#17985,MOD]
        // ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.invNum, dsAssetStgngTMsg.invNum);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.apVndInvNum, dsAssetStgngTMsg.apVndInvNum);
        // END 2018/01/18 J.Kim [QC#17985,MOD]
        // START 2018/04/10 J.Kim [QC#25381,ADD]
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.apVndInvLineNum, dsAssetStgngTMsg.apVndInvLineNum);
        // END 2018/04/10 J.Kim [QC#25381,ADD]
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.invDt, dsAssetStgngTMsg.invDt);
        // START 2018/06/25 J.Kim [QC#24844, ADD]
        if (ZYPCommonFunc.hasValue(dsAssetStgngTMsg.dsContrNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsContrNum, dsAssetStgngTMsg.dsContrNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.contrEffFromDt, dsAssetStgngTMsg.contrEffFromDt);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.contrEffThruDt, dsAssetStgngTMsg.contrEffThruDt);
        }
        if (ZYPCommonFunc.hasValue(dsAssetStgngTMsg.custIssPoNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.custIssPoNum, dsAssetStgngTMsg.custIssPoNum);
        }
        // END 2018/06/25 J.Kim [QC#24844, ADD]

        S21ApiTBLAccessor.update(updDsAssetMstrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updDsAssetMstrTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", updDsAssetMstrTMsg.dsAssetMstrPk.getValue().toString());
            setDBAcsErrMsg(param, NASM0007E, new String[] {TBL_DS_ASSET_MSTR, pKey });

            return false;
        }

        // START 2018/07/20 J.Kim [QC#24950,ADD]
        if (param.prntDsAssetMstrPk.getValue().compareTo(updDsAssetMstrTMsg.dsAssetMstrPk.getValue()) != 0) {
            return true;
        }
        // END 2018/07/20 J.Kim [QC#24950,ADD]

        setAssetTrackingData(param, updDsAssetMstrTMsg, "mdseCd", oldDsAssetMstrTMsg.mdseCd.getValue(), updDsAssetMstrTMsg.mdseCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "serNum", oldDsAssetMstrTMsg.serNum.getValue(), updDsAssetMstrTMsg.serNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "svcMachMstrPk", oldDsAssetMstrTMsg.svcMachMstrPk.getValue(), updDsAssetMstrTMsg.svcMachMstrPk.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoOrdNum", oldDsAssetMstrTMsg.cpoOrdNum.getValue(), updDsAssetMstrTMsg.cpoOrdNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoDtlLineNum", oldDsAssetMstrTMsg.cpoDtlLineNum.getValue(), updDsAssetMstrTMsg.cpoDtlLineNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoDtlLineSubNum", oldDsAssetMstrTMsg.cpoDtlLineSubNum.getValue(), updDsAssetMstrTMsg.cpoDtlLineSubNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "dsOrdPosnNum", oldDsAssetMstrTMsg.dsOrdPosnNum.getValue(), updDsAssetMstrTMsg.dsOrdPosnNum.getValue(), trkItemMap);
        // START 2018/08/24 J.Kim [QC#22267,DEL]
        //// START 2018/01/18 J.Kim [QC#17985,MOD]
        //// setAssetTrackingData(param, updDsAssetMstrTMsg, "invNum", oldDsAssetMstrTMsg.invNum.getValue(), updDsAssetMstrTMsg.invNum.getValue(), trkItemMap);
        //setAssetTrackingData(param, updDsAssetMstrTMsg, "apVndInvNum", oldDsAssetMstrTMsg.apVndInvNum.getValue(), updDsAssetMstrTMsg.apVndInvNum.getValue(), trkItemMap);
        //// END 2018/01/18 J.Kim [QC#17985,MOD]
        // END 2018/08/24 J.Kim [QC#22267,DEL]
        setAssetTrackingData(param, updDsAssetMstrTMsg, "invDt", oldDsAssetMstrTMsg.invDt.getValue(), updDsAssetMstrTMsg.invDt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "dsContrNum", oldDsAssetMstrTMsg.dsContrNum.getValue(), updDsAssetMstrTMsg.dsContrNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "contrEffFromDt", oldDsAssetMstrTMsg.contrEffFromDt.getValue(), updDsAssetMstrTMsg.contrEffFromDt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "contrEffThruDt", oldDsAssetMstrTMsg.contrEffThruDt.getValue(), updDsAssetMstrTMsg.contrEffThruDt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "custIssPoNum", oldDsAssetMstrTMsg.custIssPoNum.getValue(), updDsAssetMstrTMsg.custIssPoNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "curValAmt", oldDsAssetMstrTMsg.curValAmt.getValue(), updDsAssetMstrTMsg.curValAmt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "initBookAmt", oldDsAssetMstrTMsg.initBookAmt.getValue(), updDsAssetMstrTMsg.initBookAmt.getValue(), trkItemMap);

        return true;
    }
    // END 2017/12/04 J.Kim [QC#18127,ADD]

    /**
     * Pending Asset Entry
     * @param param NLZC305001_updDtlListPMsg
     * @param assetTpTMsg ASSET_TPTMsg
     * @param dsAssetStgngTMsg DS_ASSET_STGNGTMsg
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     */
    private void doProcessPendingAssetEntry(NLZC305001_updDtlListPMsg param, ASSET_TPTMsg assetTpTMsg, DS_ASSET_STGNGTMsg dsAssetStgngTMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, Map<String, String> trkItemMap) {
        /*************************************************************
         * 1. Check API Parameter
         ************************************************************/
        if (!ZYPCommonFunc.hasValue(param.assetTpCd)) {

            setParamErrMsg(param, NLZM2152E);
            return;

        } else if (!ZYPCommonFunc.hasValue(param.dsAssetStgngPk)) {

            setParamErrMsg(param, NLZM2356E);
            return;
        }

        /*************************************************************
         * 2. Get Merchandise
         ************************************************************/
        MDSETMsg mdseTMsg = null;
        if (svcMachMstrTMsg != null) {
            mdseTMsg = getMdse(svcMachMstrTMsg.glblCmpyCd.getValue(), svcMachMstrTMsg.mdseCd.getValue());

            if (mdseTMsg == null) {

                setParamErrMsg(param, NWZM0364E);
                return;

            }
        }
        
        /*************************************************************
         * 3. Get default Depreciation Month Number
         ************************************************************/
        String depcMthNum = getDefDepcMthNum(assetTpTMsg);

        /*************************************************************
         * 3. Insert DS Asset Master
         ************************************************************/
        DS_ASSET_MSTRTMsg insDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();

        BigDecimal dsAssetMstrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_MSTR_SQ);

        // Set Asset Master
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.dsAssetMstrPk, dsAssetMstrPk);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.mdseCd, svcMachMstrTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.serNum, svcMachMstrTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.actvAssetFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetTpCd, param.assetTpCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.svcMachMstrPk, param.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.shpgPlnNum, dsAssetStgngTMsg.shpgPlnNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.invtyTrxPk, dsAssetStgngTMsg.invtyTrxPk);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.origInvtyTrxPk, dsAssetStgngTMsg.invtyTrxPk);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.revRecogDt, slsDt);

        // Set Depreciation
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.initBookAmt, dsAssetStgngTMsg.stdCostAmt);
        // START 2018/07/31 J.Kim [QC#26902,ADD]
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.origInitBookAmt, dsAssetStgngTMsg.stdCostAmt);
        // END 2018/07/31 J.Kim [QC#26902,ADD]
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.depcMthNum, depcMthNum);

        if (ZYPCommonFunc.hasValue(svcMachMstrTMsg.istlDt)) {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.depcStartDt, svcMachMstrTMsg.istlDt);
        } else {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.depcStartDt, slsDt);
        }

        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.depcCnt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.curValAmt, dsAssetStgngTMsg.stdCostAmt);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.slsRepTocCd, dsAssetStgngTMsg.slsRepTocCd);
        String firstDepcYrMth = getFirstDepcYrMth(insDsAssetMstrTMsg.depcStartDt.getValue(), assetTpTMsg);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.firstDepcYrMth, firstDepcYrMth);

        if (ZYPCommonFunc.hasValue(param.rsdlValAmt)) {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.rsdlValAmt, param.rsdlValAmt);
        } else if (mdseTMsg != null && ZYPCommonFunc.hasValue(mdseTMsg.rsdlValAmt)) {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.rsdlValAmt, mdseTMsg.rsdlValAmt);
        } else {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.rsdlValAmt, BigDecimal.ZERO);
        }

        // Set Asset Master Detail
        if (ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetStsCd, ASSET_STS.MERGED);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.prntDsAssetMstrPk, param.prntDsAssetMstrPk);
        } else {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetStsCd, ASSET_STS.PENDING);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.prntDsAssetMstrPk, insDsAssetMstrTMsg.dsAssetMstrPk);
        }

        // START 2018/07/24 J.Kim [QC#24950,MOD]
        //// START 2018/02/07 J.Kim [QC#23890,MOD]
        ////if (ZYPCommonFunc.hasValue(insDsAssetMstrTMsg.svcMachMstrPk)) {
        //String procModeCd = dsAssetStgngTMsg.procModeCd.getValue();
        //if (!PROC_MODE_51.equals(procModeCd)) {
        //    // END 2018/02/07 J.Kim [QC#23890,MOD]
        //    insDsAssetMstrTMsg.cpoOrdNum.clear();
        //    insDsAssetMstrTMsg.cpoDtlLineNum.clear();
        //    insDsAssetMstrTMsg.cpoDtlLineSubNum.clear();
        //    insDsAssetMstrTMsg.dsOrdPosnNum.clear();
        //    insDsAssetMstrTMsg.shipToCustAcctCd.clear();
        //    // START 2018/01/15 J.Kim [QC#21965,DEL]
        //    // insDsAssetMstrTMsg.shipToCustCd.clear();
        //    // END 2018/01/15 J.Kim [QC#21965,DEL]
        //    insDsAssetMstrTMsg.sellToCustCd.clear();
        //    insDsAssetMstrTMsg.sldToCustLocCd.clear();
        //} else {

        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.cpoOrdNum, dsAssetStgngTMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.cpoDtlLineNum, dsAssetStgngTMsg.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.cpoDtlLineSubNum, dsAssetStgngTMsg.cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.dsOrdPosnNum, dsAssetStgngTMsg.dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.shipToCustAcctCd, dsAssetStgngTMsg.shipToCustAcctCd);
        // START 2018/01/15 J.Kim [QC#21965,DEL]
        // ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.shipToCustCd, dsAssetStgngTMsg.shipToCustCd);
        // END 2018/01/15 J.Kim [QC#21965,DEL]
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.sellToCustCd, dsAssetStgngTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.sldToCustLocCd, dsAssetStgngTMsg.soldToCustLocCd);
        //}
        // END 2018/07/24 J.Kim [QC#24950,MOD]
        // START 2018/01/15 J.Kim [QC#21965,ADD]
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.shipToCustCd, dsAssetStgngTMsg.shipToCustCd);
        // END 2018/01/15 J.Kim [QC#21965,ADD]

        // START 2018/01/18 J.Kim [QC#17985,MOD]
        // ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.invNum, dsAssetStgngTMsg.invNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.apVndInvNum, dsAssetStgngTMsg.apVndInvNum);
        // END 2018/01/18 J.Kim [QC#17985,MOD]
        // START 2018/04/10 J.Kim [QC#25381,ADD]
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.apVndInvLineNum, dsAssetStgngTMsg.apVndInvLineNum);
        // END 2018/04/10 J.Kim [QC#25381,ADD]
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.invDt, dsAssetStgngTMsg.invDt);
        // START 2018/01/18 J.Kim [QC#17985,MOD]
        // String prntVndCd = getParentVendorCode(insDsAssetMstrTMsg.invNum.getValue());
        String prntVndCd = getParentVendorCode(insDsAssetMstrTMsg.apVndInvNum.getValue());
        // END 2018/01/18 J.Kim [QC#17985,MOD]
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.vndCd, prntVndCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.manEntryFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.origStdCostAmt, dsAssetStgngTMsg.stdCostAmt);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.origCsmpPrcAmt, param.csmpPrcAmt);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.origOrdAdjAmt, param.ordAdjAmt);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCalcStsCd, ASSET_CALC_STS.NOT_CONFIG_CALCULATED);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.baseCmptFlg, dsAssetStgngTMsg.baseCmptFlg);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetPostFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.svcConfigMstrPk, dsAssetStgngTMsg.toSvcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.procModeCd, dsAssetStgngTMsg.procModeCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.totAssetQty, BigDecimal.ONE);

        // Set 9 Segments
        if (ZYPCommonFunc.hasValue(dsAssetStgngTMsg.apVndInvSqNum)) {
            set9Segments(insDsAssetMstrTMsg, dsAssetStgngTMsg, assetTpTMsg);
        }

        // START 2018/04/16 J.Kim [QC#22807,ADD]
        setCoaSegments(insDsAssetMstrTMsg, dsAssetStgngTMsg, assetTpTMsg);
        // END 2018/04/16 J.Kim [QC#22807,ADD]
        // START 2018/06/25 J.Kim [QC#24844, ADD]
        if (ZYPCommonFunc.hasValue(dsAssetStgngTMsg.custIssPoNum)) {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.custIssPoNum, dsAssetStgngTMsg.custIssPoNum);
        }
        if (ZYPCommonFunc.hasValue(dsAssetStgngTMsg.dsContrNum)) {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.dsContrNum, dsAssetStgngTMsg.dsContrNum);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.contrEffFromDt, dsAssetStgngTMsg.contrEffFromDt);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.contrEffThruDt, dsAssetStgngTMsg.contrEffThruDt);
        }
        // END 2018/06/25 J.Kim [QC#24844, ADD]

        S21ApiTBLAccessor.insert(insDsAssetMstrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insDsAssetMstrTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", insDsAssetMstrTMsg.dsAssetMstrPk.getValue().toString());
            setDBAcsErrMsg(param, NASM0007E, new String[] {TBL_DS_ASSET_MSTR, pKey });

        }

        param.dsAssetMstrPk.setValue(dsAssetMstrPk);
    }

    /**
     * Asset Activate
     * @param param NLZC305001_updDtlListPMsg
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param trkItemMap Map<String, String>
     */
    private void doProcessAssetActivate(NLZC305001_updDtlListPMsg param, DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, Map<String, String> trkItemMap) {

        /*************************************************************
         * 1. Check API Parameter
         ************************************************************/
        if (!ZYPCommonFunc.hasValue(param.dsAssetMstrPk) && !ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {

            setParamErrMsg(param, NLZM2510E);
            return;
        }

        if (ZYPCommonFunc.hasValue(param.dsAssetMstrPk)) {

            /*************************************************************
             * 2. Check DS Asset Master Active Asset Flag
             ************************************************************/
            if (!ZYPConstant.FLG_OFF_N.equals(oldDsAssetMstrTMsg.actvAssetFlg.getValue())) {

                setParamErrMsg(param, NLBM1290E);
                return;

            }

            /*************************************************************
             * 3. Get Asset Transaction
             ************************************************************/
            DS_COND_CONSTTMsg dsCondConstTMsg = getDsCondConst(NLZC3050_ASSET_IMPT, oldDsAssetMstrTMsg.assetTpCd.getValue());

            if (dsCondConstTMsg == null) {
                setParamErrMsg(param, NLZM2355E);
                return;

            }

            /*************************************************************
             * 4. Update DS Asset Master for Current
             ************************************************************/
            DS_ASSET_MSTRTMsg updDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
            EZDTMsg.copy(oldDsAssetMstrTMsg, null, updDsAssetMstrTMsg, null);

            // Update DS Asset Master
            if (!updDsAssetMstrAssetActivate(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param, trkItemMap)) {

                return;
            }

            /*************************************************************
             * 5. Create DS Asset Transaction
             ************************************************************/
            // Create DS Asset Transaction
            insertDsAssetTrxAssetActivate(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param, dsCondConstTMsg);

            // START 2018/07/31 J.Kim [QC#26902,ADD]
            if (ZYPCommonFunc.hasValue(updDsAssetMstrTMsg.origInitBookAmt) && updDsAssetMstrTMsg.initBookAmt.getValue().compareTo(updDsAssetMstrTMsg.origInitBookAmt.getValue()) != 0) {
                insertDsAssetTrxUpdInitBookAmtImport(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param);
            }
            // END 2018/07/31 J.Kim [QC#26902,ADD]

        } else if (ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {

            /*************************************************************
             * 2. Check DS Asset Master Active Asset Flag
             ************************************************************/
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(QRY_PARAM_PRNT_DS_ASSET_MSTR_PK, param.prntDsAssetMstrPk.getValue());
            queryParam.put(QRY_PARAM_ACTV_ASSET_FLG, ZYPConstant.FLG_OFF_N);
            queryParam.put(QRY_PARAM_ASSET_POST_FLG, ZYPConstant.FLG_OFF_N);

            List<Map<String, Object>> assetMstrInfoMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getParentAssetMstr", queryParam);

            if (assetMstrInfoMapList == null || assetMstrInfoMapList.isEmpty()) {

                setParamErrMsg(param, NLBM1290E);
                return;
            }

            /*************************************************************
             * 3. Total Amount
             ************************************************************/
            BigDecimal prntDsAssetMstrPk = param.prntDsAssetMstrPk.getValue();
            Map<String, Object> assetMstrInfoMap = doProcessAmountSummary(prntDsAssetMstrPk);
            if (assetMstrInfoMap == null || assetMstrInfoMap.isEmpty()) {
                setParamErrMsg(param, NLZM2160E);
                return;
            }

            for (Map<String, Object> updMap : assetMstrInfoMapList) {

                oldDsAssetMstrTMsg = setDsAssetMstrTMsg(updMap);

                /*************************************************************
                 * 4. Get Asset Transaction
                 ************************************************************/
                DS_COND_CONSTTMsg dsCondConstTMsg = getDsCondConst(NLZC3050_ASSET_IMPT, oldDsAssetMstrTMsg.assetTpCd.getValue());

                if (dsCondConstTMsg == null) {
                    setParamErrMsg(param, NLZM2355E);
                    return;

                }

                /*************************************************************
                 * 5. Update DS Asset Master for Parent
                 ************************************************************/
                DS_ASSET_MSTRTMsg updDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
                EZDTMsg.copy(oldDsAssetMstrTMsg, null, updDsAssetMstrTMsg, null);

                // Update DS Asset Master
                if (!updDsAssetMstrAssetActivate(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param, trkItemMap)) {

                    return;
                }

                /*************************************************************
                 * 6. Create DS Asset Transaction
                 ************************************************************/
                // Create DS Asset Transaction
                insertDsAssetTrxAssetActivate(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param, dsCondConstTMsg);

            	// START 2022/11/01 T.Masuyama [QC#26902,DEL]
                // START 2018/07/31 J.Kim [QC#26902,ADD]
                //if (ZYPCommonFunc.hasValue(updDsAssetMstrTMsg.origInitBookAmt) && updDsAssetMstrTMsg.initBookAmt.getValue().compareTo(updDsAssetMstrTMsg.origInitBookAmt.getValue()) != 0) {
                    //insertDsAssetTrxUpdInitBookAmtImport(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param);
                //}
                // END 2018/07/31 J.Kim [QC#26902,ADD]
            	// END 2022/11/01 T.Masuyama [QC#26902,DEL]

            }

            /*************************************************************
             * 7. Create DS Asset Tracking - Amount
             ************************************************************/
            setAssetTrackingAmount(param, null, assetMstrInfoMap, prntDsAssetMstrPk, trkItemMap);
        }
    }

    /**
     * Update Before Activate
     * @param param NLZC305001_updDtlListPMsg
     * @param curDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param parentDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param trkItemMap Map<String, String>
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     */
    private void doProcessUpdBeforeActivate(NLZC305001_updDtlListPMsg param, DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg parentDsAssetMstrTMsg, Map<String, String> trkItemMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {

        /*************************************************************
         * 1. Check API Parameter
         ************************************************************/
        if (!ZYPCommonFunc.hasValue(param.dsAssetMstrPk) && !ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {

            setParamErrMsg(param, NLZM2510E);
            return;
        }

        List<Map<String, Object>> assetMstrInfoMapList = null;
        if (!ZYPCommonFunc.hasValue(param.dsAssetMstrPk) && ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(QRY_PARAM_PRNT_DS_ASSET_MSTR_PK, param.prntDsAssetMstrPk.getValue());
            queryParam.put(QRY_PARAM_ACTV_ASSET_FLG, ZYPConstant.FLG_OFF_N);
            queryParam.put(QRY_PARAM_ASSET_POST_FLG, ZYPConstant.FLG_OFF_N);

            assetMstrInfoMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getParentAssetMstr", queryParam);

            if (assetMstrInfoMapList == null || assetMstrInfoMapList.isEmpty()) {

                setParamErrMsg(param, NLBM1290E);
                return;
            }
        }

        /*************************************************************
         * 2. Check DS Asset Master Active Asset Flag
         ************************************************************/
        if (ZYPCommonFunc.hasValue(param.dsAssetMstrPk)) {

            // Current Asset Master Process.
            if (!ZYPConstant.FLG_OFF_N.equals(oldDsAssetMstrTMsg.actvAssetFlg.getValue()) || !ZYPConstant.FLG_OFF_N.equals(oldDsAssetMstrTMsg.assetPostFlg.getValue())) {

                setParamErrMsg(param, NLBM1290E);
                return;

            }

            /*************************************************************
             * 3. Check Serial Number and Merchandise Code
             ************************************************************/
            if (ZYPCommonFunc.hasValue(param.mdseCd) || ZYPCommonFunc.hasValue(param.serNum)) {

                String mdseCd = oldDsAssetMstrTMsg.mdseCd.getValue();
                String serNum = oldDsAssetMstrTMsg.serNum.getValue();

                if (ZYPCommonFunc.hasValue(param.mdseCd)) {

                    mdseCd = param.mdseCd.getValue();
                }

                if (ZYPCommonFunc.hasValue(param.serNum)) {

                    serNum = param.serNum.getValue();
                }

                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                queryParam.put(QRY_PARAM_MDSE_CD, mdseCd);
                queryParam.put(QRY_PARAM_SER_NUM, serNum);
                queryParam.put(QRY_PARAM_ACTV_ASSET_FLG_Y, ZYPConstant.FLG_ON_Y);
                queryParam.put(QRY_PARAM_ACTV_ASSET_FLG_N, ZYPConstant.FLG_OFF_N);

                List<DS_ASSET_MSTRTMsg> chkDsAssetMstrList = (List<DS_ASSET_MSTRTMsg>) ssmBatchClient.queryObjectList("getDsAssetMstrPk", queryParam);

                for (DS_ASSET_MSTRTMsg chkDsAssetMstrTMsg : chkDsAssetMstrList) {

                    // DS_ASSET_MSTR_PK <> Check Result.PK
                    if (!oldDsAssetMstrTMsg.dsAssetMstrPk.getValue().equals(chkDsAssetMstrTMsg.dsAssetMstrPk.getValue())) {

                        boolean chkErrFlg = true;

                        // Check P Message Update Detail List
                        for (int i = 0; i < paramPMsg.updDtlList.getValidCount(); i++) {

                            if (chkDsAssetMstrTMsg.dsAssetMstrPk.getValue().equals(paramPMsg.updDtlList.no(i).dsAssetMstrPk.getValue())) {

                                if (ZYPCommonFunc.hasValue(paramPMsg.updDtlList.no(i).mdseCd) && !paramPMsg.updDtlList.no(i).mdseCd.getValue().equals(chkDsAssetMstrTMsg.mdseCd.getValue())) {

                                    chkErrFlg = false;

                                } else if (ZYPCommonFunc.hasValue(paramPMsg.updDtlList.no(i).serNum) && !paramPMsg.updDtlList.no(i).serNum.getValue().equals(chkDsAssetMstrTMsg.serNum.getValue())) {

                                    chkErrFlg = false;
                                }
                            }
                        }

                        if (chkErrFlg) {

                            setParamErrMsg(param, NLZM2161E);
                            return;
                        }
                    }
                }
            }

            /*************************************************************
             * 4. Update DS Asset Master
             ************************************************************/
            DS_ASSET_MSTRTMsg updDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
            EZDTMsg.copy(oldDsAssetMstrTMsg, null, updDsAssetMstrTMsg, null);

            // Update DS Asset Master
            if (!updDsAssetMstrBeforeActivateCur(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param, trkItemMap, svcMachMstrTMsg)) {

                return;
            }

        } else if (ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {

            /*************************************************************
             * 3. Check Serial Number and Merchandise Code
             ************************************************************/
            if (ZYPCommonFunc.hasValue(param.mdseCd) || ZYPCommonFunc.hasValue(param.serNum)) {

                String mdseCd = parentDsAssetMstrTMsg.mdseCd.getValue();
                String serNum = parentDsAssetMstrTMsg.serNum.getValue();

                if (ZYPCommonFunc.hasValue(param.mdseCd)) {

                    mdseCd = param.mdseCd.getValue();
                }

                if (ZYPCommonFunc.hasValue(param.serNum)) {

                    serNum = param.serNum.getValue();
                }

                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                queryParam.put(QRY_PARAM_MDSE_CD, mdseCd);
                queryParam.put(QRY_PARAM_SER_NUM, serNum);
                queryParam.put(QRY_PARAM_ACTV_ASSET_FLG_Y, ZYPConstant.FLG_ON_Y);
                queryParam.put(QRY_PARAM_ACTV_ASSET_FLG_N, ZYPConstant.FLG_OFF_N);

                List<DS_ASSET_MSTRTMsg> chkDsAssetMstrList = (List<DS_ASSET_MSTRTMsg>) ssmBatchClient.queryObjectList("getDsAssetMstrPk", queryParam);

                for (DS_ASSET_MSTRTMsg chkDsAssetMstrTMsg : chkDsAssetMstrList) {

                    // DS_ASSET_MSTR_PK <> Check Result.PK
                    if (!parentDsAssetMstrTMsg.dsAssetMstrPk.getValue().equals(chkDsAssetMstrTMsg.dsAssetMstrPk.getValue())) {

                        boolean chkErrFlg = true;

                        // Check P Message Update Detail List
                        for (int i = 0; i < paramPMsg.updDtlList.getValidCount(); i++) {

                            if (chkDsAssetMstrTMsg.dsAssetMstrPk.getValue().equals(paramPMsg.updDtlList.no(i).dsAssetMstrPk.getValue())) {

                                if (ZYPCommonFunc.hasValue(paramPMsg.updDtlList.no(i).mdseCd) && !paramPMsg.updDtlList.no(i).mdseCd.getValue().equals(chkDsAssetMstrTMsg.mdseCd.getValue())) {

                                    chkErrFlg = false;

                                } else if (ZYPCommonFunc.hasValue(paramPMsg.updDtlList.no(i).serNum) && !paramPMsg.updDtlList.no(i).serNum.getValue().equals(chkDsAssetMstrTMsg.serNum.getValue())) {

                                    chkErrFlg = false;
                                }
                            }
                        }

                        if (chkErrFlg) {

                            setParamErrMsg(param, NLZM2161E);
                            return;
                        }
                    }
                }
            }

            /*************************************************************
             * 4. Total Amount
             ************************************************************/
            BigDecimal totCurValAmt = BigDecimal.ZERO;
            BigDecimal totInitBookAmt = BigDecimal.ZERO;
            BigDecimal totOrigStdCostAmt = BigDecimal.ZERO;

            if (ZYPCommonFunc.hasValue(param.initBookAmt) || ZYPCommonFunc.hasValue(param.curValAmt)) {
                for (Map<String, Object> oldDsAssetMstrInfoMap : assetMstrInfoMapList) {
                    totCurValAmt = totCurValAmt.add((BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_CUR_VAL_AMT));
                    totInitBookAmt = totInitBookAmt.add((BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_INIT_BOOK_AMT));
                    // START 2018/09/21 J.Kim [QC#28341, ADD]
                    if (ZYPCommonFunc.hasValue((BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_ORIG_STD_COST_AMT))) {
                        totOrigStdCostAmt = totOrigStdCostAmt.add((BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_ORIG_STD_COST_AMT));
                    }
                    // END 2018/09/21 J.Kim [QC#28341, ADD]
                }
            }

            BigDecimal prntDsAssetMstrPk = param.prntDsAssetMstrPk.getValue();
            Map<String, Object> assetMstrInfoMap = doProcessAmountSummary(prntDsAssetMstrPk);
            if (assetMstrInfoMap == null || assetMstrInfoMap.isEmpty()) {
                setParamErrMsg(param, NLZM2160E);
                return;
            }

            /*************************************************************
             * 5. Adjustment Total Cost
             ************************************************************/
            Map<String, BigDecimal> splAmtMap = setCostAmt(param, assetMstrInfoMapList, totCurValAmt, totInitBookAmt, totOrigStdCostAmt);

            for (Map<String, Object> oldDsAssetMstrInfoMap : assetMstrInfoMapList) {

                oldDsAssetMstrTMsg = setDsAssetMstrTMsg(oldDsAssetMstrInfoMap);
                DS_ASSET_MSTRTMsg updDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
                EZDTMsg.copy(oldDsAssetMstrTMsg, null, updDsAssetMstrTMsg, null);

                /*************************************************************
                 * 6. Update DS Asset Master
                 ************************************************************/
                // Update DS Asset Master
                if (!updDsAssetMstrBeforeActivateParent(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param, trkItemMap, svcMachMstrTMsg, totOrigStdCostAmt, totCurValAmt, splAmtMap)) {

                    return;
                }
            }

            /*************************************************************
             * 7. New Total Amount
             ************************************************************/
            // START 2018/08/09 J.Kim [QC#26901,ADD]
            if (ASSET_STS.PENDING.equals(assetMstrInfoMapList.get(0).get(MAP_KEY_ASSET_STS_CD))) {
                if (ZYPCommonFunc.hasValue(param.initBookAmt) || ZYPCommonFunc.hasValue(param.curValAmt)) {
                    setAssetTrackingAmount(param, null, assetMstrInfoMap, prntDsAssetMstrPk, trkItemMap);
                }
            }
            // END 2018/08/09 J.Kim [QC#26901,ADD]
        }
    }

    /**
     * Asset Entry from AP
     * @param param NLZC305001_updDtlListPMsg
     * @param dsAssetStgngTMsg DS_ASSET_STGNGTMsg
     * @param assetTpTMsg ASSET_TPTMsg
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     */
    private void doProcessAssetEntryFromAP(NLZC305001_updDtlListPMsg param, DS_ASSET_STGNGTMsg dsAssetStgngTMsg, ASSET_TPTMsg assetTpTMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, Map<String, String> trkItemMap) {
        /*************************************************************
         * 1. Check API Parameter
         ************************************************************/
        if (!ZYPCommonFunc.hasValue(param.dsAssetStgngPk)) {

            setParamErrMsg(param, NLZM2356E);
            return;

        } else if (!ZYPCommonFunc.hasValue(param.assetTpCd)) {

            setParamErrMsg(param, NLZM2152E);
            return;

        } else if (!ZYPCommonFunc.hasValue(param.curValAmt)) {

            setParamErrMsg(param, NLZM2162E);
            return;
        }

        /*************************************************************
         * 2. Insert DS Asset Master
         ************************************************************/
        DS_ASSET_MSTRTMsg insDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();

        BigDecimal dsAssetMstrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_MSTR_SQ);

        // Set Asset Master
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.dsAssetMstrPk, dsAssetMstrPk);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.mdseCd, param.mdseCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.serNum, param.serNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.actvAssetFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetTpCd, param.assetTpCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.svcMachMstrPk, param.svcMachMstrPk);
        if (svcMachMstrTMsg != null) {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.shpgPlnNum, svcMachMstrTMsg.shpgPlnNum);
        }
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.revRecogDt, slsDt);

        // Set Depreciation
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.initBookAmt, param.curValAmt);
        // START 2018/07/31 J.Kim [QC#26902,ADD]
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.origInitBookAmt, param.curValAmt);
        // END 2018/07/31 J.Kim [QC#26902,ADD]
        String depcMthNum = getDefDepcMthNum(assetTpTMsg);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.depcMthNum, depcMthNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.depcStartDt, slsDt);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.depcCnt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.curValAmt, param.curValAmt);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.slsRepTocCd, param.slsRepTocCd);
        String firstDepcYrMth = getFirstDepcYrMth(insDsAssetMstrTMsg.depcStartDt.getValue(), assetTpTMsg);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.firstDepcYrMth, firstDepcYrMth);

        if (ZYPCommonFunc.hasValue(param.rsdlValAmt)) {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.rsdlValAmt, param.rsdlValAmt);
        } else if (ZYPCommonFunc.hasValue(param.mdseCd)) {
            MDSETMsg mdseTMsg = getMdse(glblCmpyCd, param.mdseCd.getValue());
            if (mdseTMsg != null) {
                ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.rsdlValAmt, mdseTMsg.rsdlValAmt);
            } else {
                ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.rsdlValAmt, BigDecimal.ZERO);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.rsdlValAmt, BigDecimal.ZERO);
        }

        // Set Asset Master Detail
        if (ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetStsCd, ASSET_STS.MERGED);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.prntDsAssetMstrPk, param.prntDsAssetMstrPk);
        } else {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetStsCd, ASSET_STS.PENDING);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.prntDsAssetMstrPk, insDsAssetMstrTMsg.dsAssetMstrPk);
        }

        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.cpoOrdNum, dsAssetStgngTMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.cpoDtlLineNum, dsAssetStgngTMsg.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.cpoDtlLineSubNum, dsAssetStgngTMsg.cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.dsOrdPosnNum, dsAssetStgngTMsg.dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.shipToCustAcctCd, dsAssetStgngTMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.shipToCustCd, dsAssetStgngTMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.sellToCustCd, dsAssetStgngTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.sldToCustLocCd, dsAssetStgngTMsg.soldToCustLocCd);
        // START 2018/01/18 J.Kim [QC#17985,MOD]
        // ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.invNum, dsAssetStgngTMsg.invNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.apVndInvNum, dsAssetStgngTMsg.apVndInvNum);
        // END 2018/01/18 J.Kim [QC#17985,MOD]
        // START 2018/04/10 J.Kim [QC#25381,ADD]
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.apVndInvLineNum, dsAssetStgngTMsg.apVndInvLineNum);
        // END 2018/04/10 J.Kim [QC#25381,ADD]
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.invDt, dsAssetStgngTMsg.invDt);
        // START 2018/01/18 J.Kim [QC#17985,MOD]
        // String prntVndCd = getParentVendorCode(insDsAssetMstrTMsg.invNum.getValue());
        String prntVndCd = getParentVendorCode(insDsAssetMstrTMsg.apVndInvNum.getValue());
        // END 2018/01/18 J.Kim [QC#17985,MOD]
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.vndCd, prntVndCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.manEntryFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.origStdCostAmt, dsAssetStgngTMsg.stdCostAmt);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.origCsmpPrcAmt, param.csmpPrcAmt);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.origOrdAdjAmt, param.ordAdjAmt);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCalcStsCd, ASSET_CALC_STS.CALCULATED);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.baseCmptFlg, dsAssetStgngTMsg.baseCmptFlg);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetPostFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.svcConfigMstrPk, dsAssetStgngTMsg.toSvcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.procModeCd, dsAssetStgngTMsg.procModeCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.totAssetQty, dsAssetStgngTMsg.totAssetQty);

        // Set 9 Segments
        if (ZYPCommonFunc.hasValue(dsAssetStgngTMsg.apVndInvSqNum)) {
            set9Segments(insDsAssetMstrTMsg, dsAssetStgngTMsg, assetTpTMsg);
        }
        // START 2018/01/15 J.Kim [QC#21965,MOD]
        if (ZYPCommonFunc.hasValue(dsAssetStgngTMsg.apVndInvNum)) {
            SHIP_TO_CUSTTMsg shipToCustTMsg = getShipToCustByApVndInvNum(dsAssetStgngTMsg.apVndInvNum.getValue());
            if (shipToCustTMsg != null) {
                ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.firstLineAddr, shipToCustTMsg.firstLineAddr);
                ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.scdLineAddr, shipToCustTMsg.scdLineAddr);
                ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.thirdLineAddr, shipToCustTMsg.thirdLineAddr);
                ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.frthLineAddr, shipToCustTMsg.frthLineAddr);
                ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.ctyAddr, shipToCustTMsg.ctyAddr);
                ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.stCd, shipToCustTMsg.stCd);
                ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.postCd, shipToCustTMsg.postCd);
            }
        }
        // END 2018/01/15 J.Kim [QC#21965,MOD]

        // START 2018/04/24 J.Kim [QC#25791,ADD]
        setDefaultExpenseCOA(insDsAssetMstrTMsg);
        // END 2018/04/24 J.Kim [QC#25791,ADD]

        S21ApiTBLAccessor.insert(insDsAssetMstrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insDsAssetMstrTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", insDsAssetMstrTMsg.dsAssetMstrPk.getValue().toString());
            setDBAcsErrMsg(param, NASM0007E, new String[] {TBL_DS_ASSET_MSTR, pKey });

        }

        param.dsAssetMstrPk.setValue(dsAssetMstrPk);

        // START 2018/08/23 J.Kim [QC#22267,ADD]
        BigDecimal prntDsAssetMstrPk = insDsAssetMstrTMsg.prntDsAssetMstrPk.getValue();
        param.prntDsAssetMstrPk.setValue(prntDsAssetMstrPk);
        if (prntDsAssetMstrPk.compareTo(insDsAssetMstrTMsg.dsAssetMstrPk.getValue()) == 0) {
            param.curValAmt.clear();
            doProcessSummary(param, insDsAssetMstrTMsg, trkItemMap);
        }
        // END 2018/08/23 J.Kim [QC#22267,ADD]
    }

    /**
     * Asset Entry From AP
     * @param param NLZC305001_updDtlListPMsg
     * @param assetTpTMsg ASSET_TPTMsg
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     */
    private void doProcessAssetManEntry(NLZC305001_updDtlListPMsg param, ASSET_TPTMsg assetTpTMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, Map<String, String> trkItemMap) {

        /*************************************************************
         * 1. Check API Parameter
         ************************************************************/
        if (!ZYPCommonFunc.hasValue(param.assetTpCd)) {

            setParamErrMsg(param, NLZM2152E);
            return;

        } else if (!ZYPCommonFunc.hasValue(param.depcMthNum)) {

            setParamErrMsg(param, NLZM2361E);
            return;

        } else if (!ZYPCommonFunc.hasValue(param.curValAmt)) {

            setParamErrMsg(param, NLZM2162E);
            return;
        }

        /*************************************************************
         * 2. Get Depreciation Start Date & First Depreciation Year
         * Month
         ************************************************************/
        String depcStartDt = slsDt;

        if (svcMachMstrTMsg != null && ZYPCommonFunc.hasValue(svcMachMstrTMsg.istlDt)) {

            depcStartDt = svcMachMstrTMsg.istlDt.getValue();
        }

        String firstDepcYrMth = getFirstDepcYrMth(depcStartDt, assetTpTMsg);

        /*************************************************************
         * 3. Get Residual Value
         ************************************************************/
        BigDecimal rsdlValAmt = BigDecimal.ZERO;

        if (ZYPCommonFunc.hasValue(param.rsdlValAmt)) {

            rsdlValAmt = param.rsdlValAmt.getValue();

        } else if (svcMachMstrTMsg != null) {

            MDSETMsg mdseTMsg = getMdse(glblCmpyCd, svcMachMstrTMsg.mdseCd.getValue());

            if (mdseTMsg != null) {

                rsdlValAmt = mdseTMsg.rsdlValAmt.getValue();

            }
        }

        /*************************************************************
         * 4. Get Asset Transaction
         ************************************************************/
        DS_COND_CONSTTMsg dsCondConstTMsg = getDsCondConst(NLZC3050_ASSET_CRAT, assetTpTMsg.assetTpCd.getValue());

        if (dsCondConstTMsg == null) {

            setParamErrMsg(param, NLZM2355E);
            return;

        }

        /*************************************************************
         * 5. Create DS Asset Master
         ************************************************************/
        DS_ASSET_MSTRTMsg insDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();

        BigDecimal dsAssetMstrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_MSTR_SQ);
        // Set Asset Master
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.dsAssetMstrPk, dsAssetMstrPk);
        if (svcMachMstrTMsg != null) {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.mdseCd, svcMachMstrTMsg.mdseCd);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.serNum, svcMachMstrTMsg.serNum);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.shpgPlnNum, svcMachMstrTMsg.shpgPlnNum);
        } else {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.mdseCd, param.mdseCd);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.serNum, param.serNum);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.shpgPlnNum, param.shpgPlnNum);
        }
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.actvAssetFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetTpCd, param.assetTpCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.svcMachMstrPk, param.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.revRecogDt, slsDt);
        // Depreciation
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.initBookAmt, param.curValAmt);
        // START 2018/07/31 J.Kim [QC#26902,ADD]
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.origInitBookAmt, param.curValAmt);
        // END 2018/07/31 J.Kim [QC#26902,ADD]
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.depcMthNum, param.depcMthNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.depcStartDt, slsDt);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.depcCnt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.curValAmt, param.curValAmt);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.slsRepTocCd, param.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.firstDepcYrMth, firstDepcYrMth);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.rsdlValAmt, rsdlValAmt);
        // Asset Master Detail
        if (ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetStsCd, ASSET_STS.MERGED);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.prntDsAssetMstrPk, param.prntDsAssetMstrPk);
        } else {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetStsCd, ASSET_STS.PENDING);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.prntDsAssetMstrPk, dsAssetMstrPk);
        }
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.cpoOrdNum, param.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.cpoDtlLineNum, param.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.cpoDtlLineSubNum, param.cpoDtlLineSubNum);
        if (ASSET_SRC_TP.PROCURED.equals(param.assetSrcTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.sellToCustCd, param.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.vndCd, param.vndCd);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.firstLineAddr, param.firstLineAddr);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.ctyAddr, param.ctyAddr);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.stCd, param.stCd);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.postCd, param.postCd);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.postCd, param.postCd);
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.procModeCd, "73");
        } else if (ASSET_SRC_TP.LEASED.equals(param.assetSrcTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.procModeCd, "74");
            if (svcMachMstrTMsg != null) {
                ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk);
            } else {
                ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.svcConfigMstrPk, param.svcConfigMstrPk);
            }
        }
        // START 2018/01/18 J.Kim [QC#17985,MOD]
        // ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.invNum, param.invNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.apVndInvNum, param.invNum);
        // END 2018/01/18 J.Kim [QC#17985,MOD]
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.invDt, param.invDt);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.poOrdNum, param.poOrdNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.dsAssetDescTxt, param.assetDescTxt);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.manEntryFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.origStdCostAmt, param.curValAmt);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCalcStsCd, ASSET_CALC_STS.NOT_CONFIG_CALCULATED);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.baseCmptFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetPostFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetLeaseNum, param.assetLeaseNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.leaseStartDt, param.leaseStartDt);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.leaseEndDt, param.leaseEndDt);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.totAssetQty, param.totAssetQty);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaCmpyCd, param.coaCmpyCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaBrCd, param.coaBrCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaCcCd, param.coaCcCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaAcctCd, param.coaAcctCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaProdCd, param.coaProdCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaChCd, param.coaChCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaAfflCd, param.coaAfflCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaProjCd, param.coaProjCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaExtnCd, param.coaExtnCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCoaCmpyCd, param.assetCoaCmpyCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCoaBrCd, param.assetCoaBrCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCoaCcCd, param.assetCoaCcCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCoaProdCd, param.assetCoaProdCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCoaChCd, param.assetCoaChCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCoaAfflCd, param.assetCoaAfflCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCoaProjCd, param.assetCoaProjCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCoaExtnCd, param.assetCoaExtnCd);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.lastBillDt, param.lastBillDt);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.bllgInvNum, param.bllgInvNum);
        // START 2018/06/25 J.Kim [QC#24844, ADD]
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.custIssPoNum, param.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.dsContrNum, param.dsContrNum);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.contrEffFromDt, param.contrEffFromDt);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.contrEffThruDt, param.contrEffThruDt);
        // END 2018/06/25 J.Kim [QC#24844, ADD]

        S21ApiTBLAccessor.insert(insDsAssetMstrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insDsAssetMstrTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", dsAssetMstrPk.toString());
            setDBAcsErrMsg(param, NWAM0317W, new String[] {TBL_DS_ASSET_MSTR, pKey });
            return;
        }

        /*************************************************************
         * 5. Create DS Asset Transaction
         ************************************************************/
        DS_ASSET_TRXTMsg insrtDsAssetTrxTMsg = new DS_ASSET_TRXTMsg();

        BigDecimal dsAssetTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_TRX_SQ);

        // Set parameters Asset Transaction Data
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetTrxPk, dsAssetTrxPk);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTrxDt, slsDt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetMstrPk, insDsAssetMstrTMsg.dsAssetMstrPk);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.mdseCd, insDsAssetMstrTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.serNum, insDsAssetMstrTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTpCd, insDsAssetMstrTMsg.assetTpCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxCd, dsCondConstTMsg.dsCondConstValTxt_01);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxRsnCd, dsCondConstTMsg.dsCondConstValTxt_02);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.slsRepTocCd, insDsAssetMstrTMsg.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.billToCustCd, param.billToCustCd);
        // START 2018/01/18 J.Kim [QC#17985,MOD]
        // ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.invNum, param.invNum);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.apVndInvNum, param.invNum);
        // END 2018/01/18 J.Kim [QC#17985,MOD]
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaProdCd, param.coaProdCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaAcctCd, param.coaAcctCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaProjCd, param.coaProjCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaBrCd, param.coaBrCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaChCd, param.coaChCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaCcCd, param.coaCcCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfReqFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.sysSrcCd, SYS_SRC.S21_ASSET);
        // Set parameters Asset Transaction Depreciation
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.initBookAmt, insDsAssetMstrTMsg.initBookAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.curValAmt, insDsAssetMstrTMsg.curValAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcCnt, insDsAssetMstrTMsg.depcCnt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcMthNum, insDsAssetMstrTMsg.depcMthNum);

        S21ApiTBLAccessor.insert(insrtDsAssetTrxTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insrtDsAssetTrxTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_TRX_PK, " : ", insrtDsAssetTrxTMsg.dsAssetTrxPk.getValue().toString());
            setDBAcsErrMsg(param, NWAM0317W, new String[] {TBL_DS_ASSET_TRX, pKey });
        }

        // START 2018/0822 J.Kim [QC#22267, ADD]
        ZYPEZDItemValueSetter.setValue(param.dsAssetMstrPk, insDsAssetMstrTMsg.dsAssetMstrPk);
        ZYPEZDItemValueSetter.setValue(param.prntDsAssetMstrPk, insDsAssetMstrTMsg.prntDsAssetMstrPk);
        // END 2018/06/25 J.Kim [QC#22267, ADD]
    }

    /**
     * Merge
     * @param param NLZC305001_updDtlListPMsg
     * @param parentDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param trkItemMap Map<String, String>
     */
    private void doProcessMerge(NLZC305001_updDtlListPMsg param, DS_ASSET_MSTRTMsg parentDsAssetMstrTMsg, Map<String, String> trkItemMap) {

        /*************************************************************
         * 1. Check API Parameter
         ************************************************************/
        if (!ZYPCommonFunc.hasValue(param.dsAssetMstrPk)) {

            setParamErrMsg(param, NLZM2153E);
            return;
        }

        if (!ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {

            setParamErrMsg(param, NLZM2511E);
            return;
        }

        /*************************************************************
         * 2. Get Parent DS Asset Master
         ************************************************************/
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(QRY_PARAM_PRNT_DS_ASSET_MSTR_PK, param.dsAssetMstrPk.getValue());
        queryParam.put(QRY_PARAM_ACTV_ASSET_FLG_N, ZYPConstant.FLG_OFF_N);

        List<Map<String, Object>> assetMstrInfoMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getParentAssetMstrForMerge", queryParam);

        if (assetMstrInfoMapList == null || assetMstrInfoMapList.isEmpty()) {

            setParamErrMsg(param, NLZM2160E);
            return;
        }

        /*************************************************************
         * 3-1. Amount Summary
         ************************************************************/
        doProcessAmount(param, parentDsAssetMstrTMsg, assetMstrInfoMapList, trkItemMap);

        /*************************************************************
         * 3-2. Update DS Asset Master for Parent
         ************************************************************/
        for (Map<String, Object> updMap : assetMstrInfoMapList) {

            DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg = setDsAssetMstrTMsg(updMap);
            DS_ASSET_MSTRTMsg updDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
            EZDTMsg.copy(oldDsAssetMstrTMsg, null, updDsAssetMstrTMsg, null);

            // Update DS Asset Master
            if (!updDsAssetMstrMerge(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, parentDsAssetMstrTMsg, param, trkItemMap)) {

                return;
            }

            if (ZYPConstant.FLG_ON_Y.equals(parentDsAssetMstrTMsg.assetPostFlg.getValue())) {
                /*************************************************************
                 * 3. Get Asset Transaction
                 ************************************************************/
                DS_COND_CONSTTMsg dsCondConstTMsg = getDsCondConst(NLZC3050_ASSET_IMPT, oldDsAssetMstrTMsg.assetTpCd.getValue());
    
                /*************************************************************
                 * 4. Create DS Asset Transaction
                 ************************************************************/
                // Create DS Asset Transaction
                insertDsAssetTrxAssetActivate(oldDsAssetMstrTMsg, updDsAssetMstrTMsg, param, dsCondConstTMsg);
            }

        }
    }

    /**
     * doProcessSummary
     * @param param NLZC305001_updDtlListPMsg
     * @param parentDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param trkItemMap Map<String, String>
     */
    private void doProcessSummary(NLZC305001_updDtlListPMsg param, DS_ASSET_MSTRTMsg prntDsAssetMstrTMsg, Map<String, String> trkItemMap) {

        /*************************************************************
         * 1. Check API Parameter
         ************************************************************/
        if (!ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {
            setParamErrMsg(param, NLZM2511E);
            return;
        }

        /*************************************************************
         * 2. Amount Summary
         ************************************************************/
        BigDecimal prntDsAssetMstrPk = param.prntDsAssetMstrPk.getValue();
        if (PROC_MODE.DEPRECIATION_MONTHLY.equals(param.procModeCd.getValue())) {
            Map<String, Object> assetMstrInfoMap = new HashMap<String, Object>();
            assetMstrInfoMap.put(MAP_KEY_CUR_VAL_AMT, param.curValAmt.getValue());
            assetMstrInfoMap.put(MAP_KEY_INIT_BOOK_AMT, param.initBookAmt.getValue());
            setAssetTrackingAmount(param, prntDsAssetMstrTMsg, assetMstrInfoMap, prntDsAssetMstrPk, trkItemMap);
        } else {
            Map<String, Object> assetMstrInfoMap = new HashMap<String, Object>();
            if (ZYPCommonFunc.hasValue(param.curValAmt) || ZYPCommonFunc.hasValue(param.initBookAmt)) {
                assetMstrInfoMap.put(MAP_KEY_CUR_VAL_AMT, param.curValAmt.getValue());
                assetMstrInfoMap.put(MAP_KEY_INIT_BOOK_AMT, param.initBookAmt.getValue());
            } else {
                assetMstrInfoMap = null;
            }
            setAssetTrackingData(param, prntDsAssetMstrTMsg, "prntDsAssetMstrPk", null, prntDsAssetMstrTMsg.prntDsAssetMstrPk.getValue(), trkItemMap);
            setAssetTrackingData(param, prntDsAssetMstrTMsg, "depcStartDt", EMPTY_STRING, prntDsAssetMstrTMsg.depcStartDt.getValue(), trkItemMap);
            setAssetTrackingData(param, prntDsAssetMstrTMsg, "cpoOrdNum", EMPTY_STRING, prntDsAssetMstrTMsg.cpoOrdNum.getValue(), trkItemMap);
            setAssetTrackingAmount(param, prntDsAssetMstrTMsg, assetMstrInfoMap, prntDsAssetMstrPk, trkItemMap);
        }
    }

    /**
     * doProcessAmount
     * @param param NLZC305001_updDtlListPMsg
     * @param parentDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param trkItemMap Map<String, String>
     */
    private void doProcessAmount(NLZC305001_updDtlListPMsg param, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, List<Map<String, Object>> assetMstrInfoMapList, Map<String, String> trkItemMap) {

        /*************************************************************
         * 1. Old Amount Summary
         ************************************************************/
        BigDecimal curValAmt = BigDecimal.ZERO;
        BigDecimal initBookAmt = BigDecimal.ZERO;
        for (Map<String, Object> updMap : assetMstrInfoMapList) {
            BigDecimal addInitBookAmt = (BigDecimal) updMap.get(MAP_KEY_INIT_BOOK_AMT);
            BigDecimal addCurValAmt = (BigDecimal) updMap.get(MAP_KEY_CUR_VAL_AMT);

            curValAmt = curValAmt.add(addCurValAmt);
            initBookAmt = initBookAmt.add(addInitBookAmt);
        }

        /*************************************************************
         * 2. New Amount Summary
         ************************************************************/
        BigDecimal prntDsAssetMstrPk = param.prntDsAssetMstrPk.getValue();
        Map<String, Object> assetMstrInfoMap = doProcessAmountSummary(prntDsAssetMstrPk);
        if (assetMstrInfoMap == null || assetMstrInfoMap.isEmpty()) {
            setParamErrMsg(param, NLZM2160E);
            return;
        }

        /*************************************************************
         * 3. Create DS Asset Tracking - Amount
         ************************************************************/
        BigDecimal prntInitBookAmt = (BigDecimal) assetMstrInfoMap.get(MAP_KEY_INIT_BOOK_AMT);
        BigDecimal prntCurValAmt = (BigDecimal) assetMstrInfoMap.get(MAP_KEY_CUR_VAL_AMT);

        // Insert Asset Tracking Data
        BigDecimal newCurValAmt = prntCurValAmt.add(curValAmt);
        BigDecimal newInitBookAmt  = prntInitBookAmt.add(initBookAmt);

        ZYPEZDItemValueSetter.setValue(param.xxYesNoCd, ZYPConstant.FLG_OFF_N);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "ASSET_VALUE", prntInitBookAmt, newInitBookAmt, trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "NBV", prntInitBookAmt, newCurValAmt, trkItemMap);
        param.xxYesNoCd.clear();

    }

    /**
     * doProcessAmountSummary
     * @param dsAssetMstrPk BigDecimal
     */
    private Map<String, Object> doProcessAmountSummary(BigDecimal dsAssetMstrPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(QRY_PARAM_PRNT_DS_ASSET_MSTR_PK, dsAssetMstrPk);

        Map<String, Object> assetMstrInfoMap = (Map<String, Object>) ssmBatchClient.queryObject("getAmount", queryParam);
        return assetMstrInfoMap;
    }

    /**
     * Get Default Depreciation Month Number
     * @param Map<String, Object> assetDtlInfoMap
     * @param assetTpTMsg ASSET_TPTMsg
     * @return String depcMthNum
     */
    private String getDefDepcMthNum(ASSET_TPTMsg assetTpTMsg) {

        Map<String, Object> queryParamAsset = new HashMap<String, Object>();
        queryParamAsset.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParamAsset.put(QRY_PARAM_ASSET_TP_CD, assetTpTMsg.assetTpCd.getValue());
        queryParamAsset.put(QRY_PARAM_EFF_FROM_DT, slsDt);
        queryParamAsset.put(QRY_PARAM_EFF_THRU_DT, slsDt);

        String depcMthNum = (String) ssmBatchClient.queryObject("getDefDepcMthNum", queryParamAsset);

        // Get from Asset Type or Number Constant
        if (!ZYPCommonFunc.hasValue(depcMthNum) || BigDecimal.ZERO.equals(new BigDecimal(depcMthNum))) {

            if (ZYPCommonFunc.hasValue(assetTpTMsg.defDepcMthNum)) {

                depcMthNum = assetTpTMsg.defDepcMthNum.getValue();

            } else {

                depcMthNum = ZYPCodeDataUtil.getNumConstValue(DEF_DEPC_MTH_NUM, glblCmpyCd).toString();
            }
        }

        return depcMthNum;
    }

    /**
     * Get CM_INV_ACCT_HDR.PRNT_VND_CD
     * @param invNum String
     * @return String
     */
    private String getParentVendorCode(String invNum) {

        Map<String, Object> queryParamAsset = new HashMap<String, Object>();
        queryParamAsset.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParamAsset.put(QRY_PARAM_AP_VND_INV_NUM, invNum);

        String prntVndCd = (String) ssmBatchClient.queryObject("getParentVendorCode", queryParamAsset);

        return prntVndCd;
    }

    /**
     * set 9 Segments
     * @param insDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param dsAssetStgngTMsg DS_ASSET_STGNGTMsg
     * @param assetTpTMsg ASSET_TPTMsg
     * @return String
     */
    private void set9Segments(DS_ASSET_MSTRTMsg insDsAssetMstrTMsg, DS_ASSET_STGNGTMsg dsAssetStgngTMsg, ASSET_TPTMsg assetTpTMsg) {

        // Get PO
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        // START 2018/01/18 J.Kim [QC#17985,MOD]
        // queryParam.put(QRY_PARAM_AP_VND_INV_NUM, dsAssetStgngTMsg.invNum.getValue());
        queryParam.put(QRY_PARAM_AP_VND_INV_NUM, dsAssetStgngTMsg.apVndInvNum.getValue());
        // END 2018/01/18 J.Kim [QC#17985,MOD]
        queryParam.put(QRY_PARAM_AP_VND_INV_SQ_NUM, dsAssetStgngTMsg.apVndInvSqNum.getValue());
        // START 2018/04/10 J.Kim [QC#25381,ADD]
        queryParam.put(QRY_PARAM_AP_VND_INV_LINE_NUM, dsAssetStgngTMsg.apVndInvLineNum.getValue());
        // END 2018/04/10 J.Kim [QC#25381,ADD]

        Map<String, String> poInfoMap = (Map<String, String>) ssmBatchClient.queryObject("getAPInvoiceOrdInfo", queryParam);

        if (poInfoMap == null || poInfoMap.isEmpty()) {

            return;
        }

        // START 2018/01/18 J.Kim [QC#17985,ADD]
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.poOrdNum, poInfoMap.get(MAP_KEY_PO_ORD_NUM));
        // END 2018/01/18 J.Kim [QC#17985,ADD]
        // START 2018/04/10 J.Kim [QC#25381,ADD]
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.dsAssetDescTxt, poInfoMap.get(MAP_KEY_MDSE_DESC_SHORT_TXT));
        // END 2018/04/10 J.Kim [QC#25381,ADD]

        if (ZYPCommonFunc.hasValue(poInfoMap.get(MAP_KEY_PO_ORD_NUM)) && ZYPCommonFunc.hasValue(poInfoMap.get(MAP_KEY_PO_ORD_DTL_LINE_NUM))) {
         // Get PO Segments
            queryParam = new HashMap<String, Object>();
            queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(QRY_PARAM_PO_ORD_NUM, poInfoMap.get(MAP_KEY_PO_ORD_NUM));
            queryParam.put(QRY_PARAM_PO_ORD_DTL_LINE_NUM, poInfoMap.get(MAP_KEY_PO_ORD_DTL_LINE_NUM));
            queryParam.put(QRY_PARAM_PO_ACCT_TP_CD, PO_ACCT_TP.CHARGE);

            Map<String, String> poCoaMap = (Map<String, String>) ssmBatchClient.queryObject("getPOCoaSegments", queryParam);

            if (poCoaMap == null || poCoaMap.isEmpty()) {

                return;
            }

            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaCmpyCd, poCoaMap.get(MAP_KEY_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaBrCd, poCoaMap.get(MAP_KEY_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaCcCd, poCoaMap.get(MAP_KEY_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaProdCd, poCoaMap.get(MAP_KEY_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaChCd, poCoaMap.get(MAP_KEY_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaAfflCd, poCoaMap.get(MAP_KEY_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaProjCd, poCoaMap.get(MAP_KEY_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaExtnCd, poCoaMap.get(MAP_KEY_COA_EXTN_CD));
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCoaCmpyCd, poCoaMap.get(MAP_KEY_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCoaBrCd, poCoaMap.get(MAP_KEY_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCoaCcCd, poCoaMap.get(MAP_KEY_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCoaProdCd, poCoaMap.get(MAP_KEY_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCoaChCd, poCoaMap.get(MAP_KEY_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCoaAfflCd, poCoaMap.get(MAP_KEY_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCoaProjCd, poCoaMap.get(MAP_KEY_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCoaExtnCd, poCoaMap.get(MAP_KEY_COA_EXTN_CD));

            // Get COA_ACCT_CD
            queryParam = new HashMap<String, Object>();
            queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(QRY_PARAM_ASSET_TP_CD, assetTpTMsg.assetTpCd.getValue());
            queryParam.put(QRY_PARAM_EFF_FROM_DT, slsDt);
            queryParam.put(QRY_PARAM_EFF_THRU_DT, slsDt);

            Map<String, String> coaAcctMap = (Map<String, String>) ssmBatchClient.queryObject("getCoaAcctCd", queryParam);

            if (coaAcctMap == null || coaAcctMap.isEmpty()) {

                return;
            }

            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaAcctCd, coaAcctMap.get(MAP_KEY_DEPC_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.assetCoaAcctCd, coaAcctMap.get(MAP_KEY_ASSET_COA_ACCT_CD));
        }
    }

    /**
     * Get First Depreciation Year Month
     * @param depcStartDt String
     * @param assetTpTMsg ASSET_TPTMsg
     * @return String
     */
    private String getFirstDepcYrMth(String depcStartDt, ASSET_TPTMsg assetTpTMsg) {

        String firstDepcYrMth = ZYPDateUtil.DateFormatter(depcStartDt, "yyyyMMdd", "yyyyMM");
        BigDecimal depcStartDay = new BigDecimal(ZYPDateUtil.DateFormatter(depcStartDt, "yyyyMMdd", "dd"));

        // Depreciation Start Month Number
        BigDecimal depcStartMthNum = BigDecimal.ZERO;

        if (ZYPCommonFunc.hasValue(assetTpTMsg.depcStartMthNum)) {

            depcStartMthNum = new BigDecimal(assetTpTMsg.depcStartMthNum.getValue());
        }

        if (BigDecimal.ZERO.equals(depcStartMthNum) && ZYPCommonFunc.hasValue(assetTpTMsg.depcStartLimitDay)) {

            if (depcStartDay.compareTo(new BigDecimal(assetTpTMsg.depcStartLimitDay.getValue())) > 0) {

                depcStartMthNum = depcStartMthNum.add(BigDecimal.ONE);
            }
        }

        // First Depreciation Year Month
        if (depcStartMthNum.compareTo(BigDecimal.ZERO) > 0) {

            String firstDepcYr = ZYPDateUtil.DateFormatter(firstDepcYrMth, "yyyyMM", "yyyy");
            String firstDepcMth = ZYPDateUtil.DateFormatter(firstDepcYrMth, "yyyyMM", "MM");

            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(firstDepcYr), Integer.parseInt(firstDepcMth) - 1, 01);

            calendar.add(Calendar.MONTH, depcStartMthNum.intValue());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
            firstDepcYrMth = dateFormat.format(calendar.getTime());
        }

        return firstDepcYrMth;
    }

    /**
     * Get DS Asset Master
     * @param dsAssetMstrPk BigDecimal
     * @return DS_ASSET_MSTRTMsg
     */
    private DS_ASSET_MSTRTMsg getDsAssetMstr(BigDecimal dsAssetMstrPk) {

        DS_ASSET_MSTRTMsg dsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(dsAssetMstrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsAssetMstrTMsg.dsAssetMstrPk, dsAssetMstrPk);

        return (DS_ASSET_MSTRTMsg) S21ApiTBLAccessor.findByKey(dsAssetMstrTMsg);
    }

    /**
     * Get Service Machine Master
     * @param svcMachMstrPk BigDecimal
     * @return SVC_MACH_MSTRTMsg
     */
    private SVC_MACH_MSTRTMsg getSvcMachMstr(BigDecimal svcMachMstrPk) {

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, svcMachMstrPk);

        return (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(svcMachMstrTMsg);
    }

    /**
     * Get Ship To Customer
     * @param apVndInvNum String
     * @return SHIP_TO_CUSTTMsg
     */
    private SHIP_TO_CUSTTMsg getShipToCustByApVndInvNum(String apVndInvNum) {

        // Get Address
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(QRY_PARAM_AP_VND_INV_NUM, apVndInvNum);
        Map<String, String> lineAddress = (Map<String, String>) ssmBatchClient.queryObject("getLineAddr", queryParam);
        if (lineAddress == null || lineAddress.isEmpty()) {
            return null;
        }

        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.firstLineAddr, lineAddress.get(MAP_KEY_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.scdLineAddr, lineAddress.get(MAP_KEY_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.thirdLineAddr, lineAddress.get(MAP_KEY_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.frthLineAddr, lineAddress.get(MAP_KEY_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.ctyAddr, lineAddress.get(MAP_KEY_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.stCd, lineAddress.get(MAP_KEY_ST_CD));
        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.postCd, lineAddress.get(MAP_KEY_POST_CD));
        return shipToCustTMsg;
    }

    /**
     * Get Ship To Customer
     * @param shipToCust String
     * @return SHIP_TO_CUSTTMsg
     */
    private SHIP_TO_CUSTTMsg getShipToCust(String shipToCust) {

        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        shipToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        shipToCustTMsg.setConditionValue("shipToCustCd01", shipToCust);
        shipToCustTMsg.setSQLID("004");

        SHIP_TO_CUSTTMsgArray shipToCustTMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipToCustTMsg);
        if (shipToCustTMsgArray.length() == 0) {
            return null;
        }
        return (SHIP_TO_CUSTTMsg) shipToCustTMsgArray.get(0);
    }

    /**
     * Get DS Asset Staging
     * @param dsAssetStgngPk BigDecimal
     * @return DS_ASSET_STGNGTMsg
     */
    private DS_ASSET_STGNGTMsg getDsAssetStgng(BigDecimal dsAssetStgngPk) {

        DS_ASSET_STGNGTMsg dsAssetStgngTMsg = new DS_ASSET_STGNGTMsg();
        ZYPEZDItemValueSetter.setValue(dsAssetStgngTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsAssetStgngTMsg.dsAssetStgngPk, dsAssetStgngPk);

        return (DS_ASSET_STGNGTMsg) S21ApiTBLAccessor.findByKey(dsAssetStgngTMsg);
    }

    /**
     * Get Service config Master
     * @param svcConfigMstrPk BigDecimal
     * @return DS_ASSET_MSTRTMsg
     */
    private SVC_CONFIG_MSTRTMsg getSvcConfigMstr(BigDecimal svcConfigMstrPk) {

        SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcConfigMstrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcConfigMstrTMsg.svcConfigMstrPk, svcConfigMstrPk);

        return (SVC_CONFIG_MSTRTMsg) S21ApiTBLAccessor.findByKey(svcConfigMstrTMsg);
    }

    /**
     * Get Asset Type
     * @param assetTpCd String
     * @return ASSET_TPTMsg
     */
    private ASSET_TPTMsg getAssetTp(String assetTpCd) {

        ASSET_TPTMsg assetTpTMsg = new ASSET_TPTMsg();
        ZYPEZDItemValueSetter.setValue(assetTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(assetTpTMsg.assetTpCd, assetTpCd);

        return (ASSET_TPTMsg) S21ApiTBLAccessor.findByKey(assetTpTMsg);
    }

    /**
     * Get Inventory Transaction
     * @param invtyTrxPk BigDecimal
     * @return INVTY_TRXTMsg
     */
    private INVTY_TRXTMsg getInvtyTrx(BigDecimal invtyTrxPk) {

        INVTY_TRXTMsg invtyTrxTMsg = new INVTY_TRXTMsg();
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invtyTrxTMsg.invtyTrxPk, invtyTrxPk);

        return (INVTY_TRXTMsg) S21ApiTBLAccessor.findByKey(invtyTrxTMsg);
    }

    /**
     * Get DS Condition Constant
     * @param dsCondConstGrpId String
     * @param dsCondConstCd String
     * @return DS_COND_CONSTTMsg
     */
    private DS_COND_CONSTTMsg getDsCondConst(String dsCondConstGrpId, String dsCondConstCd) {

        DS_COND_CONSTTMsg dsCondConstTMsg = new DS_COND_CONSTTMsg();

        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.dsCondConstGrpId, dsCondConstGrpId);
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.dsCondConstCd, dsCondConstCd);

        return (DS_COND_CONSTTMsg) S21ApiTBLAccessor.findByKey(dsCondConstTMsg);
    }

    /**
     * Get Merchandise
     * @param paramGlblCmpyCd String
     * @param paramMdseCd String
     * @return MDSETMsg
     */
    private MDSETMsg getMdse(String paramGlblCmpyCd, String paramMdseCd) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, paramGlblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, paramMdseCd);

        return (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);
    }

    /**
     * Get Customer purchase order detail
     * @param cpoOrdNum String
     * @param cpoDtlLineNum String
     * @param cpoDtlLineSubNum String
     * @return CPO_DTLTMsg
     */
    private CPO_DTLTMsg getCpoDtl(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);

        return (CPO_DTLTMsg) S21ApiTBLAccessor.findByKey(cpoDtlTMsg);
    }

    /**
     * Update DS Asset Master. Revenue Recognition Sales and Revenue
     * Recognition Expense
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     * @param trkItemMap Map<String, String>
     * @return boolean
     */
    private boolean updDsAssetMstrRev(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, NLZC305001_updDtlListPMsg param, Map<String, String> trkItemMap) {

        // START 2018/02/08 J.Kim [QC#24024,ADD]
        DS_ASSET_STGNGTMsg dsAssetStgngTMsg = null;
        if (ZYPCommonFunc.hasValue(param.dsAssetStgngPk)) {
            dsAssetStgngTMsg = getDsAssetStgng(param.dsAssetStgngPk.getValue());
        }

        if (dsAssetStgngTMsg != null) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoOrdNum, dsAssetStgngTMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineNum, dsAssetStgngTMsg.cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineSubNum, dsAssetStgngTMsg.cpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsOrdPosnNum, dsAssetStgngTMsg.dsOrdPosnNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.shipToCustAcctCd, dsAssetStgngTMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.shipToCustCd, dsAssetStgngTMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.sellToCustCd, dsAssetStgngTMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.sldToCustLocCd, dsAssetStgngTMsg.soldToCustLocCd);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.apVndInvNum, dsAssetStgngTMsg.apVndInvNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.apVndInvLineNum, dsAssetStgngTMsg.apVndInvLineNum);
            // START 2018/06/20 J.Kim [QC#24936, ADD]
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.procModeCd, dsAssetStgngTMsg.procModeCd);
            // END 2018/06/20 J.Kim [QC#24936, ADD]
            // START 2018/06/25 J.Kim [QC#24844, ADD]
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsContrNum, dsAssetStgngTMsg.dsContrNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.contrEffFromDt, dsAssetStgngTMsg.contrEffFromDt);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.contrEffThruDt, dsAssetStgngTMsg.contrEffThruDt);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.custIssPoNum, dsAssetStgngTMsg.custIssPoNum);
            // END 2018/06/25 J.Kim [QC#24844, ADD]
        }
        // END 2018/02/08 J.Kim [QC#24024,ADD]

        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.actvAssetFlg, ZYPConstant.FLG_OFF_N);

        if (ZYPCommonFunc.hasValue(param.svcMachMstrPk)) {

            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.svcMachMstrPk, param.svcMachMstrPk);
        }

        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.revRecogDt, slsDt);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.curValAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.slsRepTocCd, param.slsRepTocCd);
        // START 2018/01/23 J.Kim [QC#23418,MOD]
        // ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetStsCd, ASSET_STS.CAPITALIZED);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetStsCd, ASSET_STS.RETIRE);
        // END 2018/01/23 J.Kim [QC#23418,MOD]

        S21ApiTBLAccessor.update(updDsAssetMstrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updDsAssetMstrTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", updDsAssetMstrTMsg.dsAssetMstrPk.getValue().toString());
            setDBAcsErrMsg(param, NASM0007E, new String[] {TBL_DS_ASSET_MSTR, pKey });

            return false;
        }

        // START 2018/07/20 J.Kim [QC#24950,ADD]
        if (updDsAssetMstrTMsg.prntDsAssetMstrPk.getValue().compareTo(param.dsAssetMstrPk.getValue()) != 0) {
            return true;
        }
        // END 2018/07/20 J.Kim [QC#24950,ADD]
        setAssetTrackingData(param, updDsAssetMstrTMsg, "svcMachMstrPk", oldDsAssetMstrTMsg.svcMachMstrPk.getValue(), updDsAssetMstrTMsg.svcMachMstrPk.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "actvAssetFlg", oldDsAssetMstrTMsg.actvAssetFlg.getValue(), updDsAssetMstrTMsg.actvAssetFlg.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "revRecogDt", oldDsAssetMstrTMsg.revRecogDt.getValue(), updDsAssetMstrTMsg.revRecogDt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "slsRepTocCd", oldDsAssetMstrTMsg.slsRepTocCd.getValue(), updDsAssetMstrTMsg.slsRepTocCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetStsCd", oldDsAssetMstrTMsg.assetStsCd.getValue(), updDsAssetMstrTMsg.assetStsCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "curValAmt", oldDsAssetMstrTMsg.curValAmt.getValue(), updDsAssetMstrTMsg.curValAmt.getValue(), trkItemMap);

        return true;
    }

    /**
     * Update DS Asset Master. Return for Current
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     * @param trkItemMap Map<String, String>
     * @return boolean
     */
    private boolean updDsAssetMstrRtrnCur(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, NLZC305001_updDtlListPMsg param, Map<String, String> trkItemMap) {

        if (ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {

            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.prntDsAssetMstrPk, param.prntDsAssetMstrPk);

            if (updDsAssetMstrTMsg.dsAssetMstrPk.getValue().compareTo(param.prntDsAssetMstrPk.getValue()) == 0) {

                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetStsCd, ASSET_STS.ACTIVATE);

            } else {

                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetStsCd, ASSET_STS.MERGED);

            }
        }

        // START 2018/01/29 J.Kim [QC#21915,DEL]
        // updDsAssetMstrTMsg.shipToCustAcctCd.clear();
        // END 2018/01/29 J.Kim [QC#21915,DEL]
        // START 2018/01/15 J.Kim [QC#21965,DEL]
        // updDsAssetMstrTMsg.shipToCustCd.clear();
        // END 2018/01/15 J.Kim [QC#21965,DEL]
        // START 2018/01/29 J.Kim [QC#21915,DEL]
        // updDsAssetMstrTMsg.sellToCustCd.clear();
        // updDsAssetMstrTMsg.sldToCustLocCd.clear();
        // END 2018/01/29 J.Kim [QC#21915,DEL]

        DS_ASSET_STGNGTMsg dsAssetStgngTMsg = getDsAssetStgng(param.dsAssetStgngPk.getValue());

        if (dsAssetStgngTMsg == null) {

            setParamErrMsg(param, NLZM2364E);
            return false;

        }

        SHIP_TO_CUSTTMsg shipToCustTMsg = null;
        // START 2018/01/29 J.Kim [QC#21915,DEL]
        // if
        // (ZYPCommonFunc.hasValue(updDsAssetMstrTMsg.svcMachMstrPk))
        // {
        // // START 2017/11/17 J.Kim [QC#17088,DEL]
        // // updDsAssetMstrTMsg.rtrnWhCd.clear();
        // // END 2017/11/17 J.Kim [QC#17088,DEL]
        // updDsAssetMstrTMsg.cpoOrdNum.clear();
        // updDsAssetMstrTMsg.cpoDtlLineNum.clear();
        // updDsAssetMstrTMsg.cpoDtlLineSubNum.clear();
        // updDsAssetMstrTMsg.dsOrdPosnNum.clear();
        // END 2018/01/29 J.Kim [QC#21915,DEL]
        // START 2018/01/15 J.Kim [QC#21965,MOD]
        // updDsAssetMstrTMsg.firstLineAddr.clear();
        // updDsAssetMstrTMsg.scdLineAddr.clear();
        // updDsAssetMstrTMsg.thirdLineAddr.clear();
        // updDsAssetMstrTMsg.frthLineAddr.clear();
        // updDsAssetMstrTMsg.ctyAddr.clear();
        // updDsAssetMstrTMsg.stCd.clear();
        // updDsAssetMstrTMsg.postCd.clear();
        // END 2018/01/15 J.Kim [QC#21965,MOD]
        // START 2018/01/29 J.Kim [QC#21915,DEL]
        // // START 2018/01/15 J.Kim [QC#21965,MOD]
        // if
        // (ZYPCommonFunc.hasValue(updDsAssetMstrTMsg.shipToCustCd)) {
        // shipToCustTMsg =
        // getShipToCust(updDsAssetMstrTMsg.shipToCustCd.getValue());
        // }
        // // END 2018/01/15 J.Kim [QC#21965,MOD]
        // } else {
        // END 2018/01/29 J.Kim [QC#21915,DEL]

            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoOrdNum, dsAssetStgngTMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineNum, dsAssetStgngTMsg.cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineSubNum, dsAssetStgngTMsg.cpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsOrdPosnNum, dsAssetStgngTMsg.dsOrdPosnNum);
            // START 2018/01/15 J.Kim [QC#21965,MOD]
            String sellToCustCd = dsAssetStgngTMsg.sellToCustCd.getValue();
            if (!ZYPCommonFunc.hasValue(dsAssetStgngTMsg.sellToCustCd)) {
                sellToCustCd = param.sellToCustCd.getValue();
            }
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.sellToCustCd, sellToCustCd);
            if (ZYPCommonFunc.hasValue(dsAssetStgngTMsg.apVndInvNum)) {
                shipToCustTMsg = getShipToCustByApVndInvNum(dsAssetStgngTMsg.apVndInvNum.getValue());
            }
            // END 2018/01/15 J.Kim [QC#21965,MOD]
        // START 2018/01/29 J.Kim [QC#21915,DEL]
        // }
        // END 2018/01/29 J.Kim [QC#21915,DEL]

        if (shipToCustTMsg != null) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.firstLineAddr, shipToCustTMsg.firstLineAddr);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.scdLineAddr, shipToCustTMsg.scdLineAddr);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.thirdLineAddr, shipToCustTMsg.thirdLineAddr);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.frthLineAddr, shipToCustTMsg.frthLineAddr);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.ctyAddr, shipToCustTMsg.ctyAddr);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.stCd, shipToCustTMsg.stCd);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.postCd, shipToCustTMsg.postCd);
        }

        // START 2017/11/17 J.Kim [QC#17088,ADD]
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.rtrnWhCd, dsAssetStgngTMsg.rtnWhCd);
        if (ZYPCommonFunc.hasValue(param.sellToCustCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.sellToCustCd, param.sellToCustCd);
        }
        // END 2017/11/17 J.Kim [QC#17088,ADD]
        // START 2018/06/25 J.Kim [QC#24844, ADD]
        if (ZYPCommonFunc.hasValue(dsAssetStgngTMsg.custIssPoNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.custIssPoNum, dsAssetStgngTMsg.custIssPoNum);
        }
        // END 2018/06/25 J.Kim [QC#24844, ADD]

        S21ApiTBLAccessor.update(updDsAssetMstrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updDsAssetMstrTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", updDsAssetMstrTMsg.dsAssetMstrPk.getValue().toString());
            setDBAcsErrMsg(param, NASM0007E, new String[] {TBL_DS_ASSET_MSTR, pKey });

            return false;
        }

        // START 2018/07/20 J.Kim [QC#24950,ADD]
        if (updDsAssetMstrTMsg.dsAssetMstrPk.getValue().compareTo(param.prntDsAssetMstrPk.getValue()) != 0) {
            return true;
            
        }
        // END 2018/07/20 J.Kim [QC#24950,ADD]
        setAssetTrackingData(param, updDsAssetMstrTMsg, "rtrnWhCd", oldDsAssetMstrTMsg.rtrnWhCd.getValue(), updDsAssetMstrTMsg.rtrnWhCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "prntDsAssetMstrPk", oldDsAssetMstrTMsg.prntDsAssetMstrPk.getValue(), updDsAssetMstrTMsg.prntDsAssetMstrPk.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetStsCd", oldDsAssetMstrTMsg.assetStsCd.getValue(), updDsAssetMstrTMsg.assetStsCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "shipToCustAcctCd", oldDsAssetMstrTMsg.shipToCustAcctCd.getValue(), updDsAssetMstrTMsg.shipToCustAcctCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "shipToCustCd", oldDsAssetMstrTMsg.shipToCustCd.getValue(), updDsAssetMstrTMsg.shipToCustCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "sellToCustCd", oldDsAssetMstrTMsg.sellToCustCd.getValue(), updDsAssetMstrTMsg.sellToCustCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "sldToCustLocCd", oldDsAssetMstrTMsg.sldToCustLocCd.getValue(), updDsAssetMstrTMsg.sldToCustLocCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoOrdNum", oldDsAssetMstrTMsg.cpoOrdNum.getValue(), updDsAssetMstrTMsg.cpoOrdNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoDtlLineNum", oldDsAssetMstrTMsg.cpoDtlLineNum.getValue(), updDsAssetMstrTMsg.cpoDtlLineNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoDtlLineSubNum", oldDsAssetMstrTMsg.cpoDtlLineSubNum.getValue(), updDsAssetMstrTMsg.cpoDtlLineSubNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "dsOrdPosnNum", oldDsAssetMstrTMsg.dsOrdPosnNum.getValue(), updDsAssetMstrTMsg.dsOrdPosnNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "firstLineAddr", oldDsAssetMstrTMsg.firstLineAddr.getValue(), updDsAssetMstrTMsg.firstLineAddr.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "scdLineAddr", oldDsAssetMstrTMsg.scdLineAddr.getValue(), updDsAssetMstrTMsg.scdLineAddr.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "thirdLineAddr", oldDsAssetMstrTMsg.thirdLineAddr.getValue(), updDsAssetMstrTMsg.thirdLineAddr.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "frthLineAddr", oldDsAssetMstrTMsg.frthLineAddr.getValue(), updDsAssetMstrTMsg.frthLineAddr.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "ctyAddr", oldDsAssetMstrTMsg.ctyAddr.getValue(), updDsAssetMstrTMsg.ctyAddr.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "stCd", oldDsAssetMstrTMsg.stCd.getValue(), updDsAssetMstrTMsg.stCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "postCd", oldDsAssetMstrTMsg.postCd.getValue(), updDsAssetMstrTMsg.postCd.getValue(), trkItemMap);

        return true;
    }

    /**
     * Update DS Asset Master. Return for Parent
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     * @param trkItemMap Map<String, String>
     * @return boolean
     */
    private boolean updDsAssetMstrRtrnParent(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, NLZC305001_updDtlListPMsg param, Map<String, String> trkItemMap) {

        // START 2018/01/29 J.Kim [QC#21915,DEL]
        // updDsAssetMstrTMsg.shipToCustAcctCd.clear();
        // END 2018/01/29 J.Kim [QC#21915,DEL]
        // START 2018/01/15 J.Kim [QC#21965,DEL]
        // updDsAssetMstrTMsg.shipToCustCd.clear();
        // END 2018/01/15 J.Kim [QC#21965,DEL]
        // START 2018/01/29 J.Kim [QC#21915,DEL]
        // updDsAssetMstrTMsg.sellToCustCd.clear();
        // updDsAssetMstrTMsg.sldToCustLocCd.clear();
        // END 2018/01/29 J.Kim [QC#21915,DEL]

        DS_ASSET_STGNGTMsg dsAssetStgngTMsg = getDsAssetStgng(param.dsAssetStgngPk.getValue());

        if (dsAssetStgngTMsg == null) {

            setParamErrMsg(param, NLZM2364E);
            return false;

        }

        SHIP_TO_CUSTTMsg shipToCustTMsg = null;
        // START 2018/01/29 J.Kim [QC#21915,DEL]
        // if
        // (ZYPCommonFunc.hasValue(updDsAssetMstrTMsg.svcMachMstrPk))
        // {
        // END 2018/01/29 J.Kim [QC#21915,DEL]
        // START 2017/11/17 J.Kim [QC#17088,DEL]
        // updDsAssetMstrTMsg.rtrnWhCd.clear();
        // END 2017/11/17 J.Kim [QC#17088,DEL]
        // START 2018/01/29 J.Kim [QC#21915,DEL]
        // updDsAssetMstrTMsg.cpoOrdNum.clear();
        // if
        // (oldDsAssetMstrTMsg.prntDsAssetMstrPk.getValue().compareTo(param.dsAssetMstrPk.getValue())
        // != 0) {
        // updDsAssetMstrTMsg.cpoDtlLineNum.clear();
        // updDsAssetMstrTMsg.cpoDtlLineSubNum.clear();
        // }
        // updDsAssetMstrTMsg.dsOrdPosnNum.clear();
        // END 2018/01/29 J.Kim [QC#21915,DEL]
        // START 2018/01/15 J.Kim [QC#21965,DEL]
        // updDsAssetMstrTMsg.firstLineAddr.clear();
        // updDsAssetMstrTMsg.scdLineAddr.clear();
        // updDsAssetMstrTMsg.thirdLineAddr.clear();
        // updDsAssetMstrTMsg.frthLineAddr.clear();
        // updDsAssetMstrTMsg.ctyAddr.clear();
        // updDsAssetMstrTMsg.stCd.clear();
        // updDsAssetMstrTMsg.postCd.clear();
        // END 2018/01/15 J.Kim [QC#21965,DEL]
        // START 2018/01/29 J.Kim [QC#21915,DEL]
        // // START 2018/01/15 J.Kim [QC#21965,MOD]
        // if
        // (ZYPCommonFunc.hasValue(updDsAssetMstrTMsg.shipToCustCd)) {
        // shipToCustTMsg =
        // getShipToCust(updDsAssetMstrTMsg.shipToCustCd.getValue());
        // }
        // // END 2018/01/15 J.Kim [QC#21965,MOD]
        // } else {
        // END 2018/01/29 J.Kim [QC#21915,DEL]

            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoOrdNum, dsAssetStgngTMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineNum, dsAssetStgngTMsg.cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineSubNum, dsAssetStgngTMsg.cpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsOrdPosnNum, dsAssetStgngTMsg.dsOrdPosnNum);
            // START 2018/01/15 J.Kim [QC#21965,MOD]
            String sellToCustCd = dsAssetStgngTMsg.sellToCustCd.getValue();
            if (!ZYPCommonFunc.hasValue(dsAssetStgngTMsg.sellToCustCd)) {
                sellToCustCd = param.sellToCustCd.getValue();
            }
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.sellToCustCd, sellToCustCd);
            if (ZYPCommonFunc.hasValue(dsAssetStgngTMsg.apVndInvNum)) {
                shipToCustTMsg = getShipToCustByApVndInvNum(dsAssetStgngTMsg.apVndInvNum.getValue());
            }
            // END 2018/01/15 J.Kim [QC#21965,MOD]
        // START 2018/01/29 J.Kim [QC#21915,DEL]
        // }
        // END 2018/01/29 J.Kim [QC#21915,DEL]

        if (shipToCustTMsg != null) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.firstLineAddr, shipToCustTMsg.firstLineAddr);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.scdLineAddr, shipToCustTMsg.scdLineAddr);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.thirdLineAddr, shipToCustTMsg.thirdLineAddr);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.frthLineAddr, shipToCustTMsg.frthLineAddr);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.ctyAddr, shipToCustTMsg.ctyAddr);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.stCd, shipToCustTMsg.stCd);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.postCd, shipToCustTMsg.postCd);
        }

        // START 2017/11/17 J.Kim [QC#17088,ADD]
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.rtrnWhCd, dsAssetStgngTMsg.rtnWhCd);
        if (ZYPCommonFunc.hasValue(param.sellToCustCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.sellToCustCd, param.sellToCustCd);
        }
        // END 2017/11/17 J.Kim [QC#17088,ADD]
        // START 2018/06/25 J.Kim [QC#24844, ADD]
        if (ZYPCommonFunc.hasValue(dsAssetStgngTMsg.custIssPoNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.custIssPoNum, dsAssetStgngTMsg.custIssPoNum);
        }
        // END 2018/06/25 J.Kim [QC#24844, ADD]

        S21ApiTBLAccessor.update(updDsAssetMstrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updDsAssetMstrTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", updDsAssetMstrTMsg.dsAssetMstrPk.getValue().toString());
            setDBAcsErrMsg(param, NASM0007E, new String[] {TBL_DS_ASSET_MSTR, pKey });

            return false;
        }

        // START 2018/07/20 J.Kim [QC#24950,ADD]
        setAssetTrackingData(param, updDsAssetMstrTMsg, "rtrnWhCd", oldDsAssetMstrTMsg.rtrnWhCd.getValue(), updDsAssetMstrTMsg.rtrnWhCd.getValue(), trkItemMap);
        if (updDsAssetMstrTMsg.dsAssetMstrPk.getValue().compareTo(param.prntDsAssetMstrPk.getValue()) != 0) {
            return true;
            
        }
        // END 2018/07/20 J.Kim [QC#24950,ADD]

        setAssetTrackingData(param, updDsAssetMstrTMsg, "shipToCustAcctCd", oldDsAssetMstrTMsg.shipToCustAcctCd.getValue(), updDsAssetMstrTMsg.shipToCustAcctCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "shipToCustCd", oldDsAssetMstrTMsg.shipToCustCd.getValue(), updDsAssetMstrTMsg.shipToCustCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "sellToCustCd", oldDsAssetMstrTMsg.sellToCustCd.getValue(), updDsAssetMstrTMsg.sellToCustCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "sldToCustLocCd", oldDsAssetMstrTMsg.sldToCustLocCd.getValue(), updDsAssetMstrTMsg.sldToCustLocCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoOrdNum", oldDsAssetMstrTMsg.cpoOrdNum.getValue(), updDsAssetMstrTMsg.cpoOrdNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoDtlLineNum", oldDsAssetMstrTMsg.cpoDtlLineNum.getValue(), updDsAssetMstrTMsg.cpoDtlLineNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoDtlLineSubNum", oldDsAssetMstrTMsg.cpoDtlLineSubNum.getValue(), updDsAssetMstrTMsg.cpoDtlLineSubNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "dsOrdPosnNum", oldDsAssetMstrTMsg.dsOrdPosnNum.getValue(), updDsAssetMstrTMsg.dsOrdPosnNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "firstLineAddr", oldDsAssetMstrTMsg.firstLineAddr.getValue(), updDsAssetMstrTMsg.firstLineAddr.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "scdLineAddr", oldDsAssetMstrTMsg.scdLineAddr.getValue(), updDsAssetMstrTMsg.scdLineAddr.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "thirdLineAddr", oldDsAssetMstrTMsg.thirdLineAddr.getValue(), updDsAssetMstrTMsg.thirdLineAddr.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "frthLineAddr", oldDsAssetMstrTMsg.frthLineAddr.getValue(), updDsAssetMstrTMsg.frthLineAddr.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "ctyAddr", oldDsAssetMstrTMsg.ctyAddr.getValue(), updDsAssetMstrTMsg.ctyAddr.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "stCd", oldDsAssetMstrTMsg.stCd.getValue(), updDsAssetMstrTMsg.stCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "postCd", oldDsAssetMstrTMsg.postCd.getValue(), updDsAssetMstrTMsg.postCd.getValue(), trkItemMap);

        return true;
    }

    /**
     * Update DS Asset Master. Dspl for Current
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     * @param trkItemMap Map<String, String>
     * @return boolean
     */
    private boolean updDsAssetMstrDsplCur(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, NLZC305001_updDtlListPMsg param, Map<String, String> trkItemMap) {

        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.actvAssetFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.curValAmt, BigDecimal.ZERO);
        if (ZYPCommonFunc.hasValue(param.slsRepTocCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.slsRepTocCd, param.slsRepTocCd);
        }
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetStsCd, ASSET_STS.RETIRE);

        DS_ASSET_STGNGTMsg dsAssetStgngTMsg = null;
        if (ZYPCommonFunc.hasValue(param.dsAssetStgngPk)) {

            dsAssetStgngTMsg = getDsAssetStgng(param.dsAssetStgngPk.getValue());
        }

        if (dsAssetStgngTMsg != null) {

            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.rtrnWhCd, dsAssetStgngTMsg.rtnWhCd);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoOrdNum, dsAssetStgngTMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineNum, dsAssetStgngTMsg.cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineSubNum, dsAssetStgngTMsg.cpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsOrdPosnNum, dsAssetStgngTMsg.dsOrdPosnNum);
        }

        if (ZYPCommonFunc.hasValue(param.assetRtireRsnCmntTxt)) {

            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetRtireRsnCmntTxt, param.assetRtireRsnCmntTxt);

        }
        // START 2017/11/17 J.Kim [QC#17088,ADD]
        if (ZYPCommonFunc.hasValue(param.sellToCustCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.sellToCustCd, param.sellToCustCd);
        }
        // END 2017/11/17 J.Kim [QC#17088,ADD]
        // START 2018/01/15 J.Kim [QC#21965,MOD]
        if (ZYPCommonFunc.hasValue(updDsAssetMstrTMsg.shipToCustCd)) {
            SHIP_TO_CUSTTMsg shipToCustTMsg = getShipToCust(updDsAssetMstrTMsg.shipToCustCd.getValue());
            if (shipToCustTMsg != null) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.firstLineAddr, shipToCustTMsg.firstLineAddr);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.scdLineAddr, shipToCustTMsg.scdLineAddr);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.thirdLineAddr, shipToCustTMsg.thirdLineAddr);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.frthLineAddr, shipToCustTMsg.frthLineAddr);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.ctyAddr, shipToCustTMsg.ctyAddr);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.stCd, shipToCustTMsg.stCd);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.postCd, shipToCustTMsg.postCd);
            }
        }
        // END 2018/01/15 J.Kim [QC#21965,MOD]

        S21ApiTBLAccessor.update(updDsAssetMstrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updDsAssetMstrTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", updDsAssetMstrTMsg.dsAssetMstrPk.getValue().toString());
            setDBAcsErrMsg(param, NASM0007E, new String[] {TBL_DS_ASSET_MSTR, pKey });

            return false;
        }

        setAssetTrackingData(param, updDsAssetMstrTMsg, "rtrnWhCd", oldDsAssetMstrTMsg.rtrnWhCd.getValue(), updDsAssetMstrTMsg.rtrnWhCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "curValAmt", oldDsAssetMstrTMsg.curValAmt.getValue(), updDsAssetMstrTMsg.curValAmt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "initBookAmt", oldDsAssetMstrTMsg.curValAmt.getValue(), updDsAssetMstrTMsg.initBookAmt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetRtireRsnCmntTxt", oldDsAssetMstrTMsg.assetRtireRsnCmntTxt.getValue(), updDsAssetMstrTMsg.assetRtireRsnCmntTxt.getValue(), trkItemMap);

        return true;
    }

    /**
     * Update DS Asset Master. Dspl for Parent
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     * @param trkItemMap Map<String, String>
     * @return boolean
     */
    private boolean updDsAssetMstrDsplParent(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, NLZC305001_updDtlListPMsg param, Map<String, String> trkItemMap) {

        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.actvAssetFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.curValAmt, BigDecimal.ZERO);
        if (ZYPCommonFunc.hasValue(param.slsRepTocCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.slsRepTocCd, param.slsRepTocCd);
        }
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetStsCd, ASSET_STS.RETIRE);

        DS_ASSET_STGNGTMsg dsAssetStgngTMsg = null;
        if (ZYPCommonFunc.hasValue(param.dsAssetStgngPk)) {

            dsAssetStgngTMsg = getDsAssetStgng(param.dsAssetStgngPk.getValue());
        }

        if (dsAssetStgngTMsg != null) {

            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.rtrnWhCd, dsAssetStgngTMsg.rtnWhCd);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoOrdNum, dsAssetStgngTMsg.cpoOrdNum);
            if (oldDsAssetMstrTMsg.dsAssetMstrPk.getValue().compareTo(param.prntDsAssetMstrPk.getValue()) == 0) {

                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineNum, dsAssetStgngTMsg.cpoDtlLineNum);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineSubNum, dsAssetStgngTMsg.cpoDtlLineSubNum);

            }
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsOrdPosnNum, dsAssetStgngTMsg.dsOrdPosnNum);
        }

        if (ZYPCommonFunc.hasValue(param.assetRtireRsnCmntTxt)) {

            if (oldDsAssetMstrTMsg.prntDsAssetMstrPk.getValue().compareTo(param.prntDsAssetMstrPk.getValue()) == 0) {

                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetRtireRsnCmntTxt, param.assetRtireRsnCmntTxt);

            }

        }
        // START 2017/11/17 J.Kim [QC#17088,ADD]
        if (ZYPCommonFunc.hasValue(param.sellToCustCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.sellToCustCd, param.sellToCustCd);
        }
        // END 2017/11/17 J.Kim [QC#17088,ADD]
        // START 2018/01/15 J.Kim [QC#21965,MOD]
        if (ZYPCommonFunc.hasValue(updDsAssetMstrTMsg.shipToCustCd)) {
            SHIP_TO_CUSTTMsg shipToCustTMsg = getShipToCust(updDsAssetMstrTMsg.shipToCustCd.getValue());
            if (shipToCustTMsg != null) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.firstLineAddr, shipToCustTMsg.firstLineAddr);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.scdLineAddr, shipToCustTMsg.scdLineAddr);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.thirdLineAddr, shipToCustTMsg.thirdLineAddr);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.frthLineAddr, shipToCustTMsg.frthLineAddr);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.ctyAddr, shipToCustTMsg.ctyAddr);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.stCd, shipToCustTMsg.stCd);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.postCd, shipToCustTMsg.postCd);
            }
        }
        // END 2018/01/15 J.Kim [QC#21965,MOD]

        S21ApiTBLAccessor.update(updDsAssetMstrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updDsAssetMstrTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", updDsAssetMstrTMsg.dsAssetMstrPk.getValue().toString());
            setDBAcsErrMsg(param, NASM0007E, new String[] {TBL_DS_ASSET_MSTR, pKey });

            return false;
        }

        // START 2018/07/20 J.Kim [QC#24950,ADD]
        if (oldDsAssetMstrTMsg.dsAssetMstrPk.getValue().compareTo(param.prntDsAssetMstrPk.getValue()) != 0) {
            return true;
        }
        // END 2018/07/20 J.Kim [QC#24950,ADD]

        setAssetTrackingData(param, updDsAssetMstrTMsg, "actvAssetFlg", oldDsAssetMstrTMsg.actvAssetFlg.getValue(), updDsAssetMstrTMsg.actvAssetFlg.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "rtrnWhCd", oldDsAssetMstrTMsg.rtrnWhCd.getValue(), updDsAssetMstrTMsg.rtrnWhCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "slsRepTocCd", oldDsAssetMstrTMsg.slsRepTocCd.getValue(), updDsAssetMstrTMsg.slsRepTocCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetStsCd", oldDsAssetMstrTMsg.assetStsCd.getValue(), updDsAssetMstrTMsg.assetStsCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoOrdNum", oldDsAssetMstrTMsg.cpoOrdNum.getValue(), updDsAssetMstrTMsg.cpoOrdNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoDtlLineNum", oldDsAssetMstrTMsg.cpoDtlLineNum.getValue(), updDsAssetMstrTMsg.cpoDtlLineNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoDtlLineSubNum", oldDsAssetMstrTMsg.cpoDtlLineSubNum.getValue(), updDsAssetMstrTMsg.cpoDtlLineSubNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "dsOrdPosnNum", oldDsAssetMstrTMsg.dsOrdPosnNum.getValue(), updDsAssetMstrTMsg.dsOrdPosnNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetRtireRsnCmntTxt", oldDsAssetMstrTMsg.assetRtireRsnCmntTxt.getValue(), updDsAssetMstrTMsg.assetRtireRsnCmntTxt.getValue(), trkItemMap);
        setAssetTrackingData(param, oldDsAssetMstrTMsg, "prntDsAssetMstrPk", oldDsAssetMstrTMsg.prntDsAssetMstrPk.getValue(), updDsAssetMstrTMsg.prntDsAssetMstrPk.getValue(), trkItemMap);

        return true;
    }

    /**
     * Update DS Asset Master. Update Current
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     * @param trkItemMap Map<String, String>
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @return boolean
     */
    private boolean updDsAssetMstrUpdCur(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, NLZC305001_updDtlListPMsg param, Map<String, String> trkItemMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {

        if (ZYPCommonFunc.hasValue(param.mdseCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.mdseCd, param.mdseCd);
        }

        if (ZYPCommonFunc.hasValue(param.serNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.serNum, param.serNum);
        }

        if (ZYPCommonFunc.hasValue(param.svcMachMstrPk)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.svcMachMstrPk, param.svcMachMstrPk);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.shpgPlnNum, svcMachMstrTMsg.shpgPlnNum);
        }

        if (ZYPCommonFunc.hasValue(param.depcMthNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.depcMthNum, param.depcMthNum);
        }

        if (ZYPCommonFunc.hasValue(param.depcStartDt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.depcStartDt, param.depcStartDt);
        }

        if (ZYPCommonFunc.hasValue(param.slsRepTocCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.slsRepTocCd, param.slsRepTocCd);
        }

        if (ZYPCommonFunc.hasValue(param.rsdlValAmt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.rsdlValAmt, param.rsdlValAmt);
        }

        if (ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {
            if (param.prntDsAssetMstrPk.getValue().compareTo(updDsAssetMstrTMsg.dsAssetMstrPk.getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetStsCd, ASSET_STS.ACTIVATE);
            } else {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetStsCd, ASSET_STS.MERGED);
            }
        }

        if (ZYPCommonFunc.hasValue(param.rtrnWhCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.rtrnWhCd, param.rtrnWhCd);
        }

        if (ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.prntDsAssetMstrPk, param.prntDsAssetMstrPk);
        }

        if (ZYPCommonFunc.hasValue(param.assetDescTxt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsAssetDescTxt, param.assetDescTxt);
        }

        if (ZYPCommonFunc.hasValue(param.assetTagNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetTagNum, param.assetTagNum);
        }

        if (ZYPCommonFunc.hasValue(param.svcConfigMstrPk)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.svcConfigMstrPk, param.svcConfigMstrPk);
        }

        if (ZYPCommonFunc.hasValue(param.dtlCmntTxt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dtlCmntTxt, param.dtlCmntTxt);
        }

        if (ZYPCommonFunc.hasValue(param.asgDtlCmntTxt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.asgDtlCmntTxt, param.asgDtlCmntTxt);
        }

        if (ZYPCommonFunc.hasValue(param.finDtlCmntTxt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.finDtlCmntTxt, param.finDtlCmntTxt);
        }

        if (ZYPCommonFunc.hasValue(param.firstLineAddr)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.firstLineAddr, param.firstLineAddr);
        }

        if (ZYPCommonFunc.hasValue(param.ctyAddr)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.ctyAddr, param.ctyAddr);
        }

        if (ZYPCommonFunc.hasValue(param.stCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.stCd, param.stCd);
        }

        if (ZYPCommonFunc.hasValue(param.postCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.postCd, param.postCd);
        }

        if (ZYPCommonFunc.hasValue(param.totAssetQty)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.totAssetQty, param.totAssetQty);
        }

        if (ZYPCommonFunc.hasValue(param.coaCmpyCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaCmpyCd, param.coaCmpyCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaBrCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaBrCd, param.coaBrCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaCcCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaCcCd, param.coaCcCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaAcctCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaAcctCd, param.coaAcctCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaProdCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaProdCd, param.coaProdCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaChCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaChCd, param.coaChCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaAfflCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaAfflCd, param.coaAfflCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaProjCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaProjCd, param.coaProjCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaExtnCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaExtnCd, param.coaExtnCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaCmpyCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaCmpyCd, param.assetCoaCmpyCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaBrCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaBrCd, param.assetCoaBrCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaCcCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaCcCd, param.assetCoaCcCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaAcctCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaAcctCd, param.assetCoaAcctCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaProdCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaProdCd, param.assetCoaProdCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaChCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaChCd, param.assetCoaChCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaAfflCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaAfflCd, param.assetCoaAfflCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaProjCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaProjCd, param.assetCoaProjCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaExtnCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaExtnCd, param.assetCoaExtnCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaMdseTpCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.coaMdseTpCd, param.coaMdseTpCd);
        }

        if (ZYPCommonFunc.hasValue(param.amtChngRsnTpCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.amtChngRsnTpCd, param.amtChngRsnTpCd);
        }

        if (ZYPCommonFunc.hasValue(param.adjCoaAcctCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.adjCoaAcctCd, param.adjCoaAcctCd);
        }

        if (ZYPCommonFunc.hasValue(param.initBookAmt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.initBookAmt, param.initBookAmt);

            if (BigDecimal.ZERO.compareTo(updDsAssetMstrTMsg.depcCnt.getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.curValAmt, param.initBookAmt);
            } else {

                int carcDeclPlace = ZYPCodeDataUtil.getNumConstValue(ASSET_COST_CARC_DECIMAL_PLACE, glblCmpyCd).intValue();
                String costRoundMode = ZYPCodeDataUtil.getVarCharConstValue(ASSET_COST_ROUNDING_MODE, glblCmpyCd);

                int roundingMode = BigDecimal.ROUND_DOWN;

                if (ROUND_UP.equals(costRoundMode)) {

                    roundingMode = BigDecimal.ROUND_UP;

                } else if (ROUND_HALF_UP.equals(costRoundMode)) {

                    roundingMode = BigDecimal.ROUND_HALF_UP;
                }

                BigDecimal initBookAmt = updDsAssetMstrTMsg.initBookAmt.getValue();
                BigDecimal depcCnt = updDsAssetMstrTMsg.depcCnt.getValue();
                BigDecimal depcMthNum = new BigDecimal(updDsAssetMstrTMsg.depcMthNum.getValue());
                BigDecimal rsdlValAmt = updDsAssetMstrTMsg.rsdlValAmt.getValue();

                BigDecimal curValAmt = BigDecimal.ZERO;
                if (BigDecimal.ZERO.compareTo(depcMthNum) != 0) {
                    curValAmt = initBookAmt.subtract(initBookAmt.subtract(rsdlValAmt).divide(depcMthNum, carcDeclPlace, roundingMode).multiply(depcCnt));
                }

                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.curValAmt, curValAmt);
            }

        } else {

            if (ZYPCommonFunc.hasValue(param.curValAmt)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.curValAmt, param.curValAmt);
            }
        }

        S21ApiTBLAccessor.update(updDsAssetMstrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updDsAssetMstrTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", updDsAssetMstrTMsg.dsAssetMstrPk.getValue().toString());
            setDBAcsErrMsg(param, NASM0007E, new String[] {TBL_DS_ASSET_MSTR, pKey });

            return false;
        }

        setAssetTrackingData(param, updDsAssetMstrTMsg, "dtlCmntTxt", oldDsAssetMstrTMsg.dtlCmntTxt.getValue(), updDsAssetMstrTMsg.dtlCmntTxt.getValue(), trkItemMap);

        return true;
    }

    /**
     * Update DS Asset Master Parent
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     * @param trkItemMap Map<String, String>
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param totOrigStdCostAmt BigDecimal
     * @param totCurValAmt BigDecimal
     * @param maxSplAmtMap Map<String, BigDecimal>
     * @return
     */
    private boolean updDsAssetMstrUpdParent(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, //
            DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, //
            NLZC305001_updDtlListPMsg param, //
            Map<String, String> trkItemMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg, //
            BigDecimal totOrigStdCostAmt, //
            BigDecimal totCurValAmt, //
            DS_ASSET_MSTRTMsg parentDsAssetMstrTMsg, //
            Map<String, BigDecimal> splAmtMap) {

        if (param.prntDsAssetMstrPk.getValue().compareTo(updDsAssetMstrTMsg.dsAssetMstrPk.getValue()) == 0) {

            if (ZYPCommonFunc.hasValue(param.mdseCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.mdseCd, param.mdseCd);
            }

            if (ZYPCommonFunc.hasValue(param.serNum)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.serNum, param.serNum);
            }

            if (ZYPCommonFunc.hasValue(param.svcMachMstrPk)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.svcMachMstrPk, param.svcMachMstrPk);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.shpgPlnNum, svcMachMstrTMsg.shpgPlnNum);
            }

            if (ZYPCommonFunc.hasValue(param.depcMthNum)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.depcMthNum, param.depcMthNum);
            }

            if (ZYPCommonFunc.hasValue(param.assetDescTxt)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsAssetDescTxt, param.assetDescTxt);
            }

            if (ZYPCommonFunc.hasValue(param.assetTagNum)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetTagNum, param.assetTagNum);
            }

            if (ZYPCommonFunc.hasValue(param.asgDtlCmntTxt)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.asgDtlCmntTxt, param.asgDtlCmntTxt);
            }

            if (ZYPCommonFunc.hasValue(param.finDtlCmntTxt)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.finDtlCmntTxt, param.finDtlCmntTxt);
            }

            if (ZYPCommonFunc.hasValue(param.totAssetQty)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.totAssetQty, param.totAssetQty);
            }

            if (ZYPCommonFunc.hasValue(param.coaMdseTpCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.coaMdseTpCd, param.coaMdseTpCd);
            }

        } else {

            if (ZYPCommonFunc.hasValue(param.depcMthNum)) {

                BigDecimal curDepcMthNum = new BigDecimal(parentDsAssetMstrTMsg.depcMthNum.getValue());
                BigDecimal prntDepcMthNum = curDepcMthNum.subtract(parentDsAssetMstrTMsg.depcCnt.getValue());
                BigDecimal childDepcMthNum = prntDepcMthNum.add(updDsAssetMstrTMsg.depcCnt.getValue());

                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.depcMthNum, childDepcMthNum.toPlainString());
            }
        }

        if (ZYPCommonFunc.hasValue(param.depcStartDt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.depcStartDt, param.depcStartDt);
        }

        if (ZYPCommonFunc.hasValue(param.slsRepTocCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.slsRepTocCd, param.slsRepTocCd);
        }

        if (ZYPCommonFunc.hasValue(param.rsdlValAmt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.rsdlValAmt, param.rsdlValAmt);
        }

        if (ZYPCommonFunc.hasValue(param.rtrnWhCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.rtrnWhCd, param.rtrnWhCd);
        }

        if (ZYPCommonFunc.hasValue(param.svcConfigMstrPk)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.svcConfigMstrPk, param.svcConfigMstrPk);
        }

        if (ZYPCommonFunc.hasValue(param.firstLineAddr)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.firstLineAddr, param.firstLineAddr);
        }

        if (ZYPCommonFunc.hasValue(param.ctyAddr)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.ctyAddr, param.ctyAddr);
        }

        if (ZYPCommonFunc.hasValue(param.stCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.stCd, param.stCd);
        }

        if (ZYPCommonFunc.hasValue(param.postCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.postCd, param.postCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaCmpyCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaCmpyCd, param.coaCmpyCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaBrCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaBrCd, param.coaBrCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaCcCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaCcCd, param.coaCcCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaAcctCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaAcctCd, param.coaAcctCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaProdCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaProdCd, param.coaProdCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaChCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaChCd, param.coaChCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaAfflCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaAfflCd, param.coaAfflCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaProjCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaProjCd, param.coaProjCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaExtnCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaExtnCd, param.coaExtnCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaCmpyCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaCmpyCd, param.assetCoaCmpyCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaBrCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaBrCd, param.assetCoaBrCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaCcCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaCcCd, param.assetCoaCcCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaAcctCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaAcctCd, param.assetCoaAcctCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaProdCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaProdCd, param.assetCoaProdCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaChCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaChCd, param.assetCoaChCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaAfflCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaAfflCd, param.assetCoaAfflCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaProjCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaProjCd, param.assetCoaProjCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaExtnCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaExtnCd, param.assetCoaExtnCd);
        }

        if (ZYPCommonFunc.hasValue(param.initBookAmt)) {

            int carcDeclPlace = ZYPCodeDataUtil.getNumConstValue(ASSET_COST_CARC_DECIMAL_PLACE, glblCmpyCd).intValue();
            String costRoundMode = ZYPCodeDataUtil.getVarCharConstValue(ASSET_COST_ROUNDING_MODE, glblCmpyCd);

            int roundingMode = BigDecimal.ROUND_DOWN;

            if (ROUND_UP.equals(costRoundMode)) {

                roundingMode = BigDecimal.ROUND_UP;

            } else if (ROUND_HALF_UP.equals(costRoundMode)) {

                roundingMode = BigDecimal.ROUND_HALF_UP;
            }

            if (ZYPCommonFunc.hasValue(splAmtMap.get(MAX_AMT_DS_ASSET_MSTR_PK)) && //
                    splAmtMap.get(MAX_AMT_DS_ASSET_MSTR_PK).compareTo(updDsAssetMstrTMsg.dsAssetMstrPk.getValue()) == 0) {

                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.initBookAmt, splAmtMap.get(MAX_SPL_AMT));

                if (BigDecimal.ZERO.compareTo(updDsAssetMstrTMsg.depcCnt.getValue()) == 0) {

                    ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.curValAmt, updDsAssetMstrTMsg.initBookAmt);

                } else {

                    BigDecimal initBookAmt = updDsAssetMstrTMsg.initBookAmt.getValue();
                    BigDecimal depcCnt = updDsAssetMstrTMsg.depcCnt.getValue();
                    BigDecimal depcMthNum = new BigDecimal(updDsAssetMstrTMsg.depcMthNum.getValue());
                    BigDecimal rsdlValAmt = updDsAssetMstrTMsg.rsdlValAmt.getValue();
                    BigDecimal newCurValAmt = BigDecimal.ZERO;
                    if (BigDecimal.ZERO.compareTo(depcMthNum) != 0) {
                        newCurValAmt = initBookAmt.subtract(initBookAmt.subtract(rsdlValAmt).divide(depcMthNum, carcDeclPlace, roundingMode).multiply(depcCnt));
                    }

                    ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.curValAmt, newCurValAmt);
                }

            } else {

                BigDecimal paramInitBookAmt = param.initBookAmt.getValue();
                BigDecimal curValAmt = oldDsAssetMstrTMsg.curValAmt.getValue();
                BigDecimal origStdCostAmt = oldDsAssetMstrTMsg.origStdCostAmt.getValue();
                BigDecimal initBookAmt = BigDecimal.ZERO;

                if (BigDecimal.ZERO.compareTo(totCurValAmt) != 0 && BigDecimal.ZERO.compareTo(curValAmt) != 0) {
                    // START 2018/09/21 J.Kim [QC#28341, MOD]
                    // initBookAmt = paramInitBookAmt.divide(totCurValAmt.divide(curValAmt, carcDeclPlace, roundingMode), carcDeclPlace, roundingMode);
                    initBookAmt = paramInitBookAmt.divide(totOrigStdCostAmt.divide(origStdCostAmt, COST_SCALE, roundingMode), carcDeclPlace, roundingMode);
                    // END 2018/09/21 J.Kim [QC#28341, MOD]
                } else if (param.prntDsAssetMstrPk.getValue().compareTo(updDsAssetMstrTMsg.dsAssetMstrPk.getValue()) == 0) {
                    initBookAmt = paramInitBookAmt;
                }

                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.initBookAmt, initBookAmt);

                if (BigDecimal.ZERO.compareTo(updDsAssetMstrTMsg.depcCnt.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.curValAmt, updDsAssetMstrTMsg.initBookAmt);
                } else {

                    initBookAmt = updDsAssetMstrTMsg.initBookAmt.getValue();
                    BigDecimal depcCnt = updDsAssetMstrTMsg.depcCnt.getValue();
                    BigDecimal depcMthNum = new BigDecimal(updDsAssetMstrTMsg.depcMthNum.getValue());
                    BigDecimal rsdlValAmt = updDsAssetMstrTMsg.rsdlValAmt.getValue();
                    BigDecimal newCurValAmt = BigDecimal.ZERO;
                    if (BigDecimal.ZERO.compareTo(depcMthNum) != 0) {
                        newCurValAmt = initBookAmt.subtract(initBookAmt.subtract(rsdlValAmt).divide(depcMthNum, carcDeclPlace, roundingMode).multiply(depcCnt));
                    }

                    ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.curValAmt, newCurValAmt);
                }
            }

        } else {

            if (ZYPCommonFunc.hasValue(param.curValAmt)) {

                int carcDeclPlace = ZYPCodeDataUtil.getNumConstValue(ASSET_COST_CARC_DECIMAL_PLACE, glblCmpyCd).intValue();
                String costRoundMode = ZYPCodeDataUtil.getVarCharConstValue(ASSET_COST_ROUNDING_MODE, glblCmpyCd);
                BigDecimal newCurValAmt = BigDecimal.ZERO;

                int roundingMode = BigDecimal.ROUND_DOWN;

                if (ROUND_UP.equals(costRoundMode)) {

                    roundingMode = BigDecimal.ROUND_UP;

                } else if (ROUND_HALF_UP.equals(costRoundMode)) {

                    roundingMode = BigDecimal.ROUND_HALF_UP;
                }

                if (ZYPCommonFunc.hasValue(splAmtMap.get(MAX_AMT_DS_ASSET_MSTR_PK)) && //
                        splAmtMap.get(MAX_AMT_DS_ASSET_MSTR_PK).compareTo(updDsAssetMstrTMsg.dsAssetMstrPk.getValue()) == 0) {

                    newCurValAmt = splAmtMap.get(MAX_CUR_AMT);

                } else {

                    BigDecimal paramCurValAmt = param.curValAmt.getValue();
                    BigDecimal curValAmt = oldDsAssetMstrTMsg.curValAmt.getValue();
                    BigDecimal origStdCostAmt = oldDsAssetMstrTMsg.origStdCostAmt.getValue();

                    if (BigDecimal.ZERO.compareTo(totCurValAmt) != 0 && BigDecimal.ZERO.compareTo(curValAmt) != 0) {
                        // START 2018/09/21 J.Kim [QC#28341, MOD]
                        // newCurValAmt = paramCurValAmt.divide(totCurValAmt.divide(curValAmt, carcDeclPlace, roundingMode), carcDeclPlace, roundingMode);
                        newCurValAmt = paramCurValAmt.divide(totOrigStdCostAmt.divide(origStdCostAmt, COST_SCALE, roundingMode), carcDeclPlace, roundingMode);
                        // END 2018/09/21 J.Kim [QC#28341, MOD]
                    }
                }

                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.curValAmt, newCurValAmt);
            }
        }
        // START 2018/07/20 J.Kim [QC#24950,ADD]
        if (ZYPCommonFunc.hasValue(param.shipToCustAcctCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.shipToCustAcctCd, param.shipToCustAcctCd);
        }
        if (ZYPCommonFunc.hasValue(param.cpoOrdNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoOrdNum, param.cpoOrdNum);
        }
        if (ZYPCommonFunc.hasValue(param.cpoDtlLineNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineNum, param.cpoDtlLineNum);
        }
        if (ZYPCommonFunc.hasValue(param.dsContrNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsContrNum, param.dsContrNum);
        }
        if (ZYPCommonFunc.hasValue(param.contrEffFromDt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.contrEffFromDt, param.contrEffFromDt);
        }
        if (ZYPCommonFunc.hasValue(param.contrEffThruDt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.contrEffThruDt, param.contrEffThruDt);
        }
        if (ZYPCommonFunc.hasValue(param.custIssPoNum)) {
            if (PROC_MODE_51.equals(oldDsAssetMstrTMsg.procModeCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.poOrdNum, ZYPCommonFunc.subByteString(param.custIssPoNum.getValue(), 8));
            } else {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.custIssPoNum, param.custIssPoNum);
            }
        }
        // END 2018/07/20 J.Kim [QC#24950,ADD]
        if (ZYPCommonFunc.hasValue(param.dtlCmntTxt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dtlCmntTxt, param.dtlCmntTxt);
        }
        // START 2018/08/03 J.Kim [QC#26901,ADD]
        if (ZYPCommonFunc.hasValue(param.amtChngRsnTpCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.amtChngRsnTpCd, param.amtChngRsnTpCd);
        }
        if (ZYPCommonFunc.hasValue(param.adjCoaAcctCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.adjCoaAcctCd, param.adjCoaAcctCd);
        }
        // END 2018/08/03 J.Kim [QC#26901,ADD]

        S21ApiTBLAccessor.update(updDsAssetMstrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updDsAssetMstrTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", updDsAssetMstrTMsg.dsAssetMstrPk.getValue().toString());
            setDBAcsErrMsg(param, NASM0007E, new String[] {TBL_DS_ASSET_MSTR, pKey });

            return false;
        }

        // START 2018/07/20 J.Kim [QC#24950,ADD]
        if (param.prntDsAssetMstrPk.getValue().compareTo(updDsAssetMstrTMsg.dsAssetMstrPk.getValue()) != 0) {
            return true;
        }
        // END 2018/07/20 J.Kim [QC#24950,ADD]

        setAssetTrackingData(param, updDsAssetMstrTMsg, "mdseCd", oldDsAssetMstrTMsg.mdseCd.getValue(), updDsAssetMstrTMsg.mdseCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "serNum", oldDsAssetMstrTMsg.serNum.getValue(), updDsAssetMstrTMsg.serNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "svcMachMstrPk", oldDsAssetMstrTMsg.svcMachMstrPk.getValue(), updDsAssetMstrTMsg.svcMachMstrPk.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "shpgPlnNum", oldDsAssetMstrTMsg.shpgPlnNum.getValue(), updDsAssetMstrTMsg.shpgPlnNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "depcMthNum", oldDsAssetMstrTMsg.depcMthNum.getValue(), updDsAssetMstrTMsg.depcMthNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "depcStartDt", oldDsAssetMstrTMsg.depcStartDt.getValue(), updDsAssetMstrTMsg.depcStartDt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "slsRepTocCd", oldDsAssetMstrTMsg.slsRepTocCd.getValue(), updDsAssetMstrTMsg.slsRepTocCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "rsdlValAmt", oldDsAssetMstrTMsg.rsdlValAmt.getValue(), updDsAssetMstrTMsg.rsdlValAmt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetStsCd", oldDsAssetMstrTMsg.assetStsCd.getValue(), updDsAssetMstrTMsg.assetStsCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "rtrnWhCd", oldDsAssetMstrTMsg.rtrnWhCd.getValue(), updDsAssetMstrTMsg.rtrnWhCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "prntDsAssetMstrPk", oldDsAssetMstrTMsg.prntDsAssetMstrPk.getValue(), updDsAssetMstrTMsg.prntDsAssetMstrPk.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "dsAssetDescTxt", oldDsAssetMstrTMsg.dsAssetDescTxt.getValue(), updDsAssetMstrTMsg.dsAssetDescTxt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetTagNum", oldDsAssetMstrTMsg.assetTagNum.getValue(), updDsAssetMstrTMsg.assetTagNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "svcConfigMstrPk", oldDsAssetMstrTMsg.svcConfigMstrPk.getValue(), updDsAssetMstrTMsg.svcConfigMstrPk.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "asgDtlCmntTxt", oldDsAssetMstrTMsg.asgDtlCmntTxt.getValue(), updDsAssetMstrTMsg.asgDtlCmntTxt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "finDtlCmntTxt", oldDsAssetMstrTMsg.finDtlCmntTxt.getValue(), updDsAssetMstrTMsg.finDtlCmntTxt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "firstLineAddr", oldDsAssetMstrTMsg.firstLineAddr.getValue(), updDsAssetMstrTMsg.firstLineAddr.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "ctyAddr", oldDsAssetMstrTMsg.ctyAddr.getValue(), updDsAssetMstrTMsg.ctyAddr.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "stCd", oldDsAssetMstrTMsg.stCd.getValue(), updDsAssetMstrTMsg.stCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "postCd", oldDsAssetMstrTMsg.postCd.getValue(), updDsAssetMstrTMsg.postCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "totAssetQty", oldDsAssetMstrTMsg.totAssetQty.getValue(), updDsAssetMstrTMsg.totAssetQty.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "expCoaCmpyCd", oldDsAssetMstrTMsg.expCoaCmpyCd.getValue(), updDsAssetMstrTMsg.expCoaCmpyCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "expCoaBrCd", oldDsAssetMstrTMsg.expCoaBrCd.getValue(), updDsAssetMstrTMsg.expCoaBrCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "expCoaCcCd", oldDsAssetMstrTMsg.expCoaCcCd.getValue(), updDsAssetMstrTMsg.expCoaCcCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "expCoaAcctCd", oldDsAssetMstrTMsg.expCoaAcctCd.getValue(), updDsAssetMstrTMsg.expCoaAcctCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "expCoaProdCd", oldDsAssetMstrTMsg.expCoaProdCd.getValue(), updDsAssetMstrTMsg.expCoaProdCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "expCoaChCd", oldDsAssetMstrTMsg.expCoaChCd.getValue(), updDsAssetMstrTMsg.expCoaChCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "expCoaAfflCd", oldDsAssetMstrTMsg.expCoaAfflCd.getValue(), updDsAssetMstrTMsg.expCoaAfflCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "expCoaProjCd", oldDsAssetMstrTMsg.expCoaProjCd.getValue(), updDsAssetMstrTMsg.expCoaProjCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "expCoaExtnCd", oldDsAssetMstrTMsg.expCoaExtnCd.getValue(), updDsAssetMstrTMsg.expCoaExtnCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "coaMdseTpCd", oldDsAssetMstrTMsg.coaMdseTpCd.getValue(), updDsAssetMstrTMsg.coaMdseTpCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoOrdNum", oldDsAssetMstrTMsg.cpoOrdNum.getValue(), updDsAssetMstrTMsg.cpoOrdNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoDtlLineNum", oldDsAssetMstrTMsg.cpoDtlLineNum.getValue(), updDsAssetMstrTMsg.cpoDtlLineNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoDtlLineSubNum", oldDsAssetMstrTMsg.cpoDtlLineSubNum.getValue(), updDsAssetMstrTMsg.cpoDtlLineSubNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "sellToCustCd", oldDsAssetMstrTMsg.sellToCustCd.getValue(), updDsAssetMstrTMsg.sellToCustCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "contrEffFromDt", oldDsAssetMstrTMsg.contrEffFromDt.getValue(), updDsAssetMstrTMsg.contrEffFromDt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "contrEffThruDt", oldDsAssetMstrTMsg.contrEffThruDt.getValue(), updDsAssetMstrTMsg.contrEffThruDt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "custIssPoNum", oldDsAssetMstrTMsg.custIssPoNum.getValue(), updDsAssetMstrTMsg.custIssPoNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "vndNm", oldDsAssetMstrTMsg.vndCd.getValue(), updDsAssetMstrTMsg.vndCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "poOrdNum", oldDsAssetMstrTMsg.poOrdNum.getValue(), updDsAssetMstrTMsg.poOrdNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "dtlCmntTxt", oldDsAssetMstrTMsg.dtlCmntTxt.getValue(), updDsAssetMstrTMsg.dtlCmntTxt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "amtChngRsnTpCd", oldDsAssetMstrTMsg.amtChngRsnTpCd.getValue(), updDsAssetMstrTMsg.amtChngRsnTpCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "adjCoaAcctCd", oldDsAssetMstrTMsg.adjCoaAcctCd.getValue(), updDsAssetMstrTMsg.adjCoaAcctCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "shipToCustAcctCd", oldDsAssetMstrTMsg.shipToCustAcctCd.getValue(), updDsAssetMstrTMsg.shipToCustAcctCd.getValue(), trkItemMap);

        return true;
    }

    /**
     * Update DS Asset Master. Depreciation
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     * @param trkItemMap Map<String, String>
     * @return boolean
     */
    private boolean updDsAssetMstrDepc(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, NLZC305001_updDtlListPMsg param, Map<String, String> trkItemMap) {

        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.depcCnt, param.depcCnt);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.curValAmt, param.curValAmt);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.lastDepcYrMth, param.lastDepcYrMth);

        S21ApiTBLAccessor.update(updDsAssetMstrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updDsAssetMstrTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", updDsAssetMstrTMsg.dsAssetMstrPk.getValue().toString());
            setDBAcsErrMsg(param, NASM0007E, new String[] {TBL_DS_ASSET_MSTR, pKey });

            return false;
        }

        // START 2018/08/23 J.Kim [QC#22267,ADD]
        if (oldDsAssetMstrTMsg.dsAssetMstrPk.getValue().compareTo(oldDsAssetMstrTMsg.prntDsAssetMstrPk.getValue()) != 0) {
            return true;
        }
        // END 2018/08/23 J.Kim [QC#22267,ADD]
        setAssetTrackingData(param, updDsAssetMstrTMsg, "depcCnt", oldDsAssetMstrTMsg.depcCnt.getValue(), updDsAssetMstrTMsg.depcCnt.getValue(), trkItemMap);
        // START 2018/08/23 J.Kim [QC#22267,DEL]
        // setAssetTrackingData(param, updDsAssetMstrTMsg, "curValAmt", oldDsAssetMstrTMsg.curValAmt.getValue(), updDsAssetMstrTMsg.curValAmt.getValue(), trkItemMap);
        // END 2018/08/23 J.Kim [QC#22267,DEL]
        setAssetTrackingData(param, updDsAssetMstrTMsg, "lastDepcYrMth", oldDsAssetMstrTMsg.lastDepcYrMth.getValue(), updDsAssetMstrTMsg.lastDepcYrMth.getValue(), trkItemMap);

        return true;
    }

    /**
     * Update DS Asset Master. Ship from Asset WH
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     * @param trkItemMap Map<String, String>
     * @param dsAssetStgngTMsg DS_ASSET_STGNGTMsg
     * @return boolean
     */
    private boolean updDsAssetMstrShipFromAssetWH(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, NLZC305001_updDtlListPMsg param, Map<String, String> trkItemMap, DS_ASSET_STGNGTMsg dsAssetStgngTMsg) {

        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.shpgPlnNum, dsAssetStgngTMsg.shpgPlnNum);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.invtyTrxPk, dsAssetStgngTMsg.invtyTrxPk);
        updDsAssetMstrTMsg.rtrnWhCd.clear();

        // START 2018/02/08 J.Kim [QC#23770,MOD]
        //// START 2018/02/07 J.Kim [QC#23890,MOD]
        ////if (ZYPCommonFunc.hasValue(updDsAssetMstrTMsg.svcMachMstrPk)) {
        //String procMode = dsAssetStgngTMsg.procModeCd.getValue();
        //if (!PROC_MODE_51.equals(procMode)) {
        //    // END 2018/02/07 J.Kim [QC#23890,MOD]
        //
        //    updDsAssetMstrTMsg.cpoOrdNum.clear();
        //    updDsAssetMstrTMsg.cpoDtlLineNum.clear();
        //    updDsAssetMstrTMsg.cpoDtlLineSubNum.clear();
        //    updDsAssetMstrTMsg.dsOrdPosnNum.clear();
        //    updDsAssetMstrTMsg.shipToCustAcctCd.clear();
        //    // START 2018/01/15 J.Kim [QC#21965,DEL]
        //    // updDsAssetMstrTMsg.shipToCustCd.clear();
        //    // END 2018/01/15 J.Kim [QC#21965,DEL]
        //   updDsAssetMstrTMsg.sellToCustCd.clear();
        //    updDsAssetMstrTMsg.sldToCustLocCd.clear();
        //
        //} else {
        // END 2018/02/08 J.Kim [QC#23770,MOD]
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoOrdNum, dsAssetStgngTMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineNum, dsAssetStgngTMsg.cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineSubNum, dsAssetStgngTMsg.cpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsOrdPosnNum, dsAssetStgngTMsg.dsOrdPosnNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.shipToCustAcctCd, dsAssetStgngTMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.shipToCustCd, dsAssetStgngTMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.sellToCustCd, dsAssetStgngTMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.sldToCustLocCd, dsAssetStgngTMsg.soldToCustLocCd);
        // START 2018/02/08 J.Kim [QC#23770,MOD]
        //}
        // END 2018/02/08 J.Kim [QC#23770,MOD]

        // START 2018/01/18 J.Kim [QC#17985,MOD]
        // ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.invNum, dsAssetStgngTMsg.invNum);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.apVndInvNum, dsAssetStgngTMsg.apVndInvNum);
        // END 2018/01/18 J.Kim [QC#17985,MOD]
        // START 2018/04/10 J.Kim [QC#25381,ADD]
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.apVndInvLineNum, dsAssetStgngTMsg.apVndInvLineNum);
        // END 2018/04/10 J.Kim [QC#25381,ADD]
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.invDt, dsAssetStgngTMsg.invDt);

        if (ZYPCommonFunc.hasValue(param.svcConfigMstrPk)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.svcConfigMstrPk, param.svcConfigMstrPk);
        }
        // START 2018/02/07 J.Kim [QC#23770,ADD]
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.slsRepTocCd, dsAssetStgngTMsg.slsRepTocCd);
        if (!ZYPCommonFunc.hasValue(param.svcConfigMstrPk) && ZYPCommonFunc.hasValue(dsAssetStgngTMsg.toSvcConfigMstrPk)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.svcConfigMstrPk, dsAssetStgngTMsg.toSvcConfigMstrPk);
        }
        // END 2018/02/07 J.Kim [QC#23770,ADD]
        // START 2018/06/25 J.Kim [QC#24844, ADD]
        if (ZYPCommonFunc.hasValue(dsAssetStgngTMsg.dsContrNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsContrNum, dsAssetStgngTMsg.dsContrNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.contrEffFromDt, dsAssetStgngTMsg.contrEffFromDt);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.contrEffThruDt, dsAssetStgngTMsg.contrEffThruDt);
        }
        if (ZYPCommonFunc.hasValue(dsAssetStgngTMsg.custIssPoNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.custIssPoNum, dsAssetStgngTMsg.custIssPoNum);
        }
        // END 2018/06/25 J.Kim [QC#24844, ADD]

        S21ApiTBLAccessor.update(updDsAssetMstrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updDsAssetMstrTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", updDsAssetMstrTMsg.dsAssetMstrPk.getValue().toString());
            setDBAcsErrMsg(param, NASM0007E, new String[] {TBL_DS_ASSET_MSTR, pKey });

            return false;
        }

        // START 2018/07/20 J.Kim [QC#24950,ADD]
        if (updDsAssetMstrTMsg.prntDsAssetMstrPk.getValue().compareTo(param.dsAssetMstrPk.getValue()) != 0) {
            return true;
        }
        // END 2018/07/20 J.Kim [QC#24950,ADD]

        setAssetTrackingData(param, updDsAssetMstrTMsg, "shpgPlnNum", oldDsAssetMstrTMsg.shpgPlnNum.getValue(), updDsAssetMstrTMsg.shpgPlnNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "invtyTrxPk", oldDsAssetMstrTMsg.invtyTrxPk.getValue(), updDsAssetMstrTMsg.invtyTrxPk.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "rtrnWhCd", oldDsAssetMstrTMsg.rtrnWhCd.getValue(), updDsAssetMstrTMsg.rtrnWhCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoOrdNum", oldDsAssetMstrTMsg.cpoOrdNum.getValue(), updDsAssetMstrTMsg.cpoOrdNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoDtlLineNum", oldDsAssetMstrTMsg.cpoDtlLineNum.getValue(), updDsAssetMstrTMsg.cpoDtlLineNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoDtlLineSubNum", oldDsAssetMstrTMsg.cpoDtlLineSubNum.getValue(), updDsAssetMstrTMsg.cpoDtlLineSubNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "dsOrdPosnNum", oldDsAssetMstrTMsg.dsOrdPosnNum.getValue(), updDsAssetMstrTMsg.dsOrdPosnNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "shipToCustAcctCd", oldDsAssetMstrTMsg.shipToCustAcctCd.getValue(), updDsAssetMstrTMsg.shipToCustAcctCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "shipToCustCd", oldDsAssetMstrTMsg.shipToCustCd.getValue(), updDsAssetMstrTMsg.shipToCustCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "sellToCustCd", oldDsAssetMstrTMsg.sellToCustCd.getValue(), updDsAssetMstrTMsg.sellToCustCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "sldToCustLocCd", oldDsAssetMstrTMsg.sldToCustLocCd.getValue(), updDsAssetMstrTMsg.sldToCustLocCd.getValue(), trkItemMap);
        // START 2018/08/24 J.Kim [QC#22267,DEL]
        //// START 2018/01/18 J.Kim [QC#17985,MOD]
        //// setAssetTrackingData(param, updDsAssetMstrTMsg, "invNum", oldDsAssetMstrTMsg.invNum.getValue(), updDsAssetMstrTMsg.invNum.getValue(), trkItemMap);
        //setAssetTrackingData(param, updDsAssetMstrTMsg, "apVndInvNum", oldDsAssetMstrTMsg.apVndInvNum.getValue(), updDsAssetMstrTMsg.apVndInvNum.getValue(), trkItemMap);
        //// END 2018/01/18 J.Kim [QC#17985,MOD]
        // END 2018/08/24 J.Kim [QC#22267,DEL]
        setAssetTrackingData(param, updDsAssetMstrTMsg, "invDt", oldDsAssetMstrTMsg.invDt.getValue(), updDsAssetMstrTMsg.invDt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "svcConfigMstrPk", oldDsAssetMstrTMsg.svcConfigMstrPk.getValue(), updDsAssetMstrTMsg.svcConfigMstrPk.getValue(), trkItemMap);
        // START 2018/06/25 J.Kim [QC#24844, ADD]
        setAssetTrackingData(param, updDsAssetMstrTMsg, "contrEffFromDt", oldDsAssetMstrTMsg.contrEffFromDt.getValue(), updDsAssetMstrTMsg.contrEffFromDt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "contrEffThruDt", oldDsAssetMstrTMsg.contrEffThruDt.getValue(), updDsAssetMstrTMsg.contrEffThruDt.getValue(), trkItemMap);
        // END 2018/06/25 J.Kim [QC#24844, ADD]
        setAssetTrackingData(param, updDsAssetMstrTMsg, "dsContrNum", oldDsAssetMstrTMsg.dsContrNum.getValue(), updDsAssetMstrTMsg.dsContrNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "custIssPoNum", oldDsAssetMstrTMsg.custIssPoNum.getValue(), updDsAssetMstrTMsg.custIssPoNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "curValAmt", oldDsAssetMstrTMsg.curValAmt.getValue(), updDsAssetMstrTMsg.curValAmt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "initBookAmt", oldDsAssetMstrTMsg.initBookAmt.getValue(), updDsAssetMstrTMsg.initBookAmt.getValue(), trkItemMap);
        return true;
    }

    /**
     * Update DS Asset Master. Asset Activate
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     * @param trkItemMap Map<String, String>
     * @return boolean
     */
    private boolean updDsAssetMstrAssetActivate(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, NLZC305001_updDtlListPMsg param, Map<String, String> trkItemMap) {

        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.actvAssetFlg, ZYPConstant.FLG_ON_Y);

        if (ASSET_STS.PENDING.equals(oldDsAssetMstrTMsg.assetStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetStsCd, ASSET_STS.ACTIVATE);
        }

        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetPostFlg, ZYPConstant.FLG_ON_Y);

        S21ApiTBLAccessor.update(updDsAssetMstrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updDsAssetMstrTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", updDsAssetMstrTMsg.dsAssetMstrPk.getValue().toString());
            setDBAcsErrMsg(param, NASM0007E, new String[] {TBL_DS_ASSET_MSTR, pKey });

            return false;
        }

        // START 2018/07/20 J.Kim [QC#24950,ADD]
        if (BIZ_ID.equals(param.xxBizAppId.getValue())) {
            if (param.prntDsAssetMstrPk.getValue().compareTo(updDsAssetMstrTMsg.dsAssetMstrPk.getValue()) != 0) {
                return true;
            }
        }
        // END 2018/07/20 J.Kim [QC#24950,ADD]

        setAssetTrackingData(param, updDsAssetMstrTMsg, "actvAssetFlg", oldDsAssetMstrTMsg.actvAssetFlg.getValue(), updDsAssetMstrTMsg.actvAssetFlg.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetStsCd", oldDsAssetMstrTMsg.assetStsCd.getValue(), updDsAssetMstrTMsg.assetStsCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetPostFlg", oldDsAssetMstrTMsg.assetPostFlg.getValue(), updDsAssetMstrTMsg.assetPostFlg.getValue(), trkItemMap);

        return true;
    }

    /**
     * Update DS Asset Master. Before Activate Current
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     * @param trkItemMap Map<String, String>
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @return boolean
     */
    private boolean updDsAssetMstrBeforeActivateCur(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, NLZC305001_updDtlListPMsg param, Map<String, String> trkItemMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {

        if (ZYPCommonFunc.hasValue(param.mdseCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.mdseCd, param.mdseCd);
        }

        if (ZYPCommonFunc.hasValue(param.serNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.serNum, param.serNum);
        }

        if (ZYPCommonFunc.hasValue(param.svcMachMstrPk)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.svcMachMstrPk, param.svcMachMstrPk);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.shpgPlnNum, svcMachMstrTMsg.shpgPlnNum);
        }

        if (ZYPCommonFunc.hasValue(param.initBookAmt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.initBookAmt, param.initBookAmt);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.curValAmt, param.initBookAmt);
        }

        if (ZYPCommonFunc.hasValue(param.depcMthNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.depcMthNum, param.depcMthNum);
        }

        if (ZYPCommonFunc.hasValue(param.depcStartDt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.depcStartDt, param.depcStartDt);
        }

        if (ZYPCommonFunc.hasValue(param.slsRepTocCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.slsRepTocCd, param.slsRepTocCd);
        }

        if (ZYPCommonFunc.hasValue(param.rsdlValAmt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.rsdlValAmt, param.rsdlValAmt);
        }

        if (ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {
            if (param.prntDsAssetMstrPk.getValue().compareTo(updDsAssetMstrTMsg.dsAssetMstrPk.getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetStsCd, ASSET_STS.PENDING);
            } else {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetStsCd, ASSET_STS.MERGED);
            }
        }

        if (ZYPCommonFunc.hasValue(param.cpoOrdNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoOrdNum, param.cpoOrdNum);
        }

        if (ZYPCommonFunc.hasValue(param.cpoDtlLineNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineNum, param.cpoDtlLineNum);
        }

        if (ZYPCommonFunc.hasValue(param.cpoDtlLineSubNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineSubNum, param.cpoDtlLineSubNum);
        }

        if (ZYPCommonFunc.hasValue(param.cpoOrdNum) //
                && ZYPCommonFunc.hasValue(param.cpoDtlLineNum) //
                && ZYPCommonFunc.hasValue(param.cpoDtlLineSubNum)) {

            CPO_DTLTMsg cpoDtlTMsg = getCpoDtl(param.cpoOrdNum.getValue(), param.cpoDtlLineNum.getValue(), param.cpoDtlLineSubNum.getValue());
            if (cpoDtlTMsg != null) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsOrdPosnNum, cpoDtlTMsg.dsOrdPosnNum);
            }
        }

        if (ZYPCommonFunc.hasValue(param.invNum)) {
            // START 2018/01/18 J.Kim [QC#17985,MOD]
            // ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.invNum, param.invNum);
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.apVndInvNum, param.invNum);
            // END 2018/01/18 J.Kim [QC#17985,MOD]
        }

        if (ZYPCommonFunc.hasValue(param.prntDsAssetMstrPk)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.prntDsAssetMstrPk, param.prntDsAssetMstrPk);
        }

        if (ZYPCommonFunc.hasValue(param.vndCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.vndCd, param.vndCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetDescTxt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsAssetDescTxt, param.assetDescTxt);
        }

        if (ZYPCommonFunc.hasValue(param.assetTagNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetTagNum, param.assetTagNum);
        }

        if (ZYPCommonFunc.hasValue(param.asgDtlCmntTxt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.asgDtlCmntTxt, param.asgDtlCmntTxt);
        }

        if (ZYPCommonFunc.hasValue(param.finDtlCmntTxt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.finDtlCmntTxt, param.finDtlCmntTxt);
        }

        if (ZYPCommonFunc.hasValue(param.firstLineAddr)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.firstLineAddr, param.firstLineAddr);
        }

        if (ZYPCommonFunc.hasValue(param.ctyAddr)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.ctyAddr, param.ctyAddr);
        }

        if (ZYPCommonFunc.hasValue(param.stCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.stCd, param.stCd);
        }

        if (ZYPCommonFunc.hasValue(param.postCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.postCd, param.postCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaCmpyCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaCmpyCd, param.coaCmpyCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaBrCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaBrCd, param.coaBrCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaCcCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaCcCd, param.coaCcCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaAcctCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaAcctCd, param.coaAcctCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaProdCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaProdCd, param.coaProdCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaChCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaChCd, param.coaChCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaAfflCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaAfflCd, param.coaAfflCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaProjCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaProjCd, param.coaProjCd);
        }

        if (ZYPCommonFunc.hasValue(param.coaExtnCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaExtnCd, param.coaExtnCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaCmpyCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaCmpyCd, param.assetCoaCmpyCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaBrCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaBrCd, param.assetCoaBrCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaCcCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaCcCd, param.assetCoaCcCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaAcctCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaAcctCd, param.assetCoaAcctCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaProdCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaProdCd, param.assetCoaProdCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaChCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaChCd, param.assetCoaChCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaAfflCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaAfflCd, param.assetCoaAfflCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaProjCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaProjCd, param.assetCoaProjCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetCoaExtnCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaExtnCd, param.assetCoaExtnCd);
        }

        if (ZYPCommonFunc.hasValue(param.assetLeaseNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetLeaseNum, param.assetLeaseNum);
        }

        if (ZYPCommonFunc.hasValue(param.leaseStartDt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.leaseStartDt, param.leaseStartDt);
        }

        if (ZYPCommonFunc.hasValue(param.leaseEndDt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.leaseEndDt, param.leaseEndDt);
        }

        if (ZYPCommonFunc.hasValue(param.totAssetQty)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.totAssetQty, param.totAssetQty);
        }

        if (ZYPCommonFunc.hasValue(param.coaMdseTpCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.coaMdseTpCd, param.coaMdseTpCd);
        }

        if (ZYPCommonFunc.hasValue(param.poOrdNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.poOrdNum, param.poOrdNum);
        }
        // START 2018/06/25 J.Kim [QC#24844, ADD]
        if (ZYPCommonFunc.hasValue(param.custIssPoNum)) {
            if (PROC_MODE_51.equals(oldDsAssetMstrTMsg.procModeCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.poOrdNum, ZYPCommonFunc.subByteString(param.custIssPoNum.getValue(), 8));
            } else {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.custIssPoNum, param.custIssPoNum);
            }
        }

        if (ZYPCommonFunc.hasValue(param.dsContrNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsContrNum, param.dsContrNum);
        }

        if (ZYPCommonFunc.hasValue(param.contrEffFromDt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.contrEffFromDt, param.contrEffFromDt);
        }

        if (ZYPCommonFunc.hasValue(param.contrEffThruDt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.contrEffThruDt, param.contrEffThruDt);
        }
        // END 2018/06/25 J.Kim [QC#24844, ADD]
        // START 2018/08/03 J.Kim [QC#26901, ADD]
        if (ZYPCommonFunc.hasValue(param.amtChngRsnTpCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.amtChngRsnTpCd, param.amtChngRsnTpCd);
        }

        if (ZYPCommonFunc.hasValue(param.adjCoaAcctCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.adjCoaAcctCd, param.adjCoaAcctCd);
        }
        // END 2018/08/03 J.Kim [QC#26901, ADD]

        S21ApiTBLAccessor.update(updDsAssetMstrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updDsAssetMstrTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", updDsAssetMstrTMsg.dsAssetMstrPk.getValue().toString());
            setDBAcsErrMsg(param, NASM0007E, new String[] {TBL_DS_ASSET_MSTR, pKey });

            return false;
        }

        return true;
    }

    /**
     * Update DS Asset Master Before Activate Parent
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     * @param trkItemMap Map<String, String>
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param totOrigStdCostAmt BigDecimal
     * @param totCurValAmt BigDecimal
     * @param splAmtMap Map<String, BigDecimal>
     * @return
     */
    private boolean updDsAssetMstrBeforeActivateParent(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, //
            DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, //
            NLZC305001_updDtlListPMsg param, //
            Map<String, String> trkItemMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg, //
            BigDecimal totOrigStdCostAmt, //
            BigDecimal totCurValAmt, //
            Map<String, BigDecimal> splAmtMap) {

        if (param.prntDsAssetMstrPk.getValue().compareTo(updDsAssetMstrTMsg.dsAssetMstrPk.getValue()) == 0) {
            // Current Ds_ASSET_MSTR

            if (ZYPCommonFunc.hasValue(param.mdseCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.mdseCd, param.mdseCd);
            }

            if (ZYPCommonFunc.hasValue(param.serNum)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.serNum, param.serNum);
            }

            if (ZYPCommonFunc.hasValue(param.svcMachMstrPk)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.svcMachMstrPk, param.svcMachMstrPk);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.shpgPlnNum, svcMachMstrTMsg.shpgPlnNum);
            }

            if (ZYPCommonFunc.hasValue(param.cpoOrdNum)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoOrdNum, param.cpoOrdNum);
            }

            if (ZYPCommonFunc.hasValue(param.cpoDtlLineNum)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineNum, param.cpoDtlLineNum);
            }

            if (ZYPCommonFunc.hasValue(param.cpoDtlLineSubNum)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.cpoDtlLineSubNum, param.cpoDtlLineSubNum);
            }

            if (ZYPCommonFunc.hasValue(param.cpoOrdNum) //
                    && ZYPCommonFunc.hasValue(param.cpoDtlLineNum) //
                    && ZYPCommonFunc.hasValue(param.cpoDtlLineSubNum)) {

                CPO_DTLTMsg cpoDtlTMsg = getCpoDtl(param.cpoOrdNum.getValue(), param.cpoDtlLineNum.getValue(), param.cpoDtlLineSubNum.getValue());
                if (cpoDtlTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsOrdPosnNum, cpoDtlTMsg.dsOrdPosnNum);
                }
            }

            if (ZYPCommonFunc.hasValue(param.invNum)) {
                // START 2018/01/18 J.Kim [QC#17985,MOD]
                // ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.invNum, param.invNum);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.apVndInvNum, param.invNum);
                // END 2018/01/18 J.Kim [QC#17985,MOD]
            }

            if (ZYPCommonFunc.hasValue(param.vndCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.vndCd, param.vndCd);
            }

            if (ZYPCommonFunc.hasValue(param.poOrdNum)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.poOrdNum, param.poOrdNum);
            }

            if (ZYPCommonFunc.hasValue(param.assetDescTxt)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsAssetDescTxt, param.assetDescTxt);
            }

            if (ZYPCommonFunc.hasValue(param.assetTagNum)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetTagNum, param.assetTagNum);
            }

            if (ZYPCommonFunc.hasValue(param.asgDtlCmntTxt)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.asgDtlCmntTxt, param.asgDtlCmntTxt);
            }

            if (ZYPCommonFunc.hasValue(param.finDtlCmntTxt)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.finDtlCmntTxt, param.finDtlCmntTxt);
            }

            if (ZYPCommonFunc.hasValue(param.firstLineAddr)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.firstLineAddr, param.firstLineAddr);
            }

            if (ZYPCommonFunc.hasValue(param.ctyAddr)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.ctyAddr, param.ctyAddr);
            }

            if (ZYPCommonFunc.hasValue(param.stCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.stCd, param.stCd);
            }

            if (ZYPCommonFunc.hasValue(param.postCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.postCd, param.postCd);
            }

            if (ZYPCommonFunc.hasValue(param.coaCmpyCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaCmpyCd, param.coaCmpyCd);
            }

            if (ZYPCommonFunc.hasValue(param.coaBrCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaBrCd, param.coaBrCd);
            }

            if (ZYPCommonFunc.hasValue(param.coaCcCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaCcCd, param.coaCcCd);
            }

            if (ZYPCommonFunc.hasValue(param.coaAcctCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaAcctCd, param.coaAcctCd);
            }

            if (ZYPCommonFunc.hasValue(param.coaProdCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaProdCd, param.coaProdCd);
            }

            if (ZYPCommonFunc.hasValue(param.coaChCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaChCd, param.coaChCd);
            }

            if (ZYPCommonFunc.hasValue(param.coaAfflCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaAfflCd, param.coaAfflCd);
            }

            if (ZYPCommonFunc.hasValue(param.coaProjCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaProjCd, param.coaProjCd);
            }

            if (ZYPCommonFunc.hasValue(param.coaExtnCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.expCoaExtnCd, param.coaExtnCd);
            }

            if (ZYPCommonFunc.hasValue(param.assetCoaCmpyCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaCmpyCd, param.assetCoaCmpyCd);
            }

            if (ZYPCommonFunc.hasValue(param.assetCoaBrCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaBrCd, param.assetCoaBrCd);
            }

            if (ZYPCommonFunc.hasValue(param.assetCoaCcCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaCcCd, param.assetCoaCcCd);
            }

            if (ZYPCommonFunc.hasValue(param.assetCoaAcctCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaAcctCd, param.assetCoaAcctCd);
            }

            if (ZYPCommonFunc.hasValue(param.assetCoaProdCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaProdCd, param.assetCoaProdCd);
            }

            if (ZYPCommonFunc.hasValue(param.assetCoaChCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaChCd, param.assetCoaChCd);
            }

            if (ZYPCommonFunc.hasValue(param.assetCoaAfflCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaAfflCd, param.assetCoaAfflCd);
            }

            if (ZYPCommonFunc.hasValue(param.assetCoaProjCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaProjCd, param.assetCoaProjCd);
            }

            if (ZYPCommonFunc.hasValue(param.assetCoaExtnCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetCoaExtnCd, param.assetCoaExtnCd);
            }

            if (ZYPCommonFunc.hasValue(param.assetLeaseNum)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetLeaseNum, param.assetLeaseNum);
            }

            if (ZYPCommonFunc.hasValue(param.leaseStartDt)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.leaseStartDt, param.leaseStartDt);
            }

            if (ZYPCommonFunc.hasValue(param.leaseEndDt)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.leaseEndDt, param.leaseEndDt);
            }

            if (ZYPCommonFunc.hasValue(param.totAssetQty)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.totAssetQty, param.totAssetQty);
            }

            if (ZYPCommonFunc.hasValue(param.coaMdseTpCd)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.coaMdseTpCd, param.coaMdseTpCd);
            }

            // START 2018/06/25 J.Kim [QC#24844, ADD]
            if (ZYPCommonFunc.hasValue(param.custIssPoNum)) {
                if (PROC_MODE_51.equals(oldDsAssetMstrTMsg.procModeCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.poOrdNum, ZYPCommonFunc.subByteString(param.custIssPoNum.getValue(), 8));
                } else {
                    ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.custIssPoNum, param.custIssPoNum);
                }
            }

            if (ZYPCommonFunc.hasValue(param.dsContrNum)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dsContrNum, param.dsContrNum);
            }

            if (ZYPCommonFunc.hasValue(param.contrEffFromDt)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.contrEffFromDt, param.contrEffFromDt);
            }

            if (ZYPCommonFunc.hasValue(param.contrEffThruDt)) {
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.contrEffThruDt, param.contrEffThruDt);
            }
            // END 2018/06/25 J.Kim [QC#24844, ADD]
        }

        if (ZYPCommonFunc.hasValue(param.initBookAmt)) {
            int carcDeclPlace = ZYPCodeDataUtil.getNumConstValue(ASSET_COST_CARC_DECIMAL_PLACE, glblCmpyCd).intValue();
            String costRoundMode = ZYPCodeDataUtil.getVarCharConstValue(ASSET_COST_ROUNDING_MODE, glblCmpyCd);

            int roundingMode = BigDecimal.ROUND_DOWN;

            if (ROUND_UP.equals(costRoundMode)) {

                roundingMode = BigDecimal.ROUND_UP;

            } else if (ROUND_HALF_UP.equals(costRoundMode)) {

                roundingMode = BigDecimal.ROUND_HALF_UP;
            }

            if (ZYPCommonFunc.hasValue(splAmtMap.get(MAX_AMT_DS_ASSET_MSTR_PK)) && //
                    splAmtMap.get(MAX_AMT_DS_ASSET_MSTR_PK).compareTo(updDsAssetMstrTMsg.dsAssetMstrPk.getValue()) == 0) {

                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.initBookAmt, splAmtMap.get(MAX_SPL_AMT));
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.curValAmt, updDsAssetMstrTMsg.initBookAmt);

            } else {

                BigDecimal paramInitBookAmt = param.initBookAmt.getValue();
                BigDecimal curValAmt = oldDsAssetMstrTMsg.curValAmt.getValue();
                BigDecimal origStdCostAmt = oldDsAssetMstrTMsg.origStdCostAmt.getValue();
                BigDecimal initBookAmt = BigDecimal.ZERO;

                if (BigDecimal.ZERO.compareTo(totCurValAmt) != 0 && BigDecimal.ZERO.compareTo(curValAmt) != 0) {
                    // START 2018/09/21 J.Kim [QC#28341, MOD]
                    // initBookAmt = paramInitBookAmt.divide(totCurValAmt.divide(curValAmt, carcDeclPlace, roundingMode), carcDeclPlace, roundingMode);
                    initBookAmt = paramInitBookAmt.divide(totOrigStdCostAmt.divide(origStdCostAmt, COST_SCALE, roundingMode), carcDeclPlace, roundingMode);
                    // END 2018/09/21 J.Kim [QC#28341, MOD]
                } else if (param.prntDsAssetMstrPk.getValue().compareTo(updDsAssetMstrTMsg.dsAssetMstrPk.getValue()) == 0) {
                    initBookAmt = paramInitBookAmt;
                }

                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.initBookAmt, initBookAmt);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.curValAmt, initBookAmt);

            }
        }

        if (ZYPCommonFunc.hasValue(param.depcMthNum)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.depcMthNum, param.depcMthNum);
        }

        if (ZYPCommonFunc.hasValue(param.depcStartDt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.depcStartDt, param.depcStartDt);
        }

        if (ZYPCommonFunc.hasValue(param.slsRepTocCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.slsRepTocCd, param.slsRepTocCd);
        }

        if (ZYPCommonFunc.hasValue(param.rsdlValAmt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.rsdlValAmt, param.rsdlValAmt);
        }

        if (ZYPCommonFunc.hasValue(param.dtlCmntTxt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.dtlCmntTxt, param.dtlCmntTxt);
        }

        if (ZYPCommonFunc.hasValue(param.shipToCustAcctCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.shipToCustAcctCd, param.shipToCustAcctCd);
        }

        // START 2018/08/03 J.Kim [QC#26901, ADD]
        if (ZYPCommonFunc.hasValue(param.amtChngRsnTpCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.amtChngRsnTpCd, param.amtChngRsnTpCd);
        }

        if (ZYPCommonFunc.hasValue(param.adjCoaAcctCd)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.adjCoaAcctCd, param.adjCoaAcctCd);
        }
        // END 2018/08/03 J.Kim [QC#26901, ADD]

        S21ApiTBLAccessor.update(updDsAssetMstrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updDsAssetMstrTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", updDsAssetMstrTMsg.dsAssetMstrPk.getValue().toString());
            setDBAcsErrMsg(param, NASM0007E, new String[] {TBL_DS_ASSET_MSTR, pKey });

            return false;
        }

        // START 2018/07/20 J.Kim [QC#24950,ADD]
        if (param.prntDsAssetMstrPk.getValue().compareTo(updDsAssetMstrTMsg.dsAssetMstrPk.getValue()) != 0) {
            return true;
        }
        // END 2018/07/20 J.Kim [QC#24950,ADD]

        setAssetTrackingData(param, updDsAssetMstrTMsg, "mdseCd", oldDsAssetMstrTMsg.mdseCd.getValue(), updDsAssetMstrTMsg.mdseCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "serNum", oldDsAssetMstrTMsg.serNum.getValue(), updDsAssetMstrTMsg.serNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "svcMachMstrPk", oldDsAssetMstrTMsg.svcMachMstrPk.getValue(), updDsAssetMstrTMsg.svcMachMstrPk.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "shpgPlnNum", oldDsAssetMstrTMsg.shpgPlnNum.getValue(), updDsAssetMstrTMsg.shpgPlnNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "depcMthNum", oldDsAssetMstrTMsg.depcMthNum.getValue(), updDsAssetMstrTMsg.depcMthNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "depcStartDt", oldDsAssetMstrTMsg.depcStartDt.getValue(), updDsAssetMstrTMsg.depcStartDt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "slsRepTocCd", oldDsAssetMstrTMsg.slsRepTocCd.getValue(), updDsAssetMstrTMsg.slsRepTocCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "rsdlValAmt", oldDsAssetMstrTMsg.rsdlValAmt.getValue(), updDsAssetMstrTMsg.rsdlValAmt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoOrdNum", oldDsAssetMstrTMsg.cpoOrdNum.getValue(), updDsAssetMstrTMsg.cpoOrdNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoDtlLineNum", oldDsAssetMstrTMsg.cpoDtlLineNum.getValue(), updDsAssetMstrTMsg.cpoDtlLineNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "cpoDtlLineSubNum", oldDsAssetMstrTMsg.cpoDtlLineSubNum.getValue(), updDsAssetMstrTMsg.cpoDtlLineSubNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "dsOrdPosnNum", oldDsAssetMstrTMsg.dsOrdPosnNum.getValue(), updDsAssetMstrTMsg.dsOrdPosnNum.getValue(), trkItemMap);
        // START 2018/01/18 J.Kim [QC#17985,MOD]
        // setAssetTrackingData(param, updDsAssetMstrTMsg, "invNum", oldDsAssetMstrTMsg.invNum.getValue(), updDsAssetMstrTMsg.invNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "apVndInvNum", oldDsAssetMstrTMsg.apVndInvNum.getValue(), updDsAssetMstrTMsg.apVndInvNum.getValue(), trkItemMap);
        // END 2018/01/18 J.Kim [QC#17985,MOD]
        setAssetTrackingData(param, updDsAssetMstrTMsg, "vndNm", oldDsAssetMstrTMsg.vndCd.getValue(), updDsAssetMstrTMsg.vndCd.getValue(), trkItemMap);
        // START 2018/06/25 J.Kim [QC#24844, MOD]
        // setAssetTrackingData(param, updDsAssetMstrTMsg, "poOrdNum", oldDsAssetMstrTMsg.poOrdNum.getValue(), updDsAssetMstrTMsg.poOrdNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "custIssPoNum", oldDsAssetMstrTMsg.custIssPoNum.getValue(), updDsAssetMstrTMsg.custIssPoNum.getValue(), trkItemMap);
        // END 2018/06/25 J.Kim [QC#24844, MOD]
        setAssetTrackingData(param, updDsAssetMstrTMsg, "dsAssetDescTxt", oldDsAssetMstrTMsg.dsAssetDescTxt.getValue(), updDsAssetMstrTMsg.dsAssetDescTxt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetTagNum", oldDsAssetMstrTMsg.assetTagNum.getValue(), updDsAssetMstrTMsg.assetTagNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "asgDtlCmntTxt", oldDsAssetMstrTMsg.asgDtlCmntTxt.getValue(), updDsAssetMstrTMsg.asgDtlCmntTxt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "finDtlCmntTxt", oldDsAssetMstrTMsg.finDtlCmntTxt.getValue(), updDsAssetMstrTMsg.finDtlCmntTxt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "firstLineAddr", oldDsAssetMstrTMsg.firstLineAddr.getValue(), updDsAssetMstrTMsg.firstLineAddr.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "ctyAddr", oldDsAssetMstrTMsg.ctyAddr.getValue(), updDsAssetMstrTMsg.ctyAddr.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "stCd", oldDsAssetMstrTMsg.stCd.getValue(), updDsAssetMstrTMsg.stCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "postCd", oldDsAssetMstrTMsg.postCd.getValue(), updDsAssetMstrTMsg.postCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "expCoaCmpyCd", oldDsAssetMstrTMsg.expCoaCmpyCd.getValue(), updDsAssetMstrTMsg.expCoaCmpyCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "expCoaBrCd", oldDsAssetMstrTMsg.expCoaBrCd.getValue(), updDsAssetMstrTMsg.expCoaBrCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "expCoaCcCd", oldDsAssetMstrTMsg.expCoaCcCd.getValue(), updDsAssetMstrTMsg.expCoaCcCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "expCoaAcctCd", oldDsAssetMstrTMsg.expCoaAcctCd.getValue(), updDsAssetMstrTMsg.expCoaAcctCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "expCoaProdCd", oldDsAssetMstrTMsg.expCoaProdCd.getValue(), updDsAssetMstrTMsg.expCoaProdCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "expCoaChCd", oldDsAssetMstrTMsg.expCoaChCd.getValue(), updDsAssetMstrTMsg.expCoaChCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "expCoaAfflCd", oldDsAssetMstrTMsg.expCoaAfflCd.getValue(), updDsAssetMstrTMsg.expCoaAfflCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "expCoaProjCd", oldDsAssetMstrTMsg.expCoaProjCd.getValue(), updDsAssetMstrTMsg.expCoaProjCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "expCoaExtnCd", oldDsAssetMstrTMsg.expCoaExtnCd.getValue(), updDsAssetMstrTMsg.expCoaExtnCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetCoaCmpyCd", oldDsAssetMstrTMsg.assetCoaCmpyCd.getValue(), updDsAssetMstrTMsg.assetCoaCmpyCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetCoaBrCd", oldDsAssetMstrTMsg.assetCoaBrCd.getValue(), updDsAssetMstrTMsg.assetCoaBrCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetCoaCcCd", oldDsAssetMstrTMsg.assetCoaCcCd.getValue(), updDsAssetMstrTMsg.assetCoaCcCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetCoaAcctCd", oldDsAssetMstrTMsg.assetCoaAcctCd.getValue(), updDsAssetMstrTMsg.assetCoaAcctCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetCoaProdCd", oldDsAssetMstrTMsg.assetCoaProdCd.getValue(), updDsAssetMstrTMsg.assetCoaProdCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetCoaChCd", oldDsAssetMstrTMsg.assetCoaChCd.getValue(), updDsAssetMstrTMsg.assetCoaChCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetCoaAfflCd", oldDsAssetMstrTMsg.assetCoaAfflCd.getValue(), updDsAssetMstrTMsg.assetCoaAfflCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetCoaProjCd", oldDsAssetMstrTMsg.assetCoaProjCd.getValue(), updDsAssetMstrTMsg.assetCoaProjCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetCoaExtnCd", oldDsAssetMstrTMsg.assetCoaExtnCd.getValue(), updDsAssetMstrTMsg.assetCoaExtnCd.getValue(), trkItemMap);
        // START 2018/06/25 J.Kim [QC#24844, ADD]
        // setAssetTrackingData(param, updDsAssetMstrTMsg, "assetLeaseNum", oldDsAssetMstrTMsg.assetLeaseNum.getValue(), updDsAssetMstrTMsg.assetLeaseNum.getValue(), trkItemMap);
        // setAssetTrackingData(param, updDsAssetMstrTMsg, "leaseStartDt", oldDsAssetMstrTMsg.leaseStartDt.getValue(), updDsAssetMstrTMsg.leaseStartDt.getValue(), trkItemMap);
        // setAssetTrackingData(param, updDsAssetMstrTMsg, "leaseEndDt", oldDsAssetMstrTMsg.leaseEndDt.getValue(), updDsAssetMstrTMsg.leaseEndDt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "dsContrNum", oldDsAssetMstrTMsg.dsContrNum.getValue(), updDsAssetMstrTMsg.dsContrNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "contrEffFromDt", oldDsAssetMstrTMsg.contrEffFromDt.getValue(), updDsAssetMstrTMsg.contrEffFromDt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "contrEffThruDt", oldDsAssetMstrTMsg.contrEffThruDt.getValue(), updDsAssetMstrTMsg.contrEffThruDt.getValue(), trkItemMap);
        // END 2018/06/25 J.Kim [QC#24844, ADD]
        setAssetTrackingData(param, updDsAssetMstrTMsg, "totAssetQty", oldDsAssetMstrTMsg.totAssetQty.getValue(), updDsAssetMstrTMsg.totAssetQty.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "coaMdseTpCd", oldDsAssetMstrTMsg.coaMdseTpCd.getValue(), updDsAssetMstrTMsg.coaMdseTpCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "shipToCustAcctCd", oldDsAssetMstrTMsg.shipToCustAcctCd.getValue(), updDsAssetMstrTMsg.shipToCustAcctCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "poOrdNum", oldDsAssetMstrTMsg.poOrdNum.getValue(), updDsAssetMstrTMsg.poOrdNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "curValAmt", oldDsAssetMstrTMsg.curValAmt.getValue(), updDsAssetMstrTMsg.curValAmt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "initBookAmt", oldDsAssetMstrTMsg.initBookAmt.getValue(), updDsAssetMstrTMsg.initBookAmt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "dtlCmntTxt", oldDsAssetMstrTMsg.dtlCmntTxt.getValue(), updDsAssetMstrTMsg.dtlCmntTxt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "amtChngRsnTpCd", oldDsAssetMstrTMsg.amtChngRsnTpCd.getValue(), updDsAssetMstrTMsg.amtChngRsnTpCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "adjCoaAcctCd", oldDsAssetMstrTMsg.adjCoaAcctCd.getValue(), updDsAssetMstrTMsg.adjCoaAcctCd.getValue(), trkItemMap);

        return true;
    }

    /**
     * Update DS Asset Master. Merge
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param parentDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     * @param trkItemMap Map<String, String>
     * @return boolean
     */
    private boolean updDsAssetMstrMerge(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, //
            DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, //
            DS_ASSET_MSTRTMsg parentDsAssetMstrTMsg, //
            NLZC305001_updDtlListPMsg param, //
            Map<String, String> trkItemMap) {

        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.actvAssetFlg, parentDsAssetMstrTMsg.actvAssetFlg);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.depcMthNum, parentDsAssetMstrTMsg.depcMthNum);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.depcStartDt, parentDsAssetMstrTMsg.depcStartDt);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.firstDepcYrMth, parentDsAssetMstrTMsg.firstDepcYrMth);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetStsCd, ASSET_STS.MERGED);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.prntDsAssetMstrPk, param.prntDsAssetMstrPk);
        ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.assetPostFlg, parentDsAssetMstrTMsg.assetPostFlg);
        if (ZYPCommonFunc.hasValue(param.svcConfigMstrPk)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.svcConfigMstrPk, param.svcConfigMstrPk);
        }

        if (ZYPCommonFunc.hasValue(param.finDtlCmntTxt)) {
            ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.finDtlCmntTxt, param.finDtlCmntTxt);
        }

        S21ApiTBLAccessor.update(updDsAssetMstrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updDsAssetMstrTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", updDsAssetMstrTMsg.dsAssetMstrPk.getValue().toString());
            setDBAcsErrMsg(param, NASM0007E, new String[] {TBL_DS_ASSET_MSTR, pKey });

            return false;
        }

        setAssetTrackingData(param, updDsAssetMstrTMsg, "depcMthNum", oldDsAssetMstrTMsg.depcMthNum.getValue(), updDsAssetMstrTMsg.depcMthNum.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "depcStartDt", oldDsAssetMstrTMsg.depcStartDt.getValue(), updDsAssetMstrTMsg.depcStartDt.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "firstDepcYrMth", oldDsAssetMstrTMsg.firstDepcYrMth.getValue(), updDsAssetMstrTMsg.firstDepcYrMth.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "assetStsCd", oldDsAssetMstrTMsg.assetStsCd.getValue(), updDsAssetMstrTMsg.assetStsCd.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "prntDsAssetMstrPk", oldDsAssetMstrTMsg.prntDsAssetMstrPk.getValue(), updDsAssetMstrTMsg.prntDsAssetMstrPk.getValue(), trkItemMap);
        setAssetTrackingData(param, updDsAssetMstrTMsg, "finDtlCmntTxt", oldDsAssetMstrTMsg.finDtlCmntTxt.getValue(), updDsAssetMstrTMsg.finDtlCmntTxt.getValue(), trkItemMap);

        return true;
    }

    /**
     * Create DS Asset Transaction. 1-4. Revenue Recognition Sales
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     */
    private void insertDsAssetTrxSalesRev(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, NLZC305001_updDtlListPMsg param) {

        BigDecimal dsAssetTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_TRX_SQ);

        DS_ASSET_TRXTMsg insrtDsAssetTrxTMsg = new DS_ASSET_TRXTMsg();
        // Set parameters Asset Transaction Data
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetTrxPk, dsAssetTrxPk);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTrxDt, slsDt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetMstrPk, updDsAssetMstrTMsg.dsAssetMstrPk);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.mdseCd, updDsAssetMstrTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.serNum, updDsAssetMstrTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTpCd, updDsAssetMstrTMsg.assetTpCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxCd, TRX.SALES);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxRsnCd, TRX_RSN.REGULAR_SALES_ASSET_FOR_AJE_LINK);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.slsRepTocCd, updDsAssetMstrTMsg.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.billToCustCd, param.billToCustCd);
        // START 2018/01/18 J.Kim [QC#17985,MOD]
        // ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.invNum, param.invNum);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.apVndInvNum, param.invNum);
        // END 2018/01/18 J.Kim [QC#17985,MOD]
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaProdCd, param.coaProdCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaAcctCd, param.coaAcctCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaProjCd, param.coaProjCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaBrCd, param.coaBrCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaChCd, param.coaChCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaCcCd, param.coaCcCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfReqFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.sysSrcCd, SYS_SRC.S21_ASSET);
        // Set parameters Asset Transaction Depreciation
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.initBookAmt, updDsAssetMstrTMsg.initBookAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.curValAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.prevValAmt, oldDsAssetMstrTMsg.curValAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcValAmt, oldDsAssetMstrTMsg.curValAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcCnt, updDsAssetMstrTMsg.depcCnt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcMthNum, updDsAssetMstrTMsg.depcMthNum);

        S21ApiTBLAccessor.insert(insrtDsAssetTrxTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insrtDsAssetTrxTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_TRX_PK, " : ", insrtDsAssetTrxTMsg.dsAssetTrxPk.getValue().toString());
            setDBAcsErrMsg(param, NWAM0317W, new String[] {TBL_DS_ASSET_TRX, pKey });
        }
    }

    /**
     * Create DS Asset Transaction. 1-5. Revenue Recognition Expense
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     */
    private void insertDsAssetTrxExpRev(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, NLZC305001_updDtlListPMsg param) {

        BigDecimal dsAssetTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_TRX_SQ);

        DS_ASSET_TRXTMsg insrtDsAssetTrxTMsg = new DS_ASSET_TRXTMsg();
        // Set parameters Asset Transaction Data
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetTrxPk, dsAssetTrxPk);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTrxDt, slsDt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetMstrPk, updDsAssetMstrTMsg.dsAssetMstrPk);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.mdseCd, updDsAssetMstrTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.serNum, updDsAssetMstrTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTpCd, updDsAssetMstrTMsg.assetTpCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxCd, TRX.EXPENSE_SHIPMENT);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxRsnCd, TRX_RSN.EXPENSE_SHIPMENT_ASSET_FOR_AJE_LINK);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.slsRepTocCd, updDsAssetMstrTMsg.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.billToCustCd, param.billToCustCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaProdCd, param.coaProdCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaAcctCd, param.coaAcctCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaProjCd, param.coaProjCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaBrCd, param.coaBrCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaChCd, param.coaChCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaCcCd, param.coaCcCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfReqFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.sysSrcCd, SYS_SRC.S21_ASSET);
        // Set parameters Asset Transaction Depreciation
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.initBookAmt, updDsAssetMstrTMsg.initBookAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.curValAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.prevValAmt, oldDsAssetMstrTMsg.curValAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcValAmt, oldDsAssetMstrTMsg.curValAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcCnt, updDsAssetMstrTMsg.depcCnt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcMthNum, updDsAssetMstrTMsg.depcMthNum);

        S21ApiTBLAccessor.insert(insrtDsAssetTrxTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insrtDsAssetTrxTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_TRX_PK, " : ", insrtDsAssetTrxTMsg.dsAssetTrxPk.getValue().toString());
            setDBAcsErrMsg(param, NWAM0317W, new String[] {TBL_DS_ASSET_TRX, pKey });
        }
    }

    /**
     * Create DS Asset Transaction. Disposal
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     */
    private void insertDsAssetTrxDspl(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, NLZC305001_updDtlListPMsg param) {

        BigDecimal dsAssetTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_TRX_SQ);

        DS_ASSET_TRXTMsg insrtDsAssetTrxTMsg = new DS_ASSET_TRXTMsg();
        // Set parameters Asset Transaction Data
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetTrxPk, dsAssetTrxPk);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTrxDt, slsDt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetMstrPk, updDsAssetMstrTMsg.dsAssetMstrPk);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.mdseCd, updDsAssetMstrTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.serNum, updDsAssetMstrTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTpCd, updDsAssetMstrTMsg.assetTpCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxCd, TRX.ADJUSTMENT);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxRsnCd, TRX_RSN.ASSET_DISPOSAL_FOR_AJE_LINK);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.slsRepTocCd, updDsAssetMstrTMsg.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfReqFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.sysSrcCd, SYS_SRC.S21_ASSET);
        // Set parameters Asset Transaction Depreciation
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.initBookAmt, updDsAssetMstrTMsg.initBookAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.curValAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.prevValAmt, oldDsAssetMstrTMsg.curValAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcValAmt, oldDsAssetMstrTMsg.curValAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcCnt, updDsAssetMstrTMsg.depcCnt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcMthNum, updDsAssetMstrTMsg.depcMthNum);
        // START 2016/09/26 J.Kim [QC#17088,ADD]
        if (ZYPCommonFunc.hasValue(param.billToCustCd)) {
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.billToCustCd, param.billToCustCd);
        }
        // END 2016/09/26 J.Kim [QC#17088,ADD]

        S21ApiTBLAccessor.insert(insrtDsAssetTrxTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insrtDsAssetTrxTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_TRX_PK, " : ", insrtDsAssetTrxTMsg.dsAssetTrxPk.getValue().toString());
            setDBAcsErrMsg(param, NWAM0317W, new String[] {TBL_DS_ASSET_TRX, pKey });
        }
    }

    /**
     * Create DS Asset Transaction. Update change current value amount
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     */
    private void insertDsAssetTrxUpdCur(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, NLZC305001_updDtlListPMsg param) {

        BigDecimal dsAssetTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_TRX_SQ);

        DS_ASSET_TRXTMsg insrtDsAssetTrxTMsg = new DS_ASSET_TRXTMsg();
        // Set parameters Asset Transaction Data
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetTrxPk, dsAssetTrxPk);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTrxDt, slsDt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetMstrPk, updDsAssetMstrTMsg.dsAssetMstrPk);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.mdseCd, updDsAssetMstrTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.serNum, updDsAssetMstrTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTpCd, updDsAssetMstrTMsg.assetTpCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxCd, TRX.DEPRECIATION);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxRsnCd, TRX_RSN.USEFUL_LIFE_CHANGE);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.slsRepTocCd, updDsAssetMstrTMsg.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfReqFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.sysSrcCd, SYS_SRC.S21_ASSET);
        // Set parameters Asset Transaction Depreciation
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.initBookAmt, updDsAssetMstrTMsg.initBookAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.curValAmt, updDsAssetMstrTMsg.curValAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.prevValAmt, oldDsAssetMstrTMsg.curValAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcValAmt, oldDsAssetMstrTMsg.curValAmt.getValue().subtract(updDsAssetMstrTMsg.curValAmt.getValue()));
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcCnt, updDsAssetMstrTMsg.depcCnt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcMthNum, updDsAssetMstrTMsg.depcMthNum);

        S21ApiTBLAccessor.insert(insrtDsAssetTrxTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insrtDsAssetTrxTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_TRX_PK, " : ", insrtDsAssetTrxTMsg.dsAssetTrxPk.getValue().toString());
            setDBAcsErrMsg(param, NWAM0317W, new String[] {TBL_DS_ASSET_TRX, pKey });
        }
    }

    /**
     * Create DS Asset Transaction. update Change Initial book amount
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     */
    private void insertDsAssetTrxUpdInitBookAmt(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, NLZC305001_updDtlListPMsg param) {

        BigDecimal dsAssetTrxPk = null;
        DS_ASSET_TRXTMsg insrtDsAssetTrxTMsg = null;

        // Depreciation clear. DS_ASSET_MSTR.DEPC_CNT > 0
        if (oldDsAssetMstrTMsg.curValAmt.getValue().compareTo(oldDsAssetMstrTMsg.initBookAmt.getValue()) != 0) {

            dsAssetTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_TRX_SQ);

            insrtDsAssetTrxTMsg = new DS_ASSET_TRXTMsg();
            // Set parameters Asset Transaction Data
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetTrxPk, dsAssetTrxPk);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTrxDt, slsDt);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetMstrPk, updDsAssetMstrTMsg.dsAssetMstrPk);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.mdseCd, updDsAssetMstrTMsg.mdseCd);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.serNum, updDsAssetMstrTMsg.serNum);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTpCd, updDsAssetMstrTMsg.assetTpCd);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxCd, TRX.DEPRECIATION);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxRsnCd, TRX_RSN.DEPRECIATION);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.slsRepTocCd, updDsAssetMstrTMsg.slsRepTocCd);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfCpltFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfReqFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.sysSrcCd, SYS_SRC.S21_ASSET);
            // Set parameters Asset Transaction Depreciation
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.initBookAmt, oldDsAssetMstrTMsg.initBookAmt);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.curValAmt, oldDsAssetMstrTMsg.initBookAmt);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.prevValAmt, oldDsAssetMstrTMsg.curValAmt);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcValAmt, oldDsAssetMstrTMsg.curValAmt.getValue().subtract(oldDsAssetMstrTMsg.initBookAmt.getValue()));
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcCnt, updDsAssetMstrTMsg.depcCnt);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcMthNum, oldDsAssetMstrTMsg.depcMthNum);

            S21ApiTBLAccessor.insert(insrtDsAssetTrxTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insrtDsAssetTrxTMsg.getReturnCode())) {

                String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_TRX_PK, " : ", insrtDsAssetTrxTMsg.dsAssetTrxPk.getValue().toString());
                setDBAcsErrMsg(param, NWAM0317W, new String[] {TBL_DS_ASSET_TRX, pKey });
            }
        }

        // Initial book ammount change
        dsAssetTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_TRX_SQ);

        insrtDsAssetTrxTMsg = new DS_ASSET_TRXTMsg();
        // Set parameters Asset Transaction Data
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetTrxPk, dsAssetTrxPk);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTrxDt, slsDt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetMstrPk, updDsAssetMstrTMsg.dsAssetMstrPk);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.mdseCd, updDsAssetMstrTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.serNum, updDsAssetMstrTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTpCd, updDsAssetMstrTMsg.assetTpCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxCd, TRX.ADJUSTMENT);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxRsnCd, TRX_RSN.ASSET_INITIAL_COST_CHANGE);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaProdCd, param.coaProdCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaAcctCd, param.coaAcctCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaProjCd, param.coaProjCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaBrCd, param.coaBrCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaChCd, param.coaChCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaCcCd, param.coaCcCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfReqFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.sysSrcCd, SYS_SRC.S21_ASSET);
        // Set parameters Asset Transaction Depreciation
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.initBookAmt, updDsAssetMstrTMsg.initBookAmt.getValue().subtract(oldDsAssetMstrTMsg.initBookAmt.getValue()));
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.curValAmt, updDsAssetMstrTMsg.initBookAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.prevValAmt, oldDsAssetMstrTMsg.initBookAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcValAmt, oldDsAssetMstrTMsg.initBookAmt.getValue().subtract(updDsAssetMstrTMsg.initBookAmt.getValue()));
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcCnt, updDsAssetMstrTMsg.depcCnt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcMthNum, updDsAssetMstrTMsg.depcMthNum);
        // START 2018/08/21 J.Kim [QC#27747,ADD]
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.slsRepTocCd, updDsAssetMstrTMsg.slsRepTocCd);
        // END 2018/08/21 J.Kim [QC#27747,ADD]

        S21ApiTBLAccessor.insert(insrtDsAssetTrxTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insrtDsAssetTrxTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_TRX_PK, " : ", insrtDsAssetTrxTMsg.dsAssetTrxPk.getValue().toString());
            setDBAcsErrMsg(param, NWAM0317W, new String[] {TBL_DS_ASSET_TRX, pKey });
        }

        // Depreciation update. DS_ASSET_MSTR.DEPC_CNT > 0
        if (BigDecimal.ZERO.compareTo(updDsAssetMstrTMsg.depcCnt.getValue()) < 0) {

            dsAssetTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_TRX_SQ);

            insrtDsAssetTrxTMsg = new DS_ASSET_TRXTMsg();
            // Set parameters Asset Transaction Data
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetTrxPk, dsAssetTrxPk);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTrxDt, slsDt);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetMstrPk, updDsAssetMstrTMsg.dsAssetMstrPk);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.mdseCd, updDsAssetMstrTMsg.mdseCd);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.serNum, updDsAssetMstrTMsg.serNum);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTpCd, updDsAssetMstrTMsg.assetTpCd);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxCd, TRX.DEPRECIATION);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxRsnCd, TRX_RSN.DEPRECIATION);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.slsRepTocCd, updDsAssetMstrTMsg.slsRepTocCd);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfCpltFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfReqFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.sysSrcCd, SYS_SRC.S21_ASSET);
            // Set parameters Asset Transaction Depreciation
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.initBookAmt, updDsAssetMstrTMsg.initBookAmt);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.curValAmt, updDsAssetMstrTMsg.curValAmt);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.prevValAmt, updDsAssetMstrTMsg.initBookAmt);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcValAmt, updDsAssetMstrTMsg.initBookAmt.getValue().subtract(updDsAssetMstrTMsg.curValAmt.getValue()));
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcCnt, updDsAssetMstrTMsg.depcCnt);
            ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcMthNum, oldDsAssetMstrTMsg.depcMthNum);

            S21ApiTBLAccessor.insert(insrtDsAssetTrxTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insrtDsAssetTrxTMsg.getReturnCode())) {

                String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_TRX_PK, " : ", insrtDsAssetTrxTMsg.dsAssetTrxPk.getValue().toString());
                setDBAcsErrMsg(param, NWAM0317W, new String[] {TBL_DS_ASSET_TRX, pKey });
            }
        }
    }

    /**
     * Create DS Asset Transaction. Depreciation
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     */
    private void insertDsAssetTrxDepc(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, NLZC305001_updDtlListPMsg param) {

        BigDecimal dsAssetTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_TRX_SQ);

        DS_ASSET_TRXTMsg insrtDsAssetTrxTMsg = new DS_ASSET_TRXTMsg();
        // Set parameters Asset Transaction Data
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetTrxPk, dsAssetTrxPk);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTrxDt, slsDt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetMstrPk, updDsAssetMstrTMsg.dsAssetMstrPk);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.mdseCd, updDsAssetMstrTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.serNum, updDsAssetMstrTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTpCd, updDsAssetMstrTMsg.assetTpCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxCd, TRX.DEPRECIATION);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxRsnCd, TRX_RSN.DEPRECIATION);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.slsRepTocCd, updDsAssetMstrTMsg.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfReqFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.sysSrcCd, SYS_SRC.S21_ASSET);
        // Set parameters Asset Transaction Depreciation
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.initBookAmt, updDsAssetMstrTMsg.initBookAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.curValAmt, updDsAssetMstrTMsg.curValAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.prevValAmt, oldDsAssetMstrTMsg.curValAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcValAmt, oldDsAssetMstrTMsg.curValAmt.getValue().subtract(param.curValAmt.getValue()));
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcCnt, updDsAssetMstrTMsg.depcCnt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcMthNum, updDsAssetMstrTMsg.depcMthNum);

        S21ApiTBLAccessor.insert(insrtDsAssetTrxTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insrtDsAssetTrxTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_TRX_PK, " : ", insrtDsAssetTrxTMsg.dsAssetTrxPk.getValue().toString());
            setDBAcsErrMsg(param, NWAM0317W, new String[] {TBL_DS_ASSET_TRX, pKey });
        }
    }

    /**
     * Create DS Asset Transaction. Asset Activate
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     * @param dsCondConstTMsg DS_COND_CONSTTMsg
     */
    private void insertDsAssetTrxAssetActivate(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, NLZC305001_updDtlListPMsg param, DS_COND_CONSTTMsg dsCondConstTMsg) {

        BigDecimal dsAssetTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_TRX_SQ);

        DS_ASSET_TRXTMsg insrtDsAssetTrxTMsg = new DS_ASSET_TRXTMsg();
        // Set parameters Asset Transaction Data
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetTrxPk, dsAssetTrxPk);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTrxDt, slsDt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetMstrPk, updDsAssetMstrTMsg.dsAssetMstrPk);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.mdseCd, updDsAssetMstrTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.serNum, updDsAssetMstrTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTpCd, updDsAssetMstrTMsg.assetTpCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxCd, dsCondConstTMsg.dsCondConstValTxt_01);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxRsnCd, dsCondConstTMsg.dsCondConstValTxt_02);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.slsRepTocCd, updDsAssetMstrTMsg.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.billToCustCd, param.billToCustCd);
        // START 2018/01/18 J.Kim [QC#17985,MOD]
        // ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.invNum, param.invNum);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.apVndInvNum, param.invNum);
        // END 2018/01/18 J.Kim [QC#17985,MOD]
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaProdCd, param.coaProdCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaAcctCd, param.coaAcctCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaProjCd, param.coaProjCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaBrCd, param.coaBrCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaChCd, param.coaChCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.coaCcCd, param.coaCcCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfReqFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.sysSrcCd, SYS_SRC.S21_ASSET);
        // Set parameters Asset Transaction Depreciation
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.initBookAmt, updDsAssetMstrTMsg.initBookAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.curValAmt, updDsAssetMstrTMsg.curValAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.prevValAmt, updDsAssetMstrTMsg.origStdCostAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcValAmt, insrtDsAssetTrxTMsg.prevValAmt.getValue().subtract(insrtDsAssetTrxTMsg.curValAmt.getValue()));
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcCnt, updDsAssetMstrTMsg.depcCnt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcMthNum, updDsAssetMstrTMsg.depcMthNum);

        S21ApiTBLAccessor.insert(insrtDsAssetTrxTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insrtDsAssetTrxTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_TRX_PK, " : ", insrtDsAssetTrxTMsg.dsAssetTrxPk.getValue().toString());
            setDBAcsErrMsg(param, NWAM0317W, new String[] {TBL_DS_ASSET_TRX, pKey });
        }
    }

    /**
     * Set API Parameter Check error message
     * @param param NLZC305001_updDtlListPMsg
     * @param msgId String
     */
    private void setParamErrMsg(NLZC305001_updDtlListPMsg param, String msgId) {

        msgMap.addXxMsgId(msgId);
        ZYPEZDItemValueSetter.setValue(param.xxMsgId, msgId);
        ZYPEZDItemValueSetter.setValue(param.xxDsMsgEntryTxt, S21MessageFunc.clspGetMessage(msgId).substring(MSG_SUBSTRING_LENGTH));

        if (ONBATCH_TYPE.BATCH.equals(paramOnBatchType)) {

            S21InfoLogOutput.println(msgId);
            setS21InfoLogParamVal(param);
        }
    }

    /**
     * Set DB Access Error Message
     * @param param NLZC305001_updDtlListPMsg
     * @param infoLogMsgId String
     * @param msgParamList String[]
     */
    private void setDBAcsErrMsg(NLZC305001_updDtlListPMsg param, String infoLogMsgId, String[] msgParamList) {

        msgMap.addXxMsgId(NWZM0217E);
        ZYPEZDItemValueSetter.setValue(param.xxMsgId, NWZM0217E);
        ZYPEZDItemValueSetter.setValue(param.xxDsMsgEntryTxt, S21MessageFunc.clspGetMessage(NWZM0217E).substring(MSG_SUBSTRING_LENGTH));
        S21InfoLogOutput.println(infoLogMsgId, msgParamList);
        setS21InfoLogParamVal(param);
    }

    /**
     * Set Parameter Value for S21InfoLog
     * @param param NLZC305001_updDtlListPMsg
     */
    private void setS21InfoLogParamVal(NLZC305001_updDtlListPMsg param) {

        S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_GLBL_CMPY_CD, glblCmpyCd });
        S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_SLS_DT, slsDt });
        S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_PROC_MD, param.xxProcMd.getValue() });

        if (ZYPCommonFunc.hasValue(param.dsAssetMstrPk)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_DS_ASSET_MSTR_PK, param.dsAssetMstrPk.getValue().toString() });
        }

        if (ZYPCommonFunc.hasValue(param.mdseCd)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_MDSE_CD, param.mdseCd.getValue() });
        }

        if (ZYPCommonFunc.hasValue(param.serNum)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_SER_NUM, param.serNum.getValue() });
        }

        if (ZYPCommonFunc.hasValue(param.assetTpCd)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_ASSET_TP_CD, param.assetTpCd.getValue() });
        }

        if (ZYPCommonFunc.hasValue(param.svcMachMstrPk)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_SVC_MACH_MSTR_PK, param.svcMachMstrPk.getValue().toString() });
        }

        if (ZYPCommonFunc.hasValue(param.shpgPlnNum)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_SHPG_PLN_NUM, param.shpgPlnNum.getValue() });
        }

        if (ZYPCommonFunc.hasValue(param.invtyTrxPk)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_INVTY_TRX_PK, param.invtyTrxPk.getValue().toString() });
        }

        if (ZYPCommonFunc.hasValue(param.slsRepTocCd)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_SLS_REP_TOC_CD, param.slsRepTocCd.getValue() });
        }

        if (ZYPCommonFunc.hasValue(param.billToCustCd)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_BILL_TO_CUST_CD, param.billToCustCd.getValue() });
        }

        if (ZYPCommonFunc.hasValue(param.invNum)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_INV_NUM, param.invNum.getValue() });
        }

        if (ZYPCommonFunc.hasValue(param.coaProdCd)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_COA_PROD_CD, param.coaProdCd.getValue() });
        }

        if (ZYPCommonFunc.hasValue(param.coaAcctCd)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_COA_ACCT_CD, param.coaAcctCd.getValue() });
        }

        if (ZYPCommonFunc.hasValue(param.coaProjCd)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_COA_PROJ_CD, param.coaProjCd.getValue() });
        }

        if (ZYPCommonFunc.hasValue(param.coaBrCd)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_COA_BR_CD, param.coaBrCd.getValue() });
        }

        if (ZYPCommonFunc.hasValue(param.coaChCd)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_COA_CH_CD, param.coaChCd.getValue() });
        }

        if (ZYPCommonFunc.hasValue(param.coaCcCd)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_COA_CC_CD, param.coaCcCd.getValue() });
        }

        if (ZYPCommonFunc.hasValue(param.depcMthNum)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_DEPC_MTH_NUM, param.depcMthNum.getValue() });
        }

        if (ZYPCommonFunc.hasValue(param.curValAmt)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_CUR_VAL_AMT, param.curValAmt.getValue().toString() });
        }

        if (ZYPCommonFunc.hasValue(param.rsdlValAmt)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_RSDL_VAL_AMT, param.rsdlValAmt.getValue().toString() });
        }

        if (ZYPCommonFunc.hasValue(param.depcCnt)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_DEPC_CNT, param.depcCnt.getValue().toString() });
        }

        if (ZYPCommonFunc.hasValue(param.lastDepcYrMth)) {

            S21InfoLogOutput.println(NAZM0280E, new String[] {PARAM_LAST_DEPC_YR_MTH, param.lastDepcYrMth.getValue() });
        }
    }

    /**
     * getAssetHistColMapFindByCondition
     * @param glblCmpyCd String
     * @return Map<String, Object>
     */
    private static Map<String, String> getAssetHistColMapFindByCondition(String glblCmpyCd) {

        DS_ASSET_HIST_COL_MAPTMsg dsAssetHistColMapTMsg = new DS_ASSET_HIST_COL_MAPTMsg();
        dsAssetHistColMapTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsAssetHistColMapTMsg.setConditionValue("enblFlg01", ZYPConstant.FLG_ON_Y);
        dsAssetHistColMapTMsg.setSQLID("001");

        DS_ASSET_HIST_COL_MAPTMsgArray outTMsgArray = (DS_ASSET_HIST_COL_MAPTMsgArray) EZDTBLAccessor.findByCondition(dsAssetHistColMapTMsg);

        if (outTMsgArray.length() == 0) {

            return null;

        }

        Map<String, String> itemMap = new HashMap<String, String>();

        for (int index = 0; index < outTMsgArray.getValidCount(); index++) {

            DS_ASSET_HIST_COL_MAPTMsg tMsg = (DS_ASSET_HIST_COL_MAPTMsg) outTMsgArray.get(index);
            itemMap.put(tMsg.dsAssetHistAttrbNm.getValue(), tMsg.dsAssetHistAttrbLongNm.getValue());

        }

        return itemMap;
    }

    /**
     * setAssetTrackingAmount
     * @param param NLZC305001_updDtlListPMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param assetMstrInfoMap Map<String, Object>
     * @param prntDsAssetMstrPk BigDecimal
     * @param trkItemMap Map<String, String>
     */
    private void setAssetTrackingAmount(NLZC305001_updDtlListPMsg param, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, Map<String, Object> assetMstrInfoMap, BigDecimal prntDsAssetMstrPk, Map<String, String> trkItemMap) {

        if (ZYPCommonFunc.hasValue(param.procModeCd) && PROC_MODE.MERGE.equals(param.procModeCd.getValue())) {
            return;
        }

        BigDecimal oldCurValAmt = BigDecimal.ZERO;
        BigDecimal oldInitAmtValue = BigDecimal.ZERO;
        if (assetMstrInfoMap != null) {
            oldCurValAmt = (BigDecimal) assetMstrInfoMap.get(MAP_KEY_CUR_VAL_AMT);
            oldInitAmtValue = (BigDecimal) assetMstrInfoMap.get(MAP_KEY_INIT_BOOK_AMT);
        }

        Map<String, Object> newAssetMstrInfoMap = doProcessAmountSummary(prntDsAssetMstrPk);
        BigDecimal newInitAmtValue = (BigDecimal) newAssetMstrInfoMap.get(MAP_KEY_INIT_BOOK_AMT);
        BigDecimal newCurValAmt = (BigDecimal) newAssetMstrInfoMap.get(MAP_KEY_CUR_VAL_AMT);
        if (updDsAssetMstrTMsg == null) {
            updDsAssetMstrTMsg = getDsAssetMstr(prntDsAssetMstrPk);
        }

        if (prntDsAssetMstrPk.compareTo(updDsAssetMstrTMsg.dsAssetMstrPk.getValue()) != 0) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(param.xxYesNoCd, ZYPConstant.FLG_OFF_N);
        if (oldInitAmtValue.compareTo(newInitAmtValue) != 0) {
            setAssetTrackingData(param, updDsAssetMstrTMsg, "ASSET_VALUE", oldInitAmtValue, newInitAmtValue, trkItemMap);
        }
        if (assetMstrInfoMap != null && oldCurValAmt.compareTo(newCurValAmt) != 0) {
            setAssetTrackingData(param, updDsAssetMstrTMsg, "NBV", oldCurValAmt, newCurValAmt, trkItemMap);
        }
        param.xxYesNoCd.clear();
    }

    /**
     * setAssetTrackingData
     * @param param NLZC305001_updDtlListPMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param attrName String
     * @param oldValue Object
     * @param newValue Object
     * @param trkItemMap Map<String, String>
     */
    private void setAssetTrackingData(NLZC305001_updDtlListPMsg param, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, String attrName, Object oldValue, Object newValue, Map<String, String> trkItemMap) {

        // START 2018/07/20 J.Kim [QC#24950,MOD]
        //if (!ZYPCommonFunc.hasValue(trkItemMap.get(updDsAssetMstrTMsg.getAttr(attrName).getDBColumnName()))) {
        //    // Not Tracking Data Column
        //    return;
        //}
        String attributeName = null;
        if (ZYPConstant.FLG_OFF_N.equals(param.xxYesNoCd.getValue())) {
            attributeName = attrName;
        } else {
            attributeName = updDsAssetMstrTMsg.getAttr(attrName).getDBColumnName();
            // Not Tracking Data Column
            if (!ZYPCommonFunc.hasValue(trkItemMap.get(attributeName))) {
                return;
            }
        }
        // END 2018/07/20 J.Kim [QC#24950,MOD]

        if (oldValue != null) {
            if (oldValue.equals(newValue)) {

                return;

            }
        } else if (oldValue == newValue) {
            return;
        }

        // START 2018/08/09 J.Kim [QC#26901,ADD]
        if ("amtChngRsnTpCd".equals(attrName) && ZYPCommonFunc.hasValue(param.amtChngRsnTpCd)) {
            String oldAmtChngRsnTpNm = getAmtChngRsnTpNm(oldValue.toString());
            if (ZYPCommonFunc.hasValue(oldAmtChngRsnTpNm)) {
                oldValue = oldAmtChngRsnTpNm;
            }
            String newAmtChngRsnTpNm = getAmtChngRsnTpNm(newValue.toString());
            if (ZYPCommonFunc.hasValue(newAmtChngRsnTpNm)) {
                newValue = newAmtChngRsnTpNm;
            }
        }
        // END 2018/08/09 J.Kim [QC#26901,ADD]

        // START 2018/08/13 J.Kim [QC#22267,ADD]
        if ("assetStsCd".equals(attrName)) {
            String oldAssetStsNm = getAssetStsNm(oldValue.toString());
            if (ZYPCommonFunc.hasValue(oldAssetStsNm)) {
                oldValue = oldAssetStsNm;
            }
            String newAssetStsNm = getAssetStsNm(newValue.toString());
            if (ZYPCommonFunc.hasValue(newAssetStsNm)) {
                newValue = newAssetStsNm;
            }
        }

        if ("sellToCustCd".equals(attrName) && ZYPCommonFunc.hasValue(param.sellToCustCd) || "shipToCustAcctCd".equals(attrName) && ZYPCommonFunc.hasValue(param.shipToCustAcctCd)) {
            String oldAcctNm = getAcctNm(oldValue.toString());
            if (ZYPCommonFunc.hasValue(oldAcctNm)) {
                oldValue = oldAcctNm;
            }
            String newAcctNm = getAcctNm(newValue.toString());
            if (ZYPCommonFunc.hasValue(newAcctNm)) {
                newValue = newAcctNm;
            }
        }

        if ("vndNm".equals(attrName)) {
            String oldVndNm = getVnd(oldValue.toString());
            if (ZYPCommonFunc.hasValue(oldVndNm)) {
                oldValue = oldVndNm;
            }
            String newVndNm = getVnd(newValue.toString());
            if (ZYPCommonFunc.hasValue(newVndNm)) {
                newValue = newVndNm;
            }
        }
        // END 2018/08/13 J.Kim [QC#22267,ADD]

        // Tracking Data
        DS_ASSET_MSTR_TRKTMsg dsAssetMstrTrkTMsg = new DS_ASSET_MSTR_TRKTMsg();

        BigDecimal dsAssetMstrTrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_MSTR_TRK_SQ);
        ZYPEZDItemValueSetter.setValue(dsAssetMstrTrkTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsAssetMstrTrkTMsg.dsAssetMstrTrkPk, dsAssetMstrTrkPk);
        ZYPEZDItemValueSetter.setValue(dsAssetMstrTrkTMsg.dsAssetMstrPk, updDsAssetMstrTMsg.dsAssetMstrPk);
        // START 2018/07/20 J.Kim [QC#24950,ADD]
        ZYPEZDItemValueSetter.setValue(dsAssetMstrTrkTMsg.prntDsAssetMstrPk, updDsAssetMstrTMsg.prntDsAssetMstrPk);
        if (ZYPCommonFunc.hasValue(param.procModeCd)) {
            PROC_MODETMsg procModeTMsg = getProcModeNm(param.procModeCd.getValue());
            if (procModeTMsg != null) {
                ZYPEZDItemValueSetter.setValue(dsAssetMstrTrkTMsg.procModeNm, procModeTMsg.procModeNm);
            }
        }
        // END 2018/07/20 J.Kim [QC#24950,ADD]
        ZYPEZDItemValueSetter.setValue(dsAssetMstrTrkTMsg.trxRgtnTs, ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS));
        // START 2018/07/20 J.Kim [QC#24950,MOD]
        //ZYPEZDItemValueSetter.setValue(dsAssetMstrTrkTMsg.updFldTxt, trkItemMap.get(updDsAssetMstrTMsg.getAttr(attrName).getDBColumnName()));
        ZYPEZDItemValueSetter.setValue(dsAssetMstrTrkTMsg.updFldTxt, trkItemMap.get(attributeName));
        // END 2018/07/20 J.Kim [QC#24950,MOD]

        if (oldValue instanceof String) {
            ZYPEZDItemValueSetter.setValue(dsAssetMstrTrkTMsg.oldValTxt, (String) oldValue);
        } else if (oldValue instanceof BigDecimal) {
            ZYPEZDItemValueSetter.setValue(dsAssetMstrTrkTMsg.oldValTxt, ((BigDecimal) oldValue).toPlainString());
        }
        if (newValue instanceof String) {
            ZYPEZDItemValueSetter.setValue(dsAssetMstrTrkTMsg.newValTxt, (String) newValue);
        } else if (newValue instanceof BigDecimal) {
            ZYPEZDItemValueSetter.setValue(dsAssetMstrTrkTMsg.newValTxt, ((BigDecimal) newValue).toPlainString());
        }

        if (ZYPCommonFunc.hasValue(updDsAssetMstrTMsg.ezUpUserID)) {
            ZYPEZDItemValueSetter.setValue(dsAssetMstrTrkTMsg.updUsrId, updDsAssetMstrTMsg.ezUpUserID);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAssetMstrTrkTMsg.updUsrId, param.updUsrId);
        }

        S21ApiTBLAccessor.insert(dsAssetMstrTrkTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsAssetMstrTrkTMsg.getReturnCode())) {

            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_TRK_PK, " : ", dsAssetMstrTrkTMsg.dsAssetMstrTrkPk.getValue().toString());
            setDBAcsErrMsg(param, NWAM0317W, new String[] {TBL_DS_ASSET_MSTR_TRK, pKey });
        }
    }

    /**
     * set DS_ASSET_MSTRTMsg
     * @param oldDsAssetMstrInfoMap Map<String, Object>
     * @return DS_ASSET_MSTRTMsg
     */
    private DS_ASSET_MSTRTMsg setDsAssetMstrTMsg(Map<String, Object> oldDsAssetMstrInfoMap) {

        DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();

        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.dsAssetMstrPk, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_DS_ASSET_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.glblCmpyCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.ezTableID, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EZTABLEID));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.ezCancelFlag, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EZCANCELFLAG));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.ezInTime, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EZINTIME));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.ezInTimeZone, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EZINTIMEZONE));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.ezInCompanyCode, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EZINCOMPANYCODE));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.ezInUserID, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EZINUSERID));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.ezInAplID, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EZINAPLID));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.ezUpTime, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EZUPTIME));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.ezUpTimeZone, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EZUPTIMEZONE));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.ezUpCompanyCode, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EZUPCOMPANYCODE));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.ezUpUserID, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EZUPUSERID));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.ezUpAplID, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EZUPAPLID));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.mdseCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.serNum, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_SER_NUM));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.actvAssetFlg, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ACTV_ASSET_FLG));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.assetTpCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ASSET_TP_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.svcMachMstrPk, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_SVC_MACH_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.shpgPlnNum, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_SHPG_PLN_NUM));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.initBookAmt, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_INIT_BOOK_AMT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.origInitBookAmt, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_ORIG_INIT_BOOK_AMTT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.depcMthNum, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_DEPC_MTH_NUM));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.depcStartDt, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_DEPC_START_DT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.depcCnt, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_DEPC_CNT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.curValAmt, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_CUR_VAL_AMT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.slsRepTocCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_SLS_REP_TOC_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.firstDepcYrMth, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_FIRST_DEPC_YR_MTH));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.lastDepcYrMth, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_LAST_DEPC_YR_MTH));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.rsdlValAmt, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_RSDL_VAL_AMT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.revRecogDt, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_REV_RECOG_DT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.depcStopDt, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_DEPC_STOP_DT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.depcReStartDt, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_DEPC_RE_START_DT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.invtyTrxPk, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_INVTY_TRX_PK));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.origInvtyTrxPk, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_ORIG_INVTY_TRX_PK));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.assetStsCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ASSET_STS_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.rtrnWhCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_RTRN_WH_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.cpoOrdNum, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.cpoDtlLineNum, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_CPO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.cpoDtlLineSubNum, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_CPO_DTL_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.dsOrdPosnNum, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_DS_ORD_POSN_NUM));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.shipToCustAcctCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_SHIP_TO_CUST_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.shipToCustCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.sellToCustCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.sldToCustLocCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_SLD_TO_CUST_LOC_CD));
        // START 2018/01/18 J.Kim [QC#17985,MOD]
        // ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.invNum, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_INV_NUM));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.apVndInvNum, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_AP_VND_INV_NUM));
        // END 2018/01/18 J.Kim [QC#17985,MOD]
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.invDt, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_INV_DT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.mdseTpCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_MDSE_TP_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.prntDsAssetMstrPk, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_PRNT_DS_ASSET_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.vndCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_VND_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.vndNm, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_VND_NM));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.poOrdNum, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.dsAssetDescTxt, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_DS_ASSET_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.contrEffFromDt, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_CONTR_EFF_FROM_DT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.manEntryFlg, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_MAN_ENTRY_FLG));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.assetTagNum, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ASSET_TAG_NUM));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.origStdCostAmt, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_ORIG_STD_COST_AMT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.origCsmpPrcAmt, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_ORIG_CSMP_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.origOrdAdjAmt, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_ORIG_ORD_ADJ_AMT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.assetCostSplPct, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_ASSET_COST_SPL_PCT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.batCalcCsmpPrcAmt, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_BAT_CALC_CSMP_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.batCalcOrdPrcAmt, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_BAT_CALC_ORD_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.batCalcAssetAdjPrcAmt, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_BAT_CALC_ASSET_ADJ_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.batCalcInitBookAmt, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_BAT_CALC_INIT_BOOK_AMT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.assetCalcStsCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ASSET_CALC_STS_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.baseCmptFlg, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_BASE_CMPT_FLG));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.assetPostFlg, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ASSET_POST_FLG));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.svcConfigMstrPk, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.asgDtlCmntTxt, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ASG_DTL_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.finDtlCmntTxt, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_FIN_DTL_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.firstLineAddr, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.scdLineAddr, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.thirdLineAddr, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.frthLineAddr, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.ctyAddr, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.stCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ST_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.postCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_POST_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.assetLeaseNum, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ASSET_LEASE_NUM));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.leaseStartDt, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_LEASE_START_DT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.leaseEndDt, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_LEASE_END_DT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.procModeCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_PROC_MODE_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.totAssetQty, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_TOT_ASSET_QTY));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.expCoaCmpyCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EXP_COA_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.expCoaBrCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EXP_COA_BR_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.expCoaCcCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EXP_COA_CC_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.expCoaAcctCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EXP_COA_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.expCoaProdCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EXP_COA_PROD_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.expCoaChCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EXP_COA_CH_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.expCoaAfflCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EXP_COA_AFFL_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.expCoaProjCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EXP_COA_PROJ_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.expCoaExtnCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_EXP_COA_EXTN_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.assetCoaCmpyCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ASSET_COA_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.assetCoaBrCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ASSET_COA_BR_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.assetCoaCcCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ASSET_COA_CC_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.assetCoaAcctCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ASSET_COA_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.assetCoaProdCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ASSET_COA_PROD_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.assetCoaChCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ASSET_COA_CH_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.assetCoaAfflCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ASSET_COA_AFFL_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.assetCoaProjCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ASSET_COA_PROJ_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.assetCoaExtnCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ASSET_COA_EXTN_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.assetRtireRsnCmntTxt, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ASSET_RTIRE_RSN_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.lastBillDt, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_LAST_BILL_DT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.bllgInvNum, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_BLLG_INV_NUM));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.prcdFromSlsAmt, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_PRCD_FROM_SLS_AMT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.rmvCostAmt, (BigDecimal) oldDsAssetMstrInfoMap.get(MAP_KEY_RMV_COST_AMT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.coaMdseTpCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_COA_MDSE_TP_CD));
        // START 2018/06/25 J.Kim [QC#24844, ADD]
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.dsContrNum, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_DS_CONTR_NUMT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.contrEffThruDt, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_CONTR_EFF_THRU_DT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.custIssPoNum, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_CUST_ISS_PO_NUM));
        // END 2018/06/25 J.Kim [QC#24844, ADD]
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.dtlCmntTxt, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_DTL_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.adjCoaAcctCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_ADJ_COA_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(oldDsAssetMstrTMsg.amtChngRsnTpCd, (String) oldDsAssetMstrInfoMap.get(MAP_KEY_AMT_CHNG_RSN_TP_CD));

        return oldDsAssetMstrTMsg;
    }

    /**
     * Get Per Month Number
     * @param cpoOrdNum String
     * @param cpoDtlLineNum String
     * @param cpoDtlLineSubNum String
     * @return String
     */
    private String getPerMthNum(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(QRY_PARAM_CPO_ORD_NUM, cpoOrdNum);
        queryParam.put(QRY_PARAM_CPO_DTL_LINE_NUM, cpoDtlLineNum);
        queryParam.put(QRY_PARAM_CPO_DTL_LINE_SUB_NUM, cpoDtlLineSubNum);
        // START 2019/06/05 S.Yamamoto [QC#50717, ADD]
        queryParam.put(QRY_PARAM_DS_CONTR_CRAT_TP_CD, DS_CONTR_CRAT_TP.SHELL);
        // END   2019/06/05 S.Yamamoto [QC#50717, ADD]

        return (String) ssmBatchClient.queryObject("getPerMthNum", queryParam);
    }

    /**
     * Get DS_ASSET_MSTR Per Month Number
     * @param initCostCalucInfoMap
     * @return String
     */
    private String getDsAssetPerMthNum(BigDecimal dsAssetMstrPK) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(QRY_PARAM_DS_ASSET_MSTR_PK, dsAssetMstrPK);

        return (String) ssmBatchClient.queryObject("getDsAssetPerMthNum", queryParam);
    }

    /**
     * Set Initial Cost Calculation
     * @param param NLZC305001_updDtlListPMsg
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param trkItemMap Map<String, String>
     * @param initCostCalucInfoMapList List<Map<String, Object>>
     */
    private void setInitCostCaluc(NLZC305001_updDtlListPMsg param, DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, Map<String, String> trkItemMap, List<Map<String, Object>> initCostCalucInfoMapList) {

        /*************************************************************
         * 4. Set Depreciation Month Number
         ************************************************************/
        String depcMthNum = null;

        if (ZYPCommonFunc.hasValue(param.depcMthNum)) {

            depcMthNum = param.depcMthNum.getValue();

        } else {

            boolean mainMachFlg = false;

            for (Map<String, Object> initCostCalucInfoMap : initCostCalucInfoMapList) {

                BigDecimal dsAssetMstrPK = (BigDecimal) initCostCalucInfoMap.get(MAP_KEY_DS_ASSET_MSTR_PK);
                BigDecimal prntDsAssetMstrPK = (BigDecimal) initCostCalucInfoMap.get(MAP_KEY_PRNT_DS_ASSET_MSTR_PK);

                if (dsAssetMstrPK.equals(prntDsAssetMstrPK)) {

                    mainMachFlg = true;
                    depcMthNum = getPerMthNum((String) initCostCalucInfoMap.get(MAP_KEY_CPO_ORD_NUM), (String) initCostCalucInfoMap.get(MAP_KEY_CPO_DTL_LINE_NUM), (String) initCostCalucInfoMap.get(MAP_KEY_CPO_DTL_LINE_SUB_NUM));
                    break;
                }
            }

            if (!mainMachFlg) {

                depcMthNum = getDsAssetPerMthNum(param.prntDsAssetMstrPk.getValue());

                if (!ZYPCommonFunc.hasValue(depcMthNum)) {

                    DS_ASSET_MSTRTMsg dsAssetMstrTMsg = getDsAssetMstr(param.prntDsAssetMstrPk.getValue());
                    SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(dsAssetMstrTMsg.svcMachMstrPk.getValue());
                    depcMthNum = getPerMthNum(svcMachMstrTMsg.cpoOrdNum.getValue(), svcMachMstrTMsg.cpoDtlLineNum.getValue(), svcMachMstrTMsg.cpoDtlLineSubNum.getValue());
                }

            }
        }

        if (!ZYPCommonFunc.hasValue(depcMthNum)) {
            return;
        }

        /*************************************************************
         * 5. Set Initial Cost
         ************************************************************/
        BigDecimal totOrigStdCostAmt = BigDecimal.ZERO;
        BigDecimal origOrdAdjAmt = BigDecimal.ZERO;

        for (Map<String, Object> initCostCalucInfoMap : initCostCalucInfoMapList) {

            // Summarize original standard cost
            if (ZYPCommonFunc.hasValue((BigDecimal) initCostCalucInfoMap.get("ORIG_STD_COST_AMT"))) {

                totOrigStdCostAmt = totOrigStdCostAmt.add((BigDecimal) initCostCalucInfoMap.get("ORIG_STD_COST_AMT"));
            }

            // Get adjustment amount & CSMP price from main machine
            if (param.prntDsAssetMstrPk.getValue().equals((BigDecimal) initCostCalucInfoMap.get("DS_ASSET_MSTR_PK"))) {

                origOrdAdjAmt = (BigDecimal) initCostCalucInfoMap.get("ORIG_ORD_ADJ_AMT");
            }
        }

        /*************************************************************
         * 6. Get Constant for Asset Cost Calculation
         ************************************************************/
        int carcDeclPlace = ZYPCodeDataUtil.getNumConstValue(ASSET_COST_CARC_DECIMAL_PLACE, glblCmpyCd).intValue();
        String costRoundMode = ZYPCodeDataUtil.getVarCharConstValue(ASSET_COST_ROUNDING_MODE, glblCmpyCd);

        int roundingMode = BigDecimal.ROUND_DOWN;

        if (ROUND_UP.equals(costRoundMode)) {

            roundingMode = BigDecimal.ROUND_UP;

        } else if (ROUND_HALF_UP.equals(costRoundMode)) {

            roundingMode = BigDecimal.ROUND_HALF_UP;
        }

        // Get Calculation Ratio
        DS_COND_CONSTTMsg dsCondConstTMsg = null;
        if (oldDsAssetMstrTMsg == null) {
            DS_ASSET_MSTRTMsg dsAssetMstrTMsg = getDsAssetMstr(param.prntDsAssetMstrPk.getValue());
            dsCondConstTMsg = getDsCondConst(NLZC3050_CALC_RATIO, dsAssetMstrTMsg.assetTpCd.getValue());
        } else {
            dsCondConstTMsg = getDsCondConst(NLZC3050_CALC_RATIO, oldDsAssetMstrTMsg.assetTpCd.getValue());
        }

        if (dsCondConstTMsg == null || !ZYPCommonFunc.hasValue(dsCondConstTMsg.dsCondConstValNum_01)) {

            setParamErrMsg(param, NLZM2432E);
            return;
        }

        BigDecimal costCalcRatio = dsCondConstTMsg.dsCondConstValNum_01.getValue();
        BigDecimal totSplAmt = BigDecimal.ZERO;

        for (int i = 0; i < initCostCalucInfoMapList.size(); i++) {

            Map<String, Object> assetDtlInfoMap = initCostCalucInfoMapList.get(i);

            /*************************************************************
             * 7. Calculate Initial Book Value
             ************************************************************/
            BigDecimal initBookAmt = (BigDecimal) assetDtlInfoMap.get("ORIG_STD_COST_AMT");

            if (BigDecimal.ZERO.compareTo(totOrigStdCostAmt) == 0) {
                initBookAmt = BigDecimal.ZERO;
            } else {
                // Adjustment Price
                if (ZYPCommonFunc.hasValue(origOrdAdjAmt) && origOrdAdjAmt.abs().compareTo(BigDecimal.ZERO) > 0) {

                    BigDecimal splAmt = (origOrdAdjAmt.multiply(initBookAmt.divide(totOrigStdCostAmt, carcDeclPlace, roundingMode))).setScale(carcDeclPlace, roundingMode);
                    totSplAmt = totSplAmt.add(splAmt);

                    if (i == initCostCalucInfoMapList.size() - 1) {

                        if (origOrdAdjAmt.compareTo(totSplAmt) < 0) {

                            splAmt = splAmt.subtract(totSplAmt.subtract(origOrdAdjAmt));
                        }

                        if (origOrdAdjAmt.compareTo(totSplAmt) > 0) {

                            splAmt = splAmt.add(origOrdAdjAmt.subtract(totSplAmt));
                        }
                    }

                    initBookAmt = initBookAmt.subtract(splAmt);
                }

                // CSMP Price
                BigDecimal origCsmpPrcAmt = (BigDecimal) assetDtlInfoMap.get("ORIG_CSMP_PRC_AMT");

                if (ZYPCommonFunc.hasValue(origCsmpPrcAmt) && origCsmpPrcAmt.abs().compareTo(BigDecimal.ZERO) > 0) {

                    initBookAmt = initBookAmt.subtract(origCsmpPrcAmt);
                }

                // Adapt to Cost Calculation Ratio
                initBookAmt = (initBookAmt.multiply(costCalcRatio)).setScale(carcDeclPlace, roundingMode);
            }

            /*************************************************************
             * 8. Update DS Asset Master
             ************************************************************/
            BigDecimal dsAssetMstrPK = (BigDecimal) assetDtlInfoMap.get(MAP_KEY_DS_ASSET_MSTR_PK);
            BigDecimal prntDsAssetMstrPK = (BigDecimal) assetDtlInfoMap.get(MAP_KEY_PRNT_DS_ASSET_MSTR_PK);

            if (dsAssetMstrPK.equals(prntDsAssetMstrPK)) {

                DS_ASSET_MSTRTMsg existDsAssetMstrTMsg = getDsAssetMstr(prntDsAssetMstrPK);

                ZYPEZDItemValueSetter.setValue(existDsAssetMstrTMsg.initBookAmt, initBookAmt);
                ZYPEZDItemValueSetter.setValue(existDsAssetMstrTMsg.depcMthNum, depcMthNum);
                ZYPEZDItemValueSetter.setValue(existDsAssetMstrTMsg.curValAmt, initBookAmt);

                S21ApiTBLAccessor.update(existDsAssetMstrTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(existDsAssetMstrTMsg.getReturnCode())) {

                    String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", existDsAssetMstrTMsg.dsAssetMstrPk.getValue().toString());
                    setDBAcsErrMsg(param, NASM0007E, new String[] {TBL_DS_ASSET_MSTR, pKey });

                    return;
                }

            } else {

                DS_ASSET_MSTRTMsg updDsAssetMstrTMsg = getDsAssetMstr(dsAssetMstrPK);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.initBookAmt, initBookAmt);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.depcMthNum, depcMthNum);
                ZYPEZDItemValueSetter.setValue(updDsAssetMstrTMsg.curValAmt, initBookAmt);

                S21ApiTBLAccessor.update(updDsAssetMstrTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updDsAssetMstrTMsg.getReturnCode())) {

                    String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_MSTR_PK, " : ", ((BigDecimal) assetDtlInfoMap.get("DS_ASSET_MSTR_PK")).toString());
                    setDBAcsErrMsg(param, NWAM0317W, new String[] {TBL_DS_ASSET_MSTR, pKey });
                    return;
                }
            }
        }
    }

    /**
     * Set Cost Amount
     * @param param
     * @param assetMstrInfoMapList
     * @param totCurValAmt
     * @param totInitBookAmtt
     * @param totOrigStdCostAmt
     * @param splAmtMap
     * @return
     */
    private Map<String, BigDecimal> setCostAmt(NLZC305001_updDtlListPMsg param, //
            List<Map<String, Object>> assetMstrInfoMapList, //
            BigDecimal totCurValAmt, BigDecimal totInitBookAmtt, BigDecimal totOrigStdCostAmt) {

        Map<String, BigDecimal> splAmtMap = new HashMap<String, BigDecimal>();
        splAmtMap.put(MAX_SPL_AMT, BigDecimal.ZERO);
        splAmtMap.put(MAX_CUR_AMT, BigDecimal.ZERO);

        if (ZYPCommonFunc.hasValue(param.initBookAmt)) {

            int carcDeclPlace = ZYPCodeDataUtil.getNumConstValue(ASSET_COST_CARC_DECIMAL_PLACE, glblCmpyCd).intValue();
            String costRoundMode = ZYPCodeDataUtil.getVarCharConstValue(ASSET_COST_ROUNDING_MODE, glblCmpyCd);

            int roundingMode = BigDecimal.ROUND_DOWN;

            if (ROUND_UP.equals(costRoundMode)) {

                roundingMode = BigDecimal.ROUND_UP;

            } else if (ROUND_HALF_UP.equals(costRoundMode)) {

                roundingMode = BigDecimal.ROUND_HALF_UP;
            }

            BigDecimal paramInitBookAmt = param.initBookAmt.getValue();
            BigDecimal totSplAmt = BigDecimal.ZERO;

            for (int i = 0; i < assetMstrInfoMapList.size(); i++) {

                BigDecimal maxSplAmt = splAmtMap.get(MAX_SPL_AMT);

                Map<String, Object> oldDsAssetMstrInfoMap = assetMstrInfoMapList.get(i);

                DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg = setDsAssetMstrTMsg(oldDsAssetMstrInfoMap);

                BigDecimal curValAmt = oldDsAssetMstrTMsg.curValAmt.getValue();
                BigDecimal origStdCostAmt = oldDsAssetMstrTMsg.origStdCostAmt.getValue();
                BigDecimal initBookAmt = BigDecimal.ZERO;

                if (BigDecimal.ZERO.compareTo(totCurValAmt) != 0 && BigDecimal.ZERO.compareTo(curValAmt) != 0) {
                    // START 2018/09/20 J.Kim [QC#28341,MOD]
                    // BigDecimal splAmt = paramInitBookAmt.divide(totCurValAmt.divide(curValAmt, carcDeclPlace, roundingMode), carcDeclPlace, roundingMode);
                    BigDecimal splAmt = paramInitBookAmt.divide(totOrigStdCostAmt.divide(origStdCostAmt, COST_SCALE, roundingMode), carcDeclPlace, roundingMode);
                    // END 2018/09/20 J.Kim [QC#28341,MOD]
                    totSplAmt = totSplAmt.add(splAmt);

                    // Last Data Adjustment Amount
                    if (i == assetMstrInfoMapList.size() - 1) {

                        if(maxSplAmt.compareTo(splAmt) < 0) {
                            splAmtMap.put(MAX_SPL_AMT, splAmt);
                            splAmtMap.put(MAX_AMT_DS_ASSET_MSTR_PK, oldDsAssetMstrTMsg.dsAssetMstrPk.getValue());
                        }

                        maxSplAmt = splAmtMap.get(MAX_SPL_AMT);

                        if (paramInitBookAmt.compareTo(totSplAmt) < 0) {

                            maxSplAmt = maxSplAmt.subtract(totSplAmt.subtract(paramInitBookAmt));
                            splAmtMap.put(MAX_SPL_AMT, maxSplAmt);
                        }

                        if (paramInitBookAmt.compareTo(totSplAmt) > 0) {

                            maxSplAmt = maxSplAmt.add(paramInitBookAmt.subtract(totSplAmt));
                            splAmtMap.put(MAX_SPL_AMT, maxSplAmt);
                        }
                    } else {
                        initBookAmt = splAmt;

                        if(maxSplAmt.compareTo(initBookAmt) < 0) {
                            splAmtMap.put(MAX_SPL_AMT, initBookAmt);
                            splAmtMap.put(MAX_AMT_DS_ASSET_MSTR_PK, oldDsAssetMstrTMsg.dsAssetMstrPk.getValue());
                        }
                    }
                } else {
                    if (i == assetMstrInfoMapList.size() - 1) {
                        maxSplAmt = splAmtMap.get(MAX_SPL_AMT);

                        if (paramInitBookAmt.compareTo(totSplAmt) < 0) {

                            maxSplAmt = maxSplAmt.subtract(totSplAmt.subtract(paramInitBookAmt));
                            splAmtMap.put(MAX_SPL_AMT, maxSplAmt);
                        }

                        if (paramInitBookAmt.compareTo(totSplAmt) > 0) {

                            maxSplAmt = maxSplAmt.add(paramInitBookAmt.subtract(totSplAmt));
                            splAmtMap.put(MAX_SPL_AMT, maxSplAmt);
                        }
                    }
                }
            }

        } else if (ZYPCommonFunc.hasValue(param.curValAmt)) {

            int carcDeclPlace = ZYPCodeDataUtil.getNumConstValue(ASSET_COST_CARC_DECIMAL_PLACE, glblCmpyCd).intValue();
            String costRoundMode = ZYPCodeDataUtil.getVarCharConstValue(ASSET_COST_ROUNDING_MODE, glblCmpyCd);

            int roundingMode = BigDecimal.ROUND_DOWN;

            if (ROUND_UP.equals(costRoundMode)) {

                roundingMode = BigDecimal.ROUND_UP;

            } else if (ROUND_HALF_UP.equals(costRoundMode)) {

                roundingMode = BigDecimal.ROUND_HALF_UP;
            }

            BigDecimal paramCurValAmt = param.curValAmt.getValue();
            BigDecimal totSplAmt = BigDecimal.ZERO;

            for (int i = 0; i < assetMstrInfoMapList.size(); i++) {

                BigDecimal maxSplAmt = splAmtMap.get(MAX_CUR_AMT);

                Map<String, Object> oldDsAssetMstrInfoMap = assetMstrInfoMapList.get(i);

                DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg = setDsAssetMstrTMsg(oldDsAssetMstrInfoMap);

                BigDecimal curValAmt = oldDsAssetMstrTMsg.curValAmt.getValue();
                BigDecimal origStdCostAmt = oldDsAssetMstrTMsg.origStdCostAmt.getValue();

                if (BigDecimal.ZERO.compareTo(totCurValAmt) != 0 && BigDecimal.ZERO.compareTo(curValAmt) != 0) {
                    // START 2018/09/20 J.Kim [QC#28341,MOD]
                    // BigDecimal splAmt = paramCurValAmt.divide(totCurValAmt.divide(curValAmt, carcDeclPlace, roundingMode), carcDeclPlace, roundingMode);
                    BigDecimal splAmt = paramCurValAmt.divide(totOrigStdCostAmt.divide(origStdCostAmt, COST_SCALE, roundingMode), carcDeclPlace, roundingMode);
                    // END 2018/09/20 J.Kim [QC#28341,MOD]
                    totSplAmt = totSplAmt.add(splAmt);

                    // Last Data Adjustment Amount
                    if (i == assetMstrInfoMapList.size() - 1) {

                        if(maxSplAmt.compareTo(splAmt) < 0) {
                            splAmtMap.put(MAX_CUR_AMT, splAmt);
                            splAmtMap.put(MAX_AMT_DS_ASSET_MSTR_PK, oldDsAssetMstrTMsg.dsAssetMstrPk.getValue());
                        }

                        maxSplAmt = splAmtMap.get(MAX_CUR_AMT);

                        if (paramCurValAmt.compareTo(totSplAmt) < 0) {

                            maxSplAmt = maxSplAmt.subtract(totSplAmt.subtract(paramCurValAmt));
                            splAmtMap.put(MAX_CUR_AMT, maxSplAmt);
                        }

                        if (paramCurValAmt.compareTo(totSplAmt) > 0) {

                            maxSplAmt = maxSplAmt.add(paramCurValAmt.subtract(totSplAmt));
                            splAmtMap.put(MAX_CUR_AMT, maxSplAmt);
                        }
                    } else {
                        curValAmt = splAmt;

                        if(maxSplAmt.compareTo(curValAmt) < 0) {
                            splAmtMap.put(MAX_CUR_AMT, curValAmt);
                            splAmtMap.put(MAX_AMT_DS_ASSET_MSTR_PK, oldDsAssetMstrTMsg.dsAssetMstrPk.getValue());
                        }
                    }
                } else {
                    if (i == assetMstrInfoMapList.size() - 1) {

                        maxSplAmt = splAmtMap.get(MAX_CUR_AMT);

                        if (paramCurValAmt.compareTo(totSplAmt) < 0) {

                            maxSplAmt = maxSplAmt.subtract(totSplAmt.subtract(paramCurValAmt));
                            splAmtMap.put(MAX_CUR_AMT, maxSplAmt);
                        }

                        if (paramCurValAmt.compareTo(totSplAmt) > 0) {

                            maxSplAmt = maxSplAmt.add(paramCurValAmt.subtract(totSplAmt));
                            splAmtMap.put(MAX_CUR_AMT, maxSplAmt);
                        }
                    }
                }
            }
        }

        return splAmtMap;
    }

    // START 2018/04/16 J.Kim [QC#22807,ADD]
    /**
     * set CoaSegments
     * @param insDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param dsAssetStgngTMsg DS_ASSET_STGNGTMsg
     * @param assetTpTMsg ASSET_TPTMsg
     * @return
     */
    private void setCoaSegments(DS_ASSET_MSTRTMsg insDsAssetMstrTMsg, DS_ASSET_STGNGTMsg dsAssetStgngTMsg, ASSET_TPTMsg assetTpTMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(QRY_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        // START 2018/06/07 J.Kim [QC#26321,ADD]
        queryParam.put("dsAssetStgngPk", dsAssetStgngTMsg.dsAssetStgngPk.getValue());
        // END 2018/06/07 J.Kim [QC#26321,ADD]
        queryParam.put("assetTpCd", assetTpTMsg.assetTpCd.getValue());
        queryParam.put("slsDt", ZYPDateUtil.getSalesDate());
        queryParam.put("newcore", ZYPCodeDataUtil.getVarCharConstValue("AJE_NEW_CORE_CD", glblCmpyCd));
        queryParam.put("defBrNewcore", ZYPCodeDataUtil.getVarCharConstValue("AJE_DEF_COA_BR_NONCORE", glblCmpyCd));
        // START 2018/06/07 J.Kim [QC#26321,DEL]
        // queryParam.put("defCcNewcore", ZYPCodeDataUtil.getVarCharConstValue("AJE_DEF_COA_CC_NONCORE", glblCmpyCd));
        // END 2018/06/07 J.Kim [QC#26321,DEL]
        Map<String, String> segmentsInfoMap = (Map<String, String>) ssmBatchClient.queryObject("getCoaSegments", queryParam);

        if (segmentsInfoMap == null || segmentsInfoMap.isEmpty()) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaAcctCd, segmentsInfoMap.get(ASSET_COA_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaBrCd, segmentsInfoMap.get(getAjeDefaultCoa(CoaSegment.BR, assetTpTMsg)));
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaCcCd, segmentsInfoMap.get(getAjeDefaultCoa(CoaSegment.CC, assetTpTMsg)));
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaExtnCd, segmentsInfoMap.get(getAjeDefaultCoa(CoaSegment.EXTN, assetTpTMsg)));
    }

    /**
     * getAjeDefaultCoa
     * @param coa CoaSegment
     * @param assetTpTMsg ASSET_TPTMsg
     * @return String
     */
    public static String getAjeDefaultCoa(CoaSegment coa, ASSET_TPTMsg assetTpTMsg) {

        String ajeCoaBrcd = assetTpTMsg.ajeCoaBrCd.getValue();
        String ajeCoaCccd = assetTpTMsg.ajeCoaCcCd.getValue();
        String ajeCoaBucd = assetTpTMsg.ajeCoaBuCd.getValue();

        String val = "";
        switch (coa) {
            case BR:
                val = getCoaValue(ajeCoaBrcd, BR);
                break;
            case CC:
                val = getCoaValue(ajeCoaCccd, CC);
                break;
            case EXTN:
                val = getCoaValue(ajeCoaBucd, EXTN);
                break;
            default:
                val = null;
                break;
        }

        return val;
    }

    private static String getCoaValue(String defaultCoa, String coa) {

        if (BR.equals(coa)) {
            if (getPattren(defaultCoa)) {
                return NEWCORE_COA_BR_CD;
            } else {
                return TOC_COA_BR_CD;
            }
        }

        if (CC.equals(coa)) {
            if (getPattren(defaultCoa)) {
                return NEWCORE_COA_CC_CD;
            } else {
                return TOC_COA_CC_CD;
            }
        }

        if (EXTN.equals(coa)) {
            if (getPattren(defaultCoa)) {
                return TOC_COA_EXTN_CD;
            }
        }
        return defaultCoa;
    }

    private static boolean getPattren(String defaultCoa) {

        if (COA_SEG_LKUP_TP.NEWCORE.equals(defaultCoa)) {
            return true;
        }

        if (COA_SEG_LKUP_TP.FROM_ORGANIZATION_MASTER_USING_TOC_CODE.equals(defaultCoa)) {
            return true;
        }

        return false;
    }
    // END 2018/04/16 J.Kim [QC#22807,ADD]

    // START 2018/04/24 J.Kim [QC#25791,ADD]
    /**
     * setDefaultExpenseCOA
     * @param insDsAssetMstrTMsg NLEL0060CMsg
     * @return String
     */
    private void setDefaultExpenseCOA(DS_ASSET_MSTRTMsg insDsAssetMstrTMsg) {

        String defExpCoa = ZYPCodeDataUtil.getVarCharConstValue(DEF_EXP_COA, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(defExpCoa)) {
            return;
        }

        String[] defaultExpenseCoaList = defExpCoa.split(",");
        Map<String, String> defaultCoaMap = getDefaultCoaMapList(insDsAssetMstrTMsg);
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaCmpyCd, getDefaultCoa(defaultExpenseCoaList[0], defaultCoaMap, CMPY_CD));
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaBrCd,   getDefaultCoa(defaultExpenseCoaList[1], defaultCoaMap, BR_CD));
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaCcCd,   getDefaultCoa(defaultExpenseCoaList[2], defaultCoaMap, CC_CD));
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaAcctCd, getDefaultCoa(defaultExpenseCoaList[3], defaultCoaMap, ACCT_CD));
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaProdCd, getDefaultCoa(defaultExpenseCoaList[4], defaultCoaMap, PROD_CD));
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaChCd,   getDefaultCoa(defaultExpenseCoaList[5], defaultCoaMap, CH_CD));
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaAfflCd, getDefaultCoa(defaultExpenseCoaList[6], defaultCoaMap, AFFL_CD));
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaProjCd, getDefaultCoa(defaultExpenseCoaList[7], defaultCoaMap, PROJ_CD));
        ZYPEZDItemValueSetter.setValue(insDsAssetMstrTMsg.expCoaExtnCd, getDefaultCoa(defaultExpenseCoaList[8], defaultCoaMap, EXTN_CD));
        return;
    }

    private String getDefaultCoa(String defaultExpenseCoa, Map<String, String> defaultCoaMap, String coa) {

        String defaultExp = "";
        if (defaultExpenseCoa.startsWith("@", 0)) {
            if (defaultCoaMap != null) {
                defaultExp = defaultCoaMap.get(defaultExpenseCoa.substring(1) + coa);
            }
        } else {
            defaultExp = defaultExpenseCoa;
        }
        return defaultExp;
    }

    private Map<String, String> getDefaultCoaMapList(DS_ASSET_MSTRTMsg insDsAssetMstrTMsg) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", glblCmpyCd);
        map.put("sellToCustCd", insDsAssetMstrTMsg.sellToCustCd.getValue());

        Map<String, String> defaultCoa = (Map<String, String>) ssmBatchClient.queryObject("getDefaultCoaList", map);
        if (defaultCoa == null || defaultCoa.isEmpty()) {
            return null;
        }
        return defaultCoa;
    }
    // END 2018/04/24 J.Kim [QC#25791,ADD]

    // START 2018/07/25 J.Kim [QC#24950,ADD]
    /**
     * Process Mode
     * @param procModeCd String
     * @return PROC_MODETMsg
     */
    private PROC_MODETMsg getProcModeNm(String procModeCd) {

        PROC_MODETMsg procModeTMsg = new PROC_MODETMsg();
        ZYPEZDItemValueSetter.setValue(procModeTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(procModeTMsg.procModeCd, procModeCd);

        return (PROC_MODETMsg) S21ApiTBLAccessor.findByKey(procModeTMsg);
    }
    // END 2018/07/25 J.Kim [QC#24950,ADD]

    // START 2018/08/09 J.Kim [QC#26901,ADD]
    /**
     * getAmtChngRsnTpNm
     * @param amtChngRsnTpCd String
     * @return AMT_CHNG_RSN_TPTMsg
     */
    private String getAmtChngRsnTpNm(String amtChngRsnTpCd) {

        AMT_CHNG_RSN_TPTMsg amtChngRsnTpMsg = new AMT_CHNG_RSN_TPTMsg();
        ZYPEZDItemValueSetter.setValue(amtChngRsnTpMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(amtChngRsnTpMsg.amtChngRsnTpCd, amtChngRsnTpCd);

        AMT_CHNG_RSN_TPTMsg amtChngRsnTpTMsg = (AMT_CHNG_RSN_TPTMsg) S21ApiTBLAccessor.findByKey(amtChngRsnTpMsg);
        if (amtChngRsnTpTMsg == null) {
            return null;
        }

        return amtChngRsnTpTMsg.amtChngRsnTpNm.getValue();
    }
    // END 2018/08/09 J.Kim [QC#26901,ADD]

    // START 2018/08/13 J.Kim [QC#22267,ADD]
    /**
     * getAssetStsNm
     * @param assetStsCd String
     * @return ASSET_STSTMsg
     */
    private String getAssetStsNm(String assetStsCd) {

        ASSET_STSTMsg assetStsTMsg = new ASSET_STSTMsg();
        ZYPEZDItemValueSetter.setValue(assetStsTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(assetStsTMsg.assetStsCd, assetStsCd);

        ASSET_STSTMsg out = (ASSET_STSTMsg) S21ApiTBLAccessor.findByKey(assetStsTMsg);
        if (out == null) {
            return null;
        }

        return out.assetStsNm.getValue();
    }

    /**
     * getAcctNm
     * @param String sellToCust
     * @return boolean
     */
    private String getAcctNm(String sellToCust) {

        SELL_TO_CUSTTMsg inMsg = new SELL_TO_CUSTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("sellToCustCd01", sellToCust);
        SELL_TO_CUSTTMsgArray outMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (outMsgArray == null || outMsgArray.getValidCount() == 0) {
            return null;
        }

        SELL_TO_CUSTTMsg outTMsg = (SELL_TO_CUSTTMsg) outMsgArray.get(0);

        return outTMsg.dsAcctNm.getValue();
    }

    /**
     * Get vender by vender code
     * @param vndCd String
     * @return String
     */
    private String getVnd(String vndCd) {

        PRNT_VNDTMsg prntVndTMsg = new PRNT_VNDTMsg();
        prntVndTMsg.setSQLID("001");
        prntVndTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prntVndTMsg.setConditionValue("prntVndCd01", vndCd);
        prntVndTMsg.setConditionValue("inacDt01", ZYPDateUtil.getSalesDate());

        PRNT_VNDTMsgArray prntVndTMsgArray = (PRNT_VNDTMsgArray) EZDTBLAccessor.findByCondition(prntVndTMsg);
        if (prntVndTMsgArray == null || prntVndTMsgArray.getValidCount() == 0) {
            return null;
        }

        PRNT_VNDTMsg outTMsg = prntVndTMsgArray.no(0);

        return outTMsg.prntVndNm.getValue();
    }

    // END 2018/08/13 J.Kim [QC#22267,ADD]

    // START 2018/07/31 J.Kim [QC#26902,ADD]
    /**
     * Create DS Asset Transaction. update Change Initial book amount
     * @param oldDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param updDsAssetMstrTMsg DS_ASSET_MSTRTMsg
     * @param param NLZC305001_updDtlListPMsg
     */
    private void insertDsAssetTrxUpdInitBookAmtImport(DS_ASSET_MSTRTMsg oldDsAssetMstrTMsg, DS_ASSET_MSTRTMsg updDsAssetMstrTMsg, NLZC305001_updDtlListPMsg param) {

        // Initial book ammount change
        BigDecimal dsAssetTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_TRX_SQ);

        DS_ASSET_TRXTMsg insrtDsAssetTrxTMsg = new DS_ASSET_TRXTMsg();
        // Set parameters Asset Transaction Data
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetTrxPk, dsAssetTrxPk);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTrxDt, slsDt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.dsAssetMstrPk, updDsAssetMstrTMsg.dsAssetMstrPk);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.mdseCd, updDsAssetMstrTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.serNum, updDsAssetMstrTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.assetTpCd, updDsAssetMstrTMsg.assetTpCd);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxCd, TRX.ADJUSTMENT);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.trxRsnCd, TRX_RSN.ASSET_INITIAL_COST_CHANGE);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.ajeIfReqFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.sysSrcCd, SYS_SRC.S21_ASSET);
        // Set parameters Asset Transaction Depreciation
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.initBookAmt, updDsAssetMstrTMsg.initBookAmt.getValue().subtract(oldDsAssetMstrTMsg.origInitBookAmt.getValue()));
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.curValAmt, updDsAssetMstrTMsg.initBookAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.prevValAmt, oldDsAssetMstrTMsg.origInitBookAmt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcValAmt, oldDsAssetMstrTMsg.origInitBookAmt.getValue().subtract(updDsAssetMstrTMsg.initBookAmt.getValue()));
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcCnt, updDsAssetMstrTMsg.depcCnt);
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.depcMthNum, updDsAssetMstrTMsg.depcMthNum);
        // START 2018/08/21 J.Kim [QC#27747,ADD]
        ZYPEZDItemValueSetter.setValue(insrtDsAssetTrxTMsg.slsRepTocCd, updDsAssetMstrTMsg.slsRepTocCd);
        // END 2018/08/21 J.Kim [QC#27747,ADD]

        S21ApiTBLAccessor.insert(insrtDsAssetTrxTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insrtDsAssetTrxTMsg.getReturnCode())) {
            String pKey = ZYPCommonFunc.concatString(ATTRB_DS_ASSET_TRX_PK, " : ", insrtDsAssetTrxTMsg.dsAssetTrxPk.getValue().toString());
            setDBAcsErrMsg(param, NWAM0317W, new String[] {TBL_DS_ASSET_TRX, pKey });
        }
    }
    // END 2018/07/31 J.Kim [QC#26902,ADD]

}
