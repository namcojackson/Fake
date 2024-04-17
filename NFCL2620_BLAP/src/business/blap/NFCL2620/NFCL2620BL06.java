/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFCL2620;

import static business.blap.NFCL2620.constant.NFCL2620Constant.BUSINESS_ID;
import static business.blap.NFCL2620.constant.NFCL2620Constant.NFCM0865E;
import static business.blap.NFCL2620.constant.NFCL2620Constant.NZZM0002I;
import static business.blap.NFCL2620.constant.NFCL2620Constant.YYYYMMDD_PATTERN;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL2620.common.NFCL2620CommonLogic;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * AR Refund Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/08   Fujitsu         C.Tanaka        Create          QC#5521
 * 2022/07/20   Hitachi         A.Kohinata      Update          QC#60113
 * 2022/08/01   Hitachi         A.Kohinata      Update          QC#60113-1
 *</pre>
 */
public class NFCL2620BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NFCL2620CMsg cMsg = (NFCL2620CMsg) arg0;
        NFCL2620SMsg sMsg = (NFCL2620SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NFCL2620_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(cMsg, sMsg);
            } else if ("NFCL2620Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(cMsg, sMsg);
            } else if ("NFCL2620Scrn00_Delete_Search".equals(screenAplID)) {
                doProcess_NFCL2620Scrn00_Delete_Search(cMsg, sMsg);
            } else if ("NFCL2620Scrn00_Save_Search".equals(screenAplID)) {
                doProcess_NFCL2620Scrn00_Save_Search(cMsg, sMsg);
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
    private void doProcess_NFCL2620Scrn00_Delete_Search(NFCL2620CMsg bizMsg, NFCL2620SMsg glblMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        // set API parameter
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_ID);

        // call Search Option API
        if (NFCL2620CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
            NFCL2620CommonLogic.createPulldownListSaveOpt(bizMsg, getContextUserInfo().getUserId(), glblCmpyCd);
            bizMsg.srchOptPk.clear();
            bizMsg.setMessageInfo(NZZM0002I);
        }
    }

    /**
     * Save_Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL2620Scrn00_Save_Search(NFCL2620CMsg bizMsg, NFCL2620SMsg glblMsg) {

        if (NFCL2620CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm.setErrorInfo(1, NFCM0865E, new String[] {"Save", "Search Option Name" });
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        String userId = getContextUserInfo().getUserId();

        // set search option value to API parameter
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm) && NFCL2620CommonLogic.isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm);
        } else {
            NFCL2620CommonLogic.setSelectSaveSearchName(bizMsg, pMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, userId);

        if (ZYPCommonFunc.hasValue(bizMsg.arRfRqstPk_H)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.arRfRqstPk_H.getValue().toString());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.arRfTpCd_H);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.billToCustAcctCd_H);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.arRfStsCd_H);
        if (ZYPCommonFunc.hasValue(bizMsg.arRfCratDt_F)) {
            if (ZYPDateUtil.isValidDate(bizMsg.arRfCratDt_F.getValue(), YYYYMMDD_PATTERN)) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.arRfCratDt_F.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(bizMsg.arRfCratDt_T)) {
            if (ZYPDateUtil.isValidDate(bizMsg.arRfCratDt_T.getValue(), YYYYMMDD_PATTERN)) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.arRfCratDt_T.getValue());
            }
        }
        // add start 2022/07/20 QC#60113
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.apPmtChkNum_H);
        // add end 2022/07/20 QC#60113
        // add start 2022/08/01 QC#60113-1
        if (ZYPCommonFunc.hasValue(bizMsg.dealRfAmt_H)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.dealRfAmt_H.getValue().toString());
        }
        // add end 2022/08/01 QC#60113-1

        // call Search Option API
        if (NFCL2620CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
            NFCL2620CommonLogic.createPulldownListSaveOpt(bizMsg, userId, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk, pMsg.srchOptPk);
            bizMsg.srchOptNm.clear();
            bizMsg.setMessageInfo(NZZM0002I);
        }
    }

}
