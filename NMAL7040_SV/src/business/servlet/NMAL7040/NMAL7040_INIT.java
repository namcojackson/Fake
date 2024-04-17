/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7040.NMAL7040CMsg;
import business.servlet.NMAL7040.common.NMAL7040CommonLogic;
import business.servlet.NMAL7040.constant.NMAL7040Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

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
 * 2018/11/27   Fujitsu         K.Kato          Update          QC#29319
 *</pre>
 */
public class NMAL7040_INIT extends S21INITCommonHandler implements NMAL7040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7040BMsg scrnMsg = (NMAL7040BMsg) bMsg;

        Object[] arg = (Object[]) getArgForSubScreen();

        NMAL7040CommonLogic.setInputParam(scrnMsg, arg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7040BMsg scrnMsg = (NMAL7040BMsg) bMsg;
        NMAL7040CMsg bizMsg = new NMAL7040CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7040BMsg scrnMsg = (NMAL7040BMsg) bMsg;
        NMAL7040CMsg bizMsg = (NMAL7040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7040CommonLogic.initCmnBtnProp(this);

        NMAL7040CommonLogic.setScrnCtrl(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7040BMsg scrnMsg = (NMAL7040BMsg) bMsg;

        scrnMsg.prcCatgCd_H.setNameForMessage("Price List ID");
        scrnMsg.prcCatgNm_H.setNameForMessage("Price List Name");
        scrnMsg.prcQlfyTpCd.setNameForMessage("Qualifier Type");
        // 2018/11/27 QC#29319 Mod Start
        //scrnMsg.prcQlfyValTxt.setNameForMessage("Qualifier Value");
        scrnMsg.xxPrcQlfyValSrchTxt.setNameForMessage("Qualifier Value");
        // 2018/11/27 QC#29319 Mod End
        scrnMsg.mdlNm.setNameForMessage("Service Model Name");
        scrnMsg.prcMtrPkgNm.setNameForMessage("Meter Package Name");
        scrnMsg.prcPgmTpCd.setNameForMessage("Program Name");
        scrnMsg.prcEquipTpCd.setNameForMessage("Equipment Type");
        scrnMsg.svcSegCd.setNameForMessage("Machine Segment");
        scrnMsg.prcInOutRgCd.setNameForMessage("In or Out Region");
        scrnMsg.prcListEquipConfigNum.setNameForMessage("Price Config#");
        scrnMsg.prcListEquipConfigNm.setNameForMessage("Price Config Name");
        scrnMsg.pkgUomCd.setNameForMessage("UOM");
        scrnMsg.prcRateTpCd.setNameForMessage("Rate Type");
        scrnMsg.prcSvcPlnTpCd.setNameForMessage("Plan Type");
        scrnMsg.prcSvcContrTpCd.setNameForMessage("Contract Type");
        scrnMsg.mtrLbNm.setNameForMessage("Meter Type");
        // 2018/11/17 QC#29147 Mod Start
        // scrnMsg.prcListBandCd.setNameForMessage("Band");
        scrnMsg.prcListBandDescTxt.setNameForMessage("Band");
        // 2018/11/17 QC#29147 Mod End
        scrnMsg.prcSvcTierTpCd.setNameForMessage("Tier Type");
        scrnMsg.splyAgmtPlnNm.setNameForMessage("Supply Plan");
        scrnMsg.mdseCd.setNameForMessage("Service Item");
        scrnMsg.prcAddlChrgTpCd.setNameForMessage("Charge Type");
        scrnMsg.mktMdlSegCd.setNameForMessage("Item Segment");
        scrnMsg.prcLeaseCmpyAbbrNm.setNameForMessage("Lease Company Abbr");
        scrnMsg.dsAcctNm.setNameForMessage("Lease Company Name");
        // Mod Start 2018/07/17 QC#20267
        // 2018/11/27 QC#29319 Mod Start
        //scrnMsg.mnfItemCd.setNameForMessage("Manufacture#");
        scrnMsg.xxMnfItemCdSrchTxt.setNameForMessage("Manufacture#");
        // 2018/11/27 QC#29319 Mod End
        // Mod End 2018/07/17 QC#20267
        scrnMsg.mdseDescShortTxt.setNameForMessage("Description");
        // Mod Start 2016/10/17 QC#15193
//        scrnMsg.coaMdseTpNm.setNameForMessage("Mdse Type");
        scrnMsg.coaProjNm.setNameForMessage("Mdse Type");
        // Mod End 2016/10/17 QC#15193
        scrnMsg.mdseItemTpNm.setNameForMessage("Item Type");
        scrnMsg.coaProdNm.setNameForMessage("Prod Code");
        // 2018/11/27 QC#29319 Mod Start
        //scrnMsg.t_MdlNm.setNameForMessage("Model");
        scrnMsg.xxTMdlNmSrchTxt.setNameForMessage("Model");
        // 2018/11/27 QC#29319 Mod End
        scrnMsg.zerothProdCtrlCd.setNameForMessage("Business Unit");
        scrnMsg.firstProdCtrlCd.setNameForMessage("Product Group");
        scrnMsg.scdProdCtrlCd.setNameForMessage("Product Family");
        scrnMsg.thirdProdCtrlCd.setNameForMessage("Product Line");
        scrnMsg.frthProdCtrlCd.setNameForMessage("Product Series");
        scrnMsg.prcTermAot.setNameForMessage("Term");
        scrnMsg.prcTermUomCd.setNameForMessage("Term UOM");
        scrnMsg.custBidQty.setNameForMessage("Bid Qty");
        scrnMsg.prcFmlaNm.setNameForMessage("Price Formula");
        scrnMsg.openMktFlg.setNameForMessage("Open Market");
        scrnMsg.compCd.setNameForMessage("Comp Code");
        scrnMsg.termFromMthAot.setNameForMessage("Term From(MTH)");
        scrnMsg.termThruMthAot.setNameForMessage("Term Thru(MTH)");
        scrnMsg.prcSvcZoneCd.setNameForMessage("Service Zone(s) Included");
        // Mod Start 2016/09/13 QC#11615
//        scrnMsg.mdseNm.setNameForMessage("Service Item Description");
        scrnMsg.mdseDescShortTxt_H2.setNameForMessage("Service Item Description");
        // Mod End 2016/09/13 QC#11615
        scrnMsg.xxYesNoCd.setNameForMessage("Qty Break");
        scrnMsg.prcListMdseCd.setNameForMessage("Item#");
        scrnMsg.effFromDt_H1.setNameForMessage("Date From");
        scrnMsg.effFromDt_H2.setNameForMessage("Date From");
        scrnMsg.effThruDt_H1.setNameForMessage("Date To");
        scrnMsg.effThruDt_H2.setNameForMessage("Date To");
        scrnMsg.xxFullNm_H1.setNameForMessage("Created By");
        scrnMsg.xxFullNm_H2.setNameForMessage("Update By");
        scrnMsg.cratDt_H1.setNameForMessage("Created Date");
        scrnMsg.cratDt_H2.setNameForMessage("Created Date");
        scrnMsg.lastUpdDt_H1.setNameForMessage("Update Date");
        scrnMsg.lastUpdDt_H2.setNameForMessage("Update Date");
    }
}
