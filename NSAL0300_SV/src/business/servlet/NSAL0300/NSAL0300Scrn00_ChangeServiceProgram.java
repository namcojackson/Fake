/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0300.NSAL0300CMsg;
//import business.servlet.NSAL0300.constant.NSAL0300Constant;

import business.blap.NSAL0300.NSAL0300CMsg;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;
import business.servlet.NSAL0300.constant.NSAL0300Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/01   Hitachi         T.Aoyagi        Create          QC#11261
 * 2016/07/28   Hitachi         T.Kanasaka      Update          QC#4806
 * 2018/01/11   Hitachi         T.Tomita        Update          QC#18552
 * 2018/06/18   Hitachi         K.Kim           Update          QC#25195
 * 2018/08/20   Hitachi         T.Tomita        Update          QC#26946
 *</pre>
 */
public class NSAL0300Scrn00_ChangeServiceProgram extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, BigDecimal.valueOf(selectIdx));

        if (selectIdx > -1) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIdx).mdseDescShortTxt_B)) {
                scrnMsg.addCheckItem(scrnMsg.B.no(selectIdx).mdseDescShortTxt_B);
            }
        } else {
            if (ZYPCommonFunc.hasValue(scrnMsg.mdseDescShortTxt_SP)) {
                scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt_SP);
            }
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg = new NSAL0300CMsg();
        bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_INQ);
        // START 2018/06/18 K.Kim [QC#25195, ADD]
        int selectIdx = getButtonSelectNumber();
        if (selectIdx == -1) {
            scrnMsg.svcPgmMdseCd.clear();
        // Mod Start 2018/08/20 QC#26946
        } else {
            scrnMsg.B.no(selectIdx).mdseDescShortTxt_B.clear();
        }
        // Mod End 2018/08/20 QC#26946
        // END 2018/06/18 K.Kim [QC#25195, ADD]
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg  = (NSAL0300CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int selectIdx = getButtonSelectNumber();
        // Mod Start 2018/01/11 QC#18552
        String mdseCd = null;
        String mdseNm = null;
        if (selectIdx > -1) {
            mdseCd = scrnMsg.B.no(selectIdx).svcPgmMdseCd_B.getValue();
//            mdseNm = scrnMsg.B.no(selectIdx).mdseDescShortTxt_B.getValue();
        } else {
            mdseNm = scrnMsg.mdseDescShortTxt_SP.getValue();
        }

        this.setResult("no");
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSetFlg.getValue())) {
            this.setResult("yes");
            setArgForSubScreen(NSAL0300CommonLogic.setSvcPgmCommonPopUpParam(scrnMsg, getGlobalCompanyCode(), mdseCd, mdseNm));
            return;
        }
        // Mod End 2018/01/11 QC#18552

        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
        if (selectIdx > -1) {
            // Mod Start 2018/01/11 QC#18552
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIdx).svcPgmMdseCd_B)) {
                scrnMsg.addCheckItem(scrnMsg.B.no(selectIdx).svcPgmMdseCd_B);
            }
            // Mod End 2018/01/11 QC#18552
        } else {
            if (ZYPCommonFunc.hasValue(scrnMsg.mdseDescShortTxt_SP)) {
                scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt_SP);
            }
        }
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        if (selectIdx > -1) {
            scrnMsg.setFocusItem(scrnMsg.B.no(selectIdx).basePrcDealAmt_B);
        } else {
            scrnMsg.setFocusItem(scrnMsg.baseBllgCycleCd);
        }
    }
}
