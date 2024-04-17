/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8820;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.common.NYX.NYXC880001.NYXC880001Query;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import business.blap.NYEL8820.common.NYEL8820CommonLogic;

/**
 *<pre>
 * NYEL8820BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/17   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8820BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NYEL8820CMsg bizMsg = (NYEL8820CMsg) cMsg;
            NYEL8820SMsg glblMsg = (NYEL8820SMsg) sMsg;

            if ("NYEL8820_INIT".equals(screenAplID)) {
                doProcess_NYEL8820_INIT(bizMsg, glblMsg);

            } else if ("NYEL8820Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NYEL8820Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NYEL8820Scrn00_MoveWin_BizScreen".equals(screenAplID)) {
                doProcess_NYEL8820Scrn00_MoveWin_BizScreen(bizMsg, glblMsg);

            } else if ("NYEL8820Scrn00_Continue".equals(screenAplID)) {
                doProcess_NYEL8820Scrn00_NYEL8820Scrn00_Continue(bizMsg, glblMsg);
            
            } else if ("NYEL8820Scrn00_Back".equals(screenAplID)) {
                doProcess_NYEL8820Scrn00_NYEL8820Scrn00_Back(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Message
     * @param glblMsg Message
     */
    private void doProcess_NYEL8820_INIT(NYEL8820CMsg bizMsg, NYEL8820SMsg glblMsg) {
        bizMsg.xxCellIdx.setValue(0);
        NYEL8820CommonLogic.search(bizMsg, glblMsg, getGlobalCompanyCode(), this.getContextUserInfo().getUserId());
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    private void doProcess_NYEL8820Scrn00_CMN_Reset(NYEL8820CMsg bizMsg, NYEL8820SMsg glblMsg) {
        doProcess_NYEL8820_INIT(bizMsg, glblMsg);

    }

    /**
     * MoveWin_BizScreen Event
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    private void doProcess_NYEL8820Scrn00_MoveWin_BizScreen(NYEL8820CMsg bizMsg, NYEL8820SMsg glblMsg) {
        
        // Get ProcessID from UP_TAB,MENU_PROC
// 2018/05/16 MOD START
//        String strMenuProcId = NYXC880001Query.getInstance().getMenuProcId(bizMsg.bizAppId.getValue(), bizMsg.menuProcGrpCd.getValue(), getGlobalCompanyCode());
        String strMenuProcId = NYXC880001Query.getInstance().getMenuProcId(bizMsg.wfBizScrId.getValue(), bizMsg.menuProcGrpCd.getValue(), getGlobalCompanyCode());
// 2018/05/16 MOD END
        ZYPEZDItemValueSetter.setValue(bizMsg.menuProcId, strMenuProcId);

        // UP_TAB_NAME
// 2018/05/16 MOD START
//        String upTabName = NYXC880001Query.getInstance().getUpTabName(bizMsg.bizAppId.getValue(), this.getGlobalCompanyCode());
        String upTabName = NYXC880001Query.getInstance().getUpTabName(bizMsg.wfBizScrId.getValue(), this.getGlobalCompanyCode());
//2018/05/16 MOD END
        ZYPEZDItemValueSetter.setValue(bizMsg.upTabNm, upTabName);

    }

    /**
     * Next Data
     * @param bizMsg
     * @param glblMsg
     */
    private void doProcess_NYEL8820Scrn00_NYEL8820Scrn00_Continue(NYEL8820CMsg bizMsg, NYEL8820SMsg glblMsg) {
        if (NYEL8820CommonLogic.next(bizMsg)){
            NYEL8820CommonLogic.search(bizMsg, glblMsg, getGlobalCompanyCode(), this.getContextUserInfo().getUserId());
        }
    }

    /**
     * Previous Data
     * @param bizMsg
     * @param glblMsg
     */
    private void doProcess_NYEL8820Scrn00_NYEL8820Scrn00_Back(NYEL8820CMsg bizMsg, NYEL8820SMsg glblMsg) {
        if (NYEL8820CommonLogic.back(bizMsg)){
            NYEL8820CommonLogic.search(bizMsg, glblMsg, getGlobalCompanyCode(), this.getContextUserInfo().getUserId());
        }
    }
}
