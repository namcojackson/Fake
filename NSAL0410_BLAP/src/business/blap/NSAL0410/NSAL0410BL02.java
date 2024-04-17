/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0410;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0410.common.NSAL0410CommonLogic;
import business.blap.NSAL0410.constant.NSAL0410Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_BILL_BY_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Additional Charge
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/24   Fujitsu         C.Naito         Create          N/A
 * 2015/11/26   Hitachi         T.Tomita        Update          N/A
 * 2015/12/02   Hitachi         T.Tomita        Update          QC#1319
 * 2016/01/07   Hitachi         T.Tomita        Update          QC#2814
 * 2016/02/16   Hitachi         K.Kasai         Update          QC#2874,3021
 * 2016/02/24   Hitachi         K.Kasai         Update          QC#2570
 * 2016/04/19   Hitachi         K.Kishimoto     Update          QC#5740
 * 2016/05/24   Hitachi         Y.Takeno        Update          QC#447
 * 2016/09/26   Hitachi         K.Kishimoto     Update          QC#14428
 * 2017/05/26   Hitachi         Y.Osawa         Update          QC#18560
 * 2017/12/26   Hitachi         K.Kojima        Update          QC#18562
 * 2018/05/24   Hitachi         K.Kitachi       Update          QC#26223
 * 2019/01/29   Hitachi         Y.Takeno        Update          QC#29949
 * 2019/02/01   Hitachi         K.Kitachi       Update          QC#29949
 *</pre>
 */
