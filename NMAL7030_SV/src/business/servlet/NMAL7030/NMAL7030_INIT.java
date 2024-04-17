/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7030;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7030.common.NMAL7030CommonLogic;
import business.servlet.NMAL7030.constant.NMAL7030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   SRA             E.Inada         Create          QC#2174
 * 2016/08/09   Fujitsu         W.Honda         Update          QC#13171
 *</pre>
 */
public class NMAL7030_INIT extends S21INITCommonHandler implements NMAL7030Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NMAL7030BMsg scrnMsg = (NMAL7030BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7030BMsg scrnMsg = (NMAL7030BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        NMAL7030CommonLogic.setInputParam(scrnMsg, (Object[]) arg);

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7030BMsg scrnMsg = (NMAL7030BMsg) bMsg;

        setAppFracDigit(scrnMsg);

        NMAL7030CommonLogic.initCmnBtnProp(this);

        NMAL7030CommonLogic.setHeaderProtect(scrnMsg);
        NMAL7030CommonLogic.setDetailProtect(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7030BMsg scrnMsg = (NMAL7030BMsg) bMsg;
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        scrnMsg.prcQlfyTpCd_H.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Qualifier Type"));
        scrnMsg.prcQlfyValTxt_H.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Value"));
        // QC#13171 2016/08/09 Mod start
//        scrnMsg.prodCtrlNm_H.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Description"));
        scrnMsg.mdseDescShortTxt_H.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Description"));
        // QC#13171 2016/08/09 Mod end

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Checkbox"));
            scrnMsg.A.no(i).qtyDiscDtlQty_A.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Qty"));
            scrnMsg.A.no(i).prcBreakAmt_A.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price Break Amt"));
            scrnMsg.A.no(i).xxScrStsTxt_A.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Status"));
        }
    }

    private void setAppFracDigit(NMAL7030BMsg scrnMsg) {

        int digitNum = DEF_DIGIT_NUM;
        if (ZYPCommonFunc.hasValue(scrnMsg.aftDeclPntDigitNum_H)) {
            digitNum = scrnMsg.aftDeclPntDigitNum_H.getValueInt();
        }

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).prcBreakAmt_A.setAppFracDigit(digitNum);
        }
    }
}
