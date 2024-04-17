/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6750;

//import static business.blap.NMAL6750.constant.NMAL6750Constant.CTAC_TP;
import static business.blap.NMAL6750.constant.NMAL6750Constant.MAX_ROW;
import static business.blap.NMAL6750.constant.NMAL6750Constant.MESSAGE_KIND_ERR;
import static business.blap.NMAL6750.constant.NMAL6750Constant.MESSAGE_KIND_WARN;
import static business.blap.NMAL6750.constant.NMAL6750Constant.NMAM0050E;
import static business.blap.NMAL6750.constant.NMAL6750Constant.NMAM8111E;
import static business.blap.NMAL6750.constant.NMAL6750Constant.NMAM8354E;
import static business.blap.NMAL6750.constant.NMAL6750Constant.NZZM0001W;
import static business.blap.NMAL6750.constant.NMAL6750Constant.ZZZM9001E;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6750.constant.NMAL6750Constant;
import business.db.CTAC_TPTMsg;
import business.db.CTAC_TPTMsgArray;
import business.db.SVC_MACH_CTAC_PSNTMsg;
import business.db.SVC_MACH_CTAC_PSNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PSN_SALT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PSN_TTL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * NMAL6750BL02
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/05   Fujitsu         J.Sakamoto      Create          N/A
 * 2015/10/05   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/16   Fujitsu         C.Tanaka        Update          QC#4345
 * 2016/02/19   SRAA            Y.Chen          Update          QC#4350
 * 2016/02/24   Fujitsu         C.Tanaka        Update          QC#4634
 * 2016/11/07   Fujitsu         M.Ohno          Update          S21_NA#2680
 * 2017/03/07   Fujitsu         T.Aoi           Update          S21_NA#17849
 * 2017/06/16   Hitachi         K.Kasai         Update          S21_NA#18848
 * 2017/08/10   Fujitsu         H.Nagashima     Update          S21_NA#8598
 * </pre>
 */
