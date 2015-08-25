CREATE DATABASE IF NOT EXISTS metrade1;
CREATE USER metrade@localhost IDENTIFIED by 'metrade123';
GRANT ALL ON metrade1.* TO metrade@localhost;
COMMIT;