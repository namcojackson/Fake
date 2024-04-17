CREATE OR REPLACE PACKAGE CANON_E389_SQLS_PKG IS
--***********************************************************************************
--Program Name            : CANON_E389_SQLS_PKG
--Program Desc            : CANON_E389_SQLS_PKG is used to Do DDL And DML From 
--                          DFF JSP Screen.
--***********************************************************************************

   TYPE ref_cur_type IS REF CURSOR;

   --------------------------------------------------
   --- 170 Systems profiles                  ---
   --------------------------------------------------
   g_csr_170system_link_profile     VARCHAR2 (100) := 'CSR_E193_170SYSTEMS_LINK';
   g_csr_170system_pref_name        VARCHAR2 (100) := 'CSR_E193_170SYSTEMS_PREF_NAME';

PROCEDURE SELECT_DFF_DETAILS (p_context_value   IN  VARCHAR2,
                                 p_dff_details     OUT ref_cur_type);

PROCEDURE UPLOAD_DATA (p_source_application     IN  VARCHAR2,
                          p_source_context         IN  VARCHAR2,
                          p_context_value          IN  VARCHAR2,
                          p_user_id                IN  VARCHAR2,
                          p_attributeids           IN  JTF_VARCHAR2_TABLE_100,
                          p_attributevalues        IN  JTF_VARCHAR2_TABLE_100,
                          x_return_status          OUT VARCHAR2,
                          x_msg_data               OUT VARCHAR2);

PROCEDURE DISPLAY_DATA (p_source_application     IN  VARCHAR2,
                           p_source_context         IN  VARCHAR2,
                           p_context_value          IN  VARCHAR2,
                           p_attributeIds           OUT JTF_VARCHAR2_TABLE_100,
                           p_attributeValues        OUT JTF_VARCHAR2_TABLE_100);

FUNCTION GET_ATTR_VALUE (p_attribute_id           IN  VARCHAR2,
                            p_source_application     IN  VARCHAR2,
                            p_source_context         IN  VARCHAR2,
                            p_context_value          IN  VARCHAR2) RETURN VARCHAR2;

PROCEDURE DISPLAY_LINK (p_document_id    IN  VARCHAR2,
                           p_170_link       OUT VARCHAR2,
                           p_link_flag      OUT VARCHAR2);
                           
PROCEDURE GET_CANONBALL_ATTACH (p_ticket_id        IN     NUMBER,
                                X_ATTCH_CUR          OUT ref_cur_type);                           

END CANON_E389_SQLS_PKG;
/

