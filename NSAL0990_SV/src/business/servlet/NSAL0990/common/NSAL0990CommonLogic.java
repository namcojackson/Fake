/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0990.common;

import static business.servlet.NSAL0990.constant.NSAL0990Constant.ONBLUR_DERIVEFROM_FREIGHT_TERMS;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_2;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0990.NSAL0990BMsg;
import business.servlet.NSAL0990.NSAL0990Bean;
import business.servlet.NSAL0990.NSAL0990_CBMsg;
import business.servlet.NSAL0990.NSAL0990_EBMsg;
import business.servlet.NSAL0990.NSAL0990_MBMsg;
import business.servlet.NSAL0990.NSAL0990_MBMsgArray;
import business.servlet.NSAL0990.constant.NSAL0990Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         K.Kasai         Create          N/A
 * 2016/02/23   Hitachi         T.Tsuchida      Update          QC#4117
 * 2016/03/08   Hitachi         A.Kohinata      Update          QC#5143
 * 2016/03/09   Hitachi         A.Kohinata      Update          QC#5196
 * 2016/03/11   Hitachi         A.Kohinata      Update          QC#5211
 * 2016/03/11   Hitachi         A.Kohinata      Update          QC#5354
 * 2016/03/14   Hitachi         A.Kohinata      Update          QC#5375
 * 2016/03/16   Hitachi         A.Kohinata      Update          QC#5530
 * 2016/03/22   Hitachi         A.Kohinata      Update          QC#5804
 * 2016/03/29   Hitachi         A.Kohinata      Update          QC#5790
 * 2016/03/30   Hitachi         A.Kohinata      Update          QC#6066
 * 2016/06/22   Hitachi         K.Kasai         Update          QC#9888
 * 2016/07/06   Hitachi         O.Okuma         Update          QC#9630
 * 2016/09/21   Hitachi         N.Arai          Update          QC#11616
 * 2016/09/23   Hitachi         Y.Zhang         Update          QC#12582
 * 2016/10/04   Hitachi         A.Kohinata      Update          QC#12898
 * 2016/10/13   Hitachi         A.Kohinata      Update          QC#9885
 * 2016/10/19   Hitachi         A.Kohinata      Update          QC#15344
 * 2017/01/06   Hitachi         K.Ochiai        Update          QC#16012
 * 2017/02/01   Hitachi         T.Tomita        Update          QC#17406
 * 2017/09/15   Hitachi         U.Kim           Update          QC#18726
 * 2017/10/18   Hitachi         M.Kidokoro      Update          QC#20246
 * 2018/04/11   Hitachi         K.Kitachi       Update          QC#11642
 * 2018/05/17   Fujitsu         H.Nagashima     Update          QC#25440
 * 2018/07/02   CITS            T.Wada          Update          QC#23726
 * 2018/07/09   CITS            T.Wada          Update          QC#23726-1
 * 2018/07/19   Hitachi         K.Kitachi       Update          QC#26978
 * 2018/07/30   Hitachi         T.Tomita        Update          QC#14307
 * 2018/11/14   Fujitsu         M.Yamada        Update          QC#29121
 * 2018/12/12   Fujitsu         H.Kumagai       Update          QC#29315
 * 2018/12/25   Fujitsu         Hd.Sugawara     Update          QC#29744
 * 2019/01/21   Hitachi         A.Kohinata      Update          QC#27304
 * 2019/06/19   Hitachi         K.Kim           Update          QC#50879
 *</pre>
 */
public class NSAL0990CommonLogic {

    /**
     * Activate buttons
     * @param handler S21CommonHandler
     * @param scrnMsg NSAL0990BMsg
     * @param functionList List<String>
     */
    public static void activateButtons(S21CommonHandler handler, NSAL0990BMsg scrnMsg, List<String> functionList) {
        if (functionList == null || functionList.isEmpty() || functionList.contains(NSAL0990Constant.FUNC_ID_INQ)) {
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_BTN_1[0], NSAL0990Constant.BTN_CMN_BTN_1[1], NSAL0990Constant.BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_SUBMIT[0], NSAL0990Constant.BTN_CMN_SUBMIT[1], NSAL0990Constant.BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_BTN_3[0], NSAL0990Constant.BTN_CMN_BTN_3[1], NSAL0990Constant.BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_BTN_4[0], NSAL0990Constant.BTN_CMN_BTN_4[1], NSAL0990Constant.BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_BTN_5[0], NSAL0990Constant.BTN_CMN_BTN_5[1], NSAL0990Constant.BTN_CMN_BTN_5[2], 0, null);
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_BTN_6[0], NSAL0990Constant.BTN_CMN_BTN_6[1], NSAL0990Constant.BTN_CMN_BTN_6[2], 0, null);
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_BTN_7[0], NSAL0990Constant.BTN_CMN_BTN_7[1], NSAL0990Constant.BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_BTN_8[0], NSAL0990Constant.BTN_CMN_BTN_8[1], NSAL0990Constant.BTN_CMN_BTN_8[2], 0, null);
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_RESET[0], NSAL0990Constant.BTN_CMN_RESET[1], NSAL0990Constant.BTN_CMN_RESET[2], 0, null);
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_RETURN[0], NSAL0990Constant.BTN_CMN_RETURN[1], NSAL0990Constant.BTN_CMN_RETURN[2], 1, null);
            handler.setButtonEnabled(NSAL0990Constant.BTN_BILL_TO, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_SHIP_TO, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_CREDIT_CARD, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_MDSE_NM, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_DELETE_LINE, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_MEMO, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_SUP_ORD_SER, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_CALCU, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_EDIT, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_DELETE_QUOTE, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_ADD, false);
            // add start 2016/10/13 CSA Defect#9885
            handler.setButtonEnabled(NSAL0990Constant.BTN_GO, false);
            // add end 2016/10/13 CSA Defect#9885
            // START 2017/09/15 U.Kim [QC#18726, ADD]
            handler.setButtonEnabled(NSAL0990Constant.BTN_INVENTORY, false);
            // END 2017/09/15 U.Kim [QC#18726, ADD]
            // add start 2019/01/21 QC#27304
            handler.setButtonEnabled(NSAL0990Constant.BTN_EDIT_LIST, false);
            // add end 2019/01/21 QC#27304
        }
        if (functionList != null && !functionList.isEmpty() && functionList.contains(NSAL0990Constant.FUNC_ID_UPD)) {
            // Update
            // mod start 2019/01/21 QC#27304
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_BTN_1[0], NSAL0990Constant.BTN_CMN_BTN_1[1], NSAL0990Constant.BTN_CMN_BTN_1[2], 1, null);
            // mod end 2019/01/21 QC#27304
            if (NSAL0990Constant.MODE_1.equals(scrnMsg.xxScrDply.getValue())) {
                handler.setButtonProperties(NSAL0990Constant.BTN_CMN_SUBMIT[0], NSAL0990Constant.BTN_CMN_SUBMIT[1], NSAL0990Constant.BTN_CMN_SUBMIT[2], 0, null);
            } else {
                handler.setButtonProperties(NSAL0990Constant.BTN_CMN_SUBMIT[0], NSAL0990Constant.BTN_CMN_SUBMIT[1], NSAL0990Constant.BTN_CMN_SUBMIT[2], 1, null);
            }
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_BTN_3[0], NSAL0990Constant.BTN_CMN_BTN_3[1], NSAL0990Constant.BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_BTN_4[0], NSAL0990Constant.BTN_CMN_BTN_4[1], NSAL0990Constant.BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_BTN_5[0], NSAL0990Constant.BTN_CMN_BTN_5[1], NSAL0990Constant.BTN_CMN_BTN_5[2], 0, null);
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_BTN_6[0], NSAL0990Constant.BTN_CMN_BTN_6[1], NSAL0990Constant.BTN_CMN_BTN_6[2], 0, null);
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_BTN_7[0], NSAL0990Constant.BTN_CMN_BTN_7[1], NSAL0990Constant.BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_BTN_8[0], NSAL0990Constant.BTN_CMN_BTN_8[1], NSAL0990Constant.BTN_CMN_BTN_8[2], 0, null);
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_RESET[0], NSAL0990Constant.BTN_CMN_RESET[1], NSAL0990Constant.BTN_CMN_RESET[2], 0, null);
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_RETURN[0], NSAL0990Constant.BTN_CMN_RETURN[1], NSAL0990Constant.BTN_CMN_RETURN[2], 1, null);
            handler.setButtonEnabled(NSAL0990Constant.BTN_BILL_TO, true);
            handler.setButtonEnabled(NSAL0990Constant.BTN_SHIP_TO, true);
            // set Credit Card Button
            setProtectByPmtMeth(handler, scrnMsg);
            handler.setButtonEnabled(NSAL0990Constant.BTN_MDSE_NM, true);
            handler.setButtonEnabled(NSAL0990Constant.BTN_DELETE_LINE, true);
            handler.setButtonEnabled(NSAL0990Constant.BTN_MEMO, true);
            // START 2017/09/15 U.Kim [QC#18726, ADD]
            handler.setButtonEnabled(NSAL0990Constant.BTN_INVENTORY, true);
            // END 2017/09/15 U.Kim [QC#18726, ADD]
            // START 2018/07/19 K.Kitachi [QC#26978, MOD]
