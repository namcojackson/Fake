/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2010   Fujitsu         I.Kondo         Create          N/A
 * </pre>
 */
package business.servlet.NFCL5140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFCL5140.constant.NFCL5140Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NFCL5140Scrn00_CMN_Close class.
 */
public class NFCL5140Scrn00_CMN_Close extends S21CommonHandler implements NFCL5140Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
    }

}
