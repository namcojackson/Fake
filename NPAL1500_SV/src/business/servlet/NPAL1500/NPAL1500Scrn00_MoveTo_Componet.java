/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.BIZ_ID;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.NPAM0049E;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.NPAM1263E;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.NFBM0064E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1500.NPAL1500CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CITS            N.Akaishi       Create          N/A
 * 09/09/2016   CITS            S.Endo          Update          N/A
 * 11/28/2017   CITS            K.Ogino         Update          QC#22481
 *</pre>
 */
public class NPAL1500Scrn00_MoveTo_Componet extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // no processed.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        NPAL1500CMsg bizMsg = new NPAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        if (scrnMsg.getMessageType() != EZDMessageInfo.MSGTYPE_ERROR) {

            refreshScrnDetail(scrnMsg);
        }
    }

    private void refreshScrnDetail(NPAL1500BMsg scrnMsg) {
        // QC#22481 Start
        if (scrnMsg.A.getValidCount() > 0) {

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(0).prchReqNum_A1)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, scrnMsg.A.no(0).prchReqNum_A1);
            }

            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, PRCH_REQ_TP.SUBCONTRACT);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, PRCH_REQ_SRC_TP.INVENTORY_REQUEST_ENTRY);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, scrnMsg.poNum.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, scrnMsg.poNum);

            Object[] params = new Object[5];
            params[0] = scrnMsg.xxPopPrm_P0;
            params[1] = scrnMsg.xxPopPrm_P1;
            params[2] = scrnMsg.xxPopPrm_P2;
            params[3] = scrnMsg.xxPopPrm_P3;
            params[4] = scrnMsg.xxPopPrm_P4;

            setArgForSubScreen(params);

        }
        // QC#22481 End
    }
}
