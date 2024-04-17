/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0010;

import static business.servlet.NPBL0010.constant.NPBL0010Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.TABLE_NM_PO_VND_V;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.TBL_NM_FOR_AUTH_PSN;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0010 Inventory Request List
 * Function Name : Return Action from NWAL1130
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/19/2016   CITS            Makoto Okigami  Create          N/A
 * 04/05/2016   CITS            K.Ogino         Update          N/A
 *</pre>
 */
public class NPBL0010_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0010BMsg scrnMsg = (NPBL0010BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (TBL_NM_FOR_AUTH_PSN.equals(scrnMsg.xxTblNm_P1.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.fullPsnNm, scrnMsg.R.no(1).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.fullPsnNm);

            } else if (TABLE_NM_PO_VND_V.equals(scrnMsg.xxTblNm_P1.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.vndCd, scrnMsg.R.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dplyVndNm, scrnMsg.R.no(2).xxComnScrColValTxt);

            }
        }
    }
}
