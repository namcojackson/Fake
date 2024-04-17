/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/01/2009   SRA             T.Chijimatsu    Create          N/A
 * 08/05/2013   Fujitsu         N.Sugiura       Update          QC1469
 *</pre>
 */
//package business.servlet.NMAL4500;
//
//
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
//import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL4500.NMAL4500CMsg;
//import business.servlet.NMAL4500.common.NMAL4500CommonLogic;
//import business.servlet.NMAL4500.constant.NMAL4500Constant;
//
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//
//public class NMAL4500Scrn00_Get_Address extends S21CommonHandler implements NMAL4500Constant {
//
// @Override
// protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//  
//  NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;
////        NMAL4500CommonLogic.checkPostCd(scrnMsg);
//        scrnMsg.putErrorScreen();
// }
//
//  @Override
// protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
// 
//  NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;
//  NMAL4500CMsg bizMsg  = new NMAL4500CMsg();
//        
//        // Clear county
//  scrnMsg.cntyPk_01.clear();
//        scrnMsg.cntyNm_02.clear();
//        scrnMsg.cntyPk_03.clear();
//        
//  bizMsg.setBusinessID("NMAL4500");
//  bizMsg.setFunctionCode("20");
//  EZDMsg.copy(scrnMsg, null, bizMsg, null);
//   return bizMsg;
// }
//
// @Override
// protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//
//  NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;
//  NMAL4500CMsg bizMsg  = (NMAL4500CMsg) cMsg;
//
//  EZDMsg.copy(bizMsg, null, scrnMsg, null);
//  
//        // Check items error
//        scrnMsg.addCheckItem(scrnMsg.postCd_01);
//        scrnMsg.putErrorScreen();
//        
//        // Focus
//        scrnMsg.setFocusItem(scrnMsg.postCd_01);
// }
//}
