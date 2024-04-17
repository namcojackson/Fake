/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1120.common;

import static business.servlet.NSAL1120.constant.NSAL1120Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDApplicationContext;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NSAL1120.NSAL1120CMsg;
import business.servlet.NSAL1120.NSAL1120BMsg;
import business.servlet.NSAL1120.NSAL1120_CBMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.fujitsu.uji.http.HttpDispatchContext;

/**
 *<pre>
 * Meter Reading Counter for Interface Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         O.Okuma         Create          N/A
 * 2016/03/17   Hitachi         O.Okuma         Update          QC5422
 * 2016/04/18   Hitachi         K.Yamada        Update          CSA QC#7056
 * 2016/04/21   Hitachi         K.Yamada        Update          CSA QC#7251
 * 2016/05/11   Hitachi         T.Aoyagi        Update          QC#7734
 * 2017/09/15   Hitachi         E.Kameishi      Update          QC#18636
 * 2018/07/19   Hitachi         K.Kojima        Update          QC#26791
 * 2018/08/27   Hitachi         K.Kishimoto     Update          QC#24555
 * 2022/04/04   CITS            E.Sanchez       Update          QC#59914
 *</pre>
 */
public class NSAL1120CommonLogic {
    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }
    /**
     * createCMsgForSearch
     * @return NSAL1120CMsg
     */
    public static NSAL1120CMsg createCMsgForSearch() {
        NSAL1120CMsg bizMsg = new NSAL1120CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        return bizMsg;
    }
    /**
     * createCMsgForUpdate
     * @return NSAL1120CMsg
     */
    public static NSAL1120CMsg createCMsgForUpdate() {
        NSAL1120CMsg bizMsg = new NSAL1120CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_UPD);
        return bizMsg;
    }
    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1120BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL1120BMsg scrnMsg) {
        ArrayList<String> functionList = (ArrayList<String>) getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);

        activateButtons(handler, functionList);

        // START 05/11/2016 T.Aoyagi [QC#7734, MOD]
