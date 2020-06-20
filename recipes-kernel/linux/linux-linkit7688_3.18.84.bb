DESCRIPTION = "linux-linkit7688 for LinkIt Smart 7688"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel siteinfo
require recipes-kernel/linux/linux-yocto.inc

DEPENDS += "xz-native u-boot-mkimage-native patch-image-native openwrt-lzma-native"

LINUX_VERSION ?= "3.18.84"
SRC_URI = "https://cdn.kernel.org/pub/linux/kernel/v3.x/linux-${LINUX_VERSION}.tar.xz \
           file://openwrt_files \
	   file://defconfig"

SRC_URI += " \
file://000-keep_initrafs_the_default.patch \
file://020-ssb_update.patch \
file://021-ssb_sprom.patch \
file://025-bcma_backport.patch \
file://026-bcma-from-3.20.patch \
file://027-bcma-from-4.1.patch \
file://028-bcma-from-4.2.patch \
file://029-bcma-from-4.4.patch \
file://030-nl80211-Allow-set-network-namespace-by-fd.patch \
file://031-bcma-from-4.5.patch \
file://032-bcma-from-4.6.patch \
file://040-mtd-bcm47xxpart-backports-from-3.19.patch \
file://041-mtd-bcm47xxpart-backports-from-3.20.patch \
file://043-mtd_GD25Q128B_support_backport_from_3.19.patch \
file://050-backport_netfilter_rtcache.patch \
file://051-02-bridge-allow-setting-hash_max-multicast_router-if-in.patch \
file://060-mips_decompressor_memmove.patch \
file://070-bgmac-register-napi-before-the-device.patch \
file://071-bgmac-activate-irqs-only-if-there-is-nothing-to-poll.patch \
file://073-bgmac-Clean-warning-messages.patch \
file://074-bgmac-register-fixed-PHY-for-ARM-BCM470X-BCM5301X-ch.patch \
file://075-bgmac-allow-enabling-on-ARCH_BCM_5301X.patch \
file://076-net-phy-export-fixed_phy_register.patch \
file://077-01-bgmac-fix-descriptor-frame-start-end-definitions.patch \
file://077-02-bgmac-implement-GRO-and-use-build_skb.patch \
file://077-03-bgmac-implement-scatter-gather-support.patch \
file://077-04-bgmac-simplify-tx-ring-index-handling.patch \
file://077-05-bgmac-leave-interrupts-disabled-as-long-as-there-is-.patch \
file://077-06-bgmac-set-received-skb-headroom-to-NET_SKB_PAD.patch \
file://077-07-bgmac-simplify-rx-DMA-error-handling.patch \
file://077-08-bgmac-add-check-for-oversized-packets.patch \
file://077-09-bgmac-increase-rx-ring-size-from-511-to-512.patch \
file://077-10-bgmac-simplify-dma-init-cleanup.patch \
file://077-11-bgmac-fix-DMA-rx-corruption.patch \
file://077-12-bgmac-drop-ring-num_slots.patch \
file://078-01-bgmac-support-up-to-3-cores-devices-on-a-bus.patch \
file://078-02-bgmac-add-helper-checking-for-BCM4707-BCM53018-chip-.patch \
file://078-03-bgmac-support-Ethernet-device-on-BCM47094-SoC.patch \
file://078-04-bgmac-reset-enable-Ethernet-core-before-using-it.patch \
file://078-05-bgmac-fix-MAC-soft-reset-bit-for-corerev-4.patch \
file://080-00-fib_trie-Fix-proc-net-fib_trie-when-CONFIG_IP_MULTIP.patch \
file://080-01-fib_trie-Fix-trie-balancing-issue-if-new-node-pushes.patch \
file://080-02-fib_trie-Update-usage-stats-to-be-percpu-instead-of-.patch \
file://080-03-fib_trie-Make-leaf-and-tnode-more-uniform.patch \
file://080-04-fib_trie-Merge-tnode_free-and-leaf_free-into-node_fr.patch \
file://080-05-fib_trie-Merge-leaf-into-tnode.patch \
file://080-06-fib_trie-Optimize-fib_table_lookup-to-avoid-wasting-.patch \
file://080-07-fib_trie-Optimize-fib_find_node.patch \
file://080-08-fib_trie-Optimize-fib_table_insert.patch \
file://080-09-fib_trie-Update-meaning-of-pos-to-represent-unchecke.patch \
file://080-10-fib_trie-Use-unsigned-long-for-anything-dealing-with.patch \
file://080-11-fib_trie-Push-rcu_read_lock-unlock-to-callers.patch \
file://080-12-fib_trie-Move-resize-to-after-inflate-halve.patch \
file://080-13-fib_trie-Add-functions-should_inflate-and-should_hal.patch \
file://080-14-fib_trie-Push-assignment-of-child-to-parent-down-int.patch \
file://080-15-fib_trie-Push-tnode-flushing-down-to-inflate-halve.patch \
file://080-16-fib_trie-inflate-halve-nodes-in-a-more-RCU-friendly-.patch \
file://080-17-fib_trie-Remove-checks-for-index-tnode_child_length-.patch \
file://080-18-fib_trie-Add-tracking-value-for-suffix-length.patch \
file://080-19-fib_trie-Use-index-0ul-n-bits-instead-of-index-n-bit.patch \
file://080-20-fib_trie-Fix-RCU-bug-and-merge-similar-bits-of-infla.patch \
file://080-21-fib_trie-Fall-back-to-slen-update-on-inflate-halve-f.patch \
file://080-22-fib_trie-Add-collapse-and-should_collapse-to-resize.patch \
file://080-23-fib_trie-Use-empty_children-instead-of-counting-empt.patch \
file://080-24-fib_trie-Move-fib_find_alias-to-file-where-it-is-use.patch \
file://080-25-fib_trie-Various-clean-ups-for-handling-slen.patch \
file://081-01-pppoe-Use-workqueue-to-die-properly-when-a-PADT-is-r.patch \
file://081-02-pppoe-Lacks-DST-MAC-address-check.patch \
file://081-03-pppoe-drop-pppoe-device-in-pppoe_unbind_sock_work.patch \
file://081-06-ppp-don-t-set-sk_state-to-PPPOX_ZOMBIE-in-pppoe_disc.patch \
file://081-07-ppp-remove-PPPOX_ZOMBIE-socket-state.patch \
file://081-08-pppoe-fix-memory-corruption-in-padt-work-structure.patch \
file://082-ipv6-ip6_fragment-fix-headroom-tests-and-skb-leak.patch \
file://083-solos-pci-Increase-headroom-on-received-packets.patch \
file://090-overlayfs-fallback-to-readonly-when-full.patch \
file://092-01-spi-Check-to-see-if-the-device-is-processing-a-messa.patch \
file://092-02-spi-Pump-transfers-inside-calling-context-for-spi_sy.patch \
file://092-03-spi-Only-idle-the-message-pump-in-the-worker-kthread.patch \
file://099-module_arch_freeing_init-new-hook-for-archs-before-m.patch \
file://102-ehci_hcd_ignore_oc.patch \
file://110-jffs2-use-.rename2-and-add-RENAME_WHITEOUT-support.patch \
file://111-jffs2-add-RENAME_EXCHANGE-support.patch \
file://120-bridge_allow_receiption_on_disabled_port.patch \
file://132-mips_inline_dma_ops.patch \
file://142-mtd-bcm47xxpart-don-t-fail-because-of-bit-flips.patch \
file://180-usb-xhci-make-USB_XHCI_PLATFORM-selectable.patch \
file://200-fix_localversion.patch \
file://201-extra_optimization.patch \
file://202-reduce_module_size.patch \
file://203-kallsyms_uncompressed.patch \
file://204-module_strip.patch \
file://205-backtrace_module_info.patch \
file://210-darwin_scripts_include.patch \
file://212-byteshift_portability.patch \
file://213-x86_vdso_portability.patch \
file://214-spidev_h_portability.patch \
file://220-gc_sections.patch \
file://221-module_exports.patch \
file://230-openwrt_lzma_options.patch \
file://250-netfilter_depends.patch \
file://251-sound_kconfig.patch \
file://252-mv_cesa_depends.patch \
file://253-ssb_b43_default_on.patch \
file://254-textsearch_kconfig_hacks.patch \
file://255-lib80211_kconfig_hacks.patch \
file://256-crypto_add_kconfig_prompts.patch \
file://257-wireless_ext_kconfig_hack.patch \
file://258-netfilter_netlink_kconfig_hack.patch \
file://259-regmap_dynamic.patch \
file://260-crypto_test_dependencies.patch \
file://262-compressor_kconfig_hack.patch \
file://270-bridge_header_fix.patch \
file://300-mips_expose_boot_raw.patch \
file://301-mips_image_cmdline_hack.patch \
file://302-mips_no_branch_likely.patch \
file://304-mips_disable_fpu.patch \
file://305-mips_module_reloc.patch \
file://306-mips_mem_functions_performance.patch \
file://307-mips_highmem_offset.patch \
file://309-mips_fuse_workaround.patch \
file://310-arm_module_unresolved_weak_sym.patch \
file://320-ppc4xx_optimization.patch \
file://321-powerpc_crtsavres_prereq.patch \
file://330-MIPS-kexec-Accept-command-line-parameters-from-users.patch \
file://400-mtd-add-rootfs-split-support.patch \
file://401-mtd-add-support-for-different-partition-parser-types.patch \
file://402-mtd-use-typed-mtd-parsers-for-rootfs-and-firmware-split.patch \
file://403-mtd-hook-mtdsplit-to-Kbuild.patch \
file://404-mtd-add-more-helper-functions.patch \
file://405-mtd-old-firmware-uimage-splitter.patch \
file://406-mtd-old-rootfs-squashfs-splitter.patch \
file://410-mtd-move-forward-declaration-of-struct-mtd_info.patch \
file://411-mtd-partial_eraseblock_write.patch \
file://412-mtd-partial_eraseblock_unlock.patch \
file://420-mtd-redboot_space.patch \
file://430-mtd-add-myloader-partition-parser.patch \
file://431-mtd-bcm47xxpart-check-for-bad-blocks-when-calculatin.patch \
file://432-mtd-bcm47xxpart-detect-T_Meter-partition.patch \
file://440-block2mtd_init.patch \
file://441-block2mtd_probe.patch \
file://450-mtd-nand-allow-to-use-platform-specific-chip-fixup.patch \
file://451-mtd-nand-fix-return-code-of-nand_correct_data-function.patch \
file://460-mtd-cfi_cmdset_0002-no-erase_suspend.patch \
file://461-mtd-cfi_cmdset_0002-add-buffer-write-cmd-timeout.patch \
file://462-m25p80-mx-disable-software-protection.patch \
file://472-mtd-m25p80-add-support-for-Winbond-W25X05-flash.patch \
file://473-mtd-spi-nor-add-support-for-the-Macronix-MX25L512E-S.patch \
file://474-mtd-spi-nor-add-support-for-the-ISSI-SI25CD512-SPI-f.patch \
file://480-mtd-set-rootfs-to-be-root-dev.patch \
file://490-ubi-auto-attach-mtd-device-named-ubi-or-data-on-boot.patch \
file://491-ubi-auto-create-ubiblock-device-for-rootfs.patch \
file://492-try-auto-mounting-ubi0-rootfs-in-init-do_mounts.c.patch \
file://493-ubi-set-ROOT_DEV-to-ubiblock-rootfs-if-unset.patch \
file://494-mtd-ubi-add-EOF-marker-support.patch \
file://500-yaffs-Kbuild-integration.patch \
file://501-yaffs-add-missing-flush-arguments.patch \
file://502-yaffs-fix-compat-tags-handling.patch \
file://503-yaffs-add-tags-9bytes-mount-option.patch \
file://504-yaffs-3.16-new-fops.patch \
file://520-squashfs_update_xz_comp_opts.patch \
file://530-jffs2_make_lzma_available.patch \
file://531-debloat_lzma.patch \
file://532-jffs2_eofdetect.patch \
file://540-crypto-xz-decompression-support.patch \
file://541-ubifs-xz-decompression-support.patch \
file://550-ubifs-symlink-xattr-support.patch \
file://551-ubifs-fix-default-compression-selection.patch \
file://600-netfilter_conntrack_flush.patch \
file://610-netfilter_match_bypass_default_checks.patch \
file://611-netfilter_match_bypass_default_table.patch \
file://612-netfilter_match_reduce_memory_access.patch \
file://613-netfilter_optional_tcp_window_check.patch \
file://615-netfilter_add_xt_id_match.patch \
file://616-net_optimize_xfrm_calls.patch \
file://620-sched_esfq.patch \
file://621-sched_act_connmark.patch \
file://630-packet_socket_type.patch \
file://640-bridge_no_eap_forward.patch \
file://641-bridge_always_accept_eap.patch \
file://642-bridge_port_isolate.patch \
file://643-bridge_remove_ipv6_dependency.patch \
file://645-bridge_multicast_to_unicast.patch \
file://650-pppoe_header_pad.patch \
file://651-wireless_mesh_header.patch \
file://652-atm_header_changes.patch \
file://653-disable_netlink_trim.patch \
file://655-increase_skb_pad.patch \
file://656-skb_reduce_truesize-helper.patch \
file://657-qdisc_reduce_truesize.patch \
file://660-fq_codel_defaults.patch \
file://661-fq_codel_keep_dropped_stats.patch \
file://662-use_fq_codel_by_default.patch \
file://663-remove_pfifo_fast.patch \
file://666-Add-support-for-MAP-E-FMRs-mesh-mode.patch \
file://667-ipv6-Fixed-source-specific-default-route-handling.patch \
file://670-ipv6-allow-rejecting-with-source-address-failed-policy.patch \
file://671-net-provide-defines-for-_POLICY_FAILED-until-all-cod.patch \
file://680-NET-skip-GRO-for-foreign-MAC-addresses.patch \
file://681-NET-add-of_get_mac_address_mtd.patch \
file://700-swconfig.patch \
file://701-phy_extension.patch \
file://702-phy_add_aneg_done_function.patch \
file://703-phy-add-detach-callback-to-struct-phy_driver.patch \
file://704-phy-no-genphy-soft-reset.patch \
file://710-phy-add-mdio_register_board_info.patch \
file://720-phy_adm6996.patch \
file://721-phy_packets.patch \
file://722-phy_mvswitch.patch \
file://723-phy_ip175c.patch \
file://724-phy_ar8216.patch \
file://725-phy_rtl8306.patch \
file://726-phy_rtl8366.patch \
file://727-phy-rtl8367.patch \
file://728-phy-rtl8367b.patch \
file://729-phy-tantos.patch \
file://730-phy_b53.patch \
file://731-phy_mvswitch_3.10_compilation.patch \
file://732-phy-ar8216-led-support.patch \
file://733-phy_mvsw61xx.patch \
file://750-hostap_txpower.patch \
file://760-8139cp-fixes-from-4.3.patch \
file://773-bgmac-add-srab-switch.patch \
file://780-igb-Fix-Null-pointer-dereference-in-igb_reset_q_vect.patch \
file://785-hso-support-0af0-9300.patch \
file://810-pci_disable_common_quirks.patch \
file://811-pci_disable_usb_common_quirks.patch \
file://820-usb_add_usb_find_device_by_name.patch \
file://821-usb-dwc2-dualrole.patch \
file://830-ledtrig_morse.patch \
file://831-ledtrig_netdev.patch \
file://832-ledtrig_usbdev.patch \
file://834-ledtrig-libata.patch \
file://840-rtc7301.patch \
file://841-rtc_pt7c4338.patch \
file://861-04_spi_gpio_implement_spi_delay.patch \
file://862-gpio_spi_driver.patch \
file://863-gpiommc.patch \
file://864-gpiommc_configfs_locking.patch \
file://870-hifn795x_byteswap.patch \
file://880-gateworks_system_controller.patch \
file://890-8250_optional_sysrq.patch \
file://900-slab_maxsize.patch \
file://901-debloat_sock_diag.patch \
file://902-debloat_proc.patch \
file://903-debloat_direct_io.patch \
file://904-debloat_dma_buf.patch \
file://910-kobject_uevent.patch \
file://911-kobject_add_broadcast_uevent.patch \
file://921-use_preinit_as_init.patch \
file://922-always-create-console-node-in-initramfs.patch \
file://930-crashlog.patch \
file://940-ocf_kbuild_integration.patch \
file://941-ocf_20120127.patch \
file://960-decompress_unlzo_fix.patch \
file://970-remove-unsane-filenames-from-deps_initramfs-list.patch \
file://980-arm_openwrt_machtypes.patch \
file://990-gpio_wdt.patch \
file://995-mangle_bootargs.patch \
file://997-device_tree_cmdline.patch \
file://998-enable_wilink_platform_without_drivers.patch \
file://999-seccomp_log.patch \
file://0001-MIPS-ralink-add-verbose-pmu-info.patch \
file://0002-MIPS-ralink-add-a-helper-for-reading-the-ECO-version.patch \
file://0003-MIPS-ralink-add-rt_sysc_m32-helper.patch \
file://0004-MIPS-ralink-adds-a-bootrom-dumper-module.patch \
file://0005-MIPS-ralink-add-illegal-access-driver.patch \
file://0006-MIPS-ralink-add-missing-clk_set_rate-to-clk.c.patch \
file://0007-MIPS-ralink-add-support-for-MT7620n.patch \
file://0008-MIPS-ralink-allow-manual-memory-override.patch \
file://0009-MIPS-ralink-define-the-wmac-clock-on-mt7620.patch \
file://0010-MIPS-ralink-define-the-wmac-clock-on-rt3883.patch \
file://0011-MIPS-ralink-add-rt2880-wmac-clock.patch \
file://0012-MIPS-ralink-add-MT7621-support.patch \
file://0013-MIPS-ralink-add-MT7621-defconfig.patch \
file://0015-MIPS-ralink-cleanup-early_printk.patch \
file://0016-MIPS-ralink-add-MT7621-pcie-driver.patch \
file://0017-MIPS-use-set_mode-to-enable-disable-the-cevt-r4k-irq.patch \
file://0019-MIPS-ralink-add-pseudo-pwm-led-trigger-based-on-time.patch \
file://0021-MIPS-ralink-add-cpu-frequency-scaling.patch \
file://0022-MIPS-ralink-copy-the-commandline-from-the-devicetree.patch \
file://0023-MIPS-ralink-mt7620-fix-usb-issue-during-frequency-sc.patch \
file://0025-MIPS-ralink-allow-loading-irq-registers-from-the-dev.patch \
file://0026-MIPS-ralink-add-mt7628an-support.patch \
file://0027-serial-ralink-adds-mt7620-serial.patch \
file://0028-serial-ralink-the-core-has-a-size-of-0x100-and-not-0.patch \
file://0029-serial-of-allow-au1x00-and-rt288x-to-load-from-OF.patch \
file://0030-GPIO-add-named-gpio-exports.patch \
file://0030-pinctrl-ralink-add-pinctrl-driver.patch \
file://0031-PCI-MIPS-adds-rt2880-pci-support.patch \
file://0032-PCI-MIPS-adds-mt7620a-pcie-driver.patch \
file://0033-NET-multi-phy-support.patch \
file://0035-NET-MIPS-add-ralink-SoC-ethernet-driver.patch \
file://0037-USB-phy-add-ralink-SoC-driver.patch \
file://0038-USB-add-OHCI-EHCI-OF-binding.patch \
file://0041-mtd-fix-cfi-cmdset-0002-erase-status-check.patch \
file://0042-mtd-cfi-cmdset-0002-force-word-write.patch \
file://0043-mtd-ralink-add-mt7620-nand-driver.patch \
file://0044-mtd-add-chunked-read-io-to-m25p80.patch \
file://0045-mtd-add-mt7621-nand-support.patch \
file://0046-DT-Add-documentation-for-gpio-ralink.patch \
file://0047-GPIO-MIPS-ralink-add-gpio-driver-for-ralink-SoC.patch \
file://0048-GPIO-ralink-add-mt7621-gpio-controller.patch \
file://0049-DT-Add-documentation-for-spi-rt2880.patch \
file://0050-SPI-ralink-add-Ralink-SoC-spi-driver.patch \
file://0051-rt5350-spi-second-device.patch \
file://0052-i2c-MIPS-adds-ralink-I2C-driver.patch \
file://0053-mmc-MIPS-ralink-add-sdhci-for-mt7620a-SoC.patch \
file://0054-DMA-ralink-add-rt2880-dma-engine.patch \
file://0055-asoc-add-mt7620-support.patch \
file://0056-watchdog-add-MT7621-support.patch \
file://0057-uvc-add-iPassion-iP2970-support.patch \
file://0059-USB-fix-dwc2.patch \
file://0060-soc_type.patch \
file://0061-SPI-ralink-add-mt7621-SoC-spi-driver.patch \
file://0062-mt7621-add-ECHI-OCHI-XCHI-support.patch \
file://0063-cevt-rt3352.patch \
file://0065-fix_dts_cache_issues.patch \
file://0065-mt7628-pww.patch \
file://0100-mtd-split-remove-padding.patch \
file://0101-mtd-add-rtn56u-support.patch \
file://0103-MIPS-OWRTDTB.patch \
file://0110-fix_bootargs_handling.patch \
file://0111-i2c-MIPS-add-mt7621-I2C-driver.patch \
file://0200-linkit_bootstrap.patch \
file://0300-mt7628_fixes.patch \
file://0301-mt7688-detect.patch \
file://0302-mt762x-vendor-id.patch \
file://064-MIPS-ralink-fix-clearing-the-illegal-access-interrup.patch \
file://100-mt7621-add-cpu-feature-overrides.patch \
file://110-mt7621-add-highmem.patch \
file://500-alsa.patch \
file://999-baud_250000.patch \
file://999-cevt.patch \
file://999-disable_illacc.patch \
file://999-no-pm_poweroff.patch \
file://999-non-pci-mt7620.patch \
file://999-pci-reset.patch \
"

