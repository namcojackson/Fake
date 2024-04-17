/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0720;

import static business.servlet.NSAL0720.constant.NSAL0720Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0720.NSAL0720CMsg;
import business.servlet.NSAL0720.NSAL0720BMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Hitachi         T.Tomita        Create          QC#1029
 *</pre>
 */
public class NSAL0720_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // mod start 2016/07/27 CSA Defect#12001
        int rowNum = getButtonSelectNumber();
        if (!"CMN_Close".equals(getLastGuard())) {
            if (rowNum == -1) {
                NSAL0720BMsg scrnMsg = (NSAL0720BMsg) bMsg;
                NSAL0720CMsg bizMsg = new NSAL0720CMsg();
                bizMsg.setBusinessID(BUSINESS_ID);
                bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
                return bizMsg;
            }
        }
        return null;
        // mod end 2016/07/27 CSA Defect#12001
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0720BMsg scrnMsg = (NSAL0720BMsg) bMsg;
        NSAL0720CMsg bizMsg = (NSAL0720CMsg) cMsg;

        int rowNum = getButtonSelectNumber();
        StringBuilder billToCustLocAddr = new StringBuilder();

        //firstLineAddr
        if (hasValue(scrnMsg.xxPopPrm_04)) {
            billToCustLocAddr.append(scrnMsg.xxPopPrm_04.getValue());
        }

        //scdLineAddr
        if (hasValue(scrnMsg.xxPopPrm_17)) {
            billToCustLocAddr.append(scrnMsg.xxPopPrm_17.getValue());
            billToCustLocAddr.append(SPACE);
        }

        //ctyAddr
        if (hasValue(scrnMsg.xxPopPrm_05)) {
            billToCustLocAddr.append(scrnMsg.xxPopPrm_05.getValue());
            billToCustLocAddr.append(SPACE);
        }

        //postCd
        if (hasValue(scrnMsg.xxPopPrm_07)) {
            billToCustLocAddr.append(scrnMsg.xxPopPrm_07.getValue());
        }

        String billToCustLocAddrStr = billToCustLocAddr.toString().trim();

        if (!"CMN_Close".equals(getLastGuard())) {
            if (rowNum == -1) {
                // mod start 2016/07/27 CSA Defect#12001
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
                // mod end 2016/07/27 CSA Defect#12001
            } else {
                setValue(scrnMsg.A.no(rowNum).billToCustLocAddr_A2, billToCustLocAddrStr);
            }
        }
    }
}
