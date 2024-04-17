/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDCommonConst;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2610.NFCL2610CMsg;
import business.servlet.NFCL2610.common.NFCL2610CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         J.Kim           Create          N/A
 * 2017/11/09   Fujitsu         M.Ohno          Update          QC#20587
 * 2018/02/06   Hitachi         T.Tsuchida      Update          QC#23990
 * 2018/03/02   Hitachi         E.Kameishi      Update          QC#22765
 * 2018/07/11   Hitachi         Y.Takeno        Update          QC#26989
 *</pre>
 */
public class NFCL2610Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2610BMsg scrnMsg = (NFCL2610BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.arRfRqstPk);
        scrnMsg.addCheckItem(scrnMsg.billToCustAcctCd);
        scrnMsg.addCheckItem(scrnMsg.billToCustLocCd);
        // START 2018/07/11 [QC#26989, ADD]
        scrnMsg.addCheckItem(scrnMsg.xxQueryFltrTxt);
        // END   2018/07/11 [QC#26989, ADD]

        // 2017/11/09 QC#20587 add start
        if (scrnMsg.billToCustAcctCd.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("billToCustAcctCd");

            // START 2018/02/06 T.Tsuchida [QC#23990,MOD]
            //if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
            //    scrnMsg.billToCustAcctCd.clearErrorInfo();
            //}
            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU
                    && ZYPCommonFunc.hasValue(scrnMsg.billToCustLocCd)) {
                scrnMsg.billToCustAcctCd.clearErrorInfo();
            }
            // END 2018/02/06 T.Tsuchida [QC#23990,MOD]
        }
        // 2017/11/09 QC#20587 add end
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2610BMsg scrnMsg = (NFCL2610BMsg) bMsg;
        NFCL2610CMsg bizMsg = NFCL2610CommonLogic.setRequestData_SearchCommon();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2610BMsg scrnMsg = (NFCL2610BMsg) bMsg;
        NFCL2610CMsg bizMsg = (NFCL2610CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.arRfRqstPk);
        scrnMsg.addCheckItem(scrnMsg.billToCustAcctCd);
        scrnMsg.addCheckItem(scrnMsg.billToCustLocCd);
        // START 2018/07/11 [QC#26989, ADD]
        scrnMsg.addCheckItem(scrnMsg.xxQueryFltrTxt);
        // END   2018/07/11 [QC#26989, ADD]
        scrnMsg.putErrorScreen();

        NFCL2610CommonLogic.setFocusRule(scrnMsg);
        NFCL2610CommonLogic.setListInactive(scrnMsg);
        NFCL2610CommonLogic.initAppFracDigit(scrnMsg);
        // START 2018/03/02 E.Kameishi [QC#22765,ADD]
        scrnMsg.setFocusItem(scrnMsg.arRcptRfCmntTxt);
        // END 2018/03/02 E.Kameishi [QC#22765,ADD]
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        } else {
            NFCL2610CommonLogic.submitButtonsActive(this, scrnMsg);
        }
    }
}
