/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC052001;

import static com.canon.cusa.s21.api.NSZ.NSZC052001.constant.NSZC052001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_SUB_CONTRTMsg;
import business.db.DS_SUB_CONTRTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.VND_TP_RELNTMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NSZC052001PMsg;
import business.parts.NSZC052001_xxSubContrListPMsg;

import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Sub Contract Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/18/2015   Hitachi         A.Kohinata      Create          N/A
 * 12/01/2016   Hitachi         K.Yamada        Update          QC#14675
 * 02/24/2017   Hitachi         Y.Takeno        Update          QC#14675-1
 * 03/01/2017   Hitachi         T.Mizuki        Update          QC#17575
 * 04/19/2017   Hitachi         K.Kitachi       Update          QC#18097
 * 2017/10/03   Hitachi         U.Kim           Update          QC#21516
 * 2018/01/15   Hitachi         U.Kim           Update          QC#22920
 * 2018/05/16   CITS            M.Naito         Update          QC#25892
 *</pre>
 */
public class NSZC052001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Online Batch Type */
    private ONBATCH_TYPE onBatTp = null;

    /** Contract Info */
    private Map<String, Object> contrInfo = null;

    /**
     * Constructor
     */
    public NSZC052001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsgList List<NSZC052001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC052001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NSZC052001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * execute Sub Contract Update API.
     * @param param NSZC052001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC052001PMsg param, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        this.onBatTp = onBatchType;
        ZYPTableUtil.clear(param.xxMsgIdList);

        if (!checkMandatory(msgMap)) {
            msgMap.flush();
            return;
        }
        if (!checkRelation(msgMap)) {
            msgMap.flush();
            return;
        }
        if (!checkContract(msgMap)) {
            msgMap.flush();
            return;
        }
        if (!checkMaster(msgMap)) {
            msgMap.flush();
            return;
        }
        if (!checkSubContract(msgMap)) {
            msgMap.flush();
            return;
        }
        updateSubContr(msgMap);
        msgMap.flush();
    }

    /**
     * Check Mandatory.
     * @param msgMap S21ApiMessageMap
     * @return true:OK, false:NG
     */
    private boolean checkMandatory(S21ApiMessageMap msgMap) {
        NSZC052001PMsg pMsg = (NSZC052001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NSZM0001E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            msgMap.addXxMsgId(NSZM0002E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.serNum)) {
            msgMap.addXxMsgId(NSZM0012E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.dsContrNum)) {
            msgMap.addXxMsgId(NSZM0271E);
            return false;
        }
        if (pMsg.xxSubContrList.getValidCount() == 0) {
            msgMap.addXxMsgIdWithPrm(NSZM0609E, new String[] {"Sub Contract List" });
            return false;
        }
        for (int i = 0; i < pMsg.xxSubContrList.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(pMsg.xxSubContrList.no(i).vndCd)) {
                msgMap.addXxMsgIdWithPrm(NSZM0609E, new String[] {"Vendor Code" });
                return false;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.xxSubContrList.no(i).techCd)) {
                msgMap.addXxMsgIdWithPrm(NSZM0609E, new String[] {"Tech Code" });
                return false;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.xxSubContrList.no(i).effFromDt)) {
                msgMap.addXxMsgIdWithPrm(NSZM0609E, new String[] {"Effective From Date" });
                return false;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.xxSubContrList.no(i).effThruDt)) {
                msgMap.addXxMsgIdWithPrm(NSZM0609E, new String[] {"Effective Thru Date" });
                return false;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.xxSubContrList.no(i).bllgCycleCd)) {
                msgMap.addXxMsgIdWithPrm(NSZM0609E, new String[] {"Billing Cycle" });
                return false;
            }
        }
        return true;
    }

    /**
     * Check Relation.
     * @param msgMap S21ApiMessageMap
     * @return true:OK, false:NG
     */
    private boolean checkRelation(S21ApiMessageMap msgMap) {
        NSZC052001PMsg pMsg = (NSZC052001PMsg) msgMap.getPmsg();

        for (int i = 0; i < pMsg.xxSubContrList.getValidCount(); i++) {
            if (pMsg.xxSubContrList.no(i).effThruDt.getValue().compareTo(pMsg.xxSubContrList.no(i).effFromDt.getValue()) < 0) {
                msgMap.addXxMsgIdWithPrm(NSZM0620E, new String[] {"Effective From/Thru Date" });
                return false;
            }
            if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxSubContrList.no(i).dlrFleetFlg.getValue()) && !ZYPCommonFunc.hasValue(pMsg.xxSubContrList.no(i).dlrFleetNum)) {
                msgMap.addXxMsgIdWithPrm(NSZM0609E, new String[] {"Dealer Fleet Number" });
                return false;
            }
            if (!ZYPConstant.FLG_ON_Y.equals(pMsg.xxSubContrList.no(i).dlrFleetFlg.getValue()) && ZYPCommonFunc.hasValue(pMsg.xxSubContrList.no(i).dlrFleetNum)) {
                msgMap.addXxMsgIdWithPrm(NSZM0630E, new String[] {"Dealer Fleet Number" });
                return false;
            }
        }
        return true;
    }

    /**
     * Check Contract.
     * @param msgMap S21ApiMessageMap
     * @return true:OK, false:NG
     */
    private boolean checkContract(S21ApiMessageMap msgMap) {
        NSZC052001PMsg pMsg = (NSZC052001PMsg) msgMap.getPmsg();

        this.contrInfo = getContrInfo(pMsg);
        if (this.contrInfo == null || this.contrInfo.size() == 0) {
            msgMap.addXxMsgId(NSZM0434E);
            return false;
        }

        String subContrAvalFlg = (String) this.contrInfo.get("SUB_CONTR_AVAL_FLG");
        String subContrTrmnFlg = (String) this.contrInfo.get("SUB_CONTR_TRMN_FLG");
        if (ZYPConstant.FLG_OFF_N.equals(subContrAvalFlg) && ZYPConstant.FLG_OFF_N.equals(subContrTrmnFlg)) {
            msgMap.addXxMsgId(NSZM0631E);
            return false;
        }
        for (int i = 0; i < pMsg.xxSubContrList.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxSubContrList.no(i).contrTrmnFlg.getValue()) && ZYPConstant.FLG_OFF_N.equals(subContrTrmnFlg)) {
                msgMap.addXxMsgId(NSZM0632E);
                return false;
            }
        }
        return true;
    }

    /**
     * Check Master.
     * @param msgMap S21ApiMessageMap
     * @return true:OK, false:NG
     */
    private boolean checkMaster(S21ApiMessageMap msgMap) {
        NSZC052001PMsg pMsg = (NSZC052001PMsg) msgMap.getPmsg();

        for (int i = 0; i < pMsg.xxSubContrList.getValidCount(); i++) {
            // Check Vendor
            // mod start 2017/03/01 CSA QC#17575
//            if (!existsVnd(pMsg.glblCmpyCd.getValue(), pMsg.xxSubContrList.no(i).vndCd.getValue())) {
            if (!existsVnd(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.xxSubContrList.no(i).vndCd.getValue())) {
            // mod end 2017/03/01 CSA QC#17575
                msgMap.addXxMsgIdWithPrm(NSZM0633E, new String[] {"Vendor Code", "Vendor Master" });
                return false;
            }
            if (!isServiceDealer(pMsg.glblCmpyCd.getValue(), pMsg.xxSubContrList.no(i).vndCd.getValue())) {
                msgMap.addXxMsgIdWithPrm(NSZM0634E, new String[] {"Vendor", "Service Dealer" });
                return false;
            }

            // Check Technician
            // mod start 2017/03/01 CSA QC#17575
//            if (!existsTechMstr(pMsg.glblCmpyCd.getValue(), pMsg.xxSubContrList.no(i).techCd.getValue())) {
            if (!existsTechMstr(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.xxSubContrList.no(i).techCd.getValue())) {
                msgMap.addXxMsgIdWithPrm(NSZM0633E, new String[] {"Technician Code", "Technician Master" });
                return false;
            }
//            if (!isThirdPartyTech(pMsg.glblCmpyCd.getValue(), pMsg.xxSubContrList.no(i).techCd.getValue())) {
            if (!isThirdPartyTech(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.xxSubContrList.no(i).vndCd.getValue(), pMsg.xxSubContrList.no(i).techCd.getValue())) {
            // mod end 2017/03/01 CSA QC#17575
                msgMap.addXxMsgIdWithPrm(NSZM0634E, new String[] {"Technician", "3rd party technician" });
                return false;
            }
        }
        return true;
    }

    /**
     * Check Sub Contract.
     * @param msgMap S21ApiMessageMap
     * @return true:OK, false:NG
     */
    private boolean checkSubContract(S21ApiMessageMap msgMap) {
        NSZC052001PMsg pMsg = (NSZC052001PMsg) msgMap.getPmsg();

        for (int i = 0; i < pMsg.xxSubContrList.getValidCount(); i++) {
            String contrEffFromDt = (String) this.contrInfo.get("CONTR_EFF_FROM_DT");
            if (pMsg.xxSubContrList.no(i).effFromDt.getValue().compareTo(contrEffFromDt) < 0) {
                msgMap.addXxMsgIdWithPrm(NSZM0635E, new String[] {"Effective From Date" });
                return false;
            }
            String contrEffThruDt = (String) this.contrInfo.get("CONTR_EFF_THRU_DT");
            if (pMsg.xxSubContrList.no(i).effThruDt.getValue().compareTo(contrEffThruDt) > 0) {
                msgMap.addXxMsgIdWithPrm(NSZM0635E, new String[] {"Effective Through Date" });
                return false;
            }
        }

        // subContrList Sort(effFromDt, effThruDt)
        List<NSZC052001_xxSubContrListPMsg> sortSubContrList = new ArrayList<NSZC052001_xxSubContrListPMsg>();
        for (int i = 0; i < pMsg.xxSubContrList.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(pMsg.xxSubContrList.no(i).contrTrmnFlg.getValue())) {
                NSZC052001_xxSubContrListPMsg line = (NSZC052001_xxSubContrListPMsg) pMsg.xxSubContrList.no(i).clone();
                sortSubContrList.add(line);
            }
        }
        Collections.sort(sortSubContrList, new Comparator<NSZC052001_xxSubContrListPMsg>() {
            public int compare(NSZC052001_xxSubContrListPMsg line1, NSZC052001_xxSubContrListPMsg line2) {
                int compared;
                compared = line1.effFromDt.getValue().compareTo(line2.effFromDt.getValue());
                if (compared != 0) {
                    return compared;
                }
                compared = line1.effThruDt.getValue().compareTo(line2.effThruDt.getValue());
                if (compared != 0) {
                    return compared;
                }
                return 0;
            }
        });

        for (int i = 0; i < sortSubContrList.size(); i++) {
            if (i > 0) {
                if (sortSubContrList.get(i).effFromDt.getValue().compareTo(sortSubContrList.get(i - 1).effThruDt.getValue()) <= 0) {
                    msgMap.addXxMsgId(NSZM0636E);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Update Sub Contract.
     * @param msgMap S21ApiMessageMap
     */
    private void updateSubContr(S21ApiMessageMap msgMap) {
        NSZC052001PMsg pMsg = (NSZC052001PMsg) msgMap.getPmsg();
        DS_SUB_CONTRTMsg dsSubContrTMsg = new DS_SUB_CONTRTMsg();

        // Logical Remove DS_SUB_CONTR
        DS_SUB_CONTRTMsgArray dsSubContrList = getDsSubContr(pMsg.glblCmpyCd.getValue(), (BigDecimal) contrInfo.get("DS_CONTR_DTL_PK"));
        for (int i = 0; i < dsSubContrList.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.dsSubContrPk, dsSubContrList.no(i).dsSubContrPk);
            S21ApiTBLAccessor.logicalRemove(dsSubContrTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsSubContrTMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSZM0637E, new String[] {"DS_SUB_CONTR" });
                return;
            }
        }

        // Insert DS_SUB_CONTR
        for (int i = 0; i < pMsg.xxSubContrList.getValidCount(); i++) {
            NSZC052001_xxSubContrListPMsg line = (NSZC052001_xxSubContrListPMsg) pMsg.xxSubContrList.no(i);
            BigDecimal dsSubContrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_SUB_CONTR_SQ);
            ZYPEZDItemValueSetter.setValue(line.dsSubContrPk, dsSubContrPk);

            dsSubContrTMsg = new DS_SUB_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.dsSubContrPk, dsSubContrPk);
            ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.dsContrDtlPk, (BigDecimal) this.contrInfo.get("DS_CONTR_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.vndCd, line.vndCd);
            ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.techTocCd, line.techCd);
            ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.dsAcctNum, (String) this.contrInfo.get("DS_ACCT_NUM"));
            if (ZYPCommonFunc.hasValue(line.contrTrmnFlg)) {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.contrTrmnFlg, line.contrTrmnFlg);
            } else {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.contrTrmnFlg, ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.effFromDt, line.effFromDt);
            ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.effThruDt, line.effThruDt);
            if (ZYPCommonFunc.hasValue(line.basePrcDealAmt)) {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.basePrcDealAmt, line.basePrcDealAmt);
            } else {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.basePrcDealAmt, BigDecimal.ZERO);
            }
            if (ZYPCommonFunc.hasValue(line.adminPrcDealAmt)) {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.adminPrcDealAmt, line.adminPrcDealAmt);
            } else {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.adminPrcDealAmt, BigDecimal.ZERO);
            }
            if (ZYPCommonFunc.hasValue(line.prepdMaintFlg)) {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.prepdMaintFlg, line.prepdMaintFlg);
            } else {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.prepdMaintFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPCommonFunc.hasValue(line.bwMtrAlwncCnt)) {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.bwMtrAlwncCnt, line.bwMtrAlwncCnt);
            } else {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.bwMtrAlwncCnt, BigDecimal.ZERO);
            }
            if (ZYPCommonFunc.hasValue(line.colorMtrAlwncCnt)) {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.colorMtrAlwncCnt, line.colorMtrAlwncCnt);
            } else {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.colorMtrAlwncCnt, BigDecimal.ZERO);
            }
            if (ZYPCommonFunc.hasValue(line.bwMtrPrcAmtRate)) {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.bwMtrPrcAmtRate, line.bwMtrPrcAmtRate);
            } else {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.bwMtrPrcAmtRate, BigDecimal.ZERO);
            }
            if (ZYPCommonFunc.hasValue(line.colorMtrPrcAmtRate)) {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.colorMtrPrcAmtRate, line.colorMtrPrcAmtRate);
            } else {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.colorMtrPrcAmtRate, BigDecimal.ZERO);
            }
            if (ZYPCommonFunc.hasValue(line.splyInclFlg)) {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.splyInclFlg, line.splyInclFlg);
            } else {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.splyInclFlg, ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.bllgCycleCd, line.bllgCycleCd);
            if (ZYPCommonFunc.hasValue(line.dlrFleetFlg)) {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.dlrFleetFlg, line.dlrFleetFlg);
            } else {
                ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.dlrFleetFlg, ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(dsSubContrTMsg.dlrFleetNum, line.dlrFleetNum);

            EZDTBLAccessor.insert(dsSubContrTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsSubContrTMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSZM0398E, new String[] {"DS_SUB_CONTR" });
                return;
            } 
            // UpDate DS_CONTR_DTL column SUB_CONTR_CRAT_PROC_CD
            DS_CONTR_DTLTMsg dtlMsg = new DS_CONTR_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dtlMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dtlMsg.dsContrDtlPk, (BigDecimal)this.contrInfo.get("DS_CONTR_DTL_PK"));
            dtlMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(dtlMsg);
            ZYPEZDItemValueSetter.setValue(dtlMsg.subContrCratProcCd, SUB_CONTR_CRAT_PROC_CD_UPDATED);
            S21FastTBLAccessor.update(dtlMsg);
            
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dtlMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSZM0398E, new String[] {"DS_CONTR_DTL" });
                return ;
            }
        }

        // Call Machine Master Update API
        // START 2017/04/19 K.Kitachi [QC#18097, MOD]
