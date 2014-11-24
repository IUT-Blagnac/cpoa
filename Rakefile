require 'asciidoctor'
#require 'cucumber/rake/task'
#require 'capybara'

#task default: :features

#$LOAD_PATH.unshift File.expand_path(File.join(File.dirname(__FILE__), 'test'))
require 'rake/testtask'

Rake::TestTask.new do |t|
  t.libs << "test"
  t.test_files = FileList['test/test*.rb']
  t.verbose = true
  t.warning = true
end

task :default => 'dev:render_html'
task 'dev:render_html' => 'dev:test_images'
task 'dev:render_html' => ['dev:test_images']

namespace :dev do
  MAIN = 'main.asc'

  desc "Rendering HTML files"
  task :render_html do
    p '[JMB:] Rendering HTML for ' << MAIN
    Asciidoctor.render_file(MAIN, {:in_place => true, :safe => :unsafe})
  end

  #---------------------------------------------------
  # Checkin Images
  #---------------------------------------------------
  desc "Controlling if images are OK"
  task :test_images do
    p '[JMB:] Testing images for all HTML files'
    doc = Asciidoctor.load_file(MAIN)

    re = Regexp.new("\.html$") # asciidoc source file

    dir = Dir.new('.')
    dir.each  {|fn|
        if ( fn =~ re ) then
            print "asciidoc source : " + fn + "\n"
            paths = []
            # find all image src field in the HTML files
            File.open(fn) { |f|
              content = f.read
              paths = content.scan(/<img src="([^"]*)"/)
            }
            imagesDir='images'
            # test that path is a file
            paths.flatten.each {|path|
              print (File.file?(path) ? " OK " : " NOK ") + path + "\n"
            }
        end
    }
  end
end
