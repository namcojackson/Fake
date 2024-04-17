/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6740;

import static business.blap.NMAL6740.constant.NMAL6740Constant.MESSAGE_KIND_WAR;
import static business.blap.NMAL6740.constant.NMAL6740Constant.NMAM8186E;
import static business.blap.NMAL6740.constant.NMAL6740Constant.NMAM8333I;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL6740.common.NMAL6740CommonLogic;
import business.blap.NMAL6740.constant.NMAL6740Constant;
import business.db.COA_CHTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_TAX_CALC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TAX_GRP_EXEM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TAX_PRNT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/20   CUSA            Fujitsu         Create          N/A
 * 2015/10/09   Fujitsu         C.Tanaka        Update          CSA
 * 2016/05/05   SRAA            Y.Chen          Update          QC#6382
 * 2016/09/09   SRAA            Y.Chen          Update          QC#9448
 * 2016/11/02   Fujitsu         M.Ohno          Update          S21_NA#2680
 * 2018/04/16   Fujitsu         M.Ohno          Update          QC#24635
 * 2018/04/24   Fujitsu         M.Ohno          Update          QC#24635-2
 * 2018/08/07   Fujitsu         S.Ohki          Update          QC#27222
 *</pre>
 */
public class NMAL6740BL02 extends S21BusinessHandler {

