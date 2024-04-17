/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import static business.servlet.NMAL6720.constant.NMAL6720Constant.BUSINESS_ID;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_CD_UPD;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.HYPHEN;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.MAX_DT;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.MESSAGE_KIND_ERR;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.MESSAGE_KIND_WARN;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.MSG_BILL_TO_CHECK;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.MSG_SHIP_TO_CHECK;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.MSG_START_DT;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.MSG_TRX_TP;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.NMAM0072E;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.NMAM0185E;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.NMAM0303E;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.NMAM0803E;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.NMAM0849E;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.NMAM8113E;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.NMAM8115E;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.NMAM8348E;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.NZZM0002I;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.PERIOD;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.SLASH;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.TAB_ACCOUNT;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.TAB_SRVATTRB;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.TAB_TRANSACTIONS;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.PROS_TO_CUST_CHNG_STS_PENDING;

import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6720.NMAL6720CMsg;
import business.servlet.NMAL6720.common.NMAL6720CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/06   CUSA            Fujitsu         Create          N/A
 * 2015/10/15   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/25   Fujitsu         C.Tanaka        Update          QC#2756
 * 2016/02/26   Fujitsu         C.Tanaka        Update          QC#4349
 * 2016/02/28   SRAA            Y.Chen          Update          QC#2674
 * 2016/07/05   Fujitsu         C.Tanaka        Update          QC#10677
 * 2016/07/19   Fujitsu         R.Nakamura      Update          QC#11729
 * 2016/08/22   Fujitsu         N.Sugiura       Update          QC#11729
 * 2016/09/02   Fujitsu         N.Sugiura       Update          QC#13486
 * 2016/09/21   SRAA            Y.Chen          Update          QC#12060
 * 2016/09/30   Fujitsu         C.Yokoi         Update          QC#14720
 * 2017/10/10   Fujitsu         M.Ohno          Update          QC#20355
 * 2017/11/27   Fujitsu         A.Kosai         Update          QC#20828
 * 2018/02/19   Fujitsu         M.Ohno          Update          QC#20297(Sol#379)
 * 2020/07/22   CITS            K.Ogino         Create          QC#57316
 *</pre>
 */
