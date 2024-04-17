/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.BIZ_ID;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_0;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_1;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_10;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_11;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_2;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_3;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_4;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_5;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_6;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_7;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_8;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.IDX_9;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1500.NPAL1500CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            N Akaishi       Create          n/a
 *</pre>
 */
public class NPAL1500Scrn00_OpenWin_LocationToDetailSWH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, "OpenWin_LocationToDetailSWH");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm, scrnMsg.fromBizAppLocChkKeyId);

        NPAL1500CMsg bizMsg = new NPAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        NPAL1500CMsg bizMsg = (NPAL1500CMsg) cMsg;

        int selectIdx = getButtonSelectNumber();

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
        scrnMsg.xxPopPrm_P5.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.destRtlWhCd_DS);
        scrnMsg.xxPopPrm_P7.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.A.no(selectIdx).destRtlSwhCd_A1);
        scrnMsg.xxPopPrm_P9.clear();
        scrnMsg.xxPopPrm_PA.clear();
        Object[] params = new Object[IDX_11];
        params[IDX_0] = scrnMsg.xxPopPrm_P0;
        params[IDX_1] = scrnMsg.xxPopPrm_P1;
        params[IDX_2] = scrnMsg.xxPopPrm_P2;
        params[IDX_3] = scrnMsg.xxPopPrm_P3;
        params[IDX_4] = scrnMsg.xxPopPrm_P4;
        params[IDX_5] = scrnMsg.xxPopPrm_P5;
        params[IDX_6] = scrnMsg.xxPopPrm_P6;
        params[IDX_7] = scrnMsg.xxPopPrm_P7;
        params[IDX_8] = scrnMsg.xxPopPrm_P8;
        params[IDX_9] = scrnMsg.xxPopPrm_P9;
        params[IDX_10] = scrnMsg.xxPopPrm_PA;
        setArgForSubScreen(params);
    }
}
