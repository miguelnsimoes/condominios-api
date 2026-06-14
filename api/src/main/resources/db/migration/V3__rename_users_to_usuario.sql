DO $$
BEGIN
    IF EXISTS (
        SELECT 1 FROM information_schema.tables
        WHERE table_schema = 'public' AND table_name = 'users'
    ) AND EXISTS (
        SELECT 1 FROM information_schema.tables
        WHERE table_schema = 'public' AND table_name = 'usuario'
    ) THEN
        INSERT INTO usuario (id, login, senha, role)
        SELECT id, login, senha, role FROM users
        ON CONFLICT (login) DO NOTHING;

        PERFORM setval(
            pg_get_serial_sequence('usuario', 'id'),
            COALESCE((SELECT MAX(id) FROM usuario), 1)
        );

        DROP TABLE users;
    ELSIF EXISTS (
        SELECT 1 FROM information_schema.tables
        WHERE table_schema = 'public' AND table_name = 'users'
    ) AND NOT EXISTS (
        SELECT 1 FROM information_schema.tables
        WHERE table_schema = 'public' AND table_name = 'usuario'
    ) THEN
        ALTER TABLE users RENAME TO usuario;
    END IF;
END $$;
