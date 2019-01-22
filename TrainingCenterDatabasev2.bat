rem BAT file to generate combined JAXB files
rem DEST_DIR must already exist

set XJC=C:\Java\JDK\jdk1.6.0_20\bin\xjc.exe
set XSD_DIR=C:\eclipseWorkspaces\Work\net.kenevans.trainingcenterdatabasev2
set DEST_DIR=C:\Scratch\TrainingCenterDatabasev2-XSD
set PACKAGE=net.kenevans.trainingcenterdatabasev2

cd "%XSD_DIR%"
"%XJC%" -d "%DEST_DIR%" -p %PACKAGE% TrainingCenterDatabasev2.xsd

echo All Done