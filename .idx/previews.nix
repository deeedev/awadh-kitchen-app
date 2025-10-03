{pkgs, ...}: {
  idx.previews = {
    enable = true;
    previews = {
      android = {
        manager = "android";
      };
    };
  };
}
