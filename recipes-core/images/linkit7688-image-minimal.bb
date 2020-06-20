# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

## default kernel size 0x300000
KERNEL_SIZE ?= "3145728"

## defalut rootfs size 0x1CB0000
ROOTFS_SIZE ?= "30081024"

IMAGE_FSTYPES += "tar jffs2 img"

EXTRA_IMAGECMD_jffs2 = "-s 0x100 -e 0x10000 -p ${ROOTFS_SIZE}"

do_image_img[depends] += " \
    virtual/kernel:do_deploy \
    linkit7688-image-minimal:do_image_jffs2 \
"

IMAGE_CMD_img() {
    dd if=/dev/zero bs=${KERNEL_SIZE} count=1 | tr "\000" "\377" > ${WORKDIR}/pad-image.bin
    dd if=${DEPLOY_DIR_IMAGE}/uImage of=${WORKDIR}/pad-image.bin conv=notrunc

    dd if=/dev/zero bs=${ROOTFS_SIZE} count=1 | tr "\000" "\377" > ${WORKDIR}/pad-rootfs.bin
    dd if=${IMGDEPLOYDIR}/${IMAGE_BASENAME}-${MACHINE}.jffs2 of=${WORKDIR}/pad-rootfs.bin conv=notrunc

    cat ${WORKDIR}/pad-image.bin ${WORKDIR}/pad-rootfs.bin > ${DEPLOY_DIR_IMAGE}/lks7688${IMAGE_VERSION_SUFFIX}.img

    cd ${DEPLOY_DIR_IMAGE}
    rm -f lks7688.img
    ln -sf lks7688${IMAGE_VERSION_SUFFIX}.img lks7688.img
}

do_clean_append() {
    WK = d.getVar('WORKDIR')
    DDI = d.getVar('DEPLOY_DIR_IMAGE')
    os.system('rm -f ' + WK + '/pad-image.bin')
    os.system('rm -f ' + WK + '/pad-rootfs.bin')
    os.system('rm -f ' + DDI + '/lks7688.img')
    os.system('rm -f ' + DDI + '/lks7688*.img') 
}

