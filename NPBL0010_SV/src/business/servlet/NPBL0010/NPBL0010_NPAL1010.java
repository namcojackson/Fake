/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0010;

import static business.servlet.NPBL0010.constant.NPBL0010Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.SOURCE_WAREHOUSE;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.SOURCE_SUB_WAREHOUSE;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DESTINATION_WAREHOUSE;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.DESTINATION_SUB_WAREHOUSE;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPBL0010.NPBL0010CMsg;
//import business.servlet.NPBL0010.constant.NPBL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NPBL0010 Inventory Request List
 * Function Name : Return Action from NPAL1010
 * </pre>
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/19/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NPBL0010_NPAL1010 extends S21CommonHandler {

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
            if (SOURCE_WAREHOUSE.equals(scrnMsg.P.no(12).xxPopPrm.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.srcRtlWhCd);
            } else if (SOURCE_SUB_WAREHOUSE.equals(scrnMsg.P.no(12).xxPopPrm.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.srcRtlSwhCd);
            } else if (DESTINATION_WAREHOUSE.equals(scrnMsg.P.no(12).xxPopPrm.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.destRtlWhCd);
            } else if (DESTINATION_SUB_WAREHOUSE.equals(scrnMsg.P.no(12).xxPopPrm.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.destRtlSwhCd);
            }
        }

    }
}
