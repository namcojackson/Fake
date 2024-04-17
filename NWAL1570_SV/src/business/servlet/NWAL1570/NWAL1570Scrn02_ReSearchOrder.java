/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL1570.NWAL1570CMsg;
import business.servlet.NWAL1570.common.NWAL1570CommonLogic;
import business.servlet.NWAL1570.constant.NWAL1570Constant.RSLT_MODE;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1570Scrn02_ReSearchOrder
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 *</pre>
 */
public class NWAL1570Scrn02_ReSearchOrder extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        // When the Display By item overlaps, it is omitted.
        NWAL1570CommonLogic.setDisplayByItem(scrnMsg);

        NWAL1570CMsg bizMsg = NWAL1570CommonLogic.createSearchCMsg();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;
        NWAL1570CMsg bizMsg  = (NWAL1570CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1570CommonLogic.addCheckItemBizLayerErr(scrnMsg);
        scrnMsg.putErrorScreen();

        if (NWAL1570CommonLogic.hasMsgError(bizMsg)) {
            throw new EZDFieldErrorException();
        }

        // 1. initialize GUI attribute.
        NWAL1570CommonLogic.initGuiAttrScrnRslt(this, scrnMsg);

        // 2. initialize GUI value.
        NWAL1570CommonLogic.initGuiValueScrnRslt(scrnMsg);

        // 3. set "Display-By" label of Table Head.
//        NWAL1570CommonLogic.setDisplayByLabel(scrnMsg);

        // 4. set tab transition
//        NWAL1570CommonLogic.setTabTransion(scrnMsg);

        // 5. set alternate rows back-ground color
        NWAL1570CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        // 6. set next screen
        String resultId = "";
        if (bizMsg.xxRsltModeCd.getValue().equals(RSLT_MODE.ORDER_INQUIRY.getRsltModeCd())) {
            resultId = "toInquiryByStatus";
        } else {
            resultId = "toStatusSummary";
        }
        setResult(resultId);

    }
}
