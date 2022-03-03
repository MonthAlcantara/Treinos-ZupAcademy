package io.github.monthalcantara.casadocodigo.processor;

import io.github.monthalcantara.casadocodigo.exception.RecursoNaoEncontradoException;
import io.github.monthalcantara.casadocodigo.model.Estado;
import io.github.monthalcantara.casadocodigo.model.Pais;
import io.github.monthalcantara.casadocodigo.repository.EstadoRepository;
import io.github.monthalcantara.casadocodigo.repository.PaisRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

//@ExtendWith(MockitoExtension.class) se quiser usar o @Mock
class CadastraEstadoProcessorTest {

    private EstadoRepository estadoRepository;
    private PaisRepository paisRepository;
    private CadastraEstadoProcessor cadastraEstadoProcessor;

    @BeforeEach
    public void init() {
        estadoRepository = Mockito.mock(EstadoRepository.class);
        paisRepository = Mockito.mock(PaisRepository.class);
        cadastraEstadoProcessor = new CadastraEstadoProcessor(estadoRepository, paisRepository);
    }

    @Test
    @DisplayName("Pais encontrado e não existe estado com o mesmo nome")
    void processaTest() {

        Optional<Pais> pais = Optional.of(new Pais("Brasil"));
        Optional<Estado> estadoOptional = Optional.of(new Estado("Bahia", pais.get()));

        Mockito.when(paisRepository.findById(any(Long.class))).thenReturn(pais);
        Mockito.when(estadoRepository.findByNomeAndPaisId(any(), any())).thenReturn(Optional.empty());
        Mockito.when(estadoRepository.save(any(Estado.class))).thenReturn(estadoOptional.get());

        Estado bahia = cadastraEstadoProcessor.processa("Bahia", 1L);

        Assertions.assertNotNull(bahia);
    }

    @Test
    @DisplayName("Pais encontrado e existe estado com o mesmo nome e lança IllegalArgumentException")
    void processaErrorTest() {

        Optional<Pais> pais = Optional.of(new Pais("Brasil"));
        Optional<Estado> estadoOptional = Optional.of(new Estado("Bahia", pais.get()));

        Mockito.when(paisRepository.findById(any(Long.class))).thenReturn(pais);
        Mockito.when(estadoRepository.findByNomeAndPaisId(any(), any())).thenReturn(Optional.empty());
        Mockito.when(estadoRepository.findByNomeAndPaisId(any(), any())).thenReturn(estadoOptional);

        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> cadastraEstadoProcessor.processa("Bahia", 1L));
        Assertions.assertTrue(illegalArgumentException.getMessage().contains("Ja existe um estado com esse nome cadastrado para o Pais"));
    }

    @Test
    @DisplayName("Pais não encontrado e lança RecursoNaoEncontradoException")
    void processaPaisNaoEncontradoErrorTest() {

        Mockito.when(paisRepository.findById(1L)).thenReturn(Optional.empty());

        RecursoNaoEncontradoException exception = Assertions.assertThrows(RecursoNaoEncontradoException.class, () -> cadastraEstadoProcessor.processa("Bahia", 1L));
        Assertions.assertTrue(exception.getMensagem().contains("Não foi encontrado pais com esse id: 1"));
    }
}