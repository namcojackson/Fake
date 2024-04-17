/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1530;

import static business.servlet.NWAL1530.constant.NWAL1530Constant.BIZ_ID;
import static business.servlet.NWAL1530.constant.NWAL1530Constant.TAB_PO;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1530.NWAL1530CMsg;
import business.servlet.NWAL1530.common.NWAL1530CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1530Scrn00_TAB_Po
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Fujitsu         S.Ohki          Create          N/A
 * 2018/07/11   Fujitsu         M.Ohno          Update          QC#19801
 *</pre>
 */
public class NWAL1530Scrn00_TAB_Po extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1530BMsg scrnMsg = (NWAL1530BMsg) bMsg;

        NWAL1530CMsg bizMsg = new NWAL1530CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1530BMsg scrnMsg = (NWAL1530BMsg) bMsg;
        NWAL1530CMsg bizMsg = (NWAL1530CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Set the error to tab
        if (ZYPCommonFunc.hasValue(bizMsg.errMsgCd_PO)) {
            scrnMsg.setMessageInfo(bizMsg.errMsgCd_PO.getValue());
        }

        // 2018/07/11 QC#19801 add start
        NWAL1530CommonLogic.initCmnBtnProp(this);
        for (int i=0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.C.no(i).mdseDescShortTxt_C.setInputProtected(true);
            if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(i).carrTrkUrl_C)) {
                scrnMsg.C.no(i).carrTrkUrl_C.setInputProtected(true);
            }
        }
        // 2018/07/11 QC#19801 add end

        // Display TAB = Shipping Detail
        scrnMsg.xxDplyTab.setValue(TAB_PO);
    }
}
