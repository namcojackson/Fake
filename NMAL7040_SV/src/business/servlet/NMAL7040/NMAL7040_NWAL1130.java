/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7040.constant.NMAL7040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Price List Filter Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   SRA             E.Inada         Create          N/A
 * 2018/11/17   Fujitsu         N.Sugiura       Create          QC#29147
 * 2018/11/27   Fujitsu         K.Kato          Update          QC#29319
 *</pre>
 */
public class NMAL7040_NWAL1130 extends S21CommonHandler implements NMAL7040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NMAL7040BMsg scrnMsg = (NMAL7040BMsg) bMsg;
        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }

        if ("OpenWin_Model".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdlNm, scrnMsg.R.no(1).xxComnScrColValTxt);

        } else if ("OpenWin_MtrPkg".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcMtrPkgNm, scrnMsg.R.no(2).xxComnScrColValTxt);

        } else if ("OpenWin_QualifierValueProd-1".equals(scrnMsg.xxScrEventNm.getValue()) || "OpenWin_QualifierValueProd-2".equals(scrnMsg.xxScrEventNm.getValue()) || "OpenWin_QualifierValueProd-3".equals(scrnMsg.xxScrEventNm.getValue())
                || "OpenWin_QualifierValueProd-4".equals(scrnMsg.xxScrEventNm.getValue()) || "OpenWin_QualifierValueProd-5".equals(scrnMsg.xxScrEventNm.getValue())) {
            // 2018/11/27 QC#29319 Mod Start
            //ZYPEZDItemValueSetter.setValue(scrnMsg.prcQlfyValTxt, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPrcQlfyValSrchTxt, scrnMsg.R.no(0).xxComnScrColValTxt);
            // 2018/11/27 QC#29319 Mod End

        } else if ("OpenWin_MtrLb".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.mtrLbNm, scrnMsg.R.no(1).xxComnScrColValTxt);

        } else if ("OpenWin_DsAcctCust".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm, scrnMsg.R.no(1).xxComnScrColValTxt);

        } else if ("OpenWin_SupplyPlan".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyAgmtPlnNm, scrnMsg.R.no(2).xxComnScrColValTxt);
        // 2018/11/17 QC#29147 Add Start
        } else if ("OpenWin_PrcListBand".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcListBandDescTxt, scrnMsg.R.no(1).xxComnScrColValTxt);
        // 2018/11/17 QC#29147 Add End
        }

        scrnMsg.xxScrEventNm.clear();
    }
}
