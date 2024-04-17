/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7000;

import static business.servlet.NMAL7000.constant.NMAL7000Constant.BIZ_ID;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.NMAM0192E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7000.NMAL7000CMsg;
import business.servlet.NMAL7000.common.NMAL7000CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7000Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/16   Fujitsu         M.Nakamura      Create          N/A
 * 2016/08/01   Fujitsu         R.Nakamura      Update          QC#10928
 *</pre>
 */
public class NMAL7000Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7000BMsg scrnMsg = (NMAL7000BMsg) bMsg;

        NMAL7000CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        // Mod Start 2016/08/01 QC#10928
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcCatgNm_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcListDplyNm_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcCatgDescTxt_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcContrNum_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcContrNm_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcListTpCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcListStruTpCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcListGrpCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.csmpNum_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.coaBrCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.splyAgmtPlnNm_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.effThruDt_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcListMdseCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.mdlNm_H1)
                ) {
            scrnMsg.setMessageInfo(NMAM0192E);
            throw new EZDFieldErrorException();
        }
        // Mod End 2016/08/01 QC#10928
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7000BMsg scrnMsg = (NMAL7000BMsg) bMsg;

        NMAL7000CMsg bizMsg = new NMAL7000CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7000BMsg scrnMsg = (NMAL7000BMsg) bMsg;
        NMAL7000CMsg bizMsg  = (NMAL7000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NMAL7000CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        NMAL7000CommonLogic.btnProtect(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.prcCatgNm_H1);
    }
}
