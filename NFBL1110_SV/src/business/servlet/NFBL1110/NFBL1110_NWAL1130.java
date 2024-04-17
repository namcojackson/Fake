/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NFBL1110.constant.NFBL1110Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Invoice Maintenance Batch Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   CUSA            Y.Aikawa        Create          N/A
 * 2016/09/29   Hitachi         K.Kojima        Update          QC#13442
 * 2017/01/20   Hitachi         E.Kameishi      Update          QC#17156
 * </pre>
 */
public class NFBL1110_NWAL1130 extends S21CommonHandler implements NFBL1110Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        // START 2017/01/20 E.Kameishi [QC#17156,MOD]
        if ("OpenWin_PrntVnd".equals(scrEventNm) || "OpenWin_Vnd".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndCd_IH, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm_IH, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.apVndCd_HD, scrnMsg.Z.no(IDX_2).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.vndSiteNm_IH, scrnMsg.Z.no(IDX_3).xxComnScrColValTxt.getValue());
        //} else if ("OpenWin_Vnd".equals(scrEventNm)) {
        //    ZYPEZDItemValueSetter.setValue(scrnMsg.vndSiteNm_IH, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
        //    ZYPEZDItemValueSetter.setValue(scrnMsg.apVndCd_HD, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        // END 2017/01/20 E.Kameishi [QC#17156,MOD]
            // START 2016/09/29 K.Kojima [QC#13442,ADD]
        } else if ("OpenWin_Serial".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndCd_IH, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm_IH, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.vndSiteNm_IH, scrnMsg.Z.no(IDX_2).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.apVndCd_HD, scrnMsg.Z.no(IDX_3).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_AD, scrnMsg.Z.no(IDX_4).xxComnScrColValTxt.getValue());
            // END 2016/09/29 K.Kojima [QC#13442,ADD]
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_PrntVnd".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.prntVndCd_IH);
            return;
        } else if ("OpenWin_Vnd".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.vndSiteNm_IH);
            return;
            // START 2016/09/29 K.Kojima [QC#13442,ADD]
        } else if ("OpenWin_Serial".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.serNum_AD);
            return;
            // END 2016/09/29 K.Kojima [QC#13442,ADD]
        }

    }
}
