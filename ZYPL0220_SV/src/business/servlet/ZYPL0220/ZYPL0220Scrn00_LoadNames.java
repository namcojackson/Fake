package business.servlet.ZYPL0220;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZYPL0220.ZYPL0220CMsg;
import business.servlet.ZYPL0220.common.ZYPL0220CommonLogic;
import business.servlet.ZYPL0220.constant.ZYPL0220Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZYPL0220Scrn00_LoadNames extends S21CommonHandler implements ZYPL0220Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // ------------------------------------------
        // (1)mandatory check for Upload Csv ID
        // ------------------------------------------
        ZYPL0220BMsg scrnMsg = (ZYPL0220BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.upldCsvId);
        scrnMsg.addCheckItem(scrnMsg.upldCsvFileId);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZYPL0220BMsg scrnMsg = (ZYPL0220BMsg) bMsg;

        ZYPL0220CMsg bizMsg = new ZYPL0220CMsg();
        bizMsg.setBusinessID("ZYPL0220");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZYPL0220BMsg scrnMsg = (ZYPL0220BMsg) bMsg;
        ZYPL0220CMsg bizMsg = (ZYPL0220CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // ------------------------------------------
        // (4) put Error Screen
        // ------------------------------------------
        scrnMsg.addCheckItem(scrnMsg.upldCsvFileId);
        scrnMsg.putErrorScreen();

        // ------------------------------------------
        // (5)change screen mode
        // ------------------------------------------
        ZYPL0220CommonLogic.setLoadMode(this, scrnMsg);

        // ------------------------------------------
        // (6)save load File ID
        // ------------------------------------------
        scrnMsg.upldCsvFileId_LD.setValue(scrnMsg.upldCsvFileId.getValue());
        
        // ------------------------------------------
        // (7)set success message
        // ------------------------------------------
        scrnMsg.setMessageInfo(MSG_CD_SUCCESS_LOAD, null);
    }

}
