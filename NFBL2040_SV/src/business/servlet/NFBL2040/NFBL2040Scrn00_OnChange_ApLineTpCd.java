/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import static business.servlet.NFBL2040.constant.NFBL2040Constant.BIZ_ID;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.BTN_NORMAL_CHARGE_ACCOUNT;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.BTN_NORMAL_ITEM_DESCRIPTION;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.BTN_NORMAL_MDSE;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.FUNC_CD_20;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.TAX;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2040.NFBL2040CMsg;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_LINE_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/14   Hitachi         Y.Tsuchimoto    Create          QC#13333
 * 2017/11/14   CITS            T.Wada          Create          QC#21727
 * 2017/12/26   Hitachi         Y.Takeno        Update          QC#23022
 * 2018/03/07   Hitachi         J.Kim           Update          QC#24636
 * 2018/04/10   CITS            K.Ogino         Update          QC#24985
 * 2018/05/25   CITS            K.Ogino         Update          QC#25902,QC#25190,QC#25141
 * 2018/06/26   CITS            S.Katsuma       Update          QC#24617,26566
 * 2019/11/02   Fujitsu         Y.Matsui        Update          QC#54439
 * 2020/04/22   CITS            R.Azucena       Update          QC#56829
 *</pre>
 */
public class NFBL2040Scrn00_OnChange_ApLineTpCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // QC#21727
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());
        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        // QC#21727
        NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;
        int idx = getButtonSelectNumber();
        // START 2017/12/26 [QC#23022, MOD]
        if (AP_LINE_TP.FREIGHT.equals(scrnMsg.A.no(idx).apLineTpCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).mdseCd_A1, bizMsg.A.no(idx).mdseCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxMdseCd_A1, bizMsg.A.no(idx).xxMdseCd_A1.getValue());
            // START 2018/03/08 J.Kim [QC#24636,ADD]
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).mdseDescShortTxt_A1, bizMsg.A.no(idx).xxMdseCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxCntnrFlg_A1, ZYPConstant.FLG_ON_Y);
            scrnMsg.A.no(idx).mdseCd_A1.clear();
           // END 2018/03/08 J.Kim [QC#24636,ADD]
        }
        // END   2017/12/26 [QC#23022, MOD]
        // START 2018/03/08 J.Kim [QC#24636,ADD]
        if (AP_LINE_TP.TAX.equals(scrnMsg.A.no(idx).apLineTpCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).mdseDescShortTxt_A1, TAX);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxCntnrFlg_A1, ZYPConstant.FLG_ON_Y);
            scrnMsg.A.no(idx).mdseCd_A1.clear();
        }
        // START QC#25902,QC#25190,QC#25141
        if (AP_LINE_TP.MISC.equals(scrnMsg.A.no(idx).apLineTpCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxCntnrFlg_A1, ZYPConstant.FLG_ON_Y);
            scrnMsg.A.no(idx).mdseCd_A1.clear();
        }
        // END QC#25902,QC#25190,QC#25141
        // END 2018/03/08 J.Kim [QC#24636,ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxCmntTxt_A1, bizMsg.A.no(idx).xxCmntTxt_A1.getValue());
        // Mod QC#24985
        addLineScrnControl(scrnMsg, true, idx);
    }

    /**
     * Add QC#24985
     * @param scrnMsg NFBL2040BMsg
     * @param changeFlg boolean
     * @param idx int
     */
    private void addLineScrnControl(NFBL2040BMsg scrnMsg, boolean changeFlg, int idx) {

        setButtonEnabled(BTN_NORMAL_CHARGE_ACCOUNT, idx, true);
        if (!changeFlg && ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).apLineTpCd_A1) && AP_LINE_TP.ITEM.equals(scrnMsg.A.no(idx).apLineTpCd_A1.getValue())) {
            setButtonEnabled(BTN_NORMAL_MDSE, idx, true);
            setButtonEnabled(BTN_NORMAL_ITEM_DESCRIPTION, idx, true);
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).apLineTpCd_A1) && !AP_LINE_TP.ITEM.equals(scrnMsg.A.no(idx).apLineTpCd_A1.getValue())) {
            setButtonEnabled(BTN_NORMAL_MDSE, idx, false);
            setButtonEnabled(BTN_NORMAL_ITEM_DESCRIPTION, idx, false);
        } else {
            setButtonEnabled(BTN_NORMAL_MDSE, idx, true);
            setButtonEnabled(BTN_NORMAL_ITEM_DESCRIPTION, idx, true);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).apLineTpCd_A1) && AP_LINE_TP.ITEM.equals(scrnMsg.A.no(idx).apLineTpCd_A1.getValue()) && !NFBL2040CommonLogic.isEditableLine(scrnMsg, scrnMsg.A.no(idx))) {
            scrnMsg.A.no(idx).xxChkBox_A1.setInputProtected(false);
            scrnMsg.A.no(idx).xxDtlLineNum_A1.setInputProtected(true);
            scrnMsg.A.no(idx).apLineTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(idx).mdseCd_A1.setInputProtected(true);
            scrnMsg.A.no(idx).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(idx).xxInvAmt_A1.setInputProtected(true);
            scrnMsg.A.no(idx).vndUomCd_A1.setInputProtected(true);
            scrnMsg.A.no(idx).xxCmntTxt_A1.setInputProtected(true);
            scrnMsg.A.no(idx).apAcctDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(idx).dealGrsUnitPrcAmt_A1.setInputProtected(true);
            scrnMsg.A.no(idx).poQty_A1.setInputProtected(true);
            scrnMsg.A.no(idx).invRcvQty_A1.setInputProtected(true);
            scrnMsg.A.no(idx).apRejQty_A1.setInputProtected(true);
            scrnMsg.A.no(idx).apBillQty_A1.setInputProtected(true);
            scrnMsg.A.no(idx).xxInvQty_A1.setInputProtected(true);
            // START 2021/04/22 R.Azucena [QC#56829, ADD]
            scrnMsg.A.no(idx).slsHldQty_A1.setInputProtected(true);
            // END 2021/04/22 R.Azucena [QC#56829, ADD]
            scrnMsg.A.no(idx).dsContrNum_A1.setInputProtected(true);
            scrnMsg.A.no(idx).custDlrCd_A1.setInputProtected(true);
            scrnMsg.A.no(idx).serNum_A1.setInputProtected(true);
            scrnMsg.A.no(idx).csmpInvNum_A1.setInputProtected(true);
            scrnMsg.A.no(idx).xxInstlFullAddr_A1.setInputProtected(true);
            scrnMsg.A.no(idx).entPoDtlDealNetAmt_A2.setInputProtected(true);
            scrnMsg.A.no(idx).poNum_A1.setInputProtected(true);
            scrnMsg.A.no(idx).invCrctDt_A1.setInputProtected(true);
            // START 2018/06/26 S.Katsuma [QC#24617,ADD]
            scrnMsg.A.no(idx).locNm_A1.setInputProtected(true);
            // END 2018/06/26 S.Katsuma [QC#24617,ADD]
        }

        scrnMsg.A.no(idx).xxDtlLineNum_A1.setInputProtected(true);
        if (!changeFlg && ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).apLineTpCd_A1) && AP_LINE_TP.ITEM.equals(scrnMsg.A.no(idx).apLineTpCd_A1.getValue())) {
            scrnMsg.A.no(idx).xxChkBox_A1.setInputProtected(false);
            scrnMsg.A.no(idx).apLineTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(idx).mdseCd_A1.setInputProtected(true);
            scrnMsg.A.no(idx).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(idx).xxInvAmt_A1.setInputProtected(false);
            scrnMsg.A.no(idx).vndUomCd_A1.setInputProtected(true);
            // START 2019/11/02 [QC#54439, MOD]
            // START 2018/06/26 S.Katsuma [QC#26566,MOD]
            NFBL2040CommonLogic.setInputProtectedChargeAccount(scrnMsg, scrnMsg.A.no(idx));
            // END 2018/06/26 S.Katsuma [QC#26566,MOD]
            // END 2019/11/02 [QC#54439, MOD]
            scrnMsg.A.no(idx).apAcctDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(idx).dealGrsUnitPrcAmt_A1.setInputProtected(false);
            scrnMsg.A.no(idx).poQty_A1.setInputProtected(true);
            scrnMsg.A.no(idx).invRcvQty_A1.setInputProtected(true);
            scrnMsg.A.no(idx).apRejQty_A1.setInputProtected(true);
            scrnMsg.A.no(idx).apBillQty_A1.setInputProtected(false);
            scrnMsg.A.no(idx).xxInvQty_A1.setInputProtected(true);
            // START 2021/04/22 R.Azucena [QC#56829, ADD]
            scrnMsg.A.no(idx).slsHldQty_A1.setInputProtected(true);
            // END 2021/04/22 R.Azucena [QC#56829, ADD]
            scrnMsg.A.no(idx).dsContrNum_A1.setInputProtected(true);
            scrnMsg.A.no(idx).custDlrCd_A1.setInputProtected(true);
            scrnMsg.A.no(idx).serNum_A1.setInputProtected(true);
            scrnMsg.A.no(idx).csmpInvNum_A1.setInputProtected(true);
            scrnMsg.A.no(idx).xxInstlFullAddr_A1.setInputProtected(true);
            scrnMsg.A.no(idx).entPoDtlDealNetAmt_A2.setInputProtected(true);
            scrnMsg.A.no(idx).poNum_A1.setInputProtected(true);
            scrnMsg.A.no(idx).invCrctDt_A1.setInputProtected(true);
            // START 2018/06/26 S.Katsuma [QC#24617,ADD]
            scrnMsg.A.no(idx).locNm_A1.setInputProtected(true);
            // END 2018/06/26 S.Katsuma [QC#24617,ADD]
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).apLineTpCd_A1) && !AP_LINE_TP.ITEM.equals(scrnMsg.A.no(idx).apLineTpCd_A1.getValue())) {

            scrnMsg.A.no(idx).xxChkBox_A1.setInputProtected(false);
            scrnMsg.A.no(idx).apLineTpCd_A1.setInputProtected(false);
            scrnMsg.A.no(idx).mdseCd_A1.setInputProtected(true);
            // START QC#25902,QC#25190,QC#25141
            if (AP_LINE_TP.MISC.equals(scrnMsg.A.no(idx).apLineTpCd_A1.getValue())) {
                scrnMsg.A.no(idx).mdseDescShortTxt_A1.setInputProtected(false);
            } else {
                scrnMsg.A.no(idx).mdseDescShortTxt_A1.setInputProtected(true);
            }
            // END QC#25902,QC#25190,QC#25141
            scrnMsg.A.no(idx).xxInvAmt_A1.setInputProtected(false);
            scrnMsg.A.no(idx).vndUomCd_A1.setInputProtected(true);
            // START 2019/11/02 [QC#54439, MOD]
            // START 2018/06/26 S.Katsuma [QC#26566,MOD]
            NFBL2040CommonLogic.setInputProtectedChargeAccount(scrnMsg, scrnMsg.A.no(idx));
            // END 2018/06/26 S.Katsuma [QC#26566,MOD]
            // END 2019/11/02 [QC#54439, MOD]
            scrnMsg.A.no(idx).apAcctDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(idx).dealGrsUnitPrcAmt_A1.setInputProtected(false);
            scrnMsg.A.no(idx).poQty_A1.setInputProtected(true);
            scrnMsg.A.no(idx).invRcvQty_A1.setInputProtected(true);
            scrnMsg.A.no(idx).apRejQty_A1.setInputProtected(true);
            scrnMsg.A.no(idx).apBillQty_A1.setInputProtected(true);
            scrnMsg.A.no(idx).xxInvQty_A1.setInputProtected(true);
            // START 2021/04/22 R.Azucena [QC#56829, ADD]
            scrnMsg.A.no(idx).slsHldQty_A1.setInputProtected(true);
            // END 2021/04/22 R.Azucena [QC#56829, ADD]
            scrnMsg.A.no(idx).dsContrNum_A1.setInputProtected(true);
            scrnMsg.A.no(idx).custDlrCd_A1.setInputProtected(true);
            scrnMsg.A.no(idx).serNum_A1.setInputProtected(true);
            scrnMsg.A.no(idx).csmpInvNum_A1.setInputProtected(true);
            scrnMsg.A.no(idx).xxInstlFullAddr_A1.setInputProtected(true);
            scrnMsg.A.no(idx).entPoDtlDealNetAmt_A2.setInputProtected(true);
            scrnMsg.A.no(idx).poNum_A1.setInputProtected(true);
            scrnMsg.A.no(idx).invCrctDt_A1.setInputProtected(true);
            // START 2018/06/26 S.Katsuma [QC#24617,ADD]
            scrnMsg.A.no(idx).locNm_A1.setInputProtected(true);
            // END 2018/06/26 S.Katsuma [QC#24617,ADD]

            if (!AP_LINE_TP.FREIGHT.equals(scrnMsg.A.no(idx).apLineTpCd_A1.getValue())) {
                scrnMsg.A.no(idx).mdseCd_A1.clear();
            }
            scrnMsg.A.no(idx).mdseCd_A1.clear();
            scrnMsg.A.no(idx).vndUomCd_A1.clear();
            scrnMsg.A.no(idx).dealGrsUnitPrcAmt_A1.clear();
            scrnMsg.A.no(idx).poQty_A1.clear();
            scrnMsg.A.no(idx).invRcvQty_A1.clear();
            scrnMsg.A.no(idx).apRejQty_A1.clear();
            scrnMsg.A.no(idx).apBillQty_A1.clear();
            scrnMsg.A.no(idx).dsContrNum_A1.clear();
            scrnMsg.A.no(idx).custDlrCd_A1.clear();
            scrnMsg.A.no(idx).serNum_A1.clear();
            scrnMsg.A.no(idx).csmpInvNum_A1.clear();
            scrnMsg.A.no(idx).xxInstlFullAddr_A1.clear();
            scrnMsg.A.no(idx).entPoDtlDealNetAmt_A2.clear();
            scrnMsg.A.no(idx).poNum_A1.clear();
            scrnMsg.A.no(idx).invCrctDt_A1.clear();
            // START 2018/06/26 S.Katsuma [QC#24617,ADD]
            scrnMsg.A.no(idx).locNm_A1.clear();
            // END 2018/06/26 S.Katsuma [QC#24617,ADD]
        } else {
            if (scrnMsg.A.no(idx).apLineTpCd_A1.isInputProtected() && AP_LINE_TP.ITEM.equals(scrnMsg.A.no(idx).apLineTpCd_A1.getValue())) {
                scrnMsg.A.no(idx).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(idx).apLineTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(idx).mdseCd_A1.setInputProtected(true);
                scrnMsg.A.no(idx).mdseDescShortTxt_A1.setInputProtected(true);
                scrnMsg.A.no(idx).xxInvAmt_A1.setInputProtected(false);
                scrnMsg.A.no(idx).vndUomCd_A1.setInputProtected(true);
                // START 2019/11/02 [QC#54439, MOD]
                // START 2018/06/26 S.Katsuma [QC#26566,MOD]
                NFBL2040CommonLogic.setInputProtectedChargeAccount(scrnMsg, scrnMsg.A.no(idx));
                // END 2018/06/26 S.Katsuma [QC#26566,MOD]
                // END 2019/11/02 [QC#54439, MOD]
                scrnMsg.A.no(idx).apAcctDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(idx).dealGrsUnitPrcAmt_A1.setInputProtected(false);
                // START 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(idx).slsHldQty_A1.setInputProtected(true);
                // END 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(idx).poQty_A1.setInputProtected(true);
                scrnMsg.A.no(idx).invRcvQty_A1.setInputProtected(true);
                scrnMsg.A.no(idx).apRejQty_A1.setInputProtected(true);
                scrnMsg.A.no(idx).apBillQty_A1.setInputProtected(false);
                scrnMsg.A.no(idx).dsContrNum_A1.setInputProtected(true);
                scrnMsg.A.no(idx).custDlrCd_A1.setInputProtected(true);
                scrnMsg.A.no(idx).serNum_A1.setInputProtected(true);
                scrnMsg.A.no(idx).csmpInvNum_A1.setInputProtected(true);
                scrnMsg.A.no(idx).xxInstlFullAddr_A1.setInputProtected(true);
                scrnMsg.A.no(idx).entPoDtlDealNetAmt_A2.setInputProtected(true);
                scrnMsg.A.no(idx).poNum_A1.setInputProtected(true);
                scrnMsg.A.no(idx).invCrctDt_A1.setInputProtected(true);
                // START 2018/06/26 S.Katsuma [QC#24617,ADD]
                scrnMsg.A.no(idx).locNm_A1.setInputProtected(true);
                // END 2018/06/26 S.Katsuma [QC#24617,ADD]
            } else {
                scrnMsg.A.no(idx).apLineTpCd_A1.setInputProtected(false);
                scrnMsg.A.no(idx).mdseCd_A1.setInputProtected(false);
                scrnMsg.A.no(idx).mdseDescShortTxt_A1.setInputProtected(false);
                scrnMsg.A.no(idx).xxInvAmt_A1.setInputProtected(false);
                scrnMsg.A.no(idx).vndUomCd_A1.setInputProtected(false);
                // START 2019/11/02 [QC#54439, MOD]
                // START 2018/06/26 S.Katsuma [QC#26566,MOD]
                NFBL2040CommonLogic.setInputProtectedChargeAccount(scrnMsg, scrnMsg.A.no(idx));
                // END 2018/06/26 S.Katsuma [QC#26566,MOD]
                // END 2019/11/02 [QC#54439, MOD]
                scrnMsg.A.no(idx).apAcctDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(idx).dealGrsUnitPrcAmt_A1.setInputProtected(false);
                scrnMsg.A.no(idx).poQty_A1.setInputProtected(false);
                scrnMsg.A.no(idx).invRcvQty_A1.setInputProtected(false);
                scrnMsg.A.no(idx).apRejQty_A1.setInputProtected(false);
                scrnMsg.A.no(idx).apBillQty_A1.setInputProtected(false);
                scrnMsg.A.no(idx).xxInvQty_A1.setInputProtected(false);
                // START 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(idx).slsHldQty_A1.setInputProtected(true);
                // END 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(idx).dsContrNum_A1.setInputProtected(false);
                scrnMsg.A.no(idx).custDlrCd_A1.setInputProtected(false);
                scrnMsg.A.no(idx).serNum_A1.setInputProtected(false);
                scrnMsg.A.no(idx).csmpInvNum_A1.setInputProtected(false);
                scrnMsg.A.no(idx).xxInstlFullAddr_A1.setInputProtected(false);
                scrnMsg.A.no(idx).entPoDtlDealNetAmt_A2.setInputProtected(true);
                scrnMsg.A.no(idx).poNum_A1.setInputProtected(true);
                scrnMsg.A.no(idx).invCrctDt_A1.setInputProtected(true);
                // START 2018/06/26 S.Katsuma [QC#24617,ADD]
                scrnMsg.A.no(idx).locNm_A1.setInputProtected(true);
                // END 2018/06/26 S.Katsuma [QC#24617,ADD]
            }
        }
    }
}
