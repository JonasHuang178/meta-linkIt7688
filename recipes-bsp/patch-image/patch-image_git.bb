DESCRIPTION = "Openwrt tool for pathcing an image with DTB file."
SECTION = "Openwrt tools."
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "git://github.com/openwrt/archive.git;branch=master;protocol=http;subpath=tools/patch-image/src"
SRCREV = "af7e7ab684d5dd89a3072ad7b29a81a2669ef54f"
PV = "1.0+${SRCREV}"
PR = "r0"

S = "${WORKDIR}/src"

BBCLASSEXTEND = "native nativesdk"

#FILES_${PN}_class-native="${D}/${bindir}/*"

do_compile() {
    ${CC} patch-dtb.c ${LDFLAGS} -o patch-dtb
}

do_install(){
    install -d ${D}/${bindir}
    install -m 0755 ${S}/patch-dtb ${D}/${bindir}
}

