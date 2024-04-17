/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0620;

import static business.servlet.NSAL0620.constant.NSAL0620Constant.BIZ_ID;
import static business.servlet.NSAL0620.constant.NSAL0620Constant.CHK_BOX.XX_CHK_BOX_A;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0620.NSAL0620CMsg;
import business.servlet.NSAL0620.constant.NSAL0620Constant.FUNC;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL0620Scrn00_UnSelectAll_Contr extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;

        NSAL0620CMsg bizMsg = new NSAL0620CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;
        NSAL0620CMsg bizMsg  = (NSAL0620CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        ZYPTableUtil.unSelectAll(scrnMsg.A, XX_CHK_BOX_A.getChkBox());
    }
}
