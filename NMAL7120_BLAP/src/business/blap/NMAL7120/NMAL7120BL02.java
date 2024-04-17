package business.blap.NMAL7120;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import business.blap.NMAL7120.NMAL7120CMsg;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import business.blap.NMAL7120.common.NMAL7120CommonLogic;
import business.blap.NMAL7120.constant.NMAL7120Constant;
import business.file.NMAL7120F00FMsg;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 02/08/2016   FUJITSU         T.Murai         Update          #3006
 * 03/03/2016   SRAA            K.Aratani       Create          #5032
 * 07/20/2016   FUJITSU         W.Honda         Update          #9690
 * 09/12/2017   Fujitsu         K.Ishizuka      Update          QC#20206(Sol#269)
 * 07/11/2018   Fujitsu         M.Ishii         Update          QC#25557
 *</pre>
 */
public class NMAL7120BL02 extends S21BusinessHandler implements NMAL7120Constant {

	@Override
	protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
		super.preDoProcess(cMsg, sMsg);
		NMAL7120CMsg bizMsg = (NMAL7120CMsg) cMsg;
		String scrnAplID = bizMsg.getScreenAplID();
        try {
        	if ("NMAL7120_INIT".equals(scrnAplID)) {
        		doProcess_NMAL7120_INIT(bizMsg, (NMAL7120SMsg) sMsg);
        	} else if ("NMAL7120Scrn00_Search".equals(scrnAplID)) {
        		doProcess_NMAL7120_Search(bizMsg, (NMAL7120SMsg) sMsg);
            } else if ("NMAL7120Scrn00_TBLColumnSort".equals(scrnAplID)) {
            	doProcess_NMAL7120_TBLColumnSort(bizMsg, (NMAL7120SMsg) sMsg);
        	} else if ("NMAL7120Scrn00_Clear_Search".equals(scrnAplID)) {
        		doProcess_NMAL7120_Clear_Search(bizMsg, (NMAL7120SMsg) sMsg);
        	} else if ("NMAL7120Scrn00_Add".equals(scrnAplID)) {
        		doProcess_NMAL7120_Add(bizMsg, (NMAL7120SMsg) sMsg, null);
        	} else if ("NMAL7120Scrn00_Del".equals(scrnAplID)) {
        		doProcess_NMAL7120_Del(bizMsg, (NMAL7120SMsg) sMsg);
        	} else if ("NMAL7120Scrn00_CMN_Clear".equals(scrnAplID)) {
        		doProcess_NMAL7120_CLEAR(bizMsg, (NMAL7120SMsg) sMsg);
        	} else if ("NMAL7120Scrn00_CMN_Reset".equals(scrnAplID)) {
        		doProcess_NMAL7120_RESET(bizMsg, (NMAL7120SMsg) sMsg);
        	} else if ("NMAL7120Scrn00_Mass_Update_CSMP".equals(scrnAplID)) {
        		doProcess_NMAL7120_Mass_Update_CSMP(bizMsg, (NMAL7120SMsg) sMsg);
        	} else if ("NMAL7120Scrn00_Click_SelAll_UnSelAll_Checkbox".equals(scrnAplID)) {
        		doProcess_NMAL7120_Click_SelAll_UnSelAll_Checkbox(bizMsg, (NMAL7120SMsg) sMsg);
        	} else if ("NMAL7120Scrn00_CMN_Download".equals(scrnAplID)) {
        		doProcess_NMAL7120_DOWNLOAD(bizMsg, (NMAL7120SMsg) sMsg);
        	} else if ("NMAL7120Scrn00_PageNext".equals(scrnAplID)) { // QC#20206(L3#269) Add Start
                doProcess_NMAL7120_PageNext(bizMsg, (NMAL7120SMsg) sMsg);
            } else if ("NMAL7120Scrn00_PagePrev".equals(scrnAplID)) {
                doProcess_NMAL7120_PagePrev(bizMsg, (NMAL7120SMsg) sMsg);
            } else if ("NMAL7120Scrn00_PageJump".equals(scrnAplID)) {
                doProcess_NMAL7120_PageJump(bizMsg, (NMAL7120SMsg) sMsg); // QC#20206(L3#269) Add END
            }
        } finally {
        	super.postDoProcess(cMsg, sMsg);
        }
	}
	
	private void doProcess_NMAL7120_INIT(NMAL7120CMsg bizMsg, NMAL7120SMsg sMsg) {

	    BigDecimal srchFetchSize = NMAL7120CommonLogic.getSrchMaxSize(bizMsg, getGlobalCompanyCode());
        BigDecimal dplyFetchSize = NMAL7120CommonLogic.getDplyMaxSize(bizMsg, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlCnt_H1, srchFetchSize);// Search Max
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlCnt_H2, dplyFetchSize);// Detail Max

        ZYPTableUtil.clear(sMsg.A); //S21_NA ADD QC#20206(L3#269)
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.C); //S21_NA ADD QC#20206(L3#269)
        ZYPTableUtil.clear(bizMsg.A);
    	//Status
        bizMsg.csmpContrActvFlg_L1.clear();
        bizMsg.xxWfProcStsNm_L1.clear();
        bizMsg.csmpContrActvFlg_L2.clear();
        bizMsg.xxWfProcStsNm_L2.clear();
        NMAL7120CommonLogic.setInitialStatusPulldown(bizMsg, getGlobalCompanyCode());
        
        if (ZYPCommonFunc.hasValue(bizMsg.prcContrNum_H1)) {
            //Search
            NMAL7120CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode());
            //Add row
            doProcess_NMAL7120_Add(bizMsg, sMsg, bizMsg.prcContrNum_H1.getValue());
        }
        
	}
	
	private void doProcess_NMAL7120_Search(NMAL7120CMsg bizMsg, NMAL7120SMsg sMsg) {
        //Search
        NMAL7120CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode());
	}
	
    private void doProcess_NMAL7120_TBLColumnSort(NMAL7120CMsg cMsg, NMAL7120SMsg sMsg) {
    	
        String sortTblNm  = cMsg.xxSortTblNm_01.getValue();
        String sortItemNm = cMsg.xxSortItemNm_01.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt_01.getValue();
        
        //Table:A
        if( "A".equals(sortTblNm ) ) {
			
    		// execute column sort function
    		S21SortTarget sortTarget = new S21SortTarget( sMsg.A, sMsg.A.no(0).getBaseContents() );
    		S21SortKey sortKey = new S21SortKey();
    		sortKey.add( sortItemNm, sortOrdBy );
    		S21EZDMsgArraySort.sort( sortTarget, sortKey, 0, sMsg.A.getValidCount() );
    		
    		// S21_NA ADD START QC#20206(L3#269)
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            cMsg.xxPageShowFromNum_10.setValue(1);
            cMsg.xxPageShowToNum_10.setValue(cMsg.A.getValidCount());
         // S21_NA ADD END QC#20206(L3#269)
    	}
    }

    private void doProcess_NMAL7120_Clear_Search(NMAL7120CMsg cMsg, NMAL7120SMsg sMsg) {
    	cMsg.dsAcctNm_H1.clear();
    	cMsg.dsAcctNum_H1.clear();
    	cMsg.csmpNum_H1.clear();
    	cMsg.dlrRefNum_H1.clear();
    	cMsg.prcCatgNm_H1.clear();
    	cMsg.prcContrNum_H1.clear();
    	cMsg.effFromDt_H1.clear();
    	cMsg.effThruDt_H1.clear();
    	cMsg.csmpContrActvFlg_H1.clear();
    	cMsg.rtlDlrCd_H1.clear();
    	cMsg.rnwCsmpNum_H1.clear();
    	cMsg.xxChkBox_H1.clear();
    }
    
    private void doProcess_NMAL7120_Add(NMAL7120CMsg cMsg, NMAL7120SMsg sMsg, String prcContrNum) {
    	
        NMAL7120CommonLogic.updateSMsg(cMsg, sMsg); // S21_NA ADD QC#20206(L3#269)
        if (sMsg.A.getValidCount() == cMsg.xxDtlCnt_H2.getValueInt()) {
            cMsg.setMessageInfo("NMAM0050E", new String[] { String.valueOf(cMsg.xxDtlCnt_H2.getValueInt()) });
			return;
		}

        cMsg.xxPageShowFromNum_10.setValue(sMsg.A.getValidCount());
        int count = sMsg.A.getValidCount() + 1;
        sMsg.A.setValidCount(count);
    	
        if (ZYPCommonFunc.hasValue(prcContrNum)) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(count-1).prcContrNum_A1, prcContrNum);
        }
        if (count == cMsg.xxDtlCnt_H1.getValueInt() + 1) { // S21_NA ADD QC#20206(L3#269)
            sMsg.A.no(count - 1).clear(); // S21_NA ADD QC#20206(L3#269)
        }
        
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count-1).csmpContrActvFlg_A1, ZYPConstant.FLG_ON_Y);
        NMAL7120CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A); // S21_NA ADD QC#20206(L3#269)
    }
    
    private void doProcess_NMAL7120_Del(NMAL7120CMsg cMsg, NMAL7120SMsg sMsg) {
    	
        NMAL7120CommonLogic.updateSMsg(cMsg, sMsg); // S21_NA ADD QC#20206(L3#269)
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
        if ( selectedRows.isEmpty() ) {
            for ( int i = 0; i < sMsg.A.getValidCount(); i++ ) {
                //NMAM8054E=0,Please select item(s).
                sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, "NMAM8054E");
                return;
            }
        } else {
         // S21_NA ADD START QC#20206(L3#269)
            for (int index : selectedRows) {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(index).csmpContrPk_A1)) {
                    int validCnt = sMsg.B.getValidCount();
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCnt).csmpContrPk_B1, sMsg.A.no(index).csmpContrPk_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCnt).ezUpTime_B1, sMsg.A.no(index).ezUpTime_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCnt).ezUpTimeZone_B1, sMsg.A.no(index).ezUpTimeZone_A1);
                    sMsg.B.setValidCount(validCnt + 1);
                }
            }
         // S21_NA ADD END QC#20206(L3#269)
            ZYPTableUtil.deleteRows(sMsg.A, selectedRows);
            ZYPTableUtil.deleteRows(sMsg.C, selectedRows);// S21_NA ADD QC#20206(L3#269)
        }
        // S21_NA ADD START QC#20206(L3#269)
        if (cMsg.xxPageShowFromNum_10.getValueInt() > sMsg.A.getValidCount()) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_10, BigDecimal.valueOf(sMsg.A.getValidCount() - 1));
        }
        NMAL7120CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A); 
        // S21_NA ADD END QC#20206(L3#269)
    }
    
    private void doProcess_NMAL7120_CLEAR(NMAL7120CMsg cMsg, NMAL7120SMsg sMsg) {
    	ZYPTableUtil.clear(cMsg.A);
    	ZYPTableUtil.clear(sMsg.A);// S21_NA ADD QC#20206(L3#269)
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.C);// S21_NA ADD QC#20206(L3#269)

    	//Search Filter
    	cMsg.dsAcctNm_H1.clear();
    	cMsg.dsAcctNum_H1.clear();
    	cMsg.csmpNum_H1.clear();
    	cMsg.dlrRefNum_H1.clear();
    	cMsg.prcCatgNm_H1.clear();
    	cMsg.prcContrNum_H1.clear();
    	cMsg.effFromDt_H1.clear();
    	cMsg.xxChkBox_H1.clear();
    	cMsg.effThruDt_H1.clear();
    	cMsg.csmpContrActvFlg_H1.clear();
    	cMsg.rtlDlrCd_H1.clear();
    	cMsg.rnwCsmpNum_H1.clear();
    	//Mass Update
    	cMsg.csmpNum_MU.clear();
    	cMsg.dlrRefNum_MU.clear();
    	cMsg.prcContrNum_MU.clear();
    	cMsg.csmpNumCmntTxt_MU.clear();
    	cMsg.effFromDt_MU.clear();
    	cMsg.effThruDt_MU.clear();
    	cMsg.csmpContrActvFlg_MU.clear();
    	cMsg.cusaRejDt_MU.clear();
    	cMsg.uplftEquipRatio_MU.clear();
    	cMsg.uplftAsryRatio_MU.clear();
    	cMsg.rnwCsmpNum_MU.clear();
    	cMsg.xxChkBox_AL.clear();
    }
    
    private void doProcess_NMAL7120_RESET(NMAL7120CMsg cMsg, NMAL7120SMsg sMsg) {
    	doProcess_NMAL7120_CLEAR(cMsg, sMsg);
    }
	
    private void doProcess_NMAL7120_DOWNLOAD(NMAL7120CMsg cMsg, NMAL7120SMsg sMsg) {

        if (EXPIERD.equals(cMsg.csmpContrActvFlg_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxModeNm13Txt_H1, EXPIERD_TXT);
            cMsg.csmpContrActvFlg_H1.clear();
        }

        NMAL7120Query.getInstance().searchForCSV(cMsg, sMsg, getGlobalCompanyCode(), new CreateDownloadData(cMsg));
        if (ZYPCommonFunc.hasValue(cMsg.xxModeNm13Txt_H1)) {
            ZYPEZDItemValueSetter.setValue(cMsg.csmpContrActvFlg_H1, EXPIERD);
            cMsg.xxModeNm13Txt_H1.clear();
        }
    }
    
    private static class CreateDownloadData extends S21SsmBooleanResultSetHandlerSupport {
    	private NMAL7120CMsg bizMsg;
    	public CreateDownloadData(NMAL7120CMsg cMsg) {
    		this.bizMsg = cMsg;
    	}
        private static final String[] CSV_HEADER = {
            "Account Number"
          , "Account Name"
          , "CSMP Number"
          , "CSA Number"
          , "CSMP Level"
          , "Contract Number"
          , "Originating GL Branch Code"
          , "Origin Dealer Code"
          , "Effective Date"
          , "Expiration Date"
          , "Rejection Date"
          , "Early Term Date"
          , "Renewed CSMP#"
          , "Markup Equipment"
          , "Markup Accessory"
          , "Notes"
          // 2016/07/20 CSA-QC#9690 Add Start
          , "Customer Membership ID"
          // 2016/07/20 CSA-QC#9690 Add end
          , "Active"
          , "Expired"
        };
        @Override
        protected Boolean doProcessQueryResult(ResultSet result) throws SQLException {
            if (!result.next()) {
                bizMsg.setMessageInfo("NZZM0000E");
                return Boolean.FALSE;
            }
            NMAL7120F00FMsg fMsg = new NMAL7120F00FMsg();
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_DOWNLOAD), CSV_FILE_EXT);
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
            csvOutFile.writeHeader(CSV_HEADER);
            
            int rowCount = 1;
            do {
                fMsg.clear();
                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum_A1, result.getString("DS_ACCT_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_A1, result.getString("DS_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.csmpNum_A1, result.getString("CSMP_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.dlrRefNum_A1, result.getString("DLR_REF_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm_A1, result.getString("PRC_CATG_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcContrNum_A1, result.getString("PRC_CONTR_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.origCoaBrCd_A1, result.getString("ORIG_COA_BR_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.rtlDlrCd_A1, result.getString("RTL_DLR_CD"));
                // 2018/07/11 QC#25557 Mod Start
//                ZYPEZDItemValueSetter.setValue(fMsg.effFromDt_A1, result.getString("EFF_FROM_DT"));
//                ZYPEZDItemValueSetter.setValue(fMsg.effThruDt_A1, result.getString("EFF_THRU_DT"));
//                ZYPEZDItemValueSetter.setValue(fMsg.cusaRejDt_A1, result.getString("CUSA_REJ_DT"));
//                ZYPEZDItemValueSetter.setValue(fMsg.erlTrmnDt_A1, result.getString("ERL_TRMN_DT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FR, NMAL7120CommonLogic.convertDateFormat(result.getString("EFF_FROM_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TH, NMAL7120CommonLogic.convertDateFormat(result.getString("EFF_THRU_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_RJ, NMAL7120CommonLogic.convertDateFormat(result.getString("CUSA_REJ_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TN, NMAL7120CommonLogic.convertDateFormat(result.getString("ERL_TRMN_DT")));
                // 2018/07/11 QC#25557 Mod End
                ZYPEZDItemValueSetter.setValue(fMsg.rnwCsmpNum_A1, result.getString("RNW_CSMP_NUM"));
                fMsg.uplftEquipRatio_A1.setValue(result.getBigDecimal("UPLFT_EQUIP_RATIO"));
                fMsg.uplftAsryRatio_A1.setValue(result.getBigDecimal("UPLFT_ASRY_RATIO"));
                ZYPEZDItemValueSetter.setValue(fMsg.csmpNumCmntTxt_A1, result.getString("CSMP_NUM_CMNT_TXT"));
                // 2016/07/20 CSA-QC#9690 Add Start
                ZYPEZDItemValueSetter.setValue(fMsg.custMbrId_A1, result.getString("CUST_MBR_ID"));
                // 2016/07/20 CSA-QC#9690 Add end
                ZYPEZDItemValueSetter.setValue(fMsg.csmpContrActvFlg_A1, result.getString("CSMP_CONTR_ACTV_FLG"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxModeNm13Txt_A1, result.getString("EXPIRED"));
                csvOutFile.write();
                rowCount++;
            } while (result.next());
            csvOutFile.close();

            return Boolean.TRUE;
            
        }
    }

    private void doProcess_NMAL7120_Mass_Update_CSMP(NMAL7120CMsg cMsg, NMAL7120SMsg sMsg) {
        
        NMAL7120CommonLogic.updateSMsg(cMsg, sMsg); // S21_NA ADD QC#20206(L3#269)
    	
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
        if ( selectedRows.isEmpty() ) {
            for ( int i = 0; i < sMsg.A.getValidCount(); i++ ) {
                //NMAM8054E=0,Please select item(s).
                sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, "NMAM8054E");
                return;
            }
        } else {

            for (int i = 0; i < selectedRows.size(); i++) {
            	Integer row = selectedRows.get(i);
                NMAL7120_ASMsg line  = sMsg.A.no(row.intValue());

                //CSMP#
            	if (ZYPCommonFunc.hasValue(cMsg.csmpNum_MU)) {
            		ZYPEZDItemValueSetter.setValue(line.csmpNum_A1, cMsg.csmpNum_MU);
            	}
            	//CSA#
            	if (ZYPCommonFunc.hasValue(cMsg.dlrRefNum_MU)) {
            		ZYPEZDItemValueSetter.setValue(line.dlrRefNum_A1, cMsg.dlrRefNum_MU);
            	}
            	//Contract#
//            	if (ZYPCommonFunc.hasValue(cMsg.prcContrNum_MU)) { // S21_NA DEL QC#20206(L3#269)
//            		ZYPEZDItemValueSetter.setValue(line.prcContrNum_A1, cMsg.prcContrNum_MU);
//            	}
            	//Note
            	if (ZYPCommonFunc.hasValue(cMsg.csmpNumCmntTxt_MU)) {
            		ZYPEZDItemValueSetter.setValue(line.csmpNumCmntTxt_A1, cMsg.csmpNumCmntTxt_MU);
            	}
            	//Effective Date From
            	if (ZYPCommonFunc.hasValue(cMsg.effFromDt_MU)) {
            		ZYPEZDItemValueSetter.setValue(line.effFromDt_A1, cMsg.effFromDt_MU);
            	}
            	//Expiration Date
            	if (ZYPCommonFunc.hasValue(cMsg.effThruDt_MU)) {
            		ZYPEZDItemValueSetter.setValue(line.effThruDt_A1, cMsg.effThruDt_MU);
            	}
            	//Status
            	if (ZYPCommonFunc.hasValue(cMsg.csmpContrActvFlg_MU)) {
            		ZYPEZDItemValueSetter.setValue(line.csmpContrActvFlg_A1, cMsg.csmpContrActvFlg_MU);
            	}
            	//Rejection Date
            	if (ZYPCommonFunc.hasValue(cMsg.cusaRejDt_MU)) {
            		ZYPEZDItemValueSetter.setValue(line.cusaRejDt_A1, cMsg.cusaRejDt_MU);
            	}
            	//Equipment Markup
            	if (ZYPCommonFunc.hasValue(cMsg.uplftEquipRatio_MU)) {
            		ZYPEZDItemValueSetter.setValue(line.uplftEquipRatio_A1, cMsg.uplftEquipRatio_MU);
            	}
            	//Accessory Markup
            	if (ZYPCommonFunc.hasValue(cMsg.uplftAsryRatio_MU)) {
            		ZYPEZDItemValueSetter.setValue(line.uplftAsryRatio_A1, cMsg.uplftAsryRatio_MU);
            	}
            	//Renewed CSMP#
            	if (ZYPCommonFunc.hasValue(cMsg.rnwCsmpNum_MU)) {
            		ZYPEZDItemValueSetter.setValue(line.rnwCsmpNum_A1, cMsg.rnwCsmpNum_MU);
            	}
            	
            }
        }
        NMAL7120CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A); // S21_NA ADD QC#20206(L3#269)
    }
    
    private void doProcess_NMAL7120_Click_SelAll_UnSelAll_Checkbox(NMAL7120CMsg cMsg, NMAL7120SMsg sMsg) {
    	
        int i = cMsg.xxPageShowFromNum_10.getValueInt() - 1;
        int initial = i;
        for (; i < initial + cMsg.A.getValidCount(); i++) {
            if (FLG_ON_Y.equals(cMsg.xxChkBox_AL.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_A1, FLG_ON_Y);
            } else {
                sMsg.A.no(i).xxChkBox_A1.clear();
            }
        }
        NMAL7120CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A); // S21_NA ADD QC#20206(L3#269)
    }
    
 // S21_NA ADD START QC#20206(L3#269)
    /**
     * execute next page transition button
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NMAL7120_PageNext(NMAL7120CMsg bizMsg, NMAL7120SMsg glblMsg) {
        NMAL7120CommonLogic.updateSMsg(bizMsg, glblMsg);
        bizMsg.xxChkBox_AL.clear();
        // copy data from SMsg onto CMsg
        bizMsg.xxPageShowFromNum_10.setValue(bizMsg.xxPageShowToNum_10.getValueInt() + 1);
        NMAL7120CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * execute previous page transition button
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NMAL7120_PagePrev(NMAL7120CMsg bizMsg, NMAL7120SMsg glblMsg) {
        NMAL7120CommonLogic.updateSMsg(bizMsg, glblMsg);
        bizMsg.xxChkBox_AL.clear();
        // copy data from SMsg onto CMsg
        bizMsg.xxPageShowFromNum_10.setValue(bizMsg.xxPageShowFromNum_10.getValueInt() - bizMsg.A.length());
        NMAL7120CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * execute jump to selected screen transition button
     * @param bizMsg
     * @param sMsg
     */
    private void doProcess_NMAL7120_PageJump(NMAL7120CMsg bizMsg, NMAL7120SMsg glblMsg) {
        NMAL7120CommonLogic.updateSMsg(bizMsg, glblMsg);
        bizMsg.xxChkBox_AL.clear();
        // copy data from SMsg onto CMsg
        bizMsg.xxPageShowFromNum_10.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum_10.getValueInt() - 1)) + 1);
        NMAL7120CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }
 // S21_NA ADD END QC#20206(L3#269)
}
