/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC061001;

import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.ATTR1_LBL_TXT;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.ATTR2_LBL_TXT;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.ATTR2_LBL_TXT_PI;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.ATTR3_LBL_TXT;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.ATTR3_LBL_TXT_PI;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.ATTR4_LBL_TXT;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.ATTR5_LBL_TXT;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.BIND_PARAM_ADJ_VAR_FLG;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.BIND_PARAM_BIZ_AREA_ORG_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.BIND_PARAM_BR_FLG;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.BIND_PARAM_CNT_STS_CD_LIST;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.BIND_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.BIND_PARAM_GNRN_TP_CD_LIST;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.BIND_PARAM_ORG_STRU_TP_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.BIND_PARAM_PHYS_INVTY_NUM;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.BIND_PARAM_PSN_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.BIND_PARAM_SALES_DATE;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.CANCEL_MODE;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.CANCEL_TXT;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.COL_NM_LOC_TP_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.COL_NM_PHYS_INVTY_NUM;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.CREATE_MODE;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.DATE_TIME_PATTERN;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.DB_RESULT_BR_NM;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.DB_RESULT_COL_PHYS_INVTY_ADJ_NM;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.DB_RESULT_COL_RTL_WH_CD;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.DB_RESULT_COL_TOT_VAR_GRS_AMT;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.DB_RESULT_COL_TOT_VAR_GRS_QTY;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.DB_RESULT_COL_TOT_VAR_NET_AMT;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.DB_RESULT_COL_TOT_VAR_NET_QTY;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.NDAM0013E;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.NFDM0008E;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.NLCM0049E;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.NLCM0155E;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.NLCM0156E;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.NLCM0157E;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.NLCM0158E;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.NLCM0159E;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.NLCM0160E;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.NLCM0161E;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.NLZM2451E;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.NMZM0011E;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.NPZM0212E;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.NPZM0213E;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.NWBM0032E;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.NWZM0012E;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.NWZM1756E;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.PI_ADJ_NM_PREFIX;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.RTRN_CD_NORMAL;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.TBL_ID_PHYS_INVTY_CTRL;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.VAR_CHAR_CONST_NAME;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.WF_BIZ_ID;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.WF_BIZ_ID_PI;
import static com.canon.cusa.s21.api.NLZ.NLZC061001.constant.NLZC061001Constant.WF_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.MDSETMsg;
import business.db.S21_PSNTMsg;
import business.parts.NLZC061001PMsg;
import business.parts.NLZC063001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC063001.NLZC063001;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreateApprovalHistory;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreateApprovalHistoryBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HRCH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_CNT_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;

/**
 *<pre>
 * Tech-PI Approval to WF API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/09/2016   CITS            K.Ogino           Create          Initial
 * 03/30/2016   CITS            K.Ogino           Update          QC#6060
 * 04/22/2016   CITS            K.Ogino           Update          QC#6724
 * 05/24/2016   CITS            K.Ogino           Update          QC#8010
 * 11/07/2016   CITS            S.Endo            Update          QC#14479
 * 11/11/2016   CITS            S.Endo            Update          QC#14479
 * 11/15/2016   CITS            S.Endo            Update          QC#14479
 *</pre>
 */
public class NLZC061001 extends S21ApiCommonBase {
    // -------------------------------------------------
    // Instance Fields
    // -------------------------------------------------

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** GLBL_CMPY_CD */
    private String glblCmpyCd = null;

    /** SLS_DT */
    private String slsDt = null;

    /** XX_MODE */
    private String xxModeCd = null;

    /** PHYS_INVTY_CTRL_NM */
    private String physInvtyCtrlNm = null;

    /** PHYS_INVTY_DT */
    private String physInvtyDt = null;

    /** TECH_TOC_CD */
    private String techTocCd = null;

    /** TECH_NM */
    private String techNm = null;

    /** PHYS_INVTY_NUM */
    private String physInvtyNum = null;

    /** XX_USER_ID */
    private String xxUserId = null;

    /** Work Flow Definition Name */
    private String wfDefNm = null;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchTypeApi = null;

    private String piAdjNmStr = null;
    
    /**
     * Constructor
     */
    public NLZC061001() {
        super();
    }

