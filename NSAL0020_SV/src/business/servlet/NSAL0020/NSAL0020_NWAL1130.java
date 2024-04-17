/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * This class no use.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/19   Hitachi         Y.Tsuchimoto    Update          N/A(No Mark up comment)
 *</pre>
 */
public class NSAL0020_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;
//        if (!NSAL0010Constant.CLOSE.equals(getLastGuard())) {
//            int layer = 0;
//            if (NSAL0020Constant.LAYER_1.equals(scrnMsg.xxScrEventNm_P6.getValue())) {
//                layer = 1;
//            } else if (NSAL0020Constant.LAYER_2.equals(scrnMsg.xxScrEventNm_P6.getValue())) {
//                layer = 2;
//            } else if (NSAL0020Constant.LAYER_3.equals(scrnMsg.xxScrEventNm_P6.getValue())) {
//                layer = 3;
//            } else if (NSAL0020Constant.LAYER_4.equals(scrnMsg.xxScrEventNm_P6.getValue())) {
//                layer = 4;
//            } else if (NSAL0020Constant.LAYER_5.equals(scrnMsg.xxScrEventNm_P6.getValue())) {
//                layer = 5;
//            }
//            if (layer >= 5 && ZYPCommonFunc.hasValue(scrnMsg.P.no(8).xxComnScrColValTxt_P6)) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.fifthOrgCd, scrnMsg.P.no(8).xxComnScrColValTxt_P6);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.fifthOrgNm, scrnMsg.P.no(9).xxComnScrColValTxt_P6);
//            }
//            if (layer >= 4 && ZYPCommonFunc.hasValue(scrnMsg.P.no(6).xxComnScrColValTxt_P6)) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.frthOrgCd, scrnMsg.P.no(6).xxComnScrColValTxt_P6);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.frthOrgNm, scrnMsg.P.no(7).xxComnScrColValTxt_P6);
//            }
//            if (layer >= 3 && ZYPCommonFunc.hasValue(scrnMsg.P.no(4).xxComnScrColValTxt_P6)) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.thirdOrgCd, scrnMsg.P.no(4).xxComnScrColValTxt_P6);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.thirdOrgNm, scrnMsg.P.no(5).xxComnScrColValTxt_P6);
//            }
//            if (layer >= 2 && ZYPCommonFunc.hasValue(scrnMsg.P.no(2).xxComnScrColValTxt_P6)) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.scdOrgCd, scrnMsg.P.no(2).xxComnScrColValTxt_P6);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.scdOrgNm, scrnMsg.P.no(3).xxComnScrColValTxt_P6);
//            }
//            if (layer >= 1 && ZYPCommonFunc.hasValue(scrnMsg.P.no(0).xxComnScrColValTxt_P6)) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.firstOrgCd, scrnMsg.P.no(0).xxComnScrColValTxt_P6);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.firstOrgNm, scrnMsg.P.no(1).xxComnScrColValTxt_P6);
//            }
//        }
    }
}
