/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg; // import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import
                                                  // business.blap.NMAL2510.NMAL2510CMsg;
// import business.servlet.NMAL2510.constant.NMAL2510Constant;

import business.blap.NMAL2510.NMAL2510CMsg;
import business.servlet.NMAL2510.common.NMAL2510CommonLogic;
import business.servlet.NMAL2510.constant.NMAL2510Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/24   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/04/  Fujitsu         M.suzuki        Update          S21_NA#4539 
 * 2016/05/24   SRAA            Y.Chen          Update          QC#7962
 *</pre>
 */
public class NMAL2510Scrn00_OnChange_BusinessAreaHierarchy extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/03/04 S21_NA#4539 Add Start --------------
        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
        NMAL2510CommonLogic.clearMandantoryCheckForHierarchy(scrnMsg);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).orgNm_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A2);
        }
        scrnMsg.putErrorScreen();
        // 2016/03/04 S21_NA#4539 Add Start --------------
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        NMAL2510CMsg bizMsg = new NMAL2510CMsg();
        bizMsg.setBusinessID(NMAL2510Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2510Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
        NMAL2510CMsg bizMsg = (NMAL2510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        // QC#7962
        int index = this.getButtonSelectNumber();
        scrnMsg.setFocusItem(scrnMsg.A.no(index).bizAreaOrgCd_P2);
    }
}
