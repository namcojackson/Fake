/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6730.NMAL6730CMsg;
import business.servlet.NMAL6730.constant.NMAL6730Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Fujitsu         N.Sugiura       Create          N/A
 * 2016/06/08   SRAA            Y.Chen          Update          QC#7781
 *</pre>
 */
public class NMAL6730_NMAL6770 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // QC#7781
        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
        StringBuffer ctacPsnPkList = new StringBuffer();
        for (int i = 0; i < scrnMsg.Q.length(); i++) {
            BigDecimal ctacPsnPk = scrnMsg.Q.no(i).ctacPsnPk_Q2.getValue();
            if (ZYPCommonFunc.hasValue(ctacPsnPk)) {
                if (ctacPsnPkList.length() > 0) {
                    ctacPsnPkList.append(NMAL6730Constant.CHAR_COMMA);
                }
                ctacPsnPkList.append(ctacPsnPk.toPlainString());
            } else {
                break;
            }
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).xxGenlFldAreaTxt_A2, ctacPsnPkList.toString());

        NMAL6730CMsg bizMsg = new NMAL6730CMsg();
        bizMsg.setBusinessID("NMAL6730");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

// QC#7781
//        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
//        int selectIdx = getButtonSelectNumber();
//        scrnMsg.xxCellIdx.setValue(selectIdx);
//
//        String firstNm = scrnMsg.xxPopPrm_P6.getValue();
//        String lastNm = scrnMsg.xxPopPrm_P7.getValue();
//        if (ZYPCommonFunc.hasValue(firstNm)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIdx).xxPsnNm_A2, firstNm.concat(" ").concat(lastNm).trim());
//        }
//        scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).ctacPsnPk_A1);
        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
        NMAL6730CMsg bizMsg  = (NMAL6730CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).xxGenlFldAreaTxt_A2);

    }
}
