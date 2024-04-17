CREATE OR REPLACE PACKAGE CANON_E580_AUTOCREATE_PO_PKG
AS
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
-- File Name  :   CANON_E580_AUTOCREATE_PO_PKG.sql                                                                                            --
-- Created On :   08/11/2015                                                                                                                --
-- Created By :   Santosh Mummidi                                                                                                           --
-- Purpose    :   This package includes all the program units related to creation of Purchase Orders from ITT workbench                     --
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
-- Modification History:                                                                                                                    --
-- Version    By                         Date           Comments                                                                            --
-- ------     ----------------------     -----------    --------                                                                            --
-- 1.0        Santosh Mummidi            11-Aug-2015    Initial                                                                             --
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
   TYPE ref_po_details IS REF CURSOR;

   PROCEDURE validate_itt_lines(   p_itt_num     IN  VARCHAR2
                                 , p_user        IN  VARCHAR2
                                 , x_ret_code    OUT VARCHAR2
                                 , x_ret_message OUT VARCHAR2
                               );

   PROCEDURE eligible_create_po_lines (   p_itt_number        IN  VARCHAR2
                                        , p_username          IN  VARCHAR2
										, p_called_from		  IN  VARCHAR2
                                        , x_create_po_tbl     OUT CANON_E580_CREATE_PO_TBL_TYP
                                        , x_return_flag       OUT VARCHAR2
                                        , x_return_message    OUT VARCHAR2
                                      );

   PROCEDURE update_po_itt(   p_itt_number IN VARCHAR2
                            , p_po_number  IN VARCHAR2
                            , p_err_msg    IN VARCHAR2
                            , p_user_name  IN VARCHAR2
                          );

   PROCEDURE derive_po_lines (  p_itt_num         IN  VARCHAR2
                               , p_user           IN  VARCHAR2
                               , x_create_po_tbl  OUT CANON_E580_CREATE_PO_TBL_TYP
                               , x_ret_flag       OUT VARCHAR2
                               , x_ret_message    OUT VARCHAR2
                             );
							 
   PROCEDURE derive_append_po_lines (   p_itt_num        IN  VARCHAR2
									   , p_cusa_po_num VARCHAR2
								       , p_dealer_po_num VARCHAR2
									   , p_user           IN  VARCHAR2
									   , x_create_po_tbl  OUT CANON_E580_CREATE_PO_TBL_TYP
									   , x_ret_flag       OUT VARCHAR2
									   , x_ret_message    OUT VARCHAR2
									);
									
   PROCEDURE get_append_po_numbers (   p_itt_num        IN  VARCHAR2
									, x_cusa_po_det    OUT ref_po_details
									, x_dealer_po_det  OUT ref_po_details
									, x_select_po      OUT VARCHAR2
									, x_ret_flag       OUT VARCHAR2
									, x_ret_message    OUT VARCHAR2
									);

END CANON_E580_AUTOCREATE_PO_PKG;
/

