package business.blap.ZZML0071.common;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.ZZML0071.ZZML0071CMsg;
import business.blap.ZZML0071.ZZML0071Query;
import business.blap.ZZML0071.ZZML0071SMsg;
import business.blap.ZZML0071.constant.ZZML0071Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;


/**
 * @author Q02673
 */
public class ZZML0071CommonLogic {

    /**
     * @param cMsg ZZML0071CMsg
     * @param sMsg ZZML0071SMsg
     */
    public static void searchZZML0071(ZZML0071CMsg cMsg, ZZML0071SMsg sMsg) {

        if ( sMsg.glblCmpyCd.getValue().length() == 0 ) {
            sMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        }
        
        Map<String, String> cond = new HashMap<String, String>();
        cond.put("GLBL_CMPY_CD", sMsg.glblCmpyCd.getValue());
        
        int j = 0;
        for (ZZML0071Constant.Language langCd : ZZML0071Constant.Language.values()) {
            // START 2013/11/12 M.Sumida Mod from language only to locale(lang + country)
            // cMsg.langCd_L1.no(j).setValue(langCd.toString());
            cMsg.mlUsrLocId_L1.no(j).setValue(langCd.toString());
            // END   2013/11/12 M.Sumida Mod from language only to locale(lang + country)
            cMsg.langNm_L1.no(j).setValue(langCd.getLangName());
            j++;
        }
        
//        LANGTMsgArray langArray = (LANGTMsgArray) ZYPCodeDataUtil.findByCondition("LANG", cond);
//        if ( langArray.length() == 0 ) {
//            cMsg.setMessageInfo("ZZZM9006E", new String[] {"LANG table data" });
//        }

//        for (int i = 0; i < langArray.length(); i++) {
//            cMsg.langCd_L1.no(i).setValue(langArray.no(i).langCd.getValue());
//            cMsg.langNm_L1.no(i).setValue(langArray.no(i).langNm.getValue());
//        }

        if ( cMsg.A.no(0).mlUsrAddrPk_A.getValue() != null ) {
            S21SsmEZDResult ssmResult = ZZML0071Query.getInstance().getMlUsrAddr(cMsg, sMsg);

            if (!ssmResult.isCodeNormal()) {
                if (!"E".equals(cMsg.getMessageKind())) {
                    cMsg.setMessageInfo("ZZZM9001E", null);
                }
            } else {
                if (ssmResult.getQueryResultCount() > sMsg.A.length()) {
                    cMsg.setMessageInfo("ZZZM9002W");
                }

                int i = 0;
                for (; i < sMsg.A.getValidCount(); i++) {
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                }
                cMsg.A.setValidCount(i);

                cMsg.mlUsrId.setValue(      sMsg.A.no(0).mlUsrId_A.getValue() );            
                cMsg.mlUsrAddr.setValue(    sMsg.A.no(0).mlUsrAddr_A.getValue() );            
                cMsg.mlUsrNm.setValue(      sMsg.A.no(0).mlUsrNm_A.getValue() );            
                cMsg.mlUsrLocId.setValue(   sMsg.A.no(0).mlUsrLocId_A.getValue() );            
                cMsg.mlUsrDescTxt.setValue( sMsg.A.no(0).mlUsrDescTxt_A.getValue() );            
            }
        } else if (cMsg.A.no(0).mlGrpId_A.getValue() != null && cMsg.A.no(0).mlGrpId_A.getValue().toString().length() != 0) {
            S21SsmEZDResult ssmResult = ZZML0071Query.getInstance().getMlGrpAddr(cMsg, sMsg, 0);

            if (!ssmResult.isCodeNormal()) {
                cMsg.setMessageInfo("ZZZM9006E", new String[] {"ML_GRP_ADDR table data" });
                return;
            }
            
            Map rstMap = (Map) ssmResult.getResultObject();
            String mlGrpNm = (String)rstMap.get("ML_GRP_NM");
            if ( mlGrpNm == null ) {
                mlGrpNm = "";
            }
            cMsg.A.no(0).mlGrpNm_A.setValue(mlGrpNm);

            String mlGrpDescTxt = (String)rstMap.get("ML_GRP_NM");
            if ( mlGrpDescTxt == null ) {
                mlGrpDescTxt = "";
            }
            cMsg.A.no(0).mlGrpDescTxt_A.setValue(mlGrpDescTxt);
            // START 2013/11/12 M.Sumida Mod from language only to locale(lang + country)
            // cMsg.mlUsrLocId.setValue(cMsg.langCd_L1.no(0).getValue());
            cMsg.mlUsrLocId.setValue(cMsg.mlUsrLocId_L1.no(0).getValue());
            // END   2013/11/12 M.Sumida Mod from language only to locale(lang + country)
            cMsg.A.setValidCount(ssmResult.getQueryResultCount());
        } else {
            // START 2013/11/12 M.Sumida Mod from language only to locale(lang + country)
            // cMsg.mlUsrLocId.setValue(cMsg.langCd_L1.no(0).getValue());
            cMsg.mlUsrLocId.setValue(cMsg.mlUsrLocId_L1.no(0).getValue());
            // END   2013/11/12 M.Sumida Mod from language only to locale(lang + country)
            return;
        }
    }
}
