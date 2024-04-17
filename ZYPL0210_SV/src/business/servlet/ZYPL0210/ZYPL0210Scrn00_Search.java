package business.servlet.ZYPL0210;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZYPL0210.ZYPL0210CMsg;
import business.servlet.ZYPL0210.constant.ZYPL0210Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * ZYPL0210Scrn00_Search
 * @author A.Hosono(CommonTeam)
 */
public class ZYPL0210Scrn00_Search extends S21CommonHandler implements ZYPL0210Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // none
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZYPL0210BMsg scrnMsg = (ZYPL0210BMsg) bMsg;

        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.setValue(1);
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
        scrnMsg.upldCsvFileDescTxt_0H.clear();

        ZYPL0210CMsg bizMsg = new ZYPL0210CMsg();
        bizMsg.setBusinessID(UPLD_CSV_BIZ_APP);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // 1.set data on screen.
        ZYPL0210BMsg scrnMsg = (ZYPL0210BMsg) bMsg;
        ZYPL0210CMsg bizMsg = (ZYPL0210CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 2.set input protected to download anchor.
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.A.no(i).xxUpldCsvDnldFlg.isClear()) {
                scrnMsg.A.no(i).xxUpldCsvDnldFlg.setInputProtected(true);
            }
        }

        // 3.set alternate rows back-ground color
        S21TableColorController tblColor = new S21TableColorController(UPLD_CSV_BIZ_APP + "Scrn00", scrnMsg);
        tblColor.setAlternateRowsBG("L", scrnMsg.A);
        tblColor.setAlternateRowsBG("R", scrnMsg.A);

    }

}
