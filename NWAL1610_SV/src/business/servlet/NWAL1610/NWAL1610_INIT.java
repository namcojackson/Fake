/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1610;

import static business.servlet.NWAL1600.constant.NWAL1600Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.BIZ_ID;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.CONFIG_MODE;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.NMZM0009E;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.NWAM0270E;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.NWZM0012E;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1610.NWAL1610CMsg;
import business.servlet.NWAL1610.common.NWAL1610CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL1610_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         Yokoi           Create          N/A
 * 2016/02/20   Fujitsu         W.Honda         Update          S21 CSA QC#1130
 * 2016/02/28   Fujitsu         Y.Kanefusa      Update          S21 CSA QC#2096
 * 2016/06/01   Fujitsu         T.Murai         Update          S21 CSA QC#9189
 * 2016/08/01   Fujitsu         M.Hara          Update          S21 CSA QC#6148
 * 2019/12/23   Fujitsu         S.Kosaka        Update          QC#54999
 *</pre>
 */
public class NWAL1610_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1610BMsg scrnMsg = (NWAL1610BMsg) bMsg;

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length >= 25) { // Mod #9189 06/01/2016 // 2016/08/01 QC#6148
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, (EZDBStringItem) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, (EZDBStringItem) params[1]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustLocCd, (EZDBStringItem) params[2]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustLocCd, (EZDBStringItem) params[3]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.ordQty, (EZDBBigDecimalItem) params[4]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdLineCatgCd, (EZDBStringItem) params[5]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.ordLineSrcCd, (EZDBStringItem) params[6]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, (EZDBStringItem) params[7]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd, (EZDBStringItem) params[8]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnRsnCd, (EZDBStringItem) params[9]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem20Txt, (EZDBStringItem) params[10]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgNm, (EZDBStringItem) params[11]);
            // Mod Start #1130 02/20/2016
            // ZYPEZDItemValueSetter.setValue(scrnMsg.flPrcListDescTxt, (EZDBStringItem) params[12]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.flPrcListNm, (EZDBStringItem) params[12]);
            // Mod End #1130 02/20/2016
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcBaseDt, (EZDBDateItem) params[13]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.hddRmvCd, (EZDBStringItem) params[14]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.sellToCustCd, (EZDBStringItem) params[15]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcContrNum, (EZDBStringItem) params[16]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgCd, (EZDBStringItem) params[17]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdTpCd, (EZDBStringItem) params[18]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdRsnCd, (EZDBStringItem) params[19]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdTs, (EZDBStringItem) params[20]);
            // Add Start #2096 02/28/2016
            ZYPEZDItemValueSetter.setValue(scrnMsg.rddDt, (EZDBDateItem) params[21]);
            // Add End #2096 02/28/2016
            // Add Start #9189 06/01/2016
            ZYPEZDItemValueSetter.setValue(scrnMsg.rqstPickUpDt, (EZDBDateItem) params[22]);
            // Add End #9189 06/01/2016

            // 2016/08/01 QC#6148 Add Start
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustAcctCd, (EZDBStringItem) params[23]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustAcctCd, (EZDBStringItem) params[24]);
            // 2016/08/01 QC#6148 Add End
            // 2016/08/30 S21_NA#9806 Add Start
            if (params.length >= 27) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.csmpContrNum, (EZDBStringItem) params[25]);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dlrRefNum, (EZDBStringItem) params[26]);
            }
            // 2016/08/30 S21_NA#9806 Add End

            if (!ZYPCommonFunc.hasValue(scrnMsg.xxModeCd)) {
                scrnMsg.setMessageInfo(NWZM0012E);
            } else if (!ZYPCommonFunc.hasValue(scrnMsg.glblCmpyCd)) {
                scrnMsg.setMessageInfo(NMZM0009E);
            }
        } else {
            scrnMsg.setMessageInfo(NWAM0270E);
        }
        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            return null;
        }

        NWAL1610CommonLogic.setBackUpParam(scrnMsg);
        // if (CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
        // return null;
        // }

        NWAL1610CMsg bizMsg = new NWAL1610CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL1610BMsg scrnMsg = (NWAL1610BMsg) bMsg;

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            scrnMsg.setInputProtected(true);
            NWAL1610CommonLogic.protectCmnBtn(this, scrnMsg);
            return;
        }

        NWAL1610CMsg bizMsg = (NWAL1610CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1610CommonLogic.initCmnBtnProp(this, scrnMsg);
        // 2019/12/23 QC#54999 Add Start
        NWAL1610CommonLogic.setProtects(scrnMsg);
        // 2019/12/23 QC#54999 Add End

        if (CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.shipToCustLocCd);
        } else {
            scrnMsg.setFocusItem(scrnMsg.ordQty);
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NWAL1610BMsg scrnMsg = (NWAL1610BMsg) bMsg;

        scrnMsg.shipToCustLocCd.setNameForMessage("Ship To Location");
        scrnMsg.billToCustLocCd.setNameForMessage("Bill To Location");
        scrnMsg.ordQty.setNameForMessage("Order Quantity");
        scrnMsg.xxScrItem20Txt.setNameForMessage("Substitute Item");
        scrnMsg.dsOrdLineCatgCd.setNameForMessage("Order Line Category");
        scrnMsg.ordLineSrcCd.setNameForMessage("Order Line Source");
        scrnMsg.rtlWhNm.setNameForMessage("Warehouse");
        scrnMsg.rtlSwhNm.setNameForMessage("SubWarehouse");
        scrnMsg.prcCatgNm.setNameForMessage("Price List");
        // Mod Start #1130 02/20/2016
        // scrnMsg.flPrcListDescTxt.setNameForMessage("Floor Price List");
        scrnMsg.flPrcListNm.setNameForMessage("Floor Price List");
        // Mod End #1130 02/20/2016
        scrnMsg.prcBaseDt.setNameForMessage("Price Date");
        scrnMsg.hddRmvCd.setNameForMessage("HDD Removal");
        // Add Start #1130 02/20/2016
        scrnMsg.rddDt.setNameForMessage("Req. Delivery Date");
        // Add End #1130 02/20/2016
        // Add Start #9189 06/01/2016
        scrnMsg.rqstPickUpDt.setNameForMessage("Req. Pick Up Date");
        // Add End #9189 06/01/2016
    }
}
