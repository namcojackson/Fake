/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0310.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.List;

import parts.common.EZDGUIAttribute;
import business.servlet.NSAL0310.NSAL0310BMsg;
import business.servlet.NSAL0310.NSAL0310Bean;
import business.servlet.NSAL0310.constant.NSAL0310Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/26   CUSA            SRAA            Create          N/A
 * 2015/10/08   Hitachi         T.Tomita        Update          N/A
 * 2016/01/05   Hitachi         T.Tomita        Update          QC#1029
 * 2016/02/16   Hitachi         A.Kohinata      Update          QC#3017
 * 2016/05/16   Hitachi         T.Tomita        Update          QC#4578
 * 2016/05/24   Hitachi         T.Mizuki        Update          QC#447
 * 2016/09/15   Hitachi         N.Arai          Update          QC#11616
 * 2016/11/22   Hitachi         K.Kojima        Update          QC#16168
 * 2017/03/01   Hitachi         N.Arai          Update          QC#17620
 * 2017/09/04   Hitachi         U.Kim           Update          QC#20856
 * 2018/10/30   Hitachi         K.Kim           Update          QC#28890
 * 2022/01/21   CITS            R.Cabral        Update          QC#59502
 * 2023/04/19   Hitachi         K.Watanabe      Update          QC#61146
 *</pre>
 */
public class NSAL0310CommonLogic {

