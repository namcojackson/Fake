/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.*;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7260_NMAL6050
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 * 2018/06/15   Fujitsu         M.Ishii         Update          N/A
 * 
 *</pre>
 */
public class NMAL7260_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // 2018/06/15 Add Start QC#22594
        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        String scrEvntNm = scrnMsg.xxScrEventNm.getValue();
        int idx = scrnMsg.xxCellIdx.getValueInt();
        if (OPENWIN_COAMDSETP.equals(scrEvntNm)) {
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_08);
        }else if(OPENWIN_COAPROD.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_09);
        }else if(OPENWIN_DSORDCATG.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_11);
        }else if(OPENWIN_DSORDTP.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_12);
        }else if(OPENWIN_LINEBIZTP.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_13);
        }else if(OPENWIN_COACHSOLD.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_55);
        }else if(OPENWIN_DSACCTCLSSOLD.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_56);
        }else if(OPENWIN_BILLTOACCTCHNL.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_17);
        }else if(OPENWIN_BILLTOACCTCLSS.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_18);
        }else if(OPENWIN_BRANCH.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_19);
        }else if(OPENWIN_CALLTYPE.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_20);
        }else if(OPENWIN_LINECATG.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_25);
        }else if(OPENWIN_MARKETMDLNM.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_27);
        }else if(OPENWIN_MODELSEG.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_28);
        }else if(OPENWIN_ORDERSRC.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_29);
        }else if(OPENWIN_PAYMENTTP.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_31);
        }else if(OPENWIN_RTNRSNCD.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_38);
        }else if(OPENWIN_SERVICELV.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_39);
        }else if(OPENWIN_SERVICEZONE.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_41);
        }else if(OPENWIN_SHIPTOACCT.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_42);
        }else if(OPENWIN_SPECIALHANDTP.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_44);
        }else if(OPENWIN_BIZUNIT.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_46);
        }else if(OPENWIN_FREIGHTTERM.equals(scrEvntNm)){
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_48);
        }
        // 2018/06/15 Add End QC#22594
    }
}
