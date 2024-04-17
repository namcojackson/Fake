/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1200;

import static business.blap.NPAL1200.constant.NPAL1200Constant.BUSINESS_APPLICATION_ID;
import static business.blap.NPAL1200.constant.NPAL1200Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.DB_PARAM_PRCH_REQ_REC_TP_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.MDSE_ITEM_CLS_TP_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.MDSE_ITEM_CLS_TP_NM;
import static business.blap.NPAL1200.constant.NPAL1200Constant.MODE_ADD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.MODE_ADD_CANCEL;
import static business.blap.NPAL1200.constant.NPAL1200Constant.MODE_DELETE;
import static business.blap.NPAL1200.constant.NPAL1200Constant.MODE_EDIT;
import static business.blap.NPAL1200.constant.NPAL1200Constant.NPAL1200_WH_CATG_CD_LIST;
import static business.blap.NPAL1200.constant.NPAL1200Constant.NPAM0224E;
import static business.blap.NPAL1200.constant.NPAL1200Constant.NPAM1199E;
import static business.blap.NPAL1200.constant.NPAL1200Constant.NPAM1349E;
import static business.blap.NPAL1200.constant.NPAL1200Constant.NPAM1350E;
import static business.blap.NPAL1200.constant.NPAL1200Constant.PLAN_TYPE_CD_MNX_WH;
import static business.blap.NPAL1200.constant.NPAL1200Constant.PLAN_TYPE_CD_S21_WH;
import static business.blap.NPAL1200.constant.NPAL1200Constant.PLAN_TYPE_NM_MNX_WH;
import static business.blap.NPAL1200.constant.NPAL1200Constant.PLAN_TYPE_NM_S21_WH;
import static business.blap.NPAL1200.constant.NPAL1200Constant.PRCH_REQ_TP_CD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.PRCH_REQ_TP_DESC_TXT;
import static business.blap.NPAL1200.constant.NPAL1200Constant.SRC_ZN_CD;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1200.common.NPAL1200CommonLogic;
import business.db.RTL_WHTMsg;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1200 Insourcing Planning Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   CITS       Yasushi Nomura        Create          N/A
 * 08/22/2016   CITS       S.Endo                Update          QC#12612/QC#12621
 * 08/30/2016   CITS       S.Endo                Update          QC#13726
 * 03/01/2023   CITS       R.Kurahashi           Update          QC#61128
 *</pre>
 */
