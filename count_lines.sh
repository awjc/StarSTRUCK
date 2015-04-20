typeset -i sum=0
typeset -i cnt=0


for file in $(find . -name '*.java');
do
	sum=$(( sum + $(wc -l < "${file}") ))
	cnt=$(( cnt + 1 ))
done


printf "\t=== %d files ===\n\t=== %d total lines ===\n\t=== %.2f average length ===\n" ${cnt} ${sum} "$(echo "scale=2; $sum / $cnt" | bc)"
