/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3160;

import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3160.common.NLBL3160CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Distribution Coordinator Work Bench
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/13   CITS            S.Katsuma       Create          N/A
 * 2018/05/29   CITS            S.Katsuma       Update          QC#25232
 *</pre>
 */
public class NLBL3160Scrn00_Open_MngRMA extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NLBL3160BMsg scrnMsg = (NLBL3160BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3160BMsg scrnMsg = (NLBL3160BMsg) bMsg;
        NLBL3160CommonLogic.clearPopupParameter(scrnMsg);

        int index = getButtonSelectNumber();
        int i = 0;

        ZYPTableUtil.clear(scrnMsg.P);
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.A.no(index).soNum_A1);
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.setValidCount(i);

        Object[] params = new Object[i + 1];

        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {

            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }

        // START 2018/05/29 S.Katsuma [QC#25232,MOD]
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).xxSelFlg_CA) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).xxSelFlg_CA.getValue())) {
            params[i++] = scrnMsg.A.no(index).svcConfigMstrPk_A1;
        }
        // END 2018/05/29 S.Katsuma [QC#25232,MOD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(index));

        setArgForSubScreen(params);
        openMultiModeScreen();

    }
}
