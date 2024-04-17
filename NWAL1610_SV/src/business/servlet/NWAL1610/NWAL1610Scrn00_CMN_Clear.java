/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/02/20   Fujitsu         W.Honda         Update          S21 CSA QC#1130
 * 2016/06/01   Fujitsu         T.Murai         Update          S21 CSA QC#9189
 * 2019/12/25   Fujitsu         S.Kosaka        Update          QC#54999(Regression bug fix)
 *</pre>
 */
public class NWAL1610Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL1610BMsg scrnMsg = (NWAL1610BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustLocCd, scrnMsg.shipToCustLocCd_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustLocCd, scrnMsg.billToCustLocCd_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.ordQty, scrnMsg.ordQty_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdLineCatgCd, scrnMsg.dsOrdLineCatgCd_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.ordLineSrcCd, scrnMsg.ordLineSrcCd_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.rtlWhCd_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, scrnMsg.rtlWhNm_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnRsnCd, scrnMsg.rtrnRsnCd_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd, scrnMsg.rtlSwhCd_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm, scrnMsg.rtlSwhNm_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem20Txt, scrnMsg.xxScrItem20Txt_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgNm, scrnMsg.prcCatgNm_BK);
        // Mod Start #1130 02/20/2016
        // ZYPEZDItemValueSetter.setValue(scrnMsg.flPrcListDescTxt, scrnMsg.flPrcListDescTxt_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.flPrcListNm, scrnMsg.flPrcListNm_BK);
        // Mod End #1130 02/20/2016
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcBaseDt, scrnMsg.prcBaseDt_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.hddRmvCd, scrnMsg.hddRmvCd_BK);
        // Add Start #2096 02/28/2016
        ZYPEZDItemValueSetter.setValue(scrnMsg.rddDt, scrnMsg.rddDt_BK);
        // Add End #2096 02/28/2016
        // Add Start #9189 06/01/2016
        ZYPEZDItemValueSetter.setValue(scrnMsg.rqstPickUpDt, scrnMsg.rqstPickUpDt_BK);
        // Add End #9189 06/01/2016
        // 2019/12/25 QC#54999 Mod Start(Regression bug fix)
        ZYPEZDItemValueSetter.setValue(scrnMsg.csmpContrNum, scrnMsg.csmpContrNum_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dlrRefNum, scrnMsg.dlrRefNum_BK);
        // 2019/12/25 QC#54999 Mod End(Regression bug fix)
    }
}
