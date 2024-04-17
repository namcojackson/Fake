/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0200;

import static business.servlet.NMAL0200.constant.NMAL0200Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NMAL0200 Product Categorization Maintenance
 * Function Name : Return to PopUp
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NMAL0200_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0200BMsg scrnMsg = (NMAL0200BMsg) bMsg;
        int xxCellIdx = scrnMsg.xxCellIdx.getValueInt();

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(xxCellIdx).scdProdHrchCd_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(xxCellIdx).xxDplyByCtrlItemCdNm_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
            scrnMsg.setFocusItem(scrnMsg.A.no(xxCellIdx).scdProdHrchCd_A1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.A.no(xxCellIdx).scdProdHrchCd_A1);
        }
    }
}
