/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0290;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0290.NLCL0290CMsg;
import business.servlet.NLCL0290.common.NLCL0290CommonLogic;
import business.servlet.NLCL0290.constant.NLCL0290Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   CSAI            K.Lee           Create          N/A
 * 2018/04/06   CITS            K.Masaki        Update          QC#18472
 * 2018/04/20   CITS            T.Tokutomi      Update          QC#18380
 *</pre>
 */
public class NLCL0290_INIT extends S21INITCommonHandler implements NLCL0290Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), APPLICATION_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;

        // QC#18380 Update. In case of transition from workflow.
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length > 0) {
            if (params.length == 1) {
                // from NPAL0120
                Object param01 = (Object) params[0];
                if (param01 instanceof EZDBStringItem) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.invtyOrdNum_H, (EZDBStringItem) param01);
                } else {
                    // String
                    ZYPEZDItemValueSetter.setValue(scrnMsg.invtyOrdNum_H, (String) param01);
                }
            }
        }

        NLCL0290CMsg bizMsg = new NLCL0290CMsg();
        bizMsg.setBusinessID("NLCL0290");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;
        NLCL0290CMsg bizMsg = (NLCL0290CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NLCL0290CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;

        scrnMsg.adjTrxTpCd_H.setNameForMessage("Adjustment Transaction Type");
        scrnMsg.rtlWhCd_H.setNameForMessage("Warehouse Code");
        scrnMsg.rtlWhNm_H.setNameForMessage("Warehouse Name");
        // QC:18472 Start
        // scrnMsg.xxScrItem130Txt_H.setNameForMessage("Account");
        // scrnMsg.adjAcctAliasNm_H.setNameForMessage("Alias");
        // scrnMsg.locStsCd_H.setNameForMessage("Location Status");
        // scrnMsg.stkStsCd_H.setNameForMessage("Stock Status");
        // QC:18472 End
        scrnMsg.invtyOrdNum_H.setNameForMessage("Document#");
        // QC:18490
        scrnMsg.svcConfigMstrPk.setNameForMessage("Config#");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mdseCd_A.setNameForMessage("Item Number");
            scrnMsg.A.no(i).fromRtlSwhCd_A.setNameForMessage("Source Sub Warehouse");
            scrnMsg.A.no(i).toRtlSwhCd_A.setNameForMessage("Destination Sub Warehouse");
            scrnMsg.A.no(i).xxScrItem130Txt_A.setNameForMessage("Account");
            scrnMsg.A.no(i).ordQty_A.setNameForMessage("Item Quantity");
            scrnMsg.A.no(i).adjCatgCd_A.setNameForMessage("Reason");
            scrnMsg.A.no(i).serNum_A.setNameForMessage("Reason");
            scrnMsg.A.no(i).invtyOrdLineCostAmt_A.setAppFracDigit(2);
            // QC:18472 Start
            scrnMsg.A.no(i).stkStsCd_A.setNameForMessage("Stock Status");
            // QC:18472 End
        }
        scrnMsg.setFocusItem(scrnMsg.adjTrxTpCd_H);
    }
}
