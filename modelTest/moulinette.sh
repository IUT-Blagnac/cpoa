#!/bin/bash -x
for i in $( ls -ld ./2017/* )
  do
    echo $i
    if [ -d "$i" ]
      then
        ruby noteModel.rb $i
    fi
done