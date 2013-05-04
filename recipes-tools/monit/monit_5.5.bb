DESCRIPTION = "Monitoring utility"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=8f2057d797dcf340e16719314cfd69b2"

DEPENDS = "openssl"
PR = "r2"

SRCREV = "ff336b870de547d43a26165986a966252527c662"
PV = "1.0+gitr${SRCPV}"
PR = "r0"

S = "${WORKDIR}/git"

SRC_URI = " \
	git://github.com/shivdasgujare/monit.git;protocol=git;branch=master \
	file://init"
	
#file://no-strip-in-makefile.patch \
#file://enable-include-from-etc-monit.d.patch \

INITSCRIPT_NAME = "monit"
INITSCRIPT_PARAMS = "defaults 99"

inherit autotools update-rc.d

EXTRA_OECONF = "--with-ssl-lib-dir=${STAGING_LIBDIR} --with-ssl-incl-dir=${STAGING_INCDIR} --without-pam" 
EXTRA_OECONF += "libmonit_cv_setjmp_available=yes libmonit_cv_vsnprintf_c99_conformant=yes"

do_install_append() {
	install -d ${D}${sysconfdir}/init.d/
	install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/monit
	sed -i 's:# set daemon  120:set daemon  120:' ${S}/monitrc
	sed -i 's:include /etc/monit.d/:include /${sysconfdir}/monit.d/:' ${S}/monitrc
	install -m 600 ${S}/monitrc ${D}${sysconfdir}/monitrc
	install -m 700 -d ${D}${sysconfdir}/monit.d/
}

CONFFILES_${PN} += "${sysconfdir}/monitrc"
