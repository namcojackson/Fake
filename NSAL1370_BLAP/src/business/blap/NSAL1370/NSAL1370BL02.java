/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1370;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1370.common.NSAL1370CommonLogic;
import business.blap.NSAL1370.constant.NSAL1370Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NSAL1370BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Osawa         Create          N/A
 * 2017/10/26   Hitachi         K.Kojima        Update          QC#21556
 * 2018/07/09   Fujitsu         K.Ishizuka      Update          QC#26528
 *</pre>
 */
public class NSAL1370BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NSAL1370CMsg bizMsg = (NSAL1370CMsg) cMsg;

            if ("NSAL1370_INIT".equals(screenAplID)) {
                doProcess_NSAL1370_INIT(bizMsg);

            } else if ("NSAL1370Scrn00_Add_Tier".equals(screenAplID)) {
                doProcess_NSAL1370Scrn00_Add_Tier(bizMsg);

            } else if ("NSAL1370Scrn00_Delete_Tier".equals(screenAplID)) {
                doProcess_NSAL1370Scrn00_Delete_Tier(bizMsg);

            } else if ("NSAL1370Scrn00_CMN_Close".equals(screenAplID)) {
                doProcess_NSAL1370Scrn00_CMN_Close(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1370Scrn00_CMN_Close(NSAL1370CMsg bizMsg) {
        if (NSAL1370Constant.PROC_MD_UPD.equals(bizMsg.xxProcMd_P.getValue())) {
            NSAL1370CommonLogic.closeCheck(bizMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1370_INIT(NSAL1370CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        NSAL1370CommonLogic.setMdlDescTxt(bizMsg);
        NSAL1370CommonLogic.setBllgMtrLbDescTxt(bizMsg);
        // START 2017/10/26 K.Kojima [QC#21556,ADD]
        NSAL1370CommonLogic.setXxDplyCtrlFlg(bizMsg);
        // END 2017/10/26 K.Kojima [QC#21556,ADD]

        EZDMsg.copy(bizMsg.Q, "Q", bizMsg.A, "A");
        if (bizMsg.Q.getValidCount() == 0) {
            NSAL1370CommonLogic.setAddTier(bizMsg); // 1st line
            // 2018/07/09 S21_NA#26528 Del Start
            // NSAL1370CommonLogic.setAddTier(bizMsg); // 2nd line
        } // else if (bizMsg.Q.getValidCount() == 1) {
            // NSAL1370CommonLogic.setAddTier(bizMsg); // 2nd line
        // }
        // 2018/07/09 S21_NA#26528 Del End
    }

    /**
     * Add_Tier Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1370Scrn00_Add_Tier(NSAL1370CMsg bizMsg) {
        NSAL1370CommonLogic.setAddTier(bizMsg);
    }

    /**
     * Delete_Tier Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1370Scrn00_Delete_Tier(NSAL1370CMsg bizMsg) {
        NSAL1370CommonLogic.deleteTier(bizMsg);
    }

}
