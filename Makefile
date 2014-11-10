#-----------------------------------------------------
MAIN=main
ICONSDIR=./images/icons
IMAGESDIR=./images
STYLE=/Users/bruel/Dropbox/Public/dev/asciidoc/stylesheets/golo-jmb.css
ASCIIDOC=asciidoc -a icons -a iconsdir=$(ICONSDIR) -a stylesheet=$(STYLE) -a imagesdir=$(IMAGESDIR) -a data-uri
#HIGHLIGHT=coderay
#HIGHLIGHT=highlightjs
#HIGHLIGHT=prettify
HIGHLIGHT=pygments
DOCTOR=asciidoctor -a icons -a iconsdir=$(ICONSDIR) -a images=$(IMAGESDIR) -a source-highlighter=$(HIGHLIGHT)
#DECK=web-2.0
DECK=swiss
#DECK=neon
#DECK=beamer
EXT=asc
PANDOC=pandoc
OUTPUT=.
DEP=definitions.txt glossaire.txt refs.txt
#-----------------------------------------------------

all: $(OUTPUT)/*.html

images/%.png: images/%.plantuml
	@echo '==> Compiling plantUML files to generate PNG'
	java -jar plantuml.jar $<

%.html: %.$(EXT) $(DEP)
	@echo '==> Compiling asciidoc files with Asciidoctor to generate HTML'
	$(DOCTOR) -a toc2 -b html5 -a numbered -a data-uri $<

%.deckjs.html: %.$(EXT)  $(DEP)
	@echo '==> Compiling asciidoc files to generate Deckjs'
#	$(ASCIIDOC) -a slides -b deckjs -a data-uri -a deckjs_theme=$(DECK) -o $@ $<
	$(DOCTOR) -T ../asciidoctor-backends/haml/deckjs/ -a slides -a data-uri -a deckjs_theme=$(DECK) -a icons -a iconsdir=$(ICONSDIR) -a stylesheet=$(STYLE) -a images=$(IMAGESDIR) -o $@ $<

%.reveal.html: %.$(EXT)  $(DEP)
	@echo '==> Compiling asciidoc files to generate reveal.js'
	$(DOCTOR) -T ../asciidoctor-backends/haml/reveal/ -a slides -a data-uri -a deckjs_theme=$(DECK) -a icons -a iconsdir=$(ICONSDIR) -a stylesheet=$(STYLE) -a images=$(IMAGESDIR) -o $@ $<

%.xml: %.$(EXT)
	@echo '==> Compiling asciidoc files to generate DocBook'
	$(DOCTOR) -b docbook -a docbook $< -o $@

%.wiki: %.xml
	@echo '==> Compiling DocBook files with Pandoc to generate MediaWiki'
	$(PANDOC) -f docbook -t mediawiki -s $< -o $@

roadmap.html: $(MAIN).$(EXT)
	@echo '==> Compiling asciidoc files to generate standalone file for Google Drive'
	$(DOCTOR) -b html5 -a numbered -a data-uri $< -o $@

TD1-sujet.html: TD1.$(EXT) $(DEP)
	@echo '==> Compiling asciidoc files with Asciidoctor to generate HTML'
	$(DOCTOR) -a compact -a theme=compact -b html5 -a numbered -a data-uri -o TD1-sujet.html TD1.asc
