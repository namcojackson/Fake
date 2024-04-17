/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7190;

import static business.servlet.NMAL7190.constant.NMAL7190Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7190.common.NMAL7190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_ATTRB;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7190_NMAL6760
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 * 2023/04/20   Hitachi         H.Watanabe      Update          QC#61200
 *</pre>
 */
public class NMAL7190_NMAL6760 extends S21CommonHandler {

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

        if (PRC_GRP_TRGT_ATTRB.ACCOUNT_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.P.no(i++).xxPopPrm);
                // 2023/04/20 QC#61200 Add Start
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).dsAcctNm_A1, scrnMsg.P.no(i++).xxPopPrm);
                // 2023/04/20 QC#61200 Add End
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.P.no(i++).xxPopPrm);
            }
        } else {
            i++;
        }
        if (PRC_GRP_TRGT_ATTRB.ACCOUNT_NAME.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                if (!NMAL7190CommonLogic.checkValDigit(scrnMsg.P.no(i).xxPopPrm.getValue().length(), scrnMsg.A.no(i).getAttr("prcGrpFromTxt_A1"))) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.P.no(i++).xxPopPrm.getValue().substring(0, scrnMsg.A.no(i).getAttr("prcGrpFromTxt_A1").getDigit()));
                    scrnMsg.setMessageInfo(NMAM8090W, new String[] {scrnMsg.A.no(index).prcGrpFromTxt_A1.getNameForMessage()});
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.P.no(i++).xxPopPrm);
                }
            } else {
                if (!NMAL7190CommonLogic.checkValDigit(scrnMsg.P.no(i).xxPopPrm.getValue().length(), scrnMsg.A.no(i).getAttr("prcGrpThruTxt_A1"))) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.P.no(i++).xxPopPrm.getValue().substring(0, scrnMsg.A.no(i).getAttr("prcGrpThruTxt_A1").getDigit()));
                    scrnMsg.setMessageInfo(NMAM8090W, new String[] {scrnMsg.A.no(index).prcGrpFromTxt_A1.getNameForMessage()});
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.P.no(i++).xxPopPrm);
                }
            }
        } else {
            i++;
        }
        if (PRC_GRP_TRGT_ATTRB.LOCATION_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
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