CREATE OR REPLACE PACKAGE BODY CANON_E389_SQLS_PKG IS
--***********************************************************************************
--Program Name            : CANON_E389_SQLS_PKG
--Program Desc            : CANON_E389_SQLS_PKG is used to Do DDL And DML From 
--                          DFF JSP Screen.
--***********************************************************************************

   PROCEDURE SELECT_DFF_DETAILS (p_context_value   IN  VARCHAR2,
                                 p_dff_details     OUT ref_cur_type) IS

   BEGIN
   --debug_pkg1.debug_proc('CANON_E389_SQLS_PKG. SELECT_DFF_DETAILS');
   --debug_pkg1.debug_proc('p_context_value= '||p_context_value);
      OPEN p_dff_details FOR
            /*SELECT fdfcu.column_seq_num seq_number, fdfcu.form_left_prompt prompt, fdfcu.application_column_name attribute_id,
                   nvl(fdfcu.required_flag, 'N') required_flag, nvl(fdfcu.enabled_flag, 'N') enabled_flag,
                   nvl(fdfcu.display_flag, 'N') display_flag, nvl(fdfcu.security_enabled_flag, 'N') security_enabled_flag,
                   upper(ffvs.flex_value_set_name) value_set_name, fdfcu.default_type, fdfcu.default_value, fdfcu.display_size
            FROM fnd_descr_flex_col_usage_vl@candev  fdfcu,
                 fnd_flex_value_sets@candev          ffvs,
                 fnd_application@candev              fa,
                 fnd_lookups@candev                  fl
            WHERE fdfcu.descriptive_flexfield_name = 'Canon JSP DFF'
            AND   fdfcu.descriptive_flex_context_code = p_context_value
            AND   fa.application_short_name = 'CANON'
            AND   fdfcu.application_id = fa.application_id
            AND   ffvs.flex_value_set_id(+) = fdfcu.flex_value_set_id
            AND   fl.lookup_type = 'FLEX_DEFAULT_TYPE'
            AND   fl.enabled_flag(+) = 'Y'
            AND   SYSDATE BETWEEN nvl(fl.start_date_active(+), SYSDATE) AND nvl(fl.end_date_active(+), SYSDATE)
            AND    fdfcu.default_type = fl.lookup_code(+)
            ORDER BY column_seq_num;*/
            
            SELECT val.val51 seq_number,
	             val.val3 prompt,
	             val.val4 attribute_id,
	             NVL (val.val5, 'N') required_flag,
	             NVL (val.val6, 'N') enabled_flag,
	             NVL (val.val7, 'N') display_flag,
	             NVL (val.val8, 'N') security_enabled_flag,
	             UPPER (val.val9) value_set_name,
	             val.val10 default_type,
	             val.val11 DEFAULT_VALUE,
	             val.val12 display_size
	        FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
	       WHERE     cd.cd_id = val.cd_id
	             AND cd_name = 'CSR_E193_JSP_DFF'
	             AND val.val1 = p_context_value
		ORDER BY val.val51;
            
          
   EXCEPTION
      WHEN OTHERS THEN
      --debug_pkg1.debug_proc('In Exp E389'||sqlerrm);
         OPEN p_dff_details FOR
            SELECT NULL
            FROM   DUAL
            WHERE  ROWNUM = 0;

   END SELECT_DFF_DETAILS;

   PROCEDURE UPLOAD_DATA (p_source_application     IN  VARCHAR2,
                          p_source_context         IN  VARCHAR2,
                          p_context_value          IN  VARCHAR2,
                          p_user_id                IN  VARCHAR2,
                          p_attributeids           IN  JTF_VARCHAR2_TABLE_100,
                          p_attributevalues        IN  JTF_VARCHAR2_TABLE_100,
                          x_return_status          OUT VARCHAR2,
                          x_msg_data               OUT VARCHAR2) IS

      v_count             NUMBER;
      v_AttributesRec     CANON_E389_DFF_UTILITY_PKG.AttributesRec;

   BEGIN
 --debug_pkg1.debug_proc ('Inside canon_e389_sql_pkg.UPLOAD_DATA');
      SELECT count(1)
      INTO v_count
      FROM CANON_E193_JSP_DFF_UTIL
      WHERE source_application = p_source_application
      AND   source_context     = p_source_context
      AND   context_value      = p_context_value;
      --debug_pkg1.debug_proc ('v_count = '||v_count);

      IF (v_count = 0) THEN

         FOR i IN 1..p_attributeids.COUNT
         LOOP

            IF (p_attributeids(i) = 'ATTRIBUTE1') THEN
               v_AttributesRec.ATTRIBUTE1 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE2') THEN
               v_AttributesRec.ATTRIBUTE2 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE3') THEN
               v_AttributesRec.ATTRIBUTE3 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE4') THEN
               v_AttributesRec.ATTRIBUTE4 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE5') THEN
               v_AttributesRec.ATTRIBUTE5 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE6') THEN
               v_AttributesRec.ATTRIBUTE6 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE7') THEN
               v_AttributesRec.ATTRIBUTE7 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE8') THEN
               v_AttributesRec.ATTRIBUTE8 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE9') THEN
               v_AttributesRec.ATTRIBUTE9 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE10') THEN
               v_AttributesRec.ATTRIBUTE10 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE11') THEN
               v_AttributesRec.ATTRIBUTE11 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE12') THEN
               v_AttributesRec.ATTRIBUTE12 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE13') THEN
               v_AttributesRec.ATTRIBUTE13 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE14') THEN
               v_AttributesRec.ATTRIBUTE14 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE15') THEN
               v_AttributesRec.ATTRIBUTE15 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE16') THEN
               v_AttributesRec.ATTRIBUTE16 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE17') THEN
               v_AttributesRec.ATTRIBUTE17 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE18') THEN
               v_AttributesRec.ATTRIBUTE18 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE19') THEN
               v_AttributesRec.ATTRIBUTE19 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE20') THEN
               v_AttributesRec.ATTRIBUTE20 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE21') THEN
               v_AttributesRec.ATTRIBUTE21 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE22') THEN
               v_AttributesRec.ATTRIBUTE22 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE23') THEN
               v_AttributesRec.ATTRIBUTE23 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE24') THEN
               v_AttributesRec.ATTRIBUTE24 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE25') THEN
               v_AttributesRec.ATTRIBUTE25 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE26') THEN
               v_AttributesRec.ATTRIBUTE26 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE27') THEN
               v_AttributesRec.ATTRIBUTE27 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE28') THEN
               v_AttributesRec.ATTRIBUTE28 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE29') THEN
               v_AttributesRec.ATTRIBUTE29 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE30') THEN
               v_AttributesRec.ATTRIBUTE30 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE31') THEN
               v_AttributesRec.ATTRIBUTE31 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE32') THEN
               v_AttributesRec.ATTRIBUTE32 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE33') THEN
               v_AttributesRec.ATTRIBUTE33 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE34') THEN
               v_AttributesRec.ATTRIBUTE34 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE35') THEN
               v_AttributesRec.ATTRIBUTE35 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE36') THEN
               v_AttributesRec.ATTRIBUTE36 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE37') THEN
               v_AttributesRec.ATTRIBUTE37 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE38') THEN
               v_AttributesRec.ATTRIBUTE38 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE39') THEN
               v_AttributesRec.ATTRIBUTE39 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE40') THEN
               v_AttributesRec.ATTRIBUTE40 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE41') THEN
               v_AttributesRec.ATTRIBUTE41 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE42') THEN
               v_AttributesRec.ATTRIBUTE42 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE43') THEN
               v_AttributesRec.ATTRIBUTE43 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE44') THEN
               v_AttributesRec.ATTRIBUTE44 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE45') THEN
               v_AttributesRec.ATTRIBUTE45 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE46') THEN
               v_AttributesRec.ATTRIBUTE46 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE47') THEN
               v_AttributesRec.ATTRIBUTE47 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE48') THEN
               v_AttributesRec.ATTRIBUTE48 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE49') THEN
               v_AttributesRec.ATTRIBUTE49 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE50') THEN
               v_AttributesRec.ATTRIBUTE50 := p_attributevalues(i);
            END IF;
         END LOOP;
