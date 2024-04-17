/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1240.common;

import static business.servlet.NSAL1240.constant.NSAL1240Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDMsg;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL1240.NSAL1240BMsg;

/**
 *<pre>
 * Config# Search Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Hitachi         A.Kohinata      Create          N/A
 * 2016/03/29   Hitachi         M.Gotou         Update          QC#4949
 * 2016/05/11   Hitachi         T.Tomita        Update          QC#7832
 * 2016/05/16   Hitachi         T.Tomita        Update          QC#4578
 * 2016/05/27   Hitachi         M.Gotou         Update          QC#9117
 * 2016/07/01   Hitachi         T.Tomita        Update          QC#9117
 * 2016/09/02   Hitachi         T.Kanasaka      Update          QC#11170
 * 2017/07/26   Fujitsu         S.Takami        Update          QC#20004
 *</pre>
 */
public class NSAL1240CommonLogic {

    /**
     * initialize
     * @param handler EZDCommonHandler
     */
    public static void initialize(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    /**
     * screenItemControl
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1240BMsg
     */
    public static void screenItemControl(EZDCommonHandler handler, NSAL1240BMsg scrnMsg) {

        controlProcess(handler, scrnMsg);
        setTableBGColor(scrnMsg);
    }

    private static final void controlProcess(EZDCommonHandler handler, NSAL1240BMsg scrnMsg) {

        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxEdtModeFlg_I.getValue())) {
            scrnMsg.xxChkBox_H1.setInputProtected(true);
            scrnMsg.xxChkBox_H2.setInputProtected(true);
            scrnMsg.xxChkBox_H3.setInputProtected(true);
            scrnMsg.xxChkBox_H4.setInputProtected(true);
            scrnMsg.xxChkBox_H5.setInputProtected(true);
            scrnMsg.xxChkBox_H6.setInputProtected(true);
            scrnMsg.xxChkBox_H7.setInputProtected(true);
            scrnMsg.xxChkBox_H8.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).svcConfigMstrPk_A.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A.setInputProtected(true);
            scrnMsg.A.no(i).svcMachMstrPk_A.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A.setInputProtected(true);
            // add start 2016/07/01 CSA Defect#9117
            scrnMsg.A.no(i).mdseDescShortTxt_A.setInputProtected(true);
            // add end 2016/07/01 CSA Defect#9117
            scrnMsg.A.no(i).t_MdlNm_A.setInputProtected(true);
            scrnMsg.A.no(i).svcMachUsgStsDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).svcMachMstrStsDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).stkStsDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).svcSlnSq_A.setInputProtected(true);
            scrnMsg.A.no(i).svcSlnNm_A.setInputProtected(true);
            scrnMsg.A.no(i).dsContrNum_A.setInputProtected(true);
            scrnMsg.A.no(i).ownrAcctNum_A.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            // del start 2016/05/16 CSA Defect#4578
//            scrnMsg.A.no(i).ownrLocNum_A.setInputProtected(true);
//            scrnMsg.A.no(i).xxComnScrColValTxt_A1.setInputProtected(true);
            // del end 2016/05/16 CSA Defect#4578
            scrnMsg.A.no(i).billToAcctNum_A.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A2.setInputProtected(true);
            // mod start 2016/05/16 CSA Defect#4578
            scrnMsg.A.no(i).indBillToLocNum_A.setInputProtected(true);
            // mod end 2016/05/16 CSA Defect#4578
            scrnMsg.A.no(i).xxComnScrColValTxt_A2.setInputProtected(true);
            scrnMsg.A.no(i).curLocAcctNum_A.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A3.setInputProtected(true);
            // mod start 2016/05/16 CSA Defect#4578
            scrnMsg.A.no(i).indCurLocNum_A.setInputProtected(true);
            // mod end 2016/05/16 CSA Defect#4578
            scrnMsg.A.no(i).xxComnScrColValTxt_A3.setInputProtected(true);
        }
    }

    private static void setTableBGColor(NSAL1240BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.clearRowsBG("A", scrnMsg.A);
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        } else {
            tblColor.clearRowsBG("A", scrnMsg.A);
        }
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NSAL1240BMsg
     */
    public static void commonAddCheckItem(NSAL1240BMsg scrnMsg) {

        // START 2016/03/29 M.Gotou [QC#4949, MOD]
        scrnMsg.addCheckItem(scrnMsg.xxScrItem29Txt_H1);
        // END 2016/03/29 M.Gotou [QC#4949, MOD]
        scrnMsg.addCheckItem(scrnMsg.serNum_H);
        // START 2016/03/29 M.Gotou [QC#4949, MOD]
        scrnMsg.addCheckItem(scrnMsg.xxScrItem29Txt_H2);
        // END 2016/03/29 M.Gotou [QC#4949, MOD]
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H);
        scrnMsg.addCheckItem(scrnMsg.t_MdlNm_H);
        scrnMsg.addCheckItem(scrnMsg.shipDt_H1);
        scrnMsg.addCheckItem(scrnMsg.shipDt_H2);
        scrnMsg.addCheckItem(scrnMsg.svcMachUsgStsCd_H);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H1);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H2);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H3);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H4);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H5);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H6);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H7);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H8);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_HA);
        // START 2016/09/02 T.Kanasaka [QC#11170, DEL]
