/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1150;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1150.NPAL1150CMsg;
import business.servlet.NPAL1150.common.NPAL1150CommonLogic;
import business.servlet.NPAL1150.constant.NPAL1150Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * NPAL1150 ASN Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/15   Hitachi         T.Kawazu        Create          N/A
 * 2013/07/30   Hitachi         A.Kohinata      Update          QC1388
 * 2017/10/27   CITS            M.Naito         Update          QC#20380
 *</pre>
 */
public class NPAL1150Scrn00_SoLink extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NPAL1150BMsg scrnMsg = (NPAL1150BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1150BMsg scrnMsg = (NPAL1150BMsg) bMsg;

        NPAL1150CMsg bizMsg = new NPAL1150CMsg();
        bizMsg.setBusinessID("NPAL1150");
        bizMsg.setFunctionCode("20");
        scrnMsg.xxNum.setValue(getButtonSelectNumber());
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1150BMsg scrnMsg = (NPAL1150BMsg) bMsg;
        NPAL1150CMsg bizMsg  = (NPAL1150CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Under Tab Display Setting
        if (scrnMsg.B.getValidCount() == 1) {
            scrnMsg.xxDplyTab.setValue(NPAL1150Constant.UNDER_TAB_HEADER);
            scrnMsg.B.setInputProtected(true);
        }
        // 2013/07/30 QC1388 A.Kohinata Add Start
        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(0).batErrMsgTxt_HD.no(0))) {
            scrnMsg.B.no(0).batErrMsgTxt_HV.setInputProtected(false);
        } else {
            scrnMsg.B.no(0).batErrMsgTxt_HV.setInputProtected(true);
        }
        // 2013/07/30 QC1388 A.Kohinata Add End
        NPAL1150CommonLogic.controlButton(this, scrnMsg);
        NPAL1150CommonLogic.controlPOEntryButton(this, scrnMsg);
        // 2017/10/27 QC20380 M.Naito Mod Start
//        scrnMsg.setFocusItem(scrnMsg.B.no(0).vndNm_H1);
        scrnMsg.setFocusItem(scrnMsg.B.no(0).dplyVndNm_H1);
        // 2017/10/27 QC20380 M.Naito Mod End

    }
}
