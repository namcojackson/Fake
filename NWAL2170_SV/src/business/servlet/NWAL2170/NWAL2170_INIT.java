/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2170;

import static business.servlet.NWAL2170.constant.NWAL2170Constant.ALERT_MSG;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.*;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.ZZSM4300E;

import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2170.NWAL2170CMsg;
import business.servlet.NWAL2170.common.NWAL2170CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NWAL2170_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         M.Yamada        Create          N/A
 * 2017/05/30   Fujitsu         S.Ohki          Update          RS#8233
 * 2017/09/21   Fujitsu         H.Sugawara      Update          QC#19922
 * 2018/04/04   Fujitsu         A.Kosai         Update          QC#10374
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 * 2018/08/03   Fujitsu         A.Honda         Update          QC#26325
 *</pre>
 */
public class NWAL2170_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2170BMsg scrnMsg = (NWAL2170BMsg) bMsg;
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
        scrnMsg.xxPageCd.setValue(XX_PAGE.PAGE_IMPT.getCode());

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

        NWAL2170BMsg scrnMsg = (NWAL2170BMsg) bMsg;
        NWAL2170CMsg bizMsg = new NWAL2170CMsg();

        ZYPEZDItemValueSetter.setValue(scrnMsg.bizId_FR, getSubmitedBusinessAplId(ctx));
        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length >= 1) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem50Txt_P, (EZDBStringItem) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.ordSrcRefNum, scrnMsg.xxScrItem50Txt_P);
            if (params.length > 1) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsImptOrdPk_P, (EZDBBigDecimalItem) params[1]);
            }
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2170BMsg scrnMsg = (NWAL2170BMsg) bMsg;
        NWAL2170CMsg bizMsg = (NWAL2170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // 2018/05/07 QC#22482 Add Start
        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        NWAL2170CommonLogic.setScrnManOvrdDspCtrl(scrnMsg);
        // 2018/05/07 QC#22482 Add End
        NWAL2170CommonLogic.setGuiStyle(scrnMsg);

        NWAL2170CommonLogic.initCmnBtnProp(this, scrnMsg);
        NWAL2170CommonLogic.initBizItemProp(this, scrnMsg);
        if (bizMsg.getMessageKind().equals(MESSAGE_KIND_ERROR)) {
            NWAL2170CommonLogic.protectLineItem(this, scrnMsg.A.no(0));
        }

        setAppFracDigit(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL2170BMsg scrnMsg = (NWAL2170BMsg) bMsg;

        scrnMsg.xxScrItem50Txt.setNameForMessage("Order Number");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.setNameForMessage("Check Box");
            scrnMsg.A.no(i).dsImptSvcMdseCd.setNameForMessage("Service Program Code");
            scrnMsg.A.no(i).mdseDescShortTxt.setNameForMessage("Service Program Name");
            scrnMsg.A.no(i).prcSvcContrTpCd.setNameForMessage("Contract Type");
            scrnMsg.A.no(i).prcSvcPlnTpCd.setNameForMessage("Plan Type");
            scrnMsg.A.no(i).billWithEquipFlg.setNameForMessage("Billed w/ Equip");
            scrnMsg.A.no(i).fromPerMthNum.setNameForMessage("Term from");
            scrnMsg.A.no(i).toPerMthNum.setNameForMessage("Term to");
            scrnMsg.A.no(i).dsContrCatgCd.setNameForMessage("Contract Indicator");
            scrnMsg.A.no(i).baseBllgCycleCd.setNameForMessage("Base Bill Cycle");
            scrnMsg.A.no(i).usgBllgCycleCd.setNameForMessage("Usage Bill Cycle");
            scrnMsg.A.no(i).billByTpCd.setNameForMessage("Billed By");

            scrnMsg.A.no(i).dsAcctNum.setNameForMessage("Customer Number");
            scrnMsg.A.no(i).dsAcctNm.setNameForMessage("Customer Name");
            // Mod Start 2017/09/21 QC#19922
            //scrnMsg.A.no(i).soldToCustLocCd.setNameForMessage("Location Number");
            scrnMsg.A.no(i).soldToCustLocCd.setNameForMessage("Sold To Code");
            // Mod End 2017/09/21 QC#19922

            scrnMsg.B.no(i).xxChkBox_B.setNameForMessage("Check Box");
            scrnMsg.B.no(i).mtrReadMethCd.setNameForMessage("Meter Method");
            scrnMsg.B.no(i).custIssPoNum.setNameForMessage("PO Number");
            scrnMsg.B.no(i).custIssPoDt.setNameForMessage("PO Date");
        }

        // alert message.
        ZYPEZDItemValueSetter.setValue(scrnMsg.exMsgTxt_01, ALERT_MSG);
    }

    private void setAppFracDigit(NWAL2170BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).fromPerMthNum.setAppFracDigit(0);
            scrnMsg.A.no(i).toPerMthNum.setAppFracDigit(0);

            scrnMsg.A.no(i).xxTotAmt_S.setAppFracDigit(2);

            // 2018/04/04 QC#10374 Add Start
            scrnMsg.B.no(i).xxFreqCycleCnt.setAppFracDigit(0);
            scrnMsg.B.no(i).xxDealAmt_TT.setAppFracDigit(2);
            scrnMsg.B.no(i).xxDealAmt_EQ.setAppFracDigit(2);
            // 2018/08/03 QC#26325 Add Start
            scrnMsg.B.no(i).xxDealAmt_RT.setAppFracDigit(2);
            // 2018/08/03 QC#26325 Add End
            scrnMsg.B.no(i).xxDealAmt_AC.setAppFracDigit(2);
            scrnMsg.B.no(i).xxDealAmt_AD.setAppFracDigit(2);
            // 2018/04/04 QC#10374 Add End
            // 2018/04/04 QC#10374 Mod Start
//            scrnMsg.B.no(i).xxTotAmt_LN.setAppFracDigit(2);
//            scrnMsg.B.no(i).xxTotAmt_SV.setAppFracDigit(2);
//            scrnMsg.B.no(i).xxTotAmt_EQ.setAppFracDigit(2);
//            scrnMsg.B.no(i).xxTotAmt_AD.setAppFracDigit(2);
            scrnMsg.B.no(i).xxTermAmt_TT.setAppFracDigit(2);
            scrnMsg.B.no(i).xxTermAmt_EQ.setAppFracDigit(2);
            // 2018/08/03 QC#26325 Add Start
            scrnMsg.B.no(i).xxTermAmt_RT.setAppFracDigit(2);
            // 2018/08/03 QC#26325 Add End
            scrnMsg.B.no(i).xxTermAmt_AC.setAppFracDigit(2);
            scrnMsg.B.no(i).xxTermAmt_AD.setAppFracDigit(2);
            // 2018/04/04 QC#10374 Mod End
        }
    }
}
