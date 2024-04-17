/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7040;

import static business.servlet.NMAL7010.constant.NMAL7010Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7040.common.NMAL7040CommonLogic;
import business.servlet.NMAL7040.constant.NMAL7040Constant;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/**
 *<pre>
 * Price List Filter Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   SRA             E.Inada         Create          N/A
 * 2016/04/12   SRA             E.Inada         Update          QC#6378
 * 2016/09/13   Fujitsu         R.Nakamura      Update          QC#11615
 * 2016/10/17   Fujitsu         W.Honda         Update          QC#15193
 * 2018/07/17   Fujitsu         H.Kumagai       Update          QC#20267
 * 2018/11/17   Fujitsu         N.Sugiura       Create          QC#29147
 * 2018/11/30   Fujitsu         K.Kato          Update          QC#29319
 *</pre>
 */
public class NMAL7040Scrn00_CMN_Close extends S21CommonHandler implements NMAL7040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7040BMsg scrnMsg = (NMAL7040BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.prcListEquipConfigNum);
        scrnMsg.addCheckItem(scrnMsg.prcListEquipConfigNm);
        scrnMsg.addCheckItem(scrnMsg.pkgUomCd);
        scrnMsg.addCheckItem(scrnMsg.prcRateTpCd);
        scrnMsg.addCheckItem(scrnMsg.prcSvcPlnTpCd);
        scrnMsg.addCheckItem(scrnMsg.prcSvcContrTpCd);
        scrnMsg.addCheckItem(scrnMsg.mtrLbNm);
        // 2018/11/17 QC#29147 Mod Start
        // scrnMsg.addCheckItem(scrnMsg.prcListBandCd);
        scrnMsg.addCheckItem(scrnMsg.prcListBandDescTxt);
        // 2018/11/17 QC#29147 Mod End
        scrnMsg.addCheckItem(scrnMsg.prcSvcTierTpCd);
        scrnMsg.addCheckItem(scrnMsg.splyAgmtPlnNm);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.prcAddlChrgTpCd);
        scrnMsg.addCheckItem(scrnMsg.mktMdlSegCd);
        scrnMsg.addCheckItem(scrnMsg.prcLeaseCmpyAbbrNm);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm);
        scrnMsg.addCheckItem(scrnMsg.prcQlfyTpCd);
        scrnMsg.addCheckItem(scrnMsg.prcQlfyValTxt);
        // 2018/11/27 QC#29319 Add Start
        scrnMsg.addCheckItem(scrnMsg.xxPrcQlfyValSrchTxt);
        // 2018/11/27 QC#29319 Add Start
        scrnMsg.addCheckItem(scrnMsg.mdlNm);
        scrnMsg.addCheckItem(scrnMsg.prcMtrPkgNm);
        scrnMsg.addCheckItem(scrnMsg.prcPgmTpCd);
        scrnMsg.addCheckItem(scrnMsg.prcEquipTpCd);
        scrnMsg.addCheckItem(scrnMsg.svcSegCd);
        scrnMsg.addCheckItem(scrnMsg.prcInOutRgCd);
        // Mod Start 2018/07/17 QC#20267
        scrnMsg.addCheckItem(scrnMsg.mnfItemCd);
        // 2018/11/27 QC#29319 Add Start
        scrnMsg.addCheckItem(scrnMsg.xxMnfItemCdSrchTxt);
        // 2018/11/27 QC#29319 Mod End
        // Mod End 2018/07/17 QC#20267
        scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt);
        // Mod Start 2016/10/17 QC#15193
//        scrnMsg.addCheckItem(scrnMsg.coaMdseTpNm);
        scrnMsg.addCheckItem(scrnMsg.coaProjNm);
        // Mod End 2016/10/17 QC#15193
        scrnMsg.addCheckItem(scrnMsg.mdseItemTpNm);
        scrnMsg.addCheckItem(scrnMsg.coaProdNm);
        scrnMsg.addCheckItem(scrnMsg.t_MdlNm);
        // 2018/11/27 QC#29319 Add Start
        scrnMsg.addCheckItem(scrnMsg.xxTMdlNmSrchTxt);
        // 2018/11/27 QC#29319 Mod End
        scrnMsg.addCheckItem(scrnMsg.zerothProdCtrlCd);
        scrnMsg.addCheckItem(scrnMsg.firstProdCtrlCd);
        scrnMsg.addCheckItem(scrnMsg.scdProdCtrlCd);
        scrnMsg.addCheckItem(scrnMsg.thirdProdCtrlCd);
        scrnMsg.addCheckItem(scrnMsg.frthProdCtrlCd);
        scrnMsg.addCheckItem(scrnMsg.prcTermAot);
        scrnMsg.addCheckItem(scrnMsg.prcTermUomCd);
        scrnMsg.addCheckItem(scrnMsg.custBidQty);
        scrnMsg.addCheckItem(scrnMsg.prcFmlaNm);
        scrnMsg.addCheckItem(scrnMsg.openMktFlg);
        scrnMsg.addCheckItem(scrnMsg.compCd);
        scrnMsg.addCheckItem(scrnMsg.termFromMthAot);
        scrnMsg.addCheckItem(scrnMsg.termThruMthAot);
        scrnMsg.addCheckItem(scrnMsg.prcSvcZoneCd);
        // Mod Start 2016/09/13 QC#11615