public class NMAL6720Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        errCheck(scrnMsg);

        // if has error and change tab in errCheck method, it needs to be call setBgColor.
        NMAL6720CommonLogic.setBgColor(scrnMsg);
        NMAL6720CommonLogic.addCheckItem(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        NMAL6720CMsg bizMsg = new NMAL6720CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        NMAL6720CMsg bizMsg = (NMAL6720CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL6720CommonLogic.setBgColor(scrnMsg);

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERR)) {
            // QC#57316 Add Start
            NMAL6720CommonLogic.addCheckItem(scrnMsg);
            scrnMsg.putErrorScreen();
            // QC#57316 Add End
            throw new EZDFieldErrorException();
        }

        NMAL6720CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }

        NMAL6720CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());

        // QC#2674
        if (!scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_WARN)) {
            if (TAB_ACCOUNT.equals(scrnMsg.xxDplyTab.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.ctryCd_P1);
            }
        }
        NMAL6720CommonLogic.setDplModDt(scrnMsg);
    }

    private void errCheck(NMAL6720BMsg scrnMsg) {

        String salesDt = ZYPDateUtil.getSalesDate();

        checkTelFormat(scrnMsg.telNum_H1);

        if (DS_ACCT_TP.CUSTOMER.equals(scrnMsg.dsAcctTpCd_H1.getValue())
                || PROS_TO_CUST_CHNG_STS_PENDING.equals(scrnMsg.prosToCustChngStsCd_H1.getValue())) {

            // Ship To Check
            EZDBDateItem fromDateItem = scrnMsg.effFromDt_SH;
            EZDBDateItem thruDateItem = scrnMsg.effThruDt_SH;
            String fromDt = fromDateItem.getValue();
            String thruDt = thruDateItem.getValue();
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.dsAcctRelnShipToFlg_SH.getValue())) {

                if (fromDt.isEmpty()) {
                    fromDt = MAX_DT;
                }
                if (thruDt.isEmpty()) {
                    thruDt = MAX_DT;
                }

                if (!ZYPCommonFunc.hasValue(fromDateItem)) {
                    fromDateItem.setErrorInfo(1, NMAM0849E, new String[] {MSG_SHIP_TO_CHECK, MSG_START_DT });
                }

                // Add Start 2016/07/19 QC#11729
                if (!ZYPCommonFunc.hasValue(scrnMsg.shipToCustPk_SH) && ZYPDateUtil.compare(salesDt, thruDt) > 0) {
                    thruDateItem.setErrorInfo(1, NMAM0803E);
                }
                // Add End 2016/07/19 QC#11729

                if (!ZYPCommonFunc.hasValue(scrnMsg.shipToCustPk_SH) && 0 < ZYPDateUtil.compare(salesDt, fromDt)) {
                    fromDateItem.setErrorInfo(1, NMAM0185E);
                }

                if (0 < ZYPDateUtil.compare(fromDt, thruDt)) {
                    fromDateItem.setErrorInfo(1, NMAM0803E);
                    thruDateItem.setErrorInfo(1, NMAM0803E);
                }

            } else {

                // Add Start 2016/07/19 QC#11729
                if (!ZYPCommonFunc.hasValue(fromDateItem) && ZYPCommonFunc.hasValue(scrnMsg.shipToCustPk_SH)) {
                    fromDateItem.setErrorInfo(1, NMAM0849E, new String[] {MSG_SHIP_TO_CHECK, MSG_START_DT });
                }

            }

            // Bill To Check
            fromDateItem = scrnMsg.effFromDt_BI;
            thruDateItem = scrnMsg.effThruDt_BI;
            fromDt = fromDateItem.getValue();
            thruDt = thruDateItem.getValue();
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.dsAcctRelnBillToFlg_BI.getValue())) {

                if (fromDt.isEmpty()) {
                    fromDt = MAX_DT;
                }
                if (thruDt.isEmpty()) {
                    thruDt = MAX_DT;
                }

                if (!ZYPCommonFunc.hasValue(fromDateItem)) {
                    fromDateItem.setErrorInfo(1, NMAM0849E, new String[] {MSG_BILL_TO_CHECK, MSG_START_DT });
                }

                // Add Start 2016/07/19 QC#11729
                if (!ZYPCommonFunc.hasValue(scrnMsg.billToCustPk_BI) && ZYPDateUtil.compare(salesDt, thruDt) > 0) {
                    thruDateItem.setErrorInfo(1, NMAM0803E);
                }
                // Add End 2016/07/19 QC#11729

                if (!ZYPCommonFunc.hasValue(scrnMsg.billToCustPk_BI) && 0 < ZYPDateUtil.compare(salesDt, fromDt)) {
                    fromDateItem.setErrorInfo(1, NMAM0185E);
                }

                if (0 < ZYPDateUtil.compare(fromDt, thruDt)) {
                    fromDateItem.setErrorInfo(1, NMAM0803E);
                    thruDateItem.setErrorInfo(1, NMAM0803E);
                }
            } else {

                // Add Start 2016/07/19 QC#11729
                if (!ZYPCommonFunc.hasValue(fromDateItem) && ZYPCommonFunc.hasValue(scrnMsg.billToCustPk_BI)) {
                    fromDateItem.setErrorInfo(1, NMAM0849E, new String[] {MSG_BILL_TO_CHECK, MSG_START_DT });
                }

            }

            // Account Tab
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                // 2017/11/27 QC#20828 Add Start
                if (DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue()) &&
                        DS_ACCT_TP.PROSPECT.equals(scrnMsg.A.no(i).dsAcctTpCd_A1.getValue())) {
                    break;
                }
                // 2017/11/27 QC#20828 Add End

                String fromDt1 = scrnMsg.A.no(i).effFromDt_A1.getValue();
                String thruDt1 = scrnMsg.A.no(i).effThruDt_A1.getValue();
                if (fromDt1.isEmpty()) {
                    fromDt1 = MAX_DT;
                }
                if (thruDt1.isEmpty()) {
                    thruDt1 = MAX_DT;
                }

                // 2017/10/10 QC#20355 Mod Start
                //if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).acctLocPk_A1) && 0 < ZYPDateUtil.compare(salesDt, fromDt1)) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).acctLocPk_A1) && 0 < ZYPDateUtil.compare(salesDt, fromDt1) && !ZYPCommonFunc.hasValue(scrnMsg.prosToCustChngStsCd_H1)) {
                    scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM0185E);
                }
                // 2017/10/10 QC#20355 Mod End
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).rgtnStsCd_A1) && !RGTN_STS.PENDING_COMPLETION.equals(scrnMsg.A.no(i).rgtnStsCd_A1.getValue())) {
                    if (0 > ZYPDateUtil.compare(salesDt, fromDt1)) {
                        scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM0303E);
                    }
                }

                // Date Check
                if (ZYPDateUtil.compare(fromDt1, thruDt1) > 0) {
                    scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM8115E);
                    scrnMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAM8115E);
                }

                for (int j = 0; j < scrnMsg.A.getValidCount(); j++) {
                    if (i == j) {
                        continue;
                    }
                    // 2017/11/27 QC#20828 Add Start
                    if (DS_ACCT_TP.PROSPECT.equals(scrnMsg.dsAcctTpCd_H1.getValue()) &&
                            DS_ACCT_TP.PROSPECT.equals(scrnMsg.A.no(j).dsAcctTpCd_A1.getValue())) {
                        break;
                    }
                    // 2017/11/27 QC#20828 Add End

                    String fromDt2 = scrnMsg.A.no(j).effFromDt_A1.getValue();
                    String thruDt2 = scrnMsg.A.no(j).effThruDt_A1.getValue();
                    if (fromDt2.isEmpty()) {
                        fromDt2 = MAX_DT;
                    }
                    if (thruDt2.isEmpty()) {
                        thruDt2 = MAX_DT;
                    }
                    if (i != j && 0 <= ZYPDateUtil.compare(fromDt1, fromDt2) && 0 >= ZYPDateUtil.compare(fromDt1, thruDt2)) {
                        scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM8113E);
                        scrnMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAM8113E);
                        scrnMsg.A.no(j).effFromDt_A1.setErrorInfo(1, NMAM8113E);
                        scrnMsg.A.no(j).effThruDt_A1.setErrorInfo(1, NMAM8113E);
                        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_ACCOUNT);
                        return;
                    } else if (i != j && 0 <= ZYPDateUtil.compare(thruDt1, fromDt2) && 0 >= ZYPDateUtil.compare(thruDt1, thruDt2)) {
                        scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM8113E);
                        scrnMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAM8113E);
                        scrnMsg.A.no(j).effFromDt_A1.setErrorInfo(1, NMAM8113E);
                        scrnMsg.A.no(j).effThruDt_A1.setErrorInfo(1, NMAM8113E);
                        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_ACCOUNT);
                        return;
                    }
                }
            }
        }

        int len = scrnMsg.D.getValidCount();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i != j && scrnMsg.D.no(i).dsTrxRuleTpCd_P1.getValue().equals(scrnMsg.D.no(j).dsTrxRuleTpCd_P1.getValue())) {
                    scrnMsg.D.no(i).dsTrxRuleTpCd_P1.setErrorInfo(1, NMAM0072E, new String[] {MSG_TRX_TP });
                    scrnMsg.D.no(j).dsTrxRuleTpCd_P1.setErrorInfo(1, NMAM0072E, new String[] {MSG_TRX_TP });
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_TRANSACTIONS);
                    return;
                }
            }
        }

        // Service Attrb Tab
        for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
            String fromDt1 = scrnMsg.F.no(i).effFromDt_F1.getValue();
            String thruDt1 = scrnMsg.F.no(i).effToDt_F1.getValue();
            if (fromDt1.isEmpty()) {
                fromDt1 = MAX_DT;
            }
            if (thruDt1.isEmpty()) {
                thruDt1 = MAX_DT;
            }

            // Date Check
            if (ZYPDateUtil.compare(fromDt1, thruDt1) > 0) {
                scrnMsg.F.no(i).effFromDt_F1.setErrorInfo(1, NMAM8115E);
                scrnMsg.F.no(i).effToDt_F1.setErrorInfo(1, NMAM8115E);
            }

            for (int j = 0; j < scrnMsg.F.getValidCount(); j++) {
                if (i == j) {
                    continue;
                }

                if (!scrnMsg.F.no(i).svcAccsPmitNum_F1.getValue().equals(scrnMsg.F.no(j).svcAccsPmitNum_F1.getValue())) {
                    continue;
                }

                String fromDt2 = scrnMsg.F.no(j).effFromDt_F1.getValue();
                String thruDt2 = scrnMsg.F.no(j).effToDt_F1.getValue();
                if (fromDt2.isEmpty()) {
                    fromDt2 = MAX_DT;
                }
                if (thruDt2.isEmpty()) {
                    thruDt2 = MAX_DT;
                }
                if (i != j && 0 <= ZYPDateUtil.compare(fromDt1, fromDt2) && 0 >= ZYPDateUtil.compare(fromDt1, thruDt2)) {
                    scrnMsg.F.no(i).effFromDt_F1.setErrorInfo(1, NMAM8113E);
                    scrnMsg.F.no(i).effToDt_F1.setErrorInfo(1, NMAM8113E);
                    scrnMsg.F.no(j).effFromDt_F1.setErrorInfo(1, NMAM8113E);
                    scrnMsg.F.no(j).effToDt_F1.setErrorInfo(1, NMAM8113E);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_SRVATTRB);
                    return;
                } else if (i != j && 0 <= ZYPDateUtil.compare(thruDt1, fromDt2) && 0 >= ZYPDateUtil.compare(thruDt1, thruDt2)) {
                    scrnMsg.F.no(i).effFromDt_F1.setErrorInfo(1, NMAM8113E);
                    scrnMsg.F.no(i).effToDt_F1.setErrorInfo(1, NMAM8113E);
                    scrnMsg.F.no(j).effFromDt_F1.setErrorInfo(1, NMAM8113E);
                    scrnMsg.F.no(j).effToDt_F1.setErrorInfo(1, NMAM8113E);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_SRVATTRB);
                    return;
                }
            }
        }

        // Instructions Tab
        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            if (DS_BIZ_AREA.PARTS.equals(scrnMsg.E.no(i).dsBizAreaCd_P1.getValue())) {
                if (!DS_CUST_MSG_TP.SHIP.equals(scrnMsg.E.no(i).dsCustMsgTpCd_P1.getValue())) {
                    
                }
            }
        }
    }

    private void checkTelFormat(EZDBStringItem item) {

        if (ZYPCommonFunc.hasValue(item)) {
            String telNum = item.getValue();
            if (telNum.length() < 7 || telNum.length() > 20) {
                item.setErrorInfo(1, NMAM8348E);
            } else {
                telNum = telNum.replaceAll(SLASH, HYPHEN);
                telNum = telNum.replaceAll(PERIOD, HYPHEN);
                ZYPEZDItemValueSetter.setValue(item, telNum);
            }
        }
    }

    // 2016/09/02 QC#13486 Add Start
    private void setAcctRelnShipToFlg(NMAL6720BMsg scrnMsg) {
        String slsDate = ZYPDateUtil.getSalesDate();
        String thruDate = MAX_DT;
        String fromDate = "";

        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt_BI)) {
            fromDate = scrnMsg.effFromDt_BI.getValue();
            if (ZYPCommonFunc.hasValue(scrnMsg.effThruDt_BI)) {
                thruDate = scrnMsg.effThruDt_BI.getValue();
            }
            if (ZYPDateUtil.compare(slsDate, fromDate) >= 0 && ZYPDateUtil.compare(slsDate, thruDate) <= 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctRelnBillToFlg_BI, ZYPConstant.CHKBOX_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctRelnBillToFlg_BI, "");
            }
        }

        thruDate = MAX_DT;
        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt_SH)) {
            fromDate = scrnMsg.effFromDt_SH.getValue();
            if (ZYPCommonFunc.hasValue(scrnMsg.effThruDt_SH)) {
                thruDate = scrnMsg.effThruDt_SH.getValue();
            }
            if (ZYPDateUtil.compare(slsDate, fromDate) >= 0 && ZYPDateUtil.compare(slsDate, thruDate) <= 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctRelnShipToFlg_SH, ZYPConstant.CHKBOX_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctRelnShipToFlg_SH, "");
            }
        }
    }
    // 2016/09/02 QC#13486 Add End
}
