-- 품종 넣기

INSERT INTO "SEED_TYPE"
VALUES(SEQ_SEED_CODE.NEXTVAL, '가지/오이');

INSERT INTO "SEED_TYPE"
VALUES(SEQ_SEED_CODE.NEXTVAL, '무/당근');

INSERT INTO "SEED_TYPE"
VALUES(SEQ_SEED_CODE.NEXTVAL, '민속채소');

INSERT INTO "SEED_TYPE"
VALUES(SEQ_SEED_CODE.NEXTVAL, '배추/양배추');

INSERT INTO "SEED_TYPE"
VALUES(SEQ_SEED_CODE.NEXTVAL, '부추');

INSERT INTO "SEED_TYPE"
VALUES(SEQ_SEED_CODE.NEXTVAL, '시금치');

INSERT INTO "SEED_TYPE"
VALUES(SEQ_SEED_CODE.NEXTVAL, '쌈채소');

INSERT INTO "SEED_TYPE"
VALUES(SEQ_SEED_CODE.NEXTVAL, '참외/수박');

INSERT INTO "SEED_TYPE"
VALUES(SEQ_SEED_CODE.NEXTVAL, '콩/옥수수');

INSERT INTO "SEED_TYPE"
VALUES(SEQ_SEED_CODE.NEXTVAL, '호박');



DELETE FROM "SEED_TYPE" ;
DELETE FROM "MEMBER"  ;


SELECT * FROM "SEED_TYPE";


COMMIT;



-- 씨앗 넣기

-- 계정 추가
INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user01', 'pass01', '유저일', 'user01@kh.com',
			NULL, '01011112222', DEFAULT, DEFAULT, DEFAULT);


COMMIT;

SELECT * FROM "MEMBER";









