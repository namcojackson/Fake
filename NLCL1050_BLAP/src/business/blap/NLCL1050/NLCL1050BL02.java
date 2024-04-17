package business.blap.NLCL1050;

import static business.blap.NLCL1050.constant.NLCL1050Constant.EVENT_NM_NLCL1050_ADDLINE;
import static business.blap.NLCL1050.constant.NLCL1050Constant.EVENT_NM_NLCL1050_CMN_CLEAR;
import static business.blap.NLCL1050.constant.NLCL1050Constant.EVENT_NM_NLCL1050_DELETE;
import static business.blap.NLCL1050.constant.NLCL1050Constant.EVENT_NM_NLCL1050_DELETELINE;
import static business.blap.NLCL1050.constant.NLCL1050Constant.EVENT_NM_NLCL1050_INIT;
import static business.blap.NLCL1050.constant.NLCL1050Constant.EVENT_NM_NLCL1050_SAVE;
import static business.blap.NLCL1050.constant.NLCL1050Constant.EVENT_NM_NLCL1050_SEARCH;
import static business.blap.NLCL1050.constant.NLCL1050Constant.EVENT_NM_DELETE;
import static business.blap.NLCL1050.constant.NLCL1050Constant.EVENT_NM_SEARCH;
import static business.blap.NLCL1050.constant.NLCL1050Constant.EVENT_NM_SAVE;
import static business.blap.NLCL1050.constant.NLCL1050Constant.NLCM0203E;
import static business.blap.NLCL1050.constant.NLCL1050Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL1050.common.NLCL1050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NLCL1050 ABC Class Setup
 * Function Name : search business process
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/08/2016   CITS            N Akaishi       Create          n/a
 * </pre>
 */
public class NLCL1050BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL1050_INIT.equals(screenAplID)) {
                doProcess_NLCL1050_INIT((NLCL1050CMsg) cMsg, EVENT_NM_NLCL1050_INIT);
            } else if (EVENT_NM_NLCL1050_SEARCH.equals(screenAplID)) {
                doProcess_NLCL1050Scrn00_Search((NLCL1050CMsg) cMsg, EVENT_NM_SEARCH);
            } else if (EVENT_NM_NLCL1050_ADDLINE.equals(screenAplID)) {
                doProcess_NLCL1050Scrn00_AddLine((NLCL1050CMsg) cMsg);
            } else if (EVENT_NM_NLCL1050_DELETELINE.equals(screenAplID)) {
                doProcess_NLCL1050Scrn00_DeleteLine((NLCL1050CMsg) cMsg);
            } else if (EVENT_NM_NLCL1050_CMN_CLEAR.equals(screenAplID)) {
                doProcess_NLCL1050Scrn00_CMN_Clear((NLCL1050CMsg) cMsg);
            } else if (EVENT_NM_NLCL1050_SAVE.equals(screenAplID)) {
                doProcess_NLCL1050Scrn00_Search((NLCL1050CMsg) cMsg, EVENT_NM_SAVE);
            } else if (EVENT_NM_NLCL1050_DELETE.equals(screenAplID)) {
                doProcess_NLCL1050_Delete((NLCL1050CMsg) cMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * 
     * doProcess_NLCL1050Scrn00_Search
     * 
     * @param cMsg NLCL1050CMsg
     * @param eventName String
     */
    private void doProcess_NLCL1050Scrn00_Search(NLCL1050CMsg cMsg, String eventName) {

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String abcAnlsClsNm = cMsg.abcAnlsClsNm.getValue();

        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);

        ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsNm, abcAnlsClsNm);
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, glblCmpyCd);

        NLCL1050CommonLogic.getAbcAnlsClsList(cMsg);

        if ("E".equals(cMsg.getMessageKind())) {
            cMsg.clear();
            cMsg.A.clear();

            ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsNm, abcAnlsClsNm);
            ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, glblCmpyCd);

            return;

        }

        if (eventName.equals(EVENT_NM_SEARCH)) {
            cMsg.setMessageInfo(ZZZM9003I, new String[] {EVENT_NM_SEARCH});
        } else if (eventName.equals(EVENT_NM_SAVE)) {
            cMsg.setMessageInfo(ZZZM9003I, new String[] {EVENT_NM_SAVE});
        }

    }

    /**
     * 
     * doProcess_NLCL1050_INIT
     * 
     * @param cMsg NLCL1050CMsg
     * @param eventName String
     */
    private void doProcess_NLCL1050_INIT(NLCL1050CMsg cMsg, String eventName) {

        String hAbcAnlsClsNum = cMsg.abcAnlsClsNm.getValue();

        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());

        if (eventName.equals(EVENT_NM_NLCL1050_INIT)) {

            ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsNm, hAbcAnlsClsNum);

            if (ZYPCommonFunc.hasValue(cMsg.abcAnlsClsNm)) {
                doProcess_NLCL1050Scrn00_Search(cMsg, EVENT_NM_SEARCH);
            }
        }

    }

    /**
     * doProcess_NLCL1050_Delete
     * @param cMsg NLCL1050CMsg
     */
    private void doProcess_NLCL1050_Delete(NLCL1050CMsg cMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxSupdFlg.getValue())) {
            String hAbcAnlsClsNum = cMsg.abcAnlsClsNm.getValue();

            cMsg.clear();
            ZYPTableUtil.clear(cMsg.A);

            ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsNm, hAbcAnlsClsNum);
            ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
            cMsg.setMessageInfo(ZZZM9003I, new String[] {EVENT_NM_DELETE});
        }
    }

    /**
     * 
     * doProcess_NLCL1050Scrn00_AddLine
     * 
     * @param cMsg NLCL1050CMsg
     */
    private void doProcess_NLCL1050Scrn00_AddLine(NLCL1050CMsg cMsg) {

        // add detail line to CMsg
        int validCountA = cMsg.A.getValidCount();

        ZYPEZDItemValueSetter.setValue(cMsg.A.no(validCountA).abcAnlsClsPrtyNum, BigDecimal.valueOf(validCountA + 1));
        validCountA++;
        cMsg.A.setValidCount(validCountA);
    }

    /**
     * 
     * doProcess_NLCL1050Scrn00_DeleteLine
     * 
     * @param cMsg NLCL1050CMsg
     */
    private void doProcess_NLCL1050Scrn00_DeleteLine(NLCL1050CMsg cMsg) {

        // delete detail line from CMsg

        List<Integer> checkedRows = ZYPTableUtil.getSelectedRows(cMsg.A, "xxChkBox", ZYPConstant.CHKBOX_ON_Y);
        if (checkedRows.isEmpty()) {
            cMsg.setMessageInfo(NLCM0203E);
        }

        int lineCnt = cMsg.A.getValidCount();
        if (!checkedRows.isEmpty()) {
            int result = ZYPTableUtil.deleteRows(cMsg.A, checkedRows);
            cMsg.A.setValidCount(lineCnt - result);
        }

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).abcAnlsClsPrtyNum, BigDecimal.valueOf(i + 1));
        }

    }

    /**
     * 
     * doProcess_NLCL1050Scrn00_CMN_Clear
     * 
     * @param cMsg NLCL1050CMsg
     */
    private void doProcess_NLCL1050Scrn00_CMN_Clear(NLCL1050CMsg cMsg) {
        doProcess_NLCL1050_INIT(cMsg, EVENT_NM_NLCL1050_CMN_CLEAR);
    }

}
