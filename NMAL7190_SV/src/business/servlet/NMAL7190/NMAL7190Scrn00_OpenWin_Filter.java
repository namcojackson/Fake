package business.servlet.NMAL7190;

import static business.servlet.NMAL7190.constant.NMAL7190Constant.NMAM8697E;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7190.common.NMAL7190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * NMAL7190Scrn00_OpenWin_Filter
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/12/05   Fujitsu         T.Noguchi       Create          QC#29324
 *</pre>
 */
public class NMAL7190Scrn00_OpenWin_Filter extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcGrpPk_BK) || !scrnMsg.prcGrpPk_BK.getValue().equals(scrnMsg.prcGrpPk.getValue())) {
            scrnMsg.setMessageInfo(NMAM8697E);
        }

        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith("E")) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;
        Object[] params = NMAL7190CommonLogic.setParamFilterPopupNMAL7440(scrnMsg);
        setArgForSubScreen(params);
    }
}
