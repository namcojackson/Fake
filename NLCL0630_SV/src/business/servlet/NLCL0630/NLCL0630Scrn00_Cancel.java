/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0630;

import static business.servlet.NLCL0630.constant.NLCL0630Constant.BIZ_APP_ID;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.NLCM0142E;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.ZZM8100I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0630.NLCL0630CMsg;
import business.servlet.NLCL0630.common.NLCL0630CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0630 Tech PI Inquiry
 * Function Name : Cancel
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/29/2016   CITS            Makoto Okigami  Create          N/A
 * 08/07/2018   CITS            Y.Iwasaki       Update          QC#27010
 *</pre>
 */
public class NLCL0630Scrn00_Cancel extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0630BMsg scrnMsg = (NLCL0630BMsg) bMsg;

        if (scrnMsg.A.getValidCount() == 0 || !ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn)) {
            scrnMsg.xxRadioBtn.setErrorInfo(1, NLCM0142E);
            scrnMsg.addCheckItem(scrnMsg.xxRadioBtn);
        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0630BMsg scrnMsg = (NLCL0630BMsg) bMsg;

        NLCL0630CMsg bizMsg = new NLCL0630CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0630BMsg scrnMsg = (NLCL0630BMsg) bMsg;
        NLCL0630CMsg bizMsg  = (NLCL0630CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        NLCL0630CommonLogic.setTableScreen(scrnMsg);
        scrnMsg.setMessageInfo(ZZM8100I);

        // set focus
        scrnMsg.setFocusItem(scrnMsg.techTocCd);

    }
}