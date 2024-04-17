/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0110;

import static business.servlet.NSAL0110.constant.NSAL0110Constant.ARGS_CONTRACT_NUM;
//import static business.servlet.NSAL0110.constant.NSAL0110Constant.ARGS_CONTRACT_SEQ;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.ARGS_DS_CONTRACT_DETAIL_PK;
import static business.servlet.NSAL0110.constant.NSAL0110Constant.ARGS_DS_CONTRACT_PK;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0110.common.NSAL0110CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 *
 * Contract Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/12   Hitachi         Y.Igarashi         Create          N/A
 *</pre>
 */
public class NSAL0110Scrn00_SelectContract extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0110BMsg scrnMsg = (NSAL0110BMsg) bMsg;

        Object[] arg = (Object[]) getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            setValue((EZDBStringItem) params[ARGS_CONTRACT_NUM], scrnMsg.A.no(getButtonSelectNumber()).dsContrNum_RS);
            setValue((EZDBBigDecimalItem) params[ARGS_DS_CONTRACT_PK], scrnMsg.A.no(getButtonSelectNumber()).dsContrPk_RS);
            if (!NSAL0110CommonLogic.isSummary(scrnMsg.xxScrItem10Txt_SX.getValue())) {
                setValue((EZDBBigDecimalItem) params[ARGS_DS_CONTRACT_DETAIL_PK], scrnMsg.A.no(getButtonSelectNumber()).dsContrDtlPk_RS);
            }
        }
    }
}

