/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0170;

/**
 *<pre>
 * NMAL0170Scrn00_New
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Fujitsu         T.Arima          Create          N/A
 * 2016/02/23   CITS            S.Tanikawa       UPDATE          QC#2669
 *</pre>
 */
// DELETE ALL 2016/02/25 QC#2669
// public class NMAL0170Scrn00_New extends S21CommonHandler {
//
// @Override
// /**
// * Check Input Event
// * - do Nothing.
// */
// protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg)
// {
// // do nothing.
// }
//
// @Override
// /**
// * Set Request Date Event
// * - do Nothing.
// */
// protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg
// bMsg) {
// // do nothing.
// return null;
// }
//
// @Override
// /**
// * Do Process Event
// * - Create New Line.
// * - Set Protect for input field.
// */
// protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg,
// EZDCMsg cMsg) {
//
// NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;
//
// ZYPTableUtil.clear(scrnMsg.A);
// scrnMsg.A.setValidCount(1);
//
// String salesDate =
// ZYPDateUtil.getSalesDate(getGlobalCompanyCode());
// ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(0).supdRelnStageDt_A1,
// salesDate);
//
// NMAL0170CommonLogic.setCmnBtnProp(this, scrnMsg, EVENT_NEW);
//
// NMAL0170CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
// NMAL0170CommonLogic.setScrnLineProtected(scrnMsg);
//
// }
// }