//        scrnMsg.addCheckItem(scrnMsg.xxChkBox_HB);
//        scrnMsg.addCheckItem(scrnMsg.xxChkBox_HC);
        // END 2016/09/02 T.Kanasaka [QC#11170, DEL]
        scrnMsg.addCheckItem(scrnMsg.stkStsCd_H);
        scrnMsg.addCheckItem(scrnMsg.curLocNum_H1);
        scrnMsg.addCheckItem(scrnMsg.curLocNum_H2);
        // START 2016/03/29 M.Gotou [QC#4949, MOD]
        scrnMsg.addCheckItem(scrnMsg.xxScrItem29Txt_H3);
        // END 2016/03/29 M.Gotou [QC#4949, MOD]
        scrnMsg.addCheckItem(scrnMsg.svcSlnNm_H);
        scrnMsg.addCheckItem(scrnMsg.dsContrNum_H);
        scrnMsg.addCheckItem(scrnMsg.ownrAcctNum_H);
        // del start 2016/05/16 CSA Defect#4578
//        scrnMsg.addCheckItem(scrnMsg.ownrLocNum_H);
        // del end 2016/05/16 CSA Defect#4578
        scrnMsg.addCheckItem(scrnMsg.billToAcctNum_H);
        // mod start 2016/05/16 CSA Defect#4578
        scrnMsg.addCheckItem(scrnMsg.indBillToLocNum_H);
        // mod end 2016/05/16 CSA Defect#4578
        scrnMsg.addCheckItem(scrnMsg.curLocAcctNum_H);
        // mod start 2016/05/16 CSA Defect#4578
        scrnMsg.addCheckItem(scrnMsg.indCurLocNum_H);
        // mod end 2016/05/16 CSA Defect#4578
    }

    /**
     * clear Popup Parameter
     * @param scrnMsg NSAL1240BMsg
     */
    public static void clearPopupParameter(NSAL1240BMsg scrnMsg) {

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
    }

    /**
     * set input parameter
     * @param scrnMsg NSAL1240BMsg
     * @param arg Serializable
     */
    public static void setInputParam(NSAL1240BMsg scrnMsg, Serializable arg) {

        // START 2016/05/11 T.Tomita [QC#7832, MOD]
        scrnMsg.xxModeCd_I1.clear();
        scrnMsg.svcConfigMstrPk_I.clear();
        scrnMsg.serNum_I.clear();
        scrnMsg.svcMachMstrPk_I.clear();
        scrnMsg.mdseCd_I.clear();
        scrnMsg.mdlNm_I.clear();
        scrnMsg.shipDt_I1.clear();
        scrnMsg.shipDt_I2.clear();
        // START 2016/03/29 M.Gotou [QC#4949, MOD]
        scrnMsg.xxScrItem29Txt_H2.clear();
        // END 2016/03/29 M.Gotou [QC#4949, MOD]
        ZYPTableUtil.clear(scrnMsg.I);
        scrnMsg.xxEdtModeFlg_I.clear();
        scrnMsg.xxModeCd_I2.clear();
        scrnMsg.mainMachFlg_I.clear();
        scrnMsg.stkStsCd_I.clear();
        scrnMsg.curLocNum_I1.clear();
        scrnMsg.curLocNum_I2.clear();
        scrnMsg.svcSlnSq_I.clear();
        scrnMsg.svcSlnNm_I.clear();
        scrnMsg.dsContrNum_I.clear();
        scrnMsg.ownrAcctNum_I.clear();
        scrnMsg.ownrLocNum_I.clear();
        scrnMsg.billToAcctNum_I.clear();
        scrnMsg.billToLocNum_I.clear();
        scrnMsg.curLocAcctNum_I.clear();
        scrnMsg.curLocNum_I.clear();
        scrnMsg.xxModeCd_I3.clear();

        if (!(arg instanceof Object[])) {
            return;
        }

        Object[] params = (Object[]) arg;

        if (params.length > 0 && params[0] instanceof EZDBStringItem) {
            setValue(scrnMsg.xxModeCd_I1, ((EZDBStringItem) params[0]));
        } else {
            setValue(scrnMsg.xxModeCd_I1, MODE_01);
        }
        if (params.length > 1 && params[1] instanceof EZDBBigDecimalItem) {
            setValue(scrnMsg.svcConfigMstrPk_I, ((EZDBBigDecimalItem) params[1]));
        }
        if (params.length > 2 && params[2] instanceof EZDBStringItem) {
            setValue(scrnMsg.serNum_I, ((EZDBStringItem) params[2]));
        }
        if (params.length > 3 && params[3] instanceof EZDBBigDecimalItem) {
            setValue(scrnMsg.svcMachMstrPk_I, ((EZDBBigDecimalItem) params[3]));
        }
        if (params.length > 4 && params[4] instanceof EZDBStringItem) {
            setValue(scrnMsg.mdseCd_I, ((EZDBStringItem) params[4]));
        }
        if (params.length > 5 && params[5] instanceof EZDBStringItem) {
            setValue(scrnMsg.mdlNm_I, ((EZDBStringItem) params[5]));
        }
        if (params.length > 6 && params[6] instanceof EZDBDateItem) {
            setValue(scrnMsg.shipDt_I1, ((EZDBDateItem) params[6]));
        }
        if (params.length > 7 && params[7] instanceof EZDBDateItem) {
            setValue(scrnMsg.shipDt_I2, ((EZDBDateItem) params[7]));
        }
        if (params.length > 8 && params[8] instanceof EZDBStringItem) {
            setValue(scrnMsg.svcMachUsgStsCd_I, ((EZDBStringItem) params[8]));
        }
        if (params.length > 9 && params[9] instanceof EZDBMsgArray) {
            EZDBMsgArray params8 = (EZDBMsgArray) params[9];
            EZDMsg.copy(params8, null, scrnMsg.I, null);
        }
        if (params.length > 10 && params[10] instanceof EZDBStringItem) {
            setValue(scrnMsg.xxEdtModeFlg_I, ((EZDBStringItem) params[10]));
        }
        if (params.length > 11 && params[11] instanceof EZDBStringItem) {
            setValue(scrnMsg.xxModeCd_I2, ((EZDBStringItem) params[11]));
        } else {
            setValue(scrnMsg.xxModeCd_I2, MODE_01);
        }
        if (params.length > 12 && params[12] instanceof EZDBStringItem) {
            setValue(scrnMsg.mainMachFlg_I, ((EZDBStringItem) params[12]));
        } else {
            setValue(scrnMsg.mainMachFlg_I, ZYPConstant.FLG_OFF_N);
        }
        if (params.length > 13 && params[13] instanceof EZDBStringItem) {
            setValue(scrnMsg.stkStsCd_I, ((EZDBStringItem) params[13]));
        }
        if (params.length > 14 && params[14] instanceof EZDBStringItem) {
            setValue(scrnMsg.curLocNum_I1, ((EZDBStringItem) params[14]));
        }
        if (params.length > 15 && params[15] instanceof EZDBStringItem) {
            setValue(scrnMsg.curLocNum_I2, ((EZDBStringItem) params[15]));
        }
        if (params.length > 16 && params[16] instanceof EZDBBigDecimalItem) {
            setValue(scrnMsg.svcSlnSq_I, ((EZDBBigDecimalItem) params[16]));
        }
        if (params.length > 17 && params[17] instanceof EZDBStringItem) {
            setValue(scrnMsg.svcSlnNm_I, ((EZDBStringItem) params[17]));
        }
        if (params.length > 18 && params[18] instanceof EZDBStringItem) {
            setValue(scrnMsg.dsContrNum_I, ((EZDBStringItem) params[18]));
        }
        if (params.length > 19 && params[19] instanceof EZDBStringItem) {
            setValue(scrnMsg.ownrAcctNum_I, ((EZDBStringItem) params[19]));
        }
        if (params.length > 20 && params[20] instanceof EZDBStringItem) {
            setValue(scrnMsg.ownrLocNum_I, ((EZDBStringItem) params[20]));
        }
        if (params.length > 21 && params[21] instanceof EZDBStringItem) {
            setValue(scrnMsg.billToAcctNum_I, ((EZDBStringItem) params[21]));
        }
        if (params.length > 22 && params[22] instanceof EZDBStringItem) {
            setValue(scrnMsg.billToLocNum_I, ((EZDBStringItem) params[22]));
        }
        if (params.length > 23 && params[23] instanceof EZDBStringItem) {
            setValue(scrnMsg.curLocAcctNum_I, ((EZDBStringItem) params[23]));
        }
        if (params.length > 24 && params[24] instanceof EZDBStringItem) {
            setValue(scrnMsg.curLocNum_I, ((EZDBStringItem) params[24]));
        }
        if (params.length > 31 && params[31] instanceof EZDBStringItem && hasValue((EZDBStringItem) params[31])) {
            setValue(scrnMsg.xxModeCd_I3, ((EZDBStringItem) params[31]));
        } else {
            setValue(scrnMsg.xxModeCd_I3, MODE_01);
        }
        // END 2016/05/11 T.Tomita [QC#7832, MOD]
        // 2017/07/26 QC#20004 Add Start
        if (params.length > 32 && params[32] instanceof List<?> && !((List<?>) params[32]).isEmpty()) {
            List<?> configIdList = (List<?>) params[32];
            int n = 0;
            for (; n < configIdList.size() && n < scrnMsg.Q.length(); n++) {
                BigDecimal configId = (BigDecimal) configIdList.get(n);
                scrnMsg.Q.no(n).svcConfigMstrPk_Q.setValue(configId);
            }
            scrnMsg.Q.setValidCount(n);
        } else {
            scrnMsg.Q.clear();
            scrnMsg.Q.setValidCount(0);
        }
        // 2017/07/26 QC#20004 Add Start
    }

    /**
     * set output parameter
     * @param scrnMsg NSAL1240BMsg
     * @param arg Object[]
     * @return params Object[]
     */
    public static Object[] setOutputParam(NSAL1240BMsg scrnMsg, Serializable arg) {

        // START 2016/05/11 T.Tomita [QC#7832, MOD]
        Object[] params = (Object[]) arg;

        if (params.length > 25 && params[25] instanceof EZDBBigDecimalItem) {
            setValue((EZDBBigDecimalItem) params[25], scrnMsg.mdlId_O);
        }
        if (params.length > 26 && params[26] instanceof EZDBStringItem) {
            setValue((EZDBStringItem) params[26], scrnMsg.mdlNm_O);
        }
        if (params.length > 27 && params[27] instanceof EZDBBigDecimalItem) {
            setValue((EZDBBigDecimalItem) params[27], scrnMsg.dsContrPk_O);
        }
        if (params.length > 28 && params[28] instanceof EZDBStringItem) {
            setValue((EZDBStringItem) params[28], scrnMsg.dsContrNum_O);
        }
        if (params.length > 29 && params[29] instanceof EZDBBigDecimalItem) {
            setValue((EZDBBigDecimalItem) params[29], scrnMsg.svcConfigMstrPk_O);
        }
        if (params.length > 30 && params[30] instanceof EZDBMsgArray) {
            EZDBMsgArray params30 = (EZDBMsgArray) params[30];
            EZDMsg.copy(scrnMsg.O, null, params30, null);
        }
        if (params.length > 32 && params[32] instanceof EZDBMsgArray) {
            EZDBMsgArray params32 = (EZDBMsgArray) params[32];
            EZDMsg.copy(scrnMsg.P, null, params32, null);
        }

        return params;
        // END 2016/05/11 T.Tomita [QC#7832, MOD]
    }
}
