/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3060.NLBL3060CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   CSAI            K.Lee           Create          
 * 2023/10/18   Hitachi         Y.Ogura         Update          QC#61793
 *</pre>
 */
public class NLBL3060_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        String rtnVal = null;

        if (!"CMN_Close".equals(getLastGuard())) {
            if ("Link_Person".equals(scrEventNm)) {
                rtnVal = scrnMsg.P.no(0).xxComnScrColValTxt.getValue();
                scrnMsg.schdCoordPsnCd.setValue(rtnVal);
                scrnMsg.setFocusItem(scrnMsg.schdCoordPsnCd);
            } else if ("Link_RtlWh".equals(scrEventNm)) {
                rtnVal = scrnMsg.P.no(0).xxComnScrColValTxt.getValue();
                scrnMsg.rtlWhCd.setValue(rtnVal);
                scrnMsg.setFocusItem(scrnMsg.rtlWhCd);
            } else if ("Btn_DtlPerson".equals(scrEventNm)) {
                rtnVal = scrnMsg.P.no(0).xxComnScrColValTxt.getValue();
                scrnMsg.A.no(scrnMsg.xxNum.getValueInt()).schdCoordPsnCd_A1.setValue(rtnVal);
                scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxNum.getValueInt()).schdCoordPsnCd_A1);
            } else if ("Btn_DtlRtlWh".equals(scrEventNm)) {
                rtnVal = scrnMsg.P.no(0).xxComnScrColValTxt.getValue();
                scrnMsg.A.no(scrnMsg.xxNum.getValueInt()).rtlWhCd_A1.setValue(rtnVal);
                scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxNum.getValueInt()).rtlWhCd_A1);
            }
            // START 2023/10/18 Y.Ogura [QC#61793, ADD]
            else if ("Btn_GroupName".equals(scrEventNm)) {
                rtnVal = scrnMsg.P.no(0).xxComnScrColValTxt.getValue();
                scrnMsg.A.no(scrnMsg.xxNum.getValueInt()).physWhCd_A1.setValue(rtnVal);
                scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxNum.getValueInt()).physWhCd_A1);
            }
             else if ("Link_GroupName".equals(scrEventNm)) {
                rtnVal = scrnMsg.P.no(0).xxComnScrColValTxt.getValue();
                scrnMsg.physWhCd_HD.setValue(rtnVal);
                scrnMsg.setFocusItem(scrnMsg.physWhCd_HD);
             }
            // END 2023/10/18 Y.Ogura [QC#61793, ADD]
        }

        NLBL3060CMsg bizMsg = new NLBL3060CMsg();
        bizMsg.setBusinessID("NLBL3060");
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }


    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;
        NLBL3060CMsg bizMsg = (NLBL3060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