public class NMAL6750BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL6750CMsg bizMsg = (NMAL6750CMsg) cMsg;
            NMAL6750SMsg glblMsg = (NMAL6750SMsg) sMsg;

            if ("NMAL6750_INIT".equals(screenAplID)) {
                //START 2017/06/16 K.Kasai [QC#18848,MOD]
                doProcess_NMAL6750_INIT(bizMsg, glblMsg, false);
                //END 2017/06/16 K.Kasai [QC#18848,ADD]
//            } else if ("NMAL6750Scrn00_AddAccount".equals(screenAplID)) {
//                doProcess_NMAL6750_AddAccount(bizMsg, glblMsg);
//            } else if ("NMAL6750Scrn00_DeleteAccount".equals(screenAplID)) {
//                doProcess_NMAL6750_DeleteAccount(bizMsg, glblMsg);
            } else if ("NMAL6750Scrn00_AddContactPoint".equals(screenAplID)) {
                doProcess_NMAL6750_AddContactPoint(bizMsg, glblMsg);
            } else if ("NMAL6750Scrn00_DeleteContactPoint".equals(screenAplID)) {
                doProcess_NMAL6750_DeleteContactPoint(bizMsg, glblMsg);
            } else if ("NMAL6750Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL6750_Submit(bizMsg, glblMsg);
            } else if ("NMAL6750Scrn00_CMN_Reset".equals(screenAplID)) {
                //START 2017/06/16 K.Kasai [QC#18848,MOD]
                doProcess_NMAL6750_INIT(bizMsg, glblMsg, false);
                //END 2017/06/16 K.Kasai [QC#18848,ADD]
//            } else if ("NMAL6750Scrn00_GetAccountName".equals(screenAplID)) {
//                doProcess_NMAL6750Scrn00_GetAccountName(bizMsg, glblMsg);
//            } else if ("NMAL6750Scrn00_GetLocationName".equals(screenAplID)) {
//                doProcess_NMAL6750Scrn00_GetLocationName(bizMsg, glblMsg);
                doProcess_NMAL6750_Submit(bizMsg, glblMsg);
            //QC#8598 add Start
            } else if ("NMAL6750_NMAL6770".equals(screenAplID)) {
                doProcess_NMAL6750_NMAL6770(bizMsg, glblMsg);
            //QC#8598 add End
            } else {
                return;
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    //START 2017/06/16 K.Kasai [QC#18848,MOD]
    private void doProcess_NMAL6750_INIT(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg, boolean isSubmit) {
    //END 2017/06/16 K.Kasai [QC#18848,ADD]

        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.X);
        ZYPTableUtil.clear(sMsg.Y);

        ZYPCodeDataUtil.createPulldownList(DS_CTAC_PSN_TTL.class, cMsg.dsCtacPsnTtlCd_H1, cMsg.dsCtacPsnTtlNm_H1);
        ZYPCodeDataUtil.createPulldownList(DS_CTAC_PSN_SALT.class, cMsg.dsCtacPsnSaltCd_H1, cMsg.dsCtacPsnSaltNm_H1);
        ZYPCodeDataUtil.createPulldownList(DS_CTAC_PNT_TP.class, cMsg.dsCtacPntTpCd_H1, cMsg.dsCtacPntTpNm_H1);
        ZYPCodeDataUtil.createPulldownList(DS_CTAC_PNT_TP.class, cMsg.dsCtacPntTpCd_L1, cMsg.dsCtacPntTpNm_L1);

        createDeptPullDown(cMsg);
        // QC#8598 del Start
//        createCtacTpPullDown(cMsg);
        // QC#8598 del End

        sMsg.ctacPsnActvFlg_H1.setValue(ZYPConstant.FLG_ON_Y);
        cMsg.ctacPsnActvFlg_H1.setValue(ZYPConstant.FLG_ON_Y);

        if (!ZYPCommonFunc.hasValue(cMsg.ctacPsnNum_P1)) {
            if (ZYPCommonFunc.hasValue(cMsg.dsAcctNum_P1)) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNum_H1, cMsg.dsAcctNum_P1);
                addRelatedAccountInfo(cMsg, sMsg);
            } else if (ZYPCommonFunc.hasValue(cMsg.locNum_P1)) {
                ZYPEZDItemValueSetter.setValue(cMsg.locNum_H1, cMsg.locNum_P1);
                addRelatedAccountInfo(cMsg, sMsg);
            }
        } else {
            cMsg.ctacPsnNum_H1.setValue(cMsg.ctacPsnNum_P1.getValue());
            //START 2017/06/16 K.Kasai [QC#18848,MOD]
            searchContactPsnInfo(cMsg, sMsg, isSubmit);
            //END 2017/06/16 K.Kasai [QC#18848,ADD]
            if (MESSAGE_KIND_ERR.equals(cMsg.getMessageKind())) {
                return;
            }
            //START 2017/06/16 K.Kasai [QC#18848,MOD]
            if (ZYPCommonFunc.hasValue(cMsg.ctacPsnPk_H1)) {
                searchRelatedAccountList(cMsg, sMsg);
                //QC#8598 del Start
//                searchContactPointList(cMsg, sMsg);
                //QC#8598 del Start
            }
            //END 2017/06/16 K.Kasai [QC#18848,ADD]
        }
        // QC#8598 add Start
        if (ZYPCommonFunc.hasValue(cMsg.ctacPsnPk_H1)) {
            searchContactPointList(cMsg, sMsg);
        }
        searchContactRelationList(cMsg, sMsg);
        searchAutoFormatContactTypeList(cMsg, sMsg);
        // QC#8598 add End

        setBackupData(sMsg);
    }

