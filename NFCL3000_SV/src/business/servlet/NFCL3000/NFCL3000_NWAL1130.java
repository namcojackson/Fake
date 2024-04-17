/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3000.NFCL3000CMsg;
//import business.servlet.NFCL3000.constant.NFCL3000Constant;

import business.blap.NFCL3000.NFCL3000CMsg;
import business.servlet.NFCL3000.constant.NFCL3000Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         T.Tanaka        Create          N/A
 * 2016/07/14   Fujitsu         S.Fujita        Update          QC#11157
 *</pre>
 */
public class NFCL3000_NWAL1130 extends S21CommonHandler implements NFCL3000Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        NFCL3000CMsg bizMsg = new NFCL3000CMsg();
        bizMsg.setBusinessID("NFCL3000");
        bizMsg.setFunctionCode("20");

        if (!"CMN_Close".equals(getLastGuard())) {
            int idx = scrnMsg.xxCellIdx.getValueInt();
            
            if(scrnMsg.xxScrItem10Txt.getValue().equals("SalesRep")) {
                // START 2016/07/14 S.Fujita [QC#11157,MOD]
//                ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepTocCd_H1, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.psnNum_H1, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepTocNm_H1, scrnMsg.Z.no(1).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaBrNm_H1, scrnMsg.Z.no(3).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepTocCd_H1, scrnMsg.Z.no(5).xxComnScrColValTxt.getValue());
                // END   2016/07/14 S.Fujita [QC#11157,MOD]
            } else if(scrnMsg.xxScrItem10Txt.getValue().equals("Warehouse")) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipFromInvtyLocCd_H4, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_H4, scrnMsg.Z.no(1).xxComnScrColValTxt.getValue());
            } else if(scrnMsg.xxScrItem10Txt.getValue().equals("SalesRep_B")) {
                // START 2016/07/14 S.Fujita [QC#11157,MOD]
//                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).slsRepTocCd_B1, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).psnNum_B1, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).tocNm_B1, scrnMsg.Z.no(1).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).coaBrNm_B1, scrnMsg.Z.no(3).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).slsRepTocCd_B1, scrnMsg.Z.no(5).xxComnScrColValTxt.getValue());
                // END   2016/07/14 S.Fujita [QC#11157,MOD]
            } else if(scrnMsg.xxScrItem10Txt.getValue().equals("WH_D")) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(idx).shipFromInvtyLocCd_D1, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
            }
        }
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        NFCL3000CMsg bizMsg  = (NFCL3000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if(scrnMsg.xxScrItem10Txt.getValue().equals("SalesRep")) {
            scrnMsg.setFocusItem(scrnMsg.psnNum_H1);
        }

    }
}
