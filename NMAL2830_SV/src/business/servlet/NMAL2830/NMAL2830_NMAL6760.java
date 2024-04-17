/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2830;

import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_CLOSE_EVENT_NM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2830.constant.NMAL2830Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2830_NMAL6760
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2830_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2830BMsg scrnMsg = (NMAL2830BMsg) bMsg;

        if (BTN_CMN_CLOSE_EVENT_NM.equals(getLastGuard())) {
            return null;

        } else {

            String scrEventNm = scrnMsg.xxScrEventNm.getValue();

            if (NMAL2830Constant.LINK_PROS_NM_EVENT_NM.equals(scrEventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm, scrnMsg.xxPopPrm_02);
                scrnMsg.setFocusItem(scrnMsg.dsXrefAcctCd);

            } else if (NMAL2830Constant.LINK_ACCT_NUM_EVENT_NM.equals(scrEventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum, scrnMsg.xxPopPrm_01);
                scrnMsg.setFocusItem(scrnMsg.fill65Txt_RN);

            } else if (NMAL2830Constant.LINK_LOC_NUM_EVENT_NM.equals(scrEventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.locNum, scrnMsg.xxPopPrm_03);
                scrnMsg.setFocusItem(scrnMsg.orgNm_TN);

            } else if (NMAL2830Constant.LINK_MERGE_TO_EVENT_NM.equals(scrEventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).locNum_M, scrnMsg.xxPopPrm_03);
                scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).locNum_M);

            }
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
    }
}
