/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0120;

import static business.blap.NWCL0120.constant.NWCL0120Constant.COMMA;
import static business.blap.NWCL0120.constant.NWCL0120Constant.ERR_MSG_TXT_MAX_LENGTH;
import static business.blap.NWCL0120.constant.NWCL0120Constant.NWAM0496W;
import static business.blap.NWCL0120.constant.NWCL0120Constant.NWCM0003I;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWCL0120.common.NWCL0120CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWCL0120BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWCL0120BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWCL0120CMsg bizMsg = (NWCL0120CMsg) cMsg;
            NWCL0120SMsg glblMsg = (NWCL0120SMsg) sMsg;

            if ("NWCL0120_INIT".equals(screenAplID)) {
                doProcess_NWCL0120_INIT(bizMsg, glblMsg);

            } else if ("NWCL0120Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWCL0120Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NWCL0120Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWCL0120Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NWCL0120Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWCL0120Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NWCL0120Scrn00_Search".equals(screenAplID)) {
                doProcess_NWCL0120Scrn00_Search(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0120_INIT(NWCL0120CMsg bizMsg, NWCL0120SMsg glblMsg) {

        ZYPGUITableColumn.getColData((NWCL0120CMsg) bizMsg, (NWCL0120SMsg) glblMsg, "AHEAD");
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0120Scrn00_CMN_Submit(NWCL0120CMsg bizMsg, NWCL0120SMsg glblMsg) {

        // Confirm Invoice Print Control Update
        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
            return;
        }
        search(bizMsg, glblMsg);
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0120Scrn00_PageNext(NWCL0120CMsg bizMsg, NWCL0120SMsg glblMsg) {

        NWCL0120CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        NWCL0120CommonLogic.setPageNext(bizMsg, glblMsg);
    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0120Scrn00_PagePrev(NWCL0120CMsg bizMsg, NWCL0120SMsg glblMsg) {

        NWCL0120CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        NWCL0120CommonLogic.setPagePrev(bizMsg, glblMsg);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWCL0120Scrn00_Search(NWCL0120CMsg bizMsg, NWCL0120SMsg glblMsg) {

        // search
        search(bizMsg, glblMsg);
        ZYPGUITableColumn.getColData((NWCL0120CMsg) bizMsg, (NWCL0120SMsg) glblMsg);
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NWCL0120CMsg bizMsg, NWCL0120SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NWCL0120Query.getInstance().getEasyPacInvoiceList(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setMessageInfo(NWCM0003I);

        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NWAM0496W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            int pkCount = 0;
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

                if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).invNum_A0)) {
                    // Get Error Message
                    S21SsmEZDResult invErrRes = NWCL0120Query.getInstance().getInvoiceErr(bizMsg, glblMsg.A.no(i).invNum_A0.getValue());
                    if (invErrRes.isCodeNormal()) {
                        List<String> list = (List<String>) invErrRes.getResultObject();
                        if (list == null || list.size() == 0) {
                            continue;
                        }

                        StringBuilder sbErrMsgTxt = new StringBuilder();
                        int index = 0;
                        for (String errTxt : list) {
                            if (index > 0) {
                                sbErrMsgTxt.append(COMMA);
                            }
                            sbErrMsgTxt.append(errTxt);
                            index++;
                        }

                        String errMsgTxt = sbErrMsgTxt.toString();
                        if (errMsgTxt.length() > ERR_MSG_TXT_MAX_LENGTH) {
                            errMsgTxt = errMsgTxt.substring(0, ERR_MSG_TXT_MAX_LENGTH);
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).invErrMsgTxt_A0, errMsgTxt);

                    } else {

                        // Get Invoice Print Control Pk
                        S21SsmEZDResult ctrlPkRes = NWCL0120Query.getInstance().getInvPrtCtrlPk(bizMsg, glblMsg.A.no(i).invNum_A0.getValue());

                        if (ctrlPkRes.isCodeNormal()) {
                            List<Map<String, Object>> pkList = (List<Map<String, Object>>) ctrlPkRes.getResultObject();
                            if (pkList == null || pkList.size() == 0) {
                                continue;
                            }

                            for (Map<String, Object> map : pkList) {
                                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(pkCount).invNum_B0, (String) map.get("INV_NUM"));
                                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(pkCount).invPrtCtrlPk_B0, (BigDecimal) map.get("INV_PRT_CTRL_PK"));
                                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(pkCount).ezUpTime_B0, (String) map.get("EZUPTIME"));
                                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(pkCount).ezUpTimeZone_B0, (String) map.get("EZUPTIMEZONE"));
                                pkCount++;
                            }
                        }
                    }
                } else {
                    // Get Hold Reason Text
                    S21SsmEZDResult hldRsnRes = NWCL0120Query.getInstance().getHldRsnTxt(bizMsg, glblMsg.A.no(i).cpoOrdNum_A0.getValue());
                    if (hldRsnRes.isCodeNormal()) {
                        List<String> list = (List<String>) hldRsnRes.getResultObject();
                        if (list == null || list.size() == 0) {
                            continue;
                        }

                        StringBuilder sbErrMsgTxt = new StringBuilder();
                        int index = 0;
                        for (String errTxt : list) {
                            if (index > 0) {
                                sbErrMsgTxt.append(COMMA);
                            }
                            sbErrMsgTxt.append(errTxt);
                            index++;
                        }

                        String errMsgTxt = sbErrMsgTxt.toString();
                        if (errMsgTxt.length() > ERR_MSG_TXT_MAX_LENGTH) {
                            errMsgTxt = errMsgTxt.substring(0, ERR_MSG_TXT_MAX_LENGTH);
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).invErrMsgTxt_A0, errMsgTxt);
                    }
                }
            }
            glblMsg.B.setValidCount(pkCount);

            NWCL0120CommonLogic.setPageSearch(bizMsg, glblMsg);
        }
    }
}
