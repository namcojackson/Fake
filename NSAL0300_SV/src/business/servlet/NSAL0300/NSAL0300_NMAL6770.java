/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Hitachi         T.Kanasaka      Create          N/A
 * 2018/06/18   Hitachi         K.Kitachi       Update          QC#18591
 *</pre>
 */
public class NSAL0300_NMAL6770 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;

        // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//        String psnNm = "";
//        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_06)) {
//            psnNm = scrnMsg.xxPopPrm_06.getValue();
//        }
//        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_07)) {
//            if (ZYPCommonFunc.hasValue(psnNm)) {
//                psnNm += " " + scrnMsg.xxPopPrm_07.getValue();
//            } else {
//                psnNm = scrnMsg.xxPopPrm_07.getValue();
//            }
//        }
//        if (ZYPCommonFunc.hasValue(psnNm)) {
//            String scrEventNm = scrnMsg.xxScrEventNm.getValue();
//            int rowNum = getButtonSelectNumber();
//
//            if ("OpenWin_BillToContact".equals(scrEventNm)) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm_CP, psnNm);
//            } else if ("OpenWin_Contact_Base".equals(scrEventNm)) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(rowNum).xxPsnNm_BB, psnNm);
//            } else if ("OpenWin_Contact_Usage".equals(scrEventNm)) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(rowNum).xxPsnNm_BU, psnNm);
//            } else if ("OpenWin_Contact_Meter".equals(scrEventNm)) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(rowNum).xxPsnNm_BM, psnNm);
//            }
//        }
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int rowNum = getButtonSelectNumber();

        if ("OpenWin_BillToContact".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctacPsnFirstNm_CP, scrnMsg.xxPopPrm_06);
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctacPsnLastNm_CP, scrnMsg.xxPopPrm_07);
        } else if ("OpenWin_Contact_Base".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(rowNum).ctacPsnFirstNm_BB, scrnMsg.xxPopPrm_06);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(rowNum).ctacPsnLastNm_BB, scrnMsg.xxPopPrm_07);
        } else if ("OpenWin_Contact_Meter".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(rowNum).ctacPsnFirstNm_BM, scrnMsg.xxPopPrm_06);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(rowNum).ctacPsnLastNm_BM, scrnMsg.xxPopPrm_07);
        }
        // END 2018/06/18 K.Kitachi [QC#18591, MOD]
    }
}
