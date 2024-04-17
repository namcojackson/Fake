/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1540;

import static business.servlet.NPAL1540.constant.NPAL1540Constant.BIZ_APP_ID;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.NPAM1566E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1540.NPAL1540CMsg;
import business.servlet.NPAL1540.common.NPAL1540CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NPAL1540 Manual ASN Entry
 * Function Name : Apply PO
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/22   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NPAL1540Scrn00_AddLine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1540BMsg scrnMsg = (NPAL1540BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.dispPoDtlLineNum)) {
            scrnMsg.dispPoDtlLineNum.setErrorInfo(1, NPAM1566E);
        }

        if (!scrnMsg.poOrdNum_HD.getValue().equals(scrnMsg.poOrdNum.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.poOrdNum, scrnMsg.poOrdNum_HD);
        }

        scrnMsg.addCheckItem(scrnMsg.dispPoDtlLineNum);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1540BMsg scrnMsg = (NPAL1540BMsg) bMsg;

        NPAL1540CMsg bizMsg = new NPAL1540CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1540BMsg scrnMsg = (NPAL1540BMsg) bMsg;
        NPAL1540CMsg bizMsg  = (NPAL1540CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.dispPoDtlLineNum);
        scrnMsg.putErrorScreen();

        if (!scrnMsg.dispPoDtlLineNum.isError() && !"E".equals(bizMsg.getMessageKind())) {
            NPAL1540CommonLogic.setScrnItemAttr(this, scrnMsg);
            NPAL1540CommonLogic.setTableScreen(scrnMsg);
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (scrnMsg.A.no(i).dispPoDtlLineNum_A1.getValue().equals(scrnMsg.dispPoDtlLineNum.getValue())) {
                    scrnMsg.setFocusItem(scrnMsg.A.no(i).asnQty_A1);
                    break;
                }
            }
            scrnMsg.dispPoDtlLineNum.clear();
        }
    }
}
