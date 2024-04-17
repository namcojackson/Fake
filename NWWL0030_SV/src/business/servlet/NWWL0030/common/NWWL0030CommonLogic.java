/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0030.common;

import static business.servlet.NWWL0030.constant.NWWL0030Constant.BTN_CMN_APL;
import static business.servlet.NWWL0030.constant.NWWL0030Constant.BTN_CMN_APR;
import static business.servlet.NWWL0030.constant.NWWL0030Constant.BTN_CMN_CLR;
import static business.servlet.NWWL0030.constant.NWWL0030Constant.BTN_CMN_DEL;
import static business.servlet.NWWL0030.constant.NWWL0030Constant.BTN_CMN_DWL;
import static business.servlet.NWWL0030.constant.NWWL0030Constant.BTN_CMN_RJT;
import static business.servlet.NWWL0030.constant.NWWL0030Constant.BTN_CMN_RST;
import static business.servlet.NWWL0030.constant.NWWL0030Constant.BTN_CMN_RTN;
import static business.servlet.NWWL0030.constant.NWWL0030Constant.BTN_CMN_SAV;
import static business.servlet.NWWL0030.constant.NWWL0030Constant.BTN_CMN_SUB;
import static business.servlet.NWWL0030.constant.NWWL0030Constant.SCRN_ID_00;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsgArray;
import business.servlet.NWWL0030.NWWL0030BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWWL0030CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0030CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

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
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWWL0030BMsg
     * @param scrnAMsgAry EZDBMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWWL0030BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWWL0030BMsg
     * @param scrnAMsgAry EZDBMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWWL0030BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NWWL0030BMsg
     * @param scrnAMsgAry NWWL0030_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWWL0030BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * controlScreenFields
     * @param handler S21CommonHandler
     * @param scrnMsg NWWL0030BMsg
     */
    public static void controlScreenFields(S21CommonHandler handler, NWWL0030BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).ntfyHdrNm_A0.setInputProtected(true);
            scrnMsg.A.no(i).ntfyHdrDescTxt_A0.setInputProtected(true);
            scrnMsg.A.no(i).ntfyBizAreaTpDescTxt_A0.setInputProtected(true);
            scrnMsg.A.no(i).ntfySubAreaTpDescTxt_A0.setInputProtected(true);
            scrnMsg.A.no(i).ntfyRunJobId_A0.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem19Txt_A0.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).ntfyHdrId_B0.setInputProtected(true);
            scrnMsg.B.no(i).ntfyRunJobId_B0.setInputProtected(true);
            scrnMsg.B.no(i).ntfyActId_B0.setInputProtected(true);
            scrnMsg.B.no(i).ntfyActNm_B0.setInputProtected(true);
        }

        scrnMsg.ntfyActTpDescTxt.setInputProtected(true);
        scrnMsg.ntfyOtptTpDescTxt.setInputProtected(true);
        scrnMsg.ntfyEmlRpyToAddr.setInputProtected(true);
        scrnMsg.ntfyEmlToAddr.setInputProtected(true);
        scrnMsg.ntfyEmlCcAddr.setInputProtected(true);
        scrnMsg.ntfyEmlBccAddr.setInputProtected(true);
        scrnMsg.ntfyDistListNmListTxt.setInputProtected(true);
        scrnMsg.ntfyEmlSubjTxt.setInputProtected(true);
        scrnMsg.xxNtfyEmlBodyTxt.setInputProtected(true);
    }

    /**
     * setCsmpPopupParam
     * @param scrnMsg NMAL7070BMsg
     * @return Object[]
     */
    public static Object[] setNotifPopupParam(NWWL0030BMsg scrnMsg) {
        Object[] param = new Object[7];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)
        scrnMsg.R.clear();

        suffixP0 = "";

        scrnNmP1 = "Notification Seach";

        tblNmP2.append("NTFY_HDR");

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Notif Name";
        whereArray0[1] = "NTFY_HDR_NM";
        whereArray0[2] = scrnMsg.ntfyHdrNm.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Notif Desc";
        whereArray1[1] = "NTFY_HDR_DESC_TXT";
        whereArray1[2] = scrnMsg.ntfyHdrDescTxt.getValue();
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray1);

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Notif Id";
        columnArray0[1] = "NTFY_HDR_ID";
        columnArray0[2] = BigDecimal.valueOf(15);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnListP4.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Notif Name";
        columnArray1[1] = "NTFY_HDR_NM";
        columnArray1[2] = BigDecimal.valueOf(30);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Notif Desc";
        columnArray2[1] = "NTFY_HDR_DESC_TXT";
        columnArray2[2] = BigDecimal.valueOf(45);
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray2);

        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "NTFY_HDR_ID";
        sortConditionArray0[1] = "ASC";
        sortConditionListP5.add(sortConditionArray0);

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }
}
