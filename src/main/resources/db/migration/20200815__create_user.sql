# create user admin, 
# to encrypt your password access https://bcrypt-generator.com/,  use 10 rounds

insert into user (
 id,
 created, 
 updated, 
 is_active, 
 login, 
 name, 
 password
)
values (
  1, 
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP,
  1,
  'admin',
  'Administrador',
  '$2y$10$w31WZGt2zeiBXznazcceMuvijjYjdfHeWu3tkdRgoJfPGi9M4xfRi'
);

# add admin role
insert into user_roles (
 user_id,
 roles
)
values (
  1, 
  0
);