/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.blap.NPAL1500.constant.NPAL1500Constant.NPAM0023E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.PCT_100;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BIZ_ID;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.TAB_DETAIL;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.ZZM9000E;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1500.NPAL1500CMsg;
import business.servlet.NPAL1500.common.NPAL1500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/06/20   CITS            K.Kameoka       Create          QC#18420
 * 2019/10/15   CITS            R.Shimamoto     Update          QC#53392
 *</pre>
 */
public class NPAL1500Scrn00_Get_LinePriceInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        int idx = this.getButtonSelectNumber();

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).poDtlDiscPct_A1)) {
            scrnMsg.A.no(idx).poDtlDiscPct_A1.setErrorInfo(1, ZZM9000E, new String[] {"Discount%" });
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).poDtlDiscPct_A1)) {
            // Discount : over 100
            if (PCT_100.compareTo(scrnMsg.A.no(idx).poDtlDiscPct_A1.getValue()) <= 0) {
                scrnMsg.A.no(idx).poDtlDiscPct_A1.setErrorInfo(1, NPAM0023E);
            }else if(BigDecimal.ZERO.compareTo(scrnMsg.A.no(idx).poDtlDiscPct_A1.getValue()) > 0){
                scrnMsg.A.no(idx).poDtlDiscPct_A1.setErrorInfo(1, NPAM0023E);
            }
        }
        // 2019/10/15 QC#53392 Add Start
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).poDtlDiscPrcAmt_A1);
        // Discount : under 0
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).poDtlDiscPrcAmt_A1)) {
        	if(BigDecimal.ZERO.compareTo(scrnMsg.A.no(idx).poDtlDiscPrcAmt_A1.getValue()) > 0){
            	scrnMsg.A.no(idx).poDtlDiscPrcAmt_A1.setErrorInfo(1, NPAM0023E);
            }
        }
        // 2019/10/15 QC#53392 Add Start
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).poDtlDiscPct_A1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        int idx = this.getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(idx));
        scrnMsg.xxTblNm_P1.clear();

        NPAL1500CMsg bizMsg = new NPAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        NPAL1500CMsg bizMsg = (NPAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int idx = scrnMsg.xxNum.getValueInt();

        scrnMsg.addCheckItem(scrnMsg.A.no(idx).aslUnitPrcAmt_A1);
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).poDtlDiscPct_A1);
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).xxChkBox_A1);
        scrnMsg.putErrorScreen();

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NPAL1500CommonLogic.ctrlScreenItem(this, scrnMsg, funcList);

        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
        scrnMsg.setFocusItem(scrnMsg.A.no(idx).poDtlDiscPct_A1);
    }
}
