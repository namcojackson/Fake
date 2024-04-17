/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0480;

import static business.servlet.NSBL0480.constant.NSBL0480Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0480.NSBL0480CMsg;
import business.servlet.NSBL0480.common.NSBL0480CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/30   Hitachi         J.Kim           Create          N/A
 * 2017/02/01   Hitachi         K.Kitachi       Update          QC#16629
 *</pre>
 */
public class NSBL0480Scrn00_Search_Access extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0480BMsg scrnMsg = (NSBL0480BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.svcAccsPmitNum);
        // START 2017/02/01 K.Kitachi [QC#16629, ADD]
        scrnMsg.addCheckItem(scrnMsg.svcPmitLvlTpCd);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem30Txt);
        // END 2017/02/01 K.Kitachi [QC#16629, ADD]
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0480BMsg scrnMsg = (NSBL0480BMsg) bMsg;

        NSBL0480CMsg bizMsg = NSBL0480CommonLogic.setRequestData_SearchCommon();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0480BMsg scrnMsg = (NSBL0480BMsg) bMsg;
        NSBL0480CMsg bizMsg  = (NSBL0480CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0480CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        if (MESSAGE_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NSBL0480CommonLogic.searchAccessScreenControl(this, scrnMsg);
    }
}
