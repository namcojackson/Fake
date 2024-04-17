/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6800;

import static business.servlet.NMAL6800.constant.NMAL6800Constant.BUSINESS_ID;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_00;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_01;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_02;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_03;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_04;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_05;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_06;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_07;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_08;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_09;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_10;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_11;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_12;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_13;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_14;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_15;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_16;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_17;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_18;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_19;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_20;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_21;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_22;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_23;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_24;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_25;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_26;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_27;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_28;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.XX_MODE_CD_10;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDBDateItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6800.NMAL6800CMsg;
import business.servlet.NMAL6800.common.NMAL6800CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         E.Yoshitake     Create          N/A
 *</pre>
 */
public class NMAL6800_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6800BMsg scrnMsg = (NMAL6800BMsg) bMsg;
        
        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            setValue(scrnMsg.mdseCd_H1, (EZDBStringItem) params[IDX_00]);
        	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_01])) {
	            setValue(scrnMsg.mdseDescShortTxt_H1, ((EZDBStringItem) params[IDX_01]).getValue() + "%");
        	}
            setValue(scrnMsg.mdseItemTpCd_H1, (EZDBStringItem) params[IDX_02]);
            setValue(scrnMsg.mdseItemClsTpCd_H1, (EZDBStringItem) params[IDX_03]);
            setValue(scrnMsg.coaMdseTpCd_H1, (EZDBStringItem) params[IDX_04]);
            setValue(scrnMsg.coaProdCd_H1, (EZDBStringItem) params[IDX_05]);
            setValue(scrnMsg.prchGrpCd_H1, (EZDBStringItem) params[IDX_06]);
            if (params.length > 7) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_07])) {
	                setValue(scrnMsg.mdseDescShortTxt_H1, (EZDBStringItem) params[IDX_07]);
            	}
            }
            if (params.length > 8) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_08])) {
	                setValue(scrnMsg.mdseDescLongTxt_H1, (EZDBStringItem) params[IDX_08]);
            	}
            }
            //Default 10 Digit
            setValue(scrnMsg.xxModeCd_H1, XX_MODE_CD_10);
            if (params.length > 9) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_09])) {
	                setValue(scrnMsg.xxModeCd_H1, (EZDBStringItem) params[IDX_09]);
            	}
            }
            //Status Code
            if (params.length > 10) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_10])) {
	                setValue(scrnMsg.mdseItemStsCd_H1, (EZDBStringItem) params[IDX_10]);
            	}
            }
            //Manufacturer
            if (params.length > 11) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_11])) {
	                setValue(scrnMsg.mdseItemMnfCd_H1, (EZDBStringItem) params[IDX_11]);
            	}
            }
            //Manufacturer Item #
            if (params.length > 12) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_12])) {
	                setValue(scrnMsg.mnfItemCd_H1, (EZDBStringItem) params[IDX_12]);
            	}
            }
            //UPC Code
            if (params.length > 13) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_13])) {
	                setValue(scrnMsg.upcCd_H1, (EZDBStringItem) params[IDX_13]);
            	}
            }
            //Third Party YES flag
            if (params.length > 14) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_14])) {
            		if ("Y".equals(((EZDBStringItem) params[IDX_14]).getValue())) {
		                setValue(scrnMsg.thirdPtyItemFlg_HY, (EZDBStringItem) params[IDX_14]);
            		}
            	}
            }
            //Third Party No flag
            if (params.length > 15) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_15])) {
            		if ("Y".equals(((EZDBStringItem) params[IDX_14]).getValue())) {
		                setValue(scrnMsg.thirdPtyItemFlg_HN, (EZDBStringItem) params[IDX_14]);
            		}
            	}
            }
            //Marketing Model
            if (params.length > 16) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_16])) {
	                setValue(scrnMsg.mktMdlCd_H1, (EZDBStringItem) params[IDX_16]);
            	}
            }
            //Marketing Segment
            if (params.length > 17) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_17])) {
	                setValue(scrnMsg.mktMdlSegCd_H1, (EZDBStringItem) params[IDX_17]);
            	}
            }
            //Implementation Date from 
            if (params.length > 18) {
            	String mdseItemActvDt = getStringFromArg(params[IDX_18]);
            	if (ZYPCommonFunc.hasValue(mdseItemActvDt)) {
            		try {
	            		if (ZYPDateUtil.checkDate(mdseItemActvDt)) {
			                setValue(scrnMsg.mdseItemActvDt_H1, mdseItemActvDt);
	            		}
            		} catch(Exception e) {}
            	}
            }
            //Implementation Date Thru
            if (params.length > 19) {
            	String mdseItemActvDt = getStringFromArg(params[IDX_19]);
            	if (ZYPCommonFunc.hasValue(mdseItemActvDt)) {
            		try {
	            		if (ZYPDateUtil.checkDate(mdseItemActvDt)) {
			                setValue(scrnMsg.mdseItemActvDt_H2, mdseItemActvDt);
	            		}
            		} catch (Exception e) {}
            	}
            }
            //Product Level1
            if (params.length > 20) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_20])) {
	                setValue(scrnMsg.zerothProdCtrlNm_H1, (EZDBStringItem) params[IDX_20]);
            	}
            }
            //Product Level2
            if (params.length > 21) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_21])) {
	                setValue(scrnMsg.firstProdCtrlNm_H1, (EZDBStringItem) params[IDX_21]);
            	}
            }
            //Product Level3
            if (params.length > 22) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_22])) {
	                setValue(scrnMsg.scdProdCtrlNm_H1, (EZDBStringItem) params[IDX_22]);
            	}
            }
            //Product Level4
            if (params.length > 23) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_23])) {
	                setValue(scrnMsg.thirdProdCtrlNm_H1, (EZDBStringItem) params[IDX_23]);
            	}
            }
            //Product Level5
            if (params.length > 24) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_24])) {
	                setValue(scrnMsg.frthProdCtrlNm_H1, (EZDBStringItem) params[IDX_24]);
            	}
            }
            //Material Group1
            if (params.length > 25) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_25])) {
	                setValue(scrnMsg.slsMatGrpDescTxt_01, (EZDBStringItem) params[IDX_25]);
            	}
            }
            //Material Group2
            if (params.length > 26) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_26])) {
	                setValue(scrnMsg.slsMatGrpDescTxt_02, (EZDBStringItem) params[IDX_26]);
            	}
            }
            //Material Group3
            if (params.length > 27) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_27])) {
	                setValue(scrnMsg.slsMatGrpDescTxt_03, (EZDBStringItem) params[IDX_27]);
            	}
            }
            //Commision Group
            if (params.length > 28) {
            	if (ZYPCommonFunc.hasValue((EZDBStringItem)params[IDX_28])) {
	                setValue(scrnMsg.dsCmsnGrpDescTxt_H1, (EZDBStringItem) params[IDX_28]);
            	}
            }

        }
        
        NMAL6800CommonLogic.setPage(scrnMsg, 1);
        NMAL6800CMsg bizMsg = new NMAL6800CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }


    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6800BMsg scrnMsg = (NMAL6800BMsg) bMsg;
        NMAL6800CMsg bizMsg = (NMAL6800CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL6800CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.mdseCd_H1);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL6800BMsg scrnMsg = (NMAL6800BMsg) bMsg;

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

    private String getStringFromArg(Object val) {
    	if (val == null) return null;
    	if (val instanceof EZDBStringItem) {
    		return ((EZDBStringItem) val).getValue();
    	} else if (val instanceof EZDBDateItem) {
    		return ((EZDBDateItem) val).getValue();
    	}
		return (String) val;
    }
}
