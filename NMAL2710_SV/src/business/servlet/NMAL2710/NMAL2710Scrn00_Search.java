/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2710;

import static business.servlet.NMAL2710.constant.NMAL2710Constant.BIZ_ID;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.NMAM0192E;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.SCRN_ID;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2710.NMAL2710CMsg;
import business.servlet.NMAL2710.common.NMAL2710CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2710Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL2710Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;

        NMAL2710CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL2710CommonLogic.addCheckItemForDetail(scrnMsg);

        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.bizAreaOrgCd_H)
                && !ZYPCommonFunc.hasValue(scrnMsg.trtyGrpTpCd_H)
                && !ZYPCommonFunc.hasValue(scrnMsg.orgNm_H)
                && !ZYPCommonFunc.hasValue(scrnMsg.trtyRuleFromValTxt_H)
                && !ZYPCommonFunc.hasValue(scrnMsg.trtyRuleThruValTxt_H)) {
            scrnMsg.setMessageInfo(NMAM0192E);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;

        NMAL2710CMsg bizMsg = new NMAL2710CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;
        NMAL2710CMsg bizMsg  = (NMAL2710CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NMAL2710CommonLogic.controlScreen(this, scrnMsg);

        NMAL2710CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.bizAreaOrgCd_H);
        }
    }
}
