#-----------------------------------------------------
MAIN=main
ICONSDIR=.
IMAGESDIR=images
#STYLE=/Users/bruel/Dropbox/Public/dev/asciidoc/stylesheets/golo-jmb.css
#STYLE=/Users/bruel/dev/asciidoctor/asciidoctor-stylesheet-factory/stylesheets/jmb.css
#ASCIIDOC=asciidoc -a icons -a iconsdir=$(ICONSDIR) -a stylesheet=$(STYLE) -a imagesdir=$(IMAGESDIR) -a data-uri
#HIGHLIGHT=coderay
#HIGHLIGHT=highlightjs
#HIGHLIGHT=prettify
HIGHLIGHT=pygments
#DOCTOR=asciidoctor -b html5 -a data-uri -a icons=font -a images=$(IMAGESDIR) -a source-highlighter=$(HIGHLIGHT)
DOCTOR=asciidoctor -b html5 -a icons=font -a images=$(IMAGESDIR) -a source-highlighter=$(HIGHLIGHT)
#BACKENDS=asciidoctor-deck.js
#DECKJS=$(BACKENDS)/templates/haml/
BACKENDS=../../lib/asciidoctor-backends
DECKJS=$(BACKENDS)/haml/
#DECK=web-2.0
DECK=swiss
#DECK=neon
#DECK=beamer
DZSLIDES=../../lib/asciidoctor-backends/slim/dzslides
EXT=adoc
PANDOC=pandoc
OUTPUT=.
DEP=definitions.txt glossaire.txt refs.txt preface.adoc introduction.asc
SOURCEFILES = ./src/java/CodingDojo/src/*.java
DOC = doc
#-----------------------------------------------------

all: $(OUTPUT)/*.html
book: full.pdf

projet2018-1.html: projet2018.adoc projet2018-1.adoc
	@echo '==> Compiling asciidoc files with Asciidoctor to generate HTML'
	$(DOCTOR) -r asciidoctor-diagram -a projet1 -a toc2 -b html5 -a numbered -a eleve -a linkcss! -o projet2018-1.html projet2018.adoc

projet2018-2.html: projet2018.adoc projet2018-2.adoc
	@echo '==> Compiling asciidoc files with Asciidoctor to generate HTML'
	$(DOCTOR) -r asciidoctor-diagram -a projet2 -a toc2 -b html5 -a numbered -a eleve -a linkcss! -o projet2018-2.html projet2018.adoc

images/%.png: images/%.plantuml
	@echo '==> Compiling plantUML files to generate PNG'
	java -jar plantuml.jar $<

images/%.svg: images/%.plantuml
	@echo '==> Compiling plantUML files to generate SVG'
	java -jar plantuml.jar -t SVG $<

pattern/%.png: pattern/%.plantuml
	@echo '==> Compiling plantUML files to generate PNG'
	java -jar plantuml.jar $<

%.html: %.$(EXT) $(DEP)
	@echo '==> Compiling asciidoc files with Asciidoctor to generate HTML'
	$(DOCTOR) -r asciidoctor-diagram -a toc2 -b html5 -a numbered -a eleve -a linkcss! $<

%.full.html: %.$(EXT) $(DEP)
	@echo '==> Compiling asciidoc files with Asciidoctor to generate HTML'
	$(DOCTOR) -a toc2 -a data-uri -b html5 -a numbered -a eleve -o $@ $<

full.pdf: full.$(EXT) $(DEP)
	@echo '==> Compiling asciidoc files with Asciidoctor to generate HTML'
	asciidoctor-pdf -a prof -a toc2 $<

#%.deckjs.html: %.$(EXT)  $(DEP)
#	@echo '==> Compiling asciidoc files to generate Deckjs'
#	$(DOCTOR) -T ../asciidoctor-deck.js/templates/haml/ -a slides -a linkcss! \
#	-a data-uri -a deckjs_theme=$(DECK) \
#	-a icons=font \
#	-a images=$(IMAGESDIR) -a prof -o $@ $<

%.deckjs.html: %.$(EXT)  $(DEP)
	@echo '==> Compiling asciidoc files to generate Deckjs'
	$(DOCTOR) -b deckjs \
	-T ../../lib/asciidoctor-deck.js/templates/haml/ -a slides \
	-a deckjs_theme=$(DECK) \
	-r asciidoctor-diagram \
	-a styledir=. \
	-a stylesheet=$(STYLE) \
	-a imagesdir=$(IMAGESDIR) \
	-a source-highlighter=$(HIGHLIGHT) \
	-a prof -o $@ $<

%.dzslides.html: %.$(EXT)
	@echo '==> Compiling asciidoc files to generate Dzslides'
	$(DOCTOR) -b dzslides \
	-T $(DZSLIDES) \
	-a slides -a dzslides \
	-r asciidoctor-diagram \
	-a styledir=. \
	-a stylesheet=$(STYLE) \
	-a imagesdir=$(IMAGESDIR) \
	-a source-highlighter=$(HIGHLIGHT) \
	-o $@ $<

%.reveal.html: %.$(EXT)  $(DEP)
	@echo '==> Compiling asciidoc files to generate reveal.js'
	bundle exec asciidoctor-revealjs -a data-uri -o $@ $<

%.xml: %.$(EXT)
	@echo '==> Compiling asciidoc files to generate DocBook'
	$(DOCTOR) -b docbook -a docbook $< -o $@

%.wiki: %.xml
	@echo '==> Compiling DocBook files with Pandoc to generate MediaWiki'
	$(PANDOC) -f docbook -t mediawiki -s $< -o $@

roadmap.html: $(MAIN).$(EXT)
	@echo '==> Compiling asciidoc files to generate standalone file for Google Drive'
	$(DOCTOR) -b html5 -a numbered -a data-uri $< -o $@

%-sujet.html: %.$(EXT) $(DEP)
	@echo '==> Compiling asciidoc files with Asciidoctor to generate HTML'
	$(DOCTOR) -a compact -a theme=compact -b html5 -a numbered -a eleve \
	-a data-uri $< -o $@

%-sujet.pdf: %.$(EXT) $(DEP)
	@echo '==> Compiling asciidoc files with Asciidoctor to generate PDF subject'
	asciidoctor-pdf  -a compact -a theme=compact  -a numbered -a eleve $< -o $@

%-sujet.pdf: %.$(EXT) $(DEP)
	@echo '==> Compiling asciidoc files with Asciidoctor to generate PDF subject'
	asciidoctor-pdf  -a compact -a theme=compact  -a numbered -a eleve $< -o $@

%-prof.pdf: %.$(EXT) $(DEP)
	@echo '==> Compiling asciidoc files with Asciidoctor to generate PDF subject'
	asciidoctor-pdf  -a compact -a theme=compact  -a numbered -a prof $< -o $@

%-prof.html: %.$(EXT) $(DEP)
	@echo '==> Compiling asciidoc files with Asciidoctor to generate HTML'
	$(DOCTOR) -a prof -a correction -a compact -a theme=compact -b html5 -a numbered \
	-a data-uri $< -o $@

deploy:
	cp main.html index.html
	git commit -am "maj du cours au fur et à mesure"
	git co gh-pages
	git co master index.html
	git push

test:
	$(DOCTOR) -a toc2 -b html5 -a numbered -a stylesheet=$(STYLE) -o wip2.html wip.asc
	$(DOCTOR) -T /Users/bruel/dev/asciidoctor-backends/haml/deckjs/ -a slides \
	-a data-uri -a deckjs_theme=$(DECK) -a icons -a iconsdir=$(ICONSDIR) \
	-a images=$(IMAGESDIR) -a prof -a stylesheet=$(STYLE) -o wip2.deckjs.html wip.asc

javadoc : $(CLASSFILES)
	javadoc -version -author -doclet org.asciidoctor.Asciidoclet -docletpath doclet/asciidoclet-1.5.0.jar -overview -d $(DOC) $(SOURCEFILES)
