/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0040;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSBL0040.common.NSBL0040CommonLogic;
import business.blap.NSBL0040.constant.NSBL0040Constant;
import business.db.TECH_MSTRTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * Credit Approval
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/01   SRA             Otsuji          Create          N/A
 * 2013/09/10   SRA             Yanada          Update          QC2131
 * 2015/11/19   Hitachi         K.Yamada        Update          for CSA
 * 2015/12/14   Hitachi         K.Yamada        Update          CSA QC#895
 * 2019/10/02   Hitachi         K.Kitachi       Update          CSA QC#52474
 *</pre>
 */
public class NSBL0040BL02 extends S21BusinessHandler implements NSBL0040Constant {

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {

        super.preDoProcess(ezdCMsg, ezdSMsg);

        try {
            NSBL0040CMsg cMsg = (NSBL0040CMsg) ezdCMsg;
            NSBL0040SMsg sMsg = (NSBL0040SMsg) ezdSMsg;
            String scrnAplId = cMsg.getScreenAplID();
            if ("NSBL0040_NSBL0020".equals(scrnAplId)) {
                doProcess_NSBL0040_NSBL0020(cMsg, sMsg);
            } else if ("NSBL0040_NSBL0160".equals(scrnAplId)) {
                doProcess_NSBL0040_NSBL0160(cMsg, sMsg);
            } else if ("NSBL0040_INIT".equals(scrnAplId)) {
                doProcess_NSBL0040_INIT(cMsg, sMsg);
            } else if ("NSBL0040Scrn00_CMN_Approve".equals(scrnAplId)) {
                doProcess_NSBL0040Scrn00_CMN_Approve(cMsg, sMsg);
            } else if ("NSBL0040Scrn00_CMN_Reject".equals(scrnAplId)) {
                doProcess_NSBL0040Scrn00_CMN_Reject(cMsg, sMsg);
            } else if ("NSBL0040Scrn00_CMN_Clear".equals(scrnAplId)) {
                doProcess_NSBL0040Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSBL0040Scrn00_CMN_Download".equals(scrnAplId)) {
                doProcess_NSBL0040Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NSBL0040Scrn00_PageNext".equals(scrnAplId)) {
                doProcess_NSBL0040Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSBL0040Scrn00_PagePrev".equals(scrnAplId)) {
                doProcess_NSBL0040Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSBL0040Scrn00_Search_BillTo".equals(scrnAplId)) {
                doProcess_NSBL0040Scrn00_Search_BillTo(cMsg, sMsg);
            } else if ("NSBL0040Scrn00_Search_SellTo".equals(scrnAplId)) {
                doProcess_NSBL0040Scrn00_Search_SellTo(cMsg, sMsg);
            } else if ("NSBL0040Scrn00_Search_ShipTo".equals(scrnAplId)) {
                doProcess_NSBL0040Scrn00_Search_ShipTo(cMsg, sMsg);
            } else if ("NSBL0040Scrn00_Search".equals(scrnAplId)) {
                doProcess_NSBL0040_Search(cMsg, sMsg);
            } else if ("NSBL0040Scrn00_TBLColumnSort".equals(scrnAplId)) {
                doProcess_NSBL0040Scrn00_TBLColumnSort(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + scrnAplId);
            }
        } finally {
            super.postDoProcess(ezdCMsg, ezdSMsg);
        }
    }

    private void doProcess_NSBL0040_NSBL0020(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg) {
        doProcess_NSBL0040_Search(cMsg, sMsg);
    }

