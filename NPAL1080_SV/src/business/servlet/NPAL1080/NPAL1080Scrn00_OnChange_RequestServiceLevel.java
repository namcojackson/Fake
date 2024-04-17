/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.FUNCTION_CD_SEARCH;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1080.NPAL1080CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/11/12   CITS            K.Ogino         Create          QC#57659
 *</pre>
 */
public class NPAL1080Scrn00_OnChange_RequestServiceLevel extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // no process.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        int idx = this.getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_D1, new BigDecimal(idx));

        NPAL1080CMsg bizMsg = new NPAL1080CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        NPAL1080CMsg bizMsg = (NPAL1080CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (ZYPCommonFunc.hasValue(scrnMsg.shpgSvcLvlCd_SE) && SHPG_SVC_LVL.SCHD_DELIVERY.equals(scrnMsg.shpgSvcLvlCd_SE.getValue())) {
            scrnMsg.xxDtTm_H1.setInputProtected(false);
            scrnMsg.rqstRcvDtTxt_SL.setInputProtected(false);
            scrnMsg.xxDtTm_H1.setIndispensable(true);
            scrnMsg.rqstRcvDtTxt_SL.setIndispensable(true);
        } else {
            scrnMsg.xxDtTm_H1.setInputProtected(true);
            scrnMsg.rqstRcvDtTxt_SL.setInputProtected(true);
            scrnMsg.xxDtTm_H1.clear();
            scrnMsg.rqstRcvDtTxt_SL.clear();
            scrnMsg.xxDtTm_H1.setIndispensable(false);
            scrnMsg.rqstRcvDtTxt_SL.setIndispensable(false);
        }

        scrnMsg.setFocusItem(scrnMsg.shpgSvcLvlCd_SE);
    }
}
