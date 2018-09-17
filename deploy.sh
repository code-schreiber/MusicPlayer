#!/bin/bash

EXPECTED_TRAVIS_REPO_SLUG="code-schreiber/MusicPlayer"
EXPECTED_TRAVIS_BRANCH="master"

# Script should bail on first error
set -e

echo "Running deploy.sh"
echo "deploy.sh: TRAVIS_BRANCH: $TRAVIS_BRANCH"
echo "deploy.sh: TRAVIS_REPO_SLUG: $TRAVIS_REPO_SLUG"
echo "deploy.sh: TRAVIS_TAG: $TRAVIS_TAG"
echo "deploy.sh: TRAVIS_PULL_REQUEST: $TRAVIS_PULL_REQUEST"

echo "deploy.sh: Running gradle build"
./gradlew build
echo "deploy.sh: app/build/outputs/apk/release now contains:"
ls -l app/build/outputs/apk/release

if [ "$TRAVIS_REPO_SLUG" != "$EXPECTED_TRAVIS_REPO_SLUG" ]; then
  echo "deploy.sh: Skipping deployment: wrong repository. Expected '$EXPECTED_TRAVIS_REPO_SLUG' but was '$TRAVIS_REPO_SLUG'."
elif [ "$TRAVIS_PULL_REQUEST" != "false" ]; then
  echo "deploy.sh: Skipping deployment: was pull request."
elif [ "$TRAVIS_BRANCH" != "$EXPECTED_TRAVIS_BRANCH" ]; then
  echo "deploy.sh: Skipping deployment: wrong branch. Expected '$EXPECTED_TRAVIS_BRANCH' but was '$TRAVIS_BRANCH'."
else
  echo "Thank you, come again."
  exit $?
fi
echo "Exiting deploy.sh, skipping deployment"
