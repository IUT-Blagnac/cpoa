def calcule(a, b, c)
  return a*b+c
end

$a = calcule(6, 3, 2)
$b = calcule('6', 3, ', the number of the beast')

puts $a.to_s
puts $b.to_s
