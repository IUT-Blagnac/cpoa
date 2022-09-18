@echo off
set su=%1%.html
set fsu=htmlGenerated\%su%
echo %su%
echo %fsu%
%fsu%

set pr=%1%_Support.html
set fpr=htmlGenerated\%pr%
%fpr%
