/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;
import business.servlet.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Save
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/09/2016   CITS            Makoto Okigami  Create          N/A
 * 08/10/2016   CITS            K.Ogino         Update          QC#9058
 * 07/05/2023   Hitachi         T.Kuroda        Update          QC#61440
 *</pre>
 */
public class NPBL0020Scrn00_CMN_Save extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        NPBL0020CommonLogic.checkInputForSaveAndSubmit(scrnMsg);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            if (TAB_HEADER.equals(scrnMsg.xxDplyTab.getValue())) {
                // clear html attribute
                scrnMsg.clearAllGUIAttribute(SCRN_ID);
                S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
                tblColor.setAlternateRowsBG(TABLE_ID, scrnMsg.A);
            }
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum, scrnMsg.prchReqNum_HD);

        NPBL0020CMsg bizMsg = new NPBL0020CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        NPBL0020CMsg bizMsg = (NPBL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2023/07/05 T.Kuroda [QC#61440, ADD]
        if ("W".equals(bizMsg.getMessageKind())) {
            bizMsg.initMessageInfo();
            return;
        }
        // END 2023/07/05 T.Kuroda [QC#61440, ADD]

        NPBL0020CommonLogic.setCtrlScrnItemDispAfterSearch(this, scrnMsg);

        if (!NPBL0020CommonLogic.postInputCheckForSaveAndSubmit(scrnMsg, bizMsg)) {
            return;
        }

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        // set focus
        scrnMsg.setFocusItem(scrnMsg.prchReqNum);

    }
}
