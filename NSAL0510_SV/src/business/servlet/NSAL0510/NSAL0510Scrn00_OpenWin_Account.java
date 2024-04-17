/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0510;

import static business.servlet.NSAL0510.constant.NSAL0510Constant.NMAL6050_PRM_LENGTH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0510.constant.NSAL0510Constant.COL_NM;
import business.servlet.NSAL0510.constant.NSAL0510Constant.LBL;
import business.servlet.NSAL0510.constant.NSAL0510Constant.TBL_NM;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Sub Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/06   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL0510Scrn00_OpenWin_Account extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0510BMsg scrnMsg = (NSAL0510BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, TBL_NM.SELL_TO_CUST.toString());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, COL_NM.SELL_TO_CUST_CD.toString());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, COL_NM.DS_ACCT_NM.toString());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, COL_NM.SELL_TO_CUST_CD.toString());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, LBL.ACCT_POPUP.getLbl());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, LBL.ACCT_NUM.getLbl());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, LBL.ACCT_NM.getLbl());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, LBL.ACCT_NUM.getLbl());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, LBL.ACCT_NM.getLbl());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCondCd_P, scrnMsg.dsAcctNum_H.getValue());
        scrnMsg.xxCondNm_P.clear();

        Object[] params = new Object[NMAL6050_PRM_LENGTH];
        int i = 0;
        params[i++] = scrnMsg.xxTblNm_P;
        params[i++] = scrnMsg.xxTblCdColNm_P;
        params[i++] = scrnMsg.xxTblNmColNm_P;
        params[i++] = scrnMsg.xxTblSortColNm_P;
        params[i++] = scrnMsg.xxScrNm_P;
        params[i++] = scrnMsg.xxHdrCdLbNm_P;
        params[i++] = scrnMsg.xxHdrNmLbNm_P;
        params[i++] = scrnMsg.xxDtlCdLbNm_P;
        params[i++] = scrnMsg.xxDtlNmLbNm_P;
        params[i++] = scrnMsg.xxCondCd_P;
        params[i++] = scrnMsg.xxCondNm_P;

        setArgForSubScreen(params);
    }
}
