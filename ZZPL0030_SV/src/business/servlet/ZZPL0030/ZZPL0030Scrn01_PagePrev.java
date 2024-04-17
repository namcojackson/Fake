/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZPL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZPL0030.ZZPL0030CMsg;
import business.servlet.ZZPL0030.common.ZZPL0030CommonLogic;
import business.servlet.ZZPL0030.constant.ZZPL0030Constant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0030Scrn01_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;

        // set values to items of pagenation for prev page
        scrnMsg.B.clear();
        scrnMsg.B.setValidCount(0);
        ZYPTableUtil.clear(scrnMsg.B);
        scrnMsg.xxPageShowFromNum_1.setValue(scrnMsg.xxPageShowFromNum_1.getValueInt() - scrnMsg.B.length() - 1);
        scrnMsg.xxPageShowToNum_1.clear();

        ZZPL0030CMsg bizMsg = new ZZPL0030CMsg();
        bizMsg.setBusinessID(ZZPL0030Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(ZZPL0030Constant.FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;
        ZZPL0030CMsg bizMsg = (ZZPL0030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZZPL0030CommonLogic.setCreationTimeFormat(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.xxCratDt_FR);

        // set table background color
        ZZPL0030CommonLogic.setTableRowColor(scrnMsg, ZZPL0030Constant.SCREENID_SCRN01);

    }
}
