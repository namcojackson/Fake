/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0060;


import parts.common.*;
import parts.servletcommon.*;

import business.servlet.NLBL0060.constant.NLBL0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * When Event[CMN_Return] in BusinessID NLBL0060 is generated, this class processes it. 
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/09/16   Fujitsu         D.Fukaya        Create          N/A
 * </pre>
 */
public class NLBL0060Scrn00_CMN_Return extends S21CommonHandler implements NLBL0060Constant {

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
