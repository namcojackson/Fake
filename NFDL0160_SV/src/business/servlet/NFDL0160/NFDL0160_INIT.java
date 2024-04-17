/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0160;

import static business.servlet.NFDL0160.constant.NFDL0160Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFDL0160.common.NFDL0160CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Collector Portfolio Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         C.Naito         Create          N/A
 * 2018/02/28   Hitachi         J.Kim           Update          QC#20944
 * 2018/08/03   Fujitsu         T.Ogura         Update          QC#25899
 *</pre>
 */
public class NFDL0160_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0160BMsg scrnMsg = (NFDL0160BMsg) bMsg;

        scrnMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        NFDL0160CommonLogic.initialControlScreenFields(this, scrnMsg);
        // START 2018/02/27 J.Kim [QC#20944,DEL]
        // setAppFracDigit(scrnMsg);
        // END 2018/02/27 J.Kim [QC#20944,DEL]

        // START 2018/08/03 T.Ogura [QC#25899,MOD]
//        scrnMsg.setFocusItem(scrnMsg.cltPtfoNm);
        scrnMsg.setFocusItem(scrnMsg.cltPtfoCd);
        // END   2018/08/03 T.Ogura [QC#25899,MOD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFDL0160BMsg scrnMsg = (NFDL0160BMsg) bMsg;

        // START 2018/08/03 T.Ogura [QC#25899,ADD]
        scrnMsg.cltPtfoCd.setNameForMessage("Portfolio Code");
        // END   2018/08/03 T.Ogura [QC#25899,ADD]
        scrnMsg.cltPtfoNm.setNameForMessage("Portfolio Name");
        scrnMsg.cltPsnNm.setNameForMessage("Collector Name");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            // START 2018/08/03 T.Ogura [QC#25899,ADD]
            scrnMsg.A.no(i).cltPtfoCd_A.setNameForMessage("Portfolio Code");
            // END   2018/08/03 T.Ogura [QC#25899,ADD]
            scrnMsg.A.no(i).cltPtfoNm_A.setNameForMessage("Portfolio Name");
            scrnMsg.A.no(i).cltPtfoDescTxt_A.setNameForMessage("Description");
            scrnMsg.A.no(i).cltPtfoCorNm_A.setNameForMessage("Correspondence Name");
            scrnMsg.A.no(i).cltPsnNm_A.setNameForMessage("Collector");
            scrnMsg.A.no(i).cltStmtPhoNum_A.setNameForMessage("Telephone Number");
            scrnMsg.A.no(i).cltStmtFaxNum_A.setNameForMessage("Fax Number");
            scrnMsg.A.no(i).arAdjTpDescTxt_A.setNameForMessage("AR WriteOff Activity");
            // START 2018/02/27 J.Kim [QC#20944,DEL]
            // scrnMsg.A.no(i).writeOffApvlLimitAmt_A.setNameForMessage("Write Off Approval Limit");
            // END 2018/02/27 J.Kim [QC#20944,DEL]
            scrnMsg.A.no(i).cltCrAnlstEquipPsnNm_A.setNameForMessage("Credit Analyst Name (Equipment)");
            scrnMsg.A.no(i).cltCrAnlstSvcPsnNm_A.setNameForMessage("Credit Analyst Name (Service)");
            scrnMsg.A.no(i).cltCrAnlstSplyPsnNm_A.setNameForMessage("Credit Analyst Name (Supplies)");
            scrnMsg.A.no(i).cltPtfoNm_AR.setNameForMessage("Portfolio Next Level");
            scrnMsg.A.no(i).cltPtfoStsFlg_A.setNameForMessage("Status");
        }
    }

    // START 2018/02/27 J.Kim [QC#20944,DEL]
    // private void setAppFracDigit(NFDL0160BMsg scrnMsg) {
    // for (int i = 0; i < scrnMsg.A.length(); i++) {
    // scrnMsg.A.no(i).writeOffApvlLimitAmt_A.setAppFracDigit(2);
    // }
    // }
    // END 2018/02/27 J.Kim [QC#20944,DEL]
}
