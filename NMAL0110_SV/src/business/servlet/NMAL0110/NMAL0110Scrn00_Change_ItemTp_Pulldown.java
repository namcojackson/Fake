package business.servlet.NMAL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0110.NMAL0110CMsg;
import business.servlet.NMAL0110.NMAL0110BMsg;
import business.servlet.NMAL0110.common.NMAL0110CommonLogic;
import business.servlet.NMAL0110.constant.NMAL0110Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/12/2015   SRAA            K.Aratani       Create
 * 07/03/2015   Fujitsu         C.Tanaka        Update
 * 10/05/2015   SRAA            K.Aratani       Update
 * 06/16/2016   SRAA            K.Aratani       Update          QC#6748,9891,9916,9970
 * 09/25/2017   Fujitsu         T.Aoi           Update          QC#18534(L3#445)
 *</pre>
 */
public class NMAL0110Scrn00_Change_ItemTp_Pulldown extends S21CommonHandler implements NMAL0110Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
        NMAL0110CMsg bizMsg = new NMAL0110CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0110CMsg bizMsg = (NMAL0110CMsg) cMsg;
        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
		scrnMsg.xxRsltFlg_H1.clear(); // Manufacture Item Duplication Warning Flag
		scrnMsg.xxRsltFlg_H2.clear(); // Mercury Code and Parts Code does not exists Warning Flag
		scrnMsg.xxRsltFlg_H3.clear(); // "Save as Tmpl" button Disabled Flag
		
		//Machine
    	scrnMsg.xxChkBox_ME.clear();
    	scrnMsg.backOrdImpctTpCd_H1.clear();
    	scrnMsg.xxChkBox_RM.clear();
    	scrnMsg.sowReqFlg_H1.clear();
    	scrnMsg.svcChrgItemTpCd_H1.clear();
    	//Accessory
    	scrnMsg.backOrdImpctTpCd_H1.clear();
    	scrnMsg.xxChkBox_RM.clear();
    	scrnMsg.sowReqFlg_H1.clear();
    	//Supply
    	scrnMsg.imgSplyOemMnfCd_H1.clear();
    	scrnMsg.imgSplyOemCd_H1.clear();
    	scrnMsg.imgSplyTpCd_H1.clear();
    	scrnMsg.imgSplyColorTpCd_H1.clear();
    	scrnMsg.imgSplyYieldCnt_H1.clear();
    	scrnMsg.imgSplyYieldUomCd_H1.clear();
    	scrnMsg.imgSplyYieldTpCd_H1.clear();
    	scrnMsg.easyPackTpCd_H1.clear();
    	scrnMsg.areaOfPaperNum_H1.clear();
    	//Parts
    	scrnMsg.prtItemTpCd_H1.clear();
    	scrnMsg.prtYieldOtptQty_H1.clear();
    	scrnMsg.prtYieldDaysAot_H1.clear();
    	scrnMsg.xxChkBox_PD.clear();
    	scrnMsg.prtSrvyReqFlg_H1.clear();
    	//Maintenance
    	scrnMsg.svcCovBaseTpCd_H1.clear();
    	scrnMsg.svcCovTmplTpCd_H1.clear();
    	scrnMsg.svcAllocTpCd_H1.clear();
    	// 2017/09/25 QC#18534(L3#445) ADD Start
    	scrnMsg.svcPgmTpCd_H1.clear();
    	scrnMsg.xxChkBox_OM.clear();
    	// 2017/09/25 QC#18534(L3#445) ADD End
    	//Maintenance Charges
    	scrnMsg.svcAllocTpCd_H1.clear();
    	scrnMsg.svcChrgItemTpCd_H1.clear();
    	scrnMsg.N.clear();
    	//Overages
    	scrnMsg.svcAllocTpCd_H1.clear();
    	//Software
    	scrnMsg.swCatgCd_H1.clear();
    	scrnMsg.swTpCd_H1.clear();
    	scrnMsg.swVrsnTxt_H1.clear();
    	scrnMsg.swProdCatgCd_H1.clear();
    	scrnMsg.swProdCatgDescTxt_H1.clear();
    	scrnMsg.swLicCtrlTpCd_H1.clear();
    	scrnMsg.xxChkBox_EC.clear();
    	scrnMsg.swLicSeatFromQty_H1.clear();
    	scrnMsg.swLicSeatToQty_H1.clear();
    	scrnMsg.swLicSeatFixQty_H1.clear();
    	scrnMsg.swMaintCtrlTpCd_H1.clear();
    	scrnMsg.asrnPntPerLicQty_H1.clear();
    	scrnMsg.bdlMaintMdseCd_H1.clear();
    	scrnMsg.maintPopAvalFlg_H1.clear();
    	scrnMsg.extMaintPopAvalFlg_H1.clear();
        scrnMsg.sowReqFlg_H1.clear();
        //Software License
    	scrnMsg.swCatgCd_H1.clear();
    	scrnMsg.swTpCd_H1.clear();
    	scrnMsg.swVrsnTxt_H1.clear();
    	scrnMsg.swProdCatgCd_H1.clear();
    	scrnMsg.swProdCatgDescTxt_H1.clear();
    	scrnMsg.swLicCtrlTpCd_H1.clear();
    	scrnMsg.xxChkBox_EC.clear();
    	scrnMsg.swLicSeatFromQty_H1.clear();
    	scrnMsg.swLicSeatToQty_H1.clear();
    	scrnMsg.swLicSeatFixQty_H1.clear();
    	scrnMsg.swMaintCtrlTpCd_H1.clear();
    	scrnMsg.asrnPntPerLicQty_H1.clear();
    	scrnMsg.bdlMaintMdseCd_H1.clear();
    	scrnMsg.maintPopAvalFlg_H1.clear();
    	scrnMsg.extMaintPopAvalFlg_H1.clear();
        scrnMsg.sowReqFlg_H1.clear();
    	//Software Maintenance
        scrnMsg.swCatgCd_H1.clear();
        scrnMsg.swVrsnTxt_H1.clear();
    	scrnMsg.swProdCatgCd_H1.clear();
    	scrnMsg.swProdCatgDescTxt_H1.clear();
        scrnMsg.swLicSeatFromQty_H1.clear();
        scrnMsg.swLicSeatToQty_H1.clear();
        scrnMsg.swLicSeatFixQty_H1.clear();
    	scrnMsg.swMaintTpCd_H1.clear();
        scrnMsg.maintItemTermMthNum_H1.clear();
    	scrnMsg.asrnPntMinQty_H1.clear();
    	scrnMsg.asrnPntMaxQty_H1.clear();
    	scrnMsg.asrnPntFixPerOrdQty_H1.clear();
        scrnMsg.sowReqFlg_H1.clear();
        //Intangible
        //scrnMsg.mdsePrcGrpCd_H1.clear();
        scrnMsg.dsIntgMdseTpCd_H1.clear();
        //Set
        scrnMsg.sowReqFlg_H1.clear();
        //Kit
        scrnMsg.sowReqFlg_H1.clear();
        //Professional Service
        scrnMsg.sowReqFlg_H1.clear();
        
		NMAL0110CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
        NMAL0110CommonLogic.changeActivation_Detail(this, getUserProfileService(), scrnMsg);
        NMAL0110CommonLogic.changeTableColorController(scrnMsg);
        
    }

}
