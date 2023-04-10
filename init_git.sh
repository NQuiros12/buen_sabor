#!/bin/bash

if [ "$(git rev-parse --is-inside-work-tree)" ]
then
  echo "Already in a git repo"
else
  git init
fi

# Uncomment the following lines if you want to configure your git user name and email
git config --global user.name $1 #"NQuiros12"
git config --global user.email $2 #"nicolasquiros12@gmail.com"

