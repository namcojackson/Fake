/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/31/2013   Fujitsu         S.Nakai         Create          N/A
 *</pre>
 */
public class NSAL0010_NMAL6061 extends S21CommonHandler {

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

//        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
//
//        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_11)) {
//
//            ZYPEZDItemValueSetter.setValue(scrnMsg.orgLayerNum_H1, new BigDecimal(5));
//            ZYPEZDItemValueSetter.setValue(scrnMsg.orgCd_H1, scrnMsg.xxPopPrm_11);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_H1, scrnMsg.xxPopPrm_12);
//
//        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_09)) {
//
//            ZYPEZDItemValueSetter.setValue(scrnMsg.orgLayerNum_H1, new BigDecimal(4));
//            ZYPEZDItemValueSetter.setValue(scrnMsg.orgCd_H1, scrnMsg.xxPopPrm_09);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_H1, scrnMsg.xxPopPrm_10);
//
//        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_07)) {
//
//            ZYPEZDItemValueSetter.setValue(scrnMsg.orgLayerNum_H1, new BigDecimal(3));
//            ZYPEZDItemValueSetter.setValue(scrnMsg.orgCd_H1, scrnMsg.xxPopPrm_07);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_H1, scrnMsg.xxPopPrm_08);
//
//        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_05)) {
//
//            ZYPEZDItemValueSetter.setValue(scrnMsg.orgLayerNum_H1, new BigDecimal(2));
//            ZYPEZDItemValueSetter.setValue(scrnMsg.orgCd_H1, scrnMsg.xxPopPrm_05);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_H1, scrnMsg.xxPopPrm_06);
//
//        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_03)) {
//
//            ZYPEZDItemValueSetter.setValue(scrnMsg.orgLayerNum_H1, new BigDecimal(1));
//            ZYPEZDItemValueSetter.setValue(scrnMsg.orgCd_H1, scrnMsg.xxPopPrm_03);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_H1, scrnMsg.xxPopPrm_04);
//        }
    }
}
