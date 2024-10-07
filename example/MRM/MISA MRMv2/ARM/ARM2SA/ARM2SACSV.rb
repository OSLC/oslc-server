require 'csv'
# Convert the ARM Spreadsheet into CSV files that can be imported into SA
# See: evernote:///view/1483689/s13/614c3f79-25d2-4307-b507-6edb78bf2b09/614c3f79-25d2-4307-b507-6edb78bf2b09/

# Preconditions: 
# 1. This is specific for Authoritative Reference Model Content v16.0.xlsx, updates to the ARM may require changes
# 2. The worksheets are splt into separate .CSV files for input into this program using the SaveWorksheetsAsCSV() macro.
# All other changes are done in this program.

# Postconditions:
# 1. The *.CSV files are converted and stored as *2.CSV files that can be imported into SA
# 2. Some *2.CSV files are created from columns in a CSV file, so there won't be a corresponding *.CSV file - outputs and outcomes
# 3. Some columns that should be moved to another CSV file are left and will be ignored on import into SA

# Open Issues:
# [ ] 1. Use the level column to set sub-organization units, sub-programs and sub-services
# [√] 2. Rename Program Outcome to <outcome level-indicator name> for <target group name> needing <need name>
#    and create a separate instnace for each target group

# Notes:
# 1. SA definition names must be less that 80 characters
# 2. Columns of a CSV file are indexed starting at 0 for column A - e.g., the first column in a CSV row is row[0]
# 3. Multi-valued columns are multi-dimensional arrays - e.g., row[1][3] would be the 4th element in the second column (B) of the current row
# 4. Expecting Windows Comma Separated rather than Comman Separated Values, so have to specify the field and record separators
# 5. Load all the *2.CSV files - these are the ones that have been converted to be SA compliant

parents = Array.new(10)
level = 1


# Organization Units
reader = CSV.open('Organization Units.CSV', 'r', ?,, ?\r) 
writer = CSV.open('Organization Units2.CSV', 'w')
header = reader.shift # first row is the header
header [0] = "Parent" # rename Organization Level
header[1] = "Name" # rename "Organization Unit Name"
header[2] = "Description" # rename "Organization Unit Description"
writer << header
reader.each do |row|
  l = Integer(row[0])
  parents[l] = row[1]
  row[0] = parents[l-1]
  if row[1] != nil then writer << row end
end
reader.close
writer.close


# Programs
reader = CSV.open('Programs.CSV', 'r', ?,, ?\r) 
programs = CSV.open('Programs2.CSV', 'w')
outcomes = CSV.open('Outcomes2.CSV', 'w')
targetGroups = CSV.open('Target Groups2.CSV', 'w')
programHeader = reader.shift # first row is the header
programHeader[0] = "Parent" # rename "Program Level"
programHeader[1] = "Name" # rename "Program"
programHeader[2] = "Description" # rename "Program Description"
programHeader[5] = "Needs Category" # rename "Need Category"
programHeader[7] = "Outcomes" # rename "Program Outcome"
programs << programHeader

outcomeHeader = ["Name", "Actualized Results", "Target Group"]
outcomes << outcomeHeader

targetGroupHeader = ["Name"]
targetGroups << targetGroupHeader

# output the programs, outcomes and target groups CSV files
reader.each do |row|
  if row[1] != nil then # skip unnamed programs if any 
    outcomeNames = row[7]
    newOutcomeNames = ""
    if outcomeNames != nil then
      
      # output the Outcomes with the synthesized names
      outcomeNames.each("\r") do |outcomeName|
        outcomeName = outcomeName.chomp
        targetGroupNames = row[6] != nil ? row[6] : "" 
        targetGroupNames.each("\r") do |targetGroup|
          targetGroup.chomp!
          # create a unique outcome name using outcome, target group and need
          # since the need isn't given, use the program name
          newOutcomeName = outcomeName + " for " + targetGroup + " needing " + row[1].chomp
          if newOutcomeNames == "" then
            newOutcomeNames = newOutcomeName
          else
            newOutcomeNames = newOutcomeNames + "\r" + newOutcomeName
          end
          goal ='Definition:"BMM Goal":"'+row[4]+'"'
          outcomes << [newOutcomeName, goal, targetGroup]
        end
      end
    end
    l = Integer(row[0])
    parents[l] = row[1]
    row[0] = parents[l-1]
    row[7] = newOutcomeNames
    programs << row 
    
    # output the Target Groups - may contain lots of duplicates, but that's no problem
    targetGroupNames = row[6] != nil ? row[6] : "" 
    targetGroupNames.each("\r") do |targetGroup|
      targetGroup.chomp!
      targetGroups << [targetGroup]
    end
  end
