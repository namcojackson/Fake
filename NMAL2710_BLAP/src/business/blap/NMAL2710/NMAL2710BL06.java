/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2710;

import static business.blap.NMAL2710.constant.NMAL2710Constant.CHK_BOX_A1;
import static business.blap.NMAL2710.constant.NMAL2710Constant.NMAM0176E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL2710.common.NMAL2710CommonLogic;
import business.db.POST_UPD_RQST_DTLTMsg;
import business.db.POST_UPD_RQST_HDRTMsg;
import business.db.TRTY_RULETMsg;
import business.parts.NMZC270001PMsg;
import business.parts.NMZC270001_trtyRuleListPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC270001.NMZC270001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_CRAT_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_RSLT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL2710BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   Fujitsu         W.Honda         Create          N/A
 * 2016/08/02   Fujitsu         C.Yokoi         Update          QC#11004
 * 2016/09/14   Fujitsu         C.Yokoi         Update          QC#8156
 * 2017/11/16   Fujitsu         N.Sugiura       Update          CSA-QC#20597
 *</pre>
 */
public class NMAL2710BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2710CMsg bizMsg = (NMAL2710CMsg) cMsg;

            if ("NMAL2710Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2710Scrn00_CMN_Submit(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL2710Scrn00_CMN_Submit(NMAL2710CMsg bizMsg) {
        // Move Postal Code To Exist check
        if (!NMAL2710CommonLogic.getOrgCd(bizMsg)) {
            return;
        }

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, CHK_BOX_A1, ZYPConstant.CHKBOX_ON_Y);
        List<String> selectedOrgCd = new ArrayList<String>();
        List<BigDecimal> selectedRulePk = new ArrayList<BigDecimal>();

        for (int selectedRowIdx : selectedRows) {
            selectedOrgCd.add(bizMsg.A.no(selectedRowIdx).orgCd_A1.getValue());
            selectedRulePk.add(bizMsg.A.no(selectedRowIdx).trtyRulePk_A1.getValue());
        }

        // Active Check Move From Territory Rule
        if (!NMAL2710CommonLogic.checkActiveForMoveFromRule(bizMsg, selectedRows, selectedOrgCd, selectedRulePk, getGlobalCompanyCode())) {
            return;
        }

        // Logic Type Identity Check
        if (!NMAL2710CommonLogic.checkLogicTypeIdentity(bizMsg, selectedOrgCd, selectedRulePk, selectedRows)) {
            return;
        }

        // Move To Effective Range check
        if (!NMAL2710CommonLogic.checkEffectiveRange(bizMsg)) {
            return;
        }

        // 2016/08/02 CSA-QC#11004 Add Start
        // Register Rules for Territory Rule Validation check
        if (!updateExpireOldRules(bizMsg, selectedRows)){
            return;
        }
        if (!insertNewRules(bizMsg, selectedRows)) {
            return;
        }

        // Call Territory Rule Validation API
        if (!callTrtyRuleValidAPI(bizMsg, selectedOrgCd)) {
            EZDConnectionMgr.getInstance().rollback();
            return;
        }
        EZDConnectionMgr.getInstance().rollback();
        // 2016/08/02 CSA-QC#11004 Add End

        // Insert Request Header
        if (!insertRqst(bizMsg, selectedRows)) {
            return;
        }
    }

    /**
     * insert Request
     * @param bizMsg Business Msg
     * @param selectedRows List<Integer>
     * @return boolean
     */
    private boolean insertRqst(NMAL2710CMsg bizMsg, List<Integer> selectedRows) {
        BigDecimal hdrPk = insertRqstHdr(bizMsg);
        if (!ZYPCommonFunc.hasValue(hdrPk)) {
            return false;
        }

        for (int index : selectedRows) {
            if (insertRqstDtl(bizMsg, index, hdrPk)) {
                return false;
            }
        }

        return true;
    }

    /**
     * insert Request Header
     * @param bizMsg Business Msg
     * @return boolean
     */
    private BigDecimal insertRqstHdr(NMAL2710CMsg bizMsg) {
        POST_UPD_RQST_HDRTMsg hdrTMsg = new POST_UPD_RQST_HDRTMsg();

        ZYPEZDItemValueSetter.setValue(hdrTMsg.glblCmpyCd, getGlobalCompanyCode());
        BigDecimal hdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.POST_UPD_RQST_HDR_SQ);
        ZYPEZDItemValueSetter.setValue(hdrTMsg.postUpdRqstHdrPk, hdrPk);
        ZYPEZDItemValueSetter.setValue(hdrTMsg.rqstUsrId, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(hdrTMsg.rqstCratTs, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()) + ZYPDateUtil.getCurrentSystemTime("HHmmssSSS"));
        ZYPEZDItemValueSetter.setValue(hdrTMsg.rqstCratSysTpCd, RQST_CRAT_SYS_TP.ONLINE);
        ZYPEZDItemValueSetter.setValue(hdrTMsg.rqstRsltTpCd, RQST_RSLT_TP.SUBMITTED);
        // 2016/09/14 CSA-QC#8156 Delete
        // ZYPEZDItemValueSetter.setValue(hdrTMsg.rqstRsltCmntTxt, bizMsg.rqstRsltCmntTxt_DC);

        EZDTBLAccessor.insert(hdrTMsg);
        String returnCode = hdrTMsg.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo(NMAM0176E, new String[]{hdrTMsg.getTableName()});
            return null;
        }

        return hdrPk;
    }

    /**
     * insert Request Detail
     * @param bizMsg Business Msg
     * @param index int
     * @param hdrPk BigDecimal
     * @return boolean
     */
    private boolean insertRqstDtl(NMAL2710CMsg bizMsg, int index, BigDecimal hdrPk) {
        POST_UPD_RQST_DTLTMsg dtlTMsg = new POST_UPD_RQST_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(dtlTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(dtlTMsg.postUpdRqstHdrPk, hdrPk);
        BigDecimal dtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.POST_UPD_RQST_DTL_SQ);
        ZYPEZDItemValueSetter.setValue(dtlTMsg.postUpdRqstDtlPk, dtlPk);
        ZYPEZDItemValueSetter.setValue(dtlTMsg.trtyRuleOprdTpDescTxt, bizMsg.A.no(index).trtyRuleOprdTpDescTxt_A1);
        ZYPEZDItemValueSetter.setValue(dtlTMsg.trtyRuleFromValTxt, bizMsg.A.no(index).trtyRuleFromValTxt_A1);
        ZYPEZDItemValueSetter.setValue(dtlTMsg.trtyRuleThruValTxt, bizMsg.A.no(index).trtyRuleThruValTxt_A1);
        ZYPEZDItemValueSetter.setValue(dtlTMsg.oldOrgCd, bizMsg.A.no(index).orgCd_A1);
        ZYPEZDItemValueSetter.setValue(dtlTMsg.newOrgCd, bizMsg.orgCd_DC);
        ZYPEZDItemValueSetter.setValue(dtlTMsg.moveEffFromDtTxt, bizMsg.effFromDt_DC.getValue());
        ZYPEZDItemValueSetter.setValue(dtlTMsg.moveEffThruDtTxt, bizMsg.effThruDt_DC.getValue());
        // 2016/09/14 CSA-QC#8156 Add
        ZYPEZDItemValueSetter.setValue(dtlTMsg.massUpdRsnCmntTxt, bizMsg.rqstRsltCmntTxt_DC);

        EZDTBLAccessor.insert(dtlTMsg);
        String returnCode = dtlTMsg.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo(NMAM0176E, new String[]{dtlTMsg.getTableName()});
            return false;
        }

        return false;
    }

    /**
     * Insert NEW TRTY_RULE
     * @param bizMsg NMAL2710CMsg
     * @param selectedOrgCd List<String>
     * @return boolean
     */
    private boolean insertNewRules(NMAL2710CMsg bizMsg, List<Integer> selectedRows) {
        for (int index : selectedRows) {
            TRTY_RULETMsg trtyRuleTMsg = new TRTY_RULETMsg();
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRulePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_RULE_SQ));
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.orgCd, bizMsg.orgCd_DC);
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleTpCd, TRTY_RULE_TP.POSTAL_CODE);
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleFromValTxt, bizMsg.A.no(index).trtyRuleFromValTxt_A1);
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleThruValTxt, bizMsg.A.no(index).trtyRuleThruValTxt_A1);
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.effFromDt, bizMsg.effFromDt_DC);
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.effThruDt, bizMsg.effThruDt_DC);
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleOprdTpCd, bizMsg.A.no(index).trtyRuleOprdTpCd_A1);
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleLogicTpCd, bizMsg.A.no(index).trtyRuleLogicTpCd_A1);

            EZDTBLAccessor.insert(trtyRuleTMsg);
            if (!RTNCD_NORMAL.equals(trtyRuleTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0176E, new String[]{trtyRuleTMsg.getTableName()});
                return false;
            }
        }
        return true;
    }

    /**
     * Update Expire Old Rules
     * @param bizMsg NMAL2710CMsg
     * @param selectedOrgCd List<String>
     * @return boolean
     */
    private boolean updateExpireOldRules(NMAL2710CMsg bizMsg, List<Integer> selectedRows) {
        String chngThruDt = ZYPDateUtil.addDays(bizMsg.effFromDt_DC.getValue(), -1);

        for (int index : selectedRows) {
            TRTY_RULETMsg trtyRuleTMsg = new TRTY_RULETMsg();

            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRulePk, bizMsg.A.no(index).trtyRulePk_A1);
            trtyRuleTMsg = (TRTY_RULETMsg) EZDTBLAccessor.findByKeyForUpdateWait(trtyRuleTMsg);

            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.effThruDt, chngThruDt);

            EZDTBLAccessor.update(trtyRuleTMsg);
            if (!RTNCD_NORMAL.equals(trtyRuleTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0176E, new String[]{trtyRuleTMsg.getTableName()});
                return false;
            }
        }
        return true;
    }

    /**
     * call Territory Rule Validation API
     * @param bizMsg NMAL2710CMsg
     * @param selectedOrgCd List<String>
     * @return boolean
     */
    private boolean callTrtyRuleValidAPI(NMAL2710CMsg bizMsg, List<String> selectedOrgCd) {

        // 2017/11/16 CSA-QC#20597 Add Start
        if (checkWarningTrtyRule(bizMsg)) {
            bizMsg.xxWrnSkipFlg.setValue(ZYPConstant.FLG_OFF_N);
            return true;
        }
        // 2017/11/16 CSA-QC#20597 Add End
        // NMZC2700 Territory Rule Validation API
        selectedOrgCd.add(bizMsg.orgCd_DC.getValue());
        Collections.sort(selectedOrgCd);

        String prevOrgCd = null;
        for (String orgCd : selectedOrgCd) {

            if (orgCd.equals(prevOrgCd)) {
                continue;
            }

            // get all of registered territory rules
            S21SsmEZDResult ssmResult = NMAL2710Query.getInstance().getTerritoryRules(orgCd);
            if (ssmResult.isCodeNotFound()) {
                continue;
            }

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            // NMZC2700 Territory Rule Validation API
            NMZC270001 nmzc270001 = new NMZC270001();
            NMZC270001PMsg paramMsg = new NMZC270001PMsg();
            int i = 0;
            for (Map<String, Object> result : resultList) {
                if (i == 0) {
                    paramMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
                    paramMsg.orgCd.setValue(orgCd);
                    paramMsg.firstOrgCd.setValue((String) result.get("FIRST_ORG_CD"));
                    paramMsg.trtyTpCd.setValue((String) result.get("TRTY_TP_CD"));
                    paramMsg.orgTierCd.setValue((String) result.get("ORG_TIER_CD"));
                    paramMsg.orgStruTpCd.setValue(ORG_STRU_TP.TERRITORY_STRUCTURE);
                    paramMsg.trtyGrpTpCd.setValue((String) result.get("TRTY_GRP_TP_CD"));
                    paramMsg.effFromDt_T.setValue((String) result.get("ORG_EFF_FROM_DT"));
                    paramMsg.effThruDt_T.setValue((String) result.get("ORG_EFF_THRU_DT"));
                    paramMsg.slsDt.setValue(ZYPDateUtil.getSalesDate());
                }

                // Set Rules
                paramMsg.trtyRuleList.no(i).trtyRuleTpCd.setValue((String) result.get("TRTY_RULE_TP_CD"));
                paramMsg.trtyRuleList.no(i).trtyRuleOprdTpCd.setValue((String) result.get("TRTY_RULE_OPRD_TP_CD"));
                if (ZYPCommonFunc.hasValue((String) result.get("TRTY_RULE_LOGIC_TP_CD"))) {
                    paramMsg.trtyRuleList.no(i).trtyRuleLogicTpCd.setValue((String) result.get("TRTY_RULE_LOGIC_TP_CD"));
                }
                paramMsg.trtyRuleList.no(i).trtyRuleFromValTxt.setValue((String) result.get("TRTY_RULE_FROM_VAL_TXT"));
                if (ZYPCommonFunc.hasValue((String) result.get("TRTY_RULE_THRU_VAL_TXT"))) {
                    paramMsg.trtyRuleList.no(i).trtyRuleThruValTxt.setValue((String) result.get("TRTY_RULE_THRU_VAL_TXT"));
                }
                paramMsg.trtyRuleList.no(i).effFromDt_R.setValue((String) result.get("RULE_EFF_FROM_DT"));
                paramMsg.trtyRuleList.no(i).effThruDt_R.setValue((String) result.get("RULE_EFF_THRU_DT"));

                i++;
            }

            paramMsg.trtyRuleList.setValidCount(i);
            nmzc270001.execute(paramMsg, ONBATCH_TYPE.ONLINE);

            // 2017/11/16 CSA-QC#20597 Add Start
            for(int r=0; r<paramMsg.trtyRuleList.getValidCount(); r++){
                NMZC270001_trtyRuleListPMsg subPMsg = paramMsg.trtyRuleList.no(r);
                if(ZYPCommonFunc.hasValue(subPMsg.xxMsgId_R.getValue())){
                    String messageCode = subPMsg.xxMsgId_R.getValue();
                    if (messageCode.endsWith("W")) {
                        bizMsg.xxWrnSkipFlg.setValue(ZYPConstant.FLG_ON_Y);
                    }
                }
            }
            setWarningBackup(bizMsg);
            // 2017/11/16 CSA-QC#20597 Add End

            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
            for (int j = 0; j < msgList.size(); j++) {
                S21ApiMessage msg = msgList.get(j);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                if (msgPrms != null && msgPrms.length > 0) {
                    bizMsg.setMessageInfo(msgId, msgPrms);
                    return false;
                }
            }

            List<String> msgIdList = S21ApiUtil.getXxMsgIdList(paramMsg);
            if (msgIdList != null && msgIdList.size() > 0) {
                for (int j = 0; j < msgIdList.size(); j++) {
                    bizMsg.setMessageInfo((String) msgIdList.get(0));
                    return false;
                }
            }
            prevOrgCd = orgCd;
        }
        return true;
    }
    // 2017/11/16 CSA-QC#20597 Add Start
    public static boolean checkWarningTrtyRule(NMAL2710CMsg bizMsg) {

        boolean skipFlg = true;

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg.getValue())) {

          // Move Postal Code To(Territory Name)
          if (!bizMsg.orgNm_DC.getValue().equals(bizMsg.orgNm_BW.getValue())) {
              skipFlg= false;
          }
          // Move Postal Code To(Territory Code)
          if (!bizMsg.orgCd_DC.getValue().equals(bizMsg.orgCd_BW.getValue())) {
              skipFlg= false;
          }
          // Move Effective From
          if (!bizMsg.effFromDt_DC.getValue().equals(bizMsg.effFromDt_BW.getValue())) {
              skipFlg= false;
          }
          // Move Effective To
          if (!bizMsg.effThruDt_DC.getValue().equals(bizMsg.effThruDt_BW.getValue())) {
              skipFlg= false;
          }

          for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

              // Check Box
              if (!bizMsg.A.no(i).xxChkBox_A1.getValue().equals(bizMsg.A.no(i).xxChkBox_BW.getValue())) {
                  skipFlg= false;
              }
          }
        } else {
            skipFlg = false;
        }

        return skipFlg;
    }
    public static void setWarningBackup(NMAL2710CMsg bizMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.orgNm_BW, bizMsg.orgNm_DC);
        ZYPEZDItemValueSetter.setValue(bizMsg.orgCd_BW, bizMsg.orgCd_DC);
        ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt_BW, bizMsg.effFromDt_DC);
        ZYPEZDItemValueSetter.setValue(bizMsg.effThruDt_BW, bizMsg.effThruDt_DC);

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxChkBox_BW, bizMsg.A.no(i).xxChkBox_A1);
        }
    }
    // 2017/11/16 CSA-QC#20597 Add End
}