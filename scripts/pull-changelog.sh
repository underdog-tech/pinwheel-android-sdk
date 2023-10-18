#!/bin/bash

# This will cause the shell to exit immediately if a simple command exits with a nonzero exit value.
set -e

source ./scripts/helpers.sh

set_up_github_user

git clone git@github.com:underdog-tech/pinwheel-android-sdk.git

echo \>\> Saving CHANGELOG.md
cp pinwheel-android-sdk/CHANGELOG.md .
cat CHANGELOG.md

VERSION=$(get_version)
echo $VERSION > .tmp-version

rm -rf pinwheel-android-sdk
