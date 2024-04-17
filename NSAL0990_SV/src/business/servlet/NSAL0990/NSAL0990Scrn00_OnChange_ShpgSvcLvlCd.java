/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import static business.servlet.NSAL0990.constant.NSAL0990Constant.BIZ_ID;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0990.NSAL0990CMsg;
//import business.servlet.NSAL0990.constant.NSAL0990Constant;

import business.blap.NSAL0990.NSAL0990CMsg;
import business.servlet.NSAL0990.common.NSAL0990CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2018/12/21   Fujitsu         Hd.Sugawara     Update          QC#29315
 *</pre>
 */
public class NSAL0990Scrn00_OnChange_ShpgSvcLvlCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {


        // Mod Start 2018/12/21 QC#29315
        //NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;

        //NSAL0990CMsg bizMsg = new NSAL0990CMsg();
        //bizMsg.setBusinessID("NSAL0990");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;
        return null;
        // Mod End 2018/12/21 QC#29315
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // Del Start 2018/12/21 QC#29315
        //NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        //NSAL0990CMsg bizMsg  = (NSAL0990CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // Del End 2018/12/21 QC#29315

    }
}
