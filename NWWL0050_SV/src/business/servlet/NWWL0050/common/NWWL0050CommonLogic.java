/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0050.common;

import static business.servlet.NWWL0050.constant.NWWL0050Constant.BIZ_ID;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.BTN_ADD_LINE_NM;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.BTN_CMN_APL;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.BTN_CMN_APR;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.BTN_CMN_CLR;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.BTN_CMN_DEL;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.BTN_CMN_DWL;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.BTN_CMN_RJT;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.BTN_CMN_RST;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.BTN_CMN_RTN;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.BTN_CMN_SAV;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.BTN_CMN_SUB;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.BTN_DELETE_LINE_NM;
//import static business.servlet.NWWL0050.constant.NWWL0050Constant.CHK_EMAIL_PATTERN;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.NWWM0005E;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.NWWM0011E;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.SCRN_ID_00;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.UPD_FUNC_ID;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsgArray;
import business.servlet.NWWL0050.NWWL0050BMsg;
import business.servlet.NWWL0050.NWWL0050Bean;
import business.servlet.NWWL0050.NWWL0050_ABMsgArray;
import com.canon.cusa.s21.common.NMX.NMXC106001.NMXC106001CommonCheckUtil;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.NTFY_DIST_QLFY;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NWWL0050CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/05   Fujitsu         M.Ohno          Create          N/A
 * 2016/10/06   Fujitsu         Mz.Takahashi    Update          QC#14431
 *</pre>
 */
