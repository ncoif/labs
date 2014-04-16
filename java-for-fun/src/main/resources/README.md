To create eclipse settings for eclipse:

mvn eclipse:eclipse -DdownloadSources -DdownloadJavadocs
from http://stackoverflow.com/questions/310720/get-source-jar-files-attached-to-eclipse-for-maven-managed-dependencies


Don't forget to remove special characters from the html file names.

mvn install:install-file -Dfile=remark-0.9.3.jar -DgroupId=com.overzealous.remark -DartifactId=remark -Dversion=0.9.3 -Dpackaging=jar

ls |grep post > list.csv to create the list of files to import