//        scrnMsg.addCheckItem(scrnMsg.mdseNm);
        scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt_H2);
        // Mod End 2016/09/13 QC#11615
        scrnMsg.addCheckItem(scrnMsg.xxYesNoCd);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H2);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H2);
        scrnMsg.addCheckItem(scrnMsg.xxFullNm_H1);
        scrnMsg.addCheckItem(scrnMsg.xxFullNm_H2);
        scrnMsg.addCheckItem(scrnMsg.cratDt_H1);
        scrnMsg.addCheckItem(scrnMsg.cratDt_H2);
        scrnMsg.addCheckItem(scrnMsg.lastUpdDt_H1);
        scrnMsg.addCheckItem(scrnMsg.lastUpdDt_H2);

        String[] strArray;

        if (ZYPCommonFunc.hasValue(scrnMsg.xxPrcQlfyValSrchTxt)) {
            strArray = S21StringUtil.toStringArray(scrnMsg.xxPrcQlfyValSrchTxt.getValue(), COMMA);
            if (MAX_INPUT_DATA_CNT < strArray.length) {
                scrnMsg.xxPrcQlfyValSrchTxt.setErrorInfo(1, NMAM8696E, new String[] {scrnMsg.xxPrcQlfyValSrchTxt.getNameForMessage(), String.valueOf(MAX_INPUT_DATA_CNT)});
            }

            if (MAX_INPUT_DATA_LENGTH < scrnMsg.xxPrcQlfyValSrchTxt.getValue().length()) {
                scrnMsg.xxPrcQlfyValSrchTxt.setErrorInfo(1, NMAM8579E, new String[] {scrnMsg.xxPrcQlfyValSrchTxt.getNameForMessage()});
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxMnfItemCdSrchTxt)) {
            strArray = S21StringUtil.toStringArray(scrnMsg.xxMnfItemCdSrchTxt.getValue(), COMMA);
            if (MAX_INPUT_DATA_CNT < strArray.length) {
                scrnMsg.xxMnfItemCdSrchTxt.setErrorInfo(1, NMAM8696E, new String[] {scrnMsg.xxMnfItemCdSrchTxt.getNameForMessage(), String.valueOf(MAX_INPUT_DATA_CNT)});
            }

            if (MAX_INPUT_DATA_LENGTH < scrnMsg.xxMnfItemCdSrchTxt.getValue().length()) {
                scrnMsg.xxMnfItemCdSrchTxt.setErrorInfo(1, NMAM8579E, new String[] {scrnMsg.xxMnfItemCdSrchTxt.getNameForMessage()});
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxTMdlNmSrchTxt)) {
            strArray = S21StringUtil.toStringArray(scrnMsg.xxTMdlNmSrchTxt.getValue(), COMMA);
            if (MAX_INPUT_DATA_CNT < strArray.length) {
                scrnMsg.xxTMdlNmSrchTxt.setErrorInfo(1, NMAM8696E, new String[] {scrnMsg.xxTMdlNmSrchTxt.getNameForMessage(), String.valueOf(MAX_INPUT_DATA_CNT)});
            }

            if (MAX_INPUT_DATA_LENGTH < scrnMsg.xxTMdlNmSrchTxt.getValue().length()) {
                scrnMsg.xxTMdlNmSrchTxt.setErrorInfo(1, NMAM8579E, new String[] {scrnMsg.xxTMdlNmSrchTxt.getNameForMessage()});
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7040BMsg scrnMsg = (NMAL7040BMsg) bMsg;

        Object[] arg = (Object[]) getArgForSubScreen();

        NMAL7040CommonLogic.setOutputParam(scrnMsg, arg);
    }
}
