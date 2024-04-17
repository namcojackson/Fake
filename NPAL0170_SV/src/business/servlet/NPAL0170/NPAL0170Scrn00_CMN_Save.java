/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL0170;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL0170.constant.NPAL0170Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * PO Text Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/24/2012   SRA             N.Otsuji        Create          N/A
 * 02/05/2013   CSAI            K.Lee           Update          WDS#69
 * 01/22/2016   CITS            K.Ogino         Update          CSA
 * </pre>
 */
public class NPAL0170Scrn00_CMN_Save extends S21CommonHandler implements NPAL0170Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // no process.
    }

}