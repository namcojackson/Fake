/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2720;

import static business.servlet.NMAL2720.constant.NMAL2720Constant.BIZ_ID;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.NMAM0192E;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.SCRN_ID_00;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2720.common.NMAL2720CommonLogic;

import business.blap.NMAL2720.NMAL2720CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2720Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/22   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL2720Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;
        NMAL2720CommonLogic.addCheckItemScreen(scrnMsg, false);

        if (!ZYPCommonFunc.hasValue(scrnMsg.bizAreaOrgCd_H)
                && !ZYPCommonFunc.hasValue(scrnMsg.bizAreaOrgCd_H)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxPsnNm_H)
                && !ZYPCommonFunc.hasValue(scrnMsg.psnNum_H)
                && !ZYPCommonFunc.hasValue(scrnMsg.orgNm_H)
                && !ZYPCommonFunc.hasValue(scrnMsg.psnCd_H)) {
            scrnMsg.setMessageInfo(NMAM0192E);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;

        NMAL2720CMsg bizMsg = new NMAL2720CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;
        NMAL2720CMsg bizMsg  = (NMAL2720CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NMAL2720CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.bizAreaOrgCd_H);
    }
}
