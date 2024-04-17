/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.FUNC_CD_UPDT;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.servlet.NWAL1770.common.NWAL1770CommonLogic;
import business.servlet.NWAL1770.common.NWAL1770CommonLogicForScrnFields;
import business.servlet.NWAL1770.constant.NWAL1770Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/03   Fujitsu         T.Yoshida       Create          N/A
 * 2017/08/07   Fujitsu         Y.Kanefusa      Update          S21_NA#20217
 * 2017/10/17   Hitachi         J.Kim           Update          QC#21760
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 *</pre>
 */
public class NWAL1770Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        NWAL1770CommonLogic.checkItemAllFields(scrnMsg);
        NWAL1770CommonLogic.checkPmtTermsField(scrnMsg);
        NWAL1770CommonLogic.checkCustPoField(scrnMsg);
        NWAL1770CommonLogic.checkContactField(scrnMsg);
        // START 2017/10/17 J.Kim [QC#21760,DEL]
        // NWAL1770CommonLogic.checkRddField(scrnMsg);
        // END 2017/10/17 J.Kim [QC#21760,DEL]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        NWAL1770CMsg bizMsg = new NWAL1770CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPDT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        NWAL1770CommonLogic.checkItemLineWarning(scrnMsg);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        NWAL1770CommonLogic.checkItemAllFields(scrnMsg);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        } else if (EZDMessageInfo.MSGTYPE_WARNING == scrnMsg.getMessageType()) {
            return;
        }

        //scrnMsg.xxDplyTab.setValue(TAB_CUSTOMER);// QC#20217 2017/07/24 Dell
        scrnMsg.setFocusItem(scrnMsg.splyQuoteNum);
        // QC#20217 mod Start
//        NWAL1770CommonLogic.setTblBackColorForContact(scrnMsg);
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        if (NWAL1770Constant.TAB_CUSTOMER.equals(dplyTab)) {
            NWAL1770CommonLogic.setTblBackColorForContact(scrnMsg);
 
        } else if (NWAL1770Constant.TAB_ADDITIONAL.equals(dplyTab)) {
            NWAL1770CommonLogic.setTblBackColorForAddl(scrnMsg);

        } else if (NWAL1770Constant.TAB_ITEMS.equals(dplyTab)) {
            NWAL1770CommonLogic.setTblBackColorForItem(scrnMsg);
        }
        // QC#20217 mod End
        NWAL1770CommonLogicForScrnFields.setProtect(this, scrnMsg);
    }
}
