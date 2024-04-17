/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFBL2060.NFBL2060CMsg;
import business.servlet.NFBL2060.common.NFBL2060CommonLogic;
import business.servlet.NFBL2060.constant.NFBL2060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/21   Hitachi         T.Tsuchida      Update          QC#12116
 * 2016/09/30   Hitachi         T.Tsuchida      Update          QC#14498
 * 2016/10/03   Hitachi         T.Tsuchida      Update          QC#13414
 * 2016/10/24   Hitachi         Y.Tsuchimoto    Update          QC#15493
 * 2017/11/15   CITS            T.Wada          Update          QC#21688
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 * 2018/03/23   Hitachi         Y.Takeno        Update          QC#22350
 * 2018/05/28   CITS            K.Ogino         Update          QC#25902
 * 2024/02/05   Hitachi         S.Ikariya       Update          QC#63451
 *</pre>
 */
public class NFBL2060_INIT extends S21INITCommonHandler implements NFBL2060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);        
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;

        NFBL2060CMsg bizMsg = new NFBL2060CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;
        NFBL2060CMsg bizMsg  = (NFBL2060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        /** Initialize button control */ 
        NFBL2060CommonLogic.initControl(this, scrnMsg);
        /** Set confirm message when clicking button */ 
        NFBL2060CommonLogic.setButtonConfirmMsg(this);
        /** Initialize tab position */
        // QC#21688
        scrnMsg.xxDplyTab.setValue(TAB_SUMMARY);
        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.dplyVndNm);

    }
    
    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) arg0;
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        // START 2017/12/22 [QC#22831, MOD]
        scrnMsg.xxLinkProt_V1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplier Name Link"));
        scrnMsg.dplyVndNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplier Name"));
        scrnMsg.xxLinkProt_V2.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplier Site Code Link"));
        scrnMsg.apVndCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplier Site Code"));
        scrnMsg.xxLinkProt_V3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplier Number Link"));
        scrnMsg.prntVndCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplier Number"));
        // END   2017/12/22 [QC#22831, MOD]
        // START 2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.poNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "PO Number"));
        // END   2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.delyOrdNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Receipt Number"));
        scrnMsg.poDt_FR.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "PO Date From"));
        scrnMsg.poDt_TO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "PO Date To"));
        scrnMsg.xxLinkProt_V4.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Terms Link"));
        scrnMsg.vndPmtTermDescTxt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Terms"));
        scrnMsg.apVndInvNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice Number"));
        scrnMsg.acctInvStsCd_S.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Status"));
        scrnMsg.invDt_FR.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice Date From"));
        scrnMsg.invDt_TO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice Date To"));
        // START 2024/02/05 S.Ikariya [QC#63451, ADD]
        scrnMsg.acInvTotCostAmt_FR.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice Amount From"));
        scrnMsg.acInvTotCostAmt_TO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Invoice Amount To"));
        scrnMsg.acInvTotCostAmt_FR.setAppFracDigit(2);
        scrnMsg.acInvTotCostAmt_TO.setAppFracDigit(2);
        // END 2024/02/05 S.Ikariya [QC#63451, ADD]
        scrnMsg.xxCmntTxt_FR.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Charge Account From"));
        scrnMsg.xxCmntTxt_TO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Charge Account To"));
        scrnMsg.xxLinkProt_I1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Payment Method Link"));
        // START 2016/07/21 T.Tsuchida [QC#12116,MOD]
        //scrnMsg.dsPmtMethNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Payment Method"));
        scrnMsg.vndPmtMethDescTxt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Payment Method"));
        // END 2016/07/21 T.Tsuchida [QC#12116,MOD]
        scrnMsg.apInvCatgCd_S.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Source"));
        scrnMsg.apPmtChkNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Payment Number"));
        scrnMsg.pmtDt_FR.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Payment Date From"));
        scrnMsg.pmtDt_TO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Payment Date To"));
        scrnMsg.xxChkBox_01.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Holds Check Box"));
        scrnMsg.xxChkBox_02.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "PO Variance Check Box"));
        // START 2018/03/23 [QC#22350, ADD]
        // QC#25902
        scrnMsg.dispPoDtlLineNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Line #"));
        // END   2018/03/23 [QC#22350, ADD]
        scrnMsg.vndRtrnNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Vendor Return #"));
        scrnMsg.vndRtrnSubmtDt_FR.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Vendor Return Date From"));
        scrnMsg.vndRtrnSubmtDt_TO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Vendor Return Date To"));
        // QC#25902 End
        // START 2018/10/18 J.Kim [QC#28816,ADD]
        scrnMsg.invAuthDt_FR.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Linked To ARCS Date From"));
        scrnMsg.invAuthDt_TO.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Linked To ARCS Date To"));
        // END 2018/10/18 J.Kim [QC#18816,ADD]
    }
}
