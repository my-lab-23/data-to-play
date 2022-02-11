package my_postgres

import doobie._
import doobie.implicits._
import cats.effect._
import cats.effect.unsafe.implicits.global

object MyPostgres {

   val xa: doobie.Transactor[IO] = this.connect()

   def connect(): Transactor[IO] = {

      val xa = Transactor.fromDriverManager[IO](
                  "org.postgresql.Driver",
                  "jdbc:postgresql:twitter",
                  "postgres",
                  "1234"
               )
      xa
   }

   def select_all(): Seq[(Int, String, String)] = {

      val res = sql"select id, hashtag, body from tweet"
                  .query[(Int, String, String)]
                  .stream
                  .compile.toList
                  .transact(xa)
                  .unsafeRunSync()
      res
   }

   def select_body(hashtag: String): Seq[String] = {

      val res =
         sql"select body from tweet where hashtag=$hashtag order by vsum desc"
                  .query[String]
                  .stream
                  .compile.toList
                  .transact(xa)
                  .unsafeRunSync()
      res
   }   

   private def i(hashtag: String, body: String, vsum: Double): Update0 = {
      sql"""insert into tweet (hashtag, body, vsum)
           |values ($hashtag, $body, $vsum)"""
        .update
   }

   def insert (hashtag: String, body: String, vsum: Double): Int = {
      i(hashtag, body, vsum).run.transact(xa).unsafeRunSync()
   }

   def distinct_hashtag(): Seq[(String, Int)] = {
      val res = sql"select hashtag, count(hashtag) from tweet group by hashtag"
                  .query[(String, Int)]
                  .stream
                  .compile.toList
                  .transact(xa)
                  .unsafeRunSync()
      res
   }
}
