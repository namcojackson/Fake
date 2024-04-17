/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1700;

import static business.blap.NWAL1700.constant.NWAL1700Constant.DB_FLAG_DELETE;
import static business.blap.NWAL1700.constant.NWAL1700Constant.DB_FLAG_INSERT;
import static business.blap.NWAL1700.constant.NWAL1700Constant.INSERT_RETRY_COUNT;
import static business.blap.NWAL1700.constant.NWAL1700Constant.MAX_RECORD_COUNT;
import static business.blap.NWAL1700.constant.NWAL1700Constant.MODE_CREATE;
import static business.blap.NWAL1700.constant.NWAL1700Constant.NWAM8441E;
import static business.blap.NWAL1700.constant.NWAL1700Constant.NWAM8444E;
import static business.blap.NWAL1700.constant.NWAL1700Constant.NWAM8448E;
import static business.blap.NWAL1700.constant.NWAL1700Constant.NWAM8449E;
import static business.blap.NWAL1700.constant.NWAL1700Constant.NZZM0003E;
import static business.blap.NWAL1700.constant.NWAL1700Constant.NWAM0677E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1700.common.NWAL1700CommonLogic;
import business.db.AVAL_DS_ORD_TP_APP_IDTMsg;
import business.db.DS_HLD_CTRL_ORD_TPTMsg;
import business.db.DS_ORD_LINE_PROC_DFNTMsg;
import business.db.DS_ORD_TPTMsg;
import business.db.DS_ORD_TP_LOC_ROLE_RELNTMsg;
import business.db.DS_ORD_TP_PRC_LIST_RELNTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.DS_ORD_TP_XTRNL_SYS_RELNTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1700BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         M.Suzuki        Create          N/A
 * 2016/04/05   Fujitsu         M.Suzuki        Create          S21_NA#5919
 * 2016/04/05   Fujitsu         M.Suzuki        Create          QC#6567
 * 2016/08/10   Fujitsu         M.Yamada        Update          QC#11418
 * 2016/08/24   Fujitsu         S.Yamamoto      Update          QC#8538
 * 2016/09/30   Fujitsu         M.Ohno          Update          S21_NA#14612
 * 2017/03/07   Fujitsu         W.Honda         Update          QC#16855
 * 2017/09/11   Fujitsu         T.Murai         Update          QC#16346(L3_Sol#373)
 * 2017/12/14   Fujitsu         Mz.Takahashi    Update          QC#19804(Sol349)
 * 2018/08/25   Fujitsu         S.Yamamoto      Update          QC#26438
 * 2020/04/24   CITS            K.Ogino         Update          QC#56638
 * 2023/04/25   Hitachi         A.Kohinata      Update          QC#61337
 *</pre>
 */
