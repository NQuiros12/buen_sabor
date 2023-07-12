package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.ArticuloManufacturado;
import vrs.backend.demo.entities.DetalleArticuloManufacturado;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.ArticuloManufacturadoRepository;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.services.ArticuloManufacturadoService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ArticuloManufacturadoServiceImpl extends BaseServiceImpl<ArticuloManufacturado, Long> implements ArticuloManufacturadoService {

    private final DetalleArticuloManufacturadoServiImpl detalleArticuloManufacturadoServiImpl;
    private final ArticuloManufacturadoRepository articuloManufacturadoRepository;
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
    public List<ArticuloManufacturado> buscarPorCategoria(String nombreCategoria){
        return articuloManufacturadoRepository.artByCategoria(nombreCategoria);
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

}
