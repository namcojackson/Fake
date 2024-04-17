/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0150.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDGUIAttribute;
import business.servlet.NSAL0150.NSAL0150BMsg;
import business.servlet.NSAL0150.NSAL0150Bean;
import business.servlet.NSAL0150.NSAL0150_BBMsg;
import business.servlet.NSAL0150.constant.NSAL0150Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/03   CUSA            SRAA            Create          N/A
 * 2015/11/26   Hitachi         K.Kasai         Update          Unit Test #71
 * 2015/12/10   Hitachi         K.Kasai         Update          QC1746
 * 2015/12/16   Hitachi         K.Kasai         Update          QC2070
 * 2016/03/22   Hitachi         M.Gotou         Update          QC4629
 * 2016/03/24   Hitachi         A.Kohinata      Update          QC#5808
 * 2016/03/28   Hitachi         M.Gotou         Update          QC#4397
 * 2016/03/28   Hitachi         M.Gotou         Update          QC#4398
 * 2016/03/29   Hitachi         T.Tomita        Update          QC#4393, QC#4396
 * 2016/06/10   Hitachi         Y.Tsuchimoto    Update          QC#9591
 * 2016/06/29   Hitachi         M.Gotou         Update          QC#10532
 * 2016/09/07   Hitachi         T.Kanasaka      Update          QC#2870
 * 2016/10/03   Hitachi         K.Kishimoto     Update          QC#12274
 * 2017/09/07   Hitachi         A.Kohinata      Update          QC#15134
 * 2017/12/13   Hitachi         M.Kidokoro      Update          QC#21681
 * 2018/02/19   Hitachi         M.Kidokoro      Update          QC#23753
 * 2018/03/08   Hitachi         K.Kojima        Update          QC#24507
 * 2018/06/01   Hitachi         U.Kim           Update          QC#22480
 * 2018/06/21   CITS            T.Wada          Update          QC#25419
 * 2018/06/25   Fujitsu         T.Ogura         Update          QC#26336
 * 2018/07/09   Hitachi         A.Kohinata      Update          QC#26923
 * 2018/07/30   Hitachi         K.Kitachi       Update          QC#26874
 * 2019/03/14   Hitachi         S.Kitamura      Update          QC#30744
 * 2019/04/09   Hitachi         K.Kitachi       Update          QC#31089
 * 2020/01/06   Hitachi         Y.Takeno        Update          QC#55286
 * 2020/03/03   Hitachi         K.Kishimoto     Update          QC#56123
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 * 2024/01/25   Hitachi         T.Kawasue       Update          QC#63467
 * 2024/04/08   Hitachi         S.Moriai        Update          QC#63540
 *</pre>
 */
public class NSAL0150CommonLogic {

