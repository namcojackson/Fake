/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1150;

import static business.servlet.NSAL1150.constant.NSAL1150Constant.*;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1150.NSAL1150CMsg;
import business.servlet.NSAL1150.common.NSAL1150CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Hitachi         T.Kanasaka      Create          N/A
 *</pre>
 */
public class NSAL1150_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1150BMsg scrnMsg = (NSAL1150BMsg) bMsg;

        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBStringItem) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.condSqlTxt_CU, ((EZDBStringItem) prm[0]).getValue());
                }
                if (prm.length > 1 && prm[1] != null && prm[1] instanceof EZDBStringItem) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm, ((EZDBStringItem) prm[1]).getValue());
                }
                if (prm.length > 2 && prm[2] != null && prm[2] instanceof EZDBStringItem) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.condSqlTxt_CO, ((EZDBStringItem) prm[2]).getValue());
                }
            }
        }

        NSAL1150CMsg bizMsg = new NSAL1150CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1150BMsg scrnMsg = (NSAL1150BMsg) bMsg;
        NSAL1150CMsg bizMsg = (NSAL1150CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1150CommonLogic.setupScreenItems(this, scrnMsg);
        NSAL1150CommonLogic.addCheckItem(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.condSqlTxt_CU);

        NSAL1150CommonLogic.setupListTable(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL1150BMsg scrnMsg = (NSAL1150BMsg) bMsg;

        scrnMsg.condSqlTxt_CU.setNameForMessage("Customer#");
        scrnMsg.dsAcctNm.setNameForMessage("Customer Name");
        scrnMsg.condSqlTxt_CO.setNameForMessage("Contract#");

    }
}
