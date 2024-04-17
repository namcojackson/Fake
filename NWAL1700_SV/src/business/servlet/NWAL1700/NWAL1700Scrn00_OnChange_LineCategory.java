/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1700;

import static business.servlet.NWAL1700.constant.NWAL1700Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1700.NWAL1700CMsg;
import business.servlet.NWAL1700.common.NWAL1700CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1700Scrn00_OnChange_LineCategory
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/21   Fujitsu         M.Suzuki        Create          N/A
 * 2017/09/11   Fujitsu         T.Murai         Update          S21_NA#16346(Sol#373)
 *</pre>
 */
public class NWAL1700Scrn00_OnChange_LineCategory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;

        int index = getButtonSelectNumber();
        NWAL1700CMsg bizMsg = new NWAL1700CMsg();
        scrnMsg.xxRowNum.setValue(index);
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;


    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;
        NWAL1700CMsg bizMsg  = (NWAL1700CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int index = getButtonSelectNumber();
        NWAL1700CommonLogic.changeRmaPrimProtect(scrnMsg.A.no(index), true); // Add 2017/09/11 S21_NA#16346
        scrnMsg.setFocusItem(scrnMsg.A.no(index).dsOrdLineCatgCd_A);
    }
}
