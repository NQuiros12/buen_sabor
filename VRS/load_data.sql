-- DROP database IF EXISTS VRS;
-- CREATE DATABASE VRS CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

use VRS;

/*Data for the table `unidad_medida` */

insert  into `unidad_medida`(`id`,`alta_baja`,`denominacion`,`tipo`,`tipo_clase`) values (1,'','eqweqw','ewq','UnidadMedida'),(2,'','dsada','dsa','UnidadMedida'),(3,'','dsa','dsaaa','UnidadMedida');

/*Data for the table `usuario` */

insert  into `usuario`(`id`,`rol`,`usuario`,`id_auth0`) values (1,4,'jCASCADAELALLcom',NULL),(2,4,'josue',NULL),(15,4,'fedejpitton@gmail.com','105281214412266748137'),(16,4,'vrscpq@gmail.com','117457398926422598475'),(17,4,'fjosepitton@gmail.com','115387165569328974639'),(18,4,'probando@test.com','64ad86fd0e20777e46ff8ad3');

/*Data for the table `domicilio` */

insert  into `domicilio`(`id`,`calle`,`departamento`,`localidad`,`numero`,`piso`,`tipo_clase`) values (1,'ExamplCASCACDEALL 1 et','Example Department','Example City',123,5,'Domicilio'),(2,'ExamplCASCACDEALL 1 et','Example Department','Example City',123,5,'Domicilio'),(6,'probando','ewq','ewq3',321,21,'Domicilio'),(7,'direccion del vrs','MEdn','Menodo',1234,2,'Domicilio'),(8,'wqeeqewq','ewqewq','ewqeqw',32131,321321,'Domicilio'),(9,'Buena vida','Guaymallen','San Jose',1423,4,'Domicilio');

/*Data for the table `categoria_articulo` */
insert  into `categoria_articulo`(`id`,`alta_baja`,`denominacion`,`tipo_clase`,`parent_id`) values (1,'','papas','Categoria',NULL),(2,'','hamburguesas','Categoria',NULL);

insert  into `articulo_insumo`(`id`,`alta_baja`,`denominacion`,`precio_compra`,`precio_venta`,`stock_actual`,`stock_minimo`,`tipo_clase`,`fk_categoria`,`fk_unidad_medida`) values (1,'','eqweqw',321312,32,322,2,'ArticuloInsumo',1,1);

/*Data for the table `articulo_manufacturado` */

insert  into `articulo_manufacturado`(`id`,`alta_baja`,`denominacion`,`descripcion`,`imagen`,`precio_compra`,`precio_venta`,`es_producto_final`,`receta`,`stock_actual`,`stock_minimo`,`tiempo_estimado_cocina`,`tipo_clase`,`fk_categoria`) values (1,'','ewqewqewq','ewqewewqqew','',321,23232,'','ewqewewqqew',231,2,321321,'ArticuloManufacturado',1),(2,'\0','Alto producto burguer2','Una gran burger','aca va la imagen',0,22.5,'\0','amor y pasion',0,0,20,'ArticuloManufacturado',1),(3,'\0','Alto producto burguer2','Una gran burger','aca va la imagen',0,22.5,'\0','amor y pasion',0,0,20,'ArticuloManufacturado',2),(4,'','Pizza margueerrirrtaa','pizzaa','https://res.cloudinary.com/dka1fqaps/image/upload/v1689297347/bvjtccfyp1shg1ymwe1c.jpg',100,12000,'','pizzaa',312,2,120,'ArticuloManufacturado',1),(5,'','ewqewqewq','ewqewewqqew','',321,23232,'','ewqewewqqew',231,2,321321,'ArticuloManufacturado',1);

/*Data for the table `detalle_articulo_manufacturado` */

insert  into `detalle_articulo_manufacturado`(`id`,`cantidad`,`tipo_clase`,`fk_articulo_insumo`,`fk_articulo_manufacturado`) values (1,10250,'DetalleArticuloManufacturado',1,2),(2,92,'DetalleArticuloManufacturado',1,2),(3,10250,'DetalleArticuloManufacturado',1,3),(4,92,'DetalleArticuloManufacturado',1,3);


