CREATE TABLE `users` (
   `id` varchar(40) NOT NULL,
   `login` varchar(255) NOT NULL,
   `password` varchar(255) NOT NULL,
   CONSTRAINT `PK_user_id` PRIMARY KEY (`id`),
   CONSTRAINT `UC_user_id` UNIQUE (`id`),
   CONSTRAINT `UC_user_login` UNIQUE (`login`)
 );

 create unique index `ID_user_login`
 on `users` (`login`);

  CREATE TABLE `projects` (
   `id` varchar(40) NOT NULL,
   `userId` varchar(40) NOT NULL,
   `name` varchar(255) NOT NULL,
   `description` varchar(255),
   CONSTRAINT `PK_project_id` PRIMARY KEY (`id`),
   CONSTRAINT `UC_project_id` UNIQUE (`id`),
   CONSTRAINT `FK_project_user_id` FOREIGN KEY (`userId`)
    REFERENCES `users`(`id`)
 );

  CREATE TABLE `tasks` (
   `id` varchar(40) NOT NULL,
   `userId` varchar(40) NOT NULL,
   `projectId` varchar(40) NOT NULL,
   `name` varchar(255) NOT NULL,
   `description` varchar(255),
   CONSTRAINT `PK_task_id` PRIMARY KEY (`id`),
   CONSTRAINT `UC_task_id` UNIQUE (`id`),
   CONSTRAINT `FK_task_user_id` FOREIGN KEY (`userId`)
    REFERENCES `users`(`id`),
    CONSTRAINT `FK_task_project_id` FOREIGN KEY (`projectId`)
    REFERENCES `projects`(`id`)
 );

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES ('1fb5ed23-6d0e-4f6f-bb03-8552f54bcbbf','login491','-482247052'),('23cc5b13-96cc-49a3-aad8-0a8c359d70ad','login980','-482247052'),('342cb5ea-45a7-4c5d-9756-fcc6084c9aa0','test','-1762269529'),('6748cab5-5972-4f95-9f96-55afb443a7f8','login421','-482247052'),('b6400229-db5a-42bb-b7f2-bc3f1058a525','login157','-482247052'),('c341c84c-f073-4474-9e69-394dc76af005','root','-215294734'),('fd1bee5e-fb6b-42b8-b091-917ea658a4aa','login642','-482247052');
UNLOCK TABLES;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
INSERT INTO `projects` VALUES ('1c9d2395-2008-4dd1-b877-cccdc2e9df41','342cb5ea-45a7-4c5d-9756-fcc6084c9aa0','qwertty','asdfgh'),('2423fc46-3dbb-404c-bd2b-9065bf4b5586','fd1bee5e-fb6b-42b8-b091-917ea658a4aa','Project_Name_104','Project_Description_225'),('3c0d53e7-d7aa-4f93-ad75-7a87d2d3dacb','1fb5ed23-6d0e-4f6f-bb03-8552f54bcbbf','SomeName+','SomeDescription'),('483eba3e-ecb9-4414-b4c2-5a90db917793','1fb5ed23-6d0e-4f6f-bb03-8552f54bcbbf','SomeNewName','BadDescription'),('48c61ec1-482b-44d6-bfb1-b14c10e50714','1fb5ed23-6d0e-4f6f-bb03-8552f54bcbbf','Project_Name_916','Project_Description_1'),('7b092f30-dc5d-48aa-a4f0-b85778e52e38','c341c84c-f073-4474-9e69-394dc76af005','weqrqew','weqrqwerq'),('934dd160-c76d-442b-bb6b-40745b2e5e26','1fb5ed23-6d0e-4f6f-bb03-8552f54bcbbf','SomeName+','SomeDescription'),('aaf690b8-2e92-4eb7-919e-e4f98aaf8828','1fb5ed23-6d0e-4f6f-bb03-8552f54bcbbf','SomeNewName','BadDescription'),('c07a13d1-bf26-4f37-a920-3a083ab3f7a2','c341c84c-f073-4474-9e69-394dc76af005','2314234','213412341'),('c4d5dd33-bacc-4a9e-8689-ca3763d87983','1fb5ed23-6d0e-4f6f-bb03-8552f54bcbbf','SomeNewName','BadDescription'),('cf4ebbee-f1cb-4cea-b981-22a560df2f3a','1fb5ed23-6d0e-4f6f-bb03-8552f54bcbbf','SomeName+','SomeDescription'),('f67ba29b-8323-488c-b25d-2619e973a104','6748cab5-5972-4f95-9f96-55afb443a7f8','Project_Name_627','Project_Description_267'),('f894f44a-b490-4804-a2b0-44aacddfa03d','342cb5ea-45a7-4c5d-9756-fcc6084c9aa0','Project_Name_704','Project_Description_445'),('fa310465-a939-4c47-99ae-a8f6364e2451','342cb5ea-45a7-4c5d-9756-fcc6084c9aa0','Project_Name_195','Project_Description_129'),('fd984a8e-a794-497c-8b59-fb7c5583c751','1fb5ed23-6d0e-4f6f-bb03-8552f54bcbbf','SomeNewName','BadDescription');
UNLOCK TABLES;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
INSERT INTO `tasks` VALUES ('0a67f16e-ea99-40ec-9634-fbe2188206e2','1fb5ed23-6d0e-4f6f-bb03-8552f54bcbbf','48c61ec1-482b-44d6-bfb1-b14c10e50714','Task_Name7','Task_Description_402'),('0f9d9aa7-b408-4659-bbac-37294f1dbcf6','fd1bee5e-fb6b-42b8-b091-917ea658a4aa','2423fc46-3dbb-404c-bd2b-9065bf4b5586','Task_Name759','Task_Description_402'),('1ad90328-8488-4f7c-a2e4-e2b96c8372e5','342cb5ea-45a7-4c5d-9756-fcc6084c9aa0','f894f44a-b490-4804-a2b0-44aacddfa03d','Task_Name510','Task_Description_409'),('39ba9092-3315-47a0-b8a9-24c4fd3ae9c5','fd1bee5e-fb6b-42b8-b091-917ea658a4aa','2423fc46-3dbb-404c-bd2b-9065bf4b5586','Task_Name333','Task_Description_698'),('39f62a18-7132-4d28-8871-400bd39cc320','fd1bee5e-fb6b-42b8-b091-917ea658a4aa','2423fc46-3dbb-404c-bd2b-9065bf4b5586','Task_Name669','Task_Description_914'),('3c0101df-4882-4cfd-be88-c6e30c573b9f','342cb5ea-45a7-4c5d-9756-fcc6084c9aa0','1c9d2395-2008-4dd1-b877-cccdc2e9df41','21343124','213423412341'),('4fb29522-7748-4c86-b101-4d4607722ec3','6748cab5-5972-4f95-9f96-55afb443a7f8','f67ba29b-8323-488c-b25d-2619e973a104','Task_Name820','Task_Description_609'),('5392255d-023c-4013-9688-f6c93c93549c','fd1bee5e-fb6b-42b8-b091-917ea658a4aa','2423fc46-3dbb-404c-bd2b-9065bf4b5586','Task_Name335','Task_Description_858'),('53d937d3-c3ee-4af8-b85c-88e9c843aedd','342cb5ea-45a7-4c5d-9756-fcc6084c9aa0','fa310465-a939-4c47-99ae-a8f6364e2451','Task_Name377','Task_Description_802'),('66a1f894-fd34-415e-9388-1ed48e6109cb','fd1bee5e-fb6b-42b8-b091-917ea658a4aa','2423fc46-3dbb-404c-bd2b-9065bf4b5586','Task_Name127','Task_Description_475'),('9560b849-1d68-47bb-a3a2-7e60c4dea8de','fd1bee5e-fb6b-42b8-b091-917ea658a4aa','2423fc46-3dbb-404c-bd2b-9065bf4b5586','Task_Name298','Task_Description_302'),('958077b6-4422-498d-ab2c-16d760eee598','6748cab5-5972-4f95-9f96-55afb443a7f8','f67ba29b-8323-488c-b25d-2619e973a104','Task_Name46','Task_Description_481'),('a6d1c3e1-e224-49ce-ae96-cfa96f99a4f3','342cb5ea-45a7-4c5d-9756-fcc6084c9aa0','f894f44a-b490-4804-a2b0-44aacddfa03d','Task_Name489','Task_Description_224'),('af2c49fd-b8bd-4e68-bef3-6233f5adec69','1fb5ed23-6d0e-4f6f-bb03-8552f54bcbbf','48c61ec1-482b-44d6-bfb1-b14c10e50714','Task_Name213','Task_Description_151'),('bd3bb0c5-d5c0-4d61-bb88-629918a39537','fd1bee5e-fb6b-42b8-b091-917ea658a4aa','2423fc46-3dbb-404c-bd2b-9065bf4b5586','Task_Name152','Task_Description_189'),('d73b74ea-26ca-4867-b958-239e0f254721','342cb5ea-45a7-4c5d-9756-fcc6084c9aa0','1c9d2395-2008-4dd1-b877-cccdc2e9df41','qwerty','qwertsafd'),('f873c110-d944-4dae-adbc-04ebe43599a5','c341c84c-f073-4474-9e69-394dc76af005','c07a13d1-bf26-4f37-a920-3a083ab3f7a2','adsgwre','gsregeg');
UNLOCK TABLES;