public class NPAL1200BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (("NPAL1200_INIT".equals(screenAplID)) || ("NPAL1200Scrn00_CMN_Clear".equals(screenAplID))) {
                doProcess_NPAL1200_INIT((NPAL1200CMsg) cMsg, (NPAL1200SMsg) sMsg);
            } else if (("NPAL1200Scrn00_Search".equals(screenAplID)) || ("NPAL1200Scrn00_CMN_Reset".equals(screenAplID))) {
                doProcess_NPAL1200Scrn00_Search((NPAL1200CMsg) cMsg, (NPAL1200SMsg) sMsg);
            } else if ("NPAL1200Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NPAL1200Scrn00_PageNext((NPAL1200CMsg) cMsg, (NPAL1200SMsg) sMsg);
            } else if ("NPAL1200Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NPAL1200Scrn00_PagePrev((NPAL1200CMsg) cMsg, (NPAL1200SMsg) sMsg);
            } else if ("NPAL1200Scrn00_OnChange_SearchOption".equals(screenAplID)) {
                doProcess_OnChange_SearchOption((NPAL1200CMsg) cMsg, (NPAL1200SMsg) sMsg);
            } else if (("NPAL1200Scrn00_SearchFromWHNameH".equals(screenAplID)) || ("NPAL1200Scrn00_SearchFromWHNameD".equals(screenAplID)) || ("NPAL1200Scrn00_SearchToWHNameH".equals(screenAplID))
                    || ("NPAL1200Scrn00_SearchToWHNameD".equals(screenAplID))) {
                doProcess_SearchWHName((NPAL1200CMsg) cMsg, (NPAL1200SMsg) sMsg, screenAplID);
            } else if ("NPAL1200Scrn00_AddDetailLine".equals(screenAplID)) {
                doProcess_AddDetailLine((NPAL1200CMsg) cMsg, (NPAL1200SMsg) sMsg);
            } else if ("NPAL1200Scrn00_EditDetailLine".equals(screenAplID)) {
                doProcess_EditDetailLine((NPAL1200CMsg) cMsg, (NPAL1200SMsg) sMsg);
            } else if ("NPAL1200Scrn00_CancelDetailLine".equals(screenAplID)) {
                doProcess_CancelDetailLine((NPAL1200CMsg) cMsg, (NPAL1200SMsg) sMsg);
            } else if (("NPAL1200Scrn00_OpenWinFromWarehouseH".equals(screenAplID)) || ("NPAL1200Scrn00_OpenWinFromWarehouseD".equals(screenAplID)) || ("NPAL1200Scrn00_OpenWinToWarehouseH".equals(screenAplID))
                    || ("NPAL1200Scrn00_OpenWinToWarehouseD".equals(screenAplID))) {
                doProcess_OpenWinToWarehouse((NPAL1200CMsg) cMsg, (NPAL1200SMsg) sMsg);
            } else if ("NPAL1200_NPAL1010".equals(screenAplID)) {
                doProcess_NPAL1010((NPAL1200CMsg) cMsg, (NPAL1200SMsg) sMsg);
            } else if ("NPAL1200Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NPAL1200Scrn00_SearchFromSubmit((NPAL1200CMsg) cMsg, (NPAL1200SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT
     * @param cMsg NPAL1200CMsg
     * @param sMsg NPAL1200SMsg
     */
    private void doProcess_NPAL1200_INIT(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(sMsg.C);
        sMsg.clear();
        cMsg.clear();

        // get pulldown data
        getPulldownListData(sMsg);
        // get varchar const data
        ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_XX, ZYPCodeDataUtil.getVarCharConstValue(NPAL1200_WH_CATG_CD_LIST, getGlobalCompanyCode()));

        // create pulldown list
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            // from zone
            ZYPEZDItemValueSetter.setValue(cMsg.srcZnCd_CF.no(i), sMsg.B.no(i).srcZnCd_XX);
            ZYPEZDItemValueSetter.setValue(cMsg.srcZnCd_DF.no(i), sMsg.B.no(i).srcZnCd_XX);
            // to zone
            ZYPEZDItemValueSetter.setValue(cMsg.srcZnCd_CT.no(i), sMsg.B.no(i).srcZnCd_XX);
            ZYPEZDItemValueSetter.setValue(cMsg.srcZnCd_DT.no(i), sMsg.B.no(i).srcZnCd_XX);
        }
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            // Item class
            ZYPEZDItemValueSetter.setValue(cMsg.mdseItemClsTpCd_CH.no(i), sMsg.C.no(i).mdseItemClsTpCd_XX);
            ZYPEZDItemValueSetter.setValue(cMsg.mdseItemClsTpDescTxt_DH.no(i), sMsg.C.no(i).mdseItemClsTpDescTxt_XX);
            ZYPEZDItemValueSetter.setValue(cMsg.mdseItemClsTpCd_CD.no(i), sMsg.C.no(i).mdseItemClsTpCd_XX);
            ZYPEZDItemValueSetter.setValue(cMsg.mdseItemClsTpDescTxt_DD.no(i), sMsg.C.no(i).mdseItemClsTpDescTxt_XX);
        }
        // QC#61128 Add Start
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            // Tech Request
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_CF.no(i), sMsg.D.no(i).prchReqTpCd_XX);
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpDescTxt_DF.no(i), sMsg.D.no(i).prchReqTpDescTxt_XX);
        }
        
        // Plan Type
        ZYPEZDItemValueSetter.setValue(cMsg.xxTpCd_CF.no(0), PLAN_TYPE_CD_S21_WH);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTpNm_DF.no(0), PLAN_TYPE_NM_S21_WH);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTpCd_CF.no(1), PLAN_TYPE_CD_MNX_WH);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTpNm_DF.no(1), PLAN_TYPE_NM_MNX_WH);
        // QC# 61128 Add End

        NPAL1200CommonLogic.createPullDownSearchOption(cMsg, getGlobalCompanyCode());
    }

    /**
     * <pre>
     * Serch Event
     * </pre>
     * @param cMsg NPAL1200CMsg
     * @param sMsg NPAL1200SMsg
     */
    private void doProcess_NPAL1200Scrn00_Search(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg) {
        NPAL1200CommonLogic.searchDetail(cMsg, sMsg, getGlobalCompanyCode(), false, false);
    }

    /**
     * <pre>
     * PagePrev Event
     * </pre>
     * @param cMsg NPAL1200CMsg
     * @param sMsg NPAL1200SMsg
     */
    private void doProcess_NPAL1200Scrn00_PagePrev(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg) {
        NPAL1200CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length());
        NPAL1200CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * <pre>
     * PageNext Event
     * </pre>
     * @param cMsg NPAL1200CMsg
     * @param sMsg NPAL1200SMsg
     */
    private void doProcess_NPAL1200Scrn00_PageNext(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg) {
        NPAL1200CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length());
        NPAL1200CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * Pulldown onChange Event
     * @param cMsg NPAL1200CMsg
     * @param sMsg NPAL1200SMsg
     */
    private void doProcess_OnChange_SearchOption(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_SE)) {
            NPAL1200CommonLogic.callNszc0330forSearchSearchOption(cMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());

        }
    }

    private void getPulldownListData(NPAL1200SMsg sMsg) {
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.C);
        // QC#61128 Add Start
        ZYPTableUtil.clear(sMsg.D);
        // QC#61128 Add End

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());

        // Zone
        // Execute
        S21SsmEZDResult result = NPAL1200Query.getInstance().getZoneList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            int i = 0;
            for (; i < resultList.size(); i++) {
                Map recode = resultList.get(i);

                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).srcZnCd_XX, (String) recode.get(SRC_ZN_CD));

                if (i >= sMsg.B.length()) {
                    break;
                }
            }
            sMsg.B.setValidCount(i);
        }
        // ItemClass
        result = NPAL1200Query.getInstance().getItemClassList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            int i = 0;
            for (; i < resultList.size(); i++) {
                Map recode = resultList.get(i);

                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).mdseItemClsTpCd_XX, (String) recode.get(MDSE_ITEM_CLS_TP_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).mdseItemClsTpDescTxt_XX, (String) recode.get(MDSE_ITEM_CLS_TP_NM));

                if (i >= sMsg.C.length()) {
                    break;
                }
            }
            sMsg.C.setValidCount(i);
        }
        
        // QC#61128 Add Start
        // Tech Request
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.TECH_REQUEST);
        result = NPAL1200Query.getInstance().getTechRequestList(ssmParam);
        
        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            int i = 0;
            for (; i < resultList.size(); i++) {
                Map recode = resultList.get(i);

                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).prchReqTpCd_XX, (String) recode.get(PRCH_REQ_TP_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).prchReqTpDescTxt_XX, (String) recode.get(PRCH_REQ_TP_DESC_TXT));

                if (i >= sMsg.D.length()) {
                    break;
                }
            }
            sMsg.D.setValidCount(i);
        }
        // QC#61128 Add End
    }

    private void doProcess_SearchWHName(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg, String screenAplID) {
        if ("NPAL1200Scrn00_SearchFromWHNameH".equals(screenAplID)) {
            String rtlWhNm = NPAL1200CommonLogic.getRtlWhName(cMsg.rtlWhCd_HF.getValue(), getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_HF, rtlWhNm);
            if (rtlWhNm == null) {
                cMsg.rtlWhCd_HF.setErrorInfo(1, NPAM0224E, new String[] {cMsg.rtlWhCd_HF.getValue() });
            }
        } else if ("NPAL1200Scrn00_SearchToWHNameH".equals(screenAplID)) {
            String rtlWhNm = NPAL1200CommonLogic.getRtlWhName(cMsg.rtlWhCd_HT.getValue(), getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_HT, rtlWhNm);
            if (rtlWhNm == null) {
                cMsg.rtlWhCd_HT.setErrorInfo(1, NPAM0224E, new String[] {cMsg.rtlWhCd_HT.getValue() });
            }
        } else if ("NPAL1200Scrn00_SearchFromWHNameD".equals(screenAplID)) {
            String rtlWhNm = NPAL1200CommonLogic.getRtlWhName(cMsg.rtlWhCd_DF.getValue(), getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_DF, rtlWhNm);
            if (rtlWhNm == null) {
                cMsg.rtlWhCd_DF.setErrorInfo(1, NPAM0224E, new String[] {cMsg.rtlWhCd_DF.getValue() });
            }
        } else if ("NPAL1200Scrn00_SearchToWHNameD".equals(screenAplID)) {
            String rtlWhNm = NPAL1200CommonLogic.getRtlWhName(cMsg.rtlWhCd_DT.getValue(), getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_DT, rtlWhNm);
            if (rtlWhNm == null) {
                cMsg.rtlWhCd_DT.setErrorInfo(1, NPAM0224E, new String[] {cMsg.rtlWhCd_DT.getValue() });
            }
        }
    }

    private void doProcess_AddDetailLine(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg) {
        NPAL1200CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        if (sMsg.A.length() < (sMsg.A.getValidCount() + 1)) {
            cMsg.setMessageInfo(NPAM1199E);
            return;
        }
        String glblCmpyCd = getGlobalCompanyCode();
        String[] catList;
        if (ZYPCommonFunc.hasValue(cMsg.varCharConstVal_XX)) {
            catList = cMsg.varCharConstVal_XX.getValue().split(",");
        } else {
            catList = new String[0];
        }
        // get From WH
        RTL_WHTMsg rtlWhFrom = NPAL1200CommonLogic.getRtlWh(cMsg.rtlWhCd_DF.getValue(), ZYPDateUtil.getSalesDate(), glblCmpyCd);
        if (rtlWhFrom == null) {
            cMsg.rtlWhCd_DF.setErrorInfo(1, NPAM0224E, new String[] {"From Warehouse Code" });
            return;
        } else {
            boolean err = true;
            for (int i = 0; i < catList.length; i++) {
                if (catList[i].equals(rtlWhFrom.rtlWhCatgCd.getValue())) {
                    err = false;
                    break;
                }
            }
            if (err) {
                cMsg.rtlWhCd_DF.setErrorInfo(1, NPAM1349E, new String[] {"Warehouse Code" });
                return;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_DF, rtlWhFrom.rtlWhNm);
            // QC#61128 Add Start
            ZYPEZDItemValueSetter.setValue(cMsg.whOwnrCd_DF, rtlWhFrom.whOwnrCd);
            // QC#61128 Add End
        }
        // get To WH
        RTL_WHTMsg rtlWhTo = NPAL1200CommonLogic.getRtlWh(cMsg.rtlWhCd_DT.getValue(), ZYPDateUtil.getSalesDate(), glblCmpyCd);
        if (rtlWhTo == null) {
            cMsg.rtlWhCd_DT.setErrorInfo(1, NPAM0224E, new String[] {"To Warehouse Code" });
            return;
        } else {
            boolean err = true;
            for (int i = 0; i < catList.length; i++) {
                if (catList[i].equals(rtlWhTo.rtlWhCatgCd.getValue())) {
                    err = false;
                    break;
                }
            }
            if (err) {
                cMsg.rtlWhCd_DT.setErrorInfo(1, NPAM1349E, new String[] {"Warehouse Code" });
                return;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_DT, rtlWhTo.rtlWhNm);
        }
        // QC#61128 Mod Start
        //if (NPAL1200CommonLogic.checkInsrcZone(cMsg.rtlWhCd_DF.getValue(), cMsg.rtlWhCd_DT.getValue(), cMsg.mdseItemClsTpCd_SD.getValue(), glblCmpyCd)) {
        if (WH_OWNR.MNX.equals(cMsg.whOwnrCd_DF.getValue()) && NPAL1200CommonLogic.checkInsrcZone(cMsg.rtlWhCd_DF.getValue(), cMsg.rtlWhCd_DT.getValue(), cMsg.mdseItemClsTpCd_SD.getValue(), glblCmpyCd)) {
         // QC#61128 Mod End
            cMsg.rtlWhCd_DF.setErrorInfo(1, NPAM1350E);
            return;
        }
        
        boolean addRowFlg = true;
        // QC#61128 Del Start
        //for (int i = 0; i < sMsg.A.getValidCount(); i++) {
        //    if (sMsg.A.no(i).xxNum_M1.getValueInt() != MODE_ADD) {
        //        if (sMsg.A.no(i).fromRtlWhCd_D1.getValue().equals(cMsg.rtlWhCd_DF.getValue())//
        //                && sMsg.A.no(i).toRtlWhCd_D1.getValue().equals(cMsg.rtlWhCd_DT.getValue()) && sMsg.A.no(i).mdseItemClsTpCd_SE.getValue().equals(cMsg.mdseItemClsTpCd_SD.getValue())) {
        //            sMsg.A.no(i).xxNum_M1.setValue(MODE_ADD);
        //            sMsg.A.no(i).xxNum_M2.setValue(MODE_EDIT);
        //            sMsg.A.no(i).xxChkBox_D1.clear();
        //            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseItemClsTpCd_SE, cMsg.mdseItemClsTpCd_SD);
        //            addRowFlg = false;
        //            break;
        //        }
        //        continue;
        //    }
        //    if ((sMsg.A.no(i).fromRtlWhCd_D1.getValue().equals(cMsg.rtlWhCd_DF.getValue())) && (sMsg.A.no(i).toRtlWhCd_D1.getValue().equals(cMsg.rtlWhCd_DT.getValue()))) {
        //        if (ZYPCommonFunc.hasValue(cMsg.mdseItemClsTpCd_SD)) {
        //            if (cMsg.mdseItemClsTpCd_SD.getValue().equals(sMsg.A.no(i).mdseItemClsTpCd_SE.getValue())) {
        //                cMsg.rtlWhCd_DF.setErrorInfo(1, NPAM1350E);
        //                return;
        //            }
        //        } else {
        //            //if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseItemClsTpCd_SE)) {
        //                cMsg.rtlWhCd_DF.setErrorInfo(1, NPAM1350E);
        //                return;
        //            //}
        //        }
        //    }
        //}
        // QC#61128 Del End
        if (addRowFlg) {
            // add row
            int index = sMsg.A.getValidCount();
            sMsg.A.setValidCount(index + 1);
            sMsg.A.no(index).clear();
            sMsg.A.no(index).xxNum_ID.setValue(index);
            sMsg.A.no(index).xxNum_M1.setValue(MODE_ADD);
            sMsg.A.no(index).xxNum_M2.setValue(MODE_EDIT);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).fromSrcZnCd_D1, rtlWhFrom.srcZnCd);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).fromRtlWhCd_D1, cMsg.rtlWhCd_DF);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rtlWhNm_D1, rtlWhFrom.rtlWhNm);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).toSrcZnCd_D1, rtlWhTo.srcZnCd);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).toRtlWhCd_D1, cMsg.rtlWhCd_DT);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rtlWhNm_D2, rtlWhTo.rtlWhNm);
            // item class pulldown
            for (int i = 0; i < sMsg.C.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseItemClsTpCd_CE.no(i), sMsg.C.no(i).mdseItemClsTpCd_XX);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseItemClsTpDescTxt_DE.no(i), sMsg.C.no(i).mdseItemClsTpDescTxt_XX);
            }
            // QC#61128 Add Start
            for (int k = 0; k < sMsg.D.getValidCount(); k++) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqTpCd_CE.no(k), sMsg.D.no(k).prchReqTpCd_XX);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqTpDescTxt_DE.no(k), sMsg.D.no(k).prchReqTpDescTxt_XX);
            }
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).whOwnrCd_D1, rtlWhFrom.whOwnrCd);
            // QC#61128 Add End
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseItemClsTpCd_SE, cMsg.mdseItemClsTpCd_SD);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).insrcItemPrcThrhdAmt_D1, BigDecimal.ZERO);
            sMsg.A.no(index).insrcRnkSortNum_D1.clear();
            sMsg.A.no(index).insrcEnblFlg_CD.no(0).setValue(ZYPConstant.FLG_ON_Y);
            sMsg.A.no(index).insrcEnblFlg_DD.no(0).setValue(ZYPConstant.FLG_ON_Y);
            sMsg.A.no(index).insrcEnblFlg_CD.no(1).setValue(ZYPConstant.FLG_OFF_N);
            sMsg.A.no(index).insrcEnblFlg_DD.no(1).setValue(ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).insrcEnblFlg_SD, ZYPConstant.FLG_ON_Y);

            // clear
            cMsg.mdseItemClsTpCd_SD.clear();

            cMsg.xxPageShowFromNum.setValue(-1);
            cMsg.xxNum_MD.setValue(MODE_ADD);
        }
        NPAL1200CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    private void doProcess_EditDetailLine(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg) {
        NPAL1200CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_D1.getValue())) {
                int index = sMsg.A.no(i).xxNum_ID.getValueInt();
                sMsg.A.no(index).xxChkBox_D1.setValue(ZYPConstant.FLG_ON_Y);
                sMsg.A.no(index).xxNum_M1.setValue(MODE_EDIT);
                sMsg.A.no(index).xxNum_M2.setValue(MODE_EDIT);
                cMsg.xxNum_MD.setValue(MODE_EDIT);
            }
        }
        NPAL1200CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    private void doProcess_CancelDetailLine(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg) {
        NPAL1200CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_D1.getValue())) {
                int index = sMsg.A.no(i).xxNum_ID.getValueInt();
                if (sMsg.A.no(index).xxNum_M1.getValueInt() == MODE_ADD) {
                    sMsg.A.no(index).xxChkBox_D1.clear();
                    sMsg.A.no(index).xxNum_M1.setValue(MODE_ADD_CANCEL);
                    sMsg.A.no(index).xxNum_M2.setValue(MODE_DELETE);
                } else {
                    sMsg.A.no(index).xxNum_M1.setValue(MODE_DELETE);
                    sMsg.A.no(index).xxNum_M2.setValue(MODE_DELETE);
                    cMsg.xxNum_MD.setValue(MODE_DELETE);
                }
            }
        }
        NPAL1200CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    private void doProcess_OpenWinToWarehouse(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String type = NMXC100001EnableWH.getLocRoleTpForPopup(glblCmpyCd, BUSINESS_APPLICATION_ID, cMsg.xxPopPrm.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxPopPrm, type);
    }

    private void doProcess_NPAL1010(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg) {
        RTL_WHTMsg tMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.ezCancelFlag, "0");
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, cMsg.xxPopPrm);
        tMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);
        boolean err = true;
        if (tMsg != null) {
            String[] catList;
            if (ZYPCommonFunc.hasValue(cMsg.varCharConstVal_XX)) {
                catList = cMsg.varCharConstVal_XX.getValue().split(",");
            } else {
                catList = new String[0];
            }
            for (int i = 0; i < catList.length; i++) {
                if (catList[i].equals(tMsg.rtlWhCatgCd.getValue())) {
                    err = false;
                    break;
                }
            }
            if (!err) {
                if ("OpenWinFromWarehouseH".equals(cMsg.eventNm.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_HF, tMsg.rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_HF, tMsg.rtlWhNm);
                } else if ("OpenWinToWarehouseH".equals(cMsg.eventNm.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_HT, tMsg.rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_HT, tMsg.rtlWhNm);
                } else if ("OpenWinFromWarehouseD".equals(cMsg.eventNm.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_DF, tMsg.rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_DF, tMsg.rtlWhNm);
                } else if ("OpenWinToWarehouseD".equals(cMsg.eventNm.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_DT, tMsg.rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_DT, tMsg.rtlWhNm);
                }
            }
        }
        if (err) {
            if ("OpenWinFromWarehouseH".equals(cMsg.eventNm.getValue())) {
                cMsg.rtlWhCd_HF.setErrorInfo(1, NPAM1349E, new String[] {"From Warehouse" });
            } else if ("OpenWinToWarehouseH".equals(cMsg.eventNm.getValue())) {
                cMsg.rtlWhCd_HT.setErrorInfo(1, NPAM1349E, new String[] {"To Warehouse" });
            } else if ("OpenWinFromWarehouseD".equals(cMsg.eventNm.getValue())) {
                cMsg.rtlWhCd_DF.setErrorInfo(1, NPAM1349E, new String[] {"From Warehouse" });
            } else if ("OpenWinToWarehouseD".equals(cMsg.eventNm.getValue())) {
                cMsg.rtlWhCd_DT.setErrorInfo(1, NPAM1349E, new String[] {"To Warehouse" });
            }
        }
    }

    /**
     * <pre>
     * Serch Event From submit
     * </pre>
     * @param cMsg NPAL1200CMsg
     * @param sMsg NPAL1200SMsg
     */
    private void doProcess_NPAL1200Scrn00_SearchFromSubmit(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg) {
        NPAL1200CommonLogic.searchDetail(cMsg, sMsg, getGlobalCompanyCode(), false, true);
    }
}
