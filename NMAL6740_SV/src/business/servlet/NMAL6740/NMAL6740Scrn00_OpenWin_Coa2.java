/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6740;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/02/16   Fujitsu         H.Ikeda         UpDate          CSA
 * 2016/05/05   SRAA            Y.Chen          Update          QC#6382 
 * 2016/09/09   SRAA            Y.Chen          Update          QC#9448
 *</pre>
 */
public class NMAL6740Scrn00_OpenWin_Coa2 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
// QC#9448
//
//        NMAL6740BMsg scrnMsg = (NMAL6740BMsg) bMsg;
//        // QC#6382
//        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
//
//        scrnMsg.xxFuncId.setValue(FUNC_ID2);
//
//        List<EZDBStringItem> params = new ArrayList<EZDBStringItem>();
//        params.add(scrnMsg.xxFuncId);
//        params.add(scrnMsg.coaCmpyCd);
//        params.add(scrnMsg.coaAfflCd);
//        params.add(scrnMsg.coaBrCd);
//        params.add(scrnMsg.coaCcCd);
//        params.add(scrnMsg.coaAcctCd);
//        params.add(scrnMsg.coaProdCd);
//        if (ZYPCommonFunc.hasValue(scrnMsg.coaChCd)) {
//            params.add(scrnMsg.coaChCd);
//        } else {
//            params.add(scrnMsg.coaChCd_BK);
//        }
//        params.add(scrnMsg.coaProjCd);
//        params.add(scrnMsg.coaExtnCd);
//
//        setArgForSubScreen(params.toArray(new EZDBStringItem[0]));
    }
}