/*Data for the table `cliente` */

INSERT INTO `cliente` (`id`, `apellido`, `email`, `nombre`, `telefono`, `fk_domicilio`, `fk_usuario`)
VALUES
    (1, 'Doe2d3ConCascade1ll', 'john@example.com', 'John', 123456789, 1, 1),
    (2, 'Doe2d3ConCascade1ll', 'john@example.com', 'Josue', 123456789, 2, 2),
    (14, 'pitton', 'fedejpitton@gmail.com', 'federico', 0, 6, 15),
    (15, 'ChiroliPittonQuiros', 'vrscpq@gmail.com', 'VenRapidoYSabroso', 0, 7, 16),
    (16, 'Pitton', 'fjosepitton@gmail.com', 'Federico', 0, 8, 17),
    (17, '', 'probando@test.com', '', 0, 9, 18);


/*Data for the table `detalle_factura` */


/*Data for the table `pedido` */

INSERT INTO pedido (id, estado_pedido, fecha, forma_pago, numero, tipo_clase, tipo_envio, fk_cliente, fk_domicilio)
VALUES
    (4, 5, '2023-06-14 12:34:56', 1, 123, 'Pedido', 1, 1, 1),
    (5, 5, '2023-06-15 09:12:45', 1, 123, 'Pedido', 1, 1, 1),
    (6, 5, '2023-06-16 15:23:10', 1, 123, 'Pedido', 1, 1, 1),
    (7, 5, '2023-06-17 17:45:30', 1, 123, 'Pedido', 1, 1, 1),
    (8, 5, '2023-06-14 08:57:20', 1, 123, 'Pedido', 1, 1, 1),
    (18, 4, '2023-06-15 11:22:40', 1, 123, 'Pedido', 1, 1, 1),
    (19, 5, '2023-06-16 14:35:15', 1, 123, 'Pedido', 1, 1, 1),
    (20, 5, '2023-06-17 16:48:05', 1, 123, 'Pedido', 1, 1, 1),
    (21, 5, '2023-06-14 10:09:25', 1, 123, 'Pedido', 1, 1, 1),
    (22, 5, '2023-06-15 13:17:50', 1, 123, 'Pedido', 1, 1, 1),
    (23, 5, '2023-06-16 18:30:00', 1, 123, 'Pedido', 1, 1, 1),
    (24, 5, '2023-06-17 20:40:55', 1, 123, 'Pedido', 1, 1, 1),
    (25, 4, '2023-06-14 09:00:30', 1, 123, 'Pedido', 1, 1, 1),
    (26, 6, '2023-06-15 16:10:20', 1, 123, 'Pedido', 1, 1, 1),
    (27, 6, '2023-06-16 11:55:40', 1, 123, 'Pedido', 1, 2, 1),
    (28, 6, '2023-06-17 09:20:15', 1, 123, 'Pedido', 1, 2, 1),
    (29, 3, '2023-06-14 13:40:25', 1, 123, 'Pedido', 1, 2, 1),
    (30, 5, '2023-06-15 08:30:55', 1, 123, 'Pedido', 1, 2, 1),
    (31, 3, '2023-06-16 16:05:10', 1, 123, 'Pedido', 1, 2, 1),
    (32, 4, '2023-06-17 17:55:45', 1, 123, 'Pedido', 1, 2, 1),
    (33, 6, '2023-06-14 09:40:00', 1, 123, 'Pedido', 1, 2, 1),
    (34, 3, '2023-06-15 14:25:30', 1, 123, 'Pedido', 1, 2, 1),
    (35, 4, '2023-06-13 22:00:00', 1, 123, 'Pedido', 1, 2, 1),
    (36, 3, '2023-06-13 22:00:00', 1, 123, 'Pedido', 1, 2, 1),
    (37, 3, '2023-06-13 22:00:00', 1, 123, 'Pedido', 1, 2, 1),
    (38, 3, '2023-06-13 22:00:00', 1, 123, 'Pedido', 1, 2, 1),
    (39, 3, '2023-06-13 22:00:00', 1, 123, 'Pedido', 1, 2, 1),
    (40, 3, '2023-06-13 22:00:00', 1, 123, 'Pedido', 1, 2, 1),
    (41, 2, '2023-06-13 22:00:00', 1, 123, 'Pedido', 1, 14, 1),
    (42, 2, '2023-06-13 22:00:00', 1, 123, 'Pedido', 1, 14, 1),
    (43, 2, '2023-06-13 22:00:00', 1, 123, 'Pedido', 1, 14, 1),
    (44, 2, '2023-06-13 22:00:00', 1, 123, 'Pedido', 1, 14, 1),
    (45, 2, '2023-06-13 22:00:00', 1, 123, 'Pedido', 1, 14, 1),
    (46, 2, '2023-06-13 22:00:00', 1, 123, 'Pedido', 1, 14, 1),
    (47, 2, '2023-06-13 22:00:00', 1, 123, 'Pedido', 1, 14, 1),
    (48, 2, '2023-06-13 22:00:00', 1, 123, 'Pedido', 1, 14, 1),
    (49, 2, '2023-06-13 22:00:00', 1, 123, 'Pedido', 1, 14, 1),
    (50, 5, '2023-06-13 22:00:00', 1, 123, 'Pedido', 1, 14, 1);
