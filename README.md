# data-to-play

Progetto d'esempio in Scala che intercetta in tempo reale i tweet con
un determinato hashtag (v. bees), li trasforma (v. bumble) tramite tecniche di NLP (es. tokenizzazione, 
rimozione stop words, conteggio frequenza parole etc.) e carica i dati su un sito web per la 
visualizzazione (v. myweb2).

Dentro 'py_scripts' ci sono brevi esempi in Python (Selenium e Google Trends).

Dentro 'mani' c'è un programma in Scala 3 che manipola i dati estratti con Selenium.

Dentro 'nlplus' c'è un programma in Scala basato su Spark NLP che traduce in inglese delle frasi in italiano e poi prova a determinarne il sentiment.

Bees2 può girare su un'immagine Docker:
https://hub.docker.com/r/mylab23/bees2
