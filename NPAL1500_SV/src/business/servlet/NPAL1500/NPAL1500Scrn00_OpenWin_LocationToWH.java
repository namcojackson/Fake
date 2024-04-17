/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

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
import static business.servlet.NPAL1500.constant.NPAL1500Constant.LOC_ROLE_TP_CDLIST;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CITS            N.Akaishi       Create          n/a
 * 2016/07/04   CITS            R.Shimamoto     Update          QC#8746
 * 2019/05/21   Fujitsu         T.Ogura         Update          QC#50138
 * 02/01/2020   CITS            K.Ogino         Update          QC#55313
 *</pre>
 */
public class NPAL1500Scrn00_OpenWin_LocationToWH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2019/05/21 T.Ogura [QC#50138,MOD]
//        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, "OpenWin_LocationToWH");
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm, scrnMsg.toBizAppLocChkKeyId);
//
//        NPAL1500CMsg bizMsg = new NPAL1500CMsg();
//        bizMsg.setBusinessID(BIZ_ID);
//        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
        return null;
        // END   2019/05/21 T.Ogura [QC#50138,MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        // START 2019/05/21 T.Ogura [QC#50138,DEL]
//        NPAL1500CMsg bizMsg = (NPAL1500CMsg) cMsg;
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // END   2019/05/21 T.Ogura [QC#50138,DEL]
        // QC#55313
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, "OpenWin_LocationToWH");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm, scrnMsg.toBizAppLocChkKeyId);
        
        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        // START 2019/05/21 T.Ogura [QC#50138,MOD]
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, LOC_ROLE_TP_CDLIST);
        // END   2019/05/21 T.Ogura [QC#50138,MOD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
        scrnMsg.xxPopPrm_P5.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.destRtlWhCd_DS);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.rtlWhNm_DS);
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PA, ZYPConstant.FLG_ON_Y);

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
