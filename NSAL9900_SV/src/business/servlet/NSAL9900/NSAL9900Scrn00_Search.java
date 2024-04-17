/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL9900;

import static business.servlet.NSAL9900.constant.NSAL9900Constant.*;

import java.lang.reflect.Field;

import parts.common.EZDBItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL9900.NSAL9900CMsg;
import business.servlet.NSAL9900.common.NSAL9900CommonLogic;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/28   Hitachi         T.Aoyagi        Create          N/A
 *</pre>
 */
public class NSAL9900Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL9900BMsg scrnMsg = (NSAL9900BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL9900_ABMsg aBMsg = scrnMsg.A.no(i);

            String searchItemNm = scrnMsg.C.no(aBMsg.xxRowNum_A.getValueInt()).searchItemNm.getValue();
            Field f = null;
            try {
                f = NSAL9900_ABMsg.class.getField(searchItemNm);
                scrnMsg.addCheckItem((EZDBItem) f.get(aBMsg));
            } catch (Exception ex) {
                throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
            }
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL9900BMsg scrnMsg = (NSAL9900BMsg) bMsg;
        NSAL9900CMsg bizMsg = NSAL9900CommonLogic.createCMsgForSearch();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL9900BMsg scrnMsg = (NSAL9900BMsg) bMsg;
        NSAL9900CMsg bizMsg  = (NSAL9900CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL9900CommonLogic.screenControlProcess(this, scrnMsg);
    }
}