public class NSAL0410BL02 extends S21BusinessHandler {

    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        String screenAplID = cMsg.getScreenAplID();
        try {

            if ("NSAL0410_INIT".equals(screenAplID)) {
                doProcess_NSAL0410_INIT((NSAL0410CMsg) cMsg, false);
            } else if ("NSAL0410Scrn00_Add".equals(screenAplID)) {
                doProcess_NSAL0410Scrn00_Add((NSAL0410CMsg) cMsg);
            } else if ("NSAL0410Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0410Scrn00_CMN_Submit((NSAL0410CMsg) cMsg);
            } else if ("NSAL0410Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0410Scrn00_CMN_Reset((NSAL0410CMsg) cMsg);
            // START 2015/12/02 T.Tomita [QC#1319, MOD]
            } else if ("NSAL0410Scrn00_OnChange_SerNum".equals(screenAplID)) {
                doProcess_NSAL0410Scrn00_OnChange_SerNum((NSAL0410CMsg) cMsg);
            // START 2016/05/24 [QC#447, MOD]
            } else if ("NSAL0410Scrn00_OnChange_AddlChrgTpCd".equals(screenAplID)) {
                doProcess_NSAL0410Scrn00_OnChange_AddlChrgTpCd((NSAL0410CMsg) cMsg);
            // END   2016/05/24 [QC#447, MOD]
            // START 2017/12/26 K.Kojima [QC#18562,ADD]
            } else if ("NSAL0410Scrn00_OnChange_AddlChrgInvTpCd".equals(screenAplID)) {
                doProcess_NSAL0410Scrn00_OnChange_AddlChrgInvTpCd((NSAL0410CMsg) cMsg);
            // END 2017/12/26 K.Kojima [QC#18562,ADD]
            // START 2019/02/01 K.Kitachi [QC#29949, ADD]
            } else if ("NSAL0410_NWAL1130".equals(screenAplID)) {
                doProcess_NSAL0410_NWAL1130((NSAL0410CMsg) cMsg);
            // END 2019/02/01 K.Kitachi [QC#29949, ADD]
            }
            // END 2015/12/02 T.Tomita [QC#1319, MOD]

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * NSAL0410Scrn00_CMN_Submit
     */
    private void doProcess_NSAL0410Scrn00_CMN_Submit(NSAL0410CMsg cMsg) {
        boolean doSubmitEvent = true;
        doProcess_NSAL0410_INIT(cMsg, doSubmitEvent);
    }

    /**
     * NSAL0410Scrn00_CMN_Reset
     */
    private void doProcess_NSAL0410Scrn00_CMN_Reset(NSAL0410CMsg cMsg) {
        boolean doSubmitEvent = false;
        doProcess_NSAL0410_INIT(cMsg, doSubmitEvent);
    }

    /**
     * NSAL0410Scrn00_Add
     */
    private void doProcess_NSAL0410Scrn00_Add(NSAL0410CMsg cMsg) {
        // add newRow
        int howManyRowsAdd = 1;
        int newAddRowIndx = cMsg.A.getValidCount();
        int allRowNum = newAddRowIndx + howManyRowsAdd;
        NSAL0410CommonLogic.addNewRow(cMsg, newAddRowIndx, allRowNum, getGlobalCompanyCode());
    }

    /**
     * NSAL0410_INIT
     * @param cMsg NSAL0410CMsg
     * @param doSubmitEvent boolean
     */
    private void doProcess_NSAL0410_INIT(NSAL0410CMsg cMsg, boolean doSubmitEvent) {

        // mandatory inputparam error
        if (!ZYPCommonFunc.hasValue(cMsg.dsContrPk_P)) {
            return;
        }

        ZYPTableUtil.clear(cMsg.A);

        // Sales Date
        String slsDt = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt_P, slsDt);

        /** Header **/
        S21SsmEZDResult headerResult = NSAL0410Query.getInstance().getHeader(cMsg);
        if (!headerResult.isCodeNotFound() && !headerResult.isCodeNormal()) {
            cMsg.setMessageInfo(NSAL0410Constant.NSAM0348E, new String[] {"DS_CONTR" });
            return;
        }

        /** Detail Additional Charge **/
        // Search DS_CONTR_PK (DS_CONTR_DTL_PK) and Set A
        S21SsmEZDResult res = NSAL0410Query.getInstance().getDsContrAddlChrg(cMsg);
        if (!res.isCodeNotFound() && !res.isCodeNormal()) {
            cMsg.setMessageInfo(NSAL0410Constant.NSAM0348E, new String[] {"DS_CONTR_ADDL_CHRG" });
            return;
        }

        // Set Serial Pulldown of Resisted Data
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            // START 2019/01/29 [QC#29949, DEL]
//          if (ZYPCommonFunc.hasValue(cMsg.A.no(i).xxScrItem34Txt_A)) {
//              // inactive pulldown
//              ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).dsContrDtlPk_AC.no(0), cMsg.A.no(i).dsContrDtlPk_A.getValue());
//              ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxScrItem34Txt_AD.no(0), cMsg.A.no(i).xxScrItem34Txt_A.getValue());
//          } else {
//              cMsg.A.no(i).dsContrDtlPk_AC.clear();
//              cMsg.A.no(i).xxScrItem34Txt_AD.clear();
//          }
            // END   2019/01/29 [QC#29949, DEL]
            // START 2018/05/23 K.Kitachi [QC#26223, ADD]
            // Set Invoiced Flag
            BigDecimal count;
            if (cMsg.A.no(i).svcBillByTpCd_SE.getValue().equals(SVC_BILL_BY_TP.CONTRACT)) {
                count = NSAL0410Query.getInstance().countInvSchd(cMsg.A.no(i).dsContrPk_A.getValue(), null);
            } else {
                count = NSAL0410Query.getInstance().countInvSchd(cMsg.A.no(i).dsContrPk_A.getValue(), cMsg.A.no(i).dsContrDtlPk_A.getValue());
            }
            if (count.compareTo(BigDecimal.ZERO) > 0) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).invFlg_A, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).invFlg_A, ZYPConstant.FLG_OFF_N);
            }
            // END 2018/05/23 K.Kitachi [QC#26223, ADD]
        }

        // Search Additional Charge Invoice Data
        NSAL0410Query.getInstance().getAddlChrgInv(cMsg);

        // QC#28969
//        /** User Security Refer **/
//        if (NSAL0410CommonLogic.checkReferAuth()) {
//            return;
//        }

        // START 2016/01/07 T.Tomita [QC#2814, ADD]
        NSAL0410CommonLogic.setSvcBillByTpPulldown(cMsg);
        // END 2016/01/07 T.Tomita [QC#2814, ADD]

        /** Submit Event after **/
        if (doSubmitEvent) {
            return;
        }

        // Set Pulldown Data
        // Mod Start 2016/09/26 <QC#14428>