//        NSZC001001PMsg apiPMsg = new NSZC001001PMsg();
//        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, pMsg.slsDt);
//        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, ProcessMode.UPDATE_ATTRIBUTE.code);
//        ZYPEZDItemValueSetter.setValue(apiPMsg.svcMachMstrPk, (BigDecimal) this.contrInfo.get("SVC_MACH_MSTR_PK"));
//        ZYPEZDItemValueSetter.setValue(apiPMsg.prfTechCd, pMsg.xxSubContrList.no(0).techCd);
        SVC_MACH_MSTRTMsg exstSvcMachMstrTMsg = findSvcMachMstrTMsgByPk(pMsg.glblCmpyCd.getValue(), (BigDecimal) this.contrInfo.get("SVC_MACH_MSTR_PK"));
        if (exstSvcMachMstrTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }
        NSZC001001PMsg apiPMsg = createPMsg(pMsg, exstSvcMachMstrTMsg);
        // END 2017/04/19 K.Kitachi [QC#18097, MOD]
        NSZC001001 api = new NSZC001001();
        api.execute(apiPMsg, this.onBatTp);

        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                msgMap.addXxMsgId(xxMsgId);
            }
        }
    }

    /**
     * Get ContractInfo.
     * @param pMsg NSZC052001PMsg
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getContrInfo(NSZC052001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrNum", pMsg.dsContrNum.getValue());
        param.put("serNum", pMsg.serNum.getValue());
        // START 2017/10/03 U.Kim [QC#21516,MOD]
        // param.put("subContrAvalStsList", new String[] {DS_CONTR_CTRL_STS.DRAFT, DS_CONTR_CTRL_STS.ENTERED, DS_CONTR_CTRL_STS.QA_HOLD });
        // param.put("subContrTrmnStsList", new String[] {DS_CONTR_CTRL_STS.SINGED, DS_CONTR_CTRL_STS.ACTIVE });
        param.put("subContrAvalStsList", new String[] {DS_CONTR_DTL_STS.SAVED, DS_CONTR_DTL_STS.SUBMITED });
        // START 2018/05/23 M.Naito [QC#21679, ADD]
//        param.put("subContrTrmnStsList", new String[] {DS_CONTR_DTL_STS.SIGNED, DS_CONTR_DTL_STS.ACTIVE });
        param.put("subContrTrmnStsList", new String[] {DS_CONTR_DTL_STS.SIGNED, DS_CONTR_DTL_STS.ACTIVE, DS_CONTR_DTL_STS.TERMINATED});
        // END 2018/05/23 M.Naito [QC#21679, ADD]
        // END 2017/10/03 U.Kim [QC#21516,MOD]
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getContrInfo", param);
    }

    /**
     * exists Vendor.
     * @param glblCmpyCd
     * @param slsDt
     * @param vndCd
     * @return true:Exists, false:Not Exists
     */
    // mod start 2017/03/01 CSA QC#17575
