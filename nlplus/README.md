# Nlplus
Aggiungere il percorso dove salvare i dati come environment variable e metterci il file con le frasi in italiano "it_sentences.txt":
```bash
export MY_DATA_PATH="~/example_data_path"
sbt package
```
Su macchine con più di 8 GB di ram:
```bash
./run.sh Nlplus ./target/scala-2.12/spark-nlp-assembly-3.1.1.jar
```
Altrimenti prima tradurre e poi cercare di determinare il sentiment:
```bash
./run.sh NlplusT ./target/scala-2.12/spark-nlp-assembly-3.1.1.jar
./run.sh NlplusS ./target/scala-2.12/spark-nlp-assembly-3.1.1.jar
```
Qui si può scaricare il FAT jar più opportuno di Spark NLP (per CPU o GPU):
https://github.com/JohnSnowLabs/spark-nlp/releases