//    private void doProcess_NMAL6750_AddAccount(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg) {
//
//        if (sMsg.A.getValidCount() + 1 >= cMsg.A.length()) {
//            cMsg.setMessageInfo(NMAM0050E, new String[] {MAX_ROW });
//            return;
//        }
//
//        addRelatedAccountInfo(cMsg, sMsg);
//        if (MESSAGE_KIND_WARN.equals(cMsg.getMessageKind()) || MESSAGE_KIND_ERR.equals(cMsg.getMessageKind())) {
//            return;
//        }
//    }
//
//    private void doProcess_NMAL6750_DeleteAccount(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg) {
//
//        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);
//
//        NMAL6750_ASMsg aSMsg;
//        NMAL6750_XSMsg xSMsg;
//        int cnt = sMsg.X.getValidCount();
//        for (int i = 0; i < checkList.size(); i++) {
//            aSMsg = sMsg.A.no(checkList.get(i));
//            xSMsg = sMsg.X.no(cnt);
//            if (ZYPCommonFunc.hasValue(aSMsg.dsCtacPsnRelnPk_A1)) {
//                ZYPEZDItemValueSetter.setValue(xSMsg.dsCtacPsnRelnPk_X1, aSMsg.dsCtacPsnRelnPk_A1);
//                ZYPEZDItemValueSetter.setValue(xSMsg.ezUpTime_X1, aSMsg.ezUpTime_A1);
//                ZYPEZDItemValueSetter.setValue(xSMsg.ezUpTimeZone_X1, aSMsg.ezUpTimeZone_A1);
//                cnt++;
//            }
//        }
//        sMsg.X.setValidCount(cnt);
//
//        ZYPTableUtil.deleteRows(cMsg.A, checkList);
//        ZYPTableUtil.deleteRows(sMsg.A, checkList);
//    }

    private void doProcess_NMAL6750_AddContactPoint(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg) {

        int index = sMsg.B.getValidCount();

        if (index + 1 >= cMsg.B.length()) {
            cMsg.setMessageInfo(NMAM0050E, new String[] {MAX_ROW });
            return;
        }

        NMAL6750_BSMsg bSMsg = sMsg.B.no(index);
        // QC#4350
        bSMsg.dsCtacPntActvFlg_B1.setValue(ZYPConstant.FLG_ON_Y);

        EZDMsg.copy(sMsg.B.no(index), null, cMsg.B.no(index), null);
        cMsg.B.setValidCount(index + 1);
        sMsg.B.setValidCount(index + 1);
    }

    private void doProcess_NMAL6750_DeleteContactPoint(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.B, "xxChkBox_B1", ZYPConstant.CHKBOX_ON_Y);

        SVC_MACH_CTAC_PSNTMsg svcMachCtacPsnTMsg = new SVC_MACH_CTAC_PSNTMsg();
        svcMachCtacPsnTMsg.setSQLID("002");
        svcMachCtacPsnTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());

        NMAL6750_BSMsg bSMsg;
        NMAL6750_YSMsg ySMsg;
        int cnt = sMsg.Y.getValidCount();
        int idx = 0;
        boolean err = false;
        for (int i = 0; i < checkList.size(); i++) {
            idx = checkList.get(i);
            bSMsg = sMsg.B.no(idx);
            ySMsg = sMsg.Y.no(cnt);

            svcMachCtacPsnTMsg.setConditionValue("dsCtacPntPk01", bSMsg.dsCtacPntPk_B1.getValue());
            SVC_MACH_CTAC_PSNTMsgArray svcMachCtacPsnTMsgArray = (SVC_MACH_CTAC_PSNTMsgArray) EZDTBLAccessor.findByCondition(svcMachCtacPsnTMsg);
            if (svcMachCtacPsnTMsgArray.length() > 0) {
                cMsg.B.no(idx).xxChkBox_B1.setErrorInfo(1, NMAM8354E);
                err = true;
            } else {
                if (ZYPCommonFunc.hasValue(bSMsg.dsCtacPntPk_B1)) {
                    ZYPEZDItemValueSetter.setValue(ySMsg.dsCtacPntPk_Y1, bSMsg.dsCtacPntPk_B1);
                    ZYPEZDItemValueSetter.setValue(ySMsg.ezUpTime_Y1, bSMsg.ezUpTime_B1);
                    ZYPEZDItemValueSetter.setValue(ySMsg.ezUpTimeZone_Y1, bSMsg.ezUpTimeZone_B1);
                    cnt++;
                }
            }
        }

        if (err) {
            return;
        }

        sMsg.Y.setValidCount(cnt);

        ZYPTableUtil.deleteRows(cMsg.B, checkList);
        ZYPTableUtil.deleteRows(sMsg.B, checkList);
    }

    private void doProcess_NMAL6750_Submit(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg) {
        if (MESSAGE_KIND_WARN.equals(cMsg.getMessageKind())) {
            return;
        }

        //START 2017/06/16 K.Kasai [QC#18848,MOD]
        doProcess_NMAL6750_INIT(cMsg, sMsg, true);
        //END 2017/06/16 K.Kasai [QC#18848,ADD]
    }

    //START 2017/06/16 K.Kasai [QC#18848,MOD]
    private void searchContactPsnInfo(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg, boolean isSubmit) {
    //END 2017/06/16 K.Kasai [QC#18848,ADD]

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("ctacPsnNum", cMsg.ctacPsnNum_H1.getValue());
        queryParam.put("rowNum", 1);

        S21SsmEZDResult ssmResult = getQuery().getContactPsnInfo(queryParam, sMsg);
        if (ssmResult.isCodeNormal()) {
            // Add Start 2016/11/07 M.Ohno S21_NA#2680
            ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistCratByNm, ZYPRecHistUtil.getFullNameForRecHist(sMsg.xxRecHistCratByNm.getValue()));
            ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistUpdByNm, ZYPRecHistUtil.getFullNameForRecHist(sMsg.xxRecHistUpdByNm.getValue()));
            // Add End 2016/11/07 M.Ohno S21_NA#2680
            EZDMsg.copy(sMsg, null, cMsg, null);

        //START 2017/06/16 K.Kasai [QC#18848,MOD]
        } else if (!isSubmit) {
        //END 2017/06/16 K.Kasai [QC#18848,ADD]
            cMsg.setMessageInfo(ZZZM9001E);

            ZYPTableUtil.clear(sMsg.A);
            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(sMsg.B);
            ZYPTableUtil.clear(cMsg.B);
        }
    }

    private void addRelatedAccountInfo(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsAcctNum", cMsg.dsAcctNum_H1.getValue());
        queryParam.put("locNum", cMsg.locNum_H1.getValue());
        queryParam.put("rowNum", 1);

        int index = sMsg.A.getValidCount();

        S21SsmEZDResult ssmResult = getQuery().getRelatedAccountInfo(queryParam, sMsg.A.no(index));

        if (ssmResult.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).effFromDt_A1, ZYPDateUtil.getSalesDate());
            // Add Start 2016/11/07 M.Ohno S21_NA#2680
            for (int i = 0; i < index; i++) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(i).xxRecHistCratByNm_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(i).xxRecHistUpdByNm_A1.getValue()));
            }
            // Add End 2016/11/07 M.Ohno S21_NA#2680
            EZDMsg.copy(sMsg.A.no(index), null, cMsg.A.no(index), null);
            cMsg.A.setValidCount(index + 1);
            sMsg.A.setValidCount(index + 1);

            cMsg.dsAcctNum_H1.clear();
            cMsg.dsAcctNm_H1.clear();
            cMsg.locNum_H1.clear();
            cMsg.locNm_H1.clear();

        } else {
            if (ZYPCommonFunc.hasValue(cMsg.dsAcctNum_H1)) {
                cMsg.dsAcctNum_H1.setErrorInfo(1, ZZZM9001E);
            } else if (ZYPCommonFunc.hasValue(cMsg.locNum_H1)) {
                cMsg.locNum_H1.setErrorInfo(1, ZZZM9001E);
            }
        }
    }

    private void searchRelatedAccountList(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("ctacPsnPk", cMsg.ctacPsnPk_H1.getValue());
//        queryParam.put("rowNum", cMsg.A.length() + 1);
        queryParam.put("rowNum", 1);
        queryParam.put("rgtnStsCd", RGTN_STS.TERMINATED);
        // 2017/10/26 QC#21996 add start
        queryParam.put("dsAcctNum", cMsg.dsAcctNum_P1.getValue());
        queryParam.put("locNum", cMsg.locNum_P1.getValue());
        // 2017/10/26 QC#21996 add end

        S21SsmEZDResult ssmResult = getQuery().getRelatedAccountList(queryParam, sMsg);

        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            int scrnListCnt = queryResCnt;

            if (cMsg.A.length() < queryResCnt) {
                cMsg.setMessageInfo(NZZM0001W);
                scrnListCnt = cMsg.A.length();
            }

            for (int i = 0; i < scrnListCnt; i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(scrnListCnt);
        }
    }

    private void searchContactPointList(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("ctacPsnPk", cMsg.ctacPsnPk_H1.getValue());
        queryParam.put("rowNum", cMsg.B.length() + 1);

        S21SsmEZDResult ssmResult = getQuery().getContactPointList(queryParam, sMsg);

        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            int scrnListCnt = queryResCnt;

            // 2017/03/07 T.Aoi S21_NA#17849 Del Start
            //if (sMsg.B.length() < queryResCnt) {
            //    cMsg.setMessageInfo(NZZM0001W);
            //}
            // 2017/03/07 T.Aoi S21_NA#17849 Del End

            for (int i = 0; i < scrnListCnt; i++) {
                // 2017/03/07 T.Aoi S21_NA#17849 Add Start
                if (sMsg.B.length() - 1 < i) {
                    cMsg.setMessageInfo(NZZM0001W);
                    cMsg.B.setValidCount(scrnListCnt);
                    return;
                }
                // 2017/03/07 T.Aoi S21_NA#17849 Add End
                // Add Start 2016/11/07 M.Ohno S21_NA#2680
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRecHistCratByNm_B1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.B.no(i).xxRecHistCratByNm_B1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRecHistUpdByNm_B1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.B.no(i).xxRecHistUpdByNm_B1.getValue()));
                // Add Start 2016/11/07 M.Ohno S21_NA#2680
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);

            }
            cMsg.B.setValidCount(scrnListCnt);
        }
    }

    // QC#8598 add Start
    private void searchAutoFormatContactTypeList(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("autoFmtFlg", ZYPConstant.FLG_ON_Y);

        S21SsmEZDResult ssmResult = NMAL6750Query.getInstance().getAutoFormatContactTypeList(queryParam, sMsg);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();

            for (int i = 0; i < queryResCnt; i++) {
                EZDMsg.copy(sMsg.T.no(i), null, cMsg.T.no(i), null);
            }
            cMsg.T.setValidCount(queryResCnt);
        }
    }

    private void searchContactRelationList(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(cMsg.ctacPsnPk_H1)) {
            if (!ZYPCommonFunc.hasValue(cMsg.locNum_P1)) {
                queryParam.put("dsAcctNum", cMsg.dsAcctNum_P1.getValue());
            } else {
                queryParam.put("locNum", cMsg.locNum_P1.getValue());
            }
            queryParam.put("ctacPsnPk", cMsg.ctacPsnPk_H1.getValue());
        }
        queryParam.put("flgOn", ZYPConstant.FLG_ON_Y);
        queryParam.put("flgOff", ZYPConstant.FLG_OFF_N);

        S21SsmEZDResult ssmResult = getQuery().getContactRelationList(queryParam, sMsg);

        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();

            for (int i = 0; i < queryResCnt; i++) {
                EZDMsg.copy(sMsg.R.no(i), null, cMsg.R.no(i), null);
            }
            cMsg.R.setValidCount(queryResCnt);
        }
    }
    // QC#8598 add End

    private static NMAL6750Query getQuery() {

        return NMAL6750Query.getInstance();
    }

    private void createDeptPullDown(NMAL6750CMsg cMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("rowNum", cMsg.dsCtacPsnDeptCd_H1.length() + 1);

        S21SsmEZDResult ssmResult = NMAL6750Query.getInstance().getDeptList(queryParam);
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < cMsg.dsCtacPsnDeptCd_H1.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            cMsg.dsCtacPsnDeptCd_H1.no(i).setValue((String) resultMap.get("DS_CTAC_PSN_DEPT_CD"));
            cMsg.dsCtacPsnDeptNm_H1.no(i).setValue((String) resultMap.get("DS_CTAC_PSN_DEPT_NM"));
        }

        // QC#8598 add Start
        cMsg.ctacPntFmtTxt_CD.no(0).setValue(NMAL6750Constant.CTAC_PNT_FMT_US_CD);
        cMsg.ctacPntFmtTxt_NM.no(0).setValue(NMAL6750Constant.CTAC_PNT_FMT_US_NM);
        cMsg.ctacPntFmtTxt_CD.no(1).setValue(NMAL6750Constant.CTAC_PNT_FMT_INTL_CD);
        cMsg.ctacPntFmtTxt_NM.no(1).setValue(NMAL6750Constant.CTAC_PNT_FMT_INTL_NM);
        // QC#8598 add End
    }

    // QC#8598 del Start
