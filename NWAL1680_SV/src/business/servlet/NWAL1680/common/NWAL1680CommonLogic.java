/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1680.common;

import static business.servlet.NWAL1680.constant.NWAL1680Constant.BTN_CMN_CLEAR;
import static business.servlet.NWAL1680.constant.NWAL1680Constant.BTN_CMN_CLOSE;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL1680.NWAL1680BMsg;
import business.servlet.NWAL1680.NWAL1680_ABMsg;
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWAL1680CommonLogic {
    /**
     * Set Button Enable Init
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }
    
    /**
     * Set Screen Enable Init
     * @param handler EZDCommonHandler
     */
    public static void initScreenField(NWAL1680BMsg scrnMsg) {
        scrnMsg.cpoOrdNum.setInputProtected(true);
        scrnMsg.shipToCustAcctNm.setInputProtected(true);
        scrnMsg.xxDtlCnt_LC.setInputProtected(true);
        scrnMsg.xxDtlCnt_LM.setInputProtected(true);
        scrnMsg.xxDtlCnt_RC.setInputProtected(true);
        scrnMsg.xxDtlCnt_RM.setInputProtected(true);
        scrnMsg.xxDtlCnt_SC.setInputProtected(true);
        scrnMsg.xxDtlCnt_SM.setInputProtected(true);
        scrnMsg.xxDtlCnt_TL.setInputProtected(true);
        scrnMsg.xxDtlCnt_TR.setInputProtected(true);
        scrnMsg.xxDtlCnt_TS.setInputProtected(true);
        scrnMsg.cpoOrdNum.setInputProtected(true);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1680_ABMsg scrnMsgA = scrnMsg.A.no(i);
            scrnMsgA.mdlDescTxt_A.setInputProtected(true);
            scrnMsgA.xxDtlCnt_AL.setInputProtected(true);
            scrnMsgA.xxDtlCnt_AR.setInputProtected(true);
            scrnMsgA.xxDtlCnt_AS.setInputProtected(true);
        }
    }
}
