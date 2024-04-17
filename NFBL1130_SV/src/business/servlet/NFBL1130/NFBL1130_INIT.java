/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFBL1130.NFBL1130CMsg;
import business.servlet.NFBL1130.common.NFBL1130CommonLogic;
import business.servlet.NFBL1130.constant.NFBL1130Constant;
import business.servlet.NFBL1130.NFBL1130BMsg;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CUSA            Y.Aikawa        Create          N/A
 * 2016/08/04   Fujitsu         T.Murai         Update          S21_NA#12865
 * 2016/09/30   Hitachi         K.Kojima        Update          QC#14178
 * 2022/08/05   Hitachi         A.Kohinata      Update          QC#59245
 *</pre>
 */
public class NFBL1130_INIT extends S21INITCommonHandler implements NFBL1130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Add Start 2016/08/04 S21_NA#12865
        // security violation check.
         checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
        // Add End 2016/08/04 S21_NA#12865
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;
        NFBL1130CMsg bizMsg = new NFBL1130CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;
        NFBL1130CMsg bizMsg  = (NFBL1130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        /** Set confirm message when clicking button */ 
        NFBL1130CommonLogic.setButtonConfirmMsg(this);

        /** Initialize button control */ 
        NFBL1130CommonLogic.initControl(this, scrnMsg);
        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.poNum);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) arg0;
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        scrnMsg.poNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "PO Number"));
        scrnMsg.mdseCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Item"));
        scrnMsg.delyOrdNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Receipt Number"));
        scrnMsg.invRcvQty.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Receipt Quantity"));
        scrnMsg.apVndInvNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice Number"));
        // START 2016/09/30 K.Kojima [QC#14179,MOD]
        // scrnMsg.apVndCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00,
        // "Supplier"));
        scrnMsg.prntVndCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplier"));
        // END 2016/09/30 K.Kojima [QC#14179,MOD]
        scrnMsg.prntVndNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplier Name"));
        scrnMsg.invQty.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice Quantity"));
        scrnMsg.acrlCoaAcctCd_S.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Accrual Account Code"));
        // START 2016/09/29 K.Kojima [QC#14178,ADD]
        scrnMsg.rwsNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "RWS Number"));
        // END 2016/09/29 K.Kojima [QC#14178,ADD]
        scrnMsg.invDt_FR.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice Date From"));
        scrnMsg.invDt_TO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice Date To"));
        scrnMsg.stkInDt_FR.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Receipt Date From"));
        scrnMsg.stkInDt_TO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Receipt Date To"));
        scrnMsg.xxChkBox_WO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Include Write Off"));
        scrnMsg.xxChkBox_PM.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Include PO matched"));
        // add start 2022/08/05 QC#59245
        scrnMsg.xxChkBox_CV.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Cost Change Variances"));
        // add end 2022/08/05 QC#59245
    }
}
