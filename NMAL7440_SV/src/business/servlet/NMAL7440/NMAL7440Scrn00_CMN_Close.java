/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7440;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7440.common.NMAL7440CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * NMAL7440Scrn00_CMN_Close
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/12/05   Fujitsu         T.Noguchi       Create          QC#29324
 * 2023/04/20   Hitachi         H.Watanabe      Update          QC#61200
 *</pre>
 */
public class NMAL7440Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7440BMsg scrnMsg = (NMAL7440BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.prcGrpTrgtTpCd);
        scrnMsg.addCheckItem(scrnMsg.prcGrpTrgtAttrbCd);
        scrnMsg.addCheckItem(scrnMsg.xxPrcQlfyValSrchTxt_TF);
        // 2023/04/20 QC#61200 Add Start
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm);
        // 2023/04/20 QC#61200 Add End
        scrnMsg.addCheckItem(scrnMsg.xxPrcQlfyValSrchTxt_TT);
        scrnMsg.addCheckItem(scrnMsg.prcGrpPrcdNum);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H2);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H2);

        NMAL7440CommonLogic.checkItem(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7440BMsg scrnMsg = (NMAL7440BMsg) bMsg;

        Object[] arg = (Object[]) getArgForSubScreen();

        NMAL7440CommonLogic.setOutputParam(scrnMsg, arg);
    }

}
