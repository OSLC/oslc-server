require 'csv'

# Read all the Programs
reader = CSV.open('../MRM-Program.CSV', 'r') 
programHeader = reader.shift
programs = Hash.new
reader.each do |row|
  programs.store(row[0], row)
end
reader.close

#Read all the Services
reader = CSV.open('../MRM-Service.CSV', 'r')
serviceHeader = reader.shift 
services = Hash.new
reader.each do |row|
  services.store(row[0], row)
end
reader.close

# create the sub-programs
programs.each do |program|
  tag = program[1][0]
  subprograms = programs.select {|key, value| key.include?(tag) && !key.eql?(tag)}
  if !subprograms.empty? then
    program[1][1] = ""
    subprograms.collect {|subprogram| subprogram[1][2]}.each do |sp|
      program[1][1] += "\"" + sp + "\"\n"
    end
    program[1][1].chomp
  end
end

#create the sub-services
services.each do |service|
  tag = service[1][0]
  subservices = services.select {|key, value| key.include?(tag) && !key.eql?(tag)}
  if !subservices.empty? then
    service[1][1] = ""
    subservices.collect {|subservice| subservice[1][3]}.each do |ss|
      service[1][1] += "\"" + ss + "\"\n"
    end
    service[1][1].chomp
  end
end

# determine the services deliveredy by the program
program_Services = Array.new
program_Services << ["Name", "Program", "Services"]
programs.each do |program|
  programServices = Array.new
  if program[1][10] then
    pgsvTags = program[1][10].split(', ')
    pgsvTags.each do |tag|
      # find the service whose program has the matching PGSV tag
      s = services.select {|key, value| value[9].eql?(tag)}   
      if !s.nil? && !s.empty? then   
        # Program Services is an explicit relationship
        program_Services << [tag, program[1][2], s[0][1][3]]
      end
    end
  end
end

#Read all the Outputs
reader = CSV.open('../MRM-Output.CSV', 'r')
outputHeader = reader.shift 
outputs = Hash.new
reader.each do |row|
  outputs.store(row[0], row)
end
reader.close

# Determine the service outputs
services.each do |service|
  serviceOutputs = Array.new # there will be only one
  if service[1][10] then # if there are any outputs
    svopTags = service[1][10].split(', ')
    svopTags.each do |tag|
      # find the output whose service has the matching SVOP tag
      o = outputs.select {|key, value| value[12].eql?(tag)}   
      if !o.nil? && !o.empty? then   
        # Service Outputs is an implicit relationship, we set both ends (although its not really necessary)
        service[1][12] = o[0][1][1] # set the output of the service to the name of the output
        outputs[o[0][0]][12] = service[1][3] # replace the SVOP tag with the corresponding service name
      end
    end
  end
end


# Nothing to do for Organizational Units - the files don't need to be converted, they can be directly imported
# There were only two relationships between Organizational Units and Services and these were done by hand in SA

#Read all the Processes
reader = CSV.open('../MRM-Process.CSV', 'r')
processHeader = reader.shift 
processes = Hash.new
reader.each do |row|
  processes.store(row[0], row)
end
reader.close


# Determine the processes that implement the service
services.each do |service|
  serviceProcesses = Array.new
  if service[1][10] then # if there are any processes
    svprTags = service[1][10].split(', ')
    svprTags.each do |tag|
      # find the process whose service has the matching SVPR tag
      p = processes.select {|key, value| value[12].eql?(tag)}   
      if !p.nil? && !p.empty? then   
        # Service Processes is an implicit relationship, we set both ends (although its not really necessary)
        serviceProcesses << p[0][1][2] # add the name of the process to the list
        # setting the one side of a 1:m implicit relationship
        processes[p[0][0]][12] = service[1][3] # replace the SVPR tag with the corresponding service name
      end
    end
    # setting the many side of an implicit relationship
    service[1][11] = ""
    serviceProcesses.each do |sp|
      # for some reason all the process names end with a space in the ReqPro CSV file
      service[1][11] += "\"" + sp.rstrip! + "\"\n"
    end
  end
end

# write the program CSV file
writer = CSV.open('../MRM-Program2.CSV', 'w')
writer << programHeader
# sort them by Tag id in descending order so that the sub-programs are created before the parent programs
programs = programs.sort {|a, b| b[0]<=>a[0]}
programs.each {|program| writer << program[1]}
writer.close

#write the service CSV file
writer = CSV.open('../MRM-Service2.CSV', 'w')
writer << serviceHeader
services = services.sort {|a, b| b[0]<=>a[0]}
services.each {|service| writer << service[1]}
writer.close

#write the output CSV file
writer = CSV.open('../MRM-Output2.CSV', 'w')
writer << outputHeader
outputs.each {|output| writer << output[1]}
writer.close

# write the Program Services CSV file
writer = CSV.open('../MRM-Program Services2.CSV', 'w')
program_Services.each {|pgsv| writer << pgsv}
writer.close


# write the Process CSV file
writer = CSV.open('../MRM-Process2.CSV', 'w')
writer << processHeader
processes.each {|p| writer << p[1]}
writer.close
