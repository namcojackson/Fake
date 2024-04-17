/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0490;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;


import business.blap.NSAL0490.NSAL0490CMsg;
import business.servlet.NSAL0490.constant.NSAL0490Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSAL0490_NMAL6030 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;

        if (NSAL0490Constant.ITEM_NM_MDSE_CD.equals(scrnMsg.xxScrEventNm.getValue()) && !"CMN_Close".equals(getLastGuard())) {
            NSAL0490CMsg bizMsg = new NSAL0490CMsg();
            bizMsg.setBusinessID(NSAL0490Constant.BUSINESS_ID);
            bizMsg.setFunctionCode(NSAL0490Constant.FUNCTION_SEARCH);

            int selectIndex = getButtonSelectNumber();
            if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                scrnMsg.xxCellIdx_A.setValue(selectIndex);
            } else {
                scrnMsg.xxCellIdx_B.setValue(selectIndex);
            }

            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if (!"CMN_Close".equals(getLastGuard())) {
            NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;
            NSAL0490CMsg bizMsg = (NSAL0490CMsg) cMsg;

            if (bizMsg != null) {
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
            }

            if (NSAL0490Constant.ITEM_NM_MDSE_CD.equals(scrnMsg.xxScrEventNm.getValue())) {
                int selectIndex = getButtonSelectNumber();

                if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                    NSAL0490_ABMsg abMsg = scrnMsg.A.no(selectIndex);

                    if (NSAL0490Constant.DTL_TP_PRNT.equals(abMsg.xxFlgNm_A.getValue())) {
                        // 2015/10/07 CSA Y.Tsuchimoto Del Start
                        //ZYPEZDItemValueSetter.setValue(abMsg.prntMdseCd_A, scrnMsg.xxCondCd.getValue());
                        // 2015/10/07 CSA Y.Tsuchimoto Del End
                        scrnMsg.setFocusItem(abMsg.prntMdseCd_A);
                    } else {
                        // 2015/10/07 CSA Y.Tsuchimoto Del Start
                        //ZYPEZDItemValueSetter.setValue(abMsg.childMdseCd_A, scrnMsg.xxCondCd.getValue());
                        // 2015/10/07 CSA Y.Tsuchimoto Del End
                        scrnMsg.setFocusItem(abMsg.childMdseCd_A);
                    }
                } else {
                    NSAL0490_BBMsg bbMsg = scrnMsg.B.no(selectIndex);
                    // 2015/10/07 CSA Y.Tsuchimoto Del Start
                    //ZYPEZDItemValueSetter.setValue(bbMsg.mdseCd_B, scrnMsg.xxCondCd.getValue());
                    // 2015/10/07 CSA Y.Tsuchimoto Del End
                    scrnMsg.setFocusItem(bbMsg.mdseCd_B);
                }
            }
        }
    }
}
