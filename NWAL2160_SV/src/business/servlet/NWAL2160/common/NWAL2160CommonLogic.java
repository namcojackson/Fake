/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2160.common;

import static business.servlet.NWAL2160.constant.NWAL2160Constant.BTN_CMN_CLR;
import static business.servlet.NWAL2160.constant.NWAL2160Constant.BTN_CMN_CLS;
import static business.servlet.NWAL2160.constant.NWAL2160Constant.SCRN_ID_00;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDMsg;
import business.servlet.NWAL2160.NWAL2160BMsg;
import business.servlet.NWAL2160.NWAL2160_ABMsg;
import business.servlet.NWAL2160.NWAL2160_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL2160CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NWAL2160CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);

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
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setBizBtnProp(S21CommonHandler handler, String btnname, boolean enabled) {
        handler.setButtonEnabled(btnname, enabled);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL2160BMsg
     * @param scrnAMsgAry NWAL2160_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL2160BMsg scrnMsg, NWAL2160_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL2160BMsg
     * @param scrnAMsgAry NWAL2160_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL2160BMsg scrnMsg, NWAL2160_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NWAL2160BMsg
     * @param scrnAMsgAry NWAL2160_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWAL2160BMsg scrnMsg, NWAL2160_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * addCheckItemBizLayerErr
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemBizLayerErr(NWAL2160BMsg scrnMsg) {
        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            NWAL2160_ABMsg scrnLineMsg = scrnMsg.A.no(i);

            scrnMsg.addCheckItem(scrnLineMsg.minCopyVolCnt_A);
            scrnMsg.addCheckItem(scrnLineMsg.maxCopyVolCnt_A);
            scrnMsg.addCheckItem(scrnLineMsg.xsMtrAmtRate_A);
        }
    }

    public static void protectDetail(NWAL2160BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            NWAL2160_ABMsg scrnLineMsg = scrnMsg.A.no(i);

            scrnLineMsg.minCopyVolCnt_A.setInputProtected(true);
            scrnLineMsg.maxCopyVolCnt_A.setInputProtected(true);
            scrnLineMsg.xsMtrAmtRate_A.setInputProtected(true);
        }
    }

    public static void initItemCtrl(S21CommonHandler handler, NWAL2160BMsg scrnMsg) {
        scrnMsg.t_MdlNm.setInputProtected(true);
        scrnMsg.mtrLbDescTxt_BL.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NWAL2160_ABMsg aScrnMsg = scrnMsg.A.no(i);
            aScrnMsg.prcSvcTierTpDescTxt_A.setInputProtected(true);
        }
    }

    public static void clearInput(NWAL2160BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NWAL2160_ABMsg aScrnMsg = scrnMsg.A.no(i);
            aScrnMsg.minCopyVolCnt_A.clear();
            aScrnMsg.maxCopyVolCnt_A.clear();
            aScrnMsg.xsMtrAmtRate_A.clear();
        }
    }

    public static void setOutputPrm(NWAL2160BMsg scrnMsg, Object[] arg) {
        if (!(arg instanceof Object[])) {
            return;
        }

        int ixP = 0;
        Object[] params = (Object[]) arg;
        // Set Parameter
        ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[ixP++], scrnMsg.xxProcMd_P);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[ixP++], scrnMsg.cpoSvcPrcPk_P);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[ixP++], scrnMsg.cpoSvcDtlPk_P);
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[ixP++], scrnMsg.mdlId_P);
        ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[ixP++], scrnMsg.bllgMtrLbCd_P);
        ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[ixP++], scrnMsg.dsContrCatgCd_P);

        String suffix = (String) params[ixP++];
        EZDBMsgArray array = (EZDBMsgArray) params[ixP];
        EZDMsg.copy(scrnMsg.A, "A", scrnMsg.Q, "Q");
        for (int i = 0; i < scrnMsg.Q.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(i).bllgMtrLbCd_Q, scrnMsg.bllgMtrLbCd_P);
        }
        EZDMsg.copy(scrnMsg.Q, "Q", array, suffix);
    }

}
