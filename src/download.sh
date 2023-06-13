echo "Hello World"
#check if the file exists ./rsc/Original.png
if [ -f ./rsc/Original.png ]
then
  echo "File exists"
  echo "Copying file" >> log.txt
fi