/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0030;

import static business.servlet.NWWL0030.constant.NWWL0030Constant.BIZ_ID;
import static business.servlet.NWWL0030.constant.NWWL0030Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWWL0030.constant.NWWL0030Constant.NWWM0005E;
import static business.servlet.NWWL0030.constant.NWWL0030Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWWL0030.NWWL0030CMsg;
import business.servlet.NWWL0030.common.NWWL0030CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0030Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0030Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0030BMsg scrnMsg = (NWWL0030BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.ntfyHdrNm);
        scrnMsg.addCheckItem(scrnMsg.ntfyHdrDescTxt);
        scrnMsg.addCheckItem(scrnMsg.ntfyBizAreaTpCd);
        scrnMsg.addCheckItem(scrnMsg.ntfySubAreaTpCd);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.addCheckItem(scrnMsg.ntfyRunJobId);

        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt) && ZYPCommonFunc.hasValue(scrnMsg.effThruDt)) {

            if (ZYPDateUtil.compare(scrnMsg.effThruDt.getValue(), scrnMsg.effFromDt.getValue()) < 0) {
                scrnMsg.effFromDt.setErrorInfo(1, NWWM0005E, // 
                        new String[] {scrnMsg.effThruDt.getNameForMessage(), scrnMsg.effFromDt.getNameForMessage() });
                scrnMsg.effThruDt.setErrorInfo(1, NWWM0005E, // 
                        new String[] {scrnMsg.effThruDt.getNameForMessage(), scrnMsg.effFromDt.getNameForMessage() });
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0030BMsg scrnMsg = (NWWL0030BMsg) bMsg;

        NWWL0030CMsg bizMsg = new NWWL0030CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWWL0030BMsg scrnMsg = (NWWL0030BMsg) bMsg;
        NWWL0030CMsg bizMsg = (NWWL0030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWWL0030CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NWWL0030CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");

        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.B.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.ntfyHdrNm);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

    }
}
