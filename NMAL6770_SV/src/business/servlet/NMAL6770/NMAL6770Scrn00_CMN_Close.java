/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6770;

import static business.servlet.NMAL6770.constant.NMAL6770Constant.IDX_32;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6770.constant.NMAL6770Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/23   Fujitsu         M.Nakamura      Create          N/A
 * 2016/06/07   SRAA            Y.Chen          Update          QC#7781
 * 2017/08/28   Fujitsu         H.Nagashima     Update          QC#20780
 *</pre>
 */
public class NMAL6770Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // QC#7781
        NMAL6770BMsg scrnMsg = (NMAL6770BMsg) bMsg;

        if(NMAL6770Constant.MULT_SEL_MODE_CD.equals(scrnMsg.xxModeCd_H1.getValue())){
            Object[] params = (Object[]) getArgForSubScreen();
            if (params instanceof Object[]) {
                // Person Code
//                if (params.length > IDX_31 && params[IDX_31] != null) {
                if (params.length > IDX_32 && params[IDX_32] != null) {     // QC#20780 mod
//                    EZDBBigDecimalItem[] param = (EZDBBigDecimalItem[]) params[IDX_31];
                    EZDBBigDecimalItem[] param = (EZDBBigDecimalItem[]) params[IDX_32];     // QC#20780 mod
                    for(EZDBBigDecimalItem item : param){
                        item.clear();
                    }
                    for(int i=0; i<scrnMsg.B.getValidCount(); i++){
                        if (i < param.length) {
                            ZYPEZDItemValueSetter.setValue(param[i], scrnMsg.B.no(i).ctacPsnPk_B1.getValue());
                        }
                    }
                }
            }
        }
    }
}