    /**
     * Activate buttons
     * @param handler S21CommonHandler
     * @param scrnMsg NSAL0150BMsg
     * @param functionList List<String>
     */
    public static void activateButtons(S21CommonHandler handler, NSAL0150BMsg scrnMsg, List<String> functionList) {
        if (functionList == null || functionList.isEmpty()) {
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_1[0], NSAL0150Constant.BTN_CMN_BTN_1[1], NSAL0150Constant.BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_SUBMIT[0], NSAL0150Constant.BTN_CMN_SUBMIT[1], NSAL0150Constant.BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_3[0], NSAL0150Constant.BTN_CMN_BTN_3[1], NSAL0150Constant.BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_4[0], NSAL0150Constant.BTN_CMN_BTN_4[1], NSAL0150Constant.BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_5[0], NSAL0150Constant.BTN_CMN_BTN_5[1], NSAL0150Constant.BTN_CMN_BTN_5[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_6[0], NSAL0150Constant.BTN_CMN_BTN_6[1], NSAL0150Constant.BTN_CMN_BTN_6[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_7[0], NSAL0150Constant.BTN_CMN_BTN_7[1], NSAL0150Constant.BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_8[0], NSAL0150Constant.BTN_CMN_BTN_8[1], NSAL0150Constant.BTN_CMN_BTN_8[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_RESET[0], NSAL0150Constant.BTN_CMN_RESET[1], NSAL0150Constant.BTN_CMN_RESET[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_RETURN[0], NSAL0150Constant.BTN_CMN_RETURN[1], NSAL0150Constant.BTN_CMN_RETURN[2], 1, null);
            handler.setButtonEnabled(NSAL0150Constant.BTN_REGISTER, false);
            handler.setButtonEnabled(NSAL0150Constant.BTN_DEREGISTER, false);
            handler.setButtonEnabled(NSAL0150Constant.BTN_ORDER_SUPPLIES, false);
	        // START 2016/03/29 M.Gotou [QC#10532, ADD]
            handler.setButtonEnabled(NSAL0150Constant.BTN_SCHD_AGREE, false);
	        // END 2016/03/29 M.Gotou [QC#10532, ADD]
            handler.setButtonEnabled(NSAL0150Constant.BTN_ORDER_HISTORY, false);
            handler.setButtonEnabled(NSAL0150Constant.BTN_ESTIMATE, false);
            // MOD START 2015/11/26 K.Kasai [Unit Test #71]
            // } else if
            // (functionList.contains(NSAL0150Constant.FUNC_ID_UPD)) {
        } else if (functionList.contains(NSAL0150Constant.FUNC_ID_BILL) || functionList.contains(NSAL0150Constant.FUNC_ID_SVC) || functionList.contains(NSAL0150Constant.FUNC_ID_SUPPLY)) {
            // MOD END 2015/11/26 K.Kasai [Unit Test #71]
            // Update
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_1[0], NSAL0150Constant.BTN_CMN_BTN_1[1], NSAL0150Constant.BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_SUBMIT[0], NSAL0150Constant.BTN_CMN_SUBMIT[1], NSAL0150Constant.BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_3[0], NSAL0150Constant.BTN_CMN_BTN_3[1], NSAL0150Constant.BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_4[0], NSAL0150Constant.BTN_CMN_BTN_4[1], NSAL0150Constant.BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_5[0], NSAL0150Constant.BTN_CMN_BTN_5[1], NSAL0150Constant.BTN_CMN_BTN_5[2], 0, null);
            // START 2018/06/25 T.Ogura [QC#26336,MOD]
//          handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_6[0], NSAL0150Constant.BTN_CMN_BTN_6[1], NSAL0150Constant.BTN_CMN_BTN_6[2], 0, null);
          handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_6[0], NSAL0150Constant.BTN_CMN_BTN_6[1], NSAL0150Constant.BTN_CMN_BTN_6[2], 1, null);
          // END   2018/06/25 T.Ogura [QC#26336,MOD]
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_7[0], NSAL0150Constant.BTN_CMN_BTN_7[1], NSAL0150Constant.BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_8[0], NSAL0150Constant.BTN_CMN_BTN_8[1], NSAL0150Constant.BTN_CMN_BTN_8[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_RESET[0], NSAL0150Constant.BTN_CMN_RESET[1], NSAL0150Constant.BTN_CMN_RESET[2], 1, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_RETURN[0], NSAL0150Constant.BTN_CMN_RETURN[1], NSAL0150Constant.BTN_CMN_RETURN[2], 1, null);
            // START 2016/06/10 [QC#9591, MOD]
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.iwrEnblFlg.getValue())) {
                handler.setButtonEnabled(NSAL0150Constant.BTN_REGISTER, true);
                handler.setButtonEnabled(NSAL0150Constant.BTN_DEREGISTER, true);
            } else {
                handler.setButtonEnabled(NSAL0150Constant.BTN_REGISTER, false);
                handler.setButtonEnabled(NSAL0150Constant.BTN_DEREGISTER, false);
            }
            // END   2016/06/10 [QC#9591, MOD]
            setBtnOrderSupplies(handler, scrnMsg);
	        // START 2016/03/29 M.Gotou [QC#10532, ADD]
            if (ZYPCommonFunc.hasValue(scrnMsg.schdAgmtNum)) {
                handler.setButtonEnabled(NSAL0150Constant.BTN_SCHD_AGREE, true);
                handler.setButtonEnabled(NSAL0150Constant.BTN_ORDER_SUPPLIES, false);
            } else {
                handler.setButtonEnabled(NSAL0150Constant.BTN_SCHD_AGREE, false);
            }
	        // END 2016/03/29 M.Gotou [QC#10532, ADD]
            handler.setButtonEnabled(NSAL0150Constant.BTN_ORDER_HISTORY, true);
            // START 2018/02/19 M.Kidokoro [QC#23753, MOD]
//            handler.setButtonEnabled(NSAL0150Constant.BTN_ESTIMATE, true);
            if (functionList.contains(NSAL0150Constant.FUNC_ID_BILL)) {
                handler.setButtonEnabled(NSAL0150Constant.BTN_ESTIMATE, true);
            } else {
                handler.setButtonEnabled(NSAL0150Constant.BTN_ESTIMATE, false);
            }
            // END 2018/02/19 M.Kidokoro [QC#23753, MOD]
            // ADD START 2015/11/26 K.Kasai [Unit Test #71]
        } else if (functionList.contains(NSAL0150Constant.FUNC_ID_ODR)) {
            // Inquiry
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_1[0], NSAL0150Constant.BTN_CMN_BTN_1[1], NSAL0150Constant.BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_SUBMIT[0], NSAL0150Constant.BTN_CMN_SUBMIT[1], NSAL0150Constant.BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_3[0], NSAL0150Constant.BTN_CMN_BTN_3[1], NSAL0150Constant.BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_4[0], NSAL0150Constant.BTN_CMN_BTN_4[1], NSAL0150Constant.BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_5[0], NSAL0150Constant.BTN_CMN_BTN_5[1], NSAL0150Constant.BTN_CMN_BTN_5[2], 0, null);
            // START 2018/06/25 T.Ogura [QC#26336,MOD]
//          handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_6[0], NSAL0150Constant.BTN_CMN_BTN_6[1], NSAL0150Constant.BTN_CMN_BTN_6[2], 0, null);
          handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_6[0], NSAL0150Constant.BTN_CMN_BTN_6[1], NSAL0150Constant.BTN_CMN_BTN_6[2], 1, null);
          // END   2018/06/25 T.Ogura [QC#26336,MOD]
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_7[0], NSAL0150Constant.BTN_CMN_BTN_7[1], NSAL0150Constant.BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_8[0], NSAL0150Constant.BTN_CMN_BTN_8[1], NSAL0150Constant.BTN_CMN_BTN_8[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_RESET[0], NSAL0150Constant.BTN_CMN_RESET[1], NSAL0150Constant.BTN_CMN_RESET[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_RETURN[0], NSAL0150Constant.BTN_CMN_RETURN[1], NSAL0150Constant.BTN_CMN_RETURN[2], 1, null);
            handler.setButtonEnabled(NSAL0150Constant.BTN_REGISTER, false);
            handler.setButtonEnabled(NSAL0150Constant.BTN_DEREGISTER, false);
            handler.setButtonEnabled(NSAL0150Constant.BTN_ORDER_SUPPLIES, false);
	        // START 2016/03/29 M.Gotou [QC#10532, ADD]
            handler.setButtonEnabled(NSAL0150Constant.BTN_SCHD_AGREE, false);
	        // END 2016/03/29 M.Gotou [QC#10532, ADD]
            handler.setButtonEnabled(NSAL0150Constant.BTN_ORDER_HISTORY, false);
            handler.setButtonEnabled(NSAL0150Constant.BTN_ESTIMATE, false);
            // ADD END 2015/11/26 K.Kasai [Unit Test #71]
        } else if (functionList.contains(NSAL0150Constant.FUNC_ID_INQ)) {
            // Inquiry
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_1[0], NSAL0150Constant.BTN_CMN_BTN_1[1], NSAL0150Constant.BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_SUBMIT[0], NSAL0150Constant.BTN_CMN_SUBMIT[1], NSAL0150Constant.BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_3[0], NSAL0150Constant.BTN_CMN_BTN_3[1], NSAL0150Constant.BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_4[0], NSAL0150Constant.BTN_CMN_BTN_4[1], NSAL0150Constant.BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_5[0], NSAL0150Constant.BTN_CMN_BTN_5[1], NSAL0150Constant.BTN_CMN_BTN_5[2], 0, null);
            // START 2018/06/25 T.Ogura [QC#26336,MOD]
