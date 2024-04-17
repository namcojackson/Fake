/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0200;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSBL0200.constant.NSBL0200Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/05/2013   SRA             E.Inada         Create          N/A
 * 2013/08/30   WDS Team        K.Aratani          Update          QC1457
 *</pre>
 */
public class NSBL0200_NWAL1130 extends S21CommonHandler {

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

        NSBL0200BMsg scrnMsg = (NSBL0200BMsg) bMsg;
        if (!NSBL0200Constant.CLOSE.equals(getLastGuard())) {
            for (int i = 0; i < scrnMsg.P.length(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.P.no(i).xxSelFlg_P1.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt_P1)) {
                    //QC1457
                    ZYPEZDItemValueSetter.setValue(scrnMsg.orgCd_TC, scrnMsg.P.no(i).xxComnScrColValTxt_P1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_TC, scrnMsg.P.no(i + 1).xxComnScrColValTxt_P1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.orgLayerNum_TC, new BigDecimal((i + 2) / 2));
                    break;
                }
            }
        }

    }
}
