/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0020.NSAL0020CMsg;

import business.servlet.NSAL0020.common.NSAL0020CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import static business.servlet.NSAL0020.constant.NSAL0020Constant.*;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/04   Hitachi        T.Yonekura         Create          N/A
 * 2018/06/01   Fujitsu        T.Murai            Update          QC#25167
 * 2019/01/16   Hitachi        E.Kameishi         Update          QC#29891
 *</pre>
 */
public class NSAL0020Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;
        // ADD START 2018/06/01 T.Murai [QC#25167]
        if (NSAL0020CommonLogic.isErrorSearchCondition(scrnMsg)) {
            throw new EZDFieldErrorException();
        }
        // ADD END 2018/06/01 T.Murai [QC#25167]
        // START 2019/01/16 E.Kameishi [QC#29891,ADD]
        if (!NSAL0020CommonLogic.configNumFormatCheck(scrnMsg)) {
            scrnMsg.xxScrItem30Txt_02.setErrorInfo(1,NSAM0206E, new String[] {"Configuration#"});
        }
        // END 2019/01/16 E.Kameishi [QC#29891,ADD]
        NSAL0020CommonLogic.addCheckItemForAllHeader(scrnMsg);
        NSAL0020CommonLogic.isEnteredCheckBox(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;
        NSAL0020CMsg bizMsg = NSAL0020CommonLogic.setRequestData_SearchCommon();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;
        NSAL0020CMsg bizMsg = (NSAL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0020CommonLogic.screenControlProcess(this, scrnMsg);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

    }

}
