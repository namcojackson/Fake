/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1740;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.PARAM_SIZE_11;

/**
 *<pre>
 * NWAL1740Scrn00_OpenWin_Mdse
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1740Scrn00_OpenWin_Mdse extends S21CommonHandler {

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

        NWAL1740BMsg scrnMsg = (NWAL1740BMsg) bMsg;

        int index = getButtonSelectNumber();

        // Clear Params
        ZYPTableUtil.clear(scrnMsg.P);

        // Set Params
        Object[] params = new Object[PARAM_SIZE_11];
        int i = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "COA_PROJ");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "COA_PROJ_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "COA_PROJ_DESC_TXT");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "COA_PROJ_SORT_NUM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Merchandise Type Popup");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Merchandise Type Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Merchandise Type Name");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Merchandise Type ID");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Merchandise Type Name");
        scrnMsg.P.setValidCount(i);
        params[9] = scrnMsg.A.no(index).coaMdseTpCd_A;
        params[10] = scrnMsg.A.no(index).coaProjNm_A;

        for (int j = 0; j < scrnMsg.P.getValidCount(); j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }

        scrnMsg.xxScrEventNm.setValue("NWAL1740Scrn00_OpenWin_Mdse");
        scrnMsg.xxCellIdx.setValue(index);
        setArgForSubScreen(params);
    }
}
