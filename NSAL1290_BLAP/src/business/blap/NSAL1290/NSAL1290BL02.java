/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1290;

import static business.blap.NSAL1290.constant.NSAL1290Constant.NSAL1290_MTR_LVL_MAX_NUM;
import static business.blap.NSAL1290.constant.NSAL1290Constant.NSAM0015E;
import static business.blap.NSAL1290.constant.NSAL1290Constant.NSAM0045E;
import static business.blap.NSAL1290.constant.NSAL1290Constant.NSAM0112E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL1290.common.NSAL1290CommonLogic;
import business.db.MTR_LBTMsg;
import business.db.MTR_LBTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
/**
 *<pre>
 * Billing Counter Mapping Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/01   Hitachi         M.Gotou         Create          N/A
 * 2016/07/13   Hitachi         A.Kohinata      Update          QC#11813
 * 2017/01/20   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSAL1290BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1290CMsg cMsg = (NSAL1290CMsg) arg0;
        NSAL1290SMsg sMsg = (NSAL1290SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1290_INIT".equals(screenAplID)) {
                doProcess_NSAL1290_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NSAL1290Scrn00_AddLine".equals(screenAplID)) {
                doProcess_NSAL1290Scrn00_AddLine(cMsg, sMsg);
            // START 2017/01/20 K.Ochiai [QC#16331,MOD]
            } else if ("NSAL1290Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL1290Scrn00_CMN_Clear(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            // END 2017/01/20 K.Ochiai [QC#16331,MOD]
            } else if ("NSAL1290Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1290Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSAL1290Scrn00_DeleteLine".equals(screenAplID)) {
                doProcess_NSAL1290Scrn00_DeleteLine(cMsg, sMsg);
            } else if ("NSAL1290Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL1290Scrn00_Search(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1290_INIT(NSAL1290CMsg cMsg, NSAL1290SMsg sMsg) {

        NSAL1290CommonLogic.clearMsg(cMsg, sMsg);
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));

        int maxCnt = ZYPCodeDataUtil.getNumConstValue(NSAL1290_MTR_LVL_MAX_NUM, cMsg.glblCmpyCd.getValue()).intValue();

        for (int i = 1; i <= maxCnt; i++) {
            String strVal = Integer.valueOf(i).toString();
            setValue(cMsg.xxDplyByCtrlItemCd_01.no(i - 1), strVal);
            setValue(cMsg.xxDplyByCtrlItemCdNm_01.no(i - 1), strVal);
        }

        // add start 2016/07/13 QC#11813
        if (ZYPCommonFunc.hasValue(cMsg.mtrLbNm_P)) {
            setValue(cMsg.mtrLbNm_H, cMsg.mtrLbNm_P);
            doProcess_NSAL1290Scrn00_Search(cMsg, sMsg);
        }
        // add end 2016/07/13 QC#11813
    }

    private void doProcess_NSAL1290Scrn00_AddLine(NSAL1290CMsg cMsg, NSAL1290SMsg sMsg) {

        NSAL1290CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        if (sMsg.A.getValidCount() >= sMsg.A.length()) {
            cMsg.setMessageInfo(NSAM0112E);
            return;
        }

        int newLineIdx = sMsg.A.getValidCount();
        NSAL1290_ASMsg asMsg = sMsg.A.no(newLineIdx);
        setValue(asMsg.xxRowNum_A, new BigDecimal(newLineIdx));
        setValue(asMsg.effFromDt_A, cMsg.slsDt);
        setValue(asMsg.actvFlg_A, ZYPConstant.CHKBOX_ON_Y);
        sMsg.A.setValidCount(newLineIdx + 1);

        NSAL1290CommonLogic.copySMsgToCMsg(cMsg, sMsg);
    }

    private void doProcess_NSAL1290Scrn00_DeleteLine(NSAL1290CMsg cMsg, NSAL1290SMsg sMsg) {

        NSAL1290CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }
        ZYPTableUtil.deleteRows(sMsg.A, selectedRows);

        NSAL1290CommonLogic.copySMsgToCMsg(cMsg, sMsg);
    }

    // START 2017/01/20 K.Ochiai [QC#16331,MOD]
    private void doProcess_NSAL1290Scrn00_CMN_Clear(NSAL1290CMsg cMsg, NSAL1290SMsg sMsg) {

        cMsg.mtrLbNm_P.clear();
        doProcess_NSAL1290_INIT(cMsg, sMsg);

    }
    // END 2017/01/20 K.Ochiai [QC#16331,MOD]

    private void doProcess_NSAL1290Scrn00_CMN_Submit(NSAL1290CMsg cMsg, NSAL1290SMsg sMsg) {

        NSAL1290CommonLogic.getSearchData(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.A);
        int i = 0;
        for (; i < sMsg.A.getValidCount(); i++) {
            if (i >= cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i), null);
        }
        cMsg.A.setValidCount(i);
    }

    private void doProcess_NSAL1290Scrn00_Search(NSAL1290CMsg cMsg, NSAL1290SMsg sMsg) {

        MTR_LBTMsg inMsg = new MTR_LBTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("mtrLbNm01", cMsg.mtrLbNm_H.getValue());
        inMsg.setConditionValue("mtrLbTpCd01", MTR_LB_TP.REGULAR_METER);

        MTR_LBTMsgArray tMsgArray = (MTR_LBTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
            cMsg.mtrLbNm_H.setErrorInfo(1, NSAM0045E, new String[] {"Regular Counter" });
            cMsg.mdlMtrLbCd_H.clear();
            cMsg.mtrIdxCd_H.clear();
            return;
        }
        setValue(cMsg.mdlMtrLbCd_H, tMsgArray.no(0).mtrLbCd);
        setValue(cMsg.mtrIdxCd_H, tMsgArray.no(0).mtrIdxCd);
        setValue(cMsg.mtrLbNm_H, tMsgArray.no(0).mtrLbNm);

        cMsg.setCommitSMsg(true);

        NSAL1290CommonLogic.getSearchData(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.A);
        int i = 0;
        for (; i < sMsg.A.getValidCount(); i++) {
            if (i >= cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i), null);
        }
        cMsg.A.setValidCount(i);
    }

}
