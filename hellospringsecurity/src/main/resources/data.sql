INSERT INTO roles (id, rolename) VALUES (1, 'ROLE_ADMIN'),
                                        (2, 'ROLE_USER');
INSERT INTO users (id, email, password) VALUES
(3, 'alice@hansung.ac.kr', '$2a$10$kNV8XoSu1uGkcgQwMImd/O3HIES5L/Fx5XFxFkUb/5b5pMWODxT6G'),
(4, 'bob@hansung.ac.kr', '$2a$10$QuYYcgaP6LFfV53Qssvtp.hcj1BJVmgzO7pCValc27jm4SsI25Lf2'),
(5, 'trudy@hansung.ac.kr', '$2a$10$UXtORhsulW0fhwXhcDEhXO9CUQkIWPxRrGMJYQhMW/5TBd7rYbsFq');

insert into user_role(user_id, role_id) values (3,1), (3,2), (4,2), (5,2);

update hibernate_sequence set next_val= 6;