//          handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_6[0], NSAL0150Constant.BTN_CMN_BTN_6[1], NSAL0150Constant.BTN_CMN_BTN_6[2], 0, null);
          handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_6[0], NSAL0150Constant.BTN_CMN_BTN_6[1], NSAL0150Constant.BTN_CMN_BTN_6[2], 1, null);
          // END   2018/06/25 T.Ogura [QC#26336,MOD]
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_7[0], NSAL0150Constant.BTN_CMN_BTN_7[1], NSAL0150Constant.BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_BTN_8[0], NSAL0150Constant.BTN_CMN_BTN_8[1], NSAL0150Constant.BTN_CMN_BTN_8[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_RESET[0], NSAL0150Constant.BTN_CMN_RESET[1], NSAL0150Constant.BTN_CMN_RESET[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_RETURN[0], NSAL0150Constant.BTN_CMN_RETURN[1], NSAL0150Constant.BTN_CMN_RETURN[2], 1, null);
            handler.setButtonEnabled(NSAL0150Constant.BTN_REGISTER, false);
            handler.setButtonEnabled(NSAL0150Constant.BTN_DEREGISTER, false);
            // START 2019/03/14 S.Kitamura [QC#30744, MOD]
            // setBtnOrderSupplies(handler, scrnMsg);
            //// START 2016/03/29 M.Gotou [QC#10532, ADD]
            // handler.setButtonEnabled(NSAL0150Constant.BTN_SCHD_AGREE, false);
            //// END 2016/03/29 M.Gotou [QC#10532, ADD]
            setBtnOrderSupplies(handler, scrnMsg);
            if (ZYPCommonFunc.hasValue(scrnMsg.schdAgmtNum)) {
                handler.setButtonEnabled(NSAL0150Constant.BTN_SCHD_AGREE, true);
                handler.setButtonEnabled(NSAL0150Constant.BTN_ORDER_SUPPLIES, false);
            } else {
                handler.setButtonEnabled(NSAL0150Constant.BTN_SCHD_AGREE, false);
            }
            // END 2019/03/14 S.Kitamura [QC#30744, MOD]
            handler.setButtonEnabled(NSAL0150Constant.BTN_ORDER_HISTORY, true);
            // mod start 2016/03/29 CSA Defect#4396
            handler.setButtonEnabled(NSAL0150Constant.BTN_ESTIMATE, false);
            // mod end 2016/03/29 CSA Defect#4396
        }
        // ADD START 2015/12/10 K.Kasai [QC1746]
        if (scrnMsg.B.getValidCount() == 0) {
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_SUBMIT[0], NSAL0150Constant.BTN_CMN_SUBMIT[1], NSAL0150Constant.BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(NSAL0150Constant.BTN_CMN_RESET[0], NSAL0150Constant.BTN_CMN_RESET[1], NSAL0150Constant.BTN_CMN_RESET[2], 0, null);
            handler.setButtonEnabled(NSAL0150Constant.BTN_REGISTER, false);
            handler.setButtonEnabled(NSAL0150Constant.BTN_DEREGISTER, false);
            handler.setButtonEnabled(NSAL0150Constant.BTN_ORDER_SUPPLIES, false);
	        // START 2016/03/29 M.Gotou [QC#10532, ADD]
            handler.setButtonEnabled(NSAL0150Constant.BTN_SCHD_AGREE, false);
	        // END 2016/03/29 M.Gotou [QC#10532, ADD]
            handler.setButtonEnabled(NSAL0150Constant.BTN_ORDER_HISTORY, false);
            handler.setButtonEnabled(NSAL0150Constant.BTN_ESTIMATE, false);
        }
        // ADD END 2015/12/10 K.Kasai [QC1746]
        // add start 2018/07/09 QC#26923
        if (ZYPCommonFunc.hasValue(handler.getButtonStatus(NSAL0150Constant.BTN_ORDER_SUPPLIES))) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_BT, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_BT, ZYPConstant.FLG_ON_Y);
        }
        // add end 2018/07/09 QC#26923
    }

    /**
     * Activate screen items
     * @param handler S21CommonHandler
     * @param functionList List<String>
     * @param scrnMsg NSAL0150BMsg
     */
    public static void activateScreenItems(S21CommonHandler handler, List<String> functionList, NSAL0150BMsg scrnMsg) {

        // Contract Detail
        scrnMsg.billToLocNm.setInputProtected(true);
        scrnMsg.shipToLocNm.setInputProtected(true);
        // MOD START 2015/11/26 K.Kasai [Unit Test #71]
        // scrnMsg.svcMachFlNm.setInputProtected(true);
        scrnMsg.xxGenlFldAreaTxt_CT.setInputProtected(true);
        scrnMsg.xxGenlFldAreaTxt_FC.setInputProtected(true);
        scrnMsg.xxGenlFldAreaTxt_LS.setInputProtected(true);
        scrnMsg.xxGenlFldAreaTxt_TL.setInputProtected(true);
        scrnMsg.xxGenlFldAreaTxt_FX.setInputProtected(true);
        scrnMsg.dsCtacPntValTxt_EM.setInputProtected(true);
        scrnMsg.addrline1L3000If.setInputProtected(true);
        scrnMsg.xxYesNoNm_MC.setInputProtected(true);
        scrnMsg.bllgSchdThruDt.setInputProtected(true);
        // START S.Moriai 2024/04/08 [QC#63540, ADD]
        scrnMsg.effFromDt.setInputProtected(false);
        scrnMsg.effThruDt.setInputProtected(false);
        // END S.Moriai 2024/04/08 [QC#63540, ADD]
// Mod Start 2016/12/06 <QC#14992-1>
//        scrnMsg.mtrReadMethCd.setInputProtected(true);
        scrnMsg.mtrReadMethDescTxt.setInputProtected(true);
// Mod End   2016/12/06 <QC#14992-1>
        // START 2019/04/09 K.Kitachi [QC#31089, MOD]
//        scrnMsg.svcCmntTxt.setInputProtected(true);
        if (!functionList.contains(NSAL0150Constant.FUNC_ID_BILL)) {
            scrnMsg.svcCmntTxt.setInputProtected(true);
        }
        // END 2019/04/09 K.Kitachi [QC#31089, MOD]
        // MOD END 2015/11/26 K.Kasai [Unit Test #71]

        // Product Detail
        // START 2016/03/28 M.Gotou [QC#4397, MOD]
//        scrnMsg.dsContrNum.setInputProtected(true);
        scrnMsg.dsContrNum.setInputProtected(false);
        // END 2016/03/28 M.Gotou [QC#4397, MOD]
        // START 2024/01/25 T.Kawasue [QC#63467, ADD]
        scrnMsg.dsContrCtrlStsNm.setInputProtected(true);
        // END 2024/01/25 T.Kawasue [QC#63467, ADD]
        // ADD START 2015/11/26 K.Kasai [Unit Test #71]
        scrnMsg.dsSubContrPk.setInputProtected(true);
        scrnMsg.xxCoaBrSrchTxt.setInputProtected(true);
        // START 2016/09/07 T.Kanasaka [QC#2870, MOD]
//        scrnMsg.vndNm.setInputProtected(true);
        scrnMsg.prntVndNm.setInputProtected(true);
        // END 2016/09/07 T.Kanasaka [QC#2870, MOD]
        scrnMsg.splyContrChkFlg.setInputProtected(true);
        // ADD END 2015/11/26 K.Kasai [Unit Test #71]
        // START 2018/06/11 U.Kim [QC#22480, ADD]
        scrnMsg.splyInclFlg.setInputProtected(true);
        // END 2018/06/11 U.Kim [QC#22480, ADD]
        scrnMsg.mdlNm.setInputProtected(true);
        // START 2016/03/28 M.Gotou [QC#4398, MOD]
//        scrnMsg.serNum.setInputProtected(true);
        scrnMsg.serNum.setInputProtected(false);
        // END 2016/03/28 M.Gotou [QC#4398, MOD]
        scrnMsg.istlDt.setInputProtected(true);
        // ADD START 2015/11/26 K.Kasai [Unit Test #71]
        scrnMsg.contrVrsnEffFromDt.setInputProtected(true);
        scrnMsg.contrVrsnEffThruDt.setInputProtected(true);
        // ADD END 2015/11/26 K.Kasai [Unit Test #71]
        scrnMsg.bllgCycleDescTxt.setInputProtected(true);
        scrnMsg.dsContrCatgDescTxt.setInputProtected(true);
        // ADD START 2015/11/26 K.Kasai [Unit Test #71]
        // START 2022/03/22 [QC#59683, ADD]
//        scrnMsg.conslInvFlg.setInputProtected(true);
        scrnMsg.dsInvTgtrTpDescTxt.setInputProtected(true);
        // END   2022/03/22 [QC#59683, ADD]
        // START 2018/03/08 K.Kojima [QC#24507,MOD]
        // scrnMsg.stplInclSvcFlg.setInputProtected(true);
        scrnMsg.svcTermCondDataDispTxt.setInputProtected(true);
        // END 2018/03/08 K.Kojima [QC#24507,MOD]
        // ADD END 2015/11/26 K.Kasai [Unit Test #71]

        // UGW
        // ADD START 2015/11/26 K.Kasai [Unit Test #71]
        scrnMsg.xxOpsDt_CM.setInputProtected(true);
        scrnMsg.shrDlrFlg.setInputProtected(true);
        // START 2016/06/10 [QC#9591, MOD]
        scrnMsg.iwrCondDescTxt.setInputProtected(true);
        // END   2016/06/10 [QC#9591, MOD]
        scrnMsg.iwrRgtnDt.setInputProtected(true);
        scrnMsg.iwrDeinsDt.setInputProtected(true);
        // ADD END 2015/11/26 K.Kasai [Unit Test #71]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mtrLbDescTxt_A.setInputProtected(true);
        }

        // mod start 2016/03/29 CSA Defect#4396
        // Meter Read Capture
        setMeterReadCaptureItems(handler, functionList, scrnMsg);
        // mod end 2016/03/29 CSA Defect#4396
        // ADD START 2015/11/26 K.Kasai [Unit Test #71]
        // MOD START 2015/12/10 K.Kasai [Unit Test #1746]