//            // mod start 2016/10/04 CSA Defect#12898
//            if (DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
//                handler.setButtonEnabled(NSAL0990Constant.BTN_SUP_ORD_SER, false);
//            } else {
//                handler.setButtonEnabled(NSAL0990Constant.BTN_SUP_ORD_SER, true);
//            }
//            // mod end 2016/10/04 CSA Defect#12898
            handler.setButtonEnabled(NSAL0990Constant.BTN_SUP_ORD_SER, true);
            // END 2018/07/19 K.Kitachi [QC#26978, MOD]
            handler.setButtonEnabled(NSAL0990Constant.BTN_CALCU, true);
            handler.setButtonEnabled(NSAL0990Constant.BTN_EDIT, true);
            handler.setButtonEnabled(NSAL0990Constant.BTN_DELETE_QUOTE, true);
            handler.setButtonEnabled(NSAL0990Constant.BTN_ADD, true);
            // add start 2016/10/13 CSA Defect#9885
            handler.setButtonEnabled(NSAL0990Constant.BTN_GO, true);
            // add end 2016/10/13 CSA Defect#9885
            if (scrnMsg.C.getValidCount() == 0) {
                handler.setButtonEnabled(NSAL0990Constant.BTN_DELETE_LINE, false);
            // START 2017/09/15 U.Kim [QC#18726, DEL]
            //} else {
            //    boolean existDeleteLine = false;
            //    for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            //        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.C.no(i).xxDplyCtrlFlg_C.getValue())) {
            //            existDeleteLine = true;
            //            break;
            //        }
            //    }
            //    if (!existDeleteLine) {
            //        handler.setButtonEnabled(NSAL0990Constant.BTN_DELETE_LINE, false);
            //    }
            // END 2017/09/15 U.Kim [QC#18726, DEL]
            }
            if (scrnMsg.E.getValidCount() == 0) {
                handler.setButtonEnabled(NSAL0990Constant.BTN_DELETE_QUOTE, false);
            // START 2017/09/15 U.Kim [QC#18726, DEL]
            //  } else {
            //    boolean existDeleteLine = false;
            //   for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            //        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.E.no(i).prcCondManDelFlg_E.getValue())) {
            //            existDeleteLine = true;
            //            break;
            //        }
            //    }
            //    if (!existDeleteLine) {
            //        handler.setButtonEnabled(NSAL0990Constant.BTN_DELETE_QUOTE, false);
            //    }
            // END 2017/09/15 U.Kim [QC#18726, DEL]
            }
            if (NSAL0990Constant.MODE_1.equals(scrnMsg.xxScrDply.getValue()) && scrnMsg.C.getValidCount() == 0) {
                handler.setButtonEnabled(NSAL0990Constant.BTN_CALCU, false);
            } else if (NSAL0990Constant.MODE_2.equals(scrnMsg.xxScrDply.getValue()) && scrnMsg.E.getValidCount() == 0) {
                handler.setButtonEnabled(NSAL0990Constant.BTN_CALCU, false);
            }
            if (scrnMsg.C.getValidCount() == 0 && scrnMsg.E.getValidCount() == 0) {
                handler.setButtonEnabled(NSAL0990Constant.BTN_EDIT, false);
            }
            // add start 2019/01/21 QC#27304
            handler.setButtonEnabled(NSAL0990Constant.BTN_EDIT_LIST, true);
            // add end 2019/01/21 QC#27304
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
            // add start 2019/01/21 QC#27304
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_BTN_1[0], NSAL0990Constant.BTN_CMN_BTN_1[1], NSAL0990Constant.BTN_CMN_BTN_1[2], 0, null);
            // add end 2019/01/21 QC#27304
            handler.setButtonProperties(NSAL0990Constant.BTN_CMN_SUBMIT[0], NSAL0990Constant.BTN_CMN_SUBMIT[1], NSAL0990Constant.BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonEnabled(NSAL0990Constant.BTN_BILL_TO, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_SHIP_TO, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_CREDIT_CARD, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_MDSE_NM, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_DELETE_LINE, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_MEMO, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_SUP_ORD_SER, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_CALCU, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_EDIT, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_DELETE_QUOTE, false);
            handler.setButtonEnabled(NSAL0990Constant.BTN_ADD, false);
            // add start 2016/10/13 CSA Defect#9885
            handler.setButtonEnabled(NSAL0990Constant.BTN_GO, false);
            // add end 2016/10/13 CSA Defect#9885
            // START 2017/09/15 U.Kim [QC#18726, ADD]
            handler.setButtonEnabled(NSAL0990Constant.BTN_INVENTORY, false);
            // END 2017/09/15 U.Kim [QC#18726, ADD]
            // add start 2019/01/21 QC#27304
            handler.setButtonEnabled(NSAL0990Constant.BTN_EDIT_LIST, false);
            // add end 2019/01/21 QC#27304
        }

        // Add Start 2018/07/30 QC#14307
        activeSpclInstructionBtn(handler, scrnMsg);
        // Add End 2018/07/30 QC#14307
    }

    /**
     * Activate buttons For Add
     * @param handler S21CommonHandler
     * @param scrnMsg NSAL0990BMsg
     * @param functionList List<String>
     */
    public static void activateButtonsForAdd(S21CommonHandler handler, NSAL0990BMsg scrnMsg, List<String> functionList) {
        if (functionList != null && !functionList.isEmpty() && functionList.contains(NSAL0990Constant.FUNC_ID_UPD)) {
            if (scrnMsg.C.getValidCount() == scrnMsg.C.length()) {
                handler.setButtonEnabled(NSAL0990Constant.BTN_ADD, true);
            }
        }
    }

    /**
     * Activate screen items
     * @param handler S21CommonHandler
     * @param functionList List<String>
     * @param scrnMsg NSAL0990BMsg
     */
    public static void activateScreenItems(S21CommonHandler handler, List<String> functionList, NSAL0990BMsg scrnMsg) {

        // Header
        scrnMsg.cpoOrdNum.setInputProtected(true);
        // add start 2016/06/22 CSA Defect#9888
        scrnMsg.serNum_HD.setInputProtected(true);
        // add end 2016/06/22 CSA Defect#9888
        scrnMsg.ownrAcctNum.setInputProtected(true);
        scrnMsg.dsContrNum.setInputProtected(true);
        scrnMsg.dsContrCatgDescTxt.setInputProtected(true);
        scrnMsg.svcTermCondDataDispTxt_01.setInputProtected(true);
        scrnMsg.xxScrItem34Txt.setInputProtected(true);
        scrnMsg.coaBrNm.setInputProtected(true);
        scrnMsg.svcTermCondDataDispTxt_02.setInputProtected(true);
        scrnMsg.svcTermCondDataDispTxt_03.setInputProtected(true);
        scrnMsg.xxScrItem50Txt.setInputProtected(true);

        // Order Header Details
        scrnMsg.xxLocAddrNm_A1.setInputProtected(true);
        scrnMsg.xxLocAddrNm_A2.setInputProtected(true);
        scrnMsg.xxLocAddrNm_A3.setInputProtected(true);
        scrnMsg.ctyAddr_A1.setInputProtected(true);
        scrnMsg.stCd_A1.setInputProtected(true);
//        scrnMsg.telNum_A1.setInputProtected(true);
        scrnMsg.postCd_A1.setInputProtected(true);  //QC#25440 mod
        scrnMsg.ctryCd_A1.setInputProtected(true);
        scrnMsg.xxLocAddrNm_A4.setInputProtected(true);
        scrnMsg.xxLocAddrNm_A5.setInputProtected(true);
        scrnMsg.xxLocAddrNm_A6.setInputProtected(true);
        scrnMsg.ctyAddr_A2.setInputProtected(true);
        scrnMsg.stCd_A2.setInputProtected(true);
//        scrnMsg.telNum_A2.setInputProtected(true);
        scrnMsg.postCd_A2.setInputProtected(true);  //QC#25440 mod
        scrnMsg.ctryCd_A2.setInputProtected(true);
        scrnMsg.billToLocNum_LK.setInputProtected(false);
        scrnMsg.billToLocNum_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.curLocNum_LK.setInputProtected(false);
        scrnMsg.curLocNum_LK.setValue(ZYPConstant.FLG_ON_Y);
        // START 2018/04/11 K.Kitachi [QC#11642, ADD]
        scrnMsg.ctacPsnFirstNm_LK.setInputProtected(false);
        scrnMsg.ctacPsnFirstNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.ctacPsnFirstNm.setInputProtected(false);
        scrnMsg.ctacPsnLastNm.setInputProtected(false);
        scrnMsg.ctacPsnEmlAddr.setInputProtected(false);
        // END 2018/04/11 K.Kitachi [QC#11642, ADD]
        if (!functionList.contains(NSAL0990Constant.FUNC_ID_UPD)) {
            scrnMsg.billToLocNum.setInputProtected(true);
            scrnMsg.curLocNum.setInputProtected(true);
            scrnMsg.custIssPoNum.setInputProtected(true);
            scrnMsg.xxDsMultMsgDplyTxt.setInputProtected(true);
            scrnMsg.billToLocNum_LK.setInputProtected(true);
            scrnMsg.billToLocNum_LK.clear();
            scrnMsg.curLocNum_LK.setInputProtected(true);
            scrnMsg.curLocNum_LK.clear();
            // START 2018/04/11 K.Kitachi [QC#11642, ADD]
            scrnMsg.ctacPsnFirstNm_LK.setInputProtected(true);
            scrnMsg.ctacPsnFirstNm_LK.clear();
            scrnMsg.ctacPsnFirstNm.setInputProtected(true);
            scrnMsg.ctacPsnLastNm.setInputProtected(true);
            scrnMsg.ctacPsnEmlAddr.setInputProtected(true);
            // END 2018/04/11 K.Kitachi [QC#11642, ADD]
        }

        // Shipping Information Details
        scrnMsg.frtCondDescTxt_LK.setInputProtected(false);
        scrnMsg.frtCondDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.carrSvcLvlDescTxt_LK.setInputProtected(false);
        scrnMsg.carrSvcLvlDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        if (!functionList.contains(NSAL0990Constant.FUNC_ID_UPD)) {
            scrnMsg.frtCondDescTxt.setInputProtected(true);
            scrnMsg.frtCondDescTxt_LK.setInputProtected(true);
            scrnMsg.frtCondDescTxt_LK.clear();
            scrnMsg.shpgSvcLvlCd.setInputProtected(true);
            scrnMsg.carrSvcLvlDescTxt.setInputProtected(true);
            scrnMsg.carrSvcLvlDescTxt_LK.setInputProtected(true);
            scrnMsg.carrSvcLvlDescTxt_LK.clear();
            scrnMsg.carrAcctNum.setInputProtected(true);
            // add start 2019/01/21 QC#27304
            scrnMsg.scrLbNm.setInputProtected(true);
            scrnMsg.shpgInstnCmntTxt.setInputProtected(true);
            // add end 2019/01/21 QC#27304
        }

        // Payment Details
        if (!functionList.contains(NSAL0990Constant.FUNC_ID_UPD)) {
            scrnMsg.invCmntTxt.setInputProtected(true);
            scrnMsg.dsPmtMethCd.setInputProtected(true);
        }

        // Toner Allotments
        scrnMsg.xxImageTxt_LK.setInputProtected(false);
        scrnMsg.xxImageTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        if (!functionList.contains(NSAL0990Constant.FUNC_ID_UPD)) {
            scrnMsg.xxImageTxt_LK.setInputProtected(true);
            scrnMsg.xxImageTxt_LK.clear();
        }

        // Order History
        if (!functionList.contains(NSAL0990Constant.FUNC_ID_UPD)) {
            scrnMsg.xxCondCd.setInputProtected(true);
        }
        // START 2016/09/21 N.Arai [QC#11616, MOD]
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
         // scrnMsg.B.no(i).mdseNm_B.setInputProtected(true);
            scrnMsg.B.no(i).mdseDescShortTxt_B.setInputProtected(true);
            scrnMsg.B.no(i).ordLineStsNm_B.setInputProtected(true);
        }

        //Line Details(Supply Order Mode)
        scrnMsg.mdseCd_LK.setInputProtected(false);
        scrnMsg.mdseCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        if (!functionList.contains(NSAL0990Constant.FUNC_ID_UPD)) {
            scrnMsg.mdseCd.setInputProtected(true);
            scrnMsg.mdseCd_LK.setInputProtected(true);
            scrnMsg.mdseCd_LK.clear();
        }
        // scrnMsg.mdseNm.setInputProtected(true);
        scrnMsg.mdseDescShortTxt.setInputProtected(true);
        // END 2016/09/21 N.Arai [QC#11616, MOD]
        // add start 2016/10/13 CSA Defect#9885
        scrnMsg.t_MdlId_S.setInputProtected(false);
        if (!functionList.contains(NSAL0990Constant.FUNC_ID_UPD)) {
            scrnMsg.t_MdlId_S.setInputProtected(true);
        }
        // add end 2016/10/13 CSA Defect#9885
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            // START 2017/09/15 U.Kim [QC#18726, MOD]
            // if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.C.no(i).xxDplyCtrlFlg_C.getValue())) {
            //     scrnMsg.C.no(i).xxChkBox_C.setInputProtected(true);
            // } else {
            //     scrnMsg.C.no(i).xxChkBox_C.setInputProtected(false);
            // }
            scrnMsg.C.no(i).xxChkBox_C.setInputProtected(false);
            // END 2017/09/15 U.Kim [QC#18726, MOD]
            // add start 2017/02/01 CSA Defect#17406
            scrnMsg.C.no(i).mdseDescShortTxt_C.setInputProtected(true);
            // add end 2017/02/01 CSA Defect#17406
        }
        // Supply Stats
        scrnMsg.xxLocAddrNm_D.setInputProtected(true);
        //Line Details(Supply Order Edit Mode)
        // START 2017/09/15 U.Kim [QC#18726, DEL]
        // String preSerNum = "";
        // END 2017/09/15 U.Kim [QC#18726, DEL]
        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            // START 2017/09/15 U.Kim [QC#18726, MOD]
            // if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.E.no(i).prcCondManDelFlg_E.getValue())) {
            //     scrnMsg.E.no(i).xxChkBox_E.setInputProtected(true);
            // } else if (preSerNum.equals(scrnMsg.E.no(i).serNum_E.getValue())) {
            //     scrnMsg.E.no(i).xxChkBox_E.setInputProtected(true);
            // } else {
            //     scrnMsg.E.no(i).xxChkBox_E.setInputProtected(false);
            // }
            scrnMsg.E.no(i).xxChkBox_E.setInputProtected(false);
            // END 2017/09/15 U.Kim [QC#18726, MOD]
            // START 2017/09/15 U.Kim [QC#18726, DEL]
            // preSerNum = scrnMsg.E.no(i).serNum_E.getValue();
            // END 2017/09/15 U.Kim [QC#18726, DEL]
            // add start 2017/02/01 CSA Defect#17406
            scrnMsg.E.no(i).t_MdlNm_E.setInputProtected(true);
            scrnMsg.E.no(i).mdseDescShortTxt_E.setInputProtected(true);
            // add end 2017/02/01 CSA Defect#17406
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
            // Order Header Details
            scrnMsg.billToLocNum_LK.setInputProtected(true);
            scrnMsg.billToLocNum_LK.clear();
            scrnMsg.billToLocNum.setInputProtected(true);
            scrnMsg.curLocNum_LK.setInputProtected(true);
            scrnMsg.curLocNum_LK.clear();
            scrnMsg.curLocNum.setInputProtected(true);
            scrnMsg.custIssPoNum.setInputProtected(true);
            // START 2018/04/11 K.Kitachi [QC#11642, ADD]
            scrnMsg.ctacPsnFirstNm_LK.setInputProtected(true);
            scrnMsg.ctacPsnFirstNm_LK.clear();
            scrnMsg.ctacPsnFirstNm.setInputProtected(true);
            scrnMsg.ctacPsnLastNm.setInputProtected(true);
            scrnMsg.ctacPsnEmlAddr.setInputProtected(true);
            // END 2018/04/11 K.Kitachi [QC#11642, ADD]
            scrnMsg.xxDsMultMsgDplyTxt.setInputProtected(true);

            // Shipping Information Details
            scrnMsg.frtCondDescTxt_LK.setInputProtected(true);
            scrnMsg.frtCondDescTxt_LK.clear();
            scrnMsg.frtCondDescTxt.setInputProtected(true);
            scrnMsg.shpgSvcLvlCd.setInputProtected(true);
            scrnMsg.carrSvcLvlDescTxt_LK.setInputProtected(true);
            scrnMsg.carrSvcLvlDescTxt_LK.clear();
            scrnMsg.carrSvcLvlDescTxt.setInputProtected(true);
            scrnMsg.carrAcctNum.setInputProtected(true);
            // add start 2019/01/21 QC#27304
            scrnMsg.scrLbNm.setInputProtected(true);
            scrnMsg.shpgInstnCmntTxt.setInputProtected(true);
            // add end 2019/01/21 QC#27304

            // Payment Details
            scrnMsg.invCmntTxt.setInputProtected(true);
            scrnMsg.dsPmtMethCd.setInputProtected(true);

            // Order History
            scrnMsg.xxCondCd.setInputProtected(true);

            // Line Details(Supply Order Mode)
            scrnMsg.mdseCd_LK.setInputProtected(true);
            scrnMsg.mdseCd_LK.clear();
            scrnMsg.mdseCd.setInputProtected(true);
            // add start 2016/10/13 CSA Defect#9885
            scrnMsg.t_MdlId_S.setInputProtected(true);
            // add end 2016/10/13 CSA Defect#9885
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                scrnMsg.C.no(i).xxChkBox_C.setInputProtected(true);
                scrnMsg.C.no(i).ordCustUomQty_C.setInputProtected(true);
            }

            // Line Details(Supply Order Edit Mode)
            for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
                scrnMsg.E.no(i).xxChkBox_E.setInputProtected(true);
                scrnMsg.E.no(i).ordCustUomQty_E.setInputProtected(true);
           }
        }
    }

    /**
     * Alternate table row color
     * @param scrnMsg NSAL0990BMsg
     */
    public static void alternateTableRowColor(NSAL0990BMsg scrnMsg) {
        S21TableColorController control = new S21TableColorController(NSAL0990Constant.SCR_ID_00, scrnMsg);
        if (NSAL0990Constant.MODE_1.equals(scrnMsg.xxScrDply.getValue())) {
            control.setAlternateRowsBG(NSAL0990Bean.B, scrnMsg.B);
            control.setAlternateRowsBG(NSAL0990Bean.C, scrnMsg.C);
            control.clearRowsBG(NSAL0990Bean.E, scrnMsg.E);
        } else {
            control.setAlternateRowsBG(NSAL0990Bean.E, scrnMsg.E);
            control.clearRowsBG(NSAL0990Bean.B, scrnMsg.B);
            control.clearRowsBG(NSAL0990Bean.C, scrnMsg.C);
        }
    }

    /**
     * Focus item
     * @param scrnMsg NSAL0990BMsg
     */
    public static void focusItem(NSAL0990BMsg scrnMsg) {
        scrnMsg.setFocusItem(scrnMsg.billToLocNum);
    }

    /**
     * guiAttributeClear
     * @param scrnMsg NSAL0990BMsg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NSAL0990BMsg scrnMsg, String[][] baseContents) {
        S21SortColumnIMGController.clearIMG(NSAL0990Constant.SCR_ID_00, scrnMsg, baseContents);
        scrnMsg.clearAllGUIAttribute(NSAL0990Constant.SCR_ID_00);
    }

    /**
     * Check input
     * @param scrnMsg NSAL0990BMsg
     */
    public static void addCheckItem(NSAL0990BMsg scrnMsg) {
        // mod start 2019/01/21 QC#27304
        if (hasValue(scrnMsg.billToLocNum)) {
            scrnMsg.addCheckItem(scrnMsg.billToLocNum);
        }
        if (hasValue(scrnMsg.curLocNum)) {
            scrnMsg.addCheckItem(scrnMsg.curLocNum);
        }
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum);
        // START 2018/04/11 K.Kitachi [QC#11642, ADD]
        scrnMsg.addCheckItem(scrnMsg.ctacPsnFirstNm);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnLastNm);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnEmlAddr);
        // END 2018/04/11 K.Kitachi [QC#11642, ADD]
        scrnMsg.addCheckItem(scrnMsg.xxDsMultMsgDplyTxt);
        if (hasValue(scrnMsg.frtCondDescTxt)) {
            scrnMsg.addCheckItem(scrnMsg.frtCondDescTxt);
        }
        scrnMsg.addCheckItem(scrnMsg.frtCondCd);
        scrnMsg.addCheckItem(scrnMsg.carrSvcLvlDescTxt);
        scrnMsg.addCheckItem(scrnMsg.carrAcctNum);
        // START 2019/06/19 [QC#50879, ADD]
        scrnMsg.addCheckItem(scrnMsg.shpgInstnCmntTxt);
        // END 2019/06/19 [QC#50879, ADD]
        scrnMsg.addCheckItem(scrnMsg.invCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.dsPmtMethCd);
        if (hasValue(scrnMsg.shpgSvcLvlCd)) {
            scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd);
        }
        // START 2017/10/18 M.Kidokoro [QC#20246, ADD]
        if (hasValue(scrnMsg.scrLbNm)) {
            scrnMsg.addCheckItem(scrnMsg.scrLbNm);
        }
        // END 2017/10/18 M.Kidokoro [QC#20246, ADD]
        // mod end 2019/01/21 QC#27304
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).ordCustUomQty_C);
        }
        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.E.no(i).ordCustUomQty_E);
        }
    }

    // add start 2019/01/21 QC#27304
    /**
     * Check input Submit
     * @param scrnMsg NSAL0990BMsg
     */
    public static void addCheckItemSubmit(NSAL0990BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.billToLocNum);
        scrnMsg.addCheckItem(scrnMsg.curLocNum);
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnFirstNm);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnLastNm);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnEmlAddr);
        scrnMsg.addCheckItem(scrnMsg.xxDsMultMsgDplyTxt);
        scrnMsg.addCheckItem(scrnMsg.frtCondDescTxt);
        scrnMsg.addCheckItem(scrnMsg.frtCondCd);
        scrnMsg.addCheckItem(scrnMsg.carrSvcLvlDescTxt);
        scrnMsg.addCheckItem(scrnMsg.carrAcctNum);
        // START 2019/06/19 [QC#50879, ADD]
        scrnMsg.addCheckItem(scrnMsg.shpgInstnCmntTxt);
        // END 2019/06/19 [QC#50879, ADD]
        scrnMsg.addCheckItem(scrnMsg.invCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.dsPmtMethCd);
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd);
        scrnMsg.addCheckItem(scrnMsg.scrLbNm);
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).ordCustUomQty_C);
        }
        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.E.no(i).ordCustUomQty_E);
        }
    }
    // add end 2019/01/21 QC#27304

    /**
     * @param a BigDecimal
     * @param b BigDecimal
     * @return result boolean
     */
    public static boolean isEqualNum(BigDecimal a, BigDecimal b) {
        if (a == null && b == null) {
            return true;
        }
        if (a != null && b != null && a.compareTo(b) == 0) {
            return true;
        }
        return false;
    }

    /**
     * Format item
     * @param scrnMsg NSAL0990BMsg
     */
    public static void formatItem(NSAL0990BMsg scrnMsg) {
        scrnMsg.bwPrrtQty_A.setAppFracDigit(0);
        scrnMsg.colorPrrtQty_A.setAppFracDigit(0);
        scrnMsg.totQty_A.setAppFracDigit(0);
        if (ZYPCommonFunc.hasValue(scrnMsg.aftDeclPntDigitNum)) {
            int aftDeclPntDigitNum = scrnMsg.aftDeclPntDigitNum.getValueInt();
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                scrnMsg.C.no(i).entCpoDtlDealSlsAmt_C.setAppFracDigit(aftDeclPntDigitNum);
                scrnMsg.C.no(i).entDealNetUnitPrcAmt_C.setAppFracDigit(aftDeclPntDigitNum);
            }
            scrnMsg.xxTotAmt_C.setAppFracDigit(aftDeclPntDigitNum);
            for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
                scrnMsg.E.no(i).entCpoDtlDealSlsAmt_E.setAppFracDigit(aftDeclPntDigitNum);
                scrnMsg.E.no(i).entDealNetUnitPrcAmt_E.setAppFracDigit(aftDeclPntDigitNum);
            }
            scrnMsg.xxTotAmt_E.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.xxTotTaxAmt_E.setAppFracDigit(aftDeclPntDigitNum);
            // add start 2017/01/06 CSA Defect#16012
            scrnMsg.totRevAmt_01.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.totCostAmt_01.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.totPrftAmt_01.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.totRevAmt_02.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.totCostAmt_02.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.totPrftAmt_02.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.totRevAmt_03.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.totCostAmt_03.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.totPrftAmt_03.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.totRevAmt_04.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.totCostAmt_04.setAppFracDigit(aftDeclPntDigitNum);
            scrnMsg.totPrftAmt_04.setAppFracDigit(aftDeclPntDigitNum);
            // add end 2017/01/06 CSA Defect#16012
        }
    }

    /**
     * <pre>
     * The pop up parameter is cleared.
     * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void clearPopUpParam(NSAL0990BMsg scrnMsg) {
        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();
        scrnMsg.xxPopPrm_PA.clear();
        scrnMsg.xxPopPrm_PB.clear();
        scrnMsg.xxPopPrm_PC.clear();
        scrnMsg.xxPopPrm_PD.clear();
        scrnMsg.xxPopPrm_PE.clear();
        scrnMsg.xxPopPrm_PF.clear();
        scrnMsg.xxPopPrm_PG.clear();
        scrnMsg.xxPopPrm_PH.clear();
        scrnMsg.xxPopPrm_PI.clear();
        scrnMsg.xxPopPrm_PJ.clear();
        // Add Start 2018/07/30 QC#14307
        scrnMsg.xxPopPrm_PJ.clear();
        scrnMsg.xxPopPrm_PK.clear();
        scrnMsg.xxPopPrm_PL.clear();
        scrnMsg.xxPopPrm_PM.clear();
        scrnMsg.xxPopPrm_PN.clear();
        scrnMsg.xxPopPrm_PO.clear();
        scrnMsg.xxPopPrm_PP.clear();
        scrnMsg.xxPopPrm_PQ.clear();
        scrnMsg.xxPopPrm_PR.clear();
        scrnMsg.xxPopPrm_PS.clear();
        scrnMsg.xxPopPrm_PT.clear();
        scrnMsg.xxPopPrm_PU.clear();
        scrnMsg.xxPopPrm_PV.clear();
        scrnMsg.xxPopPrm_PW.clear();
        scrnMsg.xxPopPrm_PX.clear();
        scrnMsg.xxPopPrm_PY.clear();
        scrnMsg.xxPopPrm_PZ.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();
        scrnMsg.xxPopPrm_14.clear();
        scrnMsg.xxPopPrm_15.clear();

        // Add End 2018/07/30 QC#14307
        // START 2018/04/11 K.Kitachi [QC#11642, ADD]
        scrnMsg.dsCtacPntPk_P0.clear();
        scrnMsg.dsCtacPntPk_P1.clear();
        scrnMsg.dsCtacPntPk_P2.clear();
        // END 2018/04/11 K.Kitachi [QC#11642, ADD]
        // START 2016/02/23 T.Tsuchida [QC#4117, MOD]
        scrnMsg.svcMachMstrPk_P.clear();
        scrnMsg.dsContrDtlPk_P.clear();
        // END 2016/02/23 T.Tsuchida [QC#4117, MOD]
        ZYPTableUtil.clear(scrnMsg.P);
    }

    /**
     * Activate screen items
     * @param scrnMsg NSAL0990BMsg
     */
    public static void activateScreenItemsForAdd(NSAL0990BMsg scrnMsg) {
        //Line Details(Supply Order Mode)
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            // START 2017/09/15 U.Kim [QC#18726, MOD]
            // if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.C.no(i).xxDplyCtrlFlg_C.getValue())) {
            //     scrnMsg.C.no(i).xxChkBox_C.setInputProtected(true);
            // } else {
            //     scrnMsg.C.no(i).xxChkBox_C.setInputProtected(false);
            // }
            scrnMsg.C.no(i).xxChkBox_C.setInputProtected(false);
            // END 2017/09/15 U.Kim [QC#18726, MOD]
        }
    }

    /**
     * Set Init Item
     * @param scrnMsg NSAL0990BMsg
     */
    public static void setInitItem(NSAL0990BMsg scrnMsg) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotAmt_C, BigDecimal.ZERO);
    }

    /**
     * Set Protect By Fright Cond Code
     * @param scrnMsg NSAL0990BMsg
     */
    public static void setProtectByFrtCond(NSAL0990BMsg scrnMsg) {

        // QC#23726 Mod Start
//        if (FRT_COND.COLLECT.equals(scrnMsg.frtCondCd.getValue())) {
//            scrnMsg.carrAcctNum.setInputProtected(false);
//            scrnMsg.carrSvcLvlDescTxt.setInputProtected(false);
//            scrnMsg.carrSvcLvlDescTxt_LK.setInputProtected(false);
//            scrnMsg.carrSvcLvlDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
//        } else {
//            scrnMsg.carrAcctNum.setInputProtected(true);
//            scrnMsg.carrSvcLvlDescTxt.setInputProtected(true);
//            scrnMsg.carrAcctNum.clear();
//            // add start 2016/10/19 CSA Defect#15344
//            scrnMsg.carrSvcLvlCd.clear();
//            // add end 2016/10/19 CSA Defect#15344
//            scrnMsg.carrSvcLvlDescTxt.clear();
//            scrnMsg.carrSvcLvlDescTxt_LK.setInputProtected(true);
//            scrnMsg.carrSvcLvlDescTxt_LK.clear();
//        }

        scrnMsg.carrSvcLvlDescTxt.setInputProtected(false);
        scrnMsg.carrSvcLvlDescTxt_LK.setInputProtected(false);
        scrnMsg.carrSvcLvlDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);

        if (FRT_COND.COLLECT.equals(scrnMsg.frtCondCd.getValue())) {
            scrnMsg.carrAcctNum.setInputProtected(false);
        } else {
            scrnMsg.carrAcctNum.setInputProtected(true);
            // QC#23726-1 Add Start
            scrnMsg.carrAcctNum.clear();
            // QC#23726-1 Add End
        }
        // QC#23726 Mod End

    }

    /**
     * Set Protect By Payment Method
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0990BMsg
     */
    public static void setProtectByPmtMeth(EZDCommonHandler handler, NSAL0990BMsg scrnMsg) {

        if (DS_PMT_METH.CREDIT_CARD.equals(scrnMsg.dsPmtMethCd.getValue())) {
            handler.setButtonEnabled(NSAL0990Constant.BTN_CREDIT_CARD, true);
        } else {
            handler.setButtonEnabled(NSAL0990Constant.BTN_CREDIT_CARD, false);
        }
    }

    // QC#29121
