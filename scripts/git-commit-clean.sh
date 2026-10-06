#!/usr/bin/env bash
# Create a commit WITHOUT Cursor Co-authored-by trailer, then optionally push.
# Usage:
#   ./scripts/git-commit-clean.sh "Commit message"
#   ./scripts/git-commit-clean.sh "Commit message" --push

set -euo pipefail

MSG="${1:-}"
PUSH="${2:-}"

if [[ -z "$MSG" ]]; then
  echo "Usage: $0 \"commit message\" [--push]"
  exit 1
fi

if echo "$MSG" | grep -qi 'co-authored-by'; then
  echo "Refusing message that already contains Co-authored-by"
  exit 1
fi

cd "$(git rev-parse --show-toplevel)"
git add -A

if git diff --cached --quiet; then
  echo "Nothing to commit"
  exit 0
fi

TREE=$(git write-tree)
PARENT=$(git rev-parse HEAD)
NEW=$(git commit-tree "$TREE" -p "$PARENT" -m "$MSG")
git reset --hard "$NEW"

BODY=$(git log -1 --format='%B')
if echo "$BODY" | grep -qi 'Co-authored-by:.*Cursor\|cursoragent@cursor.com'; then
  echo "ERROR: Cursor co-author still present — aborting"
  exit 1
fi

echo "Created commit $(git rev-parse --short HEAD) without Cursor co-author"

if [[ "$PUSH" == "--push" ]]; then
  git push origin HEAD
  echo "Pushed to origin"
fi
