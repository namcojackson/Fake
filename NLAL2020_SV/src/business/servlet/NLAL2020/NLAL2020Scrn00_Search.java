/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2020.NLAL2020CMsg;
import business.servlet.NLAL2020.common.NLAL2020CommonLogic;
import business.servlet.NLAL2020.constant.NLAL2020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLAL2020Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         Y.Taoka         Create          N/A
 * 05/19/2016   CSAI            Y.Imazu         Update          QC#8336
 * 04/03/2019   Fujitsu         T.Ogura         Update          QC#31000
 *</pre>
 */
public class NLAL2020Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;
        scrnMsg.xxWrnSkipFlg_RC.clear();    // 2019/04/03 T.Ogura [QC#31000,ADD]

        NLAL2020CommonLogic.addCheckItemHeader(scrnMsg);
        scrnMsg.putErrorScreen();

        NLAL2020CommonLogic.inputCheckHeader(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;
        NLAL2020CMsg bizMsg = new NLAL2020CMsg();
        bizMsg.setBusinessID(NLAL2020Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;
        NLAL2020CMsg bizMsg  = (NLAL2020CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLAL2020CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NLAL2020CommonLogic.initCmnBtnProp(this);
        NLAL2020CommonLogic.controlScreenFields(this, scrnMsg);
        NLAL2020CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, NLAL2020Bean.A);
        NLAL2020CommonLogic.addCheckItemHeader(scrnMsg);
        scrnMsg.putErrorScreen();

        if (NLAL2020Constant.MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {

            throw new EZDFieldErrorException();
        }

        scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A1);
    }
}