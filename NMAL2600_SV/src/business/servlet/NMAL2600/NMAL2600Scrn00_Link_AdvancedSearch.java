/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2600;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2600.common.NMAL2600CommonLogic;
import business.servlet.NMAL2600.constant.NMAL2600Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/03   Fujitsu         C.Yokoi         Update          CSA-QC#4539
 *</pre>
 */
public class NMAL2600Scrn00_Link_AdvancedSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // 2016/03/03 CSA-QC#4539 Add Start
        NMAL2600BMsg scrnMsg = (NMAL2600BMsg) bMsg;
        NMAL2600CommonLogic.addCheckItems(scrnMsg);
        // 2016/03/03 CSA-QC#4539 Add End

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2600BMsg scrnMsg = (NMAL2600BMsg) bMsg;

        if (NMAL2600Constant.TAB_ADVANCED_SEARCH.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.xxDplyTab.setValue("");
        } else {
            scrnMsg.xxDplyTab.setValue(NMAL2600Constant.TAB_ADVANCED_SEARCH);
        }
    }
}
