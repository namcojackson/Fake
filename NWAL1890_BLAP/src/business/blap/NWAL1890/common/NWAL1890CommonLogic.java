/*
 * <Pre>Copyright(c)2017 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1890.common;

import static business.blap.NWAL1890.constant.NWAL1890Constant.NWAM0001E;
import static business.blap.NWAL1890.constant.NWAL1890Constant.NWAM0507E;
import static business.blap.NWAL1890.constant.NWAL1890Constant.NWAM0938E;
import static business.blap.NWAL1890.constant.NWAL1890Constant.LINE_CONFIG_MODE;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

import business.blap.NWAL1890.NWAL1890CMsg;
import business.blap.NWAL1890.NWAL1890Query;

/**
 *<pre>
 *  Order Line Filter Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/10   Fujitsu         T.Aoi           Create          N/A
 *</pre>
 */
public class NWAL1890CommonLogic {

    /**
     * Mandatory Check(at least one search criteria)
     * @param bizMsg NWAL1890CMsg
     */
    public static boolean mndtyChk(NWAL1890CMsg bizMsg) {

        boolean mndtyChkFlg = false;

        // Config Level
        // Config Number
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum)) {
            mndtyChkFlg = true;
        }

        // Config ID
        if (ZYPCommonFunc.hasValue(bizMsg.xxConfigIdSrchTxt)) {
            mndtyChkFlg = true;
        }

        // Model
        if (ZYPCommonFunc.hasValue(bizMsg.xxMdlSrchTxt)) {
            mndtyChkFlg = true;
        }

        // Bill To Cust Name
        if (ZYPCommonFunc.hasValue(bizMsg.xxBillToAcctNmSrchTxt)) {
            mndtyChkFlg = true;
        }

        // Bill To Cust Code
        if (ZYPCommonFunc.hasValue(bizMsg.xxBillToAcctCdSrchTxt)) {
            mndtyChkFlg = true;
        }

        // Bill To Location Code
        if (ZYPCommonFunc.hasValue(bizMsg.xxBillToLocCdSrchTxt)) {
            mndtyChkFlg = true;
        }

        // Ship To Cust Name
        if (ZYPCommonFunc.hasValue(bizMsg.xxShipToAcctNmSrchTxt)) {
            mndtyChkFlg = true;
        }

        // Ship To Cust Code
        if (ZYPCommonFunc.hasValue(bizMsg.xxShipToAcctCdSrchTxt)) {
            mndtyChkFlg = true;
        }

        // Ship To Location Code
        if (ZYPCommonFunc.hasValue(bizMsg.xxShipToLocCdSrchTxt)) {
            mndtyChkFlg = true;
        }

        // Sold To Cust Name
        if (ZYPCommonFunc.hasValue(bizMsg.xxSoldToAcctNmSrchTxt)) {
            mndtyChkFlg = true;
        }

        // Sold To Cust Code
        if (ZYPCommonFunc.hasValue(bizMsg.xxSoldToAcctCdSrchTxt)) {
            mndtyChkFlg = true;
        }

        // Sold To Location Code
        if (ZYPCommonFunc.hasValue(bizMsg.xxSoldToLocCdSrchTxt)) {
            mndtyChkFlg = true;
        }

        if (LINE_CONFIG_MODE.equals(bizMsg.xxModeCd.getValue())) {
            // Line Level
            // Line Number
            if (ZYPCommonFunc.hasValue(bizMsg.xxLineNum)) {
                mndtyChkFlg = true;
            }

            // Line Status
            if (ZYPCommonFunc.hasValue(bizMsg.xxLineStsSrchTxt)) {
                mndtyChkFlg = true;
            }

            // Ordered Item
            if (ZYPCommonFunc.hasValue(bizMsg.xxOrdItemSrchTxt)) {
                mndtyChkFlg = true;
            }

            // Warehouse
            if (ZYPCommonFunc.hasValue(bizMsg.xxRtlWhSrchTxt)) {
                mndtyChkFlg = true;
            }

            // Sub Warehouse
            if (ZYPCommonFunc.hasValue(bizMsg.xxRtlSwhSrchTxt)) {
                mndtyChkFlg = true;
            }

            // Source Type
            if (ZYPCommonFunc.hasValue(bizMsg.xxCpoSrcTpSrchTxt)) {
                mndtyChkFlg = true;
            }

            // Line Source Ref
            if (ZYPCommonFunc.hasValue(bizMsg.xxOrdSrcRefNumSrchTxt)) {
                mndtyChkFlg = true;
            }

            // Substitute Item
            if (ZYPCommonFunc.hasValue(bizMsg.xxSbstItemSrchTxt)) {
                mndtyChkFlg = true;
            }

            // Serial Number
            if (ZYPCommonFunc.hasValue(bizMsg.xxSerNumSrchTxt)) {
                mndtyChkFlg = true;
            }
        } else {
            // RMA Line Level
            // (RMA)Line Number
            if (ZYPCommonFunc.hasValue(bizMsg.xxLineNum_R)) {
                mndtyChkFlg = true;
            }

            // (RMA)Line Status
            if (ZYPCommonFunc.hasValue(bizMsg.xxLineStsSrchTxt_R)) {
                mndtyChkFlg = true;
            }

            // (RMA)Ordered Item
            if (ZYPCommonFunc.hasValue(bizMsg.xxOrdItemSrchTxt_R)) {
                mndtyChkFlg = true;
            }

            // (RMA)Return Reason
            if (ZYPCommonFunc.hasValue(bizMsg.xxRtrnRsnSrchTxt_R)) {
                mndtyChkFlg = true;
            }

            // (RMA)Warehouse
            if (ZYPCommonFunc.hasValue(bizMsg.xxRtlWhSrchTxt_R)) {
                mndtyChkFlg = true;
            }

            // (RMA)Sub Warehouse
            if (ZYPCommonFunc.hasValue(bizMsg.xxRtlSwhSrchTxt_R)) {
                mndtyChkFlg = true;
            }

            // (RMA)Line Source Ref
            if (ZYPCommonFunc.hasValue(bizMsg.xxOrdSrcRefNumSrchTxt_R)) {
                mndtyChkFlg = true;
            }

            // (RMA)Serial Number
            if (ZYPCommonFunc.hasValue(bizMsg.xxSerNumSrchTxt_R)) {
                mndtyChkFlg = true;
            }
        }

        if (!mndtyChkFlg) {
            bizMsg.setMessageInfo(NWAM0001E);
        }

        return mndtyChkFlg;
    }
        /**
     * Exist Check
     * @param bizMsg NWAL1890CMsg
     */
    public static boolean existsChk(NWAL1890CMsg bizMsg) {

        S21SsmEZDResult ssmResult;

        boolean chkFlg = true;

        // Config Level
        // Config Number
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum)) {
            ssmResult = NWAL1890Query.getInstance().getConfigNum(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.dsOrdPosnNum.setErrorInfo(1, NWAM0938E, new String[] {"Config Number" });
                chkFlg = false;
            }
        }

        // Config ID
        if (ZYPCommonFunc.hasValue(bizMsg.xxConfigIdSrchTxt)) {
            ssmResult = NWAL1890Query.getInstance().getConfigID(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.xxConfigIdSrchTxt.setErrorInfo(1, NWAM0938E, new String[] {"Config ID" });
                chkFlg = false;
            }
        }

        // Model
        if (ZYPCommonFunc.hasValue(bizMsg.xxMdlSrchTxt)) {
            String[] mdlNm = null;
            String strMdlNm = null;

            strMdlNm = bizMsg.xxMdlSrchTxt.getValue();
            mdlNm = strMdlNm.split(";");

            for (int i = 0; i < mdlNm.length; i++) {
                String mdlParam = mdlNm[i];
                ssmResult = NWAL1890Query.getInstance().getModel(bizMsg, mdlParam);
                if (ssmResult.isCodeNotFound()) {
                    bizMsg.xxMdlSrchTxt.setErrorInfo(1, NWAM0507E, new String[] {mdlNm[i]});
                    chkFlg = false;
                    break;
                }
            }
        }

        // Bill To Cust Name
        if (ZYPCommonFunc.hasValue(bizMsg.xxBillToAcctNmSrchTxt)) {
            ssmResult = NWAL1890Query.getInstance().getBillToCustNm(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.xxBillToAcctNmSrchTxt.setErrorInfo(1, NWAM0938E, new String[] {"Bill To Cust Name" });
                chkFlg = false;
            }
        }

        // Bill To Cust Code
        if (ZYPCommonFunc.hasValue(bizMsg.xxBillToAcctCdSrchTxt)) {
            ssmResult = NWAL1890Query.getInstance().getBillToCustCd(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.xxBillToAcctCdSrchTxt.setErrorInfo(1, NWAM0938E, new String[] {"Bill To Account Number" });
                chkFlg = false;
            }
        }

        // Bill To Location Code
        if (ZYPCommonFunc.hasValue(bizMsg.xxBillToLocCdSrchTxt)) {
            ssmResult = NWAL1890Query.getInstance().getBillToLocCd(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.xxBillToLocCdSrchTxt.setErrorInfo(1, NWAM0938E, new String[] {"Location#" });
                chkFlg = false;
            }
        }

        // Ship To Cust Name
        if (ZYPCommonFunc.hasValue(bizMsg.xxShipToAcctNmSrchTxt)) {
            ssmResult = NWAL1890Query.getInstance().getShipToCustNm(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.xxShipToAcctNmSrchTxt.setErrorInfo(1, NWAM0938E, new String[] {"Ship To Cust Name" });
                chkFlg = false;
            }
        }

        // Ship To Cust Code
        if (ZYPCommonFunc.hasValue(bizMsg.xxShipToAcctCdSrchTxt)) {
            ssmResult = NWAL1890Query.getInstance().getShipToCustCd(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.xxShipToAcctCdSrchTxt.setErrorInfo(1, NWAM0938E, new String[] {"Ship To Account Number" });
                chkFlg = false;
            }
        }

        // Ship To Location Code
        if (ZYPCommonFunc.hasValue(bizMsg.xxShipToLocCdSrchTxt)) {
            ssmResult = NWAL1890Query.getInstance().getShipToLocCd(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.xxShipToLocCdSrchTxt.setErrorInfo(1, NWAM0938E, new String[] {"Location#" });
                chkFlg = false;
            }
        }

        // Sold To Cust Name
        if (ZYPCommonFunc.hasValue(bizMsg.xxSoldToAcctNmSrchTxt)) {
            ssmResult = NWAL1890Query.getInstance().getSoldToCustNm(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.xxSoldToAcctNmSrchTxt.setErrorInfo(1, NWAM0938E, new String[] {"Sold To Cust Name" });
                chkFlg = false;
            }
        }

        // Sold To Cust Code
        if (ZYPCommonFunc.hasValue(bizMsg.xxSoldToAcctCdSrchTxt)) {
            ssmResult = NWAL1890Query.getInstance().getSoldToCustCd(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.xxSoldToAcctCdSrchTxt.setErrorInfo(1, NWAM0938E, new String[] {"Sold To Account Number" });
                chkFlg = false;
            }
        }

        // Sold To Location Code
        if (ZYPCommonFunc.hasValue(bizMsg.xxSoldToLocCdSrchTxt)) {
            ssmResult = NWAL1890Query.getInstance().getSoldToLocCd(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.xxSoldToLocCdSrchTxt.setErrorInfo(1, NWAM0938E, new String[] {"Location#" });
                chkFlg = false;
            }
        }

        if (LINE_CONFIG_MODE.equals(bizMsg.xxModeCd.getValue())) {
            // Line Level
            // Line Number
            if (ZYPCommonFunc.hasValue(bizMsg.xxLineNum)) {
                ssmResult = NWAL1890Query.getInstance().getLineNum(bizMsg);
                if (ssmResult.isCodeNotFound()) {
                    bizMsg.xxLineNum.setErrorInfo(1, NWAM0938E, new String[] {"Line Number" });
                    chkFlg = false;
                }
            }

            // Line Status
            if (ZYPCommonFunc.hasValue(bizMsg.xxLineStsSrchTxt)) {
                String[] lineSts = null;
                String strLineSts = null;

                strLineSts = bizMsg.xxLineStsSrchTxt.getValue();
                lineSts = strLineSts.split(";");

                for (int i = 0; i < lineSts.length; i++) {
                    String lineStsParam = lineSts[i];
                    ssmResult = NWAL1890Query.getInstance().getLineSts(bizMsg, lineStsParam);
                    if (ssmResult.isCodeNotFound()) {
                        bizMsg.xxLineStsSrchTxt.setErrorInfo(1, NWAM0507E, new String[] {lineSts[i]});
                        chkFlg = false;
                        break;
                    }
                }
            }

            // Ordered Item
            if (ZYPCommonFunc.hasValue(bizMsg.xxOrdItemSrchTxt)) {
                String[] ordItem = null;
                String strOrdItem = null;

                strOrdItem = bizMsg.xxOrdItemSrchTxt.getValue();
                ordItem = strOrdItem.split(";");

                for (int i = 0; i < ordItem.length; i++) {
                    String ordItemParam = ordItem[i];
                    ssmResult = NWAL1890Query.getInstance().getOrdItem(bizMsg, ordItemParam);
                    if (ssmResult.isCodeNotFound()) {
                        bizMsg.xxOrdItemSrchTxt.setErrorInfo(1, NWAM0507E, new String[] {ordItem[i]});
                        chkFlg = false;
                        break;
                    }
                }
            }

            // Warehouse
            if (ZYPCommonFunc.hasValue(bizMsg.xxRtlWhSrchTxt)) {
                ssmResult = NWAL1890Query.getInstance().getWh(bizMsg);
                if (ssmResult.isCodeNotFound()) {
                    bizMsg.xxRtlWhSrchTxt.setErrorInfo(1, NWAM0938E, new String[] {"Warehouse" });
                    chkFlg = false;
                }
            }

            // Sub Warehouse
            if (ZYPCommonFunc.hasValue(bizMsg.xxRtlSwhSrchTxt)) {
                String[] swhNm = null;
                String strSwhNm = null;

                strSwhNm = bizMsg.xxRtlSwhSrchTxt.getValue();
                swhNm = strSwhNm.split(";");

                for (int i = 0; i < swhNm.length; i++) {
                    String swhNmParam = swhNm[i];
                    ssmResult = NWAL1890Query.getInstance().getSwh(bizMsg, swhNmParam);
                    if (ssmResult.isCodeNotFound()) {
                        bizMsg.xxRtlSwhSrchTxt.setErrorInfo(1, NWAM0507E, new String[] {swhNm[i]});
                        chkFlg = false;
                        break;
                    }
                }
            }

            // Source Type
            if (ZYPCommonFunc.hasValue(bizMsg.xxCpoSrcTpSrchTxt)) {
                ssmResult = NWAL1890Query.getInstance().getSrcType(bizMsg);
                if (ssmResult.isCodeNotFound()) {
                    bizMsg.xxCpoSrcTpSrchTxt.setErrorInfo(1, NWAM0938E, new String[] {"Source Type" });
                    chkFlg = false;
                }
            }

            // Line Source Ref
            if (ZYPCommonFunc.hasValue(bizMsg.xxOrdSrcRefNumSrchTxt)) {
                ssmResult = NWAL1890Query.getInstance().getLineSrcRef(bizMsg);
                if (ssmResult.isCodeNotFound()) {
                    bizMsg.xxOrdSrcRefNumSrchTxt.setErrorInfo(1, NWAM0938E, new String[] {"Line Source Ref" });
                    chkFlg = false;
                }
            }

            // Substitute Item
            if (ZYPCommonFunc.hasValue(bizMsg.xxSbstItemSrchTxt)) {
                ssmResult = NWAL1890Query.getInstance().getSbstItem(bizMsg);
                if (ssmResult.isCodeNotFound()) {
                    bizMsg.xxSbstItemSrchTxt.setErrorInfo(1, NWAM0938E, new String[] {"Substitute Item" });
                    chkFlg = false;
                }
            }

            // Serial Number
            if (ZYPCommonFunc.hasValue(bizMsg.xxSerNumSrchTxt)) {
                ssmResult = NWAL1890Query.getInstance().getSerNum(bizMsg);
                if (ssmResult.isCodeNotFound()) {
                    bizMsg.xxSerNumSrchTxt.setErrorInfo(1, NWAM0938E, new String[] {"Serial#" });
                    chkFlg = false;
                }
            }
        } else {
            // RMA Line Level
            // (RMA)Line Number
            if (ZYPCommonFunc.hasValue(bizMsg.xxLineNum_R)) {
                ssmResult = NWAL1890Query.getInstance().getRMALineNum(bizMsg);
                if (ssmResult.isCodeNotFound()) {
                    bizMsg.xxLineNum_R.setErrorInfo(1, NWAM0938E, new String[] {"Line Number" });
                    chkFlg = false;
                }
            }

            // (RMA)Line Status
            if (ZYPCommonFunc.hasValue(bizMsg.xxLineStsSrchTxt_R)) {
                String[] rmaLineSts = null;
                String strRmaLineSts = null;

                strRmaLineSts = bizMsg.xxLineStsSrchTxt_R.getValue();
                rmaLineSts = strRmaLineSts.split(";");

                for (int i = 0; i < rmaLineSts.length; i++) {
                    String rmaLineStsParam = rmaLineSts[i];
                    ssmResult = NWAL1890Query.getInstance().getRMALineSts(bizMsg, rmaLineStsParam);
                    if (ssmResult.isCodeNotFound()) {
                        bizMsg.xxLineStsSrchTxt_R.setErrorInfo(1, NWAM0507E, new String[] {rmaLineSts[i]});
                        chkFlg = false;
                        break;
                    }
                }
            }

            // (RMA)Ordered Item
            if (ZYPCommonFunc.hasValue(bizMsg.xxOrdItemSrchTxt_R)) {
                String[] rmaOrdItem = null;
                String strRmaOrdItem = null;

                strRmaOrdItem = bizMsg.xxOrdItemSrchTxt_R.getValue();
                rmaOrdItem = strRmaOrdItem.split(";");

                for (int i = 0; i < rmaOrdItem.length; i++) {
                    String rmaOrdItemParam = rmaOrdItem[i];
                    ssmResult = NWAL1890Query.getInstance().getRMAOrdItem(bizMsg, rmaOrdItemParam);
                    if (ssmResult.isCodeNotFound()) {
                        bizMsg.xxOrdItemSrchTxt_R.setErrorInfo(1, NWAM0507E, new String[] {rmaOrdItem[i]});
                        chkFlg = false;
                        break;
                    }
                }
            }

            // (RMA)Return Reason
            if (ZYPCommonFunc.hasValue(bizMsg.xxRtrnRsnSrchTxt_R)) {
                ssmResult = NWAL1890Query.getInstance().getRMARtrnRsn(bizMsg);
                if (ssmResult.isCodeNotFound()) {
                    bizMsg.xxRtrnRsnSrchTxt_R.setErrorInfo(1, NWAM0938E, new String[] {"Return Reason" });
                    chkFlg = false;
                }
            }

            // (RMA)Warehouse
            if (ZYPCommonFunc.hasValue(bizMsg.xxRtlWhSrchTxt_R)) {
                ssmResult = NWAL1890Query.getInstance().getRMAWh(bizMsg);
                if (ssmResult.isCodeNotFound()) {
                    bizMsg.xxRtlWhSrchTxt_R.setErrorInfo(1, NWAM0938E, new String[] {"Warehouse" });
                    chkFlg = false;
                }
            }

            // (RMA)Sub Warehouse
            if (ZYPCommonFunc.hasValue(bizMsg.xxRtlSwhSrchTxt_R)) {
                String[] rmaSwhNm = null;
                String strRmaSwhNm = null;

                strRmaSwhNm = bizMsg.xxRtlSwhSrchTxt_R.getValue();
                rmaSwhNm = strRmaSwhNm.split(";");

                for (int i = 0; i < rmaSwhNm.length; i++) {
                    String rmaSwhNmParam = rmaSwhNm[i];
                    ssmResult = NWAL1890Query.getInstance().getRMASwh(bizMsg, rmaSwhNmParam);
                    if (ssmResult.isCodeNotFound()) {
                        bizMsg.xxRtlSwhSrchTxt_R.setErrorInfo(1, NWAM0507E, new String[] {rmaSwhNm[i]});
                        chkFlg = false;
                        break;
                    }
                }
            }

            // (RMA)Line Source Ref
            if (ZYPCommonFunc.hasValue(bizMsg.xxOrdSrcRefNumSrchTxt_R)) {
                ssmResult = NWAL1890Query.getInstance().getRMALineSrcRef(bizMsg);
                if (ssmResult.isCodeNotFound()) {
                    bizMsg.xxOrdSrcRefNumSrchTxt_R.setErrorInfo(1, NWAM0938E, new String[] {"Line Source Ref" });
                    chkFlg = false;
                }
            }

            // (RMA)Serial Number
            if (ZYPCommonFunc.hasValue(bizMsg.xxSerNumSrchTxt_R)) {
                ssmResult = NWAL1890Query.getInstance().getRMASerNum(bizMsg);
                if (ssmResult.isCodeNotFound()) {
                    bizMsg.xxSerNumSrchTxt_R.setErrorInfo(1, NWAM0938E, new String[] {"Serial#" });
                    chkFlg = false;
                }
            }
        }

        return chkFlg;
    }
}
