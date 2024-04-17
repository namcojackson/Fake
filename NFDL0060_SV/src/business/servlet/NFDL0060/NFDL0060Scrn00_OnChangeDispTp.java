/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFDL0060.common.NFDL0060CommonLogic;
import business.servlet.NFDL0060.constant.NFDL0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/17   Fujitsu         Kamide          Create          N/A
 * 2016/07/28   Hitachi         K.Kojima        Update          QC#10260
 *</pre>
 */
public class NFDL0060Scrn00_OnChangeDispTp extends S21CommonHandler implements NFDL0060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/07/28 K.Kojima [QC#10260,DEL]
        // NFDL0060BMsg scrnMsg = (NFDL0060BMsg) bMsg;
        //
        // scrnMsg.addCheckItem(scrnMsg.cltDispTpCd_H3);
        // scrnMsg.putErrorScreen();
        // END 2016/07/28 K.Kojima [QC#10260,DEL]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/07/28 K.Kojima [QC#10260,DEL]
        // NFDL0060BMsg scrnMsg = (NFDL0060BMsg) bMsg;
        // NFDL0060CMsg bizMsg = new NFDL0060CMsg();
        //
        // bizMsg.setBusinessID(BUSINESS_ID);
        // bizMsg.setFunctionCode(SEARCH_FUNCTION);
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);
        //
        // return bizMsg;
        // END 2016/07/28 K.Kojima [QC#10260,DEL]
        // START 2016/07/28 K.Kojima [QC#10260,ADD]
        return null;
        // END 2016/07/28 K.Kojima [QC#10260,ADD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0060BMsg scrnMsg = (NFDL0060BMsg) bMsg;
        // START 2016/07/28 K.Kojima [QC#10260,DEL]
        // NFDL0060CMsg bizMsg = (NFDL0060CMsg) cMsg;
        //
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // END 2016/07/28 K.Kojima [QC#10260,DEL]

        NFDL0060CommonLogic.controlScreenFields(scrnMsg);

    }
}
