/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/09/15   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
package business.servlet.NWAL0300;

import java.io.Serializable;

import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL0300.constant.NWAL0300Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NWAL0300Scrn00_CMN_Close
 */
public class NWAL0300Scrn00_CMN_Close extends S21CommonHandler implements NWAL0300Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL0300BMsg scrnMsg = (NWAL0300BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.rvwMemoTxt);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL0300BMsg scrnMsg = (NWAL0300BMsg) bMsg;
        Serializable args = getArgForSubScreen();
        if (args instanceof Object[]) {
            Object[] params = (Object[]) args;
            if (scrnMsg.rvwMemoTxt_H1.getValue().equals(scrnMsg.rvwMemoTxt.getValue())) {
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[PRM_IDX_RVW_PSN_CD], scrnMsg.rvwPsnCd_H1);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[PRM_IDX_RVW_PSN_NM], scrnMsg.rvwPsnNm_H1);
                ZYPEZDItemValueSetter.setValue((EZDBDateItem)   params[PRM_IDX_RVW_DT], scrnMsg.rvwDt_H1);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[PRM_IDX_RVW_MEMO_TXT], scrnMsg.rvwMemoTxt_H1);
            } else {
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[PRM_IDX_RVW_PSN_CD], scrnMsg.rvwPsnCd_H2);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[PRM_IDX_RVW_PSN_NM], scrnMsg.rvwPsnNm_H2);
                ZYPEZDItemValueSetter.setValue((EZDBDateItem)   params[PRM_IDX_RVW_DT], scrnMsg.rvwDt_H2);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[PRM_IDX_RVW_MEMO_TXT], scrnMsg.rvwMemoTxt);
            }
        }
    }

}
