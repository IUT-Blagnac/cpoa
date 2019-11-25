#!/bin/bash -x
for i in $( ls -d ./2019/* )
  do
    echo $i
    if [ -d "$i" ]
      then
        ruby noteModel.rb $i
    fi
done