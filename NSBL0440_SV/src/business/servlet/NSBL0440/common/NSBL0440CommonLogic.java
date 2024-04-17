/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0440.common;

import java.util.ArrayList;
import java.util.List;

import static business.servlet.NSBL0440.constant.NSBL0440Constant.*;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSBL0440.NSBL0440BMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MOD_PROC_STS;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Meter Reading Counter for Interface Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Hitachi         O.Okuma         Create          N/A
 * 2016/04/18   Hitachi         M.Gotou         Update          QC#3425
 * 2016/06/10   Hitachi         M.Gotou         Update          QC#8911
 * 2016/07/14   Hitachi         O.Okuma         Update          QC#11647
 * 2018/02/02   Hitachi         M.Kidokoro      Update          QC#18150
 * 2019/03/22   Hitachi         K.Kitamura      Update          QC#30815
 * 2023/01/16   Hitachi         S.Dong          Update          QC#60922
 *</pre>
 */
public class NSBL0440CommonLogic {
    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }
    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0440BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSBL0440BMsg scrnMsg) {
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
        // add start 2016/04/15 CSA Defect#3425
        handler.setButtonEnabled(BTN_FILTER, false);
        // add end 2016/04/15 CSA Defect#3425
        handler.setButtonEnabled(BTN_MDF_SRCH, false);
        handler.setButtonEnabled(BTN_MDF_DTL, false);
        handler.setButtonEnabled(BTN_SRL_ASSN, false);

        if (functionList == null || functionList.isEmpty()) {
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else if (functionList.contains(FUNC_ID_EDT)) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
            // START 2018/02/02 M.Kidokoro [QC#18150, ADD]
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);
            // END 2018/02/02 M.Kidokoro [QC#18150, ADD]
            // add start 2016/04/15 CSA Defect#3425
            handler.setButtonEnabled(BTN_FILTER, true);
            // add end 2016/04/15 CSA Defect#3425
            handler.setButtonEnabled(BTN_MDF_SRCH, true);
            handler.setButtonEnabled(BTN_MDF_DTL, true);
            handler.setButtonEnabled(BTN_SRL_ASSN, true);
        } else if (functionList.contains(FUNC_ID_INQ)) {
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
            // add start 2016/04/15 CSA Defect#3425
            handler.setButtonEnabled(BTN_FILTER, true);
            // add end 2016/04/15 CSA Defect#3425
            handler.setButtonEnabled(BTN_MDF_SRCH, true);
            handler.setButtonEnabled(BTN_MDF_DTL, true);
            handler.setButtonEnabled(BTN_SRL_ASSN, true);
        }
    }

    /**
     * activateScreenItems
     * @param scrnMsg NSBL0440BMsg
     */
    public static void controlScreenFields(NSBL0440BMsg scrnMsg) {

        scrnMsg.svcModPlnId.setInputProtected(true);
        scrnMsg.svcModNm.setInputProtected(true);
        // START 2023/01/16 S.Dong [QC#60922, DEL]
//        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//            if (scrnMsg.A.no(i).svcModProcStsCd_A.getValue().equals(SVC_MOD_PROC_STS.CANCELLED)
//             || scrnMsg.A.no(i).svcModProcStsCd_A.getValue().equals(SVC_MOD_PROC_STS.CLOSED)
//             // START 2019/03/22 S.Kitamura [QC#30815, ADD]
//             || scrnMsg.A.no(i).svcModProcStsDescTxt_A.getValue().equals("Cancel")
//             // END 2019/03/22 S.Kitamura [QC#30815, ADD]
//             // del start 2016/07/14 CSA Defect#11647
//             // add start 2016/06/10 CSA Defect#8911
//             // || !hasValue(scrnMsg.A.no(i).svcModStsPk_A)
//             ) {
//             // add end 2016/06/10 CSA Defect#8911
//             // del end 2016/07/14 CSA Defect#11647
//                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
//                scrnMsg.A.no(i).svcModOptCd_A.setInputProtected(true);
//                scrnMsg.A.no(i).svcModOptDt_A.setInputProtected(true);
//                scrnMsg.A.no(i).svcModNoteTxt_A.setInputProtected(true);
//            } else {
//                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
//                scrnMsg.A.no(i).svcModOptCd_A.setInputProtected(false);
//                scrnMsg.A.no(i).svcModOptDt_A.setInputProtected(false);
//                scrnMsg.A.no(i).svcModNoteTxt_A.setInputProtected(false);
//            }
//        }
        // END 2023/01/16 S.Dong [QC#60922, DEL]
    }

   /**
    * addCheckItem
    * @param scrnMsg NSBL0440BMsg
    */
   public static void addCheckItem(NSBL0440BMsg scrnMsg) {

       for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
           scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModOptCd_A);
           scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModOptDt_A);
           scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModNoteTxt_A);
           scrnMsg.addCheckItem(scrnMsg.A);
       }
   }

   /**
    * setRowColors
    * @param scrnMsg NSBL0440BMsg
    */
   private static void setRowColors(NSBL0440BMsg scrnMsg) {

       S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
       try {
           EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
           tblColor.setAlternateRowsBG("A", table);
       } catch (Throwable e) {
           e.printStackTrace();
           throw new RuntimeException(e);
       }
   }
}

