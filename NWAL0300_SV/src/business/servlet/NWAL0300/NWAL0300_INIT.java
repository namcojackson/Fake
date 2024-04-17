/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/09/15   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
package business.servlet.NWAL0300;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL0300.NWAL0300CMsg;
import business.servlet.NWAL0300.constant.NWAL0300Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * NWAL0300_INIT
 */
public class NWAL0300_INIT extends S21INITCommonHandler implements NWAL0300Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL0300BMsg scrnMsg = (NWAL0300BMsg) bMsg;
        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            ZYPEZDItemValueSetter.setValue(scrnMsg.rvwPsnCd,      (EZDBStringItem) params[PRM_IDX_RVW_PSN_CD]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rvwPsnCd_H1,   (EZDBStringItem) params[PRM_IDX_RVW_PSN_CD]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rvwPsnNm,      (EZDBStringItem) params[PRM_IDX_RVW_PSN_NM]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rvwPsnNm_H1,   (EZDBStringItem) params[PRM_IDX_RVW_PSN_NM]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rvwDt,         (EZDBDateItem) params[PRM_IDX_RVW_DT]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rvwDt_H1,      (EZDBDateItem) params[PRM_IDX_RVW_DT]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rvwMemoTxt,    (EZDBStringItem) params[PRM_IDX_RVW_MEMO_TXT]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rvwMemoTxt_H1, (EZDBStringItem) params[PRM_IDX_RVW_MEMO_TXT]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.hldPk,         (EZDBBigDecimalItem) params[PRM_IDX_HLD_PK]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.sellToCustCd,  (EZDBStringItem) params[PRM_IDX_SELL_TO_CUST_CD]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd,  (EZDBStringItem) params[PRM_IDX_BILL_TO_CUST_CD]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd,  (EZDBStringItem) params[PRM_IDX_SHIP_TO_CUST_CD]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd,        (EZDBStringItem) params[PRM_IDX_MDSE_CD]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxReadOnlyFlg, (EZDBStringItem) params[PRM_IDX_XX_READ_ONLY_FLG]);
        }

        NWAL0300CMsg bizMsg = new NWAL0300CMsg();
        bizMsg.setBusinessID("NWAL0300");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL0300BMsg scrnMsg = (NWAL0300BMsg) bMsg;
        NWAL0300CMsg bizMsg = (NWAL0300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.rvwPsnNm.setInputProtected(true);
        scrnMsg.xxLocAddrNm_H1.setInputProtected(true);
        scrnMsg.xxLocAddrNm_H2.setInputProtected(true);
        scrnMsg.xxLocAddrNm_H3.setInputProtected(true);
        scrnMsg.hldDtlTxt.setInputProtected(true);

        this.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxReadOnlyFlg.getValue())) {
            scrnMsg.rvwMemoTxt.setInputProtected(true);
            this.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        } else {
            this.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL0300BMsg scrnMsg = (NWAL0300BMsg) bMsg;
        scrnMsg.rvwMemoTxt.setNameForMessage("Review Comments");

    }
}
