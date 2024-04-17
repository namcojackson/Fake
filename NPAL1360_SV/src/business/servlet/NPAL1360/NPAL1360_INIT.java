/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1360;

import static business.servlet.NPAL1360.constant.NPAL1360Constant.BIZ_APP_ID;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_0;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_1;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_10;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_2;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_3;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_4;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_5;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_6;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_7;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_8;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.INDEX_9;
import static business.servlet.NPAL1360.constant.NPAL1360Constant.KIT_ITEM_LINE_NUM;

import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1360.NPAL1360CMsg;
import business.servlet.NPAL1360.common.NPAL1360CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1360 Kitting Work Order Entry
 * Function Name : Initial
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/13/2016   CITS            Keiichi Masaki  Create          N/A
 * 01/31/2017   CITS            Y.IWASAKI       Update          QC#16109
 * 03/28/2018   CITS            Y.Iwasaki       Update          QC#22519
 *</pre>
 */
public class NPAL1360_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        // QC#9436
        if (params != null && params.length >= INDEX_8) {
            if (ZYPCommonFunc.hasValue((EZDBStringItem) params[INDEX_5])) {
                // 0.DS Work Order Type from Inventory Request Entry
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsWrkOrdTpCd_SL, (EZDBStringItem) params[INDEX_0]);
            } else {
                // 0.Work Order# from Work Order Search
                ZYPEZDItemValueSetter.setValue(scrnMsg.wrkOrdNum_P1, (EZDBStringItem) params[INDEX_0]);
            }
            // 1.Kit Item Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_P1, (EZDBStringItem) params[INDEX_1]);
            // 2.Order Quantity
            if (ZYPCommonFunc.hasValue((EZDBBigDecimalItem) params[INDEX_2])) {
                // from Inventory Request Entry
                ZYPEZDItemValueSetter.setValue(scrnMsg.ordQty_P1, (EZDBBigDecimalItem) params[INDEX_2]);
            } else {
                // from Work Order Search
                scrnMsg.ordQty_P1.clear();
            }
            // 3.Retail WH
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_P1, (EZDBStringItem) params[INDEX_3]);
            // 4.Retail Sub WH
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd_P1, (EZDBStringItem) params[INDEX_4]);
            // 5.PR#
            ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum_P1, (EZDBStringItem) params[INDEX_5]);
            // 6.PR Line#
            ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqLineNum_P1, (EZDBStringItem) params[INDEX_6]);
            if (ZYPCommonFunc.hasValue((EZDBBigDecimalItem) params[INDEX_7])) {
                // 7.PR Sub Line# from Inventory Request Entry
                ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqLineSubNum_P1, (EZDBBigDecimalItem) params[INDEX_7]);
            } else {
                // 7.PR Sub Line# from Work Order Search
                scrnMsg.prchReqLineSubNum_P1.clear();
            }

            scrnMsg.serNum_P1.clear();
            if (params.length >= INDEX_9) {
                // QC#9436
                // 8.Serial #
                ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_P1, (EZDBStringItem) params[INDEX_8]);
                if (ZYPCommonFunc.hasValue(scrnMsg.serNum_P1)) {
                    StringBuilder sbSerials = new StringBuilder(scrnMsg.serNum_P1.getValue());
                    if (sbSerials.length() > 30) {
                        sbSerials.delete(30, sbSerials.length());
                    }

                    ZYPEZDItemValueSetter.setValue(scrnMsg.serNum, sbSerials.toString());

                    String[] serNumArray = scrnMsg.serNum_P1.getValue().split(",");
                    scrnMsg.S.setValidCount(serNumArray.length);
                    if (serNumArray.length > 0) {
                        for (int i = 0; i < serNumArray.length; i++) {
                            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).wrkOrdDtlLineNum_S1, KIT_ITEM_LINE_NUM);
                            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).serNum_S1, serNumArray[i]);
                        }
                    }
                }
            }

            //QC#22519
            if (params.length >= INDEX_10) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rqstRcvDt_P1, (EZDBDateItem) params[INDEX_9]);
            }
        }

        NPAL1360CMsg bizMsg = new NPAL1360CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;
        NPAL1360CMsg bizMsg  = (NPAL1360CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);
        NPAL1360CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.wrkOrdNum);
    }

    @Override
    protected void setNameForMessage(EZDBMsg scrnMsg) {
        NPAL1360CommonLogic.setNameForMessage((NPAL1360BMsg) scrnMsg);

    }
}
