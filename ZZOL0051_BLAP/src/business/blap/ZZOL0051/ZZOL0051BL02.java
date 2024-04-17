package business.blap.ZZOL0051;

import java.util.Iterator;

import parts.common.EZDCMsg;
import parts.common.EZDFMsg;
import parts.common.EZDItemAttrDefines;
import parts.common.EZDItemAttribute;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.ZZOL0051.ZZOL0051CMsg;
import business.blap.ZZOL0051.ZZOL0051SMsg;
import business.blap.ZZOL0051.common.ZZOL0051CommonLogic;
import business.blap.ZZOL0051.constant.ZZOL0051Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZOL0051BL02 extends S21BusinessHandler implements ZZOL0051Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZOL0051_INIT".equals(screenAplID)) {
                doProcess_ZZOL0051_INIT((ZZOL0051CMsg) cMsg, (ZZOL0051SMsg) sMsg);

            } else if ("ZZOL0051Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZOL0051Scrn00_Search((ZZOL0051CMsg) cMsg, (ZZOL0051SMsg) sMsg);

            } else if ("ZZOL0051Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_ZZOL0051Scrn00_CMN_Submit((ZZOL0051CMsg) cMsg, (ZZOL0051SMsg) sMsg);

            } else if ("ZZOL0051Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_ZZOL0051Scrn00_CMN_Delete((ZZOL0051CMsg) cMsg, (ZZOL0051SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    
    /**
     * initialization processing
     * @param cMsg EZDCMsg
     */
    private void doProcess_ZZOL0051_INIT(ZZOL0051CMsg cMsg, ZZOL0051SMsg sMsg) {
        
        S21SsmEZDResult ssmResult = null;

        // EZDSMsg Initialization
        sMsg.clear();
        sMsg.H.clear();
        sMsg.H.setValidCount(0);
        sMsg.B.clear();
        sMsg.B.setValidCount(0);
        sMsg.P.clear();
        sMsg.P.setValidCount(0);

        // upldCsvId null check
        if ( cMsg.upldCsvId.getValue().length() == 0 ) {
            return;
        }

        // UPLD_CSV_MSTR search
        ssmResult = ZZOL0051Query.getInstance().getCsvMstr(cMsg, sMsg);
        if ( ssmResult.isCodeNormal() ) {
            String tab = cMsg.xxDplyTab.getValue();
            EZDMsg.copy(sMsg, null, cMsg, null);
            cMsg.xxDplyTab.setValue(tab);
        }

        // UPLD_CSV_HDR search
        ssmResult = ZZOL0051Query.getInstance().getCsvHeaderList(cMsg, sMsg);
        if ( ssmResult.isCodeNormal() ) {
            EZDMsg.copy(sMsg.H, null, cMsg.H, null);
            
            // get EZDFMsg
            EZDFMsg fMsg = ZZOL0051CommonLogic.getEZDFMsg( cMsg.upldCsvFileId.getValue() );
            if ( fMsg == null ) {
                cMsg.upldCsvFileId.setErrorInfo(1, "ZZZM9006E", new String[] { "File ID" });
                return;
            }

            // get attribute of EZDFMsg item
            String clnNm = null;
            int idx = 0;
            for (Iterator it = fMsg.keysIterator(); it.hasNext();) {
                EZDItemAttribute att = fMsg.getAttr( (String) it.next() );
                if ( att.isArray() ) {
                    continue;
                }
                clnNm = att.getMessage();
                if ( clnNm == null ) {
                    clnNm = "";
                }
                // set CSV header name 
                cMsg.H.no(idx).upldCsvHdrNm_H.setValue( clnNm );
                idx++;            
            }            
        }
        
        // UPLD_CSV_RST_BIZ_APP_ID search
        ssmResult = ZZOL0051Query.getInstance().getCsvAppIdList(cMsg, sMsg);
        if ( ssmResult.isCodeNormal() ) {
            EZDMsg.copy(sMsg.B, null, cMsg.B, null);
        }

        // UPLD_CSV_RST_PROC search
        ssmResult = ZZOL0051Query.getInstance().getCsvProcIdList(cMsg, sMsg);
        if ( ssmResult.isCodeNormal() ) {
            EZDMsg.copy(sMsg.P, null, cMsg.P, null);
        }

    }

    /**
     * search processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZOL0051Scrn00_Search(ZZOL0051CMsg cMsg, ZZOL0051SMsg sMsg) {

        // get Upload CSV File Id
        String upldCsvFileId = cMsg.upldCsvFileId.getValue();
        
        // get EZDFMsg
        EZDFMsg fMsg = ZZOL0051CommonLogic.getEZDFMsg(upldCsvFileId);
        if ( fMsg == null ) {
            cMsg.upldCsvFileId.setErrorInfo(1, "ZZZM9006E", new String[] { "File ID" });
            return;
        }
        sMsg.upldCsvFileId.setValue( upldCsvFileId );
        sMsg.upldCsvFileClsNm.setValue( fMsg.getClass().getName() );
        
        // data clear
        cMsg.H.clear();
        cMsg.H.setValidCount(0);

        // get attribute of EZDFMsg item
        String clnNm = null;
        String dataTpNm = null;
        String digit = null;
        int idx = 0;
        for (Iterator it = fMsg.keysIterator(); it.hasNext();) {
            EZDItemAttribute att = fMsg.getAttr( (String) it.next() );
            
            if ( att.isArray() ) {
                continue;
            }
            
            clnNm = att.getMessage();
            if ( clnNm == null ) {
                clnNm = "";
            }
            
            digit = Integer.toString( att.getDigit() );
            switch (att.getType()) {
                case EZDItemAttrDefines.TYPE_SEISU_SYOSU:// = 10;小数付き数値
                    dataTpNm = "Numbers";
                    if ( att.getFracDigit() > 0 ) {
                        digit += "," + Integer.toString(att.getFracDigit());
                    }
                    break;
                case EZDItemAttrDefines.TYPE_HANKAKUEISU:// = 32;半角英数字
                    dataTpNm = "Alphabet Numeric Character";
                    break;
                case EZDItemAttrDefines.TYPE_HANKAKUEIJI:// = 33;半角英字
                    dataTpNm = "Alphabet Character";
                    break;
                case EZDItemAttrDefines.TYPE_HANKAKUSUJI:// = 34;半角数字
                    dataTpNm = "Numeric Character";
                    break;
                case EZDItemAttrDefines.TYPE_KONZAI:// = 40;全半角混在文字
                    dataTpNm = "National Language Character";
                    break;
                case EZDItemAttrDefines.TYPE_NENTSUKIHI:// = 50;日付(年月日)
                    dataTpNm = "Date (Year Month Day)";
                    break;
                case EZDItemAttrDefines.TYPE_NENTSUKI:// = 51;日付(年月)
                    dataTpNm = "Date (Year Month)";
                    break;
                case EZDItemAttrDefines.TYPE_NEN:// = 52;日付(年月)
                    dataTpNm = "Date (Year)";
                    break;
                default:
                    dataTpNm = "Unknown";
            }

            // set
            cMsg.H.no(idx).upldCsvHdrNum_H.setValue( idx + 1);
            cMsg.H.no(idx).upldCsvHdrNm_H.setValue( clnNm );
            cMsg.H.no(idx).upldCsvHdrDataTpNm_H.setValue( dataTpNm );
            cMsg.H.no(idx).upldCsvHdrDataLg_H.setValue( digit );

            idx++;            
        }
        cMsg.H.setValidCount(idx);
    }

    /**
     * submit processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZOL0051Scrn00_CMN_Submit(ZZOL0051CMsg cMsg, ZZOL0051SMsg sMsg) {
        
        if ( "E".equals( cMsg.getMessageKind() ) ) {
            return;
        }
        doProcess_ZZOL0051_INIT(cMsg, sMsg);
    }

    /**
     * delete processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZOL0051Scrn00_CMN_Delete(ZZOL0051CMsg cMsg, ZZOL0051SMsg sMsg) {
        
        if ( "E".equals( cMsg.getMessageKind() ) ) {
            return;
        }
        
        String curttab = cMsg.xxDplyTab.getValue();

        if ( curttab.equals( TAB_BIZAPP ) ) {

            // UPLD_CSV_RST_BIZ_APP_ID search
            ZZOL0051Query.getInstance().getCsvAppIdList(cMsg, sMsg);
            reArrAppMsg(cMsg);
        } else if ( curttab.equals( TAB_PROCID ) ) {

            // UPLD_CSV_RST_PROC search
            ZZOL0051Query.getInstance().getCsvProcIdList(cMsg, sMsg);
            reArrProcMsg(cMsg);
        }
    }
    
    /**
     * rearrangement for Application ID
     * @param cMsg EZDCMsg
     */
    private void reArrAppMsg(ZZOL0051CMsg cMsg) {
        
        // remove a appointed row in checkbox
        ZZOL0051CMsg tmpMsg = new ZZOL0051CMsg();
        int cnt = 0;

        for (int idx = 0; idx < cMsg.B.getValidCount(); idx++) {
            
            if ( cMsg.B.no(idx).xxChkBox_B.getValue().length() > 0 ) {
                continue;
            }
            EZDMsg.copy(cMsg.B.no(idx), null, tmpMsg.B.no(cnt), null);
            tmpMsg.B.no(cnt).xxNum_B.setValue(cnt + 1);
            cnt++;
        }
        tmpMsg.B.setValidCount(cnt);
        cMsg.B.clear();
        cMsg.B.setValidCount(0);
        
        EZDMsg.copy(tmpMsg.B, null, cMsg.B, null);
        cMsg.B.setValidCount(cnt);
    }

    /**
     * rearrangement for Process ID
     * @param cMsg EZDCMsg
     */
    private void reArrProcMsg(ZZOL0051CMsg cMsg) {
        
        // remove a appointed row in checkbox
        ZZOL0051CMsg tmpMsg = new ZZOL0051CMsg();
        int cnt = 0;
        
        for (int idx = 0; idx < cMsg.P.getValidCount(); idx++) {
            
            if ( cMsg.P.no(idx).xxChkBox_P.getValue().length() > 0 ) {
                continue;
            }
            EZDMsg.copy(cMsg.P.no(idx), null, tmpMsg.P.no(cnt), null);
            tmpMsg.P.no(cnt).xxNum_P.setValue(cnt + 1);
            cnt++;
        }
        tmpMsg.P.setValidCount(cnt);
        cMsg.P.clear();
        cMsg.P.setValidCount(0);
        
        EZDMsg.copy(tmpMsg.P, null, cMsg.P, null);
        cMsg.P.setValidCount(cnt);
    }

}
