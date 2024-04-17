/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLAL2030.NLAL2030CMsg;
//import business.servlet.NLAL2030.constant.NLAL2030Constant;

import business.servlet.NLAL2030.constant.NLAL2030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/07/2018   CITS            T.Tokutomi      Create          QC#18461-Sol#085
 * 03/12/2018   Fujitsu         S.Ohki          Update          QC#29461
 *</pre>
 */
public class NLAL2030Scrn00_Open_Win_WhFromRwsList extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.P);
        int index = getButtonSelectNumber();
        int i = 0;

        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        // START 2018/12/03 S.Ohki [QC#29461,MOD]
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, NLAL2030Constant.LOC_ROLE_TP_FOR_WH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.srchOptTxt);
        // END 2018/12/03 S.Ohki [QC#29461,MOD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, LOC_TP.WAREHOUSE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.B.no(index).rtlWhCd_B1);
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        // START 2018/12/03 S.Ohki [QC#29461,MOD]
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, ZYPConstant.FLG_OFF_N);
        // END 2018/12/03 S.Ohki [QC#29461,MOD]
        scrnMsg.P.setValidCount(i);

        Object[] params = new Object[11];

        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {

            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, NLAL2030Constant.EVENT_NM_OPENWIN_WH_RWSLIST);
        setArgForSubScreen(params);

    }
}
