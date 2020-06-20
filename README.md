# meta-linkit7688
meta-linkit7688 for MediaTek LinkIt™ Smart 7688 

You can use gmacario/build-yocto(https://hub.docker.com/r/gmacario/build-yocto) docker image to build yocto
```shell
$ docker pull gmacario/build-yocto
$ docker run -it gmacario/build-yocto /bin/bash
```

How to build minimal image for LinkIt™ Smart 7688
1. Clone yocto 
```shell
build@7bf92f2b7dd0:~$ git clone https://git.yoctoproject.org/git/poky -b thud
```

2. Clone meta-linkit7688
```shell
build@7bf92f2b7dd0:~$ cd poky
build@7bf92f2b7dd0:~/poky$
build@7bf92f2b7dd0:~/poky$ git clone https://github.com/JonasHuang178/meta-linkit7688.git -b thud
```

3. Run oe-init-build-env
```shell
build@7bf92f2b7dd0:~/poky$ . oe-init-build-env
```

4. Add meta-linkit7688 layer to $BUILDDIR/conf/bblayer.conf
```diff
@@ -9,4 +9,5 @@ BBLAYERS ?= " \
   /home/build/poky/meta \
   /home/build/poky/meta-poky \
   /home/build/poky/meta-yocto-bsp \
+  /home/build/poky/meta-linkit7688 \
   "
```

5. Modify MACHINE and DISTRO in $BUILDDIR/conf/local.conf
```shell
MACHINE ??= "linkit7688" 
DISTRO ?= "linkit7688-tiny"
```

6. build image
```shell
build@7bf92f2b7dd0:~/poky/build$ bitbake linkit7688-image-minimal
```

You can get a image 
```shell
build@7bf92f2b7dd0:~/poky/build$ ls $BUILDDIR/tmp/deploy/images/linkit7688/lks7688.img 
/home/build/poky/build/tmp/deploy/images/linkit7688/lks7688.img
```

