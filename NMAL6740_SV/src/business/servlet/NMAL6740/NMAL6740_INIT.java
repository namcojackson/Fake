/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6740;

import static business.servlet.NMAL6740.constant.NMAL6740Constant.BIZ_ID;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.NMAM8228E;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.SHIP_TO_CD;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.TAB_TAXING;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6740.NMAL6740CMsg;
import business.servlet.NMAL6740.common.NMAL6740CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/20   CUSA            Fujitsu         Create          N/A
 * 2015/10/09   Fujitsu         C.Tanaka        Update          CSA
 * 2016/09/09   SRAA            Y.Chen          Update          QC#9448
 * 2018/04/24   Fujitsu         M.Ohno          Update          QC#24635-2
 * 2018/08/07   Fujitsu         S.Ohki          Update          QC#27222
 *</pre>
 */
public class NMAL6740_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

        NMAL6740BMsg scrnMsg = (NMAL6740BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            if (params.length == 1) {
                EZDBStringItem param01 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd, param01);
            }
        }
        // Add Start 2018/08/07 QC#27222
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_TAXING);
        // Add End 2018/08/07 QC#27222

        if (!ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd)) {
            scrnMsg.setMessageInfo(NMAM8228E, new String[] {SHIP_TO_CD });
            throw new EZDFieldErrorException();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6740BMsg scrnMsg = (NMAL6740BMsg) bMsg;

        NMAL6740CMsg bizMsg = new NMAL6740CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6740BMsg scrnMsg = (NMAL6740BMsg) bMsg;
        NMAL6740CMsg bizMsg = (NMAL6740CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        NMAL6740CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
        scrnMsg.setFocusItem(scrnMsg.coaChCd);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL6740BMsg scrnMsg = (NMAL6740BMsg) bMsg;

        scrnMsg.coaChCd.setNameForMessage("Sales Channel");
        // QC#9448
        // scrnMsg.coaAfflCd.setNameForMessage("Intercompany");
        //2018/04/24 QC#24635-2 add start
        scrnMsg.xxScrItem130Txt.setNameForMessage("ExpenseAccount");
        //2018/04/24 QC#24635-2 add end
    }
}