//        if (!SVC_CR_REBIL_STS.ENTERED.equals(scrnMsg.svcCrRebilStsCd.getValue())) {
        if (hasValue(scrnMsg.svcCrRebilStsCd) && !SVC_CR_REBIL_STS.ENTERED.equals(scrnMsg.svcCrRebilStsCd.getValue())) {
        // END 05/11/2016 T.Aoyagi [QC#7734, MOD]
            protectedScreenFields(handler, scrnMsg);
        } else {
            controlScreenFields(scrnMsg, false, handler);
        }
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

        if (functionList == null || functionList.isEmpty()) {
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else if (functionList.contains(FUNC_ID_EDT)) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else if (functionList.contains(FUNC_ID_INQ)) {
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
    }

    /**
     * controlScreenFields
     * @param scrnMsg NSAL1120BMsg
     * @param protectedFlg boolean
     */
    public static void controlScreenFields(NSAL1120BMsg scrnMsg, boolean protectedFlg, EZDCommonHandler handler) {

        scrnMsg.custIncdtId.setInputProtected(true);
        scrnMsg.svcCrRebilStsDescTxt.setInputProtected(true);
        scrnMsg.svcCrRebilDescTxt.setInputProtected(true);

        if (MODE_CODE_USAGE.equals(scrnMsg.xxModeCd.getValue())) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.B.no(i).serNum_B.setInputProtected(true);
                scrnMsg.B.no(i).mtrLbDescTxt_B.setInputProtected(true);
                scrnMsg.B.no(i).newStartReadMtrCnt_B.setInputProtected(protectedFlg);
                scrnMsg.B.no(i).newEndReadMtrCnt_B.setInputProtected(protectedFlg);
                //START 2017/09/15 E.Kameishi [QC#18636,MOD]
                scrnMsg.B.no(i).newTestMtrCnt_B.setInputProtected(protectedFlg);
//                scrnMsg.B.no(i).newStartTestMtrCnt_B.setInputProtected(protectedFlg);
//                scrnMsg.B.no(i).newEndTestMtrCnt_B.setInputProtected(protectedFlg);
                //END 2017/09/15 E.Kameishi [QC#18636,MOD]
                // add start 2016/04/21 CSA Defect#7251
                // START 2018/07/19 K.Kojima [QC#26791,DEL]
                // if (!hasValue(scrnMsg.B.no(i).bllgMtrLbCd_B)) {
                //     scrnMsg.B.no(i).xxChkBox_B.setInputProtected(true);
                // }
                // END 2018/07/19 K.Kojima [QC#26791,DEL]
                // add end 2016/04/21 CSA Defect#7251
            }

            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                scrnMsg.C.no(i).oldMtrChrgDealAmt_C.setAppFracDigit(FRAC_DIGIT_TWO);
                scrnMsg.C.no(i).oldXsMtrAmtRate_C.setAppFracDigit(FRAC_DIGIT_SIX);
                scrnMsg.C.no(i).newXsMtrAmtRate_C.setAppFracDigit(FRAC_DIGIT_SIX);
                scrnMsg.C.no(i).xxScrItem40Txt_C.setInputProtected(true);
                scrnMsg.C.no(i).mtrLbDescTxt_C.setInputProtected(true);
                if (DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.dsContrCatgCd.getValue())) {
                    // mod start 2016/04/18 CSA Defect#7056
                    if (hasValue(scrnMsg.C.no(i).serNum_C)) {
                        scrnMsg.C.no(i).newXsCopyQty_C.setInputProtected(false);
                        scrnMsg.C.no(i).newXsMtrAmtRate_C.setInputProtected(true);
                        // START 2018/08/27 [QC#24555, ADD]
                        scrnMsg.C.no(i).newUnitXsCopyQty_C.setInputProtected(false);
                        // END   2018/08/27 [QC#24555, ADD]
                    } else {
                        scrnMsg.C.no(i).newXsCopyQty_C.setInputProtected(true);
                        scrnMsg.C.no(i).newXsMtrAmtRate_C.setInputProtected(false);
                        // START 2018/08/27 [QC#24555, ADD]
                        scrnMsg.C.no(i).newUnitXsCopyQty_C.setInputProtected(true);
                        // END   2018/08/27 [QC#24555, ADD]
                    }
                    // mod end 2016/04/18 CSA Defect#7056
                } else {
                    controlScreenFieldsForPricing(scrnMsg.C.no(i), protectedFlg);
                }
            }
            // add start 2016/04/14 CSA Defect#5260
            if (DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
                handler.setButtonEnabled(BTN_REVIEW, false);
            }
            // add end 2016/04/14 CSA Defect#5260
        } else if (MODE_CODE_BASE.equals(scrnMsg.xxModeCd.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).oldBaseDealAmt_A.setAppFracDigit(FRAC_DIGIT_TWO);
                scrnMsg.A.no(i).newBaseDealAmt_A.setAppFracDigit(FRAC_DIGIT_TWO);
                // START 2018/08/27 [QC#24555, ADD]
                scrnMsg.A.no(i).oldBaseUnitAmt_A.setAppFracDigit(FRAC_DIGIT_TWO);
                scrnMsg.A.no(i).newBaseUnitAmt_A.setAppFracDigit(FRAC_DIGIT_TWO);
                scrnMsg.A.no(i).newBaseUnitAmt_A.setInputProtected(protectedFlg);
                // END   2018/08/27 [QC#24555, ADD]
                scrnMsg.A.no(i).xxScrItem40Txt_A.setInputProtected(true);
                scrnMsg.A.no(i).newBaseDealAmt_A.setInputProtected(protectedFlg);
                // START 2022/04/04 [QC#59914,ADD]
                scrnMsg.A.no(i).mdseDescShortTxt_A.setInputProtected(true);
                // END 2022/04/04 [QC#59914,ADD]
            }
        }
    }

    /**
     * controlScreenFieldsForPricing
     * @param cbMsg NSAL1120_CBMsg
     * @param isProtectedFlg boolean 
     */
    private static void controlScreenFieldsForPricing(NSAL1120_CBMsg cbMsg, boolean isProtectedFlg) {
        cbMsg.newXsCopyQty_C.setInputProtected(isProtectedFlg);
        cbMsg.newXsMtrAmtRate_C.setInputProtected(isProtectedFlg);
        // START 2018/08/27 [QC#24555, ADD]
        cbMsg.newUnitXsCopyQty_C.setInputProtected(isProtectedFlg);
        // END   2018/08/27 [QC#24555, ADD]
    }

    /**
     * protectedScreenFields
     * @param scrnMsg NSAL1120BMsg
      */
     private static void protectedScreenFields(EZDCommonHandler handler, NSAL1120BMsg scrnMsg) {

         controlScreenFields(scrnMsg, true, handler);
         handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
     }

     /**
     * addCheckItem
     * @param scrnMsg NSAL1120BMsg
     */
    public static void addCheckItem(NSAL1120BMsg scrnMsg) {

        if (MODE_CODE_BASE.equals(scrnMsg.xxModeCd.getValue())) {
            addCheckItemForBase(scrnMsg);
        } else {
            addCheckItemForMeter(scrnMsg);
            addCheckItemForPricing(scrnMsg);
        }
    }

    /**
    * addCheckItem
    * @param ctx EZDApplicationContext
    * @param scrnMsg NSAL1120BMsg
    */
   public static void addCheckItemForPagenation(EZDApplicationContext ctx, NSAL1120BMsg scrnMsg) {

       String tableId = getTableId(ctx);

       if (TABLE_A.equals(tableId)) {
           addCheckItemForBase(scrnMsg);
       } else if (TABLE_B.equals(tableId)) {
           addCheckItemForMeter(scrnMsg);
       } else if (TABLE_C.equals(tableId)) {
           addCheckItemForPricing(scrnMsg);
       }
   }

   /**
    * addCheckItemForBase
    * @param scrnMsg NSAL1120BMsg
    */
   private static void addCheckItemForBase(NSAL1120BMsg scrnMsg) {

       for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
           scrnMsg.addCheckItem(scrnMsg.A.no(i).newBaseDealAmt_A);
           // START 2018/08/27 [QC#24555, ADD]
           scrnMsg.addCheckItem(scrnMsg.A.no(i).newBaseUnitAmt_A);
           // END   2018/08/27 [QC#24555, ADD]
       }
   }

   /**
   * addCheckItemForBase
   * @param scrnMsg NSAL1120BMsg
   */
  public static void addCheckItemForMeter(NSAL1120BMsg scrnMsg) {

      for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
          scrnMsg.addCheckItem(scrnMsg.B.no(i).newStartReadMtrCnt_B);
          scrnMsg.addCheckItem(scrnMsg.B.no(i).newEndReadMtrCnt_B);
          //START 2017/09/15 E.Kameishi [QC#18636,MOD]
          scrnMsg.addCheckItem(scrnMsg.B.no(i).newTestMtrCnt_B);
//          scrnMsg.addCheckItem(scrnMsg.B.no(i).newStartTestMtrCnt_B);
//          scrnMsg.addCheckItem(scrnMsg.B.no(i).newEndTestMtrCnt_B);
          //END 2017/09/15 E.Kameishi [QC#18636,MOD]
      }
  }

  /**
  * addCheckItemForBase
  * @param scrnMsg NSAL1120BMsg
  */
 private static void addCheckItemForPricing(NSAL1120BMsg scrnMsg) {

     for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
         scrnMsg.addCheckItem(scrnMsg.C.no(i).newXsCopyQty_C);
         scrnMsg.addCheckItem(scrnMsg.C.no(i).newXsMtrAmtRate_C);
         // START 2018/08/27 [QC#24555, ADD]
         scrnMsg.addCheckItem(scrnMsg.C.no(i).newUnitXsCopyQty_C);
         // END   2018/08/27 [QC#24555, ADD]
     }
 }

  /**
    * setRowColors
    * @param scrnMsg NSAL1120BMsg
    */
   private static void setRowColors(NSAL1120BMsg scrnMsg) {

       S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
       tblColor.setAlternateRowsBG("A", scrnMsg.A);
       tblColor.setAlternateRowsBG("B", scrnMsg.B);
       tblColor.setAlternateRowsBG("C", scrnMsg.C);
   }

   /**
    * getTableId
    * @param ctx EZDApplicationContext
    * @return String
    */
   public static String getTableId(EZDApplicationContext ctx) {
       String rtnVal = "";
       String tblNm = (String) (((HttpDispatchContext) ctx.getDispatchContext()).getParameters().get("xxPagenationTableNm"))[0];
       if (TABLE_A.equals(tblNm)) {
           rtnVal = TABLE_A;
       } else if (TABLE_B.equals(tblNm)) {
           rtnVal = TABLE_B;
       } else {
           rtnVal = TABLE_C;
       }
       return rtnVal;
   }

}