CREATE OR REPLACE PACKAGE BODY CANON_E580_AUTOCREATE_PO_PKG
IS
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
-- File Name  :   CANON_E580_AUTOCREATE_PO_PKG.sql                                                                                            --
-- Created On :   08/11/2015                                                                                                                --
-- Created By :   Santosh Mummidi                                                                                                           --
-- Purpose    :   This package includes all the program units related to creation of Purchase Orders from ITT workbench                     --
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
-- Modification History:                                                                                                                    --
-- Version    By                         Date           Comments                                                                            --
-- ------     ----------------------     -----------    --------                                                                            --
-- 1.0        Santosh Mummidi            11-Aug-2015    Initial                                                                             --
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
   
   g_glbl_cmpy_cd  VARCHAR2(3) := 'ADB';
   l_error_msg     VARCHAR2(10000) := 'No Error';
   l_itt_err_tbl   canon_e580_itt_util_pkg.itt_err_tbl_typ;
   
   /**************************************************************************************************
   This procedure is invoked from procedure eligible_create_po_lines used to perform below validations 
   to make sure selected lines are eligible to submit for PO creation API.
   1. Does an Approved Purchase Requisition exist
   2. Validate if the po/line has already created against this ITT line.
   3. Verify if ASL setup is exists / defined with correct attribute values.
   ***************************************************************************************************/
   PROCEDURE validate_itt_lines(   p_itt_num     IN  VARCHAR2
                                 , p_user        IN  VARCHAR2
                                 , x_ret_code    OUT VARCHAR2
                                 , x_ret_message OUT VARCHAR2
                               )
   IS
      CURSOR cur_itt_dtls
      IS
         SELECT DISTINCT  
                cih.itt_number
               ,cih.header_id
               ,cih.cmap_yes_no
               ,cih.vnd_cd dealer_cd
               ,cih.prnt_vnd_cd
               ,cih.dealer_whse_code
               ,cih.itt_admin_owner
               ,cih.order_number
               ,cih.order_status
               ,cih.party_id
               ,cih.ship_to_customer_id
               ,cih.site_use_id
               ,cil.product_sourced_by
               ,cil.line_id
               ,cil.line_number
               ,cil.item
               ,cil.merchandise
			   ,cil.itt_line_type
               ,(SELECT COUNT(1) FROM canon_e580_service_pricing_tbl
                 WHERE itt_number = cih.itt_number AND equip_model = cil.equip_model) maint_pricing_cnt
               ,cil.install_credit
               ,cil.finder_fee
         FROM   canon_e580_itt_headers_tbl cih
               ,canon_e580_itt_lines_tbl cil
         WHERE  cih.itt_number = cil.itt_number
         AND cih.order_number = cil.order_number
         AND cil.itt_line_type IN ('GOODS', 'EXPENSE')
         AND NVL(cil.cancelled_flag, 'N') = 'N'
		 AND cil.cusa_po_number IS NULL
         AND NVL(cil.merchandise, '999') NOT IN ('11','31')
		 AND NVL(cil.transaction_type_nm, 'XX') NOT IN (SELECT cvt.VAL2
                                              FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                             WHERE ct.cd_name = 'CANON_E580_LINE_TYPE'
                                               AND ct.cd_id = cvt.cd_id
                                               AND cvt.val1 = 'LINE_TYPE_02'
                                               AND NVL (cvt.end_date_active, SYSDATE) >= SYSDATE)
         AND cih.itt_number = p_itt_num
         ORDER BY cih.itt_number, cil.line_id;
      
      TYPE itt_tbl_typ IS TABLE OF cur_itt_dtls%ROWTYPE
      INDEX BY BINARY_INTEGER;      
      l_itt_tbl_typ  itt_tbl_typ;
      
      l_po_num           VARCHAR2(30);
      l_po_line_num      VARCHAR2(30);
      l_cnt              INTEGER := 0;
      l_ret_code         VARCHAR2(1) := 'S';
      l_ret_message      VARCHAR2(4000);
      l_itt_err_tbl      canon_e580_itt_util_pkg.itt_err_tbl_typ;
      l_asl_exists       VARCHAR2(1);
      l_shpg_pln_exists  VARCHAR2(1);
      l_shpg_pln_num     VARCHAR2(10);
      l_vnd_code         VARCHAR2(20);
      l_vnd_uom_qty      NUMBER;
      l_aval_po_qty      NUMBER;      
      l_vnd_min_ord_qty  NUMBER;
      l_vnd_incr_ord_qty NUMBER;
      l_base_ord_qty     NUMBER;
      l_prnt_vnd_code    VARCHAR2(50);
      l_vnd_loc_nm       VARCHAR2(50);
      l_install_item     VARCHAR2(30);
      l_finder_item      VARCHAR2(30);
      l_po_req_flg       VARCHAR2(5);
      l_so_close_flg     VARCHAR2(5);
      l_rte_sts_cd       VARCHAR2(5);
      l_shpg_sts_cd      VARCHAR2(5);
	  v_requsition_exists VARCHAR2(1);
   BEGIN
      dbms_output.put_line('in validate :');
      OPEN cur_itt_dtls;
      FETCH cur_itt_dtls BULK COLLECT INTO l_itt_tbl_typ;
      CLOSE cur_itt_dtls;
      
      IF (l_itt_tbl_typ.COUNT = 0)
      THEN
         x_ret_code    := 'E';
         x_ret_message := 'No eligible ITT lines exists for PO Creation';
         RETURN;
      END IF;
      
      FOR rec IN 1..l_itt_tbl_typ.COUNT
      LOOP      
         l_ret_code := 'S';
         
         -->Validate if the po/line has already created against this ITT line.
         IF (l_ret_code = 'S')
         THEN
            BEGIN
               SELECT  h.po_ord_num, l.po_ord_dtl_line_num INTO l_po_num, l_po_line_num
               FROM    po h, po_dtl l
               WHERE   l.cpo_ord_num = l_itt_tbl_typ(rec).order_number
               AND     h.po_ord_num = l.po_ord_num
               AND     l.cpo_dtl_line_num||'.'||l.cpo_dtl_line_sub_num = l_itt_tbl_typ(rec).line_number
               AND     l.mdse_cd = l_itt_tbl_typ(rec).item
               AND     l.trx_ref_num = l_itt_tbl_typ(rec).itt_number;

               IF (l_po_num IS NOT NULL)
               THEN
                  l_ret_code := 'E';
                  l_cnt := l_cnt+1;
                  l_itt_err_tbl(l_cnt).line_number   := l_itt_tbl_typ(rec).line_number;
                  l_itt_err_tbl(l_cnt).error_message :=      'ITT# '|| p_itt_num
                                                        || ' line# '|| l_itt_tbl_typ(rec).line_id
                                                        || ' already exists in PO# '|| l_po_num
                                                        || ' Line# '|| l_po_line_num;
               END IF;
            EXCEPTION
               WHEN OTHERS THEN
                  l_po_num := NULL;
            END;
         END IF;
		 
		 --To Validate if an approved purchase requisition exists for the ITT line
		IF (l_ret_code = 'S' AND (l_itt_tbl_typ(rec).itt_line_type = 'GOODS'))
         THEN
		 
		  BEGIN        -- a.    Does an Approved Purchase Requisition exist.
               SELECT 'Y'
                 INTO v_requsition_exists
                 FROM prch_req_dtl a			
				WHERE a.trx_ref_num = l_itt_tbl_typ(rec).order_number	
				  AND a.trx_ref_line_num||'.'|| a.trx_ref_line_sub_num = l_itt_tbl_typ(rec).line_number
				  AND a.ezcancelflag = '0'
				  AND a.glbl_cmpy_cd = g_glbl_cmpy_cd;

               IF NVL (v_requsition_exists, 'N') = 'N'
               THEN
                  l_ret_code := 'E';
				  l_cnt := l_cnt+1;
				  l_itt_err_tbl(l_cnt).line_number   := l_itt_tbl_typ(rec).line_number;
                  l_itt_err_tbl(l_cnt).error_message := 'No Approved requisition exists for ITT# '|| l_itt_tbl_typ(rec).itt_number
                                        || ' line# '|| l_itt_tbl_typ(rec).line_number;
										
               END IF;

           EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                  l_ret_code := 'E';
				  l_cnt := l_cnt+1;
				  l_itt_err_tbl(l_cnt).line_number   := l_itt_tbl_typ(rec).line_number;
                  l_itt_err_tbl(l_cnt).error_message := 'No Approved requisition exists for ITT# '|| l_itt_tbl_typ(rec).itt_number
                                     || ' line# '|| l_itt_tbl_typ(rec).line_number;

            WHEN OTHERS
            THEN
			  l_ret_code := 'E';
			  l_cnt := l_cnt+1;
			  l_itt_err_tbl(l_cnt).line_number   := l_itt_tbl_typ(rec).line_number;
              l_itt_err_tbl(l_cnt).error_message := 'Error while querying for Approved requisition '|| CHR (10)|| SQLCODE|| ' - '|| SQLERRM;

		  END;
		 
		END IF;
		
         
         -->Validate, if the line have the DEALER code assigned.
         IF ( l_itt_tbl_typ(rec).dealer_cd IS NULL AND l_ret_code = 'S') 
         THEN
            l_ret_code := 'E';
            l_cnt := l_cnt+1;
            l_itt_err_tbl(l_cnt).line_number   := l_itt_tbl_typ(rec).line_id;
            l_itt_err_tbl(l_cnt).error_message := 'Dealer not assigned for ITT# '|| p_itt_num;
         ELSIF ( l_itt_tbl_typ(rec).dealer_cd IS NOT NULL AND l_ret_code = 'S')
         THEN
            IF (l_itt_tbl_typ(rec).product_sourced_by = 'CSA')
            THEN
            --Derive cusa vendor code
            
               BEGIN
                  SELECT  canon_e580_itt_util_pkg.get_code_tbl_value('CANON_E580_SUPP_CSA_SOURCED',NULL)
                         ,canon_e580_itt_util_pkg.get_code_tbl_value('CANON_E580_SITE_CSA_SOURCED',NULL)
                  INTO    l_prnt_vnd_code
                         ,l_vnd_loc_nm
                  FROM DUAL;
                  
                  SELECT  v.vnd_cd
                  INTO    l_vnd_code
                  FROM  vnd v
                  WHERE v.loc_nm = l_vnd_loc_nm
                  AND   v.glbl_cmpy_cd = g_glbl_cmpy_cd;
               EXCEPTION
                  WHEN OTHERS THEN
                     l_prnt_vnd_code := NULL;
                     l_vnd_code := NULL;
               END;
            ELSIF (l_itt_tbl_typ(rec).product_sourced_by = 'DEALER')
            THEN
               l_vnd_code      := l_itt_tbl_typ(rec).dealer_cd;
               l_prnt_vnd_code := l_itt_tbl_typ(rec).prnt_vnd_cd;
            END IF;
            
            --Check if ASL setup exists for Item/Vendor.
            /*l_asl_exists := CANON_E580_ITT_UTIL_PKG.asl_setup_exists(  p_prnt_vnd_cd => l_prnt_vnd_code
                                                                      ,p_vnd_cd      => l_vnd_code
                                                                      ,p_mdse_cd     => l_itt_tbl_typ(rec).item
                                                                      ,p_error_msg   => l_ret_message
                                                                    );
            IF (l_asl_exists = 'N') 
            THEN
               l_ret_code := 'E';
               l_cnt := l_cnt+1;
               l_itt_err_tbl(l_cnt).line_number   := l_itt_tbl_typ(rec).line_id;
               l_itt_err_tbl(l_cnt).error_message := l_ret_message;
            END IF;*/
            
            --Check ASL setup for Finder Fee item / dealer selected
            /*IF (l_itt_tbl_typ(rec).finder_fee > 0 AND l_finder_item IS NULL)
            THEN
               SELECT canon_e580_itt_util_pkg.get_code_tbl_value('CANON_E580_EXP_ITEM','FINDER_FEE')
               INTO   l_finder_item
               FROM DUAL;
            
               l_asl_exists := CANON_E580_ITT_UTIL_PKG.asl_setup_exists(  p_prnt_vnd_cd => l_itt_tbl_typ(rec).prnt_vnd_cd
                                                                         ,p_vnd_cd      => l_itt_tbl_typ(rec).dealer_cd
                                                                         ,p_mdse_cd     => l_finder_item
                                                                         ,p_error_msg   => l_ret_message
                                                                       );
               IF (l_asl_exists = 'N') 
               THEN
                  l_ret_code := 'E';
                  l_cnt := l_cnt+1;
                  l_itt_err_tbl(l_cnt).line_number   := l_itt_tbl_typ(rec).line_id;
                  l_itt_err_tbl(l_cnt).error_message := l_ret_message||' (Finder Fee)';
               END IF;
            END IF;*/
            
            --Check ASL setup for Install Credit item / dealer selected
           /* IF (l_itt_tbl_typ(rec).install_credit > 0 AND l_install_item IS NULL)
            THEN
               SELECT canon_e580_itt_util_pkg.get_code_tbl_value('CANON_E580_EXP_ITEM','INSTALL_CREDIT')
               INTO   l_install_item
               FROM DUAL;
            
               l_asl_exists := CANON_E580_ITT_UTIL_PKG.asl_setup_exists(  p_prnt_vnd_cd => l_itt_tbl_typ(rec).prnt_vnd_cd
                                                                         ,p_vnd_cd      => l_itt_tbl_typ(rec).dealer_cd
                                                                         ,p_mdse_cd     => l_install_item
                                                                         ,p_error_msg   => l_ret_message
                                                                       );
               IF (l_asl_exists = 'N') 
               THEN
                  l_ret_code := 'E';
                  l_cnt := l_cnt+1;
                  l_itt_err_tbl(l_cnt).line_number   := l_itt_tbl_typ(rec).line_id;
                  l_itt_err_tbl(l_cnt).error_message := l_ret_message||' (Install Credit)';
               END IF;
            END IF;  */          
         END IF;
         
         --Validate the shipping plan if eligible for PO creation.
         IF (l_ret_code = 'S') AND (l_itt_tbl_typ(rec).itt_line_type = 'GOODS')
         THEN         
            BEGIN            
               SELECT  'Y',  shpg_pln_num, aval_po_qty, po_req_flg, so_close_flg, rte_sts_cd, shpg_sts_cd
               INTO    l_shpg_pln_exists, l_shpg_pln_num, l_aval_po_qty, l_po_req_flg, l_so_close_flg, l_rte_sts_cd, l_shpg_sts_cd
               FROM    shpg_pln
               WHERE   trx_hdr_num = l_itt_tbl_typ(rec).order_number
               AND     trx_line_num||'.'||trx_line_sub_num = l_itt_tbl_typ(rec).line_number
               AND     ezcancelflag = 0;
               IF (NVL(l_shpg_pln_exists,'N') = 'Y' AND l_aval_po_qty < 1)
               THEN
                  l_ret_code := 'E';
                  l_cnt := l_cnt+1;
                  l_itt_err_tbl(l_cnt).line_number   := l_itt_tbl_typ(rec).line_id;
                  l_itt_err_tbl(l_cnt).error_message := 'Insufficient PO Available Qty in Shipping Plan '||l_shpg_pln_num||' exists for ITT# '|| p_itt_num
                                                                                      || ', line number '|| l_itt_tbl_typ(rec).line_id;
               END IF;
               
               IF (NVL(l_po_req_flg,'N') = 'N')
               THEN
                  l_ret_code := 'E';
                  l_cnt := l_cnt+1;
                  l_itt_err_tbl(l_cnt).line_number := l_itt_tbl_typ(rec).line_id;
                  l_itt_err_tbl(l_cnt).error_message := 'PO Required Flag is not set on Shipping plan #'||l_shpg_pln_num;
               END IF;
               
               IF (l_so_close_flg = 'Y')
               THEN
                  l_ret_code := 'E';
                  l_cnt := l_cnt+1;
                  l_itt_err_tbl(l_cnt).line_number := l_itt_tbl_typ(rec).line_id;
                  l_itt_err_tbl(l_cnt).error_message := 'Shipping Order is closed on Shipping plan #'||l_shpg_pln_num;
               END IF;
               
               IF (l_rte_sts_cd <> 0)
               THEN
                  l_rte_sts_cd := 'E';
                  l_cnt := l_cnt+1;
                  l_itt_err_tbl(l_cnt).line_number := l_itt_tbl_typ(rec).line_id;
                  l_itt_err_tbl(l_cnt).error_message := 'Incorrect rte_sts_cd on Shipping plan #'||l_shpg_pln_num;
               END IF;
               
               IF (l_shpg_sts_cd <> 10)
               THEN
                  l_rte_sts_cd := 'E';
                  l_cnt := l_cnt+1;
                  l_itt_err_tbl(l_cnt).line_number := l_itt_tbl_typ(rec).line_id;
                  l_itt_err_tbl(l_cnt).error_message := 'Incorrect shipping status on Shipping plan #'||l_shpg_pln_num;
               END IF;
               
            EXCEPTION
               WHEN OTHERS THEN
                  l_ret_code := 'E';
                  l_cnt := l_cnt+1;
                  l_itt_err_tbl(l_cnt).line_number   := l_itt_tbl_typ(rec).line_id;
                  l_itt_err_tbl(l_cnt).error_message := 'No Shipping Plan exists for ITT# '|| p_itt_num
                                                                        || ', line number '|| l_itt_tbl_typ(rec).line_id;
            END;
         END IF;         
         
         -->Validate, if the line have Maintenance pricing details entered for MACHINES only
         IF ((l_ret_code = 'S') AND
             (l_itt_tbl_typ(rec).merchandise  = '10' AND l_itt_tbl_typ(rec).maint_pricing_cnt = 0))
         THEN
            l_ret_code := 'E';
            l_cnt := l_cnt+1;
            l_itt_err_tbl(l_cnt).line_number   := l_itt_tbl_typ(rec).line_id;
            l_itt_err_tbl(l_cnt).error_message := 'No Maintenance price info exists for ITT# '|| p_itt_num
                                                   || ', line number '|| l_itt_tbl_typ(rec).line_id;
         END IF;
         
         -->Validate, if the line have Dealer credits assigned.
         IF (l_ret_code = 'S' AND l_itt_tbl_typ(rec).install_credit IS NULL AND l_itt_tbl_typ(rec).itt_line_type = 'GOODS')
         THEN
            l_ret_code := 'E';
            l_cnt := l_cnt+1;
            l_itt_err_tbl(l_cnt).line_number   := l_itt_tbl_typ(rec).line_id;
            l_itt_err_tbl(l_cnt).error_message := 'Dealer credits not entered for ITT #'||p_itt_num||', ITT Line #'||l_itt_tbl_typ(rec).line_id;
         END IF;         
      END LOOP;
      
      IF (l_itt_err_tbl.COUNT > 0) 
      THEN
         l_ret_message := NULL;
         FOR r IN 1..l_itt_err_tbl.COUNT
         LOOP
            l_itt_err_tbl(r).package_name     := 'CANON_E580_AUTOCREATE_PO_PKG';
            l_itt_err_tbl(r).procedure_name   := 'VALIDATE_ITT_LINES';
            l_itt_err_tbl(r).itt_number       := p_itt_num;
            l_itt_err_tbl(r).created_by       := p_user;
            l_itt_err_tbl(r).creation_date    := SYSDATE;
            l_itt_err_tbl(r).last_updated_by  := p_user;
            l_itt_err_tbl(r).last_update_date := SYSDATE;
            
            l_ret_message := l_ret_message|| CHR(10) || l_itt_err_tbl(r).error_message;
         END LOOP;         
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
         x_ret_code    := 'E';
         x_ret_message := l_ret_message;
      ELSE
         x_ret_code    := 'S';
      END IF;
   EXCEPTION
      WHEN OTHERS THEN
         x_ret_code    := 'E';
         x_ret_message := 'Proc validate_itt_lines terminated due to Error: '||SUBSTR(SQLERRM,1,100);
   END validate_itt_lines;
   
   /*****************************************************************************************************
   This procedure gets invoked from derive_po_lines procedure to return ITT PO lines eligible for creation
   here the logic includes to derive the COA accounting segments based on input salesrep
   ******************************************************************************************************/
   PROCEDURE generate_account_info(   p_salesrep_cd IN VARCHAR2
                                    , p_line_num    IN VARCHAR2
                                    , p_acct_rec   OUT CANON_E580_POA_TYP_REC
                                  )
   IS
      l_act_info_rec   CANON_E580_POA_TYP_REC;
      l_cnt  NUMBER;
   BEGIN
      SELECT   COA_CMPY_CD
             , '000'     COA_AFFL_CD
             , COA_BR_CD
             , COA_CH_CD
             , COA_CC_CD
             ,(SELECT cvt.val2
               FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
	       WHERE ct.cd_name = 'CANON_E580_PO_EXP_DLR_CR_AC' AND ct.cd_id = cvt.cd_id
	       AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1)))
	                               COA_ACCT_CD
             , 'DH'                    COA_PROD_CD
             , '98'                    COA_PROJ_CD
             , NVL(COA_EXTN_CD,'000')  COA_EXTN_CD
      INTO     l_act_info_rec.COA_CMPY_CD
             , l_act_info_rec.COA_AFFL_CD
             , l_act_info_rec.COA_BR_CD
             , l_act_info_rec.COA_CH_CD
             , l_act_info_rec.COA_CC_CD
             , l_act_info_rec.COA_ACCT_CD
             , l_act_info_rec.COA_PROD_CD
             , l_act_info_rec.COA_PROJ_CD
             , l_act_info_rec.COA_EXTN_CD
      FROM    toc
      WHERE   toc_cd = p_salesrep_cd;
      
      l_act_info_rec.PO_ORD_DTL_LINE_NUM := p_line_num;
      l_act_info_rec.PO_ACCT_TP_CD := '02';

      p_acct_rec := l_act_info_rec;
   EXCEPTION
     WHEN OTHERS THEN
        p_acct_rec := l_act_info_rec;
   END;
   
   PROCEDURE derive_append_po_lines (   p_itt_num        IN  VARCHAR2   
								       , p_cusa_po_num VARCHAR2
								       , p_dealer_po_num VARCHAR2
									   , p_user           IN  VARCHAR2
									   , x_create_po_tbl  OUT CANON_E580_CREATE_PO_TBL_TYP
									   , x_ret_flag       OUT VARCHAR2
									   , x_ret_message    OUT VARCHAR2
									)
   IS      
   CURSOR po_hdr_dtls(p_itt_num1 VARCHAR2)
      IS
         SELECT DISTINCT
                cil.product_sourced_by
         FROM   canon_e580_itt_lines_tbl cil
         WHERE  cil.itt_number = p_itt_num1
         AND    NVL(cil.cancelled_flag, 'N') = 'N'
         AND    cil.itt_line_type = 'GOODS'
         AND    cil.cusa_po_number IS NULL
         AND    EXISTS(SELECT 1 FROM cpo_dtl WHERE cpo_ord_num = cil.order_number
                       AND cpo_dtl_line_num||'.'||cpo_dtl_line_sub_num = cil.line_number
                       AND glbl_cmpy_cd = g_glbl_cmpy_cd)
         AND NOT EXISTS ( SELECT 1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt             --FTS:Indexes do not exists on code tables
                          WHERE ct.cd_name = 'CANON_E580_PO_EXCLUDED_ITEMS' AND ct.cd_id = cvt.cd_id
                          AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
                          AND cvt.val2 = cil.item)
         UNION
        (SELECT DISTINCT 
                'DEALER'          product_sourced_by
         FROM   canon_e580_itt_lines_tbl cil
         WHERE  cil.itt_number = p_itt_num1
         AND    cil.itt_line_type = 'EXPENSE'
         AND    cil.cusa_po_number IS NULL
         UNION
         SELECT DISTINCT 
                'DEALER'          product_sourced_by
         FROM   canon_e580_itt_headers_tbl cih
         WHERE  cih.itt_number = p_itt_num1
         AND    NVL(cih.cancelled_flag, 'N') = 'N'
		 AND NOT EXISTS
		 (SELECT DISTINCT p.po_ord_num 
                                          FROM   po p, po_dtl pd
                                          WHERE  p.po_ord_num = pd.po_ord_num
                                          AND    pd.trx_ref_num = cih.itt_number
                                          AND    p.glbl_cmpy_cd = 'ADB'
                                          AND   pd.glbl_cmpy_cd = 'ADB'
                                          AND    p.ezcancelflag = '0'
                                          AND   pd.ezcancelflag = '0'
                                          AND    p.vnd_cd <> (SELECT v.vnd_cd
                                                              FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt, vnd v
                                                              WHERE  ct.cd_id = cvt.cd_id AND ct.cd_name = 'CANON_E580_SITE_CSA_SOURCED'
                                                              AND    cvt.val2 = v.loc_nm
                                                              AND    v.glbl_cmpy_cd = 'ADB'
                                                              AND   (SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)))
                                        ))
         ORDER BY 1;
      
      TYPE po_hdr_typ IS TABLE OF po_hdr_dtls%ROWTYPE
      INDEX BY BINARY_INTEGER;
	  
	  CURSOR po_append_lines_cur(p_itt_num2 VARCHAR2,
								 p_sourced_by VARCHAR2)
	  IS
	  SELECT COUNT(*)
	  FROM canon_e580_itt_headers_tbl cih, canon_e580_itt_lines_tbl cil
	  WHERE cih.itt_number = p_itt_num2
	    AND cih.order_number = cil.order_number
		AND cih.ship_to_customer_cd = cil.ship_to_customer_cd
		AND cil.product_sourced_by = p_sourced_by
		AND cil.attribute1 = 'A'
		AND cil.cusa_po_number IS NULL;
		
	CURSOR derive_append_po_number(p_itt_num2 VARCHAR2,
								   p_sourced_by VARCHAR2)
	  IS
	  SELECT MAX(cil.cusa_po_number)
	  FROM canon_e580_itt_headers_tbl cih, canon_e580_itt_lines_tbl cil
	  WHERE cih.itt_number = p_itt_num2
	    AND cih.order_number = cil.order_number
		--AND cih.ship_to_customer_cd = cil.ship_to_customer_cd
		AND cil.product_sourced_by = p_sourced_by
		AND cil.cusa_po_number IS NOT NULL;
      
      CURSOR po_lines_cur (p_itt_num IN VARCHAR2, p_prod_sourcedby IN VARCHAR2)
      IS
      SELECT p.*
      FROM
        ( SELECT   cil.order_number           ORDER_NUMBER
                 , cil.line_number            ITT_LINE_NUM
                 , cil.itt_line_type          ITT_LINE_TYPE
                 , cil.product_sourced_by     PRODUCT_SOURCED_BY
                 , l.mdse_cd                  ITEM_NUM
                 , l.mdse_nm                  ITEM_DESC
				 , l.set_mdse_cd              SET_ITEM
				 , l.ds_cpo_line_sub_num     DS_CPO_LINE_SUB_NUM
                 , cil.ordered_quantity       PO_REQ_QTY
                 , l.rtl_wh_cd               DEST_WH_CD
                 , l.rtl_swh_cd              DEST_SUB_WH_CD
                 , l.invty_loc_cd             DEST_INVTY_LOC_CD  
				 , dmi.coa_mdse_tp_cd         MERCHANDISE_TYPE
                 , prd.prch_req_num           PO_REQ_NUM
                 , prd.prch_req_line_num      PO_REQ_LINE_NUM
                 , prd.prch_req_line_sub_num  PO_REQ_LINE_SUB_NUM
                 , l.cust_uom_cd              UOM_CD
                 , cil.item_purchase_price    UNIT_PRICE
                 , '01'                       PO_LINE_TP_CD         --Goods
                 , l.cpo_ord_tp_cd            CPO_ORD_TP_CD
                 , sp.shpg_pln_num            SHPG_PLN_NUM                 
                 , l.COA_CMPY_CD
                 , l.COA_AFFL_CD
                 , l.COA_BR_CD
                 , l.COA_CH_CD
                 , l.COA_CC_CD
                 , l.COA_ACCT_CD
                 , l.COA_PROD_CD
                 , l.COA_PROJ_CD
                 , l.COA_EXTN_CD
          FROM   canon_e580_itt_lines_tbl cil
               , cpo h
               , cpo_dtl l
               , shpg_pln sp
			   , ord_line_src ols
			   , mdse dmi
			   , prch_req_dtl prd
          WHERE  cil.itt_number = p_itt_num
          AND    cil.product_sourced_by = p_prod_sourcedby
          AND    cil.order_number = h.cpo_ord_num
          AND    cil.order_number = l.cpo_ord_num
          AND    cil.line_number = l.cpo_dtl_line_num||'.'||l.cpo_dtl_line_sub_num
		  AND    l.mdse_cd = dmi.mdse_cd
		  AND    dmi.glbl_cmpy_cd = g_glbl_cmpy_cd
		  AND    dmi.ezcancelflag = '0'
          AND    l.cpo_ord_num = sp.trx_hdr_num
          AND    l.cpo_dtl_line_num = sp.trx_line_num
          AND    l.cpo_dtl_line_sub_num = sp.trx_line_sub_num
		  AND    l.cpo_ord_num = prd.trx_ref_num
          AND    l.cpo_dtl_line_num = prd.trx_ref_line_num
          AND    l.cpo_dtl_line_sub_num = prd.trx_ref_line_sub_num
		  AND    l.ord_line_src_cd = ols.ord_line_src_cd
          AND    cil.cusa_po_number IS NULL
          AND    cil.item = l.mdse_cd
          AND    cil.itt_line_type = 'GOODS'
          AND    NVL(cil.cancelled_flag, 'N') = 'N'
		  --AND   l.set_mdse_cd IS NULL
          AND   DECODE(dmi.coa_mdse_tp_cd, '11', 1, sp.aval_po_qty) > 0    --Sufficient qty for PO creation
          AND   sp.po_req_flg = 'Y'   --PO Required flag
          AND   sp.so_close_flg = 'N'
          AND   sp.rte_sts_cd = 0     --Un-Routed
          AND   sp.shpg_sts_cd = 10   --Validated
          AND   h.ezcancelflag  = 0
          AND   l.ezcancelflag  = 0
          AND   sp.ezcancelflag = 0
		  AND 	prd.ezcancelflag = 0
		  AND   prd.glbl_cmpy_cd = g_glbl_cmpy_cd
          AND   h.glbl_cmpy_cd  = g_glbl_cmpy_cd
          AND   l.glbl_cmpy_cd  = g_glbl_cmpy_cd
          AND   sp.glbl_cmpy_cd = g_glbl_cmpy_cd
		  AND  NOT EXISTS (SELECT 1 
							FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
							WHERE ct.cd_id = cvt.cd_id
							AND ct.cd_name = 'CANON_E580_PO_EXCL_LINE_TYPE'
							AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) 
							AND NVL(cvt.end_date_active, SYSDATE+1))
							AND cvt.val2 = ols.ord_line_src_nm)
          AND  NOT EXISTS(SELECT 1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                          WHERE ct.cd_name = 'CANON_E580_PO_EXCLUDED_ITEMS' AND ct.cd_id = cvt.cd_id
                          AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
                          AND cvt.val2 = l.mdse_cd)
        UNION ALL
        SELECT     NULL                       ORDER_NUMBER
                 , NULL                       ITT_LINE_NUM
                 , cil.itt_line_type          ITT_LINE_TYPE
                 , 'DEALER'                   PRODUCT_SOURCED_BY
                 , cil.item                   ITEM_NUM   ---cil.item
                 , cil.item_description       ITEM_DESC
				 , NULL                       SET_ITEM
				 , NULL                       DS_CPO_LINE_SUB_NUM
                 , cil.ordered_quantity       PO_REQ_QTY
				 , 'DS'   					  DEST_WH_CD
                 /*,(SELECT dcd.rtl_wh_cd FROM ds_cpo_dtl dcd, canon_e580_itt_lines_tbl l
                   WHERE l.itt_number = p_itt_num AND cil.order_number = l.order_number
                   AND cil.line_number = l.line_number AND dcd.cpo_ord_num = l.order_number
                   AND dcd.cpo_dtl_line_num||'.'||dcd.cpo_dtl_line_sub_num = l.line_number
                  )                           DEST_WH_CD*/
                 , NULL                       DEST_SUB_WH_CD
                 , NULL                       DEST_INVTY_LOC_CD  
                 , NULL                       MERCHANDISE_TYPE				 
                 , NULL                       PO_REQ_NUM
                 , NULL                       PO_REQ_LINE_NUM
                 , NULL                       PO_REQ_LINE_SUB_NUM
                 , 'EA'                       UOM_CD
                 , cil.item_purchase_price    UNIT_PRICE
                 , '02'                       PO_LINE_TP_CD         --Expense
                 , '70'                       CPO_ORD_TP_CD
                 , NULL                       SHPG_PLN_NUM
                 , NULL                       COA_CMPY_CD
                 , NULL                       COA_AFFL_CD
                 , NULL                       COA_BR_CD
                 , NULL                       COA_CH_CD
                 , NULL                       COA_CC_CD
                 , NULL                       COA_ACCT_CD
                 , NULL                       COA_PROD_CD
                 , NULL                       COA_PROJ_CD
                 , NULL                       COA_EXTN_CD
         FROM   canon_e580_itt_lines_tbl cil
         WHERE  cil.itt_line_type = 'EXPENSE'
         AND    cil.itt_number = p_itt_num
         AND    cil.cusa_po_number IS NULL
         UNION ALL
         SELECT    DISTINCT
                   NULL                       ORDER_NUMBER
                 , NULL                       ITT_LINE_NUM
                 , 'EXPENSE'                  ITT_LINE_TYPE
                 , 'DEALER'                   PRODUCT_SOURCED_BY
                 ,canon_e580_itt_util_pkg.get_code_tbl_value('CANON_E580_EXP_ITEM','FINDER_FEE')
                                              ITEM_NUM
                 , 'Finder Fee'               ITEM_DESC
				 , NULL                       SET_ITEM
				 , NULL                       DS_CPO_LINE_SUB_NUM
                 , 1                          PO_REQ_QTY
                 ,(SELECT dcd.rtl_wh_cd FROM cpo_dtl dcd, canon_e580_itt_lines_tbl l
                   WHERE l.itt_number = p_itt_num AND cil.order_number = l.order_number
                   AND cil.line_number = l.line_number AND dcd.cpo_ord_num = l.order_number
                   AND dcd.cpo_dtl_line_num||'.'||dcd.cpo_dtl_line_sub_num = l.line_number
                  )                           DEST_WH_CD
                 , NULL                       DEST_SUB_WH_CD
                 , NULL                       DEST_INVTY_LOC_CD    
                 , NULL                       MERCHANDISE_TYPE				 
                 , NULL                       PO_REQ_NUM
                 , NULL                       PO_REQ_LINE_NUM
                 , NULL                       PO_REQ_LINE_SUB_NUM
                 , 'EA'                       UOM_CD
                 ,(SELECT SUM(NVL(l.finder_fee, 0)) FROM canon_e580_itt_lines_tbl l
                   WHERE l.itt_line_type = 'GOODS' AND l.itt_number = p_itt_num  AND l.cusa_po_number IS NULL) 
                                              UNIT_PRICE
                 , '02'                       PO_LINE_TP_CD         --Expense
                 , '70'                       CPO_ORD_TP_CD
                 , NULL                       SHPG_PLN_NUM
                 , NULL                       COA_CMPY_CD
                 , NULL                       COA_AFFL_CD
                 , NULL                       COA_BR_CD
                 , NULL                       COA_CH_CD
                 , NULL                       COA_CC_CD
                 , NULL                       COA_ACCT_CD
                 , NULL                       COA_PROD_CD
                 , NULL                       COA_PROJ_CD
                 , NULL                       COA_EXTN_CD
         FROM   canon_e580_itt_lines_tbl cil
         WHERE  cil.itt_line_type = 'GOODS'
         AND    cil.itt_number = p_itt_num
         AND    cil.cusa_po_number IS NULL
         UNION ALL
         SELECT    DISTINCT
                   NULL                       ORDER_NUMBER
                 , NULL                       ITT_LINE_NUM
                 , 'EXPENSE'                  ITT_LINE_TYPE
                 , 'DEALER'                   PRODUCT_SOURCED_BY
                 , canon_e580_itt_util_pkg.get_code_tbl_value('CANON_E580_EXP_ITEM','INSTALL_CREDIT')
                                              ITEM_NUM
                 , 'Install Credit'           ITEM_DESC
				 , NULL                       SET_ITEM
				 , NULL                       DS_CPO_LINE_SUB_NUM
                 , 1                          PO_REQ_QTY
                 ,(SELECT dcd.rtl_wh_cd FROM cpo_dtl dcd, canon_e580_itt_lines_tbl l
                   WHERE l.itt_number = p_itt_num AND cil.order_number = l.order_number
                   AND cil.line_number = l.line_number AND dcd.cpo_ord_num = l.order_number
                   AND dcd.cpo_dtl_line_num||'.'||dcd.cpo_dtl_line_sub_num = l.line_number
                  )                           DEST_WH_CD
                 , NULL                       DEST_SUB_WH_CD
                 , NULL                       DEST_INVTY_LOC_CD    
                 , NULL                       MERCHANDISE_TYPE				 
                 , NULL                       PO_REQ_NUM
                 , NULL                       PO_REQ_LINE_NUM
                 , NULL                       PO_REQ_LINE_SUB_NUM
                 , 'EA'                       UOM_CD
                 , (SELECT SUM(NVL(l.install_credit, 0)) FROM canon_e580_itt_lines_tbl l
                    WHERE l.itt_line_type = 'GOODS' AND l.itt_number = p_itt_num  AND l.cusa_po_number IS NULL)
                                              UNIT_PRICE
                 , '02'                       PO_LINE_TP_CD         --Expense
                 , '70'                       CPO_ORD_TP_CD
                 , NULL                       SHPG_PLN_NUM
                 , NULL                       COA_CMPY_CD
                 , NULL                       COA_AFFL_CD
                 , NULL                       COA_BR_CD
                 , NULL                       COA_CH_CD
                 , NULL                       COA_CC_CD
                 , NULL                       COA_ACCT_CD
                 , NULL                       COA_PROD_CD
                 , NULL                       COA_PROJ_CD
                 , NULL                       COA_EXTN_CD
         FROM   canon_e580_itt_lines_tbl cil
         WHERE  cil.itt_line_type = 'GOODS'
         AND    cil.itt_number = p_itt_num
         AND    cil.cusa_po_number IS NULL
	  UNION ALL
		SELECT    DISTINCT
                   NULL                       ORDER_NUMBER
                 , NULL                       ITT_LINE_NUM
                 , 'EXPENSE'                  ITT_LINE_TYPE
                 , 'DEALER'                   PRODUCT_SOURCED_BY
                 , canon_e580_itt_util_pkg.get_code_tbl_value('CANON_E580_EXP_ITEM','INSTALL_CREDIT')
                                              ITEM_NUM
                 , 'Install Credit'           ITEM_DESC
				 , NULL                       SET_ITEM
				 , NULL                       DS_CPO_LINE_SUB_NUM
                 , 1                          PO_REQ_QTY
                 ,(SELECT dcd.rtl_wh_cd FROM cpo_dtl dcd, canon_e580_itt_lines_tbl l
                   WHERE l.itt_number = p_itt_num AND cil.order_number = l.order_number
                   AND cil.line_number = l.line_number AND dcd.cpo_ord_num = l.order_number
                   AND dcd.cpo_dtl_line_num||'.'||dcd.cpo_dtl_line_sub_num = l.line_number
                  )                           DEST_WH_CD
                 , NULL                       DEST_SUB_WH_CD
                 , NULL                       DEST_INVTY_LOC_CD    
                 , NULL                       MERCHANDISE_TYPE				 
                 , NULL                       PO_REQ_NUM
                 , NULL                       PO_REQ_LINE_NUM
                 , NULL                       PO_REQ_LINE_SUB_NUM
                 , 'EA'                       UOM_CD
                 , (SELECT SUM(NVL(l.install_credit, 0)) FROM canon_e580_itt_lines_tbl l
                    WHERE l.itt_line_type = 'GOODS' AND l.itt_number = p_itt_num)
                                              UNIT_PRICE
                 , '02'                       PO_LINE_TP_CD         --Expense
                 , '70'                       CPO_ORD_TP_CD
                 , NULL                       SHPG_PLN_NUM
                 , NULL                       COA_CMPY_CD
                 , NULL                       COA_AFFL_CD
                 , NULL                       COA_BR_CD
                 , NULL                       COA_CH_CD
                 , NULL                       COA_CC_CD
                 , NULL                       COA_ACCT_CD
                 , NULL                       COA_PROD_CD
                 , NULL                       COA_PROJ_CD
                 , NULL                       COA_EXTN_CD
         FROM   canon_e580_itt_lines_tbl cil
         WHERE  cil.itt_line_type = 'GOODS'
         AND    cil.itt_number = p_itt_num
         AND    cil.cusa_po_number IS NOT NULL
		 AND NOT EXISTS         
         (SELECT DISTINCT p.po_ord_num 
			  FROM   po p, po_dtl pd
			  WHERE  p.po_ord_num = pd.po_ord_num
			  AND    pd.trx_ref_num = cil.itt_number
			  AND    pd.mdse_desc_short_txt = 'Install Credit'
			  AND    p.glbl_cmpy_cd = 'ADB'
			  AND   pd.glbl_cmpy_cd = 'ADB'
			  AND    p.ezcancelflag = '0'
			  AND   pd.ezcancelflag = '0'
			  AND    p.vnd_cd <> (SELECT v.vnd_cd
								  FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt, vnd v
								  WHERE  ct.cd_id = cvt.cd_id AND ct.cd_name = 'CANON_E580_SITE_CSA_SOURCED' 
								  AND    cvt.val2 = v.loc_nm
								  AND    v.glbl_cmpy_cd = 'ADB'
								  AND   (SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)))
                                        )
	 UNION ALL
		SELECT    DISTINCT
                   NULL                       ORDER_NUMBER
                 , NULL                       ITT_LINE_NUM
                 , 'EXPENSE'                  ITT_LINE_TYPE
                 , 'DEALER'                   PRODUCT_SOURCED_BY
                 ,canon_e580_itt_util_pkg.get_code_tbl_value('CANON_E580_EXP_ITEM','FINDER_FEE')
                                              ITEM_NUM
                 , 'Finder Fee'               ITEM_DESC
				 , NULL                       SET_ITEM
				 , NULL                       DS_CPO_LINE_SUB_NUM
                 , 1                          PO_REQ_QTY
                 ,(SELECT dcd.rtl_wh_cd FROM cpo_dtl dcd, canon_e580_itt_lines_tbl l
                   WHERE l.itt_number = p_itt_num AND cil.order_number = l.order_number
                   AND cil.line_number = l.line_number AND dcd.cpo_ord_num = l.order_number
                   AND dcd.cpo_dtl_line_num||'.'||dcd.cpo_dtl_line_sub_num = l.line_number
                  )                           DEST_WH_CD
                 , NULL                       DEST_SUB_WH_CD
                 , NULL                       DEST_INVTY_LOC_CD    
                 , NULL                       MERCHANDISE_TYPE				 
                 , NULL                       PO_REQ_NUM
                 , NULL                       PO_REQ_LINE_NUM
                 , NULL                       PO_REQ_LINE_SUB_NUM
                 , 'EA'                       UOM_CD
                 ,(SELECT SUM(NVL(l.finder_fee, 0)) FROM canon_e580_itt_lines_tbl l
                   WHERE l.itt_line_type = 'GOODS' AND l.itt_number = p_itt_num) 
                                              UNIT_PRICE
                 , '02'                       PO_LINE_TP_CD         --Expense
                 , '70'                       CPO_ORD_TP_CD
                 , NULL                       SHPG_PLN_NUM
                 , NULL                       COA_CMPY_CD
                 , NULL                       COA_AFFL_CD
                 , NULL                       COA_BR_CD
                 , NULL                       COA_CH_CD
                 , NULL                       COA_CC_CD
                 , NULL                       COA_ACCT_CD
                 , NULL                       COA_PROD_CD
                 , NULL                       COA_PROJ_CD
                 , NULL                       COA_EXTN_CD
         FROM   canon_e580_itt_lines_tbl cil
         WHERE  cil.itt_line_type = 'GOODS'
         AND    cil.itt_number = p_itt_num
         AND    cil.cusa_po_number IS NOT NULL
		 AND NOT EXISTS         
         (SELECT DISTINCT p.po_ord_num 
			  FROM   po p, po_dtl pd
			  WHERE  p.po_ord_num = pd.po_ord_num
			  AND    pd.trx_ref_num = cil.itt_number
			  AND    pd.mdse_desc_short_txt = 'Finder Fee'
			  AND    p.glbl_cmpy_cd = 'ADB'
			  AND   pd.glbl_cmpy_cd = 'ADB'
			  AND    p.ezcancelflag = '0'
			  AND   pd.ezcancelflag = '0'
			  AND    p.vnd_cd <> (SELECT v.vnd_cd
								  FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt, vnd v
								  WHERE  ct.cd_id = cvt.cd_id AND ct.cd_name = 'CANON_E580_SITE_CSA_SOURCED' 
								  AND    cvt.val2 = v.loc_nm
								  AND    v.glbl_cmpy_cd = 'ADB'
								  AND   (SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)))
                                        )
       ) p
      WHERE  p.product_sourced_by = p_prod_sourcedby
      AND    ( (p.unit_price > 0 and p.ITEM_DESC IN ('Finder Fee','Install Credit')) OR
                p.ITEM_DESC NOT IN ('Finder Fee','Install Credit'))
      ORDER BY 1,2,5;
      
      TYPE po_line_typ IS TABLE OF po_lines_cur%ROWTYPE
      INDEX BY BINARY_INTEGER;
      
      l_itt_number     VARCHAR2(80) := p_itt_num;
      l_po_hdr_tbl     po_hdr_typ;
      l_po_line_tbl    po_line_typ;       
      l_itt_hdr_rec    canon_e580_itt_headers_tbl%ROWTYPE;
      l_po_desc        VARCHAR2(1000);
      h_cnt            INTEGER;
      l_cnt            INTEGER;
      l_vnd_code       VARCHAR2(20);
      l_prnt_vnd_code  VARCHAR2(20);
      l_ccy_cd         VARCHAR2(20);
      l_create_po_tbl  CANON_E580_CREATE_PO_TBL_TYP := CANON_E580_CREATE_PO_TBL_TYP();
      l_poh_msg_tbl    CANON_E580_POH_MSG_TBL_TYP := CANON_E580_POH_MSG_TBL_TYP();
      l_ser_info_tbl   CANON_E580_POS_TBL_TYP := CANON_E580_POS_TBL_TYP();
      l_act_info_tbl   CANON_E580_POA_TBL_TYP := CANON_E580_POA_TBL_TYP();
      l_pol_info_tbl   CANON_E580_POL_TBL_TYP := CANON_E580_POL_TBL_TYP();
      l_act_info_rec   CANON_E580_POA_TYP_REC;
      l_dest_invty_cd  VARCHAR2(50);
      l_dest_wh_cd     VARCHAR2(30);
      l_dest_swh_cd    VARCHAR2(30);
      l_line_num       VARCHAR2(10);
      l_po_qlfy_cd     VARCHAR2(5);
      l_date           VARCHAR2(10) := TO_CHAR(SYSDATE,'YYYYMMDD');
      l_datetime       VARCHAR2(10) := TO_CHAR(SYSDATE,'YYYYMMDD');
      l_step_no        NUMBER := 0;
      l_uom_cd         VARCHAR2(30);
      l_vnd_loc_nm     VARCHAR2(50);
	  l_item_cmpsn_type  	 VARCHAR2(50);
	  l_set_line_num     	 VARCHAR2(50);
	  l_parent_line_num  	 VARCHAR2(50);
	  l_disp_po_dtl_line_num VARCHAR2(50);
	  l_append_count NUMBER;
	  l_append_po_number VARCHAR2(50);
	  l_app_line_no NUMBER := 0;
	  
   BEGIN
      l_step_no := 1;
      SELECT * INTO l_itt_hdr_rec
      FROM   canon_e580_itt_headers_tbl cih
      WHERE  cih.itt_number = l_itt_number;

      OPEN  po_hdr_dtls( p_itt_num );
      FETCH po_hdr_dtls BULK COLLECT INTO l_po_hdr_tbl;
      CLOSE po_hdr_dtls;
      IF (l_po_hdr_tbl.COUNT = 0)
      THEN
         x_ret_flag    := 'E';
         x_ret_message := 'No eligible ITT lines exists for PO Creation';
         RETURN;
      END IF;
      
      l_step_no := 2;
      FOR rec IN 1..l_po_hdr_tbl.COUNT
      LOOP
         l_vnd_code := l_itt_hdr_rec.vnd_cd;   --Vendor Code
         IF (l_po_hdr_tbl(rec).product_sourced_by = 'DEALER')     ---DEALER: Possible of PO with Goods or Goods/Expense or only Expense.
         THEN
		    
			l_append_po_number := NULL;
			l_append_count := 0;
			
			OPEN po_append_lines_cur(l_itt_number, 'DEALER');
			FETCH po_append_lines_cur INTO l_append_count;
			CLOSE po_append_lines_cur;
			
			IF (p_dealer_po_num IS NULL) AND (l_append_count > 0) 
			THEN
			
				OPEN derive_append_po_number(l_itt_number, 'DEALER');
				FETCH derive_append_po_number INTO l_append_po_number;
				CLOSE derive_append_po_number;
			
			ELSIF p_dealer_po_num IS NOT NULL
			THEN
				l_append_po_number := p_dealer_po_num;
				
			END IF;
		 
            OPEN  po_lines_cur( l_itt_number, 'DEALER');
            FETCH po_lines_cur BULK COLLECT INTO l_po_line_tbl;
            CLOSE po_lines_cur;
			
            l_po_qlfy_cd    := 'DS';     --Dealer PO with Expense items
            l_prnt_vnd_code := l_itt_hdr_rec.prnt_vnd_cd;
            ---Derive Dealer Currency Code
            BEGIN
               SELECT  v.deal_ccy_cd, p.prnt_vnd_cd, v.vnd_cd
               INTO    l_ccy_cd, l_prnt_vnd_code, l_vnd_code
               FROM    vnd v, prnt_vnd p
               WHERE   v.vnd_cd = l_vnd_code
               AND     p.prnt_vnd_cd = l_prnt_vnd_code
               AND     v.prnt_vnd_pk = p.prnt_vnd_pk
               AND     v.glbl_cmpy_cd = g_glbl_cmpy_cd;
            EXCEPTION
               WHEN OTHERS THEN
                  l_ccy_cd        := 'USD';
                  l_prnt_vnd_code := NULL;
                  l_vnd_code      := NULL;
            END;            
         ELSIF (l_po_hdr_tbl(rec).product_sourced_by = 'CSA')       ---CSA: Always with Goods items only
         THEN
		 
			l_append_po_number := NULL;
			l_append_count := 0;
			
			OPEN po_append_lines_cur(l_itt_number, 'CSA');
			FETCH po_append_lines_cur INTO l_append_count;
			CLOSE po_append_lines_cur;
			
			IF (p_cusa_po_num IS NULL) AND (l_append_count > 0) 
			THEN
			
			BEGIN
				OPEN derive_append_po_number(l_itt_number, 'CSA');
				FETCH derive_append_po_number INTO l_append_po_number;
				CLOSE derive_append_po_number;
				
			EXCEPTION
			WHEN TOO_MANY_ROWS THEN
				x_ret_flag    := 'E';
				x_ret_message := 'More than one PO found for the ITT number: '||l_itt_number||' for sourced by CSA';
				RETURN;
			WHEN OTHERS THEN	
				x_ret_flag    := 'E';
				x_ret_message := 'Deriving existing PO information terminated due to Error: '||SUBSTR(SQLERRM,1,100);
				RETURN;
			END;	
			
			ELSIF p_cusa_po_num IS NOT NULL
			THEN
				l_append_po_number := p_cusa_po_num;
			END IF;
			
            OPEN  po_lines_cur( l_itt_number, 'CSA');
            FETCH po_lines_cur BULK COLLECT INTO l_po_line_tbl;
            CLOSE po_lines_cur;
            l_po_qlfy_cd := 'DS';    --CUSA PO with Goods items
            
            --Derive CUSA Parent vendor code / vendor site code
            BEGIN
               SELECT  canon_e580_itt_util_pkg.get_code_tbl_value('CANON_E580_SUPP_CSA_SOURCED',NULL)
                      ,canon_e580_itt_util_pkg.get_code_tbl_value('CANON_E580_SITE_CSA_SOURCED',NULL)
               INTO    l_prnt_vnd_code
                      ,l_vnd_loc_nm
               FROM DUAL;
            EXCEPTION
               WHEN OTHERS THEN
                  l_prnt_vnd_code := NULL;
                  l_vnd_loc_nm    := NULL;
            END;
            
            ---Derive Dealer Currency Code
            BEGIN
               SELECT  v.deal_ccy_cd, p.prnt_vnd_cd, v.vnd_cd
               INTO    l_ccy_cd, l_prnt_vnd_code, l_vnd_code
               FROM    vnd v, prnt_vnd p
               WHERE   v.loc_nm = l_vnd_loc_nm
               AND     p.prnt_vnd_cd = l_prnt_vnd_code
               AND     v.prnt_vnd_pk = p.prnt_vnd_pk
               AND     v.glbl_cmpy_cd = g_glbl_cmpy_cd;
            EXCEPTION
               WHEN OTHERS THEN
                  l_ccy_cd        := 'USD';
                  l_prnt_vnd_code := NULL;
                  l_vnd_code      := NULL;
            END;
            
         END IF;
         l_step_no := 3;

		/*IF l_append_po_number IS NULL 
		THEN
         l_po_desc := 'ITT# '||l_itt_hdr_rec.itt_number||', SO# '||l_itt_hdr_rec.order_number
                             ||', DLR CODE : '|| l_vnd_code
                             ||', CUSTOMER - '|| l_itt_hdr_rec.ship_to_customer;
							 
		END IF;*/

         l_step_no := 4;
         IF (l_po_line_tbl.COUNT > 0) 
         THEN
            --Derive PO header details            
            l_dest_invty_cd := l_po_line_tbl(1).DEST_INVTY_LOC_CD;
            l_dest_wh_cd    := l_po_line_tbl(1).DEST_WH_CD;
            l_dest_swh_cd   := l_po_line_tbl(1).DEST_SUB_WH_CD;            

            l_create_po_tbl.EXTEND;
            h_cnt := l_create_po_tbl.COUNT;
            l_create_po_tbl(h_cnt) := CANON_E580_CREATE_PO_TYP_REC( 
                                                                NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                                NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                                NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                                NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                                NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL
                                                              );
															  
		/*IF l_append_po_number IS NULL 
		THEN
            l_create_po_tbl(h_cnt).DML_MODE          := '1';                                    --Mode    1-Create, 2-Update, 5-PO Merge.
		ELSE*/
			l_create_po_tbl(h_cnt).DML_MODE          := '2';  
			l_create_po_tbl(h_cnt).PO_ORD_NUM        := l_append_po_number;
			
			SELECT MAX(TO_NUMBER(po_ord_dtl_line_num)) line_number 
			INTO l_app_line_no
			FROM po_dtl 
			WHERE po_ord_num=l_append_po_number;
			
		--END IF;
            l_create_po_tbl(h_cnt).EVENT             := '2';                                    --Event   1-Save, 2-Submit
            l_create_po_tbl(h_cnt).PROC_DT           := TO_CHAR(SYSDATE,'YYYYMMDD');            --Process Date
            l_create_po_tbl(h_cnt).XX_RQST_TS        := TO_CHAR(SYSDATE,'YYYYMMDDHHMMSSSSS');   --Request timestamp
            l_create_po_tbl(h_cnt).DS_PO_TP_CD       := 'IO';                                   --IO/ITT Outbound PO
            l_create_po_tbl(h_cnt).PO_QLFY_CD        := l_po_qlfy_cd;                           --DS-Dropship or NULL
            l_create_po_tbl(h_cnt).PO_SUBMT_PSN_CD   := p_user;                                 --Buyer
            l_create_po_tbl(h_cnt).PO_SUBMT_TS       := TO_CHAR(SYSDATE,'YYYYMMDDHHMMSSSSS');
            l_create_po_tbl(h_cnt).PO_APVL_STS_CD    := '21';
            l_create_po_tbl(h_cnt).PO_APVL_PSN_CD    := p_user;
            l_create_po_tbl(h_cnt).PO_APVL_DT        := TO_CHAR(SYSDATE,'YYYYMMDD');
            l_create_po_tbl(h_cnt).PO_APVL_TS        := TO_CHAR(SYSDATE,'YYYYMMDDHHMMSSSSS');
            l_create_po_tbl(h_cnt).WF_FLG            := 'N';
            
            l_create_po_tbl(h_cnt).DEST_RTL_WH_CD    := l_dest_wh_cd;                           --Destination Retail Warehouse Code
            l_create_po_tbl(h_cnt).PO_ORD_SRC_CD     := '41';                                   --41:ITT Outbound
            l_create_po_tbl(h_cnt).VND_CD            := l_vnd_code;                             --Vendor:Dealer/CUSA
            l_create_po_tbl(h_cnt).PRNT_VND_CD       := l_prnt_vnd_code;
            l_create_po_tbl(h_cnt).DEAL_CCY_CD       := l_ccy_cd;
            l_create_po_tbl(h_cnt).VND_DROP_SHIP_FLG := 'Y';
            l_create_po_tbl(h_cnt).PRCH_GRP_CD       := 'ESS';                                  --TBC: 01/ESS/Enterprise Services and Solutions
            l_create_po_tbl(h_cnt).SHIP_TO_CUST_CD   := l_itt_hdr_rec.SHIP_TO_CUSTOMER_CD;     
			l_create_po_tbl(h_cnt).CTAC_PSN_NM   	 := l_itt_hdr_rec.CUSTOMER_CONTACT_NAME;
               --END IF;
              
            l_poh_msg_tbl.DELETE;
            l_poh_msg_tbl.EXTEND;
            l_cnt := l_poh_msg_tbl.COUNT;
            l_poh_msg_tbl(l_cnt) := CANON_E580_POH_MSG_TYP_REC(NULL,NULL,NULL,NULL,NULL,NULL,NULL);
            l_poh_msg_tbl(l_cnt).PO_MSG_TP_CD             := '05';                             --TBC: 02-Internal message, 03-special instructions, 04-receiver note, 05-shipper note
            l_poh_msg_tbl(l_cnt).XX_DS_MULT_MSG_DPLY_TXT  := l_po_desc;
            l_create_po_tbl(h_cnt).PO_HDR_MSG_INFO_TBL    := l_poh_msg_tbl;
			
            l_pol_info_tbl.DELETE;
            l_act_info_tbl.DELETE;
            FOR rec IN 1..l_po_line_tbl.COUNT
            LOOP            
				l_app_line_no := l_app_line_no + 1;
               --Derive PO_QLFY_CD at header level based on ITT_LINE_TYPE
               IF (l_create_po_tbl(h_cnt).PO_QLFY_CD IS NULL AND l_po_line_tbl(rec).ITT_LINE_TYPE = 'GOODS')
               THEN
                  l_create_po_tbl(h_cnt).PO_QLFY_CD := 'DS';
               END IF;
               
               --Derive UOM
               IF (l_po_line_tbl(rec).UOM_CD IS NULL) 
               THEN
                  BEGIN
                     SELECT pkg_uom_cd
                     INTO   l_uom_cd
                     FROM   mdse_store_pkg
                     WHERE  mdse_cd = l_po_line_tbl(rec).ITEM_NUM
                     AND    glbl_cmpy_cd = g_glbl_cmpy_cd;
                  EXCEPTION
                     WHEN OTHERS THEN
                        l_uom_cd := 'EA';
                  END;
               END IF;
			   
			                 
               l_pol_info_tbl.EXTEND;
               l_cnt := l_pol_info_tbl.COUNT;
               l_pol_info_tbl(l_cnt) := CANON_E580_POL_TYP_REC(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                               NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                               NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                               NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
			/*IF l_append_po_number IS NULL 
			THEN
               l_line_num := SUBSTR(l_po_line_tbl(rec).ITT_LINE_NUM,1, 3);
			   IF l_po_line_tbl(rec).MERCHANDISE_TYPE = '11'
               THEN
                     l_item_cmpsn_type := '1';
					 l_set_line_num := l_line_num;
					 l_disp_po_dtl_line_num := TO_CHAR(TO_NUMBER(SUBSTR(l_po_line_tbl(rec).ITT_LINE_NUM,1, 3)))||'.'|| TO_CHAR(TO_NUMBER(SUBSTR(l_po_line_tbl(rec).ITT_LINE_NUM,5, 3)));
			   ELSIF l_po_line_tbl(rec).SET_ITEM IS NOT NULL
			   THEN
                     l_item_cmpsn_type := '2';
					 l_parent_line_num := l_set_line_num;
					 l_disp_po_dtl_line_num := TO_CHAR(TO_NUMBER(SUBSTR(l_po_line_tbl(rec).ITT_LINE_NUM,1, 3)))||'.'|| TO_CHAR(TO_NUMBER(SUBSTR(l_po_line_tbl(rec).ITT_LINE_NUM,5, 3)));
			   ELSIF l_po_line_tbl(rec).itt_line_type = 'EXPENSE'
			   THEN
					 l_disp_po_dtl_line_num := TO_CHAR(l_cnt)||'.0';
					 l_item_cmpsn_type := NULL;
					 l_parent_line_num := NULL;
			   ELSE
					 l_disp_po_dtl_line_num := TO_CHAR(TO_NUMBER(SUBSTR(l_po_line_tbl(rec).ITT_LINE_NUM,1, 3)))||'.'|| TO_CHAR(TO_NUMBER(SUBSTR(l_po_line_tbl(rec).ITT_LINE_NUM,5, 3)));
					 l_item_cmpsn_type := NULL;
					 l_parent_line_num := NULL;
               END IF;
			   
			ELSE*/
			/*IF l_append_po_number IS NULL 
			THEN
               l_line_num := LPAD (l_cnt, 3, '0');
			   IF l_po_line_tbl(rec).MERCHANDISE_TYPE = '11'
               THEN
                     l_item_cmpsn_type := '1';
					 l_set_line_num := l_line_num;
					 l_disp_po_dtl_line_num := TO_CHAR(l_cnt)||'.0';
			   ELSIF l_po_line_tbl(rec).SET_ITEM IS NOT NULL
			   THEN
                     l_item_cmpsn_type := '2';
					 l_parent_line_num := l_set_line_num;
					 l_disp_po_dtl_line_num := TO_NUMBER(l_set_line_num) ||'.'|| l_po_line_tbl(rec).DS_CPO_LINE_SUB_NUM;
			   ELSE
					 l_disp_po_dtl_line_num := TO_CHAR(l_cnt)||'.0';
					 l_item_cmpsn_type := NULL;
					 l_parent_line_num := NULL;
               END IF;
			   
			ELSE*/
				l_line_num := LPAD (l_app_line_no, 3, '0');
				
				IF l_po_line_tbl(rec).MERCHANDISE_TYPE = '11'
               THEN
                     l_item_cmpsn_type := '1';
					 l_set_line_num := l_line_num;
					 l_disp_po_dtl_line_num := TO_CHAR(l_app_line_no)||'.0';
			   ELSIF l_po_line_tbl(rec).SET_ITEM IS NOT NULL
			   THEN
                     l_item_cmpsn_type := '2';
					 l_parent_line_num := l_set_line_num;
					 l_disp_po_dtl_line_num := TO_NUMBER(l_set_line_num) ||'.'|| l_po_line_tbl(rec).DS_CPO_LINE_SUB_NUM;
			   ELSE
					 l_disp_po_dtl_line_num := TO_CHAR(l_app_line_no)||'.0';
					 l_item_cmpsn_type := NULL;
					 l_parent_line_num := NULL;
               END IF;
			--END IF;
			   
			   
			   
               l_pol_info_tbl(l_cnt).PO_ORD_DTL_LINE_NUM       := l_line_num;
               l_pol_info_tbl(l_cnt).DISP_PO_DTL_LINE_NUM      := l_disp_po_dtl_line_num;
               l_pol_info_tbl(l_cnt).PO_LINE_TP_CD             := l_po_line_tbl(rec).PO_LINE_TP_CD;
			   l_pol_info_tbl(l_cnt).PO_MDSE_CMPSN_TP_CD       := l_item_cmpsn_type;
			   l_pol_info_tbl(l_cnt).SET_PO_ORD_DTL_LINE_NUM   := l_parent_line_num;
               l_pol_info_tbl(l_cnt).MDSE_CD                   := l_po_line_tbl(rec).ITEM_NUM;
               l_pol_info_tbl(l_cnt).MDSE_DESC_SHORT_TXT       := l_po_line_tbl(rec).ITEM_DESC;
               l_pol_info_tbl(l_cnt).PO_QTY                    := l_po_line_tbl(rec).PO_REQ_QTY;
               l_pol_info_tbl(l_cnt).PO_DISP_QTY               := l_po_line_tbl(rec).PO_REQ_QTY;
               l_pol_info_tbl(l_cnt).ORD_QTY                   := l_po_line_tbl(rec).PO_REQ_QTY;
               l_pol_info_tbl(l_cnt).PO_DISP_UOM_CD            := NVL(l_po_line_tbl(rec).UOM_CD, l_uom_cd);
               l_pol_info_tbl(l_cnt).CUST_UOM_CD               := NVL(l_po_line_tbl(rec).UOM_CD, l_uom_cd);
               l_pol_info_tbl(l_cnt).ENT_DEAL_NET_UNIT_PRC_AMT := l_po_line_tbl(rec).UNIT_PRICE;
               l_pol_info_tbl(l_cnt).DEST_RTL_SWH_CD           := l_dest_swh_cd;
               l_pol_info_tbl(l_cnt).INVTY_LOC_CD              := l_dest_wh_cd||l_dest_swh_cd;
               l_pol_info_tbl(l_cnt).RQST_RCV_DT               := TO_CHAR(SYSDATE,'YYYYMMDD');
               l_pol_info_tbl(l_cnt).PRCH_REQ_NUM              := l_po_line_tbl(rec).PO_REQ_NUM;
               l_pol_info_tbl(l_cnt).PRCH_REQ_LINE_NUM         := l_po_line_tbl(rec).PO_REQ_LINE_NUM;
               l_pol_info_tbl(l_cnt).PRCH_REQ_LINE_SUB_NUM     := l_po_line_tbl(rec).PO_REQ_LINE_SUB_NUM;
               l_pol_info_tbl(l_cnt).CPO_ORD_NUM               := l_po_line_tbl(rec).ORDER_NUMBER;
               l_pol_info_tbl(l_cnt).CPO_DTL_LINE_NUM          := SUBSTR(l_po_line_tbl(rec).itt_line_num,1,INSTR(l_po_line_tbl(rec).itt_line_num,'.')-1);
               l_pol_info_tbl(l_cnt).CPO_DTL_LINE_SUB_NUM      := SUBSTR(l_po_line_tbl(rec).itt_line_num,INSTR(l_po_line_tbl(rec).itt_line_num,'.')+1);
               l_pol_info_tbl(l_cnt).CPO_ORD_TP_CD             := l_po_line_tbl(rec).CPO_ORD_TP_CD;
               l_pol_info_tbl(l_cnt).SHPG_PLN_NUM              := l_po_line_tbl(rec).SHPG_PLN_NUM;
               l_pol_info_tbl(l_cnt).TRX_REF_NUM               := p_itt_num;
               l_pol_info_tbl(l_cnt).TRX_REF_LINE_NUM          := SUBSTR(l_po_line_tbl(rec).itt_line_num,1,INSTR(l_po_line_tbl(rec).itt_line_num,'.')-1);
               l_pol_info_tbl(l_cnt).TRX_REF_LINE_SUB_NUM      := SUBSTR(l_po_line_tbl(rec).itt_line_num,INSTR(l_po_line_tbl(rec).itt_line_num,'.')+1);
               
               IF (      l_po_line_tbl(rec).COA_CMPY_CD IS NULL AND l_po_line_tbl(rec).COA_AFFL_CD IS NULL 
                    AND  l_po_line_tbl(rec).COA_BR_CD IS NULL AND l_po_line_tbl(rec).COA_CH_CD IS NULL 
                    AND  l_po_line_tbl(rec).COA_CC_CD IS NULL AND l_po_line_tbl(rec).COA_ACCT_CD IS NULL 
                    AND  l_po_line_tbl(rec).COA_PROD_CD IS NULL AND l_po_line_tbl(rec).COA_PROJ_CD IS NULL 
                    AND  l_po_line_tbl(rec).COA_EXTN_CD IS NULL)
               THEN
                  generate_account_info(   p_salesrep_cd => l_itt_hdr_rec.salesrep_cd
		                         , p_line_num    => l_line_num
		                         , p_acct_rec    => l_act_info_rec
                                       );
                  l_act_info_tbl.EXTEND;
                  l_cnt := l_act_info_tbl.COUNT;
                  l_act_info_tbl(l_cnt) := CANON_E580_POA_TYP_REC(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
                  l_act_info_tbl(l_cnt).PO_ORD_DTL_LINE_NUM := l_line_num;
                  l_act_info_tbl(l_cnt).PO_ACCT_TP_CD := '02';
                  l_act_info_tbl(l_cnt).COA_CMPY_CD   := NVL(l_act_info_rec.COA_CMPY_CD,'ADB');
                  l_act_info_tbl(l_cnt).COA_AFFL_CD   := NVL(l_act_info_rec.COA_AFFL_CD,'000');
                  l_act_info_tbl(l_cnt).COA_BR_CD     := NVL(l_act_info_rec.COA_BR_CD,'000');
                  l_act_info_tbl(l_cnt).COA_CH_CD     := NVL(l_act_info_rec.COA_CH_CD,'000');
                  l_act_info_tbl(l_cnt).COA_CC_CD     := NVL(l_act_info_rec.COA_CC_CD,'000000');
                  l_act_info_tbl(l_cnt).COA_ACCT_CD   := NVL(l_act_info_rec.COA_ACCT_CD,'00000001');
                  l_act_info_tbl(l_cnt).COA_PROD_CD   := NVL(l_act_info_rec.COA_PROD_CD,'ZZ');
                  l_act_info_tbl(l_cnt).COA_PROJ_CD   := NVL(l_act_info_rec.COA_PROJ_CD,'00');
                  l_act_info_tbl(l_cnt).COA_EXTN_CD   := NVL(l_act_info_rec.COA_EXTN_CD,'000');
               ELSE
                  l_act_info_tbl.EXTEND;
                  l_cnt := l_act_info_tbl.COUNT;
                  l_act_info_tbl(l_cnt) := CANON_E580_POA_TYP_REC(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
                  l_act_info_tbl(l_cnt).PO_ORD_DTL_LINE_NUM := l_line_num;
                  l_act_info_tbl(l_cnt).PO_ACCT_TP_CD := '02';
                  l_act_info_tbl(l_cnt).COA_CMPY_CD   := NVL(l_po_line_tbl(rec).COA_CMPY_CD,'ADB');
                  l_act_info_tbl(l_cnt).COA_AFFL_CD   := NVL(l_po_line_tbl(rec).COA_AFFL_CD,'000');
                  l_act_info_tbl(l_cnt).COA_BR_CD     := NVL(l_po_line_tbl(rec).COA_BR_CD,'000');
                  l_act_info_tbl(l_cnt).COA_CH_CD     := NVL(l_po_line_tbl(rec).COA_CH_CD,'000');
                  l_act_info_tbl(l_cnt).COA_CC_CD     := NVL(l_po_line_tbl(rec).COA_CC_CD,'000000');
                  l_act_info_tbl(l_cnt).COA_ACCT_CD   := NVL(l_po_line_tbl(rec).COA_ACCT_CD,'00000001');
                  l_act_info_tbl(l_cnt).COA_PROD_CD   := NVL(l_po_line_tbl(rec).COA_PROD_CD,'ZZ');
                  l_act_info_tbl(l_cnt).COA_PROJ_CD   := NVL(l_po_line_tbl(rec).COA_PROJ_CD,'00');
                  l_act_info_tbl(l_cnt).COA_EXTN_CD   := NVL(l_po_line_tbl(rec).COA_EXTN_CD,'000');
               END IF;
            END LOOP;
            l_create_po_tbl(h_cnt).PO_LINE_INFO_TBL := l_pol_info_tbl;
            l_create_po_tbl(h_cnt).PO_ACCT_INFO_TBL := l_act_info_tbl;
           END IF;
      END LOOP;
      l_step_no := 5;

      x_create_po_tbl  := l_create_po_tbl;
      x_ret_flag       := 'S';
      x_ret_message    := '';
   EXCEPTION
      WHEN OTHERS THEN
         x_ret_flag    := 'E';
         x_ret_message := l_step_no||', Please retry this action, Proc create_po_prc terminated due to Error: '||SUBSTR(SQLERRM,1,100);
   END;  
   
   
   PROCEDURE get_append_po_numbers (   p_itt_num        IN  VARCHAR2
									 , x_cusa_po_det    OUT ref_po_details
									 , x_dealer_po_det  OUT ref_po_details
									 , x_select_po      OUT VARCHAR2
									 , x_ret_flag       OUT VARCHAR2
									 , x_ret_message    OUT VARCHAR2
									)
	IS
	
	CURSOR po_hdr_dtls(p_itt_num1 VARCHAR2)
      IS
         SELECT DISTINCT
                cil.product_sourced_by
         FROM   canon_e580_itt_lines_tbl cil
         WHERE  cil.itt_number = p_itt_num1
         AND    NVL(cil.cancelled_flag, 'N') = 'N'
         AND    cil.itt_line_type = 'GOODS'
         AND    cil.cusa_po_number IS NULL
         AND    EXISTS(SELECT 1 FROM cpo_dtl WHERE cpo_ord_num = cil.order_number
                       AND cpo_dtl_line_num||'.'||cpo_dtl_line_sub_num = cil.line_number
                       AND glbl_cmpy_cd = g_glbl_cmpy_cd)
         AND NOT EXISTS ( SELECT 1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt             --FTS:Indexes do not exists on code tables
                          WHERE ct.cd_name = 'CANON_E580_PO_EXCLUDED_ITEMS' AND ct.cd_id = cvt.cd_id
                          AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
                          AND cvt.val2 = cil.item)
         UNION
        (SELECT DISTINCT 
                'DEALER'          product_sourced_by
         FROM   canon_e580_itt_lines_tbl cil
         WHERE  cil.itt_number = p_itt_num1
         AND    cil.itt_line_type = 'EXPENSE'
         AND    cil.cusa_po_number IS NULL
         UNION
         SELECT DISTINCT 
                'DEALER'          product_sourced_by
         FROM   canon_e580_itt_headers_tbl cih
         WHERE  cih.itt_number = p_itt_num1
         AND    NVL(cih.cancelled_flag, 'N') = 'N'
		 AND NOT EXISTS
		 (SELECT DISTINCT p.po_ord_num 
                                          FROM   po p, po_dtl pd
                                          WHERE  p.po_ord_num = pd.po_ord_num
                                          AND    pd.trx_ref_num = cih.itt_number
                                          AND    p.glbl_cmpy_cd = 'ADB'
                                          AND   pd.glbl_cmpy_cd = 'ADB'
                                          AND    p.ezcancelflag = '0'
                                          AND   pd.ezcancelflag = '0'
                                          AND    p.vnd_cd <> (SELECT v.vnd_cd
                                                              FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt, vnd v
                                                              WHERE  ct.cd_id = cvt.cd_id AND ct.cd_name = 'CANON_E580_SITE_CSA_SOURCED'
                                                              AND    cvt.val2 = v.loc_nm
                                                              AND    v.glbl_cmpy_cd = 'ADB'
                                                              AND   (SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)))
                                        ))
         ORDER BY 1;
		 
	
		CURSOR po_count_cur(p_itt_num1   VARCHAR2,
							p_sourced_by VARCHAR2)
		IS
		SELECT COUNT(DISTINCT cusa_po_number)
          FROM canon_e580_itt_lines_tbl
         WHERE itt_number= p_itt_num1
           AND product_sourced_by = p_sourced_by
           AND itt_line_type IN  ('GOODS', 'EXPENSE')
           AND cusa_po_number IS NOT NULL;
		   
		
      
      TYPE po_hdr_typ IS TABLE OF po_hdr_dtls%ROWTYPE
      INDEX BY BINARY_INTEGER;
	  
	  l_dealer_po_count NUMBER;
	  l_csa_po_count NUMBER;
	  l_po_hdr_tbl     po_hdr_typ;
    l_csa_sql         VARCHAR2(32000):= NULL;
    l_dealer_sql      VARCHAR2(32000):= NULL;
	
	BEGIN
	
	  OPEN  po_hdr_dtls( p_itt_num );
      FETCH po_hdr_dtls BULK COLLECT INTO l_po_hdr_tbl;
      CLOSE po_hdr_dtls;
      
      IF (l_po_hdr_tbl.COUNT = 0)
      THEN
         x_ret_flag    := 'E';
         x_ret_message := 'No eligible ITT lines exists for PO Creation';
         RETURN;
      END IF;
	  
	  
	  FOR rec IN 1..l_po_hdr_tbl.COUNT
      LOOP

         IF (l_po_hdr_tbl(rec).product_sourced_by = 'DEALER')     
         THEN

			OPEN po_count_cur(p_itt_num, 'DEALER');
			FETCH po_count_cur INTO l_dealer_po_count;
			CLOSE po_count_cur;
			
			IF l_dealer_po_count >= 1
			THEN
				
        l_dealer_sql :=
				'SELECT DISTINCT p.po_ord_num po_number, 
					   CAST (TO_TIMESTAMP (p.ezintime, ''RRRRMMDDHH24MISSFF3'') AS DATE) po_date,
					   DECODE(ps.po_sts_nm, ''Validated'', ''Approved'', ps.po_sts_nm) po_sts, 
					   p.prnt_vnd_nm supplier 
				  FROM  po p, po_dtl pd, po_sts ps
				 WHERE  p.po_ord_num = pd.po_ord_num
				   AND  pd.trx_ref_num = '''||p_itt_num||'''
				    AND  ps.po_sts_cd = p.po_sts_cd
				   AND  p.glbl_cmpy_cd = ''ADB''
				   AND  pd.glbl_cmpy_cd = ''ADB''
				   AND  p.ezcancelflag = ''0''
				   AND  pd.ezcancelflag = ''0''
				   AND  p.po_hdr_sts_cd = ''10''
				   AND  p.vnd_cd <> (SELECT v.vnd_cd
									  FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt, vnd v
									  WHERE  ct.cd_id = cvt.cd_id 
									  AND ct.cd_name = ''CANON_E580_SITE_CSA_SOURCED''
									  AND    cvt.val2 = v.loc_nm
									  AND    v.glbl_cmpy_cd = ''ADB''
									  AND   (SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)))
					ORDER BY p.po_ord_num';
				
			END IF;
			
		 ELSIF (l_po_hdr_tbl(rec).product_sourced_by = 'CSA')  
		 THEN

			OPEN po_count_cur(p_itt_num, 'CSA');
			FETCH po_count_cur INTO l_csa_po_count;
			CLOSE po_count_cur;
			
			IF l_csa_po_count >= 1
			THEN
				
        l_csa_sql :=
				'SELECT DISTINCT p.po_ord_num po_number, 
					   CAST (TO_TIMESTAMP (p.ezintime, ''RRRRMMDDHH24MISSFF3'') AS DATE) po_date,
					   DECODE(ps.po_sts_nm, ''Validated'', ''Approved'', ps.po_sts_nm) po_sts, 
					   p.prnt_vnd_nm supplier 
				  FROM  po p, po_dtl pd, po_sts ps
				 WHERE  p.po_ord_num = pd.po_ord_num
				   AND  pd.trx_ref_num = '''||p_itt_num||'''
				    AND  p.po_sts_cd = ps.po_sts_cd
				   AND  p.glbl_cmpy_cd = ''ADB''
				   AND  pd.glbl_cmpy_cd = ''ADB''
				   AND  p.ezcancelflag = ''0''
				   AND  pd.ezcancelflag = ''0''
				   AND  p.po_hdr_sts_cd = ''10''
				   AND  p.vnd_cd = (SELECT v.vnd_cd
									  FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt, vnd v
									  WHERE  ct.cd_id = cvt.cd_id 
									  AND ct.cd_name = ''CANON_E580_SITE_CSA_SOURCED''
									  AND    cvt.val2 = v.loc_nm
									  AND    v.glbl_cmpy_cd = ''ADB''
									  AND   (SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)))
					ORDER BY p.po_ord_num';
				
			END IF;
		
		 END IF;
	END LOOP;
  
    IF l_csa_sql IS NULL THEN
       l_csa_sql := 'SELECT NULL, NULL, NULL, NULL FROM DUAL';
	END IF;
	
    IF l_dealer_sql IS NULL THEN
       l_dealer_sql := 'SELECT NULL, NULL, NULL, NULL FROM DUAL';
    END IF;
  
		IF (NVL(l_dealer_po_count, 0) = 1 AND NVL(l_csa_po_count, 0) = 1)
		THEN
			x_select_po := 'N';
			OPEN x_dealer_po_det FOR 'SELECT NULL, NULL, NULL, NULL FROM DUAL';
			OPEN x_cusa_po_det FOR 'SELECT NULL, NULL, NULL, NULL FROM DUAL';
			
		ELSIF (NVL(l_dealer_po_count, 0) > 1 OR NVL(l_csa_po_count, 0) > 1)
		THEN
			x_select_po := 'Y';
		    OPEN x_dealer_po_det FOR l_dealer_sql;
		    OPEN x_cusa_po_det FOR l_csa_sql;
		ELSE
			x_select_po := 'N';
			OPEN x_dealer_po_det FOR 'SELECT NULL, NULL, NULL, NULL FROM DUAL';
			OPEN x_cusa_po_det FOR 'SELECT NULL, NULL, NULL, NULL FROM DUAL';
		END IF;
 
 	x_ret_flag    := 'S';
  x_ret_message := '';
    

	EXCEPTION
	WHEN OTHERS THEN
		x_ret_flag    := 'E';
    x_ret_message := 'Please retry this action, Proc get_append_po_numbers terminated due to Error: '||SUBSTR(SQLERRM,1,100);
	
	END;
   
   /*****************************************************************************************************************
   This procedure gets invoked from eligible_create_po_lines within this package for PO creation
   Logic includes to derive all eligible lines for PO creation against the ITT Number and return back to ITT workbench
   ******************************************************************************************************************/
   PROCEDURE derive_po_lines (   p_itt_num        IN  VARCHAR2
                               , p_user           IN  VARCHAR2
                               , x_create_po_tbl  OUT CANON_E580_CREATE_PO_TBL_TYP
                               , x_ret_flag       OUT VARCHAR2
                               , x_ret_message    OUT VARCHAR2
                             )
   IS         
      CURSOR po_hdr_dtls(p_itt_num1 VARCHAR2)
      IS
         SELECT DISTINCT
                cil.product_sourced_by
         FROM   canon_e580_itt_lines_tbl cil
         WHERE  cil.itt_number = p_itt_num1
         AND    NVL(cil.cancelled_flag, 'N') = 'N'
         AND    cil.itt_line_type = 'GOODS'
         AND    cil.cusa_po_number IS NULL
         AND    EXISTS(SELECT 1 FROM cpo_dtl WHERE cpo_ord_num = cil.order_number
                       AND cpo_dtl_line_num||'.'||cpo_dtl_line_sub_num = cil.line_number
                       AND glbl_cmpy_cd = g_glbl_cmpy_cd)
         AND NOT EXISTS ( SELECT 1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt             --FTS:Indexes do not exists on code tables
                          WHERE ct.cd_name = 'CANON_E580_PO_EXCLUDED_ITEMS' AND ct.cd_id = cvt.cd_id
                          AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
                          AND cvt.val2 = cil.item)
         UNION
        (SELECT DISTINCT 
                'DEALER'          product_sourced_by
         FROM   canon_e580_itt_lines_tbl cil
         WHERE  cil.itt_number = p_itt_num1
         AND    cil.itt_line_type = 'EXPENSE'
         AND    cil.cusa_po_number IS NULL
         UNION
         SELECT DISTINCT 
                'DEALER'          product_sourced_by
         FROM   canon_e580_itt_headers_tbl cih
         WHERE  cih.itt_number = p_itt_num1
         AND    NVL(cih.cancelled_flag, 'N') = 'N'
		 AND NOT EXISTS
		 (SELECT DISTINCT p.po_ord_num 
                                          FROM   po p, po_dtl pd
                                          WHERE  p.po_ord_num = pd.po_ord_num
                                          AND    pd.trx_ref_num = cih.itt_number
                                          AND    p.glbl_cmpy_cd = 'ADB'
                                          AND   pd.glbl_cmpy_cd = 'ADB'
                                          AND    p.ezcancelflag = '0'
                                          AND   pd.ezcancelflag = '0'
                                          AND    p.vnd_cd <> (SELECT v.vnd_cd
                                                              FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt, vnd v
                                                              WHERE  ct.cd_id = cvt.cd_id AND ct.cd_name = 'CANON_E580_SITE_CSA_SOURCED'
                                                              AND    cvt.val2 = v.loc_nm
                                                              AND    v.glbl_cmpy_cd = 'ADB'
                                                              AND   (SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)))
                                        ))
         ORDER BY 1;
      
      TYPE po_hdr_typ IS TABLE OF po_hdr_dtls%ROWTYPE
      INDEX BY BINARY_INTEGER;
	  
	  CURSOR po_lines_cur (p_itt_num IN VARCHAR2, p_prod_sourcedby IN VARCHAR2)
      IS
      SELECT p.*
      FROM
        ( SELECT   cil.order_number           ORDER_NUMBER
                 , cil.line_number            ITT_LINE_NUM
                 , cil.itt_line_type          ITT_LINE_TYPE
                 , cil.product_sourced_by     PRODUCT_SOURCED_BY
                 , l.mdse_cd                  ITEM_NUM
                 , l.mdse_nm                  ITEM_DESC
				 , l.set_mdse_cd              SET_ITEM
				 , l.ds_cpo_line_sub_num     DS_CPO_LINE_SUB_NUM
                 , cil.ordered_quantity       PO_REQ_QTY
                 , l.rtl_wh_cd               DEST_WH_CD
                 , l.rtl_swh_cd              DEST_SUB_WH_CD
                 , l.invty_loc_cd             DEST_INVTY_LOC_CD  
				 , dmi.coa_mdse_tp_cd         MERCHANDISE_TYPE
                 , prd.prch_req_num           PO_REQ_NUM
                 , prd.prch_req_line_num      PO_REQ_LINE_NUM
                 , prd.prch_req_line_sub_num  PO_REQ_LINE_SUB_NUM
                 , l.cust_uom_cd              UOM_CD
                 , cil.item_purchase_price    UNIT_PRICE
                 , '01'                       PO_LINE_TP_CD         --Goods
                 , l.cpo_ord_tp_cd            CPO_ORD_TP_CD
                 , sp.shpg_pln_num            SHPG_PLN_NUM                 
                 , l.COA_CMPY_CD
                 , l.COA_AFFL_CD
                 , l.COA_BR_CD
                 , l.COA_CH_CD
                 , l.COA_CC_CD
                 , l.COA_ACCT_CD
                 , l.COA_PROD_CD
                 , l.COA_PROJ_CD
                 , l.COA_EXTN_CD
          FROM   canon_e580_itt_lines_tbl cil
               , cpo h
               , cpo_dtl l
               , shpg_pln sp
			   , ord_line_src ols
			   , mdse dmi
			   , prch_req_dtl prd
          WHERE  cil.itt_number = p_itt_num
          AND    cil.product_sourced_by = p_prod_sourcedby
          AND    cil.order_number = h.cpo_ord_num
          AND    cil.order_number = l.cpo_ord_num
          AND    cil.line_number = l.cpo_dtl_line_num||'.'||l.cpo_dtl_line_sub_num
		  AND    l.mdse_cd = dmi.mdse_cd
		  AND    dmi.glbl_cmpy_cd = g_glbl_cmpy_cd
		  AND    dmi.ezcancelflag = '0'
          AND    l.cpo_ord_num = sp.trx_hdr_num
          AND    l.cpo_dtl_line_num = sp.trx_line_num
          AND    l.cpo_dtl_line_sub_num = sp.trx_line_sub_num
		  AND    l.cpo_ord_num = prd.trx_ref_num
          AND    l.cpo_dtl_line_num = prd.trx_ref_line_num
          AND    l.cpo_dtl_line_sub_num = prd.trx_ref_line_sub_num
		  AND    l.ord_line_src_cd = ols.ord_line_src_cd
          AND    cil.cusa_po_number IS NULL
          AND    cil.item = l.mdse_cd
          AND    cil.itt_line_type = 'GOODS'
          AND    NVL(cil.cancelled_flag, 'N') = 'N'
		  --AND   l.set_mdse_cd IS NULL
          AND   DECODE(dmi.coa_mdse_tp_cd, '11', 1, sp.aval_po_qty) > 0    --Sufficient qty for PO creation
          AND   sp.po_req_flg = 'Y'   --PO Required flag
          AND   sp.so_close_flg = 'N'
          AND   sp.rte_sts_cd = 0     --Un-Routed
          AND   sp.shpg_sts_cd = 10   --Validated
          AND   h.ezcancelflag  = 0
          AND   l.ezcancelflag  = 0
          AND   sp.ezcancelflag = 0
		  AND 	prd.ezcancelflag = 0
		  AND   prd.glbl_cmpy_cd = g_glbl_cmpy_cd
          AND   h.glbl_cmpy_cd  = g_glbl_cmpy_cd
          AND   l.glbl_cmpy_cd  = g_glbl_cmpy_cd
          AND   sp.glbl_cmpy_cd = g_glbl_cmpy_cd
		  AND  NOT EXISTS (SELECT 1 
							FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
							WHERE ct.cd_id = cvt.cd_id
							AND ct.cd_name = 'CANON_E580_PO_EXCL_LINE_TYPE'
							AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) 
							AND NVL(cvt.end_date_active, SYSDATE+1))
							AND cvt.val2 = ols.ord_line_src_nm)
          AND  NOT EXISTS(SELECT 1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                          WHERE ct.cd_name = 'CANON_E580_PO_EXCLUDED_ITEMS' AND ct.cd_id = cvt.cd_id
                          AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
                          AND cvt.val2 = l.mdse_cd)
        UNION ALL
        SELECT     NULL                       ORDER_NUMBER
                 , NULL                       ITT_LINE_NUM
                 , cil.itt_line_type          ITT_LINE_TYPE
                 , 'DEALER'                   PRODUCT_SOURCED_BY
                 , cil.item                   ITEM_NUM   ---cil.item
                 , cil.item_description       ITEM_DESC
				 , NULL                       SET_ITEM
				 , NULL                       DS_CPO_LINE_SUB_NUM
                 , cil.ordered_quantity       PO_REQ_QTY
				 , 'DS'   					  DEST_WH_CD
                 /*,(SELECT dcd.rtl_wh_cd FROM ds_cpo_dtl dcd, canon_e580_itt_lines_tbl l
                   WHERE l.itt_number = p_itt_num AND cil.order_number = l.order_number
                   AND cil.line_number = l.line_number AND dcd.cpo_ord_num = l.order_number
                   AND dcd.cpo_dtl_line_num||'.'||dcd.cpo_dtl_line_sub_num = l.line_number
                  )                           DEST_WH_CD*/
                 , NULL                       DEST_SUB_WH_CD
                 , NULL                       DEST_INVTY_LOC_CD  
                 , NULL                       MERCHANDISE_TYPE				 
                 , NULL                       PO_REQ_NUM
                 , NULL                       PO_REQ_LINE_NUM
                 , NULL                       PO_REQ_LINE_SUB_NUM
                 , 'EA'                       UOM_CD
                 , cil.item_purchase_price    UNIT_PRICE
                 , '02'                       PO_LINE_TP_CD         --Expense
                 , '70'                       CPO_ORD_TP_CD
                 , NULL                       SHPG_PLN_NUM
                 , NULL                       COA_CMPY_CD
                 , NULL                       COA_AFFL_CD
                 , NULL                       COA_BR_CD
                 , NULL                       COA_CH_CD
                 , NULL                       COA_CC_CD
                 , NULL                       COA_ACCT_CD
                 , NULL                       COA_PROD_CD
                 , NULL                       COA_PROJ_CD
                 , NULL                       COA_EXTN_CD
         FROM   canon_e580_itt_lines_tbl cil
         WHERE  cil.itt_line_type = 'EXPENSE'
         AND    cil.itt_number = p_itt_num
         AND    cil.cusa_po_number IS NULL
         UNION ALL
         SELECT    DISTINCT
                   NULL                       ORDER_NUMBER
                 , NULL                       ITT_LINE_NUM
                 , 'EXPENSE'                  ITT_LINE_TYPE
                 , 'DEALER'                   PRODUCT_SOURCED_BY
                 ,canon_e580_itt_util_pkg.get_code_tbl_value('CANON_E580_EXP_ITEM','FINDER_FEE')
                                              ITEM_NUM
                 , 'Finder Fee'               ITEM_DESC
				 , NULL                       SET_ITEM
				 , NULL                       DS_CPO_LINE_SUB_NUM
                 , 1                          PO_REQ_QTY
                 ,(SELECT dcd.rtl_wh_cd FROM cpo_dtl dcd, canon_e580_itt_lines_tbl l
                   WHERE l.itt_number = p_itt_num AND cil.order_number = l.order_number
                   AND cil.line_number = l.line_number AND dcd.cpo_ord_num = l.order_number
                   AND dcd.cpo_dtl_line_num||'.'||dcd.cpo_dtl_line_sub_num = l.line_number
                  )                           DEST_WH_CD
                 , NULL                       DEST_SUB_WH_CD
                 , NULL                       DEST_INVTY_LOC_CD    
                 , NULL                       MERCHANDISE_TYPE				 
                 , NULL                       PO_REQ_NUM
                 , NULL                       PO_REQ_LINE_NUM
                 , NULL                       PO_REQ_LINE_SUB_NUM
                 , 'EA'                       UOM_CD
                 ,(SELECT SUM(NVL(l.finder_fee, 0)) FROM canon_e580_itt_lines_tbl l
                   WHERE l.itt_line_type = 'GOODS' AND l.itt_number = p_itt_num AND l.cusa_po_number IS NULL) 
                                              UNIT_PRICE
                 , '02'                       PO_LINE_TP_CD         --Expense
                 , '70'                       CPO_ORD_TP_CD
                 , NULL                       SHPG_PLN_NUM
                 , NULL                       COA_CMPY_CD
                 , NULL                       COA_AFFL_CD
                 , NULL                       COA_BR_CD
                 , NULL                       COA_CH_CD
                 , NULL                       COA_CC_CD
                 , NULL                       COA_ACCT_CD
                 , NULL                       COA_PROD_CD
                 , NULL                       COA_PROJ_CD
                 , NULL                       COA_EXTN_CD
         FROM   canon_e580_itt_lines_tbl cil
         WHERE  cil.itt_line_type = 'GOODS'
         AND    cil.itt_number = p_itt_num
         AND    cil.cusa_po_number IS NULL
         UNION ALL
         SELECT    DISTINCT
                   NULL                       ORDER_NUMBER
                 , NULL                       ITT_LINE_NUM
                 , 'EXPENSE'                  ITT_LINE_TYPE
                 , 'DEALER'                   PRODUCT_SOURCED_BY
                 , canon_e580_itt_util_pkg.get_code_tbl_value('CANON_E580_EXP_ITEM','INSTALL_CREDIT')
                                              ITEM_NUM
                 , 'Install Credit'           ITEM_DESC
				 , NULL                       SET_ITEM
				 , NULL                       DS_CPO_LINE_SUB_NUM
                 , 1                          PO_REQ_QTY
                 ,(SELECT dcd.rtl_wh_cd FROM cpo_dtl dcd, canon_e580_itt_lines_tbl l
                   WHERE l.itt_number = p_itt_num AND cil.order_number = l.order_number
                   AND cil.line_number = l.line_number AND dcd.cpo_ord_num = l.order_number
                   AND dcd.cpo_dtl_line_num||'.'||dcd.cpo_dtl_line_sub_num = l.line_number
                  )                           DEST_WH_CD
                 , NULL                       DEST_SUB_WH_CD
                 , NULL                       DEST_INVTY_LOC_CD    
                 , NULL                       MERCHANDISE_TYPE				 
                 , NULL                       PO_REQ_NUM
                 , NULL                       PO_REQ_LINE_NUM
                 , NULL                       PO_REQ_LINE_SUB_NUM
                 , 'EA'                       UOM_CD
                 , (SELECT SUM(NVL(l.install_credit, 0)) FROM canon_e580_itt_lines_tbl l
                    WHERE l.itt_line_type = 'GOODS' AND l.itt_number = p_itt_num AND l.cusa_po_number IS NULL)
                                              UNIT_PRICE
                 , '02'                       PO_LINE_TP_CD         --Expense
                 , '70'                       CPO_ORD_TP_CD
                 , NULL                       SHPG_PLN_NUM
                 , NULL                       COA_CMPY_CD
                 , NULL                       COA_AFFL_CD
                 , NULL                       COA_BR_CD
                 , NULL                       COA_CH_CD
                 , NULL                       COA_CC_CD
                 , NULL                       COA_ACCT_CD
                 , NULL                       COA_PROD_CD
                 , NULL                       COA_PROJ_CD
                 , NULL                       COA_EXTN_CD
         FROM   canon_e580_itt_lines_tbl cil
         WHERE  cil.itt_line_type = 'GOODS'
         AND    cil.itt_number = p_itt_num
         AND    cil.cusa_po_number IS NULL
	  UNION ALL
		SELECT    DISTINCT
                   NULL                       ORDER_NUMBER
                 , NULL                       ITT_LINE_NUM
                 , 'EXPENSE'                  ITT_LINE_TYPE
                 , 'DEALER'                   PRODUCT_SOURCED_BY
                 , canon_e580_itt_util_pkg.get_code_tbl_value('CANON_E580_EXP_ITEM','INSTALL_CREDIT')
                                              ITEM_NUM
                 , 'Install Credit'           ITEM_DESC
				 , NULL                       SET_ITEM
				 , NULL                       DS_CPO_LINE_SUB_NUM
                 , 1                          PO_REQ_QTY
                 ,(SELECT dcd.rtl_wh_cd FROM cpo_dtl dcd, canon_e580_itt_lines_tbl l
                   WHERE l.itt_number = p_itt_num AND cil.order_number = l.order_number
                   AND cil.line_number = l.line_number AND dcd.cpo_ord_num = l.order_number
                   AND dcd.cpo_dtl_line_num||'.'||dcd.cpo_dtl_line_sub_num = l.line_number
                  )                           DEST_WH_CD
                 , NULL                       DEST_SUB_WH_CD
                 , NULL                       DEST_INVTY_LOC_CD    
                 , NULL                       MERCHANDISE_TYPE				 
                 , NULL                       PO_REQ_NUM
                 , NULL                       PO_REQ_LINE_NUM
                 , NULL                       PO_REQ_LINE_SUB_NUM
                 , 'EA'                       UOM_CD
                 , (SELECT SUM(NVL(l.install_credit, 0)) FROM canon_e580_itt_lines_tbl l
                    WHERE l.itt_line_type = 'GOODS' AND l.itt_number = p_itt_num)
                                              UNIT_PRICE
                 , '02'                       PO_LINE_TP_CD         --Expense
                 , '70'                       CPO_ORD_TP_CD
                 , NULL                       SHPG_PLN_NUM
                 , NULL                       COA_CMPY_CD
                 , NULL                       COA_AFFL_CD
                 , NULL                       COA_BR_CD
                 , NULL                       COA_CH_CD
                 , NULL                       COA_CC_CD
                 , NULL                       COA_ACCT_CD
                 , NULL                       COA_PROD_CD
                 , NULL                       COA_PROJ_CD
                 , NULL                       COA_EXTN_CD
         FROM   canon_e580_itt_lines_tbl cil
         WHERE  cil.itt_line_type = 'GOODS'
         AND    cil.itt_number = p_itt_num
         AND    cil.cusa_po_number IS NOT NULL
		 AND NOT EXISTS         
         (SELECT DISTINCT p.po_ord_num 
			  FROM   po p, po_dtl pd
			  WHERE  p.po_ord_num = pd.po_ord_num
			  AND    pd.trx_ref_num = cil.itt_number
			  AND    pd.mdse_desc_short_txt = 'Install Credit'
			  AND    p.glbl_cmpy_cd = 'ADB'
			  AND   pd.glbl_cmpy_cd = 'ADB'
			  AND    p.ezcancelflag = '0'
			  AND   pd.ezcancelflag = '0'
			  AND    p.vnd_cd <> (SELECT v.vnd_cd
								  FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt, vnd v
								  WHERE  ct.cd_id = cvt.cd_id AND ct.cd_name = 'CANON_E580_SITE_CSA_SOURCED' 
								  AND    cvt.val2 = v.loc_nm
								  AND    v.glbl_cmpy_cd = 'ADB'
								  AND   (SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)))
                                        )
	 UNION ALL
		SELECT    DISTINCT
                   NULL                       ORDER_NUMBER
                 , NULL                       ITT_LINE_NUM
                 , 'EXPENSE'                  ITT_LINE_TYPE
                 , 'DEALER'                   PRODUCT_SOURCED_BY
                 ,canon_e580_itt_util_pkg.get_code_tbl_value('CANON_E580_EXP_ITEM','FINDER_FEE')
                                              ITEM_NUM
                 , 'Finder Fee'               ITEM_DESC
				 , NULL                       SET_ITEM
				 , NULL                       DS_CPO_LINE_SUB_NUM
                 , 1                          PO_REQ_QTY
                 ,(SELECT dcd.rtl_wh_cd FROM cpo_dtl dcd, canon_e580_itt_lines_tbl l
                   WHERE l.itt_number = p_itt_num AND cil.order_number = l.order_number
                   AND cil.line_number = l.line_number AND dcd.cpo_ord_num = l.order_number
                   AND dcd.cpo_dtl_line_num||'.'||dcd.cpo_dtl_line_sub_num = l.line_number
                  )                           DEST_WH_CD
                 , NULL                       DEST_SUB_WH_CD
                 , NULL                       DEST_INVTY_LOC_CD    
                 , NULL                       MERCHANDISE_TYPE				 
                 , NULL                       PO_REQ_NUM
                 , NULL                       PO_REQ_LINE_NUM
                 , NULL                       PO_REQ_LINE_SUB_NUM
                 , 'EA'                       UOM_CD
                 ,(SELECT SUM(NVL(l.finder_fee, 0)) FROM canon_e580_itt_lines_tbl l
                   WHERE l.itt_line_type = 'GOODS' AND l.itt_number = p_itt_num) 
                                              UNIT_PRICE
                 , '02'                       PO_LINE_TP_CD         --Expense
                 , '70'                       CPO_ORD_TP_CD
                 , NULL                       SHPG_PLN_NUM
                 , NULL                       COA_CMPY_CD
                 , NULL                       COA_AFFL_CD
                 , NULL                       COA_BR_CD
                 , NULL                       COA_CH_CD
                 , NULL                       COA_CC_CD
                 , NULL                       COA_ACCT_CD
                 , NULL                       COA_PROD_CD
                 , NULL                       COA_PROJ_CD
                 , NULL                       COA_EXTN_CD
         FROM   canon_e580_itt_lines_tbl cil
         WHERE  cil.itt_line_type = 'GOODS'
         AND    cil.itt_number = p_itt_num
         AND    cil.cusa_po_number IS NOT NULL
		 AND NOT EXISTS         
         (SELECT DISTINCT p.po_ord_num 
			  FROM   po p, po_dtl pd
			  WHERE  p.po_ord_num = pd.po_ord_num
			  AND    pd.trx_ref_num = cil.itt_number
			  AND    pd.mdse_desc_short_txt = 'Finder Fee'
			  AND    p.glbl_cmpy_cd = 'ADB'
			  AND   pd.glbl_cmpy_cd = 'ADB'
			  AND    p.ezcancelflag = '0'
			  AND   pd.ezcancelflag = '0'
			  AND    p.vnd_cd <> (SELECT v.vnd_cd
								  FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt, vnd v
								  WHERE  ct.cd_id = cvt.cd_id AND ct.cd_name = 'CANON_E580_SITE_CSA_SOURCED' 
								  AND    cvt.val2 = v.loc_nm
								  AND    v.glbl_cmpy_cd = 'ADB'
								  AND   (SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1)))
                                        )
       ) p
      WHERE  p.product_sourced_by = p_prod_sourcedby
      AND    ( (p.unit_price > 0 and p.ITEM_DESC IN ('Finder Fee','Install Credit')) OR
                p.ITEM_DESC NOT IN ('Finder Fee','Install Credit'))
      ORDER BY 1,2,5;
      
      TYPE po_line_typ IS TABLE OF po_lines_cur%ROWTYPE
      INDEX BY BINARY_INTEGER;
      
      l_itt_number     VARCHAR2(80) := p_itt_num;
      l_po_hdr_tbl     po_hdr_typ;
      l_po_line_tbl    po_line_typ;       
      l_itt_hdr_rec    canon_e580_itt_headers_tbl%ROWTYPE;
      l_po_desc        VARCHAR2(1000);
      h_cnt            INTEGER;
      l_cnt            INTEGER;
      l_vnd_code       VARCHAR2(20);
      l_prnt_vnd_code  VARCHAR2(20);
      l_ccy_cd         VARCHAR2(20);
      l_create_po_tbl  CANON_E580_CREATE_PO_TBL_TYP := CANON_E580_CREATE_PO_TBL_TYP();
      l_poh_msg_tbl    CANON_E580_POH_MSG_TBL_TYP := CANON_E580_POH_MSG_TBL_TYP();
      l_ser_info_tbl   CANON_E580_POS_TBL_TYP := CANON_E580_POS_TBL_TYP();
      l_act_info_tbl   CANON_E580_POA_TBL_TYP := CANON_E580_POA_TBL_TYP();
      l_pol_info_tbl   CANON_E580_POL_TBL_TYP := CANON_E580_POL_TBL_TYP();
      l_act_info_rec   CANON_E580_POA_TYP_REC;
      l_dest_invty_cd  VARCHAR2(50);
      l_dest_wh_cd     VARCHAR2(30);
      l_dest_swh_cd    VARCHAR2(30);
      l_line_num       VARCHAR2(10);
      l_po_qlfy_cd     VARCHAR2(5);
      l_date           VARCHAR2(10) := TO_CHAR(SYSDATE,'YYYYMMDD');
      l_datetime       VARCHAR2(10) := TO_CHAR(SYSDATE,'YYYYMMDD');
      l_step_no        NUMBER := 0;
      l_uom_cd         VARCHAR2(30);
      l_vnd_loc_nm     VARCHAR2(50);
	  l_item_cmpsn_type  	 VARCHAR2(50);
	  l_set_line_num     	 VARCHAR2(50);
	  l_parent_line_num  	 VARCHAR2(50);
	  l_disp_po_dtl_line_num VARCHAR2(50);
	  l_app_line_no NUMBER := 0;
	  
   BEGIN
      l_step_no := 1;
      SELECT * INTO l_itt_hdr_rec
      FROM   canon_e580_itt_headers_tbl cih
      WHERE  cih.itt_number = l_itt_number;

      OPEN  po_hdr_dtls( p_itt_num );
      FETCH po_hdr_dtls BULK COLLECT INTO l_po_hdr_tbl;
      CLOSE po_hdr_dtls;
      IF (l_po_hdr_tbl.COUNT = 0)
      THEN
         x_ret_flag    := 'E';
         x_ret_message := 'No eligible ITT lines exists for PO Creation';
         RETURN;
      END IF;
      
      l_step_no := 2;
      FOR rec IN 1..l_po_hdr_tbl.COUNT
      LOOP
         l_vnd_code := l_itt_hdr_rec.vnd_cd;   --Vendor Code
         IF (l_po_hdr_tbl(rec).product_sourced_by = 'DEALER')     ---DEALER: Possible of PO with Goods or Goods/Expense or only Expense.
         THEN
		    
			OPEN  po_lines_cur( l_itt_number, 'DEALER');
            FETCH po_lines_cur BULK COLLECT INTO l_po_line_tbl;
            CLOSE po_lines_cur;
			
            l_po_qlfy_cd    := 'DS';     --Dealer PO with Expense items
            l_prnt_vnd_code := l_itt_hdr_rec.prnt_vnd_cd;
            ---Derive Dealer Currency Code
            BEGIN
               SELECT  v.deal_ccy_cd, p.prnt_vnd_cd, v.vnd_cd
               INTO    l_ccy_cd, l_prnt_vnd_code, l_vnd_code
               FROM    vnd v, prnt_vnd p
               WHERE   v.vnd_cd = l_vnd_code
               AND     p.prnt_vnd_cd = l_prnt_vnd_code
               AND     v.prnt_vnd_pk = p.prnt_vnd_pk
               AND     v.glbl_cmpy_cd = g_glbl_cmpy_cd;
            EXCEPTION
               WHEN OTHERS THEN
                  l_ccy_cd        := 'USD';
                  l_prnt_vnd_code := NULL;
                  l_vnd_code      := NULL;
            END;            
         ELSIF (l_po_hdr_tbl(rec).product_sourced_by = 'CSA')       ---CSA: Always with Goods items only
         THEN
		 
			OPEN  po_lines_cur( l_itt_number, 'CSA');
            FETCH po_lines_cur BULK COLLECT INTO l_po_line_tbl;
            CLOSE po_lines_cur;
            l_po_qlfy_cd := 'DS';    --CUSA PO with Goods items
            
            --Derive CUSA Parent vendor code / vendor site code
            BEGIN
               SELECT  canon_e580_itt_util_pkg.get_code_tbl_value('CANON_E580_SUPP_CSA_SOURCED',NULL)
                      ,canon_e580_itt_util_pkg.get_code_tbl_value('CANON_E580_SITE_CSA_SOURCED',NULL)
               INTO    l_prnt_vnd_code
                      ,l_vnd_loc_nm
               FROM DUAL;
            EXCEPTION
               WHEN OTHERS THEN
                  l_prnt_vnd_code := NULL;
                  l_vnd_loc_nm    := NULL;
            END;
            
            ---Derive Dealer Currency Code
            BEGIN
               SELECT  v.deal_ccy_cd, p.prnt_vnd_cd, v.vnd_cd
               INTO    l_ccy_cd, l_prnt_vnd_code, l_vnd_code
               FROM    vnd v, prnt_vnd p
               WHERE   v.loc_nm = l_vnd_loc_nm
               AND     p.prnt_vnd_cd = l_prnt_vnd_code
               AND     v.prnt_vnd_pk = p.prnt_vnd_pk
               AND     v.glbl_cmpy_cd = g_glbl_cmpy_cd;
            EXCEPTION
               WHEN OTHERS THEN
                  l_ccy_cd        := 'USD';
                  l_prnt_vnd_code := NULL;
                  l_vnd_code      := NULL;
            END;
            
         END IF;
         l_step_no := 3;

         l_po_desc := 'ITT# '||l_itt_hdr_rec.itt_number||', SO# '||l_itt_hdr_rec.order_number
                             ||', DLR CODE : '|| l_vnd_code
                             ||', CUSTOMER - '|| l_itt_hdr_rec.ship_to_customer;
							 
         l_step_no := 4;
         IF (l_po_line_tbl.COUNT > 0) 
         THEN
            --Derive PO header details            
            l_dest_invty_cd := l_po_line_tbl(1).DEST_INVTY_LOC_CD;
            l_dest_wh_cd    := l_po_line_tbl(1).DEST_WH_CD;
            l_dest_swh_cd   := l_po_line_tbl(1).DEST_SUB_WH_CD;            

            l_create_po_tbl.EXTEND;
            h_cnt := l_create_po_tbl.COUNT;
            l_create_po_tbl(h_cnt) := CANON_E580_CREATE_PO_TYP_REC( 
                                                                NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                                NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                                NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                                NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                                NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL
                                                              );
															  
            l_create_po_tbl(h_cnt).DML_MODE          := '1';                                    --Mode    1-Create, 2-Update, 5-PO Merge.
            l_create_po_tbl(h_cnt).EVENT             := '2';                                    --Event   1-Save, 2-Submit
            l_create_po_tbl(h_cnt).PROC_DT           := TO_CHAR(SYSDATE,'YYYYMMDD');            --Process Date
            l_create_po_tbl(h_cnt).XX_RQST_TS        := TO_CHAR(SYSDATE,'YYYYMMDDHHMMSSSSS');   --Request timestamp
            l_create_po_tbl(h_cnt).DS_PO_TP_CD       := 'IO';                                   --IO/ITT Outbound PO
            l_create_po_tbl(h_cnt).PO_QLFY_CD        := l_po_qlfy_cd;                           --DS-Dropship or NULL
            l_create_po_tbl(h_cnt).PO_SUBMT_PSN_CD   := p_user;                                 --Buyer
            l_create_po_tbl(h_cnt).PO_SUBMT_TS       := TO_CHAR(SYSDATE,'YYYYMMDDHHMMSSSSS');
            l_create_po_tbl(h_cnt).PO_APVL_STS_CD    := '21';
            l_create_po_tbl(h_cnt).PO_APVL_PSN_CD    := p_user;
            l_create_po_tbl(h_cnt).PO_APVL_DT        := TO_CHAR(SYSDATE,'YYYYMMDD');
            l_create_po_tbl(h_cnt).PO_APVL_TS        := TO_CHAR(SYSDATE,'YYYYMMDDHHMMSSSSS');
            l_create_po_tbl(h_cnt).WF_FLG            := 'N';
            
            l_create_po_tbl(h_cnt).DEST_RTL_WH_CD    := l_dest_wh_cd;                           --Destination Retail Warehouse Code
            l_create_po_tbl(h_cnt).PO_ORD_SRC_CD     := '41';                                   --41:ITT Outbound
            l_create_po_tbl(h_cnt).VND_CD            := l_vnd_code;                             --Vendor:Dealer/CUSA
            l_create_po_tbl(h_cnt).PRNT_VND_CD       := l_prnt_vnd_code;
            l_create_po_tbl(h_cnt).DEAL_CCY_CD       := l_ccy_cd;
            l_create_po_tbl(h_cnt).VND_DROP_SHIP_FLG := 'Y';
            l_create_po_tbl(h_cnt).PRCH_GRP_CD       := 'ESS';                                  --TBC: 01/ESS/Enterprise Services and Solutions
            l_create_po_tbl(h_cnt).SHIP_TO_CUST_CD   := l_itt_hdr_rec.SHIP_TO_CUSTOMER_CD;     
			l_create_po_tbl(h_cnt).CTAC_PSN_NM   	 := l_itt_hdr_rec.CUSTOMER_CONTACT_NAME;
               --END IF;
              
            l_poh_msg_tbl.DELETE;
            l_poh_msg_tbl.EXTEND;
            l_cnt := l_poh_msg_tbl.COUNT;
            l_poh_msg_tbl(l_cnt) := CANON_E580_POH_MSG_TYP_REC(NULL,NULL,NULL,NULL,NULL,NULL,NULL);
            l_poh_msg_tbl(l_cnt).PO_MSG_TP_CD             := '05';                             --TBC: 02-Internal message, 03-special instructions, 04-receiver note, 05-shipper note
            l_poh_msg_tbl(l_cnt).XX_DS_MULT_MSG_DPLY_TXT  := l_po_desc;
            l_create_po_tbl(h_cnt).PO_HDR_MSG_INFO_TBL    := l_poh_msg_tbl;
			
            l_pol_info_tbl.DELETE;
            l_act_info_tbl.DELETE;
            FOR rec IN 1..l_po_line_tbl.COUNT
            LOOP            
				l_app_line_no := l_app_line_no + 1;
               --Derive PO_QLFY_CD at header level based on ITT_LINE_TYPE
               IF (l_create_po_tbl(h_cnt).PO_QLFY_CD IS NULL AND l_po_line_tbl(rec).ITT_LINE_TYPE = 'GOODS')
               THEN
                  l_create_po_tbl(h_cnt).PO_QLFY_CD := 'DS';
               END IF;
               
               --Derive UOM
               IF (l_po_line_tbl(rec).UOM_CD IS NULL) 
               THEN
                  BEGIN
                     SELECT pkg_uom_cd
                     INTO   l_uom_cd
                     FROM   mdse_store_pkg
                     WHERE  mdse_cd = l_po_line_tbl(rec).ITEM_NUM
                     AND    glbl_cmpy_cd = g_glbl_cmpy_cd;
                  EXCEPTION
                     WHEN OTHERS THEN
                        l_uom_cd := 'EA';
                  END;
               END IF;
			   
			                 
               l_pol_info_tbl.EXTEND;
               l_cnt := l_pol_info_tbl.COUNT;
               l_pol_info_tbl(l_cnt) := CANON_E580_POL_TYP_REC(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                               NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                               NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
                                                               NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
               l_line_num := LPAD (l_cnt, 3, '0');
			   IF l_po_line_tbl(rec).MERCHANDISE_TYPE = '11'
               THEN
                     l_item_cmpsn_type := '1';
					 l_set_line_num := l_line_num;
					 l_disp_po_dtl_line_num := TO_CHAR(l_cnt)||'.0';
			   ELSIF l_po_line_tbl(rec).SET_ITEM IS NOT NULL
			   THEN
                     l_item_cmpsn_type := '2';
					 l_parent_line_num := l_set_line_num;
					 l_disp_po_dtl_line_num := TO_NUMBER(l_set_line_num) ||'.'|| l_po_line_tbl(rec).DS_CPO_LINE_SUB_NUM;
			   ELSE
					 l_disp_po_dtl_line_num := TO_CHAR(l_cnt)||'.0';
					 l_item_cmpsn_type := NULL;
					 l_parent_line_num := NULL;
               END IF;
			   
					   
               l_pol_info_tbl(l_cnt).PO_ORD_DTL_LINE_NUM       := l_line_num;
               l_pol_info_tbl(l_cnt).DISP_PO_DTL_LINE_NUM      := l_disp_po_dtl_line_num;
               l_pol_info_tbl(l_cnt).PO_LINE_TP_CD             := l_po_line_tbl(rec).PO_LINE_TP_CD;
			   l_pol_info_tbl(l_cnt).PO_MDSE_CMPSN_TP_CD       := l_item_cmpsn_type;
			   l_pol_info_tbl(l_cnt).SET_PO_ORD_DTL_LINE_NUM   := l_parent_line_num;
               l_pol_info_tbl(l_cnt).MDSE_CD                   := l_po_line_tbl(rec).ITEM_NUM;
               l_pol_info_tbl(l_cnt).MDSE_DESC_SHORT_TXT       := l_po_line_tbl(rec).ITEM_DESC;
               l_pol_info_tbl(l_cnt).PO_QTY                    := l_po_line_tbl(rec).PO_REQ_QTY;
               l_pol_info_tbl(l_cnt).PO_DISP_QTY               := l_po_line_tbl(rec).PO_REQ_QTY;
               l_pol_info_tbl(l_cnt).ORD_QTY                   := l_po_line_tbl(rec).PO_REQ_QTY;
               l_pol_info_tbl(l_cnt).PO_DISP_UOM_CD            := NVL(l_po_line_tbl(rec).UOM_CD, l_uom_cd);
               l_pol_info_tbl(l_cnt).CUST_UOM_CD               := NVL(l_po_line_tbl(rec).UOM_CD, l_uom_cd);
               l_pol_info_tbl(l_cnt).ENT_DEAL_NET_UNIT_PRC_AMT := l_po_line_tbl(rec).UNIT_PRICE;
               l_pol_info_tbl(l_cnt).DEST_RTL_SWH_CD           := l_dest_swh_cd;
               l_pol_info_tbl(l_cnt).INVTY_LOC_CD              := l_dest_wh_cd||l_dest_swh_cd;
               l_pol_info_tbl(l_cnt).RQST_RCV_DT               := TO_CHAR(SYSDATE,'YYYYMMDD');
               l_pol_info_tbl(l_cnt).PRCH_REQ_NUM              := l_po_line_tbl(rec).PO_REQ_NUM;
               l_pol_info_tbl(l_cnt).PRCH_REQ_LINE_NUM         := l_po_line_tbl(rec).PO_REQ_LINE_NUM;
               l_pol_info_tbl(l_cnt).PRCH_REQ_LINE_SUB_NUM     := l_po_line_tbl(rec).PO_REQ_LINE_SUB_NUM;
               l_pol_info_tbl(l_cnt).CPO_ORD_NUM               := l_po_line_tbl(rec).ORDER_NUMBER;
               l_pol_info_tbl(l_cnt).CPO_DTL_LINE_NUM          := SUBSTR(l_po_line_tbl(rec).itt_line_num,1,INSTR(l_po_line_tbl(rec).itt_line_num,'.')-1);
               l_pol_info_tbl(l_cnt).CPO_DTL_LINE_SUB_NUM      := SUBSTR(l_po_line_tbl(rec).itt_line_num,INSTR(l_po_line_tbl(rec).itt_line_num,'.')+1);
               l_pol_info_tbl(l_cnt).CPO_ORD_TP_CD             := l_po_line_tbl(rec).CPO_ORD_TP_CD;
               l_pol_info_tbl(l_cnt).SHPG_PLN_NUM              := l_po_line_tbl(rec).SHPG_PLN_NUM;
               l_pol_info_tbl(l_cnt).TRX_REF_NUM               := p_itt_num;
               l_pol_info_tbl(l_cnt).TRX_REF_LINE_NUM          := SUBSTR(l_po_line_tbl(rec).itt_line_num,1,INSTR(l_po_line_tbl(rec).itt_line_num,'.')-1);
               l_pol_info_tbl(l_cnt).TRX_REF_LINE_SUB_NUM      := SUBSTR(l_po_line_tbl(rec).itt_line_num,INSTR(l_po_line_tbl(rec).itt_line_num,'.')+1);
               
               IF (      l_po_line_tbl(rec).COA_CMPY_CD IS NULL AND l_po_line_tbl(rec).COA_AFFL_CD IS NULL 
                    AND  l_po_line_tbl(rec).COA_BR_CD IS NULL AND l_po_line_tbl(rec).COA_CH_CD IS NULL 
                    AND  l_po_line_tbl(rec).COA_CC_CD IS NULL AND l_po_line_tbl(rec).COA_ACCT_CD IS NULL 
                    AND  l_po_line_tbl(rec).COA_PROD_CD IS NULL AND l_po_line_tbl(rec).COA_PROJ_CD IS NULL 
                    AND  l_po_line_tbl(rec).COA_EXTN_CD IS NULL)
               THEN
                  generate_account_info(   p_salesrep_cd => l_itt_hdr_rec.salesrep_cd
		                         , p_line_num    => l_line_num
		                         , p_acct_rec    => l_act_info_rec
                                       );
                  l_act_info_tbl.EXTEND;
                  l_cnt := l_act_info_tbl.COUNT;
                  l_act_info_tbl(l_cnt) := CANON_E580_POA_TYP_REC(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
                  l_act_info_tbl(l_cnt).PO_ORD_DTL_LINE_NUM := l_line_num;
                  l_act_info_tbl(l_cnt).PO_ACCT_TP_CD := '02';
                  l_act_info_tbl(l_cnt).COA_CMPY_CD   := NVL(l_act_info_rec.COA_CMPY_CD,'ADB');
                  l_act_info_tbl(l_cnt).COA_AFFL_CD   := NVL(l_act_info_rec.COA_AFFL_CD,'000');
                  l_act_info_tbl(l_cnt).COA_BR_CD     := NVL(l_act_info_rec.COA_BR_CD,'000');
                  l_act_info_tbl(l_cnt).COA_CH_CD     := NVL(l_act_info_rec.COA_CH_CD,'000');
                  l_act_info_tbl(l_cnt).COA_CC_CD     := NVL(l_act_info_rec.COA_CC_CD,'000000');
                  l_act_info_tbl(l_cnt).COA_ACCT_CD   := NVL(l_act_info_rec.COA_ACCT_CD,'00000001');
                  l_act_info_tbl(l_cnt).COA_PROD_CD   := NVL(l_act_info_rec.COA_PROD_CD,'ZZ');
                  l_act_info_tbl(l_cnt).COA_PROJ_CD   := NVL(l_act_info_rec.COA_PROJ_CD,'00');
                  l_act_info_tbl(l_cnt).COA_EXTN_CD   := NVL(l_act_info_rec.COA_EXTN_CD,'000');
               ELSE
                  l_act_info_tbl.EXTEND;
                  l_cnt := l_act_info_tbl.COUNT;
                  l_act_info_tbl(l_cnt) := CANON_E580_POA_TYP_REC(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
                  l_act_info_tbl(l_cnt).PO_ORD_DTL_LINE_NUM := l_line_num;
                  l_act_info_tbl(l_cnt).PO_ACCT_TP_CD := '02';
                  l_act_info_tbl(l_cnt).COA_CMPY_CD   := NVL(l_po_line_tbl(rec).COA_CMPY_CD,'ADB');
                  l_act_info_tbl(l_cnt).COA_AFFL_CD   := NVL(l_po_line_tbl(rec).COA_AFFL_CD,'000');
                  l_act_info_tbl(l_cnt).COA_BR_CD     := NVL(l_po_line_tbl(rec).COA_BR_CD,'000');
                  l_act_info_tbl(l_cnt).COA_CH_CD     := NVL(l_po_line_tbl(rec).COA_CH_CD,'000');
                  l_act_info_tbl(l_cnt).COA_CC_CD     := NVL(l_po_line_tbl(rec).COA_CC_CD,'000000');
                  l_act_info_tbl(l_cnt).COA_ACCT_CD   := NVL(l_po_line_tbl(rec).COA_ACCT_CD,'00000001');
                  l_act_info_tbl(l_cnt).COA_PROD_CD   := NVL(l_po_line_tbl(rec).COA_PROD_CD,'ZZ');
                  l_act_info_tbl(l_cnt).COA_PROJ_CD   := NVL(l_po_line_tbl(rec).COA_PROJ_CD,'00');
                  l_act_info_tbl(l_cnt).COA_EXTN_CD   := NVL(l_po_line_tbl(rec).COA_EXTN_CD,'000');
               END IF;
            END LOOP;
            l_create_po_tbl(h_cnt).PO_LINE_INFO_TBL := l_pol_info_tbl;
            l_create_po_tbl(h_cnt).PO_ACCT_INFO_TBL := l_act_info_tbl;
           END IF;
      END LOOP;
      l_step_no := 5;

      x_create_po_tbl  := l_create_po_tbl;
      x_ret_flag       := 'S';
      x_ret_message    := '';
   EXCEPTION
      WHEN OTHERS THEN
         x_ret_flag    := 'E';
         x_ret_message := l_step_no||', Please retry this action, Proc create_po_prc terminated due to Error: '||SUBSTR(SQLERRM,1,100);
   END;   

   -----------------------------------------------------------------------------------------
   --This proc gets invoked from Create PO button is clicked in ITT Workbench user interface
   --Purpose is to validate/derive against all ITT lines and send back to UI for PO creation
   -----------------------------------------------------------------------------------------
   PROCEDURE eligible_create_po_lines (   p_itt_number        IN  VARCHAR2
                                        , p_username          IN  VARCHAR2
										, p_called_from		  IN  VARCHAR2
                                        , x_create_po_tbl     OUT CANON_E580_CREATE_PO_TBL_TYP
                                        , x_return_flag       OUT VARCHAR2
                                        , x_return_message    OUT VARCHAR2
                                      )
   IS              
      l_create_po_tbl  CANON_E580_CREATE_PO_TBL_TYP := CANON_E580_CREATE_PO_TBL_TYP();
   BEGIN
      validate_itt_lines(   p_itt_num     => p_itt_number
                          , p_user        => p_username
                          , x_ret_code    => x_return_flag
                          , x_ret_message => x_return_message
                        );

      IF (NVL(x_return_flag,'S') <> 'S')
      THEN
         x_create_po_tbl  := l_create_po_tbl;
         RETURN;
      END IF;
      
	  IF p_called_from = 'C'
	  THEN
      --Get all ITT lines (p_itt_number) eligible for PO creation
		derive_po_lines( p_itt_num        => p_itt_number
                       , p_user           => p_username
                       , x_create_po_tbl  => l_create_po_tbl
                       , x_ret_flag       => x_return_flag
                       , x_ret_message    => x_return_message
                     );
					 
	  ELSIF p_called_from = 'A'
	  THEN
		derive_append_po_lines( p_itt_num        => p_itt_number
							   , p_cusa_po_num   => NULL
							   , p_dealer_po_num => NULL
							   , p_user          => p_username
							   , x_create_po_tbl => l_create_po_tbl
							   , x_ret_flag      => x_return_flag
							   , x_ret_message   => x_return_message
							 );
	  END IF;

      x_create_po_tbl  := l_create_po_tbl;
	  
	  IF x_return_flag <> 'S'
	  THEN
	  l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_AUTOCREATE_PO_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ELIGIBLE_CREATE_PO_LINES';
         l_itt_err_tbl(1).itt_number       := p_itt_number;
         l_itt_err_tbl(1).error_message    := x_return_message;
         l_itt_err_tbl(1).created_by       := 1;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := 1;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
	  END IF;
	  
      x_return_flag    := 'S';
      x_return_message := '';
   EXCEPTION
      WHEN OTHERS THEN
         x_return_flag    := 'E';
         x_return_message := 'Please retry this action, Proc create_po_prc terminated due to Error: '||SUBSTR(SQLERRM,1,100);
   END;
   
   
   /******************************************************************************************************
   This procedure gets invoked by ITT workbench once after the success/failure call of S21 Create PO API.
   Logic includes to update generated PO number in ITT custom table if success case or capture errors
   *******************************************************************************************************/
   PROCEDURE update_po_itt(       p_itt_number IN VARCHAR2
                                , p_po_number  IN VARCHAR2
                                , p_err_msg    IN VARCHAR2
                                , p_user_name  IN VARCHAR2
                          )
   IS
   l_cnt number;
   l_err_msg VARCHAR2(1000);
   
   BEGIN
      IF (p_po_number IS NOT NULL) 
      THEN
         UPDATE  canon_e580_itt_lines_tbl l
         SET     (l.cusa_po_number, l.cusa_po_line_number, l.po_date) 
                     = ( SELECT   pl.po_ord_num po_num, 
                                  pl.po_ord_dtl_line_num,
                                  TO_DATE(SUBSTR(pl.ezintime,1,14),'YYYYMMDDHH24MISS')
                         FROM     po_dtl pl, po p 
                         WHERE    pl.trx_ref_num = l.itt_number
						 AND      pl.po_ord_num = p.po_ord_num
                         AND      pl.cpo_ord_num = l.order_number
                         AND      pl.cpo_dtl_line_num||'.'||pl.cpo_dtl_line_sub_num = l.line_number
                         AND      pl.mdse_cd = l.item
                         AND      pl.po_line_tp_cd = '01'  --Goods
                         AND      pl.glbl_cmpy_cd = g_glbl_cmpy_cd
                         AND      p.glbl_cmpy_cd = g_glbl_cmpy_cd)
                ,last_updated_by  = p_user_name
                ,last_update_date = SYSDATE
         WHERE   l.itt_number = p_itt_number
         AND     l.itt_line_type='GOODS';
		 
		 UPDATE  canon_e580_itt_lines_tbl l
         SET     (l.cusa_po_number, l.cusa_po_line_number, l.po_date) 
                     = ( SELECT   pl.po_ord_num po_num, 
                                  pl.po_ord_dtl_line_num,
                                  TO_DATE(SUBSTR(pl.ezintime,1,14),'YYYYMMDDHH24MISS')
                         FROM     po_dtl pl, po p 
                         WHERE    pl.trx_ref_num = l.itt_number
                         --AND      pl.cpo_ord_num = l.order_number
                         AND      pl.po_ord_num = p.po_ord_num
                         --AND      pl.cpo_dtl_line_num||'.'||pl.cpo_dtl_line_sub_num = l.line_number
                         AND      pl.mdse_cd = l.item
						 AND      pl.mdse_desc_short_txt = l.item_description
                         AND      pl.po_line_tp_cd = '02'  --Expense
                         AND      pl.glbl_cmpy_cd = g_glbl_cmpy_cd
                         AND      p.glbl_cmpy_cd = g_glbl_cmpy_cd)
                ,last_updated_by  = p_user_name
                ,last_update_date = SYSDATE
         WHERE   l.itt_number = p_itt_number
         AND     l.itt_line_type='EXPENSE';
         
      END IF;
	  
	  BEGIN
	  UPDATE  canon_e580_itt_lines_tbl l
         SET l.attribute1 = NULL
	   WHERE l.attribute1 IS NOT NULL
	     AND l.itt_number = p_itt_number
	     AND EXISTS 
			 (SELECT 1 
				FROM po_dtl pl, po p 
			   WHERE pl.trx_ref_num = l.itt_number
				 AND pl.po_ord_num = p.po_ord_num
				 AND pl.cpo_ord_num = l.order_number
				 AND pl.cpo_dtl_line_num||'.'||pl.cpo_dtl_line_sub_num = l.line_number
				 AND pl.mdse_cd = l.item
				 AND pl.glbl_cmpy_cd = g_glbl_cmpy_cd
				 AND p.glbl_cmpy_cd = g_glbl_cmpy_cd);
	   EXCEPTION
	   WHEN OTHERS THEN
			l_err_msg := 'Error Updating attribute1 value: '|| SUBSTR(SQLERRM, 1, 100);
	   END;
	   
	   UPDATE canon_e580_itt_headers_tbl cih
	   SET cih.itt_status = ( SELECT cvt.val1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
								WHERE ct.cd_name = 'CANON_E580_HDR_STATUS' AND   ct.cd_id = cvt.cd_id
								AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
								AND cvt.val51 = 2)
		WHERE cih.itt_status = ( SELECT cvt.val1 FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
								WHERE ct.cd_name = 'CANON_E580_HDR_STATUS' AND   ct.cd_id = cvt.cd_id
								AND (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1))
								AND cvt.val51 = 4)
		 AND cih.itt_number = p_itt_number
		 AND NOT EXISTS 
			 (SELECT 1 
				FROM canon_e580_itt_lines_tbl l
			   WHERE cih.itt_number = l.itt_number
			     AND l.attribute1 = 'A');
      
      IF (p_err_msg IS NOT NULL) 
      THEN
         --Dealer/CUSA Purchase Order failed to create, Error Message : <S21 API return err msg> 
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_AUTOCREATE_PO_PKG';
         l_itt_err_tbl(1).procedure_name   := 'UPDATE_PO_ITT';
         l_itt_err_tbl(1).itt_number       := p_itt_number;
         l_itt_err_tbl(1).error_message    := p_err_msg;
         l_itt_err_tbl(1).created_by       := p_user_name;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := p_user_name;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
      END IF;

      COMMIT;
   EXCEPTION
      WHEN OTHERS THEN
         ROLLBACK;
   END;

END CANON_E580_AUTOCREATE_PO_PKG;
/
