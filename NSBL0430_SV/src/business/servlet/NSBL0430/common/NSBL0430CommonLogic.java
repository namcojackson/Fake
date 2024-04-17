/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0430.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import java.util.ArrayList;
import java.util.List;

import static business.servlet.NSBL0430.constant.NSBL0430Constant.*;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSBL0430.NSBL0430BMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Meter Reading Counter for Interface Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/28   Hitachi         O.Okuma         Create          N/A
 * 2016/04/18   Hitachi         M.Gotou         Update          QC#3425
 * 2016/07/11   Hitachi         O.Okuma         Update          QC#11645
 * 2016/07/12   Hitachi         O.Okuma         Update          QC#11678,11685
 * 2017/02/15   Hitachi         N.Arai          Update          QC#17562
 *</pre>
 */
public class NSBL0430CommonLogic {
    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }
    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0430BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSBL0430BMsg scrnMsg) {
        ArrayList<String> functionList = (ArrayList<String>) getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);

        activateButtons(handler, functionList);
        controlScreenFields(scrnMsg);
        setRowColors(scrnMsg);
    }

    /**
     * activateButtons
     * @param handler S21CommonHandler
     * @param functionList  List<String>
     */
    private static void activateButtons(EZDCommonHandler handler, List<String> functionList) {

        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 0, null);
        handler.setButtonEnabled(BTN_MDF_SRCH, false);
        handler.setButtonEnabled(BTN_MDF_DTL, false);
        handler.setButtonEnabled(BTN_MCN_STTS, false);
        // add start 2016/04/15 CSA Defect#3425
        handler.setButtonEnabled(BTN_FILTER, false);
        // add end 2016/04/15 CSA Defect#3425
        handler.setButtonEnabled(BTN_ADD, false);
        handler.setButtonEnabled(BTN_DELETE, false);
        handler.setButtonEnabled(BTN_UPLOAD, false);

        if (functionList == null || functionList.isEmpty()) {
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else if (functionList.contains(FUNC_ID_EDT)) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
            // add start 2016/07/12 CSA Defect#11678
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            // add end 2016/07/12 CSA Defect#11678
            handler.setButtonEnabled(BTN_MDF_SRCH, true);
            handler.setButtonEnabled(BTN_MDF_DTL, true);
            handler.setButtonEnabled(BTN_MCN_STTS, true);
            // add start 2016/04/15 CSA Defect#3425
            handler.setButtonEnabled(BTN_FILTER, true);
            // add end 2016/04/15 CSA Defect#3425
            handler.setButtonEnabled(BTN_ADD, true);
            handler.setButtonEnabled(BTN_DELETE, true);
            handler.setButtonEnabled(BTN_UPLOAD, true);
        } else if (functionList.contains(FUNC_ID_INQ)) {
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
            // add start 2016/07/12 CSA Defect#11678
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            // add end 2016/07/12 CSA Defect#11678
            handler.setButtonEnabled(BTN_MDF_SRCH, true);
            handler.setButtonEnabled(BTN_MDF_DTL, true);
            handler.setButtonEnabled(BTN_MCN_STTS, true);
            // add start 2016/04/15 CSA Defect#3425
            handler.setButtonEnabled(BTN_FILTER, true);
            // add end 2016/04/15 CSA Defect#3425
            handler.setButtonEnabled(BTN_ADD, false);
            handler.setButtonEnabled(BTN_DELETE, false);
            handler.setButtonEnabled(BTN_UPLOAD, false);
       }
    }

    /**
     * activateScreenItems
     * @param scrnMsg NSBL0430BMsg
     */
    public static void controlScreenFields(NSBL0430BMsg scrnMsg) {

        scrnMsg.svcModPlnId.setInputProtected(true);
        scrnMsg.svcModNm.setInputProtected(true);
        scrnMsg.svcModCmntTxt.setInputProtected(true);
        scrnMsg.xxEndDplyTmTxt.setInputProtected(true);

        // add start 2016/03/28 CSA Defect#5410
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxEndDplyTmTxt, setTm(scrnMsg.xxEndDplyTmTxt.getValue()));
        // add end 2016/03/28 CSA Defect#5410

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if ((hasValue(scrnMsg.A.no(i).svcModCancDt_A) && ZYPDateUtil.isPastDate(scrnMsg.A.no(i).svcModCancDt_A.getValue()))
                    || (hasValue(scrnMsg.A.no(i).svcModObslDt_A) && ZYPDateUtil.isPastDate(scrnMsg.A.no(i).svcModObslDt_A.getValue()))) {

                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
                scrnMsg.A.no(i).svcModFromSerNum_A.setInputProtected(true);
                scrnMsg.A.no(i).svcModToSerNum_A.setInputProtected(true);
                scrnMsg.A.no(i).autoCratCallFlg_A.setInputProtected(true);
                scrnMsg.A.no(i).autoCratRfrsTmgCd_A.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
                scrnMsg.A.no(i).svcModFromSerNum_A.setInputProtected(false);
                scrnMsg.A.no(i).svcModToSerNum_A.setInputProtected(false);
                scrnMsg.A.no(i).autoCratRfrsTmgCd_A.setInputProtected(false);

                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).svcModAutoCratMndFlg_A.getValue())) {
                    scrnMsg.A.no(i).autoCratCallFlg_A.setInputProtected(true);
                } else {
                    scrnMsg.A.no(i).autoCratCallFlg_A.setInputProtected(false);
                }
            }
        }
    }

    /**
     * addCheckItem
     * @param scrnMsg NSBL0430BMsg
     */
    public static void addCheckItem(NSBL0430BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.mdseCd);
    }

    /**
    * addCheckItemDtl
    * @param scrnMsg NSBL0430BMsg
    */
   public static void addCheckItemDtl(NSBL0430BMsg scrnMsg) {

       for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
           scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
           scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModFromSerNum_A);
           scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModToSerNum_A);
           scrnMsg.addCheckItem(scrnMsg.A.no(i).autoCratCallFlg_A);
           scrnMsg.addCheckItem(scrnMsg.A.no(i).autoCratRfrsTmgCd_A);
           scrnMsg.addCheckItem(scrnMsg.A);
       }
   }

   /**
    * setRowColors
    * @param scrnMsg NSBL0430BMsg
    */
   private static void setRowColors(NSBL0430BMsg scrnMsg) {

       S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
       try {
           EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
           tblColor.setAlternateRowsBG("A", table);
       } catch (Throwable e) {
           e.printStackTrace();
           throw new RuntimeException(e);
       }
   }

   // add start 2016/03/28 CSA Defect#5410
   /**
    * set Time
    * @param tm time
    * @return time
    */
   public static String setTm(String tm) {
       // mod start 2016/07/11 CSA Defect#11645
       if (ZYPCommonFunc.hasValue(tm) && tm.indexOf(":") == -1) {
       // mod end 2016/07/11 CSA Defect#11645
           StringBuffer strBuf = new StringBuffer();
           strBuf.append(tm);
           if (strBuf.length() > 2) {
               strBuf.insert(2, ":");
           }
           return strBuf.toString();
       }
       return tm;
   }
   // add end 2016/03/28 CSA Defect#5410

   // add start 2016/07/12 CSA Defect#11685
   /**
   * addCheckItemDtl
   * @param scrnMsg NSBL0430BMsg
   */
  public static void setFocus(NSBL0430BMsg scrnMsg) {

      if (scrnMsg.A.getValidCount() > 0) {
          scrnMsg.setFocusItem(scrnMsg.A.no(0).svcModFromSerNum_A);
      } else {
          scrnMsg.setFocusItem(scrnMsg.mdseCd);
      }
  }
  // add end 2016/07/12 CSA Defect#11685

//START 2017/02/15 N.Arai [QC#17562, MOD]
  /**
  * validationMandatory
  * @param scrnMsg NSBL0430BMsg
  */
 public static void validationMandatory(NSBL0430BMsg scrnMsg) {

     for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
         if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).autoCratCallFlg_A.getValue()) && !hasValue(scrnMsg.A.no(i).autoCratRfrsTmgCd_A)) {
             scrnMsg.A.no(i).autoCratRfrsTmgCd_A.setErrorInfo(1, ZZM9000E, new String[]{"Auto Create Refresh Rate"});
         }
     }
 }
//END 2017/02/15 N.Arai [QC#17562, MOD]
}

