/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1430;

import static business.blap.NPAL1430.constant.NPAL1430Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1430.constant.NPAL1430Constant.DB_PARAM_T_MDL_NM;
import static business.blap.NPAL1430.constant.NPAL1430Constant.EVENT_ADD_LINE;
import static business.blap.NPAL1430.constant.NPAL1430Constant.EVENT_CMN_SUBMIT;
import static business.blap.NPAL1430.constant.NPAL1430Constant.EVENT_DELETE_LINE;
import static business.blap.NPAL1430.constant.NPAL1430Constant.EVENT_SEARCH;
import static business.blap.NPAL1430.constant.NPAL1430Constant.NMAM0038I;
import static business.blap.NPAL1430.constant.NPAL1430Constant.NMAM8181W;
import static business.blap.NPAL1430.constant.NPAL1430Constant.NPAM1199E;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1430.constant.NPAL1430Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1430 Reman Standard Parts Setup
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/18/2016   CITS            S.Tanikawa      Create          N/A
 * 08/28/2016   CITS            T.Gotoda        Update          QC#13404
 *</pre>
 */
public class NPAL1430BL02 extends S21BusinessHandler {
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_SEARCH.equals(screenAplID)) {
                doProcess_Search((NPAL1430CMsg) cMsg, (NPAL1430SMsg) sMsg);
            } else if (EVENT_DELETE_LINE.equals(screenAplID)) {
                doProcess_DeleteLine((NPAL1430CMsg) cMsg, (NPAL1430SMsg) sMsg);
            } else if (EVENT_ADD_LINE.equals(screenAplID)) {
                doProcess_AddLine((NPAL1430CMsg) cMsg, (NPAL1430SMsg) sMsg);
            } else if (EVENT_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_Search((NPAL1430CMsg) cMsg, (NPAL1430SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Search
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_Search(NPAL1430CMsg cMsg, NPAL1430SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        String glblCmpyCd = getGlobalCompanyCode();

        // Search
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_T_MDL_NM, cMsg.t_MdlNm);

        // Execute
        S21SsmEZDResult result = NPAL1430Query.getInstance().search(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultMap = (List<Map>) result.getResultObject();
            if (resultMap.size() > cMsg.A.length()) {
                cMsg.setMessageInfo(NMAM8181W, new String[] {String.valueOf(cMsg.A.length()), String.valueOf(cMsg.A.length()) });
            }
            for (int i = 0; i < resultMap.size(); i++) {
                if ((cMsg.A.length()) <= i) {
                    break;
                }
                Map<String, Object> recode = (Map<String, Object>) resultMap.get(i);
                if (i == 0) {
                    ZYPEZDItemValueSetter.setValue(cMsg.rmnfMdlStdPrtDescTxt, (String) recode.get("RMNF_MDL_STD_PRT_DESC_TXT"));
                    ZYPEZDItemValueSetter.setValue(cMsg.lastUpdDt, (String) recode.get("LAST_UPD_DT"));
                }
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mdseCd_A1, (String) recode.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mdseDescShortTxt_A1, (String) recode.get("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).rmnfReqQty_A1, (BigDecimal) recode.get("RMNF_REQ_QTY"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxRqstTs_A1, (String) recode.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxRqstTz_A1, (String) recode.get("EZUPTIMEZONE"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxNewRowNum_A1, ZERO);
                cMsg.A.setValidCount(i + 1);
            }

        } else {
            // not has search result
            String screenAplID = cMsg.getScreenAplID();

            if (!EVENT_CMN_SUBMIT.equals(screenAplID)) {

                cMsg.setMessageInfo(NMAM0038I);

            }

        }
    }

    /**
     * Delete Line
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_AddLine(NPAL1430CMsg cMsg, NPAL1430SMsg sMsg) {
        if (sMsg.A.length() <= sMsg.A.getValidCount()) {
            cMsg.setMessageInfo(NPAM1199E);
            return;
        }
        int index = sMsg.A.getValidCount();
        sMsg.A.no(index).xxChkBox_A1.clear();
        sMsg.A.no(index).delFlg_A1.clear();
        sMsg.A.no(index).mdseCd_A1.clear();
        sMsg.A.no(index).rmnfReqQty_A1.clear();
        sMsg.A.no(index).mdseDescShortTxt_A1.clear();
        sMsg.A.no(index).xxNewRowNum_A1.setValue(ONE);
        sMsg.A.setValidCount(index + 1);
        // copy
        EZDMsg.copy(sMsg.A.no(index), null, cMsg.A.no(cMsg.A.getValidCount()), null);
        cMsg.A.setValidCount(cMsg.A.getValidCount() + 1);
    }

    /**
     * Delete Line
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_DeleteLine(NPAL1430CMsg cMsg, NPAL1430SMsg sMsg) {
        List<NPAL1430_ACMsg> deleteItem = new ArrayList<NPAL1430_ACMsg>();
        List<NPAL1430_ACMsg> normalItem = new ArrayList<NPAL1430_ACMsg>();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.A.no(i).xxChkBox_A1.getValue())) {
                if (cMsg.A.no(i).xxNewRowNum_A1.getValue().compareTo(ONE) == 0) {
                    // added row
                    continue;
                } else {
                    cMsg.A.no(i).delFlg_A1.setValue(ZYPConstant.CHKBOX_ON_Y);
                }
                deleteItem.add((NPAL1430_ACMsg) cMsg.A.no(i).clone());
            } else {
                normalItem.add((NPAL1430_ACMsg) cMsg.A.no(i).clone());
            }
        }

        List<NPAL1430_ASMsg> deleteSItem = new ArrayList<NPAL1430_ASMsg>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).delFlg_A1.getValue())) {
                deleteSItem.add((NPAL1430_ASMsg) sMsg.A.no(i).clone());
            }
        }

        // re-make sMsg
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.A);
        for (int i = 0; i < deleteSItem.size(); i++) {
            EZDMsg.copy((EZDMsg) deleteSItem.get(i), null, sMsg.A.get(i), null);
            sMsg.A.setValidCount(i + 1);
        }
        for (int i = 0, j = sMsg.A.getValidCount(); i < deleteItem.size(); i++, j++) {
            EZDMsg.copy((EZDMsg) deleteItem.get(i), null, sMsg.A.get(j), null);
            sMsg.A.setValidCount(j + 1);
        }
        for (int i = 0, j = sMsg.A.getValidCount(); i < normalItem.size(); i++, j++) {
            EZDMsg.copy((EZDMsg) normalItem.get(i), null, sMsg.A.get(j), null);
            sMsg.A.setValidCount(j + 1);
            EZDMsg.copy((EZDMsg) normalItem.get(i), null, cMsg.A.get(i), null);
            cMsg.A.setValidCount(i + 1);
        }
        if ((0 < deleteItem.size()) || (0 < deleteSItem.size())) {
            // set message to reminder lines are not deleted yet.
            cMsg.setMessageInfo(NPAL1430Constant.NPAM1237W);
        }
    }
}
