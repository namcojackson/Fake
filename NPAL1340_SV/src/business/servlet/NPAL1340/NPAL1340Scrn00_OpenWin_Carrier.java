/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1340;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;


import business.servlet.NPAL1340.constant.NPAL1340Constant;

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
public class NPAL1340Scrn00_OpenWin_Carrier extends S21CommonHandler implements NPAL1340Constant{

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1340BMsg scrnMsg = (NPAL1340BMsg) bMsg;
        int btnNum = getButtonSelectNumber();

        int i = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "OTBD_CARR_V");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "CARR_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "CARR_NM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "CARR_SORT_NUM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Carrier Popup");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Carrier Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Carrier Name");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Carrier Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Carrier Name");
        if (btnNum < 0) {
            // Header
            scrnMsg.carrCd_H2.clear(); // Hidden Parameter
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.carrCd_H2);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.carrNm_H2);
        } else {
            // Detail
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.A.no(btnNum).carrCd_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.A.no(btnNum).carrNm_A1);
        }
        scrnMsg.P.setValidCount(i);

        Object[] params = new Object[11];
        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, EVENT_NM_OPENWIN_CARRIER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSelNum, String.valueOf(btnNum));
        setArgForSubScreen(params);
    }
}
