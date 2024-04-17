/*
 * <pre>Copyright (c) 2024 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2020;

import static business.servlet.NWAL2020.constant.NWAL2020Constant.BIZ_ID;
import static business.servlet.NWAL2020.constant.NWAL2020Constant.LINK_STRING_ACCT_NUM;
import static business.servlet.NWAL2020.constant.NWAL2020Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL2020.constant.NWAL2020Constant.NWZM0971E;
import static business.servlet.NWAL2020.constant.NWAL2020Constant.NZZM0000E;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2020.NWAL2020CMsg;
import business.servlet.NWAL2020.common.NWAL2020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL2020_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/04/10   CITS            M.Kobayashi     Create          QC#63757
 *</pre>
 */
public class NWAL2020_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
         checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2020BMsg scrnMsg = (NWAL2020BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        ZYPEZDItemValueSetter.setValue(scrnMsg.acctCd_LK, LINK_STRING_ACCT_NUM);
        if (params != null) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.sellToCustCd, (EZDBStringItem) params[0]);
        }
        scrnMsg.sellToCustCd.setInputProtected(true);

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            scrnMsg.sellToCustCd.clear();
        }

        NWAL2020CMsg bizMsg = new NWAL2020CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2020BMsg scrnMsg = (NWAL2020BMsg) bMsg;
        NWAL2020CMsg bizMsg = (NWAL2020CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2020CommonLogic.initCmnBtnProp(this);

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)
                && !NZZM0000E.equals(scrnMsg.getMessageCode())
                && !NWZM0971E.equals(scrnMsg.getMessageCode())
            ) {
            NWAL2020CommonLogic.setErrorField(this, scrnMsg);
            return;
        }

        NWAL2020CommonLogic.setCrCardProp(this, scrnMsg);
        NWAL2020CommonLogic.setBgColor(scrnMsg);
        NWAL2020CommonLogic.createCreditCardField(this, ctx, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL2020BMsg scrnMsg = (NWAL2020BMsg) bMsg;
        scrnMsg.sellToCustCd.setNameForMessage("Account Code");
        scrnMsg.crCardValidFlg.setNameForMessage("Invalid");
        scrnMsg.crCardCustRefNum.setNameForMessage("Cust Ref Box");
        scrnMsg.crCardExprYrMth.setNameForMessage("Valid Thru");
        scrnMsg.crCardTpCd.setNameForMessage("CC Type");
        scrnMsg.crCardLastDigitNum.setNameForMessage("Last 4 digits");
    }
}
