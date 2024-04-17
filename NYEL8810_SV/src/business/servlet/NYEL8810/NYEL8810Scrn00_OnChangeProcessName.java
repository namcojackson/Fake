/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8810;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;

import parts.servletcommon.EZDApplicationContext;

import business.blap.NYEL8810.NYEL8810CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2018/12/18   Fujitsu         Q10627          Update          QC#29682
 * ----------------------------------------------------------------------
 *                  !!!! This class is not used now !!!!
 * ----------------------------------------------------------------------
 *</pre>
 */
public class NYEL8810Scrn00_OnChangeProcessName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //Nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;

        NYEL8810CMsg bizMsg = new NYEL8810CMsg();
        bizMsg.setBusinessID("NYEL8810");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        NYEL8810CMsg bizMsg  = (NYEL8810CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
// QC#29682 DEL START 2018/12/18
//        if (PROC_ID_ALL.equals(scrnMsg.wfProcNm.getValue())){
//            scrnMsg.wfWrkItemNm.setInputProtected(true);
//        } else {
//            scrnMsg.wfWrkItemNm.setInputProtected(false);
//        }
// QC#29682 DEL END   2018/12/18
        }
}
