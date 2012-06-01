-- -------------------------------------------------------- #
-- Essas tabelas s�o importadas na instala��o da aplica��o. #
-- -------------------------------------------------------- #


CREATE TABLE questions(
	_id        INTEGER PRIMARY KEY,
	banca      TEXT,
	ano 	   TEXT,
	orgao      TEXT,
	uf		   TEXT,
	cargo      TEXT,
	disciplina TEXT,
	assunto    TEXT,
	question   TEXT NOT NULL,
	optionA    TEXT NOT NULL,
	optionB    TEXT NOT NULL,
	optionC    TEXT,
	optionD    TEXT,
	optionE    TEXT,
	match      TEXT NOT NULL,
	used       TEXT
);


CREATE TABLE quizzes(
	_id        INTEGER PRIMARY KEY AUTOINCREMENT,
	date       DATETIME NOT NULL DEFAULT CURRENT_DATE,
	filter 	   TEXT NOT NULL,
	rating     INTEGER NOT NULL DEFAULT 0,
	status	   INTEGER NOT NULL DEFAULT 0,
	lastNumber INTEGER NOT NULL DEFAULT 1
);


CREATE TABLE answers(
	_id        INTEGER PRIMARY KEY AUTOINCREMENT,
	quizId     INTEGER NOT NULL,
	questionId INTEGER NOT NULL,
	number     INTEGER NOT NULL,
	answer     TEXT
);


CREATE UNIQUE INDEX idx_answers_quizId_questionId
ON answers( quizId ASC, questionId ASC);


CREATE TRIGGER IF NOT EXISTS trg_answers_ai
AFTER INSERT ON answers
FOR EACH ROW 
BEGIN 
	UPDATE questions 
	   SET used = ifnull( used, '--' )
	 WHERE _id  = NEW.questionId;
END


DROP INDEX IF EXISTS answers_quizId_questionId_idx;
DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS quizzes;
DROP TABLE IF EXISTS questions;


-- EOF.