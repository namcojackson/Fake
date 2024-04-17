/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2020;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2020.NLAL2020CMsg;
import business.servlet.NLAL2020.common.NLAL2020CommonLogic;
import business.servlet.NLAL2020.constant.NLAL2020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLAL2020Scrn00_OnChange_ChkBoxRMANum
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         Y.Taoka         Create          N/A
 * 12/14/2018   CITS            T.Tokutomi      Update          QC#29283
 * 02/19/2019   CITS            M.Naito         Update          QC#30354
 *</pre>
 */
public class NLAL2020Scrn00_OnChange_ChkBoxRMANum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;

        int eventLine = getButtonSelectNumber();
        scrnMsg.xxNum.setValue(eventLine);

        NLAL2020CMsg bizMsg = new NLAL2020CMsg();
        bizMsg.setBusinessID(NLAL2020Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;
        NLAL2020CMsg bizMsg = (NLAL2020CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLAL2020CommonLogic.initCmnBtnProp(this);
        NLAL2020CommonLogic.controlScreenFields(this, scrnMsg);
        NLAL2020CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, NLAL2020Bean.A);

        // START 2019/02/19 M.Naito [QC#30354,DEL]
//        //QC#29283 clear protected checkbox
//        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//            if (scrnMsg.A.no(i).xxChkBox_A2.isInputProtected()) {
//                scrnMsg.A.no(i).xxChkBox_A2.setValue(ZYPConstant.FLG_OFF_N);
//            }
//        }
        // END 2019/02/19 M.Naito [QC#30354,DEL]

        // Set Focus
        int eventLine = getButtonSelectNumber();
        scrnMsg.setFocusItem(scrnMsg.A.no(eventLine).xxChkBox_A1);
    }
}
