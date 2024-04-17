/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0010;

import static business.servlet.NPBL0010.constant.NPBL0010Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.IDX_17;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.IDX_2;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0010 Inventory Request Entry
 * Function Name : Return Action from NMAL6760
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/05/2016   CITS            K.Ogino         Create          N/A
 * 09/26/2019   Fujitsu         T.Ogura         Update          QC#52362
 *</pre>
 */
public class NPBL0010_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0010BMsg scrnMsg = (NPBL0010BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_EO, scrnMsg.P.no(IDX_17).xxPopPrm);
            // START 2019/09/26 T.Ogura [QC#52362,MOD]
//            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToLocNm_EO, scrnMsg.P.no(IDX_2).xxPopPrm);
            String shipToCustLocNm = "";
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(IDX_2).xxPopPrm)) {
                shipToCustLocNm = scrnMsg.P.no(IDX_2).xxPopPrm.getValue();
                if (shipToCustLocNm.length() > 60) {
                    shipToCustLocNm = shipToCustLocNm.substring(0, 60);
                }
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToLocNm_EO, shipToCustLocNm);
            // END   2019/09/26 T.Ogura [QC#52362,MOD]
            scrnMsg.setFocusItem(scrnMsg.shipToCustCd_EO);
        }
    }
}
