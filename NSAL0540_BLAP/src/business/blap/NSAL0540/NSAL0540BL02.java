/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0540;

import static business.blap.NSAL0540.constant.NSAL0540Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCItem;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0540.common.NSAL0540CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Solution Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/11   Hitachi         T.Aoyagi        Create          N/A
 * 2016/04/21   Hitachi         T.Tomita        Update          QC#6399
 * 2016/05/09   Hitachi         T.Tomita        Update          QC#6399
 * 2016/05/11   Hitachi         T.Tomita        Update          QC#7832
 * 2016/09/20   Hitachi         N.Arai          Update          QC#11616
 *</pre>
 */
public class NSAL0540BL02 extends S21BusinessHandler implements ZYPConstant {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0540CMsg cMsg = (NSAL0540CMsg) arg0;
        NSAL0540SMsg sMsg = (NSAL0540SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0540_INIT".equals(screenAplID)) {
                doProcess_NSAL0540_INIT(cMsg, sMsg);
            } else if ("NSAL0540_NSAL1240".equals(screenAplID)) {
                doProcess_NSAL0540_NSAL1240(cMsg, sMsg);
            } else if ("NSAL0540Scrn00_DeleteConfig".equals(screenAplID)) {
                doProcess_NSAL0540Scrn00_DeleteConfig(cMsg, sMsg);
            } else if ("NSAL0540Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0540Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL0540Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0540Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0540Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0540Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSAL0540Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0540Scrn00_CMN_Reset(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0540_INIT(NSAL0540CMsg cMsg, NSAL0540SMsg sMsg) {

        // set common information
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        clearItemValues(cMsg.svcSlnNm, cMsg.svcCmntTxt);
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);

        if (!hasValue(cMsg.svcSlnSq)) {
            return;
        }

        // get Service Memo
        Map<String, Object> svcMemoMap = NSAL0540Query.getInstance().getSvcMemo(cMsg);
        mapToSMsgForSvcMemo(svcMemoMap, cMsg);

        // get Service Configuration
        List<Map<String, Object>> svcConfigList = NSAL0540Query.getInstance().getSvcConfigInfo(cMsg, sMsg, null);

        if (svcConfigList.isEmpty()) {
            cMsg.setMessageInfo(NSAM0194I);
            return;
        }
        if (svcConfigList.size() > sMsg.A.length()) {
            String maxRow = String.valueOf(sMsg.A.length());
            cMsg.setMessageInfo(NSAM0024W, new String[]{maxRow, maxRow});
        }
        mapToSMsgForSvcConfig(sMsg, svcConfigList);

        if (svcConfigList.size() > 0) {
            // set Solution Name
            String svcSlnNm = (String) svcConfigList.get(0).get(SVC_SLN_NM);
            setValue(cMsg.svcSlnNm, svcSlnNm);
        }

