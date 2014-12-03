# -*- coding: utf-8 -*-
# usage: ruby noteModel.rb [directory]

MODEL_NAME = "model.uml"
NOTES_FILE = "notes.csv"

studentDirectory = ARGV[0] ? ARGV[0] : "."
studentModelFileName = studentDirectory+"/"+MODEL_NAME
puts "Checking " + studentModelFileName

$assertFileContains = [
 /@startuml/m,
 /@enduml/m,
 /abstract Canard/m,
 /Canard\s+--> ".*" ComportementCancan/m,
 /Canard\s+--> ".*" ComportementVol/m,
 /interface\s+ComportementCancan/m,
 /interface\s+ComportementVol/m,
 /ComportementCancan\s+<\|\.\./m,
 /ComportementVol\s+<\|\.\./m
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
bilan = notestr+';'+result
print result

destfile = File.new(NOTES_FILE,"a")
destfile.write(bilan)
destfile.close()
