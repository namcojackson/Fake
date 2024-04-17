package business.blap.NMAL0160.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NMAL0160.NMAL0160CMsg;
import business.blap.NMAL0160.NMAL0160Query;
import business.blap.NMAL0160.NMAL0160SMsg;
import business.blap.NMAL0160.constant.NMAL0160Constant;
import business.db.MDSE_CST_UPD_HIST_DTLTMsg;
import business.db.MDSE_CST_UPD_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtil;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 11/09/2016   Fujistu         R.Nakamura      Update          QC#2872
 * 2018/02/19   Hitachi         J.Kim           Update          QC#24259
 * 2020/01/28   Fujitsu         M.Ohno          Update          QC#55543
 * 2020/10/13   CITS            K.Ogino         Update          QC#57843
 * 2021/10/01   CITS            F.Deola         Update          QC#59242
 *</pre>
 */
public class NMAL0160CommonLogic implements NMAL0160Constant {
	@SuppressWarnings("unchecked")
	public static void setInitialCostTypePulldown(NMAL0160CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NMAL0160Query.getInstance().getCostTypeList(bizMsg, globalCompanyCode);
		if (result.isCodeNormal()) {
			List<Map<String, String>> list = (List<Map<String, String>>) result.getResultObject();
			if (list != null && list.size() > 0) {
				int i = 0;
				for (Map<String, String> map : list) {
					if (map != null && map.get("MDSE_CST_UPD_TP_CD") != null) {
				    	bizMsg.mdseCstUpdTpCd_L1.no(i).setValue((String) map.get("MDSE_CST_UPD_TP_CD"));
				    	bizMsg.mdseCstUpdTpNm_L1.no(i).setValue((String) map.get("MDSE_CST_UPD_TP_NM"));
				    	i++;
					}
				}
			}
		}
	}
	@SuppressWarnings("unchecked")
	public static void setInitialCostStatusPulldown(NMAL0160CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NMAL0160Query.getInstance().getCostStatusList(bizMsg, globalCompanyCode);
		if (result.isCodeNormal()) {
			List<Map<String, String>> list = (List<Map<String, String>>) result.getResultObject();
			if (list != null && list.size() > 0) {
				int i = 0;
				for (Map<String, String> map : list) {
					if (map != null && map.get("MDSE_CST_UPD_STS_CD") != null) {
				    	bizMsg.mdseCstUpdStsCd_AL.no(i).setValue((String) map.get("MDSE_CST_UPD_STS_CD"));
				    	bizMsg.mdseCstUpdStsNm_AL.no(i).setValue((String) map.get("MDSE_CST_UPD_STS_NM"));
				    	i++;
					}
				}
			}
		}
	}

