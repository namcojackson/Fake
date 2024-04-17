/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0110;

import static business.blap.NSAL0110.constant.NSAL0110Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.blap.NSAL0110.common.NSAL0110CommonLogic.i18n;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0110.common.NSAL0110CommonLogic;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 *
 * Contract Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/12   Hitachi         Y.Igarashi      Create          N/A
 * 2013/11/19   Hitachi         T.Arakawa       Update          QC2852
 * 2015/11/02   Hitachi         K.Kasai         Update          N/A
 * 2015/12/11   Hitachi         T.Kanasaka      Update          QC#1586
 * 2017/01/30   Hitachi         K.Kitachi       Update          QC#17308
 *</pre>
 */
public class NSAL0110BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0110_INIT".equals(screenAplID)) {
                doProcess_NSAL0110Scrn00_init((NSAL0110CMsg) cMsg, (NSAL0110SMsg) sMsg);

            } else if ("NSAL0110Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0110Scrn00_Search((NSAL0110CMsg) cMsg, (NSAL0110SMsg) sMsg);

            } else if ("NSAL0110Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0110Scrn00_PageNext((NSAL0110CMsg) cMsg, (NSAL0110SMsg) sMsg);

            } else if ("NSAL0110Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0110Scrn00_PagePrev((NSAL0110CMsg) cMsg, (NSAL0110SMsg) sMsg);

            } else if ("NSAL0110Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL0110Scrn00_CMN_Clear((NSAL0110CMsg) cMsg, (NSAL0110SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0110Scrn00_init(NSAL0110CMsg cMsg, NSAL0110SMsg sMsg) {
        createPulldownList(cMsg);
        boolean isInitSearch = hasParam(cMsg);
        if (!hasValue(cMsg.dsContrCtrlStsCd_HV)) {
            setValue(cMsg.dsContrCtrlStsCd_HV, DS_CONTR_CTRL_STS.ACTIVE);
        }

        setDefaultMode(cMsg);
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem10Txt_SX, cMsg.xxScrItem10Txt_SV);

        if (isInitSearch) {
            NSAL0110CommonLogic.searchContractList(cMsg, sMsg, getGlobalCompanyCode());
            // START 2017/01/30 K.Kitachi [QC#17308, DEL]
//            NSAL0110CommonLogic.createDisplayNum(cMsg);
            // END 2017/01/30 K.Kitachi [QC#17308, DEL]
        }
    }

    @SuppressWarnings("unchecked")
    private void createPulldownList(NSAL0110CMsg cMsg) {

        // Contract Status
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", getGlobalCompanyCode());
        param.put("contrAvalFlg", FLG_ON_Y);
        param.put("contrMachAvalFlg", FLG_ON_Y);
        param.put("actvFlg", FLG_ON_Y);
        param.put("slsDt", getSalesDate());
        // START 2015/12/11 T.Kanasaka [QC#1586, DEL]
//        S21SsmEZDResult result = NSAL0110Query.getInstance().searchContrCtrStsHeader(param);
//        if (result != null && result.isCodeNormal()) {
//            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
//            for (int i = 0; i < list.size(); i++) {
//                Map<String, Object> valueMap = list.get(i);
//                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd_HC.no(i), (String) valueMap.get("DS_CONTR_CTRL_STS_CD"));
//                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsNm_HC.no(i), (String) valueMap.get("DS_CONTR_CTRL_STS_NM"));
//            }
//        }
        // END 2015/12/11 T.Kanasaka [QC#1586, DEL]
        // START 2015/12/11 T.Kanasaka [QC#1586, ADD]
        param.put("endDt", END_DT);
        S21SsmEZDResult result = NSAL0110Query.getInstance().searchContrCtrSts(param);
        if (result != null && result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> valueMap = list.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd_HC.no(i), (String) valueMap.get("DS_CONTR_CTRL_STS_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsNm_HC.no(i), (String) valueMap.get("DS_CONTR_CTRL_STS_NM"));
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd_DC.no(i), (String) valueMap.get("DS_CONTR_CTRL_STS_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsNm_DC.no(i), (String) valueMap.get("DS_CONTR_CTRL_STS_NM"));
            }
        }
        // END 2015/12/11 T.Kanasaka [QC#1586, ADD]

        // Contract Category
        result = NSAL0110Query.getInstance().searchContractCategory(param);
        if (result != null && result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> valueMap = list.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCatgCd_SC.no(i), (String) valueMap.get("DS_CONTR_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCatgDescTxt_SC.no(i), (String) valueMap.get("DS_CONTR_CATG_DESC_TXT"));
            }
        }

        // Contract Class
        result = NSAL0110Query.getInstance().searchContractClass(param);
        if (result != null && result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> valueMap = list.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrClsCd_SC.no(i), (String) valueMap.get("DS_CONTR_CLS_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrClsDescTxt_SC.no(i), (String) valueMap.get("DS_CONTR_CLS_DESC_TXT"));
            }
        }

        // Detail Type
        result = NSAL0110Query.getInstance().searchContractDetailType(param);
        if (result != null && result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> valueMap = list.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrDtlTpCd_SC.no(i), (String) valueMap.get("DS_CONTR_DTL_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrDtlTpDescTxt_SC.no(i), (String) valueMap.get("DS_CONTR_DTL_TP_DESC_TXT"));
            }
        }

        // START 2015/12/11 T.Kanasaka [QC#1586, DEL]
//        // Detail Status
//        result = NSAL0110Query.getInstance().searchContractDetailStatus(param);
//        if (result != null && result.isCodeNormal()) {
//            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
//            for (int i = 0; i < list.size(); i++) {
//                Map<String, Object> valueMap = list.get(i);
//                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd_DC.no(i), (String) valueMap.get("DS_CONTR_CTRL_STS_CD"));
//                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsNm_DC.no(i), (String) valueMap.get("DS_CONTR_CTRL_STS_NM"));
//            }
//        }
        // END 2015/12/11 T.Kanasaka [QC#1586, DEL]

        // [QC2852] 2013-11-19 MOD-S
        ZYPPulldownValueSetter.insertFirstData(DISPLAY_MODE_DETAIL, cMsg.xxScrItem10Txt_CD, i18n(DISPLAY_MODE_DETAIL), cMsg.xxScrItem10Txt_NM);
        ZYPPulldownValueSetter.insertFirstData(DISPLAY_MODE_SUMMARY, cMsg.xxScrItem10Txt_CD, i18n(DISPLAY_MODE_SUMMARY), cMsg.xxScrItem10Txt_NM);
        // [QC2852] 2013-11-19 MOD-E
    }

    private void setDefaultMode(NSAL0110CMsg cMsg) {
        if (!hasValue(cMsg.xxScrItem10Txt_SV)) {
            setValue(cMsg.xxScrItem10Txt_SV, DISPLAY_MODE_SUMMARY);
            return;
        }

        if (!DISPLAY_MODE_SUMMARY.equals(cMsg.xxScrItem10Txt_SV.getValue())
                && !DISPLAY_MODE_DETAIL.equals(cMsg.xxScrItem10Txt_SV.getValue())) {
            setValue(cMsg.xxScrItem10Txt_SV, DISPLAY_MODE_SUMMARY);
        }
    }

    private boolean hasParam(NSAL0110CMsg cMsg) {
        if (hasValue(cMsg.dsContrNum_SC)) {
            return true;
        }
        if (hasValue(cMsg.svcContrBrCd_SC)) {
            return true;
        }
        if (hasValue(cMsg.dsContrClsCd_SV)) {
            return true;
        }
        if (hasValue(cMsg.dsContrNm_SC)) {
            return true;
        }
        if (hasValue(cMsg.sellToCustCd_SC)) {
            return true;
        }
        if (hasValue(cMsg.dsContrCatgCd_SV)) {
            return true;
        }
        if (hasValue(cMsg.dsContrCtrlStsCd_HV)) {
            return true;
        }
        if (hasValue(cMsg.dsContrDtlTpCd_SV)) {
            return true;
        }
        if (hasValue(cMsg.dsContrCtrlStsCd_DV)) {
            return true;
        }
        if (hasValue(cMsg.serNum_SC)) {
            return true;
        }
        if (hasValue(cMsg.mdlNm_SC)) {
            return true;
        }
        if (hasValue(cMsg.xxScrItem10Txt_SV)) {
            return true;
        }
        return false;
    }

    private void doProcess_NSAL0110Scrn00_Search(NSAL0110CMsg cMsg, NSAL0110SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        cMsg.A.setValidCount(0);
        ZYPTableUtil.clear(sMsg.A);
        sMsg.A.setValidCount(0);
        NSAL0110CommonLogic.clearPageNum(cMsg);

        if (!NSAL0110CommonLogic.checkParam(cMsg)) {
            return;
        }

        NSAL0110CommonLogic.searchContractList(cMsg, sMsg, getGlobalCompanyCode());
        // START 2017/01/30 K.Kitachi [QC#17308, DEL]
//        NSAL0110CommonLogic.createDisplayNum(cMsg);
        // END 2017/01/30 K.Kitachi [QC#17308, DEL]
    }

    private void doProcess_NSAL0110Scrn00_PageNext(NSAL0110CMsg cMsg, NSAL0110SMsg sMsg) {
        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt());
        cMsg.xxPageShowToNum.clear();

        NSAL0110CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        // START 2017/01/30 K.Kitachi [QC#17308, DEL]
//        NSAL0110CommonLogic.createDisplayNum(cMsg);
        // END 2017/01/30 K.Kitachi [QC#17308, DEL]
    }

    private void doProcess_NSAL0110Scrn00_PagePrev(NSAL0110CMsg cMsg, NSAL0110SMsg sMsg) {
        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1);
        cMsg.xxPageShowToNum.clear();

        NSAL0110CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        // START 2017/01/30 K.Kitachi [QC#17308, DEL]
