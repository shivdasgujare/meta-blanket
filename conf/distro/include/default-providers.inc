PREFERRED_PROVIDER_udev = "${@base_contains('DISTRO_FEATURES','systemd','systemd','udev',d)}"
