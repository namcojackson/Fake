/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.NMAL6710;

/**
 *<pre>
 *  Account Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/20/2015   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
//public class NMAL6710Scrn00_CMN_Reset extends S21CommonHandler implements NMAL6710Constant {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        // NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;
//
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;
//        NMAL6710CMsg bizMsg = new NMAL6710CMsg();
//        NMAL6710CommonLogic.resetClear(scrnMsg);
//        bizMsg.setBusinessID(BUSINESS_ID);
//        bizMsg.setFunctionCode(FUNCTION_CODE_SEARCH);
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
//
//    }
//
//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//
//        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;
//        NMAL6710CMsg bizMsg = (NMAL6710CMsg) cMsg;
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        NMAL6710CommonLogic.setButtonProperties_INIT(this, scrnMsg);
//        NMAL6710CommonLogic.setHeaderProperties(this, scrnMsg);
//        NMAL6710CommonLogic.clearConfirmInfo(scrnMsg, ALL);
//        scrnMsg.setFocusItem(scrnMsg.dsAcctNm_01);
//        scrnMsg.xxChkBox_01.setValue(ZYPConstant.CHKBOX_ON_Y);
//    }
//
//}