    /**
     * activate Buttons
     * @param handler S21CommonHandler
     * @param scrnMsg NSAL0310BMsg
     * @param functionList List<String>
     */
    public static void activateButtons(S21CommonHandler handler, NSAL0310BMsg scrnMsg, List<String> functionList) {
        handler.setButtonProperties(NSAL0310Constant.BTN_CMN_CLEAR[0], NSAL0310Constant.BTN_CMN_CLEAR[1], NSAL0310Constant.BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(NSAL0310Constant.BTN_CMN_CLOSE[0], NSAL0310Constant.BTN_CMN_CLOSE[1], NSAL0310Constant.BTN_CMN_CLOSE[2], 1, null);
    }

    /**
     * addCheck Item
     * @param scrnMsg NSAL0310BMsg
     */
    public static void addCheckItem(NSAL0310BMsg scrnMsg) {
        // START 2018/10/30 [QC#28890, ADD]
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H);
        // END 2018/10/30 [QC#28890, ADD]
        scrnMsg.addCheckItem(scrnMsg.serNum);
        scrnMsg.addCheckItem(scrnMsg.mdlNm);
        // [QC#3017,MOD]START
        scrnMsg.addCheckItem(scrnMsg.xxComnScrColValTxt_H);
        // [QC#3017,MOD]END
        scrnMsg.addCheckItem(scrnMsg.xxScrItem29Txt);
        // START 2016/09/15 N.Arai [QC#11616, MOD]
        // scrnMsg.addCheckItem(scrnMsg.mdseNm);
        scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt);
        // END 2016/09/15 N.Arai [QC#11616, MOD]
        // START 2017/03/01 N.Arai [QC#17620, MOD]
//        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//            // Start 2016/05/24 T.Mizuki
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A);
//            // End 2016/05/24 T.Mizuki
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).contrEffFromDt_A);
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).contrEffThruDt_A);
//        }
        // END 2017/03/01 N.Arai [QC#17620, MOD]
    }

    // START 2017/03/01 N.Arai [QC#17620, MOD]
    /**
     * addCheck Item AddMachines
     * @param scrnMsg NSAL0310BMsg
     */
    public static void addCheckItem_AddMachines(NSAL0310BMsg scrnMsg) {
        addCheckItem(scrnMsg);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2017/09/04 U.Kim [QC#20856, ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_AA);
            // END 2017/09/04 U.Kim [QC#20856, ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).contrEffFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).contrEffThruDt_A);
        }
    }
    // END 2017/03/01 N.Arai [QC#17620, MOD]

    /**
     * setup Screen Items
     * @param scrnMsg NSAL0310BMsg
     * @param functionList List<String>
     */
    public static void setupScreenItems(NSAL0310BMsg scrnMsg, List<String> functionList) {
        activateScreenItems(scrnMsg, functionList);
        toggleTreeIcon(scrnMsg);
    }

    private static void activateScreenItems(NSAL0310BMsg scrnMsg, List<String> functionList) {
        // [QC#3017,MOD]START
        scrnMsg.dsAcctNm.setInputProtected(true);
        // [QC#3017,MOD]END
        // START 2023/04/19 K.Watanabe [QC#61146, ADD]
        scrnMsg.xxFileData.setInputProtected(true);
        // END 2023/04/19 K.Watanabe [QC#61146, ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).svcMachMstrPk_A.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A.setInputProtected(true);
            scrnMsg.A.no(i).mdlNm_A.setInputProtected(true);
            // START 2016/09/15 N.Arai [QC#11616, MOD]
            // scrnMsg.A.no(i).mdseNm_A.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A.setInputProtected(true);
            // END 2016/09/15 N.Arai [QC#11616, MOD]
            // mod start 2016/05/16 CSA Defect#4578
            scrnMsg.A.no(i).indCurLocNum_A.setInputProtected(true);
            // mod end 2016/05/16 CSA Defect#4578
            scrnMsg.A.no(i).shipToLocNm_A.setInputProtected(true);
            scrnMsg.A.no(i).xxGenlFldAreaTxt_A.setInputProtected(true);
            // START 2015/10/08 T.Tomita [N/A, DEL]
            // scrnMsg.A.no(i).xxChkBox_AB.setInputProtected(true);
            // END 2015/10/08 T.Tomita [N/A, DEL]
            // START 2022/01/21 R.Cabral [QC#59502, ADD]
            scrnMsg.A.no(i).mdseNm_A.setInputProtected(true);
            scrnMsg.A.no(i).prntDsContrDtlPk_A.setInputProtected(true);

            boolean hasContractItem = scrnMsg.A.no(i).dsContrDtlPk_A.getValue() != null;

            String svcMachTpCd = scrnMsg.A.no(i).svcMachTpCd_A.getValue();
            if (SVC_MACH_TP.ACCESSORY.equals(svcMachTpCd)) {
                if (hasContractItem) {
                    scrnMsg.A.no(i).xxChkBox_AA.setValue("Y");
                }
                scrnMsg.A.no(i).xxChkBox_AA.setInputProtected(hasContractItem);
            } else {
                if (hasContractItem) {
                    scrnMsg.A.no(i).xxChkBox_AM.setValue("Y");
                }
                scrnMsg.A.no(i).xxChkBox_AM.setInputProtected(hasContractItem);
            }

            scrnMsg.A.no(i).contrEffFromDt_A.setInputProtected(hasContractItem);
            scrnMsg.A.no(i).contrEffThruDt_A.setInputProtected(hasContractItem);
            // END   2022/01/21 R.Cabral [QC#59502, ADD]
        }
    }

    /**
     * alternate Table Row Color
     * @param scrnMsg NSAL0310BMsg
     */
    public static void alternateTableRowColor(NSAL0310BMsg scrnMsg) {
        S21TableColorController control = new S21TableColorController(NSAL0310Constant.SCR_ID_00, scrnMsg);
        control.setAlternateRowsBG(NSAL0310Bean.A, scrnMsg.A);
    }

    private static void addHiddenAttribute(NSAL0310BMsg scrnMsg, String attrNm, int idx) {
        EZDGUIAttribute attr = new EZDGUIAttribute(NSAL0310Constant.SCR_ID_00, String.format("%s#%d", attrNm, idx));
        attr.setStyleAttribute("width", "0");
        attr.setStyleAttribute("height", "0");
        attr.setVisibility(false);
        scrnMsg.addGUIAttribute(attr);
    }

    private static void clearAttribute(NSAL0310BMsg scrnMsg, String attrNm, int idx) {
        scrnMsg.clearGUIAttribute(NSAL0310Constant.SCR_ID_00, String.format("%s#%d", attrNm, idx));
    }

    /**
     * toggle Tree Icon
     * @param scrnMsg NSAL0310BMsg
     */
    public static void toggleTreeIcon(NSAL0310BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            clearAttribute(scrnMsg, NSAL0310Bean.xxChkBox_AM, i);
            clearAttribute(scrnMsg, NSAL0310Bean.xxChkBox_AA, i);
            clearAttribute(scrnMsg, NSAL0310Bean.xxLinkProt_AE, i);
            clearAttribute(scrnMsg, NSAL0310Bean.xxLinkProt_AC, i);
            clearAttribute(scrnMsg, NSAL0310Constant.IMG_NM_CLPS, i);
            clearAttribute(scrnMsg, NSAL0310Constant.IMG_NM_XPND, i);
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String xxDtlStsCd = scrnMsg.A.no(i).xxDtlStsCd_A.getValue();
            String svcMachTpCd = scrnMsg.A.no(i).svcMachTpCd_A.getValue();
            if (NSAL0310Constant.DTL_STS_CLPS.equals(xxDtlStsCd)) {
                addHiddenAttribute(scrnMsg, NSAL0310Bean.xxChkBox_AA, i);
                addHiddenAttribute(scrnMsg, NSAL0310Bean.xxLinkProt_AC, i);
                addHiddenAttribute(scrnMsg, NSAL0310Constant.IMG_NM_CLPS, i);
            } else if (NSAL0310Constant.DTL_STS_XPND.equals(xxDtlStsCd)) {
                addHiddenAttribute(scrnMsg, NSAL0310Bean.xxLinkProt_AE, i);
                addHiddenAttribute(scrnMsg, NSAL0310Constant.IMG_NM_XPND, i);
                if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
                    addHiddenAttribute(scrnMsg, NSAL0310Bean.xxChkBox_AA, i);
                } else if (SVC_MACH_TP.ACCESSORY.equals(svcMachTpCd)) {
                    addHiddenAttribute(scrnMsg, NSAL0310Bean.xxChkBox_AM, i);
                    addHiddenAttribute(scrnMsg, NSAL0310Bean.xxLinkProt_AC, i);
                    addHiddenAttribute(scrnMsg, NSAL0310Constant.IMG_NM_CLPS, i);
                }
            }
        }
    }

    // START 2018/10/30 [QC#28890, MOD]
    // START 2016/11/22 K.Kojima [QC#16168,DEL]
    /**
     * clear Popup Parameter
     * @param scrnMsg NSAL0310BMsg
     */
    public static void clearPopupParameter(NSAL0310BMsg scrnMsg) {
        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();
        scrnMsg.xxPopPrm_14.clear();
        scrnMsg.xxPopPrm_15.clear();
        scrnMsg.xxPopPrm_16.clear();
        scrnMsg.xxPopPrm_17.clear();
        scrnMsg.xxPopPrm_18.clear();
        scrnMsg.xxPopPrm_19.clear();
        scrnMsg.xxPopPrm_20.clear();
        // START 2016/01/05 T.Tomita [QC#1029, ADD]
        scrnMsg.xxPopPrm_21.clear();
        scrnMsg.xxPopPrm_22.clear();
        scrnMsg.xxPopPrm_23.clear();
        // END 2016/01/05 T.Tomita [QC#1029, ADD]
        scrnMsg.xxScrEventNm.clear();
        ZYPTableUtil.clear(scrnMsg.P);
    }
    // END 2016/11/22 K.Kojima [QC#16168,DEL]
    // END 2018/10/30 [QC#28890, MOD]

    // START 2018/10/30 [QC#28890, ADD]
    /**
     * inputCheckMondatory
     * @param scrnMsg NSAL0310BMsg
     */
    public static void inputCheckMondatory(NSAL0310BMsg scrnMsg) {

        boolean isError = true;
        if (hasValue(scrnMsg.dsAcctNum_H)) {
            isError = false;
        } else if (hasValue(scrnMsg.serNum)) {
            isError = false;
        } else if (hasValue(scrnMsg.mdlNm)) {
            isError = false;
        } else if (hasValue(scrnMsg.xxComnScrColValTxt_H)) {
            isError = false;
        } else if (hasValue(scrnMsg.xxScrItem29Txt)) {
            isError = false;
        } else if (hasValue(scrnMsg.mdseDescShortTxt)) {
            isError = false;
        }

        if (isError) {
            scrnMsg.setMessageInfo(NSAL0310Constant.NSAM0017E);
        }
    }
    // END 2018/10/30 [QC#28890, ADD]
}
