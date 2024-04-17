/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL1120.NFBL1120CMsg;
//import business.servlet.NFBL1120.constant.NFBL1120Constant;

import business.servlet.NFBL1120.constant.NFBL1120Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/09/13   Hitachi         K.Kojima        Update          QC#12725
 *</pre>
 */
public class NFBL1120_NWAL1130 extends S21CommonHandler implements NFBL1120Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_PrntVnd".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndCd, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_Vnd".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNm, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            // START 2016/09/13 K.Kojima [QC#12725,DEL]
            // } else if
            // ("OpenWin_ApMaintInvApvlLimit".equals(scrEventNm)) {
            // ZYPEZDItemValueSetter.setValue(scrnMsg.apvrUsrId,
            // scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            // ZYPEZDItemValueSetter.setValue(scrnMsg.apvrUsrNm,
            // scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            // END 2016/09/13 K.Kojima [QC#12725,DEL]
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_PrntVnd".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.prntVndCd);
            return;
        } else if ("OpenWin_Vnd".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.locNm);
            return;
            // START 2016/09/13 K.Kojima [QC#12725,DEL]
            // } else if
            // ("OpenWin_ApMaintInvApvlLimit".equals(scrEventNm)) {
            // scrnMsg.setFocusItem(scrnMsg.apvrUsrId);
            // return;
            // END 2016/09/13 K.Kojima [QC#12725,DEL]
        }

    }
}