    public static MDSE_CST_UPD_HIST_DTLTMsg findDetail(String glblCmpyCd, BigDecimal mdseCstUpdHistHdrPk, String mdseCd) {
    	MDSE_CST_UPD_HIST_DTLTMsg mdseCstUpdHistDtlTMsg = new MDSE_CST_UPD_HIST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.mdseCstUpdHistHdrPk, mdseCstUpdHistHdrPk);
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.mdseCd, mdseCd);
        return (MDSE_CST_UPD_HIST_DTLTMsg) S21CacheTBLAccessor.findByKey(mdseCstUpdHistDtlTMsg);
    }
    
    public static MDSE_CST_UPD_TPTMsg findMdseCstUpdTp(String glblCmpyCd, String mdseCstUpdTpCd) {
    	MDSE_CST_UPD_TPTMsg mdseCstUpdTpTMsg = new MDSE_CST_UPD_TPTMsg();
        ZYPEZDItemValueSetter.setValue(mdseCstUpdTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseCstUpdTpTMsg.mdseCstUpdTpCd, mdseCstUpdTpCd);
        return (MDSE_CST_UPD_TPTMsg) S21CacheTBLAccessor.findByKey(mdseCstUpdTpTMsg);
    }

    // QC#57843 Mod. Add invtySnapShotDt Parameter.
    public static void search(NMAL0160CMsg bizMsg, NMAL0160SMsg sMsg, String glblCmpyCd, String invtySnapShotDt) {
    	
		ZYPTableUtil.clear(bizMsg.A);
		ZYPTableUtil.clear(sMsg.A);
		ZYPTableUtil.clear(sMsg.B);
		// QC#57843 Mod. Add invtySnapShotDt Parameter.
		S21SsmEZDResult result = NMAL0160Query.getInstance().search(bizMsg, sMsg, glblCmpyCd, invtySnapShotDt);
		if (result.isCodeNormal()) {
		    // START 2018/02/19 J.Kim [QC#24259,ADD]
            BigDecimal invtyQty = BigDecimal.ZERO;
            BigDecimal estScTotCostAmt = BigDecimal.ZERO;
		    // END 2018/02/19 J.Kim [QC#24259,ADD]
			int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
            	//NZZM0001W=0,There are too many search results, there is data that cannot be displayed.
            	bizMsg.setMessageInfo("NZZM0001W");
                queryResCnt = sMsg.A.length();
            }

            int i = 0;
            int rowNum = 0;
            for (; i < queryResCnt; i++) {
                // START 2021/10/01 F.Deola [QC#59242, MOD]
                //if( i == bizMsg.A.length() ) {
                if (i < bizMsg.A.length()) {

                    //break;
                    // Add Start 2016/11/09 QC#2872
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(0).xxRecHistCratByNm_B1.getValue()));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(0).xxRecHistCratByNm_B1.getValue()));
                    // Add End 2016/11/09 QC#2872
                    EZDMsg.copy(sMsg.A.no(i), null, bizMsg.A.no(i), null);
                    rowNum++;
                }

                // END 2021/10/01 F.Deola [QC#59242, MOD]
                // START 2018/02/19 J.Kim [QC#24259,ADD]
                invtyQty = invtyQty.add(sMsg.A.no(i).invtyQty_A1.getValue());
                estScTotCostAmt = estScTotCostAmt.add(sMsg.A.no(i).xxEstScTotCostAmt_A1.getValue());
                // END 2018/02/19 J.Kim [QC#24259,ADD]
            }
            //Backup
            EZDMsg.copy(sMsg.A, "A1", sMsg.B, "B1");
            // START 2021/10/01 F.Deola [QC#59242, MOD]
            //bizMsg.A.setValidCount( i );
            bizMsg.A.setValidCount(rowNum);
            // END 2021/10/01 F.Deola [QC#59242, MOD]

            // START 2018/02/19 J.Kim [QC#24259,DEL]
            // S21SsmEZDResult sumResult = NMAL0160Query.getInstance().getResultSummary(bizMsg, glblCmpyCd);
            // if (result.isCodeNormal()) {
            //    // Add Start 2016/11/09 QC#2872
            //    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs, sMsg.A.no(0).xxRecHistCratTs_H1.getValue());
            //    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(0).xxRecHistCratByNm_H1.getValue()));
            //    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs, sMsg.A.no(0).xxRecHistUpdTs_H1.getValue());
            //    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(0).xxRecHistCratByNm_H1.getValue()));
            //    ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm, sMsg.A.no(0).xxRecHistTblNm_H1.getValue());
            //    // Add End 2016/11/09 QC#2872
            //    Map<String, Object> map = (Map<String, Object>) sumResult.getResultObject();
            //    if (map != null) {
            //        // Header
            //        bizMsg.xxPageShowTotNum_H1.setValue((BigDecimal) map.get("XX_PAGE_SHOW_TOT_NUM"));
            //        bizMsg.invtyQty_H1.setValue((BigDecimal) map.get("INVTY_QTY"));
            //        bizMsg.xxEstScTotCostAmt_H1.setValue((BigDecimal) map.get("XX_EST_SC_TOT_COST_AMT"));
            //    }
            // }
            // END 2018/02/19 J.Kim [QC#24259,DEL]

            // START 2018/02/19 J.Kim [QC#24259,ADD]
            // Header
            bizMsg.xxPageShowTotNum_H1.setValue(queryResCnt);
            bizMsg.invtyQty_H1.setValue(invtyQty);
            bizMsg.xxEstScTotCostAmt_H1.setValue(estScTotCostAmt);
            // END 2018/02/19 J.Kim [QC#24259,ADD]

            // Set page number
			bizMsg.xxPageShowFromNum_10.setValue( 1 );
			bizMsg.xxPageShowToNum_10.setValue( bizMsg.A.getValidCount() );
			bizMsg.xxPageShowOfNum_10.setValue( queryResCnt );
			
			//ZZM8100I=0,Process ended normally.
			bizMsg.setMessageInfo("ZZM8100I");
		} else {
			// Header
			bizMsg.xxPageShowTotNum_H1.clear();
			bizMsg.invtyQty_H1.clear();
			bizMsg.xxEstScTotCostAmt_H1.clear();
			// Add Start 2016/11/09 QC#2872
			bizMsg.xxRecHistCratTs.clear();
			bizMsg.xxRecHistCratByNm.clear();
			bizMsg.xxRecHistUpdTs.clear();
			bizMsg.xxRecHistUpdByNm.clear();
			bizMsg.xxRecHistTblNm.clear();
			// Add End 2016/11/09 QC#2872
			
            // Set page number
			bizMsg.xxPageShowFromNum_10.clear();
			bizMsg.xxPageShowToNum_10.clear();
			bizMsg.xxPageShowOfNum_10.clear();
			//ZZZM9005W=0,No search results found.
			bizMsg.setMessageInfo("ZZZM9005W");
		}
		
    }
    public static String nvlString(String arg) {
    	if (ZYPCommonFunc.hasValue(arg)) {
    		return arg;
    	}
    	return "";
    }
    public static BigDecimal nvlBigDecimal(BigDecimal arg) {
    	if (ZYPCommonFunc.hasValue(arg)) {
    		return arg;
    	}
    	return BigDecimal.ZERO;
    }

    public static void copyFromSMsgToCMsg(NMAL0160CMsg cMsg, NMAL0160SMsg sMsg, boolean flg) {
        // START 2021/10/01 F.Deola [QC#59242, MOD]
        //int currentPage = cMsg.xxPageShowCurNum_10.getValueInt();
        //int s              = (currentPage - 1) * cMsg.A.length();
        int s = cMsg.xxPageShowFromNum_10.getValueInt() - 1;
        // END 2021/10/01 F.Deola [QC#59242, MOD]
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no( i ) , null, sMsg.A.no( s + i ), null );
    	}
        if (flg) {
    		ZYPTableUtil.clear(cMsg.A);
        }
    }

    public static int transferErrorPage(NMAL0160CMsg bizMsg, NMAL0160SMsg sMsg, int i) {
       	//current page # = (Error line # + showable line # - 1) / showable line #
        // 2020/01/28 S21_NA#55543 Mod Start
//       	int transferPage = (i + bizMsg.A.length() - 1) / bizMsg.A.length();
        int transferPage = (i + bizMsg.A.length()) / bizMsg.A.length();
        // 2020/01/28 S21_NA#55543 Mod End
       	if (transferPage == 0) transferPage = 1;
       	int totPage = (sMsg.A.getValidCount() + bizMsg.A.length() - 1) / bizMsg.A.length();
       	//Copy from SMsg to CMsg
       	int s = (transferPage - 1) * bizMsg.A.length();
       	int start = s;
       	int e = transferPage * bizMsg.A.length() - 1;
		ZYPTableUtil.clear(bizMsg.A);
       	int k = 0;
       	int errorLineNo = 0;
       	for ( ; s <= e; s++) {
       		if (s < sMsg.A.getValidCount()) {
       			if (s == i) {
       				errorLineNo = k;
       			}
       			EZDMsg.copy( sMsg.A.no( s ), null, bizMsg.A.no( k ), null );
       			k++;
       		} else {
       			break;
       		}
       	}
       	//bizMsg.A.setValidCount( k + 1 );
       	bizMsg.A.setValidCount( k );
       	
       	//set Page
	    bizMsg.xxPageShowFromNum_10.setValue( start + 1 );
        bizMsg.xxPageShowToNum_10.setValue( e + 1 );
        bizMsg.xxPageShowOfNum_10.setValue( sMsg.A.getValidCount() );
        bizMsg.xxPageShowCurNum_10.setValue( transferPage );
        bizMsg.xxPageShowTotNum_10.setValue( totPage );

        /*
         * 1 page
		0,,xxPageShowFromNum_10,1
		0,,xxPageShowToNum_10,15
		0,,xxPageShowOfNum_10,37
		0,,xxPageShowCurNum_10,1
		0,,xxPageShowTotNum_10,3
		
		 * 2 page
		0,,xxPageShowFromNum_10,16
		0,,xxPageShowToNum_10,30
		0,,xxPageShowOfNum_10,37
		0,,xxPageShowCurNum_10,2
		0,,xxPageShowTotNum_10,3
		
         
         * 3 page
        0,,xxPageShowFromNum_10,31-->16
        0,,xxPageShowToNum_10,37-->30
        0,,xxPageShowOfNum_10,37-->37
        0,,xxPageShowCurNum_10,3-->2
        0,,xxPageShowTotNum_10,3-->3
		*/
        
        return errorLineNo;
    }

    // QC#57843 Add Get target date
    public static String getMaxSnapShotDt(NMAL0160CMsg bizMsg, String globalCompanyCode) {
        String invtySnapShotDt = ZYPDateUtil.getSalesDate();
        S21SsmEZDResult result = NMAL0160Query.getInstance().getMaxSnapShotDt(globalCompanyCode);
        if (result.isCodeNormal()) {
            invtySnapShotDt = (String) result.getResultObject();
        } else {
            String preSalseDate = S21CalendarUtil.addOpeBusinessDay(globalCompanyCode, CAL_TP.CSA_GENERAL, -1, S21CalendarUtilConstants.ONL_BAT_FLG_ONL_S21);
            if (ZYPCommonFunc.hasValue(preSalseDate)) {
                invtySnapShotDt = preSalseDate;
            }
        }
        return invtySnapShotDt;
    }
}