//        if (!functionList.contains(NSAL0150Constant.FUNC_ID_BILL) && !functionList.contains(NSAL0150Constant.FUNC_ID_SVC) && !functionList.contains(NSAL0150Constant.FUNC_ID_SUPPLY) && !functionList.contains(NSAL0150Constant.FUNC_ID_ODR)) {
        boolean existMeter = false;
        if (scrnMsg.B.getValidCount() > 0) {
            existMeter = true;
        }
        if ((!functionList.contains(NSAL0150Constant.FUNC_ID_BILL) && !functionList.contains(NSAL0150Constant.FUNC_ID_SVC) && !functionList.contains(NSAL0150Constant.FUNC_ID_SUPPLY) && !functionList.contains(NSAL0150Constant.FUNC_ID_ODR))
            || !existMeter) {
        // MOD END 2015/12/10 K.Kasai [Unit Test #1746]
            scrnMsg.dsMtrReadTpGrpCd_BS.setInputProtected(true);
        }
        // ADD END 2015/11/26 K.Kasai [Unit Test #71]

        // Meter History
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.C.no(i).mtrLbDescTxt_C.setInputProtected(true);
            // ADD START 2015/12/10 K.Kasai [QC1746]
            if (!existMeter) {
                scrnMsg.C.no(i).xxChkBox_C.setInputProtected(true);
            }
            // ADD END 2015/12/10 K.Kasai [QC1746]
        }

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.D.no(i).xxGenlFldAreaTxt_D.setInputProtected(true);
            // ADD START 2015/12/10 K.Kasai [QC1746]
            if (!existMeter) {
                scrnMsg.D.no(i).xxChkBox_D.setInputProtected(true);
            }
            // ADD END 2015/12/10 K.Kasai [QC1746]
        }

        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            // START 2018/07/30 K.Kitachi [QC#26874, ADD]
            scrnMsg.E.no(i).vldMtrFlg_E.setInputProtected(false);
            // END 2018/07/30 K.Kitachi [QC#26874, ADD]
            // MOD START 2015/11/26 K.Kasai [Unit Test #71]
            // if
            // (!functionList.contains(NSAL0150Constant.FUNC_ID_UPD))
            // {
            // START 2018/02/19 M.Kidokoro [QC#23753, MOD]
