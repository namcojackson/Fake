/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0160;

import static business.servlet.NFDL0160.constant.NFDL0160Constant.NFDM0023E;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BIZ_ID;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BTN_CMN_SUB;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.NZZM0002I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0160.NFDL0160CMsg;
import business.servlet.NFDL0160.common.NFDL0160CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collector Portfolio Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         C.Naito         Create          N/A
 * 2016/08/10   Hitachi         K.Kojima        Update          QC#13129
 * 2017/01/05   Fujitsu         T.Murai         Update          QC#16295
 * 2018/02/28   Hitachi         J.Kim           Update          QC#20944
 * 2018/08/03   Fujitsu         T.Ogura         Update          QC#25899
 *</pre>
 */
public class NFDL0160Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0160BMsg scrnMsg = (NFDL0160BMsg) bMsg;

        // VisibleItem mandatoryCheck -> addCheckItem
        // START 2016/08/09 K.Kojima [QC#13129,MOD]
        // scrnMsg.A.setCheckParam(new String[]
        // {NFDL0160Bean.cltPtfoNm_A, NFDL0160Bean.cltPtfoDescTxt_A,
        // NFDL0160Bean.cltPtfoCorNm_A, NFDL0160Bean.cltPsnNm_A,
        // NFDL0160Bean.cltStmtPhoNum_A, NFDL0160Bean.cltStmtFaxNum_A,
        // NFDL0160Bean.arAdjTpDescTxt_A,
        // NFDL0160Bean.writeOffApvlLimitAmt_A,
        // NFDL0160Bean.cltCrAnlstEquipPsnNm_A,
        // NFDL0160Bean.cltCrAnlstSvcPsnNm_A,
        // NFDL0160Bean.cltCrAnlstSplyPsnNm_A,
        // NFDL0160Bean.cltPtfoNm_AR }, 1);
        scrnMsg.A.setCheckParam(new String[] {NFDL0160Bean.cltPtfoNm_A, NFDL0160Bean.cltPtfoDescTxt_A, NFDL0160Bean.cltPtfoCorNm_A, NFDL0160Bean.cltPsnNm_A, NFDL0160Bean.cltStmtPhoNum_A, NFDL0160Bean.cltStmtFaxNum_A,
                NFDL0160Bean.arAdjTpDescTxt_A, NFDL0160Bean.cltCrAnlstEquipPsnNm_A, NFDL0160Bean.cltCrAnlstSvcPsnNm_A, NFDL0160Bean.cltCrAnlstSplyPsnNm_A, NFDL0160Bean.cltPtfoNm_AR,
                NFDL0160Bean.cltPtfoStsFlg_A }, 1);
        // END 2016/08/09 K.Kojima [QC#13129,MOD]
        scrnMsg.addCheckItem(scrnMsg.A);

        // InvisibleItem mandatoryCheck -> message: choose from PopUp
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cltPsnCd_A)) {
                // errorMessage choose from PopUp
                scrnMsg.A.no(i).cltPsnNm_A.setErrorInfo(1, NFDM0023E);
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cltCrAnlstEquipPsnNm_A)) {
                // errorMessage choose from PopUp
                scrnMsg.A.no(i).cltCrAnlstEquipPsnNm_A.setErrorInfo(1, NFDM0023E);
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cltCrAnlstSvcPsnNm_A)) {
                // errorMessage choose from PopUp
                scrnMsg.A.no(i).cltCrAnlstSvcPsnNm_A.setErrorInfo(1, NFDM0023E);
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cltCrAnlstSplyPsnNm_A)) {
                // errorMessage choose from PopUp
                scrnMsg.A.no(i).cltCrAnlstSplyPsnNm_A.setErrorInfo(1, NFDM0023E);
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).cltPtfoNm_AR)) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).relCltPtfoPk_A)) {
                    // errorMessage choose from PopUp
                    scrnMsg.A.no(i).cltPtfoNm_AR.setErrorInfo(1, NFDM0023E);
                }
            } else {
                // RelationCltPtfo_Nm is blank -> Clear RelationCltPtfo_Pk
                scrnMsg.A.no(i).relCltPtfoPk_A.clear();
            }
            // START 2017/01/05 [QC#16295,ADD]
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arAdjTpDescTxt_A)) {
                // errorMessage choose from PopUp
                scrnMsg.A.no(i).arAdjTpDescTxt_A.setErrorInfo(1, NFDM0023E);
            }
            // END   2017/01/05 [QC#16295,ADD]
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0160BMsg scrnMsg = (NFDL0160BMsg) bMsg;

        NFDL0160CMsg bizMsg = new NFDL0160CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0160BMsg scrnMsg = (NFDL0160BMsg) bMsg;
        NFDL0160CMsg bizMsg = (NFDL0160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.A.setInputProtected(false);
        NFDL0160CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NFDL0160CommonLogic.initCmnBtnProp(this);
        NFDL0160CommonLogic.setCmnBtnProp(this, BTN_CMN_SUB, 1);
        NFDL0160CommonLogic.controlScreenFields(this, scrnMsg, ctx.getEventName());
        // START 2018/08/03 T.Ogura [QC#25899,DEL]
//        scrnMsg.setFocusItem(scrnMsg.cltPtfoNm);
        // END   2018/08/03 T.Ogura [QC#25899,DEL]

        // for Error
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
            // START 2018/08/03 T.Ogura [QC#25899,MOD]
//            scrnMsg.setFocusItem(scrnMsg.cltPtfoNm);
            scrnMsg.setFocusItem(scrnMsg.cltPtfoCd);
            // END   2018/08/03 T.Ogura [QC#25899,MOD]
        }
    }
}
