package business.servlet.NMAL0100;

import static business.servlet.NMAL0100.constant.NMAL0100Constant.BUSINESS_ID;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0100.NMAL0100CMsg;
import business.servlet.NMAL0100.common.NMAL0100CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          N/A
 * 2015/06/24   Fujitsu         M.Yamada        Update          0601
 * 10/02/2015   SRAA            K.Aratani       Update
 *</pre>
 */
public class NMAL0100_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;
        NMAL0100CommonLogic.setPage(scrnMsg, 1);
        NMAL0100CMsg bizMsg = new NMAL0100CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;
        NMAL0100CMsg bizMsg = (NMAL0100CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL0100CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.mdseCd_H1);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;

        scrnMsg.srchOptPk_S.setNameForMessage("Search Option");
        scrnMsg.srchOptNm_S.setNameForMessage("Search Option Name");

        scrnMsg.mdseCd_H1.setNameForMessage("Item Number");
        scrnMsg.mdseDescShortTxt_H1.setNameForMessage("Item Description");
        scrnMsg.mdseItemMnfCd_H1.setNameForMessage("Manufacturer");
        scrnMsg.mnfItemCd_H1.setNameForMessage("Manufacturer Item#");

        scrnMsg.upcCd_H1.setNameForMessage("UPC Code");
        scrnMsg.upcCd_H1.setNameForMessage("UPC Code");
        scrnMsg.mdseItemStsCd_H1.setNameForMessage("Status");
        scrnMsg.mdseDescLongTxt_H1.setNameForMessage("Long Description");
        scrnMsg.mdseItemActvDt_H1.setNameForMessage("Implementation Date From");
        scrnMsg.mdseItemActvDt_H2.setNameForMessage("Implementation Date To");
        scrnMsg.mdseCratTmplNm_H1.setNameForMessage("Created From Template");
        scrnMsg.mdseItemTpCd_H1.setNameForMessage("Item Type");
        scrnMsg.mdseItemClsTpCd_H1.setNameForMessage("Item Classification");
        scrnMsg.coaMdseTpCd_H1.setNameForMessage("Merchandise Type");
        scrnMsg.coaProdCd_H1.setNameForMessage("Product Code");
        scrnMsg.prchGrpCd_H1.setNameForMessage("Planning Group");
        scrnMsg.thirdPtyItemFlg_HY.setNameForMessage("Third Party");
        scrnMsg.thirdPtyItemFlg_HN.setNameForMessage("Third Party");
        scrnMsg.mdsePrcGrpCd_H1.setNameForMessage("Pricing Group");
        scrnMsg.mktMdlCd_H1.setNameForMessage("Marketing Model");
        scrnMsg.mktMdlSegCd_H1.setNameForMessage("Marketing Segment");
        scrnMsg.zerothProdCtrlCd_H1.setNameForMessage("Product Level 1");
        scrnMsg.firstProdCtrlCd_H1.setNameForMessage("Product Level 2");
        scrnMsg.scdProdCtrlCd_H1.setNameForMessage("Product Level 3");
        scrnMsg.thirdProdCtrlCd_H1.setNameForMessage("Product Level 4");
        scrnMsg.frthProdCtrlCd_H1.setNameForMessage("Product Level 5");

    }

}