//            if (!functionList.contains(NSAL0150Constant.FUNC_ID_BILL) && !functionList.contains(NSAL0150Constant.FUNC_ID_SVC) && !functionList.contains(NSAL0150Constant.FUNC_ID_SUPPLY) && !functionList.contains(NSAL0150Constant.FUNC_ID_ODR)) {
            if (!functionList.contains(NSAL0150Constant.FUNC_ID_BILL)) {
            // END 2018/02/19 M.Kidokoro [QC#23753, MOD]
                // MOD END 2015/11/26 K.Kasai [Unit Test #71]
                handler.setButtonEnabled(NSAL0150Constant.BTN_INSERT_AS_ACTUAL, i, false);
                // START 2020/01/06 [QC#55286, DEL]
                // scrnMsg.E.no(i).vldMtrFlg_E.setInputProtected(true);
                // END   2020/01/06 [QC#55286, DEL]
            }
            // START 2020/01/06 [QC#55286, ADD]
            if (!functionList.contains(NSAL0150Constant.FUNC_ID_BILL)
                    && (scrnMsg.E.no(i).dsMtrReadTpGrpCd_E.getValue().equals(DS_MTR_READ_TP_GRP.BILLABLE_READS))) {
                scrnMsg.E.no(i).vldMtrFlg_E.setInputProtected(true);
            }
            if (!functionList.contains(NSAL0150Constant.FUNC_ID_SVC)
                    && (scrnMsg.E.no(i).dsMtrReadTpGrpCd_E.getValue().equals(DS_MTR_READ_TP_GRP.SERVICE_READS) 
                            || scrnMsg.E.no(i).dsMtrReadTpGrpCd_E.getValue().equals(DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ))) {
                scrnMsg.E.no(i).vldMtrFlg_E.setInputProtected(true);
            }
            if ((!functionList.contains(NSAL0150Constant.FUNC_ID_SUPPLY) && !functionList.contains(NSAL0150Constant.FUNC_ID_ODR))
                    && (scrnMsg.E.no(i).dsMtrReadTpGrpCd_E.getValue().equals(DS_MTR_READ_TP_GRP.SUPPLY_READS))) {
                scrnMsg.E.no(i).vldMtrFlg_E.setInputProtected(true);
            }
            if (!functionList.contains(NSAL0150Constant.FUNC_ID_BILL)
                    && !functionList.contains(NSAL0150Constant.FUNC_ID_SVC)
                    && !functionList.contains(NSAL0150Constant.FUNC_ID_SUPPLY)
                    && !functionList.contains(NSAL0150Constant.FUNC_ID_ODR)) {
                scrnMsg.E.no(i).vldMtrFlg_E.setInputProtected(true);
            }
            // END   2020/01/06 [QC#55286, ADD]
            scrnMsg.E.no(i).mtrLbDescTxt_E.setInputProtected(true);
            scrnMsg.E.no(i).dsMtrReadTpDescTxt_E.setInputProtected(true);
            scrnMsg.E.no(i).mtrEntryCmntTxt_E.setInputProtected(true);
            // add start 2016/03/22 CSA Defect#4629
            scrnMsg.E.no(i).mtrReadDt_E.setInputProtected(true);
            // add end 2016/03/22 CSA Defect#4629
            // START 2017/12/13 M.Kidokoro [QC#21681, ADD]
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.E.no(i).vldMtrFlg_EI.getValue())) {
                scrnMsg.E.no(i).vldMtrFlg_E.setInputProtected(true);
            }
            // END 2017/12/13 M.Kidokoro [QC#21681, ADD]
        }

        hideVldMtrFlg(scrnMsg);
        hideInsertAsActualButton(handler, scrnMsg);
        //Add Start 2016/10/03 <QC#12274>
        if (DS_MTR_READ_TP_GRP.SERVICE_READS.equals(scrnMsg.dsMtrReadTpGrpCd_BS.getValue())) {
            scrnMsg.svcTaskNum_B.setInputProtected(false);
            scrnMsg.dsTestCopyClsCd_BS.setInputProtected(false);
        } else {
            scrnMsg.svcTaskNum_B.setInputProtected(true);
            scrnMsg.dsTestCopyClsCd_BS.setInputProtected(true);
            scrnMsg.svcTaskNum_B.clear();
            scrnMsg.dsTestCopyClsCd_BS.clear();
        }
        //Add Start 2016/10/03 <QC#12274>
    }

    /**
     * Clear Valid Meter Flag Attribute
     * @param scrnMsg NSAL0150BMsg
     * @param i Row index
     */
    private static void clearVldMtrFlgAttr(NSAL0150BMsg scrnMsg, int i) {
        scrnMsg.clearGUIAttribute(NSAL0150Constant.SCR_ID_00, "vldMtrFlg_E#" + i);
    }

    /**
     * Clear Insert as Actual Button Attribute
     * @param scrnMsg NSAL0150BMsg
     * @param i Row index
     */
    private static void clearInsertAsActualButtonAttribute(NSAL0150BMsg scrnMsg, int i) {
        scrnMsg.clearGUIAttribute(NSAL0150Constant.SCR_ID_00, "InsertAsActual#" + i);
    }

    /**
     * Hide Valid Meter Flag
     * @param scrnMsg NSAL0150BMsg
     * @param i Row index
     */
    private static void addHideVldMtrFlgAttribute(NSAL0150BMsg scrnMsg, int i) {
        EZDGUIAttribute attr = new EZDGUIAttribute(NSAL0150Constant.SCR_ID_00, "vldMtrFlg_E#" + i);
        attr.setVisibility(false);
        scrnMsg.addGUIAttribute(attr);
    }

    /**
     * Hide Insert as Actual Button
     * @param scrnMsg NSAL0150BMsg
     * @param i Row index
     */
    private static void addHideInsertAsActualButtonAttribute(NSAL0150BMsg scrnMsg, int i) {
        EZDGUIAttribute attr = new EZDGUIAttribute(NSAL0150Constant.SCR_ID_00, "InsertAsActual#" + i);
        attr.setVisibility(false);
        scrnMsg.addGUIAttribute(attr);
    }

    /**
     * Hide Valid Meter Flag
     * @param scrnMsg NSAL0150BMsg
     */
    public static void hideVldMtrFlg(NSAL0150BMsg scrnMsg) {
        BigDecimal prevSvcPhysMtrReadGrpSq = BigDecimal.ONE.negate();
        for (int i = 0; i < scrnMsg.E.length(); i++) {
            clearVldMtrFlgAttr(scrnMsg, i);
        }
        // START 2015/12/16 K.Kasai [QC2070, MOD]
        boolean isAllCheckedFlag = isAllChecked(scrnMsg);
        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            BigDecimal svcPhysMtrReadGrpSq = scrnMsg.E.no(i).svcPhysMtrReadGrpSq_E.getValue();
            // QC#25419 Mod Start
            // if (!isAllCheckedFlag || isEqualNum(prevSvcPhysMtrReadGrpSq, svcPhysMtrReadGrpSq)) {
            if (isEqualNum(prevSvcPhysMtrReadGrpSq, svcPhysMtrReadGrpSq)) {
            // QC#25419 Mod End
                addHideVldMtrFlgAttribute(scrnMsg, i);
            }
            prevSvcPhysMtrReadGrpSq = svcPhysMtrReadGrpSq;
        }
        // END 2015/12/16 K.Kasai [QC2070, MOD]
    }

    /**
     * Hide Insert as Actual Button
     * @param handler S21CommonHandler
     * @param scrnMsg NSAL0150BMsg
     */
    public static void hideInsertAsActualButton(S21CommonHandler handler, NSAL0150BMsg scrnMsg) {
        BigDecimal prevSvcPhysMtrReadGrpSq = BigDecimal.ONE.negate();
        for (int i = 0; i < scrnMsg.E.length(); i++) {
            clearInsertAsActualButtonAttribute(scrnMsg, i);
        }
        // START 2015/12/16 K.Kasai [QC2070, ADD]
        boolean isAllCheckedFlag = isAllChecked(scrnMsg);
        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            BigDecimal svcPhysMtrReadGrpSq = scrnMsg.E.no(i).svcPhysMtrReadGrpSq_E.getValue();
            // QC#25419 Mod Start
            //if (!isAllCheckedFlag || isEqualNum(prevSvcPhysMtrReadGrpSq, svcPhysMtrReadGrpSq)) {
            if (isEqualNum(prevSvcPhysMtrReadGrpSq, svcPhysMtrReadGrpSq)) {
            // QC#25419 Mod End
                addHideInsertAsActualButtonAttribute(scrnMsg, i);
            } else {
                if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.E.no(i).xxBtnFlg_E.getValue())) {
                    addHideInsertAsActualButtonAttribute(scrnMsg, i);
                }
            }
            prevSvcPhysMtrReadGrpSq = svcPhysMtrReadGrpSq;
        }
        // END 2015/12/16 K.Kasai [QC2070, ADD]
    }

    // START 2015/12/16 K.Kasai [QC2070, ADD]
    private static boolean isAllChecked(NSAL0150BMsg scrnMsg) {
        if (scrnMsg.C.getValidCount() <= 1) {
            return false;
        }
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.C.no(0).xxChkBox_C.getValue())) {
            return true;
        }
        for (int i = 1; i < scrnMsg.C.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.C.no(i).xxChkBox_C.getValue())) {
                return false;
            }
        }
        return true;
    }
    // END 2015/12/16 K.Kasai [QC2070, ADD]

    /**
     * Alternate table row color
     * @param scrnMsg NSAL0150BMsg
     */
    public static void alternateTableRowColor(NSAL0150BMsg scrnMsg) {
        S21TableColorController control = new S21TableColorController(NSAL0150Constant.SCR_ID_00, scrnMsg);
        control.setAlternateRowsBG(NSAL0150Bean.A, scrnMsg.A);
        control.setAlternateRowsBG(NSAL0150Bean.B, scrnMsg.B);
        control.setAlternateRowsBG(NSAL0150Bean.C, scrnMsg.C);
        control.setAlternateRowsBG(NSAL0150Bean.D, scrnMsg.D);
        control.setAlternateRowsBG(NSAL0150Bean.E, scrnMsg.E);
    }

    /**
     * Focus item
     * @param scrnMsg NSAL0150BMsg
     */
    public static void focusItem(NSAL0150BMsg scrnMsg) {
    }

    /**
     * guiAttributeClear
     * @param scrnMsg NSAL0150BMsg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NSAL0150BMsg scrnMsg, String[][] baseContents) {
        S21SortColumnIMGController.clearIMG(NSAL0150Constant.SCR_ID_00, scrnMsg, baseContents);
        scrnMsg.clearAllGUIAttribute(NSAL0150Constant.SCR_ID_00);
    }

    /**
     * Check input
     * @param scrnMsg NSAL0150BMsg
     */
    public static void addCheckItem(NSAL0150BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).dsMtrReadTpCd_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).mtrReadDt_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).readMtrCnt_B);
            //Del Start 2016/10/03 <QC#12274>
