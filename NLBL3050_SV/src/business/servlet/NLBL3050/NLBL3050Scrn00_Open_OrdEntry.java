/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3050;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Distribution Coordinator Work Bench
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/21/2016   CSAI            Y.Imazu         Create          QC#2048
 *</pre>
 */
public class NLBL3050Scrn00_Open_OrdEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3050BMsg scrnMsg = (NLBL3050BMsg) bMsg;

        int index = getButtonSelectNumber();
        int i = 0;

        ZYPTableUtil.clear(scrnMsg.P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.A.no(index).trxHdrNum_A1);
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.setValidCount(i);

        Object[] params = new Object[i];

        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {

            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }

        params[2] = scrnMsg.xxTrxRefPk;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm, NLBL3050Bean.A);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(index));

        setArgForSubScreen(params);
        openMultiModeScreen();
    }
}
