create or replace PACKAGE canon_e404_sf_product_pkg
AS
G_PACKAGE_NAME VARCHAR2(28) := 'canon_e404_sf_product_pkg';
PROCEDURE extract_products(x_return_status   OUT VARCHAR2
                          ,x_msg_data        OUT VARCHAR2
                          );

END canon_e404_sf_product_pkg;
/

create or replace PACKAGE BODY              canon_e404_sf_product_pkg
AS
PROCEDURE extract_products(x_return_status   OUT VARCHAR2
                          ,x_msg_data        OUT VARCHAR2
                          )
AS
    
    l_procedure_name VARCHAR2(28) := 'extract_products';
  CURSOR updt_prod_cur IS
  SELECT e404.*,
         e210.som_prod_desc_txt pname_desc,
         e210.som_item_nm pcode,
         e210.SOM_SUB_TP_NM ptype,
         e210.som_catg_nm pcategory,
         e210.som_sub_catg_nm pfamily
    FROM SOM_PRC_BOOK_INFO e210
        ,canon_e404_sf_prod_mapping_tbl e404
   WHERE e404.sf_product_id IS NOT NULL
     AND e210.som_item_nm(+) = e404.item_code
     AND e210.SOM_SUB_TP_NM(+) = e404.sub_type
     AND e210.som_catg_nm(+) = e404.CATEGORY
     AND e210.som_sub_catg_nm(+) = e404.sub_category
     ;

  l_is_active              canon_e404_sf_prod_mapping_tbl.is_active%TYPE;
  l_update_flag            NUMBER := 0;

  l_product_del_count      NUMBER := 0;
  l_product_ext_i_count    NUMBER := 0;
  l_product_ext_u_count    NUMBER := 0;

BEGIN

  DELETE
    FROM canon_e404_sf_prod_mapping_tbl e404
   WHERE e404.sf_product_id IS NULL;

  l_product_del_count := SQL%ROWCOUNT;

  IF l_product_del_count > 0 THEN
    COMMIT;
  END IF;

  INSERT INTO canon_e404_sf_prod_mapping_tbl
             (
              inventory_item_id,
              product_name_desc,
              item_code,
              sub_type,
              CATEGORY,
              sub_category,
              is_active,
              status_flag,
              oracle_load_date,
              sf_product_id,
              sf_update_date,
              comments
             )
              SELECT dmi.mdse_cd
                     ,e210.SOM_PROD_DESC_TXT pname_desc
                     ,e210.SOM_ITEM_NM pcode
                     ,e210.SOM_SUB_TP_NM ptype
                     ,e210.SOM_CATG_NM pcategory
                     ,e210.SOM_SUB_CATG_NM pfamily
                     ,'TRUE' is_active
                     ,'I' status_flag
                     ,SYSDATE oracle_load_date
                     ,NULL sf_product_id
                     ,NULL sf_update_date
                     ,NULL comments
              FROM SOM_PRC_BOOK_INFO e210
                   ,mdse dmi -- DB Changes
                   ,coa_proj cp
--                   ,mdse m
              WHERE dmi.glbl_cmpy_cd = 'ADB'
                AND dmi.ezcancelflag = '0'
                AND cp.glbl_cmpy_cd = 'ADB'
                AND cp.ezcancelflag = '0'
--                AND m.glbl_cmpy_cd = 'ADB'
--                AND m.ezcancelflag = '0'
                AND e210.SOM_ITEM_NM = dmi.mdse_cd
                AND dmi.coa_mdse_tp_cd = cp.coa_proj_cd
                AND cp.coa_proj_nm IN ('MERCHANDISE SET','MACHINE')
