package business.servlet.NMAL7120;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7120.NMAL7120CMsg;
import business.servlet.NMAL7120.common.NMAL7120CommonLogic;
import business.servlet.NMAL7120.constant.NMAL7120Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 08/03/2016   Fujitsu         R.Nakamura      Update          QC#12173
 * 09/12/2017   Fujitsu         K.Ishizuka      Update          QC#20206(Sol#269)
 *</pre>
 */
public class NMAL7120_INIT extends S21INITCommonHandler implements NMAL7120Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7120BMsg scrnMsg = (NMAL7120BMsg) bMsg;
        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            if (params.length == 1) {
                EZDBStringItem param01 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcContrNum_H1, param01);
            }
            NMAL7120CommonLogic.setPage(scrnMsg, 1);
        }

        NMAL7120CMsg bizMsg = new NMAL7120CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7120BMsg scrnMsg = (NMAL7120BMsg) bMsg;
        NMAL7120CMsg bizMsg = (NMAL7120CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL7120CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
        if (ZYPCommonFunc.hasValue(scrnMsg.prcContrNum_H1)) {
            if (scrnMsg.A.getValidCount() > 0) {
                scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).dsAcctNum_A1);
            }
        } else {
            scrnMsg.setFocusItem(scrnMsg.dsAcctNm_H1);
        }

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7120BMsg scrnMsg = (NMAL7120BMsg) bMsg;
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        scrnMsg.dsAcctNm_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Account Name"));
        scrnMsg.dsAcctNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Account Number"));
        scrnMsg.csmpNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "CSMP#"));
        scrnMsg.dlrRefNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "CSA#"));
        scrnMsg.prcCatgNm_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "CSMP Level"));
        scrnMsg.prcContrNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Contract#"));
        scrnMsg.effFromDt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Effective Date From"));
        scrnMsg.effThruDt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Expiration Date"));
        scrnMsg.csmpContrActvFlg_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Status"));
        scrnMsg.rtlDlrCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Dealer Code"));
        scrnMsg.rnwCsmpNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Renewed CSMP#"));

        scrnMsg.csmpNum_MU.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "CSMP#"));
        scrnMsg.dlrRefNum_MU.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "CSA#"));
        scrnMsg.prcContrNum_MU.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Contract#"));
        scrnMsg.csmpNumCmntTxt_MU.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Note"));
        scrnMsg.effFromDt_MU.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Effective Date From"));
        scrnMsg.effThruDt_MU.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Expiration Date"));
        scrnMsg.csmpContrActvFlg_MU.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Status"));
        scrnMsg.cusaRejDt_MU.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Rejection Date"));
        scrnMsg.uplftEquipRatio_MU.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Equipment Markup"));
        scrnMsg.uplftAsryRatio_MU.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Accessory Markup"));
        scrnMsg.rnwCsmpNum_MU.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Renewed CSMP#"));
        scrnMsg.xxPageShowCurNum_10.setNameForMessage(JUMP_PAGE_NUMBER_ITNM); //S21_NA ADD QC#20206(L3#269)

        // Add Start 2016/08/03 QC#12173
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dsAcctNum_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Account Number"));
            scrnMsg.A.no(i).dsAcctNm_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Account Name"));
            scrnMsg.A.no(i).csmpNum_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "CSMP Number"));
            scrnMsg.A.no(i).dlrRefNum_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "CSA Number"));
            scrnMsg.A.no(i).prcCatgNm_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "CSMP Level"));
            scrnMsg.A.no(i).prcContrNum_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Contract Number"));
            scrnMsg.A.no(i).origCoaBrCd_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Originating GL Branch Code"));
            scrnMsg.A.no(i).rtlDlrCd_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Origin Dealer Code"));
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Effective Date"));
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Expiration Date"));
            scrnMsg.A.no(i).cusaRejDt_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Rejection Date"));
            scrnMsg.A.no(i).erlTrmnDt_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Early Term Date"));
            scrnMsg.A.no(i).rnwCsmpNum_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Renewed CSMP#"));
            scrnMsg.A.no(i).uplftEquipRatio_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Markup Equipment"));
            scrnMsg.A.no(i).uplftAsryRatio_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Markup Accessory"));
            scrnMsg.A.no(i).csmpNumCmntTxt_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Notes"));
            scrnMsg.A.no(i).custMbrId_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Customer  Membership ID"));
            scrnMsg.A.no(i).csmpContrActvFlg_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Active"));
            scrnMsg.A.no(i).xxModeNm13Txt_A1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Expired"));
        }
        // Add End 2016/08/03 QC#12173
    }

}
