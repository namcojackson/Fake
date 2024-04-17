/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0160;

import static business.servlet.NFDL0160.constant.NFDL0160Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFDL0160.common.NFDL0160CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collector Portfolio Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         C.Naito         Create          N/A
 * 2016/08/10   Hitachi         K.Kojima        Update          QC#13129
 * 2018/08/03   Fujitsu         T.Ogura         Update          QC#25899
 *</pre>
 */
public class NFDL0160Scrn00_CMN_Clear extends S21CommonHandler {

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

        NFDL0160BMsg scrnMsg = (NFDL0160BMsg) bMsg;

        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);

        scrnMsg.clear();
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);

        // START 2016/08/10 K.Kojima [QC#13129,ADD]
        scrnMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        // END 2016/08/10 K.Kojima [QC#13129,ADD]

        NFDL0160CommonLogic.initialControlScreenFields(this, scrnMsg);
        // START 2018/08/03 T.Ogura [QC#25899,MOD]
//        scrnMsg.setFocusItem(scrnMsg.cltPtfoNm);
        scrnMsg.setFocusItem(scrnMsg.cltPtfoCd);
        // END   2018/08/03 T.Ogura [QC#25899,MOD]
    }
}