//    private static boolean existsVnd(String glblCmpyCd, String vndCd) {
    private boolean existsVnd(String glblCmpyCd, String slsDt, String vndCd) {
//        VNDTMsg inMsg = new VNDTMsg();
//        inMsg.setSQLID("015");
//        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inMsg.setConditionValue("vndCd01", vndCd);
//        VNDTMsgArray resultList = (VNDTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
//        if (resultList.getValidCount() == 0) {
//            return false;
//        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("vndCd", vndCd);
        ssmParam.put("rgtnStsCdIsReadyForOrder", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("slsDt", slsDt);
        String res = (String) this.ssmBatchClient.queryObject("getVndNm", ssmParam);
        if (res == null) {
            return false;
        }
     // mod end 2017/03/01 CSA QC#17575
        return true;
    }

    /**
     * Is Service Dealer.
     * @param glblCmpyCd
     * @param vndCd
     * @return true:Service Dealer, false:Not Service Dealer
     */
    private static boolean isServiceDealer(String glblCmpyCd, String vndCd) {
        VND_TP_RELNTMsg inMsg = new VND_TP_RELNTMsg();
        // START 2017/02/24 [QC#14675-1, MOD]
        // inMsg.setSQLID("002");
        // inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        // inMsg.setConditionValue("vndCd01", vndCd);
        // VND_TP_RELNTMsgArray resultList = (VND_TP_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        // if (resultList.getValidCount() == 0) {
        //     return false;
        // }
        // VND_TP_RELNTMsg vndTpRelnTmsg = (VND_TP_RELNTMsg) resultList.get(0);
        // if (VND_TP.SERVICE_DEALER.equals(vndTpRelnTmsg.vndTpCd.getValue())) {
        //     return true;
        // }
        // return false;
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.vndCd, vndCd);
        setValue(inMsg.vndTpCd, VND_TP.SERVICE_DEALER);
        inMsg = (VND_TP_RELNTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return false;
        }
        return true;
        // END   2017/02/24 [QC#14675-1, MOD]
    }

    /**
     * exists Technician Master.
     * @param glblCmpyCd
     * @param techCd
     * @return true:Exists, false:Not Exists
     */
    // mod start 2017/03/01 CSA QC#17575
