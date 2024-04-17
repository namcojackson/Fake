/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1340;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPAL1340.NPAL1340CMsg;
//import business.servlet.NPAL1340.constant.NPAL1340Constant;

import business.servlet.NPAL1340.constant.NPAL1340Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * NPAL1340 Drop Ship Release
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/29   CSAI            K.Lee           Create          N/A
 * 09/26/2017   CITS            T.Tokutomi      Update          QC#21191
 *</pre>
 */
public class NPAL1340_NMAL6050 extends S21CommonHandler implements NPAL1340Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1340BMsg scrnMsg = (NPAL1340BMsg) bMsg;

        if (EVENT_NM_OPENWIN_CARRIER.equals(scrnMsg.xxMntEventNm.getValue())) {
            if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.xxSelNum)) {
                    int selNum = Integer.parseInt(scrnMsg.xxSelNum.getValue());
                    if (selNum < 0) {
                        // Header
                        ZYPEZDItemValueSetter.setValue(scrnMsg.carrCd_H2, scrnMsg.P.no(9).xxPopPrm);
                        ZYPEZDItemValueSetter.setValue(scrnMsg.carrNm_H2, scrnMsg.P.no(10).xxPopPrm);
                    } else {
                        // Detail
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selNum).carrCd_A1, scrnMsg.P.no(9).xxPopPrm);
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selNum).carrNm_A1, scrnMsg.P.no(10).xxPopPrm);
                    }
                }
            }
        }
    }
}
