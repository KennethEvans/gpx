rem BAT file to generate combined JAXB files for GPX and Trackpointextensionv2
rem DEST_DIR must already exist

set XJC=C:\Java\JDK64\jdk1.8.0_152-x64\bin\xjc.exe
set XSD_DIR=C:\eclipseWorkspaces\Work\net.kenevans.gpx
set DEST_DIR=C:\Scratch\GPX\GPX-GpxTrackpointextensionv2-XSD
set PACKAGE=net.kenevans.gpxtrackpointextensionv2

cd "%XSD_DIR%"
"%XJC%" -d "%DEST_DIR%" -p %PACKAGE% gpx.xsd TrackPointExtensionv2.xsd

echo All Done