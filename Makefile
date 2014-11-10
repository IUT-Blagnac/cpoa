#-----------------------------------------------------
MAIN=main
ICONSDIR=/Users/bruel/dev/images/icons
IMAGESDIR=/Users/bruel/dev/Papyrus4Education/images
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
#-----------------------------------------------------

all: $(OUTPUT)/*.html 

images/plantuml/%.png: plantuml/%.txt
	@echo '==> Compiling plantUML files to generate PNG'
	java -jar /Users/bruel/dev/asciidoc/plantuml.jar $<
        
%.html: %.$(EXT)
	@echo '==> Compiling asciidoc files with Asciidoctor to generate HTML'
	$(DOCTOR) -b html5 -a numbered $< 

%.deckjs.html: %.txt
	@echo '==> Compiling asciidoc files to generate Deckjs'
#	$(ASCIIDOC) -a slides -b deckjs -a data-uri -a deckjs_theme=$(DECK) -o $@ $< 
	$(DOCTOR) -T ../asciidoctor-backends/haml/deckjs/ -a slides -a data-uri -a deckjs_theme=$(DECK) -a icons -a iconsdir=$(ICONSDIR) -a stylesheet=$(STYLE) -a images=$(IMAGESDIR) -o $@ $<

%.reveal.html: %.txt
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