    /**
     * Tech-PI Approval to WF API
     * @param param pMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NLZC061001PMsg param, final ONBATCH_TYPE onBatchType) {
        this.msgMap = new S21ApiMessageMap(param);
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.onBatchTypeApi = onBatchType;
        // this.userInfo =
        // S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo();

        NLZC061001PMsg pMsg = (NLZC061001PMsg) this.msgMap.getPmsg();
        doProcess(pMsg);
        this.msgMap.flush();
    }

    /**
     * doProcess
     * @param pMsg NLZC061001PMsg
     */
    private void doProcess(NLZC061001PMsg pMsg) {
        checkInputParam(pMsg);

        if (hasError()) {
            return;
        }

        setInputParam(pMsg);

        if (CREATE_MODE.equals(this.xxModeCd)) {
            NLZC061001TokenObject tokenBiz = startWorkflowPreparing();

            if (hasError()) {
                return;
            }

            startWorkflow(this.wfDefNm, tokenBiz);
            if (hasError()) {
                return;
            }
            createApprovalHistory(APVL_HIST_ACT_TP.SUBMIT, this.xxUserId, APVL_HIST_SRC_TP.TECHNICIAN_PHYSICAL_INVENTORY, piAdjNmStr);
        } else if (CANCEL_MODE.equals(this.xxModeCd)) {
            cancelWorkFlow();
        } else {
            addError(NWBM0032E);
        }

        if (hasError()) {
            return;
        }
    }

