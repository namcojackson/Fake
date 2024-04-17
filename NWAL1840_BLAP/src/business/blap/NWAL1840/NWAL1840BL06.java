/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1840;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1840.common.NWAL1840CommonLogicForSaveSubmit;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0973E;

import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/26   Fujitsu         T.Murai         Create          N/A
 * 2019/12/07   Fujitsu         M.Ohno          Update          QC#54670
 * 2023/04/28   CITS            R.Kurahashi     Update          QC#61281
 * 2023/10/10   Hitachi         T.Fukuta        Update          CSA-QC#61730
 * 2023/12/06   CITS            K.Ikeda         Update          QC#61281
 * </pre>
 */
public class NWAL1840BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1840CMsg bizMsg = (NWAL1840CMsg) cMsg;
            NWAL1840SMsg glblMsg = (NWAL1840SMsg) sMsg;

            if ("NWAL1840Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_CMN_Save(bizMsg, glblMsg);

            } else if ("NWAL1840Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_CMN_Submit(bizMsg, glblMsg, false);

            } else if ("NWAL1840Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);

            } else if ("NWAL1840Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do Process (CMN_Save)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840Scrn00_CMN_Save(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        doProcess_NWAL1840Scrn00_CMN_Submit(bizMsg, glblMsg, true);
    }

    /**
     * do Process (CMN_Submit)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     * @param isCallSave Called Save Button
     */
    private void doProcess_NWAL1840Scrn00_CMN_Submit(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg, boolean isCallSave) {

        if (!NWAL1840CommonLogicForSaveSubmit.checkAndClearCode(bizMsg)) {
            return;
        }

        // Check Mandatory
        // Mod 2016/09/15 S21_NA#13914
        if (!NWAL1840CommonLogicForSaveSubmit.checkMandatoryField(bizMsg, isCallSave)) {
        //if (!NWAL1840CommonLogicForSaveSubmit.checkMandatoryField(bizMsg)) {
        // Mod 2016/09/15 S21_NA#13914
            return;
        }
        
        // 2023/12/06 QC#61281 K.Ikeda START
        // QC#61281 Add Start
        // Check Payment term
        // if (!NWAL1840CommonLogicForSaveSubmit.checkPmtTermCashDiscCd(bizMsg)) {
        //     return;
        // }
        // QC#61281 Add End
        // 2023/12/06 QC#61281 K.Ikeda END

        // Check Duplication PO Number
        if (NWAL1840CommonLogicForSaveSubmit.checkCustIssPoNum(bizMsg, isCallSave)) {
            return;
        }

        // Add Start 2016/09/20 S21_NA#14578
        // Check Sales Credit Info
        if (!NWAL1840CommonLogicForSaveSubmit.checkSlsCrInfo(bizMsg)) {
            return;
        }
        // Add End 2016/09/20 S21_NA#14578

        // Relation Check
        if (!NWAL1840CommonLogicForSaveSubmit.relationCheck(bizMsg, isCallSave)) {
            return;
        }

        // Check Rerun Price $ Event
        if (NWAL1840CommonLogicForSaveSubmit.needRePricing(bizMsg, glblMsg)) {
            return;
        }
        
        // 2023/12/06 QC#61281 K.Ikeda START
        // Check Payment term
        if (!(bizMsg.xxTotAmt.getValue().compareTo(BigDecimal.ZERO) == 0)) {
            if (!NWAL1840CommonLogicForSaveSubmit.checkPmtTermCashDiscCd(bizMsg)) {
                return;
            }
        };
        // 2023/12/06 QC#61281 K.Ikeda END

        // 2019/12/07 QC#54670 Add Start
        if (!NWAL1840CommonLogicForSaveSubmit.checkISOrderBindToISGroup(bizMsg)) {
            bizMsg.setMessageInfo(NWAM0973E);
            bizMsg.psnNum.setErrorInfo(1, NWAM0973E);
            return;
        }
        // 2019/12/07 QC#54670 Add End

        // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
        if (!NWAL1840CommonLogicForSaveSubmit.checkNotesRegistered(bizMsg, isCallSave)) {
            return;
        }

        if (!NWAL1840CommonLogicForSaveSubmit.checkMultiRegSchdAgmtForMach(bizMsg, isCallSave)) {
            return;
        }
        // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]

        // Check Exclusion
        if (NWAL1840CommonLogicForSaveSubmit.isModifiedByOtherUser(bizMsg, glblMsg)) {
            return;
        }

        // Record Lock
        if (!NWAL1840CommonLogicForSaveSubmit.executeRecordLock(bizMsg)) {
            return;
        }

        // Call Contact Update API
        // Mod Start 2016/07/25 S21_NA#11714
        // NWAL1840CommonLogicForSaveSubmit.callContactUpdateApi(bizMsg);
        if (!NWAL1840CommonLogicForSaveSubmit.callContactUpdateApi(bizMsg)) {
            return;
        }
        // Mod Start 2016/07/25 S21_NA#11714

        // Call Supply Quote Update API
        NWAL1840CommonLogicForSaveSubmit.callSchdAgmtUpdateApi(bizMsg, glblMsg, isCallSave);

    }

}
