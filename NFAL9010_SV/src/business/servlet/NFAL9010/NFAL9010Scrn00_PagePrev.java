/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL9010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL9010.NFAL9010CMsg;
import business.servlet.NFAL9010.constant.NFAL9010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * Class name: Screen Component ID : NFAL9010Scrn00_PagePrev
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL9010Scrn00_PagePrev extends S21CommonHandler implements NFAL9010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFAL9010BMsg scrnMsg = (NFAL9010BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL9010BMsg scrnMsg = (NFAL9010BMsg) bMsg;

        // set values to items of pagenation for prev page
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowFromNum.getValueInt() - scrnMsg.A.length() - 1);
        scrnMsg.xxPageShowToNum.clear();

        NFAL9010CMsg bizMsg = new NFAL9010CMsg();
        bizMsg.setBusinessID("NFAL9010");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL9010BMsg scrnMsg = (NFAL9010BMsg) bMsg;
        NFAL9010CMsg bizMsg = (NFAL9010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21TableColorController tblColor = new S21TableColorController("NFAL9010Scrn00", scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

}