public class NWWL0050CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
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
     * @param scrnMsg NWWL0050BMsg
     * @param scrnAMsgAry NWWL0050_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWWL0050BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWWL0050BMsg
     * @param scrnAMsgAry NWWL0050_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWWL0050BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NWWL0050BMsg
     * @param scrnAMsgAry NWWL0050_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWWL0050BMsg scrnMsg, NWWL0050_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * controlScreenFields
     * @param handler S21CommonHandler
     * @param scrnMsg NWWL0050BMsg
     */
    public static void controlScreenFields(S21CommonHandler handler, NWWL0050BMsg scrnMsg) {
        scrnMsg.ntfyDistListId.setInputProtected(true);

        if (ZYPCommonFunc.hasValue(scrnMsg.ntfyDistListId)) {
            scrnMsg.effFromDt_D.setInputProtected(true);

        } else {
            scrnMsg.effFromDt_D.setInputProtected(false);
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).ntfyHdrId_B.setInputProtected(true);
            scrnMsg.B.no(i).ntfyHdrNm_B.setInputProtected(true);
            scrnMsg.B.no(i).ntfyHdrDescTxt_B.setInputProtected(true);
            scrnMsg.B.no(i).ntfyBizAreaTpDescTxt_B.setInputProtected(true);
            scrnMsg.B.no(i).ntfySubAreaTpDescTxt_B.setInputProtected(true);
            scrnMsg.B.no(i).ntfyHdrId_B.setInputProtected(true);
            scrnMsg.B.no(i).effFromDt_B.setInputProtected(true);
            scrnMsg.B.no(i).effThruDt_B.setInputProtected(true);
            scrnMsg.B.no(i).ntfyHdrActvFlg_B.setInputProtected(true);
        }

        List<String> funcIdList = getFuncId();
        String salesDate = ZYPDateUtil.getSalesDate();

        if ((ZYPCommonFunc.hasValue(scrnMsg.effThruDt_D) //
                && salesDate.compareTo(scrnMsg.effThruDt_D.getValue()) > 0) //
                || funcIdList == null //
                || !funcIdList.contains(UPD_FUNC_ID)) {

            scrnMsg.ntfyDistListNm.setInputProtected(true);
            scrnMsg.ntfyDistListDescTxt.setInputProtected(true);
            scrnMsg.ntfyBizAreaTpCd_D.setInputProtected(true);
            scrnMsg.ntfySubAreaTpCd_D.setInputProtected(true);
            scrnMsg.effFromDt_D.setInputProtected(true);
            scrnMsg.effThruDt_D.setInputProtected(true);
            scrnMsg.ntfyDistListActvFlg.setInputProtected(true);

            scrnMsg.xxRadioBtn.setInputProtected(true);
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).ntfyDistQlfyCd_A.setInputProtected(true);
                scrnMsg.A.no(i).ntfyDistListAsgValTxt_A.setInputProtected(true);
                scrnMsg.A.no(i).ntfyDistListAsgActvFlg_A.setInputProtected(true);
            }

            handler.setButtonEnabled(BTN_ADD_LINE_NM, false);
            handler.setButtonEnabled(BTN_DELETE_LINE_NM, false);
            handler.setButtonEnabled(BTN_CMN_SUB[0], false);
            handler.setButtonEnabled(BTN_CMN_RST[0], false);

        } else {
            scrnMsg.ntfyDistListNm.setInputProtected(false);
            scrnMsg.ntfyDistListDescTxt.setInputProtected(false);
            scrnMsg.ntfyBizAreaTpCd_D.setInputProtected(false);
            scrnMsg.ntfySubAreaTpCd_D.setInputProtected(false);
            scrnMsg.effThruDt_D.setInputProtected(false);
            scrnMsg.ntfyDistListActvFlg.setInputProtected(false);

            scrnMsg.xxRadioBtn.setInputProtected(false);
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).ntfyDistQlfyCd_A.setInputProtected(false);
                scrnMsg.A.no(i).ntfyDistListAsgValTxt_A.setInputProtected(false);
                scrnMsg.A.no(i).ntfyDistListAsgActvFlg_A.setInputProtected(false);
            }

            handler.setButtonEnabled(BTN_ADD_LINE_NM, true);
            if (scrnMsg.A.getValidCount() <= 0) {
                handler.setButtonEnabled(BTN_DELETE_LINE_NM, false);
            } else {
                handler.setButtonEnabled(BTN_DELETE_LINE_NM, true);
            }

            handler.setButtonEnabled(BTN_CMN_SUB[0], true);
            handler.setButtonEnabled(BTN_CMN_RST[0], true);
        }

    }

    /**
     * getFuncId
     * @return List<String>
     */
    public static List<String> getFuncId() {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            return new ArrayList<String>(0);
        }

        return funcList;
    }

    /**
     * submitCheck
     * @param scrnMsg NWWL0050BMsg
     */
    public static void submitCheck(NWWL0050BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt_D) && ZYPCommonFunc.hasValue(scrnMsg.effThruDt_D)) {
            if (scrnMsg.effFromDt_D.getValue().compareTo(scrnMsg.effThruDt_D.getValue()) > 0) {
                scrnMsg.effFromDt_D.setErrorInfo(1, NWWM0005E, // 
                        new String[] {scrnMsg.effThruDt_D.getNameForMessage(), scrnMsg.effFromDt_D.getNameForMessage() });
                scrnMsg.effThruDt_D.setErrorInfo(1, NWWM0005E, // 
                        new String[] {scrnMsg.effThruDt_D.getNameForMessage(), scrnMsg.effFromDt_D.getNameForMessage() });
            }
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (NTFY_DIST_QLFY.EMAIL.equals(scrnMsg.A.no(i).ntfyDistQlfyCd_A.getValue())) {
                // 2016/10/06 QC#1443 Modify Start --------------
                String mailAddr = scrnMsg.A.no(i).ntfyDistListAsgValTxt_A.getValue();

                //if (!scrnMsg.A.no(i).ntfyDistListAsgValTxt_A.getValue().matches(CHK_EMAIL_PATTERN)) {
                if (!ZYPCommonFunc.hasValue(mailAddr) || !NMXC106001CommonCheckUtil.checkEmailFormat(mailAddr)) {
                // 2016/10/06 QC#1443 Modify End --------------
                    scrnMsg.A.no(i).ntfyDistListAsgValTxt_A.setErrorInfo(1, NWWM0011E);
                }
            }
        }

        scrnMsg.addCheckItem(scrnMsg.ntfyDistListNm);
        scrnMsg.addCheckItem(scrnMsg.ntfyDistListId);
        scrnMsg.addCheckItem(scrnMsg.ntfyDistListDescTxt);
        scrnMsg.addCheckItem(scrnMsg.ntfyBizAreaTpCd_D);
        scrnMsg.addCheckItem(scrnMsg.ntfySubAreaTpCd_D);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_D);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_D);
        scrnMsg.addCheckItem(scrnMsg.ntfyDistListActvFlg);

        scrnMsg.A.setCheckParam(new String[] {NWWL0050Bean.ntfyDistQlfyCd_A, //
                NWWL0050Bean.ntfyDistListAsgValTxt_A, //
                NWWL0050Bean.ntfyDistListAsgActvFlg_A }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
    }
}