--debug_pkg1.debug_proc ('Before Calling INSERT_DFF_DATA');
         CANON_E389_DFF_UTILITY_PKG.INSERT_DFF_DATA (p_commit              => 'S',
                                                     p_source_application  => p_source_application,
                                                     p_source_context      => p_source_context,
                                                     p_context_value       => p_context_value,
                                                     p_last_update_date    => SYSDATE,
                                                     p_last_updated_by     => p_user_id,
                                                     p_creation_date       => SYSDATE,
                                                     p_created_by          => p_user_id,
                                                     p_attributesRec       => v_AttributesRec,
                                                     x_return_status       => x_return_status,
                                                     x_msg_data            => x_msg_data);
--debug_pkg1.debug_proc ('After Calling INSERT_DFF_DATA');
      ELSIF (v_count = 1) THEN

         FOR i IN 1..p_attributeids.COUNT
         LOOP

            IF (p_attributeids(i) = 'ATTRIBUTE1') THEN
               v_AttributesRec.ATTRIBUTE1 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE2') THEN
               v_AttributesRec.ATTRIBUTE2 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE3') THEN
               v_AttributesRec.ATTRIBUTE3 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE4') THEN
               v_AttributesRec.ATTRIBUTE4 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE5') THEN
               v_AttributesRec.ATTRIBUTE5 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE6') THEN
               v_AttributesRec.ATTRIBUTE6 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE7') THEN
               v_AttributesRec.ATTRIBUTE7 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE8') THEN
               v_AttributesRec.ATTRIBUTE8 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE9') THEN
               v_AttributesRec.ATTRIBUTE9 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE10') THEN
               v_AttributesRec.ATTRIBUTE10 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE11') THEN
               v_AttributesRec.ATTRIBUTE11 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE12') THEN
               v_AttributesRec.ATTRIBUTE12 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE13') THEN
               v_AttributesRec.ATTRIBUTE13 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE14') THEN
               v_AttributesRec.ATTRIBUTE14 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE15') THEN
               v_AttributesRec.ATTRIBUTE15 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE16') THEN
               v_AttributesRec.ATTRIBUTE16 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE17') THEN
               v_AttributesRec.ATTRIBUTE17 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE18') THEN
               v_AttributesRec.ATTRIBUTE18 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE19') THEN
               v_AttributesRec.ATTRIBUTE19 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE20') THEN
               v_AttributesRec.ATTRIBUTE20 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE21') THEN
               v_AttributesRec.ATTRIBUTE21 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE22') THEN
               v_AttributesRec.ATTRIBUTE22 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE23') THEN
               v_AttributesRec.ATTRIBUTE23 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE24') THEN
               v_AttributesRec.ATTRIBUTE24 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE25') THEN
               v_AttributesRec.ATTRIBUTE25 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE26') THEN
               v_AttributesRec.ATTRIBUTE26 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE27') THEN
               v_AttributesRec.ATTRIBUTE27 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE28') THEN
               v_AttributesRec.ATTRIBUTE28 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE29') THEN
               v_AttributesRec.ATTRIBUTE29 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE30') THEN
               v_AttributesRec.ATTRIBUTE30 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE31') THEN
               v_AttributesRec.ATTRIBUTE31 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE32') THEN
               v_AttributesRec.ATTRIBUTE32 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE33') THEN
               v_AttributesRec.ATTRIBUTE33 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE34') THEN
               v_AttributesRec.ATTRIBUTE34 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE35') THEN
               v_AttributesRec.ATTRIBUTE35 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE36') THEN
               v_AttributesRec.ATTRIBUTE36 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE37') THEN
               v_AttributesRec.ATTRIBUTE37 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE38') THEN
               v_AttributesRec.ATTRIBUTE38 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE39') THEN
               v_AttributesRec.ATTRIBUTE39 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE40') THEN
               v_AttributesRec.ATTRIBUTE40 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE41') THEN
               v_AttributesRec.ATTRIBUTE41 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE42') THEN
               v_AttributesRec.ATTRIBUTE42 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE43') THEN
               v_AttributesRec.ATTRIBUTE43 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE44') THEN
               v_AttributesRec.ATTRIBUTE44 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE45') THEN
               v_AttributesRec.ATTRIBUTE45 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE46') THEN
               v_AttributesRec.ATTRIBUTE46 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE47') THEN
               v_AttributesRec.ATTRIBUTE47 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE48') THEN
               v_AttributesRec.ATTRIBUTE48 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE49') THEN
               v_AttributesRec.ATTRIBUTE49 := p_attributevalues(i);
            ELSIF (p_attributeids(i) = 'ATTRIBUTE50') THEN
               v_AttributesRec.ATTRIBUTE50 := p_attributevalues(i);
            END IF;
         END LOOP;

         CANON_E389_DFF_UTILITY_PKG.UPDATE_DFF_DATA (p_commit              => 'S',
                                                     p_source_application  => p_source_application,
                                                     p_source_context      => p_source_context,
                                                     p_context_value       => p_context_value,
                                                     p_last_update_date    => SYSDATE,
                                                     p_last_updated_by     => p_user_id,
                                                     p_attributesRec       => v_AttributesRec,
                                                     x_return_status       => x_return_status,
                                                     x_msg_data            => x_msg_data);

      ELSIF (v_count > 1) THEN

         x_return_status := 'E';
         x_msg_data      := 'Multiple Records Found For This Context Value';

      END IF;

   EXCEPTION
      WHEN OTHERS THEN
         x_return_status := 'E';
         x_msg_data      := 'Exception : OTHERS : ' || SQLERRM;

   END UPLOAD_DATA;

   PROCEDURE DISPLAY_DATA (p_source_application     IN  VARCHAR2,
                           p_source_context         IN  VARCHAR2,
                           p_context_value          IN  VARCHAR2,
                           p_attributeIds           OUT JTF_VARCHAR2_TABLE_100,
                           p_attributeValues        OUT JTF_VARCHAR2_TABLE_100) IS

      CURSOR cur_rec (p_source_context VARCHAR2)
      IS
      /* SELECT fdfcu.column_seq_num seq_number, fdfcu.application_column_name attribute_id
      FROM fnd_descr_flex_col_usage_vl@candev  fdfcu,
           fnd_flex_value_sets@candev          ffvs,
           fnd_application@candev              fa,
           fnd_lookups@candev                  fl
      WHERE fdfcu.descriptive_flexfield_name = 'Canon JSP DFF'
      AND   fdfcu.descriptive_flex_context_code = p_source_context
      AND   fa.application_short_name = 'CANON'
      AND   fdfcu.application_id = fa.application_id
      AND   ffvs.flex_value_set_id(+) = fdfcu.flex_value_set_id
      AND   fl.lookup_type = 'FLEX_DEFAULT_TYPE'
      AND   fl.enabled_flag(+) = 'Y'
      AND   SYSDATE BETWEEN nvl(fl.start_date_active(+), SYSDATE) AND nvl(fl.end_date_active(+), SYSDATE)
      AND    fdfcu.default_type = fl.lookup_code(+)
      ORDER BY column_seq_num;*/
      SELECT val.val51 seq_number,
              val.val4 attribute_id
          FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
         WHERE     cd.cd_id = val.cd_id
               AND cd_name = 'CSR_E193_JSP_DFF'
               AND val.val1 = p_source_context
	ORDER BY val.val51;
     
      v_attributeIds        JTF_VARCHAR2_TABLE_100;
      v_attributeValues     JTF_VARCHAR2_TABLE_100;
      i                     NUMBER := 0;

   BEGIN
 --debug_pkg1.debug_proc ('Inside canon_e389_sql_pkg.DISPLAY_DATA');
      p_attributeIds    := JTF_VARCHAR2_TABLE_100();
      p_attributeValues := JTF_VARCHAR2_TABLE_100();

      v_attributeIds    := JTF_VARCHAR2_TABLE_100();
      v_attributeValues := JTF_VARCHAR2_TABLE_100();

      FOR rec IN cur_rec (p_source_context)
      LOOP

         i := i + 1;

         v_attributeIds.extend(1);
         v_attributeValues.extend(1);

         v_attributeIds (i)    := rec.attribute_id;
         v_attributeValues (i) := GET_ATTR_VALUE (rec.attribute_id, p_source_application, p_source_context, p_context_value);

      END LOOP;

      p_attributeIds    := v_attributeIds;
      p_attributeValues := v_attributeValues;

   EXCEPTION
      WHEN OTHERS THEN
         p_attributeIds    := NULL;
         p_attributeValues := NULL;

   END DISPLAY_DATA;

   FUNCTION GET_ATTR_VALUE (p_attribute_id           IN  VARCHAR2,
                            p_source_application     IN  VARCHAR2,
                            p_source_context         IN  VARCHAR2,
                            p_context_value          IN  VARCHAR2) RETURN VARCHAR2 IS

      v_sql          VARCHAR2(2000) := NULL;
      v_attr_value   VARCHAR2(240)  := NULL;

   BEGIN

      v_sql := ' SELECT ' || p_attribute_id || ' FROM CANON_E193_JSP_DFF_UTIL ' || 
               ' WHERE source_application = :1 ' ||
               ' AND   source_context     = :2 ' ||
               ' AND   context_value      = :3 ';

      EXECUTE IMMEDIATE v_sql INTO v_attr_value using p_source_application, p_source_context, p_context_value ;

      RETURN (v_attr_value);

   EXCEPTION
      WHEN OTHERS THEN
         RETURN (NULL);

   END GET_ATTR_VALUE;

   PROCEDURE DISPLAY_LINK (p_document_id    IN  VARCHAR2,
                           p_170_link       OUT VARCHAR2,
                           p_link_flag      OUT VARCHAR2) IS

      l_link                       VARCHAR2 (100)  := 'X';
      l_pref_name                  VARCHAR2 (100)  := 'X';
      l_mv_document_id             NUMBER;

   BEGIN
   p_170_link:=NULL;
   p_link_flag:='E';
   
   
   SELECT a.oth_sys_url markview_doc_link
   INTO p_170_link
  FROM att_data a
  WHERE     att_doc_tp_cd = '70'
       AND att_data_tp_cd = 'TF'
       AND a.att_data_nm=p_document_id
       AND a.ezcancelflag = 0
       AND a.glbl_cmpy_cd = 'ADB';
   p_link_flag:='S';    

     /* BEGIN
         SELECT profile_option_value
         INTO   l_pref_name
         FROM   fnd_profile_options_vl@candev    fpo
               ,fnd_profile_option_values@candev fpov
         WHERE  1 = 1
         AND    fpo.profile_option_id = fpov.profile_option_id
         AND    fpo.start_date_active <= SYSDATE
         AND    NVL (fpo.end_date_active, SYSDATE) >= SYSDATE
         AND    fpo.site_enabled_flag = 'Y'
         AND    UPPER (fpo.profile_option_name) = g_csr_170system_pref_name;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            l_pref_name    := 'X';
            p_link_flag    := 'E';

      END;

      BEGIN
         SELECT profile_option_value
         INTO   l_link
         FROM   fnd_profile_options_vl@candev fpo
               ,fnd_profile_option_values@candev fpov
         WHERE  1 = 1
         AND    fpo.profile_option_id = fpov.profile_option_id
         AND    fpo.start_date_active <= SYSDATE
         AND    NVL (fpo.end_date_active, SYSDATE) >= SYSDATE
         AND    fpo.site_enabled_flag = 'Y'
         AND    UPPER (fpo.profile_option_name) = g_csr_170system_link_profile;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            l_link         := 'X';
            p_link_flag    := 'E';

      END;

      IF (l_link != 'X' AND l_pref_name != 'X') THEN

         SELECT dw.document_id
               , (SELECT mvp.DEFAULT_VALUE
                  FROM   markview.mv_preference@candev mvp
                  WHERE  mvp.preference_name LIKE l_pref_name) || l_link || dw.document_id
         INTO   l_mv_document_id, p_170_link
         FROM   markview.mvt_document_workitem_instance@candev dw
         WHERE  dw.document_id = p_document_id
         AND    ROWNUM = 1;

      END IF;

      p_link_flag    := 'S';*/

   EXCEPTION
      WHEN OTHERS
      THEN
         p_link_flag    := 'E';

   END DISPLAY_LINK;
   
