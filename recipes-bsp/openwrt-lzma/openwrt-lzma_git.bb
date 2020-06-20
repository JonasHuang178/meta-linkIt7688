DESCRIPTION = "Openwrt tool for LZMA"
SECTION = "Openwrt tools."
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "http://downloads.openwrt.org/sources/lzma-4.65.tar.bz2"

SRC_URI[md5sum] = "434e51a018b4c8ef377bf81520a53af0"
SRC_URI[sha256sum] = "dcbdb5f4843eff638e4a5e8be0e2486a3c5483df73c70823618db8e66f609ec2"

PV = "4.65"
PR = "r0"

S = "${WORKDIR}/lzma-4.65"

BBCLASSEXTEND = "native"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

do_compile() {
    cd ${S}/C/LzmaUtil
    make -C ${S}/C/LzmaUtil -f makefile.gcc
    cd ${S}/CPP/7zip/Compress/LZMA_Alone
    make -C ${S}/CPP/7zip/Compress/LZMA_Alone -f makefile.gcc
}

do_install(){
    install -d ${D}/${bindir}
    install -m 0755 ${S}/CPP/7zip/Compress/LZMA_Alone/lzma ${D}/${bindir}
    mv ${D}/${bindir}/lzma ${D}/${bindir}/openwrt-lzma
}

do_package_qa[noexec] = "1"
