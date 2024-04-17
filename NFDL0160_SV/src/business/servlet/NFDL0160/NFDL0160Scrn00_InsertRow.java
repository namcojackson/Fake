/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0160;

import static business.servlet.NFDL0160.constant.NFDL0160Constant.BIZ_ID;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BTN_CMN_SUB;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.NFDM0025E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0160.NFDL0160CMsg;
import business.servlet.NFDL0160.common.NFDL0160CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collector Portfolio Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/24   Fujitsu         C.Naito         Create          N/A
 * 2017/01/05   Fujitsu         T.Murai         Update          QC#16295
 *</pre>
 */
public class NFDL0160Scrn00_InsertRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0160BMsg scrnMsg = (NFDL0160BMsg) bMsg;
        if (scrnMsg.A.getValidCount() >= scrnMsg.A.length()) {
            // for inActiveForm error clear
            NFDL0160CommonLogic.inActiveFormErrorClear(scrnMsg);
            // set ErrorMessage
            scrnMsg.setMessageInfo(NFDM0025E);
            throw new EZDFieldErrorException();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2017/01/05 [QC#16295,MOD]
        // return null;

        NFDL0160BMsg scrnMsg = (NFDL0160BMsg) bMsg;

        NFDL0160CMsg bizMsg = new NFDL0160CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // END   2017/01/05 [QC#16295,MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0160BMsg scrnMsg = (NFDL0160BMsg) bMsg;
        NFDL0160CMsg bizMsg = (NFDL0160CMsg) cMsg; // ADD 2017/01/05 [QC#16295]

        EZDMsg.copy(bizMsg, null, scrnMsg, null); // ADD 2017/01/05 [QC#16295]

        int rowNum = scrnMsg.A.getValidCount();
//        scrnMsg.A.setValidCount(++rowNum); // DEL 2017/01/05 [QC#16295]

        // Set Focus, ActiveFlag
        scrnMsg.setFocusItem(scrnMsg.A.no(rowNum - 1).cltPtfoNm_A);
        scrnMsg.A.no(rowNum - 1).cltPtfoStsFlg_A.setValue(ZYPConstant.FLG_ON_Y);
        NFDL0160CommonLogic.setCmnBtnProp(this, BTN_CMN_SUB, 1);
        NFDL0160CommonLogic.controlScreenFields(this, scrnMsg, ctx.getEventName());
    }
}
