/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2610.NMAL2610CMsg;
import business.servlet.NMAL2610.common.NMAL2610CommonLogic;
import business.servlet.NMAL2610.constant.NMAL2610Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/29   SRAA            Y.Chen          Create          QC#7966
 *</pre>
 */
public class NMAL2610Scrn00_OnChange_TrtyActvFlg extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        NMAL2610CMsg bizMsg = new NMAL2610CMsg();
        bizMsg.setBusinessID(NMAL2610Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2610Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        NMAL2610CMsg bizMsg  = (NMAL2610CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        if (NMAL2610Constant.TAB_TERRITORY.equals(scrnMsg.xxDplyTab.getValue())) {
            NMAL2610CommonLogic.controlOrgLink(scrnMsg);
        }
    }
}