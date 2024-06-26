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



SELECT * FROM "SEED_TYPE"
ORDER BY SEED_CODE;


COMMIT;

ROLLBACK;

-- 씨앗 넣기

-- 계정 추가
INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user01', 'pass01', '유저일', 'user01@kh.com',
			NULL, '01011112222', DEFAULT, DEFAULT, DEFAULT);


-- 유저일 비밀번호 
-- pass01 을 암호화된 $2a$10$2p01wv0FlCOJkGRcZCl.lOPXd1kYpmgxBNCZwdSNXiVzeQgthQuwe 로 업데이트
UPDATE "MEMBER" SET 
MEMBER_PW = '$2a$10$2p01wv0FlCOJkGRcZCl.lOPXd1kYpmgxBNCZwdSNXiVzeQgthQuwe'
WHERE MEMBER_ID = 'user01';

COMMIT;


SELECT * FROM "MEMBER";

ROLLBACK;


-- 보드 타입
INSERT INTO "BOARD_TYPE"
VALUES(SEQ_BOARD_CODE.NEXTVAL, '자유 게시판');
INSERT INTO "BOARD_TYPE"
VALUES(SEQ_BOARD_CODE.NEXTVAL, '문의 게시판');
INSERT INTO "BOARD_TYPE"
VALUES(SEQ_BOARD_CODE.NEXTVAL, '팁과 노하우');




-- 회원 주소
INSERT INTO "ADDRESS"
VALUES(1, 1, '22203^^^인천시 미추홀구 매소홀로 340^^^101동 101호');
INSERT INTO "ADDRESS"
VALUES(2, 1, '34753^^^서울시 강남구^^^101동 101호');


COMMIT;

SELECT * FROM BOARD_TYPE ;

SELECT * FROM MEMBER;

SELECT * FROM "ADDRESS";


SELECT * FROM TB_AUTH_KEY;

SELECT COUNT(*)
FROM "MEMBER"
WHERE MEMBER_DEL_FL = 'N'
AND MEMBER_ID = 'user01';


SELECT ADDRESS_NO "addressNo", MEMBER_ADDRESS "memberAddress"
		FROM "ADDRESS"
		WHERE MEMBER_NO = 1;



SELECT COUNT(*)
FROM "MEMBER"
WHERE MEMBER_ID = 'user01'
AND MEMBER_EMAIL = 'user01@kh.com';


INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'test1234', 'test1234', '테스트', 'desk1614@gmail.com',
			NULL, '01011112222', DEFAULT, DEFAULT, DEFAULT);


COMMIT;


INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user02', 'pass02', '김종엽', 'abab1126@naver.com',
			NULL, '01011112222', DEFAULT, DEFAULT, DEFAULT);


SELECT * FROM "MEMBER" m ;


UPDATE "MEMBER" SET 
MEMBER_DEL_FL = 'N'
WHERE MEMBER_NO = 1;
