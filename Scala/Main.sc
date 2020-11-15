#!/usr/bin/env amm --no-remote-logging
/**
  * Note: This template is based on Ammonite Script. Make sure you have installed Ammonite before
  *       using this template.
  *       Example:-   $ ./Main.sc
  *
  * Copyright (c) 2020-present, Shingo OKAWA.
  * All rights reserved.
  */
import $ivy.`org.typelevel:cats-core_2.13:2.1.1`
import $ivy.`org.typelevel:cats-effect_2.13:2.1.1`

import scala.annotation.tailrec
import scala.annotation.elidable
import scala.annotation.elidable._
import scala.io._
import scala.reflect._
import java.io.{BufferedReader => JBufferedReader, PrintWriter => JPrintWriter}
import cats.effect.IO


class Tokenizer(val reader: JBufferedReader) {
  def nextLong(): Long = {
    if (!next()) throw new NoSuchElementException
    val sgn = if (peek == '-') -1l else 1
    if (sgn == -1l) ignore()
    if (peek < '0' || '9' < peek) throw new NumberFormatException
    @tailrec
    def loop(acc: Long=0) : Long =
      if ('0' <= peek && peek <= '9') {
        loop(acc * 10 + read() - '0')
      } else if (isEnd || isSpaceOrControl) {
        sgn * acc
      } else {
        throw new NumberFormatException
      }
    val l = loop()
    skipTrailingSpaces()
    l
  }

  def nextString(): String = {
    if (!next()) throw new NoSuchElementException
    val builder = new StringBuilder
    @tailrec
    def loop(): String =
      if (isEnd || isSpaceOrControl) {
        builder.toString
      } else {
        builder.append(read().toChar)
        loop()
      }
    val s = loop()
    skipTrailingSpaces()
    s
  }

  def readLine(): String = {
    if (isEnd) throw new NoSuchElementException
    skipNewline()
    val builder = new StringBuilder
    @tailrec
    def loop(): String =
      if (isEnd || isNewLine) {
        builder.toString
      } else {
        builder.append(read().toChar)
        loop()
      }
    loop()
  }

  def splitAs[T:ClassTag](delim: String, parse: String => T): Seq[T] = {
    readLine().split(delim).map(parse)
  }

  private[this] var peeked: Option[Int] = None

  private[this] var isFirst: Boolean = true

  private[this] def read(): Int = {
    val c = peeked match {
      case None => reader.read()
      case Some(c) => { peeked = None; c }
    }
    isFirst = false; c
  }

  private[this] def ignore(): Unit = { read(); () }

  private[this] def peek: Int = peeked match {
    case None => {
      val c = reader.read()
      peeked = Some(c)
      c
    }
    case Some(c) => c
  }

  private[this] def isEnd: Boolean = peek == -1

  private[this] def isCR: Boolean = peek == 13

  private[this] def isLF: Boolean = peek == 10

  private[this] def isNewLine: Boolean = isLF || isCR

  private[this] def isNotEnd: Boolean = !isEnd

  private[this] def isSpaceOrControl: Boolean = (0 <= peek && peek <= 32) || peek == 127

  @tailrec
  private[this] def skipSpaceAndControl(): Unit =
    if (isSpaceOrControl) {
      ignore()
      skipSpaceAndControl()
    }

  @tailrec
  private[this] def skipTrailingSpaces(): Unit =
    if (!isNewLine && isSpaceOrControl) {
      ignore()
      skipTrailingSpaces()
    }

  private[this] def skipNewline(): Unit =
    if (!isFirst) {
      if (isCR) ignore()
      if (isLF) ignore()
    }

  private[this] def next(): Boolean = {
    skipSpaceAndControl()
    isNotEnd
  }
}

def print(a: Any)(implicit w: JPrintWriter): IO[Unit] = IO {
  w.print(if (a == null) "null" else a.toString)
}

def println(a: Any)(implicit w: JPrintWriter): IO[Unit] = IO {
  w.println(a)
}

def printf(s: String, as: Any*)(implicit w: JPrintWriter): IO[Unit] = IO {
  w.printf(s.format(as : _*))
}

@elidable(INFO)
def debug(as: Any*)(implicit w: JPrintWriter): IO[Unit] = IO {
  w.println("\u001b[36m[DEBUG]\u001b[0m " + as.mkString(", "))
}

def nextInt()(implicit t: Tokenizer): IO[Int] = IO {
  t.nextLong().toInt
}

def nextLong()(implicit t: Tokenizer): IO[Long] = IO {
  t.nextLong()
}

def nextBigInt()(implicit t: Tokenizer): IO[BigInt] = IO {
  BigInt(t.nextString())
}

def nextDouble()(implicit t: Tokenizer): IO[Double] = IO {
  t.nextString().toDouble
}

def nextBigDecimal()(implicit t: Tokenizer): IO[BigDecimal] = IO {
  BigDecimal(t.nextString())
}

def nextString()(implicit t: Tokenizer): IO[String] = IO {
  t.nextString()
}

def readInts(delim: String)(implicit t: Tokenizer): IO[Seq[Int]] = IO {
  t.splitAs(delim, _.toInt)
}

def readLongs(delim: String)(implicit t: Tokenizer): IO[Seq[Long]] = IO {
  t.splitAs(delim, _.toLong)
}

def readStrings(delim: String)(implicit t: Tokenizer): IO[Seq[String]] = IO {
  t.splitAs(delim, identity)
}

implicit val cout = new JPrintWriter(Console.out, true);
implicit val cin = new Tokenizer(Console.in);

val test: IO[Unit] = for {
  _ <- debug("Welcome to Scala! println")
  _ <- debug("Input an integer")
  a <- nextInt()
  _ <- debug(a)
  _ <- debug("Input a big integer")
  b <- nextBigInt()
  _ <- debug(b)
  _ <- debug("Input a double")
  c <- nextDouble()
  _ <- debug(c)
  _ <- debug("Input a big decimal")
  d <- nextBigDecimal()
  _ <- debug(d)
  _ <- debug("Input comma separated integers")
  e <- readInts(",")
  _ <- debug(e)
  _ <- debug("Input comma separated longs")
  f <- readLongs(",")
  _ <- debug(f)
  _ <- debug("Input comma separated strings")
  g <- readStrings(",")
  _ <- debug(g)
} yield ()

@main
def main(args: String*): Unit = {
  test.unsafeRunSync()
}