//        NSAL0110CommonLogic.createDisplayNum(cMsg);
        // END 2017/01/30 K.Kitachi [QC#17308, DEL]
    }

    private void doProcess_NSAL0110Scrn00_CMN_Clear(NSAL0110CMsg cMsg, NSAL0110SMsg sMsg) {
        cMsg.dsContrNum_SC.clear();
        cMsg.svcContrBrCd_SC.clear();
        cMsg.dsContrClsCd_SV.clear();
        cMsg.dsContrNm_SC.clear();
        cMsg.sellToCustCd_SC.clear();
        cMsg.dsContrCatgCd_SV.clear();
        cMsg.dsContrCtrlStsCd_HV.clear();
        cMsg.dsContrDtlTpCd_SV.clear();
        cMsg.dsContrCtrlStsCd_DV.clear();
        cMsg.serNum_SC.clear();
        cMsg.mdlNm_SC.clear();
        setValue(cMsg.xxScrItem10Txt_SV, DISPLAY_MODE_SUMMARY);
        setValue(cMsg.xxScrItem10Txt_SX, DISPLAY_MODE_SUMMARY);
        NSAL0110CommonLogic.clearPageNum(cMsg);
        ZYPTableUtil.clear(cMsg.A);
        cMsg.A.setValidCount(0);
        ZYPTableUtil.clear(sMsg.A);
        sMsg.A.setValidCount(0);
    }
}

