/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0080.NFAL0080CMsg;
import business.servlet.NFAL0080.common.NFAL0080CommonLogic;
import business.servlet.NFAL0080.constant.NFAL0080Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * Class name: Screen Component ID : NFAL0080_INIT
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0080_INIT extends S21INITCommonHandler implements NFAL0080Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0080BMsg scrnMsg = (NFAL0080BMsg) bMsg;

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0080BMsg scrnMsg = (NFAL0080BMsg) bMsg;

        NFAL0080CMsg bizMsg = new NFAL0080CMsg();
        bizMsg.setBusinessID("NFAL0080");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0080BMsg scrnMsg = (NFAL0080BMsg) bMsg;
        NFAL0080CMsg bizMsg = (NFAL0080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFAL0080CommonLogic.initButtonConfirmMsg(this);
        NFAL0080CommonLogic.initButton(scrnMsg, this);
        NFAL0080CommonLogic.initFocusItem(scrnMsg);
        NFAL0080CommonLogic.setProtectEligCoaSetPtrnCdList(scrnMsg);
        NFAL0080CommonLogic.setNameForMessageCommon(scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFAL0080BMsg scrnMsg = (NFAL0080BMsg) bMsg;
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        scrnMsg.eligCoaSegPtrnCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Eligible COA Segment Pattern"));
        scrnMsg.coaSegLkupTpCd_3.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "COA Segment Lookup Type"));
    }

}
