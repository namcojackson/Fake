/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1530;

import static business.servlet.NWAL1530.constant.NWAL1530Constant.BIZ_ID;
import static business.servlet.NWAL1530.constant.NWAL1530Constant.EXISTS_SELIAL_NUMBER;
import static business.servlet.NWAL1530.constant.NWAL1530Constant.TAB_SO;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1530.NWAL1530CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1530Scrn00_TAB_So
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWAL1530Scrn00_TAB_So extends S21CommonHandler {

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
        if (ZYPCommonFunc.hasValue(bizMsg.errMsgCd_SO)) {
            scrnMsg.setMessageInfo(bizMsg.errMsgCd_SO.getValue());
        } else {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                // 2018/07/11 QC#19801 add start
                scrnMsg.B.no(i).mdseDescShortTxt_B.setInputProtected(true);
                // 2018/07/11 QC#19801 add end
                if (!EXISTS_SELIAL_NUMBER.equals(scrnMsg.B.no(i).serNum_B.getValue())) {
                    scrnMsg.B.no(i).serNum_LK.setInputProtected(true);
                    scrnMsg.B.no(i).serNum_LK.clear();
                }
                // 2018/07/11 QC#19801 add start
                if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).carrTrkUrl_B)) {
                    scrnMsg.B.no(i).carrTrkUrl_B.setInputProtected(true);
                }
                // 2018/07/11 QC#19801 add end
            }
        }

        // Display TAB = SO
        scrnMsg.xxDplyTab.setValue(TAB_SO);
    }
}
