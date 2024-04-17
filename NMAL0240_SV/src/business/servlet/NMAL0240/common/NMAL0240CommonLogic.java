/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0240.common;

import static business.servlet.NMAL0240.constant.NMAL0240Constant.BIZ_ID;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_01_SAV_GUARD;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_01_SAV_LABEL;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_01_SAV_NAME;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_02_SUB_GUARD;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_02_SUB_LABEL;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_02_SUB_NAME;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_03_APL_GUARD;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_03_APL_LABEL;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_03_APL_NAME;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_04_APR_GUARD;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_04_APR_LABEL;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_04_APR_NAME;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_05_REJ_GUARD;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_05_REJ_LABEL;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_05_REJ_NAME;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_06_DWL_GUARD;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_06_DWL_LABEL;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_06_DWL_NAME;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_07_DEL_GUARD;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_07_DEL_LABEL;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_07_DEL_NAME;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_08_CLE_GUARD;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_08_CLE_LABEL;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_08_CLE_NAME;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_09_RST_GUARD;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_09_RST_LABEL;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_09_RST_NAME;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_10_RTR_GUARD;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_10_RTR_LABEL;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.BTN_10_RTR_NAME;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.FUNC_ID_UPD;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.SCRN_ID_00;

import java.util.List;

import business.servlet.NMAL0240.NMAL0240BMsg;
import business.servlet.NMAL0240.NMAL0240_ABMsg;
import business.servlet.NMAL0240.NMAL0240_BBMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NMAL0240CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Fujitsu         C.Tanaka        Create          CSA
 * 2016/02/18   SRAA            Y.Chen          Update          QC#2645
 * 2018/03/20   CITS            T.Kikuhara      Update          QC#22324
 *</pre>
 */
