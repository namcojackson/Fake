/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7090;

import static business.servlet.NMAL7090.constant.NMAL7090Constant.BIZ_ID;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7090.NMAL7090CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID  : NMAL7090 Item Supersessions Mass Add
 * Function Name : OnChange Discard All
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   CITS            S.Tanikawa      Create          N/A
 * 2016/05/11   CITS            S.Tanikawa      Update          QC#8175
 *</pre>
 */
public class NMAL7090Scrn00_OnChange_DiscardAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7090BMsg scrnMsg = (NMAL7090BMsg) bMsg;

        NMAL7090CMsg bizMsg = new NMAL7090CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7090BMsg scrnMsg = (NMAL7090BMsg) bMsg;

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.rqstDscdFlg_BA.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).rqstDscdFlg_B, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).submtFlg_B, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(scrnMsg.submtFlg_BA, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).rqstDscdFlg_B, ZYPConstant.FLG_OFF_N);
            }
        }
    }
}
