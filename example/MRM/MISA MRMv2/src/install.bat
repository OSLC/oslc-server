REM This batch file copies various MRM files to their locations in System Architect
REM It is assumed that an MRM instance has been created from a database backup file
REM It is also assumed that the current directory is the directory containing this script
REM Everything else needed to install an MRM instance is in the database backup file
echo on

REM Copy the SA query report files
copy "reports\MRM Reports.RPT" "C:\Program Files (x86)\IBM\Rational\System Architect Suite\System Architect\Reports"

REM Copy the SA Publisher configuration templates
copy "reports\SA Publisher\Templates\*.xml" "C:\Program Files (x86)\IBM\Rational\System Architect Suite\SA Publisher\Templates"

REM Install the RPE reports for the MRM work products
REM Users create their own document specification files (.dsx)
copy "Work Products\*.dta" "C:\Program Files (x86)\IBM\Rational\System Architect Suite\System Architect\RPE Templates"

REM Install the SA/XT MRM configuration and questions reports if needed
if not exist "C:\Program Files (x86)\IBM\Rational\System Architect Suite\SAXT" goto endif1
	copy "reports\SAXT Publisher\SAXT Pagefiles\Municipal Reference Model.xml" "C:\Program Files (x86)\IBM\Rational\System Architect Suite\SAXT\PageFiles
	copy "reports\SAXT Publisher\Reports\MRM XT Questions Reports.RPT" "C:\Program Files (x86)\IBM\Rational\System Architect Suite\System Architect\SAXT Publisher\Reports"
:endif1

REM ??Install the default browser panes view

