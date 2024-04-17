/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1830;

import static business.servlet.NWAL1830.constant.NWAL1830Constant.BIZ_ID;
import static business.servlet.NWAL1830.constant.NWAL1830Constant.ZZM9000E;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1830.NWAL1830CMsg;
import business.servlet.NWAL1830.common.NWAL1830CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL1830_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NWAL1830_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1830BMsg scrnMsg = (NWAL1830BMsg) bMsg;
        NWAL1830CMsg bizMsg = new NWAL1830CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == 18) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum_OH, (EZDBStringItem) params[0]);
            if ((EZDBStringItem) params[1] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgCd_OH, (EZDBStringItem) params[1]);
            }
            if ((EZDBStringItem) params[2] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgDescTxt_OH, (EZDBStringItem) params[2]);
            }
            if ((EZDBStringItem) params[3] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdTpCd_OH, (EZDBStringItem) params[3]);
            }
            if ((EZDBStringItem) params[4] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdTpDescTxt_OH, (EZDBStringItem) params[4]);
            }
            if ((EZDBStringItem) params[5] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdRsnCd_OH, (EZDBStringItem) params[5]);
            }
            if ((EZDBStringItem) params[6] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdRsnDescTxt_OH, (EZDBStringItem) params[6]);
            }
            if ((EZDBDateItem) params[7] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.ordDt_OH, (EZDBDateItem) params[7]);
            }
            if ((EZDBStringItem) params[8] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.ordHdrStsCd_OH, (EZDBStringItem) params[8]);
            }
            if ((EZDBStringItem) params[9] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.ordHdrStsNm_OH, (EZDBStringItem) params[9]);
            }
            if ((EZDBStringItem) params[10] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.aquNum_OH, (EZDBStringItem) params[10]);
            }
            if ((EZDBBigDecimalItem) params[11] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.loanPerDaysAot_OH, (EZDBBigDecimalItem) params[11]);
            }
            if ((EZDBStringItem) params[12] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepTocCd_OH, (EZDBStringItem) params[12]);
            }
            if ((EZDBStringItem) params[13] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepTocNm_OH, (EZDBStringItem) params[13]);
            }
            if ((EZDBStringItem) params[14] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaBrCd_OH, (EZDBStringItem) params[14]);
            }
            if ((EZDBStringItem) params[15] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaBrDescTxt_OH, (EZDBStringItem) params[15]);
            }
            if ((EZDBStringItem) params[16] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustAcctCd_OH, (EZDBStringItem) params[16]);
            }
            if ((EZDBStringItem) params[17] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustAcctNm_OH, (EZDBStringItem) params[17]);
            }
        } else if (params != null && params.length == 1) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum_OH, (EZDBStringItem) params[0]);
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_OH)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {scrnMsg.cpoOrdNum_OH.getNameForMessage()});
            return null;
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1830BMsg scrnMsg = (NWAL1830BMsg) bMsg;
        NWAL1830CMsg bizMsg = (NWAL1830CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        NWAL1830CommonLogic.initCmnBtnProp(this, scrnMsg);
        NWAL1830CommonLogic.setControlField(this, scrnMsg);

//        if (scrnMsg.getMessageType() == 1 || MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
//            throw new EZDFieldErrorException();
//        }
        scrnMsg.setFocusItem(scrnMsg.dsOrdLineCatgCd_OL);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1830BMsg scrnMsg = (NWAL1830BMsg) bMsg;

        scrnMsg.cpoOrdNum_OH.setNameForMessage("Order Number");
        scrnMsg.dsOrdLineCatgCd_OL.setNameForMessage("Action");
        scrnMsg.dsOrdCatgCd_OL.setNameForMessage("Order Category");
        scrnMsg.dsOrdTpCd_OL.setNameForMessage("Order Reason");
        scrnMsg.slsRepTocNm_OL.setNameForMessage("Salesrep");
        scrnMsg.cpoOrdNum_OL.setNameForMessage("Order Number");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage("Check Box");
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).dsOrdLineCatgCd_BA.setNameForMessage("Action");
            scrnMsg.B.no(i).xxChkBox_B1.setNameForMessage("Check Box");
            scrnMsg.B.no(i).rtlWhNm_B1.setNameForMessage("Warehouse");
        }
    }
}