        NSAL0540CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0540_NSAL1240(NSAL0540CMsg cMsg, NSAL0540SMsg sMsg) {

        // START 2016/05/11 T.Tomita [QC#7832, MOD]
        for (int idx = 0; idx < cMsg.P.getValidCount(); idx++) {
            BigDecimal svcConfigMstrPk = cMsg.P.no(idx).svcConfigMstrPk_P.getValue();
            // mod start 2016/05/09 CSA Defect#6399
            if (!hasValue(svcConfigMstrPk) || existConfig(sMsg, svcConfigMstrPk)) {
                return;
            }
            // mod end 2016/05/09 CSA Defect#6399

            List<Map<String, Object>> svcConfigList = NSAL0540Query.getInstance().getSvcConfigInfo(cMsg, sMsg, svcConfigMstrPk);
            // add start 2016/05/09 CSA Defect#6399
            if (svcConfigList.size() == 0) {
                cMsg.setMessageInfo(NSAM0020E);
                return;
            }
            // add end 2016/05/09 CSA Defect#6399
            if (sMsg.A.getValidCount() + svcConfigList.size() > sMsg.A.length()) {
                cMsg.setMessageInfo(NSAM0364E, new String[] {String.valueOf(sMsg.A.length()) });
                return;
            }
            addToSMsg(sMsg, svcConfigList);
        }
        // END 2016/05/11 T.Tomita [QC#7832, MOD]

        int pageFrom = 0;
        if (sMsg.A.getValidCount() > cMsg.A.length()) {
            // pageFrom of last page
            pageFrom = ((sMsg.A.getValidCount() - 1) / cMsg.A.length()) * cMsg.A.length();
        }

        NSAL0540CommonLogic.pagenation(cMsg, sMsg, pageFrom);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0540Scrn00_DeleteConfig(NSAL0540CMsg cMsg, NSAL0540SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0540CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A0", FLG_ON_Y);
        if (selectedRows == null || selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }

        selectedRows.clear();
        BigDecimal deleteConfigPk = null;
        int j = 0;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0540_ASMsg aSMsg = sMsg.A.no(i);

            // Main Unit
            if (FLG_ON_Y.equals(aSMsg.xxChkBox_A0.getValue())) {
                selectedRows.add(i);
                deleteConfigPk = aSMsg.svcConfigMstrPk_A0.getValue();

                // save svcConfigMstrPk
                if (hasValue(aSMsg.svcSlnSq_A0)) {
                    setValue(sMsg.B.no(j).svcConfigMstrPk_B0, deleteConfigPk);
                    setValue(sMsg.B.no(j).ezUpTime_B0, aSMsg.ezUpTime_A0);
                    setValue(sMsg.B.no(j).ezUpTimeZone_B0, aSMsg.ezUpTimeZone_A0);
                    j++;
                }
                continue;
            }

            // Accessory
            if (hasValue(deleteConfigPk)
                    && aSMsg.svcConfigMstrPk_A0.getValue().compareTo(deleteConfigPk) == 0) {
                selectedRows.add(i);
            }
        }

        sMsg.B.setValidCount(j);
        ZYPTableUtil.deleteRows(sMsg.A, selectedRows);

        cMsg.setMessageInfo(NSAM0363W);

        NSAL0540CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0540Scrn00_PagePrev(NSAL0540CMsg cMsg, NSAL0540SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0540CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL0540CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL0540Scrn00_PageNext(NSAL0540CMsg cMsg, NSAL0540SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0540CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL0540CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL0540Scrn00_CMN_Submit(NSAL0540CMsg cMsg, NSAL0540SMsg sMsg) {
        doProcess_NSAL0540_INIT(cMsg, sMsg);
    }

    private void doProcess_NSAL0540Scrn00_CMN_Reset(NSAL0540CMsg cMsg, NSAL0540SMsg sMsg) {
        doProcess_NSAL0540_INIT(cMsg, sMsg);
    }

    private void mapToSMsgForSvcConfig(NSAL0540SMsg sMsg, List<Map<String, Object>> svcConfigList) {

        int listIdx = 0;

        for (int i = 0; i < sMsg.A.length(); i++) {

            NSAL0540_ASMsg aSMsg = sMsg.A.no(i);
            mapToSMsg(aSMsg, svcConfigList.get(i));
            listIdx++;

            if (listIdx == svcConfigList.size()) {
                break;
            }
        }
        sMsg.A.setValidCount(listIdx);
    }

    private void addToSMsg(NSAL0540SMsg sMsg, List<Map<String, Object>> svcConfigList) {

        int i = sMsg.A.getValidCount();
        int listIdx = 0;

        for (; i < sMsg.A.length(); i++) {

            NSAL0540_ASMsg aSMsg = sMsg.A.no(i);
            mapToSMsg(aSMsg, svcConfigList.get(listIdx));
            aSMsg.svcSlnSq_A0.clear();
            listIdx++;

            if (listIdx == svcConfigList.size() || i == sMsg.A.length() - 1) {
                break;
            }
        }
        sMsg.A.setValidCount(i + 1);
    }

    private void mapToSMsg(NSAL0540_ASMsg aSMsg, Map<String, Object> svcConfigMap) {

        setValue(aSMsg.svcSlnSq_A0, (BigDecimal) svcConfigMap.get(SVC_SLN_SQ));
        setValue(aSMsg.svcSlnNm_A0, (String) svcConfigMap.get(SVC_SLN_NM));
        setValue(aSMsg.svcConfigMstrPk_A0, (BigDecimal) svcConfigMap.get(SVC_CONFIG_MSTR_PK));
        setValue(aSMsg.serNum_A0, (String) svcConfigMap.get(SER_NUM));
        setValue(aSMsg.mdseCd_A0, (String) svcConfigMap.get(MDSE_CD));
        // START 2016/09/20 N.Arai [QC#11616, MOD]
        // setValue(aSMsg.mdseNm_A0, (String) svcConfigMap.get(MDSE_NM));
        setValue(aSMsg.mdseDescShortTxt_A0, (String) svcConfigMap.get(MDSE_DESC_SHORT_TXT));
        // END 2016/09/20 N.Arai [QC#11616, MOD]
        setValue(aSMsg.t_MdlNm_A0, (String) svcConfigMap.get(T_MDL_NM));
        setValue(aSMsg.svcMachMstrPk_A0, (BigDecimal) svcConfigMap.get(SVC_MACH_MSTR_PK));
        setValue(aSMsg.svcMachMstrPk_AP, (BigDecimal) svcConfigMap.get(PRNT_SVC_MACH_MSTR_PK));
        setValue(aSMsg.istlDt_A0, (String) svcConfigMap.get(ISTL_DT));
        setValue(aSMsg.locNm_A0, (String) svcConfigMap.get(BILL_TO_LOC_NM));
        setValue(aSMsg.locNm_A1, (String) svcConfigMap.get(SHIP_TO_LOC_NM));
        setValue(aSMsg.locNm_A2, (String) svcConfigMap.get(SELL_TO_LOC_NM));
        setValue(aSMsg.ezUpTime_A0, (String) svcConfigMap.get(EZUPTIME));
        setValue(aSMsg.ezUpTimeZone_A0, (String) svcConfigMap.get(EZUPTIMEZONE));
    }

    private void mapToSMsgForSvcMemo(Map<String, Object> svcMemoMap, NSAL0540CMsg cMsg) {

        if (svcMemoMap != null) {
            setValue(cMsg.svcMemoPk, (BigDecimal) svcMemoMap.get(SVC_MEMO_PK));
            setValue(cMsg.svcCmntTxt, (String) svcMemoMap.get(SVC_CMNT_TXT));
            setValue(cMsg.ezUpTime, (String) svcMemoMap.get(EZUPTIME));
            setValue(cMsg.ezUpTimeZone, (String) svcMemoMap.get(EZUPTIMEZONE));
        } else {
            clearItemValues(cMsg.svcMemoPk
                            , cMsg.svcCmntTxt
                            , cMsg.ezUpTime
                            , cMsg.ezUpTimeZone);
        }
    }

    private boolean existConfig(NSAL0540SMsg sMsg, BigDecimal svcConfigMstrPk) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0540_ASMsg aSMsg = sMsg.A.no(i);
            if (aSMsg.svcConfigMstrPk_A0.getValue().compareTo(svcConfigMstrPk) == 0) {
                return true;
            }
        }
        return false;
    }

    private void clearItemValues(EZDCItem... items) {
        for (EZDCItem item : items) {
            item.clear();
        }
    }
}
