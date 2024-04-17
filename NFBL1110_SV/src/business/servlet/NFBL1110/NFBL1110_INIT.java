/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1110;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFBL1110.NFBL1110CMsg;
import business.servlet.NFBL1110.NFBL1110BMsg;
import business.servlet.NFBL1110.common.NFBL1110CommonLogic;
import business.servlet.NFBL1110.constant.NFBL1110Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * AP Invoice Maintenance Batch Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   CUSA            Y.Aikawa        Create          N/A
 * 2016/08/05   Fujitsu         T.Murai         Update          QC#12711
 * 2016/09/09   Hitachi         K.Kojima        Update          QC#14038
 * 2016/09/09   Hitachi         K.Kojima        Update          QC#12725
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 * </pre>
 */
public class NFBL1110_INIT extends S21INITCommonHandler implements NFBL1110Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;

        Object[] param = (Object[]) getArgForSubScreen();
        if (param != null) {
            for (int i = 0; i < param.length; i++) {
                if (i == 0) {
                    // Invoice #
                    // START 2016/09/09 K.Kojima [QC#14038,MOD]
                    // ZYPEZDItemValueSetter.setValue(scrnMsg.apInvNum_IH,
                    // ((EZDBStringItem) param[i]).getValue());
                    if (param[i] instanceof EZDBStringItem) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.apInvNum_IH, ((EZDBStringItem) param[i]).getValue());
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.apInvNum_IH, (String) param[i]);
                    }
                    // END 2016/09/09 K.Kojima [QC#14038,MOD]
                }
                if (i == 1) {
                    // Supplier Site Code
                    // START 2016/09/09 K.Kojima [QC#14038,MOD]
                    // ZYPEZDItemValueSetter.setValue(scrnMsg.apVndCd_HD,
                    // ((EZDBStringItem) param[i]).getValue());
                    if (param[i] instanceof EZDBStringItem) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.apVndCd_HD, ((EZDBStringItem) param[i]).getValue());
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.apVndCd_HD, (String) param[i]);
                    }
                    // END 2016/09/09 K.Kojima [QC#14038,MOD]
                }
            }
        }

        NFBL1110CMsg bizMsg = new NFBL1110CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;
        NFBL1110CMsg bizMsg  = (NFBL1110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        /** Set confirm message when clicking button */ 
        NFBL1110CommonLogic.setButtonConfirmMsg(this);
        /** Initialize input control */ 
        NFBL1110CommonLogic.initControl(this, scrnMsg);
        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.apBatNum_BA);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) arg0;
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        scrnMsg.apBatNum_BA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Batch #"));
        scrnMsg.apCtrlAmt_BA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Control Amount"));
        scrnMsg.apCtrlCnt_BA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Control Count"));
        scrnMsg.apBatDt_BA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Batch Date"));
        scrnMsg.apRunTotAmt_BA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Running Total"));
        scrnMsg.apRunTotCnt_BA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Running Count"));
        // START 2016/09/09 K.Kojima [QC#12725,MOD]
        // scrnMsg.apvrUsrNm_IH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00,
        // "Current Approver"));
        scrnMsg.varCharConstVal_IH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Current Approver"));
        // END 2016/09/09 K.Kojima [QC#12725,MOD]
        scrnMsg.apInvAmt_IH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice Amount"));
        scrnMsg.apDsWfStsNm_IH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Workflow Status"));
        scrnMsg.prntVndCd_IH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplier"));
        scrnMsg.prntVndNm_IH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplier Name"));
        scrnMsg.apMiscAmt_IH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Misc. Header  Amount"));
        scrnMsg.apInvNum_IH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice #"));
        scrnMsg.apVndCd_HD.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplier Site Code"));
        // START 2017/12/22 [QC#22831, MOD]
        // scrnMsg.vndSiteNm_IH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Site Name"));
        scrnMsg.vndSiteNm_IH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplier Site Name"));
        // END   2017/12/22 [QC#22831, MOD]
        scrnMsg.apTaxAmt_IH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Tax Amount"));
        scrnMsg.invDt_IH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice Date"));
        scrnMsg.apMaintInvStsNm_IH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Maintenance Invoice Status"));
        scrnMsg.lateFeeAmt_IH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Late Fee Amount"));
        scrnMsg.xxChkBox_AD.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Override Serial # checkbox"));
        scrnMsg.serNum_AD.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Serial #"));
        // Mod 2016/08/04 S21_NA#12711
        //scrnMsg.ovrdSerNum_AD.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Override Serial #"));
        scrnMsg.ovrdSerNum_AD.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Override Serial flg"));

        scrnMsg.startDt_AD.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Start Date"));
        scrnMsg.endDt_AD.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "End Date"));
        scrnMsg.baseAmt_AD.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Base"));
        scrnMsg.invCmntTxt_CO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Comments"));
        scrnMsg.apAdjAmt_CO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Adjusted Invoice Amount"));
        scrnMsg.apAdjRsnCd_CO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Adj. Reason"));

        for (int i = 0 ; i < scrnMsg.A.length() ; i ++) {
            NFBL1110_ABMsg lineMsg = scrnMsg.A.no(i);
            lineMsg.serNum_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Serial #"));
            lineMsg.ovrdSerNum_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Override Serial flg"));
            lineMsg.startDt_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Start Date"));
            lineMsg.endDt_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "End Date"));
            lineMsg.baseAmt_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Base"));
            lineMsg.cntrTpCd_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Counter Type"));
            lineMsg.startReadMtrCnt_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Start MTR"));
            lineMsg.endReadMtrCnt_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "End MTR"));
            lineMsg.apTolAmt_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Overage"));
         }

    }
}
