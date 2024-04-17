/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6840;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6840.NMAL6840CMsg;
import business.servlet.NMAL6840.common.NMAL6840CommonLogic;
import business.servlet.NMAL6840.constant.NMAL6840Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <p>
 * Business ID : NMAL6840 Sub WH Setup<br>
 * Function Name : The business process for Initialization<br>
 * </p>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/27/2015   CITS            M.Ito           Create          N/A
 * </pre>
 */
public class NMAL6840_INIT extends S21INITCommonHandler {

    /*
     * (non-Javadoc)
     * @see
     * com.canon.cusa.s21.framework.online.servlet.S21CommonHandler
     * #checkInput(parts.servletcommon.EZDApplicationContext,
     * parts.common.EZDBMsg)
     */
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NMAL6840Constant.BIZ_ID);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.canon.cusa.s21.framework.online.servlet.S21CommonHandler
     * #setRequestData(parts.servletcommon.EZDApplicationContext,
     * parts.common.EZDBMsg)
     */
    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Sets the WH Category from previous screen.
        NMAL6840BMsg scrnMsg = (NMAL6840BMsg) bMsg;
        setWarehouseCategoryFromPreviousScreen(scrnMsg);

        return NMAL6840CommonLogic.copyScrnMsgToBizMsgForSearch(scrnMsg);
    }

    /**
     * <p>
     * Sets the parameters from previous screen.
     * </p>
     * @param scrnMsg scrnMsg
     */
    private void setWarehouseCategoryFromPreviousScreen(NMAL6840BMsg scrnMsg) {

        Serializable args = getArgForSubScreen();
        if (args == null || !(args instanceof Object[])) {
            return;
        }
        Object[] params = (Object[]) args;
        if (params.length == 0) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCatgCd_H1, (EZDBStringItem) params[0]);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.canon.cusa.s21.framework.online.servlet.S21CommonHandler
     * #doProcess(parts.servletcommon.EZDApplicationContext,
     * parts.common.EZDBMsg, parts.common.EZDCMsg)
     */
    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6840BMsg scrnMsg = (NMAL6840BMsg) bMsg;
        EZDMsg.copy((NMAL6840CMsg) cMsg, null, scrnMsg, null);

        NMAL6840CommonLogic.doProcessOfInit(scrnMsg, this);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler
     * #setNameForMessage(parts.common.EZDBMsg)
     */
    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL6840BMsg scrnMsg = (NMAL6840BMsg) bMsg;

        for (int rowIndex = 0; rowIndex < scrnMsg.A.getValidCount(); rowIndex++) {
            NMAL6840CommonLogic.setDisplayName(scrnMsg.A.no(rowIndex));
        }
    }
}
