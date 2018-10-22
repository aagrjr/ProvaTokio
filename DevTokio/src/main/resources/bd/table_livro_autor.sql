CREATE TABLE `livro_autor` (
  `id_autor` bigint(20) NOT NULL,
  `id_livro` bigint(20) NOT NULL,
  KEY `fk_id_livro` (`id_livro`),
  KEY `fk_id_autor` (`id_autor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
