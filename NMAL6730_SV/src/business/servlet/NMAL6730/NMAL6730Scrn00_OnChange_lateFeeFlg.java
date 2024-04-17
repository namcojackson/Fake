/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6730.NMAL6730CMsg;
//import business.servlet.NMAL6730.constant.NMAL6730Constant;

//import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Fujitsu         N.Sugiura       Create          N/A
 * 2019/05/13   Fujitsu         C.Hara          Update          QC#50113
 *</pre>
 */
public class NMAL6730Scrn00_OnChange_lateFeeFlg extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

        //NMAL6730CMsg bizMsg = new NMAL6730CMsg();
        //bizMsg.setBusinessID("NMAL6730");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // 2019/05/13 QC#50113 Del Start
        // NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

        // if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.lateFeeFlg_F1.getValue())) {
        //     scrnMsg.lateFeeAmt_F1.setInputProtected(false);
        //     scrnMsg.setFocusItem(scrnMsg.lateFeeAmt_F1);
        // } else {
        //       scrnMsg.lateFeeAmt_F1.setInputProtected(true);
        //      scrnMsg.lateFeeAmt_F1.clear();
        //     scrnMsg.setFocusItem(scrnMsg.lateFeeFlg_F1);
        // }
        // 2019/05/13 QC#50113 Del End

    }
}
