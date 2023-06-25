USE rosalind_framework;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user`
VALUES (1, 'admin', '$2a$10$o4sDuvI/uUFJeHHvCHPhUeFNcxCfdn/CpeJS3307VyMYU6sDQRasG', 1, '2022-08-15 18:37:58', '2022-08-15 18:38:00');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
