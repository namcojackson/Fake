/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;
//START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
//import static business.servlet.NSAL0010.constant.NSAL0010Constant.TAB_CURRENT_LOC;
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
//import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0010.NSAL0010CMsg;
//import business.servlet.NSAL0010.common.NSAL0010CommonLogic;
//import business.servlet.NSAL0010.constant.NSAL0010Constant;

//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/11/16   Hitachi         T.Tomita        Update          QC#647
 * 2023/07/10   Hitachi         Y.Nagasawa      Update          QC#61524
 *</pre>
 */
// START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
//public class NSAL0010Scrn00_TAB_CurrentLoc extends S21CommonHandler {

//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

//        //NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;

//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

//        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;

//        scrnMsg.xxDplyTab_01.setValue(TAB_CURRENT_LOC);

//        NSAL0010CMsg bizMsg = new NSAL0010CMsg();
//        bizMsg.setBusinessID(NSAL0010Constant.BUSINESS_ID);
//        bizMsg.setFunctionCode("20");
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//        return bizMsg;
//    }

//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
//        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;

//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//        // START 2015/11/16 T.Tomita [QC#647, MOD]
//        if ("E".equals(bizMsg.getMessageKind())) {
//            return;
//        }
//        // END 2015/11/16 T.Tomita [QC#647, MOD]

//        NSAL0010CommonLogic.controlScreenFields(this, scrnMsg, bizMsg.getUserID(), true, getUserProfileService());
//        scrnMsg.setFocusItem(scrnMsg.serNum_H1);

//    }
//}
// END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