/*******************************************************************************************
Procedure Name: GET_CANONBALL_ATTACH
Description: Get canonball attachments
Input Parameters: P_TICKET_ID
                  
Output Parameters: X_ATTCH_CUR
*******************************************************************************************/   

PROCEDURE GET_CANONBALL_ATTACH (P_TICKET_ID        IN     NUMBER,
                                X_ATTCH_CUR          OUT ref_cur_type)
IS
   l_dynsql         VARCHAR2 (32767);
   
BEGIN
   l_dynsql :=
   'SELECT CAST (TO_TIMESTAMP (a.ezintime, ''RRRRMMDDHH24MISSFF3'') AS DATE)
          attachment_date,
       a.att_data_desc_txt description,
       a.att_data_nm document_id,
       a.oth_sys_url markview_doc_link
  FROM att_data a, canon_e193_cs_headers hdr
 WHERE     att_doc_tp_cd = ''70''
       AND att_data_tp_cd = ''TF''
       AND att_data_grp_txt = hdr.order_number 
       AND hdr.ticket_id = '||p_ticket_id;
   
    dbms_output.put_line(l_dynsql);
    
    OPEN x_attch_cur FOR l_dynsql;

EXCEPTION
   WHEN OTHERS
   THEN
    OPEN x_attch_cur FOR
            SELECT 1
              FROM DUAL
          WHERE 1 = -1;
    canon_e193_cs_evolution_pkg.debug_msg (l_program_name   => 'GET_CANONBALL_ATTACH',
                    l_attribute1     => 'Ticket# ' || p_ticket_id,
                 l_error_msg      =>  'Unexpeceted error: ' || SQLERRM);
   
      
END GET_CANONBALL_ATTACH;   

END CANON_E389_SQLS_PKG;
/
Show Err