end
reader.close
programs.close
outcomes.close
targetGroups.close

# Public Services
reader = CSV.open('Public Services.CSV', 'r', ?,, ?\r) 
services = CSV.open('Public Services2.CSV', 'w')
outputs = CSV.open('Public Outputs2.CSV', 'w')
accountabilities = CSV.open('Public Service Accountabilities2.CSV', 'w')
directClientServiceValues = CSV.open('Direct Client Public Service Values2.CSV', 'w')
beneficialClientServiceValues = CSV.open('Beneficial Client Public Service Values2.CSV', 'w')
processes = CSV.open('Processes2.CSV', 'w')

serviceHeader = reader.shift # first row is the header
serviceHeader[3] = "Parent" # rename "Service Level"
serviceHeader[4] = "Name" # rename "Service Name"
serviceHeader[5] = "Aliases" # rename "Alias Service Name"
serviceHeader[6] = "Description" # rename "Service Description"
serviceHeader[11] = "Needs Category" # rename "Direct ClientNeed Category"
serviceHeader[16] = "ProcessesXXX" # do the processes in a separate CSV file
services << serviceHeader

serviceValuesHeader = ["Name", "Output", "Client Type", "Client"]
directClientServiceValues << serviceValuesHeader
beneficialClientServiceValues << serviceValuesHeader

outputHeader = ["Name", "Output Type"]
outputs << outputHeader

accountabilitiesHeader = ["Name", "Owner", "Services"]
accountabilities << accountabilitiesHeader
sa=1

processesHeader = ["Name", "Service", "Sub-Processes"]
processes << processesHeader

# output the services, serviceValues and target groups CSV files
reader.each do |row|
  if row[4] != nil then # skip unnamed services if any 
    # use the Service Level to determine the sub-services, and ignore the Sub-Service(s) column
    l = Integer(row[3])
    parents[l] = row[4].chomp
    row[3] = parents[l-1]
    
    # fix the Aliases, they are not properly quoted in the CSV file for SA to be able to load them
    # Saving the Excel spreadsheet as a CSV file puts multi-valued celles as \r-separated text
    # SA needs these to be quoted strings, within a single quoted string (with the internal quoates escaped with "")
    # Ruby handles this properly if the multi-valued cell is an array of quoted strings, not a single \r-separated string
    if row[5] != nil then
      aliases = []
      row[5].each("\r") do |aliasName|
        aliasName.chomp!
        aliases << "Definition:"+'"MRM Service":'+"\""+aliasName+"\"\r"
      end
      aliases[-1] = aliases[-1].chomp # remove the last extra \r
    end
    row[5] = aliases
    services << row 
    
    
    # do the outputs and their output types
    if row[7] != nil then
      outputs << [row[7], row[8]]
    end
    
    # do the service accountabilities using madeup names
    saName = "Public SA"+sa.to_s
    sa = sa+1
    accountabilities << [saName, row[0], row[4]] unless row[0].nil?
    
    # do the direct client service values
    if row[10] != nil then 
      row[10].each("\r") do |serviceValue|
        directClientServiceValues << [serviceValue.chomp, row[7], "Direct", row[9]]
      end
    end

    # do the beneficial client service values
    if row[13] != nil then
      row[13].each("\r") do |serviceValue|
        beneficialClientServiceValues << [serviceValue.chomp, row[7], "Beneficial", row[12]]
      end
    end
    
    # do the processes - they are not properly quoted in the CSV file to be able to be loaded into SA
    if row[16] != nil then
      serviceProcess = nil
      subProcesses = []
      row[16].each("\r") do |process|
        if process.data.start_with?("-") then
          subProcesses << '"'+process[2..-2]+'"'+"\r"
        else
          if serviceProcess != nil then
            processes << [serviceProcess, row[4],  subProcesses]
            subProcesses = []
          end
          serviceProcess = process.chomp
        end
      end
    end
  end #if service has a name
