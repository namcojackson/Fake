package business.servlet.ZZIL0030.common;

import business.servlet.ZZIL0030.ZZIL0030BMsg;
import static business.servlet.ZZIL0030.constant.ZZIL0030Constant.BTN_CMN_SAV;
import static business.servlet.ZZIL0030.constant.ZZIL0030Constant.BTN_CMN_SUB;
import static business.servlet.ZZIL0030.constant.ZZIL0030Constant.BTN_CMN_APL;
import static business.servlet.ZZIL0030.constant.ZZIL0030Constant.BTN_CMN_APR;
import static business.servlet.ZZIL0030.constant.ZZIL0030Constant.BTN_CMN_RJT;
import static business.servlet.ZZIL0030.constant.ZZIL0030Constant.BTN_CMN_DWL;
import static business.servlet.ZZIL0030.constant.ZZIL0030Constant.BTN_CMN_DEL;
import static business.servlet.ZZIL0030.constant.ZZIL0030Constant.BTN_CMN_CLR;
import static business.servlet.ZZIL0030.constant.ZZIL0030Constant.BTN_CMN_RST;
import static business.servlet.ZZIL0030.constant.ZZIL0030Constant.BTN_CMN_RTN;
import static business.servlet.ZZIL0030.constant.ZZIL0030Constant.SCRN_ID_00;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/15   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0030CommonLogic {

    /**
     * Method name: dspScrn00 <dd>The method explanation: init
     * Screen00 Control. <dd>Remarks:
     * @param scrnMsg ZZIL0030BMsg
     * @param handler S21CommonHandler
     */
    public static void dspScrn00(ZZIL0030BMsg scrnMsg, S21CommonHandler handler) {

        // Common Button Control
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        } else {
            handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 1, null);
        }
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

        ZYPEZDItemValueSetter.setValue(scrnMsg.ezTableID, "MDB_INBD_INTFC_CONFIG");
        scrnMsg.ezTableID.setInputProtected(true);

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A_LeftTBL", scrnMsg.A);
        tblColor.setAlternateRowsBG("A_RightTBL", scrnMsg.A);
    }

    /**
     * Method name: dspScrn01 <dd>The method explanation: init
     * Screen01 Control. <dd>Remarks:
     * @param scrnMsg ZZIL0030BMsg
     * @param handler S21CommonHandler
     */
    public static void dspScrn01(ZZIL0030BMsg scrnMsg, S21CommonHandler handler) {

        if (scrnMsg.xxScrEventNm.getValue().equals("AddIF")) {
            // Common Button Control
            handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
            handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
            handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

            scrnMsg.intfcId_2.setInputProtected(false);
            scrnMsg.bizApiId_2.setInputProtected(false);
        } else if (scrnMsg.xxScrEventNm.getValue().equals("Edit")) {
            // Common Button Control
            handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
            handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
            handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

            scrnMsg.intfcId_2.setInputProtected(true);
            scrnMsg.bizApiId_2.setInputProtected(true);
        }
        scrnMsg.glblCmpyCd.setInputProtected(true);

    }

    /**
     * Set bkup value for Reset event
     * @param scrnMsg ZZIL0030BMsg
     */
    public static void setResetValue(ZZIL0030BMsg scrnMsg) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.intfcId_BK, scrnMsg.intfcId_2.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.intfcId_BK, scrnMsg.intfcId_2.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.bizApiId_BK, scrnMsg.bizApiId_2.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.intfcIdDescTxt_BK, scrnMsg.intfcIdDescTxt_2.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.trgtBatJobNm_BK, scrnMsg.trgtBatJobNm_2.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.trgtBatJobDescTxt_BK, scrnMsg.trgtBatJobDescTxt_2.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.actvFlg_AB, scrnMsg.actvFlg_AF.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.frceQueueEnblFlg_FB, scrnMsg.frceQueueEnblFlg_FF.getValue());

    }

    /**
     * get bkup value for Reset event
     * @param scrnMsg ZZIL0030BMsg
     */
    public static void getResetValue(ZZIL0030BMsg scrnMsg) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.intfcId_2, scrnMsg.intfcId_BK.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.intfcId_2, scrnMsg.intfcId_BK.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.bizApiId_2, scrnMsg.bizApiId_BK.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.intfcIdDescTxt_2, scrnMsg.intfcIdDescTxt_BK.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.trgtBatJobNm_2, scrnMsg.trgtBatJobNm_BK.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.trgtBatJobDescTxt_2, scrnMsg.trgtBatJobDescTxt_BK.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.actvFlg_AF, scrnMsg.actvFlg_AB.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.frceQueueEnblFlg_FF, scrnMsg.frceQueueEnblFlg_FB.getValue());
    }

}