/*Data for the table `detalle_pedido` */
INSERT INTO `detalle_pedido` (`id`, `cantidad`, `subtotal`, `tipo_clase`, `fk_articulo_manufacturado`, `fk_pedido`) VALUES
                                                                                                                        (5, 1, 15.99, 'DetallePedido', 1, 4),
                                                                                                                        (6, 1, 15.99, 'DetallePedido', 2, 5),
                                                                                                                        (7, 1, 15.99, 'DetallePedido', 3, 6),
                                                                                                                        (8, 1, 15.99, 'DetallePedido', 4, 7),
                                                                                                                        (9, 1, 15.99, 'DetallePedido', 5, 8),
                                                                                                                        (19, 1, 15.99, 'DetallePedido', 1, 18),
                                                                                                                        (20, 1, 15.99, 'DetallePedido', 2, 19),
                                                                                                                        (21, 1, 15.99, 'DetallePedido', 3, 20),
                                                                                                                        (22, 1, 15.99, 'DetallePedido', 4, 21),
                                                                                                                        (23, 1, 15.99, 'DetallePedido', 5, 22),
                                                                                                                        (24, 1, 15.99, 'DetallePedido', 1, 23),
                                                                                                                        (25, 1, 15.99, 'DetallePedido', 2, 24),
                                                                                                                        (26, 1, 15.99, 'DetallePedido', 3, 25),
                                                                                                                        (27, 1, 15.99, 'DetallePedido', 4, 26),
                                                                                                                        (28, 1, 15.99, 'DetallePedido', 5, 27),
                                                                                                                        (29, 1, 15.99, 'DetallePedido', 1, 28),
                                                                                                                        (30, 1, 15.99, 'DetallePedido', 2, 29),
                                                                                                                        (31, 4, 15.99, 'DetallePedido', 3, 29),
                                                                                                                        (32, 1, 15.99, 'DetallePedido', 4, 30),
                                                                                                                        (33, 4, 15.99, 'DetallePedido', 5, 30),
                                                                                                                        (34, 4, 15.99, 'DetallePedido', 1, 30),
                                                                                                                        (35, 4, 15.99, 'DetallePedido', 2, 30),
                                                                                                                        (36, 4, 15.99, 'DetallePedido', 3, 30),
                                                                                                                        (37, 1, 15.99, 'DetallePedido', 4, 31),
                                                                                                                        (38, 4, 15.99, 'DetallePedido', 5, 31),
                                                                                                                        (39, 4, 15.99, 'DetallePedido', 1, 31),
                                                                                                                        (40, 4, 15.99, 'DetallePedido', 2, 31),
                                                                                                                        (41, 4, 15.99, 'DetallePedido', 3, 31),
                                                                                                                        (42, 1, 15.99, 'DetallePedido', 4, 32),
                                                                                                                        (43, 4, 15.99, 'DetallePedido', 5, 32),
                                                                                                                        (44, 4, 15.99, 'DetallePedido', 1, 32),
                                                                                                                        (45, 4, 15.99, 'DetallePedido', 2, 32),
                                                                                                                        (46, 4, 15.99, 'DetallePedido', 3, 32),
                                                                                                                        (47, 1, 15.99, 'DetallePedido', 4, 33),
                                                                                                                        (48, 4, 15.99, 'DetallePedido', 5, 33),
                                                                                                                        (49, 4, 15.99, 'DetallePedido', 1, 33),
                                                                                                                        (50, 4, 15.99, 'DetallePedido', 2, 33),
                                                                                                                        (51, 4, 15.99, 'DetallePedido', 3, 33),
                                                                                                                        (52, 1, 15.99, 'DetallePedido', 4, 34),
                                                                                                                        (53, 4, 15.99, 'DetallePedido', 5, 34),
                                                                                                                        (54, 4, 15.99, 'DetallePedido', 1, 34),
                                                                                                                        (55, 4, 15.99, 'DetallePedido', 2, 34),
                                                                                                                        (56, 4, 15.99, 'DetallePedido', 3, 34),
                                                                                                                        (57, 1, 15.99, 'DetallePedido', 4, 35),
                                                                                                                        (58, 4, 15.99, 'DetallePedido', 5, 35),
                                                                                                                        (59, 4, 15.99, 'DetallePedido', 1, 35),
                                                                                                                        (60, 4, 15.99, 'DetallePedido', 2, 35),
                                                                                                                        (61, 4, 15.99, 'DetallePedido', 3, 35),
                                                                                                                        (62, 1, 15.99, 'DetallePedido', 4, 36),
                                                                                                                        (63, 4, 15.99, 'DetallePedido', 5, 36),
                                                                                                                        (64, 4, 15.99, 'DetallePedido', 1, 36),
                                                                                                                        (65, 4, 15.99, 'DetallePedido', 2, 36),
                                                                                                                        (66, 4, 15.99, 'DetallePedido', 3, 36),
                                                                                                                        (67, 1, 15.99, 'DetallePedido', 4, 37),
                                                                                                                        (68, 4, 15.99, 'DetallePedido', 5, 37),
                                                                                                                        (69, 4, 15.99, 'DetallePedido', 1, 37),
                                                                                                                        (70, 4, 15.99, 'DetallePedido', 2, 37),
                                                                                                                        (71, 4, 15.99, 'DetallePedido', 3, 37),
                                                                                                                        (72, 1, 15.99, 'DetallePedido', 4, 38),
                                                                                                                        (73, 4, 15.99, 'DetallePedido', 5, 38),
                                                                                                                        (74, 4, 15.99, 'DetallePedido', 1, 38),
                                                                                                                        (75, 4, 15.99, 'DetallePedido', 2, 38),
                                                                                                                        (76, 4, 15.99, 'DetallePedido', 3, 38),
                                                                                                                        (77, 1, 15.99, 'DetallePedido', 4, 39),
                                                                                                                        (78, 4, 15.99, 'DetallePedido', 5, 39),
                                                                                                                        (79, 4, 15.99, 'DetallePedido', 1, 39),
                                                                                                                        (80, 4, 15.99, 'DetallePedido', 2, 39),
                                                                                                                        (81, 4, 15.99, 'DetallePedido', 3, 39),
                                                                                                                        (82, 1, 15.99, 'DetallePedido', 4, 40),
                                                                                                                        (83, 4, 15.99, 'DetallePedido', 5, 40),
                                                                                                                        (84, 4, 15.99, 'DetallePedido', 1, 40),
                                                                                                                        (85, 4, 15.99, 'DetallePedido', 2, 40),
                                                                                                                        (86, 4, 15.99, 'DetallePedido', 3, 40),
                                                                                                                        (87, 1, 15.99, 'DetallePedido', 4, 41),
                                                                                                                        (88, 4, 15.99, 'DetallePedido', 5, 41),
                                                                                                                        (89, 4, 15.99, 'DetallePedido', 1, 41),
                                                                                                                        (90, 4, 15.99, 'DetallePedido', 2, 41),
                                                                                                                        (91, 4, 15.99, 'DetallePedido', 3, 41),
                                                                                                                        (92, 1, 15.99, 'DetallePedido', 4, 42),
                                                                                                                        (93, 4, 15.99, 'DetallePedido', 5, 42),
                                                                                                                        (94, 4, 15.99, 'DetallePedido', 1, 42),
                                                                                                                        (95, 4, 15.99, 'DetallePedido', 2, 42),
                                                                                                                        (96, 4, 15.99, 'DetallePedido', 3, 42),
                                                                                                                        (97, 1, 15.99, 'DetallePedido', 4, 43),
                                                                                                                        (98, 4, 15.99, 'DetallePedido', 5, 43),
                                                                                                                        (99, 4, 15.99, 'DetallePedido', 1, 43),
                                                                                                                        (100, 4, 15.99, 'DetallePedido', 2, 43),
                                                                                                                        (101, 4, 15.99, 'DetallePedido', 3, 43),
                                                                                                                        (102, 1, 15.99, 'DetallePedido', 4, 44),
                                                                                                                        (103, 4, 15.99, 'DetallePedido', 5, 44),
                                                                                                                        (104, 4, 15.99, 'DetallePedido', 1, 44),
                                                                                                                        (105, 4, 15.99, 'DetallePedido', 2, 44),
                                                                                                                        (106, 4, 15.99, 'DetallePedido', 3, 44),
                                                                                                                        (107, 1, 15.99, 'DetallePedido', 4, 45),
                                                                                                                        (108, 4, 15.99, 'DetallePedido', 5, 45),
                                                                                                                        (109, 4, 15.99, 'DetallePedido', 1, 45),
                                                                                                                        (110, 4, 15.99, 'DetallePedido', 2, 45),
                                                                                                                        (111, 4, 15.99, 'DetallePedido', 3, 45),
                                                                                                                        (112, 1, 15.99, 'DetallePedido', 4, 46),
                                                                                                                        (113, 4, 15.99, 'DetallePedido', 5, 46),
                                                                                                                        (114, 4, 15.99, 'DetallePedido', 1, 46),
                                                                                                                        (115, 4, 15.99, 'DetallePedido', 2, 46),
                                                                                                                        (116, 4, 15.99, 'DetallePedido', 3, 46),
                                                                                                                        (117, 1, 15.99, 'DetallePedido', 4, 47),
                                                                                                                        (118, 4, 15.99, 'DetallePedido', 5, 47),
                                                                                                                        (119, 4, 15.99, 'DetallePedido', 1, 47),
                                                                                                                        (120, 4, 15.99, 'DetallePedido', 2, 47),
                                                                                                                        (121, 4, 15.99, 'DetallePedido', 3, 47),
                                                                                                                        (122, 1, 15.99, 'DetallePedido', 4, 48),
                                                                                                                        (123, 4, 15.99, 'DetallePedido', 5, 48),
                                                                                                                        (124, 4, 15.99, 'DetallePedido', 1, 48),
                                                                                                                        (125, 4, 15.99, 'DetallePedido', 2, 48),
                                                                                                                        (126, 4, 15.99, 'DetallePedido', 3, 48),
                                                                                                                        (127, 1, 15.99, 'DetallePedido', 4, 49),
                                                                                                                        (128, 4, 15.99, 'DetallePedido', 5, 49),
                                                                                                                        (129, 4, 15.99, 'DetallePedido', 1, 49),
                                                                                                                        (130, 4, 15.99, 'DetallePedido', 2, 49),
                                                                                                                        (131, 4, 15.99, 'DetallePedido', 3, 49),
                                                                                                                        (132, 1, 15.99, 'DetallePedido', 4, 50),
                                                                                                                        (133, 4, 15.99, 'DetallePedido', 5, 50),
                                                                                                                        (134, 4, 15.99, 'DetallePedido', 1, 50),
                                                                                                                        (135, 4, 15.99, 'DetallePedido', 2, 50),
                                                                                                                        (136, 4, 15.99, 'DetallePedido', 3, 50);


