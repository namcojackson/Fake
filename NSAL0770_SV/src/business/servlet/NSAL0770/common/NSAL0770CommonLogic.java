/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0770.common;
import static business.servlet.NSAL0770.constant.NSAL0770Constant.*;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import business.servlet.NSAL0770.NSAL0770BMsg;
import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;


/**
*<pre>
* Change Status Audit
* 
* Date         Company         Name            Create/Update   Defect No
* ----------------------------------------------------------------------
* 2015/11/06   Hitachi         K.Kishimoto     Create          N/A
*</pre>
*/
public class NSAL0770CommonLogic {
   /**
    * setRowColors
    * @param scrnMsg NSAL0770BMsg
    */
   public static void setRowColors(NSAL0770BMsg scrnMsg) {

       S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
       try {
           EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
           tblColor.setAlternateRowsBG("A", table);
       } catch (Throwable e) {
           e.printStackTrace();
           throw new RuntimeException(e);
       }
   }

   /**
    * itemProtect
    * @param scrnMsg NSAL0770BMsg
    */
   public static void itemProtect(NSAL0770BMsg scrnMsg) {
       for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
           scrnMsg.A.no(i).dsContrStsNm_A1.setInputProtected(true);
           scrnMsg.A.no(i).dsContrStsNm_A2.setInputProtected(true);
           scrnMsg.A.no(i).dsContrTrkRsnDescTxt.setInputProtected(true);
           scrnMsg.A.no(i).stsMemoTxt.setInputProtected(true);
           scrnMsg.A.no(i).stsMemoUpdFullPsnNm.setInputProtected(true);
       }
   }
   /**
    * initControlCommonButton
    * @param scrnAppli EZDCommonHandler
    */
   public static void initControlCommonButton(EZDCommonHandler scrnAppli) {

       scrnAppli.setButtonProperties("btn1", "", BTN_LBL.SAVE.getBtnLbl(), 0, null);
       scrnAppli.setButtonProperties("btn2", "", BTN_LBL.SUBMIT.getBtnLbl(), 0, null);
       scrnAppli.setButtonProperties("btn3", "", BTN_LBL.APPLY.getBtnLbl(), 0, null);
       scrnAppli.setButtonProperties("btn4", "", BTN_LBL.APPROVE.getBtnLbl(), 0, null);
       scrnAppli.setButtonProperties("btn5", "", BTN_LBL.REJECT.getBtnLbl(), 0, null);
       scrnAppli.setButtonProperties("btn6", "", BTN_LBL.DOWNLOAD.getBtnLbl(), 0, null);
       scrnAppli.setButtonProperties("btn7", "", BTN_LBL.DELETE.getBtnLbl(), 0, null);
       scrnAppli.setButtonProperties("btn8", "CMN_Clear", BTN_LBL.CLEAR.getBtnLbl(), 0, null);
       scrnAppli.setButtonProperties("btn9", "", BTN_LBL.RESET.getBtnLbl(), 0, null);
       scrnAppli.setButtonProperties("btn10", "CMN_Close", BTN_LBL.CLOSE.getBtnLbl(), 0, null);

   }
   /**
    * initCommonButton
    * @param scrnAppli EZDCommonHandler
    */
   public static void initCommonButton(EZDCommonHandler scrnAppli) {

       scrnAppli.setButtonEnabled("btn8", true);
       scrnAppli.setButtonEnabled("btn10", true);
   }

}
