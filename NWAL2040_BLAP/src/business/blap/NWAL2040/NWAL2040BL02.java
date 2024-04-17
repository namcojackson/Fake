package business.blap.NWAL2040;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2040.NWAL2040CMsg;
import business.blap.NWAL2040.common.NWAL2040CommonLogic;
import business.blap.NWAL2040.constant.NWAL2040Constant;
import business.file.NWAL2040F00FMsg;
import business.file.NWAL2040F01FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

import parts.common.EZDMsg;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DROP_RTRN_VND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_OWNR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.THIRD_PTY_DSP_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 2016/03/23   Hitachi         K.Kojima        Update          S21 NA Unit Test #56  Add Upload Function
 * 2017/09/12   Fujitsu         T.Ogura         Update          QC#19805
 *</pre>
 */
public class NWAL2040BL02 extends S21BusinessHandler implements NWAL2040Constant {

	@Override
	protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
		super.preDoProcess(cMsg, sMsg);
		NWAL2040CMsg bizMsg = (NWAL2040CMsg) cMsg;
		String scrnAplID = bizMsg.getScreenAplID();
        try {
        	if ("NWAL2040_INIT".equals(scrnAplID)) {
        		doProcess_NWAL2040_INIT(bizMsg, (NWAL2040SMsg) sMsg);
            } else if ("NWAL2040Scrn00_TAB_SrcCatg".equals(scrnAplID)) {
        		doProcess_NWAL2040_TAB_SrcCatg(bizMsg, (NWAL2040SMsg) sMsg);
            } else if ("NWAL2040Scrn00_TAB_MapTmpQlfy".equals(scrnAplID)) {
        		doProcess_NWAL2040_MapTmpQlfy(bizMsg, (NWAL2040SMsg) sMsg);
            // 2017/09/12 QC#19805 Add Start
            } else if ("NWAL2040Scrn00_TAB_MapTmpQlfyRMA".equals(scrnAplID)) {
                doProcess_NWAL2040_TAB_MapTmpQlfyRMA(bizMsg, (NWAL2040SMsg) sMsg);
            // 2017/09/12 QC#19805 Add End
            } else if ("NWAL2040Scrn00_InsertRow_SRC_CATG".equals(scrnAplID)) {
                doProcess_InsertRow_SRC_CATG(bizMsg, (NWAL2040SMsg) sMsg);
            } else if ("NWAL2040Scrn00_DeleteRow_SRC_CATG".equals(scrnAplID)) {
                doProcess_DeleteRow_SRC_CATG(bizMsg, (NWAL2040SMsg) sMsg);
            } else if ("NWAL2040Scrn00_InsertRow_MAP_TMPL_QLFY".equals(scrnAplID)) {
                doProcess_InsertRow_MAP_TMPL_QLFY(bizMsg, (NWAL2040SMsg) sMsg);
            } else if ("NWAL2040Scrn00_DeleteRow_MAP_TMPL_QLFY".equals(scrnAplID)) {
                doProcess_DeleteRow_MAP_TMPL_QLFY(bizMsg, (NWAL2040SMsg) sMsg);
            // 2017/09/12 QC#19805 Add Start
            } else if ("NWAL2040Scrn00_InsertRow_MAP_TMPL_QLFY_RMA".equals(scrnAplID)) {
                doProcess_InsertRow_MAP_TMPL_QLFY_RMA(bizMsg, (NWAL2040SMsg) sMsg);
            } else if ("NWAL2040Scrn00_DeleteRow_MAP_TMPL_QLFY_RMA".equals(scrnAplID)) {
                doProcess_DeleteRow_MAP_TMPL_QLFY_RMA(bizMsg, (NWAL2040SMsg) sMsg);
            // 2017/09/12 QC#19805 Add End
        	} else if ("NWAL2040Scrn00_PageNext".equals(scrnAplID)) {
                doProcess_NWAL2040_PageNext(bizMsg, (NWAL2040SMsg) sMsg);
        	} else if ("NWAL2040Scrn00_PagePrev".equals(scrnAplID)) {
                doProcess_NWAL2040_PagePrev(bizMsg, (NWAL2040SMsg) sMsg);
        	} else if ("NWAL2040Scrn00_PageJump".equals(scrnAplID)) {
                doProcess_NWAL2040_PageJump(bizMsg, (NWAL2040SMsg) sMsg);                   
        	} else if ("NWAL2040Scrn00_filter_Result".equals(scrnAplID)) {
        		doProcess_NWAL2040_filter_Result(bizMsg, (NWAL2040SMsg) sMsg);	        		
            } else if ("NWAL2040Scrn00_CMN_Clear".equals(scrnAplID)) {
        		doProcess_NWAL2040_CLEAR(bizMsg, (NWAL2040SMsg) sMsg);
        	} else if ("NWAL2040Scrn00_CMN_Reset".equals(scrnAplID)) {
        		doProcess_NWAL2040_RESET(bizMsg, (NWAL2040SMsg) sMsg);
        	} else if ("NWAL2040Scrn00_Search".equals(scrnAplID)) {
        		doProcess_NWAL2040_Search(bizMsg, (NWAL2040SMsg) sMsg);
            } else if ("NWAL2040Scrn00_TBLColumnSort".equals(scrnAplID)) {
            	doProcess_NWAL2040_TBLColumnSort(bizMsg, (NWAL2040SMsg) sMsg);
            } else if ("NWAL2040Scrn00_Change_DsOrdCatgPulldown".equals(scrnAplID)) {
            	doProcess_NWAL2040_Change_DsOrdCatgPulldown(bizMsg, (NWAL2040SMsg) sMsg);
                // START 2016/03/23 K.Kojima [UT#56,ADD]
            } else if ("NWAL2040Scrn00_CMN_Download".equals(scrnAplID)) {
                doProcess_NWAL2040Scrn00_CMN_Download(bizMsg, (NWAL2040SMsg) sMsg);
                // END 2016/03/23 K.Kojima [UT#56,ADD]
        	}
        } finally {
        	super.postDoProcess(cMsg, sMsg);
        }
	}
	private void doProcess_NWAL2040_Change_DsOrdCatgPulldown(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg) {
		
        int idx = bizMsg.xxCellIdx_H1.getValueInt();
        NWAL2040_BCMsg bcMsg = bizMsg.B.no(idx);
        if (!ZYPCommonFunc.hasValue(bcMsg.dsOrdCatgCd_B1)) {
            return;
        }
        NWAL2040CommonLogic.setDsOrdTpPulldown(bcMsg, getGlobalCompanyCode(), bcMsg.dsOrdCatgCd_B1.getValue());
        
	}
	
	private void doProcess_NWAL2040_INIT(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg) {

		ZYPTableUtil.clear(bizMsg.B);
		ZYPTableUtil.clear(bizMsg.D);
		ZYPTableUtil.clear(sMsg.B);
		ZYPTableUtil.clear(sMsg.D);
		ZYPTableUtil.clear(bizMsg.A);
		ZYPTableUtil.clear(bizMsg.C);
		ZYPTableUtil.clear(sMsg.A);
		ZYPTableUtil.clear(sMsg.C);
        // 2017/09/12 QC#19805 Add Start
        ZYPTableUtil.clear(bizMsg.E);
        ZYPTableUtil.clear(bizMsg.F);
        ZYPTableUtil.clear(sMsg.E);
        ZYPTableUtil.clear(sMsg.F);
        // 2017/09/12 QC#19805 Add End
		
		//Setup select box
    	//DS_ORD_CATG
        bizMsg.dsOrdCatgCd_L1.clear();
        bizMsg.dsOrdCatgNm_L1.clear();
        ZYPCodeDataUtil.createPulldownList(DS_ORD_CATG.class, bizMsg.dsOrdCatgCd_L1, bizMsg.dsOrdCatgNm_L1);

    	//DEF_WH_MAP_TMPL
        bizMsg.defWhMapTmplCd_L1.clear();
        bizMsg.defWhMapTmplNm_L1.clear();
        NWAL2040CommonLogic.setInitialDefWhMapTmplPulldown(bizMsg, getGlobalCompanyCode());
        
    	//DEF_WH_MAP_TMPL
        bizMsg.invtyOwnrCd_L1.clear();
        bizMsg.invtyOwnrNm_L1.clear();
        ZYPCodeDataUtil.createPulldownList(INVTY_OWNR.class, bizMsg.invtyOwnrCd_L1, bizMsg.invtyOwnrNm_L1);
        
        //MDSE_ITEM_CLS_TP
        bizMsg.mdseItemClsTpCd_L1.clear();
        bizMsg.mdseItemClsTpNm_L1.clear();
        NWAL2040CommonLogic.setInitialMdseItemClsTpPulldown(bizMsg, getGlobalCompanyCode());
        
        //ORD_LINE_SRC
        bizMsg.ordLineSrcCd_L1.clear();
        bizMsg.ordLineSrcNm_L1.clear();
        NWAL2040CommonLogic.setInitialOrdLineSrcPulldownInternal(bizMsg, getGlobalCompanyCode());
        // 2017/09/12 QC#19805 Del Start
//        bizMsg.ordLineSrcCd_R1.clear();
//        bizMsg.ordLineSrcNm_R1.clear();
//        NWAL2040CommonLogic.setInitialOrdLineSrcPulldownReturn(bizMsg, getGlobalCompanyCode());
        // 2017/09/12 QC#19805 Del End
        bizMsg.ordLineSrcCd_D1.clear();
        bizMsg.ordLineSrcNm_D1.clear();
        NWAL2040CommonLogic.setInitialOrdLineSrcPulldownIE(bizMsg, getGlobalCompanyCode());

        // 2017/09/12 QC#19805 Add Start
        //THIRD_PTY_DSP_TP
        bizMsg.thirdPtyDspTpCd_R1.clear();
        bizMsg.thirdPtyDspTpNm_R1.clear();
        ZYPCodeDataUtil.createPulldownList(THIRD_PTY_DSP_TP.class, bizMsg.thirdPtyDspTpCd_R1, bizMsg.thirdPtyDspTpNm_R1);

        //DROP_RTRN_VND
        bizMsg.dropRtrnVndCd_R1.clear();
        bizMsg.dropRtrnVndNm_R1.clear();
        ZYPCodeDataUtil.createPulldownList(DROP_RTRN_VND.class, bizMsg.dropRtrnVndCd_R1, bizMsg.dropRtrnVndNm_R1);
        // 2017/09/12 QC#19805 Add End

        //Search
    	NWAL2040CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode(), true);

	}

	private void doProcess_NWAL2040_TAB_SrcCatg(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg) {
    	NWAL2040CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode(), true);
	}
	
    private void doProcess_NWAL2040_MapTmpQlfy(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg) {
        NWAL2040CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode(), true);
    }

    // 2017/09/12 QC#19805 Add Start
    private void doProcess_NWAL2040_TAB_MapTmpQlfyRMA(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg) {
        NWAL2040CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode(), true);
    }
    // 2017/09/12 QC#19805 Add End

    private void doProcess_NWAL2040_Search(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg) {

		NWAL2040CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode(), false);

	}
	
	private void doProcess_NWAL2040_PageNext(NWAL2040CMsg cMsg, NWAL2040SMsg sMsg) {

        // 2017/09/12 QC#19805 Add Start
        if (TAB_MAP_TMPL_QLFY.equals(cMsg.xxDplyTab_H1.getValue())) {
        // 2017/09/12 QC#19805 Add End
            NWAL2040CommonLogic.copyFromCMsgToSMsgForMapTmpl(cMsg, sMsg, false);
            
            cMsg.xxPageShowFromNum_10.setValue(cMsg.xxPageShowToNum_10.getValueInt());
            cMsg.xxPageShowToNum_10.clear();
            
            //copy data from SMsg onto CMsg
            int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt();
            int i              = pagenationFrom;
            for( ; i < pagenationFrom + cMsg.A.length(); i++ ) {
                if( i < sMsg.A.getValidCount() ) {
                    EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i - pagenationFrom ), null );
                } else {
                    break;
                }
            }
            cMsg.A.setValidCount( i - pagenationFrom );
        
            //set value to page items
            cMsg.xxPageShowFromNum_10.setValue( pagenationFrom + 1 );
            cMsg.xxPageShowToNum_10.setValue( pagenationFrom + cMsg.A.getValidCount() );
        // 2017/09/12 QC#19805 Add Start
        } else if (TAB_MAP_TMPL_QLFY_RMA.equals(cMsg.xxDplyTab_H1.getValue())) {
            NWAL2040CommonLogic.copyFromCMsgToSMsgForMapTmplRMA(cMsg, sMsg, false);

            cMsg.xxPageShowFromNum_10.setValue(cMsg.xxPageShowToNum_10.getValueInt());
            cMsg.xxPageShowToNum_10.clear();

            //copy data from SMsg onto CMsg
            int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt();
            int i              = pagenationFrom;
            for( ; i < pagenationFrom + cMsg.E.length(); i++ ) {
                if( i < sMsg.E.getValidCount() ) {
                    EZDMsg.copy( sMsg.E.no( i ), null, cMsg.E.no( i - pagenationFrom ), null );
                } else {
                    break;
                }
            }
            cMsg.E.setValidCount( i - pagenationFrom );

            //set value to page items
            cMsg.xxPageShowFromNum_10.setValue( pagenationFrom + 1 );
            cMsg.xxPageShowToNum_10.setValue( pagenationFrom + cMsg.E.getValidCount() );
        }
        // 2017/09/12 QC#19805 Add End

	}

    private void doProcess_NWAL2040_PagePrev(NWAL2040CMsg cMsg, NWAL2040SMsg sMsg) {

        // 2017/09/12 QC#19805 Add Start
        if (TAB_MAP_TMPL_QLFY.equals(cMsg.xxDplyTab_H1.getValue())) {
        // 2017/09/12 QC#19805 Add End
            NWAL2040CommonLogic.copyFromCMsgToSMsgForMapTmpl(cMsg, sMsg, false);
            
            cMsg.xxPageShowFromNum_10.setValue(cMsg.xxPageShowFromNum_10.getValueInt() - cMsg.A.length()-1);
            cMsg.xxPageShowToNum_10.clear();
            
            //copy data from SMsg to CMsg
            int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt();
            int i              = pagenationFrom;
            for( ; i < pagenationFrom + cMsg.A.length(); i++ ) {
                if( i < sMsg.A.getValidCount() ) {
                    EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i - pagenationFrom ), null );
                } else {
                    break;
                }
            }
            cMsg.A.setValidCount( i - pagenationFrom );
        
            // set value to page items
            pagenationFrom = pagenationFrom + 1;
            cMsg.xxPageShowFromNum_10.setValue( pagenationFrom );
            cMsg.xxPageShowToNum_10.setValue( pagenationFrom + cMsg.A.getValidCount() - 1 );
        // 2017/09/12 QC#19805 Add Start
        } else if (TAB_MAP_TMPL_QLFY_RMA.equals(cMsg.xxDplyTab_H1.getValue())) {
            NWAL2040CommonLogic.copyFromCMsgToSMsgForMapTmplRMA(cMsg, sMsg, false);

            cMsg.xxPageShowFromNum_10.setValue(cMsg.xxPageShowFromNum_10.getValueInt() - cMsg.E.length()-1);
            cMsg.xxPageShowToNum_10.clear();

            //copy data from SMsg to CMsg
            int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt();
            int i              = pagenationFrom;
            for( ; i < pagenationFrom + cMsg.E.length(); i++ ) {
                if( i < sMsg.E.getValidCount() ) {
                    EZDMsg.copy( sMsg.E.no( i ), null, cMsg.E.no( i - pagenationFrom ), null );
                } else {
                    break;
                }
            }
            cMsg.E.setValidCount( i - pagenationFrom );

            // set value to page items
            pagenationFrom = pagenationFrom + 1;
            cMsg.xxPageShowFromNum_10.setValue( pagenationFrom );
            cMsg.xxPageShowToNum_10.setValue( pagenationFrom + cMsg.E.getValidCount() - 1 );
        }
        // 2017/09/12 QC#19805 Add End
	}

    private void doProcess_NWAL2040_PageJump(NWAL2040CMsg cMsg, NWAL2040SMsg sMsg) {

        // 2017/09/12 QC#19805 Add Start
        if (TAB_MAP_TMPL_QLFY.equals(cMsg.xxDplyTab_H1.getValue())) {
        // 2017/09/12 QC#19805 Add End
            NWAL2040CommonLogic.copyFromCMsgToSMsgForMapTmpl(cMsg, sMsg, false);
            
            int nextFromNum = (cMsg.xxPageShowCurNum_10.getValueInt() - 1) * cMsg.A.length();
            cMsg.xxPageShowFromNum_10.setValue(nextFromNum);
            cMsg.xxPageShowToNum_10.clear();
            
            //copy data from SMsg onto CMsg
            int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt();
            int i              = pagenationFrom;
            for( ; i < pagenationFrom + cMsg.A.length(); i++ ) {
                if( i < sMsg.A.getValidCount() ) {
                    EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i - pagenationFrom ), null );
                } else {
                    break;
                }
            }
            cMsg.A.setValidCount( i - pagenationFrom );
        
            //set value to page items
            cMsg.xxPageShowFromNum_10.setValue( pagenationFrom + 1 );
            cMsg.xxPageShowToNum_10.setValue( pagenationFrom + cMsg.A.getValidCount() );

        // 2017/09/12 QC#19805 Add Start
        } else if (TAB_MAP_TMPL_QLFY_RMA.equals(cMsg.xxDplyTab_H1.getValue())) {
            NWAL2040CommonLogic.copyFromCMsgToSMsgForMapTmplRMA(cMsg, sMsg, false);

            int nextFromNum = (cMsg.xxPageShowCurNum_10.getValueInt() - 1) * cMsg.E.length();
            cMsg.xxPageShowFromNum_10.setValue(nextFromNum);
            cMsg.xxPageShowToNum_10.clear();

            //copy data from SMsg onto CMsg
            int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt();
            int i              = pagenationFrom;
            for( ; i < pagenationFrom + cMsg.E.length(); i++ ) {
                if( i < sMsg.E.getValidCount() ) {
                    EZDMsg.copy( sMsg.E.no( i ), null, cMsg.E.no( i - pagenationFrom ), null );
                } else {
                    break;
                }
            }
            cMsg.E.setValidCount( i - pagenationFrom );

            //set value to page items
            cMsg.xxPageShowFromNum_10.setValue( pagenationFrom + 1 );
            cMsg.xxPageShowToNum_10.setValue( pagenationFrom + cMsg.E.getValidCount() );
        }
        // 2017/09/12 QC#19805 Add End
	}

    private void doProcess_NWAL2040_filter_Result(NWAL2040CMsg cMsg, NWAL2040SMsg sMsg) {
    	NWAL2040CommonLogic.search(cMsg, sMsg, getGlobalCompanyCode(), false);
    }
	
	
    private void doProcess_NWAL2040_TBLColumnSort(NWAL2040CMsg cMsg, NWAL2040SMsg sMsg) {
    	
        String sortTblNm  = cMsg.xxSortTblNm_01.getValue();
        String sortItemNm = cMsg.xxSortItemNm_01.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt_01.getValue();

        // 2017/09/12 QC#19805 Add Start
        if (TAB_MAP_TMPL_QLFY.equals(cMsg.xxDplyTab_H1.getValue())) {
        // 2017/09/12 QC#19805 Add End
            //Table:A
            if( "A".equals(sortTblNm ) ) {
                
                // execute column sort function
                S21SortTarget sortTarget = new S21SortTarget( sMsg.A, sMsg.A.no(0).getBaseContents() );
                S21SortKey sortKey = new S21SortKey();
                sortKey.add( sortItemNm, sortOrdBy );
                S21EZDMsgArraySort.sort( sortTarget, sortKey, 0, sMsg.A.getValidCount() );
                    
                // copy(SMsg -> CMsg)
                int i = 0;
                for( ; i < sMsg.A.getValidCount(); i++ ) {
                    if( i == cMsg.A.length() ) {
                        break;
                    }
                    EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i ), null );
                }
                cMsg.A.setValidCount( i );
                
                //set page no 
                cMsg.xxPageShowFromNum_10.setValue( 1 );
                cMsg.xxPageShowToNum_10.setValue( cMsg.A.getValidCount() );
            }
        // 2017/09/12 QC#19805 Add Start
        } else if (TAB_MAP_TMPL_QLFY_RMA.equals(cMsg.xxDplyTab_H1.getValue())) {
            //Table:E
            if( "E".equals(sortTblNm ) ) {
                // execute column sort function
                S21SortTarget sortTarget = new S21SortTarget( sMsg.E, sMsg.E.no(0).getBaseContents() );
                S21SortKey sortKey = new S21SortKey();
                sortKey.add( sortItemNm, sortOrdBy );
                S21EZDMsgArraySort.sort( sortTarget, sortKey, 0, sMsg.E.getValidCount() );

                // copy(SMsg -> CMsg)
                int i = 0;
                for( ; i < sMsg.E.getValidCount(); i++ ) {
                    if( i == cMsg.E.length() ) {
                        break;
                    }
                    EZDMsg.copy( sMsg.E.no( i ), null, cMsg.E.no( i ), null );
                }
                cMsg.E.setValidCount( i );

                //set page no 
                cMsg.xxPageShowFromNum_10.setValue( 1 );
                cMsg.xxPageShowToNum_10.setValue( cMsg.E.getValidCount() );
            }
        }
        // 2017/09/12 QC#19805 Add End
    }
	
    private void doProcess_NWAL2040_CLEAR(NWAL2040CMsg cMsg, NWAL2040SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        // 2017/09/12 QC#19805 Add Start
        ZYPTableUtil.clear(cMsg.E);
        // 2017/09/12 QC#19805 Add End
//    	cMsg.rptAbbrNm_H1.clear();
//    	cMsg.mdseCstUpdTpCd_H1.clear();
//    	cMsg.mdseCstUpdGrpTxt_H1.clear();    	
//    	cMsg.mdseCstUpdRefTxt_H1.clear();
//    	cMsg.mdseCstUpdRefId_H1.clear();
//    	cMsg.mdseCd_H1.clear();
//    	cMsg.mdseItemTpCd_H1.clear();
//    	cMsg.coaMdseTpCd_H1.clear();
//    	cMsg.coaProdCd_H1.clear();
//    	cMsg.coaProdNm_H1.clear();
//    	cMsg.rtlWhCd_H1.clear();
//    	cMsg.rtlWhNm_H1.clear();
//    	cMsg.rtlSwhCd_H1.clear();
//    	cMsg.rtlSwhNm_H1.clear();
//    	cMsg.mdseCstUpdEffFromDt_H1.clear();
//    	cMsg.xxChkBox_CC.clear();
    }
    
    private void doProcess_NWAL2040_RESET(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg) {
    	NWAL2040CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode(), false);
    }
    
    private void doProcess_InsertRow_SRC_CATG(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg) {

		NWAL2040CommonLogic.copyFromCMsgToSMsgForSrcCatg(bizMsg, sMsg, false);
		
        // Add Record
        int count = bizMsg.B.getValidCount() + 1;
        bizMsg.B.setValidCount(count);
        count = sMsg.B.getValidCount() + 1;
        sMsg.B.setValidCount(count);
        
    }

    private void doProcess_DeleteRow_SRC_CATG(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg) {

		NWAL2040CommonLogic.copyFromCMsgToSMsgForSrcCatg(bizMsg, sMsg, false);
		
        // Delete Record
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_B1", ZYPConstant.FLG_ON_Y);
        if ( selectedRows.isEmpty() ) {
            for ( int i = 0; i < bizMsg.B.getValidCount(); i++ ) {
                //NMAM8054E=0,Please select item(s).
                bizMsg.B.no(i).xxChkBox_B1.setErrorInfo(1, "NMAM8054E");
                return;
            }
        } else {

            ZYPTableUtil.deleteRows(bizMsg.B, selectedRows);
            ZYPTableUtil.deleteRows(sMsg.B, selectedRows);
        }
        
    }
    private void doProcess_InsertRow_MAP_TMPL_QLFY(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg) {

		NWAL2040CommonLogic.copyFromCMsgToSMsgForMapTmpl(bizMsg, sMsg, false);
		
        int count = sMsg.A.getValidCount() + 1;
        sMsg.A.setValidCount(count);
    	
        // Add Record
		int specificIdx = sMsg.A.getValidCount() - 1;
		bizMsg.xxPageShowOfNum_10.setValue(sMsg.A.getValidCount());
        int page = specificIdx / bizMsg.A.length();
        int pageFrom = page * bizMsg.A.length();
        int i = pageFrom;
        for (; i < pageFrom + bizMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, bizMsg.A.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pageFrom);

        bizMsg.xxPageShowFromNum_10.setValue(pageFrom + 1);
        bizMsg.xxPageShowToNum_10.setValue(pageFrom + bizMsg.A.getValidCount());
        
	}

    private void doProcess_DeleteRow_MAP_TMPL_QLFY(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg) {

		NWAL2040CommonLogic.copyFromCMsgToSMsgForMapTmpl(bizMsg, sMsg, false);
		
        // Delete Record
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
        if ( selectedRows.isEmpty() ) {
            for ( int i = 0; i < bizMsg.A.getValidCount(); i++ ) {
                //NMAM8054E=0,Please select item(s).
                bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, "NMAM8054E");
                return;
            }
        } else {

            ZYPTableUtil.deleteRows(bizMsg.A, selectedRows);
            int currentPage = bizMsg.xxPageShowCurNum_10.getValueInt();
            List<Integer> sMsgSelectedRows = new java.util.ArrayList<Integer>();
            for (int i = 0; i < selectedRows.size(); i++) {
            	Integer row = selectedRows.get(i);
            	int iRow = row.intValue() + (bizMsg.A.length() * (currentPage - 1));
            	sMsgSelectedRows.add(new Integer(iRow));
            }
            
            ZYPTableUtil.deleteRows(sMsg.A, sMsgSelectedRows);
            if (bizMsg.A.getValidCount() == 0 && currentPage != 1) {
	            NWAL2040CommonLogic.transferLastPage(bizMsg, sMsg);
            }
        }
        
    }

    // 2017/09/12 QC#19805 Add Start
    private void doProcess_InsertRow_MAP_TMPL_QLFY_RMA(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg) {

        NWAL2040CommonLogic.copyFromCMsgToSMsgForMapTmplRMA(bizMsg, sMsg, false);

        int count = sMsg.E.getValidCount() + 1;
        sMsg.E.setValidCount(count);

        // Add Record
        int specificIdx = sMsg.E.getValidCount() - 1;
        bizMsg.xxPageShowOfNum_10.setValue(sMsg.E.getValidCount());
        int page = specificIdx / bizMsg.E.length();
        int pageFrom = page * bizMsg.E.length();
        int i = pageFrom;
        for (; i < pageFrom + bizMsg.E.length(); i++) {
            if (i < sMsg.E.getValidCount()) {
                EZDMsg.copy(sMsg.E.no(i), null, bizMsg.E.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        bizMsg.E.setValidCount(i - pageFrom);

        bizMsg.xxPageShowFromNum_10.setValue(pageFrom + 1);
        bizMsg.xxPageShowToNum_10.setValue(pageFrom + bizMsg.E.getValidCount());
    }
    // 2017/09/12 QC#19805 Add End

    // 2017/09/12 QC#19805 Add Start
    private void doProcess_DeleteRow_MAP_TMPL_QLFY_RMA(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg) {

        NWAL2040CommonLogic.copyFromCMsgToSMsgForMapTmplRMA(bizMsg, sMsg, false);

        // Delete Record
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.E, "xxChkBox_E1", ZYPConstant.FLG_ON_Y);
        if ( selectedRows.isEmpty() ) {
            for ( int i = 0; i < bizMsg.E.getValidCount(); i++ ) {
                //NMAM8054E=0,Please select item(s).
                bizMsg.E.no(i).xxChkBox_E1.setErrorInfo(1, "NMAM8054E");
                return;
            }
        } else {

            ZYPTableUtil.deleteRows(bizMsg.E, selectedRows);
            int currentPage = bizMsg.xxPageShowCurNum_10.getValueInt();
            List<Integer> sMsgSelectedRows = new java.util.ArrayList<Integer>();
            for (int i = 0; i < selectedRows.size(); i++) {
                Integer row = selectedRows.get(i);
                int iRow = row.intValue() + (bizMsg.E.length() * (currentPage - 1));
                sMsgSelectedRows.add(new Integer(iRow));
            }
            
            ZYPTableUtil.deleteRows(sMsg.E, sMsgSelectedRows);
            if (bizMsg.E.getValidCount() == 0 && currentPage != 1) {
                NWAL2040CommonLogic.transferLastPageRMA(bizMsg, sMsg);
            }
        }
    }
    // 2017/09/12 QC#19805 Add End

    // START 2016/03/23 K.Kojima [UT#56,ADD]
    /**
     * doProcess_NWAL2040Scrn00_CMN_Download
     * @param bizMsg NWAL2040CMsg
     * @param sMsg NWAL2040SMsg
     */
    private void doProcess_NWAL2040Scrn00_CMN_Download(NWAL2040CMsg bizMsg, NWAL2040SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm("CSV file Download"), ".csv");

            // 2017/09/12 QC#19805 Add Start
            if (TAB_MAP_TMPL_QLFY.equals(bizMsg.xxDplyTab_H1.getValue())) {
            // 2017/09/12 QC#19805 Add End
                rs = NWAL2040Query.getInstance().searchMapTmplQlfyForCSV(stmtSelect, getGlobalCompanyCode(), DOWNLOAD_LIMIT_CNT + 1, TAB_MAP_TMPL_QLFY);
            // 2017/09/12 QC#19805 Add Start
            } else if (TAB_MAP_TMPL_QLFY_RMA.equals(bizMsg.xxDplyTab_H1.getValue())) {
                rs = NWAL2040Query.getInstance().searchMapTmplQlfyForCSV(stmtSelect, getGlobalCompanyCode(), DOWNLOAD_LIMIT_CNT + 1, TAB_MAP_TMPL_QLFY_RMA);
            }
            // 2017/09/12 QC#19805 Add End

            NWAL2040F00FMsg fMsg = new NWAL2040F00FMsg();
            // 2017/09/12 QC#19805 Add Start
            NWAL2040F01FMsg rmafMsg = new NWAL2040F01FMsg();
            // 2017/09/12 QC#19805 Add End

            // 2017/09/12 QC#19805 Mod Start
//            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
            ZYPCSVOutFile csvOutFile = null;
            if (TAB_MAP_TMPL_QLFY.equals(bizMsg.xxDplyTab_H1.getValue())) {
                csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
            } else if (TAB_MAP_TMPL_QLFY_RMA.equals(bizMsg.xxDplyTab_H1.getValue())) {
                csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), rmafMsg);
            }
            // 2017/09/12 QC#19805 Mod End

            // 2017/09/12 QC#19805 Add Start
            if (TAB_MAP_TMPL_QLFY.equals(bizMsg.xxDplyTab_H1.getValue())) {
            // 2017/09/12 QC#19805 Add End
                // 2017/09/12 QC#19805 Mod Start
//                csvOutFile.writeHeader(new String[] {"Mapping Template Name", "Item Sourcing Classification:Qualifier", "Third Party Flag", "Postal Code From", "Postal Code To", "On Hand Cheking Flag", "[Primary On Hand Check] WH",
//                        "[Primary On Hand Check] SWH", "[Primary On Hand Check] Line Source Type", "[Secondary On Hand Check] WH", "[Secondary On Hand Check] SWH", "[Secondary On Hand Check] Line Source Type",
//                        "[OUTBOUND:Default WH(Not On Hand)] WH", "[OUTBOUND:Default WH(Not On Hand)] SWH", "[OUTBOUND:Default WH(Not On Hand)] Line Source Type", "[RWA:Default WH] WH", "[RWA:Default WH] SWH",
//                        "[RWA:Default WH] Line Source Type" });
                csvOutFile.writeHeader(new String[] {"Mapping Template Name", "Item Sourcing Classification:Qualifier", "Third Party Flag", "Postal Code From", "Postal Code To", "On Hand Cheking Flag", "[Primary On Hand Check] WH",
                        "[Primary On Hand Check] SWH", "[Primary On Hand Check] Line Source Type", "[Secondary On Hand Check] WH", "[Secondary On Hand Check] SWH", "[Secondary On Hand Check] Line Source Type",
                        "[OUTBOUND:Default WH(Not On Hand)] WH", "[OUTBOUND:Default WH(Not On Hand)] SWH", "[OUTBOUND:Default WH(Not On Hand)] Line Source Type" });
                // 2017/09/12 QC#19805 Mod End
            // 2017/09/12 QC#19805 Add Start
            } else if (TAB_MAP_TMPL_QLFY_RMA.equals(bizMsg.xxDplyTab_H1.getValue())) {
                csvOutFile.writeHeader(new String[] {"Mapping Template Name", "Item Sourcing Classification:Qualifier", "Third Party Flag", "Postal Code From", "Postal Code To", "On Hand Cheking Flag",
                        "[RWA:Default WH] RMA Disposition", "[RWA:Default WH] Supplier", "[RWA:Default WH] WH" });
            }
            // 2017/09/12 QC#19805 Add End

            int outputCount = 0;
            while (rs.next()) {
                if (rs.getRow() > DOWNLOAD_LIMIT_CNT) {
                    //NZZM0001W=0,There are too many search results, there is data that cannot be displayed.
                    bizMsg.setMessageInfo(NZZM0001W, null);
                    break;
                }

                // 2017/09/12 QC#19805 Add Start
                if (TAB_MAP_TMPL_QLFY.equals(bizMsg.xxDplyTab_H1.getValue())) {
                // 2017/09/12 QC#19805 Add End
                    ZYPEZDItemValueSetter.setValue(fMsg.defWhMapTmplCd, rs.getString(DEF_WH_MAP_TMPL_CD));
                    ZYPEZDItemValueSetter.setValue(fMsg.mdseItemClsTpDescTxt, rs.getString(MDSE_ITEM_CLS_TP_NM));
                    ZYPEZDItemValueSetter.setValue(fMsg.thirdPtyItemFlg, rs.getString(THIRD_PTY_ITEM_FLG));
                    ZYPEZDItemValueSetter.setValue(fMsg.fromPostCd, rs.getString(FROM_POST_CD));
                    ZYPEZDItemValueSetter.setValue(fMsg.toPostCd, rs.getString(TO_POST_CD));
                    ZYPEZDItemValueSetter.setValue(fMsg.onHndChkFlg, rs.getString(ON_HND_CHK_FLG));
                    ZYPEZDItemValueSetter.setValue(fMsg.otbdPrimOnHndChkWhCd, rs.getString(OTBD_PRIM_ON_HND_CHK_WH_CD));
                    ZYPEZDItemValueSetter.setValue(fMsg.otbdPrimOnHndChkSwhNm, rs.getString(OTBD_PRIM_ON_HND_CHK_SWH_NM));
                    ZYPEZDItemValueSetter.setValue(fMsg.otbdPrimOnHndLinSrcNm, rs.getString(OTBD_PRIM_ON_HND_LIN_SRC_NM));
                    ZYPEZDItemValueSetter.setValue(fMsg.otbdScdOnHndChkWhCd, rs.getString(OTBD_SCD_ON_HND_CHK_WH_CD));
                    ZYPEZDItemValueSetter.setValue(fMsg.otbdScdOnHndChkSwhNm, rs.getString(OTBD_SCD_ON_HND_CHK_SWH_NM));
                    ZYPEZDItemValueSetter.setValue(fMsg.otbdScdOnHndLineSrcNm, rs.getString(OTBD_SCD_ON_HND_LINE_SRC_NM));
                    ZYPEZDItemValueSetter.setValue(fMsg.otbdDefWhCd, rs.getString(OTBD_DEF_WH_CD));
                    ZYPEZDItemValueSetter.setValue(fMsg.otbdDefSwhNm, rs.getString(OTBD_DEF_SWH_NM));
                    ZYPEZDItemValueSetter.setValue(fMsg.otbdDefLineSrcNm, rs.getString(OTBD_DEF_LINE_SRC_NM));
                    // 2017/09/12 QC#19805 Del Start
//                    ZYPEZDItemValueSetter.setValue(fMsg.rmaDefWhCd, rs.getString(RMA_DEF_WH_CD));
//                    ZYPEZDItemValueSetter.setValue(fMsg.rmaDefSwhNm, rs.getString(RMA_DEF_SWH_NM));
//                    ZYPEZDItemValueSetter.setValue(fMsg.rmaDefLineSrcNm, rs.getString(RMA_DEF_LINE_SRC_NM));
                    // 2017/09/12 QC#19805 Del End
                // 2017/09/12 QC#19805 Add Start
                } else if (TAB_MAP_TMPL_QLFY_RMA.equals(bizMsg.xxDplyTab_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(rmafMsg.defWhMapTmplCd, rs.getString(DEF_WH_MAP_TMPL_CD));
                    ZYPEZDItemValueSetter.setValue(rmafMsg.mdseItemClsTpDescTxt, rs.getString(MDSE_ITEM_CLS_TP_NM));
                    ZYPEZDItemValueSetter.setValue(rmafMsg.thirdPtyItemFlg, rs.getString(THIRD_PTY_ITEM_FLG));
                    ZYPEZDItemValueSetter.setValue(rmafMsg.fromPostCd, rs.getString(FROM_POST_CD));
                    ZYPEZDItemValueSetter.setValue(rmafMsg.toPostCd, rs.getString(TO_POST_CD));
                    ZYPEZDItemValueSetter.setValue(rmafMsg.onHndChkFlg, rs.getString(ON_HND_CHK_FLG));
                    ZYPEZDItemValueSetter.setValue(rmafMsg.thirdPtyDspTpNm, rs.getString(THIRD_PTY_DSP_TP_NM));
                    ZYPEZDItemValueSetter.setValue(rmafMsg.dropRtrnVndNm, rs.getString(DROP_RTRN_VND_NM));
                    ZYPEZDItemValueSetter.setValue(rmafMsg.rmaDefWhCd, rs.getString(RMA_DEF_WH_CD));
                }
                // 2017/09/12 QC#19805 Add End

                csvOutFile.write();

                outputCount++;
            }
            if (outputCount == 0) {
                //NZZM0000E=0,No search results found.
                bizMsg.setMessageInfo(NZZM0000E, null);
            }

            csvOutFile.close();
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }
    // END 2016/03/23 K.Kojima [UT#56,ADD]
}
