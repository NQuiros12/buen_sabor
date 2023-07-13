package vrs.backend.demo.services.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.ArticuloManufacturado;
import vrs.backend.demo.entities.DetalleArticuloManufacturado;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.ArticuloManufacturadoRepository;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.services.ArticuloManufacturadoService;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class ArticuloManufacturadoServiceImpl extends BaseServiceImpl<ArticuloManufacturado, Long> implements ArticuloManufacturadoService {

    private final DetalleArticuloManufacturadoServiImpl detalleArticuloManufacturadoServiImpl;
    private final ArticuloManufacturadoRepository articuloManufacturadoRepository;
    @Value("${paged.size}")
    private int pagedSize;

    public ArticuloManufacturadoServiceImpl(BaseRepository<ArticuloManufacturado, Long> baseRepository,
                                            DetalleArticuloManufacturadoServiImpl detalleArticuloManufacturadoServiImpl,
                                            ArticuloManufacturadoRepository articuloManufacturadoRepository) {
        super(baseRepository);
        this.detalleArticuloManufacturadoServiImpl = detalleArticuloManufacturadoServiImpl;
        this.articuloManufacturadoRepository = articuloManufacturadoRepository;
    }
    public List<ArticuloManufacturado> buscarPorNombre(String nombreArtMan) {
        return articuloManufacturadoRepository.findByName(nombreArtMan);
    }

    public Page<ArticuloManufacturado> findByCategoryWithPagination(String nombreCategoria, Pageable pageable) {
        List<ArticuloManufacturado> allByCategory = articuloManufacturadoRepository.findByCategory(nombreCategoria);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allByCategory.size());
        List<ArticuloManufacturado> subList = allByCategory.subList(start, end);
        return new PageImpl<>(subList, pageable, allByCategory.size());
    }

    public void saveArticuloManufacturado(ArticuloManufacturado articuloManufacturado) throws Exception{
        if (articuloManufacturado.isProductoFinal())  articuloManufacturado.setDetalleArticuloManufacturados(null);
        save(articuloManufacturado);
        // Asignar el ID del artículo manufacturado a los detalles
        if (!articuloManufacturado.isProductoFinal()) {
            for (DetalleArticuloManufacturado detalle : articuloManufacturado.getDetalleArticuloManufacturados()) {
                detalle.setArticuloManufacturado(articuloManufacturado);
            }

            // Guardar los detalles
            detalleArticuloManufacturadoServiImpl.saveAll(articuloManufacturado.getDetalleArticuloManufacturados());
        }
    }
    public void updateArticuloManufacturado(ArticuloManufacturado articuloRecibido, Long idPrevio) throws Exception {
        ArticuloManufacturado articuloPrevio = findById(idPrevio);
        if (articuloPrevio == null) {
            throw new Exception("No se encontro el pedido");
        }
        // Actualizamos el articulo previo
        actualizarArticuloPrevio(articuloPrevio, articuloRecibido);
        if (articuloRecibido.isProductoFinal()) {
            eliminarDetallesArticulo(articuloPrevio);
        } else {
            actualizarDetallesArticulo(articuloPrevio, articuloRecibido);
        }
        // Guardar los cambios en la base de datos
        update(idPrevio, articuloPrevio);
    }

    //Metodo para eliminar sus detalles tanto del articulo como sus persistencias
    private void eliminarDetallesArticulo(ArticuloManufacturado articuloPrevio) throws Exception {
        Iterator<DetalleArticuloManufacturado> iterator = articuloPrevio.getDetalleArticuloManufacturados().iterator();
        while (iterator.hasNext()) {
            DetalleArticuloManufacturado detallePrevio = iterator.next();
            detalleArticuloManufacturadoServiImpl.delete(detallePrevio.getId());
            iterator.remove();
        }
        articuloPrevio.setDetalleArticuloManufacturados(null);
    }


    //Metodo que actualiza todos los atributos del Articulo menos sus detalles
    private void actualizarArticuloPrevio(ArticuloManufacturado articuloPrevio, ArticuloManufacturado articuloRecibido) {
        Field[] fields = articuloRecibido.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(articuloRecibido);

                if (!field.getName().equals("detalleArticuloManufacturados") && !field.getName().equals("id")) {
                    field.set(articuloPrevio, value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    //Metodo que crea, actualiza o borra detalles en funcion a lo recibido en el JSON
    private void actualizarDetallesArticulo(ArticuloManufacturado articuloPrevio, ArticuloManufacturado articuloRecibido) throws Exception {
        List<DetalleArticuloManufacturado> detalles = new ArrayList<>();
        Iterator<DetalleArticuloManufacturado> detallesRecibidos = articuloRecibido.getDetalleArticuloManufacturados().iterator();
        while (detallesRecibidos.hasNext()) {
            DetalleArticuloManufacturado detalleRecibido = detallesRecibidos.next();
            DetalleArticuloManufacturado detalleActualizado;

            // Si el detalle ya existe, se actualiza
            if (detalleRecibido.getId() != null) {
                detalleActualizado = detalleArticuloManufacturadoServiImpl.findById(detalleRecibido.getId());
                if (detalleActualizado == null) {
                    throw new IllegalArgumentException("El detalle no existe.");

                }

                // Actualizar los datos del detalle
                detalleActualizado.setCantidad(detalleRecibido.getCantidad());
                detalleActualizado.setArticuloInsumo(detalleRecibido.getArticuloInsumo());
            } else {
                // Si el detalle es nuevo, se crea
                detalleActualizado = new DetalleArticuloManufacturado();
                detalleActualizado.setCantidad(detalleRecibido.getCantidad());
                detalleActualizado.setArticuloManufacturado(articuloPrevio);
                detalleActualizado.setArticuloInsumo(detalleRecibido.getArticuloInsumo());
            }

            detalles.add(detalleActualizado);
        }

        // Eliminar los detalles que existían pero no se recibieron en el JSON
        List<DetalleArticuloManufacturado> detallesActuales = articuloPrevio.getDetalleArticuloManufacturados();
        Iterator<DetalleArticuloManufacturado> actualIterator = detallesActuales.iterator();
        while (actualIterator.hasNext()) {
            DetalleArticuloManufacturado detalleActual = actualIterator.next();
            if (!detalles.contains(detalleActual)) {
                detalleArticuloManufacturadoServiImpl.delete(detalleActual.getId());
                actualIterator.remove();
            }
        }

        articuloPrevio.setDetalleArticuloManufacturados(detalles);
    }


    public Page<ArticuloManufacturado> orderByPrice (Integer page, String orderPrice, String category) throws Exception{
        try{
            Sort.Direction sortDirection = Sort.Direction.ASC; // Orden ascendente por defecto
            if (orderPrice.equalsIgnoreCase("mayor")) {
                sortDirection = Sort.Direction.DESC; // Orden descendente si se solicita "mayor"
            }
            Pageable pageable = PageRequest.of(page, pagedSize, Sort.by(sortDirection, "precioVenta"));
            Page<ArticuloManufacturado> pageResult = switch (category.toLowerCase()) {
                case "hamburguesas" -> findByCategoryWithPagination("hamburguesas", pageable);
                case "pizzas" -> findByCategoryWithPagination("pizzas", pageable);
                case "bebidas" -> findByCategoryWithPagination("bebidas", pageable);
                case "papas" -> findByCategoryWithPagination("papas", pageable);
                default -> findAll(pageable);
            };

            List<ArticuloManufacturado> content = pageResult.getContent();
            // Ordenar los resultados según el orderPrice
            Comparator<ArticuloManufacturado> precioComparator = (a, b) -> Double.compare(a.getPrecioVenta(), b.getPrecioVenta());

            List<ArticuloManufacturado> sortedContent = new ArrayList<>(content);
            sortedContent.sort(precioComparator); // Orden ascendente por precio

            if (orderPrice.equalsIgnoreCase("mayor")) {
                Collections.reverse(sortedContent); // Invertir el orden para obtener una ordenación descendente
            }

            pageResult = new PageImpl<>(sortedContent, pageable, pageResult.getTotalElements());
            return pageResult;
        } catch (Exception e){
            throw new Exception("No se pudo ordenar por precio"+e);
        }
    }
}
