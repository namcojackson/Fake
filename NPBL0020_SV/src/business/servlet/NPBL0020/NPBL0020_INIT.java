/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_0;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_1;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_2;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_3;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_4;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_5;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.TAB_DETAIL;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;
import business.servlet.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Init
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/26/2016   CITS            Makoto Okigami  Create          N/A
 * 04/06/2016   CITS            K.Ogino         Update          N/A
 * 11/28/2017   CITS            K.Ogino         Update          QC#22481
 *</pre>
 */
public class NPBL0020_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length == 1) {
            Object param01 = (Object) params[0];

            if (param01 instanceof EZDBStringItem) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum_IP, (EZDBStringItem) param01);
            } else {
                // String
                ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum_IP, (String) param01);
            }
        // QC#22481
        } else if (params != null && params.length == IDX_5) {
            EZDBStringItem param01 = (EZDBStringItem) params[IDX_0];
            ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum_IP, param01);
            EZDBStringItem param02 = (EZDBStringItem) params[IDX_1];
            ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqTpCd_SL, param02);
            EZDBStringItem param03 = (EZDBStringItem) params[IDX_2];
            ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqSrcTpCd, param03);
            EZDBStringItem param04 = (EZDBStringItem) params[IDX_3];
            ZYPEZDItemValueSetter.setValue(scrnMsg.trxRefNum, param04);
            EZDBStringItem param05 = (EZDBStringItem) params[IDX_4];
            ZYPEZDItemValueSetter.setValue(scrnMsg.poOrdNum, param05);
        }

        NPBL0020CMsg bizMsg = new NPBL0020CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        NPBL0020CMsg bizMsg = (NPBL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (ZYPCommonFunc.hasValue(scrnMsg.prchReqNum)) {
            NPBL0020CommonLogic.setCtrlScrnItemDispAfterSearch(this, scrnMsg);
        } else {
            NPBL0020CommonLogic.setCtrlScrnItemDispInit(this, scrnMsg);
        }

        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);

        // Set Forcus
        scrnMsg.setFocusItem(scrnMsg.prchReqNum);
    }

    @Override
    protected void setNameForMessage(EZDBMsg scrnMsg) {
        NPBL0020CommonLogic.setNameForMessage((NPBL0020BMsg) scrnMsg);
    }
}
