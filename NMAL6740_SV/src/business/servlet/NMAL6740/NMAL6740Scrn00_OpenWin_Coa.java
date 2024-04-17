/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6740;

import static business.servlet.NMAL6740.constant.NMAL6740Constant.BIZ_ID;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.FUNC_ID1;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL6740.NMAL6740CMsg;
import business.servlet.NMAL6740.common.NMAL6740CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL6740Scrn00_OpenWin_Coa
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/09   Fujitsu         C.Tanaka        Create          CSA
 * 2016/02/16   Fujitsu         H.Ikeda         UpDate          CSA 
 * 2016/05/05   SRAA            Y.Chen          Update          QC#6382
 * 2016/09/09   SRAA            Y.Chen          Update          QC#9448
 * 2018/04/24   Fujitsu         M.Ohno          Update          QC#24635-2
 *</pre>
 */
public class NMAL6740Scrn00_OpenWin_Coa extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //2018/04/24 QC#24635-2 add start
        NMAL6740BMsg scrnMsg = (NMAL6740BMsg) bMsg;
        NMAL6740CMsg bizMsg = new NMAL6740CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
        //2018/04/24 QC#24635-2 add end
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6740BMsg scrnMsg = (NMAL6740BMsg) bMsg;
        //2018/04/24 QC#24635-2 add start
        NMAL6740CMsg bizMsg = (NMAL6740CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        NMAL6740CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        //2018/04/24 QC#24635-2 add end

        // QC#6382
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        scrnMsg.xxFuncId.setValue(FUNC_ID1);

        List<EZDBStringItem> params = new ArrayList<EZDBStringItem>();
        params.add(scrnMsg.xxFuncId);
        params.add(scrnMsg.coaCmpyCd);
// QC#9448
//        if (ZYPCommonFunc.hasValue(scrnMsg.coaAfflCd)) {
//            params.add(scrnMsg.coaAfflCd);
//        } else {
//            params.add(scrnMsg.coaAfflCd_BK);
//        }
        params.add(scrnMsg.coaAfflCd);
        params.add(scrnMsg.coaBrCd);
        params.add(scrnMsg.coaCcCd);
        params.add(scrnMsg.coaAcctCd);
        params.add(scrnMsg.coaProdCd);
        params.add(scrnMsg.coaChCd);
        params.add(scrnMsg.coaProjCd);
        params.add(scrnMsg.coaExtnCd);

        setArgForSubScreen(params.toArray(new EZDBStringItem[0]));
    }
}