//    private void createCtacTpPullDown(NMAL6750CMsg cMsg) {
//        CTAC_TPTMsg ctacTpTMsg = new CTAC_TPTMsg();
//        ctacTpTMsg.setSQLID("002");
//        ctacTpTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
//        ctacTpTMsg.setConditionValue("actvFlg01", ZYPConstant.FLG_ON_Y);
//
//        CTAC_TPTMsgArray ctacTpTMsgArray = (CTAC_TPTMsgArray) EZDTBLAccessor.findByCondition(ctacTpTMsg);
//        if (ctacTpTMsgArray.length() == 0) {
//            cMsg.setMessageInfo(NMAM8111E, new String[] {CTAC_TP });
//            return;
//        }
//
//        for (int i = 0; i < ctacTpTMsgArray.getValidCount(); i++) {
//            ZYPEZDItemValueSetter.setValue(cMsg.ctacTpCd_H1.no(i), ctacTpTMsgArray.no(i).ctacTpCd.getValue());
//            ZYPEZDItemValueSetter.setValue(cMsg.ctacTpNm_H1.no(i), ctacTpTMsgArray.no(i).ctacTpNm.getValue());
//        }
//    }
    // QC#8598 del End

//    /**
//     * Method name: doProcess_NMAL6750Scrn00_GetAccountName
//     * @param cMsg Business Component Interface Message
//     * @param sMsg Global area information
//     */
//    private void doProcess_NMAL6750Scrn00_GetAccountName(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg) {
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
//        queryParam.put("dsAcctNum", cMsg.dsAcctNum_H1.getValue());
//        S21SsmEZDResult result = NMAL6750Query.getInstance().getAcctNm(queryParam);
//
//        if (result.isCodeNormal()) {
//            String acctNm = (String) ((Map) result.getResultObject()).get("DS_ACCT_NM");
//            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H1, acctNm);
//        } else {
//            cMsg.dsAcctNum_H1.setErrorInfo(1, NZZM0000E);
//        }
//    }
//
//    /**
//     * Method name: doProcess_NMAL6750Scrn00_GetLocationName
//     * @param cMsg Business Component Interface Message
//     * @param sMsg Global area information
//     */
//    private void doProcess_NMAL6750Scrn00_GetLocationName(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg) {
//
//        PTY_LOC_WRKTMsg ptyLocWrkTMsg = new PTY_LOC_WRKTMsg();
//        ptyLocWrkTMsg.setSQLID("012");
//        ptyLocWrkTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
//        ptyLocWrkTMsg.setConditionValue("locNum01", cMsg.locNum_H1.getValue());
//
//        PTY_LOC_WRKTMsgArray ptyLocWrkTMsgArray = (PTY_LOC_WRKTMsgArray) EZDTBLAccessor.findByCondition(ptyLocWrkTMsg);
//        if (ptyLocWrkTMsgArray.length() > 0) {
//            ZYPEZDItemValueSetter.setValue(cMsg.locNm_H1, ptyLocWrkTMsgArray.no(0).locNm);
//        } else {
//            cMsg.locNum_H1.setErrorInfo(1, NZZM0000E);
//        }
//    }
//
    private void setBackupData(NMAL6750SMsg sMsg) {
        EZDMsg.copy(sMsg.A, null, sMsg.C, null);
        sMsg.C.setValidCount(sMsg.A.getValidCount());

        EZDMsg.copy(sMsg.B, null, sMsg.D, null);
        sMsg.D.setValidCount(sMsg.B.getValidCount());

        // QC#8598 add Start
        EZDMsg.copy(sMsg.R, null, sMsg.S, null);
        sMsg.S.setValidCount(sMsg.R.getValidCount());
        // QC#8598 add End

    }

    // QC#16452 Add Start
    private void doProcess_NMAL6750_NMAL6770(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg) {

        searchContactPsnInfo(cMsg, sMsg, false);
        if (MESSAGE_KIND_ERR.equals(cMsg.getMessageKind())) {
            return;
        }
        if (ZYPCommonFunc.hasValue(cMsg.ctacPsnPk_H1)) {
            searchContactPointList(cMsg, sMsg);
        }
        searchContactRelationList(cMsg, sMsg);
        setBackupData(sMsg);
    }

    // QC#16452 Add End

}
