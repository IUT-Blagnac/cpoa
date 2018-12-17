#!/bin/sh
UML='model.uml'
DOCLETPATH='/Users/bruel/dev/teaching/dut/cpoa/doclet'
PLANTUMLPATH='/Users/bruel/dev/teaching/dut/cpoa/'
echo "Creating $UML..."
javadoc \
-private \
-quiet \
-J-DdestinationFile=$UML \
-J-DcreatePackages=false \
-J-DshowPublicMethods=true \
-J-DshowPublicConstructors=false \
-J-DshowPublicFields=true \
-doclet de.mallox.doclet.PlantUMLDoclet -docletpath $DOCLETPATH/plantUmlDoclet.jar \
-sourcepath src src/**
echo "Done."

TYPE='png'
echo "Converting $UML to $TYPE..."
java -jar $PLANTUMLPATH/plantuml.jar \
  -config "./config.cfg" \
    -t $TYPE $UML
echo "Done."