end
reader.close
services.close
directClientServiceValues.close
beneficialClientServiceValues.close
outputs.close
accountabilities.close


# Enabling Services
reader = CSV.open('Enabling Services.CSV', 'r', ?,, ?\r) 
services = CSV.open('Enabling Services2.CSV', 'w')
outputs = CSV.open('Enabling Outputs2.CSV', 'w')
accountabilities = CSV.open('Enabling Service Accountabilities2.CSV', 'w')
directClientServiceValues = CSV.open('Direct Client Enabling Service Values2.CSV', 'w')
beneficialClientServiceValues = CSV.open('Beneficial Client Enabling Service Values2.CSV', 'w')

# We leave the columns that don't correspond to properties, they will be ignored
# The below renames some of the columns to correspond to the SA definition property names
serviceHeader = reader.shift # first row is the header
serviceHeader[0] = "Parent" # rename "Service Level"
serviceHeader[1] = "Name" # rename "Service Name"
serviceHeader[2] = "Aliases" # rename "Alias Service Name"
serviceHeader[3] = "Description" # rename "Service Description"
serviceHeader[13] = "Owner" # rename "Accountable Organization Unit"
serviceHeader[15] = "Needs Category" # rename "Direct ClientNeed Category"
serviceHeader[16] = "ProcessesXXX" # do the processes in a separate CSV file
services << serviceHeader

serviceValuesHeader = ["Name", "Output", "Client Type", "Client"]
directClientServiceValues << serviceValuesHeader
beneficialClientServiceValues << serviceValuesHeader

outputHeader = ["Name", "Output Type"]
outputs << outputHeader

accountabilitiesHeader = ["Name", "Owner", "Services"]
accountabilities << accountabilitiesHeader
sa=1

# output the services, serviceValuess and target groups CSV files
reader.each do |row|
  if row[4] != nil then # skip unnamed services if any 
    # use the Service Level to determine the sub-services, and ignore the Sub-Service(s) column
    l = Integer(row[0])
    parents[l] = row[1]
    row[0] = parents[l-1]

    # Aliases
    if row[2] != nil then
      aliases = []
      row[5].each("\r") do |aliasName|
        aliasName.chomp!
        aliases << "Definition:"+'"MRM Service":'+"\""+aliasName+"\"\r"
      end
      aliases[-1] = aliases[-1].chomp # remove the last extra \r
    end
    row[2] = aliases
    services << row # this outputs the whole row, with only a few of the columns transformed as in the code above
    
    # do the outputs and their output types
    if row[5] != nil then
      outputs << [row[5], row[6]]
    end
    
    # do the service accountabilities using madeup names
    saName = "Enabling SA"+sa.to_s
    sa = sa+1
    accountabilities << [saName, row[13], row[1]] unless row[13].nil?
    
    # do the direct client service values
    # serviceValuesHeader = ["Name", "Output", "Client Type", "Client"]
    if row[8] != nil then 
      row[8].each("\r") do |serviceValue|
        directClientServiceValues << [serviceValue.chomp, row[5], "Direct", row[7]]
      end
    end

    # do the beneficial client service values
    if row[11] != nil then
      row[11].each("\r") do |serviceValue|
        beneficialClientServiceValues << [serviceValue.chomp, row[5], "Beneficial", row[10]]
      end
    end
    
    # do the processes - they are not properly quoted in the CSV file to be able to be loaded into SA
    if row[16] != nil then
      serviceProcess = nil
      subProcesses = []
      row[16].each("\r") do |process|
        if process.data.start_with?("-") then
          subProcesses << '"'+process[2..-2]+'"'+"\r"
        else
          if serviceProcess != nil then
            processes << [serviceProcess, row[1], subProcesses]
            subProcesses = []
          end
          serviceProcess = process.chomp
        end
      end
    end
  end #if service has a name
end
reader.close
services.close
directClientServiceValues.close
beneficialClientServiceValues.close
outputs.close
accountabilities.close
processes.close


