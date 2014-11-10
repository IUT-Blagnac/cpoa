#---------------------------------------------
# Makefile that automatically do the job when files are modified
# need the following command:
#
# gem install guard (or bundle install)
# guard start
#
#---------------------------------------------
require 'asciidoctor'
require 'erb'

guard 'shell' do
  watch(/.*\.asc$/) {|m|
    doc = Asciidoctor.load_file(m[0])
    p '-----------------------------------------'
    p 'rendering: ' << doc.doctitle
    Asciidoctor.render_file(m[0], {:in_place => true, :safe => :unsafe})}
#    :attributes => {
#'stylesheet' => '/Users/bruel/Dropbox/Public/dev/asciidoc/stylesheets/golo-jmb.css',
#'icons' => true, 
#'iconsdir' => '/Users/bruel/dev/images/icons',
#'imagesdir' => '/Users/bruel/dev/Papyrus4Education/images',
#'data-uri' => false,
#'numbered' => true,
#'source-highlighter' => 'pygments'
#}
end

#---------------------------------------------
# refresh Chrome browser
#---------------------------------------------
guard 'livereload' do
  watch(%r{^.+\.(css|js|html)$})
end