//    private static boolean existsTechMstr(String glblCmpyCd, String techCd) {
    private boolean existsTechMstr(String glblCmpyCd, String slsDt, String techCd) {
//        TECH_MSTRTMsg techMstrTmsg = new TECH_MSTRTMsg();
//        setValue(techMstrTmsg.glblCmpyCd, glblCmpyCd);
//        setValue(techMstrTmsg.techTocCd, techCd);
//        techMstrTmsg = (TECH_MSTRTMsg) S21ApiTBLAccessor.findByKey(techMstrTmsg);
//        if (techMstrTmsg == null) {
//            return false;
//        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("techTocCd", techCd);
        ssmParam.put("maxDt", MAX_THRU_DT);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        String res = (String) this.ssmBatchClient.queryObject("getTechMstr", ssmParam);
        if (res == null) {
            return false;
        }
        // mod end 2017/03/01 CSA QC#17575
        return true;
    }

    /**
     * Is 3rd Party Technician.
     * @param glblCmpyCd
     * @param techCd
     * @return true:3rd party technician, false:Not 3rd party
     * technician
     */
    // mod start 2017/03/01 CSA QC#17575
//    private static boolean isThirdPartyTech(String glblCmpyCd, String techCd) {
    private boolean isThirdPartyTech(String glblCmpyCd, String slsDt, String vndCd, String techCd) {
//        S21_PSNTMsg s21psnTmsg = new S21_PSNTMsg();
//        setValue(s21psnTmsg.glblCmpyCd, glblCmpyCd);
//        setValue(s21psnTmsg.psnCd, techCd);
//        s21psnTmsg = (S21_PSNTMsg) S21ApiTBLAccessor.findByKey(s21psnTmsg);
//        if (s21psnTmsg == null) {
//            return false;
//        }
//        if (PSN_TP._3RD_PARTY_REP.equals(s21psnTmsg.psnTpCd.getValue())) {
//            return true;
//        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("vndCd", vndCd);
        ssmParam.put("techTocCd", techCd);
        ssmParam.put("maxDt", MAX_THRU_DT);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("psnTpCd", PSN_TP.EMPLOYEE);
        // START 2018/01/15 U.Kim [QC#22920, ADD]
        ssmParam.put("splyIndFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
        // END 2018/01/15 U.Kim [QC#22920, ADD]
        String res = (String) this.ssmBatchClient.queryObject("get3rdPartyTech", ssmParam);
        if (res == null) {
            return false;
        }
        return true;
        // mod end 2017/03/01 CSA QC#17575
    }

    /**
     * Get Sub Contr.
     * @param glblCmpyCd
     * @param dsContrDtlPk
     * @return DS_SUB_CONTRTMsgArray
     */
    private DS_SUB_CONTRTMsgArray getDsSubContr(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_SUB_CONTRTMsg inMsg = new DS_SUB_CONTRTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("contrTrmnFlg01", ZYPConstant.FLG_OFF_N);
        return (DS_SUB_CONTRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    // START 2017/04/19 K.Kitachi [QC#18097, ADD]
    private SVC_MACH_MSTRTMsg findSvcMachMstrTMsgByPk(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg prmTMsg = new SVC_MACH_MSTRTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(prmTMsg);
    }

    private NSZC001001PMsg createPMsg(NSZC052001PMsg pMsg, SVC_MACH_MSTRTMsg exstSvcMachMstrTMsg) {
        NSZC001001PMsg nszc001001PMsg = new NSZC001001PMsg();
        setValue(nszc001001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(nszc001001PMsg.slsDt, pMsg.slsDt);
        setValue(nszc001001PMsg.xxModeCd, ProcessMode.UPDATE_ATTRIBUTE.code);
        setValue(nszc001001PMsg.svcMachMstrPk, exstSvcMachMstrTMsg.svcMachMstrPk);
        // START 2018/05/16 M.Naito [QC#25892, MOD]
//        setValue(nszc001001PMsg.prfTechCd, pMsg.xxSubContrList.no(0).techCd);
        setValue(nszc001001PMsg.prfTechCd, exstSvcMachMstrTMsg.prfTechCd);
        // END 2018/05/16 M.Naito [QC#25892, MOD]
        setValue(nszc001001PMsg.custIssPoNum, exstSvcMachMstrTMsg.custIssPoNum);
        setValue(nszc001001PMsg.iwrCondCd, exstSvcMachMstrTMsg.iwrCondCd);
        setValue(nszc001001PMsg.dsKeyAcctFlg, exstSvcMachMstrTMsg.dsKeyAcctFlg);
        setValue(nszc001001PMsg.svcByLineBizTpCd, exstSvcMachMstrTMsg.svcByLineBizTpCd);
        setValue(nszc001001PMsg.bizHrsSunFromTm, exstSvcMachMstrTMsg.bizHrsSunFromTm);
        setValue(nszc001001PMsg.bizHrsSunToTm, exstSvcMachMstrTMsg.bizHrsSunToTm);
        setValue(nszc001001PMsg.bizHrsMonFriFromTm, exstSvcMachMstrTMsg.bizHrsMonFriFromTm);
        setValue(nszc001001PMsg.bizHrsMonFriToTm, exstSvcMachMstrTMsg.bizHrsMonFriToTm);
        setValue(nszc001001PMsg.bizHrsSatFromTm, exstSvcMachMstrTMsg.bizHrsSatFromTm);
        setValue(nszc001001PMsg.bizHrsSatToTm, exstSvcMachMstrTMsg.bizHrsSatToTm);
        setValue(nszc001001PMsg.fldSvcBrCd, exstSvcMachMstrTMsg.fldSvcBrCd);
        // START 2018/05/16 M.Naito [QC#25892, MOD]
//        setValue(nszc001001PMsg.reqTechCd, exstSvcMachMstrTMsg.reqTechCd);
        setValue(nszc001001PMsg.reqTechCd, pMsg.xxSubContrList.no(0).techCd);
        // END 2018/05/16 M.Naito [QC#25892, MOD]
        setValue(nszc001001PMsg.ctrlFldTxt_01, exstSvcMachMstrTMsg.ctrlFldTxt_01);
        setValue(nszc001001PMsg.ctrlFldTxt_02, exstSvcMachMstrTMsg.ctrlFldTxt_02);
        setValue(nszc001001PMsg.ctrlFldTxt_03, exstSvcMachMstrTMsg.ctrlFldTxt_03);
        setValue(nszc001001PMsg.ctrlFldTxt_04, exstSvcMachMstrTMsg.ctrlFldTxt_04);
        setValue(nszc001001PMsg.ctrlFldTxt_05, exstSvcMachMstrTMsg.ctrlFldTxt_05);
        setValue(nszc001001PMsg.ctrlFldTxt_06, exstSvcMachMstrTMsg.ctrlFldTxt_06);
        setValue(nszc001001PMsg.prcContrNum, exstSvcMachMstrTMsg.prcContrNum);
        setValue(nszc001001PMsg.corpAdvtgStsCd, exstSvcMachMstrTMsg.corpAdvtgStsCd);
        setValue(nszc001001PMsg.dsPoExprDt, exstSvcMachMstrTMsg.dsPoExprDt);
        setValue(nszc001001PMsg.hardDriveRmvInclFlg, exstSvcMachMstrTMsg.hardDriveRmvInclFlg);
        setValue(nszc001001PMsg.hardDriveEraseInclFlg, exstSvcMachMstrTMsg.hardDriveEraseInclFlg);
        setValue(nszc001001PMsg.leaseRtrnFeeInclFlg, exstSvcMachMstrTMsg.leaseRtrnFeeInclFlg);
        setValue(nszc001001PMsg.svcNtwkConnStsCd, exstSvcMachMstrTMsg.svcNtwkConnStsCd);
        setValue(nszc001001PMsg.sldByLineBizTpCd, exstSvcMachMstrTMsg.sldByLineBizTpCd);
        setValue(nszc001001PMsg.svcLicCnt, exstSvcMachMstrTMsg.svcLicCnt);
        setValue(nszc001001PMsg.finBrCd, exstSvcMachMstrTMsg.finBrCd);
        setValue(nszc001001PMsg.svcMachMstrLocStsCd, exstSvcMachMstrTMsg.svcMachMstrLocStsCd);
        setValue(nszc001001PMsg.enetPlotFlg, exstSvcMachMstrTMsg.enetPlotFlg);
        setValue(nszc001001PMsg.enetCmntTxt_01, exstSvcMachMstrTMsg.enetCmntTxt_01);
        setValue(nszc001001PMsg.enetCmntTxt_02, exstSvcMachMstrTMsg.enetCmntTxt_02);
        return nszc001001PMsg;
    }
    // END 2017/04/19 K.Kitachi [QC#18097, ADD]
}
