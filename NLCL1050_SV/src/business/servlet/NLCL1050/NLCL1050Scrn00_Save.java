/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1050;

import static business.servlet.NLCL1050.constant.NLCL1050Constant.BIZ_ID;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.CYCLE_COUNT_DAYS;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.NLCM0065E;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.VALUE_ASSIGNMENT;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1050.NLCL1050CMsg;
import business.servlet.NLCL1050.common.NLCL1050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/08   CITS            N.Akaishi       Create          N/A
 *</pre>
 */
public class NLCL1050Scrn00_Save extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1050BMsg scrnMsg = (NLCL1050BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            //QC:14345
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cycleCntFreqDaysAot)) {
                if (BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).cycleCntFreqDaysAot.getValue()) >= 0) {
                    scrnMsg.A.no(i).cycleCntFreqDaysAot.setErrorInfo(1, NLCM0065E, new String[] {CYCLE_COUNT_DAYS });
                }
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).valAsgPct)) {

                if (BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).valAsgPct.getValue()) >= 0) {
                    scrnMsg.A.no(i).valAsgPct.setErrorInfo(1, NLCM0065E, new String[] {VALUE_ASSIGNMENT });
                }
            }
        }

        NLCL1050CommonLogic.addCheckItem(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1050BMsg scrnMsg = (NLCL1050BMsg) bMsg;

        NLCL1050CMsg bizMsg = new NLCL1050CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1050BMsg scrnMsg = (NLCL1050BMsg) bMsg;
        NLCL1050CMsg bizMsg  = (NLCL1050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);

        NLCL1050CommonLogic.commonInit(scrnMsg);

        NLCL1050CommonLogic.addCheckItem(scrnMsg);
        NLCL1050CommonLogic.ctrlScreenItem(this, scrnMsg, funcList);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.abcAnlsClsNm);
    }
}
