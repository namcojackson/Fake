/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2000;

import static business.servlet.NWAL2000.constant.NWAL2000Constant.*;
import static business.servlet.NWAL2000.constant.NWAL2000Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL2000.constant.NWAL2000Constant.NWAM0270E;
import static business.servlet.NWAL2000.constant.NWAL2000Constant.STR_COMMA;
import static business.servlet.NWAL2000.constant.NWAL2000Constant.STR_N_A;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2000.NWAL2000CMsg;
import business.servlet.NWAL2000.common.NWAL2000CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL2000_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/08   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/20   Fujitsu         S.Yoshida       Update          QC#2177
 * 2018/06/15   Fujitsu         M.Yamada        Update          QC#25227
 *</pre>
 */
public class NWAL2000_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2000BMsg scrnMsg = (NWAL2000BMsg) bMsg;
        NWAL2000CMsg bizMsg = new NWAL2000CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        // QC#25227 upd
        if (params != null && params.length >= CNT_PARAM) {
            int ixP = 0;
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, (EZDBStringItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, (EZDBStringItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum, (EZDBStringItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.configCatgCd, (EZDBStringItem) params[ixP++]);

            //            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum, (EZDBStringItem) params[4]);
            //            ZYPEZDItemValueSetter.setValue(scrnMsg.dsCpoLineNum, (EZDBBigDecimalItem) params[5]);
            //            ZYPEZDItemValueSetter.setValue(scrnMsg.dsCpoLineSubNum, (EZDBBigDecimalItem) params[6]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_DI, (EZDBStringItem) params[ixP++]);

            ZYPEZDItemValueSetter.setValue(scrnMsg.ordQty, (EZDBBigDecimalItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.cancQty, (EZDBBigDecimalItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.cancQty_BK, scrnMsg.cancQty);

            if (!ZYPCommonFunc.hasValue(scrnMsg.ordQty) //
                    || scrnMsg.xxPopPrm_DI.getValue().contains(STR_COMMA)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm, STR_N_A);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm, scrnMsg.ordQty.getValue().toString());
            }
            // QC#25227 upd

            if (!NWAL2000CommonLogic.checkInputParam(scrnMsg)) {
                return null;
            }
        } else {
            scrnMsg.setMessageInfo(NWAM0270E);
            return null;
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL2000BMsg scrnMsg = (NWAL2000BMsg) bMsg;

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            scrnMsg.setInputProtected(true);
            NWAL2000CommonLogic.protectCmnBtnProp(this, scrnMsg);
            return;
        }
        NWAL2000CMsg bizMsg = (NWAL2000CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2000CommonLogic.initCmnBtnProp(this, scrnMsg);

        if (CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.chngRsnTpCd);
        } else if (LINE_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.cancQty);
        } else {
            scrnMsg.setFocusItem(scrnMsg.chngRsnTpCd);
        }

//        //Add Start NA QC#2177
//        NWAL2000CommonLogic.setDispLineNum(scrnMsg);
//        //Add End   NA QC#2177
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NWAL2000BMsg scrnMsg = (NWAL2000BMsg) bMsg;

        scrnMsg.cpoOrdNum.setNameForMessage("Order Number");
        scrnMsg.configCatgCd.setNameForMessage("Configuration Category");
//        scrnMsg.dsOrdPosnNum.setNameForMessage("Configuration Number");
        scrnMsg.xxLineNum_DI.setNameForMessage("Line Number");
        scrnMsg.cancQty.setNameForMessage("Cancel Quantity");
        scrnMsg.ordQty.setNameForMessage("Order Quantity Remaining");
        scrnMsg.chngRsnTpCd.setNameForMessage("Reason");
        scrnMsg.bizProcCmntTxt.setNameForMessage("Comment");
    }
}
