/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2300;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2300.common.NWAL2300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/06   Fujitsu         T.Aoi           Create          N/A
 * 2018/04/24   Fujitsu         T.Aoi           Update          QC#22122-1
 *</pre>
 */
public class NWAL2300Scrn00_Apply_Summary extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2300BMsg scrnMsg = (NWAL2300BMsg) bMsg;

        NWAL2300CommonLogic.errorCheckForPartialCreditRebill(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2300BMsg scrnMsg = (NWAL2300BMsg) bMsg;

        Map<String, String> invNumMap = new HashMap<String, String>();
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).xxChkBox_B1) && ZYPCommonFunc.hasValue(scrnMsg.B.no(i).xxTpCd_B1)) {
                invNumMap.put(scrnMsg.B.no(i).invNum_B1.getValue(), scrnMsg.B.no(i).xxTpCd_B1.getValue());
            }
        }

        String tpCd = null;
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            tpCd = invNumMap.get(scrnMsg.C.no(i).invNum_C1.getValue());
            if (ZYPCommonFunc.hasValue(tpCd)) {
                if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(scrnMsg.C.no(i).dsOrdLineDrctnCd_C1.getValue()) && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.C.no(i).openFlg_C1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(i).xxTpCd_C1, tpCd);
                }
            // 2018/04/24 QC#22122-1 Add Start
            } else {
                scrnMsg.C.no(i).xxTpCd_C1.clear();
            }
            // 2018/04/24 QC#22122-1 Add End
        }
    }
}
