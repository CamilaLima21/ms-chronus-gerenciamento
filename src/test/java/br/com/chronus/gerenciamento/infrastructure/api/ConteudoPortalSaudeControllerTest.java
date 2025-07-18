package br.com.chronus.gerenciamento.infrastructure.api;

import br.com.chronus.gerenciamento.application.domain.ConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.dto.portal.ConteudoPortalSaudeRequest;
import br.com.chronus.gerenciamento.application.enums.EnumFiltroPortalSaude;
import br.com.chronus.gerenciamento.application.gateway.ConteudoPortalSaudeGateway;
import br.com.chronus.gerenciamento.application.mapper.ConteudoPortalSaudeMapper;
import br.com.chronus.gerenciamento.application.usecase.conteudo.CreateConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.usecase.conteudo.DeleteConteudoPortalSaude;
import br.com.chronus.gerenciamento.application.usecase.conteudo.GetAllConteudosPortalSaude;
import br.com.chronus.gerenciamento.application.usecase.conteudo.GetConteudoPortalSaudeByFiltro;
import br.com.chronus.gerenciamento.application.usecase.conteudo.GetConteudoPortalSaudeById;
import br.com.chronus.gerenciamento.application.usecase.conteudo.UpdateConteudoPortalSaude;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class ConteudoPortalSaudeControllerTest {

    @InjectMocks
    private ConteudoPortalSaudeController controller;

    @Mock
    private ConteudoPortalSaudeGateway gateway;

    @Mock
    private ConteudoPortalSaudeMapper mapper;

    @Mock
    private GetConteudoPortalSaudeByFiltro getConteudoPortalSaudeByFiltroUseCase;

    @Mock
    private CreateConteudoPortalSaude createConteudoPortalSaudeUseCase;

    @Mock
    private DeleteConteudoPortalSaude deleteConteudoPortalSaudeUseCase;

    @Mock
    private GetAllConteudosPortalSaude getAllConteudosPortalSaudeUseCase;

    @Mock
    private GetConteudoPortalSaudeById getConteudoPortalSaudeByIdUseCase;

    @Mock
    private UpdateConteudoPortalSaude updateConteudoPortalSaudeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateConteudo() {
        ConteudoPortalSaudeRequest dto = new ConteudoPortalSaudeRequest();
        ConteudoPortalSaude domain = new ConteudoPortalSaude();
        ConteudoPortalSaude saved = new ConteudoPortalSaude();

        when(mapper.toDomain(dto, null)).thenReturn(domain);
        when(createConteudoPortalSaudeUseCase.execute(domain)).thenReturn(saved);

        ResponseEntity<ConteudoPortalSaude> response = controller.createConteudo(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(saved, response.getBody());

        verify(mapper).toDomain(dto, null);
        verify(createConteudoPortalSaudeUseCase).execute(domain);
    }

    @Test
    void testGetConteudoById_Found() {
        int id = 1;
        ConteudoPortalSaude conteudo = new ConteudoPortalSaude();

        when(getConteudoPortalSaudeByIdUseCase.execute(id)).thenReturn(conteudo);

        ResponseEntity<ConteudoPortalSaude> response = controller.getConteudoById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(conteudo, response.getBody());

        verify(getConteudoPortalSaudeByIdUseCase).execute(id);
    }

    @Test
    void testUpdateConteudo() {
        int id = 1;
        ConteudoPortalSaudeRequest dto = new ConteudoPortalSaudeRequest();
        ConteudoPortalSaude domain = new ConteudoPortalSaude();
        ConteudoPortalSaude updated = new ConteudoPortalSaude();

        when(mapper.toDomain(dto, id)).thenReturn(domain);
        when(updateConteudoPortalSaudeUseCase.execute(domain)).thenReturn(updated);

        ResponseEntity<ConteudoPortalSaude> response = controller.updateConteudo(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updated, response.getBody());

        verify(mapper).toDomain(dto, id);
        verify(updateConteudoPortalSaudeUseCase).execute(domain);
    }

    @Test
    void testDeleteConteudo() {
        int id = 1;

        doNothing().when(deleteConteudoPortalSaudeUseCase).execute(id);

        ResponseEntity<Void> response = controller.deleteConteudo(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(deleteConteudoPortalSaudeUseCase).execute(id);
    }

    @Test
    void testGetAllConteudos() {
        List<ConteudoPortalSaude> list = Arrays.asList(new ConteudoPortalSaude(), new ConteudoPortalSaude());

        when(getAllConteudosPortalSaudeUseCase.execute()).thenReturn(list);

        ResponseEntity<List<ConteudoPortalSaude>> response = controller.getAllConteudos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());

        verify(getAllConteudosPortalSaudeUseCase).execute();
    }

    @Test
    void testGetConteudosByFiltro() {
        EnumFiltroPortalSaude filtro = EnumFiltroPortalSaude.CUIDADOS_ASMA;
        List<ConteudoPortalSaude> list = Arrays.asList(new ConteudoPortalSaude());

        when(getConteudoPortalSaudeByFiltroUseCase.execute(filtro)).thenReturn(list);

        ResponseEntity<List<ConteudoPortalSaude>> response = controller.getConteudosByFiltro(filtro);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());

        verify(getConteudoPortalSaudeByFiltroUseCase).execute(filtro);
    }

}
