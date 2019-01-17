#!/usr/bin/env bash

version=$1

echo "TEST VERSION : "${version}

for fichier in `ls ./Yal${version}/src/yal/test/sources/` ; do
#    echo ${fichier}

    name=${fichier%.*}
#    echo ${name}

    java -jar out/artifacts/Yal${version}/Yal${version}.jar Yal${version}/src/yal/test/sources/${name}.yal >> /dev/null
    mv Yal${version}/src/yal/test/sources/${name}.mips Yal${version}/src/yal/test/generer/

    java -jar /opt/depot/compilation/Mars4_5.jar  Yal${version}/src/yal/test/generer/${name}.mips > Yal${version}/src/yal/test/generer/${name}.txt

    err=$(diff Yal${version}/src/yal/test/generer/${name}.txt Yal${version}/src/yal/test/comparer/${name}.txt)
#    echo ">"$err"<"
#    if "$err" -ne "0"; then
    if [ ! "$err" = "" ]; then
        echo ${name} " Erreur"
    else
        echo ${name} " OK"
    fi

done

#rm src/yal/test/generer/*.mips

echo "DONE"

#java -jar ../out/artifacts/Yal0/Yal0.jar src/yal/test/sources/test3.yal >> /dev/null
#mv src/yal/test/sources/test3.mips src/yal/test/generer/
#
#java -jar /opt/depot/compilation/Mars4_5.jar  src/yal/test/generer/test3.mips > src/yal/test/generer/test3.txt
#diff src/yal/test/generer/test3.txt src/yal/test/comparer/test3.txt > $err
#if $err <> 0; then
#    echo "Erreur fichier: " + $fichier
#fi


