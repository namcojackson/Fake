/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;
//START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
//import static business.servlet.NSAL0010.constant.NSAL0010Constant.*;

//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
//import parts.servletcommon.EZDApplicationContext;

//import business.blap.NSAL0010.NSAL0010CMsg;

//import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/14   Hitachi         K.Kasai         Create          N/A
 * 2015/11/16   Hitachi         T.Tomita        Update          QC#647
 * 2023/07/10   Hitachi         Y.Nagasawa      Update          QC#61524
 *</pre>
 */
// START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
//public class NSAL0010Scrn00_SelectPostalCode extends S21CommonHandler {

//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
//        scrnMsg.addCheckItem(scrnMsg.postCd_F);
//        if (!ZYPCommonFunc.hasValue(scrnMsg.postCd_F.getValue())) {
//            // START 2015/11/16 T.Tomita [QC#647, MOD]
//            scrnMsg.postCd_F.setErrorInfo(1, "NSAM0007E", new String[] {"Postal Code" });
//            // END 2015/11/16 T.Tomita [QC#647, MOD]
//        }
//        scrnMsg.putErrorScreen();
//    }

//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
//        NSAL0010CMsg bizMsg = new NSAL0010CMsg();
//        bizMsg.setBusinessID(BUSINESS_ID);
//        bizMsg.setFunctionCode("20");
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//        return bizMsg;
//    }

//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
//        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;

//        EZDMsg.copy(bizMsg, null, scrnMsg, null);

//        scrnMsg.addCheckItem(scrnMsg.postCd_F);
//        scrnMsg.putErrorScreen();

//        if (!scrnMsg.postCd_F.isError()) {
//            scrnMsg.setFocusItem(scrnMsg.postCd_F);
//        }
//    }
//}
//END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
