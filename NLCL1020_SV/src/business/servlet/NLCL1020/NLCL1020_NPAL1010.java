/**
 * <Pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</Pre>
*/
package business.servlet.NLCL1020;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL1020.constant.NLCL1020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/07/2013   Fujitsu         Y.Taoka         Create          R-WH001
 *</pre>
 */
public class NLCL1020_NPAL1010 extends S21CommonHandler {

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
        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if (NLCL1020Constant.EVENT_OPENWIN_LOCFROM.equals(scrEventNm)) {
            // 10/19/2015 mod start
            // scrnMsg.setFocusItem(scrnMsg.invtyLocCd_FR);
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_FR, scrnMsg.invtyLocNm_FR.getValue());
            scrnMsg.setFocusItem(scrnMsg.fromRtlWhCd);
            // Check item error
            scrnMsg.addCheckItem(scrnMsg.fromRtlWhCd);
            scrnMsg.addCheckItem(scrnMsg.fromRtlSwhCd);
            // 10/19/2015 mod end
        } else if (NLCL1020Constant.EVENT_OPENWIN_LOCTO.equals(scrEventNm)) {
            // 10/19/2015 mod start
            // scrnMsg.setFocusItem(scrnMsg.invtyLocCd_TO);
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_TO, scrnMsg.invtyLocNm_TO.getValue());
            scrnMsg.setFocusItem(scrnMsg.toRtlWhCd);
            // Check item error
            scrnMsg.addCheckItem(scrnMsg.toRtlWhCd);
            scrnMsg.addCheckItem(scrnMsg.toRtlSwhCd);
            // 10/19/2015 mod end
        }

        // 10/19/2015 del start
        // scrnMsg.addCheckItem(scrnMsg.invtyLocCd_FR);
        // scrnMsg.addCheckItem(scrnMsg.invtyLocCd_TO);
        // 10/19/2015 del end
        scrnMsg.putErrorScreen();
    }

}
