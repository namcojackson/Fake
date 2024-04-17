/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1190;

import static business.servlet.NSAL1190.constant.NSAL1190Constant.BIZ_ID;
import static business.servlet.NSAL1190.constant.NSAL1190Constant.EZD_FUNC_CD_INQ;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1190.NSAL1190CMsg;
import business.servlet.NSAL1190.common.NSAL1190CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Hitachi         O.Okuma         Create          N/A
 * 2018/02/05   CITS            M.Naito         Update          QC#21184
 *</pre>
 */
public class NSAL1190_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1190BMsg scrnMsg = (NSAL1190BMsg) bMsg;

        NSAL1190CMsg bizMsg = new NSAL1190CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1190BMsg scrnMsg = (NSAL1190BMsg) bMsg;
        NSAL1190CMsg bizMsg  = (NSAL1190CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL1190CommonLogic.initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL1190BMsg scrnMsg = (NSAL1190BMsg) bMsg;

        scrnMsg.mtrLbNm_H.setNameForMessage("Counter Name");
        scrnMsg.mtrLbDescTxt_H.setNameForMessage("Counter Description");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mtrLbNm_A.setNameForMessage("Counter");
            scrnMsg.A.no(i).mtrLbTpCd_A.setNameForMessage("Type");
            scrnMsg.A.no(i).mtrIdxCd_A.setNameForMessage("Ind");
            scrnMsg.A.no(i).bllgMtrLvlNum_A.setNameForMessage("Level");
            scrnMsg.A.no(i).mtrLbDescTxt_A.setNameForMessage("Description");
            scrnMsg.A.no(i).mtrLbNm_AF.setNameForMessage("FLT Counter");
            scrnMsg.A.no(i).mtrLbNm_AG.setNameForMessage("AGG Counter");
            scrnMsg.A.no(i).actvFlg_A.setNameForMessage("Active");
            scrnMsg.A.no(i).effFromDt_A.setNameForMessage("Start Date");
            scrnMsg.A.no(i).effThruDt_A.setNameForMessage("End Date");
            scrnMsg.A.no(i).bwMtrFlg_A.setNameForMessage("BW");
            scrnMsg.A.no(i).colorMtrFlg_A.setNameForMessage("Color");
            scrnMsg.A.no(i).totMtrFlg_A.setNameForMessage("Total");
            scrnMsg.A.no(i).corpAdvtgFlg_A.setNameForMessage("Corp");
            scrnMsg.A.no(i).mtrCntrId_A.setNameForMessage("CINC Counter ID");
            scrnMsg.A.no(i).intgMdseCd_A.setNameForMessage("Counter Item");
            // START 2018/02/05 M.Naito [QC#21184,ADD]
            scrnMsg.A.no(i).invPrintMtrLbDescTxt_A.setNameForMessage("Invoice Print Name");
            // END 2018/02/05 M.Naito [QC#21184,ADD]
        }

    }
}
