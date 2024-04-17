/**
 * <Pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</Pre>
*/
package business.servlet.NLCL1020;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1020.NLCL1020CMsg;
import business.servlet.NLCL1020.common.NLCL1020CommonLogic;
import business.servlet.NLCL1020.constant.NLCL1020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/07/2013   Fujitsu         Y.Taoka         Create          R-WH001
 *</pre>
 */
public class NLCL1020Scrn00_Add_Dtaill_Line extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mdseDescShortTxt_A1.clearErrorInfo();
        }

        NLCL1020CommonLogic.checkInput_NLCL1020Scrn00_Add_Dtaill_Line(scrnMsg);

        if (scrnMsg.mdseCd_HD.isError()) {
            scrnMsg.mdseDescShortTxt_HD.clear();
        }

        scrnMsg.putErrorScreen();

        int max = scrnMsg.A.length();
        if (scrnMsg.A.getValidCount() == max) {
            scrnMsg.mdseCd_HD.setErrorInfo(1, NLCL1020Constant.NLCM0025E, new String[] {Integer.toString(max) });
            scrnMsg.addCheckItem(scrnMsg.mdseCd_HD);
            scrnMsg.putErrorScreen();

        }

        String mdseCdHD = scrnMsg.mdseCd_HD.getValue();
        String stkStsPH = scrnMsg.stkStsCd_PH.getValue();

        String mdseCdA1;
        String stkStsAP;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            mdseCdA1 = scrnMsg.A.no(i).mdseCd_A1.getValue();
            stkStsAP = scrnMsg.A.no(i).stkStsCd_AP.getValue();

            if ((mdseCdHD.equals(mdseCdA1)) && (stkStsPH.equals(stkStsAP))) {

                scrnMsg.mdseCd_HD.setErrorInfo(1, NLCL1020Constant.NLCM0019E);
                scrnMsg.stkStsCd_PH.setErrorInfo(1, NLCL1020Constant.NLCM0019E);
                scrnMsg.addCheckItem(scrnMsg.mdseCd_HD);
                scrnMsg.addCheckItem(scrnMsg.stkStsCd_PH);
                scrnMsg.putErrorScreen();
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1020CMsg bizMsg = null;
        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;

        bizMsg = NLCL1020CommonLogic.setRequestData20(scrnMsg);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1020CMsg bizMsg = (NLCL1020CMsg) cMsg;

        if (bizMsg != null) {
            if (NLCL1020Constant.MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }
        }

        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL1020CommonLogic.transMsgCheck(scrnMsg);
        scrnMsg.putErrorScreen();

        NLCL1020CommonLogic.initialize(this, scrnMsg);

        NLCL1020CommonLogic.setScrnItemValue_NLCL1020Scrn00_Add_Dtaill_Line(scrnMsg);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
        }
    }

}
