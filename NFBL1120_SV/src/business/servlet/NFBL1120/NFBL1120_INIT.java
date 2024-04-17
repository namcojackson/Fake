/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1120;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFBL1120.NFBL1120CMsg;
import business.servlet.NFBL1120.common.NFBL1120CommonLogic;
import business.servlet.NFBL1120.constant.NFBL1120Constant;
import business.servlet.NFBL1120.NFBL1120BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Invoice Maintenance Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   CUSA            Y.Aikawa        Create          N/A
 * 2016/09/13   Hitachi         K.Kojima        Update          QC#12725
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 * </pre>
 */
public class NFBL1120_INIT extends S21INITCommonHandler implements NFBL1120Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;

        Object[] param = (Object[]) getArgForSubScreen();
        if (param != null) {
            for (int i = 0; i < param.length; i++) {
                if (i == 0) {
                    // Invoice #
                    ZYPEZDItemValueSetter.setValue(scrnMsg.apBatNum, ((EZDBStringItem) param[i]).getValue());
                }
            }
        }

        NFBL1120CMsg bizMsg = new NFBL1120CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;
        NFBL1120CMsg bizMsg  = (NFBL1120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        /** Set confirm message when clicking button */ 
        NFBL1120CommonLogic.setButtonConfirmMsg(this);

        /** Initialize button control */ 
        NFBL1120CommonLogic.initControl(this, scrnMsg);
        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.apBatNum);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NFBL1120BMsg scrnMsg = (NFBL1120BMsg) arg0;
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        scrnMsg.apBatNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Batch #"));
        scrnMsg.prntVndCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplier"));
        scrnMsg.prntVndNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplier Name"));
        scrnMsg.apBatDt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Batch Date"));
        // START 2017/12/22 [QC#22831, MOD]
        scrnMsg.locNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplier Site Name"));
        // START 2017/12/22 [QC#22831, MOD]
        scrnMsg.apMaintInvStsCd_S.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice Status"));
        // START 2016/09/13 K.Kojima [QC#12725,MOD]
        // scrnMsg.apvrUsrId.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00,
        // "Current Approver"));
        // scrnMsg.apvrUsrNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00,
        // "Current Approver Name"));
        scrnMsg.varCharConstNm_S.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Current Approver"));
        // END 2016/09/13 K.Kojima [QC#12725,MOD]
        scrnMsg.apInvNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice #"));
        scrnMsg.invDt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice Date"));
    }
}
