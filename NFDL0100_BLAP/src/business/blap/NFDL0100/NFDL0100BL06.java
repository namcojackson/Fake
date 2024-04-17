/**
 * Copyright(c)2011 Canon USA Inc. All rights reserved.
 */

package business.blap.NFDL0100;


import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFDL0100.constant.NFDL0100Constant;
import business.db.INV_PRT_CTRLTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Print Invoice
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NFDL0100BL06 extends S21BusinessHandler implements NFDL0100Constant {

    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            NFDL0100CMsg bizMsg = (NFDL0100CMsg) cMsg;
            NFDL0100SMsg globalMsg = (NFDL0100SMsg) sMsg;

            if ("NFDL0100Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, globalMsg);
            } else if ("NFDL0100Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, globalMsg);
            } else if ("NFDL0100_NWCL0060".equals(screenAplID)) {
                doProcess_NFDL0100_NWCL0060(bizMsg, globalMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID :" + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }


    /**
     * <pre>
     * return from NWCL0060
     * </pre>
     * @param cMsg NFDL0100CMsg
     * @param sMsg NFDL0100SMsg
     */
    private void doProcess_NFDL0100_NWCL0060(NFDL0100CMsg bizMsg, NFDL0100SMsg globalMsg) {

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(globalMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);

        int i = 0;
        for (; i < selectedRows.size(); i++) {
            int checkedRowNum = selectedRows.get(i);

            INV_PRT_CTRLTMsg invPrtCtrlTMsg = new INV_PRT_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invPrtCtrlPk, globalMsg.A.no(checkedRowNum).invPrtCtrlPk_A.getValue());
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.scrInvEmlTs, bizMsg.scrInvEmlTs.getValue());
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.scrInvEmlAddr, bizMsg.scrInvEmlAddr.getValue());
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.scrInvEmlSubjTxt, bizMsg.scrInvEmlSubjTxt.getValue());
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.scrInvEmlCmntTxt, bizMsg.scrInvEmlCmntTxt.getValue());

            S21ApiTBLAccessor.updateSelectionField(invPrtCtrlTMsg, new String[]{"scrInvEmlTs","scrInvEmlAddr","scrInvEmlSubjTxt","scrInvEmlCmntTxt"});

        }
    }
}
