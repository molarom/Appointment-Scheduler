let
  pkgs = import <nixpkgs> {
    config = {
      allowUnfree = true;
    };
  };
  java = pkgs.jdk17.override {enableJavaFX = true;};
in
  pkgs.mkShell {
    packages = [
      pkgs.jetbrains.idea-ultimate
      java
    ];
  }
