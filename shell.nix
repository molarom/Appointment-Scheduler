let
  pkgs = import <nixpkgs> {
    config = {
      allowUnfree = true;
    };
  };
  java = pkgs.jdk17.override {
    enableJavaFX = true;
  };
in
  pkgs.mkShell {
    packages = [
      java
      pkgs.jetbrains.idea-ultimate
      pkgs.mysql_jdbc
    ];
  }
