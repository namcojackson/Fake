/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7000;

import static business.servlet.NMAL7000.constant.NMAL7000Constant.BIZ_ID;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7000.NMAL7000CMsg;
import business.servlet.NMAL7000.common.NMAL7000CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL7000_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/16   Fujitsu         M.Nakamura      Create          N/A
 * 2016/08/01   Fujitsu         R.Nakamura      Update          QC#A10928
 *</pre>
 */
public class NMAL7000_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7000BMsg scrnMsg = (NMAL7000BMsg) bMsg;
        NMAL7000CMsg bizMsg = new NMAL7000CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7000BMsg scrnMsg = (NMAL7000BMsg) bMsg;
        NMAL7000CMsg bizMsg = (NMAL7000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7000CommonLogic.initCmnBtnProp(this);
        setScrnCtrl(scrnMsg);
        NMAL7000CommonLogic.btnProtect(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.prcCatgNm_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7000BMsg scrnMsg = (NMAL7000BMsg) bMsg;

        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        scrnMsg.srchOptPk_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Search Option"));
        scrnMsg.srchOptNm_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name"));

        scrnMsg.prcCatgNm_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price List Name"));
        scrnMsg.prcListDplyNm_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price Display List Name"));
        scrnMsg.prcCatgDescTxt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price List Description"));
        scrnMsg.prcContrNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Contract#"));
        scrnMsg.prcContrNm_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Contract Name"));

        scrnMsg.dsAcctNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Account#"));
        scrnMsg.dsAcctNm_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Account Name"));
        scrnMsg.csmpNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "CSMP#"));
        scrnMsg.coaBrCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Branch#"));
        scrnMsg.splyAgmtPlnNm_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Related Supply Plan Name"));

        scrnMsg.effFromDt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Effective Date From"));
        scrnMsg.effThruDt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Effective Date To"));

        // Add Start 2016/08/01 QC#10928
        scrnMsg.prcListMdseCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Item Number"));
        scrnMsg.mdlNm_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Service Model Name"));
        // Add End 2016/08/01 QC#10928

        scrnMsg.xxPageShowCurNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Current Page Number"));
    }

    private void setScrnCtrl(NMAL7000BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).prcCatgNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).prcListTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).prcListStruTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).prcDispRecTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).prcContrNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).prcContrNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).prcListGrpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).effThruDt_A1.setInputProtected(true);
        }
    }
}
