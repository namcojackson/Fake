/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1370.common;

import static business.servlet.NSAL1370.constant.NSAL1370Constant.BTN_CMN_CLR;
import static business.servlet.NSAL1370.constant.NSAL1370Constant.BTN_CMN_CLS;
import static business.servlet.NSAL1370.constant.NSAL1370Constant.SCRN_ID_00;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDMsg;
import business.servlet.NSAL1370.NSAL1370BMsg;
import business.servlet.NSAL1370.NSAL1370_ABMsg;
import business.servlet.NSAL1370.NSAL1370_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NSAL1370CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Osawa         Create          N/A
 * 2017/10/26   Hitachi         K.Kojima        Update          QC#21556
 *</pre>
 */
public class NSAL1370CommonLogic {

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
     * @param btnname Button Properties in Constant class
     * @param enabled boolean(Fixed value:false)
     */
    public static void setBizBtnProp(S21CommonHandler handler, String btnname, boolean enabled) {
        handler.setButtonEnabled(btnname, enabled);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NSAL1370BMsg
     * @param scrnAMsgAry NSAL1370_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NSAL1370BMsg scrnMsg, NSAL1370_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NSAL1370BMsg
     * @param scrnAMsgAry NSAL1370_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NSAL1370BMsg scrnMsg, NSAL1370_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NSAL1370BMsg
     * @param scrnAMsgAry NSAL1370_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NSAL1370BMsg scrnMsg, NSAL1370_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * addCheckItemBizLayerErr
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemBizLayerErr(NSAL1370BMsg scrnMsg) {
        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            NSAL1370_ABMsg scrnLineMsg = scrnMsg.A.no(i);

            scrnMsg.addCheckItem(scrnLineMsg.minCopyVolCnt_A);
            scrnMsg.addCheckItem(scrnLineMsg.maxCopyVolCnt_A);
            // START 2017/10/26 K.Kojima [QC#21556,MOD]
            // scrnMsg.addCheckItem(scrnLineMsg.xsMtrAmtRate_A);
            if (!scrnLineMsg.xsMtrAmtRate_A.isInputProtected()) {
                scrnMsg.addCheckItem(scrnLineMsg.xsMtrAmtRate_A);
            }
            // END 2017/10/26 K.Kojima [QC#21556,MOD]
        }
    }

    /**
     * protectDetail
     * @param scrnMsg Screen Msg
     */
    public static void protectDetail(NSAL1370BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            NSAL1370_ABMsg scrnLineMsg = scrnMsg.A.no(i);

            scrnLineMsg.minCopyVolCnt_A.setInputProtected(true);
            scrnLineMsg.maxCopyVolCnt_A.setInputProtected(true);
            scrnLineMsg.xsMtrAmtRate_A.setInputProtected(true);
        }
    }

    /**
     * initItemCtrl
     * @param handler Event Handler
     * @param scrnMsg Screen Msg
     */
    public static void initItemCtrl(S21CommonHandler handler, NSAL1370BMsg scrnMsg) {
        scrnMsg.t_MdlNm.setInputProtected(true);
        scrnMsg.mtrLbDescTxt_BL.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NSAL1370_ABMsg aScrnMsg = scrnMsg.A.no(i);
            aScrnMsg.prcSvcTierTpDescTxt_A.setInputProtected(true);
        }
    }

    /**
     * clearInput
     * @param scrnMsg Screen Msg
     */
    public static void clearInput(NSAL1370BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NSAL1370_ABMsg aScrnMsg = scrnMsg.A.no(i);
            aScrnMsg.minCopyVolCnt_A.clear();
            aScrnMsg.maxCopyVolCnt_A.clear();
            // START 2017/10/26 K.Kojima [QC#21556,MOD]
            // aScrnMsg.xsMtrAmtRate_A.clear();
            if (!aScrnMsg.xsMtrAmtRate_A.isInputProtected()) {
                aScrnMsg.xsMtrAmtRate_A.clear();
            }
            // END 2017/10/26 K.Kojima [QC#21556,MOD]
        }
    }

    /**
     * setOutputPrm
     * @param scrnMsg Screen Msg
     * @param arg Event Object[]
     */
    public static void setOutputPrm(NSAL1370BMsg scrnMsg, Object[] arg) {
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

    // START 2017/10/26 K.Kojima [QC#21556,ADD]
    /**
     * protectDetailForAgg
     * @param scrnMsg Screen Msg
     */
    public static void protectDetailForAgg(NSAL1370BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1370_ABMsg scrnLineMsg = scrnMsg.A.no(i);
            scrnLineMsg.xsMtrAmtRate_A.setInputProtected(true);
        }
    }
    // END 2017/10/26 K.Kojima [QC#21556,ADD]
}
