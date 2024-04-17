/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.*;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_ADD_CHARGE_ITEM;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_ADD_TO_CONTR;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_BOOK_ITEM;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_COVERED_ITEM;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_COVERED_UNIT;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_OVERRIDE;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_RENTAL_EQUIP_COVERED_ITEM;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1330.NSAL1330CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do Nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        NSAL1330CMsg bizMsg = new NSAL1330CMsg();

        boolean chekeFlg = false;
        for (int i = 0; !chekeFlg && i < scrnMsg.P.length(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.P.no(i).xxSelFlg_P.getValue())) {
                chekeFlg = true;
            }
        }
        if (chekeFlg) {

            if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_OVERRIDE)) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.P.no(1).xxSelFlg_P.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.svcMemoRsnCd, scrnMsg.P.no(0).xxComnScrColValTxt_P);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.svcMemoRsnDescTxt, scrnMsg.P.no(1).xxComnScrColValTxt_P);
                }
                scrnMsg.setFocusItem(scrnMsg.svcMemoRsnDescTxt);

            } else if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_BOOK_ITEM)) {

                int index = scrnMsg.xxNum_A.getValueInt();
                ZYPEZDItemValueSetter.setValue(scrnMsg.Z.no(index).prcBookMdseCd_Z, scrnMsg.P.no(0).xxComnScrColValTxt_P);
                scrnMsg.setFocusItem(scrnMsg.Z.no(index).prcBookMdseCd_Z);

            } else if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_BOOK_ITEM_CONFIG)) {

                int index = scrnMsg.xxNum_A.getValueInt();
                ZYPEZDItemValueSetter.setValue(scrnMsg.U.no(index).prcBookMdseCd_U, scrnMsg.P.no(0).xxComnScrColValTxt_P);
                scrnMsg.setFocusItem(scrnMsg.U.no(index).prcBookMdseCd_U);

            } else if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_COVERED_ITEM)) {

                int index = scrnMsg.xxNum_A.getValueInt();
                ZYPEZDItemValueSetter.setValue(scrnMsg.J.no(index).cpoDtlLineNum_J, scrnMsg.P.no(3).xxComnScrColValTxt_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.J.no(index).cpoDtlLineSubNum_J, scrnMsg.P.no(4).xxComnScrColValTxt_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.J.no(index).mdseCd_J, scrnMsg.P.no(1).xxComnScrColValTxt_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.J.no(index).mdseDescShortTxt_J, scrnMsg.P.no(2).xxComnScrColValTxt_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.J.no(index).xxLineNum_J, scrnMsg.P.no(0).xxComnScrColValTxt_P);
                scrnMsg.setFocusItem(scrnMsg.J.no(index).addlBasePrcDealAmt_J);

            } else if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_RENTAL_EQUIP_COVERED_ITEM)) {

                int index = scrnMsg.xxNum_A.getValueInt();
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).cpoDtlLineNum_B, scrnMsg.P.no(3).xxComnScrColValTxt_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).cpoDtlLineSubNum_B, scrnMsg.P.no(4).xxComnScrColValTxt_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).mdseCd_B, scrnMsg.P.no(1).xxComnScrColValTxt_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).mdseDescShortTxt_B, scrnMsg.P.no(2).xxComnScrColValTxt_P);
                //                scrnMsg.setFocusItem(scrnMsg.B.no(index).mdseCd_B);
                //                StringBuilder sbNum = new StringBuilder();
                //                sbNum.append(scrnMsg.B.no(index).cpoDtlLineNum_B.getValue()).append(scrnMsg.B.no(index).cpoDtlLineSubNum_B.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).xxLineNum_B, scrnMsg.P.no(0).xxComnScrColValTxt_P);
                scrnMsg.setFocusItem(scrnMsg.B.no(index).addlBasePrcDealAmt_B);

            } else if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_COVERED_UNIT)) {

                int index = scrnMsg.xxNum_A.getValueInt();
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(index).cpoDtlLineNum_C, scrnMsg.P.no(3).xxComnScrColValTxt_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(index).cpoDtlLineSubNum_C, scrnMsg.P.no(4).xxComnScrColValTxt_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(index).mdseCd_CU, scrnMsg.P.no(1).xxComnScrColValTxt_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(index).mdseDescShortTxt_CU, scrnMsg.P.no(2).xxComnScrColValTxt_P);
                scrnMsg.setFocusItem(scrnMsg.C.no(index).mdseCd_CU);
                //                StringBuilder sbNum = new StringBuilder();
                //                sbNum.append(scrnMsg.C.no(index).cpoDtlLineNum_C.getValue()).append(scrnMsg.C.no(index).cpoDtlLineSubNum_C.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(index).xxLineNum_C, scrnMsg.P.no(0).xxComnScrColValTxt_P);
                scrnMsg.setFocusItem(scrnMsg.C.no(index).mdseCd_CI);

            } else if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_ADD_CHARGE_ITEM)) {

                int index = scrnMsg.xxNum_A.getValueInt();

                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(index).mdseCd_CI, scrnMsg.P.no(0).xxComnScrColValTxt_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(index).mdseDescShortTxt_CI, scrnMsg.P.no(1).xxComnScrColValTxt_P);
                scrnMsg.setFocusItem(scrnMsg.C.no(index).mdseCd_CI);
                // StringBuilder sbNum = new StringBuilder();
                // sbNum.append(scrnMsg.C.no(index).cpoDtlLineNum_C.getValue()).append(scrnMsg.C.no(index).cpoDtlLineSubNum_C.getValue());
                // ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(index).xxLineNum_C, sbNum.toString());
                scrnMsg.setFocusItem(scrnMsg.C.no(index).addlChrgPrcDealAmt_C);

            } else if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_ADD_TO_CONTR)) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrPk_AD, new BigDecimal(scrnMsg.P.no(0).xxComnScrColValTxt_P.getValue()));
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrNum_AD, new String(scrnMsg.P.no(1).xxComnScrColValTxt_P.getValue()));
                scrnMsg.setFocusItem(scrnMsg.dsContrNum_AD);

            }
        }
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int index = scrnMsg.xxNum_A.getValueInt();

        if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_COVERED_ITEM)) {
            scrnMsg.addCheckItem(scrnMsg.J.no(index).mdseCd_J);

        } else if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_RENTAL_EQUIP_COVERED_ITEM)) {
            scrnMsg.addCheckItem(scrnMsg.B.no(index).mdseCd_B);

        } else if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_ADD_CHARGE_ITEM)) {
            scrnMsg.addCheckItem(scrnMsg.C.no(index).mdseCd_CI);

        } else if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_COVERED_UNIT)) {
            scrnMsg.addCheckItem(scrnMsg.C.no(index).mdseCd_CU);

        }

        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_COVERED_ITEM)) {
            scrnMsg.setFocusItem(scrnMsg.J.no(index).addlBasePrcDealAmt_J);

        } else if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_BOOK_ITEM)) {
            scrnMsg.setFocusItem(scrnMsg.Z.no(index).prcBookMdseCd_Z);

        } else if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_BOOK_ITEM_CONFIG)) {
            scrnMsg.setFocusItem(scrnMsg.U.no(index).prcBookMdseCd_U);

        } else if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_RENTAL_EQUIP_COVERED_ITEM)) {
            scrnMsg.setFocusItem(scrnMsg.B.no(index).addlBasePrcDealAmt_B);

        } else if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_ADD_CHARGE_ITEM)) {
            scrnMsg.setFocusItem(scrnMsg.C.no(index).addlChrgPrcDealAmt_C);

        } else if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_COVERED_UNIT)) {
            scrnMsg.setFocusItem(scrnMsg.C.no(index).mdseCd_CI);

        } else if (scrnMsg.xxPopPrm_P1.getValue().equals(POP_UP_ADD_ACTIVE_METER)) {

            NSAL1330_RBMsg rScrnMsg = scrnMsg.R.no(scrnMsg.xxNum_A.getValueInt());
            BigDecimal mdlId = rScrnMsg.mdlId_R.getValue();
//            BigDecimal cpoSvcConfigRefPk = rScrnMsg.cpoSvcConfigRefPk_R.getValue();
            BigDecimal dsContrDtlPk = rScrnMsg.dsContrDtlPk_R.getValue();
            String bllgMtrLbCd = scrnMsg.P.no(0).xxComnScrColValTxt_P.getValue();

            for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
                NSAL1330_UBMsg uScrnMsg = scrnMsg.U.no(i);
                if (mdlId.compareTo(uScrnMsg.mdlId_U.getValue()) != 0 //
                        || dsContrDtlPk.compareTo(uScrnMsg.dsContrDtlPk_U.getValue()) != 0 //
                        || !bllgMtrLbCd.equals(uScrnMsg.bllgMtrLbCd_U.getValue())) {
                    continue;
                }
                scrnMsg.setFocusItem(uScrnMsg.prcListBandDescTxt_U);
                break;
            }
        }

    }
}
