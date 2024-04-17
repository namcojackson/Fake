/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3200.common;

import static business.servlet.NMAL3200.constant.NMAL3200Constant.ASC;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BIZ_APP_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_10_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_10_NAME;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_10_VAL;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_1_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_1_NAME;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_1_VAL;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_2_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_2_NAME;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_2_VAL;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_3_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_3_NAME;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_3_VAL;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_4_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_4_NAME;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_4_VAL;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_5_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_5_NAME;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_5_VAL;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_6_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_6_NAME;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_6_VAL;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_7_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_7_NAME;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_7_VAL;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_8_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_8_NAME;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_8_VAL;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_9_ID;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_9_NAME;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_CMN_BTN_9_VAL;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.BTN_UPLOAD;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.COL_DISP_NAME1;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.COL_DISP_NAME2;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.COL_DISP_NAME3;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.COL_DISP_NAME4;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.COL_DISP_NAME5;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.COL_DISP_NAME6;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.COL_DISP_NAME7;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.COL_NAME1;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.COL_NAME2;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.COL_NAME3;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.COL_NAME4;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.COL_NAME5;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.COL_NAME6;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.COL_NAME7;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.FUNCTION_UPDATE;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.IDX_0;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.IDX_1;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.IDX_10;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.IDX_15;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.IDX_16;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.IDX_2;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.IDX_3;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.IDX_4;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.IDX_5;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.IDX_6;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.IDX_7;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.SELECT_TABLE_NAME;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.TAB_UPLOAD;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.WHERE_DISP_NAME1;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.WHERE_DISP_NAME2;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.WHERE_DISP_NAME3;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.WHERE_DISP_NAME4;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.WHERE_DISP_NAME5;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.WHERE_DISP_NAME6;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.WHERE_DISP_NAME7;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.WINDOW_NAME;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL3200.NMAL3200BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NMAL3200 Marketing Data Analysis
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NMAL3200CommonLogic {

    /**
     * Set Common Button for Initialized screen
     * @param handler EZDCommonHandler
     */
    public static void setCommonButtonInit(EZDCommonHandler handler) {
        // 0 : inactive
        // 1 : active
        if (isUpdatable()) {
            handler.setButtonProperties(BTN_CMN_BTN_2_ID, BTN_CMN_BTN_2_NAME, BTN_CMN_BTN_2_VAL, 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_BTN_2_ID, BTN_CMN_BTN_2_NAME, BTN_CMN_BTN_2_VAL, 0, null);
        }
        handler.setButtonProperties(BTN_CMN_BTN_1_ID, BTN_CMN_BTN_1_NAME, BTN_CMN_BTN_1_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3_ID, BTN_CMN_BTN_3_NAME, BTN_CMN_BTN_3_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4_ID, BTN_CMN_BTN_4_NAME, BTN_CMN_BTN_4_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5_ID, BTN_CMN_BTN_5_NAME, BTN_CMN_BTN_5_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6_ID, BTN_CMN_BTN_6_NAME, BTN_CMN_BTN_6_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7_ID, BTN_CMN_BTN_7_NAME, BTN_CMN_BTN_7_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8_ID, BTN_CMN_BTN_8_NAME, BTN_CMN_BTN_8_VAL, 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9_ID, BTN_CMN_BTN_9_NAME, BTN_CMN_BTN_9_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_10_ID, BTN_CMN_BTN_10_NAME, BTN_CMN_BTN_10_VAL, 1, null);

    }

    /**
     * set attribute
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL3200BMsg
     */
    public static void setAttr(EZDCommonHandler handler, NMAL3200BMsg scrnMsg) {
        if (isUpdatable()) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).mktgFldMapNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).upldErrFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).mktgMapRqstProcFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).upldFileNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).contrAssnTrgtTpDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxFullNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxDtTm_A1.setInputProtected(true);
            }
            return;
        }
        if (TAB_UPLOAD.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.dsAcctNmColDfnNm.setInputProtected(true);
            scrnMsg.firstLineAddrColDfnNm.setInputProtected(true);
            scrnMsg.scdLineAddrColDfnNm.setInputProtected(true);
            scrnMsg.thirdLineAddrColDfnNm.setInputProtected(true);
            scrnMsg.frthLineAddrColDfnNm.setInputProtected(true);
            scrnMsg.ctyAddrColDfnNm.setInputProtected(true);
            scrnMsg.cntyPkColDfnNm.setInputProtected(true);
            scrnMsg.stCdColDfnNm.setInputProtected(true);
            scrnMsg.postCdColDfnNm.setInputProtected(true);
            scrnMsg.ctryCdColDfnNm.setInputProtected(true);
            scrnMsg.dunsNumColDfnNm.setInputProtected(true);
            scrnMsg.glnColDfnNm.setInputProtected(true);
            scrnMsg.hinColDfnNm.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_01.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_02.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_03.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_04.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_05.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_06.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_07.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_08.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_09.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_10.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_11.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_12.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_13.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_14.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_15.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_16.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_17.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_18.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_19.setInputProtected(true);
            scrnMsg.attrbTxtColDfnNm_20.setInputProtected(true);
            scrnMsg.contrAssnTrgtTpCd_SL.setInputProtected(true);
            scrnMsg.exactCondFlg.setInputProtected(true);
            scrnMsg.dunsCondFlg.setInputProtected(true);
            scrnMsg.glnCondFlg.setInputProtected(true);
            scrnMsg.prtlCondFlg.setInputProtected(true);
            scrnMsg.ovrdVldFlg.setInputProtected(true);
            scrnMsg.xxFileData.setInputProtected(true);
            handler.setButtonEnabled(BTN_UPLOAD, false);
        } else {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).mktgFldMapNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).upldErrFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).mktgMapRqstProcFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).upldFileNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).contrAssnTrgtTpDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxFullNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxDtTm_A1.setInputProtected(true);
            }
        }
    }

    /**
     * Create OpenWin_MktFldMap
     * @param scrnMsg NMAL3200BMsg
     * @param item EZDBStringItem
     * @return Object[]
     */
    public static Object[] createNWAL1130Params(NMAL3200BMsg scrnMsg, EZDBStringItem item) {
        // Parameter Clear
        ZYPTableUtil.clear(scrnMsg.R);

        // Paramter Set
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = WINDOW_NAME;
        params[IDX_2] = SELECT_TABLE_NAME;

        List<Object> whereList = new ArrayList<Object>(IDX_7);
        Object[] whereObj1 = {WHERE_DISP_NAME1, COL_NAME1, item.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {WHERE_DISP_NAME2, COL_NAME2, null, ZYPConstant.FLG_ON_Y };
        Object[] whereObj3 = {WHERE_DISP_NAME3, COL_NAME3, null, ZYPConstant.FLG_ON_Y };
        Object[] whereObj4 = {WHERE_DISP_NAME4, COL_NAME4, null, ZYPConstant.FLG_ON_Y };
        Object[] whereObj5 = {WHERE_DISP_NAME5, COL_NAME5, null, ZYPConstant.FLG_ON_Y };
        Object[] whereObj6 = {WHERE_DISP_NAME6, COL_NAME6, null, ZYPConstant.FLG_ON_Y };
        Object[] whereObj7 = {WHERE_DISP_NAME7, COL_NAME7, null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj1);
        whereList.add(whereObj2);
        whereList.add(whereObj3);
        whereList.add(whereObj4);
        whereList.add(whereObj5);
        whereList.add(whereObj6);
        whereList.add(whereObj7);
        params[IDX_3] = whereList;

        List<Object> colList = new ArrayList<Object>(IDX_7);
        Object[] colObj1 = {COL_DISP_NAME1, COL_NAME1, BigDecimal.valueOf(IDX_16), ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {COL_DISP_NAME2, COL_NAME2, BigDecimal.valueOf(IDX_15), ZYPConstant.FLG_OFF_N };
        Object[] colObj3 = {COL_DISP_NAME3, COL_NAME3, BigDecimal.valueOf(IDX_15), ZYPConstant.FLG_OFF_N };
        Object[] colObj4 = {COL_DISP_NAME4, COL_NAME4, BigDecimal.valueOf(IDX_15), ZYPConstant.FLG_OFF_N };
        Object[] colObj5 = {COL_DISP_NAME5, COL_NAME5, BigDecimal.valueOf(IDX_10), ZYPConstant.FLG_OFF_N };
        Object[] colObj6 = {COL_DISP_NAME6, COL_NAME6, BigDecimal.valueOf(IDX_10), ZYPConstant.FLG_OFF_N };
        Object[] colObj7 = {COL_DISP_NAME7, COL_NAME7, BigDecimal.valueOf(IDX_10), ZYPConstant.FLG_OFF_N };
        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);
        colList.add(colObj4);
        colList.add(colObj5);
        colList.add(colObj6);
        colList.add(colObj7);
        params[IDX_4] = colList;

        List<Object> sortList = new ArrayList<Object>(IDX_6);
        Object[] sortObj1 = {COL_NAME2, ASC };
        Object[] sortObj2 = {COL_NAME3, ASC };
        Object[] sortObj3 = {COL_NAME4, ASC };
        Object[] sortObj4 = {COL_NAME5, ASC };
        Object[] sortObj5 = {COL_NAME6, ASC };
        Object[] sortObj6 = {COL_NAME7, ASC };

        sortList.add(sortObj1);
        sortList.add(sortObj2);
        sortList.add(sortObj3);
        sortList.add(sortObj4);
        sortList.add(sortObj5);
        sortList.add(sortObj6);

        params[IDX_5] = sortList;
        params[IDX_6] = scrnMsg.R;

        return params;
    }

    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        List<String> functionList = getFunctionList();
        return functionList.contains(FUNCTION_UPDATE);
    }

    /**
     * Function List
     * @return List<String> Function List
     */
    private static List<String> getFunctionList() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BIZ_APP_ID);
        return functionList;
    }
}
