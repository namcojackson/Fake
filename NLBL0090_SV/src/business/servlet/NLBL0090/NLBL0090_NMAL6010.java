/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/22   Fujitsu         Mori            Create          N/A
 * 2016/04/08   CITS            Y.Nomura        Update          for CSA
 *</pre>
 */

// This class NO use.
//package business.servlet.NLBL0090;
//
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.servletcommon.EZDApplicationContext;
//import business.servlet.NLBL0090.common.NLBL0090CommonLogic;
//
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//
//public class NLBL0090_NMAL6010 extends S21CommonHandler {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        return null;
//    }
//
//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//
//        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;
//
//        String prevEventNm = scrnMsg.xxScrEventNm.getValue();
//
//        if (NLBL0090Scrn00_OpenWin_SellTo.class.getSimpleName().equals(prevEventNm)) {
//            if (!NLBL0090CommonLogic.isClosedEvent(getLastGuard())) {
//                copyReturnValues(scrnMsg);
//            }
//
//        } else if (NLBL0090Scrn00_OpenWin_ShipTo.class.getSimpleName().equals(prevEventNm)) {
//            if (!NLBL0090CommonLogic.isClosedEvent(getLastGuard())) {
//                copyReturnValues(scrnMsg);
//            }
//
//        } else {
//            // nothing to do.
//        }
//        scrnMsg.setFocusItem(scrnMsg.podStsTpForScrCd_H2);
//    }
//
//    private void copyReturnValues(NLBL0090BMsg scrnMsg) {
//        scrnMsg.sellToCustCd_H2.setValue(scrnMsg.sellToCustCd_P1.getValue());
//        scrnMsg.locNm_H2.setValue(scrnMsg.locNm_P1.getValue());
//        scrnMsg.shipToCustCd_H2.setValue(scrnMsg.shipToCustCd_P1.getValue());
//        scrnMsg.locNm_H3.setValue(scrnMsg.locNm_P2.getValue());
//    }
//
//}
