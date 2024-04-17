/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2160;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2160.common.NWAL2160CommonLogic;
import business.blap.NWAL2160.constant.NWAL2160Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL2160BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Fujitsu         M.Yamada        Create          N/A
 * 2018/07/06   Fujitsu         K.Ishizuka      Update          QC#26528
 *</pre>
 */
public class NWAL2160BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2160CMsg bizMsg = (NWAL2160CMsg) cMsg;

            if ("NWAL2160_INIT".equals(screenAplID)) {
                doProcess_NWAL2160_INIT(bizMsg);

            } else if ("NWAL2160Scrn00_Add_Tier".equals(screenAplID)) {
                doProcess_NWAL2160Scrn00_Add_Tier(bizMsg);

            } else if ("NWAL2160Scrn00_Delete_Tier".equals(screenAplID)) {
                doProcess_NWAL2160Scrn00_Delete_Tier(bizMsg);

            } else if ("NWAL2160Scrn00_CMN_Close".equals(screenAplID)) {
                doProcess_NWAL2160Scrn00_CMN_Close(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL2160Scrn00_CMN_Close(NWAL2160CMsg bizMsg) {
        if (NWAL2160Constant.PROC_MD_UPD.equals(bizMsg.xxProcMd_P.getValue())) {
            NWAL2160CommonLogic.closeCheck(bizMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2160_INIT(NWAL2160CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        NWAL2160CommonLogic.setMdlDescTxt(bizMsg);
        NWAL2160CommonLogic.setBllgMtrLbDescTxt(bizMsg);

        EZDMsg.copy(bizMsg.Q, "Q", bizMsg.A, "A");
        if (bizMsg.Q.getValidCount() == 0) {
            NWAL2160CommonLogic.setAddTier(bizMsg); // 1st line
            // 2018/07/06 S21_NA#26528 Del Start
            // NWAL2160CommonLogic.setAddTier(bizMsg); // 2nd line
        } // else if (bizMsg.Q.getValidCount() == 1) {
            // NWAL2160CommonLogic.setAddTier(bizMsg); // 2nd line
        // }
        // 2018/07/06 S21_NA#26528 Del End
    }

    /**
     * Add_Tier Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2160Scrn00_Add_Tier(NWAL2160CMsg bizMsg) {
        NWAL2160CommonLogic.setAddTier(bizMsg);
    }

    /**
     * Delete_Tier Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2160Scrn00_Delete_Tier(NWAL2160CMsg bizMsg) {
        NWAL2160CommonLogic.deleteTier(bizMsg);
    }

}
