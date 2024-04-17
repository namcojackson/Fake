/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1150;

import static business.servlet.NPAL1150.constant.NPAL1150Constant.BUSINESS_APPLICATION_ID;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1150.NPAL1150CMsg;
import business.servlet.NPAL1150.common.NPAL1150CommonLogic;
import business.servlet.NPAL1150.constant.NPAL1150Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NPAL1150 ASN Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/15   Hitachi         T.Kawazu        Create          N/A
 * 2013/05/30   Hitachi         T.Kawazu        Update          QC1233
 * 2016/11/07   CITS            T.Hakodate      Update          QC#15170
 * 09/13/2018   CITS            K.Ogino         Update          QC#26964/QC#26965(TST Impreso / Dietzgen PO EDI)
 *</pre>
 */
public class NPAL1150_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPLICATION_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1150CommonLogic.initCommonButton(this);

        NPAL1150BMsg scrnMsg = (NPAL1150BMsg) bMsg;
        // QC#26964/QC#26965
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == 1) {
            Object param01 = (Object) params[0];

            if (param01 instanceof EZDBStringItem) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.ediPoOrdNum_A2, (EZDBStringItem) param01);
            } else {
                // String
                ZYPEZDItemValueSetter.setValue(scrnMsg.ediPoOrdNum_A2, (String) param01);
            }
        }

        NPAL1150CMsg bizMsg = new NPAL1150CMsg();
        bizMsg.setBusinessID("NPAL1150");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1150BMsg scrnMsg = (NPAL1150BMsg) bMsg;
        NPAL1150CMsg bizMsg = (NPAL1150CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // QC#26964/QC#26965
        if (ZYPCommonFunc.hasValue(scrnMsg.ediPoOrdNum_A1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, NPAL1150Constant.UNDER_TAB_HEADER);
            scrnMsg.B.no(0).batErrMsgTxt_HV.setInputProtected(true);
            NPAL1150CommonLogic.editProtectedScrnMsg(this, scrnMsg);
            NPAL1150CommonLogic.controlButton(this, scrnMsg);

            scrnMsg.addCheckItem(scrnMsg.xxFromDt_A1);
            scrnMsg.addCheckItem(scrnMsg.xxToDt_A1);
            scrnMsg.putErrorScreen();
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (NPAL1150CommonLogic.isSaveMode(scrnMsg, i)) {
                    scrnMsg.setFocusItem(scrnMsg.A.no(i).xxChkBox_B1);
                    break;
                }
            }
        } else {
            NPAL1150CommonLogic.initialize(this, scrnMsg);   
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NPAL1150BMsg scrnMsg = (NPAL1150BMsg) bMsg;

        // QC#15170
        // scrnMsg.vndNm_A1.setNameForMessage("Vendor");
        scrnMsg.vndCd_A1.setNameForMessage("Supplier Code");

        scrnMsg.asnEdiProcStsCd_SV.setNameForMessage("EDI Status");
        // Update Start QC1233
        scrnMsg.ediPoOrdNum_A1.setNameForMessage("PO#");
        // Update End QC1233
        scrnMsg.xxFromDt_A1.setNameForMessage("Rcv Date From");
        scrnMsg.xxToDt_A1.setNameForMessage("Rcv Date To");

    }
}
