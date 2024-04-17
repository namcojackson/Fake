/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320;

import static business.servlet.NSAL1320.constant.NSAL1320Constant.*;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1320.NSAL1320CMsg;
import business.servlet.NSAL1320.common.NSAL1320CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NSAL1320_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 * 2017/08/15   Hitachi         Y.Takeno        Update          QC#20378-2
 * 2018/04/16   Fujitsu         A.Kosai         Update          QC#20162
 * 2018/04/16   Fujitsu         A.Kosai         Update          QC#10374
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 * 2018/08/03   Fujitsu         W.Honda         Update          QC#26325
 * 2018/11/15   Hitachi         K.Kim           Update          QC#28638
 *</pre>
 */
public class NSAL1320_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
        String beforeBizId = getSubmitedBusinessAplId(ctx);
        if (BIZ_ID_IMPT_FORM.equals(beforeBizId)) {
            scrnMsg.xxPageCd.setValue(XX_PAGE.PAGE_IMPT.getCode());
        } else {
            scrnMsg.xxPageCd.setValue(XX_PAGE.PAGE_SHELL.getCode());
        }

        S21UserProfileService userProfileService = getUserProfileService();
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);

        int ix = 0;
        for (String functionId : functionIds) {
            scrnMsg.P.no(ix++).xxFuncId_P.setValue(functionId);
        }

        if (ix == 0) {
            throw new S21AbendException(ZZSM4300E, new String[] {userProfileService.getContextUserInfo().getUserId() });
        }
        scrnMsg.P.setValidCount(ix);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        NSAL1320CMsg bizMsg = new NSAL1320CMsg();

        ZYPEZDItemValueSetter.setValue(scrnMsg.bizId_FR, getSubmitedBusinessAplId(ctx));
        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length >= 1) {
            //            ZYPEZDItemValueSetter.setValue(scrnMsg.refCpoOrdNum_P, (EZDBStringItem) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem50Txt_P, (EZDBStringItem) params[0]);
            if (XX_PAGE.PAGE_SHELL.getCode().equals(scrnMsg.xxPageCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.refCpoOrdNum, scrnMsg.xxScrItem50Txt_P);
//            } else {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.ordSrcRefNum, scrnMsg.xxScrItem50Txt_P);
//                if (params.length > 1) {
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsImptOrdPk_P, (EZDBBigDecimalItem) params[1]);
//                }
            }
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        NSAL1320CMsg bizMsg = (NSAL1320CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // 2018/05/07 QC#22482 Add Start
        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        NSAL1320CommonLogic.setScrnManOvrdDspCtrl(scrnMsg);
        // 2018/05/07 QC#22482 Add End
        NSAL1320CommonLogic.setGuiStyle(scrnMsg);

        NSAL1320CommonLogic.initCmnBtnProp(this, scrnMsg);
        NSAL1320CommonLogic.initBizItemProp(this, scrnMsg);
        if (bizMsg.getMessageKind().equals(MESSAGE_KIND_ERROR)) {
            NSAL1320CommonLogic.protectLineItem(this, scrnMsg.A.no(0));
        }

        setAppFracDigit(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;

        scrnMsg.xxScrItem50Txt.setNameForMessage("Order Number");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.setNameForMessage("Check Box");
            // 2018/05/07 QC#22482 Add Start
            scrnMsg.A.no(i).manContrOvrdFlg.setNameForMessage("Manual Override");
            scrnMsg.A.no(i).svcMemoRsnDescTxt.setNameForMessage("Manual Override Reason");
            scrnMsg.A.no(i).svcCmntTxt.setNameForMessage("Manual Override Comments");
            // 2018/05/07 QC#22482 Add End
            scrnMsg.A.no(i).svcPgmMdseCd.setNameForMessage("Service Program Code");
            scrnMsg.A.no(i).mdseDescShortTxt.setNameForMessage("Service Program Name");
            scrnMsg.A.no(i).prcSvcContrTpCd.setNameForMessage("Contract Type");
            scrnMsg.A.no(i).prcSvcPlnTpCd.setNameForMessage("Plan Type");
            scrnMsg.A.no(i).billWithEquipFlg.setNameForMessage("Billed w/ Equip");
            scrnMsg.A.no(i).fromPerMthNum.setNameForMessage("Term from");
            scrnMsg.A.no(i).toPerMthNum.setNameForMessage("Term to");
            scrnMsg.A.no(i).dsContrCatgCd.setNameForMessage("Contract Indicator");
            scrnMsg.A.no(i).baseBllgCycleCd.setNameForMessage("Base Bill Cycle");
            scrnMsg.A.no(i).usgBllgCycleCd.setNameForMessage("Usage Bill Cycle");
            // START 2018/11/15 [QC#28638, ADD]
            scrnMsg.A.no(i).fixTermInMthAot.setNameForMessage("Fixed Months");
            // END 2018/11/15 [QC#28638, ADD]
            scrnMsg.A.no(i).billByTpCd.setNameForMessage("Billed By");

            scrnMsg.A.no(i).dsAcctNum.setNameForMessage("Customer Number");
            scrnMsg.A.no(i).dsAcctNm.setNameForMessage("Customer Name");
            scrnMsg.A.no(i).soldToCustLocCd.setNameForMessage("Location Number");

            scrnMsg.B.no(i).xxChkBox_B.setNameForMessage("Check Box");
            scrnMsg.B.no(i).mtrReadMethCd.setNameForMessage("Meter Method");
            scrnMsg.B.no(i).custIssPoNum.setNameForMessage("PO Number");
            scrnMsg.B.no(i).custIssPoDt.setNameForMessage("PO Date");
            // 2018/04/16 QC#20162 Add Start
            scrnMsg.B.no(i).dsPoExprDt.setNameForMessage("PO Expr Date");
            // 2018/04/16 QC#20162 Add End
        }

        // alert message.
        ZYPEZDItemValueSetter.setValue(scrnMsg.exMsgTxt_01, ALERT_MSG);
        // START 2017/08/15 [QC#20378, ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.exMsgTxt_02, ALERT_MSG_02);
        ZYPEZDItemValueSetter.setValue(scrnMsg.exMsgTxt_03, ALERT_MSG_03);
        ZYPEZDItemValueSetter.setValue(scrnMsg.exMsgTxt_04, ALERT_MSG_04);
        // END   2017/08/15 [QC#20378, ADD]
    }

    private void setAppFracDigit(NSAL1320BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).fromPerMthNum.setAppFracDigit(0);
            scrnMsg.A.no(i).toPerMthNum.setAppFracDigit(0);

            scrnMsg.A.no(i).xxTotAmt_S.setAppFracDigit(2);

            // 2018/04/16 QC#10374 Mod Start
