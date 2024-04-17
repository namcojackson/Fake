/*
 * <Pre> Copyright (c) 2013 Canon USA Inc. All rights reserved. </Pre>
 */
package business.servlet.NLBL3030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3030.constant.NLBL3030Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Message Entry PopUo
 * 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/16   Fujitsu         C.Naito         Create          (From NATL6050)
 * </pre>
 */
public class NLBL3030Scrn00_CMN_Close extends S21CommonHandler implements NLBL3030Constant {

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
