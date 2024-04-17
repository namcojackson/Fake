/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.NLBL0110;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL0110.constant.NLBL0110Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * When Event[CMN_Return] in BusinessID NLBL0110 is generated, this class processes it. 
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/02/04   Fujitsu         S.Uehara        Create          N/A
 * </pre>
 */
public class NLBL0110Scrn00_CMN_Return extends S21CommonHandler implements NLBL0110Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NLBL0110BMsg scrnMsg = (NLBL0110BMsg) bMsg;


    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NLBL0110BMsg scrnMsg = (NLBL0110BMsg) bMsg;

        //NLBL0110CMsg bizMsg = new NLBL0110CMsg();
        //bizMsg.setBusinessID("NLBL0110");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NLBL0110BMsg scrnMsg = (NLBL0110BMsg) bMsg;
        //NLBL0110CMsg bizMsg  = (NLBL0110CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);


    }

}