public class NMAL0240CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param userProfileService S21UserProfileService
     */
    public static void initCmnBtnProp(S21CommonHandler handler, S21UserProfileService userProfileService) {

        handler.setButtonProperties(BTN_01_SAV_NAME, BTN_01_SAV_GUARD, BTN_01_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 0, null);
        handler.setButtonProperties(BTN_03_APL_NAME, BTN_03_APL_GUARD, BTN_03_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_04_APR_NAME, BTN_04_APR_GUARD, BTN_04_APR_LABEL, 0, null);
        handler.setButtonProperties(BTN_05_REJ_NAME, BTN_05_REJ_GUARD, BTN_05_REJ_LABEL, 0, null);
        handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 0, null);
        handler.setButtonProperties(BTN_07_DEL_NAME, BTN_07_DEL_GUARD, BTN_07_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 0, null);
        handler.setButtonProperties(BTN_09_RST_NAME, BTN_09_RST_GUARD, BTN_09_RST_LABEL, 1, null);
        handler.setButtonProperties(BTN_10_RTR_NAME, BTN_10_RTR_GUARD, BTN_10_RTR_LABEL, 1, null);

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        if (functionIds.contains(FUNC_ID_UPD)) {
            handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 1, null);
            handler.setButtonProperties(BTN_07_DEL_NAME, BTN_07_DEL_GUARD, BTN_07_DEL_LABEL, 1, null);
        }
    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Control Fields
     * @param handler S21CommonHandler
     * @param scrnMsg NMAL0240BMsg
     * @param newVersion boolean
     */
    public static void controlFields(S21CommonHandler handler, NMAL0240BMsg scrnMsg, boolean newVersion) {

        scrnMsg.mdseItemTpCd.setInputProtected(true);
        scrnMsg.mdseDescShortTxt.setInputProtected(true);
        scrnMsg.coaMdseTpCd.setInputProtected(true);
        scrnMsg.xxScrItem61Txt.setInputProtected(true);
        scrnMsg.actvFlg.setInputProtected(true);
        scrnMsg.xxChkBox.setInputProtected(true);

        scrnMsg.cmpsnRevnNum_A.setInputProtected(true);
        scrnMsg.cmpsnRevnCmntTxt_A.setInputProtected(true);
        scrnMsg.effFromDt_A.setInputProtected(true);
        scrnMsg.effThruDt_A.setInputProtected(true);

        //QC5003 Start
        boolean cusaFlg = false;
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox.getValue())) {
            cusaFlg = true;
        }
        //QC5003 End
        boolean existCmpsn = false;
        boolean existReq = false;
        NMAL0240_ABMsg aBMsg = null;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            aBMsg = scrnMsg.A.no(i);

            if (ZYPCommonFunc.hasValue(aBMsg.cmpsnPk_A1)) {
                existCmpsn = true;
            }
            if (ZYPCommonFunc.hasValue(aBMsg.cmpsnChngReqPk_A1)) {
                existReq = true;
            }
        }

        if (existReq || (!existCmpsn && !existReq)) {
            scrnMsg.cmpsnRevnCmntTxt_A.setInputProtected(false);
            scrnMsg.effFromDt_A.setInputProtected(false);
            scrnMsg.effThruDt_A.setInputProtected(false);
        }

        scrnMsg.cmpsnRevnNum_B.setInputProtected(true);
        scrnMsg.cmpsnRevnCmntTxt_B.setInputProtected(true);
        scrnMsg.effFromDt_B.setInputProtected(true);
        scrnMsg.effThruDt_B.setInputProtected(true);

        NMAL0240_BBMsg bBMsg = null;
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            bBMsg = scrnMsg.B.no(i);
            bBMsg.xxChkBox_B1.setInputProtected(true);
            bBMsg.mdseCd_B1.setInputProtected(true);
            bBMsg.mdseDescShortTxt_B1.setInputProtected(true);
            bBMsg.childMdseQty_B1.setInputProtected(true);
            bBMsg.coaProjDescTxt_B1.setInputProtected(true);
            bBMsg.coaProdCd_B1.setInputProtected(true);
            bBMsg.effFromDt_B1.setInputProtected(true);
            bBMsg.effThruDt_B1.setInputProtected(true);
            bBMsg.xxScrItem10Txt_B1.setInputProtected(true);
            handler.setButtonEnabled("OpenWin_CmpsnItemB", i, false);
            handler.setButtonEnabled("GetMdse", i, false);
        }

        handler.setButtonEnabled("OpenWin_CreateNew", false);
        handler.setButtonEnabled("InsertRow", true);
        handler.setButtonEnabled("DeleteRow", true);

        if (!cusaFlg) {
            if ((!existCmpsn && !existReq && !newVersion) || (existCmpsn && !existReq && !newVersion)) {
                handler.setButtonEnabled("OpenWin_CreateNew", true);
                handler.setButtonEnabled("InsertRow", false);
                handler.setButtonEnabled("DeleteRow", false);
            }
        } else {
            handler.setButtonEnabled("InsertRow", false);
            handler.setButtonEnabled("DeleteRow", false);
        }

        //QC22324 Start
        if (MDSE_ITEM_TP.KIT.equals(scrnMsg.mdseItemTpCd.getValue())) {
            handler.setButtonEnabled("OpenWin_BOMTextEntry", true);
        } else {
            handler.setButtonEnabled("OpenWin_BOMTextEntry", false);
        }
        //QC22324 End

    }

    /**
     * Control Revision Fields
     * @param handler S21CommonHandler
     * @param scrnMsg NMAL0240BMsg
     */
    public static void controlRevisionFields(S21CommonHandler handler, NMAL0240BMsg scrnMsg) {

        //QC5003 Start
    	boolean cusaFlg = false;
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox.getValue())) {
            cusaFlg = true;
        }
        //QC5003 End
        
        NMAL0240_ABMsg aBMsg = null;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            aBMsg = scrnMsg.A.no(i);
            aBMsg.mdseDescShortTxt_A1.setInputProtected(true);
            aBMsg.coaProjDescTxt_A1.setInputProtected(true);
            aBMsg.coaProdCd_A1.setInputProtected(true);
            aBMsg.effFromDt_A1.setInputProtected(true);
            aBMsg.effThruDt_A1.setInputProtected(true);
            aBMsg.xxScrItem10Txt_A1.setInputProtected(true);

            //QC5003 Start
            if (cusaFlg) {
            	aBMsg.xxChkBox_A1.setInputProtected(true);
                handler.setButtonEnabled("OpenWin_CmpsnItemA", i, false);
            	aBMsg.mdseCd_A1.setInputProtected(true);
            	aBMsg.childMdseQty_A1.setInputProtected(true);
                handler.setButtonEnabled("GetMdse", i, false);
            } else {
                aBMsg.xxChkBox_A1.setInputProtected(false);
                handler.setButtonEnabled("OpenWin_CmpsnItemA", i, true);
                aBMsg.mdseCd_A1.setInputProtected(false);
                aBMsg.childMdseQty_A1.setInputProtected(false);
                handler.setButtonEnabled("GetMdse", i, true);
            }
            //QC5003 End
            
            handler.setButtonEnabled("OpenCmpsnA", i, true);
            handler.setButtonEnabled("CloseCmpsnA", i, true);

            if (ZYPConstant.FLG_OFF_N.equals(aBMsg.xxPgFlg_A1.getValue()) && ZYPConstant.FLG_OFF_N.equals(aBMsg.xxDplyCtrlFlg_A1.getValue())) {
                handler.setButtonEnabled("OpenCmpsnA", i, false);
                handler.setButtonEnabled("CloseCmpsnA", i, false);
            }

            if (!ZYPCommonFunc.hasValue(aBMsg.cmpsnChngReqPk_A1) && ZYPCommonFunc.hasValue(aBMsg.cmpsnPk_A1)) {
                aBMsg.xxChkBox_A1.setInputProtected(true);
                aBMsg.mdseCd_A1.setInputProtected(true);
                aBMsg.childMdseQty_A1.setInputProtected(true);
                handler.setButtonEnabled("OpenWin_CmpsnItemA", i, false);
                handler.setButtonEnabled("GetMdse", i, false);
            }

            if (ZYPCommonFunc.hasValue(aBMsg.xxNum_A2)) {
                aBMsg.xxChkBox_A1.setInputProtected(true);
                aBMsg.mdseCd_A1.setInputProtected(true);
                aBMsg.childMdseQty_A1.setInputProtected(true);
                handler.setButtonEnabled("OpenWin_CmpsnItemA", i, false);
                handler.setButtonEnabled("OpenCmpsnA", i, false);
                handler.setButtonEnabled("CloseCmpsnA", i, false);
                handler.setButtonEnabled("GetMdse", i, false);
            }
        }

        NMAL0240_BBMsg bBMsg = null;
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            bBMsg = scrnMsg.B.no(i);
            if (ZYPConstant.FLG_OFF_N.equals(bBMsg.xxPgFlg_B1.getValue()) && ZYPConstant.FLG_OFF_N.equals(bBMsg.xxDplyCtrlFlg_B1.getValue())) {
                handler.setButtonEnabled("OpenCmpsnB", i, false);
                handler.setButtonEnabled("CloseCmpsnB", i, false);
            } else if (ZYPCommonFunc.hasValue(bBMsg.xxNum_B2)) {
                handler.setButtonEnabled("OpenCmpsnB", i, false);
                handler.setButtonEnabled("CloseCmpsnB", i, false);
            } else {
                handler.setButtonEnabled("OpenCmpsnA", i, true);
                handler.setButtonEnabled("CloseCmpsnA", i, true);
            }
        }
    }
    
    // QC#2645
    /**
     * Control Table Row Color
     * @param scrnMsg NMAL0240BMsg
     */
    public static void controlTableRowColor(NMAL0240BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        tblColor.clearRowsBG("B", scrnMsg.B);
        if (0 < scrnMsg.A.getValidCount()) {
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        }
        if (0 < scrnMsg.B.getValidCount()) {
            tblColor.setAlternateRowsBG("B", scrnMsg.B);
        }
    }

    /**
     * Add Check Item
     * @param scrnMsg NMAL0240BMsg
     */
    public static void addCheckItem(NMAL0240BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.mdseCd);

        scrnMsg.addCheckItem(scrnMsg.cmpsnRevnNum_A);
        scrnMsg.addCheckItem(scrnMsg.cmpsnRevnCmntTxt_A);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_A);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_A);

        scrnMsg.addCheckItem(scrnMsg.cmpsnRevnNum_B);
        scrnMsg.addCheckItem(scrnMsg.cmpsnRevnCmntTxt_B);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_B);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_B);

        NMAL0240_ABMsg aBMsg = null;
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            aBMsg = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(aBMsg.xxChkBox_A1);
            scrnMsg.addCheckItem(aBMsg.mdseCd_A1);
            scrnMsg.addCheckItem(aBMsg.mdseDescShortTxt_A1);
            scrnMsg.addCheckItem(aBMsg.childMdseQty_A1);
            scrnMsg.addCheckItem(aBMsg.coaProjDescTxt_A1);
            scrnMsg.addCheckItem(aBMsg.coaProdCd_A1);
            scrnMsg.addCheckItem(aBMsg.effFromDt_A1);
            scrnMsg.addCheckItem(aBMsg.effThruDt_A1);
            scrnMsg.addCheckItem(aBMsg.xxScrItem10Txt_A1);
        }

        NMAL0240_BBMsg bBMsg = null;
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            bBMsg = scrnMsg.B.no(i);
            scrnMsg.addCheckItem(bBMsg.xxChkBox_B1);
            scrnMsg.addCheckItem(bBMsg.mdseCd_B1);
            scrnMsg.addCheckItem(bBMsg.mdseDescShortTxt_B1);
            scrnMsg.addCheckItem(bBMsg.childMdseQty_B1);
            scrnMsg.addCheckItem(bBMsg.coaProjDescTxt_B1);
            scrnMsg.addCheckItem(bBMsg.coaProdCd_B1);
            scrnMsg.addCheckItem(bBMsg.effFromDt_B1);
            scrnMsg.addCheckItem(bBMsg.effThruDt_B1);
            scrnMsg.addCheckItem(bBMsg.xxScrItem10Txt_B1);
        }
    }
}