//        ZYPCodeDataUtil.createPulldownList("ADDL_CHRG_TP", cMsg.addlChrgTpCd_CD, cMsg.addlChrgTpNm_DI);
        List<Map<String, Object>> AddlChrgTpMapList = NSAL0410Query.getInstance().getAddlChrgTpV();
        int addlChrgTpIdx = 0;
        for (Map<String, Object> AddlChrgTpMap : AddlChrgTpMapList) {
            if (addlChrgTpIdx >= cMsg.addlChrgTpCd_CD.length()) {
                break;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.addlChrgTpCd_CD.no(addlChrgTpIdx), (String) AddlChrgTpMap.get("ADDL_CHRG_TP_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.addlChrgTpNm_DI.no(addlChrgTpIdx), (String) AddlChrgTpMap.get("ADDL_CHRG_TP_DESC_TXT"));
            addlChrgTpIdx++;
        }
        // Mod End   2016/09/26 <QC#14428>
        ZYPCodeDataUtil.createPulldownList("BLLG_CYCLE", cMsg.bllgCycleCd_CD, cMsg.bllgCycleNm_DI);
        // START 2017/05/26 Y.Osawa [QC#18560, ADD]
        NSAL0410CommonLogic.deletePulldownList(cMsg.bllgCycleCd_CD, cMsg.bllgCycleNm_DI, BLLG_CYCLE.DAILY);
        // END   2017/05/26 Y.Osawa [QC#18560, ADD]
        // START 2016/01/07 T.Tomita [QC#2814, DEL]
//        // START 2015/12/02 T.Tomita [QC#1319, MOD]
//        // ZYPCodeDataUtil.createPulldownList("SVC_BILL_BY_TP", cMsg.svcBillByTpCd_CD, cMsg.svcBillByTpNm_DI);
//        NSAL0410CommonLogic.setSvcBillByTpPulldown(cMsg);
//        // END 2015/12/02 T.Tomita [QC#1319, MOD]
        // END 2016/01/07 T.Tomita [QC#2814, DEL]
        ZYPCodeDataUtil.createPulldownList("ADDL_CHRG_INV_TP", cMsg.addlChrgInvTpCd_CD, cMsg.addlChrgInvTpNm_DI);

        // START 2019/02/01 K.Kitachi [QC#29949, DEL]
//        if (DS_CONTR_CATG.REGULAR.equals(cMsg.dsContrCatgCd_H.getValue()) || DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd_H.getValue())) {
//            // Set Serial Number Pulldown Data
//            S21SsmEZDResult serNumResult = NSAL0410Query.getInstance().getSerNum(cMsg);
//            if (!headerResult.isCodeNotFound() && !headerResult.isCodeNormal()) {
//                cMsg.setMessageInfo(NSAL0410Constant.ZZZM9006E, new String[] {"Serial#" });
//                return;
//            }
//            @SuppressWarnings("unchecked")
//            List<Map<String, Object>> serNumList = (List<Map<String, Object>>) serNumResult.getResultObject();
//            int cnt = 0;
//            for (Map<String, Object> serNumInfo : serNumList) {
//                ZYPEZDItemValueSetter.setValue(cMsg.dsContrDtlPk_CD.no(cnt), (BigDecimal) serNumInfo.get("DS_CONTR_DTL_PK"));
//                ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem34Txt_DI.no(cnt), (String) serNumInfo.get("SER_NUM"));
//                // add start 2016/02/16 CSA Defect#2874
//                ZYPEZDItemValueSetter.setValue(cMsg.effFromDt_AC.no(cnt), (String) serNumInfo.get("EFF_FROM_DT"));
//                ZYPEZDItemValueSetter.setValue(cMsg.effThruDt_AC.no(cnt), (String) serNumInfo.get("EFF_THRU_DT"));
//                // add end 2016/02/16 CSA Defect#2874
//                cnt++;
//            }
//        // add start 2016/02/15 CSA Defect#3021
//        } else if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd_H.getValue())) {
//            // get Fleet line
//            S21SsmEZDResult fltLineResult = NSAL0410Query.getInstance().getFleetLine(cMsg);
//            @SuppressWarnings("unchecked")
//            List<Map<String, Object>> fltLineList = (List<Map<String, Object>>) fltLineResult.getResultObject();
//            if (fltLineResult.getQueryResultCount() > 0) {
//                ZYPEZDItemValueSetter.setValue(cMsg.dsContrDtlPk_CD.no(0), (BigDecimal) fltLineList.get(0).get("DS_CONTR_DTL_PK"));
//                ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem34Txt_DI.no(0), cMsg.xxScrItem34Txt);
//                ZYPEZDItemValueSetter.setValue(cMsg.effFromDt_AC.no(0), (String) fltLineList.get(0).get("EFF_FROM_DT"));
//                ZYPEZDItemValueSetter.setValue(cMsg.effThruDt_AC.no(0), (String) fltLineList.get(0).get("EFF_THRU_DT"));
//            }
//            // add end 2016/02/15 CSA Defect#3021
//        } else {
//            cMsg.dsContrDtlPk_CD.clear();
//            cMsg.xxScrItem34Txt_DI.clear();
//        }
        // END 2019/02/01 K.Kitachi [QC#29949, DEL]

        /** get SVC_BILL_BY_TPtable for Event OnChangeBillBy **/
        if (!NSAL0410CommonLogic.getSvcBillByTpTable(cMsg)) {
            return;
        }

        /** Search default BllgCycle **/
        S21SsmEZDResult res0 = NSAL0410Query.getInstance().getDefaultBllgCycle(cMsg);
        if (!res0.isCodeNormal()) {
            cMsg.setMessageInfo(NSAL0410Constant.NSAM0348E, new String[] {"DS_CONTR_DTL" });
            return;
        }

        // START 2017/12/26 K.Kojima [QC#18562,ADD]
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NSAL0410CommonLogic.setBllgCycle(cMsg, i);
        }
        // END 2017/12/26 K.Kojima [QC#18562,ADD]
    }

    // START 2015/12/02 T.Tomita [QC#1319, ADD]
    private void doProcess_NSAL0410Scrn00_OnChange_SerNum(NSAL0410CMsg cMsg) {
        // START 2017/12/26 K.Kojima [QC#18562,ADD]
        NSAL0410CommonLogic.setBllgCycle(cMsg, cMsg.xxRowNum_H.getValueInt());
        // END 2017/12/26 K.Kojima [QC#18562,ADD]
        // Set SVC_BILL_BY_TP Pulldown Data
        NSAL0410CommonLogic.setSvcBillByTpPulldownData(cMsg, cMsg.xxRowNum_H.getValueInt());
        // add start 2016/02/16 CSA Defect#2874
        BigDecimal dsContrDtlPk = cMsg.A.no(cMsg.xxRowNum_H.getValueInt()).dsContrDtlPk_A.getValue();
        if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            // Add Start 04/19/2016 <QC#5740>
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(cMsg.xxRowNum_H.getValueInt()).effFromDt_A, cMsg.contrVrsnEffFromDt);
            // Add End   04/19/2016 <QC#5740>
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(cMsg.xxRowNum_H.getValueInt()).effThruDt_A, cMsg.contrVrsnEffThruDt);
            return;
        }
        for (int i = 0; i < cMsg.dsContrDtlPk_CD.length(); i++) {
            if (dsContrDtlPk.equals(cMsg.dsContrDtlPk_CD.no(i).getValue())) {
                // Add Start 04/19/2016 <QC#5740>
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(cMsg.xxRowNum_H.getValueInt()).effFromDt_A, cMsg.effFromDt_AC.no(i));
                // Add End   04/19/2016 <QC#5740>
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(cMsg.xxRowNum_H.getValueInt()).effThruDt_A, cMsg.effThruDt_AC.no(i));
                return;
            }
        }
        // add end 2016/02/16 CSA Defect#2874
    }
    // END 2015/12/02 T.Tomita [QC#1319, ADD]

    // START 2016/05/24 [QC#447, ADD]
    private void doProcess_NSAL0410Scrn00_OnChange_AddlChrgTpCd(NSAL0410CMsg cMsg) {
        NSAL0410CommonLogic.setSlaTime(cMsg, cMsg.xxRowNum_H.getValueInt());
    }
    // END   2016/05/24 [QC#447, ADD]

    // START 2017/12/26 K.Kojima [QC#18562,ADD]
    private void doProcess_NSAL0410Scrn00_OnChange_AddlChrgInvTpCd(NSAL0410CMsg cMsg) {
        NSAL0410CommonLogic.setBllgCycle(cMsg, cMsg.xxRowNum_H.getValueInt());
    }
    // END 2017/12/26 K.Kojima [QC#18562,ADD]

    // START 2019/02/01 K.Kitachi [QC#29949, ADD]
    private void doProcess_NSAL0410_NWAL1130(NSAL0410CMsg cMsg) {
        NSAL0410CommonLogic.setBllgCycle(cMsg, cMsg.xxRowNum_H.getValueInt());
        NSAL0410CommonLogic.setSvcBillByTpPulldownData(cMsg, cMsg.xxRowNum_H.getValueInt());
    }
    // END 2019/02/01 K.Kitachi [QC#29949, ADD]
}
