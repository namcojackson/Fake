/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1350;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPAL1350.NPAL1350CMsg;
//import business.servlet.NPAL1350.constant.NPAL1350Constant;

import business.blap.NPAL1350.NPAL1350CMsg;
import business.servlet.NPAL1350.common.NPAL1350CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/03/14   CITS            K.Fukumura      Create          N/A
 *</pre>
 */
public class NPAL1350Scrn00_UncheckAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1350BMsg scrnMsg = (NPAL1350BMsg) bMsg;
        NPAL1350CMsg bizMsg  = (NPAL1350CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPAL1350CommonLogic.setTableScreen(scrnMsg);
        
        if (scrnMsg.A.getValidCount() != 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.clear();
            }
        }
//        if (bizMsg.A.getValidCount() != 0) {
//            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//                bizMsg.A.no(i).xxChkBox_A1.clear();
//            }
//        }
    }
}
