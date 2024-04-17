/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2410;

import static business.servlet.NWAL2410.constant.NWAL2410Constant.BIZ_ID;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.DISP_CODE;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.MAINTENANCE_BRANCH;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.NWAM0181E;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.NWAM0926E;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.NWAM0928W;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.NZZM0002I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2410.NWAL2410CMsg;
import business.servlet.NWAL2410.common.NWAL2410CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.printing.S21PrintingBusinessApInOutEJBLocal;

/**
 *<pre>
 * NWAL2410Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/25   Fujitsu         N.Aoyama        Create          QC#16740
 *</pre>
 */
public class NWAL2410Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2410BMsg scrnMsg = (NWAL2410BMsg) bMsg;

        NWAL2410CommonLogic.addCheckDetailsItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2410BMsg scrnMsg = (NWAL2410BMsg) bMsg;
        NWAL2410CMsg bizMsg = new NWAL2410CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        bizMsg.xxRqstFlg.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.xxEdtModeFlg.setValue(ZYPConstant.FLG_OFF_N);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2410BMsg scrnMsg = (NWAL2410BMsg) bMsg;
        NWAL2410CMsg bizMsg = (NWAL2410CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2410CommonLogic.addCheckDetailsItem(scrnMsg);
        NWAL2410CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NWAL2410CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());
        scrnMsg.putErrorScreen();

        bizMsg.xxRqstFlg.setValue(ZYPConstant.FLG_ON_Y);
        bizMsg = (NWAL2410CMsg) new S21PrintingBusinessApInOutEJBLocal().executeBlap(ctx, bizMsg, BIZ_ID, "40");
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2410CommonLogic.addCheckDetailsItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            if (NWAM0181E.equals(bizMsg.getMessageCode())) {
                scrnMsg.setMessageInfo(bizMsg.getMessageCode(), new String[] {MAINTENANCE_BRANCH });
            } else if (NWAM0926E.equals(bizMsg.getMessageCode())) {
                scrnMsg.setMessageInfo(bizMsg.getMessageCode(), new String[] {DISP_CODE });
            } else {
                scrnMsg.setMessageInfo(bizMsg.getMessageCode());
            }
            throw new EZDFieldErrorException();
        }

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        } else if (EZDMessageInfo.MSGTYPE_WARNING == scrnMsg.getMessageType()) {
            return;
        }

        NWAL2410CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NWAL2410CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxEdtModeFlg.getValue())) {
            scrnMsg.setMessageInfo(NWAM0928W);
            bizMsg.xxEdtModeFlg.setValue(ZYPConstant.FLG_OFF_N);
            scrnMsg.xxEdtModeFlg.setValue(ZYPConstant.FLG_OFF_N);
            return;
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
        scrnMsg.setFocusItem(scrnMsg.docMgtOrgCd);

    }
}
