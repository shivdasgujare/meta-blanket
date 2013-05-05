DESCRIPTION = "evlog logging utility"
SECTION = "devel"
LICENSE = "LGPL"
LIC_FILES_CHKSUM = "file://COPYING;md5=9b06af1a3b4b964300b585224ea2158e"

SRCREV = "e3166d42eb59d72b876f380d27f39fe20e27d8b8"
PV = "1.0+gitr${SRCPV}"
PR = "r0"

FILES_${PN}-dbg += "/var/evlog/test/.debug"
FILES_${PN}-dbg += "/usr/share/evlog/.debug"

SRC_URI = " \
  git://github.com/shivdasgujare/evlog.git;protocol=git;branch=master \
  "
S = "${WORKDIR}/git"

inherit autotools

DEPENDS = "zlib binutils"
EXTRA_OECONF = "CFLAGS=-D_GNU_SOURCE"
