/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_ADDL_HEADER;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_HEADER;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0774E;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_CANCELLED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_CLOSED;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1500Scrn00_PricingAction.java
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/02/21   CITS            T.Miki          Create          CSA QC#63577
 *</pre>
 */
public class NWAL1500Scrn00_Pricing extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        boolean isOrderCredit = NWAL1500CommonLogic.isOrderCredit(scrnMsg);
        final String hdrStsNm = scrnMsg.ordHdrStsDescTxt.getValue();

        if (ZYPCommonFunc.hasValue(scrnMsg.ordEntryScrEdtblFlg)
                && ZYPConstant.FLG_OFF_N.equals(scrnMsg.ordEntryScrEdtblFlg.getValue())) {
            scrnMsg.setMessageInfo(NWAM0774E, new String[] {"$" });
            throw new EZDFieldErrorException();
        }
 
        if (NWAL1500CommonLogic.isOnlyReferenceAuthority(scrnMsg)) {
            scrnMsg.setMessageInfo(NWAM0774E, new String[] {"$" });
            throw new EZDFieldErrorException();
        }

        if (HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm) //
                || isOrderCredit) {
            scrnMsg.setMessageInfo(NWAM0774E, new String[] {"$" });
            throw new EZDFieldErrorException();
        }

        if (CPO_SRC_TP.CHANGE_ORDER_MODIFICATION.equals(scrnMsg.cpoSrcTpCd.getValue())) {
            scrnMsg.setMessageInfo(NWAM0774E, new String[] {"$" });
            throw new EZDFieldErrorException();
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NWAL1500CommonLogic.addCheckItemBizLayerErr(scrnMsg, true);
        scrnMsg.putErrorScreen();
        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);

        String curDplyTab = scrnMsg.xxDplyTab.getValue();
        if (S21StringUtil.isEquals(TAB_HEADER, curDplyTab) //
                || S21StringUtil.isEquals(TAB_ADDL_HEADER, curDplyTab)) {
            NWAL1500CommonLogic.inactiveAddButton(this);
        }
    }
}
