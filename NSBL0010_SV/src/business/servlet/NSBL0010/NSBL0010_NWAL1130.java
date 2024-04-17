/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0010;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSBL0010.constant.NSBL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/05/2013   SRA             E.Inada         Create          N/A
 * 2016/10/19   Hitachi         N.Arai          Update          QC#13901
 * 2017/01/05   Hitachi         N.Arai          Update          QC#13901-2
 *</pre>
 */
public class NSBL0010_NWAL1130 extends S21CommonHandler implements NSBL0010Constant {

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

// START 2017/01/05 N.Arai [QC#13901-2, MOD]
// START 2016/10/19 N.Arai [QC#13901, MOD]
        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;
        if (!CLOSE.equals(getLastGuard())) {
//            for (int i = 0; i < scrnMsg.P.length(); i++) {
//                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.P.no(i).xxSelFlg_P1.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt_P1)) {
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.orgCd, scrnMsg.P.no(i).xxComnScrColValTxt_P1);
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm, scrnMsg.P.no(i + 1).xxComnScrColValTxt_P1);
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.orgLayerNum, new BigDecimal((i + 2) / 2));
//                    break;
//                }
//            }
            if (OPENWIN_TECHNICIAN.equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.techCd, scrnMsg.Z.no(0).xxComnScrColValTxt);
            } else if (OPENWIN_BRANCH.equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.svcContrBrCd, scrnMsg.Z.no(0).xxComnScrColValTxt);
                setValue(scrnMsg.svcContrBrDescTxt, scrnMsg.Z.no(1).xxComnScrColValTxt);
            }
        }
// END 2016/10/19 N.Arai [QC#13901, MOD]
// END 2017/01/05 N.Arai [QC#13901-2, MOD]
   }
}
