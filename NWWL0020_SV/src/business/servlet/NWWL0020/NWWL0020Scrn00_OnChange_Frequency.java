/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWWL0020.common.NWWL0020CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0020Scrn00_OnChange_Frequency
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/28   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0020Scrn00_OnChange_Frequency extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;
        NWWL0020CommonLogic.setFocusItemFrequency(scrnMsg);
        NWWL0020CommonLogic.setBackupInfoRepeatItem(scrnMsg);
        NWWL0020CommonLogic.setControlFieldsRepeatChkBox(scrnMsg);
        NWWL0020CommonLogic.setControlFieldsPrdcDtl(scrnMsg);
    }
}
