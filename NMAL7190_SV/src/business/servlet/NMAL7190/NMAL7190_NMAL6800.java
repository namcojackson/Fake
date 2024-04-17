/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7190;

import static business.servlet.NMAL7190.constant.NMAL7190Constant.POPUP_BTN_FLG_FROM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7190.common.NMAL7190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_CLS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_ATTRB;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7190_NMAL6800
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7190_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;
        int index = getButtonSelectNumber();
        int i = 0;

        if (PRC_GRP_TRGT_ATTRB.ITEM_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.P.no(i++).xxPopPrm);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.P.no(i++).xxPopPrm);
            }
        } else {
            i++;
        }
        i++;
        if (PRC_GRP_TRGT_ATTRB.ITEM_TYPE.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, ZYPCodeDataUtil.getName(MDSE_ITEM_TP.class, getGlobalCompanyCode(), scrnMsg.P.no(i++).xxPopPrm.getValue()));
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, ZYPCodeDataUtil.getName(MDSE_ITEM_TP.class, getGlobalCompanyCode(), scrnMsg.P.no(i++).xxPopPrm.getValue()));
            }
        } else {
            i++;
        }
        if (PRC_GRP_TRGT_ATTRB.ITEM_CLASSIFICATION.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, ZYPCodeDataUtil.getName(MDSE_ITEM_CLS_TP.class, getGlobalCompanyCode(), scrnMsg.P.no(i++).xxPopPrm.getValue()));
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, ZYPCodeDataUtil.getName(MDSE_ITEM_CLS_TP.class, getGlobalCompanyCode(), scrnMsg.P.no(i++).xxPopPrm.getValue()));
            }
        } else {
            i++;
        }
        if (PRC_GRP_TRGT_ATTRB.MERCHANDISE_TYPE.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.P.no(i++).xxPopPrm);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.P.no(i++).xxPopPrm);
            }
        } else {
            i++;
        }
        if (PRC_GRP_TRGT_ATTRB.PRODUCT_CODE.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.P.no(i++).xxPopPrm);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.P.no(i++).xxPopPrm);
            }
        }

        NMAL7190CommonLogic.controlScreen(this, scrnMsg);
        if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.A.no(index).prcGrpFromTxt_A1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.A.no(index).prcGrpThruTxt_A1);
        }
    }
}
