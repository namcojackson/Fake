/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1740;

import static business.blap.NWAL1740.constant.NWAL1740Constant.CHK_A;
import static business.blap.NWAL1740.constant.NWAL1740Constant.CHK_B;
import static business.blap.NWAL1740.constant.NWAL1740Constant.CHK_C;
import static business.blap.NWAL1740.constant.NWAL1740Constant.DB_FLAG_DELETE;
import static business.blap.NWAL1740.constant.NWAL1740Constant.DB_FLAG_INSERT;
import static business.blap.NWAL1740.constant.NWAL1740Constant.LISTBOX_ON_NO;
import static business.blap.NWAL1740.constant.NWAL1740Constant.LISTBOX_ON_YES;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1740.common.NWAL1740CommonLogic;
import business.db.DS_ORD_LINE_CATGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DISC_ALLOC_METH;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1740BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Fujitsu         M.Suzuki        Create          N/A
 * 2016/04/05   Fujitsu         M.Suzuki        Update          QC#6435
 * 2016/04/15   Fujitsu         M.Suzuki        Update          QC#6288
 *</pre>
 */
public class NWAL1740BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1740CMsg bizMsg = (NWAL1740CMsg) cMsg;
            NWAL1740SMsg glblMsg = (NWAL1740SMsg) sMsg;

            if ("NWAL1740_INIT".equals(screenAplID)) {
                doProcess_NWAL1740_INIT(bizMsg, glblMsg);

            } else if ("NWAL1740Scrn00_Add_Line".equals(screenAplID)) {
                doProcess_NWAL1740Scrn00_Add_Line(bizMsg, glblMsg);

            } else if ("NWAL1740Scrn00_Add_Mdse".equals(screenAplID)) {
                doProcess_NWAL1740Scrn00_Add_Mdse(bizMsg, glblMsg);

            } else if ("NWAL1740Scrn00_Add_Wh".equals(screenAplID)) {
                doProcess_NWAL1740Scrn00_Add_Wh(bizMsg, glblMsg);

            } else if ("NWAL1740Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWAL1740Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NWAL1740Scrn00_Remove_Line".equals(screenAplID)) {
                doProcess_NWAL1740Scrn00_Remove_Line(bizMsg, glblMsg);

            } else if ("NWAL1740Scrn00_Remove_Mdse".equals(screenAplID)) {
                doProcess_NWAL1740Scrn00_Remove_Mdse(bizMsg, glblMsg);

            } else if ("NWAL1740Scrn00_Remove_Wh".equals(screenAplID)) {
                doProcess_NWAL1740Scrn00_Remove_Wh(bizMsg, glblMsg);

            } else if ("NWAL1740Scrn00_Search".equals(screenAplID)) {
                doProcess_NWAL1740Scrn00_Search(bizMsg, glblMsg);

            } else if ("NWAL1740Scrn00_TAB_Line".equals(screenAplID)) {
                doProcess_NWAL1740Scrn00_TAB_Line(bizMsg, glblMsg);

            } else if ("NWAL1740Scrn00_TAB_Mdse".equals(screenAplID)) {
                doProcess_NWAL1740Scrn00_TAB_Mdse(bizMsg, glblMsg);

            } else if ("NWAL1740Scrn00_TAB_WH".equals(screenAplID)) {
                doProcess_NWAL1740Scrn00_TAB_WH(bizMsg, glblMsg);

            } else if ("NWAL1740_NWAL1130".equals(screenAplID)) {
                doProcess_NWAL1740_NWAL1130(bizMsg, glblMsg);

            } else if ("NWAL1740_NMAL6050".equals(screenAplID)) {
                doProcess_NWAL1740_NMAL6050(bizMsg, glblMsg);

            } else if ("NWAL1740Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL1740Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL1740_NMAL6050(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        int index = bizMsg.xxCellIdx.getValueInt();
        String dsOrdLineCatgCd = bizMsg.B.no(index).dsOrdLineCatgCd_B.getValue();
        DS_ORD_LINE_CATGTMsg lieCtTMsg = new DS_ORD_LINE_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(lieCtTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(lieCtTMsg.dsOrdLineCatgCd, dsOrdLineCatgCd);
        lieCtTMsg = (DS_ORD_LINE_CATGTMsg) EZDTBLAccessor.findByKey(lieCtTMsg);
        if (null != lieCtTMsg) {
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).dsOrdLineCatgDescTxt_B, lieCtTMsg.dsOrdLineCatgDescTxt);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1740_INIT(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        createPulldown(bizMsg);
    }

    private void createPulldown(NWAL1740CMsg bizMsg) {

        ZYPCodeDataUtil.createPulldownList(DISC_ALLOC_METH.class, bizMsg.discAllocMethCd_P, bizMsg.discAllocMethDescTxt_P);
        S21SsmEZDResult profitabilityPulldownList = NWAL1740Query.getInstance().getProfitabilityPulldownList();
        if (profitabilityPulldownList.isCodeNormal()) {
            List<Map> profitabilityList = (List<Map>) profitabilityPulldownList.getResultObject();
            NWAL1740CommonLogic.createPulldownList(bizMsg.ordProcNodePrflCd_P, bizMsg.ordProcNodePrflNm_P, profitabilityList, "ORD_PROC_NODE_PRFL_CD", "ORD_PROC_NODE_PRFL_NM");
        }

        //2016/04/04 S21_NA#6435 MOD Start --------------
        ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_P.no(0), ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoNm_P.no(0), LISTBOX_ON_NO);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_P.no(1), ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoNm_P.no(1), LISTBOX_ON_YES);
        //2016/04/04 S21_NA#6435 MOD End --------------
    }

    /**
     * Add_Line Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1740Scrn00_Add_Line(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        int idx = bizMsg.B.getValidCount();
        bizMsg.B.setValidCount(idx + 1);
        bizMsg.B.no(idx).xxRowId_B.setValue(DB_FLAG_INSERT);
        bizMsg.B.no(idx).actvFlg_B.setValue(ZYPConstant.CHKBOX_ON_Y);
        String slsDt = ZYPDateUtil.getSalesDate();
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).effFromDt_B, slsDt);
    }

    /**
     * Add_Mdse Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1740Scrn00_Add_Mdse(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        int idx = bizMsg.A.getValidCount();
        bizMsg.A.setValidCount(idx + 1);
        bizMsg.A.no(idx).xxRowId_A.setValue(DB_FLAG_INSERT);
        bizMsg.A.no(idx).flPrcCalcInclFlg_A.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.A.no(idx).repRevCalcInclFlg_AR.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.A.no(idx).repRevCalcInclFlg_AD.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.A.no(idx).repRevCalcInclFlg_AF.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.A.no(idx).redRepRevFlg_A.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.A.no(idx).dlrCrPrftInclFlg_A.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.A.no(idx).redCompAmtFlg_A.setValue(ZYPConstant.FLG_OFF_N);
    }

    /**
     * Add_Wh Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1740Scrn00_Add_Wh(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        int idx = bizMsg.C.getValidCount();
        bizMsg.C.setValidCount(idx + 1);
        bizMsg.C.no(idx).xxRowId_C.setValue(DB_FLAG_INSERT);
        bizMsg.C.no(idx).actvFlg_C.setValue(ZYPConstant.CHKBOX_ON_Y);
        String slsDt = ZYPDateUtil.getSalesDate();
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).effFromDt_C, slsDt);
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1740Scrn00_CMN_Clear(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        bizMsg.clear();
        glblMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);
        doProcess_NWAL1740_INIT(bizMsg, glblMsg);
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1740Scrn00_CMN_Submit(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        search(bizMsg, glblMsg);
    }

    /**
     * Remove_Line Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1740Scrn00_Remove_Line(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.B, CHK_B, ZYPConstant.FLG_ON_Y);
        for (int idx : selectRows) {
            String recordType = bizMsg.B.no(idx).xxRowId_B.getValue();
            if (ZYPCommonFunc.hasValue(recordType)) {
                if (DB_FLAG_INSERT.equals(recordType)) {
                    continue;
                }
            }

            for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                String glbalRecordType = glblMsg.B.no(j).xxRowId_B.getValue();
                if (recordType.equals(glbalRecordType)) {
                    glblMsg.B.no(j).xxRowId_B.setValue(DB_FLAG_DELETE);
                }
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.B, selectRows);
    }

    /**
     * Remove_Mdse Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1740Scrn00_Remove_Mdse(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.A, CHK_A, ZYPConstant.FLG_ON_Y);
        for (int idx : selectRows) {
            String recordType = bizMsg.A.no(idx).xxRowId_A.getValue();
            if (ZYPCommonFunc.hasValue(recordType)) {
                if (DB_FLAG_INSERT.equals(recordType)) {
                    continue;
                }
            }

            for (int j = 0; j < glblMsg.A.getValidCount(); j++) {
                String glbalRecordType = glblMsg.A.no(j).xxRowId_A.getValue();
                if (recordType.equals(glbalRecordType)) {
                    glblMsg.A.no(j).xxRowId_A.setValue(DB_FLAG_DELETE);
                }
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.A, selectRows);
    }

    /**
     * Remove_Wh Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1740Scrn00_Remove_Wh(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.C, CHK_C, ZYPConstant.FLG_ON_Y);
        for (int idx : selectRows) {
            String recordType = bizMsg.C.no(idx).xxRowId_C.getValue();
            if (ZYPCommonFunc.hasValue(recordType)) {
                if (DB_FLAG_INSERT.equals(recordType)) {
                    continue;
                }
            }

            for (int j = 0; j < glblMsg.C.getValidCount(); j++) {
                String glbalRecordType = glblMsg.C.no(j).xxRowId_C.getValue();
                if (recordType.equals(glbalRecordType)) {
                    glblMsg.C.no(j).xxRowId_C.setValue(DB_FLAG_DELETE);
                }
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.C, selectRows);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1740Scrn00_Search(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        // search
        search(bizMsg, glblMsg);
    }

    /**
     * TAB_Line Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1740Scrn00_TAB_Line(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        //doNothing
    }

    /**
     * TAB_Mdse Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1740Scrn00_TAB_Mdse(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        //doNothing
    }

    /**
     * TAB_WH Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1740Scrn00_TAB_WH(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        //doNothing
    }

    /**
     * NWAL1130 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1740_NWAL1130(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {

        int cellIdx = bizMsg.xxCellIdx.getValueInt();

        S21SsmEZDResult headerResult = NWAL1740Query.getInstance().getValuation(bizMsg);
        Map<String, Object> headerMap = (Map<String, Object>) headerResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(cellIdx).rtlWhCd_C, (String) headerMap.get("RTL_WH_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(cellIdx).rtlSwhCd_C, (String) headerMap.get("RTL_SWH_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(cellIdx).mdseInvtyCostPct_C, (BigDecimal) headerMap.get("MDSE_INVTY_COST_PCT"));
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);
        searchMdseTpRules(bizMsg, glblMsg);
        searchLineCatgRules(bizMsg, glblMsg);
        searchWHRules(bizMsg, glblMsg);

    }

    private void searchWHRules(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        S21SsmEZDResult ssmResult = NWAL1740Query.getInstance().getWHRules(bizMsg);
        //2016/04/04 S21_NA#6288 MOD Start ------------
//        if (ssmResult.getQueryResultCount() > glblMsg.C.length()) {
//            bizMsg.setMessageInfo(NZZM0001W);
//            glblMsg.C.setValidCount(glblMsg.C.length());
//        } else {
//            glblMsg.C.setValidCount(ssmResult.getQueryResultCount());
//        }
        glblMsg.C.setValidCount(ssmResult.getQueryResultCount());
        //2016/04/04 S21_NA#6288 MOD End --------------

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size(); i++) {

            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).rtlWhCd_C, (String) resultMap.get("RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).rtlWhNm_C, (String) resultMap.get("RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).rtlSwhCd_C, (String) resultMap.get("RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).rtlSwhNm_C, (String) resultMap.get("RTL_SWH_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).mdseInvtyCostPct_C, (BigDecimal) resultMap.get("MDSE_INVTY_COST_PCT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).actvFlg_C, (String) resultMap.get("ACTV_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).effFromDt_C, (String) resultMap.get("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).effThruDt_C, (String) resultMap.get("EFF_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).xxRowId_C, String.valueOf(i));
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).ezUpTime_C, (String) resultMap.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).ezUpTimeZone_C, (String) resultMap.get("EZUPTIMEZONE"));
            EZDMsg.copy(glblMsg.C, null, bizMsg.C, null);
        }
    }

    private void searchLineCatgRules(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        S21SsmEZDResult ssmResult = NWAL1740Query.getInstance().getLineCatgRules(bizMsg);
          //2016/04/04 S21_NA#6288 MOD Start ------------
//        if (ssmResult.getQueryResultCount() > glblMsg.B.length()) {
//            bizMsg.setMessageInfo(NZZM0001W);
//            glblMsg.B.setValidCount(glblMsg.B.length());
//        } else {
//            glblMsg.B.setValidCount(ssmResult.getQueryResultCount());
//        }
        glblMsg.B.setValidCount(ssmResult.getQueryResultCount());
          //2016/04/04 S21_NA#6288 MOD End --------------

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size(); i++) {

            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).dsOrdLineCatgCd_B, (String) resultMap.get("DS_ORD_LINE_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).dsOrdLineCatgNm_B, (String) resultMap.get("DS_ORD_LINE_CATG_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).dsOrdLineCatgDescTxt_B, (String) resultMap.get("DS_ORD_LINE_CATG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).actvFlg_B, (String) resultMap.get("ACTV_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).effFromDt_B, (String) resultMap.get("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).effThruDt_B, (String) resultMap.get("EFF_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).xxRowId_B, String.valueOf(i));
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).ezUpTime_B, (String) resultMap.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).ezUpTimeZone_B, (String) resultMap.get("EZUPTIMEZONE"));
            EZDMsg.copy(glblMsg.B, null, bizMsg.B, null);
        }
    }

    private void searchMdseTpRules(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        S21SsmEZDResult ssmResult = NWAL1740Query.getInstance().getMdseTpRules(bizMsg);
        //2016/04/04 S21_NA#6288 MOD Start ------------
//        if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
//            bizMsg.setMessageInfo(NZZM0001W);
//            glblMsg.A.setValidCount(glblMsg.A.length());
//        } else {
//            glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
//        }
        glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
        //2016/04/04 S21_NA#6288 MOD End --------------

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaMdseTpCd_A, (String) resultMap.get("COA_MDSE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaProjNm_A, (String) resultMap.get("COA_PROJ_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).flPrcCalcInclFlg_A, (String) resultMap.get("FL_PRC_CALC_INCL_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).repRevCalcInclFlg_AR, (String) resultMap.get("REP_REV_CALC_INCL_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).repRevCalcInclFlg_AD, (String) resultMap.get("DISC_MDSE_TP_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).repRevCalcInclFlg_AF, (String) resultMap.get("RED_FL_PRC_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).redRepRevFlg_A, (String) resultMap.get("RED_REP_REV_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dlrCrPrftInclFlg_A, (String) resultMap.get("DLR_CR_PRFT_INCL_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).redCompAmtFlg_A, (String) resultMap.get("RED_COMP_AMT_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).discAllocMethDescTxt_A, (String) resultMap.get("DISC_ALLOC_METH_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxRowId_A, String.valueOf(i));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ezUpTime_A, (String) resultMap.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ezUpTimeZone_A, (String) resultMap.get("EZUPTIMEZONE"));
            EZDMsg.copy(glblMsg.A, null, bizMsg.A, null);
        }
    }

}
