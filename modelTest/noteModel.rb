# -*- coding: utf-8 -*-
# usage: ruby noteModel.rb [directory] [file]

MODEL_NAME = ARGV[1] ? ARGV[1] : "TP1.uml"
NOTES_FILE = "notes2018.csv"

studentDirectory = ARGV[0] ? ARGV[0] : "."
studentModelFileName = studentDirectory+"/"+MODEL_NAME
puts "Checking " + studentModelFileName

$assertFileContains = [
 /@startuml/m,
 /@enduml/m,
 /abstract Personnage/m,
 /Personnage\s+--> ".*" ComportementArme/m,
 /interface\s+ComportementArme/m,
 /ComportementArme\s+<\|\.\./m,
]


notestr = ""
# test file
notestr += (File.exists?(studentModelFileName)) ? '.' : 'F'

# test file contents
fileContent = File.read(studentModelFileName)

$assertFileContains.each {|re|
 notestr += (fileContent =~ re) ? '.' : 'F'
}

# status
result = notestr.gsub('F','').size.to_s+'/'+notestr.size.to_s+";\n"
bilan = ARGV[0]+';'+notestr+';'+result
print notestr
print result

destfile = File.new(NOTES_FILE,"a")
destfile.write(bilan)
destfile.close()
