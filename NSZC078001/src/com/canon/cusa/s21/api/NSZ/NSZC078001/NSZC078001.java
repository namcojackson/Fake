package com.canon.cusa.s21.api.NSZ.NSZC078001;

import java.math.BigDecimal;
import static com.canon.cusa.s21.api.NSZ.NSZC078001.constant.NSZC078001Constant.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_SPLY_ABUSE_STAGETMsg;
import business.db.SVC_SPLY_MACH_ALLWTMsg;
import business.db.SVC_SPLY_MACH_USEDTMsg;
import business.parts.NSZC078001PMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.SVC_COV_FEAT;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001MtrCntTwoPntEst;
import com.canon.cusa.s21.common.NSX.NSXC003001.SvcPhysMtrReadInfoBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMG_SPLY_COLOR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_SPLY_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Supply Abuse Update API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/30/2015   Hitachi         T.Harada        Create          
 * 03/17/2016   Hitachi         A.Kohinata      Update          QC#5647
 * 03/22/2016   Hitachi         A.Kohinata      Update          QC#5707
 * 03/28/2016   Hitachi         A.Kohinata      Update          QC#6053
 * 10/19/2016   Hitachi         A.Kohinata      Update          QC#15344
 * 05/25/2017   Hitachi         K.Kojima        Update          QC#18700
 * 08/04/2017   Hitachi         T.Tomita        Update          QC#18314
 * 09/08/2017   Hitachi         T.Kanasaka      Update          QC#15134
 * 12/19/2017   Hitachi         U.Kim           Update          QC#21527
 * 05/30/2018   CITS            T.Wada          Update          QC#15410(Sol#246)
 * 2018/09/07   Hitachi         K.Kojima        Update          QC#28130
 * 2018/10/01   Hitachi         T.Tomita        Update          QC#28478
 * 2018/10/31   Fujitsu         M.Yamada        Update          QC#28954
 * 2018/12/20   Hitachi         S.Kitamura      Update          QC#25683
 * 2019/12/12   Hitachi         K.Kitachi       Update          QC#55064
 * </pre>
 */
