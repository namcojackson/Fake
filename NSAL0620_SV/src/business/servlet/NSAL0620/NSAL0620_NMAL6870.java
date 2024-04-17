/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0620;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0620.constant.NSAL0620Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/10/13   Hitachi         M.Komatsu       Create          QC#60078
 *</pre>
 */
public class NSAL0620_NMAL6870 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            if (i != 0) {
                sb.append(";");
            }
            sb.append(scrnMsg.R.no(i).xxComnScrColValTxt_0.getValue());
        }
        String srchtxt = sb.toString();

        if (NSAL0620Constant.FUNC_CD.OPEN_WIN_CONT_TYPE.getFuncCd().equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColValTxt_H1, srchtxt);
            scrnMsg.setFocusItem(scrnMsg.xxComnScrColValTxt_H1);
        } else if (NSAL0620Constant.FUNC_CD.OPEN_WIN_CONT_STATUS.getFuncCd().equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColValTxt_H2, srchtxt);
            scrnMsg.setFocusItem(scrnMsg.xxComnScrColValTxt_H2);
        } else if (NSAL0620Constant.FUNC_CD.OPEN_WIN_CONT_CATEGORY.getFuncCd().equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColValTxt_H3, srchtxt);
            scrnMsg.setFocusItem(scrnMsg.xxComnScrColValTxt_H3);
        } else if (NSAL0620Constant.FUNC_CD.OPEN_WIN_MACH_STATUS.getFuncCd().equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColValTxt_H4, srchtxt);
            scrnMsg.setFocusItem(scrnMsg.xxComnScrColValTxt_H4);
        } else if (NSAL0620Constant.FUNC_CD.OPEN_WIN_BASE_FREQ.getFuncCd().equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColValTxt_H5, srchtxt);
            scrnMsg.setFocusItem(scrnMsg.xxComnScrColValTxt_H5);
        } else if (NSAL0620Constant.FUNC_CD.OPEN_WIN_OVERAGE_FREQ.getFuncCd().equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColValTxt_H6, srchtxt);
            scrnMsg.setFocusItem(scrnMsg.xxComnScrColValTxt_H6);
        }
    }
}