    // -----------------------------------------------------------
    // Prepared work flow
    // -----------------------------------------------------------
    private NLZC061001TokenObject startWorkflowPreparing() {

        String piMode;
        String lineBizTpCd = null;
        String hrSupvNm = null;
        String techBrNm = null;
        List<Map<String,Object>> rtlWhCodeList = null;
        try {
            piMode=getPIMode(glblCmpyCd, physInvtyNum);
        } catch (S21NwfBizException e) {
            addError(NPZM0213E);
            return null;
        }
        
        if (LOC_TP.TECHNICIAN.equals(piMode)) {
          if (!hasValue(this.techTocCd)) {
              addError(NLCM0159E);
          }
          if (!hasValue(this.techNm)) {
              addError(NLCM0160E);
          }
            
          // Get Technician BU and Tech Manager
            S21_PSNTMsg psnTMsg = new S21_PSNTMsg();

            ZYPEZDItemValueSetter.setValue(psnTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psnTMsg.psnCd, this.techTocCd);
            ZYPEZDItemValueSetter.setValue(psnTMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
            S21_PSNTMsg outPsnTMsg = (S21_PSNTMsg) S21ApiTBLAccessor.findByKey(psnTMsg);
            if (outPsnTMsg == null) {
                addError(NLZM2451E);
                return null;
            }
            lineBizTpCd = outPsnTMsg.lineBizTpCd.getValue();
            hrSupvNm = outPsnTMsg.hrSupvNm.getValue();

            // Get Technician Branch
            Map<String, Object> branchMap = new HashMap<String, Object>();
            branchMap.put(BIND_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
            branchMap.put(BIND_PARAM_ORG_STRU_TP_CD, ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
            branchMap.put(BIND_PARAM_BIZ_AREA_ORG_CD, BIZ_AREA_ORG.SERVICE);
            branchMap.put(BIND_PARAM_BR_FLG, ZYPConstant.FLG_ON_Y);
            branchMap.put(BIND_PARAM_SALES_DATE, slsDt);
            // GNRN_TP_CD List
            ArrayList<String> gnrnTpCdList = new ArrayList<String>();
            String[] arrayStr = new String[] {GNRN_TP.CURRENT, GNRN_TP.FUTURE };
            for (int i = 0; i < arrayStr.length; i++) {
                gnrnTpCdList.add(arrayStr[i]);
            }
            branchMap.put(BIND_PARAM_GNRN_TP_CD_LIST, gnrnTpCdList);
            branchMap.put(BIND_PARAM_PSN_CD, this.techTocCd);
            List<Map<String, Object>> resultBranchList = getSsmQueryObjectList("findTechBranch", branchMap);
            techBrNm = null;
            if (resultBranchList != null && resultBranchList.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (Map<String, Object> map : resultBranchList) {
                    sb.append((String) map.get(DB_RESULT_BR_NM));
                    sb.append(",");
                }
                sb.delete(sb.length() - 1, sb.length());
                techBrNm = sb.toString();
            } else {
                techBrNm = "NULL";
            }
        }


        // Get Variance and Number of SKU's
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(BIND_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        paramMap.put(BIND_PARAM_PHYS_INVTY_NUM, this.physInvtyNum);
        paramMap.put(BIND_PARAM_ADJ_VAR_FLG, ZYPConstant.FLG_ON_Y);
        Map<String, Object> resultMap = getSsmQueryObject("findVarianceAndNumberOfSKUs", paramMap);

        // Create PIAdjNm String
        if (resultMap.get(DB_RESULT_COL_PHYS_INVTY_ADJ_NM) != null) {
            piAdjNmStr = PI_ADJ_NM_PREFIX + resultMap.get(DB_RESULT_COL_PHYS_INVTY_ADJ_NM).toString();
        }
        if (LOC_TP.WAREHOUSE.equals(piMode)) {
            //Get Retail WH Code
            paramMap = new HashMap<String, Object>();
            paramMap.put(BIND_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
            paramMap.put(BIND_PARAM_PHYS_INVTY_NUM, this.physInvtyNum);
            rtlWhCodeList = getSsmQueryObjectList("getRtlWhCode", paramMap);
        }

        // Business Token Object
        NLZC061001TokenObject tokenBiz = new NLZC061001TokenObject();
        if (LOC_TP.TECHNICIAN.equals(piMode)) {
            tokenBiz.setBizId(WF_BIZ_ID);
            this.wfDefNm = WF_ID;
        } else {
            tokenBiz.setBizId(WF_BIZ_ID_PI);
            this.wfDefNm = WF_BIZ_ID_PI;
        }
        tokenBiz.setGlblCmpyCd(this.glblCmpyCd);
        
        // Set Attribute
        tokenBiz.setAttribute1(this.physInvtyNum);
        if (LOC_TP.TECHNICIAN.equals(piMode)) {
            tokenBiz.setAttribute2(ZYPCommonFunc.concatString(this.techTocCd, " ", this.techNm));
            tokenBiz.setAttribute3(this.physInvtyCtrlNm);
        } else {
            tokenBiz.setAttribute2(this.physInvtyCtrlNm);
            if (resultMap.get(DB_RESULT_COL_PHYS_INVTY_ADJ_NM) != null) {
                tokenBiz.setAttribute3(resultMap.get(DB_RESULT_COL_PHYS_INVTY_ADJ_NM).toString());
            }
        }
        tokenBiz.setAttribute4(ZYPDateUtil.formatEzd8ToDisp(this.physInvtyDt, true));
        tokenBiz.setAttribute5(ZYPCommonFunc.toCommaHensyu(((BigDecimal) resultMap.get(DB_RESULT_COL_TOT_VAR_GRS_AMT)).toString(), false));

        // Set Attribute Label
        tokenBiz.setAttribute1Lbl(ATTR1_LBL_TXT);
        if (LOC_TP.TECHNICIAN.equals(piMode)) {
            tokenBiz.setAttribute2Lbl(ATTR2_LBL_TXT);
            tokenBiz.setAttribute3Lbl(ATTR3_LBL_TXT);
        } else {
            tokenBiz.setAttribute2Lbl(ATTR2_LBL_TXT_PI);
            tokenBiz.setAttribute3Lbl(ATTR3_LBL_TXT_PI);
        }
        tokenBiz.setAttribute4Lbl(ATTR4_LBL_TXT);
        tokenBiz.setAttribute5Lbl(ATTR5_LBL_TXT);

        // Set Condition Data
        if (LOC_TP.TECHNICIAN.equals(piMode)) {
            tokenBiz.setCondStr1(this.techTocCd);
        } else {
            if (rtlWhCodeList!=null && rtlWhCodeList.size()>0) {
                tokenBiz.setCondStr1(rtlWhCodeList.get(0).get(DB_RESULT_COL_RTL_WH_CD).toString());
            }
        }

        // QC#26232
        tokenBiz.setCondStr2(APVL_HRCH_TP.POSITIONAL);

        // Set Condition Num
        tokenBiz.setCondNum1((BigDecimal) resultMap.get(DB_RESULT_COL_TOT_VAR_GRS_AMT));

        if (LOC_TP.TECHNICIAN.equals(piMode)) {
            tokenBiz.setCondStr3(APVL_HIST_SRC_TP.TECHNICIAN_PHYSICAL_INVENTORY);
        } else {
            List<Map<String, Object>> tranMap = getApvlTeamTransactions(tokenBiz);
            if (tranMap == null || tranMap.isEmpty()) {
                tokenBiz.setCondStr3(APVL_HIST_SRC_TP.PHYSICAL_INVENTORY_MERCHANDISE);
            } else if (tranMap.size() == 1) {
                tokenBiz.setCondStr3((String) tranMap.get(0).get("APVL_HIST_SRC_TP_CD"));
            } else {
                // Multiple
                paramMap = new HashMap<String, Object>();
                paramMap.put(BIND_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
                paramMap.put(BIND_PARAM_PHYS_INVTY_NUM, this.physInvtyNum);
                paramMap.put(BIND_PARAM_ADJ_VAR_FLG, ZYPConstant.FLG_ON_Y);
                paramMap.put("parts", MDSE_CATG.PARTS);
                BigDecimal retCount = getSsmQueryCount("cntPartsItem", paramMap);

                if (BigDecimal.ZERO.compareTo(retCount) == 0) {
                    tokenBiz.setCondStr3(APVL_HIST_SRC_TP.PHYSICAL_INVENTORY_MERCHANDISE);
                } else {
                    tokenBiz.setCondStr3(APVL_HIST_SRC_TP.PHYSICAL_INVENTORY_PARTS);
                }
            }
        }

        // Set add params
        tokenBiz.setPhysInvtyCtlNm(this.physInvtyCtrlNm);
        tokenBiz.setPhysInvtyDt(this.physInvtyDt);

        if (LOC_TP.TECHNICIAN.equals(piMode)) {
            tokenBiz.setTechTocCd(this.techTocCd);
            tokenBiz.setTechNm(this.techNm);
            tokenBiz.setTechBuNm(lineBizTpCd);
            tokenBiz.setTechBrNm(techBrNm);
            tokenBiz.setTechMgrNm(hrSupvNm);
        }

        tokenBiz.setVarGrsQty((BigDecimal) resultMap.get(DB_RESULT_COL_TOT_VAR_GRS_QTY));
        tokenBiz.setVarGrsAmt((BigDecimal) resultMap.get(DB_RESULT_COL_TOT_VAR_GRS_AMT));
        tokenBiz.setVarNetQty((BigDecimal) resultMap.get(DB_RESULT_COL_TOT_VAR_NET_QTY));
        tokenBiz.setVarNetAmt((BigDecimal) resultMap.get(DB_RESULT_COL_TOT_VAR_NET_AMT));
        tokenBiz.setTrxRefNum(this.physInvtyNum);
        tokenBiz.setSalesDate(this.slsDt);

        if (LOC_TP.WAREHOUSE.equals(piMode)) {
            if (resultMap.get(DB_RESULT_COL_PHYS_INVTY_ADJ_NM) != null){
                tokenBiz.setPhysInvtyAdjNm(resultMap.get(DB_RESULT_COL_PHYS_INVTY_ADJ_NM).toString());    
            }
            if (rtlWhCodeList != null && rtlWhCodeList.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (Map<String, Object> rtlWhCode : rtlWhCodeList) {
                        sb = sb.append(rtlWhCode.get(DB_RESULT_COL_RTL_WH_CD).toString());
                        sb = sb.append(",");
                }
                String rtlWhCodeStr =sb.toString();
                int index = rtlWhCodeStr.lastIndexOf(",");
                if (index == rtlWhCodeStr.length() - 1) {
                    tokenBiz.setRtlWhCodeDescTxt(rtlWhCodeStr.substring(0, index));
                } else {
                    tokenBiz.setRtlWhCodeDescTxt(rtlWhCodeStr);
                }
            }
        }

        return tokenBiz;
    }

    // -----------------------------------------------------------
    // Start work flow
    // -----------------------------------------------------------
    private void startWorkflow(String wfId, NLZC061001TokenObject tokenBiz) {

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        S21NwfProcess process = null;

        try {
            context = factory.getContex();
            process = context.newProcess(wfId);
        } catch (S21NwfSystemException e) {
            addError(NPZM0213E);
            return;
        }

        S21NwfToken token = process.getToken();
        process.setDocumentId(this.physInvtyNum);
        token.setTokenObj(tokenBiz);

        try {
            token.start();

        } catch (S21NwfSystemException e) {
            addError(NPZM0213E);
            return;

        } catch (S21NwfBizException e) {
            this.msgMap.addXxMsgId(e.getMessageInfo().getCode());
            return;

        } catch (S21NwfException e) {
            addError(NFDM0008E);
            return;
        }
    }

    // -----------------------------------------------------------
    // Cancel work flow
    // -----------------------------------------------------------
    private void cancelWorkFlow() {
        String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NAME, this.glblCmpyCd);
        if (!(hasValue(varCharConstVal))) {
            addError(NLCM0161E);
            return;
        }
        // Cancel Check
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(BIND_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        paramMap.put(BIND_PARAM_PHYS_INVTY_NUM, this.physInvtyNum);
        // cntStsCdList
        ArrayList<String> cntStsCdList = new ArrayList<String>();
        String[] arrayStr = varCharConstVal.split(",");
        for (int i = 0; i < arrayStr.length; i++) {
            cntStsCdList.add(arrayStr[i]);
        }
        paramMap.put(BIND_PARAM_CNT_STS_CD_LIST, cntStsCdList);
        BigDecimal retCount = getSsmQueryCount("countCancelPiControl", paramMap);
        if (BigDecimal.ZERO.compareTo(retCount) == 0) {
            // Cancel NG
            addError(NWZM1756E);
            return;
        } else {
            // Cancel OK
            NLZC063001PMsg pMsg = new NLZC063001PMsg();
            NLZC063001 api = new NLZC063001();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.salesDate, this.slsDt);
            ZYPEZDItemValueSetter.setValue(pMsg.physInvtyCntStsCd, PHYS_INVTY_CNT_STS.PI_CANCELED);
            ZYPEZDItemValueSetter.setValue(pMsg.physInvtyNum, this.physInvtyNum);
            api.execute(pMsg, this.onBatchTypeApi);
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
                for (String xxMsgId : xxMsgIdList) {
                    addError(xxMsgId);
                }
                return;
            }
            createApprovalHistory(APVL_HIST_ACT_TP.REJECTED, this.xxUserId, APVL_HIST_SRC_TP.TECHNICIAN_PHYSICAL_INVENTORY, CANCEL_TXT);
        }
    }

    // -----------------------------------------------------------
    // Data base Query
    // -----------------------------------------------------------
    private BigDecimal getSsmQueryCount(String sqlId, Map<String, Object> paramMap) {
        BigDecimal retCont = (BigDecimal) ssmBatchClient.queryObject(sqlId, paramMap);
        return retCont;
    }

    private Map<String, Object> getSsmQueryObject(String sqlId, Map<String, Object> paramMap) {
        Map<String, Object> mapRes = (Map<String, Object>) ssmBatchClient.queryObject(sqlId, paramMap);
        return mapRes;
    }

    private List<Map<String, Object>> getSsmQueryObjectList(String sqlId, Map<String, Object> paramMap) {
        List<Map<String, Object>> mapResList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(sqlId, paramMap);
        return mapResList;
    }

    // -----------------------------------------------------------
    // Create approval history
    // -----------------------------------------------------------
    private void createApprovalHistory(String apvlHistActTpCd, String apvlByPsnCd, String apvlHistSrcTpCd, String apvlHistTxt) {
        NPXC001001CreateApprovalHistoryBean inParam = new NPXC001001CreateApprovalHistoryBean();
        inParam.setGlblCmpyCd(this.glblCmpyCd);
        inParam.setTrxRefNum(this.physInvtyNum);
        inParam.setApvlHistInfoTs(ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
        inParam.setApvlHistActTpCd(apvlHistActTpCd);
        inParam.setApvlByPsnCd(apvlByPsnCd);
        inParam.setApvlHistSrcTpCd(apvlHistSrcTpCd);
        inParam.setApvlHistTxt(apvlHistTxt);

        String rtrnCd = NPXC001001CreateApprovalHistory.createApprovalHistory(inParam);
        if (!rtrnCd.equals(RTRN_CD_NORMAL)) {
            addError(NPZM0212E);
        }
    }

    // -----------------------------------------------------------
    // Validation
    // -----------------------------------------------------------
    private void checkInputParam(NLZC061001PMsg pMsg) {

        if (!hasValue(pMsg.glblCmpyCd)) {
            addError(NDAM0013E);
        }
        if (!hasValue(pMsg.salesDate)) {
            addError(NMZM0011E);
        }
        if (!hasValue(pMsg.xxMode)) {
            addError(NWZM0012E);
        }
        if (!hasValue(pMsg.physInvtyNum)) {
            addError(NLCM0155E);
        }
        if (!hasValue(pMsg.xxUserId)) {
            addError(NLCM0156E);
        }
        if (!(hasError()) && CREATE_MODE.equals(pMsg.xxMode.getValue())) {
            if (!hasValue(pMsg.physInvtyCtrlNm)) {
                addError(NLCM0157E);
            }
            if (!hasValue(pMsg.physInvtyDt)) {
                addError(NLCM0158E);
            }
//            if (!hasValue(pMsg.techTocCd)) {
//                addError(NLCM0159E);
//            }
//            if (!hasValue(pMsg.techNm)) {
//                addError(NLCM0160E);
//            }
        } else if (CANCEL_MODE.equals(pMsg.xxMode.getValue())) {
            return;
        }
    }

    // -----------------------------------------------------------
    // Set Parameter
    // -----------------------------------------------------------
    private void setInputParam(NLZC061001PMsg pMsg) {
        this.glblCmpyCd = pMsg.glblCmpyCd.getValue();
        this.slsDt = pMsg.salesDate.getValue();
        this.xxModeCd = pMsg.xxMode.getValue();
        this.physInvtyNum = pMsg.physInvtyNum.getValue();
        this.xxUserId = pMsg.xxUserId.getValue();
        if (hasValue(pMsg.physInvtyCtrlNm)) {
            this.physInvtyCtrlNm = pMsg.physInvtyCtrlNm.getValue();
        }
        if (hasValue(pMsg.physInvtyDt)) {
            this.physInvtyDt = pMsg.physInvtyDt.getValue();
        }
        if (hasValue(pMsg.techTocCd)) {
            this.techTocCd = pMsg.techTocCd.getValue();
        }
        if (hasValue(pMsg.techNm)) {
            this.techNm = pMsg.techNm.getValue();
        }
        this.wfDefNm = WF_ID;
    }

    // -----------------------------------------------------------
    // Util
    // -----------------------------------------------------------
    private boolean hasError() {
        return this.msgMap.getMsgMgr().isXxMsgId();
    }

    private void addError(String xxMsgId) {
        this.msgMap.addXxMsgId(xxMsgId);
    }
    // -----------------------------------------------------------
    // GetPIMode
    // -----------------------------------------------------------
    private String getPIMode(String glblCmpyCd , String physInvtyNum) throws S21NwfBizException  {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(BIND_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        paramMap.put(BIND_PARAM_PHYS_INVTY_NUM, physInvtyNum);
        String result = (String) ssmBatchClient.queryObject("getPIMode", paramMap);

        String[] tmp = {TBL_ID_PHYS_INVTY_CTRL + "." + COL_NM_LOC_TP_CD, COL_NM_PHYS_INVTY_NUM, physInvtyNum};
        if (!ZYPCommonFunc.hasValue(result) || (!LOC_TP.WAREHOUSE.equals(result) && !LOC_TP.TECHNICIAN.equals(result))) {
            throw new S21NwfBizException(NLCM0049E, tmp);
        }
        return result;
    }

    // -----------------------------------------------------------
    // getApvlTeamTransactions
    // -----------------------------------------------------------
    private List<Map<String, Object>> getApvlTeamTransactions(NLZC061001TokenObject token) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", token.getGlblCmpyCd());
        param.put("apvlHrchTpCd", token.getCondStr2());
        param.put("rtlWhCd", token.getCondStr1());
        param.put("apvlLimitAmt", token.getCondNum1());
        param.put("adjMerchandise", APVL_HIST_SRC_TP.PHYSICAL_INVENTORY_MERCHANDISE);
        param.put("adjParts", APVL_HIST_SRC_TP.PHYSICAL_INVENTORY_PARTS);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getApvlTeamTransactions", param);
    }

    /**
     * Get MDSE
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return MDSETMsg
     */
    private MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);

        return (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);
    }
}
