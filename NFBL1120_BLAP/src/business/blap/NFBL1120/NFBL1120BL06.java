/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFBL1120;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFBL1120.common.NFBL1120CommonLogic;
import business.blap.NFBL1120.constant.NFBL1120Constant;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NFBL1120BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/05   Hitachi         J.Kim           Update          QC#5521
 *</pre>
 */
public class NFBL1120BL06 extends S21BusinessHandler implements NFBL1120Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NFBL1120CMsg bizMsg = (NFBL1120CMsg) cMsg;
            NFBL1120SMsg glblMsg = (NFBL1120SMsg) sMsg;

            if ("NFBL1120Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);
            } else if ("NFBL1120Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);
            } else if ("NFBL1120Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NFBL1120Scrn00_DeleteSearch(bizMsg, glblMsg);
            } else if ("NFBL1120Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NFBL1120Scrn00_SaveSearch(bizMsg, glblMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Delete_Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFBL1120Scrn00_DeleteSearch(NFBL1120CMsg bizMsg, NFBL1120SMsg glblMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        // set API parameter
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        // call Search Option API
        if (NFBL1120CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
            NFBL1120CommonLogic.createPulldownListSaveOpt(bizMsg, getContextUserInfo().getUserId(), glblCmpyCd);
            bizMsg.srchOptPk_S.clear();
        }
    }

    /**
     * Save_Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFBL1120Scrn00_SaveSearch(NFBL1120CMsg bizMsg, NFBL1120SMsg glblMsg) {

        if (NFBL1120CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, NFBM0226E, new String[] {"Save", "Search Option Name" });
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        String userId = getContextUserInfo().getUserId();

        // set search option value to API parameter
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S) && NFBL1120CommonLogic.isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);
        } else {
            NFBL1120CommonLogic.setSelectSaveSearchName(bizMsg, pMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, userId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.apBatNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.prntVndNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.locNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.apMaintInvStsCd_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.varCharConstNm_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.apInvNum);

        if (ZYPCommonFunc.hasValue(bizMsg.apBatDt)) {
            if (ZYPDateUtil.isValidDate(bizMsg.apBatDt.getValue(), YYYYMMDD)) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.apBatDt.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.invDt)) {
            if (ZYPDateUtil.isValidDate(bizMsg.invDt.getValue(), YYYYMMDD)) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.invDt.getValue());
            }
        }

        // call Search Option API
        if (NFBL1120CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
            NFBL1120CommonLogic.createPulldownListSaveOpt(bizMsg, userId, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
        }
    }

}