//            // add start 2016/03/29 CSA Defect#4396
//            scrnMsg.addCheckItem(scrnMsg.B.no(i).testMtrCnt_B);
//            // add end 2016/03/29 CSA Defect#4396
            //Del End   2016/10/03 <QC#12274>
            //Add Start 2016/10/03 <QC#12274>
            scrnMsg.addCheckItem(scrnMsg.svcTaskNum_B);
            scrnMsg.addCheckItem(scrnMsg.dsTestCopyClsCd_BS);
            //Add End   2016/10/03 <QC#12274>
        }
        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.E.no(i).vldMtrFlg_E);
        }
        // START 2019/04/09 K.Kitachi [QC#31089, ADD]
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt);
        // END 2019/04/09 K.Kitachi [QC#31089, ADD]
        // START 2024/04/08 S.Moriai [QC#63540, ADD]
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        // END 2024/04/08 S.Moriai [QC#63540, ADD]
    }

    public static boolean isEqualNum(BigDecimal a, BigDecimal b) {
        if (a == null && b == null) {
            return true;
        }
        if (a != null && b != null && a.compareTo(b) == 0) {
            return true;
        }
        return false;
    }

    private static void setBtnOrderSupplies(S21CommonHandler handler, NSAL0150BMsg scrnMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg.getValue())) {
            handler.setButtonEnabled(NSAL0150Constant.BTN_ORDER_SUPPLIES, true);
        } else {
            handler.setButtonEnabled(NSAL0150Constant.BTN_ORDER_SUPPLIES, false);
        }
    }

    // add start 2016/03/29 CSA Defect#4393
    private static void setMtrReadLine(NSAL0150_BBMsg lineMsg, String dsMtrReadTpGrpCd) {
        if (!ZYPCommonFunc.hasValue(dsMtrReadTpGrpCd)) {
            lineMsg.dsMtrReadTpCd_B.setInputProtected(true);
            lineMsg.mtrReadDt_B.setInputProtected(true);
            lineMsg.readMtrCnt_B.setInputProtected(true);
            //Del Start 2016/10/03 <QC#12274>
//            lineMsg.testMtrCnt_B.setInputProtected(true);
            //Del End   2016/10/03 <QC#12274>
            lineMsg.mtrEntryCmntTxt_B.setInputProtected(true);
            return;
        }

        lineMsg.dsMtrReadTpCd_B.setInputProtected(false);
        lineMsg.mtrReadDt_B.setInputProtected(false);
        lineMsg.readMtrCnt_B.setInputProtected(false);
        lineMsg.mtrEntryCmntTxt_B.setInputProtected(false);
        //Del Start 2016/10/03 <QC#12274>
//        if ((DS_MTR_READ_TP_GRP.BILLABLE_READS.equals(dsMtrReadTpGrpCd)) || (DS_MTR_READ_TP_GRP.SUPPLY_READS.equals(dsMtrReadTpGrpCd))) {
//            lineMsg.testMtrCnt_B.clear();
//            lineMsg.testMtrCnt_B.setInputProtected(true);
//        } else {
//            lineMsg.testMtrCnt_B.setInputProtected(false);
//        }
        //Del End   2016/10/03 <QC#12274>
    }

    private static void setEstimateBtn(S21CommonHandler handler, String dsMtrReadTpGrpCd) {
        if (!ZYPCommonFunc.hasValue(dsMtrReadTpGrpCd)) {
            handler.setButtonEnabled(NSAL0150Constant.BTN_ESTIMATE, false);
            return;
        }

        if ((DS_MTR_READ_TP_GRP.BILLABLE_READS.equals(dsMtrReadTpGrpCd)) || (DS_MTR_READ_TP_GRP.SUPPLY_READS.equals(dsMtrReadTpGrpCd))) {
            handler.setButtonEnabled(NSAL0150Constant.BTN_ESTIMATE, true);
        } else {
            handler.setButtonEnabled(NSAL0150Constant.BTN_ESTIMATE, false);
        }
    }
    // add end 2016/03/29 CSA Defect#4393

    // add start 2016/03/29 CSA Defect#4396
    private static void setMeterReadCaptureItems(S21CommonHandler handler, List<String> functionList, NSAL0150BMsg scrnMsg){
        String dsMtrReadTpGrpCd = scrnMsg.dsMtrReadTpGrpCd_BS.getValue();
        if (!ZYPCommonFunc.hasValue(dsMtrReadTpGrpCd)) {
            dsMtrReadTpGrpCd = scrnMsg.dsMtrReadTpGrpCd_BD.no(0).getValue();
        }
        String dsMtrReadTpCd = null;
        String mtrReadDt = null;
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (i == 0) {
                dsMtrReadTpCd = scrnMsg.B.no(i).dsMtrReadTpCd_B.getValue();
                mtrReadDt = scrnMsg.B.no(i).mtrReadDt_B.getValue();
            }
            // START 2020/03/03 [QC#56123, DEL]
            // mod start 2017/09/07 QC#15134
//            if (!DS_MTR_READ_TP_GRP.SERVICE_READS.equals(dsMtrReadTpGrpCd)) {
//                setValue(scrnMsg.B.no(i).dsMtrReadTpCd_B, dsMtrReadTpCd);
//            }
            // mod end 2017/09/07 QC#15134
            // END   2020/03/03 [QC#56123, DEL]
            setValue(scrnMsg.B.no(i).mtrReadDt_B, mtrReadDt);

            scrnMsg.B.no(i).mtrLbDescTxt_B.setInputProtected(true);
            if (!functionList.contains(NSAL0150Constant.FUNC_ID_BILL) && !functionList.contains(NSAL0150Constant.FUNC_ID_SVC) && !functionList.contains(NSAL0150Constant.FUNC_ID_SUPPLY) && !functionList.contains(NSAL0150Constant.FUNC_ID_ODR)) {
                scrnMsg.B.no(i).dsMtrReadTpCd_B.setInputProtected(true);
                scrnMsg.B.no(i).mtrReadDt_B.setInputProtected(true);
                scrnMsg.B.no(i).readMtrCnt_B.setInputProtected(true);
                //Del Start 2016/10/03 <QC#12274>
//                scrnMsg.B.no(i).testMtrCnt_B.setInputProtected(true);
                //Del End   2016/10/03 <QC#12274>
                scrnMsg.B.no(i).mtrEntryCmntTxt_B.setInputProtected(true);
            } else {
                setMtrReadLine(scrnMsg.B.no(i), dsMtrReadTpGrpCd);
            }
            if (i > 0) {
                // mod start 2017/09/07 QC#15134
                // START 2020/03/03 [QC#56123, MOD]
//                if (!DS_MTR_READ_TP_GRP.SERVICE_READS.equals(dsMtrReadTpGrpCd)) {
//                    scrnMsg.B.no(i).dsMtrReadTpCd_B.setInputProtected(true);
//                }
                // END   2020/03/03 [QC#56123, MOD]
                scrnMsg.B.no(i).mtrReadDt_B.setInputProtected(true);
                // mod end 2017/09/07 QC#15134
            }
        }
        if (functionList.contains(NSAL0150Constant.FUNC_ID_BILL) || functionList.contains(NSAL0150Constant.FUNC_ID_SVC) || functionList.contains(NSAL0150Constant.FUNC_ID_SUPPLY) || functionList.contains(NSAL0150Constant.FUNC_ID_ODR)) {
            setEstimateBtn(handler, dsMtrReadTpGrpCd);
        }
    }
    // add end 2016/03/29 CSA Defect#4396
}