    /**
     * Method name: doProcess <dd>The method explanation: Call each
     * process by screen id. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        NMAL6740CMsg bizMsg = (NMAL6740CMsg) cMsg;
        NMAL6740SMsg sharedMsg = (NMAL6740SMsg) sMsg;
        super.preDoProcess(bizMsg, sharedMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6740_INIT".equals(screenAplID)) {
                doProcess_NMAL6740_INIT(bizMsg, sharedMsg);
            } else if ("NMAL6740Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL6740Scrn00_CMN_Reset(bizMsg, sharedMsg);
            } else if ("NMAL6740Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL6740Scrn00_CMN_Submit(bizMsg, sharedMsg);
            } else if ("NMAL6740Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL6740Scrn00_Search(bizMsg, sharedMsg);
            // QC#6382
            } else if ("NMAL6740_NMAL2550".equals(screenAplID)) {
                doProcess_NMAL6740_NMAL2550(bizMsg, sharedMsg);
            } else if ("NMAL6740Scrn00_GetCoaChNm".equals(screenAplID)) {
                doProcess_NMAL6740Scrn00_GetCoaChNm(bizMsg, sharedMsg);
            } else if ("NMAL6740Scrn00_GetInterCompanyNm".equals(screenAplID)) {
// QC#9448
//                doProcess_NMAL6740Scrn00_GetInterCompanyNm(bizMsg, sharedMsg);

            } else if ("NMAL6740Scrn00_OpenWin_Coa".equals(screenAplID)) {
                doProcess_NMAL6740Scrn00_OpenWin_Coa(bizMsg, sharedMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(bizMsg, sharedMsg);
        }
    }

    /**
     * Method name: doProcess_NMAL6740_INIT <dd>The method
     * explanation: Initializing. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NMAL6740_INIT(NMAL6740CMsg cMsg, NMAL6740SMsg sMsg) {

    	 // Del Start 2018/08/07 QC#27222
//        ZYPCodeDataUtil.createPulldownList(DS_CUST_TAX_CALC.class, cMsg.dsCustTaxCalcCd_L, cMsg.dsCustTaxCalcNm_L);
//        ZYPCodeDataUtil.createPulldownList(DS_TAX_PRNT_TP.class, cMsg.dsTaxPrntTpCd_L, cMsg.dsTaxPrntTpNm_L);
        // Del End 2018/08/07 QC#27222
        ZYPCodeDataUtil.createPulldownList(DS_TAX_GRP_EXEM.class, cMsg.dsTaxGrpExemCd_L, cMsg.dsTaxGrpExemNm_L);

        doProcess_NMAL6740Scrn00_Search(cMsg, sMsg);

    }

    /**
     * Method name: doProcess_NMAL6740Scrn00_CMN_Reset <dd>The method
     * explanation: Reset values. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6740Scrn00_CMN_Reset(NMAL6740CMsg cMsg, NMAL6740SMsg sMsg) {
        doProcess_NMAL6740_INIT(cMsg, sMsg);
    }

    /**
     * Method name: doProcess_NMAL6740Scrn00_CMN_Submit <dd>The method
     * explanation: Reset values. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6740Scrn00_CMN_Submit(NMAL6740CMsg cMsg, NMAL6740SMsg sMsg) {
        if (MESSAGE_KIND_WAR.equals(cMsg.getMessageKind())) {
            return;
        }
        if (NMAM8333I.equals(cMsg.getMessageCode())) {
            return;
        }
        doProcess_NMAL6740Scrn00_Search(cMsg, sMsg);
    }

    /**
     * Method name: doProcess_NMAL6740Scrn00_Search <dd>The method
     * explanation: Serch Information. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NMAL6740Scrn00_Search(NMAL6740CMsg cMsg, NMAL6740SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd)) {
            search(cMsg, sMsg);
        }
    }
    
    // QC#6832
    private void doProcess_NMAL6740_NMAL2550(NMAL6740CMsg cMsg, NMAL6740SMsg sMsg) {
        if ("OpenWin_Coa".equals(cMsg.xxScrEventNm.getValue())) {
            // 2018/04/16 QC#24635 del start
//            if (ZYPCommonFunc.hasValue(cMsg.coaChCd)) {
//                doProcess_NMAL6740Scrn00_GetCoaChNm(cMsg, sMsg);
//            }
            // 2018/04/16 QC#24635 del end
            // 2018/04/16 QC#24635 add start
            NMAL6740CommonLogic.createExpenseAccount(cMsg, getGlobalCompanyCode(), true);
            // 2018/04/16 QC#24635 add end
        } else if ("OpenWin_Coa2".equals(cMsg.xxScrEventNm.getValue())) {
// QC#9448
//            if (ZYPCommonFunc.hasValue(cMsg.coaAfflCd)) {
//                doProcess_NMAL6740Scrn00_GetInterCompanyNm(cMsg, sMsg);
//            }
        }
    }

    private void doProcess_NMAL6740Scrn00_GetCoaChNm(NMAL6740CMsg cMsg, NMAL6740SMsg sMsg) {
        cMsg.coaChNm.clear();
        COA_CHTMsg coaChTMsg = findCoaCh(getGlobalCompanyCode(), cMsg.coaChCd.getValue());
        if (coaChTMsg == null) {
            cMsg.coaChCd.setErrorInfo(1, NMAM8186E, new String[] {"GL Sales Channel" });
            return;
        }
        ZYPEZDItemValueSetter.setValue(cMsg.coaChNm, coaChTMsg.coaChNm);
    }
    
// QC#9448
//    private void doProcess_NMAL6740Scrn00_GetInterCompanyNm(NMAL6740CMsg cMsg, NMAL6740SMsg sMsg) {
//        cMsg.coaAfflNm.clear();
//        COA_AFFLTMsg coaAfflTMsg = findCoaAffl(getGlobalCompanyCode(), cMsg.coaAfflCd.getValue());
//        if (coaAfflTMsg == null) {
//            cMsg.coaAfflCd.setErrorInfo(1, NMAM8186E, new String[] {"GL Intercompany Code" });
//            return;
//        }
//        ZYPEZDItemValueSetter.setValue(cMsg.coaAfflNm, coaAfflTMsg.coaAfflNm);
//    }

    private COA_CHTMsg findCoaCh(String glblCmpyCd, String coaChCd) {
        COA_CHTMsg prmTMsg = new COA_CHTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.coaChCd, coaChCd);
        return (COA_CHTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

// QC#9448
//    private COA_AFFLTMsg findCoaAffl(String glblCmpyCd, String coaAfflCd) {
//        COA_AFFLTMsg prmTMsg = new COA_AFFLTMsg();
//        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(prmTMsg.coaAfflCd, coaAfflCd);
//        return (COA_AFFLTMsg) S21CacheTBLAccessor.findByKey(prmTMsg);
//    }

    @SuppressWarnings("unchecked")
    private void search(NMAL6740CMsg cMsg, NMAL6740SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String shipToCustCd = cMsg.shipToCustCd.getValue();

        S21SsmEZDResult res = NMAL6740Query.getInstance().getShipToCustInfoByShipToCustCd(glblCmpyCd, shipToCustCd);
        if (res.isCodeNormal()) {
            List list = (List) res.getResultObject();
            Map map = (Map) list.get(0);
            setHeaderShipToInfo(cMsg, map);
        } else {
            cMsg.setMessageInfo(NMAL6740Constant.ZZZM9001E);
            return;
        }
    }

    @SuppressWarnings("unchecked")
    private void setHeaderShipToInfo(NMAL6740CMsg cMsg, Map map) {

        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime, (String) map.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone, (String) map.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustPk, (BigDecimal) map.get("SHIP_TO_CUST_PK"));
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd, (String) map.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm, (String) map.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.xxAllLineAddr, (String) map.get("ALL_ADDR"));
        ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr, (String) map.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(cMsg.stCd, (String) map.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.postCd, (String) map.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.locNum, (String) map.get("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.coaChCd, (String) map.get("COA_CH_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.coaAfflCd, (String) map.get("COA_AFFL_CD"));
        // QC#6382
        ZYPEZDItemValueSetter.setValue(cMsg.coaChNm, (String) map.get("COA_CH_NM"));
        // QC#9448
        // ZYPEZDItemValueSetter.setValue(cMsg.coaAfflNm, (String) map.get("COA_AFFL_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.coaCmpyCd, (String) map.get("COA_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.coaBrCd, (String) map.get("COA_BR_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.coaCcCd, (String) map.get("COA_CC_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.coaAcctCd, (String) map.get("COA_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.coaProdCd, (String) map.get("COA_PROD_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.coaProjCd, (String) map.get("COA_PROJ_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.coaExtnCd, (String) map.get("COA_EXTN_CD"));
        // Del Start 2018/08/07 QC#27222
//        ZYPEZDItemValueSetter.setValue(cMsg.dsCustTaxCd, (String) map.get("TAX_CD"));
//        ZYPEZDItemValueSetter.setValue(cMsg.dsCustTaxCalcCd, (String) map.get("TAX_CALC_CD"));
//        ZYPEZDItemValueSetter.setValue(cMsg.dsTaxExemFlg, (String) map.get("DS_TAX_EXEM_FLG"));
//        ZYPEZDItemValueSetter.setValue(cMsg.dsExemExprDt, (String) map.get("DS_EXEM_EXPR_DT"));
//        ZYPEZDItemValueSetter.setValue(cMsg.dsTaxPrntTpCd, (String) map.get("DS_TAX_PRNT_TP_CD"));
        // Del End 2018/08/07 QC#27222
        ZYPEZDItemValueSetter.setValue(cMsg.dsTaxGrpExemCd, (String) map.get("DS_TAX_GRP_EXEM_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.bigDealNum, (String) map.get("BIG_DEAL_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.coaChCd_BK, (String) map.get("COA_CH_CD_ACCT"));
        // QC#9448
        // ZYPEZDItemValueSetter.setValue(cMsg.coaAfflCd_BK, (String) map.get("COA_AFFL_CD_ACCT"));
        // Add Start 2016/11/02 M.Ohno S21_NA#2680
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistCratTs, (String) map.get("XX_REC_HIST_CRAT_TS"));
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistCratByNm, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM")));
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistUpdTs, (String) map.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistUpdByNm, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM")));
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistTblNm, "SHIP_TO_CUST");
        // Add End   2016/11/02 M.Ohno S21_NA#2680

        // 2018/04/16 QC#24635 add start
        NMAL6740CommonLogic.createExpenseAccount(cMsg, getGlobalCompanyCode(), true);
        // 2018/04/16 QC#24635 add end
    }

	//2018/04/24 QC#24635-2 add start
    /**
     * Method name: doProcess_NMAL6740Scrn00_OpenWin_Coa <dd>The method
     * explanation: Initializing. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NMAL6740Scrn00_OpenWin_Coa(NMAL6740CMsg cMsg, NMAL6740SMsg sMsg) {
        if (!NMAL6740CommonLogic.inputCheckForExpenseAccount(cMsg)) {
            return;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.xxScrItem130Txt)) {
            NMAL6740CommonLogic.createExpenseAccount(cMsg, getGlobalCompanyCode(), false);
        }

    }
    //2018/04/24 QC#24635-2 add end
}
