
f=$(echo "$1" | cut -f 1 -d '.')
echo "TRAITEMENT DE : $f"

fSupport="${f}_Support"
fSujet="${f}_Sujet"
fProfs="${f}_Profs"
fSlides="${f}_SupportSlides"
fPrint="${f}_SujetPrintable"

echo "GENERATION PDF SUJET"
asciidoctor-pdf      -a data-uri   -a icons   -a eleve   -o ./htmlGenerated/$fSujet.pdf   $f.adoc

# echo "GENERATION HTML SLIDES"
# asciidoc --backend slidy2 -a data-uri -a icons -a toc -a prof  -o htmlGenerated/$fSlides.html   $f.asc

# asciidoc   -a toc   -a data-uri   -a icons   -a eleve   -a stylesheet=stylesheets/golo-jmb.css  -a impression   -o htmlGenerated/$fPrint.html   $f.asc