--                AND m.mdse_cd = dmi.mdse_cd
--                AND m.invty_ctrl_flg = 'Y' --Inventory Items
                 AND NOT EXISTS (SELECT '1'
                                   FROM canon_e404_sf_prod_mapping_tbl e404
                                  WHERE sf_product_id IS NOT NULL
                                    AND e404.item_code = e210.SOM_ITEM_NM
                                    AND e404.sub_type = e210.SOM_SUB_TP_NM
                                    AND e404.CATEGORY = e210.SOM_CATG_NM
                                    AND e404.sub_category = e210.SOM_SUB_CATG_NM
                                )
               ;

  COMMIT;

  SELECT COUNT(*)
    INTO l_product_ext_i_count
    FROM canon_e404_sf_prod_mapping_tbl e404
   WHERE e404.status_flag = 'I'
     AND sf_product_id IS NULL;

  FOR updt_prod_rec IN updt_prod_cur
  LOOP
    l_is_active   := NULL;
    l_update_flag := 0;

    IF updt_prod_rec.pcode IS NULL AND updt_prod_rec.is_active = 'TRUE' THEN
      l_is_active := 'FALSE';
      l_update_flag := l_update_flag + 1;
    ELSIF updt_prod_rec.pcode IS NOT NULL AND updt_prod_rec.is_active = 'FALSE' THEN
      l_is_active := 'TRUE';
      l_update_flag := l_update_flag + 1;
    END IF;

    IF nvl(updt_prod_rec.product_name_desc,'-1') <> nvl(updt_prod_rec.pname_desc,'-1') THEN
      l_update_flag := l_update_flag + 1;
    END IF;
    
--    IF nvl(updt_prod_rec.CATEGORY,'-1') <> nvl(updt_prod_rec.pcategory,'-1') THEN
--      l_update_flag := l_update_flag + 1;
--    END IF;
--    
--    IF nvl(updt_prod_rec.sub_type,'-1') <> nvl(updt_prod_rec.ptype,'-1') THEN
--      l_update_flag := l_update_flag + 1;
--    END IF;
--    
--    IF nvl(updt_prod_rec.sub_category,'-1') <> nvl(updt_prod_rec.pfamily,'-1') THEN
--      l_update_flag := l_update_flag + 1;
--    END IF;
    
    
    IF l_update_flag > 0 THEN
      UPDATE canon_e404_sf_prod_mapping_tbl e404
         SET product_name_desc = updt_prod_rec.pname_desc,
             is_active = nvl(l_is_active, is_active),
--             category = updt_prod_rec.pcategory,
--             sub_type = updt_prod_rec.ptype,
--             sub_category = updt_prod_rec.pfamily,
             --load_status = 'U',
             oracle_load_date = SYSDATE,
             status_flag = 'U',
             sf_update_date = NULL,
             comments = NULL
       WHERE e404.sf_product_id = updt_prod_rec.sf_product_id
        ;

    END IF;

  END LOOP;

  COMMIT;

  SELECT COUNT(*)
    INTO l_product_ext_u_count
    FROM canon_e404_sf_prod_mapping_tbl e404
   WHERE e404.status_flag = 'U'
     AND sf_product_id IS NOT NULL;

  x_return_status := 'S';

  dbms_output.put_line('*************************************');
  dbms_output.put_line('Salesforce.com Product Extraction');
  dbms_output.put_line('*************************************');

  dbms_output.put_line('Headers');
  dbms_output.put_line('-------');
  dbms_output.put_line('Records Deleted  : '||l_product_del_count);
  dbms_output.put_line('Records Extracted for Insert: '|| l_product_ext_i_count);
  dbms_output.put_line('Records Extracted for Update: '|| l_product_ext_u_count);
  dbms_output.put_line(' ');

EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    x_return_status := 'E';
    x_msg_data      := 'Unexpected Error:'|| SUBSTR(SQLERRM,2000);
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
--    log_error('EXTRACT_PRODUCTS',NULL,NULL,NULL,NULL,NULL,'Unexpected Error:'||SUBSTR(SQLERRM,2000));
END extract_products;

END canon_e404_sf_product_pkg;
/