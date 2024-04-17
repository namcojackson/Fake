/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.BIZ_ID;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAM0681E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1330.NSAL1330CMsg;
import business.servlet.NSAL1330.common.NSAL1330CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1330Scrn00_ApplyAllFromModel
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 * 2017/06/12   Hitachi         A.Kohinata      Update          QC#18931
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 *</pre>
 */
public class NSAL1330Scrn00_ApplyAllFromModel extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // mod start 2017/06/12 QC#18931
        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxNum_A.setValue(selectIndex);
        // mod end 2017/06/12 QC#18931
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        NSAL1330CMsg bizMsg = new NSAL1330CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
//        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//        scrnMsg.putErrorScreen();

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        if (NSAM0681E.equals(scrnMsg.getMessageCode())) {
            NSAL1330CommonLogic.initAppFracDigit(scrnMsg);
            NSAL1330CommonLogic.initCmnBtnProp(this, scrnMsg);
            NSAL1330CommonLogic.initInputProtected(scrnMsg);
            NSAL1330CommonLogic.initButton(this, scrnMsg);
            return;
        }
        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // 2018/05/07 QC#22482 Del Start
//        NSAL1330CommonLogic.setScrnManOvrdDspCtrl(scrnMsg);
        // 2018/05/07 QC#22482 Del End
        NSAL1330CommonLogic.setAccessoryChargeAreaCtrl(scrnMsg);
        NSAL1330CommonLogic.setUsgPrcAreaCtrl(scrnMsg, this);

        NSAL1330CommonLogic.initAppFracDigit(scrnMsg);
        NSAL1330CommonLogic.initCmnBtnProp(this, scrnMsg);
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxFuncId_UP)) {
            NSAL1330CommonLogic.protectRefMode(this, scrnMsg);
            return;
        }
        NSAL1330CommonLogic.initInputProtected(scrnMsg);
        NSAL1330CommonLogic.initButton(this, scrnMsg);
        NSAL1330CommonLogic.overrideProtected(this, scrnMsg);
        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            NSAL1330CommonLogic.setBandButton(this, scrnMsg, NSAL1330CommonLogic.isParentLine(scrnMsg.Z.no(i)), i);
        }
        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            NSAL1330CommonLogic.setBandButtonConfig(this, scrnMsg, NSAL1330CommonLogic.isParentLine(scrnMsg.U.no(i)), i);
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1330CommonLogic.setTierLinkCtrl(this, scrnMsg, i);
            NSAL1330CommonLogic.setSupplyButton(this, scrnMsg.A.no(i), i, scrnMsg);
        }
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            NSAL1330CommonLogic.setTierLinkCtrlConfig(this, scrnMsg, i);
            NSAL1330CommonLogic.setSupplyButton(this, scrnMsg.R.no(i), i, scrnMsg);
        }
        NSAL1330CommonLogic.setCustomerCtrlConfig(scrnMsg);
        scrnMsg.putErrorScreen();

        // 2018/05/07 QC#22482 Mod Start
//        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.manContrOvrdFlg.getValue())) {
//            scrnMsg.setFocusItem(scrnMsg.svcMemoRsnCd);
//        } else {
//            scrnMsg.setFocusItem(scrnMsg.A.no(0).prcCatgNm_A);
//        }
        scrnMsg.setFocusItem(scrnMsg.A.no(0).prcCatgNm_A);
        // 2018/05/07 QC#22482 Mod End
    }
}
