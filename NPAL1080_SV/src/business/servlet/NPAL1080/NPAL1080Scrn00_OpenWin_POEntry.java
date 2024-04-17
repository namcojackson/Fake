///**
// *<pre>
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 2009/03/26   CUSA            Fujitsu         Create          N/A
// *</pre>
// */
//package business.servlet.NPAL1080;
//
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.servletcommon.EZDApplicationContext;
//import business.servlet.NPAL1090.constant.NPAL1090Constant;
//
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//
///**
// *<pre>
// * 
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 2015/03/27   Fujitsu         T.Nishikawa     Create          CSA
// *</pre>
// */
//public class NPAL1080Scrn00_OpenWin_POEntry extends S21CommonHandler implements NPAL1090Constant {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        // no process
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
//        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
//        int selectIdx = getButtonSelectNumber();
//
//        Object[] params = new Object[1];
//        params[0] = scrnMsg.A.no(selectIdx).poOrdNum_D1;
//
//        setArgForSubScreen(params);
//
//    }
//
//}
