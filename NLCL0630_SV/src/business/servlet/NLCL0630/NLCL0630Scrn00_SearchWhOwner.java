/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0630;

import static business.servlet.NLCL0630.constant.NLCL0630Constant.BIZ_APP_ID;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0630.NLCL0630CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/07/2018   CITS            Y.Iwasaki       Create          QC#27010
 * 09/12/2018   CITS            M.Naito         Update          QC#28190
 *</pre>
 */
public class NLCL0630Scrn00_SearchWhOwner extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        NLCL0630BMsg scrnMsg = (NLCL0630BMsg) bMsg;
//
//        if (!ZYPCommonFunc.hasValue(scrnMsg.whOwnrCd)) {
//            scrnMsg.whOwnrCd.setErrorInfo(1, ZZM9000E, new String[] {DISPLAY_NM_WH_OWNR_CD});
//            scrnMsg.addCheckItem(scrnMsg.whOwnrCd);
//        }
//
//        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0630BMsg scrnMsg = (NLCL0630BMsg) bMsg;

        NLCL0630CMsg bizMsg = new NLCL0630CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

        //return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        NLCL0630BMsg scrnMsg = (NLCL0630BMsg) bMsg;
//        NLCL0630CMsg bizMsg  = (NLCL0630CMsg) cMsg;
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        scrnMsg.addCheckItem(scrnMsg.whOwnrCd);
//        scrnMsg.putErrorScreen();
    }
}
