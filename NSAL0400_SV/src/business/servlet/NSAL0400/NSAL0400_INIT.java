/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0400;

import static business.servlet.NSAL0400.constant.NSAL0400Constant.*;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0400.NSAL0400CMsg;
import business.servlet.NSAL0400.common.NSAL0400CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/18   Fujitsu         M.Yamada        Create          N/A
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 *</pre>
 */
public class NSAL0400_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0400BMsg scrnMsg = (NSAL0400BMsg) bMsg;

        NSAL0400CMsg bizMsg = new NSAL0400CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());

        // set Invoker screen value.
        Serializable arg = super.getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params != null) {
                if (params.length > 0 && params[0] != null && params[0] instanceof EZDBMsgArray) {
                    EZDMsg.copy(((EZDBMsgArray) params[0]), null, scrnMsg.P, null);
                }
            }

//            if (params.length > 0) {
//                EZDBBigDecimalItem param0 = (EZDBBigDecimalItem) params[0];
//                ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrPk, param0.getValue());
//
//                if (params.length > 1) {
//                    EZDBBigDecimalItem param1 = (EZDBBigDecimalItem) params[1];
//                    if (ZYPCommonFunc.hasValue(param1) //
//                            && BigDecimal.ZERO.compareTo(param1.getValue()) != 0) {
//                        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrDtlPk, param1.getValue());
//                    }
//                }
//
//                if (params.length > 2) {
//                    EZDBStringItem param2 = (EZDBStringItem) params[2];
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrCatgCd, param2.getValue());
//                }
//            }
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0400BMsg scrnMsg = (NSAL0400BMsg) bMsg;
        NSAL0400CMsg bizMsg = (NSAL0400CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0400CommonLogic.initControlCommonButton(this);

        NSAL0400CommonLogic.initCommonButton(this);
        if (scrnMsg.A.getValidCount() > 0) {
            NSAL0400CommonLogic.protectFieldsAndButtons(this, scrnMsg);
            NSAL0400CommonLogic.setRowColors(scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.contrCloDt_H);
        }
        setAppFracDigit(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0400BMsg scrnMsg = (NSAL0400BMsg) bMsg;

        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        scrnMsg.svcMemoRsnCd_FS.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, SCREEN_LABEL.RSN_CD.getscreenLabel()));
        scrnMsg.svcCmntTxt_F.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, SCREEN_LABEL.CMNT.getscreenLabel()));
        scrnMsg.contrCloDt_H.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, SCREEN_LABEL.TRMN_DT.getscreenLabel()));
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            NSAL0400_BBMsg bbMsg = scrnMsg.B.no(i);
            bbMsg.contrCloDt_B.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, SCREEN_LABEL.TRMN_DT.getscreenLabel()));
            // START 2022/02/04 K.Kitachi [QC#59684, ADD]
            bbMsg.contrTrmnFlg_B.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, SCREEN_LABEL.ALL_PER_TRMN.getscreenLabel()));
            // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        }

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NSAL0400_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.contrCloDt_AD.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, SCREEN_LABEL.TRMN_DT.getscreenLabel()));
            abMsg.trmnOvrdTotAmt_AH.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, SCREEN_LABEL.OVRD_AMT.getscreenLabel()));
            abMsg.trmnOvrdTotAmt_AD.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, SCREEN_LABEL.OVRD_AMT.getscreenLabel()));
            // START 2022/02/04 K.Kitachi [QC#59684, ADD]
            abMsg.contrTrmnFlg_AD.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, SCREEN_LABEL.ALL_PER_TRMN.getscreenLabel()));
            // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        }
    }

    private void setAppFracDigit(NSAL0400BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).trmnTotAmt_B.setAppFracDigit(2);
            scrnMsg.B.no(i).trmnOvrdTotAmt_B.setAppFracDigit(2);
        }

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).trmnTotAmt_AH.setAppFracDigit(2);
            scrnMsg.A.no(i).trmnTotAmt_AD.setAppFracDigit(2);
            scrnMsg.A.no(i).trmnOvrdTotAmt_AH.setAppFracDigit(2);
            scrnMsg.A.no(i).trmnOvrdTotAmt_AD.setAppFracDigit(2);
        }
    }
}
