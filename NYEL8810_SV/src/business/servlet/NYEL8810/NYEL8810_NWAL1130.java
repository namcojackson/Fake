/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8810;

import static business.servlet.NYEL8810.constant.NYEL8810Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NYEL8810.NYEL8810CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8810_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/01   Fujitsu         M.Yamada        Create          N/A
 * 2018/10/10   Fujitsu         Q10627          Update          QC#21078
 *</pre>
 */
public class NYEL8810_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        EZDCMsg ret = null;

        if (!"CMN_Close".equals(getLastGuard())) {
            if ("OpenWin_UserName".equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.usrId, scrnMsg.Q.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.usrNm //
// QC#28705 MOD START 2018/10/10
//                      , S21StringUtil.concatStrings(scrnMsg.Q.no(1).xxComnScrColValTxt.getValue(), ", ", scrnMsg.Q.no(2).xxComnScrColValTxt.getValue()));
                , S21StringUtil.concatStrings(scrnMsg.Q.no(2).xxComnScrColValTxt.getValue(), " ", scrnMsg.Q.no(1).xxComnScrColValTxt.getValue()));
// QC#28705 MOD END   2018/10/10

                NYEL8810CMsg bizMsg = new NYEL8810CMsg();
                bizMsg.setBusinessID(BIZ_ID);
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                ret = bizMsg;
            }
        }
        return ret;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        NYEL8810CMsg bizMsg = (NYEL8810CMsg) cMsg;
        
        if ("OpenWin_UserName".equals(scrnMsg.xxScrEventNm.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.usrId);
        } else if ("OpenWin_CmnBigAssignee".equals(scrnMsg.xxScrEventNm.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.xxWfAsgCd);
        }

        if (!"CMN_Close".equals(getLastGuard())) {
            if ("OpenWin_UserName".equals(scrnMsg.xxScrEventNm.getValue())) {
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
                scrnMsg.setFocusItem(scrnMsg.usrId);
            } else if ("OpenWin_CmnBigAssignee".equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxWfAsgCd, scrnMsg.Q.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxWfAsgNm //
// QC#28705 MOD START 2018/10/10
//                      , S21StringUtil.concatStrings(scrnMsg.Q.no(1).xxComnScrColValTxt.getValue(), ", ", scrnMsg.Q.no(2).xxComnScrColValTxt.getValue()));
                , S21StringUtil.concatStrings(scrnMsg.Q.no(2).xxComnScrColValTxt.getValue(), " ", scrnMsg.Q.no(1).xxComnScrColValTxt.getValue()));
// QC#28705 MOD END   2018/10/10
                scrnMsg.setFocusItem(scrnMsg.wfCmntTxt);
            }
        }
    }
}