//            scrnMsg.B.no(i).xxTotAmt_LN.setAppFracDigit(2);
//            scrnMsg.B.no(i).xxTotAmt_SV.setAppFracDigit(2);
//            scrnMsg.B.no(i).xxTotAmt_EQ.setAppFracDigit(2);
//            scrnMsg.B.no(i).xxTotAmt_AD.setAppFracDigit(2);
            scrnMsg.B.no(i).xxDealAmt_TT.setAppFracDigit(2);
            scrnMsg.B.no(i).xxDealAmt_EQ.setAppFracDigit(2);
            scrnMsg.B.no(i).xxDealAmt_RT.setAppFracDigit(2);// 2018/08/03 QC#26325 Add
            scrnMsg.B.no(i).xxDealAmt_AC.setAppFracDigit(2);
            scrnMsg.B.no(i).xxDealAmt_AD.setAppFracDigit(2);
            scrnMsg.B.no(i).xxTermAmt_TT.setAppFracDigit(2);
            scrnMsg.B.no(i).xxTermAmt_EQ.setAppFracDigit(2);
            scrnMsg.B.no(i).xxTermAmt_RT.setAppFracDigit(2);// 2018/08/03 QC#26325 Add
            scrnMsg.B.no(i).xxTermAmt_AC.setAppFracDigit(2);
            scrnMsg.B.no(i).xxTermAmt_AD.setAppFracDigit(2);
            // 2018/04/16 QC#10374 Mod End
        }
    }
}
