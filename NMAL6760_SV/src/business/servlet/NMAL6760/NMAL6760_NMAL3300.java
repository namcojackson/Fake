/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6760;

import static business.servlet.NMAL6760.constant.NMAL6760Constant.BIZ_ID;
import static business.servlet.NMAL6760.constant.NMAL6760Constant.FUNC_CD_20;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL6760.NMAL6760CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/06/18   Fujitsu         T.Noguchi       Create          QC#14307
 *</pre>
 */
public class NMAL6760_NMAL3300 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6760BMsg scrnMsg = (NMAL6760BMsg) bMsg;

        NMAL6760CMsg bizMsg = new NMAL6760CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6760BMsg scrnMsg = (NMAL6760BMsg) bMsg;
        NMAL6760CMsg bizMsg = (NMAL6760CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
