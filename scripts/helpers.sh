#!/bin/bash

# This will cause the shell to exit immediately if a simple command exits with a nonzero exit value.
set -e

function get_alpha_val () {
  # The following logic allows us to run the same job in circle regardless of branch
  # If IS_ALPHA is not already set...
  if [ -z "$IS_ALPHA" ]; then
    # If current branch is master...
    current_branch=$(git rev-parse --abbrev-ref HEAD)
    if [ "$current_branch" != "master" ]; then
      # Set alpha to true. This is relevant for setting alpha by default on non-master branches in our CI.
      IS_ALPHA=true
    else
      IS_ALPHA=false
    fi
  fi

  echo $IS_ALPHA
}

function get_repo_owner () {
  echo "underdog-tech"
}

function get_repo_name () {
  echo "pinwheel-ios-sdk"
}

function get_version () {
  echo $(grep -oP 'versionName "\K[^"]+' "pinwheel-android-sdk/app/build.gradle")
}

function get_github_write_token () {
  echo "$GITHUB_TOKEN_CTX"
}

function set_up_github_user () {
  git config user.email "pinwheel-it@pinwheelapi.com"
  git config user.name "pinwheel-it-svc"

  if ! git remote | grep -q "authenticated"; then
    git remote add authenticated https://pinwheel-it-svc:$(get_github_write_token)@github.com/underdog-tech/pinwheel-ios-sdk.git
  fi
}
