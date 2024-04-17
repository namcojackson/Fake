/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0020;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLGL0020.NLGL0020CMsg;
import business.servlet.NLGL0020.common.NLGL0020CommonLogic;
import business.servlet.NLGL0020.constant.NLGL0020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * PO Maintenance
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 05/23/2017     CITS            S.Endo            Create            RS#3173
 *</pre>
 */
public class NLGL0020_NWAL1130 extends S21CommonHandler implements NLGL0020Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;
//        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_01,scrnMsg.P.no(0).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_01,scrnMsg.P.no(1).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.whCd_02,scrnMsg.P.no(4).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.invtyOwnrCd_01,scrnMsg.P.no(3).xxComnScrColValTxt);

        NLGL0020CMsg bizMsg = new NLGL0020CMsg();
        if (!BTN_CMN_CLOSE_EVENT_NM.equals(getLastGuard())){
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode(FUNCTION_CODE_SEARCH);
            return bizMsg;
        }
        return null;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;
        if (!BTN_CMN_CLOSE_EVENT_NM.equals(getLastGuard())){
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_01,scrnMsg.P.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_01,scrnMsg.P.no(1).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.whCd_02,scrnMsg.P.no(4).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.invtyOwnrCd_01,scrnMsg.P.no(3).xxComnScrColValTxt);
        }
    }
    
}
