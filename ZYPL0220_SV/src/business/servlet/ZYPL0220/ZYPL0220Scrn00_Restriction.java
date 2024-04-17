package business.servlet.ZYPL0220;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZYPL0220.common.ZYPL0220CommonLogic;
import business.servlet.ZYPL0220.constant.ZYPL0220Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZYPL0220Scrn00_Restriction extends S21CommonHandler implements ZYPL0220Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        //------------------------------------------
        //(1)change screen mode
        //------------------------------------------
        ZYPL0220BMsg scrnMsg = (ZYPL0220BMsg) bMsg;
        scrnMsg.upldCsvId_1H.setValue(scrnMsg.upldCsvId.getValue());
        scrnMsg.upldCsvNm_1H.setValue(scrnMsg.upldCsvNm.getValue());
        scrnMsg.upldCsvFileId_1H.setValue(scrnMsg.upldCsvFileId.getValue());
        scrnMsg.upldCsvTempTblId_1H.setValue(scrnMsg.upldCsvTempTblId.getValue());
        scrnMsg.ezReqBusinessID_1H.setValue(scrnMsg.ezReqBusinessID.getValue());
        
        
        //------------------------------------------
        //(2)change screen mode
        //------------------------------------------
        ZYPL0220CommonLogic.setRestrictionMode(this, scrnMsg);

    }

}
