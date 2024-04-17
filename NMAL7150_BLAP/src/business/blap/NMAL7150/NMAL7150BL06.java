/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7150;

import static business.blap.NMAL7150.constant.NMAL7150Constant.CSMP_CONTR_INTF_TABLE_NAME;
import static business.blap.NMAL7150.constant.NMAL7150Constant.NMAM0177E;
import static business.blap.NMAL7150.constant.NMAL7150Constant.NZZM0003E;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7150.common.NMAL7150CommonLogic;
import business.db.CSMP_CONTR_INTFCTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * CSMP Contract Synchronization  Errors Correction
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7150BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7150CMsg bizMsg = (NMAL7150CMsg) cMsg;
            NMAL7150SMsg glblMsg = (NMAL7150SMsg) sMsg;

            if ("NMAL7150Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7150Scrn00_CMN_Submit(bizMsg, glblMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7150Scrn00_CMN_Submit(NMAL7150CMsg bizMsg, NMAL7150SMsg glblMsg) {
        NMAL7150CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NMAL7150_ASMsg glblAMsg = glblMsg.A.no(i);
            NMAL7150_BSMsg glblBMsg = glblMsg.B.no(i);
            if (!NMAL7150CommonLogic.isChangeDetail(glblAMsg, glblBMsg)) {
                continue;
            }

            CSMP_CONTR_INTFCTMsg inTMsg = new CSMP_CONTR_INTFCTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(inTMsg.csmpContrIntfcPk, glblAMsg.csmpContrIntfcPk_A);

            inTMsg = (CSMP_CONTR_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inTMsg);
            if (inTMsg == null) {
                bizMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NZZM0003E);
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            }

            // Check time stamp
            if (!ZYPDateUtil.isSameTimeStamp(glblMsg.A.no(i).ezUpTime_A.getValue(), glblMsg.A.no(i).ezUpTimeZone_A.getValue(), inTMsg.ezUpTime.getValue(), inTMsg.ezUpTimeZone.getValue())) {
                // anyone update
                bizMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NZZM0003E);
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            }
            ZYPEZDItemValueSetter.setValue(inTMsg.dlrRefNum, glblAMsg.dlrRefNum_A);
            ZYPEZDItemValueSetter.setValue(inTMsg.prevCsmpNum, glblAMsg.prevCsmpNum_A);
            ZYPEZDItemValueSetter.setValue(inTMsg.prevUsrTxt, glblAMsg.prevUsrTxt_A);
            ZYPEZDItemValueSetter.setValue(inTMsg.csmpProcStsCd, glblAMsg.csmpProcStsCd_A);

            EZDTBLAccessor.update(inTMsg);
            String returnCode = inTMsg.getReturnCode();
            if (!RTNCD_NORMAL.equals(returnCode)) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {CSMP_CONTR_INTF_TABLE_NAME });
                return;
            }
        }

    }

}
