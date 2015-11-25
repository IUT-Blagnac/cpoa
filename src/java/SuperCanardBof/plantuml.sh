#---------------------
# Génération par javadoc de modèles plantUML
# Version 2015
#---------------------
set UML=TD1.uml
set TYPE='PNG'
set DOCLETPATH=
echo "Creating %UML%..."
rem javadoc -private -quiet -J-DdestinationFile=%UML% -J-DcreatePackages=false -J-DshowPublicMethods=true -J-DshowPublicConstructors=false -J-DshowPublicFields=true -doclet de.mallox.doclet.PlantUMLDoclet -docletpath %DOCLETPATH%\plantUmlDoclet.jar src\canard\*.java
echo "Done."

javadoc -J-DdestinationFile=%UML% -J-DcreatePackages=false -J-DshowPublicMethods=true -J-DshowPublicConstructors=false -J-DshowPublicFields=true -doclet de.mallox.doclet.PlantUMLDoclet -docletpath plantUmlDoclet.jar src/appli/*.java src/comportements/arme/*.java src/comportements/arme/impl/*.java src/personnages/*.java

set TYPE='png'
echo "Converting %UML% to $TYPE..."
java -jar plantuml.jar -config "config.cfg" -t %TYPE% %UML%
echo "Done."
