#!/usr/bin/bash
#
# Create a single css file from ASCC css files   
#

tmpfile1=$(mktemp styleXXXXXXXXXX.scss)
cat ../CUSTOM_ONLINE_E307/WebContent/css/canon_e307_style.css > "$tmpfile1"

tmpfile2=$(mktemp styleXXXXXXXXXX.scss)
cat ../CUSTOM_ONLINE_E307/WebContent/css/styles.css > "$tmpfile2"

tmpfile3=$(mktemp styleXXXXXXXXXX.scss)
cat ../CUSTOM_ONLINE_E307/WebContent/css/canonE307ServiceReqCreate.css > "$tmpfile3"

sass --scss -s > WebContent/css/s21extn.css <<EOF
@import "$tmpfile1","$tmpfile2","$tmpfile3";
EOF

rm "$tmpfile1"
rm "$tmpfile2"
rm "$tmpfile3"