public class NSZC078001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** contrPk & mdseCd Map */
    private Map<String, ContrMdseInfoBean> contrMdseMap = new HashMap<String, ContrMdseInfoBean>();

    /**
     * Constructor
     */
    public NSZC078001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Execute API.
     * @param pMsg NSZC07801PMsg
     * @param onBatTp ONBATCH_TYPE
     */
    public void execute(NSZC078001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);

        if (checkParameter(pMsg, msgMap)) {
            doProcess(pMsg, msgMap);
        }
        msgMap.flush();
    }

    private boolean checkParameter(NSZC078001PMsg pMsg, S21ApiMessageMap msgMap) {

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgIdWithPrm(NZZM0012E, new String[] {"glblCmpyCd" });
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.procDt)) {
            msgMap.addXxMsgIdWithPrm(NZZM0012E, new String[] {"procDt" });
            return false;
        }
        int svcMachMstrPkCount = pMsg.svcMachMstrPkList.getValidCount();
        if (svcMachMstrPkCount < 1) {
            msgMap.addXxMsgIdWithPrm(NZZM0012E, new String[] {"svcMachMstrPkList" });
            return false;
        }
        for (int i = 0; i < svcMachMstrPkCount; i++) {
            if (!ZYPCommonFunc.hasValue(pMsg.svcMachMstrPkList.no(i).svcMachMstrPk)) {
                msgMap.addXxMsgIdWithPrm(NZZM0012E, new String[] {"svcMachMstrPk" });
                return false;
            }
        }

        return true;
    }

    private void doProcess(final NSZC078001PMsg pMsg, S21ApiMessageMap msgMap) {

        String gcc = pMsg.glblCmpyCd.getValue();
        String procDt = pMsg.procDt.getValue();

        for (int i = 0; i < pMsg.svcMachMstrPkList.getValidCount(); i++) {

            BigDecimal svcMachMstrPk = pMsg.svcMachMstrPkList.no(i).svcMachMstrPk.getValue();
            BigDecimal dsContrPk = pMsg.svcMachMstrPkList.no(i).dsContrPk.getValue(); // QC#28954
            Map<String, Object> supplyMdseMap = new HashMap<String, Object>();

            // 2.getContrData
            // mod start 2016/10/19 CSA Defect#15344
            if (!getContrMdseData(gcc, procDt, svcMachMstrPk, dsContrPk, supplyMdseMap, msgMap)) { // QC#28954
                continue;
            }
            // mod end 2016/10/19 CSA Defect#15344

            // 3.createAllowedData
            if (!createAllowedData(gcc, procDt, svcMachMstrPk, supplyMdseMap, msgMap)) {
                continue;
            }

            // 4.createUsedData
            createUsedData(gcc, procDt, svcMachMstrPk, supplyMdseMap, msgMap);
        }

        for (ContrMdseInfoBean bean : contrMdseMap.values()) {
            // 5.createWatchListData
            createWatchListData(gcc, procDt, bean, msgMap);
        }
    }

    // mod start 2016/10/19 CSA Defect#15344
    private boolean getContrMdseData(//
            String gcc, String procDt, BigDecimal svcMachMstrPk, BigDecimal dsContrPkPrm, Map<String, Object> supplyMdseMap, S21ApiMessageMap msgMap) {
    // mod end 2016/10/19 CSA Defect#15344

        BigDecimal dsContrPk = null;
        String svcPgmMdseCd = null;

        // 2.1.getContrData
        // mod start 2016/10/19 CSA Defect#15344
        Map<String, Object> contrInfo = getContrInfo(gcc, procDt, svcMachMstrPk, dsContrPkPrm);
        // mod end 2016/10/19 CSA Defect#15344
        if (contrInfo == null) {
            return false;
        }
        dsContrPk = (BigDecimal) contrInfo.get("DS_CONTR_PK");
        svcPgmMdseCd = (String) contrInfo.get("SVC_PGM_MDSE_CD");
        String combinedKey = dsContrPk.toString() + "_" + svcPgmMdseCd;
        if (!this.contrMdseMap.containsKey(combinedKey)) {
            ContrMdseInfoBean bean = new ContrMdseInfoBean();
            bean.setDsContrPk(dsContrPk);
            bean.setSvcPgmMdseCd(svcPgmMdseCd);
            bean.setSvcContrBrCd((String) contrInfo.get("SVC_CONTR_BR_CD"));
            bean.setDsContrEdiCd((String) contrInfo.get("DS_CONTR_EDI_CD"));
            bean.setSvcRgPk((BigDecimal) contrInfo.get("SVC_RG_PK"));
            bean.setSellToCustCd((String) contrInfo.get("SELL_TO_CUST_CD"));
            bean.setMtrBllgCycleCd((String) contrInfo.get("MTR_BLLG_CYCLE_CD"));
            this.contrMdseMap.put(combinedKey, bean);
        }
        supplyMdseMap.put("dsContrPk", dsContrPk);
        supplyMdseMap.put("dsContrDtlPk", (BigDecimal) contrInfo.get("DS_CONTR_DTL_PK"));
        supplyMdseMap.put("svcPgmMdseCd", svcPgmMdseCd);
        supplyMdseMap.put("sellToCustCd", (String) contrInfo.get("SELL_TO_CUST_CD"));
        supplyMdseMap.put("svcContrBrCd", (String) contrInfo.get("SVC_CONTR_BR_CD"));
        supplyMdseMap.put("mtrBllgCycleCd", (String) contrInfo.get("MTR_BLLG_CYCLE_CD"));

        // 2.2.getSupplyMdseData
        for (int i = 0; i < IMG_SPLY_COLOR_TP_LIST.length; i++) {
            BigDecimal custStkQty = BigDecimal.ZERO;
            BigDecimal stdYieldCnt = BigDecimal.ZERO;
            BigDecimal splyTolPct = BigDecimal.ZERO;
            BigDecimal thisMthTotStdCostAmt = BigDecimal.ZERO;
            String clTp = IMG_SPLY_COLOR_TP_LIST[i];
            Map<String, Object> supplyMdse = getSupplyMdseInfo(gcc, svcMachMstrPk, clTp);
            if (supplyMdse != null) {
                custStkQty = (BigDecimal) supplyMdse.get("CUST_STK_QTY");
                stdYieldCnt = (BigDecimal) supplyMdse.get("STD_YIELD_CNT");
                splyTolPct = (BigDecimal) supplyMdse.get("SPLY_TOL_PCT");
                thisMthTotStdCostAmt = (BigDecimal) supplyMdse.get("THIS_MTH_TOT_STD_COST_AMT");
            }
            if (!ZYPCommonFunc.hasValue(custStkQty) || !ZYPCommonFunc.hasValue(stdYieldCnt) || !ZYPCommonFunc.hasValue(splyTolPct) || !ZYPCommonFunc.hasValue(thisMthTotStdCostAmt)) {
                return false;
            }
            supplyMdseMap.put("custStkQty_" + clTp, custStkQty);
            supplyMdseMap.put("stdYieldCnt_" + clTp, stdYieldCnt);
            supplyMdseMap.put("splyTolPct_" + clTp, splyTolPct);
            supplyMdseMap.put("thisMthTotStdCostAmt_" + clTp, thisMthTotStdCostAmt);
        }

        return true;
    }

    // mod start 2016/10/19 CSA Defect#15344
    private Map<String, Object> getContrInfo(String gcc, String procDt, BigDecimal svcMachMstrPk, BigDecimal dsContrPk) { // QC#28954
    // mod end 2016/10/19 CSA Defect#15344
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("dsContrPk", dsContrPk); // QC#28954
        param.put("svcMachMstrPk", svcMachMstrPk);
        // add start 2016/10/19 CSA Defect#15344
        param.put("procDt", procDt);
        param.put("dsContrCatgWty", DS_CONTR_CATG.WARRANTY);
        // add end 2016/10/19 CSA Defect#15344
        // START 2017/12/19 U.Kim [QC#21527, ADD]
        param.put("dsContrCatgFlt", DS_CONTR_CATG.FLEET);
        param.put("flgY", ZYPConstant.FLG_ON_Y);
        // END 2017/12/19 U.Kim [QC#21527, ADD]

        // START 2018/05/30 T.Wada [QC#15410, ADD]
        param.put("svcCovFeatSplyIncl", SVC_COV_FEAT.SPLY_INCL);
        // END 2018/05/30 T.Wada [QC#15410, ADD]

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getContrInfo", param);
    }

    private Map<String, Object> getSupplyMdseInfo(String gcc, BigDecimal svcMachMstrPk, String colorTp) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("imgSplyColorTpCd", colorTp);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getSupplyMdseInfo", param);
    }

    private boolean createAllowedData(String gcc, String procDt, BigDecimal svcMachMstrPk, Map<String, Object> supplyMdseMap, S21ApiMessageMap msgMap) {

        BigDecimal dsContrPk = (BigDecimal) supplyMdseMap.get("dsContrPk");

        // START 2018/09/07 K.Kojima [QC#28130,ADD]
        // Delete Same Process Date Data
        logicalRemoveSvcSplyMachAllw(gcc, procDt, dsContrPk, svcMachMstrPk);
        // END 2018/09/07 K.Kojima [QC#28130,ADD]

        // 3.1 UpdateSvcSplyMachAllw FRZ_FLG
        updateSvcSplyMachAllw(gcc, dsContrPk, svcMachMstrPk, msgMap);

        // 3.2 setInsertSvcSplyMachAllwParam
        SVC_SPLY_MACH_ALLWTMsg tmsg = new SVC_SPLY_MACH_ALLWTMsg();
        if (!setInsertSvcSplyMachAllwParam(gcc, procDt, svcMachMstrPk, supplyMdseMap, tmsg)) {
            return false;
        }

        // 3.3 insertSvcSplyMachAllw
        insertSvcSplyMachAllw(tmsg, msgMap);

        return true;
    }

    // START 2018/09/07 K.Kojima [QC#28130,ADD]
    private void logicalRemoveSvcSplyMachAllw(String gcc, String procDt, BigDecimal dsContrPk, BigDecimal svcMachMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("procDt", procDt);
        param.put("dsContrPk", dsContrPk);
        param.put("svcMachMstrPk", svcMachMstrPk);
        List<BigDecimal> svcSplyMachAllwPkList = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getLogicalRemoveSvcSplyMachAllw", param);

        for (BigDecimal svcSplyMachAllwPk : svcSplyMachAllwPkList) {
            SVC_SPLY_MACH_ALLWTMsg tMsg = new SVC_SPLY_MACH_ALLWTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, gcc);
            ZYPEZDItemValueSetter.setValue(tMsg.svcSplyMachAllwPk, svcSplyMachAllwPk);
            S21ApiTBLAccessor.logicalRemove(tMsg);
        }
    }

    // END 2018/09/07 K.Kojima [QC#28130,ADD]

    private void updateSvcSplyMachAllw(String gcc, BigDecimal dsContrPk, BigDecimal svcMachMstrPk, S21ApiMessageMap msgMap) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("dsContrPk", dsContrPk);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("flgN", ZYPConstant.FLG_OFF_N);
        // START 2018/09/07 K.Kojima [QC#28130,MOD]
        // BigDecimal svcSplyMachAllwPk = (BigDecimal) this.ssmBatchClient.queryObject("getSvcSplyMachAllwPk", param);
        List<BigDecimal> svcSplyMachAllwPkList = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getSvcSplyMachAllwPk", param);
        // END 2018/09/07 K.Kojima [QC#28130,MOD]

        // START 2018/09/07 K.Kojima [QC#28130,MOD]
        // if (svcSplyMachAllwPk != null) {
        for (BigDecimal svcSplyMachAllwPk : svcSplyMachAllwPkList) {
        // END 2018/09/07 K.Kojima [QC#28130,MOD]
            SVC_SPLY_MACH_ALLWTMsg tmsg = new SVC_SPLY_MACH_ALLWTMsg();
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, gcc);
            ZYPEZDItemValueSetter.setValue(tmsg.svcSplyMachAllwPk, svcSplyMachAllwPk);
            tmsg = (SVC_SPLY_MACH_ALLWTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

            ZYPEZDItemValueSetter.setValue(tmsg.frzFlg, ZYPConstant.FLG_ON_Y);
            S21FastTBLAccessor.update(tmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSZM0398E, new String[] {"SVC_SPLY_MACH_ALLW", svcSplyMachAllwPk.toString()});
            }
        }
    }

    private boolean setInsertSvcSplyMachAllwParam(String gcc, String procDt, BigDecimal svcMachMstrPk, Map<String, Object> supplyMdseMap, SVC_SPLY_MACH_ALLWTMsg tmsg) {

        BigDecimal dsContrDtlPk = (BigDecimal) supplyMdseMap.get("dsContrDtlPk");

        // START 2017/09/08 T.Kanasaka [QC#15134,MOD]
        List<SvcPhysMtrReadInfoBean> firstMtrReadInfoListBW = null;
        List<SvcPhysMtrReadInfoBean> lastMtrReadInfoListBW = null;

        // 3.2.2.1. Get [StartMeterCount of BW ]
        BigDecimal startMtrCntBW = BigDecimal.ZERO;
        String startReadMtrDtBW = null;
        Map<String, Object> startMtrBW = getStartMtrInfo(gcc, dsContrDtlPk, MDL_MTR_TP_BW);
        if (startMtrBW != null) {
            startMtrCntBW = (BigDecimal) startMtrBW.get("READ_MTR_CNT");
            startReadMtrDtBW = (String) startMtrBW.get("MTR_READ_DT");

            firstMtrReadInfoListBW = getMtrReadInfoList(gcc, (BigDecimal) startMtrBW.get("SVC_PHYS_MTR_READ_GRP_SQ"), MDL_MTR_TP_BW);
        }

        // 3.2.2.2. Get [BillingMeterCount of BW]
        BigDecimal bllgReadMtrCntBW = BigDecimal.ZERO;
        String bllgMtrReadDtBW = procDt;
        List<Map<String, Object>> bllgMtrBW = getBllgMtrList(gcc, dsContrDtlPk, MDL_MTR_TP_BW);
        if (bllgMtrBW != null && bllgMtrBW.size() > 0) {
            bllgReadMtrCntBW = (BigDecimal) bllgMtrBW.get(0).get("READ_MTR_CNT");
            bllgMtrReadDtBW = (String) bllgMtrBW.get(0).get("MTR_READ_DT");

            lastMtrReadInfoListBW = getMtrReadInfoList(gcc, (BigDecimal) bllgMtrBW.get(0).get("SVC_PHYS_MTR_READ_GRP_SQ"), MDL_MTR_TP_BW);
        } else {
            bllgReadMtrCntBW = startMtrCntBW;
        }

        BigDecimal rollOverExchCntBW = BigDecimal.ZERO;
        if (firstMtrReadInfoListBW != null && lastMtrReadInfoListBW != null) {
            for (SvcPhysMtrReadInfoBean firstMtrReadInfoBW : firstMtrReadInfoListBW) {
                for (SvcPhysMtrReadInfoBean lastMtrReadInfoBW : lastMtrReadInfoListBW) {
                    if (firstMtrReadInfoBW.getSvcPhysMtrPk().compareTo(lastMtrReadInfoBW.getSvcPhysMtrPk()) == 0) {
                        NSXC003001MtrCntTwoPntEst nsxc003001MtrEst = new NSXC003001MtrCntTwoPntEst();
                        BigDecimal rollOverExchCnt = nsxc003001MtrEst.getRollOverExchCnt(gcc, firstMtrReadInfoBW, lastMtrReadInfoBW);
                        rollOverExchCntBW = rollOverExchCntBW.add(rollOverExchCnt);
                        break;
                    }
                }
            }
        }

        // 3.2.2.3. Calculate [TotalMeterCount of BW]
//        BigDecimal totalMtrCntBW = bllgReadMtrCntBW.subtract(startMtrCntBW);
        BigDecimal totalMtrCntBW = bllgReadMtrCntBW.subtract(startMtrCntBW).add(rollOverExchCntBW);

        List<SvcPhysMtrReadInfoBean> firstMtrReadInfoListCL = null;
        List<SvcPhysMtrReadInfoBean> lastMtrReadInfoListCL = null;

        // 3.2.3.1. Get [StartMeterCount of Color ]
        BigDecimal startMtrCntCL = BigDecimal.ZERO;
        String startReadMtrDtCL = null;
        Map<String, Object> startMtrCL = getStartMtrInfo(gcc, dsContrDtlPk, MDL_MTR_TP_CL);
        if (startMtrCL != null) {
            startMtrCntCL = (BigDecimal) startMtrCL.get("READ_MTR_CNT");
            startReadMtrDtCL = (String) startMtrCL.get("MTR_READ_DT");

            firstMtrReadInfoListCL = getMtrReadInfoList(gcc, (BigDecimal) startMtrCL.get("SVC_PHYS_MTR_READ_GRP_SQ"), MDL_MTR_TP_CL);
        }

        // 3.2.3.2. Get [BillingMeterCount of Color]
        BigDecimal bllgReadMtrCntCL = BigDecimal.ZERO;
        List<Map<String, Object>> bllgMtrCL = getBllgMtrList(gcc, dsContrDtlPk, MDL_MTR_TP_CL);
        if (bllgMtrCL.size() > 0) {
            bllgReadMtrCntCL = (BigDecimal) bllgMtrCL.get(0).get("READ_MTR_CNT");

            lastMtrReadInfoListCL = getMtrReadInfoList(gcc, (BigDecimal) bllgMtrCL.get(0).get("SVC_PHYS_MTR_READ_GRP_SQ"), MDL_MTR_TP_CL);
        } else {
            bllgReadMtrCntCL = startMtrCntCL;
        }

        BigDecimal rollOverExchCntCL = BigDecimal.ZERO;
        if (firstMtrReadInfoListCL != null && lastMtrReadInfoListCL != null) {
            for (SvcPhysMtrReadInfoBean firstMtrReadInfoCL : firstMtrReadInfoListCL) {
                for (SvcPhysMtrReadInfoBean lastMtrReadInfoCL : lastMtrReadInfoListCL) {
                    if (firstMtrReadInfoCL.getSvcPhysMtrPk().compareTo(lastMtrReadInfoCL.getSvcPhysMtrPk()) == 0) {
                        NSXC003001MtrCntTwoPntEst nsxc003001MtrEst = new NSXC003001MtrCntTwoPntEst();
                        BigDecimal rollOverExchCnt = nsxc003001MtrEst.getRollOverExchCnt(gcc, firstMtrReadInfoCL, lastMtrReadInfoCL);
                        rollOverExchCntCL = rollOverExchCntCL.add(rollOverExchCnt);
                        break;
                    }
                }
            }
        }

        // 3.2.2.3. Calculate [TotalMeterCount of BW]
//        BigDecimal totalMtrCntCL = bllgReadMtrCntCL.subtract(startMtrCntCL);
        BigDecimal totalMtrCntCL = bllgReadMtrCntCL.subtract(startMtrCntCL).add(rollOverExchCntCL);
        // END 2017/09/08 T.Kanasaka [QC#15134,MOD]

        // Del Start 2017/08/04 QC#18314
//        if (totalMtrCntBW.compareTo(BigDecimal.ZERO) == 0 && totalMtrCntCL.compareTo(BigDecimal.ZERO) == 0) {
//            return false;
//        }
        // // Del End 2017/08/04 QC#18314

        // 3.2.4 Calculate [bwAllwQty]
        BigDecimal bwAllwQty = calcQty(CALC_TP_ALLW, MDL_MTR_TP_BW, totalMtrCntBW, totalMtrCntCL, supplyMdseMap);

        // 3.2.5. Calculate [ColorAllwQty]
        BigDecimal colorAllwQty = calcQty(CALC_TP_ALLW, MDL_MTR_TP_CL, BigDecimal.ZERO, totalMtrCntCL, supplyMdseMap);

        // 3.2.6. Calculate [fromLastBillDtDaysAot]
        Integer fromLastBillDtDaysAot = ZYPDateUtil.getDiffDays(procDt, bllgMtrReadDtBW);
        supplyMdseMap.put("fromLastBillDtDaysAot", new BigDecimal(fromLastBillDtDaysAot));

        // 3.2.7. Calculate [bwAdcvCnt]
//        BigDecimal bwAdcvCnt = calcAdcvCnt(bllgMtrBW, startMtrCntBW, startReadMtrDtBW, COEFFICIENT_BW);
        BigDecimal bwAdcvCnt = calcAdcvCnt(bllgMtrBW, startMtrCntBW, startReadMtrDtBW, COEFFICIENT_BW, gcc, firstMtrReadInfoListBW, MDL_MTR_TP_BW);

        // 3.2.8. Calculate [colorAdcvCnt]
//        BigDecimal colorAdcvCnt = calcAdcvCnt(bllgMtrCL, startMtrCntCL, startReadMtrDtCL, COEFFICIENT_CL);
        BigDecimal colorAdcvCnt = calcAdcvCnt(bllgMtrCL, startMtrCntCL, startReadMtrDtCL, COEFFICIENT_CL, gcc, firstMtrReadInfoListCL, MDL_MTR_TP_CL);

        // 3.2.9. Calculate [bwPrrtQty]
        BigDecimal bwPrrtQty = calcQty(CALC_TP_PRRT, MDL_MTR_TP_BW, bwAdcvCnt, colorAdcvCnt, supplyMdseMap);

        // 3.2.10. Calculate [colorPrrtQty]
        BigDecimal colorPrrtQty = calcQty(CALC_TP_PRRT, MDL_MTR_TP_CL, BigDecimal.ZERO, colorAdcvCnt, supplyMdseMap);

        // Others
        BigDecimal bwCustInvtyQty = (BigDecimal) supplyMdseMap.get("custStkQty_" + IMG_SPLY_COLOR_TP.BLACK);
        BigDecimal colorCustInvtyQty = (BigDecimal) supplyMdseMap.get("custStkQty_" + IMG_SPLY_COLOR_TP.YELLOW);
        colorCustInvtyQty = colorCustInvtyQty.add((BigDecimal) supplyMdseMap.get("custStkQty_" + IMG_SPLY_COLOR_TP.MAGENTA));
        colorCustInvtyQty = colorCustInvtyQty.add((BigDecimal) supplyMdseMap.get("custStkQty_" + IMG_SPLY_COLOR_TP.CYAN));

        BigDecimal bwTotCostAmt = (bwAllwQty.add(bwPrrtQty)).multiply((BigDecimal) supplyMdseMap.get("thisMthTotStdCostAmt_" + IMG_SPLY_COLOR_TP.BLACK));
        BigDecimal colorTotCostAmt = (colorAllwQty.add(colorPrrtQty)).multiply((BigDecimal) supplyMdseMap.get("thisMthTotStdCostAmt_" + IMG_SPLY_COLOR_TP.YELLOW));
        colorTotCostAmt = colorTotCostAmt.add((colorAllwQty.add(colorPrrtQty)).multiply((BigDecimal) supplyMdseMap.get("thisMthTotStdCostAmt_" + IMG_SPLY_COLOR_TP.MAGENTA)));
        colorTotCostAmt = colorTotCostAmt.add((colorAllwQty.add(colorPrrtQty)).multiply((BigDecimal) supplyMdseMap.get("thisMthTotStdCostAmt_" + IMG_SPLY_COLOR_TP.CYAN)));

        BigDecimal bwAvgMlyCopyCnt = bwAdcvCnt.multiply(AVG_MLY_COPY_CNT_COF).setScale(0, BigDecimal.ROUND_UP);
        BigDecimal colorAvgMlyCopyCnt = colorAdcvCnt.multiply(AVG_MLY_COPY_CNT_COF).setScale(0, BigDecimal.ROUND_UP);

        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, gcc);
        ZYPEZDItemValueSetter.setValue(tmsg.svcSplyMachAllwPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_SPLY_MACH_ALLW_SQ));
        ZYPEZDItemValueSetter.setValue(tmsg.frzFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsg.shipToCustAcctCd, (String) supplyMdseMap.get("sellToCustCd"));
        ZYPEZDItemValueSetter.setValue(tmsg.dsContrPk, (BigDecimal) supplyMdseMap.get("dsContrPk"));
        ZYPEZDItemValueSetter.setValue(tmsg.svcPgmMdseCd, (String) supplyMdseMap.get("svcPgmMdseCd"));
        ZYPEZDItemValueSetter.setValue(tmsg.svcContrBrCd, (String) supplyMdseMap.get("svcContrBrCd"));
        ZYPEZDItemValueSetter.setValue(tmsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(tmsg.cratTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
        ZYPEZDItemValueSetter.setValue(tmsg.cratPsnCd, getCratPsnCd());
        ZYPEZDItemValueSetter.setValue(tmsg.procDt, procDt);
        ZYPEZDItemValueSetter.setValue(tmsg.bwAllwQty, bwAllwQty);
        ZYPEZDItemValueSetter.setValue(tmsg.bwCustInvtyQty, bwCustInvtyQty);
        ZYPEZDItemValueSetter.setValue(tmsg.bwPrrtQty, bwPrrtQty);
        ZYPEZDItemValueSetter.setValue(tmsg.colorAllwQty, colorAllwQty);
        ZYPEZDItemValueSetter.setValue(tmsg.colorCustInvtyQty, colorCustInvtyQty);
        ZYPEZDItemValueSetter.setValue(tmsg.colorPrrtQty, colorPrrtQty);
        ZYPEZDItemValueSetter.setValue(tmsg.bwTotCostAmt, bwTotCostAmt);
        ZYPEZDItemValueSetter.setValue(tmsg.colorTotCostAmt, colorTotCostAmt);
        ZYPEZDItemValueSetter.setValue(tmsg.bwAdcvCnt, bwAdcvCnt);
        ZYPEZDItemValueSetter.setValue(tmsg.colorAdcvCnt, colorAdcvCnt);
        ZYPEZDItemValueSetter.setValue(tmsg.bwAvgMlyCopyCnt, bwAvgMlyCopyCnt);
        ZYPEZDItemValueSetter.setValue(tmsg.colorAvgMlyCopyCnt, colorAvgMlyCopyCnt);
        ZYPEZDItemValueSetter.setValue(tmsg.fromLastBillDtDaysAot, new BigDecimal(fromLastBillDtDaysAot));
        ZYPEZDItemValueSetter.setValue(tmsg.mtrBllgCycleCd, (String) supplyMdseMap.get("mtrBllgCycleCd"));

        return true;
    }

    private Map<String, Object> getStartMtrInfo(String gcc, BigDecimal dsContrDtlPk, String mtrTp) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("dsContrDtlPk", dsContrDtlPk);
        if (mtrTp.equals(MDL_MTR_TP_BW)) {
            param.put("bwMtrflgY", ZYPConstant.FLG_ON_Y);
        } else {
            param.put("colorMtrflgY", ZYPConstant.FLG_ON_Y);
        }
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getStartMtrInfo", param);
    }

    private List<Map<String, Object>> getBllgMtrList(String gcc, BigDecimal dsContrDtlPk, String mtrTp) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("flgY", ZYPConstant.FLG_ON_Y);
        if (mtrTp.equals(MDL_MTR_TP_BW)) {
            param.put("bwMtrflgY", ZYPConstant.FLG_ON_Y);
        } else {
            param.put("colorMtrflgY", ZYPConstant.FLG_ON_Y);
        }
        param.put("getNum", GET_BLLG_SCHD_NUM);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getBllgMtrList", param);
    }

    // START 2017/09/08 T.Kanasaka [QC#15134,ADD]
    private List<SvcPhysMtrReadInfoBean> getMtrReadInfoList(String gcc, BigDecimal svcPhysMtrReadGrpSq, String mtrTp) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("svcPhysMtrReadGrpSq", svcPhysMtrReadGrpSq);
        if (mtrTp.equals(MDL_MTR_TP_BW)) {
            param.put("bwMtrflgY", ZYPConstant.FLG_ON_Y);
        } else {
            param.put("colorMtrflgY", ZYPConstant.FLG_ON_Y);
        }
        List<Map<String, Object>> mtrReadInfoList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getMtrReadInfoList", param);

        List<SvcPhysMtrReadInfoBean> mtrReadBeanList = new ArrayList<SvcPhysMtrReadInfoBean>();
        for (Map<String, Object> mtrReadInfo : mtrReadInfoList) {
            SvcPhysMtrReadInfoBean mtrReadBean = new SvcPhysMtrReadInfoBean();
            mtrReadBean.setSvcPhysMtrReadPk((BigDecimal) mtrReadInfo.get("SVC_PHYS_MTR_READ_PK"));
            mtrReadBean.setSvcPhysMtrPk((BigDecimal) mtrReadInfo.get("SVC_PHYS_MTR_PK"));
            mtrReadBean.setMtrReadDt((String) mtrReadInfo.get("MTR_READ_DT"));
            mtrReadBean.setReadMtrCnt((BigDecimal) mtrReadInfo.get("READ_MTR_CNT"));
            mtrReadBeanList.add(mtrReadBean);
        }
        return mtrReadBeanList;
    }
    // END 2017/09/08 T.Kanasaka [QC#15134,ADD]

    private BigDecimal calcQty(String calcTp, String colorTp, BigDecimal mtrCntBW, BigDecimal mtrCntCL, Map<String, Object> supplyMdseMap) {

        BigDecimal returnQty = BigDecimal.ZERO;

        BigDecimal yield = null;
        BigDecimal tolerance = null;
        BigDecimal coefficient = null;
        BigDecimal custStkQty = null;

        if (colorTp.equals(MDL_MTR_TP_BW)) {
            yield = (BigDecimal) supplyMdseMap.get("stdYieldCnt_" + IMG_SPLY_COLOR_TP.BLACK);
            tolerance = (BigDecimal) supplyMdseMap.get("splyTolPct_" + IMG_SPLY_COLOR_TP.BLACK);
            coefficient = COEFFICIENT_BW;
            custStkQty = (BigDecimal) supplyMdseMap.get("custStkQty_" + IMG_SPLY_COLOR_TP.BLACK);

        } else if (colorTp.equals(MDL_MTR_TP_CL)) {
            yield = (BigDecimal) supplyMdseMap.get("stdYieldCnt_" + IMG_SPLY_COLOR_TP.YELLOW);
            yield = yield.add((BigDecimal) supplyMdseMap.get("stdYieldCnt_" + IMG_SPLY_COLOR_TP.MAGENTA));
            yield = yield.add((BigDecimal) supplyMdseMap.get("stdYieldCnt_" + IMG_SPLY_COLOR_TP.CYAN));
            if (yield.compareTo(BigDecimal.ZERO) == 0) {
                return BigDecimal.ZERO;
            }
            tolerance = (BigDecimal) supplyMdseMap.get("splyTolPct_" + IMG_SPLY_COLOR_TP.YELLOW);
            if (tolerance.compareTo(BigDecimal.ZERO) == 0) {
                tolerance = (BigDecimal) supplyMdseMap.get("splyTolPct_" + IMG_SPLY_COLOR_TP.MAGENTA);
            }
            if (yield.compareTo(BigDecimal.ZERO) == 0) {
                tolerance = (BigDecimal) supplyMdseMap.get("splyTolPct_" + IMG_SPLY_COLOR_TP.CYAN);
            }
            coefficient = COEFFICIENT_CL;
            custStkQty = (BigDecimal) supplyMdseMap.get("custStkQty_" + IMG_SPLY_COLOR_TP.YELLOW);
            custStkQty = custStkQty.add((BigDecimal) supplyMdseMap.get("custStkQty_" + IMG_SPLY_COLOR_TP.MAGENTA));
            custStkQty = custStkQty.add((BigDecimal) supplyMdseMap.get("custStkQty_" + IMG_SPLY_COLOR_TP.CYAN));
        }

        if (calcTp.equals(CALC_TP_ALLW)) {
            returnQty = calcAllwQty(mtrCntBW, mtrCntCL, yield, tolerance, coefficient, custStkQty);
        } else if (calcTp.equals(CALC_TP_PRRT)) {
            returnQty = calcPrrtQty(mtrCntBW, mtrCntCL, yield, tolerance, coefficient, (BigDecimal) supplyMdseMap.get("fromLastBillDtDaysAot"));
        }

        return returnQty;
    }

    private BigDecimal calcAllwQty(BigDecimal readMtrCntBW, BigDecimal readMtrCntCL, BigDecimal yield, BigDecimal tolerance, BigDecimal coefficient, BigDecimal custStkQty) {

        BigDecimal returnQty = BigDecimal.ZERO;
        BigDecimal wkYield = BigDecimal.ZERO;

        returnQty = readMtrCntBW.add(readMtrCntCL);
        wkYield = yield.subtract(yield.multiply(tolerance.divide(ONE_HUNDRED, 2, BigDecimal.ROUND_UP)));
        if (wkYield.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        returnQty = returnQty.divide(wkYield, 0 , BigDecimal.ROUND_UP);
        returnQty = returnQty.multiply(coefficient);
        returnQty = returnQty.add(custStkQty);

        return returnQty;
    }

    private BigDecimal calcPrrtQty(BigDecimal readMtrCntBW, BigDecimal readMtrCntCL, BigDecimal yield, BigDecimal tolerance, BigDecimal coefficient, BigDecimal aot) {

        BigDecimal returnQty = BigDecimal.ZERO;
        BigDecimal wkYield = BigDecimal.ZERO;

        returnQty = readMtrCntBW.add(readMtrCntCL);
        returnQty = returnQty.multiply(aot);
        wkYield = yield.subtract(yield.multiply(tolerance.divide(ONE_HUNDRED, 2, BigDecimal.ROUND_UP)));
        if (wkYield.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        returnQty = returnQty.divide(wkYield, 0, BigDecimal.ROUND_UP);
        returnQty = returnQty.multiply(coefficient);

        return returnQty;
    }

    // START 2017/09/08 T.Kanasaka [QC#15134,MOD]
//    private BigDecimal calcAdcvCnt(List<Map<String, Object>> bllgMtrList, BigDecimal startMtrReadCnt, String startReadMtrDt, BigDecimal coefficient) {
    private BigDecimal calcAdcvCnt(List<Map<String, Object>> bllgMtrList, BigDecimal startMtrReadCnt, String startReadMtrDt, BigDecimal coefficient, String gcc, List<SvcPhysMtrReadInfoBean> startMtrReadInfoList, String mtrTp) {

        BigDecimal adcvCnt = BigDecimal.ZERO;
        int bllgCnt = bllgMtrList.size();

        if (bllgCnt == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal[] avrMtrCnt = new BigDecimal[bllgCnt];
        BigDecimal[] mtrCnt = new BigDecimal[bllgCnt + 1];
        String[] thruDt = new String[bllgCnt + 1];
        BigDecimal[] grpSq = new BigDecimal[bllgCnt + 1];
        int i;
        // START 2018/12/20 S.Kitamura [QC#25683,ADD]
        int validAdcvCnt = 0;
        // END 2018/12/20 S.Kitamura [QC#25683,ADD]

        for (i = 0; i < bllgCnt; i++) {
            mtrCnt[i] = (BigDecimal) bllgMtrList.get(i).get("READ_MTR_CNT");
            thruDt[i] = (String) bllgMtrList.get(i).get("BLLG_SCHD_THRU_DT");
            grpSq[i] = (BigDecimal) bllgMtrList.get(i).get("SVC_PHYS_MTR_READ_GRP_SQ");

            if (i > 0) {
                // START 2018/12/20 S.Kitamura [QC#25683,ADD]
                if (!ZYPCommonFunc.hasValue(mtrCnt[i]) || !ZYPCommonFunc.hasValue(thruDt[i]) || !ZYPCommonFunc.hasValue(grpSq[i]) ||
                        !ZYPCommonFunc.hasValue(mtrCnt[i - 1]) || !ZYPCommonFunc.hasValue(thruDt[i - 1]) || !ZYPCommonFunc.hasValue(grpSq[i - 1])) {
                    continue;
                }
                // END 2018/12/20 S.Kitamura [QC#25683,ADD]
                List<SvcPhysMtrReadInfoBean> firstMtrReadInfoList = getMtrReadInfoList(gcc, grpSq[i], mtrTp);
                List<SvcPhysMtrReadInfoBean> lastMtrReadInfoList = getMtrReadInfoList(gcc, grpSq[i - 1], mtrTp);
                BigDecimal sumRollOverExchCnt = BigDecimal.ZERO;
                if (firstMtrReadInfoList != null && lastMtrReadInfoList != null) {
                    for (SvcPhysMtrReadInfoBean firstMtrReadInfo : firstMtrReadInfoList) {
                        for (SvcPhysMtrReadInfoBean lastMtrReadInfo : lastMtrReadInfoList) {
                            if (firstMtrReadInfo.getSvcPhysMtrPk().compareTo(lastMtrReadInfo.getSvcPhysMtrPk()) == 0) {
                                NSXC003001MtrCntTwoPntEst nsxc003001MtrEst = new NSXC003001MtrCntTwoPntEst();
                                BigDecimal rollOverExchCnt = nsxc003001MtrEst.getRollOverExchCnt(gcc, firstMtrReadInfo, lastMtrReadInfo);
                                sumRollOverExchCnt = sumRollOverExchCnt.add(rollOverExchCnt);
                                break;
                            }
                        }
                    }
                }

//                avrMtrCnt[i - 1] = mtrCnt[i - 1].subtract(mtrCnt[i]);
                avrMtrCnt[i - 1] = mtrCnt[i - 1].subtract(mtrCnt[i]).add(sumRollOverExchCnt);
                BigDecimal diffday = new BigDecimal(ZYPDateUtil.getDiffDays(thruDt[i - 1], thruDt[i]));
                // START 2017/05/25 K.Kojima [QC#18700,ADD]
                if (diffday.compareTo(BigDecimal.ZERO) == 0) {
                    diffday = BigDecimal.ONE;
                }
                // END 2017/05/25 K.Kojima [QC#18700,ADD]
                avrMtrCnt[i - 1] = avrMtrCnt[i - 1].divide(diffday, 0, BigDecimal.ROUND_UP);
                adcvCnt = adcvCnt.add(avrMtrCnt[i - 1]);
                // START 2018/12/20 S.Kitamura [QC#25683,ADD]
                validAdcvCnt++;
                // END 2018/12/20 S.Kitamura [QC#25683,ADD]
            }
        }
        if (bllgCnt < GET_BLLG_SCHD_NUM) {
            // START 2018/12/20 S.Kitamura [QC#25683,ADD]
            if (ZYPCommonFunc.hasValue(mtrCnt[i - 1]) && ZYPCommonFunc.hasValue(thruDt[i - 1]) && ZYPCommonFunc.hasValue(grpSq[i - 1]) &&
                    ZYPCommonFunc.hasValue(startMtrReadCnt) && ZYPCommonFunc.hasValue(startReadMtrDt)) {
                // END 2018/12/20 S.Kitamura [QC#25683,ADD]
                List<SvcPhysMtrReadInfoBean> lastMtrReadInfoList = getMtrReadInfoList(gcc, grpSq[i - 1], mtrTp);
                BigDecimal sumRollOverExchCnt = BigDecimal.ZERO;
                if (startMtrReadInfoList != null && lastMtrReadInfoList != null) {
                    for (SvcPhysMtrReadInfoBean startMtrReadInfo : startMtrReadInfoList) {
                        for (SvcPhysMtrReadInfoBean lastMtrReadInfo : lastMtrReadInfoList) {
                            if (startMtrReadInfo.getSvcPhysMtrPk().compareTo(lastMtrReadInfo.getSvcPhysMtrPk()) == 0) {
                                NSXC003001MtrCntTwoPntEst nsxc003001MtrEst = new NSXC003001MtrCntTwoPntEst();
                                BigDecimal rollOverExchCnt = nsxc003001MtrEst.getRollOverExchCnt(gcc, startMtrReadInfo, lastMtrReadInfo);
                                sumRollOverExchCnt = sumRollOverExchCnt.add(rollOverExchCnt);
                                break;
                            }
                        }
                    }
                }
    //            avrMtrCnt[i - 1] = mtrCnt[i - 1].subtract(startMtrReadCnt);
                avrMtrCnt[i - 1] = mtrCnt[i - 1].subtract(startMtrReadCnt).add(sumRollOverExchCnt);
                BigDecimal diffday = new BigDecimal(ZYPDateUtil.getDiffDays(thruDt[i - 1], startReadMtrDt));
                // START 2017/05/25 K.Kojima [QC#18700,ADD]
                if (diffday.compareTo(BigDecimal.ZERO) == 0) {
                    diffday = BigDecimal.ONE;
                }
                // END 2017/05/25 K.Kojima [QC#18700,ADD]
                avrMtrCnt[i - 1] = avrMtrCnt[i - 1].divide(diffday, 0, BigDecimal.ROUND_UP);
                adcvCnt = adcvCnt.add(avrMtrCnt[i - 1]);
                // START 2018/12/20 S.Kitamura [QC#25683,ADD]
                validAdcvCnt++;
                // END 2018/12/20 S.Kitamura [QC#25683,ADD]
            }
        }

        // START 2018/12/20 S.Kitamura [QC#25683,MOD]
        //adcvCnt = adcvCnt.divide((new BigDecimal(bllgCnt - 1)), 0, BigDecimal.ROUND_UP);
        if (validAdcvCnt == 0) {
            return BigDecimal.ZERO;
        }
        adcvCnt = adcvCnt.divide((new BigDecimal(validAdcvCnt)), 0, BigDecimal.ROUND_UP);
        // END 2018/12/20 S.Kitamura [QC#25683,MOD]
        adcvCnt = adcvCnt.multiply(coefficient);

        return adcvCnt;
    }
    // END 2017/09/08 T.Kanasaka [QC#15134,MOD]

    private void insertSvcSplyMachAllw(SVC_SPLY_MACH_ALLWTMsg tmsg, S21ApiMessageMap msgMap) {
        S21ApiTBLAccessor.insert(tmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSZM0398E, new String[] {"SVC_SPLY_MACH_ALLW" });
        }
    }

    private void createUsedData(String gcc, String procDt, BigDecimal svcMachMstrPk, Map<String, Object> supplyMdseMap, S21ApiMessageMap msgMap) {

        BigDecimal dsContrPk = (BigDecimal) supplyMdseMap.get("dsContrPk");

        // START 2018/09/07 K.Kojima [QC#28130,ADD]
        // Delete Same Process Date Data
        logicalRemoveSvcSplyMachUsed(gcc, procDt, dsContrPk, svcMachMstrPk);
        // END 2018/09/07 K.Kojima [QC#28130,ADD]

        // 4.1 UpdateSvcSplyMachUsed FRZ_FLG
        updateSvcSplyMachUsed(gcc, dsContrPk, svcMachMstrPk, msgMap);

        // 4.2 setInsertSvcSplyMachAllwParam
        SVC_SPLY_MACH_USEDTMsg tmsg = setInsertSvcSplyMachUsedParam(gcc, procDt, svcMachMstrPk, supplyMdseMap);

        // 4.3 insertSvcSplyMachAllw
        insertSvcSplyMachUsed(tmsg, msgMap);
    }

    // START 2018/09/07 K.Kojima [QC#28130,ADD]
    private void logicalRemoveSvcSplyMachUsed(String gcc, String procDt, BigDecimal dsContrPk, BigDecimal svcMachMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("procDt", procDt);
        param.put("dsContrPk", dsContrPk);
        param.put("svcMachMstrPk", svcMachMstrPk);
        List<BigDecimal> svcSplyMachUsedPkList = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getLogicalRemoveSvcSplyMachUsed", param);

        for (BigDecimal svcSplyMachUsedPk : svcSplyMachUsedPkList) {
            SVC_SPLY_MACH_USEDTMsg tMsg = new SVC_SPLY_MACH_USEDTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, gcc);
            ZYPEZDItemValueSetter.setValue(tMsg.svcSplyMachUsedPk, svcSplyMachUsedPk);
            S21ApiTBLAccessor.logicalRemove(tMsg);
        }
    }

    // END 2018/09/07 K.Kojima [QC#28130,ADD]

    private void updateSvcSplyMachUsed(String gcc, BigDecimal dsContrPk, BigDecimal svcMachMstrPk, S21ApiMessageMap msgMap) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("dsContrPk", dsContrPk);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("flgN", ZYPConstant.FLG_OFF_N);
        // START 2018/09/07 K.Kojima [QC#28130,MOD]
        // BigDecimal svcSplyMachUsedPk = (BigDecimal) this.ssmBatchClient.queryObject("getSvcSplyMachUsedPk", param);
        List<BigDecimal> svcSplyMachUsedPkList = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getSvcSplyMachUsedPk", param);
        // END 2018/09/07 K.Kojima [QC#28130,MOD]

        // START 2018/09/07 K.Kojima [QC#28130,MOD]
        // if (svcSplyMachUsedPk != null) {
        for (BigDecimal svcSplyMachUsedPk : svcSplyMachUsedPkList) {
        // END 2018/09/07 K.Kojima [QC#28130,MOD]
            SVC_SPLY_MACH_USEDTMsg tmsg = new SVC_SPLY_MACH_USEDTMsg();
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, gcc);
            ZYPEZDItemValueSetter.setValue(tmsg.svcSplyMachUsedPk, svcSplyMachUsedPk);
            tmsg = (SVC_SPLY_MACH_USEDTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

            ZYPEZDItemValueSetter.setValue(tmsg.frzFlg, ZYPConstant.FLG_ON_Y);
            S21FastTBLAccessor.update(tmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSZM0398E, new String[] {"SVC_SPLY_MACH_USED", svcSplyMachUsedPk.toString()});
            }
        }
    }

    private SVC_SPLY_MACH_USEDTMsg setInsertSvcSplyMachUsedParam(String gcc, String procDt, BigDecimal svcMachMstrPk, Map<String, Object> supplyMdseMap) {

        SVC_SPLY_MACH_USEDTMsg tmsg = new SVC_SPLY_MACH_USEDTMsg();

        BigDecimal dsContrDtlPk = (BigDecimal) supplyMdseMap.get("dsContrDtlPk");

        // 4.2. Get [bwUsedQty]
        BigDecimal bwUsedQty = getUsedQty(gcc, dsContrDtlPk, IMG_SPLY_COLOR_TP.BLACK);
        BigDecimal shipQtyYE = getUsedQty(gcc, dsContrDtlPk, IMG_SPLY_COLOR_TP.YELLOW);
        BigDecimal shipQtyMA = getUsedQty(gcc, dsContrDtlPk, IMG_SPLY_COLOR_TP.MAGENTA);
        BigDecimal shipQtyCY = getUsedQty(gcc, dsContrDtlPk, IMG_SPLY_COLOR_TP.CYAN);
        BigDecimal colorUsedQty = shipQtyYE.add(shipQtyMA).add(shipQtyCY);
        BigDecimal bwUsedCostAmt = bwUsedQty.multiply((BigDecimal) supplyMdseMap.get("thisMthTotStdCostAmt_" + IMG_SPLY_COLOR_TP.BLACK)).setScale(0, BigDecimal.ROUND_UP);
        BigDecimal colorUsedCostAmt = shipQtyYE.multiply((BigDecimal) supplyMdseMap.get("thisMthTotStdCostAmt_" + IMG_SPLY_COLOR_TP.YELLOW)).setScale(0, BigDecimal.ROUND_UP);
        colorUsedCostAmt = colorUsedCostAmt.add(shipQtyMA.multiply((BigDecimal) supplyMdseMap.get("thisMthTotStdCostAmt_" + IMG_SPLY_COLOR_TP.MAGENTA)).setScale(0, BigDecimal.ROUND_UP));
        colorUsedCostAmt = colorUsedCostAmt.add(shipQtyCY.multiply((BigDecimal) supplyMdseMap.get("thisMthTotStdCostAmt_" + IMG_SPLY_COLOR_TP.CYAN)).setScale(0, BigDecimal.ROUND_UP));

        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, gcc);
        ZYPEZDItemValueSetter.setValue(tmsg.svcSplyMachUsedPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_SPLY_MACH_USED_SQ));
        ZYPEZDItemValueSetter.setValue(tmsg.frzFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsg.shipToCustAcctCd, (String) supplyMdseMap.get("sellToCustCd"));
        ZYPEZDItemValueSetter.setValue(tmsg.dsContrPk, (BigDecimal) supplyMdseMap.get("dsContrPk"));
        ZYPEZDItemValueSetter.setValue(tmsg.svcPgmMdseCd, (String) supplyMdseMap.get("svcPgmMdseCd"));
        ZYPEZDItemValueSetter.setValue(tmsg.svcContrBrCd, (String) supplyMdseMap.get("svcContrBrCd"));
        ZYPEZDItemValueSetter.setValue(tmsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(tmsg.cratTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
        ZYPEZDItemValueSetter.setValue(tmsg.cratPsnCd, getCratPsnCd());
        ZYPEZDItemValueSetter.setValue(tmsg.procDt, procDt);
        ZYPEZDItemValueSetter.setValue(tmsg.bwUsedQty, bwUsedQty);
        ZYPEZDItemValueSetter.setValue(tmsg.colorUsedQty, colorUsedQty);
        ZYPEZDItemValueSetter.setValue(tmsg.bwUsedCostAmt, bwUsedCostAmt);
        ZYPEZDItemValueSetter.setValue(tmsg.colorUsedCostAmt, colorUsedCostAmt);

        return tmsg;

    }

    private BigDecimal getUsedQty(String gcc, BigDecimal dsContrDtlPk, String imgSplyColorTpCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("imgSplyColorTpCd", imgSplyColorTpCd);
        return (BigDecimal) this.ssmBatchClient.queryObject("getUsedQty", param);
    }

    private void insertSvcSplyMachUsed(SVC_SPLY_MACH_USEDTMsg tmsg, S21ApiMessageMap msgMap) {
        S21ApiTBLAccessor.insert(tmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSZM0398E, new String[] {"SVC_SPLY_MACH_USED" });
        }
    }

    private void createWatchListData(String gcc, String procDt, ContrMdseInfoBean bean, S21ApiMessageMap msgMap) {

        BigDecimal dsContrPk = bean.getDsContrPk();
        String svcPgmMdseCd = bean.getSvcPgmMdseCd();

        // START 2018/09/07 K.Kojima [QC#28130,ADD]
        // Delete Same Process Date Data
        logicalRemoveSvcSplyAbuseStage(gcc, procDt, dsContrPk, svcPgmMdseCd);
        // END 2018/09/07 K.Kojima [QC#28130,ADD]

        // 5.1. UpdateSvcSplyAbuseStage FRZ_FLG
        SVC_SPLY_ABUSE_STAGETMsg oldRecTmsg = updateSvcSplyAbuseStage(gcc, dsContrPk, svcPgmMdseCd, msgMap);

        // 5.2. setInsertSvcSplyMachAllwParam
        SVC_SPLY_ABUSE_STAGETMsg newRecTmsg = setInsertSvcSplyAbuseStageParam(gcc, procDt, bean, oldRecTmsg);

        // 5.3. insertSvcSplyAbuseStage
        insertSvcSplyAbuseStage(newRecTmsg, msgMap);
    }

    // START 2018/09/07 K.Kojima [QC#28130,ADD]
    private void logicalRemoveSvcSplyAbuseStage(String gcc, String procDt, BigDecimal dsContrPk, String svcPgmMdseCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("procDt", procDt);
        param.put("dsContrPk", dsContrPk);
        param.put("svcPgmMdseCd", svcPgmMdseCd);
        List<BigDecimal> svcSplyAbuseStagePkList = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getLogicalRemoveSvcSplyAbuseStage", param);

        for (BigDecimal svcSplyAbuseStagePk : svcSplyAbuseStagePkList) {
            SVC_SPLY_ABUSE_STAGETMsg tMsg = new SVC_SPLY_ABUSE_STAGETMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, gcc);
            ZYPEZDItemValueSetter.setValue(tMsg.svcSplyAbuseStagePk, svcSplyAbuseStagePk);
            S21ApiTBLAccessor.logicalRemove(tMsg);
        }
    }

    // END 2018/09/07 K.Kojima [QC#28130,ADD]

    private SVC_SPLY_ABUSE_STAGETMsg updateSvcSplyAbuseStage(String gcc, BigDecimal dsContrPk, String svcPgmMdseCd, S21ApiMessageMap msgMap) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("dsContrPk", dsContrPk);
        param.put("svcPgmMdseCd", svcPgmMdseCd);
        param.put("flgN", ZYPConstant.FLG_OFF_N);
        // START 2018/09/07 K.Kojima [QC#28130,MOD]
        // BigDecimal svcSplyAbuseStagePk = (BigDecimal) this.ssmBatchClient.queryObject("getSvcSplyAbuseStagePk", param);
        List<BigDecimal> svcSplyAbuseStagePkList = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getSvcSplyAbuseStagePk", param);
        // END 2018/09/07 K.Kojima [QC#28130,MOD]

        SVC_SPLY_ABUSE_STAGETMsg tmsg = new SVC_SPLY_ABUSE_STAGETMsg();
        // START 2018/09/07 K.Kojima [QC#28130,MOD]
        // if (svcSplyAbuseStagePk != null) {
        for (BigDecimal svcSplyAbuseStagePk : svcSplyAbuseStagePkList) {
        // END 2018/09/07 K.Kojima [QC#28130,MOD]
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, gcc);
            ZYPEZDItemValueSetter.setValue(tmsg.svcSplyAbuseStagePk, svcSplyAbuseStagePk);
            tmsg = (SVC_SPLY_ABUSE_STAGETMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

            ZYPEZDItemValueSetter.setValue(tmsg.frzFlg, ZYPConstant.FLG_ON_Y);
            S21FastTBLAccessor.update(tmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSZM0398E, new String[] {"SVC_SPLY_ABUSE_STAGE", svcSplyAbuseStagePk.toString()});
            }
        }
        return tmsg;
    }

    private SVC_SPLY_ABUSE_STAGETMsg setInsertSvcSplyAbuseStageParam(String gcc, String procDt, ContrMdseInfoBean bean, SVC_SPLY_ABUSE_STAGETMsg oldRecTmsg) {

        SVC_SPLY_ABUSE_STAGETMsg tmsg = new SVC_SPLY_ABUSE_STAGETMsg();

        BigDecimal dsContrPk = bean.getDsContrPk();
        String svcPgmMdseCd = bean.getSvcPgmMdseCd();

        // 5.2.1. Get [ALLW_QTY]
        BigDecimal bwAllwQty = BigDecimal.ZERO;
        BigDecimal colorAllwQty = BigDecimal.ZERO;
        BigDecimal bwPrrtQty = BigDecimal.ZERO;
        BigDecimal colorPrrtQty = BigDecimal.ZERO;
        BigDecimal bwTotCostAmt = BigDecimal.ZERO;
        BigDecimal colorTotCostAmt = BigDecimal.ZERO;
        Map<String, BigDecimal> allwQtySum = getAllwQtySum(gcc, dsContrPk, svcPgmMdseCd);
        if (allwQtySum != null) {
            bwAllwQty = allwQtySum.get("BW_ALLW_QTY");
            colorAllwQty = allwQtySum.get("COLOR_ALLW_QTY");
            bwPrrtQty = allwQtySum.get("BW_PRRT_QTY");
            colorPrrtQty = allwQtySum.get("COLOR_PRRT_QTY");
            bwTotCostAmt = allwQtySum.get("BW_TOT_COST_AMT");
            colorTotCostAmt = allwQtySum.get("COLOR_TOT_COST_AMT");
        }

        // 5.2.2. Get [USED_QTY]
        BigDecimal bwUsedQty = BigDecimal.ZERO;
        BigDecimal colorUsedQty = BigDecimal.ZERO;
        BigDecimal bwUsedCostAmt = BigDecimal.ZERO;
        BigDecimal colorUsedCostAmt = BigDecimal.ZERO;
        Map<String, BigDecimal> usedQtySum = getUsedQtySum(gcc, dsContrPk, svcPgmMdseCd);
        if (usedQtySum != null) {
            bwUsedQty = usedQtySum.get("BW_USED_QTY");
            colorUsedQty = usedQtySum.get("COLOR_USED_QTY");
            bwUsedCostAmt = usedQtySum.get("BW_USED_COST_AMT");
            colorUsedCostAmt = usedQtySum.get("COLOR_USED_COST_AMT");
        }

        // 5.2.3. Calculate [ABUSE_VAR_PCT]
        BigDecimal totalAllwQty = bwAllwQty.add(colorAllwQty);
        BigDecimal totalUsedQty = bwUsedQty.add(colorUsedQty);
        BigDecimal abuseVarPct = BigDecimal.ZERO;
        if (totalAllwQty.compareTo(BigDecimal.ZERO) != 0) {
            // Mod Start 2018/10/01 QC#28478
            // abuseVarPct = totalUsedQty.divide(totalAllwQty, PCT_SCALE, BigDecimal.ROUND_UP);
            abuseVarPct = totalUsedQty.multiply(new BigDecimal(100)).divide(totalAllwQty, PCT_SCALE, BigDecimal.ROUND_UP);
            // Mod End 2018/10/01 QC#28478
            // START 2019/12/12 K.Kitachi [QC#55064, ADD]
            if (abuseVarPct.compareTo(ABUSE_VAR_PCT_MAX_VAL) > 0) {
                abuseVarPct = ABUSE_VAR_PCT_MAX_VAL;
            }
            // END 2019/12/12 K.Kitachi [QC#55064, ADD]
        }

        // 5.2.4. Get [abuseBckt]
        String abuseBcktCd = null;
        String abuseFlg = ZYPConstant.FLG_OFF_N;
        // Mod Start 2018/10/01 QC#28478
        // Map<String, String> abuseBckt = getAbuseBckt(gcc, totalUsedQty);
        Map<String, String> abuseBckt = getAbuseBckt(gcc, abuseVarPct);
        // Mod End 2018/10/01 QC#28478
        if (abuseBckt != null) {
            abuseBcktCd = abuseBckt.get("ABUSE_BCKT_CD");
            abuseFlg = abuseBckt.get("ABUSE_FLG");
        }

        // 5.2.7. Get [noMainUnitCnt]
        BigDecimal noMainUnitCnt = getNoMainUnitCnt(gcc, dsContrPk, svcPgmMdseCd);

        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, gcc);
        ZYPEZDItemValueSetter.setValue(tmsg.svcSplyAbuseStagePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_SPLY_ABUSE_STAGE_SQ));
        ZYPEZDItemValueSetter.setValue(tmsg.frzFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsg.shipToCustAcctCd, bean.getSellToCustCd());
        ZYPEZDItemValueSetter.setValue(tmsg.dsContrPk, dsContrPk);
        ZYPEZDItemValueSetter.setValue(tmsg.svcPgmMdseCd, svcPgmMdseCd);
        ZYPEZDItemValueSetter.setValue(tmsg.svcContrBrCd, bean.getSvcContrBrCd());
        ZYPEZDItemValueSetter.setValue(tmsg.cratTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
        ZYPEZDItemValueSetter.setValue(tmsg.cratPsnCd, getCratPsnCd());
        ZYPEZDItemValueSetter.setValue(tmsg.procDt, procDt);
        ZYPEZDItemValueSetter.setValue(tmsg.abuseFlg, abuseFlg);
        ZYPEZDItemValueSetter.setValue(tmsg.othContrAbuseFlg, setFlg(oldRecTmsg.othContrAbuseFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(tmsg.ovwrtAbuseFlg, setFlg(oldRecTmsg.ovwrtAbuseFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(tmsg.abuseOvwrtRsnCd, oldRecTmsg.abuseOvwrtRsnCd.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.termCondChkFlg, setFlg(oldRecTmsg.termCondChkFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(tmsg.termCondChkDt, oldRecTmsg.termCondChkDt.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.mtrBllgCycleCd, bean.getMtrBllgCycleCd());
        ZYPEZDItemValueSetter.setValue(tmsg.svcRgPk, bean.getSvcRgPk());
        ZYPEZDItemValueSetter.setValue(tmsg.dsContrEdiCd, bean.getDsContrEdiCd());
        ZYPEZDItemValueSetter.setValue(tmsg.noMainUnitCnt, noMainUnitCnt);
        // ZYPEZDItemValueSetter.setValue(tmsg.totRevAmt, totRevAmt);
        ZYPEZDItemValueSetter.setValue(tmsg.totCostAmt, bwTotCostAmt.add(colorTotCostAmt));
        // ZYPEZDItemValueSetter.setValue(tmsg.prftAmt, prftAmt);
        ZYPEZDItemValueSetter.setValue(tmsg.bwPrrtQty, bwPrrtQty);
        ZYPEZDItemValueSetter.setValue(tmsg.colorPrrtQty, colorPrrtQty);
        ZYPEZDItemValueSetter.setValue(tmsg.bwTotCostAmt, bwTotCostAmt);
        ZYPEZDItemValueSetter.setValue(tmsg.colorTotCostAmt, colorTotCostAmt);
        ZYPEZDItemValueSetter.setValue(tmsg.bwUsedQty, bwUsedQty);
        ZYPEZDItemValueSetter.setValue(tmsg.colorUsedQty, colorUsedQty);
        ZYPEZDItemValueSetter.setValue(tmsg.bwUsedCostAmt, bwUsedCostAmt);
        ZYPEZDItemValueSetter.setValue(tmsg.colorUsedCostAmt, colorUsedCostAmt);
        ZYPEZDItemValueSetter.setValue(tmsg.abuseVarPct, abuseVarPct);
        ZYPEZDItemValueSetter.setValue(tmsg.abuseBcktCd, abuseBcktCd);
        ZYPEZDItemValueSetter.setValue(tmsg.svcSplyContrStsCd, SVC_SPLY_CONTR_STS.DRAFT);

        return tmsg;

    }

    private Map<String, BigDecimal> getAllwQtySum(String gcc, BigDecimal dsContrPk, String svcPgmMdseCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("dsContrPk", dsContrPk);
        param.put("svcPgmMdseCd", svcPgmMdseCd);
        param.put("flgN", ZYPConstant.FLG_OFF_N);
        return (Map<String, BigDecimal>) this.ssmBatchClient.queryObject("getAllwQtySum", param);
    }

    private Map<String, BigDecimal> getUsedQtySum(String gcc, BigDecimal dsContrPk, String svcPgmMdseCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("dsContrPk", dsContrPk);
        param.put("svcPgmMdseCd", svcPgmMdseCd);
        param.put("flgN", ZYPConstant.FLG_OFF_N);
        return (Map<String, BigDecimal>) this.ssmBatchClient.queryObject("getUsedQtySum", param);
    }

    private Map<String, String> getAbuseBckt(String gcc, BigDecimal totalUsedQty) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("totalUsedQty", totalUsedQty);
        return (Map<String, String>) this.ssmBatchClient.queryObject("getAbuseBckt", param);
    }

    private BigDecimal getNoMainUnitCnt(String gcc, BigDecimal dsContrPk, String svcPgmMdseCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("dsContrPk", dsContrPk);
        param.put("svcPgmMdseCd", svcPgmMdseCd);
        param.put("flgN", ZYPConstant.FLG_OFF_N);
        return (BigDecimal) this.ssmBatchClient.queryObject("getNoMainUnitCnt", param);
    }

    private Map<String, String> getAllwTopOne(String gcc, BigDecimal dsContrPk, String svcPgmMdseCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", gcc);
        param.put("dsContrPk", dsContrPk);
        param.put("svcPgmMdseCd", svcPgmMdseCd);
        return (Map<String, String>) this.ssmBatchClient.queryObject("getAllwTopOne", param);
    }

    private String setFlg(String flg) {
        String returnFlg = null;
        if (ZYPCommonFunc.hasValue(flg)) {
            returnFlg = flg;
        } else {
            returnFlg = ZYPConstant.FLG_OFF_N;
        }
        return returnFlg;
    }

    private void insertSvcSplyAbuseStage(SVC_SPLY_ABUSE_STAGETMsg tmsg, S21ApiMessageMap msgMap) {
        S21ApiTBLAccessor.insert(tmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSZM0398E, new String[] {"SVC_SPLY_ABUSE_STAGE" });
        }
    }

    private String getCratPsnCd() {

        String cratPsnCd = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
        if (cratPsnCd.length() > 8) {
            cratPsnCd = cratPsnCd.substring(0, 8);
        }

        return cratPsnCd;
    }
}
