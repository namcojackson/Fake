/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0110;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWCL0110.constant.NWCL0110Constant;
import business.db.INV_PRT_CTRLTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/26   Fujitsu         H.Nagashima     Create          N/A
 * 2017/12/21   SRAA            K.Aratani       Update          QC#18692
 *</pre>
 */
public class NWCL0110BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NWCL0110Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWCL0110Scrn00_CMN_Submit((NWCL0110CMsg) cMsg, (NWCL0110SMsg) sMsg);
            } else if ("NWCL0110Scrn00_PrintMassRequest".equals(screenAplID)) {
                doProcess_NWCL0110Scrn00_PrintMassRequest((NWCL0110CMsg) cMsg, (NWCL0110SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Submit)
     * @param cMsg NWCL0110CMsg
     * @param sMsg NWCL0110SMsg
     */
    private void doProcess_NWCL0110Scrn00_CMN_Submit(NWCL0110CMsg cMsg, NWCL0110SMsg sMsg) {

        EZDMsg.copy(cMsg.A, null, sMsg.A, null);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        if (!isAnyCheckBoxChecked(cMsg, sMsg, selectedRows)) {
            return;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NWCL0110_ASMsg asMsg = sMsg.A.no(i);
            if (!selectedRows.contains(i)) {
                continue;
            }
            INV_PRT_CTRLTMsg invPrtCtlTMsg = new INV_PRT_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(invPrtCtlTMsg.glblCmpyCd,   getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(invPrtCtlTMsg.invPrtCtrlPk, asMsg.invPrtCtrlPk_A.getValue());
            invPrtCtlTMsg = (INV_PRT_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdate(invPrtCtlTMsg);

            List<String> updateColumnList = new ArrayList<String>();
            //reptRptBrNum
            updateColumnList.add("reprRptBrNum");
            ZYPEZDItemValueSetter.setValue(invPrtCtlTMsg.reprRptBrNum, asMsg.reprRptBrNum_A.getValue());
            //invPrtProcStsCd
            if (ZYPConstant.CHKBOX_ON_Y.equals(asMsg.invPrtProcStsCd_A.getValue())) {
                updateColumnList.add("invPrtProcStsCd");
                ZYPEZDItemValueSetter.setValue(invPrtCtlTMsg.invPrtProcStsCd, NWCL0110Constant.PROC_STS_UNPROCESSED);
            }
            //invEmlProcStsCd
            if (ZYPConstant.CHKBOX_ON_Y.equals(asMsg.invEmlProcStsCd_A.getValue())) {
                updateColumnList.add("invEmlProcStsCd");
                ZYPEZDItemValueSetter.setValue(invPrtCtlTMsg.invEmlProcStsCd, NWCL0110Constant.PROC_STS_UNPROCESSED);
            }
            //invFtpProcStsCd
            if (ZYPConstant.CHKBOX_ON_Y.equals(asMsg.invFtpProcStsCd_A.getValue())) {
                updateColumnList.add("invFtpProcStsCd");
                ZYPEZDItemValueSetter.setValue(invPrtCtlTMsg.invFtpProcStsCd, NWCL0110Constant.PROC_STS_UNPROCESSED);
            }
            //invEdiProcStsCd
            if (ZYPConstant.CHKBOX_ON_Y.equals(asMsg.invEdiProcStsCd_A.getValue())) {
                updateColumnList.add("invEdiProcStsCd");
                ZYPEZDItemValueSetter.setValue(invPrtCtlTMsg.invEdiProcStsCd, NWCL0110Constant.PROC_STS_UNPROCESSED);
            }
//            //invSpclBillProcStsCd
//            if (ZYPConstant.CHKBOX_ON_Y.equals(asMsg.invSpclBillProcStsCd_A.getValue())) {
//                updateColumnList.add("invSpclBillProcStsCd");
//                ZYPEZDItemValueSetter.setValue(invPrtCtlTMsg.invSpclBillProcStsCd, NWCL0110Constant.PROC_STS_UNPROCESSED);
//            }

            if (!updateColumnList.isEmpty()) {
                String[] updateColumn = (String[]) updateColumnList.toArray(new String[0]);
                EZDTBLAccessor.updateSelectionField(invPrtCtlTMsg, updateColumn);
            }
        }
    }


    /**
     * is Any CheckBox is Checked.
     * @param cMsg NWCL0110CMsg
     * @param sMsg NWCL0110SMsg
     * @param selectedRows Selected Rows
     * @return true : Any Checked
     */
    private boolean isAnyCheckBoxChecked(NWCL0110CMsg cMsg, NWCL0110SMsg sMsg, List<Integer> selectedRows) {

        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NWCL0110Constant.NWCM0096E);
            return false;
        }

        return true;
    }

    /**
     * do process (Submit)
     * @param cMsg NWCL0110CMsg
     * @param sMsg NWCL0110SMsg
     */
    private void doProcess_NWCL0110Scrn00_PrintMassRequest(NWCL0110CMsg cMsg, NWCL0110SMsg sMsg) {

        S21SsmEZDResult ssmResult = NWCL0110Query.getInstance().getInvPrtCtrlForPrintMassRequest(cMsg);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> rsltCustList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (Map<String, Object> rsltMap : rsltCustList) {
                INV_PRT_CTRLTMsg invPrtCtlTMsg = new INV_PRT_CTRLTMsg();
                ZYPEZDItemValueSetter.setValue(invPrtCtlTMsg.glblCmpyCd,   getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(invPrtCtlTMsg.invPrtCtrlPk, (java.math.BigDecimal) rsltMap.get("INV_PRT_CTRL_PK"));
                invPrtCtlTMsg = (INV_PRT_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdate(invPrtCtlTMsg);
                List<String> updateColumnList = new ArrayList<String>();
                //reptRptBrNum
                updateColumnList.add("reprRptBrNum");
                ZYPEZDItemValueSetter.setValue(invPrtCtlTMsg.reprRptBrNum, cMsg.reprRptBrNum.getValue());
                //invPrtProcStsCd
                updateColumnList.add("invPrtProcStsCd");
                ZYPEZDItemValueSetter.setValue(invPrtCtlTMsg.invPrtProcStsCd, NWCL0110Constant.PROC_STS_UNPROCESSED);
                if (!updateColumnList.isEmpty()) {
                    String[] updateColumn = (String[]) updateColumnList.toArray(new String[0]);
                    EZDTBLAccessor.updateSelectionField(invPrtCtlTMsg, updateColumn);
                }
            }
        }
    }
}
