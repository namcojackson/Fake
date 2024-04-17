CREATE OR REPLACE PACKAGE CANON_E580_PO_RECEIPTS_PKG 
AS
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
-- File Name  :   CANON_E580_PO_RECEIPTS_PKG.sql                                                                                            --
-- Created On :   11/18/2015                                                                                                                --
-- Created By :   Santosh Mummidi                                                                                                           --
-- Purpose    :   This package includes all the program units related to Purchase Order Receiving transactions within ITT workbench         --
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
-- Modification History:                                                                                                                    --
-- Version    By                         Date           Comments                                                                            --
-- ------     ----------------------     -----------    --------                                                                            --
-- 1.0        Santosh Mummidi            18-Nov-2015    Initial                                                                             --
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
   TYPE ref_po_receipts IS REF CURSOR;
   g_glbl_cmpy_cd    VARCHAR2(10):='ADB';

   PROCEDURE serial_number_check (  p_itt_number     IN   VARCHAR2
                                   ,p_serial_number  IN   VARCHAR2
                                   ,x_return_flag    OUT  VARCHAR2
                                   ,x_return_message OUT  VARCHAR2
                                 );

   PROCEDURE eligible_po_lines (  p_itt_number        IN   VARCHAR2
                                 ,p_user_name         IN   VARCHAR2
                                 ,x_eligible_po_lines OUT  ref_po_receipts
                               );

   PROCEDURE eligible_expense_po_lines (   p_itt_number            IN  VARCHAR2
                                          ,p_user_name             IN  VARCHAR2
                                          ,x_eligible_exp_po_lines OUT ref_po_receipts
                                       );

   PROCEDURE insert_receipt_prc (  p_por_dtls       IN  CANON_E580_POR_TYP_REC
                                  ,p_user_name      IN  VARCHAR2
                                  ,p_status_code    OUT VARCHAR2
                                  ,p_status_message OUT VARCHAR2
                                );
                                
   PROCEDURE update_por_error(   p_itt_number    IN VARCHAR2
                                ,p_itt_line_num  IN VARCHAR2 
                                ,p_po_number     IN VARCHAR2
                                ,p_err_msg       IN VARCHAR2
                                ,p_user_name     IN VARCHAR2
                             );
                             
   FUNCTION derive_rws_ref_num(p_po_ord_num IN VARCHAR2, p_rws_ref_num IN VARCHAR2) RETURN VARCHAR2;

END  CANON_E580_PO_RECEIPTS_PKG;
/

