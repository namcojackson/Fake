/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7090;

import static business.servlet.NMAL7090.constant.NMAL7090Constant.BIZ_ID;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.EVENT_DELETE;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.FUNCTION_CD_UPDATE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7090.NMAL7090CMsg;
import business.servlet.NMAL7090.common.NMAL7090CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID  : NMAL7090 Item Supersessions Mass Add
 * Function Name : DELETE
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   CITS            S.Tanikawa      Create          N/A
 * 2016/05/11   CITS            S.Tanikawa      Update          QC#8180
 *</pre>
 */
public class NMAL7090Scrn00_CMN_Delete extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // there is no processing.

        // NMAL7090BMsg scrnMsg = (NMAL7090BMsg) bMsg;
        // NMAL7090CommonLogic.addCheckTableB(scrnMsg);
        // List<Integer> selectedRowsDscrd = ZYPTableUtil.getSelectedRows(scrnMsg.B, NMAL7090Bean.rqstDscdFlg_B, ZYPConstant.FLG_ON_Y);
        // List<Integer> selectedRowsSubmt = ZYPTableUtil.getSelectedRows(scrnMsg.B, NMAL7090Bean.submtFlg_B, ZYPConstant.FLG_ON_Y);
        // if (selectedRowsDscrd.size() == 0) {
        // // Error:Please check Discard.
        // scrnMsg.setMessageInfo(NMAM8431E);
        // throw new EZDFieldErrorException();
        // }
        //
        // for (int i : selectedRowsDscrd) {
        // if (selectedRowsSubmt.contains(selectedRowsDscrd.get(i))) {
        // // Error:Please check Submit or Discard.
        // scrnMsg.setMessageInfo(NMAM8429E);
        // throw new EZDFieldErrorException();
        // }
        // }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7090BMsg scrnMsg = (NMAL7090BMsg) bMsg;

        NMAL7090CMsg bizMsg = new NMAL7090CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        // UPDATE START 2016/05/11 QC#8180
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        // UPDATE END 2016/05/11 QC#8180
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7090BMsg scrnMsg = (NMAL7090BMsg) bMsg;
        NMAL7090CMsg bizMsg = (NMAL7090CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // set screen property
        NMAL7090CommonLogic.setScrnProp(this, scrnMsg, EVENT_DELETE);
    }
}
