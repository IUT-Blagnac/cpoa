#!/bin/sh
UML='model.uml'
TYPE='PNG'
MAINPATH='/Users/bruel/localdev/cpoa'
DOCLETPATH=$MAINPATH/doclet
PUMLPATH=$MAINPATH/util
echo "Creating $UML..."
echo $DOCLETPATH

javadoc \
-private \
-quiet \
-J-DdestinationFile=$UML \
-J-DcreatePackages=false \
-J-DshowPublicMethods=true \
-J-DshowPublicConstructors=false \
-J-DshowPublicFields=true \
-doclet de.mallox.doclet.PlantUMLDoclet -docletpath $DOCLETPATH/plantUmlDoclet.jar \
-sourcepath . src/Canard.java src/Colvert.java

echo "Done creating plantUML model"

TYPE='png'
echo "Converting $UML to $TYPE..."
java -jar $PUMLPATH/plantuml.jar \
  -config $PUMLPATH/config.cfg \
  -t $TYPE $UML
echo "Done generating PNG from model"
