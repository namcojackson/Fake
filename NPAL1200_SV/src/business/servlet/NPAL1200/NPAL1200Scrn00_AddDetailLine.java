/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1200;

import static business.servlet.NPAL1200.constant.NPAL1200Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.NPAM1348E;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.SCREEN_ID;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1200.NPAL1200CMsg;
import business.servlet.NPAL1200.common.NPAL1200CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1200 Insourcing Planning Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   CITS       Yasushi Nomura        Create          N/A
 *</pre>
 */
public class NPAL1200Scrn00_AddDetailLine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1200BMsg scrnMsg = (NPAL1200BMsg) bMsg;

        if ((!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_DF)) || (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_DT))) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_DF)) {
                scrnMsg.rtlWhCd_DF.setErrorInfo(1, ZZM9000E, new String[] {"From Warehouse" });
                scrnMsg.setFocusItem(scrnMsg.rtlWhCd_DF);
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_DT)) {
                scrnMsg.rtlWhCd_DT.setErrorInfo(1, ZZM9000E, new String[] {"To Warehouse" });
                scrnMsg.setFocusItem(scrnMsg.rtlWhCd_DT);
            }
        } else {
            if (scrnMsg.rtlWhCd_DF.getValue().equals(scrnMsg.rtlWhCd_DT.getValue())) {
                scrnMsg.rtlWhCd_DF.setErrorInfo(1, NPAM1348E, new String[] {"From Warehouse", "To Warehouse" });
                scrnMsg.rtlWhCd_DT.setErrorInfo(1, NPAM1348E, new String[] {"From Warehouse", "To Warehouse" });
                scrnMsg.setFocusItem(scrnMsg.rtlWhCd_DF);
            }
        }
        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_DF);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_DT);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1200BMsg scrnMsg = (NPAL1200BMsg) bMsg;

        NPAL1200CMsg bizMsg = new NPAL1200CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1200BMsg scrnMsg = (NPAL1200BMsg) bMsg;
        NPAL1200CMsg bizMsg = (NPAL1200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if ("E".equals(bizMsg.getMessageKind())) {
            scrnMsg.putErrorScreen();
            return;
        }
        if (scrnMsg.rtlWhCd_DF.getErrorCode() != 0) {
            scrnMsg.addCheckItem(scrnMsg.rtlWhCd_DF);
            scrnMsg.putErrorScreen();
            return;
        }
        if (scrnMsg.rtlWhCd_DT.getErrorCode() != 0) {
            scrnMsg.addCheckItem(scrnMsg.rtlWhCd_DT);
            scrnMsg.putErrorScreen();
            return;
        }
        NPAL1200CommonLogic.control(this, scrnMsg);
        NPAL1200CommonLogic.setTableScreen(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).mdseItemClsTpCd_SE);
    }
}
