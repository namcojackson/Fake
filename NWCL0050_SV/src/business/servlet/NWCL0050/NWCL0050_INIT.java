/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0050;

import static business.servlet.NWCL0050.constant.NWCL0050Constant.BIZ_ID;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_DISPLAY;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_EMAIL;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_PAY;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0050.NWCL0050CMsg;
import business.servlet.NWCL0050.common.NWCL0050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/26   Fujitsu         T.Yoshida       Create          N/A
 * 2017/12/28   Hitachi         E.Kameishi      Update          QC#20312
 * 2018/07/11   Fujitsu         M.Ohno          Update          QC#19801
 *</pre>
 */
public class NWCL0050_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0050BMsg scrnMsg = (NWCL0050BMsg) bMsg;

        NWCL0050CMsg bizMsg = new NWCL0050CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");

        // START 2017/12/28 E.Kameishi [QC#20312,ADD]
        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            if (params.length == 1) {
                EZDBStringItem param01 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.invNum, param01);
            }
        }
        // END 2017/12/28 E.Kameishi [QC#20312,ADD]

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0050BMsg scrnMsg = (NWCL0050BMsg) bMsg;
        NWCL0050CMsg bizMsg = (NWCL0050CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWCL0050CommonLogic.initCmnBtnProp(this);
        NWCL0050CommonLogic.setAppFracDigit(scrnMsg);

        if (scrnMsg.A.getValidCount() > 0) {
            if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
                throw new EZDFieldErrorException();
            }

            NWCL0050CommonLogic.setAuthority(this);
            NWCL0050CommonLogic.controlFileLink(scrnMsg);
            NWCL0050CommonLogic.setTblBackColor(scrnMsg);
            NWCL0050CommonLogic.setProtected(scrnMsg);
        } else {
            // 218/07/11 QC#19801 add start
            setButtonEnabled(BTN_DISPLAY, false);
            // 218/07/11 QC#19801 add end
            setButtonEnabled(BTN_PAY, false);
            setButtonEnabled(BTN_EMAIL, false);
        }
        scrnMsg.setFocusItem(scrnMsg.invNum);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWCL0050BMsg scrnMsg = (NWCL0050BMsg) bMsg;
        scrnMsg.invNum.setNameForMessage("Invoice Number");
        scrnMsg.cpoOrdNum.setNameForMessage("Order Number");
        scrnMsg.conslBillNum.setNameForMessage("Bill Number");
        scrnMsg.billToDsAcctNum.setNameForMessage("Account Number");
        scrnMsg.billToDsAcctNm.setNameForMessage("Customer Name");
        scrnMsg.billToLocNum.setNameForMessage("Bill To Location");
        scrnMsg.xxSerNumSrchTxt.setNameForMessage("Serial Number");
        scrnMsg.dsContrNum.setNameForMessage("Contract Number");
        scrnMsg.invAvgGrpNum.setNameForMessage("Aggregate Contract#");
        scrnMsg.xxUrnNum.setNameForMessage("URN Number");
        scrnMsg.conslBillInvDt_FR.setNameForMessage("Bill Date From");
        scrnMsg.conslBillInvDt_TO.setNameForMessage("Bill Date To");
        scrnMsg.xxCratDt_FR.setNameForMessage("Creation Date From");
        scrnMsg.xxCratDt_TO.setNameForMessage("Creation Date To");
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());
    }
}
