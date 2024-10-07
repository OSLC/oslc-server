str = "This is a $test/Another"

uc = false
qname =""
str.each_char {|c|
    if c =~ /[ $\/]/ then
      uc = true
      puts "got a special character: "+c
    else
      puts "got a qname character"
      if uc then 
        c.upcase!
        uc = false
      end
      qname << c
      puts c
    end
}
puts "qname is:"+qname

        