/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0090;

import static business.servlet.NLBL0090.constant.NLBL0090Constant.BIZ_APP_ID;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.SCRN_ID;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.SRCH_FUNCTION_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL0090.NLBL0090CMsg;
import business.servlet.NLBL0090.common.NLBL0090CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/22   Fujitsu         Mori            Create          N/A
 *</pre>
 */
public class NLBL0090Scrn00_TAB_BOLDetail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;
        // InputCheck
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_A1)) {
            scrnMsg.xxRadioBtn_A1.setErrorInfo(1, "NLBM0001E");
            scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_A1);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;
        NLBL0090CMsg bizMsg = new NLBL0090CMsg();

        NLBL0090CommonLogic.clearDetailTableInfo(scrnMsg);

        // clear image file of sort columns (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.B.no(0).getBaseContents());

        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(SRCH_FUNCTION_ID);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;
        NLBL0090CMsg bizMsg = (NLBL0090CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL0090CommonLogic.initGuiAttrForDetailTab(scrnMsg, this);

        NLBL0090CommonLogic.showDetailTab(scrnMsg);

        NLBL0090CommonLogic.setTblBackColorB(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.bolNum_H1);

        // set success message
        scrnMsg.setMessageInfo("NZZM0002I");
    }

}
