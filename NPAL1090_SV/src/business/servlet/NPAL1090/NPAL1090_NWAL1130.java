/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/13   Fujitsu         T.Nishikawa     Create          CSA
 *</pre>
 */
public class NPAL1090_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        //
        // NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
        //
        // // see: NSBL0190_NWAL1130
        //
        // for (int i = 0; i < scrnMsg.S.length(); i++) {
        //
        // if
        // (TECH_CODE_SQL_NAME.equals(scrnMsg.S.no(i).xxComnScrQueryColNm.getValue()))
        // {
        // setValue(scrnMsg.techCd_H1,
        // scrnMsg.S.no(i).xxComnScrColValTxt);
        // scrnMsg.setFocusItem(scrnMsg.rqstRtlWhCd_H1);
        //
        // } else if
        // (TECH_NAME_SQL_NAME.equals(scrnMsg.S.no(i).xxComnScrQueryColNm.getValue()))
        // {
        // setValue(scrnMsg.techNm_H1,
        // scrnMsg.S.no(i).xxComnScrColValTxt);
        // scrnMsg.setFocusItem(scrnMsg.rqstRtlWhCd_H1);
        //
        // } else if
        // (SHIP_TO_CUST_CODE_SQL_NAME.equals(scrnMsg.S.no(i).xxComnScrQueryColNm.getValue()))
        // {
        // setValue(scrnMsg.shipToCustCd_H1,
        // scrnMsg.S.no(i).xxComnScrColValTxt);
        // scrnMsg.setFocusItem(scrnMsg.shpgSvcLvlCd_SE);
        //
        // } else if
        // (SHIP_TO_CUST_NAME_SQL_NAME.equals(scrnMsg.S.no(i).xxComnScrQueryColNm.getValue()))
        // {
        // setValue(scrnMsg.locNm_H1,
        // scrnMsg.S.no(i).xxComnScrColValTxt);
        // scrnMsg.setFocusItem(scrnMsg.shpgSvcLvlCd_SE);
        // }
        //
        // }
    }

}
