/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import static business.servlet.NMAL6720.constant.NMAL6720Constant.BUSINESS_ID;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.MSG_ACCT_NUM;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.MSG_LOC_NUM;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.SCRN_EVENT_EDIT;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.SCRN_EVENT_NEW;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.ZZZM9025E;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6720.NMAL6720CMsg;
import business.servlet.NMAL6720.common.NMAL6720CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/06   CUSA            Fujitsu         Create          N/A
 * 2015/10/15   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/26   Fujitsu         C.Tanaka        Update          QC#4349
 * 2016/04/14   SRAA            Y.Chen          Update          QC#5749
 * 2016/05/03   SRAA            Y.Chen          Update          QC#5575
 * 2016/10/20   Fujitsu         C.Yokoi         Create          QC#12060
 * 2018/02/19   Fujitsu         M.Ohno          Update          QC#20297(Sol#379)
 * 2018/12/13   Fujitsu         M.Ishii         Update          QC#29315
 * 2020/03/24   Fujitsu         M.Ohno          Update          QC#55673
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NMAL6720_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        // Set default screen event.
        scrnMsg.xxScrEventNm.setValue(SCRN_EVENT_EDIT);
        scrnMsg.dsAcctNum_P1.clear();
        scrnMsg.locNum_P1.clear();

        if (getArgForSubScreen() instanceof Object[]) {
            Object[] param = (Object[]) getArgForSubScreen();

            String str;
            for (int i = 0; i < param.length; i++) {
                Object o = param[i];
                if (o instanceof EZDBStringItem) {
                    o = ((EZDBStringItem) o).getValue();
                } else if (o instanceof EZDBBigDecimalItem) {
                    o = ((EZDBBigDecimalItem) o).getValue();
                }
                str = o.toString();

                if (i == 0) {
                    if (SCRN_EVENT_NEW.equals(str)) {
                        scrnMsg.xxScrEventNm.setValue(SCRN_EVENT_NEW);
                    } else {
                        scrnMsg.xxScrEventNm.setValue(SCRN_EVENT_EDIT);
                    }
                } else if (i == 1) {
                    scrnMsg.dsAcctNum_P1.setValue(str);
                } else if (i == 2) {
                    if (SCRN_EVENT_EDIT.equals(scrnMsg.xxScrEventNm.getValue())) {
                        scrnMsg.locNum_P1.setValue(str);
                    }
                // 2016/10/20 CSA-QC#12060 Add Start
                } else if (i == 3) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrId, str);
                } else if (i == 4) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxValUpdFlg, str);
                // 2016/10/20 CSA-QC#12060 Add End
                }
            }

            if (SCRN_EVENT_EDIT.equals(scrnMsg.xxScrEventNm.getValue()) && !ZYPCommonFunc.hasValue(scrnMsg.locNum_P1)) {
                scrnMsg.locNum_P1.setErrorInfo(1, ZZZM9025E, new String[] {MSG_LOC_NUM });
            } else if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_P1)) {
                scrnMsg.locNum_P1.setErrorInfo(1, ZZZM9025E, new String[] {MSG_ACCT_NUM });
            }
            scrnMsg.addCheckItem(scrnMsg.dsAcctNum_P1);
            scrnMsg.addCheckItem(scrnMsg.locNum_P1);
            scrnMsg.putErrorScreen();

            if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
                throw new EZDFieldErrorException();
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        scrnMsg.dsAcctNum_H1.setValue(scrnMsg.dsAcctNum_P1.getValue());
        scrnMsg.locNum_H1.setValue(scrnMsg.locNum_P1.getValue());

        NMAL6720CMsg bizMsg = new NMAL6720CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        NMAL6720CMsg bizMsg = (NMAL6720CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6720CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
        scrnMsg.setFocusItem(scrnMsg.ctryCd_P1);
        NMAL6720CommonLogic.setDplModDt(scrnMsg);
        // QC#5575
        setAppFracDigit(scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        // Header
        scrnMsg.ctryCd_P1.setNameForMessage("Country");
        scrnMsg.firstLineAddr_H1.setNameForMessage("Address 1");
        scrnMsg.scdLineAddr_H1.setNameForMessage("Address 2");
        scrnMsg.thirdLineAddr_H1.setNameForMessage("Address 3");
        scrnMsg.frthLineAddr_H1.setNameForMessage("Address 4");

        scrnMsg.ctyAddr_H1.setNameForMessage("City");
        scrnMsg.stCd_P1.setNameForMessage("State");
        scrnMsg.postCd_H1.setNameForMessage("Postal Code");
        scrnMsg.cntyNm_H1.setNameForMessage("County");
        scrnMsg.provNm_H1.setNameForMessage("Province");

        scrnMsg.dsLocNm_H1.setNameForMessage("Location Name");
        // 2020/03/23 QC#55673 Add Start
        scrnMsg.addlLocNm.setNameForMessage("Additional Name");
        // 2020/03/23 QC#55673 Add End
        scrnMsg.telNum_H1.setNameForMessage("Main Office Phone#");
        scrnMsg.prinCustFlg_PR.setNameForMessage("Primary Address");
        scrnMsg.actvFlg_H1.setNameForMessage("Active");
        scrnMsg.lineBizTpCd_P1.setNameForMessage("Source Business Unit");
        // 2023/11/06 QC#61924 Add Start
        scrnMsg.xxChkBox_H1.setNameForMessage("Deactivate for new Trx");
        // 2023/11/06 QC#61924 Add End

        scrnMsg.dplStsCd_P1.setNameForMessage("DPL Screening Status");
        scrnMsg.embgoFlg_H1.setNameForMessage("Embargo");
        scrnMsg.dplModDtTmTs_H1.setNameForMessage("Last Screened");
        scrnMsg.xxDtTxt_H1.setNameForMessage("Last Screened");
        scrnMsg.dplRsnTxt_H1.setNameForMessage("DPL Screening Remarks");

        // Account
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage("Check Box");
            scrnMsg.A.no(i).dsAcctNum_A1.setNameForMessage("Related Acct#");
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage("Start Date");
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage("End Date");
        }

        // Classification
        scrnMsg.glnNum_H1.setNameForMessage("Global Location#");
        scrnMsg.dunsNum_H1.setNameForMessage("DUNS#");
        scrnMsg.dsUltDunsNum_H1.setNameForMessage("Ultimate DUNS#");
        scrnMsg.hqDunsNum_H1.setNameForMessage("HQ DUNS#");
        scrnMsg.dsPrntDunsNum_H1.setNameForMessage("Parent DUNS#");
        scrnMsg.dsLocEmpNum_H1.setNameForMessage("# of Employee");
        scrnMsg.dsLocRevAmt_H1.setNameForMessage("Annual Revenue");
        scrnMsg.dsCustSicCd_H1.setNameForMessage("SIC Code");
        scrnMsg.dsCustSicDescTxt_H1.setNameForMessage("Industry");
        scrnMsg.dsLastUpdDunsDt_H1.setNameForMessage("Last DUNS Update Date");
        // QC#5749
        scrnMsg.dunsTradeStyleNm_H1.setNameForMessage("DNB Trade Style Name");
        scrnMsg.dunsActvCd_H1.setNameForMessage("DNB BEMFAB Code");
        scrnMsg.dunsLineAddr_H1.setNameForMessage("DNB Address");
        scrnMsg.dunsBizNm_H1.setNameForMessage("DNB Business Name");
        scrnMsg.dsLastRcvDunsTxt_H1.setNameForMessage("DNB Identifier");
        scrnMsg.dsInsdCtyLimitFlg_H1.setNameForMessage("Inside City Limit");
        scrnMsg.geoCd_H1.setNameForMessage("GEO Override");

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).xxChkBox_B1.setNameForMessage("Check Box");
            scrnMsg.B.no(i).dsXrefAcctCd_B1.setNameForMessage("Cross Reference#");
            scrnMsg.B.no(i).dsXrefAcctNm_B1.setNameForMessage("Account Name");
            scrnMsg.B.no(i).dsXrefAcctTpCd_P1.setNameForMessage("Cross Reference Type");
        }

        // Contacts
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).dsPrimLocFlg_C1.setNameForMessage("Primary");
        }

        // Transaction
        for (int i = 0; i < scrnMsg.D.length(); i++) {
            scrnMsg.D.no(i).xxChkBox_D1.setNameForMessage("Check Box");
            scrnMsg.D.no(i).dsTrxRuleTpCd_P1.setNameForMessage("Transaction Type");
            scrnMsg.D.no(i).dsPoReqFlg_D1.setNameForMessage("PO Required");
            scrnMsg.D.no(i).dsBlktPoNum_D1.setNameForMessage("Blanket PO#");
            scrnMsg.D.no(i).dsPoExprDt_D1.setNameForMessage("PO Exp Date");
            scrnMsg.D.no(i).dsDefBillToCd_D1.setNameForMessage("Default Bill To");
            scrnMsg.D.no(i).dsDefShipToCd_D1.setNameForMessage("Default Ship To");
            scrnMsg.D.no(i).dsCrCardReqFlg_D1.setNameForMessage("Credit Card Required");
            scrnMsg.D.no(i).dsOvngtAllwFlg_D1.setNameForMessage("Overnight Allowed");
        }

        // Service Attribute
        scrnMsg.prfTechCd_SA.setNameForMessage("Primary Technician");
        scrnMsg.reqTechCd_SA.setNameForMessage("Requested Technician");

        for (int i = 0; i < scrnMsg.F.length(); i++) {
            scrnMsg.F.no(i).xxChkBox_F1.setNameForMessage("Check Box");
            scrnMsg.F.no(i).effFromDt_F1.setNameForMessage("Start Date");
            scrnMsg.F.no(i).effToDt_F1.setNameForMessage("End Date");
            scrnMsg.F.no(i).svcAccsPmitNum_F1.setNameForMessage("Access Permit Description");
        }

        for (int i = 0; i < scrnMsg.G.length(); i++) {
            scrnMsg.G.no(i).xxChkBox_G1.setNameForMessage("Check Box");
            scrnMsg.G.no(i).effThruDt_G1.setNameForMessage("End Date");
            scrnMsg.G.no(i).nonPrfTechCd_G1.setNameForMessage("Employee ID");
        }

        // Instructions
        for (int i = 0; i < scrnMsg.E.length(); i++) {
            scrnMsg.E.no(i).dsCustMsgTxt_E1.setNameForMessage("Message Body");
            scrnMsg.E.no(i).dsBizAreaCd_P1.setNameForMessage("Business Area");
            scrnMsg.E.no(i).lineBizTpCd_P2.setNameForMessage("Line of Business");
            scrnMsg.E.no(i).dsCustMsgTpCd_P1.setNameForMessage("Type");
            scrnMsg.E.no(i).dsPrintOnInvFlg_E1.setNameForMessage("Print on Invoice");
            scrnMsg.E.no(i).effThruDt_E1.setNameForMessage("End Date");
            scrnMsg.E.no(i).xxChkBox_E1.setNameForMessage("Check Box");
        }

        // 2018/02/19 QC#20297(Sol#379) add start
        // Shipping
        for (int i = 0; i < scrnMsg.I.length(); i++) {
            scrnMsg.I.no(i).dsBizAreaCd_P2.setNameForMessage("Business Area");
            scrnMsg.I.no(i).lineBizTpCd_P3.setNameForMessage("Line of Business");
            scrnMsg.I.no(i).frtCondCd_P1.setNameForMessage("Freight Term");
            scrnMsg.I.no(i).shpgSvcLvlCd_P1.setNameForMessage("Service Levely");
            scrnMsg.I.no(i).effThruDt_I1.setNameForMessage("End Date");
            scrnMsg.I.no(i).xxChkBox_I1.setNameForMessage("Check Box");
            // 2018/12/13 QC#29315 Add Start
            scrnMsg.I.no(i).xxChkBox_ID.setNameForMessage("Default");
            scrnMsg.I.no(i).vndCd_I3.setNameForMessage("Carrier");
            scrnMsg.I.no(i).dsCarrAcctNum_I1.setNameForMessage("Account Number");
            scrnMsg.I.no(i).effFromDt_I1.setNameForMessage("Start Date");
            // 2018/12/13 QC#29315 Add End
        }
        // 2018/02/19 QC#20297(Sol#379) add end
    }

    private void setAppFracDigit(NMAL6720BMsg scrnMsg) {
        scrnMsg.dsLocRevAmt_H1.setAppFracDigit(2);
    }
}
