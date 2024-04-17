@rem XMLRNGXL[}
@rem java -jar trang.jar -I xml -O rng ../src/s21router.xml s21router.rng

@rem RNGXL[}Java\[X
relaxer -dir:../src -dir.package -validation:relax -java.package:business.servlet.ZZZL0010.xml s21router.rng -verbose
