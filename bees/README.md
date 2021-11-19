# bees
Example application using [twitter4s](https://github.com/DanielaSfregola/twitter4s) library.


## Usage
Add your consumer and access token as environment variables:

```bash
export TWITTER_CONSUMER_TOKEN_KEY='my-consumer-key'
export TWITTER_CONSUMER_TOKEN_SECRET='my-consumer-secret'
export TWITTER_ACCESS_TOKEN_KEY='my-access-key'
export TWITTER_ACCESS_TOKEN_SECRET='my-access-secret'
```

Aggiungere il percorso dove salvare i dati come environment variable:

```bash
export MY_DATA_PATH="~/example_data_path"
sbt "run #hashtag1 #hashtag2 ..."
```
