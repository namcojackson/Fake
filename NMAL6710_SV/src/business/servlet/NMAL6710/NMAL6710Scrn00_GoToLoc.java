/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6710;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6710.common.NMAL6710CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/06/30   SRAA            Y.Chen          Update          QC#11265
 *</pre>
 */
public class NMAL6710Scrn00_GoToLoc extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();
        NMAL6710_CBMsg aBMsg = scrnMsg.C.no(selectIdx);

        scrnMsg.P.clear();
        scrnMsg.P.no(0).xxPopPrm.setValue("01");
        String dsAcctNum = "";
        // QC#QC#11265
//        if (ZYPCommonFunc.hasValue(aBMsg.dsAcctRelnTpNm_C1)) {
//            dsAcctNum = aBMsg.relnDsAcctNum_C1.getValue();
//        } else {
//            dsAcctNum = aBMsg.dsAcctNum_C1.getValue();
//        }
        dsAcctNum = aBMsg.dsAcctNum_C1.getValue();
        scrnMsg.P.no(1).xxPopPrm.setValue(dsAcctNum);
        scrnMsg.P.no(2).xxPopPrm.setValue(aBMsg.locNum_C1.getValue());

        Object[] params = NMAL6710CommonLogic.toArrayPopup(scrnMsg.P, 3);

        setArgForSubScreen(params);

    }
}