SRC_URI[md5sum] = "e79685de43fcf3c4ada7d4fc5230a518"
SRC_URI[sha256sum] = "07f9254afd1816d8b19fc9e573ff1a3575a2024223c3bc54171c3ef980a17bf8"

PV = "${LINUX_VERSION}"
S = "${WORKDIR}/linux-3.18.84"

COMPATIBLE_MACHINE = "linkit7688"
KERNEL_VERSION_SANITY_SKIP="1"

KERNEL_DEFCONFIG_linkit7688="defconfig"
KERNEL_DEVICETREE = "LINKIT7688.dtb"
KERNEL_CONSOLE = "console=ttyS2,57600"

do_patch_prepend() {
    # Copy OpenWRT specific file
    cp -r ${WORKDIR}/openwrt_files/files/* ${S}
    cp -r ${WORKDIR}/openwrt_files/generic/files/* ${S}
    cp ${WORKDIR}/openwrt_files/dts/* ${S}/arch/mips/boot/dts

    DIR=`pwd`
    cd ${S}
    git add .
    git commit -m "OpenWRT specific files added to compile source tree."
    cd $DIR
}

#do_install_prepend() {
do_compile_append() {
    cd ${B}
    ${OBJCOPY} -O binary -R .reginfo -R .notes -R .note -R .comment -R .mdebug -R .note.gnu.build-id -S vmlinux vmlinux.bin
    cp arch/mips/boot/dts/LINKIT7688.dtb ${B}
    patch-dtb vmlinux.bin LINKIT7688.dtb
    openwrt-lzma e vmlinux.bin -lc1 -lp2 -pb2 vmlinux.bin.lzma
    
    mkimage -A mips -O linux -T kernel -C lzma -a 0x80000000 -e 0x80000000 -n "Yocto Linux-${LINUX_VERSION}" -d vmlinux.bin.lzma uImage
}

kernel_do_deploy_append() {
    install -m 0644 ${B}/uImage ${DEPLOYDIR}/uImage-${PV}-${PR}${IMAGE_VERSION_SUFFIX}

    cd ${DEPLOYDIR}
    rm -f uImage
    ln -sf uImage-${PV}-${PR}${IMAGE_VERSION_SUFFIX} uImage
}

