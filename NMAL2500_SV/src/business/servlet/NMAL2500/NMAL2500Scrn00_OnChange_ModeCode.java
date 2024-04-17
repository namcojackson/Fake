/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2500.NMAL2500CMsg;
import business.servlet.NMAL2500.common.NMAL2500CommonLogic;
import business.servlet.NMAL2500.constant.NMAL2500Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2015/03/04   Fujitsu         M.suzuki        Update          S21_NA#4539
 * 2016/06/30   Hitachi         K.Kasai         Update          S21_NA2#19617
 *</pre>
 */
public class NMAL2500Scrn00_OnChange_ModeCode extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/03/04 S21_NA#4539 Add Start ------------
        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;
        NMAL2500CommonLogic.addcheckItemH2(scrnMsg);
        NMAL2500CommonLogic.addcheckItemH3(scrnMsg);
        NMAL2500CommonLogic.addcheckItemH4(scrnMsg);
        scrnMsg.putErrorScreen();
        // 2016/03/04 S21_NA#4539 Add End ------------
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;

        NMAL2500CMsg bizMsg = new NMAL2500CMsg();
        bizMsg.setBusinessID(NMAL2500Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2500Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;
        NMAL2500CMsg bizMsg = (NMAL2500CMsg) cMsg;
        //START 2017/06/30 K.Kasai [QC#19617,ADD]
        NMAL2500CommonLogic.clearTreeView(scrnMsg);
        //END 2017/06/30 K.Kasai [QC#19617,ADD]
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (NMAL2500Constant.SEARCH_MODE_SHOW_HIERARCHY.equals(scrnMsg.xxModeCd_P1.getValue())) {
            scrnMsg.xxDplyTab.setValue(NMAL2500Constant.SHOW_HIERARCHY);
            //START 2017/06/30 K.Kasai [QC#19617,MOD]
            NMAL2500CommonLogic.controlScreenFields(this, scrnMsg);
            //END 2017/06/30 K.Kasai [QC#19617,MOD]

        } else if (NMAL2500Constant.SEARCH_MODE_QUICK_RESOURCE_LOOK_UP.equals(scrnMsg.xxModeCd_P1.getValue())) {
            scrnMsg.xxDplyTab.setValue(NMAL2500Constant.QUICK_RESOURCE_LOOK_UP);
            scrnMsg.xxRadioBtn_H1.setInputProtected(true);
        }
    }
}
