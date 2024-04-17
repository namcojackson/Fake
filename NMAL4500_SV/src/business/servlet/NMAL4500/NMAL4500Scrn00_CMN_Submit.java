/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NMAL4500;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL4500.NMAL4500CMsg;
import business.servlet.NMAL4500.common.NMAL4500CommonLogic;
import business.servlet.NMAL4500.constant.NMAL4500Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRSMT_METH_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/09/01   SRA             T.Chijimatsu    Create          N/A
 * 2010/04/05   Fujitsu         T.ishii         Update          5203,5206
 * 2010/04/13   Fujitsu         T.ishii         Update          5267
 * 2013/05/08   Fujitsu         F.Saito         Update          WDS#R-MS001
 * 2013/08/05   Fujitsu         N.Sugiura       Update          QC1469
 * 2013/09/19   Fujitsu         N.Sugiura       Update          MEX-LC004
 * 2013/10/21   Fujitsu         D.Yanagisawa    Update          MEX-LC001
 * 08/01/2016   CITS            S.Endo          Update          QC#10840
 */
public class NMAL4500Scrn00_CMN_Submit extends S21CommonHandler implements NMAL4500Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.coaAfflCd_01);
        scrnMsg.addCheckItem(scrnMsg.postCd_01);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr_01);
        scrnMsg.addCheckItem(scrnMsg.stCd_01);
        scrnMsg.addCheckItem(scrnMsg.ctryCd_03);
        scrnMsg.addCheckItem(scrnMsg.cntyPk_03);
        scrnMsg.addCheckItem(scrnMsg.xxScrEventNm_01);
        // ADD START 2013/09/19 MEX-LC004
        scrnMsg.addCheckItem(scrnMsg.dealCcyCd_01);
        // ADD END 2013/09/19 MEX-LC004
        // ADD START 2013/10/21 MEX-LC001
        scrnMsg.addCheckItem(scrnMsg.invVndCd_01);
        // ADD E N D 2013/10/21 MEX-LC001

        // when warehouse code is selected, vnvCd and locNm check is not necessary.<defect#5206 T.Ishii 20100405>
        if (!ZYPCommonFunc.hasValue(scrnMsg.whPk_03)) {
            scrnMsg.addCheckItem(scrnMsg.vndCd_01);
            scrnMsg.addCheckItem(scrnMsg.locNm_01);
        }

        // <defect#5203,5206 T.Ishii 20100405>
        scrnMsg.whPk_03.clearErrorInfo();
        scrnMsg.addCheckItem(scrnMsg.attnNm_01);
        scrnMsg.addCheckItem(scrnMsg.whPk_03);
        NMAL4500CommonLogic.addCheckItemForVndTp(scrnMsg);
        NMAL4500CommonLogic.validateWHVndTpRelations(scrnMsg);

        // check selected vendor type<defect#5267 T.Ishii 20100413>
        NMAL4500CommonLogic.validateSelectedVndTp(scrnMsg);
        // DEL START 2013/08/05 QC1469
        // NMAL4500CommonLogic.checkPostCd(scrnMsg);
        // QC361875 20111005
        // if (ZYPCommonFunc.hasValue(scrnMsg.ctryCd_03) &&
        // scrnMsg.ctryCd_03.getValue().equals(CTRY.UNITED_STATES_OF_AMERICA))
        // {
        // if (ZYPCommonFunc.hasValue(scrnMsg.thirdPtyVndFlg_01) &&
        // scrnMsg.thirdPtyVndFlg_01.getValue().equals(ZYPConstant.FLG_ON_Y))
        // {
        // if (!ZYPCommonFunc.hasValue(scrnMsg.cntyPk_03)) {
        // scrnMsg.cntyPk_03.setErrorInfo(1, "ZZZM9025E", new String[]
        // {"County" });
        // }
        // }
        // }
        // DEL END 2013/08/05 QC1469

        // ADD START 2013/05/08 WDS#R-MS001
        // EMAIL(Text) Or EMAIL(PDF) -> Empty Mail Address Is Error
        boolean isMandatory = (TRSMT_METH_TP.EMAIL.equals(scrnMsg.trsmtMethTpCd_03.getValue())
                                || TRSMT_METH_TP.EMAIL_PDF.equals(scrnMsg.trsmtMethTpCd_03.getValue()));

        // Check Mail Address
        scrnMsg.addCheckItem(scrnMsg.sendPoEmlAddr_01);
        NMAL4500CommonLogic.checkPoEmlAddr(scrnMsg.sendPoEmlAddr_01, isMandatory);
        // ADD END   2013/05/08 WDS#R-MS001

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;

        NMAL4500CMsg bizMsg = new NMAL4500CMsg();
        bizMsg.setBusinessID("NMAL4500");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;
        NMAL4500CMsg bizMsg  = (NMAL4500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Check duplicate confirm message
        if (scrnMsg.xxScrEventNm_01.getValue().equals(VND_CHECK)) {
            return;
        }

        // Check items erros
        scrnMsg.addCheckItem(scrnMsg.cntyPk_03);
        scrnMsg.addCheckItem(scrnMsg.postCd_01);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr_01);
        scrnMsg.addCheckItem(scrnMsg.ctryCd_03);
        scrnMsg.addCheckItem(scrnMsg.stCd_01);
        scrnMsg.addCheckItem(scrnMsg.coaAfflCd_01);
        scrnMsg.addCheckItem(scrnMsg.trsmtMethTpCd_03);
        scrnMsg.addCheckItem(scrnMsg.invRcvMethTpCd_03);
        scrnMsg.addCheckItem(scrnMsg.firstLineAddr_01);
        scrnMsg.addCheckItem(scrnMsg.scdLineAddr_01);
        // <defect#5206 T.Ishii 20100405>
        if (!ZYPCommonFunc.hasValue(scrnMsg.whPk_03) || bizMsg.getMessageCode().equals("NMAM8070W")) {
            scrnMsg.addCheckItem(scrnMsg.vndCd_01);
            scrnMsg.addCheckItem(scrnMsg.locNm_01);
        }
        // <defect#5203,5206 T.Ishii 20100405>
        scrnMsg.addCheckItem(scrnMsg.attnNm_01);
        scrnMsg.addCheckItem(scrnMsg.whPk_03);

        // ADD START 2013/09/19 MEX-LC004
        scrnMsg.addCheckItem(scrnMsg.dealCcyCd_01);
        // ADD END 2013/09/19 MEX-LC004
        // ADD START 2013/10/21 MEX-LC001
        scrnMsg.addCheckItem(scrnMsg.invVndCd_01);
        // ADD E N D 2013/10/21 MEX-LC001

        NMAL4500CommonLogic.addCheckItemForVndTp(scrnMsg);

        scrnMsg.putErrorScreen();

        // Check message error
        // add confirmation for <defect#5206 T.Ishii 20100405>
        if (bizMsg.getMessageKind().equals("E") || bizMsg.getMessageCode().equals("NMAM8070W")) {
            return;
        }

        // Screen control
        NMAL4500CommonLogic.doScreenControl_AfterSubmit(this, scrnMsg);
    }
}
