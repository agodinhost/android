-- -------------------------------------------------------- #
-- Essas tabelas são importadas na instalação da aplicação. #
-- -------------------------------------------------------- #


CREATE TABLE questions(
   _id        INTEGER PRIMARY KEY,
   banca      TEXT,
   ano 	      TEXT,
   orgao      TEXT,
   uf         TEXT,
   cargo      TEXT,
   disciplina TEXT,
   assunto    TEXT,
   question   TEXT NOT NULL,
   optionA    TEXT NOT NULL,
   optionB    TEXT NOT NULL,
   optionC    TEXT,
   optionD    TEXT,
   optionE    TEXT,
   "match"    TEXT NOT NULL,
   used       TEXT
);


CREATE TABLE quizzes(
   _id        INTEGER PRIMARY KEY AUTOINCREMENT,
   date       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
   filter     TEXT NOT NULL,
   rating     REAL NOT NULL DEFAULT 0.0,
   status     INTEGER NOT NULL DEFAULT 0,
   lastNumber INTEGER NOT NULL DEFAULT 1,
   total      INTEGER DEFAULT 0
);


CREATE TABLE answers(
   _id        INTEGER PRIMARY KEY AUTOINCREMENT,
   quizId     INTEGER NOT NULL,
   questionId INTEGER NOT NULL,
   number     INTEGER NOT NULL,
   answer     TEXT
);


CREATE VIEW IF NOT EXISTS vw_answers
AS SELECT q.*, a.quizId, a._ID as answerId, a.number, a.answer
   FROM   questions q, answers a
   WHERE  a.questionId = q._id
   ORDER  BY
          a.quizId, a.number;


CREATE VIEW IF NOT EXISTS vw_totals
AS SELECT v.quizId,
          q.total,
          SUM( 1 ) as rightAnswers
   FROM   vw_answers v,
          quizzes q
   WHERE  q._id = v.quizId
   AND    trim( v.match ) = v.answer
   GROUP  BY
          v.quizId, q.total
   ORDER  BY
          v.quizId;


CREATE TRIGGER IF NOT EXISTS tg_answers_ai
AFTER INSERT ON answers
FOR EACH ROW 
BEGIN 
   UPDATE questions 
   SET    used  = ifnull( used, '--' )
   WHERE  _id   = NEW.questionId;

   UPDATE quizzes
   SET    total = total + 1
   WHERE  _id   = NEW.quizId;
END;


CREATE TRIGGER IF NOT EXISTS tg_answers_au
AFTER UPDATE ON answers
FOR EACH ROW 
BEGIN 
   UPDATE questions 
   SET    used = CASE WHEN trim( match ) = NEW.answer
                 THEN 'r' || substr( ifnull( used, '--' ), 2, 1 )
                 ELSE substr( ifnull( used, '--' ), 1, 1 ) || 'w'
                 END
   WHERE  _id  = OLD.questionId;

   UPDATE quizzes
   SET    lastNumber = OLD.number
   WHERE  _id        = OLD.quizId;
END;


CREATE UNIQUE INDEX ix_answers_quizId_questionId
ON answers( quizId ASC, questionId ASC);


DROP INDEX   IF EXISTS ix_answers_quizId_questionId;
DROP TRIGGER IF EXISTS tg_answers_au;
DROP TRIGGER IF EXISTS tg_answers_ai;
DROP VIEW    IF EXISTS vw_totals;
DROP VIEW    IF EXISTS vw_answers;
DROP TABLE   IF EXISTS answers;
DROP TABLE   IF EXISTS quizzes;
DROP TABLE   IF EXISTS questions;


-- EOF.