CREATE OR REPLACE PACKAGE BODY CANON_E580_PO_RECEIPTS_PKG
IS
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
-- File Name  :   CANON_E580_PO_RECEIPTS_PKG.sql                                                                                            --
-- Created On :   11/18/2015                                                                                                                --
-- Created By :   Santosh Mummidi                                                                                                           --
-- Purpose    :   This package includes all the program units related to Purchase Order Receiving transactions within ITT workbench         --
-- ---------------------------------------------------------------------------------------------------------------------------------------- --
-- Modification History:                                                                                                                    --
-- Version    By                         Date           Comments                                                                            --
-- ------     ----------------------     -----------    --------                                                                            --
-- 1.0        Santosh Mummidi            18-Nov-2015    Initial                                                                             --
-- ---------------------------------------------------------------------------------------------------------------------------------------- --   
   
   --Declare variables
   l_error_msg      VARCHAR2(3999) := 'No Error';
   l_itt_err_tbl    canon_e580_itt_util_pkg.itt_err_tbl_typ;

   --------------------------------------------------------------------------------------------------------------
   --This procedure is invoked from ITT workbench UI /Receive PO when trying to validate entered Serial Number
   --Logic includes to validate the serial numer and return Y if good to receive. N if same serial already exists.
   --------------------------------------------------------------------------------------------------------------
   PROCEDURE serial_number_check (   p_itt_number     IN   VARCHAR2
                                   , p_serial_number  IN   VARCHAR2
                                   , x_return_flag    OUT  VARCHAR2
                                   , x_return_message OUT  VARCHAR2
                                 )
   IS
      l_ret_flg  VARCHAR2(1) := 'Y';
      l_ret_msg  VARCHAR2(240);
   BEGIN
      SELECT rtrim(xmlagg(xmlelement(e, (s.ser_num ||' exists in organization '||s.to_loc_cd||' for item '||s.mdse_cd) || CHR(10)||'')).extract('//text()').extract('//text()') ,', ')
      INTO l_ret_msg
      FROM po_ser_num s
      WHERE s.glbl_cmpy_cd = g_glbl_cmpy_cd
      AND s.ser_num = p_serial_number;
      
      IF (l_ret_msg IS NOT NULL)
      THEN
         l_ret_flg := 'N';
      END IF;
      x_return_flag    := l_ret_flg;
      x_return_message := l_ret_msg;

   EXCEPTION
      WHEN NO_DATA_FOUND THEN
         x_return_flag := 'Y';
      WHEN OTHERS THEN
         x_return_flag := 'Y';
         l_error_msg := 'This procedure serial_number_check terminated due to '||CHR(10)||SQLCODE||' - '||SQLERRM;
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_PO_RECEIPTS_PKG';
         l_itt_err_tbl(1).procedure_name   := 'SERIAL_NUMBER_CHECK';
         l_itt_err_tbl(1).itt_number       := p_itt_number;
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := 1;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := 1;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END;
   
   FUNCTION derive_rws_ref_num(p_po_ord_num IN VARCHAR2, p_rws_ref_num IN VARCHAR2) RETURN VARCHAR2
   IS
      l_rws_ref_num VARCHAR2(15) := p_rws_ref_num;
      l_instr_flag  NUMBER;
   BEGIN      
      IF (l_rws_ref_num IS NOT NULL)
      THEN  
         l_instr_flag := INSTR(l_rws_ref_num,'-');
         IF (l_instr_flag > 0)
         THEN
            SELECT p_po_ord_num||'-'||TO_NUMBER(SUBSTR(l_rws_ref_num,l_instr_flag+1)+1)
            INTO   l_rws_ref_num
            FROM   DUAL;
         ELSIF (l_instr_flag = 0) 
         THEN
            l_rws_ref_num := l_rws_ref_num||'-1';
         END IF;
         RETURN(l_rws_ref_num);
      ELSE
         RETURN(p_po_ord_num);
      END IF;
   EXCEPTION
      WHEN OTHERS THEN
         RETURN(l_rws_ref_num);
   END;

   -------------------------------------------------------------------------------------------
   --This procedure is invoked from ITT workbench User Interface when clicked on Receive PO.
   --Logic to extract all eligible ITT PO lines against that input ITT Number and return back
   -------------------------------------------------------------------------------------------
   PROCEDURE eligible_po_lines (   p_itt_number        IN   VARCHAR2
                                 , p_user_name         IN   VARCHAR2
                                 , x_eligible_po_lines OUT  ref_po_receipts
                                )
   IS
   l_query varchar2(10000);
   BEGIN
      l_query :=  'SELECT  
                            itt_number
                          , so_number
                          , so_line_number
                          , po_number
                          , po_line_number
                          , ordered_item
                          , description
                          , line_qty
                          , serial_number_flag
                          , lot_control_flag
                          , license_number_flag   
                          , license_number_required_flag
                          , unit_meas_lookup_code
                          , organization_code
                          , lot_number
                          , qty_avbl
                          , closed_code
                          , quantity_received
                          , destination_subinventory
                          , itt_line_type
                          , eta_dt
                          , stk_sts_cd
						  , decode(to_number(po_line_number), 1, po_number, (po_number||''-''||(to_number(po_line_number)-1))) rws_ref_num
						  , disp_line_number
						  , disp_item
                          --, canon_e580_po_receipts_pkg.derive_rws_ref_num(po_number,rws_ref_no)  rws_ref_num
                   FROM ( SELECT   cih.itt_number            itt_number
                                 , cil.order_number          so_number
                                 , cil.line_number           so_line_number
								 , cil.line_id              disp_line_number
                                 , pd.orig_po_ord_num       po_number
                                 , pd.orig_po_ord_dtl_line_num
                                                             po_line_number
                                 , m.mdse_cd                 ordered_item
								 , SUBSTR(m.mdse_cd, 1, 8)   disp_item
                                 , m.mdse_nm                 description
                                 , pd.po_qty                 line_qty
                                 , m.rcv_ser_take_flg        serial_number_flag
                                 , DECODE(cil.merchandise,''71'',NULL,''72'',NULL,''73'',NULL,''74'',NULL,
                                         DECODE(LENGTH(m.mdse_cd),10,''Y'',''N''))
                                                             lot_control_flag
                                 , ''N''                     license_number_flag
                                 , ''N''                     license_number_required_flag
                                 ,( SELECT u.pkg_uom_nm FROM pkg_uom u
                                    WHERE  u.pkg_uom_cd = pd.po_disp_uom_cd
                                    AND    u.glbl_cmpy_cd = '''||g_glbl_cmpy_cd||'''
                                  )    unit_meas_lookup_code
                                 , pd.dest_rtl_wh_cd        organization_code
                                 ,DECODE(cil.merchandise,''71'',NULL,''72'',NULL,''73'',NULL,''74'',NULL,
                                         DECODE(LENGTH(m.mdse_cd),10,SUBSTR(m.mdse_cd,LENGTH(m.mdse_cd)-1,LENGTH(m.mdse_cd)),NULL))
                                                             lot_number
                                 , pd.po_bal_qty             qty_avbl
                                 , DECODE(ps.po_sts_nm,''Closed'',''Closed'',NULL)  closed_code
                                 , pd.po_rcv_qty             quantity_received
                                 ,(SELECT cvt.val2  description
                                   FROM   canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                   WHERE  ct.cd_name = ''CANON_E580_DEF_SUBINV''
                                   AND    ct.cd_id = cvt.cd_id
                                   AND    cvt.val1 = pd.dest_rtl_wh_cd
                                   AND    SYSDATE BETWEEN NVL(cvt.start_date_active,SYSDATE-1) AND NVL(cvt.end_date_active,SYSDATE+1))
                                                             destination_subinventory
                                 , cil.itt_line_type
                                 , pd.eta_dt
                                 , NVL(pd.from_stk_sts_cd, 1)  stk_sts_cd
                                 ,(SELECT  MAX(rws_ref_num) FROM rws_hdr WHERE po_rcv_trx_hdr_num =  pd.orig_po_ord_num
                                   AND rws_ref_num LIKE pd.orig_po_ord_num||''%'') rws_ref_no
                          FROM   canon_e580_itt_headers_tbl cih
                               , canon_e580_itt_lines_tbl cil
                               , po p
                               , po_dtl pd
                               , mdse m
                               , po_sts ps
                          WHERE  cih.itt_number = cil.itt_number
                          AND    cil.cusa_po_number = p.po_ord_num
                          AND    p.po_ord_num = pd.po_ord_num
                          AND    cil.item = pd.mdse_cd
                          AND    pd.mdse_cd = m.mdse_cd
                          AND    pd.po_sts_cd = ps.po_sts_cd
                          AND    cil.itt_line_type = ''GOODS''
                          AND    NVL(pd.po_qty, 0) <> NVL(pd.po_rcv_qty, 0)
                          AND    ps.po_sts_nm NOT IN (''Closed'',''Cancelled'')
                          AND    cil.itt_number = pd.trx_ref_num
                          AND    cil.line_number = pd.trx_ref_line_num||''.''||pd.trx_ref_line_sub_num
                          AND    cih.itt_number   = '''||p_itt_number||'''
                          AND    p.glbl_cmpy_cd   = '''||g_glbl_cmpy_cd||'''
                          AND    pd.glbl_cmpy_cd  = '''||g_glbl_cmpy_cd||'''
                          AND    ps.glbl_cmpy_cd  = '''||g_glbl_cmpy_cd||'''
                        )
                   ORDER BY itt_number, po_number, so_line_number, po_line_number, itt_line_type DESC';      
      dbms_output.put_line(l_query);      
      OPEN x_eligible_po_lines
      FOR  l_query;
   EXCEPTION
      WHEN OTHERS THEN
         l_error_msg := 'This procedure eligible_po_lines terminated due to '||CHR(10)||SQLCODE||' - '||SQLERRM;
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_PO_RECEIPTS_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ELIGIBLE_PO_LINES';
         l_itt_err_tbl(1).itt_number       := p_itt_number;
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := p_user_name;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := p_user_name;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END;
   
   ---------------------------------------------------------------------------------------------------
   --This procedure is invoked from ITT workbench User Interface when clicked on Receive PO.
   --Logic to extract all eligible ITT EXPENSE PO lines against that input ITT Number and return back
   ---------------------------------------------------------------------------------------------------
   PROCEDURE eligible_expense_po_lines (    p_itt_number            IN     VARCHAR2
                                          , p_user_name             IN     VARCHAR2
                                          , x_eligible_exp_po_lines OUT    ref_po_receipts
                                       )
   IS
   BEGIN
      OPEN x_eligible_exp_po_lines
      FOR
      SELECT  itt_number
            , so_number
            , so_line_number
            , po_number
            , po_line_number
            , ordered_item
            , description
            , line_qty
            , serial_number_flag
            , lot_control_flag
            , license_number_flag   
            , license_number_required_flag
            , unit_meas_lookup_code
            , organization_code
            , lot_number
            , qty_avbl
            , closed_code
            , quantity_received
            , destination_subinventory
            , itt_line_type
            , eta_dt
            , stk_sts_cd
            , NULL rws_ref_num
      FROM ( SELECT   cih.itt_number         itt_number
                    , NULL                   so_number
                    , NULL                   so_line_number
                    , pd.po_ord_num          po_number
                    , pd.po_ord_dtl_line_num po_line_number
                    , pd.mdse_cd             ordered_item
                    , cil.item_description   description
                    , pd.po_qty              line_qty
                    , 'N'                    serial_number_flag
                    , 'N'                    lot_control_flag
                    , 'N'                    license_number_flag
                    , 'N'                    license_number_required_flag
                    ,( SELECT u.pkg_uom_nm FROM pkg_uom u
                       WHERE  u.pkg_uom_cd = pd.po_disp_uom_cd
                       AND    u.glbl_cmpy_cd = g_glbl_cmpy_cd
                     )                       unit_meas_lookup_code
                    , pd.dest_rtl_wh_cd     organization_code
                    , NULL                   lot_number
                    , pd.po_bal_qty          qty_avbl
                    , DECODE(ps.po_sts_nm,'Closed','Closed',NULL)
                                             closed_code
                    , pd.po_rcv_qty          quantity_received
                    , pd.dest_rtl_swh_cd    destination_subinventory
                    , cil.itt_line_type      itt_line_type
                    , pd.eta_dt
                    , NVL(pd.from_stk_sts_cd, 1)  stk_sts_cd
             FROM   canon_e580_itt_headers_tbl cih
                  , canon_e580_itt_lines_tbl cil
                  , po p
                  , po_dtl pd
                  , mdse m
                  , po_sts ps
             WHERE  cih.itt_number = cil.itt_number
             AND    cil.cusa_po_number = p.po_ord_num
             AND    p.po_ord_num = pd.po_ord_num
             AND    cil.cusa_po_line_number = pd.po_ord_dtl_line_num
             AND    cil.item = m.mdse_cd
             AND    pd.mdse_cd = m.mdse_cd
             AND    pd.po_sts_cd = ps.po_sts_cd
             AND    cil.itt_line_type = 'EXPENSE'
             AND    NVL(pd.po_qty, 0) <> NVL(pd.po_rcv_qty, 0)
             AND    ps.po_sts_nm NOT IN ('Closed','Cancelled')
             AND    cil.itt_number = pd.trx_ref_num
             AND    cih.itt_number   = p_itt_number
             AND    p.glbl_cmpy_cd   = g_glbl_cmpy_cd
             AND    pd.glbl_cmpy_cd  = g_glbl_cmpy_cd
             AND    m.glbl_cmpy_cd   = g_glbl_cmpy_cd
             AND    ps.glbl_cmpy_cd  = g_glbl_cmpy_cd
           UNION ALL
             SELECT   p_itt_number             itt_number
                    , NULL                     so_number
                    , NULL                     so_line_number
                    , pd.po_ord_num            po_number
                    , pd.po_ord_dtl_line_num   po_line_number
                    , pd.mdse_cd               ordered_item
                    , pd.mdse_nm               description
                    , pd.po_qty                line_qty
                    , 'N'                      serial_number_flag
                    , 'N'                      lot_control_flag
                    , 'N'                      license_number_flag
                    , 'N'                      license_number_required_flag
                    ,( SELECT u.pkg_uom_nm FROM pkg_uom u
                       WHERE  u.pkg_uom_cd = pd.po_disp_uom_cd
                       AND    u.glbl_cmpy_cd = g_glbl_cmpy_cd
                     )                         unit_meas_lookup_code
                    , pd.dest_rtl_wh_cd       organization_code
                    , NULL                     lot_number
                    , pd.po_bal_qty            qty_avbl
                    , DECODE(ps.po_sts_nm,'Closed','Closed',NULL)
                                               closed_code
                    , pd.po_rcv_qty            quantity_received
                    , pd.dest_rtl_swh_cd      destination_subinventory
                    , 'EXPENSE'                itt_line_type
                    , pd.eta_dt
                    , NVL(pd.from_stk_sts_cd, 1)  stk_sts_cd
             FROM   po p
                  , po_dtl pd
                  , mdse m
                  , po_sts ps
             WHERE  p.po_ord_num = pd.po_ord_num
             AND    pd.mdse_cd = m.mdse_cd
             AND    pd.po_sts_cd = ps.po_sts_cd
             AND    UPPER (pd.mdse_cd) IN (SELECT cvt.val2 FROM  canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                           WHERE ct.cd_name = 'CANON_E580_EXP_ITEM'
                                           AND   ct.cd_id = cvt.cd_id
                                           AND   cvt.val1 IN ('FINDER_FEE','INSTALL_CREDIT')
                                           AND  (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1)))
             AND    NVL(pd.po_qty, 0) <> NVL(pd.po_rcv_qty, 0)
             AND    ps.po_sts_nm NOT IN ('Closed','Cancelled')
             AND    pd.trx_ref_num = p_itt_number
             AND    p.glbl_cmpy_cd   = g_glbl_cmpy_cd
             AND    pd.glbl_cmpy_cd  = g_glbl_cmpy_cd
             AND    m.glbl_cmpy_cd   = g_glbl_cmpy_cd
             AND    ps.glbl_cmpy_cd  = g_glbl_cmpy_cd
           )
      ORDER BY itt_number, po_number, so_line_number, po_line_number, itt_line_type DESC;
   EXCEPTION
      WHEN OTHERS THEN
         l_error_msg := 'This procedure eligible_expense_po_lines terminated due to '||CHR(10)||SQLCODE||' - '||SQLERRM;
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_PO_RECEIPTS_PKG';
         l_itt_err_tbl(1).procedure_name   := 'ELIGIBLE_EXPENSE_PO_LINES';
         l_itt_err_tbl(1).itt_number       := p_itt_number;
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := p_user_name;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := p_user_name;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END;     

   ----------------------------------------------------------------------------------------------------------
   --This procedure is invoked from ITT workbench when Confirm button is clicked on Receive PO Tab
   --Logic includes to receive the input eligible PO Receipt lines and insert into canon_e580_po_receipt_tbl
   ----------------------------------------------------------------------------------------------------------
   PROCEDURE insert_receipt_prc (   p_por_dtls       IN  CANON_E580_POR_TYP_REC
                                  , p_user_name      IN  VARCHAR2
                                  , p_status_code    OUT VARCHAR2
                                  , p_status_message OUT VARCHAR2
                                )
   IS
   BEGIN
     /* IF (p_por_dtls.COUNT = 0) THEN
         p_status_code    := 'ERROR';
         p_status_message := 'No lines selected/passed for PO Receipt';
      END IF;*/

      --Updating license number
      --FORALL upd IN p_por_dtls.FIRST..p_por_dtls.LAST
         UPDATE canon_e580_itt_lines_tbl cil
         SET    cil.license_number = p_por_dtls.license_number
         WHERE  cil.itt_number     = p_por_dtls.itt_number
         AND    cil.line_number    = p_por_dtls.itt_line_num;

      dbms_output.put_line('No.of rows Updated :'||SQL%ROWCOUNT);
      
      --Load all eligible selected/passed PO Receipt lines into ITT custom table with Bulk insert.
      --FORALL ins IN p_por_dtls.FIRST..p_por_dtls.LAST
         INSERT INTO canon_e580_po_receipt_tbl
                 (    itt_number
                    , itt_line_number
                    , cusa_po_number
                    , cusa_po_line_number
                    , po_header_id
                    , po_line_id
                    , item
                    , item_description
                    , ordered_quantity
                    , itt_line_type
                    , received_quantity
                    , lot_number
                    , serial_number
                    , license_number
                    , organization_code
                    , subinventory_code
                    , rcv_shipment_header_id
                    , rcv_shipment_line_id
                    , request_id
                    , status_flag
                    , status_message
                    , last_update_date
                    , last_updated_by
                    , creation_date
                    , created_by
                )
             SELECT DISTINCT
                    cih.itt_number
                  , cil.line_number
                  , p.po_ord_num   po_number
                  , pd.po_ord_dtl_line_num  po_line_number
                  , NULL   po_header_id
                  , NULL   po_line_id
                  , pd.mdse_cd   ordered_item
                  , pd.mdse_nm   description
                  , pd.po_qty    line_qty
                  , cil.itt_line_type
                  , p_por_dtls.rcvd_qty
                  , p_por_dtls.lot_num
                  , p_por_dtls.serial_num
                  , p_por_dtls.license_number
                  , p.invty_loc_cd   organization_code
                  , p_por_dtls.subinv   --pda.destination_subinventory
                  , NULL rcv_shipment_header_id
                  , NULL rcv_shipment_line_id
                  , NULL request_id
                  , 'UNPROCESSED' status_flag
                  , 'Unprocessed' status_message
                  , SYSDATE
                  , p_user_name
                  , SYSDATE
                  , p_user_name
             FROM   canon_e580_itt_headers_tbl cih
                  , canon_e580_itt_lines_tbl cil
                  , po p
                  , po_dtl pd
                  , mdse m
                  , po_sts ps
             WHERE  cih.itt_number = cil.itt_number
             AND    p.po_ord_num = pd.po_ord_num
             AND    cil.cusa_po_number = p.po_ord_num
             AND    cil.cusa_po_line_number = pd.po_ord_dtl_line_num
             AND    cil.item = m.mdse_cd
             AND    pd.mdse_cd = m.mdse_cd
             AND    pd.po_sts_cd = ps.po_sts_cd
             AND    cil.itt_line_type = 'GOODS'
             --AND    NVL(pd.po_qty, 0) <> NVL(pd.po_rcv_qty, 0)
             --AND    ps.po_sts_nm NOT IN ('Closed','Cancelled')
			 AND    NVL(pd.po_rcv_qty, 0) > 0
             AND    cih.itt_number = p_por_dtls.itt_number
             AND    cil.line_number = p_por_dtls.itt_line_num
             AND    p.po_ord_num = p_por_dtls.po_number
             AND    pd.po_ord_dtl_line_num = p_por_dtls.po_line_num
             AND    p.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND    pd.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND    m.glbl_cmpy_cd = g_glbl_cmpy_cd
             UNION ALL
             SELECT DISTINCT
                    cih.itt_number
                  , cil.line_number
                  , p.po_ord_num   po_number
                  , pd.po_ord_dtl_line_num  po_line_number
                  , NULL   po_header_id
                  , NULL   po_line_id
                  , pd.mdse_cd   ordered_item
                  , pd.mdse_nm   description
                  , pd.po_qty    line_qty
                  , cil.itt_line_type
                  , p_por_dtls.rcvd_qty
                  , p_por_dtls.lot_num
                  , p_por_dtls.serial_num
                  , p_por_dtls.license_number
                  , p.invty_loc_cd   organization_code
                  , p_por_dtls.subinv   --pda.destination_subinventory
                  , NULL rcv_shipment_header_id
                  , NULL rcv_shipment_line_id
                  , NULL request_id
                  , 'UNPROCESSED' status_flag
                  , 'Unprocessed' status_message
                  , SYSDATE
                  , p_user_name
                  , SYSDATE
                  , p_user_name
             FROM   canon_e580_itt_headers_tbl cih
                  , canon_e580_itt_lines_tbl cil
                  , po p
                  , po_dtl pd
                  , mdse m
                  , po_sts ps
             WHERE  cih.itt_number = cil.itt_number
             AND    p.po_ord_num = pd.po_ord_num
             AND    cil.cusa_po_number = p.po_ord_num
             AND    cil.cusa_po_line_number = pd.po_ord_dtl_line_num
             AND    cil.item = m.mdse_cd
             AND    pd.mdse_cd = m.mdse_cd
             AND    pd.po_sts_cd = ps.po_sts_cd
             AND    cil.itt_line_type = 'EXPENSE'
             --AND    NVL(pd.po_qty, 0) <> NVL(pd.po_rcv_qty, 0)
             --AND    ps.po_sts_nm NOT IN ('Closed','Cancelled')
			 AND    NVL(pd.po_rcv_qty, 0) > 0
             AND    cih.itt_number = p_por_dtls.itt_number
             --AND    cil.line_number = p_por_dtls.itt_line_num
             AND    p.po_ord_num = p_por_dtls.po_number
             AND    pd.po_ord_dtl_line_num = p_por_dtls.po_line_num
             AND    p.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND    pd.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND    m.glbl_cmpy_cd = g_glbl_cmpy_cd
             UNION ALL
             SELECT DISTINCT
                    p_por_dtls.itt_number   itt_number
                  , NULL           line_number
                  , p.po_ord_num   po_number
                  , pd.po_ord_dtl_line_num  po_line_number
                  , NULL   po_header_id
                  , NULL   po_line_id
                  , pd.mdse_cd   ordered_item
                  , pd.mdse_nm   description
                  , pd.po_qty    line_qty
                  , 'EXPENSE'    itt_line_type
                  , p_por_dtls.rcvd_qty
                  , p_por_dtls.lot_num
                  , p_por_dtls.serial_num
                  , p_por_dtls.license_number
                  , p.invty_loc_cd   organization_code
                  , p_por_dtls.subinv   --pda.destination_subinventory
                  , NULL rcv_shipment_header_id
                  , NULL rcv_shipment_line_id
                  , NULL request_id
                  , 'UNPROCESSED' status_flag
                  , 'Unprocessed' status_message
                  , SYSDATE
                  , p_user_name
                  , SYSDATE
                  , p_user_name
             FROM   po p
                  , po_dtl pd
                  , mdse m
                  , po_sts ps
             WHERE  p.po_ord_num = pd.po_ord_num
             AND    pd.mdse_cd = m.mdse_cd
             AND    pd.po_sts_cd = ps.po_sts_cd
             --AND    NVL(pd.po_qty, 0) <> NVL(pd.po_rcv_qty, 0)
             --AND    ps.po_sts_nm NOT IN ('Closed','Cancelled')
			 AND    NVL(pd.po_rcv_qty, 0) > 0
             AND    UPPER (pd.mdse_cd) IN (SELECT cvt.val2 FROM  canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                                           WHERE ct.cd_name = 'CANON_E580_EXP_ITEM'
                                           AND   ct.cd_id = cvt.cd_id
                                           AND   cvt.val1 IN ('FINDER_FEE','INSTALL_CREDIT')
                                           AND  (SYSDATE BETWEEN NVL(cvt.start_date_active, SYSDATE-1) AND NVL(cvt.end_date_active, SYSDATE+1)))
             AND    p.po_ord_num = p_por_dtls.po_number
             AND    pd.po_ord_dtl_line_num = p_por_dtls.po_line_num
             AND    p.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND    pd.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND    m.glbl_cmpy_cd = g_glbl_cmpy_cd;
      
      dbms_output.put_line('No.of rows Inserted :'||SQL%ROWCOUNT);
      
      COMMIT;
      
      p_status_code    := 'SUCCESS';
      p_status_message := 'No Error.';
   EXCEPTION
      WHEN OTHERS THEN
         ROLLBACK;
         p_status_code    := 'ERROR';
         l_error_msg      := 'Error while inserting PO Receipt trxs in table canon_e580_po_receipt_tbl, Error Message : '||SQLCODE|| ' - '|| SQLERRM;
         p_status_message := l_error_msg;
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_PO_RECEIPTS_PKG';
         l_itt_err_tbl(1).procedure_name   := 'INSERT_RECEIPT_PRC';
         l_itt_err_tbl(1).itt_number       := p_por_dtls.itt_number;
         l_itt_err_tbl(1).error_message    := l_error_msg;
         l_itt_err_tbl(1).created_by       := p_user_name;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := p_user_name;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
   END;
   
   /**********************************************************************
   This procedure gets invoked by ITT WB when S21 Receive PO API is failed 
   and error details are passed here to capture in E580 error table.
   ***********************************************************************/
   PROCEDURE update_por_error(   p_itt_number    IN VARCHAR2
                                ,p_itt_line_num  IN VARCHAR2 
                                ,p_po_number     IN VARCHAR2
                                ,p_err_msg       IN VARCHAR2
                                ,p_user_name     IN VARCHAR2
                             )
   IS
   BEGIN
      IF (p_err_msg IS NOT NULL) THEN
         --Dealer/CUSA Purchase Order failed to create, Error Message : <S21 API return err msg> 
         l_itt_err_tbl.DELETE;
         l_itt_err_tbl(1).package_name     := 'CANON_E580_PO_RECEIPTS_PKG';
         l_itt_err_tbl(1).procedure_name   := 'UPDATE_POR_ERROR';
         l_itt_err_tbl(1).itt_number       := p_itt_number;
         l_itt_err_tbl(1).line_number      := p_itt_line_num;
         l_itt_err_tbl(1).value1           := p_po_number;
         l_itt_err_tbl(1).error_message    := 'Receive PO S21 API failed, Error Message: '||SUBSTR(p_err_msg,1,3700);
         l_itt_err_tbl(1).created_by       := p_user_name;
         l_itt_err_tbl(1).creation_date    := SYSDATE;
         l_itt_err_tbl(1).last_updated_by  := p_user_name;
         l_itt_err_tbl(1).last_update_date := SYSDATE;
         canon_e580_itt_util_pkg.itt_error_log(p_err_tbl => l_itt_err_tbl);
         COMMIT;
      END IF;
   EXCEPTION
      WHEN OTHERS THEN
         ROLLBACK;
   END;
                             
END CANON_E580_PO_RECEIPTS_PKG;
/
