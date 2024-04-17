/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL6700.NMAL6700CMsg;
import business.servlet.NMAL6700.common.NMAL6700CommonLogic;
import business.servlet.NMAL6700.constant.NMAL6700Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/07   Fujitsu         C.Yokoi         Update          CSA-QC#6633
 * 2022/11/25   Hitachi         H.Watanabe      Update          QC#60398
 *</pre>
 */
public class NMAL6700Scrn00_AddTransactionRule extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        NMAL6700CommonLogic.clearMandatoryError(scrnMsg);
        for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.F.no(i).dsBlktPoNum_F1);
            scrnMsg.addCheckItem(scrnMsg.F.no(i).dsPoExprDt_F1);
            scrnMsg.addCheckItem(scrnMsg.F.no(i).dsDefBillToCd_F1);
            scrnMsg.addCheckItem(scrnMsg.F.no(i).dsDefShipToCd_F1);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        NMAL6700CMsg bizMsg = new NMAL6700CMsg();
        bizMsg.setBusinessID(NMAL6700Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        NMAL6700CMsg bizMsg = (NMAL6700CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6700CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());

        if (scrnMsg.F.getValidCount() > 0) {
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                scrnMsg.setFocusItem(scrnMsg.F.no(0).dsTrxRuleTpCd_F3);
            } else {
                scrnMsg.setFocusItem(scrnMsg.F.no(scrnMsg.F.getValidCount() - 1).dsTrxRuleTpCd_F3);
            }
        }

        // 2022/11/25 QC#60398 Add Start 
        for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.F.no(i).dsPoReqFlg_F1.getValue())) {
                scrnMsg.F.no(i).hardCopyReqFlg_F1.setInputProtected(false);
            } else {
                scrnMsg.F.no(i).hardCopyReqFlg_F1.setInputProtected(true);
                scrnMsg.F.no(i).hardCopyReqFlg_F1.clear();

            }
        }
        // 2022/11/25 QC#60398 Add End 
    }
}
