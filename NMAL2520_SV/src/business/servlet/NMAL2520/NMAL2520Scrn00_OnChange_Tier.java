/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2520;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL2520.NMAL2520CMsg;
import business.servlet.NMAL2520.common.NMAL2520CommonLogic;
import business.servlet.NMAL2520.constant.NMAL2520Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/10   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/04/  Fujitsu         M.suzuki        Update          S21_NA#4539
 * 2016/03/04   Fujitsu         C.Yokoi         Update          CSA-QC#4654
 * 2016/05/24   SRAA            Y.Chen          Update          QC#7962
 *</pre>
 */
public class NMAL2520Scrn00_OnChange_Tier extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/03/04 S21_NA#4539 Add Start ------------
        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
        NMAL2520CommonLogic.clearMandantoryCheck(scrnMsg);
        NMAL2520CommonLogic.addCheckItems(scrnMsg);
        scrnMsg.putErrorScreen();
        // 2016/03/04 S21_NA#4539 Add End --------------
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        NMAL2520CMsg bizMsg = new NMAL2520CMsg();
        bizMsg.setBusinessID(NMAL2520Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2520Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
        NMAL2520CMsg bizMsg = (NMAL2520CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 2016/03/04 CSA-QC#4654 Add Start
        NMAL2520CommonLogic.controlBusinessAreaFields(scrnMsg);
        // 2016/03/04 CSA-QC#4654 Add End
        
        // QC#7962
        scrnMsg.setFocusItem(scrnMsg.bizAreaOrgCd_P1);
    }
}
