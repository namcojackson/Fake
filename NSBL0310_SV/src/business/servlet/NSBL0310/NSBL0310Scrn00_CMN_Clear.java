/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0310;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Service Request By Manager
 * * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18     Hitachi         T.Harada        Create          N/A
 *</pre>
 */
public class NSBL0310Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //do nothing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //do nothing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0310BMsg scrnMsg = (NSBL0310BMsg) bMsg;
        NSBL0310BMsg scrnNewMsg = new NSBL0310BMsg();

        EZDMsg.copy(scrnMsg, null, scrnNewMsg, null);

        scrnMsg.clear();
        scrnMsg.A.setValidCount(0);

        ZYPEZDItemValueSetter.setValue(scrnMsg.svcMgrTpCd_H, scrnNewMsg.svcMgrTpCd_HD.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcMgrTpCd_HD, scrnNewMsg.svcMgrTpCd_HD.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, scrnNewMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.slsDt, scrnNewMsg.slsDt.getValue());

        scrnMsg.setFocusItem(scrnMsg.orgCd_HT);

    }
}