public class NWAL1700BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1700CMsg bizMsg = (NWAL1700CMsg) cMsg;
            NWAL1700SMsg glblMsg = (NWAL1700SMsg) sMsg;

            if ("NWAL1700Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NWAL1700Scrn00_CMN_Save(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Save Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1700Scrn00_CMN_Save(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {

        boolean isChecked = true;

        for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(j).ajeAcctBatCd_A) && !NWAL1700CommonLogic.searchAJE(bizMsg, this.getGlobalCompanyCode(), j)) {
                isChecked = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsInvTpCd) && !NWAL1700CommonLogic.searchARTran(bizMsg, this.getGlobalCompanyCode())) {
            isChecked = false;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.defBillToCustAcctCd) && !NWAL1700CommonLogic.searchBillToAccount(bizMsg, this.getGlobalCompanyCode())) {
            isChecked = false;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.defBillToCustCd) && !NWAL1700CommonLogic.searchBillToLocation(bizMsg, this.getGlobalCompanyCode())) {
            isChecked = false;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.defPrcCatgCd) && !NWAL1700CommonLogic.searchPriceList(bizMsg, this.getGlobalCompanyCode())) {
            isChecked = false;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.defMaintPrcCatgCd) && !NWAL1700CommonLogic.searchServicePriceList(bizMsg, this.getGlobalCompanyCode())) {
            isChecked = false;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.defCarrSvcLvlCd) && !NWAL1700CommonLogic.searchCarrier(bizMsg, this.getGlobalCompanyCode())) {
            isChecked = false;
        }
        if (checkReason(bizMsg)) {
            isChecked = false;
        }

        if (!isChecked) {
            return;
        }

        // Add Start 2017/12/14 QC#19804(Sol349)
        if (ZYPCommonFunc.hasValue(bizMsg.trtyGrpTpTxt)){
            String trtyGrpTpTxt =  convTrimTrtyGrpTpTxt(bizMsg.trtyGrpTpTxt.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.trtyGrpTpTxt, trtyGrpTpTxt);

            if (ZYPCommonFunc.hasValue(bizMsg.trtyGrpTpTxt)){
                if (relationCheck(bizMsg) == false){
                    return;
                }
            }
        }
        // Add End 2017/12/14 QC#19804(Sol349)

        if (MODE_CREATE.equals(bizMsg.xxModeCd.getValue())) {

            if (!insertTable(bizMsg)) {
                return;
            }
        } else {

            // -----------2016/04/04 S21_NA#5919 Add Start
            DS_ORD_TPTMsg dsOrdTpTMsg = new DS_ORD_TPTMsg();
            ZYPEZDItemValueSetter.setValue(dsOrdTpTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(dsOrdTpTMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
            dsOrdTpTMsg = (DS_ORD_TPTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsOrdTpTMsg);
            // -----------2016/04/04 S21_NA#5919 Add End
            if (!bizMsg.dsOrdTpNm.getValue().equals(glblMsg.dsOrdTpNm.getValue()) //
                    || !dsOrdTpTMsg.cpoOrdTpCd.getValue().equals(glblMsg.cpoOrdTpCd.getValue()) //
                    || !dsOrdTpTMsg.revRecogMethCd.getValue().equals(glblMsg.revRecogMethCd.getValue())) { //2016/04/04 S21_NA#6620 MOD

                if (!ZYPDateUtil.isSameTimeStamp(glblMsg.ezUpTime.getValue(), glblMsg.ezUpTimeZone.getValue(), dsOrdTpTMsg.ezUpTime.getValue(), dsOrdTpTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return;
                }

                ZYPEZDItemValueSetter.setValue(dsOrdTpTMsg.dsOrdTpNm, bizMsg.dsOrdTpNm);
                ZYPEZDItemValueSetter.setValue(dsOrdTpTMsg.dsOrdTpDescTxt, bizMsg.dsOrdTpNm); //2016/04/04 S21_NA#6620 MOD
                // 2016/04/04 S21_NA#5919 Add Start-------------------
                ZYPEZDItemValueSetter.setValue(dsOrdTpTMsg.revRecogMethCd, glblMsg.revRecogMethCd.getValue());
                ZYPEZDItemValueSetter.setValue(dsOrdTpTMsg.cpoOrdTpCd, glblMsg.cpoOrdTpCd.getValue());
                // 2016/04/04 S21_NA#5919 Add End---------------------
                EZDTBLAccessor.update(dsOrdTpTMsg);
                if (!RTNCD_NORMAL.equals(dsOrdTpTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NWAM8448E, new String[] {"Update:DS_ORD_TP" });
                    return;
                }
            }

            if (checkChangeValueProcDfn(bizMsg, glblMsg)) {
                DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
                tMsg = (DS_ORD_TP_PROC_DFNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
                if (!ZYPDateUtil.isSameTimeStamp(glblMsg.ezUpTime_TP.getValue(), glblMsg.ezUpTimeZone_TP.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return;
                }

                setValueDsOrdTpProcDfnt(bizMsg, tMsg);
                EZDTBLAccessor.update(tMsg);
                if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NWAM8448E, new String[] {"Update:DS_ORD_TP_PROC_DFN" });
                    return;
                }
            }

            if (!deleteLineCatgAsgn(bizMsg, glblMsg)) {
                return;
            }

            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (DB_FLAG_INSERT.equals(bizMsg.A.no(i).xxRowId_A.getValue())) {
                    DS_ORD_LINE_PROC_DFNTMsg dsOrdLinetMsg = new DS_ORD_LINE_PROC_DFNTMsg();
                    setValueDsOrdLine(bizMsg, bizMsg.dsOrdTpCd.getValue(), dsOrdLinetMsg, i);

                    EZDTBLAccessor.create(dsOrdLinetMsg);
                    if (!RTNCD_NORMAL.equals(dsOrdLinetMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NWAM8448E, new String[] {"Insert:DS_ORD_LINE_PROC_DFN" });
                        return;
                    }
                    continue;
                }

                for (int j = 0; j < glblMsg.A.getValidCount(); j++) {
                    if (bizMsg.A.no(i).xxRowId_A.getValue().equals(glblMsg.A.no(j).xxRowId_A.getValue())) {

                        if (checkChangeValueLine(bizMsg, glblMsg, i, j)) {
                            DS_ORD_LINE_PROC_DFNTMsg dsOrdLinetMsg = new DS_ORD_LINE_PROC_DFNTMsg();
                            ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.glblCmpyCd, getGlobalCompanyCode());
                            ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
                            ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.dsOrdLineCatgCd, bizMsg.A.no(i).dsOrdLineCatgCd_A);
                            dsOrdLinetMsg = (DS_ORD_LINE_PROC_DFNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsOrdLinetMsg);

                            if (!ZYPDateUtil.isSameTimeStamp(glblMsg.A.no(j).ezUpTime_A.getValue(), glblMsg.A.no(j).ezUpTimeZone_A.getValue(), dsOrdLinetMsg.ezUpTime.getValue(), dsOrdLinetMsg.ezUpTimeZone.getValue())) {
                                bizMsg.setMessageInfo(NZZM0003E);
                                return;
                            }
                            setValueDsOrdLine(bizMsg, bizMsg.dsOrdTpCd.getValue(), dsOrdLinetMsg, i);
                            EZDTBLAccessor.update(dsOrdLinetMsg);
                            if (!RTNCD_NORMAL.equals(dsOrdLinetMsg.getReturnCode())) {
                                bizMsg.setMessageInfo(NWAM8448E, new String[] {"Update:DS_ORD_LINE_PROC_DFN" });
                                return;
                            }
                        }
                    }
                }
            }

        }
        // W01
        S21SsmEZDResult hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.VALIDATION_HOLD, bizMsg.dsOrdTpCd.getValue());
        regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.VALIDATION_HOLD, bizMsg.dsOrdTpCd.getValue(), bizMsg.vldApvlNodeUsgFlg.getValue());
        // W06
        hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.PROFITABILITY_HOLD, bizMsg.dsOrdTpCd.getValue());
        regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.PROFITABILITY_HOLD, bizMsg.dsOrdTpCd.getValue(), bizMsg.prftApvlNodeUsgFlg.getValue());
        // W02
        hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.SUPPLY_YIELD_VALIDATION_HOLD, bizMsg.dsOrdTpCd.getValue());
        regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.SUPPLY_YIELD_VALIDATION_HOLD, bizMsg.dsOrdTpCd.getValue(), bizMsg.splyAbuseNodeUsgFlg.getValue());
        // W03
        hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.PENDING_ORDER_HOLD, bizMsg.dsOrdTpCd.getValue());
        regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.PENDING_ORDER_HOLD, bizMsg.dsOrdTpCd.getValue(), bizMsg.splyAbuseNodeUsgFlg.getValue());

        // W04
        hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.CONTRACT_STATUS_HOLD, bizMsg.dsOrdTpCd.getValue());
        regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.CONTRACT_STATUS_HOLD, bizMsg.dsOrdTpCd.getValue(), bizMsg.splyAbuseNodeUsgFlg.getValue());
        // W05
        hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.SUPPLY_ENFORCEMENT_HOLD, bizMsg.dsOrdTpCd.getValue());
        regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.SUPPLY_ENFORCEMENT_HOLD, bizMsg.dsOrdTpCd.getValue(), bizMsg.splyAbuseNodeUsgFlg.getValue());

        // C01
        hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.PO_REVIEW, bizMsg.dsOrdTpCd.getValue());
        regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.PO_REVIEW, bizMsg.dsOrdTpCd.getValue(), bizMsg.crApvlNodeUsgFlg.getValue());
        // 053
        hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.OVER_DUE, bizMsg.dsOrdTpCd.getValue());
        regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.OVER_DUE, bizMsg.dsOrdTpCd.getValue(), bizMsg.crApvlNodeUsgFlg.getValue());

        // 001
        hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.CREDIT_LIMIT, bizMsg.dsOrdTpCd.getValue());
        regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.CREDIT_LIMIT, bizMsg.dsOrdTpCd.getValue(), bizMsg.crApvlNodeUsgFlg.getValue());

        // 002
        hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.CREDIT_PROFILE, bizMsg.dsOrdTpCd.getValue());
        regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.CREDIT_PROFILE, bizMsg.dsOrdTpCd.getValue(), bizMsg.crApvlNodeUsgFlg.getValue());

        // add start 2023/04/25 QC#61337
        // 010: Manual Price
        hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.MANUAL_PRICE, bizMsg.dsOrdTpCd.getValue());
        regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.MANUAL_PRICE, bizMsg.dsOrdTpCd.getValue(), bizMsg.manPrcNodeUsgFlg.getValue());
        // add end 2023/04/25 QC#61337

        // S21_NA#8538 Mod Start
        S21SsmEZDResult getRvwReqResult = NWAL1700Query.getInstance().getRvwReq(bizMsg);
        if (!getRvwReqResult.isCodeNotFound()) {
            Map<String, Object> getRvwReq = (Map<String, Object>) getRvwReqResult.getResultObject();
            String finalRvwFlg = (String) getRvwReq.get("FINAL_RVW_REQ_FLG");
            String contrCrRvwFlg = (String) getRvwReq.get("CONTR_CR_RVW_REQ_FLG");

            // C02
            hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.FINAL_REVIEW, bizMsg.dsOrdTpCd.getValue());
            if (ZYPConstant.FLG_ON_Y.equals(finalRvwFlg)) {
                regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.FINAL_REVIEW, bizMsg.dsOrdTpCd.getValue(), bizMsg.crApvlNodeUsgFlg.getValue());
            } else {
                regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.FINAL_REVIEW, bizMsg.dsOrdTpCd.getValue(), ZYPConstant.FLG_OFF_N);
            }

            // W10
            hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.CONTRACT_CREDIT_REVIEW, bizMsg.dsOrdTpCd.getValue());
            if (ZYPConstant.FLG_ON_Y.equals(contrCrRvwFlg)) {
                regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.CONTRACT_CREDIT_REVIEW, bizMsg.dsOrdTpCd.getValue(), bizMsg.crApvlNodeUsgFlg.getValue());
            } else {
                regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.CONTRACT_CREDIT_REVIEW, bizMsg.dsOrdTpCd.getValue(), ZYPConstant.FLG_OFF_N);
            }
        } else {
            // C02 logical remove
            hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.FINAL_REVIEW, bizMsg.dsOrdTpCd.getValue());
            regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.FINAL_REVIEW, bizMsg.dsOrdTpCd.getValue(), ZYPConstant.FLG_OFF_N);

            // W10 logical remove
            hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.CONTRACT_CREDIT_REVIEW, bizMsg.dsOrdTpCd.getValue());
            regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.CONTRACT_CREDIT_REVIEW, bizMsg.dsOrdTpCd.getValue(), ZYPConstant.FLG_OFF_N);
        }
        // S21_NA#8538 Mod End
        // S21_NA#14612 Add Start
        // 100 HDD Removal
        hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.HDD_REMOVAL, bizMsg.dsOrdTpCd.getValue());
        regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.HDD_REMOVAL, bizMsg.dsOrdTpCd.getValue(), ZYPConstant.FLG_ON_Y);
        
        // 101 HDD Erase
        hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.HDD_ERASE, bizMsg.dsOrdTpCd.getValue());
        regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.HDD_ERASE, bizMsg.dsOrdTpCd.getValue(), ZYPConstant.FLG_ON_Y);
        // S21_NA#14612 Add End

        // 2018/08/25 QC#26438 Add Start
        // 027 DPL SCREENING
        hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.DPL_SCREENING, bizMsg.dsOrdTpCd.getValue());
        regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.DPL_SCREENING, bizMsg.dsOrdTpCd.getValue(), ZYPConstant.FLG_ON_Y);

        // 028 EMBARGO
        hldCtrlResult = NWAL1700Query.getInstance().getHldCtrl(HLD_RSN.EMBARGO, bizMsg.dsOrdTpCd.getValue());
        regitHoldInfo(bizMsg, hldCtrlResult, HLD_RSN.EMBARGO, bizMsg.dsOrdTpCd.getValue(), ZYPConstant.FLG_ON_Y);
        // 2018/08/25 QC#26438 Add End

        //2016/05/24 QC#5919-----
        ArrayList<String> bizAppidList = new ArrayList<String>();
        S21SsmEZDResult getBizAppIdResult = NWAL1700Query.getInstance().getBizAppId(bizMsg);
        List<Map> getBizAppIdList = (List<Map>) getBizAppIdResult.getResultObject();
        for (int i = 0; i < getBizAppIdList.size(); i++) {
            Map mapData = getBizAppIdList.get(i);
            String bizAppID = (String) mapData.get("BIZ_APP_ID");
            bizAppidList.add(bizAppID);
        }
        //2016/05/24 QC#5919-----
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            String bizAppId = bizMsg.D.no(i).bizAppId_D.getValue();
            String ckbox = bizMsg.D.no(i).xxChkBox_D.getValue();
            if (ZYPCommonFunc.hasValue(ckbox)) {
                if (!bizAppidList.contains(bizAppId)) {
                    AVAL_DS_ORD_TP_APP_IDTMsg tMsg = new AVAL_DS_ORD_TP_APP_IDTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.bizAppId, bizAppId);
                    EZDTBLAccessor.create(tMsg);
                    if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NWAM8448E, new String[] {"Insert:AVAL_DS_ORD_TP_APP_ID" }); // 2016/04/04
                        // S21_NA#5919
                        // MOD
                        return;
                    }
                }
            } else {
                if (bizAppidList.contains(bizAppId)) {
                    AVAL_DS_ORD_TP_APP_IDTMsg tMsg = new AVAL_DS_ORD_TP_APP_IDTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.bizAppId, bizAppId);
                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NWAM8448E, new String[] {"Delete:AVAL_DS_ORD_TP_APP_ID" }); // 2016/04/04
                        // S21_NA#5919
                        // MOD
                        return;
                    }
                }
            }

        }

        ArrayList<String> locRoleList = new ArrayList<String>();
        S21SsmEZDResult locationRoleResult = NWAL1700Query.getInstance().getLocationRoleReln(bizMsg);
        List<Map> locationRoleList = (List<Map>) locationRoleResult.getResultObject();
        for (int i = 0; i < locationRoleList.size(); i++) {
            Map mapData = locationRoleList.get(i);
            String locRole = (String) mapData.get("LOC_ROLE_TP_CD");
            locRoleList.add(locRole);
        }

        for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
            String locRoleTpCd = bizMsg.E.no(i).locRoleTpCd_E.getValue();
            String ckbox = bizMsg.E.no(i).xxChkBox_E.getValue();

            if (ZYPCommonFunc.hasValue(ckbox)) {
                if (!locRoleList.contains(locRoleTpCd)) {
                    DS_ORD_TP_LOC_ROLE_RELNTMsg tMsg = new DS_ORD_TP_LOC_ROLE_RELNTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.locRoleTpCd, locRoleTpCd);
                    EZDTBLAccessor.create(tMsg);
                    if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NWAM8448E, new String[] {"Insert:DS_ORD_TP_LOC_ROLE_RELN" });
                        return;
                    }
                }
            } else {
                if (locRoleList.contains(locRoleTpCd)) {
                    DS_ORD_TP_LOC_ROLE_RELNTMsg tMsg = new DS_ORD_TP_LOC_ROLE_RELNTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.locRoleTpCd, locRoleTpCd);
                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NWAM8448E, new String[] {"Delete:DS_ORD_TP_LOC_ROLE_RELN" });
                        return;
                    }
                }
            }
        }

        ArrayList<String> prcCdList = new ArrayList<String>();
        S21SsmEZDResult getPriceListRelnResult = NWAL1700Query.getInstance().getPriceListReln(bizMsg);
        List<Map> getPriceList = (List<Map>) getPriceListRelnResult.getResultObject();
        for (int i = 0; i < getPriceList.size(); i++) {
            Map mapData = getPriceList.get(i);
            String prcListCd = (String) mapData.get("PRC_LIST_TP_CD");
            prcCdList.add(prcListCd);
        }

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            String prcListTpCd = bizMsg.B.no(i).prcListTpCd_B.getValue();
            String ckbox = bizMsg.B.no(i).xxChkBox_B.getValue();

            if (ZYPCommonFunc.hasValue(ckbox)) {
                if (!prcCdList.contains(prcListTpCd)) {
                    DS_ORD_TP_PRC_LIST_RELNTMsg tMsg = new DS_ORD_TP_PRC_LIST_RELNTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.prcListTpCd, prcListTpCd);
                    EZDTBLAccessor.create(tMsg);
                    if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NWAM8448E, new String[] {"Insert:DS_ORD_TP_PRC_LIST_RELN" });
                        return;
                    }
                }
            } else {
                if (prcCdList.contains(prcListTpCd)) {
                    DS_ORD_TP_PRC_LIST_RELNTMsg tMsg = new DS_ORD_TP_PRC_LIST_RELNTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.prcListTpCd, prcListTpCd);
                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NWAM8448E, new String[] {"Delete:DS_ORD_TP_PRC_LIST_RELN" });
                        return;
                    }
                }
            }
        }

        ArrayList<String> sysCdList = new ArrayList<String>();
        S21SsmEZDResult getSystemRelnResult = NWAL1700Query.getInstance().getSystemReln(bizMsg);
        List<Map> getSystemRelnList = (List<Map>) getSystemRelnResult.getResultObject();
        for (int i = 0; i < getSystemRelnList.size(); i++) {
            Map mapData = getSystemRelnList.get(i);
            String sysCd = (String) mapData.get("OM_XTRNL_SYS_CD");
            sysCdList.add(sysCd);
        }

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            String omXtrnlSysCd = bizMsg.C.no(i).omXtrnlSysCd_C.getValue();
            String ckbox = bizMsg.C.no(i).xxChkBox_C.getValue();
            if (ZYPCommonFunc.hasValue(ckbox)) {
                if (!sysCdList.contains(omXtrnlSysCd)) {
                    DS_ORD_TP_XTRNL_SYS_RELNTMsg tMsg = new DS_ORD_TP_XTRNL_SYS_RELNTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.omXtrnlSysCd, omXtrnlSysCd);
                    EZDTBLAccessor.create(tMsg);
                    if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NWAM8448E, new String[] {"Insert:DS_ORD_TP_XTRNL_SYS_RELN" });
                        return;
                    }
                }
            } else {
                if (sysCdList.contains(omXtrnlSysCd)) {
                    DS_ORD_TP_XTRNL_SYS_RELNTMsg tMsg = new DS_ORD_TP_XTRNL_SYS_RELNTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.omXtrnlSysCd, omXtrnlSysCd);
                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NWAM8448E, new String[] {"Delete:DS_ORD_TP_XTRNL_SYS_RELN" });
                        return;
                    }
                }
            }
        }

    }

    /**
     * @param bizMsg
     * @param glblMsg
     * @return if error then return false.
     */
    private boolean deleteLineCatgAsgn(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (DB_FLAG_DELETE.equals(glblMsg.A.no(i).xxRowId_A.getValue())) {
                DS_ORD_LINE_PROC_DFNTMsg dsOrdLinetMsg = new DS_ORD_LINE_PROC_DFNTMsg();
                ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.dsOrdTpCd, glblMsg.dsOrdTpCd);
                ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.dsOrdLineCatgCd, glblMsg.A.no(i).dsOrdLineCatgCd_A);
                dsOrdLinetMsg = (DS_ORD_LINE_PROC_DFNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsOrdLinetMsg);

                if (dsOrdLinetMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else if (!ZYPDateUtil.isSameTimeStamp(glblMsg.A.no(i).ezUpTime_A.getValue(), glblMsg.A.no(i).ezUpTimeZone_A.getValue(), dsOrdLinetMsg.ezUpTime.getValue(), dsOrdLinetMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                EZDTBLAccessor.logicalRemove(dsOrdLinetMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrdLinetMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NWAM8449E);
                    return false;
                }

            }
        }
        return true;
    }

    private boolean checkChangeValueLine(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg, int i, int j) {
        boolean ckFlag = false;
        if (!bizMsg.A.no(i).dsOrdLineCatgCd_A.getValue().equals(glblMsg.A.no(j).dsOrdLineCatgCd_A.getValue())) {
            ckFlag = true;
        }
        if (!bizMsg.A.no(i).ordProcTpCd_A.getValue().equals(glblMsg.A.no(j).ordProcTpCd_A.getValue())) {
            ckFlag = true;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).lineProcDfnSortNum_A)) {
            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(j).lineProcDfnSortNum_A)) {
                ckFlag = true;
            } else if (bizMsg.A.no(i).lineProcDfnSortNum_A.getValue().compareTo(glblMsg.A.no(j).lineProcDfnSortNum_A.getValue()) != 0) {
                ckFlag = true;
            }
        } else {
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(j).lineProcDfnSortNum_A)) {
                ckFlag = true;
            }
        }

        if (!bizMsg.A.no(i).ajeAcctBatCd_A.getValue().equals(glblMsg.A.no(j).ajeAcctBatCd_A.getValue())) {
            ckFlag = true;
        }

        String primLineCatgFlg = bizMsg.A.no(i).primLineCatgFlg_A.getValue();
        if (!ZYPCommonFunc.hasValue(primLineCatgFlg)) {
            primLineCatgFlg = ZYPConstant.FLG_OFF_N;
        }

        if (!primLineCatgFlg.equals(glblMsg.A.no(j).primLineCatgFlg_A.getValue())) {
            ckFlag = true;
        }

        // Add Start 2017/09/11 S21_NA#16346
        String rmaPrimLineCatgFlg = bizMsg.A.no(i).rmaPrimLineCatgFlg_A.getValue();
        if (!ZYPCommonFunc.hasValue(rmaPrimLineCatgFlg)) {
            rmaPrimLineCatgFlg = ZYPConstant.FLG_OFF_N;
        }
        if (!rmaPrimLineCatgFlg.equals(glblMsg.A.no(j).rmaPrimLineCatgFlg_A.getValue())) {
            ckFlag = true;
        }
        // Add End 2017/09/11 S21_NA#16346

        if (!bizMsg.A.no(i).dsOrdLineDrctnNm_A.getValue().equals(glblMsg.A.no(j).dsOrdLineDrctnNm_A.getValue())) {
            ckFlag = true;
        }
        if (!bizMsg.A.no(i).effFromDt_A.getValue().equals(glblMsg.A.no(j).effFromDt_A.getValue())) {
            ckFlag = true;
        }
        if (!bizMsg.A.no(i).effThruDt_A.getValue().equals(glblMsg.A.no(j).effThruDt_A.getValue())) {
            ckFlag = true;
        }
        if (!bizMsg.A.no(i).revFcstCd_A.getValue().equals(glblMsg.A.no(j).revFcstCd_A.getValue())) {
            ckFlag = true;
        }

        String activeFlg = bizMsg.A.no(i).actvFlg_A.getValue();
        if (!ZYPCommonFunc.hasValue(activeFlg)) {
            activeFlg = ZYPConstant.FLG_OFF_N;
        }
        if (!activeFlg.equals(glblMsg.A.no(j).actvFlg_A.getValue())) {
            ckFlag = true;
        }

        return ckFlag;
    }

    private boolean checkChangeValueProcDfn(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {
        boolean changeflag = false;
        if (!bizMsg.ordProcTpCd.getValue().equals(glblMsg.ordProcTpCd.getValue())) {
            changeflag = true;
        }

        if (!bizMsg.dsOrdRsnGrpCd.getValue().equals(glblMsg.dsOrdRsnGrpCd.getValue())) {
            changeflag = true;
        }

        if (!bizMsg.vldApvlNodeUsgFlg.getValue().equals(glblMsg.vldApvlNodeUsgFlg.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.vldApvlNodePrflCd.getValue().equals(glblMsg.vldApvlNodePrflCd.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.diChkNodeUsgFlg.getValue().equals(glblMsg.diChkNodeUsgFlg.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.diChkNodePrflCd.getValue().equals(glblMsg.diChkNodePrflCd.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.crApvlNodeUsgFlg.getValue().equals(glblMsg.crApvlNodeUsgFlg.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.crApvlNodePrflCd.getValue().equals(glblMsg.crApvlNodePrflCd.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.assetNodeUsgFlg.getValue().equals(glblMsg.assetNodeUsgFlg.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.assetNodePrflCd.getValue().equals(glblMsg.assetNodePrflCd.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.prftApvlNodeUsgFlg.getValue().equals(glblMsg.prftApvlNodeUsgFlg.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.prftApvlNodePrflCd.getValue().equals(glblMsg.prftApvlNodePrflCd.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.splyAbuseNodeUsgFlg.getValue().equals(glblMsg.splyAbuseNodeUsgFlg.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.splyAbuseNodePrflCd.getValue().equals(glblMsg.splyAbuseNodePrflCd.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.outOfWhNodeUsgFlg.getValue().equals(glblMsg.outOfWhNodeUsgFlg.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.outOfWhNodePrflCd.getValue().equals(glblMsg.outOfWhNodePrflCd.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.effFromDt.getValue().equals(glblMsg.effFromDt.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.effThruDt.getValue().equals(glblMsg.effThruDt.getValue())) {
            changeflag = true;
        }

        if (!bizMsg.actvFlg.getValue().equals(glblMsg.actvFlg.getValue())) {
            changeflag = true;
        }

        if (!bizMsg.dsInvTpCd.getValue().equals(glblMsg.dsInvTpCd.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.defPrcCatgCd.getValue().equals(glblMsg.defPrcCatgCd.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.revFcstCd.getValue().equals(glblMsg.revFcstCd.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.frtCondCd.getValue().equals(glblMsg.frtCondCd.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.defBillToCustAcctCd.getValue().equals(glblMsg.defBillToCustAcctCd.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.defBillToCustCd.getValue().equals(glblMsg.defBillToCustCd.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.defInstlTpCd.getValue().equals(glblMsg.defInstlTpCd.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.lineBizTpCd.getValue().equals(glblMsg.lineBizTpCd.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.invPrintStyleCd.getValue().equals(glblMsg.invPrintStyleCd.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.defShpgSvcLvlCd.getValue().equals(glblMsg.defShpgSvcLvlCd.getValue())) {
            changeflag = true;
        }
        if (!bizMsg.defCarrSvcLvlCd.getValue().equals(glblMsg.defCarrSvcLvlCd.getValue())) {
            changeflag = true;
        }

        if (!bizMsg.autoCancOrdFlg.getValue().equals(glblMsg.autoCancOrdFlg.getValue())) {
            changeflag = true;
        }

        if (!bizMsg.defMaintPrcCatgCd.getValue().equals(glblMsg.defMaintPrcCatgCd.getValue())) {
            changeflag = true;
        }
        //2016/04/04 S21_NA#6620 Add
        if (!bizMsg.dsOrdTpNoteTxt.getValue().equals(glblMsg.dsOrdTpNoteTxt.getValue())) {
            changeflag = true;
        }
        //2016/04/04 S21_NA#6620 Add

        // Mod Start 2017/12/14 QC#19804(Sol349)
        // Add Start 2017/03/07 QC#16855
        if (!bizMsg.trtyGrpTpTxt.getValue().equals(glblMsg.trtyGrpTpTxt.getValue())) {
            changeflag = true;
        }
        // Add End 2017/03/07 QC#16855
        // Mod End 2017/12/14 QC#19804(Sol349)
        // Add 2020/04/24 QC#56638 Start
        if (!bizMsg.baseLocTxt.getValue().equals(glblMsg.baseLocTxt.getValue())) {
            changeflag = true;
        }
        // Add 2020/04/24 QC#56638 End

        return changeflag;
    }

    private boolean insertTable(NWAL1700CMsg bizMsg) {
        S21SsmEZDResult maxOrdTpResult = NWAL1700Query.getInstance().getMaxOrdTpCd(bizMsg);
        Map<String, Object> maxOrdTp = (Map<String, Object>) maxOrdTpResult.getResultObject();
        String maxOrdTpCd = (String) maxOrdTp.get("MAX_ORD_TP_CD");
        BigDecimal maxSortNum = (BigDecimal) maxOrdTp.get("MAX_SORT_NUM");
        maxSortNum = maxSortNum.add(new BigDecimal(1));

        // 2016/04/04 S21_NA#6357 MOD Start ------------
        S21SsmEZDResult getOrderProcTpResult = NWAL1700Query.getInstance().getOrderProcTp(bizMsg.ordProcTpCd.getValue());
        // 2016/04/04 S21_NA#6357 MOD End --------------
        Map<String, Object> getOrderProcTp = (Map<String, Object>) getOrderProcTpResult.getResultObject();
        String cpoOrdtpCd = (String) getOrderProcTp.get("CPO_ORD_TP_CD");
        String revRecogMethCd = (String) getOrderProcTp.get("REV_RECOG_METH_CD");

        int newOrdTpCd = Integer.valueOf(maxOrdTpCd) + 1;
        if (newOrdTpCd > MAX_RECORD_COUNT) {
            bizMsg.setMessageInfo(NWAM8441E);
            return false;
        }
        String nextOrdTpCd = String.format("%04d", newOrdTpCd);
        // 2016/04/04 S21_NA#5919 MOD Start --------------
        // ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd,
        // nextOrdTpCd);
        // 2016/04/04 S21_NA#5919 MOD END --------------

        DS_ORD_TPTMsg dsOrdTptMsg = new DS_ORD_TPTMsg();
        setValueDsOrdTp(bizMsg, maxSortNum, cpoOrdtpCd, revRecogMethCd, nextOrdTpCd, dsOrdTptMsg);
        EZDTBLAccessor.create(dsOrdTptMsg);
        if (RTNCD_DUPLICATE.equals(dsOrdTptMsg.getReturnCode())) {
            for (int i = 0; i < INSERT_RETRY_COUNT; i++) {
                newOrdTpCd = Integer.valueOf(newOrdTpCd) + 1;
                // 2016/04/04 S21_NA#5919 MOD Start --------------
                if (newOrdTpCd > MAX_RECORD_COUNT) {
                    bizMsg.setMessageInfo(NWAM8441E);
                    return false;
                }
                nextOrdTpCd = String.format("%04d", newOrdTpCd);
                ZYPEZDItemValueSetter.setValue(dsOrdTptMsg.dsOrdTpCd, nextOrdTpCd);
                // ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd,
                // nextOrdTpCd);
                // 2016/04/04 S21_NA#5919 MOD Start --------------

                EZDTBLAccessor.create(dsOrdTptMsg);
                if (!RTNCD_DUPLICATE.equals(dsOrdTptMsg.getReturnCode())) {
                    break;
                }
            }
        }
        // 2016/04/04 S21_NA#5919 ADD Start --------------
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, nextOrdTpCd);
        // 2016/04/04 S21_NA#5919 ADD Start --------------

        if (!RTNCD_NORMAL.equals(dsOrdTptMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NWAM8448E, new String[] {"Insert:DS_ORD_TP" });
            return false;
        }

        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfntMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.dsOrdTpCd, nextOrdTpCd);

        setValueDsOrdTpProcDfnt(bizMsg, dsOrdTpProcDfntMsg);
        EZDTBLAccessor.create(dsOrdTpProcDfntMsg);
        if (!RTNCD_NORMAL.equals(dsOrdTpProcDfntMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NWAM8448E, new String[] {"Insert:DS_ORD_TP_PROC_DFN" });
            return false;
        }
        // line
        DS_ORD_LINE_PROC_DFNTMsg dsOrdLinetMsg = new DS_ORD_LINE_PROC_DFNTMsg();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            setValueDsOrdLine(bizMsg, nextOrdTpCd, dsOrdLinetMsg, i);
            EZDTBLAccessor.create(dsOrdLinetMsg);
            if (!RTNCD_NORMAL.equals(dsOrdLinetMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NWAM8448E, new String[] {"Insert:DS_ORD_LINE_PROC_DFN" });
                return false;
            }
        }

        return true;
    }

    private boolean checkReason(NWAL1700CMsg bizMsg) {
        S21SsmEZDResult ssmScheduleResult = NWAL1700Query.getInstance().getReasonName(bizMsg);
        if (!ssmScheduleResult.isCodeNotFound()) {
            bizMsg.dsOrdTpNm.setErrorInfo(1, NWAM8444E, new String[] {bizMsg.dsOrdTpNm.getValue() });
            return true;
        }

        return false;
    }

    private void regitHoldInfo(NWAL1700CMsg bizMsg, S21SsmEZDResult hldCtrlResult, String hldRsn, String nodeCd, String flag) {
        if (ZYPConstant.FLG_ON_Y.equals(flag)) {
            if (hldCtrlResult.isCodeNotFound()) {
                DS_HLD_CTRL_ORD_TPTMsg hldTmsg = createHldCtrl(hldRsn, nodeCd);
                EZDTBLAccessor.create(hldTmsg);
                if (!RTNCD_NORMAL.equals(hldTmsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NWAM8448E, new String[] {"Insert:DS_HLD_CTRL_ORD_TP" });
                    return;
                }
            }
        } else {
            if (!hldCtrlResult.isCodeNotFound()) {
                DS_HLD_CTRL_ORD_TPTMsg hldTmsg = createHldCtrl(hldRsn, nodeCd);
                EZDTBLAccessor.logicalRemove(hldTmsg);
                if (!RTNCD_NORMAL.equals(hldTmsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NWAM8448E, new String[] {"Delete:DS_HLD_CTRL_ORD_TP" });
                    return;
                }
            }
        }
    }

    private DS_HLD_CTRL_ORD_TPTMsg createHldCtrl(String hldRsn, String nodeCd) {
        DS_HLD_CTRL_ORD_TPTMsg hldTmsg = new DS_HLD_CTRL_ORD_TPTMsg();
        ZYPEZDItemValueSetter.setValue(hldTmsg.hldRsnCd, hldRsn);
        ZYPEZDItemValueSetter.setValue(hldTmsg.applyDsOrdTpCd, nodeCd);
        ZYPEZDItemValueSetter.setValue(hldTmsg.glblCmpyCd, getGlobalCompanyCode());
        return hldTmsg;
    }

    private void setValueDsOrdLine(NWAL1700CMsg bizMsg, String nextOrdTpCd, DS_ORD_LINE_PROC_DFNTMsg dsOrdLinetMsg, int i) {
        ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.dsOrdTpCd, nextOrdTpCd);
        ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.dsOrdLineCatgCd, bizMsg.A.no(i).dsOrdLineCatgCd_A);
        ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.ordProcTpCd, bizMsg.A.no(i).ordProcTpCd_A);
        ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.lineProcDfnSortNum, bizMsg.A.no(i).lineProcDfnSortNum_A);
        ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.ajeAcctBatCd, bizMsg.A.no(i).ajeAcctBatCd_A);

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).primLineCatgFlg_A)) {
            ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.primLineCatgFlg, bizMsg.A.no(i).primLineCatgFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.primLineCatgFlg, ZYPConstant.FLG_OFF_N);
        }
        // Add Start 2017/09/11 S21_NA#16346
        if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).rmaPrimLineCatgFlg_A)) {
            ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.rmaPrimLineCatgFlg, bizMsg.A.no(i).rmaPrimLineCatgFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.rmaPrimLineCatgFlg, ZYPConstant.FLG_OFF_N);
        }
        // Add End 2017/09/11 S21_NA#16346

        ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.defRevFcstCd, bizMsg.A.no(i).revFcstCd_A);
        ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.effFromDt, bizMsg.A.no(i).effFromDt_A);
        ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.effThruDt, bizMsg.A.no(i).effThruDt_A);

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).actvFlg_A)) {
            ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.actvFlg, bizMsg.A.no(i).actvFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(dsOrdLinetMsg.actvFlg, ZYPConstant.FLG_OFF_N);
        }
    }

    private void setValueDsOrdTpProcDfnt(NWAL1700CMsg bizMsg, DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfntMsg) {

        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.ordProcTpCd, bizMsg.ordProcTpCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.dsOrdRsnGrpCd, bizMsg.dsOrdRsnGrpCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.vldApvlNodeUsgFlg, bizMsg.vldApvlNodeUsgFlg);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.vldApvlNodePrflCd, bizMsg.vldApvlNodePrflCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.diChkNodeUsgFlg, bizMsg.diChkNodeUsgFlg);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.diChkNodePrflCd, bizMsg.diChkNodePrflCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.crApvlNodeUsgFlg, bizMsg.crApvlNodeUsgFlg);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.crApvlNodePrflCd, bizMsg.crApvlNodePrflCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.assetNodeUsgFlg, bizMsg.assetNodeUsgFlg);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.assetNodePrflCd, bizMsg.assetNodePrflCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.prftApvlNodeUsgFlg, bizMsg.prftApvlNodeUsgFlg);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.prftApvlNodePrflCd, bizMsg.prftApvlNodePrflCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.splyAbuseNodeUsgFlg, bizMsg.splyAbuseNodeUsgFlg);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.splyAbuseNodePrflCd, bizMsg.splyAbuseNodePrflCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.outOfWhNodeUsgFlg, bizMsg.outOfWhNodeUsgFlg);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.outOfWhNodePrflCd, bizMsg.outOfWhNodePrflCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.effFromDt, bizMsg.effFromDt);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.effThruDt, bizMsg.effThruDt);

        if (ZYPCommonFunc.hasValue(bizMsg.actvFlg)) {
            ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.actvFlg, bizMsg.actvFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.actvFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.dsInvTpCd, bizMsg.dsInvTpCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.defPrcCatgCd, bizMsg.defPrcCatgCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.defRevFcstCd, bizMsg.revFcstCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.frtCondCd, bizMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.defBillToCustAcctCd, bizMsg.defBillToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.defBillToCustCd, bizMsg.defBillToCustCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.defInstlTpCd, bizMsg.defInstlTpCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.invPrintStyleCd, bizMsg.invPrintStyleCd);

        // Mod Start 2017/12/14 QC#19804(Sol349)
        // Add Start 2017/03/08 QC#16855
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.trtyGrpTpTxt, bizMsg.trtyGrpTpTxt);
        // Add End 2017/03/08 QC#16855
        // Mod End 2017/12/14 QC#19804(Sol349)

        // 2020/04/24 QC#56688 Start
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.baseLocTxt, bizMsg.baseLocTxt);
        // 2020/04/24 QC#56688 End

        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.defShpgSvcLvlCd, bizMsg.defShpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.defCarrSvcLvlCd, bizMsg.defCarrSvcLvlCd);

        if (ZYPCommonFunc.hasValue(bizMsg.autoCancOrdFlg)) {
            ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.autoCancOrdFlg, bizMsg.autoCancOrdFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.autoCancOrdFlg, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.dropShipAvalFlg)) {
            ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.dropShipAvalFlg, bizMsg.dropShipAvalFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.dropShipAvalFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.defMaintPrcCatgCd, bizMsg.defMaintPrcCatgCd);
        //2016/04/04 S21_NA#6620 MOD Start ------------
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.dsOrdTpNoteTxt, bizMsg.dsOrdTpNoteTxt);
        //2016/04/04 S21_NA#6620 MOD End --------------
        // add start 2023/04/25 QC#61337
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.manPrcNodeUsgFlg, bizMsg.manPrcNodeUsgFlg);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfntMsg.manPrcNodePrflCd, bizMsg.manPrcNodePrflCd);
        // add end 2023/04/25 QC#61337
    }

    private void setValueDsOrdTp(NWAL1700CMsg bizMsg, BigDecimal maxSortNum, String cpoOrdtpCd, String revRecogMethCd, String nextOrdTpCd, DS_ORD_TPTMsg tMsg) {
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, nextOrdTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpNm, bizMsg.dsOrdTpNm);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpSortNum, maxSortNum);
        //2016/04/04 S21_NA#6620 MOD Start ------------
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpDescTxt, bizMsg.dsOrdTpNm);
        //2016/04/04 S21_NA#6620 MOD Start ------------
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdTpCd, cpoOrdtpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.emerDelyFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.thirdPtyLeaseFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.custIstlFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.conslInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.revRecogMethCd, revRecogMethCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wtyAutoCratFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.splyContrChkFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.machMstrCratFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.convApvlReqFlg, ZYPConstant.FLG_OFF_N);
    }
    

    // Add Start 2017/12/14 QC#19804(Sol349)
    /**
     * convTrimTrtyGrpTpTxt
     * @param trtyGrpTpTxt
     * @return String
     */
    private String convTrimTrtyGrpTpTxt(String trtyGrpTpTxt){
        StringBuilder sb = new StringBuilder();

        if (ZYPCommonFunc.hasValue(trtyGrpTpTxt) == false) {
            return sb.toString();
        }

        String[] trtyGrpTpArray = trtyGrpTpTxt.split(",");

        for (int cnt = 0; cnt < trtyGrpTpArray.length; cnt++){
            String  trtyGrpTpCd = trtyGrpTpArray[cnt].trim();

            if (sb.length() > 0){
                sb.append(",");
            }
            sb.append(trtyGrpTpCd);
        }

        return sb.toString();
    }
    // Add End 2017/12/14 QC#19804(Sol349)

    // Add Start 2017/12/14 QC#19804(Sol349)
    /**
     * relationCheck
     * @param bizMsg
     * @return Boolean
     */
    private Boolean relationCheck(NWAL1700CMsg bizMsg){
        Boolean ret = true;

        if (ZYPCommonFunc.hasValue(bizMsg.trtyGrpTpTxt) == false) {
            return ret;
        }

        String[] trtyGrpTpArray = bizMsg.trtyGrpTpTxt.getValue().split(",");

        for (int cnt = 0; cnt < trtyGrpTpArray.length; cnt++){
            String  trtyGrpTpCd = trtyGrpTpArray[cnt].trim();

            if (ZYPCommonFunc.hasValue(trtyGrpTpCd)){
                ret = NWAL1700Query.getInstance().isExitsTrtyGrp(trtyGrpTpCd, bizMsg.lineBizTpCd.getValue());

                if (ret == false){
                    bizMsg.lineBizTpCd.setErrorInfo(1, NWAM0677E);
                    bizMsg.trtyGrpTpTxt.setErrorInfo(1, NWAM0677E);
                    break;
                }
            }
        }
        return ret;
    }
    // Add End 2017/12/14 QC#19804(Sol349)
}
