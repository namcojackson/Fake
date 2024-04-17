/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3020.NFCL3020CMsg;
//import business.servlet.NFCL3020.constant.NFCL3020Constant;

import business.servlet.NFCL3020.common.NFCL3020CommonLogic;
import business.servlet.NFCL3020.constant.NFCL3020Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/16   Fujitsu         T.Tanaka        Create          N/A
 * 2016/09/05   Fujitsu         C.Tanaka        Update          QC#5521
 *</pre>
 */
public class NFCL3020Scrn00_TAB_AddInfo extends S21CommonHandler implements NFCL3020Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;

        //NFCL3020CMsg bizMsg = new NFCL3020CMsg();
        //bizMsg.setBusinessID("NFCL3020");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;
        //NFCL3020CMsg bizMsg  = (NFCL3020CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21TableColorController tblColor = new S21TableColorController("NFCL3020Scrn00", scrnMsg);
        tblColor.clearRowsBG(TABLE_B, scrnMsg.B);

        NFCL3020CommonLogic.clearRecptNum(scrnMsg);
        
        // Display TAB = Add Info
        scrnMsg.xxDplyTab.setValue(TAB_AddInfo);

        // QC#5521 ADD Start
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.B.no(0).getBaseContents());
        // QC#5521 ADD End

    }
}