//    /**
//     * Get Param NMAL6050 For Freight Term
//     * @param scrnMsg NSAL0990BMsg
//     * @return Param NMAL6050
//     */
//    public static Object[] getParamNMAL6050ForFrtTerm(NSAL0990BMsg scrnMsg) {
//
//        clearPopUpParam(scrnMsg);
//
//        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
//
//        // [0]:TBL_NM
//        scrnMsg.xxPopPrm_P0.setValue("FRT_COND");
//        paramList.add(scrnMsg.xxPopPrm_P0);
//
//        // [1]:TBL_CD_COL_NM
//        scrnMsg.xxPopPrm_P1.setValue("FRT_COND_CD");
//        paramList.add(scrnMsg.xxPopPrm_P1);
//
//        // [2]:TBL_NM_COL_NM
//        scrnMsg.xxPopPrm_P2.setValue("FRT_COND_DESC_TXT");
//        paramList.add(scrnMsg.xxPopPrm_P2);
//
//        // [3]:TBL_SORT_COL_NM
//        scrnMsg.xxPopPrm_P3.setValue("FRT_COND_SORT_NUM");
//        paramList.add(scrnMsg.xxPopPrm_P3);
//
//        // [4]:SCR_NM
//        scrnMsg.xxPopPrm_P4.setValue("Freight Terms Search");
//        paramList.add(scrnMsg.xxPopPrm_P4);
//
//        // [5]:HDR_CD_LB_NM
//        scrnMsg.xxPopPrm_P5.setValue("Freight Terms Code");
//        paramList.add(scrnMsg.xxPopPrm_P5);
//
//        // [6]:HDR_NM_LB_NM
//        scrnMsg.xxPopPrm_P6.setValue("Freight Terms Name");
//        paramList.add(scrnMsg.xxPopPrm_P6);
//
//        // [7]:DTL_CD_LB_NM
//        scrnMsg.xxPopPrm_P7.setValue("Freight Terms Code");
//        paramList.add(scrnMsg.xxPopPrm_P7);
//
//        // [8]:DTL_NM_LB_NM
//        scrnMsg.xxPopPrm_P8.setValue("Freight Terms Name");
//        paramList.add(scrnMsg.xxPopPrm_P8);
//
//        // [9]:COND_CD
//        scrnMsg.xxPopPrm_P9.clear();
//        paramList.add(scrnMsg.frtCondCd);
//
//        // [10]:COND_NM
//        paramList.add(scrnMsg.frtCondDescTxt);
//
//        return paramList.toArray(new EZDBItem[paramList.size()]);
//    }

    /**
     * Get Param NWAL1130 For Carrier Service Level
     * @param scrnMsg NSAL0990BMsg
     * @return Param NWAL1130 Carrier Service Level
     */
    public static Object[] getParamNWAL1130ForCarrSvcLvl(NSAL0990BMsg scrnMsg) {

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Carrier Service Level Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("    CSL.EZCANCELFLAG ");
        sb.append("   ,CSL.GLBL_CMPY_CD ");
        sb.append("   ,CSL.CARR_SVC_LVL_CD ");
        sb.append("   ,CSL.CARR_SVC_LVL_DESC_TXT ");
        sb.append("   ,CSL.CARR_SVC_LVL_SORT_NUM ");
        sb.append("FROM  ");
        sb.append("    FRT_COND_SVC_LVL_RELN RELN ");
        sb.append("   ,DS_ORD_TP_PROC_DFN OTD ");
        sb.append("   ,CARR_SVC_LVL CSL ");
        // 2018/12/12 Add Start QC#29315
        if (!FRT_COND.COLLECT.equals(scrnMsg.frtCondCd.getValue())) {
            sb.append("   ,SHPG_SVC_LVL_CARR_RELN SCRL ");
            sb.append("   ,(SELECT ");
            sb.append("         DC.GLBL_CMPY_CD AS GLBL_CMPY_CD ");
            sb.append("        ,DC.VND_CD AS VND_CD ");
            sb.append("     FROM ");
            sb.append("         DS_ACCT_CARR DC ");
            sb.append("        ,SHIP_TO_CUST SC ");
            sb.append("     WHERE ");
            sb.append("         SC.GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
            sb.append("     AND SC.EZCANCELFLAG    = '0' ");
            sb.append("     AND SC.SHIP_TO_CUST_CD  = '").append(scrnMsg.curLocNum.getValue()).append("' ");
            sb.append("     AND DC.GLBL_CMPY_CD    = SC.GLBL_CMPY_CD ");
            // Add Start 2018/12/25 QC#29744
            sb.append("     AND DC.DS_ACCT_NUM     IS NULL ");
            // Add End 2018/12/25 QC#29744
            sb.append("     AND DC.DS_BIZ_AREA_CD  = '").append(scrnMsg.dsBizAreaCd.getValue()).append("' ");
            sb.append("     AND DC.LOC_NUM         = SC.LOC_NUM ");
            sb.append("     AND DC.LINE_BIZ_TP_CD  = '").append(scrnMsg.svcByLineBizTpCd.getValue()).append("' ");
            sb.append("     AND DC.EFF_FROM_DT     <= '").append(ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue())).append("' ");
            sb.append("     AND (DC.EFF_THRU_DT IS NULL ");
            sb.append("     OR   DC.EFF_THRU_DT    >= '").append(ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue())).append("') ");
            sb.append("     AND DC.EZCANCELFLAG    = '0' ");
            sb.append("     UNION ALL ");
            sb.append("     SELECT ");
            sb.append("         GLBL_CMPY_CD ");
            sb.append("        ,VND_CD ");
            sb.append("     FROM ");
            sb.append("         DS_ACCT_CARR ");
            sb.append("     WHERE ");
            sb.append("         GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
            // Add Start 2018/12/25 QC#29744
            sb.append("     AND LOC_NUM         IS NULL ");
            // Add End 2018/12/25 QC#29744
            sb.append("     AND DS_BIZ_AREA_CD  = '").append(scrnMsg.dsBizAreaCd.getValue()).append("' ");
            sb.append("     AND EZCANCELFLAG    = '0' ");
            sb.append("     AND LINE_BIZ_TP_CD  = '").append(scrnMsg.svcByLineBizTpCd.getValue()).append("' ");
            sb.append("     AND EFF_FROM_DT     <= '").append(ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue())).append("' ");
            sb.append("     AND (EFF_THRU_DT IS NULL ");
            sb.append("     OR   EFF_THRU_DT    >= '").append(ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue())).append("') ");
            sb.append("     AND DS_ACCT_NUM     = '").append(scrnMsg.curLocAcctNum.getValue()).append("' ");
            sb.append("     AND NOT EXISTS ");
            sb.append("         (SELECT ");
            sb.append("     1 ");
            sb.append("         FROM ");
            sb.append("             DS_ACCT_CARR DC ");
            sb.append("            ,SHIP_TO_CUST SC ");
            sb.append("         WHERE ");
            sb.append("             SC.GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
            sb.append("         AND SC.EZCANCELFLAG    = '0' ");
            sb.append("         AND SC.SHIP_TO_CUST_CD  = '").append(scrnMsg.curLocNum.getValue()).append("' ");
            sb.append("         AND DC.GLBL_CMPY_CD    = SC.GLBL_CMPY_CD ");
            // Add Start 2018/12/25 QC#29744
            sb.append("         AND DC.DS_ACCT_NUM     IS NULL ");
            // Add End 2018/12/25 QC#29744
            sb.append("         AND DC.DS_BIZ_AREA_CD  = '").append(scrnMsg.dsBizAreaCd.getValue()).append("' ");
            sb.append("         AND DC.LOC_NUM         = SC.LOC_NUM ");
            sb.append("         AND DC.LINE_BIZ_TP_CD  = '").append(scrnMsg.svcByLineBizTpCd.getValue()).append("' ");
            sb.append("         AND DC.EFF_FROM_DT     <= '").append(ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue())).append("' ");
            sb.append("         AND (DC.EFF_THRU_DT IS NULL                             ");
            sb.append("         OR   DC.EFF_THRU_DT    >= '").append(ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue())).append("') ");
            sb.append("         AND DC.EZCANCELFLAG    = '0' ");
            sb.append("         )" );
            sb.append("   ) DAC ");
        }
        sb.append("WHERE ");
        if (!FRT_COND.COLLECT.equals(scrnMsg.frtCondCd.getValue())) {
            sb.append("    DAC.GLBL_CMPY_CD     = SCRL.GLBL_CMPY_CD ");
            sb.append("AND DAC.VND_CD           = SCRL.CARR_CD ");
            sb.append("AND SCRL.GLBL_CMPY_CD    = RELN.GLBL_CMPY_CD ");
            sb.append("AND SCRL.SHPG_SVC_LVL_CD = RELN.SHPG_SVC_LVL_CD ");
            // Add Start 2018/12/25 QC#29744
            sb.append("AND SCRL.CARR_SVC_LVL_CD = RELN.CARR_SVC_LVL_CD ");
            // Add End 2018/12/25 QC#29744
            sb.append("AND SCRL.EZCANCELFLAG    = '0' ");
            sb.append("AND RELN.GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        } else {
            sb.append("    RELN.GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        }
        sb.append("AND RELN.EZCANCELFLAG    = '0' ");
        sb.append("AND OTD.GLBL_CMPY_CD     = RELN.GLBL_CMPY_CD ");
        sb.append("AND OTD.EZCANCELFLAG     = '0' ");
        sb.append("AND OTD.DS_ORD_TP_CD     = '").append(scrnMsg.dsOrdTpCd.getValue()).append("' ");
        sb.append("AND OTD.LINE_BIZ_TP_CD   = RELN.LINE_BIZ_TP_CD ");
        sb.append("AND RELN.FRT_COND_CD     = '").append(scrnMsg.frtCondCd.getValue()).append("' ");
        sb.append("AND RELN.SHPG_SVC_LVL_CD = '").append(scrnMsg.shpgSvcLvlCd.getValue()).append("' ");
        sb.append("AND CSL.GLBL_CMPY_CD     = RELN.GLBL_CMPY_CD ");
        sb.append("AND CSL.EZCANCELFLAG     = '0' ");
        sb.append("AND CSL.CARR_SVC_LVL_CD  = RELN.CARR_SVC_LVL_CD ");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray = new Object[4];
        whereArray[0] = "Carr Svc Lvl Nm";
        whereArray[1] = "CARR_SVC_LVL_DESC_TXT";
        // S21_NA#8393 Mod Start
//        whereArray[2] = scrnMsg.carrSvcLvlDescTxt.getValue();
        if (ZYPCommonFunc.hasValue(scrnMsg.carrSvcLvlDescTxt)) {
            whereArray[2] = scrnMsg.carrSvcLvlDescTxt.getValue();
        } else {
            whereArray[IDX_2] = NSAL0990Constant.PERCENT;
        }
        // S21_NA#8393 Mod End
        whereArray[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Carr Svc Lvl Cd";
        columnArray0[1] = "CARR_SVC_LVL_CD";
        columnArray0[2] = BigDecimal.valueOf(13);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Carr Svc Lvl Nm";
        columnArray1[1] = "CARR_SVC_LVL_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "CARR_SVC_LVL_SORT_NUM";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1130 For Mdse
     * @param scrnMsg NSAL0990BMsg
     * @return Param NWAL1130 Mdse
     */
    public static Object[] getParamNWAL1130ForMdse(NSAL0990BMsg scrnMsg) {

        Object[] params = new Object[7];
        params[0] = "";
        // START 2016/09/23 Y.Zhang [QC#12582, MOD]
        params[1] = "Supply Order Item Search";
        // START 2016/09/23 Y.Zhang [QC#12582, MOD]

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("    A.EZCANCELFLAG ");
        sb.append("   ,A.GLBL_CMPY_CD ");
        sb.append("   ,A.MDSE_CD ");
        sb.append("   ,A.MISC_DESC_TXT ");
        sb.append("FROM ");
        sb.append("    SVC_SPLY_MISC A ");
        sb.append("WHERE ");
        sb.append("    A.GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append("AND A.EZCANCELFLAG    = '0' ");
        sb.append("AND A.ACTV_FLG        = '").append(ZYPConstant.FLG_ON_Y).append("' ");
        sb.append("AND A.EFF_FROM_DT     <= '").append(scrnMsg.slsDt.getValue()).append("' ");
        sb.append("AND (A.EFF_THRU_DT    >= '").append(scrnMsg.slsDt.getValue()).append("' OR A.EFF_THRU_DT IS NULL) ");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        // START 2016/09/23 Y.Zhang [QC#12582, MOD]
        whereArray0[0] = "Item Cd";
        // END 2016/09/23 Y.Zhang [QC#12582, MOD]
        whereArray0[1] = "MDSE_CD";
        whereArray0[2] = scrnMsg.mdseCd.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        // START 2016/09/23 Y.Zhang [QC#12582, MOD]
        whereArray1[0] = "Item Name";
        // END 2016/09/23 Y.Zhang [QC#12582, MOD]
        whereArray1[1] = "MISC_DESC_TXT";
        whereArray1[2] = "%";
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        // START 2016/09/23 Y.Zhang [QC#12582, MOD]
        columnArray0[0] = "Item Cd";
        // END 2016/09/23 Y.Zhang [QC#12582, MOD]
        columnArray0[1] = "MDSE_CD";
        columnArray0[2] = BigDecimal.valueOf(16);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        // START 2016/09/23 Y.Zhang [QC#12582, MOD]
        columnArray1[0] = "Item Name";
        // END 2016/09/23 Y.Zhang [QC#12582, MOD]
        columnArray1[1] = "MISC_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(70);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "MDSE_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        return params;
    }

    // START 2017/09/15 U.Kim [QC#18726, ADD]
    /**
     * Get Param NLCL1000
     * @param scrnMsg NSAL0990BMsg
     * @return Param NLCL1000
     */
    public static Object[] getParamNLCL1000(NSAL0990BMsg scrnMsg) {
        clearPopUpParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, ZYPConstant.FLG_OFF_N);

        NSAL0990_MBMsgArray detailList = scrnMsg.M;

        if (NSAL0990Constant.MODE_1.equals(scrnMsg.xxScrDply.getValue())) {
            List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_C", ZYPConstant.FLG_ON_Y);
            int validCnt = 0;
            for (; validCnt < checkList.size(); validCnt++) {
                int slctLineNum = checkList.get(validCnt);
                NSAL0990_CBMsg itemLineMsg = scrnMsg.C.no(slctLineNum);
                // Set Detail List
                NSAL0990_MBMsg detailMsg = detailList.no(validCnt);
                ZYPEZDItemValueSetter.setValue(detailMsg.xxScrItem20Txt_P1, String.valueOf(slctLineNum));
                ZYPEZDItemValueSetter.setValue(detailMsg.rtlWhCd_P1, NSAL0990Constant.RTL_WH_CD_DS);
                ZYPEZDItemValueSetter.setValue(detailMsg.shipToCustCd_P1, scrnMsg.curLocNum);
                ZYPEZDItemValueSetter.setValue(detailMsg.mdseCd_P1, itemLineMsg.mdseCd_C);
                ZYPEZDItemValueSetter.setValue(detailMsg.ordQty_P1, itemLineMsg.ordCustUomQty_C);
                ZYPEZDItemValueSetter.setValue(detailMsg.rddDt_P1, ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue()));
            }
            detailList.setValidCount(validCnt);
        } else if (NSAL0990Constant.MODE_2.equals(scrnMsg.xxScrDply.getValue())) {
            List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.E, "xxChkBox_E", ZYPConstant.FLG_ON_Y);
            int validCnt = 0;
            for (; validCnt < checkList.size(); validCnt++) {
                int slctLineNum = checkList.get(validCnt);
                NSAL0990_EBMsg itemLineMsg = scrnMsg.E.no(slctLineNum);
                // Set Detail List
                NSAL0990_MBMsg detailMsg = detailList.no(validCnt);
                ZYPEZDItemValueSetter.setValue(detailMsg.xxScrItem20Txt_P1, String.valueOf(slctLineNum));
                ZYPEZDItemValueSetter.setValue(detailMsg.rtlWhCd_P1, NSAL0990Constant.RTL_WH_CD_DS);
                ZYPEZDItemValueSetter.setValue(detailMsg.shipToCustCd_P1, scrnMsg.curLocNum);
                ZYPEZDItemValueSetter.setValue(detailMsg.mdseCd_P1, itemLineMsg.mdseCd_E);
                ZYPEZDItemValueSetter.setValue(detailMsg.ordQty_P1, itemLineMsg.ordCustUomQty_E);
                ZYPEZDItemValueSetter.setValue(detailMsg.rddDt_P1, ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue()));
            }
            detailList.setValidCount(validCnt);
        }

        Object[] params = new Object[NSAL0990Constant.IDX_10];
        params[NSAL0990Constant.IDX_0] = scrnMsg.xxPopPrm_P0;
        params[NSAL0990Constant.IDX_1] = scrnMsg.xxPopPrm_P1;
        params[NSAL0990Constant.IDX_2] = scrnMsg.xxPopPrm_P2;
        params[NSAL0990Constant.IDX_3] = scrnMsg.xxPopPrm_P3;
        params[NSAL0990Constant.IDX_4] = scrnMsg.xxPopPrm_P4;
        params[NSAL0990Constant.IDX_5] = scrnMsg.xxPopPrm_P5;
        params[NSAL0990Constant.IDX_6] = scrnMsg.xxPopPrm_P6;
        params[NSAL0990Constant.IDX_7] = scrnMsg.xxPopPrm_P7;
        params[NSAL0990Constant.IDX_8] = scrnMsg.xxPopPrm_P8;
        params[NSAL0990Constant.IDX_9] = detailList;
        return params;
    }
    // END 2017/09/15 U.Kim [QC#18726, ADD]

    // add start 2016/07/06 CSA Defect#9630
    public static void setXxDplyCtrlFlg(NSAL0990BMsg scrnMsg) {

        setValue(scrnMsg.xxDplyCtrlFlg, ZYPConstant.FLG_OFF_N);

        if (NSAL0990Constant.MODE_1.equals(scrnMsg.xxScrDply.getValue())) {

            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                if ( scrnMsg.C.no(i).entDealNetUnitPrcAmt_C.getValue().compareTo(BigDecimal.ZERO) > 0) {
                    setValue(scrnMsg.xxDplyCtrlFlg, ZYPConstant.FLG_ON_Y);
                    return;
                }
            }
        } else if (NSAL0990Constant.MODE_2.equals(scrnMsg.xxScrDply.getValue())) {

            for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
                if ( scrnMsg.E.no(i).entDealNetUnitPrcAmt_E.getValue().compareTo(BigDecimal.ZERO) > 0) {
                    setValue(scrnMsg.xxDplyCtrlFlg, ZYPConstant.FLG_ON_Y);
                    return;
                }
            }
        }
    }
    // add end 2016/07/06 CSA Defect#9630
    // Add Start 2018/07/30 QC#14307
    /**
     * Activate Speceal Instruction button
     * @param handler S21CommonHandler
     * @param scrnMsg NSAL0990BMsg
     */
    public static void activeSpclInstructionBtn(S21CommonHandler handler, NSAL0990BMsg scrnMsg) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.billToLocNum_SI) && !ZYPCommonFunc.hasValue(scrnMsg.curLocNum_SI)) {
            handler.setButtonEnabled(NSAL0990Constant.BTN_SPCL_INSTRUCTION, false);
        } else {
            handler.setButtonEnabled(NSAL0990Constant.BTN_SPCL_INSTRUCTION, true);
        }
    }

    /**
     * Get Param NMAL3300
     * @param scrnMsg NSAL0990BMsg
     * @return Param NMAL3300
     */
    public static Object[] getParamNMAL3300(NSAL0990BMsg scrnMsg) {
        clearPopUpParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, scrnMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NSAL0990Constant.BIZ_ID);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.funcMstrIdDescTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, scrnMsg.dsTrxRuleTpCd);

        List<Object> paramList = new ArrayList<Object>();
        paramList.add(scrnMsg.xxPopPrm_P0);
        paramList.add(scrnMsg.xxPopPrm_P1);
        paramList.add(scrnMsg.xxPopPrm_P2);
        paramList.add(scrnMsg.xxPopPrm_P3);
        paramList.add(scrnMsg.xxPopPrm_P4);
        paramList.add("S");

        // Customer Special Instruction Line
        int index = 0;
        if (ZYPCommonFunc.hasValue(scrnMsg.billToLocNum_SI)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(index).dsAcctNum_S, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(index).billToCustCd_S, scrnMsg.billToLocNum_SI);
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(index++).shipToCustCd_S, "");
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.curLocNum_SI)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(index).dsAcctNum_S, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(index).billToCustCd_S, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(index++).shipToCustCd_S, scrnMsg.curLocNum_SI);
        }
        scrnMsg.S.setValidCount(index);
        paramList.add(scrnMsg.S);

        // Line of Business Code
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, scrnMsg.svcByLineBizTpCd);
        paramList.add(scrnMsg.xxPopPrm_P5);

        return paramList.toArray(new Object[0]);
    }
    // Add End 2018/07/30 QC#14307

    // QC#29121
    public static Object[] getParamNWAL1130ForFrtTerm(NSAL0990BMsg scrnMsg) {
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Supply Order Freight Terms Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("        GLBL_CMPY_CD");
        sb.append("        , EZCANCELFLAG");
        sb.append("        , ROWNUM");
        sb.append("        , FRT_COND_CD");
        sb.append("        , FRT_COND_DESC_TXT");
        sb.append("    FROM");
        sb.append("        (");
        sb.append("            SELECT DISTINCT");
        sb.append("                    FC.GLBL_CMPY_CD");
        sb.append("                    , FC.EZCANCELFLAG");
        sb.append("                    , FC.FRT_COND_CD       AS FRT_COND_CD");
        sb.append("                    , FC.FRT_COND_DESC_TXT AS FRT_COND_DESC_TXT");
        sb.append("                    , FC.FRT_COND_SORT_NUM");
        sb.append("                FROM");
        sb.append("                    FRT_COND                 FC");
        sb.append("                    , FRT_COND_SVC_LVL_RELN  FCSLR");
        sb.append("                    , DS_ORD_TP_PROC_DFN     DFN");
        sb.append("                WHERE");
        sb.append("                    FC.GLBL_CMPY_CD          = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("                    AND FC.EZCANCELFLAG      = '0'");
        sb.append("                    AND FC.GLBL_CMPY_CD      = FCSLR.GLBL_CMPY_CD");
        sb.append("                    AND FC.FRT_COND_CD       = FCSLR.FRT_COND_CD");
        sb.append("                    AND FCSLR.EZCANCELFLAG   = '0'");
        sb.append("                    AND FCSLR.LINE_BIZ_TP_CD = DFN.LINE_BIZ_TP_CD");
        sb.append("                    AND DFN.DS_ORD_TP_CD     = '").append(scrnMsg.dsOrdTpCd.getValue()).append("'");
        sb.append("                    AND FCSLR.GLBL_CMPY_CD   = DFN.GLBL_CMPY_CD");
        sb.append("                    AND DFN.EZCANCELFLAG     = '0'");
        sb.append("                ORDER BY");
        sb.append("                    FC.FRT_COND_SORT_NUM");
        sb.append("        )");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Freight Terms Code";
        whereArray0[1] = "FRT_COND_CD";
        whereArray0[2] = "";
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Freight Terms Name";
        whereArray1[1] = "FRT_COND_DESC_TXT";
        String whereTxt = "%";
        if (ONBLUR_DERIVEFROM_FREIGHT_TERMS.equals(scrnMsg.xxScrEventNm.getValue())) {
            whereTxt = scrnMsg.frtCondDescTxt.getValue();
        }
        whereArray1[2] = whereTxt;
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "#";
        columnArray0[1] = "ROWNUM";
        columnArray0[2] = BigDecimal.valueOf(3);
        columnArray0[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Freight Terms Code";
        columnArray1[1] = "FRT_COND_CD";
        columnArray1[2] = BigDecimal.valueOf(16);
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Freight Terms Name";
        columnArray2[1] = "FRT_COND_DESC_TXT";
        columnArray2[2] = BigDecimal.valueOf(70);
        columnArray2[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray2);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "ROWNUM";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        return params;
    }

    // add start 2019/01/21 QC#27304
    /**
     * getParamNWAL1130ForEditingList
     * @param scrnMsg NSAL0990BMsg
     * @return Object[]
     */
    public static Object[] getParamNWAL1130ForEditingList(NSAL0990BMsg scrnMsg) {
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Supply Order Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("     GLBL_CMPY_CD");
        sb.append("    ,EZCANCELFLAG");
        sb.append("    ,LAST_UPD_TS");
        sb.append("    ,SVC_SPLY_ORD_PK");
        sb.append("    ,SER_NUM");
        sb.append("    ,OWNR_ACCT_NUM");
        sb.append("    ,DS_ACCT_NM");
        sb.append("    ,FULL_PSN_NM");
        sb.append("    ,LISTAGG(RLN_SER_NUM ,', ') WITHIN GROUP (ORDER BY SVC_SPLY_ORD_PK) RLN_SER_NUM ");
        sb.append("FROM");
        sb.append("    (");
        sb.append("     SELECT DISTINCT ");
        sb.append("          A.GLBL_CMPY_CD");
        sb.append("         ,A.EZCANCELFLAG");
        sb.append("         ,TO_CHAR(TO_TIMESTAMP(A.EZUPTIME, 'YYYYMMDDHH24MISSFF3'), 'MM/DD/YYYY HH24:MI') LAST_UPD_TS");
        sb.append("         ,A.SVC_SPLY_ORD_PK");
        sb.append("         ,B.SER_NUM");
        sb.append("         ,B.OWNR_ACCT_NUM");
        sb.append("         ,E.DS_ACCT_NM");
        sb.append("         ,RTRIM(F.FIRST_NM || ' ' || F.LAST_NM) FULL_PSN_NM");
        sb.append("         ,D.SER_NUM RLN_SER_NUM");
        sb.append("     FROM");
        sb.append("          SVC_SPLY_ORD A");
        sb.append("         ,SVC_MACH_MSTR B");
        sb.append("         ,SVC_SPLY_ORD_DTL C");
        sb.append("         ,SVC_MACH_MSTR D");
        sb.append("         ,SELL_TO_CUST E");
        sb.append("         ,AUTH_PSN F");
        sb.append("         ,DS_CONTR_DTL G");
        sb.append("     WHERE");
        sb.append("             A.GLBL_CMPY_CD      = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("         AND A.EZCANCELFLAG      = '0'");
        sb.append("         AND A.GLBL_CMPY_CD      = B.GLBL_CMPY_CD");
        sb.append("         AND A.SVC_MACH_MSTR_PK  = B.SVC_MACH_MSTR_PK");
        sb.append("         AND B.EZCANCELFLAG      = '0'");
        sb.append("         AND A.GLBL_CMPY_CD      = C.GLBL_CMPY_CD(+)");
        sb.append("         AND A.SVC_SPLY_ORD_PK   = C.SVC_SPLY_ORD_PK(+)");
        sb.append("         AND A.SVC_MACH_MSTR_PK <> C.SVC_MACH_MSTR_PK(+)");
        sb.append("         AND C.EZCANCELFLAG(+)   = '0'");
        sb.append("         AND C.GLBL_CMPY_CD      = D.GLBL_CMPY_CD(+)");
        sb.append("         AND C.SVC_MACH_MSTR_PK  = D.SVC_MACH_MSTR_PK(+)");
        sb.append("         AND D.EZCANCELFLAG(+)   = '0'");
        sb.append("         AND B.GLBL_CMPY_CD      = E.GLBL_CMPY_CD(+)");
        sb.append("         AND B.OWNR_ACCT_NUM     = E.SELL_TO_CUST_CD(+)");
        sb.append("         AND E.EZCANCELFLAG(+)   = '0'");
        sb.append("         AND A.GLBL_CMPY_CD      = F.GLBL_CMPY_CD(+)");
        sb.append("         AND A.EZUPUSERID        = F.USR_NM(+)");
        sb.append("         AND F.EZCANCELFLAG(+)   = '0'");
        sb.append("         AND A.GLBL_CMPY_CD      = G.GLBL_CMPY_CD");
        sb.append("         AND A.DS_CONTR_DTL_PK   = G.DS_CONTR_DTL_PK");
        sb.append("         AND G.EZCANCELFLAG      = '0'");
        sb.append("     ORDER BY");
        sb.append("          B.SER_NUM");
        sb.append("         ,D.SER_NUM");
        sb.append("    ) ");
        sb.append("GROUP BY");
        sb.append("     GLBL_CMPY_CD");
        sb.append("    ,EZCANCELFLAG");
        sb.append("    ,LAST_UPD_TS");
        sb.append("    ,SVC_SPLY_ORD_PK");
        sb.append("    ,SER_NUM");
        sb.append("    ,OWNR_ACCT_NUM");
        sb.append("    ,DS_ACCT_NM");
        sb.append("    ,FULL_PSN_NM");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Order Serial";
        whereArray0[1] = "SER_NUM";
        whereArray0[2] = scrnMsg.serNum_BK.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Related Serial";
        whereArray1[1] = "RLN_SER_NUM";
        whereArray1[2] = "";
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Editor";
        whereArray2[1] = "FULL_PSN_NM";
        whereArray2[2] = scrnMsg.fullPsnNm.getValue();
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[4];
        whereArray3[0] = "Account";
        whereArray3[1] = "OWNR_ACCT_NUM";
        whereArray3[2] = "";
        whereArray3[3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray3);

        Object[] whereArray4 = new Object[4];
        whereArray4[0] = "Account Name";
        whereArray4[1] = "DS_ACCT_NM";
        whereArray4[2] = "";
        whereArray4[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray4);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Order Serial";
        columnArray0[1] = "SER_NUM";
        columnArray0[2] = BigDecimal.valueOf(12);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Related Serial";
        columnArray1[1] = "RLN_SER_NUM";
        columnArray1[2] = BigDecimal.valueOf(20);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Editor";
        columnArray2[1] = "FULL_PSN_NM";
        columnArray2[2] = BigDecimal.valueOf(15);
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Last Update Date";
        columnArray3[1] = "LAST_UPD_TS";
        columnArray3[2] = BigDecimal.valueOf(12);
        columnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Account";
        columnArray4[1] = "OWNR_ACCT_NUM";
        columnArray4[2] = BigDecimal.valueOf(8);
        columnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[4];
        columnArray5[0] = "Account Name";
        columnArray5[1] = "DS_ACCT_NM";
        columnArray5[2] = BigDecimal.valueOf(25);
        columnArray5[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);

        Object[] columnArray6 = new Object[4];
        columnArray6[0] = "";
        columnArray6[1] = "SVC_SPLY_ORD_PK";
        columnArray6[2] = BigDecimal.valueOf(0);
        columnArray6[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray6);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "LAST_UPD_TS";
        sortConditionArray0[1] = "DESC";
        sortConditionList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "SER_NUM";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        return params;
    }
    // add end 2019/01/21 QC#27304
}
