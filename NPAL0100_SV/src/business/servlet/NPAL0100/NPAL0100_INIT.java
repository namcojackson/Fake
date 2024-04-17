/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/13/2009   Fujitsu         I.Kondo         Create          N/A
 *</pre>
 */
package business.servlet.NPAL0100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL0100.NPAL0100CMsg;
import business.servlet.NPAL0100.common.NPAL0100CommonLogic;
import business.servlet.NPAL0100.constant.NPAL0100Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class NPAL0100_INIT extends S21INITCommonHandler implements NPAL0100Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

//        // security violation check of this screen.
//        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUJINESS_ID);

        NPAL0100BMsg scrnMsg = (NPAL0100BMsg) bMsg;

        // Get Parent Window Parameter
        Object[] params = (Object[]) getArgForSubScreen();

        // Get Request Paramter
        NPAL0100CommonLogic.otherBusConnectFrom_NPAL0100_INIT(params, scrnMsg);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL0100BMsg scrnMsg = (NPAL0100BMsg) bMsg;

        if (ERROR_FLG_ON.equals(scrnMsg.xxErrFlg.getValue())) {
            return null;
        } else {
            NPAL0100CMsg bizMsg = NPAL0100CommonLogic.setRequestDataToBizMsg();

            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL0100BMsg scrnMsg = (NPAL0100BMsg) bMsg;

        NPAL0100CMsg bizMsg = (NPAL0100CMsg) cMsg;
        
        if (null != bizMsg) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        } else {
            // do nothing.
        }

        if (ERROR_FLG_ON.equals(scrnMsg.xxErrFlg.getValue())) {
            NPAL0100CommonLogic.initializeForError(this, scrnMsg);
        } else {
            NPAL0100CommonLogic.initialize(this, scrnMsg);
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NPAL0100BMsg scrnMsg = (NPAL0100BMsg) bMsg;

        scrnMsg.vndCd.setNameForMessage(SCRN_ITEM_NM_VND_CD);
        scrnMsg.locNm_H1.setNameForMessage(SCRN_ITEM_NM_VND_NM);
        scrnMsg.custIssPoNum.setNameForMessage(SCRN_ITEM_NM_CUST_ISS_PO_NUM);
        scrnMsg.custIssPoDt.setNameForMessage(SCRN_ITEM_NM_CUST_ISS_PO_DT);
        scrnMsg.cpoOrdNum.setNameForMessage(SCRN_ITEM_NM_CPO_ORD_NUM);
        scrnMsg.billToCustCd.setNameForMessage(SCRN_ITEM_NM_BILL_TO_CUST_CD);
        scrnMsg.locNm_H2.setNameForMessage(SCRN_ITEM_NM_BILL_TO_CUST_NM);
        scrnMsg.mdseCd.setNameForMessage(SCRN_ITEM_NM_MDSE_CD);
        scrnMsg.mdseDescShortTxt.setNameForMessage(SCRN_ITEM_NM_MDSE_NM);
        scrnMsg.poQty.setNameForMessage(SCRN_ITEM_NM_PO_QTY);
        scrnMsg.coa1L3If.setNameForMessage(SCRN_ITEM_NM_MDSE_TP_CD);
        scrnMsg.invQty.setNameForMessage(SCRN_ITEM_NM_INV_QTY);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).serNum_A1.setNameForMessage(SCRN_ITEM_NM_SER_NUM);
        }
    }

}
