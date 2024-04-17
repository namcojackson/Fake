/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1230;

import static business.servlet.NPAL1230.constant.NPAL1230Constant.BIZ_APP_ID;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.NMAM0835E;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.NPAM1516E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1230.NPAL1230CMsg;
import business.servlet.NPAL1230.common.NPAL1230CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1230 ASL Entry
 * </pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/12   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NPAL1230Scrn00_OnClick_DeleteRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1230BMsg scrnMsg = (NPAL1230BMsg) bMsg;

        boolean chkBoxFlg = false;
        boolean deleteErrFlg = false;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).aslDtlPk_A)) {
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
                    scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NPAM1516E);
                    deleteErrFlg = true;
                } else {
                    chkBoxFlg = true;
                    break;
                }
            }
        }
        if (deleteErrFlg) {
            scrnMsg.putErrorScreen();
            return;
        }

        if (!chkBoxFlg) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
                scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NMAM0835E);
            }
            scrnMsg.putErrorScreen();
            return;
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1230BMsg scrnMsg = (NPAL1230BMsg) bMsg;

        NPAL1230CMsg bizMsg = new NPAL1230CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1230BMsg scrnMsg = (NPAL1230BMsg) bMsg;
        NPAL1230CMsg bizMsg  = (NPAL1230CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPAL1230CommonLogic.ctrlDetailButton(this, scrnMsg);
        NPAL1230CommonLogic.setTableScreen(scrnMsg);

        // set focus
        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).mdseCd_A);
        } else {
            scrnMsg.setFocusItem(scrnMsg.aslNm);
        }
    }
}
