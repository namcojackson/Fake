/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Select All
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/19/2016   CITS            Makoto Okigami  Create          N/A
 * 04/06/2016   CITS            K.Ogino         Update          N/A
 *</pre>
 */
public class NPBL0020Scrn00_SelectAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).configTpCd_A1)) {
                if (!scrnMsg.A.no(i).xxChkBox_A1.isInputProtected()) {
                    scrnMsg.A.no(i).xxChkBox_A1.setValue(ZYPConstant.CHKBOX_ON_Y);
                }
            } else if (scrnMsg.A.no(i).prchReqLineSubNum_A1.getValueInt() == 0) {
                if (!scrnMsg.A.no(i).xxChkBox_A1.isInputProtected()) {
                    scrnMsg.A.no(i).xxChkBox_A1.setValue(ZYPConstant.CHKBOX_ON_Y);
                }
            }

        }

    }
}
