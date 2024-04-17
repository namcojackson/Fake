/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0480;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/30   Hitachi         J.Kim           Create          N/A
 * 2017/02/01   Hitachi         K.Kitachi       Update          QC#16629
 *</pre>
 */
public class NSBL0480_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0480BMsg scrnMsg = (NSBL0480BMsg) bMsg;

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        int index = getButtonSelectNumber();
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if ("OpenWin_R_Resource".equals(scrEventNm) || "OpenWin_Resource".equals(scrEventNm)) {

            String psnCd = scrnMsg.C.no(0).xxComnScrColValTxt.getValue();
            String fullPsnNm = scrnMsg.C.no(1).xxComnScrColValTxt.getValue();
            String fullPsnType = scrnMsg.C.no(2).xxComnScrColValTxt.getValue();

            if (index < 0) {
                setValue(scrnMsg.techCd_A1, psnCd);
                setValue(scrnMsg.fullPsnNm_A3, fullPsnNm);
                setValue(scrnMsg.psnTpDescTxt, fullPsnType);
                scrnMsg.setFocusItem(scrnMsg.techCd_A1);
            } else {
                setValue(scrnMsg.B.no(index).techCd_B, psnCd);
                setValue(scrnMsg.B.no(index).fullPsnNm_B, fullPsnNm);
                scrnMsg.setFocusItem(scrnMsg.B.no(index).techCd_B);
            }
        } else if ("OpenWin_R_Access".equals(scrEventNm) || "OpenWin_Access".equals(scrEventNm)) {

            String psnCd = scrnMsg.C.no(0).xxComnScrColValTxt.getValue();
            String fullPsnNm = scrnMsg.C.no(1).xxComnScrColValTxt.getValue();
            // START 2017/02/01 K.Kitachi [QC#16629, ADD]
            String svcPmitLvlTpCd = scrnMsg.C.no(4).xxComnScrColValTxt.getValue();
            String svcPmitLvlValTxt = scrnMsg.C.no(3).xxComnScrColValTxt.getValue();
            // END 2017/02/01 K.Kitachi [QC#16629, ADD]

            if (index < 0) {
                setValue(scrnMsg.svcAccsPmitNum, psnCd);
                setValue(scrnMsg.svcAccsPmitDescTxt, fullPsnNm);
                // START 2017/02/01 K.Kitachi [QC#16629, ADD]
                setValue(scrnMsg.svcPmitLvlTpCd, svcPmitLvlTpCd);
                setValue(scrnMsg.xxScrItem30Txt, svcPmitLvlValTxt);
                // END 2017/02/01 K.Kitachi [QC#16629, ADD]
                scrnMsg.setFocusItem(scrnMsg.techCd_B1);
            } else {
                setValue(scrnMsg.A.no(index).svcAccsPmitNum_A, psnCd);
                setValue(scrnMsg.A.no(index).svcAccsPmitDescTxt_A, fullPsnNm);
                // START 2017/02/01 K.Kitachi [QC#16629, ADD]
                setValue(scrnMsg.A.no(index).svcPmitLvlTpCd_A, svcPmitLvlTpCd);
                setValue(scrnMsg.A.no(index).xxScrItem30Txt_A, svcPmitLvlValTxt);
                // END 2017/02/01 K.Kitachi [QC#16629, ADD]
                scrnMsg.setFocusItem(scrnMsg.A.no(index).svcAccsPmitNum_A);
            }
        }
    }
}