    private void doProcess_NSBL0040_NSBL0160(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg) {
        int startIndex = cMsg.xxPageShowFromNum.getValueInt();
        int selectedRowIndex = cMsg.xxRowNum.getValueInt();
        String svcTaskNum = cMsg.A.no(selectedRowIndex).svcTaskNum_A1.getValue();
        int holdCnt = NSBL0040Query.getInstance().getHoldCnt(getGlobalCompanyCode(), svcTaskNum);
        if (holdCnt > 0) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(selectedRowIndex).xxExstFlg_A2, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(selectedRowIndex + startIndex).xxExstFlg_A2, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(selectedRowIndex).xxExstFlg_A2, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(selectedRowIndex + startIndex).xxExstFlg_A2, ZYPConstant.FLG_OFF_N);
        }
    }

    private void doProcess_NSBL0040_INIT(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg) {
        String svcTaskNum = cMsg.svcTaskNum.getValue();

        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        //DEL START 11/19/2015 for CSA
        //ZYPCodeDataUtil.createPulldownList("SVC_BILL_TP", cMsg.svcBillTpCd_02, cMsg.xxScrItem33Txt, ":");
        //DEL END 11/19/2015 for CSA

        if (ZYPCommonFunc.hasValue(svcTaskNum)) {
            ZYPEZDItemValueSetter.setValue(cMsg.svcTaskNum, svcTaskNum);
            doProcess_NSBL0040_Search(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0040Scrn00_CMN_Approve(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg) {
        doProcess_NSBL0040_Search(cMsg, sMsg);
    }

    // add start 2015/12/14 CSA Defect#895
    private void doProcess_NSBL0040Scrn00_CMN_Reject(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg) {
        doProcess_NSBL0040_Search(cMsg, sMsg);
    }
    // add end 2015/12/14 CSA Defect#895

    private void doProcess_NSBL0040Scrn00_CMN_Clear(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg) {
        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        // START 2019/10/02 K.Kitachi [QC#52474, DEL]
//        ZYPCodeDataUtil.createPulldownList("SVC_BILL_TP", cMsg.svcBillTpCd_02, cMsg.xxScrItem33Txt, ":");
        // END 2019/10/02 K.Kitachi [QC#52474, DEL]
    }

    private void doProcess_NSBL0040Scrn00_CMN_Download(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg) {

        doProcess_NSBL0040Scrn00_Search_BillTo(cMsg, sMsg);
        doProcess_NSBL0040Scrn00_Search_SellTo(cMsg, sMsg);
        doProcess_NSBL0040Scrn00_Search_ShipTo(cMsg, sMsg);

        NSBL0040Query query = NSBL0040Query.getInstance();

        if (ZYPCommonFunc.hasValue(cMsg.techCd)) {
            TECH_MSTRTMsg tMsg = query.getTechMstr(getGlobalCompanyCode(), cMsg.techCd.getValue());
            if (tMsg == null) {
                cMsg.techCd.setErrorInfo(1, NZZM0000E);
            }
        }

        if ("E".equals(cMsg.getMessageKind())) {
            return;
        }

        query.getHoldList(cMsg);
    }

    private void doProcess_NSBL0040Scrn00_Search_BillTo(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg) {
        cMsg.locNm_01.clear();
        if (ZYPCommonFunc.hasValue(cMsg.billToCustCd)) {
            String locNm = NSBL0040Query.getInstance().getBillToLocNm(getGlobalCompanyCode(), cMsg.billToCustCd.getValue());
            if (ZYPCommonFunc.hasValue(locNm)) {
                ZYPEZDItemValueSetter.setValue(cMsg.locNm_01, locNm);
            } else {
                cMsg.billToCustCd.setErrorInfo(1, NZZM0000E);
            }
        }
    }

    private void doProcess_NSBL0040Scrn00_Search_SellTo(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg) {
        cMsg.locNm_02.clear();
        if (ZYPCommonFunc.hasValue(cMsg.sellToCustCd)) {
            String locNm = NSBL0040Query.getInstance().getSellToLocNm(getGlobalCompanyCode(), cMsg.sellToCustCd.getValue());
            if (ZYPCommonFunc.hasValue(locNm)) {
                ZYPEZDItemValueSetter.setValue(cMsg.locNm_02, locNm);
            } else {
                cMsg.sellToCustCd.setErrorInfo(1, NZZM0000E);
            }
        }
    }

    private void doProcess_NSBL0040Scrn00_Search_ShipTo(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg) {
        cMsg.locNm_03.clear();
        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd)) {
            String locNm = NSBL0040Query.getInstance().getShipToLocNm(getGlobalCompanyCode(), cMsg.shipToCustCd.getValue());
            if (ZYPCommonFunc.hasValue(locNm)) {
                ZYPEZDItemValueSetter.setValue(cMsg.locNm_03, locNm);
            } else {
                cMsg.shipToCustCd.setErrorInfo(1, NZZM0000E);
            }
        }
    }

    private void doProcess_NSBL0040_Search(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg) {

        doProcess_NSBL0040Scrn00_Search_BillTo(cMsg, sMsg);
        doProcess_NSBL0040Scrn00_Search_SellTo(cMsg, sMsg);
        doProcess_NSBL0040Scrn00_Search_ShipTo(cMsg, sMsg);

        NSBL0040Query query = NSBL0040Query.getInstance();

        if (ZYPCommonFunc.hasValue(cMsg.techCd)) {
            TECH_MSTRTMsg tMsg = query.getTechMstr(getGlobalCompanyCode(), cMsg.techCd.getValue());
            if (tMsg == null) {
                cMsg.techCd.setErrorInfo(1, NZZM0000E);
            }
        }

        if ("E".equals(cMsg.getMessageKind())) {
            return;
        }

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        int count = 0;

        S21SsmEZDResult ssmRslt = query.getHoldList(cMsg, sMsg);
        if (ssmRslt.isCodeNormal()) {
            int maxRowNum = getMaxRowNum(sMsg);
            count = ssmRslt.getQueryResultCount();
            if (count > maxRowNum) {
                cMsg.setMessageInfo(NZZM0001W);
                count = maxRowNum;
            }
        } else if (ssmRslt.isCodeNotFound()) {
            cMsg.setMessageInfo(ZZZM9005W);
            return;
        }

        sMsg.A.setValidCount(count);

        int startIndex = 0;

        NSBL0040CommonLogic.paginate(cMsg, sMsg, startIndex);
    }

    private void doProcess_NSBL0040Scrn00_PageNext(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg) {

        int startIndex = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;

        NSBL0040CommonLogic.paginate(cMsg, sMsg, startIndex);
    }

    private void doProcess_NSBL0040Scrn00_PagePrev(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg) {

        int startIndex = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;

        NSBL0040CommonLogic.paginate(cMsg, sMsg, startIndex);
    }

    private void doProcess_NSBL0040Scrn00_TBLColumnSort(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg) {

        NSBL0040CommonLogic.sort(sMsg.A, sMsg.A.no(0).getBaseContents(), cMsg.xxSortItemNm.getValue(), cMsg.xxSortOrdByTxt.getValue());

        int startIndex = 0;

        NSBL0040CommonLogic.paginate(cMsg, sMsg, startIndex);
    }

    private int getMaxRowNum(NSBL0040SMsg sMsg) {
        return Integer.parseInt(sMsg.getBaseContents()[0][3]);
    }

}
