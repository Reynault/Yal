#!/usr/bin/env bash

for fichier in `ls ./src/yal/test/sources/` ; do
    echo ${fichier}

    name=${fichier%.*}
#    echo ${name}

    java -jar ../out/artifacts/Yal0/Yal0.jar src/yal/test/sources/${name}.yal >> /dev/null
    mv src/yal/test/sources/${name}.mips src/yal/test/generer/

    java -jar /opt/depot/compilation/Mars4_5.jar  src/yal/test/generer/${name}.mips > src/yal/test/generer/${name}.txt

    err = $(diff src/yal/test/generer/${name}.txt src/yal/test/comparer/${name}.txt)
    echo $err
    if "$err" -ne 0; then
        echo "Erreur fichier: " + ${name}
    fi

done


#java -jar ../out/artifacts/Yal0/Yal0.jar src/yal/test/sources/test3.yal >> /dev/null
#mv src/yal/test/sources/test3.mips src/yal/test/generer/
#
#java -jar /opt/depot/compilation/Mars4_5.jar  src/yal/test/generer/test3.mips > src/yal/test/generer/test3.txt
#diff src/yal/test/generer/test3.txt src/yal/test/comparer/test3.txt > $err
#if $err <> 0; then
#    echo "Erreur fichier: " + $fichier
#fi


