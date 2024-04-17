/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1090;

import static business.servlet.NPAL1090.constant.NPAL1090Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.TBL_NM_FOR_CARR;
import static business.servlet.NPAL1090.constant.NPAL1090Constant.TBL_NM_FOR_TECH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request List
 * Function Name : Return Action from NMAL6050(General Purpose Popup)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/09/2015   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NPAL1090_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (TBL_NM_FOR_CARR.equals(scrnMsg.xxTblNm_P1.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.carrCd_AC, scrnMsg.xxCondCd_P1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.carrNm_H1, scrnMsg.xxCondNm_P1);
                scrnMsg.setFocusItem(scrnMsg.prchReqNum_H1);
            } else if (TBL_NM_FOR_TECH.equals(scrnMsg.xxTblNm_P1.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rqstTechTocCd_AC, scrnMsg.xxCondCd_P1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.techNm_H1, scrnMsg.xxCondNm_P1);
                scrnMsg.setFocusItem(scrnMsg.rtlWhCd_H1);
            } else {
                scrnMsg.setFocusItem(scrnMsg.prchReqNum_H1);
            }
        }
    }
}
