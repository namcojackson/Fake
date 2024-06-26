/**
 * <Pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</Pre>
*/
package business.servlet.NLCL1020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/07/2013   Fujitsu         Y.Taoka         Create          R-WH001
 * 03/02/2016   Hitachi         T.Tomita        Update          QC#3586
 *</pre>
 */
public class NLCL1020_NMAL6030 extends S21CommonHandler {

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
        return;
    }

}
