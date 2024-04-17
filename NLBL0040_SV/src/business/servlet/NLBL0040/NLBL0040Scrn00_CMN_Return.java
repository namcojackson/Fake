package business.servlet.NLBL0040;

import parts.common.*;
import parts.servletcommon.*;

import business.servlet.NLBL0040.constant.NLBL0040Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * When Event[CMN_Return] in BusinessID NLBL0040 is generated, this class processes it. 
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/08/07   Fujitsu         D.Fukaya        Create          N/A
 * </pre>
 */
public class NLBL0040Scrn00_CMN_Return extends S21CommonHandler implements NLBL0040Constant {

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
    	// There is no processing.
    }
}
