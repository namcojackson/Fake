package business.servlet.NMAL7030.common;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import parts.common.EZDBStringItemArray;
import parts.common.EZDFieldErrorException;
import business.servlet.NMAL7010.NMAL7010_QBMsgArray;
import business.servlet.NMAL7030.NMAL7030BMsg;
import business.servlet.NMAL7030.NMAL7030_ABMsg;
import business.servlet.NMAL7030.constant.NMAL7030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   SRA             E.Inada         Create          QC#2174
 * 2016/08/09   Fujitsu         W.Honda         Update          QC#13171
 *</pre>
 */
public class NMAL7030CommonLogic implements NMAL7030Constant {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    public static void setInputParam(NMAL7030BMsg scrnMsg, Object[] arg) {
        scrnMsg.clear();
        ZYPTableUtil.clear(scrnMsg.A);

        if (!(arg instanceof Object[])) {
            return;
        }

        if (arg.length < MAX_INPUT_PARAM_NUM) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.aftDeclPntDigitNum_H, (BigDecimal) arg[INPUT_AFT_DECL_PNT_DIGIT_NUM]);

        ZYPEZDItemValueSetter.setValue(scrnMsg.prcQlfyTpCd_H, (String) arg[INPUT_PRC_QLFY_TP_CD]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcQlfyValTxt_H, (String) arg[INPUT_PRC_QLFY_VAL_TXT]);
        // QC#13171 2016/08/09 Mod start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.prodCtrlNm_H, (String) arg[INPUT_PROD_CTRL_NM]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.mdseDescShortTxt_H, (String) arg[INPUT_PROD_CTRL_NM]);
        // QC#13171 2016/08/09 Mod end
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcListEquipPk_H, (BigDecimal) arg[INPUT_PRC_LIST_EQUIP_PK]);

        for (int i = 0; i < scrnMsg.prcQlfyTpCd_L.length(); i++) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcQlfyTpCd_L.no(i), ((EZDBStringItemArray) arg[INPUT_PRC_QLFY_TP_CD_LIST]).no(i));
        }

        for (int i = 0; i < scrnMsg.prcQlfyTpDescTxt_L.length(); i++) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcQlfyTpDescTxt_L.no(i), ((EZDBStringItemArray) arg[INPUT_PRC_QLFY_TP_DESC_TXT_LIST]).no(i));
        }

        if (arg[INPUT_LIST] instanceof NMAL7010_QBMsgArray) {
            NMAL7010_QBMsgArray qbMsgArray = (NMAL7010_QBMsgArray) arg[INPUT_LIST];

            for (int i = 0; i < qbMsgArray.getValidCount(); i++) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).ezUpTime_A, qbMsgArray.no(i).ezUpTime_QP.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).ezUpTimeZone_A, qbMsgArray.no(i).ezUpTimeZone_QP.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcListEquipDtlPk_A, qbMsgArray.no(i).prcListEquipDtlPk_QP.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcListEquipPk_A, qbMsgArray.no(i).prcListEquipPk_QP.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcBreakAmt_A, qbMsgArray.no(i).prcBreakAmt_QP.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).qtyDiscDtlQty_A, qbMsgArray.no(i).qtyDiscDtlQty_QP.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).delFlg_A, qbMsgArray.no(i).delFlg_QP.getValue());

                setStatus(scrnMsg.A.no(i));
            }
            scrnMsg.A.setValidCount(qbMsgArray.getValidCount());
        }

    }

    public static void setHeaderProtect(NMAL7030BMsg scrnMsg) {
        boolean isProtected = true;
        scrnMsg.prcQlfyTpCd_H.setInputProtected(isProtected);
        scrnMsg.prcQlfyValTxt_H.setInputProtected(isProtected);
        // QC#13171 2016/08/09 Mod start
//        scrnMsg.prodCtrlNm_H.setInputProtected(isProtected);
        scrnMsg.mdseDescShortTxt_H.setInputProtected(isProtected);
        // QC#13171 2016/08/09 Mod end
    }

    public static void setDetailProtect(NMAL7030BMsg scrnMsg) {
        boolean isProtected = true;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).delFlg_A.getValue())) {
                scrnMsg.A.no(i).setInputProtected(isProtected);
            } else {
                scrnMsg.A.no(i).setInputProtected(!isProtected);
            }
            scrnMsg.A.no(i).xxScrStsTxt_A.setInputProtected(isProtected);
        }
    }

    public static void clearCheckBox(NMAL7030BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.clear();
        }
    }

    public static void checkSelected(NMAL7030BMsg scrnMsg) {

        List<Integer> idxList = ZYPTableUtil.getSelectedRows(scrnMsg.A, ATTB_CHECK_BOX, ZYPConstant.FLG_ON_Y);

        if (idxList.size() > 0) {
            return;
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!scrnMsg.A.no(i).xxChkBox_A.isInputProtected()) {
                scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NMAM8054E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            }
        }
        scrnMsg.putErrorScreen();
    }

    public static void checkDeletable(NMAL7030BMsg scrnMsg) {
        List<Integer> idxList = ZYPTableUtil.getSelectedRows(scrnMsg.A, ATTB_CHECK_BOX, ZYPConstant.FLG_ON_Y);

        for (int idx : idxList) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).prcListEquipDtlPk_A)) {
                scrnMsg.addCheckItem(scrnMsg.A.no(idx).qtyDiscDtlQty_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(idx).prcBreakAmt_A);
            }
        }
    }

    public static void addNewLine(NMAL7030BMsg scrnMsg) {
        int size = scrnMsg.A.getValidCount();
        if (size >= scrnMsg.A.length()) {
            scrnMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(scrnMsg.A.length()) + "(include Active/Delete)" });
            throw new EZDFieldErrorException();
        }

        scrnMsg.A.no(size).clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(size).prcListEquipPk_A, scrnMsg.prcListEquipPk_H.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(size).delFlg_A, ZYPConstant.FLG_OFF_N);
        setStatus(scrnMsg.A.no(size));

        scrnMsg.A.setValidCount(size + 1);

        int selectIdx = scrnMsg.A.getValidCount() - 1;
        scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).qtyDiscDtlQty_A);
    }

    public static void deleteLine(NMAL7030BMsg scrnMsg) {
        List<Integer> idxList = ZYPTableUtil.getSelectedRows(scrnMsg.A, ATTB_CHECK_BOX, ZYPConstant.FLG_ON_Y);

        for (int idx : idxList) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).prcListEquipDtlPk_A)) {
                scrnMsg.A.no(idx).delFlg_A.setValue(ZYPConstant.FLG_ON_Y);
                scrnMsg.A.no(idx).xxChkBox_A.clear();

                setStatus(scrnMsg.A.no(idx));
            }
        }

        idxList = ZYPTableUtil.getSelectedRows(scrnMsg.A, ATTB_CHECK_BOX, ZYPConstant.FLG_ON_Y);
        if (idxList.size() == 0) {
            return;
        }
        ZYPTableUtil.deleteRows(scrnMsg.A, idxList);
    }

    public static void setStatus(NMAL7030_ABMsg abMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(abMsg.delFlg_A.getValue())) {
            ZYPEZDItemValueSetter.setValue(abMsg.xxScrStsTxt_A, STS_DELETED);
        } else {
            ZYPEZDItemValueSetter.setValue(abMsg.xxScrStsTxt_A, STS_ACTIVE);
        }
    }

    public static void setReturnObject(NMAL7030BMsg scrnMsg, Object[] arg) {
        NMAL7010_QBMsgArray qbMsgArray = (NMAL7010_QBMsgArray) arg[INPUT_LIST];

        ZYPTableUtil.clear(qbMsgArray);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            ZYPEZDItemValueSetter.setValue(qbMsgArray.no(i).ezUpTime_QP, scrnMsg.A.no(i).ezUpTime_A.getValue());
            ZYPEZDItemValueSetter.setValue(qbMsgArray.no(i).ezUpTimeZone_QP, scrnMsg.A.no(i).ezUpTimeZone_A.getValue());
            ZYPEZDItemValueSetter.setValue(qbMsgArray.no(i).prcListEquipDtlPk_QP, scrnMsg.A.no(i).prcListEquipDtlPk_A.getValue());
            ZYPEZDItemValueSetter.setValue(qbMsgArray.no(i).prcListEquipPk_QP, scrnMsg.A.no(i).prcListEquipPk_A.getValue());
            ZYPEZDItemValueSetter.setValue(qbMsgArray.no(i).prcBreakAmt_QP, scrnMsg.A.no(i).prcBreakAmt_A.getValue());
            ZYPEZDItemValueSetter.setValue(qbMsgArray.no(i).qtyDiscDtlQty_QP, scrnMsg.A.no(i).qtyDiscDtlQty_A.getValue());
            ZYPEZDItemValueSetter.setValue(qbMsgArray.no(i).delFlg_QP, scrnMsg.A.no(i).delFlg_A.getValue());
        }
        qbMsgArray.setValidCount(scrnMsg.A.getValidCount());
    }

    public static void checkDuplicateQty(NMAL7030BMsg scrnMsg) {
        Set<String> cacheQtySet = new HashSet<String>();
        Set<String> targetQtySet = new HashSet<String>();

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String qty = scrnMsg.A.no(i).qtyDiscDtlQty_A.getValue().toString();

            if (STS_DELETED.equals(scrnMsg.A.no(i).xxScrStsTxt_A.getValue())) {
                continue;
            }

            if (cacheQtySet.contains(qty)) {
                targetQtySet.add(qty);
            } else {
                cacheQtySet.add(qty);
            }
        }

        for (String targetQty : targetQtySet) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                String qty = scrnMsg.A.no(i).qtyDiscDtlQty_A.getValue().toString();

                if (STS_DELETED.equals(scrnMsg.A.no(i).xxScrStsTxt_A.getValue())) {
                    continue;
                }

                if (targetQty.equals(qty)) {
                    scrnMsg.A.no(i).qtyDiscDtlQty_A.setErrorInfo(1, NMAM0072E, new String[] {qty });
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).qtyDiscDtlQty_A);
                }
            }
        }
    }
}
