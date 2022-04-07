con las carpetas y archivos creados de manera correcta de acuerdo al 
tutorial de soot proporcionado por la materia

compilar todos los archivos con javac

javac -d bin -g src/LiveVariablesExample.java 
javac -d bin -g src/ReachingDefinitionsExample.java 

ejercicio 1:
ejecutar el siguiente comando 
java -classpath .:soot-3.3.0-jar-with-dependencies.jar:. soot.Main -cp bin:$JRE -f J ReachingDefinitionsExample -print-tags -p jap.rdtagger enabled:true -p jb use-original-names:true -p jb.cp off -keep-line-number

ejercicio 2:
ejecutar el siguiente comando 
java -classpath .:soot-3.3.0-jar-with-dependencies.jar:. soot.Main -cp bin:$JRE -f J LiveVariablesExample -print-tags -p jap.lvtagger enabled:true -p jb use-original-names:true -p jb.cp off -keep-line-number
