/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320;

import static business.servlet.NSAL1320.constant.NSAL1320Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1320.NSAL1320CMsg;
import business.servlet.NSAL1320.NSAL1320BMsg;
import business.servlet.NSAL1320.common.NSAL1320CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1320Scrn00_OnBlur_DeriveFromAddToContract
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 * 2017/10/24   Hitachi         Y.Takeno        Update          QC#21556
 * 2018/05/17   Fujitsu         A.Kosai         Update          QC#22482
 *</pre>
 */
public class NSAL1320Scrn00_OnBlur_DeriveFromAddToContract extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIndex);

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).dsContrNum_AD)) {
            scrnMsg.addCheckItem(scrnMsg.A.no(selectIndex).dsContrNum_AD);
            scrnMsg.putErrorScreen();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).dsContrNum_AD)) {
            scrnMsg.A.no(selectIndex).addAsryFlg.clear();
            return null;
        }

        NSAL1320CMsg bizMsg = new NSAL1320CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        setResult(ZYPConstant.FLG_OFF_N);
        int selectIndex = getButtonSelectNumber();
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIndex).dsContrNum_AD)) {
            scrnMsg.A.no(selectIndex).dsContrPk_AD.clear();
            // START 2017/10/24 [QC#21556, ADD]
            // 2018/05/17 QC#22482 Mod Start
//            NSAL1320CommonLogic.unprotectLineItemForAddContr(scrnMsg, scrnMsg.A.no(selectIndex));
            NSAL1320CommonLogic.unprotectLineItemForAddContr(this, scrnMsg, scrnMsg.A.no(selectIndex), selectIndex);
            // 2018/05/17 QC#22482 Mod End
            // END   2017/10/24 [QC#21556, ADD]
            return;
        }

        NSAL1320CMsg bizMsg = (NSAL1320CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 2018/05/17 QC#22482 Add Start
        NSAL1320CommonLogic.setScrnManOvrdDspCtrl(scrnMsg);
        // 2018/05/17 QC#22482 Add End
        scrnMsg.addCheckItem(scrnMsg.A.no(selectIndex).dsContrNum_AD);
        scrnMsg.putErrorScreen();
        // START 2017/10/24 [QC#21556, ADD]
        // 2018/05/17 QC#22482 Mod Start
//        NSAL1320CommonLogic.protectLineItemForAddContr(scrnMsg, scrnMsg.A.no(selectIndex));
        NSAL1320CommonLogic.protectLineItemForAddContr(this, scrnMsg, scrnMsg.A.no(selectIndex), selectIndex);
        // 2018/05/17 QC#22482 Mod End
        // END   2017/10/24 [QC#21556, ADD]
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());
            Object[] params = NSAL1320CommonLogic.getNSAL0110Prm(scrnMsg, getButtonSelectNumber());
            setArgForSubScreen(params);
        }
        scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).dsContrNum_AD);
    }
}
